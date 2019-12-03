package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the i0032 database table.
 * 
 */
@Entity
@Table(name = "i0032")
@NamedQuery(name = "I0032.findAll", query = "SELECT i FROM I0032 i")
public class I0032 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int buyer_ID;

	@Column(name = "category_name")
	private String categoryName;

	private String city;

	private String country;

	private int country_ID;
	
	private String approvalStatus;

	@Column(name = "cross_total")
	private String crossTotal;

	@Column(name = "customer_name")
	private String customerName;

	@Temporal(TemporalType.DATE)
	@Column(name = "delivery_date")
	private Date deliveryDate;

	@Column(name = "e_mail")
	private String eMail;

	@Column(name = "firm_type")
	private String firmType;

	@Column(name = "freelancer_name")
	private String freelancerName;

	@Column(name = "nature_of_business")
	private String natureOfBusiness;

	private String note;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "sales_order_date")
	private Date salesOrderDate;

	@Column(name = "sales_order_number")
	private String salesOrderNumber;

	@Column(name = "shiping_address")
	private String shipingAddress;

	@Column(name = "shipping_charge")
	private String shippingCharge;

	private String state;

	private String address;
	
	private String status;

	private String status2;

	@Column(name = "tax_number")
	private String taxNumber;

	@Column(name = "total_number_of_count")
	private int totalNumberOfCount;

	private String client_ID;
	
	@Column(name = "customer_title")
	private String customerTitle;
	
	@Column(name = "customer_middle_name")
	private String customerMiddleName;
	
	@Column(name = "customer_last_name")
	private String customerLastName;
	
	@Column(name = "customer_suffix")
	private String customerSuffix;
	
	@Column(name = "customer_mobile")
	private String customerMobile;
	
	@Column(name = "fax_number")
	private String customerFaxNumber;
	
	private String other;
	
	private String company;
	
	private String website;
	
	@Column(name = "display_name_as")
	private String displayNameAs;
	
	@Column(name = "present_city")
	private String presentCity;
	
	@Column(name = "present_country")
	private String presentCountry;
	
	@Column(name = "permanent_postal_code")
	private String permanentPostCode;
	
	@Column(name = "present_postal_code")
	private String presentPostCode;
	
	@Column(name = "present_state")
	private String presentState;
	
	@Column(name = "attachment_file")
	private String attachmentFile;
	
	@Column(name = "photo_upload")
	private String photoUpload;
	
	@Column(name = "phone_code")
	private String phoneCode;
	
	// bi-directional many-to-one association to UserCreate
	@ManyToOne
	@JoinColumn(name = "user_ID")
	private UserCreate userID;
	
	private String customerLicenseNumber;
	
	@Temporal(TemporalType.DATE)
	private Date customerExpireDate; 
	
	private String customerType;
	
	private String customerCode;	
	
	
	
	
	@Column(name = "PN_Name")
	private String pnName;
	
	@Column(name = "PN_Father_Relation")
	private String pnFatherRelation;
	
	@Column(name = "PN_DOB")
	private Date pnDOB;
	
	@Column(name = "PN_EmailID")
	private String pnEmailID;
	
	@Column(name = "PN_Phonenumber1")
	private String pnPhoneNumber1;
	
	@Column(name = "PN_Phonenumber2")
	private String pnPhoneNumber2;
	
	@Column(name = "PN_Address")
	private String pnAddress;
	
	@Column(name = "Date_of_Join")
	private Date dateOfJoin;
	
	@Column(name="paymentType")
	private String paymentType;  
	
	@ManyToOne
	@JoinColumn(name = "paymentCash_ID") 	
	private Paymentcash paymentCash_ID; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	public String getCustomerLicenseNumber() {
		return customerLicenseNumber;
	}

	public void setCustomerLicenseNumber(String customerLicenseNumber) {
		this.customerLicenseNumber = customerLicenseNumber;
	}

	public Date getCustomerExpireDate() {
		return customerExpireDate;
	}

	public void setCustomerExpireDate(Date customerExpireDate) {
		this.customerExpireDate = customerExpireDate;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Paymentcash getPaymentCash_ID() {
		return paymentCash_ID;
	}

	public void setPaymentCash_ID(Paymentcash paymentCash_ID) {
		this.paymentCash_ID = paymentCash_ID;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public UserCreate getUserID() {
		return userID;
	}

	public void setUserID(UserCreate userID) {
		this.userID = userID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getCustomerTitle() {
		return customerTitle;
	}

	public void setCustomerTitle(String customerTitle) {
		this.customerTitle = customerTitle;
	}

	public String getCustomerMiddleName() {
		return customerMiddleName;
	}

	public void setCustomerMiddleName(String customerMiddleName) {
		this.customerMiddleName = customerMiddleName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerSuffix() {
		return customerSuffix;
	}

	public void setCustomerSuffix(String customerSuffix) {
		this.customerSuffix = customerSuffix;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerFaxNumber() {
		return customerFaxNumber;
	}

	public void setCustomerFaxNumber(String customerFaxNumber) {
		this.customerFaxNumber = customerFaxNumber;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDisplayNameAs() {
		return displayNameAs;
	}

	public void setDisplayNameAs(String displayNameAs) {
		this.displayNameAs = displayNameAs;
	}

	public String getPresentCity() {
		return presentCity;
	}

	public void setPresentCity(String presentCity) {
		this.presentCity = presentCity;
	}

	public String getPresentCountry() {
		return presentCountry;
	}

	public void setPresentCountry(String presentCountry) {
		this.presentCountry = presentCountry;
	}

	public String getPermanentPostCode() {
		return permanentPostCode;
	}

	public void setPermanentPostCode(String permanentPostCode) {
		this.permanentPostCode = permanentPostCode;
	}

	public String getPresentPostCode() {
		return presentPostCode;
	}

	public void setPresentPostCode(String presentPostCode) {
		this.presentPostCode = presentPostCode;
	}

	public String getPresentState() {
		return presentState;
	}

	public void setPresentState(String presentState) {
		this.presentState = presentState;
	}

	public String getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(String attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public String getPhotoUpload() {
		return photoUpload;
	}

	public void setPhotoUpload(String photoUpload) {
		this.photoUpload = photoUpload;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}
	
	public I0032() {
	}

	public int getBuyer_ID() {
		return this.buyer_ID;
	}

	public void setBuyer_ID(int buyer_ID) {
		this.buyer_ID = buyer_ID;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getCountry_ID() {
		return this.country_ID;
	}

	public void setCountry_ID(int country_ID) {
		this.country_ID = country_ID;
	}

	public String getCrossTotal() {
		return this.crossTotal;
	}

	public void setCrossTotal(String crossTotal) {
		this.crossTotal = crossTotal;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getEMail() {
		return this.eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public String getFirmType() {
		return this.firmType;
	}

	public void setFirmType(String firmType) {
		this.firmType = firmType;
	}

	public String getFreelancerName() {
		return this.freelancerName;
	}

	public void setFreelancerName(String freelancerName) {
		this.freelancerName = freelancerName;
	}

	public String getNatureOfBusiness() {
		return this.natureOfBusiness;
	}

	public void setNatureOfBusiness(String natureOfBusiness) {
		this.natureOfBusiness = natureOfBusiness;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getSalesOrderDate() {
		return this.salesOrderDate;
	}

	public void setSalesOrderDate(Date salesOrderDate) {
		this.salesOrderDate = salesOrderDate;
	}

	public String getSalesOrderNumber() {
		return this.salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public String getShipingAddress() {
		return this.shipingAddress;
	}

	public void setShipingAddress(String shipingAddress) {
		this.shipingAddress = shipingAddress;
	}

	public String getShippingCharge() {
		return this.shippingCharge;
	}

	public void setShippingCharge(String shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus2() {
		return this.status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	/*
	 * public int getTaxNumber() { return this.taxNumber; }
	 * 
	 * public void setTaxNumber(int taxNumber) { this.taxNumber = taxNumber; }
	 */

	public int getTotalNumberOfCount() {
		return this.totalNumberOfCount;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public void setTotalNumberOfCount(int totalNumberOfCount) {
		this.totalNumberOfCount = totalNumberOfCount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getPnName() {
		return pnName;
	}

	public void setPnName(String pnName) {
		this.pnName = pnName;
	}

	public String getPnFatherRelation() {
		return pnFatherRelation;
	}

	public void setPnFatherRelation(String pnFatherRelation) {
		this.pnFatherRelation = pnFatherRelation;
	}

	public Date getPnDOB() {
		return pnDOB;
	}

	public void setPnDOB(Date pnDOB) {
		this.pnDOB = pnDOB;
	}

	public String getPnEmailID() {
		return pnEmailID;
	}

	public void setPnEmailID(String pnEmailID) {
		this.pnEmailID = pnEmailID;
	}

	public String getPnPhoneNumber1() {
		return pnPhoneNumber1;
	}

	public void setPnPhoneNumber1(String pnPhoneNumber1) {
		this.pnPhoneNumber1 = pnPhoneNumber1;
	}

	public String getPnPhoneNumber2() {
		return pnPhoneNumber2;
	}

	public void setPnPhoneNumber2(String pnPhoneNumber2) {
		this.pnPhoneNumber2 = pnPhoneNumber2;
	}

	public String getPnAddress() {
		return pnAddress;
	}

	public void setPnAddress(String pnAddress) {
		this.pnAddress = pnAddress;
	}

	public Date getDateOfJoin() {
		return dateOfJoin;
	}

	public void setDateOfJoin(Date dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}

	
}