package com.inacsys.managedBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.ATransaction;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.Transaction;

@ManagedBean(name = "aTransactionMB")
public class ATransactionaccountMB {
	private static Logger logger = Logger
			.getLogger(ATransactionaccountMB.class);
	public Date date;
	public String date1;
	public String paymentMode;
	public String transactionType;
	public String particular;
	public String amount;
	public String note;
	public String validate;
	public String cardno;
	public String bankname;
	public String chequeno;
	public Date chequedate;
	public String chequedate1;
	public String flag1 = "none";
	public String flag2 = "none";
	public String transactionNo;
	public Date fromdate;
	public Date todate;
	public List<Transaction> view = null;
	public List<ATransaction> viewlist = null;
	public String status;
	public String flag = "none";
	public String align1 = "none";
	public String align2 = "none";
	public String align3 = "none";
	SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy");
	public String flagpop = "none";

	public String getFlagpop() {
		return flagpop;
	}

	public void setFlagpop(String flagpop) {
		this.flagpop = flagpop;
	}

	public String getAlign3() {
		return align3;
	}

	public void setAlign3(String align3) {
		this.align3 = align3;
	}

	public String getAlign1() {
		return align1;
	}

	public void setAlign1(String align1) {
		this.align1 = align1;
	}

	public String getAlign2() {
		return align2;
	}

	public void setAlign2(String align2) {
		this.align2 = align2;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ATransaction> getViewlist() {
		return viewlist;
	}

	public void setViewlist(List<ATransaction> viewlist) {
		this.viewlist = viewlist;
	}

	public List<Transaction> getView() {
		return view;
	}

	public void setView(List<Transaction> view) {
		this.view = view;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getChequeno() {
		return chequeno;
	}

	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
	}

	public Date getChequedate() {
		return chequedate;
	}

	public void setChequedate(Date chequedate) {
		this.chequedate = chequedate;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getParticular() {
		return particular;
	}

	public void setParticular(String particular) {
		this.particular = particular;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getChequedate1() {
		return chequedate1;
	}

	public void setChequedate1(String chequedate1) {
		this.chequedate1 = chequedate1;
	}

	public String transactionView() {
		logger.info("[transactionView()] --------------- Inside transactionView() method() ------------------------");
		validate = "";
		setValidate("");
		flag = "none";
		fromdate = null;
		todate = null;
		flagpop = "none";
		return "transactionview";

	}

	public String cancel() {
		logger.info("[cancel()] --------------- Inside cancel() method() ------------------------");
		flag = "none";
		flagpop = "none";
		return "transactionHome";

	}

	public String search() {
		logger.info("[search()] --------------- Inside search() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		ATransaction search = null;
		try {
			setValidate("");
			search = new ATransaction();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if (fromdate == null) {
				throw new DemoException("Enter From Date");
			} else if (todate == null) {
				throw new DemoException("Enter To Date");
			}

			search.setFromdate(fromdate);
			logger.debug("[search()] --------------- Inside search() method() formdate ------------------------>"+fromdate);
			search.setTodate(todate);
			logger.debug("[search()] --------------- Inside search() method() todate ------------------------>"+todate);
			setView(search.getView());
			controller.transactionView(search);
			if (search.view.size() > 0) {
				flag = "1";
				flagpop = "none";
				setView(search.getView());
				logger.debug("[search()] --------------- Inside if values----->amount,particular---------->"+view.get(0).getAmount()+"-->"+view.get(0).getParticulars()+"-->"+view.get(0).getTransactionType()+"-->"+view.get(0).getTransactionNo());
			} else {
				setView(search.getView());
				flagpop = "1";
				flag = "none";
				logger.debug("[search()] --------------- Inside else values----->amount,particular---------->"+view.get(0).getAmount()+"-->"+view.get(0).getParticulars()+"-->"+view.get(0).getTransactionType()+"-->"+view.get(0).getTransactionNo());
			}
		} catch (DemoException ie) {
			flag = "none";
			setValidate(ie.getMessage());
			logger.debug("" + ie.getMessage());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			controller =null;
		}
		return "";
	}

	public String submit() {
		logger.info("[submit()] --------------- Inside submit() method() ------------------------");
		try {
			setValidate("");
			if (date == null) {
				throw new DemoException("*Enter Date");
			} else if (paymentMode.equals("")) {
				throw new DemoException("*Enter PaymentMode");
			}
			if (paymentMode.equals("Cash")) {
				logger.info("[submit()] --------------- Inside submit() method() if condition cash------------------------");
				align1 = "1";
				align2 = "none";
				align3 = "none";
			}

			if (paymentMode.equals("credit card")) {
				logger.info("[submit()] --------------- Inside submit() method() if condition credit card------------------------");
				align1 = "1";
				align2 = "none";
				align3 = "none";

			}
			if (paymentMode.equals("Card")) {
				logger.info("[submit()] --------------- Inside submit() method() if condition card------------------------");
				if (bankname.equals("")) {
					throw new DemoException("*Enter Bankname");
				} else if (cardno.equals("")) {
					throw new DemoException("*Enter Card Number");
				}
				align1 = "1";
				align2 = "none";
				align3 = "1";
			}
			if (paymentMode.equals("Cheque")) {
				logger.info("[submit()] --------------- Inside submit() method() if condition cheque------------------------");
				align2 = "1";
				align1 = "none";
				align3 = "none";
				if (bankname.equals("")) {
					throw new DemoException("*Enter Bankname");
				} else if (chequeno.equals("")) {
					throw new DemoException("*Enter Cheque Number");
				} else if (chequedate.equals("")) {
					throw new DemoException("*Enter Cheque Date");
				}
			}
			if (paymentMode.equals("Transfer")) {
				logger.info("[submit()] --------------- Inside submit() method() if condition transfer------------------------");
				align2 = "1";
				align1 = "none";
				align3 = "none";
				if (bankname.equals("")) {
					throw new DemoException("*Enter Bankname");
				} else if (chequeno.equals("")) {
					throw new DemoException("*Enter Cheque Number");
				} else if (chequedate.equals("")) {
					throw new DemoException("*Enter Cheque Date");
				}
			}
			if (transactionType.equals("")) {
				throw new DemoException("*Enter Transaction Type");
			}
			if (particular.equals("")) {
				throw new DemoException("*Enter Name");
			}
			if (amount.equalsIgnoreCase("")) {
				throw new DemoException("*Enter Amount");
			}
			date1 = sdf.format(date);
			if (chequedate != null) {
				chequedate1 = sdf.format(chequedate);
			}
			return "submit";
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.debug(e.getMessage());
			return "failure";
		} finally {
			
		}
	}

	public String confirm() {
		logger.info("[confirm()] --------------- Inside confirm() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		ATransaction save = new ATransaction();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			save.setAmount(amount);
			logger.debug("[confirm()] --------------- Inside confirm() method() amount ------------------------>"+amount);
			save.setDate(date);
			logger.debug("[confirm()] --------------- Inside confirm() method() date ------------------------>"+date);
			save.setNote(note);
			logger.debug("[confirm()] --------------- Inside confirm() method() note ------------------------>"+note);
			save.setParticular(particular);
			logger.debug("[confirm()] --------------- Inside confirm() method() particular ------------------------>"+particular);
			save.setPaymentMode(paymentMode);
			logger.debug("[confirm()] --------------- Inside confirm() method() paymentMode ------------------------>"+paymentMode);
			save.setTransactionType(transactionType);
			logger.debug("[confirm()] --------------- Inside confirm() method() transactionType ------------------------>"+transactionType);
			save.setBankname(bankname);
			logger.debug("[confirm()] --------------- Inside confirm() method() bankname ------------------------>"+bankname);
			save.setCardno(cardno);
			logger.debug("[confirm()] --------------- Inside confirm() method() cardno ------------------------>"+cardno);
			save.setChequedate(chequedate);
			logger.debug("[confirm()] --------------- Inside confirm() method() chequedate ------------------------>"+chequedate);
			save.setChequeno(chequeno);
			logger.debug("[confirm()] --------------- Inside confirm() method() chequeno ------------------------>"+chequeno);
			save.setTransactionNo(transactionNo);
			logger.debug("[confirm()] --------------- Inside confirm() method() transactionNo ------------------------>"+transactionNo);
			save.setAmount(amount);
			controller.saveconfirm(save);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			amount = "";
			date = null;
			paymentMode = null;
			bankname = null;
			cardno = null;
			chequedate = null;
			chequeno = null;
			particular = null;
			note = null;
			transactionType = null;
		}
		return "save";
	}

	public void paymentmodechange(ValueChangeEvent vv) {
		logger.info("[paymentmodechange()] --------------- Inside paymentmodechange() method() ------------------------");
		if (vv.getNewValue().equals("Card")) {
			logger.info("[paymentmodechange()] --------------- Inside paymentmodechange() method() if condition card------------------------");
			flag1 = "1";
		} else {
			flag1 = "none";
		}
		if (vv.getNewValue().equals("Cheque")) {
			logger.info("[paymentmodechange()] --------------- Inside paymentmodechange() method() if condition cheque------------------------");
			flag2 = "1";
		} else {
			flag2 = "none";
		}
	}

	public String viewForm() {
		logger.info("[viewForm()] --------------- Inside viewForm() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		ATransaction view = new ATransaction();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			view.setTransactionNo(transactionNo);
			logger.debug("[viewForm()] --------------- transaction no ------------------------>"+transactionNo);
			setViewlist(controller.getviewForm(view));
			setAmount(viewlist.get(0).getAmount());
			setBankname(viewlist.get(0).getBankname());
			setCardno(viewlist.get(0).getCardno());
			setChequedate(viewlist.get(0).getChequedate());
			setChequeno(viewlist.get(0).getChequeno());
			setDate(viewlist.get(0).getDate());
			setNote(viewlist.get(0).getNote());
			setParticular(viewlist.get(0).getParticular());
			setPaymentMode(viewlist.get(0).getPaymentMode());
			setAmount(viewlist.get(0).getAmount());
			setCardno(viewlist.get(0).getCardno());
			setBankname(viewlist.get(0).getBankname());
			logger.debug("[viewForm()] --------------- pay mode ------------------------>"+viewlist.get(0).getPaymentMode());
			if (paymentMode.equals("Card")) {
				logger.info("[viewForm()] --------------- Inside viewForm() method() if condition card------------------------");
				align1 = "1";
				align2 = "none";
				align3 = "1";
			} else if (paymentMode.equals("Cash")) {
				logger.info("[viewForm()] --------------- Inside viewForm() method() if condition cash------------------------");
				align1 = "1";
				align2 = "none";
				align3 = "none";
			} else if (paymentMode.equals("Cheque")) {
				logger.info("[viewForm()] --------------- Inside viewForm() method() if condition cheque------------------------");
				align1 = "none";
				align2 = "1";
				align3 = "none";
			} else if (paymentMode.equals("Transfer")) {
				logger.info("[viewForm()] --------------- Inside viewForm() method() if condition transfer------------------------");
				align1 = "none";
				align2 = "1";
				align3 = "none";
			}
			setTransactionNo(viewlist.get(0).getTransactionNo());
			logger.debug("[viewForm()] --------------- transaction number------------------------>"+viewlist.get(0).getTransactionNo());
			setTransactionType(viewlist.get(0).getTransactionType());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			controller =null;
		}
		return "aTransactionViewForm";
	}

	public String returnback() {
		logger.info("[returnback()] --------------- Inside returnback() method() ------------------------");
		try {
			search();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			
		}
		return "aTransactionView";
	}

	public String editForm() {
		logger.info("[editForm()] --------------- Inside editForm() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		ATransaction edit = new ATransaction();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			edit.setTransactionNo(transactionNo);
			logger.debug("[editForm()] --------------- transaction no ------------------------>"+transactionNo);
			edit.setStatus("register");
			setViewlist(controller.getEditForm(edit));
			setAmount(viewlist.get(0).getAmount());
			logger.debug("[editForm()] --------------- amount ------------------------>"+viewlist.get(0).getAmount());
			setBankname(viewlist.get(0).getBankname());
			setCardno(viewlist.get(0).getCardno());
			setChequedate(viewlist.get(0).getChequedate());
			setChequeno(viewlist.get(0).getChequeno());
			setDate(viewlist.get(0).getDate());
			setNote(viewlist.get(0).getNote());
			setParticular(viewlist.get(0).getParticular());
			setPaymentMode(viewlist.get(0).getPaymentMode());
			setTransactionNo(viewlist.get(0).getTransactionNo());
			setTransactionType(viewlist.get(0).getTransactionType());
			logger.debug("[editForm()] --------------- transaction number ------------------------>"+viewlist.get(0).getTransactionNo());
			setTransactionType(viewlist.get(0).getTransactionType());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			controller =null;
		}
		return "aTransactionEditForm";
	}

	public String confirmedit() {
		logger.info("[confirmedit()] --------------- Inside confirmedit() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		ATransaction edit = new ATransaction();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			edit.setTransactionNo(transactionNo);
			logger.debug("[confirmedit()] --------------- transaction no ------------------------>"+transactionNo);
			edit.setStatus("update");
			edit.setAmount(amount);
			logger.debug("[confirmedit()] --------------- amount ------------------------>"+amount);
			edit.setDate(date);
			logger.debug("[confirmedit()] --------------- date ------------------------>"+date);
			edit.setNote(note);
			logger.debug("[confirmedit()] --------------- note ------------------------>"+note);
			edit.setParticular(particular);
			logger.debug("[confirmedit()] --------------- particular ------------------------>"+particular);
			edit.setPaymentMode(paymentMode);
			logger.debug("[confirmedit()] --------------- paymentMode ------------------------>"+paymentMode);
			edit.setTransactionType(transactionType);
			logger.debug("[confirmedit()] --------------- transactionType ------------------------>"+transactionType);
			edit.setBankname(bankname);
			logger.debug("[confirmedit()] --------------- bankname ------------------------>"+bankname);
			edit.setCardno(cardno);
			logger.debug("[confirmedit()] --------------- cardno ------------------------>"+cardno);
			edit.setChequedate(chequedate);
			logger.debug("[confirmedit()] --------------- chequedate ------------------------>"+chequedate);
			edit.setChequeno(chequeno);
			logger.debug("[confirmedit()] --------------- chequeno ------------------------>"+chequeno);
			edit.setTransactionNo(transactionNo);
			logger.debug("[confirmedit()] --------------- transactionNo ------------------------>"+transactionNo);
			setViewlist(controller.getEditForm(edit));
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

			setAmount("");
			setDate(null);
			setPaymentMode(null);
			setBankname(null);
			setCardno(null);
			setChequedate(null);
			setChequeno(null);
			setParticular(null);
			setNote(null);
			setTransactionType(null);
		}
		return "edit";
	}

	public String delete() {
		logger.info("[delete()] --------------- Inside delete() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		ATransaction edit = new ATransaction();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			edit.setTransactionNo(transactionNo);
			logger.debug("[confirmedit()] --------------- transactionNo ------------------------>"+transactionNo);
			edit.setStatus("delete");
			setViewlist(controller.getEditForm(edit));
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			view = null;
		}
		return "delete";
	}

	public String chequeCredit() {
		logger.info("[chequeCredit()] --------------- Inside chequeCredit() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		ATransaction change = new ATransaction();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			change.setTransactionNo(transactionNo);
			logger.debug("[confirmedit()] --------------- transactionNo ------------------------>"+transactionNo);
			change.setStatus("credited");
			setViewlist(controller.getStatusChange(change));
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			view = null;
		}
		return "";
	}

	public String ok() {
		logger.info("[ok()] --------------- Inside ok() method() ------------------------");
		setAmount("");
		setDate(null);
		setPaymentMode(null);
		setBankname(null);
		setCardno(null);
		setChequedate(null);
		setChequeno(null);
		setParticular(null);
		setNote(null);
		setTransactionType(null);
		return "home";
	}

	public String deleteback() {
		logger.info("[deleteback()] --------------- Inside deleteback() method() ------------------------");
		flag = "none";
		setFromdate(null);
		setTodate(null);
		return "deleteback";
	}

	public String transationClear() {
		logger.info("[transationClear()] --------------- Inside transationClear() method() ------------------------");
		validate = "";
		date = null;
		paymentMode = null;
		transactionType = null;
		particular = null;
		amount = "";
		note = null;
		bankname = null;
		cardno = null;
		chequedate = null;
		chequeno = null;
		return "transactionclear";
	}

}