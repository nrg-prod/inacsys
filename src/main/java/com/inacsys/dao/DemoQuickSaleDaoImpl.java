package com.inacsys.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.context.FacesContext;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.exolab.castor.types.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
import com.inacsys.shared.Client;
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
import com.inacsys.shared.SalesRecord;
import com.inacsys.shared.SalesReturn;
import com.inacsys.shared.UserCreate;
import com.inacsys.util.CurrencyConverter;
import com.inacsys.util.GenerateEmployee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*import scala.annotation.meta.getter;*/

//import scala.annotation.meta.getter;

/**
 * This Java Class will communicate with Database
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 * 
 */
@Repository
@Singleton
public class DemoQuickSaleDaoImpl implements DemoQuickSaleDao {
	final Logger logger = LoggerFactory.getLogger(DemoQuickSaleDaoImpl.class);

	@PersistenceContext(unitName = "inacsys-pu")
	private EntityManager entitymanager;

	@Autowired
	DemoDao dao;

	/* Sivaranjini 8/1/15 */

	@Transactional(value = "transactionManager")
	public List<String> getProductName() throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<String> product = null;
		try {

			q = entitymanager.createQuery("select productName from I0018 where client_ID=? and status='insert'");
			q.setParameter(1, clientID);
			product = (List<String>) q.getResultList();
			HashSet<String> hashList=new HashSet<String>(product);
			product.clear();
			product.addAll(hashList);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return product;
	}

	@Transactional(value = "transactionManager")
	public String getpurchaseInfo(PurchaseOrder p) throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0018> batch = null;
		List<I0017> stock = null;
		List<I0016> joinPurchase = null;
		List<I0031> joinProduct = null;
		try {
			logger.debug("product name------------>" + p.getBatchProductName());
			Query q2 = null;
			q = entitymanager
					.createQuery("from I0018 where productName=? and (status='closed' or status='insert') and client_ID=? ");
			logger.debug("1");
			q.setParameter(1, p.getBatchProductName());
			q.setParameter(2, clientID);
			List<I0018> batchProduct = (List<I0018>) q.getResultList();
			int id = 0;
			if (batchProduct.size() > 0) {
				int i = 0;

				q2 = entitymanager
						.createQuery("from I0001 where productName=?  and client_ID=?");
				logger.debug("1");
				q2.setParameter(1, p.getBatchProductName());
				q2.setParameter(2, clientID);
				List<I0001> batchProduct1 = (List<I0001>) q2.getResultList();

				logger.debug("batch size====>" + batchProduct.size());
				id = batchProduct.get(i).getBatch_ID();
				logger.debug("innnnnnnnnnnnn" + id);
				p.setProduct_ID(batchProduct.get(i).getI0017().getI0015()
						.getI0016s().get(i).getI0031().getI0001()
						.getProduct_ID());

				p.setSellingPrice(batchProduct1.get(i).getSellingPrice());
				p.setUnit(batchProduct1.get(i).getUnit());

				logger.debug("Selling Price====>" + p.sellingPrice);
				p.setMarginPrice((batchProduct.get(i).getI0017().getI0015()
						.getI0016s().get(i).getI0031().getI0001()
						.getMarginPrice()));

				logger.debug("iddddddddd" + id);
				p.setBarcode1(batchProduct1.get(i).getBarCode());
			}

		} catch (Exception e) {

		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public List<I0018> getBatch(String productName) throws DemoException {
		Query q = null;
		List<I0018> batch = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager.createQuery("from I0018 where productName=? and client_ID=?");
			q.setParameter(1, productName);
			q.setParameter(2, clientID);
			batch = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return batch;
	}

	@Transactional(value = "transactionManager")
	public List<I0017> getStock(int id) throws DemoException {
		Query q = null;
		List<I0017> stock = null;
		try {
			q = entitymanager.createQuery("from I0017 where stock_ID=?");
			q.setParameter(1, id);
			stock = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return stock;
	}

	@Transactional(value = "transactionManager")
	public List<I0016> getpurchaseJoin(int id) throws DemoException {
		Query q = null;
		List<I0016> purchase = null;
		try {
			q = entitymanager.createQuery("from I0016 where purchase_ID=?");
			q.setParameter(1, id);
			purchase = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return purchase;
	}

	@Transactional(value = "transactionManager")
	public List<I0031> getproductJoin(int id) throws DemoException {
		Query q = null;
		List<I0031> product = null;
		try {
			q = entitymanager.createQuery("from I0031 where has_vendor_ID=?");
			q.setParameter(1, id);
			product = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return product;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> getbarcode(int id) throws DemoException {
		Query q = null;
		List<I0019> barcode = null;
		try {
			q = entitymanager
					.createQuery("from I0019 where status=? and batch_ID=?");
			q.setParameter(1, "barcode genterated");
			q.setParameter(2, id);
			barcode = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return barcode;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> getBarCodeData(String productName) throws DemoException {
		Query q = null;
		List<I0018> batch = null;
		List<I0019> barcode = null;
		try {
			batch = getBatch(productName);
			if (batch.size() > 0) {
				barcode = getbarcode(batch.get(0).getBatch_ID());
			} else {
				logger.debug("No data in batch>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return barcode;
	}

	@Transactional(value = "transactionManager")
	public List<I0021> getSalesOrder() throws DemoException {
		Query q = null;
		List<I0021> salesOrder = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager.createQuery("from I0021 where client_ID=?");
			 q.setParameter(1, clientID);
			salesOrder = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return salesOrder;
	}

	@Transactional(value = "transactionManager")
	public String saveSales(PurchaseOrder purchaseOrder) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.debug("inside salesOrder4::::::::::::::::::");
		logger.debug("product name:::::::::::::::"
				+ purchaseOrder.getBatchProductName());
		logger.debug("inside dao1");
		Query q = null, q2 = null;
		q = entitymanager
				.createQuery("from I0018 where productName=? and status=? and client_ID=?");
		logger.debug("1");
		q.setParameter(1, purchaseOrder.getBatchProductName());
		q.setParameter(2, "insert");
		 q.setParameter(3, clientID);
		/* q.setParameter(2, "closed"); */
		List<I0018> batchProduct = (List<I0018>) q.getResultList();
		int id = 0;
		if (batchProduct.size() > 0) {
			int i = 0;

			q2 = entitymanager.createQuery("from I0001 where productName=?  and client_ID=?");
			logger.debug("1");
			q2.setParameter(1, purchaseOrder.getBatchProductName());
			q2.setParameter(2, clientID);
			/* q.setParameter(2, "closed"); */
			List<I0001> batchProduct1 = (List<I0001>) q2.getResultList();

			logger.debug("batch size====>" + batchProduct.size());
			id = batchProduct.get(i).getBatch_ID();
			logger.debug("innnnnnnnnnnnn" + id);
			purchaseOrder.setProduct_ID(batchProduct.get(i).getI0017()
					.getI0015().getI0016s().get(i).getI0031().getI0001()
					.getProduct_ID());

			purchaseOrder.setSellingPrice(batchProduct1.get(i)
					.getSellingPrice());

			logger.debug("Selling Price====>" + purchaseOrder.sellingPrice);
			purchaseOrder.setMarginPrice((batchProduct.get(i).getI0017()
					.getI0015().getI0016s().get(i).getI0031().getI0001()
					.getMarginPrice()));

			logger.debug("ID ------------------------->" + id);

		}
		if (batchProduct.size() == 0) {
			throw new DemoException("*This Product Not In Stock");
		}
		logger.debug("iddddddddd" + id);
		float quant = Integer.parseInt(purchaseOrder.getQuantity());
		logger.debug("inside dao2");
		Query q1 = null;
		q1 = entitymanager
				.createQuery("from I0019 where batch_ID=? and status=?");
		logger.debug("1");
		q1.setParameter(1, id);
		logger.debug("2");
		q1.setParameter(2, "barcode genterated");
		logger.debug("3");
		List<I0019> batchProduct1 = (List<I0019>) q1.getResultList();
		int barcodeID = 0;
		int count = 0;
		String stockquan = "";
		if (batchProduct1.size() > 0) {
			int i = 0;
			stockquan = batchProduct1.get(0).getQuantity();
			logger.debug("inside if::::::::4");
			barcodeID = batchProduct1.get(i).getBar_code_ID();
			for (I0019 b : batchProduct1) {
				count++;
			}
			logger.debug("count______________----------------" + count);

		} else {
			I0018 batchClose = entitymanager.find(I0018.class, id);
			batchClose.setStatus("closed");
		}

		if (count < quant) {

			throw new DemoException("The stock only has " + count
					+ " product left. Please contact warehouse.");
		}
		logger.debug("id of batch is:::" + id);
		purchaseOrder.setBatchID(id);
		logger.debug("out");
		return null;

	}

	@Transactional(value = "transactionManager")
	public String qucikSalesConform(PurchaseOrder purchaseOrder)
			throws DemoException {
		try {
			System.out.println("--------------------inside qucikSalesConform----------------------");
			System.out.println("inside product name----------------->"
					+ purchaseOrder.getBatchProductName());
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			System.out.println("sales id======="+purchaseOrder.getSalesId());
			Query q = null;
			String stockQuan = "";
			String RemQuan = "0";
			List<I0019> list19 = null;
			q = entitymanager
					.createQuery("from I0018 where productName=? and status=? and client_ID=?");
			System.out.println("1");
			q.setParameter(1, purchaseOrder.getBatchProductName());
			q.setParameter(2, "insert");
			q.setParameter(3, clientID);
			List<I0018> batchProduct = (List<I0018>) q.getResultList();
			int id = 0;
			if (batchProduct.size() > 0) {
				int i = 0;
				id = batchProduct.get(i).getBatch_ID();
				System.out.println("id--------------->" + id);
			}
			System.out.println("-----------------inside dao2---------------------");
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0019 where batch_ID=? and status=?");
			System.out.println("1");
			q1.setParameter(1, id);
			System.out.println("2");
			q1.setParameter(2, "barcode genterated");
			System.out.println("3");
			List<I0019> batchProduct1 = (List<I0019>) q1.getResultList();
			int barcodeID = 0;
			if (batchProduct1.size() > 0) {
				int i = 0;
				barcodeID = batchProduct1.get(i).getBar_code_ID();
				stockQuan = batchProduct1.get(0).getQuantity();
			}

			System.out.println("barcode ID---------------->" + barcodeID);
			System.out.println("salesID------------------->"
					+ purchaseOrder.getSalesId());
			System.out.println("inside dao RemQuan " + RemQuan);
			RemQuan = ""
					+ (new BigDecimal(stockQuan)).subtract(new BigDecimal(
							purchaseOrder.getQuantity()));
			System.out.println("inside dao method");
			System.out.println("inside dao QUAN " + purchaseOrder.getQuantity());
			System.out.println("inside dao RemQuan " + RemQuan);

			I0019 barcode1 = entitymanager.find(I0019.class, barcodeID);
			barcode1.setQuantity(RemQuan);
			/*
			 * barcode1.setI0021(entitymanager.find(I0021.class,
			 * purchaseOrder.getSalesId() + 1));
			 */
			entitymanager.merge(barcode1);

			System.out.println("in dao --<< discountztype : "
					+ purchaseOrder.getDiscountzType());
			System.out.println("in dao --<< discountz : "
					+ purchaseOrder.getDiscountz());
			System.out.println("in dao --<< discountzamount : "
					+ purchaseOrder.getDiscountzAmount());
			System.out.println("in dao --<< sales id : "
					+ purchaseOrder.getSalesId());
			SalesRecord srec = new SalesRecord();
			srec.setReturnQuantity("0");
			srec.setReturnStatus("no");
			srec.setSoldQuantity(purchaseOrder.getQuantity());
			srec.setSoldStatus("delivered");
			srec.setStatus("quicksales");
			srec.setDiscountType(purchaseOrder.getDiscountzType());
			srec.setDiscount(purchaseOrder.getDiscountz());
			srec.setDiscountAmount(purchaseOrder.discountzAmount);
			srec.setI0021(entitymanager.find(I0021.class,
					purchaseOrder.getSalesId()));
			srec.setI0019(entitymanager.find(I0019.class, barcodeID));
			entitymanager.persist(srec);

		}catch(Exception e){
			e.printStackTrace();
		}finally {

		}

		return null;
	}

	public String clientCurrency(String clientID){
		System.out.println("client id "+clientID);
		Query v=null;
		v=entitymanager.createQuery("from Client where clientNumber=? and status='Active'");
		v.setParameter(1, clientID);
		List<Client> client=(List<Client>)v.getResultList();
		return client.get(0).getBaseCurrency();
	}

	@Transactional(value = "transactionManager")
	public String qucikSalesConform2(PurchaseOrder purchaseOrder)
			throws DemoException {

		entitymanager.clear();
		I0021 sales1 = entitymanager.find(I0021.class,
				purchaseOrder.getSalesId() + 1);
		logger.debug("1");
		sales1.setTotalNumberOfCount((purchaseOrder.getQuantity()));
		logger.debug("2");
		sales1.setCrossTotal(purchaseOrder.getCrosstotal1());

		entitymanager.merge(sales1);
		logger.debug("10");

		return "";
	}

	public List<String> customername(Commission commission)
			throws DemoException {
		logger.debug("inside-------------->" + commission.getCustomername());
		List<I0032> value = null;
		List<String> name = new ArrayList<String>();
		Query q = null;
		String a = "";
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");

		try {
			if (commission.getCustomername().equalsIgnoreCase("Free Lancer")) {
				q = entitymanager
						.createQuery("from I0032 where categoryName=? and client_ID=?");
				q.setParameter(1, commission.getCustomername());
				q.setParameter(2, clientID);
			} else if (commission.getCustomername()
					.equalsIgnoreCase("Customer")) {
				q = entitymanager
						.createQuery("from I0032 where categoryName!='Free Lancer' and client_ID=?");
				q.setParameter(1, clientID);

			} else {
				throw new DemoException("No data found");
			}

			value = q.getResultList();
			logger.debug("inside query");
			if (value.size() > 0) {
				logger.debug("inside the if condition----------------->"
						+ value);
				for (I0032 i0032 : value) {
					if (commission.getCustomername().equalsIgnoreCase(
							"Free Lancer")) {
						name.add(i0032.getFreelancerName());
						logger.debug("catory-------->"
								+ i0032.getFreelancerName());
					} else {
						name.add(i0032.getCustomerName());
						logger.debug("catory-------->"
								+ i0032.getCustomerName());
					}
				}
				commission.setList(name);
			} else {
				logger.debug("inside the if condition1");
			}

		} finally {

		}
		return null;
	}

	public String commissionview(Commission commission) throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");

		List<Commission> totallist = new ArrayList<Commission>();
		List<I0021> result = null;
		logger.debug("reach dao-------1");
		try {
			q = entitymanager
					.createQuery("from I0021 where customerName=? and salesOrderDate between ? and ? and client_ID=?");
			logger.debug("reach dao------->" + commission.getCategory());
			q.setParameter(1, commission.getCategory());
			logger.debug("reach dao------->" + commission.getFromdate());
			q.setParameter(2, commission.getFromdate());
			logger.debug("reach dao------->" + commission.getTodate());
			q.setParameter(3, commission.getTodate());
			 q.setParameter(4, clientID);
			result = q.getResultList();
			logger.debug("reach dao------->" + result.size());
			if (result.size() > 0) {
				for (I0021 i0021 : result) {
					Commission addvalue = new Commission();
					addvalue.setCname(i0021.getCustomerName());
					addvalue.setInvoiceno(i0021.getSalesOrderNumber());
					addvalue.setOrderdate("" + i0021.getSalesOrderDate());
					totallist.add(addvalue);
				}
				commission.setTotallist(totallist);
				logger.debug("list value-->" + commission.getTotallist().size());
			} else {
				throw new DemoException("No Data Found");
			}
		} finally {

		}
		return null;
	}


	public ArrayList<I0032> catogerycustomer(ArrayList<I0032> buyername,
			PurchaseOrder purchaseOrder) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		Query q = null;
		List<I0032> value = null;
		List<String> name = new ArrayList<String>();
		logger.debug("name=--->" + purchaseOrder.category);
		try {
			q = entitymanager
					.createQuery("from I0032 where categoryName=? and status='in' and client_ID=? and user_ID=? and approvalStatus='Approved'");
			q.setParameter(1, purchaseOrder.getCategory());
			q.setParameter(2, clientID);
			q.setParameter(3, userID);
			value = q.getResultList();
			if (value.size() > 0) {
				logger.debug("inside the if condition----------------->"
						+ value);
				for (I0032 i0032 : value) {
					if (purchaseOrder.getCategory().equalsIgnoreCase(
							"Free Lancer")) {
						name.add(i0032.getFreelancerName());
						logger.debug("catory-------->"
								+ i0032.getFreelancerName());
					} else {
						name.add(i0032.getCustomerName());
						logger.debug("catory-------->"
								+ i0032.getCustomerName());
					}
				}
			}
			List<String> name1 = new ArrayList<String>();
			HashSet<String> pSet = new HashSet<String>();
			for (String item : name) {
				if (!pSet.contains(item)) {
					name1.add(item);
					pSet.add(item);
				}
			}
			purchaseOrder.setResulfinal1(name1);
			logger.debug("result------>"
					+ purchaseOrder.getResulfinal1().size());
		} finally {

		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public String salesconfirm(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");

		try {
			String stockQuan = "";
			String RemQuan = "0";
			logger.debug("--------------------inside qucikSalesConform----------------------");
			logger.debug("inside product name----------------->"
					+ purchaseOrder.getBatchProductName());
			Query q = null;
			q = entitymanager
					.createQuery("from I0018 where productName=? and status=? and client_ID=?");
			logger.debug("1");
			q.setParameter(1, purchaseOrder.getBatchProductName());
			q.setParameter(2, "insert");
			q.setParameter(3, clientID);
			List<I0018> batchProduct = (List<I0018>) q.getResultList();
			int id = 0;
			if (batchProduct.size() > 0) {
				int i = 0;
				id = batchProduct.get(i).getBatch_ID();
				logger.debug("id--------------->" + id);
			}
			logger.debug("-----------------inside dao2---------------------");
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0019 where batch_ID=? and status=?");
			logger.debug("1");
			q1.setParameter(1, id);
			logger.debug("2");
			q1.setParameter(2, "barcode genterated");
			logger.debug("3");
			List<I0019> batchProduct1 = (List<I0019>) q1.getResultList();
			int barcodeID = 0;
			if (batchProduct1.size() > 0) {
				int i = 0;
				stockQuan = batchProduct1.get(0).getQuantity();
				barcodeID = batchProduct1.get(i).getBar_code_ID();
			}

					RemQuan = ""
					+ (new BigDecimal(stockQuan)).subtract(new BigDecimal(
							purchaseOrder.getQuantity()));
			logger.debug("inside dao QUAN " + purchaseOrder.getQuantity());
			logger.debug("inside dao RemQuan " + RemQuan);

			if (RemQuan.equals("0")) {
				logger.debug("inside if");
				I0019 barcode1 = entitymanager.find(I0019.class, barcodeID);
				barcode1.setStock_In(String.valueOf(RemQuan));
				barcode1.setStock_Out(purchaseOrder.getQuantity());
				barcode1.setRoll_status("Sold");
				barcode1.setQuantity(RemQuan);
				barcode1.setI0021(entitymanager.find(I0021.class,
						purchaseOrder.getSalesId() + 1));
				entitymanager.merge(barcode1);
			} else {
				logger.debug("inside else");
				I0019 barcode1 = entitymanager.find(I0019.class, barcodeID);
				barcode1.setStock_In(String.valueOf(RemQuan));
				barcode1.setQuantity(RemQuan);
				barcode1.setStock_Out(purchaseOrder.getQuantity());
				barcode1.setI0021(entitymanager.find(I0021.class,
						purchaseOrder.getSalesId() + 1));
				entitymanager.merge(barcode1);
			}

			BigDecimal bb = new BigDecimal(purchaseOrder.getQuantity());
			bb = bb.setScale(2, RoundingMode.CEILING);
			SalesRecord srec = new SalesRecord();
			srec.setReturnQuantity("0");
			srec.setReturnStatus("no");
			srec.setSoldQuantity("" + bb);
			srec.setSell_price(purchaseOrder.getPrice());
			srec.setSoldStatus("solded");
			srec.setStatus("salesorder");
			srec.setClient_ID(clientID);
			srec.setDiscountType(purchaseOrder.getDiscountzType());
			srec.setDiscount(purchaseOrder.getDiscountz());
			srec.setDiscountAmount(purchaseOrder.discountzAmount);
			srec.setTotcommAmt(purchaseOrder.getNetAmount());
			srec.setI0021(entitymanager.find(I0021.class,
					purchaseOrder.getSalesId()));
			srec.setI0019(entitymanager.find(I0019.class, barcodeID));
			entitymanager.persist(srec);

		} finally {

		}

		return null;
	}

	

	@Transactional(value = "transactionManager")
	public ArrayList<String> getRollList(String productName,
			ArrayList<String> rollList) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.debug("-------------------inside getRollList dao Impl  Begin--------------"
				+ productName);

		ArrayList<String> rolls = new ArrayList<String>();
		ArrayList<String> rolls1 = new ArrayList<String>();
		Query qry = null;
		qry = entitymanager
				.createQuery("from I0018 where productName=? and status=? and client_ID=?");
		qry.setParameter(1, productName);
		qry.setParameter(2, "insert");
		qry.setParameter(3, clientID);
		List<I0018> resultqry = (List<I0018>) qry.getResultList();
		int batchId = 0;
		if (resultqry.size() > 0) {
			int k = 0;
			batchId = resultqry.get(k).getBatch_ID();

			Query qry1 = null;
			qry1 = entitymanager
					.createQuery("from I0019 where batch_ID=? and roll_status=?");
			qry1.setParameter(1, batchId);
			qry1.setParameter(2, "Insert");
			List<I0019> resultqry1 = (List<I0019>) qry1.getResultList();
			for (I0019 re : resultqry1)

			{

				logger.debug("product name" + re.getRoll_ID());
				logger.debug("Qty" + re.getStock_In());
				rolls.add(re.getRoll_ID());
			}
			rolls1.addAll(rolls);

		} else {
			throw new DemoException("*This productName is not there");
		}

		logger.debug("---------getBatch_ID-----------" + batchId);
		logger.debug("---------Roll Size-----------" + rolls.size());
		logger.debug("---------Roll Size-----------" + rolls1.size());

		logger.debug("-------------------inside getRollList dao Impl End--------------"
				+ rollList);

		return rolls1;
	}

	@Transactional(value = "transactionManager")
	public String qucikSalesRoll(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.debug("homeMBs.get(i).qucikSalesRoll()-123 "
					+ purchaseOrder.getPrice());
			logger.debug("--------------------inside qucikSalesRoll Begin----------------------");

			Query q = null;
			q = entitymanager
					.createQuery("from I0018 where productName=? and status=? and client_ID=? ");
			logger.debug("1");
			q.setParameter(1, purchaseOrder.getBatchProductName());
			q.setParameter(2, "insert");
			q.setParameter(3, clientID);
			List<I0018> batchProduct = (List<I0018>) q.getResultList();
			int id = 0;
			if (batchProduct.size() > 0) {
				int i = 0;
				id = batchProduct.get(i).getBatch_ID();
				logger.debug("id--------------->" + id);
			}
			Query qry1 = null;
			qry1 = entitymanager
					.createQuery("from I0019 where batch_ID=? and status=?");
			qry1.setParameter(1, id);
			qry1.setParameter(2, "barcode genterated");
			List<I0019> result = (List<I0019>) qry1.getResultList();
			float stockin = (float) 0.0;
			int barcodeID = 0;
			int batchID = 0;
			String stockqunt = "";
			if (result.size() > 0) {
				int k = 0;
				batchID = result.get(k).getI0018().getBatch_ID();
				barcodeID = result.get(k).getBar_code_ID();
				stockqunt = result.get(k).getQuantity();

			}

			logger.debug("batchID" + batchID);
			logger.debug("barcodeID" + barcodeID);
			logger.debug("stockqunt" + stockqunt);
			logger.debug("salesID------------------->"
					+ purchaseOrder.getSalesId());
			logger.debug("getQuantity------------------->"
					+ purchaseOrder.getQuantity());

			stockin = Float.parseFloat(stockqunt)
					- Float.parseFloat(purchaseOrder.getQuantity());
			logger.debug("stockindiff---" + stockin);

			if ((stockin == 0.0) || (stockin == 0)) {
				I0019 barcode1 = entitymanager.find(I0019.class, barcodeID);
				barcode1.setQuantity(String.valueOf(stockin));
				barcode1.setI0021(entitymanager.find(I0021.class,
						purchaseOrder.getSalesId() + 1));
				entitymanager.merge(barcode1);
			} else {
				I0019 barcode1 = entitymanager.find(I0019.class, barcodeID);
				barcode1.setQuantity(String.valueOf(stockin));
				barcode1.setI0021(entitymanager.find(I0021.class,
						purchaseOrder.getSalesId() + 1));
				entitymanager.merge(barcode1);
			}

			logger.debug("After I0019");
			logger.debug("homeMBs.get(i).qucikSalesRoll()-1234 "
					+ purchaseOrder.getPrice());
			System.out.println("sales id-------------------"+purchaseOrder.getSalesId());
			SalesRecord srec = new SalesRecord();
			srec.setReturnQuantity("0");
			srec.setReturnStatus("no");
			logger.debug("-----------------s------------------------"
					+ new BigDecimal(purchaseOrder.getQuantity()).setScale(2));
			srec.setSoldQuantity(""
					+ new BigDecimal(purchaseOrder.getQuantity()).setScale(2));
			srec.setSell_price(purchaseOrder.getPrice());
			srec.setSoldStatus("delivered");
			srec.setStatus("quicksales");
			srec.setDiscountType(purchaseOrder.getDiscountzType());
			srec.setDiscount(purchaseOrder.getDiscountz());
			srec.setDiscountAmount(purchaseOrder.discountzAmount);
			srec.setClient_ID(clientID);
			srec.setI0021(entitymanager.find(I0021.class,
					purchaseOrder.getSalesId()));
			srec.setI0019(entitymanager.find(I0019.class, barcodeID));
			entitymanager.persist(srec);
			logger.debug("After SalesRecord");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("inside exception",e);
		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public String updateRollSales(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {

			logger.debug("------------------Inside updateRollSales DAO---------------------"
					+ purchaseOrder.getTotalPrice());
			logger.debug("roll id" + purchaseOrder.getRollID());
			logger.debug("roll id" + purchaseOrder.getProduct_name());
			logger.debug("Quantity" + purchaseOrder.getQuantity());
			logger.debug("order Number------------>"
					+ purchaseOrder.getSalesIdReference());
			Query q = null;
			q = entitymanager
					.createQuery("from I0021 where salesOrderNumber=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getSalesIdReference());
			q.setParameter(2, clientID);
			ArrayList<I0021> result = (ArrayList<I0021>) q.getResultList();
			if (result.size() > 0) {
				int salesid = 0;
				salesid = result.get(0).getSales_ID();
				logger.debug("sales id------------>" + salesid);
				String disctype = "" + result.get(0).getDisctype();
				String discamnt = "" + result.get(0).getDiscamnt();
				String shipcost = "" + result.get(0).getShippingCharge();
				Query qry1 = null;
				qry1 = entitymanager
						.createQuery("from I0019 where roll_ID=? and (roll_status='Insert' or roll_status='Sold')");
				qry1.setParameter(1, purchaseOrder.getRollID());
				List<I0019> res = (List<I0019>) qry1.getResultList();

				int barcodeID = 0;
				int batchID = 0;
				String stockinqunt = "";
				String stockoutqunt = "";
				float temp = (float) 0.0;
				float tempin = (float) 0.0;
				float tempout = (float) 0.0;
				if (res.size() > 0) {
					int k = 0;
					batchID = res.get(k).getI0018().getBatch_ID();
					barcodeID = res.get(k).getBar_code_ID();
					stockinqunt = res.get(k).getQuantity();
					stockoutqunt = res.get(k).getStock_Out();

					logger.debug("batchID--" + batchID + "------barcodeID--"
							+ barcodeID + "----stockinqunt--" + stockinqunt
							+ "---stockoutqunt--" + stockoutqunt);
			

					String oldquantities = "";
					String newquantities = "";
					String finquantities = "";
					String stcquantities = "";
					String outquantities = "";
					oldquantities = purchaseOrder.getQuantity1();
					newquantities = purchaseOrder.getQuantity();

					if (new BigDecimal(oldquantities).compareTo(new BigDecimal(
							newquantities)) == 1) {
						finquantities = ""
								+ (new BigDecimal(oldquantities)
										.subtract(new BigDecimal(newquantities)));
					}
					if (new BigDecimal(oldquantities).compareTo(new BigDecimal(
							newquantities)) == -1) {
						finquantities = ""
								+ (new BigDecimal(newquantities)
										.subtract(new BigDecimal(oldquantities)));
					} else {
						finquantities = ""
								+ (new BigDecimal(oldquantities)
										.subtract(new BigDecimal(newquantities)));
					}
					logger.debug("finquantities" + finquantities);
					logger.debug("oldquantities" + oldquantities);
					logger.debug("newquantities" + newquantities);

					if (new BigDecimal(oldquantities).compareTo(new BigDecimal(
							newquantities)) == 1) {
						stcquantities = ""
								+ (new BigDecimal(stockinqunt)
										.add(new BigDecimal(finquantities)));
						outquantities = ""
								+ (new BigDecimal(stockoutqunt)
										.subtract(new BigDecimal(finquantities)));
					}
					if (new BigDecimal(oldquantities).compareTo(new BigDecimal(
							newquantities)) == -1) {
						stcquantities = ""
								+ (new BigDecimal(stockinqunt)
										.subtract(new BigDecimal(finquantities)));
						outquantities = ""
								+ (new BigDecimal(stockoutqunt)
										.add(new BigDecimal(finquantities)));
					}

					I0019 barcode1 = entitymanager.find(I0019.class, barcodeID);
					barcode1.setStock_In((stcquantities));
					barcode1.setStock_Out((outquantities));
					if (new BigDecimal(purchaseOrder.getRollStockIn())
							.compareTo(new BigDecimal(newquantities)) == 0) {
						barcode1.setRoll_status("Sold");
					}
					entitymanager.merge(barcode1);

					logger.debug("sales id -- " + salesid);
					logger.debug("bar   id -- " + barcodeID);
					logger.debug("soldquan -- " + purchaseOrder.getQuantity1());

					Query q3 = null;
					String saledQuantity = "0";
					q3 = entitymanager
							.createQuery("from SalesRecord where sales_id=? and bar_code_id=? and soldQuantity=?");
					logger.debug("-------------1-------------" + salesid);
					q3.setParameter(1, salesid);
					logger.debug("-------------2------------" + barcodeID);
					q3.setParameter(2, barcodeID);
					logger.debug("-------------3-------------"
							+ purchaseOrder.getQuantity1());
					q3.setParameter(3, purchaseOrder.getQuantity1());
					logger.debug("-------------4-------------");
					List<SalesRecord> salerec = (List<SalesRecord>) q3
							.getResultList();
					logger.debug("salesrec size -- " + salerec.size());
					if (salerec.size() > 0) {
						int salrecID = 0;
						saledQuantity = salerec.get(0).getSoldQuantity();
						salrecID = salerec.get(0).getSalesRecordId();

						SalesRecord rec = entitymanager.find(SalesRecord.class,
								salrecID);
						BigDecimal bd = BigDecimal.valueOf(0);
						bd = new BigDecimal(newquantities);
						bd = bd.setScale(2, RoundingMode.CEILING);
						rec.setSoldQuantity("" + bd);
						logger.debug("~~~~TTTTTTTTT~~~~~"
								+ purchaseOrder.getTotalPrice());
						/* rec.sett */
						rec.setSell_price(purchaseOrder.getTotalPrice());
						entitymanager.merge(rec);

						Query q4 = null;
						q4 = entitymanager
								.createQuery("from SalesRecord where sales_id=?");
						q4.setParameter(1, salesid);
						List<SalesRecord> salerecz = (List<SalesRecord>) q4
								.getResultList();
						if (salerecz.size() > 0) {
							String disAmount = "0";
							String crossAmount = "0";
							String temp1 = "0";
							String quan = "0";
							String totquan = "0";
							String price = "0";
							for (int i = 0; i < salerecz.size(); i++) {
								quan = salerecz.get(i).getSoldQuantity();
	
								price = salerecz.get(i).getSell_price();
								logger.debug("quan" + quan);
								disAmount = salerecz.get(i).getDiscountAmount();
								temp1 = ""
										+ (new BigDecimal(price)
												.multiply(new BigDecimal(quan))
												.subtract(new BigDecimal(
														disAmount)));
								logger.debug("temp total --<<  " + temp1);
								crossAmount = ""
										+ new BigDecimal(crossAmount)
												.add(new BigDecimal(temp1));
								logger.debug("croos total --<<  " + crossAmount);
								totquan = ""
										+ (new BigDecimal(totquan)
												.add(new BigDecimal(quan)));
							}

							logger.debug("type" + disctype);
							logger.debug("amnt" + discamnt);
							logger.debug("ship" + shipcost);

							if (disctype.equalsIgnoreCase("RP")) {
								logger.debug("if -->>");
								crossAmount = ""
										+ new BigDecimal(crossAmount).subtract(
												new BigDecimal(discamnt)).add(
												new BigDecimal(shipcost));
							} else if (disctype.equalsIgnoreCase("%")) {
								logger.debug("else if -->>");
								BigDecimal tempval = BigDecimal.valueOf(0);
								tempval = new BigDecimal(crossAmount).multiply(
										new BigDecimal(discamnt)).divide(
										BigDecimal.valueOf(100));
								crossAmount = ""
										+ new BigDecimal(crossAmount).subtract(
												(tempval)).add(
												new BigDecimal(shipcost));
							} else {
								logger.debug("else -->>");
								crossAmount = ""
										+ new BigDecimal(crossAmount)
												.add(new BigDecimal(shipcost));
							}

							I0021 sal = entitymanager
									.find(I0021.class, salesid);
							sal.setCrossTotal(crossAmount);
							sal.setTotalNumberOfCount(totquan);
							entitymanager.merge(sal);
						} else {
							logger.debug("-----inside else------------------------------->>");
						}

					}

				}
			}

			logger.debug("------------------Inside updateRollSales DAO End---------------------");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return null;
	}

	
	@Transactional(value = "transactionManager")
	public String salesRollconfirm(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.debug("--------------------inside salesRollconfirm Begin----------------------");

			logger.debug("Roll" + purchaseOrder.getRollID());
			logger.debug("Roll" + purchaseOrder.getQuantity());

			Query qry1 = null;
			qry1 = entitymanager
					.createQuery("from I0019 where roll_ID=? and roll_status=? and client_ID=? ");
			qry1.setParameter(1, purchaseOrder.getRollID());
			qry1.setParameter(2, "Insert");
			qry1.setParameter(3, clientID);
			List<I0019> result = (List<I0019>) qry1.getResultList();
			float stockin = (float) 0.0;
			int barcodeID = 0;
			int batchID = 0;
			String stockqunt = "";
			if (result.size() > 0) {
				int k = 0;
				batchID = result.get(k).getI0018().getBatch_ID();
				barcodeID = result.get(k).getBar_code_ID();
				stockqunt = result.get(k).getStock_In();

			}

			logger.debug("batchID" + batchID);
			logger.debug("barcodeID" + barcodeID);
			logger.debug("stockqunt" + stockqunt);
			logger.debug("salesID------------------->"
					+ purchaseOrder.getSalesId());
			logger.debug("getQuantity------------------->"
					+ purchaseOrder.getQuantity());

			stockin = Float.parseFloat(stockqunt)
					- Float.parseFloat(purchaseOrder.getQuantity());
			logger.debug("stockindiff---" + stockin);

			if ((stockin == 0.0) || (stockin == 0)) {
				I0019 barcode1 = entitymanager.find(I0019.class, barcodeID);
				barcode1.setStock_In(String.valueOf(stockin));
				barcode1.setStock_Out(purchaseOrder.getQuantity());
				barcode1.setRoll_status("Sold");
				barcode1.setI0021(entitymanager.find(I0021.class,
						purchaseOrder.getSalesId() + 1));
				entitymanager.merge(barcode1);
			} else {
				I0019 barcode1 = entitymanager.find(I0019.class, barcodeID);
				barcode1.setStock_In(String.valueOf(stockin));
				barcode1.setStock_Out(purchaseOrder.getQuantity());
				barcode1.setI0021(entitymanager.find(I0021.class,
						purchaseOrder.getSalesId() + 1));
				entitymanager.merge(barcode1);
			}

			logger.debug("After I0019");
			BigDecimal qq = new BigDecimal(purchaseOrder.getQuantity());
			qq = qq.setScale(2, RoundingMode.CEILING);
			SalesRecord srec = new SalesRecord();
			srec.setReturnQuantity("0");
			srec.setReturnStatus("no");
			srec.setSoldQuantity("" + qq);
			srec.setSell_price(purchaseOrder.getPrice());
			srec.setSoldStatus("solded");
			srec.setStatus("salesorder");
			srec.setClient_ID(clientID);
			srec.setDiscountType(purchaseOrder.getDiscountzType());
			srec.setDiscount(purchaseOrder.getDiscountz());
			srec.setDiscountAmount(purchaseOrder.discountzAmount);
			srec.setI0021(entitymanager.find(I0021.class,
					purchaseOrder.getSalesId()));
			srec.setI0019(entitymanager.find(I0019.class, barcodeID));
			entitymanager.persist(srec);
			logger.debug("After SalesRecord");

			logger.debug("--------------------inside salesRollconfirm Begin----------------------");

		} catch (Exception e) {
			logger.error("inside exception",e);
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<String> getRollQuanList(String productName,
			ArrayList<String> rollList) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.debug("-------------------inside getRollList dao Impl  Begin--------------"
				+ productName);

		ArrayList<String> rolls = new ArrayList<String>();
		ArrayList<String> rolls1 = new ArrayList<String>();
		Query qry = null;
		qry = entitymanager
				.createQuery("from I0018 where productName=? and status=? and client_ID=? ");
		qry.setParameter(1, productName);
		qry.setParameter(2, "insert");
		qry.setParameter(3, clientID);
		List<I0018> resultqry = (List<I0018>) qry.getResultList();
		int batchId = 0;
		if (resultqry.size() > 0) {
			int k = 0;
			batchId = resultqry.get(k).getBatch_ID();

			Query qry1 = null;
			qry1 = entitymanager
					.createQuery("from I0019 where batch_ID=? and status=?");
			qry1.setParameter(1, batchId);
			qry1.setParameter(2, "barcode genterated");
			List<I0019> resultqry1 = (List<I0019>) qry1.getResultList();
			for (I0019 re : resultqry1)

			{
				rolls.add(re.getQuantity());
	
			}
			rolls1.addAll(rolls);

		} else {
			throw new DemoException("*This productName is not there");
		}

		logger.debug("---------getBatch_ID-----------" + batchId);
		logger.debug("---------Roll Size-----------" + rolls.size());
		logger.debug("---------Roll Size-----------" + rolls1.size());

		logger.debug("-------------------inside getRollList dao Impl End--------------"
				+ rollList);

		return rolls1;
	}

	@Transactional(value = "transactionManager")
	public String updateRollQuantity(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.debug("----------Inside  updateRollQuantity Dao------------");

			logger.debug("----------getRollID------"
					+ purchaseOrder.getRollID());

			Query q = null;
			q = entitymanager
					.createQuery("from I0019 where roll_ID=? and (roll_status='insert' or roll_status='sold') and client_ID=? ");
			q.setParameter(1, purchaseOrder.getRollID());
			q.setParameter(2, clientID);
			List<I0019> result = (List<I0019>) q.getResultList();

			float stockin = (float) 0.0;
			if (result.size() > 0) {
				int k = 0;
				try {
					purchaseOrder.setRollStockIn(Float.parseFloat(result.get(k)
							.getStock_In()));
					logger.debug("----------Inside  updateRollQuantity !!!!!------------"
							+ purchaseOrder.getRollStockIn());
				} catch (NumberFormatException e) {
					purchaseOrder.setRollStockIn(0.0f);
				}
			}

			logger.debug("----------Inside  updateRollQuantity Dao End------------"
					+ purchaseOrder.getRollStockIn());

		} catch (Exception e) {
			logger.error("inside exception",e);
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String salesOrderdelete(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		Query q4 = null;
		Query q5 = null;
		logger.debug("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager.createQuery("from I0021 where sales_ID=?");
		q.setParameter(1, purchaseOrder.getSalesId());
		// q.setParameter(2, "insert");
		logger.debug("inside sales order cancel::::");
		List<I0021> result = (List<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		if (result.size() > 0) {
			logger.debug("1");
			pkid = result.get(i).getSales_ID();
			logger.debug("2---------->" + pkid);
			Query q1 = null;

			I0021 sale = entitymanager.find(I0021.class, pkid);
			sale.setStatus2("");
			sale.setStatus("Sales Order Cancelled");
			entitymanager.merge(sale);

			String saledQuant = "0";
			int barID = 0;

			logger.debug("salesid --<< " + purchaseOrder.getSalesIdReference());
			q4 = entitymanager.createQuery("from SalesRecord where sales_id=?");
			q4.setParameter(1, pkid);
			List<SalesRecord> salerecz = (List<SalesRecord>) q4.getResultList();
			if (salerecz.size() > 0) {
				for (int j = 0; j < salerecz.size(); j++) {
					saledQuant = salerecz.get(j).getSoldQuantity();
					barID = salerecz.get(j).getI0019().getBar_code_ID();

					String stockQuant = "0";
					String newQuant = "0";
					q5 = entitymanager
							.createQuery("from I0019 where bar_code_ID=?");
					q5.setParameter(1, barID);
					List<I0019> batchProduct = (List<I0019>) q5.getResultList();
					String stockinqunt = "";
					float temp = (float) 0.0;
					if (batchProduct.size() > 0) {
						int k = 0;
						stockinqunt = batchProduct.get(k).getQuantity();

						temp = (Float.parseFloat(stockinqunt) + Float
								.parseFloat(saledQuant));

						I0019 barcode = entitymanager.find(I0019.class, barID);
						barcode.setQuantity(String.valueOf(temp));
						barcode.setStock_Out("0");
						barcode.setRoll_status("Insert");
						entitymanager.merge(barcode);

					}

				}
			}

			Query q2 = null;
			q2 = entitymanager.createQuery("from I0022 where sales_ID=?");
			q2.setParameter(1, pkid);
			List<I0022> result1 = (List<I0022>) q2.getResultList();
			int invoiceId = 0;
			if (result1.size() > 0) {
				logger.debug("-------------------This order number hase generated Invoice-----------");
				invoiceId = result1.get(0).getInvoice_ID();
				logger.debug("----------invoiceId------------" + invoiceId);
				Query q3 = null;
				q3 = entitymanager.createQuery("from I0023 where invoice_ID=?");
				q3.setParameter(1, invoiceId);
				List<I0023> result2 = (List<I0023>) q3.getResultList();
				int paymentid = 0;
				if (result2.size() > 0) {
					logger.debug("-------------------This order number hase generated Invoice-----------");
					paymentid = result2.get(0).getPayment_ID();
					logger.debug("----------payment id------------" + paymentid);
					I0023 i0023 = entitymanager.find(I0023.class, paymentid);
					entitymanager.remove(i0023);
				}
				I0022 i0022 = entitymanager.find(I0022.class, invoiceId);
				entitymanager.remove(i0022);
			}


		} else {
			throw new DemoException("*Please check the sales order number");
		}

		return null;

	}

	@Transactional(value = "transactionManager")
	public String quickSaleDropdown(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {

			logger.debug("inside quickSaleDropdown");
			q = entitymanager
					.createQuery("select salesOrderNumber from I0021 where (status='Quick sales' and quickStatus='delivered') and client_ID=? ");
			q.setParameter(2, clientID);
			List<I0021> result = (List<I0021>) q.getResultList();

			if (result.size() > 0) {

				purchaseOrder.setResult(result);
			} else {
				logger.debug("inside 2 else loop--------->>>>>");
				purchaseOrder.setResult(null);
				throw new DemoException("*This salesOrderNumber not there");
			}

		} finally {

		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public String quicksaleReturnSubmit(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.debug("inside dao in sales return  -- >> ");
		Query q = null;
		Query s = null;
		Query s1 = null;
		Query s2 = null;
		String stockQuan = "0";
		String newQuant = "0";
		String remQuant = "0";
		float stockin = (float) 0.0;
		float stockout = (float) 0.0;
		float tempnew = (float) 0.0;
		float tempnew1 = (float) 0.0;
		try {
			Date date = Calendar.getInstance().getTime();
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String today = formatter.format(date);
			logger.debug("Today : " + today);
			q = entitymanager
					.createQuery("from I0021 where salesOrderNumber=? and client_ID=? ");
			q.setParameter(1, purchaseOrder.getSalesIdReference());
			q.setParameter(2, clientID);
			List<I0021> sales = (List<I0021>) q.getResultList();
			int batchid = 0;
			int barcodeid = 0;
			int salesid = 0;
			int salesrecordid = 0;
			if (sales.size() > 0) {
				salesid = sales.get(0).getSales_ID();
				logger.debug("sales id -- >>> " + salesid);
			}
			s = entitymanager.createQuery("from I0018 where productName=? and client_ID=?");
			s.setParameter(1, purchaseOrder.getProduct_name());
			s.setParameter(2, clientID);
			List<I0018> result = (List<I0018>) s.getResultList();
			if (result.size() > 0) {
				batchid = result.get(0).getBatch_ID();
				logger.debug("product id -- >>> " + batchid);
				s1 = entitymanager.createQuery("from I0019 where batch_ID=? ");
				s1.setParameter(1, batchid);
				List<I0019> resul = (List<I0019>) s1.getResultList();
				if (resul.size() > 0) {
					// stockQuan=resul.get(0).getQuantity();
					stockin = Float.parseFloat(resul.get(0).getQuantity());
					/* stockout=Float.parseFloat(resul.get(0).getStock_Out()); */
					barcodeid = resul.get(0).getBar_code_ID();
					logger.debug("barcode id -- >> " + barcodeid);
					s2 = entitymanager
							.createQuery("from SalesRecord where bar_code_ID=? and sales_ID=?  and (returnStatus='no' or returnStatus='Return')");
					s2.setParameter(1, barcodeid);
					s2.setParameter(2, salesid);
					List<SalesRecord> resu = (List<SalesRecord>) s2
							.getResultList();
					String temp = "";
					if (resu.size() > 0) {
						salesrecordid = resu.get(0).getSalesRecordId();
						logger.debug("salse record id -- >> " + salesrecordid);
						logger.debug("nr ret -- >> " + purchaseOrder.getNr()
								+ " dam ret -- >> " + purchaseOrder.getDr());
						temp = ""
								+ (new BigDecimal(purchaseOrder.getNr())
										.add(new BigDecimal(purchaseOrder
												.getDr())));
						logger.debug("return quantity -- >> " + temp);
						float returnq = 0;
						float norreturn = 0;
						float damreturn = 0;
						try {
							returnq = Float.parseFloat(resu.get(0)
									.getReturnQuantity());
	
						} catch (Exception e) {
							returnq = Float.parseFloat("0");
							norreturn = Float.parseFloat("0");
							damreturn = Float.parseFloat("0");
							;
						}
						logger.debug("1st -- > return quantity -=->> "
								+ returnq + " nor quan -- >> " + norreturn
								+ " dam quan  - > " + damreturn);
						returnq = returnq + Float.parseFloat(temp);
						norreturn = norreturn
								+ Float.parseFloat(purchaseOrder.getNr());
						damreturn = damreturn
								+ Float.parseFloat(purchaseOrder.getDr());
						logger.debug("total  -- > return quantity -=->> "
								+ returnq + " nor quan -- >> " + norreturn
								+ " dam quan  - > " + damreturn);
						SalesRecord retur = entitymanager.find(
								SalesRecord.class, salesrecordid);
						BigDecimal bd = BigDecimal.valueOf(0);
						bd = BigDecimal.valueOf(returnq);
						bd = bd.setScale(2, RoundingMode.CEILING);

						retur.setReturnQuantity("" + bd);
						retur.setReturnStatus("Return");
		
						entitymanager.merge(retur);
					}

					SalesReturn insert = new SalesReturn();
					insert.setProductName(purchaseOrder.getProduct_name());
					insert.setQuantity(purchaseOrder.getQuantity());
					insert.setDueDate(formatter.parse(today));
					insert.setClient_ID(clientID);
					insert.setDr(purchaseOrder.getDr());
					insert.setNr(purchaseOrder.getNr());
					insert.setSalesRecord(entitymanager.find(SalesRecord.class,
							salesrecordid));
	
					insert.setSalesOrderNumber(purchaseOrder
							.getSalesIdReference());
					insert.setI0021(entitymanager.find(I0021.class, salesid));
					entitymanager.persist(insert);

					if (purchaseOrder.getNr() != null) {
						logger.debug("inside nr");
						tempnew = stockin
								+ Float.parseFloat(purchaseOrder.getNr());
						tempnew1 = stockout
								- Float.parseFloat(purchaseOrder.getNr());
						logger.debug("vallllllllll---" + tempnew);
						logger.debug("barcodeid---" + barcodeid);
						I0019 stock = entitymanager
								.find(I0019.class, barcodeid);
						stock.setQuantity(String.valueOf(tempnew));
						/* stock.setStock_Out(String.valueOf(tempnew1)); */
						stock.setRoll_status("Insert");
						entitymanager.merge(stock);

						s1 = entitymanager
								.createQuery("from I0019 where roll_ID=? ");
						s1.setParameter(1, purchaseOrder.getRollID());
						List<I0019> resul1 = (List<I0019>) s1.getResultList();

						logger.debug("list 19 size" + resul1.size());

					}
					s2 = entitymanager
							.createQuery("from SalesRecord where bar_code_ID=? and sales_ID=? and (returnStatus='no' or returnStatus='Return')");
					s2.setParameter(1, barcodeid);
					s2.setParameter(2, salesid);
					List<SalesRecord> resu1 = (List<SalesRecord>) s2
							.getResultList();
					if (resu1.size() > 0) {
						int sid = 0;
						if (resu1.get(0).getSoldQuantity()
								.equals(resu1.get(0).getReturnQuantity())) {
							sid = resu1.get(0).getSalesRecordId();
							SalesRecord retur = entitymanager.find(
									SalesRecord.class, sid);
							retur.setReturnStatus("Returned");
						}

					}

				}
			} else {
				logger.debug("this product not have a batch id -->> ");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return null;
	}

	@Override
	public String quickViewSalesReturn(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			List<PurchaseOrder> mb2 = new ArrayList<PurchaseOrder>();
			logger.debug("-----------------inside sale return view dao------------------");
			purchaseOrder.setResult1(null);
			logger.debug("from date------------->"
					+ purchaseOrder.getFromDate());
			logger.debug("to date--------------->" + purchaseOrder.getToDate());

			Query q = entitymanager.createQuery("From SalesReturn where client_ID=? ");
			q.setParameter(1, clientID);
			List<SalesReturn> salelist = (ArrayList<SalesReturn>) q
					.getResultList();
			int salrecid = 0;
			if (salelist.size() > 0) {
				for (int i = 0; i < salelist.size(); i++) {
					salrecid = salelist.get(i).getSalesRecord()
							.getSalesRecordId();

					Query q1 = entitymanager
							.createQuery("From SalesRecord where salesRecordId=? and (returnStatus='Return' or returnStatus='Returned') and status='quicksales' ");
					q1.setParameter(1, salrecid);
					List<SalesRecord> salelist1 = (ArrayList<SalesRecord>) q1
							.getResultList();
					if (salelist1.size() > 0) {
						PurchaseOrder po = new PurchaseOrder();
						po.setOrderDate(salelist.get(i).getDueDate());
						po.setOrderNumber(salelist.get(i).getSalesOrderNumber());
						mb2.add(po);
						purchaseOrder.setDomain2(mb2);
					}
				}

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return null;

	}

	public String getbarcodeInfo(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q1 = null;
			q1 = entitymanager.createQuery("from I0001 where barCode=? and client_ID=?");
			q1.setParameter(1, purchaseOrder.getBarcode1());
			q1.setParameter(2, clientID);
			List<I0001> batchProduct1 = (List<I0001>) q1.getResultList();
			int i = 0;
			if (batchProduct1.size() > 0) {
				purchaseOrder.setProduct_name(batchProduct1.get(0)
						.getProductName());
				purchaseOrder.setBarcode1(batchProduct1.get(0).getBarCode());
			} else {
				logger.debug("inside exception message -- >>");
				throw new DemoException("Enter the Correct Barcode");
			}
		} finally {

		}
		return "";
	}

	@Override
	public String getPurchaseQty(PurchaseOrder purchaseOrder)
			throws DemoException {
		try {
			logger.debug("Inside getPurchaseQty DAOImpl"
					+ purchaseOrder.getSalesIdReference());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return null;
	}

	@SuppressWarnings("null")
	@Override
	public String findGlobalSearch(String golbalnamesearch) {
		String status = "Fail";
		try {
			logger.debug("---------------------- Calling findGlobalSearch Dao Begin----------------------"
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
					} else {
						status = "Stock";

					}

				}
			}
			logger.debug("---------------------- Calling findGlobalSearch Dao End----------------------");

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			logger.debug("---------------------- Calling findGlobalSearch Dao Exception----------------------");

		}
		return status;
	}

}
