package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the i0012 database table.
 * 
 */
@Entity
@Table(name = "i0012")
@NamedQuery(name = "I0012.findAll", query = "SELECT i FROM I0012 i")
public class I0012 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int civil_ID;

	@Column(name = "product_cent")
	private String productCent;

	@Column(name = "selling_unit")
	private String sellingUnit;

	// bi-directional many-to-one association to I0001
	@OneToMany(mappedBy = "i0012")
	private List<I0001> i0001s;

	// bi-directional many-to-one association to I0006
	@ManyToOne
	@JoinColumn(name = "industry_ID")
	private I0006 i0006;

	// bi-directional many-to-one association to I0014
	@OneToMany(mappedBy = "i0012")
	private List<I0014> i0014s;

	public I0012() {
	}

	public int getCivil_ID() {
		return this.civil_ID;
	}

	public void setCivil_ID(int civil_ID) {
		this.civil_ID = civil_ID;
	}

	public String getProductCent() {
		return this.productCent;
	}

	public void setProductCent(String productCent) {
		this.productCent = productCent;
	}

	public String getSellingUnit() {
		return this.sellingUnit;
	}

	public void setSellingUnit(String sellingUnit) {
		this.sellingUnit = sellingUnit;
	}

	public List<I0001> getI0001s() {
		return this.i0001s;
	}

	public void setI0001s(List<I0001> i0001s) {
		this.i0001s = i0001s;
	}

	public I0001 addI0001(I0001 i0001) {
		getI0001s().add(i0001);
		i0001.setI0012(this);

		return i0001;
	}

	public I0001 removeI0001(I0001 i0001) {
		getI0001s().remove(i0001);
		i0001.setI0012(null);

		return i0001;
	}

	public I0006 getI0006() {
		return this.i0006;
	}

	public void setI0006(I0006 i0006) {
		this.i0006 = i0006;
	}

	public List<I0014> getI0014s() {
		return this.i0014s;
	}

	public void setI0014s(List<I0014> i0014s) {
		this.i0014s = i0014s;
	}

	public I0014 addI0014(I0014 i0014) {
		getI0014s().add(i0014);
		i0014.setI0012(this);

		return i0014;
	}

	public I0014 removeI0014(I0014 i0014) {
		getI0014s().remove(i0014);
		i0014.setI0012(null);

		return i0014;
	}

}