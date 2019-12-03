package com.inacsys.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.context.FacesContext;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.inacsys.shared.I0025;
import com.inacsys.shared.I0031;
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
 * 
 */
@Repository
@Singleton
public class DemoAccountReceivableDaoImpl implements DemoAccountReceivableDao {

	final Logger logger = LoggerFactory
			.getLogger(DemoAccountReceivableDaoImpl.class);

	@PersistenceContext(unitName = "inacsys-pu")
	private EntityManager entitymanager;

	/* Account Receivable Asset */

	@Transactional(value = "transactionManager")
	public List<Transaction> getTransactionIncome(Date fDate, Date tDate)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		List<Transaction> trans = null;
		try {
			q = entitymanager
					.createQuery("from Transaction where (status=? or status='credited') and transactionStatus=? and (paymentMode=? or paymentMode=? or paymentMode=?) and client_ID=?");
			q.setParameter(1, "inserted");
			q.setParameter(2, "credited");
			q.setParameter(3, "Card");
			q.setParameter(4, "Cheque");
			q.setParameter(5, "Transfer");
			q.setParameter(6, clientID);
			trans = q.getResultList();
			logger.info("size-------->>>>" + trans.size());
		} catch (Exception e) {
		} finally {

		}
		return trans;
	}

	@Transactional(value = "transactionManager")
	public List<AccReceivableAsset> getAccountReceivable(int id)
			throws DemoException {
		Query q = null;
		List<AccReceivableAsset> ara = null;
		try {
			q = entitymanager.createQuery("from AccReceivableAsset where transaction_id=? and status=?");
			q.setParameter(1, id);
			q.setParameter(2, "inserted");
			ara = q.getResultList();
			logger.info("acc receivablesize---------->>>" + ara.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
		}
		return ara;
	}

	@Transactional(value = "transactionManager")
	public List<I0021> getSalesAsset(String sDate) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		List<I0021> sales = null;
		try {
			q = entitymanager
					.createQuery("from I0021 where client_ID=? and salesOrderNumber=? and (status=? or status=?)");
			q.setParameter(1, clientID);
			q.setParameter(2, sDate);
			logger.info("sale date------>>>>" + sDate);
			q.setParameter(3, "insert");
			q.setParameter(4, "Delivered");
			sales = q.getResultList();
			logger.info("sales size for only sales-------->>" + sales.size());
		} catch (Exception e) {
		} finally {

		}
		return sales;
	}

	@Transactional(value = "transactionManager")
	public List<I0021> getSalesAsset1(String sDate) throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0021> sales = null;
		try {
			q = entitymanager
					.createQuery("from I0021 where salesOrderNumber=? and status=? and client_ID=?");
			q.setParameter(1, sDate);
			q.setParameter(2, "Quick sales");
			q.setParameter(3, clientID);
			sales = q.getResultList();
			logger.info("sales size-------->>" + sales.size());
		} catch (Exception e) {
		} finally {

		}
		return sales;
	}

	@Transactional(value = "transactionManager")
	public List<String> getSalesDates(Date fDate, Date tDate)
			throws DemoException {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<String> sales = null;
		try {
			q = entitymanager
					.createQuery("select salesOrderNumber from I0021 where client_ID=? and (status=? or status=?)");
			q.setParameter(1, clientID);
			q.setParameter(2, "insert");
			q.setParameter(3, "Delivered");
			sales = q.getResultList();
			logger.info("Date Count>>>>>>>>>>>>>>>>>>>>" + sales.size());
		} catch (Exception e) {
		} finally {

		}
		return sales;
	}

	@Transactional(value = "transactionManager")
	public List<String> getSalesDates1(Date fDate, Date tDate)
			throws DemoException {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<String> sales = null;
		try {
			q = entitymanager
					.createQuery("select salesOrderNumber from I0021 where client_ID=? and status=?");
			q.setParameter(1, clientID);
			q.setParameter(2, "Quick sales");
			sales = q.getResultList();
			logger.info("Date Count>>>>>>>>>>>>>>>>>>>>" + sales.size());
		} catch (Exception e) {
		} finally {

		}
		return sales;
	}

	/* Cash Asset */

	@Transactional(value = "transactionManager")
	public List<Transaction> getCashAsset(Date fDate, Date tDate)
			throws DemoException {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<Transaction> trans = null;
		try {
			q = entitymanager
					.createQuery("from Transaction where status=? and transactionStatus=? and paymentMode=? and client_ID=?");

			q.setParameter(1, "inserted");
			q.setParameter(2, "credited");
			q.setParameter(3, "Cash");
			q.setParameter(4, clientID);
			trans = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return trans;
	}

	@Transactional(value = "transactionManager")
	public List<CashAsset> getCashAssetInfo(int id) throws DemoException {
		Query q = null;
		List<CashAsset> ca = null;
		try {
			q = entitymanager
					.createQuery("from CashAsset where transaction_id=? and status=?");
			q.setParameter(1, id);
			q.setParameter(2, "inserted");
			ca = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
		}
		return ca;
	}

	/* Account payable Liabilities */

	@Transactional(value = "transactionManager")
	public List<Date> getpurchaseOrderDatefromPurchase(Date fDate, Date tDate)
			throws DemoException {
		List<Date> pod = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			q = entitymanager
					.createQuery("select orderDate from I0015 where (status=? or status=?) and client_ID=?");

			q.setParameter(1, "delivered");
			q.setParameter(2, "insert");
			q.setParameter(3, clientID);
			pod = q.getResultList();
			logger.info("zize i0015-------->>>" + pod.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return pod;
	}

	@Transactional(value = "transactionManager")
	public List<I0015> getPurchasesforAsset(Date fDate) throws DemoException {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0015> p = null;
		try {
			q = entitymanager
					.createQuery("from I0015 where orderDate=? and (status=? or status=?) and client_ID=?");
			q.setParameter(1, fDate);
			logger.info("order date-------->>>>" + fDate);
			q.setParameter(2, "delivered");
			q.setParameter(3, "insert");
			q.setParameter(4, clientID);
			p = q.getResultList();
			logger.info(">>size>>>>>>>>>>>>>" + p.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return p;
	}

	@Transactional(value = "transactionManager")
	public List<Date> getpurchaseOrderDatefromRawPurchase(Date fDate, Date tDate)
			throws DemoException {
		List<Date> pod = null;
		Query q = null;
		try {
			q = entitymanager
					.createQuery("select orderDate from RawPurchase where orderDate between ? and ? and (status=? or status=?)");
			q.setParameter(1, fDate);
			q.setParameter(2, tDate);
			q.setParameter(3, "delivered");
			q.setParameter(4, "inserted");
			pod = q.getResultList();
			logger.info("raw purchase---------->>>" + pod.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return pod;
	}

	@Transactional(value = "transactionManager")
	public List<Transaction> getTransactionExpense(Date fDate, Date tDate)
			throws DemoException {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<Transaction> trans = null;
		try {
			q = entitymanager
					.createQuery("from Transaction where  (status=? or status='debited') and transactionStatus=? and client_ID=?");

			q.setParameter(1, "inserted");
			q.setParameter(2, "debited");
			q.setParameter(3, clientID);
			trans = q.getResultList();
			logger.info("size--------->>>" + trans.size());
		} catch (Exception e) {
		} finally {

		}
		return trans;
	}

	@Transactional(value = "transactionManager")
	public List<AccPayableLiability> getAccountPayableInfo(int id)
			throws DemoException {
		Query q = null;
		List<AccPayableLiability> apl = null;
		try {
			q = entitymanager.createQuery("from AccPayableLiability where transaction_id=? and status=?");
			q.setParameter(1, id);
			q.setParameter(2, "inserted");
			apl = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
		}
		return apl;
	}

	/* Payroll Liability */

	@Transactional(value = "transactionManager")
	public List<Payroll> getPayrollDate(String payrollno) throws DemoException {
		Query q = null;
		List<Payroll> pay = null;
		try {
			q = entitymanager
					.createQuery("from Payroll where payrollNumber=? and status=?");
			q.setParameter(1, payrollno);
			logger.info("date--------->>>" + payrollno);
			q.setParameter(2, "inserted");
			pay = q.getResultList();
			logger.info("payroll size--------->>>>" + pay.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return pay;
	}

	@Transactional(value = "transactionManager")
	public List<String> getpayrollDates(Date fDate, Date tDate)
			throws DemoException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Query q = null;
		List<String> payDate = null;
		try {
			q = entitymanager
					.createQuery("select payrollNumber from Payroll where status=?");
			q.setParameter(1, "inserted");
			payDate = q.getResultList();
			logger.info("pay dates" + payDate);
			logger.info("pay size---------->>>>" + payDate.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return payDate;
	}

	@Transactional(value = "transactionManager")
	public List<Date> commision(Date fDate, Date tDate) throws DemoException {
		List<Date> commision = null;
		Query q = null;
		try {
			q = entitymanager
					.createQuery(" from RawPurchase where date between ? and ? and status=?");
			q.setParameter(1, fDate);
			q.setParameter(2, tDate);
			q.setParameter(3, "offer");

			commision = q.getResultList();
			logger.info("commission---------->" + commision.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return commision;
	}


	@Transactional(value = "transactionManager")
	public List<I0022> getInvicesData(int sid) throws DemoException {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0022> iList = null;
		try {
			q = entitymanager.createQuery("from I0022 where sales_ID=? and client_ID=?");
			q.setParameter(1, sid);
			q.setParameter(2, clientID);
			iList = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return iList;
	}

	@Transactional(value = "transactionManager")
	public List<I0023> getInvoicePaymentSalesData(int invoiceId)
			throws DemoException {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0023> ipList = null;
		try {
			q = entitymanager
					.createQuery("from I0023 where invoice_ID=? and status=? and client_ID=?");
			q.setParameter(1, invoiceId);
			q.setParameter(2, "paid");
			q.setParameter(3, clientID);
			ipList = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return ipList;
	}

	/* siva 23_2_15 */

	@Transactional(value = "transactionManager")
	public List<I0022> getInvicesPurchaseData(int pid) throws DemoException {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0022> iList = null;
		try {
			q = entitymanager
					.createQuery("from I0022 where purchase_ID=? and status='purchase Invoice' and client_ID=?");
			q.setParameter(1, pid);
			q.setParameter(2, clientID);
			iList = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return iList;
	}

	@Transactional(value = "transactionManager")
	public List<I0023> getInvoicePaymentPurchaseData(int invoiceId)
			throws DemoException {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0023> ipList = null;
		try {
			q = entitymanager
					.createQuery("from I0023 where invoice_ID=? and status=? and client_ID=?");
			q.setParameter(1, invoiceId);
			q.setParameter(2, "paid");
			q.setParameter(3, clientID);
			ipList = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return ipList;
	}

	@Transactional(value = "transactionManager")
	public String getInvoicePaymentPurchaseData1(int invoiceId)
			throws DemoException {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0023> ipList = null;
		String cName = "";
		try {
			logger.info("invoice id" + invoiceId);
			ipList = getInvoicePaymentPurchaseData(invoiceId);
			logger.info("invoice id size " + ipList.size());
			if (ipList.size() > 0) {
				cName = ipList.get(0).getI0022().getI0015().getI0016s().get(0)
						.getI0031().getI0025().getVendorPhoneNumber();
				logger.info("cname -- " + cName);
			} else {
				q = entitymanager
						.createQuery("from I0023 where invoice_ID=? and status=? and client_ID=?");
				q.setParameter(1, invoiceId);
				q.setParameter(2, "pending");
				q.setParameter(3, clientID);
				ipList = q.getResultList();
				if (ipList.size() > 0) {
					cName = ipList.get(0).getI0022().getI0015().getI0016s()
							.get(0).getI0031().getI0025()
							.getVendorPhoneNumber();
					logger.info("cname -1- " + cName);
				}
			}
			logger.info("out if con cname");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return cName;
	}

	@Transactional(value = "transactionManager")
	public List<I0023> getInvoicePaymentPurchaseData11(int invoiceId)
			throws DemoException {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0023> ipList = null;
		try {
			q = entitymanager
					.createQuery("from I0023 where invoice_ID=? and status=? and client_ID=?");
			q.setParameter(1, invoiceId);
			q.setParameter(2, "Pending");
			q.setParameter(3, clientID);
			ipList = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return ipList;
	}

	@Transactional(value = "transactionManager")
	public String getInvoicePaymentPurchaseData12(int invoiceId)
			throws DemoException {
		Query q = null;
		List<I0022> ipList = null;
		String cName = "";
		try {
			ipList = getInvicesPurchaseData(invoiceId);
			if (ipList.size() > 0) {
				cName = ipList.get(0).getI0015().getI0016s().get(0).getI0031()
						.getI0025().getVendorPhoneNumber();
				logger.info("cname:" + cName);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return cName;
	}

	@Transactional(value = "transactionManager")
	public List<SalesReturn> quicksalereturn(String ordernumber)
			throws DemoException {
		List<SalesReturn> res = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("Inside quicksalereturn" + ordernumber);

			Query q = entitymanager
					.createQuery("from SalesReturn where salesOrderNumber=? and client_ID=?");
			q.setParameter(1, ordernumber);
			q.setParameter(2, clientID);
			res = (List<SalesReturn>) q.getResultList();
			logger.info("Result Size" + res.size());

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return res;
	}

	@Override
	public List<SalesReturn> normalsalereturn(int Sales_ID)
			throws DemoException {
		List<SalesReturn> res = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("Inside normalsalereturn" + Sales_ID);

			Query q = entitymanager
					.createQuery("from SalesReturn where sales_ID=? and client_ID=?");
			q.setParameter(1, Sales_ID);
			q.setParameter(2, clientID);
			res = (List<SalesReturn>) q.getResultList();
			logger.info("Result Size" + res.size());

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return res;
	}

	@Override
	public List<PurchaseReturn> purchasereturn(int p_ID) throws DemoException {

		List<PurchaseReturn> res = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("Inside purchasereturn" + p_ID);

			Query q = entitymanager
					.createQuery("from PurchaseReturn where purchase_id=? and client_ID=?");
			q.setParameter(1, p_ID);
			q.setParameter(2, clientID);
			res = (List<PurchaseReturn>) q.getResultList();
			logger.info("Result Size" + res.size());

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return res;

	}

	@Transactional(value = "transactionManager")
	public List<I0023> getInvoicePaymentPurchaseData2(int invoiceId)
			throws DemoException {
		Query q = null;
		List<I0023> ipList = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager
					.createQuery("from I0023 where invoice_ID=? and status=? and client_ID=?");
			q.setParameter(1, invoiceId);
			q.setParameter(2, "paid");
			q.setParameter(3, clientID);
			ipList = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return ipList;
	}

	@Override
	public List<PurchaseReturn> getamount(int j) throws DemoException {
		List<PurchaseReturn> res = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("Inside purchasereturn" + j);

			Query q = entitymanager
					.createQuery("from PurchaseReturn where purchase_id=? and client_ID=?");
			q.setParameter(1, j);
			q.setParameter(2, clientID);
			res = (List<PurchaseReturn>) q.getResultList();
			logger.info("Result Size" + res.size());

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return res;

	}

}
