package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the quotation database table.
 * 
 */
@Entity
@Table(name="quotation")
@NamedQuery(name="Quotation.findAll", query="SELECT q FROM Quotation q")
public class Quotation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="quotation_id")
	private int quotationId;

	@Column(name="approval_status")
	private String approvalStatus;

	private String client_ID;

	@Temporal(TemporalType.DATE)
	@Column(name="delivery_date")
	private Date deliveryDate;

	@Column(name="product_count")
	private String productCount;

	@Column(name="product_name")
	private String productName;

	@Column(name="quotation_number")
	private String quotationNumber;

	private String status;
	
	private String choosenStatus;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@ManyToOne
	@JoinColumn(name = "user_ID")
	private UserCreate userID;

	public Quotation() {
	}

	public String getChoosenStatus() {
		return choosenStatus;
	}


	public void setChoosenStatus(String choosenStatus) {
		this.choosenStatus = choosenStatus;
	}


	public int getQuotationId() {
		return this.quotationId;
	}

	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getClient_ID() {
		return this.client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getProductCount() {
		return this.productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuotationNumber() {
		return this.quotationNumber;
	}

	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public UserCreate getUserID() {
		return userID;
	}

	public void setUserID(UserCreate userID) {
		this.userID = userID;
	}

}