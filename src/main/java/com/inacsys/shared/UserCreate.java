package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user_create database table.
 * 
 */
@Entity
@Table(name="user_create")
@NamedQuery(name="UserCreate.findAll", query="SELECT u FROM UserCreate u")
public class UserCreate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_no")
	private int userNo;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	@Column(name="user_mailid")
	private String userMailid;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_password")
	private String userPassword;

	@Column(name="user_phone")
	private String userPhone;
	
	// bi-directional many-to-one association to I0017
	@ManyToOne
	@JoinColumn(name = "client_ID")
	private Client client;
	
	@Column(name="user_type")
	private String userType;

	public UserCreate() {
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getUserNo() {
		return this.userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
 

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUserMailid() {
		return this.userMailid;
	}

	public void setUserMailid(String userMailid) {
		this.userMailid = userMailid;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

}