package com.inacsys.dao;

import java.util.*;

import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.AccPayableLiability;
import com.inacsys.shared.AccReceivableAsset;
import com.inacsys.shared.CashAsset;
import com.inacsys.shared.I0001;
import com.inacsys.shared.I0015;
import com.inacsys.shared.I0021;
import com.inacsys.shared.I0022;
import com.inacsys.shared.I0023;
import com.inacsys.shared.Payroll;
import com.inacsys.shared.PurchaseReturn;
import com.inacsys.shared.SalesReturn;
import com.inacsys.shared.Transaction;

/**
 * This Java Class will communicate with Database
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 * 
 */
public interface DemoAccountReceivableDao {
	public List<Transaction> getTransactionIncome(Date fDate, Date tDate)
			throws DemoException;

	public List<I0021> getSalesAsset(String sDate) throws DemoException;

	public List<String> getSalesDates(Date fDate, Date tDate)
			throws DemoException;

	public List<Transaction> getCashAsset(Date fDate, Date tDate)
			throws DemoException;

	public List<Date> getpurchaseOrderDatefromPurchase(Date fDate, Date tDate)
			throws DemoException;

	public List<I0015> getPurchasesforAsset(Date fDate) throws DemoException;

	public List<Date> getpurchaseOrderDatefromRawPurchase(Date fDate, Date tDate)
			throws DemoException;

	public List<Transaction> getTransactionExpense(Date fDate, Date tDate)
			throws DemoException;

	public List<String> getpayrollDates(Date fDate, Date tDate)
			throws DemoException;

	public List<Payroll> getPayrollDate(String fDate) throws DemoException;

	public List<AccReceivableAsset> getAccountReceivable(int id)
			throws DemoException;

	public List<CashAsset> getCashAssetInfo(int id) throws DemoException;

	public List<AccPayableLiability> getAccountPayableInfo(int id)
			throws DemoException;

	/* siva 16_2_15 */

	public List<I0022> getInvicesData(int sid) throws DemoException;

	public List<I0023> getInvoicePaymentSalesData(int invoiceId)
			throws DemoException;

	public List<I0021> getSalesAsset1(String sDate) throws DemoException;

	public List<String> getSalesDates1(Date fDate, Date tDate)
			throws DemoException;

	public List<I0022> getInvicesPurchaseData(int pid) throws DemoException;

	public List<I0023> getInvoicePaymentPurchaseData(int invoiceId)
			throws DemoException;

	public String getInvoicePaymentPurchaseData1(int invoiceId)
			throws DemoException;

	public List<I0023> getInvoicePaymentPurchaseData11(int invoiceId)
			throws DemoException;

	public String getInvoicePaymentPurchaseData12(int invoiceId)
			throws DemoException;

	public List<SalesReturn> quicksalereturn(String ordernumber)
			throws DemoException;

	public List<SalesReturn> normalsalereturn(int Sales_ID)
			throws DemoException;

	public List<PurchaseReturn> purchasereturn(int p_ID) throws DemoException;

	public List<I0023> getInvoicePaymentPurchaseData2(int invoiceId)
			throws DemoException;

	public List<PurchaseReturn> getamount(int j) throws DemoException;
}
