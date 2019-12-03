package com.inacsys.shared;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.inacsys.domain.ATransaction;
import com.inacsys.domain.PurchaseOrder;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the expense_transaction database table.
 * 
 */
@Entity
@Table(name="expense_transaction")
@NamedQuery(name="ExpenseTransaction.findAll", query="SELECT e FROM ExpenseTransaction e")
public class ExpenseTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="expense_transaction_ID")
	private int expense_transaction_ID;
	
	@Column(name="approval_status")
	private String approvalStatus;

	@Column(name="bill_number")
	private String billNumber;

	private String client_ID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Column(name="mailing_address")
	private String mailingAddress;

	@Column(name="payment_method")
	private String paymentMethod;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	private String status;
	
	private String payterms;

	@Column(name="transaction_amount")
	private String transactionAmount;

	@Column(name="transaction_type")
	private String transactionType;

	@Temporal(TemporalType.DATE)
	@Column(name="updated_date")
	private Date updatedDate;

	private String name;
	
	private String code;
	
	@Column(name="payment_status")
	private String paymentStatus;
	
	@Column(name="balance_amount")
	private String balanceAmount;

	@Column(name="paid_amount")
	private String paidAmount;
	
	@Temporal(TemporalType.DATE)
	@Column(name="pay_date")
	private Date payDate;
	
	private String codeDescription;
	
	private String currencyAmount;
	
	private String currencybeforetaxAmount;
	
	private String currencyTaxAmount;
	
	private String placeofLocation;
	
	private String taxType;
	
	private String beforetaxAmount;
	
	private String taxAmount;
	
	private String baseCurrency;
	
	private String subTotalAmount;
	
	private String currencyType;
	
	@Column(name="from_account_type")
	private String fromAccountType;

	//bi-directional many-to-one association to ExpenseAccountsPayment
	@OneToMany(mappedBy="expenseTransaction",cascade = CascadeType.ALL)
	private Set<ExpenseAccountsPayment> expenseAccountsPayments;
	
	//bi-directional many-to-one association to JournalEntry
	@OneToMany(mappedBy="expenseTransaction",cascade = CascadeType.ALL)
	private Set<JournalEntry> journalEntries;
	
	//bi-directional many-to-one association to ExpenseCoa
	@OneToMany(mappedBy="expenseTransaction",cascade = CascadeType.ALL)
	private Set<ExpenseCoa> expenseCoas;
	
	//bi-directional many-to-one association to BankAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<AccountPayableAcct> accountPayableAccts;
	
	//bi-directional many-to-one association to BankAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<BankAcct> bankAccts;
	
	//bi-directional many-to-one association to OtherCurrentAssetsAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<OtherCurrentAssetsAcct> otherCurrentAssetsAccts;
		
	//bi-directional many-to-one association to OtherAssetsAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<OtherAssetsAcct> otherAssetsAccts;
		
	//bi-directional many-to-one association to FixedAssetsAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<FixedAssetsAcct> fixedAssetsAccts;
	
	//bi-directional many-to-one association to CreditCardAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<CreditCardAcct> creditCardAccts;
	
	//bi-directional many-to-one association to OtherCurrentLibAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<OtherCurrentLibAcct> othercurrentlibAccts;
		
	//bi-directional many-to-one association to NotesPayableAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<NotesPayableAcct> notespayableAccts;
	
	//bi-directional many-to-one association to EquityAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<EquityAcct> equityAccts;
	
	//bi-directional many-to-one association to IncomeAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<IncomeAcct> incomeAccts;
	
	//bi-directional many-to-one association to OtherIncomeAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<OtherIncomeAcct> otherIncomeAccts;
		
	//bi-directional many-to-one association to CogAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<CogAcct> cogAccts;
	
	//bi-directional many-to-one association to ExpensesAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<ExpensesAcct> expenseAccts;
		
	//bi-directional many-to-one association to OtherExpensesAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<OtherExpensesAcct> otherExpenseAccts;
	
	//bi-directional many-to-one association to OtherExpensesAcct
	@OneToMany(mappedBy="expenseTransaction", cascade = CascadeType.ALL) 
	private Set<OpenbalEquityAcct> openingBalEquityAccts;
	
	//bi-directional many-to-one association to SalesCoa
	@OneToMany(mappedBy="salestransaction", cascade = CascadeType.ALL) 
	private Set<GstAcct> gstAccts;

	public ExpenseTransaction(ATransaction aTransaction) {
		BigDecimal tempamt=BigDecimal.valueOf(0);
        this.transactionType = aTransaction.getTransactionType();
        this.name = aTransaction.getVendorName();
        this.billNumber= aTransaction.getRefNo();
        this.transactionAmount= aTransaction.getTotalAmount();
        this.paidAmount = "0";
        this.payDate = null;
        this.client_ID = aTransaction.getClientID();
        this.mailingAddress = aTransaction.getMailingAddress();
        this.dueDate = aTransaction.getDueDate();
        this.startDate = aTransaction.getBillDate();
        this.paymentMethod = aTransaction.getPaymentMode();
        this.status = aTransaction.getStatus();
        this.createdDate = new Date();
        this.updatedDate = null;
        this.approvalStatus = aTransaction.getApprovalStatus();
        this.code=aTransaction.getCode();
        this.codeDescription=aTransaction.getCodeDescription();
        this.paymentStatus=aTransaction.getPaymentStatus();
        this.fromAccountType=aTransaction.getFromAccount();
        if(aTransaction.getTransactionType().equalsIgnoreCase("Vendor Credit")){
        	this.balanceAmount=tempamt.subtract(new BigDecimal(aTransaction.getCurrencyAmount())).toString();
        }else if(aTransaction.getTransactionType().equalsIgnoreCase("Bill")){
        	this.balanceAmount=aTransaction.getCurrencyAmount();
        }else{
        	this.balanceAmount="0";
        }
        if(aTransaction.getTransactionType().equalsIgnoreCase("Vendor Credit")){
        	this.currencyAmount = tempamt.subtract(new BigDecimal(aTransaction.getCurrencyAmount())).toString();
        	this.currencybeforetaxAmount = tempamt.subtract(new BigDecimal(aTransaction.getCurrencybeforeTaxAmount())).toString();
			this.currencyTaxAmount = tempamt.subtract(new BigDecimal(aTransaction.getCurrencytaxAmount())).toString();
        }else{
        	this.currencyAmount = aTransaction.getCurrencyAmount();
        	this.currencybeforetaxAmount = aTransaction.getCurrencybeforeTaxAmount();
        	this.currencyTaxAmount = aTransaction.getCurrencytaxAmount();
        }
        this.placeofLocation = aTransaction.getLocation();
        this.taxType = aTransaction.getTaxType();
        this.beforetaxAmount = aTransaction.getBeforeTaxAmount();
        this.taxAmount = aTransaction.getTaxAmount();
        this.baseCurrency = aTransaction.getBaseCurrency();
        this.subTotalAmount = aTransaction.getSubTotalAmount();
        this.payterms = aTransaction.getTerms();
        this.currencyType = aTransaction.getCurrencyType();
	}
	
	public ExpenseTransaction() {
	}

	public ExpenseTransaction(PurchaseOrder purchaseOrder,String clientID) {
		this.transactionType = "Purchase Invoice";
        this.name = purchaseOrder.getVendor_phone_number();
        this.billNumber=purchaseOrder.getOrderNumber();
        this.transactionAmount=purchaseOrder.getTotalPrice();
        this.paidAmount = "0";
        this.payDate = null;
        this.startDate = purchaseOrder.getOrderDate();
        this.dueDate = purchaseOrder.getTargentDate();
        this.createdDate = new Date();
        this.status="Active";
        this.client_ID =clientID;
        this.paidAmount="0";
        this.paymentStatus="Unpaid";
        this.updatedDate = null;
        this.approvalStatus = "draft";
        this.balanceAmount = purchaseOrder.getCurrencyAmount();
        this.currencyAmount = purchaseOrder.getCurrencyAmount();
        this.currencybeforetaxAmount = purchaseOrder.getCurrencyAmount();
        this.currencyTaxAmount = "0";
        this.taxType = "Out of scope of Tax";
        this.beforetaxAmount = purchaseOrder.getCurrencyAmount();
        this.taxAmount = "0";
        this.baseCurrency = purchaseOrder.getBaseCurrency();
        this.subTotalAmount = purchaseOrder.getTotalPrice();
        this.currencyType = purchaseOrder.getCurrency();
	}
	
	
	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getPayterms() {
		return payterms;
	}

	public void setPayterms(String payterms) {
		this.payterms = payterms;
	}

	public Set<GstAcct> getGstAccts() {
		return gstAccts;
	}

	public void setGstAccts(Set<GstAcct> gstAccts) {
		this.gstAccts = gstAccts;
	}

	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public String getCurrencybeforetaxAmount() {
		return currencybeforetaxAmount;
	}

	public void setCurrencybeforetaxAmount(String currencybeforetaxAmount) {
		this.currencybeforetaxAmount = currencybeforetaxAmount;
	}

	public String getCurrencyTaxAmount() {
		return currencyTaxAmount;
	}

	public void setCurrencyTaxAmount(String currencyTaxAmount) {
		this.currencyTaxAmount = currencyTaxAmount;
	}

	public String getPlaceofLocation() {
		return placeofLocation;
	}

	public void setPlaceofLocation(String placeofLocation) {
		this.placeofLocation = placeofLocation;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public String getBeforetaxAmount() {
		return beforetaxAmount;
	}

	public void setBeforetaxAmount(String beforetaxAmount) {
		this.beforetaxAmount = beforetaxAmount;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getSubTotalAmount() {
		return subTotalAmount;
	}

	public void setSubTotalAmount(String subTotalAmount) {
		this.subTotalAmount = subTotalAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public int getExpense_transaction_ID() {
		return this.expense_transaction_ID;
	}

	public void setExpense_transaction_ID(int expense_transaction_ID) {
		this.expense_transaction_ID = expense_transaction_ID;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getBillNumber() {
		return this.billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
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

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getMailingAddress() {
		return this.mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransactionAmount() {
		return this.transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ExpenseAccountsPayment> getExpenseAccountsPayments() {
		return expenseAccountsPayments;
	}

	public void setExpenseAccountsPayments(
			Set<ExpenseAccountsPayment> expenseAccountsPayments) {
		this.expenseAccountsPayments = expenseAccountsPayments;
	}

	public ExpenseAccountsPayment addExpenseAccountsPayment(ExpenseAccountsPayment expenseAccountsPayment) {
		getExpenseAccountsPayments().add(expenseAccountsPayment);
		expenseAccountsPayment.setExpenseTransaction(this);

		return expenseAccountsPayment;
	}

	public ExpenseAccountsPayment removeExpenseAccountsPayment(ExpenseAccountsPayment expenseAccountsPayment) {
		getExpenseAccountsPayments().remove(expenseAccountsPayment);
		expenseAccountsPayment.setExpenseTransaction(null);

		return expenseAccountsPayment;
	}


	public Set<ExpenseCoa> getExpenseCoas() {
		return expenseCoas;
	}

	public void setExpenseCoas(Set<ExpenseCoa> expenseCoas) {
		this.expenseCoas = expenseCoas;
	}

	public ExpenseCoa addExpenseCoa(ExpenseCoa expenseCoa) {
		getExpenseCoas().add(expenseCoa);
		expenseCoa.setExpenseTransaction(this);

		return expenseCoa;
	}

	public ExpenseCoa removeExpenseCoa(ExpenseCoa expenseCoa) {
		getExpenseCoas().remove(expenseCoa);
		expenseCoa.setExpenseTransaction(null);

		return expenseCoa;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeDescription() {
		return codeDescription;
	}

	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

	public Set<AccountPayableAcct> getAccountPayableAccts() {
		return accountPayableAccts;
	}

	public void setAccountPayableAccts(Set<AccountPayableAcct> accountPayableAccts) {
		this.accountPayableAccts = accountPayableAccts;
	}

	public Set<BankAcct> getBankAccts() {
		return bankAccts;
	}

	public void setBankAccts(Set<BankAcct> bankAccts) {
		this.bankAccts = bankAccts;
	}

	public Set<OtherCurrentAssetsAcct> getOtherCurrentAssetsAccts() {
		return otherCurrentAssetsAccts;
	}

	public void setOtherCurrentAssetsAccts(
			Set<OtherCurrentAssetsAcct> otherCurrentAssetsAccts) {
		this.otherCurrentAssetsAccts = otherCurrentAssetsAccts;
	}

	public Set<OtherAssetsAcct> getOtherAssetsAccts() {
		return otherAssetsAccts;
	}

	public void setOtherAssetsAccts(Set<OtherAssetsAcct> otherAssetsAccts) {
		this.otherAssetsAccts = otherAssetsAccts;
	}

	public Set<FixedAssetsAcct> getFixedAssetsAccts() {
		return fixedAssetsAccts;
	}

	public void setFixedAssetsAccts(Set<FixedAssetsAcct> fixedAssetsAccts) {
		this.fixedAssetsAccts = fixedAssetsAccts;
	}

	public Set<CreditCardAcct> getCreditCardAccts() {
		return creditCardAccts;
	}

	public void setCreditCardAccts(Set<CreditCardAcct> creditCardAccts) {
		this.creditCardAccts = creditCardAccts;
	}

	public Set<OtherCurrentLibAcct> getOthercurrentlibAccts() {
		return othercurrentlibAccts;
	}

	public void setOthercurrentlibAccts(
			Set<OtherCurrentLibAcct> othercurrentlibAccts) {
		this.othercurrentlibAccts = othercurrentlibAccts;
	}

	public Set<NotesPayableAcct> getNotespayableAccts() {
		return notespayableAccts;
	}

	public void setNotespayableAccts(Set<NotesPayableAcct> notespayableAccts) {
		this.notespayableAccts = notespayableAccts;
	}

	public Set<EquityAcct> getEquityAccts() {
		return equityAccts;
	}

	public void setEquityAccts(Set<EquityAcct> equityAccts) {
		this.equityAccts = equityAccts;
	}

	public Set<IncomeAcct> getIncomeAccts() {
		return incomeAccts;
	}

	public void setIncomeAccts(Set<IncomeAcct> incomeAccts) {
		this.incomeAccts = incomeAccts;
	}

	public Set<OtherIncomeAcct> getOtherIncomeAccts() {
		return otherIncomeAccts;
	}

	public void setOtherIncomeAccts(Set<OtherIncomeAcct> otherIncomeAccts) {
		this.otherIncomeAccts = otherIncomeAccts;
	}

	public Set<CogAcct> getCogAccts() {
		return cogAccts;
	}

	public void setCogAccts(Set<CogAcct> cogAccts) {
		this.cogAccts = cogAccts;
	}

	public Set<ExpensesAcct> getExpenseAccts() {
		return expenseAccts;
	}

	public void setExpenseAccts(Set<ExpensesAcct> expenseAccts) {
		this.expenseAccts = expenseAccts;
	}

	public Set<OtherExpensesAcct> getOtherExpenseAccts() {
		return otherExpenseAccts;
	}

	public void setOtherExpenseAccts(Set<OtherExpensesAcct> otherExpenseAccts) {
		this.otherExpenseAccts = otherExpenseAccts;
	}

	public Set<OpenbalEquityAcct> getOpeningBalEquityAccts() {
		return openingBalEquityAccts;
	}

	public void setOpeningBalEquityAccts(Set<OpenbalEquityAcct> openingBalEquityAccts) {
		this.openingBalEquityAccts = openingBalEquityAccts;
	}

	public Set<JournalEntry> getJournalEntries() {
		return journalEntries;
	}

	public void setJournalEntries(Set<JournalEntry> journalEntries) {
		this.journalEntries = journalEntries;
	}

	public String getFromAccountType() {
		return fromAccountType;
	}

	public void setFromAccountType(String fromAccountType) {
		this.fromAccountType = fromAccountType;
	}
	
}