package com.inacsys.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.swing.event.ChangeEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.domain.Vendor;
import com.inacsys.domain.VendorUpdate;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0025;
import com.inacsys.shared.I0028;
import com.inacsys.util.AccountsJDBC;
import com.inacsys.util.CommonValidate;
import com.inacsys.util.MailSendJDBC;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "vendorRegisterFormMB")
@RequestScoped
public class VendorRegisterFormMB implements Serializable {
	private static Logger logger = Logger.getLogger(VendorRegisterFormMB.class);
	@ManagedProperty(value = "#{purchaseOrderFromMB}")
	PurchaseOrderFromMB purchaseOrderFromMB;

	public PurchaseOrderFromMB getPurchaseOrderFromMB() {
		return purchaseOrderFromMB;
	}

	public void setPurchaseOrderFromMB(PurchaseOrderFromMB purchaseOrderFromMB) {
		this.purchaseOrderFromMB = purchaseOrderFromMB;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String firmName;
	public String firmRegistrationNumber;
	public String address;
	public String vendorTelephoneNumber;
	public String vendorPhoneNumber;
	public String country_ID;
	public String state;
	public String city;
	public String email_ID_vendor;
	public String faxVendor;
	public String peresonIncharge;
	public String nature_of_business_id;
	public String firmTypeStandard;
	public String frim_ID;
	public String otherfirmtype;
	public String natureofbusiness;
	private boolean vendorFlag = false;
	private boolean vendorSuccessFlag = false;
	
		/* Stanley */
	    public String venCompany;
		public String venCity;
		public String venCountry;
		public String venPostalcode;
		public String venState;
		public String venAddress1;
		public String venCity1;
		public String venCountry1;
		public String venPostalcode1;
		public String venState1;
		public List<String> stateList=null;
		public List<String> stateList1=null;
		private boolean venBox=false; 
		public String code;
		private boolean hiddenFlag=false;
		private boolean showFlag=true;

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

		public boolean isVenBox() {
			return venBox;
		}

		public void setVenBox(boolean venBox) {
			this.venBox = venBox;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
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

	public String getOtherfirmtype() {
		return otherfirmtype;
	}

	public String getVenCompany() {
		return venCompany;
	}

	public void setVenCompany(String venCompany) {
		this.venCompany = venCompany;
	}

	public String getVenCity() {
		return venCity;
	}

	public void setVenCity(String venCity) {
		this.venCity = venCity;
	}

	public String getVenCountry() {
		return venCountry;
	}

	public void setVenCountry(String venCountry) {
		this.venCountry = venCountry;
	}

	public String getVenPostalcode() {
		return venPostalcode;
	}

	public void setVenPostalcode(String venPostalcode) {
		this.venPostalcode = venPostalcode;
	}

	public String getVenState() {
		return venState;
	}

	public void setVenState(String venState) {
		this.venState = venState;
	}

	public String getVenAddress1() {
		return venAddress1;
	}

	public void setVenAddress1(String venAddress1) {
		this.venAddress1 = venAddress1;
	}

	public String getVenCity1() {
		return venCity1;
	}

	public void setVenCity1(String venCity1) {
		this.venCity1 = venCity1;
	}

	public String getVenCountry1() {
		return venCountry1;
	}

	public void setVenCountry1(String venCountry1) {
		this.venCountry1 = venCountry1;
	}

	public String getVenPostalcode1() {
		return venPostalcode1;
	}

	public void setVenPostalcode1(String venPostalcode1) {
		this.venPostalcode1 = venPostalcode1;
	}

	public String getVenState1() {
		return venState1;
	}

	public void setVenState1(String venState1) {
		this.venState1 = venState1;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public void setOtherfirmtype(String otherfirmtype) {
		this.otherfirmtype = otherfirmtype;
	}

	public String getNatureofbusiness() {
		return natureofbusiness;
	}

	public void setNatureofbusiness(String natureofbusiness) {
		this.natureofbusiness = natureofbusiness;
	}

	public List<I0025> I0025;
	public boolean update = false;

	public List<I0025> getI0025() {
		return I0025;
	}

	public void setI0025(List<I0025> i0025) {
		I0025 = i0025;
	}

	public String getFrim_ID() {
		return frim_ID;
	}

	public void setFrim_ID(String frim_ID) {
		this.frim_ID = frim_ID;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getFirmRegistrationNumber() {
		return firmRegistrationNumber;
	}

	public void setFirmRegistrationNumber(String firmRegistrationNumber) {
		this.firmRegistrationNumber = firmRegistrationNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVendorPhoneNumber() {
		return vendorPhoneNumber;
	}

	public void setVendorPhoneNumber(String vendorPhoneNumber) {
		this.vendorPhoneNumber = vendorPhoneNumber;
	}

	public String getCountry_ID() {
		return country_ID;
	}

	public void setCountry_ID(String country_ID) {
		this.country_ID = country_ID;
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

	public String getNature_of_business_id() {
		return nature_of_business_id;
	}

	public String getPeresonIncharge() {
		return peresonIncharge;
	}

	public void setPeresonIncharge(String peresonIncharge) {
		this.peresonIncharge = peresonIncharge;
	}

	public void setNature_of_business_id(String nature_of_business_id) {
		this.nature_of_business_id = nature_of_business_id;
	}

	public String getFirmTypeStandard() {
		return firmTypeStandard;
	}

	public void setFirmTypeStandard(String firmTypeStandard) {
		this.firmTypeStandard = firmTypeStandard;
	}

	public String getVendorTelephoneNumber() {
		return vendorTelephoneNumber;
	}

	public void setVendorTelephoneNumber(String vendorTelephoneNumber) {
		this.vendorTelephoneNumber = vendorTelephoneNumber;
	}

	public String getEmail_ID_vendor() {
		return email_ID_vendor;
	}

	public void setEmail_ID_vendor(String email_ID_vendor) {
		this.email_ID_vendor = email_ID_vendor;
	}

	public String getFaxVendor() {
		return faxVendor;
	}

	public void setFaxVendor(String faxVendor) {
		this.faxVendor = faxVendor;
	}

	public String validate;

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	Vendor vendor = new Vendor();

	public String test() {
		DemoController controller = null;
		ApplicationContext ctx = FacesContextUtils
				.getWebApplicationContext(FacesContext.getCurrentInstance());
		controller = (DemoController) ctx.getBean("controller");
		System.out.println("name---"+vendor.getName());
		return "success";
	}

	public String website;
	public String note;

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	

	public ArrayList<I0028> countrydrop1;

	public ArrayList<I0028> getCountrydrop1() {
		return countrydrop1;
	}

	public void setCountrydrop1(ArrayList<I0028> countrydrop1) {
		this.countrydrop1 = countrydrop1;
	}

	public String countryDrop() {
		logger.info("[countryDrop()]--------------------Inside countryDrop() method() -------------------------");
		DemoController controller = null;
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.countryDrop(vendor);
			setCountrydrop1(vendor.getCountrydrop1());
			logger.debug("[countryDrop()]-------------------Outside vendorRegister() method() --------------");
			return "countryDropsuccess";
		}

		catch (DemoException ie) {
			logger.error("Inside Exception"+ie.getMessage());
			setValidate(ie.getMessage());

			return "countryDropfailure";
		} finally {

		}
	}

	

	private boolean valid = true;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/*
	 * This method is used for validation for vendor Registeration
	 */

	private boolean validate5(boolean flag) {
		boolean valid = true;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		if (vendorPhoneNumber.equalsIgnoreCase("")) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("vfname")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Enter the FirmName."));
			}
			valid = false;
		}
		if (firmRegistrationNumber.equalsIgnoreCase("")) {
			if (flag) {

				fieldName = CommonValidate.findComponentInRoot("vTaxNo")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Enter the Tax Number."));
			}
			valid = false;
		} else if (!firmRegistrationNumber.equalsIgnoreCase("")) {
			if (!firmRegistrationNumber.matches("[0-9]+")) {
				if (flag) {
					fieldName = CommonValidate.findComponentInRoot("vTaxNo")
							.getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage(
							"Please Enter the valid Tax Number."));
				}
				valid = false;
			}
		}
		if (city.equalsIgnoreCase("")) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("vcity")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Enter the City."));
			}
			valid = false;

		}
		return valid;
	}

	

	public static String isEmailValid(String email) {

		// Initialize reg ex for email.
		try {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			CharSequence inputStr = email;
			// Make the comparison case-insensitive.
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inputStr);
			if (matcher.matches()) {
				return "Match";
			} else {
				return "NotMatch";
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "NotMatch";
		} finally {

		}

	}

	public static String isTelePhoneNumberValid(String phoneNumber) {
		// Initialize reg ex for phone number.
		try {
			String expression = "^[0-9]{6,10}$";
			CharSequence inputStr = phoneNumber;
			Pattern pattern = Pattern.compile(expression);
			Matcher matcher = pattern.matcher(inputStr);
			if (matcher.matches()) {
				return "Match";
			} else {
				return "NotMatch";
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "NotMatch";
		} finally {

		}
	}

	public static String isPhoneNumberValid(String phoneNumber) {
		// Initialize reg ex for phone number.
		try {
			String expression = "^((\\+|00)(\\d{1,3}))?(\\d{5,10})$";
			CharSequence inputStr = phoneNumber;
			Pattern pattern = Pattern.compile(expression);
			Matcher matcher = pattern.matcher(inputStr);
			if (matcher.matches()) {
				return "Match";
			} else {
				return "NotMatch";
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "NotMatch";
		} finally {

		}
	}

	public static String isNumberValid(String phoneNumber) {
		// Initialize reg ex for phone number.
		try {
			String expression = "^\\d+$";
			CharSequence inputStr = phoneNumber;
			Pattern pattern = Pattern.compile(expression);
			Matcher matcher = pattern.matcher(inputStr);
			if (matcher.matches()) {
				return "Match";
			} else {
				return "NotMatch";
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "NotMatch";
		} finally {

		}
	}

	public static String isFloatValue(String phoneNumber) {
		// Initialize reg ex for phone number.
		try {
			String expression = "[0-9]*\\.?[0-9]+";
			CharSequence inputStr = phoneNumber;
			Pattern pattern = Pattern.compile(expression);
			Matcher matcher = pattern.matcher(inputStr);
			if (matcher.matches()) {
				return "Match";
			} else {
				return "NotMatch";
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "NotMatch";
		} finally {

		}
	}

	public String vendorDialog() {
		logger.info("[vendorDialog()]-----------Inside vendorDialog() method()-------------------------");
		setVendorFlag(true);
		return "";

	}

	public boolean isVendorFlag() {
		return vendorFlag;
	}

	public void setVendorFlag(boolean vendorFlag) {
		this.vendorFlag = vendorFlag;
	}

	public String hideDialogBox() {
		logger.info("[hideDialogBox]---------Inside hideDialog Box() method()-----------------");
		setVendorFlag(false);

		return "";

	}

	public String dialogSubmit() {
		return "";

	}

	public String loadDialogBox() {
		logger.info("[loadDialogBox()]-----------------Inside loadDialogBox() method()------------");
		DemoController controller = null;
		try {
			setVendorFlag(true);
			setWebsite(null);
			setNote(null);
			setValidate(null);
			setFirmName(null);
			setFirmRegistrationNumber(null);
			setOtherfirmtype(null);
			setNatureofbusiness(null);
			setAddress(null);
			setPeresonIncharge(null);
			setCity(null);
			setVendorPhoneNumber(null);
			setCountry_ID(null);
			setVendorTelephoneNumber(null);
			setState(null);
			setEmail_ID_vendor(null);
			setFaxVendor(null);
			setFirmTypeStandard(null);
			setVendorSuccessFlag(false);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.countryDrop(vendor);
			setCountrydrop1(vendor.getCountrydrop1());
			logger.debug("country" + vendor.getCountrydrop1());

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			setVendorFlag(false);
			setVendorSuccessFlag(false);
		}

		return "";

	}

	/**
	 * @return the vendorSuccessFlag
	 */
	public boolean isVendorSuccessFlag() {
		return vendorSuccessFlag;
	}

	/**
	 * @param vendorSuccessFlag
	 *            the vendorSuccessFlag to set
	 */
	public void setVendorSuccessFlag(boolean vendorSuccessFlag) {
		this.vendorSuccessFlag = vendorSuccessFlag;
	}

	public String namecheck() {
			logger.debug("<--------------inside namecheck--------------->");
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext context1 = FacesContext.getCurrentInstance();
		DemoController controller = null;
		try {

			Map<String, String> params = context1.getExternalContext()
					.getRequestParameterMap();
			logger.debug("------------------->" + params.get("param1"));
			String name = params.get("param1");

			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String status = controller.getVendorVerification(name);
			if (status.equalsIgnoreCase("Success")) {
				context.execute("checkFail();");
			} else {
				context.execute("checkSuccess();");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";

	}

	public void addVendorDialog() {
		validate = "";
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 480);
		options.put("contentWidth", 500);
		RequestContext.getCurrentInstance().openDialog("vendorDialog", options,
				null);
		loadDialogBox();

	}

	public List<I0025> drop;

	public List<I0025> getDrop() {
		return drop;
	}

	public void setDrop(List<I0025> drop) {
		this.drop = drop;
	}

	public String pvendorRegister() {
		logger.info("[pvendorRegister]----------Inside pvendorRegister() method()--------------------");
		validate = "";
		DemoController controller = null;
		try {
			if (vendorPhoneNumber.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Firm Name");
			} else if (firmRegistrationNumber.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Tax Number");
			} else if (!firmRegistrationNumber.matches("[0-9]+")) {
				throw new Exception("Please Enter the Valid Tax Number");
			} else if (city.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the City");
			} else if (peresonIncharge.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Person Incharge");
			} else if (firmName.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Telephone Number");
			} else if (!firmName.matches("[0-9]+")) {
				throw new Exception("Please Enter the Valid Phone Number");
			} else if (country_ID.equalsIgnoreCase("select")) {
				throw new Exception("Please Choose the Country");
			} else if (email_ID_vendor.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Mail-id");
			} else if (!email_ID_vendor.equalsIgnoreCase("")) {
				String res = isEmailValid(email_ID_vendor);
				if (res.equalsIgnoreCase("Match")) {
					logger.debug("valid Email");
				} else {
					throw new DemoException(
							"Please Enter the  Valid Vendor Email Id");
				}
			} else if (address.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Address");
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			vendorRegister();
			purchaseOrderFromMB.purchaseDrop();

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
		} finally {

		}
		return "";
	}
	
	/* stanley */
	public void selectState(ValueChangeEvent vc){
		logger.info("[selectState]----------Inside selectState() method()--------------------");
		String country = "";
		ApplicationContext ctx = null;
		DemoController controller = null;
		try{
			country = vc.getNewValue().toString();
			   ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			   controller = (DemoController) ctx.getBean("controller");
			   stateList=new ArrayList<String>();
			   stateList=controller.getstatelist(country);  
			   System.out.println("stateist-------------> "+stateList.size());
			   if(country.equalsIgnoreCase("India")) setCode("+91");
			   else if(country.equalsIgnoreCase("Indonesia")) setCode("+62");
			   else if(country.equalsIgnoreCase("Malesia")) setCode("+60");
			   else if(country.equalsIgnoreCase("Singapore")) setCode("+65");
			   else if(country.equalsIgnoreCase("UAE")) setCode("+971");
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
		
	}
	
	public void selectState1(ValueChangeEvent vc){
		logger.info("[selectState1]----------Inside selectState1() method()--------------------");
		String country = "";
		ApplicationContext ctx = null;
		DemoController controller = null;
		try{
			country = vc.getNewValue().toString();
			   ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			   controller = (DemoController) ctx.getBean("controller");
			   stateList1=new ArrayList<String>();
			   stateList1=controller.getstatelist(country); 
			   System.out.println("stateist1----->"+stateList1.size());
			   if(country.equalsIgnoreCase("India")) setCode("+91");
			   else if(country.equalsIgnoreCase("Indonesia")) setCode("+62");
			   else if(country.equalsIgnoreCase("Malesia")) setCode("+60");
			   else if(country.equalsIgnoreCase("Singapore")) setCode("+65");
			   else if(country.equalsIgnoreCase("UAE")) setCode("+971");
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
		
	}
	
	public void checkBoxValueChange(ValueChangeEvent vc){
		logger.info("[checkBoxValueChange]----------Inside checkBoxValueChange() method()--------------------");
		try{
			String check=vc.getNewValue().toString();
			if(check.equals("true")){
				setHiddenFlag(true);
				setShowFlag(false);
				setVenAddress1(address); 
				setVenCity1(city); 
				setVenCountry1(country_ID); 
				setVenPostalcode1(venPostalcode); 
				setVenState1(state); 
			}else{
				setHiddenFlag(false);
				setShowFlag(true);
				setVenAddress1(""); 
				setVenCity1(""); 
				setVenCountry1(""); 
				setVenPostalcode1(""); 
				setVenState1(""); 
			}
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
	}
	
	/*21-3-2017*/
	public List<String> daylist=null;
	
	public List<String> getDaylist() {
		return daylist;
	}

	public void setDaylist(List<String> daylist) {
		this.daylist = daylist;
	}

	public void venTypes(ValueChangeEvent v){
		logger.info("[venTypes]----------Inside venTypes() method()--------------------");
		String valueven="";	
		DemoController controller=null;
		try{
			 valueven=v.getNewValue().toString();
			if(!valueven.equalsIgnoreCase("Cash")){
				ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");				
				daylist=controller.getpaytype();
				System.out.println("daylist---->"+daylist.size()); 
			}
			setPayment(valueven);
			System.out.println("---------"+valueven);
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
		
	}
	
	public void dialog(){
		logger.info("[dialog]----------Inside dialog() method()--------------------");
		  try{
		    setValidate("");
		    setNewcash("");
		    RequestContext.getCurrentInstance().execute("PF('confirm1').show();");  
		  }catch(Exception e){
			  logger.error("Inside Exception", e);
		  }
		 }
	
	
	public String venLicence;
	public String venType;
	public Date venExdate; 
	public String venCode;
	public String payment;
	public String cash;
	public String newcash;
	public boolean venflag=true; 
	
	

	public boolean isVenflag() {
		return venflag;
	}

	public void setVenflag(boolean venflag) {
		this.venflag = venflag;
	}

	public String getNewcash() {
		return newcash;
	}

	public void setNewcash(String newcash) {
		this.newcash = newcash;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getVenLicence() {
		return venLicence;
	}

	public void setVenLicence(String venLicence) {
		this.venLicence = venLicence;
	}

	public String getVenType() {
		return venType;
	}

	public void setVenType(String venType) {
		this.venType = venType;
	}

	
	public Date getVenExdate() {
		return venExdate;
	}

	public void setVenExdate(Date venExdate) {
		this.venExdate = venExdate;
	}

	public String getVenCode() {
		return venCode;
	}

	public void setVenCode(String venCode) {
		this.venCode = venCode;
	}

	public String vendorRegister() {
		 String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		 String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		System.out.println("inside vendor register method");
		logger.info("[vendorRegister()]------------Inside vendorRegister() method()-----------------------");
		DemoController controller = null;
		try {
			
			try{
				if(country_ID.equalsIgnoreCase(null) || country_ID.equals("select") || country_ID.equalsIgnoreCase("")){
					throw new DemoException("Please Select Country");
				}
			}catch(Exception e){
				logger.error("Inside Exception"+e.getMessage());
				throw new DemoException("Please Select Country");
			}
			
			System.out.println("firm name"+firmName);
			vendor.setFirmName(firmName);
			vendor.setFirmRegistrationNumber(firmRegistrationNumber);
			vendor.setAddress(address);
			vendor.setVendorTelephoneNumber(vendorTelephoneNumber);
			vendor.setVendorPhoneNumber(vendorPhoneNumber);
			vendor.setCountry_ID(country_ID);			
			vendor.setEmail_ID_vendor(email_ID_vendor);
			vendor.setFaxVendor(faxVendor);
			vendor.setWebsite(website);
			vendor.setNote(note);
			// vendor.setNature_of_business_id(nature_of_business_id);;
			vendor.setPeresonIncharge(peresonIncharge);
			vendor.setFirmTypeStandard(firmTypeStandard);
			// vendor.setFrim_ID(frim_ID);
			vendor.setOtherfirmtype(otherfirmtype);
			vendor.setNatureofbusiness(natureofbusiness);
			vendor.setVenCompany(venCompany); 
			vendor.setVenCity(city);  
			vendor.setVenCountry(venCountry); 
			vendor.setVenPostalcode(venPostalcode); 
			vendor.setVenState(state); 
			vendor.setVenAddress1(venAddress1); 
			vendor.setVenCity1(venCity1); 
			vendor.setVenCountry1(venCountry1); 
			vendor.setVenPostalcode1(venPostalcode1); 
			vendor.setVenState1(venState1); 
			
			vendor.setVenLicence(venLicence); 
			vendor.setVenType(venType);
			vendor.setVenExdate(venExdate);  
			vendor.setVenCode(venCode); 
			vendor.setPayment(payment); 
			vendor.setCash(cash);  
			
			vendor.setProductName(productName);
			vendor.setProductPrice(productPrice);
			
			vendor.setClientID(clientID);
			vendor.setUserID(userID);
			
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String status = controller.venderReg(vendor);
			logger.debug("Status-----" + status);
			RequestContext context = RequestContext.getCurrentInstance();
			if (status.equalsIgnoreCase("success")) {
				setVendorFlag(false);
				 context.execute("PF('confirm').show();"); 
				setVendorSuccessFlag(true);
			} else {
				setVendorFlag(true);
				context.execute("checkFail();");
				setVendorSuccessFlag(false);
			}

			logger.debug("[vendorRegister()]method() reference--------------Outside vendor Register -------------->"
					+ isVendorFlag());

			return "";

		} catch (DemoException ie) {
			setValidate(ie.getMessage());

			return "";
		} finally {

		}
	}
	
	public String reset() {
		logger.info("[reset()]------------Inside reset() method()-----------------------");
		setWebsite(null);
		setNote(null);
		setValidate(null);
		setFirmName(null);
		setFirmRegistrationNumber(null);
		setOtherfirmtype(null);
		setNatureofbusiness(null);
		setAddress(null);
		setPeresonIncharge(null);
		setCity(null);
		setVendorPhoneNumber(null);
		setCountry_ID(null);
		setVendorTelephoneNumber(null);
		setState(null);
		setEmail_ID_vendor(null);
		setFaxVendor(null);
		setFirmTypeStandard(null);
		setVenCompany(null);
		setVenCity(null); 
		setVenCountry(null); 
		setVenPostalcode(null); 
		setVenState(null); 
		setVenAddress1(null); 
		setVenCity1(null); 
		setVenCountry1(null); 
		setVenPostalcode1(null); 
		setVenState1(null); 
		setVenBox(false); 
		setVenLicence(null); 
		setVenExdate(null); 
		setVenType(null); 
		//setVenCode(null); 
		setPayment(null); 
		setCash(null); 
		setNewcash(null);
		return "";
	}
	
	public String vendorDirect() {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		logger.info("[vendorDirect()]------------Inside vendorDirect() method()-----------------------");
		setWebsite(null);
		setNote(null);
		setValidate(null);
		setFirmName(null);
		setFirmRegistrationNumber(null);
		setOtherfirmtype(null);
		setNatureofbusiness(null);
		setAddress(null);
		setPeresonIncharge(null);
		setCity(null);
		setVendorPhoneNumber(null);
		setCountry_ID(null);
		setVendorTelephoneNumber(null);
		setState(null);
		setEmail_ID_vendor(null);
		setFaxVendor(null);
		setFirmTypeStandard(null);
		setVenCompany(null);
		setVenCity(null); 
		setVenCountry(null); 
		setVenPostalcode(null); 
		setVenState(null); 
		setVenAddress1(null); 
		setVenCity1(null); 
		setVenCountry1(null); 
		setVenPostalcode1(null); 
		setVenState1(null); 
		setVenBox(false);
		setVenLicence(null); 
		setVenExdate(null); 
		setVenType(null); 
		setVenCode(null);
		setPayment(null); 
		setCash(null); 
		setNewcash(null);
		setHiddenFlag(false);
		setShowFlag(true);
		DemoController controller=null;
		try{
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			venCode=controller.getvencode(clientID,userID);
			daylist=controller.getpaytype();
			logger.debug("daylist in registration------->"+daylist); 
			setVenflag(false);
		}catch(Exception e){
			logger.warn("Inside Exception", e);
		}
		return "vendorDirect";
	}

	public String vendorRegistercash(){
		logger.info("[vendorRegistercash()]------------Inside vendorRegistercash() method()-----------------------");
		  DemoController controller=null;
		  String status="";
		  setValidate(null);
		  try{
		   ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		   controller = (DemoController) ctx.getBean("controller");
		    vendor.setNewcash(newcash+" days"); 
		    status=controller.setcash(vendor); 
		    setCash(vendor.getNewcash()); 
		    RequestContext.getCurrentInstance().update("center_content:daypanelid");
		     if("Success".equalsIgnoreCase(status)){
		     RequestContext.getCurrentInstance().execute("PF('confirm1').hide();");
		     daylist=controller.getpaytype();     
		     RequestContext.getCurrentInstance().update("center_content");
		   }else if("exist".equalsIgnoreCase(status)){
			   setValidate("Already Exist this Day");
		   }
		  }catch(Exception e){
			  logger.warn("Inside Exception", e);
		  }
		  return "";
		 }
	
	//Quotation Module
	
	List<String> productList=new ArrayList<String>();
	List<String> vendorList=new ArrayList<String>();
	List<VendorRegisterFormMB> quotationList=null;
	List<VendorRegisterFormMB> quotationDetailList=null;
	List<VendorRegisterFormMB> finalquotationDetailList=null;
	public String serialNo;
	public String productName;
	public String vendorName;
	public String productCount;
	public String quotationNumber;
	public String status;
	public String approvalStatus;
	public Date orderDate;
	public Date deliveryDate;
	public String productPrice;
	public String userID;
	private String[] selectedVendors;
	private String approveButtonFlag;
	public String userType;
	public String choosenStatus;
	public String vendormailid;
	public int quotationDetailsId;
	private boolean quotationCheck=false;
	private List<String> mailList=null;
	ArrayList<VendorRegisterFormMB> filterList;
	public String serialno;
	
	
	
	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public ArrayList<VendorRegisterFormMB> getFilterList() {
		return filterList;
	}

	public void setFilterList(ArrayList<VendorRegisterFormMB> filterList) {
		this.filterList = filterList;
	}
	
	public String getVendormailid() {
		return vendormailid;
	}

	public void setVendormailid(String vendormailid) {
		this.vendormailid = vendormailid;
	}

	public String getChoosenStatus() {
		return choosenStatus;
	}

	public void setChoosenStatus(String choosenStatus) {
		this.choosenStatus = choosenStatus;
	}

	public List<String> getMailList() {
		return mailList;
	}

	public void setMailList(List<String> mailList) {
		this.mailList = mailList;
	}

	public List<VendorRegisterFormMB> getFinalquotationDetailList() {
		return finalquotationDetailList;
	}

	public void setFinalquotationDetailList(List<VendorRegisterFormMB> finalquotationDetailList) {
		this.finalquotationDetailList = finalquotationDetailList;
	}

	public int getQuotationDetailsId() {
		return quotationDetailsId;
	}

	public void setQuotationDetailsId(int quotationDetailsId) {
		this.quotationDetailsId = quotationDetailsId;
	}

	public List<VendorRegisterFormMB> getQuotationDetailList() {
		return quotationDetailList;
	}

	public void setQuotationDetailList(List<VendorRegisterFormMB> quotationDetailList) {
		this.quotationDetailList = quotationDetailList;
	}

	public boolean isQuotationCheck() {
		return quotationCheck;
	}

	public void setQuotationCheck(boolean quotationCheck) {
		this.quotationCheck = quotationCheck;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getApproveButtonFlag() {
		return approveButtonFlag;
	}

	public void setApproveButtonFlag(String approveButtonFlag) {
		this.approveButtonFlag = approveButtonFlag;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getQuotationNumber() {
		return quotationNumber;
	}

	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String[] getSelectedVendors() {
		return selectedVendors;
	}

	public void setSelectedVendors(String[] selectedVendors) {
		this.selectedVendors = selectedVendors;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<String> getVendorList() {
		return vendorList;
	}

	public void setVendorList(List<String> vendorList) {
		this.vendorList = vendorList;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public List<VendorRegisterFormMB> getQuotationList() {
		return quotationList;
	}

	public void setQuotationList(List<VendorRegisterFormMB> quotationList) {
		this.quotationList = quotationList;
	}

	public List<String> getProductList() {
		return productList;
	}

	public void setProductList(List<String> productList) {
		this.productList = productList;
	}

	public String quotationPage(){
		logger.info("[quotationPage()]------------------ Inside Quotation New Page() method()----------------");
		DemoController controller=null;
		setValidate("");
		try{
			 String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			 String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			 ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			 controller = (DemoController) ctx.getBean("controller");
			 productList=controller.getquotproductList(clientID,userID);
			 quotationList=new ArrayList<VendorRegisterFormMB>();
			 for (int i = 0; i < 10; i++) {
				 VendorRegisterFormMB ven=new VendorRegisterFormMB();
				 ven.setSerialNo(String.valueOf(i+1));
				 ven.setSelectedVendors(new String[0]);
				 ven.setProductCount("");
				 ven.setProductName("");
				 quotationList.add(ven);
			 }
		}catch(Exception e){
			logger.error("Inside Exception"+e.getMessage());
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return "quotationNewPage";
	}
	
	public String addButton(){
		logger.info("[addButton()]---------------- Inside Quotation add button() method()----------------");
		try{
			for (int i = 0; i < 1; i++) {
				VendorRegisterFormMB ven=new VendorRegisterFormMB();
				ven.setSerialNo(String.valueOf(quotationList.size()+1));
				quotationList.add(ven);
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e);  
		}finally{
		}
		return "";
	}
	public String addEditButton(){
		logger.info("[addEditButton()]----------------- Inside Quotation Edit add button() method()--------------");
		try{
			for (int i = 0; i < 1; i++) {
				VendorRegisterFormMB ven=new VendorRegisterFormMB();
				ven.setSerialNo(String.valueOf(quotationDetailList.size()+1));
				quotationDetailList.add(ven);
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e);  
		}finally{
		}
		return "";
	}
	public void productvalueChange(ValueChangeEvent vc){
		logger.info("[productvalueChange()]---------------- Inside Quotation Product Value Change() method()---------------------");
		String serialNo="";selectedVendors=new String[0];
		DemoController controller=null;
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			serialNo=vc.getComponent().getAttributes().get("serial").toString();
			logger.debug("serial no"+serialNo); 
			String str=vc.getNewValue().toString();
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			vendorList=controller.getproductVendor(clientID,userID,str);
			VendorRegisterFormMB ven=new VendorRegisterFormMB();
			ven.setSerialNo(serialNo);
			ven.setVendorList(vendorList);
			ven.setSelectedVendors(quotationList.get(Integer.parseInt(serialNo) - 1).getSelectedVendors());
			ven.setProductCount(quotationList.get(Integer.parseInt(serialNo) - 1).getProductCount());
			ven.setDeliveryDate(quotationList.get(Integer.parseInt(serialNo) -1).getDeliveryDate());
			ven.setProductName(str);
			quotationList.set((Integer.parseInt(serialNo)-1),ven);
		}catch(Exception e){
			logger.warn("Inside Exception", e);  
		}finally{
		}
	}
	public void editProductvalueChange(ValueChangeEvent vc){
		logger.info("[editproductvalueChange()]------------- Inside Quotation Edit Product Value Change() method() ----------------------");
		String serialNo="";selectedVendors=new String[0];
		DemoController controller=null;
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			serialNo=vc.getComponent().getAttributes().get("serial").toString();
			System.out.println("serial no"+serialNo);
			String str=vc.getNewValue().toString();
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			vendorList=controller.getproductVendor(clientID,userID,str);
			System.out.println("vendorlist size---"+vendorList.size());
			VendorRegisterFormMB ven=new VendorRegisterFormMB();
			ven.setSerialNo(serialNo);
			ven.setVendorList(vendorList);
			ven.setSelectedVendors(quotationDetailList.get(Integer.parseInt(serialNo) - 1).getSelectedVendors());
			ven.setProductCount(quotationDetailList.get(Integer.parseInt(serialNo) - 1).getProductCount());
			ven.setProductPrice(quotationDetailList.get(Integer.parseInt(serialNo) - 1).getProductCount());
			ven.setDeliveryDate(quotationDetailList.get(Integer.parseInt(serialNo) -1).getDeliveryDate());
			ven.setQuotationDetailsId(quotationDetailList.get(Integer.parseInt(serialNo) -1).getQuotationDetailsId());
			ven.setProductName(str);
			quotationDetailList.set((Integer.parseInt(serialNo)-1),ven);
		}catch(Exception e){
			logger.warn("Inside Exception", e);  
		}finally{
		}
	}
	public void vendorValueChange(AjaxBehaviorEvent event){
		logger.info("[vendorvalueCHANGE()]--------------- Inside Quotation Vendor Value Change()method()-------------------------");
		try{
			RequestContext.getCurrentInstance().execute("PF('addvendor').hide();");
			RequestContext.getCurrentInstance().execute("PF('emptyprod').hide();");
			String productname=event.getComponent().getAttributes().get("product").toString();
			String serialno=event.getComponent().getAttributes().get("serial").toString();
			if(productname.equals("")){
				RequestContext.getCurrentInstance().execute("PF('emptyprod').show();");
			}else{
				for (int i = 0; i < quotationList.size(); i++) {
					for (int j = 0; j < quotationList.get(Integer.parseInt(serialno) - 1).selectedVendors.length; j++) {
						if(quotationList.get(Integer.parseInt(serialno) - 1).selectedVendors[j].equals("add")){
							setProductName(productname);
							vendorDirect();
							setProductPrice("");
							RequestContext.getCurrentInstance().execute("PF('addvendor').show();");
						}
					}
				}
			}
		}catch(Exception e){
			setValidate(e.getMessage());
			logger.warn("Inside Exception", e);  
		}finally{
		}
	}
	
	public String saveQuotation(){
		 logger.info("[saveQuotation()]------------ Inside Save Quotation() method()-------------------");
		 DemoController controller=null;
		 int count=0;setValidate("");String status="Fail";
		 try{
			 String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			 String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			 ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			 controller = (DemoController) ctx.getBean("controller");
			 for (int i = 0; i < quotationList.size(); i++) {
				 try{
					 if(quotationList.get(i).getProductName().equals("") || quotationList.get(i).getProductName() == null){
						 
						 count++;
					 }
				 }catch(Exception e){
					 count++;
					 logger.warn("Inside Exception"+e);
					 setValidate(e.getMessage());
				 }
			 }
			 if(count==quotationList.size()){
				 logger.info("inside product name empty exception");
				 throw new Exception("Please Choose the Product Name"); 
			 }
			 status=controller.insertQuotation(clientID,userID,quotationList);
			 if(status.equalsIgnoreCase("Success")){
					RequestContext.getCurrentInstance().execute("PF('quotconfirm').show();");
			 }
		 }catch(Exception e){
			 setValidate(e.getMessage());
			 logger.warn("Inside Exception", e); 
		 }finally{
			 // democontroller missing
		 }
		 return "";
	}

	//Quotation Console Module
	public String quotationConsolePage(){
		setFilterList(null);
		logger.info("[quotationConsolePage()]--------------- Inside Quotation Console Page() method()---------------------");
		int count=0;setValidate("");
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			setUserType(userType);
			quotationList=new ArrayList<VendorRegisterFormMB>();
			quotationList=AccountsJDBC.getquotationList(clientID,userID,userType,approvalStatus);
			for (int i = 0; i < quotationList.size(); i++) {
				if(quotationList.get(i).getApprovalStatus().equalsIgnoreCase("draft")){ 
					count++;
				}
			}
			if(count==0){
				approveButtonFlag="none";
			}else{
				approveButtonFlag="1";
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return "quotationConsolePage";
	}
	
	public String quotationApproval(){
		logger.info("[quotqtionApproval()--------------- Inside Quotation Approval() method()----------------");
		String status="";DemoController controller = null;
		int count=0;setValidate("");String mailStatus="Fail";
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			for (int i = 0; i < quotationList.size(); i++) {
				if(quotationList.get(i).isQuotationCheck()==true){
					count++;
				}
			}
			if(count==0){
				throw new Exception("Please Choose atleast one row for Approval.");
			}else{
				setValidate("");
				VendorRegisterFormMB vendor=new VendorRegisterFormMB();
				status=controller.quotationApproval(clientID,userID,quotationList,vendor);
				logger.debug("status"+status); 
				if(status.equalsIgnoreCase("Success")){
					mailList=new ArrayList<String>();
					quotationDetailList=new ArrayList<VendorRegisterFormMB>();
					setMailList(vendor.getMailList());
					HashSet<String> hashList=new HashSet<String>(mailList);
					mailList.clear();
					mailList.addAll(hashList);
					setQuotationDetailList(vendor.getQuotationDetailList());
					mailStatus=MailSendJDBC.sendquotationVendorMail(mailList,quotationDetailList);
					logger.debug("quotation mail status"+mailStatus);
					RequestContext.getCurrentInstance().execute("PF('approvalConfirm').show()");
				}
			}
		}catch(Exception e){
			setValidate(e.getMessage());
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return "";
	}
	
	public String quotationView(){
		logger.info("[qutationView()]---------------- Inside Quotation View() Method()------------------");
		DemoController controller = null;setValidate("");
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			quotationDetailList=new ArrayList<VendorRegisterFormMB>();
			quotationDetailList=controller.getquotationDetails(clientID,quotationNumber);
			logger.debug("quotation view list size"+quotationDetailList.size());
		}catch(Exception e){
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return "";
	}
	
	public String quotationEdit(){
		logger.info("[qutationEdit()]-------------- Inside Quotation Edit() Method()-----------------------");
		DemoController controller = null;setValidate("");
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			quotationDetailList=new ArrayList<VendorRegisterFormMB>();
			productList=controller.getquotproductList(clientID,userID);
			quotationDetailList=controller.getquotationDetails(clientID,quotationNumber);
			for (int i = 0; i < quotationDetailList.size(); i++) {
				vendorList=controller.getproductVendor(clientID,userID,quotationDetailList.get(i).getProductName());
				VendorRegisterFormMB ven=new VendorRegisterFormMB();
				ven.setVendorList(vendorList);
				ven.setSerialNo(quotationDetailList.get(i).getSerialNo());
				ven.setProductName(quotationDetailList.get(i).getProductName());
				ven.setProductCount(quotationDetailList.get(i).getProductCount());
				ven.setDeliveryDate(quotationDetailList.get(i).getDeliveryDate());
				ven.setProductPrice(quotationDetailList.get(i).getProductPrice());
				ven.setSelectedVendors(quotationDetailList.get(i).getSelectedVendors());
				ven.setQuotationDetailsId(quotationDetailList.get(i).getQuotationDetailsId());
				quotationDetailList.set((i+1) -1,ven);
				System.out.println("vendor list size--"+vendorList.size());
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return "";
	}
	public String quotationDelete(){
		logger.info("[qutationDelete()]------------------ Inside Quotation Delete() Method()------------------");
		DemoController controller = null;String status="Fail";
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			status=controller.deleteQuotation(clientID,quotationNumber);
			if(status.equalsIgnoreCase("Success")){
				RequestContext.getCurrentInstance().execute("PF('cdsu').show();");
			}
		}catch(Exception e){
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return "";
	}
	
	public String finalQuotation(){
		logger.info("[finalQutatio()]------------------- Inside Final Quotation() Method()-------------------");
		setValidate("");
		DemoController controller = null;String status="Fail";int count=0;
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			for (int i = 0; i < quotationDetailList.size(); i++) {
				if(quotationDetailList.get(i).isQuotationCheck()==true){
					count++;
				}
			}
			if(count==0){
				throw new Exception("Please Choose atleast one row for Final Quotation");
			}else{
				status=controller.finalQuotation(clientID,quotationNumber,quotationDetailList);
				if(status.equalsIgnoreCase("Success")){
					finalquotationDetailList=new ArrayList<VendorRegisterFormMB>();
					finalquotationDetailList=controller.getfinalQuotationList(clientID,quotationNumber);
					RequestContext.getCurrentInstance().execute("PF('quotationDialogView1').hide();");
					RequestContext.getCurrentInstance().execute("PF('finalQuotationDialog').show();");
				}
			}
		}catch(Exception e){
			setValidate(e.getMessage());
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return "";
	}
	
	public String finalQuotationView(){
		logger.info("[finalQutationView()]------------------ Inside Final Quotation View() Method()-------------------");
		DemoController controller = null;
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			quotationDetailList=new ArrayList<VendorRegisterFormMB>();
			quotationDetailList=controller.getfinalQuotationList(clientID,quotationNumber);
		}catch(Exception e){
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return "";
	}
	public String updateQuotation(){
		logger.info("[updateQutation()]---------------- Inside Quotation Update() Method()--------------------");
		DemoController controller = null;String status="Fail";int count=0;
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			for (int i = 0; i < quotationDetailList.size(); i++) {
				if(quotationDetailList.get(i).getProductName().equals("") || quotationDetailList.get(i).getProductName().equals(null)){
					count++;
				}
			}
			if(count==quotationDetailList.size()){
				throw new Exception("Please Choose the Product Name");
			}else{
				status=controller.quotationUpdate(clientID,userID,quotationNumber,quotationDetailList);
				if(status.equalsIgnoreCase("Success")){
					RequestContext.getCurrentInstance().execute("PF('updateConfirm').show();");
				}
			}
		}catch(Exception e){
			setValidate(e.getMessage());
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return "";
	}
	public String removeButton(){
		logger.info("[removeButton()]---------------- Inside removeButton() Method()--------------------");
		DemoController controller = null;
		try{
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.removeEditRow(quotationDetailsId);
			quotationEdit();
		}catch(Exception e){
			logger.warn("Inside Exception", e); 
		}finally{
		}
		return "";
	}
}
