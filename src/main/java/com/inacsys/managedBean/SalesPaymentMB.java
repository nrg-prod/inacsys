package com.inacsys.managedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.SortOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0018;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0021;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "salesPaymentMB")
public class SalesPaymentMB {
	private static Logger logger = Logger.getLogger(SalesPaymentMB.class);
	public String customername;
	public String countryID;
	public Date salesorderdate;
	public String shipingaddress;
	public String phonenumber;
	public String email;
	public String totalnumberofcount;
	public String totalnumberofcount1;
	public String quantity;
	public String sellingPrice;
	public String salesIdReference;
	public Date deliverydate;
	public String note;
	public String shipping_charge;
	public String crosstotal;
	public String shipping_charge1;
	public String crosstotal1;
	public int salesId;
	public String batchProductName;
	public String batchProductName1;
	public int productId;
	List<I0018> batchProductName3;
	public String marginPrice;
	public int batchID;
	public String flag = "none";
	List<I0019> resul;
	public String referenceNumber;
	public String validate;

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String s = "The cross Total Of this sales Order Is";

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	List<I0021> salesreferenumber;

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public List<I0021> getSalesreferenumber() {
		return salesreferenumber;
	}

	public void setSalesreferenumber(List<I0021> salesreferenumber) {
		this.salesreferenumber = salesreferenumber;
	}

	public String getS() {
		return s;
	}

	public String totalPrice;

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
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

	public String getSalesIdReference() {
		return salesIdReference;
	}

	public void setSalesIdReference(String salesIdReference) {
		this.salesIdReference = salesIdReference;
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

	List<I0021> result;

	public List<I0021> getResult() {
		return result;
	}

	public void setResult(List<I0021> result) {
		this.result = result;
	}

	DemoController controller = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();

	public String salepayDirect() {
		setFlag("none");
		setSalesIdReference(null);

		return "salepayDirect";
	}

	public String salesPayment1() {
		logger.info("[salesPayment1()]-------------------inside salesPayment1() method()---------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setSalesreferenumber(controller.salesPayment1(salesreferenumber));
			return "";
		}

		catch (Exception e) {
			logger.error("Inside Exception", e);
			flag = "none";
			return "";
		}
	}

	public String SalesPayment2() {
		logger.info("[salesPayment2()]-------------------inside salesPayment2() method()---------------");
		setValidate("");
		flag = "none";
		crosstotal1 = "0";
		try {

			logger.debug("customer name:::" + getCustomername());
			result = null;
			purchaseOrder.setCustomername(customername);
			purchaseOrder.setSalesorderdate(salesorderdate);
			purchaseOrder.setShipingaddress(shipingaddress);
			purchaseOrder.setPhonenumber(phonenumber);
			purchaseOrder.setEmail(email);
			purchaseOrder.setTotalnumberofcount(totalnumberofcount);
			purchaseOrder.setCrosstotal(crosstotal);
			purchaseOrder.setShipping_charge(shipping_charge);
			purchaseOrder.setTotalnumberofcount1("" + totalnumberofcount1);
			purchaseOrder.setDeliverydate(deliverydate);
			purchaseOrder.setShipping_charge1("" + shipping_charge1);
			purchaseOrder.setNote(note);
			purchaseOrder.setCrosstotal1("" + crosstotal1);
			purchaseOrder.setSalesId(salesId);
			purchaseOrder.setSalesIdReference(salesIdReference);
			purchaseOrder.setResul(resul);
			purchaseOrder.setResult(result);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if (customername.equalsIgnoreCase("")) {
				throw new DemoException("Please Choose the Customer Name");
			}

			if (salesIdReference.equalsIgnoreCase("")) {
				throw new DemoException("Please Choose Sales Order Number");
			}
			controller.salesPayment2(purchaseOrder);
			setResult(purchaseOrder.getResult());
			setTotalPrice(purchaseOrder.getTotalPrice());
			setCrosstotal(purchaseOrder.getCrosstotal());
			if (result.size() > 0) {
				flag = "1";
			}

			return "";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			flag = "none";
			logger.debug(e.getMessage());
			return "";
		}
	}

	/* jeni */

	List<PurchaseOrder> salesPaymentLists = new ArrayList<PurchaseOrder>();

	public List<PurchaseOrder> getSalesPaymentLists() {
		return salesPaymentLists;
	}

	public void setSalesPaymentLists(List<PurchaseOrder> salesPaymentLists) {
		this.salesPaymentLists = salesPaymentLists;
	}

	public String SalesPaymentPending() {
		logger.info("[salesPaymentPending()]-------------------inside salesPaymentPending() method()---------------");
		try {
			flag = "none";
			crosstotal1 = "0";
			result = null;
			purchaseOrder.setCustomername(customername);
			purchaseOrder.setSalesorderdate(salesorderdate);
			purchaseOrder.setShipingaddress(shipingaddress);
			purchaseOrder.setPhonenumber(phonenumber);
			purchaseOrder.setEmail(email);
			purchaseOrder.setTotalnumberofcount(totalnumberofcount);
			purchaseOrder.setCrosstotal(crosstotal);
			purchaseOrder.setShipping_charge(shipping_charge);
			purchaseOrder.setTotalnumberofcount1(totalnumberofcount1);
			purchaseOrder.setDeliverydate(deliverydate);
			purchaseOrder.setShipping_charge1(shipping_charge1);
			purchaseOrder.setNote(note);
			purchaseOrder.setCrosstotal1(crosstotal1);
			purchaseOrder.setSalesId(salesId);
			purchaseOrder.setSalesIdReference(salesIdReference);
			purchaseOrder.setResul(resul);
			purchaseOrder.setResult(result);
			purchaseOrder.setStatus2("paid");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesPaypend(purchaseOrder);
			setSalesPaymentLists(purchaseOrder.getResult5());
			if (salesPaymentLists.size() > 0) {
				flag = "1";
			}
			return "";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			flag = "none";
			logger.debug(e.getMessage());
			return "";
		}
	}

	public String redirectPage() {
		logger.info("[redirectPage()]-------------------inside redirectPage() method()---------------");
		flag = "none";
		setCustomername("");
		setSalesIdReference("");
		return "";
	}

	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public SalesPaymentMB() {
		sortsOrders = new HashMap<String, SortOrder>();
		sortPriorities = new ArrayList<String>();
	}

	public void sort() {
		logger.info("[sort()]-------------------inside sort() method()---------------");
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

	// prema begin 29/04/2016 dialog box creation for sales payment view
	public void salespaymentview() {
		logger.info("[salespaymentview()]-------------------inside salespaymentview() method()---------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("salesPayment", options,
				null);
		redirectPage();
	}

	// prema end 29/0/2016
	public void salespaymentviewclose() {
		logger.info("[salespaymentviewclose()]-------------------inside salespaymentviewclose() method()---------------");
		RequestContext.getCurrentInstance().closeDialog("salesPayment");
	}
}
