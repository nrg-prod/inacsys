package com.inacsys.managedBean;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0018;
import com.inacsys.shared.I0019;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "salesOrdercancelFormMB")
public class SalesOrdercancelFormMB {
	private static Logger logger = Logger
			.getLogger(SalesOrdercancelFormMB.class);
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
	List<I0019> resul;
	public String validate;
	public String flag = "none";

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

	public String s = "The cross Total Of this sales Order Is";

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

	DemoController controller = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();

	public String direct6() {
		logger.info("[direct6()]------------------- inside direct6 method() ---------------");
		setSalesIdReference(null);
		setValidate("");
		setFlag("none");
		return "direct6";
	}

	public String salesOrdercancelForm() {
		logger.info("[salesOrdercancelForm()]------------------- inside salesOrdercancelForm method() ---------------");
		try {
			flag = "none";
			validate = null;
			crosstotal1 = "0";
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
				throw new DemoException("*Enter the Sales orde Number");
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrdercancelFormsub(purchaseOrder);
			setResul(purchaseOrder.getResul());
			if (resul.size() == 0) {
				throw new DemoException(
						"*This order number didnot purchase any Product");
			}
			setCustomername(purchaseOrder.getResul().get(0).getI0018()
					.getProductName());
			flag = "1";
			setCrosstotal1(purchaseOrder.getResul().get(0).getI0021()
					.getCrossTotal());
			return "";
		} catch (DemoException e) {
			logger.error("inside exception ",e);
			flag = "none";
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "";
		}

	}

	public String salesOrdercancelForm1() {
		logger.info("[salesOrdercancelForm1()]------------------- inside salesOrdercancelForm1 method() ---------------");
		try {
			flag = "none";
			validate = null;
			if (purchaseOrder.getResul().size() == 0) {
				throw new DemoException("wrong.....enter the sales orde number");
			}
			return "success";
		} catch (Exception e) {
			logger.error("inside exception ",e);
			logger.debug(e.getMessage());
			return "failure";
		}
	}

	public String salesOrdercancelForm2() {
		logger.info("[salesOrdercancelForm2()]------------------- inside salesOrdercancelForm2 method() ---------------");
		try {
			flag = "none";
			if (purchaseOrder.getResul().size() == 0) {
				throw new DemoException("wrong.....enter the sales orde number");
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrdercancelForm2(purchaseOrder);
			return "success1";
		} catch (DemoException e) {
			logger.error("inside exception ",e);
			logger.debug(e.getMessage());
			return "failure1";
		}

		finally {
			resul = null;
			purchaseOrder.setResul(null);
		}
	}

}
