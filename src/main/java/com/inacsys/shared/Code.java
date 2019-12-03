package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import com.inacsys.domain.ATransaction;


/**
 * The persistent class for the code database table.
 * 
 */
@Entity
@Table(name="code")
@NamedQuery(name="Code.findAll", query="SELECT c FROM Code c")
public class Code implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String client_ID;

	private String code;

	private String description;

	private String status;

	public Code() {
	}
	
	public Code(ATransaction aTransaction){
		this.client_ID=aTransaction.getClientID();
		this.code=aTransaction.getCode();
		this.description=aTransaction.getCodeDescription();
		this.status="Active";
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient_ID() {
		return this.client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}