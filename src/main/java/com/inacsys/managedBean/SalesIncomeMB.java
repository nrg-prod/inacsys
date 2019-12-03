package com.inacsys.managedBean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.exception.DemoException;

@ManagedBean(name = "salesIncomeMB")
public class SalesIncomeMB {
	private static Logger logger = Logger.getLogger(SalesIncomeMB.class);
	public Date fDate;
	public Date tDate;
	public int sNo;
	public String amount;
	public String reason;
	public String sDate;
	public List<SalesIncomeMB> salesList = new ArrayList<SalesIncomeMB>();
	public List<SalesIncomeMB> filterList;
	public String flag;
	public String clientName;
	public String Validate;

	public List<SalesIncomeMB> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<SalesIncomeMB> filterList) {
		this.filterList = filterList;
	}

	public String getValidate() {
		return Validate;
	}

	public void setValidate(String validate) {
		Validate = validate;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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

	public List<SalesIncomeMB> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<SalesIncomeMB> salesList) {
		this.salesList = salesList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String flagpop;

	public String getFlagpop() {
		return flagpop;
	}

	public void setFlagpop(String flagpop) {
		this.flagpop = flagpop;
	}

	public String salesMenuCall() {
		logger.info("[salesMenuCall()]------------------- inside salesMenuCall method() ---------------");
		try {
			
			setValidate("");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			fDate = null;
			tDate = null;
			salesList.clear();
			flag = "none";
			flagpop = "none";
		}
		return "";
	}

	public String cancel() {
		logger.info("[cancel()]------------------- inside cancel method() ---------------");
		flag = "none";
		flagpop = "none";
		fDate = null;
		tDate = null;

		return "accreceivableassetshome";

	}

	public Date ttDate;

	public Date getTtDate() {
		return ttDate;
	}

	public void setTtDate(Date ttDate) {
		this.ttDate = ttDate;
	}

	BigDecimal ss = BigDecimal.valueOf(0);
	BigDecimal totals = BigDecimal.valueOf(0);

	public BigDecimal getSs() {
		return ss;
	}

	public void setSs(BigDecimal ss) {
		this.ss = ss;
	}

	public BigDecimal getTotals() {
		return totals;
	}

	public void setTotals(BigDecimal totals) {
		this.totals = totals;
	}

	public String SalesIncomeAsset() {
		logger.info("[SalesIncomeAsset()]------------------- inside SalesIncomeAsset method() ---------------");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat newdate = new SimpleDateFormat("dd/MM/yyyy");
		DemoController controller = null;
		try {
			setValidate(null);
			ss = BigDecimal.valueOf(0);
			salesList.clear();
			flag = "1";
			flagpop = "none";
			List<SalesIncomeMB> li = null;
			List<SalesIncomeMB> li1 = new ArrayList<SalesIncomeMB>();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			li = controller.getAccountReceivableSales(fDate, tDate);
			List<Date> dt = new ArrayList<Date>();
			List<Date> dt1 = new ArrayList<Date>();
			if (li.size() > 0) {
				flag = "1";
				flagpop = "none";
				for (int i = 0; i < li.size(); i++) {
					dt.add((li.get(i).gettDate()));
				}
			} else {
				flagpop = "1";
				flag = "none";
			}
			Collections.sort(dt);
			HashSet<Date> dtset = new HashSet<Date>();
			for (Date item : dt) {
				if (!dtset.contains(item)) {
					dt1.add(item);
					dtset.add(item);
				}
			}
			for (int i = 0; i < dt1.size(); i++) {
				for (int j = 0; j < li.size(); j++) {
					if (df.format(dt1.get(i)).equalsIgnoreCase(
							li.get(j).getsDate())) {
						SalesIncomeMB si = new SalesIncomeMB();
						si.setAmount(li.get(j).getAmount());
						si.setReason(li.get(j).getReason());
						si.settDate(li.get(j).gettDate());
						si.setClientName(li.get(j).getClientName());
						li1.add(si);
					}
				}

			}
			for (int i = 0; i < li1.size(); i++) {
				SalesIncomeMB si = new SalesIncomeMB();
				si.setsNo(i + 1);
				si.setAmount(li1.get(i).getAmount());
				si.setReason(li1.get(i).getReason());
				si.settDate(li.get(i).gettDate());
				si.setClientName(li1.get(i).getClientName());
				salesList.add(si);
				ss = ss.add(new BigDecimal(li1.get(i).getAmount()));
				setTotals(ss);
			}

		} catch (DemoException ie) {
			logger.error("Inside Exception", ie);
			flag = "none";
			setValidate(ie.getMessage());
			logger.debug("" + ie.getMessage());
		} catch (Exception e) {
			flag = "none";
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	// prema begin 02/05/2016 dialog box creation for Account Receivable Assets

	public void accrecevassets() {
		logger.info("[accrecevassets()]------------------- inside accrecevassets method() ---------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);

		RequestContext.getCurrentInstance().openDialog(
				"aAccountsReceivableAssets", options, null);
		salesMenuCall();
	}

	// prema end 02/05/2016
	public void accrecevassetsclose() {
		logger.info("[accrecevassetsclose()]------------------- inside accrecevassetsclose method() ---------------");
		RequestContext.getCurrentInstance().closeDialog(
				"aAccountsReceivableAssets");
	}
}
