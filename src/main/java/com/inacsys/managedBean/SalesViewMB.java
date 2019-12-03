package com.inacsys.managedBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.SortOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

//import antlr.collections.List;

//import antlr.collections.List;











import com.inacsys.controler.DemoController;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0021;
import com.inacsys.shared.I0023;
import com.inacsys.shared.SalesRecord;
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

@ManagedBean(name = "salesViewMB")
public class SalesViewMB {
	private static Logger logger = Logger.getLogger(SalesViewMB.class);
	public String netReference;

	public String getNetReference() {
		return netReference;
	}

	public void setNetReference(String netReference) {
		this.netReference = netReference;
	}

	public Date date;
	public Date todate;
	public Date odate;
	public Date tardate;
	public String discType;
	public String discAmnt;
	public String currency;
	public String userType;
	public String approvalStatus;
	private boolean salesCheck=false;
	private String approveButtonFlag="none";
	public int salesId;
	
	
	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}

	public boolean isSalesCheck() {
		return salesCheck;
	}

	public void setSalesCheck(boolean salesCheck) {
		this.salesCheck = salesCheck;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApproveButtonFlag() {
		return approveButtonFlag;
	}

	public void setApproveButtonFlag(String approveButtonFlag) {
		this.approveButtonFlag = approveButtonFlag;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public boolean salesDeleteFlag = false;

	public boolean isSalesDeleteFlag() {
		return salesDeleteFlag;
	}

	public void setSalesDeleteFlag(boolean salesDeleteFlag) {
		this.salesDeleteFlag = salesDeleteFlag;
	}

	public String getDiscType() {
		return discType;
	}

	public void setDiscType(String discType) {
		this.discType = discType;
	}

	public String getDiscAmnt() {
		return discAmnt;
	}

	public void setDiscAmnt(String discAmnt) {
		this.discAmnt = discAmnt;
	}

	public Date getOdate() {
		return odate;
	}

	public void setOdate(Date odate) {
		this.odate = odate;
	}

	public Date getTardate() {
		return tardate;
	}

	public void setTardate(Date tardate) {
		this.tardate = tardate;
	}

	List<I0021> result;
	public String salereferencenumber;
	public String flag = "none";
	public String flag1 = "none";
	public String flag2 = "none";
	public String buyerName;
	public String crossTotal1;

	public String getCrossTotal1() {
		return crossTotal1;
	}

	public void setCrossTotal1(String crossTotal1) {
		this.crossTotal1 = crossTotal1;
	}

	public String getCrossTotal() {
		return crossTotal;
	}

	public void setCrossTotal(String crossTotal) {
		this.crossTotal = crossTotal;
	}

	public String productname;
	public String quantity;
	public String quantity1;
	public String quantity2;
	public String discountzAmount;
	private String rollID;
	private Date salesOrderDate;
	private String shipcompany;
	private String shipcost;

	public String getShipcompany() {
		return shipcompany;
	}

	public void setShipcompany(String shipcompany) {
		this.shipcompany = shipcompany;
	}

	public String getShipcost() {
		return shipcost;
	}

	public void setShipcost(String shipcost) {
		this.shipcost = shipcost;
	}

	private String crossTotal;
	private String unit;

	public String getRollID() {
		return rollID;
	}

	public void setRollID(String rollID) {
		this.rollID = rollID;
	}

	public String getDiscountzAmount() {
		return discountzAmount;
	}

	public void setDiscountzAmount(String discountzAmount) {
		this.discountzAmount = discountzAmount;
	}

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	public String getQuantity2() {
		return quantity2;
	}

	public void setQuantity2(String quantity2) {
		this.quantity2 = quantity2;
	}

	public String getQuantity1() {
		return quantity1;
	}

	public void setQuantity1(String quantity1) {
		this.quantity1 = quantity1;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
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

	public String getSalereferencenumber() {
		return salereferencenumber;
	}

	public void setSalereferencenumber(String salereferencenumber) {
		this.salereferencenumber = salereferencenumber;
	}

	public List<I0021> getResult() {
		return result;
	}

	public void setResult(List<I0021> result) {
		this.result = result;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	List<SalesRecord> resul1;

	public List<SalesRecord> getResul1() {
		return resul1;
	}

	public void setResul1(List<SalesRecord> resul1) {
		this.resul1 = resul1;
	}

	List<I0019> resul;

	public List<I0019> getResul() {
		return resul;
	}

	public void setResul(List<I0019> resul) {
		this.resul = resul;
	}

	DemoController controller = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();
	String validate;

	ArrayList<I0021> result1 = null;
	ArrayList<I0021> result2 = null;

	public ArrayList<I0021> getResult2() {
		return result2;
	}

	public void setResult2(ArrayList<I0021> result2) {
		this.result2 = result2;
	}

	public ArrayList<I0021> getResult1() {
		return result1;
	}

	public void setResult1(ArrayList<I0021> result1) {
		this.result1 = result1;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	ArrayList<SalesViewMB> sales = new ArrayList<SalesViewMB>();
	ArrayList<SalesViewMB> filterList1;

	public ArrayList<SalesViewMB> getFilterList1() {
		return filterList1;
	}

	public void setFilterList1(ArrayList<SalesViewMB> filterList1) {
		this.filterList1 = filterList1;
	}

	BigDecimal phones = BigDecimal.valueOf(0);
	BigDecimal cross = BigDecimal.valueOf(0);
	public String status;
	public String status2;
	public String salesorder;
	ArrayList<SalesViewMB> filterList;

	public ArrayList<SalesViewMB> getFilterList() {
		return filterList;
	}

	public void setFilterList(ArrayList<SalesViewMB> filterList) {
		this.filterList = filterList;
	}

	public String getSalesorder() {
		return salesorder;
	}

	public void setSalesorder(String salesorder) {
		this.salesorder = salesorder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public ArrayList<SalesViewMB> getSales() {
		return sales;
	}

	public void setSales(ArrayList<SalesViewMB> sales) {
		this.sales = sales;
	}

	public BigDecimal getPhones() {
		return phones;
	}

	public void setPhones(BigDecimal phones) {
		this.phones = phones;
	}

	public BigDecimal getCross() {
		return cross;
	}

	public void setCross(BigDecimal cross) {
		this.cross = cross;
	}
	
	public String baseCurrency;
	

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String approval;
	
	
	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String salesView() {
		logger.info("[inside salesView()]-------------------inside salesView() method()---------------");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
		try {
			setFilterList(null);
			sales.clear();int count=0;
			validate = null;
			purchaseOrder.setApproval(approval);
			purchaseOrder.setResult(result);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesView(purchaseOrder);
			result = purchaseOrder.getResult();

			if (result.size() > 0) {
				
				for (int i = 0; i < result.size(); i++) {
					SalesViewMB views = new SalesViewMB();
					views.setSerialno(String.valueOf(i+1));  
					views.setSalesOrderDate(result.get(i).getSalesOrderDate());
					views.setCustomerName(result.get(i).getCustomerName());
					views.setSalesorder(result.get(i).getSalesOrderNumber());
					views.setPhones(new BigDecimal(result.get(i)
							.getPhoneNumber()));
					views.setCross(new BigDecimal(result.get(i).getCrossTotal()));
					views.setStatus(result.get(i).getStatus());
					views.setStatus2(result.get(i).getStatus2());
					views.setApprovalStatus(result.get(i).getApprovalStatus());
					views.setSalesId(result.get(i).getSales_ID());
					views.setCurrency(result.get(i).getCrossTotal());
					views.setCurrencyAmount(result.get(i).getCurrencyAmount());
					views.setBaseCurrency(baseCurrency);
					sales.add(views);
					/*if(result.get(i).getUserID().getUserNo()==Integer.parseInt(userID)){
						sales.add(views);
					}else{
						if(result.get(i).getApprovalStatus().equals("draft")){
							sales.add(views);
						}
					}*/					
					if(result.get(i).getApprovalStatus().equals("draft")){
						count++;
					}
				}
				if(count==0){
					approveButtonFlag="none";
				}else{
					approveButtonFlag="1";
				}
			} else if (result.size() == 0) {
				
			}

			return "";
		} catch (DemoException e) {
			setValidate(e.getMessage());
			logger.error("inside exception",e);
			return "";
		}

		finally {

		}

	}

	public String salesOrdercancelForm2() {
		logger.info("[inside salesOrdercancelForm2()]-------------------inside salesOrdercancelForm2() method()---------------");
		try {
			flag = "none";
			purchaseOrder.setSalesIdReference(salereferencenumber);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");

			controller.salesOrdercancelForm2(purchaseOrder);
			return "salesdeleteconfirmpage";
		} catch (Exception e) {
			logger.error("inside exception",e);
			setValidate(e.getMessage());
			return "";
		} finally {
			resul = null;
			purchaseOrder.setResul(null);
		}
	}

	public String salesOrdercancelForm3() {
		logger.info("[inside salesOrdercancelForm3()]-------------------inside salesOrdercancelForm3() method()---------------");
		try {
			flag = "none";
			setSalesDeleteFlag(false);
			
			purchaseOrder.setSalesIdReference(salereferencenumber);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrdercancelForm5(purchaseOrder);
			RequestContext.getCurrentInstance().execute(
					"PF('confirmsu').show();");
			return "";
		} catch (DemoException e) {
			RequestContext.getCurrentInstance()
					.execute("PF('errormsg').show()");
			return "";
		} catch (Exception e) {
			logger.error("inside exception",e);
			setValidate("*This order number didnot purchase any Product");
			return "";
		} finally {
			resul = null;
			purchaseOrder.setResul(null);
		}
	}

	public String salesOrderdelete() {
		logger.info("[inside salesOrderdelete()]-------------------inside salesOrderdelete() method()---------------");
		try {
			flag = "none";
			purchaseOrder.setSalesIdReference(salereferencenumber);
			purchaseOrder.setSalesId(salesId);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrderdelete(purchaseOrder);
			RequestContext.getCurrentInstance().execute(
					"PF('confirmsu').show();");
			return "";
		} catch (DemoException e) {
			logger.error("inside exception",e);
			RequestContext.getCurrentInstance()
					.execute("PF('errormsg').show()");
			return "";
		} catch (Exception e) {
			logger.error("inside exception",e);
			setValidate("*This order number didnot purchase any Product");
			return "";
		} finally {
			resul = null;
			purchaseOrder.setResul(null);
		}
	}

	public String salesOrdercancelForm1() {
		logger.info("[inside salesOrdercancelForm1()]-------------------inside salesOrdercancelForm1() method()---------------");
		try {
			flag = "none";
			validate = null;
			if (purchaseOrder.getResul().size() == 0) {
				throw new DemoException("wrong.....enter the sales orde number");
			}
			return "success";
		} catch (Exception e) {
			logger.error("inside exception",e);
			logger.debug(e.getMessage());
			return "failure";
		}
	}

	public void changeDrop(ValueChangeEvent v) {
		logger.info("[inside changedrop()]-------------------inside changedrop() method()---------------");
		try {

			validate = null;
			flag2 = "none";
			flag = "none";
			String s = (String) v.getNewValue();
			purchaseOrder.setValueChange(s);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.changeDrop(purchaseOrder);
			result2 = purchaseOrder.getResult2();
			if (result.size() == 0) {
				flag2 = "none";
				flag = "none";
				flag1 = "none";

			}

		} catch (DemoException e) {
			logger.error("inside exception",e);
			flag = "none";
			flag2 = "none";
			flag1 = "none";
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
		}

	}

	public String direct3() {
		logger.info("[direct3()]-------------------inside direct3() method()---------------");
		try {

			flag2 = "none";
			flag = "none";
			setValidate(null);
			setDate(null);
			setTodate(null);
			setBuyerName(null);
			setResul(null);
			setResult(null);
			setSalereferencenumber(null);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesDelete();
		} catch (Exception e) {
			logger.error("inside exception",e);
		} finally {
			date = null;
			todate = null;
			buyerName = null;
			salereferencenumber = null;
			flag2 = "none";
		}

		return "";
	}

	List<PurchaseOrder> resulfinal = null;

	public List<PurchaseOrder> getResulfinal() {
		return resulfinal;
	}

	public void setResulfinal(List<PurchaseOrder> resulfinal) {
		this.resulfinal = resulfinal;
	}

	public String customerName;
	public String address;
	public String telephonenumber;

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephonenumber() {
		return telephonenumber;
	}

	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}

	BigDecimal cross1 = BigDecimal.valueOf(0);
	public Date salesdateorder;
	public String salesnum;
	public String currencyAmount;

	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public BigDecimal getCross1() {
		return cross1;
	}

	public void setCross1(BigDecimal cross1) {
		this.cross1 = cross1;
	}

	public Date getSalesdateorder() {
		return salesdateorder;
	}

	public void setSalesdateorder(Date salesdateorder) {
		this.salesdateorder = salesdateorder;
	}

	public String getSalesnum() {
		return salesnum;
	}

	public void setSalesnum(String salesnum) {
		this.salesnum = salesnum;
	}

	ArrayList<SalesViewMB> viewMBs = new ArrayList<SalesViewMB>();

	public ArrayList<SalesViewMB> getViewMBs() {
		return viewMBs;
	}

	public void setViewMBs(ArrayList<SalesViewMB> viewMBs) {
		this.viewMBs = viewMBs;
	}

	public String serialno;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String salesView2() {
		logger.info("[salesView2()]-------------------inside salesView2() method()---------------");
		try {
			flag1 = "none";
			viewMBs.clear();
			validate = null;
			BigDecimal qus = BigDecimal.valueOf(0);
			BigDecimal qustot = BigDecimal.valueOf(0);
			purchaseOrder.setSalesIdReference(salereferencenumber);
			purchaseOrder.setResul(resul);
			purchaseOrder.setSalesId(salesId);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrderViewproduct(purchaseOrder);
			resulfinal = purchaseOrder.getResulfinal();
			if (resulfinal.size() == 0) {
				flag1 = "none";
				flag2 = "1";
				throw new DemoException("This date didnot have any sales");

			} else {
				int count = 0;
				List<String> productname = new ArrayList<String>();
				for (int i = 0; i < resulfinal.size(); i++) {
					productname.add(resulfinal.get(i).getProduct_name());
				}
				HashSet<String> s = new HashSet<String>(productname);
				for (String e : s) {
					qus = BigDecimal.valueOf(0);
					qustot = BigDecimal.valueOf(0);
					for (int j = 0; j < resulfinal.size(); j++) {
						if (e.equalsIgnoreCase(resulfinal.get(j)
								.getProduct_name())) {
							qus = qus.add(new BigDecimal(resulfinal.get(j)
									.getQuantity()));
							qustot = qustot.add(new BigDecimal(resulfinal
									.get(j).getTotalPrice()));

						} else {
						}
					}
					SalesViewMB saless = new SalesViewMB();
					count = count + 1;
					saless.setSerialno("" + count);
					saless.setProductname("" + e);
					saless.setQuantity("" + qus);
					saless.setTotalPrice("" + qustot);
					saless.setCurrency(resulfinal.get(0).getCurrency());
					viewMBs.add(saless);
				}

				for (int i = 0; i < resulfinal.size(); i++) {
					
					resulfinal.get(i).setSellingPrice(
							GenerateEmployee.numberFormat
									.format(new BigDecimal(resulfinal.get(i)
											.getSellingPrice())));
					resulfinal.get(i).setDiscountzAmount(
							GenerateEmployee.numberFormat
									.format(new BigDecimal(resulfinal.get(i)
											.getDiscountzAmount())));
					resulfinal.get(i).setTotalPrice(
							GenerateEmployee.numberFormat
									.format(new BigDecimal(resulfinal.get(i)
											.getTotalPrice())));
				}

				flag1 = "1";
				flag = "1";
				flag2 = "none";
				setSalesOrderDate(purchaseOrder.getSalesdateorder());
				setCustomerName(purchaseOrder.getCustomerName());
				setTelephonenumber(purchaseOrder.getTelephonenumber());
				setAddress(purchaseOrder.getAddress());
				setUnit(purchaseOrder.getUnit());
				setTotalPrice(purchaseOrder.getTotalPrice());
				setSaleNumber(purchaseOrder.getSalesIdReference());
				setDiscountzAmount(purchaseOrder.getDiscountzAmount());
				setTardate(purchaseOrder.getTargentDate());
				setShipcompany(purchaseOrder.getShipping_company());
				setShipcost(purchaseOrder.getShipping_charge());
				setCrossTotal(purchaseOrder.getgTotal());
				setCrossTotal1(purchaseOrder.getCrosstotal1());
				setNetReference(purchaseOrder.getCrosstotal1());
				setDiscAmnt(purchaseOrder.getDiscAmnt());
				setDiscType(purchaseOrder.getDiscType());
				setStatus(purchaseOrder.getStatus());
				setCurrency(purchaseOrder.getCurrency());
				setCurrencyAmount(purchaseOrder.getCurrencyAmount());
			}

			return "";
		} catch (DemoException e) {
			flag = "none";
			flag1 = "none";
			flag2 = "none";
			logger.error("inside exception ",e);
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "failure";
		} catch (Exception ie) {
			setValidate("*This order is already cancelled");
			logger.error("inside exception ",ie);
			logger.debug(ie.getStackTrace());
			logger.error("Inside Exception", ie);
			return "";
		}

	}

	public String salesViewnew() {
		logger.info("[salesViewnew()]-------------------inside salesViewnew() method()---------------");
		try {
			flag1 = "none";
			viewMBs.clear();
			validate = null;
			BigDecimal qus = BigDecimal.valueOf(0);
			BigDecimal qustot = BigDecimal.valueOf(0);
			if (salereferencenumber.equals("")) {
				throw new DemoException("");
			}
			purchaseOrder.setSalesIdReference(salereferencenumber);
			purchaseOrder.setResul(resul);

			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrderViewproduct(purchaseOrder);
			resulfinal = purchaseOrder.getResulfinal();

			if (resulfinal.size() == 0) {
				flag1 = "none";
				flag2 = "1";
				throw new DemoException("This date didnot have any sales");

			} else {
				int count = 0;
				List<String> productname = new ArrayList<String>();
				for (int i = 0; i < resulfinal.size(); i++) {
					productname.add(resulfinal.get(i).getProduct_name());
				}
				
				HashSet<String> s = new HashSet<String>(productname);
				for (String e : s) {
					qus = BigDecimal.valueOf(0);
					qustot = BigDecimal.valueOf(0);
					for (int j = 0; j < resulfinal.size(); j++) {
						if (e.equalsIgnoreCase(resulfinal.get(j)
								.getProduct_name())) {
							qus = qus.add(new BigDecimal(resulfinal.get(j)
									.getQuantity()));
							qustot = qustot.add(new BigDecimal(resulfinal
									.get(j).getTotalPrice()));

						} else {
							logger.debug("~~~inside>>>>else");
						}
					}
					SalesViewMB saless = new SalesViewMB();
					count = count + 1;
					saless.setSerialno("" + count);
					saless.setProductname("" + e);
					saless.setQuantity("" + qus);
					saless.setTotalPrice("" + qustot);
					viewMBs.add(saless);

				}

				setNetReference(purchaseOrder.getCrosstotal1());
				setSalesdateorder(purchaseOrder.getTargentDate());
			}

			return "salesView4";
		} catch (DemoException e) {
			flag = "none";
			flag1 = "none";
			flag2 = "none";
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "failure";
		} catch (Exception ie) {
			setValidate("*This order is already cancelled");
			logger.debug(ie.getStackTrace());
			logger.error("Inside Exception", ie);
			return "";
		}

	}

	public String totalPrice;
	public String saleNumber;

	public String getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String salesView3() {
		logger.info("[salesView3()]-------------------inside salesView3() method()---------------");
		try {
			flag2 = "none";
			validate = null;
			quantity = null;
			purchaseOrder.setActualPrice("" + BigDecimal.valueOf(0));
			purchaseOrder.setSalesIdReference(salereferencenumber);
			purchaseOrder.setResul(resul);
			purchaseOrder.setSalesId(salesId);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrderViewproduct(purchaseOrder);
			resulfinal = purchaseOrder.getResulfinal();
			logger.debug("pkid::::::::" + purchaseOrder.getSalesId());

			if (resulfinal.size() == 0) {
				flag2 = "none";
				throw new DemoException("This date didnot have any sales");

			} else {
				flag2 = "1";
				flag = "1";
				setCustomerName(purchaseOrder.getCustomerName());
				setTelephonenumber(purchaseOrder.getTelephonenumber());
				setAddress(purchaseOrder.getAddress());
				if (purchaseOrder.getResult().get(0).getStatus2()
						.equals("pending")) {
					throw new DemoException("*****");
				}
				if (purchaseOrder.getResult().get(0).getStatus()
						.equals("cancelled")) {
					throw new DemoException("*This order is already cancelled");
				}
				try {
					if (purchaseOrder.getResult().get(0).getStatus2()
							.equals("paid")) {
						throw new DemoException("*This order is already paid");
					}
				} catch (NullPointerException e) {
				}

			}

			return "";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			flag = "none";
			flag2 = "none";
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "failure";
		} catch (Exception ie) {
			setValidate("*This order is already cancelled");
			logger.debug(ie.getStackTrace());
			logger.error("Inside Exception", ie);
			return "";
		} finally {
			flag2 = "none";
		}

	}

	public String salesView4() {
		logger.info("[salesView4()]-------------------inside salesView4() method()---------------");
		try {
			flag2 = "none";
			validate = null;
			purchaseOrder.setActualPrice("" + BigDecimal.valueOf(0));
			if (salereferencenumber.equals("")) {
				throw new DemoException("");
			}
			purchaseOrder.setSalesIdReference(salereferencenumber);
			purchaseOrder.setSalesorderdate(salesOrderDate);
			purchaseOrder.setSalesId(salesId);
			purchaseOrder.setResul(resul);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrderViewproduct(purchaseOrder);
			resulfinal = purchaseOrder.getResulfinal();
			setCurrencyAmount(purchaseOrder.getCurrencyAmount());
			setCurrency(purchaseOrder.getCurrency());
			if (resulfinal.size() == 0) {
				flag2 = "none";
				throw new DemoException("This date didnot have any sales");

			} else {
				flag2 = "1";
				flag = "1";
				setCustomerName(purchaseOrder.getCustomerName());
				setTelephonenumber(purchaseOrder.getTelephonenumber());
				setAddress(purchaseOrder.getAddress());
				if (purchaseOrder.getResult().get(0).getStatus()
						.equals("Delivered")) {
					throw new DemoException("*This order is already delivered");
				}

				if (purchaseOrder.getActualPrice().equalsIgnoreCase(
						"" + BigDecimal.valueOf(0))) {
					throw new DemoException(
							"*Edit cannot done after payment started");
				}
			}

			return "";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			flag = "none";
			flag2 = "none";
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "failure";
		} catch (Exception ie) {
			setValidate("*This order is already cancelled");
			logger.debug(ie.getStackTrace());
			logger.error("Inside Exception", ie);
			return "";
		}

	}

	public String salesDrop() {
		logger.info("[salesDrop()]-------------------inside salesDrop() method()---------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesDrop(purchaseOrder);
			result1 = purchaseOrder.getResult1();
			result1 = new ArrayList<I0021>(new HashSet<I0021>(result1));
			flag1 = "1";
			return "";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			return "";
		} catch (NullPointerException e) {
			logger.error("Inside Exception", e);
			return "";
		}

	}

	public String salesView1() {
		logger.info("[salesView1()]-------------------inside salesView1() method()---------------");
		try {
			sales.clear();
			flag = "none";
			flag2 = "none";
			validate = null;
			if (buyerName.equalsIgnoreCase("")) {
				throw new DemoException("Please Choose the Buyer Name");
			} else if (salereferencenumber.equals("")) {
				throw new DemoException("Please Choose the Sales Order Number");
			}
			purchaseOrder.setSalesIdReference(salereferencenumber);
			purchaseOrder.setResul(resul);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrdercancelForm4(purchaseOrder);
			resul1 = purchaseOrder.getResul1();
			result = purchaseOrder.getResult();
			if (result.size() == 0) {
				flag = "none";
				flag2 = "1";
			}
			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					SalesViewMB views = new SalesViewMB();
					views.setSalesOrderDate(result.get(i).getSalesOrderDate());
					views.setCustomerName(result.get(i).getCustomerName());
					views.setSalesorder(result.get(i).getSalesOrderNumber());
					views.setPhones(new BigDecimal(result.get(i)
							.getPhoneNumber()));
					views.setCross(new BigDecimal(result.get(i).getCrossTotal()));
					views.setStatus(result.get(i).getStatus());
					views.setStatus2(result.get(i).getStatus2());
					sales.add(views);
				}
			}
			if (resul1.get(0).getStatus().equals(null)) {
			}
			flag = "1";
			flag2 = "none";
			return "";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			flag = "none";
			flag2 = "none";
			setValidate(e.getMessage());
			return "";
		} catch (Exception ie) {
			logger.error("Inside Exception", ie);
			return "";
		}

	}

	public String updateSales() {
		logger.info("[updateSales()]-------------------inside updateSales() method()---------------");
		quantity = null;
		totalPrice = null;
		try {
			setValidate("");
			purchaseOrder.setProduct_name(productname);
			purchaseOrder.setRollID(rollID);
			purchaseOrder.setTotalPrice(totalPrice);
			purchaseOrder.setQuantity1(quantity1);
			return "updateSalesSuccess";
		}

		catch (Exception e) {
			logger.error("Inside Exception", e);
			return "updateSalesFailure";
		}
	}

	public String updateSales1() {
		ApplicationContext ctx=null;String status="";
		System.out.println("[updateSales1()]-------------------inside updateSales1() method()---------------");
		try {
			String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
			purchaseOrder.setResult5(resulfinal);
			for (int j = 0; j < resulfinal.size(); j++) {
				if (resulfinal.get(j).getQuantity().equals("")) {
					throw new DemoException("*Enter the quantity");
				}
				if (!resulfinal.get(j).getQuantity().matches("[-+]?[0-9]*\\.?[0-9]+")) {
					throw new DemoException("*Quantity should be in numeric");
				}
			}
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setSalesIdReference(salereferencenumber); 
			purchaseOrder.setSalesId(salesId); 
			purchaseOrder.setCurrency(currency);
			purchaseOrder.setBaseCurrency(baseCurrency);
			purchaseOrder.setResulfinal(resulfinal); 
			status=controller.updateSales1(purchaseOrder);
			controller.salesOrderViewproduct(purchaseOrder);
			System.out.println("---- return status ---->"+status); 
			if(status.equalsIgnoreCase("Fail")){
				setStatus2(purchaseOrder.getStatus2()); 
				RequestContext.getCurrentInstance().execute("PF('salesedit1').show()"); 
			}else{
				RequestContext.getCurrentInstance().execute("PF('salesedit').show();");
			}
			//setValidate(null);
			resulfinal = purchaseOrder.getResulfinal();
			return "";
		} catch (DemoException e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
			return "updateSales1Failure";
		}
	}

	public String cancel() {
		logger.info("[cancel()]-------------------inside cancel() method()---------------");
		flag = "none";
		flag2 = "none";
		flag1 = "none";
		todate = null;
		buyerName = null;
		date = null;

		salereferencenumber = null;
		return "salesView";
	}

	public String salescancel() {
		logger.info("[salescancel()]-------------------inside salescancel() method()---------------");
		try {
			flag2 = "none";
			validate = null;
			quantity = null;
			purchaseOrder.setActualPrice("" + BigDecimal.valueOf(0));
			if (salereferencenumber.equals("")) {
				throw new DemoException("");
			}
			purchaseOrder.setSalesIdReference(salereferencenumber);
			purchaseOrder.setResul(resul);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrderViewproduct(purchaseOrder);
			resulfinal = purchaseOrder.getResulfinal();

			if (resulfinal.size() == 0) {
				flag2 = "none";
				throw new DemoException("This date didnot have any sales");

			} else {
				flag2 = "1";
				flag = "1";
				setCustomerName(purchaseOrder.getCustomerName());
				setTelephonenumber(purchaseOrder.getTelephonenumber());
				setAddress(purchaseOrder.getAddress());
				if (purchaseOrder.getResult().get(0).getStatus()
						.equals("Delivered")) {
					throw new DemoException("*This order is already delivered");
				} else if (purchaseOrder.getResult().get(0).getStatus()
						.equals("cancelled")) {
					throw new DemoException("*This order is already cancelled");
				}

				try {
					if (purchaseOrder.getResult().get(0).getStatus2()
							.equals("paid")) {
						throw new DemoException("*This order is already paid");
					}
				} catch (NullPointerException e) {
				}

				if (purchaseOrder.getActualPrice().equalsIgnoreCase(
						"" + BigDecimal.valueOf(0))) {
					throw new DemoException(
							"*Edit cannot done after payment started");
				}
			}

			return "salesdeletepage";
		} catch (DemoException e) {
			flag = "none";
			flag2 = "none";
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "failure";
		} catch (Exception ie) {
			setValidate("*This order is already cancelled");
			logger.debug(ie.getStackTrace());
			logger.error("Inside Exception", ie);
			return "";
		} finally {
			flag2 = "none";
		}

	}

	public String ok() {
		return "salesPaymentPending";
	}

	public String back() {
		return "salesView";
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getSalesOrderDate() {
		return salesOrderDate;
	}

	public void setSalesOrderDate(Date salesOrderDate) {
		this.salesOrderDate = salesOrderDate;
	}

	/* jency */
	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public SalesViewMB() {
		sortsOrders = new HashMap<String, SortOrder>();
		sortPriorities = new ArrayList<String>();
	}

	public void sort() {
		logger.info("[sort()]-------------------inside sort() method()---------------");
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

	// prema begin 29/04/2016 dialog box creation for sales view
	public void salesview() {
		logger.info("[salesview()]-------------------inside salesview() method()---------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("salesView", options,
				null);
		salesView();
	}

	// prema end 29/0/2016
	public void salesviewclose() {
		RequestContext.getCurrentInstance().closeDialog("salesView");
	}

	public String salesdeliveryMB1() {
		logger.info("[salesDeliveryMB1()]-------------------inside salesDeliveryMB1() method()---------------");
		try {

			purchaseOrder.setSalesIdReference(salereferencenumber);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesdelivery(purchaseOrder);

			return "";
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return "failure1";
		}

		finally {
			resul1 = null;
			purchaseOrder.setResul(null);

		}
	}

	public void salesedit1() {
		RequestContext.getCurrentInstance().closeDialog("salesView3");
	}

	public void salesedit() {
		logger.info("[salesedit()]-------------------inside salesedit() method()---------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("salesView3", options,
				null);
		salesView3();
	}

	public void QuicksaleEdit() {
		logger.info("[quicksalesedit()]-------------------inside quicksalesedit() method()---------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("salesView5", options,
				null);
		salesView4();
	}

	public void QuicksaleEdit1() {
		RequestContext.getCurrentInstance().closeDialog("salesView5");
	}
	public String salesApproval(){
		logger.info("[salesApproval()]-------------------inside salesApproval() method()---------------");
		String status="";DemoController controller = null;
		int count=0;setValidate("");
		try{
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			for (int i = 0; i < sales.size(); i++) {
				if(sales.get(i).isSalesCheck()==true){
					System.out.println("inside if----");
					count++;
				}
			}
			if(count==0){
				System.out.println("inside if=====");
				throw new Exception("Please Choose atleast one row for Approval.");
			}else{
				setValidate("");
				status=controller.salesApproval(sales);
				System.out.println("status"+status);
				if(status.equalsIgnoreCase("Success")){
					RequestContext.getCurrentInstance().execute("PF('approvalConfirm').show()");
				}
			}
		}catch(Exception e){
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
		}
		return "";
	}
}
