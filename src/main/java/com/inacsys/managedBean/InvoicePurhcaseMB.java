
package com.inacsys.managedBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0015;
import com.inacsys.shared.I0016;
import com.inacsys.util.GenerateEmployee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

//import scala.annotation.meta.setter;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "invoicePurhcaseMB")
public class InvoicePurhcaseMB {
	@ManagedProperty(value = "#{purchaseViewMB}")
	PurchaseViewMB purchaseViewMB;

	public PurchaseViewMB getPurchaseViewMB() {
		return purchaseViewMB;
	}

	public void setPurchaseViewMB(PurchaseViewMB purchaseViewMB) {
		this.purchaseViewMB = purchaseViewMB;
	}

	@Inject
	private static Logger logger = Logger.getLogger(InvoicePurhcaseMB.class);
	private static final long serialVersionUID = 1L;
	public String firmName;
	public String flag = "none";
	public String flag1 = "none";
	public Date fromDate;
	public Date toDate;
	public String firmRegistrationNumber;
	public String address;
	public String vendorTelephoneNumber;
	public String vendorPhoneNumber;
	public String country_ID;
	public String state;
	public String city;
	public String email_ID_vendor;
	public String faxVendor;
	public String peresonIncharge;
	public String nature_of_business_id;
	public String firmTypeStandard;
	public String frim_ID;
	public int product_ID;
	public String product_name = null;
	public String autual_price;
	public String market_price;
	public String totPrize;
	public String totQuan;

	public String getTotPrize() {
		return totPrize;
	}

	public void setTotPrize(String totPrize) {
		this.totPrize = totPrize;
	}

	public String getTotQuan() {
		return totQuan;
	}

	public void setTotQuan(String totQuan) {
		this.totQuan = totQuan;
	}

	public String sellingPrice;
	public String margin_price;
	public Date date;
	public Date expired_date;
	public String batch;
	public String description;
	public String color;
	public String size_of_product;
	public String product_standard;
	public String brand;
	public String product_weight;
	public String ideal_for;
	public String type_parent;
	public String product_group;
	public String industry_ID;
	public String software;
	public String electronics;
	public String mechanical;
	public String electrical;
	public String biomedical;
	public String civil;
	public String food;
	public String industry;
	public String totalprice;
	public String quantity;
	public String orderNumber;
	public String s;
	public String validate;
	public String tempOrder;
	private Integer pid;
	private String unit;
	private String name;
	private String aname;
	private byte[] data;
	private ArrayList<UploadedImage> files = new ArrayList<UploadedImage>();
	private List<UploadedImage> filess;
	public String filepath;
	public ArrayList<I0016> purchaselist;
	public ArrayList<I0016> purchaselist1;
	public ArrayList<I0015> purchaselist2 = null;
	DemoController controller = null;
	public ArrayList<I0015> purchasedrop = null;
	ArrayList<String> finallist1 = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();
	ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
	ArrayList<InvoicePurhcaseMB> finallist = new ArrayList<InvoicePurhcaseMB>();
	public int serialNo;
	public String tDate;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public String getAutual_price() {
		return autual_price;
	}

	public void setAutual_price(String autual_price) {
		this.autual_price = autual_price;
	}

	public String getMarket_price() {
		return market_price;
	}

	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getMargin_price() {
		return margin_price;
	}

	public void setMargin_price(String margin_price) {
		this.margin_price = margin_price;
	}

	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}

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

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public ArrayList<UploadedImage> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<UploadedImage> files) {
		this.files = files;
	}

	public List<UploadedImage> getFiless() {
		return filess;
	}

	public void setFiless(List<UploadedImage> filess) {
		this.filess = filess;
	}

	UploadedFile file;

	private String destination = "d:\\ram\\";

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public ArrayList<InvoicePurhcaseMB> getFinallist() {
		return finallist;
	}

	public void setFinallist(ArrayList<InvoicePurhcaseMB> finallist) {
		this.finallist = finallist;
	}

	public ArrayList<I0015> getPurchasedrop() {
		return purchasedrop;
	}

	public void setPurchasedrop(ArrayList<I0015> purchasedrop) {
		this.purchasedrop = purchasedrop;
	}

	public ArrayList<String> getFinallist1() {
		return finallist1;
	}

	public void setFinallist1(ArrayList<String> finallist1) {
		this.finallist1 = finallist1;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;

	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getFirmRegistrationNumber() {
		return firmRegistrationNumber;
	}

	public void setFirmRegistrationNumber(String firmRegistrationNumber) {
		this.firmRegistrationNumber = firmRegistrationNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVendorTelephoneNumber() {
		return vendorTelephoneNumber;
	}

	public void setVendorTelephoneNumber(String vendorTelephoneNumber) {
		this.vendorTelephoneNumber = vendorTelephoneNumber;
	}

	public String getVendorPhoneNumber() {
		return vendorPhoneNumber;
	}

	public void setVendorPhoneNumber(String vendorPhoneNumber) {
		this.vendorPhoneNumber = vendorPhoneNumber;
	}

	public String getCountry_ID() {
		return country_ID;
	}

	public void setCountry_ID(String country_ID) {
		this.country_ID = country_ID;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail_ID_vendor() {
		return email_ID_vendor;
	}

	public void setEmail_ID_vendor(String email_ID_vendor) {
		this.email_ID_vendor = email_ID_vendor;
	}

	public String getFaxVendor() {
		return faxVendor;
	}

	public void setFaxVendor(String faxVendor) {
		this.faxVendor = faxVendor;
	}

	public String getPeresonIncharge() {
		return peresonIncharge;
	}

	public void setPeresonIncharge(String peresonIncharge) {
		this.peresonIncharge = peresonIncharge;
	}

	public String getNature_of_business_id() {
		return nature_of_business_id;
	}

	public void setNature_of_business_id(String nature_of_business_id) {
		this.nature_of_business_id = nature_of_business_id;
	}

	public String getFirmTypeStandard() {
		return firmTypeStandard;
	}

	public void setFirmTypeStandard(String firmTypeStandard) {
		this.firmTypeStandard = firmTypeStandard;
	}

	public String getFrim_ID() {
		return frim_ID;
	}

	public void setFrim_ID(String frim_ID) {
		this.frim_ID = frim_ID;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getExpired_date() {
		return expired_date;
	}

	public void setExpired_date(Date expired_date) {
		this.expired_date = expired_date;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize_of_product() {
		return size_of_product;
	}

	public void setSize_of_product(String size_of_product) {
		this.size_of_product = size_of_product;
	}

	public String getProduct_standard() {
		return product_standard;
	}

	public void setProduct_standard(String product_standard) {
		this.product_standard = product_standard;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProduct_weight() {
		return product_weight;
	}

	public void setProduct_weight(String product_weight) {
		this.product_weight = product_weight;
	}

	public String getIdeal_for() {
		return ideal_for;
	}

	public void setIdeal_for(String ideal_for) {
		this.ideal_for = ideal_for;
	}

	public String getType_parent() {
		return type_parent;
	}

	public void setType_parent(String type_parent) {
		this.type_parent = type_parent;
	}

	public String getProduct_group() {
		return product_group;
	}

	public void setProduct_group(String product_group) {
		this.product_group = product_group;
	}

	public String getIndustry_ID() {
		return industry_ID;
	}

	public void setIndustry_ID(String industry_ID) {
		this.industry_ID = industry_ID;
	}

	public String getSoftware() {
		return software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getElectronics() {
		return electronics;
	}

	public void setElectronics(String electronics) {
		this.electronics = electronics;
	}

	public String getMechanical() {
		return mechanical;
	}

	public void setMechanical(String mechanical) {
		this.mechanical = mechanical;
	}

	public String getElectrical() {
		return electrical;
	}

	public void setElectrical(String electrical) {
		this.electrical = electrical;
	}

	public String getBiomedical() {
		return biomedical;
	}

	public void setBiomedical(String biomedical) {
		this.biomedical = biomedical;
	}

	public String getCivil() {
		return civil;
	}

	public void setCivil(String civil) {
		this.civil = civil;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public ArrayList<I0015> getPurchaselist2() {
		return purchaselist2;
	}

	public void setPurchaselist2(ArrayList<I0015> purchaselist2) {
		this.purchaselist2 = purchaselist2;
	}

	public ArrayList<I0016> getPurchaselist1() {
		return purchaselist1;
	}

	public void setPurchaselist1(ArrayList<I0016> purchaselist1) {
		this.purchaselist1 = purchaselist1;
	}

	public ArrayList<I0016> getPurchaselist() {
		return purchaselist;
	}

	public void setPurchaselist(ArrayList<I0016> purchaselist) {
		this.purchaselist = purchaselist;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getTempOrder() {
		return tempOrder;
	}

	public void setTempOrder(String tempOrder) {
		this.tempOrder = tempOrder;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String invalidate;

	public String getInvalidate() {
		return invalidate;
	}

	public void setInvalidate(String invalidate) {
		this.invalidate = invalidate;
	}

	public ArrayList<PurchaseOrder> getResult4() {
		return result4;
	}

	public void setResult4(ArrayList<PurchaseOrder> result4) {
		this.result4 = result4;
	}

	public String invoicePurhcase() {
		logger.info("[invoicePurhcase()] --------------- Inside invoicePurhcase() method() ------------------------");
		try {
			invalidate = null;
			finallist.clear();
			setS(purchaseViewMB.orderNumber);
			purchaseOrder.setOrderNumber(s);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist = controller.purchaseOrdercancel(s, purchaselist,
					purchaseOrder);
			if (purchaselist.size() > 0) {
				for (int i = 0; purchaselist.size() < 0; i++) {
					logger.debug("==in purr===");
					result4.get(i).setTotalPrice(
							GenerateEmployee.numberFormat
									.format(new BigDecimal(result4.get(i)
											.getTotalPrice())));
				}
			}
			if (purchaselist == null) {
				throw new DemoException(
						"its null pointer exception because of null purchaselist");
			}
			if (purchaselist != null) {
				setResult4(purchaseOrder.getResult4());
				flag = "1";
			}
			int i = 0;
			return "";
		} catch (DemoException ie) {
			logger.debug(ie.getMessage());
			setInvalidate(ie.getMessage());
			logger.error(ie.getMessage());
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";

		} finally {

		}
	}

	public String invoicePurachaseDrop() {
		logger.info("[invoicePurachaseDrop()] --------------- Inside invoicePurachaseDrop() method() ------------------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist2 = controller.invoicePurachaseDrop(purchaselist2);
			if (purchaselist2 == null) {
				throw new DemoException(
						"its null pointer exception because of null purchaselist:::::");
			} else {
				finallist1 = new ArrayList<String>();
				for (int i = 0; i < purchaselist2.size(); i++) {
					finallist1.add(purchaselist2.get(i).getTemOrderNumber());
				}
			}
			int i = 0;
			return "";
		} catch (DemoException ie) {
			setInvalidate(ie.getMessage());
			logger.error(ie.getMessage());
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		} finally {

		}
	}

	public String invoicePurachaseDrop1() {
		logger.info("[invoicePurachaseDrop1()] --------------- Inside invoicePurachaseDrop1() method() ------------------------");
		try {
			invalidate = null;
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchasedrop = controller.invoicePurachaseDrop1(purchasedrop);
			if (purchasedrop == null) {
				throw new DemoException("no data found");
			}
			finallist1 = new ArrayList<String>();
			for (I0015 purchasedrop1 : purchasedrop) {
				finallist1.add(purchasedrop1.getTemOrderNumber());
			}
			return "";
		} catch (DemoException ie) {
			setInvalidate(ie.getMessage());
			logger.error(ie.getMessage());
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		} finally {

		}
	}

	public String dateSearchInvoice() {
		logger.info("[dateSearchInvoice()] --------------- Inside dateSearchInvoice() method() ------------------------");
		try {
			validate = "";
			flag = "none";
			flag1 = "none";
			invalidate = null;
			purchaselist = null;
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist2 = controller.dateSearchInvoice(fromDate, toDate,
					purchaselist2, purchaseOrder);
			if (purchaselist2 == null) {
				throw new DemoException(
						"its null pointer exception because of null purchaselist:::::");
			}
			if (purchaselist2 != null) {
				setResult4(purchaseOrder.getResult4());
				flag1 = "1";
			}
			int i = 0;
			return "success6";
		} catch (DemoException ie) {
			setValidate("*No value Found");
			setInvalidate(ie.getMessage());
			logger.error(ie.getMessage());
			return "";
		} catch (Exception e) {
			setValidate("*No value Found");
			logger.error(e.getStackTrace());
			return "";
		} finally {

		}
	}

	public String purchaseInvoice() {
		logger.info("[purchaseInvoice()] --------------- Inside purchaseInvoice() method() ------------------------");
		try {
			setInvalidate(null);
			setS("");
			setFlag("none");
			setFromDate(null);
			setToDate(null);
			setFlag1("none");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesDelete();

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "success7";

	}

	public String invoiceView() {
		logger.info("[invoiceView()] --------------- Inside invoiceView() method() ------------------------");
		try {
			
			flag = "none";
			int i = 0;
			purchaseOrder.setOrderNumber(orderNumber);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaselist = controller.invoicePurhcase1(purchaseOrder,
					purchaselist);
			firmName = purchaselist.get(i).getI0031().getI0025().getFirmName();
			vendorPhoneNumber = purchaselist.get(i).getI0031().getI0025()
					.getVendorPhoneNumber();
			address = purchaselist.get(i).getI0031().getI0025().getAddress();
			country_ID = purchaselist.get(i).getI0031().getI0025().getI0028()
					.getName();
			email_ID_vendor = purchaselist.get(i).getI0031().getI0025()
					.getEmail_ID_vendor();
			peresonIncharge = purchaselist.get(i).getI0031().getI0025()
					.getPeresonIncharge();
			quantity = purchaselist.get(i).getI0015().getQuantity();
			product_name = purchaselist.get(i).getI0031().getI0001()
					.getProductName();
			brand = purchaselist.get(i).getI0031().getI0001().getBrand();
			sellingPrice = purchaselist.get(i).getI0031().getI0001()
					.getSellingPrice();
			totalprice = "" + purchaselist.get(i).getI0015().getTotalPrice();
			return "success4";
		} catch (Exception e) {
			int i = 0;
			firmName = purchaselist.get(i).getI0031().getI0025().getFirmName();
			vendorPhoneNumber = purchaselist.get(i).getI0031().getI0025()
					.getVendorPhoneNumber();
			address = purchaselist.get(i).getI0031().getI0025().getAddress();
			country_ID = purchaselist.get(i).getI0031().getI0025().getI0028()
					.getName();
			email_ID_vendor = purchaselist.get(i).getI0031().getI0025()
					.getEmail_ID_vendor();
			peresonIncharge = purchaselist.get(i).getI0031().getI0025()
					.getPeresonIncharge();
			quantity = purchaselist.get(i).getI0015().getQuantity();
			product_name = purchaselist.get(i).getI0031().getI0001()
					.getProductName();
			brand = purchaselist.get(i).getI0031().getI0001().getBrand();
			sellingPrice = purchaselist.get(i).getI0031().getI0001()
					.getSellingPrice();
			totalprice = "" + purchaselist.get(i).getI0015().getTotalPrice();
			logger.error("invoiceView----------->"
					+ purchaselist.get(i).getI0031().getI0001()
							.getProductName());
			logger.error("-----------outside mb exception-----------");
			return "failure4";
		}
	}

	public String redirect() {
		  logger.info("[redirect()] --------------- Inside redirect() method() ------------------------");
		  try {
		   flag = "none";
		   int i = 0;
		   setS(purchaseViewMB.orderNumber);
		   purchaseOrder.setOrderNumber(s);
		   ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		   controller = (DemoController) ctx.getBean("controller");
		   purchaseOrder.setResultview(purchaseViewMB.getResultview()); //stanley changes start
		   purchaseOrder.setVendor_phone_number(purchaseViewMB.getVendor_phone_number()); 
		   purchaseOrder.setOrderNumber(purchaseViewMB.getOrderNumber());  
		   purchaseOrder.setOrderDate(purchaseViewMB.getOrderDate());  
		   purchaseOrder.setTargentDate(purchaseViewMB.getTargentDate());
		   purchaseOrder.setCurrencyAmount(purchaseViewMB.getCurrency());
		   purchaseOrder.setTotalPrice(purchaseViewMB.getTotalPrice());
		   purchaseOrder.setBaseCurrency(purchaseViewMB.getBaseCurrency());
		   purchaseOrder.setCurrency(purchaseViewMB.getSourceCurrency());
		   controller.invoicePurhcase(purchaseOrder);
		   setPurchaselist(purchaseOrder.getPurchaselist());
		   BigDecimal qq = BigDecimal.valueOf(0);
		   BigDecimal pp = BigDecimal.valueOf(0);
		   int count = 0;
		   for (I0016 pu : purchaselist) {
		    InvoicePurhcaseMB invoicePurhcaseMB = new InvoicePurhcaseMB();
		    invoicePurhcaseMB.setDate(pu.getOrderDate());
		    invoicePurhcaseMB.setProduct_name(pu.getI0031().getI0001()
		      .getProductName());
		    invoicePurhcaseMB.setSellingPrice(pu.getI0031().getI0001()
		      .getAutualPrice());
		    invoicePurhcaseMB.setQuantity(pu.getQuantity());
		    invoicePurhcaseMB.setTotalprice(""
		      + purchaseOrder.getF().get(count).getTotalPrice());
		    invoicePurhcaseMB.setSerialNo(purchaseOrder.getF().get(count)
		      .getSerialNo());
		    qq = qq.add(new BigDecimal(pu.getQuantity()));
		    pp = pp.add(new BigDecimal(purchaseOrder.getF().get(count)
		      .getTotalPrice()));
		    finallist.add(invoicePurhcaseMB);
		    count++;
		   }
		   setTotPrize("" + pp);
		   setTotQuan("" + qq);
		   tDate = sdf.format(Calendar.getInstance().getTime());
		   firmName = purchaselist.get(i).getI0031().getI0025().getFirmName();
		   vendorPhoneNumber = purchaselist.get(i).getI0031().getI0025()
		     .getVendorPhoneNumber();
		   address = purchaselist.get(i).getI0031().getI0025().getAddress();
		   country_ID = purchaselist.get(i).getI0031().getI0025().getI0028()
		     .getName();
		   email_ID_vendor = purchaselist.get(i).getI0031().getI0025()
		     .getEmail_ID_vendor();
		   peresonIncharge = purchaselist.get(i).getI0031().getI0025()
		     .getPeresonIncharge();
		   quantity = purchaselist.get(i).getI0015().getQuantity();
		   product_name = purchaselist.get(i).getI0031().getI0001()
		     .getProductName();
		   brand = purchaselist.get(i).getI0031().getI0001().getBrand();
		   sellingPrice = purchaselist.get(i).getI0031().getI0001()
		     .getSellingPrice();
		   totalprice = "" + purchaselist.get(i).getI0015().getTotalPrice();
		   return "";
		  } catch (Exception e) {
		   logger.error("-----------inside mb redirect-------------------");
		   int i = 0;
		   firmName = purchaselist.get(i).getI0031().getI0025().getFirmName();
		   vendorPhoneNumber = purchaselist.get(i).getI0031().getI0025()
		     .getVendorPhoneNumber();
		   address = purchaselist.get(i).getI0031().getI0025().getAddress();
		   country_ID = purchaselist.get(i).getI0031().getI0025().getI0028()
		     .getName();
		   email_ID_vendor = purchaselist.get(i).getI0031().getI0025()
		     .getEmail_ID_vendor();
		   peresonIncharge = purchaselist.get(i).getI0031().getI0025()
		     .getPeresonIncharge();
		   quantity = purchaselist.get(i).getI0015().getQuantity();
		   product_name = purchaselist.get(i).getI0031().getI0001()
		     .getProductName();
		   brand = purchaselist.get(i).getI0031().getI0001().getBrand();
		   sellingPrice = purchaselist.get(i).getI0031().getI0001()
		     .getSellingPrice();
		   totalprice = "" + purchaselist.get(i).getI0015().getTotalPrice();
		   logger.error("firmname----------->" + firmName);
		   setPurchaselist(purchaseOrder.getPurchaselist());
		   int count = 0;
		   for (I0016 pu : purchaselist) {
		    logger.error("$$$$$$$$$$$$$ Inside for $$$$$$$$$$$$");
		    InvoicePurhcaseMB invoicePurhcaseMB = new InvoicePurhcaseMB();
		    invoicePurhcaseMB.setDate(pu.getOrderDate());
		    invoicePurhcaseMB.setProduct_name(pu.getI0031().getI0001()
		      .getProductName());
		    invoicePurhcaseMB.setSellingPrice(pu.getI0031().getI0001()
		      .getAutualPrice());
		    invoicePurhcaseMB.setQuantity(pu.getQuantity());
		    invoicePurhcaseMB.setTotalprice(""
		      + purchaseOrder.getF().get(count).getTotalPrice());

		    logger.error("total price=================>"
		      + purchaseOrder.getF().get(count).getTotalPrice());
		    finallist.add(invoicePurhcaseMB);
		    count++;
		   }
		   logger.error("outside ::::::::::::::"
		     + purchaselist.get(i).getI0031().getI0001()
		       .getProductName());
		   return "";
		  }
		 }

	public String redirect1() {
		logger.info("[redirect1()] --------------- Inside redirect1() method() ------------------------");
		try {
			flag = "none";
			int i = 0;
			logger.debug("[redirect()] --------------- outside ------------------------>"+purchaselist.get(i).getI0031().getI0001().getProductName());
			setTempOrder(s);
			return "success";
		} catch (Exception e) {
			return "failure";
		}
	}

	public String redirecthome() {
		logger.info("[redirecthome()] --------------- Inside redirecthome() method() ------------------------");
		return "redirecthome";
	}

	public String directHome() {
		return "home";
	}

	public String checkPage() {
		logger.info("[checkpage()] --------------- Inside checkpage() method() ------------------------");
		try {
			validate = "";
			if (purchaseOrder.getOrderNumber().equals("")) {
			}
			return "success";
		} catch (NullPointerException e) {
			logger.error("inside final exception");
			setValidate("Click ok to select purchaseorder");
			return "failure";
		}
	}

	public void listener(FileUploadEvent event) throws Exception {
		logger.info("[listener()] --------------- Inside listener() method() ------------------------");
		try {
			if (purchaseOrder.getOrderNumber() == null) {
				throw new DemoException("Click ok to select purchaseorder");
			}
			UploadedFile item = event.getFile();
			UploadedImage file = new UploadedImage();
			file.setName(item.getFileName());
			file.setData(item.getContents());
			files.add(file);
			File fullFile = new File(item.getFileName());
			this.file = event.getFile();
			TransferFile(getFile().getFileName(), getFile().getInputstream());
		} catch (DemoException e) {
			e.getMessage();
			logger.error("" + e.getMessage());
		}
	}

	public void TransferFile(String fileName, InputStream in) throws DemoException {
		logger.info("[TransferFile()] --------------- Inside TransferFile() method() ------------------------");
		try {

			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			filepath = controller.filePath(purchaseOrder);
			logger.debug("[TransferFile()] --------------- file name ------------------------>"+purchaseOrder.getFilenametemp());
			logger.debug("[TransferFile()] --------------- file path ------------------------>"+filepath);
			logger.debug("[TransferFile()] --------------- inside file transfer purchase ------------------------>"+purchaseOrder.getOrderNumber());
			OutputStream out = new FileOutputStream(new File(filepath
					+ purchaseOrder.getFilenametemp() + "" + fileName));
			purchaseOrder.setFilePathfinal(purchaseOrder.getFilenametemp() + ""
					+ fileName);
			controller.fileSave(purchaseOrder);
			int reader = 0;
			byte[] bytes = new byte[(int) getFile().getSize()];
			while ((reader = in.read(bytes)) != -1) {
				out.write(bytes, 0, reader);
			}
			in.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.debug(e.getMessage());
		}
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	// prema begin 02/05/2016 dialog box creation for purchase invoice

	public void purchaseinvoice() {
		logger.info("[purchaseinvoice()] --------------- Inside purchaseinvoice() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);

		RequestContext.getCurrentInstance().openDialog("invoicePurchase",
				options, null);
		purchaseInvoice();
	}

	// prema end 02/05/2016
	public void purchaseinvclose() {
		RequestContext.getCurrentInstance().closeDialog("invoicePurchase");
	}
}