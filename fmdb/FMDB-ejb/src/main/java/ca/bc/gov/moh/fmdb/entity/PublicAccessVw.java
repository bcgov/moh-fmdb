/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        PublicAccessVw.java                            *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.entity;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "PublicAccessVw.findAll", 
  query = "select o from PublicAccessVw o")
@Table(name = "PUBLIC_ACCESS_VW")
public class PublicAccessVw
  implements Serializable
{
  @Column(name="CDR_REVIEW_YN")
  private String cdrReviewYn;
  @Column(name="CEDAC_RECOMMENDATION_DT")
  private Timestamp cedacRecommendationDt;
  @Column(name="CHEMICAL_NM", nullable = false)
  private String chemicalNm;
  @Column(name="COMPANY_NOTICE_SENT_DT")
  private Timestamp companyNoticeSentDt;
  @Column(name="LEGAL_NM", nullable = false)
  private String legalNm;
  @Column(name="LETTER_BIN")
  private byte[] letterBin;
  @Column(name="MEDICAL_INDICATIONS_TXT")
  private String medicalIndicationsTxt;
  @Column(name="PSD_WEB_COMMENTS")
  private String psdWebComments;
  @Column(name="RECEIVED_DT")
  private Timestamp receivedDt;
  @Column(name="REVIEW_STATUS_DSC", nullable = false)
  private String reviewStatusDsc;
  @Id
  @Column(name="SUBMISSION_ID", nullable = false)
  private Long submissionId;
  @Column(name="SUBMISSION_TYPE_DSC", nullable = false)
  private String submissionTypeDsc;
  @Column(name="TRADE_NM")
  private String tradeNm;

  public PublicAccessVw()
  {
  }

  public String getCdrReviewYn()
  {
    return cdrReviewYn;
  }

  public void setCdrReviewYn(String cdrReviewYn)
  {
    this.cdrReviewYn = cdrReviewYn;
  }

  public Timestamp getCedacRecommendationDt()
  {
    return cedacRecommendationDt;
  }

  public void setCedacRecommendationDt(Timestamp cedacRecommendationDt)
  {
    this.cedacRecommendationDt = cedacRecommendationDt;
  }

  public String getChemicalNm()
  {
    return chemicalNm;
  }

  public void setChemicalNm(String chemicalNm)
  {
    this.chemicalNm = chemicalNm;
  }

  public Timestamp getCompanyNoticeSentDt()
  {
    return companyNoticeSentDt;
  }

  public void setCompanyNoticeSentDt(Timestamp companyNoticeSentDt)
  {
    this.companyNoticeSentDt = companyNoticeSentDt;
  }

  public String getLegalNm()
  {
    return legalNm;
  }

  public void setLegalNm(String legalNm)
  {
    this.legalNm = legalNm;
  }

  public byte[] getLetterBin()
  {
    return letterBin;
  }

  public void setLetterBin(byte[] letterBin)
  {
    this.letterBin = letterBin;
  }

  public String getMedicalIndicationsTxt()
  {
    return medicalIndicationsTxt;
  }

  public void setMedicalIndicationsTxt(String medicalIndicationsTxt)
  {
    this.medicalIndicationsTxt = medicalIndicationsTxt;
  }

  public String getPsdWebComments()
  {
    return psdWebComments;
  }

  public void setPsdWebComments(String psdWebComments)
  {
    this.psdWebComments = psdWebComments;
  }

  public Timestamp getReceivedDt()
  {
    return receivedDt;
  }

  public void setReceivedDt(Timestamp receivedDt)
  {
    this.receivedDt = receivedDt;
  }

  public String getReviewStatusDsc()
  {
    return reviewStatusDsc;
  }

  public void setReviewStatusDsc(String reviewStatusDsc)
  {
    this.reviewStatusDsc = reviewStatusDsc;
  }

  public Long getSubmissionId()
  {
    return submissionId;
  }

  public void setSubmissionId(Long submissionId)
  {
    this.submissionId = submissionId;
  }

  public String getSubmissionTypeDsc()
  {
    return submissionTypeDsc;
  }

  public void setSubmissionTypeDsc(String submissionTypeDsc)
  {
    this.submissionTypeDsc = submissionTypeDsc;
  }

  public String getTradeNm()
  {
    return tradeNm;
  }

  public void setTradeNm(String tradeNm)
  {
    this.tradeNm = tradeNm;
  }
}
