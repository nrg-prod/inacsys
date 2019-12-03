package com.inacsys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inacsys.domain.CustomerReport;
import com.inacsys.domain.PurchaseReport;
import com.inacsys.domain.ReportDatabean;
import com.inacsys.domain.SalesReport;
import com.inacsys.domain.StockReport;
import com.inacsys.domain.VendorReport;

public class ReportJDBC {

	static final Logger logger = LoggerFactory
			.getLogger(ReportJDBC.class);
	public static Connection con = null;
	public static PreparedStatement preparedStatement = null;
	public static Statement stmt = null;
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	/*Vendor Report*/
	public static void vendorReportSearch(ReportDatabean reportDatabean) throws SQLException {
		logger.info("[vendorReportSearch()]-------------------------------Inside vendorReportSearch() in JDBC Calling------------------------------");
		try{
			reportDatabean.setOrdernumberList(new ArrayList<String>());
			java.sql.Date sqldate = new java.sql.Date(reportDatabean.getFromDate().getTime());
			java.sql.Date sqldate1 = new java.sql.Date(reportDatabean.getToDate().getTime());
			con= Util.getConnection();
			if(reportDatabean.getUserType().equals("Maker")){
				preparedStatement = con.prepareStatement(Constants.MAKERVENDORNAME_QUERY);
				preparedStatement.setString(1, reportDatabean.getClientID());
				preparedStatement.setString(2, reportDatabean.getUserID());
				preparedStatement.setDate(3, sqldate);
				preparedStatement.setDate(4, sqldate1);
				preparedStatement.setString(5, "inserted");
			}else{
				preparedStatement = con.prepareStatement(Constants.ALLVENDORNAME_QUERY);
				preparedStatement.setString(1, reportDatabean.getClientID());
				preparedStatement.setDate(2, sqldate);
				preparedStatement.setDate(3, sqldate1);
				preparedStatement.setString(4, "inserted");
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ReportDatabean domain = new ReportDatabean();
				domain.setName(rs.getString("firm_name"));
				domain.setPhoneNumber(rs.getString("phone_number"));
				domain.setPrice(rs.getString("price"));
				domain.setQuantity(rs.getString("quantity"));
				domain.setOrderNumber(rs.getString("order_no"));
				domain.setProductName(rs.getString("product_name"));
				domain.setStatus(rs.getString("status"));
				if(reportDatabean.getType().equals("single")){
					if (!reportDatabean.getName().equals("")) {
						if(reportDatabean.getName().equals(rs.getString("firm_name"))){
							reportDatabean.getOrdernumberList().add(rs.getString("order_no"));
						}
					}
				}else if(reportDatabean.getType().equals("multiple")){
					if (!reportDatabean.getAllName().equals("")) {
						reportDatabean.getOrdernumberList().add(rs.getString("order_no"));
					}
				}
				reportDatabean.getReportList().add(domain);
			}
			HashSet<String> hashList = new HashSet<String>(reportDatabean.getOrdernumberList());
			reportDatabean.getOrdernumberList().clear();
			reportDatabean.getOrdernumberList().addAll(hashList);
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			con.close();
		}
	}
	
	/*Customer Report*/
	public static void customerReportsearch(ReportDatabean reportDatabean) throws SQLException {
		logger.info("[customerReportsearch()]-------------------------------Inside customerReportsearch() in JDBC Calling------------------------------");
		try{
			reportDatabean.setOrdernumberList(new ArrayList<String>());
			java.sql.Date sqldate = new java.sql.Date(reportDatabean.getFromDate().getTime());
			java.sql.Date sqldate1 = new java.sql.Date(reportDatabean.getToDate().getTime());
			con= Util.getConnection();
			if(reportDatabean.getUserType().equals("Maker")){
				preparedStatement = con.prepareStatement(Constants.MAKERCUSTOMERNAME_QUERY);
				preparedStatement.setString(1, reportDatabean.getClientID());
				preparedStatement.setString(2, reportDatabean.getUserID());
				preparedStatement.setDate(3, sqldate);
				preparedStatement.setDate(4, sqldate1);
				preparedStatement.setString(5, "insert");
				preparedStatement.setString(6, "Delivered");
			}else{
				preparedStatement = con.prepareStatement(Constants.ALLCUSTOMERNAME_QUERY);
				preparedStatement.setString(1, reportDatabean.getClientID());
				preparedStatement.setDate(2, sqldate);
				preparedStatement.setDate(3, sqldate1);
				preparedStatement.setString(4, "insert");
				preparedStatement.setString(5, "Delivered");
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ReportDatabean domain = new ReportDatabean();
				domain.setName(rs.getString("customer_name"));
				domain.setPhoneNumber(rs.getString("phone_number"));
				domain.setPrice(rs.getString("unit_price"));
				domain.setTotalPrice(rs.getString("net_amount"));
				domain.setQuantity(rs.getString("quantity"));
				domain.setOrderNumber(rs.getString("sales_order_no"));
				domain.setProductName(rs.getString("productname"));
				domain.setStatus(rs.getString("sales_status"));
				domain.setCrossTotal(rs.getString("cross_total"));
				domain.setShippingCost(rs.getString("shipping_cost"));
				if(reportDatabean.getType().equals("single")){
					if (!reportDatabean.getName().equals("")) {
						if(reportDatabean.getName().equals(rs.getString("customer_name"))){
							reportDatabean.getOrdernumberList().add(rs.getString("sales_order_no"));
						}
					}
				}else if(reportDatabean.getType().equals("multiple")){
					if (!reportDatabean.getAllName().equals("")) {
						reportDatabean.getOrdernumberList().add(rs.getString("sales_order_no"));
					}
				}
				reportDatabean.getReportList().add(domain);
			}
			HashSet<String> hashList = new HashSet<String>(reportDatabean.getOrdernumberList());
			reportDatabean.getOrdernumberList().clear();
			reportDatabean.getOrdernumberList().addAll(hashList);
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			con.close();
		}
	}
	
	/* Purchase Report*/
	
	public static void purchasereportsearch(ReportDatabean reportDatabean)throws SQLException {
		logger.info("[purchasereportsearch()]-------------------------------Inside purchasereportsearch() in JDBC Calling------------------------------");
		try{
			reportDatabean.setOrdernumberList(new ArrayList<String>());
			java.sql.Date sqldate = new java.sql.Date(reportDatabean.getFromDate().getTime());
			java.sql.Date sqldate1 = new java.sql.Date(reportDatabean.getToDate().getTime());
			con= Util.getConnection();
			if(reportDatabean.getUserType().equals("Maker")){
				if(reportDatabean.getAllName().equals("Purchase Return")){
					preparedStatement = con.prepareStatement(Constants.MAKERPURRETURN_QUERY);
					preparedStatement.setString(1, reportDatabean.getClientID());
					preparedStatement.setString(2, reportDatabean.getUserID());
					preparedStatement.setDate(3, sqldate);
					preparedStatement.setDate(4, sqldate1);
				}else{
					preparedStatement = con.prepareStatement(Constants.PURMAKERVENDORNAME_QUERY);
					preparedStatement.setString(1, reportDatabean.getClientID());
					preparedStatement.setString(2, reportDatabean.getUserID());
					preparedStatement.setDate(3, sqldate);
					preparedStatement.setDate(4, sqldate1);
					preparedStatement.setString(5, "insert");
					preparedStatement.setString(6, "delivered");
				}
			}else{
				if(reportDatabean.getAllName().equals("Purchase Return")){
					preparedStatement = con.prepareStatement(Constants.ALLPURRETURN_QUERY);
					preparedStatement.setString(1, reportDatabean.getClientID());
					preparedStatement.setDate(2, sqldate);
					preparedStatement.setDate(3, sqldate1);
				}else{
					preparedStatement = con.prepareStatement(Constants.PURALLVENDORNAME_QUERY);
					preparedStatement.setString(1, reportDatabean.getClientID());
					preparedStatement.setDate(2, sqldate);
					preparedStatement.setDate(3, sqldate1);
					preparedStatement.setString(4, "insert");
					preparedStatement.setString(5, "delivered");
				}
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ReportDatabean domain = new ReportDatabean();
				domain.setName(rs.getString("firm_name"));
				domain.setQuantity(rs.getString("quantity"));
				domain.setProductName(rs.getString("product_name"));
				domain.setOrderNumber(rs.getString("order_number"));
				if(reportDatabean.getAllName().equalsIgnoreCase("Purchase Return")){
					domain.setNormalReturn(rs.getString("normal_return"));
					domain.setDamageReturn(rs.getString("damage_return"));
					domain.setOrderDate(rs.getDate("return_date"));
				}else{
					domain.setPhoneNumber(rs.getString("phone_number"));
					domain.setPrice(rs.getString("product_price"));
					domain.setStatus(rs.getString("purchase_status"));
					domain.setPaymentStatus(rs.getString("record_status2"));
					domain.setOrderDate(rs.getDate("order_date"));
				}
				if(reportDatabean.getType().equals("single")){
					if (!reportDatabean.getName().equals("")) {
						if(reportDatabean.getName().equals(rs.getString("firm_name"))){
							reportDatabean.getOrdernumberList().add(rs.getString("order_number"));
						}
					}
				}else if(reportDatabean.getType().equals("multiple")){
					if (!reportDatabean.getAllName().equals("")) {
						reportDatabean.getOrdernumberList().add(rs.getString("order_number"));
					}
				}
				reportDatabean.getReportList().add(domain);
			}
			HashSet<String> hashList = new HashSet<String>(reportDatabean.getOrdernumberList());
			reportDatabean.getOrdernumberList().clear();
			reportDatabean.getOrdernumberList().addAll(hashList);
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			con.close();
		}
	}
	
	/* Sales Report */
	
	public static void salesreportsearch(ReportDatabean reportDatabean)throws SQLException {
		logger.info("[salesreportsearch()]-------------------------------Inside salesreportsearch() in JDBC Calling------------------------------");
		try{
			reportDatabean.setOrdernumberList(new ArrayList<String>());
			java.sql.Date sqldate = new java.sql.Date(reportDatabean.getFromDate().getTime());
			java.sql.Date sqldate1 = new java.sql.Date(reportDatabean.getToDate().getTime());
			con= Util.getConnection();
			if(reportDatabean.getUserType().equals("Maker")){
				preparedStatement = con.prepareStatement(Constants.MAKERSALESCUSTOMERNAME_QUERY);
				preparedStatement.setString(1, reportDatabean.getClientID());
				preparedStatement.setString(2, reportDatabean.getUserID());
				preparedStatement.setDate(3, sqldate);
				preparedStatement.setDate(4, sqldate1);
				preparedStatement.setString(5, "insert");
				preparedStatement.setString(6, "Delivered");
			}else{
				preparedStatement = con.prepareStatement(Constants.ALLCUSTOMERSALESREPORT_QUERY);
				preparedStatement.setString(1, reportDatabean.getClientID());
				preparedStatement.setDate(2, sqldate);
				preparedStatement.setDate(3, sqldate1);
				preparedStatement.setString(4, "insert");
				preparedStatement.setString(5, "Delivered");
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ReportDatabean domain = new ReportDatabean();
				domain.setName(rs.getString("customer_name"));
				domain.setQuantity(rs.getString("sold_quantity"));
				domain.setProductName(rs.getString("product_name"));
				domain.setPhoneNumber(rs.getString("phone_number"));
				domain.setOrderNumber(rs.getString("sales_order_number"));
				domain.setPrice(rs.getString("sell_price"));
				domain.setOrderDate(rs.getDate("sales_order_date"));
				domain.setStatus(rs.getString("status"));
				domain.setDeliveryStatus(rs.getString("status1"));
				reportDatabean.getReportList().add(domain);
				reportDatabean.getOrdernumberList().add(rs.getString("phone_number"));
			}
			HashSet<String> hashList = new HashSet<String>(reportDatabean.getOrdernumberList());
			reportDatabean.getOrdernumberList().clear();
			reportDatabean.getOrdernumberList().addAll(hashList);
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			con.close();
		}
	}
	
	/* Payment Report*/
	public static ArrayList<PurchaseReport> paymentreportsearch(
			String paymentType, String type, Date payFromDate, Date payToDate,
			String customertype, String vendortype) throws SQLException {
		ArrayList<PurchaseReport> result = null;
		try {
			java.sql.Date sqldate = new java.sql.Date(payFromDate.getTime());
			java.sql.Date sqldate1 = new java.sql.Date(payToDate.getTime());

		con= Util.getConnection();

			if (type.equalsIgnoreCase("Sales")) {
				logger.debug("==========inside sales if===========");
				if (paymentType.equalsIgnoreCase("All")) {
					if (customertype.equalsIgnoreCase("All")) {
						logger.debug("inside if all");
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.ALLPAYMENTTYPE_QUERY);
						preparedStatement.setDate(1, sqldate);
						preparedStatement.setDate(2, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setCustomerName(rs
									.getString("customer_name"));
							purchaseReport.setCustPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("cross_total"));
							purchaseReport.setPaymentStatus2(rs
									.getString("sales_pay_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setPaymentType(rs
									.getString("payment_type"));
							result.add(purchaseReport);
						}
					} else {
						logger.debug("inside if else");
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.PAYCUSTOMERNAME_QUERY);
						preparedStatement
								.setString(1, "%" + customertype + "%");
						preparedStatement.setDate(2, sqldate);
						preparedStatement.setDate(3, sqldate);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setCustomerName(rs
									.getString("customer_name"));
							purchaseReport.setCustPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("cross_total"));
							purchaseReport.setPaymentStatus2(rs
									.getString("sales_pay_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setPaymentType(rs
									.getString("payment_type"));
							result.add(purchaseReport);
						}
					}
				} else if (paymentType.equalsIgnoreCase("Cash")) {
					logger.debug("inside if cash");
					if (customertype.equalsIgnoreCase("All")) {
						logger.debug("all cash if");
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.CASHPAYMENTTYPE_QUERY);
						preparedStatement.setDate(1, sqldate);
						preparedStatement.setDate(2, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setCustomerName(rs
									.getString("customer_name"));
							purchaseReport.setCustPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("cross_total"));
							purchaseReport.setPaymentStatus2(rs
									.getString("sales_pay_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							result.add(purchaseReport);
						}
					} else {
						logger.debug("cash else");
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.CASHCUSTOMERNAME_QUERY);
						preparedStatement
								.setString(1, "%" + customertype + "%");
						preparedStatement.setDate(2, sqldate);
						preparedStatement.setDate(3, sqldate);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setCustomerName(rs
									.getString("customer_name"));
							purchaseReport.setCustPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("cross_total"));
							purchaseReport.setPaymentStatus2(rs
									.getString("sales_pay_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							result.add(purchaseReport);
						}
					}
				} else if (paymentType.equalsIgnoreCase("Card")) {
					logger.debug("inside if card");
					if (customertype.equalsIgnoreCase("All")) {
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.CARDPAYMENTTYPE_QUERY);
						preparedStatement.setDate(1, sqldate);
						preparedStatement.setDate(2, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setCustomerName(rs
									.getString("customer_name"));
							purchaseReport.setCustPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("cross_total"));
							purchaseReport.setPaymentStatus2(rs
									.getString("sales_pay_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setBankName(rs
									.getString("bank_name"));
							purchaseReport.setCardNumber(rs
									.getString("card_number"));
							result.add(purchaseReport);
						}
					} else {
						logger.debug(" card else");
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.CARDCUSTOMERNAME_QUERY);
						preparedStatement
								.setString(1, "%" + customertype + "%");
						preparedStatement.setDate(2, sqldate);
						preparedStatement.setDate(3, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setCustomerName(rs
									.getString("customer_name"));
							purchaseReport.setCustPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("cross_total"));
							purchaseReport.setPaymentStatus2(rs
									.getString("sales_pay_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setBankName(rs
									.getString("bank_name"));
							purchaseReport.setCardNumber(rs
									.getString("card_number"));
							result.add(purchaseReport);
						}
					}
				} else if (paymentType.equalsIgnoreCase("Cheque")) {
					logger.debug("inside if cheque");
					if (customertype.equalsIgnoreCase("All")) {
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.CHGEQUEPAYMENTTYPE_QUERY);
						preparedStatement.setDate(1, sqldate);
						preparedStatement.setDate(2, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setCustomerName(rs
									.getString("customer_name"));
							purchaseReport.setCustPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("cross_total"));
							purchaseReport.setPaymentStatus2(rs
									.getString("sales_pay_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setBankName(rs
									.getString("bank_name"));
							purchaseReport.setChequeNumber(rs
									.getString("cheque_no"));
							purchaseReport.setChequeDate(rs
									.getDate("cheque_date"));
							result.add(purchaseReport);
						}
					} else {
						logger.debug(" cheque else");
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.CHEQUECUSTOMERNAME_QUERY);
						preparedStatement
								.setString(1, "%" + customertype + "%");
						preparedStatement.setDate(2, sqldate);
						preparedStatement.setDate(3, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setCustomerName(rs
									.getString("customer_name"));
							purchaseReport.setCustPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("cross_total"));
							purchaseReport.setPaymentStatus2(rs
									.getString("sales_pay_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setBankName(rs
									.getString("bank_name"));
							purchaseReport.setChequeNumber(rs
									.getString("cheque_no"));
							purchaseReport.setChequeDate(rs
									.getDate("cheque_date"));
							result.add(purchaseReport);
						}
					}
				} else if (paymentType.equalsIgnoreCase("Transfer")) {
					logger.debug("inside if Transfer");
					if (customertype.equalsIgnoreCase("All")) {
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.TRANSFERPAYMENTTYPE_QUERY);
						preparedStatement.setDate(1, sqldate);
						preparedStatement.setDate(2, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setCustomerName(rs
									.getString("customer_name"));
							purchaseReport.setCustPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("cross_total"));
							purchaseReport.setPaymentStatus2(rs
									.getString("sales_pay_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setBankName(rs
									.getString("bank_name"));
							purchaseReport.setAccountNumber(rs
									.getString("account_no"));
							result.add(purchaseReport);
						}
					} else {
						logger.debug(" Transfer else");
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.TRANSFERCUSTOMERNAME_QUERY);
						preparedStatement
								.setString(1, "%" + customertype + "%");
						preparedStatement.setDate(2, sqldate);
						preparedStatement.setDate(3, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setCustomerName(rs
									.getString("customer_name"));
							purchaseReport.setCustPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("cross_total"));
							purchaseReport.setPaymentStatus2(rs
									.getString("sales_pay_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setBankName(rs
									.getString("bank_name"));
							purchaseReport.setAccountNumber(rs
									.getString("account_no"));
							result.add(purchaseReport);
						}
					}
				}
			} else {
				logger.debug("==========inside purchase else===========");

				logger.debug("==========inside sales if===========");
				if (paymentType.equalsIgnoreCase("All")) {
					if (vendortype.equalsIgnoreCase("All")) {
						logger.debug("inside if all");
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.ALLPURPAYMENTTYPE_QUERY);
						preparedStatement.setDate(1, sqldate);
						preparedStatement.setDate(2, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setVendorName(rs
									.getString("firm_name"));
							purchaseReport.setVendorPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("total_price"));
							purchaseReport.setPaymentStatus2(rs
									.getString("payment_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setPaymentType(rs
									.getString("payment_type"));
							result.add(purchaseReport);
						}
					} else {
						logger.debug("inside if else");
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.PAYVENDORNAME_QUERY);
						preparedStatement.setString(1, "%" + vendortype + "%");
						preparedStatement.setDate(2, sqldate);
						preparedStatement.setDate(3, sqldate);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setVendorName(rs
									.getString("firm_name"));
							purchaseReport.setVendorPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("total_price"));
							purchaseReport.setPaymentStatus2(rs
									.getString("payment_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setPaymentType(rs
									.getString("payment_type"));
							result.add(purchaseReport);
						}
					}
				} else if (paymentType.equalsIgnoreCase("Cash")) {
					logger.debug("inside if cash");
					if (vendortype.equalsIgnoreCase("All")) {
						logger.debug("all cash if");
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.CASHPURPAYMENTTYPE_QUERY);
						preparedStatement.setDate(1, sqldate);
						preparedStatement.setDate(2, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setVendorName(rs
									.getString("firm_name"));
							purchaseReport.setVendorPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("total_price"));
							purchaseReport.setPaymentStatus2(rs
									.getString("payment_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							result.add(purchaseReport);
						}
					} else {
						logger.debug("cash else");
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.CASHVENDORNAME_QUERY);
						preparedStatement.setString(1, "%" + vendortype + "%");
						preparedStatement.setDate(2, sqldate);
						preparedStatement.setDate(3, sqldate);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setVendorName(rs
									.getString("firm_name"));
							purchaseReport.setVendorPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("total_price"));
							purchaseReport.setPaymentStatus2(rs
									.getString("payment_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							result.add(purchaseReport);
						}
					}
				} else if (paymentType.equalsIgnoreCase("Card")) {
					logger.debug("inside if card");
					if (vendortype.equalsIgnoreCase("All")) {
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.CARDPURPAYMENTTYPE_QUERY);
						preparedStatement.setDate(1, sqldate);
						preparedStatement.setDate(2, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setVendorName(rs
									.getString("firm_name"));
							purchaseReport.setVendorPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("total_price"));
							purchaseReport.setPaymentStatus2(rs
									.getString("payment_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setBankName(rs
									.getString("bank_name"));
							purchaseReport.setCardNumber(rs
									.getString("card_number"));
							result.add(purchaseReport);
						}
					} else {
						logger.debug(" card else");
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.CARDVENDORNAME_QUERY);
						preparedStatement.setString(1, "%" + vendortype + "%");
						preparedStatement.setDate(2, sqldate);
						preparedStatement.setDate(3, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setVendorName(rs
									.getString("firm_name"));
							purchaseReport.setVendorPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("total_price"));
							purchaseReport.setPaymentStatus2(rs
									.getString("payment_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setBankName(rs
									.getString("bank_name"));
							purchaseReport.setCardNumber(rs
									.getString("card_number"));
							result.add(purchaseReport);
						}
					}
				} else if (paymentType.equalsIgnoreCase("Cheque")) {
					logger.debug("inside if cheque");
					if (vendortype.equalsIgnoreCase("All")) {
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.CHGEQUEPURPAYMENTTYPE_QUERY);
						preparedStatement.setDate(1, sqldate);
						preparedStatement.setDate(2, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setVendorName(rs
									.getString("firm_name"));
							purchaseReport.setVendorPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("total_price"));
							purchaseReport.setPaymentStatus2(rs
									.getString("payment_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setBankName(rs
									.getString("bank_name"));
							purchaseReport.setChequeNumber(rs
									.getString("cheque_no"));
							purchaseReport.setChequeDate(rs
									.getDate("cheque_date"));
							result.add(purchaseReport);
						}
					} else {
						logger.debug(" cheque else");
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.CHEQUEVENDORNAME_QUERY);
						preparedStatement.setString(1, "%" + vendortype + "%");
						preparedStatement.setDate(2, sqldate);
						preparedStatement.setDate(3, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setVendorName(rs
									.getString("firm_name"));
							purchaseReport.setVendorPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("total_price"));
							purchaseReport.setPaymentStatus2(rs
									.getString("payment_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setBankName(rs
									.getString("bank_name"));
							purchaseReport.setChequeNumber(rs
									.getString("cheque_no"));
							purchaseReport.setChequeDate(rs
									.getDate("cheque_date"));
							result.add(purchaseReport);
						}
					}
				} else if (paymentType.equalsIgnoreCase("Transfer")) {
					logger.debug("inside if Transfer");
					if (vendortype.equalsIgnoreCase("All")) {
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.TRANSFERPURPAYMENTTYPE_QUERY);
						preparedStatement.setDate(1, sqldate);
						preparedStatement.setDate(2, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setVendorName(rs
									.getString("firm_name"));
							purchaseReport.setVendorPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("total_price"));
							purchaseReport.setPaymentStatus2(rs
									.getString("payment_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setBankName(rs
									.getString("bank_name"));
							purchaseReport.setAccountNumber(rs
									.getString("account_no"));
							result.add(purchaseReport);
						}
					} else {
						logger.debug(" Transfer else");
						result = new ArrayList<PurchaseReport>();
						preparedStatement = con
								.prepareStatement(Constants.TRANSFERVENDORNAME_QUERY);
						preparedStatement.setString(1, "%" + vendortype + "%");
						preparedStatement.setDate(2, sqldate);
						preparedStatement.setDate(3, sqldate1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							PurchaseReport purchaseReport = new PurchaseReport();
							purchaseReport.setPaymentDate(rs
									.getDate("payment_date"));
							purchaseReport.setVendorName(rs
									.getString("firm_name"));
							purchaseReport.setVendorPhoneNumber(rs
									.getString("phone_number"));
							purchaseReport.setCrossTotal(rs
									.getString("total_price"));
							purchaseReport.setPaymentStatus2(rs
									.getString("payment_status"));
							purchaseReport.setPayableAmount(rs
									.getString("paid_amount"));
							purchaseReport.setBalanceAmount(rs
									.getString("balance_amount"));
							purchaseReport.setBankName(rs
									.getString("bank_name"));
							purchaseReport.setAccountNumber(rs
									.getString("account_no"));
							result.add(purchaseReport);
						}
					}
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

	public static ArrayList<PurchaseReport> customerNameList() {
		ArrayList<PurchaseReport> customerList = null;
		try {
			con= Util.getConnection();
			customerList = new ArrayList<PurchaseReport>();
			preparedStatement = con.prepareStatement(Constants.CUSTOMERLIST_QUERY);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PurchaseReport purchaseReport = new PurchaseReport();
				purchaseReport.setCustomerName(rs.getString("customer_name"));
				purchaseReport.setCustPhoneNumber(rs.getString("phone_number"));
				customerList.add(purchaseReport);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return customerList;
	}

	public static ArrayList<PurchaseReport> vendorNameList() {
		ArrayList<PurchaseReport> vendorList = null;
		try {
			con= Util.getConnection();
			vendorList = new ArrayList<PurchaseReport>();
			preparedStatement = con.prepareStatement(Constants.VENDORLIST_QUERY);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PurchaseReport purchaseReport = new PurchaseReport();
				purchaseReport.setVendorName(rs.getString("firm_name"));
				purchaseReport.setVendorPhoneNumber(rs
						.getString("phone_number"));
				vendorList.add(purchaseReport);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return vendorList;
	}

	/* Stock Report */
	public static void stockReportSearch(ReportDatabean reportDatabean) throws SQLException {
		logger.info("[stockReportSearch()]-------------------------------Inside stockReportSearch() in JDBC Calling------------------------------");
		try{
			con= Util.getConnection();
			preparedStatement = con.prepareStatement(Constants.STOCKREPORT_QUERY);
			preparedStatement.setString(1, reportDatabean.getClientID());
			preparedStatement.setString(2, "insert");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ReportDatabean domain = new ReportDatabean();
				domain.setPrice(rs.getString("Unit_price"));
				domain.setQuantity(rs.getString("quantity"));
				domain.setProductName(rs.getString("product_name"));
				domain.setStockinQuantity(rs.getString("stock_in"));
				domain.setStockoutQuantity(rs.getString("stock_out"));
				domain.setStockdamageQuantity(rs.getString("damge_status"));
				reportDatabean.getReportList().add(domain);
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			con.close();
		}
	}
	
}
