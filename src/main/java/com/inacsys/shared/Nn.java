package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the nn database table.
 * 
 */
@Entity
@Table(name = "nn")
@NamedQuery(name = "Nn.findAll", query = "SELECT n FROM Nn n")
public class Nn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "reg_id")
	private int regId;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "product_id")
	private int productId;

	private String status;

	private String status2;

	@Column(name = "temp_file_path")
	private String tempFilePath;

	public Nn() {
	}

	public int getRegId() {
		return this.regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus2() {
		return this.status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public String getTempFilePath() {
		return this.tempFilePath;
	}

	public void setTempFilePath(String tempFilePath) {
		this.tempFilePath = tempFilePath;
	}

}