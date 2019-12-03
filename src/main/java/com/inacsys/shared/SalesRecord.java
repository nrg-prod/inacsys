package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the sales_record database table.
 * 
 */
@Entity
@Table(name = "sales_record")
@NamedQuery(name = "SalesRecord.findAll", query = "SELECT s FROM SalesRecord s")
public class SalesRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "sales_record_id")
	private int salesRecordId;

	private String discount;

	@Column(name = "discount_amount")
	private String discountAmount;

	@Column(name = "discount_type")
	private String discountType;

	@Column(name = "return_quantity")
	private String returnQuantity;

	@Column(name = "return_status")
	private String returnStatus;

	@Column(name = "sold_quantity")
	private String soldQuantity;

	@Column(name = "sold_status")
	private String soldStatus;

	private String status;

	private String sell_price;

	// bi-directional many-to-one association to I0019
	@ManyToOne
	@JoinColumn(name = "bar_code_id")
	private I0019 i0019;

	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}
	// bi-directional many-to-one association to I0021
	@ManyToOne
	@JoinColumn(name = "sales_id")
	private I0021 i0021;

	public SalesRecord() {
	}

	public int getSalesRecordId() {
		return this.salesRecordId;
	}

	public void setSalesRecordId(int salesRecordId) {
		this.salesRecordId = salesRecordId;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getDiscountType() {
		return this.discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getReturnQuantity() {
		return this.returnQuantity;
	}

	public void setReturnQuantity(String returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public String getReturnStatus() {
		return this.returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public String getSoldQuantity() {
		return this.soldQuantity;
	}

	public void setSoldQuantity(String soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public String getSoldStatus() {
		return this.soldStatus;
	}

	public void setSoldStatus(String soldStatus) {
		this.soldStatus = soldStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public I0019 getI0019() {
		return this.i0019;
	}

	public void setI0019(I0019 i0019) {
		this.i0019 = i0019;
	}

	public I0021 getI0021() {
		return this.i0021;
	}

	public void setI0021(I0021 i0021) {
		this.i0021 = i0021;
	}

	public String getSell_price() {
		return sell_price;
	}

	public void setSell_price(String sell_price) {
		this.sell_price = sell_price;
	}

	@Column(name = "cmsion_amt")
	private String cmsionAmt;

	@Column(name = "comm_amt")
	private String commAmt;
	private String totcmsion;

	@Column(name = "totcomm_amt")
	private String totcommAmt;

	public String getCmsionAmt() {
		return this.cmsionAmt;
	}

	public void setCmsionAmt(String cmsionAmt) {
		this.cmsionAmt = cmsionAmt;
	}

	public String getCommAmt() {
		return this.commAmt;
	}

	public void setCommAmt(String commAmt) {
		this.commAmt = commAmt;
	}

	public String getTotcmsion() {
		return this.totcmsion;
	}

	public void setTotcmsion(String totcmsion) {
		this.totcmsion = totcmsion;
	}

	public String getTotcommAmt() {
		return this.totcommAmt;
	}

	public void setTotcommAmt(String totcommAmt) {
		this.totcommAmt = totcommAmt;
	}

}