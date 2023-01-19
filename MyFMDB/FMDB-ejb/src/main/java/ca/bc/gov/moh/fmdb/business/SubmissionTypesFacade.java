/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SubmissionTypesFacade.java                     *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.bc.gov.moh.fmdb.business;

import ca.bc.gov.moh.fmdb.Constants;
import ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException;
import ca.bc.gov.moh.fmdb.entity.SubmissionTypes;
import ca.bc.gov.moh.fmdb.logging.LogManager;
import ca.bc.gov.moh.fmdb.logging.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author Chris.Prince
 */
@Stateless
public class SubmissionTypesFacade implements SubmissionTypesFacadeLocal {

    @PersistenceContext(unitName = "FMDB-ejbPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    private Logger logger = LogManager.getLogManager().getLogger(Constants.EJB_LOGGER);

    public void create(SubmissionTypes SubmissionTypes) {
        em.persist(SubmissionTypes);
    }

    public void edit(SubmissionTypes SubmissionTypes) {
        em.merge(SubmissionTypes);
    }

    public void remove(SubmissionTypes SubmissionTypes) {
        em.remove(em.merge(SubmissionTypes));
    }

    public SubmissionTypes find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.SubmissionTypes.class, id);
    }

    /**
     * finds all of the SubmissionTypes regardless of the active code. These are used
     * on the admin screen.
     * 
     * @return List<SubmissionTypes> - a list of all SubmissionTypes
     */
    public List<SubmissionTypes> findAll() {
        em.clear();
        String queryStr = "select object(o) from SubmissionTypes as o order by o.submissionTypeDsc";
        Query query = em.createQuery(queryStr);
        return query.getResultList();
    }

    /**
     * finds all of the active SubmissionTypes which are set to active. 
     * 
     * @return List<SubmissionTypes> - a list of active SubmissionTypes
     */
    public List<SubmissionTypes> findAllActive() {
        em.clear();
        String queryStr = "SELECT s FROM SubmissionTypes s WHERE s.activeYn = :active order by s.submissionTypeDsc";
        Query query = em.createQuery(queryStr);
        query.setParameter("active", 'Y');
        return query.getResultList();
    }

    /**
     * executed when the user saves or a code. Based on the boolean value passed it 
     * is determined whether this is an update or an addition.
     * 
     * @param subCodes - SubmissionTypes entity to add or update
     * @param newCode - boolean indicating if it is an addition or update.
     * @throws ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveSubmissionTypeCodes(SubmissionTypes subCodes, boolean newCode) throws SaveFMDBException {
        if (subCodes == null) {
            throw new SaveFMDBException("Cannot save the code since it's NULL.", SaveFMDBException.SaveExceptionType.SUBMISSION_NULL);
        }

        if (newCode && em.find(subCodes.getClass(), subCodes.getSubmissionTypeCd()) != null) {
            throw new SaveFMDBException("Cannot save the code since it already exists.", SaveFMDBException.SaveExceptionType.OTHER);
        }

        //See if we're trying to create a NEW submission, or update an EXISTING one
        if (newCode) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Attempting to create a new code");
            }
            this.em.persist(subCodes);
        } else {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Attempting to save an existing code with id '" +
                        subCodes.getSubmissionTypeCd() + "'");
            }
            if (this.find(subCodes.getSubmissionTypeCd()) == null) {
                String msg = "Cannot save changes to the code with id '" +
                        subCodes.getSubmissionTypeCd() + "' since the code doesn't exist.";

                throw new SaveFMDBException(msg, SaveFMDBException.SaveExceptionType.CODE_NOT_FOUND);
            }

            this.em.merge(subCodes);
        }
    }

    /**
     * when a user de-activates a code this method is executed to determine how many submissions are
     * using that code.
     *
     * @param code - the code which is being set to inactive.
     * @return long - a count of Submissions.
     */
    public long findAssociatedSubmissions(String code) {

        SubmissionTypes subTypes = new SubmissionTypes(code);
        String queryStr = "SELECT COUNT(s.submissionId) FROM Submission s, SubmissionReviewDetails srd WHERE s.submissionTypeCd = :code AND srd.companyNoticeSentDt IS NULL";

        Query query = em.createQuery(queryStr);
        query.setParameter("code", subTypes);

        List result = query.getResultList();

        Long resultLong = (Long) result.get(0);

        return resultLong.longValue();
    }
}
