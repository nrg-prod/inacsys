package com.inacsys.domain;

import java.util.*;

import com.inacsys.managedBean.ApprovalViewMB;
import com.inacsys.shared.I0015;
import com.inacsys.shared.I0033;

public class Approval {
	public Date fromDate;
	public Date toDate;
	ArrayList<String> finallist = null;
	public String orderNumber;
	public String purchaseNumber;
	public int apporvalnumber;
	public ArrayList<I0033> filelist = null;
	int resullt1 = 0;

	public int getResullt1() {
		return resullt1;
	}

	public void setResullt1(int resullt1) {
		this.resullt1 = resullt1;
	}

	public int getApporvalnumber() {
		return apporvalnumber;
	}

	public void setApporvalnumber(int apporvalnumber) {
		this.apporvalnumber = apporvalnumber;
	}

	public ArrayList<I0033> getFilelist() {
		return filelist;
	}

	public void setFilelist(ArrayList<I0033> filelist) {
		this.filelist = filelist;
	}

	public String getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public ArrayList<ApprovalViewMB> purchaselist = new ArrayList<ApprovalViewMB>();

	public ArrayList<ApprovalViewMB> getPurchaselist() {
		return purchaselist;
	}

	public void setPurchaselist(ArrayList<ApprovalViewMB> purchaselist) {
		this.purchaselist = purchaselist;
	}

	public ArrayList<String> getFinallist() {
		return finallist;
	}

	public void setFinallist(ArrayList<String> finallist) {
		this.finallist = finallist;
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
}
