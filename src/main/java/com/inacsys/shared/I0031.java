package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the i0031 database table.
 * 
 */
@Entity
@Table(name = "i0031")
@NamedQuery(name = "I0031.findAll", query = "SELECT i FROM I0031 i")
public class I0031 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int has_vendor_ID;

	private String status;
	
	@Column(name="product_price")
	private String productPrice;

	// bi-directional many-to-one association to I0016
	@OneToMany(mappedBy = "i0031")
	private List<I0016> i0016s;

	// bi-directional many-to-one association to I0001
	@ManyToOne
	@JoinColumn(name = "product_ID")
	private I0001 i0001;

	// bi-directional many-to-one association to I0025
	@ManyToOne
	@JoinColumn(name = "vendor_ID")
	private I0025 i0025;

	public I0031() {
	}

	public String getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}


	public int getHas_vendor_ID() {
		return this.has_vendor_ID;
	}

	public void setHas_vendor_ID(int has_vendor_ID) {
		this.has_vendor_ID = has_vendor_ID;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<I0016> getI0016s() {
		return this.i0016s;
	}

	public void setI0016s(List<I0016> i0016s) {
		this.i0016s = i0016s;
	}

	public I0016 addI0016(I0016 i0016) {
		getI0016s().add(i0016);
		i0016.setI0031(this);

		return i0016;
	}

	public I0016 removeI0016(I0016 i0016) {
		getI0016s().remove(i0016);
		i0016.setI0031(null);

		return i0016;
	}

	public I0001 getI0001() {
		return this.i0001;
	}

	public void setI0001(I0001 i0001) {
		this.i0001 = i0001;
	}

	public I0025 getI0025() {
		return this.i0025;
	}

	public void setI0025(I0025 i0025) {
		this.i0025 = i0025;
	}

}