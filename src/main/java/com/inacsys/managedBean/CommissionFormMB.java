package com.inacsys.managedBean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.inacsys.domain.Commission;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0032;

@ManagedBean(name = "commissionFormMB")
public class CommissionFormMB {
	private static Logger logger = Logger.getLogger(CommissionFormMB.class);
	BigDecimal unitprice = BigDecimal.valueOf(0);

	public BigDecimal getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public String productname;

	public String rollids;

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getRollids() {
		return rollids;
	}

	public void setRollids(String rollids) {
		this.rollids = rollids;
	}

	public String totQuan = "";
	public String totRoll = "";
	public String Validate;
	public String customername;
	public String category;
	public Date fromdate;
	public Date todate;
	List<String> name = new ArrayList<String>();
	List<Commission> totallist = new ArrayList<Commission>();
	public String flag = "none";
	public String flag1 = "none";
	public String invoiceno;
	public String orderdate;
	public String cname;
	public String totalSaleAmount;
	public String quantity;
	public String commisionAmount;
	public String totalCommision;
	public String sNo;
	public String address;
	public String soldQuan;
	public String prodcode;

	public String getTotQuan() {
		return totQuan;
	}

	public void setTotQuan(String totQuan) {
		this.totQuan = totQuan;
	}

	public String getTotRoll() {
		return totRoll;
	}

	public void setTotRoll(String totRoll) {
		this.totRoll = totRoll;
	}

	public String getProdcode() {
		return prodcode;
	}

	public void setProdcode(String prodcode) {
		this.prodcode = prodcode;
	}

	public String rollnum;

	public String getRollnum() {
		return rollnum;
	}

	public void setRollnum(String rollnum) {
		this.rollnum = rollnum;
	}

	public String getSoldQuan() {
		return soldQuan;
	}

	public void setSoldQuan(String soldQuan) {
		this.soldQuan = soldQuan;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTotalSaleAmount() {
		return totalSaleAmount;
	}

	public void setTotalSaleAmount(String totalSaleAmount) {
		this.totalSaleAmount = totalSaleAmount;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getCommisionAmount() {
		return commisionAmount;
	}

	public void setCommisionAmount(String commisionAmount) {
		this.commisionAmount = commisionAmount;
	}

	public String getTotalCommision() {
		return totalCommision;
	}

	public void setTotalCommision(String totalCommision) {
		this.totalCommision = totalCommision;
	}

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
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

	public List<Commission> getTotallist() {
		return totallist;
	}

	public void setTotallist(List<Commission> totallist) {
		this.totallist = totallist;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public String getValidate() {
		return Validate;
	}

	public void setValidate(String validate) {
		Validate = validate;
	}

	PurchaseOrder purchaseOrder = new PurchaseOrder();
	DemoController controller = null;

	public String commissionMenuCall() {
		logger.info("[commisionMenuCall()] --------------- inside commisionMenuCall() method() ------------------------>");
		ArrayList<I0032> buyerName = null;
		try {
			setValidate("");
			setTodate(null);
			setFromdate(null);
			flag = "none";
			flag1 = "none";
			setCategory("");
			setCustomername("");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setCategory("Free Lancer");
			controller.catogerycustomer(buyerName, purchaseOrder);
			setName(purchaseOrder.getResulfinal1());
			logger.debug("[commisionMenuCall()] --------------- name ------------------------>"+name);
			return "commissionMenuCall";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		} finally {

		}

	}

	Commission commission = new Commission();

	BigDecimal totlsales1 = BigDecimal.valueOf(0);
	BigDecimal totalamount1 = BigDecimal.valueOf(0);

	public BigDecimal getTotlsales1() {
		return totlsales1;
	}

	public void setTotlsales1(BigDecimal totlsales1) {
		this.totlsales1 = totlsales1;
	}

	public BigDecimal getTotalamount1() {
		return totalamount1;
	}

	public void setTotalamount1(BigDecimal totalamount1) {
		this.totalamount1 = totalamount1;
	}

	public String commissionview() {
		logger.info("[commisionview()] --------------- inside commisionview() method() ------------------------");
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		totallist.clear();
		DemoController controller = null;
		ApplicationContext ctx = null;
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if (fromdate == null) {
				throw new Exception("*Please Choose the From Date");
			} else if (todate == null) {
				throw new Exception("*Please Choose the To Date");
			}
			commission.setFromdate(fromdate);
			commission.setTodate(todate);
			commission.setCategory(category);
			logger.debug("[commisionview()] --------------- values--->fromdate,todate,category ------------------------>"+fromdate+"-->"+todate+"-->"+category);
			setTotallist(controller.viewCommission(commission));
			setTotalamount1(commission.getTotalamount1());
			setTotlsales1(commission.getTotlsales1());
			if (totallist.size() > 0) {
				logger.debug("[commisionview()] --------------- final value size ------------------------>"+totallist.size());
				Validate = "";
				return "commissionForm1";
			} else {
				throw new Exception("No data found");
			}
		} catch (Exception i) {
			setValidate(i.getMessage());
			logger.debug("inside the exception" + i.getMessage());
			flag1 = "1";
			flag = "none";
			return "";
		}

	}

	BigDecimal totlamt = BigDecimal.valueOf(0);
	BigDecimal totlquan = BigDecimal.valueOf(0);
	BigDecimal cmst = BigDecimal.valueOf(0);
	BigDecimal totcms = BigDecimal.valueOf(0);

	public BigDecimal getTotlamt() {
		return totlamt;
	}

	public void setTotlamt(BigDecimal totlamt) {
		this.totlamt = totlamt;
	}

	public BigDecimal getTotlquan() {
		return totlquan;
	}

	public void setTotlquan(BigDecimal totlquan) {
		this.totlquan = totlquan;
	}

	public BigDecimal getCmst() {
		return cmst;
	}

	public void setCmst(BigDecimal cmst) {
		this.cmst = cmst;
	}

	public BigDecimal getTotcms() {
		return totcms;
	}

	public void setTotcms(BigDecimal totcms) {
		this.totcms = totcms;
	}

	public void quantitychange(ValueChangeEvent ve) {
		logger.info("[quantitychange()] --------------- inside quantitychange() method() ------------------------");
		try {
			String temp = "0";
			String invoice = ve.getComponent().getAttributes()
					.get("refference").toString();
			logger.debug("[quantitychange()] --------------- invoice ------------------------>"+invoice);
			productname = ve.getComponent().getAttributes().get("prodname")
					.toString();
			unitprice = new BigDecimal(ve.getComponent().getAttributes()
					.get("price1").toString());
			totalamount1 = new BigDecimal(ve.getComponent().getAttributes()
					.get("total").toString());
			String quantityy = (ve.getComponent().getAttributes().get("qty")
					.toString());
			logger.debug("[quantitychange()] --------------- Quantity,productname,rollid,totalamount ------------------------>"+quantity+"-->"+productname+"-->"+rollids+"-->"+totalamount1);
			int serial = Integer.parseInt(ve.getComponent().getAttributes()
					.get("serial").toString());
			logger.debug("[quantitychange()] --------------- serial,totalamount ------------------------>"+serial+"-->"+totalamount1);
			String commision = ve.getNewValue().toString();
			logger.debug("[quantitychange()] --------------- Commision ------------------------>"+commision);
			temp = ""
					+ (new BigDecimal(quantityy).multiply(new BigDecimal(ve
							.getNewValue().toString())));
			logger.debug("[quantitychange()] --------------- Total ------------------------>"+temp);
			Commission comm = new Commission();
			comm.setsNo("" + serial);
			comm.setOrderdate(ve.getComponent().getAttributes().get("odate")
					.toString());
			comm.setInvoiceno(invoice);
			comm.setCname(ve.getComponent().getAttributes().get("cname")
					.toString());
			comm.setProductname(productname);
			comm.setRollids(rollids);
			comm.setUnitprice(unitprice);
			comm.setTotlamt(new BigDecimal(ve.getComponent().getAttributes()
					.get("tsalary").toString()));
			comm.setTotlquan(new BigDecimal(quantityy));
			comm.setCmst(new BigDecimal(commision));
			comm.setTotcms(new BigDecimal(temp));
			comm.setTotalSaleAmount(ve.getComponent().getAttributes()
					.get("tsalary").toString());
			comm.setQuantity("" + quantityy);
			comm.setFlag1("1");
			comm.setFlag("none");
			totallist.set((serial - 1), comm);
			setTotalamount1(totalamount1.add(new BigDecimal(temp)));
			controller.commisionUpdate(comm);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
	}

	public String edit() {
		logger.info("[edit()] --------------- inside edit() method() ------------------------");
		try {
			Commission comm = new Commission();
			comm.setsNo(sNo);
			comm.setOrderdate(orderdate);
			comm.setInvoiceno(invoiceno);
			comm.setCname(cname);
			comm.setProductname(productname);
			comm.setRollids(rollids);
			comm.setUnitprice(unitprice);
			comm.setTotlamt(totlamt);
			comm.setTotlquan(totlquan);
			comm.setCmst(cmst);
			comm.setTotalamount1(totalamount1);
			comm.setTotcms(BigDecimal.ZERO);
			comm.setFlag1("none");
			comm.setFlag("1");
			totallist.set((Integer.parseInt(sNo) - 1), comm);
			setTotalamount1(totalamount1.subtract(totcms));
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public String dispatch() {
		logger.info("[dispatch()] --------------- inside dispatch() method() ------------------------");
		DemoController controller = null;
		ApplicationContext ctx = null;
		try {
			soNumber = "";
			setValidate(null);
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setSoNumberList(controller.getSONfordispatch());
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		} finally {

		}

	}

	List<CommissionFormMB> as = new ArrayList<CommissionFormMB>();

	public List<CommissionFormMB> getAs() {
		return as;
	}

	public void setAs(List<CommissionFormMB> as) {
		this.as = as;
	}

	public String rollquan;
	public String rollunit;

	public String getRollquan() {
		return rollquan;
	}

	public void setRollquan(String rollquan) {
		this.rollquan = rollquan;
	}

	public String getRollunit() {
		return rollunit;
	}

	public void setRollunit(String rollunit) {
		this.rollunit = rollunit;
	}

	public String dispatchview() {
		logger.info("[dispatchview()] --------------- inside dispatchview() method() ------------------------");
		setVehicle1(null);
		vehicle2 = null;
		DemoController controller = null;
		ApplicationContext ctx = null;
		PurchaseOrder dom = new PurchaseOrder();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if (soNumber.equalsIgnoreCase("")) {
				throw new DemoException("Please Choose the Sales Order Number");
			}
			setAs(controller.getDispatchData(soNumber, dom));
			// purchaseOrder.getUnit()
			if (as.size() > 0) {
				dispatchNo = as.get(0).getDispatchNo();
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
				String strDate = sdf.format(d);
				tDate = strDate;
				comList = as.get(0).getComList();
				address = as.get(0).getAddress();
				setTotRoll(comList.get(0).getTotRoll());
				setTotQuan(dom.getQuantity());
			} else {
				throw new DemoException("No data found");
			}
			return "dispatchview1";
		} catch (DemoException e) {
			setValidate(e.getMessage());
			return "";
		} catch (Exception a) {
			a.printStackTrace();
			return "";

		}

	}

	public String soNumber;
	public List<String> soNumberList;
	public String dispatchNo;
	SimpleDateFormat smd = new SimpleDateFormat("MM-dd-yyyy");
	public String tDate;
	public String productName;
	public String uPrice;
	public List<CommissionFormMB> comList = new ArrayList<CommissionFormMB>();

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getuPrice() {
		return uPrice;
	}

	public void setuPrice(String uPrice) {
		this.uPrice = uPrice;
	}

	public List<CommissionFormMB> getComList() {
		return comList;
	}

	public void setComList(List<CommissionFormMB> comList) {
		this.comList = comList;
	}

	public String getDispatchNo() {
		return dispatchNo;
	}

	public void setDispatchNo(String dispatchNo) {
		this.dispatchNo = dispatchNo;
	}

	public String gettDate() {
		return tDate;
	}

	public void settDate(String tDate) {
		this.tDate = tDate;
	}

	public List<String> getSoNumberList() {
		return soNumberList;
	}

	public void setSoNumberList(List<String> soNumberList) {
		this.soNumberList = soNumberList;
	}

	public String getSoNumber() {
		return soNumber;
	}

	public void setSoNumber(String soNumber) {
		this.soNumber = soNumber;
	}

	private String vehicle1;
	private String vehicle2;

	public String getVehicle1() {
		return vehicle1;
	}

	public void setVehicle1(String vehicle1) {
		this.vehicle1 = vehicle1;
	}

	public String getVehicle2() {
		return vehicle2;
	}

	public void setVehicle2(String vehicle2) {
		this.vehicle2 = vehicle2;
	}

	public void update(ValueChangeEvent ve) {
		logger.info("[update()] --------------- inside update() method() ------------------------");
		DemoController controller = null;
		ApplicationContext ctx = null;
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.updateDispatch(dispatchNo, ve.getNewValue().toString(),
					vehicle2);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
	}

	public void update1(ValueChangeEvent ve) {
		logger.info("[update1()] --------------- inside update1() method() ------------------------");
		DemoController controller = null;
		ApplicationContext ctx = null;
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.updateDispatch(dispatchNo, vehicle1, ve.getNewValue()
					.toString());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
	}

	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public CommissionFormMB() {
		sortsOrders = new HashMap<String, SortOrder>();
		sortPriorities = new ArrayList<String>();
	}

	public void sort() {
		logger.info("[sort()] --------------- inside sort() method() ------------------------");
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

	// prema begin 29/04/2016 dialog box creation for purchase order
	public void dispatchviews() {
		logger.info("[dispatchviews()] --------------- inside dispatchviews() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("dispatch", options,
				null);
		dispatch();
	}

	// prema end 29/0/2016
	public void dispatchviewclose() {
		logger.info("[dispatchviewclose()] --------------- inside dispatchviewclose() method() ------------------------");
		RequestContext.getCurrentInstance().closeDialog("dispatch");
	}
}
