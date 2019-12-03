package com.inacsys.managedBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.EmployeePayroll;
import com.inacsys.shared.Designation;
import com.inacsys.shared.Employee;
import com.inacsys.shared.Month;
import com.inacsys.shared.Year;

@ManagedBean(name = "employeePayrollViewFormMB")
public class EmployeePayrollViewFormMB {
	private static Logger logger = Logger
			.getLogger(EmployeePayrollViewFormMB.class);
	public String name;
	public String employeeid;
	public String basicsalary;
	public String designation;
	public String month;
	public String months;
	public String year;
	public List<Month> monthinfo = null;
	public List<Year> yearinfo = null;
	public List<String> list;
	public ArrayList<String> list1 = null;
	public ArrayList<String> list2 = null;
	public List<String> designate = null;
	public int workingdays;
	public String advanceamount;
	public String totalsalary;
	public Date payrolldate;
	public List<Employee> list3 = null;
	public String validate;
	public String payrollno;
	public String flag = "none";
	public String flag2 = "none";
	public String flag3 = "none";
	public List<Month> list4 = null;
	public String flag1 = "none";
	public List<EmployeePayroll> vallist = new ArrayList<EmployeePayroll>();
	public List<EmployeePayroll> value = new ArrayList<EmployeePayroll>();
	public String status;
	public List<EmployeePayroll> value1 = new ArrayList<EmployeePayroll>();
	public String designation1;
	public String employeeid1;
	public String todayDate;
	public String month1;
	public String commission;
	public String overtime;
	public String userType;
	public String baseCurrency;
	public String currency;
	public String currencyAmount;
	public String approveButtonFlag="none";
	public String approveButtonFlag1="none";
	
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getApproveButtonFlag1() {
		return approveButtonFlag1;
	}

	public void setApproveButtonFlag1(String approveButtonFlag1) {
		this.approveButtonFlag1 = approveButtonFlag1;
	}

	public String getApproveButtonFlag() {
		return approveButtonFlag;
	}

	public void setApproveButtonFlag(String approveButtonFlag) {
		this.approveButtonFlag = approveButtonFlag;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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

	public String getMonth1() {
		return month1;
	}

	public void setMonth1(String month1) {
		this.month1 = month1;
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

	public List<EmployeePayroll> getValue1() {
		return value1;
	}

	public void setValue1(List<EmployeePayroll> value1) {
		this.value1 = value1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<EmployeePayroll> getVallist() {
		return vallist;
	}

	public void setVallist(List<EmployeePayroll> vallist) {
		this.vallist = vallist;
	}

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	public List<EmployeePayroll> getValue() {
		return value;
	}

	public void setValue(List<EmployeePayroll> value) {
		this.value = value;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getFlag3() {
		return flag3;
	}

	public void setFlag3(String flag3) {
		this.flag3 = flag3;
	}

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public List<Month> getList4() {
		return list4;
	}

	public void setList4(List<Month> list4) {
		this.list4 = list4;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public void setBasicsalary(String basicsalary) {
		this.basicsalary = basicsalary;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public ArrayList<String> getList1() {
		return list1;
	}

	public void setList1(ArrayList<String> list1) {
		this.list1 = list1;
	}

	public ArrayList<String> getList2() {
		return list2;
	}

	public void setList2(ArrayList<String> list2) {
		this.list2 = list2;
	}

	public int getWorkingdays() {
		return workingdays;
	}

	public List<String> getDesignate() {
		return designate;
	}

	public void setDesignate(List<String> designate) {
		this.designate = designate;
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

	public List<Employee> getList3() {
		return list3;
	}

	public void setList3(List<Employee> list3) {
		this.list3 = list3;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getPayrollno() {
		return payrollno;
	}

	public void setPayrollno(String payrollno) {
		this.payrollno = payrollno;
	}

	/****/
	public String redirect() {
		logger.info("[redirect()] --------------- Inside redirect() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			flag1 = "none";
			flag = "none";
			flag2 = "none";
			flag3 = "none";
			setValidate("");
			setMonths(null);
			setMonth(null);
			setMonth1(null);
			setEmployeeid(null);
			setEmployeeid1(null);
			setDesignation(null);
			setDesignation1(null);
			setName(null);
			setYear(null);
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setDesignate(controller.designationInfo(designate));
			Collections.sort(designate, String.CASE_INSENSITIVE_ORDER);
			setMonthinfo(controller.monthInfo(monthinfo));
			setYearinfo(controller.yearInfo(yearinfo));
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

		return "";
	}

	public void change(ValueChangeEvent changeEvent) {
		logger.info("[change()] --------------- Inside change() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll change = new EmployeePayroll();
		try {
			setDesignation("" + changeEvent.getNewValue());
			String s = changeEvent.getNewValue().toString();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			change.setEmployeeid(employeeid);
			change.setDesignation(s);
			change.setList(list);
			list1 = controller.changeEvent2(s, list1);
			Collections.sort(list1, String.CASE_INSENSITIVE_ORDER);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
	}

	public void change1(ValueChangeEvent changeEvent) {
		logger.info("[change1()] --------------- Inside change1 method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll change = new EmployeePayroll();
		try {
			setDesignation("" + changeEvent.getNewValue());
			String s = changeEvent.getNewValue().toString();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			change.setName(name);
			change.setDesignation(s);
			change.setList(list);
			list2 = controller.changeEvent3(s, list2);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
	}

	public String payrollform() {
		logger.info("[payrollform()] --------------- Inside payrollform() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll pay = new EmployeePayroll();
		try {
			flag = "1";
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setList3(list3);
			pay.setPayrollno(payrollno);
			pay.setEmployeeid(employeeid);
			controller.payroll1(pay);
			if (pay.getList3().size() > 0) {
				setList3(pay.getList3());
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public String payrollform1() {
		logger.info("[payrollform1()] --------------- Inside payrollform1() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll pay = new EmployeePayroll();
		try {
			logger.debug("inside try in mb------>>>>");
			flag = "1";
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setList3(list3);
			pay.setPayrollno(payrollno);
			logger.debug("payroll number---------->>>" + payrollno);
			pay.setName(name);
			logger.debug("employee name----->" + name);
			controller.payroll(pay);
			if (pay.getList3().size() > 0) {
				setList3(pay.getList3());
				logger.debug(pay.getList3().get(0).getPayroll()
						.getPayrollNumber());
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public List<EmployeePayroll> datalist = new ArrayList<EmployeePayroll>();
	public List<EmployeePayroll> data = new ArrayList<EmployeePayroll>();

	public List<EmployeePayroll> getDatalist() {
		return datalist;
	}

	public void setDatalist(List<EmployeePayroll> datalist) {
		this.datalist = datalist;
	}

	public List<EmployeePayroll> getData() {
		return data;
	}

	public void setData(List<EmployeePayroll> data) {
		this.data = data;
	}

	/** search by month & year **/
	public String search1() {
		logger.info("[search1()] --------------- Inside search1() method() ------------------------");
		logger.debug("===search1=====");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll pay = new EmployeePayroll();
		try {
			setValidate("");
			if (month.equals("")) {
				throw new Exception("Please Choose the Month");
			} else if (year.equals("")) {
				throw new Exception("Please Choose the Year");
			}
			vallist.clear();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			pay.setMonths(month);
			logger.debug("month : " + getMonths());
			logger.debug("year : " + getYear());
			pay.setYear(year);
			pay.setAdvanceamount(advanceamount);
			pay.setCommission(commission);
			pay.setOvertime(overtime);
			pay.setBasicsalary(basicsalary);
			pay.setDesignation(designation);
			pay.setEmployeeid(employeeid);
			pay.setName(name);
			pay.setTodayDate(todayDate);
			pay.setPayrollno(payrollno);
			pay.setTotalsalary(totalsalary);
			pay.setWorkingdays(workingdays);
			controller.search1(pay);
			logger.debug("------------- check");
			if (pay.vallist.size() > 0) {
				flag = "1";
				flag1 = "none";
				flag2 = "none";
				flag3 = "none";
				setValue(pay.getVallist());
				logger.debug("vallist-------0---->>>" + value.size());
			} else {
				logger.debug("inside else");
				flag3 = "1";
				flag = "none";
				flag2 = "none";
				flag1 = "none";
			}
		} catch (Exception e) {
			flag2 = "none";
			flag = "none";
			flag1 = "none";
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
			return "";
		} finally {
		}
		return "";

	}

	/** search by month & designation & emp id **/
	public String search2() {
		logger.info("[search2()] --------------- Inside search2() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll pay = new EmployeePayroll();
		try {
			try {
				validate = null;
				setValidate("");
				if (designation1.equals("")) {
					throw new Exception("Please Choose the Designation");
				} else if (employeeid1.equals("")) {
					throw new Exception("Please Choose the Employee ID");
				} else if (month1.equals("")) {
					throw new Exception("Please Choose the Month");
				}
			} catch (Exception e) {
				flag2 = "none";
				flag = "none";
				flag1 = "none";
				setValidate(e.getMessage());
				return "";
			}
			vallist.clear();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			pay.setMonth1(month1);
			logger.debug("desn : " + getDesignation());
			logger.debug("id : " + getEmployeeid());
			logger.debug("month : " + getMonth());
			pay.setYear(year);
			pay.setAdvanceamount(advanceamount);
			pay.setBasicsalary(basicsalary);
			pay.setDesignation1(designation1);
			pay.setEmployeeid1(employeeid1);
			pay.setCommission(commission);
			pay.setOvertime(overtime);
			pay.setName(name);
			pay.setTodayDate(todayDate);
			pay.setPayrollno(payrollno);
			pay.setTotalsalary(totalsalary);
			pay.setWorkingdays(workingdays);
			controller.search2(pay);
			if (pay.vallist.size() > 0) {
				flag2 = "1";
				flag = "none";
				flag3 = "none";
				flag1 = "none";
				setValue(pay.getVallist());
				logger.debug("vallist----------->>>" + value.size());
			} else {
				flag3 = "1";
				flag2 = "none";
				flag = "none";
				flag1 = "none";
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
		}
		return "";

	}

	/** for no records **/
	public String cancel() {
		logger.info("[cancel()] --------------- Inside cancel() method() ------------------------");
		flag = "none";
		flag1 = "none";
		flag2 = "none";
		flag3 = "none";
		setMonths(null);
		setYear(null);
		setDesignation(null);
		setDesignation1(null);
		setMonth1(null);
		setEmployeeid(null);
		setEmployeeid1(null);
		setMonth(null);
		return "nodata";

	}
public List<EmployeePayroll> viewlist=null;


	public List<EmployeePayroll> getViewlist() {
	return viewlist;
}

public void setViewlist(List<EmployeePayroll> viewlist) {
	this.viewlist = viewlist;
}

	/** payroll view **/
	public String payrollViewz() {
		logger.info("[payrollviewz()] --------------- Inside payrollviewz() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll view = new EmployeePayroll();
		try {
			viewlist=new ArrayList<EmployeePayroll>();
			String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			view.setPayrollno(payrollno);
			view.setId(Integer.parseInt(employeeid1));
			System.out.println("payroll number---------->>>" + payrollno);
			setViewlist(controller.viewPayrollz(view));
			setAdvanceamount(viewlist.get(0).getAdvanceamount());
			setBasicsalary(viewlist.get(0).getBasicsalary());
			setDesignation(viewlist.get(0).getDesignation());
			setEmployeeid(viewlist.get(0).getEmployeeid());
			setMonth(viewlist.get(0).getMonth());
			setName(viewlist.get(0).getName());
			System.out.println("name--------->>>>" + viewlist.get(0).getName());
			setTodayDate(viewlist.get(0).getTodayDate());
			setPayrollno(viewlist.get(0).getPayrollno());
			setTotalsalary(viewlist.get(0).getTotalsalary());
			setWorkingdays(viewlist.get(0).getWorkingdays());
			System.out.println("working days-----"+viewlist.get(0).getWorkingdays());
			setCommission(viewlist.get(0).getCommission());
			setOvertime(viewlist.get(0).getOvertime());
			setCurrency(viewlist.get(0).getCurrency());
			setCurrencyAmount(viewlist.get(0).getCurrencyAmount());
			setBaseCurrency(baseCurrency);
			setYear(viewlist.get(0).getYear());
			System.out.println("year-----"+viewlist.get(0).getYear());
			employeepayrollview();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public String payrollEdit() {
		logger.info("[payrolledit()] --------------- Inside payrolledit() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll view = new EmployeePayroll();
		setValidate("");
		try {
			String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
			logger.debug("inside try in mb------>>>>");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			view.setPayrollno(payrollno);
			logger.debug("payroll number---------->>>" + payrollno);
			view.setStatus("register");
			setDatalist(controller.editPayroll(view));
			setAdvanceamount(datalist.get(0).getAdvanceamount());
			setBasicsalary(datalist.get(0).getBasicsalary());
			setDesignation(datalist.get(0).getDesignation());
			setEmployeeid(datalist.get(0).getEmployeeid());
			setMonth(datalist.get(0).getMonth());
			setName(datalist.get(0).getName());
			logger.debug("name--------->>>>" + datalist.get(0).getName());
			setTodayDate(datalist.get(0).getTodayDate());
			setPayrollno(datalist.get(0).getPayrollno());
			setTotalsalary(datalist.get(0).getTotalsalary());
			setWorkingdays(datalist.get(0).getWorkingdays());
			setCommission(datalist.get(0).getCommission());
			setOvertime(datalist.get(0).getOvertime());
			setCurrency(datalist.get(0).getCurrency());
			setCurrencyAmount(datalist.get(0).getCurrencyAmount());
			setYear(datalist.get(0).getYear());
			setBaseCurrency(baseCurrency);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		employeepayrolledit();
		return "";
	}

	public void employeepayrolledit() {

		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);

		RequestContext.getCurrentInstance().openDialog(
				"aEmployeePayrollEditForm", options, null);

	}

	/****/
	public String confirmEdit() {
		logger.info("[conformedit()] --------------- Inside conformedit() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll view = new EmployeePayroll();
		try {
			try {
				setValidate("");
				if (currency.equalsIgnoreCase("")) {
					throw new Exception("Please select the Currency");
				}
				if (getWorkingdays() > 31) {
					throw new Exception("No of working days exceeds");
				}

				else if (workingdays == 0) {
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

			} catch (Exception e) {
				setValidate(e.getMessage());
				return "";
			}

			logger.debug("inside try in mb------>>>>");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			view.setPayrollno(payrollno);
			view.setStatus("update");
			view.setAdvanceamount(advanceamount);
			view.setBasicsalary(basicsalary);
			view.setDesignation(designation);
			view.setEmployeeid(employeeid);
			view.setMonth(month);
			view.setName(name);
			view.setTodayDate(todayDate);
			view.setPayrollno(payrollno);
			view.setTotalsalary(totalsalary);
			view.setWorkingdays(workingdays);
			view.setCommission(commission);
			view.setOvertime(overtime);
			view.setYear(year);
			view.setCurrency(currency);
			setDatalist(controller.editPayroll(view));
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			flag = "none";
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			setMonths(null);
			setYear(null);
			setDesignation(null);
			setDesignation1(null);
			setMonth1(null);
			setEmployeeid(null);
			setEmployeeid1(null);
		}

		return "Payrollconfirm";

	}

	public String payrolldelete() {
		logger.info("[payrolldelete()] --------------- Inside payrolldelete() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll view = new EmployeePayroll();
		try {
			logger.debug("inside try in mb------>>>>");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			view.setPayrollno(payrollno);
			view.setStatus("delete");
			setDatalist(controller.editPayroll(view));
			RequestContext.getCurrentInstance().execute("PF('paydeleteconf').show();");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		//employeepayrolldelete();
		return "";
	}

	public void employeepayrolldelete() {
		logger.info("[employeepayrolldelete()] --------------- Inside employeepayrolldelete() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);

		RequestContext.getCurrentInstance().openDialog(
				"aEmployeePayrollDeleteForm", options, null);
		logger.debug("aEmployeePayrollView method");

	}

	EmployeePayroll employeePayroll=new EmployeePayroll();
	
	public EmployeePayroll getEmployeePayroll() {
		return employeePayroll;
	}

	public void setEmployeePayroll(EmployeePayroll employeePayroll) {
		this.employeePayroll = employeePayroll;
	}

	public String search3(String a, String b) {
		logger.info("[search3()] --------------- Inside search3 method() ------------------------");
		employeePayroll.setFilterList(null);
		employeeid = a;
		designation = b;approveButtonFlag1="none";
		logger.debug("inside mb----<<<<::::::::->>>");
		ApplicationContext ctx = null;
		DemoController controller = null;int count=0;
		EmployeePayroll pay = new EmployeePayroll();
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
		try {
			setUserType(userType);
			try {
				validate = null;
				setValidate("");
				if (designation.equals("")) {
					throw new Exception("Please Choose the Designation");
				} else if (employeeid.equals("")) {
					throw new Exception("Please Choose the Employee ID");
				}

			} catch (Exception e) {
				flag2 = "none";
				flag = "none";
				flag1 = "none";
				setValidate(e.getMessage());
				return "";
			}
			logger.debug("inside try in mb------>>>>");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			pay.setMonths(months);
			logger.debug("month : " + getMonths());
			logger.debug("year : " + getYear());
			pay.setYear(year);
			pay.setAdvanceamount(advanceamount);
			pay.setBasicsalary(basicsalary);
			pay.setDesignation(designation);
			pay.setEmployeeid(employeeid);
			pay.setName(name);
			pay.setTodayDate(todayDate);
			pay.setPayrollno(payrollno);
			pay.setTotalsalary(totalsalary);
			pay.setWorkingdays(workingdays);
			pay.setCommission(commission);
			pay.setOvertime(overtime);
			controller.payroll(pay);
			if (pay.vallist.size() > 0) {
				setBaseCurrency(baseCurrency);
				flag1 = "1";
				flag2 = "none";
				flag = "none";
				flag3 = "none";
				setValue1(pay.getVallist());
				logger.debug("vallist----------->>>" + value1.size());
				for (int i = 0; i < value1.size(); i++) {
					if(value1.get(i).getApprovalStatus().equals("draft")){
						count++;
					}
				}
				System.out.println("count"+count);
				if(count==0){
					approveButtonFlag="none";
					approveButtonFlag1="none";
				}else{
					approveButtonFlag="1";
					approveButtonFlag1="1";
				}
				RequestContext.getCurrentInstance().execute("PF('nopayrolldlg').hide();");
			} else {
				flag1 = "none";
				flag2 = "none";
				flag = "none";
				flag3 = "1";
				RequestContext.getCurrentInstance().execute("PF('nopayrolldlg').show();");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public String payrollView() {
		logger.info("[payrollview()] --------------- Inside payrollview() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll view = new EmployeePayroll();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			view.setPayrollno(payrollno);
			setDatalist(controller.viewPayroll(view));
			setAdvanceamount(datalist.get(0).getAdvanceamount());
			setBasicsalary(datalist.get(0).getBasicsalary());
			setDesignation(datalist.get(0).getDesignation());
			setEmployeeid(datalist.get(0).getEmployeeid());
			setMonth(datalist.get(0).getMonth());
			setName(datalist.get(0).getName());
			setTodayDate(datalist.get(0).getTodayDate());
			setPayrollno(datalist.get(0).getPayrollno());
			setTotalsalary(datalist.get(0).getTotalsalary());
			setWorkingdays(datalist.get(0).getWorkingdays());
			setCommission(datalist.get(0).getCommission());
			setOvertime(datalist.get(0).getOvertime());
			setYear(datalist.get(0).getYear());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "payrollview";
	}

	public String returnBack() {
		logger.info("[returnback()] --------------- Inside returnback() method() ------------------------");
		flag = "none";
		flag1 = "none";
		flag2 = "none";
		flag3 = "none";
		setMonths(null);
		setYear(null);
		setDesignation(null);
		setDesignation1(null);
		setMonth1(null);
		setEmployeeid(null);
		setEmployeeid1(null);
		setMonth(null);
		return "payrollview";
	}

	public void changez(ValueChangeEvent changeEvent) {
		logger.info("[changez()] --------------- Inside changez() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll change = new EmployeePayroll();
		try {
			setDesignation("" + changeEvent.getNewValue());
			String s = changeEvent.getNewValue().toString();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			change.setEmployeeid1(employeeid1);
			change.setDesignation1(s);
			change.setList(list);
			logger.debug("[payrolldelete()] --------------- desigination ------------------------>"+getDesignation());
			list1 = controller.changezEvent(s, list1);
			logger.debug("[payrolldelete()] --------------- list1 ------------------------>"+list1);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
	}

	public String deleteback() {
		logger.info("[deleteback()] --------------- Inside deleteback() method() ------------------------");
		flag = "none";
		flag1 = "none";
		flag2 = "none";
		flag3 = "none";
		setMonths(null);
		setYear(null);
		setDesignation(null);
		setDesignation1(null);
		setMonth1(null);
		setEmployeeid(null);
		setEmployeeid1(null);
		return "deleteback";
	}

	/* jency */
	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public EmployeePayrollViewFormMB() {
		sortsOrders = new HashMap<String, SortOrder>();
		sortPriorities = new ArrayList<String>();
	}

	public void sort() {
		logger.info("[sort()] --------------- Inside sort() method() ------------------------");
		String property = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get(SORT_PROPERTY_PARAMETER);
		if (property != null) {
			SortOrder currentPropertySortOrder = sortsOrders.get(property);
			if (multipleSorting) {
				if (!sortPriorities.contains(property)) {
					sortPriorities.add(property);
				}
			} else {
				sortsOrders.clear();
			}
			if (currentPropertySortOrder == null
					|| currentPropertySortOrder.equals(SortOrder.DESCENDING)) {
				sortsOrders.put(property, SortOrder.ASCENDING);
			} else {
				sortsOrders.put(property, SortOrder.ASCENDING);
			}

		}
	}

	public Map<String, SortOrder> getSortsOrders() {
		return sortsOrders;
	}

	public void setSortsOrders(Map<String, SortOrder> sortsOrders) {
		this.sortsOrders = sortsOrders;
	}

	public List<String> getSortPriorities() {
		return sortPriorities;
	}

	public void setSortPriorities(List<String> sortPriorities) {
		this.sortPriorities = sortPriorities;
	}

	public boolean isMultipleSorting() {
		return multipleSorting;
	}

	public void setMultipleSorting(boolean multipleSorting) {
		this.multipleSorting = multipleSorting;
	}

	public void changes1(ValueChangeEvent ot) {
		logger.info("[changes1()] --------------- Inside changes1() method() ------------------------");
		String otamount = "" + ot.getNewValue();
		BigDecimal totalsal = BigDecimal.valueOf(0);
		try {
			totalsal = (new BigDecimal(otamount).add(
					new BigDecimal(basicsalary))
					.add(new BigDecimal(commission)).subtract(new BigDecimal(
					advanceamount)));
			setTotalsalary("" + totalsal);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public void changes2(ValueChangeEvent la) {
		logger.info("[changes2()] --------------- Inside changes2() method() ------------------------");
		String loanadv = "" + la.getNewValue();
		BigDecimal totalsal = BigDecimal.valueOf(0);
		try {
			totalsal = (new BigDecimal(basicsalary).add(new BigDecimal(
					commission)).add(new BigDecimal(overtime)))
					.subtract((new BigDecimal(loanadv)));
			setTotalsalary("" + totalsal);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	// prema begin 02/05/2016 dialog box creation for payroll view

	public void employeepayrollview() {
		logger.info("[employeepayrollview()] --------------- Inside employeepayrollview() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);

		RequestContext.getCurrentInstance().openDialog(
				"aEmployeePayrollViewForm", options, null);
		logger.debug("aEmployeePayrollView method");

	}

	// prema end 02/05/2016
	public String emppayrollviewclose() {
		RequestContext.getCurrentInstance().closeDialog(
				"aEmployeePayrollViewForm");
		return "";
	}

	ArrayList<EmployeePayroll> filterList;
	public ArrayList<EmployeePayroll> getFilterList() {
		return filterList;
	}

	public void setFilterList(ArrayList<EmployeePayroll> filterList) {
		this.filterList = filterList;
	}

	public String employeepayrollPage(){
		logger.info("[employeepayrollPage()] --------------- Inside employeepayrollPage() method() ------------------------");
		setFilterList(null);
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeePayroll employeePayroll=new EmployeePayroll();
		try{
			String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			datalist=controller.getEmployeePayrollDetails(employeePayroll);
			setBaseCurrency(baseCurrency);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "aEmployeePayrollView";
	}
	
	public String payrollApproval(){
		logger.info("[payrollApproval()] --------------- Inside payrollApproval() method() ------------------------");
		String status="";DemoController controller = null;
		int count=0;setValidate("");
		try{
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			for (int i = 0; i < value1.size(); i++) {
				if(value1.get(i).isPayrollCheck()==true){
					System.out.println("inside if----");
					count++;
				}
			}
			if(count==0){
				System.out.println("inside if=====");
				throw new Exception("Please Choose atleast one row for Approval.");
			}else{
				setValidate("");
				status=controller.payrollApproval(value1);
				System.out.println("status"+status);
				if(status.equalsIgnoreCase("Success")){
					RequestContext.getCurrentInstance().execute("PF('approvalConfirm').show()");
				}
			}
		}catch(Exception e){
			setValidate(e.getMessage());
			e.printStackTrace();
		}finally{
			//setValidate("");
		}
		return "";
	}
}