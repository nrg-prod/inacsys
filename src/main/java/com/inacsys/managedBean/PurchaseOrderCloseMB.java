package com.inacsys.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.inacsys.shared.I0015;
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

@ManagedBean(name = "purchaseOrderCloseMB")
public class PurchaseOrderCloseMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PurchaseOrderCloseMB.class);
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
	public ArrayList<I0015> purchaselist1;
	public ArrayList<I0015> purchaselist2 = new ArrayList<I0015>();
	Date s;
	Date s1;
	public String flag = "none";
	public String flag1 = "none";
	public String phoneNumber;
	public String totalprice;
	public String status;
	ArrayList<PurchaseOrderCloseMB> purchaseOrderCloseMBs = null;

	public ArrayList<I0015> getPurchaselist1() {
		return purchaselist1;
	}

	public void setPurchaselist1(ArrayList<I0015> purchaselist1) {
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

	public Date getS1() {
		return s1;
	}

	public void setS1(Date s1) {
		this.s1 = s1;
	}

	public Date getS() {
		return s;
	}

	public void setS(Date s) {
		this.s = s;
	}

	public String Validate;

	public String getValidate() {
		return Validate;
	}

	public void setValidate(String validate) {
		Validate = validate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public ArrayList<PurchaseOrderCloseMB> getPurchaseOrderCloseMBs() {
		return purchaseOrderCloseMBs;
	}

	public void setPurchaseOrderCloseMBs(
			ArrayList<PurchaseOrderCloseMB> purchaseOrderCloseMBs) {
		this.purchaseOrderCloseMBs = purchaseOrderCloseMBs;
	}

	DemoController controller = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();

	/*
	 * public String purchaseOrderClose() { logger.info(
	 * "--------------$$$$$$$$$$$$$$------------inside purchaseOrderClose mb-------------$$$$$$$$$$$$$$-----------"
	 * ); try { flag="none"; logger.debug("order number----------->"+s);
	 * if(s==null) { logger.debug("-----------inside if---------------"); throw
	 * new InventoryException("Please Enter the From Date "); } if(s1==null) {
	 * logger.debug("-----------inside if---------------"); throw new
	 * InventoryException("Please Enter the To Date "); } ApplicationContext ctx
	 * =
	 * FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance
	 * ()); controller = (InventoryController) ctx.getBean("controller");
	 * purchaselist1=controller.purchaseOrderClose(s,s1,
	 * purchaselist1,purchaseOrder);
	 * //logger.debug("size of list"+purchaselist1.size());
	 * if(purchaselist1.size()<=0) { logger.debug("inside null purchase");
	 * flag1="1";flag="none";
	 * logger.info("-------------inside if-----------------");
	 * 
	 * } else { int count=0; purchaseOrderCloseMBs=new
	 * ArrayList<PurchaseOrderCloseMB>(); for (I0015 i0015 : purchaselist1) {
	 * PurchaseOrderCloseMB purchaseOrderCloseMB=new PurchaseOrderCloseMB();
	 * purchaseOrderCloseMB
	 * .setFirmName(purchaseOrder.getPhoneNumber().get(count));
	 * purchaseOrderCloseMB
	 * .setPhoneNumber(purchaseOrder.getFirmname().get(count));
	 * purchaseOrderCloseMB.setOrderNumber(i0015.getTemOrderNumber());
	 * purchaseOrderCloseMB.setOrderDate(i0015.getOrderDate());
	 * purchaseOrderCloseMB.setStatus("Closed");
	 * purchaseOrderCloseMB.setTotalprice(i0015.getTotalPrice());
	 * purchaseOrderCloseMBs.add(purchaseOrderCloseMB); count++; } flag="1"; }
	 * //
	 * logger.debug("purchase order close------->"+purchaseOrderCloseMBs.size()
	 * ); //logger.debug("value------------->"+purchaselist1); int i=0;
	 * 
	 * logger.info(
	 * "--------------$$$$$$$$$$$$$$------------Outside purchaseOrderClose mb-------------$$$$$$$$$$$$$$-----------"
	 * ); return ""; } catch(InventoryException ie) { flag="none";
	 * setValidate(ie.getMessage()); logger.error(ie.getMessage()); return ""; }
	 * catch(Exception e) { flag="none"; logger.error("Inside Exception",e);
	 * return ""; } finally {
	 * 
	 * } }
	 */
	public String purchaseOrderClose() {
		logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseOrderClose mb-------------$$$$$$$$$$$$$$-----------");
		try {
			purchaselist1 = new ArrayList<I0015>();
			/* flag="none"; */
			logger.debug("order number----------->" + s);
			/*
			 * if(s==null) {
			 * logger.debug("-----------inside if---------------"); throw new
			 * DemoException("Please Enter the From Date "); } if(s1==null) {
			 * logger.debug("-----------inside if---------------"); throw new
			 * DemoException("Please Enter the To Date "); }
			 */
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist1 = controller.purchaseOrderClose(purchaselist1,
					purchaseOrder);
			// logger.debug("size of list"+purchaselist1.size());
			if (purchaselist1.size() <= 0) {
				logger.debug("inside null purchase");
				/* flag1="1";flag="none"; */
				logger.info("-------------inside if-----------------");

			} else {
				int count = 0;
				purchaseOrderCloseMBs = new ArrayList<PurchaseOrderCloseMB>();
				for (I0015 i0015 : purchaselist1) {
					PurchaseOrderCloseMB purchaseOrderCloseMB = new PurchaseOrderCloseMB();
					purchaseOrderCloseMB.setFirmName(purchaseOrder
							.getPhoneNumber().get(count));
					purchaseOrderCloseMB.setPhoneNumber(purchaseOrder
							.getFirmname().get(count));
					purchaseOrderCloseMB.setOrderNumber(i0015
							.getTemOrderNumber());
					purchaseOrderCloseMB.setOrderDate(i0015.getOrderDate());
					purchaseOrderCloseMB.setStatus("Closed");
					purchaseOrderCloseMB.setTotalprice(i0015.getTotalPrice());
					purchaseOrderCloseMBs.add(purchaseOrderCloseMB);
					count++;
				}
				/* flag="1"; */
			}
			// logger.debug("purchase order close------->"+purchaseOrderCloseMBs.size());
			// logger.debug("value------------->"+purchaselist1);
			int i = 0;

			logger.info("--------------$$$$$$$$$$$$$$------------Outside purchaseOrderClose mb-------------$$$$$$$$$$$$$$-----------");
			return "";
		} catch (DemoException ie) {
			flag = "none";
			setValidate(ie.getMessage());
			logger.error(ie.getMessage());
			return "";
		} catch (Exception e) {
			flag = "none";
			logger.error("Inside Exception", e);
			return "";
		} finally {

		}
	}

	public String conform() {
		logger.info("--------------$$$$$$$$$$$$$$------------inside conform mb-------------$$$$$$$$$$$$$$-----------");
		try {
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
			logger.info("--------------$$$$$$$$$$$$$$------------Outside conform mb-------------$$$$$$$$$$$$$$-----------");
			return "success1";
		} catch (Exception e) {
			return "failure1";
		}
	}

	public String cancel() {

		flag = "none";
		flag1 = "none";

		return "purchaseOrder";

	}

	public void purchaseclose() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("purhcaseOrderClose",
				options, null);
	}
}
