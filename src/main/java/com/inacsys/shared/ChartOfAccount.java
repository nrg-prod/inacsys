package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The persistent class for the chart_of_account database table.
 * 
 */
@Entity
@Table(name = "chart_of_account")
@NamedQuery(name = "ChartOfAccount.findAll", query = "SELECT c FROM ChartOfAccount c")
public class ChartOfAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int chart_of_account_ID;
	
	@Temporal(TemporalType.DATE)
	@Column(name="tran_date")
	private Date trandate;

	@Column(name="account_name")
	private String accountName;

	@Column(name="account_name_description")
	private String accountNameDescription;
	
	@Column(name="opening_balance")
	private String openingBalance;

	private String balance;
	
	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="updated_date")
	private Date updatedDate;
	
	private String status;
	
	@Column(name="approval_status")
	private String approvalStatus;

	private String client_ID;

	private String currency;
	
	private String taxes;
	

	// bi-directional many-to-one association to AccountType
	@ManyToOne
	@JoinColumn(name="account_type_ID")
	private AccountType accountType;

	//bi-directional many-to-one association to ExpenseCoa
	@OneToMany(mappedBy="chartOfAccount")
	private List<ExpenseCoa> expenseCoas;

	//bi-directional many-to-one association to SalesCoa
	@OneToMany(mappedBy="chartOfAccount", cascade = CascadeType.ALL) 
	private Set<SalesCoa> salesCoas;
	
	//bi-directional many-to-one association to BankAcct
	@OneToMany(mappedBy="chartOfAccount", cascade = CascadeType.ALL) 
	private Set<BankAcct> bankAccts;
	
	//bi-directional many-to-one association to OtherCurrentAssetsAcct
	@OneToMany(mappedBy="chartOfAccount", cascade = CascadeType.ALL) 
	private Set<OtherCurrentAssetsAcct> otherCurrentAssetsAccts;
		
	//bi-directional many-to-one association to OtherAssetsAcct
	@OneToMany(mappedBy="chartOfAccount", cascade = CascadeType.ALL) 
	private Set<OtherAssetsAcct> otherAssetsAccts;
		
	//bi-directional many-to-one association to FixedAssetsAcct
	@OneToMany(mappedBy="chartOfAccount", cascade = CascadeType.ALL) 
	private Set<FixedAssetsAcct> fixedAssetsAccts;
	
	//bi-directional many-to-one association to CreditCardAcct
	@OneToMany(mappedBy="chartOfAccount", cascade = CascadeType.ALL) 
	private Set<CreditCardAcct> creditCardAccts;
	
	//bi-directional many-to-one association to OpenbalEquityAcct
	@OneToMany(mappedBy="chartOfAccount", cascade = CascadeType.ALL) 
	private Set<OpenbalEquityAcct> openingbalequityAccts;
	
	//bi-directional many-to-one association to EquityAcct
	@OneToMany(mappedBy="chartOfAccount", cascade = CascadeType.ALL) 
	private Set<EquityAcct> equityAccts;
	
	//bi-directional many-to-one association to EquityAcct
	@OneToMany(mappedBy="chartOfAccount", cascade = CascadeType.ALL) 
	private Set<OtherCurrentLibAcct> othercurrentlibAccts;
		
	//bi-directional many-to-one association to EquityAcct
	@OneToMany(mappedBy="chartOfAccount", cascade = CascadeType.ALL) 
	private Set<NotesPayableAcct> notespayableAccts;
	
	public ChartOfAccount() {
	}
	
	public String getTaxes() {
		return taxes;
	}

	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Set<OtherCurrentLibAcct> getOthercurrentlibAccts() {
		return othercurrentlibAccts;
	}

	public void setOthercurrentlibAccts(Set<OtherCurrentLibAcct> othercurrentlibAccts) {
		this.othercurrentlibAccts = othercurrentlibAccts;
	}

	public Set<NotesPayableAcct> getNotespayableAccts() {
		return notespayableAccts;
	}

	public void setNotespayableAccts(Set<NotesPayableAcct> notespayableAccts) {
		this.notespayableAccts = notespayableAccts;
	}

	public Set<OpenbalEquityAcct> getOpeningbalequityAccts() {
		return openingbalequityAccts;
	}

	public void setOpeningbalequityAccts(Set<OpenbalEquityAcct> openingbalequityAccts) {
		this.openingbalequityAccts = openingbalequityAccts;
	}

	public Set<EquityAcct> getEquityAccts() {
		return equityAccts;
	}

	public void setEquityAccts(Set<EquityAcct> equityAccts) {
		this.equityAccts = equityAccts;
	}

	public Set<CreditCardAcct> getCreditCardAccts() {
		return creditCardAccts;
	}

	public void setCreditCardAccts(Set<CreditCardAcct> creditCardAccts) {
		this.creditCardAccts = creditCardAccts;
	}

	public Set<OtherCurrentAssetsAcct> getOtherCurrentAssetsAccts() {
		return otherCurrentAssetsAccts;
	}

	public void setOtherCurrentAssetsAccts(Set<OtherCurrentAssetsAcct> otherCurrentAssetsAccts) {
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

	public Set<BankAcct> getBankAccts() {
		return bankAccts;
	}

	public void setBankAccts(Set<BankAcct> bankAccts) {
		this.bankAccts = bankAccts;
	}

	public int getChart_of_account_ID() {
		return this.chart_of_account_ID;
	}

	public void setChart_of_account_ID(int chart_of_account_ID) {
		this.chart_of_account_ID = chart_of_account_ID;
	}
	
	public Date getTrandate() {
		return trandate;
	}

	public void setTrandate(Date trandate) {
		this.trandate = trandate;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNameDescription() {
		return this.accountNameDescription;
	}

	public void setAccountNameDescription(String accountNameDescription) {
		this.accountNameDescription = accountNameDescription;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
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

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public AccountType getAccountType() {
		return this.accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	public String getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(String openingBalance) {
		this.openingBalance = openingBalance;
	}

	public List<ExpenseCoa> getExpenseCoas() {
		return this.expenseCoas;
	}

	public void setExpenseCoas(List<ExpenseCoa> expenseCoas) {
		this.expenseCoas = expenseCoas;
	}

	public ExpenseCoa addExpenseCoa(ExpenseCoa expenseCoa) {
		getExpenseCoas().add(expenseCoa);
		expenseCoa.setChartOfAccount(this);

		return expenseCoa;
	}

	public ExpenseCoa removeExpenseCoa(ExpenseCoa expenseCoa) {
		getExpenseCoas().remove(expenseCoa);
		expenseCoa.setChartOfAccount(null);

		return expenseCoa;
	}

	public Set<SalesCoa> getSalesCoas() {
		return salesCoas;
	}

	public void setSalesCoas(Set<SalesCoa> salesCoas) {
		this.salesCoas = salesCoas;
	}

	public SalesCoa addSalesCoa(SalesCoa salesCoa) {
		getSalesCoas().add(salesCoa);
		salesCoa.setChartOfAccount(this);

		return salesCoa;
	}

	public SalesCoa removeSalesCoa(SalesCoa salesCoa) {
		getSalesCoas().remove(salesCoa);
		salesCoa.setChartOfAccount(null);

		return salesCoa;
	}

}