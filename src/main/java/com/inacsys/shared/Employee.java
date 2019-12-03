package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name = "employee")
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "employee_details_id")
	private int employeeDetailsId;

	@Column(name = "account_no")
	private String accountNo;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "basic_salary")
	private String basicSalary;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_join")
	private Date dateOfJoin;

	@Column(name = "description_any")
	private String descriptionAny;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "employee_address")
	private String employeeAddress;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "employee_name")
	private String employeeName;

	@Temporal(TemporalType.DATE)
	@Column(name = "entry_date")
	private Date entryDate;

	private String freelancer;

	private String gender;

	@Column(name = "payroll_status")
	private String payrollStatus;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "registration_id")
	private String registrationId;
	
	private String status;
	private String street;
	private String present_street;
	private String city;
	private String present_city;
	private String country;
	private String present_country;
	private String state;
	private String present_state;
	private String phn_code;
	private String present_phn_code;
	private String present_phno;
	private String postal_code;
	private String present_postal_code;
	private String file_path;
	private String currency;
	private String currency_amount;
	private String passport;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "passport_date")
	private Date passportdate;
	
	private String emirates;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "emirates_date")
	private Date emiratesdate;
	
	private String licence;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "licence_date")
	private Date licencedate;
	
	private String other_filename;
	private String other_file;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	// bi-directional many-to-one association to Qualification
	@ManyToOne
	@JoinColumn(name = "qualification_category_id")
	private Qualification qualification;

	// bi-directional many-to-one association to Designation
	@ManyToOne
	@JoinColumn(name = "designation_category_id")
	private Designation designation;

	// bi-directional many-to-one association to Payroll
	@ManyToOne
	@JoinColumn(name = "payroll_id")
	private Payroll payroll;

	// bi-directional many-to-one association to Employeepayroll
	@OneToMany(mappedBy = "employee")
	private List<Employeepayroll> employeepayrolls;

	// bi-directional many-to-one association to Payroll
	@OneToMany(mappedBy = "employee")
	private List<Payroll> payrolls;

	// bi-directional many-to-one association to UserCreate
	@ManyToOne
	@JoinColumn(name = "user_ID")
	private UserCreate userID;
	
	private String approvalStatus;
	
	public Employee() {
	}

	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getCurrency_amount() {
		return currency_amount;
	}


	public void setCurrency_amount(String currency_amount) {
		this.currency_amount = currency_amount;
	}


	public int getEmployeeDetailsId() {
		return this.employeeDetailsId;
	}

	public void setEmployeeDetailsId(int employeeDetailsId) {
		this.employeeDetailsId = employeeDetailsId;
	}

	public UserCreate getUserID() {
		return userID;
	}

	public void setUserID(UserCreate userID) {
		this.userID = userID;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBasicSalary() {
		return this.basicSalary;
	}

	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfJoin() {
		return this.dateOfJoin;
	}

	public void setDateOfJoin(Date dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}

	public String getDescriptionAny() {
		return this.descriptionAny;
	}

	public void setDescriptionAny(String descriptionAny) {
		this.descriptionAny = descriptionAny;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmployeeAddress() {
		return this.employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getFreelancer() {
		return this.freelancer;
	}

	public void setFreelancer(String freelancer) {
		this.freelancer = freelancer;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPayrollStatus() {
		return this.payrollStatus;
	}

	public void setPayrollStatus(String payrollStatus) {
		this.payrollStatus = payrollStatus;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegistrationId() {
		return this.registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Qualification getQualification() {
		return this.qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public Designation getDesignation() {
		return this.designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Payroll getPayroll() {
		return this.payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}

	public List<Employeepayroll> getEmployeepayrolls() {
		return this.employeepayrolls;
	}

	public void setEmployeepayrolls(List<Employeepayroll> employeepayrolls) {
		this.employeepayrolls = employeepayrolls;
	}

	public Employeepayroll addEmployeepayroll(Employeepayroll employeepayroll) {
		getEmployeepayrolls().add(employeepayroll);
		employeepayroll.setEmployee(this);

		return employeepayroll;
	}

	public Employeepayroll removeEmployeepayroll(Employeepayroll employeepayroll) {
		getEmployeepayrolls().remove(employeepayroll);
		employeepayroll.setEmployee(null);

		return employeepayroll;
	}

	public List<Payroll> getPayrolls() {
		return this.payrolls;
	}

	public void setPayrolls(List<Payroll> payrolls) {
		this.payrolls = payrolls;
	}

	public Payroll addPayroll(Payroll payroll) {
		getPayrolls().add(payroll);
		payroll.setEmployee(this);

		return payroll;
	}

	public Payroll removePayroll(Payroll payroll) {
		getPayrolls().remove(payroll);
		payroll.setEmployee(null);

		return payroll;
	}

	
	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPresent_street() {
		return present_street;
	}

	public void setPresent_street(String present_street) {
		this.present_street = present_street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPresent_city() {
		return present_city;
	}

	public void setPresent_city(String present_city) {
		this.present_city = present_city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPresent_country() {
		return present_country;
	}

	public void setPresent_country(String present_country) {
		this.present_country = present_country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPresent_state() {
		return present_state;
	}

	public void setPresent_state(String present_state) {
		this.present_state = present_state;
	}

	public String getPhn_code() {
		return phn_code;
	}

	public void setPhn_code(String phn_code) {
		this.phn_code = phn_code;
	}

	public String getPresent_phn_code() {
		return present_phn_code;
	}

	public void setPresent_phn_code(String present_phn_code) {
		this.present_phn_code = present_phn_code;
	}

	public String getPresent_phno() {
		return present_phno;
	}

	public void setPresent_phno(String present_phno) {
		this.present_phno = present_phno;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getPresent_postal_code() {
		return present_postal_code;
	}

	public void setPresent_postal_code(String present_postal_code) {
		this.present_postal_code = present_postal_code;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public Date getPassportdate() {
		return passportdate;
	}

	public void setPassportdate(Date passportdate) {
		this.passportdate = passportdate;
	}

	public String getEmirates() {
		return emirates;
	}

	public void setEmirates(String emirates) {
		this.emirates = emirates;
	}

	public Date getEmiratesdate() {
		return emiratesdate;
	}

	public void setEmiratesdate(Date emiratesdate) {
		this.emiratesdate = emiratesdate;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public Date getLicencedate() {
		return licencedate;
	}

	public void setLicencedate(Date licencedate) {
		this.licencedate = licencedate;
	}

	public String getOther_file() {
		return other_file;
	}

	public void setOther_file(String other_file) {
		this.other_file = other_file;
	}

	public String getOther_filename() {
		return other_filename;
	}

	public void setOther_filename(String other_filename) {
		this.other_filename = other_filename;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}