package com.inacsys.managedBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
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

@ManagedBean(name = "stockOutFormMB")
public class StockOutFormMB {
	private static Logger logger = Logger.getLogger(StockOutFormMB.class);
	List<I0018> batch;
	List<I0019> batch2;
	List<I0019> industryList;
	List<I0025> ven = null;
	DemoController controller = null;
	ProductRegister productRegister = new ProductRegister();
	public String flag = "none";
	List<StockOutFormMB> sOut = new ArrayList<StockOutFormMB>();
	List<StockOutFormMB> sOut1 = new ArrayList<StockOutFormMB>();
	public List<StockView> domain1 = new ArrayList<StockView>();
	public List<StockView> domain2 = new ArrayList<StockView>();
	private String rollID;
	private String unit;
	private String date;
	public String finAmt;
	public String finRoll;
	public String finQuan;
	public String finPric;

	public String getFinAmt() {
		return finAmt;
	}

	public void setFinAmt(String finAmt) {
		this.finAmt = finAmt;
	}

	public String getFinRoll() {
		return finRoll;
	}

	public void setFinRoll(String finRoll) {
		this.finRoll = finRoll;
	}

	public String getFinQuan() {
		return finQuan;
	}

	public void setFinQuan(String finQuan) {
		this.finQuan = finQuan;
	}

	public String getFinPric() {
		return finPric;
	}

	public void setFinPric(String finPric) {
		this.finPric = finPric;
	}

	public String getRollID() {
		return rollID;
	}

	public void setRollID(String rollID) {
		this.rollID = rollID;
	}

	public List<StockView> getDomain1() {
		return domain1;
	}

	public void setDomain1(List<StockView> domain1) {
		this.domain1 = domain1;
	}

	public List<StockView> getDomain2() {
		return domain2;
	}

	public void setDomain2(List<StockView> domain2) {
		this.domain2 = domain2;
	}

	public List<StockOutFormMB> getsOut1() {
		return sOut1;
	}

	public void setsOut1(List<StockOutFormMB> sOut1) {
		this.sOut1 = sOut1;
	}

	public List<StockOutFormMB> getsOut() {
		return sOut;
	}

	public void setsOut(List<StockOutFormMB> sOut) {
		this.sOut = sOut;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String batch1;
	String s;
	String s1 = "Number of Sold Product In the ";
	String batch3;
	public int value = 0;
	private String qunatity;

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

	public String validate;

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;

	}

	public String frimname;
	public String firmRegistration;
	public String productName;
	public String unitPrice;
	public String BatchName;
	public Date stockinDate;
	public String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return BatchName;
	}

	public void setBatchName(String batchName) {
		BatchName = batchName;
	}

	public Date getStockinDate() {
		return stockinDate;
	}

	public void setStockinDate(Date stockinDate) {
		this.stockinDate = stockinDate;
	}

	public String getFrimname() {
		return frimname;
	}

	public void setFrimname(String frimname) {
		this.frimname = frimname;
	}

	public String getFirmRegistration() {
		return firmRegistration;
	}

	public void setFirmRegistration(String firmRegistration) {
		this.firmRegistration = firmRegistration;
	}

	public StockView getStockView() {
		return stockView;
	}

	public void setStockView(StockView stockView) {
		this.stockView = stockView;
	}

	public String dropDown() {
		logger.info("[dropDown()]----------------- Inside dropDown() method()----------------------- ");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setBatch(controller.salesDrop(batch));
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		}
	}

	StockView stockView = new StockView();
	public String p;

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String stockoutDirect() {
		logger.info("[stockoutDirect()]----------------- Inside stockoutDirect() method()----------------------- ");
		setS("");
		setValidate(null);
		setFlag("none");
		/* return "stockoutDirect"; */
		return "";
	}

	public String stockoutForm() {
		logger.info("[stockoutForm()]----------------- Inside stockoutForm() method()----------------------- ");
		try {
			flag = "none";
			validate = null;
			if (s.equalsIgnoreCase("Select")) {
				throw new DemoException("*select anyone Product Name");
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setIndustryList(controller.stockoutForm(batch2, p, stockView));
			if (industryList.size() == 0) {
				throw new DemoException("*The batch name is not correct");
			}
			int count = 0;

			if (industryList.size() > 0) {
				batch3 = industryList.get(0).getI0018().getProductName();
				setFrimname(stockView.getS1());
				setFirmRegistration(stockView.getS2());
				setProductName(industryList.get(0).getI0018().getProductName());
				setUnitPrice(industryList.get(0).getI0018().getUnitPrice());
				setBatchName(industryList.get(0).getI0018().getBatchName());
				setStatus(industryList.get(0).getI0018().getI0017().getI0015()
						.getStatus());
				setStockinDate(industryList.get(0).getI0018().getI0017()
						.getStockInDate());

				flag = "1";
				for (I0019 b : industryList) {
					count++;
				}
			}
			value = count;
			// setBatch(controller.salesDrop(batch));
			return "";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			flag = "none";
			industryList = null;
			batch3 = null;
			value = 0;
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "";
		} finally {

		}
	}

	public String totalPrice;

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String stockoutForm1() {
		logger.info("[stockoutForm1()]----------------- Inside stockoutForm1() method()----------------------- ");
		try {
			sOut.clear();
			flag = "none";
			validate = null;
			// String s=batch1;
			if (s.equalsIgnoreCase("")) {
				throw new DemoException("Please Choose the Product Name");
			}
			
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setIndustryList(controller.stockoutForm(batch2, s, stockView));
			
			try {
				if (stockView.domain2.size() > 0) {
					setDomain1(stockView.getDomain2());
					BigDecimal totAmnt = BigDecimal.valueOf(0);
					BigDecimal totQuan = BigDecimal.valueOf(0);
					for (int i = 0; i < stockView.domain2.size(); i++) {
						totAmnt = totAmnt.add(new BigDecimal(stockView.domain2
								.get(i).getTotalPrice()));
						totQuan = totQuan.add(new BigDecimal(stockView.domain2
								.get(i).getSaleQuan()));
					}
					setFinRoll("" + stockView.domain2.size());
					setFinPric("" + totAmnt);
					setFinQuan("" + totQuan);
				}
			} catch (NullPointerException n) {
				throw new DemoException("This Product Is Not Yet Solded");
			}

			if (industryList.size() == 0) {
				throw new DemoException("This Product Is Not Yet Solded");
			}
			int count = 0;
			List<Integer> sIds = new ArrayList<Integer>();
			List<Integer> sIds1 = new ArrayList<Integer>();
			if (industryList.size() > 0) {
				for (int i = 0; i < industryList.size(); i++) {
					try {
						if (!(industryList.get(i).getI0021().equals(null) || industryList
								.get(i).getI0021().equals(""))) {
							sIds.add(industryList.get(i).getI0021()
									.getSales_ID());
						} else {

						}
					} catch (Exception e) {

					}
				}
				HashSet<Integer> set1 = new HashSet<Integer>();
				for (Integer item : sIds) {
					if (!set1.contains(item)) {
						sIds1.add(item);
						set1.add(item);
					}
				}
				int scnt = 0;
				int index = 0;
				for (int j = 0; j < sIds1.size(); j++) {
					for (int i = 0; i < industryList.size(); i++) {
						try {
							if (!(industryList.get(i).getI0021().equals(null) || industryList
									.get(i).getI0021().equals(""))) {
								if (industryList.get(i).getI0021()
										.getSales_ID() == sIds1.get(j)) {
									index = i;
									scnt++;
								}
							} else {

							}
						} catch (Exception e) {
							logger.error("Inside Exception", e);
						}
					}
					
					scnt = 0;
				}
				flag = "1";
				
			}
			
			return "";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			flag = "none";
			industryList = null;
			batch3 = null;
			value = 0;
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "";
		} finally {

		}
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getQunatity() {
		return qunatity;
	}

	public void setQunatity(String qunatity) {
		this.qunatity = qunatity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	// prema begin 29/04/2016 dialog box creation for stock out
	public void stockout() {
		logger.info("[stockout()]----------------- Inside stockout() method()----------------------- ");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("stockOutForm", options,
				null);
		stockoutDirect();
	}
	// prema end 29/0/2016

}
