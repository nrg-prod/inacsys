package com.inacsys.controler;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.URL;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;
//import java.sql.Date;
import java.text.ParseException;
import java.util.*;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

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
import com.inacsys.service.DemoService;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This Java Class will communicate with InventoryBo.java
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization
 * 
 */

@Controller("controller")
public class DemoControllerImpl implements DemoController {

	final Logger logger = LoggerFactory.getLogger(DemoControllerImpl.class);

	@Autowired
	DemoService service;

	public DemoService getService() {
		return service;
	}

	public void setService(DemoService service) {
		this.service = service;
	}

	public String userLogin(LoginAccess loginaccess) throws DemoException {

		logger.debug("inside controller");
		logger.debug(loginaccess.getUsername());
		logger.debug(" controller");
		service.loginService(loginaccess);
		logger.debug("outside controller");
		return "";
	}

	public String venderReg(Vendor vendor) throws DemoException {
		logger.debug("inside controller");

		return service.vendorService(vendor);
	}

	public String countryDrop(Vendor vendor) throws DemoException {
		logger.debug("inside controller");
		service.countryDrop(vendor);
		return "success";
	}

	public List<I0025> vendorUpdate(Vendor vendor, List<I0025> I0025)
			throws DemoException {
		logger.debug("controller");
		logger.debug(vendor.getFirmName());
		I0025 = service.vendorUpdateService(vendor, I0025);
		return I0025;
	}

	public String vendorDelete(VendorDelete vendorDelete) throws DemoException {
		logger.debug("inside vendor delete controller::::");
		service.vendorDeleteService(vendorDelete);
		return null;
	}

	public String vendorModify(Vendor vendor, List<I0025> xx)
			throws DemoException {
		logger.debug("inside controller");
		service.vendorModify(vendor, xx);
		return null;
	}

	public String dropDown(ProductRegister productRegister,
			List<I0004> typeparent, List<I0002> productgroup,
			List<I0006> industryList) throws DemoException {
		logger.debug("inside controller");
		service.dropDown(productRegister, typeparent, productgroup,
				industryList);
		return null;
	}

	public List<I0004> dropDown(List<I0004> typeparent) throws DemoException {
		typeparent = service.dropDown(typeparent);
		return typeparent;
	}

	public List<I0002> dropDow(List<I0002> productgroup) throws DemoException {
		productgroup = service.dropDow(productgroup);
		return productgroup;
	}

	public List<I0006> dropDo(List<I0006> industryList) throws DemoException {
		industryList = service.dropDo(industryList);
		return industryList;
	}

	public List<String> dropD(List<String> ven) throws DemoException {
		ven = service.dropD(ven);
		return ven;
	}

	public String saveProductRegister(ProductRegister productRegister)
			throws DemoException, ParseException {

		return service.saveProductRegister(productRegister);
	}

	public String saveProductEdit(List<I0001> i0001s,
			ProductRegister productRegister) throws Exception {

		return service.saveProductEdit(i0001s, productRegister);
	}

	public String saveProductEdit(ProductRegister productRegister)
			throws DemoException {

		return null;
	}

	public List<I0001> productView(List<I0001> i0001s,
			ProductRegister productRegister) throws DemoException {
		logger.debug("--------------$$$$$$$$$$$$$$------------inside productView controller-------------$$$$$$$$$$$$$$-----------");
		i0001s = service.productView(i0001s, productRegister);
		logger.debug("--------------$$$$$$$$$$$$$$------------Outside productView controller-------------$$$$$$$$$$$$$$-----------");
		return i0001s;
	}

	public List<I0001> productView1(List<I0001> i0001s,
			ProductRegister productRegister) throws DemoException {
		logger.debug("--------------$$$$$$$$$$$$$$------------inside productView1 controller-------------$$$$$$$$$$$$$$-----------");
		i0001s = service.productView1(i0001s, productRegister);
		logger.debug("--------------$$$$$$$$$$$$$$------------Outside productView1 controller-------------$$$$$$$$$$$$$$-----------");
		return i0001s;
	}

	@Override
	public List<I0001> productView2(List<I0001> i0001s,
			ProductRegister productRegister, String golbalnamesearch)
			throws DemoException {
		logger.debug("--------------$$$$$$$$$$$$$$------------inside productView2 controller-------------$$$$$$$$$$$$$$-----------");
		i0001s = service
				.productView2(i0001s, productRegister, golbalnamesearch);
		logger.debug("--------------$$$$$$$$$$$$$$------------Outside productView2 controller-------------$$$$$$$$$$$$$$-----------");
		return i0001s;

	}

	public String productReject(ProductRegister productRegister)
			throws DemoException {
		logger.debug("--------------$$$$$$$$$$$$$$------------inside productReject controller-------------$$$$$$$$$$$$$$-----------");
		service.productReject(productRegister);
		logger.debug("--------------$$$$$$$$$$$$$$------------Outside productReject controller-------------$$$$$$$$$$$$$$-----------");
		return null;
	}

	public String productRemove(ProductRegister productRegister)
			throws DemoException {
		logger.debug("--------------$$$$$$$$$$$$$$------------inside productRemove controller-------------$$$$$$$$$$$$$$-----------");
		service.productRemove(productRegister);
		logger.debug("--------------$$$$$$$$$$$$$$------------Outside productRemove controller-------------$$$$$$$$$$$$$$-----------");
		return null;
	}

	public List<I0001> autoComplete(List<I0001> auto,
			ProductRegister productRegister) throws DemoException {
		auto = service.autoComplete(auto, productRegister);
		logger.debug("autocomplete" + auto);
		return auto;
	}

	public List<I0025> purchaseDrop(List<I0025> drop,
			PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("inside controller::::::::::");
		drop = service.purchaseDrop(drop, purchaseOrder);
		logger.debug("outside controller::::::::::");
		return drop;
	}

	public ArrayList<String> changeDrop(String s, ArrayList<String> productlist)
			throws DemoException {
		productlist = service.changeDrop(s, productlist);
		return productlist;
	}

	public String changeFirmName(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.changeFirmName(purchaseOrder);
		return "";
	}

	public String changeDrop1(String s) throws DemoException {
		s = service.changeDrop1(s);
		return s;
	}

	public String puruchaseOrder(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.puruchaseOrder(purchaseOrder);
		return null;
	}

	public String savePuruchaseOrder(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.savePuruchaseOrder(purchaseOrder);
		return null;
	}

	public String savePuruchaseOrder1(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.savePuruchaseOrder1(purchaseOrder);
		return null;
	}

	public ArrayList<I0016> purchaseOrdercancel(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("~~~in cntrl~~~~");
		purchaselist = service.purchaseOrdercancel(s, purchaselist,
				purchaseOrder);
		return purchaselist;
	}

	public ArrayList<I0016> purchaseOrdercancel1(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		purchaselist = service.purchaseOrdercancel1(s, purchaselist,
				purchaseOrder);
		logger.debug("3");
		return purchaselist;
	}

	public ArrayList<I0015> invoicePurachaseDrop(ArrayList<I0015> purchaselist)
			throws DemoException {
		purchaselist = service.invoicePurachaseDrop(purchaselist);
		return purchaselist;
	}

	public ArrayList<I0015> invoicePurachaseDrop1(ArrayList<I0015> purchaselist)
			throws DemoException {
		purchaselist = service.invoicePurachaseDrop1(purchaselist);
		return purchaselist;
	}

	public String purchaseClose(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.purchaseClose(purchaseOrder);
		return null;
	}

	public ArrayList<I0015> dateSearchInvoice(Date fromDate, Date todate,
			ArrayList<I0015> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		purchaselist = service.dateSearchInvoice(fromDate, todate,
				purchaselist, purchaseOrder);
		logger.debug("siiiiiiiiii" + purchaselist.size());
		return purchaselist;
	}

	public String cancelConform1(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.cancelConform1(purchaseOrder);
		return null;
	}

	public ArrayList<I0016> purchaseView(PurchaseOrder purchaseOrder,
			ArrayList<I0016> purchaselist) throws DemoException {
		purchaselist = service.purchaseView(purchaseOrder, purchaselist);
		return purchaselist;
	}

	public ArrayList<I0016> invoicePurhcase1(PurchaseOrder purchaseOrder,
			ArrayList<I0016> purchaselist) throws DemoException {
		purchaselist = service.invoicePurhcase1(purchaseOrder, purchaselist);
		return purchaselist;
	}

	public String invoicePurhcase(PurchaseOrder purchaseOrder)
			throws DemoException {

		service.invoicePurhcase(purchaseOrder);

		return null;
	}

	public ArrayList<String> dropAccount(String s, ArrayList<String> ordernumber)
			throws DemoException {
		ordernumber = service.dropAccount(s, ordernumber);
		return ordernumber;
	}

	public ArrayList<I0023> AccountOut(PurchaseOrder purchaseOrder,
			ArrayList<I0023> purchaselist) throws DemoException {
		purchaselist = service.AccountOut(purchaseOrder, purchaselist);
		return purchaselist;
	}

	public String payNow1(PurchaseOrder purchaseOrder) throws DemoException {
		service.payNow1(purchaseOrder);
		return null;
	}

	public ArrayList<I0016> purchaseOrderPayment(Date s, Date s1,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		purchaselist = service.purchaseOrderPayment(s, s1, purchaselist,
				purchaseOrder);
		return purchaselist;

	}

	public ArrayList<I0016> purchaseDeliveryStatus(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		purchaselist = service.purchaseDeliveryStatus(s, purchaselist,
				purchaseOrder);
		return purchaselist;
	}

	public ArrayList<I0016> purchaseDeliveryStatus2(
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		purchaselist = service.purchaseDeliveryStatus2(purchaselist,
				purchaseOrder);
		return purchaselist;
	}

	public String deliveryStatus(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.deliveryStatus(purchaseOrder);
		return null;
	}

	public ArrayList<I0015> purchaseOrderClose(ArrayList<I0015> purchaselist,
			PurchaseOrder purchaseOrder) throws DemoException {
		purchaselist = service.purchaseOrderClose(purchaselist, purchaseOrder);
		return purchaselist;
	}

	public String stockInForm(PurchaseOrder purchaseOrder) throws DemoException {
		service.stockInForm(purchaseOrder);
		return null;
	}

	public String stockInForm1(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.stockInForm1(purchaseOrder);
		return null;
	}

	public String addStock(PurchaseOrder purchaseOrder) throws DemoException {
		return service.addStock(purchaseOrder);
	}

	public String addStock1(PurchaseOrder purchaseOrder) throws DemoException {
		service.addStock1(purchaseOrder);
		return null;
	}

	public List<I0018> salesDrop(List<I0018> batch) throws DemoException {
		batch = service.salesDrop(batch);
		return batch;
	}

	public List<I0019> stockView(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		batch2 = service.stockView(batch2, s, stockView);
		return batch2;
	}

	public List<I0019> stockView2(List<I0019> batch2, StockView stockView)
			throws DemoException {
		batch2 = service.stockView2(batch2, stockView);
		return batch2;
	}

	public List<I0018> stockView1(List<I0018> batch2) throws DemoException {
		batch2 = service.stockView1(batch2);
		return batch2;
	}

	public String addDamage(PurchaseOrder purchaseOrder) throws DemoException {
		service.addDamage(purchaseOrder);
		return null;
	}

	public String addDamage1(PurchaseOrder purchaseOrder) throws DemoException {
		service.addDamage1(purchaseOrder);
		return null;
	}

	public List<I0019> stockdamageView(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		batch2 = service.stockdamageView(batch2, s, stockView);
		return batch2;
	}

	public String salesOrder1(PurchaseOrder purchaseOrder) throws DemoException {
		service.salesOrder1(purchaseOrder);
		return null;
	}

	public List<I0018> salesOrder3(List<I0018> batchProductName3)
			throws DemoException {
		batchProductName3 = service.salesOrder3(batchProductName3);
		return batchProductName3;
	}

	public List<String> productVendor(List<String> batchProductName3)
			throws DemoException {
		batchProductName3 = service.productVendor(batchProductName3);
		return batchProductName3;
	}

	@Override
	public List<String> getnames(List<String> names) throws DemoException {
		names = service.getnames(names);
		return names;

	}

	@Override
	public List<String> getnames1(List<String> names1) throws DemoException {
		names1 = service.getnames1(names1);
		return names1;

	}

	public List<String> productVendor1(List<String> batchProductName3, String s)
			throws DemoException {
		batchProductName3 = service.productVendor1(batchProductName3, s);
		return batchProductName3;
	}

	public String salesOrder4(PurchaseOrder purchaseOrder) throws DemoException {
		service.salesOrder4(purchaseOrder);
		return null;
	}

	public String salesOrder5(PurchaseOrder purchaseOrder) throws DemoException {
		service.salesOrder5(purchaseOrder);
		return null;
	}

	public List<I0019> stockoutForm(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		batch2 = service.stockoutForm(batch2, s, stockView);
		return batch2;
	}

	public List<I0019> stockInForm(List<I0019> batch2, String s)
			throws DemoException {
		batch2 = service.stockInForm(batch2, s);
		return batch2;
	}

	public String salesOrdercancelForm(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesOrdercancelForm(purchaseOrder);
		return null;
	}

	public String salesOrderViewproduct(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesOrderViewproduct(purchaseOrder);
		return null;
	}

	public String salesOrdercancelForm4(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesOrdercancelForm4(purchaseOrder);
		return null;
	}

	public String updateSales1(PurchaseOrder purchaseOrder)
			throws DemoException {
		return service.updateSales1(purchaseOrder);
	}

	public String salesOrdercancel(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesOrdercancel(purchaseOrder);
		return null;
	}

	public String salesOrdercancelFormsub1(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesOrdercancelFormsub1(purchaseOrder);
		return null;
	}

	public String salesOrdercancelFormsub(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesOrdercancelFormsub(purchaseOrder);
		return null;
	}

	public String salesDrop(PurchaseOrder purchaseOrder) throws DemoException {
		service.salesDrop(purchaseOrder);
		return null;
	}

	public String salesDrop1(PurchaseOrder purchaseOrder) throws DemoException {
		service.salesDrop1(purchaseOrder);
		return null;
	}

	public String changeDrop(PurchaseOrder purchaseOrder) throws DemoException {
		service.changeDrop(purchaseOrder);
		return null;
	}

	public String changeDrop1(PurchaseOrder purchaseOrder) throws DemoException {
		service.changeDrop1(purchaseOrder);
		return null;
	}

	public String salesOrdercancelForm1(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesOrdercancelForm1(purchaseOrder);
		return null;
	}

	public String salesOrdercancelForm3(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesOrdercancelForm3(purchaseOrder);
		return null;
	}

	public String salesOrdercancelForm2(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesOrdercancelForm2(purchaseOrder);
		return null;
	}

	public String salesOrdercancelForm5(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesOrdercancelForm5(purchaseOrder);
		return null;
	}

	public String salesReturnForm2(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesReturnForm2(purchaseOrder);
		return null;
	}

	public String salesReturnForm3(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesReturnForm3(purchaseOrder);
		return null;
	}

	public String salesReturnForm(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesReturnForm(purchaseOrder);
		return null;
	}

	public String salesReturnForm5(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesReturnForm5(purchaseOrder);
		return null;
	}

	public String salesdelivery(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesdelivery(purchaseOrder);
		return null;
	}

	public List<I0021> salesPayment1(List<I0021> salesreferenumber)
			throws DemoException {
		salesreferenumber = service.salesPayment1(salesreferenumber);
		return salesreferenumber;
	}

	public String salesPayment2(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesPayment2(purchaseOrder);
		return null;
	}

	public String salesView(PurchaseOrder purchaseOrder) throws DemoException {
		service.salesView(purchaseOrder);
		return null;
	}

	public String invoiceSales(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.invoiceSales(purchaseOrder);
		return null;
	}

	public ArrayList<I0021> invoiceSales(PurchaseOrder purchaseOrder,
			ArrayList<I0021> sales) throws DemoException {
		sales = service.invoiceSales(purchaseOrder, sales);
		return sales;
	}

	public String invoiceSales1(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.invoiceSales1(purchaseOrder);
		return null;
	}

	public String accountin(PurchaseOrder purchaseOrder) throws DemoException {
		service.accountin(purchaseOrder);
		return null;
	}

	public ArrayList<I0021> accountin(ArrayList<I0021> drop)
			throws DemoException {
		drop = service.accountin(drop);
		return drop;
	}

	public String payNowAccount(PurchaseOrder purchaseOrder)
			throws DemoException {
		return service.payNowAccount(purchaseOrder);
	}

	@Override
	public ArrayList<I0001> dropdownproduct(String productname,
			ArrayList<I0001> productDrop) throws DemoException {
		logger.debug("inside controller dropdown::::::::::");
		productDrop = service.dropdownproduct(productname, productDrop);
		return productDrop;
	}

	@Override
	public ArrayList<I0022> reportInvoice(ArrayList<I0022> invoiceList,
			Report1 report1) throws DemoException {
		invoiceList = service.reportInvoice(invoiceList, report1);
		logger.debug("out controller::::::::::");
		return invoiceList;
	}

	public ArrayList<I0015> reportPurchase(ArrayList<I0015> purchaseList,
			Report1 report1) throws DemoException {
		purchaseList = service.reportPurchase(purchaseList, report1);
		logger.debug("out controller::::::::::");
		return purchaseList;
	}

	public ArrayList<I0021> reportSales(ArrayList<I0021> salesList1,
			Report1 report1) throws DemoException {
		salesList1 = service.reportSales(salesList1, report1);
		logger.debug("out controller::::::::::");
		return salesList1;
	}

	@Override
	public ArrayList<I0025> vendorView(Vendor vendor) throws DemoException {

		return service.vendorView(vendor);
	}

	@Override
	public ArrayList<I0025> vendorView1(Vendor vendor, String golbalnamesearch)
			throws DemoException {

		return service.vendorView1(vendor, golbalnamesearch);
	}

	@Override
	public String salesDelete() throws DemoException {
		logger.debug("==service===");
		service.salesDelete();
		return null;
	}

	public List<I0018> dropDownbatch(List<I0018> batch) throws DemoException {
		batch = service.dropDownbatch(batch);
		return batch;
	}

	public ArrayList<I0032> salesOrder(ArrayList<I0032> buyername, Buyer b)
			throws DemoException {
		return service.salesOrder(buyername, b);
	}

	@Override
	public String purchaseProductEdit(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.purchaseProductEdit(purchaseOrder);
		return null;
	}

	public int getPurchasestatus() throws DemoException {
		return service.getPurchasestatus();
	}

	public int getsalesstatus() throws DemoException {
		return service.getsalesstatus();
	}

	public int getSalesOrderStatus() throws DemoException {
		return service.getSalesOrderStatus();
	}

	public int getPurchaseorderStatus() throws DemoException {
		return service.getPurchaseorderStatus();
	}

	@Override
	public ArrayList<I0032> salesCustomer(ArrayList<I0032> buyername,
			PurchaseOrder purchaseOrder) throws DemoException {
		return service.salesCustomer(buyername, purchaseOrder);
	}

	@Override
	public ArrayList<I0032> customerNameChange(String s) throws DemoException {

		return service.customerNameChange(s);
	}

	public String saveBuyer(Buyer b) throws DemoException {
		return service.saveBuyer(b);
	}

	public List<I0028> getCountry() throws DemoException {
		return service.getCountry();
	}

	public List<I0032> getBuyerInfo(String phoneNumber) throws DemoException {
		return service.getBuyerInfo(phoneNumber);
	}

	public List<I0032> getBuyerInfo1(String phoneNumber,String clientID,String userID) throws DemoException {
		return service.getBuyerInfo1(phoneNumber,clientID,userID);
	}

	public List<I0032> getBuyerUpdate(Buyer b) throws DemoException {
		return service.getBuyerUpdate(b);
	}

	public List<I0032> getPhone() throws DemoException {
		return service.getPhone();
	}

	public String buyerDelete(Buyer b) throws DemoException {
		return service.buyerDelete(b);
	}

	@Override
	public List<StockView> getStockInfo() throws DemoException {

		return service.getStockInfo();
	}

	/* @Value( "${filep}" ) */
	public String filepath = "D://doc//";

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public String filePath(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("inside controller");
		logger.debug("file path---------->" + filepath);
		service.filePath(purchaseOrder);
		return filepath;
	}

	@Override
	public String fileSave(PurchaseOrder purchaseOrder) throws DemoException {
		service.fileSave(purchaseOrder);
		return null;
	}

	@Override
	public String approvalView(Approval approval) throws DemoException {
		service.approvalView(approval);
		return null;
	}

	@Override
	public String approvalView1(Approval approval) throws DemoException {
		service.approvalView1(approval);
		return null;
	}

	@Override
	public String approvalView2(Approval approval) throws DemoException {
		service.approvalView2(approval);
		return null;
	}

	@Override
	public String approvalView3(Approval approval) throws DemoException {
		service.approvalView3(approval);
		return null;
	}

	@Override
	public String approvalView4(Approval approval) throws DemoException {
		service.approvalView4(approval);
		return null;
	}

	/* udhaya 30.12.2014 */
	public String categoryType(CategoryRegistration categoryreg)
			throws DemoException {
		logger.debug("inside controller-->>>");
		return service.categoryType(categoryreg);
	}

	/* udhaya 31.12.2014 */
	public List<String> categorylist(List<String> categorytype)
			throws DemoException {
		logger.debug("inside controller---->>");
		return service.categorylist(categorytype);
	}

	public String category(ProductRegister productRegister)
			throws DemoException {
		logger.debug("inside controller---->>");
		return service.category(productRegister);
	}

	/* udhaya 2.1.2015 */
	public int getPurchaseapprovalstatus() throws DemoException {
		logger.debug("inside controller--------->>");
		return service.getPurchaseapprovalstatus();
	}

	public String approvalstatus(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("inside controller--------->>");
		return service.approvalstatus(purchaseOrder);
	}

	/* udhaya 5.1.2015 */
	public ArrayList<I0016> approvalStage(String orderNumber,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("inside controller--------->>");
		return service.approvalStage(orderNumber, purchaselist, purchaseOrder);
	}

	public String approved(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("inside controller--------->>");
		return service.approved(purchaseOrder);
	}

	public String quickSaleView(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("Inside Controller------------->>>");
		return service.quickSaleView(purchaseOrder);
	}

	/* udhaya 7.1 2015 */
	public List<I0021> view(List<I0021> saleslist, PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("Inside Controller------------->>>");
		return service.view(saleslist, purchaseOrder);
	}

	public List<PurchaseOrder> getQuicksaleEdit(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("Inside Controller------------->>>");
		return service.getQuicksaleEdit(purchaseOrder);
	}

	/* udhaya 8.1 2015 */
	public List<I0021> customerNameChange(List<I0021> cusname)
			throws DemoException {
		logger.debug("Inside Controller------------->>>");
		return service.customerNameChange(cusname);
	}

	/* jeni */
	public List<I0032> getBuyercustInfo(Buyer b) throws DemoException {
		logger.debug("Inside Controller");
		return service.getBuyercustInfo(b);
	}

	@Override
	public List<I0032> getBuyercustInfo(Buyer b, String golbalnamesearch)
			throws DemoException {

		return service.getBuyercustInfo(b, golbalnamesearch);
	}

	public String getPurchaseVendorView(PurchaseOrder purchaseVendor)
			throws DemoException {
		service.getPurchaseVendorView(purchaseVendor);
		return null;
	}

	/* kasturi */
	public ArrayList<I0016> purchaseOrderPayment1(
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		service.purchaseOrderPayment1(purchaselist, purchaseOrder);
		return null;
	}

	public String remSalesDeliver(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.remSalesDeliver(purchaseOrder);
		return null;
	}

	public String remSalesDelivery(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.remSalesDelivery(purchaseOrder);
		return null;
	}

	public ArrayList<I0016> remPurchaseDeliver(ArrayList<I0016> purchaselist,
			PurchaseOrder purchaseOrder) throws DemoException {
		service.remPurchaseDeliver(purchaselist, purchaseOrder);
		return null;
	}

	public String remPurchasePaymentStatus(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.remPurchasePaymentStatus(purchaseOrder);
		return null;
	}

	/* jeni */
	public String salesPaypend(PurchaseOrder purchaseOrder)
			throws DemoException {

		service.salesPaypend(purchaseOrder);
		return null;

	}

	public int getSalesPayStatus() throws DemoException {

		return service.getSalesPayStatus();

	}

	/* ranjini */
	public String getpurchaseInfo(PurchaseOrder p) throws DemoException {
		return service.getpurchaseInfo(p);
	}

	public List<String> getProductName() throws DemoException {
		return service.getProductName();
	}

	public List<I0019> getBarCodeData(String productName) throws DemoException {
		return service.getBarCodeData(productName);
	}

	public String saveSales(PurchaseOrder purchaseOrder) throws DemoException {
		return service.saveSales(purchaseOrder);
	}

	@Override
	public String qucikSalesConform(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.qucikSalesConform(purchaseOrder);
		return null;
	}

	@Override
	public String qucikSalesConform2(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.qucikSalesConform2(purchaseOrder);
		return null;
	}

	@Override
	public String qucikSalesConform1(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.qucikSalesConform1(purchaseOrder);
		return null;
	}

	public List<PurchaseOrder> getpurchaseDataFVendor(String vendorName)
			throws DemoException {
		return service.getpurchaseDataFVendor(vendorName);
	}

	/* Sivaranjini 12/1/2015 */

	public int getsalesQuantityOfMonth(Date fDate, Date tDate)
			throws DemoException {
		return service.getsalesQuantityOfMonth(fDate, tDate);
	}

	// sivaranjini 13_1_15

	public float getSalesAmount(Date fDate, Date tDate) throws DemoException {
		return service.getSalesAmount(fDate, tDate);
	}

	public float getpurchaseAmount(Date fDate, Date tDate) throws DemoException {
		return service.getpurchaseAmount(fDate, tDate);
	}

	public int getStockinQuantity(Date fDate, Date tDate) throws DemoException {
		return service.getStockinQuantity(fDate, tDate);
	}

	public int getStockOutQuantity(Date fDate, Date tDate) throws DemoException {
		return service.getStockOutQuantity(fDate, tDate);
	}

	// sivaranjini 14_1_15

	public int getsalesInvoice(Date fDate, Date tDate) throws DemoException {
		return service.getsalesInvoice(fDate, tDate);
	}

	public int getpurchaseInvoice(Date fDate, Date tDate) throws DemoException {
		return service.getpurchaseInvoice(fDate, tDate);
	}

	public List<String> designationInfo(List<String> designate)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return service.designationInfo(designate);
	}

	public List<Qualification> qualificationInfo(List<Qualification> qualificate)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return service.qualificationInfo(qualificate);
	}

	public String employee(EmployeeDetail employee) throws DemoException {
		logger.debug("inside controller------->>>");
		return service.employee(employee);
	}

	public String employeeIdInfo(EmployeeDetail employee) throws DemoException {
		logger.debug("inside controller------->>>");
		return service.employeeIdInfo(employee);
	}

	public String employeeNameInfo(EmployeeDetail employee)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return service.employeeNameInfo(employee);
	}

	@Override
	public List<Employee> employeeNameInfo(EmployeeDetail employee,
			String golbalnamesearch) throws DemoException {

		return service.employeeNameInfo(employee, golbalnamesearch);
	}

	public List<String> employeeId(List<String> emploeid) throws DemoException {
		logger.debug("inside controller------->>>");
		return service.employeeId(emploeid);
	}

	public List<String> employeeName(List<String> emploename)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return service.employeeName(emploename);
	}

	public List<EmployeeDetail> getEmployeeDetail(EmployeeDetail employee)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return service.getEmployeeDetail(employee);
	}

	public List<EmployeeDetail> getEmployeeDetailEdit(EmployeeDetail employee)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return service.getEmployeeDetailEdit(employee);
	}

	public String employeeIdSearch(EmployeeDetail empid) throws DemoException {
		return service.employeeIdSearch(empid);
	}

	/* siva20_2_15 */
	public int stockInfo() throws DemoException {
		return service.stockInfo();
	}

	public List<StockView> getStockInInfo() throws DemoException {
		return service.getStockInInfo();
	}

	/* siva 23_2_15 */

	public String saveconfirm(ATransaction save) throws DemoException {
		logger.debug("inside controller------->>>");
		return service.saveconfirm(save);
	}

	public String transactionView(ATransaction search) throws DemoException {
		logger.debug("inside controller------->>>");
		return service.transactionView(search);
	}

	public List<ATransaction> getviewForm(ATransaction view)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return service.getviewForm(view);
	}

	public List<ATransaction> getEditForm(ATransaction edit)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return service.getEditForm(edit);
	}

	public List<ATransaction> getStatusChange(ATransaction change)
			throws DemoException {
		logger.debug("---------inside controller---------");
		return service.getStatusChange(change);
	}

	public List<SalesIncomeMB> getAccountReceivableSales(Date fDate, Date tDate)
			throws DemoException {
		return service.getAccountReceivableSales(fDate, tDate);
	}

	public List<CashAssetMB> getCashAsset(Date fDate, Date tDate)
			throws DemoException {
		return service.getCashAsset(fDate, tDate);
	}

	public List<PurchaseExpenseMB> getPurchaseExpences(Date fDate, Date tDate)
			throws DemoException {
		return service.getPurchaseExpences(fDate, tDate);
	}

	public List<PayrollLiablityMB> getPayrollLiability(Date fDate, Date tDate)
			throws DemoException {
		return service.getPayrollLiability(fDate, tDate);
	}

	@Override
	public List<Employee> empInfo(List<Employee> empinfo) throws DemoException {

		return service.empInfo(empinfo);
	}

	@Override
	public List<Employee> nameInfo(List<Employee> nameinfo)
			throws DemoException {

		return service.nameInfo(nameinfo);
	}

	@Override
	public String employeeDetails(EmployeeDetail employee) throws DemoException {

		return service.employeeDetails(employee);
	}

	public List<Month> monthInfo(List<Month> monthinfo) throws DemoException {
		logger.debug("inside controller------->>>");
		return service.monthInfo(monthinfo);
	}

	public List<Year> yearInfo(List<Year> yearinfo) throws DemoException {
		logger.debug("inside controller------->>>");
		return service.yearInfo(yearinfo);
	}

	public ArrayList<String> changeEvent(String s, ArrayList<String> list1)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return service.changeEvent(s, list1);
	}

	public ArrayList<String> changeEvent1(String s, ArrayList<String> list2)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return service.changeEvent1(s, list2);
	}

	public List<Employee> payroll(List<Employee> list3, EmployeePayroll save)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return service.payroll(list3, save);
	}

	public List<Employee> payrollemp(List<Employee> list3, EmployeePayroll save)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return service.payrollemp(list3, save);
	}

	public String confirm(EmployeePayroll save) throws DemoException {
		logger.debug("inside controller------->>>");
		return service.confirm(save);
	}

	public ArrayList<String> changeEvent2(String s, ArrayList<String> list1)
			throws DemoException {

		return service.changeEvent2(s, list1);
		// return null;
	}

	public String payroll1(EmployeePayroll pay) throws DemoException {

		return service.payroll1(pay);
	}

	public String search1(EmployeePayroll pay) throws DemoException {

		return service.search1(pay);
	}

	public String search2(EmployeePayroll pay) throws DemoException {

		return service.search2(pay);
	}

	public List<EmployeePayroll> viewPayrollz(EmployeePayroll view)
			throws DemoException {
		return service.viewPayrollz(view);
	}

	public List<EmployeePayroll> editPayroll(EmployeePayroll view)
			throws DemoException {
		return service.editPayroll(view);
	}

	public List<EmployeePayroll> viewPayroll(EmployeePayroll view)
			throws DemoException {
		return service.viewPayroll(view);
	}

	public ArrayList<String> changezEvent(String s, ArrayList<String> list1)
			throws DemoException {
		return service.changezEvent(s, list1);
	}

	public ArrayList<String> changeEvent3(String s, ArrayList<String> list2)
			throws DemoException {
		return service.changeEvent3(s, list2);
	}

	public String payroll(EmployeePayroll pay) throws DemoException {
		return service.payroll(pay);
	}

	public List<ProductRegister> getProductInInfo(Date startDate, Date endDate)
			throws DemoException {
		logger.debug("inside controller");
		return service.getProductInInfo(startDate, endDate);
	}

	public int productInfo(Date startDate, Date endDate) throws DemoException {
		logger.debug("inside controller");
		return service.productInfo(startDate, endDate);

	}

	public int productInfo1() throws DemoException {
		return service.productInfo1();
	}

	public int productInfo2() throws DemoException {
		return service.productInfo2();
	}

	public List<I0001> dropdownproduct1() throws DemoException {
		return service.dropdownproduct1();
	}

	public List<String> categorylist1(List<String> categorytype)
			throws DemoException {
		return service.categorylist1(categorytype);
	}

	public String purchaseReturnValuechangeDrop1(String s,
			PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("inside service");
		service.purchaseReturnValuechangeDrop1(s, purchaseOrder);
		return "";
	}

	public String purchaseRetViewForm(PurchaseOrder pur) throws DemoException {
		service.purchaseRetViewForm(pur);
		return null;
	}

	public String purOrderViewproduct(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.purOrderViewproduct(purchaseOrder);
		return null;
	}

	public String purchReturnSubmit(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.purchReturnSubmit(purchaseOrder);
		return null;

	}

	/* Arun */

	public ArrayList<I0032> catogerycustomer(ArrayList<I0032> buyername,
			PurchaseOrder purchaseOrder) throws DemoException {
		service.catogerycustomer(buyername, purchaseOrder);
		return null;
	}

	public List<String> customername(Commission commission)
			throws DemoException {
		return service.customername(commission);
	}

	public String commissionview(Commission commission) throws DemoException {
		service.commissionview(commission);
		return null;
	}

	public ArrayList<I0032> customerNameChange1(String s) throws DemoException {
		return service.customerNameChange1(s);
	}

	public String viewPurchaseReturnDetail(PurchaseOrder purchaseOrder)
			throws DemoException {
		return service.viewPurchaseReturnDetail(purchaseOrder);
	}

	public String viewPurchaseReturn(PurchaseOrder purchaseOrder)
			throws DemoException {
		return service.viewPurchaseReturn(purchaseOrder);
	}

	public String purchaseReturnInsert(PurchaseOrder purchaseOrder)
			throws DemoException {
		return service.purchaseReturnInsert(purchaseOrder);
	}

	public List<Commission> viewCommission(Commission c) throws DemoException {
		return service.viewCommission(c);
	}

	public String commisionUpdate(Commission c) throws DemoException {
		return service.commisionUpdate(c);
	}

	public String purchaseInfocollect(PurchaseOrder purchaseOrder)
			throws DemoException {
		return service.purchaseInfocollect(purchaseOrder);
	}

	public String findCashBook(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.findCashBook(purchaseOrder);
		return null;
	}

	// 04/09/15 Sree
	public List<SalesIncomeMB> getAccountReceivableSales1(Date fDate, Date tDate)
			throws DemoException {
		return service.getAccountReceivableSales1(fDate, tDate);
	}

	public List<PurchaseExpenseMB> getPurchaseExpences1(Date fDate, Date tDate)
			throws DemoException {
		return service.getPurchaseExpences1(fDate, tDate);
	}

	public List<I0032> getFreeLancerInfo(String freeLancerName)
			throws DemoException {
		return service.getFreeLancerInfo(freeLancerName);
	}

	/* siva 10-4-15 */
	public List<String> getSONfordispatch() throws DemoException {
		return service.getSONfordispatch();
	}

	public List<CommissionFormMB> getDispatchData(String soNumber,
			PurchaseOrder dom) throws DemoException {
		return service.getDispatchData(soNumber, dom);
	}

	public String getCommisionAmount(String freelancerName)
			throws DemoException {
		return service.getCommisionAmount(freelancerName);
	}

	public String salesOrder4Normal(PurchaseOrder purchaseOrder)
			throws DemoException {
		return service.salesOrder4Normal(purchaseOrder);
	}

	public List<ProductRegister> nonsales() throws DemoException {
		return service.nonsales();
	}

	public int outofStock() throws DemoException {
		return service.outofStock();
	}

	public List<ProductRegister> outofStock1() throws DemoException {
		return service.outofStock1();
	}

	public String partialnormalreturn(PurchaseOrder p, int qty)
			throws DemoException {
		return service.partialnormalreturn(p, qty);
	}

	public String partialdamagereturn(PurchaseOrder p, int qty)
			throws DemoException {
		return service.partialdamagereturn(p, qty);
	}

	public int salesreturncount() throws DemoException {
		logger.debug("==in===");
		return service.salesreturncount();
	}

	public String viewAccountReceivable(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("service");
		service.viewAccountReceivable(purchaseOrder);
		return null;
	}

	public String viewAccountPayable(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("service");
		service.viewAccountPayable(purchaseOrder);
		return null;
	}

	public List<Sales> getSalesReturnView() throws DemoException {
		return service.getSalesReturnView();
	}

	public String designation(EmployeeDetail emp) throws DemoException {
		return service.designation(emp);
	}

	public String updateDispatch(String str1, String str2, String str3)
			throws DemoException {
		return service.updateDispatch(str1, str2, str3);
	}

	public String salesSave(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("customer name----------->>MMNN>>>"
				+ purchaseOrder.getCustomername());
		service.salesSave(purchaseOrder);
		return null;
	}

	public String salesconfirm(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesconfirm(purchaseOrder);
		return null;
	}

	public String purchasePrice(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("controller ---->>> ");
		return service.purchasePrice(purchaseOrder);
	}

	public String purchase(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("controller ---->>> ");
		return service.purchase(purchaseOrder);
	}

	public String purchase1(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("controller ---->>> ");
		return service.purchase1(purchaseOrder);
	}

	public String purchaseorderClose(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("controller ---->>> ");
		return service.purchaseorderClose(purchaseOrder);
	}

	public List<I0023> paymentamount(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("controller ---->>> ");
		return service.paymentamount(purchaseOrder);
	}

	public List<I0023> paymentamountsale(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("controller ---->>> ");
		return service.paymentamountsale(purchaseOrder);
	}

	public String returnQuantity(PurchaseOrder purchaseOrder)
			throws DemoException {
		return service.returnQuantity(purchaseOrder);
	}

	public String salesReturnSubmit(PurchaseOrder purchaseOrder)
			throws DemoException {
		return service.salesReturnSubmit(purchaseOrder);
	}

	public String viewSalesReturnDetail(PurchaseOrder purchaseOrder)
			throws DemoException {
		return service.viewSalesReturnDetail(purchaseOrder);
	}

	public String viewSalesReturn(PurchaseOrder purchaseOrder)
			throws DemoException {
		return service.viewSalesReturn(purchaseOrder);

	}

	@Override
	public String searchCity(Vendor vendor) throws DemoException {
		service.searchCity(vendor);
		return null;
	}

	public ArrayList<String> changeList(String changeEvent,
			ArrayList<String> productList, PurchaseOrder purchaseOrder)
			throws DemoException {
		productList = service.changeList(changeEvent, productList,
				purchaseOrder);
		return productList;
	}

	@Override
	public String addStockIn(PurchaseOrder purchaseOrder) throws DemoException {
		return service.addStockIn(purchaseOrder);
	}

	@Override
	public ArrayList<String> getRollList(String productName,
			ArrayList<String> rollList) throws DemoException {
		rollList = service.getRollList(productName, rollList);
		return rollList;
	}

	@Override
	public String getRollQuantity(PurchaseOrder purchaseOrder)
			throws DemoException {

		return service.getRollQuantity(purchaseOrder);
	}

	@Override
	public String qucikSalesRoll(PurchaseOrder purchaseOrder)
			throws DemoException {

		service.qucikSalesRoll(purchaseOrder);
		return null;
	}

	@Override
	public String updateRollSales(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.updateRollSales(purchaseOrder);
		return null;
	}

	@Override
	public String salesRollconfirm(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesRollconfirm(purchaseOrder);
		return null;
	}

	@Override
	public String updateRollQuantity(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.updateRollQuantity(purchaseOrder);
		return null;
	}

	@Override
	public String salesOrderdelete(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesOrderdelete(purchaseOrder);
		return null;
	}

	@Override
	public String getProductQuntity(PurchaseOrder purchaseOrder)
			throws DemoException {

		return service.getProductQuntity(purchaseOrder);
	}

	@Override
	public List<I0032> customerNameChange11(String s1) throws DemoException {

		return service.customerNameChange11(s1);
	}

	@Override
	public List<Employee> freelancerNameInfo(List<Employee> name)
			throws DemoException {

		return service.freenamelistser(name);
	}

	@Override
	public ArrayList<String> getAddRollList(String productName,
			ArrayList<String> rollList) throws DemoException {
		rollList = service.getAddRollList(productName, rollList);
		return rollList;
	}

	@Override
	public String rollDamage(PurchaseOrder purchaseOrder) throws DemoException {

		service.rollDamage(purchaseOrder);
		return "";

	}

	@Override
	public String quickSaleDropdown(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.quickSaleDropdown(purchaseOrder);
		return null;
	}

	@Override
	public String quicksaleReturnSubmit(PurchaseOrder purchaseOrder)
			throws DemoException {

		return service.quicksaleReturnSubmit(purchaseOrder);
	}

	@Override
	public String quickViewSalesReturn(PurchaseOrder purchaseOrder)
			throws DemoException {

		return service.quickViewSalesReturn(purchaseOrder);
	}

	@Override
	public List<I0019> stockView3(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		batch2 = service.stockView3(batch2, s, stockView);
		return batch2;
	}

	@Override
	public List<String> productVendor1(List<String> batchProductName4)
			throws DemoException {
		batchProductName4 = service.productVendor1(batchProductName4);
		return batchProductName4;

	}

	public String getbarcodeInfo(PurchaseOrder purchaseOrder)
			throws DemoException {
		return service.getbarcodeInfo(purchaseOrder);
	}

	public String changeUserName(String newuser, String invusername)
			throws DemoException {
		return service.changeUserName(newuser, invusername);
	}

	public String changeUserPassword(String newpasswrd, String invpassword)
			throws DemoException {
		return service.changeUserPassword(newpasswrd, invpassword);
	}

	public String openingStockInsert(PurchaseOrder opngstock)
			throws DemoException {
		return service.openingStockInsert(opngstock);
	}

	@Override
	public String getPurchaseQty(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.getPurchaseQty(purchaseOrder);
		return null;
	}

	@Override
	public String getpurchaseView(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.getpurchaseView(purchaseOrder);
		return null;
	}

	@Override
	public String getunitprice(String productName, String unitprice)
			throws DemoException {
		return service.getunitprice(productName, unitprice);

	}

	public String salesRecordView(PurchaseOrder purchaseOrder)
			throws DemoException {
		service.salesRecordView(purchaseOrder);
		return null;
	}

	@Override
	public ArrayList<String> productnonsales() throws DemoException {
		return service.productnonsales();

	}

	@Override
	public ArrayList<String> getRollQuanList(String productName,
			ArrayList<String> rollList) throws DemoException {
		rollList = service.getRollQuanList(productName, rollList);
		return rollList;
	}

	@Override
	public List<I0032> getcitiesinfo(String city) throws DemoException {
		// TODO Auto-generated method stub
		return service.getcitiesinfo(city);
	}

	public String designRegSubmit(ProductRegister productRegister)
			throws DemoException, ParseException {
		service.designRegSubmit(productRegister);
		return null;
	}

	public String designView(ProductRegister productRegister)
			throws DemoException {
		return service.designView(productRegister);
	}

	public String designViews(ProductRegister productRegister)
			throws DemoException {
		return service.designViews(productRegister);
	}

	public String designViewDelete(ProductRegister productRegister)
			throws DemoException {
		return service.designViewDelete(productRegister);
	}

	public String designValidate(ProductRegister productRegister)
			throws DemoException {
		return service.designValidate(productRegister);
	}

	public String saveImages(ProductRegister productRegister)
			throws DemoException {
		logger.debug("-->>cntrlr");
		return service.saveImages(productRegister);
	}

	@Override
	public String databaseValidate(LoginAccess loginaccess) {

		return service.databaseValidate(loginaccess);
	}

	@Override
	public String checkErrorcode(LoginAccess loginaccess) {

		return service.checkErrorcode(loginaccess);
	}

	@Override
	public String insertCreateUser(UserCreateDataBean userCreateDataBean) {
		return service.insertCreateUser(userCreateDataBean);
	}

	@Override
	public List<UserCreateDataBean> insideUserEdit(
			UserCreateDataBean userCreateDataBean) {
		return service.insideUserEdit(userCreateDataBean);
	}

	@Override
	public String insideUpdate(UserCreateDataBean userCreateDataBean) {
		return service.insideUpdate(userCreateDataBean);
	}

	@Override
	public String userInsert(UserCreateDataBean userCreateDataBean)
			throws DemoException {
		return service.userInsert(userCreateDataBean);
	}

	@Override
	public String getVendorVerification(String name) {

		return service.getVendorVerification(name);
	}

	@Override
	public String userCheck(UserCreateDataBean userCreateDataBean)
			throws DemoException {
		return service.userCheck(userCreateDataBean);
	}

	@Override
	public String getCustomerVerification(String name) {
		return service.getCustomerVerification(name);
	}

	@Override
	public String retirveUser(UserCreateDataBean userCreateDataBean) {
		return service.retirveUser(userCreateDataBean);
	}

	@Override
	public List<String> getCustomerInfo(Buyer b) {
		return service.getCustomerInfo(b);
	}

	@Override
	public List<String> getproductListInfo(ProductRegister productRegister) {
		return service.getproductListInfo(productRegister);
	}

	@Override
	public String getProductVerification(String name) {

		return service.getProductVerification(name);
	}

	@Override
	public ArrayList<Report1> getVendorNameList() {
		return service.getVendorNameList();
	}

	@Override
	public String findGlobalSearch(String golbalnamesearch) {

		return service.findGlobalSearch(golbalnamesearch);
	}

	@Override
	public ArrayList<VendorReport> insidevendornamesearch(
			VendorReport vendorReport) {

		return service.insidevendornamesearch(vendorReport);
	}

	@Override
	public ArrayList<VendorReport> insideallvendornamesearch(
			VendorReport vendorReport) {

		return service.insideallvendornamesearch(vendorReport);
	}

	@Override
	public List<I0025> getphonenumberglobalsearch(String golbalnamesearch)
			throws DemoException {

		return service.getphonenumberglobalsearch(golbalnamesearch);
	}

	@Override
	public List<I0032> getcustphnosearch(String golbalnamesearch)
			throws DemoException {

		return service.getcustphnosearch(golbalnamesearch);
	}

	@Override
	public List<Employee> getempphnosearch(String golbalnamesearch)
			throws DemoException {

		return service.getempphnosearch(golbalnamesearch);
	}

	@Override
	public ArrayList<I0025> getgmailsearch(String golbalnamesearch)
			throws DemoException {

		return service.getgmailsearch(golbalnamesearch);
	}

	@Override
	public List<I0032> getcustemailsearch(String golbalnamesearch)
			throws DemoException {
		return service.getcustemailsearch(golbalnamesearch);
	}

	@Override
	public List<Employee> getempemailsearch(String golbalnamesearch)
			throws DemoException {
		return service.getempemailsearch(golbalnamesearch);
	}

	@Override
	public List<SubProduct> submenus(int product_ID, String productCode) {
		return service.submenus(product_ID,productCode);
	}
	
	public String saveClient(ClientDataBean clientDataBean)throws DemoException{
		return service.saveClient(clientDataBean);
	}
	
	public String clientNoCheck(String phno)throws DemoException{
		return service.clientNoCheck(phno);
	}
	
	public String getclientDetails(ClientDataBean clientDataBean){
		return service.getclientDetails(clientDataBean);
	}
	
	public String getclientDetailsView(ClientDataBean clientDataBean){
		return service.getclientDetailsView(clientDataBean);
	}
	
	public String clientUpdate(ClientDataBean clientDataBean){
		return service.clientUpdate(clientDataBean);
	}
	
	public String clientDelete(ClientDataBean clientDataBean){
		return service.clientDelete(clientDataBean);
	}
	
	public String userView(UserCreateDataBean userCreateDataBean){
		return service.userView(userCreateDataBean);
	}
	
	public String getUserdetails(UserCreateDataBean userCreateDataBean){
		return service.getUserdetails(userCreateDataBean);
	}
	
	public String userUpdate(UserCreateDataBean userCreateDataBean){
		return service.userUpdate(userCreateDataBean);
	}
	
	public String userDelete(UserCreateDataBean userCreateDataBean){
		return service.userDelete(userCreateDataBean);
	}

	@Override
	public String getdashboardCount(LoginAccess loginaccess) {
		return service.getdashboardCount(loginaccess);
	}

	@Override
	public List<String> getAccountType(String clientID) {
		return service.getAccountType(clientID);
	}

	@Override
	public String addAccount(ATransaction payment) {
		return service.addAccount(payment);
	}

	public List<String> getstatelist(String country) {
		return service.getstatelist(country);
	}

	@Override
	public String getempID() {
		return service.getempID();
	}

	public List<EmployeePayroll> getEmployeePayrollDetails(EmployeePayroll employeePayroll){
		return service.getEmployeePayrollDetails(employeePayroll);
	}

	public String getdocUpload(EmployeeDetail employeeDetail){
		return service.getdocUpload(employeeDetail);
	}
	
	public void updatequalify(String qualID){
		service.updatequalify(qualID);
	}

	public void updateExpernce(String expID){
		service.updateExpernce(expID);
	}

	public List<String> getdepartmentname() {
		return service.getdepartmentname();
	}

	public String getProductcodeVerification(String name) {
		return service.getProductcodeVerification(name);
	}

	public String saveAcountDeposit(String clientID, ATransaction payment){
		return service.saveAcountDeposit(clientID,payment);
	}

	public List<String> getAccountDepsit(String clientID, ATransaction atrans){
		return service.getAccountDepsit(clientID,atrans);
	}

	public String saveTransPayment(String clientID, ATransaction atransaction){
		return service.saveTransPayment(clientID,atransaction);
	}

	public String getDailyreport(Report1 report1) {
		return service.getDailyreport(report1);
	}

	public List<String> getmenus() {
		return service.getmenus();
	}

	public String insertdepartment(String department) {
		return service.insertdepartment(department);
	}

	public String vendorApproval(ArrayList<VendorViewFormMB> ven1,String approvalStatus) {
		return service.vendorApproval(ven1,approvalStatus);
	}

	public String customerApproval(ArrayList<BuyersViewMB> catbuy,String approvalStatus) {
		return service.customerApproval(catbuy,approvalStatus);
	}

	public List<ProductViewMB> getcategoryList(String approvalStatus) {
		return service.getcategoryList(approvalStatus);
	}

	public String categoryApproval(List<ProductViewMB> categoryList) {
		return service.categoryApproval(categoryList);
	}
	
	public void getAccountTypes(String clientID,AccountsDatabean accountsDatabean){
		service.getAccountTypes(clientID,accountsDatabean);
	}
	
	public String saveAcountDeposit(String clientID,AccountsDatabean accountsDatabean){
		return service.saveAcountDeposit(clientID,accountsDatabean);
	}
	
	public void getCOAdetails(String clientID, AccountsDatabean accountsDatabean){
		service.getCOAdetails(clientID,accountsDatabean);
	}
	
	public void getviewCOAAccounts(String clientID,AccountsDatabean accountsDatabean){
		service.getviewCOAAccounts(clientID,accountsDatabean);
	}
	
	public void getGeneralLedger(String clientID,AccountsDatabean accountsDatabean){
		service.getGeneralLedger(clientID,accountsDatabean);
	}
	
	public void getTrialBalance(String clientID,AccountsDatabean accountsDatabean){
		service.getTrialBalance(clientID,accountsDatabean);
	}	

	public void getProfitLoss(AccountsDatabean accountsDatabean){
		service.getProfitLoss(accountsDatabean);
	}
	
	public void getBalanceSheet(String clientID,AccountsDatabean accountsDatabean){
		service.getBalanceSheet(clientID,accountsDatabean);
	}

	public void getcategoryview(ProductRegister productRegister) {
		service.getcategoryview(productRegister);
	}

	public String categoryUpdate(ProductRegister productRegister) {
		return service.categoryUpdate(productRegister);
	}

	public String deleteCategory(String id) {
		return service.deleteCategory(id);
	}

	public String productApproval(ArrayList<ProductViewMB> finalList) {
		return service.productApproval(finalList);
	}
	
	//Stanley
	
	public String setcash(Vendor vendor) {
		return service.setcash(vendor); 
	}

	public List<String> getpaytype() {		
		return service.getpaytype();
	}

	@Override
	public String getvencode(String clientID,String userID) {
		return service.getvencode(clientID,userID);
	}

	@Override
	public String getcusCode(String clientID,String userID) {
		return service.getcusCode(clientID,userID);
	}

	@Override
	public String setbuycash(Buyer buyer) {
		return service.setbuycash(buyer); 
	}

	@Override
	public String setcashupdate(Vendor vendor) {
		return service.setcashupdate(vendor); 
	}

	@Override
	public String employeeApproval(List<EmployeeDetailsViewFormMB> employeeDetailList) {
		return service.employeeApproval(employeeDetailList);
	}

	@Override
	public String payrollApproval(List<EmployeePayroll> value1) {
		return service.payrollApproval(value1);
	}

	@Override
	public String purchaseApproval(ArrayList<PurchaseOrder> result4) {
		return service.purchaseApproval(result4);
	}

	@Override
	public String salesApproval(ArrayList<SalesViewMB> sales) {		
		return service.salesApproval(sales);
	}
	
	public ArrayList<String> accountdescription(String accounttype) {
		return service.accountdescription(accounttype);
	}

	@Override
	public String quicksalesApproval(ArrayList<QuickSaleViewMB> sales1) {
		return service.quicksalesApproval(sales1);
	}

	@Override
	public List<String> getquotproductList(String clientID, String userID) {
		return service.getquotproductList(clientID,userID);
	}

	@Override
	public List<String> getproductVendor(String clientID, String userID,String str) {
		return service.getproductVendor(clientID,userID,str);
	}

	public String insertQuotation(String clientID, String userID,List<VendorRegisterFormMB> quotationList) {
		return service.insertQuotation(clientID,userID,quotationList);
	}

	
	@Override
	public String quotationApproval(String clientID, String userID,List<VendorRegisterFormMB> quotationList,VendorRegisterFormMB vendor) {
		return service.quotationApproval(clientID,userID,quotationList,vendor);
	}

	@Override
	public List<VendorRegisterFormMB> getquotationDetails(String clientID,String quotationNumber) {
		return service.getquotationDetails(clientID,quotationNumber);
	}
	
	@Override
	public String deleteQuotation(String clientID, String quotationNumber) {
		return service.deleteQuotation(clientID,quotationNumber);
	}

	@Override
	public String finalQuotation(String clientID, String quotationNumber,List<VendorRegisterFormMB> quotationDetailList) {
		return service.finalQuotation(clientID,quotationNumber,quotationDetailList);
	}

	@Override
	public List<VendorRegisterFormMB> getfinalQuotationList(String clientID,String quotationNumber) {
		return service.getfinalQuotationList(clientID,quotationNumber);
	}

	@Override
	public String quotationUpdate(String clientID,String userID,String quotationNumber,List<VendorRegisterFormMB> quotationDetailList) {		
		return service.quotationUpdate(clientID,userID,quotationNumber,quotationDetailList);
	}

	@Override
	public void removeEditRow(int quotationDetailsId) {
		service.removeEditRow(quotationDetailsId);
	}

	@Override
	public List<LoginAccess> getapprovalCountList(String clientID) {
		return service.getapprovalCountList(clientID);
	}
	
	@Override
	public List<String> getstatusList() {
		return service.getstatusList();
	}

	@Override
	public String getcmtcode() {
		return service.getcmtcode();
	}
	
	@Override
	public List<String> getcrmtype() {
		
		return service.getcrmtype();
	}

	@Override
	public List<String> getcrmproduct() {
		return service.getcrmproduct();
	}

	@Override
	public String getCrmIndustrySave(Buyer buyer) {
		
		return service.getCrmIndustrySave(buyer); 
	}
	
	public String saveCrm(Buyer buyer) {
		return service.saveCrm(buyer); 
	}

	@Override
	public List<CrmCustomerdetail> getcrmenquiry(String query) {
		return service.getcrmenquiry(query); 
	}

	@Override
	public List<String> getcustomername() {
		return service.getcustomername();
	}

	@Override
	public List<String> getcustomerDetails(Sales sales) {
		return service.getcustomerDetails(sales); 
	}

	@Override
	public List<String> getproductlist(String clientID, String userID) {
		return service.getproductlist(clientID, userID); 
	}

	@Override
	public String getUnitprice(String productname) throws DemoException {
		return service.getUnitprice(productname); 
	}

	@Override
	public String insertNewouote(String clientID, String userID,ArrayList<SalesOrderFormMB> mblist, Sales sales) {
		return service.insertNewouote(clientID, userID, mblist, sales); 
	}

	

	@Override
	public Map<SalesOrderFormMB, ArrayList<SalesOrderFormMB>> getQuoteview(Map<SalesOrderFormMB, ArrayList<SalesOrderFormMB>> quoteList) {
		return service.getQuoteview(quoteList); 
	}
	
	
	@Override
	public String getquotationcode() {
		return service.getquotationcode(); 
	}

	@Override
	public List<SalesOrderFormMB> getQuoteviewdetails(String clientID,int quoteID, Sales sales) {
		return service.getQuoteviewdetails(clientID, quoteID, sales);
	}

	@Override
	public String quoteTabledelete(int quoteID) {
		return service.quoteTabledelete(quoteID); 
	}

	@Override
	public String consoleUpdate(String clientID,ArrayList<SalesOrderFormMB> quoteListDetails,int quoteID) {
		return service.consoleUpdate(clientID, quoteListDetails,quoteID); 
	}
	
	@Override
	public void expenseResource(String clientID, ATransaction aTransaction) {
		service.expenseResource(clientID,aTransaction);
	}

	@Override
	public List<ChartOfAccount> accountlist(String clientID) {
		return service.accountlist(clientID);
	}

	@Override
	public void accountbalance(String clientID, ATransaction aTransaction) {
		service.accountbalance(clientID,aTransaction);
		
	}

	@Override
	public String expenseSave(ATransaction aTransaction, String clientID) {
		return service.expenseSave(aTransaction,clientID);
	}

	@Override
	public List<ExpenseTransaction> getbillnumber(String clientID,
			String transactionType) {
		return service.getbillnumber(clientID,transactionType);
	}

	@Override
	public void mailresource(String clientID, ATransaction aTransaction) {
		service.mailresource(clientID,aTransaction);
	}

	@Override
	public List<ATransaction> expensesDataTable(String clientID) {
		return service.expensesDataTable(clientID);
	}

	@Override
	public void expanseDetailView(String clientID, ATransaction aTransaction) {
		service.expanseDetailView(clientID,aTransaction);
		
	}

	@Override
	public String expenseUpdate(ATransaction aTransaction, String clientID) {
		return service.expenseUpdate(aTransaction,clientID);
	}

	@Override
	public String expenseMakePayment(String clientID, ATransaction aTransaction) {
		return service.expenseMakePayment(aTransaction,clientID);
	}

	@Override
	public void getCOAtransactiondetails(String clientID,AccountsDatabean accountsDatabean) {
		service.getCOAtransactiondetails(clientID,accountsDatabean);
		
	}

	@Override
	public String editAccount(String clientID, AccountsDatabean accountsDatabean) {
		return service.editAccount(accountsDatabean,clientID);
	}
	
	@Override
	public void getsalestransactioncustprod(String clientID,ATransaction aTransaction) {
		service.getsalestransactioncustprod(clientID,aTransaction);
	}

	@Override
	public void getcustomerdetails(String clientID, ATransaction aTransaction) {
		service.getcustomerdetails(clientID,aTransaction);
	}

	@Override
	public String saveSalesTransaction(String clientID,ATransaction aTransaction, List<ATransaction> productdetails) {
		return service.saveSalesTransaction(clientID,aTransaction,productdetails);
	}

	@Override
	public List<ATransaction> getsalestransactiontableview(String clientID,ATransaction aTransaction) {
		return service.getsalestransactiontableview(clientID,aTransaction);
	}

	@Override
	public List<ATransaction> getpaymentdetails(String clientID,String paymentStatus, ATransaction aTransaction) {
		return service.getpaymentdetails(clientID,paymentStatus,aTransaction);
	}

	@Override
	public String generateInvoice(String clientID, int transactionID) {
		return service.generateInvoice(clientID,transactionID);
	}

	@Override
	public String salesTransactionUpadte(String clientID,ATransaction aTransaction) {
		return service.salesTransactionUpadte(clientID,aTransaction);
	}

	@Override
	public void salesTransactionView(String clientID, ATransaction aTransaction) {
		service.salesTransactionView(clientID,aTransaction);
	}

	@Override
	public void AccountsBalanceCal(AccountsDatabean accountsDatabean) {
		service.AccountsBalanceCal(accountsDatabean);
		
	}
	
	@Override
	public String quoteApproval(String clientID, String userID,ArrayList<SalesOrderFormMB> quoteTablelist, SalesOrderFormMB sales) {
		return service.quoteApproval(clientID, userID, quoteTablelist, sales); 
	}
	
	@Override
	public List<LoginAccess> getglobalsearchList(String clientID, String userID){
		return service.getglobalsearchList(clientID,userID); 
	}
	
	@Override
	public String codeSave(ATransaction aTransaction) {
		return service.codeSave(aTransaction);
	}

	@Override
	public List<String> getCodelist(String clientID) {
		return service.getCodelist(clientID);
	}

	@Override
	public List<ATransaction> codeDetails(ATransaction aTransaction) {
		return service.codeDetails(aTransaction);
	}

	@Override
	public void getDescription(ATransaction aTransaction) {
		service.getDescription(aTransaction);
	}

	@Override
	public List<AccountsDatabean> getcoaDetailsList(AccountsDatabean accountsDatabean) {
		return service.getcoaDetailsList(accountsDatabean);
	}

	@Override
	public List<AccountsDatabean> getjournalEntryList(AccountsDatabean accountsDatabean) {
		return service.getjournalEntryList(accountsDatabean);
	}

	@Override
	public String paymentSave(Buyer buyer) {
		return service.paymentSave(buyer);
	}

	@Override
	public List<Buyer> getmemberPayment(Buyer buyer) {
		return service.getmemberPayment(buyer);
	}

	@Override
	public String mamberPaymentUpdate(Buyer buyer) {
		return service.mamberPaymentUpdate(buyer);
	}

	@Override
	public String mamberPaymentDelete(Buyer buyer) {
		return service.mamberPaymentDelete(buyer);
	}

	@Override
	public String revenueInsertion(Sales sales) {
		return service.revenueInsertion(sales);
	}

	@Override
	public List<SalesOrderFormMB> getValuesRevenue(Sales sales) {
		return service.getValuesRevenue(sales);
	}

	@Override
	public List<Revenue> getViewRevenue(Sales sales) {
		return service.getViewRevenue(sales);
	}

	@Override
	public String coformDelete(Sales sales) {
		return service.coformDelete(sales);
	}

	@Override
	public String revenueUpdate(Sales sales) {
		return service.revenueUpdate(sales);
	}
	
	
	
}