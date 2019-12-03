package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import com.inacsys.domain.ATransaction;
import com.inacsys.domain.AccountsDatabean;
import com.inacsys.domain.PurchaseOrder;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the journal_entry database table.
 * 
 */
@Entity
@Table(name="journal_entry")
@NamedQuery(name="JournalEntry.findAll", query="SELECT j FROM JournalEntry j")
public class JournalEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="journal_entry_id")
	private int journalEntryId;

	private String client_ID;

	@Column(name="credit_amount")
	private String creditAmount;

	@Column(name="credit_particular")
	private String creditParticular;

	@Column(name="debit_amount")
	private String debitAmount;

	@Column(name="debit_particular")
	private String debitParticular;

	@Column(name="invoice_number")
	private String invoiceNumber;

	@Column(name="payee_name")
	private String payeeName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tranDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="transaction_type")
	private String transactionType;
	
	//bi-directional many-to-one association to SalesTransaction
	@ManyToOne
	@JoinColumn(name="sales_transaction_ID")
	private SalesTransaction salesTransaction;
	
	//bi-directional many-to-one association to ExpenseTransaction
	@ManyToOne
	@JoinColumn(name="expense_transaction_ID")
	private ExpenseTransaction expenseTransaction;
	
	private String status;

	public JournalEntry(){
	}
	
	public JournalEntry(PurchaseOrder purchaseOrder){
		this.tranDate = purchaseOrder.getOrderDate();
		this.createdDate = purchaseOrder.getOrderDate();
		this.debitParticular = purchaseOrder.getDebitParticular();
		this.debitAmount = purchaseOrder.getCrosstotal1();
		this.creditParticular = purchaseOrder.getCustomerName();
		this.creditAmount = purchaseOrder.getCrosstotal1();
		this.client_ID = purchaseOrder.getClientID();
		this.invoiceNumber = purchaseOrder.getSalesIdReference();
		this.transactionType = purchaseOrder.getTransactionType();
	}
	
	public JournalEntry(ATransaction aTransaction, String clientID,String debitParticular, String creditParticular,SalesTransaction salesTransaction, String activeStatus) {
		this.tranDate = aTransaction.getBillDate();
		this.createdDate = aTransaction.getBillDate();
		this.payeeName=aTransaction.getCustomerName();
		this.debitParticular=debitParticular;
		this.debitAmount = aTransaction.getCurrencyAmount();
		this.creditParticular = creditParticular;
		this.creditAmount = aTransaction.getCurrencyAmount();
		this.invoiceNumber = aTransaction.getTransactionNo();
		this.transactionType = aTransaction.getTransactionType();
		this.salesTransaction = salesTransaction;
		this.client_ID = clientID;
		this.status = activeStatus;
	}
	
	public JournalEntry(ATransaction aTransaction, String clientID,String debitParticular, String creditParticular, String creditAmount,SalesTransaction salesTransaction) {
		this.tranDate = aTransaction.getBillDate();
		this.createdDate = aTransaction.getBillDate();
		this.payeeName=aTransaction.getCustomerName();
		this.debitParticular=debitParticular;
		this.debitAmount = aTransaction.getCurrencyAmount();
		this.creditParticular = creditParticular;
		this.creditAmount = creditAmount;
		this.invoiceNumber = aTransaction.getTransactionNo();
		this.transactionType = aTransaction.getTransactionType();
		this.salesTransaction = salesTransaction;
		this.client_ID = clientID;
		this.status = aTransaction.getStatus();
	}

	public JournalEntry(AccountsDatabean accountsDatabean,String debitParticular,String creditParticular) {
		this.tranDate = accountsDatabean.getDate();
		this.createdDate = accountsDatabean.getDate();
		this.debitParticular=debitParticular;
		this.debitAmount = accountsDatabean.getBalance();
		this.creditParticular = creditParticular;
		this.creditAmount =  accountsDatabean.getBalance();
		this.transactionType = accountsDatabean.getTransactionType();
		this.client_ID = accountsDatabean.getClientID();
		this.status = accountsDatabean.getStatus();
	}

	public JournalEntry(PurchaseOrder purchaseOrder, String clientID,String debitParticular, String creditParticular,SalesTransaction salesTransaction, String activeStatus) {
		this.tranDate = purchaseOrder.getOrderDate();
		this.createdDate = purchaseOrder.getOrderDate();
		this.payeeName=purchaseOrder.getCustomername();
		this.debitParticular=debitParticular;
		this.debitAmount = purchaseOrder.getCurrencyAmount();
		this.creditParticular = creditParticular;
		this.creditAmount = purchaseOrder.getCurrencyAmount();
		this.invoiceNumber = purchaseOrder.getSalesIdReference();
		this.transactionType = "Sales Invoice";
		this.salesTransaction = salesTransaction;
		this.client_ID = clientID;
		this.status = activeStatus;
	}

	public JournalEntry(ATransaction aTransaction, String creditParticular,String debitParticular,ExpenseTransaction expenseTransaction,String debitAmount) {
		this.tranDate = aTransaction.getBillDate();
		this.createdDate = aTransaction.getBillDate();
		this.payeeName = aTransaction.getVendorName();
		this.debitParticular = debitParticular;
		this.debitAmount = debitAmount;
		this.creditParticular = creditParticular;
		this.creditAmount = aTransaction.getCurrencyAmount();
		this.invoiceNumber = aTransaction.getRefNo();
		this.transactionType = aTransaction.getTransactionType();
		this.client_ID = aTransaction.getClientID();
		this.status = aTransaction.getStatus();
		this.expenseTransaction = expenseTransaction;
	}
	
	public JournalEntry(String debitParticular,String creditParticular,String creditAmount,ATransaction aTransaction,ExpenseTransaction expenseTransaction) {
		this.tranDate = aTransaction.getBillDate();
		this.createdDate = aTransaction.getBillDate();
		this.payeeName = aTransaction.getVendorName();
		this.debitParticular = debitParticular;
		this.debitAmount = aTransaction.getCurrencyAmount();
		this.creditParticular = creditParticular;
		this.creditAmount = creditAmount;
		this.invoiceNumber = aTransaction.getRefNo();
		this.transactionType = aTransaction.getTransactionType();
		this.client_ID = aTransaction.getClientID();
		this.status = aTransaction.getStatus();
		this.expenseTransaction = expenseTransaction;
	}

	public JournalEntry(String debitParticular,ExpenseTransaction expenseTransaction, ATransaction aTransaction,ATransaction atrans) {
		this.tranDate = new Date();
		this.createdDate = new Date();
		this.payeeName = aTransaction.getVendorName();
		this.debitParticular = debitParticular;
		this.debitAmount = atrans.getAmount();
		this.creditParticular = atrans.getToAccount();
		this.creditAmount = atrans.getAmount();
		this.invoiceNumber = aTransaction.getRefNo();
		this.transactionType = aTransaction.getTransactionType();
		this.client_ID = aTransaction.getClientID();
		this.status = aTransaction.getStatus();
		this.expenseTransaction = expenseTransaction;
	}

	public JournalEntry(ATransaction aTransaction, String clientID,String debitParticular, String creditParticular,ExpenseTransaction expenseTrans) {
		this.tranDate = aTransaction.getBillDate();
		this.createdDate = aTransaction.getBillDate();
		this.payeeName = aTransaction.getVendorName();
		this.debitParticular = debitParticular;
		this.debitAmount = aTransaction.getCurrencyAmount();
		this.creditParticular = creditParticular;
		this.creditAmount = aTransaction.getCurrencyAmount();
		this.invoiceNumber = aTransaction.getRefNo();
		this.transactionType = aTransaction.getTransactionType();
		this.client_ID = clientID;
		this.status = aTransaction.getStatus();
		this.expenseTransaction = expenseTrans;
	}

	public JournalEntry(ATransaction aTransaction, String activeStatus,ExpenseTransaction expenseTransaction,String clientID) {
		this.expenseTransaction = expenseTransaction;
		this.tranDate = aTransaction.getBillDate();
		this.createdDate = aTransaction.getBillDate();
		this.payeeName = aTransaction.getVendorName();
		this.debitParticular = "Purchases";
		this.debitAmount = aTransaction.getCurrencyAmount();
		this.creditParticular = "Accounts Payable (A/P)";
		this.creditAmount = aTransaction.getCurrencyAmount();
		this.invoiceNumber = aTransaction.getRefNo();
		this.transactionType = aTransaction.getTransactionType();
		this.client_ID = clientID;
		this.status = activeStatus;
	}

	public JournalEntry(ATransaction aTransaction, String accrecName,String creditParticular, SalesTransaction salestransaction,String creditAmount) {
		this.tranDate = aTransaction.getBillDate();
		this.createdDate = aTransaction.getBillDate();
		this.payeeName=aTransaction.getCustomerName();
		this.debitParticular=accrecName;
		this.debitAmount = aTransaction.getCurrencyAmount();
		this.creditParticular = creditParticular;
		this.creditAmount = creditAmount;
		this.invoiceNumber = aTransaction.getTransactionNo();
		this.transactionType = aTransaction.getTransactionType();
		this.salesTransaction = salestransaction;
		this.client_ID = aTransaction.getClientID();
		this.status = aTransaction.getStatus();
	}

	public JournalEntry(ATransaction aTransaction, String debitParticular,String creditParticular, String debitAmount,SalesTransaction salestransaction) {
		this.tranDate=aTransaction.getBillDate();
		this.createdDate = aTransaction.getBillDate();
		this.payeeName=aTransaction.getCustomerName();
		this.debitParticular=debitParticular;
		this.debitAmount = debitAmount;
		this.creditParticular = creditParticular;
		this.creditAmount = aTransaction.getReceiveAmount();
		this.invoiceNumber = aTransaction.getTransactionNo();
		this.transactionType = aTransaction.getTransactionType();
		this.salesTransaction = salestransaction;
		this.client_ID = aTransaction.getClientID();
		this.status = aTransaction.getStatus();
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public ExpenseTransaction getExpenseTransaction() {
		return expenseTransaction;
	}

	public void setExpenseTransaction(ExpenseTransaction expenseTransaction) {
		this.expenseTransaction = expenseTransaction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SalesTransaction getSalesTransaction() {
		return salesTransaction;
	}

	public void setSalesTransaction(SalesTransaction salesTransaction) {
		this.salesTransaction = salesTransaction;
	}

	public int getJournalEntryId() {
		return this.journalEntryId;
	}

	public void setJournalEntryId(int journalEntryId) {
		this.journalEntryId = journalEntryId;
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

	public String getCreditParticular() {
		return this.creditParticular;
	}

	public void setCreditParticular(String creditParticular) {
		this.creditParticular = creditParticular;
	}

	public String getDebitAmount() {
		return this.debitAmount;
	}

	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}

	public String getDebitParticular() {
		return this.debitParticular;
	}

	public void setDebitParticular(String debitParticular) {
		this.debitParticular = debitParticular;
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