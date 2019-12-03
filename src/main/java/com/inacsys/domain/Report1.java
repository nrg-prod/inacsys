package com.inacsys.domain;

import java.util.*;

import com.inacsys.shared.I0022;

public class Report1 {
	ArrayList<String> strin = null;
	public Date fromDate;
	public Date toDate;
	public String category;
	public String firmName;
	public String totalAmount;
	public String orderNumber;
	public String Date;
	public String cuscategory;
	public Date orderdate;
	public Date reportdate;
	public String customername;
	public String businesspartner;
	List<Report1> droppedrtype=new ArrayList<Report1>();
	ArrayList<Report1> report2 = new ArrayList<Report1>();
	public String price;
	public String returnQuantity;
	public String status;
	List<Report1> vendorlist=new ArrayList<Report1>();
	public String vendorName;
	private String reportpage;
	private String  quarterlyreporttype;
	private String year;
	private String month;
	private String  halfyearlyreporttype;
	public Date transdate;
	private String frommonth;
	private String tomonth;
	private String week;
	List<String> week1=new ArrayList<String>();
	private String[] types;
	
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	public List<String> getWeek1() {
		return week1;
	}
	public void setWeek1(List<String> week1) {
		this.week1 = week1;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}

	public String getFrommonth() {
		return frommonth;
	}

	public void setFrommonth(String frommonth) {
		this.frommonth = frommonth;
	}

	public String getTomonth() {
		return tomonth;
	}

	public void setTomonth(String tomonth) {
		this.tomonth = tomonth;
	}

	public Date getTransdate() {
		return transdate;
	}

	public void setTransdate(Date transdate) {
		this.transdate = transdate;
	}

	public String getHalfyearlyreporttype() {
		return halfyearlyreporttype;
	}

	public void setHalfyearlyreporttype(String halfyearlyreporttype) {
		this.halfyearlyreporttype = halfyearlyreporttype;
	}
	public String getQuarterlyreporttype() {
		return quarterlyreporttype;
	}

	public void setQuarterlyreporttype(String quarterlyreporttype) {
		this.quarterlyreporttype = quarterlyreporttype;
	}
	
	
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getReportpage() {
		return reportpage;
	}

	public void setReportpage(String reportpage) {
		this.reportpage = reportpage;
	}

	

	public List<Report1> getVendorlist() {
		return vendorlist;
	}

	public void setVendorlist(List<Report1> vendorlist) {
		this.vendorlist = vendorlist;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getReturnQuantity() {
		return returnQuantity;
	}

	public void setReturnQuantity(String returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public String getBusinesspartner() {
		return businesspartner;
	}

	public void setBusinesspartner(String businesspartner) {
		this.businesspartner = businesspartner;
	}

	public Date getReportdate() {
		return reportdate;
	}

	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}

	public List<Report1> getDroppedrtype() {
		return droppedrtype;
	}

	public void setDroppedrtype(List<Report1> droppedrtype) {
		this.droppedrtype = droppedrtype;
	}

	public ArrayList<Report1> getReport2() {
		return report2;
	}

	public void setReport2(ArrayList<Report1> report2) {
		this.report2 = report2;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public String getCuscategory() {
		return cuscategory;
	}

	public void setCuscategory(String cuscategory) {
		this.cuscategory = cuscategory;
	}

	ArrayList<Report1> report = new ArrayList<Report1>();

	public ArrayList<Report1> getReport() {
		return report;
	}

	public void setReport(ArrayList<Report1> report) {
		this.report = report;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	ArrayList<I0022> invoiceList = null;
	public String total;

	public ArrayList<String> getStrin() {
		return strin;
	}

	public void setStrin(ArrayList<String> strin) {
		this.strin = strin;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ArrayList<I0022> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(ArrayList<I0022> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	

}
