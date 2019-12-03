package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the i0014 database table.
 * 
 */
@Entity
@Table(name = "i0014")
@NamedQuery(name = "I0014.findAll", query = "SELECT i FROM I0014 i")
public class I0014 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int hasproduct_ID;

	// bi-directional many-to-one association to I0007
	@ManyToOne
	@JoinColumn(name = "software_ID")
	private I0007 i0007;

	// bi-directional many-to-one association to I0008
	@ManyToOne
	@JoinColumn(name = "mechanical_ID")
	private I0008 i0008;

	// bi-directional many-to-one association to I0009
	@ManyToOne
	@JoinColumn(name = "biomedical_ID")
	private I0009 i0009;

	// bi-directional many-to-one association to I0010
	@ManyToOne
	@JoinColumn(name = "electrical_ID")
	private I0010 i0010;

	// bi-directional many-to-one association to I0011
	@ManyToOne
	@JoinColumn(name = "electronics_ID")
	private I0011 i0011;

	// bi-directional many-to-one association to I0012
	@ManyToOne
	@JoinColumn(name = "civil_ID")
	private I0012 i0012;

	// bi-directional many-to-one association to I0013
	@ManyToOne
	@JoinColumn(name = "food_ID")
	private I0013 i0013;

	public I0014() {
	}

	public int getHasproduct_ID() {
		return this.hasproduct_ID;
	}

	public void setHasproduct_ID(int hasproduct_ID) {
		this.hasproduct_ID = hasproduct_ID;
	}

	public I0007 getI0007() {
		return this.i0007;
	}

	public void setI0007(I0007 i0007) {
		this.i0007 = i0007;
	}

	public I0008 getI0008() {
		return this.i0008;
	}

	public void setI0008(I0008 i0008) {
		this.i0008 = i0008;
	}

	public I0009 getI0009() {
		return this.i0009;
	}

	public void setI0009(I0009 i0009) {
		this.i0009 = i0009;
	}

	public I0010 getI0010() {
		return this.i0010;
	}

	public void setI0010(I0010 i0010) {
		this.i0010 = i0010;
	}

	public I0011 getI0011() {
		return this.i0011;
	}

	public void setI0011(I0011 i0011) {
		this.i0011 = i0011;
	}

	public I0012 getI0012() {
		return this.i0012;
	}

	public void setI0012(I0012 i0012) {
		this.i0012 = i0012;
	}

	public I0013 getI0013() {
		return this.i0013;
	}

	public void setI0013(I0013 i0013) {
		this.i0013 = i0013;
	}

}