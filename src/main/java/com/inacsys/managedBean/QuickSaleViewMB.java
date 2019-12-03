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
import org.primefaces.context.RequestContext;
import org.primefaces.model.SortOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0021;
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

@ManagedBean(name = "quickSaleViewMB")
public class QuickSaleViewMB {
	private static Logger logger = Logger.getLogger(QuickSaleViewMB.class);
	public String customername;
	public Date date;
	public String phnumber;
	public String mailid;
	public String productName;
	public String quantity;
	public String sellingprice;
	public String marketprice;
	public String marginPrice;
	public String grossTotal;
	public String Description;
	public List<PurchaseOrder> datalist1 = null;
	public String shippingaddress;
	public String salesno;
	public Date deliverdate;
	public List<I0021> saleslist = null;
	public String validate;
	public String userType;
	public String approvalStatus;
	private boolean quicksalesCheck=false;
	private String approveButtonFlag="none";
	public int qsalesid;
	public String baseCurrency;
	public String currencyAmount;
	
	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public int getQsalesid() {
		return qsalesid;
	}

	public void setQsalesid(int qsalesid) {
		this.qsalesid = qsalesid;
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

	public boolean isQuicksalesCheck() {
		return quicksalesCheck;
	}

	public void setQuicksalesCheck(boolean quicksalesCheck) {
		this.quicksalesCheck = quicksalesCheck;
	}

	public String getApproveButtonFlag() {
		return approveButtonFlag;
	}

	public void setApproveButtonFlag(String approveButtonFlag) {
		this.approveButtonFlag = approveButtonFlag;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getShippingaddress() {
		return shippingaddress;
	}

	public void setShippingaddress(String shippingaddress) {
		this.shippingaddress = shippingaddress;
	}

	public String getSalesno() {
		return salesno;
	}

	public void setSalesno(String salesno) {
		this.salesno = salesno;
	}

	public Date getDeliverdate() {
		return deliverdate;
	}

	public void setDeliverdate(Date deliverdate) {
		this.deliverdate = deliverdate;
	}

	public List<PurchaseOrder> getDatalist1() {
		return datalist1;
	}

	public void setDatalist1(List<PurchaseOrder> datalist1) {
		this.datalist1 = datalist1;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPhnumber() {
		return phnumber;
	}

	public void setPhnumber(String phnumber) {
		this.phnumber = phnumber;
	}

	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(String sellingprice) {
		this.sellingprice = sellingprice;
	}

	public String getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(String marketprice) {
		this.marketprice = marketprice;
	}

	public String getMarginPrice() {
		return marginPrice;
	}

	public void setMarginPrice(String marginPrice) {
		this.marginPrice = marginPrice;
	}

	public String getGrossTotal() {
		return grossTotal;
	}

	public void setGrossTotal(String grossTotal) {
		this.grossTotal = grossTotal;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date fromDate;
	public Date toDate;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	ArrayList<QuickSaleViewMB> quicks = new ArrayList<QuickSaleViewMB>();

	public ArrayList<QuickSaleViewMB> getQuicks() {
		return quicks;
	}

	public void setQuicks(ArrayList<QuickSaleViewMB> quicks) {
		this.quicks = quicks;
	}

	BigDecimal cross1 = BigDecimal.valueOf(0);
	public Date salesdateorder;
	public String salesnum;

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

	public BigDecimal getCross1() {
		return cross1;
	}

	public void setCross1(BigDecimal cross1) {
		this.cross1 = cross1;
	}

	ArrayList<QuickSaleViewMB> sales1 = new ArrayList<QuickSaleViewMB>();
	ArrayList<QuickSaleViewMB> filteredList;

	public ArrayList<QuickSaleViewMB> getFilteredList() {
		return filteredList;
	}

	public void setFilteredList(ArrayList<QuickSaleViewMB> filteredList) {
		this.filteredList = filteredList;
	}

	public ArrayList<QuickSaleViewMB> getSales1() {
		return sales1;
	}

	public void setSales1(ArrayList<QuickSaleViewMB> sales1) {
		this.sales1 = sales1;
	}

	public String approval;
	
	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}
	
	public String serialno;
	

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String quicksaleview() {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			sales1.clear();int count=0;setFilteredList(null);
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
			setValidate(null);
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			/*
			 * purchaseOrder.setOrderDate(fromDate);
			 * purchaseOrder.setToDate(toDate);
			 */
			setResult(purchaseOrder.getResult());
			purchaseOrder.setApproval(approval);
			purchaseOrder.setCustomername(customername);
			controller.quickSaleView(purchaseOrder);
			if (purchaseOrder.getResult().size() > 0) {
				setResult(purchaseOrder.getResult());
				logger.debug("datalist-------->>" + result);
				for (int i = 0; i < result.size(); i++) {
					/*
					 * QuickSaleViewMB quick1=new QuickSaleViewMB(); quick1.set
					 */
					/*
					 * result.get(i).setCrossTotal(GenerateEmployee.numberFormat.
					 * format(new BigDecimal(result.get(i).getCrossTotal())));
					 */
					QuickSaleViewMB quicks = new QuickSaleViewMB();
					quicks.setSerialno(String.valueOf(i+1));  
					quicks.setSalesdateorder(result.get(i).getSalesOrderDate());
					quicks.setSalesnum(result.get(i).getSalesOrderNumber());
					quicks.setCross1(new BigDecimal(result.get(i)
							.getCrossTotal()));
					quicks.setSalesno(result.get(i).getPaymentType());
					quicks.setApprovalStatus(result.get(i).getApprovalStatus());
					quicks.setQsalesid(result.get(i).getSales_ID());
					quicks.setBaseCurrency(baseCurrency);
					quicks.setCurrencyAmount(result.get(i).getCurrencyAmount());
					sales1.add(quicks);
					/*if(result.get(i).getUserID().getUserNo()==Integer.parseInt(userID)){
						sales1.add(quicks);
					}else{
						if(result.get(i).getApprovalStatus().equals("draft")){
							sales1.add(quicks);
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

				/* flag="1"; */
			} else if (purchaseOrder.getResult().size() == 0) {
				setResult(purchaseOrder.getResult());
				logger.debug("datalist catch----->>" + result.size());
				/* flagpop="1";flag="none"; */
			}

		} catch (DemoException i) {
			setValidate(i.getMessage());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

		finally {

		}
		return "";

	}

	List<I0019> resul;

	public List<I0019> getResul() {
		return resul;
	}

	public void setResul(List<I0019> resul) {
		this.resul = resul;
	}

	public List<I0021> result = null;

	public List<I0021> getResult() {
		return result;

	}

	public void setResult(List<I0021> result) {
		this.result = result;
	}

	public String flag = "none";
	public String flagpop = "none";

	public String getFlagpop() {
		return flagpop;
	}

	public void setFlagpop(String flagpop) {
		this.flagpop = flagpop;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String view() {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {

			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setSalesIdReference(salesno);
			logger.debug("salesno-->>" + salesno);
			saleslist = (controller.view(saleslist, purchaseOrder));
			grossTotal = "" + saleslist.get(0).getCrossTotal();
			logger.debug("gross total----->>" + grossTotal);
			salesno = saleslist.get(0).getSalesOrderNumber();
			logger.debug("sales no---->>" + salesno);
			shippingaddress = saleslist.get(0).getShipingAddress();
			deliverdate = saleslist.get(0).getDeliveryDate();
			logger.debug("del date----->>" + deliverdate);
			phnumber = saleslist.get(0).getPhoneNumber();
			logger.debug("ph number------->>>" + phnumber);
			date = saleslist.get(0).getSalesOrderDate();
			logger.debug("date---->>" + date);
			customername = saleslist.get(0).getCustomerName();
			logger.debug("customer name---->>" + customername);
			mailid = saleslist.get(0).getEMail();
			logger.debug("mailid---->>" + mailid);
			Description = saleslist.get(0).getNote();
			// productName=saleslist.get(0).getI0028().getI0021s().get(0).getI0019s().get(0).getI0001().getProductName();
			logger.debug("product    name---->>>" + productName);
			// sellingprice=saleslist.get(0).getI0028().getI0021s().get(0).getI0019s().get(0).getI0001().getSellingPrice();
			logger.debug("selling price------->>" + sellingprice);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "quickSaleView1";
	}

	public List<PurchaseOrder> editlist = null;

	public List<I0021> getSaleslist() {
		return saleslist;
	}

	public void setSaleslist(List<I0021> saleslist) {
		this.saleslist = saleslist;
	}

	public List<PurchaseOrder> getEditlist() {
		return editlist;
	}

	public void setEditlist(List<PurchaseOrder> editlist) {
		this.editlist = editlist;
	}

	public String edit() {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {

			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setSalesIdReference(salesno);
			logger.debug("salesno-->>" + salesno);
			purchaseOrder.setStatus("edit");
			setEditlist(controller.getQuicksaleEdit(purchaseOrder));
			setCustomername(editlist.get(0).getCustomername());
			logger.debug("customer name------->>"
					+ editlist.get(0).getCustomername());
			setDate(editlist.get(0).getOrderDate());
			logger.debug("datre------->>" + editlist.get(0).getOrderDate());
			setDeliverdate(editlist.get(0).getDeliverydate());
			setGrossTotal(editlist.get(0).getGrosstotal());
			setMailid(editlist.get(0).getEmail());
			setPhnumber(editlist.get(0).getPhonenumber());
			setSalesno(editlist.get(0).getSalesIdReference());
			setShippingaddress(editlist.get(0).getShipingaddress());
			setDescription(editlist.get(0).getDelayreason());
			logger.debug("description-------->>>"
					+ editlist.get(0).getDelayreason());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "quickSaleEdit";
	}

	public String confirmedit() {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {

			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setSalesIdReference(salesno);
			logger.debug("salesno-->>" + salesno);
			purchaseOrder.setStatus("update");
			purchaseOrder.setCustomername(customername);
			logger.debug("customername--->>" + customername);
			purchaseOrder.setDelayreason(Description);
			purchaseOrder.setEmail(mailid);
			purchaseOrder.setGrosstotal(grossTotal);
			purchaseOrder.setOrderDate(date);
			purchaseOrder.setPhonenumber(phnumber);
			purchaseOrder.setDeliverydate(deliverdate);
			purchaseOrder.setSalesIdReference(salesno);
			purchaseOrder.setShipingaddress(shippingaddress);
			setEditlist(controller.getQuicksaleEdit(purchaseOrder));
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "quickSaleEditConfirm";
	}

	public String delete() {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setSalesIdReference(salesno);
			logger.debug("salesno-->>" + salesno);
			purchaseOrder.setStatus("delete");
			setEditlist(controller.getQuicksaleEdit(purchaseOrder));
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "quickSaleDelete";
	}

	public List<I0021> cusname = null;

	public List<I0021> getCusname() {
		return cusname;
	}

	public void setCusname(List<I0021> cusname) {
		this.cusname = cusname;
	}

	public String dropdown() {
		logger.debug("inside manage bean---------->>>>");
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			logger.debug("inside try in mb----->>>");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setCusname(controller.customerNameChange(cusname));
			logger.debug("custromer name--------->>" + cusname);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";

	}

	public String cancel() {
		flag = "none";
		flagpop = "none";
		fromDate = null;
		toDate = null;
		return "QuickSaleHomeviewpage";

	}

	public String redirectHome() {
		flag = "none";
		fromDate = null;
		toDate = null;
		flagpop = "none";
		setValidate(null);
		/*
		 * return "redirectQuickHome";
		 */
		return "";
	}

	/* jency */
	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public QuickSaleViewMB() {
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

	// prema begin 29/04/2016 dialog box creation for Quick sales view
	public void quicksalesview() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("quickSaleView",
				options, null);
		quicksaleview();
	}

	// prema end 29/0/2016
	public void quicksaleviewclose() {
		RequestContext.getCurrentInstance().closeDialog("quickSaleView");
	}
	
	public String quickSalesApproval(){
		String status="";DemoController controller = null;
		int count=0;setValidate("");
		try{
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			for (int i = 0; i < sales1.size(); i++) {
				if(sales1.get(i).isQuicksalesCheck()==true){
					System.out.println("inside if----");
					count++;
				}
			}
			if(count==0){
				System.out.println("inside if=====");
				throw new Exception("Please Choose atleast one row for Approval.");
			}else{
				setValidate("");
				status=controller.quicksalesApproval(sales1);
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
}
