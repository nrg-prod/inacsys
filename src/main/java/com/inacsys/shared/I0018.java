package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the i0018 database table.
 * 
 */
@Entity
@Table(name = "i0018")
@NamedQuery(name = "I0018.findAll", query = "SELECT i FROM I0018 i")
public class I0018 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int batch_ID;

	@Column(name = "batch_name")
	private String batchName;

	@Column(name = "product_name")
	private String productName;

	private String status;

	@Column(name = "unit_price")
	private String unitPrice;

	// bi-directional many-to-one association to I0017
	@ManyToOne
	@JoinColumn(name = "stock_ID")
	private I0017 i0017;

	// bi-directional many-to-one association to I0019
	@OneToMany(mappedBy = "i0018")
	private List<I0019> i0019s;

	public I0018() {
	}

	public int getBatch_ID() {
		return this.batch_ID;
	}

	public void setBatch_ID(int batch_ID) {
		this.batch_ID = batch_ID;
	}

	public String getBatchName() {
		return this.batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public I0017 getI0017() {
		return this.i0017;
	}

	public void setI0017(I0017 i0017) {
		this.i0017 = i0017;
	}

	public List<I0019> getI0019s() {
		return this.i0019s;
	}

	public void setI0019s(List<I0019> i0019s) {
		this.i0019s = i0019s;
	}

	public I0019 addI0019(I0019 i0019) {
		getI0019s().add(i0019);
		i0019.setI0018(this);

		return i0019;
	}

	public I0019 removeI0019(I0019 i0019) {
		getI0019s().remove(i0019);
		i0019.setI0018(null);

		return i0019;
	}

	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}
	
}