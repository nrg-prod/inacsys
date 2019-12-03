package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Value;

import com.inacsys.domain.ATransaction;
import com.inacsys.util.Util;

import java.util.Date;


/**
 * The persistent class for the account_receivable_acct database table.
 * 
 */
@Entity
@Table(name="account_receivable_acct")
@NamedQuery(name="AccountReceivableAcct.findAll", query="SELECT a FROM AccountReceivableAcct a")
public class AccountReceivableAcct implements Serializable {
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

	//bi-directional many-to-one association to ChartOfAccount
	@ManyToOne
	@JoinColumn(name="has_coa_id")
	private ChartOfAccount chartOfAccount;
	
	//bi-directional many-to-one association to SalesTransaction
	@ManyToOne
	@JoinColumn(name="sales_transaction_ID")
	private SalesTransaction salestransaction;
	
	//bi-directional many-to-one association to ExpenseTransaction
	@ManyToOne
	@JoinColumn(name="expense_transaction_ID")
	private ExpenseTransaction expenseTransaction;
	
	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;
	
	private String account;
	
	public AccountReceivableAcct() {
	}

	public AccountReceivableAcct(ATransaction aTransaction, String clientID,String accountName,String categoryType,String subaccount,SalesTransaction salesTransaction) {
		this.tranDate=aTransaction.getBillDate();
		this.createdDate=new Date();
		this.dueDate=aTransaction.getDueDate();
		this.accountName=accountName;
		this.payeeName=aTransaction.getCustomerName();
		this.transactionType=aTransaction.getTransactionType();
		this.invoiceNumber=aTransaction.getTransactionNo();
		this.debitAmount=aTransaction.getTransamount();
		this.creditAmount=aTransaction.getCreditAmount();
		this.balance=aTransaction.getTransamount();	
		this.status=aTransaction.getStatus();
		this.subAccount=subaccount;
		this.account=categoryType;
		this.salestransaction=salesTransaction;
		this.client_ID=clientID;
	}

	public AccountReceivableAcct(String clientID,ATransaction aTransaction,SalesTransaction salesTransaction,ChartOfAccount chartOfAccount,String categoryType) {
		this.tranDate=aTransaction.getBillDate();
		this.dueDate=aTransaction.getDueDate();
		this.createdDate=new Date();
		this.accountName=aTransaction.getAccounts();
		this.payeeName=aTransaction.getCustomerName();
		this.transactionType=aTransaction.getTransactionType();
		this.invoiceNumber=aTransaction.getTransactionNo();
		this.creditAmount=aTransaction.getReceiveAmount();
		this.balance=aTransaction.getTransamount();
		this.status=aTransaction.getStatus();
		this.subAccount=aTransaction.getAccountType();
		this.salestransaction=salesTransaction;
		this.account=categoryType;
		this.chartOfAccount=chartOfAccount;
		this.client_ID=clientID;
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

	public ExpenseTransaction getExpenseTransaction() {
		return expenseTransaction;
	}

	public void setExpenseTransaction(ExpenseTransaction expenseTransaction) {
		this.expenseTransaction = expenseTransaction;
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

	public ChartOfAccount getChartOfAccount() {
		return this.chartOfAccount;
	}

	public void setChartOfAccount(ChartOfAccount chartOfAccount) {
		this.chartOfAccount = chartOfAccount;
	}

	public SalesTransaction getSalestransaction() {
		return salestransaction;
	}

	public void setSalestransaction(SalesTransaction salestransaction) {
		this.salestransaction = salestransaction;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}