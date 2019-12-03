package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Value;

import com.inacsys.domain.ATransaction;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.managedBean.QuickSaleMB;
import com.inacsys.util.Util;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the sales_accounts_payments database table.
 * 
 */
@Entity
@Table(name="sales_accounts_payments")
@NamedQuery(name="SalesAccountsPayment.findAll", query="SELECT s FROM SalesAccountsPayment s")
public class SalesAccountsPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sales_account_payments_ID")
	private int sales_account_payments_ID;
	
	@Column(name="product_name")
	private String productName;
	
	private String quantity;

	private String rate;

	@Column(name="total_amount")
	private String totalAmount;
	
	@Column(name="taxes")
	private String taxes;

	private String status;
	
	//bi-directional many-to-one association to SalesTransaction
	@ManyToOne
	@JoinColumn(name="sales_transaction_ID")
	private SalesTransaction salestransaction;
	
	@Column(name="IGST_type")
	private String igstType;
	
	@Column(name="CGST_type")
	private String cgstType;
	
	@Column(name="SGST_type")
	private String sgstType;

	@Column(name="tax_amount")
	private String taxAmount;
	
	@Column(name="cgst_amount")
	private String cgstAmount;
	
	@Column(name="sgst_amount")
	private String sgstAmount;

	@Column(name="tax_percentage")
	private String taxPercentage;
	
	@Column(name="gst_amount")
	private String gstAmount;
	
	public SalesAccountsPayment() {
	}
	
	public SalesAccountsPayment(ATransaction atrans,SalesTransaction salestransaction,String status) {
		this.salestransaction = salestransaction;
		this.status = status;
		this.productName = atrans.getProductName();
		this.quantity = atrans.getQuantity();
		this.rate = atrans.getRate();
		this.totalAmount=atrans.getAmount();
		this.gstAmount=atrans.getGstAmount();
		this.taxes = atrans.getTaxes();
		this.taxPercentage=atrans.getPercentageValue();
		this.taxAmount=atrans.getPercentageAmount();
		this.igstType=atrans.getGstType();
		this.cgstType=atrans.getCgstType();
		this.sgstType=atrans.getSgstType();
		this.cgstAmount=atrans.getCgstAmount();
		this.sgstAmount=atrans.getSgstAmount();
	}
	
	public SalesAccountsPayment(String clientID,PurchaseOrder purchase,SalesTransaction salestransaction) {
		this.productName = purchase.getProduct_name();
		this.salestransaction = salestransaction;
		this.quantity = purchase.getQuantity();
		this.rate = purchase.getSellingPrice();
		this.totalAmount = purchase.getTotalPrice();
		this.status = "Active";
	}

	public String getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(String gstAmount) {
		this.gstAmount = gstAmount;
	}

	public String getIgstType() {
		return igstType;
	}

	public void setIgstType(String igstType) {
		this.igstType = igstType;
	}

	public String getCgstType() {
		return cgstType;
	}

	public void setCgstType(String cgstType) {
		this.cgstType = cgstType;
	}

	public String getSgstType() {
		return sgstType;
	}

	public void setSgstType(String sgstType) {
		this.sgstType = sgstType;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getCgstAmount() {
		return cgstAmount;
	}

	public void setCgstAmount(String cgstAmount) {
		this.cgstAmount = cgstAmount;
	}

	public String getSgstAmount() {
		return sgstAmount;
	}

	public void setSgstAmount(String sgstAmount) {
		this.sgstAmount = sgstAmount;
	}

	public String getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(String taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public String getTaxes() {
		return taxes;
	}

	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}

	public int getSales_account_payments_ID() {
		return this.sales_account_payments_ID;
	}

	public void setSales_account_payments_ID(int sales_account_payments_ID) {
		this.sales_account_payments_ID = sales_account_payments_ID;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getRate() {
		return this.rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public SalesTransaction getSalestransaction() {
		return salestransaction;
	}

	public void setSalestransaction(SalesTransaction salestransaction) {
		this.salestransaction = salestransaction;
	}

	
}