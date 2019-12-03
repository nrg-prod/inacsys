package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the i0030 database table.
 * 
 */
@Entity
@Table(name = "i0030")
@NamedQuery(name = "I0030.findAll", query = "SELECT i FROM I0030 i")
public class I0030 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int dist_ID;

	private String name;

	// bi-directional many-to-one association to I0029
	@ManyToOne
	@JoinColumn(name = "state_ID")
	private I0029 i0029;

	public I0030() {
	}

	public int getDist_ID() {
		return this.dist_ID;
	}

	public void setDist_ID(int dist_ID) {
		this.dist_ID = dist_ID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public I0029 getI0029() {
		return this.i0029;
	}

	public void setI0029(I0029 i0029) {
		this.i0029 = i0029;
	}

}