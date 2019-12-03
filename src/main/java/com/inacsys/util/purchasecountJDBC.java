package com.inacsys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inacsys.domain.Chartcount;
import com.inacsys.domain.PurchasecountDomain;

public class purchasecountJDBC {
	static final Logger logger = LoggerFactory
			.getLogger(purchasecountJDBC.class);
	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	//static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
	//static final String USER = "root";
	//static final String PASS = "root";
	public static Connection con = null;
	public static Statement stmt = null;

	public static List<PurchasecountDomain> purchasecountSearch()
			throws SQLException {
		List<PurchasecountDomain> result = null;
		try {
			result = new ArrayList<PurchasecountDomain>();
			//Class.forName(JDBC_DRIVER);
			//con = DriverManager.getConnection(DB_URL, USER, PASS);
			con= Util.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from purchase_count");
			while (rs.next()) {
				PurchasecountDomain purchasecounts = new PurchasecountDomain();
				purchasecounts.setJan(rs.getInt("JAN"));
				purchasecounts.setFeb(rs.getInt("FEB"));
				purchasecounts.setMar(rs.getInt("MAR"));
				purchasecounts.setApril(rs.getInt("APRIL"));
				purchasecounts.setMay(rs.getInt("MAY"));
				purchasecounts.setJune(rs.getInt("JUNE"));
				purchasecounts.setJuly(rs.getInt("JULY"));
				purchasecounts.setAug(rs.getInt("AUG"));
				purchasecounts.setSep(rs.getInt("SEP"));
				purchasecounts.setOct(rs.getInt("OCT"));
				purchasecounts.setNov(rs.getInt("NOV"));
				purchasecounts.setDec(rs.getInt("DEC"));

				result.add(purchasecounts);
			}
			/*
			 * JAN | FEB | MAR | APRIL | MAY | JUNE | JULY | AUG | SEP | OCT |
			 * NOV | DEC
			 */

			con.close();

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			con.close();
		}
		return result;
	}
}
