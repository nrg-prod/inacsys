package com.inacsys.bo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.context.FacesContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.inacsys.dao.DemoAccountReceivableDao;
import com.inacsys.dao.DemoAccountsDao;
import com.inacsys.dao.DemoDao;
import com.inacsys.dao.DemoDashboardDao;
import com.inacsys.dao.DemoPurchaseDao;
import com.inacsys.dao.DemoQuickSaleDao;
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
import com.inacsys.managedBean.PurchaseOrderFromMB;
import com.inacsys.managedBean.QuickSaleViewMB;
import com.inacsys.managedBean.SalesIncomeMB;
import com.inacsys.managedBean.SalesOrderFormMB;
import com.inacsys.managedBean.SalesViewMB;
import com.inacsys.managedBean.VendorRegisterFormMB;
import com.inacsys.managedBean.VendorViewFormMB;
import com.inacsys.shared.AccPayableLiability;
import com.inacsys.shared.AccReceivableAsset;
import com.inacsys.shared.AccountDeposit;
import com.inacsys.shared.AccountPayment;
import com.inacsys.shared.AccountReceivableAcct;
import com.inacsys.shared.AccountType;
import com.inacsys.shared.CashAsset;
import com.inacsys.shared.ChartOfAccount;
import com.inacsys.shared.Client;
import com.inacsys.shared.Code;
import com.inacsys.shared.CrmCustomerdetail;
import com.inacsys.shared.Dispatch;
import com.inacsys.shared.Employee;
import com.inacsys.shared.ExpenseAccountsPayment;
import com.inacsys.shared.ExpenseCoa;
import com.inacsys.shared.ExpenseTransaction;
import com.inacsys.shared.GstAcct;
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
import com.inacsys.shared.IncomeAcct;
import com.inacsys.shared.Indexes;
import com.inacsys.shared.JournalEntry;
import com.inacsys.shared.MemberPayment;
import com.inacsys.shared.Month;
import com.inacsys.shared.Payroll;
import com.inacsys.shared.PurchaseReturn;
import com.inacsys.shared.Qualification;
import com.inacsys.shared.Quotation;
import com.inacsys.shared.QuotationDetail;
import com.inacsys.shared.Revenue;
import com.inacsys.shared.SalesQuote;
import com.inacsys.shared.SalesQuoteDetails;
import com.inacsys.shared.SalesRecord;
import com.inacsys.shared.SalesReturn;
import com.inacsys.shared.SalesAccountsPayment;
import com.inacsys.shared.SubProduct;
import com.inacsys.shared.Transaction;
import com.inacsys.shared.SalesTransaction;
import com.inacsys.shared.Year;
import com.inacsys.util.AccountsJDBC;
import com.inacsys.util.CurrencyConverter;
import com.inacsys.util.GenerateEmployee;
import com.inacsys.util.Util;
import com.lowagie.text.pdf.crypto.ARCFOUREncryption;

/**
 * This Java Class will communicate with InventoryDao.java
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 * 
 */
@Service("bo")
public class DemoBoImpl implements DemoBo {
	final Logger logger = LoggerFactory.getLogger(DemoBoImpl.class);

	@Autowired
	DemoDao dao;
	
	@Autowired
	DemoQuickSaleDao qdao;

	@Autowired
	DemoDashboardDao ddao;

	@Autowired
	DemoAccountReceivableDao ardao;
	
	@Autowired
	DemoAccountsDao adao;
	
	@Value("${accountssales.invoice.status}" )
	private String salesInvoice;
	
	@Value("${accountssales.payment.status}" )
	private String salesPayment;
	
	@Value("${accountssales.estimate.status}" )
	private String salesEstimate;
	
	@Value("${accountssales.salesreceipt.status}" )
	private String salesReceipt;
	
	@Value("${accountssales.creditmemo.status}" )
	private String salesCreditMemo;
	
	@Value("${accountssales.delayedcharge.status}" )
	private String salesDelayedCharge;
	
	@Value("${accountssales.timeactivity.status}" )
	private String salesTimeActivity;
	
	@Value("${accountrec.name.status}" )
	private String accrecName;
	
	@Value("${service.name.status}" )
	private String serviceName;
	
	@Value("${accounts.runreportbtn.status}" )
	private String runreportBtn;
	
	@Value("${accounts.acchistorybtn.status}" )
	private String accthistoryBtn;
	
	
	public String loginBo(LoginAccess loginaccess) throws DemoException {
		logger.debug("inside bo");
		if (loginaccess.getStatus().equalsIgnoreCase("active")) {
			dao.loginDao(loginaccess);
		}
		if (loginaccess.getStatus().equalsIgnoreCase("deactive")) {
			dao.logoutDao(loginaccess);
		}
		return null;
	}

	@Override
	public int productInfo2() throws DemoException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String vendorbo(Vendor vendor) throws DemoException {

		return dao.vendorDao(vendor);
	}

	public String countryDrop(Vendor vendor) throws DemoException {
		dao.countryDrop(vendor);
		return "success";
	}

	public List<I0025> vendorUpadteBo(Vendor vendor, List<I0025> I0025)
			throws DemoException {
		I0025 = dao.vendorUpdateDao(vendor, I0025);
		if (I0025.size() > 0) {
			logger.debug("inside vendor update condition::::::::::");
			//int i = 0;
		}
		return I0025;
	}

	public String vendorDeleteBo(VendorDelete vendorDelete)
			throws DemoException {
		dao.vendorDeleteDao(vendorDelete);
		return null;
	}

	public String vendorModify(Vendor vendor, List<I0025> xx)
			throws DemoException {
		dao.vendorModify(vendor, xx);
		return null;
	}

	public String dropDown(ProductRegister productRegister,
			List<I0004> typeparent, List<I0002> productgroup,
			List<I0006> industryList) throws DemoException {
		dao.dropDown(productRegister, typeparent, productgroup, industryList);
		return null;
	}

	public List<I0004> dropDown(List<I0004> typeparent) throws DemoException {
		typeparent = dao.dropDown(typeparent);
		return typeparent;
	}

	public List<I0002> dropDow(List<I0002> productgroup) throws DemoException {
		productgroup = dao.dropDow(productgroup);
		return productgroup;
	}

	public List<I0006> dropDo(List<I0006> industryList) throws DemoException {
		industryList = dao.dropDo(industryList);
		return industryList;
	}

	public List<String> dropD(List<String> ven) throws DemoException {
		ven = dao.dropD(ven);
		return ven;
	}

	public String saveProductRegister(ProductRegister productRegister)
			throws DemoException, ParseException {

		return dao.saveProductRegister(productRegister);
	}

	public String saveProductEdit(List<I0001> i0001s,
			ProductRegister productRegister) throws Exception {
		dao.saveProductEdit(i0001s, productRegister);
		return null;
	}

	public List<I0001> productView(List<I0001> i0001s,
			ProductRegister productRegister) throws DemoException {
		i0001s = dao.productView(i0001s, productRegister);
		return i0001s;
	}

	public List<I0001> productView1(List<I0001> i0001s,
			ProductRegister productRegister) throws DemoException {
		i0001s = dao.productView1(i0001s, productRegister);
		return i0001s;
	}

	@Override
	public List<I0001> productView2(List<I0001> i0001s,
			ProductRegister productRegister, String golbalnamesearch)
			throws DemoException {
		i0001s = dao.productView2(i0001s, productRegister, golbalnamesearch);
		return i0001s;
	}

	public String productReject(ProductRegister productRegister)
			throws DemoException {
		dao.productReject(productRegister);
		return null;
	}

	public String productRemove(ProductRegister productRegister)
			throws DemoException {
		dao.productRemove(productRegister);
		return null;
	}

	public List<I0001> autoComplete(List<I0001> auto,
			ProductRegister productRegister) throws DemoException {
		auto = dao.autoComplete(auto, productRegister);
		return auto;
	}

	public List<I0025> purchaseDrop(List<I0025> drop,
			PurchaseOrder purchaseOrder) throws DemoException {
		drop = dao.purchaseDrop(drop, purchaseOrder);
		return drop;
	}

	public ArrayList<String> changeDrop(String s, ArrayList<String> productlist)
			throws DemoException {
		productlist = dao.changeDrop(s, productlist);
		return productlist;
	}

	public String changeFirmName(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.changeFirmName(purchaseOrder);
		return "";
	}

	public String changeDrop1(String s) throws DemoException {
		s = dao.changeDrop1(s);
		return s;
	}

	public String puruchaseOrder(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.puruchaseOrder(purchaseOrder);
		logger.info("----------- Inside puruchaseOrder method() ---------");
		logger.debug("[puruchaseOrder] selling price------------>"
				+ purchaseOrder.getSellingPrice());
		logger.debug("quantit" + purchaseOrder.getQuantity());
		int q = Integer.parseInt(purchaseOrder.getQuantity());
		String total = ""
				+ (BigDecimal.valueOf(q).multiply(new BigDecimal(purchaseOrder
						.getActualPrice())));
		logger.debug("[puruchaseOrder] Total -->" + total);
		purchaseOrder.setTotalPrice(total);
		return null;
	}

	public String savePuruchaseOrder(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.savePuruchaseOrder(purchaseOrder);
		return null;
	}

	public String savePuruchaseOrder1(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.savePuruchaseOrder1(purchaseOrder);
		return null;
	}

	public ArrayList<I0016> purchaseOrdercancel(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.info("----------- Inside purchaseOrdercancel() mehtod --------------");
		logger.debug("purchaseOrdercancel value " + s);
		purchaselist = dao.purchaseOrdercancel(s, purchaselist, purchaseOrder);
		if (purchaselist.size() == 0) {
			throw new DemoException("*Already delivered !");
		}
		return purchaselist;
	}

	public ArrayList<I0016> purchaseOrdercancel1(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("purchaseOrdercancel1 value " + s);
		if (s.equals("")) {
			throw new DemoException("* should enter the Order Number");
		}
		purchaselist = dao.purchaseOrdercancel1(s, purchaselist, purchaseOrder);
		if (purchaselist == null) {
			throw new DemoException("*Already delivered !");
		}
		return purchaselist;
	}

	public ArrayList<I0015> invoicePurachaseDrop(ArrayList<I0015> purchaselist)
			throws DemoException {
		purchaselist = dao.invoicePurachaseDrop(purchaselist);
		return purchaselist;
	}

	public ArrayList<I0015> invoicePurachaseDrop1(ArrayList<I0015> purchaselist)
			throws DemoException {
		purchaselist = dao.invoicePurachaseDrop1(purchaselist);
		return purchaselist;
	}

	public String purchaseClose(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.purchaseClose(purchaseOrder);
		return null;
	}

	public ArrayList<I0015> dateSearchInvoice(Date fromDate, Date todate,
			ArrayList<I0015> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		purchaselist = dao.dateSearchInvoice(fromDate, todate, purchaselist,
				purchaseOrder);
		return purchaselist;
	}

	public String cancelConform1(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.cancelConform1(purchaseOrder);
		return null;
	}

	public ArrayList<I0016> purchaseView(PurchaseOrder purchaseOrder,
			ArrayList<I0016> purchaselist) throws DemoException {
		logger.debug("string" + purchaseOrder.getOrderDate());
		purchaselist = dao.purchaseView(purchaseOrder, purchaselist);
		return purchaselist;
	}

	public ArrayList<I0016> invoicePurhcase1(PurchaseOrder purchaseOrder,
			ArrayList<I0016> purchaselist) throws DemoException {
		purchaselist = dao.invoicePurhcase1(purchaseOrder, purchaselist);
		return purchaselist;
	}

	public String invoicePurhcase(PurchaseOrder purchaseOrder)
			throws DemoException {
		adao.invoicePurhcase(purchaseOrder);
		return null;
	}

	public ArrayList<String> dropAccount(String s, ArrayList<String> ordernumber)
			throws DemoException {
		ordernumber = dao.dropAccount(s, ordernumber);
		return ordernumber;
	}

	public ArrayList<I0023> AccountOut(PurchaseOrder purchaseOrder,
			ArrayList<I0023> purchaselist) throws DemoException {
		purchaselist = dao.AccountOut(purchaseOrder, purchaselist);
		return purchaselist;
	}

	public String payNow1(PurchaseOrder purchaseOrder) throws DemoException {
		dao.payNow1(purchaseOrder);
		return null;
	}

	public ArrayList<I0016> purchaseOrderPayment(Date s, Date s1,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		if (s.equals("")) {
			throw new DemoException("* should enter the Order number");
		}
		purchaselist = dao.purchaseOrderPayment(s, s1, purchaselist,
				purchaseOrder);
		return purchaselist;
	}

	public ArrayList<I0016> purchaseDeliveryStatus(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		if (s.equals("")) {
			throw new DemoException("should enter the Order Number>>>>>>");
		}
		purchaselist = dao.purchaseDeliveryStatus(s, purchaselist,
				purchaseOrder);
		return purchaselist;
	}

	public ArrayList<I0016> purchaseDeliveryStatus2(
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		purchaselist = dao.purchaseDeliveryStatus2(purchaselist, purchaseOrder);
		return purchaselist;
	}

	public String deliveryStatus(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.deliveryStatus(purchaseOrder);
		return null;
	}

	public ArrayList<I0015> purchaseOrderClose(ArrayList<I0015> purchaselist,
			PurchaseOrder purchaseOrder) throws DemoException {
		purchaselist = dao.purchaseOrderClose(purchaselist, purchaseOrder);
		return purchaselist;
	}

	public String stockInForm(PurchaseOrder purchaseOrder) throws DemoException {
		dao.stockInForm(purchaseOrder);
		return null;
	}

	public String stockInForm1(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.stockInForm1(purchaseOrder);
		return null;
	}

	// who added this method 
	public String addStock(PurchaseOrder purchaseOrder) throws DemoException {
		String status="";
		status=dao.addStock(purchaseOrder);
		if(!status.equalsIgnoreCase("Exist")){
			List<I0016> resul = dao.addStock1(purchaseOrder);
			String s = resul.get(0).getI0015().getQuantity();
			float quant = Float.parseFloat(s);
			int count = 0;
			I0018 batch = new I0018();
			int J = 0;
			for (I0016 i0016 : resul) {
				dao.valueChange(resul, purchaseOrder, J, batch);

				J++;
			}
			for (I0016 i0016 : resul) {
				logger.debug("==========for count=============" + count);
			}

			purchaseOrder.setBatchID(resul.get(count).getHas_purchase_ID());
			logger.debug("Order Number" + resul.get(count).getHas_purchase_ID());
			dao.addStock2(purchaseOrder, resul, count);
			float quantity = 0;
			for (I0018 i0018 : purchaseOrder.getResup1()) {
				for (int l = 0; l < purchaseOrder.getResulp().size(); l++) {
					if (i0018.getProductName().equals(
							purchaseOrder.getResulp().get(l).getI0031().getI0001()
									.getProductName())) {
						int batchid = 0;
						quantity = Float.parseFloat(purchaseOrder.getResulp()
								.get(l).getQuantity());
						batchid = i0018.getBatch_ID();
						logger.debug("-------------->"
								+ purchaseOrder.getDeliveredDate());
						Date dd = PurchaseOrderFromMB
								.sampleAfterOneWeak(purchaseOrder
										.getDeliveredDate());
						status=dao.addStock4(purchaseOrder, i0018.getBatch_ID(), quantity);
						logger.debug("Date-------------<><><><>" + dd);

					}
				}

			}
			dao.addedStock(purchaseOrder);
			logger.debug("success");
		}
		System.out.println("status------"+status);
		return status;
	}

	public String addStock1(PurchaseOrder purchaseOrder) throws DemoException {
		dao.addStock3(purchaseOrder);
		return null;
	}

	public List<I0018> salesDrop(List<I0018> batch) throws DemoException {
		batch = dao.salesDrop(batch);
		return batch;
	}

	public List<I0019> stockView(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		batch2 = dao.stockView(batch2, s, stockView);
		return batch2;
	}

	public List<I0019> stockView2(List<I0019> batch2, StockView stockView)
			throws DemoException {
		batch2 = dao.stockView2(batch2, stockView);
		return batch2;
	}

	public List<I0018> stockView1(List<I0018> batch2) throws DemoException {
		batch2 = dao.stockView1(batch2);
		return batch2;
	}
// Without any reson the value has been assigned to s , quant 
	public String addDamage(PurchaseOrder purchaseOrder) throws DemoException {
		//String s = purchaseOrder.getQuantity();
		//float quant = Float.parseFloat(s);
		dao.addDamage(purchaseOrder);
		return null;
	}

	public String addDamage1(PurchaseOrder purchaseOrder) throws DemoException {
		dao.stockQuantity(purchaseOrder);
		dao.addDamage1(purchaseOrder);
		String s = purchaseOrder.getQuantity();
		float quant = Float.parseFloat(s);
		logger.debug("addDamage1 quantity -->" + quant);
		return null;
	}

	public List<I0019> stockdamageView(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		batch2 = dao.stockdamageView(batch2, s, stockView);
		return batch2;
	}

	public List<I0018> salesOrder3(List<I0018> batchProductName3)
			throws DemoException {
		batchProductName3 = dao.salesOrder3(batchProductName3);
		return batchProductName3;
	}

	public List<String> productVendor(List<String> batchProductName3)
			throws DemoException {
		batchProductName3 = dao.productVendor(batchProductName3);
		return batchProductName3;
	}

	public List<String> productVendor1(List<String> batchProductName3, String s)
			throws DemoException {
		batchProductName3 = dao.productVendor1(batchProductName3, s);
		return batchProductName3;
	}

	public String salesOrder4(PurchaseOrder purchaseOrder) throws DemoException {
		dao.salesOrder4(purchaseOrder);
		logger.debug("1");
		String quant = purchaseOrder.getQuantity();
		logger.debug("2");
		String temp = "0";
		try {
			temp = ""
					+ BigDecimal.valueOf(0).add(
							new BigDecimal(purchaseOrder.getCrosstotal1()));
		} catch (Exception e) {
		}
		logger.debug("3");
		try {
			temp = ""
					+ (new BigDecimal(quant).multiply(new BigDecimal(
							purchaseOrder.getSellingPrice())))
							.add(new BigDecimal(temp));
		} catch (Exception e) {
		}
		logger.debug("4");
		logger.debug("crosstotal:::::" + temp);
		purchaseOrder.setCrosstotal1(temp);
		logger.debug("5");
		return null;
	}

	// FINISHED 12/11/14
	public String salesOrder5(PurchaseOrder purchaseOrder) throws DemoException {

		logger.debug("[salesOrder5] crosstotal-->" + purchaseOrder.getCrosstotal1());
		int quant = Integer.parseInt(purchaseOrder.getQuantity());

		for (int i = 0; i < quant; i++) {
			dao.salesOrder5(purchaseOrder);
		}
		return null;
	}

	public List<I0019> stockoutForm(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		batch2 = dao.stockoutForm(batch2, s, stockView);
		return batch2;
	}

	public List<I0019> stockInForm(List<I0019> batch2, String s)
			throws DemoException {
		batch2 = dao.stockInForm(batch2, s);
		return batch2;
	}

	public String salesOrdercancelForm(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesOrdercancelForm(purchaseOrder);

		logger.debug("return two sucess::::");
		return null;
	}

	public String salesOrdercancelForm4(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesOrdercancelForm4(purchaseOrder);
		logger.debug("return two sucess::::");
		return null;
	}

	public String updateSales1(PurchaseOrder purchaseOrder)
			throws DemoException {
		return adao.updateSales1(purchaseOrder);
	}

	public String salesOrdercancel(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesOrdercancel(purchaseOrder);
		logger.debug("return two sucess::::");
		return null;
	}

	public String salesOrdercancelFormsub1(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesOrdercancelFormsub1(purchaseOrder);
		logger.debug("return two sucess::::");
		return null;
	}

	public String salesOrdercancelFormsub(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesOrdercancelFormsub(purchaseOrder);
		logger.debug("return two sucess::::");
		return null;
	}

	public String salesDrop(PurchaseOrder purchaseOrder) throws DemoException {
		dao.salesDrop(purchaseOrder);
		logger.debug("return two sucess::::");
		return null;
	}

	public String salesDrop1(PurchaseOrder purchaseOrder) throws DemoException {
		dao.salesDrop1(purchaseOrder);
		logger.debug("return two sucess::::");
		return null;
	}

	public String changeDrop(PurchaseOrder purchaseOrder) throws DemoException {
		dao.changeDrop(purchaseOrder);
		logger.debug("return two sucess::::");
		return null;
	}

	public String changeDrop1(PurchaseOrder purchaseOrder) throws DemoException {
		dao.changeDrop1(purchaseOrder);
		logger.debug("return two sucess::::");
		return null;
	}

	public String salesOrdercancelForm1(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesOrdercancelForm1(purchaseOrder);
		logger.debug("return two sucess::::");
		return null;
	}

	public String salesOrdercancelForm3(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesOrdercancelForm3(purchaseOrder);
		logger.debug("return two sucess::::");
		return null;
	}

	public String salesOrdercancelForm2(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesOrdercancelForm2(purchaseOrder);

		logger.debug("return two sucess::::");
		return null;
	}

	public String salesOrdercancelForm5(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesOrdercancelForm5(purchaseOrder);

		logger.debug("return two sucess::::");
		return null;
	}

	public String salesReturnForm2(PurchaseOrder purchaseOrder)
			throws DemoException {

		List<I0021> sales = null;
		List<I0018> bList = null;
		List<I0019> i19 = null;
		try {
			dao.salesOrdercancelFormsub1(purchaseOrder);
			sales = purchaseOrder.getResult();
			if (sales.size() > 0) {
				bList = dao.getStockDataforbatch(sales.get(0).getSales_ID());
				if (bList.size() > 0) {
					logger.debug("Size-------------------------------->"
							+ bList.size());
					for (int a = 0; a < bList.size(); a++) {
						i19 = dao.stocks(sales.get(0).getSales_ID(),
								bList.get(a).getBatch_ID());
						if (i19.size() > 0) {
							for (I0019 re : i19) {
								int id = re.getI0018().getBatch_ID();
								logger.debug("int id::::::::::::::" + id);
								int barid = re.getBar_code_ID();
								dao.stockupdate(barid);
								dao.batchupdated(id);
							}

							dao.salesreturnSave(i19,
									purchaseOrder.getReturnDate(), "normal");
						}
					}
					dao.salesupdateforreturn(sales.get(0).getSales_ID(),
							purchaseOrder);
				}
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		logger.debug("return two sucess::::");
		return null;
	}

	public String salesReturnForm3(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesReturnForm3(purchaseOrder);
		dao.salesReturnForm4(purchaseOrder);
		// dao.salesOrder2(purchaseOrder);
		logger.debug("return two sucess::::");
		return null;
	}

	public String salesReturnForm(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesReturnFor(purchaseOrder);
		dao.salesReturnForm4(purchaseOrder);
		logger.debug("return two sucess::::");
		return null;
	}

	public String salesReturnForm5(PurchaseOrder purchaseOrder)
			throws DemoException {

		List<I0021> sales = null;
		List<I0018> bList = null;
		List<I0019> i19 = null;
		try {
			dao.salesOrdercancelFormsub1(purchaseOrder);
			sales = purchaseOrder.getResult();
			if (sales.size() > 0) {
				bList = dao.getStockDataforbatch(sales.get(0).getSales_ID());
				if (bList.size() > 0) {
					logger.debug("Size-------------------------------->"
							+ bList.size());
					for (int a = 0; a < bList.size(); a++) {
						i19 = dao.stocks(sales.get(0).getSales_ID(),
								bList.get(a).getBatch_ID());
						if (i19.size() > 0) {
							for (I0019 re : i19) {
								int id = re.getI0018().getBatch_ID();
								logger.debug("int id::::::::::::::" + id);
								int barid = re.getBar_code_ID();
								dao.stockupdate1(barid);

							}

							dao.salesreturnSave(i19,
									purchaseOrder.getReturnDate(), "damage");
						}
					}
					dao.salesupdateforreturn1(sales.get(0).getSales_ID(),
							purchaseOrder);
				}
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		logger.debug("return two sucess::::");
		return null;
	}

	public String salesdelivery(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesdelivery(purchaseOrder);
		// dao.salesOrder2(purchaseOrder);
		logger.debug("return two sucess::::");
		return null;
	}

	public List<I0021> salesPayment1(List<I0021> salesreferenumber)
			throws DemoException {
		salesreferenumber = dao.salesPayment1(salesreferenumber);
		return salesreferenumber;
	}

	public String salesPayment2(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesPayment2(purchaseOrder);
		return null;
	}

	public String salesView(PurchaseOrder purchaseOrder) throws DemoException {
		dao.salesView(purchaseOrder);
		return null;
	}

	public ArrayList<I0021> invoiceSales(PurchaseOrder purchaseOrder,
			ArrayList<I0021> sales) throws DemoException {
		sales = dao.invoiceSales(purchaseOrder, sales);
		return sales;
	}

	public String invoiceSales(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.invoiceSales(purchaseOrder);
		return null;
	}

	public String invoiceSales1(PurchaseOrder purchaseOrder)
			throws DemoException {
		adao.invoiceSales1(purchaseOrder);

		logger.debug("5");
		return null;
	}

	public String accountin(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("inside bo:::::::::::::");
		dao.accountin(purchaseOrder);
		dao.accountin1(purchaseOrder);
		logger.debug("return bo:::::::::::::::");
		return null;
	}

	public ArrayList<I0021> accountin(ArrayList<I0021> drop)
			throws DemoException {
		drop = dao.accountin(drop);
		return drop;
	}

	public String payNowAccount(PurchaseOrder purchaseOrder)
			throws DemoException {
		return dao.payNowAccount(purchaseOrder);
	}

	public ArrayList<I0001> dropdownproduct(String productname,
			ArrayList<I0001> productDrop) throws DemoException {
		logger.debug("inside bo dropdown::::::::::::::::");
		productDrop = dao.dropdownproduct(productname, productDrop);
		return productDrop;
	}

	public ArrayList<I0022> reportInvoice(ArrayList<I0022> invoiceList,
			Report1 report1) throws DemoException {
		invoiceList = dao.reportInvoice(invoiceList, report1);
		logger.debug("out dao::::::::::");
		return invoiceList;
	}

	public ArrayList<I0015> reportPurchase(ArrayList<I0015> purchaseList,
			Report1 report1) throws DemoException {
		purchaseList = dao.reportPurchase(purchaseList, report1);
		logger.debug("out dao::::::::::");
		return purchaseList;
	}

	public ArrayList<I0021> reportSales(ArrayList<I0021> salesList1,
			Report1 report1) throws DemoException {
		salesList1 = dao.reportSales(salesList1, report1);
		logger.debug("out dao::::::::::");
		return salesList1;
	}

	public ArrayList<I0025> vendorView(Vendor vendor) throws DemoException {

		return dao.vendorView(vendor);
	}

	@Override
	public ArrayList<I0025> vendorView1(Vendor vendor, String golbalnamesearch)
			throws DemoException {

		return dao.vendorView1(vendor, golbalnamesearch);
	}

	public String salesDelete() throws DemoException {
		ArrayList<I0021> temp = dao.salesDelete();
		if (temp.size() > 0) {
			int id = 0;
			logger.debug("temp--------->" + temp.size());
			for (I0021 i0021 : temp) {
				id = i0021.getSales_ID();
				logger.debug("sales Id------------->" + id);
				dao.salesDelete1(id);
			}
		}
		return null;
	}

	public List<I0018> dropDownbatch(List<I0018> batch) throws DemoException {
		logger.debug("inside Bo::::::::::::");
		batch = dao.dropDownbatch(batch);
		return batch;
	}

	public String purchaseProductEdit(PurchaseOrder purchaseOrder)
			throws DemoException {
		adao.purchaseProductEdit(purchaseOrder);
		return null;
	}

	public String saveBuyer(Buyer b) throws DemoException {
		return dao.saveBuyer(b);
	}

	public int getPurchasestatus() throws DemoException {
		return dao.getPurchasestatus();
	}

	public int getsalesstatus() throws DemoException {
		return dao.getsalesstatus();
	}

	public int getSalesOrderStatus() throws DemoException {
		return dao.getSalesOrderStatus();
	}

	public int getPurchaseorderStatus() throws DemoException {
		return dao.getPurchaseorderStatus();
	}

	public List<I0028> getCountry() throws DemoException {
		return dao.getCountry();
	}

	public List<I0032> getBuyerInfo(String phoneNumber) throws DemoException {
		return dao.getBuyerInfo(phoneNumber);
	}

	public List<I0032> getBuyerInfo1(String phoneNumber,String clientID,String userID) throws DemoException {
		return dao.getBuyerInfo1(phoneNumber,clientID,userID);
	}

	public List<I0032> getBuyerUpdate(Buyer b) throws DemoException {
		return dao.getBuyerUpdate(b);
	}

	public List<I0032> getPhone() throws DemoException {
		return dao.getPhone();
	}

	public ArrayList<I0032> customerNameChange(String s) throws DemoException {

		return dao.customerNameChange(s);
	}

	public String buyerDelete(Buyer b) throws DemoException {
		return dao.buyerDelete(b);
	}

	public ArrayList<I0032> salesCustomer(ArrayList<I0032> buyername,
			PurchaseOrder purchaseOrder) throws DemoException {

		return dao.salesCustomer(buyername, purchaseOrder);
	}

	public ArrayList<I0032> salesOrder(ArrayList<I0032> buyername, Buyer b)
			throws DemoException {

		return dao.salesOrder(buyername, b);
	}

	public List<StockView> getStockInfo() throws DemoException {

		return dao.getStockInfo();
	}

	public String filePath(PurchaseOrder purchaseOrder) throws DemoException {
		dao.filePath(purchaseOrder);
		return null;
	}

	public String fileSave(PurchaseOrder purchaseOrder) throws DemoException {
		dao.fileSave(purchaseOrder);
		return null;
	}

	public String approvalView(Approval approval) throws DemoException {
		dao.approvalView(approval);
		return null;
	}

	public String approvalView1(Approval approval) throws DemoException {
		dao.approvalView1(approval);
		return null;
	}

	public String approvalView2(Approval approval) throws DemoException {
		dao.approvalView2(approval);
		return null;
	}

	public String approvalView3(Approval approval) throws DemoException {
		dao.approvalView3(approval);
		return null;
	}
//Who put this method ?
	public String approvalView4(Approval approval) throws DemoException {
		int i = 0;
		for (I0033 i033 : dao.approvalView4(approval)) {

			logger.debug("aproval" + i);
			dao.approvalView5(dao.approvalView4(approval), i,approval.getResullt1());

		}
		dao.approvalView6(approval.getResullt1());

		return null;
	}

	/* udhaya 30.12.2014 */
	public String categoryType(CategoryRegistration categoryreg)
			throws DemoException {
		logger.debug("inside bo-------->>");
		return dao.categoryType(categoryreg);
	}

	/* udhaya 31.12.2014 */
	public List<String> categorylist(List<String> categorytype)
			throws DemoException {
		logger.debug("inside bo--------->>");
		return dao.categorylist(categorytype);
	}

	public String category(ProductRegister productRegister)
			throws DemoException {
		logger.debug("inside bo--------->>");
		return dao.category(productRegister);
	}

	/* udhaya 2.1.2015 */
	public int getPurchaseapprovalstatus() throws DemoException {
		logger.debug("inside bo--------->>");
		return dao.getPurchaseapprovalstatus();
	}

	public String approvalstatus(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("inside bo--------->>");
		return dao.approvalstatus(purchaseOrder);
	}

	/* udhaya 5.1.2015 */
	public ArrayList<I0016> approvalStage(String orderNumber,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("inside bo--------->>");
		return dao.approvalStage(orderNumber, purchaselist, purchaseOrder);
	}

	public String approved(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("inside bo--------->>");
		return dao.approved(purchaseOrder);
	}

	public String quickSaleView(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("Inside bo=-------------->>");
		return dao.quickSaleView(purchaseOrder);
	}

	/* udhaya 7.1.2015 */

	public List<I0021> view(List<I0021> saleslist, PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("Inside bo=-------------->>");
		return dao.view(saleslist, purchaseOrder);
	}

	public List<PurchaseOrder> getQuicksaleEdit(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("Inside bo=-------------->>");
		return dao.getQuicksaleEdit(purchaseOrder);
	}

	/* udhaya 8.1.2015 */
	public List<I0021> customerNameChange(List<I0021> cusname)
			throws DemoException {
		logger.debug("Inside bo=-------------->>");
		return dao.customerNameChange(cusname);
	}

	/* jeni */
	public List<I0032> getBuyercustInfo(Buyer b) throws DemoException {
		logger.debug("Inside bo");
		return dao.getBuyercustInfo(b);

	}

	@Override
	public List<I0032> getBuyercustInfo(Buyer b, String golbalnamesearch)
			throws DemoException {
		return dao.getBuyercustInfo(b, golbalnamesearch);
	}

	public String getPurchaseVendorView(PurchaseOrder purchaseVendor)
			throws DemoException {
		return dao.getPurchaseVendorView(purchaseVendor);
	}

	/* kasturi */

	public ArrayList<I0016> purchaseOrderPayment1(
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.purchaseOrderPayment1(purchaselist, purchaseOrder);
		return null;
	}

	public String remSalesDeliver(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.remSalesDeliver(purchaseOrder);
		return null;
	}

	public String remSalesDelivery(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.remSalesDelivery(purchaseOrder);
		return null;
	}

	public ArrayList<I0016> remPurchaseDeliver(ArrayList<I0016> purchaselist,
			PurchaseOrder purchaseOrder) throws DemoException {
		dao.remPurchaseDeliver(purchaselist, purchaseOrder);
		return null;
	}

	public String remPurchasePaymentStatus(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.remPurchasePaymentStatus(purchaseOrder);
		return null;
	}

	/* jeni */
	public String salesPaypend(PurchaseOrder purchaseOrder)
			throws DemoException {
		return dao.salesPaypend(purchaseOrder);
	}

	public int getSalesPayStatus() throws DemoException {
		return dao.getSalesPayStatus();
	}

	/* ranjini */
	public String getpurchaseInfo(PurchaseOrder p) throws DemoException {
		return qdao.getpurchaseInfo(p);
	}

	public List<String> getProductName() throws DemoException {
		return qdao.getProductName();
	}

	public List<I0019> getBarCodeData(String productName) throws DemoException {
		return qdao.getBarCodeData(productName);
	}

	public String saveSales(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("cross total------------>"
				+ purchaseOrder.getCrosstotal1());
		qdao.saveSales(purchaseOrder);
		String quant = purchaseOrder.getQuantity();
		String temp = ""
				+ BigDecimal.valueOf(0).add(
						new BigDecimal(purchaseOrder.getCrosstotal1()));
		temp = ""
				+ new BigDecimal(quant).multiply(
						new BigDecimal(purchaseOrder.getSellingPrice())).add(
						new BigDecimal(temp));
		logger.debug("crosstotal:::::" + temp);
		purchaseOrder.setCrosstotal1(temp);
		return "";
	}

	public String qucikSalesConform(PurchaseOrder purchaseOrder)throws DemoException {
		adao.qucikSalesConform1(purchaseOrder);
		return null;
	}

	public String qucikSalesConform2(PurchaseOrder purchaseOrder)
			throws DemoException {

		logger.debug("crosstotal------------->"
				+ purchaseOrder.getCrosstotal1());
		//float quant = Float.parseFloat(purchaseOrder.getQuantity());
		qdao.qucikSalesConform(purchaseOrder);

		return null;

	}

	public String qucikSalesConform1(PurchaseOrder purchaseOrder)
			throws DemoException {
		qdao.qucikSalesConform2(purchaseOrder);
		logger.debug("crosstotal------------->"
				+ purchaseOrder.getCrosstotal1());
		int quant = Integer.parseInt(purchaseOrder.getQuantity());
		for (int i = 0; i < quant; i++) {
			qdao.qucikSalesConform(purchaseOrder);
		}
		return null;

	}

	@Autowired
	DemoPurchaseDao pdao;

	public List<PurchaseOrder> getpurchaseDataFVendor(String vendorName)
			throws DemoException {
		return pdao.getpurchaseDataFVendor(vendorName);
	}

	/* Sivaranjini 12/1/2015 */

	public int getsalesQuantityOfMonth(Date fDate, Date tDate)
			throws DemoException {
		return ddao.getsalesQuantityOfMonth(fDate, tDate);
	}

	// sivaranjini 13_1_15

	public float getSalesAmount(Date fDate, Date tDate) throws DemoException {
		return ddao.getSalesAmount(fDate, tDate);
	}

	public float getpurchaseAmount(Date fDate, Date tDate) throws DemoException {
		return ddao.getpurchaseAmount(fDate, tDate);
	}

	public int getStockinQuantity(Date fDate, Date tDate) throws DemoException {
		return ddao.getStockinQuantity(fDate, tDate);
	}

	public int getStockOutQuantity(Date fDate, Date tDate) throws DemoException {
		return ddao.getStockOutQuantity(fDate, tDate);
	}

	// sivaranjini 14_1_15

	public int getsalesInvoice(Date fDate, Date tDate) throws DemoException {
		return ddao.getsalesInvoice(fDate, tDate);
	}

	public int getpurchaseInvoice(Date fDate, Date tDate) throws DemoException {
		return ddao.getpurchaseInvoice(fDate, tDate);
	}

	/* siva 20_2_15 */

	public int stockInfo() throws DemoException {
		logger.debug("Inside Stock infoo");
		int count = 0;
		List<I0001> product = null;
		List<I0018> batch = null;
		List<I0019> bar = null;
		try {
			int stockcount = 0;
			product = dao.getProductInfo();
			if (product.size() > 0) {
				for (int i = 0; i < product.size(); i++) {
					batch = dao.getBatchInfo(product.get(i).getProductName());
					if (batch.size() > 0) {
						bar = dao.getBarcodeInfo(batch.get(0).getBatch_ID());
						if (bar.size() > 0) {
							stockcount = bar.size();
							if (stockcount < product.get(i).getProductLimit()) {
								count++;
							}
						} else {

						}

					} else {
						logger.debug("No data in batch");
					}
				}
			} else {
				logger.debug("No data in product");
			}

		} catch (Exception e) {

		} finally {
		}
		return count;
	}

	public List<StockView> getStockInInfo() throws DemoException {
		List<StockView> stock = null;
		List<I0001> product = null;
		List<I0018> batch = null;
	//	List<I0019> bar = null;
		try {
			stock = new ArrayList<StockView>();
			//int stockcount = 0;
			product = dao.getProductInfo();
			if (product.size() > 0) {
				for (int i = 0; i < product.size(); i++) {
					batch = dao.getBatchInfo(product.get(i).getProductName());
					if (batch.size() > 0) {
						List<StockView> stock1 = new ArrayList<StockView>();
						stock1.add(dao.getStockData(batch.get(0).getBatch_ID()));
						logger.debug("size<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
								+ stock1.size());
						for (int j = 0; j < stock1.size(); j++) {
							if (stock1.get(j).getQuantity() < stock1.get(j)
									.getStocklimit()) {
								StockView st = new StockView();
								st.setStockinDate(stock1.get(j)
										.getStockinDate());
								st.setProductname(stock1.get(j)
										.getProductname());
								st.setFirmName(stock1.get(j).getFirmName());
								st.setBatchName(stock1.get(j).getBatchName());
								st.setBuyingPrice(stock1.get(j)
										.getBuyingPrice());
								st.setUnitprice(stock1.get(j).getUnitprice());
								st.setQuantity(stock1.get(j).getQuantity());
								st.setStocklimit(stock1.get(j).getStocklimit());
								stock.add(st);

							}
						}

					} else {
						logger.debug("No data in batch");
					}
				}
			} else {
				logger.debug("No data in product");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return stock;
	}

	public String saveconfirm(ATransaction save) throws DemoException {
		logger.debug("inside bo------>>>");
		return adao.saveconfirm(save);
	}

	public String transactionView(ATransaction search) throws DemoException {
		logger.debug("inside controller------->>>");
		return adao.transactionView(search);
	}

	public List<ATransaction> getviewForm(ATransaction view)
			throws DemoException {
		logger.debug("inside bo------>>>");
		return adao.getviewForm(view);
	}

	public List<ATransaction> getEditForm(ATransaction edit)
			throws DemoException {
		logger.debug("inside bo------>>>");
		return adao.getEditForm(edit);
	}

	public List<ATransaction> getStatusChange(ATransaction change)
			throws DemoException {
		logger.debug("---------inside controller---------");
		return adao.getStatusChange(change);
	}

	public List<CashAssetMB> getCashAsset(Date fDate, Date tDate)
			throws DemoException {
		List<CashAssetMB> ca = new ArrayList<CashAssetMB>();
		List<Transaction> trans = null;
		List<CashAsset> cad = null;
		try {
			trans = ardao.getCashAsset(fDate, tDate);
			if (trans.size() > 0) {
				for (int i = 0; i < trans.size(); i++) {
					cad = ardao.getCashAssetInfo(trans.get(i)
							.getTransactionId());
					if (cad.size() > 0) {
						SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						CashAssetMB si = new CashAssetMB();
						si.setAmount(cad.get(0).getTransaction().getAmount());
						si.setReason("Transaction");
						si.setsDate(df.format(cad.get(0).getTransaction()
								.getPayDate()));
						si.setClientName(cad.get(0).getTransaction()
								.getParticulars());
						ca.add(si);
					}

				}
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return ca;
	}

	public List<PayrollLiablityMB> getPayrollLiability(Date fDate, Date tDate)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<PayrollLiablityMB> pay = new ArrayList<PayrollLiablityMB>();
		List<Payroll> payroll = null;
		List<String> pD = null;
		List<String> dt = new ArrayList<String>();
		try {
			logger.debug("11111111111111");
			pD = ardao.getpayrollDates(fDate, tDate);
			logger.debug("22222222222222");
			logger.debug("sizes>>>>>>>>>>>>>>" + pD.size());
			HashSet<String> set = new HashSet<String>();
			for (String item : pD) {
				logger.debug("1");
				if (!set.contains(item)) {
					logger.debug("11");
					dt.add(item);
					set.add(item);
				}
			}
			logger.debug("Size of set1>>>>>>>>>>>>>>>" + set.size());
			logger.debug("Size of set list1>>>>>>>>>>>>>>>" + dt.size());
			//float amt = 0;
			if (dt.size() > 0) {
				for (int i = 0; i < dt.size(); i++) {
					PayrollLiablityMB si = new PayrollLiablityMB();
					payroll = ardao.getPayrollDate(dt.get(i));
					logger.debug("Size sales>>>>>>>>>>>>>>>>>>>>>>"
							+ payroll.size());
					logger.debug("Size of before>>>>>>>>>>>>>>" + pay.size());
					if (payroll.size() > 0) {
						for (int j = 0; j < payroll.size(); j++) {
							if(clientID.equals(payroll.get(j).getEmployee().getClient_ID())){
								si.setAmount(payroll.get(j).getTotalSalary());
								si.setsDate(payroll.get(j).getPayrollDate());
								si.setEmployeeName(payroll.get(j).getEmployee()
										.getEmployeeName());
								si.setPayrollNo(payroll.get(j).getPayrollNumber());
								pay.add(si);
							}							
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return pay;
	}

	
	public String employeeIdSearch(EmployeeDetail empid) throws DemoException {
		return adao.employeeIdSearch(empid);
	}

	public List<String> designationInfo(List<String> designate)
			throws DemoException {
		logger.debug("inside bo------>>>");
		return adao.designationInfo(designate);
	}

	public List<Qualification> qualificationInfo(List<Qualification> qualificate)
			throws DemoException {
		logger.debug("inside bo------>>>");
		return adao.qualificationInfo(qualificate);
	}

	public String employee(EmployeeDetail employee) throws DemoException {
		logger.debug("inside bo------>>>");
		return adao.employee(employee);
	}

	public String employeeIdInfo(EmployeeDetail employee) throws DemoException {
		logger.debug("inside bo------>>>");
		return adao.employeeIdInfo(employee);
	}

	public String employeeNameInfo(EmployeeDetail employee)
			throws DemoException {
		logger.debug("inside bo------>>>");
		return adao.employeeNameInfo(employee);
	}

	@Override
	public List<Employee> employeeNameInfo(EmployeeDetail employee,
			String golbalnamesearch) throws DemoException {
		return adao.employeeNameInfo(employee, golbalnamesearch);
	}

	public List<String> employeeId(List<String> emploeid) throws DemoException {
		logger.debug("inside bo------>>>");
		return adao.employeeId(emploeid);
	}

	public List<String> employeeName(List<String> emploename)
			throws DemoException {
		logger.debug("inside bo------>>>");
		return adao.employeeName(emploename);
	}

	public List<EmployeeDetail> getEmployeeDetail(EmployeeDetail employee)
			throws DemoException {
		logger.debug("inside bo------>>>");
		return adao.getEmployeeDetail(employee);
	}

	public List<EmployeeDetail> getEmployeeDetailEdit(EmployeeDetail employee)
			throws DemoException {
		logger.debug("inside bo------>>>");
		return adao.getEmployeeDetailEdit(employee);
	}

	@Override
	public List<Employee> empInfo(List<Employee> empinfo) throws DemoException {

		return adao.empInfo(empinfo);
	}

	@Override
	public List<Employee> nameInfo(List<Employee> nameinfo)
			throws DemoException {

		return adao.nameInfo(nameinfo);
	}

	@Override
	public String employeeDetails(EmployeeDetail employee) throws DemoException {

		return adao.employeeDetails(employee);
	}

	public List<Month> monthInfo(List<Month> monthinfo) throws DemoException {
		logger.debug("inside controller------->>>");
		return adao.monthInfo(monthinfo);
	}

	public List<Year> yearInfo(List<Year> yearinfo) throws DemoException {
		logger.debug("inside controller------->>>");
		return adao.yearInfo(yearinfo);
	}

	public ArrayList<String> changeEvent(String s, ArrayList<String> list1)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return adao.changeEvent(s, list1);
	}

	public ArrayList<String> changeEvent1(String s, ArrayList<String> list2)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return adao.changeEvent1(s, list2);
	}

	public List<Employee> payroll(List<Employee> list3, EmployeePayroll save)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return adao.payroll(list3, save);
	}

	public List<Employee> payrollemp(List<Employee> list3, EmployeePayroll save)
			throws DemoException {
		logger.debug("inside controller------->>>");
		return adao.payrollemp(list3, save);
	}

	public String confirm(EmployeePayroll save) throws DemoException {
		logger.debug("inside controller------->>>");
		return adao.confirm(save);
	}

	public ArrayList<String> changeEvent2(String s, ArrayList<String> list1)
			throws DemoException {

		return adao.changeEvent2(s, list1);
		// return null;
	}

	public String payroll1(EmployeePayroll pay) throws DemoException {

		return adao.payroll1(pay);
	}

	public String search1(EmployeePayroll pay) throws DemoException {

		return adao.search1(pay);
	}

	public String search2(EmployeePayroll pay) throws DemoException {

		return adao.search2(pay);
	}

	public List<EmployeePayroll> viewPayrollz(EmployeePayroll view)
			throws DemoException {
		return adao.viewPayrollz(view);
	}

	public List<EmployeePayroll> editPayroll(EmployeePayroll view)
			throws DemoException {
		return adao.editPayroll(view);
	}

	public List<EmployeePayroll> viewPayroll(EmployeePayroll view)
			throws DemoException {
		return adao.viewPayroll(view);
	}

	public ArrayList<String> changezEvent(String s, ArrayList<String> list1)
			throws DemoException {
		return adao.changezEvent(s, list1);
	}

	public ArrayList<String> changeEvent3(String s, ArrayList<String> list2)
			throws DemoException {
		return adao.changeEvent3(s, list2);
	}

	public String payroll(EmployeePayroll pay) throws DemoException {
		return adao.payroll(pay);
	}

	public List<ProductRegister> getProductInInfo(Date startDate, Date endDate)
			throws DemoException {
		List<ProductRegister> nonsaleproduct = null;
		List<I0001> product = null;
		List<I0018> batch = null;
		List<I0019> bar = null;
		List<I0017> stock = null;
		try {
			logger.debug("inside BO");
			nonsaleproduct = new ArrayList<ProductRegister>();

			product = dao.getProductInfo();
			if (product.size() > 0) {
				for (int i = 0; i < product.size(); i++) {
					logger.debug("p" + i);
					batch = dao.getBatchInfo(product.get(i).getProductName());
					if (batch.size() > 0) {

						stock = dao.getStock(batch.get(0).getI0017()
								.getStock_ID(), startDate, endDate);
						logger.debug("Stock Size" + stock);
						if (stock.size() > 0) {
							logger.debug("p" + i);
							bar = dao
									.getBarcodeInfo(batch.get(0).getBatch_ID());
							logger.debug("1");
							if (bar.size() > 0) {
								logger.debug("p" + i);
								nonsaleproduct.add(dao.getProductData(batch
										.get(0).getBatch_ID()));
								logger.debug("p" + nonsaleproduct);
							} else {
								logger.debug("No data in bar>>>>>>>>>>");
							}
						}

						else {
							logger.debug("No data in Stock>>>>>>>>>>");
						}

					} else {
						logger.debug("No data in batch");
					}
				}

				logger.debug("Size of Stock" + nonsaleproduct.size());
			} else {
				logger.debug("No data in product");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return nonsaleproduct;
	}

	public int productInfo(Date startDate, Date endDate) throws DemoException {
		int count = 0;
		List<I0001> product = null;
		List<I0018> batch = null;
		List<I0019> bar = null;
		List<I0017> stock = null;

		try {
			logger.debug("Inside BOImpl");
			product = dao.getProductInfo();
			logger.debug("Size of product" + product.size());
			if (product.size() > 0) {
				for (int i = 0; i < product.size(); i++) {
					batch = dao.getBatchInfo(product.get(i).getProductName());
					logger.debug("Size of Batch" + batch.size());
					if (batch.size() > 0) {

						stock = dao.getStock(batch.get(0).getI0017()
								.getStock_ID(), startDate, endDate);
						logger.debug("Stock Size" + stock);
						if (stock.size() > 0) {
							bar = dao
									.getBarcodeInfo(batch.get(0).getBatch_ID());
							logger.debug("Size of bar" + bar.size());
							if (bar.size() > 0) {

								count++;
								logger.debug("count" + count);
							} else {

								logger.debug("No data in Stock");
							}

						} else {
							logger.debug("No data in batch");
						}
					}
				}
			} else {

				logger.debug("No data in product");
			}

		} catch (Exception e) {

		}

		return count;
	}

	public int productInfo1() throws DemoException {
		int size = 0;
		List<Integer> prodids = null;
		try {
			List<Integer> baridList = new ArrayList<Integer>();
			List<Integer> batidList = new ArrayList<Integer>();

			List<I0021> product = null;
			PurchaseOrder po = new PurchaseOrder();

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar today = Calendar.getInstance();
			Calendar from = Calendar.getInstance();
			Calendar to = Calendar.getInstance();
			today.add(Calendar.DATE, 0);
			logger.debug("------date-----" + sdf.format(today.getTime()));
			int cals = today.get(Calendar.DAY_OF_WEEK);
			logger.debug("~~print cals~~~~" + cals);

			Date d = null;
			Date d1 = null;
			String s = "";
			String s1 = "";

			Calendar now = Calendar.getInstance();
			logger.debug("-->> date 2 " + sdf.format(now.getTime()));
			s1 = sdf.format(now.getTime());
			now = Calendar.getInstance();
			now.add(Calendar.DATE, -19);
			logger.debug("-->> date 1 " + sdf.format(now.getTime()));
			s = sdf.format(now.getTime());

			d = sdf.parse(s);
			d1 = sdf.parse(s1);
			po.setFromDate(d);
			po.setToDate(d1);
			product = dao.getProductInf(po.getFromDate(), po.getToDate());
			logger.debug("~~~dddddddd~~~~~~" + d + "~~~~~~d111111~~~~" + d1);
			if (product.size() > 0) {
				List<SalesRecord> salesrec = null;
				logger.debug("~~~product~~~4~~~~");
				for (int i = 0; i < product.size(); i++) {
					int salesidd = product.get(i).getSales_ID();
					salesrec = dao.getProductInfo2(salesidd);
					if (salesrec.size() > 0) {
						for (int k = 0; k < salesrec.size(); k++) {
							logger.debug("~~~bar id~~~~"
									+ salesrec.get(k).getI0019()
											.getBar_code_ID());
							int barid = salesrec.get(k).getI0019()
									.getBar_code_ID();
							baridList.add(barid);
						}

					}
					logger.debug("-----barlit=-------" + baridList);
				}
				logger.debug("-----barlit=-for------" + baridList);
				HashSet<Integer> hasbar = new HashSet<Integer>(baridList);
				logger.debug("~~~has bar~~~~" + hasbar);
				for (Integer j : hasbar) {
					logger.debug("~~~has bar~~~~" + j);
					List<I0019> batchids = null;
					batchids = dao.getbatchdemo(j);
					if (batchids.size() > 0) {
						logger.debug("~~~bat id~~~~"
								+ batchids.get(0).getI0018().getBatch_ID());
						int battid = batchids.get(0).getI0018().getBatch_ID();
						batidList.add(battid);
					}
					HashSet<Integer> hasbat = new HashSet<Integer>(batidList);
					logger.debug("-----batchlit=-------" + hasbat);
					if (hasbat.size() > 0) {

						prodids = dao.getproductsname();
						logger.debug("~~~list~~~~~~~" + prodids.size());
						if (prodids.size() > 0) {

							logger.debug("~~~~~final~~~~~"
									+ prodids.removeAll(hasbat));
						}

						size = prodids.size();
						logger.debug("~~~size~~~" + size);

						logger.debug("~~~list of 18~~~~" + po.getI0018list());
					}

				}

			} else {

				prodids = dao.getproductsname();
				logger.debug("~~~list~~~~~~~" + prodids.size());

				size = prodids.size();
				logger.debug("~~~size~~~" + size);

				logger.debug("~~~list of 18~~~~" + po.getI0018list());
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return size;

	}

	@Override
	public ArrayList<String> productnonsales() throws DemoException {
		int size = 0;
		List<Integer> prodids = null;

		ArrayList<String> addproduct = new ArrayList<String>();

		try {
			List<Integer> baridList = new ArrayList<Integer>();
			List<Integer> batidList = new ArrayList<Integer>();

			List<I0021> product = null;
			PurchaseOrder po = new PurchaseOrder();

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar today = Calendar.getInstance();
			Calendar from = Calendar.getInstance();
			Calendar to = Calendar.getInstance();
			today.add(Calendar.DATE, 0);
			logger.debug("------date-----" + sdf.format(today.getTime()));
			int cals = today.get(Calendar.DAY_OF_WEEK);
			logger.debug("~~print cals~~~~" + cals);
			Date d = null;
			Date d1 = null;
			String s = "";
			String s1 = "";
			Calendar now = Calendar.getInstance();
			logger.debug("-->> date 2 " + sdf.format(now.getTime()));
			s1 = sdf.format(now.getTime());
			now = Calendar.getInstance();
			now.add(Calendar.DATE, -19);
			logger.debug("-->> date 1 " + sdf.format(now.getTime()));
			s = sdf.format(now.getTime());

			d = sdf.parse(s);
			d1 = sdf.parse(s1);

			po.setFromDate(d);
			po.setToDate(d1);
			product = dao.getProductInf(po.getFromDate(), po.getToDate());
			logger.debug("~~~dddddddd~~~~~~" + d + "~~~~~~d111111~~~~" + d1);
			if (product.size() > 0) {
				List<SalesRecord> salesrec = null;
				logger.debug("~~~product~~~4~~~~");
				for (int i = 0; i < product.size(); i++) {
					int salesidd = product.get(i).getSales_ID();
					salesrec = dao.getProductInfo2(salesidd);
					if (salesrec.size() > 0) {
						for (int k = 0; k < salesrec.size(); k++) {
							logger.debug("~~~bar id~~~~"
									+ salesrec.get(k).getI0019()
											.getBar_code_ID());
							int barid = salesrec.get(k).getI0019()
									.getBar_code_ID();
							baridList.add(barid);
						}

					}
					logger.debug("-----barlit=-------" + baridList);
				}
				logger.debug("-----barlit=-for------" + baridList);
				HashSet<Integer> hasbar = new HashSet<Integer>(baridList);
				logger.debug("~~~has bar~~~~" + hasbar);
				for (Integer j : hasbar) {
					logger.debug("~~~has bar~~~~" + j);
					List<I0019> batchids = null;
					batchids = dao.getbatchdemo(j);
					if (batchids.size() > 0) {
						logger.debug("~~~bat id~~~~"
								+ batchids.get(0).getI0018().getBatch_ID());
						int battid = batchids.get(0).getI0018().getBatch_ID();
						batidList.add(battid);
					}
					HashSet<Integer> hasbat = new HashSet<Integer>(batidList);
					logger.debug("-----batchlit=-------" + hasbat);
					if (hasbat.size() > 0) {

						prodids = dao.getproductsname();
						logger.debug("~~~list~~~~~~~" + prodids.size());
						if (prodids.size() > 0) {
							logger.debug("~~~~~final~~~~~"
									+ prodids.removeAll(hasbat));
						}

						size = prodids.size();

						logger.debug("~~~size~~~" + size);

						logger.debug("~~~list of 18~~~~" + po.getI0018list());
					}

				}

			} else {

				prodids = dao.getproductsname();
				logger.debug("~~~list~~~~~~~" + prodids.size());

				size = prodids.size();
				logger.debug("~~~size~~~" + size);

				logger.debug("~~~list of 18~~~~" + po.getI0018list());
			}
			int count = 0;

			for (Integer g : prodids) {

				logger.debug("~~~pos~~~" + g);
				ArrayList<String> names = dao.productnonsales(po, g);
				logger.debug("~~~list~~of~~non~~~");
				if (names.size() > 0) {
					addproduct.addAll(names);
					count++;
				} else {
					throw new Exception("*NO Records Found");
				}
				logger.debug("~~serial~~~" + count);
			}
			logger.debug("~~add pro~~~" + addproduct.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return addproduct;

	}

	public List<I0001> dropdownproduct1() throws DemoException {
		return dao.dropdownproduct1();
	}

	public List<String> categorylist1(List<String> categorytype)
			throws DemoException {
		return dao.categorylist1(categorytype);
	}

	public String purchaseReturnValuechangeDrop1(String s,
			PurchaseOrder purchaseOrder) throws DemoException {
		dao.purchaseReturnValuechangeDrop1(s, purchaseOrder);
		return "";
	}

	public String purchaseRetViewForm(PurchaseOrder pur) throws DemoException {
		return pdao.purchaseRetViewForm(pur);
	}

	public String purOrderViewproduct(PurchaseOrder purchaseOrder)
			throws DemoException {
		return pdao.purOrderViewproduct(purchaseOrder);
	}

	public String purchReturnSubmit(PurchaseOrder purchaseOrder)
			throws DemoException {

		return null;
	}

	/* Arun */

	public ArrayList<I0032> catogerycustomer(ArrayList<I0032> buyername,
			PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("arun");
		qdao.catogerycustomer(buyername, purchaseOrder);
		return null;
	}

	public List<String> customername(Commission commission)
			throws DemoException {

		return qdao.customername(commission);
	}

	public String commissionview(Commission commission) throws DemoException {
		qdao.commissionview(commission);
		return null;
	}

	/* Siva */

	public ArrayList<I0032> customerNameChange1(String s) throws DemoException {
		return dao.customerNameChange1(s);
	}

	public String salesOrder1(PurchaseOrder purchaseOrder) throws DemoException {
		dao.salesOrder1(purchaseOrder);
		ArrayList<I0032> dump = null;
		Buyer b = new Buyer();
		b.setPhoneNumber(purchaseOrder.getPhonenumber());
		ArrayList<I0032> dummy = dao.salesOrder(dump, b);
		purchaseOrder.setDummyId(dummy.get(0).getBuyer_ID());
		dao.salesOrder3(purchaseOrder);
		dao.salesOrder2(purchaseOrder);
		return null;
	}

	public String viewPurchaseReturnDetail(PurchaseOrder purchaseOrder)
			throws DemoException {
		return dao.viewPurchaseReturnDetail(purchaseOrder);
	}

	public String viewPurchaseReturn(PurchaseOrder purchaseOrder)
			throws DemoException {
		return dao.viewPurchaseReturn(purchaseOrder);

	}

	public String purchaseReturnInsert(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.purchaseReturnInsert(purchaseOrder);
		dao.purchaseReturnInsert1(purchaseOrder);
		return "";
	}

	public List<Commission> viewCommission(Commission c) throws DemoException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		List<Commission> comissionData = new ArrayList<Commission>();
		List<I0032> buyersList = null;
		BigDecimal totlsales = BigDecimal.valueOf(0);
		BigDecimal totalamount = BigDecimal.valueOf(0);
		List<SalesRecord> salesQty = null;

		try {
			logger.debug("Category-------------->" + c.getCategory());
			String ss = c.getCategory();
			logger.debug("==value===" + ss);

			List<I0021> salesList = new ArrayList<I0021>();
			List<I0018> salesList1 = new ArrayList<I0018>();

			int count = 0;
			salesList = dao.getSalesData1(c.getFromdate(), c.getTodate());
			if (salesList.size() > 0) {

				for (int i = 0; i < salesList.size(); i++) {
					logger.debug("------------->"
							+ salesList.get(i).getSales_ID());

					logger.debug("==amount==="
							+ new BigDecimal(salesList.get(i).getCrossTotal()));
					totlsales = totlsales.add(new BigDecimal(salesList.get(i)
							.getCrossTotal()));
					logger.debug("~~~~~~~cross~~~~~~" + totlsales);
					salesQty = dao.getSalesQuantityData(salesList.get(i)
							.getSales_ID());

					logger.debug("------------->" + salesQty.size());
					if (salesQty.size() > 0) {

						for (int j = 0; j < salesQty.size(); j++)

						{

							logger.debug("Quantity>>>>" + salesQty.size());
							Commission cc = new Commission();
							count = count + 1;
							cc.setsNo("" + (count));
							cc.setOrderdate(""
									+ df.format(salesList.get(i)
											.getSalesOrderDate()));
							cc.setProductname(salesQty.get(j).getI0019()
									.getI0018().getProductName());
							cc.setUnitprice(new BigDecimal(salesQty.get(j)
									.getSell_price()));
							cc.setRollids(salesQty.get(j).getI0019()
									.getRoll_ID());
							logger.debug("===name==="
									+ salesQty.get(j).getI0019().getRoll_ID());
							logger.debug("===prod name==="
									+ salesQty.get(j).getI0019().getI0018()
											.getProductName());
							cc.setInvoiceno(salesList.get(i)
									.getSalesOrderNumber());
							cc.setCname(salesList.get(i).getCustomerName());

							logger.debug("*****total******sales***" + totlsales);
							cc.setTotlamt(new BigDecimal(salesList.get(i)
									.getCrossTotal()));
							logger.debug("==quantity==="
									+ new BigDecimal(salesQty.get(j)
											.getSoldQuantity()));
							cc.setTotlquan(new BigDecimal(salesQty.get(j)
									.getSoldQuantity()));

							cc.setTotlsales1(totlsales);

							logger.debug("barcode id==="
									+ salesQty.get(j).getI0019()
											.getBar_code_ID());
							if (salesQty.get(j).getCommAmt() == null
									|| salesQty.get(j).getCommAmt()
											.equalsIgnoreCase("")) {

								cc.setCmst(BigDecimal.ZERO);
								cc.setTotcms(BigDecimal.ZERO);
								logger.debug("=====");
								cc.setFlag1("none");
								cc.setFlag("1");
							} else {

								logger.debug("==comm==amt==="
										+ salesQty.get(j).getCommAmt()
										+ "==total==amm==="
										+ salesQty.get(j).getTotcommAmt());
								cc.setCmst(new BigDecimal(salesQty.get(j)
										.getCommAmt()));
								cc.setTotcms(new BigDecimal(salesQty.get(j)
										.getTotcmsion()));

								cc.setTotalSaleAmount(""
										+ salesList.get(i).getCrossTotal());
								cc.setQuantity(salesQty.get(j)
										.getSoldQuantity());
								totalamount = totalamount.add(new BigDecimal(
										salesQty.get(j).getTotcmsion()));
								logger.debug("total~~~~~~~comm~~~~"
										+ totalamount);

								cc.setTotalamount1(totalamount);
								logger.debug("~~~~~~~~~~~"
										+ cc.getTotalamount1());

							}
							comissionData.add(cc);

							try {
								if (new BigDecimal(salesQty.get(j).getCommAmt())
										.compareTo(BigDecimal.valueOf(0)) == 1) {
									cc.setFlag1("1");
									cc.setFlag("none");
								} else {
									cc.setFlag("1");
									cc.setFlag1("none");
								}

							} catch (Exception e) {

							}

						}
					}

					else {
						logger.debug("No stock>>>>>>>>");
					}

				}
			}
			c.setTotalamount1(totalamount);
			c.setTotlsales1(totlsales);
			logger.debug("sizeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
					+ salesList.size());

		}

		catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return comissionData;
	}

	public String commisionUpdate(Commission c) throws DemoException {
		List<I0021> salesList = null;
		try {
			salesList = dao.getSalesDatafromNumber(c.getInvoiceno());
			if (salesList.size() > 0) {
				dao.commisionUpdate(salesList.get(0).getSales_ID(), c);
			} else {
				logger.debug("No sales found");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public String purchaseInfocollect(PurchaseOrder purchaseOrder)
			throws DemoException {
		return pdao.purchaseInfocollect(purchaseOrder);
	}

	public String findCashBook(PurchaseOrder purchaseOrder)
			throws DemoException {
		purchaseOrder.setCounter(0);
		purchaseOrder.cashBookList.clear();
		dao.findCashBook(purchaseOrder);
		logger.debug("list final size--------------->"
				+ purchaseOrder.cashBookList.size());
		return null;
	}

	public List<SalesIncomeMB> getAccountReceivableSales(Date fDate, Date tDate)
			throws DemoException {
		List<SalesIncomeMB> salesIncome = new ArrayList<SalesIncomeMB>();
		List<Transaction> tran = null;
		List<String> salesD = null;
		List<String> salesD1 = null;
		List<I0021> sales = null;
		List<I0021> sales1 = null;
		List<String> dt = new ArrayList<String>();
		List<String> dt1 = new ArrayList<String>();
		List<AccReceivableAsset> ara = null;
		List<Integer> idList = new ArrayList<Integer>();
		List<Integer> inIdList = new ArrayList<Integer>();
		List<I0022> iList = null;
		List<I0023> ipList = null;
		try {
			salesD = ardao.getSalesDates(fDate, tDate);
			logger.debug("sizes>>>>>>>>>>>>>>" + salesD.size());
			HashSet<String> set = new HashSet<String>();
			for (String item : salesD) {
				logger.debug("1");
				if (!set.contains(item)) {
					logger.debug("11");
					dt.add(item);
					set.add(item);
				}
			}
			logger.debug("Size of set>>>>>>>>>>>>>>>" + set.size());
			logger.debug("Size of set list>>>>>>>>>>>>>>>" + dt.size());
			float amt = 0;
			for (int i = 0; i < dt.size(); i++) {
				SalesIncomeMB si = new SalesIncomeMB();
				logger.debug("" + i);
				logger.debug("Date>>>>>>>>>>>>>>>>>>>>>>>>>>" + dt.get(i));
				sales = ardao.getSalesAsset(dt.get(i));
				logger.debug("Size sales>>>>>>>>>>>>>>>>>>>>>>" + sales.size());
				logger.debug("Size of before>>>>>>>>>>>>>>"
						+ salesIncome.size());
				if (sales.size() > 0) {
					for (int j = 0; j < sales.size(); j++) {
						idList.add(sales.get(j).getSales_ID());
					}

				} else {
					logger.debug("No data in sales>>>>>>>>>>>>>>>>>>");
				}
			}
			if (idList.size() > 0) {
				logger.debug("Sales table list size>>>>>>>>>>>>>>>>>" + idList);
				logger.debug("1");
				for (int k = 0; k < idList.size(); k++) {
					logger.debug("2");
					iList = ardao.getInvicesData(idList.get(k));
					logger.debug("3");
					if (iList.size() > 0) {
						logger.debug("4");
						ipList = ardao.getInvoicePaymentPurchaseData11(iList
								.get(0).getInvoice_ID());
						logger.debug("5");

						if (ipList.size() > 0) {
							logger.debug("6");
							SalesIncomeMB si = new SalesIncomeMB();
							si.setAmount(ipList.get(0).getBalanceAmount());
							si.setReason("sales");
							si.setsDate(""
									+ ipList.get(0).getI0022().getI0021()
											.getSalesOrderDate());
							si.settDate(ipList.get(0).getI0022().getI0021()
									.getSalesOrderDate());
							si.setClientName(ipList.get(0).getI0022()
									.getI0021().getCustomerName());
							salesIncome.add(si);
							logger.debug("7");
						} else {
							logger.debug("-->> bo 1");
							ipList = ardao.getInvoicePaymentPurchaseData2(iList
									.get(0).getInvoice_ID());
							if (ipList.size() > 0) {
								logger.debug("-->> bo 2");
							} else {
								logger.debug("-->> bo 3");
								logger.debug("-->> i0022 id :  "
										+ iList.get(0).getInvoice_ID());
								SalesIncomeMB si = new SalesIncomeMB();
								si.setAmount(iList.get(0).getI0021()
										.getCrossTotal());
								si.setReason("sales");
								si.setsDate(""
										+ iList.get(0).getI0021()
												.getSalesOrderDate());
								si.settDate(iList.get(0).getI0021()
										.getSalesOrderDate());
								si.setClientName(iList.get(0).getI0021()
										.getCustomerName());
								salesIncome.add(si);
							}
							logger.debug("No sales Payment>>>>>>>>");
						}

					} else {
						logger.debug("No data in invoices>>>>>>>>>>>");
					}
				}
			} else {
				logger.debug("No data in isales1>>>>>>>>>>>>>>>>");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}

		return salesIncome;
	}

	public List<PurchaseExpenseMB> getPurchaseExpences(Date fDate, Date tDate)
			throws DemoException {
		List<PurchaseExpenseMB> purchaseExpense = null;
		List<Transaction> tran = null;
		List<Date> pD = null;
		List<Date> rpD = null;
		List<I0015> purchase = null;
		List<Date> dt = new ArrayList<Date>();
		List<AccPayableLiability> apyl = null;
		List<Integer> idList = new ArrayList<Integer>();
		List<Integer> inIdList = new ArrayList<Integer>();
		List<I0022> iList = null;
		List<I0023> ipList = null;
		List<PurchaseReturn> prlist = null;
		try {

			pD = ardao.getpurchaseOrderDatefromPurchase(fDate, tDate);
			logger.debug("sizes>>>>>>>>>>>>>>" + pD.size());
			HashSet<Date> set = new HashSet<Date>();
			for (Date item : pD) {
				logger.debug("1");
				if (!set.contains(item)) {
					logger.debug("11");
					dt.add(item);
					set.add(item);
				}
			}
			logger.debug("Size of set1>>>>>>>>>>>>>>>" + set.size());
			logger.debug("Size of set list1>>>>>>>>>>>>>>>" + dt.size());
			for (int i = 0; i < dt.size(); i++) {
				PurchaseExpenseMB si = new PurchaseExpenseMB();
				logger.debug("" + i);
				logger.debug("Date>>>>>>>>>>>>>>>>>>>>>>>>>>" + dt.get(i));
				purchase = ardao.getPurchasesforAsset(dt.get(i));
				logger.debug("Size sales>>>>>>>>>>>>>>>>>>>>>>"
						+ purchase.size());
				if (purchase.size() > 0) {
					for (int j = 0; j < purchase.size(); j++) {
						idList.add(purchase.get(j).getPurchase_ID());
					}

				} else {
					logger.debug("No data in sales>>>>>>>>>>>>>>>>>>");
				}
			}
			logger.debug("Size2>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + idList.size());
			if (idList.size() > 0) {
				purchaseExpense = new ArrayList<PurchaseExpenseMB>();
				logger.debug("purchase table list size>>>>>>>>>>>>>>>>>"
						+ idList);
				logger.debug("1");
				for (int k = 0; k < idList.size(); k++) {
					logger.debug("2");
					BigDecimal rq = BigDecimal.valueOf(0);
					BigDecimal aprice = BigDecimal.valueOf(0);
					logger.debug("purchase id  - " + idList.get(k));

					iList = ardao.getInvicesPurchaseData(idList.get(k));
					logger.debug("3");
					if (iList.size() > 0) {
						logger.debug("4");
						ipList = ardao.getInvoicePaymentPurchaseData11(iList
								.get(0).getInvoice_ID());
						logger.debug("5~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
								+ ipList.size());

						if (ipList.size() > 0) {
							logger.debug("6");
							PurchaseExpenseMB si = new PurchaseExpenseMB();
							si.setAmount(""
									+ new BigDecimal(ipList.get(0)
											.getBalanceAmount()));
							si.setsDate(""
									+ ipList.get(0).getI0022().getI0015()
											.getOrderDate());
							si.settDate(ipList.get(0).getI0022().getI0015()
									.getOrderDate());
							logger.debug(">>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<"
									+ ipList.get(0).getI0022().getInvoice_ID());
							si.setClientName(ardao
									.getInvoicePaymentPurchaseData1(ipList
											.get(0).getI0022().getInvoice_ID()));
							logger.debug("<<<<<<<<<<<<>>>>>>>>>>>>>"
									+ si.clientName);
							si.setReason("purchase");
							purchaseExpense.add(si);
							logger.debug("7");
						} else {
							logger.debug("No sales Payment>>>>>>>>");
							logger.debug("-->> bo 1 ");
							ipList = ardao.getInvoicePaymentPurchaseData2(iList
									.get(0).getInvoice_ID());
							if (ipList.size() > 0) {
								logger.debug("-->> bo 2 ");
								logger.debug("no data");
							} else {
								logger.debug("-->> bo 3 ");
								PurchaseExpenseMB si = new PurchaseExpenseMB();
								si.setAmount(iList.get(0).getI0015()
										.getTotalPrice());
								si.setsDate(""
										+ iList.get(0).getI0015()
												.getOrderDate());
								si.settDate(iList.get(0).getI0015()
										.getOrderDate());
								si.setClientName(ardao
										.getInvoicePaymentPurchaseData12(iList
												.get(0).getI0015()
												.getPurchase_ID()));
								si.setReason("purchase");
								purchaseExpense.add(si);
							}
						}

					} else {

						logger.debug("No data in invoices>>>>>>>>>>>");
					}
				}
			} else {
				logger.debug("No data in isales1>>>>>>>>>>>>>>>>");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}

		return purchaseExpense;
	}

	public List<SalesIncomeMB> getAccountReceivableSales1(Date fDate, Date tDate)
			throws DemoException {
		List<SalesIncomeMB> salesIncome = new ArrayList<SalesIncomeMB>();
		List<Transaction> tran = null;
		List<String> salesD = null;
		List<String> salesD1 = null;
		List<I0021> sales = null;
		List<I0021> sales1 = null;
		List<String> dt = new ArrayList<String>();
		List<String> dt1 = new ArrayList<String>();
		List<AccReceivableAsset> ara = null;
		List<Integer> idList = new ArrayList<Integer>();
		List<Integer> inIdList = new ArrayList<Integer>();
		List<I0022> iList = null;
		List<I0023> ipList = null;
		List<SalesReturn> salesRtrn = null;
		List<SalesReturn> NsalesRtrn = null;

		try {
			// method 111111111111111111111111111111111

			// method3333333333333333333333333333
			salesD1 = ardao.getSalesDates1(fDate, tDate);
			logger.debug("sizes>>>>>>4247>>>>>>>>" + salesD1.size());
			HashSet<String> set1 = new HashSet<String>();
			for (String item : salesD1) {
				logger.debug("1");
				if (!set1.contains(item)) {
					logger.debug("11");
					dt1.add(item);
					set1.add(item);
				}
			}
			logger.debug("Size of set>>>>>>>4259>>>>>>>>" + set1.size());
			logger.debug("Size of set list>>>>>>>>>>>>>>>" + dt1.size());
			for (int i = 0; i < dt1.size(); i++) {
				SalesIncomeMB si = new SalesIncomeMB();
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				logger.debug("" + i);
				logger.debug("Date order number>>>>>>>>>>>>>4266>>>>>>>>>>>>>"
						+ dt1.get(i));

				//
				salesRtrn = ardao.quicksalereturn(dt1.get(i));
				logger.debug("res--" + salesRtrn.size());

				if (salesRtrn.size() > 0) {
					BigDecimal dr = new BigDecimal("0"), nr = new BigDecimal(
							"0"), retun_qty = new BigDecimal("0"), sell_price = new BigDecimal(
							"0");
					String discount = "0", type = "";
					BigDecimal amount = new BigDecimal("0"), temp = new BigDecimal(
							"0"), temp1 = new BigDecimal("0"), soldQTY = new BigDecimal(
							"0");
					BigDecimal totalprice = BigDecimal.valueOf(0);
					for (int m = 0; m <= salesRtrn.size(); m++) {
						try {
							dr = dr.add(new BigDecimal(salesRtrn.get(m).getDr()));
							logger.debug("dr" + dr);
							nr = nr.add(new BigDecimal(salesRtrn.get(m).getNr()));
							logger.debug("nr" + nr);
							discount = salesRtrn.get(m).getSalesRecord()
									.getDiscount();
							logger.debug("discount" + discount);
							sell_price = new BigDecimal(salesRtrn.get(m)
									.getSalesRecord().getSell_price());
							logger.debug("sell_price" + sell_price);
							type = salesRtrn.get(m).getSalesRecord()
									.getDiscountType();
							logger.debug("type" + type);
							soldQTY = new BigDecimal(salesRtrn.get(m)
									.getSalesRecord().getSoldQuantity());
							logger.debug("soldQTY" + soldQTY);
							retun_qty = dr.add(nr);
							logger.debug("retun_qty" + retun_qty);
							if (discount == null) {
								discount = "0";
								amount = new BigDecimal("0");
								logger.debug("amount" + amount);
							} else if (type.equals("%")) {
								logger.debug("%");
								amount = sell_price.multiply(new BigDecimal(
										discount).divide(BigDecimal
										.valueOf(100)));
								logger.debug("amount" + amount);
							} else if (type.equals("IDR")) {
								logger.debug("IDR");
								temp = new BigDecimal(discount).divide(soldQTY);
								logger.debug("temp" + temp);
								amount = retun_qty.multiply(temp);
								logger.debug("amount" + amount);
							} else if (type.equals("") || type == null) {
								logger.debug("empty");
								amount = new BigDecimal("0");
								logger.debug("amount" + amount);
							}

							temp1 = (sell_price.multiply(retun_qty))
									.subtract(amount);
							logger.debug("temp1" + temp1);
							totalprice = temp1;
							logger.debug("totalprice" + totalprice);

						} catch (IndexOutOfBoundsException e) {
							logger.debug("Inside Exception");
						}
					}
					sales1 = ardao.getSalesAsset1(dt1.get(i));
					if (sales1.size() > 0) {
						for (int j = 0; j < sales1.size(); j++) {
							logger.debug("totalprice" + totalprice);
							logger.debug("getCrossTotal"
									+ sales1.get(0).getCrossTotal());
							logger.debug("Subtract"
									+ new BigDecimal(sales1.get(0)
											.getCrossTotal())
											.subtract(totalprice));
							si.setAmount(""
									+ (new BigDecimal(sales1.get(0)
											.getCrossTotal())
											.subtract(totalprice)));
							si.setReason("QuickSales");
							si.setsDate(df.format(sales1.get(j)
									.getSalesOrderDate()));
							si.setClientName(sales1.get(j).getCustomerName());
							salesIncome.add(si);
						}
					}
				} else {
					sales1 = ardao.getSalesAsset1(dt1.get(i));
					if (sales1.size() > 0) {
						for (int j = 0; j < sales1.size(); j++) {
							si.setAmount(sales1.get(j).getCrossTotal());
							si.setReason("QuickSales");
							si.setsDate(df.format(sales1.get(j)
									.getSalesOrderDate()));
							si.setClientName(sales1.get(j).getCustomerName());
							salesIncome.add(si);
						}
					}
				}
			}
			// method 555555555555555555555555
			salesD = ardao.getSalesDates(fDate, tDate);
			logger.debug("sizes>>>>>>>>>>>>>>" + salesD.size());
			HashSet<String> set = new HashSet<String>();
			for (String item : salesD) {
				logger.debug("1");
				if (!set.contains(item)) {
					logger.debug("11");
					dt.add(item);
					set.add(item);
				}
			}
			logger.debug("Size of set>>>>>>>>>>>>>>>" + set.size());
			logger.debug("Size of set list>>>>>>>>>>>>>>>" + dt.size());
			float amt = 0;
			for (int i = 0; i < dt.size(); i++) {
				SalesIncomeMB si = new SalesIncomeMB();
				logger.debug("" + i);
				logger.debug("Date>>>>>>>>>>>>>>>>>>>>>>>>>>" + dt.get(i));
				// method 6666666666666666666666
				sales = ardao.getSalesAsset(dt.get(i));
				logger.debug("Size sales>>>>>>>>>>>>>>>>>>>>>>" + sales.size());
				logger.debug("Size of before>>>>>>>>>>>>>>"
						+ salesIncome.size());
				if (sales.size() > 0) {
					for (int j = 0; j < sales.size(); j++) {
						idList.add(sales.get(j).getSales_ID());
					}

				} else {
					logger.debug("No data in sales>>>>>>>>>>>>>>>>>>");
				}
			}

			if (idList.size() > 0) {
				logger.debug("Sales table list size>>>>>>>>4400>>>>>>>>>"
						+ idList);
				logger.debug("1");
				for (int k = 0; k < idList.size(); k++) {
					logger.debug("2");
					// changes
					iList = ardao.getInvicesData(idList.get(k));
					if (iList.size() > 0) {
						logger.debug("4");
						logger.debug("3 -- " + iList.get(0).getInvoice_ID());

						ipList = ardao.getInvoicePaymentSalesData(iList.get(0)
								.getInvoice_ID());
						logger.debug("5");

						if (ipList.size() > 0) {
							int s_Id = 0;
							s_Id = ipList.get(0).getI0022().getI0021()
									.getSales_ID();
							NsalesRtrn = ardao.normalsalereturn(s_Id);
							if (NsalesRtrn.size() > 0) {
								BigDecimal dr1 = new BigDecimal("0"), nr1 = new BigDecimal(
										"0"), retun_qty1 = new BigDecimal("0"), sell_price1 = new BigDecimal(
										"0");
								String discount1 = "0", type1 = "";
								BigDecimal amount1 = new BigDecimal("0"), temp111 = new BigDecimal(
										"0"), temp11 = new BigDecimal("0"), soldQTY1 = new BigDecimal(
										"0");
								BigDecimal temp113 = new BigDecimal("0");
								BigDecimal totalprice1 = BigDecimal.valueOf(0);
								for (int m = 0; m <= NsalesRtrn.size(); m++) {
									try {

										dr1 = dr1.add(new BigDecimal(NsalesRtrn
												.get(m).getDr()));
										logger.debug("dr" + dr1);
										nr1 = nr1.add(new BigDecimal(NsalesRtrn
												.get(m).getNr()));
										logger.debug("nr" + nr1);
										discount1 = NsalesRtrn.get(m)
												.getSalesRecord().getDiscount();
										logger.debug("discount" + discount1);
										sell_price1 = new BigDecimal(NsalesRtrn
												.get(m).getSalesRecord()
												.getSell_price());
										logger.debug("sell_price" + sell_price1);
										type1 = NsalesRtrn.get(m)
												.getSalesRecord()
												.getDiscountType();
										logger.debug("type" + type1);
										soldQTY1 = new BigDecimal(NsalesRtrn
												.get(m).getSalesRecord()
												.getSoldQuantity());
										logger.debug("soldQTY" + soldQTY1);

										retun_qty1 = dr1.add(nr1);
										logger.debug("retun_qty" + retun_qty1);
										if (discount1 == null) {
											discount1 = "0";
											amount1 = new BigDecimal("0");
											temp113 = new BigDecimal(discount1)
													.divide(soldQTY1);
											logger.debug("temp" + temp113);
											amount1 = retun_qty1
													.multiply(temp113);
											logger.debug("amount" + amount1);
										} else if (type1.equals("%")) {
											logger.debug("Inside %----");
											amount1 = sell_price1
													.multiply(new BigDecimal(
															discount1).divide(BigDecimal
															.valueOf(100)));
											logger.debug("amount" + amount1);
										} else if (type1.equals("IDR")) {
											logger.debug("Inside IDR");
											temp11 = new BigDecimal(discount1)
													.divide(soldQTY1);
											logger.debug("temp" + temp11);
											amount1 = retun_qty1
													.multiply(temp11);
											logger.debug("amount" + amount1);
										} else if (type1.equals("")
												|| type1 == null) {
											logger.debug("Inside empty");
											amount1 = new BigDecimal("0");
											logger.debug("amount" + amount1);
										} else if (((discount1 == null) || (discount1
												.equalsIgnoreCase("")))
												&& ((type1 == null) || (type1
														.equalsIgnoreCase("")))) {
											logger.debug("null");
											discount1 = "0";
											temp113 = new BigDecimal(discount1)
													.divide(soldQTY1);
											logger.debug("temp" + temp113);
											amount1 = retun_qty1
													.multiply(temp113);
											logger.debug("amount" + amount1);
										}

										temp111 = (sell_price1
												.multiply(retun_qty1))
												.subtract(amount1);
										logger.debug("temp1" + temp111);
										totalprice1 = temp111;
										logger.debug("totalprice" + totalprice1);
									} catch (IndexOutOfBoundsException e) {
										logger.debug("Inside Exception");
									}
								}
								logger.debug("6");
								SimpleDateFormat df = new SimpleDateFormat(
										"dd/MM/yyyy");
								SalesIncomeMB si = new SalesIncomeMB();
								logger.debug("getCrossTotal"
										+ ipList.get(0).getI0022().getI0021()
												.getCrossTotal());
								si.setAmount(""
										+ new BigDecimal(ipList.get(0)
												.getI0022().getI0021()
												.getCrossTotal())
												.subtract(totalprice1));
								si.setReason("Sales");
								si.setsDate(df.format(ipList.get(0).getI0022()
										.getI0021().getSalesOrderDate()));
								si.setClientName(ipList.get(0).getI0022()
										.getI0021().getCustomerName());
								salesIncome.add(si);
								logger.debug("7");
							} else {
								SimpleDateFormat df = new SimpleDateFormat(
										"dd/MM/yyyy");
								SalesIncomeMB si = new SalesIncomeMB();
								si.setAmount(ipList.get(0).getI0022()
										.getI0021().getCrossTotal());
								si.setReason("Sales");
								si.setsDate(df.format(ipList.get(0).getI0022()
										.getI0021().getSalesOrderDate()));
								si.setClientName(ipList.get(0).getI0022()
										.getI0021().getCustomerName());
								salesIncome.add(si);
							}

						} else {
							logger.debug("No sales Payment>>>>>>>>");
						}
					} else {
						logger.debug("No data in invoices>>>>>>>>>>>");
					}
				}
			} else {
				logger.debug("No data in isales1>>>>>>>>>>>>>>>>");
			}
			tran = ardao.getTransactionIncome(fDate, tDate);
			if (tran.size() > 0) {
				for (int i = 0; i < tran.size(); i++) {
					// method222222222222222222222222222
					ara = ardao.getAccountReceivable(tran.get(i)
							.getTransactionId());
					if (ara.size() > 0) {
						SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						SalesIncomeMB si = new SalesIncomeMB();
						si.setAmount(ara.get(0).getTransaction().getAmount());
						si.setReason("Transaction");
						si.setsDate(""+ ara.get(0).getTransaction().getPayDate());
						si.setClientName(ara.get(0).getTransaction()
								.getParticulars());
						salesIncome.add(si);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Inside Exception", e);
		} finally {

		}

		return salesIncome;
	}

	public List<PurchaseExpenseMB> getPurchaseExpences1(Date fDate, Date tDate)
			throws DemoException {
		List<PurchaseExpenseMB> purchaseExpense = new ArrayList<PurchaseExpenseMB>();
		List<Transaction> tran = null;
		List<Date> pD = null;
		List<Date> rpD = null;
		List<I0015> purchase = null;
		List<PurchaseReturn> purchaseRtrn = null;
		List<Date> dt = new ArrayList<Date>();
		List<AccPayableLiability> apyl = null;
		List<Integer> idList = new ArrayList<Integer>();
		List<Integer> inIdList = new ArrayList<Integer>();
		List<I0022> iList = null;
		List<I0023> ipList = null;

		try {
			tran = ardao.getTransactionExpense(fDate, tDate);
			if (tran.size() > 0) {
				for (int i = 0; i < tran.size(); i++) {
					apyl = ardao.getAccountPayableInfo(tran.get(i)
							.getTransactionId());
					if (apyl.size() > 0) {
						PurchaseExpenseMB si = new PurchaseExpenseMB();
						SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						si.setAmount(""
								+ apyl.get(0).getTransaction().getAmount());
						si.setReason("Transaction");
						si.setsDate(""+apyl.get(0).getTransaction()
								.getPayDate());
						System.out.println("datee "+si.getsDate());
						si.setClientName(apyl.get(0).getTransaction()
								.getParticulars());
						purchaseExpense.add(si);
					}
				}
			}
			logger.debug("Size1>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
					+ purchaseExpense.size());
			pD = ardao.getpurchaseOrderDatefromPurchase(fDate, tDate);
			logger.debug("sizes>>>>>>>>>>>>>>" + pD.size());
			HashSet<Date> set = new HashSet<Date>();
			for (Date item : pD) {
				logger.debug("1");
				if (!set.contains(item)) {
					logger.debug("11");
					dt.add(item);
					set.add(item);
				}
			}
			logger.debug("Size of set1>>>>>>>>>>>>>>>" + set.size());
			logger.debug("Size of set list1>>>>>>>>>>>>>>>" + dt.size());
			for (int i = 0; i < dt.size(); i++) {
				PurchaseExpenseMB si = new PurchaseExpenseMB();
				logger.debug("" + i);
				logger.debug("Date>>>>>>>>>>>>>>>>>>>>>>>>>>" + dt.get(i));
				purchase = ardao.getPurchasesforAsset(dt.get(i));
				logger.debug("Size sales>>>>>>>>>>>>>>>>>>>>>>"
						+ purchase.size());
				logger.debug("Size of before>>>>>>>>>>>>>>"
						+ purchaseExpense.size());
				if (purchase.size() > 0) {
					for (int j = 0; j < purchase.size(); j++) {
						idList.add(purchase.get(j).getPurchase_ID());
					}

				} else {
					logger.debug("No data in sales>>>>>>>>>>>>>>>>>>");
				}
			}
			logger.debug("Size2>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + idList.size());
			if (idList.size() > 0) {
				logger.debug("purchase table list size>>>>>>>>>>>>>>>>>"
						+ idList);
				logger.debug("1");
				for (int k = 0; k < idList.size(); k++) {
					logger.debug("2");
					iList = ardao.getInvicesPurchaseData(idList.get(k));
					logger.debug("3");
					if (iList.size() > 0) {
						logger.debug("4");
						ipList = ardao.getInvoicePaymentPurchaseData(iList.get(
								0).getInvoice_ID());
						logger.debug("5");
						if (ipList.size() > 0) {
							int p_ID = 0;
							logger.debug("Purchase id---"
									+ ipList.get(0).getI0022().getI0015()
											.getPurchase_ID());
							p_ID = ipList.get(0).getI0022().getI0015()
									.getPurchase_ID();
							purchaseRtrn = ardao.purchasereturn(p_ID);
							if (purchaseRtrn.size() > 0) {
								BigDecimal dr1 = new BigDecimal("0"), nr1 = new BigDecimal(
										"0"), retun_qty1 = new BigDecimal("0"), sell_price1 = new BigDecimal(
										"0");
								String discount1 = "0", type1 = "";
								BigDecimal amount1 = new BigDecimal("0"), temp111 = new BigDecimal(
										"0"), temp11 = new BigDecimal("0"), soldQTY1 = new BigDecimal(
										"0");
								BigDecimal totalprice1 = BigDecimal.valueOf(0);
								for (int m = 0; m <= purchaseRtrn.size(); m++) {
									try {
										dr1 = dr1.add(new BigDecimal(
												purchaseRtrn.get(m)
														.getDamageReturn()));
										logger.debug("dr" + dr1);
										nr1 = nr1.add(new BigDecimal(
												purchaseRtrn.get(m)
														.getNormalReturn()));
										logger.debug("nr" + nr1);
										sell_price1 = new BigDecimal(
												purchaseRtrn.get(m).getPrice())
												.divide(new BigDecimal(
														purchaseRtrn.get(m)
																.getQuantity()));
										logger.debug("Sell price" + sell_price1);
										soldQTY1 = new BigDecimal(purchaseRtrn
												.get(m).getQuantity());
										logger.debug("soldQTY" + soldQTY1);
										retun_qty1 = dr1.add(nr1);
										logger.debug("retun_qty" + retun_qty1);
										totalprice1 = sell_price1
												.multiply(retun_qty1);
										logger.debug("Amount" + totalprice1);
									} catch (IndexOutOfBoundsException e) {
										logger.debug("Inside Exception");
									}
								}
								logger.debug("6");
								PurchaseExpenseMB si = new PurchaseExpenseMB();
								SimpleDateFormat df = new SimpleDateFormat(
										"dd/MM/yyyy");
								logger.debug("Total"
										+ ipList.get(0).getI0022().getI0015()
												.getTotalPrice());
								logger.debug("totalprice1" + totalprice1);
								logger.debug("Subtract"
										+ new BigDecimal(ipList.get(0)
												.getI0022().getI0015()
												.getTotalPrice())
												.subtract(totalprice1));
								si.setAmount(""
										+ new BigDecimal(ipList.get(0)
												.getI0022().getI0015()
												.getTotalPrice())
												.subtract(totalprice1));
								si.setReason("Purchase");
								si.setsDate(df.format(ipList.get(0).getI0022()
										.getI0015().getOrderDate()));
								si.setClientName(ardao
										.getInvoicePaymentPurchaseData1(ipList
												.get(0).getI0022()
												.getInvoice_ID()));
								purchaseExpense.add(si);
							} else {
								PurchaseExpenseMB si = new PurchaseExpenseMB();
								SimpleDateFormat df = new SimpleDateFormat(
										"dd/MM/yyyy");
								si.setAmount(ipList.get(0).getI0022()
										.getI0015().getTotalPrice());
								si.setReason("Purchase");
								si.setsDate(df.format(ipList.get(0).getI0022()
										.getI0015().getOrderDate()));
								si.setClientName(ardao
										.getInvoicePaymentPurchaseData1(ipList
												.get(0).getI0022()
												.getInvoice_ID()));
								purchaseExpense.add(si);
							}

						} else {
							logger.debug("No sales Payment>>>>>>>>");
						}
					} else {
						logger.debug("No data in invoices>>>>>>>>>>>");
					}
				}
			} else {
				logger.debug("No data in isales1>>>>>>>>>>>>>>>>");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}

		return purchaseExpense;
	}

	public List<I0032> getFreeLancerInfo(String freeLancerName)
			throws DemoException {
		return dao.getFreeLancerInfo(freeLancerName);
	}

	/* siva 10-4-15 */
	public List<String> getSONfordispatch() throws DemoException {
		return dao.getSONfordispatch();
	}

	public List<CommissionFormMB> getDispatchData(String soNumber,
			PurchaseOrder dom) throws DemoException {
		List<CommissionFormMB> disp = new ArrayList<CommissionFormMB>();
		List<CommissionFormMB> dispFinal = new ArrayList<CommissionFormMB>();
		List<CommissionFormMB> dispFinal1 = new ArrayList<CommissionFormMB>();
		List<Dispatch> dispatchList = null;
		List<I0019> stock = null;
		List<Integer> bids = new ArrayList<Integer>();
		List<SalesRecord> stock1 = null;
		try {
			dispatchList = dao.getDispathchfromSales(soNumber);
			if (dispatchList.size() > 0) {

				stock = dao.getDeliveredStock(dispatchList.get(0).getI0021()
						.getSales_ID());
				stock1 = dao.getDeliveredStockz(dispatchList.get(0).getI0021()
						.getSales_ID(), dom);
				logger.debug("size of stock==>>" + stock1.size() + "unit -->> "
						+ dom.getUnit());
			} else {
				logger.debug("No data for sale");
			}
			if (stock.size() > 0) {
				HashSet<Integer> hset = new HashSet<Integer>();
				for (int i = 0; i < stock.size(); i++) {
					if (!hset.contains(stock.get(i).getI0018().getBatch_ID())) {
						hset.add(stock.get(i).getI0018().getBatch_ID());
						bids.add(stock.get(i).getI0018().getBatch_ID());
					}
				}
				logger.debug(">>>>>>>>>>>>>>>>>>IDS>>>>>>>" + bids);
				int count = 0;
				int index = 0;
				for (int j = 0; j < bids.size(); j++) {
					CommissionFormMB cmb = new CommissionFormMB();
					for (int i = 0; i < stock.size(); i++) {
						if (bids.get(j) == stock.get(i).getI0018()
								.getBatch_ID()) {
							index = i;
							count++;
						}
					}
					if (count > 0) {
						cmb.setProductName(stock.get(index).getI0018()
								.getProductName());
						cmb.setuPrice(""
								+ stock.get(index).getI0018().getUnitPrice());
						cmb.setQuantity("" + count);
						disp.add(cmb);
						count = 0;
					}

				}
				CommissionFormMB commi = new CommissionFormMB();
				commi.setAddress(dispatchList.get(0).getI0021()
						.getShipingAddress());
				commi.setVehicle1(dispatchList.get(0).getVehicle1());
				commi.setVehicle2(dispatchList.get(0).getVehicle2());
				commi.setDispatchNo(dispatchList.get(0).getDispatchno());
				commi.setComList(disp);
				dispFinal.add(commi);
			}
			if (stock1.size() > 0) {
				int index = 0;
				int v = 1;
				int serialno = 0;
				int count = 0;
				HashSet<Integer> hset = new HashSet<Integer>();
				for (int i = 0; i < stock1.size(); i++) {
					if (!hset.contains(stock1.get(i).getI0019().getI0018()
							.getBatch_ID())) {
						serialno = v;
						hset.add(stock1.get(i).getI0019().getI0018()
								.getBatch_ID());
						bids.add(stock1.get(i).getI0019().getI0018()
								.getBatch_ID());
					}

				}
				BigDecimal bb = BigDecimal.valueOf(0);
				String totQuan = "";
				String totRoll = "";
				for (int j = 0; j < bids.size(); j++) {
					for (int i = 0; i < stock1.size(); i++) {
						if (bids.get(j) == stock1.get(i).getI0019().getI0018()
								.getBatch_ID()) {
							index = i;
							count++;
						}
						if (count > 0) {

							logger.debug("size -- >>" + stock1.size()
									+ " no -- >>" + v);

							CommissionFormMB cmb = new CommissionFormMB();

							cmb.setsNo("" + v);
							cmb.setProductName(stock1.get(index).getI0019()
									.getI0018().getProductName());
							cmb.setuPrice(""
									+ stock1.get(index).getI0019().getI0018()
											.getUnitPrice());
							cmb.setQuantity("" + count);
							logger.debug("==roll id==="
									+ stock1.get(index).getI0019().getRoll_ID());
							cmb.setRollnum(stock1.get(index).getI0019()
									.getRoll_ID());
							logger.debug("==quan===="
									+ stock1.get(index).getI0019()
											.getRoll_Qunatity());
							cmb.setRollquan(stock1.get(index).getSoldQuantity());
							cmb.setRollunit(dom.getUnit());

							String s = stock1.get(index).getI0019().getI0018()
									.getProductName();
							dao.changeDrop1(s, dom);
							cmb.setProdcode(dom.getProdcode());
							totRoll = "" + stock1.size();
							logger.debug("in bo 1-->> bb "
									+ bb
									+ "add"
									+ stock1.get(index).getI0019()
											.getStock_Out());
							bb = bb.add(new BigDecimal(stock1.get(index)
									.getSoldQuantity()));
							logger.debug("in bo 1-->> bb " + bb);
							cmb.setTotRoll(totRoll);

							dao.changeDrop1(s);
							disp.add(cmb);
							v++;
							count = 0;

						}

					}

					logger.debug("serial no -- >>" + serialno);
					// v++;
				}
				dom.setQuantity("" + bb);
				logger.debug("in bo 2-->> bb " + bb);
				CommissionFormMB commi = new CommissionFormMB();
				commi.setAddress(dispatchList.get(0).getI0021()
						.getShipingAddress());
				commi.setVehicle1(dispatchList.get(0).getVehicle1());
				commi.setVehicle2(dispatchList.get(0).getVehicle2());
				commi.setDispatchNo(dispatchList.get(0).getDispatchno());
				commi.setComList(disp);
				dispFinal1.add(commi);

			} else {
				logger.debug("No data found");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return dispFinal1;
	}

	public String getCommisionAmount(String freelancerName)
			throws DemoException {
		String amount = "";
		List<I0032> buyers = null;
		List<I0021> sales = null;
		try {
			buyers = dao.getCategoryData(freelancerName);
			if (buyers.size() > 0) {
				logger.debug("b---------------->" + buyers.size());
				sales = dao.getSalesDataForCommision(buyers.get(0)
						.getBuyer_ID());
				logger.debug("o");
				logger.debug("-----------------------Size---->" + sales.size());
				if (sales.size() > 0) {
					String tC = "0";
					for (int i = 0; i < sales.size(); i++) {
						tC = ""
								+ (new BigDecimal(tC).add(new BigDecimal(sales
										.get(i).getCrossCommision())));
					}
					amount = tC;
					logger.debug("------------------------<><>" + amount);
				} else {
					logger.debug("No data for sales");
				}
			} else {
				logger.debug("No free lancer is found");
			}

		} catch (Exception e) {

		} finally {

		}
		return amount;
	}

	public String salesOrder4Normal(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesOrder4(purchaseOrder);
		logger.debug("1");
		String quant = purchaseOrder.getQuantity();
		logger.debug("2");
		String temp = ""
				+ BigDecimal.valueOf(0).add(
						new BigDecimal(purchaseOrder.getCrosstotal1()));
		logger.debug("3");
		if (purchaseOrder.getCategory().equalsIgnoreCase("")) {
			logger.debug("4");
			temp = ""
					+ (new BigDecimal(quant).multiply(new BigDecimal(
							purchaseOrder.getSellingPrice()))
							.add(new BigDecimal(temp)));
		} else if (purchaseOrder.getCategory().equalsIgnoreCase("%")) {
			logger.debug("4.1");
			temp = ""
					+ (new BigDecimal(quant).multiply(new BigDecimal(
							purchaseOrder.getSellingPrice()))
							.add(new BigDecimal(temp)));
			temp = ""
					+ (new BigDecimal(temp).subtract((new BigDecimal(temp)
							.multiply(new BigDecimal(purchaseOrder
									.getActualPrice())).divide(BigDecimal
							.valueOf(100)))));
		} else if (purchaseOrder.getCategory().equalsIgnoreCase("IDR")) {
			logger.debug("4.2");
			temp = ""
					+ new BigDecimal(quant)
							.multiply(
									new BigDecimal(purchaseOrder
											.getSellingPrice()))
							.add(new BigDecimal(temp))
							.subtract(
									new BigDecimal(purchaseOrder
											.getActualPrice()));
		}
		logger.debug("5");
		logger.debug("crosstotal:::::" + temp);
		purchaseOrder.setCrosstotal1(temp);
		return null;
	}

	public List<ProductRegister> nonsales() throws DemoException {
		int count = 0;
		List<ProductRegister> nonsaleproduct = new ArrayList<ProductRegister>();
		List<I0001> product = null;
		List<I0018> batch = null;
		List<I0019> bar = null;
		List<I0017> stock = null;
		int temp = 0;
		try {
			logger.debug("Inside BOImpl");
			product = dao.getProductInfo();
			logger.debug("Size of product" + product.size());
			if (product.size() > 0) {
				for (int i = 0; i < product.size(); i++) {
					batch = dao.getBatchInfo(product.get(i).getProductName());
					logger.debug("Size of Batch" + batch.size());
					if (batch.size() > 0) {

						stock = dao.getStock1(batch.get(0).getI0017()
								.getStock_ID());
						logger.debug("Stock Size" + stock);
						if (stock.size() > 0) {
							List<Date> dlist = dao.getBarcodeInfo11(batch
									.get(0).getBatch_ID());
							logger.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<----------------------------------->>>>>>>>>>>>>>>>"
									+ dlist.size() + "<<<<<---->>>>" + dlist);
							if (dlist.size() > 0) {
								for (int ii = 0; ii < dlist.size(); ii++) {
									List<I0019> data = dao.getBarcodeInfo2(
											batch.get(0).getBatch_ID(),
											dlist.get(ii));
									if (data.size() == 0) {
										nonsaleproduct.add(dao.getProductData1(
												batch.get(0).getBatch_ID(),
												dlist.get(ii)));
									} else {
										logger.debug("Stock out");
									}
								}
							} else {
								logger.debug("No data for dues");
							}

						} else {
							logger.debug("No data in batch");
						}
					}

				}
			} else {

				logger.debug("No data in product");
			}

		} catch (Exception e) {

		}

		return nonsaleproduct;
	}

	public int outofStockz() throws DemoException {
		int count = 0;
		List<I0018> bList = null;
		List<I0019> stock = null;
		try {
			logger.debug("in out");
			bList = dao.getBatchfromStock();
			if (bList.size() > 0) {
				for (int i = 0; i < bList.size(); i++) {
					stock = dao.outofStock(bList.get(i).getBatch_ID());

					if (stock.size() > 0) {
						for (int j = 0; j < stock.size(); j++) {
							count = count + 1;
							logger.debug("count--------------------------->"
									+ count);
						}
					}
				}
			} else {
				logger.debug("Nooooooooooooooooooooo");
			}
		} catch (Exception e) {

		} finally {

		}
		return count;

	}

	public int outofStock() throws DemoException {
		int count = 0;
		List<I0018> bList = null;
		List<I0019> stock = null;
		try {
			logger.debug("in out bo");
			bList = dao.getBatchfromStock();
			if (bList.size() > 0) {
				for (int i = 0; i < bList.size(); i++) {
					String strr = "";
					strr = dao.outofStockz(bList.get(i).getBatch_ID());
					logger.debug("bo size -->>  " + strr);

					if ((Integer.parseInt(strr)) == 0) {
						count = count + 1;
						logger.debug("count--------------------------->"
								+ count);

					}
				}
			} else {
				logger.debug("Nooooooooooooooooooooo");
			}
		} catch (Exception e) {

		} finally {

		}
		return count;

	}

	public List<ProductRegister> outofStock1() throws DemoException {
		List<ProductRegister> pdt = new ArrayList<ProductRegister>();
		int count = 0;
		List<I0018> bList = null;
		List<I0019> stock = null;
		List<Integer> iData = new ArrayList<Integer>();
		try {
			logger.debug("in out bo");
			bList = dao.getBatchfromStock();
			if (bList.size() > 0) {
				for (int i = 0; i < bList.size(); i++) {
					String strr = "";
					strr = dao.outofStockz(bList.get(i).getBatch_ID());
					logger.debug("bo size -->>  " + strr);

					if ((Integer.parseInt(strr)) == 0) {
						pdt.add(dao.getProductData2(bList.get(i).getBatch_ID()));
						logger.debug("count--------------------------->"
								+ count);

					}
				}
			} else {
				logger.debug("Nooooooooooooooooooooo");
			}

		} catch (Exception e) {

		} finally {

		}
		return pdt;

	}

	public String partialnormalreturn(PurchaseOrder p, int qty)
			throws DemoException {
		List<I0021> sales = null;
		try {
			dao.salesOrdercancelFormsub1(p);
			sales = p.getResult();
			if (sales.size() > 0) {
				p.setStatus("normal");
				dao.partialnormalreturn(p, qty, sales.get(0).getSales_ID());
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public String partialdamagereturn(PurchaseOrder p, int qty)
			throws DemoException {
		List<I0021> sales = null;
		try {
			dao.salesOrdercancelFormsub1(p);
			sales = p.getResult();
			if (sales.size() > 0) {
				p.setStatus("damage");
				dao.partialnormalreturn(p, qty, sales.get(0).getSales_ID());
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public int salesreturncount() throws DemoException {
		logger.debug("==in==bo=");
		int count = 0;
		List<String> sList = null;
		try {
			Date d = PurchaseOrderFromMB.beforeonemonth(Calendar.getInstance()
					.getTime());
			sList = dao.getSalesreturnSalesorder(d);
			logger.debug("SR LIST:::" + sList);
			if (sList.size() > 0) {
				count = sList.size();
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return count;
	}

	public List<Sales> getSalesReturnView() throws DemoException {
		List<Sales> salesReturnList = new ArrayList<Sales>();
		List<SalesReturn> srList = null;
		try {
			Date d = PurchaseOrderFromMB.beforeonemonth(Calendar.getInstance()
					.getTime());
			srList = dao.getSaleReturn(d);
			logger.debug("srlist" + srList.size());
			if (srList.size() > 0) {
				List<String> oList = new ArrayList<String>();
				HashSet<String> oset = new HashSet<String>();
				List<String> pList = new ArrayList<String>();
				HashSet<String> pset = new HashSet<String>();
				for (int i = 0; i < srList.size(); i++) {
					if (!oset.contains(srList.get(i).getSalesOrderNumber())) {
						oset.add(srList.get(i).getSalesOrderNumber());
						oList.add(srList.get(i).getSalesOrderNumber());
					}
					if (!pset.contains(srList.get(i).getProductName())) {
						pset.add(srList.get(i).getProductName());
						pList.add(srList.get(i).getProductName());
					}
				}
				logger.debug("------------------------->" + oList.size());
				logger.debug("------------------------->" + pList.size());
				String rCount = "0";
				String nrCount = "0";
				String drCount = "0";
				for (int j = 0; j < pList.size(); j++) {
					for (int k = 0; k < oList.size(); k++) {
						List<SalesReturn> srList1 = dao.getSaleReturn1(d,
								pList.get(j), oList.get(k));
						if (srList1.size() > 0) {
							Sales s = new Sales();
							s.setProductName(srList1.get(0).getProductName());
							s.setSalesOrderNumber(srList1.get(0)
									.getSalesOrderNumber());
							s.setSalesorderDate(srList1.get(0).getI0021()
									.getSalesOrderDate());
							s.setCustomerName(srList1.get(0).getI0021()
									.getCustomerName());
							logger.debug("==phn num==="
									+ srList1.get(0).getI0021()
											.getPhoneNumber());
							s.setPhoneNumber(srList1.get(0).getI0021()
									.getPhoneNumber());
							for (int a = 0; a < srList1.size(); a++) {
								logger.debug("c -1-  " + rCount);
								rCount = ""
										+ (new BigDecimal(rCount)
												.add(new BigDecimal(srList1
														.get(a).getQuantity())));
								nrCount = ""
										+ (new BigDecimal(nrCount)
												.add(new BigDecimal(srList1
														.get(a).getNr())));
								drCount = ""
										+ (new BigDecimal(drCount)
												.add(new BigDecimal(srList1
														.get(a).getDr())));
								logger.debug("c -2-  " + rCount);
							}
							logger.debug("===a1===" + rCount);
							logger.debug("===a1===" + drCount);
							logger.debug("===a1===" + nrCount);
							s.setA1(new BigDecimal("" + rCount).setScale(2));
							s.setA2(new BigDecimal("" + drCount).setScale(2));
							s.setA3(new BigDecimal("" + nrCount).setScale(2));
							s.setReturnItems("" + rCount);
							s.setDamageReturnItems("" + drCount);
							s.setNormalReturnItems("" + nrCount);
							salesReturnList.add(s);
							rCount = "0";
							nrCount = "0";
							drCount = "0";
						}
					}
				}
			} else {

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return salesReturnList;
	}

	public String viewAccountReceivable(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.viewAccountReceivable(purchaseOrder);
		return null;
	}

	public String viewAccountPayable(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.viewAccountPayable(purchaseOrder);
		return null;
	}

	public String designation(EmployeeDetail emp) throws DemoException {
		return dao.designation(emp);
	}

	public String updateDispatch(String str1, String str2, String str3)
			throws DemoException {
		List<Dispatch> dis = null;
		try {
			dis = dao.getDispathchfromrefference(str1);
			if (dis.size() > 0) {
				dao.updateDispatch(dis.get(0).getDispatch_ID(), str2, str3);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	public String salesSave(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("customer name----------->>MMNNnew>>>"
				+ purchaseOrder.getCustomername());

		dao.salesSave(purchaseOrder);
		dao.salesOrder1(purchaseOrder);
		dao.salesSave1(purchaseOrder);
		return null;
	}

	public String salesconfirm(PurchaseOrder purchaseOrder)
			throws DemoException {

		logger.debug("crosstotal------------->"
				+ purchaseOrder.getCrosstotal1());
		float quant = Float.parseFloat(purchaseOrder.getQuantity());

		qdao.salesconfirm(purchaseOrder);

		return null;

	}

	public String purchasePrice(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("bo ---->>> ");
		return dao.purchasePrice(purchaseOrder);
	}

	public String purchase(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("bo ---->>> ");
		return dao.purchase(purchaseOrder);
	}

	public String purchase1(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("bo ---->>> ");
		return dao.purchase1(purchaseOrder);
	}

	public String purchaseorderClose(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("bo ---->>> ");
		return dao.purchaseorderClose(purchaseOrder);
	}

	public List<I0023> paymentamount(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("bo ---->>> ");
		return dao.paymentamount(purchaseOrder);
	}

	public List<I0023> paymentamountsale(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.debug("service ---->>> ");
		return dao.paymentamountsale(purchaseOrder);
	}

	public String returnQuantity(PurchaseOrder purchaseOrder)
			throws DemoException {
		return dao.returnQuantity(purchaseOrder);
	}

	public String salesReturnSubmit(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesReturnSubmit(purchaseOrder);
		dao.salesReturnSubmit1(purchaseOrder);
		return "";
	}

	public String viewSalesReturnDetail(PurchaseOrder purchaseOrder)
			throws DemoException {
		return dao.viewSalesReturnDetail(purchaseOrder);
	}

	public String viewSalesReturn(PurchaseOrder purchaseOrder)
			throws DemoException {
		return dao.viewSalesReturn(purchaseOrder);

	}

	@Override
	public String searchCity(Vendor vendor) throws DemoException {
		dao.searchCity(vendor);
		return null;
	}

	public ArrayList<String> changeList(String changeEvent,
			ArrayList<String> productList, PurchaseOrder purchaseOrder)
			throws DemoException {
		productList = dao.changeList(changeEvent, productList, purchaseOrder);
		return productList;
	}

	@Override
	public String addStockIn(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("----------->>>>Inside dao method begin<<-----------------");
		dao.addStockIn(purchaseOrder);
		logger.debug("----------->>>>Inside dao method end<<-----------------");
		return null;
	}

	@Override
	public ArrayList<String> getRollList(String productName,
			ArrayList<String> rollList) throws DemoException {
		rollList = qdao.getRollList(productName, rollList);
		return rollList;
	}

	@Override
	public String getRollQuantity(PurchaseOrder purchaseOrder)
			throws DemoException {

		return dao.getRollQuantity(purchaseOrder);
	}

	@Override
	public String qucikSalesRoll(PurchaseOrder purchaseOrder)
			throws DemoException {

		qdao.qucikSalesRoll(purchaseOrder);
		return null;
	}

	@Override
	public String updateRollSales(PurchaseOrder purchaseOrder)
			throws DemoException {
		qdao.updateRollSales(purchaseOrder);
		return null;
	}

	@Override
	public String salesRollconfirm(PurchaseOrder purchaseOrder)
			throws DemoException {
		qdao.salesRollconfirm(purchaseOrder);

		return null;
	}

	@Override
	public String updateRollQuantity(PurchaseOrder purchaseOrder)
			throws DemoException {
		qdao.updateRollQuantity(purchaseOrder);
		return null;
	}

	@Override
	public String salesOrderdelete(PurchaseOrder purchaseOrder)
			throws DemoException {
		qdao.salesOrderdelete(purchaseOrder);
		return null;
	}

	@Override
	public String getProductQuntity(PurchaseOrder purchaseOrder)
			throws DemoException {

		return dao.getProductQuntity(purchaseOrder);
	}

	@Override
	public List<I0032> customerNameChange11(String s1) throws DemoException {

		return dao.customerNameChange11(s1);
	}

	@Override
	public List<Employee> freenamelistser(List<Employee> name)
			throws DemoException {

		return dao.freenamelistser(name);
	}

	@Override
	public ArrayList<String> getAddRollList(String productName,
			ArrayList<String> rollList) throws DemoException {
		rollList = dao.getAddRollList(productName, rollList);
		return rollList;
	}

	@Override
	public String rollDamage(PurchaseOrder purchaseOrder) throws DemoException {
		dao.rollDamage(purchaseOrder);
		return "";
	}

	@Override
	public String quickSaleDropdown(PurchaseOrder purchaseOrder)
			throws DemoException {
		qdao.quickSaleDropdown(purchaseOrder);
		return null;
	}

	@Override
	public String quicksaleReturnSubmit(PurchaseOrder purchaseOrder)
			throws DemoException {

		return qdao.quicksaleReturnSubmit(purchaseOrder);
	}

	@Override
	public String quickViewSalesReturn(PurchaseOrder purchaseOrder)
			throws DemoException {

		return qdao.quickViewSalesReturn(purchaseOrder);
	}

	@Override
	public List<I0019> stockView3(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		batch2 = dao.stockView3(batch2, s, stockView);
		return batch2;
	}

	@Override
	public List<String> productVendor1(List<String> batchProductName4)
			throws DemoException {
		batchProductName4 = dao.productVendor1(batchProductName4);
		return batchProductName4;
	}

	public String getbarcodeInfo(PurchaseOrder purchaseOrder)
			throws DemoException {
		return qdao.getbarcodeInfo(purchaseOrder);
	}

	public String changeUserName(String newuser, String invusername)
			throws DemoException {
		return dao.changeUserName(newuser, invusername);
	}

	public String changeUserPassword(String newpasswrd, String invpassword)
			throws DemoException {
		return dao.changeUserPassword(newpasswrd, invpassword);
	}

	public String openingStockInsert(PurchaseOrder opngstock)
			throws DemoException {
		dao.i0015Insert(opngstock);
		dao.i0016Insert(opngstock);
		dao.i0018Insert(opngstock);

		dao.openingStockInsert(opngstock.getBatchID(), opngstock);
		return null;
	}

	@Override
	public String getPurchaseQty(PurchaseOrder purchaseOrder)
			throws DemoException {
		qdao.getPurchaseQty(purchaseOrder);
		return null;
	}

	@Override
	public String getpurchaseView(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.getpurchaseView(purchaseOrder);
		return null;
	}

	@Override
	public String getunitprice(String productName, String unitprice)
			throws DemoException {
		return dao.getunitprice(productName, unitprice);

	}

	public String salesOrderViewproduct(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesOrderViewproduct(purchaseOrder);
		return null;
	}

	public String salesRecordView(PurchaseOrder purchaseOrder)
			throws DemoException {
		dao.salesRecordView(purchaseOrder);
		return null;
	}

	@Override
	public ArrayList<String> getRollQuanList(String productName,
			ArrayList<String> rollList) throws DemoException {
		rollList = qdao.getRollQuanList(productName, rollList);
		return rollList;
	}

	@Override
	public List<I0032> getcitiesinfo(String city) throws DemoException {
		// TODO Auto-generated method stub
		return dao.getcitiesinfo(city);
	}

	@Override
	public List<String> getnames(List<String> names) throws DemoException {
		names = dao.getnames(names);
		return names;
	}

	@Override
	public List<String> getnames1(List<String> names1) throws DemoException {
		names1 = dao.getnames1(names1);
		return names1;

	}

	public String designRegSubmit(ProductRegister productRegister)
			throws DemoException, ParseException {
		dao.designRegSubmit(productRegister);
		return null;
	}

	public String designView(ProductRegister productRegister)
			throws DemoException {
		return dao.designView(productRegister);
	}

	public String designViews(ProductRegister productRegister)
			throws DemoException {
		for (int i = 0; i < productRegister.getFiles().size(); i++) {
			dao.designViews(productRegister, i);
		}

		return "";
	}

	public String designViewDelete(ProductRegister productRegister)
			throws DemoException {
		return dao.designViewDelete(productRegister);
	}

	public String designValidate(ProductRegister productRegister)
			throws DemoException {
		return dao.designValidate(productRegister);
	}

	public String saveImages(ProductRegister productRegister)
			throws DemoException {
		logger.debug("-->>bo ");
		logger.debug("-->>domain1 size " + productRegister.getDomain1().size());
		for (int i = 0; i < productRegister.getDomain1().size(); i++) {
			logger.debug("-->>bo "
					+ productRegister.getDomain1().get(i).getStatus());
			try {
				if (productRegister.getDomain1().get(i).getStatus()
						.equalsIgnoreCase("selected")) {
					dao.saveImages(productRegister, i);
				}
			} catch (Exception e) {

			}

		}
		return "";
	}

	@Override
	public String databaseValidate(LoginAccess loginaccess) {

		return dao.databaseValidate(loginaccess);
	}

	@Override
	public String checkErrorcode(LoginAccess loginaccess) {

		return dao.checkerrorcode(loginaccess);
	}

	@Override
	public String insertCreateUser(UserCreateDataBean userCreateDataBean) {

		return dao.insertCreateUser(userCreateDataBean);
	}

	@Override
	public List<UserCreateDataBean> insideUserEdit(
			UserCreateDataBean userCreateDataBean) {

		return dao.insideUserEdit(userCreateDataBean);
	}

	@Override
	public String insideUpdate(UserCreateDataBean userCreateDataBean) {
		return dao.insideUpdate(userCreateDataBean);
	}

	@Override
	public String userInsert(UserCreateDataBean userCreateDataBean)
			throws DemoException {
		return dao.userInsert(userCreateDataBean);
	}

	@Override
	public String getVendorVerification(String name) {

		return dao.getVendorVerification(name);
	}

	@Override
	public String userCheck(UserCreateDataBean userCreateDataBean)
			throws DemoException {
		return dao.userCheck(userCreateDataBean);
	}

	@Override
	public String getCustomerVerification(String name) {

		return dao.getCustomerVerification(name);
	}

	@Override
	public String retirveUser(UserCreateDataBean userCreateDataBean) {
		return dao.retirveUser(userCreateDataBean);
	}

	@Override
	public List<String> getCustomerInfo(Buyer b) {
		return dao.getCustomerInfo(b);
	}

	@Override
	public List<String> getproductListInfo(ProductRegister productRegister) {
		return dao.getproductListInfo(productRegister);
	}

	@Override
	public String getProductVerification(String name) {
		return dao.getProductVerification(name);
	}

	@Override
	public ArrayList<Report1> getVendorNameList() {
		return dao.getVendorNameList();
	}

	@SuppressWarnings("null")
	@Override
	public String findGlobalSearch(String golbalnamesearch) {
		String status = "Fail";
		try {
			logger.debug("---------------------- Calling findGlobalSearch Bo Begin----------------------"
					+ golbalnamesearch);
			if (golbalnamesearch != null
					|| golbalnamesearch.equalsIgnoreCase("")) {
				StringBuilder global = new StringBuilder(golbalnamesearch);
				if (global.length() >= 2) {
					String name = global.substring(0, 2);
					if (name.equalsIgnoreCase("PO")) {
						status = "Purchase";
					} else if (name.equalsIgnoreCase("SO")) {
						status = "Sales";
					} else if (name.equalsIgnoreCase("QS")) {
						status = "QuickSales";
					} else {
						status = "Stock";
					}
				}

			}
			logger.debug("---------------------- Calling findGlobalSearch Bo End----------------------");

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			logger.debug("---------------------- Calling findGlobalSearch Bo Exception----------------------");

		}
		return status;
	}

	@Override
	public ArrayList<VendorReport> insidevendornamesearch(
			VendorReport vendorReport) {

		return dao.insidevendornamesearch(vendorReport);
	}

	@Override
	public ArrayList<VendorReport> insideallvendornamesearch(
			VendorReport vendorReport) {

		return dao.insideallvendornamesearch(vendorReport);
	}

	@Override
	public List<I0025> getphonenumberglobalsearch(String golbalnamesearch)
			throws DemoException {

		return dao.getphonenumberglobalsearch(golbalnamesearch);
	}

	@Override
	public List<I0032> getcustphnosearch(String golbalnamesearch)
			throws DemoException {

		return dao.getcustphnosearch(golbalnamesearch);
	}

	@Override
	public List<Employee> getempphnosearch(String golbalnamesearch)
			throws DemoException {
		return dao.getempphnosearch(golbalnamesearch);
	}

	@Override
	public ArrayList<I0025> getgmailsearch(String golbalnamesearch)
			throws DemoException {

		return dao.getgmailsearch(golbalnamesearch);
	}

	@Override
	public List<I0032> getcustemailsearch(String golbalnamesearch)
			throws DemoException {
		return dao.getcustemailsearch(golbalnamesearch);
	}

	@Override
	public List<Employee> getempemailsearch(String golbalnamesearch)
			throws DemoException {
		return dao.getempemailsearch(golbalnamesearch);
	}

	@Override
	public List<SubProduct> submenus(int product_ID, String productCode) {
		return dao.submenus(product_ID,productCode);
	}
	
	public String saveClient(ClientDataBean clientDataBean)throws DemoException{
		List<Client> client=new ArrayList<Client>();
		String clientID="";
		try{
			client=dao.getAllClient();
			if(client.size()>0){
				int clientSize=client.size()+1;
				if(clientSize<=9) clientID="CNT000000"+clientSize;
				else if(clientSize>9 && clientSize<=99) clientID="CNT00000"+clientSize;
				else if(clientSize>99 && clientSize<=999) clientID="CNT0000"+clientSize;
				else if(clientSize>999 && clientSize<=9999) clientID="CNT000"+clientSize;
				else if(clientSize>9999 && clientSize<=99999) clientID="CNT00"+clientSize;
				else if(clientSize>99999 && clientSize<=999999) clientID="CNT0"+clientSize;
				else clientID="CNT"+clientSize;
			}else{
				clientID="CNT0000001";
			}
			logger.debug("client number "+clientID);
			clientDataBean.setClientNumber(clientID);
			return dao.saveClient(clientDataBean);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public String clientNoCheck(String phno)throws DemoException{
		return dao.clientNoCheck(phno);
	}
	
	public String getclientDetails(ClientDataBean clientDataBean){
		return dao.getclientDetails(clientDataBean);
	}
	
	public String getclientDetailsView(ClientDataBean clientDataBean){
		return dao.getclientDetailsView(clientDataBean);
	}
	
	public String clientUpdate(ClientDataBean clientDataBean){
		return dao.clientUpdate(clientDataBean);
	}
	
	public String clientDelete(ClientDataBean clientDataBean){
		return dao.clientDelete(clientDataBean);
	}

	public String userView(UserCreateDataBean userCreateDataBean){
		return dao.userView(userCreateDataBean);
	}
	
	public String getUserdetails(UserCreateDataBean userCreateDataBean){
		return dao.getUserdetails(userCreateDataBean);
	}
	
	public String userUpdate(UserCreateDataBean userCreateDataBean){
		return dao.userUpdate(userCreateDataBean);
	}
	
	public String userDelete(UserCreateDataBean userCreateDataBean){
		return dao.userDelete(userCreateDataBean);
	}
	
	@Override
	public String getdashboardCount(LoginAccess loginaccess) {
		return dao.getdashboardCount(loginaccess);
	}
	@Override
	public String addAccount(ATransaction payment) {
		return dao.addAccount(payment);
	}
	@Override
	public List<String> getAccountType(String clientID) {
		return dao.getAccountType(clientID);
	}

	public List<String> getstatelist(String country) {
		return dao.getstatelist(country);
	}

	@Override
	public String getempID() {
		return dao.getempID();
	}

	public List<EmployeePayroll> getEmployeePayrollDetails(EmployeePayroll employeePayroll){
		return adao.getEmployeePayrollDetails(employeePayroll);
	}

	public String getdocUpload(EmployeeDetail employeeDetail){
		return dao.getdocUpload(employeeDetail);
	}
	
	public void updatequalify(String qualID){
		adao.updatequalify(qualID);
	}

	public void updateExpernce(String expID){
		adao.updateExpernce(expID);
	}

	public List<String> getdepartmentname() {
		return dao.getdepartmentname();
	}

	public String getProductcodeVerification(String name) {
		return dao.getProductcodeVerification(name);
	}


	public String saveAcountDeposit(String clientID, ATransaction payment){
		return adao.saveAcountDeposit(clientID,payment);
	}

	public List<String> getAccountDepsit(String clientID, ATransaction atrans){
		return adao.getAccountDepsit(clientID,atrans);
	}

	public String saveTransPayment(String clientID, ATransaction atransaction){
		return adao.saveTransPayment(clientID,atransaction);
	}
	
	@Override
	public String getDailyreport(Report1 report1) {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		BigDecimal sellingprice=BigDecimal.valueOf(0);
		List<I0015> i0015=null;List<I0015> i0015paid=null;
		List<I0016> i0016=null;List<I0016> i0016paid=null;
		List<I0021> i0021=null;List<I0021> i0021paid=null;
		List<I0021> qi0021=null;List<I0021> qi0021paid=null;
		List<PurchaseReturn> returnlist=null;List<SalesReturn> sreturn=null;
		List<SalesReturn> qsreturn=null;List<Transaction> trans=null;
		try{
			System.out.println("--------getDailyreport---------");
			List<Report1> reportlist=new ArrayList<Report1>();
			if(report1.getBusinesspartner().equalsIgnoreCase("Vendor"))
			{
				System.out.println("type size "+report1.getTypes().length);
				for (int j = 0; j < report1.getTypes().length; j++) 
				{
					System.out.println("purchase "+report1.getTypes()[j]);
					if(report1.getTypes()[j].equals("Purchase Delivery"))
					{
						System.out.println("-----Purchase Delivery------"+report1.getYear());
						 if(report1.getReportpage().equalsIgnoreCase("DailyReports"))
						 {
							 i0015=dao.getI0015daily(report1,clientID);
						}
						 else if(report1.getReportpage().equalsIgnoreCase("WeeklyReports")){
							 	i0015=dao.getI0015weekly(report1,clientID);
						}
						else if(report1.getReportpage().equalsIgnoreCase("MonthlyReports")){
							i0015=dao.getI0015monthly(report1,clientID);
						}
						else if(report1.getReportpage().equalsIgnoreCase("QuarterlyReports")){
							i0015=dao.getI0015quarterly(report1,clientID);
						}
						else if(report1.getReportpage().equalsIgnoreCase("HalfyearlyReports")){
							i0015=dao.getI0015halfyearly(report1,clientID);
						}
						else if(report1.getReportpage().equalsIgnoreCase("AnnualReports")){
							i0015=dao.getI0015annual(report1,clientID);
							
						}System.out.println("----i0015------Delivery-------------->"+i0015.size());
						if(i0015.size()>0)
						{
							for (int i = 0; i < i0015.size(); i++)
							{
								Report1 report=new Report1();
								report.setOrderNumber(i0015.get(i).getTemOrderNumber());
								String orderNumber=i0015.get(i).getTemOrderNumber();
								i0016=dao.getvendorname(orderNumber);
								if(i0016.size()>0){
									report.setVendorName(i0016.get(0).getI0031().getI0025().getVendorPhoneNumber());
								}
								report.setPrice(i0015.get(i).getTotalPrice());
								report.setStatus(i0015.get(i).getStatus());
								reportlist.add(report);
							}
						}
					}
					if(report1.getTypes()[j].equals("Purchase Payment"))
					{
						System.out.println("-------Purchase Payment-------");
						 if(report1.getReportpage().equalsIgnoreCase("DailyReports")){
							 i0015paid=dao.getI0015daily(report1,clientID);
							}
							else if(report1.getReportpage().equalsIgnoreCase("WeeklyReports")){
								i0015paid=dao.getI0015weekly(report1,clientID);
							}
							else if(report1.getReportpage().equalsIgnoreCase("MonthlyReports")){
								i0015paid=dao.getI0015monthly(report1,clientID);
							}
							else if(report1.getReportpage().equalsIgnoreCase("QuarterlyReports")){
								i0015paid=dao.getI0015quarterly(report1,clientID);
							}
							else if(report1.getReportpage().equalsIgnoreCase("HalfyearlyReports")){
								i0015paid=dao.getI0015halfyearly(report1,clientID);
							}
							else if(report1.getReportpage().equalsIgnoreCase("AnnualReports")){
								i0015paid=dao.getI0015annual(report1,clientID);
							}
						
						
						System.out.println("i0015paid ---Payment-------> "+i0015paid.size());
						if(i0015paid.size()>0)
						{
							for (int i = 0; i < i0015paid.size(); i++)
							{
								String orderNumber=i0015paid.get(i).getTemOrderNumber();
								i0016paid=dao.geti0016paid(orderNumber);
								if(i0016paid.size()>0)
								{
									Report1 report=new Report1();
									report.setOrderNumber(i0015paid.get(i).getTemOrderNumber());
									report.setPrice(i0015paid.get(i).getTotalPrice());
									report.setStatus("Paid");
									report.setVendorName(i0016paid.get(0).getI0031().getI0025().getVendorPhoneNumber());
									reportlist.add(report);
								}
								
							}
						}		
					}
					if(report1.getTypes()[j].equals("Purchase Return"))
					{
						q=null;
						if(report1.getReportpage().equalsIgnoreCase("DailyReports")){
							returnlist=dao.getpreturndaily(report1,clientID);
						}
						else if(report1.getReportpage().equalsIgnoreCase("WeeklyReports")){
							returnlist=dao.getpreturnweekly(report1,clientID);
						}
						else if(report1.getReportpage().equalsIgnoreCase("MonthlyReports")){
							returnlist=dao.getpreturnmonthly(report1,clientID);
						}
						else if(report1.getReportpage().equalsIgnoreCase("QuarterlyReports")){
							returnlist=dao.getpreturquarterly(report1,clientID);
							
						}
						else if(report1.getReportpage().equalsIgnoreCase("HalfyearlyReports")){
							returnlist=dao.getpreturnhalfyearly(report1,clientID);
						}
						else if(report1.getReportpage().equalsIgnoreCase("AnnualReports")){
							returnlist=dao.getpreturnannual(report1,clientID);
						}
						System.out.println("return list---------- purchase return list size------>"+returnlist.size());
						if(returnlist.size()>0)
						{
							for (int i = 0; i < returnlist.size(); i++)
							{
								Report1 report=new Report1();
								report.setOrderNumber(returnlist.get(i).getPurchaseOrderNumber());	
								report.setPrice(returnlist.get(i).getPrice());
								report.setStatus("Puchase return");
								report.setReturnQuantity(returnlist.get(i).getQuantity());
								String orderNumber=returnlist.get(i).getPurchaseOrderNumber();
								i0016=dao.geti0016paid(orderNumber);
								if(i0016.size()>0){
									report.setVendorName(i0016.get(0).getI0031().getI0025().getVendorPhoneNumber());
								}
								reportlist.add(report);
							}
						}		
					}
				}
				report1.setVendorlist(reportlist);
			}
			else if(report1.getBusinesspartner().equalsIgnoreCase("Customer"))
			{
				for (int j = 0; j < report1.getTypes().length; j++) 
				{
					if(report1.getTypes()[j].equals("Sales Delivery"))
					{
						q=null;
						if(report1.getReportpage().equalsIgnoreCase("DailyReports")){
							i0021=dao.getdailysalesdelivery(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("WeeklyReports")){
							i0021=dao.getweeklysalesdelivery(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("MonthlyReports")){
							i0021=dao.getmonthlysalesdelivery(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("QuarterlyReports")){
							i0021=dao.getquarterlysalesdelivery(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("HalfyearlyReports")){
							i0021=dao.gethalfyearlysalesdelivery(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("AnnualReports")){
							i0021=dao.getannualsalesdelivery(clientID,report1);
						}
						if(i0021.size()>0)
						{
							for (int i = 0; i < i0021.size(); i++)
							{
								Report1 report=new Report1();
								report.setOrderNumber(i0021.get(i).getSalesOrderNumber());	
								report.setPrice(i0021.get(i).getCrossTotal());
								report.setStatus("Sales Delivered");
								report.setCustomername(i0021.get(i).getCustomerName());
								reportlist.add(report);
							}
						}		
					}if(report1.getTypes()[j].equals("Sales Payment"))
					{
						q=null;
						if(report1.getReportpage().equalsIgnoreCase("DailyReports")){
							i0021paid=dao.getdailysalespayment(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("WeeklyReports")){
							i0021paid=dao.getweeklysalespayment(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("MonthlyReports")){
							i0021paid=dao.getmonthlysalespayment(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("QuarterlyReports")){
							i0021paid=dao.getquaterlysalespayment(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("HalfyearlyReports")){
							i0021paid=dao.gethalfyearlysalespayment(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("AnnualReports")){
							i0021paid=dao.getannualsalespayment(clientID,report1);
						}
						if(i0021paid.size()>0)
						{
							
							for (int i = 0; i < i0021paid.size(); i++)
							{
								Report1 report=new Report1();
								System.out.println("list size and data"+i0021paid.get(i).getSalesOrderNumber());
								
								report.setOrderNumber(i0021paid.get(i).getSalesOrderNumber());	
								report.setPrice(i0021paid.get(i).getCrossTotal());
								report.setCustomername(i0021paid.get(i).getCustomerName());
								report.setStatus("Sales Paid");
								reportlist.add(report);
							}
						}
					}
					if(report1.getTypes()[j].equals("Sales Return")){
						q=null;
						
						if(report1.getReportpage().equalsIgnoreCase("DailyReports")){
							sreturn=dao.getdailySalesreturn(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("WeeklyReports")){
							sreturn=dao.getweeklySalesreturn(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("MonthlyReports")){
							sreturn=dao.getmonthlySalesreturn(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("QuarterlyReports")){
							sreturn=dao.getquarterlySalesreturn(clientID,report1);
							
						}
						else if(report1.getReportpage().equalsIgnoreCase("HalfyearlyReports")){
							sreturn=dao.gethalfyearlySalesreturn(clientID,report1);
						
						}
						else if(report1.getReportpage().equalsIgnoreCase("AnnualReports")){
							sreturn=dao.getannualSalesreturn(clientID,report1);
						}
						if(sreturn.size()>0)
						{
							for (int i = 0; i < sreturn.size(); i++)
							{
								if(sreturn.get(i).getI0021().getNatureOfBusiness().equalsIgnoreCase("Normal sales")){
									Report1 report=new Report1();
									report.setOrderNumber(sreturn.get(i).getSalesOrderNumber());
									sellingprice=new BigDecimal(sreturn.get(i).getSalesRecord().getSell_price()).multiply(new BigDecimal(sreturn.get(i).getQuantity()));
									report.setPrice(sellingprice.toString());
									report.setStatus("Sales Return");
									report.setVendorName(sreturn.get(i).getProductName());
									reportlist.add(report);
								}
							}
						}
					} if(report1.getTypes()[j].equals("Quick Sales Delivery"))
					{
						q=null;
						if(report1.getReportpage().equalsIgnoreCase("DailyReports")){
							qi0021=dao.getdailyQsalesdelivery(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("WeeklyReports")){
							qi0021=dao.getweeklyQsalesdelivery(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("MonthlyReports")){
							qi0021=dao.getmonthlyQsalesdelivery(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("QuarterlyReports")){
							qi0021=dao.getquarterlyQsalesdelivery(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("HalfyearlyReports")){
							qi0021=dao.gethalfyearlyQsalesdelivery(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("AnnualReports")){
							qi0021=dao.getannualQsalesdelivery(clientID,report1);
						}
						if(qi0021.size()>0)
						{
							for (int i = 0; i < qi0021.size(); i++)
							{
								Report1 report=new Report1();
								report.setOrderNumber(qi0021.get(i).getSalesOrderNumber());	
								report.setPrice(qi0021.get(i).getCrossTotal());
								report.setCustomername(qi0021.get(i).getCustomerName());
								report.setStatus("QuickSales Delivered");
								reportlist.add(report);
							}
						}	
					}
					else if(report1.getTypes()[j].equals("Quick Sales Payment"))
					{
						q=null;
						if(report1.getReportpage().equalsIgnoreCase("DailyReports")){
							qi0021paid=dao.getdailyQsalespaid(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("WeeklyReports")){
							qi0021paid=dao.getweeklyQsalespaid(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("MonthlyReports")){
							qi0021paid=dao.getmonthlyQsalespaid(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("QuarterlyReports")){
							qi0021paid=dao.getquarterlyQsalespaid(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("HalfyearlyReports")){
							qi0021paid=dao.gethalfyearlyQsalespaid(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("AnnualReports")){
							qi0021paid=dao.getannualQsalespaid(clientID,report1);
							
						}
						
						if(qi0021paid.size()>0)
						{
							for (int i = 0; i < qi0021paid.size(); i++)
							{
								Report1 report=new Report1();
								report.setOrderNumber(qi0021paid.get(i).getSalesOrderNumber());	
								report.setPrice(qi0021paid.get(i).getCrossTotal());
								report.setCustomername(qi0021paid.get(i).getCustomerName());
								report.setStatus("QuickSales Paid");
								reportlist.add(report);
							}
						}
					}else if(report1.getTypes()[j].equals("Quick Sales Return"))
					{
						if(report1.getReportpage().equalsIgnoreCase("DailyReports")){
							qsreturn=dao.getdailyQsalesreturn(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("WeeklyReports")){
							qsreturn=dao.getweeklyQsalesreturn(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("MonthlyReports")){
							qsreturn=dao.getmonthlyQsalesreturn(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("QuarterlyReports")){
							qsreturn=dao.getquarterlyQsalesreturn(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("HalfyearlyReports")){
							qsreturn=dao.gethalfyearlyQsalesreturn(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("AnnualReports")){
							qsreturn=dao.getannualQsalesreturn(clientID,report1);
						}
						if(qsreturn.size()>0)
						{
							for (int i = 0; i < qsreturn.size(); i++)
							{
								if(qsreturn.get(i).getI0021().getNatureOfBusiness().equalsIgnoreCase("Quick sales")){
									Report1 report=new Report1();
									report.setOrderNumber(qsreturn.get(i).getSalesOrderNumber());
									sellingprice=new BigDecimal(qsreturn.get(i).getSalesRecord().getSell_price()).multiply(new BigDecimal(qsreturn.get(i).getQuantity()));
									report.setPrice(sellingprice.toString());
									report.setStatus("QuickSales Return");
									report.setVendorName(qsreturn.get(i).getProductName());
									reportlist.add(report);
								}
							}
						}
					}
				}
				report1.setVendorlist(reportlist);
			}
			else if(report1.getBusinesspartner().equalsIgnoreCase("Transaction"))
			{
				System.out.println("Transaction");
				for (int j = 0; j < report1.getTypes().length; j++) 
				{
					if(report1.getTypes()[j].equals("Profit"))
					{
						q=null;
						
						if(report1.getReportpage().equalsIgnoreCase("DailyReports")){
							trans=dao.getdailyprofit(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("WeeklyReports")){
							trans=dao.getweeklyprofit(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("MonthlyReports")){
							trans=dao.getmonthlyprofit(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("QuarterlyReports")){
							trans=dao.getquaterlyprofit(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("HalfyearlyReports")){
							trans=dao.gethalfyearlyprofit(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("AnnualReports")){
							trans=dao.getannualprofit(clientID,report1);
						}
						System.out.println("trans -- "+trans.size());
						if(trans.size()>0){
							for (int i = 0; i < trans.size(); i++)
							{
								Report1 report=new Report1();
								report.setOrderNumber(trans.get(i).getTransactionNo());
								report.setPrice(trans.get(i).getAmount());
								report.setStatus("Credit");
								report.setTransdate(trans.get(i).getPayDate());
								reportlist.add(report);
							}
						}
					}
					else if(report1.getTypes()[j].equals("Loss"))
					{
						q=null;
						
						if(report1.getReportpage().equalsIgnoreCase("DailyReports")){
							trans=dao.getdailyloss(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("WeeklyReports")){
							trans=dao.getweeklyloss(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("MonthlyReports")){
							trans=dao.getmonthlyloss(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("QuarterlyReports")){
							trans=dao.getquarterlyloss(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("HalfyearlyReports")){
							trans=dao.gethalfyearlyloss(clientID,report1);
						}
						else if(report1.getReportpage().equalsIgnoreCase("AnnualReports")){
							trans=dao.getannualloss(clientID,report1);
						}
						System.out.println("trans -- "+trans.size());
						if(trans.size()>0){
							for (int i = 0; i < trans.size(); i++)
							{
								Report1 report=new Report1();
								report.setOrderNumber(trans.get(i).getTransactionNo());
								report.setPrice(trans.get(i).getAmount());
								report.setStatus("Debit");
								report.setTransdate(trans.get(i).getPayDate());
								reportlist.add(report);
							}
						}
					}
				}
				report1.setVendorlist(reportlist);
				System.out.println("reportlist  --- "+reportlist.size());
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public List<String> getmenus() {
		return dao.getmenus();
	}

	public String insertdepartment(String department) {
		return dao.insertdepartment(department);
	}

	public String vendorApproval(ArrayList<VendorViewFormMB> ven1,String approvalStatus) {
		logger.info("inside vendor approval bo method calling");
		String status="Fail";
		try{
			for (int i = 0; i < ven1.size(); i++) {
				if(ven1.get(i).isVendorCheck()==true){
					status=dao.vendorApproval(ven1.get(i).getVendor_Id(),approvalStatus);
				}
			}
		}catch(Exception e){
			logger.warn("Inside Exception"+e);
		}
		return status;
	}

	public String customerApproval(ArrayList<BuyersViewMB> catbuy,String approvalStatus) {
		logger.info("inside customer approval bo method calling");
		String status="Fail";
		try{
			for (int i = 0; i < catbuy.size(); i++) {
				if(catbuy.get(i).isCustomerCheck()==true){
					status=dao.customerApproval(catbuy.get(i).getBuyer_ID(),approvalStatus);
				}
			}
		}catch(Exception e){
			logger.warn("Inside Exception"+e.getMessage());
		}
		return status;
	}
	
	public List<ProductViewMB> getcategoryList(String approvalStatus) {
		return dao.getcategoryList(approvalStatus);
	}
	
	public String categoryApproval(List<ProductViewMB> categoryList) {
		return dao.categoryApproval(categoryList);
	}
	
	public String saveAcountDeposit(String clientID,AccountsDatabean accountsDatabean){
		return adao.saveAcountDeposit(clientID,accountsDatabean);
	}
	/*
	public void getCOAdetails(String clientID, AccountsDatabean accountsDatabean){
		System.out.println("get coa details in bo");
		List<AccountType> accountType=null;
		List<AccountDeposit> accountDeposit=null;
		List<AccountPayment> accountPayment=null;
		List<Transaction> transaction=null;
		List<AccountsDatabean> accountsDetails=new ArrayList<AccountsDatabean>();
		List<AccountsDatabean> purchases=null;
		List<AccountsDatabean> sales=null;
		try{
			accountType=adao.getAllTypes();
			if(accountType.size()>0){
				for (int i = 0; i < accountType.size(); i++) {
					AccountsDatabean accountsList=new AccountsDatabean();
					accountsList.setAccount_type(accountType.get(i).getAccountType());
					accountsList.setAccount_name(accountType.get(i).getDescription());
					BigDecimal amount=BigDecimal.valueOf(0);
					accountDeposit=adao.getDeposit(clientID,accountType.get(i).getDescription());
					if(accountDeposit.size()>0){
						if(accountDeposit.get(0).getBalance()==null || accountDeposit.get(0).getBalance().equals("")) amount=BigDecimal.valueOf(0);
						else amount=new BigDecimal(accountDeposit.get(0).getBalance());
					}
					transaction=adao.getTransactionDetails(clientID);
					if(transaction.size()>0){
						for (int j = 0; j < transaction.size(); j++) {
							accountPayment=adao.getPaymentDetails(transaction.get(j).getTransactionId());
							if(accountPayment.size()>0){
								BigDecimal penAmount=BigDecimal.valueOf(0);BigDecimal balAmount=BigDecimal.valueOf(0);
								BigDecimal amounts=BigDecimal.valueOf(0);
								if(transaction.get(j).getTransactionStatus().equals("credited")){
									if(transaction.get(j).getPaymentStatus().equals("Pending")){
										for (int k = 0; k < accountPayment.size(); k++) {
											if(accountType.get(i).getDescription().equals(accountPayment.get(k).getAccountDeposit())){
												penAmount=penAmount.add(new BigDecimal(accountPayment.get(k).getPaidAmount()));
											}	
											amounts=amounts.add(new BigDecimal(accountPayment.get(k).getPaidAmount()));
										}
										if(accountType.get(i).getDescription().equals("Accounts Receivable (A/R)")){
											balAmount=new BigDecimal(transaction.get(j).getAmount()).subtract(amounts);
											amount=amount.add(balAmount);
										}else{
											amount=amount.add(penAmount); 
										}
									}else if(transaction.get(j).getPaymentStatus().equals("Paid")){
										for (int k = 0; k < accountPayment.size(); k++) {
											if(accountType.get(i).getDescription().equals(accountPayment.get(k).getAccountDeposit())){
												penAmount=penAmount.add(new BigDecimal(accountPayment.get(k).getPaidAmount()));
											}	
											amounts=amounts.add(new BigDecimal(accountPayment.get(k).getPaidAmount()));
										}
										if(accountType.get(i).getDescription().equals("Accounts Receivable (A/R)")){
											balAmount=new BigDecimal(transaction.get(j).getAmount()).subtract(amounts);
											amount=amount.add(balAmount);
										}else{
											amount=amount.add(penAmount); 
										}
									}
								}else if(transaction.get(j).getTransactionStatus().equals("debited")){
									if(transaction.get(j).getPaymentStatus().equals("Pending")){
										for (int k = 0; k < accountPayment.size(); k++) {
											if(accountType.get(i).getDescription().equals("Cash on hand")){
												penAmount=penAmount.subtract(new BigDecimal(accountPayment.get(k).getPaidAmount()));
											}	
											amounts=amounts.add(new BigDecimal(accountPayment.get(k).getPaidAmount()));
										}
										if(accountType.get(i).getDescription().equals("Accounts Payable(A/P)")){
											balAmount=new BigDecimal(transaction.get(j).getAmount()).subtract(amounts);
											amount=amount.add(balAmount);
										}else{
											amount=amount.add(penAmount); 
										}
									}else if(transaction.get(j).getPaymentStatus().equals("Paid")){
										for (int k = 0; k < accountPayment.size(); k++) {
											if(accountType.get(i).getDescription().equals("Cash on hand")){
												penAmount=penAmount.subtract(new BigDecimal(accountPayment.get(k).getPaidAmount()));
											}	
											amounts=amounts.add(new BigDecimal(accountPayment.get(k).getPaidAmount()));
										}
										if(accountType.get(i).getDescription().equals("Accounts Payable(A/P)")){
											balAmount=new BigDecimal(transaction.get(j).getAmount()).subtract(amounts);
											amount=amount.add(balAmount);
										}else{
											amount=amount.add(penAmount); 
										}
									}
								}								
							}else{
								if(transaction.get(j).getTransactionStatus().equals("credited")){
									if(transaction.get(j).getTransactionType().equals("Estimate")){
										amount=amount.add(BigDecimal.valueOf(0)); 			
									}else{
										if(accountType.get(i).getDescription().equals("Accounts Receivable (A/R)")){
											amount=amount.add(new BigDecimal(transaction.get(j).getAmount()));										
										}
									}																		
								}else if(transaction.get(j).getTransactionStatus().equals("debited")){
									if(transaction.get(j).getTransactionType().equals("Expense")){
										if(accountType.get(i).getDescription().equals("Cash on hand")){
											amount=amount.subtract(new BigDecimal(transaction.get(j).getAmount())); 
										}
									}else{
										if(accountType.get(i).getDescription().equals("Accounts Payable(A/P)")){
											amount=amount.add(new BigDecimal(transaction.get(j).getAmount()));										
										}
									}									
								}
							}							
						}						
					}
					sales=AccountsJDBC.salesAccounts(clientID,accountsDatabean);
					if(sales.size()>0){
						for (int k = 0; k < sales.size(); k++) {
							if(sales.get(k).getAccount_name().equals("Quick sales")){
								if(accountType.get(i).getDescription().equals("Cash on hand")){
									amount=amount.add(new BigDecimal(sales.get(k).getAccount_amount()));										
								}
							}else if(sales.get(k).getAccount_name().equals("Normal sales")){
								if(sales.get(k).getStatus()==null){
									if(accountType.get(i).getDescription().equals("Accounts Receivable (A/R)")){
										amount=amount.add(new BigDecimal(sales.get(k).getAccount_amount()));										
									}
								}else if(sales.get(k).getStatus()!=null){
									if(sales.get(k).getStatus().equals("pending")){
										if(accountType.get(i).getDescription().equals("Accounts Receivable (A/R)")){
											amount=amount.add(new BigDecimal(sales.get(k).getBalance()));										
										}else if(!accountType.get(i).getDescription().equals("Accounts Receivable (A/R)")){
											if(accountType.get(i).getDescription().equals(sales.get(k).getAccount_description())){
												amount=amount.add(new BigDecimal(sales.get(k).getCredit()));										
											}
										}
									}else{
										if(accountType.get(i).getDescription().equals(sales.get(k).getAccount_description())){
											amount=amount.add(new BigDecimal(sales.get(k).getCredit()));										
										}
									}
								}	
							}							
						}
					}
					purchases=AccountsJDBC.purchaseAccounts(clientID,accountsDatabean);
					if(purchases.size()>0){
						for (int k = 0; k < purchases.size(); k++) {
							if(purchases.get(k).getStatus()==null){
								if(accountType.get(i).getDescription().equals("Accounts Payable(A/P)")){
									amount=amount.add(new BigDecimal(purchases.get(k).getAccount_amount()));										
								}
							}else if(purchases.get(k).getStatus()!=null){
								if(purchases.get(k).getStatus().equals("pending")){
									if(accountType.get(i).getDescription().equals("Accounts Payable(A/P)")){
										amount=amount.add(new BigDecimal(purchases.get(k).getBalance()));										
									}else if(!accountType.get(i).getDescription().equals("Accounts Payable(A/P)")){
										if(accountType.get(i).getDescription().equals(purchases.get(k).getAccount_description())){
											amount=amount.subtract(new BigDecimal(purchases.get(k).getCredit()));										
										}
									}
								}else{
									if(accountType.get(i).getDescription().equals(purchases.get(k).getAccount_description())){
										amount=amount.subtract(new BigDecimal(purchases.get(k).getCredit()));										
									}
								}
							}
						}
					}
					accountsList.setAccount_amount(String.valueOf(amount));
					accountsDetails.add(accountsList);
				}				
				accountsDatabean.setAccounts(accountsDetails);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	*/
	public void getviewCOAAccounts(String clientID,AccountsDatabean accountsDatabean){
		
	}
	
	public void getGeneralLedger(String clientID,AccountsDatabean accountsDatabean){
		logger.info("[getGeneralLedger()]-----------------------Inside getGeneralLedger() in BoImpln Calling-----------------------");
		try{
			accountsDatabean.setClientID(clientID);
			AccountsJDBC.getgeneralLedgerList(accountsDatabean);
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("-----------------Inside Exception------------------------------");
		}
	}

	
	@Override
	public void AccountsBalanceCal(AccountsDatabean accountsDatabean) {
		logger.info("[AccountsBalanceCal()]-----------------------Inside AccountsBalanceCal() in BoImpln Calling-----------------------");
		Double banktemp=0.0;
		Double accrectemp=0.0;Double othercurrassetstemp=0.0;Double fixedassetstemp=0.0;
		Double othherassetstemp=0.0;Double accpayabletemp=0.0;Double creditcardtemp=0.0;
		Double othercurrentlibtemp=0.0;Double longtermlibtemp=0.0;Double currentlibtemp=0.0;
		accountsDatabean.setTotalcreditcards(0.0);accountsDatabean.setTotalcurrliability(0.0);
		accountsDatabean.setTotalassets(0.0);accountsDatabean.setTotalaccpayable(0.0);
		accountsDatabean.setTotalliablity(0.0);accountsDatabean.setTotalbankassets(0.0);
		accountsDatabean.setTotalaccrecassets(0.0);accountsDatabean.setTotalothercurrentassets(0.0);
		accountsDatabean.setTotalfixedassets(0.0);accountsDatabean.setTotalotherassets(0.0);
		accountsDatabean.setTotalEquities(0.0);accountsDatabean.setNetIncome(0.0);
		accountsDatabean.setTotalothercurrliability(0.0);accountsDatabean.setTotallongtermliability(0.0);
		try{
			accountsDatabean.setAssetsList(new ArrayList<AccountsDatabean>());accountsDatabean.setBankaccountsList(new ArrayList<AccountsDatabean>());
			accountsDatabean.setOthercurrentassetsList(new ArrayList<AccountsDatabean>());accountsDatabean.setFixedassetsList(new ArrayList<AccountsDatabean>());
			accountsDatabean.setAccountReceivableList(new ArrayList<AccountsDatabean>());accountsDatabean.setEquities(new ArrayList<AccountsDatabean>());
			accountsDatabean.setLiabilityList(new ArrayList<AccountsDatabean>());accountsDatabean.setCreditcardList(new ArrayList<AccountsDatabean>());
			accountsDatabean.setOthercurrentlibList(new ArrayList<AccountsDatabean>());accountsDatabean.setLongtermlibList(new ArrayList<AccountsDatabean>());
			accountsDatabean.setAccounts(new ArrayList<AccountsDatabean>());accountsDatabean.setCurrLibList(new ArrayList<AccountsDatabean>());
			accountsDatabean.setAccounts(AccountsJDBC.gettrialbalandbalsheetList(accountsDatabean));
			for (int i = 0; i < accountsDatabean.getAccounts().size(); i++) {
				AccountsDatabean tempaccount=new AccountsDatabean();
				tempaccount.setAccount_name(accountsDatabean.getAccounts().get(i).getAccount_name());
				tempaccount.setBalance(accountsDatabean.getAccounts().get(i).getBalance());
				if(accountsDatabean.getAccounts().get(i).getAccount_type().equalsIgnoreCase("Bank")){
					accountsDatabean.getBankaccountsList().add(tempaccount);
					banktemp=banktemp+Double.valueOf(accountsDatabean.getAccounts().get(i).getBalance());
					accountsDatabean.setTotalbankassets(banktemp);
				}
				if(accountsDatabean.getAccounts().get(i).getAccount_type().equalsIgnoreCase("Accounts Receivable (A/R)")){
					accountsDatabean.getAccountReceivableList().add(tempaccount);
					accrectemp=accrectemp+Double.valueOf(accountsDatabean.getAccounts().get(i).getBalance());
					accountsDatabean.setTotalaccrecassets(accrectemp);
				}
				if(accountsDatabean.getAccounts().get(i).getAccount_type().equalsIgnoreCase("Other Current Assets")){
					accountsDatabean.getOthercurrentassetsList().add(tempaccount);
					othercurrassetstemp=othercurrassetstemp+Double.valueOf(accountsDatabean.getAccounts().get(i).getBalance());
					accountsDatabean.setTotalothercurrentassets(othercurrassetstemp);
				}
				if(accountsDatabean.getAccounts().get(i).getAccount_type().equalsIgnoreCase("Fixed Assets")){
					accountsDatabean.getFixedassetsList().add(tempaccount);
					fixedassetstemp=fixedassetstemp+Double.valueOf(accountsDatabean.getAccounts().get(i).getBalance());
					accountsDatabean.setTotalfixedassets(fixedassetstemp);
				}
				if(accountsDatabean.getAccounts().get(i).getAccount_type().equalsIgnoreCase("Other Assets")){
					accountsDatabean.getAssetsList().add(tempaccount);
					othherassetstemp=othherassetstemp+Double.valueOf(accountsDatabean.getAccounts().get(i).getBalance());
					accountsDatabean.setTotalotherassets(othherassetstemp);
				}
				if(accountsDatabean.getAccounts().get(i).getAccount_type().equalsIgnoreCase("Accounts Payable (A/P)")){
					accountsDatabean.getLiabilityList().add(tempaccount);
					accpayabletemp=accpayabletemp+Double.valueOf(accountsDatabean.getAccounts().get(i).getBalance());
					accountsDatabean.setTotalaccpayable(accpayabletemp);
				}
				if(accountsDatabean.getAccounts().get(i).getAccount_type().equalsIgnoreCase("Credit Card")){
					accountsDatabean.getCreditcardList().add(tempaccount);
					creditcardtemp=creditcardtemp+Double.valueOf(accountsDatabean.getAccounts().get(i).getBalance());
					accountsDatabean.setTotalcreditcards(creditcardtemp);
				}
				if(accountsDatabean.getAccounts().get(i).getAccount_type().equalsIgnoreCase("Current liabilities")){
					accountsDatabean.getCurrLibList().add(tempaccount);
					currentlibtemp=currentlibtemp+Double.valueOf(accountsDatabean.getAccounts().get(i).getBalance());
					accountsDatabean.setTotalcurrliability(currentlibtemp);
				}
				if(accountsDatabean.getAccounts().get(i).getAccount_type().equalsIgnoreCase("Other Current Liabilities")){
					accountsDatabean.getOthercurrentlibList().add(tempaccount);
					othercurrentlibtemp=othercurrentlibtemp+Double.valueOf(accountsDatabean.getAccounts().get(i).getBalance());
					accountsDatabean.setTotalothercurrliability(othercurrentlibtemp);
				}
				if(accountsDatabean.getAccounts().get(i).getAccount_type().equalsIgnoreCase("Notes Payable")){
					accountsDatabean.getLongtermlibList().add(tempaccount);
					longtermlibtemp=longtermlibtemp+Double.valueOf(accountsDatabean.getAccounts().get(i).getBalance());
					accountsDatabean.setTotallongtermliability(longtermlibtemp);
				}
				if(accountsDatabean.getAccounts().get(i).getAccount_type().equalsIgnoreCase("Equity")){
					accountsDatabean.getEquities().add(tempaccount);
					accountsDatabean.setTotalEquities(accountsDatabean.getTotalEquities()+Double.valueOf(accountsDatabean.getAccounts().get(i).getBalance()));
					
				}
			}
			accountsDatabean.setTotalassets(banktemp+accrectemp+othercurrassetstemp+fixedassetstemp+othherassetstemp);
			accountsDatabean.setTotalliablity(accpayabletemp+creditcardtemp+othercurrentlibtemp+longtermlibtemp+currentlibtemp);
			accountsDatabean.setAccountStatus("PLBS");
			getProfitLoss(accountsDatabean);
			AccountsDatabean tempaccount1=new AccountsDatabean();
			if(accountsDatabean.getProfitAmount().compareTo(BigDecimal.ZERO)==1){
				tempaccount1.setAccount_name("Profit for the year");
			}else if(accountsDatabean.getProfitAmount().compareTo(BigDecimal.ZERO)==-1){
				tempaccount1.setAccount_name("Loss for the year");
			}else{
				tempaccount1.setAccount_name("Net Income");
			}
			tempaccount1.setBalance(String.valueOf(accountsDatabean.getProfitAmount()));
			accountsDatabean.getEquities().add(tempaccount1);
			Double temp = accountsDatabean.getTotalEquities()+Double.valueOf(tempaccount1.getBalance());
			accountsDatabean.setTotalEquities(temp);
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("-----------------Inside Exception------------------------------");
		}finally{
			banktemp=0.0;accrectemp=0.0;othercurrassetstemp=0.0;fixedassetstemp=0.0;currentlibtemp=0.0;
			othherassetstemp=0.0;accpayabletemp=0.0;creditcardtemp=0.0;othercurrentlibtemp=0.0;longtermlibtemp=0.0;
		}
	}
	
	public void getTrialBalance(String clientID,AccountsDatabean accountsDatabean){
		logger.info("[getTrialBalance()]-----------------------Inside getTrialBalance() in BoImpln Calling-----------------------");
		BigDecimal totdebitamt=BigDecimal.valueOf(0);BigDecimal totcreditamt=BigDecimal.valueOf(0);
		BigDecimal tempamt=BigDecimal.valueOf(0);Double totalassets=0.0;Double totalliabilities=0.0;
		try {
			DecimalFormat df = new DecimalFormat(".##");
			List<String> debitList=Arrays.asList("Accounts Receivable (A/R)","Other Current Assets","Bank","Fixed Assets","Other Assets","Cost of Goods Sold","Expenses","Other Expenses","Cash","Purchase","Direct Expenses","Indirect Expenses");
			List<String> creditList=Arrays.asList("Accounts Payable (A/P)","Credit Card","Other Current Liabilities","Notes Payable","Equity","Income","Other Income","Current liabilities","Sales");
			accountsDatabean.setAccounts(new ArrayList<AccountsDatabean>());
			accountsDatabean.setAccounts(AccountsJDBC.gettrialbalandbalsheetList(accountsDatabean));
			if(accountsDatabean.getAccounts().size()>0){
				for (int i = 0; i < accountsDatabean.getAccounts().size(); i++) {
					AccountsDatabean tempaccount=new AccountsDatabean();
					tempaccount.setAccount_name(accountsDatabean.getAccounts().get(i).getAccount_name());
					if(debitList.contains(accountsDatabean.getAccounts().get(i).getAccount_type())){
						if (Double.valueOf(accountsDatabean.getAccounts().get(i).getBalance())<0) {
							tempaccount.setCredit(tempamt.subtract(new BigDecimal(accountsDatabean.getAccounts().get(i).getBalance())));
							totcreditamt=totcreditamt.add(tempamt.subtract(new BigDecimal(accountsDatabean.getAccounts().get(i).getBalance())));
						}else{
							tempaccount.setDebit(new BigDecimal(accountsDatabean.getAccounts().get(i).getBalance()));
							totdebitamt=totdebitamt.add(new BigDecimal(accountsDatabean.getAccounts().get(i).getBalance()));
						}
					}
					else if(creditList.contains(accountsDatabean.getAccounts().get(i).getAccount_type())){
						if (Double.valueOf(accountsDatabean.getAccounts().get(i).getBalance())<0) {
							tempaccount.setDebit(tempamt.subtract(new BigDecimal(accountsDatabean.getAccounts().get(i).getBalance())));
							totdebitamt=totdebitamt.add(tempamt.subtract(new BigDecimal(accountsDatabean.getAccounts().get(i).getBalance())));
						}else{
							tempaccount.setCredit(new BigDecimal(accountsDatabean.getAccounts().get(i).getBalance()));
							totcreditamt=totcreditamt.add(new BigDecimal(accountsDatabean.getAccounts().get(i).getBalance()));
						}
						
					}
				accountsDatabean.getTrialBalance().add(tempaccount);
				}
				String totaldebitamt=String.valueOf(totdebitamt);String totalcreditamt=String.valueOf(totcreditamt);
				totalassets=Double.valueOf(totaldebitamt);
				totalliabilities=Double.valueOf(totalcreditamt);
				accountsDatabean.setTotalassets(Double.valueOf(df.format(totalassets)));
				accountsDatabean.setTotalliablity(Double.valueOf(df.format(totalliabilities)));
			}
		} catch (Exception e) {
			logger.warn("-----------------Inside Exception------------------------------");
		}finally{
			totalassets=0.0;totalliabilities=0.0;
		}
	}
	
	/*public void getProfitLoss(String clientID, AccountsDatabean accountsDatabean){
		System.out.println("get P&L details in bo");
		List<AccountType> accountType=null;
		List<AccountDeposit> accountDeposit=null;
		List<AccountPayment> accountPayment=null;
		List<Transaction> transaction=null;
		List<AccountsDatabean> profits=new ArrayList<AccountsDatabean>();
		List<AccountsDatabean> loss=new ArrayList<AccountsDatabean>();
		List<AccountsDatabean> sales=null;
		List<AccountsDatabean> puchases=null;
		try{
			accountType=adao.getAllTypes();
			System.out.println("account type size-----"+accountType.size());
			if(accountType.size()>0){
				System.out.println("Inside if-----");
				for (int i = 0; i < accountType.size(); i++) {
					System.out.println("Inside for loop-----");
					BigDecimal debit=BigDecimal.valueOf(0);
					BigDecimal credit=BigDecimal.valueOf(0);
					AccountsDatabean accountsList=new AccountsDatabean();
					accountsList.setAccount_name(accountType.get(i).getDescription());
					accountDeposit=adao.getDeposit(clientID,accountType.get(i).getDescription(),accountsDatabean);
					System.out.println("Inside accountDeposit size-----"+accountDeposit.size());
					if(accountDeposit.size()>0){
						System.out.println("inside accountDeposit if---");
						if(accountDeposit.get(0).getBalance()==null || accountDeposit.get(0).getBalance().equals("")){}
						else{
							System.out.println("inside getBalance else---");
							if(accountType.get(i).getAccountType().equals("Accounts Receivable (A/R)") || accountType.get(i).getAccountType().equals("Bank")
									|| accountType.get(i).getAccountType().equals("Other Current Liabilities") || accountType.get(i).getAccountType().equals("Long Term Liabilities")
									|| accountType.get(i).getAccountType().equals("Income") || accountType.get(i).getAccountType().equals("Other Income")){
								credit=credit.add(new BigDecimal(accountDeposit.get(0).getBalance()));
							}else{
								debit=debit.add(new BigDecimal(accountDeposit.get(0).getBalance()));
							}
						}
					}
					transaction=adao.getTransactionDetails(clientID,accountsDatabean);
					if(transaction.size()>0){
						for (int j = 0; j < transaction.size(); j++) {
							BigDecimal penAmount=BigDecimal.valueOf(0);BigDecimal balAmount=BigDecimal.valueOf(0);
							BigDecimal amounts=BigDecimal.valueOf(0);
							accountPayment=adao.getPaymentDetails(transaction.get(j).getTransactionId());
							if(accountPayment.size()>0){								
								if(transaction.get(j).getTransactionStatus().equals("credited")){
									if(transaction.get(j).getPaymentStatus().equals("Paid")){
										for (int k = 0; k < accountPayment.size(); k++) {
											if(accountType.get(i).getDescription().equals(accountPayment.get(k).getAccountDeposit())){
												penAmount=penAmount.add(new BigDecimal(accountPayment.get(k).getPaidAmount()));
											}	
											amounts=amounts.add(new BigDecimal(accountPayment.get(k).getPaidAmount()));
										}
										if(accountType.get(i).getDescription().equals("Accounts Receivable (A/R)")){
											balAmount=new BigDecimal(transaction.get(j).getAmount()).subtract(amounts);
											credit=credit.add(balAmount);
										}else{
											String type=adao.getType(accountType.get(i).getDescription());
											if(type.equals("Bank")|| type.equals("Other Current Liabilities") || type.equals("Long Term Liabilities") 
													|| type.equals("Income")|| type.equals("Other Income")){
												credit=credit.add(new BigDecimal(accountDeposit.get(0).getBalance()));
											}else{
												debit=debit.add(penAmount);
											}  
										}
									}
								}else if(transaction.get(j).getTransactionStatus().equals("debited")){
									if(transaction.get(j).getPaymentStatus().equals("Paid")){
										for (int k = 0; k < accountPayment.size(); k++) {
											if(accountType.get(i).getDescription().equals("Cash on hand")){
												penAmount=penAmount.subtract(new BigDecimal(accountPayment.get(k).getPaidAmount()));
											}	
											amounts=amounts.add(new BigDecimal(accountPayment.get(k).getPaidAmount()));
										}
										if(accountType.get(i).getDescription().equals("Accounts Payable(A/P)")){
											balAmount=new BigDecimal(transaction.get(j).getAmount()).subtract(amounts);
											debit=debit.add(balAmount);
										}else if(accountType.get(i).getDescription().equals("Cash on hand")){
											credit=credit.add(penAmount); 
										}
									}
								}
							}else{
								if(transaction.get(j).getTransactionType().equals("Estimate")){
									if(accountType.get(i).getDescription().equals("Accounts Receivable (A/R)")){
										credit=credit.add(new BigDecimal(transaction.get(j).getAmount())); 
									}											
								}
								if(transaction.get(j).getTransactionType().equals("Expense")){
									if(accountType.get(i).getDescription().equals("Accounts Payable(A/P)")){
										debit=debit.add(new BigDecimal(transaction.get(j).getAmount())); 
									}
								}
							}
						}
					}
					sales=AccountsJDBC.salesAccounts(clientID,accountsDatabean);
					System.out.println("inside purchases list size----"+sales.size());
					if(sales.size()>0){
						for (int k = 0; k < sales.size(); k++) {
							if(sales.get(k).getAccount_name().equals("Quick sales")){
								if(accountType.get(i).getDescription().equals("Cash on hand")){
									credit=credit.add(new BigDecimal(sales.get(k).getAccount_amount()));										
								}
							}else if(sales.get(k).getAccount_name().equals("Normal sales")){
								if(sales.get(k).getStatus()!=null){
									if(sales.get(k).getStatus().equals("paid")){
										if(accountType.get(i).getDescription().equals(sales.get(k).getAccount_description())){
											credit=credit.add(new BigDecimal(sales.get(k).getCredit()));										
										}
									}
								}
							}
						}
					}
					puchases=AccountsJDBC.purchaseAccounts(clientID,accountsDatabean);
					System.out.println("inside purchases list size----"+puchases.size());
					if(puchases.size()>0){
						for (int k = 0; k < puchases.size(); k++) {							
							if(puchases.get(k).getStatus()!=null){
								if(puchases.get(k).getStatus().equals("paid")){
									if(accountType.get(i).getDescription().equals(puchases.get(k).getAccount_description())){
										debit=debit.add(new BigDecimal(puchases.get(k).getCredit()));										
									}
								}
							}
						}
					}
					if(accountType.get(i).getAccountType().equals("Accounts Receivable (A/R)") || accountType.get(i).getAccountType().equals("Bank")
							|| accountType.get(i).getAccountType().equals("Other Current Liabilities") || accountType.get(i).getAccountType().equals("Long Term Liabilities")
							|| accountType.get(i).getAccountType().equals("Income") || accountType.get(i).getAccountType().equals("Other Income")){
						accountsList.setCredit(String.valueOf(credit));
						profits.add(accountsList);
					}else{
						accountsList.setDebit(String.valueOf(debit));
						loss.add(accountsList);
					}
				}		
				List<AccountsDatabean> paccounts=new ArrayList<AccountsDatabean>();
				List<AccountsDatabean> laccounts=new ArrayList<AccountsDatabean>();
				BigDecimal credits=BigDecimal.valueOf(0);
				BigDecimal debits=BigDecimal.valueOf(0);
				for (int j = 0; j < profits.size(); j++) {
					if(!profits.get(j).getCredit().equals("0")){
						AccountsDatabean accdtabean=new AccountsDatabean();
						accdtabean.setAccount_name(profits.get(j).getAccount_name());
						if(profits.get(j).getCredit().equals("0")) accdtabean.setCredit("");
						else{
							accdtabean.setCredit(profits.get(j).getCredit());
							credits=credits.add(new BigDecimal(profits.get(j).getCredit()));
						}
						paccounts.add(accdtabean);
					}	
				}
				accountsDatabean.setProfits(paccounts);
				for (int j = 0; j < loss.size(); j++) {
					if(!loss.get(j).getDebit().equals("0")){
						AccountsDatabean accdtabean=new AccountsDatabean();
						accdtabean.setAccount_name(loss.get(j).getAccount_name());						
						if(loss.get(j).getDebit().equals("0")) accdtabean.setDebit("");
						else{
							accdtabean.setDebit(loss.get(j).getDebit());
							debits=debits.add(new BigDecimal(loss.get(j).getDebit()));
						}
						laccounts.add(accdtabean);
					}	
				}
				accountsDatabean.setLoss(laccounts);
				accountsDatabean.setCredit(String.valueOf(credits));
				accountsDatabean.setDebit(String.valueOf(debits));
				accountsDatabean.setProfit(String.valueOf(credits.subtract(debits)));
				if(accountsDatabean.getReportPeriod().equals("This month")){
					accountsDatabean.setMonthProfit(String.valueOf(credits));
					accountsDatabean.setMonthLoss(String.valueOf(debits));
					accountsDatabean.setMonthNet(String.valueOf(credits.subtract(debits)));
				}				
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}*/
	
	/*public void getBalanceSheet(String clientID,AccountsDatabean accountsDatabean){
		System.out.println("get P&L details in bo");
		List<AccountType> accountType=null;
		List<AccountDeposit> accountDeposit=null;
		List<AccountPayment> accountPayment=null;
		List<Transaction> transaction=null;
		//List<I0021> i0021=null;List<I0022> i0022=null;List<I0023> i0023=null;List<I0015> i0015=null;
		List<AccountsDatabean> profits=new ArrayList<AccountsDatabean>();
		List<AccountsDatabean> loss=new ArrayList<AccountsDatabean>();
		List<AccountsDatabean> equityy=new ArrayList<AccountsDatabean>();
		List<AccountsDatabean> sales=null;
		List<AccountsDatabean> purchases=null;
		try{
			accountType=adao.getAllTypes();
			if(accountType.size()>0){
				for (int i = 0; i < accountType.size(); i++) {
					BigDecimal debit=BigDecimal.valueOf(0);
					BigDecimal credit=BigDecimal.valueOf(0);
					BigDecimal equity=BigDecimal.valueOf(0);
					AccountsDatabean accountsList=new AccountsDatabean();
					accountsList.setAccount_name(accountType.get(i).getDescription());
					accountDeposit=adao.getDeposit(clientID,accountType.get(i).getDescription(),accountsDatabean);
					if(accountDeposit.size()>0){
						if(accountDeposit.get(0).getBalance()==null || accountDeposit.get(0).getBalance().equals("")){}
						else{
							if(accountType.get(i).getAccountType().equals("Accounts Receivable (A/R)") || accountType.get(i).getAccountType().equals("Bank")
									|| accountType.get(i).getAccountType().equals("Other Current Assets") || accountType.get(i).getAccountType().equals("Fixed Asstes")
									|| accountType.get(i).getAccountType().equals("Income") || accountType.get(i).getAccountType().equals("Other Income")
									|| accountType.get(i).getAccountType().equals("Other Asstes")){
								credit=credit.add(new BigDecimal(accountDeposit.get(0).getBalance()));
							}else if(accountType.get(i).getAccountType().equals("Other Current Liabilities") || accountType.get(i).getAccountType().equals("Long Term Liabilities")
									|| accountType.get(i).getAccountType().equals("Accounts Payable(A/P)")){
								debit=debit.add(new BigDecimal(accountDeposit.get(0).getBalance()));
							}else if(accountType.get(i).getAccountType().equals("Equity")) {
								equity=equity.add(new BigDecimal(accountDeposit.get(0).getBalance()));
							}
						}
					}
					transaction=adao.getTransactionDetails(clientID,accountsDatabean);
					if(transaction.size()>0){
						for (int j = 0; j < transaction.size(); j++) {
							BigDecimal penAmount=BigDecimal.valueOf(0);BigDecimal balAmount=BigDecimal.valueOf(0);
							BigDecimal amounts=BigDecimal.valueOf(0);
							accountPayment=adao.getPaymentDetails(transaction.get(j).getTransactionId());
							if(accountPayment.size()>0){								
								if(transaction.get(j).getTransactionStatus().equals("credited")){
									if(transaction.get(j).getPaymentStatus().equals("Paid")){
										for (int k = 0; k < accountPayment.size(); k++) {
											if(accountType.get(i).getDescription().equals(accountPayment.get(k).getAccountDeposit())){
												penAmount=penAmount.add(new BigDecimal(accountPayment.get(k).getPaidAmount()));
											}	
											amounts=amounts.add(new BigDecimal(accountPayment.get(k).getPaidAmount()));
										}
										if(accountType.get(i).getDescription().equals("Accounts Receivable (A/R)")){
											balAmount=new BigDecimal(transaction.get(j).getAmount()).subtract(amounts);
											credit=credit.add(balAmount);
										}else{
											String type=adao.getType(accountType.get(i).getDescription());
											if(type.equals("Bank")|| type.equals("Other Current Liabilities") || type.equals("Long Term Liabilities") 
													|| type.equals("Income")|| type.equals("Other Income")){
												credit=credit.add(new BigDecimal(accountDeposit.get(0).getBalance()));
											}else{
												debit=debit.add(penAmount);
											}  
										}
									}
								}else if(transaction.get(j).getTransactionStatus().equals("debited")){
									if(transaction.get(j).getPaymentStatus().equals("Paid")){
										for (int k = 0; k < accountPayment.size(); k++) {
											if(accountType.get(i).getDescription().equals("Cash on hand")){
												penAmount=penAmount.subtract(new BigDecimal(accountPayment.get(k).getPaidAmount()));
											}	
											amounts=amounts.add(new BigDecimal(accountPayment.get(k).getPaidAmount()));
										}
										if(accountType.get(i).getDescription().equals("Accounts Payable(A/P)")){
											balAmount=new BigDecimal(transaction.get(j).getAmount()).subtract(amounts);
											debit=debit.add(balAmount);
										}else if(accountType.get(i).getDescription().equals("Cash on hand")){
											credit=credit.add(penAmount); 
										}
									}
								}
							}else{
								if(transaction.get(j).getTransactionType().equals("Estimate")){
									if(accountType.get(i).getDescription().equals("Accounts Receivable (A/R)")){
										credit=credit.add(new BigDecimal(transaction.get(j).getAmount())); 
									}											
								}
								if(transaction.get(j).getTransactionType().equals("Expense")){
									if(accountType.get(i).getDescription().equals("Accounts Payable(A/P)")){
										debit=debit.add(new BigDecimal(transaction.get(j).getAmount())); 
									}
								}
							}
						}
					}
					sales=AccountsJDBC.salesAccounts(clientID,accountsDatabean);
					if(sales.size()>0){
						for (int k = 0; k < sales.size(); k++) {
							if(sales.get(k).getAccount_name().equals("Quick sales")){
								if(accountType.get(i).getDescription().equals("Cash on hand")){
									credit=credit.add(new BigDecimal(sales.get(k).getAccount_amount()));										
								}
							}else if(sales.get(k).getAccount_name().equals("Normal sales")){
								if(sales.get(k).getStatus()!=null){
									if(sales.get(k).getStatus().equals("paid")){
										if(accountType.get(i).getDescription().equals(sales.get(k).getAccount_description())){
											credit=credit.add(new BigDecimal(sales.get(k).getCredit()));										
										}
									}
								}
							}
						}
					}
					purchases=AccountsJDBC.purchaseAccounts(clientID,accountsDatabean);
					if(purchases.size()>0){
						for (int k = 0; k < purchases.size(); k++) {							
							if(purchases.get(k).getStatus()!=null){
								if(purchases.get(k).getStatus().equals("paid")){
									if(accountType.get(i).getDescription().equals(purchases.get(k).getAccount_description())){
										debit=debit.add(new BigDecimal(purchases.get(k).getCredit()));										
									}
								}
							}
						}
					}
					if(accountType.get(i).getAccountType().equals("Accounts Receivable (A/R)") || accountType.get(i).getAccountType().equals("Bank")
							|| accountType.get(i).getAccountType().equals("Other Current Assets") || accountType.get(i).getAccountType().equals("Fixed Asstes")
							|| accountType.get(i).getAccountType().equals("Income") || accountType.get(i).getAccountType().equals("Other Income")
							|| accountType.get(i).getAccountType().equals("Other Asstes")){
						accountsList.setCredit(String.valueOf(credit));
						profits.add(accountsList);
					}else if(accountType.get(i).getAccountType().equals("Other Current Liabilities") || accountType.get(i).getAccountType().equals("Long Term Liabilities")
									|| accountType.get(i).getAccountType().equals("Accounts Payable(A/P)")){
						accountsList.setDebit(String.valueOf(debit));
						loss.add(accountsList);
					}else{
						accountsList.setEquity(String.valueOf(equity));
						equityy.add(accountsList);
					}
				}		
				List<AccountsDatabean> assets=new ArrayList<AccountsDatabean>();
				List<AccountsDatabean> liabilities=new ArrayList<AccountsDatabean>();
				List<AccountsDatabean> equiti=new ArrayList<AccountsDatabean>();
				BigDecimal credits=BigDecimal.valueOf(0);
				BigDecimal debits=BigDecimal.valueOf(0);
				BigDecimal equities=BigDecimal.valueOf(0);
				for (int j = 0; j < profits.size(); j++) {
					if(!profits.get(j).getCredit().equals("0")){
						AccountsDatabean accdtabean=new AccountsDatabean();
						accdtabean.setAccount_name(profits.get(j).getAccount_name());
						if(profits.get(j).getCredit().equals("0")) accdtabean.setCredit("");
						else{
							accdtabean.setCredit(profits.get(j).getCredit());
							credits=credits.add(new BigDecimal(profits.get(j).getCredit()));
						}
						assets.add(accdtabean);
					}	
				}
				System.out.println("assets "+assets.size());
				accountsDatabean.setProfits(assets);
				for (int j = 0; j < loss.size(); j++) {
					if(!loss.get(j).getDebit().equals("0")){
						AccountsDatabean accdtabean=new AccountsDatabean();
						accdtabean.setAccount_name(loss.get(j).getAccount_name());						
						if(loss.get(j).getDebit().equals("0")) accdtabean.setDebit("");
						else{
							accdtabean.setDebit(loss.get(j).getDebit());
							debits=debits.add(new BigDecimal(loss.get(j).getDebit()));
						}
						liabilities.add(accdtabean);
					}	
				}
				System.out.println("liability "+liabilities.size());
				accountsDatabean.setLoss(liabilities);
				for (int j = 0; j < equityy.size(); j++) {
					if(!equityy.get(j).getEquity().equals("0")){
						AccountsDatabean accdtabean=new AccountsDatabean();
						accdtabean.setAccount_name(equityy.get(j).getAccount_name());						
						if(equityy.get(j).getEquity().equals("0")) accdtabean.setEquity("");
						else{
							accdtabean.setDebit(equityy.get(j).getEquity());
							equities=equities.add(new BigDecimal(equityy.get(j).getEquity()));
						}
						equiti.add(accdtabean);
					}	
				}
				accountsDatabean.setEquities(equiti);
				System.out.println("equity "+equiti.size());
				accountsDatabean.setCredit(String.valueOf(credits));
				accountsDatabean.setDebit(String.valueOf(debits));
				accountsDatabean.setEquity(String.valueOf(equities));
				accountsDatabean.setProfit(String.valueOf(credits.subtract(debits)));
				accountsDatabean.setLibequity(String.valueOf(equities.add(debits)));
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}*/
	
	public void getcategoryview(ProductRegister productRegister) {
		List<I0005> categoryList=new ArrayList<I0005>();
		try{
			categoryList=dao.getcategoryview(productRegister);
			if(categoryList.size()>0){
				productRegister.setCategory(categoryList.get(0).getCategoryType());
				productRegister.setDescription(categoryList.get(0).getDescription());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String categoryUpdate(ProductRegister productRegister) {
		return dao.categoryUpdate(productRegister);
	}
	
	public String deleteCategory(String id) {
		return dao.deleteCategory(id);
	}
	
	public String productApproval(ArrayList<ProductViewMB> finalList) {
		return dao.productApproval(finalList);
	}
	
	//Stanley
	
	public String setcash(Vendor vendor) {
		return dao.setcash(vendor); 
	}

	public List<String> getpaytype() {		
		return dao.getpaytype();
	}

	@Override
	public String getvencode(String clientID,String userID) {
		return dao.getvencode(clientID,userID);
	}
	
	@Override
	public String getcusCode(String clientID,String userID) {
		return dao.getcusCode(clientID,userID);
	}

	@Override
	public String setbuycash(Buyer buyer) {
		return dao.setbuycash(buyer); 
	}

	@Override
	public String setcashupdate(Vendor vendor) {
		return dao.setcashupdate(vendor); 
	}

	@Override
	public String employeeApproval(List<EmployeeDetailsViewFormMB> employeeDetailList) {
		return dao.employeeApproval(employeeDetailList);
	}
	
	public String payrollApproval(List<EmployeePayroll> value1) {
		return dao.payrollApproval(value1);
	}
	
	public String purchaseApproval(ArrayList<PurchaseOrder> result4) {
		return dao.purchaseApproval(result4);
	}
	
	@Override
	public String salesApproval(ArrayList<SalesViewMB> sales) {		
		return dao.salesApproval(sales);
	}
	
	@Override
	 public ArrayList<String> accountdescription(String accounttype) {
	  List<AccountType> accountdescription=null;
	  ArrayList<String> accountDescriptionlist = new ArrayList<String>();
	  try {
	   accountdescription=dao.accountdescription(accounttype);
	   if(accountdescription.size()>0){
	   for (int i = 0; i < accountdescription.size(); i++) {
	    //accountDescriptionlist.add(accountdescription.get(i).getDescription());
	   }
	   }
	   
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  return accountDescriptionlist;
	 }

	public String quicksalesApproval(ArrayList<QuickSaleViewMB> sales1) {
		return dao.quicksalesApproval(sales1);
	}
	
	public List<String> getquotproductList(String clientID, String userID) {
		return dao.getquotproductList(clientID,userID);
	}
	
	@Override
	public List<String> getproductVendor(String clientID, String userID,String str) {
		List<I0001> resultList=null;List<I0031> resultList1=null;
		List<String> vendorList=new ArrayList<String>();
		try{
			resultList=dao.getproductVendor(clientID, userID, str);
			if(resultList.size()>0){
				int prodId=resultList.get(0).getProduct_ID();
				resultList1=dao.getvendorList(prodId);
				if(resultList1.size()>0){
					for (int i = 0; i < resultList1.size(); i++) {
						if(resultList1.get(i).getI0025().getApprovalStatus().equals("Approved")){
							vendorList.add(resultList1.get(i).getI0025().getVendorPhoneNumber());
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return vendorList;
	}

	public String insertQuotation(String clientID, String userID,List<VendorRegisterFormMB> quotationList) {
		logger.info("---------------- Inside insertQuotation method --------------");
		String status="Fail";List<Quotation> quotationlists=null;
		String quotationNumber="";int quot_id=0;int product_id=0;int vendor_id=0;
		int product_price=0;
		try{
			quotationlists=new ArrayList<Quotation>();
			quotationlists=dao.getquotationnumber(clientID,userID);
			logger.debug("quotation list size------"+quotationlists.size());
			if(quotationlists.size()>0){
				String quotNumber=quotationlists.get(quotationlists.size()-1).getQuotationNumber();
				int quotationSize=Integer.parseInt(quotNumber.substring(3))+1;
				if(quotationSize<=9) quotationNumber="RFQ000000"+quotationSize;
				else if(quotationSize>9 && quotationSize<=99) quotationNumber="RFQ00000"+quotationSize;
				else if(quotationSize>99 && quotationSize<=999) quotationNumber="RFQ0000"+quotationSize;
				else if(quotationSize>999 && quotationSize<=9999) quotationNumber="RFQ000"+quotationSize;
				else if(quotationSize>9999 && quotationSize<=99999) quotationNumber="RFQ00"+quotationSize;
				else if(quotationSize>99999 && quotationSize<=999999) quotationNumber="RFQ0"+quotationSize;
				else quotationNumber="RFQ"+quotationSize;
			}else{
				quotationNumber="RFQ0000001";
			}
			for (int i = 0; i < quotationList.size(); i++) {
				logger.debug("inside insert quotation for loop"+quotationList.size());
			    if(!quotationList.get(i).getProductName().equals("")){
			    	logger.debug("inside insert quotation product name not empty if"+quotationList.get(i).getProductName());
			    	quot_id=dao.insertQuotation(clientID,userID,quotationList,i,quotationNumber);
			    	logger.debug("inside insert quotation quotation id"+quot_id);
			    	product_id=dao.getproductid(clientID,userID,quotationList.get(i).getProductName());
			    	logger.debug("inside insert quotation product id"+product_id);
			    	if(quotationList.get(i).getSelectedVendors().length>0){ 
			    		logger.debug("inside insert quotation select vendor length greater than zero"+quotationList.get(i).getSelectedVendors().length);
			    		for (int j = 0; j < quotationList.get(i).getSelectedVendors().length; j++) {
			    			logger.info("inside insert quotation select vendor length greater than zero for loop");
			    			if(quotationList.get(i).getSelectedVendors().length==1){
			    				logger.info("inside insert quotation select vendor length equal to one if");
			    				if(quotationList.get(i).getSelectedVendors()[j].equals("add")){
			    					logger.info("inside insert quotation select vendor length equal to one add if");
			    					status=dao.insertQuotationDetails(quot_id,product_price,product_id,quotationList.get(i).getProductName(),
			    							quotationList.get(i).getProductCount());
			    				}else{
			    					logger.info("inside insert quotation select vendor length equal to one add else");
			    					vendor_id=dao.getvendorid(clientID,userID,quotationList.get(i).getSelectedVendors()[j]);
			    					product_price=dao.getproductPrice(product_id,vendor_id);
			    					status=dao.insertQuotationDetails(quot_id,vendor_id,product_price,product_id,quotationList.get(i).getProductName(),
			    							quotationList.get(i).getSelectedVendors()[j],quotationList.get(i).getProductCount());
			    				}
			    			}else{
			    				logger.info("inside insert quotation select vendor length equal to one else");
			    				if(!quotationList.get(i).getSelectedVendors()[j].equals("add")){
			    					logger.info("inside insert quotation select vendor length equal to one else add if");
			    					vendor_id=dao.getvendorid(clientID,userID,quotationList.get(i).getSelectedVendors()[j]);
			    					product_price=dao.getproductPrice(product_id,vendor_id);
			    					status=dao.insertQuotationDetails(quot_id,vendor_id,product_price,product_id,quotationList.get(i).getProductName(),
			    							quotationList.get(i).getSelectedVendors()[j],quotationList.get(i).getProductCount());
			    				}
			    			}
			    		}
			    	}
			    	if(quotationList.get(i).getSelectedVendors().length==0){
			    		logger.info("inside insert quotation select vendor length equal to zero if");
			    		status=dao.insertQuotationDetails(quot_id,product_price,product_id,quotationList.get(i).getProductName(),
			    				quotationList.get(i).getProductCount());
			    	}
			    }
			}
			logger.debug("insert quotation status"+status);
		}catch(Exception e){
			logger.warn("inside Exception"+e); 
		}finally{
		}
		return status;
	}

	@Override
	public String quotationApproval(String clientID, String userID,List<VendorRegisterFormMB> quotationList,VendorRegisterFormMB vendor) {
		logger.info("---------------- Inside quotationApproval method --------------");
		List<Quotation> list=null;String status="Fail";List<QuotationDetail> quotdetailList=null;
		List<String> vendormaillist=null;
		List<VendorRegisterFormMB> quotationDetailList=null;
		try{
			vendormaillist=new ArrayList<String>();
			quotationDetailList=new ArrayList<VendorRegisterFormMB>(); 
			for (int i = 0; i < quotationList.size(); i++) {
				if(quotationList.get(i).isQuotationCheck()==true){
					list=dao.getquotationApprovallist(clientID,userID,quotationList,i);
					if(list.size()>0){
						for (int j = 0; j < list.size(); j++) {
							status=dao.quotationApproval(list.get(j).getQuotationId());
							if(status.equalsIgnoreCase("Success")){
								quotdetailList=dao.getquotationDetailsList(list.get(j).getQuotationId());
								if(quotdetailList.size()>0){
									for (int k = 0; k < quotdetailList.size(); k++) {
										VendorRegisterFormMB ven=new VendorRegisterFormMB();
										ven.setProductName(quotdetailList.get(k).getProductName());
										ven.setVendorName(quotdetailList.get(k).getVendorName());
										ven.setProductCount(quotdetailList.get(k).getQuotationId().getProductCount());
										ven.setDeliveryDate(quotdetailList.get(k).getQuotationId().getDeliveryDate());
										ven.setVendormailid(quotdetailList.get(k).getVendor_ID().getEmail_ID_vendor());
										quotationDetailList.add(ven);
										vendor.setQuotationDetailList(quotationDetailList);
										vendormaillist.add(quotdetailList.get(k).getVendorName());
										vendor.setMailList(vendormaillist);
									}
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return status;
	}
	
	@Override
	public List<VendorRegisterFormMB> getquotationDetails(String clientID,String quotationNumber) {
		logger.info("-----------------inside getquotationDetails method calling--------------");
		List<VendorRegisterFormMB> list=null;
		List<Quotation> quotationList=null;List<QuotationDetail> quotdetailList=null;
		int count=1;
		try{
			list=new ArrayList<VendorRegisterFormMB>();
			quotationList=dao.getquotationDetails(clientID,quotationNumber);
			if(quotationList.size()>0){
				for (int i = 0; i < quotationList.size(); i++) {					
					quotdetailList=dao.getquotationDetailsList(quotationList.get(i).getQuotationId());
					if(quotdetailList.size()>0){
						for (int j = 0; j < quotdetailList.size(); j++) {
							VendorRegisterFormMB vendor=new VendorRegisterFormMB();
							List<String> vendors=new ArrayList<String>();
							vendor.setSerialNo(String.valueOf(count));
							vendor.setProductName(quotdetailList.get(j).getProductName());
							vendor.setVendorName(quotdetailList.get(j).getVendorName());
							vendor.setProductPrice(String.valueOf(quotdetailList.get(j).getProductPrice()));
							vendor.setProductCount(quotationList.get(i).getProductCount());
							vendor.setDeliveryDate(quotationList.get(i).getDeliveryDate());
							vendor.setQuotationDetailsId(quotdetailList.get(j).getQuotationDetailsId());
							vendors.add(quotdetailList.get(j).getVendorName());
							String[] vendorArray = vendors.toArray(new String[vendors.size()]);
							for (int k = 0; k < vendors.size(); k++) {
								vendorArray[k]=vendors.get(k);
							}
							vendor.setSelectedVendors(vendorArray);
							list.add(vendor);
							count++;
						}						
					}
				}
			}else{
				logger.info("inside getquotationDetails method list size is zero");
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return list;
	}
	
	@Override
	public String deleteQuotation(String clientID, String quotationNumber) {
		logger.info("-----------------inside deleteQuotation method calling--------------");
		List<Quotation> quotationList=null;List<QuotationDetail> quotdetailList=null;
		String status="Fail";
		try{
			quotationList=dao.getquotationDetails(clientID,quotationNumber);
			if(quotationList.size()>0){
				for (int i = 0; i < quotationList.size(); i++) {
					status=dao.deleteQuotation(quotationList.get(i).getQuotationId());
					quotdetailList=dao.getquotationDetailsList(quotationList.get(i).getQuotationId());
					if(quotdetailList.size()>0){
						for (int j = 0; j < quotdetailList.size(); j++) {
							dao.deleteQuotationdetail(quotdetailList.get(j).getQuotationDetailsId());
						}
					}
				}
			}
			if(quotationList.size()==0){
				status="Success";
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return status;
	}
	
	@Override
	public String finalQuotation(String clientID, String quotationNumber,List<VendorRegisterFormMB> quotationDetailList) {
		logger.info("-----------------inside finalQuotation method calling--------------");
		List<Quotation> quotationList=null;String status="Fail";String choosenStatus="";
		try{
			quotationList=dao.getquotationDetails(clientID,quotationNumber);
			if(quotationList.size()>0){
				for (int i = 0; i < quotationList.size(); i++) {
					dao.finalQuotation(quotationList.get(i).getQuotationId());
				}
				for (int i = 0; i < quotationDetailList.size(); i++) {
					if(quotationDetailList.get(i).isQuotationCheck()==true){
						choosenStatus="Choosed";
						status=dao.finalQuotationDetail(quotationDetailList.get(i).getQuotationDetailsId(),choosenStatus);
					}
					if (quotationDetailList.get(i).isQuotationCheck()==false){
						choosenStatus="Not Choosed";
						status=dao.finalQuotationDetail(quotationDetailList.get(i).getQuotationDetailsId(),choosenStatus);
					}
				}
			}else{
				logger.info("inside finalQuotation method list size is zero");
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return status;
	}
	
	@Override
	public List<VendorRegisterFormMB> getfinalQuotationList(String clientID,String quotationNumber) {
		logger.info("-----------------inside quotationUpdate method calling--------------");
		List<VendorRegisterFormMB> list=null;
		List<Quotation> quotList=null;List<QuotationDetail> quotdetailList=null;
		try{
			list=new ArrayList<VendorRegisterFormMB>();
			quotList=dao.getfinalquotationList(clientID,quotationNumber);
			if(quotList.size()>0){
				for (int i = 0; i < quotList.size(); i++) {
					quotdetailList=dao.getdinalquotationDetailList(quotList.get(i).getQuotationId());
					if(quotdetailList.size()>0){
						for (int j = 0; j < quotdetailList.size(); j++) {
							VendorRegisterFormMB ven=new VendorRegisterFormMB();
							ven.setDeliveryDate(quotList.get(i).getDeliveryDate());
							ven.setProductCount(quotList.get(i).getProductCount());
							ven.setProductName(quotdetailList.get(j).getProductName());
							ven.setProductPrice(String.valueOf(quotdetailList.get(j).getProductPrice()));
							ven.setVendorName(quotdetailList.get(j).getVendorName());
							list.add(ven);
						}
					}
				}
			}else{
				logger.info("inside getfinalQuotationList method list size is zero");
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return list;		
	}
		
	@Override
	public String quotationUpdate(String clientID,String userID,String quotationNumber,List<VendorRegisterFormMB> quotationDetailList) {
		logger.info("-----------------inside quotationUpdate method calling--------------");
		int product_id=0;int product_price=0;int vendor_id=0;
		int quot_id=0;
		String status="Fail";
		try{
			deleteQuotation(clientID, quotationNumber);
			for (int i = 0; i < quotationDetailList.size(); i++) {
				if(!quotationDetailList.get(i).getProductName().equals("")){
					quot_id=dao.updateQuotation(clientID,userID,quotationDetailList,i,quotationNumber);
			    	product_id=dao.getproductid(clientID,userID,quotationDetailList.get(i).getProductName());
			    	if(quotationDetailList.get(i).getSelectedVendors().length==0){
			    		status=dao.insertQuotationDetails(quot_id,product_price,product_id,quotationDetailList.get(i).getProductName(),
			    				quotationDetailList.get(i).getProductCount());
			    	}else{
			    		for (int j = 0; j < quotationDetailList.get(i).getSelectedVendors().length; j++) {
	    					vendor_id=dao.getvendorid(clientID,userID,quotationDetailList.get(i).getSelectedVendors()[j]);
	    					product_price=dao.getproductPrice(product_id,vendor_id);
	    					status=dao.insertQuotationDetails(quot_id,vendor_id,product_price,product_id,quotationDetailList.get(i).getProductName(),
	    							quotationDetailList.get(i).getSelectedVendors()[j],quotationDetailList.get(i).getProductCount());
			    		}
			    	}
				}else{
					logger.info("inside quotationUpdate product name not empty else");
				}
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return status;
	}

	@Override
	public void removeEditRow(int quotationDetailsId) {
		logger.info("-----------------inside removeEditRow method calling--------------");
		dao.removeEditRow(quotationDetailsId);
	}

	@Override
	public List<LoginAccess> getapprovalCountList(String clientID) {
		logger.info("-----------------inside getapprovalCountList method calling--------------");
		List<String> menuList=null;int productCount=0;
		List<LoginAccess> list=null;
		try{
			menuList=new ArrayList<String>();list=new ArrayList<LoginAccess>();
			menuList.add("Vendor");
			menuList.add("Customer");
			menuList.add("Purchase");
			menuList.add("Sales");
			menuList.add("Quick Sales");
			menuList.add("Employee");
			menuList.add("Payroll");
			menuList.add("Category");
			menuList.add("Product");
			menuList.add("Quotation");
			menuList.add("Sales Quote");
			for (int i = 0; i < menuList.size(); i++) { 
				LoginAccess loginaccess=new LoginAccess();
				if(menuList.get(i).equalsIgnoreCase("Vendor")){ 
					productCount=dao.getvendorapprovalCount(clientID);
					loginaccess.setApprovalCount(productCount);
					loginaccess.setMenuName(menuList.get(i));
				}
				if(menuList.get(i).equalsIgnoreCase("Customer")){ 
					productCount=dao.getcustomerapprovalCount(clientID);
					loginaccess.setApprovalCount(productCount);
					loginaccess.setMenuName(menuList.get(i));
				}
				if(menuList.get(i).equalsIgnoreCase("Purchase")){ 
					productCount=dao.getpurchaseapprovalCount(clientID);
					loginaccess.setApprovalCount(productCount);
					loginaccess.setMenuName(menuList.get(i));
				}
				if(menuList.get(i).equalsIgnoreCase("Sales")){ 
					String str="Sales";
					productCount=dao.getsalesapprovalCount(clientID,str);
					loginaccess.setApprovalCount(productCount);
					loginaccess.setMenuName(menuList.get(i));
				}
				if(menuList.get(i).equalsIgnoreCase("Quick Sales")){ 
					String str="Quick Sales";
					productCount=dao.getsalesapprovalCount(clientID,str);
					loginaccess.setApprovalCount(productCount);
					loginaccess.setMenuName(menuList.get(i));
				}
				if(menuList.get(i).equalsIgnoreCase("Employee")){ 
					productCount=dao.getemployeeapprovalCount(clientID);
					loginaccess.setApprovalCount(productCount);
					loginaccess.setMenuName(menuList.get(i));
				}
				if(menuList.get(i).equalsIgnoreCase("Payroll")){ 
					productCount=dao.getpayrollapprovalCount(clientID);
					loginaccess.setApprovalCount(productCount);
					loginaccess.setMenuName(menuList.get(i));
				}
				if(menuList.get(i).equalsIgnoreCase("Category")){ 
					productCount=dao.getcategoryapprovalCount(clientID);
					loginaccess.setApprovalCount(productCount);
					loginaccess.setMenuName(menuList.get(i));
				}
				if(menuList.get(i).equalsIgnoreCase("Product")){ 
					productCount=dao.getproductapprovalCount(clientID);
					loginaccess.setApprovalCount(productCount);
					loginaccess.setMenuName(menuList.get(i));
				}
				if(menuList.get(i).equalsIgnoreCase("Quotation")){ 
					productCount=dao.getquotationapprovalCount(clientID);
					loginaccess.setApprovalCount(productCount);
					loginaccess.setMenuName(menuList.get(i));
				}
				if(menuList.get(i).equalsIgnoreCase("Sales Quote")){ 
					productCount=dao.getsalesQuoteapprovalCount(clientID);
					loginaccess.setApprovalCount(productCount);
					loginaccess.setMenuName(menuList.get(i));
				}
				if(loginaccess.getApprovalCount()>0){ 
					list.add(loginaccess);
				}
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return list;
	}
	
	@Override
	public List<String> getstatusList() {
		return dao.getstatusList();
	}

	@Override
	public String getcmtcode() {
		return dao.getcmtcode();
	}
	
	@Override
	public List<String> getcrmtype() {
		
		return dao.getcrmtype();
	}

	@Override
	public List<String> getcrmproduct() {
		return dao.getcrmproduct();
	}
	

	@Override
	public String getCrmIndustrySave(Buyer buyer) {
		
		return dao.getCrmIndustrySave(buyer); 
	}

	public String saveCrm(Buyer buyer) {
		return dao.saveCrm(buyer); 
	}
	
	@Override
	public List<CrmCustomerdetail> getcrmenquiry(String query) {
		if(query.equalsIgnoreCase("crmenquiry")){
			query="from CrmCustomerdetail where status='Enquiry' order by updateDate desc";
		}else if(query.equalsIgnoreCase("crmreaches")){
			query="from CrmCustomerdetail where status='Reaches' order by updateDate desc";
		}else if(query.equalsIgnoreCase("crmleads")){
			query="from CrmCustomerdetail where status='Leads' order by updateDate desc";
		}else if(query.equalsIgnoreCase("crmimpleads")){
			query="from CrmCustomerdetail where status='Imp-Leads' order by updateDate desc";
		}else if(query.equalsIgnoreCase("crmclosure")){
			query="from CrmCustomerdetail where status='Closure' order by updateDate desc";
		}else if(query.equalsIgnoreCase("crmnegosation")){
			query="from CrmCustomerdetail where status='Negosiation' order by updateDate desc";
		}else if(query.equalsIgnoreCase("crmonboard")){
			query="from CrmCustomerdetail where status='Onboard' order by updateDate desc";
		}else if(query.equalsIgnoreCase("crmcompleted")){
			query="from CrmCustomerdetail where status='Completed' order by updateDate desc";
		}else if(query.equalsIgnoreCase("crmrejected")){
			query="from CrmCustomerdetail where status='Rejected' order by updateDate desc";
		}		
		
		return dao.getcrmenquiry(query); 
	}

	@Override
	public List<String> getcustomername() {
		return dao.getcustomername();
	}
	
	
	@Override
	public List<String> getcustomerDetails(Sales sales) {
		if(sales.getCustomerName()!=null){
			String[] parts = sales.getCustomerName().split("-");
			String customername = parts[0]; 
			String emailaddress = parts[1]; 
			System.out.println("Customer name -->"+customername);
			System.out.println("Customer email address -->"+emailaddress);
			sales.setCustomerName(customername.trim());
			sales.setEmail(emailaddress.trim());
		}
		else
		{
			System.out.println("Customer name is null");
		}
		
		return dao.getcustomerDetails(sales); 
	}
	@Override
	public List<String> getproductlist(String clientID, String userID) {
		return dao.getproductlist(clientID, userID); 
	}
	
	@Override
	public String getUnitprice(String productname) throws DemoException {
		return dao.getUnitprice(productname); 
	}
	
	@Override
	public String insertNewouote(String clientID, String userID,ArrayList<SalesOrderFormMB> mblist, Sales sales) {
		 sales.setQuotationcode("SALQ000"); // 
		return dao.insertNewouote(clientID, userID, mblist, sales); 
	}
	
	@Override
	public Map<SalesOrderFormMB, ArrayList<SalesOrderFormMB>> getQuoteview(Map<SalesOrderFormMB, ArrayList<SalesOrderFormMB>> quoteList) {
		return dao.getQuoteview(quoteList); 
	}
	
	@Override
	public String getquotationcode() {
		return dao.getquotationcode(); 
	}
	
	@Override
	public List<SalesOrderFormMB> getQuoteviewdetails(String clientID,int quoteID, Sales sales) {
		logger.info("-----------------inside getquotationDetails method calling--------------");
		List<SalesOrderFormMB> list=null;
		List<SalesQuote> quotationList=null;
		List<SalesQuoteDetails> quotdetailList=null;
		int count=1;
		try{
			list=new ArrayList<SalesOrderFormMB>();
			quotationList=dao.getQuoteviewdetails(clientID,quoteID,sales); 
			System.out.println("---- inside getQuoteviewdetails() method quotationList from parent table bo ------>"+quotationList.size()); 
			if(quotationList.size()>0){
				for (int i = 0; i < quotationList.size(); i++) {					
					quotdetailList=dao.getsalesquotationdetails(quoteID);   
					System.out.println("---- inside getQuoteviewdetails() method quotdetailList from child table bo ------>"+quotdetailList.size());
					if(quotdetailList.size()>0){
						for (int j = 0; j < quotdetailList.size(); j++) {
							SalesOrderFormMB quote=new SalesOrderFormMB();
							List<String> vendors=new ArrayList<String>();
							quote.setSerialno(String.valueOf(count)); 
							quote.setProductName(quotdetailList.get(j).getName());
							quote.setUnit(quotdetailList.get(j).getPrice()); 
							quote.setQuantity(quotdetailList.get(j).getDescription()); 
							quote.setTotalAmount(quotdetailList.get(j).getNetprize()); 
							sales.setCustomerName(quotationList.get(j).getCustomerName()); 
							sales.setCustomername1(quotationList.get(j).getCustomerName());
							sales.setPhoneNumber(quotationList.get(j).getMobileNumber()); 
							list.add(quote); 
							 System.out.println("---- inside getQuoteviewdetails() method bo list size ---->"+list.size()); 
							count++;
						}						
					}
				}
			}else{
				System.out.println("----- inside getQuoteviewdetails() method bo else() ------- ");
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
		}
		return list;
	}
	
	@Override
	public String quoteTabledelete(int quoteID) {
		return dao.quoteTabledelete(quoteID); 
	} 

	@Override
	public String consoleUpdate(String clientID,ArrayList<SalesOrderFormMB> quoteListDetails,int quoteID) {
		return dao.consoleUpdate(clientID, quoteListDetails,quoteID); 
	}
	
	@Override
	public void getsalestransactioncustprod(String clientID,ATransaction aTransaction) {
		adao.getsalestransactioncustprod(clientID,aTransaction);
	}
	
	
	@Override
	public void getcustomerdetails(String clientID, ATransaction aTransaction) {
		adao.getcustomerdetails(clientID,aTransaction);
	}

	/*John Clinton*/
	@Override
	public void expenseResource(String clientID, ATransaction aTransaction) {
		adao.expenseResource(clientID,aTransaction);
		
	}
	
	@Override
	public List<ChartOfAccount> accountlist(String clientID) {
		return adao.accountlist(clientID);
	}
	
	public void getAccountTypes(String clientID,AccountsDatabean accountsDatabean){
		logger.info("[getAccountTypes()]---------------------Inside getAccountTypes BoImpln Calling---------------------");
		List<AccountType> accountstype=null;List<String> accounts=null;List<String> types=null;
		try{
			accounts=new ArrayList<String>();types=new ArrayList<String>();
			if(accountsDatabean.getStatus().equals("Category Type")){
				accountstype=adao.getCategoryTypes();
				if(accountstype.size()>0){
					for (int i = 0; i < accountstype.size(); i++) {
						accounts.add(accountstype.get(i).getCategoryType());
					}
					HashSet<String> accoType=new HashSet<String>(accounts);
					accounts.clear();
					accounts.addAll(accoType);
					Collections.sort(accounts);
					accountsDatabean.setAccountType(accounts);
					accountsDatabean.setAccount_type("");
					accountsDatabean.setAccount_name("");
					accountsDatabean.setTypes(types);
					accountsDatabean.setValidate("");accountsDatabean.setBalance("0.0");
				}
			}
			if(accountsDatabean.getStatus().equals("Detail Type")){
				types=adao.getAccountDetailsType(accountsDatabean.getAccount_type());
				Collections.sort(types);
				accountsDatabean.setTypes(types);
				accountsDatabean.setAccount_name("");
			}			
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public void getCOAdetails(String clientID, AccountsDatabean accountsDatabean){
		logger.info("[getCOAdetails()]-------------------Inside getCOAdetails() in BoImpln Calling-------------------------");
		List<ChartOfAccount> ChartofAccounts=null;List<AccountsDatabean> accountsDetails=null;
		List<AccountsDatabean> gstaccountDetails=null;
		try {
			List<String> list=Arrays.asList("Income","Other Income","Cost of Goods Sold","Expenses","Other Expenses");
			List<String> gstList=Arrays.asList("Output CGST","Output SGST","Output IGST","Input IGST","Input SGST","Input CGST");
			accountsDetails=new ArrayList<AccountsDatabean>();gstaccountDetails=new ArrayList<AccountsDatabean>();
			accountsDatabean.setAccounts(new ArrayList<AccountsDatabean>());
			ChartofAccounts=adao.accountlist(clientID);
			if(ChartofAccounts.size()>0){
				for (int i = 0; i < ChartofAccounts.size(); i++) {
					if(!gstList.contains(ChartofAccounts.get(i).getAccountName())){
						AccountsDatabean domain=new AccountsDatabean();
						domain.setAccount_name(ChartofAccounts.get(i).getAccountName());
						domain.setAccount_type(ChartofAccounts.get(i).getAccountType().getCategoryType());
						domain.setDetailType(ChartofAccounts.get(i).getAccountType().getDetailType());
						domain.setAccount_date(ChartofAccounts.get(i).getTrandate());
						domain.setStatus(ChartofAccounts.get(i).getStatus());
						domain.setBalance(ChartofAccounts.get(i).getBalance());
						domain.setTaxes(ChartofAccounts.get(i).getTaxes());
						if(list.contains(domain.getAccount_type())){
							domain.setButtonValue(runreportBtn);
						}else{
							domain.setButtonValue(accthistoryBtn);
						}
						accountsDetails.add(domain);
					}
				}
				accountsDatabean.getAccounts().addAll(accountsDetails);
				gstaccountDetails=AccountsJDBC.getGstCoaDetails(clientID);
				if(gstaccountDetails.size()>0){
					accountsDatabean.getAccounts().addAll(gstaccountDetails);
				}
			}
		}catch(Exception e){
			logger.warn("--------------Inside Exception-------------------"+e.getMessage());
		}finally{
			ChartofAccounts=null;accountsDetails=null;gstaccountDetails=null;
		}
	}
	
	@Override
	public void accountbalance(String clientID, ATransaction aTransaction) {
		adao.accountbalance(clientID , aTransaction);
		
	}

	public String expenseSave(ATransaction aTransaction, String clientID) {
		logger.info("[expenseSave()]----------------------Inside expenseSave() in BoImpln Calling---------------------------");
		String status="";
		try{
			status=adao.saveexpenseTrans(aTransaction,clientID);
		}catch (Exception e) {
			logger.warn("----------------Inside Exception----------------------"+e.getMessage());
		}
		return status;
	}
		
	@Override
	public void expanseDetailView(String clientID, ATransaction aTransaction) {
		logger.info("[expanseDetailView()]----------------------------Inside expanseDetailView() in BoImpln Calling------------------------------");
		List<ExpenseTransaction> expensetransactionList=null;List<ExpenseAccountsPayment> expenseAccountsPaymentList=null;
		try{
			expensetransactionList=adao.expensesDataTable(clientID);
			for (int i = 0; i < expensetransactionList.size(); i++) {
				ATransaction atransaction= new ATransaction();
				atransaction.setDate(expensetransactionList.get(i).getStartDate());
				atransaction.setTransactionType(expensetransactionList.get(i).getTransactionType());
				atransaction.setRefNo(expensetransactionList.get(i).getBillNumber());
				atransaction.setVendorName(expensetransactionList.get(i).getName());
				atransaction.setDueDate(expensetransactionList.get(i).getDueDate());
				atransaction.setFromAccount(expensetransactionList.get(i).getFromAccountType());
				atransaction.setMailingAddress(expensetransactionList.get(i).getMailingAddress());
				atransaction.setPaymentMode(expensetransactionList.get(i).getPaymentMethod());
				atransaction.setTerms(expensetransactionList.get(i).getPayterms());
				atransaction.setAmount(expensetransactionList.get(i).getCurrencyAmount());
				atransaction.setIndex(expensetransactionList.get(i).getExpense_transaction_ID());
				atransaction.setCode(expensetransactionList.get(i).getCode());
				atransaction.setBalAmount(expensetransactionList.get(i).getBalanceAmount());
				atransaction.setBaseCurrency(expensetransactionList.get(i).getBaseCurrency());
				atransaction.setBeforeTaxAmount(expensetransactionList.get(i).getCurrencybeforetaxAmount());
				atransaction.setTaxAmount(expensetransactionList.get(i).getCurrencyTaxAmount());
				atransaction.setCodeDescription(expensetransactionList.get(i).getCodeDescription());
				atransaction.setPaymentStatus(expensetransactionList.get(i).getPaymentStatus());
				atransaction.setCurrencyType(expensetransactionList.get(i).getCurrencyType());
				atransaction.setLocation(expensetransactionList.get(i).getPlaceofLocation());
				atransaction.setTaxType(expensetransactionList.get(i).getTaxType());
				atransaction.setPaymentStatus(expensetransactionList.get(i).getPaymentStatus());
				atransaction.setTotalAmount(expensetransactionList.get(i).getTransactionAmount());
				atransaction.setSubTotalAmount(expensetransactionList.get(i).getSubTotalAmount());
				atransaction.setCurrencyAmount(expensetransactionList.get(i).getCurrencyAmount());
				atransaction.setBaseCurrency(expensetransactionList.get(i).getBaseCurrency());
				aTransaction.getExpenseTransactionDataList().add(atransaction);
			}
			expenseAccountsPaymentList=adao.expenseaccountpaymentDetail(clientID);
			for (int i = 0; i < expenseAccountsPaymentList.size(); i++) {
				ATransaction atrans=new ATransaction();
				atrans.setPaymentId(expenseAccountsPaymentList.get(i).getExpense_accounts_payments_ID());
				atrans.setTransactionID(expenseAccountsPaymentList.get(i).getExpenseTransaction().getExpense_transaction_ID());
				atrans.setToAccount(expenseAccountsPaymentList.get(i).getAccountType());
				atrans.setAmount(expenseAccountsPaymentList.get(i).getTotalAmount());
				atrans.setDescription(expenseAccountsPaymentList.get(i).getDescription());
				atrans.setTaxes(expenseAccountsPaymentList.get(i).getTaxes());
				atrans.setBaseCurrency(expenseAccountsPaymentList.get(i).getExpenseTransaction().getBaseCurrency());
				atrans.setGstAmount(expenseAccountsPaymentList.get(i).getGstAmount());
				atrans.setPercentIGSTFlag("none");
				atrans.setPercentGSTFlag("none");
				try{
					if(!expenseAccountsPaymentList.get(i).getCgstType().equalsIgnoreCase(null) || !expenseAccountsPaymentList.get(i).getCgstType().equalsIgnoreCase("")){
						atrans.setCgstType(expenseAccountsPaymentList.get(i).getCgstType());
						atrans.setCgstAmount(expenseAccountsPaymentList.get(i).getCgstAmount());
						atrans.setPercentageValue(expenseAccountsPaymentList.get(i).getTaxPercentage());
						atrans.setPercentGSTFlag("1");
					}
				}catch(NullPointerException e){
					logger.warn("[expanseDetailView] Inside Exception", e.getMessage()); 
				}
				try{
					if(!expenseAccountsPaymentList.get(i).getSgstType().equalsIgnoreCase(null) || !expenseAccountsPaymentList.get(i).getSgstType().equalsIgnoreCase("")){
						atrans.setSgstType(expenseAccountsPaymentList.get(i).getSgstType());
						atrans.setSgstAmount(expenseAccountsPaymentList.get(i).getSgstAmount());
						atrans.setPercentageValue(expenseAccountsPaymentList.get(i).getTaxPercentage());
						atrans.setPercentGSTFlag("1");
					}
				}catch(NullPointerException e){
					logger.warn("[expanseDetailView] Inside Exception", e.getMessage()); 
				}
				try{
					if(!expenseAccountsPaymentList.get(i).getIgstType().equalsIgnoreCase(null) || !expenseAccountsPaymentList.get(i).getIgstType().equalsIgnoreCase("")){
						atrans.setGstType(expenseAccountsPaymentList.get(i).getIgstType());
						atrans.setPercentageAmount(expenseAccountsPaymentList.get(i).getTaxAmount());
						atrans.setPercentageValue(expenseAccountsPaymentList.get(i).getTaxPercentage());
						atrans.setPercentIGSTFlag("1");
					}
				}catch(NullPointerException e){
					logger.warn("[expanseDetailView] Inside Exception", e.getMessage()); 
				}
				aTransaction.getExpenseaccPaymentDataList().add(atrans);
			}
		}catch (Exception e) {
			logger.warn("----------------Inside Exception----------------------"+e.getMessage());
		}finally{
			expensetransactionList=null;expenseAccountsPaymentList=null;
		}
	}
	
	@Override
	public List<ATransaction> expensesDataTable(String clientID) {
		logger.info("[expensesDataTable()]----------------------------Inside expensesDataTable() in BoImpln Calling------------------------------");
		List<ATransaction> ExpenseDataTableList=null;List<ExpenseTransaction> expensetransaction=null;
		try {
			ExpenseDataTableList=new ArrayList<ATransaction>();expensetransaction=new ArrayList<ExpenseTransaction>();
			expensetransaction=adao.expensesDataTable(clientID);
			for (int i = 0; i < expensetransaction.size(); i++) {
				ATransaction atransaction= new ATransaction();
				atransaction.setDate(expensetransaction.get(i).getStartDate());
				atransaction.setTransactionType(expensetransaction.get(i).getTransactionType());
				atransaction.setRefNo(expensetransaction.get(i).getBillNumber());
				atransaction.setPayeeName(expensetransaction.get(i).getName());
				atransaction.setAmount(expensetransaction.get(i).getCurrencyAmount());
				atransaction.setIndex(expensetransaction.get(i).getExpense_transaction_ID());
				atransaction.setCode(expensetransaction.get(i).getCode());
				atransaction.setBalAmount(expensetransaction.get(i).getBalanceAmount());
				atransaction.setBaseCurrency(expensetransaction.get(i).getBaseCurrency());
				atransaction.setBeforeTaxAmount(expensetransaction.get(i).getCurrencybeforetaxAmount());
				atransaction.setTaxAmount(expensetransaction.get(i).getCurrencyTaxAmount());
				atransaction.setCodeDescription(expensetransaction.get(i).getCodeDescription());
				atransaction.setPaymentStatus(expensetransaction.get(i).getPaymentStatus());
				ExpenseDataTableList.add(atransaction);
			}
		} catch(Exception e){
			e.printStackTrace();
			logger.warn("---------------Inside Exception-----------------------"+e.getMessage());
		}
		return ExpenseDataTableList;
	}
	
	
	
	@Override
	public String expenseUpdate(ATransaction aTransaction, String clientID) {
		String status="";
		try {
			status=adao.expenseUpdate(aTransaction,clientID);
			System.out.println("-------update success--------->"+status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public void getCOAtransactiondetails(String clientID,AccountsDatabean accountsDatabean) {
		System.out.println("---------getCOA Transaction Detail-----------"+accountsDatabean.getAccount_name());
		List<ChartOfAccount> accountDeposit=null;
		List<Transaction> transaction=null;
		List<AccountsDatabean> accountsDetails=new ArrayList<AccountsDatabean>();
		try {
			accountDeposit=adao.getaccountnamedetail(clientID, accountsDatabean.getAccount_name());
			System.out.println("---------accountDeposit------->"+accountDeposit.size());
			if(accountDeposit.size()>0){
					AccountsDatabean accountsList=new AccountsDatabean();
					accountsList.setAccount_date(accountDeposit.get(0).getTrandate());
					accountsList.setAccount_type("Deposit");
					accountsList.setAccount_description(accountDeposit.get(0).getAccountName());
					accountsList.setBalance(accountDeposit.get(0).getBalance());
					
			
				accountsDetails.add(accountsList);
				}

			accountsDatabean.setAccounts(accountsDetails);
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}
	
	/*manos*/
	@Override
	public List<ExpenseTransaction> getbillnumber(String clientID,
			String transactionType) {
		
		return adao.getbillnumber(clientID,transactionType);
	}

	@Override
	public void mailresource(String clientID, ATransaction aTransaction) {
		adao.mailresource(clientID , aTransaction);
		
	}
	@Override
	public String editAccount(AccountsDatabean accountsDatabean , String clientID ) {
		return adao.editAccount(clientID,accountsDatabean);
	}
	
	@Override
	public String expenseMakePayment(ATransaction aTransaction, String clientID) {
		return adao.expenseMakePayment(aTransaction,clientID);
	}
	
	@Override
	public String saveSalesTransaction(String clientID,ATransaction aTransaction, List<ATransaction> productdetails) {
		logger.info("[saveSalesTransaction()-----------------Inside saveSalesTransaction() in BoImpn Calling-----------------]");
		List<SalesTransaction> salesTransactionList=null;String salestransactionNumber="";String uniquetype="";
		String status="Fail";
		try{
			salesTransactionList=new ArrayList<SalesTransaction>();
			salesTransactionList=adao.getsalestransactionNumber(clientID,aTransaction.getTransactionType());
			if(aTransaction.getTransactionType().equals("Invoice")){
				uniquetype=salesInvoice;
			}else if(aTransaction.getTransactionType().equals("Payment")){
				uniquetype=salesPayment;
			}else if(aTransaction.getTransactionType().equals("Estimate")){
				uniquetype=salesEstimate;
			}else if(aTransaction.getTransactionType().equals("Sales Receipt")){
				uniquetype=salesReceipt;
			}else if(aTransaction.getTransactionType().equals("Credit Memo")){
				uniquetype=salesCreditMemo;
			}else if(aTransaction.getTransactionType().equals("Delayed Charge")){
				uniquetype=salesDelayedCharge;
			}else if(aTransaction.getTransactionType().equals("Time Activity")){
				uniquetype=salesTimeActivity;
			}
			if(salesTransactionList.size()>0){
				String transactionNumber=salesTransactionList.get(salesTransactionList.size()-1).getReferenceNumber();
				int listSize=Integer.parseInt(transactionNumber.substring(5))+1;
				if(listSize<=9) salestransactionNumber=uniquetype+"000000"+listSize;
				else if(listSize>9 && listSize<=99) salestransactionNumber=uniquetype+"00000"+listSize;
				else if(listSize>99 && listSize<=999) salestransactionNumber=uniquetype+"0000"+listSize;
				else if(listSize>999 && listSize<=9999) salestransactionNumber=uniquetype+"000"+listSize;
				else if(listSize>9999 && listSize<=99999) salestransactionNumber=uniquetype+"00"+listSize;
				else if(listSize>99999 && listSize<=999999) salestransactionNumber=uniquetype+"0"+listSize;
				else salestransactionNumber=uniquetype+listSize;
			}else{
				salestransactionNumber=uniquetype+"0000001";
			}
			aTransaction.setTransactionNo(salestransactionNumber);
			status=adao.saveSalesTransaction(clientID,aTransaction,productdetails);
		}catch(Exception e){
			logger.warn("------------Inside Exception-------------------"+e.getMessage());
		}finally{
			salestransactionNumber="";uniquetype="";
		}
		return status;
	}

	@Override
	public List<AccountsDatabean> getcoaDetailsList(AccountsDatabean accountsDatabean) {
		logger.info("[saveSalesTransaction()-----------------Inside saveSalesTransaction() in BoImpn Calling-----------------]");
		List<AccountsDatabean> coaDetailList=null;
		try{
			coaDetailList=new ArrayList<AccountsDatabean>();
			coaDetailList=AccountsJDBC.getcoaDetailList(accountsDatabean);
		}catch(Exception e){
			logger.warn("--------------Inside Exception---------------------"+e.getMessage());
		}
		return coaDetailList;
	}

	@Override
	public List<ATransaction> getsalestransactiontableview(String clientID,ATransaction aTransaction) {
		logger.info("[getsalestransactiontableview()]-----------------------Inside getsalestransactiontableview() in DaoImpln Calling--------------------------");
		List<SalesTransaction> salestransactionList=null;List<ATransaction> domainList=null;
		try{
			salestransactionList=adao.getsalestransactiontableview(clientID, aTransaction);
			domainList=new ArrayList<ATransaction>();
			if(salestransactionList.size()>0){
				for (int i = 0; i < salestransactionList.size(); i++) {
					ATransaction atrans=new ATransaction();
					atrans.setTransactionNo(salestransactionList.get(i).getReferenceNumber());
					atrans.setBillDate(salestransactionList.get(i).getStartDate());
					atrans.setTransactionType(salestransactionList.get(i).getTransactionType());
					atrans.setCustomerName(salestransactionList.get(i).getCustomerName());
					atrans.setDueDate(salestransactionList.get(i).getDueDate());
					atrans.setBalAmount(salestransactionList.get(i).getBalanceAmount());
					atrans.setTransamount(salestransactionList.get(i).getTransactionAmount());
					atrans.setTransactionID(salestransactionList.get(i).getSales_transaction_ID());
					atrans.setTransStatus(salestransactionList.get(i).getInvoiceStatus());
					atrans.setStatus(salestransactionList.get(i).getPaymentStatus());
					atrans.setCurrency(salestransactionList.get(i).getCurrencyType());
					atrans.setCurrencyAmount(salestransactionList.get(i).getCurrencyAmount());
					atrans.setBaseCurrency(salestransactionList.get(i).getBaseCurrency());
					atrans.setCurrencytaxAmount(salestransactionList.get(i).getCurrencyTaxAmount());
					atrans.setBeforeTaxAmount(salestransactionList.get(i).getCurrencybeforetaxAmount());
					domainList.add(atrans);
				}
			}
		}catch(Exception e){
			logger.warn("--------------------Inside Exception----------------------"+e.getMessage());
		}finally{
			salestransactionList=null;
		}
		return domainList;
	}

	@Override
	public List<ATransaction> getpaymentdetails(String clientID,String paymentStatus, ATransaction aTransaction) {
		List<ATransaction> domainList=null;Date date=new Date();
		List<SalesTransaction> salestransactionList=null;
		try{
			domainList=new ArrayList<ATransaction>();
			salestransactionList=adao.getcustomersalestransactionList(clientID,paymentStatus,aTransaction);
			if(paymentStatus.equalsIgnoreCase("receive payment")){
				aTransaction.setCustomerName(salestransactionList.get(0).getCustomerName());
				aTransaction.setCustomerEmailId(salestransactionList.get(0).getMailId());
				aTransaction.setCurrency(salestransactionList.get(0).getCurrencyType());
				aTransaction.setDescription(salestransactionList.get(0).getDescription());
				aTransaction.setBillDate(date);
			}
			if(salestransactionList.size()>0){
				for (int i = 0; i < salestransactionList.size(); i++) {
					ATransaction atrans=new ATransaction();
					atrans.setSerialNo(String.valueOf(i+1));
					atrans.setTransactionNo(salestransactionList.get(i).getReferenceNumber());
					atrans.setBillDate(salestransactionList.get(i).getStartDate());
					atrans.setDueDate(salestransactionList.get(i).getDueDate());
					atrans.setTransamount(salestransactionList.get(i).getTransactionAmount());
					atrans.setAmount(salestransactionList.get(i).getCurrencyAmount());
					atrans.setCurrency(salestransactionList.get(i).getCurrencyType());
					atrans.setTransactionID(salestransactionList.get(i).getSales_transaction_ID());
					atrans.setBalAmount(salestransactionList.get(i).getBalanceAmount());
					domainList.add(atrans);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return domainList;
	}
	
	@Override
	public String generateInvoice(String clientID, int transactionID) {
		return adao.generateInvoice(clientID,transactionID);
	}

	 	@Override
		public String salesTransactionUpadte(String clientID,ATransaction aTransaction) {
			return adao.salesTransactionUpadte(clientID,aTransaction);
		}

	    @Override
		public void salesTransactionView(String clientID,ATransaction aTransaction) {
			List<SalesTransaction> salesTransactionList=null;
			List<SalesAccountsPayment> accountList=null;
			try{
				salesTransactionList=new ArrayList<SalesTransaction>();
				accountList=new ArrayList<SalesAccountsPayment>();
				aTransaction.setProductList(new ArrayList<ATransaction>());
				salesTransactionList=adao.salesTransactionView(clientID,aTransaction);
				if(salesTransactionList.size()>0){
					accountList=adao.salesTransactionPaymentview(salesTransactionList.get(0).getSales_transaction_ID(),clientID);
						for (int j = 0; j < accountList.size(); j++) {
							ATransaction transaction=new ATransaction();
							transaction.setSerialNo(String.valueOf(j+1));
							transaction.setSalesPaymentID(accountList.get(j).getSales_account_payments_ID());
							transaction.setProductName(accountList.get(j).getProductName());
							transaction.setQuantity(accountList.get(j).getQuantity());
							transaction.setRate(accountList.get(j).getRate());
							transaction.setAmount(accountList.get(j).getTotalAmount());
							transaction.setTaxes(accountList.get(j).getTaxes());
							transaction.setGstAmount(accountList.get(j).getGstAmount());
							transaction.setPercentIGSTFlag("none");
							transaction.setPercentGSTFlag("none");
							try{
								if(!accountList.get(j).getCgstType().equalsIgnoreCase(null) || !accountList.get(j).getCgstType().equalsIgnoreCase("")){
									transaction.setCgstType(accountList.get(j).getCgstType());
									transaction.setCgstAmount(accountList.get(j).getCgstAmount());
									transaction.setPercentageValue(accountList.get(j).getTaxPercentage());
									transaction.setPercentGSTFlag("1");
								}
							}catch(NullPointerException e){
								logger.warn("[salesTransactionView] Inside Exception", e.getMessage()); 
							}
							try{
								if(!accountList.get(j).getSgstType().equalsIgnoreCase(null) || !accountList.get(j).getSgstType().equalsIgnoreCase("")){
									transaction.setSgstType(accountList.get(j).getSgstType());
									transaction.setSgstAmount(accountList.get(j).getSgstAmount());
									transaction.setPercentageValue(accountList.get(j).getTaxPercentage());
									transaction.setPercentGSTFlag("1");
								}
							}catch(NullPointerException e){
								logger.warn("[salesTransactionView] Inside Exception", e.getMessage()); 
							}
							try{
								if(!accountList.get(j).getIgstType().equalsIgnoreCase(null) || !accountList.get(j).getIgstType().equalsIgnoreCase("")){
									transaction.setGstType(accountList.get(j).getIgstType());
									transaction.setPercentageAmount(accountList.get(j).getTaxAmount());
									transaction.setPercentageValue(accountList.get(j).getTaxPercentage());
									transaction.setPercentIGSTFlag("1");
								}
							}catch(NullPointerException e){
								logger.warn("[salesTransactionView] Inside Exception", e.getMessage()); 
							}
							aTransaction.getProductList().add(transaction);
						 }
					 aTransaction.setCustomerName(salesTransactionList.get(0).getCustomerName());
					 aTransaction.setCustomerEmailId(salesTransactionList.get(0).getMailId());
					 aTransaction.setCurrencyType(salesTransactionList.get(0).getCurrencyType());
					 aTransaction.setDescription(salesTransactionList.get(0).getDescription());
					 aTransaction.setCustomerBillingAddress(salesTransactionList.get(0).getMailingAddress());
					 aTransaction.setTerms(salesTransactionList.get(0).getTerms());
					 //aTransaction.setTransamount(salesTransactionList.get(0).getTransactionAmount());
					 aTransaction.setTotalAmount(salesTransactionList.get(0).getTransactionAmount());
					 aTransaction.setBillDate(salesTransactionList.get(0).getStartDate());
					 aTransaction.setDueDate(salesTransactionList.get(0).getDueDate());
					 aTransaction.setBalAmount(salesTransactionList.get(0).getBalanceAmount());
					 aTransaction.setTaxType(salesTransactionList.get(0).getTaxType());
					 aTransaction.setLocation(salesTransactionList.get(0).getPlaceofLocation());
					 aTransaction.setBaseCurrency(salesTransactionList.get(0).getBaseCurrency());
					 aTransaction.setSubTotalAmount(salesTransactionList.get(0).getSubTotalAmount());
					 aTransaction.setCurrencyAmount(salesTransactionList.get(0).getCurrencyAmount());
					 aTransaction.setPaymentMode(salesTransactionList.get(0).getPaymentMethod());
					 aTransaction.setAccountType(salesTransactionList.get(0).getDepositAccount());
					 aTransaction.setPayAmount(salesTransactionList.get(0).getPaidAmount());
					 aTransaction.setTransStatus(salesTransactionList.get(0).getInvoiceStatus());
				}
			}catch(Exception e){
				logger.warn("[salesTransactionView] Inside Exception", e.getMessage()); 
			}
		}
	    
	    @Override
	    public String quoteApproval(String clientID, String userID,ArrayList<SalesOrderFormMB> quoteTablelist, SalesOrderFormMB sales) {
			logger.info("---------------- Inside quotationApproval method --------------");
			List<SalesQuote> list=null;
			String status="Fail";
			//List<SalesQuoteDetails> quotdetailList=null;
			//List<String> vendormaillist=null;
			//List<SalesOrderFormMB> quotationDetailList=null;
			try{
				//vendormaillist=new ArrayList<String>();
				//quotationDetailList=new ArrayList<SalesOrderFormMB>(); 
				for (int i = 0; i < quoteTablelist.size(); i++) {
					if(quoteTablelist.get(i).purchaseCheck ==true){
						list=dao.getquoteApprovallist(clientID,userID,quoteTablelist,i);
						if(list.size()>0){
							for (int j = 0; j < list.size(); j++) {
								status=dao.quoteApproval(list.get(j).getSales_quote_ID());
								/*if(status.equalsIgnoreCase("Success")){
									quotdetailList=dao.getquotationDetailsList(list.get(j).getQuotationId());
									if(quotdetailList.size()>0){
										for (int k = 0; k < quotdetailList.size(); k++) {
											VendorRegisterFormMB ven=new VendorRegisterFormMB();
											ven.setProductName(quotdetailList.get(k).getProductName());
											ven.setVendorName(quotdetailList.get(k).getVendorName());
											ven.setProductCount(quotdetailList.get(k).getQuotationId().getProductCount());
											ven.setDeliveryDate(quotdetailList.get(k).getQuotationId().getDeliveryDate());
											ven.setVendormailid(quotdetailList.get(k).getVendor_ID().getEmail_ID_vendor());
											quotationDetailList.add(ven);
											vendor.setQuotationDetailList(quotationDetailList);
											vendormaillist.add(quotdetailList.get(k).getVendorName());
											vendor.setMailList(vendormaillist);
										}
									}*/
								//}
							}
						}
					}
				}
			}catch(Exception e){
				logger.warn("[quoteApproval] Inside Exception", e.getMessage()); 
			}finally{
			}
			return status;
		}
	   

@Override
public List<LoginAccess> getglobalsearchList(String clientID, String userID){
	List<LoginAccess> valueList=null;
	try{
		valueList=new ArrayList<LoginAccess>();
		valueList=AccountsJDBC.getglobalsearchList(clientID, userID);
	}catch(Exception e){
		e.printStackTrace();
	}
	return valueList; 
}

@Override
public String codeSave(ATransaction aTransaction) {
	return dao.codeSave(aTransaction);
}

@Override
public List<String> getCodelist(String clientID) {
	return dao.getCodelist(clientID);
}

@Override
public List<ATransaction> codeDetails(ATransaction aTransaction) {
	List<ATransaction> codeList=null;
	List<Code> list=null;
	try{
		codeList=new ArrayList<ATransaction>();
		list=new ArrayList<Code>();
		list=dao.codeDetails(aTransaction);
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				ATransaction trans=new ATransaction();
				trans.setCode(list.get(i).getCode());
				trans.setCodeDescription(list.get(i).getDescription());
				trans.setSerialNo(String.valueOf(i+1));
				trans.setStatus(list.get(i).getStatus());
				codeList.add(trans);
			} 
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return codeList;
}

@Override
public void getDescription(ATransaction aTransaction) {
	dao.getDescription(aTransaction);
}

@Override
public List<AccountsDatabean> getjournalEntryList(AccountsDatabean accountsDatabean) {
	logger.info("[getjournalEntryList()]------------------Inside getjournalEntryList() in BoImpln Calling---------------------");
	List<AccountsDatabean> domainList=null;List<JournalEntry> journalEntryList=null;List<ChartOfAccount> accountlist=null;
	BigDecimal debitTotal=BigDecimal.valueOf(0);BigDecimal creditTotal=BigDecimal.valueOf(0);
	try{
		accountsDatabean.setCreditParticularList(new ArrayList<String>());accountsDatabean.setDebitParticularList(new ArrayList<String>());
		accountsDatabean.setDebitAmountList(new ArrayList<String>());accountsDatabean.setCreditAmountList(new ArrayList<String>());
		List<String> list=Arrays.asList("Deposit","Credit Card Expense","Journal");
		List<String> list1=Arrays.asList("Journal Entry","Cheque Expense","Credit Card Credit");
		domainList=new ArrayList<AccountsDatabean>();journalEntryList=new ArrayList<JournalEntry>();
		accountlist=new ArrayList<ChartOfAccount>();
		journalEntryList=adao.getjournalEntryList(accountsDatabean);
		accountlist=adao.getchartofaccountList(accountsDatabean.getClientID());
		if(journalEntryList.size()>0){
			for (JournalEntry journal : journalEntryList) {
				debitTotal=BigDecimal.valueOf(0);creditTotal=BigDecimal.valueOf(0);
				AccountsDatabean acc=new AccountsDatabean();
				acc.setAccount_date(journal.getTranDate());
				acc.setTransactionType(journal.getTransactionType());
				acc.setRefNo(journal.getInvoiceNumber());
				acc.setPayeeName(journal.getPayeeName());
				for (int i = 0; i < accountlist.size(); i++) {
					if(accountlist.get(i).getAccountName().equals(journal.getDebitParticular().split(",")[0])){
						if(accountlist.get(i).getStatus().equals("Active")){
							acc.setDebitParticular(journal.getDebitParticular());
							acc.setDebitParticularList(Arrays.asList(journal.getDebitParticular().split(",")));
						}else{
							acc.setDebitParticular(journal.getDebitParticular()+""+"(deleted)");
							acc.setDebitParticularList(Arrays.asList(journal.getDebitParticular().split(",")));
						}
					}
					if(accountlist.get(i).getAccountName().equals(journal.getCreditParticular().split(",")[0])){
						if(accountlist.get(i).getStatus().equals("Active")){
							acc.setCreditParticular(journal.getCreditParticular());
							acc.setCreditParticularList(Arrays.asList(journal.getCreditParticular().split(",")));
						}else{
							acc.setCreditParticular(journal.getCreditParticular()+""+"(deleted)");
							acc.setCreditParticularList(Arrays.asList(journal.getCreditParticular().split(",")));
						}
					}
				}
				acc.setDebitAmountList(Arrays.asList(journal.getDebitAmount().split(",")));
				acc.setCreditAmountList(Arrays.asList(journal.getCreditAmount().split(",")));
				if(list.contains(journal.getTransactionType())){
					acc.setAccount_description("Opening Balance");
				}else if(list1.contains(journal.getTransactionType())){
					acc.setAccount_description("Adjust balance for deletion");
				}
				for (int j = 0; j < acc.getDebitAmountList().size(); j++) {
					debitTotal=debitTotal.add(new BigDecimal(acc.getDebitAmountList().get(j)));
					acc.setDebitAmount(String.valueOf(debitTotal));
				}
				for (int j = 0; j < acc.getCreditAmountList().size(); j++) {
					creditTotal=creditTotal.add(new BigDecimal(acc.getCreditAmountList().get(j)));
					acc.setCreditAmount(String.valueOf(creditTotal));
				}
				domainList.add(acc);
			}
			debitTotal=BigDecimal.valueOf(0);creditTotal=BigDecimal.valueOf(0);
			for (int i = 0; i < domainList.size(); i++) {
					debitTotal=debitTotal.add(new BigDecimal(domainList.get(i).getDebitAmount()));
					creditTotal=creditTotal.add(new BigDecimal(domainList.get(i).getCreditAmount()));				
			}
			accountsDatabean.setDebitTotal(debitTotal.toString());accountsDatabean.setCreditTotal(creditTotal.toString());
		}
	}catch(Exception e){
		logger.warn("------------Inside Exception----------------"+e.getMessage());
	}finally{
		journalEntryList=null;
	}
	return domainList;
}

public void getProfitLoss(AccountsDatabean accountsDatabean){
	logger.info("[getProfitLoss]---------------------Inside getProfitLoss() in Bo Impln Calling-----------------------------");
	List<AccountsDatabean> profitandlossList=null;BigDecimal incometotalamt=BigDecimal.valueOf(0);BigDecimal purchasecogtotalamt=BigDecimal.valueOf(0);
	BigDecimal otherincometotalamt=BigDecimal.valueOf(0);BigDecimal cogtotalamt=BigDecimal.valueOf(0);
	BigDecimal expensestotalamt=BigDecimal.valueOf(0);BigDecimal otherexpensestotalamt=BigDecimal.valueOf(0);
	BigDecimal grossprofitamt=BigDecimal.valueOf(0);BigDecimal profitamt=BigDecimal.valueOf(0);BigDecimal purchaseamt=BigDecimal.valueOf(0);
	try{
		profitandlossList=new ArrayList<AccountsDatabean>();accountsDatabean.setIncomeList(new ArrayList<AccountsDatabean>());
		accountsDatabean.setOtherIncomeList(new ArrayList<AccountsDatabean>());accountsDatabean.setCogList(new ArrayList<AccountsDatabean>());
		accountsDatabean.setExpensesList(new ArrayList<AccountsDatabean>());accountsDatabean.setOtherExpensesList(new ArrayList<AccountsDatabean>());
		accountsDatabean.setPurchaseList(new ArrayList<AccountsDatabean>());
		profitandlossList=AccountsJDBC.getprofitlossDetailList(accountsDatabean);
		if(profitandlossList.size()>0){
			for (int i = 0; i < profitandlossList.size(); i++) {
				if(profitandlossList.get(i).getAccount_type().equals("Income")){
					AccountsDatabean accounts=new AccountsDatabean();
					accounts.setAccount_name(profitandlossList.get(i).getAccount_name());
					accounts.setBalance(profitandlossList.get(i).getBalance());
					incometotalamt=incometotalamt.add(new BigDecimal(profitandlossList.get(i).getBalance()));
					accountsDatabean.getIncomeList().add(accounts);
				}
				if(profitandlossList.get(i).getAccount_type().equals("Other Income")){
					AccountsDatabean accounts=new AccountsDatabean();
					accounts.setAccount_name(profitandlossList.get(i).getAccount_name());
					accounts.setBalance(profitandlossList.get(i).getBalance());
					otherincometotalamt=otherincometotalamt.add(new BigDecimal(profitandlossList.get(i).getBalance()));
					accountsDatabean.getOtherIncomeList().add(accounts);
				}
				if(profitandlossList.get(i).getAccount_type().equals("Cost of Goods Sold")){
					AccountsDatabean accounts=new AccountsDatabean();
					accounts.setAccount_name(profitandlossList.get(i).getAccount_name());
					accounts.setBalance(profitandlossList.get(i).getBalance());
					cogtotalamt=cogtotalamt.add(new BigDecimal(profitandlossList.get(i).getBalance()));
					accountsDatabean.getCogList().add(accounts);
				}
				if(profitandlossList.get(i).getAccount_type().equals("Expenses")){
					if(profitandlossList.get(i).getSubAccount().equalsIgnoreCase("Purchases")){
						AccountsDatabean accounts=new AccountsDatabean();
						accounts.setAccount_name(profitandlossList.get(i).getAccount_name());
						accounts.setBalance(profitandlossList.get(i).getBalance());
						purchaseamt=purchaseamt.add(new BigDecimal(profitandlossList.get(i).getBalance()));
						accountsDatabean.getPurchaseList().add(accounts);
					}else{
						AccountsDatabean accounts=new AccountsDatabean();
						accounts.setAccount_name(profitandlossList.get(i).getAccount_name());
						accounts.setBalance(profitandlossList.get(i).getBalance());
						expensestotalamt=expensestotalamt.add(new BigDecimal(profitandlossList.get(i).getBalance()));
						accountsDatabean.getExpensesList().add(accounts);
					}
				}
				if(profitandlossList.get(i).getAccount_type().equals("Other Expenses")){
					AccountsDatabean accounts=new AccountsDatabean();
					accounts.setAccount_name(profitandlossList.get(i).getAccount_name());
					accounts.setBalance(profitandlossList.get(i).getBalance());
					otherexpensestotalamt=otherexpensestotalamt.add(new BigDecimal(profitandlossList.get(i).getBalance()));
					accountsDatabean.getOtherExpensesList().add(accounts);
				}
			}
		}
		if(profitandlossList.size()==0){
			incometotalamt=BigDecimal.valueOf(0);purchaseamt=BigDecimal.valueOf(0);
			otherincometotalamt=BigDecimal.valueOf(0);cogtotalamt=BigDecimal.valueOf(0);
			expensestotalamt=BigDecimal.valueOf(0);otherexpensestotalamt=BigDecimal.valueOf(0);
			grossprofitamt=BigDecimal.valueOf(0);profitamt=BigDecimal.valueOf(0);
		}
		accountsDatabean.setTotalIncome(incometotalamt.toString());accountsDatabean.setTotalOtherIncome(otherincometotalamt.toString());
		accountsDatabean.setCogTotal(cogtotalamt);accountsDatabean.setTotalExpenses(expensestotalamt.toString());
		accountsDatabean.setTotalOtherExpenses(otherexpensestotalamt.toString());accountsDatabean.setTotalPurchases(purchaseamt.toString());
		purchasecogtotalamt=purchaseamt.add(cogtotalamt);
		grossprofitamt=incometotalamt.subtract(purchasecogtotalamt);
		accountsDatabean.setCrossProfit(grossprofitamt.toString());
		BigDecimal incomeamt=grossprofitamt.add(otherincometotalamt);
		BigDecimal expensesamt=expensestotalamt.add(otherexpensestotalamt);
		profitamt=incomeamt.subtract(expensesamt);
		accountsDatabean.setProfitAmount(profitamt);
	}catch(Exception e){
		logger.warn("------------Inside Exception----------------"+e.getMessage());
	}finally{
		incometotalamt=BigDecimal.valueOf(0);otherincometotalamt=BigDecimal.valueOf(0);cogtotalamt=BigDecimal.valueOf(0);
		expensestotalamt=BigDecimal.valueOf(0);otherexpensestotalamt=BigDecimal.valueOf(0);purchasecogtotalamt=BigDecimal.valueOf(0);
		grossprofitamt=BigDecimal.valueOf(0);profitamt=BigDecimal.valueOf(0);purchaseamt=BigDecimal.valueOf(0);
	}
}

@Override
public String paymentSave(Buyer buyer) {
	return dao.paymentSave(buyer);
}

@Override
public List<Buyer> getmemberPayment(Buyer buyer) {
	List<MemberPayment> payList=null;
	List<Buyer> buyerList=null;
	try{
		buyerList=new ArrayList<Buyer>();
		payList=new ArrayList<MemberPayment>();
		payList=dao.getmemberPayment(buyer);
		if(payList.size()>0){
			for (int i = 0; i < payList.size(); i++) {
				Buyer buy=new Buyer();
				buy.setCash(payList.get(i).getAmount());
				buy.setDate(payList.get(i).getCreaateDate());
				buy.setNote(payList.get(i).getDescription());
				buy.setPaymentID(payList.get(i).getPaymentId());
				buyerList.add(buy);
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return buyerList;
}

@Override
public String mamberPaymentUpdate(Buyer buyer) {
	return dao.mamberPaymentUpdate(buyer);
}

@Override
public String mamberPaymentDelete(Buyer buyer) {
	return dao.mamberPaymentDelete(buyer);
}

@Override
public String revenueInsertion(Sales sales) {
	return dao.revenueInsertion(sales);
}

@Override
public List<SalesOrderFormMB> getValuesRevenue(Sales sales) {
	return dao.getValuesRevenue(sales);
}

@Override
public List<Revenue> getViewRevenue(Sales sales) {
	return dao.getViewRevenue(sales);
}

@Override
public String coformDelete(Sales sales) {
	return dao.coformDelete(sales);
}

@Override
public String revenueUpdate(Sales sales) {
	return dao.revenueUpdate(sales);
}
}
