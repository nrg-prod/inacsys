package com.inacsys.dao;

import java.text.ParseException;
import java.util.*;

import com.inacsys.domain.ATransaction;
import com.inacsys.domain.Approval;
import com.inacsys.domain.Buyer;
import com.inacsys.domain.CategoryRegistration;
import com.inacsys.domain.ClientDataBean;
import com.inacsys.domain.Commission;
import com.inacsys.domain.EmployeeDetail;
import com.inacsys.domain.EmployeePayroll;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.ProductRegister;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.domain.Report1;
import com.inacsys.domain.Sales;
import com.inacsys.domain.StockView;
import com.inacsys.domain.UserCreateDataBean;
import com.inacsys.domain.Vendor;
import com.inacsys.domain.VendorDelete;
import com.inacsys.domain.VendorReport;
import com.inacsys.exception.DemoException;
import com.inacsys.managedBean.BuyersViewMB;
import com.inacsys.managedBean.EmployeeDetailsViewFormMB;
import com.inacsys.managedBean.ProductViewMB;
import com.inacsys.managedBean.QuickSaleViewMB;
import com.inacsys.managedBean.SalesOrderFormMB;
import com.inacsys.managedBean.SalesViewMB;
import com.inacsys.managedBean.VendorRegisterFormMB;
import com.inacsys.managedBean.VendorViewFormMB;
import com.inacsys.shared.AccountType;
import com.inacsys.shared.Client;
import com.inacsys.shared.Code;
import com.inacsys.shared.CrmCustomerdetail;
import com.inacsys.shared.Dispatch;
import com.inacsys.shared.Employee;
import com.inacsys.shared.I0001;
import com.inacsys.shared.I0002;
import com.inacsys.shared.I0004;
import com.inacsys.shared.I0005;
import com.inacsys.shared.I0006;
import com.inacsys.shared.I0015;
import com.inacsys.shared.I0016;
import com.inacsys.shared.I0017;
import com.inacsys.shared.I0018;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0021;
import com.inacsys.shared.I0022;
import com.inacsys.shared.I0023;
import com.inacsys.shared.I0025;
import com.inacsys.shared.I0028;
import com.inacsys.shared.I0031;
import com.inacsys.shared.I0032;
import com.inacsys.shared.I0033;
import com.inacsys.shared.Indexes;
import com.inacsys.shared.MemberPayment;
import com.inacsys.shared.PurchaseReturn;
import com.inacsys.shared.Quotation;
import com.inacsys.shared.QuotationDetail;
import com.inacsys.shared.Revenue;
import com.inacsys.shared.SalesQuote;
import com.inacsys.shared.SalesQuoteDetails;
import com.inacsys.shared.SalesRecord;
import com.inacsys.shared.SalesReturn;
import com.inacsys.shared.SalesTransaction;
import com.inacsys.shared.SubProduct;
import com.inacsys.shared.Transaction;

/**
 * This Java Class will communicate with Database
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */
public interface DemoDao {
	public List<String> getnames(List<String> names) throws DemoException;

	public String loginDao(LoginAccess loginaccess) throws DemoException;

	public String vendorDao(Vendor vendor) throws DemoException;

	public String countryDrop(Vendor vendor) throws DemoException;

	public List<I0025> vendorUpdateDao(Vendor vendor, List<I0025> I0025)
			throws DemoException;

	public String vendorDeleteDao(VendorDelete vendorDelete)
			throws DemoException;

	public String vendorModify(Vendor vendor, List<I0025> xx)
			throws DemoException;

	public String dropDown(ProductRegister productRegister,
			List<I0004> typeparent, List<I0002> productgroup,
			List<I0006> industryList) throws DemoException;

	public List<I0004> dropDown(List<I0004> typeparent) throws DemoException;

	public List<I0002> dropDow(List<I0002> productgroup) throws DemoException;

	public List<I0006> dropDo(List<I0006> industryList) throws DemoException;

	public List<String> dropD(List<String> ven) throws DemoException;

	public String saveProductRegister(ProductRegister productRegister)
			throws DemoException, ParseException;

	public String saveProductEdit(List<I0001> i0001s,
			ProductRegister productRegister) throws Exception;

	public List<I0001> productView(List<I0001> i0001s,
			ProductRegister productRegister) throws DemoException;

	public List<I0001> productView1(List<I0001> i0001s,
			ProductRegister productRegister) throws DemoException;

	public String productReject(ProductRegister productRegister)
			throws DemoException;

	public String productRemove(ProductRegister productRegister)
			throws DemoException;

	public List<I0001> autoComplete(List<I0001> auto,
			ProductRegister productRegister) throws DemoException;

	public List<I0025> purchaseDrop(List<I0025> drop,
			PurchaseOrder purchaseOrder) throws DemoException;

	public ArrayList<String> changeDrop(String s, ArrayList<String> productlist)
			throws DemoException;

	public String changeFirmName(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String changeDrop1(String s) throws DemoException;

	public String puruchaseOrder(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String savePuruchaseOrder(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String savePuruchaseOrder1(PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<I0016> purchaseOrdercancel(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<I0016> purchaseOrdercancel1(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<I0015> invoicePurachaseDrop(ArrayList<I0015> purchaselist)
			throws DemoException;

	public ArrayList<I0015> invoicePurachaseDrop1(ArrayList<I0015> purchaselist)
			throws DemoException;

	public ArrayList<I0015> dateSearchInvoice(Date fromDate, Date todate,
			ArrayList<I0015> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException;

	public String purchaseClose(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String cancelConform1(PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<I0016> purchaseView(PurchaseOrder purchaseOrder,
			ArrayList<I0016> purchaselist) throws DemoException;

	public ArrayList<I0016> invoicePurhcase1(PurchaseOrder purchaseOrder,
			ArrayList<I0016> purchaselist) throws DemoException;

	public ArrayList<String> dropAccount(String s, ArrayList<String> ordernumber)
			throws DemoException;

	public ArrayList<I0023> AccountOut(PurchaseOrder purchaseOrder,
			ArrayList<I0023> purchaselist) throws DemoException;

	public String payNow1(PurchaseOrder purchaseOrder) throws DemoException;

	public ArrayList<I0016> purchaseOrderPayment(Date s, Date s1,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<I0016> purchaseDeliveryStatus(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<I0016> purchaseDeliveryStatus2(
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException;

	public String deliveryStatus(PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<I0015> purchaseOrderClose(ArrayList<I0015> purchaselist,
			PurchaseOrder purchaseOrder) throws DemoException;

	public String stockInForm(PurchaseOrder purchaseOrder) throws DemoException;

	public String stockInForm1(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String addStock(PurchaseOrder purchaseOrder) throws DemoException;

	public String addStock3(PurchaseOrder purchaseOrder) throws DemoException;

	public List<I0016> addStock1(PurchaseOrder purchaseOrder)
			throws DemoException;

	public List<I0016> valueChange(List<I0016> resul,
			PurchaseOrder purchaseOrder, int j, I0018 batch)
			throws DemoException;

	public String addStock2(PurchaseOrder purchaseOrder, List<I0016> resul,
			int count) throws DemoException;

	public String addedStock(PurchaseOrder purchaseOrder) throws DemoException;

	public String addStock4(PurchaseOrder purchaseOrder, int batchid,
			float quantity) throws DemoException;

	public String addStock4Normal(int batchid, Date due) throws DemoException;

	public List<I0018> salesDrop(List<I0018> batch) throws DemoException;

	public List<I0019> stockView(List<I0019> batch2, String s,
			StockView stockView) throws DemoException;

	public List<I0019> stockView2(List<I0019> batch2, StockView stockView)
			throws DemoException;

	public List<I0018> stockView1(List<I0018> batch2) throws DemoException;

	public String addDamage(PurchaseOrder purchaseOrder) throws DemoException;

	public String addDamage1(PurchaseOrder purchaseOrder) throws DemoException;

	public List<I0019> stockdamageView(List<I0019> batch2, String s,
			StockView stockView) throws DemoException;

	public String salesOrder1(PurchaseOrder purchaseOrder) throws DemoException;

	public String salesOrder2(PurchaseOrder purchaseOrder) throws DemoException;

	public String salesOrder3(PurchaseOrder purchaseOrder) throws DemoException;

	public List<I0018> salesOrder3(List<I0018> batchProductName3)
			throws DemoException;

	public List<String> productVendor(List<String> batchProductName3)
			throws DemoException;

	public List<String> productVendor1(List<String> batchProductName3, String s)
			throws DemoException;

	public String salesOrder4(PurchaseOrder purchaseOrder) throws DemoException;

	public String salesOrder5(PurchaseOrder purchaseOrder) throws DemoException;

	public List<I0019> stockoutForm(List<I0019> batch2, String s,
			StockView stockView) throws DemoException;

	public List<I0019> stockInForm(List<I0019> batch2, String s)
			throws DemoException;

	public String salesOrdercancelForm(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesOrderViewproduct(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesOrdercancelForm4(PurchaseOrder purchaseOrder)
			throws DemoException;


	public String salesOrdercancel(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesOrdercancelFormsub1(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesOrdercancelFormsub(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesDrop(PurchaseOrder purchaseOrder) throws DemoException;

	public String salesDrop1(PurchaseOrder purchaseOrder) throws DemoException;

	public String changeDrop(PurchaseOrder purchaseOrder) throws DemoException;

	public String changeDrop1(PurchaseOrder purchaseOrder) throws DemoException;

	public String salesOrdercancelForm1(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesOrdercancelForm3(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesOrdercancelForm2(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesOrdercancelForm5(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesReturnForm2(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesReturnForm3(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesReturnFor(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesReturnForm4(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesReturnForm5(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesdelivery(PurchaseOrder purchaseOrder)
			throws DemoException;

	public List<I0021> salesPayment1(List<I0021> salesreferenumber)
			throws DemoException;

	public String salesPayment2(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesView(PurchaseOrder purchaseOrder) throws DemoException;

	public String invoiceSales(PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<I0021> invoiceSales(PurchaseOrder purchaseOrder,
			ArrayList<I0021> sales) throws DemoException;

	public String accountin(PurchaseOrder purchaseOrder) throws DemoException;

	public ArrayList<I0021> accountin(ArrayList<I0021> drop)
			throws DemoException;

	public String accountin1(PurchaseOrder purchaseOrder) throws DemoException;

	public String payNowAccount(PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<I0001> dropdownproduct(String productname,
			ArrayList<I0001> productDrop) throws DemoException;

	public ArrayList<I0022> reportInvoice(ArrayList<I0022> invoiceList,
			Report1 report1) throws DemoException;

	public ArrayList<I0015> reportPurchase(ArrayList<I0015> purchaseList,
			Report1 report1) throws DemoException;

	public ArrayList<I0021> reportSales(ArrayList<I0021> salesList1,
			Report1 report1) throws DemoException;

	public ArrayList<I0025> vendorView(Vendor vendor) throws DemoException;

	public String salesDelete1(int id) throws DemoException;

	public ArrayList<I0021> salesDelete() throws DemoException;

	public List<I0018> dropDownbatch(List<I0018> batch) throws DemoException;

	public String buyerDelete(Buyer b) throws DemoException;

	public ArrayList<I0032> customerNameChange(String s) throws DemoException;

	public ArrayList<I0032> salesCustomer(ArrayList<I0032> buyername,
			PurchaseOrder purchaseOrder) throws DemoException;

	public ArrayList<I0032> salesOrder(ArrayList<I0032> buyername, Buyer b)
			throws DemoException;

	public List<I0032> getPhone() throws DemoException;

	public List<I0032> getBuyerUpdate(Buyer b) throws DemoException;

	public List<I0032> getBuyerInfo(String phoneNumber) throws DemoException;

	public List<I0032> getBuyerInfo1(String phoneNumber,String clientID,String userID) throws DemoException;

	public List<I0028> getCountry() throws DemoException;

	public List<StockView> getStockInfo() throws DemoException;

	public String saveBuyer(Buyer b) throws DemoException;

	public int getSalesOrderStatus() throws DemoException;

	public int getPurchaseorderStatus() throws DemoException;

	public int getsalesstatus() throws DemoException;

	public int getPurchasestatus() throws DemoException;

	public String filePath(PurchaseOrder purchaseOrder) throws DemoException;

	public String fileSave(PurchaseOrder purchaseOrder) throws DemoException;

	public String approvalView(Approval approval) throws DemoException;

	public String approvalView1(Approval approval) throws DemoException;

	public String approvalView2(Approval approval) throws DemoException;

	public String approvalView3(Approval approval) throws DemoException;

	public ArrayList<I0033> approvalView4(Approval approval)
			throws DemoException;

	public ArrayList<I0033> approvalView5(ArrayList<I0033> approval, int i,
			int j) throws DemoException;

	public void approvalView6(int i) throws DemoException;

	/* udhaya 30.12.2014 */
	public String categoryType(CategoryRegistration categoryreg)
			throws DemoException;

	/* udhaya 31.12.2014 */
	public List<String> categorylist(List<String> categorytype)
			throws DemoException;

	public String category(ProductRegister productRegister)
			throws DemoException;

	/* udhaya 2.1.2015 */
	public int getPurchaseapprovalstatus() throws DemoException;

	public String approvalstatus(PurchaseOrder purchaseOrder)
			throws DemoException;

	/* udhaya 5.1.2015 */
	public ArrayList<I0016> approvalStage(String orderNumber,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException;

	public String approved(PurchaseOrder purchaseOrder) throws DemoException;

	public String quickSaleView(PurchaseOrder purchaseOrder)
			throws DemoException;

	/* udhaya 7.1.2015 */
	public List<I0021> view(List<I0021> saleslist, PurchaseOrder purchaseOrder)
			throws DemoException;

	public List<PurchaseOrder> getQuicksaleEdit(PurchaseOrder purchaseOrder)
			throws DemoException;

	/* udhaya 8.1.2015 */
	public List<I0021> customerNameChange(List<I0021> cusname)
			throws DemoException;

	/* jeni */
	public List<I0032> getBuyercustInfo(Buyer b) throws DemoException;

	public String getPurchaseVendorView(PurchaseOrder purchaseVendor)
			throws DemoException;

	public String salesPaypend(PurchaseOrder purchaseOrder)
			throws DemoException;

	public int getSalesPayStatus() throws DemoException;

	// public String changeSalesPay(PurchaseOrder purchaseOrder)throws
	// InventoryException;

	/* kasturi */
	public ArrayList<I0016> purchaseOrderPayment1(
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException;

	public String remSalesDeliver(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String remSalesDelivery(PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<I0016> remPurchaseDeliver(ArrayList<I0016> purchaselist,
			PurchaseOrder purchaseOrder) throws DemoException;

	public String remPurchasePaymentStatus(PurchaseOrder purchaseOrder)
			throws DemoException;

	/* siva20_2_15 */
	public List<I0001> getProductInfo() throws DemoException;

	public List<I0018> getBatchInfo(String pName) throws DemoException;

	public List<I0019> getBarcodeInfo(int batchId) throws DemoException;

	public StockView getStockData(int batchID) throws DemoException;

	public ProductRegister getProductData(int batchID) throws DemoException;

	public List<I0017> getStock(int id, Date startDate, Date endDate)
			throws DemoException;

	public List<I0017> getStock1(int id) throws DemoException;

	public List<I0019> getBarcodeInfo1(int batchId) throws DemoException;

	public List<I0019> getBarcodeInfo2(int batchId, Date d)
			throws DemoException;

	public List<I0001> dropdownproduct1() throws DemoException;

	public List<String> categorylist1(List<String> categorytype)
			throws DemoException;

	public String purchaseReturnValuechangeDrop1(String s,
			PurchaseOrder purchaseOrder) throws DemoException;

	public ArrayList<I0032> customerNameChange1(String s) throws DemoException;

	public String viewPurchaseReturnDetail(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String viewPurchaseReturn(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String purchaseReturnInsert(PurchaseOrder purchaseOrder)
			throws DemoException;

	public List<I0032> getCategoryData(String category) throws DemoException;

	public List<I0021> getSalesData1(Date fDate, Date tDate)
			throws DemoException;

	public List<I0021> getSalesData(Date fDate, Date tDate, int bId)
			throws DemoException;

	public List<SalesRecord> getSalesQuantityData(int sId) throws DemoException;

	public List<I0021> getSalesDatafromNumber(String saleNo)
			throws DemoException;

	public String commisionUpdate(int sid, Commission c) throws DemoException;

	public String findCashBook(PurchaseOrder purchaseOrder)
			throws DemoException;

	public List<I0032> getFreeLancerInfo(String freeLancerName)
			throws DemoException;

	/* siva 10-4-15 */
	public List<String> getSONfordispatch() throws DemoException;

	public List<Dispatch> getDispathchfromSales(String soNumber)
			throws DemoException;

	public List<I0019> getDeliveredStock(int sid) throws DemoException;

	public List<I0021> getSalesDataForCommision(int bID) throws DemoException;

	public List<Date> getBarcodeInfo11(int batchId) throws DemoException;

	public ProductRegister getProductData1(int batchID, Date d)
			throws DemoException;

	public List<I0018> getBatchfromStock() throws DemoException;

	public String outofStockz(int batchId) throws DemoException;

	public List<I0019> outofStock(int batchId) throws DemoException;

	public ProductRegister getProductData2(int batchID) throws DemoException;

	public List<I0018> getStockDataforbatch(int sid) throws DemoException;

	public List<I0019> stocks(int sid, int bid) throws DemoException;

	public String stockupdate(int bid) throws DemoException;

	public String stockupdate1(int bid) throws DemoException;

	public String batchupdated(int batId) throws DemoException;

	public String salesreturnSave(List<I0019> i19, Date d, String s)
			throws DemoException;

	public String salesupdateforreturn(int sid, PurchaseOrder purchaseOrder)
			throws DemoException;

	public String partialnormalreturn(PurchaseOrder p, int qty, int sid)
			throws DemoException;

	public String salesupdateforreturn1(int sid, PurchaseOrder purchaseOrder)
			throws DemoException;

	public List<String> getSalesreturnSalesorder(Date d) throws DemoException;

	public String viewAccountReceivable(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String viewAccountPayable(PurchaseOrder purchaseOrder)
			throws DemoException;

	public List<SalesReturn> getSaleReturn(Date d) throws DemoException;

	public List<SalesReturn> getSaleReturn1(Date d, String pNmae, String oNumber)
			throws DemoException;

	public String designation(EmployeeDetail emp) throws DemoException;

	public List<Dispatch> getDispathchfromrefference(String soNumber)
			throws DemoException;

	public String updateDispatch(int id, String v1, String v2)
			throws DemoException;

	public String salesSave(PurchaseOrder purchaseOrder) throws DemoException;

	public String salesSave1(PurchaseOrder purchaseOrder) throws DemoException;

	public String purchasePrice(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String purchase(PurchaseOrder purchaseOrder) throws DemoException;

	public String purchase1(PurchaseOrder purchaseOrder) throws DemoException;

	public String purchaseorderClose(PurchaseOrder purchaseOrder)
			throws DemoException;

	public List<I0023> paymentamount(PurchaseOrder purchaseOrder)
			throws DemoException;

	public List<I0023> paymentamountsale(PurchaseOrder purchaseOrder)
			throws DemoException;

	public List<SalesRecord> getDeliveredStockz(int sales_ID, PurchaseOrder dom)
			throws DemoException;

	public String stockQuantity(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String returnQuantity(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesReturnSubmit(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String viewSalesReturnDetail(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String viewSalesReturn(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String searchCity(Vendor vendor) throws DemoException;

	public ArrayList<String> changeList(String changeEvent,
			ArrayList<String> productList, PurchaseOrder purchaseOrder)
			throws DemoException;

	public String addStockIn(PurchaseOrder purchaseOrder) throws DemoException;

	public String getRollQuantity(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String getProductQuntity(PurchaseOrder purchaseOrder)
			throws DemoException;

	public List<I0032> customerNameChange11(String s1) throws DemoException;

	public List<Employee> freenamelistser(List<Employee> name)
			throws DemoException;

	public ArrayList<String> getAddRollList(String productName,
			ArrayList<String> rollList) throws DemoException;

	public String rollDamage(PurchaseOrder purchaseOrder) throws DemoException;

	public String changeDrop1(String s, PurchaseOrder dom) throws DemoException;

	public List<I0019> stockView3(List<I0019> batch2, String s,
			StockView stockView) throws DemoException;

	public List<String> productVendor1(List<String> batchProductName4)
			throws DemoException;

	public String changeUserName(String newuser, String invusername)
			throws DemoException;

	public String changeUserPassword(String newpasswrd, String invpassword)
			throws DemoException;

	public String i0015Insert(PurchaseOrder opngstock) throws DemoException;

	public String i0016Insert(PurchaseOrder opngstock) throws DemoException;

	public String i0018Insert(PurchaseOrder opngstock) throws DemoException;

	public String openingStockInsert(int batchid, PurchaseOrder opngstock)
			throws DemoException;

	public String rollInsertI0019(PurchaseOrder opngstock) throws DemoException;

	public String getpurchaseView(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String getunitprice(String productName, String unitprice)
			throws DemoException;

	public String salesRecordView(PurchaseOrder purchaseOrder)
			throws DemoException;

	public int productInfo2() throws DemoException;

	public List<I0021> getProductInf(Date date, Date date2)
			throws DemoException;

	public List<SalesRecord> getProductInfo2(int salesidd) throws DemoException;

	public List<I0019> getbatchdemo(Integer j) throws DemoException;

	// public List<I0018> getproductsname(HashSet<Integer> hasbat)throws
	// InventoryException;
	public List<Integer> getproductsname() throws DemoException;

	public ArrayList<String> productnonsales(PurchaseOrder po, Integer g)
			throws DemoException;

	public List<I0032> getcitiesinfo(String city) throws DemoException;

	public List<String> getnames1(List<String> names1) throws DemoException;

	public String designRegSubmit(ProductRegister productRegister)
			throws DemoException, ParseException;

	public String designView(ProductRegister productRegister)
			throws DemoException;

	public String designViews(ProductRegister productRegister, int i)
			throws DemoException;

	public String designViewDelete(ProductRegister productRegister)
			throws DemoException;

	public String designValidate(ProductRegister productRegister)
			throws DemoException;

	public String saveImages(ProductRegister productRegister, int i)
			throws DemoException;

	public String logoutDao(LoginAccess loginaccess) throws DemoException;

	public String purchaseReturnInsert1(PurchaseOrder purchaseOrder);

	public String salesReturnSubmit1(PurchaseOrder purchaseOrder);

	public String databaseValidate(LoginAccess loginaccess);

	public String checkerrorcode(LoginAccess loginaccess);

	public String insertCreateUser(UserCreateDataBean userCreateDataBean);

	public List<UserCreateDataBean> insideUserEdit(
			UserCreateDataBean userCreateDataBean);

	public String insideUpdate(UserCreateDataBean userCreateDataBean);

	public String userInsert(UserCreateDataBean userCreateDataBean)
			throws DemoException;

	public String getVendorVerification(String name);

	public String userCheck(UserCreateDataBean userCreateDataBean)
			throws DemoException;

	public String getCustomerVerification(String name);

	public String retirveUser(UserCreateDataBean userCreateDataBean);

	public List<String> getCustomerInfo(Buyer b);

	public List<String> getproductListInfo(ProductRegister productRegister);

	public String getProductVerification(String name);

	public ArrayList<Report1> getVendorNameList();

	public ArrayList<VendorReport> insidevendornamesearch(
			VendorReport vendorReport);

	public ArrayList<VendorReport> insideallvendornamesearch(
			VendorReport vendorReport);

	public List<I0001> productView2(List<I0001> i0001s,
			ProductRegister productRegister, String golbalnamesearch)
			throws DemoException;

	public ArrayList<I0025> vendorView1(Vendor vendor, String golbalnamesearch)
			throws DemoException;

	public List<I0032> getBuyercustInfo(Buyer b, String golbalnamesearch)
			throws DemoException;

	public List<I0025> getphonenumberglobalsearch(String golbalnamesearch)
			throws DemoException;

	public List<I0032> getcustphnosearch(String golbalnamesearch)
			throws DemoException;

	public List<Employee> getempphnosearch(String golbalnamesearch)
			throws DemoException;

	public ArrayList<I0025> getgmailsearch(String golbalnamesearch)
			throws DemoException;

	public List<I0032> getcustemailsearch(String golbalnamesearch)
			throws DemoException;

	public List<Employee> getempemailsearch(String golbalnamesearch)
			throws DemoException;

	public List<SubProduct> submenus(int product_ID, String productCode);
	
	public String saveClient(ClientDataBean clientDataBean) throws DemoException;

	public String clientNoCheck(String phno)throws DemoException;

	public String getclientDetails(ClientDataBean clientDataBean);

	public String getclientDetailsView(ClientDataBean clientDataBean);

	public String clientUpdate(ClientDataBean clientDataBean);

	public String clientDelete(ClientDataBean clientDataBean);

	public String userView(UserCreateDataBean userCreateDataBean);

	public String getUserdetails(UserCreateDataBean userCreateDataBean);

	public String userUpdate(UserCreateDataBean userCreateDataBean);

	public String userDelete(UserCreateDataBean userCreateDataBean);

	public String getdashboardCount(LoginAccess loginaccess);

	public List<String> getAccountType(String clientID);

	public String addAccount(ATransaction payment);

	public List<String> getstatelist(String country);

	public String getempID();
	
	public String getdocUpload(EmployeeDetail employeeDetail);

	public List<String> getdepartmentname();

	public String getProductcodeVerification(String name);

	public List<I0015> getI0015daily(Report1 report1, String clientID);

	public List<I0015> getI0015weekly(Report1 report1, String clientID);

	public List<I0015> getI0015monthly(Report1 report1, String clientID);

	public List<I0015> getI0015quarterly(Report1 report1, String clientID);

	public List<I0015> getI0015halfyearly(Report1 report1, String clientID);

	public List<I0015> getI0015annual(Report1 report1, String clientID);

	public List<I0016> getvendorname(String orderNumber);

	public List<I0016> geti0016paid(String orderNumber);

	public List<PurchaseReturn> getpreturndaily(Report1 report1, String clientID);

	public List<PurchaseReturn> getpreturnmonthly(Report1 report1,
			String clientID);

	public List<PurchaseReturn> getpreturquarterly(Report1 report1,
			String clientID);

	public List<PurchaseReturn> getpreturnhalfyearly(Report1 report1,
			String clientID);

	public List<PurchaseReturn> getpreturnannual(Report1 report1,
			String clientID);

	public List<I0021> getdailysalesdelivery(String clientID, Report1 report1);

	public List<I0021> getmonthlysalesdelivery(String clientID, Report1 report1);

	public List<I0021> getquarterlysalesdelivery(String clientID,
			Report1 report1);

	public List<I0021> gethalfyearlysalesdelivery(String clientID,
			Report1 report1);

	public List<I0021> getannualsalesdelivery(String clientID, Report1 report1);

	public List<I0021> getdailysalespayment(String clientID, Report1 report1);

	public List<I0021> getmonthlysalespayment(String clientID, Report1 report1);

	public List<I0021> getquaterlysalespayment(String clientID, Report1 report1);

	public List<I0021> gethalfyearlysalespayment(String clientID,
			Report1 report1);

	public List<I0021> getannualsalespayment(String clientID, Report1 report1);

	public List<SalesReturn> getdailySalesreturn(String clientID,
			Report1 report1);

	public List<SalesReturn> getmonthlySalesreturn(String clientID,
			Report1 report1);

	public List<SalesReturn> getquarterlySalesreturn(String clientID,
			Report1 report1);

	public List<SalesReturn> gethalfyearlySalesreturn(String clientID,
			Report1 report1);

	public List<SalesReturn> getannualSalesreturn(String clientID,
			Report1 report1);

	public List<I0021> getdailyQsalesdelivery(String clientID,
			Report1 report1);

	public List<I0021> getmonthlyQsalesdelivery(String clientID, Report1 report1);

	public List<I0021> getquarterlyQsalesdelivery(String clientID,
			Report1 report1);

	public List<I0021> gethalfyearlyQsalesdelivery(String clientID,
			Report1 report1);

	public List<I0021> getannualQsalesdelivery(String clientID, Report1 report1);

	public List<I0021> getdailyQsalespaid(String clientID, Report1 report1);

	public List<I0021> getmonthlyQsalespaid(String clientID, Report1 report1);

	public List<I0021> getquarterlyQsalespaid(String clientID, Report1 report1);

	public List<I0021> gethalfyearlyQsalespaid(String clientID, Report1 report1);

	public List<I0021> getannualQsalespaid(String clientID, Report1 report1);

	public List<SalesReturn> getdailyQsalesreturn(String clientID,
			Report1 report1);

	public List<SalesReturn> getmonthlyQsalesreturn(String clientID,
			Report1 report1);

	public List<SalesReturn> getquarterlyQsalesreturn(String clientID,
			Report1 report1);

	public List<SalesReturn> gethalfyearlyQsalesreturn(String clientID,
			Report1 report1);

	public List<SalesReturn> getannualQsalesreturn(String clientID,
			Report1 report1);

	public List<Transaction> getdailyprofit(String clientID, Report1 report1);

	public List<Transaction> getmonthlyprofit(String clientID, Report1 report1);

	public List<Transaction> getquaterlyprofit(String clientID, Report1 report1);

	public List<Transaction> gethalfyearlyprofit(String clientID,
			Report1 report1);

	public List<Transaction> getannualprofit(String clientID, Report1 report1);

	public List<Transaction> getdailyloss(String clientID, Report1 report1);

	public List<Transaction> getmonthlyloss(String clientID, Report1 report1);

	public List<Transaction> getquarterlyloss(String clientID, Report1 report1);

	public List<Transaction> gethalfyearlyloss(String clientID, Report1 report1);

	public List<Transaction> getannualloss(String clientID, Report1 report1);

	public List<PurchaseReturn> getpreturnweekly(Report1 report1,
			String clientID);

	public List<I0021> getweeklysalesdelivery(String clientID, Report1 report1);

	public List<I0021> getweeklysalespayment(String clientID, Report1 report1);

	public List<SalesReturn> getweeklySalesreturn(String clientID,
			Report1 report1);

	public List<I0021> getweeklyQsalesdelivery(String clientID, Report1 report1);

	public List<I0021> getweeklyQsalespaid(String clientID, Report1 report1);

	public List<SalesReturn> getweeklyQsalesreturn(String clientID,
			Report1 report1);

	public List<Transaction> getweeklyprofit(String clientID, Report1 report1);

	public List<Transaction> getweeklyloss(String clientID, Report1 report1);

	public List<String> getmenus();

	public String insertdepartment(String department);

	public List<Client> getAllClient();

	public String vendorApproval(int i, String approvalStatus);

	public String customerApproval(int buyer_ID, String approvalStatus); 
	
	public List<ProductViewMB> getcategoryList(String approvalStatus);

	public String categoryApproval(List<ProductViewMB> categoryList);

	public List<I0005> getcategoryview(ProductRegister productRegister);

	public String categoryUpdate(ProductRegister productRegister);

	public String deleteCategory(String id);

	public String productApproval(ArrayList<ProductViewMB> finalList);
	
	//Stanley
	public String setcash(Vendor vendor);

	public List<String> getpaytype();

	public String getvencode(String clientID,String userID);

	public String getcusCode(String clientID,String userID);

	public String setbuycash(Buyer buyer);

	public String setcashupdate(Vendor vendor);

	public String employeeApproval(List<EmployeeDetailsViewFormMB> employeeDetailList);

	public String payrollApproval(List<EmployeePayroll> value1);

	public String purchaseApproval(ArrayList<PurchaseOrder> result4);

	public String salesApproval(ArrayList<SalesViewMB> sales);

	public List<AccountType> accountdescription(String accounttype);

	public String quicksalesApproval(ArrayList<QuickSaleViewMB> sales1);

	public List<String> getquotproductList(String clientID, String userID);

	public List<I0001> getproductVendor(String clientID, String userID,String str);

	public List<I0031> getvendorList(int prodId);

	public int insertQuotation(String clientID, String userID,List<VendorRegisterFormMB> quotationList, int i, String quotationNumber);

	public List<Quotation> getquotationnumber(String clientID, String userID);

	public int getproductid(String clientID, String userID, String productName);

	public int getvendorid(String clientID, String userID, String vendorName);

	public int getproductPrice(int product_id, int vendor_id);

	public String insertQuotationDetails(int quot_id, int vendor_id, int product_price, int product_id, String productName, String vendorName, String productCount);

	public String insertQuotationDetails(int quot_id, int product_price,int product_id, String productName, String productCount);

	public List<Quotation> getquotationApprovallist(String clientID,String userID, List<VendorRegisterFormMB> quotationList, int i);

	public String quotationApproval(int quotationId);

	public List<Quotation> getquotationDetails(String clientID,String quotationNumber);

	public List<QuotationDetail> getquotationDetailsList(int quotationId);
	
	public String deleteQuotation(int quotationId);

	public String deleteQuotationdetail(int quotationDetailsId);

	public void finalQuotation(int quotationId);

	public String finalQuotationDetail(int quotationDetailsId,String choosenStatus);

	public List<Quotation> getfinalquotationList(String clientID,String quotationNumber);

	public List<QuotationDetail> getdinalquotationDetailList(int quotationId);
	
	public int updateQuotation(String clientID, String userID,List<VendorRegisterFormMB> quotationDetailList, int i,String quotationNumber);

	public void removeEditRow(int quotationDetailsId);

	public int getvendorapprovalCount(String clientID);

	public int getcustomerapprovalCount(String clientID);

	public int getpurchaseapprovalCount(String clientID);

	public int getsalesapprovalCount(String clientID, String str);

	public int getemployeeapprovalCount(String clientID);

	public int getpayrollapprovalCount(String clientID);

	public int getcategoryapprovalCount(String clientID);

	public int getproductapprovalCount(String clientID);

	public int getquotationapprovalCount(String clientID);
	
	public List<String> getstatusList();
	// CRM Code start, Not used as of now 
	public String getcmtcode();
	public List<String> getcrmtype(); 
	public List<String> getcrmproduct();
	public String getCrmIndustrySave(Buyer buyer);
	public String saveCrm(Buyer buyer);
	public List<CrmCustomerdetail> getcrmenquiry(String query);
	// CRM Code end, Not used as of now
	public List<String> getcustomername();	
	public List<String> getcustomerDetails(Sales sales);	
	public List<String> getproductlist(String clientID, String userID);
	public String getUnitprice(String productname) throws DemoException;	
	public String insertNewouote(String clientID, String userID,ArrayList<SalesOrderFormMB> mblist, Sales sales);	
	public Map<SalesOrderFormMB, ArrayList<SalesOrderFormMB>> getQuoteview(Map<SalesOrderFormMB, ArrayList<SalesOrderFormMB>> quoteList);
	public String getquotationcode();
	public int getsalesQuoteapprovalCount(String clientID); 	
	public List<SalesQuote> getQuoteviewdetails(String clientID,int quoteID, Sales sales);
	public List<SalesQuoteDetails> getsalesquotationdetails(int quoteID);
	public String quoteTabledelete(int quoteID);
	public String consoleUpdate(String clientID,ArrayList<SalesOrderFormMB> quoteListDetails, int quoteID);
	public List<SalesQuote> getquoteApprovallist(String clientID, String userID,ArrayList<SalesOrderFormMB> quoteTablelist, int i);
	public String quoteApproval(int sales_quote_ID); 
		
	public String codeSave(ATransaction aTransaction);

	public List<String> getCodelist(String clientID);

	public List<Code> codeDetails(ATransaction aTransaction);

	public void getDescription(ATransaction aTransaction);
	
	public String paymentSave(Buyer buyer);

	public String mamberPaymentUpdate(Buyer buyer);

	public String mamberPaymentDelete(Buyer buyer);

	public String revenueInsertion(Sales sales);

	public List<SalesOrderFormMB> getValuesRevenue(Sales sales);

	public List<Revenue> getViewRevenue(Sales sales);

	public String coformDelete(Sales sales);

	public String revenueUpdate(Sales sales);

	public List<MemberPayment> getmemberPayment(Buyer buyer);

}