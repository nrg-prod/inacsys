package com.inacsys.managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SortOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0015;
import com.inacsys.shared.I0016;
import com.inacsys.shared.I0025;
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

@ManagedBean(name = "purchaseViewMB")
@ViewScoped
public class PurchaseViewMB implements Serializable {
	private static Logger logger = Logger.getLogger(PurchaseViewMB.class);
	private static final long serialVersionUID = 1L;
	public String validate1;
	public Date orderDate;
	public String vendor_phone_number;
	public String firmName;
	public String productName1;
	public String frim_ID;
	public String sellingPrice;
	private BigDecimal netAmount = new BigDecimal("0");
	private String netAmount1;
	private String userType;
	private String approveButtonFlag="none";
	public String approveStatus;
	public String baseCurrency;
	public String currency;
	public int purchaseid;
	public String sourceCurrency;

	
	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getPurchaseid() {
		return purchaseid;
	}

	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getApproveButtonFlag() {
		return approveButtonFlag;
	}

	public void setApproveButtonFlag(String approveButtonFlag) {
		this.approveButtonFlag = approveButtonFlag;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getNetAmount1() {
		return netAmount1;
	}

	public void setNetAmount1(String netAmount1) {
		this.netAmount1 = netAmount1;
	}

	public Date targentDate;
	public String quantity;
	public String quantity1;
	public int product_ID;
	public String product_name;
	public String totalPrice;
	public String orderNumber;
	public List<I0025> result = null;
	private List<PurchaseOrder> resultview = null;
	private List<PurchaseOrder> filteredList = null;

	public String getQuantity1() {
		return quantity1;
	}

	public void setQuantity1(String quantity1) {
		this.quantity1 = quantity1;
	}

	public List<PurchaseOrder> getFilteredList() {
		return filteredList;
	}

	public void setFilteredList(List<PurchaseOrder> filteredList) {
		this.filteredList = filteredList;
	}

	public List<I0025> getResult() {
		return result;
	}

	public void setResult(List<I0025> result) {
		this.result = result;
	}

	public String Validate;
	public String flag = "none";
	public ArrayList<I0016> purchaselist = null;;
	public String flaga = "none";
	public String flag2 = "none";
	public String purchaseQuantity;

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	public String getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(String purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public String getFlaga() {
		return flaga;
	}

	public void setFlaga(String flaga) {
		this.flaga = flaga;
	}

	public Date toDate;

	public String getValidate1() {
		return validate1;
	}

	public void setValidate1(String validate1) {
		this.validate1 = validate1;
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

	public String getFrim_ID() {
		return frim_ID;
	}

	public void setFrim_ID(String frim_ID) {
		this.frim_ID = frim_ID;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
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

	public String getValidate() {
		return Validate;
	}

	public void setValidate(String validate) {
		Validate = validate;
	}

	public DemoController getController() {
		return controller;
	}

	public void setController(DemoController controller) {
		this.controller = controller;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public ArrayList<I0016> getPurchaselist() {
		return purchaselist;
	}

	public void setPurchaselist(ArrayList<I0016> purchaselist) {
		this.purchaselist = purchaselist;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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
	ArrayList<PurchaseOrder> filterList;
	public String approval;
	
	public ArrayList<PurchaseOrder> getFilterList() {
		return filterList;
	}

	public void setFilterList(ArrayList<PurchaseOrder> filterList) {
		this.filterList = filterList;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}
	
	
	public String redirect1() {
		flag2 = "none";
		flag = "none";
		setValidate1("");
		setValidate("");
		logger.debug("inside reirect::::::::::::");
		setFirmName(null);
		setProduct_ID(0);
		setProduct_name(null);
		setOrderDate(null);
		setTargentDate(null);
		setQuantity(null);
		setPurchaselist(null);
		flag = "none";
		setToDate(null);
		setOrderDate(null);
		setVendorPhoneNumber("");
		setResult4(null);
		flag1 = "none";
		purchases = null;
		/*
		 * return "redirect1";
		 */return "";
	}

	public String cancel() {
		try {
			/* purchaselist=null; */
			setValidate1("");
			logger.debug("--------------$$$$$$$$$$$$$$------------inside purchaseOrdercancel mb-------------$$$$$$$$$$$$$$-----------");
			Validate = null;
			logger.debug("order number::::" + orderNumber);
			flag = "none";
			logger.debug("middle purchaseOrdercancel::::::::::::");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setPurchaseid(purchaseid);
			purchaselist = controller.purchaseOrdercancel1(orderNumber,
					purchaselist, purchaseOrder);
			logger.debug("1");
			if (purchaselist == null) {
				flag = "none";
				logger.debug("inside if::::::::::::");
				throw new DemoException(
						"* its null pointer exception because of null purchaselist");
			} else {
				logger.debug("valu::::::" + purchaselist);
				int i = 0;
				flag = "1";
				logger.debug("outside ::::::::::::::"
						+ purchaselist.get(i).getI0031().getI0001()
								.getProductName());

				firmName = purchaselist.get(i).getI0031().getI0025()
						.getFirmName();
				vendor_phone_number = purchaselist.get(i).getI0031().getI0025()
						.getVendorPhoneNumber();
				product_name = purchaselist.get(i).getI0031().getI0001()
						.getProductName();
				product_ID = purchaselist.get(i).getI0031().getI0001()
						.getProduct_ID();
				sourceCurrency = purchaselist.get(i).getI0015().getCurrencyType();
				orderDate = purchaselist.get(i).getI0015().getOrderDate();
				targentDate = purchaselist.get(i).getI0015().getTargentDate();
				sellingPrice = purchaselist.get(i).getI0031().getI0001()
						.getSellingPrice();
				quantity = purchaselist.get(i).getI0015().getQuantity();
				totalPrice = purchaselist.get(i).getI0015().getTotalPrice();
				orderNumber = purchaselist.get(0).getOrdernumber();
				flag = "none";
				logger.debug("--------------$$$$$$$$$$$$$$------------Outside purchaseOrdercancel mb-------------$$$$$$$$$$$$$$-----------");
			}

			return "";
		} catch (DemoException ie) {

			setValidate1("* This Order is already Delivered or Cancelled");
			logger.debug(ie.getMessage());
			logger.debug("ss" + purchaseOrder.getPurchaselist().size());
			setPurchaselist(purchaseOrder.getPurchaselist());
			flag = "1";
			logger.debug("inside the exception");

			return "";
		}

		finally {

		}

	}

	public String editpurchase() {
		try {
			flaga = "none";
			/* purchaselist=null; */
			setValidate1("");
			logger.debug("--------------$$$$$$$$$$$$$$------------inside purchaseOrdercancel mb-------------$$$$$$$$$$$$$$-----------");
			Validate = null;
			logger.debug("order number::::" + orderNumber);
			flag = "1";
			logger.debug("middle purchaseOrdercancel::::::::::::");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist = controller.purchaseOrdercancel1(orderNumber,
					purchaselist, purchaseOrder);
			logger.debug("1");
			if (purchaselist == null) {
				flag = "none";
				logger.debug("inside if::::::::::::");
				throw new DemoException(
						"* its null pointer exception because of null purchaselist");
			} else {
				logger.debug("valu::::::" + purchaselist);
				int i = 0;
				flag = "1";
				logger.debug("outside ::::::::::::::"
						+ purchaselist.get(i).getI0031().getI0001()
								.getProductName());

				firmName = purchaselist.get(i).getI0031().getI0025()
						.getFirmName();
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
				logger.debug("margin price------------->"
						+ purchaseOrder.getResult4().get(0).getMarginPrice());
				if (!purchaseOrder.getResult4().get(0).getMarginPrice()
						.equalsIgnoreCase("0")) {
					logger.debug("inside if");
					throw new Exception();
				}
				flag = "1";
				/* setResult4(purchaseOrder.getResult4()); */
				logger.debug("--------------$$$$$$$$$$$$$$------------Outside purchaseOrdercancel mb1-------------$$$$$$$$$$$$$$-----------");
			}
			logger.debug("1");
			return "";
		} catch (DemoException ie) {

			setValidate("* This order is already cancelled or delivered");
			logger.debug(ie.getMessage());
			logger.debug("ss" + purchaseOrder.getPurchaselist().size());
			setPurchaselist(purchaseOrder.getPurchaselist());

			flag = "1";
			logger.debug("inside the exception");

			return "";
		} catch (Exception e) {
			setValidate("*This order already started payment");
			logger.error("Inside Exception", e);
			return "";
		}

		finally {

		}

	}

	public int serialno;

	public int getSerialno() {
		return serialno;
	}

	public void setSerialno(int serialno) {
		this.serialno = serialno;
	}

	public String deliveryStatus() {
		logger.debug("--------------$$$$$$$$$$$$$$------------inside deliveryStatus mb-------------$$$$$$$$$$$$$$-----------");
		try {
			int i = 0;
			logger.debug("po number------------>" + orderNumber);
			purchaseOrder.setOrderNumber(orderNumber);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String status = controller.deliveryStatus(purchaseOrder);
			logger.debug("--------------$$$$$$$$$$$$$$------------Outside deliveryStatus mb-------------$$$$$$$$$$$$$$-----------");
			logger.debug("status" + status);
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "failure2";
		} finally {

		}
	}

	public String view() throws ParseException {
		try {
			setValidate1("");
			logger.debug("--------------$$$$$$$$$$$$$$------------inside purchaseOrdercancel mb-------------$$$$$$$$$$$$$$-----------");
			Validate = null;
			logger.debug("order number::::" + orderNumber);
			purchaseOrder.setSalesIdReference(orderNumber);
			purchaseOrder.setPurchaseid(purchaseid);
			logger.debug("middle purchaseOrdercancel::::::::::::");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.getpurchaseView(purchaseOrder);
			resultview = purchaseOrder.getResulfinal();
			logger.debug("Size....." + resultview.size());
			logger.debug("Prize..." + resultview.get(0).getTotalPrice());
			totalPrice = GenerateEmployee.numberFormat.format(new BigDecimal(
					resultview.get(0).getTotalPrice()));
			setSourceCurrency(resultview.get(0).getDestinationCurrency());
			firmName = resultview.get(0).getFirmName();
			vendor_phone_number = resultview.get(0).getVendorPhoneNumber();
			setOrderDate(resultview.get(0).getOrderDate());
			targentDate = resultview.get(0).getTargentDate();
			purchaseOrder.setStatus3(resultview.get(0).getStatus3());
			purchaseOrder.setStatus2(resultview.get(0).getStatus2());
			logger.debug("status3" + purchaseOrder.getStatus3()
					+ purchaseOrder.getResulfinal().get(0).getStatus3());
			logger.debug("status3" + purchaseOrder.getStatus2()
					+ purchaseOrder.getResulfinal().get(0).getStatus2());
			return "";
		} catch (DemoException ie) {
			setValidate(ie.getMessage());
			setValidate1("* This Order is already Cancelled");
			logger.debug(ie.getMessage());
			logger.debug("ss" + purchaseOrder.getPurchaselist().size());
			setPurchaselist(purchaseOrder.getPurchaselist());
			logger.debug("inside the exception");

			return "";
		}

		finally {

		}

	}

	ArrayList<PurchaseViewMB> viewMBs = new ArrayList<PurchaseViewMB>();

	public ArrayList<PurchaseViewMB> getViewMBs() {
		return viewMBs;
	}

	public void setViewMBs(ArrayList<PurchaseViewMB> viewMBs) {
		this.viewMBs = viewMBs;
	}

	public String serialno1;

	public String getSerialno1() {
		return serialno1;
	}

	public void setSerialno1(String serialno1) {
		this.serialno1 = serialno1;
	}

	public String viewnew() {
		try {
			viewMBs.clear();
			BigDecimal qus = BigDecimal.valueOf(0);
			BigDecimal qustot = BigDecimal.valueOf(0);
			/*
			 * if(flag.equalsIgnoreCase("1")) { flag="1"; } else { flag="none";
			 * } if(flag1.equalsIgnoreCase("1")) { flag1="1"; } else {
			 * flag1="none"; } setValidate1(""); logger.debug(
			 * "--------------$$$$$$$$$$$$$$------------inside purchaseOrdercancel mb-------------$$$$$$$$$$$$$$-----------"
			 * ); Validate=null;
			 */
			logger.debug("order number::::" + orderNumber);
			purchaseOrder.setSalesIdReference(orderNumber);
			logger.debug("middle purchaseOrdercancel::::::::::::");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.getpurchaseView(purchaseOrder);
			resultview = purchaseOrder.getResulfinal();
			BigDecimal prices = BigDecimal.valueOf(0);
			logger.debug("Size....." + resultview.size());
			if (resultview.size() > 0) {
				int count = 0;
				List<String> productname = new ArrayList<String>();
				for (int i = 0; i < resultview.size(); i++) {
					productname.add(resultview.get(i).getProduct_name());
				}
				logger.debug("-----product name size--------------->>"
						+ productname.size());
				HashSet<String> s = new HashSet<String>(productname);
				logger.debug("----s------------------" + s.size());
				for (String e : s) {
					qus = BigDecimal.valueOf(0);
					qustot = BigDecimal.valueOf(0);
					for (int j = 0; j < resultview.size(); j++) {
						if (e.equalsIgnoreCase(resultview.get(j)
								.getProduct_name())) {
							qus = qus.add(new BigDecimal(resultview.get(j)
									.getQuantity()));
							qustot = qustot.add(new BigDecimal(resultview
									.get(j).getTotalPrice()));
							prices = new BigDecimal(resultview.get(j)
									.getQuantity()).multiply(new BigDecimal(
									resultview.get(j).getPrice()));

						} else {
							logger.debug("~~~inside>>>>else");
						}
					}
					PurchaseViewMB saless = new PurchaseViewMB();
					count = count + 1;
					saless.setSerialno1("" + count);
					saless.setProduct_name("" + e);
					saless.setQuantity("" + qus);
					saless.setTotalPrice("" + prices);
					viewMBs.add(saless);

					logger.debug("~~~print~~~~~~" + qus
							+ "~~~~~~~~~~~~~~~~~~~pricetttttot~~~~~~" + qustot);
				}

			}
			logger.debug("Prize..." + resultview.get(0).getTotalPrice());
			totalPrice = GenerateEmployee.numberFormat.format(new BigDecimal(
					resultview.get(0).getTotalPrice()));
			firmName = resultview.get(0).getFirmName();
			vendor_phone_number = resultview.get(0).getVendorPhoneNumber();
			setOrderDate(resultview.get(0).getOrderDate());
			targentDate = resultview.get(0).getTargentDate();

			return "purchasrOrderViewnew";
		} catch (Exception ie) {
			logger.error("Inside Exception", ie);

			return "";
		}

		finally {

		}

	}

	public String view1() {
		try {
			setValidate1("");
			logger.debug("--------------$$$$$$$$$$$$$$------------inside purchaseOrdercancel mb-------------$$$$$$$$$$$$$$-----------");
			Validate = null;
			logger.debug("order number::::" + orderNumber);
			flag = "none";
			logger.debug("middle purchaseOrdercancel::::::::::::");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist = controller.purchaseOrdercancel(orderNumber,
					purchaselist, purchaseOrder);
			if (purchaselist == null) {
				flag = "none";
				logger.debug("inside if::::::::::::");
				throw new DemoException(
						"* its null pointer exception because of null purchaselist");
			}
			logger.debug("valu::::::" + purchaselist);
			if (purchaselist.size() > 0) {
				for (int i = 0; i < purchaselist.size(); i++) {
					purchaselist
							.get(i)
							.getI0031()
							.getI0001()
							.setAutualPrice(
									GenerateEmployee.numberFormat
											.format(new BigDecimal(purchaselist
													.get(i).getI0031()
													.getI0001()
													.getAutualPrice())));
				}
			}
			int i = 0;
			flag = "1";
			logger.debug("outside ::::::::::::::"
					+ purchaselist.get(i).getI0031().getI0001()
							.getProductName());

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
			BigDecimal qutt = new BigDecimal(purchaselist.get(i).getI0015()
					.getQuantity());
			qutt = qutt.setScale(2, RoundingMode.CEILING);
			logger.debug("" + qutt);
			quantity = qutt.toString();
			logger.debug(">>>>>>>>" + quantity);
			totalPrice = GenerateEmployee.numberFormat.format(new BigDecimal(
					purchaselist.get(i).getI0015().getTotalPrice()));
			flag = "none";
			logger.debug("--------------$$$$$$$$$$$$$$------------Outside purchaseOrdercancel mb-------------$$$$$$$$$$$$$$-----------");
			return "purchaseOrderView3";
		} catch (DemoException ie) {
			setValidate(ie.getMessage());
			setValidate1("* This Order is already Cancelled");
			logger.debug(ie.getMessage());
			logger.debug("ss" + purchaseOrder.getPurchaselist().size());
			setPurchaselist(purchaseOrder.getPurchaselist());
			flag = "1";
			logger.debug("inside the exception");

			return "";
		}

		finally {

		}

	}

	public String cancelConform1() {
		try {
			setValidate1("");
			int i = 0;
			logger.debug("value:::::::::" + orderNumber);
			purchaseOrder.setOrderNumber(orderNumber);
			logger.debug("value:::::::::" + purchaseOrder.getOrderNumber());
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setPurchaseid(purchaseid);
			controller.cancelConform1(purchaseOrder);
			orderNumber = purchaseOrder.getOrderNumber();
			return "success2";
		} catch (Exception e) {
			return "";
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

	public String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String purchaseProductName;

	public String getPurchaseProductName() {
		return purchaseProductName;
	}

	public void setPurchaseProductName(String purchaseProductName) {
		this.purchaseProductName = purchaseProductName;
	}

	public String updatePurchase() {
		  try {
		   validate1 = null;
		   purchaseOrder.setOrderNumber(orderNumber);
		   purchaseOrder.setProduct_name(purchaseProductName);
		   purchaseOrder.setPurchaseQuantity(purchaseQuantity);
		   purchaseOrder.setQuantity(quantity1);
		   if (purchaseQuantity.equals("")) {
		    throw new DemoException("*Enter the Quantity");
		   }
		   if (!purchaseQuantity.matches("[-+]?[0-9]*\\.?[0-9]+")) {
		    throw new DemoException("*Quantity should be in numeric");
		   }
		   NumberFormat formatter = NumberFormat.getInstance();
		   ParsePosition pos = new ParsePosition(0);
		   formatter.parse(purchaseQuantity, pos);
		   if (purchaseQuantity.length() != pos.getIndex()) {
		    throw new DemoException("*numbers only");
		   }

		   ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		   controller = (DemoController) ctx.getBean("controller");
		   purchaseOrder.setTotalAmount(totalPrice); //stanley changes
		   controller.purchaseProductEdit(purchaseOrder);
		   RequestContext.getCurrentInstance().execute("PF('puredit').show();");
		   purchaselist = controller.purchaseOrdercancel1(orderNumber,purchaselist, purchaseOrder);
		   if (purchaselist == null) {
		    throw new DemoException("* its null pointer exception because of null purchaselist");
		   }
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
		   return "";
		  } catch (DemoException e) {
		   setValidate1(e.getMessage());
		   logger.debug("message----------------->" + e.getMessage());
		   return "";
		  } catch (Exception e) {
		   return "updatePurchasefailure";
		  }
		 }

	public String test1() {
		try {
			setValidate1("");
			setPurchaseQuantity("");
			int i = 0;
			logger.debug("value:::::::::" + purchaseProductName);

			logger.debug("value:::::::::" + purchaseProductName);

			return "successtest";
		} catch (Exception e) {
			return "failureeditpurchase";
		} finally {

		}
	}

	public void changeproductQuantity(ValueChangeEvent e) {
		flaga = "1";
		logger.debug("VALUE CHANGED111111111111111111111111111111111111"
				+ e.getNewValue());
	}

	/* udhaya 2.1.2015 */
	public ArrayList<I0016> datalist = null;

	public ArrayList<I0016> getDatalist() {
		return datalist;
	}

	public void setDatalist(ArrayList<I0016> datalist) {
		this.datalist = datalist;
	}

	public String approvalstatus() {
		logger.debug("inside mb---------->>");
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			logger.debug("inside try in mb--------->>");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setDatalist(purchaseOrder.getDatalist());
			controller.approvalstatus(purchaseOrder);
			if (purchaseOrder.getDatalist().size() > 0) {
				setDatalist(purchaseOrder.getDatalist());
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	/* udhaya 5.1.2015 */
	public List<PurchaseOrder> purchase = null;

	public List<PurchaseOrder> getPurchase() {
		return purchase;
	}

	public void setPurchase(List<PurchaseOrder> purchase) {
		this.purchase = purchase;
	}

	public String approval() {
		logger.debug("inside mb---------->>");
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			logger.debug("inside try in mb--------->>");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist = controller.approvalStage(orderNumber, purchaselist,
					purchaseOrder);
			firmName = purchaselist.get(0).getI0031().getI0025().getFirmName();
			logger.debug("firm name-------->>" + firmName);
			vendor_phone_number = purchaselist.get(0).getI0031().getI0025()
					.getVendorPhoneNumber();
			product_name = purchaselist.get(0).getI0031().getI0001()
					.getProductName();
			product_ID = purchaselist.get(0).getI0031().getI0001()
					.getProduct_ID();
			orderDate = purchaselist.get(0).getI0015().getOrderDate();
			targentDate = purchaselist.get(0).getI0015().getTargentDate();
			sellingPrice = purchaselist.get(0).getI0031().getI0001()
					.getSellingPrice();
			quantity = purchaselist.get(0).getI0015().getQuantity();
			totalPrice = purchaselist.get(0).getI0015().getTotalPrice();
		} catch (Exception e) {
			setValidate(e.getMessage());
		} finally {

		}
		return "purchaseOrderApprovalForm";
	}

	public String approval1() {
		logger.debug("inside mb---------->>");
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			logger.debug("inside try in mb--------->>");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setOrderNumber(orderNumber);
			logger.debug("order number------->>" + orderNumber);
			controller.approved(purchaseOrder);
		} catch (Exception ie) {
			logger.error("Inside Exception", ie);
		} finally {

		}
		return "purchaseOrderApprovalConform";
	}

	/* jeni */
	public String flag1 = "none";

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public List<I0025> vendorInfo;
	ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
	ArrayList<PurchaseOrder> results = new ArrayList<PurchaseOrder>();

	public ArrayList<PurchaseOrder> getResults() {
		return results;
	}

	public void setResults(ArrayList<PurchaseOrder> results) {
		this.results = results;
	}

	public ArrayList<PurchaseOrder> getResult4() {
		return result4;
	}

	public void setResult4(ArrayList<PurchaseOrder> result4) {
		this.result4 = result4;
	}

	public List<I0025> getVendorInfo() {
		return vendorInfo;
	}

	public void setVendorInfo(List<I0025> vendorInfo) {
		this.vendorInfo = vendorInfo;
	}

	List<PurchaseOrder> result5 = null;
	List<PurchaseOrder> vendorview = new ArrayList<PurchaseOrder>();

	public List<PurchaseOrder> getResult5() {
		return result5;
	}

	public void setResult5(List<PurchaseOrder> result5) {
		this.result5 = result5;
	}

	public List<PurchaseOrder> getVendorview() {
		return vendorview;
	}

	public void setVendorview(List<PurchaseOrder> vendorview) {
		this.vendorview = vendorview;
	}

	public String vendorPhoneNumber;

	public String getVendorPhoneNumber() {
		return vendorPhoneNumber;
	}

	public void setVendorPhoneNumber(String vendorPhoneNumber) {
		this.vendorPhoneNumber = vendorPhoneNumber;
	}

	public String flags = "none";
	public List<PurchaseOrder> purchases = null;

	public List<PurchaseOrder> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseOrder> purchases) {
		this.purchases = purchases;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	/*
	 * public String purchaseView1() {
	 * 
	 * InventoryController controller=null; ApplicationContext ctx=null; try {
	 * 
	 * flag1="none";flag="none";flag2="none"; ctx =
	 * FacesContextUtils.getWebApplicationContext
	 * (FacesContext.getCurrentInstance()); controller = (InventoryController)
	 * ctx.getBean("controller");
	 * logger.debug("inside purchaseOrder Firm Name View::::::::::::");
	 * 
	 * setVendorInfo(controller.getPurchaseVendorView(vendorPhoneNumber));
	 * logger.debug("Back to bean"); PurchaseOrder purchaseVendor=new
	 * PurchaseOrder(); purchaseVendor.setVendorPhoneNumber(vendorPhoneNumber);
	 * logger.debug("FirmName"+purchaseVendor.getVendorPhoneNumber());
	 * purchaseVendor.setVendorInfo(vendorInfo);
	 * setVendorview(purchaseOrder.getVendorview());
	 * purchaseVendor.setOrderDate(orderDate);
	 * purchaseVendor.setOrderNumber(orderNumber);
	 * purchaseVendor.setTotalPrice(totalPrice);
	 * purchaseVendor.setFirmName(firmName);
	 * logger.debug("firmname"+purchaseVendor.getVendorPhoneNumber());
	 * controller.getPurchaseVendorView(purchaseVendor);
	 * logger.debug("Before---------"+purchaseVendor.result5.size());
	 * if(purchaseVendor.result5.size()>0) {
	 * logger.debug("Back to Bean 1-------------");
	 * setResult5(purchaseVendor.result5); logger.debug("inside ");
	 * flag1="1";flag2="none";flag="none"; }
	 * if(purchaseVendor.result5.size()==0) {
	 * logger.debug("Back to Bean- 2------------");
	 * setResult5(purchaseVendor.result5); }
	 * 
	 * 
	 * logger.debug("size--------->"+result5.size());
	 * 
	 * flag1="1";flag2="none";
	 * 
	 * return ""; }
	 * 
	 * catch(Exception e) {
	 * 
	 * 
	 * logger.error("Inside Exception",e);
	 * logger.debug("inside catch"+e.getStackTrace()); flag2="1"; return "";
	 * 
	 * } finally {
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 */

	//
	/* Sivaranjini 10/1/15 */
	public String purchaseView1() {
		setOrderDate(null);
		setToDate(null);
		DemoController controller = null;
		ApplicationContext ctx = null;
		try {
			if (vendorPhoneNumber.equalsIgnoreCase("")) {
				flag = "none";
				flag1 = "none";
				flag2 = "none";
				throw new DemoException("Please Enter the Vendor Name");
			}

			flag = "none";
			// flags="1";
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchases = controller.getpurchaseDataFVendor(vendorPhoneNumber);
			setValidate("");
			if (purchases.size() > 0) {
				logger.debug("Back to Bean 1-------------");
				setResult5(purchases);
				for (int i = 0; i < purchases.size(); i++) {
					/*
					 * purchases.get(i).setTotalPrice(GenerateEmployee.numberFormat
					 * .format(new
					 * BigDecimal(purchases.get(i).getTotalPrice())));
					 */
				}
				logger.debug("inside ");
				flag1 = "1";
				flag2 = "none";
				flag = "none";
			}
			if (purchases.size() == 0) {
				flag = "none";
				flag1 = "none";
				flag2 = "1";
				logger.debug("Back to Bean- 2------------");
				setResult5(purchases);
			}

			logger.debug("size--------->" + result5.size());

			flag1 = "1";
			flag2 = "none";

			return "";
		} catch (DemoException i) {
			setValidate(i.getMessage());
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			logger.debug("inside catch" + e.getStackTrace());
			flag1 = "none";
			flag2 = "1";
			return "";

		} finally {

		}
	}

	public String cancel1() {

		flag = "none";
		flag1 = "none";
		flag2 = "none";
		orderDate = null;
		toDate = null;
		vendorPhoneNumber = null;
		return "purchaseView";

	}

	public String editpurchase1() {
		try {
			/* purchaselist=null; */
			setValidate1("");
			logger.debug("--------------$$$$$$$$$$$$$$------------inside purchaseOrdercancel mb-------------$$$$$$$$$$$$$$-----------");
			Validate = null;
			logger.debug("order number::::" + orderNumber);
			flag = "none";
			logger.debug("middle purchaseOrdercancel::::::::::::");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setPurchaseid(purchaseid);
			purchaselist = controller.purchaseOrdercancel1(orderNumber,
					purchaselist, purchaseOrder);
			logger.debug("1");
			if (purchaselist == null) {
				flag1 = "none";
				logger.debug("inside if::::::::::::");
				throw new DemoException(
						"* This order is already cancelled or delivered");
			} else {
				logger.debug("valu::::::" + purchaselist);
				int i = 0;
				flag1 = "1";
				flag = "none";
				logger.debug("outside ::::::::::::::"
						+ purchaselist.get(i).getI0031().getI0001()
								.getProductName());
				purchaselist.get(i).setQuantityTotal(
						purchaselist.get(i).getQuantity());
				firmName = purchaselist.get(i).getI0031().getI0025()
						.getFirmName();
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
				sourceCurrency=purchaselist.get(i).getI0015().getCurrencyType();
				logger.debug("margin price------------->"
						+ purchaseOrder.getResult4().get(0).getMarginPrice());
				if (!purchaseOrder.getResult4().get(0).getMarginPrice()
						.equalsIgnoreCase("0")) {
					logger.debug("inside if");
					throw new Exception();
				}
				logger.debug("--------------$$$$$$$$$$$$$$------------Outside purchaseOrdercancel mb1-------------$$$$$$$$$$$$$$-----------");
			}
			logger.debug("1");

			return "";
		} catch (DemoException ie) {

			setValidate(ie.getMessage());
			setValidate("* This order is already cancelled or delivered");
			/* setValidate(ie.getMessage()); */
			logger.debug(ie.getMessage());
			logger.debug("ss" + purchaseOrder.getPurchaselist().size());
			setPurchaselist(purchaseOrder.getPurchaselist());
			flag1 = "1";
			logger.debug("inside the exception");

			return "";
		} catch (Exception e) {
			setValidate("*This order already started payment");
			logger.error("Inside Exception", e);
			return "";
		}

		finally {

		}

	}

	public String purchaseEditRedirect() {
		try {
			orderDate = null;
			toDate = null;
			vendorPhoneNumber = null;
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "redirect1";
	}

	public String printok() {
		return "printokpurchaseorder";
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public List<PurchaseOrder> getResultview() {
		return resultview;
	}

	public void setResultview(List<PurchaseOrder> resultview) {
		this.resultview = resultview;
	}

	/* jency */
	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public PurchaseViewMB() {
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

	public ArrayList<I0015> purchaselist1;
	ArrayList<PurchaseOrderCloseMB> purchaseOrderCloseMBs = null;

	public ArrayList<PurchaseOrderCloseMB> getPurchaseOrderCloseMBs() {
		return purchaseOrderCloseMBs;
	}

	public void setPurchaseOrderCloseMBs(
			ArrayList<PurchaseOrderCloseMB> purchaseOrderCloseMBs) {
		this.purchaseOrderCloseMBs = purchaseOrderCloseMBs;
	}

	public ArrayList<I0015> getPurchaselist1() {
		return purchaselist1;
	}

	public void setPurchaselist1(ArrayList<I0015> purchaselist1) {
		this.purchaselist1 = purchaselist1;
	}

	public String purchaseOrderClose() {
		logger.debug("--------------$$$$$$$$$$$$$$------------inside purchaseOrderClose mb-------------$$$$$$$$$$$$$$-----------");
		try {
			purchaseOrder.setSalesIdReference(orderNumber);
			logger.debug("middle purchaseOrdercancel::::::::::::");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.getpurchaseView(purchaseOrder);
			resultview = purchaseOrder.getResulfinal();
			logger.debug("Size....." + resultview.size());
			purchaseOrder.setStatus3(resultview.get(0).getStatus3());
			purchaseOrder.setStatus2(resultview.get(0).getStatus2());
			logger.debug("status3" + purchaseOrder.getStatus3()
					+ purchaseOrder.getResulfinal().get(0).getStatus3());
			logger.debug("status3" + purchaseOrder.getStatus2()
					+ purchaseOrder.getResulfinal().get(0).getStatus2());
			if (purchaseOrder.getStatus3().equalsIgnoreCase("delivered")
					&& purchaseOrder.getStatus2().equalsIgnoreCase("paid")) {
				logger.debug("inside if");
				RequestContext.getCurrentInstance().execute(
						"PF('poclose').show();");
			} else {
				logger.debug("inside else");
				RequestContext.getCurrentInstance().execute(
						"PF('poclose1').show();");
			}
			logger.debug("--------------$$$$$$$$$$$$$$------------Outside purchaseOrderClose mb-------------$$$$$$$$$$$$$$-----------");
			return "";
		}

		catch (Exception e) {

			logger.error("Inside Exception", e);
			return "";
		} finally {

		}
	}

	public String redirect5() {
		flag = "none";
		flag1 = "none";
		flag2 = "none";
		setToDate(null);
		setOrderDate(null);
		setVendorPhoneNumber("");
		return "redirect5";

	}

	// prema begin 29/04/2016 dialog box creation for purchase view
	public void purchaseview() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("purchaseView", options,
				null);
		redirect1();
	}

	// prema end 29/0/2016
	public void purchaseviewclose() {
		RequestContext.getCurrentInstance().closeDialog("purchaseView");
	}

	public void purchaseorderview() throws ParseException {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 600);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("purchaseOrderView",
				options, null);
		view();
	}

	public void purchaseorderviewclose() {
		RequestContext.getCurrentInstance().closeDialog("purchaseOrderView");
	}

	public void purchaseorderedit() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("purchaseOrderEdit",
				options, null);
		editpurchase1();
	}

	public void purchaseordereditclose() {
		RequestContext.getCurrentInstance().closeDialog("purchaseOrderEdit");
	}

	public void purchaseordercancel() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog(
				"purchaseOrdercancelconform1", options, null);
		cancel();
	}

	public void purchaseordercancelclose() {
		RequestContext.getCurrentInstance().closeDialog(
				"purchaseOrdercancelconform1");
	}
	public void onrowselect(final SelectEvent event){
		logger.debug("inside row select");
		orderNumber = ((PurchaseViewMB) event.getObject()).getOrderNumber();
		logger.debug("ordernumber"+orderNumber); 
		purchaseorderedit();
		
	}
	
	public String purchaseApproval(){
		String status="";DemoController controller = null;
		int count=0;setValidate("");
		try{
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			for (int i = 0; i < result4.size(); i++) {
				if(result4.get(i).isPurchaseCheck()==true){
					System.out.println("inside if----");
					count++;
				}
			}
			if(count==0){
				System.out.println("inside if=====");
				throw new Exception("Please Choose atleast one row for Approval.");
			}else{
				setValidate("");
				status=controller.purchaseApproval(result4);
				System.out.println("status"+status);
				if(status.equalsIgnoreCase("Success")){
					RequestContext.getCurrentInstance().execute("PF('approvalConfirm').show()");
				}
			}
		}catch(Exception e){
			setValidate(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}
	
public String serialno3;
	

	public String getSerialno3() {
		return serialno3;
	}

	public void setSerialno3(String serialno3) {
		this.serialno3 = serialno3;
	}

	public String purchaseView() {
		try {
			purchaseOrder.setFilterList(null);
			setVendorPhoneNumber(null);
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			setValidate(null);
			setValidate1("");int count=0;
			purchaselist = null;
			setResult4(null);
			flag = "none";
			flag1 = "none";
			flag2 = "none";
			logger.debug("inside purchaseOrdercancel::::::::::::");
			logger.debug("order number::::" + s);
			logger.debug("order date----------->" + orderDate);
			logger.debug("To date----------->" + toDate);
			purchaseOrder.setOrderDate(orderDate);
			purchaseOrder.setToDate(toDate);
			purchaseOrder.setApproval(approval);
			
			/*
			 * if(orderDate==null) { throw new
			 * DemoException("Please Choose the From Date"); } if(toDate==null)
			 * { throw new DemoException("Please Choose the To Date"); }
			 */
			logger.debug("middle purchaseOrdercancel::::::::::::");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist = controller.purchaseView(purchaseOrder, purchaselist);
			purchaseOrder.setPurchaselist(purchaselist);
			if (purchaselist == null) {
				flag = "none";
				flag1 = "none";
				flag2 = "1";
				logger.debug("inside if::::::::::::");
				throw new DemoException("");
			}
			logger.debug("valu::::::" + purchaselist);
			setResults(purchaseOrder.getResult4());
			if (results.size() > 0) {
				result4=new ArrayList<PurchaseOrder>();
				for (int i = 0; i < results.size(); i++) {
					PurchaseOrder purchase=new PurchaseOrder();
					purchase.setTotalPrice(
							GenerateEmployee.numberFormat
									.format(new BigDecimal(results.get(i)
											.getTotalPrice())));
					purchase.setSerialno3(String.valueOf(i+1));  
					purchase.setOrderDate(results.get(i).getOrderDate());
					purchase.setOrderNumber(results.get(i).getOrderNumber());
					purchase.setFirmName(results.get(i).getFirmName());
					purchase.setNum(results.get(i).getNum());
					purchase.setPhonenumber(results.get(i).getPhonenumber());
					purchase.setIndustry(results.get(i).getIndustry());
					purchase.setTot(results.get(i).getTot());
					purchase.setTotalPrice(results.get(i).getTotalPrice());
					purchase.setApproveStatus(results.get(i).getApproveStatus());
					purchase.setPid(results.get(i).getPid());
					purchase.setPayableAmount(results.get(i).getPayableAmount());
					purchase.setUserID(String.valueOf(results.get(i).getUserID()));
					purchase.setStatus(results.get(i).getStatus());
					purchase.setStatus2(results.get(i).getStatus2());
					purchase.setStatus3(results.get(i).getStatus3());
					purchase.setApprovalStatus(results.get(i).getApprovalStatus());
					purchase.setStatus4(results.get(i).getStatus4());
					purchase.setPurchaseid(results.get(i).getPurchaseid());
					purchase.setBaseCurrency(results.get(i).getBaseCurrency());
					purchase.setCurrency(results.get(i).getCurrency());
					result4.add(purchase);
					/*if(results.get(i).getUserID().equals(userID)){
						result4.add(purchase);
					}else{
						if(results.get(i).getApproveStatus().equals("draft")){
							result4.add(purchase);
						}
					}*/
					if(results.get(i).getApproveStatus().equals("draft")){
						count++;
					}
				}
				if(count==0){
					approveButtonFlag="none";
				}else{
					approveButtonFlag="1";
				}
			}
			flag = "1";
			flag1 = "none";
			flag2 = "none";
			int i = 0;

			logger.debug("outside ::::::::::::::"
					+ purchaselist.get(i).getI0031().getI0001()
							.getProductName());
			// logger.debug(purchaselist.get(0).getTotalPrice());
			logger.debug("outside purchaseOrdercancel::::::::::::");
			return "";
		} catch (DemoException ie) {
			setValidate(ie.getMessage());
			flag = "none";
			logger.debug(ie.getMessage());

			return "";
		} catch (Exception e) {
			flag = "none";
			flag2 = "1";
			logger.error("Inside Exception", e);
			logger.debug("log::::enter date" + e.getStackTrace());
			return "";

		} finally {

		}

	}


}