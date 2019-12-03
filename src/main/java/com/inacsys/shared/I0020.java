package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the i0020 database table.
 * 
 */
@Entity
@Table(name = "i0020")
@NamedQuery(name = "I0020.findAll", query = "SELECT i FROM I0020 i")
public class I0020 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int stock_damage_ID;

	// bi-directional many-to-one association to I0015
	@ManyToOne
	@JoinColumn(name = "purchase_ID")
	private I0015 i0015;

	// bi-directional many-to-one association to I0017
	@ManyToOne
	@JoinColumn(name = "stock_ID")
	private I0017 i0017;

	public I0020() {
	}

	public int getStock_damage_ID() {
		return this.stock_damage_ID;
	}

	public void setStock_damage_ID(int stock_damage_ID) {
		this.stock_damage_ID = stock_damage_ID;
	}

	public I0015 getI0015() {
		return this.i0015;
	}

	public void setI0015(I0015 i0015) {
		this.i0015 = i0015;
	}

	public I0017 getI0017() {
		return this.i0017;
	}

	public void setI0017(I0017 i0017) {
		this.i0017 = i0017;
	}

	
	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}
}