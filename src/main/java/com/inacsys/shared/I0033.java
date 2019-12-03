package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the i0033 database table.
 * 
 */
@Entity
@Table(name = "i0033")
@NamedQuery(name = "I0033.findAll", query = "SELECT i FROM I0033 i")
public class I0033 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int filepath_Id;

	private String filepath;

	@Column(name = "finalfile_path")
	private String finalfilePath;

	private String status;

	// bi-directional many-to-one association to I0015
	@ManyToOne
	@JoinColumn(name = "purchase_ID")
	private I0015 i0015;

	// bi-directional many-to-one association to I0022
	@ManyToOne
	@JoinColumn(name = "invoice_ID")
	private I0022 i0022;

	public I0033() {
	}

	public int getFilepath_Id() {
		return this.filepath_Id;
	}

	public void setFilepath_Id(int filepath_Id) {
		this.filepath_Id = filepath_Id;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFinalfilePath() {
		return this.finalfilePath;
	}

	public void setFinalfilePath(String finalfilePath) {
		this.finalfilePath = finalfilePath;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public I0015 getI0015() {
		return this.i0015;
	}

	public void setI0015(I0015 i0015) {
		this.i0015 = i0015;
	}

	public I0022 getI0022() {
		return this.i0022;
	}

	public void setI0022(I0022 i0022) {
		this.i0022 = i0022;
	}

}