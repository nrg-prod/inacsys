package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the i0017 database table.
 * 
 */
@Entity
@Table(name = "i0017")
@NamedQuery(name = "I0017.findAll", query = "SELECT i FROM I0017 i")
public class I0017 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int stock_ID;

	@Temporal(TemporalType.DATE)
	@Column(name = "purchase_date")
	private Date purchaseDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "stock_in_date")
	private Date stockInDate;

	// bi-directional many-to-one association to I0015
	@ManyToOne
	@JoinColumn(name = "purchase_ID")
	private I0015 i0015;

	// bi-directional many-to-one association to I0018
	@OneToMany(mappedBy = "i0017")
	private List<I0018> i0018s;

	// bi-directional many-to-one association to I0020
	@OneToMany(mappedBy = "i0017")
	private List<I0020> i0020s;

	public I0017() {
	}

	public int getStock_ID() {
		return this.stock_ID;
	}

	public void setStock_ID(int stock_ID) {
		this.stock_ID = stock_ID;
	}

	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getStockInDate() {
		return this.stockInDate;
	}

	public void setStockInDate(Date stockInDate) {
		this.stockInDate = stockInDate;
	}

	public I0015 getI0015() {
		return this.i0015;
	}

	public void setI0015(I0015 i0015) {
		this.i0015 = i0015;
	}

	public List<I0018> getI0018s() {
		return this.i0018s;
	}

	public void setI0018s(List<I0018> i0018s) {
		this.i0018s = i0018s;
	}

	public I0018 addI0018(I0018 i0018) {
		getI0018s().add(i0018);
		i0018.setI0017(this);

		return i0018;
	}

	public I0018 removeI0018(I0018 i0018) {
		getI0018s().remove(i0018);
		i0018.setI0017(null);

		return i0018;
	}

	public List<I0020> getI0020s() {
		return this.i0020s;
	}

	public void setI0020s(List<I0020> i0020s) {
		this.i0020s = i0020s;
	}

	public I0020 addI0020(I0020 i0020) {
		getI0020s().add(i0020);
		i0020.setI0017(this);

		return i0020;
	}

	public I0020 removeI0020(I0020 i0020) {
		getI0020s().remove(i0020);
		i0020.setI0017(null);

		return i0020;
	}

}