package com.inacsys.shared;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the paymentcash database table.
 * 
 */
@Entity
@Table(name="paymentcash")
@NamedQuery(name="Paymentcash.findAll", query="SELECT p FROM Paymentcash p")
public class Paymentcash implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int paymentCashId;

	private String paymentType;

	private String status;

	public Paymentcash() {
	}

	public int getPaymentCashId() {
		return this.paymentCashId;
	}

	public void setPaymentCashId(int paymentCashId) {
		this.paymentCashId = paymentCashId;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}