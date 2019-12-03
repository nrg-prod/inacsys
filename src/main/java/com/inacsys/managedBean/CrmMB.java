package com.inacsys.managedBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.Buyer;
import com.inacsys.shared.CrmCustomerdetail;

@ManagedBean(name = "crmMB")
public class CrmMB {
	private static Logger logger = Logger.getLogger(CrmMB.class);
	SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
	
	/*stanley code for CRM start*/
	
	public List<String> statusList=null;
	public String email2;
	public String crmcomments;
	public String crmwebsite2;
	public String crmfaxnumber2;
	public String crmstatus;
	public String crmcode;
	public Date createddate;
	public Date updateddate;
	public Date followupdate;
	public String crmcusproduct;
	public String crmmodeofcommunications;
	public String crmcreatedperson;
	public String crmupdatedperson;
	public String crmcash;
	public List<String>crmproductlist=null;
	public List<String> crmindustrylist=null;
	public String customerTitle;
	public String customerMiddleName;
	public String customerLastName;
	public String customerSuffix;
	public String custMobile;
	public String other;
	public String company;
	public String website;
	public String faxnumber;
	public String customername;
	public String countryID;
	public Date salesorderdate;
	public String shipingaddress;
	public String phonenumber;
	public String email;
	public String code;
	public String categoryName;
	public String taxnumber;
	public String cusLicence;
	public String cusType;
	public Date cusExdate;
	public String cusCode;
	public boolean venflag=true; 
	public String type;
	public String cash;
	public String newcash;
	public List<String> daylist=null;
	public String permanentaddress;
	public String presentCity;
	public String perPostCode;
	public String prePostCode;
	public String presentstate;
	public List<String> stateList=null;
	public List<String> stateList1=null;
	private boolean sameCheckBox=false;
	public String presentcountryID;
	public String state;
	public String city;
	public String note;
	public String validate;
	public boolean customerflag1 = false;
	public String freelancerName = "";
	public String validate1;
	private boolean hiddenFlag=false;
	private boolean showFlag=true;
	ArrayList<CrmMB>mblist=new ArrayList<CrmMB>(); 
	List<CrmCustomerdetail>crmlist=null;
	List<CrmCustomerdetail>crmlist1=null;
	public int crm_customerDetails_ID;
	
		
	public int getCrm_customerDetails_ID() {
		return crm_customerDetails_ID;
	}

	public void setCrm_customerDetails_ID(int crm_customerDetails_ID) {
		this.crm_customerDetails_ID = crm_customerDetails_ID;
	}

	public List<CrmCustomerdetail> getCrmlist() {
		return crmlist;
	}

	public void setCrmlist(List<CrmCustomerdetail> crmlist) {
		this.crmlist = crmlist;
	}

	public List<CrmCustomerdetail> getCrmlist1() {
		return crmlist1;
	}

	public void setCrmlist1(List<CrmCustomerdetail> crmlist1) {
		this.crmlist1 = crmlist1;
	}

	public ArrayList<CrmMB> getMblist() {
		return mblist;
	}

	public void setMblist(ArrayList<CrmMB> mblist) {
		this.mblist = mblist;
	}

	public boolean isHiddenFlag() {
		return hiddenFlag;
	}

	public void setHiddenFlag(boolean hiddenFlag) {
		this.hiddenFlag = hiddenFlag;
	}

	public boolean isShowFlag() {
		return showFlag;
	}

	public void setShowFlag(boolean showFlag) {
		this.showFlag = showFlag;
	}

	public String getValidate1() {
		return validate1;
	}

	public void setValidate1(String validate1) {
		this.validate1 = validate1;
	}
	public String getFreelancerName() {
		return freelancerName;
	}

	public void setFreelancerName(String freelancerName) {
		this.freelancerName = freelancerName;
	}

	public boolean isCustomerflag1() {
		return customerflag1;
	}

	public void setCustomerflag1(boolean customerflag1) {
		this.customerflag1 = customerflag1;
	}
	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPresentcountryID() {
		return presentcountryID;
	}

	public void setPresentcountryID(String presentcountryID) {
		this.presentcountryID = presentcountryID;
	}

	public boolean isSameCheckBox() {
		return sameCheckBox;
	}

	public void setSameCheckBox(boolean sameCheckBox) {
		this.sameCheckBox = sameCheckBox;
	}

	public String getPermanentaddress() {
		return permanentaddress;
	}

	public void setPermanentaddress(String permanentaddress) {
		this.permanentaddress = permanentaddress;
	}

	public String getPresentCity() {
		return presentCity;
	}

	public void setPresentCity(String presentCity) {
		this.presentCity = presentCity;
	}

	public String getPerPostCode() {
		return perPostCode;
	}

	public void setPerPostCode(String perPostCode) {
		this.perPostCode = perPostCode;
	}

	public String getPrePostCode() {
		return prePostCode;
	}

	public void setPrePostCode(String prePostCode) {
		this.prePostCode = prePostCode;
	}

	public String getPresentstate() {
		return presentstate;
	}

	public void setPresentstate(String presentstate) {
		this.presentstate = presentstate;
	}

	public List<String> getStateList() {
		return stateList;
	}

	public void setStateList(List<String> stateList) {
		this.stateList = stateList;
	}

	public List<String> getStateList1() {
		return stateList1;
	}

	public void setStateList1(List<String> stateList1) {
		this.stateList1 = stateList1;
	}

	public String getCusLicence() {
		return cusLicence;
	}

	public void setCusLicence(String cusLicence) {
		this.cusLicence = cusLicence;
	}

	public String getCusType() {
		return cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public Date getCusExdate() {
		return cusExdate;
	}

	public void setCusExdate(Date cusExdate) {
		this.cusExdate = cusExdate;
	}

	public String getCusCode() {
		return cusCode;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}

	public boolean isVenflag() {
		return venflag;
	}

	public void setVenflag(boolean venflag) {
		this.venflag = venflag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public String getNewcash() {
		return newcash;
	}

	public void setNewcash(String newcash) {
		this.newcash = newcash;
	}

	public List<String> getDaylist() {
		return daylist;
	}

	public void setDaylist(List<String> daylist) {
		this.daylist = daylist;
	}

	public String getTaxnumber() {
		return taxnumber;
	}

	public void setTaxnumber(String taxnumber) {
		this.taxnumber = taxnumber;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCountryID() {
		return countryID;
	}

	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}

	public Date getSalesorderdate() {
		return salesorderdate;
	}

	public void setSalesorderdate(Date salesorderdate) {
		this.salesorderdate = salesorderdate;
	}

	public String getShipingaddress() {
		return shipingaddress;
	}

	public void setShipingaddress(String shipingaddress) {
		this.shipingaddress = shipingaddress;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomerTitle() {
		return customerTitle;
	}

	public void setCustomerTitle(String customerTitle) {
		this.customerTitle = customerTitle;
	}

	public String getCustomerMiddleName() {
		return customerMiddleName;
	}

	public void setCustomerMiddleName(String customerMiddleName) {
		this.customerMiddleName = customerMiddleName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerSuffix() {
		return customerSuffix;
	}

	public void setCustomerSuffix(String customerSuffix) {
		this.customerSuffix = customerSuffix;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFaxnumber() {
		return faxnumber;
	}

	public void setFaxnumber(String faxnumber) {
		this.faxnumber = faxnumber;
	}

	public List<String> getCrmindustrylist() {
		return crmindustrylist;
	}

	public void setCrmindustrylist(List<String> crmindustrylist) {
		this.crmindustrylist = crmindustrylist;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getCrmcomments() {
		return crmcomments;
	}

	public void setCrmcomments(String crmcomments) {
		this.crmcomments = crmcomments;
	}

	public String getCrmwebsite2() {
		return crmwebsite2;
	}

	public void setCrmwebsite2(String crmwebsite2) {
		this.crmwebsite2 = crmwebsite2;
	}

	public String getCrmfaxnumber2() {
		return crmfaxnumber2;
	}

	public void setCrmfaxnumber2(String crmfaxnumber2) {
		this.crmfaxnumber2 = crmfaxnumber2;
	}

	public String getCrmstatus() {
		return crmstatus;
	}

	public void setCrmstatus(String crmstatus) {
		this.crmstatus = crmstatus;
	}

	public String getCrmcode() {
		return crmcode;
	}

	public void setCrmcode(String crmcode) {
		this.crmcode = crmcode;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

	public Date getFollowupdate() {
		return followupdate;
	}

	public void setFollowupdate(Date followupdate) {
		this.followupdate = followupdate;
	}

	public String getCrmcusproduct() {
		return crmcusproduct;
	}

	public void setCrmcusproduct(String crmcusproduct) {
		this.crmcusproduct = crmcusproduct;
	}

	public String getCrmmodeofcommunications() {
		return crmmodeofcommunications;
	}

	public void setCrmmodeofcommunications(String crmmodeofcommunications) {
		this.crmmodeofcommunications = crmmodeofcommunications;
	}

	public String getCrmcreatedperson() {
		return crmcreatedperson;
	}

	public void setCrmcreatedperson(String crmcreatedperson) {
		this.crmcreatedperson = crmcreatedperson;
	}

	public String getCrmupdatedperson() {
		return crmupdatedperson;
	}

	public void setCrmupdatedperson(String crmupdatedperson) {
		this.crmupdatedperson = crmupdatedperson;
	}

	public String getCrmcash() {
		return crmcash;
	}

	public void setCrmcash(String crmcash) {
		this.crmcash = crmcash;
	}

	public List<String> getCrmproductlist() {
		return crmproductlist;
	}

	public void setCrmproductlist(List<String> crmproductlist) {
		this.crmproductlist = crmproductlist;
	}

/* --------------CRM NEW START-------------- */
	
	/* new menu loading */
	public String crmNewNavigation()
	{		
		logger.info("------inside crmNewNavigation-------");
		 try{		
			setCustomerTitle("");
			setCustomername(""); 
			setCustomerMiddleName(""); 
			setCustomerLastName("");
			setCustomerSuffix("");
			setEmail(""); 
			setCode("");
			setPhonenumber(""); 
			setCustMobile("");
			setCategoryName("");
			setOther("");
			setSalesorderdate(null);  
			setCompany("");
			setWebsite("");
			setTaxnumber("");
			setFaxnumber("");
			setCrmfaxnumber2(""); 
			setCusLicence("");
			setCusType("");
			setCusExdate(null);
			setEmail2(""); 
			setCrmcomments(""); 
			setCrmwebsite2(""); 
			setFaxnumber(""); 
			setCrmstatus(""); 
			setCrmcode("");  
			setCreateddate(null); 
			setUpdateddate(null); 
			setFollowupdate(null); 
			setCrmcusproduct(""); 
			setCrmmodeofcommunications(""); 
			setCrmcreatedperson(""); 
			setCrmupdatedperson(""); 
			setShipingaddress(""); 
			setPermanentaddress("");
			setPresentCity("");
			setPresentcountryID("");
			setCity("");
			setCountryID("");
			setPrePostCode("");
			setPresentstate("");
			setPerPostCode("");
			setState("");
			setNote("");
			setSameCheckBox(false);  
			DemoController controller=null;
				try{
					ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
					controller = (DemoController) ctx.getBean("controller");
					crmcode=controller.getcmtcode(); 
					statusList=new ArrayList<String>();
					statusList=controller.getstatusList();
					logger.debug("----status list-----"+statusList); 
					crmindustrylist=controller.getcrmtype();
					logger.debug("----crmindustrylist list-----"+crmindustrylist);
					crmproductlist=controller.getcrmproduct();
					logger.debug("----crmproductlist list-----"+crmproductlist);
				}catch(Exception e){
					logger.warn("Inside Exception", e);
				//e.printStackTrace();
			}
		 }catch(Exception e){
			 logger.warn("Inside Exception", e);
			// e.printStackTrace();
		 }
		return "crmNew";
	}
	
	/* add new button */
	public String addnew()
		{
			try{		
				System.out.println("inside addnew");
				setCrmcash(null); 
				setValidate(null); 
				RequestContext.getCurrentInstance().execute("PF('confirm1').show();");  
				RequestContext.getCurrentInstance().update("center_content:daypanelid1"); 
			}catch(Exception e){
				logger.warn("Inside Exception", e);
				//e.printStackTrace();
			}
			return "";
		}
	
	/* industry save*/
	public String crmIndustrySave()
	{
		System.out.println("inside click");
		DemoController controller=null;
		String status="";
		try{
			System.out.println("inside click for");
			Buyer buyer=new Buyer();
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if(crmcash == null){ 
				System.out.println("inside click if");
				setValidate("Please Enter the Industry Name"); 
			}else{
				System.out.println("inside click else");
				buyer.setCrmcash(crmcash); 
				status=controller.getCrmIndustrySave(buyer); 
				setCategoryName(crmcash); 		 		 
				if(status.equalsIgnoreCase("Exist")){
					setValidate("Already Exist");
				}else if("Success".equalsIgnoreCase(status)){ 
					RequestContext.getCurrentInstance().execute("PF('confirm1').hide();");
					crmindustrylist=controller.getcrmtype();					
					RequestContext.getCurrentInstance().update("center_content");
				}
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e);
			//e.printStackTrace();
		}
		return "";
	}
	
	/* reset button */
	public String crmreset() 
	{
		logger.info("------inside crmreset------");
		customerflag1 = false;
		setCustomername("");
		setSalesorderdate(null);
		setShipingaddress("");
		setState("");
		setCountryID("");
		setCategoryName("");
		setPhonenumber("");
		setEmail("");
		setTaxnumber("");
		setFreelancerName("");
		setNote("");
		setCity("");
		setCustomerTitle("");
		setCustomerMiddleName("");
		setCustomerLastName("");
		setCustomerSuffix("");
		setCode("");
		setCustMobile("");
		setOther("");
		setCompany("");
		setWebsite("");
		setFaxnumber("");
		setPermanentaddress("");
		setPresentCity("");
		setPresentcountryID("");
		setPerPostCode("");
		setPrePostCode("");
		setPresentstate("");
		setCusLicence("");
		setCusExdate(null); 
		setCusCode(""); 
		setCusType(""); 
		setType(""); 
		setCash(""); 
		setNewcash("");
		setEmail2(""); 
		setCrmcomments(""); 
		setCrmwebsite2(""); 
		setCrmfaxnumber2(""); 
		setCrmstatus(""); 
		setCreateddate(null); 
		setUpdateddate(null);
		setFollowupdate(null); 
		setCrmcusproduct(""); 
		setCrmmodeofcommunications(""); 
		setCrmcreatedperson(""); 
		setCrmupdatedperson(""); 
		setSameCheckBox(false);
		setValidate1(""); 
		return "";
	}
	
	/* registration */
	public String crmRegister()
	{
		logger.info("------inside crmRegister------");
		System.out.println("inside crmreg"); 
		Buyer buyer=new Buyer(); 
		DemoController controller=null;
		String status=null;
		try{
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			buyer.setCustomerTitle(customerTitle);
			buyer.setCustomerName(customername); 
			buyer.setCustomerMiddleName(customerMiddleName);
			buyer.setCustomerLastName(customerLastName);
			buyer.setCustomerSuffix(customerSuffix);
			buyer.setMail(email);
			buyer.setCode(code);
			buyer.setPhoneNumber(phonenumber);
			buyer.setCustMobile(custMobile);
			buyer.setCategoryName(categoryName);
			buyer.setOther(other);
			buyer.setDate(salesorderdate);
			buyer.setCompany(company);
			buyer.setWebsite(website);
			buyer.setTaxnumber(taxnumber);
			buyer.setFilePath(faxnumber);
			buyer.setCusLicence(cusLicence);
			buyer.setCusType(cusType);
			buyer.setCusExdate(cusExdate);
			buyer.setEmail2(email2); 
			buyer.setCrmcomments(crmcomments); 
			buyer.setCrmwebsite2(crmwebsite2); 
			buyer.setFaxnumber(crmfaxnumber2); 
			buyer.setCrmstatus(crmstatus); 
			buyer.setCrmcode(crmcode);   
			buyer.setCreateddate(createddate); 
			buyer.setUpdateddate(updateddate); 
			buyer.setFollowupdate(followupdate); 
			buyer.setCrmcusproduct(crmcusproduct); 
			buyer.setCrmmodeofcommunications(crmmodeofcommunications); 
			buyer.setCrmcreatedperson(crmcreatedperson); 
			buyer.setCrmupdatedperson(crmupdatedperson); 
			buyer.setAddress(shipingaddress);
			buyer.setPermanentaddress(permanentaddress);
			buyer.setPresentCity(presentCity);
			buyer.setPresentcountryID(presentcountryID);
			buyer.setCity(city);
			buyer.setCountry(countryID);
			buyer.setPrePostCode(prePostCode);
			buyer.setPresentstate(presentstate);
			buyer.setPerPostCode(perPostCode);
			buyer.setState(state);
			buyer.setNote(note);
			System.out.println("given fax from UI----->"+faxnumber); 
			System.out.println("register fax number------->"+buyer.getFilePath()); 
			status=controller.saveCrm(buyer); 
			logger.debug("crm status-----"+status);
			if(status.equalsIgnoreCase("success"))
			{
				logger.debug("inside crm status success if-----"+status);
				RequestContext.getCurrentInstance().execute("PF('confirm').show();"); 
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e);
			//e.printStackTrace();
		}
		return "";
	}
	
	/* check for validation */
	public String customernamecheck() 
	{
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext context1 = FacesContext.getCurrentInstance();
		DemoController controller = null;
		try {
			Map<String, String> params = context1.getExternalContext().getRequestParameterMap();
			logger.debug("------------------->" + params.get("param2"));
			String name = params.get("param2");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String status = controller.getCustomerVerification(name);
			logger.debug("status" + status);
			if (status.equalsIgnoreCase("Success")) {
				context.execute("checkFail1();");
			} else {
				context.execute("checkSuccess1();");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";

	}
	
	/* present state change according to country */
	public void crmPresentStateChange(ValueChangeEvent vc)
	{
		logger.info("------inside crmPresentStateChange------");
		String country = "";setCode("");
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			country = vc.getNewValue().toString();
		   ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		   controller = (DemoController) ctx.getBean("controller");
		   stateList1=new ArrayList<String>();
		   stateList1=controller.getstatelist(country);
		   logger.debug("-----crmPresentStateChange stateList1-----"+stateList1);
		}catch(Exception e){
			logger.warn("Inside Exception", e);
			//e.printStackTrace();
		}
	}
	
	/* permenant state change according to country */
	public void crmPermenantStateChange(ValueChangeEvent vc)
	{
		logger.info("------inside crmPermenantStateChange------");
		String country = "";
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
		   country = vc.getNewValue().toString();
		   ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		   controller = (DemoController) ctx.getBean("controller");
		   stateList=new ArrayList<String>();
		   stateList=controller.getstatelist(country);
		   logger.debug("-----crmPermenantStateChange statelist-----"+stateList); 
		}catch(Exception e){
			logger.warn("Inside Exception", e);
			//e.printStackTrace();
		}
	}
	
	/* check box change */
	public void checkBoxValueChange(ValueChangeEvent vc)
	{
		logger.info("------inside checkBoxValueChange------"); 
		try{
			String check=vc.getNewValue().toString();
			if(check.equals("true")){
				logger.info("------inside checkBoxValueChange if------");
				setHiddenFlag(true);
				setShowFlag(false);
				setPermanentaddress(shipingaddress);
				setCity(presentCity);
				setCountryID(presentcountryID);
				setPerPostCode(prePostCode);
				setState(presentstate);
			}else{
				logger.info("------inside checkBoxValueChange else------");
				setHiddenFlag(false);
				setShowFlag(true);
				setPermanentaddress("");
				setCity("");
				setCountryID("");
				setPerPostCode("");
				setState("");
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e);
			//e.printStackTrace();
		}
	}
/* --------------CRM NEW END-------------- */
/* --------------CRM ENQUIRY START-------- */
	public String crmEnquiryNavigation(){
		String query="";
		DemoController controller=null;
		ApplicationContext ctx = null;	
		try{
			query="crmenquiry";
			mblist.clear();
			setCustomername(""); 
			setPhonenumber("");
			setEmail(""); 
			setPresentCity("");			
			setValidate1(""); 
			try{
				ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				crmlist=controller.getcrmenquiry(query);				
				System.out.println("crmlist------------>"+crmlist.size()); 
				if(crmlist.size() > 0){
					for (int i = 0; i < crmlist.size(); i++) {
						CrmMB crmMB=new CrmMB();
						crmMB.setCustomername(crmlist.get(i).getPersonName());  
						crmMB.setCategoryName(crmlist.get(i).getCustomerProduct()); 
						crmMB.setCreateddate(crmlist.get(i).getDate());
						crmMB.setCrmstatus(crmlist.get(i).getPresentAddress()); 
						crmMB.setPresentCity(crmlist.get(i).getMobileNo_1()); 
						crmMB.setPresentcountryID(crmlist.get(i).getEmail1()); 
						crmMB.setCrm_customerDetails_ID(crmlist.get(i).getCrm_customerDetails_ID()); 
						mblist.add(crmMB);
						
					}		
					System.out.println("list---->"+mblist.size()); 
				}
					
			}catch(Exception e){
				logger.warn("Inside Exception", e);
				//e.printStackTrace();
			}
				
		}catch(Exception e){
			logger.warn("Inside Exception", e);
			//e.printStackTrace();
		}
		return "crmenquiry";
	}
/* --------------CRM ENQUIRY START-------- */
/* --------------CRM REACHES START-------- */
	public String crmReachesNavigation(){
		String query="";
		DemoController controller=null;
		ApplicationContext ctx = null;
		try{
			query="crmreaches";
			mblist.clear();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			crmlist=controller.getcrmenquiry(query);	
			System.out.println("crmlist------------>"+crmlist.size()); 
			if(crmlist.size() > 0){
				for (int i = 0; i < crmlist.size(); i++) {
					CrmMB crmMB=new CrmMB();
					crmMB.setCustomername(crmlist.get(i).getPersonName()); 
					crmMB.setCategoryName(crmlist.get(i).getCustomerProduct()); 
					crmMB.setCreateddate(crmlist.get(i).getDate());
					crmMB.setCrmstatus(crmlist.get(i).getPresentAddress()); 
					crmMB.setPresentCity(crmlist.get(i).getMobileNo_1()); 
					crmMB.setPresentcountryID(crmlist.get(i).getEmail1());  
					crmMB.setCrm_customerDetails_ID(crmlist.get(i).getCrm_customerDetails_ID());
					mblist.add(crmMB);
				}	
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e);
		}
		return "crmreaches";
	}
/* --------------CRM REACHES END-------- */
/* --------------CRM LEADS START-------- */
	public String crmLeadsNavigation(){
		String query="";
		DemoController controller=null;
		ApplicationContext ctx = null;
		try{
			query="crmleads";
			mblist.clear();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			crmlist=controller.getcrmenquiry(query);	
			System.out.println("crmlist------------>"+crmlist.size()); 
			if(crmlist.size() > 0){
				for (int i = 0; i < crmlist.size(); i++) {
					CrmMB crmMB=new CrmMB();
					crmMB.setCustomername(crmlist.get(i).getPersonName()); 
					crmMB.setCategoryName(crmlist.get(i).getCustomerProduct()); 
					crmMB.setCreateddate(crmlist.get(i).getDate());
					crmMB.setCrmstatus(crmlist.get(i).getPresentAddress()); 
					crmMB.setPresentCity(crmlist.get(i).getMobileNo_1()); 
					crmMB.setPresentcountryID(crmlist.get(i).getEmail1()); 
					crmMB.setCrm_customerDetails_ID(crmlist.get(i).getCrm_customerDetails_ID());
					mblist.add(crmMB);
				}	
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e);
		}
		return "crmleads";
	}
/* --------------CRM LEADS END-------- */
/* --------------CRM IMP-LEADS START-------- */
	public String crmImpLeadsNavigation(){
		String query="";
		DemoController controller=null;
		ApplicationContext ctx = null;
		try{
			query="crmimpleads";
			mblist.clear();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			crmlist=controller.getcrmenquiry(query);	
			System.out.println("crmlist------------>"+crmlist.size()); 
			if(crmlist.size() > 0){
				for (int i = 0; i < crmlist.size(); i++) {
					CrmMB crmMB=new CrmMB();
					crmMB.setCustomername(crmlist.get(i).getPersonName()); 
					crmMB.setCategoryName(crmlist.get(i).getCustomerProduct()); 
					crmMB.setCreateddate(crmlist.get(i).getDate());
					crmMB.setCrmstatus(crmlist.get(i).getPresentAddress()); 
					crmMB.setPresentCity(crmlist.get(i).getMobileNo_1()); 
					crmMB.setPresentcountryID(crmlist.get(i).getEmail1()); 
					crmMB.setCrm_customerDetails_ID(crmlist.get(i).getCrm_customerDetails_ID());
					mblist.add(crmMB);
				}	
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e);
		}
		return "crmimpleads";
	}
/* --------------CRM IMP-LEADS END-------- */
/* --------------CRM CLOSURE START-------- */
	public String crmClosureNavigation(){
		String query="";
		DemoController controller=null;
		ApplicationContext ctx = null;
		try{
			query="crmclosure";
			mblist.clear();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			crmlist=controller.getcrmenquiry(query);	
			System.out.println("crmlist------------>"+crmlist.size()); 
			if(crmlist.size() > 0){
				for (int i = 0; i < crmlist.size(); i++) {
					CrmMB crmMB=new CrmMB();
					crmMB.setCustomername(crmlist.get(i).getPersonName()); 
					crmMB.setCategoryName(crmlist.get(i).getCustomerProduct()); 
					crmMB.setCreateddate(crmlist.get(i).getDate());
					crmMB.setCrmstatus(crmlist.get(i).getPresentAddress()); 
					crmMB.setPresentCity(crmlist.get(i).getMobileNo_1()); 
					crmMB.setPresentcountryID(crmlist.get(i).getEmail1()); 
					crmMB.setCrm_customerDetails_ID(crmlist.get(i).getCrm_customerDetails_ID());
					mblist.add(crmMB);
				}	
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e);
		}
		return "crmclosure";
	}
/* --------------CRM CLOSURE END-------- */
/* --------------CRM NEGOSATION START-------- */
	public String crmNegosationNavigation(){
		String query="";
		DemoController controller=null;
		ApplicationContext ctx = null;
		try{
			query="crmnegosation";
			mblist.clear();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			crmlist=controller.getcrmenquiry(query);	
			System.out.println("crmlist------------>"+crmlist.size()); 
			if(crmlist.size() > 0){
				for (int i = 0; i < crmlist.size(); i++) {
					CrmMB crmMB=new CrmMB();
					crmMB.setCustomername(crmlist.get(i).getPersonName()); 
					crmMB.setCategoryName(crmlist.get(i).getCustomerProduct()); 
					crmMB.setCreateddate(crmlist.get(i).getDate());
					crmMB.setCrmstatus(crmlist.get(i).getPresentAddress()); 
					crmMB.setPresentCity(crmlist.get(i).getMobileNo_1()); 
					crmMB.setPresentcountryID(crmlist.get(i).getEmail1()); 
					crmMB.setCrm_customerDetails_ID(crmlist.get(i).getCrm_customerDetails_ID());
					mblist.add(crmMB);
				}	
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e);
		}
		return "crmnegosation";
	}
/* --------------CRM NEGOSATION END-------- */
/* --------------CRM ONBOARD START-------- */
	public String crmOnboardNavigation(){
		String query="";
		DemoController controller=null;
		ApplicationContext ctx = null;
		try{
			query="crmonboard";
			mblist.clear();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			crmlist=controller.getcrmenquiry(query);	
			System.out.println("crmlist------------>"+crmlist.size()); 
			if(crmlist.size() > 0){
				for (int i = 0; i < crmlist.size(); i++) {
					CrmMB crmMB=new CrmMB();
					crmMB.setCustomername(crmlist.get(i).getPersonName()); 
					crmMB.setCategoryName(crmlist.get(i).getCustomerProduct()); 
					crmMB.setCreateddate(crmlist.get(i).getDate());
					crmMB.setCrmstatus(crmlist.get(i).getPresentAddress()); 
					crmMB.setPresentCity(crmlist.get(i).getMobileNo_1()); 
					crmMB.setPresentcountryID(crmlist.get(i).getEmail1()); 
					crmMB.setCrm_customerDetails_ID(crmlist.get(i).getCrm_customerDetails_ID());
					mblist.add(crmMB);
				}	
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e);
		}
		return "crmonboard";
	}
/* --------------CRM ONBOARD END-------- */
/* --------------CRM COMPLETE START-------- */
	public String crmCompletedNavigation(){
		String query="";
		DemoController controller=null;
		ApplicationContext ctx = null;
		try{
			query="crmcompleted";
			mblist.clear();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			crmlist=controller.getcrmenquiry(query);	
			System.out.println("crmlist------------>"+crmlist.size()); 
			if(crmlist.size() > 0){
				for (int i = 0; i < crmlist.size(); i++) {
					CrmMB crmMB=new CrmMB();
					crmMB.setCustomername(crmlist.get(i).getPersonName()); 
					crmMB.setCategoryName(crmlist.get(i).getCustomerProduct()); 
					crmMB.setCreateddate(crmlist.get(i).getDate());
					crmMB.setCrmstatus(crmlist.get(i).getPresentAddress()); 
					crmMB.setPresentCity(crmlist.get(i).getMobileNo_1()); 
					crmMB.setPresentcountryID(crmlist.get(i).getEmail1()); 
					crmMB.setCrm_customerDetails_ID(crmlist.get(i).getCrm_customerDetails_ID());
					mblist.add(crmMB);
				}	
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e);
		}
		return "crmcompleted";
	}
/* --------------CRM COMPLETE END-------- */
/* --------------CRM REJECTED START-------- */
	public String crmRejectedNavigation(){
		String query="";
		DemoController controller=null;
		ApplicationContext ctx = null;
		try{
			query="crmrejected";
			mblist.clear();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			crmlist=controller.getcrmenquiry(query);	
			System.out.println("crmlist------------>"+crmlist.size()); 
			if(crmlist.size() > 0){
				for (int i = 0; i < crmlist.size(); i++) {
					CrmMB crmMB=new CrmMB();
					crmMB.setCustomername(crmlist.get(i).getPersonName()); 
					crmMB.setCategoryName(crmlist.get(i).getCustomerProduct()); 
					crmMB.setCreateddate(crmlist.get(i).getDate());
					crmMB.setCrmstatus(crmlist.get(i).getPresentAddress()); 
					crmMB.setPresentCity(crmlist.get(i).getMobileNo_1()); 
					crmMB.setPresentcountryID(crmlist.get(i).getEmail1()); 
					crmMB.setCrm_customerDetails_ID(crmlist.get(i).getCrm_customerDetails_ID());
					mblist.add(crmMB);
				}	
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e);
		}
		return "crmrejected";
	}
/* --------------CRM REJECTED END-------- */		
/*stanley code for CRM end*/	

}
