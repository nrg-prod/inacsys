package com.inacsys.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the i0025 database table.
 * 
 */
@Entity
@Table(name="i0025")
@NamedQuery(name="I0025.findAll", query="SELECT i FROM I0025 i")
public class I0025 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int vendor_ID;

	private String address;

	private String attachemantfiles;

	private String category;

	@Column(name="city_name")
	private String cityName;

	private String client_ID;

	private String company;

	private String email_ID_vendor;

	@Column(name="fax_vendor")
	private String faxVendor;

	@Column(name="firm_name")
	private String firmName;

	@Column(name="firm_registration_number")
	private String firmRegistrationNumber;

	@Column(name="firm_type")
	private String firmType;

	@Column(name="nature_of_business")
	private String natureOfBusiness;
	
	@Column(name="notes_any")
	private String notesAny;

	@Column(name="other_firm_type")
	private String otherFirmType;

	@Column(name="pereson_incharge")
	private String peresonIncharge;

	private String permenantaddress;

	private String permenantcity;

	private String permenantcountry;

	private String permenantpostalcode;

	private String permenantstate;

	private String phonenum1;

	private String phonenum2;

	private String presentcity;

	private String presentcountry;

	private String presentpostalcode;

	private String presentstate;

	@Column(name="state_name")
	private String stateName;

	private String status;

	private String approvalStatus;
	
	private String status2;

	private String uploadfile;

	@Column(name="vendor_phone_number")
	private String vendorPhoneNumber;

	@Column(name="vendor_telephone_number")
	private String vendorTelephoneNumber;

	@Column(name="website_any")
	private String websiteAny;
	
	// bi-directional many-to-one association to I0026
	@ManyToOne
	@JoinColumn(name = "frim_ID")
	private I0026 i0026;

	// bi-directional many-to-one association to I0027
	@ManyToOne
	@JoinColumn(name = "nature_of_business_id")
	private I0027 i0027;
	
	// bi-directional many-to-one association to UserCreate
	@ManyToOne
	@JoinColumn(name = "user_ID")
	private UserCreate userID;
	
	private String vendorLicenceNumber;
	
	@Temporal(TemporalType.DATE)
	private Date vendorExpireDate; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	private String vendorType;
	
	private String vendorCode;	
	
	@Column(name="paymentType")
	private String paymentType;  
	
	@ManyToOne
	@JoinColumn(name = "paymentCash_ID") 	
	private Paymentcash paymentCash_ID;  
	
	public String getVendorLicenceNumber() {
		return vendorLicenceNumber;
	}

	public void setVendorLicenceNumber(String vendorLicenceNumber) {
		this.vendorLicenceNumber = vendorLicenceNumber;
	}

	public Date getVendorExpireDate() {
		return vendorExpireDate;
	}

	public void setVendorExpireDate(Date vendorExpireDate) {
		this.vendorExpireDate = vendorExpireDate;
	}

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
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

	public I0026 getI0026() {
		return i0026;
	}

	public void setI0026(I0026 i0026) {
		this.i0026 = i0026;
	}

	public I0027 getI0027() {
		return i0027;
	}

	public void setI0027(I0027 i0027) {
		this.i0027 = i0027;
	}

	public I0028 getI0028() {
		return i0028;
	}

	public void setI0028(I0028 i0028) {
		this.i0028 = i0028;
	}

	// bi-directional many-to-one association to I0028
	@ManyToOne
	@JoinColumn(name = "country_ID")
	private I0028 i0028;

	
	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public I0025() {
	}

	
	public UserCreate getUserID() {
		return userID;
	}

	public void setUserID(UserCreate userID) {
		this.userID = userID;
	}

	public int getVendor_ID() {
		return this.vendor_ID;
	}

	public void setVendor_ID(int vendor_ID) {
		this.vendor_ID = vendor_ID;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAttachemantfiles() {
		return this.attachemantfiles;
	}

	public void setAttachemantfiles(String attachemantfiles) {
		this.attachemantfiles = attachemantfiles;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getClient_ID() {
		return this.client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail_ID_vendor() {
		return this.email_ID_vendor;
	}

	public void setEmail_ID_vendor(String email_ID_vendor) {
		this.email_ID_vendor = email_ID_vendor;
	}

	public String getFaxVendor() {
		return this.faxVendor;
	}

	public void setFaxVendor(String faxVendor) {
		this.faxVendor = faxVendor;
	}

	public String getFirmName() {
		return this.firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getFirmRegistrationNumber() {
		return this.firmRegistrationNumber;
	}

	public void setFirmRegistrationNumber(String firmRegistrationNumber) {
		this.firmRegistrationNumber = firmRegistrationNumber;
	}

	public String getFirmType() {
		return this.firmType;
	}

	public void setFirmType(String firmType) {
		this.firmType = firmType;
	}

	public String getNatureOfBusiness() {
		return this.natureOfBusiness;
	}

	public void setNatureOfBusiness(String natureOfBusiness) {
		this.natureOfBusiness = natureOfBusiness;
	}

	public String getNotesAny() {
		return this.notesAny;
	}

	public void setNotesAny(String notesAny) {
		this.notesAny = notesAny;
	}

	public String getOtherFirmType() {
		return this.otherFirmType;
	}

	public void setOtherFirmType(String otherFirmType) {
		this.otherFirmType = otherFirmType;
	}

	public String getPeresonIncharge() {
		return this.peresonIncharge;
	}

	public void setPeresonIncharge(String peresonIncharge) {
		this.peresonIncharge = peresonIncharge;
	}

	public String getPermenantaddress() {
		return this.permenantaddress;
	}

	public void setPermenantaddress(String permenantaddress) {
		this.permenantaddress = permenantaddress;
	}

	public String getPermenantcity() {
		return this.permenantcity;
	}

	public void setPermenantcity(String permenantcity) {
		this.permenantcity = permenantcity;
	}

	public String getPermenantcountry() {
		return this.permenantcountry;
	}

	public void setPermenantcountry(String permenantcountry) {
		this.permenantcountry = permenantcountry;
	}

	public String getPermenantpostalcode() {
		return this.permenantpostalcode;
	}

	public void setPermenantpostalcode(String permenantpostalcode) {
		this.permenantpostalcode = permenantpostalcode;
	}

	public String getPermenantstate() {
		return this.permenantstate;
	}

	public void setPermenantstate(String permenantstate) {
		this.permenantstate = permenantstate;
	}

	public String getPhonenum1() {
		return this.phonenum1;
	}

	public void setPhonenum1(String phonenum1) {
		this.phonenum1 = phonenum1;
	}

	public String getPhonenum2() {
		return this.phonenum2;
	}

	public void setPhonenum2(String phonenum2) {
		this.phonenum2 = phonenum2;
	}

	public String getPresentcity() {
		return this.presentcity;
	}

	public void setPresentcity(String presentcity) {
		this.presentcity = presentcity;
	}

	public String getPresentcountry() {
		return this.presentcountry;
	}

	public void setPresentcountry(String presentcountry) {
		this.presentcountry = presentcountry;
	}

	public String getPresentpostalcode() {
		return this.presentpostalcode;
	}

	public void setPresentpostalcode(String presentpostalcode) {
		this.presentpostalcode = presentpostalcode;
	}

	public String getPresentstate() {
		return this.presentstate;
	}

	public void setPresentstate(String presentstate) {
		this.presentstate = presentstate;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
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

	public String getUploadfile() {
		return this.uploadfile;
	}

	public void setUploadfile(String uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getVendorPhoneNumber() {
		return this.vendorPhoneNumber;
	}

	public void setVendorPhoneNumber(String vendorPhoneNumber) {
		this.vendorPhoneNumber = vendorPhoneNumber;
	}

	public String getVendorTelephoneNumber() {
		return this.vendorTelephoneNumber;
	}

	public void setVendorTelephoneNumber(String vendorTelephoneNumber) {
		this.vendorTelephoneNumber = vendorTelephoneNumber;
	}

	public String getWebsiteAny() {
		return this.websiteAny;
	}

	public void setWebsiteAny(String websiteAny) {
		this.websiteAny = websiteAny;
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

}