package com.inacsys.managedBean;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.ATransaction;
import com.inacsys.domain.EmployeeDetail;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.Designation;
import com.inacsys.shared.Employee;
import com.inacsys.shared.Qualification;
import com.inacsys.util.CommonValidate;

@ManagedBean(name = "employeeRegistrationFormMB")
public class EmployeeRegistrationFormMB {
	private static Logger logger = Logger
			.getLogger(EmployeeRegistrationFormMB.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public String name;
	public String employeeid;
	public Date dob;
	public String dob1;
	public String mailid;
	public String gender;
	public String phno;
	public String address;
	public String qualification;
	public Date joindate;
	public String joindate1;
	public String basicsalary;
	public String designation;
	public String description;
	public Date entrydate;
	public String entrydate1;
	public String validate;
	public String bankName;
	public String accNo;
	public String code;
	public String country;
	public String state;
	public String streetname;
	public String postalcode;
	public String city;
	public String present_code;
	public String present_country;
	public String present_state;
	public String present_streetname;
	public String present_postalcode;
	public String present_city;
	public String present_phno;
	public String course;
	public String yearofpassing;
	public String university;
	public String medium;
	public String certificate_path;
	public String expCertificate_path;
	public String companyName;
	public String fromDate;
	public String toDate;
	public String contactName;
	public String contactNumber;
	private UploadedFile empexpFile;
	private UploadedFile empQual_File;
	private UploadedFile empFile;
	private UploadedFile passportFile;
	private UploadedFile emiratesFile;
	private UploadedFile licenceFile;
	private UploadedFile otherFile;
	public Date passport_date;
	public Date emirates_date;
	public Date licence_date;
	public String otherFilename;
	private boolean hiddenFlag=false;
	private boolean showFlag=true;
	List<String> stateist=new ArrayList<String>();
	List<String> stateist1=new ArrayList<String>();
	private String currency;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public UploadedFile getPassportFile() {
		return passportFile;
	}

	public void setPassportFile(UploadedFile passportFile) {
		this.passportFile = passportFile;
	}

	public UploadedFile getEmiratesFile() {
		return emiratesFile;
	}

	public void setEmiratesFile(UploadedFile emiratesFile) {
		this.emiratesFile = emiratesFile;
	}

	public UploadedFile getLicenceFile() {
		return licenceFile;
	}

	public void setLicenceFile(UploadedFile licenceFile) {
		this.licenceFile = licenceFile;
	}

	public UploadedFile getOtherFile() {
		return otherFile;
	}

	public void setOtherFile(UploadedFile otherFile) {
		this.otherFile = otherFile;
	}

	public Date getPassport_date() {
		return passport_date;
	}

	public void setPassport_date(Date passport_date) {
		this.passport_date = passport_date;
	}

	public Date getEmirates_date() {
		return emirates_date;
	}

	public void setEmirates_date(Date emirates_date) {
		this.emirates_date = emirates_date;
	}

	public Date getLicence_date() {
		return licence_date;
	}

	public void setLicence_date(Date licence_date) {
		this.licence_date = licence_date;
	}

	public String getOtherFilename() {
		return otherFilename;
	}

	public void setOtherFilename(String otherFilename) {
		this.otherFilename = otherFilename;
	}

	public List<String> getStateist1() {
		return stateist1;
	}

	public void setStateist1(List<String> stateist1) {
		this.stateist1 = stateist1;
	}

	public boolean tick=false;
	
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

	public UploadedFile getEmpexpFile() {
		return empexpFile;
	}

	public void setEmpexpFile(UploadedFile empexpFile) {
		this.empexpFile = empexpFile;
	}

	public UploadedFile getEmpQual_File() {
		return empQual_File;
	}

	public void setEmpQual_File(UploadedFile empQual_File) {
		this.empQual_File = empQual_File;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public EmployeeDetail getEmpdetail() {
		return empdetail;
	}

	public void setEmpdetail(EmployeeDetail empdetail) {
		this.empdetail = empdetail;
	}

	public UploadedFile getEmpFile() {
		return empFile;
	}

	public void setEmpFile(UploadedFile empFile) {
		this.empFile = empFile;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getYearofpassing() {
		return yearofpassing;
	}

	public void setYearofpassing(String yearofpassing) {
		this.yearofpassing = yearofpassing;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getCertificate_path() {
		return certificate_path;
	}

	public void setCertificate_path(String certificate_path) {
		this.certificate_path = certificate_path;
	}

	public String getExpCertificate_path() {
		return expCertificate_path;
	}

	public void setExpCertificate_path(String expCertificate_path) {
		this.expCertificate_path = expCertificate_path;
	}

	public boolean isTick() {
		return tick;
	}

	public void setTick(boolean tick) {
		this.tick = tick;
	}

	public String getPresent_code() {
		return present_code;
	}

	public void setPresent_code(String present_code) {
		this.present_code = present_code;
	}

	public String getPresent_country() {
		return present_country;
	}

	public void setPresent_country(String present_country) {
		this.present_country = present_country;
	}

	public String getPresent_state() {
		return present_state;
	}

	public void setPresent_state(String present_state) {
		this.present_state = present_state;
	}

	public String getPresent_streetname() {
		return present_streetname;
	}

	public void setPresent_streetname(String present_streetname) {
		this.present_streetname = present_streetname;
	}

	public String getPresent_postalcode() {
		return present_postalcode;
	}

	public void setPresent_postalcode(String present_postalcode) {
		this.present_postalcode = present_postalcode;
	}

	public String getPresent_city() {
		return present_city;
	}

	public void setPresent_city(String present_city) {
		this.present_city = present_city;
	}

	public String getPresent_phno() {
		return present_phno;
	}

	public void setPresent_phno(String present_phno) {
		this.present_phno = present_phno;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreetname() {
		return streetname;
	}

	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<String> getStateist() {
		return stateist;
	}

	public void setStateist(List<String> stateist) {
		this.stateist = stateist;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String regid;
	public List<Qualification> qualificate = null;
	public List<Designation> designate = null;
	public String payroll;
	public List<String> emploeid = null;
	public List<String> emploename = null;
	public String freelancer;

	public String getFreelancer() {
		return freelancer;
	}

	public void setFreelancer(String freelancer) {
		this.freelancer = freelancer;
	}

	public String getDob1() {
		return dob1;
	}

	public void setDob1(String dob1) {
		this.dob1 = dob1;
	}

	public String getJoindate1() {
		return joindate1;
	}

	public void setJoindate1(String joindate1) {
		this.joindate1 = joindate1;
	}

	public String getEntrydate1() {
		return entrydate1;
	}

	public void setEntrydate1(String entrydate1) {
		this.entrydate1 = entrydate1;
	}

	public String getName() {
		return name;
	}

	public List<String> getEmploeid() {
		return emploeid;
	}

	public void setEmploeid(List<String> emploeid) {
		this.emploeid = emploeid;
	}

	public List<String> getEmploename() {
		return emploename;
	}

	public void setEmploename(List<String> emploename) {
		this.emploename = emploename;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public String getBasicsalary() {
		return basicsalary;
	}

	public void setBasicsalary(String basicsalary) {
		this.basicsalary = basicsalary;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getRegid() {
		return regid;
	}

	public void setRegid(String regid) {
		this.regid = regid;
	}

	public List<Qualification> getQualificate() {
		return qualificate;
	}

	public void setQualificate(List<Qualification> qualificate) {
		this.qualificate = qualificate;
	}

	public List<Designation> getDesignate() {
		return designate;
	}

	public void setDesignate(List<Designation> designate) {
		this.designate = designate;
	}

	public String getPayroll() {
		return payroll;
	}

	public void setPayroll(String payroll) {
		this.payroll = payroll;
	}

	private List<EmployeeDetail> qualificationlist=null;
	private List<EmployeeDetail> educationlist=null;
	

	public List<EmployeeDetail> getQualificationlist() {
		return qualificationlist;
	}

	public void setQualificationlist(List<EmployeeDetail> qualificationlist) {
		this.qualificationlist = qualificationlist;
	}

	public List<EmployeeDetail> getEducationlist() {
		return educationlist;
	}

	public void setEducationlist(List<EmployeeDetail> educationlist) {
		this.educationlist = educationlist;
	}

	public String onload() {
		logger.info("[onload()] --------------- Inside onload() method() ------------------------");
		setQualificationlist(null);setEducationlist(null);
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			qualificationlist=new ArrayList<EmployeeDetail>();
			educationlist=new ArrayList<EmployeeDetail>();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setQualificate(controller.qualificationInfo(qualificate));
			String empID=controller.getempID();
			setEmployeeid(empID);
			for (int i = 0; i < 3; i++) {
				EmployeeDetail empdetail=new EmployeeDetail();
				empdetail.setQualification("");
				empdetail.setSerialNo(String.valueOf(i+1));
				empdetail.setSerialNo1(String.valueOf(i+1));
				qualificationlist.add(empdetail);
				educationlist.add(empdetail);
			}
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		} finally {
			validate = "";
			name = "";
			dob = null;
			mailid = "";
			gender = "";
			phno = "";
			address = "";
			qualification = "";
			entrydate = null;
			joindate = null;
			basicsalary = "";
			designation = "";
			description = "";
			freelancer = "";
			bankName="";
			accNo="";
			streetname="";
			city="";
			state="";
			country="";
			postalcode="";
			phno="";
			code="";
			present_streetname="";
			present_city="";
			present_state="";
			present_country="";
			present_phno="";
			present_code="";
			present_postalcode="";
			passport_date=null;
			emirates_date=null;
			licence_date=null;
			otherFilename="";
			setTick(false);
		}

	}

	public String reset(){
		logger.info("[reset()] --------------- Inside reset() method() ------------------------");
		onload();
		return "";
	}
	/****/
	
	private boolean validate(boolean flag) {
		logger.info("[validate()] --------------- Inside validate() method() ------------------------");
		boolean valid=true;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		if (StringUtils.isEmpty(name)) {
			if(flag){
				fieldName = CommonValidate.findComponentInRoot("emp_Name")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Enter the Employee Name"));
			}
			valid = false;
		} else if(!StringUtils.isEmpty(name)){
			if(!CommonValidate.validateName(name)){
				if(flag){
					fieldName = CommonValidate.findComponentInRoot("emp_Name").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter valid Employee Name"));
					}
					valid = false;
			}
		}
		
		if(dob==null){
			if(flag){
				fieldName = CommonValidate.findComponentInRoot("emp_Dob").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter the Employee DOB"));
				}
				valid = false;
		}
		
		if(empFile==null){
			if(flag){
				fieldName = CommonValidate.findComponentInRoot("TPhoto").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Upload the Photo"));
				}
				valid = false;
		}
		if (StringUtils.isEmpty(mailid)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_Mail").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Enter the Mail ID"));
			}
			valid = false;
		} else if(!StringUtils.isEmpty(mailid)){
			if(!CommonValidate.validateEmail(mailid)){
				if(flag){
					fieldName = CommonValidate.findComponentInRoot("emp_Mail").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter valid Mail ID"));
					}
					valid = false;
			}
		}
		if (StringUtils.isEmpty(gender)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_Gender").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Choose the Gender"));
			}
			valid = false;
		} 
		
		if (StringUtils.isEmpty(designation)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_Desgin").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Enter Employee Designation"));
			}
			valid = false;
		} else if(!StringUtils.isEmpty(designation)){
			if(!CommonValidate.validateName(designation)){
				if(flag){
					fieldName = CommonValidate.findComponentInRoot("emp_Desgin").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter valid Employee Designation"));
					}
					valid = false;
			}
		}
		
		if (StringUtils.isEmpty(streetname)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_street").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Enter the Door/Stree Name"));
			}
			valid = false;
		} 
		if (StringUtils.isEmpty(present_streetname)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_pstreet").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Enter the Door/Stree Name"));
			}
			valid = false;
		} 
		
		
		if (StringUtils.isEmpty(city)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_city").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Enter the City"));
			}
			valid = false;
		} 
		if (StringUtils.isEmpty(present_city)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_pcity").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Enter the City"));
			}
			valid = false;
		} 
		
		
		if (StringUtils.isEmpty(postalcode)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_postcode").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Enter the Postal Code"));
			}
			valid = false;
		} 
		
		if (StringUtils.isEmpty(present_postalcode)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_ppostcode").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Enter the Postal Code"));
			}
			valid = false;
		} 
		
		if (StringUtils.isEmpty(present_phno)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_pPhone").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Enter the Phone Number"));
			}
			valid = false;
		} else if(!StringUtils.isEmpty(present_phno)){
			if(!CommonValidate.isNumeric(present_phno)){
				if(flag){
					fieldName = CommonValidate.findComponentInRoot("emp_pPhone").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter valid Phone Number"));
					}
					valid = false;
			}
		}
		if (StringUtils.isEmpty(phno)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_Phone").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Enter the Phone Number"));
			}
			valid = false;
		} else if(!StringUtils.isEmpty(phno)){
			if(!CommonValidate.isNumeric(phno)){
				if(flag){
					fieldName = CommonValidate.findComponentInRoot("emp_Phone").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter valid Phone Number"));
					}
					valid = false;
			}
		}
		
		if (StringUtils.isEmpty(present_phno)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_pPhone").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Enter the Phone Number in Present Address"));
			}
			valid = false;
		} else if(!StringUtils.isEmpty(present_phno)){
			if(!CommonValidate.isNumeric(present_phno)){
				if(flag){
					fieldName = CommonValidate.findComponentInRoot("emp_pPhone").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter valid Phone Number in Present Address"));
					}
					valid = false;
			}
		}
		
		if (state==null || StringUtils.isEmpty(state)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_state").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Choose the State"));
			}
			valid = false;
		}
		
		if (present_state==null || StringUtils.isEmpty(present_state)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_pstate").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Choose the State"));
			}
			valid = false;
		}
		if (country==null || StringUtils.isEmpty(country)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_country").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Choose the Country"));
			}
			valid = false;
		}
		
		if (present_country==null || StringUtils.isEmpty(present_country)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_pcountry").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Choose the Country"));
			}
			valid = false;
		}
		
		if (StringUtils.isEmpty(basicsalary)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_BSalary").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Enter Employee Basic Salary"));
			}
			valid = false;
		} else if(!StringUtils.isEmpty(basicsalary)){
			if(!CommonValidate.validateNumber(basicsalary)){
				if(flag){
					fieldName = CommonValidate.findComponentInRoot("emp_BSalary").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter valid Employee Basic Salary"));
					}
					valid = false;
			}
		}
		if (StringUtils.isEmpty(designation)) {
			if(flag){
			fieldName = CommonValidate.findComponentInRoot("emp_Desgin").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Enter Employee Designation"));
			}
			valid = false;
		} else if(!StringUtils.isEmpty(designation)){
			if(!CommonValidate.validateName(designation)){
				if(flag){
					fieldName = CommonValidate.findComponentInRoot("emp_Desgin").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter valid Employee Designation"));
					}
					valid = false;
			}
		}
		if(!StringUtils.isEmpty(bankName)){
			if(!CommonValidate.validateName(bankName)){
				if(flag){
					fieldName = CommonValidate.findComponentInRoot("emp_bank").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter valid Bank Name"));
					}
					valid = false;
			}
		}
		if(!StringUtils.isEmpty(accNo)){
			if(!CommonValidate.validateNumber(accNo)){
				if(flag){
					fieldName = CommonValidate.findComponentInRoot("emp_acc").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter valid Account Number"));
					}
					valid = false;
			}
		}
		if(StringUtils.isEmpty(currency)){
			if(flag){
				fieldName = CommonValidate.findComponentInRoot("curr").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the Currency"));
			}
			valid = false;
		}
		int count=0;
		for (int i = 0; i < qualificationlist.size(); i++) {
			if(qualificationlist.get(i).getCourse()==null){
				count++;
			}
		}
		if(count==qualificationlist.size()){
			fieldName = CommonValidate.findComponentInRoot("dataTable").getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage("Please Enter atleast one qualification"));
			valid = false;
		}else{
			for (int i = 0; i < qualificationlist.size(); i++) {
				try{
					if(qualificationlist.get(i).getCourse()!=null || !qualificationlist.get(i).getCourse().equals("")){
						logger.debug("empty");
					}
					else{
						if(qualificationlist.get(i).getYearofpassing()==null){
							fieldName = CommonValidate.findComponentInRoot("dataTable").getClientId(fc);
							fc.addMessage(fieldName, new FacesMessage("Please Enter year of passing"));
							valid = false;
						}else if(qualificationlist.get(i).getUniversity()==null){
							fieldName = CommonValidate.findComponentInRoot("dataTable").getClientId(fc);
							fc.addMessage(fieldName, new FacesMessage("Please Enter the School/University"));
							valid = false;
						}
						else if(qualificationlist.get(i).getMedium()==null){
							fieldName = CommonValidate.findComponentInRoot("dataTable").getClientId(fc);
							fc.addMessage(fieldName, new FacesMessage("Please Enter the Medium of Instruction"));
							valid = false;
						}
					}
				}catch(Exception e){
					
				}
			}
		}	
		
		for (int i = 0; i < educationlist.size(); i++) {
			logger.debug("company "+educationlist.get(i).getCompanyName());
			try{
				if(educationlist.get(i).getCompanyName().equals("") || educationlist.get(i).getCompanyName()==null){
					logger.debug("empty");
				}
				else{
					logger.debug("from "+educationlist.get(i).getFromDate()+" to "+educationlist.get(i).getToDate()+
							" contact "+educationlist.get(i).getContactName()+" number "+educationlist.get(i).getContactNumber());
					if(educationlist.get(i).getFromDate()==null){
						fieldName = CommonValidate.findComponentInRoot("dataTable1").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Choose the From Date"));
						valid = false;
					}else if(educationlist.get(i).getToDate()==null){
						fieldName = CommonValidate.findComponentInRoot("dataTable1").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter the To Date"));
						valid = false;
					}
					else if(educationlist.get(i).getContactName()==null){
						fieldName = CommonValidate.findComponentInRoot("dataTable1").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter the Contact Name"));
						valid = false;
					}
					else if(educationlist.get(i).getContactNumber()==null){
						fieldName = CommonValidate.findComponentInRoot("dataTable1").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter the Contact Number"));
						valid = false;
					}
				}
			}catch(Exception e){
				
			}			
		}
		System.out.println("efd "+empdetail.getQualificationlist().size());
		return valid;
	}
	public String submit() {
		logger.info("[submit()] --------------- Inside submit() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		String page="aEmployeeRegConfirmationForm";
		try {
			setValidate("");
			if(validate(true)){
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			EmployeeDetail empid = new EmployeeDetail();
			empid.setEmployeeid(employeeid);
			String status = controller.employeeIdSearch(empid);
			return page;
			}
			
		} catch (DemoException ie) {
			setValidate(ie.getMessage());
			return "failure";
		}
		return "";
		

	}

	private String isEmailValid(String mail) {
		logger.info("[isEmailValid()] --------------- Inside isEmailValid() method() ------------------------");
		try {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			CharSequence inputStr = mail;
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

	public String save() {
		logger.info("[save()] --------------- Inside save() method() ------------------------");
		String status="";
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if(validate(true)){
				EmployeeDetail employee = new EmployeeDetail();
				employee.setBasicsalary(basicsalary);
				employee.setDescription(description);
				employee.setDesignation(designation);
				employee.setDob(dob);
				employee.setEmployeeid(employeeid);
				employee.setEntrydate(entrydate);
				employee.setGender(gender);
				employee.setJoindate(joindate);
				employee.setMailid(mailid);
				employee.setAccNo(accNo);
				employee.setBankName(bankName);
				employee.setName(name);
				employee.setEmpFile(empFile);
				employee.setPassportFile(passportFile);
				employee.setEmiratesFile(emiratesFile);
				employee.setLicenceFile(licenceFile);
				employee.setOtherFile(otherFile);
				employee.setPassport_date(passport_date);
				employee.setEmirates_date(emirates_date);
				employee.setLicence_date(licence_date);
				employee.setPhno(phno);
				employee.setPresent_phno(present_phno);
				employee.setQualification(qualification);
				employee.setFreelancer("no");
				employee.setDesignation(designation);
				employee.setStreetname(streetname);
				employee.setPresent_streetname(present_streetname);
				employee.setCity(city);
				employee.setPresent_city(present_city);
				employee.setCountry(country);
				employee.setPresent_country(present_country);
				employee.setState(state);
				employee.setPresent_state(present_state);
				employee.setPostalcode(postalcode);
				employee.setPresent_postalcode(present_postalcode);
				employee.setCode(code);
				employee.setPresent_code(present_code);
				employee.setOtherFilename(otherFilename);
				employee.setDescription(description);
				employee.setDob(dob);
				employee.setEntrydate(entrydate);
				employee.setQualificationlist(qualificationlist);
				employee.setEducationlist(educationlist);
				employee.setCurrency(currency);
				employee.setBaseCurrency(baseCurrency);
				status= controller.employee(employee);
				if(status.equalsIgnoreCase("success")){
					RequestContext.getCurrentInstance().execute(
							"PF('cd').show();");
				}
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} 
		return "";
	}

	public String dropdown() {
		logger.info("[dropdown()] --------------- Inside dropdown() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setEmploeid(controller.employeeId(emploeid));
			Collections.sort(emploeid, String.CASE_INSENSITIVE_ORDER);
			setEmploename(controller.employeeName(emploename));
			Collections.sort(emploename, String.CASE_INSENSITIVE_ORDER);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public String homepage() {
		return "home";
	}

	// prema begin 02/05/2016 dialog box creation for category Registration

	public void employeeform() {
		logger.info("[employeeform()] --------------- Inside employeeform() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 550);
		options.put("contentWidth", 850);

		RequestContext.getCurrentInstance().openDialog(
				"aEmployeeRegistrationForm", options, null);
		onload();
	}

	// prema end 02/05/2016
	public void employeeformclose() {
		RequestContext.getCurrentInstance().closeDialog(
				"aEmployeeRegistrationForm");
	}
	
	public void statechange(ValueChangeEvent v) {
		logger.info("[statechange()] --------------- Inside statechange() method() ------------------------");
		String country = "";
		country = v.getNewValue().toString();
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			stateist=controller.getstatelist(country);
			if(country.equalsIgnoreCase("India")) setCode("+91");
			else if(country.equalsIgnoreCase("Indonesia")) setCode("+62");
			else if(country.equalsIgnoreCase("Malesia")) setCode("+60");
			else if(country.equalsIgnoreCase("Singapore")) setCode("+65");
			else if(country.equalsIgnoreCase("UAE")) setCode("+971");
		} 
		catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}
	
	public void statechange1(ValueChangeEvent v) {
		logger.info("[statechange1()] --------------- Inside statechange1() method() ------------------------");
		String country = "";
		country = v.getNewValue().toString();
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			stateist1=controller.getstatelist(country);
			if(country.equalsIgnoreCase("India")) setPresent_code("+91");
			else if(country.equalsIgnoreCase("Indonesia")) setPresent_code("+62");
			else if(country.equalsIgnoreCase("Malesia")) setPresent_code("+60");
			else if(country.equalsIgnoreCase("Singapore")) setPresent_code("+65");
			else if(country.equalsIgnoreCase("UAE")) setPresent_code("+971");
		} 
		catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public String addQualifications(){
		logger.info("[addQualifications()] --------------- Inside addQualifications() method() ------------------------");
		EmployeeDetail empdetail=new EmployeeDetail();
		empdetail.setSerialNo(String.valueOf(qualificationlist.size()+1));
		qualificationlist.add(empdetail);
		return "";
	}
	
	public String addExperiences(){
		logger.info("[addExperiences()] --------------- Inside addExperiences() method() ------------------------");
		EmployeeDetail empdetail=new EmployeeDetail();
		empdetail.setSerialNo1(String.valueOf(educationlist.size()+1));
		educationlist.add(empdetail);
		return "";
	}

	public void check(ValueChangeEvent v){
		logger.info("[check()] --------------- Inside check() method() ------------------------");
		if(v.getNewValue().toString().equals("true")){
			setHiddenFlag(true);setShowFlag(false);
			setPresent_streetname(streetname);
			setPresent_city(city);
			setPresent_code(code);
			setPresent_country(country);
			setPresent_state(state);
			setPresent_phno(phno);
			setPresent_postalcode(postalcode);
			System.out.println("city "+city);
		}else{
			setHiddenFlag(false);setShowFlag(true);
			setPresent_streetname("");
			setPresent_city("");
			setPresent_code("");
			setPresent_country("");
			setPresent_state("");
			setPresent_phno("");
			setPresent_postalcode("");
		}
	}
	
	 EmployeeDetail empdetail=new EmployeeDetail();
	 
	public String dummyAction(FileUploadEvent event) throws IOException {
		EmployeeDetail empdetail=new EmployeeDetail();
		  this.empFile = event.getFile();
		  empdetail.setEmpFile(event.getFile());
		  FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		  FacesContext.getCurrentInstance().addMessage(null, message);
		 
		  return "";
		 }
	
	
	
	public void imageview(OutputStream out, Object data) throws IOException {
		logger.info("[imageview()] --------------- Inside imageview() method() ------------------------");
		String s ="/home/ec2-user/File_Inacsys/Employee/Photo";
		
		try{			
			BufferedImage img = ImageIO
					.read(new File(s + empdetail.getEmpFile()));
			ImageIO.write(img, "png", out);
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
		
	}
	
	public String serialNo;
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String delteQualify(){
		logger.info("[delteQualify()] --------------- Inside delteQualify() method() ------------------------");
		try{
			qualificationlist.remove(Integer.valueOf(serialNo)-1);
			for (int i = 0; i <qualificationlist.size(); i++) {
				EmployeeDetail empdetail=new EmployeeDetail();
				empdetail.setSerialNo(String.valueOf(i+1));
				empdetail.setCourse(qualificationlist.get(i).getCourse());
				empdetail.setYearofpassing(qualificationlist.get(i).getYearofpassing());
				empdetail.setMedium(qualificationlist.get(i).getMedium());
				empdetail.setUniversity(qualificationlist.get(i).getUniversity());
				qualificationlist.set(i,empdetail);
			}		
			System.out.println("size e "+qualificationlist.size());
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
		return "";
	}
	
	public String delteExperience(){
		logger.info("[delteExperiance()] --------------- Inside delteExperiance() method() ------------------------");
		try{
			educationlist.remove(Integer.valueOf(serialNo)-1);
			for (int i = 0; i <educationlist.size(); i++) {
				EmployeeDetail empdetail=new EmployeeDetail();
				empdetail.setSerialNo1(String.valueOf(i+1));
				empdetail.setCompanyName(educationlist.get(i).getCompanyName());
				empdetail.setFromDate(educationlist.get(i).getFromDate());
				empdetail.setToDate(educationlist.get(i).getToDate());
				empdetail.setContactName(educationlist.get(i).getContactName());
				empdetail.setContactNumber(educationlist.get(i).getContactNumber());
				educationlist.set(i,empdetail);
			}		
		}catch(Exception e){
			e.printStackTrace();
			logger.error("delete exp "+e);
		}
		return "";
	}
	
	public String dummyAction1(FileUploadEvent event) throws IOException {
		logger.info("[dummyAction1()] --------------- Inside dummyAction1() method() ------------------------");
		EmployeeDetail empdetail=new EmployeeDetail();
		  this.empQual_File = event.getFile();
		  empdetail.setEmpQual_File(event.getFile());
		   return "";
		 }
	public String dummyAction2(FileUploadEvent event) throws IOException {
		logger.info("[dummyAction2()] --------------- Inside dummyAction2() method() ------------------------");
		EmployeeDetail empdetail=new EmployeeDetail();
		  this.empexpFile = event.getFile();
		  empdetail.setEmpexpFile(event.getFile());
		  return "";
		 }
	public String dummyAction3(FileUploadEvent event) throws IOException {
		logger.info("[dummyAction3()] --------------- Inside dummyAction3() method() ------------------------");
		  this.passportFile = event.getFile();
		  empdetail.setPassportFile(event.getFile());
		  return "";
		 }
	public String dummyAction4(FileUploadEvent event) throws IOException {
		logger.info("[dummyAction4()] --------------- Inside dummyAction4() method() ------------------------");
		  this.emiratesFile = event.getFile();
		  empdetail.setEmiratesFile(event.getFile());
		  return "";
		 }
	public String dummyAction5(FileUploadEvent event) throws IOException {
		logger.info("[dummyAction5()] --------------- Inside dummyAction5() method() ------------------------");
		  this.licenceFile = event.getFile();
		  empdetail.setLicenceFile(event.getFile());
		  return "";
		 }
	public String dummyAction6(FileUploadEvent event) throws IOException {
		logger.info("[dummyAction6()] --------------- Inside dummyAction6() method() ------------------------");
		  this.otherFile = event.getFile();
		  empdetail.setOtherFile(event.getFile());
		  return "";
		 }
	
	public String saveQual_file(){
		logger.info("[saveQual_file()] --------------- Inside saveQual_file() method() ------------------------");
		try
		{
			String fieldName;
			FacesContext fc = FacesContext.getCurrentInstance();
			if(empQual_File==null){
				fieldName = CommonValidate.findComponentInRoot("empfile").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the File"));
			}
			else{
				System.out.println("serialno "+serialNo);
				System.out.println("file "+empQual_File);
				EmployeeDetail emp=new EmployeeDetail();
				emp.setSerialNo(serialNo);
				emp.setCourse(qualificationlist.get(Integer.parseInt(serialNo)-1).getCourse());
				emp.setYearofpassing(qualificationlist.get(Integer.parseInt(serialNo)-1).getYearofpassing());
				emp.setUniversity(qualificationlist.get(Integer.parseInt(serialNo)-1).getUniversity());
				emp.setMedium(qualificationlist.get(Integer.parseInt(serialNo)-1).getMedium());
				emp.setEmpQual_File(empQual_File);
				qualificationlist.set(Integer.parseInt(serialNo)-1, emp);
			}
		}
		catch(Exception e)
		{
			logger.error("Inside Exception", e);
		}
		return "";
	}
	
	public String saveExp_file(){
		logger.info("[saveExp_file()] --------------- Inside saveExp_file() method() ------------------------");
		try
		{
			String fieldName;
			FacesContext fc = FacesContext.getCurrentInstance();
			if(empexpFile==null){
				fieldName = CommonValidate.findComponentInRoot("empfile1").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose the File"));
			}
			else{
				EmployeeDetail emp=new EmployeeDetail();
				emp.setSerialNo1(serialNo);
				emp.setCompanyName(educationlist.get(Integer.parseInt(serialNo)-1).getCompanyName());
				emp.setFromDate(educationlist.get(Integer.parseInt(serialNo)-1).getToDate());
				emp.setToDate(educationlist.get(Integer.parseInt(serialNo)-1).getFromDate());
				emp.setContactName(educationlist.get(Integer.parseInt(serialNo)-1).getContactName());
				emp.setContactNumber(educationlist.get(Integer.parseInt(serialNo)-1).getContactNumber());
				emp.setEmpexpFile(empexpFile);
				educationlist.set(Integer.parseInt(serialNo)-1, emp);
			}
		}
		catch(Exception e)
		{
			logger.error("Inside Exception", e);
		}
		return "";
	}
	
	public String employeeFormPage(){
		System.out.println("employee form page");
		onload();
		return "hrEmployeeForm";
	}
	
	public String hrFileUpload() {
		logger.info("[hrFileUpload()] --------------- Inside hrFileUpload() method() ------------------------");
		uploadlist=new ArrayList<EmployeeDetail>(); 
		try{
			setDocDate(null); 
			setDocDescription(""); 
			setDocFilename(""); 
			setDocFiletype(""); 
			for (int i = 1; i < 2; i++) {
				EmployeeDetail employee=new EmployeeDetail();
				employee.setSerialNo(String.valueOf(i));
				employee.setEmpFile1(null);
				uploadlist.add(employee); 
			}
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
		return "hrFileUpload";

	}
	
	public String addFileUpload() {
		logger.info("[addFileUpload()] --------------- Inside addFileUpload() method() ------------------------");
		try{
			EmployeeDetail employee=new EmployeeDetail();
			employee.setSerialNo(String.valueOf(uploadlist.size()+1));
			employee.setEmpFile1(null);
			uploadlist.add(employee);
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
		return "";

	}

	public String docUpload(){
		logger.info("[docUpload()] --------------- Inside docUpload() method() ------------------------");
		String status="";
		ApplicationContext ctx=null;
		DemoController controller=null;
		try{
			if(validateFileupload(true)){ 
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance()); 
			controller = (DemoController) ctx.getBean("controller");
				EmployeeDetail employeeDetail=new EmployeeDetail();
				employeeDetail.setDocDate(docDate); 
				employeeDetail.setDocFilename(docFilename); 
				employeeDetail.setDocFiletype(docFiletype); 
				employeeDetail.setDocDescription(docDescription); 
				employeeDetail.setEmpFile1(empFile1);
				employeeDetail.setEducationlist(uploadlist);
				status=controller.getdocUpload(employeeDetail);				
				if(status.equalsIgnoreCase("success")){
					RequestContext.getCurrentInstance().execute("PF('docSuccess').show();"); 				
			}}
		}catch(Exception e){
			logger.error("Inside Exception", e);		
		}
		return "";
	}
	public String dummyAction11(FileUploadEvent event) throws IOException { 
		logger.info("[dummyaction11()] --------------- Inside dummyaction11 method() ------------------------");
		EmployeeDetail empdetail=new EmployeeDetail();
		  this.empFile1 = event.getFile();
		  empdetail.setEmpFile1(event.getFile());  
		  FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		  FacesContext.getCurrentInstance().addMessage(null, message);
		 
		  return "";
		 }
	private boolean validateFileupload(boolean flag){ 
		logger.info("[validateFileupload()] --------------- Inside validateFileupload() method() ------------------------");
		boolean valid=true;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			if (StringUtils.isEmpty(docFilename)) {
				if(flag){
					fieldName = CommonValidate.findComponentInRoot("hrfilename").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter the FileName "));
				}
				valid = false;
			} else if(!StringUtils.isEmpty(docFilename)){ 
				if(!CommonValidate.validateName(docFilename)){ 
					if(flag){
						fieldName = CommonValidate.findComponentInRoot("hrfilename").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter valid FileName"));
						}
						valid = false;
				}
			}
			if (docDate == null) { 
				if(flag){
					fieldName = CommonValidate.findComponentInRoot("hrdate").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter the date "));
				}
				valid = false;
			} 
			if (docFiletype.equals("select")) {
				if(flag){
					fieldName = CommonValidate.findComponentInRoot("hrfiletype").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter the Filetype "));
				}
				valid = false;
			} 
			if (StringUtils.isEmpty(docDescription)) {
				if(flag){
					System.out.println("===============================>"+docDescription+"===============>");
					fieldName = CommonValidate.findComponentInRoot("hrdesc").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter the Description "));
				}
				valid = false;
			} else if(!StringUtils.isEmpty(docDescription)){ 
				if(!CommonValidate.validateName(docDescription)){  
					if(flag){
						fieldName = CommonValidate.findComponentInRoot("hrdesc").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("Please Enter valid Description"));
						}
						valid = false;
				}
			}
		
		
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
		return valid;
	}
	
public Date docDate;
public String docFiletype;
public String docDescription;
public String docFilename;
private UploadedFile empFile1;
private List<EmployeeDetail> uploadlist=null;
public List<EmployeeDetail> getUploadlist() {
	return uploadlist;
}

public void setUploadlist(List<EmployeeDetail> uploadlist) {
	this.uploadlist = uploadlist;
}

public UploadedFile getEmpFile1() {
	return empFile1;
}

public void setEmpFile1(UploadedFile empFile1) {
	this.empFile1 = empFile1;
}

public Date getDocDate() {
	return docDate;
}

public void setDocDate(Date docDate) {
	this.docDate = docDate;
}

public String getDocFiletype() {
	return docFiletype;
}

public void setDocFiletype(String docFiletype) {
	this.docFiletype = docFiletype;
}

public String getDocDescription() {
	return docDescription;
}

public void setDocDescription(String docDescription) {
	this.docDescription = docDescription;
}

public String getDocFilename() {
	return docFilename;
}

public void setDocFilename(String docFilename) {
	this.docFilename = docFilename;
}





}
