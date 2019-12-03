package com.inacsys.managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
//import java.util.Date;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.hibernate.sql.Update;
import org.omg.CORBA.Request;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

//import scala.annotation.meta.setter;















import com.inacsys.controler.DemoController;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.domain.Vendor;
import com.inacsys.domain.VendorDelete;
import com.inacsys.domain.VendorUpdate;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0025;
import com.inacsys.shared.I0028;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "vendorViewFormMB")
@RequestScoped
public class VendorViewFormMB implements Serializable {
	private static Logger logger = LoggerFactory
			.getLogger(VendorViewFormMB.class);
	@ManagedProperty(value = "#{vendorUpdateFormMB}")
	VendorUpdateFormMB vendorUpdateFormMB;
	@ManagedProperty(value = "#{vendorRegisterFormMB}")
	VendorRegisterFormMB vendorRegisterFormMB;

	public VendorUpdateFormMB getVendorUpdateFormMB() {
		return vendorUpdateFormMB;
	}

	public void setVendorUpdateFormMB(VendorUpdateFormMB vendorUpdateFormMB) {
		this.vendorUpdateFormMB = vendorUpdateFormMB;
	}

	public VendorRegisterFormMB getVendorRegisterFormMB() {
		return vendorRegisterFormMB;
	}

	public void setVendorRegisterFormMB(
			VendorRegisterFormMB vendorRegisterFormMB) {
		this.vendorRegisterFormMB = vendorRegisterFormMB;
	}

	private static final long serialVersionUID = 1L;
	public String frimName;
	public String flag = "none";
	public String flag1 = "none";
	public String peresonIncharge;
	public ArrayList<I0025> vendorlist = null;
	public ArrayList<VendorViewFormMB> vendorlist1 = new ArrayList<VendorViewFormMB>();
	public String firmName;
	public String firmRegistrationNumber;
	public String address;
	public String vendorTelephoneNumber;
	public String vendorPhoneNumber;
	public String country_ID;
	public String state;
	public String city;
	public String cities;
	List<String> names;
	private List<Vendor> filterList = null;
	
	ArrayList<VendorViewFormMB> filterList1;
	
	public ArrayList<VendorViewFormMB> getFilterList1() {
		return filterList1;
	}

	public void setFilterList1(ArrayList<VendorViewFormMB> filterList1) {
		this.filterList1 = filterList1;
	}

		/* Stanley */
	 	public String code;
		private boolean hiddenFlag=false;
		private boolean showFlag=true;
		private boolean venBox=false; 
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
		public String address1;
		public String city1;
		public String post;
		public String post1;
		public String country_ID1;
		public String state1;
		public String companyven;
		public String approvalStatus;
		public String userType;
		private boolean vendorCheck=false;
		private String approveButtonFlag="none";
		
		public String getApproveButtonFlag() {
			return approveButtonFlag;
		}

		public void setApproveButtonFlag(String approveButtonFlag) {
			this.approveButtonFlag = approveButtonFlag;
		}

		public boolean isVendorCheck() {
			return vendorCheck;
		}

		public void setVendorCheck(boolean vendorCheck) {
			this.vendorCheck = vendorCheck;
		}

		public String getUserType() {
			return userType;
		}

		public void setUserType(String userType) {
			this.userType = userType;
		}

		public String getApprovalStatus() {
			return approvalStatus;
		}

		public void setApprovalStatus(String approvalStatus) {
			this.approvalStatus = approvalStatus;
		}

		public String getAddress1() {
			return address1;
		}

		public void setAddress1(String address1) {
			this.address1 = address1;
		}

		public String getCity1() {
			return city1;
		}

		public void setCity1(String city1) {
			this.city1 = city1;
		}

		public String getPost() {
			return post;
		}

		public void setPost(String post) {
			this.post = post;
		}

		public String getPost1() {
			return post1;
		}

		public void setPost1(String post1) {
			this.post1 = post1;
		}

		public String getCountry_ID1() {
			return country_ID1;
		}

		public void setCountry_ID1(String country_ID1) {
			this.country_ID1 = country_ID1;
		}

		public String getState1() {
			return state1;
		}

		public void setState1(String state1) {
			this.state1 = state1;
		}

		public String getCompanyven() {
			return companyven;
		}

		public void setCompanyven(String companyven) {
			this.companyven = companyven;
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

		public boolean isVenBox() {
			return venBox;
		}

		public void setVenBox(boolean venBox) {
			this.venBox = venBox;
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

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public String getCities() {
		return cities;
	}

	public void setCities(String cities) {
		this.cities = cities;
	}

	public String email_ID_vendor;
	public String faxVendor;
	public String nature_of_business_id;
	public String firmTypeStandard;
	public String frim_ID;
	public int vendor_Id = 0;
	public String validate;
	public String otherfirmtype;
	public String natureofbusiness;

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public String getOtherfirmtype() {
		return otherfirmtype;
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

	List<I0025> xx = new ArrayList<I0025>();

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getVendor_Id() {
		return vendor_Id;
	}

	public void setVendor_Id(int vendor_Id) {
		this.vendor_Id = vendor_Id;
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

	public String getVendorTelephoneNumber() {
		return vendorTelephoneNumber;
	}

	public void setVendorTelephoneNumber(String vendorTelephoneNumber) {
		this.vendorTelephoneNumber = vendorTelephoneNumber;
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

	public String getPeresonIncharge() {
		return peresonIncharge;
	}

	public void setPeresonIncharge(String peresonIncharge) {
		this.peresonIncharge = peresonIncharge;
	}

	public String getNature_of_business_id() {
		return nature_of_business_id;
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

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getVendorPhoneNumber() {
		return vendorPhoneNumber;
	}

	public void setVendorPhoneNumber(String vendorPhoneNumber) {
		this.vendorPhoneNumber = vendorPhoneNumber;
	}

	public List<I0025> getXx() {
		return xx;
	}

	public void setXx(ArrayList<I0025> xx) {
		this.xx = xx;
	}

	public ArrayList<VendorViewFormMB> getVendorlist1() {
		return vendorlist1;
	}

	public void setVendorlist1(ArrayList<VendorViewFormMB> vendorlist1) {
		this.vendorlist1 = vendorlist1;
	}

	public ArrayList<I0025> getVendorlist() {
		return vendorlist;
	}

	public void setVendorlist(ArrayList<I0025> vendorlist) {
		this.vendorlist = vendorlist;
	}

	public String getFrimName() {
		return frimName;
	}

	public void setFrimName(String frimName) {
		this.frimName = frimName;
	}

	Vendor vendor = new Vendor();

	ArrayList<VendorViewFormMB> ven1 = new ArrayList<VendorViewFormMB>();
	ArrayList<VendorViewFormMB> filterValue;

	public ArrayList<VendorViewFormMB> getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(ArrayList<VendorViewFormMB> filterValue) {
		this.filterValue = filterValue;
	}

	private List<VendorViewFormMB> vendorFilter;

	BigDecimal phn = BigDecimal.valueOf(0);
	public String country1;

	/*
	 * jeni 12.1.2015
	 */

	public String getCountry1() {
		return country1;
	}

	public void setCountry1(String country1) {
		this.country1 = country1;
	}

	public BigDecimal getPhn() {
		return phn;
	}

	public void setPhn(BigDecimal phn) {
		this.phn = phn;
	}

	public ArrayList<VendorViewFormMB> getVen1() {
		return ven1;
	}

	public void setVen1(ArrayList<VendorViewFormMB> ven1) {
		this.ven1 = ven1;
	}

	List<String> batchProductName4;
	List<String> citiesvens;

	public List<String> getCitiesvens() {
		return citiesvens;
	}

	public void setCitiesvens(List<String> citiesvens) {
		this.citiesvens = citiesvens;
	}

	public List<String> getBatchProductName4() {
		return batchProductName4;
	}

	public void setBatchProductName4(List<String> batchProductName4) {
		this.batchProductName4 = batchProductName4;
	}

	public String vendorname;

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public String vendors() {
		logger.info("[vendors()]-------------------Inside vendors() method()---------------------------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setBatchProductName4(controller.productVendor1(batchProductName4));
			Collections.sort(batchProductName4, String.CASE_INSENSITIVE_ORDER);
			setNames(controller.getnames(names));
			Collections.sort(names, String.CASE_INSENSITIVE_ORDER);
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		}
	}

	public String vendorView() {
		logger.info("[vendorView()]-------------------Inside vendorView() method()---------------------------------");
		ven1.clear();
		cities = "";
		DemoController controller = null;
		try {
			setValidate("");
			if (vendorname.equalsIgnoreCase("")) {
				throw new Exception("Please Select the Vendor Name");
			}
			flag = "none";
			vendorlist1 = null;
			flag1 = "none";
			vendor.setFirmName(vendorname);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			vendorlist = controller.vendorView(vendor);
			for (int j = 0; j < vendorlist.size(); j++) {
				VendorViewFormMB vens = new VendorViewFormMB();
				vens.setVendorPhoneNumber(vendorlist.get(j)
						.getVendorPhoneNumber());
				vens.setCity(vendorlist.get(j).getCityName());
				vens.setCountry1(vendorlist.get(j).getI0028().getName());
				vens.setPhn(new BigDecimal(vendorlist.get(j).getFirmName()));
				ven1.add(vens);
			}
			flag = "1";
			return "";
		} catch (Exception ie) {
			logger.error("Inside Exception"+ie.getMessage());
			setValidate(ie.getMessage());
			return "";
		} finally {

		}

	}

	public String searchCity() {
		logger.info("[searchCity()]-------------------Inside searchCity() method()---------------------------------");
		DemoController controller = null;
		try {
			ven1.clear();
			setValidate("");
			vendorname = "";
			if (cities.equalsIgnoreCase("")) {
				throw new Exception("Please Select the City Name");
			}
			flag = "none";
			vendorlist1 = null;
			flag1 = "none";
			vendor.setCities(cities);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.searchCity(vendor);
			setVendorlist(vendor.getVendorlist());
			for (int j = 0; j < vendorlist.size(); j++) {
				VendorViewFormMB vens = new VendorViewFormMB();
				vens.setVendorPhoneNumber(vendorlist.get(j)
						.getVendorPhoneNumber());
				vens.setCity(vendorlist.get(j).getCityName());
				vens.setCountry1(vendorlist.get(j).getI0028().getName());
				vens.setPhn(new BigDecimal(vendorlist.get(j).getFirmName()));
				ven1.add(vens);
			}
			flag = "1";
			
			return "";
		} catch (Exception ie) {
			logger.error("Inside Exception"+ie.getMessage());
			setValidate(ie.getMessage());
			return "";
		} finally {

		}

	}

	public String cancel() {

		flag = "none";
		flag1 = "none";
		frimName = null;
		return "venorForm";

	}

	/*
	 * jeni up to this
	 */
	public String website;
	public String note;

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

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

	public void setXx(List<I0025> xx) {
		this.xx = xx;
	}

	DemoController controller = null;

	public String backProccess() {
		logger.info("[backProccess()]-------------------Inside backProccess() method()---------------------------------");
		try {
			setCities("");
			DemoController controller = null;
			ven1.clear();
			flag = "none";
			vendorlist1 = null;
			flag1 = "none";
			frimName = "";
			vendorname = "";
			setValidate(null);
			vendor.setFirmName(frimName);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if (vendor.getVendorlist().size() > 0) {
				setVendorlist(vendor.getVendorlist());
				flag = "none";
				flag1 = "none";
				if (vendorlist.size() > 0) {
					for (int i = 0; i < vendorlist.size(); i++) {
						VendorViewFormMB vens = new VendorViewFormMB();
						vens.setVendorPhoneNumber(vendorlist.get(i)
								.getVendorPhoneNumber());
						vens.setNatureofbusiness(vendorlist.get(i)
								.getNatureOfBusiness());
						vens.setPhn(new BigDecimal(vendorlist.get(i)
								.getFirmName()));
						vens.setCity(vendorlist.get(i).getCityName());
						vens.setCountry1(vendorlist.get(i).getI0028().getName());
						ven1.add(vens);
					}
					flag = "1";
				}
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

		finally {
			firmName = null;
			firmRegistrationNumber = "";
			address = null;
			vendorTelephoneNumber = null;
			vendorPhoneNumber = null;
			country_ID = null;
			email_ID_vendor = null;
			faxVendor = null;
			peresonIncharge = null;
			nature_of_business_id = null;
			firmTypeStandard = null;
			frim_ID = null;
			vendor_Id = 0;
			natureofbusiness = null;
			otherfirmtype = null;
			logger.debug("111111111111111111111");
		}

		return "vendorViewsucsuccess4";

	}
	public boolean Viewflag = false;
	public boolean Modifyflag = false;

	public boolean isModifyflag() {
		return Modifyflag;
	}

	public void setModifyflag(boolean modifyflag) {
		Modifyflag = modifyflag;
	}

	public void Viewven() {
		logger.info("[Viewven()]-------------------Inside Viewven() method()---------------------------------");
		setViewflag(true);
		vendorView1();
	}

	public void Modifyven() {
		logger.info("[Modifyven()]-------------------Inside Modifyven() method()---------------------------------");
		setModifyflag(true);
		vendorUpdate();
		vendorUpdateFormMB.update();
		vendorRegisterFormMB.countryDrop();

	}

	public ArrayList<I0028> countrydrop1;

	public ArrayList<I0028> getCountrydrop1() {
		return countrydrop1;
	}

	public void setCountrydrop1(ArrayList<I0028> countrydrop1) {
		this.countrydrop1 = countrydrop1;
	}

	public boolean isViewflag() {
		return Viewflag;
	}

	public void setViewflag(boolean viewflag) {
		Viewflag = viewflag;
	}

	

	public static String isEmailValid(String email) {
		logger.info("[isEmailValid()]-------------------Inside isEmailValid() method()---------------------------------");
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

	

	public String hideppup() {
		logger.info("[hideppup()]-------------------Inside hideppup() method()---------------------------------");
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("checkMy();");
		return "";

	}

	public String delete() {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		DemoController controller = null;
		logger.info("[delete()]-------------------Inside delete() method()---------------------------------");
		try {
			flag = "none";
			VendorDelete vendorDelete = new VendorDelete();
			logger.debug("[delete()] --------------- phone number ------------------------>"+vendorPhoneNumber);
			vendorDelete.setVendorPhoneNumber(String.valueOf(vendor_Id));
			vendorDelete.setClientID(clientID);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.vendorDelete(vendorDelete);
			RequestContext.getCurrentInstance().execute("PF('cdsu').show();");
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
		}
		return "";
	}

	public String Phonenumberv;

	public String getPhonenumberv() {
		return Phonenumberv;
	}

	public void setPhonenumberv(String phonenumberv) {
		Phonenumberv = phonenumberv;
	}

	public String approval;
	
	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}
	
	

	public String success1() {
		logger.info("[success1()]-------------------Inside success1() method()---------------------------------");
		firmName = null;
		firmRegistrationNumber = null;
		address = null;
		vendorTelephoneNumber = null;
		vendorPhoneNumber = null;
		country_ID = null;
		email_ID_vendor = null;
		faxVendor = null;
		peresonIncharge = null;
		nature_of_business_id = null;
		firmTypeStandard = null;
		frim_ID = null;
		vendor_Id = 0;
		natureofbusiness = null;
		otherfirmtype = null;
		return "success5";
	}

	public String save() {
		logger.info("[save()]-------------------Inside save() method()---------------------------------");
		return address;

	}

	private org.primefaces.model.SortOrder sortPhoneNumber = org.primefaces.model.SortOrder.UNSORTED;
	private org.primefaces.model.SortOrder sortCountryr = org.primefaces.model.SortOrder.UNSORTED;
	private org.primefaces.model.SortOrder sortNature = org.primefaces.model.SortOrder.UNSORTED;
	private org.primefaces.model.SortOrder sortFirm = org.primefaces.model.SortOrder.UNSORTED;

	

	public org.primefaces.model.SortOrder getSortNature() {
		return sortNature;
	}

	public void setSortNature(org.primefaces.model.SortOrder sortNature) {
		this.sortNature = sortNature;
	}

	public org.primefaces.model.SortOrder getSortFirm() {
		return sortFirm;
	}

	public void setSortFirm(org.primefaces.model.SortOrder sortFirm) {
		this.sortFirm = sortFirm;
	}

	public void sortByvendorPhoneNumber() {
		logger.info("[sortByvendorPhoneNumber()]-------------------Inside sortByvendorPhoneNumber() method()---------------------------------");
		sortNature = org.primefaces.model.SortOrder.UNSORTED;
		sortCountryr = org.primefaces.model.SortOrder.UNSORTED;
		sortFirm = org.primefaces.model.SortOrder.UNSORTED;
		if (sortPhoneNumber.equals(org.primefaces.model.SortOrder.ASCENDING)) {
			setSortPhoneNumber(org.primefaces.model.SortOrder.DESCENDING);
		} else {
			setSortPhoneNumber(org.primefaces.model.SortOrder.ASCENDING);
		}
	}

	public void sortByCountry() {
		logger.info("[sortByCountry()]-------------------Inside sortByCountry() method()---------------------------------");
		sortPhoneNumber = org.primefaces.model.SortOrder.UNSORTED;
		sortFirm = org.primefaces.model.SortOrder.UNSORTED;
		sortNature = org.primefaces.model.SortOrder.UNSORTED;
		if (sortCountryr.equals(org.primefaces.model.SortOrder.ASCENDING)) {
			setSortCountryr(org.primefaces.model.SortOrder.DESCENDING);
		} else {
			setSortCountryr(org.primefaces.model.SortOrder.ASCENDING);
		}
	}

	public void sortByNature() {
		logger.info("[sortByNature()]-------------------Inside sortByNature() method()---------------------------------");
		sortPhoneNumber = org.primefaces.model.SortOrder.UNSORTED;
		sortCountryr = org.primefaces.model.SortOrder.UNSORTED;
		sortFirm = org.primefaces.model.SortOrder.UNSORTED;
		if (sortNature.equals(org.primefaces.model.SortOrder.ASCENDING)) {
			setSortNature(org.primefaces.model.SortOrder.DESCENDING);
		} else {
			setSortNature(org.primefaces.model.SortOrder.ASCENDING);
		}
	}

	public void sortByFirm() {
		logger.info("[sortByFirm()]-------------------Inside sortByFirm() method()---------------------------------");
		sortPhoneNumber = org.primefaces.model.SortOrder.UNSORTED;
		sortCountryr = org.primefaces.model.SortOrder.UNSORTED;
		sortNature = org.primefaces.model.SortOrder.UNSORTED;
		if (sortFirm.equals(org.primefaces.model.SortOrder.ASCENDING)) {
			setSortFirm(org.primefaces.model.SortOrder.DESCENDING);
		} else {
			setSortFirm(org.primefaces.model.SortOrder.DESCENDING);
		}
	}

	

	public org.primefaces.model.SortOrder getSortPhoneNumber() {
		return sortPhoneNumber;
	}

	public void setSortPhoneNumber(org.primefaces.model.SortOrder sortPhoneNumber) {
		this.sortPhoneNumber = sortPhoneNumber;
	}

	public org.primefaces.model.SortOrder getSortCountryr() {
		return sortCountryr;
	}

	public void setSortCountryr(org.primefaces.model.SortOrder sortCountryr) {
		this.sortCountryr = sortCountryr;
	}

	/**
	 * @return the vendorFilter
	 */
	public List<VendorViewFormMB> getVendorFilter() {
		return vendorFilter;
	}

	/**
	 * @param vendorFilter
	 *            the vendorFilter to set
	 */
	public void setVendorFilter(List<VendorViewFormMB> vendorFilter) {
		this.vendorFilter = vendorFilter;
	}

	public void vendorviews() {
		logger.info("[vendorviews()]-------------------Inside vendorviews() method()---------------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("vendorViewForm",
				options, null);
		try {

			setCities("");
			DemoController controller = null;
			ven1.clear();
			flag = "none";
			vendorlist1 = null;
			flag1 = "none";
			frimName = "";
			vendorname = "";
			setValidate(null);
			vendor.setFirmName(frimName);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			vendorlist = controller.vendorView(vendor);
			setVendorlist(vendor.getVendorlist());
			flag = "none";
			flag1 = "none";
			if (vendorlist.size() > 0) {
				for (int i = 0; i < vendorlist.size(); i++) {
					VendorViewFormMB vens = new VendorViewFormMB();
					vens.setVendorPhoneNumber(vendorlist.get(i)
							.getVendorPhoneNumber());
					vens.setNatureofbusiness(vendorlist.get(i)
							.getNatureOfBusiness());
					vens.setPhn(new BigDecimal(vendorlist.get(i).getFirmName()));
					vens.setCity(vendorlist.get(i).getCityName());
					vens.setCountry1(vendorlist.get(i).getI0028().getName());
					ven1.add(vens);
				}
				flag = "1";
			} else {
				flag1 = "1";

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		finally {
			firmName = null;
			firmRegistrationNumber = "";
			address = null;
			vendorTelephoneNumber = null;
			vendorPhoneNumber = null;
			country_ID = null;
			email_ID_vendor = null;
			faxVendor = null;
			peresonIncharge = null;
			nature_of_business_id = null;
			firmTypeStandard = null;
			frim_ID = null;
			vendor_Id = 0;
			natureofbusiness = null;
			otherfirmtype = null;
		}
	}

	// prema begin 02/05/2016 vendor dialog box close
	public void vendorviewclose() {
		logger.info("[vendorviewclose()]-------------------Inside vendorviewclose() method()---------------------------------");
		RequestContext.getCurrentInstance().closeDialog("vendorViewForm");
	}

	// prema end 02/05/2016

	public void vendorView2() {
		logger.info("[vendorview2()]-------------------Inside vendorview2() method()---------------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 230);
		options.put("contentWidth", 430);
		RequestContext.getCurrentInstance().openDialog("vendorView", options,
				null);
		vendorView1();
	}

	public void vendorModify() {
		logger.info("[vendorModify()]-------------------Inside vendorModify() method()---------------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 330);
		options.put("contentWidth", 650);
		RequestContext.getCurrentInstance().openDialog("vendorViewUpdate",
				options, null);
		vendorUpdate();

	}

	public String vendormodifyclose() {
		logger.info("[vendormodifyclose()]-------------------Inside vendormodifyclose() method()---------------------------------");
		RequestContext.getCurrentInstance().execute("alert('hai');");
		RequestContext.getCurrentInstance().closeDialog("vendorViewForm");

		return "";
	}

	public String vendorupdate() {
		logger.info("[vendorupdate()]-------------------Inside vendorupdate() method()---------------------------------");
		vendorUpdate();
		success();
		return "";

	}

	public List<Vendor> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<Vendor> filterList) {
		this.filterList = filterList;
	}
	public void onrowselect(final SelectEvent event){
		logger.info("[vendorupdate()]-------------------Inside vendorupdate() method()---------------------------------");
		vendorPhoneNumber = ((VendorViewFormMB) event.getObject()).getVendorPhoneNumber();
		vendorUpdate();
	}
	
	/* Stanley */
	public void checkBoxValueChange(ValueChangeEvent vc){
		logger.info("[checkBoxValueChange()]-------------------Inside checkBoxValueChange() method()---------------------------------");
		try{
			String check=vc.getNewValue().toString();
			if(check.equals("true")){
				setHiddenFlag(true);
				setShowFlag(false);
				setAddress1(address);   
				setCity1(city);  
				setCountry_ID1(country_ID);  
				setPost1(post);   
				setState1(state);  
			}else{
				setHiddenFlag(false);
				setShowFlag(true);
				setAddress1("");   
				setCity1("");  
				setCountry_ID1("");  
				setPost1("");  
				setState1("");  
			}
		}catch(Exception e){
			logger.error("Inside Exception"+e.getMessage());
		}
	}
	
	public void selectState(ValueChangeEvent vc){
		logger.info("[selectState()]-------------------Inside selectState() method()---------------------------------");
		String country = "";
		ApplicationContext ctx = null;
		DemoController controller = null;
		try{
			country = vc.getNewValue().toString();
			   ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			   controller = (DemoController) ctx.getBean("controller");
			   stateList=new ArrayList<String>();
			   stateList=controller.getstatelist(country); 
			   System.out.println("stateist------------> "+stateList.size());
			   if(country.equalsIgnoreCase("India")) setCode("+91");
			   else if(country.equalsIgnoreCase("Indonesia")) setCode("+62");
			   else if(country.equalsIgnoreCase("Malesia")) setCode("+60");
			   else if(country.equalsIgnoreCase("Singapore")) setCode("+65");
			   else if(country.equalsIgnoreCase("UAE")) setCode("+971");
		}catch(Exception e){
			logger.error("Inside Exception"+e.getMessage());
		}
		
	}

	public void selectState1(ValueChangeEvent vc){
		logger.info("[selectState1()]-------------------Inside selectState1() method()---------------------------------");
		String country = "";
		ApplicationContext ctx = null;
		DemoController controller = null;
		try{
			country = vc.getNewValue().toString();
			   ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			   controller = (DemoController) ctx.getBean("controller");
			   stateList1=new ArrayList<String>();
			   stateList1=controller.getstatelist(country); 
			   System.out.println("stateist----------->"+stateList1.size());
			   if(country.equalsIgnoreCase("India")) setCode("+91");
			   else if(country.equalsIgnoreCase("Indonesia")) setCode("+62");
			   else if(country.equalsIgnoreCase("Malesia")) setCode("+60");
			   else if(country.equalsIgnoreCase("Singapore")) setCode("+65");
			   else if(country.equalsIgnoreCase("UAE")) setCode("+971");
		}catch(Exception e){
			logger.error("Inside Exception"+e.getMessage());
		}
	}
	
	//vendor approval
	public String vendorApproval(){
		logger.info("[vendorApproval()]-------------------Inside vendorApproval() method()---------------------------------");
		String status="";DemoController controller = null;
		int count=0;setValidate("");String approvalStatus="";
		try{
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			for (int i = 0; i < ven1.size(); i++) {
				if(ven1.get(i).isVendorCheck()==true){
					count++;
				}
			}
			if(count==0){
				throw new Exception("Please Choose atleast one row for Approval.");
			}else{
				setValidate("");
				approvalStatus="Approve";
				status=controller.vendorApproval(ven1,approvalStatus);
				if(status.equalsIgnoreCase("Success")){
					RequestContext.getCurrentInstance().execute("PF('approvalConfirm').show()");
				}
			}
		}catch(Exception e){
			setValidate(e.getMessage());
			logger.warn("Inside Exception"+e);
		}finally{
		}
		return "";
	}
	
	//vendor Reject
	public String vendorReject(){
		logger.info("[vendorReject()]-------------------Inside vendorReject() method()---------------------------------");
		String status="";DemoController controller = null;
		int count=0;setValidate("");
		try{
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			for (int i = 0; i < ven1.size(); i++) {
				if(ven1.get(i).isVendorCheck()==true){
					count++;
				}
			}
			if(count==0){
				throw new Exception("Please Choose atleast one row for Reject.");
			}else{
				setValidate("");
				approvalStatus="Reject";
				status=controller.vendorApproval(ven1,approvalStatus);
				if(status.equalsIgnoreCase("Success")){
					RequestContext.getCurrentInstance().execute("PF('rejectConfirm').show()");
				}
			}
		}catch(Exception e){
			setValidate(e.getMessage());
			logger.warn("Inside Exception"+e);
		}finally{
		}
		return "";
	}
	
	public String licenceven;
	public Date exDateven;
	public String typeven;
	public String codeven;
	public String payType;
	public String payDays;
	public boolean venflag=true; 
	public String newcash;
	public List<String> daylist=null;
	
	public DemoController getController() {
		return controller;
	}

	public void setController(DemoController controller) {
		this.controller = controller;
	}

	public String getLicenceven() {
		return licenceven;
	}

	public void setLicenceven(String licenceven) {
		this.licenceven = licenceven;
	}

	public Date getExDateven() {
		return exDateven;
	}

	public void setExDateven(Date exDateven) {
		this.exDateven = exDateven;
	}

	public String getTypeven() {
		return typeven;
	}

	public void setTypeven(String typeven) {
		this.typeven = typeven;
	}

	public String getCodeven() {
		return codeven;
	}

	public void setCodeven(String codeven) {
		this.codeven = codeven;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayDays() {
		return payDays;
	}

	public void setPayDays(String payDays) {
		this.payDays = payDays;
	}

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

	public List<String> getDaylist() {
		return daylist;
	}

	public void setDaylist(List<String> daylist) {
		this.daylist = daylist;
	}

	public String vendorView1() {
		logger.info("[vendorView1()]-------------------Inside vendorView1() method()---------------------------------");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		xx = null;
		try {
			setValidate("");
			flag = "none";
			vendor.setClientID(clientID);
			vendor.setVendorPhoneNumber(String.valueOf(vendor_Id));
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			xx = controller.vendorUpdate(vendor, xx);
			if (xx.size() > 0) {
				int i = 0;
				logger.debug(xx.get(i).getAddress());
			}
			if (xx.size() > 0) {
				int i = 0;
				String s = xx.get(i).getI0028().getName();
				firmName = xx.get(0).getFirmName();
				firmRegistrationNumber = xx.get(0).getFirmRegistrationNumber();
				address = xx.get(0).getAddress();
				vendorTelephoneNumber = xx.get(0).getVendorTelephoneNumber();
				vendorPhoneNumber = xx.get(0).getVendorPhoneNumber();
				country_ID = xx.get(0).getI0028().getName();
				email_ID_vendor = xx.get(0).getEmail_ID_vendor();
				faxVendor = xx.get(0).getFaxVendor();
				peresonIncharge = xx.get(0).getPeresonIncharge();
				firmTypeStandard = xx.get(0).getFirmType();
				natureofbusiness = xx.get(0).getNatureOfBusiness();
				otherfirmtype = xx.get(0).getOtherFirmType();
				vendor_Id = xx.get(0).getVendor_ID();
				city = xx.get(0).getPresentcity();
				website = xx.get(0).getWebsiteAny();
				note = xx.get(0).getNotesAny();
				country_ID = xx.get(0).getI0028().getName();
				address1=xx.get(0).getPermenantaddress();
				city1=xx.get(0).getPermenantcity();
				country_ID1=xx.get(0).getPermenantcountry();
				post=xx.get(0).getPresentpostalcode();
				post1=xx.get(0).getPermenantpostalcode();
				state1=xx.get(0).getPermenantstate();
				state=xx.get(0).getPresentstate();
				companyven=xx.get(0).getCompany();
				licenceven=xx.get(0).getVendorLicenceNumber();
				exDateven=xx.get(0).getVendorExpireDate();
				typeven=xx.get(0).getVendorType();
				codeven=xx.get(0).getVendorCode();
				payType=xx.get(0).getPaymentType(); 
				try{
					if(!payType.equalsIgnoreCase("") || !payType.equalsIgnoreCase(null) ){
						if(payType.equals("Credit")){
							payDays=xx.get(0).getPaymentCash_ID().getPaymentType();
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if (xx.size() == 0) {
				throw new DemoException("* enter correct firm Name");
			}
			return "";
		} catch (DemoException i) {
			logger.warn("Inside Exception"+i);
			i.printStackTrace();
			setValidate(i.getMessage());
			return "failurev";
		} finally {

		}

	}
	
	public String vendorUpdate() {
		System.out.println("[vendorUpdate()]-------------------Inside vendorUpdate() method()---------------------------------");
		xx = null;
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			flag = "none";
			setValidate("");
			// vendor.setFirmName(firmName);
			vendor.setClientID(clientID);vendor.setUserID(userID);
			vendor.setVendorPhoneNumber(String.valueOf(vendor_Id));
			System.out.println("vendor id----"+vendor.getVendorPhoneNumber()+"clientid---"+vendor.getClientID()+"userid---"+vendor.getUserID());
			logger.debug(vendor.getVendorPhoneNumber());
			logger.debug(vendor.getFirmName());
			logger.debug("MB");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			xx = controller.vendorUpdate(vendor, xx);
			if (xx.size() > 0) {
				int i = 0;
			}
			if (xx.size() > 0) {
				int i = 0;
				String s = xx.get(i).getI0028().getName();
				firmName = xx.get(0).getFirmName();
				firmRegistrationNumber = xx.get(0).getFirmRegistrationNumber();
				address = xx.get(0).getAddress();
				vendorTelephoneNumber = xx.get(0).getVendorTelephoneNumber();
				vendorPhoneNumber = xx.get(0).getVendorPhoneNumber();
				country_ID = xx.get(0).getI0028().getName();
				email_ID_vendor = xx.get(0).getEmail_ID_vendor();
				faxVendor = xx.get(0).getFaxVendor();
				peresonIncharge = xx.get(0).getPeresonIncharge();
				firmTypeStandard = xx.get(0).getFirmType();
				natureofbusiness = xx.get(0).getNatureOfBusiness();
				otherfirmtype = xx.get(0).getOtherFirmType();
				vendor_Id = xx.get(0).getVendor_ID();
				city = xx.get(0).getPresentcity();
				state = xx.get(0).getStateName(); 
				website = xx.get(0).getWebsiteAny();
				note = xx.get(0).getNotesAny();
				country_ID = xx.get(0).getI0028().getName();
				state=xx.get(0).getPresentstate();
				address1=xx.get(0).getPermenantaddress();
				city1=xx.get(0).getPermenantcity();
				country_ID1=xx.get(0).getPermenantcountry();
				post=xx.get(0).getPresentpostalcode();
				post1=xx.get(0).getPermenantpostalcode();
				state1=xx.get(0).getPermenantstate(); 
				companyven=xx.get(0).getCompany();
				licenceven=xx.get(0).getVendorLicenceNumber();
				exDateven=xx.get(0).getVendorExpireDate();
				typeven=xx.get(0).getVendorType();
				codeven=xx.get(0).getVendorCode();
				payType=xx.get(0).getPaymentType();
				try{
					if(!payType.equalsIgnoreCase("")|| !(payType.equalsIgnoreCase(null))){
						if(payType.equals("Credit")){
							daylist=new ArrayList<String>();
							payDays=xx.get(0).getPaymentCash_ID().getPaymentType();
							daylist=controller.getpaytype();
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				stateList=new ArrayList<String>();
		 		stateList=controller.getstatelist(xx.get(0).getPresentcountry());
		 		stateList1=new ArrayList<String>();
				stateList1=controller.getstatelist(xx.get(0).getPermenantcountry()); 
			}
			if (xx.size() == 0) {
				throw new DemoException("* enter correct firm Name");
			}
			vendorRegisterFormMB.loadDialogBox();
			return "";
		} catch (DemoException i) {
			logger.warn("Inside Exception"+i);
			i.printStackTrace();
			setValidate(i.getMessage());
			return "";
		}

	}
	
	public String updateModify() {
		logger.info("[updateModify()]-------------------Inside updateModify() method()---------------------------------");
		validate = "";
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			/*if (city.equalsIgnoreCase("")) {
				throw new DemoException("Enter the City");
			} else if (city != null || !city.equalsIgnoreCase("")) {
				if (!city.matches("^[a-zA-Z ]{1,30}$")) {
					throw new DemoException("City Name Should be in alphabet");
				}
			}
			if (peresonIncharge.equalsIgnoreCase("")) {
				throw new DemoException("Enter the Person Name");
			} else if (!peresonIncharge.equalsIgnoreCase("")) {
				if (!peresonIncharge.matches("^[a-zA-Z ]{1,30}$")) {
					throw new DemoException("Enter Valid ER");
				}

			}*/
			if(firmName.equalsIgnoreCase("")) {
				throw new DemoException("Enter TelephoneNumber");
			} else if (firmName != null || !firmName.equalsIgnoreCase("")) {
				if (!firmName.matches("^\\d+(\\.\\d+)*$")) {
					throw new DemoException(
							"Enter Valid TelePhone Number");
				}
			}

			if (country_ID.equalsIgnoreCase("")) {
				throw new DemoException("Choose the Country");
			}
			/*if (address.equalsIgnoreCase("")) {
				throw new DemoException("Address should not be Blank");
			}
			if (payType.equalsIgnoreCase("select")) {
			    throw new DemoException("Please Choose Payment Type");
			}
			if(payType.equalsIgnoreCase("Credit")){
				if(payDays.equalsIgnoreCase("select")){
					throw new DemoException("Please Choose Payment Days");
				}
			}if(!faxVendor.equals("")){
				if(!faxVendor.matches("^\\d+(\\.\\d+)*$")){
					throw new DemoException("Please Enter valid Fax Number");
				}
			}if(licenceven!=null){
				if(!licenceven.matches("^\\d+(\\.\\d+)*$")){
					throw new DemoException("Please Enter valid Licence Number");
				}
			}*/
			if(email_ID_vendor.equalsIgnoreCase("")) {
				throw new DemoException("Enter Mail ID");
			}else if(!email_ID_vendor.equals("")){
				if(!email_ID_vendor.matches("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$")){
					throw new DemoException("Please Enter the Valid Email ID(Eg:aa@bb.cc)");
				}
			}
			vendor.setVendor_Id(vendor_Id);
			vendor.setFirmName(firmName);
			vendor.setFirmRegistrationNumber(firmRegistrationNumber);
			
			vendor.setVendorTelephoneNumber(vendorTelephoneNumber);
			vendor.setVendorPhoneNumber(vendorPhoneNumber);
			
			vendor.setEmail_ID_vendor(email_ID_vendor);
			vendor.setFaxVendor(faxVendor);
			vendor.setPeresonIncharge(peresonIncharge);
			// vendor.setNature_of_business_id(nature_of_business_id);
			vendor.setFirmTypeStandard(firmTypeStandard);
			vendor.setOtherfirmtype(otherfirmtype);
			vendor.setNatureofbusiness(natureofbusiness);
			
			
			vendor.setWebsite(website);
			vendor.setNote(note);
			vendor.setVenCompany(companyven);
			
			vendor.setAddress(address); 
			vendor.setVenAddress1(address1); 
			vendor.setVenCity(city); 
			vendor.setVenCity1(city1); 
			vendor.setCountry_ID(country_ID);
			vendor.setVenCountry1(country_ID1); 
			vendor.setVenPostalcode(post); 
			vendor.setVenPostalcode1(post1); 
			vendor.setVenState(state); 
			vendor.setVenState1(state1); 
			
			vendor.setVenLicence(licenceven); 
			vendor.setVenExdate(exDateven); 
			vendor.setVenType(typeven); 
			vendor.setVenCode(codeven); 
			vendor.setPayType(payType); 
			vendor.setPayDays(payDays);
			
			vendor.setClientID(clientID);
			vendor.setUserID(userID);
			
			System.out.println("in vendor--------------------->"+vendor.getVenExdate()); 
			
			// vendor.setFrim_ID(frim_ID);
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.vendorModify(vendor, xx);
			RequestContext.getCurrentInstance().execute("PF('vendorDialogModify').hide();");
			RequestContext.getCurrentInstance().execute("PF('Vuc').show();");
			return "";
		}

		catch (DemoException e) {
			setValidate(e.getMessage());
			logger.debug("log:" + e.getMessage());
			return "vendorViewfailure3";
		}
	}
	
	public void venTypes(ValueChangeEvent v){
		logger.info("[venTypes()]-------------------Inside venTypes() method()---------------------------------");
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
			setPayType(valueven);  
			System.out.println("---------"+valueven);
			//RequestContext.getCurrentInstance().update("center_content");
		}catch(Exception e){
			logger.warn("Inside Exception"+e);
		}
		
	}
	public void dialog(){
		logger.info("[dialog()]-------------------Inside dialog() method()---------------------------------");
		System.out.println("in ++");
		  try{
		    setValidate("");
		    setNewcash("");
		    RequestContext.getCurrentInstance().execute("PF('confirm2').show();"); 
		    RequestContext.getCurrentInstance().execute("PF('vendorDialogModify').show();"); 
		  }catch(Exception e){
			  logger.warn("Inside Exception"+e);
		  }
		 }
	public String validate1;

	public String getValidate1() {
		return validate1;
	}

	public void setValidate1(String validate1) {
		this.validate1 = validate1;
	}
	
	public String vendorRegistercash(){
		logger.info("[vendorRegistercash()]-------------------Inside vendorRegistercash() method()---------------------------------");
		  DemoController controller=null;
		  String status="";
		  setValidate1(null);
		  try{
		   ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		   controller = (DemoController) ctx.getBean("controller");
		    vendor.setNewcash(newcash+" days"); 
		    status=controller.setcash(vendor); 
		    setPayDays(vendor.getNewcash()); 
		     if("Success".equalsIgnoreCase(status)){
		     RequestContext.getCurrentInstance().execute("PF('confirm2').hide();");
		     daylist=controller.getpaytype();     
		    RequestContext.getCurrentInstance().execute("PF('vendorDialogModify').show();");
		   }else if("exist".equalsIgnoreCase(status)){
			   setValidate1("Already Exist this Day");
		   }
		     RequestContext.getCurrentInstance().update("center_content:venModify");
		  }catch(Exception e){
			  logger.warn("Inside Exception"+e);
		  }
		  return "";
		 }
	
/*stanley changes*/
	public String serialno;
	
	
	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String success() {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		logger.info("[success()]-------------------Inside success() method()---------------------------------");
		try {
			setCities("");int count=0;
			DemoController controller = null;
			setFilterList1(null);
			ven1.clear();
			flag = "none";
			vendorlist1 = null;
			flag1 = "none";
			frimName = "";
			vendorname = "";
			setValidate(null);
			
			vendor.setFirmName(frimName);
			vendor.setApproval(approval);
			
			vendor.setClientID(clientID);
			vendor.setUserID(userID);
			vendor.setUserType(userType);
			
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			vendorlist = controller.vendorView(vendor);
			
			setVendorlist(vendor.getVendorlist());
			flag = "none";
			flag1 = "none";
			
			if (vendorlist.size() > 0) {
				for (int i = 0; i < vendorlist.size(); i++) {
					VendorViewFormMB vens = new VendorViewFormMB();
					vens.setSerialno(String.valueOf(i+1));   
					vens.setVendorPhoneNumber(vendorlist.get(i)
							.getVendorPhoneNumber());
					vens.setNatureofbusiness(vendorlist.get(i)
							.getNatureOfBusiness());
					vens.setPhonenumberv(vendorlist.get(i).getFirmName());
					vens.setCity(vendorlist.get(i).getPresentcity());
					vens.setPeresonIncharge(vendorlist.get(i).getPeresonIncharge());
					vens.setCountry1(vendorlist.get(i).getI0028().getName());
					vens.setState(vendorlist.get(i).getPresentstate());
					vens.setEmail_ID_vendor(vendorlist.get(i).getEmail_ID_vendor());
					vens.setCompanyven(vendorlist.get(i).getCompany());
					vens.setPost(vendorlist.get(i).getPresentpostalcode());
					vens.setApprovalStatus(vendorlist.get(i).getApprovalStatus());
					vens.setVendor_Id(vendorlist.get(i).getVendor_ID());
					ven1.add(vens);
					/*if(vendorlist.get(i).getUserID().getUserNo()==Integer.parseInt(userID)){
						ven1.add(vens);
					}else{
						if(vendorlist.get(i).getApprovalStatus().equals("draft")){
							ven1.add(vens);
						}
					}*/					
					logger.debug("" + ven1.size());
					if(vendorlist.get(i).getApprovalStatus().equals("draft")){
						count++;
					}
				}
				if(count==0){
					approveButtonFlag="none";
				}else{
					approveButtonFlag="1";
				}
				flag = "1";
			} else {
				flag1 = "1";

			}
			logger.info("[success()]-----------Outside success () method()---------------------");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Inside Exception", e);
			logger.error("inside exception ",e);
		}
		finally {
			firmName = null;
			firmRegistrationNumber = "";
			address = null;
			vendorTelephoneNumber = null;
			vendorPhoneNumber = null;
			Phonenumberv = null;
			country_ID = null;
			email_ID_vendor = null;
			faxVendor = null;
			peresonIncharge = null;
			nature_of_business_id = null;
			firmTypeStandard = null;
			frim_ID = null;
			vendor_Id = 0;
			natureofbusiness = null;
			otherfirmtype = null;
			
		}
		return "";
	}
}
