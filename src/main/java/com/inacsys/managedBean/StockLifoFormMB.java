package com.inacsys.managedBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.ProductRegister;
import com.inacsys.domain.StockView;
import com.inacsys.exception.DemoException;
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

@ManagedBean(name = "stockLifoFormMB")
public class StockLifoFormMB {
	private static Logger logger = Logger.getLogger(StockLifoFormMB.class);
	List<I0018> batch;
	List<I0019> batch2;
	List<I0019> industryList;

	List<I0025> ven = null;
	DemoController controller = null;
	ProductRegister productRegister = new ProductRegister();

	public String batch1;
	String s;
	String s1 = "Number of Sold Product In the ";
	String batch3;
	public int value = 0;
	public String quantity;
	public String overallPrice;
	List<StockView> view = null;

	public List<StockView> getView() {
		return view;
	}

	public void setView(List<StockView> view) {
		this.view = view;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getOverallPrice() {
		return overallPrice;
	}

	public void setOverallPrice(String overallPrice) {
		this.overallPrice = overallPrice;
	}

	public String getBatch3() {
		return batch3;
	}

	public void setBatch3(String batch3) {
		this.batch3 = batch3;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
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

	List<String> productName = null;
	List<String> batchName = null;
	List<Date> date1 = null;
	List<String> price = null;

	public List<String> getProductName() {
		return productName;
	}

	public void setProductName(List<String> productName) {
		this.productName = productName;
	}

	public List<String> getBatchName() {
		return batchName;
	}

	public void setBatchName(List<String> batchName) {
		this.batchName = batchName;
	}

	public List<Date> getDate1() {
		return date1;
	}

	public void setDate1(List<Date> date1) {
		this.date1 = date1;
	}

	public List<String> getPrice() {
		return price;
	}

	public void setPrice(List<String> price) {
		this.price = price;
	}

	public String dropDown() {

		try {

			s = null;
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setBatch(controller.salesDrop(batch));

			return "";

		} catch (Exception e) {
			return "";
		}
	}

	public String stockLifoForm() {
		logger.info("[stockLifoForm()]----------------- Inside stockLifoForm() method()----------------------- ");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setView(controller.getStockInfo());
			Collections.reverse(view);
			quantity = "" + view.size();
			return "";

		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			return "";
		} finally {

		}
	}

	public String lifoPriceBack() {
		logger.info("[lifoPriceBack()]----------------- Inside lifoPriceBack() method()----------------------- ");
		try {
			return "stockLifoForm";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "stocklifoPrice";
		} finally {

		}
	}

	public String lifoClickme() {
		logger.info("[lifoClickme()]----------------- Inside lifoClickme() method()----------------------- ");
		float price = 0;
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setView(controller.getStockInfo());
			Collections.reverse(view);
			if (view.size() > 0) {
				for (StockView b : view) {
					price = price + Float.parseFloat(b.getUnitprice());
				}
				overallPrice = "" + price;
			}
			return "stocklifoPrice";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "stockLifoForm";
		} finally {

		}
	}

}
