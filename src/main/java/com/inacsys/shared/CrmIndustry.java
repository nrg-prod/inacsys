package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the crm_industry database table.
 * 
 */
@Entity
@Table(name="crm_industry")
@NamedQuery(name="CrmIndustry.findAll", query="SELECT c FROM CrmIndustry c")
public class CrmIndustry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int crm_industry_ID;

	private String industry;
	
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

	public CrmIndustry() {
	}

	public int getCrm_industry_ID() {
		return this.crm_industry_ID;
	}

	public void setCrm_industry_ID(int crm_industry_ID) {
		this.crm_industry_ID = crm_industry_ID;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

}