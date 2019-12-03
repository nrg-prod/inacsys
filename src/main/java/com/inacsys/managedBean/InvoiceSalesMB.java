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
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
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

@ManagedBean(name = "invoiceSalesMB")
public class InvoiceSalesMB {
	@ManagedProperty(value = "#{salesViewMB}")
	SalesViewMB salesViewMB;

	public SalesViewMB getSalesViewMB() {
		return salesViewMB;
	}

	public void setSalesViewMB(SalesViewMB salesViewMB) {
		this.salesViewMB = salesViewMB;
	}

	private static Logger logger = Logger.getLogger(InvoiceSalesMB.class);
	private static final List<PurchaseOrder> NullPointerException = null;
	List<I0021> result;
	public String salereferencenumber;
	public String flag = "none";
	public String customername;
	public String countryID;
	public Date salesorderdate;
	public String shipingaddress;
	public String phonenumber;
	public String email;
	public String totalnumberofcount;
	public String totalnumberofcount1;
	public Date deliverydate;
	public String note;
	public String shipping_charge;
	public String crosstotal;
	public String shipping_charge1;
	public String crosstotal1;
	public int salesId;
	List<I0019> resul;
	public String validate;
	ArrayList<I0021> sales = null;
	List<PurchaseOrder> resulfinal = null;
	public int serialNo;
	public String tDate;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public String gettDate() {
		return tDate;
	}

	public void settDate(String tDate) {
		this.tDate = tDate;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public List<PurchaseOrder> getResulfinal() {
		return resulfinal;
	}

	public void setResulfinal(List<PurchaseOrder> resulfinal) {
		this.resulfinal = resulfinal;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCountryID() {
		return countryID;
	}

	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}

	public Date getSalesorderdate() {
		return salesorderdate;
	}

	public void setSalesorderdate(Date salesorderdate) {
		this.salesorderdate = salesorderdate;
	}

	public String getShipingaddress() {
		return shipingaddress;
	}

	public void setShipingaddress(String shipingaddress) {
		this.shipingaddress = shipingaddress;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTotalnumberofcount() {
		return totalnumberofcount;
	}

	public void setTotalnumberofcount(String totalnumberofcount) {
		this.totalnumberofcount = totalnumberofcount;
	}

	public String getTotalnumberofcount1() {
		return totalnumberofcount1;
	}

	public void setTotalnumberofcount1(String totalnumberofcount1) {
		this.totalnumberofcount1 = totalnumberofcount1;
	}

	public Date getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getShipping_charge() {
		return shipping_charge;
	}

	public void setShipping_charge(String shipping_charge) {
		this.shipping_charge = shipping_charge;
	}

	public String getCrosstotal() {
		return crosstotal;
	}

	public void setCrosstotal(String crosstotal) {
		this.crosstotal = crosstotal;
	}

	public String getShipping_charge1() {
		return shipping_charge1;
	}

	public void setShipping_charge1(String shipping_charge1) {
		this.shipping_charge1 = shipping_charge1;
	}

	public String getCrosstotal1() {
		return crosstotal1;
	}

	public void setCrosstotal1(String crosstotal1) {
		this.crosstotal1 = crosstotal1;
	}

	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
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

	public ArrayList<I0021> getSales() {
		return sales;
	}

	public void setSales(ArrayList<I0021> sales) {
		this.sales = sales;
	}

	public List<I0019> getResul() {
		return resul;
	}

	public void setResul(List<I0019> resul) {
		this.resul = resul;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	DemoController controller = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();

	public String invoiceSalesDirect() {
		logger.info("[invoiceSalesDirect()] --------------- Inside invoiceSalesDirect() method() ------------------------");
		try {
			setValidate(null);
			setFlag("none");
			setSalereferencenumber(null);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesDelete();
		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
		}
		return "";
	}

	public String invoiceSales() {
		logger.info("[invoiceSales()] --------------- Inside invoiceSales() method() ------------------------");
		try {
			flag = "none";
			setValidate("");
			logger.debug("[invoiceSales()] --------------- salereferencenumber ------------------------"+salereferencenumber);
			purchaseOrder.setSalesIdReference(salesViewMB.salereferencenumber);
			purchaseOrder.setResul(resul);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.invoiceSales(purchaseOrder);
			controller.salesOrdercancelForm3(purchaseOrder);
			setResul(purchaseOrder.getResul());
			setResult(purchaseOrder.getResult());
			flag = "1";
			return "";
		} catch (DemoException e) {
			setValidate(e.getMessage());
			logger.error(e.getMessage());
			return "";
		}
	}

	public String invoiceSalesDrop() {
		logger.info("[invoiceSalesDrop()] --------------- Inside invoiceSalesDrop() method() ------------------------");
		try {
			purchaseOrder.setSalesIdReference(salereferencenumber);
			purchaseOrder.setResul(resul);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			sales = controller.invoiceSales(purchaseOrder, sales);
			if (sales.size() > 0) {
				logger.info("[invoiceSalesDrop()] --------------- Inside invoiceSalesDrop() method() if condition------------------------");
			}
			return "";
		} catch (DemoException e) {
			logger.error(e.getMessage());
			return "";
		}
	}
	
	public String redirect() { 
		ApplicationContext ctx=null;BigDecimal bb=null;
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			flag = "none";int i = 0;
			purchaseOrder.setSalesIdReference(salesViewMB.salereferencenumber);
			purchaseOrder.setOrderDate(salesViewMB.getOdate());
			purchaseOrder.setDueDate(salesViewMB.getTardate()); 
			purchaseOrder.setCustomername(salesViewMB.getCustomerName()); 
			purchaseOrder.setCurrency(salesViewMB.getCurrency()); 
			purchaseOrder.setResul(resul);
			purchaseOrder.setClientID(clientID);
			purchaseOrder.setCurrencyAmount(salesViewMB.getCurrencyAmount());
			purchaseOrder.setSalesId(salesViewMB.getSalesId());
			purchaseOrder.setResulfinal(salesViewMB.getResulfinal()); 
			purchaseOrder.setBaseCurrency(salesViewMB.getBaseCurrency());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrderViewproduct(purchaseOrder);
			if (purchaseOrder.getResulfinal() == NullPointerException) {
				throw new DemoException("*This order number did purchase any product");
			}
			setSerialNo(purchaseOrder.getSerialNo());
			resulfinal = purchaseOrder.getResulfinal();
			controller.invoiceSales1(purchaseOrder);
			bb = BigDecimal.valueOf(0);
			if (resulfinal.size() > 0) {
				for (int j = 0; j < resulfinal.size(); j++) {
					resulfinal.get(j).setSellingPrice(""+ (new BigDecimal(resulfinal.get(j).getSellingPrice())));
					resulfinal.get(j).setTotalPrice(""+ (new BigDecimal(resulfinal.get(j).getTotalPrice())));
					bb = (bb.add(new BigDecimal(resulfinal.get(j).getTotalPrice()).add(new BigDecimal(purchaseOrder.getShipping_charge()))));
				}
			}
			setResult(purchaseOrder.getResult());
			setTotalnumberofcount("" + resulfinal.size());
			setTotalnumberofcount1("" + bb);
			flag = "1";
			salereferencenumber = result.get(i).getSalesOrderNumber();
			customername = result.get(i).getCustomerName();
			shipingaddress = result.get(i).getShipingAddress();
			deliverydate = result.get(i).getDeliveryDate();
			tDate = sdf.format(Calendar.getInstance().getTime());
			email = result.get(i).getEMail();
			crosstotal1 = result.get(i).getCrossTotal();
			phonenumber = result.get(i).getPhoneNumber();
			return "";
		} catch (DemoException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			flag = "none";
			int i = 0;
			if (purchaseOrder.getResulfinal() == NullPointerException) {
				setValidate("*This order number did purchase any product");
			}
			resulfinal = purchaseOrder.getResulfinal();
			salereferencenumber = result.get(i).getSalesOrderNumber();
			customername = result.get(i).getCustomerName();
			shipingaddress = result.get(i).getShipingAddress();
			deliverydate = result.get(i).getDeliveryDate();
			email = result.get(i).getEMail();
			crosstotal1 = result.get(i).getCrossTotal();
			phonenumber = result.get(i).getPhoneNumber();
			setValidate(e.getMessage());
			setResult(purchaseOrder.getResult());
			return "failure";
		}
	}

	public String invoiceSales1() {
		logger.info("[invoiceSales1()] --------------- Inside invoiceSales1() method() ------------------------");
		try {
			flag = "none";
			purchaseOrder.setSalesIdReference(salereferencenumber);
			purchaseOrder.setResul(resul);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrdercancelForm(purchaseOrder);
			setResul(purchaseOrder.getResul());
			return "";
		} catch (DemoException e) {
			logger.error(e.getMessage());
			return "";
		}
	}

	// prema begin 02/05/2016 dialog box creation for sales invoice

	public void salesinvoice() {
		logger.info("[salesinvoice()] --------------- Inside salesinvoice() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);

		RequestContext.getCurrentInstance().openDialog("invoiceSales", options,
				null);
		invoiceSalesDirect();
	}

	// prema end 02/05/2016
	public void salesinvclose() {
		RequestContext.getCurrentInstance().closeDialog("invoiceSales");
	}

}
