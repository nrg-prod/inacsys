package com.inacsys.managedBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.hibernate.sql.Select;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.ProductRegister;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.domain.StockView;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0002;
import com.inacsys.shared.I0006;
import com.inacsys.shared.I0018;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0025;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "stockDamageMB")
public class StockDamageMB {
	private static Logger logger = Logger.getLogger(StockDamageMB.class);
	@ManagedProperty(value = "#{stockViewMB}")
	StockViewMB stockViewMB;

	public StockViewMB getStockViewMB() {
		return stockViewMB;
	}

	public void setStockViewMB(StockViewMB stockViewMB) {
		this.stockViewMB = stockViewMB;
	}

	List<I0018> batch;
	List<I0019> batch2;
	List<I0019> industryList;

	List<I0025> ven = null;
	DemoController controller = null;
	ProductRegister productRegister = new ProductRegister();

	public String batch1;
	public String quantity;
	String s;
	public BigDecimal totalQuantity;

	public String productname;
	public String unitprice;
	public String flag = "none";
	List<StockDamageMB> stockdamageList = new ArrayList<StockDamageMB>();
	private ArrayList<String> rollList;

	public BigDecimal getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(BigDecimal totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String unit;

	public ArrayList<String> getRollList() {
		return rollList;
	}

	public void setRollList(ArrayList<String> rollList) {
		this.rollList = rollList;
	}

	private String rollID;

	public List<StockDamageMB> getStockdamageList() {
		return stockdamageList;
	}

	public void setStockdamageList(List<StockDamageMB> stockdamageList) {
		this.stockdamageList = stockdamageList;
	}

	List<String> products = null;

	public List<String> getProducts() {
		return products;
	}

	public void setProducts(List<String> products) {
		this.products = products;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public List<I0019> getBatch2() {
		return batch2;
	}

	public void setBatch2(List<I0019> batch2) {
		this.batch2 = batch2;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getBatch1() {
		return batch1;
	}

	public void setBatch1(String batch1) {
		this.batch1 = batch1;
	}

	public List<I0018> getBatch() {
		return batch;
	}

	public void setBatch(List<I0018> batch) {
		this.batch = batch;
	}

	public List<I0019> getIndustryList() {
		return industryList;
	}

	public void setIndustryList(List<I0019> industryList) {
		this.industryList = industryList;
	}

	public List<I0025> getVen() {
		return ven;
	}

	public void setVen(List<I0025> ven) {
		this.ven = ven;
	}

	public DemoController getController() {
		return controller;
	}

	public void setController(DemoController controller) {
		this.controller = controller;
	}

	public ProductRegister getProductRegister() {
		return productRegister;
	}

	public void setProductRegister(ProductRegister productRegister) {
		this.productRegister = productRegister;
	}

	public String validate;
	public String validate1;

	public String getValidate1() {
		return validate1;
	}

	public void setValidate1(String validate1) {
		this.validate1 = validate1;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String dropDown() {
		logger.info("[inside dropDown()]-------------------inside dropDown() method()---------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setBatch(controller.salesDrop(batch));
			return "";

		} catch (Exception e) {
			return "";
		}
	}

	public String stockDamagedirect() {
		logger.info("[inside stockdamagedirect()]-------------------inside stockDamagedirect() method()---------------");
		setRollID("");
		setBatch1("");
		setQuantity("");
		setS("");
		setValidate(null);
		setValidate1(null);
		setFlag("none");
		/* return "stockDamagedirect"; */
		return "";
	}

	/* * john */

	public void damagedadd(final SelectEvent event) {
		logger.info("[inside damagedadd()]-------------------inside damagedadd() method()---------------");
		validate = "";
		setQuantity("");
		try {
			batch1 = ((StockViewMB) event.getObject()).getpName();
			totalQuantity = ((StockViewMB) event.getObject()).getQ1();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public String dropDownbatch() {
		logger.info("[inside dropDownbatch()]-------------------inside dropDownbatch() method()---------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setProducts(controller.productVendor(products));
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		}

	}

	StockView stockView = new StockView();
	PurchaseOrder purchaseOrder = new PurchaseOrder();

	public String stockDamage() {
		logger.info("[inside stockDamage()]-------------------inside stockDamage() method()---------------");
		try {
			flag = "none";
			validate1 = null;
			validate = null;
			setS("");
			industryList = null;
			if (quantity.equalsIgnoreCase("")) {
				throw new DemoException("*Enter the Quantity");
			}
			if (!quantity.matches("[-+]?[0-9]*\\.?[0-9]+")) {
				throw new DemoException("*Quantity Should Be Number Formate");
			}

			purchaseOrder.setQuantity(quantity);
			purchaseOrder.setBatch(batch1);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.addDamage1(purchaseOrder);
			flag = "1";
			setProductname(purchaseOrder.product_name);
			setBatch1(purchaseOrder.getBatch());
			setUnitprice(purchaseOrder.sellingPrice);
			setQuantity(purchaseOrder.quantity);
			setQuantity(null);
			// setBatch(controller.salesDrop(batch));
			return "";

		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			flag = "none";
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "failure";
		}
	}

	public String stockDamage1() {
		logger.info("[inside stockDamage1()]-------------------inside stockDamage1() method()---------------");
		try {
			validate = "";
			if (quantity.equalsIgnoreCase("")) {
				throw new DemoException("*Enter the Quantity");
			}
			
			BigDecimal tempqun = new BigDecimal(quantity.replaceAll(",", ""));

			if (tempqun.compareTo(new BigDecimal("0.00")) == 0) {
				throw new DemoException("*Quantity should be greater then '0' value");
			}
			// String s=batch1;
			if (totalQuantity.compareTo(tempqun) < 0) {
				throw new DemoException("Damaged Quantity Should Be equal or less then the product quantity ");
			}
			flag = "none";
			// String s=batch1;
			purchaseOrder.setQuantity(quantity);
			purchaseOrder.setProduct_name(productname);
			purchaseOrder.setSellingPrice(unitprice);
			purchaseOrder.setBatch(batch1);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.addDamage(purchaseOrder);
			
			RequestContext.getCurrentInstance().execute(
					"PF('addDamageDialog').hide();");
			RequestContext.getCurrentInstance()
					.execute("PF('confirm').show();");
			quantity = null;
			validate = null;
			return "";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			logger.debug("++++++++++++++++++++++" + e.getMessage());
			quantity = "";
			return "";
		}
	}

	public String s1;
	public String s2;
	public String productName;
	public String unitPrice;
	public String batchName;
	public Date stockinDate;

	public StockView getStockView() {
		return stockView;
	}

	public void setStockView(StockView stockView) {
		this.stockView = stockView;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public String getS2() {
		return s2;
	}

	public void setS2(String s2) {
		this.s2 = s2;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public Date getStockinDate() {
		return stockinDate;
	}

	public void setStockinDate(Date stockinDate) {
		this.stockinDate = stockinDate;
	}

	public String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String stockdamageView() {
		logger.info("[inside stockdamageView()]-------------------inside stockdamageView() method()---------------");
		try {
			flag = "none";
			validate1 = null;
			validate = null;
			setQuantity("");
			setBatch1("");
			industryList = null;
			stockdamageList.clear();
			// String s=batch1;
			if (s.equalsIgnoreCase("")) {
				throw new DemoException("*Enter the Product name");
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setIndustryList(controller.stockdamageView(batch2, s, stockView));
			if (industryList.size() == 0) {
				throw new DemoException("*No value found !");
			}
			if (industryList.size() > 0) {
				StockDamageMB sdmb = new StockDamageMB();
				sdmb.setQuantity(stockView.getDamageQ());
				sdmb.setS1(stockView.getS1());
				sdmb.setS2(stockView.getS2());
				sdmb.setProductName(industryList.get(0).getI0018()
						.getProductName());
				sdmb.setUnitPrice(""
						+ industryList.get(0).getI0018().getUnitPrice());
				sdmb.setBatchName(industryList.get(0).getI0018().getBatchName());
				sdmb.setStatus(industryList.get(0).getI0018().getI0017()
						.getI0015().getStatus());
				sdmb.setStockinDate(industryList.get(0).getI0018().getI0017()
						.getStockInDate());
				flag = "1";
				stockdamageList.add(sdmb);
			}

			return "";

		} catch (DemoException e) {
			logger.error("Inside Exception"+e.getMessage());
			flag = "none";
			setValidate1(e.getMessage());
			logger.debug(e.getMessage());
			return "";
		}
	}

	public String getRollID() {
		return rollID;
	}

	public void setRollID(String rollID) {
		this.rollID = rollID;
	}

	public void valueChange(ValueChangeEvent v) {
		logger.info("[inside valueChange()]-------------------inside valueChange() method()---------------");

		try {
			productName = "" + v.getNewValue();
			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			purchaseOrder.setProductName(productName);
			rollList = (ArrayList<String>) controller.getAddRollList(
					productName, rollList);

			if (rollList.size() == 0) {

				throw new DemoException(
						"No Roll there please select another Product");
			}

		} catch (Exception e) {
			logger.error("inside exception",e);
		}
	}

	// prema begin 29/04/2016 dialog box creation for purchase order
	public void stockdamage() {
		logger.info("[inside stockdamage()]-------------------inside stockdamage() method()---------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("stockDamage", options,
				null);
		stockDamagedirect();
	}

	// prema end 29/0/2016
	public void stockdamageclose() {
		logger.info("[inside stockdamageclose()]-------------------inside stockdamageclose() method()---------------");
		RequestContext.getCurrentInstance().closeDialog("stockDamage");
	}
}
