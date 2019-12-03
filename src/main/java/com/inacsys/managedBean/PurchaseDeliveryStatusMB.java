package com.inacsys.managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0016;
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

@ManagedBean(name = "purchaseDeliveryStatusMB")
public class PurchaseDeliveryStatusMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger
			.getLogger(PurchaseDeliveryStatusMB.class);
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
	public ArrayList<I0016> purchaselist1;
	public String flag = "none";
	Date deliveredDate;
	String delayreason;
	String s;
	public String sale;
	public String validate;
	ArrayList<String> order = new ArrayList<String>();
	List<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
	public String status;
	public List<PurchaseOrder> result5 = new ArrayList<PurchaseOrder>();
	public String vendorTelephoneNumber;

	public String getVendorTelephoneNumber() {
		return vendorTelephoneNumber;
	}

	public void setVendorTelephoneNumber(String vendorTelephoneNumber) {
		this.vendorTelephoneNumber = vendorTelephoneNumber;
	}

	public List<PurchaseOrder> getResult5() {
		return result5;
	}

	public void setResult5(List<PurchaseOrder> result5) {
		this.result5 = result5;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<PurchaseOrder> getResult4() {
		return result4;
	}

	public void setResult4(List<PurchaseOrder> result4) {
		this.result4 = result4;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public ArrayList<String> getOrder() {
		return order;
	}

	public void setOrder(ArrayList<String> order) {
		this.order = order;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public ArrayList<I0016> getPurchaselist1() {
		return purchaselist1;
	}

	public void setPurchaselist1(ArrayList<I0016> purchaselist1) {
		this.purchaselist1 = purchaselist1;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public Date getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public String getDelayreason() {
		return delayreason;
	}

	public void setDelayreason(String delayreason) {
		this.delayreason = delayreason;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	DemoController controller = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();

	public String redirect() {
		setS(null);
		setValidate(null);

		setFlag("none");
		logger.info("-----------inside delivery redirect------------------");
		/*
		 * return "redirect3";
		 */
		return "";
	}

	public String purchaseDeliverydrop() {
		logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseDeliverydrop mb-------------$$$$$$$$$$$$$$-----------");
		try {
			// validate=null;
			purchaselist1 = null;
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist1 = controller.purchaseDeliveryStatus2(purchaselist1,
					purchaseOrder);
			logger.debug("valu::::::" + purchaselist1);
			int i = 0;
			setOrder(purchaseOrder.getOrder());
			logger.info("--------------$$$$$$$$$$$$$$--------Outside purchaseDeliverydrop mb-------------$$$$$$$$$$$$$$-----------");
			return "";
		} catch (DemoException ie) {

			setValidate("");
			logger.error(ie.getMessage());
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		} finally {

		}
	}

	public String purchaseDeliveryStatus() {
		logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseDeliveryStatus mb-------------$$$$$$$$$$$$$$-----------");
		try {
			flag = "none";
			validate = "";
			logger.debug("------------->" + s);
			if (s.equalsIgnoreCase("")) {
				logger.debug(">> inside exception");
				throw new DemoException("*Please Select the Order Number");
			}
			logger.debug("order number------------>" + s);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist = controller.purchaseDeliveryStatus(s, purchaselist,
					purchaseOrder);
			if (purchaselist == null) {
				flag = "none";
				logger.info("-----------inside if----------------");
				throw new DemoException(
						"its null pointer exception because of null purchaselist:::::");
			}
			logger.debug("value------------->" + purchaselist);
			int i = 0;
			setResult4(purchaseOrder.getResult4());
			flag = "1";
			/*
			 * logger.debug("outside ------------->"+purchaselist.get(i).getI0031
			 * ().getI0001().getProductName());
			 */
			logger.info("--------------$$$$$$$$$$$$$$------------Outside purchaseDeliveryStatus mb-------------$$$$$$$$$$$$$$-----------");
			return "";
		} catch (DemoException ie) {
			flag = "none";
			setValidate(ie.getMessage());
			logger.debug(ie.getMessage());
			logger.error(ie.getMessage());
			return "";
		} catch (Exception e) {
			flag = "none";
			logger.error("Inside Exception", e);
			return "";
		} finally {

		}
	}

	public String deliveryStatusconform() {
		logger.debug("in~~~MB~~~");
		logger.info("--------------$$$$$$$$$$$$$$------------inside deliveryStatusconform mb-------------$$$$$$$$$$$$$$-----------");
		try {
			int i = 0;
			logger.debug("inisde delivery conform-------------->" + sale);
			if (purchaselist.get(i).getStatus3().equals("delivered")) {
				throw new DemoException("this purchase order is already added");
			}
			if (purchaselist.size() > 0) {
				for (int j = 0; j < purchaselist.size(); j++) {
					logger.debug("--in====");
					// purchaselist.get(j).getI0031().getI0001().setAutualPrice(GenerateEmployee.numberFormat.format(new
					// BigDecimal(purchaselist.get(j).getI0031().getI0001().getAutualPrice())));
				}
			}
			purchaseOrder.setOrderNumber(sale);
			logger.debug("==sale~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + sale);
			purchaselist = controller.purchaseOrdercancel(sale, purchaselist,
					purchaseOrder);
			logger.debug("=~~~~~~~Size~~~~~~~~~~~~~~~~~~>>>>>>>>>>>>>>"
					+ purchaselist.size());
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
			totalPrice = GenerateEmployee.numberFormat.format(new BigDecimal(
					purchaselist.get(i).getI0015().getTotalPrice()));
			logger.info("--------------$$$$$$$$$$$$$$------------Outside deliveryStatusconform mb-------------$$$$$$$$$$$$$$-----------");
			return "success1";
		} catch (Exception e) {
			return "failure1";
		}
	}

	public String deliveryStatus() {
		logger.info("--------------$$$$$$$$$$$$$$------------inside deliveryStatus mb-------------$$$$$$$$$$$$$$-----------");
		try {
			int i = 0;
			logger.debug("sales number------------>" + sale);
			PurchaseDeliveryStatusMB purchaseDeliveryStatusMB = new PurchaseDeliveryStatusMB();
			purchaseDeliveryStatusMB.setOrderNumber(sale);
			purchaseOrder.setOrderNumber(sale);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.deliveryStatus(purchaseOrder);
			sale = null;
			logger.info("--------------$$$$$$$$$$$$$$------------Outside deliveryStatus mb-------------$$$$$$$$$$$$$$-----------");
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "failure2";
		} finally {

		}
	}

	/** remainder for purchase delivery **/

	public String remPurchaseDeliver() {
		logger.debug("--------------$$$$$$$$$$$$$$------------inside purchaseDeliveryStatus mb-------------$$$$$$$$$$$$$$-----------");
		try {
			flag = "none";
			validate = null;
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext

			(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			PurchaseOrder purchaseOrder = new PurchaseOrder();
			purchaseOrder.setOrderDate(orderDate);
			purchaseOrder.setFirmName(firmName);
			purchaseOrder.setVendorTelephoneNumber(vendorTelephoneNumber);
			logger.debug("--- " + vendorTelephoneNumber);
			purchaseOrder.setTotalPrice("" + totalPrice);
			purchaseOrder.setOrderNumber(orderNumber);
			purchaseOrder.setStatus(status);
			controller.remPurchaseDeliver(purchaselist, purchaseOrder);
			logger.debug("mb list ::" + purchaseOrder.getResult5().size());
			if (purchaseOrder.result5.size() > 0) {
				logger.debug("mb list " + purchaseOrder.getResult5().size());
				setResult4(purchaseOrder.getResult5());

			}

		}

		catch (Exception ie) {
			ie.getMessage();
		} finally {

		}
		return "rempurchasedelivery";
	}

	/** remainder for purchase delivery **/
	public String remPurchaseDelivery() {
		try {
			int i = 0;
			logger.debug("---mb ");
			PurchaseOrder purchaseOrder = new PurchaseOrder();
			purchaselist = controller.purchaseOrdercancel(sale, purchaselist,
					purchaseOrder);
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
			return "rempurchasedeliv";
		} catch (Exception e) {
			return "failure1";
		}
	}

	// prema begin 29/04/2016 dialog box creation for purchase delivery
	public void purchasedelivery() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog(
				"purhcaseDeliveryStatus", options, null);
		redirect();
	}

	// prema end 29/0/2016
	public void purchasedeliveryclose() {
		RequestContext.getCurrentInstance().closeDialog(
				"purhcaseDeliveryStatus");
	}
}
