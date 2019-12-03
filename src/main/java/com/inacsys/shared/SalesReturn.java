package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the sales_return database table.
 * 
 */
@Entity
@Table(name = "sales_return")
@NamedQuery(name = "SalesReturn.findAll", query = "SELECT s FROM SalesReturn s")
public class SalesReturn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int sales_return_Id;

	private String dr;

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Date dueDate;

	private String nr;

	@Column(name = "product_name")
	private String productName;

	private String quantity;

	@Column(name = "sales_order_number")
	private String salesOrderNumber;

	// bi-directional many-to-one association to I0021
	@ManyToOne
	@JoinColumn(name = "sales_ID")
	private I0021 i0021;

	// bi-directional many-to-one association to SalesRecord
	@ManyToOne
	@JoinColumn(name = "sales_record_id")
	private SalesRecord salesRecord;

	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}
	public SalesReturn() {
	}

	public int getSales_return_Id() {
		return this.sales_return_Id;
	}

	public void setSales_return_Id(int sales_return_Id) {
		this.sales_return_Id = sales_return_Id;
	}

	public String getDr() {
		return this.dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getNr() {
		return this.nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSalesOrderNumber() {
		return this.salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public I0021 getI0021() {
		return this.i0021;
	}

	public void setI0021(I0021 i0021) {
		this.i0021 = i0021;
	}

	public SalesRecord getSalesRecord() {
		return this.salesRecord;
	}

	public void setSalesRecord(SalesRecord salesRecord) {
		this.salesRecord = salesRecord;
	}

}