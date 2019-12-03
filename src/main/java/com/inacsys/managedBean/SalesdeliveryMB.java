package com.inacsys.managedBean;

import java.math.BigDecimal;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

//import antlr.collections.List;






import com.inacsys.controler.DemoController;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.Purchase;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0018;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0021;
import com.inacsys.shared.SalesRecord;
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

@ManagedBean(name = "salesdeliveryMB")
public class SalesdeliveryMB {
	private static Logger logger = Logger.getLogger(SalesdeliveryMB.class);
	public String customername;
	public String countryID;
	public Date salesorderdate;
	public String shipingaddress;
	public String phonenumber;
	public String email;
	public String totalnumberofcount;
	public float totalnumberofcount1;
	public String quantity;
	public float sellingPrice;
	public String salesIdReference;
	public Date deliverydate;
	public String note;
	public String shipping_charge;
	public String crosstotal;
	public float shipping_charge1;
	public float crosstotal1;
	public int salesId;
	public String batchProductName;
	public String batchProductName1;
	public int productId;
	List<I0018> batchProductName3;
	public float marginPrice;
	public int batchID;
	public String flag = "none";
	List<I0019> resul;
	List<SalesRecord> resul1;
	public String orderNumber;

	public List<SalesRecord> getResul1() {
		return resul1;
	}

	public void setResul1(List<SalesRecord> resul1) {
		this.resul1 = resul1;
	}

	public String s = "The cross Total Of this sales Order Is";

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public float getTotalnumberofcount1() {
		return totalnumberofcount1;
	}

	public void setTotalnumberofcount1(float totalnumberofcount1) {
		this.totalnumberofcount1 = totalnumberofcount1;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public float getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(float sellingPrice) {
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

	public float getShipping_charge1() {
		return shipping_charge1;
	}

	public void setShipping_charge1(float shipping_charge1) {
		this.shipping_charge1 = shipping_charge1;
	}

	public float getCrosstotal1() {
		return crosstotal1;
	}

	public void setCrosstotal1(float crosstotal1) {
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

	public float getMarginPrice() {
		return marginPrice;
	}

	public void setMarginPrice(float marginPrice) {
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

	public void setValidate(String validate) {
		this.validate = validate;
	}

	List<I0021> result;

	public List<I0021> getResult() {
		return result;
	}

	public void setResult(List<I0021> result) {
		this.result = result;
	}

	List<I0021> result1 = null;

	public List<I0021> getResult1() {
		return result1;
	}

	public void setResult1(List<I0021> result1) {
		this.result1 = result1;
	}

	DemoController controller = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();

	public String salereferencenumber;

	public String getSalereferencenumber() {
		return salereferencenumber;
	}

	public void setSalereferencenumber(String salereferencenumber) {
		this.salereferencenumber = salereferencenumber;
	}

	public String saleDirect() {
		logger.info("[saleDirect()]------------------- inside saleDirect method() ---------------");
		try {
			setFlag("none");
			setResult1(null);
			result1.clear();
			result.clear();
			setValidate(null);
			salesIdReference = "";
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesDelete();
		} catch (DemoException a) {
			logger.error("Inside Exception", a);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

		return "";
	}

	public String salesView2() {
		logger.info("[salesView2()]------------------- inside salesView2 method() ---------------");		
		try {
			validate = null;
			purchaseOrder.setSalesIdReference(salereferencenumber);
			purchaseOrder.setResul(resul);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrdercancelForm(purchaseOrder);
			resul = purchaseOrder.getResul();
			logger.debug("pkid::::::::" + purchaseOrder.getSalesId());
			if (resul.get(0).getStatus().equals(null)) {
				logger.info("[salesView2()]------------------- inside if condition status ---------------");
			}
			if (result.size() == 0) {
				logger.info("[salesView2()]------------------- inside if result size ---------------");
				throw new DemoException("*This date Didnot have Any Sales");

			} else {
				logger.info("[salesView2()]------------------- inside else result size ---------------");
			}

			return "success3";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "failure";
		} catch (Exception ie) {
			setValidate("*This orderNumber Did not purschase product");
			logger.error("Inside Exception", ie);
			logger.debug(ie.getStackTrace());
			return "";
		}

	}

	public String salesdeliveryDrop() {
		logger.info("[salesdeliveryDrop()]------------------- inside salesdeliveryDrop method() ---------------");
		try {
			crosstotal1 = 0;
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrdercancel(purchaseOrder);
			setResult(purchaseOrder.getResult());
			setResul(purchaseOrder.getResul());
			return "";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate("");
			setResult(null);
			setResul(null);
			result1 = null;
			logger.debug(e.getMessage());
			return "";
		}

	}

	public String salesdeliveryMB() {
		logger.info("[salesdeliveryMB()]------------------- inside salesdeliveryMB method() ---------------");
		try {
			flag = "none";
			validate = "";
			crosstotal1 = 0;
			setResult1(null);
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
			if (salesIdReference.equalsIgnoreCase("")) {
				throw new DemoException("Please Choose the Sales Order Number");
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrdercancelForm4(purchaseOrder);
			setResul(purchaseOrder.getResul());
			setResult1(purchaseOrder.getResult());
			flag = "1";
			if (result1.size() > 0) {
				for (int i = 0; i < result1.size(); i++) {
					result1.get(i).setCrossTotal(
							GenerateEmployee.numberFormat
									.format(new BigDecimal(result1.get(i)
											.getCrossTotal())));
				}
			}
			return "";
		} catch (DemoException e) {
			flag = "none";
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
			logger.debug(e.getMessage());
			return "";
		}

	}

	public String salesOrdercancelForm1() {
		logger.info("[salesOrdercancelForm1()]------------------- inside salesOrdercancelForm1 method() ---------------");
		try {
			validate = "";
			flag = "none";
			result = null;
			setOrderNumber(orderNumber);
			logger.debug("num---" + getOrderNumber());
			if (purchaseOrder.getResul1().size() == 0) {
				throw new DemoException("wrong.....enter the sales orde number");
			}
			return "success";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			logger.debug(e.getMessage());
			return "failure";
		}
	}

	public String salesdeliveryMB1() {
		logger.info("[salesdeliveryMB1()]------------------- inside salesdeliveryMB1 method() ---------------");
		try {
			if (purchaseOrder.getResul1().size() == 0) {
				throw new DemoException("wrong.....enter the sales orde number");
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesdelivery(purchaseOrder);
			return "success1";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "failure1";
		}

		finally {
			resul1 = null;
			purchaseOrder.setResul(null);

		}
	}

	/** remainder for sales delivery **/
	public String remSalesDeliver() {
		logger.info("[remSalesDeliver()]------------------- inside remSalesDeliver method() ---------------");
		try {
			flag = "none";
			validate = "";
			crosstotal1 = 0;

			logger.debug("customer name:::" + getCustomername());
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
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.remSalesDeliver(purchaseOrder);
			setResul(purchaseOrder.getResul());
			setResult1(purchaseOrder.getResult());
			flag = "1";
			return "";
		} catch (DemoException e) {
			flag = "none";
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			logger.error("Inside Exception", e);
			return "";
		}

	}

	/** remainder for sales delivery **/
	public String remDelivery() {
		logger.info("[remSalesDeliver()]------------------- inside remSalesDeliver method() ---------------");
		try {
			logger.debug("inside mb -->> deliver");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setOrderNumber(orderNumber);
			logger.debug("num --" + getOrderNumber());
			controller.remSalesDeliver(purchaseOrder);

		} catch (Exception E) {
			logger.error("Inside Exception", E);
		} finally {

		}
		return "";
	}

	/** remainder for sales delivery **/
	public String remDelivery1() {
		logger.info("[remDelivery1()]------------------- inside remDelivery1 method() ---------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setOrderNumber(orderNumber);
			controller.remSalesDelivery(purchaseOrder);

		} catch (Exception E) {
			logger.error("Inside Exception", E);
		} finally {

		}
		return "delivered";
	}

	// prema begin 29/04/2016 dialog box creation for sales delivery
	public void salesdelivery() {
		logger.info("[salesdelivery()]------------------- inside salesdelivery method() ---------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("salesdelivery",
				options, null);
		saleDirect();
	}

	// prema end 29/0/2016
	public void salesdeliveryclose() {
		logger.info("[salesdeliveryclose()]------------------- inside salesdeliveryclose method() ---------------");
		RequestContext.getCurrentInstance().closeDialog("salesdelivery");
	}

}
