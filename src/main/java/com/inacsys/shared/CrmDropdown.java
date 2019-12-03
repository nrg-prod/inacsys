package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the crm_dropdown database table.
 * 
 */
@Entity
@Table(name="crm_dropdown")
@NamedQuery(name="CrmDropdown.findAll", query="SELECT c FROM CrmDropdown c")
public class CrmDropdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int crm_dropDown_ID;

	private String status;
	
	private String client_ID;
	
	@ManyToOne
	@JoinColumn(name = "user_ID")
	private UserCreate userID;
	
	

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public UserCreate getUserID() {
		return userID;
	}

	public void setUserID(UserCreate userID) {
		this.userID = userID;
	}

	public CrmDropdown() {
	}

	public int getCrm_dropDown_ID() {
		return this.crm_dropDown_ID;
	}

	public void setCrm_dropDown_ID(int crm_dropDown_ID) {
		this.crm_dropDown_ID = crm_dropDown_ID;
	}


	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}