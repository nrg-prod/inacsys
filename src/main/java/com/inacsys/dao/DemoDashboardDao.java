package com.inacsys.dao;

import java.text.ParseException;
import java.util.*;

import com.inacsys.domain.Approval;
import com.inacsys.domain.Buyer;
import com.inacsys.domain.CategoryRegistration;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.ProductRegister;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.domain.Report1;
import com.inacsys.domain.StockView;
import com.inacsys.domain.Vendor;
import com.inacsys.domain.VendorDelete;
import com.inacsys.exception.DemoException;
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
 *         ------------------------------------------------------------ Alex
 * 
 */
public interface DemoDashboardDao {
	public int getsalesQuantityOfMonth(Date fDate, Date tDate)
			throws DemoException;


	public float getSalesAmount(Date fDate, Date tDate) throws DemoException;

	public float getpurchaseAmount(Date fDate, Date tDate) throws DemoException;

	public int getStockinQuantity(Date fDate, Date tDate) throws DemoException;

	public int getStockOutQuantity(Date fDate, Date tDate) throws DemoException;


	public int getsalesInvoice(Date fDate, Date tDate) throws DemoException;

	public int getpurchaseInvoice(Date fDate, Date tDate) throws DemoException;

}
