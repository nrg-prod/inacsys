package com.inacsys.shared;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.inacsys.domain.ATransaction;
import com.inacsys.domain.AccountsDatabean;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the openbal_equity_acct database table.
 * 
 */
@Entity
@Table(name="openbal_equity_acct")
@NamedQuery(name="OpenbalEquityAcct.findAll", query="SELECT o FROM OpenbalEquityAcct o")
public class OpenbalEquityAcct implements Serializable {
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
	
	//bi-directional many-to-one association to ExpenseTransaction
	@ManyToOne
	@JoinColumn(name="expense_transaction_ID")
	private ExpenseTransaction expenseTransaction;
	
	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;
	
	private String account;
	
	private String ref_account_name;

	public OpenbalEquityAcct() {
	}

	public OpenbalEquityAcct(AccountsDatabean accountsDatabean,ChartOfAccount chartofaccount, String account) {
		this.tranDate=accountsDatabean.getDate();
		this.createdDate=new Date();
		this.invoiceNumber=accountsDatabean.getName();
		this.accountName=accountsDatabean.getName();
		this.transactionType=accountsDatabean.getTransactionType();
		this.debitAmount=accountsDatabean.getDepositAmount();
		this.creditAmount=accountsDatabean.getCreditAmount();
		this.balance=accountsDatabean.getAccount_amount();
		this.subAccount=accountsDatabean.getDetailType();
		this.account=account;
		this.status=accountsDatabean.getStatus();
		this.client_ID=accountsDatabean.getClientID();
		this.ref_account_name=accountsDatabean.getAccount_name();
		this.chartOfAccount=chartofaccount;
	}

	public OpenbalEquityAcct(ATransaction atrans, ATransaction aTransaction,String categoryType,String detailType, ExpenseTransaction expenseTransaction) {
		BigDecimal tempamt=BigDecimal.valueOf(0);
		this.tranDate=aTransaction.getBillDate();
		this.createdDate=new Date();
		this.invoiceNumber=aTransaction.getRefNo();
		this.accountName=atrans.getToAccount();
		this.payeeName=aTransaction.getVendorName();
		this.transactionType=aTransaction.getTransactionType();
		this.debitAmount=atrans.getAmount();
		this.creditAmount=aTransaction.getCreditAmount();
		this.balance=tempamt.subtract(new BigDecimal(atrans.getAmount())).toString();
		this.subAccount=detailType;
		this.account=categoryType;
		this.status=aTransaction.getStatus();
		this.client_ID=aTransaction.getClientID();
		this.expenseTransaction=expenseTransaction;
		this.ref_account_name=atrans.getToAccount();
	}

	public OpenbalEquityAcct(String categoryType,String detailType, ATransaction atrans,ATransaction aTransaction, ExpenseTransaction expenseTransaction) {
		this.tranDate=aTransaction.getBillDate();
		this.createdDate=new Date();
		this.invoiceNumber=aTransaction.getRefNo();
		this.accountName=atrans.getToAccount();
		this.payeeName=aTransaction.getVendorName();
		this.transactionType=aTransaction.getTransactionType();
		this.creditAmount=atrans.getAmount();
		this.balance=atrans.getAmount();
		this.subAccount=detailType;
		this.account=categoryType;
		this.status=aTransaction.getStatus();
		this.client_ID=aTransaction.getClientID();
		this.ref_account_name=atrans.getToAccount();
		this.expenseTransaction=expenseTransaction;
	}

	public OpenbalEquityAcct(AccountsDatabean accountsDatabean,List<ChartOfAccount> accDeposit1) {
		this.tranDate=new Date();
		this.createdDate=new Date();
		this.accountName=accountsDatabean.getName();
		this.transactionType=accountsDatabean.getTransactionType();
		this.debitAmount=accountsDatabean.getDebitAmount();
		this.balance=accountsDatabean.getBalance();
		this.creditAmount=accountsDatabean.getCreditAmount();
		this.subAccount=accDeposit1.get(0).getAccountType().getDetailType();
		this.account=accDeposit1.get(0).getAccountType().getCategoryType();
		this.status=accountsDatabean.getAccountStatus();
		this.client_ID=accountsDatabean.getClientID();
		this.ref_account_name=accountsDatabean.getAccount_name();
	}

	public String getRef_account_name() {
		return ref_account_name;
	}

	public void setRef_account_name(String ref_account_name) {
		this.ref_account_name = ref_account_name;
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