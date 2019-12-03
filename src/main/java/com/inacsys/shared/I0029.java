package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the i0029 database table.
 * 
 */
@Entity
@Table(name = "i0029")
@NamedQuery(name = "I0029.findAll", query = "SELECT i FROM I0029 i")
public class I0029 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int state_ID;

	private String name;

	// bi-directional many-to-one association to I0028
	@ManyToOne
	@JoinColumn(name = "country_ID")
	private I0028 i0028;

	// bi-directional many-to-one association to I0030
	@OneToMany(mappedBy = "i0029")
	private List<I0030> i0030s;

	public I0029() {
	}

	public int getState_ID() {
		return this.state_ID;
	}

	public void setState_ID(int state_ID) {
		this.state_ID = state_ID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public I0028 getI0028() {
		return this.i0028;
	}

	public void setI0028(I0028 i0028) {
		this.i0028 = i0028;
	}

	public List<I0030> getI0030s() {
		return this.i0030s;
	}

	public void setI0030s(List<I0030> i0030s) {
		this.i0030s = i0030s;
	}

	public I0030 addI0030(I0030 i0030) {
		getI0030s().add(i0030);
		i0030.setI0029(this);

		return i0030;
	}

	public I0030 removeI0030(I0030 i0030) {
		getI0030s().remove(i0030);
		i0030.setI0029(null);

		return i0030;
	}

}