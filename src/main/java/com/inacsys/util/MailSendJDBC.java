package com.inacsys.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.inacsys.domain.ClientDataBean;
import com.inacsys.managedBean.VendorRegisterFormMB;



public class MailSendJDBC {
	private static Logger logger = Logger.getLogger(MailSendJDBC.class);
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/PollerDB";
	//static final String DB_URL = "jdbc:mysql://localhost:3306/pollor";
	//static final String DB_URL = "jdbc:mysql://172.31.56.25:3306/PollerDB";
	static final String USER = "root";
	static final String PASS = "AJdata@123" ;
	//static final String PASS = "root" ;
	public static Connection con = null; 
	public static PreparedStatement preparedStatement=null; 
	public static Statement stmt=null;
	static final String Query ="insert into email_temp (toaddress,subject,message,status,pdf_path,pdf_file) values(?,?,?,?,?,?)";
	
	public static String clientInsert(ClientDataBean clientDataBean) throws SQLException, IOException{
		logger.debug("inside client send mail");
		try{
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, USER, PASS);
			stmt=con.createStatement();
			preparedStatement=con.prepareStatement(Query);
			preparedStatement.setString(1, clientDataBean.getMailID());
			preparedStatement.setString(2, "Confirmation"); 			
			/*preparedStatement.setString(3, "Congratulations! "+"<br/>"+"<br/>"
					+"You are Successfully On boarded with INACSYS to work for you so that you concentrate on matters for you. "+"<br/>"+"<br/>"+ 
					"Given below is your INACSYS details, "+"<br/>"+
					"URL &nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; http://35.166.255.46:7005/inacsys/ or http://35.166.255.46:7006/inacsys/"+"<br/>"
					+"For assistance write to contactus@ajtechi.com or call +971561638930");	*/
			
			preparedStatement.setString(3, "Congratulations! "
					+"You are Successfully On boarded with INACSYS to work for you so that you concentrate on matters for you. "+"<br/>"+"<br/>"+ 
					"Given below is your INACSYS details, "+
					"URL &nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; http://35.166.255.46:7005/inacsys/ or http://35.166.255.46:7006/inacsys/"
					+"For assistance write to contactus@ajtechi.com or call +971561638930");	
			
			
			
			preparedStatement.setString(4, "I");
			preparedStatement.setString(5, null);
			preparedStatement.setBytes(6, null);
			preparedStatement.executeUpdate();			
		}catch(Exception e){
			logger.warn(" exception - "+e);
		}finally{
			if (preparedStatement != null) preparedStatement.close();
			if (con != null) con.close();
		}
		return "";
	}

	public static void sendMailUsers(List<String> emailList, List<String> userpawd) throws SQLException {
		logger.debug("inside client send mail "+emailList.size());
		try{
			for (int i = 0; i < emailList.size(); i++) {
				String userPwd=userpawd.get(i);
				//mailSendUsers(emailList.get(i),userpawd.get(i));
				logger.debug("oo");
				Class.forName(JDBC_DRIVER);
				con=DriverManager.getConnection(DB_URL, USER, PASS);
				stmt=con.createStatement();
				preparedStatement=con.prepareStatement(Query);
				preparedStatement.setString(1, emailList.get(i));
				preparedStatement.setString(2, "Confirmation"); 
			/*	preparedStatement.setString(3, "Congratulations! "+"<br/>"+"<br/>"
						+"Now you can let INACSYS to work for you so that you concentrate on matters for you. "+"<br/>"+"<br/>"+ 
						"Given below is your INACSYS details, "+"<br/>"+
						"URL &nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; http://35.166.255.46:7005/inacsys/ or http://35.166.255.46:7006/inacsys/"+"<br/>"
						+"Username &nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; "+userPwd.split("/")[0]+"<br/>"
						+"password &nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; "+userPwd.split("/")[1]+"<br/>"+"<br/>"
						+"For assistance write to contactus@ajtechi.com or call +971561638930");	*/
				
				preparedStatement.setString(3, "Congratulations! "
						+"Now you can let INACSYS to work for you so that you concentrate on matters for you. "+"<br/>"+"<br/>"+ 
						"Given below is your INACSYS details, "+"<br/>"+
						"URL &nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; http://35.166.255.46:7005/inacsys/ or http://35.166.255.46:7006/inacsys/"+"<br/>"
						+"Username &nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; "+userPwd.split("/")[0]+"<br/>"
						+"password &nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; "+userPwd.split("/")[1]+"<br/>"+"<br/>"
						+"For assistance write to contactus@ajtechi.com or call +971561638930");	
				
				preparedStatement.setString(4, "I");
				preparedStatement.setString(5, null);
				preparedStatement.setBytes(6, null);
				preparedStatement.executeUpdate();	
			}		
		}catch(Exception e){
			logger.warn(" exception - "+e);
		}finally{
			if (preparedStatement != null) preparedStatement.close();
			if (con != null) con.close();
		}
	}

	private static void mailSendUsers(String mailId, String userPwd) throws SQLException {
		String username=userPwd.substring(userPwd.lastIndexOf("/"+0));
		String password=userPwd.substring(userPwd.lastIndexOf("/"+1));
		logger.debug("user "+username+" pwd "+password);
		try{	
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, USER, PASS);
			stmt=con.createStatement();
			preparedStatement=con.prepareStatement(Query);
			preparedStatement.setString(1, mailId);
			preparedStatement.setString(2, "Confirmation"); 			
			preparedStatement.setString(3, "Congratulations! "
					+"Now you can let INACSYS to work for you so that you concentrate on matters for you. "+"<br/>"+"<br/>"+ 
					"Given below is your INACSYS details, "+"<br/>"+
					"URL &nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; http://35.166.255.46:7005/inacsys/ or http://35.166.255.46:7006/inacsys/"+"<br/>"
					+"Username &nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; "+username+"<br/>"
					+"password &nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; "+password+"<br/>"+"<br/>"
					+"For assistance write to contactus@ajtechi.com or call +971561638930");			
			preparedStatement.setString(4, "I");
			preparedStatement.setString(5, null);
			preparedStatement.setBytes(6, null);
			preparedStatement.executeUpdate();	
		}catch(Exception e){
			logger.warn(" exception - "+e);
			logger.error("inside exception ",e);
		}finally{
			con.close();
		}	
	}
	
	public static void sendMailUsersUpdate(String createUserName,String createUserPwd, String createUserMail) throws SQLException {
		  System.out.println("user "+createUserName+" pwd "+createUserPwd+"mail "+createUserMail);
		  try{ 
			  Class.forName(JDBC_DRIVER);
			  con=DriverManager.getConnection(DB_URL, USER, PASS);
			  stmt=con.createStatement();
			  preparedStatement=con.prepareStatement(Query);
			  preparedStatement.setString(1, createUserMail);
			  preparedStatement.setString(2, "Confirmation");    
			  preparedStatement.setString(3, "Congratulations! "
					  +"Now you can let INACSYS to work for you so that you concentrate on matters for you. "+"<br/>"+"<br/>"+ 
					  "Given below is your Updated INACSYS details, "+"<br/>"+
					  "URL &nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp;or http://35.166.255.46:7005/inacsys/ or http://35.166.255.46:7006/inacsys/"+"<br/>"
					  +"Username &nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; "+createUserName+"<br/>"
					  +"password &nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp; "+createUserPwd+"<br/>"+"<br/>"
					  +"For assistance write to contactus@ajtechi.com or call +971561638930");   
			  preparedStatement.setString(4, "I");
			  preparedStatement.setString(5, null);
			  preparedStatement.setBytes(6, null);
			  preparedStatement.executeUpdate();
		  }catch(Exception e){
			  logger.warn(" exception - "+e);
			  logger.error("inside exception ",e);
		  }finally{
			  con.close();
		  } 
	}

public static String sendquotationVendorMail(List<String> mailList,List<VendorRegisterFormMB> quotationDetailList) throws SQLException {
	String status="Fail";
	try{
		for (int i = 0; i < mailList.size(); i++) {
			for (int j = 0; j < quotationDetailList.size(); j++) {
				if(mailList.get(i).equals(quotationDetailList.get(j).getVendorName())){
					if(!quotationDetailList.get(j).getVendormailid().equals("")){
						status=sendmailquotation(quotationDetailList,j);
					}
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	return status;	
}

private static String sendmailquotation(List<VendorRegisterFormMB> quotationDetailList, int j) throws SQLException {
	String status="Fail"; 
	try{ 
		  	Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, USER, PASS);
			stmt=con.createStatement();
			preparedStatement=con.prepareStatement(Query);
			preparedStatement.setString(1, quotationDetailList.get(j).getVendormailid());
			preparedStatement.setString(2, "Request for Quotation"); 			
			preparedStatement.setString(3, "<htm><head></heade><body>"
					+ " <header style='background-color:orange;color:white;height:50px;'>" + "<br></br>"
					+ "<center>" + "<h1> Request for Quotation</h1><br></br></center>" + "</center>"
					+ "</header>" 
					+ "<table bgcolor='orange' border='1' cellpadding='10' style='width:100%;border: 1px solid black;border-collapse: collapse;'>"
					+ "<tr>" + "<td> " + " Product Name" + "</td>" + "<td>" + " Product Count" + "</td>" + "<td>" + " Delivery Date" + "</td>" 
					+ "</tr>" + "<tr>"
					+ "<td> " + quotationDetailList.get(j).getProductName()
					+ "</td>" + "<td>" + quotationDetailList.get(j).getProductCount() + "</td>" + "<td>" + quotationDetailList.get(j).getDeliveryDate() + "</td>"
					+ "</tr>" 
					+ "</table>" + "</body></html>");			
			preparedStatement.setString(4, "I");
			preparedStatement.setString(5, null);
			preparedStatement.setBytes(6, null);
			preparedStatement.executeUpdate();	
			status="Success";
	  }catch(Exception e){
		  e.printStackTrace();
	  }finally{
		  con.close();
	  }
	return status; 
}
}
