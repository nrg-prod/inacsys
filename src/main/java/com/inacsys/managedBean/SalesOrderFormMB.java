package com.inacsys.managedBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.regex.REUtil;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.domain.Sales;
import com.inacsys.shared.SalesQuote;
import com.inacsys.util.AccountsJDBC;
import com.inacsys.util.CommonValidate;
import com.inacsys.util.Util;
import com.inacsys.controler.DemoController;
import com.inacsys.domain.Buyer;
import com.inacsys.domain.EmployeeDetail;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.ProductRegister;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.Employee;
import com.inacsys.shared.I0018;
import com.inacsys.shared.I0028;
import com.inacsys.shared.I0032;
import com.inacsys.shared.Revenue;
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

@ManagedBean(name = "salesOrderFormMB")
public class SalesOrderFormMB {
	private static Logger logger = Logger.getLogger(SalesOrderFormMB.class);
	SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
	@ManagedProperty(value = "#{salesViewMB}")
	SalesViewMB salesViewMB;
	@ManagedProperty(value = "#{salesReturnFormMB}")
	SalesReturnFormMB salesReturnFormMB;
	@ManagedProperty(value = "#{invoiceSalesMB}")
	InvoiceSalesMB invoiceSalesMB;
	@ManagedProperty(value = "#{accountinReMB}")
	AccountinReMB accountinReMB;
	
	public String tempDiscount;
	public String disAmount;
	public String dflag;
	public String dflag1;
	public String dflag2;
	public String dflag3;
	public String uflag = "none";
	public String uflag1 = "none";
	public String uflag2 = "none";
	public String dicountAmount;
	public List<I0032> phnnum = null;
	private String priflag1;
	private String priflag2;
	private String stockin;
	public String rollQuantities;
	public String rollQFlag1;
	public String rollQFlag2;
	public String innerserial1;
	public String innerserial2;
	public String presentcountryID;
	public String customerTitle;
	public String customerMiddleName;
	public String customerLastName;
	public String customerSuffix;
	public String custMobile;
	public String other;
	public String company;
	public String website;
	public String faxnumber;
	public String displayName;
	public String permanentaddress;
	public String presentCity;
	public String perPostCode;
	public String prePostCode;
	public String presentstate;
	public List<String> stateList=null;
	public List<String> stateList1=null;
	private UploadedFile attachmentFile;
	private UploadedFile photoUploadFile;
	private boolean sameCheckBox=false;
	private boolean hiddenFlag=false;
	private boolean showFlag=true;
	public List<String> displayList=null;
	private String currency;
	public boolean purchaseCheck;
	List<String>customerlist=new ArrayList<String>();
	List<SalesOrderFormMB>quoteList=new ArrayList<SalesOrderFormMB>();
	ArrayList<SalesOrderFormMB>quoteTablelist=new ArrayList<SalesOrderFormMB>();
	List<SalesOrderFormMB> quotationDetailList=null;
	public String quotationcode;
	public int quoteID;
	public int quoteDetailsID;
	ArrayList<SalesOrderFormMB>quoteListDetails=null;
	public String salesHidden;
	Map<SalesOrderFormMB, ArrayList<SalesOrderFormMB>> maps = null;
	public String currencyAmount;
	public String baseCurrency;
	public UploadedFile nomineePhoto;
	
	public UploadedFile getNomineePhoto() {
		return nomineePhoto;
	}

	public void setNomineePhoto(UploadedFile nomineePhoto) {
		this.nomineePhoto = nomineePhoto;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public boolean isPurchaseCheck() {
		return purchaseCheck;
	}

	public void setPurchaseCheck(boolean purchaseCheck) {
		this.purchaseCheck = purchaseCheck;
	}
	
	public int getQuoteDetailsID() {
		return quoteDetailsID;
	}

	public void setQuoteDetailsID(int quoteDetailsID) {
		this.quoteDetailsID = quoteDetailsID;
	}

	public String getSalesHidden() {
		return salesHidden;
	}

	public void setSalesHidden(String salesHidden) {
		this.salesHidden = salesHidden;
	}

	public ArrayList<SalesOrderFormMB> getQuoteListDetails() {
		return quoteListDetails;
	}

	public void setQuoteListDetails(ArrayList<SalesOrderFormMB> quoteListDetails) {
		this.quoteListDetails = quoteListDetails;
	}	
	
	

	public List<SalesOrderFormMB> getQuotationDetailList() {
		return quotationDetailList;
	}

	public void setQuotationDetailList(List<SalesOrderFormMB> quotationDetailList) {
		this.quotationDetailList = quotationDetailList;
	}

	public int getQuoteID() {
		return quoteID;
	}

	public void setQuoteID(int quoteID) {
		this.quoteID = quoteID;
	}

	public String getQuotationcode() {
		return quotationcode;
	}

	public void setQuotationcode(String quotationcode) {
		this.quotationcode = quotationcode;
	}

	public List<SalesOrderFormMB> getQuoteList() {
		return quoteList;
	}

	public void setQuoteList(List<SalesOrderFormMB> quoteList) {
		this.quoteList = quoteList;
	}

	public ArrayList<SalesOrderFormMB> getQuoteTablelist() {
		return quoteTablelist;
	}

	public void setQuoteTablelist(ArrayList<SalesOrderFormMB> quoteTablelist) {
		this.quoteTablelist = quoteTablelist;
	}

	//@Inject
	//Sales sales=null;
	@Inject
	Sales sales = new Sales();
	Boolean test=true;
	List<String>prodlist=new ArrayList<String>(); 
	ArrayList<SalesOrderFormMB>quoteView=null;
	ArrayList<SalesOrderFormMB>mblist=null;
	public String customername1;
	public String businessname;
	public String mobile;
	public String newQuotenumber;
	ArrayList<VendorRegisterFormMB> filterList;
	public String userType;
	public String approvalStatus;
	public String userID;
	private String approveButtonFlag;
	private boolean quotationCheck=false;
	
	
	

	public ArrayList<SalesOrderFormMB> getQuoteView() {
		return quoteView;
	}

	public void setQuoteView(ArrayList<SalesOrderFormMB> quoteView) {
		this.quoteView = quoteView;
	}

	public boolean isQuotationCheck() {
		return quotationCheck;
	}

	public void setQuotationCheck(boolean quotationCheck) {
		this.quotationCheck = quotationCheck;
	}

	public String getApproveButtonFlag() {
		return approveButtonFlag;
	}

	public void setApproveButtonFlag(String approveButtonFlag) {
		this.approveButtonFlag = approveButtonFlag;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public ArrayList<VendorRegisterFormMB> getFilterList() {
		return filterList;
	}

	public void setFilterList(ArrayList<VendorRegisterFormMB> filterList) {
		this.filterList = filterList;
	}

	public String getNewQuotenumber() {
		return newQuotenumber;
	}

	public void setNewQuotenumber(String newQuotenumber) {
		this.newQuotenumber = newQuotenumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBusinessname() {
		return businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public String getCustomername1() {
		return customername1;
	}

	public void setCustomername1(String customername1) {
		this.customername1 = customername1;
	}

	public ArrayList<SalesOrderFormMB> getMblist() {
		return mblist;
	}

	public void setMblist(ArrayList<SalesOrderFormMB> mblist) {
		this.mblist = mblist;
	}

	public List<String> getProdlist() {
		return prodlist;
	}

	public void setProdlist(List<String> prodlist) {
		this.prodlist = prodlist;
	}

	public Boolean getTest() {
		return test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}
//new Sales();
	public Sales getSales() {
		return sales;
	}

	public void setSales(Sales sales) {
		this.sales = sales;
	}

	
	public List<String> getCustomerlist() {
		return customerlist;
	}

	public void setCustomerlist(List<String> customerlist) {
		this.customerlist = customerlist;
	}

	
	public AccountinReMB getAccountinReMB() {
		return accountinReMB;
	}

	public void setAccountinReMB(AccountinReMB accountinReMB) {
		this.accountinReMB = accountinReMB;
	}

	public InvoiceSalesMB getInvoiceSalesMB() {
		return invoiceSalesMB;
	}

	public void setInvoiceSalesMB(InvoiceSalesMB invoiceSalesMB) {
		this.invoiceSalesMB = invoiceSalesMB;
	}

	public SalesReturnFormMB getSalesReturnFormMB() {
		return salesReturnFormMB;
	}

	public void setSalesReturnFormMB(SalesReturnFormMB salesReturnFormMB) {
		this.salesReturnFormMB = salesReturnFormMB;
	}

	public SalesViewMB getSalesViewMB() {
		return salesViewMB;
	}

	public void setSalesViewMB(SalesViewMB salesViewMB) {
		this.salesViewMB = salesViewMB;
	}

	public String discType;
	public String discAmnt;
	public String temptotquan;
	public String code;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTemptotquan() {
		return temptotquan;
	}

	public void setTemptotquan(String temptotquan) {
		this.temptotquan = temptotquan;
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

	public String getDflag3() {
		return dflag3;
	}

	public void setDflag3(String dflag3) {
		this.dflag3 = dflag3;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<String> getDisplayList() {
		return displayList;
	}

	public void setDisplayList(List<String> displayList) {
		this.displayList = displayList;
	}

	public boolean isShowFlag() {
		return showFlag;
	}
	public void setShowFlag(boolean showFlag) {
		this.showFlag = showFlag;
	}
	public boolean isHiddenFlag() {
		return hiddenFlag;
	}
	public void setHiddenFlag(boolean hiddenFlag) {
		this.hiddenFlag = hiddenFlag;
	}
	public boolean isSameCheckBox() {
		return sameCheckBox;
	}

	public void setSameCheckBox(boolean sameCheckBox) {
		this.sameCheckBox = sameCheckBox;
	}

	public UploadedFile getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(UploadedFile attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public UploadedFile getPhotoUploadFile() {
		return photoUploadFile;
	}

	public void setPhotoUploadFile(UploadedFile photoUploadFile) {
		this.photoUploadFile = photoUploadFile;
	}

	public List<String> getStateList1() {
		return stateList1;
	}

	public void setStateList1(List<String> stateList1) {
		this.stateList1 = stateList1;
	}

	public String getCustomerTitle() {
		return customerTitle;
	}

	public void setCustomerTitle(String customerTitle) {
		this.customerTitle = customerTitle;
	}

	public String getCustomerMiddleName() {
		return customerMiddleName;
	}

	public void setCustomerMiddleName(String customerMiddleName) {
		this.customerMiddleName = customerMiddleName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerSuffix() {
		return customerSuffix;
	}

	public void setCustomerSuffix(String customerSuffix) {
		this.customerSuffix = customerSuffix;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFaxnumber() {
		return faxnumber;
	}

	public void setFaxnumber(String faxnumber) {
		this.faxnumber = faxnumber;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPermanentaddress() {
		return permanentaddress;
	}

	public void setPermanentaddress(String permanentaddress) {
		this.permanentaddress = permanentaddress;
	}

	public String getPresentCity() {
		return presentCity;
	}

	public void setPresentCity(String presentCity) {
		this.presentCity = presentCity;
	}

	public String getPerPostCode() {
		return perPostCode;
	}

	public void setPerPostCode(String perPostCode) {
		this.perPostCode = perPostCode;
	}

	public String getPrePostCode() {
		return prePostCode;
	}

	public void setPrePostCode(String prePostCode) {
		this.prePostCode = prePostCode;
	}

	public String getPresentstate() {
		return presentstate;
	}

	public void setPresentstate(String presentstate) {
		this.presentstate = presentstate;
	}

	public String getPresentcountryID() {
		return presentcountryID;
	}

	public void setPresentcountryID(String presentcountryID) {
		this.presentcountryID = presentcountryID;
	}

	public List<String> getStateList() {
		return stateList;
	}

	public void setStateList(List<String> stateList) {
		this.stateList = stateList;
	}

	public String getInnerserial2() {
		return innerserial2;
	}

	public void setInnerserial2(String innerserial2) {
		this.innerserial2 = innerserial2;
	}

	public String getInnerserial1() {
		return innerserial1;
	}

	public void setInnerserial1(String innerserial1) {
		this.innerserial1 = innerserial1;
	}

	public String getDflag2() {
		return dflag2;
	}

	public void setDflag2(String dflag2) {
		this.dflag2 = dflag2;
	}

	public String getRollQFlag1() {
		return rollQFlag1;
	}

	public void setRollQFlag1(String rollQFlag1) {
		this.rollQFlag1 = rollQFlag1;
	}

	public String getRollQFlag2() {
		return rollQFlag2;
	}

	public void setRollQFlag2(String rollQFlag2) {
		this.rollQFlag2 = rollQFlag2;
	}

	public String getRollQuantities() {
		return rollQuantities;
	}

	public void setRollQuantities(String rollQuantities) {
		this.rollQuantities = rollQuantities;
	}

	public String getPriflag1() {
		return priflag1;
	}

	public void setPriflag1(String priflag1) {
		this.priflag1 = priflag1;
	}

	public String getPriflag2() {
		return priflag2;
	}

	public void setPriflag2(String priflag2) {
		this.priflag2 = priflag2;
	}

	private ArrayList<String> rollList;
	private String rflag1;
	private String rflag2;

	private String unit;
	private String tflag1;
	private String tflag2;

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTflag1() {
		return tflag1;
	}

	public void setTflag1(String tflag1) {
		this.tflag1 = tflag1;
	}

	public String getTflag2() {
		return tflag2;
	}

	public void setTflag2(String tflag2) {
		this.tflag2 = tflag2;
	}

	public ArrayList<String> getRollList() {
		return rollList;
	}

	public void setRollList(ArrayList<String> rollList) {
		this.rollList = rollList;
	}

	public String getRflag1() {
		return rflag1;
	}

	public void setRflag1(String rflag1) {
		this.rflag1 = rflag1;
	}

	public String getRflag2() {
		return rflag2;
	}

	public void setRflag2(String rflag2) {
		this.rflag2 = rflag2;
	}

	public List<I0032> getPhnnum() {
		return phnnum;
	}

	public void setPhnnum(List<I0032> phnnum) {
		this.phnnum = phnnum;
	}

	public String getDicountAmount() {
		return dicountAmount;
	}

	public void setDicountAmount(String dicountAmount) {
		this.dicountAmount = dicountAmount;
	}

	public String getUflag() {
		return uflag;
	}

	public void setUflag(String uflag) {
		this.uflag = uflag;
	}

	public String getUflag1() {
		return uflag1;
	}

	public void setUflag1(String uflag1) {
		this.uflag1 = uflag1;
	}

	public String getUflag2() {
		return uflag2;
	}

	public void setUflag2(String uflag2) {
		this.uflag2 = uflag2;
	}

	public String getDflag() {
		return dflag;
	}

	public void setDflag(String dflag) {
		this.dflag = dflag;
	}

	public String getDflag1() {
		return dflag1;
	}

	public void setDflag1(String dflag1) {
		this.dflag1 = dflag1;
	}

	public String getDisAmount() {
		return disAmount;
	}

	public void setDisAmount(String disAmount) {
		this.disAmount = disAmount;
	}

	public String getTempDiscount() {
		return tempDiscount;
	}

	public void setTempDiscount(String tempDiscount) {
		this.tempDiscount = tempDiscount;
	}

	public String totalAmount;
	public String custName;
	public String custPhNo;
	public String custEmailId;

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustPhNo() {
		return custPhNo;
	}

	public void setCustPhNo(String custPhNo) {
		this.custPhNo = custPhNo;
	}

	public String getCustEmailId() {
		return custEmailId;
	}

	public void setCustEmailId(String custEmailId) {
		this.custEmailId = custEmailId;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public ArrayList<String> productlist = null;

	public ArrayList<String> getProductlist() {
		return productlist;
	}

	public void setProductlist(ArrayList<String> productlist) {
		this.productlist = productlist;
	}

	ArrayList<SalesOrderFormMB> homeMBs = new ArrayList<SalesOrderFormMB>();
	public String totalPrice;
	public String serialno;
	public String productName;
	public String flag;
	public String flag1;
	public String flag2;
	public String d;
	public Date oderDate;
	public Date targetDate;
	public String dicount;
	public String price;
	public String netAmount;
	public String vendorName;
	public String total;
	public String od;
	public String td;
	public String validate2;
	public String serialno1;

	public String getSerialno1() {
		return serialno1;
	}

	public void setSerialno1(String serialno1) {
		this.serialno1 = serialno1;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	private String roll;
	public String rollq;

	public String getRollq() {
		return rollq;
	}

	public void setRollq(String rollq) {
		this.rollq = rollq;
	}

	public ArrayList<SalesOrderFormMB> getHomeMBs() {
		return homeMBs;
	}

	public void setHomeMBs(ArrayList<SalesOrderFormMB> homeMBs) {
		this.homeMBs = homeMBs;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public Date getOderDate() {
		return oderDate;
	}

	public void setOderDate(Date oderDate) {
		this.oderDate = oderDate;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getDicount() {
		return dicount;
	}

	public void setDicount(String dicount) {
		this.dicount = dicount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getOd() {
		return od;
	}

	public void setOd(String od) {
		this.od = od;
	}

	public String getTd() {
		return td;
	}

	public void setTd(String td) {
		this.td = td;
	}

	public String getValidate2() {
		return validate2;
	}

	public void setValidate2(String validate2) {
		this.validate2 = validate2;
	}

	public String customername;
	public String countryID;
	public Date salesorderdate;
	public String shipingaddress;
	public String phonenumber;
	public String email;
	public String totalnumberofcount;
	public String totalnumberofcount1;
	public String quantity = null;
	public String sellingPrice;
	public String salesIdReference;
	public String orderNumber;
	public Date deliverydate;
	public String note;
	public String shipping_charge = "0";
	public String crosstotal;
	public String shipping_charge1;
	private String shipping_company;
	public String crosstotal1;
	public int salesId;
	public String batchProductName = null;
	public String batchProductName1;
	public int productId;
	List<I0018> batchProductName3;
	public String marginPrice;
	public int batchID;
	public String state;
	public String city;
	public List<I0028> countrys;
	public List<I0032> buyer;
	public String taxnumber;
	public String salesorderdate1;
	private String totalPrice1;
	public String cFlag;
	public String cFlag1;
	public String saFlag;
	public String saFlag1;
	public String cyFlag;
	public String cyFlag1;
	public String stFlag;
	public String stFlag1;
	public String conFlag;
	public String conFlag1;
	public String eFlag;
	public String eFlag1;
	public String phFlag;
	public String phFlag1;
	public String phNO;

	public String getPhNO() {
		return phNO;
	}

	public void setPhNO(String phNO) {
		this.phNO = phNO;
	}

	public String getSaFlag() {
		return saFlag;
	}

	public void setSaFlag(String saFlag) {
		this.saFlag = saFlag;
	}

	public String getSaFlag1() {
		return saFlag1;
	}

	public void setSaFlag1(String saFlag1) {
		this.saFlag1 = saFlag1;
	}

	public String getCyFlag() {
		return cyFlag;
	}

	public void setCyFlag(String cyFlag) {
		this.cyFlag = cyFlag;
	}

	public String getCyFlag1() {
		return cyFlag1;
	}

	public void setCyFlag1(String cyFlag1) {
		this.cyFlag1 = cyFlag1;
	}

	public String getStFlag() {
		return stFlag;
	}

	public void setStFlag(String stFlag) {
		this.stFlag = stFlag;
	}

	public String getStFlag1() {
		return stFlag1;
	}

	public void setStFlag1(String stFlag1) {
		this.stFlag1 = stFlag1;
	}

	public String getConFlag() {
		return conFlag;
	}

	public void setConFlag(String conFlag) {
		this.conFlag = conFlag;
	}

	public String getConFlag1() {
		return conFlag1;
	}

	public void setConFlag1(String conFlag1) {
		this.conFlag1 = conFlag1;
	}

	public String geteFlag() {
		return eFlag;
	}

	public void seteFlag(String eFlag) {
		this.eFlag = eFlag;
	}

	public String geteFlag1() {
		return eFlag1;
	}

	public void seteFlag1(String eFlag1) {
		this.eFlag1 = eFlag1;
	}

	public String getPhFlag() {
		return phFlag;
	}

	public void setPhFlag(String phFlag) {
		this.phFlag = phFlag;
	}

	public String getPhFlag1() {
		return phFlag1;
	}

	public void setPhFlag1(String phFlag1) {
		this.phFlag1 = phFlag1;
	}

	public String getcFlag() {
		return cFlag;
	}

	public void setcFlag(String cFlag) {
		this.cFlag = cFlag;
	}

	public String getcFlag1() {
		return cFlag1;
	}

	public void setcFlag1(String cFlag1) {
		this.cFlag1 = cFlag1;
	}

	public String getSalesorderdate1() {
		return salesorderdate1;
	}

	public void setSalesorderdate1(String salesorderdate1) {
		this.salesorderdate1 = salesorderdate1;
	}

	public String getTaxnumber() {
		return taxnumber;
	}

	public void setTaxnumber(String taxnumber) {
		this.taxnumber = taxnumber;
	}

	public List<I0032> getBuyer() {
		return buyer;
	}

	public void setBuyer(List<I0032> buyer) {
		this.buyer = buyer;
	}

	public List<I0028> getCountrys() {
		return countrys;
	}

	public void setCountrys(List<I0028> countrys) {
		this.countrys = countrys;
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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getSalesIdReference() {
		return salesIdReference;
	}

	public void setSalesIdReference(String salesIdReference) {
		this.salesIdReference = salesIdReference;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getBatchID() {
		return batchID;
	}

	public void setBatchID(int batchID) {
		this.batchID = batchID;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getMarginPrice() {
		return marginPrice;
	}

	public void setMarginPrice(String marginPrice) {
		this.marginPrice = marginPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public List<I0018> getBatchProductName3() {
		return batchProductName3;
	}

	public void setBatchProductName3(List<I0018> batchProductName3) {
		this.batchProductName3 = batchProductName3;
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

	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
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

	public String validate;

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	DemoController controller = null;
	PurchaseOrder purchaseOrder = new PurchaseOrder();

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	
	public String saledirect() {
		try {
			logger.debug("==sales direct===");
			setCategory1(null);
			setCustomername(null);
			setShipingaddress(null);
			setPhonenumber(null);
			setPhoneNumber(null);
			setNote(null);
			setDeliverydate(null);
			setSalesorderdate(null);
			setValidate(null);
			setEmail(null);
			setCountryID(null);
			setShipingaddress(null);
			setBatchProductName(null);
			setQuantity(null);
			setComFlag("none");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesDelete();
		} catch (Exception e) {

		} finally {
			customername = null;
			phonenumber = null;
			phoneNumber = null;
		}
		return "saledirect";
	}

	public String buyerdirect() {
		setCustomername(null);
		setShipingaddress(null);
		setPhonenumber(null);
		setNote(null);
		setDeliverydate(null);
		setSalesorderdate(null);
		setValidate(null);
		setEmail(null);
		setCountryID(null);
		setShipingaddress(null);
		setCountryID(null);
		setState(null);
		setCity(null);
		return "buyerdirect";
	}

	ArrayList<I0032> buyerName = null;
	ArrayList<I0032> saleDetail = null;
	public String phoneNumber;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ArrayList<I0032> getSaleDetail() {
		return saleDetail;
	}

	public void setSaleDetail(ArrayList<I0032> saleDetail) {
		this.saleDetail = saleDetail;
	}

	public ArrayList<I0032> getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(ArrayList<I0032> buyerName) {
		this.buyerName = buyerName;
	}

	List<String> resulfinal = null;

	public List<String> getResulfinal() {
		return resulfinal;
	}

	public void setResulfinal(List<String> resulfinal) {
		this.resulfinal = resulfinal;
	}

	public String getShipping_company() {
		return shipping_company;
	}

	public void setShipping_company(String shipping_company) {
		this.shipping_company = shipping_company;
	}

	public String category1;

	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	public String phflag = "1";

	public String getPhflag() {
		return phflag;
	}

	public void setPhflag(String phflag) {
		this.phflag = phflag;
	}

	public String temptotalAmount;

	public String getTemptotalAmount() {
		return temptotalAmount;
	}

	public void setTemptotalAmount(String temptotalAmount) {
		this.temptotalAmount = temptotalAmount;
	}

	public void cost(ValueChangeEvent cost) {
		logger.info("[cost()]-------------------inside cost() method()---------------");
		String cost1;
		try {
			cost1 = (String) cost.getNewValue();
			BigDecimal tempval = BigDecimal.valueOf(0);
			BigDecimal temp = BigDecimal.valueOf(0);
			BigDecimal bgg1 = BigDecimal.valueOf(0);
			temp = (new BigDecimal(crosstotal).add(new BigDecimal(cost1)));
			bgg1 = BigDecimal.valueOf(0);
			bgg1 = temp.setScale(0, BigDecimal.ROUND_HALF_UP);
			temptotalAmount = "" + bgg1;
			setTotalPrice("" + (bgg1));
		} catch (NumberFormatException n) {
			logger.error("Inside Exception", n);
			cost1 = "0";
			BigDecimal temp = (new BigDecimal(crosstotal).add(new BigDecimal(
					cost1)));
			BigDecimal bgg1 = BigDecimal.valueOf(0);
			bgg1 = temp.setScale(0, BigDecimal.ROUND_HALF_UP);
			temptotalAmount = "" + bgg1;
			setTotalPrice("" + (bgg1));

		} catch (NullPointerException e) {
			logger.error("Inside Exception", e);
			cost1 = "0";
			BigDecimal temp = (new BigDecimal(crosstotal).add(new BigDecimal(
					cost1)));
			BigDecimal bgg1 = BigDecimal.valueOf(0);
			bgg1 = temp.setScale(0, BigDecimal.ROUND_HALF_UP);
			setTotalPrice("" + (bgg1));
		}
	}

	public void category(ValueChangeEvent a) {
		logger.info("[category()]-------------------inside category() method()---------------");
		String categoryname = (String) a.getNewValue();
		resulfinal.clear();
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setCategory(a.getNewValue().toString());
			controller.catogerycustomer(buyerName, purchaseOrder);
			setResulfinal(purchaseOrder.getResulfinal1());
			comFlag = "none";
			if ((categoryname.equalsIgnoreCase("Customer Name"))
					|| (categoryname.contains("Customer Name"))) {
				phflag = "none";
			} else {
				phflag = "1";
			}

		} catch (DemoException e) {
			logger.error("inside exception ",e);
		} catch (Exception e) {
			logger.error("inside exception ",e);
		}
	}

	public String salesOrder() {
		logger.info("[salesOrder()]-------------------inside salesOrder() method()---------------");
		Buyer b = new Buyer();
		setValidate(null);
		try {
			b.setPhoneNumber(phoneNumber);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if (category1.equalsIgnoreCase("")) {
				throw new DemoException("*Select Category");
			}
			if (customername.equalsIgnoreCase("")) {
				buyerName = null;
				throw new DemoException("Select Customer Name");
			}
			if (phoneNumber.equalsIgnoreCase("")) {
				throw new DemoException("Select Phone Number");
			}
			if (category1.equalsIgnoreCase("Customer Name")) {
				saleDetail = controller.salesOrder(saleDetail, b);
				cFlag = "1";
				cFlag1 = "none";
				saFlag = "1";
				saFlag1 = "none";
				cyFlag = "1";
				cyFlag1 = "none";
				stFlag = "1";
				stFlag1 = "none";
				conFlag = "1";
				conFlag1 = "none";
				eFlag = "1";
				eFlag1 = "none";
				phFlag = "1";
				phFlag1 = "none";
				if (saleDetail.size() > 0) {

					customername = saleDetail.get(0).getCustomerName();
					salesorderdate = null;
					shipingaddress = saleDetail.get(0).getShipingAddress();
					city = saleDetail.get(0).getShipingAddress();
					state = saleDetail.get(0).getState();
					countryID = saleDetail.get(0).getCountry();
					phNO = saleDetail.get(0).getPhoneNumber();
					email = saleDetail.get(0).getEMail();
				}
			} else {
				cFlag1 = "1";
				cFlag = "none";
				saFlag1 = "1";
				saFlag = "none";
				cyFlag1 = "1";
				cyFlag = "none";
				stFlag1 = "1";
				stFlag = "none";
				conFlag1 = "1";
				conFlag = "none";
				eFlag1 = "1";
				eFlag = "none";
				phFlag1 = "1";
				phFlag = "none";
				setCountrys(controller.getCountry());
				phonenumber = customername = "";
				state = "";
				city = "";
				phNO = "";
			}

			dueDate = null;
			return "success";
		} catch (DemoException ie) {
			setValidate(ie.getMessage());
			logger.error("inside exception ",ie);
			saleDetail = null;
			return "failure";
		} finally {

		}

	}

	public static String isEmailValid(String email) {
		logger.info("[isEmailValid()]-------------------inside isEmailValid() method()---------------");
		try {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			CharSequence inputStr = email;
			// Make the comparison case-insensitive.
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inputStr);
			if (matcher.matches()) {
				return "Match";
			} else {
				return "NotMatch";
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "NotMatch";
		} finally {

		}

	}

	public String confirmBuyerRegister() {
		logger.info("[confirmBuyerRegister()]-------------------inside confirmBuyerRegister() method()---------------");
		Buyer b = null;
		try {
			setValidate("");
			salesorderdate1 = sdf.format(salesorderdate);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setValidate("");
			b = new Buyer();
			b.setCustomerName(customername);
			b.setDate(salesorderdate);
			b.setAddress(shipingaddress);
			b.setCity(city);
			b.setCountry(countryID);
			b.setState(state);
			b.setPhoneNumber(phonenumber);
			if (email.equalsIgnoreCase("")) {
				email = "";
				b.setMail("");
			} else {
				b.setMail(email);
			}
			if (taxnumber.equalsIgnoreCase("")) {
				taxnumber = "0";
				b.setTaxnumber("0");
			} else {
				b.setTaxnumber(taxnumber);
			}
			if (note.equalsIgnoreCase("")) {
				note = "";
				b.setNote("");
			} else {
				b.setNote(note);
			}
			b.setCategoryName(categoryName);
			controller.saveBuyer(b);
		} catch (DemoException ie) {
			logger.error("Inside Exception", ie);
		} finally {

		}
		return "home";
	}

	public String buyerConfirm() {
		logger.info("[buyerConfirm()]-------------------inside buyerConfirm() method()---------------");
		try {
			return "home";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "buyerRegisterForm1";
		} finally {

		}
	}

	public String buyerCountryLoad() {
		logger.info("[buyerCountryLoad()]-------------------inside buyerCountryLoad() method()---------------");
		ApplicationContext ctx = null;
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setCountrys(controller.getCountry());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public String salesOrder1() {
		logger.info("[salesOrder1()]-------------------inside salesOrder1() method()---------------");
		setValidate(null);
		try {
			crosstotal1 = "0";
			setValidate("");
			if (!category1.equalsIgnoreCase("Customer Name")) {
				if (customername.equalsIgnoreCase("")) {
					throw new DemoException("*Enter customer Name");
				} else if (salesorderdate == null) {
					throw new DemoException("*Select Date");
				} else if (shipingaddress.equalsIgnoreCase("")) {
					throw new DemoException("*Enter Shiping Address");
				} else if (city.equalsIgnoreCase("")) {
					throw new DemoException("*Enter City");
				} else if (state.equalsIgnoreCase("")) {
					throw new DemoException("* Enter state");
				} else if (countryID.equalsIgnoreCase("")) {
					throw new DemoException("*Enter Country ID");
				}

				else if (phNO.equalsIgnoreCase("")) {
					throw new DemoException("*Enter Phone Number");
				} else if (!phNO.matches("[0-9]+")) {
					throw new DemoException("Phone number must be a number");
				} else if (email.equalsIgnoreCase("")) {
					throw new DemoException("*Enter Email");
				} else if (deliverydate == null) {
					throw new DemoException("*Select Delivery Date");
				} else if (dueDate == null) {
					throw new DemoException("*select due Date");
				} else if (!email.equalsIgnoreCase("")) {
					String res = isEmailValid(email);
					if (res.equalsIgnoreCase("Match")) {
					} else {
						throw new DemoException("* Invalid email id");
					}
				}

			} else {
				if (salesorderdate == null) {
					throw new DemoException("*Select Date");
				} else if (deliverydate == null) {
					throw new DemoException("*Select Delivery Date");
				} else if (dueDate == null) {
					throw new DemoException("*select due Date");
				}
			}

			purchaseOrder.setCustomername(customername);
			purchaseOrder.setSalesorderdate(salesorderdate);
			purchaseOrder.setShipingaddress(shipingaddress);
			purchaseOrder.setPhonenumber(phoneNumber);
			purchaseOrder.setPhNo(phNO);
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
			dueDate = PurchaseOrderFromMB.sample(salesorderdate);
			purchaseOrder.setDueDate(dueDate);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrder1(purchaseOrder);
			discountAmount = "";
			discountType = "";
			return "success1";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			return "failure1";
		}

	}

	public String salesOrder3() {
		logger.info("[salesOrder3()]-------------------inside salesOrder3() method()---------------");

		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setBatchProductName3(controller.salesOrder3(batchProductName3));
			if (batchProductName.equalsIgnoreCase("")) {
				throw new DemoException("*Select Product name");
			} else if (quantity.equalsIgnoreCase("")) {
				throw new DemoException("*Enter Quantity");
			}
			return "";
		} catch (DemoException ie) {
			logger.error("Inside Exception", ie);
			setValidate(ie.getMessage());
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";

		}
	}

	public String salesOrder4() {
		logger.info("[salesOrder3()]-------------------inside salesOrder3() method()---------------");
		try {
			setValidate("");
			purchaseOrder.setQuantity(quantity);
			purchaseOrder.setBatchProductName(batchProductName);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if (batchProductName.equalsIgnoreCase("")) {
				throw new DemoException("*Select Product name");
			} else if (quantity.equalsIgnoreCase("")) {
				throw new DemoException("*Enter Quantity");
			} else if (!discountAmount.equalsIgnoreCase("")) {
				String res = VendorRegisterFormMB.isNumberValid(discountAmount);
				if (res.equalsIgnoreCase("Match")) {
					if (discountType.equalsIgnoreCase("")) {
						throw new DemoException("*Enter discount type");
					} else {

					}
				} else {
					throw new DemoException("*Enter number only for discount");
				}

			}
			purchaseOrder.setCategory(discountType);
			if (discountAmount.equalsIgnoreCase("")) {
				purchaseOrder.setActualPrice("" + BigDecimal.valueOf(0));
			} else {
				purchaseOrder.setActualPrice(discountAmount);
			}
			controller.salesOrder4Normal(purchaseOrder);
			marginPrice = purchaseOrder.getMarginPrice();
			setSellingPrice(purchaseOrder.getSellingPrice());
			setProductId(purchaseOrder.getProduct_ID());
			setCrosstotal1(purchaseOrder.getCrosstotal1());
			setOrderNumber("" + purchaseOrder.getSalesIdReference());
			return "success2";
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			return "failure2";
		}
	}

	public String salesOrder5() {
		logger.info("[salesOrder5()]-------------------inside salesOrder5() method()---------------");
		try {
			purchaseOrder.setQuantity(quantity);
			purchaseOrder.setBatchProductName(batchProductName);
			if (purchaseOrder.getBatchProductName().equalsIgnoreCase("")
					|| purchaseOrder.getQuantity().equalsIgnoreCase("")) {
				throw new DemoException("please fill all the fields.......");
			}

			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesOrder5(purchaseOrder);
			return "success3";
		} catch (DemoException e) {
			logger.debug(e.getMessage());
			logger.error("Inside Exception", e);
			return "failure3";
		}

	}

	List<String> phnozz = new ArrayList<String>();

	public List<String> getPhnozz() {
		return phnozz;
	}

	public void setPhnozz(List<String> phnozz) {
		this.phnozz = phnozz;
	}

	public void customerName(ValueChangeEvent v) {
		logger.info("[customerName()]-------------------inside customerName() method()---------------");
		phnozz.clear();
		try {
			String s = "" + v.getNewValue();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setBuyerName(controller.customerNameChange(s));
			if (buyerName.size() > 0) {
				for (int i = 0; i < buyerName.size(); i++) {
					phnozz.add(buyerName.get(i).getPhoneNumber());
				}
			} else {
			}
			
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public void phonenum(ValueChangeEvent v) {
		logger.info("[phonenum()]-------------------inside phonenum() method()---------------");
		try {
			String s1 = "" + v.getNewValue();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if (category1.equalsIgnoreCase("Free Lancer")) {
				setPhnnum(controller.customerNameChange11(s1));

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public String salesOrder6() {
		logger.info("[salesOrder6()]-------------------inside salesOrder6() method()---------------");
		try {
			setQuantity(null);
			setBatchProductName(null);
			return "success4";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "failure4";
		}
	}

	public String categoryName;
	public String freelancerName = "";
	public String flagFree = "none";
	public String flagfree1 = "none";

	public String getFlagfree1() {
		return flagfree1;
	}

	public void setFlagfree1(String flagfree1) {
		this.flagfree1 = flagfree1;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getFreelancerName() {
		return freelancerName;
	}

	public void setFreelancerName(String freelancerName) {
		this.freelancerName = freelancerName;
	}

	public String getFlagFree() {
		return flagFree;
	}

	public void setFlagFree(String flagFree) {
		this.flagFree = flagFree;
	}

	public List<Employee> name = null;

	public List<Employee> getName() {
		return name;
	}

	public void setName(List<Employee> name) {
		this.name = name;
	}

	public void categoryChange(ValueChangeEvent v) throws DemoException {
		logger.info("[categoryChange()]-------------------inside categoryChange() method()---------------");
		try {
			setValidate("");
			if (v.getNewValue().toString().equalsIgnoreCase("Free Lancer")) {
				setName(controller.freelancerNameInfo(name));
				flagFree = "1";
				flagfree1 = "1";
			} else {
				flagFree = "none";
				flagfree1 = "1";
				freelancerName = "";
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
	}

	public String home() {
		setBuyerName(null);
		return "home";
	}

	public String discountType;
	public String discountAmount;

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String comFlag = "none";
	public String commisionAmount;

	public String getComFlag() {
		return comFlag;
	}

	public void setComFlag(String comFlag) {
		this.comFlag = comFlag;
	}

	public String getCommisionAmount() {
		return commisionAmount;
	}

	public void setCommisionAmount(String commisionAmount) {
		this.commisionAmount = commisionAmount;
	}

	public Date dueDate;

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String purchase() {
		logger.info("[purchase()]-------------------inside purchase() method()---------------");
		try {
			setDiscType("");
			setDiscAmnt("");
			validate2 = "";
			validate = "";
			dicount = "";
			productlist = null;
			oderDate = null;
			targetDate = null;
			vendorName = "";
			totalPrice = "";
			totalPrice1 = "";
			flag = "none";
			phonenumber = "";
			crosstotal = "";
			customername = "";
			phoneNumber = "";
			category1 = "";
			custEmailId = "";
			custPhNo = "";
			custName = "";
			shipping_charge = "0";
			shipping_company = "";
			setPriflag2("");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setBatchProductName3(controller.salesOrder3(batchProductName3));
			productlist = new ArrayList<String>();
			for (int i = 0; i < batchProductName3.size(); i++) {
				String s = "" + batchProductName3.get(i);
				productlist.add("" + batchProductName3.get(i));

			}
			Collections.sort(productlist, String.CASE_INSENSITIVE_ORDER);
			controller.salesCustomer(buyerName, purchaseOrder);
			setResulfinal(purchaseOrder.getResulfinal1());
			if (homeMBs.size() > 0) {
				homeMBs.clear();
				for (int i = 1; i <= 5; i++) {
					SalesOrderFormMB homeMB = new SalesOrderFormMB();
					homeMB.setSerialno("" + i);
					homeMB.setProductName("");
					homeMB.setFlag("1");
					homeMB.setFlag1("1");
					homeMB.setFlag2("none");
					homeMB.setBarflag1("none");
					homeMB.setBarflag("1");
					homeMB.setBarcode1("");
					homeMBs.add(homeMB);
				}

				productName = null;

			} else {
				for (int i = 1; i <= 5; i++) {
					SalesOrderFormMB homeMB = new SalesOrderFormMB();
					homeMB.setSerialno("" + i);
					homeMB.setProductName("");
					homeMB.setFlag("1");
					homeMB.setFlag1("1");
					homeMB.setFlag2("none");
					homeMB.setBarflag1("none");
					homeMB.setBarflag("1");
					homeMB.setBarcode1("");
					homeMBs.add(homeMB);
				}
				productName = null;
			}
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		} finally {
		}
	}
	
	public String confirm() {
		logger.info("[confirm()]-------------------inside confirm() method()---------------");
		try {
			String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
			if (oderDate == null) {
				throw new DemoException("Please Choose the Order Date");
			} else if (targetDate == null) {
				throw new DemoException(
						"Please Choose the Estimated Delivery Date");
			}

			else if ((category1.equalsIgnoreCase(""))
					|| (category1.equalsIgnoreCase("Select"))) {
				throw new DemoException("Please Choose the Category");
			}

			else if (customername.equalsIgnoreCase("")) {
				throw new DemoException("Please Choose the Customer Name");
			} else if (custPhNo.equalsIgnoreCase("")) {
				throw new DemoException(
						"Please Choose the Customer Phone Number");
			}else if(currency.equalsIgnoreCase("") || currency == null){
				throw new DemoException("Please Choose the Currency");
			}
			
			setValidate2("");
			setValidate(validate);
			BigDecimal temp = BigDecimal.valueOf(0);
			BigDecimal quant = BigDecimal.valueOf(0);
			rollQuan1 = BigDecimal.valueOf(0);
			for (int i = 0; i < homeMBs.size(); i++) {
				try {
					if (!homeMBs.get(i).getProductName().equals("")) {
						BigDecimal bg = BigDecimal.valueOf(0);
						temp = temp.add(new BigDecimal(homeMBs.get(i)
								.getNetAmount().replace(",", "")));
						quant = quant.add(new BigDecimal(homeMBs.get(i)
								.getQuantity()));
						
					} else {
					}
				} catch (NullPointerException e) {
				}
			}
			
			purchaseOrder.setTotalnumberofcount("" + quant);
			purchaseOrder.setQuantity("" + quant);
			purchaseOrder.setQuantity1("" + quant);
			purchaseOrder.setOrderDate(oderDate);
			purchaseOrder.setTargentDate(targetDate);
			purchaseOrder.setShipping_charge(shipping_charge);
			purchaseOrder.setShipping_company(shipping_company);
			purchaseOrder.setTotalPrice(totalPrice.replace(",", ""));
			purchaseOrder.setCrosstotal(crosstotal);
			purchaseOrder.setBaseCurrency(baseCurrency);
			int count = 0;
			for (int i = 0; i < homeMBs.size(); i++) {
				if (!homeMBs.get(i).getProductName().equals("")) {
					
					purchaseOrder.setQuantity(homeMBs.get(i).getQuantity());
					purchaseOrder.setBatchProductName(homeMBs.get(i)
							.getProductName());
					purchaseOrder.setDelayreason(homeMBs.get(i)
							.getDicountAmount());
					controller.salesOrder4(purchaseOrder);
					try {
						if (purchaseOrder.getBatchProductName()
								.equalsIgnoreCase("")) {
							throw new DemoException(
									"Please Fill All the Fileds");
						}
						if (homeMBs.get(i).getQuantity().equals("")) {
							throw new DemoException("Please Enter the Quantity");
						} else if (!quantity.matches("[0-9]{1,5}")) {
							throw new DemoException(
									"Quantity should be in whole Number");
						} else if (quantity.equals("0")) {
							throw new DemoException(
									"Quantity should not be zero");
						}
						else if(new BigDecimal(purchaseOrder.getQuantity1()).compareTo(
								new BigDecimal(purchaseOrder.getQuantity()))==-1){
							setValidate("");
							throw new DemoException("Only "+purchaseOrder.getQuantity1()+" quantity available in stock for "
								+purchaseOrder.getBatchProductName());
						}						
						
					} catch (NullPointerException e) {
						throw new DemoException("Please Fill All the Fileds");
					}

				} else {
					count++;
				}
			}
			if (count == homeMBs.size()) {
				throw new DemoException("Please Choose Product Name");
			}

			purchaseOrder.setTotalAmount(""
					+ GenerateEmployee.numberFormat.format(temp.setScale(0,
							BigDecimal.ROUND_HALF_UP)));

			purchaseOrder.setPhonenumber(phoneNumber);
			purchaseOrder.setSalesorderdate(oderDate);
			purchaseOrder.setDeliverydate(targetDate);
			purchaseOrder.setCustName(custName);
			purchaseOrder.setCurrency(currency);
			purchaseOrder.setCustomername(customername);
			
			purchaseOrder.setCustPhNo(custPhNo);
			purchaseOrder.setCustEmailId(custEmailId);
			purchaseOrder.setShipping_charge(shipping_charge);
			purchaseOrder.setShipping_company(shipping_company);
			purchaseOrder.setCategory(category1);
			controller.salesSave(purchaseOrder);
			String tempdisz = "0", type = "", amnt = "";

			for (int i = 0; i < homeMBs.size(); i++) {
				if (!homeMBs.get(i).getProductName().equals("")) {
					purchaseOrder.setPrice(homeMBs.get(i).getPrice());
					purchaseOrder.setQuantity(homeMBs.get(i).getQuantity());
					purchaseOrder.setBatchProductName(homeMBs.get(i)
							.getProductName());
					purchaseOrder.setNetAmount(homeMBs.get(i).getNetAmount());
					if (!homeMBs.get(i).getProductName().equalsIgnoreCase("")) {
						QuickSaleMB qs = new QuickSaleMB();
						try {
							if (homeMBs.get(i).getDicount()
									.equalsIgnoreCase("%")) {
								String x = ""
										+ (new BigDecimal(homeMBs.get(i)
												.getPrice().replace(",", ""))
												.multiply(new BigDecimal(
														homeMBs.get(i)
																.getQuantity())));
								
								tempdisz = ""
										+ new BigDecimal(x)
												.multiply(
														new BigDecimal(
																homeMBs.get(i)
																		.getDicountAmount()))
												.divide(BigDecimal.valueOf(100));
								type = homeMBs.get(i).getDicount();
								amnt = homeMBs.get(i).getDicountAmount();
							} else if (homeMBs.get(i).getDicount()
									.equalsIgnoreCase("Rp")) {

								tempdisz = homeMBs.get(i).getDicountAmount()
										.replace(",", "");
								type = homeMBs.get(i).getDicount();
								amnt = homeMBs.get(i).getDicountAmount();
							} else if (homeMBs.get(i).getDicount()
									.equalsIgnoreCase("")) {
								tempdisz = "0";
								type = "RP";
								amnt = "0";
							}
						} catch (Exception e) {
							logger.error("Inside Exception", e);
							tempdisz = "0";
							type = "RP";
							amnt = "0";
						}

						purchaseOrder.setDiscountzType(type);
						purchaseOrder.setDiscountzAmount(tempdisz);
						purchaseOrder.setDiscountz(amnt);
					}

					controller.salesconfirm(purchaseOrder);
					
				} else {
					count++;
				}
			}

			vendorName = "" + purchaseOrder.getAddress();

			setOrderNumber(purchaseOrder.getSalesIdReference());
			List<SalesOrderFormMB> qsMB = new ArrayList<SalesOrderFormMB>();
			/* BigDecimal temp1=BigDecimal.valueOf(0); */
			String temp1 = "";
			BigDecimal x = BigDecimal.valueOf(0);
			for (int i = 0; i < homeMBs.size(); i++) {
				dicountAmount = "";
				SalesOrderFormMB uu = new SalesOrderFormMB();
				
				if (!homeMBs.get(i).getProductName().equalsIgnoreCase("")) {
					if (homeMBs.get(i).getPrice() == null) {
						throw new DemoException("Please Enter all the Fields");
					}

					dicountAmount = "" + temp1;
					uu.setDicountAmount("" + temp1);
					uu.setNetAmount(homeMBs.get(i).getNetAmount());
					uu.setProductName(homeMBs.get(i).getProductName());
					uu.setSerialno(homeMBs.get(i).getSerialno());
					uu.setRoll(homeMBs.get(i).getRoll());
					uu.setQuantity(homeMBs.get(i).getQuantity());
					uu.setUnit(homeMBs.get(i).getUnit());
					uu.setCurrency(currency);
					uu.setPrice(homeMBs.get(i).getPrice());
					try {
						if (homeMBs.get(i).getDicount().equalsIgnoreCase("%")) {
							type = homeMBs.get(i).getDicount();
							amnt = homeMBs.get(i).getDicountAmount();
						} else if (homeMBs.get(i).getDicount()
								.equalsIgnoreCase("Rp")) {
							type = homeMBs.get(i).getDicount();
							amnt = homeMBs.get(i).getDicountAmount();
						} else if (homeMBs.get(i).getDicount()
								.equalsIgnoreCase("")) {
							type = "RP";
							amnt = "0";
						}
					} catch (Exception e) {
						logger.error("Inside Exception", e);
						type = "RP";
						amnt = "0";
					}
					uu.setDicount(type);
					uu.setDicountAmount(amnt);

					qsMB.add(uu);

					/* homeMBs.set((i), uu); */
					temp1 = "";
					/* homeMBs.add(uu); */
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			od = sdf.format(oderDate);
			td = sdf.format(targetDate);
			homeMBs.clear();
			homeMBs = (ArrayList<SalesOrderFormMB>) qsMB;
			setCurrencyAmount(purchaseOrder.getCurrencyAmount());
			setBaseCurrency(baseCurrency);
			return "saleNewSuccess";
		}

		catch (DemoException ei) {
			setValidate2(ei.getMessage());
			logger.error("Inside Exception", ei);
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		}

	}

	public void valueChange(ValueChangeEvent v) {
		logger.info("[valueChange()]-------------------inside valueChange() method()---------------");
		String serialNo = "";
		try {
			setValidate("");
			productName = "" + v.getNewValue();
			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			serialNo = v.getComponent().getAttributes().get("serial")
					.toString();
			purchaseOrder.setBatchProductName(productName);
			controller.getpurchaseInfo(purchaseOrder);
			barcode1 = purchaseOrder.getBarcode1();
			setUnit("" + purchaseOrder.getUnit());
			marginPrice = "" + purchaseOrder.getMarginPrice();
			price = purchaseOrder.getSellingPrice();
			
			rollList = (ArrayList<String>) controller.getRollQuanList(
					productName, rollList);
			if (rollList.size() == 0) {
				throw new DemoException(
						"There is no stock for this Product. Please select another Product");
			}

			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setSerialno(serialNo);
			homeMB.setPrice(price);
			homeMB.setFlag("none");
			homeMB.setProductName(productName);
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setRollList(rollList);
			homeMB.setTflag1("1");
			homeMB.setUflag("1");
			homeMB.setDflag("1");
			homeMB.setTflag2("none");
			homeMB.setBarflag("none");
			homeMB.setBarflag1("1");
			homeMB.setBarcode1(barcode1);
			homeMB.setUnit(unit);
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
			purchaseOrder.setTotalnumberofcount("" + rollList.get(0));
			

			controller.getProductName();
			for (int i = 0; i < homeMBs.size(); i++) {
				logger.info("[redirectPage()]-------------------inside for loop---------------");
				logger.debug("[redirectPage()]-------------------serial num--------------->"+homeMBs.get(i).getSerialno());
				logger.debug("[redirectPage()]-------------------price--------------->"+homeMBs.get(i).getPrice());
				logger.debug("[redirectPage()]-------------------flag--------------->"+homeMBs.get(i).getFlag());
				logger.debug("[redirectPage()]-------------------Product Name--------------->"+homeMBs.get(i).getProductName());
			}
			purchaseOrder.setTotalQuan2("0");
			BigDecimal bb = BigDecimal.valueOf(0);
			for (int i = 0; i < homeMBs.size(); i++) {
				if (!homeMBs.get(i).getProductName().equalsIgnoreCase("")) {
					if (homeMBs.get(i).getProductName()
							.equalsIgnoreCase(productName)) {
						try{
							if (!homeMBs.get(i).getQuantity().equalsIgnoreCase("")) {
								bb = bb.add(new BigDecimal(homeMBs.get(i)
										.getQuantity()));
								purchaseOrder.setTotalQuan2("" + bb);
								
							}
						}catch(Exception e){
							logger.error("Inside Exception", e);
							purchaseOrder.setTotalQuan2("" + bb);
						}						
					}
				}
			}
		} catch (DemoException e) {
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setSerialno(serialNo);
			homeMB.setDflag("none");
			homeMB.setFlag("1");
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
		}
	}

	public void rollChange(ValueChangeEvent rollval) {
		logger.info("[rollChange()]-------------------inside rollChange() method()---------------");
		String serialNo = rollval.getComponent().getAttributes().get("serial2")
				.toString();
		String productname = rollval.getComponent().getAttributes()
				.get("product2").toString();
		String barcode = rollval.getComponent().getAttributes().get("barcode")
				.toString();
		String price = rollval.getComponent().getAttributes().get("price")
				.toString();
		try {
			setValidate("");
			roll = "" + rollval.getNewValue();

			if (productname == null || productname.equalsIgnoreCase("")
					|| productname.contains("Select")
					|| productname.equalsIgnoreCase("Select")) {
				throw new DemoException("*Enter the Product Name");
			} else if (price.equalsIgnoreCase("")) {
				throw new DemoException("*Enter the Price");
			}

			purchaseOrder.setRollID(roll);
			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.getRollQuantity(purchaseOrder);
			setStockin(String.valueOf(purchaseOrder.getRollStockIn()));
			BigDecimal stock = new BigDecimal(purchaseOrder.getRollStockIn());
			stock = stock.setScale(2, RoundingMode.CEILING);
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setSerialno(serialNo);
			homeMB.setPrice(price);
			homeMB.setFlag("none");
			homeMB.setUflag("1");
			homeMB.setStockin(stock.toString());
			homeMB.setProductName(productname);
			homeMB.setDicount("");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			homeMB.setRoll(roll);
			homeMB.setRflag1("none");
			homeMB.setRflag2("1");
			homeMB.setTflag1("1");
			homeMB.setBarcode1(barcode);
			homeMB.setBarflag("none");
			homeMB.setBarflag1("1");
			homeMB.setTflag2("none");
			homeMB.setUnit(unit);
			homeMB.setDicountAmount("");
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);

		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setFlag("1");
			homeMB.setUflag("none");
			homeMB.setTflag1("1");
			homeMB.setTflag2("none");
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setUnit(unit);
			homeMB.setStockin("");
			homeMB.setProductName("");
			homeMB.setDicount("");
			homeMB.setRoll("");
			homeMB.setDicountAmount("");
			homeMB.setRflag2("none");
			homeMB.setBarcode1("");
			homeMB.setBarflag1("none");
			homeMB.setBarflag("1");
			homeMB.setRflag1("1");
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
		} catch (NullPointerException n) {
			logger.error("Inside Exception", n);
			setValidate(n.getMessage());
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setSerialno(serialNo);
			homeMB.setFlag("1");
			homeMB.setUflag("none");
			homeMB.setRflag2("none");
			homeMB.setRflag1("1");
			homeMB.setTflag1("1");
			homeMB.setStockin("");
			homeMB.setTflag2("none");
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setUnit(unit);
			homeMB.setProductName("");
			homeMB.setBarcode1("");
			homeMB.setBarflag1("none");
			homeMB.setBarflag("1");
			homeMB.setDicount("");
			homeMB.setRoll("");
			homeMB.setDicountAmount("");
			logger.debug("home size---------->" + homeMBs.size());
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	BigDecimal rollQuan1 = BigDecimal.valueOf(0);
	BigDecimal rollQuan2 = BigDecimal.valueOf(0);

	public void quantityChange1(ValueChangeEvent vi) {
		logger.info("[quantityChange1()]-------------------inside quantityChange1() method()---------------");
		setValidate2("");
		String serialNo = "";
		String serialNo1 = "";
		BigDecimal temp1 = BigDecimal.valueOf(0);
		SalesOrderFormMB homeMB = new SalesOrderFormMB();
		try {
			serialNo = vi.getComponent().getAttributes().get("serial").toString();
			String price = vi.getComponent().getAttributes().get("price")
					.toString();
			setValidate("");
			quantity = "" + vi.getNewValue();
			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setRollID(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getRoll());
			if (quantity.equals("0")) {
				throw new DemoException("*Quantity should not be 0");
			}
			BigDecimal totalQuantity = BigDecimal.valueOf(0);
			totalQuantity = totalQuantity.add(new BigDecimal(quantity));
			purchaseOrder.setQuantity("" + totalQuantity);
			rollQuan1 = BigDecimal.valueOf(0);
			rollQuan2 = BigDecimal.valueOf(0);
			BigDecimal totquan = new BigDecimal(purchaseOrder.getTotalQuan2())
					.add(new BigDecimal(quantity));
			BigDecimal stock = new BigDecimal(
					purchaseOrder.getTotalnumberofcount());
			stock = stock.setScale(2, RoundingMode.CEILING);
			BigDecimal quantt = new BigDecimal(purchaseOrder.getQuantity());
			quantt = quantt.setScale(2, RoundingMode.CEILING);
			
			int comp = stock.compareTo(new BigDecimal(quantity));
			int comp1 = stock.compareTo((totquan));
			BigDecimal tq = BigDecimal.valueOf(0);
			
			purchaseOrder.setBatchProductName(""
					+ homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getProductName());

			if (purchaseOrder.getBatchProductName().equalsIgnoreCase("")) {
				throw new DemoException("*Select product name");
			} else if (price.equalsIgnoreCase("")) {
				throw new DemoException("*Enter the Price");
			} else if (purchaseOrder.getQuantity().equalsIgnoreCase("")) {
				throw new DemoException("*Enter the quantity");
			}
			controller.salesOrder4(purchaseOrder);
			if(new BigDecimal(purchaseOrder.getQuantity1()).compareTo(new BigDecimal(purchaseOrder.getQuantity()))==-1){
				temp1=BigDecimal.valueOf(0);
				throw new DemoException("Only "+purchaseOrder.getQuantity1()+" quantity available in stock for "
					+purchaseOrder.getBatchProductName());
			
			}
			String finalRoll1 = "";
			String finalRoll2 = "";
			BigDecimal totquant = BigDecimal.valueOf(0);

			BigDecimal finalQuan1 = BigDecimal.valueOf(0);
			BigDecimal finalQuan2 = BigDecimal.valueOf(0);
			roll2 = new ArrayList<SalesOrderFormMB>();
			homeMB = new SalesOrderFormMB();

			if (homeMBs.size() > 0) {
				logger.info("[quantityChange1()]-------------------inside if loop---------------");
				logger.debug("-------------------inside if loop-----------------");
				homeMB.setSerialno(serialNo);
				homeMB.setPrice(""
						+ (String) vi.getComponent().getAttributes()
								.get("price"));
				homeMB.setFlag("none");
				homeMB.setProductName(""
						+ homeMBs.get(Integer.parseInt(serialNo) - 1)
								.getProductName());
				homeMB.setRollQFlag1("none");
				homeMB.setRollQFlag2("1");
				homeMB.setTflag1("none");
				homeMB.setTflag2("1");
				homeMB.setPriflag1("none");
				homeMB.setPriflag2("1");
				homeMB.setBarcode1(homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getBarcode1());
				homeMB.setBarflag1("1");
				homeMB.setBarflag("none");
				homeMB.setRoll1(roll2);
				homeMB.setFlag1("none");
				homeMB.setFlag2("1");
				homeMB.setUflag("1");
				homeMB.setQuantity(quantity);
				homeMB.setUnit(unit);
				temp1 = (new BigDecimal(homeMBs.get(Integer.parseInt(serialNo) - 1).getPrice()).multiply(new BigDecimal(quantity)));
				
				homeMB.setNetAmount("" + temp1);
				
				homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
			} else {
				throw new DemoException("error");
			}
			BigDecimal temp = BigDecimal.valueOf(0);
			for (int i = 0; i < homeMBs.size(); i++) {
				try {
					if (!homeMBs.get(i).getPrice().equals("")) {
						
						temp = temp.add(new BigDecimal(homeMBs.get(i)
								.getNetAmount().replace(",", "")));
					} else {
					}
				} catch (NullPointerException e) {
				}
			}

			BigDecimal newqunt = new BigDecimal("0");
			String rollTempId = "";
			BigDecimal givenquan = new BigDecimal("0");

			setCrosstotal("" + (temp));
			setTotalPrice("" + (temp));
		} catch (DemoException e) {
			
			homeMB.setSerialno(serialNo);
			homeMB.setPrice(""
					+ (String) vi.getComponent().getAttributes()
							.get("price"));
			homeMB.setFlag("none");
			homeMB.setProductName(""
					+ homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getProductName());
			homeMB.setRollQFlag1("none");
			homeMB.setRollQFlag2("1");
			homeMB.setTflag1("none");
			homeMB.setTflag2("1");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			homeMB.setBarcode1(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getBarcode1());
			homeMB.setBarflag1("1");
			homeMB.setBarflag("none");
			homeMB.setRoll1(roll2);
			homeMB.setFlag1("none");
			homeMB.setFlag2("1");
			homeMB.setUflag("1");
			homeMB.setQuantity(quantity);
			homeMB.setUnit(unit);
			homeMB.setNetAmount("" + temp1);
			homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
			setValidate2("");
			setValidate(e.getMessage());
			
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}


	public String edit() {
		logger.info("[edit()]-------------------inside edit() method()---------------");
		try {
			for (int j = 0; j < productlist.size(); j++) {
				if (homeMBs.get(Integer.parseInt(serialno) - 1).getProductName().equals(productlist.get(j))) {
					
					logger.info("[edit()]-------------------inside if condition---------------");
				} else {
					logger.info("[edit()]-------------------inside else condition---------------");
				}
			}
			
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setSerialno(serialno);
			homeMB.setProductName("");
			homeMB.setPrice("");
			homeMB.setFlag("1");
			homeMB.setFlag1("1");
			homeMB.setFlag2("none");
			homeMB.setRflag1("1");
			homeMB.setRflag2("none");
			homeMB.setUnit("");
			homeMB.setTflag1("1");
			homeMB.setTflag2("none");
			homeMB.setBarflag1("none");
			homeMB.setBarcode1("");
			homeMB.setBarflag("1");
			homeMB.setBarflag1("none");
			homeMB.setBarflag("1");
			homeMB.setBarcode1("");
			BigDecimal temp = BigDecimal.valueOf(0);
			BigDecimal grosstemp = BigDecimal.valueOf(0);
			
			if (!netAmount.equalsIgnoreCase("") && !netAmount.equalsIgnoreCase("0")) {
				
				temp = temp.add((new BigDecimal(crosstotal.replace(",", "")))
						.subtract(new BigDecimal(netAmount.replace(",", ""))));
			} else {
			
				if (crosstotal.equalsIgnoreCase("")) {
					
					temp = temp.add((grosstemp));
				} else {
					
					temp = temp.add(new BigDecimal(crosstotal.replace(",", "")));
				}
			}
			homeMBs.set(Integer.parseInt(serialno) - 1, homeMB);
			setCrosstotal("" +temp);
			setTotalPrice("" +temp);

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";
	}

	public void customerNamez(ValueChangeEvent v) {
		logger.info("[customerNamez()]-------------------inside customerNamez method()---------------");
		try {
			String s = "" + v.getNewValue();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setBuyerName(controller.customerNameChange(s));
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public String redirectHome2() {
		logger.info("[redirectHome2()]-------------------inside redirectHome2 method()---------------");
		try {
			price = "";
			productName = "";
			logger.debug("list size----------------->" + homeMBs.size());
			int j = 0;
			j = homeMBs.size();
			int i = 0;
			validate = "";
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setSerialno("" + (homeMBs.size() + 1));
			homeMB.setProductName("");
			homeMB.setFlag("1");
			homeMB.setFlag1("1");
			homeMB.setFlag2("none");
			homeMB.setBarflag1("none");
			homeMB.setBarflag("1");
			homeMB.setBarcode1("");
			homeMBs.add(homeMB);
			
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";
	}

	public void discountChange(ValueChangeEvent vi) {
		logger.info("[discountChange()]-------------------inside discountChange method()---------------");
		String serialNo = "", net = "", disc = "";
		String temp1 = "0";
		
		BigDecimal b1 = BigDecimal.valueOf(0);
		BigDecimal b2 = BigDecimal.valueOf(0);
		if (!vi.getNewValue().toString().equalsIgnoreCase(null)||!vi.getNewValue().toString().equalsIgnoreCase("")) {
		try {
			serialNo = vi.getComponent().getAttributes().get("serial").toString();
			tempDiscount = vi.getComponent().getAttributes().get("discount").toString();
			net = vi.getComponent().getAttributes().get("net").toString();
			setValidate("");
			disc = (String) vi.getNewValue();
			if (!vi.getNewValue().toString().equalsIgnoreCase("")) {
				if (tempDiscount.equalsIgnoreCase("%")) {
					
					int i = Integer.parseInt(vi.getNewValue().toString());
					if (i < 100) {
						b1 = (new BigDecimal(net)
								.multiply(new BigDecimal(disc)))
								.divide(BigDecimal.valueOf(100));

					} else {
						throw new DemoException("*Enter discount below 100");
					}
				} else if (tempDiscount.equals("Rp")) {
					disAmount = ""
							+ Integer.parseInt(vi.getNewValue().toString());
					temp1 = "0";
					b1 = new BigDecimal(disc);
				}

			} else if (vi.getNewValue().toString().equalsIgnoreCase("")) {
			}
			b2 = new BigDecimal(net).subtract(b1);

			String tempTotal = "0";
			String d = "0";
			String a = "0";
			String b = "0";
			if (homeMBs.size() > 0) {
				logger.info("[discountChange()]-------------------inside if loop---------------");
				SalesOrderFormMB homeMB = new SalesOrderFormMB();
				homeMB.setSerialno(serialNo);
				homeMB.setPrice(homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getPrice());
				homeMB.setFlag("none");
				homeMB.setDflag("none");
				homeMB.setFlag1("none");
				homeMB.setFlag2("1");
				homeMB.setUflag("1");
				homeMB.setPriflag1("none");
				homeMB.setPriflag2("1");
				homeMB.setTflag1("none");
				homeMB.setTflag2("1");
				homeMB.setRollQFlag1("none");
				homeMB.setRollQFlag2("1");
				homeMB.setDicountAmount(homeMBs.get(
						Integer.parseInt(serialNo) - 1).getDiscountAmount());
				homeMB.setDicountAmount(homeMBs.get(
						Integer.parseInt(serialNo) - 1).getDiscountAmount());
				homeMB.setRollQuantities(homeMBs.get(
						Integer.parseInt(serialNo) - 1).getRollQuantities());
				homeMB.setUnit(homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getUnit());
				homeMB.setDflag3("none");
				homeMB.setDflag2("1");
				homeMB.setDflag1("1");
				homeMB.setProductName(""
						+ homeMBs.get(Integer.parseInt(serialNo) - 1)
								.getProductName());
				homeMB.setRflag1("none");
				homeMB.setRflag2("1");
				homeMB.setStockin(homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getStockin());
				homeMB.setBarcode1(homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getBarcode1());
				homeMB.setBarflag1("1");
				homeMB.setBarflag("none");
				homeMB.setRoll(homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getRoll());
				homeMB.setQuantity(""
						+ homeMBs.get(Integer.parseInt(serialNo) - 1)
								.getQuantity());
				homeMB.setDicount(homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getDicount());
				homeMB.setRoll1(homeMBs.get(Integer.parseInt(serialNo) - 1)
						.getRoll1());
				homeMB.setDicountAmount(vi.getNewValue().toString());
				homeMB.setNetAmount("" + b2);
				purchaseOrder.setShipping_charge(shipping_charge);
				homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);

			} else {
				throw new DemoException("error");
			}
			String temp = "0";
			for (int i = 0; i < homeMBs.size(); i++) {
				logger.info("[discountChange()]-------------------inside for loop()---------------");
				try {
					if (!homeMBs.get(i).getPrice().equals("")) {
						logger.info("[discountChange()]-------------------inside for loop() if condition---------------");
						temp = ""
								+ (new BigDecimal(temp).add(new BigDecimal(
										homeMBs.get(i).getNetAmount()
												.replace(",", ""))));
					} else {
						logger.info("[discountChange()]-------------------inside for loop() else condition---------------");
					}
				} catch (NullPointerException e) {
					logger.error("Inside Exception", e);
				}
			}
			setCrosstotal(temp);
			setTotalPrice(temp);
			logger.debug("----success-----");
		}
		catch (DemoException e) {
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setSerialno(serialNo);
			homeMB.setProductName(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getProductName());
			homeMB.setRoll(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getRoll());
			homeMB.setPrice(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getPrice());
			homeMB.setUnit(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getUnit());
			homeMB.setQuantity(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getQuantity());
			homeMB.setDicount(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getDicount());
			homeMB.setDicountAmount("");
			homeMB.setNetAmount(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getNetAmount());
			homeMB.setFlag("none");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			homeMB.setUflag("1");
			homeMB.setStockin(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getStockin());
			homeMB.setBarcode1(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getBarcode1());
			homeMB.setBarflag1("1");
			homeMB.setBarflag("none");
			homeMB.setFlag1("none");
			homeMB.setDflag("none");
			homeMB.setDflag1("1");
			homeMB.setDflag3("1");
			homeMB.setDflag2("none");
			homeMB.setFlag2("1");
			homeMB.setRflag1("none");
			homeMB.setRflag2("1");
			homeMB.setRollQFlag1("none");
			homeMB.setRollQFlag2("1");
			homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		}
	}

	public void discounttypeChange(ValueChangeEvent vi) {
		logger.info("[discounttypeChange()]-------------------inside discounttypeChange method()---------------");
		try {
			setValidate("");
			String serialNo = "";
			serialNo = vi.getComponent().getAttributes().get("serial").toString();
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setSerialno(serialNo);
			homeMB.setPrice(homeMBs.get(Integer.parseInt(serialNo) - 1).getPrice());
			homeMB.setFlag("none");
			homeMB.setFlag1("none");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			homeMB.setFlag2("1");
			homeMB.setUflag("1");
			homeMB.setDflag("none");
			
			homeMB.setBarcode1(homeMBs.get(Integer.parseInt(serialNo) - 1).getBarcode1());
			homeMB.setBarflag1("1");
			homeMB.setBarflag("none");
			homeMB.setDflag1("1");
			homeMB.setProductName(""+ homeMBs.get(Integer.parseInt(serialNo) - 1).getProductName());
			
			homeMB.setUnit(homeMBs.get(Integer.parseInt(serialNo) - 1).getUnit());
			homeMB.setQuantity(""+ homeMBs.get(Integer.parseInt(serialNo) - 1).getQuantity());
			
			homeMB.setDicount(vi.getNewValue().toString());
			homeMB.setDicountAmount(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getDicountAmount());
			homeMB.setNetAmount(homeMBs.get(Integer.parseInt(serialNo) - 1)
					.getNetAmount());
			homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
		}

		catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
	}

	public String getTotalPrice1() {
		return totalPrice1;
	}

	public void setTotalPrice1(String totalPrice1) {
		this.totalPrice1 = totalPrice1;
	}

	public void priceChange(ValueChangeEvent values) {
		logger.info("[priceChange()]-------------------inside priceChange method()---------------");
		String serialNo = "";
		try {
			serialNo = values.getComponent().getAttributes().get("serial2")
					.toString();
			String pName = values.getComponent().getAttributes()
					.get("product2").toString();
			String barcode = values.getComponent().getAttributes()
					.get("barcode").toString();
			String price_val = values.getNewValue().toString();
			if ((pName.equalsIgnoreCase(""))
					|| (pName.equalsIgnoreCase("select"))) {
				throw new DemoException("Please select the product name");
			}
			if (price_val.equals("0")) {
				throw new DemoException("*Price should not be 0");
			}
			if (!price_val.equals("")) {
				String res = VendorRegisterFormMB.isFloatValue(price_val);
				if (res.equalsIgnoreCase("Match")) {
				} else {
					throw new DemoException("*Price accept only numeric values");
				}
			}

			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setSerialno(serialNo);
			homeMB.setPrice(price_val);
			homeMB.setFlag("none");
			homeMB.setUflag("1");
			homeMB.setTflag1("1");
			homeMB.setTflag2("none");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			homeMB.setUnit(unit);
			homeMB.setProductName(pName);
			homeMB.setBarcode1(barcode);
			homeMB.setBarflag("none");
			homeMB.setBarflag1("1");
			homeMB.setDicount("");
			homeMB.setRollList(rollList);
			homeMB.setDicountAmount("");
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
		} catch (DemoException in) {
			logger.error("Inside Exception", in);
			setValidate(in.getMessage());
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setSerialno(serialNo);
			// homeMB.setPrice("");
			homeMB.setFlag("1");
			homeMB.setUflag("none");
			homeMB.setTflag1("1");
			homeMB.setTflag2("none");
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setUnit(unit);
			homeMB.setProductName("");
			homeMB.setDicount("");
			homeMB.setBarcode1("");
			homeMB.setBarflag1("none");
			homeMB.setBarflag("1");
			homeMB.setRollList(null);
			homeMB.setDicountAmount("");
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
		}
	}

	public ArrayList<SalesOrderFormMB> roll1 = new ArrayList<SalesOrderFormMB>();
	public ArrayList<SalesOrderFormMB> roll2;

	public ArrayList<SalesOrderFormMB> getRoll1() {
		return roll1;
	}

	public void setRoll1(ArrayList<SalesOrderFormMB> roll1) {
		this.roll1 = roll1;
	}

	public ArrayList<SalesOrderFormMB> getRoll2() {
		return roll2;
	}

	public void setRoll2(ArrayList<SalesOrderFormMB> roll2) {
		this.roll2 = roll2;
	}

	public void rollQuanChange(ValueChangeEvent values) {
		logger.info("[rollQuanChange()]-------------------inside rollQuanChange method()---------------");
		String serialNo = "";
		try {
			String quantity1 = (String) values.getNewValue();

			serialNo = values.getComponent().getAttributes().get("serial2")
					.toString();
			String pName = values.getComponent().getAttributes()
					.get("product2").toString();
			String barcode = values.getComponent().getAttributes()
					.get("barcode").toString();
			String price = values.getComponent().getAttributes().get("price")
					.toString();
			if ((pName.equalsIgnoreCase(""))
					|| (pName.equalsIgnoreCase("select"))) {
				throw new DemoException("Please select the product name");
			}
			if (quantity1.equals("0")) {
				throw new DemoException("*Quantity should not be 0");
			}
			if (!quantity1.equals("")) {
				String res = VendorRegisterFormMB.isNumberValid(quantity1);
				if (res.equalsIgnoreCase("Match")) {
				} else {
					throw new DemoException("*Enter Integer values");
				}
			}

			int quant = Integer.parseInt(quantity1);

			BigDecimal bd1 = new BigDecimal(rollList.size());
			BigDecimal bd2 = new BigDecimal(quant);
			if (bd1.compareTo(bd2) == -1) {
				throw new DemoException("*Only " + bd1
						+ " rolls are available for this product");
			}

			roll1.clear();
			roll2 = new ArrayList<SalesOrderFormMB>();

			for (int i = 1; i <= quant; i++) {
				SalesOrderFormMB soMB = new SalesOrderFormMB();
				soMB.setSerialno1("" + i);
				soMB.setInnerserial1("" + i);
				soMB.setRoll("");
				soMB.setQuantity("");
				roll2.add(soMB);
			}
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setSerialno(serialNo);
			homeMB.setPrice(price);
			homeMB.setFlag("none");
			homeMB.setUflag("1");
			homeMB.setTflag1("1");
			homeMB.setTflag2("none");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			homeMB.setUnit(unit);
			homeMB.setProductName(pName);
			homeMB.setBarcode1(barcode);
			homeMB.setBarflag("none");
			homeMB.setBarflag1("1");
			homeMB.setDicount("");
			homeMB.setRollList(rollList);
			homeMB.setDicountAmount("");
			homeMB.setRollQFlag1("none");
			homeMB.setRollQFlag2("1");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			homeMB.setRoll1(roll2);
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
		} catch (DemoException in) {
			logger.error("Inside Exception", in);
			setValidate(in.getMessage());
		}
	}

	public void rolldropChange(ValueChangeEvent rollval) {
		logger.info("[rolldropChange()]-------------------inside rolldropChange method()---------------");
		String serialNo = rollval.getComponent().getAttributes().get("serial2")
				.toString();
		String productname = rollval.getComponent().getAttributes()
				.get("product2").toString();
		String barcode = rollval.getComponent().getAttributes().get("barcode")
				.toString();
		String price = rollval.getComponent().getAttributes().get("price")
				.toString();
		String rollQ = rollval.getComponent().getAttributes().get("rollQ")
				.toString();
		String innerserial = rollval.getComponent().getAttributes()
				.get("serial3").toString();
		String serial = rollval.getComponent().getAttributes().get("serial3")
				.toString();
		try {
			setValidate("");
			roll = "" + rollval.getNewValue();
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			if (productname == null || productname.equalsIgnoreCase("")
					|| productname.contains("Select")
					|| productname.equalsIgnoreCase("Select")) {
				throw new DemoException("*Enter the Product Name");
			} else if (price.equalsIgnoreCase("")) {
				throw new DemoException("*Enter the Price");
			}
			String[] parts = roll.split(" / ");
			ArrayList<SalesOrderFormMB> roll2 = null;
			roll2 = homeMBs.get(Integer.parseInt(serialNo) - 1).getRoll1();
			int j = homeMBs.get(Integer.parseInt(serialNo) - 1).getRoll1()
					.size();

			roll2 = new ArrayList<SalesOrderFormMB>();
			homeMB = new SalesOrderFormMB();
			for (int i = 1; i <= homeMBs.get(Integer.parseInt(serialNo) - 1).roll1
					.size(); i++) {
				if (serial.equals("" + i)) {
					SalesOrderFormMB st = new SalesOrderFormMB();
					st.setRoll(roll);
					st.setRollq(parts[0]);
					st.setInnerserial1(serial);
					st.setRflag1("none");
					st.setRflag2("1");
					st.setQuantity("");
					st.setFlag1("1");
					roll2.add(st);
				} else {
					SalesOrderFormMB st = new SalesOrderFormMB();

					if (!homeMBs.get(Integer.parseInt(serialNo) - 1).getRoll1()
							.get(i - 1).getRoll().equalsIgnoreCase("")) {
						st.setRflag2("1");
						st.setFlag2("1");
						st.setFlag1("none");
						st.setRflag1("none");
					} else {
						st.setRflag1("1");
						st.setFlag1("1");
						st.setFlag2("none");
						st.setRflag2("none");
					}

					st.setUnit(purchaseOrder.getUnit());
					st.setRollq(homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getRoll1().get(i - 1).getRollq());
					st.setQuantity(homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getRoll1().get(i - 1).getQuantity());
					st.setRoll(homeMBs.get(Integer.parseInt(serialNo) - 1)
							.getRoll1().get(i - 1).getRoll());
					st.setInnerserial1(homeMBs
							.get(Integer.parseInt(serialNo) - 1).getRoll1()
							.get(i - 1).getInnerserial1());
					
					roll2.add(st);
				}

			}

			purchaseOrder.setRollID(roll);
			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			

			homeMB.setSerialno(serialNo);
			homeMB.setPrice(price);
			homeMB.setRollQuantities(rollQ);
			homeMB.setFlag("none");
			homeMB.setUflag("1");
			/* homeMB.setStockin(stock.toString()); */
			homeMB.setProductName(productname);
			homeMB.setDicount("");
			homeMB.setPriflag1("none");
			homeMB.setPriflag2("1");
			/* homeMB.setRoll(roll); */
			homeMB.setRflag1("none");
			homeMB.setRflag2("1");
			homeMB.setTflag1("1");
			homeMB.setBarcode1(barcode);
			homeMB.setBarflag("none");
			homeMB.setBarflag1("1");
			homeMB.setTflag2("none");
			homeMB.setUnit(unit);
			homeMB.setDicountAmount("");
			homeMB.setRollQFlag1("none");
			homeMB.setRollQFlag2("1");
			homeMB.setRoll1(roll2);
			logger.debug(" try home size---------->" + homeMBs.size());
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);

			String rollName = "";
			String rollQuan = "";
			BigDecimal totquan = BigDecimal.valueOf(0);
			purchaseOrder.setTemptotquan("0");
			for (int k = 0; k < homeMBs.size(); k++) {
				if (!homeMBs.get((k)).getProductName().equalsIgnoreCase("")) {
					String l = homeMBs.get((k)).getRollQuantities();
					for (int m = 0; m < Integer.parseInt(l); m++) {

						if (!homeMBs.get(k).getRoll1().get(m).getQuantity()
								.equalsIgnoreCase("")
								&& !homeMBs.get(k).getRoll1().get(m).getRoll()
										.equalsIgnoreCase("")) {
							rollQuan = homeMBs.get(k).getRoll1().get(m)
									.getQuantity();
							rollName = homeMBs.get(k).getRoll1().get(m)
									.getRollq();
							if (rollName.equalsIgnoreCase(parts[0])) {
								totquan = totquan.add(new BigDecimal(rollQuan));
								purchaseOrder.setTemptotquan("" + totquan);
							}
						}
					}
				}
			}

		} catch (DemoException e) {
			setValidate(e.getMessage());
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setFlag("1");
			homeMB.setUflag("none");
			homeMB.setTflag1("1");
			homeMB.setTflag2("none");
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setUnit(unit);
			homeMB.setStockin("");
			homeMB.setProductName("");
			homeMB.setDicount("");
			/* homeMB.setRoll(""); */
			homeMB.setDicountAmount("");
			homeMB.setRflag2("none");
			homeMB.setBarcode1("");
			homeMB.setBarflag1("none");
			homeMB.setBarflag("1");
			homeMB.setRflag1("1");
			homeMB.setRollQFlag1("none");
			homeMB.setRollQFlag2("1");
			logger.error("Inside Exception", e);
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
		} catch (NullPointerException n) {
			logger.error("Inside Exception", n);
			// logger.error("Inside Exception",e);
			setValidate(n.getMessage());
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setSerialno(serialNo);
			// homeMB.setPrice("");
			homeMB.setFlag("1");
			homeMB.setUflag("none");
			homeMB.setRflag2("none");
			homeMB.setRflag1("1");
			homeMB.setTflag1("1");
			homeMB.setStockin("");
			homeMB.setTflag2("none");
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setUnit(unit);
			homeMB.setProductName("");
			homeMB.setBarcode1("");
			homeMB.setBarflag1("none");
			homeMB.setBarflag("1");
			homeMB.setDicount("");
			homeMB.setRoll("");
			homeMB.setDicountAmount("");
			
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public String getStockin() {
		return stockin;
	}

	public void setStockin(String stockin) {
		this.stockin = stockin;
	}

	public String barcode1;
	public String barflag = "none";
	public String barflag1 = "none";

	public String getBarcode1() {
		return barcode1;
	}

	public void setBarcode1(String barcode1) {
		this.barcode1 = barcode1;
	}

	public String getBarflag() {
		return barflag;
	}

	public void setBarflag(String barflag) {
		this.barflag = barflag;
	}

	public String getBarflag1() {
		return barflag1;
	}

	public void setBarflag1(String barflag1) {
		this.barflag1 = barflag1;
	}

	public void barcodevaluechange(ValueChangeEvent v) {
		logger.info("[barcodevaluechange()]-------------------inside barcodevaluechange method()---------------");
		String serialNo = "";
		serialNo = v.getComponent().getAttributes().get("serial").toString();
		try {
			barcode1 = "" + v.getNewValue().toString();
			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseOrder.setBarcode1(barcode1);
			controller.getbarcodeInfo(purchaseOrder);
			setPrice("" + purchaseOrder.getSellingPrice());
			setUnit("" + purchaseOrder.getUnit());
			
			marginPrice = "" + purchaseOrder.getMarginPrice();
			setProductName(purchaseOrder.getProduct_name());
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setPrice(price);
			homeMB.setUnit(unit);
			homeMB.setProductName(productName);
			homeMB.setBarcode1(barcode1);
			homeMB.setFlag("none");
			homeMB.setUflag("1");
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setRflag1("1");
			homeMB.setRflag2("none");
			homeMB.setFlag2("none");
			homeMB.setFlag1("1");
			homeMB.setTflag1("none");
			homeMB.setQuantity("");
			homeMB.setTflag2("none");
			homeMB.setDflag("1");
			homeMB.setDflag1("1");
			homeMB.setStockin("");
			homeMB.setRoll("");
			homeMB.setBarflag("none");
			homeMB.setBarflag1("1");
			homeMB.setSerialno(serialNo);
			homeMBs.set((Integer.parseInt(serialNo) - 1), homeMB);
			for (int i = 0; i < homeMBs.size(); i++) {
				String product11 = productName;
				for (int c = 0; c < productlist.size(); c++) {
					if (productlist.get(c).equals(product11)) {
						logger.debug("product  removed---" + productlist.get(c));
						productlist.remove(c);
					}
				}
			}
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			SalesOrderFormMB homeMB = new SalesOrderFormMB();
			homeMB.setSerialno(serialNo);
			homeMB.setPrice("");
			homeMB.setFlag("1");
			homeMB.setFlag1("1");
			homeMB.setFlag2("none");
			homeMB.setProductName("");
			homeMB.setRflag1("1");
			homeMB.setRflag2("none");
			homeMB.setRoll("");
			homeMB.setPriflag1("1");
			homeMB.setPriflag2("none");
			homeMB.setTflag1("none");
			homeMB.setTflag2("1");
			homeMB.setUnit("");
			homeMB.setStockin("");
			homeMB.setBarflag("1");
			homeMB.setBarflag1("none");
			homeMB.setBarcode1("");
			homeMBs.set(Integer.parseInt(serialNo) - 1, homeMB);
		}
	}

	public void phnoChange(ValueChangeEvent a) {
		logger.info("[phnoChange()]-------------------inside phnoChange method()---------------");
		setCustPhNo(a.getNewValue().toString());

	}

	public void emailChange(ValueChangeEvent a) {
		logger.info("[emailChange()]-------------------inside emailChange method()---------------");
		setCustEmailId(a.getNewValue().toString());

	}

	public void dateChange(ValueChangeEvent a) {
		logger.info("[dateChange()]-------------------inside dateChange method()---------------");
		setOderDate((Date) a.getNewValue());

	}

	public void dateChange1(ValueChangeEvent a) {
		logger.info("[dateChange1()]-------------------inside dateChange1 method()---------------");
		setTargetDate((Date) a.getNewValue());

	}

	public void discAmntListener(ValueChangeEvent cost) {
		String cost1;
		String tempDiscount = "";
		String total = "";
		cost1 = discAmnt;
		try {
			setValidate("");
			tempDiscount = discType;
			cost1 = "" + cost.getNewValue();
			BigDecimal tempval = BigDecimal.valueOf(0);
			BigDecimal temp = BigDecimal.valueOf(0);

			if (discType.equalsIgnoreCase("")) {
				throw new DemoException("*Select the discount type");
			}
			if (tempDiscount.equals("%")) {
				int i = Integer.parseInt(cost1);
				if (i < 100) {
					tempval = new BigDecimal(crosstotal).multiply(
							new BigDecimal(cost1)).divide(
							BigDecimal.valueOf(100));
					temp = (new BigDecimal(crosstotal).subtract((tempval)))
							.add(new BigDecimal(shipping_charge));
				} else {
					throw new DemoException("*Enter discount below 100");
				}
			} else if (tempDiscount.equals("Rp")) {
				disAmount = "" + Integer.parseInt(cost1);
				if (new BigDecimal(crosstotal).compareTo(new BigDecimal(
						disAmount)) == -1) {
					throw new DemoException(
							"*Discount should be  less than total price");
				}
				temp = (new BigDecimal(crosstotal).subtract(new BigDecimal(
						disAmount))).add(new BigDecimal(shipping_charge));
			}

			
			BigDecimal bgg1 = BigDecimal.valueOf(0);
			bgg1 = temp.setScale(0, BigDecimal.ROUND_HALF_UP);
			setTotalPrice("" + (bgg1));
		} catch (NumberFormatException n) {
			logger.error("Inside Exception", n);
			cost1 = "0";
			BigDecimal temp = (new BigDecimal(crosstotal).add(new BigDecimal(
					cost1)));
			BigDecimal bgg1 = BigDecimal.valueOf(0);
			bgg1 = temp.setScale(0, BigDecimal.ROUND_HALF_UP);
			setTotalPrice("" + (bgg1));
		} catch (NullPointerException e) {
			logger.error("Inside Exception", e);
			cost1 = "0";
			BigDecimal temp = (new BigDecimal(crosstotal).add(new BigDecimal(
					cost1)));
			BigDecimal bgg1 = BigDecimal.valueOf(0);
			bgg1 = temp.setScale(0, BigDecimal.ROUND_HALF_UP);
			setTotalPrice("" + (bgg1));
		} catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
		}

	}

	public boolean customerflag = false;

	public boolean isCustomerflag() {
		return customerflag;
	}

	public void setCustomerflag(boolean customerflag) {
		this.customerflag = customerflag;
	}

	public String validate1;
	public String getValidate1() {
		return validate1;
	}

	public void setValidate1(String validate1) {
		this.validate1 = validate1;
	}

	

	public String customerback() {
		logger.debug("customer back");
		customerflag = false;
		customerflag1 = false;
		return "businesspartner";
	}

	/* Customer Registration Form in Business partner module */

	public boolean customerflag1 = false;

	public boolean isCustomerflag1() {
		return customerflag1;
	}

	public void setCustomerflag1(boolean customerflag1) {
		this.customerflag1 = customerflag1;
	}

	public String customerDialog() {
		logger.info("[customerDialog()]-------------------inside customerDialog() method()---------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		customerflag1 = false;
		setCustomerSuccessFlag(false);
		logger.debug("flag -- " + customerflag1);
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setCountrys(controller.getCountry());
			customerflag1 = false;
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			setCustomername("");
			setSalesorderdate(null);
			setShipingaddress("");
			setState("");
			setCountryID("");
			setCategoryName("");
			setPhonenumber("");
			setEmail("");
			setTaxnumber("");
			setFreelancerName("");
			setNote("");
			setCity("");
			setValidate(null);setValidate1(null);
			setValidate2(null);
			setCustomerSuccessFlag(false);
			setCustomerflag1(false);
		}
		return "businesspartner";
	}

	private boolean customerSuccessFlag = false;

	public boolean isCustomerSuccessFlag() {
		return customerSuccessFlag;
	}

	public void setCustomerSuccessFlag(boolean customerSuccessFlag) {
		this.customerSuccessFlag = customerSuccessFlag;
	}

	public String cusnamecheck() {
		logger.info("[cusnamecheck()]-------------------inside cusnamecheck() method()---------------");
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext context1 = FacesContext.getCurrentInstance();
		DemoController controller = null;
		try {

			Map<String, String> params = context1.getExternalContext()
					.getRequestParameterMap();
			String name = params.get("param2");

			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String status = controller.getCustomerVerification(name);
			if (status.equalsIgnoreCase("Success")) {
				context.execute("checkFail1();");
				logger.info("[cusnamecheck()]-------------------inside if condition status equal success---------------");
			} else {
				context.execute("checkSuccess1();");
				logger.info("[cusnamecheck()]-------------------inside else condition status ---------------");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";

	}

	public String hideDialogBox() {
		logger.info("[hideDialogBox()]-------------------inside hideDialogBox() method()---------------");
		setCustomerflag1(false);
		setCustomerSuccessFlag(false);
		return "";

	}

	public String purchase1() {
		logger.info("[purchase1()]-------------------inside purchase1() method()---------------");
		try {
			setDiscType("");
			setDiscAmnt("");
			validate2 = "";
			validate = "";
			dicount = "";
			/* productlist=null; */
			oderDate = null;
			targetDate = null;
			vendorName = "";
			totalPrice = "";
			totalPrice1 = "";
			flag = "none";
			phonenumber = "";
			crosstotal = "";
			customername = "";
			phoneNumber = "";
			category1 = "";
			custEmailId = "";
			custPhNo = "";
			custName = "";
			shipping_charge = "0";
			shipping_company = "";
			currency = "";
			setPriflag2("");
			if (homeMBs.size() > 0) {
				homeMBs.clear();
				for (int i = 1; i <= 5; i++) {
					SalesOrderFormMB homeMB = new SalesOrderFormMB();
					homeMB.setSerialno("" + i);
					homeMB.setProductName("");
					homeMB.setFlag("1");
					homeMB.setFlag1("1");
					homeMB.setFlag2("none");
					homeMB.setBarflag1("none");
					homeMB.setBarflag("1");
					homeMB.setBarcode1("");
					homeMBs.add(homeMB);
				}

				productName = null;

			} else {
				for (int i = 1; i <= 5; i++) {
					SalesOrderFormMB homeMB = new SalesOrderFormMB();
					homeMB.setSerialno("" + i);
					homeMB.setProductName("");
					homeMB.setFlag("1");
					homeMB.setFlag1("1");
					homeMB.setFlag2("none");
					homeMB.setBarflag1("none");
					homeMB.setBarflag("1");
					homeMB.setBarcode1("");
					homeMBs.add(homeMB);
				}

				productName = null;

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "saledirect1";
	}

	private boolean salesviewFlag;
	private boolean salesorderFlag;
	private boolean salesorderdtFlag;
	private boolean salesviewdtFlag;
	private boolean salesreturnFlag;
	private boolean salespaymentFlag;

	public boolean isSalespaymentFlag() {
		return salespaymentFlag;
	}

	public void setSalespaymentFlag(boolean salespaymentFlag) {
		this.salespaymentFlag = salespaymentFlag;
	}

	public boolean isSalesreturnFlag() {
		return salesreturnFlag;
	}

	public void setSalesreturnFlag(boolean salesreturnFlag) {
		this.salesreturnFlag = salesreturnFlag;
	}

	public String categoryType;

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public boolean isSalesorderdtFlag() {
		return salesorderdtFlag;
	}

	public void setSalesorderdtFlag(boolean salesorderdtFlag) {
		this.salesorderdtFlag = salesorderdtFlag;
	}

	public boolean isSalesviewdtFlag() {
		return salesviewdtFlag;
	}

	public void setSalesviewdtFlag(boolean salesviewdtFlag) {
		this.salesviewdtFlag = salesviewdtFlag;
	}

	public boolean isSalesviewFlag() {
		return salesviewFlag;
	}

	public void setSalesviewFlag(boolean salesviewFlag) {
		this.salesviewFlag = salesviewFlag;
	}

	public boolean isSalesorderFlag() {
		return salesorderFlag;
	}

	public void setSalesorderFlag(boolean salesorderFlag) {
		this.salesorderFlag = salesorderFlag;
	}

	// prema begin 29/04/2016 dialog box creation for sales order
	public void salesorder() {
		logger.info("[salesordr()]-------------------inside salesorder() method()---------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("salesOrderForm5",
				options, null);
		try {
			purchase();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

	}

	// prema end 29/0/2016
	public void salesorderclose() {
		logger.info("[salesorderclose()]-------------------inside salesorderclose() method()---------------");
		RequestContext.getCurrentInstance().closeDialog("salesOrderForm5");
	}

	public void valueChange1(ValueChangeEvent ve) {
		logger.info("[valueChange1()]-------------------inside valueChange1() method()---------------");
		String str = ve.getNewValue().toString();
		setSalesreturnFlag(false);
		purchaseOrder.setSalesIdReference(salesViewMB.purchaseOrder.getSalesIdReference());
		if (str.equalsIgnoreCase("Sales return")) {
			setSalespaymentFlag(false);
			salesReturnFormMB.salesReturnView();
			if(salesReturnFormMB.getNote().equals("Delivered") ){
				setSalesreturnFlag(true);
				RequestContext.getCurrentInstance().execute("PF('salesreturn1').hide();");
			}else if(salesReturnFormMB.getNote().equals("returned")){
				setSalesreturnFlag(false);
				RequestContext.getCurrentInstance().execute("PF('salesreturn1').hide();");
			}
			else{
				setSalesreturnFlag(false);
			}
		} else if (str.equalsIgnoreCase("Sales invoice")) {
			setSalesreturnFlag(false);
			setSalespaymentFlag(false);
			invoiceSalesMB.invoiceSales();
		} else if (str.equalsIgnoreCase("Sales payment")) {
			accountinReMB.accountin();
			if (accountinReMB.resu.size() > 0) {
				setSalesreturnFlag(false);
				setSalespaymentFlag(true);
			} else {
				setSalesreturnFlag(false);
				setSalespaymentFlag(false);
			}
		} else if (str.equalsIgnoreCase("Sales delivery")) {
			setSalesreturnFlag(false);
			setSalespaymentFlag(false);
		}

	}

	public void salesorderview() {
		logger.info("[salesorderview()]-------------------inside salesorderview() method()---------------");
		System.out.println("sales order view method calling");
		try {
			setCategoryType(null);
			setValidate(null);
			Map<String, Object> options = new HashMap<String, Object>();
			options.put("modal", true);
			options.put("draggable", false);
			options.put("resizable", false);
			options.put("contentHeight", 600);
			options.put("contentWidth", 800);
			RequestContext.getCurrentInstance().openDialog("salesView1",
					options, null);
			salesViewMB.salesView2();
			setSalesreturnFlag(false);
			setSalespaymentFlag(false);
			
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}
	public String salesOrderpage() {
		logger.info("[salesOrderpage()]-------------------inside salesOrderpage() method()---------------");
		try {
			setDiscType("");
			setDiscAmnt("");
			validate2 = "";
			validate = "";
			dicount = "";
			productlist = null;
			oderDate = null;
			targetDate = null;
			vendorName = "";
			totalPrice = "";
			totalPrice1 = "";
			flag = "none";
			phonenumber = "";
			crosstotal = "";
			customername = "";
			phoneNumber = "";
			category1 = "";
			custEmailId = "";
			custPhNo = "";
			custName = "";
			shipping_charge = "0";
			shipping_company = "";
			currency="";
			setPriflag2("");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setBatchProductName3(controller.salesOrder3(batchProductName3));
			productlist = new ArrayList<String>();
			resulfinal=new ArrayList<String>();
			for (int i = 0; i < batchProductName3.size(); i++) {
				String s = "" + batchProductName3.get(i);
				productlist.add("" + batchProductName3.get(i));

			}
			Collections.sort(productlist, String.CASE_INSENSITIVE_ORDER);
			//controller.salesCustomer(buyerName, purchaseOrder);
			//setResulfinal(purchaseOrder.getResulfinal1());
			if (homeMBs.size() > 0) {
				homeMBs.clear();
				for (int i = 1; i <= 5; i++) {
					SalesOrderFormMB homeMB = new SalesOrderFormMB();
					homeMB.setSerialno("" + i);
					homeMB.setProductName("");
					homeMB.setFlag("1");
					homeMB.setFlag1("1");
					homeMB.setFlag2("none");
					homeMB.setBarflag1("none");
					homeMB.setBarflag("1");
					homeMB.setBarcode1("");
					homeMBs.add(homeMB);
				}

				productName = null;

			} else {
				for (int i = 1; i <= 5; i++) {
					SalesOrderFormMB homeMB = new SalesOrderFormMB();
					homeMB.setSerialno("" + i);
					homeMB.setProductName("");
					homeMB.setFlag("1");
					homeMB.setFlag1("1");
					homeMB.setFlag2("none");
					homeMB.setBarflag1("none");
					homeMB.setBarflag("1");
					homeMB.setBarcode1("");
					homeMBs.add(homeMB);
				}

				productName = null;

			}

			
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
		}
		return "salesOrderForm5";
	}

	public String submit() {
		logger.info("[submit()]-------------------inside submit() method()---------------");
		try {
			if (categoryType.equalsIgnoreCase("Sales delivery")) {
				salesViewMB.salesdeliveryMB1();
				setSalesreturnFlag(false);
				setSalespaymentFlag(false);
			} else if (categoryType.equalsIgnoreCase("Sales return")) {
				setSalesreturnFlag(true);
				setSalespaymentFlag(false);
				salesReturnFormMB.salesReturnSubmit();
			} else if (categoryType.equalsIgnoreCase("Sales invoice")) {
				setSalesreturnFlag(false);
				setSalespaymentFlag(false);
				invoiceSalesMB.redirect();
			} else if (categoryType.equalsIgnoreCase("Sales payment")) {
				setSalesreturnFlag(false);
				setSalespaymentFlag(true);

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";

	}
	public String customernameclick(){
		logger.info("[customernameclick()]-------------------inside customernameclick() method()---------------");
		try{
			if(category1.equals("") || category1 == null || category1.equals("select")){
				RequestContext.getCurrentInstance().execute("PF('confirm1').show();");
			}else{
				if(resulfinal.size()==0){
					RequestContext.getCurrentInstance().execute("PF('confirm2').show();");
				}else{
					RequestContext.getCurrentInstance().execute("PF('confirm2').hide();");
				}
			}
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
		return "";
	}
	public String productnameclick(){
		logger.info("[productnameclick()]-------------------inside productnameclick() method()---------------");
		try{
			RequestContext.getCurrentInstance().execute("PF('confirm3').hide();");
			if(productlist.size()==0){
				RequestContext.getCurrentInstance().execute("PF('confirm3').show();");
			}
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
		return "";
	}
	public void counSelectValueChange(ValueChangeEvent vc){
		logger.info("[counSelectValueChange()]-------------------inside counSelectValueChange() method()---------------");
		String country = "";
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
		   country = vc.getNewValue().toString();
		   ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		   controller = (DemoController) ctx.getBean("controller");
		   stateList=new ArrayList<String>();
		   stateList=controller.getstatelist(country);
	}catch(Exception e){
		logger.error("Inside Exception", e);
	}
	}
	public void counSelectValueChange1(ValueChangeEvent vc){
		logger.info("[counSelectValueChange1()]-------------------inside counSelectValueChange1() method()---------------");
		String country = "";setCode("");
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			country = vc.getNewValue().toString();
		   ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		   controller = (DemoController) ctx.getBean("controller");
		   stateList1=new ArrayList<String>();
		   stateList1=controller.getstatelist(country);
		   System.out.println("stateist "+stateList1.size());
		   if(country.equalsIgnoreCase("India")) setCode("+91");
		   else if(country.equalsIgnoreCase("Indonesia")) setCode("+62");
		   else if(country.equalsIgnoreCase("Malesia")) setCode("+60");
		   else if(country.equalsIgnoreCase("Singapore")) setCode("+65");
		   else if(country.equalsIgnoreCase("UAE")) setCode("+971");
	}catch(Exception e){
		logger.error("Inside Exception", e);
	}
	}
	public void dummyAction(FileUploadEvent event) throws IOException {
		this.photoUploadFile = event.getFile();
	}
	public void dummyAction1(FileUploadEvent event) throws IOException {
		this.attachmentFile = event.getFile();
	}
	public void dummyAction2(FileUploadEvent event) throws IOException {
		this.nomineePhoto = event.getFile();
	}
	private void copyFile(Date date, String fileName, InputStream inputstream, String n) {
		logger.info("[copyFile()]-------------------inside copyFile() method()---------------");
		try {
			// Create Directory
			File files = new File(Util.getMessage("PhotoPath", "messages")+ ft.format(date));
			if (!files.exists()) {
				files.mkdirs();
			} else {
				logger.debug("Alreday Found");
			}
			
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(files + "/" + fileName + "." + n));
			System.out.println("types11111-----------"+n);
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while ((read = inputstream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			inputstream.close();
			out.flush();
			out.close();
			} catch (IOException e) {
				logger.error("Inside Exception", e);
		}

	}
	private void copyFile1(Date date, String fileName, InputStream inputstream, String n) {
		logger.info("[copyFile1()]-------------------inside copyFile1() method()---------------");
		try {
			// Create Directory
			File files = new File("/home/ec2-user/File_Inacsys/Customer/PDF/"+ ft.format(date));
			if (!files.exists()) {
				files.mkdirs();
			} else {
				logger.debug("Alreday Found");
			}
			
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(files + "/" + fileName + "." + n));
			System.out.println("types-----------"+n);
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while ((read = inputstream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			inputstream.close();
			out.flush();
			out.close();
			
			} catch (IOException e) {
			logger.debug(e.getMessage());
		}
	}
	public void checkBoxValueChange(ValueChangeEvent vc){
		logger.info("[checkBoxValueChange()]-------------------inside checkBoxValueChange() method()---------------");
		try{
			String check=vc.getNewValue().toString();
			if(check.equals("true")){
				setHiddenFlag(true);
				setShowFlag(false);
				setPermanentaddress(shipingaddress);
				setCity(presentCity);
				setCountryID(presentcountryID);
				setPerPostCode(prePostCode);
				setState(presentstate);
			}else{
				setHiddenFlag(false);
				setShowFlag(true);
				setPermanentaddress("");
				setCity("");
				setCountryID("");
				setPerPostCode("");
				setState("");
			}
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
	}
	public void custTitleValueChange(ValueChangeEvent vc){
		logger.info("[custTitleValueChange()]-------------------inside custTitleValueChange() method()---------------");
		String str="";
		try{
			str=vc.getNewValue().toString();
			if(!str.equalsIgnoreCase("")) setDisplayName(str+" "+getCustomername()+" "+getCustomerMiddleName()+" "+getCustomerLastName()+" "+getCustomerSuffix());
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
	}
	public void custFnameValueChange(ValueChangeEvent vc){
		logger.info("[custFnameValueChange()]-------------------inside custFnameValueChange() method()---------------");
		String str="";
		try{
			str=vc.getNewValue().toString();
			if(!str.equalsIgnoreCase(""))setDisplayName(getCustomerTitle()+" "+str+" "+getCustomerMiddleName()+" "+getCustomerLastName()+" "+getCustomerSuffix());
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
	}
	public void custMnameValueChange(ValueChangeEvent vc){
		logger.info("[custMnameValueChange()]-------------------inside custMnameValueChange() method()---------------");
		String str="";
		try{
			str=vc.getNewValue().toString();
			if(!str.equalsIgnoreCase(""))setDisplayName(getCustomerTitle()+" "+getCustomername()+" "+str+" "+getCustomerLastName()+" "+getCustomerSuffix());
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
	}
	public void custLnameValueChange(ValueChangeEvent vc){
		logger.info("[custLnameValueChange()]-------------------inside custLnameValueChange() method()---------------");
		String str="";
		try{
			str=vc.getNewValue().toString();
			if(!str.equalsIgnoreCase(""))setDisplayName(getCustomerTitle()+" "+getCustomername()+" "+getCustomerMiddleName()+" "+str+" "+getCustomerSuffix());
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
	}
	public void custSuffixValueChange(ValueChangeEvent vc){
		logger.info("[custSuffixValueChange()]-------------------inside custSuffixValueChange() method()---------------");
		String str="";
		try{
			str=vc.getNewValue().toString();
			if(!str.equalsIgnoreCase(""))setDisplayName(getCustomerTitle()+" "+getCustomername()+" "+getCustomerMiddleName()+" "+getCustomerLastName()+" "+str);
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
	}
	
	
	/*22-3-17*/
	
	public void venTypes(ValueChangeEvent v){
		logger.info("[venTypes()]-------------------inside venTypes() method()---------------");
		String valueven="";	
		DemoController controller=null;
		try{
			 valueven=v.getNewValue().toString();
			if(!valueven.equalsIgnoreCase("Cash")){
				ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");				
				daylist=controller.getpaytype();
			}
			
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
		
	}
	
public void dialog(){
	logger.info("[dialog()]-------------------inside dialog() method()---------------");
		try{
			setValidate("");
		    setNewcash("");
			RequestContext.getCurrentInstance().execute("PF('confirm1').show();");  
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
	}
	
	public String cusLicence;
	public String cusType;
	public Date cusExdate;
	public String cusCode;
	public boolean venflag=true; 
	public String type;
	public String cash;
	public String newcash;
	public List<String> daylist=null;
	public boolean newFlag=false;
	

	public boolean isNewFlag() {
		return newFlag;
	}

	public void setNewFlag(boolean newFlag) {
		this.newFlag = newFlag;
	}

	public List<String> getDaylist() {
		return daylist;
	}

	public void setDaylist(List<String> daylist) {
		this.daylist = daylist;
	}

	public String getNewcash() {
		return newcash;
	}

	public void setNewcash(String newcash) {
		this.newcash = newcash;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public boolean isVenflag() {
		return venflag;
	}

	public void setVenflag(boolean venflag) {
		this.venflag = venflag;
	}

	public String getCusLicence() {
		return cusLicence;
	}

	public void setCusLicence(String cusLicence) {
		this.cusLicence = cusLicence;
	}

	public String getCusType() {
		return cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	

	public Date getCusExdate() {
		return cusExdate;
	}

	public void setCusExdate(Date cusExdate) {
		this.cusExdate = cusExdate;
	}

	public String getCusCode() {
		return cusCode;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}
	
	
	
	
	public String partnerShipName;
	public String partnerFnamerelation;
	public Date pnDOB;
	public String pnEmailID;
	public String pnPhoneNumber1;
	public String pnPhoneNumber2;
	public String pnAddress;
	public Date dateOfJoin;
	
	
	public Date getDateOfJoin() {
		return dateOfJoin;
	}

	public void setDateOfJoin(Date dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}

	public String getPartnerShipName() {
		return partnerShipName;
	}

	public void setPartnerShipName(String partnerShipName) {
		this.partnerShipName = partnerShipName;
	}

	public String getPartnerFnamerelation() {
		return partnerFnamerelation;
	}

	public void setPartnerFnamerelation(String partnerFnamerelation) {
		this.partnerFnamerelation = partnerFnamerelation;
	}

	public Date getPnDOB() {
		return pnDOB;
	}

	public void setPnDOB(Date pnDOB) {
		this.pnDOB = pnDOB;
	}

	public String getPnEmailID() {
		return pnEmailID;
	}

	public void setPnEmailID(String pnEmailID) {
		this.pnEmailID = pnEmailID;
	}

	public String getPnPhoneNumber1() {
		return pnPhoneNumber1;
	}

	public void setPnPhoneNumber1(String pnPhoneNumber1) {
		this.pnPhoneNumber1 = pnPhoneNumber1;
	}

	public String getPnPhoneNumber2() {
		return pnPhoneNumber2;
	}

	public void setPnPhoneNumber2(String pnPhoneNumber2) {
		this.pnPhoneNumber2 = pnPhoneNumber2;
	}

	public String getPnAddress() {
		return pnAddress;
	}

	public void setPnAddress(String pnAddress) {
		this.pnAddress = pnAddress;
	}

	
	public void memberAssociation() {
		System.out.println("----------inside member association---------------");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		DemoController controller = null;
		ApplicationContext ctx = null;
		Buyer b = null;
		String status ="";
		setBatchProductName("");
		try {

			System.out.println("---------inside buyerRegister SIOCOA-----------");
			if(memberValidation(true)){
				System.out.println("---------inside buyerRegister SIOCOA if condition-----------");
				b = new Buyer();
				b.setAddress(shipingaddress);
				b.setPermanentaddress(permanentaddress);
				b.setCity(city);
				b.setPresentCity(presentCity);
				b.setCountry(countryID);
				b.setPresentcountryID(presentcountryID);
				b.setPerPostCode(perPostCode);
				b.setPrePostCode(prePostCode);
				b.setState(state);
				b.setPresentstate(presentstate);
				b.setClientID(clientID);
				b.setUserID(userID);
				b.setPhoneNumber(custMobile);
				b.setCustomerName(businessname);
				b.setCustomerMiddleName(custName);
				b.setCustomerLastName(customername);
				b.setCustomerTitle(customerLastName);
				b.setDate(salesorderdate);
				b.setDeliveryDate(deliverydate);
				b.setMail(custEmailId);
				b.setOther(barcode1);
				b.setType(type);
				b.setCusCode(cusCode);
				
				b.setPartnerShipName(partnerShipName);
				b.setPartnerFnamerelation(partnerFnamerelation);
				b.setPnDOB(pnDOB);
				b.setPnEmailID(pnEmailID);
				b.setPnPhoneNumber1(pnPhoneNumber1);
				b.setPnPhoneNumber2(pnPhoneNumber2);
				b.setPnAddress(pnAddress);
				b.setDateofjoin(dateOfJoin);
				
				
				
				if(photoUploadFile==null || photoUploadFile.equals(null)){
					b.setPhotouploadfile("");
				}else{
					String s=getPhotoUploadFile().getContentType();
					String type = s.substring(s.lastIndexOf("/") + 1);
					copyFile(new Date(), b.getCustomerName(),getPhotoUploadFile().getInputstream(),type);
					b.setPhotouploadfile(ft.format(new Date()) + "/" +b.getCustomerName()+ "." + type);
				}
				if(attachmentFile==null || attachmentFile.equals(null)){
					b.setAttachFilePath("");
				}else{
					String s=getAttachmentFile().getContentType();
					String type = s.substring(s.lastIndexOf("/") + 1);
					copyFile(new Date(), b.getCustomerMiddleName(),getAttachmentFile().getInputstream(),type);
					b.setAttachFilePath(ft.format(new Date()) + "/" +b.getCustomerMiddleName()+ "." + type);
				}
				if(nomineePhoto==null || nomineePhoto.equals(null)){
					b.setFilePath("");
				}else{
					String s=getNomineePhoto().getContentType();
					String type = s.substring(s.lastIndexOf("/") + 1);
					copyFile(new Date(), b.getCustomerLastName(),getNomineePhoto().getInputstream(),type);
					b.setFilePath(ft.format(new Date()) + "/" +b.getCustomerLastName()+ "." + type);
				}
				ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				System.out.println("------before passing controller--------");
				status = controller.saveBuyer(b);
			}
			RequestContext context = RequestContext.getCurrentInstance();
			if (status.equalsIgnoreCase("success")) {
				setCustomerSuccessFlag(true);
				customerflag1 = false;
				context.execute("PF('confirm').show();");
			} else {
				customerflag1 = true;
				/*context.execute("checkFail1();");*/
				setCustomerSuccessFlag(false);
			}
			RequestContext.getCurrentInstance().update("center_content");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String buyerRegister() throws IOException {
		logger.info("[buyerRegister()]-------------------inside buyerRegister() method()---------------");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		DemoController controller = null;
		ApplicationContext ctx = null;
		Buyer b = null;
		String status ="";
		setBatchProductName("");
		try {
			
			if(presentcountryID.equalsIgnoreCase(null) || presentcountryID.equals("select") || presentcountryID.equalsIgnoreCase("")){
				setValidate1("Please Select Country");
				throw new DemoException("Please Select Country");
			}else{
				setValidate1("");
			}
			if(categoryName.equalsIgnoreCase(null) || categoryName.equals("select") || categoryName.equalsIgnoreCase("")){
				setValidate2("Please Select Category");
				throw new DemoException("Please Select Category");				
			}else{
				setValidate2("");
			}
			if(!presentcountryID.equals("") && !categoryName.equals("")){
				System.out.println("inside if");
				b = new Buyer();
				b.setCustomerTitle(customerTitle);
				b.setCustomerName(customername);
				b.setCustomerMiddleName(customerMiddleName);
				b.setCustomerLastName(customerLastName);
				b.setCustomerSuffix(customerSuffix);
				b.setMail(email);
				b.setCode(code);
				b.setPhoneNumber(phonenumber);
				b.setCustMobile(custMobile);
				b.setCategoryName(categoryName);
				b.setOther(other);
				b.setDate(salesorderdate);
				b.setCompany(company);
				b.setWebsite(website);
				b.setTaxnumber(taxnumber);
				b.setFaxnumber(faxnumber);
				b.setDisplayName(displayName);
				b.setAddress(shipingaddress);
				b.setPermanentaddress(permanentaddress);
				b.setCity(city);
				b.setPresentCity(presentCity);
				b.setCountry(countryID);
				b.setPresentcountryID(presentcountryID);
				b.setPerPostCode(perPostCode);
				b.setPrePostCode(prePostCode);
				b.setState(state);
				b.setPresentstate(presentstate);
				b.setNote(note);
				b.setCusLicence(cusLicence); 
				b.setCusExdate(cusExdate);
				b.setCusCode(cusCode); 
				b.setCusType(cusType); 
				b.setType(type); 
				b.setCash(cash); 
				
				b.setClientID(clientID);
				b.setUserID(userID);
				
				String path ="";
				if(photoUploadFile==null || photoUploadFile.equals("")){
					logger.info("[buyerRegister()]------------------- if condition photo null---------------");
				}else{
					logger.info("[buyerRegister()]------------------- else condition photo---------------");
					String ss=getPhotoUploadFile().getContentType();
					String type = ss.substring(ss.lastIndexOf("/") + 1);
					copyFile(b.getDate(), b.getPhoneNumber(),getPhotoUploadFile().getInputstream(),type);
					path = ft.format(b.getDate()) + "/" +b.getPhoneNumber()+ "." + type;
				}
				b.setFilePath(path);
				String paths ="";
				if(attachmentFile==null || attachmentFile.equals("")){
					logger.info("[buyerRegister()]------------------- if condition attachement null---------------");
					System.out.println("inside null");
				}else{
					logger.info("[buyerRegister()]------------------- else condition attachements---------------");
					String sss=getAttachmentFile().getContentType();
					System.out.println("transFile --- "+sss);
					String types = sss.substring(sss.lastIndexOf("/") + 1);
					System.out.println("transFile---  "+types);
					System.out.println("phone number"+b.getPhoneNumber()+"--"+phonenumber+"date"+b.getDate()+"--"+salesorderdate);
					copyFile1(b.getDate(), b.getPhoneNumber(),getAttachmentFile().getInputstream(),types);
					paths = ft.format(b.getDate()) + "/" +b.getPhoneNumber()+ "." + types;
				}
				
				b.setAttachFilePath(paths);
				ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				status = controller.saveBuyer(b);
			}
			
				RequestContext context = RequestContext.getCurrentInstance();
				if (status.equalsIgnoreCase("success")) {
					setCustomerSuccessFlag(true);
					customerflag1 = false;
					context.execute("PF('confirm').show();");
				} else {
					customerflag1 = true;
					context.execute("checkFail1();");
					setCustomerSuccessFlag(false);
				}
			
			
			return "";
		} catch (DemoException ie) {
			setValidate(ie.getMessage());
			logger.error("inside exception ",ie);
			return "";
		} finally {

		}
	}

	private boolean memberValidation(boolean valid) {
		valid=true;
		String name="";
		FacesContext fc=FacesContext.getCurrentInstance();
		try{
		if(StringUtils.isEmpty(businessname)){
			name=CommonValidate.findComponentInRoot("unitname").getClientId(fc);
			fc.addMessage(name, new FacesMessage(Util.getMessage("Unitname", "messages")));
			valid=false;
		}
		if(StringUtils.isEmpty(custName)){
			name=CommonValidate.findComponentInRoot("ownername").getClientId(fc);
			fc.addMessage(name, new FacesMessage(Util.getMessage("Ownername", "messages")));
			valid=false;
		}
		/*if(StringUtils.isEmpty(customerLastName)){
			name=CommonValidate.findComponentInRoot("nomineename").getClientId(fc);
			fc.addMessage(name, new FacesMessage(Util.getMessage("Nomineename", "messages")));
			valid=false;
		}*/if(!custMobile.equalsIgnoreCase("")){    //stanley changes for ass
		if(!custMobile.matches("^\\d+(\\.\\d+)*$")){  
	    	  name=CommonValidate.findComponentInRoot("phonenumber").getClientId(fc);
	    	  fc.addMessage(name, new FacesMessage(Util.getMessage("Number", "messages")));
		       valid=false;
	      }}
		if(purchaseCheck==false){
			name=CommonValidate.findComponentInRoot("termscondition").getClientId(fc);
			fc.addMessage(name, new FacesMessage(Util.getMessage("Termscondition", "messages")));
			valid=false;
		}
		}catch(Exception e){
		}
		return valid;
	}

	public String reset() {
		logger.info("[reset()]------------------- inside reset method() ---------------");
		customerflag1 = false;
		setCustomername("");
		setSalesorderdate(null);
		setShipingaddress("");
		setState("");
		setCountryID("");
		setCategoryName("");
		setPhonenumber("");
		setEmail("");
		setTaxnumber("");
		setFreelancerName("");
		setNote("");
		setCity("");
		setCustomerTitle("");
		setCustomerMiddleName("");
		setCustomerLastName("");
		setCustomerSuffix("");
		setCode("");
		setCustMobile("");
		setOther("");
		setCompany("");
		setWebsite("");
		setFaxnumber("");
		setDisplayName("");
		setPermanentaddress("");
		setPresentCity("");
		setPresentcountryID("");
		setPerPostCode("");
		setPrePostCode("");
		setPresentstate("");
		setCusLicence("");
		setCusExdate(null); 
		//setCusCode(""); 
		setCusType(""); 
		setType(""); 
		setCash(""); 
		setNewcash("");
		setSameCheckBox(false);
		setHiddenFlag(false);
		setShowFlag(true);
		return "";
	}
	
	public String redirectbuyerRegister() {
		logger.info("[redirectbuyerRegister()]------------------- inside redirectbuyerRegister method() ---------------");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		try {
			customername = null;
			salesorderdate = null;
			shipingaddress = null;
			city = null;
			state = null;
			countryID = null;
			phonenumber = null;
			email = null;
			note = null;
			validate = "";
			taxnumber = null;
			categoryName = "";
			freelancerName = "";
			flagFree = "none";
			flagfree1 = "none";
			setCustomerTitle("");
			setCustomerMiddleName("");
			setCustomerLastName("");
			setCustomerSuffix("");
			setCode("");
			setCustMobile("");
			setOther("");
			setCompany("");
			setWebsite("");
			setFaxnumber("");
			setDisplayName("");
			setPermanentaddress("");
			setPresentCity("");
			setPresentcountryID("");
			setPerPostCode("");
			setPrePostCode("");
			setPresentstate("");
			setCusLicence("");
			setCusExdate(null); 
			setCusCode(""); 
			setCusType("");
			setType(""); 
			setCash("");
			setNewcash("");
			setSameCheckBox(false);
			setHiddenFlag(false);
			setShowFlag(true);
			DemoController controller=null;
			setPurchaseCheck(false);
			//setNewFlag(true);
			setBusinessname("");
			setCustName("");
			setCustEmailId("");
			setBarcode1("");
			setDeliverydate(null);
			setBatchProductName("");
			try{
				ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				cusCode=controller.getcusCode(clientID,userID); 
				daylist=controller.getpaytype();
				if(clientID.equals(Util.getMessage("SIOCOA", "messages"))){
					setNewFlag(true);
				}else{
					setNewFlag(false);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "redirectbuyerRegister";
	}
	
	public String vendorRegistercash(){
		logger.info("[vendorRegistercash()]------------------- inside vendorRegistercash method() ---------------");
		  DemoController controller=null;
		  String status="";
		  setValidate(null);
		  try{
			  Buyer buyer=new Buyer();
		   ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		   controller = (DemoController) ctx.getBean("controller");
		   buyer.setNewcash(newcash+" days"); 
		   status=controller.setbuycash(buyer); 
		    setCash(buyer.getNewcash()); 
		    RequestContext.getCurrentInstance().update("center_content:daypanelid1");
		     if("Success".equalsIgnoreCase(status)){
		     RequestContext.getCurrentInstance().execute("PF('confirm1').hide();");
		     daylist=controller.getpaytype();     
		     RequestContext.getCurrentInstance().update("center_content");
		   }else if("exist".equalsIgnoreCase(status)){
			   setValidate("Already Exist this Day");
		   }
		  }catch(Exception e){
			  logger.error("inside exception ",e);
		  }
		  return "";
		 }
	
/* -------- stanley begin 30/5/2017 new quote and quoteconsole ---------*/
	
	/*New Quote menu click*/
	public String getDataLoad(){
		logger.info("[getDataLoad()]------------------- inside getDataLoad method() ---------------");
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setTest(false); 
			setSalesHidden("sales-quote-register");
			sales.setHidden(salesHidden); 
			quotationcode=controller.getquotationcode();
			System.out.println("--- mb quotation code---->"+quotationcode); 
			setValidate(""); 
			customerlist=controller.getcustomername();
			sales.setCustomername1(""); 
			sales.setBusinessname(""); 
			prodlist=controller.getproductlist(clientID,userID);
			mblist=new ArrayList<SalesOrderFormMB>();
			for(int i=0; i < 5 ; i++){
				SalesOrderFormMB sales=new SalesOrderFormMB();
				sales.setSerialno(String.valueOf(i+1));  
				sales.setProductName(""); 
				sales.setUnit(""); 
				sales.setQuantity(""); 
				sales.setNetAmount(""); 
				mblist.add(sales); 
			}
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "newOuote"; 
	}

	/*Getting customerName from database in newQuote*/
	public void setCustomerDetails(ValueChangeEvent v){
		logger.info("[setCustomerDetails()]------------------- inside setCustomerDetails method() ---------------");
		String customername="";
		setValidate("");
		try{
			customername=v.getNewValue().toString();
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller"); 
			if(customername != "")
			{
				setTest(true); 
				sales.setCustomerName(customername);
				controller.getcustomerDetails(sales);	
			}
			else 
			{
				setTest(false); 
			}
			 
		}catch(Exception e){
			logger.error("inside exception ",e);
			setValidate(e.getMessage());
		}
		
	}
	
	/*Getting product from database in newQuote and quote console table*/
	public void onClickProductChange(ValueChangeEvent v) {
		logger.info("[onClickProductChange()]------------------- inside onClickProductChange method() ---------------");
		String serialno = "";
		String unitprice="";
		setValidate("");
		ApplicationContext ctx = null;
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			serialno = v.getComponent().getAttributes().get("serial").toString();
			String productname=v.getNewValue().toString();
			unitprice=controller.getUnitprice(productname); 
			SalesOrderFormMB sales=new SalesOrderFormMB();//
			sales.setSerialno(serialno); 
			sales.setProductName(productName);
			sales.setUnit(unitprice); 
			if(salesHidden.equalsIgnoreCase("sales-quote-edit")){
				sales.setQuantity(quoteListDetails.get(Integer.parseInt(serialno) - 1).getQuantity());   
				sales.setNetAmount(quoteListDetails.get(Integer.parseInt(serialno)-1).getNetAmount());  
				sales.setQuoteDetailsID(quoteListDetails.get(Integer.parseInt(serialno) -1).getQuoteDetailsID());
				quoteListDetails.set((Integer.parseInt(serialno)-1),sales); 
			}else if(salesHidden.equalsIgnoreCase("sales-quote-register")){
				sales.setQuantity(mblist.get(Integer.parseInt(serialno) - 1).getQuantity());   
				sales.setNetAmount(mblist.get(Integer.parseInt(serialno)-1).getNetAmount());  
				mblist.set((Integer.parseInt(serialno)-1),sales); 
			}				
		} catch (DemoException e) {
			logger.error("inside exception ",e);
			setValidate(e.getMessage());
		}
	}
	
	/*Changing the quantity based on the product choosen in newquote and quote console*/
	public void onClickQuantityChange(ValueChangeEvent v){
		logger.info("[onClickQuantityChange()]------------------- inside onClickQuantityChange method() ---------------");
		String serialno = "";
		String unit="";
		setValidate("");
		ApplicationContext ctx = null;
		BigDecimal netAmount=BigDecimal.valueOf(0);
		try{
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			serialno = v.getComponent().getAttributes().get("serial").toString();
			unit = v.getComponent().getAttributes().get("unit").toString();
			String Quantity=v.getNewValue().toString();
			SalesOrderFormMB sales=new SalesOrderFormMB();
			if(salesHidden.equalsIgnoreCase("sales-quote-register")){
				sales.setSerialno(serialno); 
				sales.setProductName(mblist.get(Integer.parseInt(serialno) - 1).getProductName());
				sales.setUnit(unit);
				sales.setQuantity(Quantity); 
				netAmount=new BigDecimal(unit).multiply(new BigDecimal(Quantity));
				netAmount.toString();
				sales.setNetAmount(String.valueOf(netAmount)); 
				mblist.set((Integer.parseInt(serialno)-1),sales); 
			}else if(salesHidden.equalsIgnoreCase("sales-quote-edit")){
				sales.setSerialno(serialno); 
				sales.setProductName(quoteListDetails.get(Integer.parseInt(serialno) - 1).getProductName());
				sales.setQuoteDetailsID(quoteListDetails.get(Integer.parseInt(serialno) -1).getQuoteDetailsID());
				sales.setUnit(unit);
				sales.setQuantity(Quantity); 
				netAmount=new BigDecimal(unit).multiply(new BigDecimal(Quantity));
				netAmount.toString();
				sales.setNetAmount(String.valueOf(netAmount)); 
				quoteListDetails.set((Integer.parseInt(serialno)-1),sales); 
			}
			
		}catch(Exception e){
			logger.error("inside exception ",e);
			setValidate(e.getMessage());
		}
		
	}

	/*Adding the extra row in table newquote and quote console*/
	public String addNewRow(){		
		logger.info("[addNewRow()]------------------- inside addNewRow method() ---------------");
		try{
			for(int i=0; i < 1 ; i++)
			{
				SalesOrderFormMB sales =new SalesOrderFormMB();
				if(salesHidden.equalsIgnoreCase("sales-quote-register")){
				sales.setSerialno(String.valueOf(mblist.size()+1)); 
				mblist.add(sales); 
				System.out.println("-----inside addrwow register mblist------>"+mblist.size());
				}else if(salesHidden.equalsIgnoreCase("sales-quote-edit")){
				sales.setSerialno(String.valueOf(quoteListDetails.size()+1)); 
				quoteListDetails.add(sales); 
				}
			}
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "";
	}
    
	/*Insertion in newquote in quote console*/ 
	 public String saveNewquote(){
	  logger.info("[saveNewquote()]------------------- inside saveNewquote method() ---------------");
	  DemoController controller=null;
	  String status="Fail";
	  try{
	   if (validation(true)) 
	   {
	    String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
	    String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
	    ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
	    controller = (DemoController) ctx.getBean("controller");
	    status=controller.insertNewouote(clientID,userID,mblist,sales);  
	   }
	    if(status.equalsIgnoreCase("success")){
	     RequestContext.getCurrentInstance().execute("PF('quotconfirm').show();");   
	    }
	  }
	  catch(Exception w){
	    w.printStackTrace();
	  }
	  return "";
	 }

	// Getting all Sales Quotation Values from the database
	public String getSalesQuoteView(){
		logger.info("----- Inside getSalesQuoteView method() -----------");
		DemoController controller=null;	
		String clientID=null;
		String userID=null;
		ApplicationContext ctx =null;
		int j=0;
		try{
			clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			maps = new HashMap<SalesOrderFormMB, ArrayList<SalesOrderFormMB>>();
			quoteTablelist.clear();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setSalesHidden("sales-quote-edit");
			sales.setHidden(salesHidden);
			maps=controller.getQuoteview(maps);
			j=0;
			for (SalesOrderFormMB key : maps.keySet() ) {
				/*if(approvalStatus.equalsIgnoreCase("ApprovalData")){
					if(key.getApprovalStatus().equalsIgnoreCase("PENDING FOR APPROVAL")){
						key.setSerialno(String.valueOf(j+1)); 
						quoteTablelist.add(key);
						j++;
					}
				}else{*/
					key.setSerialno(String.valueOf(j+1)); 
					quoteTablelist.add(key);
					j++;
				/*}*/
			}
			prodlist=controller.getproductlist(clientID,userID);			
		}catch(Exception e){
			logger.error("inside exception "+e.getMessage());
		}
		finally {
			if(controller!=null)
				controller=null;
			if(userID!=null)
				userID=null;
			if(clientID!=null)
				clientID=null;
			}
			if(ctx!=null)
				ctx=null;
		return "quoteConsole";
	}

	// Getting the value from the Map Object In Sales Quotation Edit
	public String getQuoteViewandEdit(){
		logger.info("[getQuoteViewandEdit()]------------------- inside getQuoteViewandEdit method() ---------------");
		int i=0;
		try{	
			quoteListDetails=new ArrayList<SalesOrderFormMB>();
			for(SalesOrderFormMB key : maps.keySet() ) {	
				if(key.getQuotationcode().equalsIgnoreCase(quotationcode)){
					setCustomername(key.getProductName()); 
					setPhoneNumber(key.getPhoneNumber());
					setPermanentaddress(key.getPermanentaddress());	
					setBusinessname(key.getBusinessname()); 
					setMobile(key.getMobile()); 
					setEmail(key.getEmail()); 
					setQuoteID(key.getQuoteID());  
					logger.debug("Quote "+key.getQuoteID());
					quoteListDetails  = maps.get(key);
					i++;
				}
			}
		}catch(Exception e){
			logger.error("inside exception "+e.getMessage());
		}finally{
			if(i!=0)
				i=0;
		}
		return "";
	}
   
	public String updateSalesQuote(){
		logger.info("[updateSalesQuote()]------------------- inside updateSalesQuote method() ---------------");
		String status=null;
		String clientID=null;
		ApplicationContext ctx=null;
		String sStatus="success";
		String fStatus="fail";
		String pReturn="";		
		try{
			logger.debug("Child table Id ==>"+quoteDetailsID);
			clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			status=controller.consoleUpdate(clientID,quoteListDetails,quoteID);
			if(status.equalsIgnoreCase(sStatus)){
				RequestContext.getCurrentInstance().execute("PF('updateSalesquote').show();");
			}
			if(status.equalsIgnoreCase(fStatus)){
				logger.info("Exception occure !!! ");
 			}
		}catch(Exception e){
			logger.error("inside exception "+e.getMessage());
		}finally{
			if(controller!=null)
			controller=null;
			if(ctx!=null)
				ctx=null;
			if(clientID!=null)
				clientID=null;
			if(sStatus!=null)
				sStatus=null;
			if(fStatus!=null)
				fStatus=null;
			if(status!=null)
				status=null;
		}
		return pReturn;
	}
	
	public String removeSalesQuote(){
		logger.info("[removeSalesQuote()]------------------- inside removeSalesQuote method() ---------------");
		DemoController controller=null;
		String status="Fail";
		ApplicationContext ctx =null;
		try{
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			status=controller.quoteTabledelete(quoteID); 
			if(status.equalsIgnoreCase("Success")){
				RequestContext.getCurrentInstance().execute("PF('quoteDelete').show();");
			}
		}catch(Exception e){
			logger.error("inside exception "+e.getMessage());
		}
		finally
		{
			if(ctx!=null)
				 ctx =null;
			if(controller!=null)
				controller =null;
		}
		return "";
	}
	
	/*null validation*/
	  private boolean validation(boolean valid) {
	   String name=null;
	   FacesContext fc=null;//
	   try {	  
	   fc=FacesContext.getCurrentInstance();
	   for (int i = 0; i < mblist.size(); i++) {
	    if (i==0) {
	     if (StringUtils.isEmpty(mblist.get(i).getProductName())) {
	    	 	name=CommonValidate.findComponentInRoot("table").getClientId(fc);
	           fc.addMessage(name, new FacesMessage("Please Select the Product Name"));
	           valid=false;
	     }else{
	      if(StringUtils.isEmpty(mblist.get(i).getQuantity())){
	       name=CommonValidate.findComponentInRoot("table").getClientId(fc);
	       fc.addMessage(name, new FacesMessage("Please Enter the Quantity"));
	       valid=false;
	      }if(!mblist.get(i).getQuantity().matches("^\\d+(\\.\\d+)*$")){
	    	  name=CommonValidate.findComponentInRoot("table").getClientId(fc);
		       fc.addMessage(name, new FacesMessage("Please Enter the Numbers"));
		       valid=false;
	      }
	     }
	    }
	   }
	  } catch (Exception e) {
	  logger.warn("Exception -->"  +e.getMessage());
	  }
	   finally
	   {
		   if(fc!=null)
			   fc =null;
		   if(name!=null)
			   name =null;
		   
		   
	   }
	   return valid;
	  }

	  public String quoteconsoleApproval(){
			logger.info("[quotqtionApproval()--------------- Inside Quotation Approval() method()----------------");
			String status="";
			DemoController controller = null;
			int count=0;
			setValidate("");
			String mailStatus="Fail";
			String clientID=null;
			ApplicationContext ctx=null;
			String userID=null;
			try{
				clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
				userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
				ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				for (int i = 0; i < quoteTablelist.size(); i++) {
					if(quoteTablelist.get(i).purchaseCheck==true){
						count++;
					}
				}
				if(count==0){
					throw new Exception("Please Choose atleast one row for Approval.");
				}else{
					setValidate("");
					SalesOrderFormMB sales=new SalesOrderFormMB();
					status=controller.quoteApproval(clientID,userID,quoteTablelist,sales);
					logger.debug("status"+status); 
					if(status.equalsIgnoreCase("Success")){
						RequestContext.getCurrentInstance().execute("PF('approvalConfirm').show()");
					}
					/*if(status.equalsIgnoreCase("Success")){
						mailList=new ArrayList<String>();
						quotationDetailList=new ArrayList<VendorRegisterFormMB>();
						setMailList(vendor.getMailList());
						HashSet<String> hashList=new HashSet<String>(mailList);
						mailList.clear();
						mailList.addAll(hashList);
						setQuotationDetailList(vendor.getQuotationDetailList());
						mailStatus=MailSendJDBC.sendquotationVendorMail(mailList,quotationDetailList);
						logger.debug("quotation mail status"+mailStatus);
						RequestContext.getCurrentInstance().execute("PF('approvalConfirm').show()");
					}*/
				}
			}catch(Exception e){
				setValidate(e.getMessage());
				logger.warn("Inside Exception" +e.getMessage()); 
			}finally{
				if(clientID!=null)
					clientID=null;
				if(userID!=null)
					userID=null;
				
				if(ctx!=null)
					ctx=null;
			}
			return "";
		}
	  
	//stanley for revenue
	  public String programmeName;
	  public String schedules;
	  public String billedAmount;
	  public String billedAmountPercentage;
	  public int id;
	  
	  
	  public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProgrammeName() {
		return programmeName;
	}

	public void setProgrammeName(String programmeName) {
		this.programmeName = programmeName;
	}

	public String getSchedules() {
		return schedules;
	}

	public void setSchedules(String schedules) {
		this.schedules = schedules;
	}

	public String getBilledAmount() {
		return billedAmount;
	}

	public void setBilledAmount(String billedAmount) {
		this.billedAmount = billedAmount;
	}

	public String getBilledAmountPercentage() {
		return billedAmountPercentage;
	}

	public void setBilledAmountPercentage(String billedAmountPercentage) {
		this.billedAmountPercentage = billedAmountPercentage;
	}
	
	

	public String revenueInsertion(){
		System.out.println("------------inside of revenue mb-----------");
		  DemoController controller = null;ApplicationContext ctx=null;String status="Fail";Sales sales=null;
		  try{
			  ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			  controller = (DemoController) ctx.getBean("controller");sales=new Sales();
			  sales.setSalesorderDate(salesorderdate);sales.setProgrammeName(programmeName); 
			  sales.setSchedules(schedules);sales.setBilledAmount(billedAmount);
			  sales.setBilledAmountPercentage(billedAmountPercentage);sales.setTotalpayable(totalAmount);
			  status=controller.revenueInsertion(sales);
			  if(status.equals("success")){
				  RequestContext.getCurrentInstance().execute("PF('suceess').show()");
				  RequestContext.getCurrentInstance().execute("PF('revenueForm').hide()");
			  }else{
				  System.out.println("------------inside else of revenue mb-----------");
			  }
		  }catch(Exception e){
			  e.printStackTrace();
		  }finally{
			  if(controller != null){
				  controller=null;
			  }if(ctx != null){
				  ctx=null;
			  }
		  }
		  return null;
	  }
	
	public void revenueButtonclick(){
		DemoController controller = null;ApplicationContext ctx=null;String clientID=null;
		try{
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			controller = (DemoController) ctx.getBean("controller");quotationDetailList=new ArrayList<SalesOrderFormMB>();
			sales.setClientID(clientID); 
			quotationDetailList=controller.getValuesRevenue(sales);
			try{
				setSalesorderdate(null);
				setProgrammeName("");
				setSchedules("");
				setBilledAmount("");
				setBilledAmountPercentage(""); 
				setTotalAmount("");
			}catch(Exception p){
				p.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(controller != null){
				controller=null;
			}if(ctx != null){
				ctx=null;
			}if(clientID != null){
				clientID=null;
			}
		}
	}
	
	public String revenueView(){
		DemoController controller = null;ApplicationContext ctx=null;String clientID=null;List<Revenue>list=null;
		try{
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			controller = (DemoController) ctx.getBean("controller");
			sales.setClientID(clientID);
			sales.setId(id); 
			list=controller.getViewRevenue(sales);
			if(list.size() > 0){
				for(Revenue s : list){
					setSalesorderdate(s.getDate());
					setProgrammeName(s.getProgrammeName()); 
					setSchedules(s.getSchedules()); 
					setBilledAmount(s.getBilledAmount()); 
					setBilledAmountPercentage(s.getBilledAmountpercentage());
					setTotalAmount(s.getTotalPayable()); 
				} 
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(controller != null){
				controller=null;
			}if(ctx != null){
				ctx=null;
			}if(clientID != null){
				clientID=null;
			}
		}
		return "";
	}
	
	public String revenueDelete(){
		DemoController controller = null;ApplicationContext ctx=null;String status="Fail";
		try{
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");sales.setId(id);
			status=controller.coformDelete(sales); 
			if(status.equals("success")){
				RequestContext.getCurrentInstance().execute("PF('deltconform').show()");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(controller !=null){
				controller=null;
			}if(ctx != null){
				ctx=null;
			}
		}
		return "";
	}

	public String revenueUpdate(){
		DemoController controller = null;ApplicationContext ctx=null;String status="Fail";Sales sales=null;
		  try{
			  ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			  controller = (DemoController) ctx.getBean("controller");sales=new Sales();
			  sales.setSalesorderDate(salesorderdate);sales.setProgrammeName(programmeName); 
			  sales.setSchedules(schedules);sales.setBilledAmount(billedAmount);sales.setId(id); 
			  sales.setBilledAmountPercentage(billedAmountPercentage);sales.setTotalAmount(totalAmount);
			  status=controller.revenueUpdate(sales);
			  if(status.equals("success")){
				  RequestContext.getCurrentInstance().execute("PF('upform').show()");
			  }else{
				  System.out.println("------------inside else of revenue mb-----------");
			  }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
}
