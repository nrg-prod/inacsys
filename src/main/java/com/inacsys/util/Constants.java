package com.inacsys.util;

public class Constants {
	/* Customer Report*/
	static final String MAKERCUSTOMERNAME_QUERY = "select * from customer_report where client_ID=? and user_ID=? and order_date between ? and ? and (sales_status=? or sales_status=?)";
	static final String ALLCUSTOMERNAME_QUERY = "select * from customer_report where client_ID=? and order_date between ? and ? and (sales_status=? or sales_status=?)";
	public static final String VENDOR_VIEW = "select * from vendor_v";
	/* Vendor Report*/
	static final String MAKERVENDORNAME_QUERY = "select * from vendor_report where client_ID=? and user_ID=? and order_date between ? and ? and status=?";
	static final String ALLVENDORNAME_QUERY = "select * from vendor_report where client_ID=? and order_date between ? and ? and status=?";
	/*Purchase Report*/
	static final String PURMAKERVENDORNAME_QUERY = "select * from purchase_report where client_ID=? and user_ID=? and order_date between ? and ? and (purchase_status=? or purchase_status=?)";
	static final String PURALLVENDORNAME_QUERY = "select * from purchase_report where client_ID=? and order_date between ? and ? and (purchase_status=? or purchase_status=?)";
	static final String MAKERPURRETURN_QUERY = "select * from purchaseret_report where client_ID=? and user_ID=? and return_date between ? and ?";
	static final String ALLPURRETURN_QUERY = "select * from purchaseret_report where client_ID=? and return_date between ? and ?";
	
	/* Sales Report */
	static final String MAKERSALESCUSTOMERNAME_QUERY = "select * from sales_report where client_ID=? and user_ID=? and sales_order_date between ? and ? and (status=? or status=?)";
	static final String ALLCUSTOMERSALESREPORT_QUERY = "select * from sales_report where client_ID=? and sales_order_date between ? and ? and (status=? or status=?)";
	static final String SALESDELIVERED_QUERY = "select * from sales_report where sales_order_date between ? and ? and (status1='Normal sales' and status='Delivered')";
	static final String SALESRETURNED_QUERY = "select * from sales_report where sales_order_date between ? and ? and (status1='Normal sales' and status='Returned')";
	
	static final String ALLPAYMENTTYPE_QUERY = "select * from sales_payment where payment_date between ? and ?";
	static final String CASHPAYMENTTYPE_QUERY = "select * from sales_payment where payment_date between ? and ? and payment_type='Cash'";
	static final String CARDPAYMENTTYPE_QUERY = "select * from sales_payment where payment_date between ? and ? and payment_type='Card'";
	static final String CHGEQUEPAYMENTTYPE_QUERY = "select * from sales_payment where payment_date between ? and ? and payment_type='Cheque'";
	static final String TRANSFERPAYMENTTYPE_QUERY = "select * from sales_payment where payment_date between ? and ? and payment_type='Transfer'";
	static final String CUSTOMERLIST_QUERY = "select * from sales_payment";
	static final String PAYCUSTOMERNAME_QUERY = "select * from sales_payment where customer_name like ? and payment_date between ? and ?";
	static final String CASHCUSTOMERNAME_QUERY = "select * from sales_payment where customer_name like ? and payment_date between ? and ? and payment_type='Cash'";
	static final String CARDCUSTOMERNAME_QUERY = "select * from sales_payment where customer_name like ? and payment_date between ? and ? and payment_type='Card'";
	static final String CHEQUECUSTOMERNAME_QUERY = "select * from sales_payment where customer_name like ? and payment_date between ? and ? and payment_type='Cheque'";
	static final String TRANSFERCUSTOMERNAME_QUERY = "select * from sales_payment where customer_name like ? and payment_date between ? and ? and payment_type='Transfer'";
	/*Payment Report*/
	static final String ALLPURPAYMENTTYPE_QUERY = "select * from purchase_payment where payment_date between ? and ?";
	static final String CASHPURPAYMENTTYPE_QUERY = "select * from purchase_payment where payment_date between ? and ? and payment_type='Cash'";
	static final String CARDPURPAYMENTTYPE_QUERY = "select * from purchase_payment where payment_date between ? and ? and payment_type='Card'";
	static final String CHGEQUEPURPAYMENTTYPE_QUERY = "select * from purchase_payment where payment_date between ? and ? and payment_type='Cheque'";
	static final String TRANSFERPURPAYMENTTYPE_QUERY = "select * from purchase_payment where payment_date between ? and ? and payment_type='Transfer'";
	static final String VENDORLIST_QUERY = "select * from purchase_payment";
	static final String PAYVENDORNAME_QUERY = "select * from purchase_payment where firm_name like ? and payment_date between ? and ?";
	static final String CASHVENDORNAME_QUERY = "select * from purchase_payment where firm_name like ? and payment_date between ? and ? and payment_type='Cash'";
	static final String CARDVENDORNAME_QUERY = "select * from purchase_payment where firm_name like ? and payment_date between ? and ? and payment_type='Card'";
	static final String CHEQUEVENDORNAME_QUERY = "select * from purchase_payment where firm_name like ? and payment_date between ? and ? and payment_type='Cheque'";
	static final String TRANSFERVENDORNAME_QUERY = "select * from purchase_payment where firm_name like ? and payment_date between ? and ? and payment_type='Transfer'";
	
	/* Stock Report */
	static final String STOCKREPORT_QUERY = "select * from stock_report where client_ID=? and stock_status=?";
}
