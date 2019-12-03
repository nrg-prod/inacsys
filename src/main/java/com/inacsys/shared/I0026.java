package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the i0026 database table.
 * 
 */
@Entity
@Table(name = "i0026")
@NamedQuery(name = "I0026.findAll", query = "SELECT i FROM I0026 i")
public class I0026 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int frim_ID;

	@Column(name = "corpration_type")
	private String corprationType;

	private String name;

	@Column(name = "partnership_type")
	private String partnershipType;

	@Column(name = "proprietorship_type")
	private String proprietorshipType;

	// bi-directional many-to-one association to I0025
	@OneToMany(mappedBy = "i0026")
	private List<I0025> i0025s;

	public I0026() {
	}

	public int getFrim_ID() {
		return this.frim_ID;
	}

	public void setFrim_ID(int frim_ID) {
		this.frim_ID = frim_ID;
	}

	public String getCorprationType() {
		return this.corprationType;
	}

	public void setCorprationType(String corprationType) {
		this.corprationType = corprationType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartnershipType() {
		return this.partnershipType;
	}

	public void setPartnershipType(String partnershipType) {
		this.partnershipType = partnershipType;
	}

	public String getProprietorshipType() {
		return this.proprietorshipType;
	}

	public void setProprietorshipType(String proprietorshipType) {
		this.proprietorshipType = proprietorshipType;
	}

	public List<I0025> getI0025s() {
		return this.i0025s;
	}

	public void setI0025s(List<I0025> i0025s) {
		this.i0025s = i0025s;
	}

	public I0025 addI0025(I0025 i0025) {
		getI0025s().add(i0025);
		i0025.setI0026(this);

		return i0025;
	}

	public I0025 removeI0025(I0025 i0025) {
		getI0025s().remove(i0025);
		i0025.setI0026(null);

		return i0025;
	}

}