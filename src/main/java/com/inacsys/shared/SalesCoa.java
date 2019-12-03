package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the sales_coa database table.
 * 
 */
@Entity
@Table(name="sales_coa")
@NamedQuery(name="SalesCoa.findAll", query="SELECT s FROM SalesCoa s")
public class SalesCoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sales_coa_ID;

	@Column(name="account_status")
	private String accountStatus;

	private String status;

	//bi-directional many-to-one association to ChartOfAccount
	@ManyToOne
	@JoinColumn(name="chart_of_account_ID")
	private ChartOfAccount chartOfAccount;

	//bi-directional many-to-one association to SalesTransaction
	@ManyToOne
	@JoinColumn(name="sales_transaction_ID")
	private SalesTransaction salestransaction;

	public SalesCoa() {
	}
	
	public SalesCoa(SalesTransaction salestransaction2, ChartOfAccount chartofaccount2,String accountStatus,String status) {
		this. chartOfAccount = chartofaccount2;
		this. salestransaction = salestransaction2;
		this. accountStatus = accountStatus;
		this. status = status;
	}

	public int getSales_coa_ID() {
		return this.sales_coa_ID;
	}

	public void setSales_coa_ID(int sales_coa_ID) {
		this.sales_coa_ID = sales_coa_ID;
	}

	public String getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}