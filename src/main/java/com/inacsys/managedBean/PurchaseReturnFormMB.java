package com.inacsys.managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.primefaces.context.RequestContext;
import org.primefaces.model.SortOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.domain.Vendor;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0015;
import com.inacsys.shared.I0018;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0021;
import com.inacsys.shared.I0025;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "purchaseReturnFormMB")
public class PurchaseReturnFormMB implements Serializable {

	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(PurchaseReturnFormMB.class);
	@ManagedProperty(value = "#{purchaseOrderFromMB}")
	PurchaseOrderFromMB purchaseOrderFromMB;
	@ManagedProperty(value = "#{purchaseViewMB}")
	PurchaseViewMB purchaseViewMB;

	public PurchaseOrderFromMB getPurchaseOrderFromMB() {
		return purchaseOrderFromMB;
	}

	public void setPurchaseOrderFromMB(PurchaseOrderFromMB purchaseOrderFromMB) {
		this.purchaseOrderFromMB = purchaseOrderFromMB;
	}

	public PurchaseViewMB getPurchaseViewMB() {
		return purchaseViewMB;
	}

	public void setPurchaseViewMB(PurchaseViewMB purchaseViewMB) {
		this.purchaseViewMB = purchaseViewMB;
	}

	private static final long serialVersionUID = 1L;

	public String firmname;
	public String vendorname;
	public String countryID;
	public Date salesorderdate;
	public String shipingaddress;
	public String phonenumber;
	public String email;
	public String totalnumberofcount;
	public String totalnumberofcount1;
	public String quantity;
	public String sellingPrice;
	public String purchaseIdReference;
	public Date deliverydate;
	public String note;
	private String unit;
	public String shipping_charge;
	public String crosstotal;
	public String shipping_charge1;
	public String crosstotal1;
	public String saleId;
	public int salesId;
	public String batchProductName;
	public String batchProductName1;
	public int productId;
	List<I0018> batchProductName3;
	public String marginPrice;
	public int batchID;
	List<I0019> resul;
	public String s = "cross Total Of ";
	public String crosstotal2;
	public String flag1 = "none";
	public String flag2 = "none";
	public String flag3 = "none";
	public String flag4 = "none";
	public String quantity1;
	public String validate1;
	public String validate2;
	List<String> vendnames = null;

	ArrayList<I0015> purorderresult = null;
	List<PurchaseOrder> finallist = null;
	List<String> finallist2 = null;
	public String tempValidate;
	public String tempFlag1 = "none";
	public String tempFlag2 = "none";
	public String priority;
	List<I0025> venresult = null;
	public int serialNo;
	public String Nr;
	public String Dr;
	private String rollID;
	List<I0015> orderresul = null;

	private String rollStockIn;

	public List<I0015> getOrderresul() {
		return orderresul;
	}

	public void setOrderresul(List<I0015> orderresul) {
		this.orderresul = orderresul;
	}

	public List<I0025> getVenresult() {
		return venresult;
	}

	public void setVenresult(List<I0025> venresult) {
		this.venresult = venresult;
	}

	public String getNr() {
		return Nr;
	}

	public void setNr(String nr) {
		Nr = nr;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getDr() {
		return Dr;
	}

	public void setDr(String dr) {
		Dr = dr;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTempValidate() {
		return tempValidate;
	}

	public void setTempValidate(String tempValidate) {
		this.tempValidate = tempValidate;
	}

	public String getTempFlag1() {
		return tempFlag1;
	}

	public void setTempFlag1(String tempFlag1) {
		this.tempFlag1 = tempFlag1;
	}

	public String getTempFlag2() {
		return tempFlag2;
	}

	public void setTempFlag2(String tempFlag2) {
		this.tempFlag2 = tempFlag2;
	}

	public String getFlag4() {
		return flag4;
	}

	public void setFlag4(String flag4) {
		this.flag4 = flag4;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	Vendor ven = new Vendor();
	PurchaseOrder pur = new PurchaseOrder();

	public String getFirmname() {
		return firmname;
	}

	public void setFirmname(String firmname) {
		this.firmname = firmname;
	}

	public List<String> getFinallist2() {
		return finallist2;
	}

	public void setFinallist2(List<String> finallist2) {
		this.finallist2 = finallist2;
	}

	public List<PurchaseOrder> getFinallist() {
		return finallist;
	}

	public void setFinallist(List<PurchaseOrder> finallist) {
		this.finallist = finallist;
	}

	public String getPurchaseIdReference() {
		return purchaseIdReference;
	}

	public void setPurchaseIdReference(String purchaseIdReference) {
		this.purchaseIdReference = purchaseIdReference;
	}

	public ArrayList<I0015> getPurorderresult() {
		return purorderresult;
	}

	public void setPurorderresult(ArrayList<I0015> purorderresult) {
		this.purorderresult = purorderresult;
	}

	public List<String> getVendnames() {
		return vendnames;
	}

	public void setVendnames(List<String> vendnames) {
		this.vendnames = vendnames;
	}

	public String getValidate2() {
		return validate2;
	}

	public void setValidate2(String validate2) {
		this.validate2 = validate2;
	}

	public String getValidate1() {
		return validate1;
	}

	public void setValidate1(String validate1) {
		this.validate1 = validate1;
	}

	public String getQuantity1() {
		return quantity1;
	}

	public void setQuantity1(String quantity1) {
		this.quantity1 = quantity1;
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

	List<I0021> result;

	public List<I0021> getResult() {
		return result;
	}

	public void setResult(List<I0021> result) {
		this.result = result;
	}

	public String getFlag3() {
		return flag3;
	}

	public void setFlag3(String flag3) {
		this.flag3 = flag3;
	}

	public String getCrosstotal2() {
		return crosstotal2;
	}

	public void setCrosstotal2(String crosstotal2) {
		this.crosstotal2 = crosstotal2;
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public List<I0019> getResul() {
		return resul;
	}

	public void setResul(List<I0019> resul) {
		this.resul = resul;
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

	public String getBatchProductName() {
		return batchProductName;
	}

	public void setBatchProductName(String batchProductName) {
		this.batchProductName = batchProductName;
	}

	public String getBatchProductName1() {
		return batchProductName1;
	}

	public void setBatchProductName1(String batchProductName1) {
		this.batchProductName1 = batchProductName1;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public List<I0018> getBatchProductName3() {
		return batchProductName3;
	}

	public void setBatchProductName3(List<I0018> batchProductName3) {
		this.batchProductName3 = batchProductName3;
	}

	public String getMarginPrice() {
		return marginPrice;
	}

	public void setMarginPrice(String marginPrice) {
		this.marginPrice = marginPrice;
	}

	public int getBatchID() {
		return batchID;
	}

	public void setBatchID(int batchID) {
		this.batchID = batchID;
	}

	public String validate;

	public String getValidate() {
		return validate;
	}

	public String productName;
	ArrayList<I0021> result1 = null;

	public ArrayList<I0021> getResult1() {
		return result1;
	}

	public void setResult1(ArrayList<I0021> result1) {
		this.result1 = result1;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String purchaseReturn1() {
		try {
			setS1(null);
			/* setSalesIdReference(null); */
			setFlag1("none");
			setFlag2("none");
			setFlag3("none");
			setSaleId(null);
			setValidate(null);
			setCurrentDate(null);
			setVendorname("");
			setPurchaseIdReference("");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesDelete();
		} catch (Exception e) {

		} finally {

		}

		/* return "purchaseReturn1"; */
		return "";
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	DemoController controller = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();

	ArrayList<I0021> result2 = null;

	public ArrayList<I0021> getResult2() {
		return result2;
	}

	public void setResult2(ArrayList<I0021> result2) {
		this.result2 = result2;
	}

	public String salereferencenumber;

	public String getSalereferencenumber() {
		return salereferencenumber;
	}

	public void setSalereferencenumber(String salereferencenumber) {
		this.salereferencenumber = salereferencenumber;
	}

	public String purchaseReturn() {
		try {

			logger.debug("inside salesReturn------------------>");

			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesReturnForm3(purchaseOrder);

			return "home";
		}

		catch (Exception ie) {

			logger.debug(ie.getStackTrace());
			return "home";
		}

	}

	/*
	 * public String purchaseReturnInsert() { logger.debug("-->> mb"); try {
	 * setValidate(""); logger.debug("-->> mb try"); ApplicationContext ctx =
	 * FacesContextUtils
	 * .getWebApplicationContext(FacesContext.getCurrentInstance()); controller
	 * = (InventoryController) ctx.getBean("controller");
	 * setDomainlist(finallist);
	 * 
	 * 
	 * for(int i=0;i<finallist.size();i++) {
	 * if(!finallist.get(i).getPriroty().equalsIgnoreCase("true")) {
	 * logger.debug("exception null"); } else {
	 * setTotalQuan1(domainlist.get(i).totalQuan1);
	 * setTotalQuan2(domainlist.get(i).totalQuan2);
	 * setProductName(domainlist.get(i).product_name);
	 * setUnit(domainlist.get(i).unit);
	 * setRollID(domainlist.get(i).getRollID());
	 * purchaseOrder.setProduct_name(productName);
	 * purchaseOrder.setOrderNumber(purchaseIdReference);
	 * purchaseOrder.setReturnDate(currentDate);
	 * purchaseOrder.setRollID(rollID);
	 * logger.debug("-->>mb  Quant 1 "+totalQuan1);
	 * logger.debug("-->>mb  Quant 2 "+totalQuan2);
	 * logger.debug("-->>mb  prod name "+productName);
	 * logger.debug("-->>mb  order no "+purchaseIdReference);
	 * logger.debug("-->>mb  return date "+currentDate);
	 * if(totalQuan1.equalsIgnoreCase("")) { totalQuan1="0"; }
	 * if(totalQuan2.equalsIgnoreCase("")) { totalQuan2="0"; } String
	 * temp=""+(new BigDecimal(totalQuan1).add(new BigDecimal(totalQuan2)));
	 * logger.debug("hhhh"+temp); purchaseOrder.setTotalQuan1(totalQuan1);
	 * purchaseOrder.setTotalQuan2(totalQuan2); if(new
	 * BigDecimal(temp).compareTo(BigDecimal.valueOf(0))==0) { throw new
	 * InventoryException("*Enter quanty for return"); } else { if(new
	 * BigDecimal(temp).compareTo(new
	 * BigDecimal(domainlist.get(i).getQuantity()))==1) { throw new
	 * InventoryException("Enter quantity is greater than available purchase");
	 * } else { controller.purchaseInfocollect(purchaseOrder); int
	 * qty=purchaseOrder.getResul().size(); BigDecimal qty=new
	 * BigDecimal(purchaseOrder.getPurchaseQuantity()); BigDecimal dbquantity=
	 * new BigDecimal(purchaseOrder.getRemaining());
	 * dbquantity=dbquantity.setScale(2, RoundingMode.CEILING);
	 * logger.debug(">>>>>>>>>>>>>>>>>>>>>>>"+qty);
	 * logger.debug(" in mb>>>>>>>>>>>>>>>>>>>>>>>"+dbquantity); if(new
	 * BigDecimal(temp).compareTo(qty)<=0) {
	 * controller.purchaseReturnInsert(purchaseOrder); } else { throw new
	 * InventoryException
	 * ("*Only "+qty+" quantity is avalable in this "+purchaseOrder.getRollID()
	 * +
	 * " id stock for "+purchaseOrder.getOrderNumber()+" already return "+dbquantity
	 * +" quantity");
	 * 
	 * } } } }
	 * 
	 * 
	 * } purchaseReturn1(); return "purchaseReturnz"; } catch
	 * (InventoryException e) { logger.error("Inside Exception",e);
	 * logger.debug(e.getMessage()); setTempValidate(e.getMessage()); return "";
	 * }
	 * 
	 * }
	 */

	/*
	 * public String purchaseReturnInsert() { logger.debug("-->> mb"); try {
	 * setValidate(""); logger.debug("-->> mb try"); ApplicationContext ctx =
	 * FacesContextUtils
	 * .getWebApplicationContext(FacesContext.getCurrentInstance()); controller
	 * = (InventoryController) ctx.getBean("controller");
	 * setDomainlist(finallist);
	 * 
	 * for(int i=0;i<finallist.size();i++) {
	 * 
	 * setTotalQuan1(domainlist.get(i).totalQuan1);
	 * setTotalQuan2(domainlist.get(i).totalQuan2);
	 * setProductName(domainlist.get(i).product_name);
	 * purchaseOrder.setTotalQuan1(totalQuan1);
	 * purchaseOrder.setTotalQuan2(totalQuan2);
	 * purchaseOrder.setProduct_name(productName);
	 * purchaseOrder.setOrderNumber(purchaseIdReference);
	 * purchaseOrder.setReturnDate(currentDate);
	 * logger.debug("-->>mb  Quant 1 "+totalQuan1);
	 * logger.debug("-->>mb  Quant 2 "+totalQuan2);
	 * logger.debug("-->>mb  prod name "+productName);
	 * logger.debug("-->>mb  order no "+purchaseIdReference);
	 * logger.debug("-->>mb  return date "+returnDate);
	 * controller.purchaseReturnInsert(purchaseOrder);
	 * 
	 * 
	 * } } catch (InventoryException e) { logger.error("Inside Exception",e); }
	 * return "";
	 * 
	 * }
	 */

	public String purchaseReturnDamage() {
		try {

			logger.debug("inside salesReturn------------------>");
			if (productName.equals("")) {
				throw new DemoException("*enter the product Name");
			}

			else if (quantity1.equals("")) {
				throw new DemoException("*Enter the Quantity");
			}
			setValidate2("");
			purchaseOrder.setProduct_name(productName);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			for (int i = 0; i < resulfinal1.size(); i++) {
				logger.debug("1final");
				if (productName.equals(resulfinal1.get(i).getProduct_name())) {
					logger.debug("productname" + productName);
					if (Integer.parseInt(quantity1) <= Integer
							.parseInt(resulfinal1.get(i).getQuantity())) {
						logger.debug("quantity" + quantity1);
						for (int j = 0; j < Integer.parseInt(quantity1); j++) {
							logger.debug("loop two");
							controller.salesReturnForm(purchaseOrder);
						}
					} else {
						throw new DemoException(
								"*the quantity you enter is above");
					}
				}
			}

			return "home";
		} catch (DemoException e) {
			flag1 = "none";
			setValidate2(e.getMessage());
			logger.debug(e.getMessage());
			return "";

		}

		catch (Exception ie) {

			logger.debug(ie.getStackTrace());
			return "home";
		} finally {
			productName = null;
			quantity1 = null;
		}

	}

	public String purchaseReturnNormal() {
		try {

			logger.debug("inside sales return normal------------------>");
			if (productName.equals("")) {
				throw new DemoException("*enter the product Name");
			}

			else if (quantity1.equals("")) {
				throw new DemoException("*Enter the Quantity");
			}
			setValidate1("");
			purchaseOrder.setProduct_name(productName);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			for (int i = 0; i < resulfinal1.size(); i++) {
				logger.debug("1final");
				if (productName.equals(resulfinal1.get(i).getProduct_name())) {
					logger.debug("productname" + productName);
					if (Integer.parseInt(quantity1) <= Integer
							.parseInt(resulfinal1.get(i).getQuantity())) {
						logger.debug("quantity" + quantity1);
						for (int j = 0; j < Integer.parseInt(quantity1); j++) {
							logger.debug("loop two");
							controller.salesReturnForm3(purchaseOrder);
						}
					} else {
						throw new DemoException(
								"*the quantity you enter is above");
					}
				}
			}

			return "home";
		} catch (DemoException e) {
			flag1 = "none";
			setValidate1(e.getMessage());
			logger.debug(e.getMessage());
			return "";

		}

		catch (Exception ie) {

			logger.debug(ie.getStackTrace());
			return "home";
		} finally {
			productName = null;
			quantity1 = null;
		}

	}

	List<PurchaseOrder> domainlist = null;
	public String phNo;

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	public List<PurchaseOrder> getDomainlist() {
		return domainlist;
	}

	public void setDomainlist(List<PurchaseOrder> domainlist) {
		this.domainlist = domainlist;
	}

	public void purchaseReturnChangeDrop(ValueChangeEvent v) {

		try {

			validate = null;
			String s = (String) v.getNewValue();
			logger.debug("sssssssssssssss" + s);
			purchaseOrder.setVendor_phone_number(s);
			logger.debug("insiode drop::::");
			// logger.debug("customer name:::"+getCustomername());
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.purchaseReturnValuechangeDrop1(s, purchaseOrder);
			purorderresult = purchaseOrder.getPurorderresult();
			/* finallist=purchaseOrder.getFinallist(); */

			setFinallist2(purchaseOrder.getFinallist2());

			logger.debug("pkid::::::::" + purchaseOrder.getFinallist());
			setDomainlist(purchaseOrder.getDomainlist());
			setTelephonenumber(getDomainlist().get(0).vendorTelephoneNumber);
			telephonenumber = getDomainlist().get(0).vendorTelephoneNumber;
			logger.debug(" in mb phno -->> " + phNo);
			setPhNo(getDomainlist().get(0).vendorTelephoneNumber);
			/*
			 * if(result.size()==0) { throw new
			 * InventoryException("This date DidnOt hava Any Sales>>>>>");
			 * 
			 * }
			 */

		} catch (DemoException e) {
			flag1 = "none";
			setValidate("");
			logger.debug(e.getMessage());

		}

	}

	List<PurchaseOrder> resulfinal1 = null;

	public List<PurchaseOrder> getResulfinal1() {
		return resulfinal1;
	}

	public void setResulfinal1(List<PurchaseOrder> resulfinal1) {
		this.resulfinal1 = resulfinal1;
	}

	public String customerName;
	public String address;
	public String telephonenumber;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String salesView2() {
		ArrayList<String> s2 = null;
		try {

			resulfinal1 = null;
			resul = null;
			logger.debug("inside salesview2::::::::::::");
			validate = null;

			purchaseOrder.setSalesIdReference(salereferencenumber);
			purchaseOrder.setResul(resul);
			// logger.debug("customer name:::"+getCustomername());
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrderViewproduct(purchaseOrder);
			resulfinal1 = purchaseOrder.getResulfinal();
			resul = purchaseOrder.getResul();

			logger.debug("1");
			if (result.size() == 0) {
				logger.debug("2");
				flag1 = "none";
				throw new DemoException("This date DidnOt hava Any Sales>>>>>");

			}

			else {
				logger.debug("3");
				int i = 0;
				s2 = new ArrayList<String>();
				for (PurchaseOrder ii : resulfinal1) {
					logger.debug("4");

					PurchaseReturnFormMB salesReturnFormMB = new PurchaseReturnFormMB();
					logger.debug("5");
					salesReturnFormMB.setProductName(resulfinal1.get(i)
							.getProduct_name());
					logger.debug("6");
					logger.debug("777777777777777777"
							+ salesReturnFormMB.getProductName());
					s2.add(salesReturnFormMB.productName);

					logger.debug("7");
					i++;

				}
				s1 = s2;

				flag1 = "1";
				setCustomerName(purchaseOrder.getCustomerName());
				setTelephonenumber(purchaseOrder.getTelephonenumber());
				setAddress(purchaseOrder.getAddress());
			}

			return "success1";
		}

		catch (DemoException e) {

			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "failure";
		} catch (Exception ie) {
			setValidate("*This oderNumber Did not purschase product");
			logger.error("Inside Exception", ie);
			return "";
		}

	}

	public String saleReturn() {
		ArrayList<String> s2 = null;
		resulfinal1 = null;
		resul = null;
		validate = null;
		try {
			logger.debug("inside salesview2::::::::::::");
			purchaseOrder.setSalesIdReference(salereferencenumber);
			purchaseOrder.setResul(resul);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrderViewproduct(purchaseOrder);
			resulfinal1 = purchaseOrder.getResulfinal();
			resul = purchaseOrder.getResul();
			logger.debug("1");
			if (result.size() == 0) {
				logger.debug("2");
				flag1 = "none";
				throw new DemoException("*This date didnot have any sales");

			}

			else {
				logger.debug("3");
				int i = 0;
				s2 = new ArrayList<String>();
				for (PurchaseOrder ii : resulfinal1) {
					logger.debug("4");

					PurchaseReturnFormMB salesReturnFormMB = new PurchaseReturnFormMB();
					logger.debug("5");
					salesReturnFormMB.setProductName(resulfinal1.get(i)
							.getProduct_name());
					logger.debug("6");
					logger.debug("777777777777777777"
							+ salesReturnFormMB.getProductName());
					s2.add(salesReturnFormMB.productName);

					logger.debug("7");
					i++;

				}
				s1 = s2;

				flag1 = "1";
				setCustomerName(purchaseOrder.getCustomerName());
				setTelephonenumber(purchaseOrder.getTelephonenumber());
				setAddress(purchaseOrder.getAddress());
			}

			return "success3";
		}

		catch (DemoException e) {

			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "failure";
		} catch (Exception ie) {
			setValidate("*This odernumber did not purschase product");
			logger.error("Inside Exception", ie);
			return "";
		}

	}

	List<String> resulfinal = null;

	public List<String> getResulfinal() {
		return resulfinal;
	}

	public void setResulfinal(List<String> resulfinal) {
		this.resulfinal = resulfinal;
	}

	public String purchaseReturnDrop() {
		try {
			logger.debug("insiode drop::::");
			// logger.debug("customer name:::"+getCustomername());
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");

			setVendnames(controller.dropD(vendnames));
			setResulfinal(purchaseOrder.getResulfinal1());
			setTelephonenumber(purchaseOrder.getFirmName());
			logger.debug("pkid::::::::" + purchaseOrder.getResulfinal1());
			/*
			 * if(result.size()==0) { throw new
			 * InventoryException("This date DidnOt hava Any Sales>>>>>");
			 * 
			 * }
			 */

			return "";
		} catch (DemoException e) {

			logger.debug(e.getMessage());
			return "";
		}

	}

	public String salesReturnForm3() {
		try {

			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			crosstotal1 = "0";
			setValidate2("");
			// logger.debug("customer name:::"+getCustomername());
			// shipping_charge1=Float.parseFloat(shipingaddress);
			// crosstotal1=Float.parseFloat(crosstotal);
			// totalnumberofcount1=Float.parseFloat(totalnumberofcount);
			/*
			 * purchaseOrder.setCustomername(customername);
			 * purchaseOrder.setSalesorderdate(salesorderdate);
			 * purchaseOrder.setShipingaddress(shipingaddress);
			 * purchaseOrder.setPhonenumber(phonenumber);
			 * purchaseOrder.setEmail(email);
			 * purchaseOrder.setTotalnumberofcount(totalnumberofcount);
			 * purchaseOrder.setCrosstotal(crosstotal);
			 * purchaseOrder.setShipping_charge(shipping_charge);
			 * purchaseOrder.setTotalnumberofcount1(totalnumberofcount1);
			 * purchaseOrder.setDeliverydate(deliverydate);
			 * purchaseOrder.setShipping_charge1(shipping_charge1);
			 * purchaseOrder.setNote(note);
			 * purchaseOrder.setCrosstotal1(crosstotal2);
			 * purchaseOrder.setSalesId(salesId);
			 * purchaseOrder.setSalesIdReference(saleId);
			 * purchaseOrder.setResul(resul);
			 */
			if (saleId.equalsIgnoreCase("")) {
				throw new DemoException("*Enter the Sales order Number");
			}
			logger.debug("customer name:::" + saleId);
			// logger.debug("customer name:::"+getCustomername());
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrdercancelForm(purchaseOrder);
			logger.debug("1");
			setResul(purchaseOrder.getResul());
			setResult(purchaseOrder.getResult());
			logger.debug("2");
			if (resul.size() == 0) {
				throw new DemoException(
						"*This order number didnot purchase any Product");
			}
			/*
			 * setCustomername(purchaseOrder.getResul().get(0).getI0018().
			 * getProductName()); logger.debug("3"); //flag1="1"; flag2="1";
			 */

			setCrosstotal2(purchaseOrder.getResul().get(0).getI0021()
					.getCrossTotal());
			logger.debug("pkid::::::::" + purchaseOrder.getSalesId());
			return "";
		} catch (DemoException e) {
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "";
		}

	}

	public String salesReturnForm1() {
		try {
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			if (purchaseOrder.getResul().size() == 0) {
				throw new DemoException(
						"wrong.....enter the sales order number");
			}
			return "success";
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return "failure";
		}
	}

	public String salesReturnForm4() {
		try {
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			if (purchaseOrder.getResul().size() == 0) {
				throw new DemoException(
						"wrong.....enter the sales order number");
			}
			return "success2";
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return "failure2";
		}
	}

	public String salesReturnForm2() {
		try {
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			if (purchaseOrder.getResul().size() == 0) {
				throw new DemoException(
						"wrong.....enter the sales order number");
			}

			/*
			 * logger.debug("customer name:::"+getCustomername());
			 */ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesReturnForm2(purchaseOrder);

			return "home";
		} catch (DemoException e) {
			logger.debug(e.getMessage());
			return "failure1";
		}

		finally {
			resul = null;
			purchaseOrder.setResul(null);

		}
	}

	public String salesReturnForm5() {
		try {
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			if (purchaseOrder.getResul().size() == 0) {
				throw new DemoException("wrong.....enter the sales orde number");
			}

			/*
			 * logger.debug("customer name:::"+getCustomername());
			 */ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesReturnForm5(purchaseOrder);

			return "home";
		} catch (DemoException e) {
			logger.debug(e.getMessage());
			return "home";
		}

		finally {
			resul = null;
			purchaseOrder.setResul(null);

		}
	}

	public String purchaseReturnSubmit() {
		try {
			logger.debug("<---------------inside sales return submit------------------->");
			logger.debug("list size---------------------->"
					+ resulfinal1.size());
			logger.debug("sales id------------------>"
					+ purchaseOrder.getSalesId());
			purchaseOrder.setResulfinal(resulfinal1);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			/* controller.salesReturnSubmit(purchaseOrder); */
			return "purchaseReturnSubmitSuccess";
		} catch (Exception e) {
			logger.debug("----------------inside exception-------------------");
			logger.error("Inside Exception", e);
			return "purchaseReturnSubmitFailure";
		}
	}

	public void checkBox(ValueChangeEvent v) {
		logger.debug("inside check change");
		tempValidate = "";
		logger.debug("change value------------------>" + v.getNewValue());
		logger.debug("value flag1---------------->"
				+ v.getComponent().getAttributes().get("flag1"));
		logger.debug("value flag2---------------->"
				+ v.getComponent().getAttributes().get("flag2"));
		logger.debug("value serial---------------->"
				+ v.getComponent().getAttributes().get("serial").toString());
		if (("" + v.getNewValue()).equals("true")) {

			String s = "" + v.getComponent().getAttributes().get("serial");
			logger.debug("ftt" + v.getComponent().getAttributes().get("serial"));
			int index = Integer.parseInt(s) - 1;

			PurchaseOrder order = new PurchaseOrder();

			order.setTempFlag1("1");

			order.setTempFlag2("1");

			order.setPriroty("true");

			order.setQuantity(""
					+ v.getComponent().getAttributes().get("quant"));

			order.setProduct_name((String) v.getComponent().getAttributes()
					.get("pName"));
			order.setRollID((String) v.getComponent().getAttributes()
					.get("rolL"));
			order.setSerialNo(Integer.parseInt(v.getComponent().getAttributes()
					.get("serial").toString()));
			order.setTotalQuan1("");
			order.setUnit((String) v.getComponent().getAttributes().get("uniT"));
			order.setTotalQuan2("");

			finallist.set((index), order);

		} else {
			logger.debug("inside else");
			String s = v.getComponent().getAttributes().get("serial")
					.toString();
			int index = Integer.parseInt(s) - 1;
			logger.debug("index----------------->" + index);

			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("none");
			order.setTempFlag2("none");
			order.setPriroty("false");
			order.setQuantity((String) v.getComponent().getAttributes()
					.get("quant"));
			order.setProduct_name((String) v.getComponent().getAttributes()
					.get("pName"));
			order.setRollID((String) v.getComponent().getAttributes()
					.get("rolL"));
			order.setSerialNo(Integer.parseInt(v.getComponent().getAttributes()
					.get("serial").toString()));
			order.setNr("");
			order.setUnit((String) v.getComponent().getAttributes().get("uniT"));

			order.setDr("");
			finallist.set((index), order);
		}

		logger.debug("-------------------success fully completed---------------------");
	}

	public void nrChange(ValueChangeEvent v) {
		logger.debug("inside nr change");
		String s = v.getComponent().getAttributes().get("serial").toString();
		int index = Integer.parseInt(s) - 1;
		tempValidate = "";
		try {
			String enterednr = "0";
			String entereddr = "0";
			enterednr = v.getNewValue().toString();
			logger.debug("entered nr values" + enterednr);
			entereddr = v.getComponent().getAttributes().get("dr").toString();
			String stockin = v.getComponent().getAttributes().get("quant")
					.toString();
			logger.debug("Stock" + stockin);
			logger.debug("entered dr values" + entereddr);
			float temp = (float) 0.0;
			try {
				temp = Float.parseFloat(enterednr)
						+ Float.parseFloat(entereddr);
			} catch (NumberFormatException e) {
				logger.debug("--------------inisde exception------------------");
				entereddr = "0";
			}
			if (Float.parseFloat(stockin) < Float.parseFloat(enterednr)) {
				throw new DemoException(
						"Out of Stock : Enter the Correct Quantity");
			} else if (Float.parseFloat(stockin) < temp) {
				throw new DemoException(
						"Out of Stock: Enter the Correct Quantity");
			}

			logger.debug("index----------------->" + index);

			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("1");
			order.setTempFlag2("1");
			order.setPriroty("" + v.getComponent().getAttributes().get("pri"));
			order.setQuantity(v.getComponent().getAttributes().get("quant")
					.toString());
			order.setProduct_name(v.getComponent().getAttributes().get("pName")
					.toString());
			order.setSerialNo(Integer.parseInt(v.getComponent().getAttributes()
					.get("serial").toString()));
			order.setTotalQuan1(v.getNewValue().toString());
			order.setTotalQuan2(v.getComponent().getAttributes()
					.get("dr").toString());
			/*order.setRollID(v.getComponent().getAttributes().get("rolL")
					.toString());
			order.setUnit(v.getComponent().getAttributes().get("uniT")
					.toString());*/
			order.setNr("" + v.getNewValue().toString());
			order.setDr(v.getComponent().getAttributes().get("dr").toString());
			finallist.set((index), order);

		} catch (DemoException e) {
			logger.debug("Eroor");
			setTempValidate(e.getMessage());
			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("none");
			order.setTempFlag2("none");
			order.setPriroty("false");
			order.setQuantity(v.getComponent().getAttributes().get("quant")
					.toString());
			order.setProduct_name(v.getComponent().getAttributes().get("pName")
					.toString());
			order.setSerialNo(Integer.parseInt(v.getComponent().getAttributes()
					.get("serial").toString()));
			order.setNr("0");
			order.setDr(v.getComponent().getAttributes().get("dr").toString());
			/*order.setRollID(v.getComponent().getAttributes().get("rolL")
					.toString());
			order.setUnit(v.getComponent().getAttributes().get("uniT")
					.toString());*/
			order.setTotalQuan1("0");
			order.setTotalQuan2(v.getComponent().getAttributes()
					.get("nr").toString());
			logger.debug("---------inide exception end-------------");
			finallist.set((index), order);
		}

	}

	public void drChange(ValueChangeEvent v) {
		tempValidate = "";
		String s = ""
				+ v.getComponent().getAttributes().get("serial").toString();
		int index = Integer.parseInt(s) - 1;
		try {
			String enterednr = "0";
			String entereddr = "0";
			entereddr = v.getNewValue().toString();
			logger.debug("entered dr values" + entereddr);
			enterednr = v.getComponent().getAttributes().get("nr").toString();
			String stockin = v.getComponent().getAttributes().get("quant")
					.toString();
			logger.debug("Stock" + stockin);
			logger.debug("entered nr values" + enterednr);
			float temp = (float) 0.0;
			try {
				temp = Float.parseFloat(enterednr)
						+ Float.parseFloat(entereddr);
			} catch (NumberFormatException e) {
				logger.debug("--------------inisde exception------------------");
				enterednr = "0";
			}
			if (Float.parseFloat(stockin) < Float.parseFloat(enterednr)) {
				throw new DemoException(
						"Out of Stock : Enter the Correct Quantity");
			} else if (Float.parseFloat(stockin) < temp) {
				throw new DemoException(
						"Out of Stock: Enter the Correct Quantity");
			}

			logger.debug("index----------------->" + index);
		} catch (DemoException e) {
			logger.debug("inside value---------->" + e.getMessage());
			setTempValidate(e.getMessage());
		}

	}

	public String purchaseRetForminfo() {
		try {

			ArrayList<String> s2 = null;
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			flag4 = "none";
			validate = null;
			crosstotal1 = "0";
			setValidate1("");

			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			pur.setVendorname(vendorname);
			pur.setPurchaseIdReference(purchaseIdReference);

			controller.purchaseRetViewForm(pur);
			pur.setPurchaseIdReference(pur.getOrderNumber());
			pur.setVendorname(pur.getVendor_phone_number());
			pur.setTelephonenumber(pur.getFirmName());
			logger.debug("Telephonenumber" + pur.getFirmName());
			logger.debug("1");

			setResul(purchaseOrder.getResul());
			setResult(purchaseOrder.getResult());
			/*
			 * setOrderresul(pur.getOrderresul()); logger.debug("2");
			 * 
			 * s2=new ArrayList<String>(); if(orderresul.size()>0) { int
			 * i=0,count=0; for (i=0;i<orderresul.size();i++) {
			 * logger.debug("4");
			 * 
			 * PurchaseReturnFormMB pReturnFormMB=new PurchaseReturnFormMB();
			 * logger.debug("5"); pReturnFormMB.setProductName(productName);
			 * 
			 * logger.debug("6");
			 * logger.debug("777777777777777777"+pReturnFormMB
			 * .getProductName()); s2.add(pReturnFormMB.productName);
			 * 
			 * logger.debug("7"); count++;
			 * 
			 * }
			 * 
			 * s1=s2; flag1="1";
			 * setVendorname(purchaseOrder.getVendorPhoneNumber());
			 * setTelephonenumber(purchaseOrder.getTelephonenumber());
			 * setQuantity(purchaseOrder.getQuantity());
			 * setAddress(purchaseOrder.getAddress()); flag4="1";
			 * 
			 * 
			 * } else { throw new
			 * InventoryException("*This order number didnot purchase any Product"
			 * ); }
			 * 
			 * if(resul.size()==0) { throw new
			 * InventoryException("*This order number didnot purchase any Product"
			 * ); } flag1="1"; flag3="1"; flag4="1"; //flag3="none";
			 * setCustomername
			 * (purchaseOrder.getResul().get(0).getI0018().getProductName());
			 * logger.debug("3");
			 * 
			 * setCrosstotal1(purchaseOrder.getResul().get(0).getI0021().
			 * getCrossTotal());
			 * logger.debug("pkid::::::::"+purchaseOrder.getSalesId());
			 */
			return "purchaseRetForminfo";
		} catch (DemoException e) {
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			flag4 = "none";
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "purchaseRetForminfo";
		}

	}

	/*
	 * public String purInfoReturnView() { ArrayList<String> s2 = null; try {
	 * logger.debug("in mb phno "+getTelephonenumber());
	 * setTelephonenumber(pur.vendorTelephoneNumber);
	 * 
	 * tempValidate=""; resulfinal1=null; flag4="none";
	 * logger.debug("----------------inside salesReturnView--------------------"
	 * ); validate=null; if(currentDate==null){ throw new
	 * InventoryException("Please Choose Current Date"); }else
	 * if(vendorname.equalsIgnoreCase("--- Select ---") ||
	 * vendorname.equalsIgnoreCase("")){ throw new
	 * InventoryException("Please Choose Vendor Name"); }else
	 * if(purchaseIdReference.equalsIgnoreCase("--- Select ---") ||
	 * purchaseIdReference.equalsIgnoreCase("")){ throw new
	 * InventoryException("Please Choose Purchase Order Number"); }
	 * purchaseOrder.setVendorname(vendorname);
	 * purchaseOrder.setPurchaseIdReference(purchaseIdReference);
	 * purchaseOrder.setReturnDate(currentDate);
	 * logger.debug("currnt dat "+currentDate+getCurrentDate());
	 * ApplicationContext ctx =
	 * FacesContextUtils.getWebApplicationContext(FacesContext
	 * .getCurrentInstance()); controller = (InventoryController)
	 * ctx.getBean("controller"); controller.purOrderViewproduct(purchaseOrder);
	 * finallist=purchaseOrder.getFinallist();
	 * logger.debug("in mb size-->> "+finallist.size()); if(finallist.size()==0)
	 * { flag1="none"; throw new
	 * InventoryException("*This PO is not added to stock"); }
	 * if(finallist.size()==0) { flag1="none"; throw new
	 * InventoryException("This  Purchase Order Number Is Not Added To StockIN"
	 * ); } setProductName(purchaseOrder.getProduct_name());
	 * setUnit(purchaseOrder.getUnit());
	 * setQuantity(purchaseOrder.getQuantity());
	 * setSerialNo(purchaseOrder.getSerialNo());
	 * 
	 * flag1="1"; setCustomerName(purchaseOrder.getCustomerName());
	 * setTelephonenumber(purchaseOrder.getTelephonenumber());
	 * setAddress(purchaseOrder.getAddress()); flag4="1"; return ""; } catch
	 * (InventoryException e) {
	 * 
	 * setValidate(e.getMessage()); logger.debug(e.getMessage()); return ""; }
	 * catch(Exception ie) {
	 * setValidate("*This oderNumber Did not purschase product");
	 * ilogger.error("Inside Exception",e); return ""; }
	 * 
	 * }
	 */

	List<PurchaseOrder> mb2 = new ArrayList<PurchaseOrder>();
	List<PurchaseOrder> mb3 = new ArrayList<PurchaseOrder>();
	public Date returnDate;
	public String reason;
	public String returnQuan1;
	public String returnQuan2;
	public String porderNo;
	public String vendorName;
	public String orderDate;
	public String totalQuan2;
	public String totalQuan1;
	public Date fromDate;
	public Date toDate;

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/*
	 * public Date getOrderDate() { return orderDate; } public void
	 * setOrderDate(String string) { this.orderDate = string; }
	 */
	public List<PurchaseOrder> getMb3() {
		return mb3;
	}

	public void setMb3(List<PurchaseOrder> mb3) {
		this.mb3 = mb3;
	}

	public String getTotalQuan1() {
		return totalQuan1;
	}

	public void setTotalQuan1(String totalQuan1) {
		this.totalQuan1 = totalQuan1;
	}

	public String getTotalQuan2() {
		return totalQuan2;
	}

	public void setTotalQuan2(String totalQuan2) {
		this.totalQuan2 = totalQuan2;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getPorderNo() {
		return porderNo;
	}

	public void setPorderNo(String porderNo) {
		this.porderNo = porderNo;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public List<PurchaseOrder> getMb2() {
		return mb2;
	}

	public void setMb2(List<PurchaseOrder> mb2) {
		this.mb2 = mb2;
	}

	public String viewPurchaseReturn() {
		logger.debug("-->> mb");
		try {
			/*
			 * if(fromDate==null) { throw new
			 * DemoException("Please Choose the From Date"); } else
			 * if(toDate==null) { throw new
			 * DemoException("Please Choose the To Date"); }
			 */
			logger.debug("-->> mb try");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			/*
			 * purchaseOrder.setFromDate(fromDate);
			 * purchaseOrder.setToDate(toDate);
			 */
			controller.viewPurchaseReturn(purchaseOrder);
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
				flag4 = "1";
				logger.debug("inside else");
			}

		} catch (DemoException e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
		}
		return "";

	}

	public String viewPurchaseReturnDetail() {
		logger.debug("-->> mb");
		try {
			setValidate("");
			logger.debug("-->> mb try");
			/*
			 * purchaseOrder.setFromDate(fromDate);
			 * purchaseOrder.setToDate(toDate);
			 */
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setOrderNumber(porderNo);
			logger.debug("-->> order no " + getPorderNo());
			controller.viewPurchaseReturnDetail(purchaseOrder);
			if (purchaseOrder.domain2.size() > 0) {

				logger.debug("inside mb if");
				setMb3(purchaseOrder.getDomain2());
			} else {
				logger.debug("inside else");
				throw new DemoException("This purchase order number not found");
			}
			setVendorName(mb3.get(0).getVendorPhoneNumber());
			setOrderDate(new SimpleDateFormat("dd/MM/yyyy").format(mb3.get(0)
					.getOrderDate()));
			logger.debug("inside mb end" + getOrderDate());
		} catch (DemoException e) {
			setValidate(e.getMessage());
			// logger.error("Inside Exception",e);
		}
		return "purReturnView1";

	}

	public String purchaseReturnPage() {
		setValidate("");
		mb3.clear();
		return "purReturnView2";
	}

	public String purchaseReturnRedirect() {
		flag1 = "none";
		setValidate("");
		setFromDate(null);
		setToDate(null);
		mb3.clear();
		flag4 = "none";
		/* return "purReturnView3"; */
		return "";
	}

	public String cancel() {
		flag1 = "none";
		flag4 = "none";
		setValidate("");
		setFromDate(null);
		setToDate(null);
		return "purchaseReturnView";

	}

	public String todayDate;
	public Date currentDate;

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRollID() {
		return rollID;
	}

	public void setRollID(String rollID) {
		this.rollID = rollID;
	}

	public String getRollStockIn() {
		return rollStockIn;
	}

	public void setRollStockIn(String rollStockIn) {
		this.rollStockIn = rollStockIn;
	}

	BigDecimal sq = BigDecimal.valueOf(0);
	BigDecimal ss = BigDecimal.valueOf(0);

	public BigDecimal getSq() {
		return sq;
	}

	public void setSq(BigDecimal sq) {
		this.sq = sq;
	}

	public BigDecimal getSs() {
		return ss;
	}

	public void setSs(BigDecimal ss) {
		this.ss = ss;
	}

	public String newq;

	public String getNewq() {
		return newq;
	}

	public void setNewq(String newq) {
		this.newq = newq;
	}

	BigDecimal rollss = BigDecimal.valueOf(0);

	public BigDecimal getRollss() {
		return rollss;
	}

	public void setRollss(BigDecimal rollss) {
		this.rollss = rollss;
	}

	public String purInfoReturnView() {
		ArrayList<String> s2 = null;
		try {
			setValidate("");
			sq = BigDecimal.valueOf(0);
			ss = BigDecimal.valueOf(0);
			flag1 = "none";
			logger.debug("in mb phno " + getTelephonenumber());
			setTelephonenumber(pur.vendorTelephoneNumber);
			tempValidate = "";
			resulfinal1 = null;
			flag4 = "none";
			logger.debug("----------------inside salesReturnView--------------------");
			/* validate=null; */

			/*
			 * if(currentDate==null) { throw new
			 * DemoException("Please Enter the Date"); } else
			 * if(vendorname.equalsIgnoreCase("")) { throw new
			 * DemoException("Please Select the Vendor Name"); } else
			 * if(purchaseIdReference.equalsIgnoreCase("")) { throw new
			 * DemoException("Please Select the Order Number"); }
			 */
			purchaseOrder.setVendorname(purchaseViewMB.vendor_phone_number);
			logger.debug("vendor name" + purchaseOrder.getVendorname());
			purchaseOrder.setPurchaseIdReference(purchaseViewMB.orderNumber);
			logger.debug("po number" + purchaseOrder.getPurchaseIdReference());
			purchaseOrder.setReturnDate(currentDate);
			logger.debug("currnt dat " + currentDate + getCurrentDate());
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.purOrderViewproduct(purchaseOrder);
			finallist = purchaseOrder.getFinallist();
			logger.debug("in mb size-->>1 " + purchaseOrder.getFinallist());
			logger.debug("in mb size-->>2 " + finallist.size());
			int s;
			s = finallist.size();
			setRollss(new BigDecimal(s));
			if (finallist.size() > 0) {
				for (int i = 0; i < finallist.size(); i++) {
					setProductName(purchaseOrder.getProduct_name());
					setUnit(purchaseOrder.getUnit());

					setQuantity(purchaseOrder.getQuantity());
					logger.debug("Quantity~~~~~~"
							+ finallist.get(i).getQuantity());
					setNewq(finallist.get(i).getQuantity());
					logger.debug("~~new quans~~~~" + newq);
					ss = ss.add(new BigDecimal(finallist.get(i).getQuantity()));
					setSerialNo(purchaseOrder.getSerialNo());

					flag1 = "1";
					setCustomerName(purchaseOrder.getCustomerName());
					setTelephonenumber(purchaseOrder.getTelephonenumber());
					setAddress(purchaseOrder.getAddress());
					flag4 = "1";
				}
				setSq(ss);

			} else {
				purchaseOrderFromMB.setPurchaseviewFlag(false);

			}
			return "";

		} catch (DemoException e) {
			/* setTempValidate(e.getMessage()); */
			setValidate(e.getMessage());
			/* setValidate("*Enter the Date"); */
			logger.debug(e.getMessage());
			return "";
		}

	}

	/* jency */
	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public PurchaseReturnFormMB() {
		sortsOrders = new HashMap<String, SortOrder>();
		sortPriorities = new ArrayList<String>();
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

	public String purchaseReturnInsert() {
		logger.debug("-->> mb");
		try {
			setValidate("");
			logger.debug("-->> mb try");

			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setDomainlist(finallist);
			int ii = 0;
			int cc = 0;
			for (ii = 0; ii < finallist.size(); ii++) {
				if (finallist.get(ii).getPriroty().equalsIgnoreCase("true")) {
					logger.debug("exception null");
					cc++;
				}
			}
			if (cc == 0) {
				throw new DemoException("*Enter the Quantity to Return");
			}

			for (int i = 0; i < finallist.size(); i++) {
				BigDecimal qty = BigDecimal.valueOf(0);
				BigDecimal retqty = BigDecimal.valueOf(0);
				BigDecimal qty1 = BigDecimal.valueOf(0);
				BigDecimal dbquantity = BigDecimal.valueOf(0);

				if (!finallist.get(i).getPriroty().equalsIgnoreCase("true")) {
					logger.debug("exception null");
				} else {
					setTotalQuan1(domainlist.get(i).totalQuan1);
					setTotalQuan2(domainlist.get(i).totalQuan2);
					setProductName(domainlist.get(i).product_name);
					setUnit(domainlist.get(i).unit);
					setRollID(domainlist.get(i).getRollID());
					purchaseOrder.setProduct_name(productName);
					purchaseOrder.setOrderNumber(purchaseViewMB
							.getOrderNumber());
					purchaseOrder.setReturnDate(currentDate);
					purchaseOrder.setRollID(rollID);
					logger.debug("-->>mb  Quant 1 " + totalQuan1);
					logger.debug("-->>mb  Quant 2 " + totalQuan2);
					logger.debug("-->>mb  prod name " + productName);
					logger.debug("-->>mb  order no "
							+ purchaseOrder.getOrderNumber());
					logger.debug("-->>mb  return date " + currentDate);
					if (totalQuan1.equalsIgnoreCase("")) {
						totalQuan1 = "0";
					}
					if (totalQuan2.equalsIgnoreCase("")) {
						totalQuan2 = "0";
					}
					String temp = ""
							+ (new BigDecimal(totalQuan1).add(new BigDecimal(
									totalQuan2)));
					logger.debug("hhhh" + temp);
					purchaseOrder.setTotalQuan1(totalQuan1);
					purchaseOrder.setTotalQuan2(totalQuan2);
					if (new BigDecimal(temp).compareTo(BigDecimal.valueOf(0)) == 0) {
						throw new DemoException("*Enter the Quantity");
					} else {
						if (new BigDecimal(temp).compareTo(new BigDecimal(
								domainlist.get(i).getQuantity())) == 1) {
							throw new DemoException(
									"Enter Quantity is Greater than Available Purchase");
						} else {

							controller.purchaseInfocollect(purchaseOrder);
							logger.debug("-->> tot ret quan "
									+ purchaseOrder.getReturnedQ());

							qty = new BigDecimal(totalQuan1)
									.add(new BigDecimal(totalQuan2));
							retqty = (new BigDecimal(
									purchaseOrder.getReturnedQ()));
							qty1 = new BigDecimal(purchaseOrder.getReturnedQ())
									.add(new BigDecimal(totalQuan1)).add(
											new BigDecimal(totalQuan2));
							dbquantity = new BigDecimal(
									purchaseOrder.getRemaining());
							logger.debug(">>>>>>>>>>>>>>>>>>>>>>>" + qty);
							logger.debug(" in mb>>>>>>>>>>>>>>>>>>>>>>>"
									+ dbquantity);
							logger.debug("quantity 1" + qty1 + "qqq"
									+ purchaseOrder.getPurchaseQuantity());
							if ((qty1).compareTo(new BigDecimal(purchaseOrder
									.getPurchaseQuantity())) >= 1) {
								throw new DemoException("* "
										+ purchaseOrder.getOrderNumber()
										+ " Already Returned " + retqty
										+ " Quantity");
							}
							if ((qty).compareTo(dbquantity) <= 0) {
								purchaseOrder.setQ(qty1);
								controller.purchaseReturnInsert(purchaseOrder);
								RequestContext.getCurrentInstance().execute(
										"PF('purret').show();");
							} else {
								throw new DemoException("* Only " + dbquantity
										+ " Quantity is Available in the Stock");
							}
						}
					}
				}

			}
			/* purchaseReturn1(); */
			return "";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			logger.debug(e.getMessage());
			setTempValidate(e.getMessage());
			return "";
		}

	}

	// prema begin 29/04/2016 dialog box creation for purchase return
	public void purchasereturn() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("purchaseReturn",
				options, null);
		purchaseReturn1();
	}

	// prema end 29/0/2016

	// prema begin 29/04/2016 dialog box creation for purchase return view
	public void purchasereturnview() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("purchaseReturnView",
				options, null);
		viewPurchaseReturn();
	}

	// prema end 29/0/2016
	public void purchasereturnclose() {
		RequestContext.getCurrentInstance().closeDialog("purchaseReturn");
	}

	public void purchasereturnviewclose() {
		RequestContext.getCurrentInstance().closeDialog("purchaseReturnView");
	}
}
