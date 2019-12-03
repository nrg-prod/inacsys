package com.inacsys.domain;

import java.util.Date;
import java.util.List;

public class ReportDatabean {
	
	private Date fromDate;
	private Date toDate;
	private Date orderDate;
	private String type;
	private String name;
	private String allName;
	private String clientID;
	private String normalReturn;
	private String damageReturn;
	private String userID;
	private String userType;
	private String phoneNumber;
	private String orderNumber;
	private String productName;
	private String quantity;
	private String price;
	private String crossTotal;
	private String status;
	private String deliveryStatus;
	private String paymentStatus;
	private String totalPrice;
	private String totalAmount;
	private String shippingCost;
	private String stockinQuantity;
	private String stockoutQuantity;
	private String stockdamageQuantity;
	private List<ReportDatabean> reportList=null;
	private List<ReportDatabean> reportLists=null;
	private List<ReportDatabean> detailReportLists=null;
	private List<String> ordernumberList=null;
	
	
	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getNormalReturn() {
		return normalReturn;
	}

	public void setNormalReturn(String normalReturn) {
		this.normalReturn = normalReturn;
	}

	public String getDamageReturn() {
		return damageReturn;
	}

	public void setDamageReturn(String damageReturn) {
		this.damageReturn = damageReturn;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getStockinQuantity() {
		return stockinQuantity;
	}

	public void setStockinQuantity(String stockinQuantity) {
		this.stockinQuantity = stockinQuantity;
	}

	public String getStockoutQuantity() {
		return stockoutQuantity;
	}

	public void setStockoutQuantity(String stockoutQuantity) {
		this.stockoutQuantity = stockoutQuantity;
	}

	public String getStockdamageQuantity() {
		return stockdamageQuantity;
	}

	public void setStockdamageQuantity(String stockdamageQuantity) {
		this.stockdamageQuantity = stockdamageQuantity;
	}

	public String getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(String shippingCost) {
		this.shippingCost = shippingCost;
	}

	public String getCrossTotal() {
		return crossTotal;
	}

	public void setCrossTotal(String crossTotal) {
		this.crossTotal = crossTotal;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<String> getOrdernumberList() {
		return ordernumberList;
	}

	public void setOrdernumberList(List<String> ordernumberList) {
		this.ordernumberList = ordernumberList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ReportDatabean> getReportList() {
		return reportList;
	}

	public void setReportList(List<ReportDatabean> reportList) {
		this.reportList = reportList;
	}

	public List<ReportDatabean> getReportLists() {
		return reportLists;
	}

	public void setReportLists(List<ReportDatabean> reportLists) {
		this.reportLists = reportLists;
	}

	public List<ReportDatabean> getDetailReportLists() {
		return detailReportLists;
	}

	public void setDetailReportLists(List<ReportDatabean> detailReportLists) {
		this.detailReportLists = detailReportLists;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAllName() {
		return allName;
	}

	public void setAllName(String allName) {
		this.allName = allName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
}
