package com.inacsys.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inacsys.shared.Employee;
import com.inacsys.shared.Month;

public class EmployeePayroll {
	public String name;
	public String employeeid;
	public String basicsalary;
	public String designation;
	public String month;
	public String months;
	public String year;
	public List<String> list;
	public ArrayList<String> list1 = null;
	public int workingdays;
	public String advanceamount;
	public String totalsalary;
	public Date payrolldate;
	public String payrollno;
	public List<Month> list4 = null;
	public List<EmployeePayroll> datalist = new ArrayList<EmployeePayroll>();
	public List<EmployeePayroll> data = new ArrayList<EmployeePayroll>();
	public List<EmployeePayroll> vallist = new ArrayList<EmployeePayroll>();
	public List<EmployeePayroll> value = new ArrayList<EmployeePayroll>();
	public int empId;
	public String payrollStatus;
	public List<EmployeePayroll> payrollInfo;
	public String status;
	public String designation1;
	public String employeeid1;
	public String date;
	public String todayDate;
	public String month1;
	public String regid;
	public String commission;
	public String currencyAmount;
	public String currency;
	public int id;
	public String overtime;
	public String gobackearly;
	public String lateattendance;
	public String absent;
	public String attendancehours;
	public List<EmployeePayroll> payroll = new ArrayList<EmployeePayroll>();
	ArrayList<EmployeePayroll> filterList;
	
	
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

	public ArrayList<EmployeePayroll> getFilterList() {
		return filterList;
	}

	public void setFilterList(ArrayList<EmployeePayroll> filterList) {
		this.filterList = filterList;
	}

	public String approvalStatus;
	private boolean payrollCheck=false;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public boolean isPayrollCheck() {
		return payrollCheck;
	}

	public void setPayrollCheck(boolean payrollCheck) {
		this.payrollCheck = payrollCheck;
	}

	public List<EmployeePayroll> getPayroll() {
		return payroll;
	}

	public void setPayroll(List<EmployeePayroll> payroll) {
		this.payroll = payroll;
	}

	public String getAttendancehours() {
		return attendancehours;
	}

	public void setAttendancehours(String attendancehours) {
		this.attendancehours = attendancehours;
	}

	public String getGobackearly() {
		return gobackearly;
	}

	public void setGobackearly(String gobackearly) {
		this.gobackearly = gobackearly;
	}

	public String getLateattendance() {
		return lateattendance;
	}

	public void setLateattendance(String lateattendance) {
		this.lateattendance = lateattendance;
	}

	public String getAbsent() {
		return absent;
	}

	public void setAbsent(String absent) {
		this.absent = absent;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String string) {
		this.commission = string;
	}

	public String getOvertime() {
		return overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

	public String getRegid() {
		return regid;
	}

	public void setRegid(String regid) {
		this.regid = regid;
	}

	public String getMonth1() {
		return month1;
	}

	public void setMonth1(String month1) {
		this.month1 = month1;
	}

	public String getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDesignation1() {
		return designation1;
	}

	public void setDesignation1(String designation1) {
		this.designation1 = designation1;
	}

	public String getEmployeeid1() {
		return employeeid1;
	}

	public void setEmployeeid1(String employeeid1) {
		this.employeeid1 = employeeid1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<EmployeePayroll> getPayrollInfo() {
		return payrollInfo;
	}

	public void setPayrollInfo(List<EmployeePayroll> payrollInfo) {
		this.payrollInfo = payrollInfo;
	}

	public String getPayrollStatus() {
		return payrollStatus;
	}

	public void setPayrollStatus(String payrollStatus) {
		this.payrollStatus = payrollStatus;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public List<EmployeePayroll> getVallist() {
		return vallist;
	}

	public void setVallist(List<EmployeePayroll> vallist) {
		this.vallist = vallist;
	}

	public List<EmployeePayroll> getValue() {
		return value;
	}

	public void setValue(List<EmployeePayroll> value) {
		this.value = value;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public List<EmployeePayroll> getDatalist() {
		return datalist;
	}

	public void setDatalist(List<EmployeePayroll> datalist) {
		this.datalist = datalist;
	}

	public List<EmployeePayroll> getData() {
		return data;
	}

	public void setData(List<EmployeePayroll> data) {
		this.data = data;
	}

	public List<Month> getList4() {
		return list4;
	}

	public void setList4(List<Month> list4) {
		this.list4 = list4;
	}

	public String getPayrollno() {
		return payrollno;
	}

	public void setPayrollno(String payrollno) {
		this.payrollno = payrollno;
	}

	public int getWorkingdays() {
		return workingdays;
	}

	public void setWorkingdays(int workingdays) {
		this.workingdays = workingdays;
	}

	public String getAdvanceamount() {
		return advanceamount;
	}

	public void setAdvanceamount(String advanceamount) {
		this.advanceamount = advanceamount;
	}

	public String getTotalsalary() {
		return totalsalary;
	}

	public void setTotalsalary(String totalsalary) {
		this.totalsalary = totalsalary;
	}

	public Date getPayrolldate() {
		return payrolldate;
	}

	public void setPayrolldate(Date payrolldate) {
		this.payrolldate = payrolldate;
	}

	public List<Employee> list3 = null;

	public List<Employee> getList3() {
		return list3;
	}

	public void setList3(List<Employee> list3) {
		this.list3 = list3;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public ArrayList<String> getList1() {
		return list1;
	}

	public void setList1(ArrayList<String> list1) {
		this.list1 = list1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getBasicsalary() {
		return basicsalary;
	}

	public void setBasicsalary(String basicsalary) {
		this.basicsalary = basicsalary;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	BigDecimal bsc = BigDecimal.valueOf(0);
	BigDecimal work = BigDecimal.valueOf(0);

	public BigDecimal getWork() {
		return work;
	}

	public void setWork(BigDecimal work) {
		this.work = work;
	}

	public BigDecimal getBsc() {
		return bsc;
	}

	public void setBsc(BigDecimal bsc) {
		this.bsc = bsc;
	}

}