package com.inacsys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inacsys.domain.StockReport;
import com.inacsys.managedBean.StockReportMB;

public class StockReportJDBC {

	static final Logger logger = LoggerFactory.getLogger(StockReportJDBC.class);
	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	//static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
	//static final String USER = "root";
	//static final String PASS = "root";
	static final String ALLEMPLOYEE_QUERY = "select * from employee_report where payroll_status='payroll generated' and payroll_date between ? and ? ";
	static final String EMPLOYEEREPORT_QUERY = "select * from employee_report where employee_name=? and payroll_status='payroll generated' and payroll_date between ? and ? ";
	static final String ALLSTOCK_QUERY = "select * from stock_report ";
	static final String STOCKREPORT_QUERY = "select * from stock_report where product_name=? and stock_status='insert'";
	public static Connection con = null;
	public static PreparedStatement preparedStatement = null;
	public static Statement stmt = null;

	public static ArrayList<StockReport> stocksearch(String type)
			throws SQLException {
		ArrayList<StockReport> result = null;
		try {

			//Class.forName(JDBC_DRIVER);

			//con = DriverManager.getConnection(DB_URL, USER, PASS);
			con= Util.getConnection();

			if (type.equalsIgnoreCase("All")) {
				result = new ArrayList<StockReport>();
				preparedStatement = con.prepareStatement(ALLSTOCK_QUERY);
				stmt = con.createStatement();
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					StockReport stockReport = new StockReport();
					stockReport.setAvlQuantity(rs.getString("quantity"));
					stockReport.setProductName(rs.getString("product_name"));
					stockReport
							.setDamagedQuantity(rs.getString("damge_status"));
					stockReport.setStockInQuantity(rs.getString("stock_in"));
					stockReport.setStockOutQuantity(rs.getString("stock_out"));
					stockReport.setUnitprice(rs.getString("Unit_price"));

					result.add(stockReport);
				}
			} else {
				result = new ArrayList<StockReport>();
				preparedStatement = con.prepareStatement(STOCKREPORT_QUERY);
				stmt = con.createStatement();
				preparedStatement.setString(1, type);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					StockReport stockReport = new StockReport();
					stockReport.setAvlQuantity(rs.getString("quantity"));
					stockReport.setProductName(rs.getString("product_name"));
					stockReport
							.setDamagedQuantity(rs.getString("damge_status"));
					stockReport.setStockInQuantity(rs.getString("stock_in"));
					stockReport.setStockOutQuantity(rs.getString("stock_out"));
					stockReport.setUnitprice(rs.getString("Unit_price"));

					result.add(stockReport);
				}
			}
			con.close();

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			con.close();
		}
		return result;
	}

	// john

	public static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	public static ArrayList<StockReport> employeesearch(String type,
			Date fromDate, Date toDate) throws SQLException {
		ArrayList<StockReport> result = null;
		try {
			java.sql.Date sqldate = new java.sql.Date(fromDate.getTime());
			java.sql.Date sqldate1 = new java.sql.Date(toDate.getTime());

//			Class.forName(JDBC_DRIVER);

	//		con = DriverManager.getConnection(DB_URL, USER, PASS);
			con= Util.getConnection();

			if (type.equalsIgnoreCase("All")) {
				result = new ArrayList<StockReport>();
				preparedStatement = con.prepareStatement(ALLEMPLOYEE_QUERY);
				stmt = con.createStatement();
				preparedStatement.setDate(1, sqldate);
				preparedStatement.setDate(2, sqldate1);
				ResultSet rs = preparedStatement.executeQuery();
				logger.debug("f and t" + fromDate + "====" + toDate);
				while (rs.next()) {
					logger.debug("1");
					StockReport employeeReport = new StockReport();
					employeeReport.setEmployeeName(rs
							.getString("employee_name"));
					employeeReport.setPayrollDate(rs.getDate("payroll_date"));
					employeeReport.setBasicSalary(rs.getString("basic_salary"));
					employeeReport.setTotalSalary(rs.getString("total_salary"));
					employeeReport.setPayrollNumber(rs
							.getString("payroll_number"));
					employeeReport.setMonth(rs.getString("month"));
					employeeReport.setYear(rs.getString("year"));
					logger.debug("==============");
					result.add(employeeReport);
				}
			} else {
				result = new ArrayList<StockReport>();
				preparedStatement = con.prepareStatement(EMPLOYEEREPORT_QUERY);
				stmt = con.createStatement();
				preparedStatement.setString(1, type);
				preparedStatement.setDate(2, sqldate);
				preparedStatement.setDate(3, sqldate1);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					logger.debug("1");
					StockReport employeeReport = new StockReport();
					employeeReport.setEmployeeName(rs
							.getString("employee_name"));
					employeeReport.setPayrollDate(rs.getDate("payroll_date"));
					employeeReport.setBasicSalary(rs.getString("basic_salary"));
					employeeReport.setTotalSalary(rs.getString("total_salary"));
					employeeReport.setPayrollNumber(rs
							.getString("payroll_number"));
					employeeReport.setMonth(rs.getString("month"));
					employeeReport.setYear(rs.getString("year"));
					logger.debug("==============");
					result.add(employeeReport);
				}
			}
			con.close();

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			con.close();
		}
		return result;
	}

}
