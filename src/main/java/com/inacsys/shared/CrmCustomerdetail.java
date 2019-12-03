package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the crm_customerdetails database table.
 * 
 */
@Entity
@Table(name="crm_customerdetails")
@NamedQuery(name="CrmCustomerdetail.findAll", query="SELECT c FROM CrmCustomerdetail c")
public class CrmCustomerdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int crm_customerDetails_ID;

	private String client_ID;

	private String code;

	private String company;

	private String companyName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	private String createdPerson;

	private String crmCode;

	private String customerComments;

	private String customerProduct;

	private String customerType;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String displayName;

	@Column(name="email_1")
	private String email1;

	@Column(name="email_2")
	private String email2;

	@Temporal(TemporalType.DATE)
	private Date expireDate;

	private String faxNo_1;

	private String faxNo_2;

	@Temporal(TemporalType.DATE)
	private Date followupDate;

	private String industry;

	private String licenceNo;

	private String middleName;

	private String mobileNo_1;

	private String mobileNo_2;

	private String modeOfCommunication;

	private String notes;

	private String other;

	private String permenantAddress;

	private String permenantCity;

	private String permenantCountry;

	private String permenantPostalcode;

	private String permenantState;

	private String personName;

	private String presentAddress;

	private String presentCity;

	private String presentCountry;

	private String presentPostalcode;

	private String presentState;

	private String status;

	private String suffix;

	private String taxNo;

	private String title;
	
	private String approvalStatus;
	
	

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	private String updatePerson;

	@ManyToOne
	@JoinColumn(name = "user_ID")
	private UserCreate userID;

	@Column(name="website_1")
	private String website1;

	@Column(name="website_2")
	private String website2;

	public CrmCustomerdetail() {
	}

	public int getCrm_customerDetails_ID() {
		return this.crm_customerDetails_ID;
	}

	public void setCrm_customerDetails_ID(int crm_customerDetails_ID) {
		this.crm_customerDetails_ID = crm_customerDetails_ID;
	}

	public String getClient_ID() {
		return this.client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedPerson() {
		return this.createdPerson;
	}

	public void setCreatedPerson(String createdPerson) {
		this.createdPerson = createdPerson;
	}

	public String getCrmCode() {
		return this.crmCode;
	}

	public void setCrmCode(String crmCode) {
		this.crmCode = crmCode;
	}

	public String getCustomerComments() {
		return this.customerComments;
	}

	public void setCustomerComments(String customerComments) {
		this.customerComments = customerComments;
	}

	public String getCustomerProduct() {
		return this.customerProduct;
	}

	public void setCustomerProduct(String customerProduct) {
		this.customerProduct = customerProduct;
	}

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail1() {
		return this.email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return this.email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getFaxNo_1() {
		return this.faxNo_1;
	}

	public void setFaxNo_1(String faxNo_1) {
		this.faxNo_1 = faxNo_1;
	}

	public String getFaxNo_2() {
		return this.faxNo_2;
	}

	public void setFaxNo_2(String faxNo_2) {
		this.faxNo_2 = faxNo_2;
	}

	public Date getFollowupDate() {
		return this.followupDate;
	}

	public void setFollowupDate(Date followupDate) {
		this.followupDate = followupDate;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getLicenceNo() {
		return this.licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMobileNo_1() {
		return this.mobileNo_1;
	}

	public void setMobileNo_1(String mobileNo_1) {
		this.mobileNo_1 = mobileNo_1;
	}

	public String getMobileNo_2() {
		return this.mobileNo_2;
	}

	public void setMobileNo_2(String mobileNo_2) {
		this.mobileNo_2 = mobileNo_2;
	}

	public String getModeOfCommunication() {
		return this.modeOfCommunication;
	}

	public void setModeOfCommunication(String modeOfCommunication) {
		this.modeOfCommunication = modeOfCommunication;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getPermenantAddress() {
		return this.permenantAddress;
	}

	public void setPermenantAddress(String permenantAddress) {
		this.permenantAddress = permenantAddress;
	}

	public String getPermenantCity() {
		return this.permenantCity;
	}

	public void setPermenantCity(String permenantCity) {
		this.permenantCity = permenantCity;
	}

	public String getPermenantCountry() {
		return this.permenantCountry;
	}

	public void setPermenantCountry(String permenantCountry) {
		this.permenantCountry = permenantCountry;
	}

	public String getPermenantPostalcode() {
		return this.permenantPostalcode;
	}

	public void setPermenantPostalcode(String permenantPostalcode) {
		this.permenantPostalcode = permenantPostalcode;
	}

	public String getPermenantState() {
		return this.permenantState;
	}

	public void setPermenantState(String permenantState) {
		this.permenantState = permenantState;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPresentAddress() {
		return this.presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getPresentCity() {
		return this.presentCity;
	}

	public void setPresentCity(String presentCity) {
		this.presentCity = presentCity;
	}

	public String getPresentCountry() {
		return this.presentCountry;
	}

	public void setPresentCountry(String presentCountry) {
		this.presentCountry = presentCountry;
	}

	public String getPresentPostalcode() {
		return this.presentPostalcode;
	}

	public void setPresentPostalcode(String presentPostalcode) {
		this.presentPostalcode = presentPostalcode;
	}

	public String getPresentState() {
		return this.presentState;
	}

	public void setPresentState(String presentState) {
		this.presentState = presentState;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getTaxNo() {
		return this.taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdatePerson() {
		return this.updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	

	public UserCreate getUserID() {
		return userID;
	}

	public void setUserID(UserCreate userID) {
		this.userID = userID;
	}

	public String getWebsite1() {
		return this.website1;
	}

	public void setWebsite1(String website1) {
		this.website1 = website1;
	}

	public String getWebsite2() {
		return this.website2;
	}

	public void setWebsite2(String website2) {
		this.website2 = website2;
	}

}