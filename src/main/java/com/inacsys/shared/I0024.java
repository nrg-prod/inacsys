package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the i0024 database table.
 * 
 */
@Entity
@Table(name = "i0024")
@NamedQuery(name = "I0024.findAll", query = "SELECT i FROM I0024 i")
public class I0024 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int delivery_ID;

	@Column(name = "delay_reason")
	private String delayReason;

	@Temporal(TemporalType.DATE)
	@Column(name = "delivered_date")
	private Date deliveredDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "delivery_date")
	private Date deliveryDate;

	private String status;

	// bi-directional many-to-one association to I0015
	@ManyToOne
	@JoinColumn(name = "purchase_ID")
	private I0015 i0015;

	// bi-directional many-to-one association to I0021
	@ManyToOne
	@JoinColumn(name = "sales_ID")
	private I0021 i0021;

	public I0024() {
	}

	public int getDelivery_ID() {
		return this.delivery_ID;
	}

	public void setDelivery_ID(int delivery_ID) {
		this.delivery_ID = delivery_ID;
	}

	public String getDelayReason() {
		return this.delayReason;
	}

	public void setDelayReason(String delayReason) {
		this.delayReason = delayReason;
	}

	public Date getDeliveredDate() {
		return this.deliveredDate;
	}

	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public I0015 getI0015() {
		return this.i0015;
	}

	public void setI0015(I0015 i0015) {
		this.i0015 = i0015;
	}

	public I0021 getI0021() {
		return this.i0021;
	}

	public void setI0021(I0021 i0021) {
		this.i0021 = i0021;
	}

}