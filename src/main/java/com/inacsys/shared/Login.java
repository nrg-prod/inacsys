package com.inacsys.shared;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the login database table.
 * 
 */
@Entity
@Table(name="login")
@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="login_id")
	private int loginId;

	@Column(name="client_id")
	private String clientId;

	@Column(name="login_password")
	private String loginPassword;

	@Column(name="login_user")
	private String loginUser;

	private String role;

	private String status;

	// bi-directional many-to-one association to user_create
	@ManyToOne
	@JoinColumn(name = "user_ID")
	private UserCreate userCreate;

	public Login() {
	}

	public int getLoginId() {
		return this.loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getLoginUser() {
		return this.loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserCreate getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(UserCreate userCreate) {
		this.userCreate = userCreate;
	}



}