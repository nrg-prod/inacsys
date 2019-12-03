package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the i0016 database table.
 * 
 */
@Entity
@Table(name = "i0016")
@NamedQuery(name = "I0016.findAll", query = "SELECT i FROM I0016 i")
public class I0016 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int has_purchase_ID;

	@Column(name = "approval_status")
	private String approvalStatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "order_date")
	private Date orderDate;

	private String ordernumber;

	private String quantity;

	private String status;

	private String status2;

	private String status3;

	private String status4;

	private String quantityTotal;

	public String getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(String quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	// bi-directional many-to-one association to I0001
	@ManyToOne
	@JoinColumn(name = "product_ID")
	private I0001 i0001;

	// bi-directional many-to-one association to I0015
	@ManyToOne
	@JoinColumn(name = "purchase_ID")
	private I0015 i0015;

	// bi-directional many-to-one association to I0031
	@ManyToOne
	@JoinColumn(name = "has_vendor_ID")
	private I0031 i0031;

	public I0016() {
	}

	public int getHas_purchase_ID() {
		return this.has_purchase_ID;
	}

	public void setHas_purchase_ID(int has_purchase_ID) {
		this.has_purchase_ID = has_purchase_ID;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrdernumber() {
		return this.ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus2() {
		return this.status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public String getStatus3() {
		return this.status3;
	}

	public void setStatus3(String status3) {
		this.status3 = status3;
	}

	public String getStatus4() {
		return this.status4;
	}

	public void setStatus4(String status4) {
		this.status4 = status4;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Date dueDate;

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public I0001 getI0001() {
		return this.i0001;
	}

	public void setI0001(I0001 i0001) {
		this.i0001 = i0001;
	}

	public I0015 getI0015() {
		return this.i0015;
	}

	public void setI0015(I0015 i0015) {
		this.i0015 = i0015;
	}

	public I0031 getI0031() {
		return this.i0031;
	}

	public void setI0031(I0031 i0031) {
		this.i0031 = i0031;
	}

	
	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}
}