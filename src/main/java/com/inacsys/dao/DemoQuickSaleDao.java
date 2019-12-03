package com.inacsys.dao;

import java.text.ParseException;
import java.util.*;

import com.inacsys.domain.Approval;
import com.inacsys.domain.Buyer;
import com.inacsys.domain.CategoryRegistration;
import com.inacsys.domain.Commission;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.ProductRegister;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.domain.Report1;
import com.inacsys.domain.StockView;
import com.inacsys.domain.Vendor;
import com.inacsys.domain.VendorDelete;
import com.inacsys.exception.DemoException;
import com.inacsys.managedBean.QuickSaleMB;
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
import com.inacsys.shared.I0033;

/**
 * This Java Class will communicate with Database
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *  ------------------------------------------------------------ 
	Alex
 * 
 */
public interface DemoQuickSaleDao {

	public String getpurchaseInfo(PurchaseOrder p) throws DemoException;

	public List<String> getProductName() throws DemoException;

	public List<I0019> getBarCodeData(String productName) throws DemoException;

	public String saveSales(PurchaseOrder p) throws DemoException;

	public String qucikSalesConform(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String qucikSalesConform2(PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<I0032> catogerycustomer(ArrayList<I0032> buyername,
			PurchaseOrder purchaseOrder) throws DemoException;

	public List<String> customername(Commission commission)
			throws DemoException;

	public String commissionview(Commission commission) throws DemoException;

	public String salesconfirm(PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<String> getRollList(String productName,
			ArrayList<String> rollList) throws DemoException;

	public String qucikSalesRoll(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String updateRollSales(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesRollconfirm(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String updateRollQuantity(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String salesOrderdelete(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String quickSaleDropdown(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String quicksaleReturnSubmit(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String quickViewSalesReturn(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String getbarcodeInfo(PurchaseOrder purchaseOrder)
			throws DemoException;

	public String getPurchaseQty(PurchaseOrder purchaseOrder)
			throws DemoException;

	public ArrayList<String> getRollQuanList(String productName,
			ArrayList<String> rollList) throws DemoException;

	public String findGlobalSearch(String golbalnamesearch);
}
