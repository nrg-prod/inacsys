package com.inacsys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inacsys.domain.PurchaseReport;
import com.inacsys.domain.SalesReport;

public class SalesReportJDBC {
	static final Logger logger = LoggerFactory.getLogger(SalesReportJDBC.class);
	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	//static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
	//public static final String USER = "root";
	//static final String PASS = "root";
	static final String CUSTOMERNAME_QUERY = "select * from sales_report where customer_name like ? and sales_order_date between ? and ? and status='insert'";
	static final String ALLCUSTOMERNAME_QUERY = "select * from sales_report where sales_order_date between ? and ? and (status='insert' or status='delivered')";
	static final String SALESDELIVERED_QUERY = "select * from sales_report where sales_order_date between ? and ? and (status1='Normal sales' and status='Delivered')";
	static final String SALESRETURNED_QUERY = "select * from sales_report where sales_order_date between ? and ? and (status1='Normal sales' and status='Returned')";
	public static String status;
	public static Connection con = null;
	public static PreparedStatement preparedStatement = null;
	public static PreparedStatement preparedStatement1 = null;
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public static ArrayList<SalesReport> customerNameSearch(
			String customerType, String aLLcustomerName, String repornew,
			Date salesFromDate, Date salesToDate) throws SQLException {

		ArrayList<SalesReport> result = null;
		try {

			java.sql.Date sqldate = new java.sql.Date(salesFromDate.getTime());
			java.sql.Date sqldate1 = new java.sql.Date(salesToDate.getTime());
			//Class.forName(JDBC_DRIVER);
			//con = DriverManager.getConnection(DB_URL, USER, PASS);
			con= Util.getConnection();			
			if (customerType.equalsIgnoreCase("single")) {
				result = new ArrayList<SalesReport>();
				preparedStatement = con.prepareStatement(CUSTOMERNAME_QUERY);
				preparedStatement.setString(1, repornew);
				preparedStatement.setDate(2, sqldate);
				preparedStatement.setDate(3, sqldate1);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					SalesReport salesReport = new SalesReport();
					salesReport.setCustomerName(rs.getString("customer_name"));
					salesReport.setOrderdate(rs.getString("sales_order_date"));
					salesReport.setOrderNumber(rs
							.getString("sales_order_number"));
					salesReport.setQuantity(rs.getString("sold_quantity"));
					salesReport.setPhonenumber(rs.getString("phone_number"));
					salesReport.setSellprice(rs.getString("sell_price"));
					salesReport.setStatus(rs.getString("status"));
					salesReport.setProductName(rs.getString("product_name"));
					salesReport.setReturnstatus(rs.getString("return_status"));
					salesReport.setPaymentstatus(rs.getString("status2"));
					salesReport.setDeliverystatus(rs.getString("sold_status"));
					result.add(salesReport);
				}
			} else if (customerType.equalsIgnoreCase("multiple")) {
				logger.debug("============inside multiple else if==============");

				result = new ArrayList<SalesReport>();
				if (aLLcustomerName.equalsIgnoreCase("All")) {
					logger.debug("inside all++++++++++++++");
					preparedStatement = con
							.prepareStatement(ALLCUSTOMERNAME_QUERY);
					preparedStatement.setDate(1, sqldate);
					preparedStatement.setDate(2, sqldate1);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						SalesReport salesReport = new SalesReport();
						salesReport.setCustomerName(rs
								.getString("customer_name"));
						salesReport
								.setPhonenumber(rs.getString("phone_number"));
						salesReport.setOrderNumber(rs
								.getString("sales_order_number"));
						salesReport.setSellprice(rs.getString("sell_price"));
						salesReport.setQuantity(rs.getString("sold_quantity"));
						salesReport
								.setProductName(rs.getString("product_name"));
						salesReport.setReturnstatus(rs
								.getString("return_status"));
						salesReport.setPaymentstatus(rs.getString("status2"));
						salesReport.setDeliverystatus(rs
								.getString("sold_status"));
						result.add(salesReport);
					}
				} else if (aLLcustomerName.equalsIgnoreCase("Sales Delivery")) {
					logger.debug("inside sales Delivery++++++++++++++");
					preparedStatement = con
							.prepareStatement(SALESDELIVERED_QUERY);
					preparedStatement.setDate(1, sqldate);
					preparedStatement.setDate(2, sqldate1);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						SalesReport salesReport = new SalesReport();
						salesReport.setOrderNumber(rs
								.getString("sales_order_number"));
						salesReport
								.setProductName(rs.getString("product_name"));
						salesReport.setQuantity(rs.getString("sold_quantity"));
						salesReport.setCustomerName(rs
								.getString("customer_name"));
						salesReport
								.setPhonenumber(rs.getString("phone_number"));
						salesReport.setSellprice(rs.getString("sell_price"));
						salesReport.setDeliverystatus(rs.getString("status"));
						result.add(salesReport);
					}
				} else if (aLLcustomerName.equalsIgnoreCase("Sales Return")) {
					logger.debug("inside sales Return++++++++++++++");
					preparedStatement = con
							.prepareStatement(SALESRETURNED_QUERY);
					preparedStatement.setDate(1, sqldate);
					preparedStatement.setDate(2, sqldate1);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						SalesReport salesReport = new SalesReport();
						salesReport.setOrderNumber(rs
								.getString("sales_order_number"));
						salesReport
								.setProductName(rs.getString("product_name"));
						salesReport.setQuantity(rs.getString("sold_quantity"));
						salesReport.setCustomerName(rs
								.getString("customer_name"));
						salesReport
								.setPhonenumber(rs.getString("phone_number"));
						salesReport.setSellprice(rs.getString("sell_price"));
						salesReport.setNormalreturn(rs
								.getString("normal_return"));
						salesReport.setDamagereturn(rs
								.getString("damage_return"));
						salesReport.setReturnstatus(rs.getString("status"));
						salesReport.setReturndate(rs.getString("due_date"));
						result.add(salesReport);
					}
				}

			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			con.close();
		}
		return result;
	}

}
