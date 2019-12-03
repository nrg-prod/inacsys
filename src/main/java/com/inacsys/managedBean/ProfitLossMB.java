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
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.exception.DemoException;

@ManagedBean(name = "profitLossMB")
public class ProfitLossMB {
	private static Logger logger = Logger.getLogger(ProfitLossMB.class);
	public Date fDate;
	public Date tDate;
	public int sNo;
	public String amount;
	public String sDate;
	public List<ProfitLossMB> profit = new ArrayList<ProfitLossMB>();
	List<ProfitLossMB> filterList;
	public List<ProfitLossMB> loss = new ArrayList<ProfitLossMB>();
	public String pAmount;
	public String lAmount;
	public String plAmount;
	public String color;
	public List<SalesIncomeMB> saleList = new ArrayList<SalesIncomeMB>();
	public List<CashAssetMB> cashList = new ArrayList<CashAssetMB>();
	public List<PayrollLiablityMB> payrollList = new ArrayList<PayrollLiablityMB>();
	public List<PurchaseExpenseMB> purchaseList = new ArrayList<PurchaseExpenseMB>();
	public String clientName;
	public String reason;
	public String salesorderno;

	public List<ProfitLossMB> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<ProfitLossMB> filterList) {
		this.filterList = filterList;
	}

	public String Validate;

	public String getValidate() {
		return Validate;
	}

	public void setValidate(String validate) {
		Validate = validate;
	}

	public String getSalesorderno() {
		return salesorderno;
	}

	public void setSalesorderno(String salesorderno) {
		this.salesorderno = salesorderno;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public List<ProfitLossMB> getProfit() {
		return profit;
	}

	public void setProfit(List<ProfitLossMB> profit) {
		this.profit = profit;
	}

	public List<ProfitLossMB> getLoss() {
		return loss;
	}

	public void setLoss(List<ProfitLossMB> loss) {
		this.loss = loss;
	}

	public String getpAmount() {
		return pAmount;
	}

	public void setpAmount(String pAmount) {
		this.pAmount = pAmount;
	}

	public String getlAmount() {
		return lAmount;
	}

	public void setlAmount(String lAmount) {
		this.lAmount = lAmount;
	}

	public String getPlAmount() {
		return plAmount;
	}

	public void setPlAmount(String plAmount) {
		this.plAmount = plAmount;
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

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	public String flag;
	public String flag1;
	public String flag2;
	
	private String profits;
	private String losses;

	public String getProfits() {
		return profits;
	}

	public void setProfits(String profits) {
		this.profits = profits;
	}

	public String getLosses() {
		return losses;
	}

	public void setLosses(String losses) {
		this.losses = losses;
	}

	public String profitLose() {
		logger.info("[profitLose()] --------------- Inside profitLose() method() ------------------------");
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		DemoController controller = null;
		try {
			setValidate(null);
			profit.clear();
			loss.clear();
			flag = "1";
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			saleList = controller.getAccountReceivableSales1(fDate, tDate);
			cashList = controller.getCashAsset(fDate, tDate);
			List<Date> dtin = new ArrayList<Date>();
			List<Date> dtin1 = new ArrayList<Date>();
			List<Date> dts = new ArrayList<Date>();
			List<Date> dts1 = new ArrayList<Date>();
			List<ProfitLossMB> dummy = new ArrayList<ProfitLossMB>();
			if (saleList.size() > 0) {
				logger.info("[profitLose()] --------------- if condition salelist ------------------------");
				for (int i = 0; i < saleList.size(); i++) {
					logger.info("[profitLose()] --------------- if condition salelist for loop ------------------------");
					ProfitLossMB pal = new ProfitLossMB();
					pal.setsDate(saleList.get(i).getsDate());
					pal.setClientName(saleList.get(i).getClientName());
					pal.setReason(saleList.get(i).getReason());
					pal.setAmount(saleList.get(i).getAmount());
					profit.add(pal);
				}
			}
			if (cashList.size() > 0) {
				logger.info("[cashlist()] --------------- if condition cashlist ------------------------");
				for (int i = 0; i < cashList.size(); i++) {
					logger.info("[cashlist()] --------------- if condition cashlist for loop ------------------------");
					ProfitLossMB pal = new ProfitLossMB();
					pal.setsDate(cashList.get(i).getsDate());
					pal.setClientName(cashList.get(i).getClientName());
					pal.setReason(cashList.get(i).getReason());
					pal.setAmount(cashList.get(i).getAmount());
					profit.add(pal);
				}
			}

			for (int i = 0; i < profit.size(); i++) {
				logger.info("[profitLose()] --------------- for condition profit size ------------------------");
				System.out.println("-------------" + profit.get(i).getsDate());
				dts.add(df.parse(profit.get(i).getsDate()));
				System.out.println("-------------parse date-----------"
						+ df.parse(profit.get(i).getsDate()));
			}
			Collections.sort(dts);
			HashSet<Date> dtset1 = new HashSet<Date>();
			for (Date item : dts) {
				if (!dtset1.contains(item)) {
					dts1.add(item);
					dtset1.add(item);
					dtin.add(item);
				}
			}
			for (int i = 0; i < dts1.size(); i++) {
				for (int j = 0; j < profit.size(); j++) {
					if (dts1.get(i).equals(df.parse(profit.get(j).getsDate()))) {
						ProfitLossMB pal = new ProfitLossMB();
						pal.setsDate(df.format(dts1.get(i)));
						pal.setClientName(profit.get(j).getClientName());
						pal.setReason(profit.get(j).getReason());
						pal.setAmount(profit.get(j).getAmount());
						dummy.add(pal);
					}
				}
			}
			profit.clear();
			BigDecimal profitcount=BigDecimal.valueOf(0);
			for (int i = 0; i < dummy.size(); i++) {
				logger.info("[profitLose()] --------------- for condition dummy size ------------------------");
				ProfitLossMB pal = new ProfitLossMB();
				pal.setsNo(i + 1);
				pal.setsDate(dummy.get(i).getsDate());
				pal.setClientName(dummy.get(i).getClientName());
				pal.setReason(dummy.get(i).getReason());
				pal.setAmount(dummy.get(i).getAmount());
				profit.add(pal);
				profitcount=profitcount.add(new BigDecimal(1));
			}

			String amtin = "0";
			for (int i = 0; i < profit.size(); i++) {
				amtin = ""
						+ (new BigDecimal(amtin).add(new BigDecimal(profit.get(
								i).getAmount())));

			}
			pAmount = "" + amtin;
			amtin = "0";
			dummy.clear();
			dts.clear();
			dts1.clear();

			/* Expense */

			purchaseList = controller.getPurchaseExpences1(fDate, tDate);
			payrollList = controller.getPayrollLiability(fDate, tDate);

			List<Date> dtout = new ArrayList<Date>();
			List<String> dtoutt = new ArrayList<String>();
			List<String> dtoutt1 = new ArrayList<String>();
			List<String> dtpu = new ArrayList<String>();
			List<String> dtpu1 = new ArrayList<String>();
			if (payrollList.size() > 0) {
				logger.info("[payrollList()] --------------- if condition payrollList ------------------------");
				for (int i = 0; i < payrollList.size(); i++) {
					ProfitLossMB pal = new ProfitLossMB();
					logger.debug("Date----" + payrollList.get(i).getsDate());
					pal.setsDate(payrollList.get(i).getsDate());
					pal.setClientName(payrollList.get(i).getEmployeeName());
					pal.setReason("payroll");
					pal.setAmount("" + payrollList.get(i).getAmount());
					loss.add(pal);
				}
			}

			logger.debug("Size out p>>>>>>>>>>>>>>>>" + purchaseList.size());
			if (purchaseList.size() > 0) {
				for (int i = 0; i < purchaseList.size(); i++) {
					ProfitLossMB pal = new ProfitLossMB();
					logger.debug("Date----" + purchaseList.get(i).getsDate());
					pal.setsDate(purchaseList.get(i).getsDate());
					pal.setClientName(purchaseList.get(i).getClientName());
					pal.setReason(purchaseList.get(i).getReason());
					pal.setAmount(purchaseList.get(i).getAmount());
					loss.add(pal);
				}
			}

			for (int i = 0; i < loss.size(); i++) {
				dtpu.add(loss.get(i).getsDate());
			}
			Collections.sort(dtpu);
			HashSet<String> dtsetpu = new HashSet<String>();
			for (String item : dtpu) {
				if (!dtsetpu.contains(item)) {
					dtpu1.add(item);
					dtsetpu.add(item);
					dtoutt.add(item);
				}
			}
			for (int i = 0; i < dtpu1.size(); i++) {
				for (int j = 0; j < loss.size(); j++) {
					if (dtpu1.get(i).equals(loss.get(j).getsDate())) {
						ProfitLossMB pal = new ProfitLossMB();
						pal.setsDate(dtpu1.get(i));
						logger.debug("-------dates-------");
						pal.setClientName(loss.get(j).getClientName());
						pal.setReason(loss.get(j).getReason());
						pal.setAmount(loss.get(j).getAmount());
						dummy.add(pal);
					}
				}
			}
			loss.clear();
			BigDecimal losscount=BigDecimal.valueOf(0);
			for (int i = 0; i < dummy.size(); i++) {
				ProfitLossMB pal = new ProfitLossMB();
				pal.setsNo(i + 1);
				pal.setsDate(dummy.get(i).getsDate());
				pal.setClientName(dummy.get(i).getClientName());
				pal.setReason(dummy.get(i).getReason());
				pal.setAmount(dummy.get(i).getAmount());
				loss.add(pal);
				losscount=losscount.add(new BigDecimal(1));
			}
			String amtout = "0";
			for (int i = 0; i < loss.size(); i++) {
				amtout = ""
						+ (new BigDecimal(amtout).add(new BigDecimal(loss
								.get(i).getAmount())));
			}
			lAmount = amtout;
			amtout = "0";

			plAmount = ""
					+ new BigDecimal(pAmount).subtract(new BigDecimal(lAmount));
			if (new BigDecimal(plAmount).compareTo(BigDecimal.valueOf(0)) == 1) {
				color = "green";
			} else {
				color = "red";
			}
			profits=profitcount.toString();
			losses=losscount.toString();
		}

		catch (DemoException ie) {
			flag = "none";
			ie.printStackTrace();
			setValidate(ie.getMessage());
			logger.debug("" + ie.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("" + e.getMessage());
			return "";
		} finally {

		}
		return "";
	}

	public String profitMenuCall() {
		try {
			setValidate("");
			flag1 = "none";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			fDate = null;
			tDate = null;
			flag = "none";
			pAmount = "";
			plAmount = "";
			lAmount = "";
			profit.clear();
			loss.clear();
		}
		return "";
	}

	// prema begin 02/05/2016 dialog box creation for profit and loss

	public void profitandloss() {

		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);

		RequestContext.getCurrentInstance().openDialog(
				"aProfitAndLossStatement", options, null);
		profitMenuCall();
	}

	// prema end 02/05/2016
	public void profitandlossclose() {
		RequestContext.getCurrentInstance().closeDialog(
				"aProfitAndLossStatement");
	}
}
