package com.inacsys.managedBean;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.hibernate.validator.util.privilegedactions.GetConstructor;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.SortOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.ChartOfAccount;
import com.inacsys.shared.I0018;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0021;
import com.inacsys.shared.I0032;
import com.inacsys.util.CommonValidate;
import com.inacsys.util.GenerateEmployee;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "quickSaleMB")
public class QuickSaleMB {
	private static Logger logger = Logger.getLogger(QuickSaleMB.class);

	public QuickSaleViewMB getQuickSaleViewMB() {
		return quickSaleViewMB;
	}

	public void setQuickSaleViewMB(QuickSaleViewMB quickSaleViewMB) {
		this.quickSaleViewMB = quickSaleViewMB;
	}

	@ManagedProperty(value = "#{salesViewMB}")
	SalesViewMB salesViewMB;
	@ManagedProperty(value = "#{quickSaleViewMB}")
	QuickSaleViewMB quickSaleViewMB;

	@ManagedProperty(value = "#{accountsMB}")
	AccountsMB accountsMB;
	
	
	public AccountsMB getAccountsMB() {
		return accountsMB;
	}

	public void setAccountsMB(AccountsMB accountsMB) {
		this.accountsMB = accountsMB;
	}

	public SalesViewMB getSalesViewMB() {
		return salesViewMB;
	}

	public void setSalesViewMB(SalesViewMB salesViewMB) {
		this.salesViewMB = salesViewMB;
	}

	public String crosstotal;
	public ArrayList<String> productlist = null;
	public String baseCurrency;
	public String currencyAmount;
	
	
	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public ArrayList<String> getProductlist() {
		return productlist;
	}

	public void setProductlist(ArrayList<String> productlist) {
		this.productlist = productlist;
	}

	public String getCrosstotal() {
		return crosstotal;
	}

	public void setCrosstotal(String crosstotal) {
		this.crosstotal = crosstotal;
	}

	public String discType;
	public String discAmnt;

	public String getDiscType() {
		return discType;
	}

	public void setDiscType(String discType) {
		this.discType = discType;
	}

	public String getDiscAmnt() {
		return discAmnt;
	}

	public void setDiscAmnt(String discAmnt) {
		this.discAmnt = discAmnt;
	}

	public String dr;
	public String nr;
	public String rollQuantities;
	public String rollQFlag1;
	public String rollQFlag2;

	public String getRollQFlag1() {
		return rollQFlag1;
	}

	public void setRollQFlag1(String rollQFlag1) {
		this.rollQFlag1 = rollQFlag1;
	}

	public String getRollQFlag2() {
		return rollQFlag2;
	}

	public void setRollQFlag2(String rollQFlag2) {
		this.rollQFlag2 = rollQFlag2;
	}

	public String getRollQuantities() {
		return rollQuantities;
	}

	public void setRollQuantities(String rollQuantities) {
		this.rollQuantities = rollQuantities;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
	}

	public String batchProductName;
	List<String> batchProductName3;
	public String quantity;
	public String tbankname;

	public String getTbankname() {
		return tbankname;
	}

	public void setTbankname(String tbankname) {
		this.tbankname = tbankname;
	}

	public String sellingPrice;
	public String marginPrice;
	public String totalPrice;
	public String paymentType;
	public String validate;
	PurchaseOrder purchaseOrder = new PurchaseOrder();
	List<I0019> barcode;
	public String validate1;
	public String payType;
	public String discountzType;
	public String discountz;
	public String discountzAmount;
	private String unit;
	private String tflag1;
	private String tflag2;
	public String newbankName;
	public String cardno;
	public String bankname;
	public String accountno;
	private String priflag1;
	private String stockin;
	private Date qdate;
	public String currency;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPriflag1() {
		return priflag1;
	}

	public void setPriflag1(String priflag1) {
		this.priflag1 = priflag1;
	}

	public String getPriflag2() {
		return priflag2;
	}

	public void setPriflag2(String priflag2) {
		this.priflag2 = priflag2;
	}

	private String priflag2;

	public String getNewbankName() {
		return newbankName;
	}

	public void setNewbankName(String newbankName) {
		this.newbankName = newbankName;
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

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String bankName;
	public String chequeno;
	public Date chequedate;
	private String customerName;
	private String salereferencenumber;
	List<I0019> resul;
	public String address;
	public String telephonenumber;
	private String rollID;
	private Date fromdate;
	private Date todate;
	List<PurchaseOrder> mb2 = new ArrayList<PurchaseOrder>();
	List<PurchaseOrder> mb3 = new ArrayList<PurchaseOrder>();
	public String returnQuan1;
	public String returnQuan2;
	public String porderNo;
	public String vendorName;
	public Date orderDate;
	public String orderDate1;

	public String getOrderDate1() {
		return orderDate1;
	}

	public void setOrderDate1(String orderDate1) {
		this.orderDate1 = orderDate1;
	}

	public String totalQuan2;
	public String totalQuan1;

	public List<PurchaseOrder> getMb2() {
		return mb2;
	}

	public void setMb2(List<PurchaseOrder> mb2) {
		this.mb2 = mb2;
	}

	public List<PurchaseOrder> getMb3() {
		return mb3;
	}

	public void setMb3(List<PurchaseOrder> mb3) {
		this.mb3 = mb3;
	}

	public String getReturnQuan1() {
		return returnQuan1;
	}

	public void setReturnQuan1(String returnQuan1) {
		this.returnQuan1 = returnQuan1;
	}

	public String getReturnQuan2() {
		return returnQuan2;
	}

	public void setReturnQuan2(String returnQuan2) {
		this.returnQuan2 = returnQuan2;
	}

	public String getPorderNo() {
		return porderNo;
	}

	public void setPorderNo(String porderNo) {
		this.porderNo = porderNo;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getTotalQuan2() {
		return totalQuan2;
	}

	public void setTotalQuan2(String totalQuan2) {
		this.totalQuan2 = totalQuan2;
	}

	public String getTotalQuan1() {
		return totalQuan1;
	}

	public void setTotalQuan1(String totalQuan1) {
		this.totalQuan1 = totalQuan1;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephonenumber() {
		return telephonenumber;
	}

	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}

	ArrayList<String> s1 = new ArrayList<String>();

	public ArrayList<String> getS1() {
		return s1;
	}

	public void setS1(ArrayList<String> s1) {
		this.s1 = s1;
	}

	public List<I0019> getResul() {
		return resul;
	}

	public void setResul(List<I0019> resul) {
		this.resul = resul;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSalereferencenumber() {
		return salereferencenumber;
	}

	public void setSalereferencenumber(String salereferencenumber) {
		this.salereferencenumber = salereferencenumber;
	}

	List<I0021> result;
	List<PurchaseOrder> resulfinal1 = null;

	public List<PurchaseOrder> getResulfinal1() {
		return resulfinal1;
	}

	public void setResulfinal1(List<PurchaseOrder> resulfinal1) {
		this.resulfinal1 = resulfinal1;
	}

	public String getFlag3() {
		return flag3;
	}

	public void setFlag3(String flag3) {
		this.flag3 = flag3;
	}

	public String getTempValidate() {
		return tempValidate;
	}

	public void setTempValidate(String tempValidate) {
		this.tempValidate = tempValidate;
	}

	public String getFlag4() {
		return flag4;
	}

	public void setFlag4(String flag4) {
		this.flag4 = flag4;
	}

	private String flag3 = "none";
	private String quantity1;

	private String tempValidate;
	private String flag4 = "none";

	public List<I0021> getResult() {
		return result;
	}

	public void setResult(List<I0021> result) {
		this.result = result;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTflag1() {
		return tflag1;
	}

	public void setTflag1(String tflag1) {
		this.tflag1 = tflag1;
	}

	public String getTflag2() {
		return tflag2;
	}

	public void setTflag2(String tflag2) {
		this.tflag2 = tflag2;
	}

	private String roll;
	private ArrayList<String> rollList;
	private String rflag1;
	private String rflag2;

	public String cashflag = "none";

	public String getCashflag() {
		return cashflag;
	}

	public void setCashflag(String cashflag) {
		this.cashflag = cashflag;
	}

	public String getCardflag() {
		return cardflag;
	}

	public void setCardflag(String cardflag) {
		this.cardflag = cardflag;
	}

	public String getGiroflag() {
		return giroflag;
	}

	public void setGiroflag(String giroflag) {
		this.giroflag = giroflag;
	}

	public String getTransflag() {
		return transflag;
	}

	public void setTransflag(String transflag) {
		this.transflag = transflag;
	}

	public String cardflag = "none";
	public String giroflag = "none";
	public String transflag = "none";

	public String getRflag1() {
		return rflag1;
	}

	public void setRflag1(String rflag1) {
		this.rflag1 = rflag1;
	}

	public String getRflag2() {
		return rflag2;
	}

	public void setRflag2(String rflag2) {
		this.rflag2 = rflag2;
	}

	public String tots;

	public String getTots() {
		return tots;
	}

	public void setTots(String tots) {
		this.tots = tots;
	}

	public String getDiscountzType() {
		return discountzType;
	}

	public void setDiscountzType(String discountzType) {
		this.discountzType = discountzType;
	}

	public String getDiscountz() {
		return discountz;
	}

	public void setDiscountz(String discountz) {
		this.discountz = discountz;
	}

	public String getDiscountzAmount() {
		return discountzAmount;
	}

	public void setDiscountzAmount(String discountzAmount) {
		this.discountzAmount = discountzAmount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getValidate1() {
		return validate1;
	}

	public void setValidate1(String validate1) {
		this.validate1 = validate1;
	}

	public String currentDate;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public List<I0019> getBarcode() {
		return barcode;
	}

	public void setBarcode(List<I0019> barcode) {
		this.barcode = barcode;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getBatchProductName() {
		return batchProductName;
	}

	public void setBatchProductName(String batchProductName) {
		this.batchProductName = batchProductName;
	}

	public List<String> getBatchProductName3() {
		return batchProductName3;
	}

	public void setBatchProductName3(List<String> batchProductName3) {
		this.batchProductName3 = batchProductName3;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getMarginPrice() {
		return marginPrice;
	}

	public void setMarginPrice(String marginPrice) {
		this.marginPrice = marginPrice;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public List<String> getRollList() {
		return rollList;
	}

	public void setRollList(ArrayList<String> rollList) {
		this.rollList = rollList;
	}

	DemoController controller = null;

	public void nameChange(ValueChangeEvent ve) {
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			logger.debug(">>>>>>>>>>>>>>>>>>>>" + ve.getNewValue());
			purchaseOrder.setBatchProductName(ve.getNewValue().toString());
			controller.getpurchaseInfo(purchaseOrder);
			sellingPrice = purchaseOrder.getSellingPrice();
			marginPrice = purchaseOrder.getMarginPrice();

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
	}

	public void quantityChange(ValueChangeEvent ve) {
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String s = ve.getNewValue().toString();
			int ii = Integer.parseInt(s);
			String temp = "0";
			temp = ""
					+ new BigDecimal(temp).add((BigDecimal.valueOf(ii)
							.multiply(new BigDecimal(sellingPrice))));
			totalPrice = temp + "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
	}

	public int productId;
	public String crosstotal1;
	public String orderNumber;
	public String accountType;

	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCrosstotal1() {
		return crosstotal1;
	}

	public void setCrosstotal1(String crosstotal1) {
		this.crosstotal1 = crosstotal1;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String quicksaleSubmit() {
		try {
			logger.debug("-------------------inside quick sales-------------------");
			setValidate("");
			purchaseOrder.setCrosstotal1("0");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			// barcode=controller.getBarCodeData(batchProductName);
			// int count=barcode.size();
			// int qua=Integer.parseInt(quantity);
			/*
			 * if(qua>count) { validate=
			 * "You have entered the quantity is above in the stock.Check your stock"
			 * ; } else {
			 */

			if (batchProductName.equals("")) {
				throw new DemoException("enter  product name");
			} else if (quantity.equals("")) {
				throw new DemoException("enter  Quantity");
			} else if (sellingPrice.equalsIgnoreCase("")) {
				throw new DemoException("enter  selling price");
			} else if (totalPrice.equals("")) {
				throw new DemoException("enter  total price");
			} else if (paymentType.equals("")) {
				throw new DemoException("enter  payment type");
			}

			logger.debug("1");
			purchaseOrder.setQuantity(quantity);
			purchaseOrder.setCrosstotal(totalPrice);
			purchaseOrder.setBatchProductName(batchProductName);
			purchaseOrder.setStatus(paymentType);
			purchaseOrder.setQuantity(quantity);
			purchaseOrder.setBatchProductName(batchProductName);
			logger.debug("2");
			controller.saveSales(purchaseOrder);
			logger.debug("3");
			marginPrice = purchaseOrder.getMarginPrice();
			setSellingPrice(purchaseOrder.getSellingPrice());
			setProductId(purchaseOrder.getProduct_ID());
			setCrosstotal1(purchaseOrder.getCrosstotal1());
			setOrderNumber("" + purchaseOrder.getSalesId());
			logger.debug("crosstotal in mb close::::::"
					+ purchaseOrder.getCrosstotal1());

			logger.debug("after");
			/* } */

			logger.debug("pkid::::::::::" + purchaseOrder.getSalesId());
			purchaseOrder.setQuantity(quantity);
			purchaseOrder.setBatchProductName(batchProductName);
			if (purchaseOrder.getBatchProductName().equalsIgnoreCase("")
					|| purchaseOrder.getQuantity().equalsIgnoreCase("")) {
				throw new DemoException("please fill all the fields.......");
			}
			controller.qucikSalesConform(purchaseOrder);
			setSaleOrderNumber(purchaseOrder.getSalesIdReference());
			totalPrice = "" + purchaseOrder.getCrosstotal1();
			logger.debug("sale order reference number------------------->"
					+ saleOrderNumber);
			logger.debug("after");

			return "quickSaleFormsuccess";
		} catch (DemoException ie) {
			logger.debug("--------------inside exception--------------");
			setValidate(ie.getMessage());
			return "";
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
			return "";
		} finally {
			/*
			 * batchProductName=null; quantity=null; sellingPrice=0;
			 * marginPrice=0; totalPrice=null; paymentType=null;
			 */
		}

	}

	/*
	 * I0021 i0021=null;
	 * 
	 * public I0021 getI0021() { return i0021; } public void setI0021(I0021
	 * i0021) { this.i0021 = i0021; } public QuickSaleMB() {
	 * 
	 * logger.debug("----------------inside qucik sale-----------------");
	 * i0021=new I0021();
	 * 
	 * 
	 * 
	 * }
	 */

	public String saleOrderNumber;

	public String getSaleOrderNumber() {
		return saleOrderNumber;
	}

	public void setSaleOrderNumber(String saleOrderNumber) {
		this.saleOrderNumber = saleOrderNumber;
	}

	public String quickSalesConfirm() {

		return "quickSalesConfirmSuccess";

	}

	public String quicksaleSubmit1() {
		try {
			logger.debug("-------------------inside quick sales-------------------");
			setValidate("");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			// barcode=controller.getBarCodeData(batchProductName);
			// int count=barcode.size();
			// int qua=Integer.parseInt(quantity);
			/*
			 * if(qua>count) { validate=
			 * "You have entered the quantity is above in the stock.Check your stock"
			 * ; } else {
			 */

			if (batchProductName.equals("")) {
				throw new DemoException("enter  product name");
			} else if (quantity.equals("")) {
				throw new DemoException("enter  Quantity");
			} else if (sellingPrice.equalsIgnoreCase("")) {
				throw new DemoException("enter  selling price");
			} else if (totalPrice.equals("")) {
				throw new DemoException("enter  total price");
			} else if (paymentType.equals("")) {
				throw new DemoException("enter  payment type");
			}

			logger.debug("1");
			purchaseOrder.setQuantity(quantity);
			purchaseOrder.setCrosstotal(totalPrice);
			purchaseOrder.setBatchProductName(batchProductName);
			purchaseOrder.setStatus(paymentType);
			purchaseOrder.setQuantity(quantity);
			purchaseOrder.setBatchProductName(batchProductName);
			logger.debug("2");
			controller.saveSales(purchaseOrder);
			logger.debug("3");
			marginPrice = purchaseOrder.getMarginPrice();
			setSellingPrice(purchaseOrder.getSellingPrice());
			setProductId(purchaseOrder.getProduct_ID());
			setCrosstotal1(purchaseOrder.getCrosstotal1());
			setOrderNumber("" + purchaseOrder.getSalesId());
			logger.debug("crosstotal in mb close::::::"
					+ purchaseOrder.getCrosstotal1());

			logger.debug("after");
			/* } */

			logger.debug("pkid::::::::::" + purchaseOrder.getSalesId());
			purchaseOrder.setQuantity(quantity);
			purchaseOrder.setBatchProductName(batchProductName);
			if (purchaseOrder.getBatchProductName().equalsIgnoreCase("")
					|| purchaseOrder.getQuantity().equalsIgnoreCase("")) {
				throw new DemoException("please fill all the fields.......");
			}
			controller.qucikSalesConform1(purchaseOrder);

			logger.debug("after");
			totalPrice = "" + purchaseOrder.getCrosstotal1();
			return "quickSaleFormsuccess";
		} catch (DemoException ie) {
			logger.debug("--------------inside exception--------------");
			setValidate(ie.getMessage());
			return "";
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
			return "";
		} finally {
			/*
			 * batchProductName=null; quantity=null; sellingPrice=0;
			 * marginPrice=0; totalPrice=null; paymentType=null;
			 */
		}

	}

	public String quicksaleSubmit2() {
		batchProductName = null;
		quantity = null;
		sellingPrice = "";
		marginPrice = "";
		totalPrice = null;
		paymentType = null;
		return "quickSaleForm.xhtml";
	}

	ArrayList<QuickSaleMB> homeMBs = new ArrayList<QuickSaleMB>();
	public String serialno;
	public String productName;
	public String flag;
	public String flag1;
	public String flag2;

	public ArrayList<QuickSaleMB> getHomeMBs() {
		return homeMBs;
	}

	public void setHomeMBs(ArrayList<QuickSaleMB> homeMBs) {
		this.homeMBs = homeMBs;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String price;
	public String netAmount;

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	private List<String> accounttypeList=null;
	
	

	public List<String> getAccounttypeList() {
		return accounttypeList;
	}

	public void setAccounttypeList(List<String> accounttypeList) {
		this.accounttypeList = accounttypeList;
	}

	public String quickSalescall() {
		setValidate(null);
		setValidate2(null);
		List<ChartOfAccount> accountlist=null;
		try {
			accounttypeList=new ArrayList<String>();
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			accountlist=controller.accountlist(clientID);
			 if(accountlist.size()>0){
				 for (int i = 0; i < accountlist.size(); i++) {
					if(accountlist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Bank") || 
							accountlist.get(i).getAccountType().getCategoryType().equalsIgnoreCase("Other Current Assets")){
						accounttypeList.add(accountlist.get(i).getAccountName());
					}
				 }
			 }
			logger.debug("before");
			setBatchProductName3(controller.getProductName());
			Collections.sort(batchProductName3);
			logger.debug("after");
			logger.debug("inside reference");
			setDiscAmnt(null);
			setDiscType(null);
			if (homeMBs.size() > 0) {
				logger.debug("innnnnnn");
				homeMBs.clear();
				for (int i = 1; i <= 5; i++) {
					QuickSaleMB homeMB = new QuickSaleMB();
					homeMB.setSerialno("" + i);
					homeMB.setProductName("");
					homeMB.setFlag("1");
					homeMB.setFlag1("1");
					homeMB.setFlag2("none");
					homeMB.setBarflag("1");
					homeMB.setBarflag1("none");
					homeMB.setBarcode1("");
					homeMB.setDicount("");
					homeMBs.add(homeMB);
				}

				productName = null;

			} else {
				logger.debug("ouuuuuuuuuuuuuuu");
				for (int i = 1; i <= 5; i++) {
					QuickSaleMB homeMB = new QuickSaleMB();
					homeMB.setSerialno("" + i);
					homeMB.setProductName("");
					homeMB.setFlag("1");
					homeMB.setFlag1("1");
					homeMB.setFlag2("none");
					homeMB.setDicount("");
					homeMB.setBarflag("1");
					homeMB.setBarflag1("none");
					homeMB.setBarcode1("");
					homeMBs.add(homeMB);
				}

				productName = null;

			}
			controller.salesDelete();
			/* return "QucikSuccess"; */
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			/* return "QucikSuccess"; */
			return "";
		} finally {
			paymentType = null;
			dicount = null;
			dicountAmount = null;
			totalPrice = null;
		}
	}

	public String redirectHome2() {
		try {
			price = "";
			productName = "";
			logger.debug("list size----------------->" + homeMBs.size());
			int j = 0;
			j = homeMBs.size();
			int i = 0;
			/*
			 * if(Integer.parseInt(homeMBs.get(j-1).getQuantity())!=0) {
			 */
			validate = "";
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setSerialno("" + (homeMBs.size() + 1));
			homeMB.setProductName("");
			homeMB.setFlag("1");
			homeMB.setFlag1("1");
			homeMB.setFlag2("none");
			homeMB.setBarflag1("none");
			homeMB.setBarflag("1");
			homeMB.setBarcode1("");
			homeMBs.add(homeMB);
			/*
			 * } else { validate="* Enter quantity"; }
			 */
			logger.debug("count-->" + i);
			logger.debug("list size----------------->" + homeMBs.size());
			logger.debug("---------------inside redirect mb1--------------");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";
	}

	List<String> price1 = null;

	public List<String> getPrice1() {
		return price1;
	}

	public void setPrice1(List<String> price1) {
		this.price1 = price1;
	}

	public void priceChange(ValueChangeEvent values) {
		String serialNo = "";
		try {
			serialNo = values.getComponent().getAttributes().get("serial2")
					.toString();
			String pName = values.getComponent().getAttributes()
					.get("product2").toString();
			String barcode = values.getComponent().getAttributes()
					.get("barcode").toString();
			String price_val = values.getNewValue().toString();
			if ((pName.equalsIgnoreCase(""))
					|| (pName.equalsIgnoreCase("select"))) {
				throw new DemoException("Please select the product name");
			}
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setSerialno(serialNo);
			homeMB.setPrice(price_val);
			homeMB.setFlag("none");
			homeMB.setFlag3("1");
			homeMB.setUflag("1");
			homeMB.setTflag1("1");
			homeMB.setTflag2("none");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			homeMB.setUnit(unit);
			homeMB.setProductName(pName);
			homeMB.setDicount("");
			homeMB.setBarflag("none");
			homeMB.setBarflag1("1");
			homeMB.setBarcode1(barcode);
			homeMB.setRollList(rollList);
			homeMB.setDicountAmount("");
			logger.debug("home size---------->" + homeMBs.size());
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
		} catch (DemoException in) {
			setValidate(in.getMessage());
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setSerialno(serialNo);
			// homeMB.setPrice("");
			homeMB.setFlag("1");
			homeMB.setUflag("none");
			homeMB.setTflag1("1");
			homeMB.setTflag2("none");
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setUnit(unit);
			homeMB.setProductName("");
			homeMB.setBarflag1("none");
			homeMB.setBarflag("1");
			homeMB.setBarcode1("");
			homeMB.setDicount("");
			homeMB.setRollList(null);
			homeMB.setDicountAmount("");
			logger.debug("home size---------->" + homeMBs.size());
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
		}
	}

	/*
	 * public void valueChange(ValueChangeEvent v) { String serialNo=""; try {
	 * setValidate("");
	 * logger.debug("value---------->"+v.getNewValue().toString());
	 * productName=""+v.getNewValue();
	 * logger.debug("inside value change-----------------"+productName);
	 * 
	 * ApplicationContext ctx=null;
	 * ctx=FacesContextUtils.getWebApplicationContext
	 * (FacesContext.getCurrentInstance());
	 * logger.debug("---------------inside mb------------"); controller =
	 * (InventoryController) ctx.getBean("controller");
	 * serialNo=v.getComponent().getAttributes().get("serial").toString();
	 * purchaseOrder.setBatchProductName(productName);
	 * controller.getpurchaseInfo(purchaseOrder);
	 * setPrice(""+GenerateEmployee.numberFormat.format(new
	 * BigDecimal(purchaseOrder.getSellingPrice())));
	 * setUnit(""+purchaseOrder.getUnit());
	 * marginPrice=purchaseOrder.getMarginPrice();
	 * barcode1=purchaseOrder.getBarcode1();
	 * logger.debug("Inside----------------if"); rollList=(ArrayList<String>)
	 * controller.getRollList(productName,rollList);
	 * rollList=(ArrayList<String>)
	 * controller.getRollQuanList(productName,rollList); if(rollList.size()==0)
	 * { logger.debug("inside empty Rolls......"); throw new
	 * InventoryException("No Roll there please select another Product"); }
	 * logger.debug("list"+getRollList()); QuickSaleMB homeMB=new QuickSaleMB();
	 * homeMB.setSerialno(serialNo); //homeMB.setPrice(price);
	 * homeMB.setFlag("none"); homeMB.setUflag("1"); homeMB.setTflag1("1");
	 * homeMB.setTflag2("none"); homeMB.setPriflag1("1");
	 * homeMB.setPriflag2("none"); homeMB.setUnit(unit);
	 * homeMB.setProductName(productName); homeMB.setDicount("");
	 * homeMB.setRollList(rollList); homeMB.setDicountAmount("");
	 * homeMB.setBarflag("none"); homeMB.setBarflag1("1");
	 * homeMB.setBarcode1(barcode1);
	 * logger.debug("home size---------->"+homeMBs.size());
	 * homeMBs.set((Integer.parseInt(serialNo)-1), homeMB);
	 * controller.getProductName(); for(int i=0;i<homeMBs.size();i++) {
	 * logger.debug("<=============inside for loop=========>");
	 * logger.debug("serial num---->"+homeMBs.get(i).getSerialno());
	 * logger.debug("price---->"+homeMBs.get(i).getPrice());
	 * logger.debug("flag---->"+homeMBs.get(i).getFlag());
	 * logger.debug("Product Name---->"+homeMBs.get(i).getProductName()); } }
	 * catch(InventoryException e) { setValidate(e.getMessage());
	 * 
	 * 
	 * } }
	 */

	public ArrayList<QuickSaleMB> roll1 = new ArrayList<QuickSaleMB>();
	public ArrayList<QuickSaleMB> roll2;

	public ArrayList<QuickSaleMB> getRoll1() {
		return roll1;
	}

	public void setRoll1(ArrayList<QuickSaleMB> roll1) {
		this.roll1 = roll1;
	}

	public ArrayList<QuickSaleMB> getRoll2() {
		return roll2;
	}

	public void setRoll2(ArrayList<QuickSaleMB> roll2) {
		this.roll2 = roll2;
	}

	public String innerserial1;
	public String innerserial2;
	public String serialno1;

	public String getSerialno1() {
		return serialno1;
	}

	public void setSerialno1(String serialno1) {
		this.serialno1 = serialno1;
	}

	public String getInnerserial1() {
		return innerserial1;
	}

	public void setInnerserial1(String innerserial1) {
		this.innerserial1 = innerserial1;
	}

	public String getInnerserial2() {
		return innerserial2;
	}

	public void setInnerserial2(String innerserial2) {
		this.innerserial2 = innerserial2;
	}

	public void rollQuanChange(ValueChangeEvent values) {
		String serialNo = "";
		try {
			String quantity1 = (String) values.getNewValue();

			serialNo = values.getComponent().getAttributes().get("serial2")
					.toString();
			String pName = values.getComponent().getAttributes()
					.get("product2").toString();
			String barcode = values.getComponent().getAttributes()
					.get("barcode").toString();
			String price = values.getComponent().getAttributes().get("price")
					.toString();
			if ((pName.equalsIgnoreCase(""))
					|| (pName.equalsIgnoreCase("select"))) {
				throw new DemoException("Please select the product name");
			}
			if (!quantity1.equals("")) {
				String res = VendorRegisterFormMB.isNumberValid(quantity1);
				if (res.equalsIgnoreCase("Match")) {
					logger.debug("valid Quantity");
				} else {
					throw new DemoException("*Enter Integer values");
				}
			}

			int quant = Integer.parseInt(quantity1);

			BigDecimal bd1 = new BigDecimal(rollList.size());
			BigDecimal bd2 = new BigDecimal(quant);
			if (bd1.compareTo(bd2) == -1) {
				throw new DemoException("*Only " + bd1
						+ " rolls are available for this product");
			}

			roll1.clear();
			roll2 = new ArrayList<QuickSaleMB>();

			for (int i = 1; i <= quant; i++) {
				QuickSaleMB soMB = new QuickSaleMB();
				soMB.setSerialno1("" + i);
				soMB.setInnerserial1("" + i);
				soMB.setRoll("");
				soMB.setQuantity("");
				roll2.add(soMB);
				logger.debug("serial no-->> inner " + soMB.getInnerserial1());
			}
			logger.debug("Size " + roll2.size());
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setSerialno(serialNo);
			homeMB.setPrice(price);
			homeMB.setFlag("none");
			homeMB.setUflag("1");
			homeMB.setTflag1("1");
			homeMB.setTflag2("none");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			homeMB.setUnit(unit);
			homeMB.setFlag3("1");
			homeMB.setProductName(pName);
			homeMB.setBarcode1(barcode);
			homeMB.setBarflag("none");
			homeMB.setBarflag1("1");
			homeMB.setDicount("");
			homeMB.setRollList(rollList);
			homeMB.setDicountAmount("");
			homeMB.setRollQFlag1("none");
			homeMB.setRollQFlag2("1");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			homeMB.setRoll1(roll2);
			logger.debug("home size---------->" + homeMBs.size());
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
		} catch (DemoException in) {
			setValidate(in.getMessage());
			/*
			 * SalesOrderFormMB homeMB=new SalesOrderFormMB();
			 * homeMB.setSerialno(serialNo); homeMB.setPrice(price);
			 * homeMB.setFlag("1"); homeMB.setUflag("none");
			 * homeMB.setTflag1("1"); homeMB.setTflag2("none");
			 * homeMB.setPriflag1("1"); homeMB.setPriflag2("none");
			 * homeMB.setUnit(unit); homeMB.setProductName("");
			 * homeMB.setDicount(""); homeMB.setBarcode1("");
			 * homeMB.setBarflag1("none"); homeMB.setBarflag("1");
			 * homeMB.setRollList(null); homeMB.setDicountAmount("");
			 * homeMB.setRollQFlag1("1"); homeMB.setRollQFlag2("none");
			 * logger.debug("home size---------->"+homeMBs.size());
			 * homeMBs.set((Integer.parseInt(serialNo)-1), homeMB);
			 */
		}
	}

	/*
	 * public void valueChange(ValueChangeEvent v) { String serialNo=""; try {
	 * setValidate("");
	 * logger.debug("value---------->"+v.getNewValue().toString());
	 * productName=""+v.getNewValue();
	 * logger.debug("inside value change-----------------"+productName);
	 * 
	 * ApplicationContext ctx=null;
	 * ctx=FacesContextUtils.getWebApplicationContext
	 * (FacesContext.getCurrentInstance());
	 * logger.debug("---------------inside mb------------"); controller =
	 * (InventoryController) ctx.getBean("controller");
	 * serialNo=v.getComponent().getAttributes().get("serial").toString();
	 * purchaseOrder.setBatchProductName(productName);
	 * controller.getpurchaseInfo(purchaseOrder);
	 * barcode1=purchaseOrder.getBarcode1();
	 * setUnit(""+purchaseOrder.getUnit());
	 * marginPrice=""+purchaseOrder.getMarginPrice();
	 * logger.debug("purchaseOrder.getUnit()--------------if"
	 * +purchaseOrder.getUnit()); rollList=(ArrayList<String>)
	 * controller.getRollQuanList(productName,rollList); if(rollList.size()==0)
	 * { logger.debug("inside empty Rolls......"); throw new
	 * InventoryException("No Roll there please select another Product"); }
	 * logger.debug("list"+getRollList()); QuickSaleMB homeMB=new QuickSaleMB();
	 * homeMB.setSerialno(serialNo); homeMB.setPrice(price);
	 * homeMB.setFlag("none"); homeMB.setProductName(productName);
	 * homeMB.setPriflag1("1"); homeMB.setPriflag2("none");
	 * homeMB.setRollList(rollList); homeMB.setTflag1("1");
	 * homeMB.setTflag2("none"); homeMB.setBarflag("none");
	 * homeMB.setBarflag1("1"); homeMB.setBarcode1(barcode1);
	 * homeMB.setUnit(unit);
	 * logger.debug("home size---------->"+homeMBs.size());
	 * homeMBs.set((Integer.parseInt(serialNo)-1), homeMB);
	 * controller.getProductName(); for(int i=0;i<homeMBs.size();i++) {
	 * logger.debug("<=============inside for loop=========>");
	 * logger.debug("serial num---->"+homeMBs.get(i).getSerialno());
	 * logger.debug("price---->"+homeMBs.get(i).getPrice());
	 * logger.debug("flag---->"+homeMBs.get(i).getFlag());
	 * logger.debug("Product Name---->"+homeMBs.get(i).getProductName()); } }
	 * catch(InventoryException e) { logger.error("Inside Exception",e); } }
	 */
	public void valueChange(ValueChangeEvent v) {
		String serialNo = "";
		try {
			setValidate("");
			logger.debug("value---------->" + v.getNewValue().toString());
			productName = "" + v.getNewValue();
			logger.debug("inside value change-----------------" + productName);

			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			logger.debug("---------------inside mb------------");
			controller = (DemoController) ctx.getBean("controller");
			serialNo = v.getComponent().getAttributes().get("serial")
					.toString();
			purchaseOrder.setBatchProductName(productName);
			controller.getpurchaseInfo(purchaseOrder);
			barcode1 = purchaseOrder.getBarcode1();
			setUnit("" + purchaseOrder.getUnit());
			marginPrice = "" + purchaseOrder.getMarginPrice();
			price = purchaseOrder.getSellingPrice();
			logger.debug("purchaseOrder.getUnit()--------------if"
					+ purchaseOrder.getUnit());
			rollList = (ArrayList<String>) controller.getRollQuanList(
					productName, rollList);
			if (rollList.size() == 0) {
				logger.debug("inside empty Rolls......");
				throw new DemoException(
						"There is no stock for this Product. Please select another Product");
			}
			logger.debug("list" + getRollList());
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setSerialno(serialNo);
			homeMB.setPrice(price);
			homeMB.setFlag("none");
			homeMB.setProductName(productName);
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setRollList(rollList);
			homeMB.setTflag1("1");
			homeMB.setTflag2("none");
			homeMB.setFlag3("1");
			homeMB.setBarflag("none");
			homeMB.setBarflag1("1");
			homeMB.setBarcode1(barcode1);
			homeMB.setUnit(unit);
			logger.debug("home size---------->" + homeMBs.size());
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
			purchaseOrder.setTotalnumberofcount("" + rollList.get(0));
			logger.debug("TOT QUAN -->> "
					+ purchaseOrder.getTotalnumberofcount());

			controller.getProductName();
			for (int i = 0; i < homeMBs.size(); i++) {
				logger.debug("<=============inside for loop=========>");
				logger.debug("serial num---->" + homeMBs.get(i).getSerialno());
				logger.debug("price---->" + homeMBs.get(i).getPrice());
				logger.debug("flag---->" + homeMBs.get(i).getFlag());
				logger.debug("Product Name---->"
						+ homeMBs.get(i).getProductName());
			}
			purchaseOrder.setTotalQuan2("0");
			BigDecimal bb = BigDecimal.valueOf(0);
			for (int i = 0; i < homeMBs.size(); i++) {
				if (!homeMBs.get(i).getProductName().equalsIgnoreCase("")) {
					if (homeMBs.get(i).getProductName()
							.equalsIgnoreCase(productName)) {
						try{
							if (!homeMBs.get(i).getQuantity().equalsIgnoreCase("")) {
								bb = bb.add(new BigDecimal(homeMBs.get(i)
										.getQuantity()));
								purchaseOrder.setTotalQuan2("" + bb);
								logger.debug(" -->> same prod quan " + productName
										+ " / " + bb);
							}
						}catch(Exception e){
							purchaseOrder.setTotalQuan2("" + bb);
						}						
					}
				}
			}
		} catch (DemoException e) {
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setSerialno(serialNo);
			homeMB.setFlag3("none");
			homeMB.setFlag("1");
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
		}
	}

	public void rollChange(ValueChangeEvent rollval) {
		String serialNo = "";
		String productname = "";
		String barcode = "";
		String price = "";
		try {
			serialNo = rollval.getComponent().getAttributes().get("serial2")
					.toString();
			productname = rollval.getComponent().getAttributes()
					.get("product2").toString();
			barcode = rollval.getComponent().getAttributes().get("barcode")
					.toString();
			price = rollval.getComponent().getAttributes().get("price")
					.toString();
			setValidate("");
			roll = "" + rollval.getNewValue();

			if (productname == null || productname.equalsIgnoreCase("")
					|| productname.contains("Select")
					|| productname.equalsIgnoreCase("Select")) {
				throw new DemoException("*Enter the Product Name");
			} else if (price.equalsIgnoreCase("")) {
				throw new DemoException("*Enter the Price");
			}
			purchaseOrder.setRollID(roll);
			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.getRollQuantity(purchaseOrder);
			logger.debug("values added" + purchaseOrder.getRollStockIn());
			BigDecimal sto = new BigDecimal(purchaseOrder.getRollStockIn());
			sto = sto.setScale(2, RoundingMode.CEILING);
			// setStockin(String.valueOf(purchaseOrder.getRollStockIn()));
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setSerialno(serialNo);
			homeMB.setPrice(price);
			homeMB.setFlag("none");
			homeMB.setStockin(sto.toString());
			homeMB.setUflag("1");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			homeMB.setProductName(productname);
			homeMB.setDicount("");
			homeMB.setRoll(roll);
			homeMB.setRflag1("none");
			homeMB.setTflag1("1");
			homeMB.setTflag2("none");
			homeMB.setBarflag("none");
			homeMB.setBarflag1("1");
			homeMB.setBarcode1(barcode);
			homeMB.setUnit(unit);
			homeMB.setRflag2("1");
			homeMB.setDicountAmount("");
			logger.debug("home size---------->" + homeMBs.size());
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);

		} catch (DemoException e) {
			setValidate(e.getMessage());
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setSerialno(serialNo);
			// homeMB.setPrice("");
			homeMB.setFlag("1");
			homeMB.setUflag("none");
			homeMB.setTflag1("1");
			homeMB.setTflag2("none");
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setUnit(unit);
			homeMB.setProductName("");
			homeMB.setDicount("");
			homeMB.setRoll("");
			homeMB.setStockin("");
			homeMB.setBarflag1("none");
			homeMB.setBarflag("1");
			homeMB.setBarcode1("");
			homeMB.setDicountAmount("");
			homeMB.setRflag2("none");
			homeMB.setRflag1("1");
			logger.debug("home size---------->" + homeMBs.size());
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
		} catch (NullPointerException n) {
			// logger.error("Inside Exception",e);
			setValidate(n.getMessage());
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setSerialno(serialNo);
			// homeMB.setPrice("");
			homeMB.setFlag("1");
			homeMB.setUflag("none");
			homeMB.setTflag1("1");
			homeMB.setTflag2("none");
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setStockin("");
			homeMB.setUnit(unit);
			homeMB.setProductName("");
			homeMB.setBarflag1("none");
			homeMB.setBarflag("1");
			homeMB.setBarcode1("");
			homeMB.setDicount("");
			homeMB.setRoll("");
			homeMB.setDicountAmount("");
			homeMB.setRflag2("none");
			homeMB.setRflag1("1");
			logger.debug("home size---------->" + homeMBs.size());
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	/*
	 * public void quantityChange1(ValueChangeEvent vi) { String serialNo="";
	 * serialNo=vi.getComponent().getAttributes().get("serial").toString();
	 * String roll=vi.getComponent().getAttributes().get("rollL").toString();
	 * String price=vi.getComponent().getAttributes().get("price").toString();
	 * logger.debug("------------inside quantityChange-------------"+roll); try
	 * { logger.debug("Size--------------------"+homeMBs.size());
	 * if(homeMBs.size()>0){ for(int i=0; i<homeMBs.size();i++){
	 * logger.debug("product size "+" "+i+batchProductName3.size());
	 * logger.debug(batchProductName3); } } setValidate("");
	 * logger.debug("value---------->"+vi.getNewValue());
	 * quantity=""+vi.getNewValue(); BigDecimal qty= new BigDecimal(quantity);
	 * qty=qty.setScale(2, RoundingMode.CEILING);
	 * logger.debug("Vales for celing"+qty);
	 * logger.debug("inside value change-----------------"+quantity);
	 * logger.debug
	 * ("value-------------product name--->"+(String)vi.getComponent(
	 * ).getAttributes().get("ram")); ApplicationContext ctx=null;
	 * ctx=FacesContextUtils
	 * .getWebApplicationContext(FacesContext.getCurrentInstance()); controller
	 * = (InventoryController) ctx.getBean("controller");
	 * 
	 * logger.debug("serial number------------>"+serialNo);
	 * logger.debug("product name------------>"
	 * +homeMBs.get(Integer.parseInt(serialNo)-1).getProductName());
	 * logger.debug
	 * ("selling price--------------->"+homeMBs.get(Integer.parseInt(
	 * serialNo)-1).getPrice());
	 * logger.debug("Roll ID--------------->"+homeMBs.get
	 * (Integer.parseInt(serialNo)-1).getRoll());
	 * purchaseOrder.setRollID(homeMBs
	 * .get(Integer.parseInt(serialNo)-1).getRoll());
	 * controller.getRollQuantity(purchaseOrder);
	 * logger.debug("values added"+purchaseOrder.getRollStockIn()); //float
	 * stock=(float) 0.0;
	 * 
	 * //float quantt=(float) 0.0;
	 * 
	 * 
	 * 
	 * purchaseOrder.setQuantity(qty.toString()); BigDecimal stock= new
	 * BigDecimal(purchaseOrder.getRollStockIn()); stock=stock.setScale(2,
	 * RoundingMode.CEILING); BigDecimal quantt= new
	 * BigDecimal(purchaseOrder.getQuantity()); quantt=quantt.setScale(2,
	 * RoundingMode.CEILING); int comp1=stock.compareTo(quantt);
	 * logger.debug("stock"+stock); logger.debug("quantt"+quantt);
	 * purchaseOrder.
	 * setBatchProductName(""+homeMBs.get(Integer.parseInt(serialNo
	 * )-1).getProductName());
	 * if(purchaseOrder.getBatchProductName().equalsIgnoreCase("")) { throw new
	 * InventoryException("*Select product name"); } else
	 * if(price.equalsIgnoreCase("")) { throw new
	 * InventoryException("*Enter the Price"); }else
	 * if((roll.equalsIgnoreCase("")) || (roll.equalsIgnoreCase("Select"))) {
	 * throw new InventoryException("*Select the Roll"); } else
	 * if(purchaseOrder.getQuantity().equalsIgnoreCase("")) { throw new
	 * InventoryException("*Enter the quantity"); }else if(comp1 == -1){ throw
	 * new InventoryException("*Quantity cannot be more than "+stock+
	 * " for this roll, please try again"); }
	 * 
	 * purchaseOrder.setCategory(homeMBs.get(Integer.parseInt(serialNo)-1).
	 * getDicount()); controller.salesOrder4(purchaseOrder);
	 * logger.debug("--------------after if loop------------------");
	 * if(homeMBs.size()>0) { String amunt1="";
	 * logger.debug("-------------------inside if loop-----------------");
	 * QuickSaleMB homeMB=new QuickSaleMB(); logger.debug("1");
	 * homeMB.setSerialno(serialNo); logger.debug("2");
	 * homeMB.setPrice(homeMBs.get(Integer.parseInt(serialNo)-1).getPrice());
	 * logger.debug("3"); homeMB.setFlag("none"); homeMB.setFlag1("none");
	 * homeMB.setFlag2("1"); homeMB.setUflag("1"); homeMB.setPriflag1("none");
	 * homeMB.setPriflag2("1"); homeMB.setRflag1("none");
	 * homeMB.setBarflag1("1"); homeMB.setBarflag("none");
	 * homeMB.setBarcode1(homeMBs
	 * .get(Integer.parseInt(serialNo)-1).getBarcode1()); homeMB.setRflag2("1");
	 * homeMB.setTflag1("none"); homeMB.setTflag2("1");
	 * homeMB.setUnit(homeMBs.get(Integer.parseInt(serialNo)-1).getUnit());
	 * homeMB.setRoll(homeMBs.get(Integer.parseInt(serialNo)-1).getRoll());
	 * logger.debug("4");
	 * homeMB.setProductName(""+homeMBs.get(Integer.parseInt(serialNo
	 * )-1).getProductName()); logger.debug("5");
	 * homeMB.setStockin(homeMBs.get(Integer
	 * .parseInt(serialNo)-1).getStockin()); homeMB.setQuantity(qty.toString());
	 * logger.debug("6");
	 * homeMB.setDicount(homeMBs.get(Integer.parseInt(serialNo
	 * )-1).getDicount()); logger.debug("7");
	 * homeMB.setDicountAmount(homeMBs.get
	 * (Integer.parseInt(serialNo)-1).getDicountAmount());
	 * logger.debug(homeMBs.get
	 * (Integer.parseInt(serialNo)-1).getDicountAmount()); logger.debug("8");
	 * if(!homeMBs.get(Integer.parseInt(serialNo)-1).getDicountAmount().
	 * equalsIgnoreCase("")) {
	 * if(homeMBs.get(Integer.parseInt(serialNo)-1).getDicountAmount
	 * ().equalsIgnoreCase("%")) { logger.debug("9.1"); float
	 * tempnet=((Float.parseFloat
	 * (homeMBs.get(Integer.parseInt(serialNo)-1).getPrice
	 * ()))*(Float.parseFloat(
	 * ""+vi.getNewValue())))-((Float.parseFloat(homeMBs.get
	 * (Integer.parseInt(serialNo
	 * )-1).getPrice()))*(Float.parseFloat(""+vi.getNewValue
	 * ()))*Float.parseFloat
	 * (homeMBs.get(Integer.parseInt(serialNo)-1).getDicountAmount())/100);
	 * String tempnet=""+(new
	 * BigDecimal(homeMBs.get(Integer.parseInt(serialNo)-1
	 * ).getPrice()).multiply(qty).subtract(new
	 * BigDecimal(homeMBs.get(Integer.parseInt
	 * (serialNo)-1).getPrice().replace(",","")).multiply(qty).multiply(new
	 * BigDecimal
	 * (homeMBs.get(Integer.parseInt(serialNo)-1).getDicountAmount()).divide
	 * (BigDecimal.valueOf(100))))); homeMB.setNetAmount(""+tempnet); } else
	 * if(homeMBs
	 * .get(Integer.parseInt(serialNo)-1).getDicountAmount().equalsIgnoreCase
	 * ("IDR")) { float
	 * tempnet=((Float.parseFloat(homeMBs.get(Integer.parseInt(serialNo
	 * )-1).getPrice
	 * ()))*(Float.parseFloat(""+vi.getNewValue())))-(Float.parseFloat
	 * (homeMBs.get(Integer.parseInt(serialNo)-1).getDicountAmount())); String
	 * tempnet=""+(new
	 * BigDecimal(homeMBs.get(Integer.parseInt(serialNo)-1).getPrice
	 * ()).multiply(qty).subtract(new
	 * BigDecimal(homeMBs.get(Integer.parseInt(serialNo
	 * )-1).getDicountAmount()))); homeMB.setNetAmount(""+tempnet); } } else {
	 * logger.debug("9.2"); amunt1=""+(new
	 * BigDecimal(homeMBs.get(Integer.parseInt
	 * (serialNo)-1).getPrice().replace(","
	 * ,"")).multiply(qty)).subtract(BigDecimal.valueOf(0));
	 * homeMB.setNetAmount(GenerateEmployee.numberFormat.format(new
	 * BigDecimal(amunt1))); } logger.debug("10");
	 * logger.debug("Quantity---------->"+""+vi.getNewValue());
	 * logger.debug("net amount------------------>"
	 * +homeMBs.get(Integer.parseInt(serialNo)-1).getPrice());
	 * homeMB.setNetAmount(""+(new
	 * BigDecimal(homeMBs.get(Integer.parseInt(serialNo
	 * )-1).getPrice()).multiply(new BigDecimal(vi.getNewValue().toString()))));
	 * logger.debug("home size---------->"+homeMBs.size());
	 * homeMBs.set(Integer.parseInt(serialNo)-1, homeMB); } else { throw new
	 * InventoryException("error"); }
	 * 
	 * logger.debug("----------->"+batchProductName3); for(int
	 * j=0;j<batchProductName3.size();j++) {
	 * logger.debug("-----------inside for loop------------");
	 * if(homeMBs.get(Integer
	 * .parseInt(serialNo)-1).getProductName().equals(batchProductName3.get(j)))
	 * {
	 * logger.debug("------------------>product name---------->"+batchProductName3
	 * .get(j)); batchProductName3.remove(j); }
	 * 
	 * } String temp="0"; for(int i=0;i<homeMBs.size();i++) { try{
	 * if(!homeMBs.get(i).getPrice().equals("")) {
	 * logger.debug("---------------the list has value----------------"
	 * +homeMBs.get(i).getProductName());
	 * 
	 * temp=""+(new BigDecimal(temp).add(new
	 * BigDecimal(homeMBs.get(i).getNetAmount().replace(",",""))));
	 * 
	 * logger.debug("total amunt---------->"+temp); } else {
	 * logger.debug("---------------the list has no value----------------"); } }
	 * 
	 * catch(NullPointerException e) {
	 * logger.debug("--------------inside null pointer exception----------"); }
	 * } float newqunt=(float)0.0; for(int i=0;i<homeMBs.size();i++){ try{
	 * if(!homeMBs.get(i).getQuantity().equals("")){ newqunt=newqunt +
	 * Float.parseFloat(homeMBs.get(i).getQuantity());
	 * 
	 * if(stock<newqunt){ throw new
	 * InventoryException("*Not enough Quantity please select another roll "
	 * +stock+" Only have this Roll");
	 * 
	 * }
	 * 
	 * } }catch(NullPointerException e) {
	 * logger.debug("--------------inside null pointer exception----------"); }
	 * }
	 * 
	 * //float newqunt=(float)0.0;String rollTempId=""; BigDecimal newqunt= new
	 * BigDecimal("0");String rollTempId=""; for (int i = 0; i < homeMBs.size();
	 * i++) { try { newqunt=new BigDecimal("0");rollTempId=""; for (int j = 0; j
	 * < homeMBs.size(); j++) { try {
	 * if(homeMBs.get(i).getProductName().equals(homeMBs
	 * .get(j).getProductName()) &&
	 * homeMBs.get(i).getRoll().equals(homeMBs.get(j).getRoll())) {
	 * if(serialNo.equals(""+(j+1))) { newqunt=newqunt.add(qty); } else {
	 * newqunt=newqunt.add(new BigDecimal(homeMBs.get(j).getQuantity())); }
	 * 
	 * rollTempId=homeMBs.get(i).getRoll(); } } catch(Exception e) {
	 * 
	 * }
	 * 
	 * } } catch(Exception e) {
	 * 
	 * } finally { logger.debug("==========================");
	 * 
	 * purchaseOrder.setRollID(rollTempId);
	 * controller.getRollQuantity(purchaseOrder); stock=new
	 * BigDecimal(purchaseOrder.getRollStockIn());
	 * 
	 * logger.debug("---------stock1-----"+stock);
	 * logger.debug("---------stock2-----"+newqunt); int
	 * comp2=stock.compareTo(newqunt); if(comp2 == -1) { throw new
	 * InventoryException
	 * (homeMBs.get(i).getRoll()+" has been selected, please choose other rolls"
	 * ); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * setTotalPrice(""+GenerateEmployee.numberFormat.format(new
	 * BigDecimal(temp)));
	 * 
	 * 
	 * logger.debug("----success-----"); } catch (InventoryException e) {
	 * setValidate(e.getMessage()); logger.debug(e.getMessage()); QuickSaleMB
	 * homeMB=new QuickSaleMB(); homeMB.setSerialno(serialNo);
	 * homeMB.setPrice(""); homeMB.setFlag("1"); homeMB.setFlag1("1");
	 * homeMB.setFlag2("none"); homeMB.setProductName("");
	 * homeMB.setRflag1("1"); homeMB.setRflag2("none"); homeMB.setRoll("");
	 * homeMB.setPriflag1("1"); homeMB.setPriflag2("none");
	 * homeMB.setBarflag("1"); homeMB.setBarflag1("none");
	 * homeMB.setBarcode1(""); homeMB.setTflag1("none"); homeMB.setTflag2("1");
	 * homeMB.setUnit(""); homeMB.setStockin("");
	 * homeMBs.set(Integer.parseInt(serialNo)-1, homeMB); } catch(Exception e) {
	 * logger.error("Inside Exception",e); } }
	 */
	public String dicount;

	public String getDicount() {
		return dicount;
	}

	public void setDicount(String dicount) {
		this.dicount = dicount;
	}

	public String disAmount;
	public String total;

	public String getDisAmount() {
		return disAmount;
	}

	public void setDisAmount(String disAmount) {
		this.disAmount = disAmount;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String confirm() {
		setValidate2(null);
		//setValidate(null);
		try {
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			/* disAmount="0"; */
			total = "0";
			// setValidate1("");
			setValidate2("");
			logger.debug("-------------inside mb-----------" + paymentType);
			if(accountType == null || accountType.equalsIgnoreCase("") ||  accountType.equalsIgnoreCase("add")){
				throw new DemoException("*Select the Account Type");
			}else if (paymentType == null || paymentType.equalsIgnoreCase("")) {
				logger.debug("~~~~payemnt~~~~~");
				throw new DemoException("*Select the Payment Type");
			} else if (paymentType != null) {
				if (paymentType.equalsIgnoreCase("Card")) {
					if (newbankName == null || newbankName.equalsIgnoreCase("")) {
						throw new DemoException("*Enter the Bank Name");
					} else if (!newbankName
							.matches("^(?=.*[A-Za-z])([a-zA-Z0-9,\\'\\-\\.\\&\\/(\\s)+]*)$")) {
						throw new DemoException(
								" Bank Name should be in Alphabets");
					}
					if (cardno == null || cardno.equalsIgnoreCase("")) {
						throw new DemoException("*Enter the Card Number");
					} else if (!cardno.matches("^\\d+(\\.\\d+)*$")) {
						throw new DemoException(
								"Card Number Should be in Numbers");
					}

				} else if (paymentType.equalsIgnoreCase("Cheque")) {
					if (bankName == null || bankName.equalsIgnoreCase("")) {
						throw new DemoException("*Enter the Bank Name");
					} else if (!bankName
							.matches("^(?=.*[A-Za-z])([a-zA-Z0-9,\\'\\-\\.\\&\\/(\\s)+]*)$")) {
						throw new DemoException(
								" Bank Name should be in Alphabets");
					}
					if (chequeno == null || chequeno.equalsIgnoreCase("")) {
						throw new DemoException("*Enter the Cheque Number");
					} else if (!chequeno.matches("^\\d+(\\.\\d+)*$")) {
						throw new DemoException(
								" Cheque Number Should be in Numbers");
					}
					if (chequedate == null) {
						throw new DemoException("*Enter the Cheque Date");
					}
				} else if (paymentType.equalsIgnoreCase("Transfer")) {
					if (tbankname == null || tbankname.equalsIgnoreCase("")) {
						throw new DemoException("*Enter the Bank Name");
					} else if (!tbankname
							.matches("^(?=.*[A-Za-z])([a-zA-Z0-9,\\'\\-\\.\\&\\/(\\s)+]*)$")) {
						throw new DemoException(
								" Bank Name should be in Alphabets");
					}

					if (accountno == null || accountno.equalsIgnoreCase("")) {
						throw new DemoException("*Enter the Account Number");
					} else if (!accountno.matches("^\\d+(\\.\\d+)*$")) {
						throw new DemoException(
								"Account Number Should be in Numbers");
					}

				}
				if (productName == null || productName.equalsIgnoreCase("")) {
					throw new DemoException("*Enter the Product Name");
				}
				if (quantity == null || quantity.equalsIgnoreCase("")) {
					throw new DemoException("*Enter the Quantity");
				} else if (!quantity.matches("[0-9]{1,5}")) {
					throw new DemoException(
							"Quantity should be in Number");
				}
			}else if(currency.equalsIgnoreCase("") || currency == null){
				throw new DemoException(
						"please choose the currency");
			}
			logger.debug("2====" + homeMBs.get(0).getDicount());
			int cnt = 0;
			logger.debug("size of the final list----------->" + homeMBs.size());
			BigDecimal temp = BigDecimal.valueOf(0);
			BigDecimal quant = BigDecimal.valueOf(0);
			String discountz = "0";
			for (int i = 0; i < homeMBs.size(); i++) {
				if (!homeMBs.get(i).getProductName().equals("")) {

					temp = temp.add(new BigDecimal(homeMBs.get(i)
							.getNetAmount().replace(",", "")));
					quant = quant.add(new BigDecimal(homeMBs.get(i)
							.getQuantity()));
					/*
					 * quant=quant+Float.parseFloat(homeMBs.get(i).getQuantity())
					 * ; logger.debug("total amunt---------->"+temp);
					 */

				} else {
					logger.debug("---------------the list has no value----------------");
				}

			}

			logger.debug("total amount---------->" + temp);
			logger.debug("total quantity--------->" + quant);

			// purchaseOrder.setCustomerName(customername);
			// logger.debug("customer name------->>>"+customername);
			purchaseOrder.setTotalnumberofcount("" + quant);
			purchaseOrder.setCrosstotal1("" + temp);
			purchaseOrder.setQuantity("" + quant);
			purchaseOrder.setTotalPrice(totalPrice);
			logger.debug("price 1 -- >" + purchaseOrder.getTotalPrice());
			purchaseOrder.setStatus(paymentType);
			purchaseOrder.setPaymentType(paymentType);
			if (paymentType.equals("Card")) {
				purchaseOrder.setBankname(newbankName);
			} else if (paymentType.equals("Cheque")) {
				purchaseOrder.setBankname(bankName);
			} else if (paymentType.equals("Transfer")) {
				purchaseOrder.setBankname(tbankname);
			} else if (paymentType.equals("Cash")) {
				purchaseOrder.setBankname("");
			}
			purchaseOrder.setAccno(accountno);
			logger.debug("==card===>>" + purchaseOrder.getCardno());
			purchaseOrder.setCardno(cardno);
			purchaseOrder.setChequeno(chequeno);
			purchaseOrder.setChequedate(chequedate);
			purchaseOrder.setCurrency(currency);
			logger.debug("~~~TTTTTTTTTTTTTTTTTTTT~~~" + temp);

			logger.debug("~~~TTTTTTTTTTTTTTTTTTTT~~~"
					+ purchaseOrder.getTotalAmount());

			logger.debug("disc type---------> " + discType);
			logger.debug("disc amnt---------> " + discAmnt);
			BigDecimal tempval1 = BigDecimal.valueOf(0);
			BigDecimal tempval2 = BigDecimal.valueOf(0);

			purchaseOrder.setTotalAmount(""
					+ (((tempval2)).setScale(0, BigDecimal.ROUND_HALF_UP)));
			/*
			 * purchaseOrder.setTotalPrice(""+(((tempval2)).setScale(0,BigDecimal
			 * .ROUND_HALF_UP)));
			 */
			purchaseOrder.setBaseCurrency(baseCurrency);
			purchaseOrder.setResulfinal(new ArrayList<PurchaseOrder>());
			for (int i = 0; i < homeMBs.size(); i++) {
				PurchaseOrder domain=new PurchaseOrder();
				domain.setProductName(homeMBs.get(i).getProductName());
				domain.setProduct_name(homeMBs.get(i).getProductName());
				domain.setQuantity(homeMBs.get(i).getQuantity());
				domain.setSellingPrice(homeMBs.get(i).getPrice());
				domain.setTotalPrice(homeMBs.get(i).getNetAmount());
				purchaseOrder.getResulfinal().add(domain);
			}
			purchaseOrder.setAccounttype(accountType);
			controller.qucikSalesConform(purchaseOrder);
			setTots(purchaseOrder.getTotalPrice());
			setValidate(validate);
			String tempdisz = "0";
			int count = 0;

			for (int i = 0; i < homeMBs.size(); i++) {
				if (!homeMBs.get(i).getProductName().equals("")) {
					logger.debug("---------------the list has value----------------"
							+ homeMBs.get(i).getProductName());
					purchaseOrder.setQuantity(homeMBs.get(i).getQuantity());
					purchaseOrder.setBatchProductName(homeMBs.get(i)
							.getProductName());
					controller.salesOrder4(purchaseOrder);
					try {
						if (purchaseOrder.getBatchProductName()
								.equalsIgnoreCase("")) {
							throw new DemoException(
									"Please Fill All the Fileds");
						} else if (purchaseOrder.getQuantity()
								.equalsIgnoreCase("")) {
							throw new DemoException(
									"Please Fill All the Fileds");
						}else if (purchaseOrder.getQuantity().equals("0")) {
							throw new DemoException(
									"Quantity should not be zero");
						}
						else if(new BigDecimal(purchaseOrder.getQuantity1()).compareTo(
								new BigDecimal(purchaseOrder.getQuantity()))==-1){
							throw new DemoException("Only "+purchaseOrder.getQuantity1()+" quantity available in stock for "
								+purchaseOrder.getBatchProductName());
						}

					} catch (NullPointerException e) {
						throw new DemoException("Please Fill All the Fileds");
					}

				} else {
					logger.debug("---------------the list has no value----------------");
					count++;
				}
			}
			if (count == homeMBs.size()) {
				logger.debug("----------inside if----------");
				throw new DemoException("Please Choose Product Name");
			}
			String type = "", amnt = "";
			for (int i = 0; i < homeMBs.size(); i++) {
				if (!homeMBs.get(i).getProductName().equals("")) {

					QuickSaleMB qs = new QuickSaleMB();
					try {
						if (homeMBs.get(i).getDicount().equalsIgnoreCase("%")) {
							String x = ""
									+ (new BigDecimal(homeMBs.get(i).getPrice()
											.replace(",", ""))
											.multiply(new BigDecimal(homeMBs
													.get(i).getQuantity())));
							logger.debug("==discount amount==="
									+ (homeMBs.get(i).getDicountAmount()));
							tempdisz = ""
									+ new BigDecimal(x).multiply(
											new BigDecimal(homeMBs.get(i)
													.getDicountAmount()))
											.divide(BigDecimal.valueOf(100));
							type = homeMBs.get(i).getDicount();
							amnt = homeMBs.get(i).getDicountAmount();
						} else if (homeMBs.get(i).getDicount()
								.equalsIgnoreCase("Rp")) {

							tempdisz = homeMBs.get(i).getDicountAmount()
									.replace(",", "");
							type = homeMBs.get(i).getDicount();
							amnt = homeMBs.get(i).getDicountAmount();
						} else if (homeMBs.get(i).getDicount()
								.equalsIgnoreCase("")) {
							tempdisz = "0";
							type = "RP";
							amnt = "0";
							logger.debug("inside null");
						}
					} catch (Exception e) {
						tempdisz = "0";
						type = "RP";
						amnt = "0";
					}
					logger.debug("-->> disc type " + type);
					logger.debug("-->> disc  " + amnt);
					logger.debug("-->> amnt  " + tempdisz);
					purchaseOrder.setPrice(homeMBs.get(i).getPrice());
					purchaseOrder.setQuantity(homeMBs.get(i).getQuantity());
					purchaseOrder.setBatchProductName(homeMBs.get(i)
							.getProductName());
					purchaseOrder.setNetAmount(homeMBs.get(i).getNetAmount());
					purchaseOrder.setDiscountzType(type);
					purchaseOrder.setDiscountzAmount(tempdisz);
					purchaseOrder.setDiscountz(amnt);

					controller.qucikSalesRoll(purchaseOrder);
				} else {
					logger.debug("---------------the list has no value----------------");
				}
			}

			logger.debug("crosstotal in mb close::::::"
					+ purchaseOrder.getCrosstotal1());
			BigDecimal x = BigDecimal.valueOf(0);
			setTotalPrice(""
					+ GenerateEmployee.numberFormat.format(new BigDecimal(
							purchaseOrder.getCrosstotal1())));
			setSaleOrderNumber(purchaseOrder.getSalesIdReference());
			logger.debug("sale order reference number------------------->"
					+ saleOrderNumber);
			logger.debug("after");
			currentDate = sdf.format(Calendar.getInstance().getTime());
			List<QuickSaleMB> qsMB = new ArrayList<QuickSaleMB>();

			for (int i = 0; i < homeMBs.size(); i++) {
				if (!homeMBs.get(i).getProductName().equalsIgnoreCase("")) {
					QuickSaleMB qs = new QuickSaleMB();
					qs.setSerialno(homeMBs.get(i).getSerialno());
					qs.setProductName(homeMBs.get(i).getProductName());
					qs.setPrice(homeMBs.get(i).getPrice());
					BigDecimal ttqq = BigDecimal.valueOf(0);
					BigDecimal temp1 = BigDecimal.valueOf(0);
					try {
						if (homeMBs.get(i).getDicount().equalsIgnoreCase("%")) {
							type = homeMBs.get(i).getDicount();
							amnt = homeMBs.get(i).getDicountAmount();
						} else if (homeMBs.get(i).getDicount()
								.equalsIgnoreCase("Rp")) {
							type = homeMBs.get(i).getDicount();
							amnt = homeMBs.get(i).getDicountAmount();
						} else if (homeMBs.get(i).getDicount()
								.equalsIgnoreCase("")) {
							type = "RP";
							amnt = "0";
						}
					} catch (Exception e) {
						type = "RP";
						amnt = "0";
					}

					dicountAmount = "" + temp1;
					qs.setNetAmount(homeMBs.get(i).getNetAmount());
					qs.setQuantity(homeMBs.get(i).getQuantity());
					qs.setDicount(type);
					qs.setDicountAmount(amnt);
					qsMB.add(qs);
					logger.debug("------finish");

				}
			}
			homeMBs.clear();
			homeMBs = (ArrayList<QuickSaleMB>) qsMB;
			setCurrencyAmount(purchaseOrder.getCurrencyAmount());
			setBaseCurrency(baseCurrency);
			return "QucikSuccess1";
		}

		catch (DemoException ei) {
			/* setValidate(ei.getMessage()); */
			setValidate("");
			setValidate2(ei.getMessage());
			logger.debug("----------inside io exception--------"
					+ ei.getMessage());
			ei.printStackTrace();
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		}

	}

	public String dicountAmount;

	public String getDicountAmount() {
		return dicountAmount;
	}

	public void setDicountAmount(String dicountAmount) {
		this.dicountAmount = dicountAmount;
	}

	public String validate2;

	public String getValidate2() {
		return validate2;
	}

	public void setValidate2(String validate2) {
		this.validate2 = validate2;
	}

	public String dflag;
	public String dflag1;
	public String dflag2;
	public String dflag3;

	public String getDflag2() {
		return dflag2;
	}

	public void setDflag2(String dflag2) {
		this.dflag2 = dflag2;
	}

	public String getDflag3() {
		return dflag3;
	}

	public void setDflag3(String dflag3) {
		this.dflag3 = dflag3;
	}

	public String uflag = "none";
	public String uflag1 = "none";
	public String uflag2 = "none";
	public String tempDiscount;

	public String getTempDiscount() {
		return tempDiscount;
	}

	public void setTempDiscount(String tempDiscount) {
		this.tempDiscount = tempDiscount;
	}

	public String getUflag() {
		return uflag;
	}

	public void setUflag(String uflag) {
		this.uflag = uflag;
	}

	public String getUflag1() {
		return uflag1;
	}

	public void setUflag1(String uflag1) {
		this.uflag1 = uflag1;
	}

	public String getUflag2() {
		return uflag2;
	}

	public void setUflag2(String uflag2) {
		this.uflag2 = uflag2;
	}

	public String getDflag() {
		return dflag;
	}

	public void setDflag(String dflag) {
		this.dflag = dflag;
	}

	public String getDflag1() {
		return dflag1;
	}

	public void setDflag1(String dflag1) {
		this.dflag1 = dflag1;
	}

	public void discounttypeChange(ValueChangeEvent vi) {
		try {
			setValidate("");
			String serialNo = "";
			serialNo = vi.getComponent().getAttributes().get("serial")
					.toString();
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setSerialno(serialNo);
			homeMB.setPrice(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getPrice());
			homeMB.setFlag("none");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			homeMB.setFlag1("none");
			homeMB.setFlag2("1");
			homeMB.setFlag3("1");
			homeMB.setUflag("1");
			homeMB.setDflag("none");
			homeMB.setDflag1("1");
			homeMB.setProductName(""
					+ homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getProductName());
			homeMB.setRflag1("none");
			homeMB.setRflag2("1");
			homeMB.setBarflag1("1");
			homeMB.setBarflag("none");
			homeMB.setBarcode1(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getBarcode1());
			homeMB.setRoll(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getRoll());
			homeMB.setStockin(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getStockin());
			homeMB.setTflag1("none");
			homeMB.setTflag2("1");
			homeMB.setUnit(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getUnit());
			homeMB.setQuantity(""
					+ homeMBs.get(Integer.parseInt(serialNo) - 1).getQuantity());
			logger.debug("====discount type chng===" + vi.getNewValue());
			homeMB.setDicount(vi.getNewValue().toString());
			logger.debug("===discoutn amount===="
					+ homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getDicountAmount());
			homeMB.setDicountAmount(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getDicountAmount());
			homeMB.setNetAmount(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getNetAmount());
			homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
		}

		catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
	}

	public void discountChange(ValueChangeEvent vi) {
		String serialNo = "", net = "", disc = "";
		;
		String temp1 = "0";
		BigDecimal b1 = BigDecimal.valueOf(0);
		BigDecimal b2 = BigDecimal.valueOf(0);
		
		logger.debug("------------inside discountChange-------------");
		try {
			disc = (String) vi.getNewValue();
			serialNo = vi.getComponent().getAttributes().get("serial").toString();
			tempDiscount = vi.getComponent().getAttributes().get("discount").toString();
			net = vi.getComponent().getAttributes().get("net").toString();
			setValidate("");
			logger.debug("value---------->" + vi.getNewValue());

			if (!vi.getNewValue().toString().equalsIgnoreCase("")) {
				if (tempDiscount.equals("%")) {
					int i = Integer.parseInt(vi.getNewValue().toString());
					if (i < 100) {
						logger.debug("validation number1");

						logger.debug("dicount----->" + tempDiscount);

						disAmount = ""
								+ (Double.parseDouble(vi.getNewValue()
										.toString()) / 100);
						logger.debug("total--->" + temp1 + "dicountamount-->"
								+ disAmount);
						b1 = (new BigDecimal(net)
								.multiply(new BigDecimal(disc)))
								.divide(BigDecimal.valueOf(100));
					} else {
						logger.debug("validation number2");
						throw new DemoException("*Enter discount below 100");
					}
				} else if (tempDiscount.equals("Rp")) {
					logger.debug("dicount----->" + vi.getNewValue().toString());
					disAmount = ""
							+ Integer.parseInt(vi.getNewValue().toString());
					temp1 = "0";
					b1 = new BigDecimal(disc);
				}

			}

			else if (vi.getNewValue().toString().equalsIgnoreCase("")) {
				logger.debug("Enter discount amount");
			}
			b2 = new BigDecimal(net).subtract(b1);
			logger.debug("b1----->" + b1);
			logger.debug("b2----->" + b2);
			logger.debug("inside value change-----------------" + disAmount);

			String tempTotal = "0";
			String d = "0";
			String a = "0";
			String b = "0";
			if (homeMBs.size() > 0) {
				logger.debug("-------------------inside if loop-----------------");
				QuickSaleMB homeMB = new QuickSaleMB();
				homeMB.setSerialno(serialNo);
				logger.debug("serial----------------->" + serialNo);
				homeMB.setPrice(homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getPrice());
				homeMB.setFlag("none");
				homeMB.setFlag1("none");
				homeMB.setFlag3("1");
				homeMB.setFlag2("1");
				homeMB.setPriflag1("none");
				homeMB.setPriflag2("1");
				homeMB.setUflag("1");
				homeMB.setDflag("none");
				homeMB.setDflag1("1");
				homeMB.setDflag2("1");
				homeMB.setDflag3("none");
				homeMB.setProductName(""
						+ homeMBs.get(Integer.parseInt(serialNo) - 1)
								.getProductName());
				homeMB.setRflag1("none");
				homeMB.setRflag2("1");
				homeMB.setBarflag1("1");
				homeMB.setBarflag("none");
				homeMB.setBarcode1(homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getBarcode1());
				homeMB.setTflag1("none");
				homeMB.setTflag2("1");
				homeMB.setUnit(homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getUnit());
				homeMB.setQuantity(""
						+ homeMBs.get(Integer.parseInt(serialNo) - 1)
								.getQuantity());
				homeMB.setDicount(homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getDicount());
				homeMB.setDicountAmount(vi.getNewValue().toString());
				homeMB.setNetAmount("" + b2);
				homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
			} else {
				throw new DemoException("error");
			}
			String temp = "0";
			for (int i = 0; i < homeMBs.size(); i++) {
				try {
					if (!homeMBs.get(i).getPrice().equals("")) {
						logger.debug("---------------the list has value----------------"
								+ homeMBs.get(i).getProductName());

						temp = ""
								+ (new BigDecimal(temp).add(new BigDecimal(
										homeMBs.get(i).getNetAmount()
												.replace(",", ""))));

						logger.debug("total amunt---------->" + temp);
					} else {
						logger.debug("---------------the list has no value----------------");
					}
				}

				catch (NullPointerException e) {
					logger.debug("--------------inside null pointer exception----------");
				}
			}

			setTotalPrice("" + (temp));

			logger.debug("----success-----");
		} catch (DemoException e) {
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setSerialno(serialNo);
			homeMB.setProductName(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getProductName());
			homeMB.setRoll(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getRoll());
			homeMB.setPrice(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getPrice());
			homeMB.setTflag1("none");
			homeMB.setTflag2("1");
			homeMB.setUnit(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getUnit());
			homeMB.setQuantity(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getQuantity());
			homeMB.setDicount(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getDicount());
			homeMB.setDicountAmount("");
			homeMB.setNetAmount(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getNetAmount());
			homeMB.setStockin(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getStockin());
			homeMB.setBarflag1("1");
			homeMB.setBarflag("none");
			homeMB.setBarcode1(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getBarcode1());
			homeMB.setFlag("none");
			homeMB.setUflag("1");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			homeMB.setFlag1("none");
			homeMB.setFlag2("1");
			homeMB.setRflag1("none");
			homeMB.setRflag2("1");
			homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public String quickdelete() {
		logger.debug("inside quick sale delete mb--------->>>>");
		QuickSaleMB pn = new QuickSaleMB();
		try {
			setValidate2("");
			logger.debug("serial no----------->>>" + serialno);
			logger.debug("product name---------->>>" + productName);
			String sn = serialno;
			pn.setProductName("");
			pn.setRoll("");
			pn.setSerialno(sn);
			pn.setQuantity("");
			pn.setNetAmount("");
			pn.setPrice("");
			pn.setUflag("none");
			pn.setFlag("1");
			pn.setFlag1("1");
			pn.setFlag2("none");
			pn.setRflag1("1");
			pn.setRflag2("none");
			pn.setUnit("");
			pn.setTflag1("1");
			pn.setBarflag("1");
			pn.setBarflag1("none");
			pn.setBarcode1("");
			pn.setTflag2("none");
			Float temp = (float) 0.0;
			logger.debug(totalPrice);
			BigDecimal bb = BigDecimal.valueOf(0);
			/*
			 * if(!netAmount.equalsIgnoreCase("")) { temp=""+(new
			 * BigDecimal(temp).add(new BigDecimal(totalPrice).subtract(new
			 * BigDecimal(netAmount))));
			 * 
			 * } else { temp=""+(new BigDecimal(temp).add(new
			 * BigDecimal(totalPrice))); }
			 */
			logger.debug("temp - "+temp+ " price "+totalPrice+" net "+netAmount);
			
			if (!netAmount.equalsIgnoreCase("") && !netAmount.equalsIgnoreCase("0")) {
				
				temp = (temp + ((Float.parseFloat(totalPrice.replace(",", ""))) - (Float
						.parseFloat(netAmount.replace(",", "")))));

			} else {
			
				try {
					temp = (temp + (Float.parseFloat(totalPrice
							.replace(",", ""))));

				} catch (NullPointerException n) {
					temp = temp;
				}
				
				logger.debug("totalPrice-->>" + totalPrice);

			}
			logger.debug("temp 1- "+temp+ " price "+totalPrice+" net "+netAmount);
			homeMBs.set(Integer.parseInt(sn) - 1, pn);
			// batchProductName3.add(productName);
			setTotalPrice(String.valueOf(temp));
			logger.debug("----------->" + batchProductName3);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			logger.error("inside exception ",e);
		} finally {
			setValidate2("");
		}
		return "";
	}

	public void paymentTypechnge(ValueChangeEvent vv) {
		paymentType = "" + vv.getNewValue().toString();
		logger.debug("payment type -- >> " + paymentType);
		if (paymentType.equals("Cash")) {
			logger.debug("inside cash -- >> ");
			cashflag = "none";
			cardflag = "none";
			giroflag = "none";
			transflag = "none";
		} else if (paymentType.equals("Card")) {
			logger.debug("inside card -- >> ");
			cashflag = "none";
			cardflag = "1";
			giroflag = "none";
			transflag = "none";
		} else if (paymentType.equals("Cheque")) {
			logger.debug("inside Cheque -- >> ");
			cashflag = "none";
			cardflag = "none";
			giroflag = "1";
			transflag = "none";
		} else if (paymentType.equals("Transfer")) {
			logger.debug("inside transfer -- >> ");
			cashflag = "none";
			cardflag = "none";
			giroflag = "none";
			transflag = "1";
		}
	}

	public String salesReturnView() {
		logger.debug("Inside of Sales Return");
		ArrayList<String> s2 = null;
		try {
			tempValidate = "";
			resulfinal1 = null;
			logger.debug("----------------inside salesReturnView--------------------");
			validate = null;
			/*
			 * if(salereferencenumber.equalsIgnoreCase("")){ throw new
			 * DemoException("Please Choose Order Number "); }
			 */
			purchaseOrder.setSalesId(0);
			purchaseOrder.setSalesIdReference(salesViewMB.salereferencenumber);
			purchaseOrder.setResul(resul);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			/* controller.salesOrderViewproduct(purchaseOrder); */
			controller.salesRecordView(purchaseOrder);

			resulfinal1 = purchaseOrder.getResulfinal();
			int i = 0;
			s2 = new ArrayList<String>();
			logger.debug("Roll id" + purchaseOrder.getRollID());
			logger.debug("resulfinal1---->" + resulfinal1.size());
			for (PurchaseOrder ii : resulfinal1) {
				logger.debug("4");

				QuickSaleMB quickSaleMB = new QuickSaleMB();
				logger.debug("5");
				quickSaleMB
						.setProductName(resulfinal1.get(i).getProduct_name());
				logger.debug("6");
				logger.debug("777777777777777777"
						+ quickSaleMB.getProductName());

				s2.add(quickSaleMB.productName);

				logger.debug("7");
				i++;

			}

			s1 = s2;
			flag4 = "1";
			setCustomerName(purchaseOrder.getCustomerName());
			setTelephonenumber(purchaseOrder.getTelephonenumber());
			setAddress(purchaseOrder.getAddress());
			return "";
		} catch (DemoException e) {

			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "";
		} catch (Exception ie) {
			// setValidate("Please ");
			logger.error("Inside Exception", ie);
			return "";
		}

	}

	public boolean quicksaleflag;
	public boolean quicksaleflag1;
	public String categoryType;

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public boolean quicksalesreturnflag;

	public boolean isQuicksalesreturnflag() {
		return quicksalesreturnflag;
	}

	public void setQuicksalesreturnflag(boolean quicksalesreturnflag) {
		this.quicksalesreturnflag = quicksalesreturnflag;
	}

	public boolean isQuicksaleflag() {
		return quicksaleflag;
	}

	public void setQuicksaleflag(boolean quicksaleflag) {
		this.quicksaleflag = quicksaleflag;
	}

	public boolean isQuicksaleflag1() {
		return quicksaleflag1;
	}

	public void setQuicksaleflag1(boolean quicksaleflag1) {
		this.quicksaleflag1 = quicksaleflag1;
	}

	public void valueChange1(ValueChangeEvent ve) {
		String str = ve.getNewValue().toString();
		logger.debug("QS category value change method calling" + str);
		if (str.equalsIgnoreCase("QS return")) {
			logger.debug("qs return");
			setQuicksaleflag(true);
			salesReturnView();

		} else {
			setQuicksaleflag(false);
		}
	}

	public void Quicksale() {

		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 600);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("salesViewj", options,
				null);
		salesViewMB.salesView2();
		setQuicksaleflag(false);
		setCategoryType(null);
		setTempValidate("");
	}

	public String redirectQucikReturn() {
		try {
			setFlag4("none");
			logger.debug("Inside of Loading page");
			setValidate(null);
			setFlag1("none");
			setFlag2("none");
			setFlag3("none");
			resulfinal1 = null;
			salereferencenumber = "";
			customerName = "";

		} catch (Exception e) {

		}

		/* return "redirectQucikReturnSale"; */
		return "";
	}

	public String returnDrop() {
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.quickSaleDropdown(purchaseOrder);
			setResult(purchaseOrder.getResult());

			return "";
		} catch (DemoException e) {

			setValidate("");
			setResult(null);

			logger.debug(e.getMessage());
			return "";
		}

	}

	public void checkBox(ValueChangeEvent v) {
		tempValidate = "";
		logger.debug("change value------------------>" + v.getNewValue());
		logger.debug("value serial---------------->"
				+ v.getComponent().getAttributes().get("serial").toString());
		logger.debug("value flag1---------------->"
				+ (String) v.getComponent().getAttributes().get("flag1"));
		logger.debug("value flag2---------------->"
				+ (String) v.getComponent().getAttributes().get("flag2"));
		String serialNo = "";
		serialNo = v.getComponent().getAttributes().get("serial").toString();
		if (("" + v.getNewValue()).equals("true")) {
			logger.debug("inside if");
			/*
			 * String s=(String)v.getComponent().getAttributes().get("serial");
			 * int index=Integer.parseInt(s)-1;
			 * logger.debug("index----------------->"+index);
			 */
			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("1");
			order.setTempFlag2("1");
			order.setPriroty("true");
			order.setQuantity((String) v.getComponent().getAttributes()
					.get("quant"));
			order.setProduct_name((String) v.getComponent().getAttributes()
					.get("pName"));
			order.setRollID((String) v.getComponent().getAttributes()
					.get("roll"));
			// order.setSerialNo((String)v.getComponent().getAttributes().get("serial"));
			order.setSerialNo(resulfinal1.get(Integer.parseInt(serialNo) - 1)
					.getSerialNo());
			order.setUnit((String) v.getComponent().getAttributes()
					.get("unit1"));
			order.setNr("");
			order.setDr("");
			resulfinal1.set((Integer.parseInt(serialNo) - 1), order);
		} else {
			logger.debug("inside else");
			/* String s=(String)v.getComponent().getAttributes().get("serial"); */
			/*
			 * int index=Integer.parseInt(s)-1;
			 * logger.debug("index----------------->"+index);
			 */

			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("none");
			order.setTempFlag2("none");
			order.setPriroty("false");
			order.setRollID((String) v.getComponent().getAttributes()
					.get("roll"));
			order.setUnit((String) v.getComponent().getAttributes()
					.get("unit1"));
			order.setQuantity((String) v.getComponent().getAttributes()
					.get("quant"));
			order.setProduct_name((String) v.getComponent().getAttributes()
					.get("pName"));
			order.setSerialNo(resulfinal1.get(Integer.parseInt(serialNo) - 1)
					.getSerialNo());
			order.setNr("");
			order.setDr("");
			resulfinal1.set((Integer.parseInt(serialNo) - 1), order);
		}
		logger.debug("-------------------success fully completed---------------------");
	}

	public void nrChange(ValueChangeEvent v) {
		logger.debug("Inside nr change");

		String serialNo = "";
		serialNo = v.getComponent().getAttributes().get("serial").toString();
		logger.debug("serialNo" + serialNo);
		tempValidate = "";
		try {
			logger.debug("serialNo " + serialNo);
			float j = 0, i = 0;
			i = Float.parseFloat("" + v.getNewValue());
			logger.debug("nr values" + i);
			try {
				j = Float.parseFloat((String) v.getComponent().getAttributes()
						.get("dr"));
			} catch (NullPointerException n) {
				logger.debug("--------------inisde NullPointerException------------------");
				j = 0;
			} catch (NumberFormatException e) {
				logger.debug("--------------inisde exception------------------");
				j = 0;
			}
			logger.debug("---->" + (j) + "------------and---------------" + i);
			logger.debug("total-------------->" + (i + j));
			if ((i + j) > (Float.parseFloat(""
					+ (String) v.getComponent().getAttributes().get("quant")))) {
				logger.debug("-------------------inside high the quantity---------------");
				throw new DemoException("Enter the Correct Quantity");
			}
			logger.debug("value---------------->" + v.getNewValue());
			// logger.debug("index----------------->"+index);
			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("1");
			order.setTempFlag2("1");
			order.setPriroty(""
					+ (String) v.getComponent().getAttributes().get("pri"));
			order.setRollID((String) v.getComponent().getAttributes()
					.get("rolls"));
			order.setUnit((String) v.getComponent().getAttributes()
					.get("unit1"));
			order.setQuantity((String) v.getComponent().getAttributes()
					.get("quant"));
			order.setProduct_name((String) v.getComponent().getAttributes()
					.get("pName"));
			order.setSerialNo((Integer) v.getComponent().getAttributes()
					.get("serial"));
			order.setNr("" + v.getNewValue());
			order.setDr((String) v.getComponent().getAttributes().get("dr"));
			resulfinal1.set((Integer.parseInt(serialNo) - 1), order);
		} catch (DemoException e) {
			logger.debug("inside value---------->" + e.getMessage());
			tempValidate = e.getMessage();
			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("none");
			order.setTempFlag2("none");
			order.setPriroty("false");
			order.setQuantity((String) v.getComponent().getAttributes()
					.get("quant"));
			order.setProduct_name((String) v.getComponent().getAttributes()
					.get("pName"));
			order.setRollID((String) v.getComponent().getAttributes()
					.get("rolls"));
			order.setUnit((String) v.getComponent().getAttributes()
					.get("unit1"));
			order.setSerialNo((Integer) v.getComponent().getAttributes()
					.get("serial"));
			order.setNr("0");
			order.setDr((String) v.getComponent().getAttributes().get("dr"));
			logger.debug("---------inide exception end-------------");
			resulfinal1.set((Integer.parseInt(serialNo) - 1), order);
		}
	}

	public void drChange(ValueChangeEvent v) {
		logger.debug("Inside dr change");
		tempValidate = "";
		int s = (Integer) v.getComponent().getAttributes().get("serial");
		int index = s - 1;
		logger.debug(index);

		try {
			float j = 0, i = 0;
			i = Float.parseFloat("" + v.getNewValue());
			try {
				j = Float.parseFloat((String) v.getComponent().getAttributes()
						.get("nr"));
			} catch (NumberFormatException e) {
				logger.debug("--------------inisde exception------------------");
				j = 0;
			}
			logger.debug("---->" + (j) + "------------and---------------" + i);
			logger.debug("total-------------->" + (i + j));
			if ((i + j) > (Float.parseFloat(""
					+ (String) v.getComponent().getAttributes().get("quant")))) {
				logger.debug("-------------------inside high the quantity---------------");
				throw new DemoException("Enter the Correct Quantity");
			}
			logger.debug("value---------------->" + v.getNewValue());
			logger.debug("index----------------->" + index);
			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("1");
			order.setTempFlag2("1");
			order.setPriroty(""
					+ (String) v.getComponent().getAttributes().get("pri"));
			order.setQuantity((String) v.getComponent().getAttributes()
					.get("quant"));
			order.setProduct_name((String) v.getComponent().getAttributes()
					.get("pName"));
			order.setRollID((String) v.getComponent().getAttributes()
					.get("rolls"));
			order.setUnit((String) v.getComponent().getAttributes()
					.get("unit1"));
			order.setSerialNo((Integer) v.getComponent().getAttributes()
					.get("serial"));
			order.setNr((String) v.getComponent().getAttributes().get("nr"));
			order.setDr("" + v.getNewValue());
			resulfinal1.set((index), order);
		} catch (DemoException e) {
			logger.debug("inside value---------->" + e.getMessage());
			tempValidate = e.getMessage();
			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("none");
			order.setTempFlag2("none");
			order.setPriroty("false");
			order.setQuantity((String) v.getComponent().getAttributes()
					.get("quant"));
			order.setProduct_name((String) v.getComponent().getAttributes()
					.get("pName"));
			order.setRollID((String) v.getComponent().getAttributes()
					.get("rolls"));
			order.setUnit((String) v.getComponent().getAttributes()
					.get("unit1"));
			order.setSerialNo((Integer) v.getComponent().getAttributes()
					.get("serial"));
			order.setNr((String) v.getComponent().getAttributes().get("nr"));
			order.setDr("0");
			logger.debug("---------inide exception end-------------");
			resulfinal1.set((index), order);
		}

	}

	public String getRollID() {
		return rollID;
	}

	public void setRollID(String rollID) {
		this.rollID = rollID;
	}

	public String salesReturnSubmit() {
		try {
			logger.debug("<---------------inside sales return submit------------------->");
			logger.debug("list size---------------------->"
					+ resulfinal1.size());
			logger.debug("sales id------------------>"
					+ purchaseOrder.getSalesId());
			tempValidate = "";
			purchaseOrder.setResulfinal(resulfinal1);
			// purchaseOrder.setTargentDate(returnDate);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			int ii = 0;
			int cc = 0;
			for (ii = 0; ii < resulfinal1.size(); ii++) {
				if (resulfinal1.get(ii).getPriroty().equalsIgnoreCase("true")) {
					logger.debug("exception null");
					cc++;
				}
			}
			if (cc == 0) {
				throw new DemoException("*Enter the quantity to return");
			}

			for (int i = 0; i < resulfinal1.size(); i++) {
				if (!resulfinal1.get(i).getPriroty().equalsIgnoreCase("true")) {
					logger.debug("enter the return value  -- >> ");
				} else {
					setNr(resulfinal1.get(i).getNr());
					logger.debug("NR" + getNr());
					setDr(resulfinal1.get(i).getDr());
					logger.debug("DR" + getDr());
					setProductName(resulfinal1.get(i).getProduct_name());
					setRollID(resulfinal1.get(i).getRollID());
					// purchaseOrder.setTargentDate(returnDate);
					purchaseOrder.setProduct_name(productName);
					purchaseOrder.setQuantity(resulfinal1.get(i).getQuantity());
					purchaseOrder.setRollID(rollID);
					if (nr.equalsIgnoreCase("")) {
						nr = "0";
					}
					if (dr.equalsIgnoreCase("")) {
						dr = "0";
					}
					purchaseOrder.setNr(nr);
					logger.debug("nr -- >> " + nr);
					purchaseOrder.setDr(dr);
					logger.debug("dr -- >> " + dr);
					String temp = ""
							+ new BigDecimal(nr).add(new BigDecimal(dr));
					logger.debug("return quantity -- >> " + temp);
					if (new BigDecimal(temp).compareTo(BigDecimal.valueOf(0)) == 0) {
						logger.debug("please enter the quantity  -- >> ");
						throw new DemoException("*Enter Quantity for Return");
					} else {
						if (new BigDecimal(temp).compareTo(new BigDecimal(
								resulfinal1.get(i).getQuantity())) == 1) {
							logger.debug("enter the correct quantity -- > ");
							throw new DemoException(
									"Enter quantity is greater than available purchase");
						} else {
							controller.returnQuantity(purchaseOrder);
							BigDecimal quanyity = new BigDecimal(
									purchaseOrder.getQuantity1());
							logger.debug("quantity -- >> " + quanyity);
							String dbquantity = purchaseOrder.getRemaining();
							if (new BigDecimal(temp).compareTo(quanyity) <= 0) {
								logger.debug("return quantity is lessthan sold quantity  ---- >>");
								controller.quicksaleReturnSubmit(purchaseOrder);
								RequestContext.getCurrentInstance().execute(
										"PF('delistatus1').show();");
							} else {
								logger.debug("return quantity is greaterthan sold quantity  ---- >>");
								throw new DemoException("*Already "
										+ dbquantity
										+ " quantities are return for "
										+ purchaseOrder.getSalesIdReference()
										+ " - product is "
										+ resulfinal1.get(i).getProduct_name());
							}
						}
					}
				}
			}

			return "";
		} catch (DemoException e) {
			setTempValidate(e.getMessage());
			logger.debug("----------------inside exception-------------------");
			logger.error("Inside Exception", e);
			return "";
		}
	}

	public String redirectQucikReturnView() {
		try {
			logger.debug("Inside of redirectQucikReturnView Loading page");

			setValidate("");
			mb3.clear();
			fromdate = null;
			todate = null;
			flag1 = "none";
		} catch (Exception e) {

		}

		return "";
	}

	public String quickViewSalesReturn() {
		flag1 = "none";
		flag2 = "none";
		logger.debug("-->> mb");
		try {
			/*
			 * if(fromdate==null) { throw new
			 * DemoException("Please Choose the From Date"); } else
			 * if(todate==null) { throw new
			 * DemoException("Please Choose the To Date"); }
			 */
			logger.debug("-->> mb try");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			/*
			 * purchaseOrder.setFromDate(fromdate);
			 * purchaseOrder.setToDate(todate);
			 */
			// controller.viewSalesReturn(purchaseOrder);
			controller.quickViewSalesReturn(purchaseOrder);

			logger.debug("-->> size " + purchaseOrder.domain2.size());
			if (purchaseOrder.domain2.size() > 0) {
				setValidate("");
				flag1 = "1";
				setMb2(purchaseOrder.getDomain2());
				List<String> values = new ArrayList<String>();
				for (int i = 0; i < mb2.size(); i++) {
					values.add(mb2.get(i).getOrderNumber());
				}
				List<String> templist = new ArrayList<String>();
				HashSet<String> hashlist1 = new HashSet<String>(values);
				logger.debug(" hashlist1  " + hashlist1);
				for (String a : hashlist1) {
					templist.add(a);
				}
				int count = 0;
				List<PurchaseOrder> po = new ArrayList<PurchaseOrder>();
				for (int i = 0; i < templist.size(); i++) {
					for (int j = 0; j < mb2.size(); j++) {
						if (templist.get(i).equalsIgnoreCase(
								mb2.get(j).getOrderNumber())) {
							count = j;
						}
					}
					PurchaseOrder pp = new PurchaseOrder();
					pp.setOrderNumber(mb2.get(count).getOrderNumber());
					pp.setOrderDate(mb2.get(count).getOrderDate());
					pp.setVendorPhoneNumber(mb2.get(count)
							.getVendorPhoneNumber());
					po.add(pp);
					count = 0;
				}
				mb2.clear();
				mb2 = po;
			} else {
				flag2 = "1";
				logger.debug("inside else");
			}

		} catch (DemoException e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
		}
		return "";

	}

	public String viewSalesReturnDetail() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		logger.debug("-->> mb");
		try {
			setValidate("");
			logger.debug("-->> mb try");
			/*
			 * purchaseOrder.setFromDate(fromdate);
			 * purchaseOrder.setToDate(todate);
			 */
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setOrderNumber(porderNo);
			logger.debug("-->> order no " + getPorderNo());
			controller.viewSalesReturnDetail(purchaseOrder);
			if (purchaseOrder.domain2.size() > 0) {

				logger.debug("inside mb if");
				setMb3(purchaseOrder.getDomain2());
			} else {
				logger.debug("inside else");
			}
			setVendorName(mb3.get(0).getVendorPhoneNumber());
			setOrderDate1(sdf.format(mb3.get(0).getOrderDate()));
			logger.debug("inside mb end" + getOrderDate1());
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
		}
		return "quicksaleReturnView1";

	}

	public String salesReturnPage() {
		setValidate("");
		mb3.clear();
		return "quicksalezReturnView2";
	}

	public String getStockin() {
		return stockin;
	}

	public void setStockin(String stockin) {
		this.stockin = stockin;
	}

	public String barcode1;
	public String barflag = "none";
	public String barflag1 = "none";

	public String getBarcode1() {
		return barcode1;
	}

	public void setBarcode1(String barcode1) {
		this.barcode1 = barcode1;
	}

	public String getBarflag() {
		return barflag;
	}

	public void setBarflag(String barflag) {
		this.barflag = barflag;
	}

	public String getBarflag1() {
		return barflag1;
	}

	public void setBarflag1(String barflag1) {
		this.barflag1 = barflag1;
	}

	public void barcodevaluechange(ValueChangeEvent v) {
		String serialNo = "";
		serialNo = v.getComponent().getAttributes().get("serial").toString();
		logger.debug("serial no -- >>  " + serialNo);
		try {
			setFlag3("none");
			barcode1 = "" + v.getNewValue().toString();
			logger.debug("barcode---->>>" + barcode);
			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setBarcode1(barcode1);
			controller.getbarcodeInfo(purchaseOrder);
			setPrice("" + purchaseOrder.getSellingPrice());
			setUnit("" + purchaseOrder.getUnit());
			marginPrice = "" + purchaseOrder.getMarginPrice();
			setProductName(purchaseOrder.getProduct_name());
			setBarcode(barcode);
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setPrice(price);
			homeMB.setProductName(productName);
			homeMB.setBarcode1(barcode1);
			homeMB.setFlag("none");
			homeMB.setUflag("1");
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setRflag1("1");
			homeMB.setRflag2("none");
			homeMB.setFlag2("none");
			homeMB.setFlag1("1");
			homeMB.setTflag1("none");
			homeMB.setQuantity("");
			homeMB.setTflag2("none");
			homeMB.setDflag("1");
			homeMB.setDflag1("1");
			homeMB.setUnit(unit);
			homeMB.setStockin("");
			homeMB.setRoll("");
			homeMB.setFlag3("1");
			homeMB.setFlag4("none");
			homeMB.setBarflag("none");
			homeMB.setBarflag1("1");
			homeMB.setSerialno(serialNo);
			logger.debug("home size---------->" + homeMBs.size());
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
			for (int i = 0; i < homeMBs.size(); i++) {
				logger.debug(" bar 00 --------"
						+ homeMBs.get(Integer.parseInt(serialNo) - 1)
								.getBarcode());
				String product11 = productName;
				for (int c = 0; c < batchProductName3.size(); c++) {
					if (batchProductName3.get(c).equals(product11)) {
						logger.debug("product  removed---"
								+ batchProductName3.get(c));
						batchProductName3.remove(c);
					}
				}
			}
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setSerialno(serialNo);
			homeMB.setPrice("");
			homeMB.setFlag("1");
			homeMB.setFlag1("1");
			homeMB.setFlag2("none");
			homeMB.setProductName("");
			homeMB.setRflag1("1");
			homeMB.setRflag2("none");
			homeMB.setRoll("");
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setTflag1("none");
			homeMB.setTflag2("1");
			homeMB.setUnit("");
			homeMB.setStockin("");
			homeMB.setBarflag("1");
			homeMB.setBarflag1("none");
			homeMB.setBarcode1("");
			homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
		}
	}

	public Date getQdate() {
		return qdate;
	}

	public void setQdate(Date qdate) {
		this.qdate = qdate;
	}

	public void dateChange(ValueChangeEvent a) {
		logger.debug("Inside dateChange Listener");
		setChequedate((Date) a.getNewValue());
	}

	public void chqeChange(ValueChangeEvent a) {
		logger.debug("Inside chqeChange Listener");
		setChequeno(a.getNewValue().toString());
	}

	public void bankChange(ValueChangeEvent a) {
		logger.debug("Inside bankChange Listener");
		setBankName(a.getNewValue().toString());
	}

	public void TaccountnoChange(ValueChangeEvent vi) {
		logger.debug("Inside TaccountnoChange Listener");
		setAccountno(vi.getNewValue().toString());
	}

	public void TBankChange(ValueChangeEvent vi) {
		logger.debug("Inside TBankChange Listener");
		setTbankname(vi.getNewValue().toString());
	}

	public void newACNOChange(ValueChangeEvent vi) {
		logger.debug("Inside newACNOChange Listener");
		setCardno(vi.getNewValue().toString());
	}

	public void newBankChange(ValueChangeEvent vi) {
		logger.debug("Inside newBankChange Listener");
		setNewbankName(vi.getNewValue().toString());
	}

	/* jency */
	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public QuickSaleMB() {
		sortsOrders = new HashMap<String, SortOrder>();
		sortPriorities = new ArrayList<String>();
	}

	public String rollq;

	public String getRollq() {
		return rollq;
	}

	public void setRollq(String rollq) {
		this.rollq = rollq;
	}

	public void sort() {
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
				sortsOrders.put(property, SortOrder.ASCENDING);
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

	public void rolldropChange(ValueChangeEvent rollval) {
		String serialNo = rollval.getComponent().getAttributes().get("serial2")
				.toString();
		String productname = rollval.getComponent().getAttributes()
				.get("product2").toString();
		String barcode = rollval.getComponent().getAttributes().get("barcode")
				.toString();
		String price = rollval.getComponent().getAttributes().get("price")
				.toString();
		String rollQ = rollval.getComponent().getAttributes().get("rollQ")
				.toString();
		String innerserial = rollval.getComponent().getAttributes()
				.get("serial3").toString();
		String serial = rollval.getComponent().getAttributes().get("serial3")
				.toString();
		logger.debug("-->> innerserial " + innerserial);
		try {
			setValidate("");
			roll = "" + rollval.getNewValue();
			QuickSaleMB homeMB = new QuickSaleMB();
			if (productname == null || productname.equalsIgnoreCase("")
					|| productname.contains("Select")
					|| productname.equalsIgnoreCase("Select")) {
				throw new DemoException("*Enter the Product Name");
			} else if (price.equalsIgnoreCase("")) {
				throw new DemoException("*Enter the Price");
			}
			String[] parts = roll.split(" / ");
			logger.debug("parts[1] -->> " + parts[1]);
			logger.debug("parts[0] -->> " + parts[0]);
			ArrayList<QuickSaleMB> roll2 = null;
			roll2 = homeMBs.get(Integer.parseInt(serialNo) - 1).getRoll1();
			int j = homeMBs.get(Integer.parseInt(serialNo) - 1).getRoll1()
					.size();

			logger.debug("list size----------------->" + homeMBs.size());
			roll2 = new ArrayList<QuickSaleMB>();
			homeMB = new QuickSaleMB();

			logger.debug("-->> j " + j);
			logger.debug("-->> serial " + serial);
			logger.debug("-->> roll " + roll);
			logger.debug("-->> quantity " + quantity);
			logger.debug("-->> roll1 size "
					+ homeMBs.get(Integer.parseInt(serialNo) - 1).roll1.size());

			for (int i = 1; i <= homeMBs.get(Integer.parseInt(serialNo) - 1).roll1
					.size(); i++) {
				if (serial.equals("" + i)) {
					logger.debug("-- -- ** ** ");
					QuickSaleMB st = new QuickSaleMB();
					st.setRoll(roll);
					st.setRollq(parts[0]);
					st.setInnerserial1(serial);
					st.setRflag1("none");
					st.setRflag2("1");
					st.setQuantity("");
					st.setFlag1("1");
					roll2.add(st);
					logger.debug("serial no-->> inner " + st.getInnerserial1());
				} else {

					logger.debug("*********************************************");
					QuickSaleMB st = new QuickSaleMB();

					if (!homeMBs.get(Integer.parseInt(serialNo) - 1).getRoll1()
							.get(i - 1).getRoll().equalsIgnoreCase("")) {
						logger.debug("roll -->> not null");
						st.setRflag2("1");
						st.setFlag2("1");
						st.setFlag1("none");
						st.setRflag1("none");

					} else {
						logger.debug("roll -->> null");
						st.setRflag1("1");
						st.setFlag1("1");
						st.setFlag2("none");
						st.setRflag2("none");
					}

					st.setUnit(purchaseOrder.getUnit());
					st.setRollq(homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getRoll1().get(i - 1).getRollq());
					st.setQuantity(homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getRoll1().get(i - 1).getQuantity());
					st.setRoll(homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getRoll1().get(i - 1).getRoll());
					st.setInnerserial1(homeMBs
							.get(Integer.parseInt(serialNo) - 1).getRoll1()
							.get(i - 1).getInnerserial1());
					/*
					 * st.setRflag1(homeMBs.get(Integer.parseInt(serialNo)-1).
					 * getRoll1().get(i-1).getRflag1()); st.setRflag1("1");
					 * st.setRflag2("none");
					 */
					roll2.add(st);
					logger.debug("serial no-->> inner " + st.getInnerserial1());
				}

			}

			purchaseOrder.setRollID(roll);
			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			/* controller.getRollQuantity(purchaseOrder); */
			/*
			 * logger.debug("values added"+purchaseOrder.getRollStockIn());
			 * setStockin(String.valueOf(purchaseOrder.getRollStockIn()));
			 * BigDecimal stock = new
			 * BigDecimal(purchaseOrder.getRollStockIn());
			 * stock=stock.setScale(2, RoundingMode.CEILING);
			 */

			homeMB.setSerialno(serialNo);
			homeMB.setPrice(price);
			homeMB.setRollQuantities(rollQ);
			homeMB.setFlag("none");
			homeMB.setUflag("1");
			/* homeMB.setStockin(stock.toString()); */
			homeMB.setFlag3("1");
			homeMB.setProductName(productname);
			homeMB.setDicount("");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			/* homeMB.setRoll(roll); */
			homeMB.setRflag1("none");
			homeMB.setRflag2("1");
			homeMB.setTflag1("1");
			homeMB.setBarcode1(barcode);
			homeMB.setBarflag("none");
			homeMB.setBarflag1("1");
			homeMB.setTflag2("none");
			homeMB.setUnit(unit);
			homeMB.setDicountAmount("");
			homeMB.setRollQFlag1("none");
			homeMB.setRollQFlag2("1");
			homeMB.setRoll1(roll2);
			logger.debug(" try home size---------->" + homeMBs.size());
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);

			String rollName = "";
			String rollQuan = "";
			BigDecimal totquan = BigDecimal.valueOf(0);
			purchaseOrder.setTemptotquan("0");
			for (int k = 0; k < homeMBs.size(); k++) {
				if (!homeMBs.get((k)).getProductName().equalsIgnoreCase("")) {
					String l = homeMBs.get((k)).getRollQuantities();
					for (int m = 0; m < Integer.parseInt(l); m++) {

						if (!homeMBs.get(k).getRoll1().get(m).getQuantity()
								.equalsIgnoreCase("")
								&& !homeMBs.get(k).getRoll1().get(m).getRoll()
										.equalsIgnoreCase("")) {
							rollQuan = homeMBs.get(k).getRoll1().get(m)
									.getQuantity();
							rollName = homeMBs.get(k).getRoll1().get(m)
									.getRollq();
							logger.debug("-->> rollName " + rollName);
							logger.debug("-->> rollQuan " + rollQuan);
							if (rollName.equalsIgnoreCase(parts[0])) {
								totquan = totquan.add(new BigDecimal(rollQuan));
								purchaseOrder.setTemptotquan("" + totquan);
								logger.debug("-->> totQuan " + totquan);
							}
						}
					}
				}
			}

		} catch (DemoException e) {
			setValidate(e.getMessage());
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setFlag("1");
			homeMB.setUflag("none");
			homeMB.setTflag1("1");
			homeMB.setTflag2("none");
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setUnit(unit);
			homeMB.setStockin("");
			homeMB.setProductName("");
			homeMB.setDicount("");
			/* homeMB.setRoll(""); */
			homeMB.setDicountAmount("");
			homeMB.setRflag2("none");
			homeMB.setBarcode1("");
			homeMB.setBarflag1("none");
			homeMB.setBarflag("1");
			homeMB.setRflag1("1");
			homeMB.setRollQFlag1("none");
			homeMB.setRollQFlag2("1");
			logger.debug("InventoryException home size---------->"
					+ homeMBs.size());
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
		} catch (NullPointerException n) {
			// logger.error("Inside Exception",e);
			setValidate(n.getMessage());
			QuickSaleMB homeMB = new QuickSaleMB();
			homeMB.setSerialno(serialNo);
			// homeMB.setPrice("");
			homeMB.setFlag("1");
			homeMB.setUflag("none");
			homeMB.setRflag2("none");
			homeMB.setRflag1("1");
			homeMB.setTflag1("1");
			homeMB.setStockin("");
			homeMB.setTflag2("none");
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setUnit(unit);
			homeMB.setProductName("");
			homeMB.setBarcode1("");
			homeMB.setBarflag1("none");
			homeMB.setBarflag("1");
			homeMB.setDicount("");
			homeMB.setRoll("");
			homeMB.setDicountAmount("");
			logger.debug("NullPointerException home size---------->"
					+ homeMBs.size());
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	BigDecimal rollQuan1 = BigDecimal.valueOf(0);
	BigDecimal rollQuan2 = BigDecimal.valueOf(0);

	public void quantityChange1(ValueChangeEvent vi) {
		setValidate2("");
		String serialNo = "";
		String serialNo1 = "";
		BigDecimal temp1 = BigDecimal.valueOf(0);
		QuickSaleMB homeMB = new QuickSaleMB();
		logger.debug("------------inside quantityChange-------------");
		try {
			serialNo = vi.getComponent().getAttributes().get("serial").toString();
			String price = vi.getComponent().getAttributes().get("price")
					.toString();
			setValidate("");
			quantity = "" + vi.getNewValue();
			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			logger.debug("serial number2------------>" + serialNo1);
			logger.debug("serial number------------>" + serialNo);
			logger.debug("product name------------>"
					+ homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getProductName());
			logger.debug("selling price--------------->"
					+ homeMBs.get(Integer.parseInt(serialNo) - 1).getPrice());
			logger.debug("Roll ID--------------->"
					+ homeMBs.get(Integer.parseInt(serialNo) - 1).getRoll());
			purchaseOrder.setRollID(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getRoll());

			BigDecimal totalQuantity = BigDecimal.valueOf(0);
			totalQuantity = totalQuantity.add(new BigDecimal(quantity));
			purchaseOrder.setQuantity("" + totalQuantity);
			rollQuan1 = BigDecimal.valueOf(0);
			rollQuan2 = BigDecimal.valueOf(0);

			BigDecimal totquan = new BigDecimal(purchaseOrder.getTotalQuan2())
					.add(new BigDecimal(quantity));
			BigDecimal stock = new BigDecimal(
					purchaseOrder.getTotalnumberofcount());

			stock = stock.setScale(2, RoundingMode.CEILING);
			BigDecimal quantt = new BigDecimal(purchaseOrder.getQuantity());
			quantt = quantt.setScale(2, RoundingMode.CEILING);
			logger.debug("stock" + stock);
			logger.debug("quantt" + quantt);
			int comp = stock.compareTo(quantt);
			int comp1 = stock.compareTo((totquan));
			/*
			 * BigDecimal tq=BigDecimal.valueOf(0); tq=new
			 * BigDecimal(purchaseOrder.getTemptotquan()).add(new
			 * BigDecimal(quantity));
			 */
			logger.debug("temptotquan " + purchaseOrder.getTemptotquan());
			purchaseOrder.setBatchProductName(""
					+ homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getProductName());

			if (purchaseOrder.getBatchProductName().equalsIgnoreCase("")) {
				throw new DemoException("*Select product name");
			} else if (price.equalsIgnoreCase("")) {
				throw new DemoException("*Enter the Price");
			} else if (purchaseOrder.getQuantity().equalsIgnoreCase("")) {
				throw new DemoException("*Enter the quantity");
			} else if (purchaseOrder.getQuantity().equalsIgnoreCase("0")) {
				throw new DemoException("*Quantity should not be zero");
			} /*else if (comp == -1) {
				throw new DemoException("*Quantity cannot be more than "
						+ stock + " , please try again");
			} else if (comp1 == -1) {
				throw new DemoException("*Quantity cannot be more than "
						+ stock + " , please try again");
			}*/
			/*
			 * else if(stock.compareTo(tq)==-1) { throw new
			 * Demo4Exception("*Quantity cannot be more than "
			 * +stock+" for this roll, please try again"); }
			 */
			controller.salesOrder4(purchaseOrder);
			if(new BigDecimal(purchaseOrder.getQuantity1()).compareTo(
					new BigDecimal(purchaseOrder.getQuantity()))==-1){
				temp1=BigDecimal.valueOf(0);
				throw new DemoException("Only "+purchaseOrder.getQuantity1()+" quantity available in stock for "
					+purchaseOrder.getBatchProductName());
			}
		

			String finalRoll1 = "";
			String finalRoll2 = "";
			BigDecimal totquant = BigDecimal.valueOf(0);

			BigDecimal finalQuan1 = BigDecimal.valueOf(0);
			BigDecimal finalQuan2 = BigDecimal.valueOf(0);
			roll2 = new ArrayList<QuickSaleMB>();
			homeMB = new QuickSaleMB();

			if (homeMBs.size() > 0) {
				logger.debug("-------------------inside if loop-----------------");

				homeMB.setSerialno(serialNo);
				homeMB.setPrice(""
						+ (String) vi.getComponent().getAttributes()
								.get("price"));
				homeMB.setFlag("none");
				homeMB.setProductName(""
						+ homeMBs.get(Integer.parseInt(serialNo) - 1)
								.getProductName());
				homeMB.setRollQFlag1("none");
				homeMB.setRollQFlag2("1");
				homeMB.setTflag1("none");
				homeMB.setTflag2("1");
				homeMB.setPriflag1("none");
				homeMB.setFlag3("1");
				homeMB.setPriflag2("1");
				homeMB.setUnit(homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getUnit());
				homeMB.setBarcode1(homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getBarcode1());
				homeMB.setBarflag1("1");
				homeMB.setBarflag("none");
				homeMB.setFlag1("none");
				homeMB.setFlag2("1");
				homeMB.setQuantity(quantity);
				temp1 = (new BigDecimal(homeMBs.get(
						Integer.parseInt(serialNo) - 1).getPrice())
						.multiply(new BigDecimal(quantity)));
				homeMB.setNetAmount("" + temp1);
				homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
			} else {
				throw new DemoException("error");
			}

			logger.debug("----------->" + productlist);
			BigDecimal temp = BigDecimal.valueOf(0);
			for (int i = 0; i < homeMBs.size(); i++) {
				try {
					if (!homeMBs.get(i).getPrice().equals("")) {
						logger.debug("---------------the list has value----------------"
								+ homeMBs.get(i).getProductName());
						temp = temp.add(new BigDecimal(homeMBs.get(i)
								.getNetAmount().replace(",", "")));
						logger.debug("total amunt---------->" + temp);
					} else {
						logger.debug("---------------the list has no value----------------");
					}
				} catch (NullPointerException e) {
					logger.debug("--------------inside null pointer exception----------");
				}
			}

			/*
			 * roll2=new ArrayList<SalesOrderFormMB>();
			 * finalQuan1=BigDecimal.valueOf(0);
			 * finalQuan2=BigDecimal.valueOf(0); for (int
			 * i=0;i<homeMBs.size();i++) {
			 * 
			 * for (int j=0;j<homeMBs.get(i).getRoll1().size();j++) {
			 * if(homeMBs.
			 * get(i).getProductName().equals(homeMBs.get(j).getProductName()))
			 * { if(homeMBs.get(i).getRoll1().get(j).getRoll().equals(roll)) {
			 * 
			 * logger.debug("-->>for loop quan "+homeMBs.get(i).getRoll1().get(j)
			 * .getQuantity());
			 * logger.debug("-->>for loop roll "+homeMBs.get(i).
			 * getRoll1().get(j).getRoll());
			 * logger.debug("-->>for roll1 size "+homeMBs
			 * .get(i).getRoll1().size()); finalQuan1=new
			 * BigDecimal(homeMBs.get(i).getRoll1().get(j).getQuantity());
			 * finalQuan2=finalQuan2.add(finalQuan1);
			 * logger.debug("-->>finalQuan2 "+finalQuan2); try {
			 * if(finalQuan2.compareTo(new BigDecimal(parts[1]))==1) { throw new
			 * InventoryException(roll+
			 * " has been selected, please choose other rolls"); }
			 * 
			 * } catch (InventoryException ie) { setValidate(ie.getMessage());
			 * logger.debug(ie.getMessage()); SalesOrderFormMB st = new
			 * SalesOrderFormMB(); st.setInnerserial1(serialNo1);
			 * st.setFlag1("1"); st.setFlag2("none"); roll2.add(st);
			 * homeMB.setRoll1(roll2); homeMBs.set(Integer.parseInt(serialNo)-1,
			 * homeMB); }
			 * 
			 * } } }
			 * 
			 * }
			 */

			BigDecimal newqunt = new BigDecimal("0");
			String rollTempId = "";
			BigDecimal givenquan = new BigDecimal("0");
			/*
			 * for (int i = 0; i < homeMBs.size(); i++) {
			 * 
			 * try { newqunt=new BigDecimal("0");rollTempId=""; for (int j = 0;
			 * j < homeMBs.size(); j++) { try {
			 * if(homeMBs.get(i).getProductName(
			 * ).equals(homeMBs.get(j).getProductName()) &&
			 * homeMBs.get(i).getRoll().equals(homeMBs.get(j).getRoll())) {
			 * 
			 * if(serialNo.equals(""+(j+1))) { newqunt=newqunt.add(quantt);
			 * 
			 * } else { newqunt=newqunt.add(new
			 * BigDecimal(homeMBs.get(j).getQuantity())); }
			 * 
			 * rollTempId=homeMBs.get(i).getRoll(); } } catch(Exception e) {
			 * 
			 * }
			 * 
			 * } } catch(Exception e) {
			 * 
			 * } finally { logger.debug("==========================");
			 * 
			 * purchaseOrder.setRollID(rollTempId);
			 * controller.getRollQuantity(purchaseOrder); stock=new
			 * BigDecimal(parts[1]);
			 * 
			 * logger.debug("---------stock1-----"+stock);
			 * logger.debug("---------stock2-----"+newqunt); int
			 * comp2=stock.compareTo(newqunt); if(comp2 == -1) { throw new
			 * InventoryException(homeMBs.get(i).getRoll()+
			 * " has been selected, please choose other rolls"); }
			 * 
			 * }
			 * 
			 * }
			 */

			setCrosstotal("" + (temp));
			setTotalPrice("" + temp);

			logger.debug("----success-----");
		} catch (DemoException e) {
			homeMB.setSerialno(serialNo);
			homeMB.setPrice(""
					+ (String) vi.getComponent().getAttributes()
							.get("price"));
			homeMB.setFlag("none");
			homeMB.setProductName(""
					+ homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getProductName());
			homeMB.setRollQFlag1("none");
			homeMB.setRollQFlag2("1");
			homeMB.setTflag1("none");
			homeMB.setTflag2("1");
			homeMB.setPriflag1("none");
			homeMB.setFlag3("1");
			homeMB.setPriflag2("1");
			homeMB.setUnit(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getUnit());
			homeMB.setBarcode1(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getBarcode1());
			homeMB.setBarflag1("1");
			homeMB.setBarflag("none");
			homeMB.setFlag1("none");
			homeMB.setFlag2("1");
			homeMB.setQuantity(quantity);
			
			homeMB.setNetAmount("" + temp1);
			homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			/*
			 * SalesOrderFormMB homeMB=new SalesOrderFormMB();
			 * homeMB.setSerialno(serialNo); homeMB.setPrice("");
			 * homeMB.setStockin(""); homeMB.setFlag("1"); homeMB.setFlag1("1");
			 * homeMB.setFlag2("none"); homeMB.setRflag1("1");
			 * homeMB.setRflag2("none"); homeMB.setRoll("");
			 * homeMB.setPriflag1("1"); homeMB.setPriflag2("none");
			 * homeMB.setTflag1("none"); homeMB.setBarcode1("");
			 * homeMB.setBarflag("1"); homeMB.setBarflag1("none");
			 * homeMB.setTflag2("1"); homeMB.setUnit("");
			 * homeMBs.set(Integer.parseInt(serialNo)-1, homeMB);
			 */
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public String temptotalAmount;

	public String getTemptotalAmount() {
		return temptotalAmount;
	}

	public void setTemptotalAmount(String temptotalAmount) {
		this.temptotalAmount = temptotalAmount;
	}

	public void discAmntListener(ValueChangeEvent cost) {

		String cost1;
		String tempDiscount = "";
		String total = "";
		cost1 = discAmnt;
		try {
			setValidate("");
			tempDiscount = discType;
			cost1 = "" + cost.getNewValue();
			logger.debug("cost1 " + cost1);
			logger.debug("total " + crosstotal);
			logger.debug("temptotalAmount " + temptotalAmount);
			logger.debug("tempDiscount " + tempDiscount);
			BigDecimal tempval = BigDecimal.valueOf(0);
			BigDecimal temp = BigDecimal.valueOf(0);

			if (tempDiscount.equals("%")) {
				int i = Integer.parseInt(cost1);
				if (i < 100) {
					logger.debug("dicount----->" + tempDiscount);
					tempval = new BigDecimal(crosstotal).multiply(
							new BigDecimal(cost1)).divide(
							BigDecimal.valueOf(100));
					logger.debug("tempval-->" + tempval);
					temp = (new BigDecimal(crosstotal).subtract((tempval)));
				} else {
					logger.debug("validation number2");
					throw new DemoException("*Enter discount below 100");
				}
			} else if (tempDiscount.equals("Rp")) {
				disAmount = "" + Integer.parseInt(cost1);
				if (new BigDecimal(crosstotal).compareTo(new BigDecimal(
						disAmount)) == -1) {
					throw new DemoException(
							"*Discount should be  less than total price");
				}
				logger.debug("dicount----->" + cost1);
				temp = (new BigDecimal(crosstotal).subtract(new BigDecimal(
						disAmount)));
			} else {
				disAmount = "0";
				discType = "Rp";
				temp = (new BigDecimal(crosstotal).subtract(new BigDecimal(
						disAmount)));
			}

			/*
			 * BigDecimal temp=(new
			 * BigDecimal(crosstotal.replace(",","")).add(new
			 * BigDecimal(cost1.replace(",",""))));
			 */
			BigDecimal bgg1 = BigDecimal.valueOf(0);
			bgg1 = temp.setScale(0, BigDecimal.ROUND_HALF_UP);
			logger.debug("==value of float===" + bgg1);
			setTotalPrice("" + (bgg1));
		} catch (NumberFormatException n) {
			logger.debug("Inside Number Format Exception");
			cost1 = "0";
			logger.debug("total" + crosstotal);
			BigDecimal temp = (new BigDecimal(crosstotal).add(new BigDecimal(
					cost1)));
			BigDecimal bgg1 = BigDecimal.valueOf(0);
			bgg1 = temp.setScale(0, BigDecimal.ROUND_HALF_UP);
			logger.debug("==value of float===" + bgg1);
			setTotalPrice("" + (bgg1));
		} catch (NullPointerException e) {
			logger.debug("Inside NullPointerException");
			cost1 = "0";
			logger.debug("total" + crosstotal);
			BigDecimal temp = (new BigDecimal(crosstotal).add(new BigDecimal(
					cost1)));
			BigDecimal bgg1 = BigDecimal.valueOf(0);
			bgg1 = temp.setScale(0, BigDecimal.ROUND_HALF_UP);
			logger.debug("==value of float===" + bgg1);
			setTotalPrice("" + (bgg1));
		} catch (DemoException e) {
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
		}

	}

	// prema begin 29/04/2016 dialog box creation for Quick Sales
	public String quicksales() {
		/*Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("quickSaleForm",
				options, null);*/
		quickSalescall();
		setQuicksaleflag1(false);
		setQuicksaleflag(true);
		setPaymentType(null);
		setChequeno(null);
		setBankname(null);
		setTbankname(null);
		setNewbankName(null);
		setAccountno(null);
		setCardno(null);
		setProductName(null);
		setQuantity(null);
		setPrice(null);
		setDicount(null);
		setBarcode(null);
		setCurrency("");
		setAccountType("");
		return "quickSaleForm";
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
			setAccountType(accountname);
		}catch(Exception e){
			logger.warn("-----------Inside Exception-----------------"+e.getMessage());
		}
	}

	// prema end 29/0/2016
	public void quicksalesclose() {
		RequestContext.getCurrentInstance().closeDialog("quickSaleForm");
	}

	public void qsreturnviewclose() {
		RequestContext.getCurrentInstance().closeDialog("quickSaleReturnView");
	}

	public void tabchange(TabChangeEvent event) {
		if (event.getTab().getTitle().equalsIgnoreCase("Quick Sales")) {
			logger.debug("inside Account Payable Liablity if condition");
			quickSaleViewMB.quicksaleview();

		} else if ((event.getTab().getTitle().equalsIgnoreCase("Sales"))) {
			logger.debug("inside Account Receivable Asset if condition");
			salesViewMB.salesView();
		}
	}

	// prema begin 29/04/2016 dialog box creation for Quick Sale Return
	public void quicksalesreturn() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("quickSaleReturn",
				options, null);
		redirectQucikReturn();
	}

	// prema end 29/0/2016
	public void qsreturnclose() {
		RequestContext.getCurrentInstance().closeDialog("quickSaleReturn");
	}

	// prema begin 29/04/2016 dialog box creation for Quick Sale Return view
	public void quicksalereturnview() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("quickSaleReturnView",
				options, null);
		quickViewSalesReturn();
	}
	// prema end 29/0/2016

}