package com.inacsys.managedBean;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.EmployeeDetail;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.Designation;
import com.inacsys.shared.Employee;
import com.inacsys.shared.Qualification;
import com.inacsys.util.CommonValidate;

@ManagedBean(name = "employeeDetailsViewFormMB")
public class EmployeeDetailsViewFormMB {
	private static Logger logger = Logger
			.getLogger(EmployeeDetailsViewFormMB.class);
	public String name;
	public String employeeid;
	public Date dob;
	public String mailid;
	public String gender;
	public String phno;
	public String address;
	public String qualification;
	public String bankName;
	public String accNo;
	public Date joindate;
	public String basicsalary;
	public String designation;
	public String description;
	public Date entrydate;
	public String validate;
	public String regid;
	public List<Employee> list = null;
	public String flag = "none";
	public List<EmployeeDetail> viewlist = null;
	public String status;
	public String freelancer;
	public List<Qualification> qualificate = null;
	public List<String> designate = null;
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
	private List<EmployeeDetail> qualificationlist=null;
	private List<EmployeeDetail> experiencelist=null;
	private boolean hiddenFlag=false;
	private boolean showFlag=true;
	List<String> stateist=new ArrayList<String>();
	public boolean tick=false;
	private UploadedFile empexpFile;
	private UploadedFile empQual_File;
	private UploadedFile empFile;
	public String filepath;
	public String certificate_path;
	public String expCertificate_path;
	public String serialNo;
	public String expID;
	public String qualID;
	public String otherFilename;
	public String passport_path;
	public String emirates_path;
	public String licence_path;
	public String otherfile_path;
	private UploadedFile passportFile;
	private UploadedFile emiratesFile;
	private UploadedFile licenceFile;
	private UploadedFile otherFile;
	public Date passport_date;
	public Date emirates_date;
	public Date licence_date;
	public String approvalStatus;
	public String userType;
	public String currency;
	public String currencyAmount;
	public String baseCurrency;
	private boolean employeeCheck=false;
	private String approveButtonFlag="none";
	private List<EmployeeDetailsViewFormMB> employeeDetailList=null;
	private int employeeDetailsId;
	List<String> stateist1=new ArrayList<String>();
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public List<String> getStateist1() {
		return stateist1;
	}

	public void setStateist1(List<String> stateist1) {
		this.stateist1 = stateist1;
	}

	public int getEmployeeDetailsId() {
		return employeeDetailsId;
	}

	public void setEmployeeDetailsId(int employeeDetailsId) {
		this.employeeDetailsId = employeeDetailsId;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isEmployeeCheck() {
		return employeeCheck;
	}

	public void setEmployeeCheck(boolean employeeCheck) {
		this.employeeCheck = employeeCheck;
	}

	public String getApproveButtonFlag() {
		return approveButtonFlag;
	}

	public void setApproveButtonFlag(String approveButtonFlag) {
		this.approveButtonFlag = approveButtonFlag;
	}

	public List<EmployeeDetailsViewFormMB> getEmployeeDetailList() {
		return employeeDetailList;
	}

	public void setEmployeeDetailList(
			List<EmployeeDetailsViewFormMB> employeeDetailList) {
		this.employeeDetailList = employeeDetailList;
	}

	public String getOtherFilename() {
		return otherFilename;
	}

	public void setOtherFilename(String otherFilename) {
		this.otherFilename = otherFilename;
	}

	public String getPassport_path() {
		return passport_path;
	}

	public void setPassport_path(String passport_path) {
		this.passport_path = passport_path;
	}

	public String getEmirates_path() {
		return emirates_path;
	}

	public void setEmirates_path(String emirates_path) {
		this.emirates_path = emirates_path;
	}

	public String getLicence_path() {
		return licence_path;
	}

	public void setLicence_path(String licence_path) {
		this.licence_path = licence_path;
	}

	public String getOtherfile_path() {
		return otherfile_path;
	}

	public void setOtherfile_path(String otherfile_path) {
		this.otherfile_path = otherfile_path;
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

	public String getExpID() {
		return expID;
	}

	public void setExpID(String expID) {
		this.expID = expID;
	}

	public String getQualID() {
		return qualID;
	}

	public void setQualID(String qualID) {
		this.qualID = qualID;
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

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
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

	public UploadedFile getEmpFile() {
		return empFile;
	}

	public void setEmpFile(UploadedFile empFile) {
		this.empFile = empFile;
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

	public List<String> getStateist() {
		return stateist;
	}

	public void setStateist(List<String> stateist) {
		this.stateist = stateist;
	}

	public boolean isTick() {
		return tick;
	}

	public void setTick(boolean tick) {
		this.tick = tick;
	}

	public List<EmployeeDetail> getQualificationlist() {
		return qualificationlist;
	}

	public void setQualificationlist(List<EmployeeDetail> qualificationlist) {
		this.qualificationlist = qualificationlist;
	}

	public List<EmployeeDetail> getExperiencelist() {
		return experiencelist;
	}

	public void setExperiencelist(List<EmployeeDetail> experiencelist) {
		this.experiencelist = experiencelist;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		EmployeeDetailsViewFormMB.logger = logger;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	public List<Qualification> getQualificate() {
		return qualificate;
	}

	public void setQualificate(List<Qualification> qualificate) {
		this.qualificate = qualificate;
	}

	public String getFreelancer() {
		return freelancer;
	}

	public List<String> getDesignate() {
		return designate;
	}

	public void setDesignate(List<String> designate) {
		this.designate = designate;
	}

	public void setFreelancer(String freelancer) {
		this.freelancer = freelancer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<EmployeeDetail> getViewlist() {
		return viewlist;
	}

	public void setViewlist(List<EmployeeDetail> viewlist) {
		this.viewlist = viewlist;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<Employee> getList() {
		return list;
	}

	public void setList(List<Employee> list) {
		this.list = list;
	}

	public String getName() {
		return name;
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

	public String getAddress() {
		return address;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
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

	public String menuCall() {
		logger.info("[menuCall()] --------------- Inside menuCall() method() ------------------------");
		try {
			validate = "";
			employeeid = "";
			name = "";
			flag = "none";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}

		return "";
	}
	public String approval;
	ArrayList<EmployeeDetailsViewFormMB> filterList;
	
	public ArrayList<EmployeeDetailsViewFormMB> getFilterList() {
		return filterList;
	}

	public void setFilterList(ArrayList<EmployeeDetailsViewFormMB> filterList) {
		this.filterList = filterList;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}
	public String serialno;
	

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	/****/
	public String view() {
		logger.info("[view()] --------------- Inside view() method() ------------------------");
		list = null;
		setFilterList(null);
		ApplicationContext ctx = null;
		employeeDetailList=null;int count=0;
		DemoController controller = null;
		EmployeeDetail employee = new EmployeeDetail();
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		try {
			employeeDetailList=new ArrayList<EmployeeDetailsViewFormMB>();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			employee.setApproval(approval);
			employee.setStatus(status);
			controller.employeeIdInfo(employee);
			if (employee.getList().size() > 0) {
				validate = null;
				setList(employee.getList());
				for (int i = 0; i < list.size(); i++) {
					EmployeeDetailsViewFormMB emp=new EmployeeDetailsViewFormMB();
					emp.setSerialno(String.valueOf(i+1));  
					emp.setRegid(list.get(i).getRegistrationId());
					emp.setEmployeeid(list.get(i).getEmployeeId());
					emp.setName(list.get(i).getEmployeeName());
					emp.setPhno(list.get(i).getPhoneNumber());
					emp.setCode(list.get(i).getPhn_code());  //stanley changes on 20
					emp.setDesignation(list.get(i).getDesignation().getType());
					emp.setEmployeeDetailsId(list.get(i).getEmployeeDetailsId());
					emp.setApprovalStatus(list.get(i).getApprovalStatus());
					employeeDetailList.add(emp);
					/*if(list.get(i).getUserID().getUserNo()==Integer.parseInt(userID)){
						employeeDetailList.add(emp);
					}else{
						if(list.get(i).getApprovalStatus().equals("draft")){
							employeeDetailList.add(emp);
						}
					}*/	
					if(list.get(i).getApprovalStatus().equals("draft")){
						count++;
					}
				}
				if(status.equals("empView")){
					if(count==0){
						approveButtonFlag="none";
					}else{
						approveButtonFlag="1";
					}
				}else if(status.equals("empPayroll")){
					approveButtonFlag="none";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	/****/
	public String view1() {
		logger.info("[view1()] --------------- Inside view1() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeeDetail employee = new EmployeeDetail();
		try {
			try {
				if (name.equals("")) {
					throw new Exception("Please Choose the Employee Name");
				}
			} catch (Exception e) {
				flag = "none";
				setValidate(e.getMessage());
			}

			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");

			setList(employee.getList());
			employee.setName(name);
			logger.debug("[view1()] --------------- Employee name ------------------------>"+name);
			controller.employeeNameInfo(employee);
			if (employee.getList().size() > 0) {
				flag = "1";
				validate = null;
				setList(employee.getList());
				logger.debug("[view1()] --------------- datalist ------------------------>"+list);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public String viewform() {
		logger.info("[viewform()] --------------- Inside viewform() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeeDetail employee = new EmployeeDetail();
		try {
			String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			employee.setRegid(regid);
			logger.debug("[viewform()] --------------- registrationID ------------------------>"+regid);			
			employee.setEmployeeDetailsId(employeeDetailsId);
			setViewlist(controller.getEmployeeDetail(employee));
			setAddress(viewlist.get(0).getAddress());
			setBasicsalary(viewlist.get(0).getBasicsalary());
			setDescription(viewlist.get(0).getDescription());
			setDesignation(viewlist.get(0).getDesignation());
			setDob(viewlist.get(0).getDob());
			setEntrydate(viewlist.get(0).getEntrydate());
			setGender(viewlist.get(0).getGender());
			setJoindate(viewlist.get(0).getJoindate());
			setMailid(viewlist.get(0).getMailid());
			setName(viewlist.get(0).getName());
			setPhno(viewlist.get(0).getPhno());
			setQualification(viewlist.get(0).getQualification());
			setFreelancer(viewlist.get(0).getFreelancer());
			setEmployeeid(viewlist.get(0).getEmployeeid());
			logger.debug("14" + employeeid);
			setRegid(viewlist.get(0).getRegid());
			setBankName(viewlist.get(0).getBankName());
			setAccNo(viewlist.get(0).getAccNo());
			setStreetname(viewlist.get(0).getStreetname());
			setCity(viewlist.get(0).getCity());
			setState(viewlist.get(0).getState());
			setCountry(viewlist.get(0).getCountry());
			setCode(viewlist.get(0).getCode());
			setPostalcode(viewlist.get(0).getPostalcode());
			setPresent_city(viewlist.get(0).getPresent_city());
			setPresent_streetname(viewlist.get(0).getPresent_streetname());
			setPresent_country(viewlist.get(0).getCountry());
			setPresent_state(viewlist.get(0).getPresent_state());
			setPresent_phno(viewlist.get(0).getPresent_phno());
			setPresent_code(viewlist.get(0).getPresent_code());
			setPresent_postalcode(viewlist.get(0).getPresent_postalcode());
			logger.debug("[viewform()] --------------- retrive value  ------------------------>"+viewlist.size());
			setFilepath(viewlist.get(0).getPath());
			setPassport_date(viewlist.get(0).getPassport_date());
			setPassport_path(viewlist.get(0).getPassport_path());
			setEmirates_date(viewlist.get(0).getEmirates_date());
			setEmirates_path(viewlist.get(0).getEmirates_path());
			setLicence_date(viewlist.get(0).getLicence_date());
			setLicence_path(viewlist.get(0).getLicence_path());
			setOtherfile_path(viewlist.get(0).getOtherfile_path());
			setOtherFilename(viewlist.get(0).getOtherFilename());
			setQualificationlist(employee.getQualificationlist());
			setExperiencelist(employee.getEducationlist());
			setCurrency(viewlist.get(0).getCurrency());
			setCurrencyAmount(viewlist.get(0).getCurrencyAmount());
			setBaseCurrency(baseCurrency);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}

		employeeviewform();
		return "";
	}

	public String edit() {
		logger.info("[edit()] --------------- Inside edit() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeeDetail employee = new EmployeeDetail();
		setValidate(null);
		try {
			String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			employee.setRegid(regid);
			logger.debug("[edit()] --------------- registrationID ------------------------>"+regid);
			employee.setStatus("register");
			employee.setEmployeeDetailsId(employeeDetailsId);
			setViewlist(controller.getEmployeeDetailEdit(employee));
			setAddress(viewlist.get(0).getAddress());
			setBasicsalary(viewlist.get(0).getBasicsalary());
			setDescription(viewlist.get(0).getDescription());
			setDesignation(viewlist.get(0).getDesignation());
			setDob(viewlist.get(0).getDob());
			setEntrydate(viewlist.get(0).getEntrydate());
			setGender(viewlist.get(0).getGender());
			setJoindate(viewlist.get(0).getJoindate());
			setMailid(viewlist.get(0).getMailid());
			setName(viewlist.get(0).getName());
			setPhno(viewlist.get(0).getPhno());
			setQualification(viewlist.get(0).getQualification());
			setEmployeeid(viewlist.get(0).getEmployeeid());
			setRegid(viewlist.get(0).getRegid());
			setBankName(viewlist.get(0).getBankName());
			setAccNo(viewlist.get(0).getAccNo());
			setDesignate(controller.designationInfo(designate));
			setStreetname(viewlist.get(0).getStreetname());
			setCity(viewlist.get(0).getCity());
			setState(viewlist.get(0).getState());
			setCountry(viewlist.get(0).getCountry());
			stateist=controller.getstatelist(viewlist.get(0).getCountry());
			setCode(viewlist.get(0).getCode());
			setPostalcode(viewlist.get(0).getPostalcode());
			setPresent_city(viewlist.get(0).getPresent_city());
			setPresent_streetname(viewlist.get(0).getPresent_streetname());
			setPresent_country(viewlist.get(0).getPresent_country());
			stateist1=controller.getstatelist(viewlist.get(0).getPresent_country());
			setPresent_state(viewlist.get(0).getPresent_state());
			System.out.println("state-----"+getPresent_state());
			setPresent_phno(viewlist.get(0).getPresent_phno());
			setPresent_code(viewlist.get(0).getPresent_code());
			setPresent_postalcode(viewlist.get(0).getPresent_postalcode());
			setPassport_date(viewlist.get(0).getPassport_date());
			setPassport_path(viewlist.get(0).getPassport_path());
			setEmirates_date(viewlist.get(0).getEmirates_date());
			setEmirates_path(viewlist.get(0).getEmirates_path());
			setLicence_date(viewlist.get(0).getLicence_date());
			setLicence_path(viewlist.get(0).getLicence_path());
			setOtherfile_path(viewlist.get(0).getOtherfile_path());
			setOtherFilename(viewlist.get(0).getOtherFilename());
			setCurrency(viewlist.get(0).getCurrency());
			setCurrencyAmount(viewlist.get(0).getCurrencyAmount());
			setBaseCurrency(baseCurrency);
			setQualificationlist(employee.getQualificationlist());
			setExperiencelist(employee.getEducationlist());
			setQualificate(controller.qualificationInfo(qualificate));
			setFilepath(viewlist.get(0).getPath());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public String editconfirm() {
		logger.info("[editconform()] --------------- Inside editconform() method() ------------------------");
		try {
			// setValidate("");
			if (name.equals("")) {
				throw new DemoException("Please Enter the Employee Name");
			}
			
			else if (dob == null) {
				throw new DemoException("Please Enter the Employee DOB");
			}

			else if (gender.equals("")) {
				throw new DemoException("Please select the Employee Gender");
			}

			 else if (phno.equalsIgnoreCase("")) {
				throw new DemoException("Please Enter the Employee Phone Number");
			}
			 else if (!phno.matches("^\\d+(\\.\\d+)*$")) {
				throw new DemoException("Phone Number should be numeric");
			}
			 else if (basicsalary.equals("")) {
					throw new DemoException("Please Enter the Basic Salary");
				} 
			 else if (!basicsalary.matches("^\\d+(\\.\\d+)*$")) {
					throw new DemoException("Basic salary should be numeric");
				} 
			 else if (streetname.equals("")) {
					throw new DemoException("Please Enter the Door/Stree Name");
				} 
			 else if (present_streetname.equals("")) {
					throw new DemoException("Please Enter the Door/Stree Name");
				} 
			 else if (city.equals("")) {
					throw new DemoException("Please Enter the City");
				} 
			 else if (present_city.equals("")) {
					throw new DemoException("Please Enter the City");
				} 
			 else if (country.equals("")) {
					throw new DemoException("Please Enter the Country");
				} 
			 else if (present_country.equals("")) {
					throw new DemoException("Please Enter the Country");
				} 
			 else if (currency.equals("")) {
					throw new DemoException("Please Select the Currency");
				} 
			 else if (state.equals("")) {
					throw new DemoException("Please Enter the State");
				} 
			 else if (present_state.equals("")) {
					throw new DemoException("Please Enter the State");
				} 
			 else if (postalcode.equals("")) {
					throw new DemoException("Please Enter the Postal Code");
				} 
			 else if (present_postalcode.equals("")) {
					throw new DemoException("Please Enter the Postal Code");
				} 
				else if (present_phno.equals("")) {
					throw new DemoException("Please Enter the Phone Number");
				} 
				else if (!present_phno.matches("^\\d+(\\.\\d+)*$")) {
					throw new DemoException("Phone Number should be numeric");
				} 
			ApplicationContext ctx = null;
			DemoController controller = null;
			EmployeeDetail employee = new EmployeeDetail();
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			employee.setRegid(regid);
			employee.setStatus("update");
			logger.debug("[editconform()] --------------- registratioID ------------------------>"+employee.getRegid());
			employee.setAddress(address);
			employee.setBasicsalary(basicsalary);
			employee.setDescription(description);
			employee.setDesignation(designation);
			employee.setDob(dob);
			employee.setEmployeeid(employeeid);
			employee.setEntrydate(entrydate);
			employee.setGender(gender);
			employee.setJoindate(joindate);
			employee.setMailid(mailid);
			employee.setName(name);
			employee.setPhno(phno);
			employee.setQualification(qualification);
			employee.setFreelancer("no");
			employee.setBankName(bankName);
			employee.setAccNo(accNo);
			employee.setPresent_phno(present_phno);
			employee.setQualification(qualification);
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
			employee.setEmpFile(empFile);
			employee.setPassportFile(passportFile);
			employee.setEmiratesFile(emiratesFile);
			employee.setLicenceFile(licenceFile);
			employee.setOtherFile(otherFile);
			employee.setCurrency(currency);
			employee.setCurrencyAmount(currencyAmount);
			employee.setBaseCurrency(baseCurrency);
			employee.setPassport_date(passport_date);
			employee.setEmirates_date(emirates_date);
			employee.setLicence_date(licence_date);
			employee.setQualificationlist(qualificationlist);
			employee.setEducationlist(experiencelist);
			for (int i = 0; i < experiencelist.size(); i++) {
				logger.info("[editconform()] --------------- experience list ------------------------>"+experiencelist.get(i).getExpID());
			}
			employee.setEmployeeDetailsId(employeeDetailsId);
			controller.designation(employee);
			setViewlist(controller.getEmployeeDetailEdit(employee));
			employeeviewclose();
			return "";
		} catch (DemoException e) {
			setValidate(e.getMessage());
			logger.debug("inside catch------>>>>");
			return "";
		} finally {

		}

	}

	public String delete() {
		logger.info("[delete()] --------------- Inside delete() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		EmployeeDetail employee = new EmployeeDetail();
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			employee.setRegid(regid);
			employee.setStatus("delete"); employee.setEmployeeDetailsId(employeeDetailsId);
			logger.info("[delete()] --------------- employee registrationID ------------------------>"+employee.getRegid());
			setViewlist(controller.getEmployeeDetailEdit(employee));
			RequestContext.getCurrentInstance().execute("PF('deleteconf').show();");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";

	}

	/** clear session **/
	public String clearSession() {
		logger.info("[clearSession()] --------------- Inside clearSession() method() ------------------------");
		flag = "none";
		setValidate(null);
		setEmployeeid(null);
		setName(null);
		return "employeeviewz";
	}

	public String editback() {
		logger.info("[editback()] --------------- Inside editback() method() ------------------------");
		flag = "none";
		setEmployeeid(null);
		setName(null);
		setValidate("");
		return "details";

	}

	public String deleteback() {
		logger.info("[deleteback()] --------------- Inside deleteback() method() ------------------------");
		flag = "none";
		setEmployeeid(null);
		setName(null);
		return "delete";
	}

	// prema begin 02/05/2016 dialog box creation for Employee View

	public void employeeviews() {
		logger.info("[employeeviews()] --------------- Inside employeeviews() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("aEmployeeDetailsView",
				options, null);
		menuCall();
	}

	// prema end 02/05/2016
	public void employeeviewclose() {
		RequestContext.getCurrentInstance().closeDialog("aEmployeeDetailsView");
	}

	public void employeeviewclosemodify() {
		RequestContext.getCurrentInstance().closeDialog(
				"aEmployeeDetailsEditForm");
	}

	// John
	public void employeeviewform() {
		logger.info("[employeeviewform()] --------------- Inside employeeviewform() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 550);
		options.put("contentWidth", 900);

		RequestContext.getCurrentInstance().openDialog(
				"aEmployeeDetailsViewForm", options, null);

	}

	public void employeemodifyform() {
		logger.info("[employeemodifyform()] --------------- Inside employeemodifyform() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 550);
		options.put("contentWidth", 1150);
		edit();
		RequestContext.getCurrentInstance().openDialog(
				"aEmployeeDetailsEditForm", options, null);
	}
	
	public String delteQualify(){
		logger.info("[delteQualify()] --------------- Inside delteQualify() method() ------------------------");
		try{
			qualificationlist.remove(Integer.valueOf(serialNo)-1);
			DemoController controller = null;
			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
						.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				controller.updatequalify(qualID);
			for (int i = 0; i <qualificationlist.size(); i++) {
				EmployeeDetail empdetail=new EmployeeDetail();
				empdetail.setSerialNo(String.valueOf(i+1));
				empdetail.setCourse(qualificationlist.get(i).getCourse());
				empdetail.setQualID(qualificationlist.get(i).getQualID());
				empdetail.setYearofpassing(qualificationlist.get(i).getYearofpassing());
				empdetail.setMedium(qualificationlist.get(i).getMedium());
				empdetail.setUniversity(qualificationlist.get(i).getUniversity());
				qualificationlist.set(i,empdetail);
			}		
		}catch(Exception e){
			logger.error("Inside Exception"+e.getMessage());
		}
		return "";
	}
	
	public String delteExperience(){
		logger.info("[deleteexperiance()] --------------- Inside deleteexperiance() method() ------------------------");
		try{
			experiencelist.remove(Integer.valueOf(serialNo)-1);
			DemoController controller = null;
			ApplicationContext ctx = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
						.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				controller.updateExpernce(expID);
			for (int i = 0; i <experiencelist.size(); i++) {
				EmployeeDetail empdetail=new EmployeeDetail();
				empdetail.setSerialNo1(String.valueOf(i+1));
				empdetail.setExpID(experiencelist.get(i).getExpID());
				empdetail.setCompanyName(experiencelist.get(i).getCompanyName());
				empdetail.setFromDate(experiencelist.get(i).getFromDate());
				empdetail.setToDate(experiencelist.get(i).getToDate());
				empdetail.setContactName(experiencelist.get(i).getContactName());
				empdetail.setContactNumber(experiencelist.get(i).getContactNumber());
				experiencelist.set(i,empdetail);
			}		
		}catch(Exception e){
			logger.error("Inside Exception"+e.getMessage());
		}
		return "";
	}
	
	public String addQualifications(){
		logger.info("[addQualifications()] --------------- Inside addQualifications() method() ------------------------");
		EmployeeDetail empdetail=new EmployeeDetail();
		empdetail.setSerialNo(String.valueOf(qualificationlist.size()+1));
		empdetail.setQualID("");
		empdetail.setQualification("");
		qualificationlist.add(empdetail);
		return "";
	}
	
	public String addExperiences(){
		logger.info("[addExperiences()] --------------- Inside addExperiences() method() ------------------------");
		EmployeeDetail empdetail=new EmployeeDetail();
		empdetail.setSerialNo1(String.valueOf(experiencelist.size()+1));
		empdetail.setExpID("");
		experiencelist.add(empdetail);
		return "";
	}

	public void statechange(ValueChangeEvent v) {
		logger.info("[statechange()] --------------- Inside statechange() method() ------------------------");
		String country = "";
		country = v.getNewValue().toString();
		ApplicationContext ctx = null;
		DemoController controller = null;
		logger.debug("[statechange()] --------------- country ------------------------>"+country);
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			stateist=controller.getstatelist(country);
			System.out.println("stateist "+stateist.size());
			if(country.equalsIgnoreCase("India")) setCode("+91");
			else if(country.equalsIgnoreCase("Indonesia")) setCode("+62");
			else if(country.equalsIgnoreCase("Malesia")) setCode("+60");
			else if(country.equalsIgnoreCase("Singapore")) setCode("+65");
			else if(country.equalsIgnoreCase("UAE")) setCode("+971");
		} 
		catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
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
			System.out.println("stateist1 "+stateist1.size());
			if(country.equalsIgnoreCase("India")) setPresent_code("+91");
			else if(country.equalsIgnoreCase("Indonesia")) setPresent_code("+62");
			else if(country.equalsIgnoreCase("Malesia")) setPresent_code("+60");
			else if(country.equalsIgnoreCase("Singapore")) setPresent_code("+65");
			else if(country.equalsIgnoreCase("UAE")) setPresent_code("+971");
		} 
		catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
		}
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
		  this.empFile = event.getFile();
		  empdetail.setEmpFile(event.getFile());
		  
		  return "";
		 }
	public String dummyAction1(FileUploadEvent event) throws IOException {
		  this.empQual_File = event.getFile();
		  empdetail.setEmpQual_File(event.getFile());
		   return "";
		 }
	public String dummyAction2(FileUploadEvent event) throws IOException {
		  this.empexpFile = event.getFile();
		  empdetail.setEmpexpFile(event.getFile());
		  return "";
		 }
	
	public void imageview(OutputStream out, Object data) throws IOException {
		String s ="/home/ec2-user/File_Inacsys/Employee/Photos/";
		
		try{		
			System.out.println(s + filepath);
			BufferedImage img = ImageIO
					.read(new File(s + filepath));
			ImageIO.write(img, "png", out);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	

	private StreamedContent stream;
	private StreamedContent stream1;
	private StreamedContent stream2;
	private StreamedContent stream3;
	private StreamedContent stream4;
	private StreamedContent stream5;
	
	public EmployeeDetail getEmpdetail() {
		return empdetail;
	}

	public void setEmpdetail(EmployeeDetail empdetail) {
		this.empdetail = empdetail;
	}

	public StreamedContent getStream2() {
		return stream2;
	}

	public void setStream2(StreamedContent stream2) {
		this.stream2 = stream2;
	}

	public StreamedContent getStream3() {
		return stream3;
	}

	public void setStream3(StreamedContent stream3) {
		this.stream3 = stream3;
	}

	public StreamedContent getStream4() {
		return stream4;
	}

	public void setStream4(StreamedContent stream4) {
		this.stream4 = stream4;
	}

	public StreamedContent getStream5() {
		return stream5;
	}

	public void setStream5(StreamedContent stream5) {
		this.stream5 = stream5;
	}

	public StreamedContent getStream1() {
		return stream1;
	}

	public void setStream1(StreamedContent stream1) {
		this.stream1 = stream1;
	}

	public StreamedContent getStream() {
		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}

	public void file() throws FileNotFoundException {
		logger.info("[file()] --------------- Inside file() method() ------------------------");
	    try{
	    	System.out.println("file === "+certificate_path);
	    	InputStream input=null;
	    	File file=new File("/home/ec2-user/File_Inacsys/Employee/Qualification/"+certificate_path);
			input = new FileInputStream(file);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			this.setStream(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
	    }
	    catch(Exception e){
	    	logger.warn(" exception - "+e);
	    	logger.warn("Inside Exception",e);
	    }
	   }
	
	public void file1() throws FileNotFoundException {
		logger.info("[file1()] --------------- Inside file1() method() ------------------------");
	    try{
	    	System.out.println("file === "+expCertificate_path);
	    	InputStream input=null;
	    	File file=new File("/home/ec2-user/File_Inacsys/Employee/Experience/"+expCertificate_path);
			input = new FileInputStream(file);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			this.setStream1(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
	    }
	    catch(Exception e){
	    	logger.warn(" exception - "+e);
	    	logger.warn("Inside Exception",e);
	    }
	   }
	
	public void file2() throws FileNotFoundException {
		logger.info("[file2()] --------------- Inside file2() method() ------------------------");
	    try{
	    	System.out.println("file =2== "+passport_path);
	    	InputStream input=null;
	    	File file=new File("/home/ec2-user/File_Inacsys/Employee/Passport/"+passport_path);
			input = new FileInputStream(file);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			this.setStream2(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	logger.warn(" exception - "+e);
	    	logger.warn("Inside Exception",e);
	    }
	   }
	public void file3() throws FileNotFoundException {
		logger.info("[file3()] --------------- Inside file3() method() ------------------------");
	    try{
	    	System.out.println("file =3== "+emirates_path);
	    	InputStream input=null;
	    	File file=new File("/home/ec2-user/File_Inacsys/Employee/Emirates/"+emirates_path);
			input = new FileInputStream(file);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			this.setStream3(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	logger.warn(" exception - "+e);
	    	logger.warn("Inside Exception",e);
	    }
	   }
	public void file4() throws FileNotFoundException {
		logger.info("[file4()] --------------- Inside file4() method() ------------------------");
	    try{
	    	System.out.println("file ==4= "+licence_path);
	    	InputStream input=null;
	    	File file=new File("/home/ec2-user/File_Inacsys/Employee/Licence/"+licence_path);
			input = new FileInputStream(file);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			this.setStream4(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	logger.warn(" exception - "+e);
	    	logger.warn("Inside Exception",e);
	    }
	   }
	public void file5() throws FileNotFoundException {
		logger.info("[file5()] --------------- Inside file5() method() ------------------------");
	    try{
	    	System.out.println("file =5== "+otherfile_path);
	    	InputStream input=null;
	    	File file=new File("/home/ec2-user/File_Inacsys/Employee/Other/"+otherfile_path);
			input = new FileInputStream(file);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			this.setStream5(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	logger.warn(" exception - "+e);
	    	logger.warn("Inside Exception",e);
	    }
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
				emp.setQualID(qualificationlist.get(Integer.parseInt(serialNo)-1).getQualID());
				emp.setMedium(qualificationlist.get(Integer.parseInt(serialNo)-1).getMedium());
				emp.setEmpQual_File(empQual_File);
				qualificationlist.set(Integer.parseInt(serialNo)-1, emp);
			}

			   
		}
		catch(Exception e)
		{
			logger.warn(" exception - "+e);
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
				System.out.println("serialno "+serialNo);
				System.out.println("file ===  "+empexpFile);
				EmployeeDetail emp=new EmployeeDetail();
				emp.setSerialNo1(serialNo);
				emp.setCompanyName(experiencelist.get(Integer.parseInt(serialNo)-1).getCompanyName());
				emp.setFromDate(experiencelist.get(Integer.parseInt(serialNo)-1).getToDate());
				emp.setToDate(experiencelist.get(Integer.parseInt(serialNo)-1).getFromDate());
				emp.setContactName(experiencelist.get(Integer.parseInt(serialNo)-1).getContactName());
				emp.setContactNumber(experiencelist.get(Integer.parseInt(serialNo)-1).getContactNumber());
				emp.setExpID(experiencelist.get(Integer.parseInt(serialNo)-1).getExpID());
				emp.setEmpexpFile(empexpFile);
				experiencelist.set(Integer.parseInt(serialNo)-1, emp);
			}
		}
		catch(Exception e)
		{
			logger.warn(" exception - "+e);
		}
		return "";
	}

	public String dummyAction3(FileUploadEvent event) throws IOException {
		  this.passportFile = event.getFile();
		  empdetail.setPassportFile(event.getFile());
		  return "";
		 }
	public String dummyAction4(FileUploadEvent event) throws IOException {
		  this.emiratesFile = event.getFile();
		  empdetail.setEmiratesFile(event.getFile());
		  return "";
		 }
	public String dummyAction5(FileUploadEvent event) throws IOException {
		  this.licenceFile = event.getFile();
		  empdetail.setLicenceFile(event.getFile());
		  return "";
		 }
	public String dummyAction6(FileUploadEvent event) throws IOException {
		  this.otherFile = event.getFile();
		  empdetail.setOtherFile(event.getFile());
		  return "";
		 }
	
	public String employeeApproval(){
		logger.info("[employeeApproval()] --------------- Inside employeeApproval() method() ------------------------");
		String status="";DemoController controller = null;
		int count=0;setValidate("");
		try{
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			for (int i = 0; i < employeeDetailList.size(); i++) {
				if(employeeDetailList.get(i).isEmployeeCheck()==true){
					System.out.println("inside if----");
					count++;
				}
			}
			if(count==0){
				System.out.println("inside if=====");
				throw new Exception("Please Choose atleast one row for Approval.");
			}else{
				setValidate("");
				status=controller.employeeApproval(employeeDetailList);
				System.out.println("status"+status);
				if(status.equalsIgnoreCase("Success")){
					RequestContext.getCurrentInstance().execute("PF('approvalConfirm').show()");
				}
			}
		}catch(Exception e){
			setValidate(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}
	
	public void quaupload(){
		RequestContext.getCurrentInstance().execute("PF('uploadDialog1').show()");
	}
	
}