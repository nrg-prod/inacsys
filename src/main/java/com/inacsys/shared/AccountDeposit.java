package com.inacsys.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the account_deposit database table.
 * 
 */
@Entity
@Table(name="account_deposit")
@NamedQuery(name="AccountDeposit.findAll", query="SELECT a FROM AccountDeposit a")
public class AccountDeposit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int account_deposit_ID;

	@Column(name="deposit_to")
	private String depositTo;

	private String status;
	
	@Column(name="account_type")
	private String accountType;
	
	@Column(name="balance")
	private String balance;
	
	@Column(name="client_ID")
	private String clientID;
	
	@Column(name="account_status")
	private String accountStatus;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	public AccountDeposit() {
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public int getAccount_deposit_ID() {
		return this.account_deposit_ID;
	}

	public void setAccount_deposit_ID(int account_deposit_ID) {
		this.account_deposit_ID = account_deposit_ID;
	}

	public String getDepositTo() {
		return this.depositTo;
	}

	public void setDepositTo(String depositTo) {
		this.depositTo = depositTo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}