package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the quotation_details database table.
 * 
 */
@Entity
@Table(name="quotation_details")
@NamedQuery(name="QuotationDetail.findAll", query="SELECT q FROM QuotationDetail q")
public class QuotationDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="quotation_details_id")
	private int quotationDetailsId;

	@Column(name="product_price")
	private int productPrice;

	@ManyToOne
	@JoinColumn(name = "quotation_id")
	private Quotation quotationId;

	private String status;

	@ManyToOne
	@JoinColumn(name = "vendor_ID")
	private I0025 vendor_ID;
	
	@ManyToOne
	@JoinColumn(name = "product_ID")
	private I0001 product_ID;

	@Column(name="product_name")
	private String productName;
	
	@Column(name="vendor_name")
	private String vendorName;
	
	@Column(name="total_price")
	private String totalPrice;
	
	private String choosenStatus;
	
	public QuotationDetail() {
	}

	public String getChoosenStatus() {
		return choosenStatus;
	}


	public void setChoosenStatus(String choosenStatus) {
		this.choosenStatus = choosenStatus;
	}


	public int getQuotationDetailsId() {
		return this.quotationDetailsId;
	}

	public void setQuotationDetailsId(int quotationDetailsId) {
		this.quotationDetailsId = quotationDetailsId;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public Quotation getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(Quotation quotationId) {
		this.quotationId = quotationId;
	}

	public I0025 getVendor_ID() {
		return vendor_ID;
	}

	public void setVendor_ID(I0025 vendor_ID) {
		this.vendor_ID = vendor_ID;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public I0001 getProduct_ID() {
		return product_ID;
	}

	public void setProduct_ID(I0001 product_ID) {
		this.product_ID = product_ID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

}