package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the payroll database table.
 * 
 */
@Entity
@Table(name = "payroll")
@NamedQuery(name = "Payroll.findAll", query = "SELECT p FROM Payroll p")
public class Payroll implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "payroll_id")
	private int payrollId;

	private String absent;

	@Column(name = "advance_amount")
	private String advanceAmount;

	@Column(name = "attendance_hours")
	private String attendanceHours;

	private String commission;

	@Column(name = "emp_id")
	private String empId;

	@Column(name = "go_back_early")
	private String goBackEarly;

	@Column(name = "late_attendance")
	private String lateAttendance;

	@Column(name = "over_time")
	private String overTime;

	@Column(name = "payroll_date")
	private String payrollDate;

	@Column(name = "payroll_number")
	private String payrollNumber;

	@Column(name = "payroll_status")
	private String payrollStatus;

	private String status;

	@Column(name = "total_salary")
	private String totalSalary;

	@Column(name = "working_days")
	private int workingDays;

	// bi-directional many-to-one association to Employee
	@OneToMany(mappedBy = "payroll")
	private List<Employee> employees;

	// bi-directional many-to-one association to Employeepayroll
	@OneToMany(mappedBy = "payroll")
	private List<Employeepayroll> employeepayrolls;

	// bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name = "employee_details_id")
	private Employee employee;

	// bi-directional many-to-one association to Month
	@ManyToOne
	@JoinColumn(name = "month_id")
	private Month month;

	// bi-directional many-to-one association to Year
	@ManyToOne
	@JoinColumn(name = "year_id")
	private Year year;
	
	// bi-directional many-to-one association to UserCreate
	@ManyToOne
	@JoinColumn(name = "user_ID")
	private UserCreate userID;
		
	private String client_ID;
	
	private String approvalStatus;
	
	private String currency;
	
	private String currencyAmount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public UserCreate getUserID() {
		return userID;
	}

	public void setUserID(UserCreate userID) {
		this.userID = userID;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}
	public Payroll() {
	}

	public int getPayrollId() {
		return this.payrollId;
	}

	public void setPayrollId(int payrollId) {
		this.payrollId = payrollId;
	}

	public String getAbsent() {
		return this.absent;
	}

	public void setAbsent(String absent) {
		this.absent = absent;
	}

	public String getAdvanceAmount() {
		return this.advanceAmount;
	}

	public void setAdvanceAmount(String advanceAmount) {
		this.advanceAmount = advanceAmount;
	}

	public String getAttendanceHours() {
		return this.attendanceHours;
	}

	public void setAttendanceHours(String attendanceHours) {
		this.attendanceHours = attendanceHours;
	}

	public String getCommission() {
		return this.commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getGoBackEarly() {
		return this.goBackEarly;
	}

	public void setGoBackEarly(String goBackEarly) {
		this.goBackEarly = goBackEarly;
	}

	public String getLateAttendance() {
		return this.lateAttendance;
	}

	public void setLateAttendance(String lateAttendance) {
		this.lateAttendance = lateAttendance;
	}

	public String getOverTime() {
		return this.overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public String getPayrollDate() {
		return this.payrollDate;
	}

	public void setPayrollDate(String payrollDate) {
		this.payrollDate = payrollDate;
	}

	public String getPayrollNumber() {
		return this.payrollNumber;
	}

	public void setPayrollNumber(String payrollNumber) {
		this.payrollNumber = payrollNumber;
	}

	public String getPayrollStatus() {
		return this.payrollStatus;
	}

	public void setPayrollStatus(String payrollStatus) {
		this.payrollStatus = payrollStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotalSalary() {
		return this.totalSalary;
	}

	public void setTotalSalary(String totalSalary) {
		this.totalSalary = totalSalary;
	}

	public int getWorkingDays() {
		return this.workingDays;
	}

	public void setWorkingDays(int workingDays) {
		this.workingDays = workingDays;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setPayroll(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setPayroll(null);

		return employee;
	}

	public List<Employeepayroll> getEmployeepayrolls() {
		return this.employeepayrolls;
	}

	public void setEmployeepayrolls(List<Employeepayroll> employeepayrolls) {
		this.employeepayrolls = employeepayrolls;
	}

	public Employeepayroll addEmployeepayroll(Employeepayroll employeepayroll) {
		getEmployeepayrolls().add(employeepayroll);
		employeepayroll.setPayroll(this);

		return employeepayroll;
	}

	public Employeepayroll removeEmployeepayroll(Employeepayroll employeepayroll) {
		getEmployeepayrolls().remove(employeepayroll);
		employeepayroll.setPayroll(null);

		return employeepayroll;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Month getMonth() {
		return this.month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public Year getYear() {
		return this.year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}