package com.inacsys.service;

//import org.apache.log4j.Logger;
//import java.sql.Date;
import java.text.ParseException;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.inacsys.bo.DemoBo;
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
import com.inacsys.domain.VendorUpdate;
import com.inacsys.exception.DemoException;
import com.inacsys.managedBean.BuyersViewMB;
import com.inacsys.managedBean.CashAssetMB;
import com.inacsys.managedBean.CommissionFormMB;
import com.inacsys.managedBean.EmployeeDetailsViewFormMB;
import com.inacsys.managedBean.LoginMB;
import com.inacsys.managedBean.PayrollLiablityMB;
import com.inacsys.managedBean.ProductViewMB;
import com.inacsys.managedBean.PurchaseExpenseMB;
import com.inacsys.managedBean.QuickSaleMB;
import com.inacsys.managedBean.QuickSaleViewMB;
import com.inacsys.managedBean.SalesIncomeMB;
import com.inacsys.managedBean.SalesOrderFormMB;
import com.inacsys.managedBean.SalesViewMB;
import com.inacsys.managedBean.VendorRegisterFormMB;
import com.inacsys.managedBean.VendorViewFormMB;
import com.inacsys.shared.ChartOfAccount;
import com.inacsys.shared.CrmCustomerdetail;
import com.inacsys.shared.Designation;
import com.inacsys.shared.Employee;
import com.inacsys.shared.ExpenseTransaction;
import com.inacsys.shared.I0001;
import com.inacsys.shared.I0002;
import com.inacsys.shared.I0004;
import com.inacsys.shared.I0005;
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
import com.inacsys.shared.SalesQuote;
import com.inacsys.shared.SubProduct;
import com.inacsys.shared.Year;

/**
 * This Java Class will communicate with InventoryBo.java
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 * 
 */
@Service("service")
public class DemoServiceImpl implements DemoService {

	@Autowired
	@Qualifier("bo")
	DemoBo bo;
	private static Logger logger = Logger.getLogger(DemoServiceImpl.class);

	public String loginService(LoginAccess loginaccess) throws DemoException {
		logger.debug("inside service ::::::::::::::::::");
		logger.debug(loginaccess.getUsername());

		bo.loginBo(loginaccess);
		return null;
	}

	public String vendorService(Vendor vendor) throws DemoException {
		logger.debug("inside bo.....");

		return bo.vendorbo(vendor);
	}

	public String countryDrop(Vendor vendor) throws DemoException {
		logger.debug("inside bo.....");
		bo.countryDrop(vendor);

		return "success";
	}

	public List<I0025> vendorUpdateService(Vendor vendor, List<I0025> I0025)
			throws DemoException {
		logger.debug("service");
		I0025 = bo.vendorUpadteBo(vendor, I0025);
		return I0025;
	}

	public String vendorDeleteService(VendorDelete vendorDelete)
			throws DemoException {
		logger.debug("inside vendelete service");
		bo.vendorDeleteBo(vendorDelete);
		return null;
	}

	public String vendorModify(Vendor vendor, List<I0025> xx)
			throws DemoException {
		logger.debug("inside service");
		bo.vendorModify(vendor, xx);
		return null;
	}

	public String dropDown(ProductRegister productRegister,
			List<I0004> typeparent, List<I0002> productgroup,
			List<I0006> industryList) throws DemoException {
		logger.debug("inside service");
		bo.dropDown(productRegister, typeparent, productgroup, industryList);
		return null;
	}

	public List<I0004> dropDown(List<I0004> typeparent) throws DemoException {
		typeparent = bo.dropDown(typeparent);
		return typeparent;
	}

	public List<I0002> dropDow(List<I0002> productgroup) throws DemoException {
		productgroup = bo.dropDow(productgroup);
		return productgroup;
	}

	public List<I0006> dropDo(List<I0006> industryList) throws DemoException {
		industryList = bo.dropDo(industryList);
		return industryList;
	}

	public List<String> dropD(List<String> ven) throws DemoException {
		ven = bo.dropD(ven);
		return ven;
	}

	public String saveProductRegister(ProductRegister productRegister)
			throws DemoException, ParseException {

		return bo.saveProductRegister(productRegister);
	}

	public String saveProductEdit(List<I0001> i0001s,
			ProductRegister productRegister) throws Exception {
		bo.saveProductEdit(i0001s, productRegister);
		return null;
	}

	public List<I0001> productView(List<I0001> i0001s,
			ProductRegister productRegister) throws DemoException {
		System.out
				.println("--------------$$$$$$$$$$$$$$------------inside productView service-------------$$$$$$$$$$$$$$-----------");
		i0001s = bo.productView(i0001s, productRegister);
		System.out
				.println("--------------$$$$$$$$$$$$$$------------Outside productView service-------------$$$$$$$$$$$$$$-----------");
		return i0001s;
	}

	public List<I0001> productView1(List<I0001> i0001s,
			ProductRegister productRegister) throws DemoException {
		System.out
				.println("--------------$$$$$$$$$$$$$$------------inside productView1 service-------------$$$$$$$$$$$$$$-----------");
		i0001s = bo.productView1(i0001s, productRegister);
		System.out
				.println("--------------$$$$$$$$$$$$$$------------Outside productView1 service-------------$$$$$$$$$$$$$$-----------");
		return i0001s;
	}

	@Override
	public List<I0001> productView2(List<I0001> i0001s,
			ProductRegister productRegister, String golbalnamesearch)
			throws DemoException {

		System.out
				.println("--------------$$$$$$$$$$$$$$------------inside productView2 service-------------$$$$$$$$$$$$$$-----------");
		i0001s = bo.productView2(i0001s, productRegister, golbalnamesearch);
		System.out
				.println("--------------$$$$$$$$$$$$$$------------Outside productView2 service-------------$$$$$$$$$$$$$$-----------");
		return i0001s;
	}

	public String productReject(ProductRegister productRegister)
			throws DemoException {
		System.out
				.println("--------------$$$$$$$$$$$$$$------------inside productReject service-------------$$$$$$$$$$$$$$-----------");
		bo.productReject(productRegister);
		System.out
				.println("--------------$$$$$$$$$$$$$$------------Outside productReject service-------------$$$$$$$$$$$$$$-----------");
		return "";
	}

	public String productRemove(ProductRegister productRegister)
			throws DemoException {
		System.out
				.println("--------------$$$$$$$$$$$$$$------------inside productRemove service-------------$$$$$$$$$$$$$$-----------");
		bo.productRemove(productRegister);
		System.out
				.println("--------------$$$$$$$$$$$$$$------------Outside productRemove service-------------$$$$$$$$$$$$$$-----------");
		return null;
	}

	public List<I0001> autoComplete(List<I0001> auto,
			ProductRegister productRegister) throws DemoException {
		auto = bo.autoComplete(auto, productRegister);
		logger.debug(auto);
		return auto;
	}

	public List<I0025> purchaseDrop(List<I0025> drop,
			PurchaseOrder purchaseOrder) throws DemoException {

		logger.debug("inside service::::::::::");
		drop = bo.purchaseDrop(drop, purchaseOrder);
		logger.debug("outside service::::::::::");
		return drop;
	}

	public ArrayList<String> changeDrop(String s, ArrayList<String> productlist)
			throws DemoException {
		productlist = bo.changeDrop(s, productlist);
		return productlist;
	}

	public String changeFirmName(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.changeFirmName(purchaseOrder);
		return "";
	}

	public String changeDrop1(String s) throws DemoException {
		s = bo.changeDrop1(s);
		return s;
	}

	public String puruchaseOrder(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.puruchaseOrder(purchaseOrder);
		return null;
	}

	public String savePuruchaseOrder(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.savePuruchaseOrder(purchaseOrder);
		return null;
	}

	public String savePuruchaseOrder1(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.savePuruchaseOrder1(purchaseOrder);
		return null;
	}

	public ArrayList<I0016> purchaseOrdercancel(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		purchaselist = bo.purchaseOrdercancel(s, purchaselist, purchaseOrder);
		return purchaselist;
	}

	public ArrayList<I0016> purchaseOrdercancel1(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		purchaselist = bo.purchaseOrdercancel1(s, purchaselist, purchaseOrder);
		logger.debug("2");
		return purchaselist;
	}

	public ArrayList<I0015> invoicePurachaseDrop(ArrayList<I0015> purchaselist)
			throws DemoException {
		purchaselist = bo.invoicePurachaseDrop(purchaselist);
		return purchaselist;
	}

	public ArrayList<I0015> invoicePurachaseDrop1(ArrayList<I0015> purchaselist)
			throws DemoException {
		purchaselist = bo.invoicePurachaseDrop1(purchaselist);
		return purchaselist;
	}

	public String purchaseClose(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.purchaseClose(purchaseOrder);
		return null;
	}

	public ArrayList<I0015> dateSearchInvoice(Date fromDate, Date todate,
			ArrayList<I0015> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		purchaselist = bo.dateSearchInvoice(fromDate, todate, purchaselist,
				purchaseOrder);
		logger.debug("sizeeeeeeeeeeeeee" + purchaselist.size());
		return purchaselist;
	}

	public String cancelConform1(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.cancelConform1(purchaseOrder);
		return null;
	}

	public ArrayList<I0016> purchaseView(PurchaseOrder purchaseOrder,
			ArrayList<I0016> purchaselist) throws DemoException {
		purchaselist = bo.purchaseView(purchaseOrder, purchaselist);
		return purchaselist;
	}

	public ArrayList<I0016> invoicePurhcase1(PurchaseOrder purchaseOrder,
			ArrayList<I0016> purchaselist) throws DemoException {
		purchaselist = bo.invoicePurhcase1(purchaseOrder, purchaselist);
		return purchaselist;
	}

	public String invoicePurhcase(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.invoicePurhcase(purchaseOrder);
		return null;
	}

	public ArrayList<String> dropAccount(String s, ArrayList<String> ordernumber)
			throws DemoException {

		ordernumber = bo.dropAccount(s, ordernumber);
		return ordernumber;
	}

	public ArrayList<I0023> AccountOut(PurchaseOrder purchaseOrder,
			ArrayList<I0023> purchaselist) throws DemoException {
		purchaselist = bo.AccountOut(purchaseOrder, purchaselist);
		return purchaselist;
	}

	public String payNow1(PurchaseOrder purchaseOrder) throws DemoException {
		bo.payNow1(purchaseOrder);
		return null;
	}

	public ArrayList<I0016> purchaseOrderPayment(Date s, Date s1,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		purchaselist = bo.purchaseOrderPayment(s, s1, purchaselist,
				purchaseOrder);
		return purchaselist;
	}

	public ArrayList<I0016> purchaseDeliveryStatus(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		purchaselist = bo
				.purchaseDeliveryStatus(s, purchaselist, purchaseOrder);
		return purchaselist;
	}

	public ArrayList<I0016> purchaseDeliveryStatus2(
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		purchaselist = bo.purchaseDeliveryStatus2(purchaselist, purchaseOrder);
		return purchaselist;
	}

	public String deliveryStatus(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.deliveryStatus(purchaseOrder);
		return null;
	}

	public ArrayList<I0015> purchaseOrderClose(ArrayList<I0015> purchaselist,
			PurchaseOrder purchaseOrder) throws DemoException {
		purchaselist = bo.purchaseOrderClose(purchaselist, purchaseOrder);
		return purchaselist;
	}

	public String stockInForm(PurchaseOrder purchaseOrder) throws DemoException {
		bo.stockInForm(purchaseOrder);
		return null;
	}

	public String stockInForm1(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.stockInForm1(purchaseOrder);
		return null;
	}

	public String addStock(PurchaseOrder purchaseOrder) throws DemoException {
		return bo.addStock(purchaseOrder);
	}

	public String addStock1(PurchaseOrder purchaseOrder) throws DemoException {
		bo.addStock1(purchaseOrder);
		return null;
	}

	public List<I0018> salesDrop(List<I0018> batch) throws DemoException {
		batch = bo.salesDrop(batch);
		return batch;
	}

	public List<I0019> stockView(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		batch2 = bo.stockView(batch2, s, stockView);
		return batch2;
	}

	public List<I0019> stockView2(List<I0019> batch2, StockView stockView)
			throws DemoException {
		batch2 = bo.stockView2(batch2, stockView);
		return batch2;
	}

	public List<I0018> stockView1(List<I0018> batch2) throws DemoException {
		batch2 = bo.stockView1(batch2);
		return batch2;
	}

	public String addDamage(PurchaseOrder purchaseOrder) throws DemoException {
		bo.addDamage(purchaseOrder);
		return null;
	}

	public String addDamage1(PurchaseOrder purchaseOrder) throws DemoException {
		bo.addDamage1(purchaseOrder);
		return null;
	}

	public List<I0019> stockdamageView(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		batch2 = bo.stockdamageView(batch2, s, stockView);
		return batch2;
	}

	public String salesOrder1(PurchaseOrder purchaseOrder) throws DemoException {

		bo.salesOrder1(purchaseOrder);
		return null;
	}

	public List<I0018> salesOrder3(List<I0018> batchProductName3)
			throws DemoException {
		batchProductName3 = bo.salesOrder3(batchProductName3);
		return batchProductName3;
	}

	public List<String> productVendor(List<String> batchProductName3)
			throws DemoException {
		batchProductName3 = bo.productVendor(batchProductName3);
		return batchProductName3;
	}

	public List<String> productVendor1(List<String> batchProductName3, String s)
			throws DemoException {
		batchProductName3 = bo.productVendor1(batchProductName3, s);
		return batchProductName3;
	}

	public String salesOrder4(PurchaseOrder purchaseOrder) throws DemoException {

		bo.salesOrder4(purchaseOrder);
		return null;
	}

	public String salesOrder5(PurchaseOrder purchaseOrder) throws DemoException {

		bo.salesOrder5(purchaseOrder);
		return null;
	}

	public List<I0019> stockoutForm(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		batch2 = bo.stockoutForm(batch2, s, stockView);
		return batch2;
	}

	public List<I0019> stockInForm(List<I0019> batch2, String s)
			throws DemoException {
		batch2 = bo.stockInForm(batch2, s);
		return batch2;
	}

	public String salesOrdercancelForm(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesOrdercancelForm(purchaseOrder);
		return null;
	}

	public String salesOrderViewproduct(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesOrderViewproduct(purchaseOrder);
		return null;
	}

	public String salesOrdercancelForm4(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesOrdercancelForm4(purchaseOrder);
		return null;
	}

	public String updateSales1(PurchaseOrder purchaseOrder)
			throws DemoException {

		return bo.updateSales1(purchaseOrder);
	}

	public String salesOrdercancel(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesOrdercancel(purchaseOrder);
		return null;
	}

	public String salesOrdercancelFormsub1(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesOrdercancelFormsub1(purchaseOrder);
		return null;
	}

	public String salesOrdercancelFormsub(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesOrdercancelFormsub(purchaseOrder);
		return null;
	}

	public String salesDrop(PurchaseOrder purchaseOrder) throws DemoException {
		bo.salesDrop(purchaseOrder);
		return null;
	}

	public String salesDrop1(PurchaseOrder purchaseOrder) throws DemoException {
		bo.salesDrop1(purchaseOrder);
		return null;
	}

	public String changeDrop(PurchaseOrder purchaseOrder) throws DemoException {
		bo.changeDrop(purchaseOrder);
		return null;
	}

	public String changeDrop1(PurchaseOrder purchaseOrder) throws DemoException {
		bo.changeDrop1(purchaseOrder);
		return null;
	}

	public String salesOrdercancelForm1(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesOrdercancelForm1(purchaseOrder);
		return null;
	}

	public String salesOrdercancelForm3(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesOrdercancelForm3(purchaseOrder);
		return null;
	}

	public String salesOrdercancelForm2(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesOrdercancelForm2(purchaseOrder);
		return null;
	}

	public String salesOrdercancelForm5(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesOrdercancelForm5(purchaseOrder);
		return null;
	}

	public String salesReturnForm2(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesReturnForm2(purchaseOrder);
		return null;
	}

	public String salesReturnForm3(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesReturnForm3(purchaseOrder);
		return null;
	}

	public String salesReturnForm(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesReturnForm(purchaseOrder);
		return null;
	}

	public String salesReturnForm5(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesReturnForm5(purchaseOrder);
		return null;
	}

	public String salesdelivery(PurchaseOrder purchaseOrder)
			throws DemoException {

		bo.salesdelivery(purchaseOrder);
		return null;
	}

	public List<I0021> salesPayment1(List<I0021> salesreferenumber)
			throws DemoException {
		salesreferenumber = bo.salesPayment1(salesreferenumber);
		return salesreferenumber;
	}

	public String salesPayment2(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.salesPayment2(purchaseOrder);
		return null;
	}

	public String salesView(PurchaseOrder purchaseOrder) throws DemoException {
		bo.salesView(purchaseOrder);
		return null;
	}

	public String invoiceSales(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.invoiceSales(purchaseOrder);
		return null;
	}

	public ArrayList<I0021> invoiceSales(PurchaseOrder purchaseOrder,
			ArrayList<I0021> sales) throws DemoException {
		sales = bo.invoiceSales(purchaseOrder, sales);
		return sales;
	}

	public String invoiceSales1(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.invoiceSales1(purchaseOrder);
		return null;
	}

	public String accountin(PurchaseOrder purchaseOrder) throws DemoException {
		bo.accountin(purchaseOrder);
		return null;
	}

	public ArrayList<I0021> accountin(ArrayList<I0021> drop)
			throws DemoException {
		drop = bo.accountin(drop);
		return drop;
	}

	public String payNowAccount(PurchaseOrder purchaseOrder)
			throws DemoException {
		return bo.payNowAccount(purchaseOrder);
	}

	public ArrayList<I0001> dropdownproduct(String productname,
			ArrayList<I0001> productDrop) throws DemoException {
		logger.debug("inside service:::::::::::::");
		productDrop = bo.dropdownproduct(productname, productDrop);
		return productDrop;
	}

	public ArrayList<I0022> reportInvoice(ArrayList<I0022> invoiceList,
			Report1 report1) throws DemoException {
		invoiceList = bo.reportInvoice(invoiceList, report1);
		logger.debug("out service::::::::::");
		return invoiceList;
	}

	public ArrayList<I0015> reportPurchase(ArrayList<I0015> purchaseList,
			Report1 report1) throws DemoException {
		purchaseList = bo.reportPurchase(purchaseList, report1);
		logger.debug("out service::::::::::");
		return purchaseList;
	}

	public ArrayList<I0021> reportSales(ArrayList<I0021> salesList1,
			Report1 report1) throws DemoException {
		salesList1 = bo.reportSales(salesList1, report1);
		logger.debug("out service::::::::::");
		return salesList1;
	}

	public ArrayList<I0025> vendorView(Vendor vendor) throws DemoException {

		return bo.vendorView(vendor);
	}

	@Override
	public ArrayList<I0025> vendorView1(Vendor vendor, String golbalnamesearch)
			throws DemoException {

		return bo.vendorView1(vendor, golbalnamesearch);
	}

	public String salesDelete() throws DemoException {
		bo.salesDelete();
		return null;
	}

	public List<I0018> dropDownbatch(List<I0018> batch) throws DemoException {
		batch = bo.dropDownbatch(batch);
		return batch;
	}

	public String purchaseProductEdit(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.purchaseProductEdit(purchaseOrder);
		return null;
	}

	public String saveBuyer(Buyer b) throws DemoException {
		return bo.saveBuyer(b);
	}

	public List<I0028> getCountry() throws DemoException {
		return bo.getCountry();
	}

	public int getPurchasestatus() throws DemoException {
		return bo.getPurchasestatus();
	}

	public int getsalesstatus() throws DemoException {
		return bo.getsalesstatus();
	}

	public int getSalesOrderStatus() throws DemoException {
		return bo.getSalesOrderStatus();
	}

	public int getPurchaseorderStatus() throws DemoException {
		return bo.getPurchaseorderStatus();
	}

	public List<I0032> getBuyerInfo(String phoneNumber) throws DemoException {
		return bo.getBuyerInfo(phoneNumber);
	}

	public List<I0032> getBuyerInfo1(String phoneNumber,String clientID,String userID) throws DemoException {
		return bo.getBuyerInfo1(phoneNumber,clientID,userID);
	}

	public List<I0032> getBuyerUpdate(Buyer b) throws DemoException {
		return bo.getBuyerUpdate(b);
	}

	public List<I0032> getPhone() throws DemoException {
		return bo.getPhone();
	}

	public ArrayList<I0032> salesCustomer(ArrayList<I0032> buyername,
			PurchaseOrder purchaseOrder) throws DemoException {
		return bo.salesCustomer(buyername, purchaseOrder);
	}

	public ArrayList<I0032> salesOrder(ArrayList<I0032> buyername, Buyer b)
			throws DemoException {
		return bo.salesOrder(buyername, b);
	}

	public String buyerDelete(Buyer b) throws DemoException {
		return bo.buyerDelete(b);
	}

	public ArrayList<I0032> customerNameChange(String s) throws DemoException {
		return bo.customerNameChange(s);
	}

	public List<StockView> getStockInfo() throws DemoException {

		return bo.getStockInfo();
	}

	public String filePath(PurchaseOrder purchaseOrder) throws DemoException {
		bo.filePath(purchaseOrder);
		return null;
	}

	public String fileSave(PurchaseOrder purchaseOrder) throws DemoException {
		bo.fileSave(purchaseOrder);
		return null;
	}

	public String approvalView(Approval approval) throws DemoException {
		bo.approvalView(approval);
		return null;
	}

	public String approvalView1(Approval approval) throws DemoException {
		bo.approvalView1(approval);
		return null;
	}

	public String approvalView2(Approval approval) throws DemoException {
		bo.approvalView2(approval);
		return null;
	}

	public String approvalView3(Approval approval) throws DemoException {
		bo.approvalView3(approval);
		return null;
	}

	public String approvalView4(Approval approval) throws DemoException {
		bo.approvalView4(approval);
		return null;
	}

	/* udhaya 30.12.2014 */
	public String categoryType(CategoryRegistration categoryreg)
			throws DemoException {
		logger.debug("inside service--->>");
		return bo.categoryType(categoryreg);
	}

	/* udhaya 31.12.2014 */
	public List<String> categorylist(List<String> categorytype)
			throws DemoException {
		logger.debug("inside service----->>");
		return bo.categorylist(categorytype);
	}

	public String category(ProductRegister productRegister)
			throws DemoException {
		logger.debug("inside service----->>");
		return bo.category(productRegister);
	}

	/* udhaya 2.1.2015 */
	public int getPurchaseapprovalstatus() throws DemoException {
		logger.debug("inside service----->>");
		return bo.getPurchaseapprovalstatus();
	}

	public String approvalstatus(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("inside service----->>");
		return bo.approvalstatus(purchaseOrder);
	}

	/* udhaya 5.1.2015 */
	public ArrayList<I0016> approvalStage(String orderNumber,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("inside service----->>");
		return bo.approvalStage(orderNumber, purchaselist, purchaseOrder);
	}

	public String approved(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("inside service----->>");
		return bo.approved(purchaseOrder);
	}

	public String quickSaleView(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("Inside Service->>>");
		return bo.quickSaleView(purchaseOrder);
	}

	/* udhaya 7.1.2015 */
	public List<I0021> view(List<I0021> saleslist, PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("Inside Service->>>");
		return bo.view(saleslist, purchaseOrder);
	}

	public List<PurchaseOrder> getQuicksaleEdit(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("Inside Service->>>");
		return bo.getQuicksaleEdit(purchaseOrder);
	}

	/* udhaya 8.1.2015 */
	public List<I0021> customerNameChange(List<I0021> cusname)
			throws DemoException {
		logger.debug("Inside Service->>>");
		return bo.customerNameChange(cusname);
	}

	/* jeni */
	public List<I0032> getBuyercustInfo(Buyer b) throws DemoException {
		logger.debug("Inside Service");
		return bo.getBuyercustInfo(b);
	}

	@Override
	public List<I0032> getBuyercustInfo(Buyer b, String golbalnamesearch)
			throws DemoException {

		return bo.getBuyercustInfo(b, golbalnamesearch);
	}

	public String getPurchaseVendorView(PurchaseOrder purchaseVendor)
			throws DemoException {
		logger.debug("Inside Service");
		return bo.getPurchaseVendorView(purchaseVendor);
	}

	/* kasturi */

	public ArrayList<I0016> purchaseOrderPayment1(
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.purchaseOrderPayment1(purchaselist, purchaseOrder);
		return null;
	}

	public String remSalesDeliver(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.remSalesDeliver(purchaseOrder);
		return null;
	}

	public String remSalesDelivery(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.remSalesDelivery(purchaseOrder);
		return null;
	}

	public ArrayList<I0016> remPurchaseDeliver(ArrayList<I0016> purchaselist,
			PurchaseOrder purchaseOrder) throws DemoException {
		bo.remPurchaseDeliver(purchaselist, purchaseOrder);
		return null;
	}

	public String remPurchasePaymentStatus(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.remPurchasePaymentStatus(purchaseOrder);
		return null;
	}

	/* jeni */
	public String salesPaypend(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("Inside Service");
		return bo.salesPaypend(purchaseOrder);
	}

	public int getSalesPayStatus() throws DemoException {
		logger.debug("Inside Service");
		return bo.getSalesPayStatus();

	}

	/* ranjini */
	public String getpurchaseInfo(PurchaseOrder p) throws DemoException {
		return bo.getpurchaseInfo(p);
	}

	public List<String> getProductName() throws DemoException {
		return bo.getProductName();
	}

	public List<I0019> getBarCodeData(String productName) throws DemoException {
		return bo.getBarCodeData(productName);
	}

	public String saveSales(PurchaseOrder purchaseOrder) throws DemoException {
		return bo.saveSales(purchaseOrder);
	}

	public String qucikSalesConform(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.qucikSalesConform(purchaseOrder);
		return null;
	}

	public String qucikSalesConform2(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.qucikSalesConform2(purchaseOrder);
		return null;
	}

	public String qucikSalesConform1(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.qucikSalesConform1(purchaseOrder);
		return null;
	}

	public List<PurchaseOrder> getpurchaseDataFVendor(String vendorName)
			throws DemoException {
		return bo.getpurchaseDataFVendor(vendorName);
	}

	/* Sivaranjini 12/1/2015 */
	public int getsalesQuantityOfMonth(Date fDate, Date tDate)
			throws DemoException {
		return bo.getsalesQuantityOfMonth(fDate, tDate);
	}

	// sivaranjini 13_1_15

	public float getSalesAmount(Date fDate, Date tDate) throws DemoException {
		return bo.getSalesAmount(fDate, tDate);
	}

	public float getpurchaseAmount(Date fDate, Date tDate) throws DemoException {
		return bo.getpurchaseAmount(fDate, tDate);
	}

	public int getStockinQuantity(Date fDate, Date tDate) throws DemoException {
		return bo.getStockinQuantity(fDate, tDate);
	}

	public int getStockOutQuantity(Date fDate, Date tDate) throws DemoException {
		return bo.getStockOutQuantity(fDate, tDate);
	}

	// sivaranjini 14_1_15

	public int getsalesInvoice(Date fDate, Date tDate) throws DemoException {
		return bo.getsalesInvoice(fDate, tDate);
	}

	public int getpurchaseInvoice(Date fDate, Date tDate) throws DemoException {
		return bo.getpurchaseInvoice(fDate, tDate);
	}

	public List<String> designationInfo(List<String> designate)
			throws DemoException {
		logger.debug("inside service------>>>");
		return bo.designationInfo(designate);
	}

	public List<Qualification> qualificationInfo(List<Qualification> qualificate)
			throws DemoException {
		logger.debug("inside service------>>>");
		return bo.qualificationInfo(qualificate);
	}

	public String employee(EmployeeDetail employee) throws DemoException {
		logger.debug("inside service------>>>");
		return bo.employee(employee);
	}

	public String employeeNameInfo(EmployeeDetail employee)
			throws DemoException {
		logger.debug("inside service------>>>");
		return bo.employeeNameInfo(employee);
	}

	@Override
	public List<Employee> employeeNameInfo(EmployeeDetail employee,
			String golbalnamesearch) throws DemoException {

		return bo.employeeNameInfo(employee, golbalnamesearch);
	}

	public String employeeIdInfo(EmployeeDetail employee) throws DemoException {
		logger.debug("inside service------>>>");
		return bo.employeeIdInfo(employee);
	}

	public List<String> employeeId(List<String> emploeid) throws DemoException {
		logger.debug("inside service------>>>");
		return bo.employeeId(emploeid);
	}

	public List<String> employeeName(List<String> emploename)
			throws DemoException {
		logger.debug("inside service------>>>");
		return bo.employeeName(emploename);
	}

	public List<EmployeeDetail> getEmployeeDetail(EmployeeDetail employee)
			throws DemoException {
		logger.debug("inside service------>>>");
		return bo.getEmployeeDetail(employee);
	}

	public List<EmployeeDetail> getEmployeeDetailEdit(EmployeeDetail employee)
			throws DemoException {
		logger.debug("inside service------>>>");
		return bo.getEmployeeDetailEdit(employee);
	}

	public String employeeIdSearch(EmployeeDetail empid) throws DemoException {
		return bo.employeeIdSearch(empid);
	}

	/* siva20_2_15 */
	public int stockInfo() throws DemoException {
		return bo.stockInfo();
	}

	public List<StockView> getStockInInfo() throws DemoException {
		return bo.getStockInInfo();
	}

	/* siva 23_2_15 */

	public String saveconfirm(ATransaction save) throws DemoException {
		logger.debug("inside service------>>>");
		return bo.saveconfirm(save);
	}

	public String transactionView(ATransaction search) throws DemoException {
		logger.debug("inside controller------->>>");
		return bo.transactionView(search);
	}

	public List<ATransaction> getviewForm(ATransaction view)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return bo.getviewForm(view);
	}

	public List<ATransaction> getEditForm(ATransaction edit)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return bo.getEditForm(edit);
	}

	public List<ATransaction> getStatusChange(ATransaction change)
			throws DemoException {
		logger.debug("---------inside controller---------");
		return bo.getStatusChange(change);
	}

	public List<SalesIncomeMB> getAccountReceivableSales(Date fDate, Date tDate)
			throws DemoException {
		return bo.getAccountReceivableSales(fDate, tDate);
	}

	public List<CashAssetMB> getCashAsset(Date fDate, Date tDate)
			throws DemoException {
		return bo.getCashAsset(fDate, tDate);
	}

	public List<PurchaseExpenseMB> getPurchaseExpences(Date fDate, Date tDate)
			throws DemoException {
		return bo.getPurchaseExpences(fDate, tDate);
	}

	public List<PayrollLiablityMB> getPayrollLiability(Date fDate, Date tDate)
			throws DemoException {
		return bo.getPayrollLiability(fDate, tDate);
	}

	public List<Employee> empInfo(List<Employee> empinfo) throws DemoException {

		return bo.empInfo(empinfo);
	}

	public List<Employee> nameInfo(List<Employee> nameinfo)
			throws DemoException {

		return bo.nameInfo(nameinfo);
	}

	public String employeeDetails(EmployeeDetail employee) throws DemoException {

		return bo.employeeDetails(employee);
	}

	public List<Month> monthInfo(List<Month> monthinfo) throws DemoException {
		logger.debug("inside controller------->>>");
		return bo.monthInfo(monthinfo);
	}

	public List<Year> yearInfo(List<Year> yearinfo) throws DemoException {
		logger.debug("inside controller------->>>");
		return bo.yearInfo(yearinfo);
	}

	public ArrayList<String> changeEvent(String s, ArrayList<String> list1)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return bo.changeEvent(s, list1);
	}

	public ArrayList<String> changeEvent1(String s, ArrayList<String> list2)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return bo.changeEvent1(s, list2);
	}

	public List<Employee> payroll(List<Employee> list3, EmployeePayroll save)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return bo.payroll(list3, save);
	}

	public List<Employee> payrollemp(List<Employee> list3, EmployeePayroll save)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return bo.payrollemp(list3, save);
	}

	public String confirm(EmployeePayroll save) throws DemoException {
		logger.debug("inside controller------->>>");
		return bo.confirm(save);
	}

	public ArrayList<String> changeEvent2(String s, ArrayList<String> list1)
			throws DemoException {

		return bo.changeEvent2(s, list1);
		// return null;
	}

	public String payroll1(EmployeePayroll pay) throws DemoException {

		return bo.payroll1(pay);
	}

	public String search1(EmployeePayroll pay) throws DemoException {

		return bo.search1(pay);
	}

	public String search2(EmployeePayroll pay) throws DemoException {

		return bo.search2(pay);
	}

	public List<EmployeePayroll> viewPayrollz(EmployeePayroll view)
			throws DemoException {
		return bo.viewPayrollz(view);
	}

	public List<EmployeePayroll> editPayroll(EmployeePayroll view)
			throws DemoException {
		return bo.editPayroll(view);
	}

	public List<EmployeePayroll> viewPayroll(EmployeePayroll view)
			throws DemoException {
		return bo.viewPayroll(view);
	}

	public ArrayList<String> changezEvent(String s, ArrayList<String> list1)
			throws DemoException {
		return bo.changezEvent(s, list1);
	}

	public ArrayList<String> changeEvent3(String s, ArrayList<String> list2)
			throws DemoException {
		return bo.changeEvent3(s, list2);
	}

	public String payroll(EmployeePayroll pay) throws DemoException {
		return bo.payroll(pay);
	}

	public List<ProductRegister> getProductInInfo(Date startDate, Date endDate)
			throws DemoException {
		logger.debug("inside controller");
		return bo.getProductInInfo(startDate, endDate);
	}

	public int productInfo(Date startDate, Date endDate) throws DemoException {
		logger.debug("inside controller");
		return bo.productInfo(startDate, endDate);

	}

	public List<I0001> dropdownproduct1() throws DemoException {
		return bo.dropdownproduct1();
	}

	public List<String> categorylist1(List<String> categorytype)
			throws DemoException {
		return bo.categorylist1(categorytype);
	}

	public String purchaseReturnValuechangeDrop1(String s,
			PurchaseOrder purchaseOrder) throws DemoException {
		bo.purchaseReturnValuechangeDrop1(s, purchaseOrder);
		return "";
	}

	public String purchaseRetViewForm(PurchaseOrder pur) throws DemoException {
		bo.purchaseRetViewForm(pur);
		return null;
	}

	public String purOrderViewproduct(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.purOrderViewproduct(purchaseOrder);
		return null;
	}

	public String purchReturnSubmit(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.purchReturnSubmit(purchaseOrder);
		return null;
	}

	/* Arun */

	public ArrayList<I0032> catogerycustomer(ArrayList<I0032> buyername,
			PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("arun");
		bo.catogerycustomer(buyername, purchaseOrder);
		return null;
	}

	public List<String> customername(Commission commission)
			throws DemoException {

		return bo.customername(commission);
	}

	public String commissionview(Commission commission) throws DemoException {
		bo.commissionview(commission);
		return null;
	}

	public ArrayList<I0032> customerNameChange1(String s) throws DemoException {
		return bo.customerNameChange1(s);
	}

	public String viewPurchaseReturnDetail(PurchaseOrder purchaseOrder)
			throws DemoException {
		return bo.viewPurchaseReturnDetail(purchaseOrder);
	}

	public String viewPurchaseReturn(PurchaseOrder purchaseOrder)
			throws DemoException {
		return bo.viewPurchaseReturn(purchaseOrder);
	}

	public String purchaseReturnInsert(PurchaseOrder purchaseOrder)
			throws DemoException {
		return bo.purchaseReturnInsert(purchaseOrder);
	}

	public List<Commission> viewCommission(Commission c) throws DemoException {
		return bo.viewCommission(c);
	}

	public String commisionUpdate(Commission c) throws DemoException {
		return bo.commisionUpdate(c);
	}

	public String purchaseInfocollect(PurchaseOrder purchaseOrder)
			throws DemoException {
		return bo.purchaseInfocollect(purchaseOrder);
	}

	public String findCashBook(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.findCashBook(purchaseOrder);
		return null;
	}

	// 04/09/15 Sree
	public List<SalesIncomeMB> getAccountReceivableSales1(Date fDate, Date tDate)
			throws DemoException {
		return bo.getAccountReceivableSales1(fDate, tDate);
	}

	public List<PurchaseExpenseMB> getPurchaseExpences1(Date fDate, Date tDate)
			throws DemoException {
		return bo.getPurchaseExpences1(fDate, tDate);
	}

	public List<I0032> getFreeLancerInfo(String freeLancerName)
			throws DemoException {
		return bo.getFreeLancerInfo(freeLancerName);
	}

	/* siva 10-4-15 */
	public List<String> getSONfordispatch() throws DemoException {
		return bo.getSONfordispatch();
	}

	public List<CommissionFormMB> getDispatchData(String soNumber,
			PurchaseOrder dom) throws DemoException {
		return bo.getDispatchData(soNumber, dom);
	}

	public String getCommisionAmount(String freelancerName)
			throws DemoException {
		return bo.getCommisionAmount(freelancerName);
	}

	public String salesOrder4Normal(PurchaseOrder purchaseOrder)
			throws DemoException {
		return bo.salesOrder4Normal(purchaseOrder);
	}

	public int productInfo1() throws DemoException {
		return bo.productInfo1();
	}

	public int productInfo2() throws DemoException {
		return bo.productInfo2();
	}

	public List<ProductRegister> nonsales() throws DemoException {
		return bo.nonsales();
	}

	public int outofStock() throws DemoException {
		return bo.outofStock();
	}

	public List<ProductRegister> outofStock1() throws DemoException {
		return bo.outofStock1();
	}

	public String partialnormalreturn(PurchaseOrder p, int qty)
			throws DemoException {
		return bo.partialnormalreturn(p, qty);
	}

	public String partialdamagereturn(PurchaseOrder p, int qty)
			throws DemoException {
		return bo.partialdamagereturn(p, qty);
	}

	public int salesreturncount() throws DemoException {
		return bo.salesreturncount();
	}

	public String viewAccountReceivable(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.viewAccountReceivable(purchaseOrder);
		return null;
	}

	public String viewAccountPayable(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.viewAccountPayable(purchaseOrder);
		return null;
	}

	public List<Sales> getSalesReturnView() throws DemoException {
		return bo.getSalesReturnView();
	}

	public String designation(EmployeeDetail emp) throws DemoException {
		return bo.designation(emp);
	}

	public String updateDispatch(String str1, String str2, String str3)
			throws DemoException {
		return bo.updateDispatch(str1, str2, str3);
	}

	public String salesconfirm(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.salesconfirm(purchaseOrder);
		return null;
	}

	public String salesSave(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("customer name----------->>MMNN 1>>>"
				+ purchaseOrder.getCustomername());

		bo.salesSave(purchaseOrder);
		return null;
	}

	public String purchasePrice(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("Service ---->>> ");
		return bo.purchasePrice(purchaseOrder);
	}

	public String purchase(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("service ---->>> ");
		return bo.purchase(purchaseOrder);
	}

	public String purchase1(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("service ---->>> ");
		return bo.purchase1(purchaseOrder);
	}

	public String purchaseorderClose(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("service ---->>> ");
		return bo.purchaseorderClose(purchaseOrder);
	}

	public List<I0023> paymentamount(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("service ---->>> ");
		return bo.paymentamount(purchaseOrder);
	}

	public List<I0023> paymentamountsale(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("service ---->>> ");
		return bo.paymentamountsale(purchaseOrder);
	}

	public String returnQuantity(PurchaseOrder purchaseOrder)
			throws DemoException {
		return bo.returnQuantity(purchaseOrder);
	}

	public String salesReturnSubmit(PurchaseOrder purchaseOrder)
			throws DemoException {
		return bo.salesReturnSubmit(purchaseOrder);
	}

	public String viewSalesReturnDetail(PurchaseOrder purchaseOrder)
			throws DemoException {
		return bo.viewSalesReturnDetail(purchaseOrder);
	}

	public String viewSalesReturn(PurchaseOrder purchaseOrder)
			throws DemoException {
		return bo.viewSalesReturn(purchaseOrder);

	}

	@Override
	public String searchCity(Vendor vendor) throws DemoException {
		bo.searchCity(vendor);
		return null;
	}

	public ArrayList<String> changeList(String changeEvent,
			ArrayList<String> productList, PurchaseOrder purchaseOrder)
			throws DemoException {
		productList = bo.changeList(changeEvent, productList, purchaseOrder);
		return productList;
	}

	@Override
	public String addStockIn(PurchaseOrder purchaseOrder) throws DemoException {
		return bo.addStockIn(purchaseOrder);
	}

	@Override
	public ArrayList<String> getRollList(String productName,
			ArrayList<String> rollList) throws DemoException {
		rollList = bo.getRollList(productName, rollList);
		return rollList;
	}

	@Override
	public String getRollQuantity(PurchaseOrder purchaseOrder)
			throws DemoException {

		return bo.getRollQuantity(purchaseOrder);
	}

	@Override
	public String qucikSalesRoll(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.qucikSalesRoll(purchaseOrder);
		return null;
	}

	@Override
	public String updateRollSales(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.updateRollSales(purchaseOrder);
		return null;
	}

	@Override
	public String salesRollconfirm(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.salesRollconfirm(purchaseOrder);
		return null;
	}

	@Override
	public String updateRollQuantity(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.updateRollQuantity(purchaseOrder);
		return null;
	}

	@Override
	public String salesOrderdelete(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.salesOrderdelete(purchaseOrder);
		return null;
	}

	@Override
	public String getProductQuntity(PurchaseOrder purchaseOrder)
			throws DemoException {

		return bo.getProductQuntity(purchaseOrder);
	}

	@Override
	public List<I0032> customerNameChange11(String s1) throws DemoException {

		return bo.customerNameChange11(s1);
	}

	@Override
	public List<Employee> freenamelistser(List<Employee> name)
			throws DemoException {

		return bo.freenamelistser(name);
	}

	@Override
	public ArrayList<String> getAddRollList(String productName,
			ArrayList<String> rollList) throws DemoException {
		rollList = bo.getAddRollList(productName, rollList);
		return rollList;
	}

	@Override
	public String rollDamage(PurchaseOrder purchaseOrder) throws DemoException {
		bo.rollDamage(purchaseOrder);
		return "";
	}

	@Override
	public String quickSaleDropdown(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.quickSaleDropdown(purchaseOrder);
		return null;
	}

	@Override
	public String quicksaleReturnSubmit(PurchaseOrder purchaseOrder)
			throws DemoException {

		return bo.quicksaleReturnSubmit(purchaseOrder);
	}

	@Override
	public String quickViewSalesReturn(PurchaseOrder purchaseOrder)
			throws DemoException {

		return bo.quickViewSalesReturn(purchaseOrder);
	}

	@Override
	public List<I0019> stockView3(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		batch2 = bo.stockView3(batch2, s, stockView);
		return batch2;
	}

	@Override
	public List<String> productVendor1(List<String> batchProductName4)
			throws DemoException {
		batchProductName4 = bo.productVendor1(batchProductName4);
		return batchProductName4;
	}

	public String getbarcodeInfo(PurchaseOrder purchaseOrder)
			throws DemoException {
		return bo.getbarcodeInfo(purchaseOrder);
	}

	public String changeUserName(String newuser, String invusername)
			throws DemoException {
		return bo.changeUserName(newuser, invusername);
	}

	public String changeUserPassword(String newpasswrd, String invpassword)
			throws DemoException {
		return bo.changeUserPassword(newpasswrd, invpassword);
	}

	public String openingStockInsert(PurchaseOrder opngstock)
			throws DemoException {
		return bo.openingStockInsert(opngstock);
	}

	@Override
	public String getPurchaseQty(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.getPurchaseQty(purchaseOrder);
		return null;
	}

	@Override
	public String getpurchaseView(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.getpurchaseView(purchaseOrder);
		return null;
	}

	@Override
	public String getunitprice(String productName, String unitprice)
			throws DemoException {
		return bo.getunitprice(productName, unitprice);

	}

	public String salesRecordView(PurchaseOrder purchaseOrder)
			throws DemoException {
		bo.salesRecordView(purchaseOrder);
		return null;
	}

	@Override
	public ArrayList<String> productnonsales() throws DemoException {
		return bo.productnonsales();

	}

	@Override
	public ArrayList<String> getRollQuanList(String productName,
			ArrayList<String> rollList) throws DemoException {
		rollList = bo.getRollQuanList(productName, rollList);
		return rollList;
	}

	@Override
	public List<I0032> getcitiesinfo(String city) throws DemoException {
		// TODO Auto-generated method stub
		return bo.getcitiesinfo(city);
	}

	@Override
	public List<String> getnames(List<String> names) throws DemoException {
		names = bo.getnames(names);
		return names;
	}

	@Override
	public List<String> getnames1(List<String> names1) throws DemoException {
		names1 = bo.getnames1(names1);
		return names1;

	}

	public String designRegSubmit(ProductRegister productRegister)
			throws DemoException, ParseException {
		bo.designRegSubmit(productRegister);
		return null;
	}

	public String designView(ProductRegister productRegister)
			throws DemoException {
		return bo.designView(productRegister);
	}

	public String designViews(ProductRegister productRegister)
			throws DemoException {
		return bo.designViews(productRegister);
	}

	public String designViewDelete(ProductRegister productRegister)
			throws DemoException {
		return bo.designViewDelete(productRegister);
	}

	public String designValidate(ProductRegister productRegister)
			throws DemoException {
		return bo.designValidate(productRegister);
	}

	public String saveImages(ProductRegister productRegister)
			throws DemoException {
		logger.debug("-->>service");
		return bo.saveImages(productRegister);
	}

	@Override
	public String databaseValidate(LoginAccess loginaccess) {

		return bo.databaseValidate(loginaccess);
	}

	@Override
	public String checkErrorcode(LoginAccess loginaccess) {

		return bo.checkErrorcode(loginaccess);
	}

	@Override
	public String insertCreateUser(UserCreateDataBean userCreateDataBean) {
		return bo.insertCreateUser(userCreateDataBean);
	}

	@Override
	public List<UserCreateDataBean> insideUserEdit(
			UserCreateDataBean userCreateDataBean) {
		return bo.insideUserEdit(userCreateDataBean);
	}

	@Override
	public String insideUpdate(UserCreateDataBean userCreateDataBean) {

		return bo.insideUpdate(userCreateDataBean);
	}

	@Override
	public String userInsert(UserCreateDataBean userCreateDataBean)
			throws DemoException {
		return bo.userInsert(userCreateDataBean);
	}

	@Override
	public String getVendorVerification(String name) {

		return bo.getVendorVerification(name);
	}

	@Override
	public String userCheck(UserCreateDataBean userCreateDataBean)
			throws DemoException {
		return bo.userCheck(userCreateDataBean);
	}

	@Override
	public String getCustomerVerification(String name) {
		return bo.getCustomerVerification(name);
	}

	@Override
	public String retirveUser(UserCreateDataBean userCreateDataBean) {
		return bo.retirveUser(userCreateDataBean);
	}

	@Override
	public List<String> getCustomerInfo(Buyer b) {
		return bo.getCustomerInfo(b);
	}

	@Override
	public List<String> getproductListInfo(ProductRegister productRegister) {
		return bo.getproductListInfo(productRegister);
	}

	@Override
	public String getProductVerification(String name) {

		return bo.getProductVerification(name);
	}

	@Override
	public ArrayList<Report1> getVendorNameList() {
		return bo.getVendorNameList();
	}

	@Override
	public String findGlobalSearch(String golbalnamesearch) {

		return bo.findGlobalSearch(golbalnamesearch);
	}

	@Override
	public ArrayList<VendorReport> insidevendornamesearch(
			VendorReport vendorReport) {

		return bo.insidevendornamesearch(vendorReport);
	}

	@Override
	public ArrayList<VendorReport> insideallvendornamesearch(
			VendorReport vendorReport) {

		return bo.insideallvendornamesearch(vendorReport);
	}

	@Override
	public List<I0025> getphonenumberglobalsearch(String golbalnamesearch)
			throws DemoException {

		return bo.getphonenumberglobalsearch(golbalnamesearch);
	}

	@Override
	public List<I0032> getcustphnosearch(String golbalnamesearch)
			throws DemoException {

		return bo.getcustphnosearch(golbalnamesearch);
	}

	@Override
	public List<Employee> getempphnosearch(String golbalnamesearch)
			throws DemoException {
		return bo.getempphnosearch(golbalnamesearch);
	}

	@Override
	public ArrayList<I0025> getgmailsearch(String golbalnamesearch)
			throws DemoException {
		return bo.getgmailsearch(golbalnamesearch);
	}

	@Override
	public List<I0032> getcustemailsearch(String golbalnamesearch)
			throws DemoException {
		return bo.getcustemailsearch(golbalnamesearch);
	}

	@Override
	public List<Employee> getempemailsearch(String golbalnamesearch)
			throws DemoException {
		return bo.getempemailsearch(golbalnamesearch);
	}

	@Override
	public List<SubProduct> submenus(int product_ID, String productCode) {
		return bo.submenus(product_ID,productCode);
	}
	
	public String saveClient(ClientDataBean clientDataBean)throws DemoException{
		return bo.saveClient(clientDataBean);
	}
	
	public String clientNoCheck(String phno)throws DemoException{
		return bo.clientNoCheck(phno);
	}
	
	public String getclientDetails(ClientDataBean clientDataBean){
		return bo.getclientDetails(clientDataBean);
	}
	
	public String getclientDetailsView(ClientDataBean clientDataBean){
		return bo.getclientDetailsView(clientDataBean);
	}
	
	public String clientUpdate(ClientDataBean clientDataBean){
		return bo.clientUpdate(clientDataBean);
	}
	
	public String clientDelete(ClientDataBean clientDataBean){
		return bo.clientDelete(clientDataBean);
	}
	
	public String userView(UserCreateDataBean userCreateDataBean){
		return bo.userView(userCreateDataBean);
	}
	
	public String getUserdetails(UserCreateDataBean userCreateDataBean){
		return bo.getUserdetails(userCreateDataBean);
	}
	
	public String userUpdate(UserCreateDataBean userCreateDataBean){
		return bo.userUpdate(userCreateDataBean);
	}
	
	public String userDelete(UserCreateDataBean userCreateDataBean){
		return bo.userDelete(userCreateDataBean);
	}

	@Override
	public String getdashboardCount(LoginAccess loginaccess) {
		return bo.getdashboardCount(loginaccess);
	}

	@Override
	public List<String> getAccountType(String clientID) {
		return bo.getAccountType(clientID);
	}

	@Override
	public String addAccount(ATransaction payment) {
		return bo.addAccount(payment);
	}

	@Override
	public List<String> getstatelist(String country) {
		return bo.getstatelist(country);
	}

	@Override
	public String getempID() {
		return bo.getempID();
	}
	
	public List<EmployeePayroll> getEmployeePayrollDetails(EmployeePayroll employeePayroll){
		return bo.getEmployeePayrollDetails(employeePayroll);
	}
	
	public String getdocUpload(EmployeeDetail employeeDetail){
		return bo.getdocUpload(employeeDetail);
	}
	
	public void updatequalify(String qualID){
		bo.updatequalify(qualID);
	}

	public void updateExpernce(String expID){
		bo.updateExpernce(expID);
	}

	@Override
	public List<String> getdepartmentname() {
		return bo.getdepartmentname();
	}

	public String getProductcodeVerification(String name) {
		return bo.getProductcodeVerification(name);
	}
	
	public String saveAcountDeposit(String clientID, ATransaction payment){
		return bo.saveAcountDeposit(clientID,payment);
	}

	public List<String> getAccountDepsit(String clientID, ATransaction atrans){
		return bo.getAccountDepsit(clientID,atrans);
	}

	public String saveTransPayment(String clientID, ATransaction atransaction){
		return bo.saveTransPayment(clientID,atransaction);
	}

	public String getDailyreport(Report1 report1) {
		return bo.getDailyreport(report1);
	}
	
	public List<String> getmenus() {
		return bo.getmenus();
	}

	public String insertdepartment(String department) {
		return bo.insertdepartment(department);
	}

	public String vendorApproval(ArrayList<VendorViewFormMB> ven1,String approvalStatus) {
		return bo.vendorApproval(ven1,approvalStatus);
	}

	public String customerApproval(ArrayList<BuyersViewMB> catbuy,String approvalStatus) {
		return bo.customerApproval(catbuy,approvalStatus);
	}

	public List<ProductViewMB> getcategoryList(String approvalStatus) {
		return bo.getcategoryList(approvalStatus);
	}

	public String categoryApproval(List<ProductViewMB> categoryList) {
		return bo.categoryApproval(categoryList);
	}

	public void getAccountTypes(String clientID,AccountsDatabean accountsDatabean){
		bo.getAccountTypes(clientID,accountsDatabean);
	}
	
	public String saveAcountDeposit(String clientID,AccountsDatabean accountsDatabean){
		return bo.saveAcountDeposit(clientID,accountsDatabean);
	}
	
	public void getCOAdetails(String clientID, AccountsDatabean accountsDatabean){
		bo.getCOAdetails(clientID,accountsDatabean);
	}
	
	public void getviewCOAAccounts(String clientID,AccountsDatabean accountsDatabean){
		bo.getviewCOAAccounts(clientID,accountsDatabean);
	}
	
	public void getGeneralLedger(String clientID,AccountsDatabean accountsDatabean){
		bo.getGeneralLedger(clientID,accountsDatabean);
	}
	
	public void getTrialBalance(String clientID,AccountsDatabean accountsDatabean){
		bo.getTrialBalance(clientID,accountsDatabean);
	}	

	public void getProfitLoss(AccountsDatabean accountsDatabean){
		bo.getProfitLoss(accountsDatabean);
	}
	
	public void getBalanceSheet(String clientID,AccountsDatabean accountsDatabean){
		//bo.getBalanceSheet(clientID,accountsDatabean);
	}

	public void getcategoryview(ProductRegister productRegister) {
		bo.getcategoryview(productRegister);
	}
	
	public String categoryUpdate(ProductRegister productRegister) {
		return bo.categoryUpdate(productRegister);
	}
	
	public String deleteCategory(String id) {
		return bo.deleteCategory(id);
	}
	
	public String productApproval(ArrayList<ProductViewMB> finalList) {
		return bo.productApproval(finalList);
	}
	
	//Stanley
	public String setcash(Vendor vendor) {
		return bo.setcash(vendor); 
	}

	public List<String> getpaytype() {		
		return bo.getpaytype();
	}

	@Override
	public String getvencode(String clientID,String userID) {
		return bo.getvencode(clientID,userID);
	}
	
	@Override
	public String getcusCode(String clientID,String userID) {
		return bo.getcusCode(clientID,userID);
	}

	@Override
	public String setbuycash(Buyer buyer) {
		return bo.setbuycash(buyer); 
	}

	@Override
	public String setcashupdate(Vendor vendor) {
		return bo.setcashupdate(vendor); 
	}

	@Override
	public String employeeApproval(List<EmployeeDetailsViewFormMB> employeeDetailList) {
		return bo.employeeApproval(employeeDetailList); 
	}
	
	public String payrollApproval(List<EmployeePayroll> value1) {
		return bo.payrollApproval(value1);
	}
	
	@Override
	public String salesApproval(ArrayList<SalesViewMB> sales) {		
		return bo.salesApproval(sales);
	}

	@Override
	public String purchaseApproval(ArrayList<PurchaseOrder> result4) {
		return bo.purchaseApproval(result4);
	}
	
	public ArrayList<String> accountdescription(String accounttype) {
		return bo.accountdescription(accounttype);
	}

	public String quicksalesApproval(ArrayList<QuickSaleViewMB> sales1) {
		return bo.quicksalesApproval(sales1);
	}
	
	public List<String> getquotproductList(String clientID, String userID) {
		return bo.getquotproductList(clientID,userID);
	} 
	
	@Override
	public List<String> getproductVendor(String clientID, String userID,String str) {
		return bo.getproductVendor(clientID,userID,str);
	}

	@Override
	public String insertQuotation(String clientID, String userID,List<VendorRegisterFormMB> quotationList) {
		return bo.insertQuotation(clientID,userID,quotationList);
	}

	@Override
	public String quotationApproval(String clientID, String userID,List<VendorRegisterFormMB> quotationList,VendorRegisterFormMB vendor) {
		return bo.quotationApproval(clientID,userID,quotationList,vendor);
	}
	
	@Override
	public List<VendorRegisterFormMB> getquotationDetails(String clientID,String quotationNumber) {
		return bo.getquotationDetails(clientID,quotationNumber);
	}
	
	@Override
	public String deleteQuotation(String clientID, String quotationNumber) {
		return bo.deleteQuotation(clientID,quotationNumber);
	}

	@Override
	public String finalQuotation(String clientID, String quotationNumber,List<VendorRegisterFormMB> quotationDetailList) {
		return bo.finalQuotation(clientID,quotationNumber,quotationDetailList);
	}
	
	@Override
	public List<VendorRegisterFormMB> getfinalQuotationList(String clientID,String quotationNumber) {
		return bo.getfinalQuotationList(clientID,quotationNumber);
	}
	
	@Override
	public String quotationUpdate(String clientID,String userID, String quotationNumber,List<VendorRegisterFormMB> quotationDetailList) {
		return bo.quotationUpdate(clientID,userID,quotationNumber,quotationDetailList);
	}

	@Override
	public void removeEditRow(int quotationDetailsId) {
		bo.removeEditRow(quotationDetailsId);
	}
	
	@Override
	public List<LoginAccess> getapprovalCountList(String clientID) {
		return bo.getapprovalCountList(clientID);
	}
	
	@Override
	public List<String> getstatusList() {
		return bo.getstatusList();
	}

	@Override
	public String getcmtcode() {
		return bo.getcmtcode();
	}
	
	@Override
	public List<String> getcrmtype() {
		
		return bo.getcrmtype();
	}

	@Override
	public List<String> getcrmproduct() {
		return bo.getcrmproduct();
	}
	
	@Override
	public String getCrmIndustrySave(Buyer buyer) {
		
		return bo.getCrmIndustrySave(buyer); 
	}

	public String saveCrm(Buyer buyer) {
		return bo.saveCrm(buyer); 
	}
	
	@Override
	public List<CrmCustomerdetail> getcrmenquiry(String query) {		
		return bo.getcrmenquiry(query); 
	}

	
	@Override
	public List<String> getcustomername() {
		return bo.getcustomername();
	}

	@Override
	public List<String> getcustomerDetails(Sales sales) {
		return bo.getcustomerDetails(sales); 
	}

	@Override
	public List<String> getproductlist(String clientID, String userID) {
		return bo.getproductlist(clientID, userID); 
	}
	
	@Override
	public String getUnitprice(String productname) throws DemoException {
		return bo.getUnitprice(productname); 
	}
	
	@Override
	public String insertNewouote(String clientID, String userID,ArrayList<SalesOrderFormMB> mblist, Sales sales) {
		return bo.insertNewouote(clientID, userID, mblist, sales); 
	}
	
	@Override
	public Map<SalesOrderFormMB, ArrayList<SalesOrderFormMB>> getQuoteview(Map<SalesOrderFormMB, ArrayList<SalesOrderFormMB>> quoteList) {
		return bo.getQuoteview(quoteList); 
	}
	
	@Override
	public String getquotationcode() {
		return bo.getquotationcode(); 
	}
	
	@Override
	public List<SalesOrderFormMB> getQuoteviewdetails(String clientID,int quoteID, Sales sales) {
		return bo.getQuoteviewdetails(clientID, quoteID, sales);
	} 
	
	@Override
	public String quoteTabledelete(int quoteID) {
		return bo.quoteTabledelete(quoteID); 
	} 

	@Override
	public String consoleUpdate(String clientID,ArrayList<SalesOrderFormMB> quoteListDetails,int quoteID) {
		return bo.consoleUpdate(clientID, quoteListDetails,quoteID); 
	}
	
	
	@Override
	public void expenseResource(String clientID, ATransaction aTransaction) {
		bo.expenseResource(clientID,aTransaction);
	}

	@Override
	public List<ChartOfAccount> accountlist(String clientID) {
		
		return bo.accountlist(clientID);
	}

	@Override
	public void accountbalance(String clientID, ATransaction aTransaction) {
		bo.accountbalance(clientID,aTransaction);
	}

	@Override
	public String expenseSave(ATransaction aTransaction, String clientID) {
		return bo.expenseSave(aTransaction,clientID);
	}

	@Override
	public List<ExpenseTransaction> getbillnumber(String clientID,String transactionType) {
		return bo.getbillnumber(clientID,transactionType);
	}

	@Override
	public void mailresource(String clientID, ATransaction aTransaction) {
		bo.mailresource(clientID,aTransaction);
		
	}

	@Override
	public List<ATransaction> expensesDataTable(String clientID) {
		return bo.expensesDataTable(clientID);
	}

	@Override
	public void expanseDetailView(String clientID, ATransaction aTransaction) {
		bo.expanseDetailView(clientID,aTransaction);
		
	}

	@Override
	public String expenseUpdate(ATransaction aTransaction, String clientID) {
		return bo.expenseUpdate(aTransaction , clientID);
	}

	@Override
	public String expenseMakePayment(ATransaction aTransaction, String clientID) {
		return bo.expenseMakePayment(aTransaction , clientID);
	}

	@Override
	public void getCOAtransactiondetails(String clientID,AccountsDatabean accountsDatabean) {
		bo.getCOAtransactiondetails(clientID,accountsDatabean);
		
	}

	@Override
	public String editAccount(AccountsDatabean accountsDatabean, String clientID) {
		return bo.editAccount(accountsDatabean , clientID);
	}
	
	@Override
	public void getsalestransactioncustprod(String clientID,ATransaction aTransaction) {		
		bo.getsalestransactioncustprod(clientID,aTransaction);
	}
	
	@Override
	public void getcustomerdetails(String clientID, ATransaction aTransaction) {
		bo.getcustomerdetails(clientID,aTransaction);
	}
	
	@Override
	public String saveSalesTransaction(String clientID,ATransaction aTransaction, List<ATransaction> productdetails) {
		return bo.saveSalesTransaction(clientID,aTransaction,productdetails);
	}

	@Override
	public List<ATransaction> getsalestransactiontableview(String clientID,ATransaction aTransaction) {
		return bo.getsalestransactiontableview(clientID,aTransaction);
	}
	
	@Override
	public List<ATransaction> getpaymentdetails(String clientID,String paymentStatus, ATransaction aTransaction) {
		return bo.getpaymentdetails(clientID,paymentStatus,aTransaction);
	}

	@Override
	public String generateInvoice(String clientID, int transactionID) {
		return bo.generateInvoice(clientID,transactionID);
	}
	
	@Override
	public String salesTransactionUpadte(String clientID,ATransaction aTransaction) {
		return bo.salesTransactionUpadte(clientID,aTransaction);
	}

	@Override
	public void salesTransactionView(String clientID, ATransaction aTransaction) {
		bo.salesTransactionView(clientID,aTransaction);
	}

	@Override
	public void AccountsBalanceCal(AccountsDatabean accountsDatabean) {
		bo.AccountsBalanceCal(accountsDatabean);
	}
	
	@Override
	public String quoteApproval(String clientID, String userID,ArrayList<SalesOrderFormMB> quoteTablelist, SalesOrderFormMB sales) {
		return bo.quoteApproval(clientID, userID, quoteTablelist, sales); 
	}
	
	@Override
	public List<LoginAccess> getglobalsearchList(String clientID, String userID){
		return bo.getglobalsearchList(clientID,userID); 
	}
		
	@Override
	public String codeSave(ATransaction aTransaction) {
		return bo.codeSave(aTransaction);
	}

	@Override
	public List<String> getCodelist(String clientID) {
		return bo.getCodelist(clientID);
	}

	@Override
	public List<ATransaction> codeDetails(ATransaction aTransaction) {
		return bo.codeDetails(aTransaction);
	}

	@Override
	public void getDescription(ATransaction aTransaction) {
		bo.getDescription(aTransaction);
	}

	@Override
	public List<AccountsDatabean> getcoaDetailsList(AccountsDatabean accountsDatabean) {
		return bo.getcoaDetailsList(accountsDatabean);
	}
	
	@Override
	public List<AccountsDatabean> getjournalEntryList(AccountsDatabean accountsDatabean) {
		return bo.getjournalEntryList(accountsDatabean);
	}

	@Override
	public String paymentSave(Buyer buyer) {
		return bo.paymentSave(buyer);
	}

	@Override
	public List<Buyer> getmemberPayment(Buyer buyer) {
		return bo.getmemberPayment(buyer);
	}

	@Override
	public String mamberPaymentUpdate(Buyer buyer) {
		return bo.mamberPaymentUpdate(buyer);
	}

	@Override
	public String mamberPaymentDelete(Buyer buyer) {
		return bo.mamberPaymentDelete(buyer);
	}

	@Override
	public String revenueInsertion(Sales sales) {
		return bo.revenueInsertion(sales);
	}

	@Override
	public List<SalesOrderFormMB> getValuesRevenue(Sales sales) {
		return bo.getValuesRevenue(sales);
	}

	@Override
	public List<Revenue> getViewRevenue(Sales sales) {
		return bo.getViewRevenue(sales);
	}

	@Override
	public String coformDelete(Sales sales) {
		return bo.coformDelete(sales);
	}

	@Override
	public String revenueUpdate(Sales sales) {
		return bo.revenueUpdate(sales);
	}

}
