package com.inacsys.managedBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.SortOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.LoginAccess;
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

@ManagedBean(name = "purchaseOrderPaymentMB")
public class PurchaseOrderPaymentMB {
	private static Logger logger = Logger
			.getLogger(PurchaseOrderPaymentMB.class);
	public String vendorTelephoneNumber;
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
	public String flag1 = "none";

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	Date s;
	Date s1;
	public List<PurchaseOrder> result5 = new ArrayList<PurchaseOrder>();
	String payableAmount;

	public String getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(String payableAmount) {
		this.payableAmount = payableAmount;
	}

	public List<PurchaseOrder> getResult5() {
		return result5;
	}

	public void setResult5(List<PurchaseOrder> result5) {
		this.result5 = result5;
	}

	public String getVendorTelephoneNumber() {
		return vendorTelephoneNumber;
	}

	public void setVendorTelephoneNumber(String vendorTelephoneNumber) {
		this.vendorTelephoneNumber = vendorTelephoneNumber;
	}

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

	public Date getS() {
		return s;
	}

	public void setS(Date s) {
		this.s = s;
	}

	public Date getS1() {
		return s1;
	}

	public void setS1(Date s1) {
		this.s1 = s1;
	}

	ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();

	public List<PurchaseOrder> result6 = new ArrayList<PurchaseOrder>();

	public List<PurchaseOrder> getResult6() {
		return result6;
	}

	public void setResult6(List<PurchaseOrder> result6) {
		this.result6 = result6;
	}

	public ArrayList<PurchaseOrder> getResult4() {
		return result4;
	}

	public void setResult4(ArrayList<PurchaseOrder> result4) {
		this.result4 = result4;
	}

	DemoController controller = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();

	public String purchaseOrderPayment() {
		logger.debug("--------------$$$$$$$$$$$$$$------------inside purchaseOrderPayment mb-------------$$$$$$$$$$$$$$-----------");
		try

		{
			Validate = null;
			purchaselist = null;
			flag = "none";
			flag1 = "none";
			logger.debug("inside purchaseOrdercancel::::::::::::");
			logger.debug("order number::::" + s);
			logger.debug("middle purchaseOrdercancel::::::::::::");
			purchaseOrder.setOrderDate(s);
			purchaseOrder.setToDate(s1);
			if (s == null) {
				throw new DemoException("Please Choose the From Date");
			}
			if (s1 == null) {
				throw new DemoException("Please Choose the To Date");
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist = controller.purchaseOrderPayment(s, s1, purchaselist,
					purchaseOrder);
			if (purchaselist == null) {
				flag = "none";
				flag1 = "1";
				logger.debug("inside if::::::::::::");

			}

			setResult4(purchaseOrder.getResult4());
			if (result4.size() > 0) {
				for (int i = 0; i < result4.size(); i++) {
					result4.get(i).setPayableAmount(
							GenerateEmployee.numberFormat
									.format(new BigDecimal(result4.get(i)
											.getPayableAmount())));
					/*
					 * result4.get(i).setTotalPrice(GenerateEmployee.numberFormat
					 * .format(new BigDecimal(result4.get(i).getTotalPrice())));
					 */}
			}
			logger.debug("valu::::::" + purchaselist);
			int i = 0;
			flag = "1";
			logger.debug("outside ::::::::::::::"
					+ purchaselist.get(i).getI0031().getI0001()
							.getProductName());
			logger.debug("--------------$$$$$$$$$$$$$$------------outside purchaseOrderPayment mb-------------$$$$$$$$$$$$$$-----------");
			return "";
		} catch (DemoException ie) {
			flag = "none";
			setValidate(ie.getMessage());
			logger.debug(ie.getMessage());
			return "";
		} catch (Exception e) {
			flag = "none";
			logger.error("Inside Exception", e);
			return "";

		} finally {

		}
	}

	public String conform() {
		logger.debug("--------------$$$$$$$$$$$$$$------------inside conform mb-------------$$$$$$$$$$$$$$-----------");
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
			flag = "none";
			logger.debug("--------------$$$$$$$$$$$$$$------------Outside conform mb-------------$$$$$$$$$$$$$$-----------");
			return "success1";
		} catch (Exception e) {
			return "failure1";
		}
	}

	/** remainder method 1 **/
	public String purchaseOrderPayment1() {
		logger.debug("--------------!!!!!!!! mb called-----------");
		try

		{
			purchaselist = null;
			flag = "none";
			logger.debug("inside purchaseOrdercancel::::::::::::");
			logger.debug("order number::::" + s);
			logger.debug("middle purchaseOrdercancel::::::::::::");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist = controller.purchaseOrderPayment1(purchaselist,
					purchaseOrder);
			if (purchaselist == null) {
				flag = "none";
				logger.debug("inside if::::::::::::");
				throw new DemoException("* no data found");
			}
			setResult4(purchaseOrder.getResult4());
			logger.debug("valu::::::" + purchaselist);
			int i = 0;
			flag = "1";
			logger.debug("outside ::::::::::::::"
					+ purchaselist.get(i).getI0031().getI0001()
							.getProductName());
			logger.debug("--------------$$$$$$$$$$$$$$------------outside purchaseOrderPayment mb-------------$$$$$$$$$$$$$$-----------");
			return "";
		} catch (DemoException ie) {
			flag = "none";
			setValidate(ie.getMessage());
			logger.debug(ie.getMessage());
			return "";
		} catch (Exception e) {
			flag = "none";
			logger.error("Inside Exception", e);
			return "";

		} finally {

		}
	}

	BigDecimal q = BigDecimal.valueOf(0);

	public BigDecimal getQ() {
		return q;
	}

	public void setQ(BigDecimal q) {
		this.q = q;
	}

	/** remainder purchase payment **/
	public String remPurchasePaymentStatus() {
		logger.debug("----------------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			logger.debug("in mb");
			purchaseOrder.setOrderDate(orderDate);
			purchaseOrder.setOrderNumber(orderNumber);
			purchaseOrder.setPayableAmount("" + payableAmount);
			purchaseOrder.setFirmName(firmName);
			purchaseOrder.setVendorTelephoneNumber(vendorTelephoneNumber);
			purchaseOrder.setTotalPrice("" + totalPrice);
			/*
			 * logger.debug("-----------"+(totalPrice)); purchaseOrder.setQ(new
			 * BigDecimal(totalPrice));
			 */
			controller.remPurchasePaymentStatus(purchaseOrder);
			if (purchaseOrder.result6.size() > 0) {
				setResult5(purchaseOrder.result6);
			} else {
				setValidate("No data found");
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "failure1";
		} finally {

		}
		return "";
	}

	public String cancel() {

		flag = "none";
		flag1 = "none";
		s = null;
		s1 = null;

		return "purchaseOrderPayment";

	}

	/* siva 18_2_15 */
	public String purchasePaymentMenuLoad() {
		try {

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			setValidate("");
			s = null;
			s1 = null;
			result4 = null;
			flag = "none";
		}
		/*
		 * return "purhcaseOrderPaymentsStatus";
		 */
		return "";
	}

	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public PurchaseOrderPaymentMB() {
		sortsOrders = new HashMap<String, SortOrder>();
		sortPriorities = new ArrayList<String>();
	}

	public void sort() {
		String property = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get(SORT_PROPERTY_PARAMETER);
		logger.debug("-------------sss-----------" + property);
		if (property != null) {
			SortOrder currentPropertySortOrder = sortsOrders.get(property);
			logger.debug("-----------pp--------" + currentPropertySortOrder);
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

}
