package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the i0003 database table.
 * 
 */
@Entity
@Table(name = "i0003")
@NamedQuery(name = "I0003.findAll", query = "SELECT i FROM I0003 i")
public class I0003 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int type_ID;

	@Column(name = "business_product")
	private String businessProduct;

	@Column(name = "daily_purachase_product")
	private String dailyPurachaseProduct;

	@Column(name = "unsought_product")
	private String unsoughtProduct;

	// bi-directional many-to-one association to I0004
	@ManyToOne
	@JoinColumn(name = "type_parent_ID")
	private I0004 i0004;

	public I0003() {
	}

	public int getType_ID() {
		return this.type_ID;
	}

	public void setType_ID(int type_ID) {
		this.type_ID = type_ID;
	}

	public String getBusinessProduct() {
		return this.businessProduct;
	}

	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
	}

	public String getDailyPurachaseProduct() {
		return this.dailyPurachaseProduct;
	}

	public void setDailyPurachaseProduct(String dailyPurachaseProduct) {
		this.dailyPurachaseProduct = dailyPurachaseProduct;
	}

	public String getUnsoughtProduct() {
		return this.unsoughtProduct;
	}

	public void setUnsoughtProduct(String unsoughtProduct) {
		this.unsoughtProduct = unsoughtProduct;
	}

	public I0004 getI0004() {
		return this.i0004;
	}

	public void setI0004(I0004 i0004) {
		this.i0004 = i0004;
	}

}