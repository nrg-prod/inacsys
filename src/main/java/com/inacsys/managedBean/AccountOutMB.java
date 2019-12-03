package com.inacsys.managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.AccountsDatabean;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0023;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "accountOutMB")
public class AccountOutMB implements Serializable {
	/**
	 * 
	 */
	@ManagedProperty(value = "#{purchaseViewMB}")
	PurchaseViewMB purchaseViewMB;

	public PurchaseViewMB getPurchaseViewMB() {
		return purchaseViewMB;
	}

	public void setPurchaseViewMB(PurchaseViewMB purchaseViewMB) {
		this.purchaseViewMB = purchaseViewMB;
	}

	private static final long serialVersionUID = 1L;
	public ArrayList<String> ordernumber = null;
	public String order;
	String orderNumbervalue;
	public ArrayList<I0023> purchaselist;
	public ArrayList<I0023> purchaselis;
	public ArrayList<I0023> purchaseli;
	String s;
	public String productName;
	public String productName1;
	public String vendor;
	public String vendor1;
	public int productId;
	public String vendornumber;
	public String orderdate;
	public String paidAmount;
	public String payableAmount;
	public String balanceAmount;
	public String remaining;
	public String flag = "none";
	DemoController controller = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();
	public String validate1;
	public String paymentTerms;
	public String payType;
	public String payflag = "none";
	public String payflag1 = "none";
	public String payflag2 = "none";
	public String payflag3 = "none";
	public String payflag4 = "none";
	private List<String> accountType1=null;
	public String  accountdescriptionvalue;

	
	
	public List<String> getAccountType1() {
		return accountType1;
	}

	public void setAccountType1(List<String> accountType1) {
		this.accountType1 = accountType1;
	}

	public String getAccountdescriptionvalue() {
		return accountdescriptionvalue;
	}

	public void setAccountdescriptionvalue(String accountdescriptionvalue) {
		this.accountdescriptionvalue = accountdescriptionvalue;
	}

	public String getPayflag2() {
		return payflag2;
	}

	public void setPayflag2(String payflag2) {
		this.payflag2 = payflag2;
	}

	public String getPayflag3() {
		return payflag3;
	}

	public void setPayflag3(String payflag3) {
		this.payflag3 = payflag3;
	}

	public String getPayflag4() {
		return payflag4;
	}

	public void setPayflag4(String payflag4) {
		this.payflag4 = payflag4;
	}

	public String getPayflag1() {
		return payflag1;
	}

	public void setPayflag1(String payflag1) {
		this.payflag1 = payflag1;
	}

	public String getPayflag() {
		return payflag;
	}

	public void setPayflag(String payflag) {
		this.payflag = payflag;
	}

	public String paymentType;
	public String bankname;
	public String cardno;
	public String chequeno;
	public Date chequedate;
	public String accno;

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getChequeno() {
		return chequeno;
	}

	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public List<I0023> list = null;

	public List<I0023> getList() {
		return list;
	}

	public void setList(List<I0023> list) {
		this.list = list;
	}

	public Date getChequedate() {
		return chequedate;
	}

	public void setChequedate(Date chequedate) {
		this.chequedate = chequedate;
	}

	
	public String accountType;
	public ArrayList<String> accountDescription = null;
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public ArrayList<String> getAccountDescription() {
		return accountDescription;
	}

	public void setAccountDescription(ArrayList<String> accountDescription) {
		this.accountDescription = accountDescription;
	}
	
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	
	public String getValidate1() {
		return validate1;
	}

	public void setValidate1(String validate1) {
		this.validate1 = validate1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRemaining() {
		return remaining;
	}

	public void setRemaining(String remaining) {
		this.remaining = remaining;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(String payableAmount) {
		this.payableAmount = payableAmount;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getVendornumber() {
		return vendornumber;
	}

	public void setVendornumber(String vendornumber) {
		this.vendornumber = vendornumber;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductName1() {
		return productName1;
	}

	public void setProductName1(String productName1) {
		this.productName1 = productName1;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getVendor1() {
		return vendor1;
	}

	public void setVendor1(String vendor1) {
		this.vendor1 = vendor1;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public ArrayList<I0023> getPurchaseli() {
		return purchaseli;
	}

	public void setPurchaseli(ArrayList<I0023> purchaseli) {
		this.purchaseli = purchaseli;
	}

	public ArrayList<I0023> getPurchaselis() {
		return purchaselis;
	}

	public void setPurchaselis(ArrayList<I0023> purchaselis) {
		this.purchaselis = purchaselis;
	}

	public ArrayList<I0023> getPurchaselist() {
		return purchaselist;
	}

	public void setPurchaselist(ArrayList<I0023> purchaselist) {
		this.purchaselist = purchaselist;
	}

	public String getOrderNumbervalue() {
		return orderNumbervalue;
	}

	public void setOrderNumbervalue(String orderNumbervalue) {
		this.orderNumbervalue = orderNumbervalue;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public ArrayList<String> getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(ArrayList<String> ordernumber) {
		this.ordernumber = ordernumber;
	}

	public DemoController getController() {
		return controller;
	}

	public void setController(DemoController controller) {
		this.controller = controller;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String validate;

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	private static Logger logger = Logger.getLogger(AccountOutMB.class);

	public String direct1() {
		logger.info("[direct1()] --------------- Inside direct1() method() ------------------------");
		try {
			validate = "";
			orderNumbervalue = "";
			setChequeno("");
			setCardno("");
			setAccno("");
			setBankname("");
			setPaymentType("");
			setChequedate(null);
			setOrderNumbervalue("");
			setFlag("none");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesDelete();
			logger.info("[direct1()] --------------- Outside direct1() method() ------------------------");
		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
		}finally {
			controller =null;
		}

		return "";
	}

	public String dropAccount() {
		logger.info("[dropAccount()] --------------- Inside dropAccount() method() ------------------------");
		try {
			ordernumber = new ArrayList<String>();
			String s = "PURCHASE";
			purchaseOrder.setVendor_phone_number(s);
			logger.debug("[dropAccount()] s Size ------------------------>"+s);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			ordernumber = controller.dropAccount(s, ordernumber);
			logger.debug("[dropAccount()] order number Size ------------------------>"+ordernumber.size());
			logger.debug("[dropAccount()] order number values ------------------------>"+ordernumber);
			if (ordernumber.size() == 0) {
				throw new DemoException("* No purchase order is present");
			}
			logger.info("[dropAccount()] --------------- Outside dropAccount() method() ------------------------");
			return "successdrop";
		} catch (DemoException e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception"+e.getMessage());
			return "";
		}finally {
			controller =null;
		}
	}

	public String AccountOut() {
		logger.info("[AccountOut()] --------------- Inside AccountOut() method() ------------------------");
		try {
			validate = null;
			flag = "none";
			purchaselist = null;
			logger.debug("order number----------->"
					+ purchaseViewMB.orderNumber);
			purchaseOrder.setOrderNumber(purchaseViewMB.orderNumber);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist = controller.AccountOut(purchaseOrder, purchaselist);
			if (purchaselist == null) {
				flag = "none";
				logger.info("[AccountOut()] --------------- Inside AccountOut() method() purchase null------------------------");
				setValidate("Please Check the Purchase Order Number");
				throw new DemoException(
						"Please Check the Purchase Order Number ");
			} else {
				flag = "1";
			}
			int i = 0;
			productName = "Product Name:";
			vendor = "Vendor Name:";
			productName1 = purchaselist.get(i).getI0022().getI0015()
					.getI0016s().get(0).getI0031().getI0001().getProductName();
			vendornumber = purchaselist.get(i).getI0022().getI0015()
					.getI0016s().get(0).getI0031().getI0025()
					.getVendorPhoneNumber();
			logger.debug("[AccountOut()] s Size ------------------------>"+s);
			logger.info("[AccountOut()] --------------- Outside AccountOut() method() ------------------------");
			return "";
		} catch (DemoException e) {
			setValidate(e.getMessage());
			logger.error("log--------->" + e.getStackTrace());
			return "failureAccountOut";
		} catch (Exception e) {
			logger.error("log-------->" + e.getStackTrace());
			return "failure1AccountOut";
		} finally {
			controller =null;
		}
	}

	public void purchasepay() {
		logger.info("[purchasepay()] --------------- Inside purchasepay() method() ------------------------");
		logger.debug("[purchasepay()] balanceAmount ------------------------>"+balanceAmount);
		if(balanceAmount.equals("0") || balanceAmount.equals("-0")){
			RequestContext.getCurrentInstance().execute("PF('payDialog').show();");
		}else{
			Map<String, Object> options = new HashMap<String, Object>();
			options.put("modal", true);
			options.put("draggable", true);
			options.put("resizable", false);
			options.put("contentHeight", 500);
			options.put("contentWidth", 800);
			RequestContext.getCurrentInstance().openDialog("accountOut1", options,
					null);
			payNow();
		}
	}
	public void purchasepayclose() {
		RequestContext.getCurrentInstance().closeDialog("accountOut1");
		RequestContext.getCurrentInstance().execute("PF('purpayment').hide();");
	}

	public String payNow() {
		logger.info("[payNow()] --------------- Inside payNow() method() ------------------------");
		try {
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			remaining = null;
			flag = "none";
			payType="";
			validate = null;
			int i = 0;payType="";
			accountType1=new ArrayList<String>();
			AccountsDatabean accountsDatabean=new AccountsDatabean();
			accountsDatabean.setStatus("COA add");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.getAccountTypes(clientID,accountsDatabean);	
			setAccountType1(accountsDatabean.getAccountType());
			list = controller.paymentamount(purchaseOrder);
			productName1 = purchaselist.get(i).getI0022().getI0015().getI0016s().get(0).getI0031().getI0001().getProductName();
			vendor1 = purchaselist.get(i).getI0022().getI0015().getI0016s().get(0).getI0031().getI0025().getFirmName();
			productId = purchaselist.get(i).getI0022().getI0015().getI0016s().get(0).getI0031().getI0001().getProduct_ID();
			vendornumber = purchaselist.get(i).getI0022().getI0015().getI0016s().get(0).getI0031().getI0025().getVendorPhoneNumber();
			paidAmount = purchaselist.get(i).getPaidAmount();
			balanceAmount = purchaselist.get(i).getBalanceAmount();
			payableAmount = purchaselist.get(i).getPayableAmount();
			accountType = purchaselist.get(i).getAccountType();
			paymentTerms=purchaseOrder.getPaymentTerms();
			setAccountDescription(null);
			logger.debug("[payNow()] balanceAmount ------------------------>"+balanceAmount);
			if (!list.get(i).getPaidAmount().equals("0")) {
				logger.info("[payNow()] --------------- inside paid amount ------------------------");
				payflag = "1";
				payflag1 = "none";
				payflag2 = "none";
				payflag3 = "none";
				payflag4 = "none";
				accountdescriptionvalue = list.get(i).getAccountDescription();
				paymentType = list.get(0).getPaymentType();
				logger.debug("type >>-- " + getPaymentType() + paymentType);
			} else if (list.get(i).getPaidAmount().equals("0")) {
				logger.info("[payNow()]--------------- no paid amount ------------------------");
				payflag1 = "1";
				payflag = "none";
				payflag2 = "1";
				payflag3 = "1";
				payflag4 = "1";
				accountType="";				
			}
			if (new BigDecimal(balanceAmount).compareTo(BigDecimal.valueOf(0)) == 0) {
				throw new DemoException("*The payment is already finished");

			}
			logger.info("[payNow()] --------------- Outside AccountOut Paynow ------------------------");
			return "";
		} catch (DemoException ie) {
			logger.error(ie.getMessage());
			return "";
		} catch (Exception e) {
			flag = "none";
			return "failure";
		}finally {
			controller =null;
		}
	}

	public void accountTypeChange(ValueChangeEvent vv) {		
		logger.info("[accountTypeChange()] --------------- Inside accountTypeChange() method() ------------------------");
		try {
			String accounttype=vv.getNewValue().toString();
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			accountDescription=controller.accountdescription(accounttype);
		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
		}
	}

	public String payNow1() {
		logger.info("[payNow1()] --------------- Inside payNow1() method() ------------------------");
		try {
			flag = "none";
			if(paymentTerms.equals("Cash")){
				logger.info("[payNow1()] --------------- Inside payNow1() method() if condition cash------------------------");
				if (accountType==null) {
					throw new DemoException("*Please choose the Account Type");
				} else if (accountType!=null){
					if(accountdescriptionvalue==null){
						throw new DemoException("*Please choose the Account Description");	
					}
				}
				setRemaining(balanceAmount);
			}else{
				if (balanceAmount.equals(payableAmount)){
					logger.info("[payNow1()] --------------- Inside payNow1() method() if condition payableamount------------------------");
					if(payType.equals("")){
						throw new DemoException("Please Select Amount Paying Type");
					}
					if (paymentType.equals("")) {
						throw new DemoException("*Please choose the Payment Mode");
					}else if (accountType==null) {
						throw new DemoException("*Please choose the Account Type");
					} else if (accountType!=null){
						if(accountdescriptionvalue==null){
							throw new DemoException("*Please choose the Account Description");	
						}
					}
					else if (paymentType.equals("Cash")) {

					} else if (paymentType.equals("Card")) {
						logger.info("[payNow1()] --------------- Inside payNow1() method() if condition card------------------------");
						if (bankname.equals("")) {
							throw new DemoException("*Enter Bank Name");
						} else if (cardno.equals("")) {
							throw new DemoException("*Enter Card Number");
						}
					} else if (paymentType.equals("Cheque")) {
						logger.info("[payNow1()] --------------- Inside payNow1() method() if condition cheque------------------------");
						if (bankname.equals("")) {
							throw new DemoException("*Enter Bank Name");
						} else if (chequeno.equals("")) {
							throw new DemoException("*Enter Cheque Number");
						} else if (chequedate == null) {
							throw new DemoException("*Enter Cheque Date");
						}
					} else if (paymentType.equals("Transfer")) {
						logger.info("[payNow1()] --------------- Inside payNow1() method() if condition transfer------------------------");
						if (bankname.equals("")) {
							throw new DemoException("*Enter Bank Name");
						} else if (accno.equals("")) {
							throw new DemoException("*Enter Account Number");
						}
					}
				} else if (!balanceAmount.equals(payableAmount)) {
					logger.info("[payNow1()] --------------- Inside payNow1() method() paid amount------------------------");
					accno = list.get(0).getAccountNo();
					cardno = list.get(0).getCardNo();
					chequedate = list.get(0).getChequeDate();
					chequeno = list.get(0).getChequeNo();
					bankname = list.get(0).getBankName();
				}
			}			
			purchaseOrder.setBankname(bankname);
			purchaseOrder.setCardno(cardno);
			purchaseOrder.setChequedate(chequedate);
			purchaseOrder.setChequeno(chequeno);
			purchaseOrder.setPaymentType(paymentType);
			purchaseOrder.setAccno(accno);
			purchaseOrder.setRemaining(remaining);
			purchaseOrder.setPayableAmount("" + payableAmount);
			purchaseOrder.setAccounttype(accountType);
			purchaseOrder.setAccountdescription(accountdescriptionvalue);
			logger.debug("orderNumbervalue--------->" + orderNumbervalue);
			purchaseOrder.setOrderNumber(purchaseViewMB.orderNumber);
			purchaseOrder.setPaymentTerms(paymentTerms);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.payNow1(purchaseOrder);
			RequestContext.getCurrentInstance().execute(
					"PF('purpayment').show();");
			logger.info("[payNow1()] --------------- Outside payNow1() method() ------------------------");
			return "";
		} catch (DemoException e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception"+e.getMessage());
			return "failure2";
		}finally {
			controller =null;
		}
	}

	public void payTypeChange(ValueChangeEvent v){
		logger.info("[payTypeChange()] --------------- Inside payTypeChange() method()------------------------");
		logger.debug("[payTypeChange()] --------------- Inside payTypeChange() method() type------------------------>"+v.getNewValue().toString());
		payType=v.getNewValue().toString();
		BigDecimal paidAmount=BigDecimal.valueOf(0);
		if(payType.equals("25%")){
			logger.info("[payTypeChange()] --------------- Inside payTypeChange() method() payType 25%------------------------");
			paidAmount=(new BigDecimal(balanceAmount).multiply(BigDecimal.valueOf(25))).divide(BigDecimal.valueOf(100));
		}else if(payType.equals("50%")){
			logger.info("[payTypeChange()] --------------- Inside payTypeChange() method() payType 50%------------------------");
			paidAmount=(new BigDecimal(balanceAmount).multiply(BigDecimal.valueOf(50))).divide(BigDecimal.valueOf(100));
		}else if(payType.equals("75%")){
			logger.info("[payTypeChange()] --------------- Inside payTypeChange() method() payType 75%------------------------");
			paidAmount=(new BigDecimal(balanceAmount).multiply(BigDecimal.valueOf(75))).divide(BigDecimal.valueOf(100));
		}else if(payType.equals("100%")){
			logger.info("[payTypeChange()] --------------- Inside payTypeChange() method() payType 100%------------------------");
			paidAmount=(new BigDecimal(balanceAmount).multiply(BigDecimal.valueOf(100))).divide(BigDecimal.valueOf(100));
		}
		logger.debug("[payTypeChange()] --------------- Inside payTypeChange() method() paid amount------------------------>"+paidAmount);
		setRemaining(String.valueOf(paidAmount));
	}
	
	public void purchasepayment() {
		logger.info("[purchasepayment()] --------------- Inside purchasepayment() method()------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("accountOut", options,
				null);
		direct1();
	}

	// prema end 02/05/2016
	public void purchasepaymentclose() {
		RequestContext.getCurrentInstance().closeDialog("accountOut");
	}
}
