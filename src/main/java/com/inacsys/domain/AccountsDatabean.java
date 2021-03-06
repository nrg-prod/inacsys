package com.inacsys.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountsDatabean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8673573178157339913L;
	List<AccountsDatabean> accounts=null;
	private String account_name;
	private String account_name1;
	private String account_type;
	private Date account_date;
	private String account_number;
	private String account_description;
	private String account_transaction;
	private String account_amount;
	private BigDecimal debit;
	private BigDecimal credit;
	private List<String> accountType=null;
	private List<String> types=null;
	private String status;
	private String balance;
	private String validate;
	private String refNo;
	List<AccountsDatabean> profits=null;
	List<AccountsDatabean> loss=null;
	private String profit;
	private String equity;
	private String libequity;
	List<AccountsDatabean> equities=null;
	private String name;
	private boolean customflag=false;
	private boolean otherflag=false;
	private Date fromDate;
	private Date toDate;
	private String reportPeriod;
	private boolean flag=false;
	private boolean flag1=false;
	private boolean flag2=false;
	private boolean flag3=false;
	private String monthNet;
	private String monthProfit;
	private BigDecimal monthLoss;
	private String catregoryType;
	private BigDecimal cogTotal;
	private String totalIncome;
	private String totalPurchases;
	private String totalOtherIncome;
	private String totalExpenses;
	private String totalOtherExpenses;
	private String crossProfit;
	private String clientID;
	private String buttonValue;
	private String coaStatus;
	private String newdetailType;
	
/*john Clinton*/
	private String detailType;
	private String accountStatus;
	private String paymentbalance;
	private String transactionType;
	private String transactionAmount;
	private String transaction;
	private String debitParticular;
	private String creditParticular;
	private String debitAmount;
	private String creditAmount;
	private String paymentAmount;
	private String depositAmount;
	private String subAccount;
	private String debitTotal;
	private String creditTotal;
	
	private String payeeName;
	private BigDecimal profitAmount;
	List<AccountsDatabean> accountsDetail=null;
	
	List<AccountsDatabean> assetsList=null;
	List<AccountsDatabean> liabilityList=null;
	List<AccountsDatabean> trialBalance=null;
	List<AccountsDatabean> bankaccountsList=null;
	List<AccountsDatabean> cashaccountsList=null;
	List<AccountsDatabean> accountReceivableList=null;
	List<AccountsDatabean> othercurrentassetsList=null;
	List<AccountsDatabean> fixedassetsList=null;
	List<AccountsDatabean> creditcardList=null;
	List<AccountsDatabean> othercurrentlibList=null;
	List<AccountsDatabean> longtermlibList=null;
	private List<AccountsDatabean> incomeList=null;
	private List<AccountsDatabean> otherIncomeList=null;
	private List<AccountsDatabean> cogList=null;
	private List<AccountsDatabean> expensesList=null;
	private List<AccountsDatabean> otherExpensesList=null;
	private List<AccountsDatabean> currLibList=null;
	private List<AccountsDatabean> purchaseList=null;
	
	private Double netIncome;
	private Double totalassets;
	private Double totalbankassets;	
	private Double totalcashassets;
	private Double totalaccrecassets;
	private Double totalothercurrentassets;
	private Double totalfixedassets;
	private Double totalotherassets;
	private Double totalliablity;
	private Double totalEquities;
	private Double totalaccpayable;
	private Double totalcreditcards;
	private Double totalothercurrliability; 
	private Double totalcurrliability;
	private Double totallongtermliability;
	private Date dueDate;
	private String currency;
	private String baseCurrency;
	private String currencyAmount;
	private List<String> creditParticularList=null;
	private List<String> debitParticularList=null;
	private List<String> creditAmountList=null;
	private List<String> debitAmountList=null;
	private String taxes;
	private String country;
	private String reportType;
	private Date date;
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTotalPurchases() {
		return totalPurchases;
	}

	public void setTotalPurchases(String totalPurchases) {
		this.totalPurchases = totalPurchases;
	}

	public List<AccountsDatabean> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<AccountsDatabean> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public Double getTotalcashassets() {
		return totalcashassets;
	}

	public void setTotalcashassets(Double totalcashassets) {
		this.totalcashassets = totalcashassets;
	}

	public List<AccountsDatabean> getCashaccountsList() {
		return cashaccountsList;
	}

	public void setCashaccountsList(List<AccountsDatabean> cashaccountsList) {
		this.cashaccountsList = cashaccountsList;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getNewdetailType() {
		return newdetailType;
	}

	public void setNewdetailType(String newdetailType) {
		this.newdetailType = newdetailType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTaxes() {
		return taxes;
	}

	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}

	public List<AccountsDatabean> getCurrLibList() {
		return currLibList;
	}

	public void setCurrLibList(List<AccountsDatabean> currLibList) {
		this.currLibList = currLibList;
	}

	public Double getTotalcurrliability() {
		return totalcurrliability;
	}

	public void setTotalcurrliability(Double totalcurrliability) {
		this.totalcurrliability = totalcurrliability;
	}

	public List<String> getDebitParticularList() {
		return debitParticularList;
	}

	public void setDebitParticularList(List<String> debitParticularList) {
		this.debitParticularList = debitParticularList;
	}

	public List<String> getCreditAmountList() {
		return creditAmountList;
	}

	public void setCreditAmountList(List<String> creditAmountList) {
		this.creditAmountList = creditAmountList;
	}

	public List<String> getDebitAmountList() {
		return debitAmountList;
	}

	public void setDebitAmountList(List<String> debitAmountList) {
		this.debitAmountList = debitAmountList;
	}

	public List<String> getCreditParticularList() {
		return creditParticularList;
	}

	public void setCreditParticularList(List<String> creditParticularList) {
		this.creditParticularList = creditParticularList;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCoaStatus() {
		return coaStatus;
	}

	public void setCoaStatus(String coaStatus) {
		this.coaStatus = coaStatus;
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

	public BigDecimal getProfitAmount() {
		return profitAmount;
	}

	public void setProfitAmount(BigDecimal profitAmount) {
		this.profitAmount = profitAmount;
	}

	public String getTotalOtherIncome() {
		return totalOtherIncome;
	}

	public void setTotalOtherIncome(String totalOtherIncome) {
		this.totalOtherIncome = totalOtherIncome;
	}

	public String getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(String totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public String getTotalOtherExpenses() {
		return totalOtherExpenses;
	}

	public void setTotalOtherExpenses(String totalOtherExpenses) {
		this.totalOtherExpenses = totalOtherExpenses;
	}

	public List<AccountsDatabean> getIncomeList() {
		return incomeList;
	}

	public void setIncomeList(List<AccountsDatabean> incomeList) {
		this.incomeList = incomeList;
	}

	public List<AccountsDatabean> getOtherIncomeList() {
		return otherIncomeList;
	}

	public void setOtherIncomeList(List<AccountsDatabean> otherIncomeList) {
		this.otherIncomeList = otherIncomeList;
	}

	public List<AccountsDatabean> getCogList() {
		return cogList;
	}

	public void setCogList(List<AccountsDatabean> cogList) {
		this.cogList = cogList;
	}

	public List<AccountsDatabean> getExpensesList() {
		return expensesList;
	}

	public void setExpensesList(List<AccountsDatabean> expensesList) {
		this.expensesList = expensesList;
	}

	public List<AccountsDatabean> getOtherExpensesList() {
		return otherExpensesList;
	}

	public void setOtherExpensesList(List<AccountsDatabean> otherExpensesList) {
		this.otherExpensesList = otherExpensesList;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getSubAccount() {
		return subAccount;
	}

	public void setSubAccount(String subAccount) {
		this.subAccount = subAccount;
	}

	public String getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getButtonValue() {
		return buttonValue;
	}

	public void setButtonValue(String buttonValue) {
		this.buttonValue = buttonValue;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public Double getTotalaccpayable() {
		return totalaccpayable;
	}

	public void setTotalaccpayable(Double totalaccpayable) {
		this.totalaccpayable = totalaccpayable;
	}

	public Double getTotalcreditcards() {
		return totalcreditcards;
	}

	public void setTotalcreditcards(Double totalcreditcards) {
		this.totalcreditcards = totalcreditcards;
	}

	public Double getTotalothercurrliability() {
		return totalothercurrliability;
	}

	public void setTotalothercurrliability(Double totalothercurrliability) {
		this.totalothercurrliability = totalothercurrliability;
	}

	public Double getTotallongtermliability() {
		return totallongtermliability;
	}

	public void setTotallongtermliability(Double totallongtermliability) {
		this.totallongtermliability = totallongtermliability;
	}

	public Double getTotalotherassets() {
		return totalotherassets;
	}

	public void setTotalotherassets(Double totalotherassets) {
		this.totalotherassets = totalotherassets;
	}

	public Double getTotalfixedassets() {
		return totalfixedassets;
	}

	public void setTotalfixedassets(Double totalfixedassets) {
		this.totalfixedassets = totalfixedassets;
	}

	public Double getTotalothercurrentassets() {
		return totalothercurrentassets;
	}

	public void setTotalothercurrentassets(Double totalothercurrentassets) {
		this.totalothercurrentassets = totalothercurrentassets;
	}

	public Double getTotalaccrecassets() {
		return totalaccrecassets;
	}

	public void setTotalaccrecassets(Double totalaccrecassets) {
		this.totalaccrecassets = totalaccrecassets;
	}

	public Double getTotalbankassets() {
		return totalbankassets;
	}

	public void setTotalbankassets(Double totalbankassets) {
		this.totalbankassets = totalbankassets;
	}

	public Double getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(Double netIncome) {
		this.netIncome = netIncome;
	}

	public List<AccountsDatabean> getCreditcardList() {
		return creditcardList;
	}

	public void setCreditcardList(List<AccountsDatabean> creditcardList) {
		this.creditcardList = creditcardList;
	}

	public List<AccountsDatabean> getOthercurrentlibList() {
		return othercurrentlibList;
	}

	public void setOthercurrentlibList(List<AccountsDatabean> othercurrentlibList) {
		this.othercurrentlibList = othercurrentlibList;
	}

	public List<AccountsDatabean> getLongtermlibList() {
		return longtermlibList;
	}

	public void setLongtermlibList(List<AccountsDatabean> longtermlibList) {
		this.longtermlibList = longtermlibList;
	}

	public List<AccountsDatabean> getAccountReceivableList() {
		return accountReceivableList;
	}

	public void setAccountReceivableList(
			List<AccountsDatabean> accountReceivableList) {
		this.accountReceivableList = accountReceivableList;
	}

	public List<AccountsDatabean> getOthercurrentassetsList() {
		return othercurrentassetsList;
	}

	public void setOthercurrentassetsList(
			List<AccountsDatabean> othercurrentassetsList) {
		this.othercurrentassetsList = othercurrentassetsList;
	}

	public List<AccountsDatabean> getFixedassetsList() {
		return fixedassetsList;
	}

	public void setFixedassetsList(List<AccountsDatabean> fixedassetsList) {
		this.fixedassetsList = fixedassetsList;
	}

	public List<AccountsDatabean> getBankaccountsList() {
		return bankaccountsList;
	}

	public void setBankaccountsList(List<AccountsDatabean> bankaccountsList) {
		this.bankaccountsList = bankaccountsList;
	}

	public List<AccountsDatabean> getTrialBalance() {
		return trialBalance;
	}

	public void setTrialBalance(List<AccountsDatabean> trialBalance) {
		this.trialBalance = trialBalance;
	}

	public Double getTotalEquities() {
		return totalEquities;
	}

	public void setTotalEquities(Double totalEquities) {
		this.totalEquities = totalEquities;
	}

	public Double getTotalassets() {
		return totalassets;
	}

	public void setTotalassets(Double totalassets) {
		this.totalassets = totalassets;
	}

	public Double getTotalliablity() {
		return totalliablity;
	}

	public void setTotalliablity(Double totalliablity) {
		this.totalliablity = totalliablity;
	}

	public List<AccountsDatabean> getAssetsList() {
		return assetsList;
	}

	public void setAssetsList(List<AccountsDatabean> assetsList) {
		this.assetsList = assetsList;
	}

	public List<AccountsDatabean> getLiabilityList() {
		return liabilityList;
	}

	public void setLiabilityList(List<AccountsDatabean> liabilityList) {
		this.liabilityList = liabilityList;
	}

	public String getAccount_name1() {
		return account_name1;
	}

	public void setAccount_name1(String account_name1) {
		this.account_name1 = account_name1;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getCrossProfit() {
		return crossProfit;
	}

	public void setCrossProfit(String crossProfit) {
		this.crossProfit = crossProfit;
	}

	public BigDecimal getCogTotal() {
		return cogTotal;
	}

	public void setCogTotal(BigDecimal cogTotal) {
		this.cogTotal = cogTotal;
	}

	public String getCatregoryType() {
		return catregoryType;
	}

	public void setCatregoryType(String catregoryType) {
		this.catregoryType = catregoryType;
	}
	public List<AccountsDatabean> getAccountsDetail() {
		return accountsDetail;
	}

	public void setAccountsDetail(List<AccountsDatabean> accountsDetail) {
		this.accountsDetail = accountsDetail;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getPaymentbalance() {
		return paymentbalance;
	}

	public void setPaymentbalance(String paymentbalance) {
		this.paymentbalance = paymentbalance;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public boolean isFlag3() {
		return flag3;
	}

	public void setFlag3(boolean flag3) {
		this.flag3 = flag3;
	}

	public String getMonthNet() {
		return monthNet;
	}

	public void setMonthNet(String monthNet) {
		this.monthNet = monthNet;
	}

	public String getMonthProfit() {
		return monthProfit;
	}

	public void setMonthProfit(String monthProfit) {
		this.monthProfit = monthProfit;
	}

	public BigDecimal getMonthLoss() {
		return monthLoss;
	}

	public void setMonthLoss(BigDecimal monthLoss) {
		this.monthLoss = monthLoss;
	}

	public boolean isFlag1() {
		return flag1;
	}

	public void setFlag1(boolean flag1) {
		this.flag1 = flag1;
	}

	public boolean isFlag2() {
		return flag2;
	}

	public void setFlag2(boolean flag2) {
		this.flag2 = flag2;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getReportPeriod() {
		return reportPeriod;
	}

	public void setReportPeriod(String reportPeriod) {
		this.reportPeriod = reportPeriod;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public boolean isCustomflag() {
		return customflag;
	}

	public void setCustomflag(boolean customflag) {
		this.customflag = customflag;
	}

	public boolean isOtherflag() {
		return otherflag;
	}

	public void setOtherflag(boolean otherflag) {
		this.otherflag = otherflag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEquity() {
		return equity;
	}

	public void setEquity(String equity) {
		this.equity = equity;
	}

	public String getLibequity() {
		return libequity;
	}

	public void setLibequity(String libequity) {
		this.libequity = libequity;
	}

	public List<AccountsDatabean> getEquities() {
		return equities;
	}

	public void setEquities(List<AccountsDatabean> equities) {
		this.equities = equities;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public List<AccountsDatabean> getProfits() {
		return profits;
	}

	public void setProfits(List<AccountsDatabean> profits) {
		this.profits = profits;
	}

	public List<AccountsDatabean> getLoss() {
		return loss;
	}

	public void setLoss(List<AccountsDatabean> loss) {
		this.loss = loss;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}


	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getAccountType() {
		return accountType;
	}

	public void setAccountType(List<String> accountType) {
		this.accountType = accountType;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public Date getAccount_date() {
		return account_date;
	}

	public void setAccount_date(Date account_date) {
		this.account_date = account_date;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getAccount_description() {
		return account_description;
	}

	public void setAccount_description(String account_description) {
		this.account_description = account_description;
	}

	public String getAccount_transaction() {
		return account_transaction;
	}

	public void setAccount_transaction(String account_transaction) {
		this.account_transaction = account_transaction;
	}

	public String getAccount_amount() {
		return account_amount;
	}

	public void setAccount_amount(String account_amount) {
		this.account_amount = account_amount;
	}

	public List<AccountsDatabean> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountsDatabean> accounts) {
		this.accounts = accounts;
	}
     
    private List<ATransaction> generalLedgerList;
     
    public AccountsDatabean() {
    	generalLedgerList = new ArrayList<ATransaction>();
    }
     
    public AccountsDatabean(String name) {
        this.name = name;
        generalLedgerList = new ArrayList<ATransaction>();
    }
     
    public List<ATransaction> getGeneralLedgerList() {
		return generalLedgerList;
	}

	public void setGeneralLedgerList(List<ATransaction> generalLedgerList) {
		this.generalLedgerList = generalLedgerList;
	}

	public BigDecimal getAccountAmount() {
        BigDecimal sum = BigDecimal.valueOf(0);
        for(ATransaction s : generalLedgerList) {
            sum=sum.add(new BigDecimal(s.getTransamount()));
        }
        return sum;
    }
     
    public BigDecimal getBalanceAmount() {
    	BigDecimal sum = BigDecimal.valueOf(0);
    	for(ATransaction s : generalLedgerList) {
    		sum=sum.add(new BigDecimal(s.getBalance()));
        }
        return sum;
    }
 
}
