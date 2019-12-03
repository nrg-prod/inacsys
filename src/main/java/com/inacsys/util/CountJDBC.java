package com.inacsys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.primefaces.model.chart.PieChartModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inacsys.domain.VendorCount;

public class CountJDBC {
	static final Logger logger = LoggerFactory.getLogger(CountJDBC.class);
	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	//static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
	//static final String USER = "root";
	//static final String PASS = "root";
	public static Connection con = null;
	public static Statement stmt = null;

	public static List<VendorCount> countSearch() throws SQLException {
		List<VendorCount> result = null;
		try {
			//ResourceBundle bundle = ResourceBundle.getBundle("database");
			//logger.debug("JDBC_DRIVER ::::"	+ bundle.getString("JDBC_DRIVER"));
			//logger.debug("DB_URL :::::::::" + bundle.getString("DB_URL"));
			//logger.debug("USER :::::::::::" + bundle.getString("USER"));
			//logger.debug("PASS ::::::::::: " + bundle.getString("PASS"));

			result = new ArrayList<VendorCount>();
			//Class.forName(JDBC_DRIVER);
			//con = DriverManager.getConnection(DB_URL, USER, PASS);
			
			con= Util.getConnection();	
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(Constants.VENDOR_VIEW);
			while (rs.next()) {
				VendorCount vendorcnt = new VendorCount();
				vendorcnt.setVendorcounter(rs.getInt("vendor_count"));
				vendorcnt.setCustomercounter(rs.getInt("customer_count"));
				result.add(vendorcnt);
			}

			con.close();

		} catch (Exception e) {
			logger.error("Inside Exception",e);	
		} finally {
			con.close();
		}
		return result;
	}
}
