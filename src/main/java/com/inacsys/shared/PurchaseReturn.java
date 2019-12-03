package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the purchase_return database table.
 * 
 */
@Entity
@Table(name = "purchase_return")
@NamedQuery(name = "PurchaseReturn.findAll", query = "SELECT p FROM PurchaseReturn p")
public class PurchaseReturn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int purchase_return_ID;

	@Column(name = "damage_return")
	private String damageReturn;

	@Column(name = "normal_return")
	private String normalReturn;

	private String nr;

	private String price;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "purchase_order_number")
	private String purchaseOrderNumber;

	private String quantity;

	@Temporal(TemporalType.DATE)
	@Column(name = "return_date")
	private Date returnDate;
	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}
	// bi-directional many-to-one association to I0015
	@ManyToOne
	@JoinColumn(name = "purchase_id")
	private I0015 i0015;

	// bi-directional many-to-one association to I0019
	@ManyToOne
	@JoinColumn(name = "bar_code_ID")
	private I0019 i0019;

	public I0019 getI0019() {
		return i0019;
	}

	public void setI0019(I0019 i0019) {
		this.i0019 = i0019;
	}

	public PurchaseReturn() {
	}

	public int getPurchase_return_ID() {
		return this.purchase_return_ID;
	}

	public void setPurchase_return_ID(int purchase_return_ID) {
		this.purchase_return_ID = purchase_return_ID;
	}

	public String getDamageReturn() {
		return this.damageReturn;
	}

	public void setDamageReturn(String damageReturn) {
		this.damageReturn = damageReturn;
	}

	public String getNormalReturn() {
		return this.normalReturn;
	}

	public void setNormalReturn(String normalReturn) {
		this.normalReturn = normalReturn;
	}

	public String getNr() {
		return this.nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPurchaseOrderNumber() {
		return this.purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public I0015 getI0015() {
		return this.i0015;
	}

	public void setI0015(I0015 i0015) {
		this.i0015 = i0015;
	}

}