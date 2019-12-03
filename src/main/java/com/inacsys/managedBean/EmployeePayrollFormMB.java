package com.inacsys.managedBean;

import java.awt.Event;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.exolab.castor.types.DateTime;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.EmployeeDetail;
import com.inacsys.domain.EmployeePayroll;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.Designation;
import com.inacsys.shared.Employee;
import com.inacsys.shared.Month;
import com.inacsys.shared.Year;
import com.inacsys.util.CurrencyConverter;

@ManagedBean(name = "employeePayrollFormMB")
public class EmployeePayrollFormMB {
	final Logger logger = LoggerFactory.getLogger(EmployeePayrollFormMB.class);
	public String name;
	public String employeeid;
	public String basicsalary;
	public String designation;
	public String month;
	public String year;
	public List<Month> monthinfo = null;
	public List<Year> yearinfo = null;
	// public List<String> list;
	public ArrayList<String> list1 = null;
	public ArrayList<String> list2 = null;
	public List<String> designate = null;
	public int workingdays;
	public String advanceamount = "";
	public String totalsalary;
	public Date payrolldate;
	public List<Employee> list3 = null;
	public String validate;
	public String payrollno;
	public int empId;
	public String employeeid1;
	public String designation1;
	public String todayDate;
	public String date;
	public List<Employee> empinfo;
	public List<Employee> nameinfo;
	public String flag = "none";
	public List<Employee> list = new ArrayList<Employee>();
	public String regid;
	public String commission = null;
	public String overtime = null;
	public String currency;
	public String baseCurrency;
	public String currencyAmount;

	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public String getOvertime() {
		return overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getRegid() {
		return regid;
	}

	public void setRegid(String regid) {
		this.regid = regid;
	}

	public List<Employee> getList() {
		return list;
	}

	public void setList(List<Employee> list) {
		this.list = list;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<Employee> getNameinfo() {
		return nameinfo;
	}

	public void setNameinfo(List<Employee> nameinfo) {
		this.nameinfo = nameinfo;
	}

	public List<Employee> getEmpinfo() {
		return empinfo;
	}

	public void setEmpinfo(List<Employee> empinfo) {
		this.empinfo = empinfo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	public String getDesignation1() {
		return designation1;
	}

	public void setDesignation1(String designation1) {
		this.designation1 = designation1;
	}

	public String getEmployeeid1() {
		return employeeid1;
	}

	public void setEmployeeid1(String employeeid1) {
		this.employeeid1 = employeeid1;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getPayrollno() {
		return payrollno;
	}

	public void setPayrollno(String payrollno) {
		this.payrollno = payrollno;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public List<Employee> getList3() {
		return list3;
	}

	public void setList3(List<Employee> list3) {
		this.list3 = list3;
	}

	public int getWorkingdays() {
		return workingdays;
	}

	public void setWorkingdays(int workingdays) {
		this.workingdays = workingdays;
	}

	public String getAdvanceamount() {
		return advanceamount;
	}

	public void setAdvanceamount(String advanceamount) {
		this.advanceamount = advanceamount;
	}

	public String getTotalsalary() {
		return totalsalary;
	}

	public void setTotalsalary(String totalsalary) {
		this.totalsalary = totalsalary;
	}

	public Date getPayrolldate() {
		return payrolldate;
	}

	public void setPayrolldate(Date payrolldate) {
		this.payrolldate = payrolldate;
	}

	public ArrayList<String> getList2() {
		return list2;
	}

	public void setList2(ArrayList<String> list2) {
		this.list2 = list2;
	}

	public List<String> getDesignate() {
		return designate;
	}

	public void setDesignate(List<String> designate) {
		this.designate = designate;
	}

	public ArrayList<String> getList1() {
		return list1;
	}

	public void setList1(ArrayList<String> list1) {
		this.list1 = list1;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getBasicsalary() {
		return basicsalary;
	}

	public List<Month> getMonthinfo() {
		return monthinfo;
	}

	public void setMonthinfo(List<Month> monthinfo) {
		this.monthinfo = monthinfo;
	}

	public List<Year> getYearinfo() {
		return yearinfo;
	}

	public void setYearinfo(List<Year> yearinfo) {
		this.yearinfo = yearinfo;
	}

	public void setBasicsalary(String basicsalary) {
		this.basicsalary = basicsalary;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String onload() {
		logger.info("[onload()] --------------- Inside onload() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setMonthinfo(controller.monthInfo(monthinfo));
			setYearinfo(controller.yearInfo(yearinfo));
			setEmpinfo(controller.empInfo(empinfo));
			setNameinfo(controller.nameInfo(nameinfo));
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	/****/
	public String redirect() {
		logger.info("[redirect()] --------------- Inside redirect() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			setValidate("");
			setEmployeeid("");
			setMonth("");
			setYear("");
			setDesignation("");
			setDesignation1("");
			setName("");
			setFlag("none");
			list.clear();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setDesignate(controller.designationInfo(designate));
			onload();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";
	}

	@Autowired
	public String entry() throws ParseException {
		logger.info("[entry()] --------------- Inside entry() method() ------------------------");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfv = new SimpleDateFormat("MM");
		todayDate = df.format(Calendar.getInstance().getTime());
		String todayDatevalidation = dfv.format(Calendar.getInstance()
				.getTime());
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll save = new EmployeePayroll();
		int curntyear = Calendar.getInstance().get(Calendar.YEAR);
		int curntmonth = Calendar.getInstance().get(Calendar.MONTH);
		int monthT = curntmonth;
		String months = month;
		try {
			String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
			flag = "none";
			try {
				validate = null;
				setValidate("");
				if (month.equals("")) {
					throw new Exception("Please Choose the Month");
				}

				if (year.equals("")) {
					throw new Exception("Please Choose the Year");
				} else if (curntyear < Integer.parseInt(year)) {
					throw new Exception("Please Choose Valid Year");

				} else if (curntyear == Integer.parseInt(year)) {
					Date dateT = new SimpleDateFormat("MMM", Locale.ENGLISH)
							.parse(months);
					Calendar cal = Calendar.getInstance();
					cal.setTime(dateT);
					monthT = cal.get(Calendar.MONTH);
					if (curntmonth < monthT) {
						throw new Exception("Please Choose Valid Month");
					}
				}
			} catch (Exception e) {
				setValidate(e.getMessage());
				return "";
			}
			validate = "";
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			save.setTodayDate(todayDate);
			Date date = df.parse(todayDate);
			save.setEmployeeid(employeeid);
			save.setMonth(month);
			save.setYear(year);
			save.setName(name);
			list3 = controller.payroll(list3, save);
			if (list3.size() > 0) {
				validate = "";
				setEmployeeid(list3.get(0).getEmployeeId());
				setCurrency(list3.get(0).getCurrency());
				setCurrencyAmount(list3.get(0).getCurrency_amount());
				setBaseCurrency(baseCurrency);
				setBasicsalary(list3.get(0).getBasicSalary());
				setDesignation1(list3.get(0).getDesignation().getType());
				setName(list3.get(0).getEmployeeName());
				setCommission(save.getCommission());
				setOvertime("");
				setAdvanceamount("");
				setWorkingdays(0);
			}
			validate = null;
			employeepayrollconfirm();
			return "";
		} catch (DemoException ie) {
			logger.debug("inside mb exception");
			setValidate(ie.getMessage());
			logger.debug("inside mb exception====" + validate);
			return "";
		} finally {
		}

	}

	/****/
	public String entry1() throws ParseException {
		logger.info("[entry1()] --------------- Inside entry1() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll save = new EmployeePayroll();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			todayDate = df.format(Calendar.getInstance().getTime());
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			save.setTodayDate(todayDate);
			Date date = df.parse(todayDate);
			save.setRegid(regid);
			save.setName(name);
			save.setMonth(month);
			save.setYear(year);
			list3 = controller.payrollemp(list3, save);
			if (list3.size() > 0) {
				validate = "";
				setEmployeeid(list3.get(0).getEmployeeId());
				setBasicsalary(list3.get(0).getBasicSalary());
				setDesignation1(list3.get(0).getDesignation().getType());
				setName(list3.get(0).getEmployeeName());
				setCommission(save.getCommission());
			}
			logger.debug("id-------->>>" + list3.get(0).getEmployeeId());
		} catch (DemoException ie) {
			setValidate(ie.getMessage());
			return "";
		} finally {

		}
		return "generation";
	}

	/****/
	public String submit() {
		logger.info("[submit()] --------------- Inside submit() method() ------------------------");
		EmployeePayroll save = new EmployeePayroll();
		BigDecimal currAmount=BigDecimal.valueOf(0);
		try {
			setValidate("");setCurrencyAmount("0");
			if(currency.equalsIgnoreCase("")){
				throw new Exception("Please select the Currency");
			}
			if (getWorkingdays() > 31) {
				throw new Exception(
						"Working days should not be more than 31 days");
			} else if (workingdays == 0) {
				throw new Exception("Enter the Working Days");
			} else if (advanceamount.equalsIgnoreCase("")) {
				throw new Exception("Enter the Loan Advance");
			}

			else if (overtime.equalsIgnoreCase("")) {
				throw new Exception("Enter the Over Time Amount");
			} else if (!advanceamount.matches("^\\d+(\\.\\d+)*$")) {
				throw new Exception("Amount Should be Numeric");
			} else if (!overtime.matches("^\\d+(\\.\\d+)*$")) {
				throw new Exception("Amount Should be Numeric");
			}
			if (basicsalary == null || basicsalary == "") {
				basicsalary = "0";
			}
			logger.debug("[submit()] --------------- basic salary ------------------------>"+basicsalary);
			BigDecimal deci = BigDecimal.valueOf(0);
			deci = (new BigDecimal(basicsalary).add(new BigDecimal(commission)
					.add(new BigDecimal(overtime))).subtract(new BigDecimal(
					advanceamount)));
			if(currency.equalsIgnoreCase(baseCurrency)){
				currAmount=deci;
			}else{
				currAmount=CurrencyConverter.findExchangeRateAndConvert(currency,baseCurrency,String.valueOf(deci));
			}
			setTotalsalary(String.valueOf(deci));
			setCurrencyAmount(String.valueOf(currAmount));
		} catch (Exception e) {
			setValidate(e.getMessage());
			return "failure";
		} finally {

		}
		return "aPayrollGenerationConfirmationForm";
	}

	public String saveConfirm() {
		logger.info("[saveconform()] --------------- Inside saveconform() method() ------------------------");
		DemoController controller = null;
		ApplicationContext ctx = null;
		EmployeePayroll save = new EmployeePayroll();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			save.setBasicsalary(basicsalary);
			save.setDesignation(designation);
			save.setEmployeeid(employeeid);
			save.setName(name);
			save.setMonth(month);
			save.setYear(year);
			save.setEmpId(empId);
			save.setWorkingdays(workingdays);
			save.setCommission(commission);
			save.setOvertime(overtime);
			save.setAdvanceamount(advanceamount);
			save.setTotalsalary(totalsalary);
			save.setCurrencyAmount(currencyAmount);
			save.setCurrency(currency);
			save.setTodayDate(todayDate);
			save.setPayrollno(payrollno);
			controller.confirm(save);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			workingdays = 0;
			advanceamount = "";
			totalsalary = "";
			overtime = null;
			commission = null;
			payrolldate = null;
			employeeid = null;
			name = null;
		}
		return "aPayrollGenerationConfirmation";
	}

	/** clear variable **/
	public String clearsession() {
		logger.info("[clearsession()] --------------- Inside clearsession() method() ------------------------");
		setValidate(null);
		setEmployeeid(null);
		setMonth(null);
		setYear(null);
		setDesignation(null);
		setDesignation1(null);
		setName(null);
		flag = "none";
		return "payrollgenerate";
	}

	public String viewdetails() {
		logger.info("[viewdetails()] --------------- Inside viewdetails() method() ------------------------");
		DemoController controller = null;
		ApplicationContext ctx = null;
		EmployeeDetail employee = new EmployeeDetail();
		try {
			validate = null;
			setValidate("");
			if (month.equals("")) {
				throw new Exception("Please Choose the Month");
			} else if (year.equals("")) {
				throw new Exception("Please Choose the Year");
			} else if (name.equals("")) {
				throw new Exception("Please Choose the Employee Name");
			}
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setList(employee.getList());
			employee.setName(name);
			controller.employeeDetails(employee);
			if (employee.getList().size() > 0) {
				flag = "1";
				validate = null;
				setList(employee.getList());
				logger.debug("data list---->>>" + list);
			}
		} catch (Exception e) {

			setValidate(e.getMessage());
		} finally {

		}
		return "";
	}

	// prema begin 02/05/2016 dialog box creation for payroll form

	public void employeepayroll() {
		logger.info("[employeepayroll()] --------------- Inside employeepayroll() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("aPayrollForm", options,
				null);
		redirect();
	}


	@ManagedProperty(value = "#{employeeDetailsViewFormMB}")
	EmployeeDetailsViewFormMB employeeDetailsViewFormMB;

	@ManagedProperty(value = "#{employeePayrollViewFormMB}")
	EmployeePayrollViewFormMB employeePayrollViewFormMB;

	public EmployeePayrollViewFormMB getEmployeePayrollViewFormMB() {
		return employeePayrollViewFormMB;
	}

	public void setEmployeePayrollViewFormMB(
			EmployeePayrollViewFormMB employeePayrollViewFormMB) {
		this.employeePayrollViewFormMB = employeePayrollViewFormMB;
	}

	public EmployeeDetailsViewFormMB getEmployeeDetailsViewFormMB() {
		return employeeDetailsViewFormMB;
	}

	public void setEmployeeDetailsViewFormMB(
			EmployeeDetailsViewFormMB employeeDetailsViewFormMB) {
		this.employeeDetailsViewFormMB = employeeDetailsViewFormMB;
	}

	public void employeepayrollconfirm() {

		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);

		RequestContext.getCurrentInstance().openDialog(
				"aPayrollGenerationForm", options, null);

	}
	public void employeePayrollRe() {
		employeeid = "ApprovalData";
		designation = "ApprovalData";
		employeePayrollViewFormMB.search3(employeeid, designation);
	}
	public void employeePayrolldetail(final SelectEvent event) {
		logger.info("[employeePayrolldetail()] --------------- Inside employeePayrolldetail() method() ------------------------");
		employeeid = ((Employee) event.getObject()).getEmployeeId();
		designation = ((Employee) event.getObject()).getDesignation().getType();
		employeePayrollViewFormMB.search3(employeeid, designation);
	}

	public void tabchange() {
		logger.info("[tabchange()] --------------- Inside tabchange() method() ------------------------");
		try {
			setValidate("");
			setMonth("");
			setYear("");
			onload();
			employeePayrollViewFormMB.returnBack();
			employeePayrollViewFormMB.setValidate("");
		} catch (Exception e) {
		}

	}
	
	public void employeepayrollclose() {
		logger.info("[employeepayrollclose()] --------------- Inside employeepayrollclose() method() ------------------------");
		RequestContext.getCurrentInstance().closeDialog("aPayrollForm");
		month = null;
		year = null;
		validate = null;
		employeeDetailsViewFormMB.view();
	}
}
