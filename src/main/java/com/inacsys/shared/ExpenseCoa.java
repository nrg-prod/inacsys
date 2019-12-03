package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import com.inacsys.domain.ATransaction;


/**
 * The persistent class for the expense_coa database table.
 * 
 */
@Entity
@Table(name="expense_coa")
@NamedQuery(name="ExpenseCoa.findAll", query="SELECT e FROM ExpenseCoa e")
public class ExpenseCoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expense_coa_ID;

	@Column(name="account_status")
	private String accountStatus;

	private String status;

	//bi-directional many-to-one association to ChartOfAccount
	@ManyToOne
	@JoinColumn(name="chart_of_account_ID")
	private ChartOfAccount chartOfAccount;

	//bi-directional many-to-one association to ExpenseTransaction
	@ManyToOne
	@JoinColumn(name="expense_transaction_ID")
	private ExpenseTransaction expenseTransaction;

	public ExpenseCoa() {
	}

	public ExpenseCoa(ATransaction aTransaction,String accountStatus,String Status,ExpenseTransaction Fkey,ChartOfAccount account) {
		System.out.println("chk=================expense COA==================>");
		this.expenseTransaction=Fkey;
		this.chartOfAccount=account;
        this.accountStatus=accountStatus;
        this.status=Status;
}
	
	public int getExpense_coa_ID() {
		return this.expense_coa_ID;
	}

	public void setExpense_coa_ID(int expense_coa_ID) {
		this.expense_coa_ID = expense_coa_ID;
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

	public ExpenseTransaction getExpenseTransaction() {
		return this.expenseTransaction;
	}

	public void setExpenseTransaction(ExpenseTransaction expenseTransaction) {
		this.expenseTransaction = expenseTransaction;
	}

}