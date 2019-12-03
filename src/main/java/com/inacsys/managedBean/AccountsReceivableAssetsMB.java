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
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;

@ManagedBean(name = "accountsReceivableAssetsMB")
public class AccountsReceivableAssetsMB {
	private static Logger logger = Logger
			.getLogger(AccountsReceivableAssetsMB.class);
	private Date fromDate;
	private Date toDate;
	public String flag = "none";
	public String flag1 = "none";
	List<PurchaseOrder> accountList = null;

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

	public List<PurchaseOrder> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<PurchaseOrder> accountList) {
		this.accountList = accountList;
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

	PurchaseOrder purchaseOrder;

	public String viewAccountRecievable() {
		logger.info("[viewAccountRecievable()] --------------- Inside viewAccountRecievable() method() ------------------------");
		DemoController controller = null;
		try {
			flag = "none";
			flag1 = "none";
			purchaseOrder = new PurchaseOrder();
			logger.debug("[viewAccountRecievable()] --------------- from date ------------------------>"+fromDate);
			logger.debug("[viewAccountRecievable()] --------------- to date ------------------------>"+toDate);
			purchaseOrder.setFromDate(fromDate);
			purchaseOrder.setToDate(toDate);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");

			controller.viewAccountReceivable(purchaseOrder);
			accountList = purchaseOrder.getResulfinal();
			if (accountList.size() > 0) {
				logger.debug("1");
				flag = "1";
			} else {
				logger.debug("2");
				flag1 = "1";
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}finally {
			controller =null;
		}
		return null;

	}

	public String cancel() {
		logger.info("[cancel()] --------------- Inside cancel() method() ------------------------");
		flag = "none";
		flag1 = "none";
		fromDate = null;
		toDate = null;

		return "aAccountReceivableAsset.xhtml";

	}
}
