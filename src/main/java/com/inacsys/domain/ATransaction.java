package com.inacsys.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.model.UploadedFile;

import com.inacsys.shared.SalesTransaction;
import com.inacsys.shared.Transaction;

public class ATransaction {
	public Date date;
	public String paymentMode;
	public String particular;
	public String amount;
	public String note;
	public String cardno;
	public String bankname;
	public String chequeno;
	public Date chequedate;
	public String transactionNo;
	public Date fromdate;
	public Date todate;
	public List<Transaction> view = null;
	public String status;
	public String cpaymentMode;
	public String cparticular;
	public String camount;
	public String cnote;
	public String accountType;
	public String description;
	public String maddress;
	public Date duedate;
	public Date paydate;
	public String ctransactionType;
	private List<ATransaction> debitlist=null;
	private UploadedFile transFile;
	public String filepath;
	private List<String> detailTypes=null;
	private List<String> accountTypes=null;
	private String balance;
	private String accounts;
	private String detailName;
	private String currencyType;
	private String currency;
	private String baseCurrency;
	private int salesPaymentID;
    private List<ATransaction> productList=new ArrayList<ATransaction>();
	/* Prema*/
	private List<String> customerNameList=null;
	private List<String> productNameList=null;
	private String serialNo;
	private String customerName;
	private String customerEmailId;
	private String customerBillingAddress;
	private String productName;
	private String quantity;
	private String rate;
	public String transamount;
	public String terms;
	private String balAmount;
	private int transactionID;
	private String payAmount;
	private String receiveAmount;
	private String creditmemoNo;
	private String creditmemoBalance;
	private List<ATransaction> paymentdataTableList=null;
	public String transStatus;
	public String approvalStatus;
	public String debitParticular;
	public String creditParticular;
	public String debitAmount;
	public String creditAmount;
    private String reportPeriod;
    private String editTransAmount;
    private String totalAmount;
    private String debitTotal;
    private String creditTotal;
    private int paymentId;
    private String currencyAmount;
    private String location;
    private String taxType;
    private String taxes;
    private String percentIGSTFlag="none";
    private String percentGSTFlag="none";
    private String percentageValue;
    private String percentageAmount;
    private String gstAmount;
    private String cgstAmount;
    private String sgstAmount;
    private String subTotalAmount;
    private String taxAmount;
    private String currencytaxAmount;
    private String gstType;
    private String cgstType;
    private String sgstType;
    private String beforeTaxAmount;
    private String currencyIGSTAmount;
    private String currencyCGSTAmount;
    private String currencySGSTAmount;
    private String currencybeforeTaxAmount;
    private String categoryType;
    private String gstStatus;
    private String editStatus;
    private String removeStatus;
    private String country;
    private String editBeforeTaxAmount;
    private SalesTransaction salestransaction;   
    public List<Integer> removeList=null;
    public List<ATransaction> expenseTransactionDataList=null;
    public List<ATransaction> expenseaccPaymentDataList=null;
    
    
    public List<ATransaction> getExpenseaccPaymentDataList() {
		return expenseaccPaymentDataList;
	}

	public void setExpenseaccPaymentDataList(List<ATransaction> expenseaccPaymentDataList) {
		this.expenseaccPaymentDataList = expenseaccPaymentDataList;
	}

	public List<ATransaction> getExpenseTransactionDataList() {
		return expenseTransactionDataList;
	}

	public void setExpenseTransactionDataList(List<ATransaction> expenseTransactionDataList) {
		this.expenseTransactionDataList = expenseTransactionDataList;
	}

	public List<Integer> getRemoveList() {
		return removeList;
	}

	public void setRemoveList(List<Integer> removeList) {
		this.removeList = removeList;
	}
	public String getRemoveStatus() {
		return removeStatus;
	}

	public void setRemoveStatus(String removeStatus) {
		this.removeStatus = removeStatus;
	}

	public SalesTransaction getSalestransaction() {
		return salestransaction;
	}

	public void setSalestransaction(SalesTransaction salestransaction) {
		this.salestransaction = salestransaction;
	}

	public String getEditBeforeTaxAmount() {
		return editBeforeTaxAmount;
	}

	public void setEditBeforeTaxAmount(String editBeforeTaxAmount) {
		this.editBeforeTaxAmount = editBeforeTaxAmount;
	}

	public String getEditStatus() {
		return editStatus;
	}

	public void setEditStatus(String editStatus) {
		this.editStatus = editStatus;
	}

	public String getGstStatus() {
		return gstStatus;
	}

	public void setGstStatus(String gstStatus) {
		this.gstStatus = gstStatus;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getCurrencybeforeTaxAmount() {
		return currencybeforeTaxAmount;
	}

	public void setCurrencybeforeTaxAmount(String currencybeforeTaxAmount) {
		this.currencybeforeTaxAmount = currencybeforeTaxAmount;
	}

	public String getCurrencyIGSTAmount() {
		return currencyIGSTAmount;
	}

	public void setCurrencyIGSTAmount(String currencyIGSTAmount) {
		this.currencyIGSTAmount = currencyIGSTAmount;
	}

	public String getCurrencyCGSTAmount() {
		return currencyCGSTAmount;
	}

	public void setCurrencyCGSTAmount(String currencyCGSTAmount) {
		this.currencyCGSTAmount = currencyCGSTAmount;
	}

	public String getCurrencySGSTAmount() {
		return currencySGSTAmount;
	}

	public void setCurrencySGSTAmount(String currencySGSTAmount) {
		this.currencySGSTAmount = currencySGSTAmount;
	}

	public String getBeforeTaxAmount() {
		return beforeTaxAmount;
	}

	public void setBeforeTaxAmount(String beforeTaxAmount) {
		this.beforeTaxAmount = beforeTaxAmount;
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

	public String getGstType() {
		return gstType;
	}

	public void setGstType(String gstType) {
		this.gstType = gstType;
	}

	public String getCurrencytaxAmount() {
		return currencytaxAmount;
	}

	public void setCurrencytaxAmount(String currencytaxAmount) {
		this.currencytaxAmount = currencytaxAmount;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getSubTotalAmount() {
		return subTotalAmount;
	}

	public void setSubTotalAmount(String subTotalAmount) {
		this.subTotalAmount = subTotalAmount;
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

	public String getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(String gstAmount) {
		this.gstAmount = gstAmount;
	}

	public String getPercentageAmount() {
		return percentageAmount;
	}

	public void setPercentageAmount(String percentageAmount) {
		this.percentageAmount = percentageAmount;
	}

	public String getPercentageValue() {
		return percentageValue;
	}

	public void setPercentageValue(String percentageValue) {
		this.percentageValue = percentageValue;
	}

	public String getPercentIGSTFlag() {
		return percentIGSTFlag;
	}

	public void setPercentIGSTFlag(String percentIGSTFlag) {
		this.percentIGSTFlag = percentIGSTFlag;
	}

	public String getPercentGSTFlag() {
		return percentGSTFlag;
	}

	public void setPercentGSTFlag(String percentGSTFlag) {
		this.percentGSTFlag = percentGSTFlag;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public String getTaxes() {
		return taxes;
	}

	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}

	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getDebitTotal() {
		return debitTotal;
	}

	public void setDebitTotal(String debitTotal) {
		this.debitTotal = debitTotal;
	}

	public String getCreditTotal() {
		return creditTotal;
	}

	public void setCreditTotal(String creditTotal) {
		this.creditTotal = creditTotal;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getEditTransAmount() {
		return editTransAmount;
	}

	public void setEditTransAmount(String editTransAmount) {
		this.editTransAmount = editTransAmount;
	}

	public String getReportPeriod() {
		return reportPeriod;
	}

	public void setReportPeriod(String reportPeriod) {
		this.reportPeriod = reportPeriod;
	}

	
	/* John  Clinton*/
	
	public String transactionType;
	public String vendorName;
	public String mailingAddress;
	public String Terms;
	public String refNo;
	public Date billDate;
	public Date dueDate;
	public String fromAccount;
	public String toAccount;
	
	
	private List<String> vendorlist=null;
	private List<String> allList=null;
	private List<String> accountList=null;
	private List<String> filteredaccountList=null;
	
	public String accountBalance;
	public String PayeeName;
	public String expenseResourcetype;
	public List<ATransaction> expenseTransactionlist=null;
	public int index;
	
	/*neela*/
	
	public String paymentStatus;
	private String initialbalance;
	
	private String clientID;
	private String userID;
	private String code;
	private String codeDescription;
	
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDebitParticular() {
		return debitParticular;
	}

	public void setDebitParticular(String debitParticular) {
		this.debitParticular = debitParticular;
	}

	public String getCreditParticular() {
		return creditParticular;
	}

	public void setCreditParticular(String creditParticular) {
		this.creditParticular = creditParticular;
	}

	public String getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}

	public String getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getCodeDescription() {
		return codeDescription;
	}

	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getSalesPaymentID() {
		return salesPaymentID;
	}

	public void setSalesPaymentID(int salesPaymentID) {
		this.salesPaymentID = salesPaymentID;
	}

	public List<ATransaction> getProductList() {
		return productList;
	}

	public void setProductList(List<ATransaction> productList) {
		this.productList = productList;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTerms() {
		return Terms;
	}

	public void setTerms(String terms) {
		Terms = terms;
	}

	public List<String> getAllList() {
		return allList;
	}

	public void setAllList(List<String> allList) {
		this.allList = allList;
	}

	public List<String> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<String> accountList) {
		this.accountList = accountList;
	}

	public List<String> getFilteredaccountList() {
		return filteredaccountList;
	}

	public void setFilteredaccountList(List<String> filteredaccountList) {
		this.filteredaccountList = filteredaccountList;
	}

	public String getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getPayeeName() {
		return PayeeName;
	}

	public void setPayeeName(String payeeName) {
		PayeeName = payeeName;
	}

	public String getExpenseResourcetype() {
		return expenseResourcetype;
	}

	public void setExpenseResourcetype(String expenseResourcetype) {
		this.expenseResourcetype = expenseResourcetype;
	}

	public List<ATransaction> getExpenseTransactionlist() {
		return expenseTransactionlist;
	}

	public void setExpenseTransactionlist(List<ATransaction> expenseTransactionlist) {
		this.expenseTransactionlist = expenseTransactionlist;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getInitialbalance() {
		return initialbalance;
	}

	public void setInitialbalance(String initialbalance) {
		this.initialbalance = initialbalance;
	}

	public List<ATransaction> getPaymentdataTableList() {
		return paymentdataTableList;
	}

	public String getCreditmemoNo() {
		return creditmemoNo;
	}

	public void setCreditmemoNo(String creditmemoNo) {
		this.creditmemoNo = creditmemoNo;
	}

	public String getCreditmemoBalance() {
		return creditmemoBalance;
	}

	public void setCreditmemoBalance(String creditmemoBalance) {
		this.creditmemoBalance = creditmemoBalance;
	}

	public void setPaymentdataTableList(List<ATransaction> paymentdataTableList) {
		this.paymentdataTableList = paymentdataTableList;
	}

	public String getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(String receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmailId() {
		return customerEmailId;
	}

	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}

	public String getCustomerBillingAddress() {
		return customerBillingAddress;
	}

	public void setCustomerBillingAddress(String customerBillingAddress) {
		this.customerBillingAddress = customerBillingAddress;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public List<String> getVendorlist() {
		return vendorlist;
	}

	public void setVendorlist(List<String> vendorlist) {
		this.vendorlist = vendorlist;
	}
	
	public List<String> getProductNameList() {
		return productNameList;
	}

	public void setProductNameList(List<String> productNameList) {
		this.productNameList = productNameList;
	}

	public List<String> getCustomerNameList() {
		return customerNameList;
	}

	public void setCustomerNameList(List<String> customerNameList) {
		this.customerNameList = customerNameList;
	}
	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBalAmount() {
		return balAmount;
	}

	public void setBalAmount(String balAmount) {
		this.balAmount = balAmount;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public List<String> getDetailTypes() {
		return detailTypes;
	}

	public void setDetailTypes(List<String> detailTypes) {
		this.detailTypes = detailTypes;
	}

	public List<String> getAccountTypes() {
		return accountTypes;
	}

	public void setAccountTypes(List<String> accountTypes) {
		this.accountTypes = accountTypes;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getCtransactionType() {
		return ctransactionType;
	}

	public void setCtransactionType(String ctransactionType) {
		this.ctransactionType = ctransactionType;
	}

	public UploadedFile getTransFile() {
		return transFile;
	}

	public void setTransFile(UploadedFile transFile) {
		this.transFile = transFile;
	}

	public String getMaddress() {
		return maddress;
	}

	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}

	public String getTransamount() {
		return transamount;
	}

	public void setTransamount(String transamount) {
		this.transamount = transamount;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
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

	public List<ATransaction> getDebitlist() {
		return debitlist;
	}

	public void setDebitlist(List<ATransaction> debitlist) {
		this.debitlist = debitlist;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getCpaymentMode() {
		return cpaymentMode;
	}

	public void setCpaymentMode(String cpaymentMode) {
		this.cpaymentMode = cpaymentMode;
	}

	public String getCparticular() {
		return cparticular;
	}

	public void setCparticular(String cparticular) {
		this.cparticular = cparticular;
	}

	public String getCamount() {
		return camount;
	}

	public void setCamount(String camount) {
		this.camount = camount;
	}

	public String getCnote() {
		return cnote;
	}

	public void setCnote(String cnote) {
		this.cnote = cnote;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Transaction> getView() {
		return view;
	}

	public void setView(List<Transaction> view) {
		this.view = view;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getChequeno() {
		return chequeno;
	}

	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
	}

	public Date getChequedate() {
		return chequedate;
	}

	public void setChequedate(Date chequedate) {
		this.chequedate = chequedate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getParticular() {
		return particular;
	}

	public void setParticular(String particular) {
		this.particular = particular;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
    public ATransaction() {
    }
 
    public ATransaction(String transactionType,Date date,String transactionNo,String PayeeName,String description,String accounts,String transamount,String balance) {
        this.transactionType = transactionType;
        this.date=date;
        this.transactionNo=transactionNo;
        this.PayeeName=PayeeName;
        this.description=description;
        this.accounts=accounts;
        this.transamount = transamount;
        this.balance = balance;
    }
 
}