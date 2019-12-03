package com.inacsys.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;



public class CurrencyConverter {
	public static Connection con = null;
	public static Statement stmt = null;
	public static String currencyConvertion(String currencyFrom, String currencyTo){
		try{
			System.out.println("inside convertion method");
			HttpClient httpclient = new DefaultHttpClient();
	        HttpGet httpGet = new HttpGet("http://quote.yahoo.com/d/quotes.csv?s=" + currencyFrom + currencyTo + "=X&f=l1&e=.csv");
	        ResponseHandler<String> responseHandler = new BasicResponseHandler();
	        String responseBody = httpclient.execute(httpGet, responseHandler);
	        httpclient.getConnectionManager().shutdown();
	        System.out.println("responsebody"+responseBody);
	        return responseBody;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public static BigDecimal findExchangeRateAndConvert(String from, String to, String amount) {
		String Query="";String value="";BigDecimal amounts=BigDecimal.valueOf(0);
			try {
				Query="select VALUE from currency_convertion_values where SOURCE_CURRENCY='"+from +"' AND DESTINATION_CURRENCY='"+to+"'";
				con= Util.getConnection();
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(Query);
				while(rs.next()){
					value=rs.getString(1);
				}  
		        if (value.length() > 0) {
		        	amounts=new BigDecimal(amount).multiply(new BigDecimal(value));
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }finally{
		    	if(con!=null){
		    		try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
		    	}
		    }
		return amounts;
	}

}
