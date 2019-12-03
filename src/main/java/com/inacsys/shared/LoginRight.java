package com.inacsys.shared;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the login_rights database table.
 * 
 */
@Entity
@Table(name = "login_rights")
@NamedQuery(name = "LoginRight.findAll", query = "SELECT l FROM LoginRight l")
public class LoginRight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int login_rights_ID;

	// bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name = "login_id")
	private Login login;

	// bi-directional many-to-one association to Right
	@ManyToOne
	@JoinColumn(name = "rights_ID")
	private Right right;

	public LoginRight() {
	}

	public int getLogin_rights_ID() {
		return this.login_rights_ID;
	}

	public void setLogin_rights_ID(int login_rights_ID) {
		this.login_rights_ID = login_rights_ID;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Right getRight() {
		return this.right;
	}

	public void setRight(Right right) {
		this.right = right;
	}

}