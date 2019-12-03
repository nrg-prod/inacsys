package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the i0005 database table.
 * 
 */
@Entity
@Table(name = "i0005")
@NamedQuery(name = "I0005.findAll", query = "SELECT i FROM I0005 i")
public class I0005 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "category_type")
	private String categoryType;

	private String description;
	
	private String status;

	private String industry;

	// bi-directional many-to-one association to I0001
	@OneToMany(mappedBy = "i0005")
	private List<I0001> i0001s;
	
	private String approvalStatus;
	
	// bi-directional many-to-one association to UserCreate
	@ManyToOne
	@JoinColumn(name = "user_ID")
	private UserCreate userID;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	public I0005() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public UserCreate getUserID() {
		return userID;
	}

	public void setUserID(UserCreate userID) {
		this.userID = userID;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryType() {
		return this.categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public List<I0001> getI0001s() {
		return this.i0001s;
	}

	public void setI0001s(List<I0001> i0001s) {
		this.i0001s = i0001s;
	}

	public I0001 addI0001(I0001 i0001) {
		getI0001s().add(i0001);
		i0001.setI0005(this);

		return i0001;
	}

	public I0001 removeI0001(I0001 i0001) {
		getI0001s().remove(i0001);
		i0001.setI0005(null);

		return i0001;
	}

	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}