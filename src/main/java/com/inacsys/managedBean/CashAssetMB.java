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
import com.inacsys.exception.DemoException;

@ManagedBean(name = "cashAssetMB")
public class CashAssetMB {

	private static Logger logger = Logger
			.getLogger(CashAssetMB.class);
	public Date fDate;
	public Date tDate;
	public int sNo;
	public String amount;
	public String reason;
	public String sDate;
	public List<CashAssetMB> cashList = new ArrayList<CashAssetMB>();
	public String flag;
	public String flag1;
	public String clientName;
	public String Validate;

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

	public List<CashAssetMB> getCashList() {
		return cashList;
	}

	public void setSalesList(List<CashAssetMB> cashList) {
		this.cashList = cashList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String cashAsset() {
		logger.info("[cashAsset()] --------------- Inside cashAsset() method() ------------------------");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DemoController controller = null;
		try {
			setValidate(null);
			cashList.clear();
			flag = "1";
			List<CashAssetMB> ca = new ArrayList<CashAssetMB>();
			List<CashAssetMB> ca1 = new ArrayList<CashAssetMB>();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if (fDate == null) {
				throw new DemoException("Enter From Date");
			} else if (tDate == null) {
				throw new DemoException("Enter To Date");
			}
			ca = controller.getCashAsset(fDate, tDate);
			List<Date> dt = new ArrayList<Date>();
			List<Date> dt1 = new ArrayList<Date>();
			logger.debug("[cashAsset()] --------------- Size ------------------------>"+ca.size());
			logger.debug("Size>>>>>>>>>>>>>>>>" + ca.size());
			if (ca.size() > 0) {

				for (int i = 0; i < ca.size(); i++) {
					dt.add(df.parse(ca.get(i).getsDate()));
				}
			} else if (ca.size() == 0) {
				flag1 = "1";
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
				for (int j = 0; j < ca.size(); j++) {
					if (df.format(dt1.get(i)).equalsIgnoreCase(
							ca.get(j).getsDate())) {
						CashAssetMB si = new CashAssetMB();
						si.setAmount(ca.get(j).getAmount());
						si.setReason(ca.get(j).getReason());
						si.setsDate(ca.get(j).getsDate());
						si.setClientName(ca.get(j).getClientName());
						ca1.add(si);
					}
				}
			}
			for (int i = 0; i < ca1.size(); i++) {
				CashAssetMB si = new CashAssetMB();
				si.setsNo(i + 1);
				si.setAmount(ca1.get(i).getAmount());
				si.setReason(ca1.get(i).getReason());
				si.setsDate(ca1.get(i).getsDate());
				si.setClientName(ca1.get(i).getClientName());
				cashList.add(si);
			}
		} catch (DemoException ie) {
			flag = "none";
			setValidate(ie.getMessage());
			logger.debug("" + ie.getMessage());
		} catch (Exception e) {
			logger.error("Inside Exception",e);	
		} finally {

		}
		return "";
	}

	public String cashMenuCall() {
		logger.info("[cashMenuCall()] --------------- Inside cashMenuCall() method() ------------------------");
		try {
			setValidate("");
			flag = "none";
			flag1 = "none";
		} catch (Exception e) {
			logger.error("Inside Exception",e);	
		} finally {
			fDate = null;
			tDate = null;
			cashList.clear();
			flag = "none";
		}
		return "aCashAssets";
	}
}
