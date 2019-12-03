package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import com.inacsys.domain.ATransaction;
import com.inacsys.domain.PurchaseOrder;

import java.util.Date;


/**
 * The persistent class for the expense_accounts_payments database table.
 * 
 */
@Entity
@Table(name="expense_accounts_payments")
@NamedQuery(name="ExpenseAccountsPayment.findAll", query="SELECT e FROM ExpenseAccountsPayment e")
public class ExpenseAccountsPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expense_accounts_payments_ID;

	private String status;
	
	private String description;
	
	@Column(name="account_type")
	private String accountType;

	@Column(name="total_amount")
	private String totalAmount;
	
	@Column(name="taxes")
	private String taxes;

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

	private String client_ID;
	
	//bi-directional many-to-one association to ExpenseTransaction
	@ManyToOne
	@JoinColumn(name="expense_transaction_ID")
	private ExpenseTransaction expenseTransaction;

	public ExpenseAccountsPayment() {
	}

	public ExpenseAccountsPayment(ATransaction atrans,String client_ID,ExpenseTransaction Fkey,String activeStatus) {
		this.expenseTransaction = Fkey;
        this.totalAmount = atrans.getAmount();
        this.status = activeStatus;
        this.description = atrans.getDescription();
        this.accountType = atrans.getToAccount();
        this.gstAmount=atrans.getGstAmount();
		this.taxes = atrans.getTaxes();
		this.taxPercentage=atrans.getPercentageValue();
		this.taxAmount=atrans.getPercentageAmount();
		this.igstType=atrans.getGstType();
		this.cgstType=atrans.getCgstType();
		this.sgstType=atrans.getSgstType();
		this.cgstAmount=atrans.getCgstAmount();
		this.sgstAmount=atrans.getSgstAmount();
		this.client_ID=client_ID;
	}
	
	public ExpenseAccountsPayment(PurchaseOrder order, String activeStatus, ExpenseTransaction expenseTransaction,String clientID) { 
		this.expenseTransaction=expenseTransaction;
		this.totalAmount=order.getNetAmount();
		this.status = activeStatus;
		this.accountType = "Purchases";
        this.status = activeStatus;
		this.taxes = "";
		this.client_ID=clientID;
	}

	
	public String getTaxes() {
		return taxes;
	}

	public void setTaxes(String taxes) {
		this.taxes = taxes;
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

	public String getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(String gstAmount) {
		this.gstAmount = gstAmount;
	}

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getExpense_accounts_payments_ID() {
		return this.expense_accounts_payments_ID;
	}

	public void setExpense_accounts_payments_ID(int expense_accounts_payments_ID) {
		this.expense_accounts_payments_ID = expense_accounts_payments_ID;
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
	
	public ExpenseTransaction getExpenseTransaction() {
		return this.expenseTransaction;
	}

	public void setExpenseTransaction(ExpenseTransaction expenseTransaction) {
		this.expenseTransaction = expenseTransaction;
	}

}