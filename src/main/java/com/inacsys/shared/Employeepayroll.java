package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the employeepayroll database table.
 * 
 */
@Entity
@Table(name="employeepayroll")
@NamedQuery(name="Employeepayroll.findAll", query="SELECT e FROM Employeepayroll e")
public class Employeepayroll implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="has_employee_detail_payroll_id")
	private int hasEmployeeDetailPayrollId;

	private String client_ID;

	// bi-directional many-to-one association to Employee
		@ManyToOne
		@JoinColumn(name = "employee_details_id")
		private Employee employee;

		// bi-directional many-to-one association to Payroll
		@ManyToOne
		@JoinColumn(name = "payroll_id")
		private Payroll payroll;


	private String status;

	
	public Employeepayroll() {
	}

	public int getHasEmployeeDetailPayrollId() {
		return this.hasEmployeeDetailPayrollId;
	}

	public void setHasEmployeeDetailPayrollId(int hasEmployeeDetailPayrollId) {
		this.hasEmployeeDetailPayrollId = hasEmployeeDetailPayrollId;
	}

	public String getClient_ID() {
		return this.client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}


	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Payroll getPayroll() {
		return payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}