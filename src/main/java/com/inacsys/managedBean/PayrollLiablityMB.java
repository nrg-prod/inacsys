package com.inacsys.managedBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.EmployeePayroll;
import com.inacsys.exception.DemoException;

@ManagedBean(name = "payrollLiablityMB")
public class PayrollLiablityMB {
	private static Logger logger = Logger.getLogger(PayrollLiablityMB.class);
	public Date fDate;
	public Date tDate;
	public int sNo;
	public String amount;
	public String reason;
	public String sDate;
	public List<PayrollLiablityMB> payrollList = new ArrayList<PayrollLiablityMB>();
	public String flag;
	public String employeeName;
	public String payrollNo;
	public List<EmployeePayroll> payroll = new ArrayList<EmployeePayroll>();
	public String Validate;
	public String flag1;

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public String getValidate() {
		return Validate;
	}

	public void setValidate(String validate) {
		Validate = validate;
	}

	public List<EmployeePayroll> getPayroll() {
		return payroll;
	}

	public void setPayroll(List<EmployeePayroll> payroll) {
		this.payroll = payroll;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPayrollNo() {
		return payrollNo;
	}

	public void setPayrollNo(String payrollNo) {
		this.payrollNo = payrollNo;
	}

	public Date getfDate() {
		return fDate;
	}

	public void setfDate(Date fDate) {
		this.fDate = fDate;
	}

	public Date gettDate() {
		return tDate;
	}

	public void settDate(Date tDate) {
		this.tDate = tDate;
	}

	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public List<PayrollLiablityMB> getPayrollList() {
		return payrollList;
	}

	public void setPayrollList(List<PayrollLiablityMB> payrollList) {
		this.payrollList = payrollList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String payrollMenuCall() {
		logger.info("[payrollMenuCall()] --------------- Inside payrollMenuCall() method() ------------------------");
		try {
			setValidate("");
			flag1 = "none";
		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
		} finally {
			fDate = null;
			tDate = null;
			payrollList.clear();
			flag = "none";

		}
		return "aPayrollLiabilites";
	}

	public String payrollLiability() {
		logger.info("[payrollLiability()] --------------- Inside payrollLiability() method() ------------------------");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DemoController controller = null;
		try {
			logger.info("[payrollLiability()] --------------- Inside payrollLiability() method() try start ------------------------");
			setValidate(null);
			payrollList.clear();
			flag = "1";
			List<PayrollLiablityMB> pr = new ArrayList<PayrollLiablityMB>();
			List<PayrollLiablityMB> pr1 = new ArrayList<PayrollLiablityMB>();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if (fDate == null) {
				logger.info("[payrollLiability()] Inside If condition 1 ");
				throw new DemoException("Enter From Date");
			} else if (tDate == null) {
				throw new DemoException("Enter To Date");
			}
			logger.info("[payrollLiability()] Before calling  getPayrollLiability() method ------------------------");
			pr = controller.getPayrollLiability(fDate, tDate);
			logger.info("[payrollLiability()] Successfully Called  getPayrollLiability() method ------------------------");
			List<String> dt = new ArrayList<String>();
			List<String> dt1 = new ArrayList<String>();
			logger.debug("[payrollLiability()] Size ------------------------>"+ pr.size());
			if (pr.size() > 0) {
				logger.info("[payrollLiability()] Inside If condition 2 ");
				for (int i = 0; i < pr.size(); i++) {
					dt.add(pr.get(i).getPayrollNo());
				}
			} else if (pr.size() == 0) {
				logger.info("[payrollLiability()] Inside else If ");
				flag = "none";
				flag1 = "1";
			}
			Collections.sort(dt);
			logger.debug("[payrollLiability] After sorted List value --->"+dt);
			HashSet<String> dtset = new HashSet<String>();
			for (String item : dt) {
				logger.info("[payrollLiability()] Inside 1 for loop");
				if (!dtset.contains(item)) {
					logger.info("[payrollLiability()] Inside If condition 3 ");
					dt1.add(item);
					dtset.add(item);
				}
			}
			for (int i = 0; i < dt1.size(); i++) {
				logger.info("[payrollLiability()] Inside 2 for loop");
				for (int j = 0; j < pr.size(); j++) {
					logger.info("[payrollLiability()] Inside 3 for loop start ");
					if (dt1.get(i).equalsIgnoreCase(pr.get(j).getPayrollNo())) {
						logger.info("[payrollLiability()] Inside If condition 4 ");
						PayrollLiablityMB si = new PayrollLiablityMB();
						si.setAmount(pr.get(j).getAmount());
						logger.debug("[payrollLiability] Amount --------------->" + pr.get(j).getAmount());
						si.setsDate(pr.get(j).getsDate());
						logger.debug("[payrollLiability] Date -------------------->"+ pr.get(j).getsDate());
						si.setPayrollNo(pr.get(j).getPayrollNo());
						logger.debug("[payrollLiability] Payrole Number ------->"+ pr.get(j).getPayrollNo());
						si.setEmployeeName(pr.get(j).getEmployeeName());
						pr1.add(si);
					}
					logger.info("[payrollLiability()] Inside 3 for loop end ");
				}
			}
			for (int i = 0; i < pr1.size(); i++) {
				logger.info("[payrollLiability()] Inside 4 for loop start");
				PayrollLiablityMB si = new PayrollLiablityMB();
				si.setsNo(i + 1);
				si.setAmount(pr1.get(i).getAmount());
				logger.debug("[payrollLiability()] Amount ------------->" + pr1.get(i).getAmount());
				si.setsDate(pr1.get(i).getsDate());
				logger.debug("[payrollLiability()] Date ------------->" + pr1.get(i).getsDate());
				si.setPayrollNo(pr1.get(i).getPayrollNo());
				logger.debug("[payrollLiability()] Pay roll ------------->" + pr1.get(i).getPayrollNo());
				si.setEmployeeName(pr1.get(i).getEmployeeName());
				logger.debug("[payrollLiability()] Employee name ------------->" + pr1.get(i).getEmployeeName());
				payrollList.add(si);
				logger.info("[payrollLiability()] Inside 4 for loop end");

			}
		} catch (DemoException ie) {
			flag = "none";
			setValidate(ie.getMessage());
			logger.debug("[payrollLiability()] Exception --->" + ie.getMessage());
		} catch (Exception e) {
			logger.debug("[payrollLiability()] Exception --->" + e.getMessage());

		} finally {
			controller =null;
		}
		return "";
	}
}
