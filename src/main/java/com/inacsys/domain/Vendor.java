package com.inacsys.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inacsys.shared.I0025;
import com.inacsys.shared.I0028;

public class Vendor extends Common implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String firmName;
	public String firmRegistrationNumber;
	public String address;
	public String vendorTelephoneNumber;
	public String vendorPhoneNumber;
	public String country_ID;
	public String country;
	public String approval;
	
	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String state;
	public String city;
	public String email_ID_vendor;
	public String faxVendor;
	public String peresonIncharge;
	public String nature_of_business_id;
	public String firmTypeStandard;
	public String frim_ID;
	public int vendor_Id;
	public String otherfirmtype;
	public String natureofbusiness;
	public String website;
	public String note;
	public ArrayList<I0028> countrydrop1;
	public String cities;
	
	/* Stanley */
	public String venCompany;
	public String venCity;
	public String venCountry;
	public String venPostalcode;
	public String venState;
	public String venAddress1;
	public String venCity1;
	public String venCountry1;
	public String venPostalcode1;
	public String venState1;
	
	public String venLicence;
	public String venType;
	public Date venExdate; 
	public String venCode;
	public String payment;
	public String cash;
	public String newcash;
	public String payType;
	public String payDays;
	public String productName;
	public String productPrice;
	
	private List<String> stringList=new ArrayList<String>();
	private String status;
	private String clientID;
	private String userID;
	private String userType;

	public List<String> getStringList() {
		return stringList;
	}

	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayDays() {
		return payDays;
	}

	public void setPayDays(String payDays) {
		this.payDays = payDays;
	}

	public String getNewcash() {
		return newcash;
	}

	public void setNewcash(String newcash) {
		this.newcash = newcash;
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

	public String getVenLicence() {
		return venLicence;
	}

	public void setVenLicence(String venLicence) {
		this.venLicence = venLicence;
	}

	public String getVenType() {
		return venType;
	}

	public void setVenType(String venType) {
		this.venType = venType;
	}

	public Date getVenExdate() {
		return venExdate;
	}

	public void setVenExdate(Date venExdate) {
		this.venExdate = venExdate;
	}

	public String getVenCode() {
		return venCode;
	}

	public void setVenCode(String venCode) {
		this.venCode = venCode;
	}

	public String getVenCompany() {
		return venCompany;
	}

	public void setVenCompany(String venCompany) {
		this.venCompany = venCompany;
	}

	public String getVenCity() {
		return venCity;
	}

	public void setVenCity(String venCity) {
		this.venCity = venCity;
	}

	public String getVenCountry() {
		return venCountry;
	}

	public void setVenCountry(String venCountry) {
		this.venCountry = venCountry;
	}

	public String getVenPostalcode() {
		return venPostalcode;
	}

	public void setVenPostalcode(String venPostalcode) {
		this.venPostalcode = venPostalcode;
	}

	public String getVenState() {
		return venState;
	}

	public void setVenState(String venState) {
		this.venState = venState;
	}

	public String getVenAddress1() {
		return venAddress1;
	}

	public void setVenAddress1(String venAddress1) {
		this.venAddress1 = venAddress1;
	}

	public String getVenCity1() {
		return venCity1;
	}

	public void setVenCity1(String venCity1) {
		this.venCity1 = venCity1;
	}

	public String getVenCountry1() {
		return venCountry1;
	}

	public void setVenCountry1(String venCountry1) {
		this.venCountry1 = venCountry1;
	}

	public String getVenPostalcode1() {
		return venPostalcode1;
	}

	public void setVenPostalcode1(String venPostalcode1) {
		this.venPostalcode1 = venPostalcode1;
	}

	public String getVenState1() {
		return venState1;
	}

	public void setVenState1(String venState1) {
		this.venState1 = venState1;
	}

	public String getCities() {
		return cities;
	}

	public void setCities(String cities) {
		this.cities = cities;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ArrayList<I0028> getCountrydrop1() {
		return countrydrop1;
	}

	public void setCountrydrop1(ArrayList<I0028> countrydrop1) {
		this.countrydrop1 = countrydrop1;
	}

	public String getOtherfirmtype() {
		return otherfirmtype;
	}

	public void setOtherfirmtype(String otherfirmtype) {
		this.otherfirmtype = otherfirmtype;
	}

	public String getNatureofbusiness() {
		return natureofbusiness;
	}

	public void setNatureofbusiness(String natureofbusiness) {
		this.natureofbusiness = natureofbusiness;
	}

	public ArrayList<I0025> vendorlist = null;

	public ArrayList<I0025> getVendorlist() {
		return vendorlist;
	}

	public void setVendorlist(ArrayList<I0025> vendorlist) {
		this.vendorlist = vendorlist;
	}

	/*
	 * String s;
	 * 
	 * public String getS() { return s; } public void setS(String s) { this.s =
	 * s; }
	 */
	public String getFrim_ID() {
		return frim_ID;
	}

	public int getVendor_Id() {
		return vendor_Id;
	}

	public void setVendor_Id(int vendor_Id) {
		this.vendor_Id = vendor_Id;
	}

	public void setFrim_ID(String frim_ID) {
		this.frim_ID = frim_ID;
	}

	public String getNature_of_business_id() {
		return nature_of_business_id;
	}

	public void setNature_of_business_id(String nature_of_business_id) {
		this.nature_of_business_id = nature_of_business_id;
	}

	public String getVendorTelephoneNumber() {
		return vendorTelephoneNumber;
	}

	public void setVendorTelephoneNumber(String vendorTelephoneNumber) {
		this.vendorTelephoneNumber = vendorTelephoneNumber;
	}

	public String getEmail_ID_vendor() {
		return email_ID_vendor;
	}

	public void setEmail_ID_vendor(String email_ID_vendor) {
		this.email_ID_vendor = email_ID_vendor;
	}

	public String getFaxVendor() {
		return faxVendor;
	}

	public void setFaxVendor(String faxVendor) {
		this.faxVendor = faxVendor;
	}

	public String getPeresonIncharge() {
		return peresonIncharge;
	}

	public void setPeresonIncharge(String peresonIncharge) {
		this.peresonIncharge = peresonIncharge;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getFirmRegistrationNumber() {
		return firmRegistrationNumber;
	}

	public void setFirmRegistrationNumber(String firmRegistrationNumber) {
		this.firmRegistrationNumber = firmRegistrationNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVendorPhoneNumber() {
		return vendorPhoneNumber;
	}

	public void setVendorPhoneNumber(String vendorPhoneNumber) {
		this.vendorPhoneNumber = vendorPhoneNumber;
	}

	public String getCountry_ID() {
		return country_ID;
	}

	public void setCountry_ID(String country_ID) {
		this.country_ID = country_ID;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirmTypeStandard() {
		return firmTypeStandard;
	}

	public void setFirmTypeStandard(String firmTypeStandard) {
		this.firmTypeStandard = firmTypeStandard;
	}

}
