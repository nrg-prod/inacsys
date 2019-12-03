package com.inacsys.bo;

import java.text.ParseException;
import java.util.*;

import com.inacsys.domain.ATransaction;
import com.inacsys.domain.AccountsDatabean;
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
import com.inacsys.managedBean.CashAssetMB;
import com.inacsys.managedBean.CommissionFormMB;
import com.inacsys.managedBean.EmployeeDetailsViewFormMB;
import com.inacsys.managedBean.LoginMB;
import com.inacsys.managedBean.PayrollLiablityMB;
import com.inacsys.managedBean.ProductViewMB;
import com.inacsys.managedBean.PurchaseExpenseMB;
//import com.inacsys.managedBean.QuickSaleMB;
import com.inacsys.managedBean.QuickSaleViewMB;
import com.inacsys.managedBean.SalesIncomeMB;
import com.inacsys.managedBean.SalesOrderFormMB;
import com.inacsys.managedBean.SalesViewMB;
import com.inacsys.managedBean.VendorRegisterFormMB;
import com.inacsys.managedBean.VendorViewFormMB;
import com.inacsys.shared.ChartOfAccount;
import com.inacsys.shared.CrmCustomerdetail;
//import com.inacsys.shared.Designation;
import com.inacsys.shared.Employee;
import com.inacsys.shared.ExpenseTransaction;
import com.inacsys.shared.I0001;
import com.inacsys.shared.I0002;
import com.inacsys.shared.I0004;
//import com.inacsys.shared.I0005;
import com.inacsys.shared.I0006;
import com.inacsys.shared.I0015;
import com.inacsys.shared.I0016;
import com.inacsys.shared.I0018;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0021;
import com.inacsys.shared.I0022;
import com.inacsys.shared.I0023;
import com.inacsys.shared.I0025;
import com.inacsys.shared.I0028;
import com.inacsys.shared.I0032;
import com.inacsys.shared.Indexes;
import com.inacsys.shared.Month;
import com.inacsys.shared.Qualification;
import com.inacsys.shared.Revenue;
//import com.inacsys.shared.SalesQuote;
import com.inacsys.shared.SubProduct;
import com.inacsys.shared.Year;

/**
 * This Java Class will communicate with InventoryDao.java
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 * 
 */
public interface DemoBo {
	public List<String> getnames(List<String> names) throws DemoException;
	public String loginBo(LoginAccess loginAccess) throws DemoException;
	public String vendorbo(Vendor vendor) throws DemoException;
	public String countryDrop(Vendor vendor) throws DemoException;
	public List<I0025> vendorUpadteBo(Vendor vendor, List<I0025> I0025)	throws DemoException;
	public String vendorDeleteBo(VendorDelete vendorDelete)	throws DemoException;
	public String vendorModify(Vendor vendor, List<I0025> xx) throws DemoException;
	public String dropDown(ProductRegister productRegister,List<I0004> typeparent, List<I0002> productgroup,List<I0006> industryList) throws DemoException;
	public List<I0004> dropDown(List<I0004> typeparent) throws DemoException;
	public List<I0002> dropDow(List<I0002> productgroup) throws DemoException;
	public List<I0006> dropDo(List<I0006> industryList) throws DemoException;
	public List<String> dropD(List<String> ven) throws DemoException;
	public String saveProductRegister(ProductRegister productRegister) throws DemoException, ParseException;
	public String saveProductEdit(List<I0001> i0001s,ProductRegister productRegister) throws Exception;
	public List<I0001> productView(List<I0001> i0001s,ProductRegister productRegister) throws DemoException;
	public List<I0001> productView1(List<I0001> i0001s,ProductRegister productRegister) throws DemoException;
	public String productReject(ProductRegister productRegister) throws DemoException;
	public String productRemove(ProductRegister productRegister)throws DemoException;
	public List<I0001> autoComplete(List<I0001> auto,ProductRegister productRegister) throws DemoException;
	public List<I0025> purchaseDrop(List<I0025> drop,PurchaseOrder purchaseOrder) throws DemoException;
	public ArrayList<String> changeDrop(String s, ArrayList<String> productlist)throws DemoException;
	public String changeFirmName(PurchaseOrder purchaseOrder)throws DemoException;
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

	public String invoicePurhcase(PurchaseOrder purchaseOrder)
			throws DemoException;

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

	public String addStock1(PurchaseOrder purchaseOrder) throws DemoException;

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

	public String updateSales1(PurchaseOrder purchaseOrder)
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

	public String salesReturnForm(PurchaseOrder purchaseOrder)
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

	public String invoiceSales1(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String accountin(PurchaseOrder purchaseOrder) throws DemoException;

	public ArrayList<I0021> accountin(ArrayList<I0021> drop)
			throws DemoException;

	public String payNowAccount(PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<I0001> dropdownproduct(String productname,
			ArrayList<I0001> productDrop) throws DemoException;

	public ArrayList<I0022> reportInvoice(ArrayList<I0022> category,
			Report1 report1) throws DemoException;

	public ArrayList<I0015> reportPurchase(ArrayList<I0015> purchaseList,
			Report1 report1) throws DemoException;

	public ArrayList<I0021> reportSales(ArrayList<I0021> salesList1,
			Report1 report1) throws DemoException;

	public ArrayList<I0025> vendorView(Vendor vendor) throws DemoException;

	public String salesDelete() throws DemoException;

	public List<I0018> dropDownbatch(List<I0018> batch) throws DemoException;

	public String purchaseProductEdit(PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<I0032> salesCustomer(ArrayList<I0032> buyername,
			PurchaseOrder purchaseOrder) throws DemoException;

	public ArrayList<I0032> salesOrder(ArrayList<I0032> buyername, Buyer b)
			throws DemoException;

	public String buyerDelete(Buyer b) throws DemoException;

	public ArrayList<I0032> customerNameChange(String s) throws DemoException;

	public List<StockView> getStockInfo() throws DemoException;

	public List<I0032> getPhone() throws DemoException;

	public List<I0032> getBuyerUpdate(Buyer b) throws DemoException;

	public List<I0032> getBuyerInfo(String phoneNumber) throws DemoException;

	public List<I0032> getBuyerInfo1(String phoneNumber,String clientID,String userID) throws DemoException;

	public List<I0028> getCountry() throws DemoException;

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

	public String approvalView4(Approval approval) throws DemoException;

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

	/* ranjini */
	public String getpurchaseInfo(PurchaseOrder p) throws DemoException;

	public List<String> getProductName() throws DemoException;

	public List<I0019> getBarCodeData(String productName) throws DemoException;

	public String saveSales(PurchaseOrder purchaseOrder) throws DemoException;

	// ram
	public String qucikSalesConform(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String qucikSalesConform2(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String qucikSalesConform1(PurchaseOrder purchaseOrder)
			throws DemoException;

	public List<PurchaseOrder> getpurchaseDataFVendor(String vendorName)
			throws DemoException;

	/* Sivaranjini 12/1/2015 */
	public int getsalesQuantityOfMonth(Date fDate, Date tDate)
			throws DemoException;

	// sivaranjini 13_1_15
	public float getSalesAmount(Date fDate, Date tDate) throws DemoException;

	public float getpurchaseAmount(Date fDate, Date tDate) throws DemoException;

	public int getStockinQuantity(Date fDate, Date tDate) throws DemoException;

	public int getStockOutQuantity(Date fDate, Date tDate) throws DemoException;

	// sivaranjini 14_1_15
	public int getsalesInvoice(Date fDate, Date tDate) throws DemoException;

	public int getpurchaseInvoice(Date fDate, Date tDate) throws DemoException;

	public List<String> designationInfo(List<String> designate)
			throws DemoException;

	public List<Qualification> qualificationInfo(List<Qualification> qualificate)
			throws DemoException;

	public String employee(EmployeeDetail employee) throws DemoException;

	public String employeeIdInfo(EmployeeDetail employee) throws DemoException;

	public String employeeNameInfo(EmployeeDetail employee)
			throws DemoException;

	public List<String> employeeId(List<String> emploeid) throws DemoException;

	public List<String> employeeName(List<String> emploename)
			throws DemoException;

	public List<EmployeeDetail> getEmployeeDetail(EmployeeDetail employee)
			throws DemoException;

	public List<EmployeeDetail> getEmployeeDetailEdit(EmployeeDetail employee)
			throws DemoException;

	public String employeeIdSearch(EmployeeDetail empid) throws DemoException;

	public int stockInfo() throws DemoException;
	public List<StockView> getStockInInfo() throws DemoException;
	public String saveconfirm(ATransaction save) throws DemoException;
	public String transactionView(ATransaction search) throws DemoException;
	public List<ATransaction> getviewForm(ATransaction view) throws DemoException;
	public List<ATransaction> getEditForm(ATransaction edit) throws DemoException;
	public List<ATransaction> getStatusChange(ATransaction change) throws DemoException;
	public List<SalesIncomeMB> getAccountReceivableSales(Date fDate, Date tDate) throws DemoException;
	public List<CashAssetMB> getCashAsset(Date fDate, Date tDate) throws DemoException;
	public List<PurchaseExpenseMB> getPurchaseExpences(Date fDate, Date tDate)	throws DemoException;
	public List<PayrollLiablityMB> getPayrollLiability(Date fDate, Date tDate) throws DemoException;
	public List<Year> yearInfo(List<Year> yearinfo) throws DemoException;
	public List<Month> monthInfo(List<Month> monthinfo) throws DemoException;
	public List<Employee> empInfo(List<Employee> empinfo) throws DemoException;
	public List<Employee> nameInfo(List<Employee> nameinfo) throws DemoException;
	public String employeeDetails(EmployeeDetail employee) throws DemoException;
	public ArrayList<String> changeEvent(String s, ArrayList<String> list1) throws DemoException;
	public ArrayList<String> changeEvent1(String s, ArrayList<String> list2) throws DemoException;
	public List<Employee> payroll(List<Employee> list3, EmployeePayroll save) throws DemoException;
	public List<Employee> payrollemp(List<Employee> list3, EmployeePayroll save) throws DemoException;
	public String confirm(EmployeePayroll save) throws DemoException;
	public ArrayList<String> changeEvent2(String s, ArrayList<String> list1) throws DemoException;
	public String payroll1(EmployeePayroll pay) throws DemoException;
	public String search1(EmployeePayroll pay) throws DemoException;
	public String search2(EmployeePayroll pay) throws DemoException;
	public List<EmployeePayroll> viewPayrollz(EmployeePayroll view) throws DemoException;
	public List<EmployeePayroll> editPayroll(EmployeePayroll view) throws DemoException;
	public List<EmployeePayroll> viewPayroll(EmployeePayroll view) throws DemoException;
	public ArrayList<String> changezEvent(String s, ArrayList<String> list1) throws DemoException; 
	public ArrayList<String> changeEvent3(String s, ArrayList<String> list2) throws DemoException;
	public String payroll(EmployeePayroll pay) throws DemoException;
	public List<ProductRegister> getProductInInfo(Date startDate, Date endDate) throws DemoException;
	public int productInfo(Date startDate, Date endDate) throws DemoException;
	public List<I0001> dropdownproduct1() throws DemoException;
	public List<String> categorylist1(List<String> categorytype) throws DemoException;
	public String purchaseReturnValuechangeDrop1(String s,PurchaseOrder purchaseOrder) throws DemoException;
	public String purchaseRetViewForm(PurchaseOrder pur) throws DemoException;
	public String purOrderViewproduct(PurchaseOrder purchaseOrder) throws DemoException;
	public String purchReturnSubmit(PurchaseOrder purchaseOrder) throws DemoException;
	public ArrayList<I0032> catogerycustomer(ArrayList<I0032> buyername, PurchaseOrder purchaseOrder) throws DemoException;
	public List<String> customername(Commission commission) throws DemoException;
	public String commissionview(Commission commission) throws DemoException;
	public ArrayList<I0032> customerNameChange1(String s) throws DemoException;
	public String viewPurchaseReturnDetail(PurchaseOrder purchaseOrder) throws DemoException;
	public String viewPurchaseReturn(PurchaseOrder purchaseOrder) throws DemoException;
	public String purchaseReturnInsert(PurchaseOrder purchaseOrder) throws DemoException;
	public List<Commission> viewCommission(Commission c) throws DemoException;
	public String commisionUpdate(Commission c) throws DemoException;
	public String purchaseInfocollect(PurchaseOrder purchaseOrder) throws DemoException;
	public String findCashBook(PurchaseOrder purchaseOrder) throws DemoException;
	public List<SalesIncomeMB> getAccountReceivableSales1(Date fDate, Date tDate) throws DemoException;
	public List<PurchaseExpenseMB> getPurchaseExpences1(Date fDate, Date tDate) throws DemoException;
	public List<I0032> getFreeLancerInfo(String freeLancerName) throws DemoException;
	public List<String> getSONfordispatch() throws DemoException;
	public List<CommissionFormMB> getDispatchData(String soNumber, PurchaseOrder dom) throws DemoException;
	public String getCommisionAmount(String freelancerName) throws DemoException;
	public String salesOrder4Normal(PurchaseOrder purchaseOrder) throws DemoException;
	public int productInfo1() throws DemoException;
	public List<ProductRegister> nonsales() throws DemoException;
	public int outofStock() throws DemoException;
	public List<ProductRegister> outofStock1() throws DemoException;
	public String partialnormalreturn(PurchaseOrder p, int qty) throws DemoException;
	public String partialdamagereturn(PurchaseOrder p, int qty) throws DemoException;
	public int salesreturncount() throws DemoException;
	public String viewAccountReceivable(PurchaseOrder purchaseOrder) throws DemoException;
	public String viewAccountPayable(PurchaseOrder purchaseOrder) throws DemoException;
	public List<Sales> getSalesReturnView() throws DemoException;
	public String designation(EmployeeDetail emp) throws DemoException;
	public String updateDispatch(String str1, String str2, String str3) throws DemoException;
	public String salesconfirm(PurchaseOrder purchaseOrder) throws DemoException;
	public String salesSave(PurchaseOrder purchaseOrder) throws DemoException; 
	public String purchasePrice(PurchaseOrder purchaseOrder) throws DemoException;
	public String purchase(PurchaseOrder purchaseOrder) throws DemoException;
	public String purchase1(PurchaseOrder purchaseOrder) throws DemoException;
	public String purchaseorderClose(PurchaseOrder purchaseOrder) throws DemoException;
	public List<I0023> paymentamount(PurchaseOrder purchaseOrder) throws DemoException;
	public List<I0023> paymentamountsale(PurchaseOrder purchaseOrder) throws DemoException;
	public String returnQuantity(PurchaseOrder purchaseOrder) throws DemoException;
	public String salesReturnSubmit(PurchaseOrder purchaseOrder) throws DemoException;
	public String viewSalesReturnDetail(PurchaseOrder purchaseOrder) throws DemoException;
	public String viewSalesReturn(PurchaseOrder purchaseOrder) throws DemoException;
	public String searchCity(Vendor vendor) throws DemoException;
	public ArrayList<String> changeList(String changeEvent,ArrayList<String> productList, PurchaseOrder purchaseOrder) throws DemoException;
	public String addStockIn(PurchaseOrder purchaseOrder) throws DemoException;
	public ArrayList<String> getRollList(String productName,ArrayList<String> rollList) throws DemoException;
	public String getRollQuantity(PurchaseOrder purchaseOrder)throws DemoException;
	public String qucikSalesRoll(PurchaseOrder purchaseOrder) throws DemoException;
	public String updateRollSales(PurchaseOrder purchaseOrder) throws DemoException;
	public String salesRollconfirm(PurchaseOrder purchaseOrder) throws DemoException;
	public String updateRollQuantity(PurchaseOrder purchaseOrder) throws DemoException;
	public String salesOrderdelete(PurchaseOrder purchaseOrder) throws DemoException;
	public String getProductQuntity(PurchaseOrder purchaseOrder) throws DemoException;
	public List<I0032> customerNameChange11(String s1) throws DemoException;
	public List<Employee> freenamelistser(List<Employee> name) throws DemoException;
	public ArrayList<String> getAddRollList(String productName,ArrayList<String> rollList) throws DemoException;
	public String rollDamage(PurchaseOrder purchaseOrder) throws DemoException;
	public String quickSaleDropdown(PurchaseOrder purchaseOrder) throws DemoException;
	public String quicksaleReturnSubmit(PurchaseOrder purchaseOrder) throws DemoException;
	public String quickViewSalesReturn(PurchaseOrder purchaseOrder) throws DemoException;
	public List<I0019> stockView3(List<I0019> batch2, String s,StockView stockView) throws DemoException;
	public List<String> productVendor1(List<String> batchProductName4) throws DemoException;
	public String getbarcodeInfo(PurchaseOrder purchaseOrder) throws DemoException;

	public String changeUserName(String newuser, String invusername)
			throws DemoException;

	public String changeUserPassword(String newpasswrd, String invpassword)
			throws DemoException;

	public String openingStockInsert(PurchaseOrder opngstock)
			throws DemoException;

	public String getPurchaseQty(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String getpurchaseView(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String getunitprice(String productName, String unitprice)
			throws DemoException;

	public String salesRecordView(PurchaseOrder purchaseOrder)
			throws DemoException;

	public int productInfo2() throws DemoException;

	public ArrayList<String> productnonsales() throws DemoException;

	public ArrayList<String> getRollQuanList(String productName,
			ArrayList<String> rollList) throws DemoException;

	public List<I0032> getcitiesinfo(String city) throws DemoException;

	public List<String> getnames1(List<String> names1) throws DemoException;

	public String designRegSubmit(ProductRegister productRegister)
			throws DemoException, ParseException;

	public String designView(ProductRegister productRegister)
			throws DemoException;

	public String designViews(ProductRegister productRegister)
			throws DemoException;

	public String designViewDelete(ProductRegister productRegister)
			throws DemoException;

	public String designValidate(ProductRegister productRegister)
			throws DemoException;

	public String saveImages(ProductRegister productRegister) throws DemoException;
	public String databaseValidate(LoginAccess loginaccess);
	public String checkErrorcode(LoginAccess loginaccess);
	public String insertCreateUser(UserCreateDataBean userCreateDataBean);
	public List<UserCreateDataBean> insideUserEdit(UserCreateDataBean userCreateDataBean);
	public String insideUpdate(UserCreateDataBean userCreateDataBean);
	public String userInsert(UserCreateDataBean userCreateDataBean)	throws DemoException;
	public String getVendorVerification(String name);
	public String userCheck(UserCreateDataBean userCreateDataBean) throws DemoException;
	public String getCustomerVerification(String name);
	public String retirveUser(UserCreateDataBean userCreateDataBean);
	public List<String> getCustomerInfo(Buyer b);
	public List<String> getproductListInfo(ProductRegister productRegister);
	public String getProductVerification(String name);
	public ArrayList<Report1> getVendorNameList();
	public String findGlobalSearch(String golbalnamesearch);
	public ArrayList<VendorReport> insidevendornamesearch(VendorReport vendorReport);
	public ArrayList<VendorReport> insideallvendornamesearch(VendorReport vendorReport);
	public List<I0001> productView2(List<I0001> i0001s,ProductRegister productRegister, String golbalnamesearch) throws DemoException;
	public ArrayList<I0025> vendorView1(Vendor vendor, String golbalnamesearch) throws DemoException;
	public List<I0032> getBuyercustInfo(Buyer b, String golbalnamesearch) throws DemoException;
	public List<Employee> employeeNameInfo(EmployeeDetail employee,String golbalnamesearch) throws DemoException;
	public List<I0025> getphonenumberglobalsearch(String golbalnamesearch)	throws DemoException;
	public List<I0032> getcustphnosearch(String golbalnamesearch) throws DemoException;
	public List<Employee> getempphnosearch(String golbalnamesearch)	throws DemoException;
	public ArrayList<I0025> getgmailsearch(String golbalnamesearch) throws DemoException;
	public List<I0032> getcustemailsearch(String golbalnamesearch) throws DemoException;
	public List<Employee> getempemailsearch(String golbalnamesearch) throws DemoException;
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
	public List<EmployeePayroll> getEmployeePayrollDetails(EmployeePayroll employeePayroll);
	public String getdocUpload(EmployeeDetail employeeDetail);	
	public void updatequalify(String qualID);
	public void updateExpernce(String expID);
	public List<String> getdepartmentname();
	public String getProductcodeVerification(String name);	
	public String saveAcountDeposit(String clientID, ATransaction payment);
	public List<String> getAccountDepsit(String clientID, ATransaction atrans);
	public String saveTransPayment(String clientID, ATransaction atransaction);
	public String getDailyreport(Report1 report1);
	public List<String> getmenus();
	public String insertdepartment(String department);
	public String vendorApproval(ArrayList<VendorViewFormMB> ven1, String approvalStatus);
	public String customerApproval(ArrayList<BuyersViewMB> catbuy, String approvalStatus);
	public List<ProductViewMB> getcategoryList(String approvalStatus);
	public String categoryApproval(List<ProductViewMB> categoryList);	
	public void getAccountTypes(String clientID,AccountsDatabean accountsDatabean);
	public String saveAcountDeposit(String clientID,AccountsDatabean accountsDatabean);
	public void getCOAdetails(String clientID, AccountsDatabean accountsDatabean);
	public void getviewCOAAccounts(String clientID,AccountsDatabean accountsDatabean);
	public void getTrialBalance(String clientID,AccountsDatabean accountsDatabean);
	public void getProfitLoss(AccountsDatabean accountsDatabean);
	public void getcategoryview(ProductRegister productRegister);
	public String categoryUpdate(ProductRegister productRegister);
	public String deleteCategory(String id);
	public String productApproval(ArrayList<ProductViewMB> finalList);
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
	public ArrayList<String> accountdescription(String accounttype);
	public String quicksalesApproval(ArrayList<QuickSaleViewMB> sales1);
	public List<String> getquotproductList(String clientID, String userID);
	public List<String> getproductVendor(String clientID, String userID,String str);

	public String insertQuotation(String clientID, String userID,List<VendorRegisterFormMB> quotationList);
	public List<VendorRegisterFormMB> getquotationDetails(String clientID,String quotationNumber);
	public String deleteQuotation(String clientID, String quotationNumber);
	public String finalQuotation(String clientID, String quotationNumber,List<VendorRegisterFormMB> quotationDetailList);
	public List<VendorRegisterFormMB> getfinalQuotationList(String clientID,String quotationNumber);
	public String quotationApproval(String clientID, String userID,List<VendorRegisterFormMB> quotationList,VendorRegisterFormMB vendor);
	public String quotationUpdate(String clientID, String userID,String quotationNumber, List<VendorRegisterFormMB> quotationDetailList);
	public void removeEditRow(int quotationDetailsId);
	public List<LoginAccess> getapprovalCountList(String clientID);
	
	public List<String> getstatusList();
	// CRM code start , Not used as of now
	public String getcmtcode();	
	public List<String> getcrmtype(); 
	public List<String> getcrmproduct();
	public String getCrmIndustrySave(Buyer buyer);
	public String saveCrm(Buyer buyer);
	// CRM Code end , Not used as of now
	public List<CrmCustomerdetail> getcrmenquiry(String query);  
	public List<String> getcustomername();
	public List<String> getcustomerDetails(Sales sales);
	public List<String> getproductlist(String clientID, String userID);
	public String getUnitprice(String productname) throws DemoException;
	public String insertNewouote(String clientID, String userID,ArrayList<SalesOrderFormMB> mblist, Sales sales);
	public Map<SalesOrderFormMB, ArrayList<SalesOrderFormMB>> getQuoteview(Map<SalesOrderFormMB, ArrayList<SalesOrderFormMB>> quoteList);
	public String getquotationcode();
	public List<SalesOrderFormMB> getQuoteviewdetails(String clientID,int quoteID, Sales sales);
	public String quoteTabledelete(int quoteID);
	//public int getsalesQuoteapprovalCount(String clientID); 	
	public String consoleUpdate(String clientID,ArrayList<SalesOrderFormMB> quoteListDetails, int quoteID);
	public void expenseResource(String clientID, ATransaction aTransaction);
	public List<ChartOfAccount> accountlist(String clientID);
	public void accountbalance(String clientID, ATransaction aTransaction);
	public String expenseSave(ATransaction aTransaction, String clientID);
	public List<ExpenseTransaction> getbillnumber(String clientID,String transactionType);
	public void mailresource(String clientID, ATransaction aTransaction);
	public List<ATransaction> expensesDataTable(String clientID);
	public void expanseDetailView(String clientID, ATransaction aTransaction);
	public String expenseUpdate(ATransaction aTransaction, String clientID);
	public String expenseMakePayment(ATransaction aTransaction, String clientID);
	public void getCOAtransactiondetails(String clientID,AccountsDatabean accountsDatabean);
	public String editAccount(AccountsDatabean accountsDatabean, String clientID);  
	public void getsalestransactioncustprod(String clientID,ATransaction aTransaction);
	public void getcustomerdetails(String clientID, ATransaction aTransaction);
	public String saveSalesTransaction(String clientID,ATransaction aTransaction, List<ATransaction> productdetails);
	public List<ATransaction> getsalestransactiontableview(String clientID, ATransaction aTransaction);
	public List<ATransaction> getpaymentdetails(String clientID,String paymentStatus, ATransaction aTransaction);
	public String generateInvoice(String clientID, int transactionID);
	public String salesTransactionUpadte(String clientID,ATransaction aTransaction);
	public void salesTransactionView(String clientID, ATransaction aTransaction);
	public void AccountsBalanceCal(AccountsDatabean accountsDatabean); 
	public String quoteApproval(String clientID, String userID,ArrayList<SalesOrderFormMB> quoteTablelist, SalesOrderFormMB sales);
	
	public List<LoginAccess> getglobalsearchList(String clientID, String userID);
	
	public String codeSave(ATransaction aTransaction);

	public List<String> getCodelist(String clientID);

	public List<ATransaction> codeDetails(ATransaction aTransaction);

	public void getDescription(ATransaction aTransaction);
	
	public List<AccountsDatabean> getcoaDetailsList(AccountsDatabean accountsDatabean);

	public List<AccountsDatabean> getjournalEntryList(AccountsDatabean accountsDatabean);
	
	public void getGeneralLedger(String clientID,AccountsDatabean accountsDatabean);
	
	public String paymentSave(Buyer buyer);

	public List<Buyer> getmemberPayment(Buyer buyer);

	public String mamberPaymentUpdate(Buyer buyer);

	public String mamberPaymentDelete(Buyer buyer);

	public String revenueInsertion(Sales sales);

	public List<SalesOrderFormMB> getValuesRevenue(Sales sales);

	public List<Revenue> getViewRevenue(Sales sales);

	public String coformDelete(Sales sales);

	public String revenueUpdate(Sales sales);
}
