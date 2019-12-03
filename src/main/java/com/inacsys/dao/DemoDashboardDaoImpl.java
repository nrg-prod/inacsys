package com.inacsys.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.context.FacesContext;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.managedBean.QuickSaleMB;
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
import com.inacsys.shared.I0020;
import com.inacsys.shared.I0021;
import com.inacsys.shared.I0022;
import com.inacsys.shared.I0023;
import com.inacsys.shared.I0025;
import com.inacsys.shared.I0026;
import com.inacsys.shared.I0027;
import com.inacsys.shared.I0028;
import com.inacsys.shared.I0031;
import com.inacsys.shared.I0032;
import com.inacsys.shared.I0033;
import com.inacsys.shared.Login;
import com.inacsys.util.GenerateEmployee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class DemoDashboardDaoImpl implements DemoDashboardDao {

	private static final Session HibernateUtil = null;

	final Logger logger = LoggerFactory.getLogger(DemoDashboardDaoImpl.class);

	@PersistenceContext(unitName = "inacsys-pu")
	private EntityManager entitymanager;

	@Transactional(value = "transactionManager")
	public int getsalesQuantityOfMonth(Date fDate, Date tDate)
			throws DemoException {
		Query q = null;
		int quantity = 0;
		List<I0021> sale = null;
		List<I0019> bar = null;
		try {
			sale = getMonthSale(fDate, tDate);
			if (sale.size() > 0) {
				for (int i = 0; i < sale.size(); i++) {
					bar = getSalesQuantity(sale.get(i).getSales_ID());
					if (bar.size() > 0) {
						quantity = quantity + bar.size();
					}
				}
				logger.debug("q>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + quantity);
				return quantity;
			} else {
				logger.debug("No data in sales>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				return 0;
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return 0;
		} finally {

		}

	}

	@Transactional(value = "transactionManager")
	public List<I0021> getMonthSale(Date fDate, Date tDate)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		List<I0021> sale = null;
		try {
			q = entitymanager
					.createQuery("from I0021 where salesOrderDate between ? and ? and client_ID=?");
			q.setParameter(1, fDate);
			q.setParameter(2, tDate);
			q.setParameter(3, clientID);
			sale = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return sale;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> getSalesQuantity(int id) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		List<I0019> barcode = null;
		try {
			q = entitymanager
					.createQuery("from I0019 where sales_ID=? and status='Solded' and client_ID=? ");
			q.setParameter(1, id);
			q.setParameter(2, clientID);
			barcode = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return barcode;
	}


	@Transactional(value = "transactionManager")
	public List<I0021> getMonthSaleAmount(Date fDate, Date tDate)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		List<I0021> sale = null;
		try {
			q = entitymanager
					.createQuery("from I0021 where salesOrderDate between ? and ? and status2='paid' and status!='cancelled' and status!='delete' and client_ID=? ");
			q.setParameter(1, fDate);
			q.setParameter(2, tDate);
			q.setParameter(3, clientID);
			sale = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return sale;
	}

	@Transactional(value = "transactionManager")
	public float getSalesAmount(Date fDate, Date tDate) throws DemoException {
		String f = "0";
		List<I0021> sale = null;
		try {
			sale = getMonthSaleAmount(fDate, tDate);
			if (sale.size() > 0) {
				for (int i = 0; i < sale.size(); i++) {
					f = ""
							+ (new BigDecimal(f).add(new BigDecimal(sale.get(i)
									.getCrossTotal())));
				}
			} else {
				logger.debug("No data in Sale>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return Float.parseFloat(f);
	}

	@Transactional(value = "transactionManager")
	public List<I0015> getpurchaseorderData(Date fDate, Date tDate)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		List<I0015> purchase = null;
		try {
			q = entitymanager
					.createQuery("from I0015 where orderDate between ? and ? and status!='cancelled' and status!='delete' and client_ID=? ");
			q.setParameter(1, fDate);
			q.setParameter(2, tDate);
			q.setParameter(3, clientID);
			purchase = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return purchase;
	}

	@Transactional(value = "transactionManager")
	public float getpurchaseAmount(Date fDate, Date tDate) throws DemoException {
		String amount = "0";
		List<I0015> purchase = null;
		List<I0016> hPurchase = null;
		try {
			purchase = getpurchaseorderData(fDate, tDate);
			if (purchase.size() > 0) {
				for (int i = 0; i < purchase.size(); i++) {
					hPurchase = getpurchasePay(purchase.get(i).getPurchase_ID());
					if (hPurchase.size() > 0) {
						amount = ""
								+ (new BigDecimal(amount).add(new BigDecimal(
										hPurchase.get(0).getI0015()
												.getTotalPrice())));
					} else {
						logger.debug("No data for paid>>>>>>>>>>>>>>>>>>>>");
					}
				}

			} else {
				logger.debug("No data in purchase>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return Float.parseFloat(amount);
	}

	@Transactional(value = "transactionManager")
	public List<I0016> getpurchasePay(int id) throws DemoException {
		Query q = null;
		List<I0016> purchase = null;
		try {
			q = entitymanager
					.createQuery("from I0016 where purchase_ID=? and status2='paid'");
			q.setParameter(1, id);
			purchase = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return purchase;
	}

	@Transactional(value = "transactionManager")
	public int getStockinQuantity(Date fDate, Date tDate) throws DemoException {
		int qty = 0;
		List<I0017> stock = null;
		List<I0018> batch = null;
		List<I0019> barcode = null;
		try {
			stock = getStockIn(fDate, tDate);
			if (stock.size() > 0) {
				for (int i = 0; i < stock.size(); i++) {
					batch = getBatchDatas(stock.get(i).getStock_ID());
					if (batch.size() > 0) {
						barcode = getStockbarcode(batch.get(0).getBatch_ID());
						qty = qty + barcode.size();
					} else {
						logger.debug("No data in batch>>>>>>>>>>>>>>>>>>>>>");
					}
				}
			} else {
				logger.debug("No data in stock");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		logger.debug("q>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + qty);
		return qty;
	}

	@Transactional(value = "transactionManager")
	public int getStockOutQuantity(Date fDate, Date tDate) throws DemoException {
		int qty = 0;
		List<I0017> stock = null;
		List<I0018> batch = null;
		List<I0019> barcode = null;
		try {
			stock = getStockIn(fDate, tDate);
			if (stock.size() > 0) {
				for (int i = 0; i < stock.size(); i++) {
					batch = getBatchDatas(stock.get(i).getStock_ID());
					if (batch.size() > 0) {
						barcode = getStockoutbarcode(batch.get(0).getBatch_ID());
						qty = qty + barcode.size();
					} else {
						System.out
								.println("No data in batch>>>>>>>>>>>>>>>>>>>>>");
					}
				}
			} else {
				logger.debug("No data in stock");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		logger.debug("q>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + qty);
		return qty;
	}

	@Transactional(value = "transactionManager")
	public List<I0017> getStockIn(Date fDate, Date tDate) throws DemoException {
		Query q = null;
		List<I0017> stock = null;
		try {
			q = entitymanager
					.createQuery("from I0017 where stockInDate between ? and ?");
			q.setParameter(1, fDate);
			q.setParameter(2, tDate);
			stock = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return stock;
	}

	@Transactional(value = "transactionManager")
	public List<I0018> getBatchDatas(int id) throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0018> batch = null;
		try {
			q = entitymanager
					.createQuery("from I0018 where stock_ID=? and status='insert' and client_ID=? ");
			q.setParameter(1, id);
			q.setParameter(2, clientID);
			batch = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return batch;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> getStockbarcode(int id) throws DemoException {
		Query q = null;
		List<I0019> barcode = null;
		try {
			q = entitymanager
					.createQuery("from I0019 where batch_ID=? and status='barcode genterated'");
			q.setParameter(1, id);
			barcode = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return barcode;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> getStockoutbarcode(int id) throws DemoException {
		Query q = null;
		List<I0019> barcode = null;
		try {
			q = entitymanager
					.createQuery("from I0019 where batch_ID=? and status!='barcode genterated' and status!='damaged'");
			q.setParameter(1, id);
			barcode = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return barcode;
	}

	@Transactional(value = "transactionManager")
	public int getsalesInvoice(Date fDate, Date tDate) throws DemoException {
		int qty = 0;
		List<I0022> sales = null;
		try {
			sales = getSalesIn(fDate, tDate);
			if (sales.size() > 0) {
				qty = sales.size();
			} else {
				System.out
						.println("No data in sales invoice>>>>>>>>>>>>>>>>>>>>>");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return qty;
	}

	@Transactional(value = "transactionManager")
	public int getpurchaseInvoice(Date fDate, Date tDate) throws DemoException {
		int qty = 0;
		List<I0022> purchase = null;
		try {
			purchase = getPurchaseIn(fDate, tDate);
			if (purchase.size() > 0) {
				qty = purchase.size();
			} else {
				System.out
						.println("No data in purchase invoice>>>>>>>>>>>>>>>>>>>>>");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return qty;
	}

	@Transactional(value = "transactionManager")
	public List<I0022> getSalesIn(Date fDate, Date tDate) throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0022> sales = null;
		try {
			q = entitymanager
					.createQuery("from I0022 where invoiceDate between ? and ? and status=? and client_ID=? ");
			q.setParameter(1, fDate);
			q.setParameter(2, tDate);
			q.setParameter(3, "Sales Invoice");
			q.setParameter(4, clientID);
			sales = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return sales;
	}

	@Transactional(value = "transactionManager")
	public List<I0022> getPurchaseIn(Date fDate, Date tDate)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		List<I0022> sales = null;
		try {
			q = entitymanager
					.createQuery("from I0022 where invoiceDate between ? and ? and status=? and client_ID=? ");
			q.setParameter(1, fDate);
			q.setParameter(2, tDate);
			q.setParameter(3, "purchase Invoice");
			q.setParameter(4, clientID);
			sales = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return sales;
	}

}