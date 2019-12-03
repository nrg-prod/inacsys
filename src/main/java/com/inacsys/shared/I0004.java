package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the i0004 database table.
 * 
 */
@Entity
@Table(name = "i0004")
@NamedQuery(name = "I0004.findAll", query = "SELECT i FROM I0004 i")
public class I0004 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int type_parent_ID;

	private String name;

	// bi-directional many-to-one association to I0001
	@OneToMany(mappedBy = "i0004")
	private List<I0001> i0001s;

	// bi-directional many-to-one association to I0003
	@OneToMany(mappedBy = "i0004")
	private List<I0003> i0003s;

	public I0004() {
	}

	public int getType_parent_ID() {
		return this.type_parent_ID;
	}

	public void setType_parent_ID(int type_parent_ID) {
		this.type_parent_ID = type_parent_ID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<I0001> getI0001s() {
		return this.i0001s;
	}

	public void setI0001s(List<I0001> i0001s) {
		this.i0001s = i0001s;
	}

	public I0001 addI0001(I0001 i0001) {
		getI0001s().add(i0001);
		i0001.setI0004(this);

		return i0001;
	}

	public I0001 removeI0001(I0001 i0001) {
		getI0001s().remove(i0001);
		i0001.setI0004(null);

		return i0001;
	}

	public List<I0003> getI0003s() {
		return this.i0003s;
	}

	public void setI0003s(List<I0003> i0003s) {
		this.i0003s = i0003s;
	}

	public I0003 addI0003(I0003 i0003) {
		getI0003s().add(i0003);
		i0003.setI0004(this);

		return i0003;
	}

	public I0003 removeI0003(I0003 i0003) {
		getI0003s().remove(i0003);
		i0003.setI0004(null);

		return i0003;
	}

}