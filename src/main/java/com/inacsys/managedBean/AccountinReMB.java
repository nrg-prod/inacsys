package com.inacsys.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.AccountsDatabean;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.ChartOfAccount;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0021;
import com.inacsys.shared.I0023;

/**
 * This Java Class will communicate with InventoryService
 * --------------------------------------------------------salespayment1----
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 * 
 */

@ManagedBean(name = "accountinReMB")
public class AccountinReMB implements Serializable {
	@ManagedProperty(value = "#{salesViewMB}")
	SalesViewMB salesViewMB;

	public SalesViewMB getSalesViewMB() {
		return salesViewMB;
	}

	public void setSalesViewMB(SalesViewMB salesViewMB) {
		this.salesViewMB = salesViewMB;
	}

	private static final long serialVersionUID = 1L;
	List<I0021> result;
	public String salereferencenumber;
	public String customername;
	public String countryID;
	public Date salesorderdate;
	public String shipingaddress;
	public String phonenumber;
	public String email;
	public String totalnumberofcount;
	public String totalnumberofcount1;
	public String paidamount;
	public String totalamount;
	public String balanceamount;
	public Date deliverydate;
	public String note;
	public String shipping_charge;
	public String crosstotal;
	public String shipping_charge1;
	public String crosstotal1;
	public int salesId;
	public String remaining;
	public String validate;
	public String flag = "none";
	DemoController controller = null;
	List<I0023> resu;
	List<I0019> resul;
	PurchaseOrder purchaseOrder = new PurchaseOrder();
	public ArrayList<I0021> drop = null;
	public String acflag="none";
	public String acflag1="none";
	public String paidFlag="none";
	public String balanceFlag="none";
	public String payingFlag="none";
	public ArrayList<String> accountDescription = null;
	public String accountType;
	public String accountdescriptionvalue;
	public List<I0023> list = null;
	private List<String> accountType1=null;

	public List<String> getAccountType1() {
		return accountType1;
	}

	public void setAccountType1(List<String> accountType1) {
		this.accountType1 = accountType1;
	}

	public List<I0023> getList() {
		return list;
	}

	public void setList(List<I0023> list) {
		this.list = list;
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

	public Date getChequedate() {
		return chequedate;
	}

	public void setChequedate(Date chequedate) {
		this.chequedate = chequedate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String payflag = "none";
	public String payflag1 = "none";
	public String payflag2 = "none";
	public String payflag3 = "none";
	public String payflag4 = "none";

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
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountdescriptionvalue() {
		return accountdescriptionvalue;
	}

	public void setAccountdescriptionvalue(String accountdescriptionvalue) {
		this.accountdescriptionvalue = accountdescriptionvalue;
	}
	
	public String getPaidFlag() {
		return paidFlag;
	}

	public void setPaidFlag(String paidFlag) {
		this.paidFlag = paidFlag;
	}

	public String getBalanceFlag() {
		return balanceFlag;
	}

	public void setBalanceFlag(String balanceFlag) {
		this.balanceFlag = balanceFlag;
	}

	public String getPayingFlag() {
		return payingFlag;
	}

	public void setPayingFlag(String payingFlag) {
		this.payingFlag = payingFlag;
	}	
	public ArrayList<String> getAccountDescription() {
		return accountDescription;
	}

	public void setAccountDescription(ArrayList<String> accountDescription) {
		this.accountDescription = accountDescription;
	}
	public String getAcflag() {
		return acflag;
	}

	public void setAcflag(String acflag) {
		this.acflag = acflag;
	}

	public String getAcflag1() {
		return acflag1;
	}

	public void setAcflag1(String acflag1) {
		this.acflag1 = acflag1;
	}


	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getRemaining() {
		return remaining;
	}

	public void setRemaining(String remaining) {
		this.remaining = remaining;
	}

	public String getPaidamount() {
		return paidamount;
	}

	public void setPaidamount(String paidamount) {
		this.paidamount = paidamount;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	public String getBalanceamount() {
		return balanceamount;
	}

	public void setBalanceamount(String balanceamount) {
		this.balanceamount = balanceamount;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCountryID() {
		return countryID;
	}

	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}

	public Date getSalesorderdate() {
		return salesorderdate;
	}

	public void setSalesorderdate(Date salesorderdate) {
		this.salesorderdate = salesorderdate;
	}

	public String getShipingaddress() {
		return shipingaddress;
	}

	public void setShipingaddress(String shipingaddress) {
		this.shipingaddress = shipingaddress;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTotalnumberofcount() {
		return totalnumberofcount;
	}

	public void setTotalnumberofcount(String totalnumberofcount) {
		this.totalnumberofcount = totalnumberofcount;
	}

	public String getTotalnumberofcount1() {
		return totalnumberofcount1;
	}

	public void setTotalnumberofcount1(String totalnumberofcount1) {
		this.totalnumberofcount1 = totalnumberofcount1;
	}

	public Date getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getShipping_charge() {
		return shipping_charge;
	}

	public void setShipping_charge(String shipping_charge) {
		this.shipping_charge = shipping_charge;
	}

	public String getCrosstotal() {
		return crosstotal;
	}

	public void setCrosstotal(String crosstotal) {
		this.crosstotal = crosstotal;
	}

	public String getShipping_charge1() {
		return shipping_charge1;
	}

	public void setShipping_charge1(String shipping_charge1) {
		this.shipping_charge1 = shipping_charge1;
	}

	public String getCrosstotal1() {
		return crosstotal1;
	}

	public void setCrosstotal1(String crosstotal1) {
		this.crosstotal1 = crosstotal1;
	}

	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
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

	public String getSalereferencenumber() {
		return salereferencenumber;
	}

	public void setSalereferencenumber(String salereferencenumber) {
		this.salereferencenumber = salereferencenumber;
	}

	public List<I0021> getResult() {
		return result;
	}

	public void setResult(List<I0021> result) {
		this.result = result;
	}

	public List<I0023> getResu() {
		return resu;
	}

	public void setResu(List<I0023> resu) {
		this.resu = resu;
	}

	public List<I0019> getResul() {
		return resul;
	}

	public void setResul(List<I0019> resul) {
		this.resul = resul;
	}

	public ArrayList<I0021> getDrop() {
		return drop;
	}

	public void setDrop(ArrayList<I0021> drop) {
		this.drop = drop;
	}

	private static Logger logger = Logger.getLogger(AccountinReMB.class);

	public String direct() {
		logger.info("[direct()] --------------- Inside direct() method() ------------------------");
		try {
			validate = "";
			salereferencenumber = "";
			setChequeno("");
			setCardno("");
			setAccno("");
			setBankname("");
			setChequedate(null);
			setPaymentType("");
			logger.info("[direct()] inside direct------------------>");
			setSalereferencenumber("");
			setFlag("none");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesDelete();
		} catch (Exception e) {
			logger.error("[direct()] Inside Exception"+e.getMessage());
		} finally {
			controller =null;
		}

		return "";
	}

	public String accountDrop() {
		try {
			logger.info("[accountDrop()] --------------- Inside accountDrop() method() ------------------------");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			drop = controller.accountin(drop);
			if (drop.size() == 0) {
				logger.info("[accountDrop()] --------------- Inside accountDrop() method() if condition ------------------------");
				throw new DemoException("* No sales order is present");
			}
			return "";
		} catch (DemoException e) {
			logger.error("[accountDrop()] Inside Exception"+e.getMessage());
			setValidate(e.getMessage());
			return "";
		} finally {
			controller =null;
		}
	}

	public String accountin() {
		logger.info("[accountin()] --------------- Inside accountin() method() ------------------------");
		try {
			validate = null;
			logger.debug("[accountin()] Sales reference number ------------------------>"+ salereferencenumber);
			purchaseOrder.setSalesIdReference(salesViewMB.salereferencenumber);
			purchaseOrder.setResul(resul);
			purchaseOrder.setResu(resu);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.accountin(purchaseOrder);
			setResu(purchaseOrder.getResu());
			return "";
		} catch (DemoException e) {
			flag = "none";
			logger.error("[accountin()] Inside Exception"+e.getMessage());
			setValidate(e.getMessage());
			return "";
		} finally {
			controller =null;
		}
	}

	public void accountTypeChange(ValueChangeEvent vv) {
		logger.info("[accountTypeChange()] --------------- Inside accountTypeChange() method() ------------------------");
		String accounttype=vv.getNewValue().toString();List<ChartOfAccount> accountlist=null;
		try {
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			accountlist=new ArrayList<ChartOfAccount>();
			//accountDescription=controller.accountdescription(accounttype);
			accountDescription=new ArrayList<String>();
			if(accounttype.equalsIgnoreCase("select")){
				accountDescription=new ArrayList<String>();
			}else{
				accountlist=controller.accountlist(clientID);
				if(accountlist.size()>0){
					 for (int i = 0; i < accountlist.size(); i++) {
						if(accountlist.get(i).getAccountType().getCategoryType().equalsIgnoreCase(accounttype)){
							accountDescription.add(accountlist.get(i).getAccountName());
						}
					 }
				 }
			}
		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
		} finally {
			controller =null;
		}		
	}
	
	
	public String redirect() {
		logger.info("[redirect()] --------------- Inside redirect() method() ------------------------");
		try {
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			remaining = "";
			flag = "none";
			validate = null;
			int i = 0;
			paymentType = null;
			accountType = null;
			accountdescriptionvalue = null;
			accountType1=new ArrayList<String>();
			setAccountDescription(null);
			logger.debug("[redirect()] date Size ------------------------>"+resu.get(i).getStartDate());
			AccountsDatabean accountsDatabean=new AccountsDatabean();
			accountsDatabean.setStatus("COA add");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.getAccountTypes(clientID,accountsDatabean);	
			setAccountType1(accountsDatabean.getAccountType());
			list = controller.paymentamountsale(purchaseOrder);
			salesorderdate = resu.get(i).getStartDate();
			salereferencenumber = resu.get(i).getI0022().getI0021()
					.getSalesOrderNumber();
			totalamount = "" + resu.get(i).getPayableAmount();
			paidamount = "" + resu.get(i).getPaidAmount();
			balanceamount = "" + resu.get(i).getBalanceAmount();
			if (balanceamount.equalsIgnoreCase("0")) {
				logger.info("[redirect()] --------------- Inside redirect() method() balanceamount if condition ------------------------");
				throw new DemoException("the payment is already finished");
			}
			if("Credit".equalsIgnoreCase(purchaseOrder.getPaymentMode())){
				logger.info("[redirect()] --------------- Inside redirect() method() Credit if condition ------------------------");
				payflag1="1";
				paidFlag="1";
				payingFlag="1";
				balanceFlag="1";
				if (!list.get(i).getPaidAmount().equals("0")) {
					logger.info("[redirect()] --------------- inside paid amount ------------------------");
					payflag = "1";
					payflag1 = "none";
					payflag2 = "none";
					payflag3 = "none";
					payflag4 = "none";
					acflag1="1";acflag="none";
					paymentType = list.get(0).getPaymentType();
					accountType = list.get(0).getAccountType();
					accountdescriptionvalue = list.get(0).getAccountDescription();
				} else if (list.get(i).getPaidAmount().equals("0")) {
					logger.info("[redirect()] --------------- no paid amount ------------------------");
					payflag1 = "1";
					payflag = "none";
					payflag2 = "1";
					payflag3 = "1";
					payflag4 = "1";
					acflag="1";acflag1="none";
				}
			}else if("Cash".equalsIgnoreCase(purchaseOrder.getPaymentMode())){
				payflag1="none";
				paidFlag="none";
				payingFlag="none";
				balanceFlag="none";
				payflag = "none";
				acflag="1";acflag1="none";
			}
			return "";
		} catch (DemoException e) {
			flag = "none";
			logger.error("Inside Exception"+e.getMessage());
			setValidate(e.getMessage());
			return "failure";
		}finally {
			controller =null;
		}
	}

	public String payNowAccount(){
		logger.info("[payNowAccount()] --------------- Inside payNowAccount() method() ------------------------");
		setValidate("");
		try{			
			if (accountType==null) {
					throw new DemoException("*Please Choose the Account Type");
				}else if (accountType!=null){
					if(accountdescriptionvalue==null){
						throw new DemoException("*Please choose the Account Description");	
					}
				}
			purchaseOrder.setAccounttype(accountType);
			purchaseOrder.setAccountdescription(accountdescriptionvalue);
			if("Cash".equalsIgnoreCase(purchaseOrder.getPaymentMode())){
				purchaseOrder.setPayableAmount(totalamount);
				purchaseOrder.setOrderNumber(salereferencenumber);
				logger.debug("[payNowAccount()] amount ------------------------>"+paidamount+"salereferencenumber ------------------------>"+salereferencenumber);
			}else if("Credit".equalsIgnoreCase(purchaseOrder.getPaymentMode())){
				if(StringUtils.isEmpty(remaining)){
					throw new DemoException("Please select the Amount Paying Now");
				}
				if(StringUtils.isEmpty(paymentType)){
					throw new DemoException("Please select the Payment Mode");
				}else if(!StringUtils.isEmpty(paymentType)){
					if("Card".equalsIgnoreCase(paymentType)){
						logger.debug("[payNowAccount()] paid amount ------------------------>"+paidamount);
						if(paidamount.equals("0")){
						if (StringUtils.isEmpty(bankname)) {
							throw new DemoException("*Enter Bank Name");
						} else if (StringUtils.isEmpty(cardno)) {
							throw new DemoException("*Enter Card Number");
						} else if (!cardno.matches("^\\d+(\\.\\d+)*$")) {
							throw new DemoException("Card Number should be in numbers");
						}
						}
					}else if("Cheque".equalsIgnoreCase(paymentType)){
						if(paidamount.equals("0")){
						if (StringUtils.isEmpty(bankname)) {
							throw new DemoException("*Enter Bank Name");
						} else if (StringUtils.isEmpty(chequeno)) {
							throw new DemoException("*Enter Cheque Number");
						} else if (!chequeno.matches("^\\d+(\\.\\d+)*$")) {
							throw new DemoException("Cheque Number should be in numbers");
						} else if (chequedate == null) {
							throw new DemoException("*Enter Cheque Date");
						}
						}
					}else if("Transfer".equalsIgnoreCase(paymentType)){
						if(paidamount.equals("0")){
						if (StringUtils.isEmpty(bankname)) {
							throw new DemoException("*Enter Bank Name");
						} else if (StringUtils.isEmpty(accno)) {
							throw new DemoException("*Enter Account Number");
						}else if (!accno.matches("^\\d+(\\.\\d+)*$")) {
							throw new DemoException("Account Number should be in numbers");
						}
						}
					}
					if (!paidamount.equals("0")) {
						accno = list.get(0).getAccountNo();
						cardno = list.get(0).getCardNo();
						chequedate = list.get(0).getChequeDate();
						chequeno = list.get(0).getChequeNo();
						bankname = list.get(0).getBankName();
					}
						purchaseOrder.setBankname(bankname);
						purchaseOrder.setCardno(cardno);
						purchaseOrder.setChequedate(chequedate);
						purchaseOrder.setChequeno(chequeno);
						purchaseOrder.setPaymentType(paymentType);
						purchaseOrder.setAccno(accno);
						purchaseOrder.setRemaining(paidamount);
						purchaseOrder.setPayableAmount(totalamount);
						purchaseOrder.setOrderNumber(salereferencenumber);
						
				}
			}
			purchaseOrder.setAccounttype(accountType);
			purchaseOrder.setAccountdescription(accountdescriptionvalue);
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String status=controller.payNowAccount(purchaseOrder);
			logger.debug("[payNowAccount()] status ------------------------>"+status);
			if("Success".equalsIgnoreCase(status)){
				RequestContext.getCurrentInstance().execute("PF('salespay2').show();");
			}
		}catch(DemoException e){
			logger.error("Inside Exception"+e.getMessage());
			setValidate(e.getMessage());
		}finally {
			controller =null;
		}
		return "";
	}
	public void paymentmodechange(ValueChangeEvent vv) {
		logger.info("[paymentmodechange()] --------------- Inside paymentmodechange() method() ------------------------");
		if (vv.getNewValue().equals("Card")) {
			logger.info("[paymentmodechange()]--------------- Inside paymentmodechange() method() if condition card ------------------------");
			bankname="";
			cardno=null;
		}
		else if (vv.getNewValue().equals("Cheque")) {
			logger.info("[paymentmodechange()]--------------- Inside paymentmodechange() method() else if condition cheque --------------");
			bankname="";
			chequedate=null;
			chequeno=null;
		}
		else if (vv.getNewValue().equals("Transfer")) {
			logger.info("[paymentmodechange()]--------------- Inside paymentmodechange() method() else if condition transfer --------------");
			bankname="";
			cardno=null;
		}
		
	}
	
	public void payingChange(ValueChangeEvent v){
		logger.info("[payingChange()]--------------- Inside payingChange() method() ------------------------");
		String value="";
		try{
			value=v.getNewValue().toString();
			if("25%".equalsIgnoreCase(value)){
				paidamount=String.valueOf(Integer.parseInt(balanceamount)/4);
			}else if("50%".equalsIgnoreCase(value)){
				paidamount=String.valueOf(Integer.parseInt(balanceamount)/2);
			}else if("75%".equalsIgnoreCase(value)){
				int amount1=Integer.parseInt(balanceamount)/4;
				int amount2=Integer.parseInt(balanceamount)/2;
				paidamount=String.valueOf(amount1+amount2);
			}else if("100%".equalsIgnoreCase(value)){
				paidamount=balanceamount;
			}
				
		}catch(Exception e){
			logger.error("Inside Exception"+e.getMessage());
		}
	}
	
	// prema begin 02/05/2016 dialog box creation for

	public void salespayment() {
		logger.info("[salespayment()] --------------- Inside salespayment() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);

		RequestContext.getCurrentInstance().openDialog("accountIn", options,
				null);
		direct();
	}

	// prema end 02/05/2016

	public void salespayclose() {
		RequestContext.getCurrentInstance().closeDialog("accountIn");
	}

	public void paymentpage() {
		logger.info("[paymentpage()] --------------- Inside paymentpage() method() ------------------------");
		if(balanceamount.equals("0")){
			RequestContext.getCurrentInstance().execute("PF('payDialog').show();");
		}else{
			Map<String, Object> options = new HashMap<String, Object>();
			options.put("modal", true);
			options.put("draggable", true);
			options.put("resizable", false);
			options.put("contentHeight", 350);
			options.put("contentWidth", 720);
			RequestContext.getCurrentInstance().openDialog("accountIn1", options,
					null);
			redirect();
		}
	}

	public void salespayclose1() {
		RequestContext.getCurrentInstance().closeDialog("accountIn1");
	}
}
