package com.inacsys.domain;

import java.util.Date;

public class StockReport {

	public String ProductName;
	public String AvlQuantity;
	public String StockInQuantity;
	public String StockOutQuantity;
	public String DamagedQuantity;
	public String Unitprice;

	public String EmployeeName;
	public Date PayrollDate;
	public String PayrollNumber;
	public String BasicSalary;
	public String TotalSalary;
	public String Month;
	public String Year;

	public String getPayrollNumber() {
		return PayrollNumber;
	}

	public void setPayrollNumber(String payrollNumber) {
		PayrollNumber = payrollNumber;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public Date getPayrollDate() {
		return PayrollDate;
	}

	public void setPayrollDate(Date payrollDate) {
		PayrollDate = payrollDate;
	}

	public String getBasicSalary() {
		return BasicSalary;
	}

	public void setBasicSalary(String basicSalary) {
		BasicSalary = basicSalary;
	}

	public String getTotalSalary() {
		return TotalSalary;
	}

	public void setTotalSalary(String totalSalary) {
		TotalSalary = totalSalary;
	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getUnitprice() {
		return Unitprice;
	}

	public void setUnitprice(String unitprice) {
		Unitprice = unitprice;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getAvlQuantity() {
		return AvlQuantity;
	}

	public void setAvlQuantity(String avlQuantity) {
		AvlQuantity = avlQuantity;
	}

	public String getStockInQuantity() {
		return StockInQuantity;
	}

	public void setStockInQuantity(String stockInQuantity) {
		StockInQuantity = stockInQuantity;
	}

	public String getStockOutQuantity() {
		return StockOutQuantity;
	}

	public void setStockOutQuantity(String stockOutQuantity) {
		StockOutQuantity = stockOutQuantity;
	}

	public String getDamagedQuantity() {
		return DamagedQuantity;
	}

	public void setDamagedQuantity(String damagedQuantity) {
		DamagedQuantity = damagedQuantity;
	}

}
