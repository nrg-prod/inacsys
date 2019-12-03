package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the member_payment database table.
 * 
 */
@Entity
@Table(name="member_payment")
@NamedQuery(name="MemberPayment.findAll", query="SELECT m FROM MemberPayment m")
public class MemberPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="payment_id")
	private int paymentId;

	private String amount;

	@ManyToOne
	@JoinColumn(name = "buyer_id")
	private I0032 buyerId;

	private String clientID;

	@Temporal(TemporalType.DATE)
	private Date creaateDate;

	private String description;

	@Column(name="member_id")
	private String memberId;

	private String status;

	public MemberPayment() {
	}
	
	public I0032 getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(I0032 buyerId) {
		this.buyerId = buyerId;
	}

	public int getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getClientID() {
		return this.clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public Date getCreaateDate() {
		return this.creaateDate;
	}

	public void setCreaateDate(Date creaateDate) {
		this.creaateDate = creaateDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}