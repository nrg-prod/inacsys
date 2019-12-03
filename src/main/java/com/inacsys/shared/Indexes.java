package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the index database table.
 * 
 */
@Entity
@Table(name="indexes")
@NamedQuery(name="Indexes.findAll", query="SELECT i FROM Indexes i")
public class Indexes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String module;

	private String value;
	
	private String client_ID;
	
	private String userID;
	
	private String status;
	
	private int module_id;

	public Indexes() {
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}