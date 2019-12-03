package com.inacsys.managedBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.SortOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
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

@ManagedBean(name = "stockViewMB")
public class StockViewMB {
	private static Logger logger = Logger.getLogger(StockViewMB.class);
	public String productName;
	public String unitPrice;
	public String batchName;
	public String status;
	List<I0018> batch;
	List<I0019> batch2;
	List<I0018> batch3;
	List<I0019> industryList;
	public List<I0019> industryList1 = null;
	List<I0025> ven = null;
	public String flag = "none";
	DemoController controller = null;
	ProductRegister productRegister = new ProductRegister();
	public String validate;
	public String batch1;
	public String value;
	String s;
	public String pName;
	private String unit;
	private String stock_In;
	private String date;
	private String stock_Out;
	private String roll_ID;
	private ArrayList<String> rolls = null;

	public String getStock_In() {
		return stock_In;
	}

	public void setStock_In(String stock_In) {
		this.stock_In = stock_In;
	}

	public String getStock_Out() {
		return stock_Out;
	}

	public void setStock_Out(String stock_Out) {
		this.stock_Out = stock_Out;
	}

	public String getRoll_ID() {
		return roll_ID;
	}

	public void setRoll_ID(String roll_ID) {
		this.roll_ID = roll_ID;
	}

	List<StockViewMB> stockList = new ArrayList<StockViewMB>();
	List<StockViewMB> stockList1 = new ArrayList<StockViewMB>();

	public List<StockViewMB> getStockList1() {
		return stockList1;
	}

	public void setStockList1(List<StockViewMB> stockList1) {
		this.stockList1 = stockList1;
	}

	public List<StockViewMB> getStockList() {
		return stockList;
	}

	public void setStockList(List<StockViewMB> stockList) {
		this.stockList = stockList;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValue() {
		return value;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<I0019> getIndustryList1() {
		return industryList1;
	}

	public void setIndustryList1(List<I0019> industryList1) {
		this.industryList1 = industryList1;
	}

	public StockView getStockView() {
		return stockView;
	}

	public void setStockView(StockView stockView) {
		this.stockView = stockView;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<I0018> getBatch3()

	{
		return batch3;
	}

	public void setBatch3(List<I0018> batch3) {
		this.batch3 = batch3;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	String s1;
	String s2;

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

	public Date stockinDate;

	public Date getStockinDate() {
		return stockinDate;
	}

	public void setStockinDate(Date stockinDate) {
		this.stockinDate = stockinDate;
	}

	public String StockViewdirect() {
		setValidate(null);
		setFlag("none");
		setFlag1("none");
		setS("");
		setProductName("");
		setVendorname("");
		/*
		 * return "StockViewdirect";
		 */
		return "";
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
			logger.error("inside exception ",e);
			return "";
		}
	}

	List<String> batchProductName3;

	public List<String> getBatchProductName3() {
		return batchProductName3;
	}

	public void setBatchProductName3(List<String> batchProductName3) {
		this.batchProductName3 = batchProductName3;
	}

	public String productVendor() {
		logger.info("[productVendor()]----------------- Inside productVendor() method()----------------------- ");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setBatchProductName3(controller.productVendor(batchProductName3));
			Collections.sort(batchProductName3, String.CASE_INSENSITIVE_ORDER);
			setBatchProductName4(controller.productVendor1(batchProductName4));
			Collections.sort(batchProductName4, String.CASE_INSENSITIVE_ORDER);
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		}

	}

	StockView stockView = new StockView();
	List<String> batchname = null;
	public String buyingPrice;

	public String getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(String buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public List<String> getBatchname() {
		return batchname;
	}

	public void setBatchname(List<String> batchname) {
		this.batchname = batchname;
	}

	public void valueChange(ValueChangeEvent v) {
		logger.info("[valueChange()]----------------- Inside valueChange() method()----------------------- ");
		try {
			String s = (String) v.getNewValue();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setBatchname(controller.productVendor1(batchname, s));
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public String stockView2() {
		logger.info("[stockView2()]----------------- Inside stockView2() method()----------------------- ");
		try {
			flag = "none";
			validate = null;
			if (s.equalsIgnoreCase("")) {
				throw new DemoException("*Enter the Batch Name");
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setIndustryList(controller.stockView(batch2, s, stockView));
			if (industryList.size() == 0) {
				throw new DemoException("*The batch name is not correct");
			}
			flag = "1";
			setIndustryList(controller.stockView(batch2, s, stockView));
			if (industryList.size() > 0) {
				StockViewMB vmbMb = new StockViewMB();
				vmbMb.setS1(stockView.getS1());
				vmbMb.setS2(stockView.getS2());
				vmbMb.setpName(industryList.get(0).getI0018().getProductName());
				vmbMb.setUnitPrice(industryList.get(0).getI0018()
						.getUnitPrice());
				vmbMb.setBatchName(industryList.get(0).getI0018()
						.getBatchName());
				vmbMb.setStatus(industryList.get(0).getI0018().getI0017()
						.getI0015().getStatus());
				vmbMb.setStockinDate(industryList.get(0).getI0018().getI0017()
						.getStockInDate());
				vmbMb.setBuyingPrice(stockView.getBuyingPrice());
			}
			int i = 0;
			setValue("" + industryList.size());
			// setBatch(controller.salesDrop(batch));
			return "";

		} catch (DemoException e) {
			flag = "none";
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "";
		}
	}

	public String totall;

	public String getTotall() {
		return totall;
	}

	public void setTotall(String totall) {
		this.totall = totall;
	}

	public String net1 = "";

	public String getNet1() {
		return net1;
	}

	public void setNet1(String net1) {
		this.net1 = net1;
	}

	String totRollNos;
	String totRollQuans;

	public String getTotRollNos() {
		return totRollNos;
	}

	public void setTotRollNos(String totRollNos) {
		this.totRollNos = totRollNos;
	}

	public String getTotRollQuans() {
		return totRollQuans;
	}

	public void setTotRollQuans(String totRollQuans) {
		this.totRollQuans = totRollQuans;
	}

	BigDecimal q = BigDecimal.valueOf(0);
	BigDecimal q1 = BigDecimal.valueOf(0);
	BigDecimal q2 = BigDecimal.valueOf(0);

	public BigDecimal getQ1() {
		return q1;
	}

	public void setQ1(BigDecimal q1) {
		this.q1 = q1;
	}

	public BigDecimal getQ2() {
		return q2;
	}

	public void setQ2(BigDecimal q2) {
		this.q2 = q2;
	}

	public BigDecimal getQ() {
		return q;
	}

	public void setQ(BigDecimal q) {
		this.q = q;
	}
	
	public String serialno;
	

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String stockView3() {
		logger.info("[stockView3()]----------------- Inside stockView3() method()----------------------- ");
		BigDecimal ee = BigDecimal.valueOf(0);
		BigDecimal rolquan = BigDecimal.valueOf(0);
		stockList.clear();
		try {
			vendorname = "";
			// flag="none";
			validate = null;
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setIndustryList(controller.stockView2(batch2, stockView));
			if (industryList == null) {
				flag = "none";
				throw new DemoException(" No Stock In Warehouse");
			} else {
				try {
					Format formatter = new SimpleDateFormat("dd/MM/yyyy");
					if (industryList.size() > 0) {
						if (stockView.getSample().size() > 0) {

							for (int p = 0; p < stockView.getDomain1().size(); p++) {
								System.out.println("product "+stockView.getDomain1().get(p).getProductname()+
												" price  "+stockView.getDomain1().get(p).getBuyingPrice());
								BigDecimal b1 = new BigDecimal(stockView
										.getSample().get(p).getQuantity());
								b1 = b1.setScale(2, RoundingMode.CEILING);

								StockViewMB vmbMb = new StockViewMB();
								vmbMb.setSerialno(String.valueOf(p+1));  
								vmbMb.setS1(stockView.getDomain1().get(p)
										.getS1());
								
								vmbMb.setS2(stockView.getDomain1().get(p)
										.getS2());
								vmbMb.setQ2(new BigDecimal(stockView
										.getDomain1().get(p).getBuyingPrice()));
								
								vmbMb.setBuyingPrice(stockView.getDomain1()
										.get(p).getBuyingPrice());
								System.out.println("buying price============>"+stockView.getDomain1()
										.get(p).getBuyingPrice());
								
								vmbMb.setpName(stockView.getDomain1().get(p)
										.getProductname());
								vmbMb.setUnitPrice(stockView.getDomain1()
										.get(p).getUnitprice());
								
								vmbMb.setQ1(new BigDecimal(b1.toString()));
								
								vmbMb.setUnit(stockView.getDomain1().get(p)
										.getUnit());
								vmbMb.setQ(new BigDecimal(stockView
										.getDomain1().get(p).getBuyingPrice())
										.multiply(new BigDecimal(stockView
												.getSample().get(p)
												.getQuantity())));
								

								stockList.add(vmbMb);
								setTotal1(""
										+ new BigDecimal(stockView.getDomain1()
												.get(p).getBuyingPrice())
												.multiply(new BigDecimal(
														stockView.getSample()
																.get(p)
																.getQuantity())));

								logger.debug("total -- >> " + total1);
								ee = ee.add(new BigDecimal(total1));
								rolquan = rolquan.add(new BigDecimal(stockView
										.getSample().get(p).getQuantity()));
							}
							setTotRollNos("" + stockView.getSample().size());
							setNet1("" + ee);
							setTotRollQuans("" + rolquan);
							flag = "1";
							flag1 = "none";
						}
					}
				} catch (IllegalArgumentException p) {
					logger.error("inside exception",p);
				}
				
				int i = 0;

				setValue("" + industryList.size());
				// setBatch(controller.salesDrop(batch));
				return "";

			}
		} catch (DemoException e) {
			flag = "none";
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "";
		}

	}

	private String formatDateStr(String date2) {
		logger.info("[formatDateStr()]----------------- Inside formatDateStr() method()----------------------- ");
		String res = null;
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		Date date;
		try {
			date = format1.parse(date2);
			res = format2.format(date);
		} catch (ParseException e) {

			logger.error("inside exception",e);
		}
		return res;
	}

	public String stockView1() {
		logger.info("[stockView1()]----------------- Inside stockView1() method()----------------------- ");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setBatch3(controller.stockView1(batch3));
			if (batch3.size() > 0) {
				logger.debug("sucess full" + batch3);
			}

			return "";

		} catch (DemoException e) {

			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "";
		}
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public ArrayList<String> getRolls() {
		return rolls;
	}

	public void setRolls(ArrayList<String> rolls) {
		this.rolls = rolls;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String vendorname;
	List<String> batchProductName4;

	public List<String> getBatchProductName4() {
		return batchProductName4;
	}

	public void setBatchProductName4(List<String> batchProductName4) {
		this.batchProductName4 = batchProductName4;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public String flag1 = "none";

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public String total1 = "";

	public String getTotal1() {
		return total1;
	}

	public void setTotal1(String total1) {
		this.total1 = total1;
	}

	public String net11 = "";

	public String getNet11() {
		return net11;
	}

	public void setNet11(String net11) {
		this.net11 = net11;
	}

	BigDecimal a = BigDecimal.valueOf(0);
	BigDecimal a1 = BigDecimal.valueOf(0);
	BigDecimal a2 = BigDecimal.valueOf(0);

	public BigDecimal getA() {
		return a;
	}

	public void setA(BigDecimal a) {
		this.a = a;
	}

	public BigDecimal getA1() {
		return a1;
	}

	public void setA1(BigDecimal a1) {
		this.a1 = a1;
	}

	public BigDecimal getA2() {
		return a2;
	}

	public void setA2(BigDecimal a2) {
		this.a2 = a2;
	}

	public String stockView4() {
		logger.info("[stockView4()]----------------- Inside stockView4() method()----------------------- ");
		try {
			BigDecimal eee = BigDecimal.valueOf(0);
			BigDecimal rolquan = BigDecimal.valueOf(0);
			try {
				stockList1.clear();
				productName = "";
				validate = null;
				if (vendorname.equalsIgnoreCase("")) {
					throw new DemoException("Please Choose the Vendor Name");
				}
				ApplicationContext ctx = FacesContextUtils
						.getWebApplicationContext(FacesContext
								.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				setIndustryList(controller.stockView3(batch2, vendorname,
						stockView));

				try {
					Format formatter = new SimpleDateFormat("dd/MM/yyyy");
					if (industryList.size() == 0) {
						flag1 = "none";
						throw new DemoException(
								"*This Product Has No Stock In Warehouse");
					}

					if (industryList.size() > 0) {
						if (stockView.getSample().size() > 0) {
							
							for (int i = 0; i < stockView.getSample().size(); i++) {
								
								BigDecimal sto = new BigDecimal(stockView
										.getSample().get(i).getQuantity());
								sto = sto.setScale(2, RoundingMode.CEILING);
								StockViewMB vmbMb1 = new StockViewMB();
								vmbMb1.setS1(stockView.getS1());
								vmbMb1.setS2(stockView.getS2());
								
								vmbMb1.setpName(stockView.getSample().get(i)
										.getI0018().getProductName());
								{
									String up = "";
									String Untprc = controller.getunitprice(
											stockView.getSample().get(i)
													.getI0018()
													.getProductName(), up);
									vmbMb1.setA2(new BigDecimal(Untprc));
								}

								vmbMb1.setUnitPrice(stockView.getSample()
										.get(i).getI0018().getUnitPrice());
								vmbMb1.setBatchName(stockView.getSample()
										.get(i).getI0018().getBatchName());
								vmbMb1.setStatus(stockView.getSample().get(i)
										.getI0018().getI0017().getI0015()
										.getStatus());
								
								vmbMb1.setA(new BigDecimal(sto.toString()));
								vmbMb1.setValue(sto.toString());
								
								vmbMb1.setUnit(stockView.getUnit());
								vmbMb1.setA1(new BigDecimal(stockView
										.getBuyingPrice())
										.multiply(new BigDecimal(stockView
												.getSample().get(i)
												.getQuantity())));
								vmbMb1.setTotal1(""
										+ new BigDecimal(stockView
												.getBuyingPrice())
												.multiply(new BigDecimal(
														stockView.getSample()
																.get(i)
																.getQuantity())));
								stockList1.add(vmbMb1);
								setTotal1(""
										+ new BigDecimal(
												stockView.getBuyingPrice())
												.multiply(sto));
								eee = eee.add(new BigDecimal(total1));
								rolquan = rolquan.add((sto));

							}
							
							setNet11("" + eee);
							setTotRollNos("" + stockView.getSample().size());
							setTotRollQuans("" + rolquan);
							flag1 = "1";
							flag = "none";
						}
					} else {
						flag1 = "none";
						flag = "none";
					}
				} catch (IllegalArgumentException p) {
					logger.error("inside exception",p);
				}
				setValue("" + industryList.size());
				return "";
			} catch (DemoException e) {
				flag = "none";
				setValidate(e.getMessage());
				logger.debug(e.getMessage());
				return "";
			}
		} catch (NumberFormatException e) {
			logger.debug("Number Format Exception");
			return "";
		}
	}

	

	// jency

	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public StockViewMB() {
		sortsOrders = new HashMap<String, SortOrder>();
		sortPriorities = new ArrayList<String>();
	}

	public void sort() {
		logger.info("[sort()]----------------- Inside sort() method()----------------------- ");
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
				sortsOrders.put(property, SortOrder.DESCENDING);
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


	// prema begin 29/04/2016 dialog box creation for stock view
	public void stockview() {
		logger.info("[stockView()]----------------- Inside stockView() method()----------------------- ");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("stockView", options,
				null);
		StockViewdirect();
	}
	// prema end 29/0/2016
}
