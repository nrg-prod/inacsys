package com.inacsys.domain;

import java.util.Date;

public class VendorReport {

	public String vendorReportName;
	public String allrepVendorName;
	public String vendorRepName;
	public String vendorphonenumber;
	public String prize;
	public String purchaseOrderNO;
	public String purchaseStatus;
	public Date vendorFromDate;
	public Date vendorToDate;
	public String allvendorphNo;
	public String totalprice;
	public String totquantity;
	private boolean norecordFlag = false;
	public String detailVendorName;
	public String detailPONumber;
	public String detailProduct;
	public String detailQuan;
	public String sellingPrice;
	public String netAmount;

	public String getDetailVendorName() {
		return detailVendorName;
	}

	public void setDetailVendorName(String detailVendorName) {
		this.detailVendorName = detailVendorName;
	}

	public String getDetailPONumber() {
		return detailPONumber;
	}

	public void setDetailPONumber(String detailPONumber) {
		this.detailPONumber = detailPONumber;
	}

	public String getDetailProduct() {
		return detailProduct;
	}

	public void setDetailProduct(String detailProduct) {
		this.detailProduct = detailProduct;
	}

	public String getDetailQuan() {
		return detailQuan;
	}

	public void setDetailQuan(String detailQuan) {
		this.detailQuan = detailQuan;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public String getTotquantity() {
		return totquantity;
	}

	public void setTotquantity(String totquantity) {
		this.totquantity = totquantity;
	}

	public boolean isNorecordFlag() {
		return norecordFlag;
	}

	public void setNorecordFlag(boolean norecordFlag) {
		this.norecordFlag = norecordFlag;
	}

	public String getAllvendorphNo() {
		return allvendorphNo;
	}

	public void setAllvendorphNo(String allvendorphNo) {
		this.allvendorphNo = allvendorphNo;
	}

	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}

	public String getAllrepVendorName() {
		return allrepVendorName;
	}

	public void setAllrepVendorName(String allrepVendorName) {
		this.allrepVendorName = allrepVendorName;
	}

	public String getVendorRepName() {
		return vendorRepName;
	}

	public void setVendorRepName(String vendorRepName) {
		this.vendorRepName = vendorRepName;
	}

	public Date getVendorFromDate() {
		return vendorFromDate;
	}

	public void setVendorFromDate(Date vendorFromDate) {
		this.vendorFromDate = vendorFromDate;
	}

	public Date getVendorToDate() {
		return vendorToDate;
	}

	public void setVendorToDate(Date vendorToDate) {
		this.vendorToDate = vendorToDate;
	}

	public String getVendorphonenumber() {
		return vendorphonenumber;
	}

	public void setVendorphonenumber(String vendorphonenumber) {
		this.vendorphonenumber = vendorphonenumber;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}

	public String getPurchaseOrderNO() {
		return purchaseOrderNO;
	}

	public void setPurchaseOrderNO(String purchaseOrderNO) {
		this.purchaseOrderNO = purchaseOrderNO;
	}

	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public String getVendorReportName() {
		return vendorReportName;
	}

	public void setVendorReportName(String vendorReportName) {
		this.vendorReportName = vendorReportName;
	}

}
