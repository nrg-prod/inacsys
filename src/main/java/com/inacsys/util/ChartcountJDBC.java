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

;

public class ChartcountJDBC {
	static final Logger logger = LoggerFactory.getLogger(ChartcountJDBC.class);

	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	//static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
	//static final String USER = "root";
	//static final String PASS = "root";
	public static Connection con = null;
	public static Statement stmt = null;

	public static List<Chartcount> countSearch() throws SQLException {
		List<Chartcount> result = null;
		try {
			result = new ArrayList<Chartcount>();
			//Class.forName(JDBC_DRIVER);
			//con = DriverManager.getConnection(DB_URL, USER, PASS);
			con= Util.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from table_v");
			while (rs.next()) {
				Chartcount chartcounts = new Chartcount();
				chartcounts.setSalesCount(rs.getInt("SALES"));
				chartcounts.setPurchaseCount(rs.getInt("PURCHASE"));
				chartcounts.setStockInCount(rs.getInt("STOCK IN"));
				chartcounts.setStockOutCount(rs.getInt("STOCK OUT"));
				chartcounts.setPurchaseProductCount(rs
						.getInt("PURCHASE_PRODUCT"));
				chartcounts.setSalesProductCount(rs.getInt("SALE_PRODUCT"));
				chartcounts
						.setStockInProductCount(rs.getInt("STOCKIN_PRODUCT"));
				chartcounts.setStockOutProductCount(rs
						.getInt("STOCKOUT_PRODUCT"));
				result.add(chartcounts);
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
