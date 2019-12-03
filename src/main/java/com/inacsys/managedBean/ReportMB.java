package com.inacsys.managedBean;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jfree.chart.JFreeChart;
import org.primefaces.component.export.PDFExporter;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;











import org.w3c.dom.ls.LSInput;















//import scala.annotation.meta.setter;
import antlr.collections.List;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.CustomerReport;
import com.inacsys.domain.PurchaseReport;
import com.inacsys.domain.Report1;
import com.inacsys.domain.ReportDatabean;
import com.inacsys.domain.StockReport;
import com.inacsys.domain.VendorReport;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0010;
import com.inacsys.shared.I0015;
import com.inacsys.shared.I0016;
import com.inacsys.shared.I0021;
import com.inacsys.shared.I0022;
import com.inacsys.shared.Year;
import com.inacsys.util.CommonValidate;
import com.inacsys.util.GenerateEmployee;
import com.inacsys.util.ReportJDBC;
import com.inacsys.util.StockReportJDBC;
import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.awt.PdfPrinterGraphics2D;
import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "reportMB")
public class ReportMB {
	private static Logger logger = Logger.getLogger(ReportMB.class);
	public String category;
	public Date orderDate;
	public Date fromDate;
	public Date toDate;
	public Date reportDate;
	ArrayList<I0022> invoiceList;
	ArrayList<I0016> purchaseList1;
	ArrayList<I0015> purchaseList;
	ArrayList<I0021> salesList1;
	public String validate;
	public String validate1;
	public String validate2;
	public String flag1 = "none";
	public String flag = "none";
	public String cuscategory;
	private String repornew;
	private String year;
	private String month;
	private String reportType;
	private String reportIcon;
	private String businessPartner;
	private String treevalue;
	private TreeNode root1;
	private TreeNode root2;
	private TreeNode selectedNode1;
	private TreeNode selectedNode2;
	java.util.List<Year> yearinfo = null;
	private java.util.List<Report1> format = null;
	private java.util.List<Report1> droppedformat = null;
	private java.util.List<Report1> availablertype = null;
	private java.util.List<Report1> droppedrtype = null;
	java.util.List<Report1> reportlist = new ArrayList<Report1>();
	Report1 report1 = new Report1();
	private boolean tableflag = false;
	private StreamedContent streamedContent;
	private boolean pdfflag = false;
	private String quarterlyreporttype;
	private String halfyearlyreporttype;
	private String week;
	private boolean norecordFlag = false;
	
	public boolean isNorecordFlag() {
		return norecordFlag;
	}

	public void setNorecordFlag(boolean norecordFlag) {
		this.norecordFlag = norecordFlag;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getHalfyearlyreporttype() {
		return halfyearlyreporttype;
	}

	public void setHalfyearlyreporttype(String halfyearlyreporttype) {
		this.halfyearlyreporttype = halfyearlyreporttype;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getQuarterlyreporttype() {
		return quarterlyreporttype;
	}

	public void setQuarterlyreporttype(String quarterlyreporttype) {
		this.quarterlyreporttype = quarterlyreporttype;
	}

	public boolean isPdfflag() {
		return pdfflag;
	}

	public void setPdfflag(boolean pdfflag) {
		this.pdfflag = pdfflag;
	}

	public boolean isTableflag() {
		return tableflag;
	}

	public void setTableflag(boolean tableflag) {
		this.tableflag = tableflag;
	}

	public java.util.List<Report1> getReportlist() {
		return reportlist;
	}

	public void setReportlist(java.util.List<Report1> reportlist) {
		this.reportlist = reportlist;
	}

	public java.util.List<Report1> getAvailablertype() {
		return availablertype;
	}

	public void setAvailablertype(java.util.List<Report1> availablertype) {
		this.availablertype = availablertype;
	}

	public java.util.List<Report1> getDroppedrtype() {
		return droppedrtype;
	}

	public void setDroppedrtype(java.util.List<Report1> droppedrtype) {
		this.droppedrtype = droppedrtype;
	}

	public java.util.List<Report1> getFormat() {
		return format;
	}

	public void setFormat(java.util.List<Report1> format) {
		this.format = format;
	}

	public java.util.List<Report1> getDroppedformat() {
		return droppedformat;
	}

	public void setDroppedformat(java.util.List<Report1> droppedformat) {
		this.droppedformat = droppedformat;
	}

	public String getReportIcon() {
		return reportIcon;
	}

	public void setReportIcon(String reportIcon) {
		this.reportIcon = reportIcon;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public java.util.List<Year> getYearinfo() {
		return yearinfo;
	}

	public void setYearinfo(java.util.List<Year> yearinfo) {
		this.yearinfo = yearinfo;
	}

	public TreeNode getRoot1() {
		return root1;
	}

	public void setRoot1(TreeNode root1) {
		this.root1 = root1;
	}

	public TreeNode getRoot2() {
		return root2;
	}

	public void setRoot2(TreeNode root2) {
		this.root2 = root2;
	}

	public TreeNode getSelectedNode1() {
		return selectedNode1;
	}

	public void setSelectedNode1(TreeNode selectedNode1) {
		this.selectedNode1 = selectedNode1;
	}

	public TreeNode getSelectedNode2() {
		return selectedNode2;
	}

	public void setSelectedNode2(TreeNode selectedNode2) {
		this.selectedNode2 = selectedNode2;
	}

	public String getTreevalue() {
		return treevalue;
	}

	public void setTreevalue(String treevalue) {
		this.treevalue = treevalue;
	}

	java.util.List<String> reporttypeList = null;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getBusinessPartner() {
		return businessPartner;
	}

	public void setBusinessPartner(String businessPartner) {
		this.businessPartner = businessPartner;
	}

	ArrayList<Report1> report2 = new ArrayList<Report1>();

	public String getRepornew() {
		return repornew;
	}

	public void setRepornew(String repornew) {
		this.repornew = repornew;
	}

	public ArrayList<Report1> getReport2() {
		return report2;
	}

	public void setReport2(ArrayList<Report1> report2) {
		this.report2 = report2;
	}

	public String getCuscategory() {
		return cuscategory;
	}

	public void setCuscategory(String cuscategory) {
		this.cuscategory = cuscategory;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public ArrayList<I0015> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(ArrayList<I0015> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public String getValidate1() {
		return validate1;
	}

	public void setValidate1(String validate1) {
		this.validate1 = validate1;
	}

	public String getValidate2() {
		return validate2;
	}

	public void setValidate2(String validate2) {
		this.validate2 = validate2;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public ArrayList<I0021> getSalesList1() {
		return salesList1;
	}

	public void setSalesList1(ArrayList<I0021> salesList1) {
		this.salesList1 = salesList1;
	}

	public ArrayList<I0016> getPurchaseList1() {
		return purchaseList1;
	}

	public void setPurchaseList1(ArrayList<I0016> purchaseList1) {
		this.purchaseList1 = purchaseList1;
	}

	public String total;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ArrayList<I0022> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(ArrayList<I0022> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public ArrayList<I0016> purchaselist;

	public ArrayList<I0016> getPurchaselist() {
		return purchaselist;
	}

	public void setPurchaselist(ArrayList<I0016> purchaselist) {
		this.purchaselist = purchaselist;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	String s;

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	DemoController controller = null;

	public String redirect() {
		return "report";
	}

	public String redirect1() {
		return "product4";
	}

	public Date invoiceDate;
	public String orderNumber;
	public String VendorName;
	public String totalPrice;

	public String reportinvoiceDirect() {
		try {
			arrayReportMB = null;
			setValidate(null);
			setValidate1(null);
			setValidate2(null);
			setFromDate(null);
			setToDate(null);
			setCategory(null);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesDelete();
		} catch (Exception e) {

		}
		return "reportinvoiceDirect";
	}

	public String reportpurchaseDirect() {
		try {
			arrayReportMB = null;
			setValidate(null);
			setValidate1(null);
			setValidate2(null);
			setFromDate(null);
			setToDate(null);
			setCategory(null);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesDelete();
		} catch (Exception e) {

		}
		return "reportpurchaseDirect";
	}

	public String reportsalesDirect() {
		try {
			arrayReportMB = null;
			setValidate(null);
			setValidate1(null);
			setValidate2(null);
			setFromDate(null);
			setToDate(null);
			setCategory(null);
			setCuscategory("");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.salesDelete();
		} catch (Exception e) {

		}
		return "reportsalesDirect";
	}

	public DemoController getController() {
		return controller;
	}

	public void setController(DemoController controller) {
		this.controller = controller;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getVendorName() {
		return VendorName;
	}

	public void setVendorName(String vendorName) {
		VendorName = vendorName;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ArrayList<ReportMB> arrayReportMB = null;

	public ArrayList<ReportMB> getArrayReportMB() {
		return arrayReportMB;
	}

	public void setArrayReportMB(ArrayList<ReportMB> arrayReportMB) {
		this.arrayReportMB = arrayReportMB;
	}

	ArrayList<Report1> report = new ArrayList<Report1>();

	public ArrayList<Report1> getReport() {
		return report;
	}

	public void setReport(ArrayList<Report1> report) {
		this.report = report;
	}

	public String fromdate1;
	public String toDate1;

	public String getFromdate1() {
		return fromdate1;
	}

	public void setFromdate1(String fromdate1) {
		this.fromdate1 = fromdate1;
	}

	public String getToDate1() {
		return toDate1;
	}

	public void setToDate1(String toDate1) {
		this.toDate1 = toDate1;
	}

	/*
	 * jeni 12.1.2015
	 */
	public String reportPurchase() {
		logger.debug("--------------$$$$$$$$$$$$$$------------inside reportPurchase-------------$$$$$$$$$$$$$$-----------");
		try {

			validate1 = "";
			total = "0";
			invoiceList = null;
			if (fromDate == null) {
				logger.debug("inside date:::::::::::::::::");
				throw new Exception("*Enter the From Date");
			} else if (toDate == null) {
				logger.debug("inside date:::::::::::::::::");
				throw new Exception("*Enter the To Date");
			}
			Report1 report1 = new Report1();
			report1.setFromDate(fromDate);
			report1.setToDate(toDate);
			report1.setCategory(category);
			report1.setTotal(total);
			logger.debug("inside mb::::::::::::::::::::");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			purchaseList = controller.reportPurchase(purchaseList, report1);

			if (purchaseList.size() > 0) {
				logger.debug("Inside Value");
				flag1 = "none";
				flag = "1";
			}
			if (purchaseList.size() == 00) {
				logger.debug("Inside Value");
				flag1 = "1";
				flag = "none";
			}
			String DATE_FORMAT_NOW = "dd/MM/YYYY";
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			fromdate1 = sdf.format(fromDate);

			Date date2 = sdf.parse(fromdate1);
			toDate1 = sdf.format(toDate);

			Date date3 = sdf.parse(fromdate1);

			logger.debug("outside date:::::::@@@@@@@@@@@@@@@@@@@" + fromdate1);
			logger.debug("outside date:::::::@@@@@@@@@@@@@@@@@@@" + toDate1);

			setReport(report1.getReport());
			setTotal(GenerateEmployee.numberFormat.format(new BigDecimal(
					report1.getTotal())));
			logger.debug("--------------$$$$$$$$$$$$$$------------Outside reportPurchase-------------$$$$$$$$$$$$$$-----------");
			return "success2";
		} catch (Exception e) {
			setValidate1(e.getMessage());
			logger.debug("log:::::" + e.getMessage());
			return "failure2";
		}
	}

	public String reportInvoice() {
		logger.debug("inside the managed bean");
		try {
			// validate="";
			total = "0";

			Report1 report1 = new Report1();
			logger.debug("inside::::::::::::::::date1");
			try {
				if (fromDate == null) {
					throw new DemoException("*Enter FromDate");
				} else if (toDate == null) {
					throw new DemoException("*Enter ToDate");
				} else if (category.equalsIgnoreCase("")) {
					throw new DemoException("*Select any Category");
				}
			} catch (DemoException ee) {
				setValidate2(ee.getMessage());
				logger.debug("inside exception");
				return "";
			}
			report1.setFromDate(fromDate);
			report1.setToDate(toDate);
			report1.setCategory(category);
			report1.setTotal(total);
			logger.debug("inside mb::::::::::::::::::::");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			invoiceList = controller.reportInvoice(invoiceList, report1);
			setCategory(report1.getCategory());
			logger.debug("total::::::::" + report1.getTotal());
			setTotal(report1.getTotal());
			String DATE_FORMAT_NOW = "dd/MM/YYYY";
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			logger.debug("==from---tt--t--t-" + sdf);
			fromdate1 = sdf.format(fromDate);

			Date date2 = sdf.parse(fromdate1);
			toDate1 = sdf.format(toDate);

			Date date3 = sdf.parse(fromdate1);

			logger.debug("Invoice List:::::::@@@@@@@@@@@@@@@@@@@" + invoiceList);
			logger.debug("outside date:::::::@@@@@@@@@@@@@@@@@@@" + fromdate1);
			logger.debug("outside date:::::::@@@@@@@@@@@@@@@@@@@" + toDate1);
			int count = 0;
			if (invoiceList != null) {
				ArrayList<ReportMB> arrayReportMB1 = new ArrayList<ReportMB>();
				for (I0022 invoice : invoiceList) {
					ReportMB reportMB = new ReportMB();

					reportMB.setOrderNumber(invoiceList.get(count).getI0015()
							.getTemOrderNumber());
					logger.debug("1" + reportMB.getOrderNumber());
					reportMB.setVendorName(report1.getStrin().get(count));
					logger.debug("2" + reportMB.getVendorName());
					reportMB.setTotalPrice(invoiceList.get(count).getI0015()
							.getTotalPrice());
					logger.debug("3" + reportMB.getTotalPrice());
					reportMB.setOrderDate(invoiceList.get(count)
							.getInvoiceDate());
					logger.debug("4" + invoiceList.get(count).getInvoiceDate());
					arrayReportMB1.add(reportMB);
					logger.debug("5");
					count++;
				}
				setArrayReportMB(arrayReportMB1);
			}
			if (invoiceList.size() == 0) {
				logger.debug("Inside Null value from DAO");
				flag1 = "1";
				flag = "none";
			}

			else {
				logger.debug("-------inisde no list--------");
				flag1 = "none";
				flag = "1";
			}
			logger.debug("7");

			if (total.equalsIgnoreCase("0")) {
				logger.debug("8");

			}
			if (category.equals("Sales Invoice")) {
				logger.debug("9");

			}

			logger.debug("size---------------->" + arrayReportMB.size());
			if (report1.getInvoiceList().size() == 0)

			{
				logger.debug("zero check");
				flag1 = "1";
			}

			logger.debug("--------------$$$$$$$$$$$$$$------------Outside reportInvoice-------------$$$$$$$$$$$$$$-----------");
			return "success";
		} catch (DemoException e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
			logger.debug("Inside list f valkue");
			logger.debug("logaaaa:::::" + e.getMessage());
			if (invoiceList.size() == 0) {
				logger.debug("zero check");
				flag1 = "1";
				flag = "none";
			} else {
				flag1 = "none";
				flag = "1";
			}

			return "failure";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			logger.debug("loga:::::" + e.getMessage());
			if (invoiceList.size() == 0) {
				logger.debug("zero check");
				flag1 = "1";
				flag = "none";
			}
			return "failure1";
		}
	}

	public String reportSales() {
		try {
			logger.debug("--------------$$$$$$$$$$$$$$------------Inside reportSales-------------$$$$$$$$$$$$$$-----------");
			validate2 = "";
			total = "0";
			invoiceList = null;
			if (fromDate == null) {
				logger.debug("inside date:::::::::::::::::");
				throw new Exception("*Enter the From Date");
			} else if (toDate == null) {
				logger.debug("inside date:::::::::::::::::");
				throw new Exception("*Enter the To Date");
			} else if (cuscategory.equalsIgnoreCase("")) {
				throw new Exception("*Select the Category");
			}
			logger.debug("=====category===" + category + "=====cus category==="
					+ cuscategory);
			Report1 report1 = new Report1();
			report1.setFromDate(fromDate);
			report1.setToDate(toDate);
			report1.setCuscategory(cuscategory);
			report1.setCategory(category);
			report1.setTotal(total);
			logger.debug("inside reportSalesmb::::::::::::::::::::");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			salesList1 = controller.reportSales(salesList1, report1);
			String DATE_FORMAT_NOW = "yyyy-MM-dd";
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			fromdate1 = sdf.format(fromDate);

			Date date2 = sdf.parse(fromdate1);
			toDate1 = sdf.format(toDate);

			Date date3 = sdf.parse(fromdate1);

			logger.debug("outside date:::::::@@@@@@@@@@@@@@@@@@@" + fromdate1);
			logger.debug("outside date:::::::@@@@@@@@@@@@@@@@@@@" + toDate1);
			if (salesList1.size() == 0) {
				logger.debug("inside Null");
				flag1 = "1";
				flag = "none";
			} else if (salesList1.size() > 0) {
				logger.debug("inside Value");
				flag1 = "none";
				flag = "1";
			}
			setTotal(report1.getTotal());
			setReport2(report1.getReport2());
			logger.debug("--------------$$$$$$$$$$$$$$------------Outside reportSales-------------$$$$$$$$$$$$$$-----------");
			return "success3";
		} catch (Exception e) {
			setValidate2(e.getMessage());
			logger.debug("log:::::" + e.getMessage());
			return "failure3";
		}
	}

	public String cancel() {

		flag = "none";
		flag1 = "none";
		fromDate = null;
		toDate = null;
		return "reportM";

	}

	/* jeni up to this */

	// prema 03/05/2016 module for vendor report
	/* getters and setters */
	private boolean reportSuccessFlag = false;
	private boolean detailreportSuccessFlag = false;
	public Date rVendorFromDate;
	public Date rVendorToDate;
	public String allVendorName;
	public String vendorReportName;
	public ArrayList<VendorReport> vendorReportlist = null;
	public ArrayList<VendorReport> vendorReportlist1 = null;
	public ArrayList<VendorReport> vendorReportlist2 = null;
	public String vendorType;
	public String singleVendorFlag;
	public String multipleVendorFlag;
	private boolean vendorDetailFlag = false;
	private boolean vendorDetailFlag1 = false;
	public ArrayList<VendorReport> vendorReportList3 = null;
	public String totAmount;
	public ArrayList<VendorReport> vendorReportList4 = new ArrayList<VendorReport>();

	public String getTotAmount() {
		return totAmount;
	}

	public void setTotAmount(String totAmount) {
		this.totAmount = totAmount;
	}

	public boolean isReportSuccessFlag() {
		return reportSuccessFlag;
	}

	public void setReportSuccessFlag(boolean reportSuccessFlag) {
		this.reportSuccessFlag = reportSuccessFlag;
	}

	public boolean isDetailreportSuccessFlag() {
		return detailreportSuccessFlag;
	}

	public void setDetailreportSuccessFlag(boolean detailreportSuccessFlag) {
		this.detailreportSuccessFlag = detailreportSuccessFlag;
	}

	public String getVendorReportName() {
		return vendorReportName;
	}

	public void setVendorReportName(String vendorReportName) {
		this.vendorReportName = vendorReportName;
	}

	public Date getrVendorFromDate() {
		return rVendorFromDate;
	}

	public void setrVendorFromDate(Date rVendorFromDate) {
		this.rVendorFromDate = rVendorFromDate;
	}

	public Date getrVendorToDate() {
		return rVendorToDate;
	}

	public void setrVendorToDate(Date rVendorToDate) {
		this.rVendorToDate = rVendorToDate;
	}

	public String getAllVendorName() {
		return allVendorName;
	}

	public void setAllVendorName(String allVendorName) {
		this.allVendorName = allVendorName;
	}

	public ArrayList<VendorReport> getVendorReportlist2() {
		return vendorReportlist2;
	}

	public void setVendorReportlist2(ArrayList<VendorReport> vendorReportlist2) {
		this.vendorReportlist2 = vendorReportlist2;
	}

	public ArrayList<VendorReport> getVendorReportlist1() {
		return vendorReportlist1;
	}

	public void setVendorReportlist1(ArrayList<VendorReport> vendorReportlist1) {
		this.vendorReportlist1 = vendorReportlist1;
	}

	public ArrayList<VendorReport> getVendorReportlist() {
		return vendorReportlist;
	}

	public void setVendorReportlist(ArrayList<VendorReport> vendorReportlist) {
		this.vendorReportlist = vendorReportlist;
	}

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public String getSingleVendorFlag() {
		return singleVendorFlag;
	}

	public void setSingleVendorFlag(String singleVendorFlag) {
		this.singleVendorFlag = singleVendorFlag;
	}

	public String getMultipleVendorFlag() {
		return multipleVendorFlag;
	}

	public void setMultipleVendorFlag(String multipleVendorFlag) {
		this.multipleVendorFlag = multipleVendorFlag;
	}

	public boolean isVendorDetailFlag1() {
		return vendorDetailFlag1;
	}

	public void setVendorDetailFlag1(boolean vendorDetailFlag1) {
		this.vendorDetailFlag1 = vendorDetailFlag1;
	}

	public boolean isVendorDetailFlag() {
		return vendorDetailFlag;
	}

	public void setVendorDetailFlag(boolean vendorDetailFlag) {
		this.vendorDetailFlag = vendorDetailFlag;
	}

	public ArrayList<VendorReport> getVendorReportList3() {
		return vendorReportList3;
	}

	public void setVendorReportList3(ArrayList<VendorReport> vendorReportList3) {
		this.vendorReportList3 = vendorReportList3;
	}

	public ArrayList<VendorReport> getVendorReportList4() {
		return vendorReportList4;
	}

	public void setVendorReportList4(ArrayList<VendorReport> vendorReportList4) {
		this.vendorReportList4 = vendorReportList4;
	}


	/*public String searchReport() {
		logger.info("[searchReport()]---------------------------Inside searchReport() in MB Calling-------------------------------------");
		setValidate(null);
		vendorReportlist = null;
		vendorReportlist1 = null;
		vendorReportlist2 = null;
		vendorReportList3 = null;
		vendorReportList4 = null;
		setVendorDetailFlag(false);
		setVendorDetailFlag1(false);
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if (rVendorFromDate == null) {
				fieldName = CommonValidate.findComponentInRoot("fdate").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the  From Date."));
			}
			if (rVendorToDate == null) {
				fieldName = CommonValidate.findComponentInRoot("tdate").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the  To Date."));
			}
			if (StringUtils.isBlank(vendorType)) {
				System.out.println("vendor type if-----");
				fieldName = CommonValidate.findComponentInRoot("vt").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select Vendor type"));
			}else if (vendorType.equalsIgnoreCase("single")) {
				System.out.println("vendor type else if");
				System.out.println("repornew"+repornew);
				if (repornew.equals("") || repornew.equals(null)) {
					System.out.println("inside reportnew empty");
					fieldName = CommonValidate.findComponentInRoot("v1").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter the Vendor Name"));
				} else if (!repornew.equalsIgnoreCase("")) {
					VendorReport vendorReport = new VendorReport();
					vendorReport.setVendorReportName(repornew);
					logger.debug("vendor name"
							+ vendorReport.getVendorReportName());
					setrVendorFromDate(rVendorFromDate);
					setrVendorToDate(rVendorToDate);
					String vendorname = vendorReport.getVendorReportName();
					vendorReportlist = ReportJDBC.vendorNameSearch(
							vendorReport.getVendorReportName(),
							rVendorFromDate, rVendorToDate);
					if (vendorReportlist.size() > 0) {
						logger.debug("vendorReportList size"
								+ vendorReportlist.size());
						BigDecimal quan = BigDecimal.valueOf(0);
						BigDecimal price = BigDecimal.valueOf(0);
						logger.debug("vendorname in report list "
								+ vendorReportlist.get(0).getVendorRepName());
						ArrayList<String> orderno = new ArrayList<String>();
						for (int i = 0; i < vendorReportlist.size(); i++) {
							orderno.add(vendorReportlist.get(i).getDetailQuan());
						}
						vendorReportlist2 = new ArrayList<VendorReport>();
						HashSet<String> ordno = new HashSet<String>(orderno);
						orderno.clear();
						orderno.addAll(ordno);
						Collections.sort(orderno);
						logger.debug("order no size" + orderno.size());
						for (int i = 0; i < orderno.size(); i++) {
							price = BigDecimal.valueOf(0);
							quan = BigDecimal.valueOf(0);
							for (int j = 0; j < vendorReportlist.size(); j++) {
								if (vendorReportlist.get(j).getDetailQuan()
										.equals(orderno.get(i))) {
									logger.debug("inside if");
									quan = quan.add(new BigDecimal(
											vendorReportlist.get(j)
													.getPurchaseOrderNO()));
									logger.debug("quantity" + quan);
									price = price
											.add(new BigDecimal(
													vendorReportlist.get(j)
															.getPrize())
													.multiply(new BigDecimal(
															vendorReportlist
																	.get(j)
																	.getPurchaseOrderNO())));
									logger.debug("price" + price);
								}

							}
							VendorReport ven = new VendorReport();
							ven.setVendorRepName(vendorReportlist.get(0)
									.getVendorRepName());
							ven.setVendorphonenumber(vendorReportlist.get(0)
									.getVendorphonenumber());
							ven.setPurchaseStatus(vendorReportlist.get(i)
									.getPurchaseStatus());
							ven.setPurchaseOrderNO(orderno.get(i));
							ven.setPrize("" + price);
							vendorReportlist2.add(ven);

						}
						if (vendorReportlist2.size() > 0) {
							setReportSuccessFlag(true);
							setDetailreportSuccessFlag(false);
							setNorecordFlag(false);

						} else {
							setReportSuccessFlag(false);
							setDetailreportSuccessFlag(false);
							setNorecordFlag(true);
						}
					} else {
						setNorecordFlag(true);
						setDetailreportSuccessFlag(false);
						setReportSuccessFlag(false);
					}

				}
			} else if (vendorType.equalsIgnoreCase("multiple")) {
				if (allVendorName.equals("")) {
					fieldName = CommonValidate.findComponentInRoot("av1")
							.getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage(
							"Please Choose the Vendor Name"));

				} else if (allVendorName.equalsIgnoreCase("All")) {
					setValidate(null);
					java.util.List<String> vendors = new ArrayList<String>();
					java.util.List<String> phno = new ArrayList<String>();
					logger.debug("All vendor name" + allVendorName);
					VendorReport vendorReport = new VendorReport();
					vendorReport.setAllrepVendorName(allVendorName);
					vendorReport.setVendorFromDate(rVendorFromDate);
					vendorReport.setVendorToDate(rVendorToDate);
					vendorReportlist = ReportJDBC.vendorNameSearch("All",
							rVendorFromDate, rVendorToDate);
					System.out.println("vendor report list size---"+vendorReportlist.size());
					if (vendorReportlist.size() > 0) {
						logger.debug("all vendor report list size"
								+ vendorReportlist.size());
						vendorReportlist1 = new ArrayList<VendorReport>();
						for (int i = 0; i < vendorReportlist.size(); i++) {
							vendors.add(vendorReportlist.get(i)
									.getAllrepVendorName());
							phno.add(vendorReportlist.get(i).getAllvendorphNo());
						}
						HashSet<String> vendor = new HashSet<String>(vendors);
						HashSet<String> phone = new HashSet<String>(phno);
						vendors.clear();
						vendors.addAll(vendor);
						phno.clear();
						phno.addAll(phone);
						for (int i = 0; i < vendors.size(); i++) {
							BigDecimal totprice = BigDecimal.valueOf(0);
							BigDecimal quan = BigDecimal.valueOf(0);
							for (int j = 0; j < vendorReportlist.size(); j++) {
									if (vendorReportlist.get(j)
											.getAllrepVendorName()
											.equals(vendors.get(i))) {
										quan = quan.add(new BigDecimal(
												vendorReportlist.get(j)
														.getPurchaseOrderNO()));
										logger.debug("quantity" + quan);
										logger.debug("report list size"
												+ vendorReportlist.get(j)
														.getPrize()
												+ ""
												+ vendorReportlist.get(j)
														.getPurchaseOrderNO());
										totprice = totprice
												.add(new BigDecimal(
														vendorReportlist.get(j)
																.getPrize())
														.multiply(new BigDecimal(
																vendorReportlist
																		.get(j)
																		.getPurchaseOrderNO())));
										logger.debug("price" + totprice);
									}
							}
							VendorReport ven = new VendorReport();
							ven.setAllrepVendorName(vendors.get(i));
							ven.setAllvendorphNo(phno.get(i));
							ven.setTotalprice("" + totprice);
							ven.setTotquantity("" + quan);
							vendorReportlist1.add(ven);
						}
						if (vendorReportlist1.size() > 0) {
							System.out.println("inside if");
							setDetailreportSuccessFlag(true);
							setReportSuccessFlag(false);
							setNorecordFlag(false);
						} else {
							setReportSuccessFlag(false);
							setDetailreportSuccessFlag(false);
							setNorecordFlag(true);
						}
					} else {
						setReportSuccessFlag(false);
						setDetailreportSuccessFlag(false);
						setNorecordFlag(true);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Inside search report method exception calling");
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
		}
		return "";
	}*/
	public void onRowSelect(final SelectEvent event) throws IOException,
			SQLException {
		try {
			logger.debug("select event method calling");
			String s1 = ((VendorReport) event.getObject())
					.getAllrepVendorName();
			logger.debug("s1" + s1);
			setVendorDetailFlag(true);
			vendorReportList3 = new ArrayList<VendorReport>();
			//vendorReportlist = ReportJDBC.vendorNameSearch("All",
					//rVendorFromDate, rVendorToDate);
			logger.debug("report list size" + vendorReportlist.size() + ""
					+ vendorReportlist.get(0).getPurchaseOrderNO());
			BigDecimal nettemp = BigDecimal.valueOf(0);
			for (int i = 0; i < vendorReportlist.size(); i++) {
				BigDecimal totprice = BigDecimal.valueOf(0);
				if (s1.equals(vendorReportlist.get(i).getAllrepVendorName())) {
					VendorReport vendorReport = new VendorReport();
					logger.debug("inside if" + "vendorname"
							+ vendorReportlist.get(i).getAllrepVendorName());
					totprice = totprice.add(new BigDecimal(vendorReportlist
							.get(i).getPurchaseOrderNO())
							.multiply(new BigDecimal(vendorReportlist.get(i)
									.getPrize())));
					vendorReport.setDetailPONumber(vendorReportlist.get(i)
							.getDetailQuan());
					vendorReport.setDetailProduct(vendorReportlist.get(i)
							.getDetailProduct());
					vendorReport.setDetailQuan(vendorReportlist.get(i)
							.getPurchaseOrderNO());
					vendorReport.setSellingPrice(vendorReportlist.get(i)
							.getPrize());
					vendorReport.setNetAmount("" + totprice);
					vendorReportList3.add(vendorReport);
					nettemp = nettemp.add(totprice);
					logger.debug("net amount" + nettemp);
					setTotAmount("" + nettemp);
				}

			}
			logger.debug("vendorreportlist3 size" + vendorReportList3.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}


	/* Autocomplete */

	/**
	 * Prema 04/05/2016
	 */
	public ArrayList<String> vendornameList = null;
	private ArrayList<Report1> reportdata = new ArrayList<Report1>();

	public ArrayList<String> getVendornameList() {
		return vendornameList;
	}

	public void setVendornameList(ArrayList<String> vendornameList) {
		this.vendornameList = vendornameList;
	}

	public ArrayList<Report1> complete(String query) {
		ArrayList<Report1> allThemes = new ArrayList<Report1>();
		allThemes.addAll(reportdata);
		ArrayList<Report1> filteredThemes = new ArrayList<Report1>();

		for (int i = 0; i < allThemes.size(); i++) {
			Report1 skin = allThemes.get(i);
			if (skin.getVendorName().toLowerCase().startsWith(query)) {
				filteredThemes.add(skin);
			}
		}

		return filteredThemes;
	}

	@PostConstruct
	public void init() {
		System.out.println("init method calling-------");
		DemoController controller = null;
		try {
			createPieModel2();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			reportdata = controller.getVendorNameList();
			logger.debug("report data size" + reportdata.size());

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			e.printStackTrace();
		}
	}

	private PieChartModel pieModel1;

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

	private void createPieModel2() {
		pieModel1 = new PieChartModel();
		pieModel1.setSeriesColors("051C5F,FFCC33,A1B4D5,AA4644");
		pieModel1.set("sales", 100);
		pieModel1.set("purchase", 120);
		pieModel1.set("inventory out", 80);
		pieModel1.set("inventory in", 110);
		pieModel1.setTitle("over view");
		pieModel1.setLegendPosition("w");
	}

	public void customerreport() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("customer-report",
				options, null);
		setValidate(null);
		setCustFromDate(null);
		setCustToDate(null);
		setCustName(null);
		setAllCustName(null);
		singleCustomerFlag = "none";
		multipleCustomerFlag = "none";
		setCustomerType(null);
		setCustreportSuccessFlag(false);
		setNorecordFlag(false);
		setCustomerDetailFlag(false);
		setCustdetailreportSuccessFlag(false);
		setCustomerDetailFlag1(false);
	}

	// prema begin 06/06/2016 getters and setters for customer report

	public Date custFromDate;
	public Date custToDate;
	public String custName;
	public String allCustName;
	public String customerType;
	public String singleCustomerFlag;
	public String multipleCustomerFlag;
	public ArrayList<CustomerReport> customerReportlist = null;
	public ArrayList<CustomerReport> customerReportlist1 = null;
	public ArrayList<CustomerReport> customerReportlist2 = null;
	private boolean custreportSuccessFlag = false;
	private boolean custdetailreportSuccessFlag = false;
	private boolean customerDetailFlag = false;
	private boolean customerDetailFlag1 = false;

	public boolean isCustomerDetailFlag1() {
		return customerDetailFlag1;
	}

	public void setCustomerDetailFlag1(boolean customerDetailFlag1) {
		this.customerDetailFlag1 = customerDetailFlag1;
	}

	public boolean isCustdetailreportSuccessFlag() {
		return custdetailreportSuccessFlag;
	}

	public void setCustdetailreportSuccessFlag(
			boolean custdetailreportSuccessFlag) {
		this.custdetailreportSuccessFlag = custdetailreportSuccessFlag;
	}

	public ArrayList<CustomerReport> getCustomerReportlist2() {
		return customerReportlist2;
	}

	public void setCustomerReportlist2(
			ArrayList<CustomerReport> customerReportlist2) {
		this.customerReportlist2 = customerReportlist2;
	}

	public boolean isCustomerDetailFlag() {
		return customerDetailFlag;
	}

	public void setCustomerDetailFlag(boolean customerDetailFlag) {
		this.customerDetailFlag = customerDetailFlag;
	}

	public ArrayList<CustomerReport> getCustomerReportlist1() {
		return customerReportlist1;
	}

	public void setCustomerReportlist1(
			ArrayList<CustomerReport> customerReportlist1) {
		this.customerReportlist1 = customerReportlist1;
	}

	public boolean isCustreportSuccessFlag() {
		return custreportSuccessFlag;
	}

	public void setCustreportSuccessFlag(boolean custreportSuccessFlag) {
		this.custreportSuccessFlag = custreportSuccessFlag;
	}

	public ArrayList<CustomerReport> getCustomerReportlist() {
		return customerReportlist;
	}

	public void setCustomerReportlist(
			ArrayList<CustomerReport> customerReportlist) {
		this.customerReportlist = customerReportlist;
	}

	public Date getCustFromDate() {
		return custFromDate;
	}

	public void setCustFromDate(Date custFromDate) {
		this.custFromDate = custFromDate;
	}

	public Date getCustToDate() {
		return custToDate;
	}

	public void setCustToDate(Date custToDate) {
		this.custToDate = custToDate;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAllCustName() {
		return allCustName;
	}

	public void setAllCustName(String allCustName) {
		this.allCustName = allCustName;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
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

	public void oncustRowSelectEvent(final SelectEvent event)
			throws IOException, SQLException {
		try {
			logger.debug("select event method calling");
			String s4 = ((CustomerReport) event.getObject()).getCustName();
			logger.debug("s4" + s4);
			String s3 = ((CustomerReport) event.getObject()).getCustSONumber();
			logger.debug("s3" + s3);
			setCustomerDetailFlag(true);
			//customerReportlist = ReportJDBC.customernamesearch(s4,
					//custFromDate, custToDate);
			logger.debug("customerreportList size" + customerReportlist.size());
			logger.debug("customer name"
					+ customerReportlist.get(0).getCustName() + "SO number"
					+ customerReportlist.get(0).getCustSONumber());
			customerReportlist2 = new ArrayList<CustomerReport>();
			BigDecimal nettemp = BigDecimal.valueOf(0);
			BigDecimal grandtemp = BigDecimal.valueOf(0);
			for (int i = 0; i < customerReportlist.size(); i++) {
				if (s3.equalsIgnoreCase(customerReportlist.get(i)
						.getCustSONumber())) {
					logger.debug("inside if");
					CustomerReport cus = new CustomerReport();
					cus.setCustSONumber(customerReportlist.get(i)
							.getCustSONumber());
					cus.setProdName(customerReportlist.get(i).getProdName());
					cus.setProdQuantity(customerReportlist.get(i)
							.getProdQuantity());
					cus.setProdUnitPrice(customerReportlist.get(i)
							.getProdUnitPrice());
					cus.setTotprice(customerReportlist.get(i).getTotprice());
					customerReportlist2.add(cus);
					nettemp = nettemp.add(new BigDecimal(customerReportlist
							.get(i).getTotprice()));
					grandtemp = nettemp.add(new BigDecimal(customerReportlist
							.get(i).getShippingamt()));
					setTotAmount("" + grandtemp);
				}
			}
			logger.debug("customerreportlist2 size"
					+ customerReportlist2.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public void allcustRowSelect(final SelectEvent event) throws IOException,
			SQLException {
		try {
			logger.debug("select event method calling");
			String s1 = ((CustomerReport) event.getObject()).getAllCustName();
			logger.debug("s1" + s1);
			setCustomerDetailFlag1(true);
			customerReportlist2 = new ArrayList<CustomerReport>();
			//customerReportlist = ReportJDBC.customernamesearch("All",
					//custFromDate, custToDate);
			logger.debug("customer list size" + customerReportlist.size());
			BigDecimal nettemp = BigDecimal.valueOf(0);
			for (int i = 0; i < customerReportlist.size(); i++) {
				BigDecimal totprice = BigDecimal.valueOf(0);
				logger.debug("allcustomer"
						+ customerReportlist.get(i).getAllCustName());
				if (s1.equals(customerReportlist.get(i).getAllCustName())) {
					CustomerReport custReport = new CustomerReport();
					logger.debug("inside if" + "customername"
							+ customerReportlist.get(i).getAllCustName());
					custReport.setCustSONumber(customerReportlist.get(i)
							.getCustSONumber());
					custReport.setProdName(customerReportlist.get(i)
							.getProdName());
					custReport.setProdQuantity(customerReportlist.get(i)
							.getProdQuantity());
					custReport.setProdUnitPrice(customerReportlist.get(i)
							.getProdUnitPrice());
					custReport.setTotprice(customerReportlist.get(i)
							.getTotprice());
					customerReportlist2.add(custReport);
					nettemp = nettemp.add(new BigDecimal(customerReportlist
							.get(i).getTotprice()));
					setTotAmount(""
							+ nettemp.add(new BigDecimal(customerReportlist
									.get(i).getShippingamt())));
				}

			}
			logger.debug("customerreportlist1 size"
					+ customerReportlist2.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
		}
	}

	// prema 03/05/2016 dialog box creation for sales report

	public void salesreport() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("sales-report", options,
				null);

	}

	// prema 03/05/2016 dialog box creation for stockin report

	public void stockinreport() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("stock-report", options,
				null);

	}

	// prema 03/05/2016 dialog box creation for stockout report

	public void stockoutreport() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("stock-out-report",
				options, null);

	}

	// prema 03/05/2016 dialog box creation for employee report

	public void employeereport() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("employee-report",
				options, null);

	}

	public String dailyreportspage() {
		types= new String[]{};
		formats= new String[]{};
		reportlist = new ArrayList<Report1>();
		reportDate = null;
		businessPartner = "";
		setPdfflag(false);
		setValidate("");
		System.out.println("--------dailyreportPage----------");
		try {
			formats();
			setReportpage("DailyReports");
		} catch (Exception e) {
			e.printStackTrace();

		}

		return "dailyreports";
	}

	public String weeklyreportPage() {
		types= new String[]{};
		formats= new String[]{};
		yearinfo = new ArrayList<Year>();
		businessPartner = "";
		setPdfflag(false);
		month = null;
		week = null;
		year = null;
		setValidate("");
		System.out.println("--------weeklyreportPage----------");
		try {

			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setYearinfo(controller.yearInfo(yearinfo));
			System.out.println("year --- " + yearinfo.size());
			formats();
			setReportpage("WeeklyReports");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "weeklyreports";
	}

	public String monthlyreportspage() {
		types= new String[]{};
		formats= new String[]{};
		yearinfo = new ArrayList<Year>();
		businessPartner = "";
		setPdfflag(false);
		month = "";
		year = null;
		setValidate("");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setYearinfo(controller.yearInfo(yearinfo));
			System.out.println("year --- " + yearinfo.size());
			formats();
			setReportpage("MonthlyReports");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "monthlyreports";
	}

	public String quarterlyreportspage() {
		types= new String[]{};
		formats= new String[]{};
		yearinfo = new ArrayList<Year>();
		System.out.println("quarterlyreportspage=====");
		businessPartner = "";
		setPdfflag(false);
		quarterlyreporttype = "";
		year = null;
		setValidate("");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setYearinfo(controller.yearInfo(yearinfo));
			System.out.println("year --- " + yearinfo.size());
			formats();
			setReportpage("QuarterlyReports");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "quarterlyreports";
	}

	public String halfyearlyreportspage() {
		yearinfo = new ArrayList<Year>();
		types= new String[]{};
		formats= new String[]{};
		businessPartner = "";
		setPdfflag(false);
		halfyearlyreporttype = "";
		year = "";
		setValidate("");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setYearinfo(controller.yearInfo(yearinfo));
			System.out.println("year --- " + yearinfo.size());
			formats();
			setReportpage("HalfyearlyReports");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "halfyearlyreports";
	}
	
	public void formats(){
		formatList=new ArrayList<String>();
		formatList.add("Pie Chart");
		formatList.add("Bar Chart");
		formatList.add("Table");
	}
	
	public String annualreportspage() {
		types= new String[]{};
		formats= new String[]{};
		yearinfo = new ArrayList<Year>();
		year = null;
		reportType = null;
		businessPartner = "";
		setValidate("");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setYearinfo(controller.yearInfo(yearinfo));
			System.out.println("year --- " + yearinfo.size());
			formats();			
			setReportpage("AnnualReports");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "annualReports";
	}

	public void onDrop(DragDropEvent ddEvent) {
		Report1 report1 = ((Report1) ddEvent.getData());
		droppedformat.add(report1);
		format.remove(report1);
	}

	public void onDrop1(DragDropEvent ddEvent) {
		Report1 report1 = ((Report1) ddEvent.getData());
		format.add(report1);
		droppedformat.remove(report1);
		System.out.println("size " + format.size());
	}

	public void rtypeDrop(DragDropEvent ddEvent) {
		Report1 report1 = ((Report1) ddEvent.getData());
		droppedrtype.add(report1);
		availablertype.remove(report1);
		System.out.println("availablertype---" + availablertype.size());
		System.out.println("droppedrtype----" + droppedrtype.size());
	}

	public void rtypeDrop1(DragDropEvent ddEvent) {
		Report1 report1 = ((Report1) ddEvent.getData());
		availablertype.add(report1);
		droppedrtype.remove(report1);
		System.out.println("size " + droppedrtype.size());
	}

	
	private java.util.List<String> typesList=null;
	private java.util.List<String> formatList=null;
	private String[] types;
	private String[] formats;
	

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String[] getFormats() {
		return formats;
	}

	public void setFormats(String[] formats) {
		this.formats = formats;
	}

	public java.util.List<String> getTypesList() {
		return typesList;
	}

	public void setTypesList(java.util.List<String> typesList) {
		this.typesList = typesList;
	}

	public java.util.List<String> getFormatList() {
		return formatList;
	}

	public void setFormatList(java.util.List<String> formatList) {
		this.formatList = formatList;
	}

	public void reporttypeChange(ValueChangeEvent v) {
		availablertype = new ArrayList<Report1>();
		droppedrtype = new ArrayList<Report1>();
		typesList=new ArrayList<String>();		
		try {
			String s = v.getNewValue().toString();
			if (s.equalsIgnoreCase("Vendor")) {
				System.out.println("--Vendor--");				
				typesList.add("Purchase Delivery");
				typesList.add("Purchase Payment");
				typesList.add("Purchase Return");				
			} else if (s.equalsIgnoreCase("Customer")) {
				System.out.println("--Customer--");
				typesList.add("Sales Delivery");
				typesList.add("Sales Payment");
				typesList.add("Sales Return");
				typesList.add("Quick Sales Delivery");
				typesList.add("Quick Sales Payment");
				typesList.add("Quick Sales Return");
			} else if (s.equalsIgnoreCase("Transaction")) {
				typesList.add("Profit");
				typesList.add("Loss");				
			}
			setBusinessPartner(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String reportpage;

	public String getReportpage() {
		return reportpage;
	}

	public void setReportpage(String reportpage) {
		this.reportpage = reportpage;
	}

	public String getDailyreport() {

		reportlist = new ArrayList<Report1>();
		try {
			System.out.println("-------getDailyreport--------");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			report1.setTypes(types);
			report1.setBusinesspartner(businessPartner);
			report1.setReportpage(getReportpage());
			System.out.println("types "+types);
			
			if (getReportpage().equalsIgnoreCase("DailyReports")) {
				System.out.println("inside DailyReports condition- ");
				report1.setReportdate(reportDate);
				if (reportDate == null) {
					throw new Exception("*Please Enter the Date");
				} else if (businessPartner.equalsIgnoreCase("")
						|| businessPartner.equalsIgnoreCase("select")) {
					throw new Exception("*Please Choose the Business Partner");
				}  else if (types.length==0) {
					throw new Exception("*Please Choose the Report Type");
				} else if (formats.length==0) {
					throw new Exception(
							"*Please Choose Atleast One Format");
				}

			} else if (getReportpage().equalsIgnoreCase("WeeklyReports")) {
				System.out.println("inside WeeklyReports condition- ");
				report1.setYear(year);
				report1.setMonth(month);
				report1.setWeek(week);
				report1.setWeek1(week1);
				System.out.println("year " + year);
				System.out.println("month " + month);
				if (year == null || year == "") {
					throw new Exception("*Please Choose the Year");
				} else if (month == null || month == "") {
					throw new Exception("*Please Choose the Month");
				} else if (week == null || week == "") {
					throw new Exception("*Please Choose the Week");
				} else if (businessPartner == null || businessPartner == "") {
					throw new Exception("*Please Choose the Business Partner");
				}   else if (types.length==0) {
					throw new Exception("*Please Choose the Report Type");
				} else if (formats.length==0) {
					throw new Exception(
							"*Please Choose Atleast One Format");
				}
			} else if (getReportpage().equalsIgnoreCase("MonthlyReports")) {
				System.out.println("inside MonthlyReports condition- " + month);
				report1.setYear(year);
				report1.setMonth(month);
				System.out.println("year " + year);
				System.out.println("month " + month);
				if (year == null || year == "") {
					throw new Exception("*Please Choose the Year");
				} else if (month == null || month == "") {
					throw new Exception("*Please Choose the Month");
				} else if (businessPartner == null || businessPartner == "") {
					throw new Exception("*Please Choose the Business Partner");
				}   else if (types.length==0) {
					throw new Exception("*Please Choose the Report Type");
				} else if (formats.length==0) {
					throw new Exception(
							"*Please Choose Atleast One Format");
				}
			} else if (getReportpage().equalsIgnoreCase("QuarterlyReports")) {
				System.out.println("inside QuarterlyReports condition- ");
				report1.setYear(year);
				report1.setQuarterlyreporttype(quarterlyreporttype);
				System.out.println("year " + year);
				System.out.println("halfyearlyreporttype "
						+ halfyearlyreporttype);
				if (year == null || year == "") {
					throw new Exception("*Please Choose the Year");
				} else if (quarterlyreporttype == null
						|| quarterlyreporttype == "") {
					throw new Exception(
							"*Please Choose the Quarterly Report");
				} else if (businessPartner == null || businessPartner == "") {
					throw new Exception("*Please Choose the Business Partner");
				}   else if (types.length==0) {
					throw new Exception("*Please Choose the Report Type");
				} else if (formats.length==0) {
					throw new Exception(
							"*Please Choose Atleast One Format");
				}
			} else if (getReportpage().equalsIgnoreCase("HalfyearlyReports")) {
				System.out.println("inside HalfyearlyReports condition- ");
				report1.setYear(year);
				report1.setHalfyearlyreporttype(halfyearlyreporttype);
				System.out.println("year " + year);
				System.out.println("halfyearlyreporttype "
						+ halfyearlyreporttype);
				
				System.out.println("Formates and types "+types+"--->"+formats);
				if (year == null || year == "") {
					throw new Exception("*Please Choose the Year");
				} else if (halfyearlyreporttype == null
						|| halfyearlyreporttype == "") {
					throw new Exception("*Please Choose the Halfyearly Report");
				} else if (businessPartner == null || businessPartner == "") {
					throw new Exception("*Please Choose the Business Partner");
				}  else if (types.length==0) {
					throw new Exception("*Please Choose the Report Type");
				} else if (formats.length==0) {
					throw new Exception(
							"*Please Choose Atleast One Format");
				}
			} else if (getReportpage().equalsIgnoreCase("AnnualReports")) {
				System.out.println("inside AnnualReports if condition- ");
				report1.setYear(year);
				if (year == null || year == "") {
					throw new Exception("*Please Choose the Year");
				} else if (businessPartner == null || businessPartner == "") {
					throw new Exception("*Please Choose the Business Partner");
				}  else if (types.length==0) {
					throw new Exception("*Please Choose the Report Type");
				} else if (formats.length==0) {
					throw new Exception(
							"*Please Choose Atleast One Format");
				}
			}

			System.out.println("date " + report1.getReportdate());
			controller.getDailyreport(report1);
			System.out
					.println("------reportlist.size-----" + reportlist.size());
			setReportlist(report1.getVendorlist());
			System.out.println("----bussines partner---->"+businessPartner);
			if (reportlist.size() > 0) {
				if (businessPartner.equalsIgnoreCase("Vendor")) {
					vendorpdf();
				}
				else if (businessPartner.equalsIgnoreCase("Customer")) {
					customerpdf();
				}
				else if (businessPartner.equalsIgnoreCase("Transaction")) {
					transactionpdf();
				}
			
				setPdfflag(true);
				RequestContext.getCurrentInstance()
						.execute("PF('con').show();");
			} else {
				System.out.println("inside else condition getdaily report ");
				setPdfflag(false);
				RequestContext.getCurrentInstance().execute(
						"PF('errormsg').show();");
				System.out.println("inside else end");
			}
		} catch (Exception e) {
			setValidate(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	public void vendorpdf() {

		try {
			System.out.println("inside vender pdf");
			Document doc = new Document();
			OutputStream out = new ByteArrayOutputStream();
			PdfWriter writer = null;
			writer = PdfWriter.getInstance(doc, out);
			doc.open();
			String title = "";
			for (int i = 0; i < types.length; i++) {
				
				title = title +types[i] + "|";
			}
			doc.addTitle(title);
			System.out.println("BEFORE pdf" + formats.length);
			for (int i = 0; i < formats.length; i++) {
			
				
				if (formats[i].equalsIgnoreCase("Table")) {
				
					PdfPTable nestedTable = new PdfPTable(4);
					nestedTable.setWidthPercentage(100f);

					PdfPCell nesCell1 = new PdfPCell(new Paragraph(
							"Order Number"));
					PdfPCell nesCell2 = new PdfPCell(new Paragraph(
							"Vendor Name"));
					PdfPCell nesCell3 = new PdfPCell(new Paragraph("Price"));
					PdfPCell nesCell4 = new PdfPCell(new Paragraph("Status"));
					nesCell1.setFixedHeight(22f);

					nesCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
					nesCell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
					nesCell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
					nesCell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
					nestedTable.addCell(nesCell1);
					nestedTable.addCell(nesCell2);
					nestedTable.addCell(nesCell3);
					nestedTable.addCell(nesCell4);

					doc.add(nestedTable);
					for (int j = 0; j < reportlist.size(); j++) {

						PdfPTable nestedTable1 = new PdfPTable(4);
						nestedTable1.setWidthPercentage(100f);
						PdfPCell nesCell11 = new PdfPCell(new Paragraph(
								reportlist.get(j).getOrderNumber()));
						PdfPCell nesCell21 = new PdfPCell(new Paragraph(
								reportlist.get(j).getVendorName()));
						PdfPCell nesCell31 = new PdfPCell(new Paragraph(
								reportlist.get(j).getPrice()));
						PdfPCell nesCell41 = new PdfPCell(new Paragraph(
								reportlist.get(j).getStatus()));
						nesCell11.setFixedHeight(10);

						nestedTable1.addCell(nesCell11);
						nestedTable1.addCell(nesCell21);
						nestedTable1.addCell(nesCell31);
						nestedTable1.addCell(nesCell41);
						doc.add(nestedTable1);
						

					}
					doc.newPage();
				} else if (formats[i].equalsIgnoreCase("Pie Chart")) {
					float pd = 0;
					float pp = 0;
					float pr = 0;

					for (int j = 0; j < reportlist.size(); j++) {
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("Paid")) {
							pp = pp
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("Puchase return")) {
							pr = pr
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("delivered")) {
							pd = pd
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
					}
					BigDecimal prp = new BigDecimal((pr * 100) / (pp + pr + pd))
							.setScale(0, BigDecimal.ROUND_HALF_UP);
					BigDecimal ppp = new BigDecimal((pp * 100) / (pp + pr + pd))
							.setScale(0, BigDecimal.ROUND_HALF_UP);
					BigDecimal pdp = new BigDecimal((pd * 100) / (pp + pr + pd))
							.setScale(0, BigDecimal.ROUND_HALF_UP);

					String pie1 = "http://chart.apis.google.com/chart?cht=p&chd=t:"
							+ pdp
							+ ","
							+ prp
							+ ","
							+ ppp
							+ "&chs=500x200&chdl=Purchase%20Delivery|Purchase%20Return|Purchase%20Payment&chl="
							+ pdp
							+ "%|"
							+ prp
							+ "%|"
							+ ppp
							+ "%&chtt=Pie%20Chart&chts=FF0000,15";

					doc.add(Image.getInstance(pie1));
					doc.newPage();
				} else if (formats[i].equalsIgnoreCase("Bar Chart")) {

					float pd = 0;
					float pp = 0;
					float pr = 0;

					for (int j = 0; j < reportlist.size(); j++) {
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("Paid")) {
							pp = pp
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("Puchase return")) {
							pr = pr
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("delivered")) {
							pd = pd
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
					}

					float bigestvalue = Math.max(pp, pr);
					if (bigestvalue > pr) {
						bigestvalue = Math.max(pp, pd);
					} else {
						bigestvalue = Math.max(pr, pd);
					}
					System.out.println("The max of three is: " + bigestvalue);

					BigDecimal purtotal = new BigDecimal(bigestvalue).setScale(
							0, BigDecimal.ROUND_HALF_UP);

					System.out
							.println("--------purchase payment--------------->"
									+ pp + "---purreturn------>" + pr
									+ "-----purdel-------->" + pd
									+ "-----total------>" + purtotal);
					String bar1 = "http://chart.apis.google.com/chart?cht=bvg&chs=500x500&chd=t:"
							+ pd
							+ ","
							+ pr
							+ ","
							+ pp
							+ "&chxr=1,0,"
							+ purtotal
							+ "&chds=0,"
							+ purtotal
							+ "&chxl=0:|Purchase+Delivery|Purchase+Return|Purchase+Payment&chco=80C65A|FF0000|224499&chtt=My+Bar+Chart&chbh=a,2,75&chxt=x,y";
					doc.add(Image.getInstance(bar1));
					doc.newPage();
				}

			}
			doc.close();
			out.close();

			InputStream in = new ByteArrayInputStream(
					((ByteArrayOutputStream) out).toByteArray());

			streamedContent = new DefaultStreamedContent(in, "application/pdf");

		} catch (Exception e) {
			logger.info("-------------------vendorpdf method------>"+e.getMessage());
		}

	}

	public void customerpdf() {

		try {
			System.out.println("inside customer pdf");
			Document doc = new Document();
			OutputStream out = new ByteArrayOutputStream();
			PdfWriter writer = null;
			writer = PdfWriter.getInstance(doc, out);
			doc.open();
			String title = "";
			for (int i = 0; i < types.length; i++) {
				System.out.println("type name-->" + title
						+ types[i]+ "|");
				title = title + types[i] + "|";
			}
			doc.addTitle(title);
			System.out.println("BEFORE pdf" + formats.length);
			for (int i = 0; i < formats.length; i++) {
				System.out.println("------ffffff------------>"
						+ formats[i]);
				if (formats[i].equalsIgnoreCase("Table")) {
					PdfPTable nestedTable = new PdfPTable(4);
					nestedTable.setWidthPercentage(100f);

					PdfPCell nesCell1 = new PdfPCell(new Paragraph(
							"Order Number"));
					PdfPCell nesCell2 = new PdfPCell(new Paragraph(
							"Customer Name"));
					PdfPCell nesCell3 = new PdfPCell(new Paragraph("Price"));
					PdfPCell nesCell4 = new PdfPCell(new Paragraph("Status"));
					nesCell1.setFixedHeight(22f);

					nesCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
					nesCell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
					nesCell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
					nesCell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
					nestedTable.addCell(nesCell1);
					nestedTable.addCell(nesCell2);
					nestedTable.addCell(nesCell3);
					nestedTable.addCell(nesCell4);

					doc.add(nestedTable);
					for (int j = 0; j < reportlist.size(); j++) {
					

						PdfPTable nestedTable1 = new PdfPTable(4);
						nestedTable1.setWidthPercentage(100f);
						PdfPCell nesCell11 = new PdfPCell(new Paragraph(
								reportlist.get(j).getOrderNumber()));
						PdfPCell nesCell21 = new PdfPCell(new Paragraph(
								reportlist.get(j).getVendorName()));
						PdfPCell nesCell31 = new PdfPCell(new Paragraph(
								reportlist.get(j).getPrice()));
						PdfPCell nesCell41 = new PdfPCell(new Paragraph(
								reportlist.get(j).getStatus()));
						nesCell11.setFixedHeight(20);

						nestedTable1.addCell(nesCell11);
						nestedTable1.addCell(nesCell21);
						nestedTable1.addCell(nesCell31);
						nestedTable1.addCell(nesCell41);

						doc.add(nestedTable1);

					}
					doc.newPage();
				} else if (formats[i].equalsIgnoreCase("Pie Chart")) {
					float sd = 0;
					float sp = 0;
					float sr = 0;
					float qd = 0;
					float qp = 0;
					float qr = 0;

					for (int j = 0; j < reportlist.size(); j++) {
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("Sales Delivered")) {
							sd = sd
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("Sales Paid")) {
							sp = sp
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("Sales Return")) {
							sr = sr
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("QuickSales Delivered")) {
							qd = qd
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("QuickSales Paid")) {
							qp = qp
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("QuickSales Return")) {
							qr = qr
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
					}
					System.out.println("pie chart-sd----->"+sd+"--sp---->"+sp+"---sr------>"+sr+"---qd--->"+qd+"---qp--->"+qp+"--qr--->"+qr);
					BigDecimal sdp = new BigDecimal((sd * 100)
							/ (sd + sp + sr + qd + qp + qr)).setScale(0,
							BigDecimal.ROUND_HALF_UP);
					BigDecimal spp = new BigDecimal((sp * 100)
							/ (sd + sp + sr + qd + qp + qr)).setScale(0,
							BigDecimal.ROUND_HALF_UP);
					BigDecimal srp = new BigDecimal((sr * 100)
							/ (sd + sp + sr + qd + qp + qr)).setScale(0,
							BigDecimal.ROUND_HALF_UP);
					BigDecimal qdp = new BigDecimal((qd * 100)
							/ (sd + sp + sr + qd + qp + qr)).setScale(0,
							BigDecimal.ROUND_HALF_UP);
					BigDecimal qpp = new BigDecimal((qp * 100)
							/ (sd + sp + sr + qd + qp + qr)).setScale(0,
							BigDecimal.ROUND_HALF_UP);
					BigDecimal qrp = new BigDecimal((qr * 100)
							/ (sd + sp + sr + qd + qp + qr)).setScale(0,
							BigDecimal.ROUND_HALF_UP);
					System.out.println("pie chart-sdp----->"+sdp+"--spp---->"+spp+"---srp------>"+srp+"---qdp--->"+qdp+"---qpp--->"+qpp+"--qrp--->"+qrp);
					String pie1 = "http://chart.apis.google.com/chart?cht=p&chd=t:"
							+ sdp+ ","+ spp+ ","+ srp+ ","+ qdp+ ","+ qpp+ ","+qrp+"&chs=500x200&chdl=Sales+Delivered|Sales+Paid|Sales+Return|QuickSales+Delivered|QuickSales+Paid|QuickSales+Return&chl="
							+ sdp+ "%|"+ spp+ "%|"+ srp+ "%|"+ qdp+ "%|"+ qpp+ "%|"+ qrp+ "%&chtt=Pie%20Chart&chts=FF0000,15";
					System.out.println("---pie customer-URL->"+pie1);
					doc.add(Image.getInstance(pie1));
					doc.newPage();
				} else if (formats[i].equalsIgnoreCase("Bar Chart")) {

					float sd = 0;
					float sp = 0;
					float sr = 0;
					float qd = 0;
					float qp = 0;
					float qr = 0;

					for (int j = 0; j < reportlist.size(); j++) {
						System.out.println("----price--->"+reportlist.get(j).getPrice());
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("Sales Delivered")) {
							sd = sd
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("Sales Paid")) {
							sp = sp
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("Sales Return")) {
							sr = sr
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("QuickSales Delivered")) {
							qd = qd
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("QuickSales Paid")) {
							qp = qp
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("QuickSales Return")) {
							qr = qr
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
					}
					System.out.println("bar chart-sd----->"+sd+"--sp---->"+sp+"---sr------>"+sr+"---qd--->"+qd+"---qp--->"+qp+"--qr--->"+qr);
					float bigestvalue = Math.max(
							sd,
							Math.max(sp, Math.max(sr,
									Math.max(qd, Math.max(qp, qr)))));

					System.out.println("The max of three is: " + bigestvalue);

					BigDecimal purtotal = new BigDecimal(bigestvalue).setScale(
							0, BigDecimal.ROUND_HALF_UP);

					/*
					 * float sd=0; float sp=0; float sr=0; float qd=0; float
					 * qp=0; float qr=0;
					 */
				
					
					String bar1 = "http://chart.apis.google.com/chart?cht=bvg&chs=550x400&chd=t:"
							+ sd+ ","+ sp+ ","+ sr+ ","+ qd+ ","+ qp+ ","+ qr+"&chxr=1,0,"+ purtotal+ "&chds=0,"+ purtotal
							+ "&chxl=0:|Sales+Delivery|Sales+Payment|Salea+Return|Q.+S.+Delivery|Q.+S.+Payment|Q.+S.+Return&chco=80C65A|80C65A|80C65A|224499|224499|224499&chtt=Bar+Chart&chbh=a,1,50&chxt=x,y";
					System.out.println("---barchart customer-URL->"+bar1);
					doc.add(Image.getInstance(bar1));
					doc.newPage();
				}

			}
			doc.close();
			out.close();

			InputStream in = new ByteArrayInputStream(
					((ByteArrayOutputStream) out).toByteArray());

			streamedContent = new DefaultStreamedContent(in, "application/pdf");

		} catch (Exception e) {
			logger.info("-------------------customerpdf method------>"+e.getMessage());
		}

	}

	public void transactionpdf() {

		try {
			System.out.println("inside transaction  pdf");
			Document doc = new Document();
			OutputStream out = new ByteArrayOutputStream();
			PdfWriter writer = null;
			writer = PdfWriter.getInstance(doc, out);
			doc.open();
			String title = "";
			for (int i = 0; i < types.length; i++) {
				System.out.println("type name-->" + title
						+ types[i] + "|");
				title = title + types[i] + "|";
			}
			doc.addTitle(title);
			System.out.println("BEFORE pdf" + formats.length);
			for (int i = 0; i < formats.length; i++) {
				System.out.println("------ffffff------------>"
						+ formats[i]);
				if (formats[i].equalsIgnoreCase("Table")) {
					PdfPTable nestedTable = new PdfPTable(4);
					nestedTable.setWidthPercentage(100f);

					PdfPCell nesCell1 = new PdfPCell(new Paragraph(
							"Order Number"));
					PdfPCell nesCell2 = new PdfPCell(new Paragraph(
							"Transaction Date"));
					PdfPCell nesCell3 = new PdfPCell(new Paragraph("Price"));
					PdfPCell nesCell4 = new PdfPCell(new Paragraph("Status"));
					nesCell1.setFixedHeight(22f);

					nesCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
					nesCell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
					nesCell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
					nesCell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
					nestedTable.addCell(nesCell1);
					nestedTable.addCell(nesCell2);
					nestedTable.addCell(nesCell3);
					nestedTable.addCell(nesCell4);

					doc.add(nestedTable);
					for (int j = 0; j < reportlist.size(); j++) {
						System.out.println("for loop");

						PdfPTable nestedTable1 = new PdfPTable(4);
						nestedTable1.setWidthPercentage(100f);
						PdfPCell nesCell11 = new PdfPCell(new Paragraph(
								reportlist.get(j).getOrderNumber()));
						PdfPCell nesCell21 = new PdfPCell(new Paragraph(""
								+ reportlist.get(j).getTransdate()));
						PdfPCell nesCell31 = new PdfPCell(new Paragraph(
								reportlist.get(j).getPrice()));
						PdfPCell nesCell41 = new PdfPCell(new Paragraph(
								reportlist.get(j).getStatus()));
						nesCell11.setFixedHeight(20);

						nestedTable1.addCell(nesCell11);
						nestedTable1.addCell(nesCell21);
						nestedTable1.addCell(nesCell31);
						nestedTable1.addCell(nesCell41);

						doc.add(nestedTable1);

					}
					doc.newPage();
				} else if (formats[i].equalsIgnoreCase("Pie Chart")) {
					float debit = 0;
					float credit = 0;

					for (int j = 0; j < reportlist.size(); j++) {
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("Credit")) {
							credit = credit
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("Debit")) {
							debit = debit
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}

					}

					BigDecimal debitp = new BigDecimal((debit * 100)
							/ (debit + credit)).setScale(0,
							BigDecimal.ROUND_HALF_UP);
					BigDecimal creditp = new BigDecimal((credit * 100)
							/ (debit + credit)).setScale(0,
							BigDecimal.ROUND_HALF_UP);

					String pie1 = "http://chart.apis.google.com/chart?cht=p&chd=t:"
							+ debitp
							+ ","
							+ creditp
							+ "&chs=500x200&chdl=Debit|Credit&chl="
							+ debitp
							+ "%|"
							+ creditp
							+ "%&chtt=Pie%20Chart&chts=FF0000,15";
					System.out.println("---pie transaction-URL->"+pie1);
					doc.add(Image.getInstance(pie1));
					doc.newPage();
				} else if (formats[i].equalsIgnoreCase("Bar Chart")) {

					float debit = 0;
					float credit = 0;

					for (int j = 0; j < reportlist.size(); j++) {
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("Credit")) {
							credit = credit
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}
						if (reportlist.get(j).getStatus()
								.equalsIgnoreCase("Debit")) {
							debit = debit
									+ Float.parseFloat(reportlist.get(j)
											.getPrice());
						}

					}

					float bigestvalue = Math.max(credit, debit);

					System.out.println("The max of two is: " + bigestvalue);

					BigDecimal profittotal = new BigDecimal(bigestvalue)
							.setScale(0, BigDecimal.ROUND_HALF_UP);

					System.out.println("--------credit--------------->"
							+ credit + "---debit------>" + debit
							+ "------total------>" + profittotal);
					String bar1 = "http://chart.apis.google.com/chart?cht=bvg&chs=600x400&chd=t:"
							+ debit
							+ ","
							+ credit
							+ "&chxr=1,0,"
							+ profittotal
							+ "&chds=0,"
							+ profittotal
							+ "&chxl=0:|Debit|Credit&chco=80C65A|FF0000&chtt=Bar+Chart&chbh=a,1,180&chxt=x,y";
					
					System.out.println("---barchart transaction-URL->"+bar1);
					doc.add(Image.getInstance(bar1));
					doc.newPage();
				}

			}
			doc.close();
			out.close();

			InputStream in = new ByteArrayInputStream(
					((ByteArrayOutputStream) out).toByteArray());

			streamedContent = new DefaultStreamedContent(in, "application/pdf");

		} catch (Exception e) {
			logger.info("-------------------transactionpdf method------>"+e.getMessage());
		}

	}

	private Phrase Phrase(PieChartModel contentByte) {
		// TODO Auto-generated method stub
		return null;
	}

	public StreamedContent getStreamedContent() {
		if (FacesContext.getCurrentInstance().getRenderResponse()) {
			return new DefaultStreamedContent();
		} else {
			return streamedContent;
		}
	}

	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}

	java.util.List<String> week1 = new ArrayList<String>();

	public java.util.List<String> getWeek1() {
		return week1;
	}

	public void setWeek1(java.util.List<String> week1) {
		this.week1 = week1;
	}

	public void getweek(ValueChangeEvent vc) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String week = vc.getNewValue().toString();
		week1.clear();
		String day = "01";
		try {
			Date date = sdf.parse(day + "-" + month + "-" + year);

			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			calendar.setTime(date);
			calendar.setFirstDayOfWeek(Calendar.SUNDAY);

			int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

			for (int currentDay = 01; currentDay <= daysInMonth; currentDay++) {
				Date newDate = sdf.parse(currentDay + "-" + month + "-" + year);
				System.out.println(newDate);
				calendar.setTime(newDate);
				if (calendar.get(Calendar.WEEK_OF_MONTH) == Integer
						.parseInt(week)) {
					week1.add(String.valueOf(newDate));
				}

			}
			for (int i = 0; i < week1.size(); i++) {
				System.out.println("------" + week1.get(i));
			}

			System.out.println("date start and end----->" + week1.get(0)
					+ "--------->" + week1.get(week1.size() - 1));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/* Vendor Report */
	
	ReportDatabean reportDatabean=new ReportDatabean();
	private boolean reportFlag=false;
	private boolean purdeliveryreportFlag=false;
	private boolean purreturnreportFlag=false;
	private boolean purclosereportFlag=false;
	private boolean detailReportFlag=false;
	private boolean purretunndetailReportFlag=false;
	private java.util.List<String> products=null;
	ArrayList<ReportDatabean> filteredList;
	private boolean allReportFlag=false;
	
	public boolean isAllReportFlag() {
		return allReportFlag;
	}

	public void setAllReportFlag(boolean allReportFlag) {
		this.allReportFlag = allReportFlag;
	}

	public boolean isPurretunndetailReportFlag() {
		return purretunndetailReportFlag;
	}

	public void setPurretunndetailReportFlag(boolean purretunndetailReportFlag) {
		this.purretunndetailReportFlag = purretunndetailReportFlag;
	}

	public boolean isPurdeliveryreportFlag() {
		return purdeliveryreportFlag;
	}

	public void setPurdeliveryreportFlag(boolean purdeliveryreportFlag) {
		this.purdeliveryreportFlag = purdeliveryreportFlag;
	}

	public boolean isPurreturnreportFlag() {
		return purreturnreportFlag;
	}

	public void setPurreturnreportFlag(boolean purreturnreportFlag) {
		this.purreturnreportFlag = purreturnreportFlag;
	}

	public boolean isPurclosereportFlag() {
		return purclosereportFlag;
	}

	public void setPurclosereportFlag(boolean purclosereportFlag) {
		this.purclosereportFlag = purclosereportFlag;
	}

	public ArrayList<ReportDatabean> getFilteredList() {
		return filteredList;
	}

	public void setFilteredList(ArrayList<ReportDatabean> filteredList) {
		this.filteredList = filteredList;
	}

	public java.util.List<String> getProducts() {
		return products;
	}

	public void setProducts(java.util.List<String> products) {
		this.products = products;
	}

	public boolean isReportFlag() {
		return reportFlag;
	}

	public void setReportFlag(boolean reportFlag) {
		this.reportFlag = reportFlag;
	}

	public boolean isDetailReportFlag() {
		return detailReportFlag;
	}

	public void setDetailReportFlag(boolean detailReportFlag) {
		this.detailReportFlag = detailReportFlag;
	}

	public ReportDatabean getReportDatabean() {
		return reportDatabean;
	}

	public void setReportDatabean(ReportDatabean reportDatabean) {
		this.reportDatabean = reportDatabean;
	}

	public void report() {
		logger.info("[report()]-------------------Inside report() in MB Calling-----------------------------");
		reportDatabean.setFromDate(null);
		reportDatabean.setToDate(null);
		reportDatabean.setAllName("");
		reportDatabean.setName("");
		reportDatabean.setType("");
		setReportFlag(false);
		setDetailReportFlag(false);
		setPurdeliveryreportFlag(false);
		setPurreturnreportFlag(false);
		setPurclosereportFlag(false);
		setPurretunndetailReportFlag(false);
		setAllReportFlag(false);
	}

	public void typeValueChange(ValueChangeEvent vc) {
		logger.info("[typeValueChange()]-------------------Inside typeValueChange() in MB Calling-----------------------------");
		reportDatabean.setName("");reportDatabean.setAllName("");
	}
	
	private boolean vendorreportValidation(boolean valid){
		logger.info("[vendorreportValidation()]---------------------------Inside vendorreportValidation() in MB Calling-------------------------------------");
		valid=true;String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			if (reportDatabean.getFromDate() == null) {
				fieldName = CommonValidate.findComponentInRoot("vfdate").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the From Date."));
				valid=false;
			}
			if (reportDatabean.getToDate() == null) {
				fieldName = CommonValidate.findComponentInRoot("vtdate").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the To Date."));
				valid=false;
			}
			if (StringUtils.isBlank(reportDatabean.getType())) {
				fieldName = CommonValidate.findComponentInRoot("vt").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select the Vendor type"));
				valid=false;
			}else if (reportDatabean.getType().equalsIgnoreCase("single")) {
				if (reportDatabean.getName().equals("")) {
					fieldName = CommonValidate.findComponentInRoot("vn").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter the Vendor Name"));
					valid=false;
				}
			}else if (reportDatabean.getType().equalsIgnoreCase("multiple")) {
				if (reportDatabean.getAllName().equals("")) {
					fieldName = CommonValidate.findComponentInRoot("av").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Choose the Vendor Name"));
					valid=false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return valid;
	}
	
	public String searchVendorReport() {
		logger.info("[searchVendorReport()]---------------------------Inside searchVendorReport() in MB Calling-------------------------------------");
		BigDecimal quan = BigDecimal.valueOf(0);String name="";String phonenumber="";String status="";
		setReportFlag(false);setDetailReportFlag(false);
		try{
			reportDatabean.setOrdernumberList(new ArrayList<String>());
			reportDatabean.setReportList(new ArrayList<ReportDatabean>());reportDatabean.setReportLists(new ArrayList<ReportDatabean>());
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
			reportDatabean.setClientID(clientID);reportDatabean.setUserID(userID);reportDatabean.setUserType(userType);
			if(vendorreportValidation(true)){
				ReportJDBC.vendorReportSearch(reportDatabean);
				if(reportDatabean.getReportList().size()>0){
					Collections.sort(reportDatabean.getOrdernumberList());
					for (int i = 0; i < reportDatabean.getOrdernumberList().size(); i++) {
						BigDecimal price = BigDecimal.valueOf(0);
						ReportDatabean domain = new ReportDatabean();	
						for (int j = 0; j < reportDatabean.getReportList().size(); j++) {
							try{
								if (reportDatabean.getReportList().get(j).getOrderNumber().equals(reportDatabean.getOrdernumberList().get(i))) {
									quan = quan.add(new BigDecimal(reportDatabean.getReportList().get(j).getQuantity()));
									price = price.add(new BigDecimal(reportDatabean.getReportList().get(j).getPrice())
												.multiply(new BigDecimal(reportDatabean.getReportList().get(j).getQuantity())));
									name=reportDatabean.getReportList().get(j).getName();
									phonenumber=reportDatabean.getReportList().get(j).getPhoneNumber();
									status=reportDatabean.getReportList().get(j).getStatus();
								}
							}catch(Exception e){
								e.printStackTrace();
							}
						}
						domain.setName(name);
						domain.setPhoneNumber(phonenumber);
						domain.setStatus(status);
						domain.setOrderNumber(reportDatabean.getOrdernumberList().get(i));
						domain.setPrice(String.valueOf(price));
						reportDatabean.getReportLists().add(domain);
					}
				}
				setReportFlag(true);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public void onRowSelectEvent(final SelectEvent event) throws IOException,SQLException {
		logger.info("[onRowSelectEvent()]---------------------------Inside onRowSelectEvent() in MB Calling-------------------------------------");
		try {
			String orderno = ((ReportDatabean) event.getObject()).getOrderNumber();
			reportDatabean.setDetailReportLists(new ArrayList<ReportDatabean>());
			BigDecimal nettemp = BigDecimal.valueOf(0);
			for (int i = 0; i < reportDatabean.getReportList().size(); i++) {
				BigDecimal totprice = BigDecimal.valueOf(0);
				if (orderno.equalsIgnoreCase(reportDatabean.getReportList().get(i).getOrderNumber())) {
					totprice = totprice.add(new BigDecimal(reportDatabean.getReportList().get(i).getQuantity())
								.multiply(new BigDecimal(reportDatabean.getReportList().get(i).getPrice())));
					ReportDatabean domain = new ReportDatabean();
					domain.setOrderNumber(reportDatabean.getReportList().get(i).getOrderNumber());
					domain.setProductName(reportDatabean.getReportList().get(i).getProductName());
					domain.setQuantity(reportDatabean.getReportList().get(i).getQuantity());
					domain.setPrice(reportDatabean.getReportList().get(i).getPrice());
					domain.setTotalPrice(String.valueOf(totprice));
					reportDatabean.getDetailReportLists().add(domain);
					nettemp = nettemp.add(totprice);
					reportDatabean.setTotalAmount(String.valueOf(nettemp));
				}
			}
			setDetailReportFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Customer Report */
	
	private boolean customerreportValidation(boolean valid){
		logger.info("[customerreportValidation()]---------------------------Inside customerreportValidation() in MB Calling-------------------------------------");
		valid=true;String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			if (reportDatabean.getFromDate() == null) {
				fieldName = CommonValidate.findComponentInRoot("cfdate").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the From Date."));
				valid=false;
			}
			if (reportDatabean.getToDate() == null) {
				fieldName = CommonValidate.findComponentInRoot("ctdate").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the To Date."));
				valid=false;
			}
			if (StringUtils.isBlank(reportDatabean.getType())) {
				fieldName = CommonValidate.findComponentInRoot("ct").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select the Customer type"));
				valid=false;
			}else if (reportDatabean.getType().equalsIgnoreCase("single")) {
				if (reportDatabean.getName().equals("")) {
					fieldName = CommonValidate.findComponentInRoot("cn").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter the Customer Name"));
					valid=false;
				}
			}else if (reportDatabean.getType().equalsIgnoreCase("multiple")) {
				if (reportDatabean.getAllName().equals("")) {
					fieldName = CommonValidate.findComponentInRoot("ac").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Choose the Customer Name"));
					valid=false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return valid;
	}
	
	public String searchCustomerReport() {
		logger.info("[searchCustomerReport()]---------------------------Inside searchCustomerReport() in MB Calling-------------------------------------");
		String name="";String phonenumber="";String status="";
		setReportFlag(false);setDetailReportFlag(false);
		try{
			reportDatabean.setOrdernumberList(new ArrayList<String>());
			reportDatabean.setReportList(new ArrayList<ReportDatabean>());reportDatabean.setReportLists(new ArrayList<ReportDatabean>());
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
			reportDatabean.setClientID(clientID);reportDatabean.setUserID(userID);reportDatabean.setUserType(userType);
			if(customerreportValidation(true)){
				ReportJDBC.customerReportsearch(reportDatabean);
				if(reportDatabean.getReportList().size()>0){
					Collections.sort(reportDatabean.getOrdernumberList());
					for (int i = 0; i < reportDatabean.getOrdernumberList().size(); i++) {
						BigDecimal crosstotal = BigDecimal.valueOf(0);
						ReportDatabean domain = new ReportDatabean();	
						for (int j = 0; j < reportDatabean.getReportList().size(); j++) {
							try{
								if (reportDatabean.getReportList().get(j).getOrderNumber().equals(reportDatabean.getOrdernumberList().get(i))) {
									crosstotal = new BigDecimal(reportDatabean.getReportList().get(j).getCrossTotal());
									name=reportDatabean.getReportList().get(j).getName();
									phonenumber=reportDatabean.getReportList().get(j).getPhoneNumber();
									status=reportDatabean.getReportList().get(j).getStatus();
								}
							}catch(Exception e){
								e.printStackTrace();
							}
						}
						domain.setName(name);
						domain.setPhoneNumber(phonenumber);
						domain.setStatus(status);
						domain.setOrderNumber(reportDatabean.getOrdernumberList().get(i));
						domain.setPrice(String.valueOf(crosstotal));
						reportDatabean.getReportLists().add(domain);	
					}
				}
				setReportFlag(true);	
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public void onRowSelectEvent1(final SelectEvent event) throws IOException,SQLException {
		logger.info("[onRowSelectEvent1()]---------------------------Inside onRowSelectEvent1() in MB Calling-------------------------------------");
		try {
			String orderno = ((ReportDatabean) event.getObject()).getOrderNumber();
			String name = ((ReportDatabean) event.getObject()).getName();
			reportDatabean.setDetailReportLists(new ArrayList<ReportDatabean>());
			BigDecimal nettemp = BigDecimal.valueOf(0);
			for (int i = 0; i < reportDatabean.getReportList().size(); i++) {
				if (name.equalsIgnoreCase(reportDatabean.getReportList().get(i).getName())) {
					if(orderno.equalsIgnoreCase(reportDatabean.getReportList().get(i).getOrderNumber())){
						ReportDatabean domain = new ReportDatabean();
						domain.setOrderNumber(reportDatabean.getReportList().get(i).getOrderNumber());
						domain.setProductName(reportDatabean.getReportList().get(i).getProductName());
						domain.setQuantity(reportDatabean.getReportList().get(i).getQuantity());
						domain.setPrice(reportDatabean.getReportList().get(i).getPrice());
						domain.setTotalPrice(reportDatabean.getReportList().get(i).getTotalPrice());
						reportDatabean.getDetailReportLists().add(domain);
						nettemp = nettemp.add(new BigDecimal(reportDatabean.getReportList().get(i).getTotalPrice()));
						reportDatabean.setShippingCost(reportDatabean.getReportList().get(i).getShippingCost());
						reportDatabean.setTotalAmount(nettemp.add(new BigDecimal(reportDatabean.getShippingCost())).toString());
					}
				}
			}
			setDetailReportFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Stock Report */
	
	public String dropDownProduct() {
		logger.info("[inside dropDownProduct()]-------------------inside dropDownProduct() method()---------------");
		try {
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			products=new ArrayList<String>();
			setProducts(controller.productVendor(products));
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";
	}
	
	public void stockreport() {
		logger.info("[stockreport()]-------------------Inside stockreport() in MB Calling-----------------------------");
		reportDatabean.setProductName("");
		setReportFlag(false);
		dropDownProduct();
	}
	
	public String searchStockreport() {
		logger.info("[searchStockreport()]----------------- Inside searchStockreport() method()----------------------- ");
		setReportFlag(false);String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			reportDatabean.setClientID(clientID);
			reportDatabean.setReportList(new ArrayList<ReportDatabean>());reportDatabean.setReportLists(new ArrayList<ReportDatabean>());
			if (reportDatabean.getProductName().equalsIgnoreCase("")){
				fieldName = CommonValidate.findComponentInRoot("pname").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the Product Name"));
			}else{
				ReportJDBC.stockReportSearch(reportDatabean);
				if (reportDatabean.getProductName().equalsIgnoreCase("All")){
					reportDatabean.setReportLists(reportDatabean.getReportList());
				}else{
					for (int i = 0; i < reportDatabean.getReportList().size(); i++) {
						if(reportDatabean.getProductName().equalsIgnoreCase(reportDatabean.getReportList().get(i).getProductName())){
							ReportDatabean domain=new ReportDatabean();
							domain.setPrice(reportDatabean.getReportList().get(i).getPrice());
							domain.setQuantity(reportDatabean.getReportList().get(i).getQuantity());
							domain.setProductName(reportDatabean.getReportList().get(i).getProductName());
							domain.setStockinQuantity(reportDatabean.getReportList().get(i).getStockinQuantity());
							domain.setStockoutQuantity(reportDatabean.getReportList().get(i).getStockoutQuantity());
							domain.setStockdamageQuantity(reportDatabean.getReportList().get(i).getStockdamageQuantity());
							reportDatabean.getReportLists().add(domain);
						}
					}
				}
				setReportFlag(true);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";

	}
	
	private boolean purchasereportValidation(boolean valid){
		logger.info("[purchasereportValidation()]---------------------------Inside purchasereportValidation() in MB Calling-------------------------------------");
		valid=true;String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			if (reportDatabean.getFromDate() == null) {
				fieldName = CommonValidate.findComponentInRoot("purfdate").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the From Date."));
				valid=false;
			}
			if (reportDatabean.getToDate() == null) {
				fieldName = CommonValidate.findComponentInRoot("purtdate").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the To Date."));
				valid=false;
			}
			if (StringUtils.isBlank(reportDatabean.getType())) {
				fieldName = CommonValidate.findComponentInRoot("purt").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select the Purchase type"));
				valid=false;
			}else if (reportDatabean.getType().equalsIgnoreCase("single")) {
				if (reportDatabean.getName().equals("")) {
					fieldName = CommonValidate.findComponentInRoot("purn").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter the Vendor Name"));
					valid=false;
				}
			}else if (reportDatabean.getType().equalsIgnoreCase("multiple")) {
				if (reportDatabean.getAllName().equals("")) {
					fieldName = CommonValidate.findComponentInRoot("apur").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Choose the type"));
					valid=false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return valid;
	}
	
	public String searchPurchaseReport(){
		logger.info("[searchPurchaseReport()]----------------- Inside searchPurchaseReport() method()----------------------- ");
		setReportFlag(false);String name="";String status="";Date date=null;setDetailReportFlag(false);setPurretunndetailReportFlag(false);
		setPurdeliveryreportFlag(false);setPurreturnreportFlag(false);setPurclosereportFlag(false);String quantity="";
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
			if(purchasereportValidation(true)){
				reportDatabean.setClientID(clientID);reportDatabean.setUserID(userID);reportDatabean.setUserType(userType);
				reportDatabean.setOrdernumberList(new ArrayList<String>());
				reportDatabean.setReportList(new ArrayList<ReportDatabean>());reportDatabean.setReportLists(new ArrayList<ReportDatabean>());
				ReportJDBC.purchasereportsearch(reportDatabean);
				if(reportDatabean.getType().equals("single") || reportDatabean.getType().equals("multiple")){
					if (!reportDatabean.getName().equalsIgnoreCase("") || reportDatabean.getAllName().equalsIgnoreCase("All")) {
						if(reportDatabean.getReportList().size()>0){
							for (int i = 0; i < reportDatabean.getOrdernumberList().size(); i++) {
								BigDecimal quan = BigDecimal.valueOf(0);
								BigDecimal totprice = BigDecimal.valueOf(0);
								for (int j = 0; j < reportDatabean.getReportList().size(); j++) {
									try{
										if (reportDatabean.getReportList().get(j).getOrderNumber().equalsIgnoreCase(reportDatabean.getOrdernumberList().get(i))) {
											quan = quan.add(new BigDecimal(reportDatabean.getReportList().get(j).getQuantity()));
											totprice = totprice.add(new BigDecimal(reportDatabean.getReportList().get(j).getQuantity())
															.multiply(new BigDecimal(reportDatabean.getReportList().get(j).getPrice())));
											name=reportDatabean.getReportList().get(j).getName();
											date=reportDatabean.getReportList().get(j).getOrderDate();
											status=reportDatabean.getReportList().get(j).getStatus();
										}
									}catch(Exception e){
										e.printStackTrace();
									}
								}
								ReportDatabean domain=new ReportDatabean();
								domain.setName(name);
								domain.setOrderDate(date);
								domain.setOrderNumber(reportDatabean.getOrdernumberList().get(i));
								domain.setQuantity(String.valueOf(quan));
								domain.setPrice(String.valueOf(totprice));
								domain.setStatus(status);
								reportDatabean.getReportLists().add(domain);
							}
						}
						setReportFlag(true);
					}else if(reportDatabean.getAllName().equalsIgnoreCase("Purchase Delivery")){
						for (int j = 0; j < reportDatabean.getReportList().size(); j++) {
							if(reportDatabean.getReportList().get(j).getStatus().equals("delivered")){
								ReportDatabean domain=new ReportDatabean();
								domain.setName(reportDatabean.getReportList().get(j).getName());
								domain.setProductName(reportDatabean.getReportList().get(j).getProductName());
								domain.setOrderNumber(reportDatabean.getReportList().get(j).getOrderNumber());
								domain.setQuantity(reportDatabean.getReportList().get(j).getQuantity());
								domain.setPrice(reportDatabean.getReportList().get(j).getPrice());
								domain.setStatus(reportDatabean.getReportList().get(j).getStatus());
								reportDatabean.getReportLists().add(domain);
							}
						}
						setPurdeliveryreportFlag(true);
					}else if(reportDatabean.getAllName().equalsIgnoreCase("Purchase Close")){
						for (int j = 0; j < reportDatabean.getReportList().size(); j++) {
							if(reportDatabean.getReportList().get(j).getPaymentStatus().equals("paid")){
								ReportDatabean domain=new ReportDatabean();
								domain.setName(reportDatabean.getReportList().get(j).getName());
								domain.setProductName(reportDatabean.getReportList().get(j).getProductName());
								domain.setOrderNumber(reportDatabean.getReportList().get(j).getOrderNumber());
								domain.setQuantity(reportDatabean.getReportList().get(j).getQuantity());
								domain.setPrice(reportDatabean.getReportList().get(j).getPrice());
								domain.setTotalPrice(new BigDecimal(reportDatabean.getReportList().get(j).getQuantity())
														.multiply(new BigDecimal(reportDatabean.getReportList().get(j).getPrice())).toString());
								domain.setPaymentStatus(reportDatabean.getReportList().get(j).getPaymentStatus());
								reportDatabean.getReportLists().add(domain);
							}
						}
						setPurclosereportFlag(true);
					}else if(reportDatabean.getAllName().equalsIgnoreCase("Purchase Return")){
						for (int i = 0; i < reportDatabean.getOrdernumberList().size(); i++) {
							BigDecimal nr = BigDecimal.valueOf(0);
							BigDecimal dr = BigDecimal.valueOf(0);
							for (int j = 0; j < reportDatabean.getReportList().size(); j++) {
								if (reportDatabean.getReportList().get(j).getOrderNumber().equalsIgnoreCase(reportDatabean.getOrdernumberList().get(i))) {
									quantity=reportDatabean.getReportList().get(j).getQuantity();
									nr = nr.add(new BigDecimal(reportDatabean.getReportList().get(j).getNormalReturn()));
									dr = dr.add(new BigDecimal(reportDatabean.getReportList().get(j).getDamageReturn()));
									
								}
							}
							ReportDatabean domain=new ReportDatabean();
							domain.setQuantity(quantity);
							domain.setNormalReturn(String.valueOf(nr));
							domain.setOrderNumber(reportDatabean.getOrdernumberList().get(i));
							domain.setDamageReturn(String.valueOf(dr));
							domain.setStatus("Returned");
							reportDatabean.getReportLists().add(domain);
						}
						setPurreturnreportFlag(true);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public void onPurchaseRowSelectEvent(final SelectEvent event) {
		logger.info("[onPurchaseRowSelectEvent()]---------------------------Inside onPurchaseRowSelectEvent() in MB Calling-------------------------------------");
		try {
			String orderno = ((ReportDatabean) event.getObject()).getOrderNumber();
			reportDatabean.setDetailReportLists(new ArrayList<ReportDatabean>());
			BigDecimal nettemp = BigDecimal.valueOf(0);
			for (int i = 0; i < reportDatabean.getReportList().size(); i++) {
				if(orderno.equalsIgnoreCase(reportDatabean.getReportList().get(i).getOrderNumber())){
					ReportDatabean domain = new ReportDatabean();
					domain.setOrderNumber(reportDatabean.getReportList().get(i).getOrderNumber());
					domain.setProductName(reportDatabean.getReportList().get(i).getProductName());
					domain.setQuantity(reportDatabean.getReportList().get(i).getQuantity());
					domain.setStatus(reportDatabean.getReportList().get(i).getStatus());
					domain.setPaymentStatus(reportDatabean.getReportList().get(i).getPaymentStatus());
					domain.setPrice(reportDatabean.getReportList().get(i).getPrice());
					domain.setTotalPrice(new BigDecimal(reportDatabean.getReportList().get(i).getQuantity()).
							multiply(new BigDecimal(reportDatabean.getReportList().get(i).getPrice())).toString());
					reportDatabean.getDetailReportLists().add(domain);
					nettemp = nettemp.add(new BigDecimal(domain.getTotalPrice()));
					reportDatabean.setTotalAmount(nettemp.toString());
				}
			}
			setDetailReportFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onPurchaseReturnRowSelectEvent(final SelectEvent event) {
		logger.info("[onPurchaseReturnRowSelectEvent()]---------------------------Inside onPurchaseReturnRowSelectEvent() in MB Calling-------------------------------------");
		try {
			String orderno = ((ReportDatabean) event.getObject()).getOrderNumber();
			reportDatabean.setDetailReportLists(new ArrayList<ReportDatabean>());
			for (int i = 0; i < reportDatabean.getReportList().size(); i++) {
				if(orderno.equalsIgnoreCase(reportDatabean.getReportList().get(i).getOrderNumber())){
					ReportDatabean domain = new ReportDatabean();
					domain.setOrderNumber(reportDatabean.getReportList().get(i).getOrderNumber());
					domain.setProductName(reportDatabean.getReportList().get(i).getProductName());
					domain.setName(reportDatabean.getReportList().get(i).getName());
					domain.setOrderDate(reportDatabean.getReportList().get(i).getOrderDate());
					domain.setNormalReturn(reportDatabean.getReportList().get(i).getNormalReturn());
					domain.setDamageReturn(reportDatabean.getReportList().get(i).getDamageReturn());
					domain.setQuantity(reportDatabean.getReportList().get(i).getQuantity());
					domain.setStatus("Returned");
					reportDatabean.getDetailReportLists().add(domain);
				}
			}
			setPurretunndetailReportFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private boolean salesreportValidation(boolean valid){
		logger.info("[salesreportValidation()]---------------------------Inside salesreportValidation() in MB Calling-------------------------------------");
		valid=true;String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			if (reportDatabean.getFromDate() == null) {
				fieldName = CommonValidate.findComponentInRoot("salesfdate").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the From Date."));
				valid=false;
			}
			if (reportDatabean.getToDate() == null) {
				fieldName = CommonValidate.findComponentInRoot("salestdate").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the To Date."));
				valid=false;
			}
			if (StringUtils.isBlank(reportDatabean.getType())) {
				fieldName = CommonValidate.findComponentInRoot("salest").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select the Purchase type"));
				valid=false;
			}else if (reportDatabean.getType().equalsIgnoreCase("single")) {
				if (reportDatabean.getName().equals("")) {
					fieldName = CommonValidate.findComponentInRoot("salesn").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter the Customer Name"));
					valid=false;
				}
			}else if (reportDatabean.getType().equalsIgnoreCase("multiple")) {
				if (reportDatabean.getAllName().equals("")) {
					fieldName = CommonValidate.findComponentInRoot("asales").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Choose the type"));
					valid=false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return valid;
	}
	
	public String searchSalesReport(){
		logger.info("[searchSalesReport()]----------------- Inside searchSalesReport() method()----------------------- ");
		setAllReportFlag(false);setReportFlag(false);String name="";String status="";Date date=null;setDetailReportFlag(false);setPurretunndetailReportFlag(false);
		setPurdeliveryreportFlag(false);setPurreturnreportFlag(false);setPurclosereportFlag(false);String quantity="";
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
			if(salesreportValidation(true)){
				reportDatabean.setClientID(clientID);reportDatabean.setUserID(userID);reportDatabean.setUserType(userType);
				reportDatabean.setOrdernumberList(new ArrayList<String>());
				reportDatabean.setReportList(new ArrayList<ReportDatabean>());reportDatabean.setReportLists(new ArrayList<ReportDatabean>());
				ReportJDBC.salesreportsearch(reportDatabean);
				System.out.println("list size----"+reportDatabean.getReportList().size());
				if(reportDatabean.getType().equals("single")){
					if (!reportDatabean.getName().equalsIgnoreCase("")) {
						if(reportDatabean.getReportList().size()>0){
							for (int j = 0; j < reportDatabean.getReportList().size(); j++) {
								if(reportDatabean.getName().equalsIgnoreCase(reportDatabean.getReportList().get(j).getName())){
									ReportDatabean domain=new ReportDatabean();
									domain.setName(reportDatabean.getReportList().get(j).getName());
									domain.setPhoneNumber(reportDatabean.getReportList().get(j).getPhoneNumber());
									domain.setOrderDate(reportDatabean.getReportList().get(j).getOrderDate());
									domain.setOrderNumber(reportDatabean.getReportList().get(j).getOrderNumber());
									domain.setQuantity(reportDatabean.getReportList().get(j).getQuantity());
									domain.setPrice(reportDatabean.getReportList().get(j).getPrice());
									domain.setStatus(reportDatabean.getReportList().get(j).getStatus());
									reportDatabean.getReportLists().add(domain);
								}
							}
						}
					}
					setReportFlag(true);
				}else if(reportDatabean.getType().equals("multiple")){
					if(reportDatabean.getAllName().equalsIgnoreCase("All")){
						for (int i = 0; i < reportDatabean.getOrdernumberList().size(); i++) {
							BigDecimal totprice = BigDecimal.valueOf(0);
							BigDecimal quan = BigDecimal.valueOf(0);
							for (int j = 0; j < reportDatabean.getReportList().size(); j++) {
								if (reportDatabean.getReportList().get(j).getPhoneNumber()
										.equals(reportDatabean.getOrdernumberList().get(i))) {
									quan = quan.add(new BigDecimal(reportDatabean.getReportList().get(j).getQuantity()));
									totprice = totprice.add(new BigDecimal(reportDatabean.getReportList().get(j).getPrice())
											.multiply(new BigDecimal(reportDatabean.getReportList().get(j).getQuantity())));
									name=reportDatabean.getReportList().get(j).getName();
								}
							}
							ReportDatabean domain=new ReportDatabean();
							domain.setName(name);
							domain.setPhoneNumber(reportDatabean.getOrdernumberList().get(i));
							domain.setQuantity(String.valueOf(quan));
							domain.setTotalPrice(String.valueOf(totprice));
							reportDatabean.getReportLists().add(domain);
						}
						setAllReportFlag(true);
					}else if(reportDatabean.getAllName().equalsIgnoreCase("Sales Delivery")){
						if(reportDatabean.getReportList().size()>0){
							for (int j = 0; j < reportDatabean.getReportList().size(); j++) {
								if(reportDatabean.getReportList().get(j).getStatus().equalsIgnoreCase("Delivered")&&reportDatabean.getReportList().get(j).getDeliveryStatus().equalsIgnoreCase("Normal sales")){
									ReportDatabean domain=new ReportDatabean();
									domain.setName(reportDatabean.getReportList().get(j).getName());
									domain.setOrderDate(reportDatabean.getReportList().get(j).getOrderDate());
									domain.setOrderNumber(reportDatabean.getReportList().get(j).getOrderNumber());
									domain.setQuantity(reportDatabean.getReportList().get(j).getQuantity());
									domain.setPrice(reportDatabean.getReportList().get(j).getPrice());
									domain.setStatus(reportDatabean.getReportList().get(j).getStatus());
									domain.setPhoneNumber(reportDatabean.getReportList().get(j).getPhoneNumber());
									domain.setProductName(reportDatabean.getReportList().get(j).getProductName());
									reportDatabean.getReportLists().add(domain);
								}
							}
						}
						setPurdeliveryreportFlag(true);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
}
