package com.inacsys.managedBean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;








import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.domain.Vendor;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0025;
import com.inacsys.shared.I0028;
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

@ManagedBean(name = "purchaseOrderFromMB")
public class PurchaseOrderFromMB {
	@ManagedProperty(value = "#{purchaseViewMB}")
	PurchaseViewMB purchaseViewMB;
	@ManagedProperty(value = "#{purchaseReturnFormMB}")
	PurchaseReturnFormMB purchaseReturnFormMB;
	@ManagedProperty(value = "#{invoicePurhcaseMB}")
	InvoicePurhcaseMB invoicePurhcaseMB;
	@ManagedProperty(value = "#{accountOutMB}")
	AccountOutMB accountOutMB;
	@ManagedProperty(value = "#{stockInFormMB}")
	StockInFormMB stockInFormMB;
	
	public StockInFormMB getStockInFormMB() {
		return stockInFormMB;
	}

	public void setStockInFormMB(StockInFormMB stockInFormMB) {
		this.stockInFormMB = stockInFormMB;
	}

	public AccountOutMB getAccountOutMB() {
		return accountOutMB;
	}

	public void setAccountOutMB(AccountOutMB accountOutMB) {
		this.accountOutMB = accountOutMB;
	}

	public InvoicePurhcaseMB getInvoicePurhcaseMB() {
		return invoicePurhcaseMB;
	}

	public void setInvoicePurhcaseMB(InvoicePurhcaseMB invoicePurhcaseMB) {
		this.invoicePurhcaseMB = invoicePurhcaseMB;
	}

	public PurchaseReturnFormMB getPurchaseReturnFormMB() {
		return purchaseReturnFormMB;
	}

	public void setPurchaseReturnFormMB(
			PurchaseReturnFormMB purchaseReturnFormMB) {
		this.purchaseReturnFormMB = purchaseReturnFormMB;
	}

	public PurchaseViewMB getPurchaseViewMB() {
		return purchaseViewMB;
	}

	public void setPurchaseViewMB(PurchaseViewMB purchaseViewMB) {
		this.purchaseViewMB = purchaseViewMB;
	}

	private static Logger logger = Logger.getLogger(PurchaseOrderFromMB.class);
	public String vendor_phone_number;
	public List<I0025> drop;
	public ArrayList<String> productlist = null;
	public List<String> productName;
	public String firmName;
	public ArrayList<String> firmName1 = new ArrayList<String>();
	public String productName1;
	public String sellingPrice;
	public Date orderDate;
	public Date targentDate;
	public String orderDate1;
	public String targentDate1;
	public Float quantity;
	public int product_ID;
	public String product_name;
	public String totalPrice;
	public String orderNumber;
	public int counter = 0;
	public String validate;
	public String actualPrice;
	public String quantity1;
	public String validate1;
	private String unit;
	private String uflag1;
	private String uflag2;
	ArrayList<PurchaseOrder> result4 = null;
	public String tDate;
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	public String destinationCurrency;

	public String getDestinationCurrency() {
		return destinationCurrency;
	}

	public void setDestinationCurrency(String destinationCurrency) {
		this.destinationCurrency = destinationCurrency;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String gettDate() {
		return tDate;
	}

	public void settDate(String tDate) {
		this.tDate = tDate;
	}

	public String getOrderDate1() {
		return orderDate1;
	}

	public void setOrderDate1(String orderDate1) {
		this.orderDate1 = orderDate1;
	}

	public String getTargentDate1() {
		return targentDate1;
	}

	public void setTargentDate1(String targentDate1) {
		this.targentDate1 = targentDate1;
	}

	public ArrayList<PurchaseOrder> getResult4() {
		return result4;
	}

	public void setResult4(ArrayList<PurchaseOrder> result4) {
		this.result4 = result4;
	}

	public String getQuantity1() {
		return quantity1;
	}

	public void setQuantity1(String quantity1) {
		this.quantity1 = quantity1;
	}

	public String getValidate1() {
		return validate1;
	}

	public void setValidate1(String validate1) {
		this.validate1 = validate1;
	}

	public String getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(String actualPrice) {
		this.actualPrice = actualPrice;
	}

	public ArrayList<String> getFirmName1() {
		return firmName1;
	}

	public void setFirmName1(ArrayList<String> firmName1) {
		this.firmName1 = firmName1;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
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

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getProductName1() {
		return productName1;
	}

	public void setProductName1(String productName1) {
		this.productName1 = productName1;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public List<String> getProductName() {
		return productName;
	}

	public void setProductName(List<String> productName) {
		this.productName = productName;
	}

	public ArrayList<String> getProductlist() {
		return productlist;
	}

	public void setProductlist(ArrayList<String> productlist) {
		this.productlist = productlist;
	}

	public String getVendor_phone_number() {
		return vendor_phone_number;
	}

	public void setVendor_phone_number(String vendor_phone_number) {
		this.vendor_phone_number = vendor_phone_number;
	}

	public List<I0025> getDrop() {
		return drop;
	}

	public void setDrop(List<I0025> drop) {
		this.drop = drop;
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
		setResult4(null);
		setFirmName(null);
		setProduct_ID(0);
		setProduct_name(null);
		setOrderDate(null);
		setTargentDate(null);
		setQuantity(null);
		setValidate(null);
		setProductName(null);
		setVendor_phone_number(null);
		setDueDate(null);
		logger.info("-----------inside reirect----------------");
		return "redirect";
	}

	public String purchaseDrop() {
		try {
			logger.info("------------------inside purchaseDrop mb------------------");
			purchaseOrder.setVendor_phone_number(vendor_phone_number);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			drop = controller.purchaseDrop(drop, purchaseOrder);
			logger.info("-----------------outside purchaseDrop mb------------------");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "success";
	}

	public void changeDrop(ValueChangeEvent changeEvent) {
		try {

			logger.info("------------------inside changeDrop mb------------------");
			String s = changeEvent.getNewValue().toString();
			purchaseOrder.setFirmName(firmName);
			purchaseOrder.setVendor_phone_number(s);
			System.out.println(s);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			productlist = (ArrayList<String>) controller.changeDrop(s,
					productlist);
			if (productlist.size() == 0) {
				System.out.println("inside empty product list......");
				productName.clear();
				throw new DemoException("");
			}
			logger.info("-------------inside product list-------------------");
			System.out.println("size-------------" + firmName1);
			controller.changeFirmName(purchaseOrder);
			System.out.println("firm name----------->" + purchaseOrder.getFirmName());
			setFirmName(purchaseOrder.getFirmName());
			setProductName(productlist);
			System.out.println("firmname---------------->" + firmName);
			logger.info("-----------------ouside changeDrop mb------------------");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public void changeDrop1(ValueChangeEvent changeEvent) {
		try {
			logger.info("-----------------inside changeDrop1 mb------------------");
			String s = changeEvent.getNewValue().toString();
			String a = getVendor_phone_number();
			System.out.println("phone number" + s);
			System.out.println("s------------->" + s);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			s = controller.changeDrop1(s);
			System.out.println("mb final----------->" + s);
			setSellingPrice(s);
			logger.info("-----------------outside changeDrop1 mb------------------");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy");

	public String puruchaseOrder() {
		try {
			validate = "";
			logger.info("-----------------inside puruchaseOrder mb------------------");
			System.out.println(vendor_phone_number);
			if (vendor_phone_number.equals("")) {
				logger.info("----------inside if firm----------------");
				throw new DemoException("*Enter the Vendor Number");
			} else if (product_name.equals("Select")) {
				throw new DemoException("*Enter the Product Name");
			} else if (orderDate == null) {
				throw new DemoException("*Enter the Order Date");
			} else if (targentDate == null) {
				throw new DemoException("*Enter the Estimated Date");
			} else if (quantity == 0) {
				throw new DemoException("*Enter the Quantity");
			} else if (dueDate == null) {
				throw new DemoException("*Enter the due date");
			}
			/*
			 * if(quantity.matches("[0-9]*\\.?[0-9]+")) { throw new
			 * InventoryException("*Enter the valid Quantity"); }
			 */
			/*
			 * if(!quantity.equals("")) { String
			 * res=VendorRegisterFormMB.isFloatValue(quantity);
			 * if(res.equalsIgnoreCase("Match")) {
			 * System.out.println("valid Quantity"); } else { throw new
			 * InventoryException("* Quantity accept ony numeric values"); } }
			 */
			purchaseOrder.setVendor_phone_number(vendor_phone_number);
			purchaseOrder.setFirmName(firmName);
			purchaseOrder.setProduct_ID(product_ID);
			purchaseOrder.setProduct_name(product_name);
			purchaseOrder.setOrderDate(orderDate);
			purchaseOrder.setTargentDate(targentDate);
			purchaseOrder.setQuantity("" + quantity);
			dueDate = sample(orderDate);
			System.out.println("Due--------------------------------------->"
					+ dueDate);
			purchaseOrder.setDueDate(dueDate);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.puruchaseOrder(purchaseOrder);
			tDate = sdf1.format(Calendar.getInstance().getTime());
			sellingPrice = purchaseOrder.getSellingPrice();
			actualPrice = "" + purchaseOrder.getActualPrice();
			totalPrice = purchaseOrder.getTotalPrice();
			orderDate1 = sdf.format(orderDate);
			targentDate1 = sdf.format(targentDate);
			dueDate1 = sdf.format(dueDate);
			System.out.println("totalprice------------->" + totalPrice);
			logger.info("-----------------outside puruchaseOrder mb------------------");
			return "success1";
		} catch (DemoException ie) {
			setValidate(ie.getMessage());
			logger.error("----------------inside exception-----------------");
			logger.error("" + ie.getMessage());
			return "failure1";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "failure1";
		}
	}

	public String savePuruchaseOrder1() {
		try {
			setValidate1("");
			logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$inside savePuruchaseOrder1 order$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			if (product_name.equals("Select")) {
				throw new DemoException("*please enter the product name");
			} else if (quantity1.equals("")) {
				throw new DemoException("*please enter the quantity");
			}
			purchaseOrder.setVendor_phone_number(vendor_phone_number);
			purchaseOrder.setFirmName(firmName);
			purchaseOrder.setProduct_ID(product_ID);
			purchaseOrder.setProduct_name(product_name);
			purchaseOrder.setOrderDate(orderDate);
			purchaseOrder.setTargentDate(targentDate);
			purchaseOrder.setQuantity(quantity1);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.puruchaseOrder(purchaseOrder);
			actualPrice = "" + purchaseOrder.getActualPrice();
			sellingPrice = purchaseOrder.getSellingPrice();
			totalPrice = purchaseOrder.getTotalPrice();
			logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$outside savePuruchaseOrder1 order$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			return "success6";
		} catch (DemoException ie) {
			setValidate1(ie.getMessage());
			logger.error("-------------inside exception----------------");
			logger.error("" + ie.getMessage());
			return "failure6";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		} finally {

		}
	}

	public String savePuruchaseOrder2() {
		try {
			setValidate1("");
			logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$inside savePuruchaseOrder2 order$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			if (product_name.equals("Select")) {
				throw new DemoException("*please enter the product name");
			} else if (quantity1.equals("")) {
				throw new DemoException("*please enter the quantity");
			}
			purchaseOrder.setQuantity1(quantity1);
			purchaseOrder.setProduct_name(product_name);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.savePuruchaseOrder1(purchaseOrder);
			logger.info("-----------------enter into save purachase order------------------------");
			orderNumber = purchaseOrder.getOrderNumber();
			System.out.println("firm name-------->" + firmName);
			System.out.println("Product_ID----------->" + product_ID);
			System.out.println("Product_name------>" + product_name);
			System.out.println("TargentDate-------->" + targentDate);
			System.out.println("Quantity---------->" + orderDate);
			System.out.println("OrderDate--------->" + orderDate);
			System.out.println("unitprice---------->" + sellingPrice);
			System.out.println("total price----------->" + totalPrice);
			System.out.println("Vendor_phone_number----------->"
					+ vendor_phone_number);
			System.out.println("$$$$$$$$$$$$$ order number$$$$$$$$$$"
					+ purchaseOrder.getOrderNumber());
			setQuantity1("");
			setProduct_name("");
			logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$outside savePuruchaseOrder2 order$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			return "success7";
		} catch (DemoException ie) {
			setValidate1(ie.getMessage());
			logger.error("------------inside exception-------------------");
			logger.error("" + ie.getMessage());
			return "failure7";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		} finally {

		}
	}

	public String savePuruchaseOrder() {
		try {
			logger.info("------------inside savePuruchaseOrder-------------------");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.savePuruchaseOrder(purchaseOrder);
			orderNumber = purchaseOrder.getOrderNumber();
			setProduct_name("");
			logger.info("------------outside savePuruchaseOrder-------------------");
			return "success2";
		} catch (DemoException ie) {
			logger.error("---------------inside exception----------------");
			logger.error("errrrrrrrrrrrrrrrrrrr" + ie.getMessage());
			return "failure2";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		} finally {

		}
	}

	public String purchaseClose1() {
		try {
			logger.info("###################inside purchaseClose1####################");
			setValidate1("");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$inside second purcahse order$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			if (product_name.equals("Select")) {
				throw new DemoException("*please enter the product name");
			} else if (quantity == 0) {
				throw new DemoException("*please enter the quantity");
			}

			purchaseOrder.setQuantity1("" + quantity);
			purchaseOrder.setProduct_name(product_name);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.savePuruchaseOrder(purchaseOrder);

			controller.purchaseClose(purchaseOrder);
			result4 = purchaseOrder.getResult4();
			totalPrice = "0";
			System.out.println("--------resulr--------" + result4.size());
			if (result4.size() > 0) {
				for (int i = 0; i < result4.size(); i++) {
					totalPrice = ""
							+ new BigDecimal(totalPrice).add(new BigDecimal(
									result4.get(i).getMarginPrice()));
					System.out.println("------aaaaaaaaaaaaaa-----" + totalPrice);
				}
			}
			System.out.println("enter into save purachase order");
			orderNumber = purchaseOrder.getOrderNumber();
			System.out.println("firm name:::" + firmName);
			System.out.println("Product_ID:::" + product_ID);
			System.out.println("Product_name:::" + product_name);
			System.out.println("TargentDate:::" + targentDate);
			System.out.println("Quantity:::" + orderDate);
			System.out.println("OrderDate:::" + orderDate);
			System.out.println("unitprice:::" + sellingPrice);
			System.out.println("total price:::" + totalPrice);
			System.out.println("Vendor_phone_number:::" + vendor_phone_number);
			System.out.println("$$$$$$$$$$$$$ order number$$$$$$$$$$"
					+ purchaseOrder.getOrderNumber());
			setQuantity1("");
			setProduct_name("");

			logger.info("##################outside purchaseClose1####################");
			return "successClose1";
		} catch (Exception e) {
			setValidate1(e.getMessage());
			System.out.println("inside exception:::::::::::::");
			System.out.println("errrrrrrrrrrrrrrrrrrr" + e.getMessage());
			logger.error("Inside Exception", e);
			return "failureClose1";
		}

	}

	public String purchaseClose() {
		try {
			System.out.println("###################inside purchase close####################");
			setValidate1("");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$inside second purcahse order$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			if (product_name.equals("Select")) {
				throw new DemoException("*please enter the product name");
			} else if (quantity1.equals("")) {
				throw new DemoException("*please enter the quantity");
			}

			purchaseOrder.setQuantity1(quantity1);
			purchaseOrder.setProduct_name(product_name);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.savePuruchaseOrder1(purchaseOrder);

			controller.purchaseClose(purchaseOrder);
			result4 = purchaseOrder.getResult4();
			totalPrice = "0";
			System.out.println("--------resulr--------" + result4.size());
			if (result4.size() > 0) {
				for (int i = 0; i < result4.size(); i++) {
					totalPrice = ""
							+ new BigDecimal(totalPrice).add(new BigDecimal(
									result4.get(i).getMarginPrice()));
					System.out.println("------aaaaaaaaaaaaaa-----" + totalPrice);
				}
			}
			System.out.println("enter into save purachase order");
			orderNumber = purchaseOrder.getOrderNumber();
			System.out.println("firm name:::" + firmName);
			System.out.println("Product_ID:::" + product_ID);
			System.out.println("Product_name:::" + product_name);
			System.out.println("TargentDate:::" + targentDate);
			System.out.println("Quantity:::" + orderDate);
			System.out.println("OrderDate:::" + orderDate);
			System.out.println("unitprice:::" + sellingPrice);
			System.out.println("total price:::" + totalPrice);
			System.out.println("Vendor_phone_number:::" + vendor_phone_number);
			System.out.println("$$$$$$$$$$$$$ order number$$$$$$$$$$"
					+ purchaseOrder.getOrderNumber());
			setQuantity1("");
			setProduct_name("");

			System.out.println("###################outside purchase close####################");
			return "successClose";
		} catch (Exception e) {
			setValidate1(e.getMessage());
			System.out.println("inside exception:::::::::::::");
			System.out.println("errrrrrrrrrrrrrrrrrrr" + e.getMessage());
			return "failureClose";
		}
	}

	public String reset() {
		setValidate(null);
		setVendor_phone_number(null);
		setProductName(null);
		setOrderDate(null);
		setTargentDate(null);
		setQuantity(null);
		setDueDate(null);
		return "";
	}

	public Date dueDate;
	public String dueDate1;

	public String getDueDate1() {
		return dueDate1;
	}

	public void setDueDate1(String dueDate1) {
		this.dueDate1 = dueDate1;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public static Date sample(Date dd) {
		Date fd = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date d = df.parse(df.format(dd));
			Calendar now = new GregorianCalendar();
			now.setTime(d);
			now.add(Calendar.MONTH, 1);
			String s = now.get(Calendar.DATE) + "/"
					+ (now.get(Calendar.MONTH) + 1) + "/"
					+ now.get(Calendar.YEAR);
			System.out.println("Date---------------------------->" + s);
			fd = df.parse(s);

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

		return fd;
	}

	public static Date beforeonemonth(Date dd) {
		Date fd = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date d = df.parse(df.format(dd));
			Calendar now = new GregorianCalendar();
			now.setTime(d);
			now.add(Calendar.MONTH, -1);
			String s = now.get(Calendar.DATE) + "/"
					+ (now.get(Calendar.MONTH) + 1) + "/"
					+ now.get(Calendar.YEAR);
			System.out.println("Date---------------------------->" + s);
			fd = df.parse(s);

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

		return fd;
	}

	public static Date sampleAfterOneWeak(Date dd) {
		Date fd = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date d = df.parse(df.format(dd));
			Calendar now = new GregorianCalendar();
			now.setTime(d);
			now.add(Calendar.WEEK_OF_YEAR, 1);
			String s = now.get(Calendar.DATE) + "/"
					+ (now.get(Calendar.MONTH) + 1) + "/"
					+ now.get(Calendar.YEAR);
			System.out.println("Date---------------------------->" + s);
			fd = df.parse(s);

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

		return fd;
	}

	public static Date sampleBeforeOneWeak(Date dd) {
		Date fd = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date d = df.parse(df.format(dd));
			Calendar now = new GregorianCalendar();
			now.setTime(d);
			now.add(Calendar.WEEK_OF_YEAR, -1);
			String s = now.get(Calendar.DATE) + "/"
					+ (now.get(Calendar.MONTH) + 1) + "/"
					+ now.get(Calendar.YEAR);
			System.out.println("Date---------------------------->" + s);
			fd = df.parse(s);

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

		return fd;
	}

	ArrayList<PurchaseOrderFromMB> homeMBs = new ArrayList<PurchaseOrderFromMB>();
	public String serialno;
	public String flag;
	public String flag1;
	public String flag2;
	public String vendorname;
	public String price;
	public String marginPrice;
	public String netAmount = "";
	public String totalPrice1;
	public String crosstotal1;
	public Date s;
	public Date s1;

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

	/*
	 * public String getS() { return s; } public void setS(String s) { this.s =
	 * s; } public String getS1() { return s1; } public void setS1(String s1) {
	 * this.s1 = s1; }
	 */
	public String getCrosstotal1() {
		return crosstotal1;
	}

	public void setCrosstotal1(String crosstotal1) {
		this.crosstotal1 = crosstotal1;
	}

	public String getTotalPrice1() {
		return totalPrice1;
	}

	public void setTotalPrice1(String totalPrice1) {
		this.totalPrice1 = totalPrice1;
	}

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

	public String getMarginPrice() {
		return marginPrice;
	}

	public void setMarginPrice(String marginPrice) {
		this.marginPrice = marginPrice;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public ArrayList<PurchaseOrderFromMB> getHomeMBs() {
		return homeMBs;
	}

	public void setHomeMBs(ArrayList<PurchaseOrderFromMB> homeMBs) {
		this.homeMBs = homeMBs;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
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

	public String purchase() {
		try {
			productlist = null;
			orderDate = null;
			targentDate = null;
			destinationCurrency="";
			totalPrice1 = null;
			vendorname = "";
			netAmount = null;
			price = null;
			product_name = null;
			quantity = null;
			totalPrice = "";
			flag = "none";
			validate = "";
			validate1 = "";
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			System.out.println("before");
			drop = controller.purchaseDrop(drop, purchaseOrder);
			System.out.println("after");
			System.out.println("inside reference");
			if (homeMBs.size() > 0) {
				System.out.println("innnnnnn");
				homeMBs.clear();
				for (int i = 1; i <= 5; i++) {
					System.out.println("-----------inside if------------");
					PurchaseOrderFromMB homeMB = new PurchaseOrderFromMB();
					homeMB.setSerialno("" + i);
					homeMB.setProduct_name("");
					homeMB.setFlag("1");
					homeMB.setFlag1("1");
					homeMB.setFlag2("none");
					homeMBs.add(homeMB);
				}

				productName = null;

			} else {
				System.out.println("ouuuuuuuuuuuuuuu");
				for (int i = 1; i <= 5; i++) {
					System.out.println("-----------inside else------------");
					PurchaseOrderFromMB homeMB = new PurchaseOrderFromMB();
					homeMB.setSerialno("" + i);
					homeMB.setProduct_name("");
					homeMB.setFlag("1");
					homeMB.setFlag1("1");
					homeMB.setFlag2("none");
					homeMBs.add(homeMB);
				}

				productName = null;

			}
			/*
			 * System.out.println("Testing ------------------"); RequestContext
			 * context=RequestContext.getCurrentInstance();
			 * context.execute("mypoppurchaseOrder();");
			 * System.out.println("Testing ------------------");
			 */
			// return "purchaseOrder1";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			// return "purchaseOrder1";
		} finally {
		}
		return "";
	}
	public String firmRegistrationNumber;
	public String address;
	public String vendorTelephoneNumber;
	public String vendorPhoneNumber;
	public String country_ID;
	public String state;
	public String city;
	public String email_ID_vendor;
	public String faxVendor;
	public String peresonIncharge;
	public String nature_of_business_id;
	public String firmTypeStandard;
	public String frim_ID;
	public String otherfirmtype;
	public String natureofbusiness;
	public String website;
	public String note;
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOtherfirmtype() {
		return otherfirmtype;
	}

	public void setOtherfirmtype(String otherfirmtype) {
		this.otherfirmtype = otherfirmtype;
	}

	public String getFirmRegistrationNumber() {
		return firmRegistrationNumber;
	}

	public void setFirmRegistrationNumber(String firmRegistrationNumber) {
		this.firmRegistrationNumber = firmRegistrationNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVendorTelephoneNumber() {
		return vendorTelephoneNumber;
	}

	public void setVendorTelephoneNumber(String vendorTelephoneNumber) {
		this.vendorTelephoneNumber = vendorTelephoneNumber;
	}

	public String getVendorPhoneNumber() {
		return vendorPhoneNumber;
	}

	public void setVendorPhoneNumber(String vendorPhoneNumber) {
		this.vendorPhoneNumber = vendorPhoneNumber;
	}

	public String getCountry_ID() {
		return country_ID;
	}

	public void setCountry_ID(String country_ID) {
		this.country_ID = country_ID;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail_ID_vendor() {
		return email_ID_vendor;
	}

	public void setEmail_ID_vendor(String email_ID_vendor) {
		this.email_ID_vendor = email_ID_vendor;
	}

	public String getFaxVendor() {
		return faxVendor;
	}

	public void setFaxVendor(String faxVendor) {
		this.faxVendor = faxVendor;
	}

	public String getPeresonIncharge() {
		return peresonIncharge;
	}

	public void setPeresonIncharge(String peresonIncharge) {
		this.peresonIncharge = peresonIncharge;
	}

	public String getFirmTypeStandard() {
		return firmTypeStandard;
	}

	public void setFirmTypeStandard(String firmTypeStandard) {
		this.firmTypeStandard = firmTypeStandard;
	}

	public String getFrim_ID() {
		return frim_ID;
	}

	public void setFrim_ID(String frim_ID) {
		this.frim_ID = frim_ID;
	}

	public String getNatureofbusiness() {
		return natureofbusiness;
	}

	public void setNatureofbusiness(String natureofbusiness) {
		this.natureofbusiness = natureofbusiness;
	}
	public ArrayList<I0028> countrydrop1;

	public ArrayList<I0028> getCountrydrop1() {
		return countrydrop1;
	}

	public void setCountrydrop1(ArrayList<I0028> countrydrop1) {
		this.countrydrop1 = countrydrop1;
	}
	
	
	public void valueChange(ValueChangeEvent v) {
		String serialNo = "";
		try {
			setValidate("");
			productName1 = "" + v.getNewValue();
			System.out.println("inside value change-----------------" + productName1);

			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			System.out.println("---------------inside mb------------");
			controller = (DemoController) ctx.getBean("controller");
			serialNo = v.getComponent().getAttributes().get("serial")
					.toString();
			purchaseOrder.setBatchProductName(productName1);
			controller.purchasePrice(purchaseOrder);
			setPrice("" + purchaseOrder.getSellingPrice());
			setUnit("" + purchaseOrder.getUnit());
			marginPrice = "" + purchaseOrder.getMarginPrice();
			System.out.println("Inside----------------if");
			PurchaseOrderFromMB homeMB = new PurchaseOrderFromMB();
			homeMB.setSerialno(serialNo);
			homeMB.setPrice(GenerateEmployee.numberFormat.format(new BigDecimal(price)));
			homeMB.setUnit(unit);
			homeMB.setUflag1("1");
			homeMB.setUflag2("none");
			homeMB.setFlag("none");
			homeMB.setProduct_name(productName1);
			System.out.println("home size---------->" + homeMBs.size());
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
			controller.getProductName();
			for (int i = 0; i < homeMBs.size(); i++) {
				System.out.println("<=============inside for loop=========>");
				System.out.println("serial num---->" + homeMBs.get(i).getSerialno());
				System.out.println("price---->" + homeMBs.get(i).getPrice());
				System.out.println("flag---->" + homeMBs.get(i).getFlag());
				System.out.println("Product Name---->"
						+ homeMBs.get(i).getProduct_name());
				/*
				 * if(!homeMBs.get(i).getProductName().equals("")) {
				 * System.out.println
				 * ("---------------the list has value----------------"
				 * +homeMBs.get(i).getProductName());
				 * 
				 * System.out.println("product name------------>"); } else {
				 * System.out.println
				 * ("---------------the list has no value----------------"); }
				 */
			}
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
		}
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

	/*
	 * public void quantityChange1(ValueChangeEvent vi) { String amunt="";
	 * String quant=""; String serialNo=""; float temp1=0;
	 * serialNo=vi.getComponent().getAttributes().get("serial").toString();
	 * System.out.println("------------inside quantityChange-------------"); try {
	 * setValidate(""); System.out.println("value---------->"+vi.getNewValue());
	 * quantity=""+vi.getNewValue(); quant=""+vi.getNewValue();
	 * System.out.println("inside value change-----------------"+quantity);
	 * System.out.println
	 * ("value-------------product name--->"+(String)vi.getComponent(
	 * ).getAttributes().get("udhaya")); ApplicationContext ctx=null;
	 * ctx=FacesContextUtils
	 * .getWebApplicationContext(FacesContext.getCurrentInstance()); controller
	 * = (InventoryController) ctx.getBean("controller");
	 * System.out.println("serial number------------>"+serialNo);
	 * System.out.println("product name------------>"
	 * +homeMBs.get(Integer.parseInt(serialNo)-1).getProduct_name());
	 * System.out.println
	 * ("selling price--------------->"+homeMBs.get(Integer.parseInt(
	 * serialNo)-1).getPrice()); purchaseOrder.setQuantity(""+vi.getNewValue());
	 * purchaseOrder
	 * .setBatchProductName(""+homeMBs.get(Integer.parseInt(serialNo
	 * )-1).getProduct_name());
	 * if(purchaseOrder.getBatchProductName().equalsIgnoreCase("") ||
	 * purchaseOrder.getQuantity().equalsIgnoreCase("")) { throw new
	 * InventoryException("*Fill all the fields"); }
	 * 
	 * System.out.println("--------------after if loop------------------");
	 * if(homeMBs.size()>0) {
	 * System.out.println("-------------------inside if loop-----------------");
	 * PurchaseOrderFromMB homeMB=new PurchaseOrderFromMB();
	 * homeMB.setSerialno(serialNo); homeMB.setPrice(price);
	 * homeMB.setFlag("none"); homeMB.setFlag1("none"); homeMB.setFlag2("1");
	 * homeMB.setUnit(unit); homeMB.setUflag2("1"); homeMB.setUflag1("none");
	 * homeMB
	 * .setProduct_name(""+homeMBs.get(Integer.parseInt(serialNo)-1).getProduct_name
	 * ());
	 * 
	 * System.out.println("~~~q1~~~"+quant+"~~~q2~~~"+vi.getNewValue());
	 * if(!quant.matches("^\\d+(\\.\\d+)*$")) { System.out.println("float"); throw new
	 * InventoryException("Quantity should be in Numbers"); }
	 * homeMB.setQuantity(Float.parseFloat(quant));
	 * System.out.println("Quantity---------->"+""+vi.getNewValue());
	 * System.out.println("net amount------------------>"
	 * +Float.parseFloat(homeMBs.get(
	 * Integer.parseInt(serialNo)-1).getPrice().replace(",", "")));
	 * 
	 * amunt=""+((new
	 * BigDecimal(homeMBs.get(Integer.parseInt(serialNo)-1).getPrice
	 * ().replace(",", ""))).multiply(new BigDecimal(""+vi.getNewValue())));
	 * System.out.println("--amnt---"+amunt);
	 * homeMB.setNetAmount(GenerateEmployee.numberFormat.format(new
	 * BigDecimal(amunt))); System.out.println("price  -- >>"+price);
	 * homeMB.setPrice(GenerateEmployee.numberFormat.format(new
	 * BigDecimal(price)));
	 * System.out.println("home size---------->"+homeMBs.size()+""+amunt);
	 * homeMBs.set(Integer.parseInt(serialNo)-1, homeMB); } else { throw new
	 * InventoryException("error"); } System.out.println("----------->"+productlist);
	 * for(int j=0;j<productlist.size();j++) {
	 * System.out.println("-----------inside for loop------------");
	 * if(homeMBs.get(Integer
	 * .parseInt(serialNo)-1).getProduct_name().equals(productlist.get(j))) {
	 * logger
	 * .debug("------------------>product name---------->"+productlist.get(j));
	 * productlist.remove(j); } } BigDecimal temp=BigDecimal.valueOf(0); for(int
	 * i=0;i<homeMBs.size();i++) { try{
	 * if(!homeMBs.get(i).getPrice().equals("")) {
	 * System.out.println("---------------the list has value----------------"
	 * +homeMBs.get(i).getProduct_name());
	 * 
	 * temp=temp.add(new BigDecimal(homeMBs.get(i).getNetAmount().replace(",",
	 * "")));
	 * 
	 * System.out.println("total amunt---------->"+temp); } else {
	 * System.out.println("---------------the list has no value----------------"); } }
	 * catch(NullPointerException e) {
	 * System.out.println("--------------inside null pointer exception----------"); }
	 * } setTotalPrice1(""+GenerateEmployee.numberFormat.format(temp));
	 * System.out.println("----success-----"); } catch (InventoryException e) {
	 * setValidate(e.getMessage()); System.out.println(e.getMessage());
	 * PurchaseOrderFromMB homeMB=new PurchaseOrderFromMB();
	 * homeMB.setSerialno(serialNo); homeMB.setPrice(""); homeMB.setUnit("");
	 * homeMB.setFlag("1"); homeMB.setFlag1("1"); homeMB.setFlag2("none");
	 * homeMBs.set(Integer.parseInt(serialNo)-1, homeMB); } catch(Exception e) {
	 * logger.error("Inside Exception",e); } }
	 */
	public void quantityChange1(ValueChangeEvent vi) {
		String amunt = "";
		String quant = "";
		String serialNo = "";
		float temp1 = 0;
		serialNo = vi.getComponent().getAttributes().get("serial").toString();
		System.out.println("------------inside quantityChange-------------");
		try {
			setValidate("");
			System.out.println("value---------->" + vi.getNewValue());
			/* quantity=""+vi.getNewValue(); */
			quant = "" + vi.getNewValue();
			System.out.println("inside value change-----------------" + quantity);
			System.out.println("value-------------product name--->"
					+ (String) vi.getComponent().getAttributes().get("udhaya"));
			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			System.out.println("serial number------------>" + serialNo);
			System.out.println("product name------------>"
					+ homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getProduct_name());
			System.out.println("selling price--------------->"
					+ homeMBs.get(Integer.parseInt(serialNo) - 1).getPrice());
			purchaseOrder.setQuantity("" + vi.getNewValue());
			purchaseOrder.setBatchProductName(""
					+ homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getProduct_name());
			if (purchaseOrder.getBatchProductName().equalsIgnoreCase("")
					|| purchaseOrder.getQuantity().equalsIgnoreCase("")) {
				throw new DemoException("*Fill all the fields");
			}
			if (quant.equalsIgnoreCase("0")) {
				throw new DemoException("*Quantity should not be 0");
			}
			System.out.println("--------------after if loop------------------");
			if (homeMBs.size() > 0) {
				System.out.println("-------------------inside if loop-----------------");
				PurchaseOrderFromMB homeMB = new PurchaseOrderFromMB();
				homeMB.setSerialno(serialNo);
				homeMB.setPrice(price);
				homeMB.setFlag("none");
				homeMB.setFlag1("none");
				homeMB.setFlag2("1");
				homeMB.setUnit(unit);
				homeMB.setUflag2("1");
				homeMB.setUflag1("none");
				homeMB.setProduct_name(""
						+ homeMBs.get(Integer.parseInt(serialNo) - 1)
								.getProduct_name());

				System.out.println("~~~q1~~~" + quant + "~~~q2~~~" + vi.getNewValue());
				if (!quant.matches("^\\d+(\\.\\d+)*$")) {
					System.out.println("float");
					throw new DemoException("Quantity should be in Numbers");
				}
				homeMB.setQuantity(Float.parseFloat(quant));
				System.out.println("Quantity---------->" + "" + vi.getNewValue());
				System.out.println("net amount------------------>"
						+ Float.parseFloat(homeMBs
								.get(Integer.parseInt(serialNo) - 1).getPrice()
								.replace(",", "")));

				amunt = ""
						+ ((new BigDecimal(homeMBs
								.get(Integer.parseInt(serialNo) - 1).getPrice()
								.replace(",", ""))).multiply(new BigDecimal(""
								+ vi.getNewValue())));
				System.out.println("--amnt---" + amunt);
				homeMB.setNetAmount(GenerateEmployee.numberFormat
						.format(new BigDecimal(amunt)));
				System.out.println("price  -- >>" + price);
				homeMB.setPrice(GenerateEmployee.numberFormat
						.format(new BigDecimal(price)));
				System.out.println("home size---------->" + homeMBs.size() + ""
						+ amunt);
				homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
			} else {
				throw new DemoException("error");
			}
			System.out.println("----------->" + productlist);
			for (int j = 0; j < productlist.size(); j++) {
				System.out.println("-----------inside for loop------------");
				if (homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getProduct_name().equals(productlist.get(j))) {
					System.out.println("------------------>product name---------->"
							+ productlist.get(j));
					productlist.remove(j);
				}
			}
			BigDecimal temp = BigDecimal.valueOf(0);
			for (int i = 0; i < homeMBs.size(); i++) {
				try {
					if (!homeMBs.get(i).getPrice().equals("")) {
						System.out.println("---------------the list has value----------------"
								+ homeMBs.get(i).getProduct_name());

						temp = temp.add(new BigDecimal(homeMBs.get(i)
								.getNetAmount().replace(",", "")));

						System.out.println("total amunt---------->" + temp);
					} else {
						System.out.println("---------------the list has no value----------------");
					}
				} catch (NullPointerException e) {
					System.out.println("--------------inside null pointer exception----------");
				}
			}
			setTotalPrice1("" + GenerateEmployee.numberFormat.format(temp));
			System.out.println("----success-----");
		} catch (DemoException e) {
			setValidate1(e.getMessage());
			System.out.println(e.getMessage());
			PurchaseOrderFromMB homeMB = new PurchaseOrderFromMB();
			homeMB.setSerialno(serialNo);
			homeMB.setPrice("");
			homeMB.setUnit("");
			homeMB.setFlag("1");
			homeMB.setProduct_name("");
			homeMB.setFlag1("1");
			homeMB.setFlag2("none");
			homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public String edit() {
		try {
			System.out.println("----------in--------------");
			for (int j = 0; j < productlist.size(); j++) {
				System.out.println("-----------inside for loop------------");
				if (homeMBs.get(Integer.parseInt(serialno) - 1)
						.getProduct_name().equals(productlist.get(j))) {
					System.out.println("------------------>product name---------->"
							+ productlist.get(j));
				} else {
					System.out.println("----------inside else-----------");
				}

			}
			/*
			 * if(!homeMBs.get(Integer.parseInt(serialno)-1).getQuantity().equals
			 * ("")) {
			 * productlist.add(homeMBs.get(Integer.parseInt(serialno)-1).
			 * getProduct_name()); }
			 */
			PurchaseOrderFromMB homeMB = new PurchaseOrderFromMB();
			homeMB.setSerialno(serialno);
			homeMB.setNetAmount("");
			homeMB.setProduct_name("");
			homeMB.setPrice("");
			homeMB.setUnit("");
			homeMB.setFlag("1");
			homeMB.setFlag1("1");
			homeMB.setFlag2("none");
			homeMB.setUflag1("1");
			homeMB.setUflag2("none");
			BigDecimal temp = BigDecimal.valueOf(0);
			BigDecimal grosstemp = BigDecimal.valueOf(0);
			System.out.println("totalPrice1 ------ >>>  " + totalPrice1);
			System.out.println("net amount ------ >>>  " + netAmount);
			if (!netAmount.equalsIgnoreCase("")) {
				temp = temp.add((new BigDecimal(totalPrice1.replace(",", "")))
						.subtract(new BigDecimal(netAmount.replace(",", ""))));
			} else {
				try {
					temp = temp
							.add(new BigDecimal(totalPrice1.replace(",", "")));

				} catch (NullPointerException n) {
					temp = temp.add((grosstemp));
				}

			}
			homeMBs.set(Integer.parseInt(serialno) - 1, homeMB);
			// setTotalPrice1(""+temp);
			setTotalPrice1("" + GenerateEmployee.numberFormat.format(temp));
			productlist.add(product_name);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";
	}

	public String confirm() {
		try {
			purchaseOrder.setActualPrice(actualPrice);
			System.out.println("-------------inside confirm-----------");
			if (orderDate == null) {
				System.out.println("-----------inside date-------");
				throw new DemoException("Please Choose the Order Date");
			} else if (targentDate == null) {
				System.out.println("-----------inside e date-------");
				throw new DemoException(
						"Please Choose the Estimated Delivery Date");
			} else if (vendorname.equals("")) {
				System.out.println("-----------inside vendor-------");
				throw new DemoException("Please Choose the Vendor Name");

			}
			purchaseOrder.setVendorPhoneNumber(vendorname);
			setValidate1("");
			setValidate(validate);
			System.out.println("size of the final list----------->" + homeMBs.size());
			BigDecimal temp = BigDecimal.valueOf(0);
			float quant = 0;
			String total = "";int cc=0;
			for (int i = 0; i < homeMBs.size(); i++) {
				try {
					System.out.println("--Product Name-"
							+ homeMBs.get(i).getProduct_name() + "count" + i);
					if (homeMBs.get(i).getProduct_name() == null) {
						
					}else{
						if (!homeMBs.get(i).getProduct_name().equalsIgnoreCase("")) {
							System.out.println("1~~~quantity~~~~"
									+ homeMBs.get(i).getQuantity());
							if (homeMBs.get(i).getQuantity() == null) {
								System.out.println("in purchase");
								throw new DemoException(
										"Please Enter the Quantity");
							} else if (homeMBs.get(i).getQuantity() != null) {
								System.out.println("2~~value of quan~~~"
										+ homeMBs.get(i).getQuantity());
								String res = "" + homeMBs.get(i).getQuantity();
								
								if (!res.matches("^\\d+(\\.\\d+)*$")) {
									System.out.println("invalid Quantity");
									throw new DemoException(
											"Quantity should be in Numbers");
								} else {
									System.out.println("valid Quantity");
								}
							}
							System.out.println("3---------------the list has value----------------"
									+ homeMBs.get(i).getProduct_name());
							temp = temp.add(new BigDecimal(homeMBs.get(i)
									.getNetAmount().replace(",", "")));
							total = GenerateEmployee.numberFormat.format(temp);

							quant = quant + (homeMBs.get(i).getQuantity());
							System.out.println("4 total amunt---------->" + total);
						} 
					}
				} catch (Exception e) {
					logger.error("Inside Exception", e);
					System.out.println("----------null pointer exception due to no net amount");
				}
			}
			/*System.out.println("ccc "+cc);
			if(cc==homeMBs.size()){
				throw new DemoException("Please Fill All the Fields");
			}*/
			System.out.println("5 total amount---------->" + temp);
			System.out.println("6 total quantity--------->" + quant);
			purchaseOrder.setQuantity1("" + quant);
			System.out.println("c7 ross totat------->" + quant);
			purchaseOrder.setOrderDate(orderDate);
			purchaseOrder.setTargentDate(targentDate);
			int count = 0;
			for (int i = 0; i < homeMBs.size(); i++) {
				System.out.println("--Product Name-"
						+ homeMBs.get(i).getProduct_name() + "count--" + i);
				if (homeMBs.get(i).getProduct_name() != null) {
					if (!homeMBs.get(i).getProduct_name().equalsIgnoreCase("")) {
						System.out.println("8 ---------------the list has value----------------"
								+ homeMBs.get(i).getProduct_name());
						purchaseOrder.setQuantity(""
								+ homeMBs.get(i).getQuantity());

						purchaseOrder.setBatchProductName(homeMBs.get(i)
								.getProduct_name());
						try {
							if (purchaseOrder.getBatchProductName() == null
									|| purchaseOrder.getBatchProductName()
											.equalsIgnoreCase("")
									|| purchaseOrder.getQuantity()
											.equalsIgnoreCase("0")) {
								throw new DemoException(
										"Please Fill All the Fields");
							}

						} catch (NullPointerException e) {
							logger.error("Inside Exception", e);
							throw new DemoException("*Fill all the fileds");
						}
					}
				} else {
					System.out.println("9---------------the list has no value----------------");
					count++;
				}
			}
			if (count == homeMBs.size()) {
				System.out.println("10 ----------inside if----------");
				throw new DemoException("Please Choose the Products Name");
			}
			/*
			 * if(homeMBs.get(i).getQuantity()) { System.out.println("in purchase");
			 * throw new InventoryException("Please qun"); }
			 */
			purchaseOrder.setTotalAmount("" + temp);
			controller.purchase(purchaseOrder);
			totalPrice1 = "" + total;
			for (int i = 0; i < homeMBs.size(); i++) {
				System.out.println("--Product Name-"
						+ homeMBs.get(i).getProduct_name() + "count---" + i);
				if (homeMBs.get(i).getProduct_name() != null) {
					if (!homeMBs.get(i).getProduct_name().equalsIgnoreCase("")) {
						System.out.println("11 ---------------the list has value----------------"
								+ homeMBs.get(i).getProduct_name());

						purchaseOrder.setQuantity(""
								+ homeMBs.get(i).getQuantity());
						purchaseOrder.setBatchProductName(homeMBs.get(i)
								.getProduct_name());
						purchaseOrder.setNetAmount(homeMBs.get(i)
								.getNetAmount());
						controller.purchase1(purchaseOrder);
					}
				} else {
					System.out.println("---------------the list has no value----------------");
					count++;
				}
			}
			s = orderDate;
			s1 = targentDate;
			setOrderNumber(purchaseOrder.getOrderNumber());
			controller.purchaseorderClose(purchaseOrder);
			result4 = purchaseOrder.getResult4();

			System.out.println("enter into save purachase order");
			orderNumber = purchaseOrder.getOrderNumber();
			firmName = purchaseOrder.getVendorPhoneNumber();
			vendor_phone_number = purchaseOrder.getFirmName();
			System.out.println("size "+result4.size());
			if (result4.size() > 0) {
				for (int i = 0; i < result4.size(); i++) {
					result4.get(i).setCrosstotal1(
							GenerateEmployee.numberFormat
									.format(new BigDecimal(result4.get(i)
											.getCrosstotal1())));
					result4.get(i).setMarginPrice(
							GenerateEmployee.numberFormat
									.format(new BigDecimal(result4.get(i)
											.getMarginPrice())));
				}
			}
			System.out.println("firm name:::" + firmName);
			System.out.println("Product_ID:::" + product_ID);
			System.out.println("Product_name:::" + product_name);
			System.out.println("TargentDate:::" + targentDate);
			System.out.println("Quantity:::" + orderDate);
			System.out.println("OrderDate:::" + orderDate);
			System.out.println("unitprice:::" + sellingPrice);
			System.out.println("total price:::" + totalPrice);
			System.out.println("Vendor_phone_number:::" + vendor_phone_number);
			System.out.println("$$$$$$$$$$$$$ order number$$$$$$$$$$"
					+ purchaseOrder.getOrderNumber());
			System.out.println("after");
			return "purchaseSuccess";
		} catch (DemoException ei) {
			setValidate1(ei.getMessage());
			System.out.println("----------inside io exception--------"
					+ ei.getMessage());
			ei.printStackTrace();
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("inside exception ",e);
			logger.error("Inside Exception", e);

			return "";
		}

	}
	
	public String baseCurrency;
	
	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String purchaseConfirm(){
		System.out.println("purchase save");
		setValidate1("");
		try{
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			purchaseOrder.setActualPrice(actualPrice);
			System.out.println("-------------inside confirm-----------");
			if (orderDate == null) {
				throw new DemoException("Please Choose the Order Date");
			} else if (targentDate == null) {
				throw new DemoException("Please Choose the Estimated Delivery Date");
			} else if (vendorname.equals("")) {
				throw new DemoException("Please Choose the Vendor Name");
			}else if (destinationCurrency.equals("")) {
				throw new DemoException("Please Choose the Currency Type");
			}
			purchaseOrder.setVendorPhoneNumber(vendorname);
			int cc=0;BigDecimal temp = BigDecimal.valueOf(0);
			float quant = 0;
			String total = "";
			for (int i = 0; i < homeMBs.size(); i++) {
				System.out.println("product "+homeMBs.get(i).getProduct_name());
				try{
					if(homeMBs.get(i).getProduct_name().equals("") || homeMBs.get(i).getProduct_name()==null){
						System.out.println("if");
						cc++;						
					}else{
						System.out.println("else");
						if(homeMBs.get(i).getQuantity()==null){
							throw new DemoException("Please Enter the Quantity");
						}else if(homeMBs.get(i).getQuantity()!=null){
							String quantity = "" + homeMBs.get(i).getQuantity();
							if (!quantity.matches("^\\d+(\\.\\d+)*$")) {
								System.out.println("invalid Quantity");
								throw new DemoException(
										"Quantity should be in Numbers");
							}else{
								purchaseOrder.setQuantity(""+ homeMBs.get(i).getQuantity());
								purchaseOrder.setBatchProductName(homeMBs.get(i).getProduct_name());
								temp = temp.add(new BigDecimal(homeMBs.get(i)
										.getNetAmount().replace(",", "")));
								total = GenerateEmployee.numberFormat.format(temp);

								quant = quant + (homeMBs.get(i).getQuantity());
							}							
						}
					}
				}catch(DemoException e){
					e.printStackTrace();
					setValidate1(e.getMessage());
				}
			}
			if(cc==homeMBs.size()){
				throw new DemoException("*Fill all the fileds");
			}
			purchaseOrder.setQuantity1("" + quant);
			purchaseOrder.setOrderDate(orderDate);
			purchaseOrder.setTargentDate(targentDate);
			purchaseOrder.setTotalAmount("" + temp);
			purchaseOrder.setDestinationCurrency(destinationCurrency);
			purchaseOrder.setBaseCurrency(baseCurrency);
			controller.purchase(purchaseOrder);
			totalPrice1 = "" + total;
			for (int i = 0; i < homeMBs.size(); i++) {
				if (homeMBs.get(i).getProduct_name() != null) {
					purchaseOrder.setQuantity(""+ homeMBs.get(i).getQuantity());
					purchaseOrder.setBatchProductName(homeMBs.get(i).getProduct_name());
					purchaseOrder.setNetAmount(homeMBs.get(i).getNetAmount());
					controller.purchase1(purchaseOrder);
				} 
			}
			s = orderDate;
			s1 = targentDate;
			setOrderNumber(purchaseOrder.getOrderNumber());
			String status=controller.purchaseorderClose(purchaseOrder);
			result4 = purchaseOrder.getResult4();
			System.out.println("enter into save purachase order");
			orderNumber = purchaseOrder.getOrderNumber();
			firmName = purchaseOrder.getVendorPhoneNumber();
			vendor_phone_number = purchaseOrder.getFirmName();
			System.out.println("size "+result4.size());
			setTotalPrice1(purchaseOrder.getTotalPrice());
			setTotalPrice(purchaseOrder.getTotalAmount());
			setBaseCurrency(baseCurrency);
			if(status.equals("success")){
				if (result4.size() > 0) {
					for (int i = 0; i < result4.size(); i++) {
						result4.get(i).setCrosstotal1(GenerateEmployee.numberFormat.format(new BigDecimal(result4.get(i).getCrosstotal1())));
						result4.get(i).setMarginPrice(GenerateEmployee.numberFormat.format(new BigDecimal(result4.get(i).getMarginPrice())));
					}
				}
				 return "purchaseSuccess";
			}
			else {
				return "";
			}
		}catch(DemoException e){
			e.printStackTrace();
			setValidate1(e.getMessage());		
			return "";
		}
	}

	public String redirectHome2() {
		try {
			price = "";
			productName1 = "";
			System.out.println("list size----------------->" + homeMBs.size());
			int j = 0;
			j = homeMBs.size();
			int i = 0;
			validate = "";
			PurchaseOrderFromMB homeMB = new PurchaseOrderFromMB();
			homeMB.setSerialno("" + (homeMBs.size() + 1));
			homeMB.setProduct_name("");
			homeMB.setFlag("1");
			homeMB.setFlag1("1");
			homeMB.setFlag2("none");
			homeMBs.add(homeMB);
			System.out.println("count-->" + i);
			System.out.println("list size----------------->" + homeMBs.size());
			System.out.println("---------------inside redirect mb1--------------");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";
	}

	public String purchseok() {
		System.out.println("to home ---->> ");
		setVendorname(null);
		setOrderDate(null);
		setTargentDate(null);
		setQuantity(null);
		setProduct_name(null);
		return "homepurchase";
	}

	// prema begin 29/04/2016 dialog box creation for purchase order
	public void purchaseorder() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("purchaseOrderNewForm",
				options, null);
		try {
			purchase();

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

	}

	// prema end 29/0/2016

	public void purchaseorderclose() {
	/*	RequestContext.getCurrentInstance().closeDialog("purchaseOrderNewForm");*/
		System.out.println("inside purchaseorder close------------>");
		purchase();
	}

	

	private boolean purchaseorderFlag;
	private boolean purchaseviewFlag;
	private boolean purchaseorderdtFlag;
	private boolean purchaseviewdtFlag;
	private boolean purchasereturnFlag;
	private boolean purchasepaymentFlag;
	private boolean stockinFlag;
	public String categoryType;

	public boolean isStockinFlag() {
		return stockinFlag;
	}

	public void setStockinFlag(boolean stockinFlag) {
		this.stockinFlag = stockinFlag;
	}

	public boolean isPurchasepaymentFlag() {
		return purchasepaymentFlag;
	}

	public void setPurchasepaymentFlag(boolean purchasepaymentFlag) {
		this.purchasepaymentFlag = purchasepaymentFlag;
	}

	public boolean isPurchasereturnFlag() {
		return purchasereturnFlag;
	}

	public void setPurchasereturnFlag(boolean purchasereturnFlag) {
		this.purchasereturnFlag = purchasereturnFlag;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public boolean isPurchaseorderdtFlag() {
		return purchaseorderdtFlag;
	}

	public void setPurchaseorderdtFlag(boolean purchaseorderdtFlag) {
		this.purchaseorderdtFlag = purchaseorderdtFlag;
	}

	public boolean isPurchaseviewdtFlag() {
		return purchaseviewdtFlag;
	}

	public void setPurchaseviewdtFlag(boolean purchaseviewdtFlag) {
		this.purchaseviewdtFlag = purchaseviewdtFlag;
	}

	public boolean isPurchaseorderFlag() {
		return purchaseorderFlag;
	}

	public void setPurchaseorderFlag(boolean purchaseorderFlag) {
		this.purchaseorderFlag = purchaseorderFlag;
	}

	public boolean isPurchaseviewFlag() {
		return purchaseviewFlag;
	}

	public void setPurchaseviewFlag(boolean purchaseviewFlag) {
		this.purchaseviewFlag = purchaseviewFlag;
	}

	public void valueChange1(ValueChangeEvent ve) {
		String str = ve.getNewValue().toString();
		System.out.println("po category value change method calling" + str);
		if (str.equalsIgnoreCase("PO delivery")) {
			purchaseReturnFormMB.setValidate(null);
			setPurchaseviewFlag(false);
			setPurchasepaymentFlag(false);
			setStockinFlag(false);
		} else if (str.equalsIgnoreCase("PO return")) {
			purchaseReturnFormMB.purInfoReturnView();
			if (purchaseReturnFormMB.finallist.size() > 0) {
				purchaseReturnFormMB.setValidate(null);
				setPurchaseviewFlag(true);
				setPurchasepaymentFlag(false);
				setStockinFlag(false);
			} else {
				setPurchaseviewFlag(false);
				setPurchasepaymentFlag(false);
				setStockinFlag(false);
			}
		} else if (str.equalsIgnoreCase("PO invoice")) {
			purchaseReturnFormMB.setValidate(null);
			invoicePurhcaseMB.invoicePurhcase();
			setPurchaseviewFlag(false);
			setPurchasepaymentFlag(false);
			setStockinFlag(false);
		} else if (str.equalsIgnoreCase("PO payment")) {
			purchaseReturnFormMB.setValidate(null);
			accountOutMB.AccountOut();
			if (accountOutMB.purchaselist.size() > 0) {
				setPurchasepaymentFlag(true);
				setPurchaseviewFlag(false);
				setStockinFlag(false);
			} else {
				setPurchasepaymentFlag(false);
				setPurchaseviewFlag(false);
				setStockinFlag(false);
			}
		} else if (str.equalsIgnoreCase("Stock in")) {
			purchaseReturnFormMB.setValidate(null);
			stockInFormMB.setDelayreason("");
			stockInFormMB.setDeliveredDate(null);
			stockInFormMB.stockInForm();
			setPurchasepaymentFlag(false);
			setPurchaseviewFlag(false);
			setStockinFlag(true);
		} else {
			purchaseReturnFormMB.setValidate(null);
			setPurchasepaymentFlag(false);
			setPurchaseviewFlag(false);
			setStockinFlag(false);
		}
	}

	private List<PurchaseOrder> resultview = null;

	public List<PurchaseOrder> getResultview() {
		return resultview;
	}

	public void setResultview(List<PurchaseOrder> resultview) {
		this.resultview = resultview;
	}

	public String submit() {
		setValidate(null);
		try {
			if (categoryType.equalsIgnoreCase("PO delivery")) {
				System.out.println("inside po delivery"
						+ purchaseViewMB.getOrderNumber());
				purchaseViewMB.deliveryStatus();
			} else if (categoryType.equalsIgnoreCase("Stock in")) {
				stockInFormMB.addStock();
			} else if (categoryType.equalsIgnoreCase("PO close")) {
				System.out.println("inside po order close");
				setPurchaseviewFlag(false);
				purchaseViewMB.purchaseOrderClose();
			} else if (categoryType.equalsIgnoreCase("PO return")) {
				System.out.println("inside po order return");
				purchaseReturnFormMB.purchaseReturnInsert();
				setPurchaseviewFlag(true);
			} else if (categoryType.equalsIgnoreCase("PO invoice")) {
				System.out.println("inside po order invoice");
				invoicePurhcaseMB.redirect();
				setPurchaseviewFlag(false);
				setPurchasepaymentFlag(false);
			} else if (categoryType.equalsIgnoreCase("PO payment")) {
				System.out.println("inside po order payment");
				setPurchaseviewFlag(false);
				setPurchasepaymentFlag(true);
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
		}
		return "";
	}

	public void purchaseorderview() {
		setValidate(null);
		setCategoryType(null);
		stockInFormMB.setValidate(null);
		purchaseReturnFormMB.setValidate(null);
		purchaseReturnFormMB.setTempValidate(null);
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 580);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("purchaseOrderView",
				options, null);
		try {
			setPurchaseviewFlag(false);
			setPurchasepaymentFlag(false);
			setStockinFlag(false);
			purchaseViewMB.view();
			stockInFormMB.stockin1();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}
	
	public String vendorConfirm(){
		try{
			drop = controller.purchaseDrop(drop, purchaseOrder);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	/*6-4-2017*/
	
	public Date venExdate;
	public String venLicence;
	public String venCompany;
	public String venType;
	public String venCode;
	public String venPostalcode;
	public String payment;
	public String cash;
	public String newcash;
	
	public String getNewcash() {
		return newcash;
	}

	public void setNewcash(String newcash) {
		this.newcash = newcash;
	}

	public List<String> daylist=null;
	
	
	public List<String> getDaylist() {
		return daylist;
	}

	public void setDaylist(List<String> daylist) {
		this.daylist = daylist;
	}

	
	public Date getVenExdate() {
		return venExdate;
	}

	public void setVenExdate(Date venExdate) {
		this.venExdate = venExdate;
	}

	public String getVenLicence() {
		return venLicence;
	}

	public void setVenLicence(String venLicence) {
		this.venLicence = venLicence;
	}

	public String getVenCompany() {
		return venCompany;
	}

	public void setVenCompany(String venCompany) {
		this.venCompany = venCompany;
	}

	public String getVenType() {
		return venType;
	}

	public void setVenType(String venType) {
		this.venType = venType;
	}

	public String getVenCode() {
		return venCode;
	}

	public void setVenCode(String venCode) {
		this.venCode = venCode;
	}

	public String getVenPostalcode() {
		return venPostalcode;
	}

	public void setVenPostalcode(String venPostalcode) {
		this.venPostalcode = venPostalcode;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	
	public void venTypes(ValueChangeEvent v){
		String valueven="";	
		DemoController controller=null;
		try{
			 valueven=v.getNewValue().toString();
			if(!valueven.equalsIgnoreCase("Cash")){
				ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");				
				daylist=controller.getpaytype();
				System.out.println("daylist---->"+daylist.size()); 
			}
			setPayment(valueven);
			System.out.println("---------"+valueven);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void dialog(ValueChangeEvent v){
		String add="";
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			add=v.getNewValue().toString();
			System.out.println("vendor new "+add);
			if(add.equalsIgnoreCase("Add new")){
				venCode=controller.getvencode(clientID,userID);
				RequestContext.getCurrentInstance().execute("PF('confirm1').show();");  
			}
			System.out.println("add new-------------------->"+venCode); 
			setNewcash("");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String reset2(){
		setNewcash(null);    
		setValidate(null); 
		return "";
	}
		public String venCity;
		public String venCountry;
		
		public String venState;
		public String venAddress1;
		public String venCity1;
		public String venCountry1;
		public String venPostalcode1;
		public String venState1;
		public List<String> stateList=null;
		public List<String> stateList1=null;
		public boolean venBox=false; 
		public String code;
		public boolean hiddenFlag=false;
		public boolean showFlag=true;

		
	

	public boolean isVenBox() {
			return venBox;
		}

		public void setVenBox(boolean venBox) {
			this.venBox = venBox;
		}

		public boolean isHiddenFlag() {
			return hiddenFlag;
		}

		public void setHiddenFlag(boolean hiddenFlag) {
			this.hiddenFlag = hiddenFlag;
		}

		public boolean isShowFlag() {
			return showFlag;
		}

		public void setShowFlag(boolean showFlag) {
			this.showFlag = showFlag;
		}

	public String getVenCity() {
			return venCity;
		}

		public void setVenCity(String venCity) {
			this.venCity = venCity;
		}

		public String getVenCountry() {
			return venCountry;
		}

		public void setVenCountry(String venCountry) {
			this.venCountry = venCountry;
		}

		public String getVenState() {
			return venState;
		}

		public void setVenState(String venState) {
			this.venState = venState;
		}

		public String getVenAddress1() {
			return venAddress1;
		}

		public void setVenAddress1(String venAddress1) {
			this.venAddress1 = venAddress1;
		}

		public String getVenCity1() {
			return venCity1;
		}

		public void setVenCity1(String venCity1) {
			this.venCity1 = venCity1;
		}

		public String getVenCountry1() {
			return venCountry1;
		}

		public void setVenCountry1(String venCountry1) {
			this.venCountry1 = venCountry1;
		}

		public String getVenPostalcode1() {
			return venPostalcode1;
		}

		public void setVenPostalcode1(String venPostalcode1) {
			this.venPostalcode1 = venPostalcode1;
		}

		public String getVenState1() {
			return venState1;
		}

		public void setVenState1(String venState1) {
			this.venState1 = venState1;
		}

		public List<String> getStateList() {
			return stateList;
		}

		public void setStateList(List<String> stateList) {
			this.stateList = stateList;
		}

		public List<String> getStateList1() {
			return stateList1;
		}

		public void setStateList1(List<String> stateList1) {
			this.stateList1 = stateList1;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

	public void checkBoxValueChange(ValueChangeEvent vc){
		try{
			String check=vc.getNewValue().toString();
			if(check.equals("true")){
				System.out.println(check+"chk-------------->"+city);  
				setHiddenFlag(true);
				setShowFlag(false);
				setVenAddress1(address); 
				setVenCity1(city); 
				setVenCountry1(country_ID); 
				setVenPostalcode1(venPostalcode); 
				setVenState1(state); 
			}else{
				setHiddenFlag(false);
				setShowFlag(true);
				setVenAddress1(""); 
				setVenCity1(""); 
				setVenCountry1(""); 
				setVenPostalcode1(""); 
				setVenState1(""); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void selectState(ValueChangeEvent vc){
		String country = "";
		ApplicationContext ctx = null;
		DemoController controller = null;
		try{
			country = vc.getNewValue().toString();
			   ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			   controller = (DemoController) ctx.getBean("controller");
			   stateList=new ArrayList<String>();
			   stateList=controller.getstatelist(country);  
			   System.out.println("stateist-------------> "+stateList.size());
			   if(country.equalsIgnoreCase("India")) setCode("+91");
			   else if(country.equalsIgnoreCase("Indonesia")) setCode("+62");
			   else if(country.equalsIgnoreCase("Malesia")) setCode("+60");
			   else if(country.equalsIgnoreCase("Singapore")) setCode("+65");
			   else if(country.equalsIgnoreCase("UAE")) setCode("+971");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void selectState1(ValueChangeEvent vc){
		String country = "";
		ApplicationContext ctx = null;
		DemoController controller = null;
		try{
			country = vc.getNewValue().toString();
			   ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			   controller = (DemoController) ctx.getBean("controller");
			   stateList1=new ArrayList<String>();
			   stateList1=controller.getstatelist(country); 
			   System.out.println("stateist1----->"+stateList1.size());
			   if(country.equalsIgnoreCase("India")) setCode("+91");
			   else if(country.equalsIgnoreCase("Indonesia")) setCode("+62");
			   else if(country.equalsIgnoreCase("Malesia")) setCode("+60");
			   else if(country.equalsIgnoreCase("Singapore")) setCode("+65");
			   else if(country.equalsIgnoreCase("UAE")) setCode("+971");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public String vendorRegister() {
		System.out.println("inside vendor register method");
		System.out.println("--$$$$$$$$$$$$$$------------inside vendorRegister -------------$$$$$$$$$$$$$$-----------");
		DemoController controller = null;
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			System.out.println("inside managed bean:::::::");
			System.out.println("vendor  module");
			System.out.println("firm name"+firmName);
			Vendor vendor=new Vendor();
			vendor.setFirmName(firmName);
			vendor.setFirmRegistrationNumber(firmRegistrationNumber);
			vendor.setAddress(address);
			vendor.setVendorTelephoneNumber(vendorTelephoneNumber);
			vendor.setVendorPhoneNumber(vendorPhoneNumber);
			vendor.setCountry_ID(country_ID);			
			vendor.setEmail_ID_vendor(email_ID_vendor);
			//vendor.setFaxVendor(faxVendor);
			vendor.setWebsite(website);
			vendor.setNote(note);
			// vendor.setNature_of_business_id(nature_of_business_id);;
			vendor.setPeresonIncharge(peresonIncharge);
			vendor.setFirmTypeStandard(firmTypeStandard);
			// vendor.setFrim_ID(frim_ID);
			//vendor.setOtherfirmtype(otherfirmtype);
			vendor.setNatureofbusiness(natureofbusiness);
			vendor.setVenCompany(venCompany); 
			vendor.setVenCity(city);  
			vendor.setVenCountry(venCountry); 
			vendor.setVenPostalcode(venPostalcode); 
			vendor.setVenState(state); 
			vendor.setVenAddress1(venAddress1); 
			vendor.setVenCity1(venCity1); 
			vendor.setVenCountry1(venCountry1); 
			vendor.setVenPostalcode1(venPostalcode1); 
			vendor.setVenState1(venState1); 
			
			vendor.setVenLicence(venLicence); 
			vendor.setVenType(venType);
			vendor.setVenExdate(venExdate);  
			vendor.setVenCode(venCode); 
			vendor.setPayment(payment); 
			vendor.setCash(cash);  
			
			vendor.setClientID(clientID);
			vendor.setUserID(userID);
			
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String status = controller.venderReg(vendor);
			setVendorname(vendor.getVendorPhoneNumber());
			RequestContext context = RequestContext.getCurrentInstance();
			if (status.equalsIgnoreCase("success")) { 
					context.execute("PF('addvendor').hide();"); 
					context.execute("PF('confirm').show();"); 
			} else {
				/* context.execute("alert('Hai');"); */
				context.execute("checkFail();");
			}
			return "";
		} catch (DemoException ie) {
			setValidate(ie.getMessage());

			return "";
		} finally {

		}
	}
	
	public String vendorRegistercash(){
		DemoController controller=null;
		String status="";
		
		try{
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			Vendor vendor=new Vendor();
			if(newcash.equalsIgnoreCase("")){ 
				setValidate("Please Enter NewDay"); 
				//RequestContext.getCurrentInstance().execute("PF('confirm1').hide();");
			}else{ 
				vendor.setNewcash(newcash);  
				status=controller.setcash(vendor); 	
				setCash(newcash);  
				if(status.equalsIgnoreCase("Exist")){
					setValidate("Already Exist this Day"); 
				}else{
					RequestContext.getCurrentInstance().execute("PF('confirm1').hide();");
					daylist=controller.getpaytype();
					System.out.println("daylist---->"+daylist.size()); 
					RequestContext.getCurrentInstance().update("center_content:dialogPanel");
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	/*public String vendorRegistercash(){
		DemoController controller=null;
		String status="";
		try{
			Buyer buyer=new Buyer();
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if(newcash.equalsIgnoreCase("")){ 
				setValidate("Please Enter NewDay"); 
			}else{
				buyer.setNewcash(newcash);  
				status=controller.setbuycash(buyer); 	
				setCash(newcash); 
				if(status.equalsIgnoreCase("Exist")){
					setValidate("Already Exist this Day");
				}else if("Success".equalsIgnoreCase(status)){
					RequestContext.getCurrentInstance().execute("PF('confirm1').hide();");
					daylist=controller.getpaytype();
					System.out.println("statelist1---->"+stateList.size()); 
					RequestContext.getCurrentInstance().update("center_content");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}*/

	
	
	public String namecheck() {
		logger.debug("<--------------inside namecheck--------------->");
	RequestContext context = RequestContext.getCurrentInstance();
	FacesContext context1 = FacesContext.getCurrentInstance();
	DemoController controller = null;
	try {

		Map<String, String> params = context1.getExternalContext()
				.getRequestParameterMap();
		logger.debug("------------------->" + params.get("param1"));
		String name = params.get("param1");

		ApplicationContext ctx = FacesContextUtils
				.getWebApplicationContext(FacesContext.getCurrentInstance());
		controller = (DemoController) ctx.getBean("controller");
		System.out.println("name--"+name);
		String status = controller.getVendorVerification(name);
		System.out.println("status--"+status);
		if (status.equalsIgnoreCase("Success")) {
			context.execute("checkFail();");
		} else {
			context.execute("checkSuccess();");
		}
	} catch (Exception e) {
		logger.error("Inside Exception", e);
	}
	return "";

}
	
	public void changeProduct(ValueChangeEvent v) {
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			System.out.println("changeProduct value change---------->" );
			System.out.println("------------------inside changeDrop mb------------------");
			String s=v.getNewValue().toString();
			System.out.println("value=========>" +s);
			homeMBs.clear();
				for (int i = 1; i <= 5; i++) {
					System.out.println("-----------inside if------------");
					PurchaseOrderFromMB homeMB = new PurchaseOrderFromMB();
					homeMB.setSerialno("" + i);
					homeMB.setProduct_name("");
					homeMB.setFlag("1");
					homeMB.setFlag1("1");
					homeMB.setFlag2("none");
					homeMBs.add(homeMB);
				}
				System.out.println(s);
				if(s.equalsIgnoreCase("add")){
					Vendor vendor=new Vendor();
				    System.out.println("inside add new if");
				    setWebsite(null);
					setNote(null);
					setValidate(null);
					setFirmName(null);
					setFirmRegistrationNumber(null);
					setOtherfirmtype(null);
					setNatureofbusiness(null);
					setAddress(null);
					setPeresonIncharge(null);
					setCity(null);
					setVendorPhoneNumber(null);
					setCountry_ID(null);
					setVendorTelephoneNumber(null);
					setState(null);
					setEmail_ID_vendor(null);
					setFaxVendor(null);
					setFirmTypeStandard(null);
					setVenCompany(null);
					setVenCity(null); 
					setVenCountry(null); 
					setVenPostalcode(null); 
					setVenState(null); 
					setVenAddress1(null); 
					setVenCity1(null); 
					setVenCountry1(null); 
					setVenPostalcode1(null); 
					setVenState1(null); 
					setVenBox(false); 
					setVenLicence(null); 
					setVenExdate(null); 
					setVenType(null); 
					setVenCode(null); 
					setPayment(null); 
					setCash(null); 
					setNewcash(null);
					ApplicationContext ctx = FacesContextUtils
							.getWebApplicationContext(FacesContext.getCurrentInstance());
					controller = (DemoController) ctx.getBean("controller");
					//controller.countryDrop(vendor);
					//setCountrydrop1(vendor.getCountrydrop1());
					venCode=controller.getvencode(clientID,userID);
					System.out.println("vencode "+venCode);
				    RequestContext.getCurrentInstance().execute("PF('addvendor').show();");
				}
				ApplicationContext ctx = FacesContextUtils
						.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				productlist = (ArrayList<String>) controller.changeDrop(s,productlist);
				if (productlist.size() == 0) {
					System.out.println("inside empty product list......");

					throw new DemoException("");
				}
				System.out.println("-----------------ouside changeDrop mb------------------"+productlist.size());
			
		} catch (Exception e) {
			logger.error("inside exception", e);
		}
	}
	public String reset1() {
		
			setWebsite(null);
			setNote(null);
			setValidate(null);
			setFirmName(null);
			setFirmRegistrationNumber(null);
			setOtherfirmtype(null);
			setNatureofbusiness(null);
			setAddress(null);
			setPeresonIncharge(null);
			setCity(null);
			setVendorPhoneNumber(null);
			setCountry_ID(null);
			setVendorTelephoneNumber(null);
			setState(null);
			setEmail_ID_vendor(null);
			setFaxVendor(null);
			setFirmTypeStandard(null);
			setVenCompany(null);
			setVenCity(null); 
			setVenCountry(null); 
			setVenPostalcode(null); 
			setVenState(null); 
			setVenAddress1(null); 
			setVenCity1(null); 
			setVenCountry1(null); 
			setVenPostalcode1(null); 
			setVenState1(null); 
			setVenBox(false); 
			setVenLicence(null); 
			setVenExdate(null); 
			setVenType(null); 
			setVenCode(null); 
			setPayment(null); 
			setCash(null); 
			setNewcash(null);
			return "";
		}

	
	
}
