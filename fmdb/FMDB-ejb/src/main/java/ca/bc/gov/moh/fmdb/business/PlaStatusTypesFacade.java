/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        PlaStatusTypesFacade.java                      *
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
import ca.bc.gov.moh.fmdb.entity.PlaStatusTypes;
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
public class PlaStatusTypesFacade implements PlaStatusTypesFacadeLocal {
    
    
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;
    private Logger logger = LogManager.getLogManager().getLogger(Constants.EJB_LOGGER);
    
    public void create(PlaStatusTypes PlaStatusTypes) {
        em.persist(PlaStatusTypes);
    }

    public void edit(PlaStatusTypes PlaStatusTypes) {
        em.merge(PlaStatusTypes);
    }

    public void remove(PlaStatusTypes PlaStatusTypes) {
        em.remove(em.merge(PlaStatusTypes));
    }

    public PlaStatusTypes find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.PlaStatusTypes.class, id);
    }
    
    /**
     * finds all of the PlaStatusTypes regardless of the active code. These are used
     * on the admin screen.
     * 
     * @return List<PlaStatusTypes> - a list of all PlaStatusTypes
     */
    public List<PlaStatusTypes> findAll() {
        return em.createQuery("select object(o) from PlaStatusTypes as o").getResultList();
    }
    
    /**
     * returns a List of all the active PLA Status codes used on the submission screens.
     * 
     * @return List<PlaStatusTypes> - a list of active codes
     */
    public List<PlaStatusTypes> findAllActive() {
        
        String queryStr = "SELECT p FROM PlaStatusTypes p WHERE p.activeYn = :active" ;   
        Query query = em.createQuery(queryStr);
        query.setParameter("active", 'Y');
        
        List<PlaStatusTypes> result = query.getResultList();
        
        return result;
        
    }
    
    /**
     * saves or updates a PlaStatusTypes code. If the id is null then it is a new code
     * which requires the new ID to be determined.
     * 
     * @param plaCodes - PlaStatusTypes entity containing the code info
     * @throws ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void savePlaStatusCodes(PlaStatusTypes plaCodes) throws SaveFMDBException {
        if (plaCodes == null) {
            throw new SaveFMDBException("Cannot save the code since it's NULL.", SaveFMDBException.SaveExceptionType.SUBMISSION_NULL);
        }


        //See if we're trying to create a NEW submission, or update an EXISTING one
        if (plaCodes.getPlaStatusCd() == null) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Attempting to create a new code");
            }

            //Get next available id #

            Query query = em.createNamedQuery("PlaStatusTypes.findMaxId");
            List resultQry = query.getResultList();
            String lastNumberUsed = "";
            if (resultQry != null) {
                if (resultQry.get(0) instanceof List)
                {
                    List lastNumberUsedList = (List) resultQry.get(0);
                    lastNumberUsed = lastNumberUsedList.get(0).toString();
                }
                else
                {
                    lastNumberUsed = resultQry.get(0).toString();
                }
            }

            Long newNumber = new Long(lastNumberUsed);
            newNumber++;

            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Next available ID = '" + newNumber + "'.");
            }


            plaCodes.setPlaStatusCd(newNumber.toString());
            this.em.persist(plaCodes);
        } else {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Attempting to save an existing code with id '" +
                        plaCodes.getPlaStatusCd() + "'");
            }

            if (this.find(plaCodes.getPlaStatusCd()) == null) {
                String msg = "Cannot save changes to the code with id '" +
                        plaCodes.getPlaStatusCd() + "' since the code doesn't exist.";

                throw new SaveFMDBException(msg, SaveFMDBException.SaveExceptionType.CODE_NOT_FOUND);
            }

            this.em.merge(plaCodes);
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
        
        String queryStr = "SELECT COUNT(s.submissionId) FROM Submission s, ProductListingAgreement p, PlaStatusTypes pt WHERE (p.plaStatusCd = pt.plaStatusCd) AND (s.productListingAgreement = p.plaId) AND (pt.plaStatusCd = :code)";
        Query query = em.createQuery(queryStr);
        
        query.setParameter("code", code);

        List result = query.getResultList();
        
        Long resultLong = (Long)result.get(0);

        return resultLong.longValue();


    }
    
}
