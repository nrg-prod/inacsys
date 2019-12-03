package com.inacsys.managedBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.PurchaseOrder;

@ManagedBean(name = "cashBookMB")
public class CashBookMB {
	private static Logger logger = Logger.getLogger(CashBookMB.class);
	public String validate;

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public Date fromDate;
	public Date toDate;
	public String flag = "none";
	public String flag1 = "none";

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String redirectCashBook() {
		logger.debug("----------------inside redirectbook------------");
		setFromDate(null);
		setToDate(null);
		setFlag("none");
		setFlag1("none");
		setCashBookList(null);
		return "";
	}

	PurchaseOrder purchaseOrder = new PurchaseOrder();
	public ArrayList<PurchaseOrder> cashBookList = null;
	ArrayList<PurchaseOrder> filterList;

	public ArrayList<PurchaseOrder> getFilterList() {
		return filterList;
	}

	public void setFilterList(ArrayList<PurchaseOrder> filterList) {
		this.filterList = filterList;
	}

	public ArrayList<PurchaseOrder> getCashBookList() {
		return cashBookList;
	}

	public void setCashBookList(ArrayList<PurchaseOrder> cashBookList) {
		this.cashBookList = cashBookList;
	}

	BigDecimal debit = BigDecimal.valueOf(0);
	BigDecimal credit = BigDecimal.valueOf(0);

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String findCashBook() {
		logger.info("[findCashBook()] --------------- Inside findCashBook() method() ------------------------");
		DemoController controller = null;
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			purchaseOrder.setClientID(clientID);
			flag = "none";
			flag1 = "none";
			setValidate("");
			ApplicationContext ap = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ap.getBean("controller");
			controller.findCashBook(purchaseOrder);
			setCashBookList(purchaseOrder.getCashBookList());
			if (cashBookList.size() > 0) {
				flag = "1";
				for (PurchaseOrder cashBookList1 : cashBookList) {
					logger.debug("---------inside------------");
					try {
						debit = debit.add(new BigDecimal(cashBookList1
								.getDebit()));
					} catch (Exception e) {
						credit = credit.add(new BigDecimal(cashBookList1
								.getCredit()));
					}
				}
				balance = (credit.subtract(debit));
				logger.debug("[findCashBook()] --------------- credit ------------------------>"+credit);
				logger.debug("[findCashBook()] --------------- debit ------------------------>"+debit);
				logger.debug("[findCashBook()] --------------- balance ------------------------>"+balance);
				if (balance.compareTo(BigDecimal.valueOf(0)) == 1) {
					color = "green";
				} else {
					color = "red";
				}
			} else {
				flag1 = "1";
			}

		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
		}
		return "";
	}

	public int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	public BigDecimal balance = BigDecimal.valueOf(0);

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	// prema begin 02/05/2016 dialog box creation for cash book

	public void cashbook() {
		logger.info("[cashbook()] --------------- Inside cashbook() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);

		RequestContext.getCurrentInstance().openDialog("aCashBook", options,
				null);
		redirectCashBook();
	}

	// prema end 02/05/2016

	public void cashbookclose() {
		RequestContext.getCurrentInstance().closeDialog("aCashBook");
	}
}
