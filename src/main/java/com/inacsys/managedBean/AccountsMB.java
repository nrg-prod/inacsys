package com.inacsys.managedBean;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.ATransaction;
import com.inacsys.domain.AccountsDatabean;
import com.inacsys.util.AccountsJDBC;
import com.inacsys.util.CommonValidate;
import com.inacsys.util.CurrencyConverter;
import com.inacsys.util.Dates;
import com.inacsys.util.Util;

@ManagedBean(name="accountsMB")
public class AccountsMB {
	
	AccountsDatabean accountsDatabean=new AccountsDatabean();
	List<AccountsDatabean> accounts=null;
	ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
	DemoController controller = (DemoController) ctx.getBean("controller");
	Date date=new Date();
	private static Logger logger = Logger.getLogger(AccountsMB.class);
	
	ArrayList<AccountsDatabean> filterList1;
	List<AccountsDatabean> coadetailList=new ArrayList<AccountsDatabean>();
	public List<AccountsDatabean> journalEntryList=null;
	private List<AccountsDatabean> glLists=null;

	public List<AccountsDatabean> getGlLists() {
		return glLists;
	}

	public void setGlLists(List<AccountsDatabean> glLists) {
		this.glLists = glLists;
	}
    
    public List<AccountsDatabean> getJournalEntryList() {
		return journalEntryList;
	}

	public void setJournalEntryList(List<AccountsDatabean> journalEntryList) {
		this.journalEntryList = journalEntryList;
	}

	public List<AccountsDatabean> getCoadetailList() {
		return coadetailList;
	}

	public void setCoadetailList(List<AccountsDatabean> coadetailList) {
		this.coadetailList = coadetailList;
	}

	public ArrayList<AccountsDatabean> getFilterList1() {
		return filterList1;
	}

	public void setFilterList1(ArrayList<AccountsDatabean> filterList1) {
		this.filterList1 = filterList1;
	}
	
	public List<AccountsDatabean> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountsDatabean> accounts) {
		this.accounts = accounts;
	}

	public AccountsDatabean getAccountsDatabean() {
		return accountsDatabean;
	}

	public void setAccountsDatabean(AccountsDatabean accountsDatabean) {
		this.accountsDatabean = accountsDatabean;
	}
	
	public String generalLedgerPage(){
		logger.info("[generalLedgerPage()]-----------------------------------Inside generalLedgerPage() in MB Calling-------------------------------------------");
		try{
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			accountsDatabean.setBaseCurrency(baseCurrency);
			accountsDatabean.setAccounts(new ArrayList<AccountsDatabean>());
			accountsDatabean.setAccountType(new ArrayList<String>());accountsDatabean.setAccountsDetail(new ArrayList<AccountsDatabean>());
			accountsDatabean.setReportPeriod("All dates");accountsDatabean.setAccountReceivableList(new ArrayList<AccountsDatabean>());
			controller.getGeneralLedger(clientID,accountsDatabean);	
			HashSet<String> hashList=new HashSet<String>(accountsDatabean.getAccountType());
			accountsDatabean.getAccountType().clear();
			accountsDatabean.getAccountType().addAll(hashList);
			glLists = new ArrayList<AccountsDatabean>();
			for (int i = 0; i < accountsDatabean.getAccountType().size(); i++) {
				BigDecimal tempamt=BigDecimal.valueOf(0);
				AccountsDatabean domain = new AccountsDatabean(accountsDatabean.getAccountType().get(i));
				for (int j = 0; j <  accountsDatabean.getAccounts().size(); j++) {
					if(accountsDatabean.getAccountType().get(i).equals(accountsDatabean.getAccounts().get(j).getAccount_name())){
						if(accountsDatabean.getAccountType().get(i).equals("Accounts Receivable (A/R)")
							||  accountsDatabean.getAccountType().get(i).equals("Accounts Payable (A/P)")){
							tempamt=new BigDecimal(accountsDatabean.getAccounts().get(j).getBalance());
						}else{
							tempamt=tempamt.add(new BigDecimal(accountsDatabean.getAccounts().get(j).getBalance()));
						}
						domain.getGeneralLedgerList().add(new ATransaction(accountsDatabean.getAccounts().get(j).getTransactionType(),
								accountsDatabean.getAccounts().get(j).getAccount_date(),accountsDatabean.getAccounts().get(j).getRefNo(),
								accountsDatabean.getAccounts().get(j).getPayeeName(),accountsDatabean.getAccounts().get(j).getAccount_description(),
								accountsDatabean.getAccounts().get(j).getSubAccount(),accountsDatabean.getAccounts().get(j).getAccount_amount(),
								String.valueOf(tempamt)));
					}
				}
				glLists.add(domain);
			}
			clear1();accountsDatabean.setFlag1(true);
		}catch (Exception e) {
			e.printStackTrace();
			logger.warn("------------------------------Inside Exception-------------------------------------"+e.getMessage());
		}
		return "acGeneraLedger";
	}
	
	public void reportPeriodChange(ValueChangeEvent v){		
		logger.info("[reportPeriodChange()]--------------------Inside reportPeriodChange() in MB Calling----------------------");
		accountsDatabean.setFlag3(false);
		try{
			if(v.getNewValue().toString()!=null){
				String reportPeriod=v.getNewValue().toString();
				if(reportPeriod.equals("All dates")){
					accountsDatabean.setCustomflag(false);
					accountsDatabean.setOtherflag(false);
					accountsDatabean.setFlag(false);
				}else if(reportPeriod.equals("Custom")){
					accountsDatabean.setFromDate(null);
					accountsDatabean.setToDate(null);
					accountsDatabean.setCustomflag(true);
					accountsDatabean.setOtherflag(false);
					accountsDatabean.setFlag(true);
				}else if(reportPeriod.equals("all")){
				      accountsDatabean.setCustomflag(false);
				      accountsDatabean.setOtherflag(false);
				      accountsDatabean.setFlag(false);
				}else if(!reportPeriod.equals("All dates") || !reportPeriod.equals("Custom") || !reportPeriod.equals("all")){
					accountsDatabean.setCustomflag(false);
					accountsDatabean.setOtherflag(true);
					accountsDatabean.setFlag(true);
					if(reportPeriod.equals("Today") || reportPeriod.equals("Recent")){
						accountsDatabean.setFromDate(date);
						accountsDatabean.setToDate(date);
					}else{				
						Dates.getDates(reportPeriod,accountsDatabean);							
					}
				}
			}
			accountsDatabean.setFlag1(false);accountsDatabean.setFlag2(false);
		}catch(Exception e){
			logger.warn("------------------------------Inside Exception-------------------------------------"+e.getMessage());
		}		
	}
	
	public void runReportGL(){
		logger.info("[runReportGL()]--------------------Inside runReportGL() in MB Calling----------------------");
		try{
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			accountsDatabean.setBaseCurrency(baseCurrency);
			accountsDatabean.setAccountsDetail(new ArrayList<AccountsDatabean>());
			if(validate(true)){
				accountsDatabean.setAccounts(new ArrayList<AccountsDatabean>());
				accountsDatabean.setAccounts(new ArrayList<AccountsDatabean>());
				accountsDatabean.setAccountType(new ArrayList<String>());
				accountsDatabean.setAccountReceivableList(new ArrayList<AccountsDatabean>());
				controller.getGeneralLedger(clientID,accountsDatabean);	
				HashSet<String> hashList=new HashSet<String>(accountsDatabean.getAccountType());
				accountsDatabean.getAccountType().clear();
				accountsDatabean.getAccountType().addAll(hashList);
				glLists = new ArrayList<AccountsDatabean>();
				for (int i = 0; i < accountsDatabean.getAccountType().size(); i++) {
					BigDecimal tempamt=BigDecimal.valueOf(0);
					AccountsDatabean domain = new AccountsDatabean(accountsDatabean.getAccountType().get(i));
					for (int j = 0; j <  accountsDatabean.getAccounts().size(); j++) {
						if(accountsDatabean.getAccountType().get(i).equals(accountsDatabean.getAccounts().get(j).getAccount_name())){
							if(accountsDatabean.getAccountType().get(i).equals("Accounts Receivable (A/R)")
								||  accountsDatabean.getAccountType().get(i).equals("Accounts Payable (A/P)")){
								tempamt=new BigDecimal(accountsDatabean.getAccounts().get(j).getBalance());
							}else{
								tempamt=tempamt.add(new BigDecimal(accountsDatabean.getAccounts().get(j).getBalance()));
							}
							domain.getGeneralLedgerList().add(new ATransaction(accountsDatabean.getAccounts().get(j).getTransactionType(),
									accountsDatabean.getAccounts().get(j).getAccount_date(),accountsDatabean.getAccounts().get(j).getRefNo(),
									accountsDatabean.getAccounts().get(j).getPayeeName(),accountsDatabean.getAccounts().get(j).getAccount_description(),
									accountsDatabean.getAccounts().get(j).getSubAccount(),accountsDatabean.getAccounts().get(j).getAccount_amount(),
									String.valueOf(tempamt)));
						}
					}
					glLists.add(domain);
				}
				setFlag();
			}		
		}catch(Exception e){
			logger.warn("------------------------------Inside Exception-------------------------------------"+e.getMessage());
		}
		
	}
	
	public void viewCOAAccounts(){
		String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
		controller.getviewCOAAccounts(clientID,accountsDatabean);
	}
	/*john 06-28-2017*/
	public String trialBalancePage() throws SQLException{
		logger.info("[trialBalancePage()]--------------------Inside trialBalancePage() in MB Calling----------------------");
		accountsDatabean.setTotalassets(0.0);accountsDatabean.setTotalliablity(0.0);
		try{
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			accountsDatabean.setBaseCurrency(baseCurrency);
			accountsDatabean.setClientID(clientID);
			accountsDatabean.setTrialBalance(new ArrayList<AccountsDatabean>());
			accountsDatabean.setAccounts(new ArrayList<AccountsDatabean>());
			accountsDatabean.setReportPeriod("All dates");
			controller.getTrialBalance(clientID,accountsDatabean);
			clear1();accountsDatabean.setFlag1(true);
		}catch(Exception e){
			logger.warn("-----------------------Inside Exception--------------------------------"+e.getMessage());
		}
		return "acTrialBalance";
	}
	
	public void runReportTB(){
		logger.info("[runReportTB()]-------------------------Inside runReportTB() in MB Calling----------------------------");
		DateFormat dateFormat=null;
		try {
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			accountsDatabean.setBaseCurrency(baseCurrency);
			accountsDatabean.setClientID(clientID);
			accountsDatabean.setTrialBalance(new ArrayList<AccountsDatabean>());
			accountsDatabean.setTotalassets(0.0);accountsDatabean.setTotalliablity(0.0);
			if(validate(true)){
			     dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
			     accountsDatabean.setAccount_description(dateFormat.format(accountsDatabean.getToDate()));
				 controller.getTrialBalance(clientID,accountsDatabean);	
				 setFlag();
			}
		}catch (Exception e) {
			logger.warn("-----------------------Inside Exception--------------------------------"+e.getMessage());
		}
	}
	
	public String balanceSheetPage(){
		logger.info("[balanceSheetPage()]-------------------------Inside balanceSheetPage() in MB Calling----------------------------");
		try{
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
		   accountsDatabean.setBaseCurrency(baseCurrency);
		   accountsDatabean.setAccounts(new ArrayList<AccountsDatabean>());
		   accountsDatabean.setReportPeriod("All dates");
		   accountsDatabean.setClientID(clientID);
		   controller.AccountsBalanceCal(accountsDatabean);
		   clear1();accountsDatabean.setFlag1(true);
		   System.out.println("accounts size()--"+ accountsDatabean.getAccounts().size());
		   System.out.println("total assets --"+accountsDatabean.getTotalassets());
	  }catch(Exception e){
		  e.printStackTrace();
		  logger.warn("-----------------------Inside Exception--------------------------------"+e.getMessage());
	  }
	  return "acBalanceSheet";
	}
		  
	public void clear1(){
		logger.info("[clear1()]-------------------------Inside clear1() in MB Calling----------------------------");
		accountsDatabean.setDebit(BigDecimal.ZERO);
		accountsDatabean.setProfit("");
		accountsDatabean.setCustomflag(false);
		accountsDatabean.setOtherflag(false);accountsDatabean.setFlag3(false);
		accountsDatabean.setFlag(false);
		accountsDatabean.setFlag1(false);accountsDatabean.setFlag2(false);
	}
	
	public void runReportBS(){
		logger.info("[runReportBS()]-------------------------Inside runReportBS() in MB Calling----------------------------");
		DateFormat dateFormat=null;
		try {
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			accountsDatabean.setBaseCurrency(baseCurrency);
			if(validate(true)){
				accountsDatabean.setAccounts(new ArrayList<AccountsDatabean>());
				if(!accountsDatabean.getReportPeriod().equals("All dates")){
					dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
				    accountsDatabean.setAccount_description(dateFormat.format(accountsDatabean.getToDate()));
				}
			    accountsDatabean.setClientID(clientID);
				controller.AccountsBalanceCal(accountsDatabean);
				setFlag();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.warn("----------------------Inside Exception----------------------------------"+e.getMessage());
		}
	}
	
	
	
	private boolean flag=false;
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String profitLossPage(){
		logger.info("[profitLossPage()]------------------------------Inside profitLossPage() in MB Calling---------------------------------");
		accountsDatabean.setToDate(null);accountsDatabean.setReportPeriod("");accountsDatabean.setFromDate(null);
		try{
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			accountsDatabean.setBaseCurrency(baseCurrency);
			accountsDatabean.setClientID(clientID);
			setFlag(false);
			accountsDatabean.setReportPeriod("This month");
			Calendar calendar = new GregorianCalendar();
		    calendar.set(Calendar.HOUR_OF_DAY, 0);
		    calendar.set(Calendar.MINUTE, 0);
		    calendar.set(Calendar.SECOND, 0);
		    calendar.set(Calendar.MILLISECOND, 0);
		    calendar.set(Calendar.DAY_OF_MONTH, 1);
		    accountsDatabean.setFromDate(calendar.getTime());
		    calendar.add(Calendar.MONTH, 1);
		    calendar.add(Calendar.DATE, -1);
		    accountsDatabean.setToDate(calendar.getTime());	 
		    accountsDatabean.setAccountStatus("PL");
			controller.getProfitLoss(accountsDatabean);
			clear();
			accountsDatabean.setFlag3(true);
		}catch(Exception e){
			logger.warn("---------------------Inside Exception-------------------------"+e.getMessage());
		}
		return "acProfitLoss";
	}
	
	public void runReportPL(){
		logger.info("[runReportPL()]------------------------------Inside runReportPL() in MB Calling---------------------------------");
		try{
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			accountsDatabean.setBaseCurrency(baseCurrency);
			accountsDatabean.setIncomeList(new ArrayList<AccountsDatabean>());
			accountsDatabean.setOtherIncomeList(new ArrayList<AccountsDatabean>());accountsDatabean.setCogList(new ArrayList<AccountsDatabean>());
			accountsDatabean.setExpensesList(new ArrayList<AccountsDatabean>());accountsDatabean.setOtherExpensesList(new ArrayList<AccountsDatabean>());
			accountsDatabean.setTotalIncome("0");accountsDatabean.setTotalOtherIncome("0");
			accountsDatabean.setCogTotal(BigDecimal.ZERO);accountsDatabean.setTotalExpenses("0");
			accountsDatabean.setTotalOtherExpenses("0");
			accountsDatabean.setCrossProfit("0");
			accountsDatabean.setProfitAmount(BigDecimal.ZERO);
			if(validate(true)){
				accountsDatabean.setClientID(clientID);
				setFlag(true);
				accounts=new ArrayList<AccountsDatabean>();
				accountsDatabean.setAccountStatus("PL");
				controller.getProfitLoss(accountsDatabean);
				setFlag();
			}
		}catch(Exception e){
			logger.warn("---------------------Inside Exception-------------------------"+e.getMessage());
		}
	}
	
	public String goToPL(){
		System.out.println("PL");
		return "acProfitLoss";
	}
	
	public void clear(){
		/*accountsDatabean.setMonthLoss(BigDecimal.ZERO);*/
		accountsDatabean.setDebit(BigDecimal.ZERO);
		accountsDatabean.setProfit("");
		accountsDatabean.setCustomflag(true);
		accountsDatabean.setOtherflag(false);accountsDatabean.setFlag3(false);
		accountsDatabean.setFlag(true);
		accountsDatabean.setFlag1(false);accountsDatabean.setFlag2(false);
	}

	private boolean validate(boolean valid) {
		valid=true;
		String name="";
		FacesContext fc=FacesContext.getCurrentInstance();
		try{
			if(accountsDatabean.getReportPeriod().equals("all")){
				name = CommonValidate.findComponentInRoot("reperiod").getClientId(fc);
				fc.addMessage(name, new FacesMessage("Please Select Report Period"));
				valid=false;
			}else if(accountsDatabean.getReportPeriod().equals("Custom")){
				if(accountsDatabean.getFromDate()==null){
					name = CommonValidate.findComponentInRoot("fdate").getClientId(fc);
					fc.addMessage(name, new FacesMessage("Please Select From Date"));
					valid=false;
				}if(accountsDatabean.getToDate()==null){
					name = CommonValidate.findComponentInRoot("tdate").getClientId(fc);
					fc.addMessage(name, new FacesMessage("Please Select To Date"));
					valid=false;
				}
			}
			System.out.println("valid---"+valid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return valid;
	}
	
	public void setFlag(){
		accountsDatabean.setFlag3(false);
		if(accountsDatabean.getReportPeriod().equals("all")){
			accountsDatabean.setFlag1(false);accountsDatabean.setFlag2(false);accountsDatabean.setCustomflag(false);accountsDatabean.setOtherflag(false);accountsDatabean.setFlag(false);
		}else if(accountsDatabean.getReportPeriod().equals("All dates")){
			accountsDatabean.setFlag1(true);accountsDatabean.setFlag2(false);
			accountsDatabean.setCustomflag(false);accountsDatabean.setOtherflag(false);accountsDatabean.setFlag(false);
		}else{
			accountsDatabean.setFlag1(false);accountsDatabean.setFlag2(true);
		}
	}
	
	private LineChartModel lineModel1;
    private LineChartModel lineModel2;
     
    public LineChartModel getLineModel1() {
        return lineModel1;
    }
 
    public LineChartModel getLineModel2() {
        return lineModel2;
    }
     
    public void charts(){
		createLineModels();		
		setFlag(true);
    }
    
    private void createLineModels() {
    	System.out.println("inside line chart");
    	try{
            lineModel1 = initLinearModel();
            lineModel1.setTitle("Profit and Loss");
            lineModel1.setLegendPosition("e");
            Axis yAxis = lineModel1.getAxis(AxisType.Y);
            lineModel1.getAxes().put(AxisType.X, new CategoryAxis(""));
            yAxis.setMin(-5000000);
            yAxis.setMax(5000000);
            lineModel1.setShowPointLabels(false);
            lineModel1.setLegendPosition("");
            lineModel1.setMouseoverHighlight(false);           
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    private LineChartModel initLinearModel() {
    	String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
    	accountsDatabean.setClientID(clientID);
        LineChartModel model = new LineChartModel();
        LineChartSeries series = new LineChartSeries();    
       /* Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -3);
        series.set(c.getDisplayName((Calendar.MONTH), Calendar.LONG, Locale.ENGLISH ), 0);
        */Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.MONTH, -2);
        series.set(c1.getDisplayName((Calendar.MONTH), Calendar.LONG, Locale.ENGLISH ), 0);
        Calendar c2 = Calendar.getInstance();
        Calendar calendar = new GregorianCalendar();
	    calendar.add(Calendar.MONTH, -1);
	    calendar.set(Calendar.DATE, 1);
	    accountsDatabean.setFromDate(calendar.getTime());
	    calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	    accountsDatabean.setToDate(calendar.getTime());
        c2.add(Calendar.MONTH ,-1);
        System.out.println("from  "+accountsDatabean.getFromDate()+" to "+accountsDatabean.getToDate());
        controller.getProfitLoss(accountsDatabean);
        try{
        	series.set(c2.getDisplayName((Calendar.MONTH), Calendar.LONG, Locale.ENGLISH ), Integer.parseInt(accountsDatabean.getProfit()));
        }catch(Exception e){
        	series.set(c2.getDisplayName((Calendar.MONTH), Calendar.LONG, Locale.ENGLISH ), Float.parseFloat(accountsDatabean.getProfit()));
        }
        	
        System.out.println("profit "+accountsDatabean.getProfit());
        Calendar c3 = Calendar.getInstance();
	    Calendar calendar1 = new GregorianCalendar();
	    calendar1.add(Calendar.MONTH,0);
	    calendar1.set(Calendar.DATE, 1);
	    accountsDatabean.setFromDate(calendar1.getTime());
	    calendar1.set(Calendar.DATE, calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
	    accountsDatabean.setToDate(calendar1.getTime());
	    System.out.println("from 1 "+accountsDatabean.getFromDate()+" to "+accountsDatabean.getToDate());
	    controller.getProfitLoss(accountsDatabean);
	    try{
	    	series.set(c3.getDisplayName((Calendar.MONTH), Calendar.LONG, Locale.ENGLISH ), Integer.parseInt(accountsDatabean.getProfit()));
	    }catch(Exception e){
	    	series.set(c3.getDisplayName((Calendar.MONTH), Calendar.LONG, Locale.ENGLISH ), Float.parseFloat(accountsDatabean.getProfit()));
	    }        
        System.out.println("profit1 "+accountsDatabean.getProfit());
        Calendar c4 = Calendar.getInstance();
        c4.add(Calendar.MONTH, 1);
        series.set(c4.getDisplayName((Calendar.MONTH), Calendar.LONG, Locale.ENGLISH ), 0);
        model.addSeries(series);           
        return model;
    }
    
    /*NEELA*/
    
    public String makeActive(){
    	try{
    		String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
    		System.out.println("account name --- "+accountsDatabean.getAccount_name());
    		accountsDatabean.setStatus("Active");
    		String status=controller.editAccount(clientID,accountsDatabean);
    		if(status.equalsIgnoreCase("Success")){
    			RequestContext.getCurrentInstance().execute("PF('activedialog').show();");
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
    
    /* Chart Of Account */
    
    public String chartofAccountsPage(){
    	logger.info("[chartofAccountsPage()]------------Inside chartofAccountsPage() in Managed Bean Calling----------------");
		try{
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			String clientCountry=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clientCountry").toString();
			System.out.println("country"+clientCountry+clientID);
			accountsDatabean.setCountry(clientCountry);
			setFilterList1(null);
			accountsDatabean.setReportPeriod("all");
			accounts=new ArrayList<AccountsDatabean>();
			controller.getCOAdetails(clientID,accountsDatabean);	
			setAccounts(accountsDatabean.getAccounts());
			accountsDatabean.setBaseCurrency(baseCurrency);
		}catch(Exception e){
			logger.warn("--------------Inside Exception--------------"+e.getMessage());
		}
		return "acChartofAccounts";
	}
	
	public void addAccounts(){
		logger.info("[addAccounts()]------------Inside addAccounts() in Managed Bean Calling----------------");
		try{
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			String clientCountry=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clientCountry").toString();
			accountsDatabean.setCountry(clientCountry);accountsDatabean.setDetailType("");
			accountsDatabean.setCurrency("");accountsDatabean.setTaxes("");accountsDatabean.setCurrencyAmount("");accountsDatabean.setAccount_type("");
			accountsDatabean.setBaseCurrency(baseCurrency);accountsDatabean.setTaxes("");
			accountsDatabean.setStatus("Category Type");accountsDatabean.setDate(null);
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			DemoController controller = (DemoController) ctx.getBean("controller");
			controller.getAccountTypes(clientID,accountsDatabean);	
			System.out.println("acount type size"+accountsDatabean.getAccountType().size());
			if(accountsDatabean.getAccountType().contains("Current liabilities")){
				accountsDatabean.getAccountType().remove("Current liabilities");
			}
			RequestContext.getCurrentInstance().execute("PF('addAccount').show()");
		}catch(Exception e){
			logger.warn("--------------Inside Exception--------------"+e.getMessage());
		}
	}
	
	public void accountChange(){
		logger.info("[accountChange()]------------Inside accountChange() in Managed Bean Calling----------------");
		try{
			accountsDatabean.setDetailType("");
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			if (Arrays.asList("Accounts Receivable (A/R)", "Accounts Payable (A/P)", "Income" ,"Other Income","Cost of Goods Sold","Expenses","Other Expenses").contains(accountsDatabean.getAccount_type())) {
				setFlag(false);accountsDatabean.setBalance("0");
			} else {
				accountsDatabean.setBalance("0");setFlag(true);
			}
			accountsDatabean.setStatus("Detail Type");
			controller.getAccountTypes(clientID,accountsDatabean);	
			RequestContext.getCurrentInstance().execute("PF('addAccount').show()");
		}catch(Exception e){
			logger.warn("--------------Inside Exception--------------"+e.getMessage());
		}
	}
	
	public void typeChange(ValueChangeEvent v){
		logger.info("[typeChange()]------------Inside typeChange() in Managed Bean Calling----------------");
		accountsDatabean.setAccount_name(v.getNewValue().toString());
	}
	
	public void amountChange(ValueChangeEvent v){
		logger.info("[amountChange()]------------Inside amountChange() in Managed Bean Calling----------------");
		accountsDatabean.setBalance(v.getNewValue().toString());
		if(v.getNewValue().toString().equalsIgnoreCase("")){
			accountsDatabean.setCurrencyAmount("0");
		}else if(v.getNewValue().toString().equalsIgnoreCase("0")){
			accountsDatabean.setCurrencyAmount("0");
		}else{
			if(accountsDatabean.getBaseCurrency().equalsIgnoreCase(accountsDatabean.getCurrency())){
				accountsDatabean.setCurrencyAmount(accountsDatabean.getBalance());
			}else{
				BigDecimal currAmount=CurrencyConverter.findExchangeRateAndConvert(accountsDatabean.getCurrency(),accountsDatabean.getBaseCurrency(),accountsDatabean.getBalance());
				accountsDatabean.setCurrencyAmount(String.valueOf(currAmount));
			}
		}
	}
	
	public void cancel(){
		logger.info("[cancel()]------------Inside cancel() in Managed Bean Calling----------------");
		try{
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			accountsDatabean.setStatus("COA add");
			controller.getAccountTypes(clientID,accountsDatabean);	
			accountsDatabean.setAccount_name("");accountsDatabean.setAccount_type("");accountsDatabean.setValidate("");
			accountsDatabean.setDetailType("");accountsDatabean.setBalance("0");accountsDatabean.setTypes(new ArrayList<String>());
		}catch(Exception e){
			logger.warn("--------------Inside Exception--------------"+e.getMessage());
		}
	}

	public void saveAccount(){
		logger.info("[saveAccount()]------------Inside saveAccount() in Managed Bean Calling----------------");
		String fieldName;FacesContext fc=FacesContext.getCurrentInstance();
		int c=0;
		try{
			if(accountsDatabean.getAccount_type()==null || accountsDatabean.getAccount_type()==""){		
				RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
				fieldName = CommonValidate.findComponentInRoot("type").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(Util.getMessage("AccountType","messages")));
				c++;
			}
			if(accountsDatabean.getDate()==null){		
				RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
				fieldName = CommonValidate.findComponentInRoot("date").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(Util.getMessage("Date","messages")));
				c++;
			}
			if(accountsDatabean.getDetailType()==null || accountsDatabean.getDetailType()==""){					
				RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
				fieldName = CommonValidate.findComponentInRoot("type1").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(Util.getMessage("DetailType","messages")));
				c++;
			}
			if(accountsDatabean.getAccount_name()==null || accountsDatabean.getAccount_name()==""){					
				RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
				fieldName = CommonValidate.findComponentInRoot("detName").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(Util.getMessage("AccountName","messages")));
				c++;
			}
			if(accountsDatabean.getCurrency()==null || accountsDatabean.getCurrency()==""){					
				RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
				fieldName = CommonValidate.findComponentInRoot("curr").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(Util.getMessage("Currency","messages")));
				c++;
			}
			if(accountsDatabean.getBalance()==null || accountsDatabean.getBalance()==""){					
				RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
				fieldName = CommonValidate.findComponentInRoot("bal").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(Util.getMessage("Balance","messages")));
				c++;
			}else if(accountsDatabean.getBalance()!=null || accountsDatabean.getBalance()!=""){	
				if(!CommonValidate.validateNumber(accountsDatabean.getBalance())){
					RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
					fieldName = CommonValidate.findComponentInRoot("bal").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage(Util.getMessage("BalanceInNumbers","messages"))); 
					c++;
				}
			}
			if(c==0){
				String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
				String statuss=controller.saveAcountDeposit(clientID,accountsDatabean);
				if(statuss.equals("Exist")){
					RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
					throw new Exception(Util.getMessage("ExistAccountName","messages"));
				}else if(statuss.equals("OpenbalequityExist")){
					RequestContext.getCurrentInstance().execute("PF('addAccount').show();");
					throw new Exception(Util.getMessage("ExistOpenBalEquityAccountName","messages"));
				}else{
					RequestContext.getCurrentInstance().execute("PF('addAccount').hide();");
					RequestContext.getCurrentInstance().execute("PF('confirm').show();");
					accountsDatabean.setAccount_name1(accountsDatabean.getAccount_name());
					cancel();
				}
			}
		}catch(Exception e){
			logger.warn("--------------Inside Exception--------------"+e.getMessage());
			accountsDatabean.setValidate(e.getMessage());
		}finally{
			c=0;
		}
	}
	
	public String chartofAccountsDetailPage(){
    	logger.info("[chartofAccountsDetailPage()]-----------------Inside chartofAccountsDetailPage() in MB Calling");
    	try{
    		String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
    		accountsDatabean.setClientID(clientID);
    		coadetailList=controller.getcoaDetailsList(accountsDatabean);
    	}catch(Exception e){
    		logger.warn("------------Inside Exception---------------"+e.getMessage());
    	}
    	return "acChartofAccountsDetail";
    }
	
	public String makeInactive(){
		logger.info("[makeInactive()]-----------------Inside makeInactive() in MB Calling");
    	try{
    		String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
    		List<String> lists=Arrays.asList("Accounts Receivable (A/R)","Accounts Payable (A/P)","Services","Opening Balance Equity","Purchases","Output CGST",
    				"Output SGST","Output IGST","Input CGST","Input SGST","Input IGST");
    		if(lists.contains(accountsDatabean.getAccount_name())){
    			RequestContext.getCurrentInstance().execute("PF('deactivedialog').show();");
    		}else{
    			accountsDatabean.setStatus("Deactive");
        		String status=controller.editAccount(clientID,accountsDatabean);
        		if(status.equalsIgnoreCase("Success")){
        			RequestContext.getCurrentInstance().execute("PF('deactivedialog1').show();");
        		}
    		}
    	}catch(Exception e){
    		logger.warn("------------Inside Exception---------------"+e.getMessage());
    	}
		return null;
    }
	
	// Journal Entry Code Begin By Prema

	public String journalEntryPage(){
		logger.info("[journalEntryPage()]--------------------Inside journalEntryPage() in MB Calling-----------------------");
		ApplicationContext ctx = null;DemoController controller = null;
		try{
			accountsDatabean.setCreditParticularList(new ArrayList<String>());accountsDatabean.setDebitParticularList(new ArrayList<String>());
			accountsDatabean.setDebitAmountList(new ArrayList<String>());accountsDatabean.setCreditAmountList(new ArrayList<String>());
			String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
			String baseCurrency=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency").toString();
			accountsDatabean.setClientID(clientID);accountsDatabean.setBaseCurrency(baseCurrency);
			accountsDatabean.setReportPeriod("This month");
			Calendar calendar = new GregorianCalendar();
		    calendar.set(Calendar.HOUR_OF_DAY, 0);
		    calendar.set(Calendar.MINUTE, 0);
		    calendar.set(Calendar.SECOND, 0);
		    calendar.set(Calendar.MILLISECOND, 0);
		    calendar.set(Calendar.DAY_OF_MONTH, 1);
		    accountsDatabean.setFromDate(calendar.getTime());
		    calendar.add(Calendar.MONTH, 1);
		    calendar.add(Calendar.DATE, -1);
		    accountsDatabean.setToDate(calendar.getTime());	   
			journalEntryList=new ArrayList<AccountsDatabean>();
			ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller=(DemoController) ctx.getBean("controller");
			journalEntryList=controller.getjournalEntryList(accountsDatabean);
			accountsDatabean.setOtherflag(true);accountsDatabean.setFlag1(false);accountsDatabean.setFlag2(false);
			accountsDatabean.setFlag(true);accountsDatabean.setCustomflag(false);accountsDatabean.setFlag3(true);
			accountsDatabean.setAccountType(new ArrayList<String>());
			List<String> list=Arrays.asList("Account Receiveable","Services","Output IGST");
			accountsDatabean.getAccountType().addAll(list);
		}catch(Exception e){
			logger.warn("-------------------------Inside Exception-----------------------"+e.getMessage());
		}finally{
			ctx = null;controller = null;
		}
		return "journalEntryPage";
	}
	
	public String runJournalEntry(){
		logger.info("[runJournalEntry()]--------------------Inside runJournalEntry() in MB Calling-----------------------");
		ApplicationContext ctx = null;DemoController controller = null;
		try{
			journalEntryList=new ArrayList<AccountsDatabean>();
			if(validate(true)){
				setFlag(true);
				ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller=(DemoController) ctx.getBean("controller");
				String clientID=FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID").toString();
				accountsDatabean.setClientID(clientID);
				journalEntryList=controller.getjournalEntryList(accountsDatabean);
				setFlag();
			}
		}catch(Exception e){
			logger.warn("-------------------------Inside Exception-----------------------"+e.getMessage());
		}finally{
			ctx = null;controller = null;
		}
		return "journalEntryPage";
	}
		
	// Journal Entry Code End By Prema
	
	public void currencyTypeValueChange(ValueChangeEvent vc){
		logger.info("[currencyTypeValueChange()]--------------------Inside currencyTypeValueChange() in MB Calling-----------------------");
		try{
			if(vc.getNewValue().toString().equalsIgnoreCase("") || accountsDatabean.getBalance().equals("0") || accountsDatabean.getBalance().equals("0.0")){
				accountsDatabean.setCurrencyAmount("0");
			}else{
				if(accountsDatabean.getBaseCurrency().equalsIgnoreCase(vc.getNewValue().toString())){
					accountsDatabean.setCurrencyAmount(accountsDatabean.getBalance());
				}else{
					BigDecimal currAmount=CurrencyConverter.findExchangeRateAndConvert(vc.getNewValue().toString(),accountsDatabean.getBaseCurrency(),accountsDatabean.getBalance());
					accountsDatabean.setCurrencyAmount(String.valueOf(currAmount));
				}
			}
		}catch(Exception e){
			logger.warn("-------------------------Inside Exception-----------------------"+e.getMessage());
		}
	}
   
}
