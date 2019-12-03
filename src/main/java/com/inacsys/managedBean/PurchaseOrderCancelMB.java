package com.inacsys.managedBean;

import java.io.Serializable;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0016;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "purchaseOrderCancelMB")
public class PurchaseOrderCancelMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String vendor_phone_number;
	public String firmName;
	public String productName1;
	public String frim_ID;
	public String sellingPrice;
	public Date orderDate;
	public Date targentDate;
	public String quantity;
	public int product_ID;
	public String product_name;
	public String totalPrice;
	public String orderNumber;
	public ArrayList<I0016> purchaselist;
	public String Validate;
	public String flag = "none";

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getValidate() {
		return Validate;
	}

	public void setValidate(String validate) {
		Validate = validate;
	}

	public String getFrim_ID() {
		return frim_ID;
	}

	public void setFrim_ID(String frim_ID) {
		this.frim_ID = frim_ID;
	}

	public ArrayList<I0016> getPurchaselist() {
		return purchaselist;
	}

	public void setPurchaselist(ArrayList<I0016> purchaselist) {
		this.purchaselist = purchaselist;
	}

	public String getVendor_phone_number() {
		return vendor_phone_number;
	}

	public void setVendor_phone_number(String vendor_phone_number) {
		this.vendor_phone_number = vendor_phone_number;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getProductName1() {
		return productName1;
	}

	public void setProductName1(String productName1) {
		this.productName1 = productName1;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getTargentDate() {
		return targentDate;
	}

	public void setTargentDate(Date targentDate) {
		this.targentDate = targentDate;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public int getProduct_ID() {
		return product_ID;
	}

	public void setProduct_ID(int product_ID) {
		this.product_ID = product_ID;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	String s;

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	DemoController controller = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();
	private static Logger logger = Logger
			.getLogger(PurchaseOrderCancelMB.class);

	public String purchaseOrdercancel() {
		try {
			logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseOrdercancel mb-------------$$$$$$$$$$$$$$-----------");
			Validate = null;
			logger.debug("order number------------>" + s);
			flag = "none";
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist = controller.purchaseOrdercancel(s, purchaselist,
					purchaseOrder);
			if (purchaselist == null) {
				flag = "none";
				logger.info("---------------inside if--------------");
				throw new DemoException(
						"* its null pointer exception because of null purchaselist");
			}
			logger.debug("value------------->" + purchaselist);
			int i = 0;
			flag = "1";
			logger.debug("outside------------->"
					+ purchaselist.get(i).getI0031().getI0001()
							.getProductName());
			logger.info("--------------$$$$$$$$$$$$$$------------Outside purchaseOrdercancel mb-------------$$$$$$$$$$$$$$-----------");
			return "success";
		} catch (DemoException ie) {
			setValidate(ie.getMessage());
			logger.error(ie.getMessage());
			return "failure";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		} finally {

		}

	}

	public String conform() {
		try {
			logger.info("--------------$$$$$$$$$$$$$$------------inside conform mb-------------$$$$$$$$$$$$$$-----------");
			int i = 0;
			firmName = purchaselist.get(i).getI0031().getI0025().getFirmName();
			vendor_phone_number = purchaselist.get(i).getI0031().getI0025()
					.getVendorPhoneNumber();
			product_name = purchaselist.get(i).getI0031().getI0001()
					.getProductName();
			product_ID = purchaselist.get(i).getI0031().getI0001()
					.getProduct_ID();
			orderDate = purchaselist.get(i).getI0015().getOrderDate();
			targentDate = purchaselist.get(i).getI0015().getTargentDate();
			sellingPrice = purchaselist.get(i).getI0031().getI0001()
					.getSellingPrice();
			quantity = purchaselist.get(i).getI0015().getQuantity();
			totalPrice = purchaselist.get(i).getI0015().getTotalPrice();
			flag = "none";
			logger.info("--------------$$$$$$$$$$$$$$-----------outside conform mb-------------$$$$$$$$$$$$$$-----------");
			return "success1";
		} catch (Exception e) {
			return "failure1";
		}
	}

	public String cancelConform1() {
		try {
			logger.info("--------------$$$$$$$$$$$$$$------------inside cancelConform1 mb-------------$$$$$$$$$$$$$$-----------");
			int i = 0;
			logger.debug("value:::::::::" + orderNumber);
			purchaseOrder.setOrderNumber(purchaselist.get(i).getOrdernumber());
			logger.debug("value------------->" + purchaseOrder.getOrderNumber());
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.cancelConform1(purchaseOrder);
			orderNumber = purchaseOrder.getOrderNumber();
			logger.info("--------------$$$$$$$$$$$$$$-----------outside cancelConform1 mb-------------$$$$$$$$$$$$$$-----------");
			return "success2";
		} catch (Exception e) {
			return "failure2";
		} finally {
			firmName = null;
			product_ID = 0;
			product_name = null;
			orderDate = null;
			targentDate = null;
			quantity = null;
			vendor_phone_number = null;
			purchaselist = null;
		}
	}

}
