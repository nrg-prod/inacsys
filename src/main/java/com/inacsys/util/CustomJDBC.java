package com.inacsys.util;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inacsys.domain.PurchaseOrder;

public class CustomJDBC {
	static final Logger logger = LoggerFactory.getLogger(CustomJDBC.class);
	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	//static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
	//static final String USER = "root";
	//static final String PASS = "root";
	static final String PURCHASE_QUERY = "select * from purchase_v where (purchase_status='insert' or purchase_status='delivered')";
	static final String ORDERPURCHASE_QUERY = "select * from purchase_v where tem_order_number like ? and (purchase_status='insert' or purchase_status='delivered')";
	static final String QUICKSALES_QUERY = "select * from salesorder_v where status3='Quick sales'";
	static final String ORDERQUICKSALES_QUERY = "select * from salesorder_v where order_no like ? and status3='Quick sales'";
	static final String SALES_QUERY = "select * from salesorder_v where status3='Normal sales'";
	static final String ORDERSALES_QUERY = "select * from salesorder_v where order_no like ? and status3='Normal sales'";
	public static String status;
	public static Connection con = null;
	public static PreparedStatement preparedStatement = null;
	public static Statement stmt = null;

	public static List<PurchaseOrder> findGlobalSearch(String name)
			throws SQLException {
		List<PurchaseOrder> result = null;
		try {
			result = new ArrayList<PurchaseOrder>();
			//Class.forName(JDBC_DRIVER);
			logger.debug("names " + name);
			logger.debug("name " + name.trim());
			//con = DriverManager.getConnection(DB_URL, USER, PASS);
			con= Util.getConnection();

			if (name.trim().equalsIgnoreCase("po")) {
				logger.debug("inside if");
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(PURCHASE_QUERY);
				while (rs.next()) {
					PurchaseOrder purchaseOrder = new PurchaseOrder();
					purchaseOrder.setVendorname(rs.getString("vendor_name"));
					purchaseOrder.setOrderDate(rs.getDate("order_date"));
					purchaseOrder.setOrderNumber(rs
							.getString("tem_order_number"));
					purchaseOrder.setTotalAmount(rs.getString("total_price"));
					purchaseOrder.setPhonenumber(rs.getString("phone_number"));
					purchaseOrder.setPaymentStatus(rs
							.getString("payment_status"));
					purchaseOrder.setDeliveredStatus(rs
							.getString("delivery_status"));
					result.add(purchaseOrder);
				}
			} else if (name.startsWith("PO")) {
				logger.debug("inside purchase else");
				preparedStatement = con.prepareStatement(ORDERPURCHASE_QUERY);
				preparedStatement.setString(1, "%" + name.trim() + "%");
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					PurchaseOrder purchaseOrder = new PurchaseOrder();
					purchaseOrder.setVendorname(rs.getString("vendor_name"));
					purchaseOrder.setOrderDate(rs.getDate("order_date"));
					purchaseOrder.setOrderNumber(rs
							.getString("tem_order_number"));
					purchaseOrder.setTotalAmount(rs.getString("total_price"));
					purchaseOrder.setPhonenumber(rs.getString("phone_number"));
					purchaseOrder.setPaymentStatus(rs
							.getString("payment_status"));
					purchaseOrder.setDeliveredStatus(rs
							.getString("delivery_status"));
					result.add(purchaseOrder);
				}
			}
			if (name.trim().equalsIgnoreCase("so")) {
				logger.debug("inside sales");
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SALES_QUERY);
				while (rs.next()) {
					PurchaseOrder purchaseOrder = new PurchaseOrder();
					purchaseOrder.setSalesorderdate(rs.getDate("order_date"));
					purchaseOrder.setSalesnum(rs.getString("order_no"));
					purchaseOrder
							.setCustomerName(rs.getString("customer_name"));
					purchaseOrder.setPhonenumber(rs.getString("phone_number"));
					purchaseOrder.setCrosstotal(rs.getString("total_amount"));
					purchaseOrder.setStatus2(rs.getString("payment"));
					purchaseOrder.setStatus(rs.getString("delivery_status"));
					result.add(purchaseOrder);
				}
			} else if (name.startsWith("SO")) {
				logger.debug("inside sales else");
				preparedStatement = con.prepareStatement(ORDERSALES_QUERY);
				preparedStatement.setString(1, "%" + name.trim() + "%");
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					PurchaseOrder purchaseOrder = new PurchaseOrder();
					purchaseOrder.setSalesorderdate(rs.getDate("order_date"));
					purchaseOrder.setSalesnum(rs.getString("order_no"));
					purchaseOrder
							.setCustomerName(rs.getString("customer_name"));
					purchaseOrder.setPhonenumber(rs.getString("phone_number"));
					purchaseOrder.setCrosstotal(rs.getString("total_amount"));
					purchaseOrder.setStatus2(rs.getString("payment"));
					purchaseOrder.setStatus(rs.getString("delivery_status"));
					result.add(purchaseOrder);
				}
			}
			if (name.trim().equalsIgnoreCase("qs")) {
				logger.debug("inside quick sales ");
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(QUICKSALES_QUERY);
				while (rs.next()) {
					PurchaseOrder purchaseOrder = new PurchaseOrder();
					purchaseOrder.setSalesorderdate(rs.getDate("order_date"));
					purchaseOrder.setSalesnum(rs.getString("order_no"));
					purchaseOrder.setCrosstotal(rs.getString("total_amount"));
					result.add(purchaseOrder);
				}
			} else if (name.startsWith("QS")) {
				logger.debug("inside quick sales else");
				preparedStatement = con.prepareStatement(ORDERQUICKSALES_QUERY);
				preparedStatement.setString(1, "%" + name.trim() + "%");
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					PurchaseOrder purchaseOrder = new PurchaseOrder();
					purchaseOrder.setSalesorderdate(rs.getDate("order_date"));
					purchaseOrder.setSalesnum(rs.getString("order_no"));
					purchaseOrder.setCrosstotal(rs.getString("total_amount"));
					result.add(purchaseOrder);
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
