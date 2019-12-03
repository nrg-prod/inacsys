package com.inacsys.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.model.UploadedFile;

import com.inacsys.shared.Designation;
import com.inacsys.shared.Employee;
import com.inacsys.shared.Qualification;

public class EmployeeDetail {
	public String name;
	public String employeeid;
	public Date dob;
	public String mailid;
	public String gender;
	public String phno;
	public String address;
	public String qualification;
	public Date joindate;
	public String basicsalary;
	public String designation;
	public String description;
	public Date entrydate;
	public String validate;
	public String regid;
	public List<Qualification> qualificate = null;
	public List<Designation> designate = null;
	public String payroll;
	public List<Employee> list = null;
	public String status;
	private UploadedFile empFile;
	private List<EmployeeDetail> qualificationlist=new ArrayList<EmployeeDetail>();
	private List<EmployeeDetail> educationlist=new ArrayList<EmployeeDetail>();
	public String course;
	public String yearofpassing;
	public String university;
	public String medium;
	public String certificate_path;
	public String expCertificate_path;
	public String companyName;
	public Date fromDate;
	public Date toDate;
	public String contactName;
	public String contactNumber;
	public String code;
	public String country;
	public String state;
	public String streetname;
	public String postalcode;
	public String city;
	public String path;
	public String present_code;
	public String present_country;
	public String present_state;
	public String present_streetname;
	public String present_postalcode;
	public String present_city;
	public String present_phno;
	public String serialNo;
	public String serialNo1;
	public String expID;
	public String qualID;
	private UploadedFile empexpFile;
	private UploadedFile empQual_File;
	private boolean hiddenFlag=false;
	private boolean showFlag=true;
	List<String> stateist=new ArrayList<String>();
	public boolean tick=false;
	private UploadedFile passportFile;
	private UploadedFile emiratesFile;
	private UploadedFile licenceFile;
	private UploadedFile otherFile;
	public Date passport_date;
	public Date emirates_date;
	public Date licence_date;
	public String otherFilename;
	public String passport_path;
	public String emirates_path;
	public String licence_path;
	public String otherfile_path;
	public String docFiletype;
	public String docDescription;
	public String docFilename;
	private UploadedFile empFile1;
	public Date docDate;
	public int employeeDetailsId;
	public String currency;
	public String baseCurrency;
	public String currencyAmount;
	
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String approval;
	
	
	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public int getEmployeeDetailsId() {
		return employeeDetailsId;
	}

	public void setEmployeeDetailsId(int employeeDetailsId) {
		this.employeeDetailsId = employeeDetailsId;
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

	public String getOtherFilename() {
		return otherFilename;
	}

	public void setOtherFilename(String otherFilename) {
		this.otherFilename = otherFilename;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getSerialNo1() {
		return serialNo1;
	}

	public void setSerialNo1(String serialNo1) {
		this.serialNo1 = serialNo1;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public UploadedFile getEmpFile() {
		return empFile;
	}

	public void setEmpFile(UploadedFile empFile) {
		this.empFile = empFile;
	}

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

	public String freelancer;

	public String getFreelancer() {
		return freelancer;
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

	/*
	 * public int getPhno() { return phno; } public void setPhno(int phno) {
	 * this.phno = phno; }
	 */

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

	public String bankName;
	public String accNo;

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

}