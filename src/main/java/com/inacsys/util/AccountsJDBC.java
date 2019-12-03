package com.inacsys.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.formula.functions.Vlookup;

import com.inacsys.domain.AccountsDatabean;
import com.inacsys.domain.LoginAccess;
import com.inacsys.managedBean.ATransactionMB;
import com.inacsys.managedBean.LoginMB;
import com.inacsys.managedBean.VendorRegisterFormMB;

public class AccountsJDBC {
	
	public static Connection con = null;
	public static PreparedStatement preparedStatement = null;
	public static PreparedStatement preparedStatement1 = null;
	public static Statement stmt = null;
	private static Logger logger = Logger.getLogger(AccountsJDBC.class);
	public static List<AccountsDatabean> purchaseAccounts(String client_ID, AccountsDatabean accountsDatabean) throws SQLException{
		List<AccountsDatabean> accounts=new ArrayList<AccountsDatabean>();		
		try{			
			con= Util.getConnection();
			if(!accountsDatabean.getReportPeriod().equals("all")){
				if(accountsDatabean.getReportPeriod().equals("All dates")){
					preparedStatement = con.prepareStatement("select * from purchase_accounts_view where client_ID=?");
					preparedStatement.setString(1, client_ID);
				}else{
					java.sql.Date date = new java.sql.Date(accountsDatabean.getFromDate().getTime());
					java.sql.Date date1 = new java.sql.Date(accountsDatabean.getToDate().getTime());
					preparedStatement = con.prepareStatement("select * from purchase_accounts_view where client_ID=? and date between ? and ?");
					preparedStatement.setString(1, client_ID);
					preparedStatement.setDate(2, date);
					preparedStatement.setDate(3, date1);
				}	
				stmt = con.createStatement();
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					AccountsDatabean purchases=new AccountsDatabean();
					purchases.setAccount_date(rs.getDate("date"));
					purchases.setAccount_amount(rs.getString("total_price"));
					purchases.setAccount_description(rs.getString("account_description"));
					purchases.setAccount_type(rs.getString("account_type"));
					purchases.setAccount_number(rs.getString("order_number"));
					purchases.setBalance(rs.getString("balance_amount"));
					purchases.setCredit(new BigDecimal(rs.getString("paid_amount")));
					purchases.setStatus(rs.getString("pay_status"));
					accounts.add(purchases);
				}
				con.close();
			}	
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return accounts;
	}

	public static List<AccountsDatabean> salesAccounts(String clientID, AccountsDatabean accountsDatabean) throws SQLException {
		List<AccountsDatabean> accounts=new ArrayList<AccountsDatabean>();		
		try{			
			con= Util.getConnection();
			if(!accountsDatabean.getReportPeriod().equals("all")){
				if(accountsDatabean.getReportPeriod().equals("All dates")){
					preparedStatement = con.prepareStatement("select * from sales_accounts_view where client_ID=?");
					preparedStatement.setString(1, clientID);
				}else{
					java.sql.Date date = new java.sql.Date(accountsDatabean.getFromDate().getTime());
					java.sql.Date date1 = new java.sql.Date(accountsDatabean.getToDate().getTime());				
					preparedStatement = con.prepareStatement("select * from sales_accounts_view where client_ID=? and date between ? and ?");
					preparedStatement.setString(1, clientID);
					preparedStatement.setDate(2, date);
					preparedStatement.setDate(3, date1);
				}			
				stmt = con.createStatement();
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					AccountsDatabean purchases=new AccountsDatabean();
					purchases.setAccount_date(rs.getDate("date"));
					purchases.setAccount_amount(rs.getString("total_price"));
					purchases.setAccount_description(rs.getString("account_description"));
					purchases.setAccount_type(rs.getString("account_type"));
					purchases.setAccount_number(rs.getString("order_number"));
					purchases.setBalance(rs.getString("balance_amount"));
					purchases.setCredit(new BigDecimal(rs.getString("paid_amount")));
					purchases.setStatus(rs.getString("pay_status"));
					purchases.setAccount_name(rs.getString("type"));
					accounts.add(purchases);
				}
				con.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return accounts;
	}
	
	/*stanley changes*/

	public static List<VendorRegisterFormMB> getquotationList(String clientID,String userID, String userType, String approvalStatus) throws SQLException {
		List<VendorRegisterFormMB> list=new ArrayList<VendorRegisterFormMB>();		
		try{			
			con= Util.getConnection();
			if(userType.equals("Maker")){
				preparedStatement = con.prepareStatement("select * from quotation_console_v where client_ID=? and user_ID=? and status='Active' ORDER BY createdDate DESC");
				preparedStatement.setString(1, clientID);
				preparedStatement.setString(2, userID);
			}else{
				System.out.println("inside else condition"+userID);
				if (approvalStatus=="ApprovalData") {
					System.out.println("inside appeova data");
					preparedStatement = con.prepareStatement("select * from quotation_console_v where client_ID=? and status='Active' and approval_status='draft' ORDER BY createdDate DESC");
					preparedStatement.setString(1, clientID);
				}
				else{
				preparedStatement = con.prepareStatement("select * from quotation_console_v where client_ID=? and status='Active' ORDER BY createdDate DESC");
				preparedStatement.setString(1, clientID);
				}
			}
			stmt = con.createStatement();
			ResultSet rs = preparedStatement.executeQuery();
			int i=0;
			while (rs.next()) {
				VendorRegisterFormMB vendor=new VendorRegisterFormMB();
				vendor.setSerialno(String.valueOf(i+1));
				vendor.setQuotationNumber(rs.getString("quotation_number"));
				vendor.setStatus(rs.getString("status"));	
				vendor.setApprovalStatus(rs.getString("approval_status"));
				vendor.setOrderDate(rs.getDate("createdDate"));
				vendor.setUserID(rs.getString("user_ID"));
				vendor.setChoosenStatus(rs.getString("choosenStatus"));
				list.add(vendor);
				/*if(vendor.getUserID().equals(userID)){
					list.add(vendor);
				}else{
					if(vendor.getApprovalStatus().equals("draft")){
						list.add(vendor);
					}
				}*/
				i++;
			}
			
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return list;
	}

/*	John Clinton */
	
	public static List<AccountsDatabean> expenseCOA(String client_ID, AccountsDatabean accountsDatabean) throws SQLException{
		List<AccountsDatabean> accounts=new ArrayList<AccountsDatabean>();		
		System.out.println("expenseCOA----------->"+client_ID);
		try{			
			con= Util.getConnection();
		
					preparedStatement = con.prepareStatement("select * from expense_transaction_v where client_ID=? and account_name=? ORDER BY updated_date DESC");
					preparedStatement.setString(1, client_ID);
					preparedStatement.setString(2, accountsDatabean.getAccount_name());
				stmt = con.createStatement();
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					AccountsDatabean accountsObj=new AccountsDatabean();
					accountsObj.setAccount_date(rs.getDate("updated_date"));
					accountsObj.setRefNo(rs.getString("bill_number"));
					accountsObj.setTransactionType(rs.getString("transaction_type"));
					accountsObj.setPayeeName(rs.getString("name"));
					
					accountsObj.setAccount_name(rs.getString("account_name"));
					accountsObj.setAccountStatus(rs.getString("account_status"));
					accountsObj.setTransactionAmount(rs.getString("transaction_amount"));
					accountsObj.setPaymentbalance(rs.getString("balance_amount"));
					accountsObj.setBalance(rs.getString("balance"));
				
					accounts.add(accountsObj);
				}
				con.close();
			System.out.println("--------------->"+accounts.size());
			for (int i = 0; i < accounts.size(); i++) {
				System.out.println("----value------>"+accounts.get(i).getRefNo());
			
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return accounts;
	}
	
	/* Prema */
	
	public static void getSalesCOADetails(String clientID,AccountsDatabean accountsDatabean) throws SQLException {
		System.out.println("Inside getCOADetails method in AccountJDBC");
		List<AccountsDatabean> accountsDetails=null;
		try{
			accountsDetails=new ArrayList<AccountsDatabean>();
			con= Util.getConnection();
			preparedStatement = con.prepareStatement("select * from sales_transaction_v where client_ID=? and account_name=? and status='Active' and transaction_type!='Estimate'");
			preparedStatement.setString(1, clientID);
			preparedStatement.setString(2, accountsDatabean.getAccount_name());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				AccountsDatabean accountsObj=new AccountsDatabean();
				accountsObj.setAccount_date(rs.getDate("start_date"));
				accountsObj.setTransactionType(rs.getString("transaction_type"));
				accountsObj.setRefNo(rs.getString("reference_number"));
				accountsObj.setPayeeName(rs.getString("customer_name"));
				
				accountsObj.setAccount_name(rs.getString("account_name"));
				accountsObj.setAccountStatus(rs.getString("account_status"));
				accountsObj.setTransactionAmount(rs.getString("transaction_amount"));
				accountsObj.setPaymentbalance(rs.getString("balance_amount"));
				accountsObj.setBalance(rs.getString("balance"));
			
				accountsDetails.add(accountsObj);
			}
			accountsDatabean.setAccounts(accountsDetails);
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
	
	/* Neela */
	public static List<AccountsDatabean> expensetransaction(String client_ID, AccountsDatabean accountsDatabean) throws SQLException{
		List<AccountsDatabean> accounts=new ArrayList<AccountsDatabean>();		
		try{			
			con= Util.getConnection();
			System.out.println("----------"+client_ID);
			if(accountsDatabean.getReportPeriod().equals("All dates")){
				preparedStatement = con.prepareStatement("select * from expense_transaction_v where client_ID=?");
				preparedStatement.setString(1, client_ID);
			}else{
				java.sql.Date fromdate = new java.sql.Date(accountsDatabean.getFromDate().getTime());
				java.sql.Date todate = new java.sql.Date(accountsDatabean.getToDate().getTime());
				System.out.println(fromdate+"-------"+todate);
				preparedStatement = con.prepareStatement("select * from expense_transaction_v where client_ID=? and updated_date between ? and ? ");
				preparedStatement.setString(1, client_ID);
				preparedStatement.setDate(2, fromdate);
				preparedStatement.setDate(3, todate);
			}
					
			
				stmt = con.createStatement();
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					AccountsDatabean purchases=new AccountsDatabean();
					purchases.setAccount_date(rs.getDate("updated_date"));
					purchases.setAccount_transaction(rs.getString("transaction_type"));
					System.out.println("");
					purchases.setRefNo(rs.getString("bill_number"));
					purchases.setName(rs.getString("name"));
					purchases.setAccount_type(rs.getString("category_type"));
					purchases.setAccount_amount(rs.getString("transaction_amount"));
					purchases.setBalance(rs.getString("balance_amount"));
					try{
						if(!purchases.getAccount_transaction().equalsIgnoreCase(null)
								&& !rs.getDate("updated_date").equals(null)){
							purchases.setAccount_date(rs.getDate("updated_date"));
						}else{
							purchases.setAccount_description("Opening Balance Equity");
							purchases.setAccount_date(rs.getDate("coa_date"));
						}
					}catch(NullPointerException e){
						purchases.setAccount_date(rs.getDate("updated_date"));
						purchases.setAccount_date(rs.getDate("coa_date"));
						purchases.setAccount_description("Opening Balance Equity");
					}
					accounts.add(purchases);
				}
				con.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		System.out.println("EXPENSE accounts -----"+accounts.size());
		return accounts;
	}
	
	public static List<AccountsDatabean> salestransaction(String client_ID, AccountsDatabean accountsDatabean) throws SQLException{
		List<AccountsDatabean> accounts=new ArrayList<AccountsDatabean>();		
		try{			
			con= Util.getConnection();
			System.out.println("----------"+client_ID);
			if(accountsDatabean.getReportPeriod().equals("All dates")){
				preparedStatement = con.prepareStatement("select * from sales_transaction_v where client_ID=?");
				preparedStatement.setString(1, client_ID);
			}else{
				java.sql.Date fromdate = new java.sql.Date(accountsDatabean.getFromDate().getTime());
				java.sql.Date todate = new java.sql.Date(accountsDatabean.getToDate().getTime());
				System.out.println(fromdate+"-------"+todate);
				preparedStatement = con.prepareStatement("select * from sales_transaction_v where client_ID=? and start_date between ? and ? ");
				preparedStatement.setString(1, client_ID);
				preparedStatement.setDate(2, fromdate);
				preparedStatement.setDate(3, todate);
			}
			stmt = con.createStatement();
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					AccountsDatabean purchases=new AccountsDatabean();
					purchases.setAccount_transaction(rs.getString("transaction_type"));
					purchases.setRefNo(rs.getString("reference_number"));
					purchases.setName(rs.getString("account_name"));
					purchases.setAccount_type(rs.getString("category_type"));
					purchases.setAccount_amount(rs.getString("transaction_amount"));
					purchases.setBalance(rs.getString("balance"));
					try{
						if(!purchases.getAccount_transaction().equalsIgnoreCase(null)
								&& !rs.getDate("start_date").equals(null)){
						purchases.setAccount_date(rs.getDate("start_date"));
					}else{
						purchases.setAccount_description("Opening Balance Equity");
						purchases.setAccount_date(rs.getDate("coa_date"));
					}
					}catch(NullPointerException e){
						purchases.setAccount_date(rs.getDate("start_date"));
						purchases.setAccount_date(rs.getDate("coa_date"));
						purchases.setAccount_description("Opening Balance Equity");
					}
					accounts.add(purchases);
				}
				con.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		System.out.println("SALES accounts -----"+accounts.size());
		return accounts;
	}
	
	//Ragulan
		public static List<AccountsDatabean> salesProfitLoss(AccountsDatabean accountsDatabean) throws SQLException {
			System.out.println("--------------inside salesProfit Loss Method------------------------");
			List<AccountsDatabean> accounts=null;		
			try{	
				accounts=new ArrayList<AccountsDatabean>();
				con= Util.getConnection();
				if(!accountsDatabean.getReportPeriod().equals("all")){
					if(accountsDatabean.getReportPeriod().equals("All dates")){
						preparedStatement = con.prepareStatement("select * from sales_transaction_v where  client_ID=?");
						preparedStatement.setString(1, accountsDatabean.getClientID());
					}else{
						java.sql.Date date = new java.sql.Date(accountsDatabean.getFromDate().getTime());
						java.sql.Date date1 = new java.sql.Date(accountsDatabean.getToDate().getTime());
						preparedStatement = con.prepareStatement("select * from sales_transaction_v where client_ID=?and start_date between ? and ? and status='Active' and account_status!='' ");
						preparedStatement.setString(1, accountsDatabean.getClientID());
						preparedStatement.setDate(2, date);
						preparedStatement.setDate(3, date1);
					}	
					stmt = con.createStatement();
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						AccountsDatabean purchases=new AccountsDatabean();
						purchases.setTransactionAmount(rs.getString("transaction_amount"));
						purchases.setAccount_name(rs.getString("account_name"));
						purchases.setAccount_date(rs.getDate("start_date"));
						purchases.setRefNo(rs.getString("reference_number"));
						purchases.setTransactionType(rs.getString("transaction_type"));
						purchases.setName(rs.getString("customer_name"));
						purchases.setCatregoryType(rs.getString("category_type"));
						purchases.setBalance(rs.getString("balance_amount"));
						purchases.setPaymentAmount(rs.getString("paid_amount"));
						purchases.setAccountStatus(rs.getString("account_status"));
						purchases.setStatus(rs.getString("invoice_status"));
					
						accounts.add(purchases);
					}
					con.close();
				}	
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				con.close();
			}
			System.out.println("sales accounts size------------------>"+accounts.size());
			return accounts;
		}

		public static List<AccountsDatabean> expenseProfitLoss(AccountsDatabean accountsDatabean) throws SQLException {
			System.out.println("--------------inside expenseProfit Loss Method------------------------");
			List<AccountsDatabean> accounts=null;		
			try{	
				accounts=new ArrayList<AccountsDatabean>();
				con= Util.getConnection();
				if(!accountsDatabean.getReportPeriod().equals("all")){
					if(accountsDatabean.getReportPeriod().equals("All dates")){
						preparedStatement = con.prepareStatement("select * from expense_transaction_v where  client_ID=?");
						preparedStatement.setString(1, accountsDatabean.getClientID());
					}else{
						java.sql.Date date = new java.sql.Date(accountsDatabean.getFromDate().getTime());
						java.sql.Date date1 = new java.sql.Date(accountsDatabean.getToDate().getTime());
						preparedStatement = con.prepareStatement("select * from expense_transaction_v where client_ID=? and created_date between ? and ?");
						preparedStatement.setString(1, accountsDatabean.getClientID());
						preparedStatement.setDate(2, date);
						preparedStatement.setDate(3, date1);
					}	
					stmt = con.createStatement();
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						AccountsDatabean purchases=new AccountsDatabean();
						purchases.setTransactionAmount(rs.getString("transaction_amount"));
						purchases.setAccount_name(rs.getString("account_name"));
						purchases.setAccount_date(rs.getDate("created_date"));
						purchases.setRefNo(rs.getString("bill_number"));
						purchases.setTransactionType(rs.getString("transaction_type"));
						purchases.setName(rs.getString("name"));
						purchases.setCatregoryType(rs.getString("category_type"));
						purchases.setAccountStatus(rs.getString("account_status"));
						purchases.setBalance(rs.getString("balance"));
					
						accounts.add(purchases);
					}
					con.close();
				}	
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				con.close();
			}
			System.out.println("expense accounts size------------------>"+accounts.size());
			return accounts;
		}

		public static void getTrialBalance(String clientID,AccountsDatabean accountsDatabean) throws SQLException {
			List<AccountsDatabean> accounts=null;		
			//Map<String,List<AccountsDatabean>> map=null;
			try{
				accounts=new ArrayList<AccountsDatabean>();
				con= Util.getConnection();
				if(accountsDatabean.getReportPeriod().equals("All dates")){
					preparedStatement = con.prepareStatement("select * from sales_transaction_v where client_ID=?");
					preparedStatement.setString(1, clientID);
					ResultSet rs = preparedStatement.executeQuery();	
					//map = new HashMap<String,List<AccountsDatabean>>();
					//AccountsDatabean purchases=null;
					//List<AccountsDatabean> list = new ArrayList<AccountsDatabean>();
					while (rs.next()) {
						AccountsDatabean purchases=new AccountsDatabean();
						//list = new ArrayList<AccountsDatabean>();
						purchases.setTransactionAmount(rs.getString("transaction_amount"));
						purchases.setBalance(rs.getString("balance"));
						purchases.setName(rs.getString("account_name"));
						purchases.setAccount_type(rs.getString("category_type"));
						purchases.setTransactionType(rs.getString("transaction_type"));
						purchases.setAccount_amount(rs.getString("balance_amount"));
						purchases.setPaymentAmount(rs.getString("paid_amount"));
						accounts.add(purchases);
						
						//map.put(rs.getString("account_name"), list);
						
				}
				
				}
			
			accountsDatabean.setAccounts(accounts);
				con.close();		
				
				int count =1;
				
				/*Set<String> keys=map.keySet();
				
				
				
				
				  for (Map.Entry<String, List<AccountsDatabean>> e : map.entrySet()) {
					  String key = e.getKey();
					  System.out.println("Key "+count+"-->"+key);
					  List<AccountsDatabean> values = e.getValue();
					  System.out.println("values----->"+values);					  
					  //map.get(keys);
					  
					  for(AccountsDatabean bean:map.get(keys)){
						  System.out.println("Amount -->"+bean.getTransactionAmount());
						  System.out.println("Balance -->"+bean.getBalance());
						 // System.out.println(bean.getTransactionType());
						  //System.out.println(bean.getAccount_amount());
						  //System.out.println(bean.getPaymentAmount());
						  
					  }
				  count ++;  
				  }*/
				
			
				  
				/*  Set set = map.entrySet();
			      Iterator iterator = set.iterator();
			      while(iterator.hasNext()) {
			         Map.Entry mentry = (Map.Entry)iterator.next();
			         for (Map.Entry<String, List<String>> e : Server.entrySet()) {
			             if (e.getValue().contains(value)) {
			                 return e.getKey();
			             }
			         }
			         System.out.print("Key -------->"+ mentry.getKey());
			         System.out.println("Value ---->"+mentry.getValue());
			      }*/
			      
			//return map;
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				con.close();
			}
			//return map;

		}
		
		
		
/*john clinton*/
		/*john 06-28-2017*/
		public static List<AccountsDatabean> AccountsRecords(AccountsDatabean accountsDatabean) throws SQLException {
			List<AccountsDatabean> accounts=null;		
			try{	
				System.out.println("expenseRecords----method-->"+accountsDatabean.getReportPeriod()+"----------->"+accountsDatabean.getClientID());
				accounts=new ArrayList<AccountsDatabean>();
				con= Util.getConnection();
				if(accountsDatabean.getReportPeriod().equals("All dates")){
						preparedStatement = con.prepareStatement("select * from expense_transaction_v where client_ID=?");
						preparedStatement.setString(1, accountsDatabean.getClientID());
					}else{
						java.sql.Date date = new java.sql.Date(accountsDatabean.getFromDate().getTime());
						java.sql.Date date1 = new java.sql.Date(accountsDatabean.getToDate().getTime());
						preparedStatement = con.prepareStatement("select * from expense_transaction_v where client_ID=? and (updated_date between ? and ? or updated_date='')");
						preparedStatement.setString(1, accountsDatabean.getClientID());
						preparedStatement.setDate(2, date);
						preparedStatement.setDate(3, date1);
					}	
					stmt = con.createStatement();
					ResultSet rsExpense= preparedStatement.executeQuery();
					while (rsExpense.next()) {
						AccountsDatabean accountsname=new AccountsDatabean();
						accountsname.setTransactionAmount(rsExpense.getString("transaction_amount"));
						accountsname.setAccount_name(rsExpense.getString("account_name"));
						accountsname.setAccount_date(rsExpense.getDate("created_date"));
						accountsname.setTransactionType(rsExpense.getString("transaction_type"));
						accountsname.setCatregoryType(rsExpense.getString("category_type"));
						accountsname.setAccountStatus(rsExpense.getString("account_status"));
						accountsname.setBalance(rsExpense.getString("balance"));
						accounts.add(accountsname);
					}
					rsExpense.close();
				if(accountsDatabean.getReportPeriod().equals("All dates")){
						preparedStatement = con.prepareStatement("select * from sales_transaction_v where client_ID=?");
						preparedStatement.setString(1, accountsDatabean.getClientID());
					}else{
						java.sql.Date date = new java.sql.Date(accountsDatabean.getFromDate().getTime());
						java.sql.Date date1 = new java.sql.Date(accountsDatabean.getToDate().getTime());
						preparedStatement = con.prepareStatement("select * from sales_transaction_v where client_ID=? and (start_date between ? and ? or start_date='')");
						preparedStatement.setString(1, accountsDatabean.getClientID());
						preparedStatement.setDate(2, date);
						preparedStatement.setDate(3, date1);
					}	
					stmt = con.createStatement();
					ResultSet rsSales = preparedStatement.executeQuery();
					while (rsSales.next()) {
						AccountsDatabean accountsname=new AccountsDatabean();
						accountsname.setTransactionAmount(rsSales.getString("transaction_amount"));
						accountsname.setAccount_name(rsSales.getString("account_name"));
						accountsname.setAccount_date(rsSales.getDate("start_date"));
						accountsname.setTransactionType(rsSales.getString("transaction_type"));
						accountsname.setCatregoryType(rsSales.getString("category_type"));
						accountsname.setAccountStatus(rsSales.getString("account_status"));
						accountsname.setBalance(rsSales.getString("balance"));
						accounts.add(accountsname);
					}
					System.out.println("size account name------->"+accounts.size());
					con.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				con.close();
			}
			return accounts;
		}
		

	public static void indexesInsertion(int id,String phonenumber, String name, String email_ID, String module, String clientID, String userID) {
		System.out.println("-----------Inside indexesInsertion method calling");
		List<String> valueList=null;
	    try {
		       con= Util.getConnection();
		       String sql = "INSERT INTO indexes (module, value,client_ID,module_id,status,userID)" +"VALUES (?,?,?,?,?,?)";
		       PreparedStatement preparedStmt = con.prepareStatement(sql);
		       valueList=new ArrayList<String>();
		       valueList=Arrays.asList(name,phonenumber,email_ID);
		       for (String values : valueList) {
		    	   preparedStmt.setString(1, module);
			       preparedStmt.setString(2, values);
			       preparedStmt.setString(3, clientID);
			       preparedStmt.setInt(4, id);
			       preparedStmt.setString(5, "Active");
			       preparedStmt.setString(6, userID);
			       preparedStmt.execute();
		       }
		       System.out.println("Successfully Inserted");
		} catch(SQLException se) {
		    se.printStackTrace();
		} catch(Exception e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if(stmt != null)
		            con.close();
		    } catch(SQLException se) {
		    }
		    try {
		        if(con != null)
		            con.close();
		    } catch(SQLException se) {
		        se.printStackTrace();
		    }
		}
	}

	public static List<LoginAccess> getglobalsearchList(String clientID,String userID) throws SQLException {
		List<LoginAccess> valueList=null;List<LoginAccess> valueList1=null;
		try{
			valueList=new ArrayList<LoginAccess>();valueList1=new ArrayList<LoginAccess>();
			con= Util.getConnection();
			preparedStatement = con.prepareStatement("select * from indexs_v where client_ID=? and user_ID=?");
			preparedStatement.setString(1, clientID);
			preparedStatement.setString(2, userID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				LoginAccess login1=new LoginAccess();
				login1.setModulevalue(rs.getString("value1")+"/"+rs.getString("module"));
				login1.setModulename(rs.getString("module"));
				login1.setModuleid(rs.getString("module_id"));
				login1.setApprovalStatus(rs.getString("approval_status"));
				valueList1.add(login1);
				LoginAccess login2=new LoginAccess();
				login2.setModulevalue(rs.getString("value2")+"/"+rs.getString("module"));
				login2.setModulename(rs.getString("module"));
				login2.setModuleid(rs.getString("module_id"));
				login2.setApprovalStatus(rs.getString("approval_status"));
				valueList1.add(login2);
				LoginAccess login3=new LoginAccess();
				login3.setModulevalue(rs.getString("value3")+"/"+rs.getString("module"));
				login3.setModulename(rs.getString("module"));
				login3.setModuleid(rs.getString("module_id"));
				login3.setApprovalStatus(rs.getString("approval_status"));
				valueList1.add(login3);
			}
			valueList.addAll(valueList1);
		} catch(Exception e) {
		    e.printStackTrace();
		}finally{
			valueList1=null;
			con.close();
		}
		return valueList;
	}

	public static List<AccountsDatabean> getcoaDetailList(AccountsDatabean accountsDatabean) throws SQLException {
		logger.info("[getcoaDetailList()]----------------------Inside getcoaDetailList in JDBC Calling--------------------------");
		List<AccountsDatabean> coaDetailList=null;BigDecimal tempamt=BigDecimal.valueOf(0);
		try{
			coaDetailList=new ArrayList<AccountsDatabean>();
			con= Util.getConnection();
			List<String> list=Arrays.asList("Income","Other Income","Cost of Goods Sold","Expenses","Other Expenses");
			if(accountsDatabean.getAccount_name().equals("Opening Balance Equity")){
				preparedStatement = con.prepareStatement("select * from openingbalequity_details_v where client_ID=? and status=? order by tranDate desc");
				preparedStatement.setString(1, accountsDatabean.getClientID());
				preparedStatement.setString(2, "Active");
			}else if(accountsDatabean.getButtonValue().equals("Run report")){
				preparedStatement = con.prepareStatement("select * from coa_details_v where client_ID=? and account_name=? and status=?");
				preparedStatement.setString(1, accountsDatabean.getClientID());
				preparedStatement.setString(2, accountsDatabean.getAccount_name());
				preparedStatement.setString(3, "Active");
			}else if(accountsDatabean.getAccount_type().equals("Accounts Receivable (A/R)") || accountsDatabean.getAccount_type().equals("Accounts Payable (A/P)")){
				preparedStatement = con.prepareStatement("select * from accpayrec_details_v where client_ID=? and account_name=? and status=? order by tranDate desc");
				preparedStatement.setString(1, accountsDatabean.getClientID());
				preparedStatement.setString(2, accountsDatabean.getAccount_name());
				preparedStatement.setString(3, "Active");
			}else{
				preparedStatement = con.prepareStatement("select * from coa_details_v where client_ID=? and account_name=? and status=? order by tranDate desc");
				preparedStatement.setString(1, accountsDatabean.getClientID());
				preparedStatement.setString(2, accountsDatabean.getAccount_name());
				preparedStatement.setString(3, "Active");
			}
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				AccountsDatabean accountsObj=new AccountsDatabean();
				accountsObj.setAccount_date(rs.getDate("tranDate"));
				accountsObj.setTransactionType(rs.getString("transaction_type"));
				accountsObj.setRefNo(rs.getString("invoice_number"));
				accountsObj.setPayeeName(rs.getString("payee_name"));
				accountsObj.setAccount_type(rs.getString("has_coa_id"));
				accountsObj.setDepositAmount(rs.getString("debit_amount"));
				accountsObj.setPaymentAmount(rs.getString("credit_amount"));
				if(list.contains(accountsDatabean.getAccount_type())){
					tempamt=tempamt.add(new BigDecimal(rs.getString("balance")));
					accountsObj.setBalance(tempamt.toString());
				}else{
					accountsObj.setBalance(rs.getString("balance"));
				}
				accountsObj.setSubAccount(rs.getString("sub_account"));
				if(rs.getString("sub_account").equals("Accounts Receivable (A/R)") || rs.getString("sub_account").equals("Accounts Payable (A/P)")){
					accountsObj.setDueDate(rs.getDate("due_date"));
				}
				if(rs.getString("invoice_number").equals("Opening Balance Equity")){
					accountsObj.setAccount_description("Opening Balance");
				}
				if(rs.getString("account_name").equals("Opening Balance Equity")){
					accountsObj.setAccount_name(rs.getString("ref_account_name"));
				}else{
					accountsObj.setAccount_name(rs.getString("account_name"));
				}
				coaDetailList.add(accountsObj);
			}
		}catch(Exception e){
			logger.warn("----------------Inside Exception--------------------"+e.getMessage());
		}finally{
			con.close();
		}
		return coaDetailList;
	}

	public static List<AccountsDatabean> getprofitlossDetailList(AccountsDatabean accountsDatabean) throws SQLException {
		logger.info("[getprofitlossDetailList()]----------------------Inside getprofitlossDetailList in JDBC Calling--------------------------");
		List<AccountsDatabean> profitlosslist=null;
		try{
			profitlosslist=new ArrayList<AccountsDatabean>();
			con= Util.getConnection();
			if(accountsDatabean.getAccountStatus().equals("PL")){
				if(accountsDatabean.getReportPeriod().equals("All dates")){
					preparedStatement = con.prepareStatement("select *,sum(balance) from profitandloss_view where client_ID=? and status=? group by account_name");
					preparedStatement.setString(1, accountsDatabean.getClientID());
					preparedStatement.setString(2, "Active");
				}else {
					java.sql.Date fromdate = new java.sql.Date(accountsDatabean.getFromDate().getTime());
					java.sql.Date todate = new java.sql.Date(accountsDatabean.getToDate().getTime());
					preparedStatement = con.prepareStatement("select *,sum(balance) from profitandloss_view where client_ID=? and status=? and tranDate between ? and ? group by account_name");
					preparedStatement.setString(1, accountsDatabean.getClientID());
					preparedStatement.setString(2, "Active");
					preparedStatement.setDate(3, fromdate);
					preparedStatement.setDate(4, todate);
				}
			}else if(accountsDatabean.getAccountStatus().equals("PLBS")){
				/*preparedStatement = con.prepareStatement("select *,sum(balance) from profitandloss_view where client_ID=? and status=? group by account_name");
				preparedStatement.setString(1, accountsDatabean.getClientID());
				preparedStatement.setString(2, "Active");*/
				if(accountsDatabean.getReportPeriod().equals("All dates")){
					preparedStatement = con.prepareStatement("select *,sum(balance) from profitandloss_view where client_ID=? and status=? group by account_name");
					preparedStatement.setString(1, accountsDatabean.getClientID());
					preparedStatement.setString(2, "Active");
				}else {
					java.sql.Date fromdate = new java.sql.Date(accountsDatabean.getFromDate().getTime());
					java.sql.Date todate = new java.sql.Date(accountsDatabean.getToDate().getTime());
					preparedStatement = con.prepareStatement("select *,sum(balance) from profitandloss_view where client_ID=? and status=? and tranDate between ? and ? group by account_name");
					preparedStatement.setString(1, accountsDatabean.getClientID());
					preparedStatement.setString(2, "Active");
					preparedStatement.setDate(3, fromdate);
					preparedStatement.setDate(4, todate);
				}
			}
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				AccountsDatabean accountsObj=new AccountsDatabean();
				if(rs.getString("coa_status").equals("Active")){
					accountsObj.setAccount_name(rs.getString("account_name"));
				}else{
					accountsObj.setAccount_name(rs.getString("account_name")+""+"(deleted)");
				}
				accountsObj.setBalance(rs.getString("sum(balance)"));
				accountsObj.setSubAccount(rs.getString("sub_account"));
				accountsObj.setAccount_type(rs.getString("account"));
				profitlosslist.add(accountsObj);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("----------------Inside Exception--------------------"+e.getMessage());
		}finally{
			con.close();
		}
		return profitlosslist;
	}

	public static List<AccountsDatabean> gettrialbalandbalsheetList(AccountsDatabean accountsDatabean) throws SQLException {
		logger.info("[gettrialbalandbalsheetList()]----------------------Inside gettrialbalandbalsheetList in JDBC Calling--------------------------");
		List<AccountsDatabean> trialbalList=null;
		try{
			trialbalList=new ArrayList<AccountsDatabean>();
			con= Util.getConnection();
			if(accountsDatabean.getReportPeriod().equals("All dates")){
				preparedStatement = con.prepareStatement("select *,sum(balance) from trialbalance_view where client_ID=? and status=? group by account_name");
				preparedStatement.setString(1, accountsDatabean.getClientID());
				preparedStatement.setString(2, "Active");
			}else {
				java.sql.Date fromdate = new java.sql.Date(accountsDatabean.getFromDate().getTime());
				java.sql.Date todate = new java.sql.Date(accountsDatabean.getToDate().getTime());
				preparedStatement = con.prepareStatement("select *,sum(balance) from trialbalance_view where client_ID=? and status=? and tranDate between ? and ? group by account_name");
				preparedStatement.setString(1, accountsDatabean.getClientID());
				preparedStatement.setString(2, "Active");
				preparedStatement.setDate(3, fromdate);
				preparedStatement.setDate(4, todate);
			}
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				AccountsDatabean accountsObj=new AccountsDatabean();
				if(!rs.getString("account_name").equals("Opening Balance Equity")){
					if(rs.getString("coa_status").equals("Deactive") && Double.valueOf(rs.getString("sum(balance)")).equals("0")){
						accountsObj.setAccount_name(rs.getString("account_name")+""+"(deleted)");
					}else{
						accountsObj.setAccount_name(rs.getString("account_name"));
					}
				}else{
					accountsObj.setAccount_name(rs.getString("account_name"));
				}
				accountsObj.setBalance(rs.getString("sum(balance)"));
				accountsObj.setSubAccount(rs.getString("sub_account"));
				accountsObj.setAccount_type(rs.getString("account"));
				trialbalList.add(accountsObj);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("----------------Inside Exception--------------------"+e.getMessage());
		}finally{
			con.close();
		}
		return trialbalList;
	}

	public static void getgeneralLedgerList(AccountsDatabean accountsDatabean) throws SQLException {
		logger.info("[getgeneralLedgerList()]----------------------Inside getgeneralLedgerList in JDBC Calling--------------------------");
		try{
			List<String> list=Arrays.asList("Journal Entry","Cheque Expense","Credit Card Credit");
			accountsDatabean.setAccounts(new ArrayList<AccountsDatabean>());accountsDatabean.setAccountType(new ArrayList<String>());
			con= Util.getConnection();
			if(accountsDatabean.getReportPeriod().equals("All dates")){
				preparedStatement = con.prepareStatement("select * from trialbalance_view where client_ID=? and status=?");
				preparedStatement.setString(1, accountsDatabean.getClientID());
				preparedStatement.setString(2, "Active");
			}else {
				java.sql.Date fromdate = new java.sql.Date(accountsDatabean.getFromDate().getTime());
				java.sql.Date todate = new java.sql.Date(accountsDatabean.getToDate().getTime());
				preparedStatement = con.prepareStatement("select * from trialbalance_view where client_ID=? and status=? and tranDate between ? and ?");
				preparedStatement.setString(1, accountsDatabean.getClientID());
				preparedStatement.setString(2, "Active");
				preparedStatement.setDate(3, fromdate);
				preparedStatement.setDate(4, todate);
			}
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				AccountsDatabean accountsObj=new AccountsDatabean();
				if(rs.getString("coa_status").equals("Active")){
					accountsObj.setAccount_name(rs.getString("account_name"));
				}else if(rs.getString("coa_status").equals("Deactive")){
					if(rs.getString("account_name").equals("Opening Balance Equity")){
						accountsObj.setAccount_name(rs.getString("account_name"));
					}else{
						accountsObj.setAccount_name(rs.getString("account_name")+""+"(deleted)");
					}
				}
				accountsObj.setAccount_date(rs.getDate("tranDate"));
				accountsObj.setTransactionType(rs.getString("transaction_type"));
				accountsObj.setRefNo(rs.getString("invoice_number"));
				accountsObj.setPayeeName(rs.getString("payee_name"));
				accountsObj.setBalance(rs.getString("balance"));
				accountsObj.setSubAccount(rs.getString("sub_account"));
				accountsObj.setAccount_type(rs.getString("account"));
				accountsObj.setAccount_amount(rs.getString("balance"));
				accountsDatabean.getAccountType().add(accountsObj.getAccount_name());
				if(list.contains(rs.getString("transaction_type"))){
					accountsObj.setAccount_description("Adjust balance for deletion");
				}
				try{
					if(rs.getString("invoice_number").equals("Opening Balance Equity")){
						accountsObj.setAccount_description("Opening Balance");
					}
				}catch(Exception e){
					logger.warn("----------------Inside Exception--------------------"+e.getMessage());
				}
				accountsDatabean.getAccounts().add(accountsObj);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("----------------Inside Exception--------------------"+e.getMessage());
		}finally{
			con.close();
		}
	}

	public static List<AccountsDatabean> getGstCoaDetails(String clientID) {
		logger.info("[getGstCoaDetails()]----------------------Inside getGstCoaDetails in JDBC Calling--------------------------");
		List<AccountsDatabean> domainList=null;
		try{
			domainList=new ArrayList<AccountsDatabean>();
			con= Util.getConnection();
			preparedStatement = con.prepareStatement("select * from gstsumofbalance_v where client_ID=? and status=?");
			preparedStatement.setString(1, clientID);
			preparedStatement.setString(2, "Active");
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				AccountsDatabean domain=new AccountsDatabean();
				domain.setAccount_name(rs.getString("account_name"));
				domain.setAccount_type(rs.getString("category_type"));
				domain.setDetailType(rs.getString("detail_type"));
				domain.setAccount_date(rs.getDate("tran_date"));
				domain.setStatus(rs.getString("status"));
				domain.setBalance(rs.getString("balance"));
				domain.setButtonValue("Account history");
				domainList.add(domain);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("----------------Inside Exception--------------------"+e.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return domainList;
	}

}
