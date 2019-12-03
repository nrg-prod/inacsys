package com.inacsys.managedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.validation.constraints.Null;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.EmployeePayroll;
import com.inacsys.domain.StockReport;
import com.inacsys.shared.Employeepayroll;
import com.inacsys.util.CommonValidate;
import com.inacsys.util.StockReportJDBC;

/*
 // John 20-06-2016
 */
@ManagedBean(name = "stockReportMB")
public class StockReportMB {
	private static Logger logger = Logger.getLogger(StockReportMB.class);
	@ManagedProperty(value = "#{stockDamageMB}")
	StockDamageMB stockDamageMB;
	public String validate;
	public boolean AllFlag = false;
	public boolean EmployeeFlag = false;
	public boolean StockdamageFlag = false;
	public boolean StockInFlag = false;
	public String Type;
	public Date FromDate;
	public Date ToDate;
	public String EmployeeName;

	public ArrayList<StockReport> stockReportlist = null;
	public ArrayList<StockReport> allstockList = new ArrayList<StockReport>();
	public ArrayList<StockReport> employeeList = null;
	public ArrayList<StockReport> allemployeeList = new ArrayList<StockReport>();
	public List<EmployeePayroll> EmployeeSingleDetail = new ArrayList<EmployeePayroll>();
	public ArrayList<StockReportMB> detailList = new ArrayList<StockReportMB>();

	public String Eadvanceamount;
	public String Etotalsalary;
	public String Ename;
	public String employeeid;
	public String Ebasicsalary;
	public String Edesignation;
	public String Emonth;
	public String Ecommission;
	public String Eovertime;
	public int Eworkingdays;
	public String Eid;
	public String Epayrolldate;

	public String getEid() {
		return Eid;
	}

	public void setEid(String eid) {
		Eid = eid;
	}

	public String getEpayrolldate() {
		return Epayrolldate;
	}

	public void setEpayrolldate(String epayrolldate) {
		Epayrolldate = epayrolldate;
	}

	public ArrayList<StockReportMB> getDetailList() {
		return detailList;
	}

	public void setDetailList(ArrayList<StockReportMB> detailList) {
		this.detailList = detailList;
	}

	public int getEworkingdays() {
		return Eworkingdays;
	}

	public void setEworkingdays(int eworkingdays) {
		Eworkingdays = eworkingdays;
	}

	public String getEadvanceamount() {
		return Eadvanceamount;
	}

	public void setEadvanceamount(String eadvanceamount) {
		Eadvanceamount = eadvanceamount;
	}

	public String getEtotalsalary() {
		return Etotalsalary;
	}

	public void setEtotalsalary(String etotalsalary) {
		Etotalsalary = etotalsalary;
	}

	public String getEname() {
		return Ename;
	}

	public void setEname(String ename) {
		Ename = ename;
	}

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getEbasicsalary() {
		return Ebasicsalary;
	}

	public void setEbasicsalary(String ebasicsalary) {
		Ebasicsalary = ebasicsalary;
	}

	public String getEdesignation() {
		return Edesignation;
	}

	public void setEdesignation(String edesignation) {
		Edesignation = edesignation;
	}

	public String getEmonth() {
		return Emonth;
	}

	public void setEmonth(String emonth) {
		Emonth = emonth;
	}

	public String getEcommission() {
		return Ecommission;
	}

	public void setEcommission(String ecommission) {
		Ecommission = ecommission;
	}

	public String getEovertime() {
		return Eovertime;
	}

	public void setEovertime(String eovertime) {
		Eovertime = eovertime;
	}

	public List<EmployeePayroll> getEmployeeSingleDetail() {
		return EmployeeSingleDetail;
	}

	public void setEmployeeSingleDetail(
			List<EmployeePayroll> employeeSingleDetail) {
		EmployeeSingleDetail = employeeSingleDetail;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public ArrayList<StockReport> getAllemployeeList() {
		return allemployeeList;
	}

	public void setAllemployeeList(ArrayList<StockReport> allemployeeList) {
		this.allemployeeList = allemployeeList;
	}

	public Date getFromDate() {
		return FromDate;
	}

	public void setFromDate(Date fromDate) {
		FromDate = fromDate;
	}

	public Date getToDate() {
		return ToDate;
	}

	public void setToDate(Date toDate) {
		ToDate = toDate;
	}

	public ArrayList<StockReport> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<StockReport> employeeList) {
		this.employeeList = employeeList;
	}

	public StockDamageMB getStockDamageMB() {
		return stockDamageMB;
	}

	public void setStockDamageMB(StockDamageMB stockDamageMB) {
		this.stockDamageMB = stockDamageMB;
	}

	public boolean isStockInFlag() {
		return StockInFlag;
	}

	public void setStockInFlag(boolean stockInFlag) {
		StockInFlag = stockInFlag;
	}

	public boolean isEmployeeFlag() {
		return EmployeeFlag;
	}

	public void setEmployeeFlag(boolean employeeFlag) {
		EmployeeFlag = employeeFlag;
	}

	public boolean isStockdamageFlag() {
		return StockdamageFlag;
	}

	public void setStockdamageFlag(boolean stockdamageFlag) {
		StockdamageFlag = stockdamageFlag;
	}

	public ArrayList<StockReport> getAllstockList() {
		return allstockList;
	}

	public void setAllstockList(ArrayList<StockReport> allstockList) {
		this.allstockList = allstockList;
	}

	public boolean isAllFlag() {
		return AllFlag;
	}

	public void setAllFlag(boolean allFlag) {
		AllFlag = allFlag;
	}

	public ArrayList<StockReport> getStockReportlist() {
		return stockReportlist;
	}

	public void setStockReportlist(ArrayList<StockReport> stockReportlist) {
		this.stockReportlist = stockReportlist;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public void stockreportPage() {
		logger.info("[stockreportPage()]----------------- Inside stockreportPage() method()----------------------- ");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("StockReport", options,
				null);
		stockFlagReset();
		Type = null;

	}

	public void employeereportPage() {
		logger.info("[employeereportPage()]----------------- Inside employeereportPage() method()----------------------- ");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("EmployeeReport",
				options, null);
		Type = null;
		setFromDate(null);
		setToDate(null);
		stockFlagReset();
		detailEflag = false;
	}

	public void stockFlagReset() {
		logger.info("[stockFlagReset()]----------------- Inside stockFlagReset() method()----------------------- ");
		AllFlag = false;
		setValidate(null);
		EmployeeFlag = false;
		singleTypeFlag = false;
		mulipleTypeFlag = false;
		
	}

	public boolean detailEflag = false;

	public boolean isDetailEflag() {
		return detailEflag;
	}

	public void setDetailEflag(boolean detailEflag) {
		this.detailEflag = detailEflag;
	}

	public void detailViewEmployee(final SelectEvent event) {
		logger.info("[detailViewEmployee()]----------------- Inside detailViewEmployee() method()----------------------- ");
		String batch1 = ((StockReport) event.getObject()).getPayrollNumber();
		detailList.clear();
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll view = new EmployeePayroll();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			view.setPayrollno(batch1);
			setEmployeeSingleDetail(controller.viewPayrollz(view));
			setEadvanceamount(EmployeeSingleDetail.get(0).getAdvanceamount());
			setEbasicsalary(EmployeeSingleDetail.get(0).getBasicsalary());
			setEdesignation(EmployeeSingleDetail.get(0).getDesignation());
			setEmonth(EmployeeSingleDetail.get(0).getMonth());
			setEname(EmployeeSingleDetail.get(0).getName());
			setEid(EmployeeSingleDetail.get(0).getEmployeeid());
			setEpayrolldate(EmployeeSingleDetail.get(0).getTodayDate());
			setEtotalsalary(EmployeeSingleDetail.get(0).getTotalsalary());
			setEworkingdays(EmployeeSingleDetail.get(0).getWorkingdays());
			setEcommission(EmployeeSingleDetail.get(0).getCommission());
			setEovertime(EmployeeSingleDetail.get(0).getOvertime());
			detailEflag = true;
			RequestContext.getCurrentInstance().execute("PF('EmployeeDetail').show();");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

	}

	public String stockreport() {
		logger.info("[stockreport()]----------------- Inside stockreport() method()----------------------- ");
		allstockList.clear();
		AllFlag = false;

		try {
			setValidate(null);

			if (Type.equalsIgnoreCase("")) {
				throw new Exception("Please Select the Product Name");
			}

			else if (Type.equalsIgnoreCase("All")) {

				AllFlag = true;
				stockReportlist = StockReportJDBC.stocksearch(Type);
				for (int i = 0; i < stockReportlist.size(); i++) {
					StockReport allStock = new StockReport();
					allStock.setProductName(stockReportlist.get(i)
							.getProductName());
					allStock.setAvlQuantity(stockReportlist.get(i)
							.getAvlQuantity());
					allStock.setDamagedQuantity(stockReportlist.get(i)
							.getDamagedQuantity());
					allStock.setStockInQuantity(stockReportlist.get(i)
							.getStockInQuantity());
					allStock.setStockOutQuantity(stockReportlist.get(i)
							.getStockOutQuantity());
					allStock.setUnitprice(stockReportlist.get(i).getUnitprice());
					allstockList.add(allStock);
				}
			}

			else {
				AllFlag = true;
				stockReportlist = StockReportJDBC.stocksearch(Type);
				for (int i = 0; i < stockReportlist.size(); i++) {

					StockReport allStock = new StockReport();
					allStock.setProductName(stockReportlist.get(i)
							.getProductName());
					allStock.setAvlQuantity(stockReportlist.get(i)
							.getAvlQuantity());
					allStock.setDamagedQuantity(stockReportlist.get(i)
							.getDamagedQuantity());
					allStock.setStockInQuantity(stockReportlist.get(i)
							.getStockInQuantity());
					allStock.setStockOutQuantity(stockReportlist.get(i)
							.getStockOutQuantity());
					allStock.setUnitprice(stockReportlist.get(i).getUnitprice());
					allstockList.add(allStock);

				}
			}

			
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.debug("--------------" + validate);
			logger.error("Inside Exception", e);
		}

		return "";

	}

	public String employeereport() {
		logger.info("[employeereport()]----------------- Inside employeereport() method()----------------------- ");
		allemployeeList.clear();
		detailEflag = false;
		EmployeeFlag = false;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			if (FromDate == null) {
				fieldName = CommonValidate.findComponentInRoot("fdate")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Choose  From Date"));

			} else if (ToDate == null) {

				fieldName = CommonValidate.findComponentInRoot("tdate")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Choose To Date"));
			} else if (Type.equalsIgnoreCase("")) {

				fieldName = CommonValidate.findComponentInRoot("vt")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose Type"));

			}
			if (Type.equalsIgnoreCase("All")) {
				employeeList = StockReportJDBC.employeesearch(Type, FromDate,
						ToDate);
				for (int i = 0; i < employeeList.size(); i++) {

					StockReport allEmployee = new StockReport();
					allEmployee.setEmployeeName(employeeList.get(i)
							.getEmployeeName());
					allEmployee.setPayrollDate(employeeList.get(i)
							.getPayrollDate());
					allEmployee.setBasicSalary(employeeList.get(i)
							.getBasicSalary());
					allEmployee.setTotalSalary(employeeList.get(i)
							.getTotalSalary());
					allEmployee.setPayrollNumber(employeeList.get(i)
							.getPayrollNumber());
					allEmployee.setMonth(employeeList.get(i).getMonth());
					allEmployee.setYear(employeeList.get(i).getYear());
					allemployeeList.add(allEmployee);

				}
				EmployeeFlag = true;
				setValidate(null);
			}
			if (Type.equalsIgnoreCase("single")) {
				if (EmployeeName.equalsIgnoreCase("")) {
					fieldName = CommonValidate.findComponentInRoot("v1")
							.getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage(
							"Please Enter the Customer Name"));

				} else if (!EmployeeName.equalsIgnoreCase("")) {
					employeeList = StockReportJDBC.employeesearch(EmployeeName,
							FromDate, ToDate);
					for (int i = 0; i < employeeList.size(); i++) {

						StockReport allEmployee = new StockReport();
						allEmployee.setEmployeeName(employeeList.get(i)
								.getEmployeeName());
						allEmployee.setPayrollDate(employeeList.get(i)
								.getPayrollDate());
						allEmployee.setBasicSalary(employeeList.get(i)
								.getBasicSalary());
						allEmployee.setTotalSalary(employeeList.get(i)
								.getTotalSalary());
						allEmployee.setPayrollNumber(employeeList.get(i)
								.getPayrollNumber());
						allEmployee.setMonth(employeeList.get(i).getMonth());
						allEmployee.setYear(employeeList.get(i).getYear());
						allemployeeList.add(allEmployee);

					}
					EmployeeFlag = true;
					setValidate(null);
				}
			}

		} catch (Exception e) {
			setValidate(e.getMessage());
		}
		return "";
	}

	public boolean singleTypeFlag;
	public boolean mulipleTypeFlag;
	public boolean setNorecoordFlag;

	public boolean isSingleTypeFlag() {
		return singleTypeFlag;
	}

	public void setSingleTypeFlag(boolean singleTypeFlag) {
		this.singleTypeFlag = singleTypeFlag;
	}

	public boolean isMulipleTypeFlag() {
		return mulipleTypeFlag;
	}

	public void setMulipleTypeFlag(boolean mulipleTypeFlag) {
		this.mulipleTypeFlag = mulipleTypeFlag;
	}

	public boolean isSetNorecoordFlag() {
		return setNorecoordFlag;
	}

	public void setSetNorecoordFlag(boolean setNorecoordFlag) {
		this.setNorecoordFlag = setNorecoordFlag;
	}

	public void valueChange(ValueChangeEvent vc) {
		logger.info("[valueChange()]----------------- Inside valueChange() method()----------------------- ");
		EmployeeName = null;
		String vtype = vc.getNewValue().toString();
		if (vtype.equalsIgnoreCase("single")) {
			logger.debug("inside if");
			setSingleTypeFlag(true);
			setMulipleTypeFlag(false);
			setSetNorecoordFlag(false);
		} else if (vtype.equals("All")) {
			logger.debug("inside else if");
			setSingleTypeFlag(false);
			setMulipleTypeFlag(true);
			setSetNorecoordFlag(false);
		}
	}
}
