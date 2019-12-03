package com.inacsys.shared;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the account_type database table.
 * 
 */
@Entity
@Table(name="account_type")
@NamedQuery(name="AccountType.findAll", query="SELECT a FROM AccountType a")
public class AccountType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int account_type_ID;

	@Column(name="account_type")
	private String accountType;

	@Column(name="category_type")
	private String categoryType;

	@Column(name="detail_type")
	private String detailType;

	private String status;

	@Column(name="type_description")
	private String typeDescription;
	
	@Column(name="opening_balance")
	private String openingBalance;

	//bi-directional many-to-one association to ChartOfAccount
	@OneToMany(mappedBy="accountType")
	private List<ChartOfAccount> chartOfAccounts;

	public AccountType() {
	}

	public int getAccount_type_ID() {
		return this.account_type_ID;
	}

	public void setAccount_type_ID(int account_type_ID) {
		this.account_type_ID = account_type_ID;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCategoryType() {
		return this.categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getDetailType() {
		return this.detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTypeDescription() {
		return this.typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public List<ChartOfAccount> getChartOfAccounts() {
		return this.chartOfAccounts;
	}

	public String getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(String openingBalance) {
		this.openingBalance = openingBalance;
	}

	public void setChartOfAccounts(List<ChartOfAccount> chartOfAccounts) {
		this.chartOfAccounts = chartOfAccounts;
	}

	public ChartOfAccount addChartOfAccount(ChartOfAccount chartOfAccount) {
		getChartOfAccounts().add(chartOfAccount);
		chartOfAccount.setAccountType(this);

		return chartOfAccount;
	}

	public ChartOfAccount removeChartOfAccount(ChartOfAccount chartOfAccount) {
		getChartOfAccounts().remove(chartOfAccount);
		chartOfAccount.setAccountType(null);

		return chartOfAccount;
	}

}