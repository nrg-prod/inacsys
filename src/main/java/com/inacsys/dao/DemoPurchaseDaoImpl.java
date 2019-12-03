package com.inacsys.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.inacsys.shared.PurchaseReturn;
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
public class DemoPurchaseDaoImpl implements DemoPurchaseDao {

	final Logger logger = LoggerFactory.getLogger(DemoPurchaseDaoImpl.class);

	@PersistenceContext(unitName = "inacsys-pu")
	private EntityManager entitymanager;

	@Transactional(value = "transactionManager")
	public List<PurchaseOrder> getpurchaseDataFVendor(String vendorName)
			throws DemoException {
		Query q = null;
		List<PurchaseOrder> purchase = null;
		List<I0025> vendor = null;
		List<I0031> joinVendor = null;
		List<I0016> joinpurchase = null;
		List<I0016> joinpurchase1 = null;
		try {
			vendor = getVendor(vendorName);
			if (vendor.size() > 0) {
				joinVendor = getjoinVendor(vendor.get(0).getVendor_ID());
			} else {
				logger.debug("No data in vendor>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
			if (joinVendor.size() > 0) {
				joinpurchase1 = new ArrayList<I0016>();
				for (int i = 0; i < joinVendor.size(); i++) {
					logger.debug("Id>>>>>>>>>>>>>>>>>>>>>>>"
							+ joinVendor.get(i).getHas_vendor_ID());
					joinpurchase = getJoinPurchase(joinVendor.get(i)
							.getHas_vendor_ID());
					if (joinpurchase.size() > 0) {
						logger.debug("Size>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
								+ joinpurchase.size());
						for (int j = 0; j < joinpurchase.size(); j++) {
							logger.debug("1");
							I0016 hPur = new I0016();
							hPur.setApprovalStatus(joinpurchase.get(j)
									.getApprovalStatus());
							hPur.setHas_purchase_ID(joinpurchase.get(j)
									.getHas_purchase_ID());
							hPur.setI0015(entitymanager.find(I0015.class,
									joinpurchase.get(j).getI0015()
											.getPurchase_ID()));
							hPur.setI0031(entitymanager.find(I0031.class,
									joinpurchase.get(j).getI0031()
											.getHas_vendor_ID()));
							hPur.setOrderDate(joinpurchase.get(j)
									.getOrderDate());
							hPur.setOrdernumber(joinpurchase.get(j)
									.getOrdernumber());
							hPur.setQuantity(joinpurchase.get(j).getQuantity());
							hPur.setStatus(joinpurchase.get(j).getStatus());
							hPur.setStatus2(joinpurchase.get(j).getStatus2());
							hPur.setStatus3(joinpurchase.get(j).getStatus3());
							hPur.setStatus4(joinpurchase.get(j).getStatus4());
							joinpurchase1.add(hPur);
						}
					}
				}
			} else {
				logger.debug("No data in join Vendor>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
			logger.debug("Final Size>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
					+ joinpurchase1.size());
			if (joinpurchase1.size() > 0) {
				logger.debug("Size>>>>>>>>>>>>>>>" + joinpurchase1.size());
				List<String> str = new ArrayList<String>();
				for (int i = 0; i < joinpurchase1.size(); i++) {
					str.add(joinpurchase1.get(i).getOrdernumber());
				}
				ArrayList<String> result = new ArrayList<String>();
				// Record encountered Strings in HashSet.
				HashSet<String> set = new HashSet();
				// Loop over argument list.
				for (String item : str) {
					// If String is not in set, add it to the list and the set.
					if (!set.contains(item)) {
						result.add(item);
						set.add(item);
					}
				}
				logger.debug("Size>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
								+ result.size());
				purchase = new ArrayList<PurchaseOrder>();
				for (String r : result) {
					int index = 0;
					logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
									+ r.toString());
					for (int i = 0; i < joinpurchase1.size(); i++) {
						if (joinpurchase1.get(i).getOrdernumber()
								.equalsIgnoreCase(r)) {
							index = i;
						}
					}
					PurchaseOrder p = new PurchaseOrder();
					p.setOrderDate(joinpurchase1.get(index).getOrderDate());
					p.setOrderNumber(joinpurchase1.get(index).getOrdernumber());
					p.setFirmName(joinpurchase1.get(index).getI0031()
							.getI0025().getVendorPhoneNumber());
					p.setTota(new BigDecimal(joinpurchase1.get(index)
							.getI0015().getTotalPrice()));
					p.setTotalPrice(""
							+ joinpurchase1.get(index).getI0015()
									.getTotalPrice());
					p.setStatus(joinpurchase1.get(index).getStatus());
					p.setStatus2(joinpurchase1.get(index).getStatus2());
					p.setStatus3(joinpurchase1.get(index).getStatus3());
					p.setApprovalStatus(joinpurchase1.get(index)
							.getApprovalStatus());
					int cnt = 0;
					if (p.getStatus2().equalsIgnoreCase("pending")
							&& p.getStatus3().equalsIgnoreCase("Waiting")) {

						logger.debug("if1");
						try {
							if (new BigDecimal(joinpurchase1.get(index)
									.getI0015().getI0022s().get(0).getI0023s()
									.get(0).getPaidAmount())
									.compareTo(BigDecimal.valueOf(0)) == 0) {
								logger.debug("if2");
								cnt = 2;

								logger.debug("if3");
							} else {
								cnt = 1;

							}
						} catch (Exception e) {
							if (p.getStatus2().equalsIgnoreCase("pending")
									&& p.getStatus3().equalsIgnoreCase(
											"Waiting")) {
								p.setStatus4("in2");
							} else {
								p.setStatus4("out");
							}

						}
						if (cnt == 2) {
							p.setStatus4("in2");

						} else if (cnt == 1) {
							p.setStatus4("in1");
						}
						logger.debug("if4");
					} else {
						p.setStatus4("out");
					}
					purchase.add(p);
				}
			} else {
				logger.debug("No data in has purchase>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return purchase;
	}

	@Transactional(value = "transactionManager")
	public List<I0025> getVendor(String vendorName) throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0025> vendor = null;
		try {

			q = entitymanager
					.createQuery("select o from I0025 o  where o.vendorPhoneNumber like :x and o.status='i' and o.client_ID=?");
			q.setParameter("x", vendorName + "%");
			q.setParameter(1, clientID);
			vendor = q.getResultList();
		} catch (Exception e) {

		} finally {

		}
		return vendor;
	}

	@Transactional(value = "transactionManager")
	public List<I0031> getjoinVendor(int id) throws DemoException {
		Query q = null;
		List<I0031> joinVendor = null;
		try {
			q = entitymanager.createQuery("from I0031 where vendor_ID=?");
			q.setParameter(1, id);
			joinVendor = q.getResultList();
		} catch (Exception e) {

		} finally {

		}
		return joinVendor;
	}

	@Transactional(value = "transactionManager")
	public List<I0016> getJoinPurchase(int id) throws DemoException {
		Query q = null;
		List<I0016> joinPurchase = null;
		try {
			q = entitymanager.createQuery("from I0016 where has_vendor_ID=?");
			q.setParameter(1, id);
			joinPurchase = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
		}
		return joinPurchase;
	}

	@Transactional(value = "transactionManager")
	public String purchaseRetViewForm(PurchaseOrder pur) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;

		logger.debug("purorder number" + pur.getPurchaseIdReference());
		logger.debug("Vendor Name" + pur.getVendorname());

		q = entitymanager.createQuery("from I0025 where vendor_phone_number=? and client_ID=? ");
		q.setParameter(1, pur.getVendorname());
		q.setParameter(2, clientID);
		logger.debug("inside purchase order cancel::::");
		List<I0025> result = (List<I0025>) q.getResultList();
		logger.debug("result" + result.size());

		String pkid = "";
		int i = 0;
		if (result.size() > 0) {
			logger.debug("1");
			pkid = result.get(i).getVendorTelephoneNumber();
			logger.debug("2");
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0015 where tem_order_number=? and client_ID=? ");
			logger.debug("3");
			q1.setParameter(1, pur.getPurchaseIdReference());
			logger.debug("4");
			q.setParameter(2, clientID);
			List<I0015> orderresul = (List<I0015>) q1.getResultList();
			logger.debug("5");
			pur.setOrderresul(orderresul);
			pur.setVenresult(result);
			logger.debug("6");


		} else {
			pur.setOrderresul(null);
			pur.setVenresult(null);
			throw new DemoException("*Not delivered or cancelled");
		}

		return "";

	}

	@Transactional(value = "transactionManager")
	public String purchReturnSubmitNormal(PurchaseOrder purchaseOrder,
			String productName) throws DemoException {

		return null;
	}

	@Transactional(value = "transactionManager")
	public String purchReturnSubmitNormal1(PurchaseOrder purchaseOrder,
			String productName, int i) throws DemoException {

		return null;
	}

	@Transactional(value = "transactionManager")
	public String purReturnSubmitDamage(PurchaseOrder purchaseOrder,
			String productName) throws DemoException {

		return null;
	}

	@Transactional(value = "transactionManager")
	public String purReturnSubmitDamage1(PurchaseOrder purchaseOrder,
			String productName, int i) throws DemoException {

		return null;
	}

	@Override
	public String purchReturnSubmit(PurchaseOrder purchaseOrder)
			throws DemoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(value = "transactionManager")
	public String purchaseInfocollect(PurchaseOrder purchaseOrder)
			throws DemoException {
		Query q = null;
		Query q1 = null;
		Query q2 = null;
		Query q3 = null;
		Query q4 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");

		try {
			logger.debug("Inside of purchaseInfocollect "
					+ purchaseOrder.getRollID());

			int prodVen = 0;
			int prodid = 0;
			int bid = 0;
			BigDecimal purquan = new BigDecimal("0");

			q = entitymanager.createQuery("from I0018 where productName=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getProduct_name());
			q.setParameter(2, clientID);
			List<I0018> list18 = (List<I0018>) q.getResultList();
			bid = list18.get(0).getBatch_ID();

			q = entitymanager
					.createQuery("from I0019 where batch_ID=? and status=?");
			q.setParameter(1, bid);
			q.setParameter(2, "barcode genterated");
			List<I0019> list19 = (List<I0019>) q.getResultList();
			String dbquantity = null;
			dbquantity = list19.get(0).getQuantity();
			purchaseOrder.setQuantity1(dbquantity);
			logger.debug("dao >> dbquan -- value " + dbquantity);
			purchaseOrder.setResul(list19);

			q1 = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
			q1.setParameter(1, purchaseOrder.getProduct_name());
			q1.setParameter(2, clientID);
			List<I0001> list01 = (List<I0001>) q1.getResultList();
			if (list01.size() > 0) {
				prodid = list01.get(0).getProduct_ID();
				q2 = entitymanager.createQuery("from I0031 where product_ID=?");
				q2.setParameter(1, prodid);
				List<I0031> list31 = (List<I0031>) q2.getResultList();
				if (list31.size() > 0) {
					prodVen = list31.get(0).getHas_vendor_ID();
					logger.debug(" prod -->> prim id " + prodVen);
				}
				q3 = entitymanager
						.createQuery("from I0016 where ordernumber=? and has_vendor_ID=?  and client_ID=?");
				q3.setParameter(1, purchaseOrder.getOrderNumber());
				q3.setParameter(2, prodVen);
				q3.setParameter(3, clientID);
				List<I0016> list16 = (List<I0016>) q3.getResultList();
				if (list16.size() > 0) {
					purquan = new BigDecimal(list16.get(0).getQuantity());
					logger.debug(" -->> purquan " + purquan);
					purchaseOrder.setPurchaseQuantity("" + purquan);
				}
				BigDecimal returnq = new BigDecimal("0");
				q4 = entitymanager
						.createQuery("from PurchaseReturn where purchaseOrderNumber=? and product_name=?  and client_ID=?");
				q4.setParameter(1, purchaseOrder.getOrderNumber());
				q4.setParameter(2, purchaseOrder.getProduct_name());
				q4.setParameter(3, clientID);
				List<PurchaseReturn> listz = (List<PurchaseReturn>) q4
						.getResultList();
				BigDecimal remq = new BigDecimal("0");
				int bar_ID = 0;
				String qq1 = "";
				String qq2 = "";
				String qq3 = "";
				if (listz.size() > 0) {

					BigDecimal nrq = new BigDecimal("0");
					BigDecimal drq = new BigDecimal("0");
					remq = new BigDecimal("0");
					for (int i = 0; i < listz.size(); i++) {

						bar_ID = listz.get(i).getI0019().getBar_code_ID();
						BigDecimal stock = new BigDecimal("0");
						Query tempquery = entitymanager
								.createQuery("from I0019 where bar_code_ID=? and status=?");
						tempquery.setParameter(1, bar_ID);
						tempquery.setParameter(2, "barcode genterated");
						List<I0019> newres = (List<I0019>) tempquery
								.getResultList();
						if (newres.size() > 0) {
							stock = new BigDecimal(newres.get(0).getQuantity());
							stock = stock.setScale(2, RoundingMode.CEILING);

							try {
								drq = drq.add(new BigDecimal(listz.get(i)
										.getDamageReturn()));
								nrq = nrq.add(new BigDecimal(listz.get(i)
										.getNormalReturn()));
							} catch (NullPointerException e) {
								drq = new BigDecimal("0");
								nrq = new BigDecimal("0");
							}
							logger.debug("quant > drq " + drq);
							logger.debug("quant > nrq " + nrq);
							returnq = nrq.add(drq);
							purchaseOrder.setReturnedQ("" + returnq);
							purchaseOrder.setRemaining(qq3);
							remq = stock.subtract(returnq);
							logger.debug("stock--->" + remq);
						} else {
							remq = stock;
						}
						qq1 = listz.get(i).getDamageReturn();
						qq2 = listz.get(i).getNormalReturn();
						qq3 = ""
								+ (new BigDecimal(qq1).add(new BigDecimal(qq2)));
						purchaseOrder.setRemaining(dbquantity);
						purchaseOrder.setQuantity1("" + returnq);
					}
				} else {
					qq3 = "0";
					purchaseOrder.setReturnedQ("0");
					purchaseOrder.setRemaining(dbquantity);
					logger.debug(" tot ret quan "
							+ purchaseOrder.getReturnedQ());

				}

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String purOrderViewproduct(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		LinkedList<String> allpurID = new LinkedList<String>();
		List<PurchaseOrder> purviewfinal1 = new ArrayList<PurchaseOrder>();
		Query q = null;
		Query qq1 = null;
		logger.debug("purorder number" + purchaseOrder.getPurchaseIdReference());

		q = entitymanager
				.createQuery("from I0015 where tem_order_number=? and status2=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getPurchaseIdReference());
		q.setParameter(2, "addedToStock");
		q.setParameter(3, clientID);
		logger.debug("------------------salesOrderViewproduct------------------");
		List<I0015> result = (List<I0015>) q.getResultList();
		logger.debug("size of result" + result.size());
		int purchaseId = 0;
		String Quntity = "";
		String stockin = "";
		if (result.size() > 0) {
			purchaseId = result.get(0).getPurchase_ID();
			Query q1 = entitymanager
					.createQuery("from I0016 where purchase_ID=? and status4=?");
			q1.setParameter(1, purchaseId);
			q1.setParameter(2, "Waiting");
			List<I0016> result1 = (List<I0016>) q1.getResultList();
			logger.debug("size of result1" + result1.size());
			if (result1.size() > 0) {

				String roll = "";
				int batchid = 0;
				int serialNo = 0;
				int join = 0;
				for (I0016 i0016 : result1) {

					PurchaseOrder Order = new PurchaseOrder();
					serialNo++;
					stockin = i0016.getQuantity();
					join = i0016.getI0031().getHas_vendor_ID();
					qq1 = entitymanager
							.createQuery("from I0031 where has_vendor_ID=? ");
					qq1.setParameter(1, join);
					List<I0031> result4 = (List<I0031>) qq1.getResultList();
					if (result4.size() > 0) {
						batchid = result4.get(0).getI0001().getProduct_ID();
					}

					Query q3 = entitymanager
							.createQuery("from I0001 where product_ID=? ");
					q3.setParameter(1, batchid);
					String unit = "";
					String prod = "";
					List<I0001> result3 = (List<I0001>) q3.getResultList();
					logger.debug("size of result3" + result3.size());
					if (result3.size() > 0) {
						prod = result3.get(0).getProductName();
						unit = result3.get(0).getUnit();
						Order.setUnit(unit);
						Order.setQuantity(stockin);
						Order.setSerialNo(serialNo);
						Order.setProduct_name(prod);

					}
					purviewfinal1.add(Order);

				}

				logger.debug("----------------------------------------");
				purchaseOrder.setFinallist(purviewfinal1);
			} else {
				logger.debug(" Not added to stock");
				throw new DemoException(
						"*This Purchase Order Already Returned all the Products");
			}
		} else {
			logger.debug(" Not added to stock");
			throw new DemoException(
					"*This Purchase Order is not added to the stock");
		}
		return "";

	}
}
