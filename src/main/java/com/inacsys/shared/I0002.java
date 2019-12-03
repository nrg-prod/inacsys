package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the i0002 database table.
 * 
 */
@Entity
@Table(name = "i0002")
@NamedQuery(name = "I0002.findAll", query = "SELECT i FROM I0002 i")
public class I0002 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int product_group_ID;

	private String description;

	@Column(name = "product_group_name")
	private String productGroupName;

	// bi-directional many-to-one association to I0001
	@OneToMany(mappedBy = "i0002")
	private List<I0001> i0001s;

	public I0002() {
	}

	public int getProduct_group_ID() {
		return this.product_group_ID;
	}

	public void setProduct_group_ID(int product_group_ID) {
		this.product_group_ID = product_group_ID;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductGroupName() {
		return this.productGroupName;
	}

	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}

	public List<I0001> getI0001s() {
		return this.i0001s;
	}

	public void setI0001s(List<I0001> i0001s) {
		this.i0001s = i0001s;
	}

	public I0001 addI0001(I0001 i0001) {
		getI0001s().add(i0001);
		i0001.setI0002(this);

		return i0001;
	}

	public I0001 removeI0001(I0001 i0001) {
		getI0001s().remove(i0001);
		i0001.setI0002(null);

		return i0001;
	}

}