package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the i0028 database table.
 * 
 */
@Entity
@Table(name = "i0028")
@NamedQuery(name = "I0028.findAll", query = "SELECT i FROM I0028 i")
public class I0028 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int country_ID;

	private String name;

	public I0028() {
	}

	public int getCountry_ID() {
		return this.country_ID;
	}

	public void setCountry_ID(int country_ID) {
		this.country_ID = country_ID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}