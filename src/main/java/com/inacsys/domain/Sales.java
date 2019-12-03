package com.inacsys.domain;

import java.math.BigDecimal;
import java.util.*;

import com.inacsys.managedBean.ApprovalViewMB;
import com.inacsys.shared.I0015;
import com.inacsys.shared.I0033;

public class Sales {
	public Date salesorderDate;
	public String salesOrderNumber;
	public String customerName;
	public String phoneNumber;
	public String productName;
	public String returnItems;
	public String normalReturnItems;
	public String damageReturnItems;
public String customername1;
	public String businessname;
	public String mobile;
	public String permanentaddress;
	public String email;
	public String unit;
	public String quotationcode;
	public String hidden;
	int QuoteID;
	
	//stanley for revenue
	  public String programmeName;
	  public String schedules;
	  public String billedAmount;
	  public String billedAmountPercentage;
	  public String totalpayable;
	  public Date salesorderdate;
	  public String totalAmount;
	  public String clientID;
	  public int id;

	public String getProgrammeName() {
		return programmeName;
	}

	public void setProgrammeName(String programmeName) {
		this.programmeName = programmeName;
	}

	public String getSchedules() {
		return schedules;
	}

	public void setSchedules(String schedules) {
		this.schedules = schedules;
	}

	public String getBilledAmount() {
		return billedAmount;
	}

	public void setBilledAmount(String billedAmount) {
		this.billedAmount = billedAmount;
	}

	public String getBilledAmountPercentage() {
		return billedAmountPercentage;
	}

	public void setBilledAmountPercentage(String billedAmountPercentage) {
		this.billedAmountPercentage = billedAmountPercentage;
	}

	public String getTotalpayable() {
		return totalpayable;
	}

	public void setTotalpayable(String totalpayable) {
		this.totalpayable = totalpayable;
	}

	public Date getSalesorderdate() {
		return salesorderdate;
	}

	public void setSalesorderdate(Date salesorderdate) {
		this.salesorderdate = salesorderdate;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuoteID() {
		return QuoteID;
	}

	public void setQuoteID(int quoteID) {
		QuoteID = quoteID;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getQuotationcode() {
		return quotationcode;
	}

	public void setQuotationcode(String quotationcode) {
		this.quotationcode = quotationcode;
	}
	
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPermanentaddress() {
		return permanentaddress;
	}

	public void setPermanentaddress(String permanentaddress) {
		this.permanentaddress = permanentaddress;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBusinessname() {
		return businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public String getCustomername1() {
		return customername1;
	}

	public void setCustomername1(String customername1) {
		this.customername1 = customername1;
	}
	public Date getSalesorderDate() {
		return salesorderDate;
	}

	public void setSalesorderDate(Date salesorderDate) {
		this.salesorderDate = salesorderDate;
	}

	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getReturnItems() {
		return returnItems;
	}

	public void setReturnItems(String returnItems) {
		this.returnItems = returnItems;
	}

	public String getNormalReturnItems() {
		return normalReturnItems;
	}

	public void setNormalReturnItems(String normalReturnItems) {
		this.normalReturnItems = normalReturnItems;
	}

	public String getDamageReturnItems() {
		return damageReturnItems;
	}

	public void setDamageReturnItems(String damageReturnItems) {
		this.damageReturnItems = damageReturnItems;
	}

	BigDecimal a = BigDecimal.valueOf(0);
	BigDecimal a1 = BigDecimal.valueOf(0);
	BigDecimal a2 = BigDecimal.valueOf(0);
	BigDecimal a3 = BigDecimal.valueOf(0);

	public BigDecimal getA() {
		return a;
	}

	public void setA(BigDecimal a) {
		this.a = a;
	}

	public BigDecimal getA1() {
		return a1;
	}

	public void setA1(BigDecimal a1) {
		this.a1 = a1;
	}

	public BigDecimal getA2() {
		return a2;
	}

	public void setA2(BigDecimal a2) {
		this.a2 = a2;
	}

	public BigDecimal getA3() {
		return a3;
	}

	public void setA3(BigDecimal a3) {
		this.a3 = a3;
	}

}
