package com.inacsys.shared;

import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name = "sales_quote")
@NamedQuery(name="SalesQuote.findAll", query="SELECT c FROM SalesQuote c")
public class SalesQuote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sales_quote_id")
	private int sales_quote_ID;
	private String sales_quote_number;
	private String approvalStatus;
	private String status;
	private Date createdDate;
	private String customerName;
	private String telephone;
	private String mobileNumber;
	private String email;
	private String businessName;
	private String address;
	private String client_ID;
	private String user_ID;
	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="salesquote", cascade = CascadeType.ALL , fetch = FetchType.EAGER) 
	private Set<SalesQuoteDetails> salesquotedetails;

	public SalesQuote() {
	}
	 public SalesQuote(String sales_quote_number,String approvalStatus,String status,Date createdDate,String 
			 customerName,String telephone,String mobileNumber,String email,String businessName,String address,String client_ID,String userID) {
		 
	        this.sales_quote_number = sales_quote_number;//1
	        this.approvalStatus = approvalStatus;
	        this.status = status;
	        this.createdDate=createdDate;
	        this.customerName = customerName;
	        this.telephone = telephone;
	        this.mobileNumber = mobileNumber;
	        this.email = email;
	        this.businessName = businessName;
	        this.address = address; 
	        this.client_ID = client_ID;
	        this.user_ID = userID;
	    }

	 	public int getSales_quote_ID() {
			return sales_quote_ID;
		}

		public void setSales_quote_ID(int sales_quote_ID) {
			this.sales_quote_ID = sales_quote_ID;
		}
		
		public String getSales_quote_number() {
			return sales_quote_number;
		}

		public void setSales_quote_number(String sales_quote_number) {
			this.sales_quote_number = sales_quote_number;
		}
		
		public String getApprovalStatus() {
			return approvalStatus;
		}

		public void setApprovalStatus(String approvalStatus) {
			this.approvalStatus = approvalStatus;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getBusinessName() {
			return businessName;
		}

		public void setBusinessName(String businessName) {
			this.businessName = businessName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getClient_ID() {
			return client_ID;
		}

		public void setClient_ID(String client_ID) {
			this.client_ID = client_ID;
		}

		
		public String getUser_ID() {
			return user_ID;
		}
		public void setUser_ID(String user_ID) {
			this.user_ID = user_ID;
		}
		public Set<SalesQuoteDetails> getSalesquotedetails() {
		return salesquotedetails;
		}

		public void setSalesquotedetails(Set<SalesQuoteDetails> salesquotedetails) {
		this.salesquotedetails = salesquotedetails;
		}

		public SalesQuoteDetails addProduct(SalesQuoteDetails salesquotedetails) {
		getSalesquotedetails().add(salesquotedetails);
		salesquotedetails.setSalesquote(this);
		return salesquotedetails;
		}

		public SalesQuoteDetails removeProduct(SalesQuoteDetails salesquotedetails) {
		getSalesquotedetails().remove(salesquotedetails);
		salesquotedetails.setSalesquote(null);
		return salesquotedetails;
	}

}