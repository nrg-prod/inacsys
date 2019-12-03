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
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.inacsys.domain.PurchaseReport;
import com.inacsys.domain.SalesReport;
import com.inacsys.exception.DemoException;
import com.inacsys.util.CommonValidate;
import com.inacsys.util.SalesReportJDBC;

@ManagedBean(name = "salesReportMB")
public class SalesReportMB {
	private static Logger logger = Logger.getLogger(SalesReportMB.class);
	public String validate;

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public Date salesFromDate;
	public Date salesToDate;
	public String CustomerName;
	public String ALLcustomerName;
	public String singleCustomerFlag;
	public String multipleCustomerFlag;
	public String customerType;
	public String repornew;
	public String totalamount;
	public String returnstatus;

	public String getReturnstatus() {
		return returnstatus;
	}

	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	private boolean reportSuccessFlag = false;

	public boolean salesdetailFlag = false;
	public boolean reportSuccessFlag1 = false;
	public boolean norecordflag = false;
	public boolean salesdetailFlag1 = false;
	public boolean salesdeliveredFlag = false;
	public boolean salesreturnedFlag = false;
	public boolean salesreturndetailFlag = false;

	public boolean isSalesreturndetailFlag() {
		return salesreturndetailFlag;
	}

	public void setSalesreturndetailFlag(boolean salesreturndetailFlag) {
		this.salesreturndetailFlag = salesreturndetailFlag;
	}

	public boolean isSalesreturnedFlag() {
		return salesreturnedFlag;
	}

	public void setSalesreturnedFlag(boolean salesreturnedFlag) {
		this.salesreturnedFlag = salesreturnedFlag;
	}

	public boolean isSalesdeliveredFlag() {
		return salesdeliveredFlag;
	}

	public void setSalesdeliveredFlag(boolean salesdeliveredFlag) {
		this.salesdeliveredFlag = salesdeliveredFlag;
	}

	public boolean isSalesdetailFlag1() {
		return salesdetailFlag1;
	}

	public void setSalesdetailFlag1(boolean salesdetailFlag1) {
		this.salesdetailFlag1 = salesdetailFlag1;
	}

	public boolean isNorecordflag() {
		return norecordflag;
	}

	public void setNorecordflag(boolean norecordflag) {
		this.norecordflag = norecordflag;
	}

	public boolean isReportSuccessFlag1() {
		return reportSuccessFlag1;
	}

	public void setReportSuccessFlag1(boolean reportSuccessFlag1) {
		this.reportSuccessFlag1 = reportSuccessFlag1;
	}

	public boolean isSalesdetailFlag() {
		return salesdetailFlag;
	}

	public void setSalesdetailFlag(boolean salesdetailFlag) {
		this.salesdetailFlag = salesdetailFlag;
	}

	public boolean isReportSuccessFlag() {
		return reportSuccessFlag;
	}

	public void setReportSuccessFlag(boolean reportSuccessFlag) {
		this.reportSuccessFlag = reportSuccessFlag;
	}

	public ArrayList<SalesReport> salesReportlist = null;
	public ArrayList<SalesReport> salesReportlist1 = null;
	public ArrayList<SalesReport> salesReportlist2 = null;
	public ArrayList<SalesReport> salesReportList3 = null;
	public ArrayList<SalesReport> salesReportList5 = null;
	public ArrayList<SalesReport> salesReportList6 = null;

	public ArrayList<SalesReport> salesReportList7 = null;

	public ArrayList<SalesReport> getSalesReportList7() {
		return salesReportList7;
	}

	public void setSalesReportList7(ArrayList<SalesReport> salesReportList7) {
		this.salesReportList7 = salesReportList7;
	}

	public ArrayList<SalesReport> getSalesReportList6() {
		return salesReportList6;
	}

	public void setSalesReportList6(ArrayList<SalesReport> salesReportList6) {
		this.salesReportList6 = salesReportList6;
	}

	public ArrayList<SalesReport> getSalesReportList5() {
		return salesReportList5;
	}

	public void setSalesReportList5(ArrayList<SalesReport> salesReportList5) {
		this.salesReportList5 = salesReportList5;
	}

	public ArrayList<SalesReport> salesReportList4 = new ArrayList<SalesReport>();

	public ArrayList<SalesReport> getSalesReportList3() {
		return salesReportList3;
	}

	public void setSalesReportList3(ArrayList<SalesReport> salesReportList3) {
		this.salesReportList3 = salesReportList3;
	}

	public ArrayList<SalesReport> getSalesReportList4() {
		return salesReportList4;
	}

	public void setSalesReportList4(ArrayList<SalesReport> salesReportList4) {
		this.salesReportList4 = salesReportList4;
	}

	public ArrayList<SalesReport> getSalesReportlist2() {
		return salesReportlist2;
	}

	public void setSalesReportlist2(ArrayList<SalesReport> salesReportlist2) {
		this.salesReportlist2 = salesReportlist2;
	}

	public ArrayList<SalesReport> getSalesReportlist1() {
		return salesReportlist1;
	}

	public void setSalesReportlist1(ArrayList<SalesReport> salesReportlist1) {
		this.salesReportlist1 = salesReportlist1;
	}

	public ArrayList<SalesReport> getSalesReportlist() {
		return salesReportlist;
	}

	public void setSalesReportlist(ArrayList<SalesReport> salesReportlist) {
		this.salesReportlist = salesReportlist;
	}

	public String getRepornew() {
		return repornew;
	}

	public void setRepornew(String repornew) {
		this.repornew = repornew;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public Date getSalesFromDate() {
		return salesFromDate;
	}

	public void setSalesFromDate(Date salesFromDate) {
		this.salesFromDate = salesFromDate;
	}

	public Date getSalesToDate() {
		return salesToDate;
	}

	public void setSalesToDate(Date salesToDate) {
		this.salesToDate = salesToDate;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getALLcustomerName() {
		return ALLcustomerName;
	}

	public void setALLcustomerName(String aLLcustomerName) {
		ALLcustomerName = aLLcustomerName;
	}

	public String getSingleCustomerFlag() {
		return singleCustomerFlag;
	}

	public void setSingleCustomerFlag(String singleCustomerFlag) {
		this.singleCustomerFlag = singleCustomerFlag;
	}

	public String getMultipleCustomerFlag() {
		return multipleCustomerFlag;
	}

	public void setMultipleCustomerFlag(String multipleCustomerFlag) {
		this.multipleCustomerFlag = multipleCustomerFlag;
	}

	public void salesreport() {
		logger.info("[salesreport()]-------------------inside salesreport() method()---------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("sales-report", options,
				null);
		try {
			setValidate(null);
			setSalesFromDate(null);
			setSalesToDate(null);
			setALLcustomerName(null);
			setRepornew(null);
			setCustomerType(null);
			singleCustomerFlag = "none";
			multipleCustomerFlag = "none";
			setReportSuccessFlag(false);
			setSalesdetailFlag(false);
			setReportSuccessFlag1(false);
			setSalesdetailFlag1(false);
			setNorecordflag(false);
			setSalesdeliveredFlag(false);
			setSalesreturnedFlag(false);
			setSalesreturndetailFlag(false);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

	}

	public void valueChange(ValueChangeEvent vc) {
		logger.info("[valueChange()]-------------------inside valueChange() method()---------------");
		String vtype = vc.getNewValue().toString();
		if (vtype.equalsIgnoreCase("single")) {
			singleCustomerFlag = "1";
			multipleCustomerFlag = "none";
		} else if (vtype.equals("multiple")) {
			singleCustomerFlag = "none";
			multipleCustomerFlag = "1";
		}
	}

	public String searchReport() {
		logger.info("[searchReport()]-------------------inside searchReport() method()---------------");
		setValidate(null);
		salesReportlist = null;
		salesReportlist1 = null;
		salesReportlist2 = null;
		salesReportList3 = null;
		salesReportList4 = null;
		salesReportList5 = null;
		salesReportList6 = null;
		salesReportList7 = null;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			if (salesFromDate == null) {
				fieldName = CommonValidate.findComponentInRoot("fdate")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Choose the  From Date."));
			}
			if (salesToDate == null) {
				fieldName = CommonValidate.findComponentInRoot("tdate")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Choose the  To Date."));
			}
			if (StringUtils.isBlank(customerType)) {
				fieldName = CommonValidate.findComponentInRoot("vt")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Select Customer type"));
			}
			if (customerType.equalsIgnoreCase("single")) {
				if (repornew.equalsIgnoreCase("")) {
					fieldName = CommonValidate.findComponentInRoot("v1")
							.getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage(
							"Please Enter the Customer Name"));

				} else if (!repornew.equalsIgnoreCase("")) {
					salesReportlist = SalesReportJDBC.customerNameSearch(
							customerType, ALLcustomerName, repornew,
							salesFromDate, salesToDate);
					salesReportlist1 = new ArrayList<SalesReport>();
					if (salesReportlist.size() > 0) {
						for (int i = 0; i < salesReportlist.size(); i++) {
							SalesReport sale = new SalesReport();
							sale.setCustomerName(salesReportlist.get(i)
									.getCustomerName());
							sale.setOrderdate(salesReportlist.get(i)
									.getOrderdate());
							sale.setOrderNumber(salesReportlist.get(i)
									.getOrderNumber());
							sale.setPhonenumber(salesReportlist.get(i)
									.getPhonenumber());
							sale.setQuantity(salesReportlist.get(i)
									.getQuantity());
							sale.setSellprice(salesReportlist.get(i)
									.getSellprice());
							sale.setStatus(salesReportlist.get(i).getStatus());
							salesReportlist1.add(sale);
						}
						if (salesReportlist1.size() > 0) {
							setSalesdetailFlag(true);
							setNorecordflag(false);
						}
					} else {
						setSalesdetailFlag(false);
						setNorecordflag(true);
					}

				}
			} else if (customerType.equalsIgnoreCase("multiple")) {
				if (ALLcustomerName.equalsIgnoreCase("--- Select ---")) {
					fieldName = CommonValidate.findComponentInRoot("av1")
							.getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage(
							"Please Choose the Vendor Name"));
				} else if (ALLcustomerName.equalsIgnoreCase("All")) {
					if (ALLcustomerName.equalsIgnoreCase("All")) {
						salesReportlist = SalesReportJDBC.customerNameSearch(
								customerType, ALLcustomerName, repornew,
								salesFromDate, salesToDate);
						salesReportList3 = new ArrayList<SalesReport>();
						ArrayList<String> customers = new ArrayList<String>();
						ArrayList<String> phones = new ArrayList<String>();
						for (int i = 0; i < salesReportlist.size(); i++) {
							customers.add(salesReportlist.get(i)
									.getCustomerName());
							phones.add(salesReportlist.get(i).getPhonenumber());
						}
						HashSet<String> customer = new HashSet<String>(
								customers);
						HashSet<String> phone = new HashSet<String>(phones);
						customers.clear();
						customers.addAll(customer);
						phones.clear();
						phones.addAll(phone);
						int k = 0;
						for (int i = 0; i < customers.size(); i++) {
							BigDecimal totprice = BigDecimal.valueOf(0);
							BigDecimal quan = BigDecimal.valueOf(0);
							for (int j = 0; j < salesReportlist.size(); j++) {
								if (salesReportlist.get(j).getCustomerName()
										.equals(customers.get(i))) {
									quan = quan.add(new BigDecimal(
											salesReportlist.get(j)
													.getQuantity()));
									totprice = totprice.add(new BigDecimal(
											salesReportlist.get(j)
													.getSellprice())
											.multiply(new BigDecimal(
													salesReportlist.get(j)
															.getQuantity())));
								}

							}
							SalesReport sale = new SalesReport();
							sale.setCustomerName(customers.get(i));
							sale.setPhonenumber(salesReportlist.get(k)
									.getPhonenumber());
							sale.setQuantity("" + quan);
							sale.setSellprice("" + totprice);
							salesReportList3.add(sale);
							k++;
						}
						if (salesReportlist.size() > 0) {
							setReportSuccessFlag1(true);
							setNorecordflag(false);
							setSalesdeliveredFlag(false);
							setSalesreturnedFlag(false);
							setSalesdetailFlag(false);

						} else {

							setReportSuccessFlag1(false);
							setNorecordflag(true);
							setSalesdeliveredFlag(false);
							setSalesreturnedFlag(false);
						}

					} else if (ALLcustomerName
							.equalsIgnoreCase("Sales Delivery")) {
						salesReportlist = SalesReportJDBC.customerNameSearch(
								customerType, ALLcustomerName, repornew,
								salesFromDate, salesToDate);
						salesReportList5 = new ArrayList<SalesReport>();
						for (int i = 0; i < salesReportlist.size(); i++) {
							SalesReport sale = new SalesReport();
							sale.setOrderNumber(salesReportlist.get(i)
									.getOrderNumber());
							sale.setProductName(salesReportlist.get(i)
									.getProductName());
							sale.setQuantity(salesReportlist.get(i)
									.getQuantity());
							sale.setSellprice(salesReportlist.get(i)
									.getSellprice());
							sale.setDeliverystatus(salesReportlist.get(i)
									.getDeliverystatus());
							sale.setCustomerName(salesReportlist.get(i)
									.getCustomerName());
							sale.setPhonenumber(salesReportlist.get(i)
									.getPhonenumber());
							salesReportList5.add(sale);
						}
						if (salesReportList5.size() > 0) {
							setSalesdeliveredFlag(true);
							setNorecordflag(false);
							setSalesreturnedFlag(false);
							setReportSuccessFlag1(false);

						} else {
							setSalesdeliveredFlag(false);
							setSalesreturnedFlag(false);
							setNorecordflag(true);
						}
					} else if (ALLcustomerName.equalsIgnoreCase("Sales Return")) {
						salesReportlist = SalesReportJDBC.customerNameSearch(
								customerType, ALLcustomerName, repornew,
								salesFromDate, salesToDate);
						salesReportList6 = new ArrayList<SalesReport>();
						ArrayList<String> ponumbers = new ArrayList<String>();
						for (int i = 0; i < salesReportlist.size(); i++) {
							ponumbers.add(salesReportlist.get(i)
									.getOrderNumber());
						}
						HashSet<String> ponumber = new HashSet<String>(
								ponumbers);
						ponumbers.clear();
						ponumbers.addAll(ponumber);
						Collections.sort(ponumbers);

						for (int i = 0; i < ponumber.size(); i++) {
							BigDecimal quan = BigDecimal.valueOf(0);
							BigDecimal nr = BigDecimal.valueOf(0);
							BigDecimal dr = BigDecimal.valueOf(0);
							for (int j = 0; j < salesReportlist.size(); j++) {
								if (salesReportlist.get(j).getOrderNumber()
										.equalsIgnoreCase(ponumbers.get(i))) {
									quan = new BigDecimal(salesReportlist
											.get(j).getQuantity());
									nr = nr.add(new BigDecimal(salesReportlist
											.get(j).getNormalreturn()));
									dr = dr.add(new BigDecimal(salesReportlist
											.get(j).getDamagereturn()));

								}
							}
							SalesReport sale = new SalesReport();
							sale.setOrderNumber(ponumbers.get(i));
							sale.setQuantity("" + quan);
							sale.setReturnstatus(salesReportlist.get(0)
									.getReturnstatus());
							sale.setNormalreturn("" + nr);
							sale.setDamagereturn("" + dr);
							salesReportList6.add(sale);

						}

						if (salesReportList6.size() > 0) {
							setSalesreturnedFlag(true);
							setNorecordflag(false);
							setSalesdeliveredFlag(false);

						} else {
							setSalesreturnedFlag(false);
							setSalesreturndetailFlag(true);
							setNorecordflag(true);
						}

					}

				}

			}
		} catch (Exception e) {
			logger.debug("Inside search report method exception calling");
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
			
		}
		return "";

	}

	public void onRowSelectEvent(final SelectEvent event) {
		logger.info("[onRowSelectEvent()]-------------------inside onRowSelectEvent() method()---------------");
		try {
			String p1 = ((SalesReport) event.getObject()).getCustomerName();
			String p2 = ((SalesReport) event.getObject()).getOrderNumber();
			salesReportlist = SalesReportJDBC.customerNameSearch(customerType,
					ALLcustomerName, p1, salesFromDate, salesToDate);
			setReportSuccessFlag(true);
			salesReportlist2 = new ArrayList<SalesReport>();
			BigDecimal grandtemp = BigDecimal.valueOf(0);
			for (int i = 0; i < salesReportlist.size(); i++) {
				BigDecimal totamt = BigDecimal.valueOf(0);
				if (p2.equalsIgnoreCase(salesReportlist.get(i).getOrderNumber())) {
					totamt = totamt.add(new BigDecimal(salesReportlist.get(i)
							.getQuantity()).multiply(new BigDecimal(
							salesReportlist.get(i).getSellprice())));
					SalesReport sales = new SalesReport();
					sales.setOrderNumber(salesReportlist.get(i)
							.getOrderNumber());
					sales.setProductName(salesReportlist.get(i)
							.getProductName());
					sales.setQuantity(salesReportlist.get(i).getQuantity());
					sales.setSellprice(salesReportlist.get(i).getSellprice());
					sales.setReturnstatus(salesReportlist.get(i)
							.getReturnstatus());
					sales.setDeliverystatus(salesReportlist.get(i)
							.getDeliverystatus());
					sales.setPaymentstatus(salesReportlist.get(i)
							.getPaymentstatus());

					sales.setTotPrice("" + totamt);
					salesReportlist2.add(sales);
					grandtemp = grandtemp.add(totamt);
					setTotalamount("" + grandtemp);
				}
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public void onRowSelectEvent1(final SelectEvent event) throws IOException,
			SQLException {
		logger.info("[onRowSelectEvent1()]-------------------inside onRowSelectEvent1() method()---------------");
		try {
			String s1 = ((SalesReport) event.getObject()).getCustomerName();
			setSalesreturnedFlag(true);
			salesReportList4 = new ArrayList<SalesReport>();
			salesReportlist = SalesReportJDBC.customerNameSearch(customerType,
					ALLcustomerName, CustomerName, salesFromDate, salesToDate);
			for (int i = 0; i < salesReportlist.size(); i++) {
				if (s1.equals(salesReportlist.get(i).getCustomerName())) {
					SalesReport salesReport = new SalesReport();
					salesReport.setOrderNumber(salesReportlist.get(i)
							.getOrderNumber());
					salesReport.setProductName(salesReportlist.get(i)
							.getProductName());
					salesReport.setQuantity(salesReportlist.get(i)
							.getQuantity());
					salesReport.setSellprice(salesReportlist.get(i)
							.getSellprice());
					salesReport.setDeliverystatus(salesReportlist.get(i)
							.getDeliverystatus());
					salesReport.setPaymentstatus(salesReportlist.get(i)
							.getPaymentstatus());
					salesReport.setReturnstatus(salesReportlist.get(i)
							.getReturnstatus());
					salesReportList4.add(salesReport);

				}

			}
		}

		catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public void onRowSelectEvent2(final SelectEvent event) throws IOException,
			SQLException {
		logger.info("[onRowSelectEvent2()]-------------------inside onRowSelectEvent2() method()---------------");
		try {
			String s1 = ((SalesReport) event.getObject()).getOrderNumber();
			setSalesreturndetailFlag(true);
			salesReportList7 = new ArrayList<SalesReport>();
			salesReportlist = SalesReportJDBC.customerNameSearch(customerType,
					ALLcustomerName, s1, salesFromDate, salesToDate);
			for (int i = 0; i < salesReportlist.size(); i++) {
				if (s1.equals(salesReportlist.get(i).getOrderNumber())) {
					SalesReport salesReport = new SalesReport();
					salesReport.setOrderNumber(salesReportlist.get(i)
							.getOrderNumber());
					salesReport.setProductName(salesReportlist.get(i)
							.getProductName());
					salesReport.setReturndate(salesReportlist.get(i)
							.getReturndate());
					salesReport.setCustomerName(salesReportlist.get(i)
							.getCustomerName());
					salesReport.setPhonenumber(salesReportlist.get(i)
							.getPhonenumber());
					salesReport.setNormalreturn(salesReportlist.get(i)
							.getNormalreturn());
					salesReport.setDamagereturn(salesReportlist.get(i)
							.getDamagereturn());
					salesReport.setReturnstatus(salesReportlist.get(i)
							.getReturnstatus());
					salesReportList7.add(salesReport);
				}

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

}
