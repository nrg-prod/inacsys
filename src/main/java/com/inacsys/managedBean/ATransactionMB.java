package com.inacsys.managedBean;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.regex.REUtil;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.inacsys.controler.DemoController;
import com.inacsys.domain.ATransaction;
import com.inacsys.domain.AccountsDatabean;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.AccountType;
import com.inacsys.shared.ChartOfAccount;
import com.inacsys.shared.ExpenseTransaction;
import com.inacsys.shared.Transaction;
import com.inacsys.util.CommonValidate;
import com.inacsys.util.CurrencyConverter;

@ManagedBean(name = "aTransactionMB")
public class ATransactionMB {
	
	@ManagedProperty(value = "#{accountsMB}")
	AccountsMB accountsMB;
	
	ApplicationContext ctx = null;
	DemoController controller = null;
	private static Logger logger = Logger.getLogger(ATransactionMB.class);
	Date now = new Date();
	SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
	public Date date;
	public String date1;
	public String paymentMode;
	public String transactionType;
	public String ctransactionType;
	public String particular;
	public String amount;
	public String note;
	public String accountType;
	public String description;
	public String validate;
	public String cardno;
	public String bankname;
	public String chequeno;
	public String add;
	public Date chequedate;
	public String chequedate1;
	public String flag1 = "none";
	public String flag2 = "none";
	public String transactionNo;
	public Date fromdate;
	public Date todate;
	public List<Transaction> view = null;
	public List<ATransaction> viewlist = null;
	public String status;
	public String flag = "none";
	public String align1 = "none";
	public String align2 = "none";
	public String align3 = "none";
	public String align4 = "none";
	public Date paydate;
	public Date duedate;
	public String maddress;
	public String transamount;
	public String transStatus;
	private UploadedFile transFile;
	public String mode;
	ArrayList<ATransactionMB> tarns = new ArrayList<ATransactionMB>();
	ArrayList<ATransactionMB> filteredList;
	List<String> atypeList=new ArrayList<String>();
	private String paymentStatus;
	public List<String> codeList=null;
	
	/* Prema */
	ATransaction aTransaction=new ATransaction();
	

	public List<ATransaction> productdetails=null;
	public List<ATransaction> paymentdatatableList=null;
	public List<ATransaction> salesTransactiondetails=null;
	ArrayList<ATransaction> filterList1;
	public List<String> accounttypeList=null;
	public List<String> creditMemonoList=null;
	public List<String> locationList=Arrays.asList("Andaman and Nicobar Islands","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chandigarh","Chhattisgarh",
			"Dadra and Nagar Haveli","Daman and Diu","Delhi","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala",
			"Lakshadweep","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Pondicherry","Punjab","Rajasthan","Sikkim",
			"Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal","Outside India");
	private String paymentDataTableFlag="none";
	public List<ATransaction> expenseTransactionlist=null;


	public List<String> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<String> locationList) {
		this.locationList = locationList;
	}

	public AccountsMB getAccountsMB() {
		return accountsMB;
	}

	public void setAccountsMB(AccountsMB accountsMB) {
		this.accountsMB = accountsMB;
	}

	public List<ATransaction> getexpenseTransactionlist() {
		return expenseTransactionlist;
	}

	public void setexpenseTransactionlist(List<ATransaction> expenseTransactionlist) {
		this.expenseTransactionlist = expenseTransactionlist;
	}

	public ArrayList<ATransaction> getFilterList1() {
		return filterList1;
	}

	public void setFilterList1(ArrayList<ATransaction> filterList1) {
		this.filterList1 = filterList1;
	}

	public List<String> getCreditMemonoList() {
		return creditMemonoList;
	}

	public void setCreditMemonoList(List<String> creditMemonoList) {
		this.creditMemonoList = creditMemonoList;
	}

	public List<ATransaction> getPaymentdatatableList() {
		return paymentdatatableList;
	}

	public void setPaymentdatatableList(List<ATransaction> paymentdatatableList) {
		this.paymentdatatableList = paymentdatatableList;
	}

	public String getPaymentDataTableFlag() {
		return paymentDataTableFlag;
	}

	public void setPaymentDataTableFlag(String paymentDataTableFlag) {
		this.paymentDataTableFlag = paymentDataTableFlag;
	}

	public List<String> getAccounttypeList() {
		return accounttypeList;
	}

	public void setAccounttypeList(List<String> accounttypeList) {
		this.accounttypeList = accounttypeList;
	}

	public List<ATransaction> getSalesTransactiondetails() {
		return salesTransactiondetails;
	}

	public void setSalesTransactiondetails(
			List<ATransaction> salesTransactiondetails) {
		this.salesTransactiondetails = salesTransactiondetails;
	}

	public List<ATransaction> getProductdetails() {
		return productdetails;
	}

	public void setProductdetails(List<ATransaction> productdetails) {
		this.productdetails = productdetails;
	}

	public ATransaction getaTransaction() {
		return aTransaction;
	}

	public void setaTransaction(ATransaction aTransaction) {
		this.aTransaction = aTransaction;
	}
	
	public List<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCtransactionType() {
		return ctransactionType;
	}

	public void setCtransactionType(String ctransactionType) {
		this.ctransactionType = ctransactionType;
	}

	public UploadedFile getTransFile() {
		return transFile;
	}

	public void setTransFile(UploadedFile transFile) {
		this.transFile = transFile;
	}

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public String getMaddress() {
		return maddress;
	}

	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}

	public String getTransamount() {
		return transamount;
	}

	public void setTransamount(String transamount) {
		this.transamount = transamount;
	}

	public List<String> getAtypeList() {
		return atypeList;
	}

	public void setAtypeList(List<String> atypeList) {
		this.atypeList = atypeList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public ArrayList<ATransactionMB> getFilteredList() {
		return filteredList;
	}

	public void setFilteredList(ArrayList<ATransactionMB> filteredList) {
		this.filteredList = filteredList;
	}

	public ArrayList<ATransactionMB> getTarns() {
		return tarns;
	}

	public void setTarns(ArrayList<ATransactionMB> tarns) {
		this.tarns = tarns;
	}

	public String getAlign4() {
		return align4;
	}

	public void setAlign4(String align4) {
		this.align4 = align4;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public String flagpop = "none";

	public String getFlagpop() {
		return flagpop;
	}

	public void setFlagpop(String flagpop) {
		this.flagpop = flagpop;
	}

	public String getAlign3() {
		return align3;
	}

	public void setAlign3(String align3) {
		this.align3 = align3;
	}

	public String getAlign1() {
		return align1;
	}

	public void setAlign1(String align1) {
		this.align1 = align1;
	}

	public String getAlign2() {
		return align2;
	}

	public void setAlign2(String align2) {
		this.align2 = align2;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ATransaction> getViewlist() {
		return viewlist;
	}

	public void setViewlist(List<ATransaction> viewlist) {
		this.viewlist = viewlist;
	}

	public List<Transaction> getView() {
		return view;
	}

	public void setView(List<Transaction> view) {
		this.view = view;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
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

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getParticular() {
		return particular;
	}

	public void setParticular(String particular) {
		this.particular = particular;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getChequedate1() {
		return chequedate1;
	}

	public void setChequedate1(String chequedate1) {
		this.chequedate1 = chequedate1;
	}

	public String transactionView() {
		logger.info("[transactionView()] --------------- Inside transactionView() method() ------------------------");
		validate = "";
		setValidate("");
		flag = "none";
		fromdate = null;
		todate = null;
		flagpop = "none";
		return "";

	}

	public String cancel() {
		logger.info("[cancel()] --------------- Inside cancel() method() ------------------------");
		flag = "none";
		flagpop = "none";
		return "transactionHome";
	}

	public String accountstransactionpage() {
		logger.info("--------------------------------------- Inside accountstransactionpage() method ----------------------------------");
		search();
		return "accountstransactionform";
	}
	
	BigDecimal amt1 = BigDecimal.valueOf(0);

	public BigDecimal getAmt1() {
		return amt1;
	}

	public void setAmt1(BigDecimal amt1) {
		this.amt1 = amt1;
	}

	public Date transdate;

	public Date getTransdate() {
		return transdate;
	}

	public void setTransdate(Date transdate) {
		this.transdate = transdate;
	}
	
	public String serialno;
	

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	
	public String search() {
		logger.info("[search()] --------------- Inside search() method() ------------------------");
		tarns.clear();
		flag = "none";
		flagpop = "none";
		ApplicationContext ctx = null;
		DemoController controller = null;
		ATransaction search = null;
		try {
			setValidate("");
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			search = new ATransaction();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setView(search.getView());
			controller.transactionView(search);
			if (search.view.size() > 0) {
				flag = "1";
				flagpop = "none";
				setView(search.getView());
				for (int i = 0; i < view.size(); i++) {
					ATransactionMB trans = new ATransactionMB();
					trans.setSerialno(String.valueOf(i+1));  
					trans.setTransactionNo(view.get(i).getTransactionNo());
					trans.setTransdate(view.get(i).getPayDate());
					trans.setPaymentMode(view.get(i).getPaymentMode());
					trans.setTransactionType(view.get(i).getTransactionType());
					trans.setNote(view.get(i).getParticulars());
					trans.setStatus(view.get(i).getStatus());
					trans.setAmt1(new BigDecimal(view.get(i).getCurrency()));
					trans.setMode(view.get(i).getTransactionStatus());					
					if(view.get(i).getTransactionType().equals("Invoice")||view.get(i).getTransactionType().equals("Payment")||
							view.get(i).getTransactionType().equals("Sales Receipt")||view.get(i).getTransactionType().equals("Time Activity")||
							view.get(i).getTransactionType().equals("Delayed Charge")||view.get(i).getTransactionType().equals("Bill")||
							view.get(i).getTransactionType().equals("Cheque")||view.get(i).getTransactionType().equals("Vendor Credit")){
						trans.setPaymentStatus(view.get(i).getPaymentStatus());
					}
					trans.setBaseCurrency(baseCurrency);
					tarns.add(trans);
				}
				logger.debug("[search()] --------------- Inside if values----->amount,particular---------->"+view.get(0).getAmount()+"-->"+view.get(0).getParticulars()+"-->"+view.get(0).getTransactionType()+"-->"+view.get(0).getTransactionNo());
			} else {
				setView(search.getView());
				flagpop = "1";
				flag = "none";
				logger.debug("[search()] --------------- Inside if values----->amount,particular---------->"+view.get(0).getAmount()+"-->"+view.get(0).getParticulars()+"-->"+view.get(0).getTransactionType()+"-->"+view.get(0).getTransactionNo());
			}
		} catch (DemoException ie) {
			flag = "none";
			setValidate(ie.getMessage());
			logger.error("Inside Exception", ie);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}
	private boolean validate(boolean flag) {
		logger.info("[search()] --------------- Inside validate() method() ------------------------");
		boolean valid = true;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		if (date==null) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("tran_date").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Choose the Date"));
			}
			valid = false;
		}
		if(paymentMode.equalsIgnoreCase("select")){
			if(flag){
				fieldName = CommonValidate.findComponentInRoot("trans_Mode").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the Payment Mode"));
				}
				valid = false;
		}else if(!paymentMode.equalsIgnoreCase("select")){
			if(paymentMode.equalsIgnoreCase("Card")){
				logger.debug("[search()] --------------- Inside validate() method() select card------------------------");
				if(StringUtils.isBlank(bankname)){
					if(flag){
						fieldName = CommonValidate.findComponentInRoot("tran_cardB").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter the Bank Name"));
						}
						valid = false;
				}else if(!StringUtils.isBlank(bankname)){
					if(!CommonValidate.validateName(bankname)){
					if(flag){
						fieldName = CommonValidate.findComponentInRoot("tran_cardB").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter valid Bank Name"));
						}
						valid = false;
					}
				}
				if(StringUtils.isBlank(cardno)){
					if(flag){
						fieldName = CommonValidate.findComponentInRoot("trans_cardN").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter the Card Number"));
						}
						valid = false;
				}else if(!StringUtils.isBlank(cardno)){
					if(!CommonValidate.validateNumber(cardno)){
					if(flag){
						fieldName = CommonValidate.findComponentInRoot("trans_cardN").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter valid Card Number"));
						}
						valid = false;
				}
				}
			}else if(paymentMode.equalsIgnoreCase("Cheque")){
				logger.info("[search()] --------------- Inside validate() method() select cheque------------------------");
				if(StringUtils.isBlank(bankname)){
					if(flag){
						fieldName = CommonValidate.findComponentInRoot("tran_ChequeB").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter the Bank Name"));
						}
						valid = false;
				}else if(!StringUtils.isBlank(bankname)){
					if(!CommonValidate.validateName(bankname)){

					if(flag){
						fieldName = CommonValidate.findComponentInRoot("tran_ChequeB").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter valid Bank Name"));
						}
						valid = false;
				}
				}
				if(StringUtils.isBlank(chequeno)){
					if(flag){
						fieldName = CommonValidate.findComponentInRoot("tran_ChequeN").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter the Cheque Number"));
						}
						valid = false;
				}else if(!StringUtils.isBlank(chequeno)){
					if(!CommonValidate.validateNumber(chequeno)){
						if(flag){
							fieldName = CommonValidate.findComponentInRoot("tran_ChequeN").getClientId(fc);
							fc.addMessage(fieldName, new FacesMessage("Please Enter valid Cheque Number"));
							}
							valid = false;
					}
				}
				if(chequedate==null){
					if(flag){
						fieldName = CommonValidate.findComponentInRoot("tran_ChequeDate").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Choose the Cheque Date"));
						}
						valid = false;
				}
			}else if(paymentMode.equalsIgnoreCase("Transfer")){
				logger.info("[search()] --------------- Inside validate() method() select transfer------------------------");
				if(StringUtils.isBlank(bankname)){
					if(flag){
						fieldName = CommonValidate.findComponentInRoot("tran_TransB").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter the Bank Name"));
						}
						valid = false;
				}else if(!StringUtils.isBlank(bankname)){
					if(!CommonValidate.validateName(bankname)){
					if(flag){
						fieldName = CommonValidate.findComponentInRoot("tran_TransB").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter valid Bank Name"));
						}
						valid = false;
					}
				}
				if(StringUtils.isBlank(cardno)){
					if(flag){
						fieldName = CommonValidate.findComponentInRoot("trans_TransN").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter the Account Number"));
						}
						valid = false;
				}else if(!StringUtils.isBlank(cardno)){
					if(!CommonValidate.validateNumber(cardno)){
					if(flag){
						fieldName = CommonValidate.findComponentInRoot("trans_TransN").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter valid Account Number"));
						}
						valid = false;
					}
				}
			}
		}
		if(transactionType.equalsIgnoreCase("select")){
			if(flag){
				fieldName = CommonValidate.findComponentInRoot("tran_Type").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the Transaction Type"));
				}
				valid = false;
		}
		if(StringUtils.isBlank(particular)){
			logger.info("[search()] --------------- Inside validate() method() select particular------------------------");
			if(flag){
				fieldName = CommonValidate.findComponentInRoot("tran_Name").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter the Name"));
				}
				valid = false;
		}else if(!StringUtils.isBlank(particular)){
			if(!CommonValidate.validateName(particular)){
			if(flag){
				fieldName = CommonValidate.findComponentInRoot("tran_Name").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter valid Name"));
				}
				valid = false;
		}
		}
		if(StringUtils.isBlank(amount)){
			if(flag){
				fieldName = CommonValidate.findComponentInRoot("trans_Amnt").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter the Amount"));
				}
				valid = false;
		}else if(!StringUtils.isBlank(amount)){
			if(!CommonValidate.validateNumber(amount)){
			if(flag){
				fieldName = CommonValidate.findComponentInRoot("trans_Amnt").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter valid Amount"));
				}
				valid = false;
		}
		}
		return valid;
	}
	public String submit() {
		logger.info("[submit()] --------------- Inside validate() submit() ------------------------");
		try {
			setValidate("");
			if(validate(true)){
				return "submit";
			}
			
		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
			setValidate(e.getMessage());
			return "failure";
		} finally {
			controller =null;
		}
		return "";
	}

	public String confirm() {
		logger.info("[submit()] --------------- Inside confirm() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		ATransaction save = new ATransaction();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			save.setAmount(amount);
			save.setDate(date);
			save.setNote(note);
			save.setParticular(particular);
			save.setPaymentMode(paymentMode);
			save.setTransactionType(transactionType);
			save.setBankname(bankname);
			save.setCardno(cardno);
			save.setChequedate(chequedate);
			save.setChequeno(chequeno);
			save.setTransactionNo(transactionNo);
			controller.saveconfirm(save);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			amount = "";
			date = null;
			paymentMode = null;
			bankname = null;
			cardno = null;
			chequedate = null;
			chequeno = null;
			particular = null;
			note = null;
			transactionType = null;
		}
		return "save";
	}

	public void paymentmodechange(ValueChangeEvent vv) {
		logger.info("[paymentmodechange()] --------------- Inside paymentmodechange() method() ------------------------");
		if (vv.getNewValue().equals("Card")) {
			logger.info("[paymentmodechange()] --------------- Inside paymentmodechange() method() if condition card ------------------------");
			bankname="";
			cardno=null;
		}
		else if (vv.getNewValue().equals("Cheque")) {
			logger.info("[paymentmodechange()] --------------- Inside paymentmodechange() method() if condition cheque ------------------------");
			bankname="";
			chequedate=null;
			chequeno=null;
		}
		else if (vv.getNewValue().equals("Transfer")) {
			logger.info("[paymentmodechange()] --------------- Inside paymentmodechange() method() if condition transfer ------------------------");
			bankname="";
			cardno=null;
		}
		
	}
	
	public String baseCurrency;

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String viewForm() {
		logger.info("[viewForm()] --------------- Inside viewForm() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		ATransaction view = new ATransaction();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			view.setTransactionNo(transactionNo);
			setViewlist(controller.getviewForm(view));
			setAmount(viewlist.get(0).getAmount());
			setMaddress(viewlist.get(0).getMaddress());
			setDuedate(viewlist.get(0).getDuedate());
			setPaydate(viewlist.get(0).getPaydate());
			setBankname(viewlist.get(0).getBankname());
			setCardno(viewlist.get(0).getCardno());
			setChequedate(viewlist.get(0).getChequedate());
			setChequeno(viewlist.get(0).getChequeno());
			setNote(viewlist.get(0).getNote());
			setParticular(viewlist.get(0).getParticular());
			setPaymentMode(viewlist.get(0).getPaymentMode());
			setTransactionType(viewlist.get(0).getTransactionType());
			setAccounts(viewlist.get(0).getParticular());
			setCurrencyType(viewlist.get(0).getCurrencyType());
			setCurrency(viewlist.get(0).getCurrency());
			setBaseCurrency(viewlist.get(0).getBaseCurrency());
			logger.debug("[viewForm()] --------------- pay mode ------------------------>"+viewlist.get(0).getDuedate());
			if (paymentMode.equals("Card")) {
				logger.info("[viewForm()] --------------- Inside viewForm() method() if condition card ------------------------");
				align1 = "1";
				align2 = "none";
				align3 = "1";
				align4 = "none";
			} else if (paymentMode.equals("Cash")) {
				logger.info("[viewForm()] --------------- Inside viewForm() method() if condition cash ------------------------");
				align1 = "1";
				align2 = "none";
				align3 = "none";
				align4 = "none";
			} else if (paymentMode.equals("Cheque")) {
				logger.info("[viewForm()] --------------- Inside viewForm() method() if condition cheque ------------------------");
				align1 = "none";
				align2 = "1";
				align3 = "none";
				align4 = "none";
			} else if (paymentMode.equals("Transfer")) {
				logger.info("[viewForm()] --------------- Inside viewForm() method() if condition transfer ------------------------");
				align1 = "1";
				align2 = "none";
				align3 = "none";
				align4 = "1";
			}
			setTransactionNo(viewlist.get(0).getTransactionNo());
			setTransactionType(viewlist.get(0).getTransactionType());
			setTransStatus(viewlist.get(0).getTransStatus());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			controller =null;
		}
		return "";
	}

	public String returnback() {
		logger.info("[returnback()] --------------- Inside returnback() method() ------------------------");
		try {
			search();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			controller =null;
		}
		return "aTransactionView";
	}

	public String editForm() {
		logger.info("[editForm()] --------------- Inside editForm() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		ATransaction edit = new ATransaction();
		try {
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			edit.setTransactionNo(transactionNo);
			edit.setStatus("register");
			setViewlist(controller.getEditForm(edit));
			atypeList=controller.getAccountType(clientID);
			setAmount(viewlist.get(0).getAmount());
			setBankname(viewlist.get(0).getBankname());
			setCardno(viewlist.get(0).getCardno());
			setChequedate(viewlist.get(0).getChequedate());
			setChequeno(viewlist.get(0).getChequeno());
			setNote(viewlist.get(0).getNote());
			setPaydate(viewlist.get(0).getPaydate());
			setTransStatus(viewlist.get(0).getTransStatus());
			setDuedate(viewlist.get(0).getDuedate());
			setMaddress(viewlist.get(0).getMaddress());
			setTransactionType(viewlist.get(0).getTransactionType());
			setParticular(viewlist.get(0).getParticular());
			setPaymentMode(viewlist.get(0).getPaymentMode());
			setTransactionNo(viewlist.get(0).getTransactionNo());
			setTransactionType(viewlist.get(0).getTransactionType());
			setCurrencyType(viewlist.get(0).getCurrencyType());
			setCurrency(viewlist.get(0).getCurrency());
			setBaseCurrency(viewlist.get(0).getBaseCurrency());
			setTransactionType(viewlist.get(0).getTransactionType());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			controller =null;
		}
		return "aTransactionEditForm";
	}

	public String confirmedit() {
		logger.info("[confirmedit()] --------------- Inside confirmedit() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		ATransaction edit = new ATransaction();
		try {
			setValidate("");
			if (paydate == null) {
				throw new DemoException("Please Choose the Payment Date");
			}
			if (duedate == null) {
				throw new DemoException("Please Choose the Due Date");
			}
			if (paymentMode.equals("Cash")) {
				logger.info("[confirmedit()] --------------- Inside confirmedit() method() if condition cash ------------------------");
				align1 = "1";
				align2 = "none";
				align3 = "none";
				align4 = "none";
			}
			if (paymentMode.equals("Card")) {
				logger.info("[confirmedit()] --------------- Inside confirmedit() method() if condition card ------------------------");
				if (bankname.equals("")) {
					throw new DemoException("Please Enter the Bank Name");
				} else if (cardno.equals("")) {
					throw new DemoException("Please Enter the Card Number");
				}
				align1 = "1";
				align2 = "none";
				align3 = "1";
				align4 = "none";
			}
			if (paymentMode.equals("Cheque")) {
				logger.info("[confirmedit()] --------------- Inside confirmedit() method() if condition cheque ------------------------");
				align2 = "1";
				align1 = "none";
				align3 = "none";
				align4 = "none";
				if (bankname.equals("")) {
					throw new DemoException("Please Enter the Bank Name");
				} else if (chequeno.equals("")) {
					throw new DemoException("Please Enter the Cheque Number");
				}

				if (chequedate == null) {
					throw new DemoException("Please Choose the Cheque Date");
				}
			}
			if (paymentMode.equals("Transfer")) {
				logger.info("[confirmedit()] --------------- Inside confirmedit() method() if condition transfer ------------------------");
				align1 = "1";
				align2 = "none";
				align3 = "none";
				align4 = "1";
				if (bankname.equals("")) {
					throw new DemoException("Please Enter the Bank Name");
				} else if (cardno.equals("")) {
					throw new DemoException("Please Enter the Account Number");
				}
			}
			if (maddress.equals("")) {
				throw new DemoException("Please Enter the Mailing Address");
			}
			if (transactionType.equals("")) {
				throw new DemoException("Please Choose the Transaction Type");
			}
			if (particular.equals("")) {
				throw new DemoException("Please Choose the Account");
			}
			if (amount.equalsIgnoreCase("")) {
				throw new DemoException("Please Enter the Amount");
			} else if (!amount.matches("^\\d+(\\.\\d+)*$")) {
				throw new Exception("Amount Should be Numeric");
			}if(currencyType.equals("")){
				throw new DemoException("Please Select Currency Type");
			}
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			edit.setTransactionNo(transactionNo);
			edit.setStatus("update");
			edit.setAmount(amount);
			edit.setDate(date);
			edit.setNote(note);
			edit.setParticular(particular);
			edit.setPaymentMode(paymentMode);
			edit.setTransactionType(transactionType);
			edit.setPaydate(paydate);
			edit.setDuedate(duedate);
			edit.setMaddress(maddress);
			edit.setBankname(bankname);
			edit.setCardno(cardno);
			edit.setChequedate(chequedate);
			edit.setChequeno(chequeno);
			edit.setTransStatus(transStatus);
			edit.setTransactionNo(transactionNo);
			edit.setTransFile(transFile);
			edit.setCurrencyType(currencyType);
			setViewlist(controller.getEditForm(edit));

			return "edit";
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
			return "";
		} finally {
			controller =null;
		}

	}

	public String delete() {
		logger.info("[delete()] --------------- Inside delete() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		ATransaction edit = new ATransaction();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			edit.setTransactionNo(transactionNo);
			edit.setStatus("delete");
			setViewlist(controller.getEditForm(edit));
			RequestContext.getCurrentInstance().execute("PF('deleteconf').show();");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			view = null;
		}
		return "delete";
	}

	public String chequeCredit() {
		logger.info("[delete()] --------------- Inside chequeCredit() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		ATransaction change = new ATransaction();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			change.setTransactionNo(transactionNo);
			logger.info("transaction no-------->>>" + transactionNo);
			change.setStatus("credited");
			setViewlist(controller.getStatusChange(change));
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			view = null;
		}
		return "";
	}

	public String ok() {
		logger.info("[ok()] --------------- Inside ok() method() ------------------------");
		setAmount("");
		setDate(null);
		setPaymentMode(null);
		setBankname(null);
		setCardno(null);
		setChequedate(null);
		setChequeno(null);
		setParticular(null);
		setNote(null);
		setTransactionType(null);
		return "home";
	}

	public String deleteback() { 
		logger.info("[deleteback()] --------------- Inside deleteback() method() ------------------------");
		flag = "none";
		setFromdate(null);
		setTodate(null);
		return "deleteback";
	}

	public String transationClear() {
		logger.info("[transationClear()] --------------- Inside transationClear() method() ------------------------");
		  validate = "";
		  ctx = FacesContextUtils.getWebApplicationContext(FacesContext
		    .getCurrentInstance());
		  controller = (DemoController) ctx.getBean("controller");
		  String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
		  atypeList=controller.getAccountType(clientID);
		  Collections.sort(atypeList);
		  setPayStatus("transReg");
		  transactionForm();
		  return "";
		 }

	/* jency */
	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public ATransactionMB() {
		sortsOrders = new HashMap<String, SortOrder>();
		sortPriorities = new ArrayList<String>();
	}

	public void sort() {
		logger.info("[sort()] --------------- Inside sort() method() ------------------------");
		String property = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get(SORT_PROPERTY_PARAMETER);
		if (property != null) {
			SortOrder currentPropertySortOrder = sortsOrders.get(property);
			if (multipleSorting) {
				if (!sortPriorities.contains(property)) {
					sortPriorities.add(property);
				}
			} else {
				sortsOrders.clear();
			}
			if (currentPropertySortOrder == null
					|| currentPropertySortOrder.equals(SortOrder.DESCENDING)) {
				sortsOrders.put(property, SortOrder.ASCENDING);
			} else {
				sortsOrders.put(property, SortOrder.DESCENDING);
			}

		}
	}

	public Map<String, SortOrder> getSortsOrders() {
		return sortsOrders;
	}

	public void setSortsOrders(Map<String, SortOrder> sortsOrders) {
		this.sortsOrders = sortsOrders;
	}

	public List<String> getSortPriorities() {
		return sortPriorities;
	}

	public void setSortPriorities(List<String> sortPriorities) {
		this.sortPriorities = sortPriorities;
	}

	public boolean isMultipleSorting() {
		return multipleSorting;
	}

	public void setMultipleSorting(boolean multipleSorting) {
		this.multipleSorting = multipleSorting;
	}

	// prema begin 02/05/2016 dialog box creation for Transaction form

	public void transactionform() {
		logger.info("[transactionform()] --------------- Inside transactionform() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("scrollable", true);
		options.put("contentHeight", 400);
		options.put("contentWidth", 1200);
		logger.info("============");
		ctx = FacesContextUtils.getWebApplicationContext(FacesContext
				.getCurrentInstance());
		controller = (DemoController) ctx.getBean("controller");
		String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
		atypeList=controller.getAccountType(clientID);
		Collections.sort(atypeList);
		RequestContext.getCurrentInstance().openDialog("aTransactionForm",
				options, null);
		setPayStatus("transReg");
		transactionForm();
	}

	// prema end 02/05/2016
	public void transactionformclose() {
		RequestContext.getCurrentInstance().closeDialog("aTransactionForm");
	}

	// prema begin 02/05/2016 dialog box creation for Transaction View

	public void transactionviews() {
		logger.info("[transactionviews()] --------------- Inside transactionviews() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("aTransactionView",
				options, null);
		search();
	}

	// prema end 02/05/2016
	public void transactionviewsclose() {
		RequestContext.getCurrentInstance().closeDialog("aTransactionView");
	}

	public void transactionviewform1() {
		logger.info("[transactionviewform1()] --------------- Inside transactionviewform1() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 450);
		options.put("contentWidth", 600);
		RequestContext.getCurrentInstance().openDialog("aTransactionViewForm",
				options, null);
		viewForm();
	}
	
	private List<ATransaction> debitlist=null;
	private List<ATransaction> creditlist=null;	
	private String serialNo;
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public List<ATransaction> getDebitlist() {
		return debitlist;
	}

	public void setDebitlist(List<ATransaction> debitlist) {
		this.debitlist = debitlist;
	}

	public List<ATransaction> getCreditlist() {
		return creditlist;
	}

	public void setCreditlist(List<ATransaction> creditlist) {
		this.creditlist = creditlist;
	}

	public String transactionForm(){
		logger.info("[transactionForm()] --------------- Inside transactionForm() method() ------------------------");
		date=null;
		setValidate("");
		debitlist=new ArrayList<ATransaction>();
		creditlist=new ArrayList<ATransaction>();
		try{
			for (int i = 1; i <= 5; i++) {
				ATransaction transact=new ATransaction();
				transact.setNote("");
				transact.setParticular("");
				transact.setAmount("");
				transact.setPaymentMode("");
				transact.setAccountType("");
				transact.setStatus("Debit");
				transact.setSerialNo(""+i);
				debitlist.add(transact);
				ATransaction transacts=new ATransaction();
				transacts.setCnote("");
				transacts.setCparticular("");
				transacts.setCamount("");
				transacts.setCpaymentMode("");
				transacts.setStatus("Credit");
				transacts.setSerialNo(""+i);
				creditlist.add(transacts);				
			}
		}catch(Exception e){
			logger.error("inside exception ",e);
		}finally {
			controller =null;
		}
		return "";
	}
	
	public String saveTransaction(){
		logger.info("[transactionForm()] --------------- Inside saveTransaction() method() ------------------------");
		DemoController controller = null;viewlist=new ArrayList<ATransaction>();
		ApplicationContext ctx = null;
		ATransaction transaction=new ATransaction();
		String fieldName;	int count=0,count1=0,errcount=0;
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			for (int i = 0; i < debitlist.size(); i++) {
				logger.info("[transactionForm()] --------------- Inside saveTransaction() method() for loop debitlist ------------------------");
				if(debitlist.get(i).getTransactionType()==null){
					count++;
				}else{
					if(debitlist.get(i).getParticular().equals("")){errcount++;
						fieldName = CommonValidate.findComponentInRoot("errmsg").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Select Account type in Debit row "+(i+1)));
					}else if(debitlist.get(i).getPaymentMode().equals("")){errcount++;
						fieldName = CommonValidate.findComponentInRoot("errmsg").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Select Payment Mode in Debit row "+(i+1)));
					}
				}
			}
			for (int i = 0; i < creditlist.size(); i++) {
				logger.info("[transactionForm()] --------------- Inside saveTransaction() method() for loop creditlist ------------------------");
				if(creditlist.get(i).getCtransactionType()==null){
					count1++;
				}else{
					if(creditlist.get(i).getCparticular().equals("")){errcount++;
						fieldName = CommonValidate.findComponentInRoot("errmsg").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Select Account type in Credit row "+(i+1)));
					}else if(creditlist.get(i).getCpaymentMode().equals("")){errcount++;
						fieldName = CommonValidate.findComponentInRoot("errmsg").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Select Payment Mode in Credit row "+(i+1)));
					}	
				}
			}
			if(count==debitlist.size() && count1==creditlist.size()){	
				errcount++;
				fieldName = CommonValidate.findComponentInRoot("errmsg").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please fill atleast one Transaction"));
			}
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			logger.info("errcount -- "+errcount);
			if(errcount==0){
				for (int i = 0; i < creditlist.size(); i++) {
					if(creditlist.get(i).getCtransactionType()!=null){
						ATransaction atrans=new ATransaction();
						atrans.setMaddress(creditlist.get(i).getMaddress());
						atrans.setTransactionType(creditlist.get(i).getCtransactionType());
						atrans.setPaydate(creditlist.get(i).getPaydate());
						atrans.setDuedate(creditlist.get(i).getDuedate());
						logger.info("paydate------"+creditlist.get(i).getPaydate());
						logger.info("duedate------"+creditlist.get(i).getDuedate());
						atrans.setTransFile(creditlist.get(i).getTransFile());
						atrans.setAmount(creditlist.get(i).getAmount());
						atrans.setNote(creditlist.get(i).getCnote());
						atrans.setParticular(creditlist.get(i).getCparticular());
						atrans.setPaymentMode(creditlist.get(i).getCpaymentMode());
						atrans.setBankname(creditlist.get(i).getBankname());
						atrans.setCardno(creditlist.get(i).getCardno());
						atrans.setChequeno(creditlist.get(i).getChequeno());
						atrans.setStatus(creditlist.get(i).getStatus());
						atrans.setChequedate(creditlist.get(i).getChequedate());
						atrans.setCurrencyType(creditlist.get(i).getCurrencyType());
						atrans.setTransStatus("credited");
						viewlist.add(atrans);
					}				
				}
				for (int i = 0; i < debitlist.size(); i++) {
					if(debitlist.get(i).getTransactionType()!=null){
						ATransaction atrans=new ATransaction();
						atrans.setTransactionType(debitlist.get(i).getTransactionType());
						atrans.setMaddress(debitlist.get(i).getMaddress());
						atrans.setPaydate(debitlist.get(i).getPaydate());
						atrans.setDuedate(debitlist.get(i).getDuedate());
						atrans.setTransFile(debitlist.get(i).getTransFile());
						atrans.setAmount(debitlist.get(i).getAmount());
						atrans.setNote(debitlist.get(i).getNote());
						atrans.setParticular(debitlist.get(i).getParticular());
						atrans.setPaymentMode(debitlist.get(i).getPaymentMode());
						atrans.setBankname(debitlist.get(i).getBankname());
						atrans.setCardno(debitlist.get(i).getCardno());
						atrans.setStatus(debitlist.get(i).getStatus());
						atrans.setChequeno(debitlist.get(i).getChequeno());
						atrans.setChequedate(debitlist.get(i).getChequedate());
						atrans.setCurrencyType(debitlist.get(i).getCurrencyType());
						atrans.setTransStatus("debited");
						viewlist.add(atrans);
					}				
				}
				transaction.setDebitlist(viewlist);
				transaction.setDate(date);
				logger.info("transaction size "+viewlist.size());
				controller.saveconfirm(transaction);	
				RequestContext.getCurrentInstance().execute("PF('confirmDialog').show();");
			}			
		}catch(Exception e){
			logger.error("inside exception ",e);
		}finally {
			controller =null;
		}
		return "";
	}
	
	public void paymentDetails(ValueChangeEvent v){
		logger.info("[paymentDetails()] --------------- Inside paymentDetails() method() ------------------------");
		bankname=null;cardno=null;chequedate=null;chequeno=null;
		String type=v.getNewValue().toString();
		serialNo=v.getComponent().getAttributes().get("serialNo").toString();
		status=v.getComponent().getAttributes().get("status").toString();
		try{
			setPaymentMode(type);
			if(type.equals("Card")){
				RequestContext.getCurrentInstance().execute("PF('paymentDetails').show();");
			}else if(paymentMode.equals("Transfer")){
				RequestContext.getCurrentInstance().execute("PF('tpaymentDetails').show();");
			}else if(type.equals("Cheque")){
				RequestContext.getCurrentInstance().execute("PF('paymentDetails1').show();");
			}
		}catch(Exception e){
			logger.error("inside exception ",e);
		}finally {
			controller =null;
		}
	}
	
	public String savepaymentDetails(){
		logger.info("[savepaymentDetails()] --------------- Inside savepaymentDetails() method() ------------------------");
		try{
			if(paymentMode.equals("Card") || paymentMode.equals("Transfer")){
				if(paymentMode.equals("Card")) RequestContext.getCurrentInstance().execute("PF('paymentDetails').show();");
				else if(paymentMode.equals("Transfer")) RequestContext.getCurrentInstance().execute("PF('tpaymentDetails').show();");
				if(validates(true)){
					ATransaction payment=new ATransaction();
					payment.setBankname(bankname);
					payment.setCardno(cardno);
					payment.setSerialNo(serialNo);
					if(status.equals("Debit")){
						logger.info("[savepaymentDetails()] --------------- Inside savepaymentDetails() method() if debit------------------------");
						payment.setTransactionType(debitlist.get(Integer.parseInt(serialNo)-1).getTransactionType());
						payment.setMaddress(debitlist.get(Integer.parseInt(serialNo)-1).getMaddress());
						payment.setPaydate(debitlist.get(Integer.parseInt(serialNo)-1).getPaydate());
						payment.setDuedate(debitlist.get(Integer.parseInt(serialNo)-1).getDuedate());
						payment.setTransFile(debitlist.get(Integer.parseInt(serialNo)-1).getTransFile());
						payment.setNote(debitlist.get(Integer.parseInt(serialNo)-1).getNote());
						payment.setParticular(debitlist.get(Integer.parseInt(serialNo)-1).getParticular());
						payment.setAmount(debitlist.get(Integer.parseInt(serialNo)-1).getAmount());
						payment.setPaymentMode(debitlist.get(Integer.parseInt(serialNo)-1).getPaymentMode());
						payment.setCurrencyType(debitlist.get(Integer.parseInt(serialNo)-1).getCurrencyType());
						payment.setStatus("Debit");
						debitlist.set(Integer.parseInt(serialNo)-1, payment);						
					}else if(status.equals("Credit")){
						logger.info("[savepaymentDetails()] --------------- Inside savepaymentDetails() method() if credit------------------------");
						payment.setCtransactionType(creditlist.get(Integer.parseInt(serialNo)-1).getCtransactionType());
						payment.setMaddress(creditlist.get(Integer.parseInt(serialNo)-1).getMaddress());
						payment.setPaydate(creditlist.get(Integer.parseInt(serialNo)-1).getPaydate());
						payment.setDuedate(creditlist.get(Integer.parseInt(serialNo)-1).getDuedate());
						payment.setTransFile(creditlist.get(Integer.parseInt(serialNo)-1).getTransFile());
						payment.setCnote(creditlist.get(Integer.parseInt(serialNo)-1).getCnote());
						payment.setCparticular(creditlist.get(Integer.parseInt(serialNo)-1).getCparticular());
						payment.setAmount(creditlist.get(Integer.parseInt(serialNo)-1).getAmount());
						payment.setCpaymentMode(creditlist.get(Integer.parseInt(serialNo)-1).getCpaymentMode());
						payment.setCurrencyType(creditlist.get(Integer.parseInt(serialNo)-1).getCurrencyType());
						payment.setStatus("Credit");
						creditlist.set(Integer.parseInt(serialNo)-1, payment);						
					}					
					if(paymentMode.equals("Card")) RequestContext.getCurrentInstance().execute("PF('paymentDetails').hide();");
					else if(paymentMode.equals("Transfer")) RequestContext.getCurrentInstance().execute("PF('tpaymentDetails').hide();");
					setBankname("");setCardno("");
				}
			}else if(paymentMode.equals("Cheque")){
				logger.info("[savepaymentDetails()] --------------- Inside savepaymentDetails() method() if cheque------------------------");
				RequestContext.getCurrentInstance().execute("PF('paymentDetails1').show();");
				if(validatech(true)){
					ATransaction payment=new ATransaction();
					payment.setBankname(bankname);
					payment.setChequeno(chequeno);
					payment.setChequedate(chequedate);
					payment.setSerialNo(serialNo);
					if(status.equals("Debit")){
						payment.setTransactionType(debitlist.get(Integer.parseInt(serialNo)-1).getTransactionType());
						payment.setMaddress(debitlist.get(Integer.parseInt(serialNo)-1).getMaddress());
						payment.setPaydate(debitlist.get(Integer.parseInt(serialNo)-1).getPaydate());
						payment.setDuedate(debitlist.get(Integer.parseInt(serialNo)-1).getDuedate());
						payment.setTransFile(debitlist.get(Integer.parseInt(serialNo)-1).getTransFile());
						payment.setNote(debitlist.get(Integer.parseInt(serialNo)-1).getNote());
						payment.setParticular(debitlist.get(Integer.parseInt(serialNo)-1).getParticular());
						payment.setAmount(debitlist.get(Integer.parseInt(serialNo)-1).getAmount());
						payment.setPaymentMode(debitlist.get(Integer.parseInt(serialNo)-1).getPaymentMode());
						payment.setCurrencyType(debitlist.get(Integer.parseInt(serialNo)-1).getCurrencyType());
						payment.setStatus("Debit");
						debitlist.set(Integer.parseInt(serialNo)-1, payment);						
					}else if(status.equals("Credit")){
						payment.setCtransactionType(creditlist.get(Integer.parseInt(serialNo)-1).getCtransactionType());
						payment.setMaddress(creditlist.get(Integer.parseInt(serialNo)-1).getMaddress());
						payment.setPaydate(creditlist.get(Integer.parseInt(serialNo)-1).getPaydate());
						payment.setDuedate(creditlist.get(Integer.parseInt(serialNo)-1).getDuedate());
						payment.setTransFile(creditlist.get(Integer.parseInt(serialNo)-1).getTransFile());
						payment.setCnote(creditlist.get(Integer.parseInt(serialNo)-1).getCnote());
						payment.setCparticular(creditlist.get(Integer.parseInt(serialNo)-1).getCparticular());
						payment.setAmount(creditlist.get(Integer.parseInt(serialNo)-1).getAmount());
						payment.setCpaymentMode(creditlist.get(Integer.parseInt(serialNo)-1).getCpaymentMode());
						payment.setCurrencyType(creditlist.get(Integer.parseInt(serialNo)-1).getCurrencyType());
						payment.setStatus("Credit");
						creditlist.set(Integer.parseInt(serialNo)-1, payment);						
					}	
					RequestContext.getCurrentInstance().execute("PF('paymentDetails1').hide();");
					setBankname("");setChequeno("");setChequedate(null);
				}
			}	
		}catch(Exception e){
			logger.error("inside exception ",e);
		}finally {
			controller =null;
		}
		return "";
	}
	
	private boolean validates(boolean flag) {
		logger.info("[validates()] --------------- Inside validates() method() if cheque------------------------");
		boolean valid = true;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		if (StringUtils.isEmpty(bankname)) {
			if (flag) {
				if(paymentMode.equals("Card")){
					fieldName = CommonValidate.findComponentInRoot("bankname").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter Bank Name"));
				}
				else if(paymentMode.equals("Transfer")){
					fieldName = CommonValidate.findComponentInRoot("tbankname").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter Bank Name"));
				}				
			}
			valid = false;
		}
		if (StringUtils.isEmpty(cardno)) {
			if (flag) {
				if(paymentMode.equals("Card")){
					fieldName = CommonValidate.findComponentInRoot("cardno").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter Card Number"));
				}else if(paymentMode.equals("Transfer")){
					fieldName = CommonValidate.findComponentInRoot("tcardno").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter Account Number"));
				}			
			}
			valid = false;
		}		
		return valid;
	}
	
	private boolean validatech(boolean flag) {
		logger.info("[validates()] --------------- Inside validatech() method()------------------------");
		boolean valid = true;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		if (StringUtils.isEmpty(bankname)) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("bankname1").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter Bank Name"));
			}
			valid = false;

		}if (StringUtils.isEmpty(chequeno)) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("chequeno").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter Cheque Number"));
			}
			valid = false;
		}
		try{
			if (chequedate.equals(null)) {
				if (flag) {
					fieldName = CommonValidate.findComponentInRoot("chequedate").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Select Cheque Date"));
				}
				valid = false;
			}
		}catch(Exception e){
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("chequedate").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select Cheque Date"));
			}
			valid = false;
		}	finally {
			controller =null;
		}	
		return valid;
	}
	
	public void closeTranasction(){
		RequestContext.getCurrentInstance().closeDialog("aTransactionForm");
	}
	
	public void accountDetails(ValueChangeEvent v){
		logger.info("[accountDetails()] --------------- Inside accountDetails() method()------------------------");
		add=null;accountType="";description="";
		String type=v.getNewValue().toString();
		detailTypes=new ArrayList<String>();
		try{
			setParticular(type);
			setSerialNo(v.getComponent().getAttributes().get("serialNo").toString());
			setStatus(v.getComponent().getAttributes().get("status").toString());
			logger.info("status "+status);
			if(type.equals("Add")){
				if(status.equals("Credit")){
					setAccounts("Other Current Assets");
					detailTypes.add("Allowance for Bad Debts");
					detailTypes.add("Development Costs");
					detailTypes.add("Employee Cash Advances");
					detailTypes.add("Inventory");
					detailTypes.add("Investment-Mortage/Real Estate Loans");
					detailTypes.add("Investment-Tax-Exempt Securities");
					detailTypes.add("Investment-UAE Government Obligations");
					detailTypes.add("Loans to Officers");
					detailTypes.add("Loans to Others");
					detailTypes.add("Loans to Stockholders");
					detailTypes.add("Other Current Assets");
					detailTypes.add("Prepaid Expenses");
					detailTypes.add("Retainage");
					detailTypes.add("Undeposited Funds");
				}else if(status.equals("Debit")){
					detailTypes.add("Advertising/Promotional");
					detailTypes.add("Auto");
					detailTypes.add("Bad Debts");
					detailTypes.add("Inventory");
					detailTypes.add("Bank Charges");
					detailTypes.add("Chartiable Contributions");
					detailTypes.add("Cost of Labor");
					detailTypes.add("Dues & Subscriptions");
					detailTypes.add("Entertainment");
					detailTypes.add("Entertainment Meals");
					detailTypes.add("Equipment Rental");
					detailTypes.add("Finance Costs");
					detailTypes.add("Insurance");
					detailTypes.add("Interest Paid");
					detailTypes.add("Legal & Professional Fees");
					detailTypes.add("Office/General Administrative Expenses");
					detailTypes.add("Other Miscellaneous Service Cost");
					detailTypes.add("Payroll Expenses");
					detailTypes.add("Promotional Meals");
					detailTypes.add("Rent or Lease of Buildings");
					detailTypes.add("Repair & Maintenance");
					detailTypes.add("Shipping,Freight & Delivery");
					detailTypes.add("Supplies & Materials");
					detailTypes.add("Taxes Paid");
					detailTypes.add("Travel");
					detailTypes.add("Travel Meals");
					detailTypes.add("Unapplied Cash Bill Payment Expense");
					detailTypes.add("Utilities");
					setAccounts("Expenses");
				}
				RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
			}
		}catch(Exception e){
			logger.error("inside exception ",e);
		}finally {
			controller =null;
		}
	}
	
	public String addAccount(){
		logger.info("[addAccount()] --------------- Inside addAccount() method()------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
		try{
			if(validateaccount(true)){
				String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
				ATransaction payment=new ATransaction();
				payment.setAccountType(accountType);
				payment.setTransactionType(transactionType);
				payment.setAmount(amount);
				payment.setDescription(description);
				ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				String acntstatus=controller.saveAcountDeposit(clientID,payment);
				if(acntstatus.equalsIgnoreCase("Success"))
				{
					RequestContext.getCurrentInstance().execute("PF('addAccount').hide();");
					RequestContext.getCurrentInstance().execute("PF('confirmDialog1').show();");
					atypeList.add(accountType);					
					ATransaction atrans=new ATransaction();
					atrans.setSerialNo(serialNo);
					if(status.equals("Debit")){
						logger.info("[addAccount()] --------------- Inside addAccount() method() if condition debit------------------------");
						atrans.setAmount(debitlist.get(Integer.parseInt(serialNo)-1).getAmount());
						atrans.setNote(debitlist.get(Integer.parseInt(serialNo)-1).getNote());
						atrans.setParticular(accountType);
						atrans.setPaymentMode(debitlist.get(Integer.parseInt(serialNo)-1).getPaymentMode());
						atrans.setBankname(debitlist.get(Integer.parseInt(serialNo)-1).getBankname());
						atrans.setCardno(debitlist.get(Integer.parseInt(serialNo)-1).getCardno());
						atrans.setChequeno(debitlist.get(Integer.parseInt(serialNo)-1).getChequeno());
						atrans.setStatus(debitlist.get(Integer.parseInt(serialNo)-1).getStatus());
						atrans.setChequedate(debitlist.get(Integer.parseInt(serialNo)-1).getChequedate());
						atrans.setTransactionType(debitlist.get(Integer.parseInt(serialNo)-1).getTransactionType());
						atrans.setMaddress(debitlist.get(Integer.parseInt(serialNo)-1).getMaddress());
						atrans.setPaydate(debitlist.get(Integer.parseInt(serialNo)-1).getPaydate());
						atrans.setDuedate(debitlist.get(Integer.parseInt(serialNo)-1).getDuedate());
						atrans.setTransFile(debitlist.get(Integer.parseInt(serialNo)-1).getTransFile());
						atrans.setCurrencyType(debitlist.get(Integer.parseInt(serialNo)-1).getCurrencyType());
						atrans.setStatus("Debit");
						debitlist.set(Integer.parseInt(serialNo)-1, atrans);
					}else if(status.equals("Credit")){
						logger.info("[addAccount()] --------------- Inside addAccount() method() if condition credit------------------------");
						atrans.setAmount(creditlist.get(Integer.parseInt(serialNo)-1).getAmount());
						atrans.setCnote(creditlist.get(Integer.parseInt(serialNo)-1).getNote());
						atrans.setCparticular(accountType);
						logger.info("typeeeeeee"+atrans.getCparticular());
						atrans.setCpaymentMode(creditlist.get(Integer.parseInt(serialNo)-1).getPaymentMode());
						atrans.setBankname(creditlist.get(Integer.parseInt(serialNo)-1).getBankname());
						atrans.setCardno(creditlist.get(Integer.parseInt(serialNo)-1).getCardno());
						atrans.setChequeno(creditlist.get(Integer.parseInt(serialNo)-1).getChequeno());
						atrans.setStatus(creditlist.get(Integer.parseInt(serialNo)-1).getStatus());
						atrans.setChequedate(creditlist.get(Integer.parseInt(serialNo)-1).getChequedate());
						atrans.setCtransactionType(creditlist.get(Integer.parseInt(serialNo)-1).getTransactionType());
						atrans.setMaddress(creditlist.get(Integer.parseInt(serialNo)-1).getMaddress());
						atrans.setPaydate(creditlist.get(Integer.parseInt(serialNo)-1).getPaydate());
						atrans.setDuedate(creditlist.get(Integer.parseInt(serialNo)-1).getDuedate());
						atrans.setTransFile(creditlist.get(Integer.parseInt(serialNo)-1).getTransFile());
						atrans.setCurrencyType(creditlist.get(Integer.parseInt(serialNo)-1).getCurrencyType());
						atrans.setStatus("Credit");
						creditlist.set(Integer.parseInt(serialNo)-1, atrans);
					}
					accountType="";description="";
				}
			}
		}catch(Exception e){
			logger.error("inside exception ",e);
		}finally {
			controller =null;
		}
		return "";
	}
	
	public String addAccount1(){
		logger.info("[addAccount1()] --------------- Inside addAccount1() method()------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
		try{
				if(validateaccount(true)){
					ATransaction payment=new ATransaction();
					payment.setAccountType(accountType);
					payment.setDescription(description);
					ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
					controller = (DemoController) ctx.getBean("controller");
					String acntstatus=controller.addAccount(payment);
					if(acntstatus.equalsIgnoreCase("success"))
					{
						RequestContext.getCurrentInstance().execute("PF('addAccount').hide();");
						RequestContext.getCurrentInstance().execute("PF('confirmDialog1').show();");
						atypeList.add(accountType);	
						setParticular(accountType);
					}
				}accountType="";description="";
			}catch(Exception e){
				logger.error("inside exception ",e);
			}
			return "";
		
	}
			
				
	private boolean validateaccount(boolean flag) {
		logger.info("[addAccount1()] --------------- Inside validateaccount() method()------------------------");
		logger.info("111111");
		boolean valid = true;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		if (StringUtils.isEmpty(accountType)) {
			if (flag) {
				logger.info("33333333333");
				fieldName = CommonValidate.findComponentInRoot("acnt").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter Account Type"));
			}
			valid = false;

		}
		else if (StringUtils.isNotEmpty(accountType)) {
			if (flag) {
			logger.info("acccccccc  "+accountType+"sizeee"+atypeList.size());
			for (int i = 0; i < atypeList.size(); i++) {
				if(accountType.equalsIgnoreCase(atypeList.get(i)))
				{
					fieldName = CommonValidate.findComponentInRoot("acnt").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Account Type already Exist"));
					valid = false;
				}
			}
			}
		}
		logger.info("2222  "+valid);
		return valid;
	}
	
	/*public void closeTranasction(){
		RequestContext.getCurrentInstance().closeDialog("aTransactionForm");
	}*/
	
	public void transDetails(ValueChangeEvent v){
		logger.info("[addAccount1()] --------------- Inside transDetails() method()------------------------");
		logger.info("inside transDetails ValueChange"+v.getNewValue());
		setPaydate(null);setDuedate(null);setAmount("");setMaddress("");
		setTransFile(null);setCurrencyType("");
		logger.info("type "+v.getNewValue());
		String type=v.getNewValue().toString();
		serialNo=v.getComponent().getAttributes().get("serialNo").toString();
		status=v.getComponent().getAttributes().get("status").toString();
		try{
			setTransactionType(type);
			RequestContext.getCurrentInstance().execute("PF('transDetails1').show();");
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
	}
	
	private String currencyType;
	private String currency;
	
	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String savetransDetails(){
		logger.info("[addAccount1()] --------------- Inside savetransDetails() method()------------------------");
		try
		{
			RequestContext.getCurrentInstance().execute("PF('transDetails1').show();");
			if(validatetrans(true)){
				RequestContext.getCurrentInstance().execute("PF('transDetails1').hide();");
				ATransaction payment=new ATransaction();
				payment.setPaydate(paydate);
				payment.setDuedate(duedate);
				payment.setMaddress(maddress);
				payment.setAmount(amount);
				payment.setSerialNo(serialNo);
				payment.setTransFile(transFile);
				logger.info("----------"+transFile);
				payment.setCurrencyType(currencyType);
				if(status.equals("Debit")){
					payment.setNote(debitlist.get(Integer.parseInt(serialNo)-1).getNote());
					payment.setParticular(debitlist.get(Integer.parseInt(serialNo)-1).getParticular());
					payment.setTransactionType(debitlist.get(Integer.parseInt(serialNo)-1).getTransactionType());
					payment.setPaymentMode(debitlist.get(Integer.parseInt(serialNo)-1).getPaymentMode());
					payment.setBankname(debitlist.get(Integer.parseInt(serialNo)-1).getBankname());
					payment.setCardno(debitlist.get(Integer.parseInt(serialNo)-1).getCardno());
					payment.setChequeno(debitlist.get(Integer.parseInt(serialNo)-1).getChequeno());
					payment.setStatus(debitlist.get(Integer.parseInt(serialNo)-1).getStatus());
					payment.setChequedate(debitlist.get(Integer.parseInt(serialNo)-1).getChequedate());
					payment.setStatus("Debit");
					debitlist.set(Integer.parseInt(serialNo)-1, payment);						
				}else if(status.equals("Credit")){
					payment.setCnote(creditlist.get(Integer.parseInt(serialNo)-1).getCnote());
					payment.setCparticular(creditlist.get(Integer.parseInt(serialNo)-1).getCparticular());
					payment.setCpaymentMode(creditlist.get(Integer.parseInt(serialNo)-1).getCpaymentMode());
					payment.setCtransactionType(creditlist.get(Integer.parseInt(serialNo)-1).getCtransactionType());
					payment.setBankname(creditlist.get(Integer.parseInt(serialNo)-1).getBankname());
					payment.setCardno(creditlist.get(Integer.parseInt(serialNo)-1).getCardno());
					payment.setChequeno(creditlist.get(Integer.parseInt(serialNo)-1).getChequeno());
					payment.setStatus(creditlist.get(Integer.parseInt(serialNo)-1).getStatus());
					payment.setChequedate(creditlist.get(Integer.parseInt(serialNo)-1).getChequedate());
					payment.setStatus("Credit");
					creditlist.set(Integer.parseInt(serialNo)-1, payment);				
				}	
				setPaydate(null);setDuedate(null);setAmount("");setMaddress("");
				setTransFile(null);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	private boolean validatetrans(boolean flag) {
		logger.info("[addAccount1()] --------------- Inside validatetrans() method()------------------------");
		logger.info("111111");
		boolean valid = true;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			if (paydate.equals(null)) {
				if (flag) {
					fieldName = CommonValidate.findComponentInRoot("paydate").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Select Payment Date"));
				}
				valid = false;
			}
		}catch(Exception e){
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("paydate").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select Payment Date"));
			}
			valid = false;
		}	
		try{
			if (duedate.equals(null)) {
				if (flag) {
					fieldName = CommonValidate.findComponentInRoot("duedate").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Select Due Date"));
				}
				valid = false;
			}
		}catch(Exception e){
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("duedate").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select Due Date"));
			}
			valid = false;
		}	
		if (StringUtils.isEmpty(maddress)) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("maddress").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter Mailing Address"));
			}
			valid = false;

		}if (StringUtils.isEmpty(amount)) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("amount").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter Amount"));
			}
			valid = false;
		}
		else if (!amount.matches("^\\d+(\\.\\d+)*$")) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("amount").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Amount should be numeric"));
			}
			valid = false;
		}if (StringUtils.isEmpty(currencyType)) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("currency").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select Currency Type"));
			}
			valid = false;
		}
		return valid;
	}
			
	
	public String dummyAction(FileUploadEvent event) throws IOException {
		logger.info("[addAccount1()] --------------- Inside dummyAction() method()------------------------");
		ATransaction payment=new ATransaction();
		  this.transFile = event.getFile();
		  payment.setTransFile(event.getFile());
		  FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		  FacesContext.getCurrentInstance().addMessage(null, message);
		  /*
		   * File chartFile = new File(event.getFile().getContentType());
		   * listImage=null; logger.info(listImage); final UploadedFile
		   * uploadedFile = event.getFile(); chart= new DefaultStreamedContent(new
		   * FileInputStream(event.getFile().getContentType()),"image/png");
		   * 
		   * logger.info("Uploaded File Name Is :"+uploadedFile.getFileName());
		   * listImage = new DefaultStreamedContent(new
		   * ByteArrayInputStream(uploadedFile.getContents()), "image/png");
		   */

		  return "";
		 }
	
	public void imageview(OutputStream out, Object data) throws IOException {
		logger.info("[addAccount1()] --------------- Inside imageview() method()------------------------");
		String s ="/home/ec2-user/File_Inacsys/Transaction/";
		
		try{			
			BufferedImage img = ImageIO
					.read(new File(s + viewlist.get(0).getFilepath()));
			ImageIO.write(img, "png", out);
			flag="1";flag1="none";
		}catch(Exception e){
			e.printStackTrace();
			flag1="1";flag="none";
		}
		
	}
	private StreamedContent stream;
	public StreamedContent getStream() {
		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}

	public void file() throws FileNotFoundException {
		logger.info("[addAccount1()] --------------- Inside file() method()------------------------");
	    try{
	    	InputStream input=null;
	    	File file=new File("/home/ec2-user/File_Inacsys/Transaction/"+viewlist.get(0).getFilepath());
			input = new FileInputStream(file);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			this.setStream(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
	    }
	    catch(Exception e){
	    	logger.warn(" exception - "+e);
	    	logger.warn("Inside Exception",e);
	    }
	   }
	
	/*UDHAYA*/
 	
	private List<String> detailTypes=null;
	private List<String> accountTypes=null;
	private String balance;
	private String payAmount;
	private String balAmount;
	private String accounts;
	private String detailName;
	private String payStatus;
	private String payValidate;
	private boolean aflag;
	
	public boolean isAflag() {
		return aflag;
	}

	public void setAflag(boolean aflag) {
		this.aflag = aflag;
	}

	public String getBalAmount() {
		return balAmount;
	}

	public void setBalAmount(String balAmount) {
		this.balAmount = balAmount;
	}

	public String getPayValidate() {
		return payValidate;
	}

	public void setPayValidate(String payValidate) {
		this.payValidate = payValidate;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public List<String> getAccountTypes() {
		return accountTypes;
	}

	public void setAccountTypes(List<String> accountTypes) {
		this.accountTypes = accountTypes;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public List<String> getDetailTypes() {
		return detailTypes;
	}

	public void setDetailTypes(List<String> detailTypes) {
		this.detailTypes = detailTypes;
	}

	public String paymentTrans(){
		logger.info("[addAccount1()] --------------- Inside paymentTrans() method()------------------------");
		logger.info("payment trans "+status);
		DemoController controller = null;
		setPayStatus("payment");
		ATransaction atrans=new ATransaction();
		try{
			viewForm();		
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			atrans.setTransactionNo(transactionNo);
			atypeList=controller.getAccountDepsit(clientID,atrans);			
			logger.info("account deposit size "+atypeList.size());
			setBalAmount(atrans.getBalAmount());
			logger.info("bal amount "+balAmount+" - "+atrans.getBalAmount()+" acconts "+accounts);
			if(!balAmount.equals(amount)) aflag=true;
			else aflag=false;
			RequestContext.getCurrentInstance().execute("PF('payment').show();");			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			setPayAmount("");
			setDetailName("");
		}
		return "";
	}
	
	public void addAccount(ValueChangeEvent v){
		logger.info("[addAccount1()] --------------- Inside addAccount() method()------------------------");
		logger.info("add account -- "+v.getNewValue().toString());		
		try{
			String deposit=v.getNewValue().toString();
			detailTypes=new ArrayList<String>();
			if(deposit.equals("add")){			
				RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
				RequestContext.getCurrentInstance().execute("PF('payment').hide();");
				if(status.equals("Debit")){
					detailTypes.add("Advertising/Promotional");
					detailTypes.add("Auto");
					detailTypes.add("Bad Debts");
					detailTypes.add("Inventory");
					detailTypes.add("Bank Charges");
					detailTypes.add("Chartiable Contributions");
					detailTypes.add("Cost of Labor");
					detailTypes.add("Dues & Subscriptions");
					detailTypes.add("Entertainment");
					detailTypes.add("Entertainment Meals");
					detailTypes.add("Equipment Rental");
					detailTypes.add("Finance Costs");
					detailTypes.add("Insurance");
					detailTypes.add("Interest Paid");
					detailTypes.add("Legal & Professional Fees");
					detailTypes.add("Office/General Administrative Expenses");
					detailTypes.add("Other Miscellaneous Service Cost");
					detailTypes.add("Payroll Expenses");
					detailTypes.add("Promotional Meals");
					detailTypes.add("Rent or Lease of Buildings");
					detailTypes.add("Repair & Maintenance");
					detailTypes.add("Shipping,Freight & Delivery");
					detailTypes.add("Supplies & Materials");
					detailTypes.add("Taxes Paid");
					detailTypes.add("Travel");
					detailTypes.add("Travel Meals");
					detailTypes.add("Unapplied Cash Bill Payment Expense");
					detailTypes.add("Utilities");
					setAccounts("Expenses");
				}else if(status.equals("Credit")){
					detailTypes.add("Allowance for Bad Debts");
					detailTypes.add("Development Costs");
					detailTypes.add("Employee Cash Advances");
					detailTypes.add("Inventory");
					detailTypes.add("Investment-Mortage/Real Estate Loans");
					detailTypes.add("Investment-Tax-Exempt Securities");
					detailTypes.add("Investment-UAE Government Obligations");
					detailTypes.add("Loans to Officers");
					detailTypes.add("Loans to Others");
					detailTypes.add("Loans to Stockholders");
					detailTypes.add("Other Current Assets");
					detailTypes.add("Prepaid Expenses");
					detailTypes.add("Retainage");
					detailTypes.add("Undeposited Funds");
					setAccounts("Other Current Assets");
				}				
				setDetailName("");
				setBalance("");
				setPayValidate("");
			}else{
				RequestContext.getCurrentInstance().execute("PF('addAccount').hide();");
			}
		}catch(Exception e){
			logger.warn(" exception - "+e);
		}
	}
	
	public void accountType(ValueChangeEvent v){
		logger.info("[addAccount1()] --------------- Inside accountType() method()------------------------");
		logger.info("account "+v.getNewValue().toString());
		detailTypes=new ArrayList<String>();		
		try{
			String type=v.getNewValue().toString();
			if(type.equals("Other Current Assets")){
				detailTypes.add("Allowance for Bad Debts");
				detailTypes.add("Development Costs");
				detailTypes.add("Employee Cash Advances");
				detailTypes.add("Inventory");
				detailTypes.add("Investment-Mortage/Real Estate Loans");
				detailTypes.add("Investment-Tax-Exempt Securities");
				detailTypes.add("Investment-UAE Government Obligations");
				detailTypes.add("Loans to Officers");
				detailTypes.add("Loans to Others");
				detailTypes.add("Loans to Stockholders");
				detailTypes.add("Other Current Assets");
				detailTypes.add("Prepaid Expenses");
				detailTypes.add("Retainage");
				detailTypes.add("Undeposited Funds");
			}else if(type.equals("Bank")){
				detailTypes.add("Cash on hand");
				detailTypes.add("Checking");
				detailTypes.add("Money market");
				detailTypes.add("Rents held in Trust");
				detailTypes.add("Savings");
				detailTypes.add("Trust Account");			
			}else if(type.equals("Expenses")){
				detailTypes.add("Advertising/Promotional");
				detailTypes.add("Auto");
				detailTypes.add("Bad Debts");
				detailTypes.add("Inventory");
				detailTypes.add("Bank Charges");
				detailTypes.add("Chartiable Contributions");
				detailTypes.add("Cost of Labor");
				detailTypes.add("Dues & Subscriptions");
				detailTypes.add("Entertainment");
				detailTypes.add("Entertainment Meals");
				detailTypes.add("Equipment Rental");
				detailTypes.add("Finance Costs");
				detailTypes.add("Insurance");
				detailTypes.add("Interest Paid");
				detailTypes.add("Legal & Professional Fees");
				detailTypes.add("Office/General Administrative Expenses");
				detailTypes.add("Other Miscellaneous Service Cost");
				detailTypes.add("Payroll Expenses");
				detailTypes.add("Promotional Meals");
				detailTypes.add("Rent or Lease of Buildings");
				detailTypes.add("Repair & Maintenance");
				detailTypes.add("Shipping,Freight & Delivery");
				detailTypes.add("Supplies & Materials");
				detailTypes.add("Taxes Paid");
				detailTypes.add("Travel");
				detailTypes.add("Travel Meals");
				detailTypes.add("Unapplied Cash Bill Payment Expense");
				detailTypes.add("Utilities");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void detailSelect(ValueChangeEvent v){
		logger.info("[addAccount1()] --------------- Inside detailSelect() method()------------------------");
		logger.info("detail "+v.getNewValue().toString());
		setDetailName(v.getNewValue().toString());
	}
	
	public String accountDeposit(){
		logger.info("[addAccount1()] --------------- Inside accountDeposit() method()------------------------");
		logger.info("inside accountdeposit");
		DemoController controller = null;
		ATransaction aTransaction=new ATransaction();
		String fieldName;FacesContext fc=FacesContext.getCurrentInstance();
		int c=0;
		try{
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			logger.info("type "+accounts+" detail - "+detailName+" serial "+serialNo+" type "+status);
			if(detailName==null || detailName==""){					
				RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
				fieldName = CommonValidate.findComponentInRoot("detName").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the Detail Type"));
				c++;
			}
			if(c==0){
				if(accounts.equals("add")){
					if(status.equals("Credit")) aTransaction.setAccountType("Other Current Assets");
					else if(status.equals("Debit")) aTransaction.setAccountType("Expenses");
				}
				else{
					aTransaction.setAccountType(accounts);
				}
				aTransaction.setTransactionType(detailName);
				aTransaction.setAmount(balance);
				String statuss=controller.saveAcountDeposit(clientID,aTransaction);
				if(statuss.equals("Exist")){
					RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
					throw new Exception("Another account is already using this type. Please use a different type.");
				}else{
					setPayValidate("");
					aTransaction.setTransactionNo(transactionNo);
					atypeList=controller.getAccountDepsit(clientID,aTransaction);	
					logger.info("accounts "+atypeList);
					if(payStatus.equals("payment")){
						setAccounts(detailName);
						RequestContext.getCurrentInstance().execute("PF('addAccount').hide();");
						RequestContext.getCurrentInstance().execute("PF('payment').show();");
					}else{
						RequestContext.getCurrentInstance().execute("PF('addAccount').hide();");
						ATransaction payment=new ATransaction();						
						if(status.equals("Debit")){
							payment.setNote(debitlist.get(Integer.parseInt(serialNo)-1).getNote());
							payment.setParticular(detailName);
							payment.setTransactionType(debitlist.get(Integer.parseInt(serialNo)-1).getTransactionType());
							payment.setPaymentMode(debitlist.get(Integer.parseInt(serialNo)-1).getPaymentMode());
							payment.setBankname(debitlist.get(Integer.parseInt(serialNo)-1).getBankname());
							payment.setCardno(debitlist.get(Integer.parseInt(serialNo)-1).getCardno());
							payment.setChequeno(debitlist.get(Integer.parseInt(serialNo)-1).getChequeno());
							payment.setStatus(debitlist.get(Integer.parseInt(serialNo)-1).getStatus());
							payment.setChequedate(debitlist.get(Integer.parseInt(serialNo)-1).getChequedate());
							payment.setPaydate(debitlist.get(Integer.parseInt(serialNo)-1).getPaydate());
							payment.setDuedate(debitlist.get(Integer.parseInt(serialNo)-1).getDuedate());
							payment.setMaddress(debitlist.get(Integer.parseInt(serialNo)-1).getMaddress());
							payment.setAmount(debitlist.get(Integer.parseInt(serialNo)-1).getAmount());
							payment.setSerialNo(debitlist.get(Integer.parseInt(serialNo)-1).getSerialNo());
							payment.setTransFile(debitlist.get(Integer.parseInt(serialNo)-1).getTransFile());
							payment.setCurrency(debitlist.get(Integer.parseInt(serialNo)-1).getCurrency());
							payment.setCurrencyType(debitlist.get(Integer.parseInt(serialNo)-1).getCurrencyType());
							payment.setStatus("Debit");
							debitlist.set(Integer.parseInt(serialNo)-1, payment);						
						}else if(status.equals("Credit")){
							payment.setCnote(creditlist.get(Integer.parseInt(serialNo)-1).getCnote());
							payment.setCparticular(detailName);
							payment.setCpaymentMode(creditlist.get(Integer.parseInt(serialNo)-1).getCpaymentMode());
							payment.setCtransactionType(creditlist.get(Integer.parseInt(serialNo)-1).getCtransactionType());
							payment.setBankname(creditlist.get(Integer.parseInt(serialNo)-1).getBankname());
							payment.setCardno(creditlist.get(Integer.parseInt(serialNo)-1).getCardno());
							payment.setChequeno(creditlist.get(Integer.parseInt(serialNo)-1).getChequeno());
							payment.setStatus(creditlist.get(Integer.parseInt(serialNo)-1).getStatus());
							payment.setChequedate(creditlist.get(Integer.parseInt(serialNo)-1).getChequedate());
							payment.setPaydate(creditlist.get(Integer.parseInt(serialNo)-1).getPaydate());
							payment.setDuedate(creditlist.get(Integer.parseInt(serialNo)-1).getDuedate());
							payment.setMaddress(creditlist.get(Integer.parseInt(serialNo)-1).getMaddress());
							payment.setAmount(creditlist.get(Integer.parseInt(serialNo)-1).getAmount());
							payment.setSerialNo(creditlist.get(Integer.parseInt(serialNo)-1).getSerialNo());
							payment.setTransFile(creditlist.get(Integer.parseInt(serialNo)-1).getTransFile());
							payment.setCurrency(creditlist.get(Integer.parseInt(serialNo)-1).getCurrency());
							payment.setCurrencyType(creditlist.get(Integer.parseInt(serialNo)-1).getCurrencyType());
							payment.setStatus("Credit");
							creditlist.set(Integer.parseInt(serialNo)-1, payment);				
						}	
						logger.info("details "+debitlist.get(Integer.parseInt(serialNo)-1).getParticular());
					}				
				}
			}
		}catch(Exception e){
			logger.warn(" exception - "+e);			
			setPayValidate(e.getMessage());
		}
		return "";
	}
	
	public String makePayment(){
		logger.info("[addAccount1()] --------------- Inside makePayment() method()------------------------");
		logger.info("make payment");
		ATransaction atransaction=new ATransaction();
		String fieldName;FacesContext fc=FacesContext.getCurrentInstance();
		int c=0;
		try{
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			if(accounts.equals("")){
				fieldName = CommonValidate.findComponentInRoot("details").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the Deposit To"));
				c++;
			}if(payAmount.equals("")){
				fieldName = CommonValidate.findComponentInRoot("payamt").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter the Amount"));
				c++;
			}else if(new BigDecimal(payAmount).compareTo(new BigDecimal(balAmount))==1){
				fieldName = CommonValidate.findComponentInRoot("payamt").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("You entered a exceed Amount"));
				c++;
			}
			if(c==0){
				logger.info("accounts type "+accounts);
				atransaction.setTransactionNo(transactionNo);
				atransaction.setDetailName(detailName);
				atransaction.setAccounts(accounts);
				atransaction.setPayAmount(payAmount);
				atransaction.setAmount(amount);
				atransaction.setBalAmount(balAmount);
				controller.saveTransPayment(clientID,atransaction);
				RequestContext.getCurrentInstance().execute("PF('payment').hide();");
				RequestContext.getCurrentInstance().execute("PF('payDialog').show();");
			}			
		}catch(Exception e){
			logger.warn(" exception - "+e);
		}
		return "";
	}
	
	/*John Clinton*/
	
	
	 private void clearAllValue() {
		aTransaction.setTransactionType("");
		aTransaction.setCode("");
		aTransaction.setCodeDescription("");
		aTransaction.setRefNo("");
		aTransaction.setEditStatus("");
		totalAmount=0.0;
		aTransaction.setTransamount("0");aTransaction.setSubTotalAmount("0");aTransaction.setCurrencyAmount("0");
		aTransaction.setCurrencyType("");aTransaction.setTotalAmount("0");aTransaction.setLocation("");aTransaction.setTaxType("Exclusive of Tax");
	}

	public void TaddRowexpense(){
		logger.info("TaddRowexpense");
		ATransaction atrans=new ATransaction();
		atrans.setTaxes("");
		 getexpenseTransactionlist().add(atrans);
		 
		 }
	
	public void expenseResource(String resource){
		ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		controller = (DemoController) ctx.getBean("controller");
		 String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
		 aTransaction.setExpenseResourcetype(resource);
		controller.expenseResource(clientID,aTransaction);
	}
	
/*	johnclinton*/
	AccountsDatabean accountsDatabean=new AccountsDatabean();
	
	public AccountsDatabean getAccountsDatabean() {
		return accountsDatabean;
	}

	public void setAccountsDatabean(AccountsDatabean accountsDatabean) {
		this.accountsDatabean = accountsDatabean;
	}

	/*john clinton 06-07-2017*/
			
				public Double totalAmount;
			
				
			
				public Double getTotalAmount() {
					return totalAmount;
				}

				public void setTotalAmount(Double totalAmount) {
					this.totalAmount = totalAmount;
				}

				
	/*neela*/
		
/*Manos*/
				
				public void Mailaddress(ValueChangeEvent vc){
					logger.info("Mailaddress---->"+vc.getNewValue().toString());
					aTransaction.setVendorName(vc.getNewValue().toString());
					String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
					 controller.mailresource(clientID,aTransaction);
					 }

				private boolean expensevalidation(boolean valid) {
					System.out.println("-------------------expensevalidation method--------start-------------------");
					 String fieldName;
					 FacesContext fc = FacesContext.getCurrentInstance();
					try {
						if(StringUtils.isEmpty(aTransaction.getCurrencyType())){
							System.out.println("ckeck"+StringUtils.isEmpty(aTransaction.getTransactionType()));
						     fieldName=CommonValidate.findComponentInRoot("currency").getClientId(fc);
						     fc.addMessage(fieldName, new FacesMessage("Please select the Currency Type"));
						     valid=false;
						}
						if(StringUtils.isEmpty(aTransaction.getTransactionType())){
							System.out.println("ckeck"+StringUtils.isEmpty(aTransaction.getTransactionType()));
						     fieldName=CommonValidate.findComponentInRoot("details").getClientId(fc);
						     fc.addMessage(fieldName, new FacesMessage("Please select the Account Type"));
						     valid=false;
						}else{
							System.out.println("-------------------inside else condition transaction TYPE---------------------------");
							if (aTransaction.getTransactionType().equalsIgnoreCase("Bill")||aTransaction.getTransactionType().equalsIgnoreCase("Vendor Credit")) {
								if(!aTransaction.getClientID().equals("CNT0000021")){
									if(StringUtils.isEmpty(aTransaction.getVendorName())){
										fieldName=CommonValidate.findComponentInRoot("vendorname").getClientId(fc);
										fc.addMessage(fieldName, new FacesMessage("Please select the Vendor name"));
										valid=false;
										System.out.println("vendor------------------"+valid);
									}
								}
							
							}
							if (aTransaction.getTransactionType().equalsIgnoreCase("Expense")||aTransaction.getTransactionType().equalsIgnoreCase("Check")) {
								if(StringUtils.isEmpty(aTransaction.getVendorName())){
								     fieldName=CommonValidate.findComponentInRoot("payee").getClientId(fc);
								     fc.addMessage(fieldName, new FacesMessage("Please select the Payee"));
								     valid=false;
								}
								if(StringUtils.isEmpty(aTransaction.getFromAccount()) || aTransaction.getFromAccount().equalsIgnoreCase("addnew")){
								     fieldName=CommonValidate.findComponentInRoot("account").getClientId(fc);
								     fc.addMessage(fieldName, new FacesMessage("Please choose Account"));
								     valid=false;
								}
							}
							System.out.println("-------------expenseTransactionlist------size--------------"+expenseTransactionlist.size());
							if(expenseTransactionlist.size()==0){
								 fieldName = CommonValidate.findComponentInRoot("accounts").getClientId(fc);
								 fc.addMessage(fieldName, new FacesMessage("Please enter the atleast one row value"));
								 valid = false;
							 }else{
								 for (int i = 0; i < expenseTransactionlist.size(); i++) {
										if (i==0) {
											if(aTransaction.getCountry().equalsIgnoreCase("India")){
												if(aTransaction.getTaxType().equalsIgnoreCase("")|| aTransaction.getTaxType().equalsIgnoreCase("Out of scope of Tax")){
													if(StringUtils.isEmpty(expenseTransactionlist.get(i).getToAccount())||expenseTransactionlist.get(i).getToAccount().equals("select")||StringUtils.isEmpty(expenseTransactionlist.get(i).getDescription())
															||StringUtils.isEmpty(expenseTransactionlist.get(i).getAmount())){
													     fieldName=CommonValidate.findComponentInRoot("accounts").getClientId(fc);
													     fc.addMessage(fieldName, new FacesMessage("Please enter the first row value"));
													     valid=false;
													}
												}else{
													if(StringUtils.isEmpty(expenseTransactionlist.get(i).getToAccount())||expenseTransactionlist.get(i).getToAccount().equals("select")||StringUtils.isEmpty(expenseTransactionlist.get(i).getDescription())
															||StringUtils.isEmpty(expenseTransactionlist.get(i).getAmount()) ||StringUtils.isEmpty(expenseTransactionlist.get(i).getTaxes())){
													     fieldName=CommonValidate.findComponentInRoot("accounts").getClientId(fc);
													     fc.addMessage(fieldName, new FacesMessage("Please enter the first row value"));
													     valid=false;
													}
												}
											}else{
												if(StringUtils.isEmpty(expenseTransactionlist.get(i).getToAccount())||expenseTransactionlist.get(i).getToAccount().equals("select")||StringUtils.isEmpty(expenseTransactionlist.get(i).getDescription())
														||StringUtils.isEmpty(expenseTransactionlist.get(i).getAmount())){
												     fieldName=CommonValidate.findComponentInRoot("accounts").getClientId(fc);
												     fc.addMessage(fieldName, new FacesMessage("Please enter the first row value"));
												     valid=false;
												}
											}
										}
										else if (!expenseTransactionlist.get(i).getToAccount().equals("select")) {
											if(aTransaction.getCountry().equalsIgnoreCase("India")){
												if(aTransaction.getTaxType().equalsIgnoreCase("")|| aTransaction.getTaxType().equalsIgnoreCase("Out of scope of Tax")){
													if(expenseTransactionlist.get(i).getToAccount().equals("select")||StringUtils.isEmpty(expenseTransactionlist.get(i).getDescription())
															||StringUtils.isEmpty(expenseTransactionlist.get(i).getAmount())){
													     fieldName=CommonValidate.findComponentInRoot("accounts").getClientId(fc);
													     fc.addMessage(fieldName, new FacesMessage("Please fill all field in the row"));
													     valid=false;
													}
												}else{
													if(expenseTransactionlist.get(i).getToAccount().equals("select")||StringUtils.isEmpty(expenseTransactionlist.get(i).getDescription())
															||StringUtils.isEmpty(expenseTransactionlist.get(i).getAmount()) || StringUtils.isEmpty(expenseTransactionlist.get(i).getTaxes())){
													     fieldName=CommonValidate.findComponentInRoot("accounts").getClientId(fc);
													     fc.addMessage(fieldName, new FacesMessage("Please fill all field in the row"));
													     valid=false;
													}
												}
											}else{
												if(expenseTransactionlist.get(i).getToAccount().equals("select")||StringUtils.isEmpty(expenseTransactionlist.get(i).getDescription())
														||StringUtils.isEmpty(expenseTransactionlist.get(i).getAmount())){
												     fieldName=CommonValidate.findComponentInRoot("accounts").getClientId(fc);
												     fc.addMessage(fieldName, new FacesMessage("Please fill all field in the row"));
												     valid=false;
												}
											}
										}
										else{
											System.out.println("inside else condition");
										}
										
									} 
							 }
							
							if (aTransaction.getTransactionType().equalsIgnoreCase("Bill")){
								if(!aTransaction.getClientID().equals("CNT0000021")){
									if(StringUtils.isEmpty(aTransaction.getTerms())){
										fieldName=CommonValidate.findComponentInRoot("terms").getClientId(fc);
										fc.addMessage(fieldName, new FacesMessage("Please select Terms"));
										valid=false;
										System.out.println("terms------------------"+valid);
									}
								}
								if(aTransaction.getClientID().equals("CNT0000021")){
									if(StringUtils.isEmpty(aTransaction.getCode())){
										fieldName=CommonValidate.findComponentInRoot("code").getClientId(fc);
										fc.addMessage(fieldName, new FacesMessage("Please select the Code"));
										valid=false;
										System.out.println("code------------------"+valid);
									}
								}
						}
							if (aTransaction.getTransactionType().equalsIgnoreCase("Expense")){
							if(StringUtils.isEmpty(aTransaction.getPaymentMode())){
							     fieldName=CommonValidate.findComponentInRoot("paymentmode").getClientId(fc);
							     fc.addMessage(fieldName, new FacesMessage("Please select Payment Method"));
							     valid=false;
							}
						}
					}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return valid;
				}
	/*public void toAccountValueChange(ValueChangeEvent vc){
		logger.info("[toAccountValueChange()] --------------- Inside toAccountValueChange() method() ------------------------");
		List<String> templist=null;
		try {
			templist=new ArrayList<String>();
			List<String> list=Arrays.asList("Accounts Receivable (A/R)","Accounts Payable (A/P)");
			String clientID = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			if (vc.getNewValue().toString().equalsIgnoreCase("addnew")) {
				RequestContext.getCurrentInstance().execute("PF('addAccount').show()");
				accountsDatabean.setStatus("Category Type");
				controller.getAccountTypes(clientID,accountsDatabean);
				for (int i = 0; i < accountsDatabean.getAccountType().size(); i++) {
					if(!list.contains(accountsDatabean.getAccountType().get(i)));{
						templist.add(accountsDatabean.getAccountType().get(i));
					}
				}
				aTransaction.setAccountTypes(templist);
			} 
		} catch (Exception e) {
			logger.warn("-------------------Inside Exception-------------------------"+e.getMessage());
		}
	}*/
				
	//Changes Code for Account Expense Transaction by Prema
				
	public void totalValue(ValueChangeEvent vc) throws ParseException {
		logger.info("[totalValue()] --------------- Inside totalValue() method() ------------------------");
		System.out.println("totalValue-----------------");
		String serialNo="";String rate="";String id="";
		try{
			rate=vc.getNewValue().toString();
			serialNo = vc.getComponent().getAttributes().get("serial").toString();
			if(aTransaction.getEditStatus().equalsIgnoreCase("Edit")){
				id=vc.getComponent().getAttributes().get("pid").toString();
			}
			if(!rate.equalsIgnoreCase("")){
				ATransaction atrans=new ATransaction();
				atrans.setToAccount(expenseTransactionlist.get(Integer.parseInt(serialNo) - 1).getToAccount());
				atrans.setProductName(expenseTransactionlist.get(Integer.parseInt(serialNo) - 1).getToAccount());
				atrans.setDescription(expenseTransactionlist.get(Integer.parseInt(serialNo) - 1).getDescription());
				atrans.setSerialNo(serialNo);
				atrans.setAmount(vc.getNewValue().toString());
				if(aTransaction.getEditStatus().equalsIgnoreCase("Edit")){
					atrans.setEditBeforeTaxAmount(expenseTransactionlist.get(Integer.parseInt(serialNo) - 1).getEditBeforeTaxAmount());
					atrans.setPaymentId(Integer.parseInt(id));
				}
				atrans.setTaxes(expenseTransactionlist.get(Integer.parseInt(serialNo) - 1).getTaxes());
				if(!atrans.getTaxes().equalsIgnoreCase("")){
					subtaxvalueChange(atrans,aTransaction);
				}
				expenseTransactionlist.set((Integer.parseInt(serialNo)-1),atrans);
				edittotalamountCalculation(expenseTransactionlist,aTransaction);
				subcurrencyTypeValueChange(aTransaction.getCurrencyType());
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("---------------Inside Exception----------------"+e.getMessage());
		}finally{
			serialNo="";rate="";id="";
		}
	}
	
	public void expenseUpdate(){
		logger.info("[expenseUpdate()]-----------------------Inside expenseUpdate() in MB Calling----------------------------");
		String clientID = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
		ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		controller = (DemoController) ctx.getBean("controller");
		String status="";
		try {
			edittotalamountCalculation(aTransaction.getExpenseTransactionlist(),aTransaction);
			subcurrencyTypeValueChange(aTransaction.getCurrencyType());
			if (expensevalidation(true)) {
				status=controller.expenseUpdate(aTransaction,clientID);
				if (status.equalsIgnoreCase("Success")) {
					RequestContext.getCurrentInstance().execute("PF('confirm').show()");
					aTransaction.setExpenseTransactionDataList(new ArrayList<ATransaction>());
					aTransaction.setExpenseaccPaymentDataList(new ArrayList<ATransaction>());
					controller.expanseDetailView(clientID,aTransaction);
				}
			}
		} catch (Exception e) {
			logger.warn("--------------------Inside Exception--------------------------------"+e.getMessage());
		}
	}
				
	public void expensesDataTable(){
		logger.info("[expensesDataTable()]-----------------------Inside expensesDataTable() in MB Calling----------------------------");
		String clientID = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
		ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		controller = (DemoController) ctx.getBean("controller");
		try {
			aTransaction.setExpenseTransactionDataList(new ArrayList<ATransaction>());
			aTransaction.setExpenseaccPaymentDataList(new ArrayList<ATransaction>());
			controller.expanseDetailView(clientID,aTransaction);
		} catch (Exception e) {
			logger.warn("--------------------Inside Exception--------------------------------"+e.getMessage());
		}
	}
		
	public void accountExpensesPageclose(){
		logger.info("[accountExpensesPageclose()]-----------------------Inside accountExpensesPageclose() in MB Calling----------------------------");
		RequestContext.getCurrentInstance().closeDialog("accountsExpensesUpdate");
		accountExpensesPage();
	}
	
	public void accountExpensesPageviewclose(){
		logger.info("[accountExpensesPageviewclose()]-----------------------Inside accountExpensesPageviewclose() in MB Calling----------------------------");
		RequestContext.getCurrentInstance().closeDialog("accountsExpensesView");
	}
		
	public List<ChartOfAccount> AccountList() {
		logger.info("[AccountList()]-----------------------Inside AccountList() in MB Calling----------------------------");
		List<ChartOfAccount> accountlist = null;
		try {
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String clientID = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			accountlist=controller.accountlist(clientID);
		} catch (Exception e) {
			logger.warn("-----------------Inside Exception----------------------"+e.getMessage());
		}
		return accountlist;
	}
	
	public String accountExpensesPage() {
		logger.info("[accountExpensesPage()]-----------------------Inside accountExpensesPage() in MB Calling----------------------------");
		aTransaction.setTransactionType("");
		try {
			aTransaction.setGstStatus("Input");
			String clientID = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String clientCountry = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clientCountry").toString();
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			aTransaction.setClientID(clientID);aTransaction.setCountry(clientCountry);
			aTransaction.setExpenseTransactionDataList(new ArrayList<ATransaction>());
			aTransaction.setExpenseaccPaymentDataList(new ArrayList<ATransaction>());
			controller.expanseDetailView(clientID,aTransaction);
		} catch (Exception e) {
			logger.warn("-----------------Inside Exception----------------------"+e.getMessage());
		}
		return "accountsExpensestransactionform";
	}

	public String AddTransaction(){
		logger.info("[AddTransaction()]------------------------Inside AddTransaction() in MB Calling--------------------------");
		List<String> templist=null;
		clearAllValue();
		try {
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
			String clientCountry=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clientCountry").toString();
			if(clientCountry.equalsIgnoreCase("India")){
				aTransaction.setTaxType("Exclusive of Tax");
			}else{
				aTransaction.setTaxType("");
			}
			templist=new ArrayList<String>();
			List<String> list=Arrays.asList("Accounts Receivable (A/R)","Accounts Payable (A/P)","Output CGST","Output SGST","Output IGST","Input CGST","Input SGST","Input IGST");
			aTransaction.setClientID(clientID);
			aTransaction.setUserID(userID);
			aTransaction.setBaseCurrency(baseCurrency);
			aTransaction.setGstStatus("Input");
			codeList=new ArrayList<String>();
			codeList=controller.getCodelist(aTransaction.getClientID());
			Expensetransactionform();
			List<ChartOfAccount> accountnamelist =AccountList();
			for (int i = 0; i < accountnamelist.size(); i++) {
				if(!list.contains(accountnamelist.get(i).getAccountName())){
					templist.add(accountnamelist.get(i).getAccountName());
				}
			}
			aTransaction.setAccountList(templist);
		} catch (Exception e) {
			logger.warn("-------------------Inside Exception-------------------------"+e.getMessage());
		}
		return "accountsExpensesAdd";
	}
	
	public String Expensetransactionform() {
		logger.info("[Expensetransactionform()] --------------- Inside Expensetransactionform() method() ------------------------");
		try{
			expenseTransactionlist=new ArrayList<ATransaction>();
			for (int i = 0; i < 2; i++) {
				ATransaction atrans=new ATransaction();
				atrans.setTaxes("");
				expenseTransactionlist.add(atrans);
			}
		}catch(Exception e){
			logger.warn("-------------------Inside Exception-------------------------"+e.getMessage());
		}
		return "";
	}
	
	public void accountBalance(ValueChangeEvent vc){
		logger.info("[accountBalance()] --------------- Inside accountBalance() method() ------------------------");
		try {
			String clientID = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			if (vc.getNewValue().toString().equalsIgnoreCase("addnew")) {
				accountsMB.accountsDatabean.setAccount_name("");accountsMB.accountsDatabean.setAccount_type("");accountsMB.accountsDatabean.setValidate("");accountsMB.accountsDatabean.setTaxes("");
				accountsMB.accountsDatabean.setDetailType("");accountsMB.accountsDatabean.setBalance("0");accountsMB.accountsDatabean.setTypes(new ArrayList<String>());
				accountsMB.accountsDatabean.setCurrency("");accountsMB.accountsDatabean.setBaseCurrency(baseCurrency);accountsMB.accountsDatabean.setCurrencyAmount("");
				accountsMB.accountsDatabean.setTaxes("");accountsMB.accountsDatabean.setDate(null);
				RequestContext.getCurrentInstance().execute("PF('addAccount').show()");
			} else if (!vc.getNewValue().toString().equalsIgnoreCase("")) {
				ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				aTransaction.setFromAccount(vc.getNewValue().toString());
				controller.accountbalance(clientID, aTransaction);
			}
		} catch (Exception e) {
			logger.warn("-------------------Inside Exception-------------------------"+e.getMessage());
		}
	}

	public void expenseSave() {
		logger.info("[expenseSave()]---------------------Inside expenseSave() in MB Calling-------------------------------");
		String status="";
		try {
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String clientID = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			aTransaction.setDate(new Date());
			if (expensevalidation(true)) {
				aTransaction.setExpenseTransactionlist(expenseTransactionlist);
				status=controller.expenseSave(aTransaction,clientID);
				if (status.equalsIgnoreCase("Success")) {
					RequestContext.getCurrentInstance().execute("PF('confirmTransaction').show()");
				}
			}
		} catch (Exception e) {
			logger.warn("-------------------Inside Exception---------------------------"+e.getMessage());
		}
	}
	
	public void expenseupdatePage(){
		logger.info("[expenseupdatePage()]---------------------Inside expenseupdatePage() in MB Calling-------------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 550);
		options.put("contentWidth", 1000);
		RequestContext.getCurrentInstance().openDialog("accountsExpensesUpdate",options, null);
	}
	
	public void expenseviewPage(){
		logger.info("[expenseviewPage()]---------------------Inside expenseviewPage() in MB Calling-------------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 550);
		options.put("contentWidth", 1000);
		RequestContext.getCurrentInstance().openDialog("accountsExpensesView",options, null);
	}
	
	public void expensecode(String expenseType){
		logger.info("[expensecode()]---------------------Inside expensecode() in MB Calling-------------------------------");
		List<ExpenseTransaction>transactions=null;String billnumber="";String type="";
		try {
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			transactions=new ArrayList<ExpenseTransaction>();
			transactions=controller.getbillnumber(clientID,expenseType);
			if(expenseType.equals("Bill")){
				type="ETNBI";
			}else if (expenseType.equals("Expense")) {
				type="ETNEX";
			}else if (expenseType.equals("Check")) {
				type="ETNCH";
			}else if (expenseType.equals("Vendor Credit")) {
				type="ETNVC";
			}
			if (transactions.size()>0) {
				String transactionnumber=transactions.get(transactions.size()-1).getBillNumber();
				int listsize=Integer.parseInt(transactionnumber.substring(5))+1;
				if (listsize<=9)billnumber=type+"000000"+listsize; 
				else if(listsize>9 && listsize<=99) billnumber=type+"00000"+listsize;
				else if(listsize>99 && listsize<=999) billnumber=type+"0000"+listsize;
			    else if(listsize>999 && listsize<=9999) billnumber=type+"000"+listsize;
			    else if(listsize>9999 && listsize<=99999) billnumber=type+"00"+listsize;
			    else if(listsize>99999 && listsize<=999999) billnumber=type+"0"+listsize;
			    else billnumber=type+listsize;
			}else{
				billnumber=type+"0000001";
			}
			aTransaction.setRefNo(billnumber);
		}catch (Exception e) {
			logger.warn("-------------------Inside Exception---------------------------"+e.getMessage());
		}
	}
	
	public void makepaymentaccounttypeconfirm(String accountname){
		logger.info("[makepaymentaccounttypeconfirm()]-----------------Inside makepaymentaccounttypeconfirm() in MB Calling-------------------");
		List<ChartOfAccount> accountlist = null;
		try{
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			DemoController controller=(DemoController) ctx.getBean("controller");
			accountlist=new ArrayList<ChartOfAccount>();
			accountlist=controller.accountlist(clientID);
			aTransaction.setFilteredaccountList(new ArrayList<String>());
			if(accountlist.size()>0){
				 for (int i = 0; i < accountlist.size(); i++) {
					if(accountlist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Bank") || 
							accountlist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Credit Card")){
						aTransaction.getFilteredaccountList().add(accountlist.get(i).getAccountName());
					}
				 }
			}
			aTransaction.setFromAccount(accountname);
		}catch(Exception e){
			logger.warn("-----------Inside Exception-----------------"+e.getMessage());
		}
	}
	
	public void ExpenseTrnsType(ValueChangeEvent vc){
		logger.info("[ExpenseTrnsType()]-------------------Inside ExpenseTrnsType() in MB Calling-----------------------");
		try{
		 	aTransaction.setRefNo("");
			aTransaction.setToAccount("");
			aTransaction.setDescription("");
			aTransaction.setAmount("");
			aTransaction.setMailingAddress("");
			aTransaction.setAccountType("");
			aTransaction.setTerms("");
			aTransaction.setPaymentMode("");
			aTransaction.setVendorName("");
			aTransaction.setDuedate(null);
			aTransaction.setDueDate(null);
			aTransaction.setBillDate(null);
			aTransaction.setRefNo("");
			aTransaction.setFromAccount("");
			aTransaction.setToAccount("");
			aTransaction.setAccounts("");
			aTransaction.setPaydate(null);
		 	aTransaction.setAccountBalance("");
		 	totalAmount=0.0;
			expensecode(vc.getNewValue().toString());
			accountresource(vc.getNewValue().toString());
			Expensetransactionform();
		}catch(Exception e){
			logger.warn("----------------Inside Exception-----------------------"+e.getMessage());
		}
	}
	
	public void expenseDetails(){
		logger.info("[expenseDetails()]---------------------Inside expenseDetails() in MB Calling-------------------------------");
		BigDecimal tempamt=BigDecimal.valueOf(0);
		try {
			aTransaction.setExpenseTransactionlist(new ArrayList<ATransaction>());
			aTransaction.setPaymentdataTableList(new ArrayList<ATransaction>());
			for (int i = 0; i < aTransaction.getExpenseTransactionDataList().size(); i++) {
				if(aTransaction.getExpenseTransactionDataList().get(i).getIndex()==aTransaction.getIndex()){
					System.out.println("inside if---");
					aTransaction.setBillDate(aTransaction.getExpenseTransactionDataList().get(i).getDate());
					aTransaction.setAmount(aTransaction.getExpenseTransactionDataList().get(i).getAmount());
					aTransaction.setTransactionType(aTransaction.getExpenseTransactionDataList().get(i).getTransactionType());
					aTransaction.setRefNo(aTransaction.getExpenseTransactionDataList().get(i).getRefNo());
					aTransaction.setVendorName(aTransaction.getExpenseTransactionDataList().get(i).getVendorName());
					aTransaction.setFromAccount(aTransaction.getExpenseTransactionDataList().get(i).getFromAccount());
					aTransaction.setMailingAddress(aTransaction.getExpenseTransactionDataList().get(i).getMailingAddress());
					aTransaction.setPaymentMode(aTransaction.getExpenseTransactionDataList().get(i).getPaymentMode());
					aTransaction.setDueDate(aTransaction.getExpenseTransactionDataList().get(i).getDueDate());
					aTransaction.setTerms(aTransaction.getExpenseTransactionDataList().get(i).getTerms());
					aTransaction.setCurrencyType(aTransaction.getExpenseTransactionDataList().get(i).getCurrencyType());
					aTransaction.setLocation(aTransaction.getExpenseTransactionDataList().get(i).getLocation());
					aTransaction.setTaxType(aTransaction.getExpenseTransactionDataList().get(i).getTaxType());
					aTransaction.setBalAmount(aTransaction.getExpenseTransactionDataList().get(i).getBalAmount());
					aTransaction.setSubTotalAmount(aTransaction.getExpenseTransactionDataList().get(i).getSubTotalAmount());
					if(aTransaction.getExpenseTransactionDataList().get(i).getTransactionType().equals("Vendor Credit")){
						aTransaction.setCurrencyAmount(tempamt.subtract(new BigDecimal(aTransaction.getExpenseTransactionDataList().get(i).getCurrencyAmount())).toString());
					}else{
						aTransaction.setCurrencyAmount(aTransaction.getExpenseTransactionDataList().get(i).getCurrencyAmount());
					}
					aTransaction.setBaseCurrency(aTransaction.getExpenseTransactionDataList().get(i).getBaseCurrency());
					aTransaction.setTotalAmount(aTransaction.getExpenseTransactionDataList().get(i).getTotalAmount());
					aTransaction.getPaymentdataTableList().add(aTransaction);
				}
			}
			for (int i = 0; i < aTransaction.getExpenseaccPaymentDataList().size(); i++) {
				if(aTransaction.getExpenseaccPaymentDataList().get(i).getTransactionID()==aTransaction.getIndex()){
					ATransaction domain=new ATransaction();
					domain.setPaymentId(aTransaction.getExpenseaccPaymentDataList().get(i).getPaymentId());
					domain.setToAccount(aTransaction.getExpenseaccPaymentDataList().get(i).getToAccount());
					domain.setAmount(aTransaction.getExpenseaccPaymentDataList().get(i).getAmount());
					domain.setDescription(aTransaction.getExpenseaccPaymentDataList().get(i).getDescription());
					domain.setBaseCurrency(aTransaction.getExpenseaccPaymentDataList().get(i).getBaseCurrency());
					domain.setTaxes(aTransaction.getExpenseaccPaymentDataList().get(i).getTaxes());
					domain.setGstAmount(aTransaction.getExpenseaccPaymentDataList().get(i).getGstAmount());
					domain.setPercentIGSTFlag(aTransaction.getExpenseaccPaymentDataList().get(i).getPercentIGSTFlag());
					domain.setPercentGSTFlag(aTransaction.getExpenseaccPaymentDataList().get(i).getPercentGSTFlag());
					domain.setCgstType(aTransaction.getExpenseaccPaymentDataList().get(i).getCgstType());
					domain.setCgstAmount(aTransaction.getExpenseaccPaymentDataList().get(i).getCgstAmount());
					domain.setPercentageValue(aTransaction.getExpenseaccPaymentDataList().get(i).getPercentageValue());
					domain.setSgstType(aTransaction.getExpenseaccPaymentDataList().get(i).getSgstType());
					domain.setSgstAmount(aTransaction.getExpenseaccPaymentDataList().get(i).getSgstAmount());
					domain.setGstType(aTransaction.getExpenseaccPaymentDataList().get(i).getGstType());
					domain.setPercentageAmount(aTransaction.getExpenseaccPaymentDataList().get(i).getPercentageAmount());
					aTransaction.getExpenseTransactionlist().add(domain);
				}
			}
		} catch (Exception e) {
			logger.warn("-------------------Inside Exception---------------------------"+e.getMessage());
		}
	}
	
	public void expensetransactionview(){
		logger.info("[expensetransactionview()]---------------------Inside expensetransactionview() in MB Calling-------------------------------");
		try {
			expenseDetails();
			expenseviewPage();
		} catch (Exception e) {
			logger.warn("-------------------Inside Exception---------------------------"+e.getMessage());
		}
	}
	
	public void rowselect() {
		logger.info("[rowselect()]---------------------Inside rowselect() in MB Calling-------------------------------");
		totalAmount=0.0;List<String> templist= null;List<ChartOfAccount> accountnamelist=null;String amount="";
		try {
			aTransaction.setRemoveList(new ArrayList<Integer>());
			aTransaction.setEditStatus("Edit");
			templist=new ArrayList<String>();accountnamelist=new ArrayList<ChartOfAccount>();
			String clientID = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			aTransaction.setExpenseTransactionlist(new ArrayList<ATransaction>());
			setexpenseTransactionlist(new ArrayList<ATransaction>());
			accountnamelist=controller.accountlist(clientID);
			List<String> list=Arrays.asList("Accounts Receivable (A/R)","Accounts Payable (A/P)","Output CGST","Output SGST","Output IGST","Input CGST","Input SGST","Input IGST");
			for (int i = 0; i < accountnamelist.size(); i++) {
				if(!list.contains(accountnamelist.get(i).getAccountName())){
					templist.add(accountnamelist.get(i).getAccountName());
				}
			}
			aTransaction.setAccountList(templist);			
			accountresource(aTransaction.getTransactionType());			
			/*for (int j = 0; j < aTransaction.getExpenseTransactionlist().size(); j++) {
				aTransaction.getExpenseTransactionlist().get(j).setEditTransAmount(aTransaction.getExpenseTransactionlist().get(j).getAmount());
				totalAmount=new BigDecimal(totalAmount).add(new BigDecimal(aTransaction.getExpenseTransactionlist().get(j).getAmount())).doubleValue();	
			}
			aTransaction.setEditTransAmount(String.valueOf(totalAmount));*/
			expenseDetails();
			setexpenseTransactionlist(aTransaction.getExpenseTransactionlist());
			expenseupdatePage();
			for (int i = 0; i < aTransaction.getExpenseTransactionlist().size(); i++) {
				amount=getexceptGSTAmount(aTransaction,aTransaction.getExpenseTransactionlist().get(i));
				aTransaction.getExpenseTransactionlist().get(i).setEditBeforeTaxAmount(amount);
			}
			aTransaction.setEditTransAmount(aTransaction.getCurrencyAmount());
		} catch (Exception e) {
			logger.warn("-------------------Inside Exception---------------------------"+e.getMessage());
		}
	}
	
	public String getexceptGSTAmount(ATransaction aTransaction,ATransaction atrans){
		logger.info("[getexceptGSTAmount()]----------------------Inside getexceptGSTAmount() in DaoImpln Calling----------------------------");
		String amount="";
		try{
				if(aTransaction.getTaxType().equals("Out of scope of Tax") || aTransaction.getTaxType().equals("")){
					if(aTransaction.getCurrencyType().equals(aTransaction.getBaseCurrency())){
						amount=atrans.getAmount();
					}else{
						BigDecimal currgstamt=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), atrans.getAmount());
						amount=String.valueOf(currgstamt);
					}
				}else{
					if(atrans.getTaxes().equals("Exempt IGST") || atrans.getTaxes().equals("Exempt GST") || atrans.getTaxes().equals("Out of Scope") || atrans.getTaxes().equals("")){
						if(aTransaction.getCurrencyType().equals(aTransaction.getBaseCurrency())){
							amount=atrans.getAmount();
						}else{
							BigDecimal currgstamt=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), atrans.getAmount());
							amount=String.valueOf(currgstamt);
						}
					}else{
						if(aTransaction.getCurrencyType().equals(aTransaction.getBaseCurrency())){
							amount=atrans.getGstAmount();
						}else{
							BigDecimal currgstamt=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), atrans.getGstAmount());
							amount=String.valueOf(currgstamt);
						}
					}
				}
		}catch(Exception e){
			logger.warn("-------------------Inside Exception----------------------------"+e.getMessage());
		}
		return amount;
	}
	
	public void accountRegclose(String transactionType,String accountName){
		logger.info("[accountRegclose()]---------------------Inside accountRegclose() in MB Calling-------------------------------");
		List<String> templist = null;
		try {
			templist = new ArrayList<String>();
			List<String> list=Arrays.asList("Accounts Receivable (A/R)","Accounts Payable (A/P)","Output CGST","Output IGST","Output SGST","Input CGST","Input SGST","Input IGST");
			List<ChartOfAccount> accountnamelist =AccountList();
			for (int i = 0; i < accountnamelist.size(); i++) {
				if(!list.contains(accountnamelist.get(i).getAccountName())){
					templist.add(accountnamelist.get(i).getAccountName());
				}
			}
			aTransaction.setAccountList(templist);
			templist=new ArrayList<String>();
			if(transactionType.equalsIgnoreCase("Expense")) {
				for (int i = 0; i < accountnamelist.size(); i++) {
					if (accountnamelist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Bank")|| accountnamelist.get(i).getAccountType()
									.getCategoryType().equalsIgnoreCase("Credit Card")) {
						templist.add(accountnamelist.get(i).getAccountName());
					}
				}
			}else if (transactionType.equalsIgnoreCase("Check")) {
				for (int i = 0; i < accountnamelist.size(); i++) {
					if (accountnamelist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Bank")) {
						templist.add(accountnamelist.get(i).getAccountName());
					}
				}
			} 	
			aTransaction.setFilteredaccountList(templist);
			aTransaction.setFromAccount(accountName);
		} catch (Exception e) {
			logger.warn("-------------------Inside Exception---------------------------"+e.getMessage());
		}
	}
	
	private void accountresource(String transactionType) {
		logger.info("[accountresource()]---------------------Inside accountresource() in MB Calling-------------------------------");
		aTransaction.setAllList(null);aTransaction.setVendorlist(null);aTransaction.setFilteredaccountList(null);
		List<String> templist= null;
		try{
			 templist= new ArrayList<String>();
			 if(transactionType.equalsIgnoreCase("Bill") || transactionType.equalsIgnoreCase("Purchase Invoice")){
				 expenseResource("Vendor");
			 }
			 else if(transactionType.equalsIgnoreCase("Expense")){
				 expenseResource("All");
				 List<ChartOfAccount> accountnamelist =AccountList();
				 for (int i = 0; i < accountnamelist.size(); i++) {
					 if(accountnamelist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Bank") || accountnamelist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Credit Card")){
						 templist.add(accountnamelist.get(i).getAccountName());
					 }
				 }
				 aTransaction.setFilteredaccountList(templist);
			}
			else if(transactionType.equalsIgnoreCase("Check")){
				 expenseResource("All");
				 List<ChartOfAccount> accountnamelist =AccountList();
				 for (int i = 0; i < accountnamelist.size(); i++) {
					 if(accountnamelist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Bank")){
						 templist.add(accountnamelist.get(i).getAccountName());
					 }
				 }
				 aTransaction.setFilteredaccountList(templist);
			}
			else if(transactionType.equalsIgnoreCase("Vendor Credit")){
				expenseResource("Vendor");
			}
		}
		catch(Exception e){
			logger.warn("-------------------Inside Exception---------------------------"+e.getMessage());
		}
	}
	
	public String payment() {
		logger.info("[payment()]---------------------Inside payment() in MB Calling-------------------------------");
		totalAmount=0.0;List<String> templist= null;
		try {
			templist= new ArrayList<String>();
			String clientID = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			aTransaction.setBaseCurrency(baseCurrency);
			List<ChartOfAccount> accountnamelist = AccountList();
			for (int i = 0; i < accountnamelist.size(); i++) {
				if(accountnamelist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Bank") || accountnamelist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Credit Card")){
					templist.add(accountnamelist.get(i).getAccountName());
				}
			}
			aTransaction.setFilteredaccountList(templist);
			aTransaction.setPaymentdataTableList(new ArrayList<ATransaction>());
			expenseDetails();
			//controller.expanseDetailView(clientID,aTransaction);
			aTransaction.setPaydate(now);
			aTransaction.setFromAccount("");
			aTransaction.setCurrencyType("");
			aTransaction.setCurrencyAmount("");
			aTransaction.setReceiveAmount("");
		} catch (Exception e) {
			logger.warn("-------------------Inside Exception---------------------------"+e.getMessage());
		}finally{
			templist=null;
		}
		return "accountsExpensesPayment";
	}
	
	public void balanceChange(ValueChangeEvent vc) {
		logger.info("[balanceChange()]---------------------Inside balanceChange() in MB Calling-------------------------------");
		String fieldName;FacesContext fc = FacesContext.getCurrentInstance();Double paid=0.0;
		try{
			if(!vc.getNewValue().toString().equalsIgnoreCase("")){
				try{
					if(!aTransaction.getPaymentdataTableList().get(0).getPayAmount().equalsIgnoreCase("")||!aTransaction.getPaymentdataTableList().get(0).getPayAmount().equalsIgnoreCase(null)){
						if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
							paid=Double.valueOf(vc.getNewValue().toString());
						}else{
							BigDecimal currAmount=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),vc.getNewValue().toString());
							paid=Double.valueOf(currAmount.toString());
						}
						Double balance=Double.valueOf(vc.getComponent().getAttributes().get("initialbalance").toString().trim());
					    if(paid>balance){
					    	fieldName = CommonValidate.findComponentInRoot("datatable").getClientId(fc);
					        fc.addMessage(fieldName, new FacesMessage("The Payment amount is Higher than the Open Balance"));
					    }else{
					    	aTransaction.setReceiveAmount(vc.getNewValue().toString());
					    }
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
					aTransaction.setCurrencyAmount(aTransaction.getReceiveAmount());
				}else{
					BigDecimal currAmount=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),aTransaction.getReceiveAmount());
					aTransaction.setCurrencyAmount(String.valueOf(currAmount));
				}
			}
		}catch(Exception e){
			logger.warn("----------------Inside Exception-------------------"+e.getMessage());
		}
	}
	
	private boolean expenseMakePaymentvalidation(boolean valid) {
		logger.info("[expenseMakePaymentvalidation()]---------------------Inside expenseMakePaymentvalidation() in MB Calling-------------------------------");
		String fieldName;FacesContext fc = FacesContext.getCurrentInstance();
		try {
		    if(StringUtils.isEmpty(aTransaction.getFromAccount()) || aTransaction.getFromAccount().equalsIgnoreCase("add")){
		    	fieldName=CommonValidate.findComponentInRoot("depositacnt").getClientId(fc);
		        fc.addMessage(fieldName, new FacesMessage("Please select the Deposit Account"));
		        valid=false;
		    }
		    if(StringUtils.isEmpty(aTransaction.getCurrencyType())){
		    	fieldName=CommonValidate.findComponentInRoot("paycurr").getClientId(fc);
		        fc.addMessage(fieldName, new FacesMessage("Please select the Currency Type"));
		        valid=false;
		    }
		    for (int i = 0; i < aTransaction.getPaymentdataTableList().size(); i++) {
		    	if(StringUtils.isEmpty(aTransaction.getPaymentdataTableList().get(i).getPayAmount())){
		    		fieldName = CommonValidate.findComponentInRoot("datatable").getClientId(fc);
	    			fc.addMessage(fieldName, new FacesMessage("Please Enter the Payment amount."));
	    			valid=false;
		    	}else{
		    		Double paid=Double.valueOf(aTransaction.getCurrencyAmount());
		    		Double balance=Double.valueOf(aTransaction.getPaymentdataTableList().get(i).getBalAmount());
		    		 if(paid>balance){
		 		    	fieldName = CommonValidate.findComponentInRoot("datatable").getClientId(fc);
		 		        fc.addMessage(fieldName, new FacesMessage("The Payment Amount is higher than the Opening Balance"));
		 		        valid=false;
		 		    }
		    	}
		    }
		}catch (Exception e) {
			logger.warn("----------------Inside Exception-------------------"+e.getMessage());
		}
		return valid;
	}
	 
	public void expenseMakePayment() {
		logger.info("[expenseMakePayment()]---------------------Inside expenseMakePayment() in MB Calling-------------------------------");
		String status="fail";
		try {
			String clientID = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
		    if(expenseMakePaymentvalidation(true)){
		    	status=controller.expenseMakePayment(clientID,aTransaction);
		    	if(status.equalsIgnoreCase("success")){
		    		RequestContext.getCurrentInstance().execute("PF('payconfirm').show();");
		    		accountExpensesPage();
		    	}
		    }
		} catch (Exception e) {
			logger.warn("----------------Inside Exception-------------------"+e.getMessage());
		}
	}
	
	public void expensepanelcloseListener(CloseEvent event) throws IOException{
		logger.info("[expensepanelcloseListener()]-------------------------Inside expensepanelcloseListener() in MB Calling-------------------------------");
		FacesContext.getCurrentInstance().getExternalContext().redirect("/inacsys/pages/xhtml/accountsExpensestransactionform.xhtml");
		accountExpensesPage();
	}
	//Code for Account Sales Transaction by Prema

	public String salesaccountstransactionpage() {
		logger.info("--------------------------------------- Inside salesaccountstransactionpage() method ----------------------------------");
		try{
			aTransaction.setGstStatus("Output");
			setFilterList1(null);
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
			String clientCountry=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clientCountry").toString();
			aTransaction.setCountry(clientCountry);
			accountsDatabean.setBaseCurrency(baseCurrency);
			salesTransactiondetails=controller.getsalestransactiontableview(clientID,aTransaction);
		}catch(Exception e){
			logger.warn("----------------Inside Exception-------------------"+e.getMessage());
		}
		return "salesaccountsTransactionForm";
	}
	
	public void addRow(){
		logger.info("--------------------------------------- Inside addRow() method ----------------------------------");
		for (int i = 0; i < 1; i++) {
			ATransaction atrans=new ATransaction();
			atrans.setSerialNo(String.valueOf(productdetails.size()+1));
			atrans.setProductName("select");
			atrans.setTaxes("");
			productdetails.add(atrans);
		}
	}
	
	public void taxtypeValueChange(ValueChangeEvent vc){
		logger.info("--------------------------------------- Inside taxtypeValueChange() method ----------------------------------");
		productdetails=new ArrayList<ATransaction>();
		for (int i = 0; i < 2; i++) {
			ATransaction atrans=new ATransaction();
			atrans.setSerialNo(String.valueOf(i+1));
			atrans.setTaxes("");
			productdetails.add(atrans);
		}
		aTransaction.setTotalAmount("0");aTransaction.setTransamount("0");aTransaction.setSubTotalAmount("0");aTransaction.setCurrencyAmount("0");
	}
	
	public void edittaxtypeValueChange(ValueChangeEvent vc){
		logger.info("--------------------------------------- Inside taxtypeValueChange() method ----------------------------------");
		aTransaction.setProductList(new ArrayList<ATransaction>());
		aTransaction.setProductList(productdetails);
		productdetails=new ArrayList<ATransaction>();
		for (int i = 0; i < 2; i++) {
			ATransaction atrans=new ATransaction();
			atrans.setSerialNo(String.valueOf(i+1));
			atrans.setTaxes("");
			atrans.setSalesPaymentID(aTransaction.getProductList().get(i).getSalesPaymentID());
			productdetails.add(atrans);
		}
		aTransaction.setTotalAmount("0");aTransaction.setTransamount("0");aTransaction.setSubTotalAmount("0");aTransaction.setCurrencyAmount("0");
	}
	
	public void expensetaxtypeValueChange(ValueChangeEvent vc){
		logger.info("--------------------------------------- Inside taxtypeValueChange() method ----------------------------------");
		expenseTransactionlist=new ArrayList<ATransaction>();
		for (int i = 0; i < 2; i++) {
			ATransaction atrans=new ATransaction();
			atrans.setTaxes("");
			expenseTransactionlist.add(atrans);
		}
		aTransaction.setTotalAmount("0");aTransaction.setTransamount("0");aTransaction.setSubTotalAmount("0");aTransaction.setCurrencyAmount("0");
	}
	public void reset(){
		logger.info("[reset()]---------------------Inside reset() in MB Calling-----------------------------");
		try{
			String clientCountry=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clientCountry").toString();
			if(clientCountry.equalsIgnoreCase("India")){
				aTransaction.setTaxType("Exclusive of Tax");
			}else{
				aTransaction.setTaxType("");
			}
			aTransaction.setCustomerName("");aTransaction.setCurrencyType("");aTransaction.setCustomerBillingAddress("");aTransaction.setTotalAmount("0.00");
			aTransaction.setCustomerEmailId("");aTransaction.setDescription("");aTransaction.setTransamount("0.00");aTransaction.setReceiveAmount("0.00");
			aTransaction.setPaymentMode("");aTransaction.setAccountType("");aTransaction.setCreditmemoNo("");aTransaction.setCurrencyAmount("0");
			aTransaction.setTerms("");aTransaction.setCreditmemoBalance("");aTransaction.setBillDate(null);aTransaction.setDueDate(null);
			paymentDataTableFlag="none";aTransaction.setCurrency("");aTransaction.setLocation("");aTransaction.setCurrencybeforeTaxAmount("");
			aTransaction.setCurrencytaxAmount("");aTransaction.setBeforeTaxAmount("");aTransaction.setGstType("");aTransaction.setSgstType("");aTransaction.setCgstType("");
			aTransaction.setCurrencyIGSTAmount("");aTransaction.setCurrencyCGSTAmount("");aTransaction.setCurrencySGSTAmount("");aTransaction.setEditStatus("");
			productdetails=new ArrayList<ATransaction>();
			for (int i = 0; i < 2; i++) {
				ATransaction atrans=new ATransaction();
				atrans.setSerialNo(String.valueOf(i+1));
				atrans.setTaxes("");
				productdetails.add(atrans);
			}
		}catch(Exception e){
			logger.warn("-------------------Inside Exception----------------------"+e.getMessage());
		}
	}
	public String salestransactionform() {
		logger.info("[salestransactionform()] --------------- Inside salestransactionform() method() ------------------------");
		DemoController controller = null;Date date=new Date(); 
		FacesContext fc = FacesContext.getCurrentInstance();RequestContext.getCurrentInstance().execute("PF('statusDialog').hide();");
		List<ChartOfAccount> accountlist = null;paymentDataTableFlag="none";
		try{
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			aTransaction.setBaseCurrency(baseCurrency);
			reset();
			aTransaction.setGstStatus("Output");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			accounttypeList=new ArrayList<String>();
			controller.getsalestransactioncustprod(clientID,aTransaction);
			Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
			String name = params.get("menus");
			setTransactionType(name);
			aTransaction.setTransStatus("");
			setPaymentStatus("payment");
			if(transactionType.equalsIgnoreCase("Invoice")){
				aTransaction.setTransamount("0.00");aTransaction.setBillDate(null);aTransaction.setDueDate(null);aTransaction.setSubTotalAmount("0.00");aTransaction.setCurrencyAmount("0");
				aTransaction.setTotalAmount("0.00");
			}else if(transactionType.equalsIgnoreCase("Estimate") || transactionType.equalsIgnoreCase("Credit Memo") || transactionType.equalsIgnoreCase("Delayed Charge")
					|| transactionType.equalsIgnoreCase("Time Activity")){
				aTransaction.setBillDate(date);aTransaction.setDueDate(null);
				aTransaction.setTransamount("0.00");aTransaction.setBalAmount("0");
			}else if(transactionType.equalsIgnoreCase("Payment") || transactionType.equalsIgnoreCase("Sales Receipt")){
				 aTransaction.setBillDate(date);
				 if(transactionType.equalsIgnoreCase("Sales Receipt")){
					 aTransaction.setDueDate(date);
				 }
				 accountlist=controller.accountlist(clientID);
				 if(accountlist.size()>0){
					 for (int i = 0; i < accountlist.size(); i++) {
						if(accountlist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Bank") || 
								accountlist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Other Current Assets")){
							accounttypeList.add(accountlist.get(i).getAccountName());
						}
					 }
				 }
			}
		}catch(Exception e){
			logger.warn("---------------Inside Exception----------------"+e.getMessage());
		}finally{
			accountlist=null;controller = null;
		}
		return "salesTransactionForm";
	}
	
	public void customerValueChange(ValueChangeEvent vc){
		logger.info("[customerValueChange()] --------------- Inside customerValueChange() method() ------------------------");
		String customername="";
		try{
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			customername=vc.getNewValue().toString();
			aTransaction.setCustomerName(customername);
			controller.getcustomerdetails(clientID,aTransaction);
			if(transactionType.equalsIgnoreCase("Payment")){
				if(!aTransaction.getCustomerName().equalsIgnoreCase("")){
					paymentDataTableFlag="1";String paymentstatus="payment";
					paymentdatatableList=controller.getpaymentdetails(clientID,paymentstatus,aTransaction);
				}
			}else{
				paymentDataTableFlag="none";
			}
		}catch(Exception e){
			logger.warn("---------------Inside Exception----------------"+e.getMessage());
		}finally{
			customername="";controller=null;
		}
	}
	public void termsValueChange(ValueChangeEvent vc){
		logger.info("[termsValueChange()] --------------- Inside termsValueChange() method() ------------------------");
		String terms="";Date date=new Date();String duedate=""; 
		Calendar cal = Calendar.getInstance();
		try{
			aTransaction.setTerms("Net 30");
			terms=vc.getNewValue().toString();
			if(terms.equalsIgnoreCase("select")){
				aTransaction.setBillDate(null);aTransaction.setDueDate(null);
			}else if(terms.equalsIgnoreCase("Due on receipt")){
				aTransaction.setBillDate(date);aTransaction.setDueDate(date);
			}else if(terms.equalsIgnoreCase("Net 15")){
				aTransaction.setBillDate(date);
				cal.add(Calendar.DATE, 15);
				duedate=ft.format(cal.getTime());
				aTransaction.setDueDate(ft.parse(duedate));
			}else if(terms.equalsIgnoreCase("Net 30")){
				aTransaction.setBillDate(date);
				cal.add(Calendar.DATE, 30);
				duedate=ft.format(cal.getTime());
				aTransaction.setDueDate(ft.parse(duedate));
			}else if(terms.equalsIgnoreCase("Net 60")){
				aTransaction.setBillDate(date);
				cal.add(Calendar.DATE, 60);
				duedate=ft.format(cal.getTime());
				aTransaction.setDueDate(ft.parse(duedate));
			}
		}catch(Exception e){
			logger.warn("---------------Inside Exception----------------"+e.getMessage());
		}finally{
			terms="";date=null;duedate=""; 
		}
	}
	public void productValueChange(ValueChangeEvent vc){
		logger.info("[productValueChange()] --------------- Inside productValueChange() method() ------------------------");
		String serialNo="";String productname="";String id="";
		try{
			productname=vc.getNewValue().toString();
			serialNo = vc.getComponent().getAttributes().get("serial").toString();
			System.out.println("edit status"+aTransaction.getEditStatus());
			if(aTransaction.getEditStatus().equalsIgnoreCase("Edit")){
				id=vc.getComponent().getAttributes().get("pid").toString();
			}
			if(productname.equalsIgnoreCase("select")){
				ATransaction atrans=new ATransaction();
				atrans.setProductName("");
				atrans.setSerialNo(serialNo);
				atrans.setQuantity("");
				atrans.setRate("");
				atrans.setAmount("");
				atrans.setTaxes("");
				if(aTransaction.getEditStatus().equalsIgnoreCase("Edit")){
					atrans.setSalesPaymentID(Integer.parseInt(id));
				}
				productdetails.set((Integer.parseInt(serialNo)-1),atrans);
			}else{
				ATransaction atrans=new ATransaction();
				atrans.setProductName(productname);
				atrans.setSerialNo(serialNo);
				atrans.setQuantity("1");
				atrans.setRate("0.00");
				atrans.setAmount("0.00");
				atrans.setTaxes("");
				if(aTransaction.getEditStatus().equalsIgnoreCase("Edit")){
					atrans.setSalesPaymentID(Integer.parseInt(id));
				}
				productdetails.set((Integer.parseInt(serialNo)-1),atrans);
			}
			totalamountCalculation(productdetails,aTransaction);
			subcurrencyTypeValueChange(aTransaction.getCurrencyType());
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("---------------Inside Exception----------------"+e.getMessage());
		}finally{
			serialNo="";productname="";id="";
		}
	}
	
	public void subtaxvalueChange(ATransaction atrans,ATransaction aTransaction){
		String taxrate="";BigDecimal hundredamount=BigDecimal.valueOf(100);
		DecimalFormat df = null;
		try{
			df = new DecimalFormat(".##");
			if(atrans.getTaxes().equalsIgnoreCase("0% IGST") || atrans.getTaxes().equalsIgnoreCase("5% IGST")
					|| atrans.getTaxes().equalsIgnoreCase("12% IGST") || atrans.getTaxes().equalsIgnoreCase("18% IGST")
					|| atrans.getTaxes().equalsIgnoreCase("28% IGST")){
				taxrate=atrans.getTaxes().split("%")[0];
				atrans.setPercentageValue(taxrate+"%");
				if(aTransaction.getTaxType().equalsIgnoreCase("Exclusive of Tax")){
					BigDecimal gstamount=new BigDecimal(taxrate).divide(hundredamount);
					atrans.setPercentageAmount(new BigDecimal(atrans.getAmount()).multiply(gstamount).toString());
					atrans.setGstAmount(atrans.getAmount());
				}
				if(aTransaction.getTaxType().equalsIgnoreCase("Inclusive of Tax")){
					Double tempamount=(Double.valueOf(taxrate)/Double.valueOf(100))+Double.valueOf(1);
					Double tempamout1=(double) Math.round((Double.valueOf(atrans.getAmount())/tempamount) * 100) / 100;
					Double tempamount2=Double.valueOf(atrans.getAmount())-tempamout1;
					atrans.setPercentageAmount(String.valueOf(tempamount2));
					atrans.setGstAmount(String.valueOf(tempamout1));
				}
				atrans.setPercentIGSTFlag("1");atrans.setGstType("IGST");
			}
			if(atrans.getTaxes().equalsIgnoreCase("0% GST") || atrans.getTaxes().equalsIgnoreCase("5% GST")
					|| atrans.getTaxes().equalsIgnoreCase("12% GST") || atrans.getTaxes().equalsIgnoreCase("18% GST")
					|| atrans.getTaxes().equalsIgnoreCase("28% GST")){
				BigDecimal per=new BigDecimal(atrans.getTaxes().split("%")[0]).divide(BigDecimal.valueOf(2));
				atrans.setPercentGSTFlag("1");atrans.setPercentageValue(per+"%");
				if(aTransaction.getTaxType().equalsIgnoreCase("Exclusive of Tax")){
					BigDecimal gstamount=per.divide(hundredamount);
					atrans.setSgstAmount(new BigDecimal(atrans.getAmount()).multiply(gstamount).toString());
					atrans.setCgstAmount(new BigDecimal(atrans.getAmount()).multiply(gstamount).toString());
					atrans.setGstAmount(atrans.getAmount());
				}
				if(aTransaction.getTaxType().equalsIgnoreCase("Inclusive of Tax")){
					taxrate=atrans.getTaxes().split("%")[0];
					Double tempamount=(Double.valueOf(taxrate)/Double.valueOf(100))+Double.valueOf(1);
					Double tempamout1=(double) Math.round((Double.valueOf(atrans.getAmount())/tempamount) * 100) / 100;
					Double tempamount2=Double.valueOf(atrans.getAmount())-tempamout1;
					Double cgstamt=tempamount2/Double.valueOf(2);
					Double sgstamt=tempamount2-cgstamt;
					atrans.setSgstAmount(String.valueOf((double) Math.round(sgstamt * 100) / 100));
					df.setRoundingMode(RoundingMode.DOWN);
					atrans.setCgstAmount(String.valueOf(df.format(cgstamt)));
					atrans.setGstAmount(String.valueOf(tempamout1));
				}
				atrans.setCgstType("CGST");atrans.setSgstType("SGST");
			}
			System.out.println("sgst amount"+atrans.getSgstAmount());
			System.out.println("cgst amount"+atrans.getCgstAmount());
			System.out.println("igst amount"+atrans.getPercentageAmount());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void quantityValueChange(ValueChangeEvent vc){
		logger.info("[quantityValueChange()] --------------- Inside quantityValueChange() method() ------------------------");
		String serialNo="";String quantity="";String rates="";String id="";
		try{
			quantity=vc.getNewValue().toString();
			serialNo = vc.getComponent().getAttributes().get("serial").toString();
			rates=vc.getComponent().getAttributes().get("rate").toString();
			if(aTransaction.getEditStatus().equalsIgnoreCase("Edit")){
				id=vc.getComponent().getAttributes().get("pid").toString();
			}
			if(!quantity.equalsIgnoreCase("")){
				ATransaction atrans=new ATransaction();
				atrans.setProductName(productdetails.get(Integer.parseInt(serialNo) - 1).getProductName());
				atrans.setSerialNo(serialNo);
				atrans.setQuantity(quantity);
				atrans.setRate(rates);
				atrans.setAmount(new BigDecimal(quantity).multiply(new BigDecimal(atrans.getRate())).toString());
				atrans.setTaxes(productdetails.get(Integer.parseInt(serialNo) - 1).getTaxes());
				if(aTransaction.getEditStatus().equalsIgnoreCase("Edit")){
					atrans.setSalesPaymentID(Integer.parseInt(id));
				}
				if(!atrans.getTaxes().equalsIgnoreCase("")){
					subtaxvalueChange(atrans,aTransaction);
				}
				productdetails.set((Integer.parseInt(serialNo)-1),atrans);
				totalamountCalculation(productdetails,aTransaction);
				subcurrencyTypeValueChange(aTransaction.getCurrencyType());
			}
		}catch(Exception e){
			logger.warn("---------------Inside Exception----------------"+e.getMessage());
		}finally{
			serialNo="";quantity="";rates="";id="";
		}
	}
	public void rateValueChange(ValueChangeEvent vc){
		logger.info("[rateValueChange()] --------------- Inside rateValueChange() method() ------------------------");
		String serialNo="";String rate="";String quantity="";String id="";
		try{
			rate=vc.getNewValue().toString();
			quantity=vc.getComponent().getAttributes().get("quantity").toString();
			serialNo = vc.getComponent().getAttributes().get("serial").toString();
			if(aTransaction.getEditStatus().equalsIgnoreCase("Edit")){
				id=vc.getComponent().getAttributes().get("pid").toString();
			}
			if(!rate.equalsIgnoreCase("")){
				ATransaction atrans=new ATransaction();
				atrans.setProductName(productdetails.get(Integer.parseInt(serialNo) - 1).getProductName());
				atrans.setSerialNo(serialNo);
				atrans.setQuantity(quantity);
				atrans.setRate(rate);
				atrans.setAmount(new BigDecimal(quantity).multiply(new BigDecimal(rate)).toString());
				atrans.setTaxes(productdetails.get(Integer.parseInt(serialNo) - 1).getTaxes());
				if(aTransaction.getEditStatus().equalsIgnoreCase("Edit")){
					atrans.setSalesPaymentID(Integer.parseInt(id));
				}
				if(!atrans.getTaxes().equalsIgnoreCase("")){
					subtaxvalueChange(atrans,aTransaction);
				}
				productdetails.set((Integer.parseInt(serialNo)-1),atrans);
				totalamountCalculation(productdetails,aTransaction);
				subcurrencyTypeValueChange(aTransaction.getCurrencyType());
			}
		}catch(Exception e){
			logger.warn("---------------Inside Exception----------------"+e.getMessage());
		}finally{
			serialNo="";quantity="";rate="";id="";
		}
	}	
	
	public String saveSalesTransaction(){
		logger.info("[saveSalesTransaction()] --------------- Inside saveSalesTransaction() method() ------------------------");
		String status="Fail";boolean valids=false;
		try{
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			if(paymentStatus.equals("payment")){
				if(salesvalidation(true))valids=true;
			}
			if(paymentStatus.equals("receive payment")){
				if(salesvalidation1(true))valids=true;
			}
			if(valids==true){
				aTransaction.setTransactionType(transactionType);
				aTransaction.setBaseCurrency(baseCurrency);
				aTransaction.setPaymentdataTableList(new ArrayList<ATransaction>());
				aTransaction.setPaymentdataTableList(paymentdatatableList);
				status=controller.saveSalesTransaction(clientID,aTransaction,productdetails);
				if (status.equalsIgnoreCase("Success")) {
					if(paymentStatus.equalsIgnoreCase("payment")){
						RequestContext.getCurrentInstance().execute("PF('successDialog').show();");
					} else if(paymentStatus.equalsIgnoreCase("receive payment")){
						RequestContext.getCurrentInstance().execute("PF('successPaymentDlg').show();");
					}
					salesaccountstransactionpage();
				}
			}
		}catch(Exception e){
			logger.warn("---------------Inside Exception----------------"+e.getMessage());
		}finally{
			controller=null;
		}
		return "";
	}
	
	public void payamountvalueChange(ValueChangeEvent vc){
		logger.info("[payamountvalueChange()]-------------------------Inside payamountvalueChange() in MB Calling-------------------------------");
		String amount="";String fieldName;FacesContext fc = FacesContext.getCurrentInstance();totalAmount=0.0;Double paid=0.0;
	    try {
	    	String serialNo=vc.getComponent().getAttributes().get("serial").toString();
	    	amount=vc.getNewValue().toString();
	    	Date billDate=(Date)vc.getComponent().getAttributes().get("billdate");
	    	Date dueDate=(Date)vc.getComponent().getAttributes().get("duedate");
			if(!amount.equalsIgnoreCase("")){
				ATransaction atrans=new ATransaction();
				atrans.setTransactionNo(paymentdatatableList.get(Integer.parseInt(serialNo) - 1).getTransactionNo());
				atrans.setTransactionID(paymentdatatableList.get(Integer.parseInt(serialNo) - 1).getTransactionID());
				atrans.setSerialNo(serialNo);
				atrans.setDueDate(dueDate);
				atrans.setBillDate(billDate);
				atrans.setTransamount(paymentdatatableList.get(Integer.parseInt(serialNo) - 1).getTransamount());
				atrans.setBalAmount(paymentdatatableList.get(Integer.parseInt(serialNo) - 1).getBalAmount());
				atrans.setAmount(paymentdatatableList.get(Integer.parseInt(serialNo) - 1).getAmount());
				atrans.setCurrency(paymentdatatableList.get(Integer.parseInt(serialNo) - 1).getCurrency());
				atrans.setPayAmount(amount);
				paymentdatatableList.set((Integer.parseInt(serialNo)-1),atrans);
				BigDecimal total=BigDecimal.valueOf(0);
				try{
					for (int i = 0; i < paymentdatatableList.size(); i++) {
						try{
							if(!paymentdatatableList.get(i).getPayAmount().equalsIgnoreCase("")||!paymentdatatableList.get(i).getPayAmount().equalsIgnoreCase(null)){
								if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
									paid=Double.valueOf(vc.getNewValue().toString());
								}else{
									BigDecimal currAmount=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),vc.getNewValue().toString());
									paid=Double.valueOf(currAmount.toString());
								}
								Double balance=Double.valueOf(vc.getComponent().getAttributes().get("balance").toString().trim());
							    if(paid>balance){
							    	fieldName = CommonValidate.findComponentInRoot("description").getClientId(fc);
							        fc.addMessage(fieldName, new FacesMessage(atrans.getTransactionNo()+" "+"-"+" "+"Payment amount is Higher than the Open Balance"));
							    }else{
							    	total=total.add(new BigDecimal(paymentdatatableList.get(i).getPayAmount()));
							    }
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				aTransaction.setReceiveAmount(String.valueOf(total));
				if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
					aTransaction.setCurrencyAmount(String.valueOf(total));
				}else{
					BigDecimal currAmount=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),String.valueOf(total));
					aTransaction.setCurrencyAmount(String.valueOf(currAmount));
				}
			}
	    }catch (Exception e) {
	    	logger.warn("---------------Inside Exception----------------"+e.getMessage());
		}finally{
			amount="";
		}
	}

	public String generateInvoice(){
		logger.info("[generateInvoice()]-------------------------Inside generateInvoice() in MB Calling-------------------------------");
		try{
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			controller.generateInvoice(clientID,aTransaction.getTransactionID());
			RequestContext.getCurrentInstance().execute("PF('successInvoiceDlg').show();");
		}catch(Exception e){
			logger.warn("--------------Inside Exception-------------------"+e.getMessage());
		}
		return "";
	}
	
	public String receivePayment(){
		logger.info("[generateInvoice()]-------------------------Inside generateInvoice() in MB Calling-------------------------------");
		String paymentstatus="";List<ChartOfAccount> accountlist = null;setPaymentStatus("");aTransaction.setReceiveAmount("0.00");
		aTransaction.setPaymentMode("");aTransaction.setAccountType("");aTransaction.setCurrencyAmount("");aTransaction.setCurrencyType("");
		try{
		   ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		   controller = (DemoController) ctx.getBean("controller");
		   String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
		   String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
		   paymentstatus="receive payment";
		   setPaymentStatus(paymentstatus);
		   setTransactionType("Payment");
		   aTransaction.setBaseCurrency(baseCurrency);
		   paymentdatatableList=new ArrayList<ATransaction>();
		   paymentdatatableList=controller.getpaymentdetails(clientID,paymentstatus,aTransaction);
		   accountlist=controller.accountlist(clientID);
		   accounttypeList=new ArrayList<String>();
		   if(accountlist.size()>0){
			   for (int i = 0; i < accountlist.size(); i++) {
				   if(accountlist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Bank") || 
						   accountlist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Other Current Assets")){
					   accounttypeList.add(accountlist.get(i).getAccountName());
				   }
			   }
		    }
		}catch(Exception e){
			logger.warn("--------------Inside Exception-------------------"+e.getMessage());
		}finally{
			paymentstatus="";accountlist = null;
		}
		return "salesTransactionForm";
	}
	
	public void panelcloseListener(CloseEvent event) throws IOException{
		logger.info("[panelcloseListener()]-------------------------Inside panelcloseListener() in MB Calling-------------------------------");
		FacesContext.getCurrentInstance().getExternalContext().redirect("/inacsys/pages/xhtml/salesaccountsTransactionForm.xhtml");
		salesaccountstransactionpage();
	}
	
	/* Ragulan */
	
	public void transactionViewForm() {
		logger.info("type----------------"+aTransaction.getTransactionType()+"------------"+aTransaction.getTransactionID());
		  transactionType=aTransaction.getTransactionType();
		  Map<String, Object> options = new HashMap<String, Object>();
		  options.put("modal", true);
		  options.put("draggable", false);
		  options.put("resizable", false);
		  options.put("contentHeight", 550);
		  options.put("contentWidth", 1000);
		  RequestContext.getCurrentInstance().openDialog("salesTransactionView",options, null);
		  salesDatas();
	}

	public void transactionmodifyform() {
		logger.info("[transactionmodifyform()] ---------------"+aTransaction.getTransactionType()+"------------"+aTransaction.getTransactionID());
		transactionType=aTransaction.getTransactionType();
		aTransaction.setRemoveList(new ArrayList<Integer>());
		aTransaction.setProductList(new ArrayList<ATransaction>());
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 550);
		options.put("contentWidth", 1000);
		RequestContext.getCurrentInstance().openDialog("salesTransactionEdit",options, null);
		salesDatas();
		aTransaction.setEditStatus("Edit");
		String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
		ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		DemoController controller=(DemoController) ctx.getBean("controller");
		controller.getsalestransactioncustprod(clientID,aTransaction);
	}
	
	public void salesDatas(){
		logger.info("[salesDatas()]----------------------Inside salesDatas() in MB Calling----------------------");
		String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
		try{
			productdetails=new ArrayList<ATransaction>();
			ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			DemoController controller=(DemoController) ctx.getBean("controller");
			controller.salesTransactionView(clientID,aTransaction);
			setProductdetails(aTransaction.getProductList());
			aTransaction.setEditTransAmount(aTransaction.getCurrencyAmount());
			aTransaction.setEditBeforeTaxAmount(aTransaction.getCurrencybeforeTaxAmount());
		}catch(Exception e){
		  logger.warn("------------------Inside Exception--------------------"+e.getMessage());
		}
	}
	public void addRowEdit(){
		for (int i = 0; i < 1; i++) {
			ATransaction atrans=new ATransaction();
			atrans.setSerialNo(String.valueOf(aTransaction.getProductList().size()+1));
			atrans.setTaxes("");
			aTransaction.getProductList().add(atrans);
		}
	}
	public void transactionViewclose() {
		RequestContext.getCurrentInstance().closeDialog("salesTransactionView");
	}
	public void transactionEditclose() {
		RequestContext.getCurrentInstance().closeDialog("salesTransactionEdit");
		salesaccountstransactionpage();
	}
	
	public String salesTransactionUpdate(){
		  try{
			  if(salesTransactionValidation(true)){
				  String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
				  ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				  DemoController controller=(DemoController) ctx.getBean("controller");
				  totalamountCalculation(productdetails,aTransaction);
				  subcurrencyTypeValueChange(aTransaction.getCurrencyType());
				  System.out.println("currency before tax amount"+aTransaction.getCurrencybeforeTaxAmount());
				  aTransaction.setProductList(productdetails);
				  String status=controller.salesTransactionUpadte(clientID,aTransaction);
				  salesTransactiondetails=controller.getsalestransactiontableview(clientID,aTransaction);
				  if("Success".equalsIgnoreCase(status)){
					  RequestContext.getCurrentInstance().execute("PF('editConfirm').show()");
				  }
			  }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		return "";
	}

	private boolean salesTransactionValidation(boolean valid) {
			   valid=true;
			   String fieldName="";
			   FacesContext fc=FacesContext.getCurrentInstance();
			   String[] status=new String[8];
			   status[0]="Please select the Customer Name";
			   status[1]="Please select the Currency Type";
			   status[2]="Please Select the ProductName";
			   status[3]="Please Enter The Quantity";
			   status[4]="Please Enter the Rate";
			   status[5]="The Quantity should be Number";
			   status[6]="The Rate should be Number";
			   status[7]="Please Select the Tax";
			   if(StringUtils.isEmpty(aTransaction.getCustomerName())){
			      fieldName=CommonValidate.findComponentInRoot("customername").getClientId(fc);
			      fc.addMessage(fieldName, new FacesMessage(status[0]));
			      valid=false;
			   }if(StringUtils.isEmpty(aTransaction.getCurrencyType())){
			      fieldName=CommonValidate.findComponentInRoot("currency").getClientId(fc);
			      fc.addMessage(fieldName, new FacesMessage(status[1]));
			      valid=false;
			   }
			   if(productdetails.size()==0){
					 fieldName = CommonValidate.findComponentInRoot("datatable").getClientId(fc);
					 fc.addMessage(fieldName, new FacesMessage("Please enter the atleast one row value"));
					 valid = false;
			   }else{
				   if(aTransaction.getCountry().equalsIgnoreCase("India")){
						 if (StringUtils.isEmpty(productdetails.get(0).getQuantity())
									|| StringUtils.isEmpty(productdetails.get(0).getRate())
									|| productdetails.get(0).getProductName().equalsIgnoreCase("select")
									|| StringUtils.isEmpty(productdetails.get(0).getTaxes())) {
							 fieldName = CommonValidate.findComponentInRoot("datatable").getClientId(fc);
							 fc.addMessage(fieldName, new FacesMessage("Please enter the first row value"));
							 valid = false;
						 }else{
							 for (int i = 1; i < productdetails.size(); i++) {
								 System.out.println("inside for loop"+productdetails.get(i).getProductName());
								 if(!productdetails.get(i).getProductName().equalsIgnoreCase("select")){
									 if(StringUtils.isEmpty(productdetails.get(i).getQuantity())){
									     fieldName=CommonValidate.findComponentInRoot("datatable").getClientId(fc);
									     fc.addMessage(fieldName, new FacesMessage(status[3]));
									     valid=false;
									 }/*else{
										 if(!CommonValidate.validateNumber(productdetails.get(i).getQuantity())){
											 fieldName=CommonValidate.findComponentInRoot("quan").getClientId(fc);
											 fc.addMessage(fieldName, new FacesMessage("Please Enter the Valid Quantity"));
											 valid=false;
									     }
									 }*/
									 else if(StringUtils.isEmpty(productdetails.get(i).getRate())){
									     fieldName=CommonValidate.findComponentInRoot("datatable").getClientId(fc);
									     fc.addMessage(fieldName, new FacesMessage(status[4]));
									     valid=false;
									 } 
									 else if(StringUtils.isEmpty(productdetails.get(i).getTaxes())){
									     fieldName=CommonValidate.findComponentInRoot("datatable").getClientId(fc);
									     fc.addMessage(fieldName, new FacesMessage(status[7]));
									     valid=false;
									 }
								}
							}
						 }
					 }else{
						 if (StringUtils.isEmpty(productdetails.get(0).getQuantity())
									|| StringUtils.isEmpty(productdetails.get(0).getRate())
									|| productdetails.get(0).getProductName().equalsIgnoreCase("select")) {
							 fieldName = CommonValidate.findComponentInRoot("datatable").getClientId(fc);
							 fc.addMessage(fieldName, new FacesMessage("Please enter the first row value"));
							 valid = false;
						 }else{
							 for (int i = 1; i < productdetails.size(); i++) {
								 System.out.println("inside for loop"+productdetails.get(i).getProductName());
								 if(!productdetails.get(i).getProductName().equalsIgnoreCase("select")){
									if(StringUtils.isEmpty(productdetails.get(i).getQuantity())){
									     fieldName=CommonValidate.findComponentInRoot("datatable").getClientId(fc);
									     fc.addMessage(fieldName, new FacesMessage(status[3]));
									     valid=false;
									 }/*else{
										 if(!CommonValidate.validateNumber(productdetails.get(i).getQuantity())){
											 fieldName=CommonValidate.findComponentInRoot("quan").getClientId(fc);
											 fc.addMessage(fieldName, new FacesMessage("Please Enter the Valid Quantity"));
											 valid=false;
									     }
									 }*/
									 else if(StringUtils.isEmpty(productdetails.get(i).getRate())){
									     fieldName=CommonValidate.findComponentInRoot("datatable").getClientId(fc);
									     fc.addMessage(fieldName, new FacesMessage(status[4]));
									     valid=false;
									 } 
								}
							}
						 }
					 }
			   }
			 return valid;
		}
	
	private boolean salesvalidation(boolean valid) throws SQLException {
		 valid=true;Double paid=Double.valueOf(0);
		 String fieldName="";
		 FacesContext fc=FacesContext.getCurrentInstance();
		 try{
			 if(StringUtils.isEmpty(aTransaction.getCustomerName())){
				   fieldName=CommonValidate.findComponentInRoot("customername").getClientId(fc);
				   fc.addMessage(fieldName, new FacesMessage("Please select the Customer Name"));
				   valid=false;
			 }
			 if(transactionType.equalsIgnoreCase("Payment")){
				 if(StringUtils.isEmpty(aTransaction.getCurrencyType())){
					   fieldName=CommonValidate.findComponentInRoot("curr1").getClientId(fc);
					   fc.addMessage(fieldName, new FacesMessage("Please select the Currency Type"));
					   valid=false;
				 }
			 }else{
				 if(StringUtils.isEmpty(aTransaction.getCurrencyType())){
					   fieldName=CommonValidate.findComponentInRoot("currency").getClientId(fc);
					   fc.addMessage(fieldName, new FacesMessage("Please select the Currency Type"));
					   valid=false;
				 }
			 }
			 if(transactionType.equalsIgnoreCase("Invoice")){
				 if (aTransaction.getTerms().equalsIgnoreCase("select")) {
					fieldName = CommonValidate.findComponentInRoot("terms").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please select Payment Terms"));
					valid=false;
				 }
			 }
			 if(transactionType.equalsIgnoreCase("Sales Receipt")){
				 if (StringUtils.isEmpty(aTransaction.getPaymentMode())) {
					fieldName = CommonValidate.findComponentInRoot("payterms2").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please select Payment Method"));
					valid=false;
				 }
				 if (StringUtils.isEmpty(aTransaction.getAccountType())||aTransaction.getAccountType().equalsIgnoreCase("add")) {
					fieldName = CommonValidate.findComponentInRoot("payaccounts2").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please select a Deposit Account"));
					valid=false;
				 }
			 }
			 if (!getTransactionType().equalsIgnoreCase("Payment")) {
				 if(productdetails.size()==0){
					 fieldName = CommonValidate.findComponentInRoot("products").getClientId(fc);
					 fc.addMessage(fieldName, new FacesMessage("Please enter the atleast one row value"));
					 valid = false;
				 }else{
					 if(aTransaction.getCountry().equalsIgnoreCase("India")){
						 if(aTransaction.getTaxType().equalsIgnoreCase("") || aTransaction.getTaxType().equalsIgnoreCase("Out of scope of Tax")){
							 if (StringUtils.isEmpty(productdetails.get(0).getQuantity())
										|| StringUtils.isEmpty(productdetails.get(0).getRate())
										|| productdetails.get(0).getProductName().equalsIgnoreCase("select")) {
								 fieldName = CommonValidate.findComponentInRoot("products").getClientId(fc);
								 fc.addMessage(fieldName, new FacesMessage("Please enter the first row value"));
								 valid = false;
							 }else{
								 for (int i = 1; i < productdetails.size(); i++) {
									 System.out.println("inside for loop"+productdetails.get(i).getProductName());
									 if(!productdetails.get(i).getProductName().equalsIgnoreCase("select")){
										 if(StringUtils.isEmpty(productdetails.get(i).getQuantity())){
										     fieldName=CommonValidate.findComponentInRoot("quan").getClientId(fc);
										     fc.addMessage(fieldName, new FacesMessage("Please Enter the Quantity"));
										     valid=false;
										 }/*else if(!StringUtils.isEmpty(productdetails.get(i).getQuantity())){
											 if(!CommonValidate.validateNumber(productdetails.get(i).getQuantity())){
												 fieldName=CommonValidate.findComponentInRoot("quan").getClientId(fc);
												 fc.addMessage(fieldName, new FacesMessage("Please Enter the Valid Quantity"));
												 valid=false;
										     }
										 }*/
										 else if(StringUtils.isEmpty(productdetails.get(i).getRate())){
										     fieldName=CommonValidate.findComponentInRoot("rates").getClientId(fc);
										     fc.addMessage(fieldName, new FacesMessage("Please Enter the Rate"));
										     valid=false;
										 } 
									}
								}
							 }
						 }else{
							 if (StringUtils.isEmpty(productdetails.get(0).getQuantity())
										|| StringUtils.isEmpty(productdetails.get(0).getRate())
										|| productdetails.get(0).getProductName().equalsIgnoreCase("select")
										|| StringUtils.isEmpty(productdetails.get(0).getTaxes())) {
								 fieldName = CommonValidate.findComponentInRoot("products").getClientId(fc);
								 fc.addMessage(fieldName, new FacesMessage("Please enter the first row value"));
								 valid = false;
							 }else{
								 for (int i = 1; i < productdetails.size(); i++) {
									 System.out.println("inside for loop"+productdetails.get(i).getProductName());
									 if(!productdetails.get(i).getProductName().equalsIgnoreCase("select")){
										 if(StringUtils.isEmpty(productdetails.get(i).getQuantity())){
										     fieldName=CommonValidate.findComponentInRoot("quan").getClientId(fc);
										     fc.addMessage(fieldName, new FacesMessage("Please Enter the Quantity"));
										     valid=false;
										 }/*else if(!StringUtils.isEmpty(productdetails.get(i).getQuantity())){
											 if(!CommonValidate.validateNumber(productdetails.get(i).getQuantity())){
												 fieldName=CommonValidate.findComponentInRoot("quan").getClientId(fc);
												 fc.addMessage(fieldName, new FacesMessage("Please Enter the Valid Quantity"));
												 valid=false;
										     }
										 }*/
										 else if(StringUtils.isEmpty(productdetails.get(i).getRate())){
										     fieldName=CommonValidate.findComponentInRoot("rates").getClientId(fc);
										     fc.addMessage(fieldName, new FacesMessage("Please Enter the Rate"));
										     valid=false;
										 } 
										 else if(StringUtils.isEmpty(productdetails.get(i).getTaxes())){
										     fieldName=CommonValidate.findComponentInRoot("taxs").getClientId(fc);
										     fc.addMessage(fieldName, new FacesMessage("Please Select the Tax"));
										     valid=false;
										 }
									}
								}
							 }
						 }
					 }else{
						 if (StringUtils.isEmpty(productdetails.get(0).getQuantity())
									|| StringUtils.isEmpty(productdetails.get(0).getRate())
									|| productdetails.get(0).getProductName().equalsIgnoreCase("select")) {
							 fieldName = CommonValidate.findComponentInRoot("products").getClientId(fc);
							 fc.addMessage(fieldName, new FacesMessage("Please enter the first row value"));
							 valid = false;
						 }else{
							 for (int i = 1; i < productdetails.size(); i++) {
								 if(!productdetails.get(i).getProductName().equalsIgnoreCase("select")){
									 if(StringUtils.isEmpty(productdetails.get(i).getQuantity())){
									     fieldName=CommonValidate.findComponentInRoot("quan").getClientId(fc);
									     fc.addMessage(fieldName, new FacesMessage("Please Enter the Quantity"));
									     valid=false;
									 }/*else if(!StringUtils.isEmpty(productdetails.get(i).getQuantity())){
										 if(!CommonValidate.validateNumber(productdetails.get(i).getQuantity())){
											 fieldName=CommonValidate.findComponentInRoot("quan").getClientId(fc);
											 fc.addMessage(fieldName, new FacesMessage("Please Enter the Valid Quantity"));
											 valid=false;
									     }
									 }*/
									 else if(StringUtils.isEmpty(productdetails.get(i).getRate())){
									     fieldName=CommonValidate.findComponentInRoot("rates").getClientId(fc);
									     fc.addMessage(fieldName, new FacesMessage("Please Enter the Rate"));
									     valid=false;
									 } 
								}
							}
						 }
					 }
				 }
			}
			 if (getTransactionType().equalsIgnoreCase("Payment")) {
				 if (!aTransaction.getPaymentMode().equalsIgnoreCase("Credit Memo")) {
					 if (StringUtils.isEmpty(aTransaction.getAccountType()) || aTransaction.getAccountType().equalsIgnoreCase("add")) {
						   fieldName = CommonValidate.findComponentInRoot("payaccounts").getClientId(fc);
						   fc.addMessage(fieldName, new FacesMessage("Please select a Deposit account"));
						   valid = false;
					 }
				 }
					for (int j = 0; j < paymentdatatableList.size(); j++) {
						if(j==0){
							if (StringUtils.isEmpty(paymentdatatableList.get(j).getPayAmount())) {
								fieldName = CommonValidate.findComponentInRoot("description").getClientId(fc);
								fc.addMessage(fieldName, new FacesMessage("Please Enter the amount"));
								valid = false;
							}else{
								if (!CommonValidate.validateNumber(paymentdatatableList.get(j).getPayAmount())) {
									fieldName = CommonValidate.findComponentInRoot("description").getClientId(fc);
									fc.addMessage(fieldName,new FacesMessage("Please Enter the amount should be in number"));
									valid = false;
								}else {
									for (int k = 0; k<paymentdatatableList.size(); k++) {
										if (paymentdatatableList.get(k).getPayAmount()!=null) {
											if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
												paid=Double.valueOf(paymentdatatableList.get(k).getPayAmount());
											}else{
												BigDecimal currAmount=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),paymentdatatableList.get(k).getPayAmount());
												paid=Double.valueOf(currAmount.toString());
											}
											Double balance=Double.valueOf(paymentdatatableList.get(k).getBalAmount());
										    if(paid>balance){
										    	fieldName = CommonValidate.findComponentInRoot("description").getClientId(fc);
										        fc.addMessage(fieldName, new FacesMessage(paymentdatatableList.get(k).getTransactionNo()+" "+"-"+" "+"Payment amount is Higher than the Open Balance"));
										        valid=false;
										    }
										}
									}	
								}
							}
						}
					}
				}
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		return valid;
	}
	
	private boolean salesvalidation1(boolean valid) {
		 valid=true;String fieldName="";
		 FacesContext fc=FacesContext.getCurrentInstance();
		 if(StringUtils.isEmpty(aTransaction.getCurrencyType())){
			   fieldName=CommonValidate.findComponentInRoot("recpaycurrency").getClientId(fc);
			   fc.addMessage(fieldName, new FacesMessage("Please select the Currency Type"));
			   valid=false;
		 }
		 if (StringUtils.isEmpty(aTransaction.getAccountType()) || aTransaction.getAccountType().equalsIgnoreCase("add")) {
			   fieldName = CommonValidate.findComponentInRoot("recpaypayaccounts").getClientId(fc);
			   fc.addMessage(fieldName, new FacesMessage("Please select a Deposit account"));
			   valid = false;
		 }
		for (int j = 0; j < paymentdatatableList.size(); j++) {
			if(j==0){
				if (StringUtils.isEmpty(paymentdatatableList.get(j).getPayAmount())) {
					fieldName = CommonValidate.findComponentInRoot("description").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter the amount"));
					valid = false;
				}else{
					if (!CommonValidate.validateNumber(paymentdatatableList.get(j).getPayAmount())) {
						fieldName = CommonValidate.findComponentInRoot("description").getClientId(fc);
						fc.addMessage(fieldName,new FacesMessage("Please Enter the amount should be in number"));
						valid = false;
					}else {
						for (int k = 0; k<paymentdatatableList.size(); k++) {
							if (paymentdatatableList.get(k).getPayAmount()!=null) {
								if (new BigDecimal(paymentdatatableList.get(k).getPayAmount()).compareTo(new BigDecimal(paymentdatatableList.get(k).getBalAmount()))==1) {
									fieldName=CommonValidate.findComponentInRoot("description").getClientId();
									fc.addMessage(fieldName, new FacesMessage(paymentdatatableList.get(k).getTransactionNo()+" "+"-"+" "+"Payment amount is Higher than the Open Balance"));	
									valid=false;
								}
							}
						}	
					}
				}
			}
		}
		logger.debug("valid----"+valid);
		return valid;
	}
	public void accounttypeValueChange(ValueChangeEvent vc){
		logger.info("[accounttypeValueChange()]-----------------Inside accounttypeValueChange() in MB Calling-------------------");
		String accounttype="";
		try{
			accounttype=vc.getNewValue().toString();
			if(accounttype.equalsIgnoreCase("add")){
				String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
				accountsMB.accountsDatabean.setAccount_name("");accountsMB.accountsDatabean.setAccount_type("");accountsMB.accountsDatabean.setValidate("");
				accountsMB.accountsDatabean.setDetailType("");accountsMB.accountsDatabean.setBalance("0");accountsMB.accountsDatabean.setTypes(new ArrayList<String>());
				accountsMB.accountsDatabean.setBaseCurrency(baseCurrency);accountsMB.accountsDatabean.setCurrency("");accountsMB.accountsDatabean.setCurrencyAmount("");
				accountsMB.accountsDatabean.setTaxes("");accountsMB.accountsDatabean.setDate(null);
				RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
			}
		}catch(Exception e){
			logger.warn("-----------Inside Exception-----------------"+e.getMessage());
		}finally{
			accounttype="";
		}
	}
	
	public void accounttypeconfirm(String accountname){
		logger.info("[accounttypeconfirm()]-----------------Inside accounttypeconfirm() in MB Calling-------------------");
		List<ChartOfAccount> accountlist = null;
		try{
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			DemoController controller=(DemoController) ctx.getBean("controller");
			accountlist=new ArrayList<ChartOfAccount>();
			accountlist=controller.accountlist(clientID);
			accounttypeList=new ArrayList<String>();
			if(accountlist.size()>0){
				 for (int i = 0; i < accountlist.size(); i++) {
					if(accountlist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Bank") || 
							accountlist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Other Current Assets")){
						accounttypeList.add(accountlist.get(i).getAccountName());
					}
				 }
			}
			aTransaction.setAccountType(accountname);
		}catch(Exception e){
			logger.warn("-----------Inside Exception-----------------"+e.getMessage());
		}
	}
	
	//Start Client ID- CNT0000021 New Method By Ragulan 
	public void codeChange(){
		aTransaction.setDescription("");
		aTransaction.setCodeDescription("");
		try{
			if (aTransaction.getCode().equals("addnew")) {
				aTransaction.setCode("");
				aTransaction.setCodeDescription("");
				RequestContext.getCurrentInstance().execute("PF('codeDialog').show();");
			}else{
				ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				DemoController controller=(DemoController) ctx.getBean("controller");
				controller.getDescription(aTransaction);
				expenseTransactionlist.set(0, aTransaction);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String codeSave(){
		FacesContext fc=FacesContext.getCurrentInstance();
		String name="";
		String status="";
		try{
			ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			DemoController controller=(DemoController) ctx.getBean("controller");
			if(codeSavevalidation(true)){
				status=controller.codeSave(aTransaction);
				if("Success".equalsIgnoreCase(status)){
					RequestContext.getCurrentInstance().execute("PF('codeDialog').hide();");
					codeList=new ArrayList<String>();
					codeList=controller.getCodelist(aTransaction.getClientID());
					aTransaction.setDescription(aTransaction.getCodeDescription());
					expenseTransactionlist.set(0, aTransaction);
					RequestContext.getCurrentInstance().update("center_content");
				}else if("Exist".equalsIgnoreCase(status)){
					name=CommonValidate.findComponentInRoot("newcode").getClientId();
					fc.addMessage(name, new FacesMessage("This Code Already Registered"));
				}
			}
		}catch(Exception e){
			logger.warn("-------------------------Inside Exception-----------------------"+e.getMessage());
		}
		return"";
	}

	private boolean codeSavevalidation(boolean valid) {
		valid=true;
		FacesContext fc=FacesContext.getCurrentInstance();
		String name="";
		 String[] status=new String[2];
		 status[0]="Please Enter the Code";
		 status[1]="Please Enter the Description";
		if(StringUtils.isEmpty(aTransaction.getCode())){
			name=CommonValidate.findComponentInRoot("newcode").getClientId();
			fc.addMessage(name, new FacesMessage(status[0]));
			valid=false;
		}
		if(StringUtils.isEmpty(aTransaction.getCodeDescription())){
			name=CommonValidate.findComponentInRoot("newdescription").getClientId();
			fc.addMessage(name, new FacesMessage(status[1]));
			valid=false;
		}
		return valid;
	}
	
	public String codeView(){
		logger.info("[codeView()]--------------------Inside codeView() in MB Calling-----------------------");
		try{
			viewlist=new ArrayList<ATransaction>();
			ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			DemoController controller=(DemoController) ctx.getBean("controller");
			viewlist=controller.codeDetails(aTransaction);
		}catch(Exception e){
			logger.warn("-------------------------Inside Exception-----------------------"+e.getMessage());
		}
		return "";
	}
	//End Client ID- CNT0000021 New Method By Ragulan
	
	//Sales Delete Action by Prema 
	public void salesdeleteAction(ATransaction atrans){
		logger.info("[salesdeleteAction()]--------------------Inside salesdeleteAction() in MB Calling-----------------------");
		productdetails.remove(atrans);
		totalamountCalculation(productdetails, aTransaction);
		subcurrencyTypeValueChange(aTransaction.getCurrencyType());
	}
	
	//Expense Delete Action by Stanley 
	public void expensedeleteAction(ATransaction atrans){
		logger.info("[expensedeleteAction()]--------------------Inside expensedeleteAction() in MB Calling-----------------------");
		expenseTransactionlist.remove(atrans);
		totalamountCalculation(expenseTransactionlist, aTransaction);
		subcurrencyTypeValueChange(aTransaction.getCurrencyType());
	}
	
	public void currencyTypeValueChange(ValueChangeEvent vc){
		logger.info("[currencyTypeValueChange()]--------------------Inside currencyTypeValueChange() in MB Calling-----------------------");
		String value="";
		try{
			value=vc.getNewValue().toString();
			subcurrencyTypeValueChange(value);
		}catch(Exception e){
			logger.warn("-------------------------Inside Exception-----------------------"+e.getMessage());
		}
	}
	
	public void subcurrencyTypeValueChange(String value){
		try{
			if(value.equalsIgnoreCase("")){
				aTransaction.setCurrencyAmount("0");
			}else{
				if(aTransaction.getBaseCurrency().equalsIgnoreCase(value)){
					aTransaction.setCurrencyAmount(aTransaction.getTransamount());
				}else{
					BigDecimal currAmount=CurrencyConverter.findExchangeRateAndConvert(value,aTransaction.getBaseCurrency(),aTransaction.getTransamount());
					aTransaction.setCurrencyAmount(String.valueOf(currAmount));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void expensesubcurrencyTypeValueChange(String value){
		try{
			if(value.equalsIgnoreCase("")){
				aTransaction.setCurrencyAmount("0");
			}else{
				if(aTransaction.getBaseCurrency().equalsIgnoreCase(value)){
					aTransaction.setCurrencyAmount(aTransaction.getTotalAmount());
				}else{
					BigDecimal currAmount=CurrencyConverter.findExchangeRateAndConvert(value,aTransaction.getBaseCurrency(),aTransaction.getTotalAmount());
					aTransaction.setCurrencyAmount(String.valueOf(currAmount));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void paymentcurrencyTypeValueChange(ValueChangeEvent vc){
		logger.info("[paymentcurrencyTypeValueChange()]--------------------Inside paymentcurrencyTypeValueChange() in MB Calling-----------------------");
		try{
			if(vc.getNewValue().toString().equalsIgnoreCase("")){
				aTransaction.setCurrencyAmount("0");
			}else{
				aTransaction.setCurrency(vc.getNewValue().toString());
				if(aTransaction.getBaseCurrency().equalsIgnoreCase(aTransaction.getCurrency())){
					aTransaction.setCurrencyAmount(aTransaction.getReceiveAmount());
				}else{
					BigDecimal currAmount=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrency(),aTransaction.getBaseCurrency(),aTransaction.getReceiveAmount());
					aTransaction.setCurrencyAmount(String.valueOf(currAmount));
				}
			}
		}catch(Exception e){
			logger.warn("-------------------------Inside Exception-----------------------"+e.getMessage());
		}
	}
	
	public void totalamountCalculation(List<ATransaction> productdetails,ATransaction aTransaction){
		System.out.println("inside totalamountCalculation");
		logger.info("[totalamountCalculation()]--------------------------------Inside totalamountCalculation() in MB Calling-------------------------------------");
		BigDecimal total=BigDecimal.valueOf(0);BigDecimal subtotal=BigDecimal.valueOf(0);BigDecimal peramt;BigDecimal cgstamt;BigDecimal sgstamt;
		BigDecimal totalgstamt=BigDecimal.valueOf(0);BigDecimal taxamt=BigDecimal.valueOf(0);BigDecimal totalamt=BigDecimal.valueOf(0);
		aTransaction.setGstType("");aTransaction.setSgstType("");aTransaction.setCgstType("");String igstAmt="";String cgstAmt="";String sgstAmt="";
		BigDecimal totalperamt=BigDecimal.valueOf(0);BigDecimal totalcgstamt=BigDecimal.valueOf(0);BigDecimal totalsgstamt=BigDecimal.valueOf(0);
		try{
			for (int i = 0; i < productdetails.size(); i++) {
				if(!productdetails.get(i).getProductName().equalsIgnoreCase("select")){
					if(!productdetails.get(i).getAmount().equalsIgnoreCase("")||!productdetails.get(i).getAmount().equalsIgnoreCase(null)){
						subtotal=subtotal.add(new BigDecimal(productdetails.get(i).getAmount()));
						if(aTransaction.getTaxType().equals("Out of scope of Tax") || aTransaction.getTaxType().equals("Inclusive of Tax") || aTransaction.getTaxType().equals("")){
							total=total.add(new BigDecimal(productdetails.get(i).getAmount()));
						}
						if(!aTransaction.getTaxType().equals("Out of scope of Tax") || !aTransaction.getTaxType().equals("")){
							peramt=BigDecimal.valueOf(0);cgstamt=BigDecimal.valueOf(0);sgstamt=BigDecimal.valueOf(0);
							try{
								if(productdetails.get(i).getPercentageAmount().equalsIgnoreCase("") || productdetails.get(i).getPercentageAmount()==null){
									peramt=BigDecimal.valueOf(0);aTransaction.setGstType("");igstAmt="";aTransaction.setCurrencyIGSTAmount("");
								}else{
									peramt=new BigDecimal(productdetails.get(i).getPercentageAmount());
									totalperamt=totalperamt.add(peramt);aTransaction.setGstType(aTransaction.getGstStatus()+" IGST");
									igstAmt=String.valueOf(totalperamt);
								}
							}catch(NullPointerException e){
								e.printStackTrace();
							}
							try{
								if(productdetails.get(i).getCgstAmount().equalsIgnoreCase("") || productdetails.get(i).getCgstAmount()==null){
									cgstamt=BigDecimal.valueOf(0);aTransaction.setCgstType("");cgstAmt="";aTransaction.setCurrencyCGSTAmount("");
								}else{
									cgstamt=new BigDecimal(productdetails.get(i).getCgstAmount());
									totalcgstamt=totalcgstamt.add(cgstamt);aTransaction.setCgstType(aTransaction.getGstStatus()+" CGST");
									cgstAmt=String.valueOf(totalcgstamt);
								}
							}catch(NullPointerException e){
								e.printStackTrace();
							}
							try{
								if(productdetails.get(i).getSgstAmount().equalsIgnoreCase("") || productdetails.get(i).getSgstAmount()==null){
									sgstamt=BigDecimal.valueOf(0);aTransaction.setSgstType("");sgstAmt="";aTransaction.setCurrencySGSTAmount("");
								}else {
									sgstamt=new BigDecimal(productdetails.get(i).getSgstAmount());
									totalsgstamt=totalsgstamt.add(sgstamt);aTransaction.setSgstType(aTransaction.getGstStatus()+" SGST");
									sgstAmt=String.valueOf(totalsgstamt);
								}
							}catch(NullPointerException e){
								e.printStackTrace();
							}
							totalamt=totalamt.add(new BigDecimal(productdetails.get(i).getAmount())).add(peramt).add(cgstamt).add(sgstamt);
							if(productdetails.get(i).getTaxes().equalsIgnoreCase("Exempt IGST") || productdetails.get(i).getTaxes().equalsIgnoreCase("Exempt GST")
									|| productdetails.get(i).getTaxes().equalsIgnoreCase("Out of Scope") || productdetails.get(i).getTaxes().equalsIgnoreCase("") ){
								totalgstamt=totalgstamt.add(new BigDecimal(productdetails.get(i).getAmount()));
							}else{
								totalgstamt=totalgstamt.add(new BigDecimal(productdetails.get(i).getGstAmount()));
							}
							taxamt=taxamt.add(peramt).add(cgstamt).add(sgstamt);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("-------------------------Inside Exception-----------------------"+e.getMessage());
		}
		aTransaction.setCurrencyIGSTAmount(igstAmt);aTransaction.setCurrencyCGSTAmount(cgstAmt);aTransaction.setCurrencySGSTAmount(sgstAmt);
		if(aTransaction.getTaxType().equalsIgnoreCase("Exclusive of Tax")){
			aTransaction.setTransamount(totalamt.toString());aTransaction.setSubTotalAmount(subtotal.toString());aTransaction.setTotalAmount(totalamt.toString());
			aTransaction.setBeforeTaxAmount(String.valueOf(totalgstamt));aTransaction.setTaxAmount(String.valueOf(taxamt));
		}else if(aTransaction.getTaxType().equalsIgnoreCase("Out of scope of Tax") || aTransaction.getTaxType().equalsIgnoreCase("")){
			aTransaction.setTransamount(total.toString());aTransaction.setSubTotalAmount(subtotal.toString());aTransaction.setTotalAmount(total.toString());
			aTransaction.setBeforeTaxAmount(String.valueOf(subtotal));aTransaction.setTaxAmount("0");
		}else if(aTransaction.getTaxType().equalsIgnoreCase("Inclusive of Tax")){
			aTransaction.setTransamount(total.toString());aTransaction.setSubTotalAmount(subtotal.toString());aTransaction.setTotalAmount(total.toString());
			aTransaction.setBeforeTaxAmount(String.valueOf(totalgstamt));aTransaction.setTaxAmount(String.valueOf(taxamt));
		}
	}
	
	public void edittotalamountCalculation(List<ATransaction> productdetails,ATransaction aTransaction){
		logger.info("[edittotalamountCalculation()]--------------------------------Inside edittotalamountCalculation() in MB Calling-------------------------------------");
		BigDecimal total=BigDecimal.valueOf(0);BigDecimal subtotal=BigDecimal.valueOf(0);BigDecimal peramt;BigDecimal cgstamt;BigDecimal sgstamt;
		BigDecimal totalgstamt=BigDecimal.valueOf(0);BigDecimal taxamt=BigDecimal.valueOf(0);BigDecimal totalamt=BigDecimal.valueOf(0);
		aTransaction.setGstType("");aTransaction.setSgstType("");aTransaction.setCgstType("");String igstAmt="";String cgstAmt="";String sgstAmt="";
		BigDecimal totalperamt=BigDecimal.valueOf(0);BigDecimal totalcgstamt=BigDecimal.valueOf(0);BigDecimal totalsgstamt=BigDecimal.valueOf(0);
		try{
			for (int i = 0; i < productdetails.size(); i++) {
				if(!productdetails.get(i).getToAccount().equalsIgnoreCase("select")){
					if(!productdetails.get(i).getAmount().equalsIgnoreCase("")||!productdetails.get(i).getAmount().equalsIgnoreCase(null)){
						subtotal=subtotal.add(new BigDecimal(productdetails.get(i).getAmount()));
						if(aTransaction.getTaxType().equals("Out of scope of Tax") || aTransaction.getTaxType().equals("Inclusive of Tax") || aTransaction.getTaxType().equals("")){
							total=total.add(new BigDecimal(productdetails.get(i).getAmount()));
						}
						if(!aTransaction.getTaxType().equals("Out of scope of Tax") || !aTransaction.getTaxType().equals("")){
							peramt=BigDecimal.valueOf(0);cgstamt=BigDecimal.valueOf(0);sgstamt=BigDecimal.valueOf(0);
							try{
								if(productdetails.get(i).getPercentageAmount().equalsIgnoreCase("") || productdetails.get(i).getPercentageAmount()==null){
									peramt=BigDecimal.valueOf(0);aTransaction.setGstType("");igstAmt="";aTransaction.setCurrencyIGSTAmount("");
								}else{
									peramt=new BigDecimal(productdetails.get(i).getPercentageAmount());
									totalperamt=totalperamt.add(peramt);aTransaction.setGstType(aTransaction.getGstStatus()+" IGST");
									igstAmt=String.valueOf(totalperamt);
								}
							}catch(NullPointerException e){
								e.printStackTrace();
							}
							try{
								if(productdetails.get(i).getCgstAmount().equalsIgnoreCase("") || productdetails.get(i).getCgstAmount()==null){
									cgstamt=BigDecimal.valueOf(0);aTransaction.setCgstType("");cgstAmt="";aTransaction.setCurrencyCGSTAmount("");
								}else{
									cgstamt=new BigDecimal(productdetails.get(i).getCgstAmount());
									totalcgstamt=totalcgstamt.add(cgstamt);aTransaction.setCgstType(aTransaction.getGstStatus()+" CGST");
									cgstAmt=String.valueOf(totalcgstamt);
								}
							}catch(NullPointerException e){
								e.printStackTrace();
							}
							try{
								if(productdetails.get(i).getSgstAmount().equalsIgnoreCase("") || productdetails.get(i).getSgstAmount()==null){
									sgstamt=BigDecimal.valueOf(0);aTransaction.setSgstType("");sgstAmt="";aTransaction.setCurrencySGSTAmount("");
								}else {
									sgstamt=new BigDecimal(productdetails.get(i).getSgstAmount());
									totalsgstamt=totalsgstamt.add(sgstamt);aTransaction.setSgstType(aTransaction.getGstStatus()+" SGST");
									sgstAmt=String.valueOf(totalsgstamt);
								}
							}catch(NullPointerException e){
								e.printStackTrace();
							}
							totalamt=totalamt.add(new BigDecimal(productdetails.get(i).getAmount())).add(peramt).add(cgstamt).add(sgstamt);
							if(productdetails.get(i).getTaxes().equalsIgnoreCase("Exempt IGST") || productdetails.get(i).getTaxes().equalsIgnoreCase("Exempt GST")
									|| productdetails.get(i).getTaxes().equalsIgnoreCase("Out of Scope") || productdetails.get(i).getTaxes().equalsIgnoreCase("") ){
								totalgstamt=totalgstamt.add(new BigDecimal(productdetails.get(i).getAmount()));
							}else{
								totalgstamt=totalgstamt.add(new BigDecimal(productdetails.get(i).getGstAmount()));
							}
							taxamt=taxamt.add(peramt).add(cgstamt).add(sgstamt);
						}
					}
					System.out.println("gst type"+aTransaction.getGstType());
					System.out.println("sgst type"+aTransaction.getSgstType());
					System.out.println("cgst type"+aTransaction.getCgstType());
				}
			}
		}catch(Exception e){
			logger.warn("-------------------------Inside Exception-----------------------"+e.getMessage());
		}
		aTransaction.setCurrencyIGSTAmount(igstAmt);aTransaction.setCurrencyCGSTAmount(cgstAmt);aTransaction.setCurrencySGSTAmount(sgstAmt);
		if(aTransaction.getTaxType().equalsIgnoreCase("Exclusive of Tax")){
			aTransaction.setTransamount(totalamt.toString());aTransaction.setSubTotalAmount(subtotal.toString());aTransaction.setTotalAmount(totalamt.toString());
			aTransaction.setBeforeTaxAmount(String.valueOf(totalgstamt));aTransaction.setTaxAmount(String.valueOf(taxamt));
		}else if(aTransaction.getTaxType().equalsIgnoreCase("Out of scope of Tax") || aTransaction.getTaxType().equalsIgnoreCase("")){
			aTransaction.setTransamount(total.toString());aTransaction.setSubTotalAmount(subtotal.toString());aTransaction.setTotalAmount(total.toString());
			aTransaction.setBeforeTaxAmount(String.valueOf(subtotal));aTransaction.setTaxAmount("0");
		}else if(aTransaction.getTaxType().equalsIgnoreCase("Inclusive of Tax")){
			aTransaction.setTransamount(total.toString());aTransaction.setSubTotalAmount(subtotal.toString());aTransaction.setTotalAmount(total.toString());
			aTransaction.setBeforeTaxAmount(String.valueOf(totalgstamt));aTransaction.setTaxAmount(String.valueOf(taxamt));
		}
	}
	
	public void taxValueChange(ValueChangeEvent vc){
		logger.info("[taxValueChange()]--------------------Inside taxValueChange() in MB Calling-----------------------");
		String serialNo="";String id="";
		try{
			serialNo = vc.getComponent().getAttributes().get("serial").toString();
			if(aTransaction.getEditStatus().equalsIgnoreCase("Edit")){
				id = vc.getComponent().getAttributes().get("pid").toString();
			}
			if(!vc.getNewValue().toString().equalsIgnoreCase("")){
				ATransaction atrans=new ATransaction();
				atrans.setProductName(productdetails.get(Integer.parseInt(serialNo) - 1).getProductName());
				atrans.setSerialNo(serialNo);
				atrans.setQuantity(productdetails.get(Integer.parseInt(serialNo) - 1).getQuantity());
				atrans.setRate(productdetails.get(Integer.parseInt(serialNo) - 1).getRate());
				atrans.setAmount(productdetails.get(Integer.parseInt(serialNo) - 1).getAmount());
				atrans.setTaxes(vc.getNewValue().toString());
				if(aTransaction.getEditStatus().equalsIgnoreCase("Edit")){
					atrans.setSalesPaymentID(Integer.parseInt(id));
				}
				subtaxvalueChange(atrans,aTransaction);
				productdetails.set((Integer.parseInt(serialNo)-1),atrans);
				totalamountCalculation(productdetails,aTransaction);
				subcurrencyTypeValueChange(aTransaction.getCurrencyType());
			}
		}catch(Exception e){
			logger.warn("-------------------------Inside Exception-----------------------"+e.getMessage());
		}finally{
			serialNo="";id="";
		}
	}
	
	public void expensetaxValueChange(ValueChangeEvent vc){
		logger.info("[expensetaxValueChange()]--------------------Inside expensetaxValueChange() in MB Calling-----------------------");
		String serialNo="";String id="";
		try{
			serialNo = vc.getComponent().getAttributes().get("serial").toString();
			if(aTransaction.getEditStatus().equalsIgnoreCase("Edit")){
				id=vc.getComponent().getAttributes().get("pid").toString();
			}
			if(!vc.getNewValue().toString().equalsIgnoreCase("")){
				ATransaction atrans=new ATransaction();
				atrans.setSerialNo(serialNo);
				atrans.setToAccount(expenseTransactionlist.get(Integer.parseInt(serialNo) - 1).getToAccount());
				atrans.setProductName(expenseTransactionlist.get(Integer.parseInt(serialNo) - 1).getToAccount());
				atrans.setDescription(expenseTransactionlist.get(Integer.parseInt(serialNo) - 1).getDescription());
				atrans.setAmount(expenseTransactionlist.get(Integer.parseInt(serialNo) - 1).getAmount());
				atrans.setTaxes(vc.getNewValue().toString());
				if(aTransaction.getEditStatus().equalsIgnoreCase("Edit")){
					atrans.setPaymentId(Integer.parseInt(id));
					atrans.setEditBeforeTaxAmount(expenseTransactionlist.get(Integer.parseInt(serialNo) - 1).getEditBeforeTaxAmount());
				}
				subtaxvalueChange(atrans,aTransaction);
				expenseTransactionlist.set((Integer.parseInt(serialNo)-1),atrans);
				edittotalamountCalculation(expenseTransactionlist,aTransaction);
				subcurrencyTypeValueChange(aTransaction.getCurrencyType());
			}
		}catch(Exception e){
			logger.warn("-------------------------Inside Exception-----------------------"+e.getMessage());
		}finally{
			serialNo="";id="";
		}
	}
	
	public void salesEditRemove(ATransaction atrans){
		logger.info("[salesEditRemove()]--------------------Inside salesEditRemove() in MB Calling-----------------------");
		try{
			if(atrans.getSalesPaymentID()!=0){
				aTransaction.getRemoveList().add(atrans.getSalesPaymentID());
			}
			productdetails.remove(atrans);
			totalamountCalculation(productdetails, aTransaction);
			subcurrencyTypeValueChange(aTransaction.getCurrencyType());
		}catch(Exception e){
			logger.warn("-------------------------Inside Exception-----------------------"+e.getMessage());
		}
	}
	
	public void expenseEditRemove(ATransaction atrans){
		logger.info("[expenseEditRemove()]--------------------Inside expenseEditRemove() in MB Calling-----------------------");
		try{
			if(atrans.getPaymentId()!=0){
				aTransaction.getRemoveList().add(atrans.getPaymentId());
			}
			aTransaction.getExpenseTransactionlist().remove(atrans);
			edittotalamountCalculation(aTransaction.getExpenseTransactionlist(), aTransaction);
			subcurrencyTypeValueChange(aTransaction.getCurrencyType());
		}catch(Exception e){
			logger.warn("-------------------------Inside Exception-----------------------"+e.getMessage());
		}
	}
	
}