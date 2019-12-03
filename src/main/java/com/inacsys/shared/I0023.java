package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the i0023 database table.
 * 
 */
@Entity
@Table(name = "i0023")
@NamedQuery(name = "I0023.findAll", query = "SELECT i FROM I0023 i")
public class I0023 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int payment_ID;

	@Column(name = "account_no")
	private String accountNo;

	@Column(name = "balance_amount")
	private String balanceAmount;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "card_no")
	private String cardNo;

	@Temporal(TemporalType.DATE)
	@Column(name = "cheque_date")
	private Date chequeDate;

	@Column(name = "cheque_no")
	private String chequeNo;
	
	@Column(name = "account_type")
	private String accountType;

	@Column(name = "account_description")
	private String accountDescription;

	@Column(name = "paid_amount")
	private String paidAmount;

	@Column(name = "payable_amount")
	private String payableAmount;

	@Column(name = "payment_type")
	private String paymentType;

	private float receivable;

	private int sales_payment_ID;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	private String status;

	// bi-directional many-to-one association to I0022
	@ManyToOne
	@JoinColumn(name = "invoice_ID")
	private I0022 i0022;

	public I0023() {
	}

	public int getPayment_ID() {
		return this.payment_ID;
	}

	public void setPayment_ID(int payment_ID) {
		this.payment_ID = payment_ID;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBalanceAmount() {
		return this.balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Date getChequeDate() {
		return this.chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public String getChequeNo() {
		return this.chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPayableAmount() {
		return this.payableAmount;
	}

	public void setPayableAmount(String payableAmount) {
		this.payableAmount = payableAmount;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public float getReceivable() {
		return this.receivable;
	}

	public void setReceivable(float receivable) {
		this.receivable = receivable;
	}

	public int getSales_payment_ID() {
		return this.sales_payment_ID;
	}

	public void setSales_payment_ID(int sales_payment_ID) {
		this.sales_payment_ID = sales_payment_ID;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public I0022 getI0022() {
		return this.i0022;
	}

	public void setI0022(I0022 i0022) {
		this.i0022 = i0022;
	}
	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountDescription() {
		return accountDescription;
	}

	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}

}