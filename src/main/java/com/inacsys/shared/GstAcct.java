package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import com.inacsys.domain.ATransaction;

import java.util.Date;


/**
 * The persistent class for the gst_acct database table.
 * 
 */
@Entity
@Table(name="gst_acct")
@NamedQuery(name="GstAcct.findAll", query="SELECT g FROM GstAcct g")
public class GstAcct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="account_name")
	private String accountName;

	private String balance;

	private String client_ID;

	@Column(name="credit_amount")
	private String creditAmount;

	@Column(name="debit_amount")
	private String debitAmount;

	//bi-directional many-to-one association to SalesTransaction
	@ManyToOne
	@JoinColumn(name="sales_transaction_ID")
	private SalesTransaction salestransaction;
	
	//bi-directional many-to-one association to ExpenseTransaction
	@ManyToOne
	@JoinColumn(name="expense_transaction_ID")
	private ExpenseTransaction expenseTransaction;

	//bi-directional many-to-one association to ChartOfAccount
	@ManyToOne
	@JoinColumn(name="has_coa_id")
	private ChartOfAccount chartOfAccount;

	@Column(name="invoice_number")
	private String invoiceNumber;

	@Column(name="payee_name")
	private String payeeName;

	private String status;

	@Column(name="sub_account")
	private String subAccount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tranDate;

	@Column(name="transaction_type")
	private String transactionType;
	
	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;
	
	private String account;

	public GstAcct() {
	}

	public GstAcct(SalesTransaction salestransaction,ATransaction aTransaction, ChartOfAccount chartofaccount) {
		this.tranDate=aTransaction.getBillDate();
		this.createdDate=new Date();
		this.invoiceNumber=aTransaction.getTransactionNo();
		this.accountName=aTransaction.getAccounts();
		this.transactionType=aTransaction.getTransactionType();
		this.debitAmount=aTransaction.getDebitAmount();
		this.creditAmount=aTransaction.getCreditAmount();
		this.balance=aTransaction.getBalance();
		this.subAccount=aTransaction.getDetailName();
		this.account=aTransaction.getCategoryType();
		this.status=aTransaction.getStatus();
		this.salestransaction=salestransaction;
		this.client_ID=aTransaction.getClientID();
		this.chartOfAccount=chartofaccount;
		this.payeeName=aTransaction.getCustomerName();
	}

	public GstAcct(ExpenseTransaction expenseTransaction,ATransaction aTransaction, ChartOfAccount coas1) {
		this.tranDate=aTransaction.getBillDate();
		this.createdDate=new Date();
		this.invoiceNumber=aTransaction.getRefNo();
		this.accountName=aTransaction.getAccounts();
		this.transactionType=aTransaction.getTransactionType();
		this.debitAmount=aTransaction.getDebitAmount();
		this.creditAmount=aTransaction.getCreditAmount();
		this.balance=aTransaction.getBalance();
		this.subAccount=aTransaction.getDetailName();
		this.account=aTransaction.getCategoryType();
		this.status=aTransaction.getStatus();
		this.expenseTransaction=expenseTransaction;
		this.client_ID=aTransaction.getClientID();
		this.chartOfAccount=coas1;
		this.payeeName=aTransaction.getVendorName();
	}

	public GstAcct(SalesTransaction salestransaction, ATransaction aTransaction) {
		this.tranDate=new Date();
		this.createdDate=new Date();
		this.invoiceNumber=aTransaction.getTransactionNo();
		this.accountName=aTransaction.getAccounts();
		this.transactionType=aTransaction.getTransactionType();
		this.debitAmount=aTransaction.getDebitAmount();
		this.creditAmount=aTransaction.getCreditAmount();
		this.balance=aTransaction.getBalance();
		this.subAccount=aTransaction.getDetailName();
		this.account=aTransaction.getCategoryType();
		this.status=aTransaction.getStatus();
		this.salestransaction=salestransaction;
		this.client_ID=aTransaction.getClientID();
		this.payeeName=aTransaction.getPayeeName();
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBalance() {
		return this.balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getClient_ID() {
		return this.client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public String getCreditAmount() {
		return this.creditAmount;
	}

	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getDebitAmount() {
		return this.debitAmount;
	}

	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}

	public ChartOfAccount getChartOfAccount() {
		return chartOfAccount;
	}

	public void setChartOfAccount(ChartOfAccount chartOfAccount) {
		this.chartOfAccount = chartOfAccount;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getPayeeName() {
		return this.payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public SalesTransaction getSalestransaction() {
		return salestransaction;
	}

	public void setSalestransaction(SalesTransaction salestransaction) {
		this.salestransaction = salestransaction;
	}

	public ExpenseTransaction getExpenseTransaction() {
		return expenseTransaction;
	}

	public void setExpenseTransaction(ExpenseTransaction expenseTransaction) {
		this.expenseTransaction = expenseTransaction;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubAccount() {
		return this.subAccount;
	}

	public void setSubAccount(String subAccount) {
		this.subAccount = subAccount;
	}

	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}