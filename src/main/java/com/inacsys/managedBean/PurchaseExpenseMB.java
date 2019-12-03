package com.inacsys.managedBean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.exception.DemoException;

@ManagedBean(name = "purchaseExpenseMB")
public class PurchaseExpenseMB {
	private static Logger logger = Logger.getLogger(PurchaseExpenseMB.class);

	public Date fDate;
	public Date tDate;
	public int sNo;
	public String amount;
	public String reason;
	public String sDate;
	public List<PurchaseExpenseMB> purchaseList = new ArrayList<PurchaseExpenseMB>();
	List<PurchaseExpenseMB> filteredList1;

	public List<PurchaseExpenseMB> getFilteredList1() {
		return filteredList1;
	}

	public void setFilteredList1(List<PurchaseExpenseMB> filteredList1) {
		this.filteredList1 = filteredList1;
	}

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

	public List<PurchaseExpenseMB> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<PurchaseExpenseMB> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	BigDecimal tots = BigDecimal.valueOf(0);
	BigDecimal ee = BigDecimal.valueOf(0);

	public BigDecimal getEe() {
		return ee;
	}

	public void setEe(BigDecimal ee) {
		this.ee = ee;
	}

	public BigDecimal getTots() {
		return tots;
	}

	public void setTots(BigDecimal tots) {
		this.tots = tots;
	}

	/*
	 * public String purchaseExpense() { purchaseList.clear(); SimpleDateFormat
	 * df=new SimpleDateFormat("yyyy-MM-dd"); SimpleDateFormat newdate=new
	 * SimpleDateFormat("dd/MM/yyyy"); InventoryController controller=null; try
	 * { setValidate(null); ee=BigDecimal.valueOf(0); flag="1";
	 * List<PurchaseExpenseMB> pe=new ArrayList<PurchaseExpenseMB>();
	 * List<PurchaseExpenseMB> pe1=new ArrayList<PurchaseExpenseMB>();
	 * ApplicationContext ctx =
	 * FacesContextUtils.getWebApplicationContext(FacesContext
	 * .getCurrentInstance()); controller = (InventoryController)
	 * ctx.getBean("controller"); if(fDate==null) { throw new
	 * InventoryException("Please Choose the From Date"); } else if(tDate==null)
	 * { throw new InventoryException("Please Choose the To Date"); }
	 * pe=controller.getPurchaseExpences(fDate, tDate); List<Date> dt=new
	 * ArrayList<Date>(); List<Date> dt1=new ArrayList<Date>();
	 * logger.debug("Size3>>>>>>>>>>>>>>>>"+pe.size()); if(pe.size()>0) {
	 * 
	 * for(int i=0;i<pe.size();i++) { dt.add(df.parse(pe.get(i).getsDate())); }
	 * for(int i=0;i<pe.size();i++) { dt.add((pe.get(i).gettDate())); }
	 * logger.debug( "date-->> "+dt); }
	 * 
	 * else if(pe.size()==0) { flag1="1";flag="none"; } Collections.sort(dt);
	 * logger.debug(dt); HashSet<Date> dtset=new HashSet<Date>(); for (Date item
	 * : dt) { logger.debug("1"); if (!dtset.contains(item)) {
	 * logger.debug("11"); dt1.add(item); dtset.add(item); } } for(int
	 * i=0;i<dt1.size();i++) { for(int j=0;j<pe.size();j++) {
	 * if(df.format(dt1.get(i)).equalsIgnoreCase(pe.get(j).getsDate())) {
	 * 
	 * 
	 * PurchaseExpenseMB si=new PurchaseExpenseMB();
	 * logger.debug("AAAA~~~~~"+pe.get(j).getAmount());
	 * si.setAmount(pe.get(j).getAmount()); si.setReason(pe.get(j).getReason());
	 * si.setsDate(pe.get(j).getsDate()); si.settDate(pe.get(j).gettDate());
	 * 
	 * si.setClientName(pe.get(j).getClientName()); pe1.add(si); } } }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * for(int i=0;i<pe1.size();i++) { PurchaseExpenseMB si=new
	 * PurchaseExpenseMB(); si.setsNo(i+1);
	 * logger.debug("AAAA~~2~~~"+pe1.get(i).getAmount());
	 * si.setAmount(pe1.get(i).getAmount());
	 * si.setReason(pe1.get(i).getReason()); si.setsDate(pe1.get(i).getsDate());
	 * si.settDate(pe.get(i).gettDate());
	 * si.setClientName(pe1.get(i).getClientName()); purchaseList.add(si);
	 * ee=ee.add(new BigDecimal(pe1.get(i).getAmount())); setTots(ee); } }
	 * catch(InventoryException ie) { flag="none"; setValidate(ie.getMessage());
	 * logger.debug(""+ie.getMessage()); } catch(Exception e) {
	 * logger.error("Inside Exception",e); } finally {
	 * 
	 * } return ""; }
	 */
	public String purchaseExpense() {
		purchaseList = null;

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat newdate = new SimpleDateFormat("dd/MM/yyyy");
		DemoController controller = null;
		try {
			setValidate(null);
			ee = BigDecimal.valueOf(0);
			flag = "none";
			flag1 = "none";
			List<PurchaseExpenseMB> pe = new ArrayList<PurchaseExpenseMB>();
			List<PurchaseExpenseMB> pe1 = new ArrayList<PurchaseExpenseMB>();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			/*
			 * if(fDate==null) { throw new
			 * DemoException("Please Choose the From Date"); } else
			 * if(tDate==null) { throw new
			 * DemoException("Please Choose the To Date"); }
			 */
			pe = controller.getPurchaseExpences(fDate, tDate);
			List<Date> dt = new ArrayList<Date>();
			List<Date> dt1 = new ArrayList<Date>();
			logger.debug("Size3>>>>>>>>>>>>>>>>" + pe.size());
			if (pe.size() > 0) {
				flag = "1";

				for (int i = 0; i < pe.size(); i++) {
					dt.add(df.parse(pe.get(i).getsDate()));
				}
				for (int i = 0; i < pe.size(); i++) {
					dt.add((pe.get(i).gettDate()));
				}
				logger.debug("date-->> " + dt);
			}

			else {
				flag1 = "1";
				flag = "none";
			}
			Collections.sort(dt);
			logger.debug(dt);
			HashSet<Date> dtset = new HashSet<Date>();
			for (Date item : dt) {
				logger.debug("1");
				if (!dtset.contains(item)) {
					logger.debug("11");
					dt1.add(item);
					dtset.add(item);
				}
			}

			for (int j = 0; j < pe.size(); j++) {
				/*
				 * for(int i=0;i<dt1.size();i++) {
				 * if(df.format(dt1.get(i)).equalsIgnoreCase
				 * (df.format(pe.get(j).gettDate()))) {
				 */

				PurchaseExpenseMB si = new PurchaseExpenseMB();
				logger.debug("AAAA~~~~~" + pe.get(j).getAmount());
				si.setAmount(pe.get(j).getAmount());
				si.setReason(pe.get(j).getReason());
				/* si.setsDate(pe.get(j).getsDate()); */
				si.settDate(pe.get(j).gettDate());

				si.setClientName(pe.get(j).getClientName());
				logger.debug("vendor name>>>>>>>>" + pe.get(j).getClientName());
				pe1.add(si);
				/*
				 * } }
				 */
			}

			purchaseList = new ArrayList<PurchaseExpenseMB>();

			for (int i = 0; i < pe1.size(); i++) {
				PurchaseExpenseMB si = new PurchaseExpenseMB();
				si.setsNo(i + 1);
				logger.debug("AAAA~~2~~~" + pe1.get(i).getAmount());
				si.setAmount(pe1.get(i).getAmount());
				si.setReason(pe1.get(i).getReason());
				si.setsDate(pe1.get(i).getsDate());
				si.settDate(pe.get(i).gettDate());
				si.setClientName(pe1.get(i).getClientName());
				logger.debug("cname-9-9--9-====>" + si.getClientName());
				purchaseList.add(si);
				ee = ee.add(new BigDecimal(pe1.get(i).getAmount()));
				setTots(ee);
			}
		} catch (DemoException ie) {
			flag = "none";
			setValidate(ie.getMessage());
			logger.debug("" + ie.getMessage());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}

		return "";
	}

	public String purchaseMenuCall() {
		try {
			setValidate("");

			flag = "none";
			flag1 = "none";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			fDate = null;
			tDate = null;
			purchaseList.clear();
			flag = "none";
		}
		return "";
	}

	// prema begin 02/05/2016 dialog box creation for Payable Liabilities

	public void payableliabilities() {

		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);

		RequestContext.getCurrentInstance().openDialog(
				"accountPayableLiabilites", options, null);
		purchaseMenuCall();
	}

	// prema end 02/05/2016
	public void payableliabilityclose() {
		RequestContext.getCurrentInstance().closeDialog(
				"accountPayableLiabilites");
	}

	// john
	@ManagedProperty(value = "#{salesIncomeMB}")
	SalesIncomeMB salesIncomeMB;

	@ManagedProperty(value = "#{cashBookMB}")
	CashBookMB cashBookMB;

	public CashBookMB getCashBookMB() {
		return cashBookMB;
	}

	public void setCashBookMB(CashBookMB cashBookMB) {
		this.cashBookMB = cashBookMB;
	}

	public SalesIncomeMB getSalesIncomeMB() {
		return salesIncomeMB;
	}

	public void setSalesIncomeMB(SalesIncomeMB salesIncomeMB) {
		this.salesIncomeMB = salesIncomeMB;
	}

/*	public void tabchange(TabChangeEvent event) {
		if (event.getTab().getTitle()
				.equalsIgnoreCase("Account Payable Liablity")) {
			logger.debug("inside Account Payable Liablity if condition");
			purchaseExpense();
		}

		else if ((event.getTab().getTitle()
				.equalsIgnoreCase("Account Receivable Asset"))) {
			logger.debug("inside Account Receivable Asset if condition");
			salesIncomeMB.SalesIncomeAsset();
		}

		else if ((event.getTab().getTitle().equalsIgnoreCase("Cash Book"))) {
			logger.debug("inside Cash Book if condition");
			cashBookMB.findCashBook();
		} else if ((event.getTab().getTitle()
				.equalsIgnoreCase("Profit and Loss"))) {
			logger.debug("inside Profit and Loss if condition");
		}
	}*/

}
