package com.inacsys.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inacsys.shared.I0015;
import com.inacsys.shared.I0016;
import com.inacsys.shared.I0018;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0021;
import com.inacsys.shared.I0023;
import com.inacsys.shared.I0025;
import com.inacsys.shared.SalesRecord;

public class PurchaseOrder {
	List<Integer> baridList = new ArrayList<Integer>();
	List<Integer> i0018list = new ArrayList<Integer>();

	private String golbalnamesearch;
	private String approveStatus;
	private int pid;
	private boolean purchaseCheck=false;
	private String userID;
	public String accounttype;
	public String accountdescription;
	public String paymentTerms;
	public String destinationCurrency;
	public String baseCurrency;
	public String approval;
	public String currencyAmount;
	
	/*stanley code*/
	public String serialno3;
	private List<PurchaseOrder> resultview = null;
	 

	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public List<PurchaseOrder> getResultview() {
		return resultview;
	}
	
	public void setResultview(List<PurchaseOrder> resultview) {
		this.resultview = resultview;
	}
	
	public String getSerialno3() {
		return serialno3;
	}

	public void setSerialno3(String serialno3) {
		this.serialno3 = serialno3;
	}

	/* Prema Begin*/
	
	public String debitParticular;
	public String creditParticular;
	public String clientID;
	public String transactionType;
	
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getDebitParticular() {
		return debitParticular;
	}

	public void setDebitParticular(String debitParticular) {
		this.debitParticular = debitParticular;
	}

	public String getCreditParticular() {
		return creditParticular;
	}

	public void setCreditParticular(String creditParticular) {
		this.creditParticular = creditParticular;
	}
	
	/* Prema End*/

	ArrayList<PurchaseOrder> filterList;
	
	public ArrayList<PurchaseOrder> getFilterList() {
		return filterList;
	}

	public void setFilterList(ArrayList<PurchaseOrder> filterList) {
		this.filterList = filterList;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getDestinationCurrency() {
		return destinationCurrency;
	}

	public void setDestinationCurrency(String destinationCurrency) {
		this.destinationCurrency = destinationCurrency;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getAccountdescription() {
		return accountdescription;
	}

	public void setAccountdescription(String accountdescription) {
		this.accountdescription = accountdescription;
	}
	
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public boolean isPurchaseCheck() {
		return purchaseCheck;
	}

	public void setPurchaseCheck(boolean purchaseCheck) {
		this.purchaseCheck = purchaseCheck;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getGolbalnamesearch() {
		return golbalnamesearch;
	}

	public void setGolbalnamesearch(String golbalnamesearch) {
		this.golbalnamesearch = golbalnamesearch;
	}

	public List<Integer> getI0018list() {
		return i0018list;
	}

	public void setI0018list(List<Integer> i0018list) {
		this.i0018list = i0018list;
	}

	public List<Integer> getBaridList() {
		return baridList;
	}

	public void setBaridList(List<Integer> baridList) {
		this.baridList = baridList;
	}

	BigDecimal b = BigDecimal.valueOf(0);

	public BigDecimal getB() {
		return b;
	}

	public void setB(BigDecimal b) {
		this.b = b;
	}

	public int sno1;

	public int getSno1() {
		return sno1;
	}

	public void setSno1(int sno1) {
		this.sno1 = sno1;
	}

	ArrayList<I0018> i0018s = new ArrayList<I0018>();

	public ArrayList<I0018> getI0018s() {
		return i0018s;
	}

	public void setI0018s(ArrayList<I0018> i0018s) {
		this.i0018s = i0018s;
	}

	ArrayList<String> order = new ArrayList<String>();
	List<PurchaseOrder> resulfinal = null;
	List<String> resulfinal1 = null;
	public String vendor_phone_number;
	public String orderDatez;

	public String getOrderDatez() {
		return orderDatez;
	}

	public void setOrderDatez(String orderDatez) {
		this.orderDatez = orderDatez;
	}

	public ArrayList<I0025> drop;
	public ArrayList<String> productlist = null;
	public String firmName;
	public String sellingPrice;
	public Date orderDate;
	public Date targentDate;
	public String quantity;
	public int product_ID;
	public String product_name;
	public String totalPrice;
	public String orderNumber;
	public int counter = 0;
	public String remaining;
	public String returnedQ;
	public String paymentStatus;
	public String deliveredStatus;
	public String currency;
	private String paymentMode;

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getDeliveredStatus() {
		return deliveredStatus;
	}

	public void setDeliveredStatus(String deliveredStatus) {
		this.deliveredStatus = deliveredStatus;
	}

	public String getReturnedQ() {
		return returnedQ;
	}

	public void setReturnedQ(String returnedQ) {
		this.returnedQ = returnedQ;
	}

	public String payableAmount;
	private String shipping_company;
	public String salesIdReference;
	public String unit;

	public String getgTotal() {
		return gTotal;
	}

	public void setgTotal(String gTotal) {
		this.gTotal = gTotal;
	}

	public String gTotal;
	private String price;
	Date deliveredDate;
	String delayreason;
	List<I0019> resul;
	ArrayList<String> productQuantity = null;
	public String newCustName;
	public String prodcode;
	private String netReference;

	public String getProdcode() {
		return prodcode;
	}

	public void setProdcode(String prodcode) {
		this.prodcode = prodcode;
	}

	public String getNewCustName() {
		return newCustName;
	}

	public void setNewCustName(String newCustName) {
		this.newCustName = newCustName;
	}

	public ArrayList<String> getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(ArrayList<String> productQuantity) {
		this.productQuantity = productQuantity;
	}

	List<SalesRecord> resul1;

	public List<SalesRecord> getResul1() {
		return resul1;
	}

	public void setResul1(List<SalesRecord> resul1) {
		this.resul1 = resul1;
	}

	String batch;
	public int hid;
	public String marginPrice;
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
	public String batchNumber;
	public String batchProductName;
	public String batchProductName1;
	List<I0018> batchProductName3;
	public String valueChange;
	public String industry;
	public String status;
	public String status2;
	public String status3;
	public String status4;
	public String purchaseQuantity;
	ArrayList<String> firmname = null;
	ArrayList<String> phoneNumber = null;
	List<I0018> resup1 = null;
	public String filenametemp;
	public String filePathfinal;
	public String filepathfinal1;
	public int count = 0;
	public int serialNo;
	public String category;
	ArrayList<I0015> purorderresult = null;
	List<I0015> orderresul = null;
	List<PurchaseOrder> finallist = null;
	List<String> finallist2 = null;
	public String purchaseIdReference;
	public String vendorname;
	List<I0025> venresult = null;
	public String tempValidate;
	public String tempFlag1 = "none";
	public String tempFlag2 = "none";
	public String priroty = "false";

	public String SerialNo;
	public String Nr;
	public String Dr;

	private String rollID;
	private String rollQuantity;
	private String rollBatch;
	private String rollSerialNo;
	private Float rollStockIn;
	private Float rollStockOut;

	private ArrayList homeMbs = new ArrayList();
	private ArrayList roll = new ArrayList();

	public String getNr() {
		return Nr;
	}

	public void setNr(String nr) {
		Nr = nr;
	}

	public String getDr() {
		return Dr;
	}

	public void setDr(String dr) {
		Dr = dr;
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

	public String getPriroty() {
		return priroty;
	}

	public void setPriroty(String priroty) {
		this.priroty = priroty;
	}

	public String getTempValidate() {
		return tempValidate;
	}

	public void setTempValidate(String tempValidate) {
		this.tempValidate = tempValidate;
	}

	public List<I0025> getVenresult() {
		return venresult;
	}

	public void setVenresult(List<I0025> venresult) {
		this.venresult = venresult;
	}

	public List<I0015> getOrderresul() {
		return orderresul;
	}

	public void setOrderresul(List<I0015> orderresul) {
		this.orderresul = orderresul;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public String getPurchaseIdReference() {
		return purchaseIdReference;
	}

	public void setPurchaseIdReference(String purchaseIdReference) {
		this.purchaseIdReference = purchaseIdReference;
	}

	public List<String> getFinallist2() {
		return finallist2;
	}

	public void setFinallist2(List<String> finallist2) {
		this.finallist2 = finallist2;
	}

	public List<PurchaseOrder> getFinallist() {
		return finallist;
	}

	public void setFinallist(List<PurchaseOrder> finallist) {
		this.finallist = finallist;
	}

	public ArrayList<I0015> getPurorderresult() {
		return purorderresult;
	}

	public void setPurorderresult(ArrayList<I0015> purorderresult) {
		this.purorderresult = purorderresult;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getFilepathfinal1() {
		return filepathfinal1;
	}

	public void setFilepathfinal1(String filepathfinal1) {
		this.filepathfinal1 = filepathfinal1;
	}

	public String getFilePathfinal() {
		return filePathfinal;
	}

	public void setFilePathfinal(String filePathfinal) {
		this.filePathfinal = filePathfinal;
	}

	public String getFilenametemp() {
		return filenametemp;
	}

	public void setFilenametemp(String filenametemp) {
		this.filenametemp = filenametemp;
	}

	ArrayList<PurchaseOrder> f = null;

	public ArrayList<PurchaseOrder> getF() {
		return f;
	}

	public void setF(ArrayList<PurchaseOrder> f) {
		this.f = f;
	}

	public ArrayList<String> getFirmname() {
		return firmname;
	}

	public void setFirmname(ArrayList<String> firmname) {
		this.firmname = firmname;
	}

	public ArrayList<String> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(ArrayList<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<I0018> getResup1() {
		return resup1;
	}

	public void setResup1(List<I0018> resup1) {
		this.resup1 = resup1;
	}

	public String getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(String purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public ArrayList<String> getOrder() {
		return order;
	}

	public void setOrder(ArrayList<String> order) {
		this.order = order;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public String getStatus3() {
		return status3;
	}

	public void setStatus3(String status3) {
		this.status3 = status3;
	}

	public String getStatus4() {
		return status4;
	}

	public void setStatus4(String status4) {
		this.status4 = status4;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public ArrayList<I0016> purchaselist1;

	List<String> stockDrop1;
	ArrayList<I0016> stockDrop;
	List<I0021> result;
	ArrayList<I0021> result1 = null;
	ArrayList<I0021> result2 = null;
	List<I0023> resu;
	List<I0016> resulp = null;
	public String customerName;
	public String address;
	public String telephonenumber;
	public String quantity1;
	public String actualPrice;
	List<String> totalPriceList = new ArrayList<String>();

	public List<String> getTotalPriceList() {
		return totalPriceList;
	}

	public void setTotalPriceList(List<String> totalPriceList) {
		this.totalPriceList = totalPriceList;
	}

	public List<I0016> getResulp() {
		return resulp;
	}

	public void setResulp(List<I0016> resulp) {
		this.resulp = resulp;
	}

	ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();

	public String getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(String actualPrice) {
		this.actualPrice = actualPrice;
	}

	public ArrayList<PurchaseOrder> getResult4() {
		return result4;
	}

	public void setResult4(ArrayList<PurchaseOrder> result4) {
		this.result4 = result4;
	}

	public String getQuantity1() {
		return quantity1;
	}

	public void setQuantity1(String quantity1) {
		this.quantity1 = quantity1;
	}

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public List<String> getResulfinal1() {
		return resulfinal1;
	}

	public void setResulfinal1(List<String> resulfinal1) {
		this.resulfinal1 = resulfinal1;
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

	public List<PurchaseOrder> getResulfinal() {
		return resulfinal;
	}

	public void setResulfinal(List<PurchaseOrder> resulfinal) {
		this.resulfinal = resulfinal;
	}

	public ArrayList<I0016> getPurchaselist1() {
		return purchaselist1;
	}

	public void setPurchaselist1(ArrayList<I0016> purchaselist1) {
		this.purchaselist1 = purchaselist1;
	}

	public List<String> getStockDrop1() {
		return stockDrop1;
	}

	public void setStockDrop1(List<String> stockDrop1) {
		this.stockDrop1 = stockDrop1;
	}

	public ArrayList<I0021> getResult2() {
		return result2;
	}

	public void setResult2(ArrayList<I0021> result2) {
		this.result2 = result2;
	}

	public String getValueChange() {
		return valueChange;
	}

	public void setValueChange(String valueChange) {
		this.valueChange = valueChange;
	}

	public ArrayList<I0021> getResult1() {
		return result1;
	}

	public void setResult1(ArrayList<I0021> result1) {
		this.result1 = result1;
	}

	public Date toDate;

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public ArrayList<I0016> getStockDrop() {
		return stockDrop;
	}

	public void setStockDrop(ArrayList<I0016> stockDrop) {
		this.stockDrop = stockDrop;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public List<I0023> getResu() {
		return resu;
	}

	public void setResu(List<I0023> resu) {
		this.resu = resu;
	}

	public List<I0021> getResult() {
		return result;
	}

	public void setResult(List<I0021> result) {
		this.result = result;
	}

	public int batchID;

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

	public int getBatchID() {
		return batchID;
	}

	public void setBatchID(int batchID) {
		this.batchID = batchID;
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

	public List<I0018> getBatchProductName3() {
		return batchProductName3;
	}

	public void setBatchProductName3(List<I0018> batchProductName3) {
		this.batchProductName3 = batchProductName3;
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

	public String getBatch()

	{
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public Date getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public String getDelayreason() {
		return delayreason;
	}

	public void setDelayreason(String delayreason) {
		this.delayreason = delayreason;
	}

	public String getRemaining() {
		return remaining;
	}

	public void setRemaining(String remaining) {
		this.remaining = remaining;
	}

	public ArrayList<I0016> purchaselist;

	public ArrayList<I0016> getPurchaselist() {
		return purchaselist;
	}

	public void setPurchaselist(ArrayList<I0016> purchaselist) {
		this.purchaselist = purchaselist;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getTargentDate() {
		return targentDate;
	}

	public void setTargentDate(Date targentDate) {
		this.targentDate = targentDate;
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

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(String payableAmount) {
		this.payableAmount = payableAmount;
	}

	public String getMarginPrice() {
		return marginPrice;
	}

	public void setMarginPrice(String marginPrice) {
		this.marginPrice = marginPrice;
	}

	public String getTotalnumberofcount1() {
		return totalnumberofcount1;
	}

	public void setTotalnumberofcount1(String totalnumberofcount1) {
		this.totalnumberofcount1 = totalnumberofcount1;
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

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getVendor_phone_number() {
		return vendor_phone_number;
	}

	public void setVendor_phone_number(String vendor_phone_number) {
		this.vendor_phone_number = vendor_phone_number;
	}

	public ArrayList<I0025> getDrop() {
		return drop;
	}

	public void setDrop(ArrayList<I0025> drop) {
		this.drop = drop;
	}

	public ArrayList<String> getProductlist() {
		return productlist;
	}

	public void setProductlist(ArrayList<String> productlist) {
		this.productlist = productlist;
	}

	/* udhaya 2.1.2015 */
	public ArrayList<I0016> datalist = null;

	public ArrayList<I0016> getDatalist() {
		return datalist;
	}

	public void setDatalist(ArrayList<I0016> datalist) {
		this.datalist = datalist;
	}

	/* jeni */
	public List<I0025> vendorInfo;
	public String vendorPhoneNumber;

	public List<PurchaseOrder> result5 = null;

	public List<I0025> getVendorInfo() {
		return vendorInfo;
	}

	public void setVendorInfo(List<I0025> vendorInfo) {
		this.vendorInfo = vendorInfo;
	}

	public String getVendorPhoneNumber() {
		return vendorPhoneNumber;
	}

	public void setVendorPhoneNumber(String vendorPhoneNumber) {
		this.vendorPhoneNumber = vendorPhoneNumber;
	}

	public List<PurchaseOrder> getResult5() {
		return result5;
	}

	public void setResult5(List<PurchaseOrder> result5) {
		this.result5 = result5;
	}

	List<PurchaseOrder> vendorview = new ArrayList<PurchaseOrder>();

	public List<PurchaseOrder> getVendorview() {
		return vendorview;
	}

	public void setVendorview(List<PurchaseOrder> vendorview) {
		this.vendorview = vendorview;
	}

	public String approvalStatus;

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/* kasturi */
	public List<PurchaseOrder> result6 = new ArrayList<PurchaseOrder>();
	public String vendorTelephoneNumber;

	public List<PurchaseOrder> getResult6() {
		return result6;
	}

	public void setResult6(List<PurchaseOrder> result6) {
		this.result6 = result6;
	}

	public String getVendorTelephoneNumber() {
		return vendorTelephoneNumber;
	}

	public void setVendorTelephoneNumber(String vendorTelephoneNumber) {
		this.vendorTelephoneNumber = vendorTelephoneNumber;
	}

	/* udhaya */
	public String marketprice;
	public List<PurchaseOrder> datalist1 = null;

	public List<PurchaseOrder> getDatalist1() {
		return datalist1;
	}

	public void setDatalist1(List<PurchaseOrder> datalist1) {
		this.datalist1 = datalist1;
	}

	public String getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(String marketprice) {
		this.marketprice = marketprice;
	}

	public String grosstotal;

	public String getGrosstotal() {
		return grosstotal;
	}

	public void setGrosstotal(String grosstotal) {
		this.grosstotal = grosstotal;
	}

	public Date fromDate;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public int dummyId;
	public String phNo;

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	public int getDummyId() {
		return dummyId;
	}

	public void setDummyId(int dummyId) {
		this.dummyId = dummyId;
	}

	public List<PurchaseOrder> domain2 = new ArrayList<PurchaseOrder>();
	public Date returnDate;
	public String reason;
	public String returnQuan1;
	public String returnQuan2;
	public String productName;
	public String totalQuan2;
	public String totalQuan1;

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

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public List<PurchaseOrder> getDomain2() {
		return domain2;
	}

	public void setDomain2(List<PurchaseOrder> domain2) {
		this.domain2 = domain2;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	List<PurchaseOrder> domainlist = null;

	public List<PurchaseOrder> getDomainlist() {
		return domainlist;
	}

	public void setDomainlist(List<PurchaseOrder> domainlist) {
		this.domainlist = domainlist;
	}

	private String particulars;
	private String sNo;
	private String debit;
	private String credit;
	private String clientName;

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public String getDebit() {
		return debit;
	}

	public void setDebit(String debit) {
		this.debit = debit;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public ArrayList<PurchaseOrder> cashBookList = new ArrayList<PurchaseOrder>();

	public ArrayList<PurchaseOrder> getCashBookList() {
		return cashBookList;
	}

	public void setCashBookList(ArrayList<PurchaseOrder> cashBookList) {
		this.cashBookList = cashBookList;
	}

	public Date dueDate;

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String totalAmount;

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String netAmount;

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

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

	public String paymentType;
	public String bankname;
	public String cardno;
	public String chequeno;
	public Date chequedate;
	public String accno;

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getChequeno() {
		return chequeno;
	}

	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
	}

	public Date getChequedate() {
		return chequedate;
	}

	public void setChequedate(Date chequedate) {
		this.chequedate = chequedate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String discountzType;
	public String discountz;
	public String discountzAmount;

	public String getDiscountzType() {
		return discountzType;
	}

	public void setDiscountzType(String discountzType) {
		this.discountzType = discountzType;
	}

	public String getDiscountz() {
		return discountz;
	}

	public void setDiscountz(String discountz) {
		this.discountz = discountz;
	}

	public String getDiscountzAmount() {
		return discountzAmount;
	}

	public void setDiscountzAmount(String discountzAmount) {
		this.discountzAmount = discountzAmount;
	}

	public String getRollID() {
		return rollID;
	}

	public void setRollID(String rollID) {
		this.rollID = rollID;
	}

	public String getRollQuantity() {
		return rollQuantity;
	}

	public void setRollQuantity(String rollQuantity) {
		this.rollQuantity = rollQuantity;
	}

	public String getRollSerialNo() {
		return rollSerialNo;
	}

	public void setRollSerialNo(String rollSerialNo) {
		this.rollSerialNo = rollSerialNo;
	}

	public void setSerialNo(String serialNo) {
		SerialNo = serialNo;
	}

	public String getRollBatch() {
		return rollBatch;
	}

	public void setRollBatch(String rollBatch) {
		this.rollBatch = rollBatch;
	}

	public ArrayList getHomeMbs() {
		return homeMbs;
	}

	public void setHomeMbs(ArrayList homeMbs) {
		this.homeMbs = homeMbs;
	}

	public ArrayList getRoll() {
		return roll;
	}

	public void setRoll(ArrayList roll) {
		this.roll = roll;
	}

	public Float getRollStockIn() {
		return rollStockIn;
	}

	public void setRollStockIn(Float rollStockIn) {
		this.rollStockIn = rollStockIn;
	}

	public Float getRollStockOut() {
		return rollStockOut;
	}

	public void setRollStockOut(Float rollStockOut) {
		this.rollStockOut = rollStockOut;
	}

	public String getShipping_company() {
		return shipping_company;
	}

	public void setShipping_company(String shipping_company) {
		this.shipping_company = shipping_company;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	private String barcode1;
	private int purchaseid;
	private String openingStock;

	public String getBarcode1() {
		return barcode1;
	}

	public void setBarcode1(String barcode1) {
		this.barcode1 = barcode1;
	}

	public int getPurchaseid() {
		return purchaseid;
	}

	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}

	public String getOpeningStock() {
		return openingStock;
	}

	public void setOpeningStock(String openingStock) {
		this.openingStock = openingStock;
	}

	public String getNetReference() {
		return netReference;
	}

	public void setNetReference(String netReference) {
		this.netReference = netReference;
	}

	BigDecimal q = BigDecimal.valueOf(0);
	BigDecimal q1 = BigDecimal.valueOf(0);
	BigDecimal q2 = BigDecimal.valueOf(0);

	public BigDecimal getQ2() {
		return q2;
	}

	public void setQ2(BigDecimal q2) {
		this.q2 = q2;
	}

	public BigDecimal getQ1() {
		return q1;
	}

	public void setQ1(BigDecimal q1) {
		this.q1 = q1;
	}

	public BigDecimal getQ() {
		return q;
	}

	public void setQ(BigDecimal q) {
		this.q = q;
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

	BigDecimal num = BigDecimal.valueOf(0);
	BigDecimal tot = BigDecimal.valueOf(0);

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public BigDecimal getTot() {
		return tot;
	}

	public void setTot(BigDecimal tot) {
		this.tot = tot;
	}

	BigDecimal tota = BigDecimal.valueOf(0);

	public BigDecimal getTota() {
		return tota;
	}

	public void setTota(BigDecimal tota) {
		this.tota = tota;
	}

	BigDecimal cross = BigDecimal.valueOf(0);
	public Date salesdateorder;
	public String salesnum;

	public BigDecimal getCross() {
		return cross;
	}

	public void setCross(BigDecimal cross) {
		this.cross = cross;
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

	public String temptotquan;

	public String getTemptotquan() {
		return temptotquan;
	}

	public void setTemptotquan(String temptotquan) {
		this.temptotquan = temptotquan;
	}

	public String discType;
	public String discAmnt;

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

}
