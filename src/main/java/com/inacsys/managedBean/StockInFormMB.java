package com.inacsys.managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
//import java.util.Date;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.LoginAccess;
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

@ManagedBean(name = "stockInFormMB")
public class StockInFormMB implements Serializable {
	private static Logger logger = Logger.getLogger(StockInFormMB.class);
	@ManagedProperty(value = "#{purchaseViewMB}")
	PurchaseViewMB purchaseViewMB;

	public PurchaseViewMB getPurchaseViewMB() {
		return purchaseViewMB;
	}

	public void setPurchaseViewMB(PurchaseViewMB purchaseViewMB) {
		this.purchaseViewMB = purchaseViewMB;
	}

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
	public String product_name1;
	public String totalPrice;
	public String orderNumber;
	public ArrayList<I0016> purchaselist;
	Date deliveredDate;
	String delayreason;
	private ArrayList<StockInFormMB> homeMBs = new ArrayList<StockInFormMB>();
	private ArrayList<String> productList = null;
	private String flag;
	private String serialno;
	private String serialno1;
	private String batch1;
	private String flag1;
	private String flag2;
	private String flag3;
	private String prflag;
	private String productQuantity;
	private String unit;

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public StockInFormMB getHomeMB() {
		return homeMB;
	}

	public void setHomeMB(StockInFormMB homeMB) {
		this.homeMB = homeMB;
	}

	private String flag4;

	public String getFlag4() {
		return flag4;
	}

	public void setFlag4(String flag4) {
		this.flag4 = flag4;
	}

	private ArrayList<StockInFormMB> roll1;

	public ArrayList<StockInFormMB> getRoll1() {
		return roll1;
	}

	public void setRoll1(ArrayList<StockInFormMB> roll1) {
		this.roll1 = roll1;
	}

	public ArrayList<StockInFormMB> roll = new ArrayList<StockInFormMB>();

	public String getSerialno1() {
		return serialno1;
	}

	public void setSerialno1(String serialno1) {
		this.serialno1 = serialno1;
	}

	public String getBatch1() {
		return batch1;
	}

	public void setBatch1(String batch1) {
		this.batch1 = batch1;
	}

	private ArrayList<String> val;
	private ArrayList<String> tro = new ArrayList<String>();

	public ArrayList<String> getVal() {
		return val;
	}

	public ArrayList<String> getTro() {
		return tro;
	}

	public void setTro(ArrayList<String> tro) {
		this.tro = tro;
	}

	public void setVal(ArrayList<String> val) {
		this.val = val;
	}

	public ArrayList<StockInFormMB> getRoll() {
		return roll;
	}

	public void setRoll(ArrayList<StockInFormMB> roll) {
		this.roll = roll;
	}

	public String getProduct_name1() {
		return product_name1;
	}

	public void setProduct_name1(String product_name1) {
		this.product_name1 = product_name1;
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

	String batch;

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
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

	public String getFlag3() {
		return flag3;
	}

	public void setFlag3(String flag3) {
		this.flag3 = flag3;
	}

	DemoController controller = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();

	public String validate;

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	List<String> stockDrop1;

	public List<String> getStockDrop1() {
		return stockDrop1;
	}

	public void setStockDrop1(List<String> stockDrop1) {
		this.stockDrop1 = stockDrop1;
	}

	public String stockin1() {
		setValidate(null);
		setDeliveredDate(null);
		setDelayreason(null);
		return "";
	}

	public String stockInFormdirect() {
		setValidate1("");
		setValidate(null);
		setS(null);
		setOrderNumber(null);
		setOrderDate(null);
		setFirmName(null);
		setVendor_phone_number(null);
		setProduct_name(null);
		setProduct_ID(0);
		setQuantity(null);
		setTargentDate(null);
		setBatch(null);
		setDeliveredDate(null);
		setDelayreason(null);
		homeMBs.clear();
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.addStock1(purchaseOrder);
			setStockDrop1(purchaseOrder.getStockDrop1());
		} catch (DemoException e) {
			setValidate("");
			logger.debug(e.getMessage());
			return "";
		} catch (Exception ie) {
			logger.debug(ie.getStackTrace());
			return "";
		}
		/* return "stockInFormdirect"; */
		return "";
	}

	StockInFormMB homeMB;

	ArrayList<String> productQuantitz = null;

	public ArrayList<String> getProductQuantitz() {
		return productQuantitz;
	}

	public void setProductQuantitz(ArrayList<String> productQuantitz) {
		this.productQuantitz = productQuantitz;
	}

	public String stockInForm() {
		try {
			logger.info("[stockInForm()]------------------Inside StockInForm() method()-------------------");

			validate = null;
			purchaseOrder.setOrderNumber(purchaseViewMB.orderNumber);
			purchaseOrder.setOrderDate(purchaseViewMB.orderDate);
			//
			homeMBs.clear();

			logger.info("[stockInForm()]--------------------middle StockInForm() method()-----------------");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			productList = (ArrayList<String>) controller.changeList(s,
					productList, purchaseOrder);

			if (productList.size() == 0) {

				throw new DemoException("");
			}
			// setProductList(productList);
			for (int i = 1; i <= productList.size(); i++) {
				
				homeMB = new StockInFormMB();
				homeMB.setSerialno("" + i);
				homeMB.setProductQuantity("");
				homeMB.setProduct_name("");
				homeMB.setFlag("1");
				homeMB.setFlag1("1");
				homeMB.setFlag2("none");
				homeMB.setFlag3("none");
				homeMBs.add(homeMB);
			}
			//

			controller.stockInForm(purchaseOrder);
			orderNumber = purchaseOrder.getOrderNumber();
			orderDate = purchaseOrder.getOrderDate();
			firmName = purchaseOrder.getFirmName();
			vendor_phone_number = purchaseOrder.getVendor_phone_number();
			product_ID = purchaseOrder.getProduct_ID();
			targentDate = purchaseOrder.getTargentDate();
			quantity = purchaseOrder.getQuantity();
			batch = purchaseOrder.getBatchNumber();
			product_name = purchaseOrder.getProduct_name();
			deliveredDate = purchaseOrder.getDeliveredDate();
			sellingPrice = "" + purchaseOrder.getSellingPrice();

			logger.info("[stockInForm()]--------------Outside StockInForm() method()---------------------");
			return "";
		} catch (DemoException ie) {
		logger.error("Inside Exception", ie);
			setValidate(ie.getMessage());
			logger.debug(ie.getMessage());
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";

		} finally {

		}

	}

	public String view() {
		logger.info("[stockInForm()]------------------Inside StockInForm() method()-------------------");
		try {
			validate = null;
			if (s.equals("")) {
				throw new NullPointerException("*Enter the Order Number");

			}

			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist = controller.purchaseOrdercancel(orderNumber,
					purchaselist, purchaseOrder);
			if (purchaselist == null) {
				throw new DemoException(
						"* its null pointer exception because of null purchaselist");
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

			return "view";

		} catch (NullPointerException n) {
			setValidate(n.getMessage());
		} catch (DemoException ie) {
			logger.error("Inside Exception", ie);
			setValidate(ie.getMessage());
			logger.debug(ie.getMessage());
			logger.debug("ss" + purchaseOrder.getPurchaselist().size());
			setPurchaselist(purchaseOrder.getPurchaselist());

			logger.debug("inside the exception");

			return "";
		}

		finally {

		}
		return "";
	}

	public String stockInForm1() {
		try {
			logger.info("[stockInForm1()]----------------------Inside StockInForm() method()---------------");

			validate = null;

			logger.debug("order number::::" + s);

			purchaseOrder.setOrderNumber(s);
			purchaseOrder.setOrderDate(orderDate);

			logger.info("[stockInForm()]-------------------middle StockInForm() method()------------------");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.stockInForm(purchaseOrder);

			return "";
		} catch (DemoException ie) {
			setValidate(ie.getMessage());
			logger.debug(ie.getMessage());
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";

		} finally {

		}

	}

	public String addStock() {
		try {
			String status="Fail";
			validate = null;
			logger.info("[addStock()]--------------------Inside add stock() method()----------------");
			purchaseOrder.setDelayreason(delayreason);
			purchaseOrder.setDeliveredDate(deliveredDate);
			purchaseOrder.setBatch(batch);
			if (deliveredDate == null) {
				throw new DemoException("*Enter Delivery Date");

			}
			logger.debug("================purchase order===================="
					+ purchaseOrder.getDelayreason());
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			status=controller.addStock(purchaseOrder);
			System.out.println("status in mb"+status);
			if(status.equalsIgnoreCase("Exist")){
				RequestContext.getCurrentInstance().execute("PF('alreadyStockinDlg').show();");
			}else{
				RequestContext.getCurrentInstance().execute("PF('stockin').show();");
			}
			
			return "";
		} catch (DemoException e) {
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "";
		} catch (Exception ie) {
			logger.debug(ie.getStackTrace());
			return "";
		}
	}

	public String stockDrop() {
		try {

			logger.info("[stockDrop()]---------------------Inside stockDrop() method()-----------------");

			purchaseOrder.setDelayreason(delayreason);
			purchaseOrder.setDeliveredDate(deliveredDate);
			purchaseOrder.setBatch(batch);

			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.addStock1(purchaseOrder);
			setStockDrop1(purchaseOrder.getStockDrop1());

			return "success2";
		} catch (DemoException e) {
			setValidate("");
			logger.debug(e.getMessage());
			return "";
		} catch (Exception ie) {
			logger.debug(ie.getStackTrace());
			return "";
		}
	}

	public String reset() {
		logger.info("[reset()]------------------In reset() method()---------------------");
		homeMBs.clear();
		setValidate(null);
		setS(null);
		setOrderNumber(null);
		setOrderDate(null);
		setFirmName(null);
		setVendor_phone_number(null);
		setTargentDate(null);
		setDelayreason(null);
		setDeliveredDate(null);
		return "";
	}

	
	public void removeListName(ValueChangeEvent productListName) {
		logger.info("[removeListName()]------------------In removeListName() method()---------------------");
		try {

			setValidate1("");
			String serialNo = "";
			String ListName = productListName.getNewValue().toString();
			serialNo = productListName.getComponent().getAttributes()
					.get("serial").toString();
			purchaseOrder.setBatchProductName(ListName);

			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");

			controller.getProductQuntity(purchaseOrder);

			BigDecimal remaing = new BigDecimal(purchaseOrder.getRemaining());
			remaing = remaing.setScale(2, RoundingMode.CEILING);

			homeMB = new StockInFormMB();
			homeMB.setSerialno(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getSerialno());
			homeMB.setProduct_name("" + productListName.getNewValue());
			homeMB.setProduct_name1("" + productListName.getNewValue());
			
			homeMB.setUnit(purchaseOrder.getUnit());
			homeMB.setFlag("none");
			homeMB.setFlag1("1");
			homeMB.setFlag2("none");
			homeMB.setFlag3("none");
			homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);

			setProductQuantitz(purchaseOrder.getProductQuantity());
			for (int i = 0; i < productQuantitz.size(); i++) {
			}
			String quantities = "";
			int sid = Integer.parseInt(serialNo);
			int count = 0;
			for (int j = 0; j < homeMBs.size(); j++) {
				if (ListName.equalsIgnoreCase(homeMBs.get(j).getProduct_name())) {
					count++;
					quantities = productQuantitz.get(count - 1);

				}

			}
			homeMB.setSerialno(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getSerialno());
			homeMB.setProduct_name("" + productListName.getNewValue());
			homeMB.setProduct_name1("" + productListName.getNewValue());
			/* homeMB.setProductQuantity(remaing.toString()); */
			homeMB.setProductQuantity(quantities);

			homeMB.setUnit(purchaseOrder.getUnit());
			homeMB.setFlag("none");
			homeMB.setFlag1("1");
			homeMB.setFlag2("none");
			homeMB.setFlag3("none");
			homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);

			if (ListName.contains("Select") || ListName.equalsIgnoreCase("")
					|| ListName.equalsIgnoreCase("Select")) {
			} else {

				productList.remove(ListName);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public ArrayList<String> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<String> productList) {
		this.productList = productList;
	}

	public ArrayList<StockInFormMB> getHomeMBs() {
		return homeMBs;
	}

	public void setHomeMBs(ArrayList<StockInFormMB> homeMBs) {
		this.homeMBs = homeMBs;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public void quantityChange(ValueChangeEvent quantity) {
		logger.info("[quantityChange()]------------------In quantityChange() method()---------------------");
		String serialNo = quantity.getComponent().getAttributes().get("serial")
				.toString();

		String name = quantity.getComponent().getAttributes().get("produc")
				.toString();

		try {
			validate = null;
			String quantity1 = (String) quantity.getNewValue();
			int qunt = Integer.parseInt(quantity1);
			if (name == null || name.equalsIgnoreCase("")
					|| name.contains("Select")
					|| name.equalsIgnoreCase("Select")) {
				throw new DemoException("*Enter the Product Name");
			} else if (!quantity1.matches("[0-9]+")) {
				throw new DemoException("Quantity must be a number");
			}

			roll.clear();
			roll1 = new ArrayList<StockInFormMB>();
			for (int i = 1; i <= qunt; i++) {

				StockInFormMB stMb = new StockInFormMB();
				stMb.setSerialno1("" + i);
				stMb.setBatch1("");
				stMb.setFlag1("");
				roll1.add(stMb);
			}
			homeMB = new StockInFormMB();
			homeMB.setProduct_name("" + name);
			homeMB.setProduct_name1("" + name);
			homeMB.setProductQuantity(homeMBs.get(
					Integer.parseInt(serialNo) - 1).getProductQuantity());
			homeMB.setUnit(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getUnit());
			homeMB.setSerialno(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getSerialno());
			homeMB.setFlag("none");
			homeMB.setFlag3("1");
			homeMB.setFlag2("none");
			homeMB.setRoll(roll1);
			homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);

		} catch (NumberFormatException number) {
			logger.error("Inside Exception", number);
		} catch (NullPointerException nullexception) {
			nullexception.printStackTrace();
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			homeMB = new StockInFormMB();
			homeMB.setProduct_name("");
			homeMB.setProduct_name1("");
			homeMB.setProductQuantity("");
			homeMB.setUnit("");

			homeMB.setSerialno(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getSerialno());
			homeMB.setFlag("1");
			homeMB.setFlag3("none");
			homeMB.setFlag2("none");
			homeMB.setFlag1("1");
			homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}


	public void getList(ValueChangeEvent event) {
		logger.info("[getList()]------------------In getList() method()---------------------");
		try {

			String serialNo = event.getComponent().getAttributes()
					.get("serial2").toString();
			String tempQuantity = event.getComponent().getAttributes()
					.get("productQuantity").toString();
			String quantity = event.getComponent().getAttributes()
					.get("quantity2").toString();
			String name = event.getComponent().getAttributes().get("producty")
					.toString();
			String serial = event.getComponent().getAttributes().get("serial3")
					.toString();
			String batchtemp = event.getNewValue().toString();
			/* BigDecimal product1= new BigDecimal(purchaseOrder.getQuantity()); */
			BigDecimal product1 = new BigDecimal(tempQuantity);
			product1 = product1.setScale(2, RoundingMode.CEILING);
			BigDecimal product2 = new BigDecimal(batchtemp);
			product2 = product2.setScale(2, RoundingMode.CEILING);
			int res = product1.compareTo(product2);
			validate = null;
			if (name == null || name.equalsIgnoreCase("")
					|| name.contains("Select")
					|| name.equalsIgnoreCase("Select")) {
				throw new DemoException("*Enter the Product Name");
			} else if (!quantity.matches("[0-9]+")) {
				throw new DemoException("Quantity must be a number");
			}
			else if (product1.compareTo(product2) == -1) {
				throw new DemoException("Out of Stock" + " Available only "
						+ product1 + " Quantity");
			}
			
			int qutnt = Integer.parseInt(quantity);
			String ListName = event.getNewValue().toString();

			ArrayList<StockInFormMB> roll1 = null;
			roll1 = homeMBs.get(Integer.parseInt(serialNo) - 1).getRoll();
			int j = homeMBs.get(Integer.parseInt(serialNo) - 1).getRoll()
					.size();
			roll1 = new ArrayList<StockInFormMB>();
			homeMB = new StockInFormMB();
			/* float ff= (float) 0.0 + Float.parseFloat(batchtemp); */
			BigDecimal bigProduct = new BigDecimal("0").add(product2);
			bigProduct = bigProduct.setScale(2, RoundingMode.CEILING);

			for (int i = 1; i <= homeMBs.get(Integer.parseInt(serialNo) - 1).roll
					.size(); i++) {

				try {
					
					bigProduct = bigProduct.add(new BigDecimal(homeMBs
							.get(Integer.parseInt(serialNo) - 1).getRoll()
							.get(i - 1).getBatch1()));
					bigProduct = bigProduct.setScale(2, RoundingMode.CEILING);
				} catch (Exception e) {
					bigProduct = bigProduct.add(new BigDecimal("0"));
					bigProduct = bigProduct.setScale(2, RoundingMode.CEILING);
				}

				if (serial.equals("" + i)) {

					StockInFormMB st = new StockInFormMB();

					BigDecimal tempqty = new BigDecimal(tempQuantity);
					tempqty = tempqty.setScale(2, RoundingMode.CEILING);
					int res1 = tempqty.compareTo(bigProduct);
					/* if(Float.parseFloat(tempQuantity)<ff) */
					if (res1 == -1) {
						st.setBatch1("");
						st.setFlag1("1");
					} else {
						st.setBatch1(product2.toString());
						st.setFlag1("none");
					}

					st.setSerialno1(serial);

					roll1.add(st);

				} else {
					StockInFormMB st = new StockInFormMB();
					try {
						
						st.setBatch1(homeMBs
								.get(Integer.parseInt(serialNo) - 1).getRoll()
								.get(i - 1).getBatch1());
						st.setSerialno1(homeMBs
								.get(Integer.parseInt(serialNo) - 1).getRoll()
								.get(i - 1).getSerialno1());
						st.setFlag1(homeMBs.get(Integer.parseInt(serialNo) - 1)
								.getRoll().get(i - 1).getFlag1());
					} catch (Exception e) {

						logger.error("Inside Exception", e);
						st.setBatch1("");
						st.setSerialno1("" + (i));
					}

					roll1.add(st);
				}

			}

			homeMB.setProduct_name(name);
			homeMB.setProduct_name1(name);
			homeMB.setUnit(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getUnit());

			homeMB.setProductQuantity(homeMBs.get(
					Integer.parseInt(serialNo) - 1).getProductQuantity());
			homeMB.setSerialno(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getSerialno());
			homeMB.setFlag("none");
			homeMB.setFlag2("1");
			homeMB.setFlag1("none");
			homeMB.setRoll(roll1);
			homeMB.setQuantity(quantity);
			homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
			logger.debug("list final size--------------->" + homeMBs.size());

			
			BigDecimal tempqty = new BigDecimal(tempQuantity);
			tempqty = tempqty.setScale(2, RoundingMode.CEILING);
			int res1 = tempqty.compareTo(bigProduct);
			if (res1 == -1) {
				throw new DemoException("Out of Stock");
			}


		} catch (DemoException in) {
			setValidate1(in.getMessage());
			logger.debug(in.getMessage());
		} catch (NumberFormatException number) {
		} catch (NullPointerException nullexception) {
		} catch (Exception e) {
			logger.debug("----------eeeeee------------");
			logger.error("Inside Exception", e);
		}
	}

	public String edit() {
		logger.info("[edit()]------------------In edit() method()---------------------");
		try {
			setValidate("");
			setValidate1("");
			homeMB = new StockInFormMB();
			homeMB.setProduct_name(homeMBs.get(Integer.parseInt(serialno) - 1)
					.getProduct_name());
			homeMB.setProduct_name1("");
			homeMB.setSerialno(homeMBs.get(Integer.parseInt(serialno) - 1)
					.getSerialno());
			homeMB.setFlag("none");
			homeMB.setFlag2("1");
			homeMB.setFlag1("none");
			homeMB.setProductQuantity(homeMBs.get(
					Integer.parseInt(serialno) - 1).getProductQuantity());
			homeMB.setUnit(homeMBs.get(Integer.parseInt(serialno) - 1)
					.getUnit());

			homeMB.setFlag("1");
			homeMB.setFlag1("1");
			homeMB.setFlag2("none");
			homeMB.setFlag3("none");
			homeMB.setFlag4("none");
			homeMB.setPrflag("none");
			roll1 = new ArrayList<StockInFormMB>();

			if ((!homeMBs.get(Integer.parseInt(serialno) - 1).getProduct_name()
					.equalsIgnoreCase(""))
					|| (!homeMBs.get(Integer.parseInt(serialno) - 1)
							.getProduct_name().equals(""))) {
				productList.add(homeMBs.get(Integer.parseInt(serialno) - 1)
						.getProduct_name());
			}

			for (int i = 1; i <= homeMBs.get(Integer.parseInt(serialno) - 1).roll
					.size(); i++) {

				StockInFormMB st = new StockInFormMB();
				st.setBatch1(homeMBs.get(Integer.parseInt(serialno) - 1)
						.getRoll().get(i - 1).getBatch1());
				st.setFlag1("1");
				st.setFlag4("none");
				st.setSerialno1(homeMBs.get(Integer.parseInt(serialno) - 1)
						.getRoll().get(i - 1).getSerialno());
				homeMBs.get(Integer.parseInt(serialno) - 1).getRoll()
						.set(i - 1, st);
			}

			homeMB.setRoll(homeMBs.get(Integer.parseInt(serialno) - 1).roll);
			homeMB.setQuantity(homeMBs.get(Integer.parseInt(serialno) - 1)
					.getQuantity());
			homeMB.setBatch1("");
			homeMBs.set(Integer.parseInt(serialno) - 1, homeMB);

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";
	}

	public String validate1;

	public String getValidate1() {
		return validate1;
	}

	public void setValidate1(String validate1) {
		this.validate1 = validate1;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPrflag() {
		return prflag;
	}

	public void setPrflag(String prflag) {
		this.prflag = prflag;
	}

	// prema begin 29/04/2016 dialog box creation for stock in
	public void stockin() {
		logger.info("[stockin()]------------------In stockin() method()---------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("stockInForm", options,
				null);
		stockInFormdirect();
	}

	// prema end 29/0/2016
	public void stockinclose() {
		logger.info("[stockinclose()]------------------In stockinclose() method()---------------------");
		RequestContext.getCurrentInstance().closeDialog("stockInForm");
	}
}
