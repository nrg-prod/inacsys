package com.inacsys.shared;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the account_payments database table.
 * 
 */
@Entity
@Table(name="account_payments")
@NamedQuery(name="AccountPayment.findAll", query="SELECT a FROM AccountPayment a")
public class AccountPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int account_payment_ID;

	@Column(name="balance_amount")
	private String balanceAmount;

	private String client_ID;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="paid_amount")
	private String paidAmount;

	private String status;
	
	//bi-directional many-to-one association to Transaction
	@ManyToOne
	@JoinColumn(name="transaction_ID")
	private Transaction transaction;

	@Column(name="account_deposit")
	private String accountDeposit;

	public AccountPayment() {
	}

	public int getAccount_payment_ID() {
		return this.account_payment_ID;
	}

	public void setAccount_payment_ID(int account_payment_ID) {
		this.account_payment_ID = account_payment_ID;
	}

	public String getBalanceAmount() {
		return this.balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getClient_ID() {
		return this.client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getAccountDeposit() {
		return accountDeposit;
	}

	public void setAccountDeposit(String accountDeposit) {
		this.accountDeposit = accountDeposit;
	}

}