package com.inacsys.managedBean;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.sql.Select;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.inacsys.domain.PurchaseReport;
import com.inacsys.domain.VendorReport;
import com.inacsys.exception.DemoException;
import com.inacsys.util.CommonValidate;
import com.inacsys.util.ReportJDBC;


@ManagedBean(name = "purchaseReportMB")
public class PurchaseReportMB {
	private static Logger logger = Logger.getLogger(PurchaseReportMB.class);
	public String validate;
	public Date pFromDate;
	public Date pToDate;
	public String purchaseType;
	public String vendorName;
	public String allPurchaseType;
	public String singleTypeFlag;
	public String multipleTypeFlag;
	private boolean purchaseReportDtFlag = false;
	public String returnStatus;
	private boolean purchaseDetailReportDtFlag = false;
	private boolean purchaseReportDtFlag1 = false;
	private boolean norecordFlag = false;
	private boolean purchaseDeliveredDtFlag = false;
	private boolean purchasePaymentDtFlag = false;
	private boolean purchasereturnDtFlag = false;
	private boolean purchasereturnDtFlag1 = false;
	public ArrayList<PurchaseReport> singlePurchaseReportlist = null;
	public ArrayList<PurchaseReport> purchaseReportlist = null;
	public ArrayList<PurchaseReport> purchaseReportlist1 = null;
	public ArrayList<PurchaseReport> purchaseReportlist2 = null;
	public ArrayList<PurchaseReport> purchaseReportlist3 = null;
	public String totAmount;

	public boolean isPurchasereturnDtFlag1() {
		return purchasereturnDtFlag1;
	}

	public void setPurchasereturnDtFlag1(boolean purchasereturnDtFlag1) {
		this.purchasereturnDtFlag1 = purchasereturnDtFlag1;
	}

	public boolean isPurchasereturnDtFlag() {
		return purchasereturnDtFlag;
	}

	public void setPurchasereturnDtFlag(boolean purchasereturnDtFlag) {
		this.purchasereturnDtFlag = purchasereturnDtFlag;
	}

	public boolean isPurchasePaymentDtFlag() {
		return purchasePaymentDtFlag;
	}

	public void setPurchasePaymentDtFlag(boolean purchasePaymentDtFlag) {
		this.purchasePaymentDtFlag = purchasePaymentDtFlag;
	}

	public ArrayList<PurchaseReport> getPurchaseReportlist3() {
		return purchaseReportlist3;
	}

	public void setPurchaseReportlist3(
			ArrayList<PurchaseReport> purchaseReportlist3) {
		this.purchaseReportlist3 = purchaseReportlist3;
	}

	public ArrayList<PurchaseReport> getPurchaseReportlist2() {
		return purchaseReportlist2;
	}

	public void setPurchaseReportlist2(
			ArrayList<PurchaseReport> purchaseReportlist2) {
		this.purchaseReportlist2 = purchaseReportlist2;
	}

	public boolean isPurchaseDeliveredDtFlag() {
		return purchaseDeliveredDtFlag;
	}

	public void setPurchaseDeliveredDtFlag(boolean purchaseDeliveredDtFlag) {
		this.purchaseDeliveredDtFlag = purchaseDeliveredDtFlag;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public boolean isPurchaseDetailReportDtFlag() {
		return purchaseDetailReportDtFlag;
	}

	public void setPurchaseDetailReportDtFlag(boolean purchaseDetailReportDtFlag) {
		this.purchaseDetailReportDtFlag = purchaseDetailReportDtFlag;
	}

	public String getTotAmount() {
		return totAmount;
	}

	public void setTotAmount(String totAmount) {
		this.totAmount = totAmount;
	}

	public ArrayList<PurchaseReport> getPurchaseReportlist1() {
		return purchaseReportlist1;
	}

	public void setPurchaseReportlist1(
			ArrayList<PurchaseReport> purchaseReportlist1) {
		this.purchaseReportlist1 = purchaseReportlist1;
	}

	public boolean isPurchaseReportDtFlag1() {
		return purchaseReportDtFlag1;
	}

	public void setPurchaseReportDtFlag1(boolean purchaseReportDtFlag1) {
		this.purchaseReportDtFlag1 = purchaseReportDtFlag1;
	}

	public boolean isNorecordFlag() {
		return norecordFlag;
	}

	public void setNorecordFlag(boolean norecordFlag) {
		this.norecordFlag = norecordFlag;
	}

	public ArrayList<PurchaseReport> getPurchaseReportlist() {
		return purchaseReportlist;
	}

	public void setPurchaseReportlist(
			ArrayList<PurchaseReport> purchaseReportlist) {
		this.purchaseReportlist = purchaseReportlist;
	}

	public boolean isPurchaseReportDtFlag() {
		return purchaseReportDtFlag;
	}

	public void setPurchaseReportDtFlag(boolean purchaseReportDtFlag) {
		this.purchaseReportDtFlag = purchaseReportDtFlag;
	}

	public ArrayList<PurchaseReport> getSinglePurchaseReportlist() {
		return singlePurchaseReportlist;
	}

	public void setSinglePurchaseReportlist(
			ArrayList<PurchaseReport> singlePurchaseReportlist) {
		this.singlePurchaseReportlist = singlePurchaseReportlist;
	}

	public String getSingleTypeFlag() {
		return singleTypeFlag;
	}

	public void setSingleTypeFlag(String singleTypeFlag) {
		this.singleTypeFlag = singleTypeFlag;
	}

	public String getMultipleTypeFlag() {
		return multipleTypeFlag;
	}

	public void setMultipleTypeFlag(String multipleTypeFlag) {
		this.multipleTypeFlag = multipleTypeFlag;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public Date getpFromDate() {
		return pFromDate;
	}

	public void setpFromDate(Date pFromDate) {
		this.pFromDate = pFromDate;
	}

	public Date getpToDate() {
		return pToDate;
	}

	public void setpToDate(Date pToDate) {
		this.pToDate = pToDate;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getAllPurchaseType() {
		return allPurchaseType;
	}

	public void setAllPurchaseType(String allPurchaseType) {
		this.allPurchaseType = allPurchaseType;
	}

	public void valueChange(ValueChangeEvent vc) {
		logger.debug("Inside value change method calling");
		String vtype = vc.getNewValue().toString();
		if (vtype.equalsIgnoreCase("single")) {
			logger.debug("inside if");
			singleTypeFlag = "1";
			multipleTypeFlag = "none";
			setNorecordFlag(false);
		} else if (vtype.equals("multiple")) {
			logger.debug("inside else if");
			singleTypeFlag = "none";
			multipleTypeFlag = "1";
			setNorecordFlag(false);
		}
	}

	public void purchasereport() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("PurchaseReport",
				options, null);
		setPurchaseType(null);
		setValidate(null);
		setpFromDate(null);
		setpToDate(null);
		setVendorName(null);
		setAllPurchaseType(null);
		setPurchaseReportDtFlag(false);
		setPurchaseReportDtFlag1(false);
		setPurchaseDetailReportDtFlag(false);
		setNorecordFlag(false);
		setPurchaseDeliveredDtFlag(false);
		setPurchasePaymentDtFlag(false);
		setPurchasereturnDtFlag(false);
		singleTypeFlag = "none";
		multipleTypeFlag = "none";
	}

	/*public String searchReport() {
		setValidate(null);
		logger.debug("inside search purchase report method calling");
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			if (pFromDate == null) {
				fieldName = CommonValidate.findComponentInRoot("fdate")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Choose the  From Date."));
			}
			if (pToDate == null) {
				fieldName = CommonValidate.findComponentInRoot("tdate")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Choose the  To Date."));
			}

			if (StringUtils.isBlank(purchaseType)) {

				fieldName = CommonValidate.findComponentInRoot("vt")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Select Vendor type"));
			}
			if (purchaseType.equalsIgnoreCase("single")) {
				if (vendorName.equalsIgnoreCase("")) {
					fieldName = CommonValidate.findComponentInRoot("v1")
							.getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage(
							"Please Enter the Vendor Name"));
				}
				if (!vendorName.equalsIgnoreCase("")) {
					logger.debug("inside if");
					singlePurchaseReportlist = ReportJDBC
							.vendornamesearch(purchaseType, allPurchaseType,
									vendorName, pFromDate, pToDate);
					logger.debug("purchase report list size"
							+ singlePurchaseReportlist.size());
					if (singlePurchaseReportlist.size() > 0) {
						BigDecimal quan = BigDecimal.valueOf(0);
						BigDecimal totprice = BigDecimal.valueOf(0);
						purchaseReportlist = new ArrayList<PurchaseReport>();
						ArrayList<String> ponumber = new ArrayList<String>();
						for (int i = 0; i < singlePurchaseReportlist.size(); i++) {
							ponumber.add(singlePurchaseReportlist.get(i)
									.getOrderNumber());
						}
						HashSet<String> porders = new HashSet<String>(ponumber);
						ponumber.clear();
						ponumber.addAll(porders);
						Collections.sort(ponumber);
						logger.debug("purchase order no size" + ponumber.size());
						for (int i = 0; i < ponumber.size(); i++) {
							quan = BigDecimal.valueOf(0);
							totprice = BigDecimal.valueOf(0);
							for (int j = 0; j < singlePurchaseReportlist.size(); j++) {
								if (singlePurchaseReportlist.get(j)
										.getOrderNumber()
										.equalsIgnoreCase(ponumber.get(i))) {
									quan = quan.add(new BigDecimal(
											singlePurchaseReportlist.get(j)
													.getQuantity()));
									totprice = totprice
											.add(new BigDecimal(
													singlePurchaseReportlist
															.get(j)
															.getQuantity())
													.multiply(new BigDecimal(
															singlePurchaseReportlist
																	.get(j)
																	.getPrice())));
								}

							}
							PurchaseReport pur = new PurchaseReport();
							pur.setVendorName(singlePurchaseReportlist.get(i)
									.getVendorName());
							pur.setOrderDate(singlePurchaseReportlist.get(i)
									.getOrderDate());
							pur.setOrderNumber(singlePurchaseReportlist.get(i)
									.getOrderNumber());
							pur.setQuantity("" + quan);
							pur.setPrice("" + totprice);
							pur.setPurchaseStatus(singlePurchaseReportlist.get(
									i).getPurchaseStatus());
							purchaseReportlist.add(pur);
						}
						if (purchaseReportlist.size() > 0) {
							setPurchaseReportDtFlag(true);
							setNorecordFlag(false);
						} else {
							setPurchaseReportDtFlag(false);
							setNorecordFlag(true);
						}
					} else {
						setPurchaseReportDtFlag(false);
						setNorecordFlag(true);
					}
				}
			}

			else if (purchaseType.equalsIgnoreCase("multiple")) {
				logger.debug("inside multiple if");
				if (allPurchaseType.equalsIgnoreCase("---Select---")) {
					fieldName = CommonValidate.findComponentInRoot("av1")
							.getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage(
							"Please Choose the Vendor Name"));
				} else if (allPurchaseType.equalsIgnoreCase("All")) {
					logger.debug("inside all");
					singlePurchaseReportlist = ReportJDBC
							.vendornamesearch(purchaseType, allPurchaseType,
									vendorName, pFromDate, pToDate);
					logger.debug("all purchase report list size"
							+ singlePurchaseReportlist.size());
					purchaseReportlist = new ArrayList<PurchaseReport>();
					ArrayList<String> vendors = new ArrayList<String>();
					ArrayList<String> phones = new ArrayList<String>();
					for (int i = 0; i < singlePurchaseReportlist.size(); i++) {
						vendors.add(singlePurchaseReportlist.get(i)
								.getVendorName());
						phones.add(singlePurchaseReportlist.get(i)
								.getPhoneNumber());
					}
					HashSet<String> vendor = new HashSet<String>(vendors);
					HashSet<String> phone = new HashSet<String>(phones);
					vendors.clear();
					vendors.addAll(vendor);
					phones.clear();
					phones.addAll(phone);
					for (int i = 0; i < vendors.size(); i++) {
						BigDecimal totprice = BigDecimal.valueOf(0);
						BigDecimal quan = BigDecimal.valueOf(0);
						for (int j = 0; j < singlePurchaseReportlist.size(); j++) {
							if (singlePurchaseReportlist.get(j).getVendorName()
									.equals(vendors.get(i))) {
								quan = quan.add(new BigDecimal(
										singlePurchaseReportlist.get(j)
												.getQuantity()));
								totprice = totprice.add(new BigDecimal(
										singlePurchaseReportlist.get(j)
												.getTotalPrice())
										.multiply(new BigDecimal(
												singlePurchaseReportlist.get(j)
														.getQuantity())));
							}

						}
						PurchaseReport pur = new PurchaseReport();
						pur.setVendorName(vendors.get(i));
						pur.setPhoneNumber(phones.get(i));
						pur.setQuantity("" + quan);
						pur.setTotalPrice("" + totprice);
						purchaseReportlist.add(pur);
					}
					if (purchaseReportlist.size() > 0) {
						setPurchaseDetailReportDtFlag(true);
						setNorecordFlag(false);
						setPurchaseDeliveredDtFlag(false);
						setPurchasereturnDtFlag(false);
						setPurchasePaymentDtFlag(false);
					} else {
						setNorecordFlag(true);
						setPurchaseDetailReportDtFlag(false);
						setPurchaseDeliveredDtFlag(false);
						setPurchasereturnDtFlag(false);
						setPurchasePaymentDtFlag(false);
					}
				}

				else if (allPurchaseType.equalsIgnoreCase("Purchase Delivery")) {
					logger.debug("inside purchase delivery");
					singlePurchaseReportlist =ReportJDBC
							.vendornamesearch(purchaseType, allPurchaseType,
									vendorName, pFromDate, pToDate);
					logger.debug("delivery list size"
							+ singlePurchaseReportlist.size());
					purchaseReportlist2 = new ArrayList<PurchaseReport>();
					for (int i = 0; i < singlePurchaseReportlist.size(); i++) {
						PurchaseReport pur = new PurchaseReport();
						pur.setOrderNumber(singlePurchaseReportlist.get(i)
								.getOrderNumber());
						pur.setProductName(singlePurchaseReportlist.get(i)
								.getProductName());
						pur.setQuantity(singlePurchaseReportlist.get(i)
								.getQuantity());
						pur.setPrice(singlePurchaseReportlist.get(i).getPrice());
						pur.setPurchaseStatus(singlePurchaseReportlist.get(i)
								.getPurchaseStatus());
						pur.setVendorName(singlePurchaseReportlist.get(i)
								.getVendorName());
						pur.setPhoneNumber(singlePurchaseReportlist.get(i)
								.getPhoneNumber());
						purchaseReportlist2.add(pur);
					}
					if (purchaseReportlist2.size() > 0) {
						setPurchaseDeliveredDtFlag(true);
						setNorecordFlag(false);
						setPurchasePaymentDtFlag(false);
						setPurchasereturnDtFlag(false);
						setPurchaseDetailReportDtFlag(false);
					} else {
						setPurchaseDeliveredDtFlag(false);
						setNorecordFlag(true);
						setPurchasereturnDtFlag(false);
						setPurchasePaymentDtFlag(false);
						setPurchaseDetailReportDtFlag(false);
					}
				} else if (allPurchaseType.equalsIgnoreCase("Purchase Return")) {
					logger.debug("inside purchase return");
					singlePurchaseReportlist = ReportJDBC
							.vendornamesearch(purchaseType, allPurchaseType,
									vendorName, pFromDate, pToDate);
					logger.debug("purchase return list size"
							+ singlePurchaseReportlist.size());
					purchaseReportlist3 = new ArrayList<PurchaseReport>();
					ArrayList<String> ponumbers = new ArrayList<String>();
					for (int i = 0; i < singlePurchaseReportlist.size(); i++) {
						ponumbers.add(singlePurchaseReportlist.get(i)
								.getOrderNumber());
					}
					HashSet<String> ponumber = new HashSet<String>(ponumbers);
					ponumbers.clear();
					ponumbers.addAll(ponumber);
					Collections.sort(ponumbers);

					for (int i = 0; i < ponumber.size(); i++) {
						BigDecimal quan = BigDecimal.valueOf(0);
						BigDecimal nr = BigDecimal.valueOf(0);
						BigDecimal dr = BigDecimal.valueOf(0);
						for (int j = 0; j < singlePurchaseReportlist.size(); j++) {
							if (singlePurchaseReportlist.get(j)
									.getOrderNumber()
									.equalsIgnoreCase(ponumbers.get(i))) {
								quan = new BigDecimal(singlePurchaseReportlist
										.get(j).getQuantity());
								nr = nr.add(new BigDecimal(
										singlePurchaseReportlist.get(j)
												.getNormalReturn()));
								dr = dr.add(new BigDecimal(
										singlePurchaseReportlist.get(j)
												.getDamageReurn()));

							}
						}
						PurchaseReport pur = new PurchaseReport();
						pur.setOrderNumber(ponumbers.get(i));
						pur.setQuantity("" + quan);
						pur.setReturnStatus(singlePurchaseReportlist.get(0)
								.getReturnStatus());
						pur.setNormalReturn("" + nr);
						pur.setDamageReurn("" + dr);
						purchaseReportlist3.add(pur);

					}
					if (purchaseReportlist3.size() > 0) {
						setPurchasereturnDtFlag(true);
						setNorecordFlag(false);
						setPurchaseDeliveredDtFlag(false);
						setPurchasePaymentDtFlag(false);
						setPurchaseDetailReportDtFlag(false);
					} else {
						setPurchasereturnDtFlag(false);
						setNorecordFlag(true);
						setPurchaseDeliveredDtFlag(false);
						setPurchasePaymentDtFlag(false);
						setPurchaseDetailReportDtFlag(false);
					}

				} else if (allPurchaseType.equalsIgnoreCase("Purchase Close")) {
					logger.debug("inside purchase close");
					singlePurchaseReportlist = ReportJDBC
							.vendornamesearch(purchaseType, allPurchaseType,
									vendorName, pFromDate, pToDate);
					logger.debug("delivery list size"
							+ singlePurchaseReportlist.size());
					purchaseReportlist3 = new ArrayList<PurchaseReport>();
					for (int i = 0; i < singlePurchaseReportlist.size(); i++) {
						BigDecimal totprice = BigDecimal.valueOf(0);
						totprice = totprice.add(new BigDecimal(
								singlePurchaseReportlist.get(i).getQuantity())
								.multiply(new BigDecimal(
										singlePurchaseReportlist.get(i)
												.getPrice())));
						PurchaseReport pur = new PurchaseReport();
						pur.setOrderNumber(singlePurchaseReportlist.get(i)
								.getOrderNumber());
						pur.setProductName(singlePurchaseReportlist.get(i)
								.getProductName());
						pur.setQuantity(singlePurchaseReportlist.get(i)
								.getQuantity());
						pur.setPrice(singlePurchaseReportlist.get(i).getPrice());
						pur.setTotalPrice("" + totprice);
						pur.setPaymentStatus(singlePurchaseReportlist.get(i)
								.getPaymentStatus());
						pur.setVendorName(singlePurchaseReportlist.get(i)
								.getVendorName());
						pur.setPhoneNumber(singlePurchaseReportlist.get(i)
								.getPhoneNumber());
						purchaseReportlist3.add(pur);
					}
					if (purchaseReportlist3.size() > 0) {
						setPurchasePaymentDtFlag(true);
						setNorecordFlag(false);
						setPurchaseDeliveredDtFlag(false);
						setPurchasereturnDtFlag(false);
						setPurchaseDetailReportDtFlag(false);
					} else {
						setPurchasePaymentDtFlag(false);
						setNorecordFlag(true);
						setPurchaseDeliveredDtFlag(false);
						setPurchasereturnDtFlag(false);
						setPurchaseDetailReportDtFlag(false);
					}
				}
			}

		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
		}
		return "";
	}*/

	public void onRowSelectEvent(final SelectEvent event) {
		try {
			String p1 = ((PurchaseReport) event.getObject()).getVendorName();
			logger.debug("p1" + p1);
			String p2 = ((PurchaseReport) event.getObject()).getOrderNumber();
			logger.debug("p2" + p2);
			/*singlePurchaseReportlist = ReportJDBC.vendornamesearch(
					purchaseType, allPurchaseType, p1, pFromDate, pToDate);*/
			logger.debug("singlepurchase report list size==========>"
					+ singlePurchaseReportlist.size());
			setPurchaseReportDtFlag1(true);
			purchaseReportlist1 = new ArrayList<PurchaseReport>();
			BigDecimal grandtemp = BigDecimal.valueOf(0);
			for (int i = 0; i < singlePurchaseReportlist.size(); i++) {
				BigDecimal totamt = BigDecimal.valueOf(0);
				if (p2.equalsIgnoreCase(singlePurchaseReportlist.get(i)
						.getOrderNumber())) {
					totamt = totamt.add(new BigDecimal(singlePurchaseReportlist
							.get(i).getQuantity()).multiply(new BigDecimal(
							singlePurchaseReportlist.get(i).getPrice())));
					PurchaseReport pur = new PurchaseReport();
					pur.setOrderNumber(singlePurchaseReportlist.get(i)
							.getOrderNumber());
					pur.setProductName(singlePurchaseReportlist.get(i)
							.getProductName());
					pur.setQuantity(singlePurchaseReportlist.get(i)
							.getQuantity());
					pur.setPrice(singlePurchaseReportlist.get(i).getPrice());
					pur.setPurchaseStatus(singlePurchaseReportlist.get(i)
							.getPurchaseStatus());
					pur.setPaymentStatus(singlePurchaseReportlist.get(i)
							.getPaymentStatus());
					pur.setReturnStatus(singlePurchaseReportlist.get(i)
							.getReturnStatus());
					setReturnStatus(pur.getReturnStatus());
					pur.setTotalPrice("" + totamt);
					purchaseReportlist1.add(pur);
					grandtemp = grandtemp.add(totamt);
					setTotAmount("" + grandtemp);
				}
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public void onRowSelectEvent1(final SelectEvent event) throws IOException,
			SQLException {
		try {
			logger.debug("select event method calling");
			String s1 = ((PurchaseReport) event.getObject()).getVendorName();
			logger.debug("s1" + s1);
			setPurchaseReportDtFlag1(true);
			purchaseReportlist1 = new ArrayList<PurchaseReport>();
			/*singlePurchaseReportlist = ReportJDBC.vendornamesearch(
					purchaseType, allPurchaseType, vendorName, pFromDate,
					pToDate);*/
			logger.debug("report list size" + singlePurchaseReportlist.size());
			BigDecimal nettemp = BigDecimal.valueOf(0);
			for (int i = 0; i < singlePurchaseReportlist.size(); i++) {
				BigDecimal totprice = BigDecimal.valueOf(0);
				if (s1.equals(singlePurchaseReportlist.get(i).getVendorName())) {
					totprice = totprice.add(new BigDecimal(
							singlePurchaseReportlist.get(i).getQuantity())
							.multiply(new BigDecimal(singlePurchaseReportlist
									.get(i).getTotalPrice())));
					PurchaseReport purchaseReport = new PurchaseReport();
					purchaseReport.setOrderNumber(singlePurchaseReportlist.get(
							i).getOrderNumber());
					purchaseReport.setProductName(singlePurchaseReportlist.get(
							i).getProductName());
					purchaseReport.setQuantity(singlePurchaseReportlist.get(i)
							.getQuantity());
					purchaseReport.setTotalPrice(singlePurchaseReportlist
							.get(i).getTotalPrice());
					purchaseReport.setPurchaseStatus(singlePurchaseReportlist
							.get(i).getPurchaseStatus());
					purchaseReport.setPaymentStatus(singlePurchaseReportlist
							.get(i).getPaymentStatus());
					purchaseReport.setReturnStatus(singlePurchaseReportlist
							.get(i).getReturnStatus());
					setReturnStatus(purchaseReport.getReturnStatus());
					purchaseReport.setPrice("" + totprice);
					purchaseReportlist1.add(purchaseReport);
					nettemp = nettemp.add(totprice);
					setTotAmount("" + nettemp);
				}

			}
			logger.debug("purchaseReportlist1 size"
					+ purchaseReportlist1.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public void onRowSelectEvent2(final SelectEvent event) throws IOException,
			SQLException {
		try {
			logger.debug("select event method calling");
			String s1 = ((PurchaseReport) event.getObject()).getOrderNumber();
			logger.debug("s1" + s1);
			setPurchasereturnDtFlag1(true);
			purchaseReportlist1 = new ArrayList<PurchaseReport>();
			/*singlePurchaseReportlist = ReportJDBC.vendornamesearch(
					purchaseType, allPurchaseType, s1, pFromDate, pToDate);*/
			logger.debug("return report list size"
					+ singlePurchaseReportlist.size());
			for (int i = 0; i < singlePurchaseReportlist.size(); i++) {
				if (s1.equals(singlePurchaseReportlist.get(i).getOrderNumber())) {
					PurchaseReport purchaseReport = new PurchaseReport();
					purchaseReport.setOrderNumber(singlePurchaseReportlist.get(
							i).getOrderNumber());
					purchaseReport.setProductName(singlePurchaseReportlist.get(
							i).getProductName());
					purchaseReport.setReturnDate(singlePurchaseReportlist
							.get(i).getReturnDate());
					purchaseReport.setVendorName(singlePurchaseReportlist
							.get(i).getVendorName());
					purchaseReport.setPhoneNumber(singlePurchaseReportlist.get(
							i).getPhoneNumber());
					purchaseReport.setNormalReturn(singlePurchaseReportlist
							.get(i).getNormalReturn());
					purchaseReport.setDamageReurn(singlePurchaseReportlist.get(
							i).getDamageReurn());
					purchaseReport.setReturnStatus(singlePurchaseReportlist
							.get(i).getReturnStatus());
					purchaseReportlist1.add(purchaseReport);

				}

			}
			logger.debug("purchaseReportlist1 size"
					+ purchaseReportlist1.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	/* prema begin 27/06/2016 PaymentReport */

	public void paymentreport() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("payment-report",
				options, null);
		try {
			vendordropdown();
			customerdropdown();
			setPayFromDate(null);
			setPayToDate(null);
			setType(null);
			setCustomertype(null);
			setVendortype(null);
			setPaymentType(null);
			setNorecordFlag(false);
			setPaymentReportDtFlag(false);
			setCashpaymentReportDtFlag(false);
			setAllpaymentReportDtFlag(false);
			setCardpaymentReportDtFlag(false);
			setChequepaymentReportDtFlag(false);
			setTransferpayReportDtFlag(false);
			setPurchequepaymentReportDtFlag(false);
			setPurcardpaymentReportDtFlag(false);
			setPurcashpaymentReportDtFlag(false);
			setPurpaymentReportDtFlag(false);
			setPurtransferpayReportDtFlag(false);
			vendorFlag = "none";
			customerFlag = "none";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public Date payFromDate;
	public Date payToDate;
	public String paymentType;
	public String type;
	public ArrayList<PurchaseReport> paymentReportlist = null;
	public ArrayList<PurchaseReport> paymentReportlist1 = null;
	public ArrayList<PurchaseReport> paymentReportlist2 = null;
	public ArrayList<PurchaseReport> paymentReportlist3 = null;
	public ArrayList<PurchaseReport> paymentReportlist4 = null;
	public ArrayList<PurchaseReport> paymentReportlist5 = null;
	public ArrayList<PurchaseReport> purpaymentReportlist1 = null;
	public ArrayList<PurchaseReport> purpaymentReportlist2 = null;
	public ArrayList<PurchaseReport> purpaymentReportlist3 = null;
	public ArrayList<PurchaseReport> purpaymentReportlist4 = null;
	public ArrayList<PurchaseReport> purpaymentReportlist5 = null;
	public ArrayList<PurchaseReport> customerList = null;
	public ArrayList<String> customerList1 = null;
	public ArrayList<PurchaseReport> vendorList = null;
	public ArrayList<String> vendorList1 = null;
	public String customerFlag = "none";
	public String vendorFlag = "none";
	private boolean paymentReportDtFlag = false;
	private boolean allpaymentReportDtFlag = false;
	private boolean cashpaymentReportDtFlag = false;
	private boolean cardpaymentReportDtFlag = false;
	private boolean chequepaymentReportDtFlag = false;
	private boolean transferpayReportDtFlag = false;
	private boolean purpaymentReportDtFlag = false;
	private boolean purcashpaymentReportDtFlag = false;
	private boolean purcardpaymentReportDtFlag = false;
	private boolean purchequepaymentReportDtFlag = false;
	private boolean purtransferpayReportDtFlag = false;
	public String customertype;
	public String vendortype;
	public String customerName;

	public ArrayList<PurchaseReport> getPaymentReportlist5() {
		return paymentReportlist5;
	}

	public void setPaymentReportlist5(
			ArrayList<PurchaseReport> paymentReportlist5) {
		this.paymentReportlist5 = paymentReportlist5;
	}

	public ArrayList<PurchaseReport> getPurpaymentReportlist5() {
		return purpaymentReportlist5;
	}

	public void setPurpaymentReportlist5(
			ArrayList<PurchaseReport> purpaymentReportlist5) {
		this.purpaymentReportlist5 = purpaymentReportlist5;
	}

	public boolean isTransferpayReportDtFlag() {
		return transferpayReportDtFlag;
	}

	public void setTransferpayReportDtFlag(boolean transferpayReportDtFlag) {
		this.transferpayReportDtFlag = transferpayReportDtFlag;
	}

	public boolean isPurtransferpayReportDtFlag() {
		return purtransferpayReportDtFlag;
	}

	public void setPurtransferpayReportDtFlag(boolean purtransferpayReportDtFlag) {
		this.purtransferpayReportDtFlag = purtransferpayReportDtFlag;
	}

	public ArrayList<PurchaseReport> getPurpaymentReportlist1() {
		return purpaymentReportlist1;
	}

	public void setPurpaymentReportlist1(
			ArrayList<PurchaseReport> purpaymentReportlist1) {
		this.purpaymentReportlist1 = purpaymentReportlist1;
	}

	public ArrayList<PurchaseReport> getPurpaymentReportlist2() {
		return purpaymentReportlist2;
	}

	public void setPurpaymentReportlist2(
			ArrayList<PurchaseReport> purpaymentReportlist2) {
		this.purpaymentReportlist2 = purpaymentReportlist2;
	}

	public ArrayList<PurchaseReport> getPurpaymentReportlist3() {
		return purpaymentReportlist3;
	}

	public void setPurpaymentReportlist3(
			ArrayList<PurchaseReport> purpaymentReportlist3) {
		this.purpaymentReportlist3 = purpaymentReportlist3;
	}

	public ArrayList<PurchaseReport> getPurpaymentReportlist4() {
		return purpaymentReportlist4;
	}

	public void setPurpaymentReportlist4(
			ArrayList<PurchaseReport> purpaymentReportlist4) {
		this.purpaymentReportlist4 = purpaymentReportlist4;
	}

	public boolean isPurpaymentReportDtFlag() {
		return purpaymentReportDtFlag;
	}

	public void setPurpaymentReportDtFlag(boolean purpaymentReportDtFlag) {
		this.purpaymentReportDtFlag = purpaymentReportDtFlag;
	}

	public boolean isPurcashpaymentReportDtFlag() {
		return purcashpaymentReportDtFlag;
	}

	public void setPurcashpaymentReportDtFlag(boolean purcashpaymentReportDtFlag) {
		this.purcashpaymentReportDtFlag = purcashpaymentReportDtFlag;
	}

	public boolean isPurcardpaymentReportDtFlag() {
		return purcardpaymentReportDtFlag;
	}

	public void setPurcardpaymentReportDtFlag(boolean purcardpaymentReportDtFlag) {
		this.purcardpaymentReportDtFlag = purcardpaymentReportDtFlag;
	}

	public boolean isPurchequepaymentReportDtFlag() {
		return purchequepaymentReportDtFlag;
	}

	public void setPurchequepaymentReportDtFlag(
			boolean purchequepaymentReportDtFlag) {
		this.purchequepaymentReportDtFlag = purchequepaymentReportDtFlag;
	}

	public ArrayList<PurchaseReport> getVendorList() {
		return vendorList;
	}

	public void setVendorList(ArrayList<PurchaseReport> vendorList) {
		this.vendorList = vendorList;
	}

	public ArrayList<String> getVendorList1() {
		return vendorList1;
	}

	public void setVendorList1(ArrayList<String> vendorList1) {
		this.vendorList1 = vendorList1;
	}

	public ArrayList<PurchaseReport> getPaymentReportlist3() {
		return paymentReportlist3;
	}

	public void setPaymentReportlist3(
			ArrayList<PurchaseReport> paymentReportlist3) {
		this.paymentReportlist3 = paymentReportlist3;
	}

	public ArrayList<PurchaseReport> getPaymentReportlist4() {
		return paymentReportlist4;
	}

	public void setPaymentReportlist4(
			ArrayList<PurchaseReport> paymentReportlist4) {
		this.paymentReportlist4 = paymentReportlist4;
	}

	public String getCustomerFlag() {
		return customerFlag;
	}

	public void setCustomerFlag(String customerFlag) {
		this.customerFlag = customerFlag;
	}

	public String getVendorFlag() {
		return vendorFlag;
	}

	public void setVendorFlag(String vendorFlag) {
		this.vendorFlag = vendorFlag;
	}

	public ArrayList<PurchaseReport> getPaymentReportlist2() {
		return paymentReportlist2;
	}

	public void setPaymentReportlist2(
			ArrayList<PurchaseReport> paymentReportlist2) {
		this.paymentReportlist2 = paymentReportlist2;
	}

	public boolean isAllpaymentReportDtFlag() {
		return allpaymentReportDtFlag;
	}

	public void setAllpaymentReportDtFlag(boolean allpaymentReportDtFlag) {
		this.allpaymentReportDtFlag = allpaymentReportDtFlag;
	}

	public boolean isCashpaymentReportDtFlag() {
		return cashpaymentReportDtFlag;
	}

	public void setCashpaymentReportDtFlag(boolean cashpaymentReportDtFlag) {
		this.cashpaymentReportDtFlag = cashpaymentReportDtFlag;
	}

	public boolean isCardpaymentReportDtFlag() {
		return cardpaymentReportDtFlag;
	}

	public void setCardpaymentReportDtFlag(boolean cardpaymentReportDtFlag) {
		this.cardpaymentReportDtFlag = cardpaymentReportDtFlag;
	}

	public boolean isChequepaymentReportDtFlag() {
		return chequepaymentReportDtFlag;
	}

	public void setChequepaymentReportDtFlag(boolean chequepaymentReportDtFlag) {
		this.chequepaymentReportDtFlag = chequepaymentReportDtFlag;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomertype() {
		return customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	public String getVendortype() {
		return vendortype;
	}

	public void setVendortype(String vendortype) {
		this.vendortype = vendortype;
	}

	public boolean isPaymentReportDtFlag() {
		return paymentReportDtFlag;
	}

	public void setPaymentReportDtFlag(boolean paymentReportDtFlag) {
		this.paymentReportDtFlag = paymentReportDtFlag;
	}

	public ArrayList<PurchaseReport> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<PurchaseReport> customerList) {
		this.customerList = customerList;
	}

	public ArrayList<String> getCustomerList1() {
		return customerList1;
	}

	public void setCustomerList1(ArrayList<String> customerList1) {
		this.customerList1 = customerList1;
	}

	public ArrayList<PurchaseReport> getPaymentReportlist1() {
		return paymentReportlist1;
	}

	public void setPaymentReportlist1(
			ArrayList<PurchaseReport> paymentReportlist1) {
		this.paymentReportlist1 = paymentReportlist1;
	}

	public ArrayList<PurchaseReport> getPaymentReportlist() {
		return paymentReportlist;
	}

	public void setPaymentReportlist(ArrayList<PurchaseReport> paymentReportlist) {
		this.paymentReportlist = paymentReportlist;
	}

	public Date getPayFromDate() {
		return payFromDate;
	}

	public void setPayFromDate(Date payFromDate) {
		this.payFromDate = payFromDate;
	}

	public Date getPayToDate() {
		return payToDate;
	}

	public void setPayToDate(Date payToDate) {
		this.payToDate = payToDate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String customerdropdown() {
		try {
			customerList1 = new ArrayList<String>();
			logger.debug("inside customer name dropdown method calling");
			customerList = ReportJDBC.customerNameList();
			logger.debug("customer list1 size" + customerList.size());
			ArrayList<String> customers = new ArrayList<String>();
			for (int i = 0; i < customerList.size(); i++) {
				customers.add(customerList.get(i).getCustomerName());
			}
			HashSet<String> cust = new HashSet<String>(customers);
			customers.clear();
			customers.addAll(cust);
			customerList1.addAll(customers);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";
	}

	public String vendordropdown() {
		try {
			vendorList1 = new ArrayList<String>();
			logger.debug("inside customer name dropdown method calling");
			vendorList = ReportJDBC.vendorNameList();
			logger.debug("vendor list1 size" + vendorList.size());
			ArrayList<String> vendors = new ArrayList<String>();
			for (int i = 0; i < vendorList.size(); i++) {
				vendors.add(vendorList.get(i).getVendorName());
			}
			HashSet<String> ven = new HashSet<String>(vendors);
			vendors.clear();
			vendors.addAll(ven);
			vendorList1.addAll(vendors);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";
	}

	public void valueChange1(ValueChangeEvent ve) {
		String s = ve.getNewValue().toString();
		if (s.equalsIgnoreCase("Purchase")) {
			vendorFlag = "1";
			customerFlag = "none";
			setNorecordFlag(false);
		} else if (s.equalsIgnoreCase("Sales")) {
			vendorFlag = "none";
			customerFlag = "1";
			setNorecordFlag(false);
		}
	}

	public void valueChange2(ValueChangeEvent ve) {
		String s = ve.getNewValue().toString();
		if (s.equalsIgnoreCase("select")) {
			setNorecordFlag(false);
		} else if (s.equalsIgnoreCase("All")) {
			setNorecordFlag(false);
		} else {
			setNorecordFlag(false);
		}
	}

	public void valueChange3(ValueChangeEvent ve) {
		String s = ve.getNewValue().toString();
		if (s.equalsIgnoreCase("select")) {
			setNorecordFlag(false);
		} else if (s.equalsIgnoreCase("All")) {
			setNorecordFlag(false);
		} else {
			setNorecordFlag(false);
		}
	}

	public void valueChange4(ValueChangeEvent ve) {
		String s = ve.getNewValue().toString();
		if (s.equalsIgnoreCase("select")) {
			setNorecordFlag(false);
		} else if (s.equalsIgnoreCase("All")) {
			setNorecordFlag(false);
		} else if (s.equalsIgnoreCase("Cash")) {
			setNorecordFlag(false);
		} else if (s.equalsIgnoreCase("Card")) {
			setNorecordFlag(false);
		} else if (s.equalsIgnoreCase("Cheque")) {
			setNorecordFlag(false);
		} else if (s.equalsIgnoreCase("Transfer")) {
			setNorecordFlag(false);
		}
	}

	public String setelseflagmethod() {
		setNorecordFlag(true);
		setPaymentReportDtFlag(false);
		setCashpaymentReportDtFlag(false);
		setCardpaymentReportDtFlag(false);
		setChequepaymentReportDtFlag(false);
		setTransferpayReportDtFlag(false);
		setPurchequepaymentReportDtFlag(false);
		setPurcardpaymentReportDtFlag(false);
		setPurcashpaymentReportDtFlag(false);
		setPurpaymentReportDtFlag(false);
		setPurtransferpayReportDtFlag(false);
		return "";
	}

	public String searchPaymentReport() throws SQLException {
		logger.debug("inside search payment report method calling");
		setValidate(null);
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			if (payFromDate == null) {
				fieldName = CommonValidate.findComponentInRoot("fdate")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Choose the  From Date."));
			}
			if (payToDate == null) {
				fieldName = CommonValidate.findComponentInRoot("tdate")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Choose the  To Date."));
			}
			if (paymentType.equalsIgnoreCase("---Select---")) {
				fieldName = CommonValidate.findComponentInRoot("av1")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please choose payment type"));
			}
			if (type.equalsIgnoreCase("---Select---")) {
				fieldName = CommonValidate.findComponentInRoot("av2")
						.getClientId(fc);
				fc.addMessage(fieldName,
						new FacesMessage("Please choose  type"));
			}

			else if (type.equalsIgnoreCase("Sales")) {

				if (customertype.equalsIgnoreCase("---Select---")) {
					fieldName = CommonValidate.findComponentInRoot("c1")
							.getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage(
							"Please choose  the Customer Name"));
				} else {
					logger.debug("inside sales");
					setNorecordFlag(false);
					if (paymentType.equalsIgnoreCase("All")) {
						setNorecordFlag(false);
						paymentReportlist = ReportJDBC
								.paymentreportsearch(paymentType, type,
										payFromDate, payToDate, customertype,
										vendortype);
						logger.debug("list size" + paymentReportlist.size());
						logger.debug("customer type" + customertype);
						paymentReportlist1 = new ArrayList<PurchaseReport>();
						for (int i = 0; i < paymentReportlist.size(); i++) {
							PurchaseReport pur = new PurchaseReport();
							pur.setPaymentDate(paymentReportlist.get(i)
									.getPaymentDate());
							pur.setCustomerName(paymentReportlist.get(i)
									.getCustomerName());
							pur.setCustPhoneNumber(paymentReportlist.get(i)
									.getCustPhoneNumber());
							pur.setCrossTotal(paymentReportlist.get(i)
									.getCrossTotal());
							pur.setPaymentStatus2(paymentReportlist.get(i)
									.getPaymentStatus2());
							pur.setPayableAmount(paymentReportlist.get(i)
									.getPayableAmount());
							pur.setBalanceAmount(paymentReportlist.get(i)
									.getBalanceAmount());
							pur.setPaymentType(paymentReportlist.get(i)
									.getPaymentType());
							paymentReportlist1.add(pur);

						}
						if (paymentReportlist1.size() > 0) {
							setNorecordFlag(false);
							setPaymentReportDtFlag(true);
							setCashpaymentReportDtFlag(false);
							setCardpaymentReportDtFlag(false);
							setChequepaymentReportDtFlag(false);
							setPurchequepaymentReportDtFlag(false);
							setPurcardpaymentReportDtFlag(false);
							setPurcashpaymentReportDtFlag(false);
							setPurpaymentReportDtFlag(false);
							setTransferpayReportDtFlag(false);
							setPurtransferpayReportDtFlag(false);

						} else {
							setelseflagmethod();
						}

					} else if (paymentType.equalsIgnoreCase("Cash")) {
						paymentReportlist = ReportJDBC
								.paymentreportsearch(paymentType, type,
										payFromDate, payToDate, customertype,
										vendortype);
						logger.debug("list size" + paymentReportlist.size());
						paymentReportlist2 = new ArrayList<PurchaseReport>();
						for (int i = 0; i < paymentReportlist.size(); i++) {
							PurchaseReport pur = new PurchaseReport();
							pur.setPaymentDate(paymentReportlist.get(i)
									.getPaymentDate());
							pur.setCustomerName(paymentReportlist.get(i)
									.getCustomerName());
							pur.setCustPhoneNumber(paymentReportlist.get(i)
									.getCustPhoneNumber());
							pur.setCrossTotal(paymentReportlist.get(i)
									.getCrossTotal());
							pur.setPaymentStatus2(paymentReportlist.get(i)
									.getPaymentStatus2());
							pur.setPayableAmount(paymentReportlist.get(i)
									.getPayableAmount());
							pur.setBalanceAmount(paymentReportlist.get(i)
									.getBalanceAmount());
							paymentReportlist2.add(pur);
						}
						if (paymentReportlist2.size() > 0) {
							setCashpaymentReportDtFlag(true);
							setNorecordFlag(false);
							setPaymentReportDtFlag(false);
							setChequepaymentReportDtFlag(false);
							setCardpaymentReportDtFlag(false);
							setPurchequepaymentReportDtFlag(false);
							setPurcardpaymentReportDtFlag(false);
							setPurcashpaymentReportDtFlag(false);
							setPurpaymentReportDtFlag(false);
							setTransferpayReportDtFlag(false);
							setPurtransferpayReportDtFlag(false);
							setPurtransferpayReportDtFlag(false);
						} else {
							setelseflagmethod();
						}
					} else if (paymentType.equalsIgnoreCase("Card")) {
						paymentReportlist = ReportJDBC
								.paymentreportsearch(paymentType, type,
										payFromDate, payToDate, customertype,
										vendortype);
						logger.debug("list size" + paymentReportlist.size());
						paymentReportlist3 = new ArrayList<PurchaseReport>();
						for (int i = 0; i < paymentReportlist.size(); i++) {
							PurchaseReport pur = new PurchaseReport();
							pur.setPaymentDate(paymentReportlist.get(i)
									.getPaymentDate());
							pur.setCustomerName(paymentReportlist.get(i)
									.getCustomerName());
							pur.setCustPhoneNumber(paymentReportlist.get(i)
									.getCustPhoneNumber());
							pur.setCrossTotal(paymentReportlist.get(i)
									.getCrossTotal());
							pur.setPaymentStatus2(paymentReportlist.get(i)
									.getPaymentStatus2());
							pur.setPayableAmount(paymentReportlist.get(i)
									.getPayableAmount());
							pur.setBalanceAmount(paymentReportlist.get(i)
									.getBalanceAmount());
							pur.setBankName(paymentReportlist.get(i)
									.getBankName());
							pur.setCardNumber(paymentReportlist.get(i)
									.getCardNumber());
							paymentReportlist3.add(pur);
						}
						if (paymentReportlist3.size() > 0) {
							setCashpaymentReportDtFlag(false);
							setNorecordFlag(false);
							setPaymentReportDtFlag(false);
							setCardpaymentReportDtFlag(true);
							setChequepaymentReportDtFlag(false);
							setPurchequepaymentReportDtFlag(false);
							setPurcardpaymentReportDtFlag(false);
							setPurcashpaymentReportDtFlag(false);
							setPurpaymentReportDtFlag(false);
							setTransferpayReportDtFlag(false);
							setPurtransferpayReportDtFlag(false);
							setPurtransferpayReportDtFlag(false);
						} else {
							setelseflagmethod();
						}
					} else if (paymentType.equalsIgnoreCase("Cheque")) {
						paymentReportlist = ReportJDBC
								.paymentreportsearch(paymentType, type,
										payFromDate, payToDate, customertype,
										vendortype);
						logger.debug("list size" + paymentReportlist.size());
						paymentReportlist4 = new ArrayList<PurchaseReport>();
						for (int i = 0; i < paymentReportlist.size(); i++) {
							PurchaseReport pur = new PurchaseReport();
							pur.setPaymentDate(paymentReportlist.get(i)
									.getPaymentDate());
							pur.setCustomerName(paymentReportlist.get(i)
									.getCustomerName());
							pur.setCustPhoneNumber(paymentReportlist.get(i)
									.getCustPhoneNumber());
							pur.setCrossTotal(paymentReportlist.get(i)
									.getCrossTotal());
							pur.setPaymentStatus2(paymentReportlist.get(i)
									.getPaymentStatus2());
							pur.setPayableAmount(paymentReportlist.get(i)
									.getPayableAmount());
							pur.setBalanceAmount(paymentReportlist.get(i)
									.getBalanceAmount());
							pur.setBankName(paymentReportlist.get(i)
									.getBankName());
							pur.setChequeNumber(paymentReportlist.get(i)
									.getChequeNumber());
							pur.setChequeDate(paymentReportlist.get(i)
									.getChequeDate());
							paymentReportlist4.add(pur);
						}
						if (paymentReportlist4.size() > 0) {
							setCashpaymentReportDtFlag(false);
							setNorecordFlag(false);
							setPaymentReportDtFlag(false);
							setCardpaymentReportDtFlag(false);
							setChequepaymentReportDtFlag(true);
							setPurchequepaymentReportDtFlag(false);
							setPurcardpaymentReportDtFlag(false);
							setPurcashpaymentReportDtFlag(false);
							setPurpaymentReportDtFlag(false);
							setTransferpayReportDtFlag(false);
							setPurtransferpayReportDtFlag(false);
						} else {
							setelseflagmethod();
						}
					} else if (paymentType.equalsIgnoreCase("Transfer")) {
						paymentReportlist = ReportJDBC
								.paymentreportsearch(paymentType, type,
										payFromDate, payToDate, customertype,
										vendortype);
						logger.debug("list size" + paymentReportlist.size());
						paymentReportlist5 = new ArrayList<PurchaseReport>();
						for (int i = 0; i < paymentReportlist.size(); i++) {
							PurchaseReport pur = new PurchaseReport();
							pur.setPaymentDate(paymentReportlist.get(i)
									.getPaymentDate());
							pur.setCustomerName(paymentReportlist.get(i)
									.getCustomerName());
							pur.setCustPhoneNumber(paymentReportlist.get(i)
									.getCustPhoneNumber());
							pur.setCrossTotal(paymentReportlist.get(i)
									.getCrossTotal());
							pur.setPaymentStatus2(paymentReportlist.get(i)
									.getPaymentStatus2());
							pur.setPayableAmount(paymentReportlist.get(i)
									.getPayableAmount());
							pur.setBalanceAmount(paymentReportlist.get(i)
									.getBalanceAmount());
							pur.setBankName(paymentReportlist.get(i)
									.getBankName());
							pur.setAccountNumber(paymentReportlist.get(i)
									.getAccountNumber());
							paymentReportlist5.add(pur);
						}
						if (paymentReportlist5.size() > 0) {
							setCashpaymentReportDtFlag(false);
							setNorecordFlag(false);
							setPaymentReportDtFlag(false);
							setCardpaymentReportDtFlag(false);
							setChequepaymentReportDtFlag(false);
							setPurchequepaymentReportDtFlag(false);
							setPurcardpaymentReportDtFlag(false);
							setPurcashpaymentReportDtFlag(false);
							setPurpaymentReportDtFlag(false);
							setTransferpayReportDtFlag(true);
							setPurtransferpayReportDtFlag(false);
						} else {
							setelseflagmethod();
						}
					}
				}
			} else if (type.equalsIgnoreCase("Purchase")) {

				if (vendortype.equalsIgnoreCase("---Select---")) {
					fieldName = CommonValidate.findComponentInRoot("v1")
							.getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage(
							"Please choose  the Vendor Name"));
				}

				else {
					logger.debug("inside purchase");
					if (paymentType.equalsIgnoreCase("All")) {
						setNorecordFlag(false);
						paymentReportlist = ReportJDBC
								.paymentreportsearch(paymentType, type,
										payFromDate, payToDate, customertype,
										vendortype);
						logger.debug("list size" + paymentReportlist.size());
						logger.debug("vendor type" + vendortype);
						purpaymentReportlist1 = new ArrayList<PurchaseReport>();
						for (int i = 0; i < paymentReportlist.size(); i++) {
							PurchaseReport pur = new PurchaseReport();
							pur.setPaymentDate(paymentReportlist.get(i)
									.getPaymentDate());
							pur.setVendorName(paymentReportlist.get(i)
									.getVendorName());
							pur.setVendorPhoneNumber(paymentReportlist.get(i)
									.getVendorPhoneNumber());
							pur.setCrossTotal(paymentReportlist.get(i)
									.getCrossTotal());
							pur.setPaymentStatus2(paymentReportlist.get(i)
									.getPaymentStatus2());
							pur.setPayableAmount(paymentReportlist.get(i)
									.getPayableAmount());
							pur.setBalanceAmount(paymentReportlist.get(i)
									.getBalanceAmount());
							pur.setPaymentType(paymentReportlist.get(i)
									.getPaymentType());
							purpaymentReportlist1.add(pur);

						}
						if (purpaymentReportlist1.size() > 0) {
							setNorecordFlag(false);
							setPaymentReportDtFlag(false);
							setCashpaymentReportDtFlag(false);
							setCardpaymentReportDtFlag(false);
							setChequepaymentReportDtFlag(false);
							setPurpaymentReportDtFlag(true);
							setPurcashpaymentReportDtFlag(false);
							setPurcardpaymentReportDtFlag(false);
							setPurchequepaymentReportDtFlag(false);
							setPurtransferpayReportDtFlag(false);
							setTransferpayReportDtFlag(false);
						} else {
							setelseflagmethod();
						}

					} else if (paymentType.equalsIgnoreCase("Cash")) {
						paymentReportlist = ReportJDBC
								.paymentreportsearch(paymentType, type,
										payFromDate, payToDate, customertype,
										vendortype);
						logger.debug("list size" + paymentReportlist.size());
						setNorecordFlag(false);
						purpaymentReportlist2 = new ArrayList<PurchaseReport>();
						for (int i = 0; i < paymentReportlist.size(); i++) {
							PurchaseReport pur = new PurchaseReport();
							pur.setPaymentDate(paymentReportlist.get(i)
									.getPaymentDate());
							pur.setVendorName(paymentReportlist.get(i)
									.getVendorName());
							pur.setVendorPhoneNumber(paymentReportlist.get(i)
									.getVendorPhoneNumber());
							pur.setCrossTotal(paymentReportlist.get(i)
									.getCrossTotal());
							pur.setPaymentStatus2(paymentReportlist.get(i)
									.getPaymentStatus2());
							pur.setPayableAmount(paymentReportlist.get(i)
									.getPayableAmount());
							pur.setBalanceAmount(paymentReportlist.get(i)
									.getBalanceAmount());
							purpaymentReportlist2.add(pur);
						}
						if (purpaymentReportlist2.size() > 0) {
							setCashpaymentReportDtFlag(false);
							setNorecordFlag(false);
							setPaymentReportDtFlag(false);
							setChequepaymentReportDtFlag(false);
							setCardpaymentReportDtFlag(false);
							setPurcashpaymentReportDtFlag(true);
							setPurchequepaymentReportDtFlag(false);
							setPurcardpaymentReportDtFlag(false);
							setPurpaymentReportDtFlag(false);
							setPurtransferpayReportDtFlag(false);
							setTransferpayReportDtFlag(false);
						} else {
							setelseflagmethod();
						}
					} else if (paymentType.equalsIgnoreCase("Card")) {
						paymentReportlist = ReportJDBC
								.paymentreportsearch(paymentType, type,
										payFromDate, payToDate, customertype,
										vendortype);
						logger.debug("list size" + paymentReportlist.size());
						setNorecordFlag(false);
						purpaymentReportlist3 = new ArrayList<PurchaseReport>();
						for (int i = 0; i < paymentReportlist.size(); i++) {
							PurchaseReport pur = new PurchaseReport();
							pur.setPaymentDate(paymentReportlist.get(i)
									.getPaymentDate());
							pur.setVendorName(paymentReportlist.get(i)
									.getVendorName());
							pur.setVendorPhoneNumber(paymentReportlist.get(i)
									.getVendorPhoneNumber());
							pur.setCrossTotal(paymentReportlist.get(i)
									.getCrossTotal());
							pur.setPaymentStatus2(paymentReportlist.get(i)
									.getPaymentStatus2());
							pur.setPayableAmount(paymentReportlist.get(i)
									.getPayableAmount());
							pur.setBalanceAmount(paymentReportlist.get(i)
									.getBalanceAmount());
							pur.setBankName(paymentReportlist.get(i)
									.getBankName());
							pur.setCardNumber(paymentReportlist.get(i)
									.getCardNumber());
							purpaymentReportlist3.add(pur);
						}
						if (purpaymentReportlist3.size() > 0) {
							setCashpaymentReportDtFlag(false);
							setNorecordFlag(false);
							setPaymentReportDtFlag(false);
							setCardpaymentReportDtFlag(false);
							setChequepaymentReportDtFlag(false);
							setPurcardpaymentReportDtFlag(true);
							setPurcashpaymentReportDtFlag(false);
							setPurchequepaymentReportDtFlag(false);
							setPurpaymentReportDtFlag(false);
							setPurtransferpayReportDtFlag(false);
							setTransferpayReportDtFlag(false);
						} else {
							setelseflagmethod();
						}
					} else if (paymentType.equalsIgnoreCase("Cheque")) {
						paymentReportlist = ReportJDBC
								.paymentreportsearch(paymentType, type,
										payFromDate, payToDate, customertype,
										vendortype);
						logger.debug("list size" + paymentReportlist.size());
						setNorecordFlag(false);
						purpaymentReportlist4 = new ArrayList<PurchaseReport>();
						for (int i = 0; i < paymentReportlist.size(); i++) {
							PurchaseReport pur = new PurchaseReport();
							pur.setPaymentDate(paymentReportlist.get(i)
									.getPaymentDate());
							pur.setVendorName(paymentReportlist.get(i)
									.getVendorName());
							pur.setVendorPhoneNumber(paymentReportlist.get(i)
									.getVendorPhoneNumber());
							pur.setCrossTotal(paymentReportlist.get(i)
									.getCrossTotal());
							pur.setPaymentStatus2(paymentReportlist.get(i)
									.getPaymentStatus2());
							pur.setPayableAmount(paymentReportlist.get(i)
									.getPayableAmount());
							pur.setBalanceAmount(paymentReportlist.get(i)
									.getBalanceAmount());
							pur.setBankName(paymentReportlist.get(i)
									.getBankName());
							pur.setChequeNumber(paymentReportlist.get(i)
									.getChequeNumber());
							pur.setChequeDate(paymentReportlist.get(i)
									.getChequeDate());
							purpaymentReportlist4.add(pur);
						}
						if (purpaymentReportlist4.size() > 0) {
							setCashpaymentReportDtFlag(false);
							setNorecordFlag(false);
							setPaymentReportDtFlag(false);
							setCardpaymentReportDtFlag(false);
							setChequepaymentReportDtFlag(false);
							setPurchequepaymentReportDtFlag(true);
							setPurcardpaymentReportDtFlag(false);
							setPurcashpaymentReportDtFlag(false);
							setPurpaymentReportDtFlag(false);
							setPurtransferpayReportDtFlag(false);
							setTransferpayReportDtFlag(false);
						} else {
							setelseflagmethod();
						}
					} else if (paymentType.equalsIgnoreCase("Transfer")) {
						paymentReportlist = ReportJDBC
								.paymentreportsearch(paymentType, type,
										payFromDate, payToDate, customertype,
										vendortype);
						logger.debug("list size" + paymentReportlist.size());
						setNorecordFlag(false);
						purpaymentReportlist5 = new ArrayList<PurchaseReport>();
						for (int i = 0; i < paymentReportlist.size(); i++) {
							PurchaseReport pur = new PurchaseReport();
							pur.setPaymentDate(paymentReportlist.get(i)
									.getPaymentDate());
							pur.setVendorName(paymentReportlist.get(i)
									.getVendorName());
							pur.setVendorPhoneNumber(paymentReportlist.get(i)
									.getVendorPhoneNumber());
							pur.setCrossTotal(paymentReportlist.get(i)
									.getCrossTotal());
							pur.setPaymentStatus2(paymentReportlist.get(i)
									.getPaymentStatus2());
							pur.setPayableAmount(paymentReportlist.get(i)
									.getPayableAmount());
							pur.setBalanceAmount(paymentReportlist.get(i)
									.getBalanceAmount());
							pur.setBankName(paymentReportlist.get(i)
									.getBankName());
							pur.setAccountNumber(paymentReportlist.get(i)
									.getAccountNumber());
							purpaymentReportlist5.add(pur);
						}
						if (purpaymentReportlist5.size() > 0) {
							setCashpaymentReportDtFlag(false);
							setNorecordFlag(false);
							setPaymentReportDtFlag(false);
							setCardpaymentReportDtFlag(false);
							setChequepaymentReportDtFlag(false);
							setPurchequepaymentReportDtFlag(false);
							setPurcardpaymentReportDtFlag(false);
							setPurcashpaymentReportDtFlag(false);
							setPurpaymentReportDtFlag(false);
							setPurtransferpayReportDtFlag(true);
							setTransferpayReportDtFlag(false);
						} else {
							setelseflagmethod();
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
		}
		return "";
	}

}
