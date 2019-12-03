package com.inacsys.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Commission {
	public String productname;
	public String rollids;
	public String customername;
	public String category;
	public Date fromdate;
	public Date todate;
	public List<String> list;
	public String invoiceno;
	public String orderdate;
	public String cname;
	BigDecimal totlamt = BigDecimal.valueOf(0);
	BigDecimal totlquan = BigDecimal.valueOf(0);
	BigDecimal cmst = BigDecimal.valueOf(0);
	BigDecimal totcms = BigDecimal.valueOf(0);
	BigDecimal rolquan = BigDecimal.valueOf(0);

	BigDecimal totlsales = BigDecimal.valueOf(0);
	BigDecimal totalamount = BigDecimal.valueOf(0);

	BigDecimal totlsales1 = BigDecimal.valueOf(0);
	BigDecimal totalamount1 = BigDecimal.valueOf(0);
	BigDecimal unitprice = BigDecimal.valueOf(0);

	public BigDecimal getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public BigDecimal getTotlsales1() {
		return totlsales1;
	}

	public void setTotlsales1(BigDecimal totlsales1) {
		this.totlsales1 = totlsales1;
	}

	public BigDecimal getTotalamount1() {
		return totalamount1;
	}

	public void setTotalamount1(BigDecimal totalamount1) {
		this.totalamount1 = totalamount1;
	}

	public BigDecimal getTotlsales() {
		return totlsales;
	}

	public void setTotlsales(BigDecimal totlsales) {
		this.totlsales = totlsales;
	}

	public BigDecimal getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(BigDecimal totalamount) {
		this.totalamount = totalamount;
	}

	public BigDecimal getRolquan() {
		return rolquan;
	}

	public void setRolquan(BigDecimal rolquan) {
		this.rolquan = rolquan;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getRollids() {
		return rollids;
	}

	public void setRollids(String rollids) {
		this.rollids = rollids;
	}

	/*
	 * public BigDecimal getRollids() { return rollids; } public void
	 * setRollids(BigDecimal rollids) { this.rollids = rollids; }
	 */
	public BigDecimal getTotlamt() {
		return totlamt;
	}

	public void setTotlamt(BigDecimal totlamt) {
		this.totlamt = totlamt;
	}

	public BigDecimal getTotlquan() {
		return totlquan;
	}

	public void setTotlquan(BigDecimal totlquan) {
		this.totlquan = totlquan;
	}

	public BigDecimal getCmst() {
		return cmst;
	}

	public void setCmst(BigDecimal cmst) {
		this.cmst = cmst;
	}

	public BigDecimal getTotcms() {
		return totcms;
	}

	public void setTotcms(BigDecimal totcms) {
		this.totcms = totcms;
	}

	List<Commission> totallist = new ArrayList<Commission>();
	public String totalSaleAmount;
	public String quantity;
	public String commisionAmount;
	public String totalCommision;
	public String flag;
	public String flag1;
	public String sNo;

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public String getTotalSaleAmount() {
		return totalSaleAmount;
	}

	public void setTotalSaleAmount(String totalSaleAmount) {
		this.totalSaleAmount = totalSaleAmount;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getCommisionAmount() {
		return commisionAmount;
	}

	public void setCommisionAmount(String commisionAmount) {
		this.commisionAmount = commisionAmount;
	}

	public String getTotalCommision() {
		return totalCommision;
	}

	public void setTotalCommision(String totalCommision) {
		this.totalCommision = totalCommision;
	}

	public List<Commission> getTotallist() {
		return totallist;
	}

	public void setTotallist(List<Commission> totallist) {
		this.totallist = totallist;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

}
