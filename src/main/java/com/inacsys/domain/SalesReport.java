package com.inacsys.domain;

import java.util.Date;

public class SalesReport {
	public Date salesFromDate;
	public Date salesToDate;
	public String customerName;
	public String ALLcustomerName;
	public String orderdate;
	public String orderNumber;
	public String quantity;
	public String phonenumber;
	public String sellprice;
	public String productName;
	public String totPrice;
	public String status;
	public String paymentstatus;
	public String deliverystatus;
	public String returnstatus;
	public String normalreturn;
	public String damagereturn;
	public String returndate;

	public String getReturndate() {
		return returndate;
	}

	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}

	public String getNormalreturn() {
		return normalreturn;
	}

	public void setNormalreturn(String normalreturn) {
		this.normalreturn = normalreturn;
	}

	public String getDamagereturn() {
		return damagereturn;
	}

	public void setDamagereturn(String damagereturn) {
		this.damagereturn = damagereturn;
	}

	public String getDeliverystatus() {
		return deliverystatus;
	}

	public void setDeliverystatus(String deliverystatus) {
		this.deliverystatus = deliverystatus;
	}

	public String totalamount;

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTotPrice() {
		return totPrice;
	}

	public void setTotPrice(String totPrice) {
		this.totPrice = totPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public String getReturnstatus() {
		return returnstatus;
	}

	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}

	public String getSellprice() {
		return sellprice;
	}

	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Date getSalesFromDate() {
		return salesFromDate;
	}

	public void setSalesFromDate(Date salesFromDate) {
		this.salesFromDate = salesFromDate;
	}

	public Date getSalesToDate() {
		return salesToDate;
	}

	public void setSalesToDate(Date salesToDate) {
		this.salesToDate = salesToDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getALLcustomerName() {
		return ALLcustomerName;
	}

	public void setALLcustomerName(String aLLcustomerName) {
		ALLcustomerName = aLLcustomerName;
	}

}
