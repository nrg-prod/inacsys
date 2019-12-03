package com.inacsys.managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.SortOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0018;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0021;
import com.inacsys.shared.SalesRecord;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "salesReturnFormMB")
public class SalesReturnFormMB implements Serializable {

	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(SalesReturnFormMB.class);
	@ManagedProperty(value = "#{salesViewMB}")
	SalesViewMB salesViewMB;

	public SalesViewMB getSalesViewMB() {
		return salesViewMB;
	}

	public void setSalesViewMB(SalesViewMB salesViewMB) {
		this.salesViewMB = salesViewMB;
	}

	private static final long serialVersionUID = 1L;

	public String customername;
	public String countryID;
	public Date salesorderdate;
	public String shipingaddress;
	public String phonenumber;
	public String email;
	public String totalnumberofcount;
	public String totalnumberofcount1;
	public String quantity;
	public String sellingPrice;
	public String salesIdReference;
	public Date deliverydate;
	public String note;
	public String shipping_charge;
	public String crosstotal;
	public String shipping_charge1;
	public String crosstotal1;
	public String saleId;
	public int salesId;
	public String batchProductName;
	public String batchProductName1;
	public int productId;
	List<I0018> batchProductName3;
	public String marginPrice;
	public int batchID;
	List<I0019> resul;
	public String s = "cross Total Of ";
	public String crosstotal2;
	public String flag1 = "none";
	public String flag2 = "none";
	public String flag3 = "none";
	public String quantity1;
	public String validate1;
	public String validate2;
	public Date returndate;
	private String rollID;
	List<SalesRecord> resul1;
	BigDecimal quans = BigDecimal.valueOf(0);

	public BigDecimal getQuans() {
		return quans;
	}

	public void setQuans(BigDecimal quans) {
		this.quans = quans;
	}

	public String getRollID() {
		return rollID;
	}

	public void setRollID(String rollID) {
		this.rollID = rollID;
	}

	public List<SalesRecord> getResul1() {
		return resul1;
	}

	public void setResul1(List<SalesRecord> resul1) {
		this.resul1 = resul1;
	}

	public Date getReturndate() {
		return returndate;
	}

	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}

	public String getValidate2() {
		return validate2;
	}

	public void setValidate2(String validate2) {
		this.validate2 = validate2;
	}

	public String getValidate1() {
		return validate1;
	}

	public void setValidate1(String validate1) {
		this.validate1 = validate1;
	}

	public String getQuantity1() {
		return quantity1;
	}

	public void setQuantity1(String quantity1) {
		this.quantity1 = quantity1;
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

	List<I0021> result;

	public List<I0021> getResult() {
		return result;
	}

	public void setResult(List<I0021> result) {
		this.result = result;
	}

	public String getFlag3() {
		return flag3;
	}

	public void setFlag3(String flag3) {
		this.flag3 = flag3;
	}

	public String getCrosstotal2() {
		return crosstotal2;
	}

	public void setCrosstotal2(String crosstotal2) {
		this.crosstotal2 = crosstotal2;
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public List<I0019> getResul() {
		return resul;
	}

	public void setResul(List<I0019> resul) {
		this.resul = resul;
	}

	public String getSalesIdReference() {
		return salesIdReference;
	}

	public void setSalesIdReference(String salesIdReference) {
		this.salesIdReference = salesIdReference;
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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
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

	public String getBatchProductName() {
		return batchProductName;
	}

	public void setBatchProductName(String batchProductName) {
		this.batchProductName = batchProductName;
	}

	public String getBatchProductName1() {
		return batchProductName1;
	}

	public void setBatchProductName1(String batchProductName1) {
		this.batchProductName1 = batchProductName1;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public List<I0018> getBatchProductName3() {
		return batchProductName3;
	}

	public void setBatchProductName3(List<I0018> batchProductName3) {
		this.batchProductName3 = batchProductName3;
	}

	public String getMarginPrice() {
		return marginPrice;
	}

	public void setMarginPrice(String marginPrice) {
		this.marginPrice = marginPrice;
	}

	public int getBatchID() {
		return batchID;
	}

	public void setBatchID(int batchID) {
		this.batchID = batchID;
	}

	public String validate;

	public String getValidate() {
		return validate;
	}

	public String productName;
	ArrayList<I0021> result1 = null;

	public ArrayList<I0021> getResult1() {
		return result1;
	}

	public void setResult1(ArrayList<I0021> result1) {
		this.result1 = result1;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String salesReturn1() {
		logger.info("[salesReturn1()]-------------------inside salesReturn() method()---------------");
		try {
			setS1(null);
			setSalesIdReference(null);
			setFlag1("none");
			setFlag2("none");
			setFlag3("none");
			setReason(null);
			setSaleId(null);
			setProductName(null);
			setQuantity1(null);
			setValidate(null);
			setReturndate(null);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesDelete();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}

		return "salesReturn1";
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	DemoController controller = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();
	ArrayList<I0021> result2 = null;

	public ArrayList<I0021> getResult2() {
		return result2;
	}

	public void setResult2(ArrayList<I0021> result2) {
		this.result2 = result2;
	}

	public String salereferencenumber;

	public String getSalereferencenumber() {
		return salereferencenumber;
	}

	public void setSalereferencenumber(String salereferencenumber) {
		this.salereferencenumber = salereferencenumber;
	}

	public String salesReturn() {
		logger.info("[salesReturn()]-------------------inside salesReturn() method()---------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesReturnForm3(purchaseOrder);
			return "home";
		}

		catch (Exception ie) {
			logger.error("Inside Exception", ie);
			logger.debug(ie.getStackTrace());
			return "home";
		}

	}

	public String salesReturnDamage() {
		logger.info("[salesReturnDamage()]-------------------inside salesReturnDamage() method()---------------");
		try {

			if (productName.equals("")) {
				throw new DemoException("*Select One Product");
			} else if (quantity1.equals("")) {
				throw new DemoException("*Enter the Quantity");
			} else if (reason.equalsIgnoreCase("")) {
				throw new DemoException("*Enter Reason for Return");
			} else if (returndate == null) {
				throw new DemoException("*Enter the Date");
			}
			setValidate2("");
			purchaseOrder.setProduct_name(productName);
			purchaseOrder.setReason(reason);
			purchaseOrder.setReturnDate(returndate);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			for (int i = 0; i < resulfinal1.size(); i++) {
				if (productName.equals(resulfinal1.get(i).getProduct_name())) {
					if (new BigDecimal(quantity1).compareTo(new BigDecimal(
							resulfinal1.get(i).getQuantity())) <= 0) {
						controller.partialdamagereturn(purchaseOrder,
								Integer.parseInt(quantity1));
						for (int j = 0; j < Integer.parseInt(quantity1); j++) {
							controller.salesReturnForm(purchaseOrder);
						}
					} else {
						throw new DemoException("*the quantity you enter is above");
					}
				}
			}

			return "home";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			flag1 = "none";
			setValidate2(e.getMessage());
			logger.debug(e.getMessage());
			return "";

		}

		catch (Exception ie) {
			logger.error("Inside Exception", ie);
			logger.debug(ie.getStackTrace());
			return "home";
		} finally {
			
		}

	}

	public String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String salesReturnNormal() {
		logger.info("[salesReturnNormal()]-------------------inside salesReturnNormal() method()---------------");
		try {
			setValidate("");
			if (productName.equals("")) {
				throw new DemoException("*Select one Product");
			} else if (quantity1.equals("")) {
				throw new DemoException("*Enter the Quantity");
			} else if (reason.equalsIgnoreCase("")) {
				throw new DemoException("*Enter Reason for Return");
			} else if (returndate == null) {
				throw new DemoException("*Enter the Date");
			}
			setValidate1("");
			purchaseOrder.setProduct_name(productName);
			purchaseOrder.setReason(reason);
			purchaseOrder.setReturnDate(returndate);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			for (int i = 0; i < resulfinal1.size(); i++) {
				if (productName.equals(resulfinal1.get(i).getProduct_name())) {
					if (new BigDecimal(quantity1).compareTo(new BigDecimal(
							resulfinal1.get(i).getQuantity())) <= 0) {
						controller.partialnormalreturn(purchaseOrder,
								Integer.parseInt(quantity1));
						for (int j = 0; j < Integer.parseInt(quantity1); j++) {
							controller.salesReturnForm3(purchaseOrder);
						}
					} else {
						throw new DemoException(
								"*the quantity you enter is above");
					}
				}
			}

			return "home";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			flag1 = "none";
			setValidate1(e.getMessage());
			return "";

		}

		catch (Exception ie) {
			logger.error("Inside Exception", ie);
			logger.debug(ie.getStackTrace());
			return "home";
		} finally {
			
		}

	}

	public void changeDrop(ValueChangeEvent v) {
		logger.info("[changeDrop()]-------------------inside changeDrop() method()---------------");
		try {

			validate = null;
			String s = (String) v.getNewValue();
			purchaseOrder.setValueChange(s);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.changeDrop1(purchaseOrder);
			result2 = purchaseOrder.getResult2();

		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			flag1 = "none";
			setValidate("");
			logger.debug(e.getMessage());

		}

	}

	List<PurchaseOrder> resulfinal1 = null;

	public List<PurchaseOrder> getResulfinal1() {
		return resulfinal1;
	}

	public void setResulfinal1(List<PurchaseOrder> resulfinal1) {
		this.resulfinal1 = resulfinal1;
	}

	public String customerName;
	public String address;
	public String telephonenumber;

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

	ArrayList<String> s1 = new ArrayList<String>();

	public ArrayList<String> getS1() {
		return s1;
	}

	public void setS1(ArrayList<String> s1) {
		this.s1 = s1;
	}

	public String salesView2() {
		logger.info("[salesView2()]-------------------inside salesView2() method()---------------");
		ArrayList<String> s2 = null;
		try {

			resulfinal1 = null;
			resul = null;
			validate = null;

			purchaseOrder.setSalesIdReference(salereferencenumber);
			purchaseOrder.setResul1(resul1);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrderViewproduct(purchaseOrder);
			resulfinal1 = purchaseOrder.getResulfinal();
			resul1 = purchaseOrder.getResul1();

			if (result.size() == 0) {
				flag1 = "none";
				throw new DemoException("This date DidnOt hava Any Sales>>>>>");
			}
			else {
				int i = 0;
				s2 = new ArrayList<String>();
				for (PurchaseOrder ii : resulfinal1) {
					SalesReturnFormMB salesReturnFormMB = new SalesReturnFormMB();
					salesReturnFormMB.setProductName(resulfinal1.get(i)
							.getProduct_name());
					s2.add(salesReturnFormMB.productName);
					i++;
				}
				s1 = s2;

				flag1 = "1";
				setCustomerName(purchaseOrder.getCustomerName());
				setTelephonenumber(purchaseOrder.getTelephonenumber());
				setAddress(purchaseOrder.getAddress());
			}

			return "success1";
		}

		catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "failure";
		} catch (Exception ie) {
			setValidate("*This oderNumber Did not purschase product");
			logger.error("Inside Exception", ie);
			return "";
		}

	}

	public String saleReturn() {
		logger.info("[salesReturn()]-------------------inside salesReturn() method()---------------");
		ArrayList<String> s2 = null;
		resulfinal1 = null;
		resul = null;
		validate = null;
		try {
			purchaseOrder.setSalesIdReference(salereferencenumber);
			purchaseOrder.setResul1(resul1);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrderViewproduct(purchaseOrder);
			resulfinal1 = purchaseOrder.getResulfinal();
			resul1 = purchaseOrder.getResul1();
			if (result.size() == 0) {
				flag1 = "none";
				throw new DemoException("*This date didnot have any sales");
			}

			else {
				int i = 0;
				s2 = new ArrayList<String>();
				for (PurchaseOrder ii : resulfinal1) {
					SalesReturnFormMB salesReturnFormMB = new SalesReturnFormMB();
					salesReturnFormMB.setProductName(resulfinal1.get(i)
							.getProduct_name());
					s2.add(salesReturnFormMB.productName);
					i++;

				}
				s1 = s2;
				flag1 = "1";
				setCustomerName(purchaseOrder.getCustomerName());
				setTelephonenumber(purchaseOrder.getTelephonenumber());
				setAddress(purchaseOrder.getAddress());
			}

			return "success3";
		}

		catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "failure";
		} catch (Exception ie) {
			setValidate("*This odernumber did not purschase product");
			logger.error("Inside Exception", ie);
			return "";
		}

	}

	List<String> resulfinal = null;

	public List<String> getResulfinal() {
		return resulfinal;
	}

	public void setResulfinal(List<String> resulfinal) {
		this.resulfinal = resulfinal;
	}

	public String salesReturnDrop() {
		logger.info("[salesReturnDrop()]-------------------inside salesReturnDrop() method()---------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesDrop1(purchaseOrder);
			setResulfinal(purchaseOrder.getResulfinal1());
			List<String> templist = new ArrayList<String>();
			HashSet<String> hashlist1 = new HashSet<String>(resulfinal);
			for (String a : hashlist1) {
				templist.add(a);
			}
			setResulfinal(templist);

			return "";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			logger.debug(e.getMessage());
			return "";
		}

	}

	public String salesReturnForm() {
		logger.info("[salesReturn()]-------------------inside salesReturn() method()---------------");
		try {
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			validate = null;
			crosstotal1 = "0";
			setValidate1("");
			purchaseOrder.setCustomername(customername);
			purchaseOrder.setSalesorderdate(salesorderdate);
			purchaseOrder.setShipingaddress(shipingaddress);
			purchaseOrder.setPhonenumber(phonenumber);
			purchaseOrder.setEmail(email);
			purchaseOrder.setTotalnumberofcount(totalnumberofcount);
			purchaseOrder.setCrosstotal(crosstotal);
			purchaseOrder.setShipping_charge(shipping_charge);
			purchaseOrder.setTotalnumberofcount1("" + totalnumberofcount1);
			purchaseOrder.setDeliverydate(deliverydate);
			purchaseOrder.setShipping_charge1("" + shipping_charge1);
			purchaseOrder.setNote(note);
			purchaseOrder.setCrosstotal1("" + crosstotal1);
			purchaseOrder.setSalesId(salesId);
			purchaseOrder.setSalesIdReference(salesIdReference);
			purchaseOrder.setResul1(resul1);
			if (salesIdReference.equalsIgnoreCase("")) {
				throw new DemoException("*Enter the Sales order Number");
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrdercancelFormsub1(purchaseOrder);
			setResul1(purchaseOrder.getResul1());
			setResult(purchaseOrder.getResult());
			if (resul1.size() == 0) {
				throw new DemoException(
						"*This order number didnot purchase any Product");
			}
			flag1 = "1";
			flag3 = "1";
			setCustomername(purchaseOrder.getResul1().get(0).getI0019()
					.getI0018().getProductName());
			setCrosstotal1(purchaseOrder.getResul1().get(0).getI0021()
					.getCrossTotal());
			return "";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "";
		}

	}

	public String salesReturnForm3() {
		logger.info("[salesReturnForm3()]-------------------inside salesReturnForm3() method()---------------");
		try {
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			crosstotal1 = "0";
			setValidate2("");
			purchaseOrder.setCustomername(customername);
			purchaseOrder.setSalesorderdate(salesorderdate);
			purchaseOrder.setShipingaddress(shipingaddress);
			purchaseOrder.setPhonenumber(phonenumber);
			purchaseOrder.setEmail(email);
			purchaseOrder.setTotalnumberofcount(totalnumberofcount);
			purchaseOrder.setCrosstotal(crosstotal);
			purchaseOrder.setShipping_charge(shipping_charge);
			purchaseOrder.setTotalnumberofcount1("" + totalnumberofcount1);
			purchaseOrder.setDeliverydate(deliverydate);
			purchaseOrder.setShipping_charge1("" + shipping_charge1);
			purchaseOrder.setNote(note);
			purchaseOrder.setCrosstotal1("" + crosstotal2);
			purchaseOrder.setSalesId(salesId);
			purchaseOrder.setSalesIdReference(saleId);
			purchaseOrder.setResul1(resul1);
			if (saleId.equalsIgnoreCase("")) {
				throw new DemoException("*Enter the Sales order Number");
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrdercancelForm(purchaseOrder);
			setResul1(purchaseOrder.getResul1());
			setResult(purchaseOrder.getResult());
			if (resul1.size() == 0) {
				throw new DemoException(
						"*This order number didnot purchase any Product");
			}
			setCustomername(purchaseOrder.getResul1().get(0).getI0019()
					.getI0018().getProductName());
			flag2 = "1";
			setCrosstotal2(purchaseOrder.getResul1().get(0).getI0021()
					.getCrossTotal());
			return "";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			setValidate(e.getMessage());
			return "";
		}
	}

	public String salesReturnForm1() {
		logger.info("[salesReturnForm1()]-------------------inside salesReturnForm1() method()---------------");
		try {
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			if (purchaseOrder.getResul1().size() == 0) {
				throw new DemoException("wrong.....enter the sales order number");
			}
			return "success";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			logger.debug(e.getMessage());
			return "failure";
		}
	}

	public String salesReturnForm4() {
		logger.info("[salesReturnForm4()]-------------------inside salesReturnForm4() method()---------------");
		try {
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			if (purchaseOrder.getResul1().size() == 0) {
				throw new DemoException(
						"wrong.....enter the sales order number");
			}
			return "success2";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "failure2";
		}
	}

	public String salesReturnForm2() {
		logger.info("[salesReturnForm2()]-------------------inside salesReturnForm2() method()---------------");
		try {
			if (reason.equalsIgnoreCase("")) {
				throw new DemoException("Enter Reason for Return the Order");
			} else if (returndate == null) {
				throw new DemoException("Enter the Date");
			}
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setReason(reason);
			purchaseOrder.setReturnDate(returndate);
			controller.salesReturnForm2(purchaseOrder);
			return "home";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			return "failure1";
		} finally {
			resul = null;
			purchaseOrder.setResul(null);
		}
	}

	public String salesReturnForm5() {
		logger.info("[salesReturnForm5()]-------------------inside salesReturnForm5() method()---------------");
		try {
			if (reason.equalsIgnoreCase("")) {
				throw new DemoException("Enter Reason for Return the Order");
			} else if (returndate == null) {
				throw new DemoException("*Enter the Date");
			}
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setReason(reason);
			purchaseOrder.setReturnDate(returndate);
			controller.salesReturnForm5(purchaseOrder);
			return "home";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			return "";
		} finally {
			resul = null;
			purchaseOrder.setResul(null);

		}
	}

	public String priroty = "false";
	public String nr;
	public String dr;
	public String tempFlag1 = "none";
	public String tempFlag2 = "none";
	public String serialno;
	public String flag4 = "none";
	public String tempValidate;
	public Date returnDate;

	public String getPriroty() {
		return priroty;
	}

	public void setPriroty(String priroty) {
		this.priroty = priroty;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
	}

	public String getTempFlag1() {
		return tempFlag1;
	}

	public void setTempFlag1(String tempFlag1) {
		this.tempFlag1 = tempFlag1;
	}

	public String getTempFlag2() {
		return tempFlag2;
	}

	public void setTempFlag2(String tempFlag2) {
		this.tempFlag2 = tempFlag2;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getTempValidate() {
		return tempValidate;
	}

	public void setTempValidate(String tempValidate) {
		this.tempValidate = tempValidate;
	}

	public String getFlag4() {
		return flag4;
	}

	public void setFlag4(String flag4) {
		this.flag4 = flag4;
	}

	public String salesReturnMenu() {
		logger.info("[salesReturnMenu()]-------------------inside salesReturnMenu() method()---------------");
		setCustomername("");
		setReturnDate(null);
		setSalereferencenumber(null);
		setValidate(null);
		setFlag4("none");

		setFlags("none");
		return "";

	}

	public String salesReturnviewForm() {
		logger.info("[salesReturnForm()]-------------------inside salesReturnForm() method()---------------");
		fromdate = null;
		todate = null;
		flag = "none";
		validate = null;
		return "salesreturnViewPage";
	}

	BigDecimal rollss = BigDecimal.valueOf(0);

	public BigDecimal getRollss() {
		return rollss;
	}

	public void setRollss(BigDecimal rollss) {
		this.rollss = rollss;
	}

	public String flags;

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	BigDecimal sq = BigDecimal.valueOf(0);
	BigDecimal ss = BigDecimal.valueOf(0);

	public BigDecimal getSq() {
		return sq;
	}

	public void setSq(BigDecimal sq) {
		this.sq = sq;
	}

	public BigDecimal getSs() {
		return ss;
	}

	public void setSs(BigDecimal ss) {
		this.ss = ss;
	}

	

	public String salesReturnView() {
		logger.info("[salesReturnView()]-------------------inside salesReturnView() method()---------------");
		ArrayList<String> s2 = null;
		try {
			setSq(BigDecimal.valueOf(0));
			setSs(BigDecimal.valueOf(0));
			tempValidate = "";
			resulfinal1 = null;
			flag4 = "none";
			flags = "none";
			logger.debug("----------------inside salesReturnView--------------------");
			validate = "";
			rollss = BigDecimal.valueOf(0);
			purchaseOrder.setSalesId(0);
			purchaseOrder.setSalesIdReference(salesViewMB.salereferencenumber);
			purchaseOrder.setResul(resul);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesRecordView(purchaseOrder);
			resulfinal1 = purchaseOrder.getResulfinal();
			setNote(purchaseOrder.getStatus());
			purchaseOrder.setStatus(salesViewMB.getStatus());
			if (purchaseOrder.getStatus().equalsIgnoreCase("Delivered")) {
				int s;
				s = resulfinal1.size();
				setRollss(new BigDecimal(s));
				int i = 0;
				s2 = new ArrayList<String>();
				for (PurchaseOrder ii : resulfinal1) {
					SalesReturnFormMB salesReturnFormMB = new SalesReturnFormMB();
					salesReturnFormMB.setProductName(resulfinal1.get(i)
							.getProduct_name());
					s2.add(salesReturnFormMB.productName);
					salesReturnFormMB.setQuans(new BigDecimal(resulfinal1
							.get(i).getQuantity()));
					ss = ss.add(salesReturnFormMB.getQuans());

					i++;
				}
				setSq(ss);
				flags = "1";
				s1 = s2;
				flag1 = "1";
				setCustomerName(purchaseOrder.getCustomerName());
				setTelephonenumber(purchaseOrder.getTelephonenumber());
				setAddress(purchaseOrder.getAddress());
				flag4 = "1";
			} else {
				RequestContext.getCurrentInstance().execute(
						"PF('salesreturn1').show();");

			}
			return "";

		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "";
		} catch (Exception ie) {
			setValidate("");
			logger.error("Inside Exception", ie);
			return "";
		}
	}

	public void checkBox(ValueChangeEvent v) {
		logger.info("[checkBox()]-------------------inside checkBox() method()---------------");
		tempValidate = "";
		if (("" + v.getNewValue()).equals("true")) {
			int s = (Integer) v.getComponent().getAttributes().get("serial");
			int index = s - 1;
			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("1");
			order.setTempFlag2("1");
			order.setPriroty("true");
			order.setQuantity((String) v.getComponent().getAttributes()
					.get("quant"));
			order.setProduct_name((String) v.getComponent().getAttributes()
					.get("pName"));
			order.setSerialNo((Integer) v.getComponent().getAttributes()
					.get("serial"));
			order.setRollID((String) v.getComponent().getAttributes()
					.get("rolls"));
			order.setUnit((String) v.getComponent().getAttributes().get("unit"));
			order.setNr("");
			order.setDr("");
			resulfinal1.set((index), order);
		} else {
			int s = (Integer) v.getComponent().getAttributes().get("serial");
			int index = s - 1;
			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("none");
			order.setTempFlag2("none");
			order.setPriroty("false");
			order.setQuantity((String) v.getComponent().getAttributes()
					.get("quant"));
			order.setProduct_name((String) v.getComponent().getAttributes()
					.get("pName"));
			order.setRollID((String) v.getComponent().getAttributes()
					.get("rolls"));
			order.setSerialNo((Integer) v.getComponent().getAttributes()
					.get("serial"));
			order.setNr("");
			order.setDr("");
			resulfinal1.set((index), order);
		}
	}

	public void nrChange(ValueChangeEvent v) {
		logger.info("[nrChange()]-------------------inside nrChange() method()---------------");
		int s = (Integer) v.getComponent().getAttributes().get("serial");
		int index = s - 1;
		tempValidate = "";
		try {
			float j = 0, i = 0;
			i = Float.parseFloat("" + v.getNewValue());
			try {
				j = Float.parseFloat((String) v.getComponent().getAttributes()
						.get("dr"));
			} catch (NumberFormatException e) {
				j = 0;
			}
			if ((i + j) > (Float.parseFloat(""
					+ (String) v.getComponent().getAttributes().get("quant")))) {
				throw new DemoException("Enter the Correct Quantity");
			}
			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("1");
			order.setTempFlag2("1");
			order.setPriroty(""
					+ (String) v.getComponent().getAttributes().get("pri"));
			order.setRollID((String) v.getComponent().getAttributes()
					.get("rolls"));
			order.setUnit((String) v.getComponent().getAttributes().get("unit"));
			order.setQuantity((String) v.getComponent().getAttributes()
					.get("quant"));
			order.setProduct_name((String) v.getComponent().getAttributes()
					.get("pName"));
			order.setSerialNo((Integer) v.getComponent().getAttributes()
					.get("serial"));
			order.setNr("" + v.getNewValue());
			order.setDr((String) v.getComponent().getAttributes().get("dr"));
			resulfinal1.set((index), order);
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			tempValidate = e.getMessage();
			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("none");
			order.setTempFlag2("none");
			order.setPriroty("false");
			order.setQuantity((String) v.getComponent().getAttributes()
					.get("quant"));
			order.setProduct_name((String) v.getComponent().getAttributes()
					.get("pName"));
			order.setRollID((String) v.getComponent().getAttributes()
					.get("rolls"));
			order.setUnit((String) v.getComponent().getAttributes().get("unit"));
			order.setSerialNo((Integer) v.getComponent().getAttributes()
					.get("serial"));
			order.setNr("0");
			order.setDr((String) v.getComponent().getAttributes().get("dr"));
			resulfinal1.set((index), order);
		}
	}

	public void drChange(ValueChangeEvent v) {
		logger.info("[drChange()]-------------------inside drChange() method()---------------");
		tempValidate = "";
		int s = (Integer) v.getComponent().getAttributes().get("serial");
		int index = s - 1;
		try {
			float j = 0, i = 0;
			i = Float.parseFloat("" + v.getNewValue());
			try {
				j = Float.parseFloat((String) v.getComponent().getAttributes()
						.get("nr"));
			} catch (NumberFormatException e) {
				j = 0;
			}
			if ((i + j) > (Float.parseFloat(""
					+ (String) v.getComponent().getAttributes().get("quant")))) {
				throw new DemoException("Enter the Correct Quantity");
			}
			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("1");
			order.setTempFlag2("1");
			order.setPriroty(""
					+ (String) v.getComponent().getAttributes().get("pri"));
			order.setQuantity((String) v.getComponent().getAttributes()
					.get("quant"));
			order.setProduct_name((String) v.getComponent().getAttributes()
					.get("pName"));
			order.setRollID((String) v.getComponent().getAttributes()
					.get("rolls"));
			order.setUnit((String) v.getComponent().getAttributes().get("unit"));
			order.setSerialNo((Integer) v.getComponent().getAttributes()
					.get("serial"));
			order.setNr((String) v.getComponent().getAttributes().get("nr"));
			order.setDr("" + v.getNewValue());
			resulfinal1.set((index), order);
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			tempValidate = e.getMessage();
			PurchaseOrder order = new PurchaseOrder();
			order.setTempFlag1("none");
			order.setTempFlag2("none");
			order.setPriroty("false");
			order.setQuantity((String) v.getComponent().getAttributes()
					.get("quant"));
			order.setProduct_name((String) v.getComponent().getAttributes()
					.get("pName"));
			order.setRollID((String) v.getComponent().getAttributes()
					.get("rolls"));
			order.setUnit((String) v.getComponent().getAttributes().get("unit"));
			order.setSerialNo((Integer) v.getComponent().getAttributes()
					.get("serial"));
			order.setNr((String) v.getComponent().getAttributes().get("nr"));
			order.setDr("0");
			resulfinal1.set((index), order);
		}
	}

	public String salesReturnSubmit() {
		logger.info("[salesReturnSubmit()]-------------------inside salesReturnSubmit() method()---------------");
		try {

			tempValidate = "";
			purchaseOrder.setResulfinal(resulfinal1);
			purchaseOrder.setTargentDate(salesViewMB.odate);
			purchaseOrder.setQuantity(quantity);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			int ii = 0;
			int cc = 0;
			for (ii = 0; ii < resulfinal1.size(); ii++) {
				if (resulfinal1.get(ii).getPriroty().equalsIgnoreCase("true")) {
					cc++;
				}
			}
			if (cc == 0) {
				throw new DemoException("*Enter the quantity to return");
			}

			for (int i = 0; i < resulfinal1.size(); i++) {
				if (!resulfinal1.get(i).getPriroty().equalsIgnoreCase("true")) {

				} else {
					setNr(resulfinal1.get(i).getNr());
					setDr(resulfinal1.get(i).getDr());
					setProductName(resulfinal1.get(i).getProduct_name());
					setRollID(resulfinal1.get(i).getRollID());
					purchaseOrder.setTargentDate(salesViewMB.odate);
					purchaseOrder.setProduct_name(productName);
					purchaseOrder.setQuantity(resulfinal1.get(i).getQuantity());
					purchaseOrder.setRollID(rollID);
					if (nr.equalsIgnoreCase("")) {
						nr = "0";
					}
					if (dr.equalsIgnoreCase("")) {
						dr = "0";
					}
					purchaseOrder.setNr(nr);
					purchaseOrder.setDr(dr);
					String temp = ""
							+ new BigDecimal(nr).add(new BigDecimal(dr));
					if (new BigDecimal(temp).compareTo(BigDecimal.valueOf(0)) == 0) {
						throw new DemoException("*Enter the Quantity");
					} else {
						if (new BigDecimal(temp).compareTo(new BigDecimal(
								resulfinal1.get(i).getQuantity())) == 1) {
							throw new DemoException(
									"Enter quantity is greater than available purchase");
						} else {
							controller.returnQuantity(purchaseOrder);
							BigDecimal quanyity = new BigDecimal(
									purchaseOrder.getQuantity1());
							String dbquantity = purchaseOrder.getRemaining();
							if (new BigDecimal(temp).compareTo(quanyity) <= 0) {
								controller.salesReturnSubmit(purchaseOrder);
								RequestContext.getCurrentInstance().execute(
										"PF('salesreturn').show();");
							} else {
								throw new DemoException("*Already "
										+ dbquantity
										+ " quantities are return for "
										+ purchaseOrder.getSalesIdReference()
										+ " - product is "
										+ resulfinal1.get(i).getProduct_name());
							}
						}
					}
				}
			}

			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			setTempValidate(e.getMessage());
			return "";
		}
	}

	public Date fromdate;
	public Date todate;
	public String flag = "none";

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	List<PurchaseOrder> mb2 = new ArrayList<PurchaseOrder>();
	List<PurchaseOrder> mb3 = new ArrayList<PurchaseOrder>();
	public String returnQuan1;
	public String returnQuan2;
	public String porderNo;
	public String vendorName;
	public Date orderDate;
	public String totalQuan2;
	public String totalQuan1;
	public Date fromDate;
	public Date toDate;

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<PurchaseOrder> getMb3() {
		return mb3;
	}

	public void setMb3(List<PurchaseOrder> mb3) {
		this.mb3 = mb3;
	}

	public String getTotalQuan1() {
		return totalQuan1;
	}

	public void setTotalQuan1(String totalQuan1) {
		this.totalQuan1 = totalQuan1;
	}

	public String getTotalQuan2() {
		return totalQuan2;
	}

	public void setTotalQuan2(String totalQuan2) {
		this.totalQuan2 = totalQuan2;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getPorderNo() {
		return porderNo;
	}

	public void setPorderNo(String porderNo) {
		this.porderNo = porderNo;
	}

	public String getReturnQuan1() {
		return returnQuan1;
	}

	public void setReturnQuan1(String returnQuan1) {
		this.returnQuan1 = returnQuan1;
	}

	public String getReturnQuan2() {
		return returnQuan2;
	}

	public void setReturnQuan2(String returnQuan2) {
		this.returnQuan2 = returnQuan2;
	}

	public List<PurchaseOrder> getMb2() {
		return mb2;
	}

	public void setMb2(List<PurchaseOrder> mb2) {
		this.mb2 = mb2;
	}

	public String viewSalesReturn() {
		logger.info("[viewSalesReturn()]-------------------inside viewSalesReturn() method()---------------");
		flag1 = "none";
		flag2 = "none";
		try {
			
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			
			controller.viewSalesReturn(purchaseOrder);
			logger.debug("-->> size " + purchaseOrder.domain2.size());
			if (purchaseOrder.domain2.size() > 0) {
				setValidate("");
				flag1 = "1";
				setMb2(purchaseOrder.getDomain2());
				List<String> values = new ArrayList<String>();
				for (int i = 0; i < mb2.size(); i++) {
					values.add(mb2.get(i).getOrderNumber());
				}
				List<String> templist = new ArrayList<String>();
				HashSet<String> hashlist1 = new HashSet<String>(values);
				for (String a : hashlist1) {
					templist.add(a);
				}
				int count = 0;
				List<PurchaseOrder> po = new ArrayList<PurchaseOrder>();
				for (int i = 0; i < templist.size(); i++) {
					for (int j = 0; j < mb2.size(); j++) {
						if (templist.get(i).equalsIgnoreCase(
								mb2.get(j).getOrderNumber())) {
							count = j;
						}
					}
					PurchaseOrder pp = new PurchaseOrder();
					pp.setOrderNumber(mb2.get(count).getOrderNumber());
					pp.setOrderDate(mb2.get(count).getOrderDate());
					pp.setVendorPhoneNumber(mb2.get(count)
							.getVendorPhoneNumber());
					
					po.add(pp);
					count = 0;
				}
				mb2.clear();
				mb2 = po;
			} else {
				flag2 = "1";
			}

		} catch (DemoException e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
		}
		return "";

	}

	public String date1;
	public String date2;

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate(String date1) {
		this.date1 = date1;
	}

	public String viewSalesReturnDetail() {
		logger.info("[viewSalesReturnDetail()]-------------------inside viewSalesReturnDetail() method()---------------");
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			mb3.clear();
			setValidate("");
			purchaseOrder.setFromDate(fromDate);
			purchaseOrder.setToDate(toDate);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setOrderNumber(porderNo);
			controller.viewSalesReturnDetail(purchaseOrder);
			if (purchaseOrder.domain2.size() > 0) {
				setMb3(purchaseOrder.getDomain2());
			} else {
			}
			setDate(df.format(mb3.get(0).getOrderDate()));

		} catch (DemoException e) {
			logger.error("Inside Exception", e);
		}
		return "salezReturnView1";

	}

	public String salesReturnPage() {
		logger.info("[salesReturnPage()]-------------------inside salesReturnPage() method()---------------");
		setValidate("");
		mb3.clear();
		return "salezReturnView2";
	}

	public String salesReturnRedirect() {
		logger.info("[viewSalesReturnRedirect()]-------------------inside viewSalesReturnRedirect() method()---------------");
		flag1 = "none";
		setValidate("");
		setSq(null);
		setFromDate(null);
		setToDate(null);
		mb3.clear();
		return "";
	}

	/* jency */

	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public SalesReturnFormMB() {
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

	// prema begin 29/04/2016 dialog box creation for sales return
	public void salesreturn() {
		logger.info("[salesreturn()]-------------------inside salesreturn() method()---------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("salesReturnNewForm",
				options, null);
		salesReturnMenu();
	}

	// prema end 29/0/2016
	public void salesreturnclose() {
		RequestContext.getCurrentInstance().closeDialog("salesReturnNewForm");
	}

	// prema begin 29/04/2016 dialog box creation for sales return view
	public void salesreturnview() {
		logger.info("[salesreturnview()]-------------------inside salesreturnview() method()---------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("salesReturnView",
				options, null);
		viewSalesReturn();
	}

	// prema end 29/0/2016

	public void salesreturnviewclose() {
		logger.info("[salesreturnviewclose()]-------------------inside salesreturnviewclose() method()---------------");
		RequestContext.getCurrentInstance().closeDialog("salesReturnView");
	}

}
