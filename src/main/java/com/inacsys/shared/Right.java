package com.inacsys.shared;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the rights database table.
 * 
 */
@Entity
@Table(name = "rights")
@NamedQuery(name = "Right.findAll", query = "SELECT r FROM Right r")
public class Right implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int rights_ID;

	private String mode;

	private String rights;

	// bi-directional many-to-one association to LoginRight
	@OneToMany(mappedBy = "right")
	private List<LoginRight> loginRights;

	public Right() {
	}

	public int getRights_ID() {
		return this.rights_ID;
	}

	public void setRights_ID(int rights_ID) {
		this.rights_ID = rights_ID;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getRights() {
		return this.rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public List<LoginRight> getLoginRights() {
		return this.loginRights;
	}

	public void setLoginRights(List<LoginRight> loginRights) {
		this.loginRights = loginRights;
	}

	public LoginRight addLoginRight(LoginRight loginRight) {
		getLoginRights().add(loginRight);
		loginRight.setRight(this);

		return loginRight;
	}

	public LoginRight removeLoginRight(LoginRight loginRight) {
		getLoginRights().remove(loginRight);
		loginRight.setRight(null);

		return loginRight;
	}

}