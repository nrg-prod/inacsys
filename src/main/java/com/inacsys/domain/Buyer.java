package com.inacsys.domain;

/*import java.sql.Date;*/
import java.util.*;

import org.primefaces.model.UploadedFile;

import com.inacsys.shared.I0032;

public class Buyer {

	public String customerName;
	public Date date;
	public String address;
	public String city;
	public String state;
	public String country;
	public String phoneNumber;
	public String mail;
	public String note;
	public Date deliveryDate;
	public String previouePhone;
	public List<I0032> phoneInfo;
	ArrayList<I0032> saleDetail = null;
	public String categoryName;
	public String freelancerName;
	public String presentcountryID;
	public String customerTitle;
	public String customerMiddleName;
	public String customerLastName;
	public String customerSuffix;
	public String custMobile;
	public String other;
	public String company;
	public String website;
	public String faxnumber;
	public String displayName;
	public String permanentaddress;
	public String presentCity;
	public String perPostCode;
	public String prePostCode;
	public String presentstate;
	private UploadedFile attachmentFile;
	private UploadedFile photoUploadFile;
	public String code;
	public String filePath;
	public String attachFilePath;
	public String cities;
	public String phoneno;
	public String perpostcode;
	public String perstate;
	public String photouploadfile;
	//Business partner
	public String cusLicence;
	public String cusType;
	public Date cusExdate;
	public String cusCode;
	public String payment;
	public String type;
	public String cash;
	public String newcash;
	public int crm_customerDetails_ID;	
	public String approval;
	// CRM Changes 
	public String email2;
	public String crmcomments;
	public String crmwebsite2;
	public String crmfaxnumber2;
	public String crmstatus;
	public String crmcode;
	public Date createddate;
	public Date updateddate;
	public Date followupdate;
	public String crmcusproduct;
	public String crmmodeofcommunications;
	public String crmcreatedperson;
	public String crmupdatedperson;
	public String crmcash;
	
	private String clientID;
	private int buyerID;
	private String userID;
	private String userType;
	private String status;
	private int paymentID;

	
	public String partnerShipName;
	public String partnerFnamerelation;
	public Date pnDOB;
	public String pnEmailID;
	public String pnPhoneNumber1;
	public String pnPhoneNumber2;
	public String pnAddress;
	public Date dateofjoin;
	
	
	public Date getDateofjoin() {
		return dateofjoin;
	}

	public void setDateofjoin(Date dateofjoin) {
		this.dateofjoin = dateofjoin;
	}

	public String getPartnerShipName() {
		return partnerShipName;
	}

	public void setPartnerShipName(String partnerShipName) {
		this.partnerShipName = partnerShipName;
	}

	public String getPartnerFnamerelation() {
		return partnerFnamerelation;
	}

	public void setPartnerFnamerelation(String partnerFnamerelation) {
		this.partnerFnamerelation = partnerFnamerelation;
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

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public int getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(int buyerID) {
		this.buyerID = buyerID;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public int getCrm_customerDetails_ID() {
		return crm_customerDetails_ID;
	}

	public void setCrm_customerDetails_ID(int crm_customerDetails_ID) {
		this.crm_customerDetails_ID = crm_customerDetails_ID;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public String getNewcash() {
		return newcash;
	}

	public void setNewcash(String newcash) {
		this.newcash = newcash;
	}

	public String getCusLicence() {
		return cusLicence;
	}

	public void setCusLicence(String cusLicence) {
		this.cusLicence = cusLicence;
	}

	public String getCusType() {
		return cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public Date getCusExdate() {
		return cusExdate;
	}

	public void setCusExdate(Date cusExdate) {
		this.cusExdate = cusExdate;
	}

	public String getCusCode() {
		return cusCode;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}

	public String getPhotouploadfile() {
		return photouploadfile;
	}

	public void setPhotouploadfile(String photouploadfile) {
		this.photouploadfile = photouploadfile;
	}

	public String getPerstate() {
		return perstate;
	}

	public void setPerstate(String perstate) {
		this.perstate = perstate;
	}

	public String getPerpostcode() {
		return perpostcode;
	}

	public void setPerpostcode(String perpostcode) {
		this.perpostcode = perpostcode;
	}

	public String getPresentcountry() {
		return presentcountry;
	}

	public void setPresentcountry(String presentcountry) {
		this.presentcountry = presentcountry;
	}

	public String presentcountry;
	public String getCities() {
		return cities;
	}

	public void setCities(String cities) {
		this.cities = cities;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getAttachFilePath() {
		return attachFilePath;
	}

	public void setAttachFilePath(String attachFilePath) {
		this.attachFilePath = attachFilePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public UploadedFile getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(UploadedFile attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public UploadedFile getPhotoUploadFile() {
		return photoUploadFile;
	}

	public void setPhotoUploadFile(UploadedFile photoUploadFile) {
		this.photoUploadFile = photoUploadFile;
	}

	public String getPresentcountryID() {
		return presentcountryID;
	}

	public void setPresentcountryID(String presentcountryID) {
		this.presentcountryID = presentcountryID;
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

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
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

	public String getFaxnumber() {
		return faxnumber;
	}

	public void setFaxnumber(String faxnumber) {
		this.faxnumber = faxnumber;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPermanentaddress() {
		return permanentaddress;
	}

	public void setPermanentaddress(String permanentaddress) {
		this.permanentaddress = permanentaddress;
	}

	public String getPresentCity() {
		return presentCity;
	}

	public void setPresentCity(String presentCity) {
		this.presentCity = presentCity;
	}

	public String getPerPostCode() {
		return perPostCode;
	}

	public void setPerPostCode(String perPostCode) {
		this.perPostCode = perPostCode;
	}

	public String getPrePostCode() {
		return prePostCode;
	}

	public void setPrePostCode(String prePostCode) {
		this.prePostCode = prePostCode;
	}

	public String getPresentstate() {
		return presentstate;
	}

	public void setPresentstate(String presentstate) {
		this.presentstate = presentstate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getFreelancerName() {
		return freelancerName;
	}

	public void setFreelancerName(String freelancerName) {
		this.freelancerName = freelancerName;
	}

	public ArrayList<I0032> getSaleDetail() {
		return saleDetail;
	}

	public void setSaleDetail(ArrayList<I0032> saleDetail) {
		this.saleDetail = saleDetail;
	}

	public List<I0032> getPhoneInfo() {
		return phoneInfo;
	}

	public void setPhoneInfo(List<I0032> phoneInfo) {
		this.phoneInfo = phoneInfo;
	}

	public String getPreviouePhone() {
		return previouePhone;
	}

	public void setPreviouePhone(String previouePhone) {
		this.previouePhone = previouePhone;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/* jeni */
	public String taxnumber;

	public String getTaxnumber() {
		return taxnumber;
	}

	public void setTaxnumber(String taxnumber) {
		this.taxnumber = taxnumber;
	}

	public String flag1 = "none";
	public List<I0032> customerInfo;
	public List<I0032> cityinfo;

	public List<I0032> getCityinfo() {
		return cityinfo;
	}

	public void setCityinfo(List<I0032> cityinfo) {
		this.cityinfo = cityinfo;
	}

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public List<I0032> getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(List<I0032> customerInfo) {
		this.customerInfo = customerInfo;
	}
	
	
	

	public String getCrmcash() {
		return crmcash;
	}

	public void setCrmcash(String crmcash) {
		this.crmcash = crmcash;
	}


	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getCrmcomments() {
		return crmcomments;
	}

	public void setCrmcomments(String crmcomments) {
		this.crmcomments = crmcomments;
	}

	public String getCrmwebsite2() {
		return crmwebsite2;
	}

	public void setCrmwebsite2(String crmwebsite2) {
		this.crmwebsite2 = crmwebsite2;
	}

	public String getCrmfaxnumber2() {
		return crmfaxnumber2;
	}

	public void setCrmfaxnumber2(String crmfaxnumber2) {
		this.crmfaxnumber2 = crmfaxnumber2;
	}

	public String getCrmstatus() {
		return crmstatus;
	}

	public void setCrmstatus(String crmstatus) {
		this.crmstatus = crmstatus;
	}

	public String getCrmcode() {
		return crmcode;
	}

	public void setCrmcode(String crmcode) {
		this.crmcode = crmcode;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

	public Date getFollowupdate() {
		return followupdate;
	}

	public void setFollowupdate(Date followupdate) {
		this.followupdate = followupdate;
	}

	public String getCrmcusproduct() {
		return crmcusproduct;
	}

	public void setCrmcusproduct(String crmcusproduct) {
		this.crmcusproduct = crmcusproduct;
	}

	public String getCrmmodeofcommunications() {
		return crmmodeofcommunications;
	}

	public void setCrmmodeofcommunications(String crmmodeofcommunications) {
		this.crmmodeofcommunications = crmmodeofcommunications;
	}

	public String getCrmcreatedperson() {
		return crmcreatedperson;
	}

	public void setCrmcreatedperson(String crmcreatedperson) {
		this.crmcreatedperson = crmcreatedperson;
	}

	public String getCrmupdatedperson() {
		return crmupdatedperson;
	}

	public void setCrmupdatedperson(String crmupdatedperson) {
		this.crmupdatedperson = crmupdatedperson;
	}

}
