package com.inacsys.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.naming.spi.DirStateFactory.Result;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.sf.jasperreports.engine.type.PenEnum;

import org.hibernate.mapping.Array;
import org.primefaces.component.chart.Chart;
import org.primefaces.context.RequestContext;
import org.primefaces.push.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inacsys.controler.DemoControllerImpl;
import com.inacsys.domain.ATransaction;
import com.inacsys.domain.AccountsDatabean;
import com.inacsys.domain.EmployeeDetail;
import com.inacsys.domain.EmployeePayroll;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.AccPayableLiability;
import com.inacsys.shared.AccReceivableAsset;
import com.inacsys.shared.AccountDeposit;
import com.inacsys.shared.AccountPayableAcct;
import com.inacsys.shared.AccountPayment;
import com.inacsys.shared.AccountReceivableAcct;
import com.inacsys.shared.AccountType;
import com.inacsys.shared.BankAcct;
import com.inacsys.shared.CashAsset;
import com.inacsys.shared.ChartOfAccount;
import com.inacsys.shared.Client;
import com.inacsys.shared.CogAcct;
import com.inacsys.shared.CreditCardAcct;
import com.inacsys.shared.Designation;
import com.inacsys.shared.Employee;
import com.inacsys.shared.EmployeeQualification;
import com.inacsys.shared.EquityAcct;
import com.inacsys.shared.ExpenseAccountsPayment;
import com.inacsys.shared.ExpenseCoa;
import com.inacsys.shared.ExpenseTransaction;
import com.inacsys.shared.ExpensesAcct;
import com.inacsys.shared.Experience;
import com.inacsys.shared.FixedAssetsAcct;
import com.inacsys.shared.GstAcct;
import com.inacsys.shared.I0001;
import com.inacsys.shared.I0015;
import com.inacsys.shared.I0016;
import com.inacsys.shared.I0018;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0021;
import com.inacsys.shared.I0022;
import com.inacsys.shared.I0023;
import com.inacsys.shared.I0031;
import com.inacsys.shared.I0032;
import com.inacsys.shared.IncomeAcct;
import com.inacsys.shared.JournalEntry;
import com.inacsys.shared.Month;
import com.inacsys.shared.NotesPayableAcct;
import com.inacsys.shared.OpenbalEquityAcct;
import com.inacsys.shared.OtherAssetsAcct;
import com.inacsys.shared.OtherCurrentAssetsAcct;
import com.inacsys.shared.OtherCurrentLibAcct;
import com.inacsys.shared.OtherExpensesAcct;
import com.inacsys.shared.OtherIncomeAcct;
import com.inacsys.shared.Payroll;
import com.inacsys.shared.Qualification;
import com.inacsys.shared.SalesAccountsPayment;
import com.inacsys.shared.SalesCoa;
import com.inacsys.shared.SalesRecord;
import com.inacsys.shared.SalesTransaction;
import com.inacsys.shared.Transaction;
import com.inacsys.shared.UserCreate;
import com.inacsys.shared.Year;
import com.inacsys.util.AccountsJDBC;
import com.inacsys.util.CurrencyConverter;
import com.inacsys.util.GenerateEmployee;
import com.inacsys.util.Util;

@Repository
@Singleton
public class DemoAccountsDaoImpl implements DemoAccountsDao {
	private static final ExpenseCoa ExpenseCoa = null;
	final Logger logger = LoggerFactory.getLogger(DemoAccountsDaoImpl.class);
	Date now = new Date();
	SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
	@PersistenceContext(unitName = "inacsys-pu")
	private EntityManager entitymanager;

	@Value("${accountrec.name.status}" )
	private String accrecName;
	
	@Value("${service.name.status}" )
	private String serviceName;
	
	@Value("${accountrec.categorytype.status}" )
	private String accrecCategoryType;
	
	@Value("${accountrec.detailtype.status}" )
	private String accrecDetailType;
	
	@Value("${service.categorytype.status}" )
	private String serviceCategoryType;
	
	@Value("${service.detailtype.status}" )
	private String serviceDetailType;
	
	@Value("${active.status}" )
	private String activeStatus;
	
	@Value("${approval.draft.status}" )
	private String draftStatus;
	
	@Value("${no.openingbalance.status}" )
	private String noOpeningBalance;
	
	@Value("${non.openingbalance.status}" )
	private String nonOpeningBalance;
	
	@Value("${yes.openingbalance.status}" )
	private String openingBalance;
	
	@Value("${accounts.unpaid.status}" )
	private String unpaidStatus;
	
	@Value("${accounts.paid.status}" )
	private String paidStatus;
	
	@Value("${accounts.closed.status}" )
	private String closedStatus;
	
	@Value("${accounts.invoice.status}" )
	private String invoiceStatus;
	
	@Value("${accounts.nopayment.status}" )
	private String notStartPayStatus;
	
	@Value("${openingbalequity.name.status}" )
	private String openingbalequityName;
	
	@Value("${openingbalequity.categorytype.status}" )
	private String openingbalequityCateType;
	
	@Value("${bank.categorytype.status}" )
	private String bankCategoryType;
	
	@Value("${othercurrentassets.categorytype.status}" )
	private String othercurrassetsCateType;
	
	@Value("${accounts.unapplied.status}" )
	private String unappliedStatus;
	
	@Value("${accounts.partial.status}" )
	private String partialStatus;
	
	@Value("${accountpay.name.status}" )
	private String accpayName;
	
	@Value("${accountpay.categorytype.status}" )
	private String accpayCategoryType;
	
	@Value("${accountpay.detailtype.status}" )
	private String accpayDetailType;
	
	@Value("${creditcard.categorytype.status}" )
	private String creditcardCategoryType;
	
	@Value("${purchases.name.status}")
	private String purchaseType;
	
	@Value("${ouputIGST.name.status}")
	private String outputIGSTName;
	
	@Value("${ouputCGST.name.status}")
	private String outputCGSTName;
	
	@Value("${ouputSGST.name.status}")
	private String outputSGSTName;
	
	@Value("${GST.category.status}")
	private String GSTcategorytype;
	
	@Value("${GST.detail.status}")
	private String GSTdetailtype;
	
	@Value("${inputIGST.name.status}")
	private String inputIGSTName;
	
	@Value("${inputCGST.name.status}")
	private String inputCGSTName;
	
	@Value("${inputSGST.name.status}")
	private String inputSGSTName;
	
	
	List<String> igstList=Arrays.asList("18% IGST","0% IGST","28% IGST","12% IGST","5% IGST");
	List<String> gstList=Arrays.asList("18% GST","0% GST","28% GST","12% GST","5% GST");
		
	@Transactional(value = "transactionManager")
	public List<String> designationInfo(List<String> designate)
			throws DemoException {
		logger.info(" inside dao----->>>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			logger.info("inside try in dao---->>>");
			q = entitymanager.createQuery("select type from Designation  where client_ID=?");
			q.setParameter(1, clientID);
			designate = q.getResultList();
			logger.info("designation------->>>" + designate);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return designate;
	}

	@Transactional(value = "transactionManager")
	public List<Qualification> qualificationInfo(List<Qualification> qualificate)
			throws DemoException {
		logger.info(" inside dao----->>>");
		

		Query q = null;
		try {
			logger.info("inside try in dao---->>>");
			q = entitymanager.createQuery("select type from Qualification ");
			
			qualificate = q.getResultList();
			logger.info("designation------->>>" + qualificate);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return qualificate;
	}

	@Transactional(value = "transactionManager")
	public String employee(EmployeeDetail employee) throws DemoException {
		logger.info(" inside dao----->>>");Date date=new Date();
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		String status="failed";
		try {
			logger.info("inside try in dao---->>>");
			Query q2 = null;
			q2 = entitymanager.createQuery("from Employee where client_ID=?");
			q2.setParameter(1, clientID);
			List<Employee> reult1 = (List<Employee>) q2.getResultList();
			int count = 0;
			if (reult1.size() > 0) {
				for (Employee re : reult1) {
					count++;
				}
			}
			if (count == 0) {
				count++;
				String a = "AER000" + count;
				employee.setRegid(a);
			} else {
				count++;
				String a = "AER000" + count;
				employee.setRegid(a);
			}
			logger.info("registration id=" + employee.getRegid());
			Query q = null;
			q = entitymanager.createQuery("from Designation where type=? and client_ID=?");
			q.setParameter(1, employee.getDesignation());
			logger.info("designation=" + employee.getDesignation());
			q.setParameter(2, clientID);
			logger.info("designation----->>>" + employee.getDesignation());
			List<Designation> result = (List<Designation>) q.getResultList();
			logger.info("Designation size----->>>" + result.size());
			int dcId = 0;
			if (result.size() > 0) {
				dcId = result.get(0).getDesignationCategoryId();
				logger.info("designation id------>>>" + dcId);
			} else {
				logger.info("inside else");
				Designation designation = new Designation();
				designation.setType(employee.getDesignation());
				designation.setClient_ID(clientID);
				entitymanager.persist(designation);

				Query qry = null;
				qry = entitymanager
						.createQuery("from Designation where type=? and client_ID=?");
				qry.setParameter(1, employee.getDesignation());
				logger.info("designation=" + employee.getDesignation());
				qry.setParameter(2, clientID);
				logger.info("designation----->>>" + employee.getDesignation());
				List<Designation> out = (List<Designation>) qry.getResultList();
				logger.info("Designation size----->>>" + out.size());

				if (out.size() > 0) {
					int i = 0;
					dcId = out.get(i).getDesignationCategoryId();
					logger.info("designation id------>>>" + dcId);
				}
				logger.info("inside else end");
			}
			Query q1 = null;
			q1 = entitymanager.createQuery("from Qualification where type=?  ");
			q1.setParameter(1, employee.getQualification());
			
			logger.info("qualification=" + employee.getQualification());
			List<Qualification> resul = (List<Qualification>) q1.getResultList();
			logger.info("qualification size----->>>" + resul.size());
			int qcId = 0;
			if (resul.size() > 0) {
				qcId = resul.get(0).getQualificationCategoryId();
				logger.info("Qualification id------>>>" + qcId);
			}
			Employee register = new Employee();
			register.setBasicSalary(employee.getBasicsalary());
			register.setDateOfBirth(employee.getDob());
			register.setDateOfJoin(employee.getJoindate());
			register.setDescriptionAny(employee.getDescription());
			register.setEmailId(employee.getMailid());
			register.setEmployeeAddress(employee.getAddress());
			register.setEmployeeId(employee.getEmployeeid());
			register.setEmployeeName(employee.getName());
			register.setEntryDate(employee.getEntrydate());
			register.setGender(employee.getGender());
			register.setPhoneNumber("" + employee.getPhno());
			register.setRegistrationId(employee.getRegid());
			register.setFreelancer(employee.getFreelancer());
			register.setStatus("inserted");
			register.setPayrollStatus("inserted");
			register.setBankName(employee.getBankName());
			register.setAccountNo(employee.getAccNo());
			register.setClient_ID(clientID);
			register.setCity(employee.getCity());
			register.setPresent_city(employee.getPresent_city());
			register.setStreet(employee.getStreetname());
			register.setPresent_street(employee.getPresent_streetname());
			register.setState(employee.getState());
			register.setPresent_state(employee.getPresent_state());
			register.setCountry(employee.getCountry());
			register.setPresent_country(employee.getPresent_country());
			register.setPostal_code(employee.getPostalcode());
			register.setPresent_postal_code(employee.getPostalcode());
			register.setPhn_code(employee.getCode());
			register.setPresent_phn_code(employee.getPresent_code());
			register.setPresent_phno(employee.getPresent_phno());
			register.setPassportdate(employee.getPassport_date());
			register.setEmiratesdate(employee.getEmirates_date());
			register.setLicencedate(employee.getLicence_date());
			register.setOther_filename(employee.getOtherFilename());
			register.setCreatedDate(date);
			register.setCurrency(employee.getCurrency());
			if(employee.getCurrency().equalsIgnoreCase(employee.getBaseCurrency())){
				register.setCurrency_amount(employee.getBasicsalary());
			}else{
				BigDecimal currAmount=CurrencyConverter.findExchangeRateAndConvert(employee.getCurrency(),employee.getBaseCurrency(),employee.getBasicsalary());
				register.setCurrency_amount(String.valueOf(currAmount));
			}
			register.setUserID(entitymanager.find(UserCreate.class, Integer.parseInt(userID)));
			register.setApprovalStatus("draft");
			if(employee.getEmpFile() != null)
			{
				String ss=employee.getEmpFile().getContentType();
				System.out.println("empFile  "+ss);
				String type = ss.substring(ss.lastIndexOf("/") + 1);
				System.out.println("transFile  "+type);
				copyFile11(employee.getDob(),employee.getEmployeeid(), employee.getEmpFile().getInputstream(), type);
				String path = ft.format(employee.getDob()) + "/" +employee.getEmployeeid()+ "." + type;
				register.setFile_path(path);
				System.out.println("empfile --- "+employee.getEmpFile().getSize());
			}
			
			
			if(employee.getPassportFile() != null)
			{
				String ss=employee.getPassportFile().getContentType();
				System.out.println("passportfile  "+ss);
				String type = ss.substring(ss.lastIndexOf("/") + 1);
				System.out.println("passportfile  "+type);
				String id=employee.getEmployeeid()+" "+"Passport";
				passportcopyFile(employee.getDob(),id, employee.getPassportFile().getInputstream(), type);
				String path = ft.format(employee.getDob()) + "/" +employee.getEmployeeid()+" "+"Passport"+ "." + type;
				register.setPassport(path);
				System.out.println("empfile --- "+employee.getPassportFile().getSize());
			}
			
			if(employee.getEmiratesFile() != null)
			{
				String ss=employee.getEmiratesFile().getContentType();
				System.out.println("emirates  "+ss);
				String type = ss.substring(ss.lastIndexOf("/") + 1);
				System.out.println("emirates  "+type);
				String id=employee.getEmployeeid()+" "+"Emirates";
				emiratescopyFile(employee.getDob(),id, employee.getEmiratesFile().getInputstream(), type);
				String path = ft.format(employee.getDob()) + "/" +employee.getEmployeeid()+" "+"Emirates"+ "." + type;
				register.setEmirates(path);
				System.out.println("emirates --- "+employee.getEmiratesFile().getSize());
			}
			
			if(employee.getLicenceFile() != null)
			{
				String ss=employee.getLicenceFile().getContentType();
				System.out.println("licence  "+ss);
				String type = ss.substring(ss.lastIndexOf("/") + 1);
				System.out.println("licence  "+type);
				String id=employee.getEmployeeid()+" "+"Licence";
				licencecopyFile(employee.getDob(),id, employee.getLicenceFile().getInputstream(), type);
				String path = ft.format(employee.getDob()) + "/" +employee.getEmployeeid()+" "+"Licence"+ "." + type;
				register.setLicence(path);
				System.out.println("licence --- "+employee.getLicenceFile().getSize());
			}
			
			if(employee.getOtherFile() != null)
			{
				String ss=employee.getOtherFile().getContentType();
				System.out.println("otherfile  "+ss);
				String type = ss.substring(ss.lastIndexOf("/") + 1);
				System.out.println("otherfile  "+type);
				String id=employee.getEmployeeid()+" "+employee.getOtherFilename();
				othercopyFile(employee.getDob(),id, employee.getOtherFile().getInputstream(), type);
				String path = ft.format(employee.getDob()) + "/" +employee.getEmployeeid()+" "+employee.getOtherFilename()+ "." + type;
				register.setOther_file(path);
				System.out.println("otherfile --- "+employee.getEmpFile().getSize());
			}
			
			register.setDesignation(entitymanager.find(Designation.class, dcId));
			entitymanager.persist(register);

			
			Query q3 = null;
			q3 = entitymanager.createQuery("from Employee where  employeeId=? and client_ID=? and user_ID=?");
			q3.setParameter(1, employee.getEmployeeid());
			q3.setParameter(2, clientID);
			q3.setParameter(3, userID);
			List<Employee> res = (List<Employee>) q3.getResultList();
			System.out.println("res---"+res.size()+"-----"+res.get(0).getEmployeeDetailsId());
			System.out.println(" employee.getEducationlist().size() "+ employee.getEducationlist().size());
			
			//qualification
			for (int i = 0; i < employee.getQualificationlist().size(); i++) {
				try{
					if(employee.getQualificationlist().get(i).getCourse()==null || employee.getQualificationlist().get(i).getCourse().equals(""))
					{						
					}else{
						EmployeeQualification emp=new EmployeeQualification();
						emp.setCourseName(employee.getQualificationlist().get(i).getCourse());
						emp.setCertificatePath(employee.getQualificationlist().get(i).getCertificate_path());
						System.out.println("university "+employee.getQualificationlist().get(i).getUniversity());
						emp.setInstituteName(employee.getQualificationlist().get(i).getUniversity());
						emp.setMediumInstruction(employee.getQualificationlist().get(i).getMedium());
						emp.setYearOf_passing(employee.getQualificationlist().get(i).getYearofpassing());
						emp.setEmployee(entitymanager.find(Employee.class, res.get(0).getEmployeeDetailsId()));
						System.out.println("quali file "+employee.getQualificationlist().get(i).getEmpQual_File());
						if(employee.getQualificationlist().get(i).getEmpQual_File() != null)
						{
							String ss=employee.getQualificationlist().get(i).getEmpQual_File().getContentType();
							System.out.println("empqualFile  "+ss);
							String type = ss.substring(ss.lastIndexOf("/") + 1);
							String id=employee.getEmployeeid()+" "+employee.getQualificationlist().get(i).getYearofpassing();
							System.out.println("empexpFile  "+type);
							copyFile1(employee.getDob(),id, employee.getQualificationlist().get(i).getEmpQual_File().getInputstream(), type);
							String path = ft.format(employee.getDob()) + "/" +id+ "." + type;
							emp.setCertificatePath(path);
							System.out.println("empfile --- "+employee.getQualificationlist().get(i).getEmpQual_File().getSize());
						}
						emp.setStatus("Active");
						entitymanager.persist(emp);
						entitymanager.flush();
						entitymanager.clear();
					
					}
				}catch(Exception e){
					
				}
			}
			
			//experience
			for (int i = 0; i < employee.getEducationlist().size(); i++) {
				try{
					if(employee.getEducationlist().get(i).getCompanyName()==null || employee.getEducationlist().get(i).getCompanyName().equals(""))
					{						
					}else{
						Experience experience=new Experience();
						experience.setCompanyName(employee.getEducationlist().get(i).getCompanyName());
						experience.setContactName(employee.getEducationlist().get(i).getContactName());
						experience.setContactNumber(employee.getEducationlist().get(i).getContactNumber());
						experience.setFromDate(employee.getEducationlist().get(i).getFromDate());
						experience.setToDate(employee.getEducationlist().get(i).getToDate());
						experience.setEmployee(entitymanager.find(Employee.class, res.get(0).getEmployeeDetailsId()));
						experience.setStatus("Active");
						System.out.println("-----"+experience.getEmployee());
						if(employee.getEducationlist().get(i).getEmpexpFile() != null)
						{
							String ss=employee.getEducationlist().get(i).getEmpexpFile().getContentType();
							System.out.println("empexpFile  "+ss);
							String type = ss.substring(ss.lastIndexOf("/") + 1);
							String id=employee.getEmployeeid()+" "+employee.getEducationlist().get(i).getCompanyName();
							System.out.println("empexpFile  "+type);
							copyFile2(employee.getDob(),id, employee.getEducationlist().get(i).getEmpexpFile().getInputstream(), type);
							String path = ft.format(employee.getDob()) + "/" +id+ "." + type;
							experience.setFilePath(path);
							System.out.println("empfile --- "+employee.getEducationlist().get(i).getEmpexpFile().getSize());
						}
						entitymanager.persist(experience);
						entitymanager.flush();
						entitymanager.clear();
					}
				}catch(Exception e){
					
				}
			}
			status="success";
			logger.info("Designation size----->>>" + result.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			e.printStackTrace();
		} finally {

		}
		return status;
	}


	private void copyFile11(Date date, String fileName, InputStream inputstream, String n) {
		try {
			// Create Directory
			File files = new File("/home/ec2-user/File_Inacsys/Employee/Photos/"+ ft.format(date));
			if (!files.exists()) {
				files.mkdirs();
			} else {
				logger.debug("Alreday Found");
			}
			
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(files + "/" + fileName + "." + n));
			
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while ((read = inputstream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			inputstream.close();
			out.flush();
			out.close();
			
			logger.debug("New file created!");
			} catch (IOException e) {
			logger.debug(e.getMessage());
		}

	}
	
	private void passportcopyFile(Date date, String fileName, InputStream inputstream, String n) {
		try {
			// Create Directory
			File files = new File("/home/ec2-user/File_Inacsys/Employee/Passport/"+ ft.format(date));
			if (!files.exists()) {
				files.mkdirs();
			} else {
				logger.debug("Alreday Found");
			}
			
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(files + "/" + fileName + "." + n));
			
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while ((read = inputstream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			inputstream.close();
			out.flush();
			out.close();
			
			logger.debug("New file created!");
			} catch (IOException e) {
			logger.debug(e.getMessage());
		}

	}
	
	private void emiratescopyFile(Date date, String fileName, InputStream inputstream, String n) {
		try {
			// Create Directory
			File files = new File("/home/ec2-user/File_Inacsys/Employee/Emirates/"+ ft.format(date));
			if (!files.exists()) {
				files.mkdirs();
			} else {
				logger.debug("Alreday Found");
			}
			
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(files + "/" + fileName + "." + n));
			
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while ((read = inputstream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			inputstream.close();
			out.flush();
			out.close();
			
			logger.debug("New file created!");
			} catch (IOException e) {
			logger.debug(e.getMessage());
		}

	}
	
	private void licencecopyFile(Date date, String fileName, InputStream inputstream, String n) {
		try {
			// Create Directory
			File files = new File("/home/ec2-user/File_Inacsys/Employee/Licence/"+ ft.format(date));
			if (!files.exists()) {
				files.mkdirs();
			} else {
				logger.debug("Alreday Found");
			}
			
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(files + "/" + fileName + "." + n));
			
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while ((read = inputstream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			inputstream.close();
			out.flush();
			out.close();
			
			logger.debug("New file created!");
			} catch (IOException e) {
			logger.debug(e.getMessage());
		}

	}
	
	private void othercopyFile(Date date, String fileName, InputStream inputstream, String n) {
		try {
			// Create Directory
			File files = new File("/home/ec2-user/File_Inacsys/Employee/Other/"+ ft.format(date));
			if (!files.exists()) {
				files.mkdirs();
			} else {
				logger.debug("Alreday Found");
			}
			
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(files + "/" + fileName + "." + n));
			
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while ((read = inputstream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			inputstream.close();
			out.flush();
			out.close();
			
			logger.debug("New file created!");
			} catch (IOException e) {
			logger.debug(e.getMessage());
		}

	}
	
	private void copyFile1(Date date, String fileName, InputStream inputstream, String n) {
		try {
			// Create Directory
			File files = new File("/home/ec2-user/File_Inacsys/Employee/Qualification/"+ ft.format(date));
			if (!files.exists()) {
				files.mkdirs();
			} else {
				logger.debug("Alreday Found");
			}
			
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(files + "/" + fileName + "." + n));
			
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while ((read = inputstream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			inputstream.close();
			out.flush();
			out.close();
			
			logger.debug("New file created!");
			} catch (IOException e) {
			logger.debug(e.getMessage());
		}

	}
	
	private void copyFile2(Date date, String fileName, InputStream inputstream, String n) {
		try {
			// Create Directory
			File files = new File("/home/ec2-user/File_Inacsys/Employee/Experience/"+ ft.format(date));
			if (!files.exists()) {
				files.mkdirs();
			} else {
				logger.debug("Alreday Found");
			}
			
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(files + "/" + fileName + "." + n));
			
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while ((read = inputstream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			inputstream.close();
			out.flush();
			out.close();
			
			logger.debug("New file created!");
			} catch (IOException e) {
			logger.debug(e.getMessage());
		}

	}
	
	@Transactional(value = "transactionManager")
	public String employeeIdInfo(EmployeeDetail employee) throws DemoException {
		logger.info(" inside dao----->>>");Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		try {
			logger.info("inside try in dao---->>>");
			if(userType.equalsIgnoreCase("Maker")){
				if(employee.getStatus().equalsIgnoreCase("empView")){
					q = entitymanager.createQuery("from Employee where status=? and client_ID=? and user_ID=? ORDER BY createdDate DESC");
					q.setParameter(1, "inserted");
					q.setParameter(2, clientID);
					q.setParameter(3, userID);
				}
				else if(employee.getStatus().equalsIgnoreCase("empPayroll")){
					q = entitymanager.createQuery("from Employee where status=? and client_ID=? and user_ID=? and approvalStatus='Approved' ORDER BY createdDate DESC");
					q.setParameter(1, "inserted");
					q.setParameter(2, clientID);
					q.setParameter(3, userID);
				}
			}else{
				
				if (employee.getApproval()=="ApprovalData") {
						q = entitymanager.createQuery("from Employee where status=? and client_ID=? and approvalStatus='draft' ORDER BY createdDate DESC");
						q.setParameter(1, "inserted");
						q.setParameter(2, clientID);
				}
				else{
					if(employee.getStatus().equalsIgnoreCase("empView")){
						q = entitymanager.createQuery("from Employee where status=? and client_ID=? ORDER BY createdDate DESC");
						q.setParameter(1, "inserted");
						q.setParameter(2, clientID);
					}else if(employee.getStatus().equalsIgnoreCase("empPayroll")){
						q = entitymanager.createQuery("from Employee where status=? and client_ID=? and approvalStatus='Approved' ORDER BY createdDate DESC");
						q.setParameter(1, "inserted");
						q.setParameter(2, clientID);
					}
				}
			}
			List<Employee> result = (List<Employee>) q.getResultList();
			logger.info("size------>>>" + result.size());
			employee.setList(result);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String employeeNameInfo(EmployeeDetail employee)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.info(" inside dao----->>>");
		try {
			logger.info("inside try in dao---->>>");
			Query q = null;
			q = entitymanager
					.createQuery("from Employee where employeeName=? and status=? and client_ID=? ");
			q.setParameter(1, employee.getName());
			logger.info(" employee id-------->>>" + employee.getName());
			q.setParameter(2, "inserted");
			q.setParameter(3, clientID);
			List<Employee> result = (List<Employee>) q.getResultList();
			logger.info("size------>>>" + result.size());
			employee.setList(result);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public List<Employee> employeeNameInfo(EmployeeDetail employee,
			String golbalnamesearch) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.info(" inside dao----->>>");
		List<Employee> result = null;
		try {
			logger.info("inside try in dao---->>>");
			Query q = null;
			q = entitymanager
					.createQuery("from Employee where employeeName=? and status=? and client_ID=? ");
			q.setParameter(1, golbalnamesearch);
			logger.info(" employee id-------->>>" + golbalnamesearch);
			q.setParameter(2, "inserted");
			q.setParameter(3, clientID);
			result = (List<Employee>) q.getResultList();

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return result;
	}

	@Transactional(value = "transactionManager")
	public List<String> employeeId(List<String> emploeid) throws DemoException {
		logger.info(" inside dao----->>>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("inside try in dao---->>>");
			Query q = null;
			q = entitymanager
					.createQuery("select employeeId from Employee where status='inserted' and client_ID=? ");
			q.setParameter(1, clientID);
			emploeid = q.getResultList();
			logger.info("employee id size----->>>" + emploeid);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return emploeid;
	}

	@Transactional(value = "transactionManager")
	public List<String> employeeName(List<String> emploename)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.info(" inside dao----->>>");
		try {
			logger.info("inside try in dao---->>>");
			Query q = null;
			q = entitymanager
					.createQuery("select employeeName from Employee where status='inserted' and client_ID=? ");
			q.setParameter(1, clientID);
			emploename = q.getResultList();
			logger.info("employee name size----->>>" + emploename);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return emploename;
	}

	@Transactional(value = "transactionManager")
	public List<EmployeeDetail> getEmployeeDetail(EmployeeDetail employee)
			throws DemoException {
		logger.info(" inside dao----->>>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		List<EmployeeDetail> viewlist = null;
		List<Employee> detail = null;
		List<Qualification> qualification = null;
		List<Designation> designation = null;
		try {
			System.out.println("inside try in dao---->>>");
			Query q = null;
			q = entitymanager.createQuery("from Designation where type=? and client_ID=?");
			q.setParameter(1, employee.getDesignation());
			q.setParameter(2, clientID);
			designation = q.getResultList();
			System.out.println("designation size--->>>" + designation.size());
			int dcId = 0;
			if (designation.size() > 0) {
				dcId = designation.get(0).getDesignationCategoryId();
				System.out.println(" designation id------->>" + dcId);
			}
			q = entitymanager.createQuery("from Qualification where type=?  ");
			q.setParameter(1, employee.getQualification());
			
			qualification = q.getResultList();
			System.out.println("qualification size--->>>" + qualification.size());
			int qcId = 0;
			if (qualification.size() > 0) {
				qcId = qualification.get(0).getQualificationCategoryId();
				System.out.println(" qualification id------->>" + qcId);
			}
			q = entitymanager.createQuery("from Employee where employee_details_id=?");
			q.setParameter(1, employee.getEmployeeDetailsId());
			detail = q.getResultList();
			System.out.println("detail size--->>>" + detail.size());
			int edId = 0;
			if (detail.size() > 0) {
				edId = detail.get(0).getEmployeeDetailsId();
				System.out.println(" employee id------->>" + edId);
				viewlist = new ArrayList<EmployeeDetail>();
				EmployeeDetail employedetail = new EmployeeDetail();
				employedetail.setAddress(detail.get(0).getEmployeeAddress());
				System.out.println(" address---->>>"
						+ detail.get(0).getEmployeeAddress());
				employedetail.setBasicsalary(detail.get(0).getBasicSalary());
				employedetail.setDescription(detail.get(0).getDescriptionAny());
				employedetail.setDesignation(detail.get(0).getDesignation()
						.getType());
				employedetail.setDob(detail.get(0).getDateOfBirth());
				employedetail.setEmployeeid(detail.get(0).getEmployeeId());
				employedetail.setEntrydate(detail.get(0).getEntryDate());
				employedetail.setGender(detail.get(0).getGender());
				employedetail.setJoindate(detail.get(0).getDateOfJoin());
				employedetail.setMailid(detail.get(0).getEmailId());
				employedetail.setFreelancer(detail.get(0).getFreelancer());
				employedetail.setName(detail.get(0).getEmployeeName());
				employedetail.setPhno(detail.get(0).getPhoneNumber());
				employedetail.setRegid(detail.get(0).getRegistrationId());
				employedetail.setBankName(detail.get(0).getBankName());
				employedetail.setAccNo(detail.get(0).getAccountNo());
				employedetail.setStreetname(detail.get(0).getStreet());
				employedetail.setCity(detail.get(0).getCity());
				employedetail.setCountry(detail.get(0).getCountry());
				employedetail.setState(detail.get(0).getState());
				employedetail.setCode(detail.get(0).getPhn_code());
				employedetail.setPostalcode(detail.get(0).getPostal_code());
				employedetail.setPresent_streetname(detail.get(0).getPresent_street());
				employedetail.setPresent_city(detail.get(0).getPresent_city());
				employedetail.setPresent_country(detail.get(0).getPresent_country());
				employedetail.setPresent_state(detail.get(0).getPresent_state());
				employedetail.setPresent_postalcode(detail.get(0).getPresent_postal_code());
				employedetail.setPresent_phno(detail.get(0).getPresent_phno());
				employedetail.setPresent_code(detail.get(0).getPresent_phn_code());
				employedetail.setPath(detail.get(0).getFile_path());
				employedetail.setPassport_date(detail.get(0).getPassportdate());
				employedetail.setPassport_path(detail.get(0).getPassport());
				employedetail.setEmirates_date(detail.get(0).getEmiratesdate());
				employedetail.setEmirates_path(detail.get(0).getEmirates());
				employedetail.setLicence_date(detail.get(0).getLicencedate());
				employedetail.setLicence_path(detail.get(0).getLicence());
				employedetail.setOtherFilename(detail.get(0).getOther_filename());
				employedetail.setOtherfile_path(detail.get(0).getOther_file());
				employedetail.setCurrency(detail.get(0).getCurrency());
				employedetail.setCurrencyAmount(detail.get(0).getCurrency_amount());
				System.out.println("path "+employedetail.getPath());
				q=null;
				q=entitymanager.createQuery("from EmployeeQualification where employee_ID=? and status='Active'");
				q.setParameter(1, edId);
				List<EmployeeQualification> empqual=(List<EmployeeQualification>)q.getResultList();
				if(empqual.size()>0){
					for (int i = 0; i < empqual.size(); i++) {
						EmployeeDetail ed=new EmployeeDetail();
						ed.setCourse(empqual.get(i).getCourseName());
						ed.setYearofpassing(empqual.get(i).getYearOf_passing());
						ed.setUniversity(empqual.get(i).getInstituteName());
						ed.setMedium(empqual.get(i).getMediumInstruction());
						ed.setCertificate_path(empqual.get(i).getCertificatePath());
						employee.getQualificationlist().add(ed);
					}
					System.out.println("emp Qual size === "+employedetail.getQualificationlist().size());
				}
				
				q=null;
				q=entitymanager.createQuery("from Experience where employee_details_id=? and status='Active'");
				q.setParameter(1, edId);
				List<Experience> empex=(List<Experience>)q.getResultList();
				if(empex.size()>0){
					for (int i = 0; i < empex.size(); i++) {
						EmployeeDetail ed=new EmployeeDetail();
						ed.setCompanyName(empex.get(i).getCompanyName());
						ed.setFromDate(empex.get(i).getFromDate());
						ed.setToDate(empex.get(i).getToDate());
						ed.setContactName(empex.get(i).getContactName());
						ed.setContactNumber(empex.get(i).getContactNumber());
						ed.setExpCertificate_path(empex.get(i).getFilePath());
						employee.getEducationlist().add(ed);
					}
					System.out.println("emp Experience size === "+employedetail.getEducationlist().size());
				}
				viewlist.add(employedetail);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return viewlist;
	}

	@Transactional(value = "transactionManager")
	public List<EmployeeDetail> getEmployeeDetailEdit(EmployeeDetail employee)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		System.out.println(" inside dao----->>>");
		List<EmployeeDetail> viewlist = null;
		List<Employee> detail = null;Date date=new Date();
		List<Qualification> qualification = null;
		List<Designation> designation = null;BigDecimal currAmount=BigDecimal.valueOf(0);
		try {
			System.out.println("inside try in dao---->>>");
			Query q = null;
			q = entitymanager.createQuery("from Designation where type=? and client_ID=? ");
			q.setParameter(1, employee.getDesignation());
			q.setParameter(2, clientID);
			designation = q.getResultList();
			System.out.println("designation size--->>>" + designation.size());
			int dcId = 0;
			if (designation.size() > 0) {
				dcId = designation.get(0).getDesignationCategoryId();
				System.out.println(" designation id------->>" + dcId);
			}
			q = entitymanager.createQuery("from Qualification where type=? ");
			q.setParameter(1, employee.getQualification());
			qualification = q.getResultList();
			System.out.println("qualification size--->>>" + qualification.size());
			int qcId = 0;
			if (qualification.size() > 0) {
				qcId = qualification.get(0).getQualificationCategoryId();
				System.out.println(" qualification id------->>" + qcId);
			}
			System.out.println("employee id---"+ employee.getEmployeeDetailsId());
			q = entitymanager.createQuery("from Employee where employee_details_id=?");
			q.setParameter(1, employee.getEmployeeDetailsId());
			logger.debug(" registration id----->>>" + employee.getRegid());
			detail = q.getResultList();
			System.out.println("detail size--->>>" + detail.size());
			int edId = 0;
			if (detail.size() > 0) {
				edId = detail.get(0).getEmployeeDetailsId();
				System.out.println(" employee id------->>" + edId);
			}
			if (employee.getStatus().equalsIgnoreCase("register")) {
				viewlist = new ArrayList<EmployeeDetail>();
				EmployeeDetail employedetail = new EmployeeDetail();
				employedetail.setAddress(detail.get(0).getEmployeeAddress());
				System.out.println(" address---->>>"
						+ detail.get(0).getEmployeeAddress());
				employedetail.setBasicsalary(detail.get(0).getBasicSalary());
				employedetail.setDescription(detail.get(0).getDescriptionAny());
				employedetail.setDesignation(detail.get(0).getDesignation()
						.getType());
				employedetail.setDob(detail.get(0).getDateOfBirth());
				employedetail.setEmployeeid(detail.get(0).getEmployeeId());
				employedetail.setEntrydate(detail.get(0).getEntryDate());
				employedetail.setGender(detail.get(0).getGender());
				employedetail.setJoindate(detail.get(0).getDateOfJoin());
				employedetail.setMailid(detail.get(0).getEmailId());
				employedetail.setName(detail.get(0).getEmployeeName());
				employedetail.setPhno(detail.get(0).getPhoneNumber());
				employedetail.setRegid(detail.get(0).getRegistrationId());
				employedetail.setFreelancer(detail.get(0).getFreelancer());
				employedetail.setBankName(detail.get(0).getBankName());
				employedetail.setAccNo(detail.get(0).getAccountNo());
				//employedetail.setQualification(detail.get(0).getQualification()	.getType());
				employedetail.setStreetname(detail.get(0).getStreet());
				employedetail.setCity(detail.get(0).getCity());
				employedetail.setCountry(detail.get(0).getCountry());
				employedetail.setState(detail.get(0).getState());
				System.out.println("state-----"+employedetail.getState());
				employedetail.setCode(detail.get(0).getPhn_code());
				employedetail.setPostalcode(detail.get(0).getPostal_code());
				employedetail.setPresent_streetname(detail.get(0).getPresent_street());
				employedetail.setPresent_city(detail.get(0).getPresent_city());
				employedetail.setPresent_country(detail.get(0).getPresent_country());
				employedetail.setPresent_state(detail.get(0).getPresent_state());
				System.out.println("state-----"+employedetail.getPresent_state());
				employedetail.setPresent_postalcode(detail.get(0).getPresent_postal_code());
				employedetail.setPresent_phno(detail.get(0).getPresent_phno());
				employedetail.setPresent_code(detail.get(0).getPresent_phn_code());
				employedetail.setPath(detail.get(0).getFile_path());
				employedetail.setPassport_date(detail.get(0).getPassportdate());
				employedetail.setPassport_path(detail.get(0).getPassport());
				employedetail.setEmirates_date(detail.get(0).getEmiratesdate());
				employedetail.setEmirates_path(detail.get(0).getEmirates());
				employedetail.setLicence_date(detail.get(0).getLicencedate());
				employedetail.setLicence_path(detail.get(0).getLicence());
				employedetail.setOtherFilename(detail.get(0).getOther_filename());
				employedetail.setOtherfile_path(detail.get(0).getOther_file());
				employedetail.setCurrency(detail.get(0).getCurrency());
				System.out.println("currency amount"+detail.get(0).getCurrency_amount());
				employedetail.setCurrencyAmount(detail.get(0).getCurrency_amount());
				q=null;
				q=entitymanager.createQuery("from EmployeeQualification where employee_ID=? and status='Active'");
				q.setParameter(1, edId);
				List<EmployeeQualification> empqual=(List<EmployeeQualification>)q.getResultList();
				if(empqual.size()>0){
					for (int i = 0; i < empqual.size(); i++) {
						EmployeeDetail ed=new EmployeeDetail();
						ed.setSerialNo(String.valueOf(i+1));
						ed.setCourse(empqual.get(i).getCourseName());
						ed.setYearofpassing(empqual.get(i).getYearOf_passing());
						ed.setUniversity(empqual.get(i).getInstituteName());
						ed.setMedium(empqual.get(i).getMediumInstruction());
						ed.setCertificate_path(empqual.get(i).getCertificatePath());
						ed.setQualID(""+empqual.get(i).getQualification_ID());
						employee.getQualificationlist().add(ed);
					}
					System.out.println("emp Qual size === "+employedetail.getQualificationlist().size());
				}
				
				q=null;
				q=entitymanager.createQuery("from Experience where employee_details_id=? and status='Active'");
				q.setParameter(1, edId);
				List<Experience> empex=(List<Experience>)q.getResultList();
				if(empex.size()>0){
					for (int i = 0; i < empex.size(); i++) {
						EmployeeDetail ed=new EmployeeDetail();
						ed.setSerialNo1(String.valueOf(i+1));
						ed.setCompanyName(empex.get(i).getCompanyName());
						ed.setFromDate(empex.get(i).getFromDate());
						ed.setToDate(empex.get(i).getToDate());
						ed.setContactName(empex.get(i).getContactName());
						ed.setContactNumber(empex.get(i).getContactNumber());
						ed.setExpCertificate_path(empex.get(i).getFilePath());
						ed.setExpID(""+empex.get(i).getExperience_ID());
						employee.getEducationlist().add(ed);
					}
					System.out.println("emp Experience size === "+employedetail.getEducationlist().size());
				}
				viewlist.add(employedetail);
			} else if (employee.getStatus().equalsIgnoreCase("update")) {
				System.out.println("id "+edId);
				Employee edit = entitymanager.find(Employee.class, edId);
				edit.setBasicSalary(employee.getBasicsalary());
				edit.setDateOfBirth(employee.getDob());
				edit.setDateOfJoin(employee.getJoindate());
				edit.setDescriptionAny(employee.getDescription());
				edit.setEmailId(employee.getMailid());
				edit.setEmployeeAddress(employee.getAddress());
				edit.setEmployeeId(employee.getEmployeeid());
				edit.setEmployeeName(employee.getName());
				edit.setEntryDate(employee.getEntrydate());
				edit.setGender(employee.getGender());
				edit.setPhoneNumber(employee.getPhno());
				edit.setRegistrationId(employee.getRegid());
				edit.setFreelancer(employee.getFreelancer());
				edit.setBankName(employee.getBankName());
				edit.setAccountNo(employee.getAccNo());
				edit.setStreet(employee.getStreetname());
				edit.setCity(employee.getCity());
				edit.setCountry(employee.getCountry());
				edit.setState(employee.getState());
				edit.setPhn_code(employee.getCode());
				edit.setCurrency(employee.getCurrency());
				edit.setPostal_code(employee.getPostalcode());
				edit.setPresent_street(employee.getPresent_streetname());
				edit.setPresent_city(employee.getPresent_city());
				edit.setPresent_country(employee.getPresent_country());
				edit.setPresent_state(employee.getPresent_state());
				edit.setPresent_postal_code(employee.getPresent_postalcode());
				edit.setPresent_phno(employee.getPresent_phno());
				edit.setPresent_phn_code(employee.getPresent_code());
				if(employee.getCurrency().equalsIgnoreCase(employee.getBaseCurrency())){
					currAmount=new BigDecimal(employee.getBasicsalary());
				}else{
					currAmount=CurrencyConverter.findExchangeRateAndConvert(employee.getCurrency(),employee.getBaseCurrency(),employee.getBasicsalary());
				}
				edit.setCurrency_amount(String.valueOf(currAmount));
				edit.setDesignation(entitymanager.find(Designation.class, dcId));
				edit.setUpdatedDate(date);
				System.out.println("empfile "+employee.getEmpFile());
				//edit.setQualification(entitymanager.find(Qualification.class,qcId));
				if(employee.getEmpFile() != null)
				{
					String ss=employee.getEmpFile().getContentType();
					System.out.println("empFile  "+ss);
					String type = ss.substring(ss.lastIndexOf("/") + 1);
					System.out.println("transFile  "+type);
					copyFile11(employee.getDob(),employee.getEmployeeid(), employee.getEmpFile().getInputstream(), type);
					String path = ft.format(employee.getDob()) + "/" +employee.getEmployeeid()+ "." + type;
					edit.setFile_path(path);
					System.out.println("empfile --- "+employee.getEmpFile().getSize());
				}
				if(employee.getPassportFile() != null)
				{
					String ss=employee.getPassportFile().getContentType();
					System.out.println("passportfile  "+ss);
					String type = ss.substring(ss.lastIndexOf("/") + 1);
					System.out.println("passportfile  "+type);
					String id=employee.getEmployeeid()+" "+"Passport";
					passportcopyFile(employee.getDob(),id, employee.getPassportFile().getInputstream(), type);
					String path = ft.format(employee.getDob()) + "/" +employee.getEmployeeid()+" "+"Passport"+ "." + type;
					edit.setPassport(path);
					System.out.println("empfile --- "+employee.getPassportFile().getSize());
				}
				
				if(employee.getEmiratesFile() != null)
				{
					String ss=employee.getEmiratesFile().getContentType();
					System.out.println("emirates  "+ss);
					String type = ss.substring(ss.lastIndexOf("/") + 1);
					System.out.println("emirates  "+type);
					String id=employee.getEmployeeid()+" "+"Emirates";
					emiratescopyFile(employee.getDob(),id, employee.getEmiratesFile().getInputstream(), type);
					String path = ft.format(employee.getDob()) + "/" +employee.getEmployeeid()+" "+"Emirates"+ "." + type;
					edit.setEmirates(path);
					System.out.println("emirates --- "+employee.getEmiratesFile().getSize());
				}
				
				if(employee.getLicenceFile() != null)
				{
					String ss=employee.getLicenceFile().getContentType();
					System.out.println("licence  "+ss);
					String type = ss.substring(ss.lastIndexOf("/") + 1);
					System.out.println("licence  "+type);
					String id=employee.getEmployeeid()+" "+"Licence";
					licencecopyFile(employee.getDob(),id, employee.getLicenceFile().getInputstream(), type);
					String path = ft.format(employee.getDob()) + "/" +employee.getEmployeeid()+" "+"Licence"+ "." + type;
					edit.setLicence(path);
					System.out.println("licence --- "+employee.getLicenceFile().getSize());
				}
				
				if(employee.getOtherFile() != null)
				{
					String ss=employee.getOtherFile().getContentType();
					System.out.println("otherfile  "+ss);
					String type = ss.substring(ss.lastIndexOf("/") + 1);
					System.out.println("otherfile  "+type);
					String id=employee.getEmployeeid()+" "+employee.getOtherFilename();
					othercopyFile(employee.getDob(),id, employee.getOtherFile().getInputstream(), type);
					String path = ft.format(employee.getDob()) + "/" +employee.getEmployeeid()+" "+employee.getOtherFilename()+ "." + type;
					edit.setOther_file(path);
					System.out.println("otherfile --- "+employee.getEmpFile().getSize());
				}
				entitymanager.merge(edit);
				
				//Qualification
				System.out.println("Qualificationlist == "+employee.getQualificationlist().size());
				System.out.println("Experiencelist == "+employee.getEducationlist().size());
				if(employee.getQualificationlist().size()>0){
					for (int j = 0; j < employee.getQualificationlist().size(); j++) {
						System.out.println("qua id "+employee.getQualificationlist().get(j).getQualID());
						if(employee.getQualificationlist().get(j).getQualID().equals("")){
							EmployeeQualification emp=new EmployeeQualification();
							emp.setCourseName(employee.getQualificationlist().get(j).getCourse());
							emp.setCertificatePath(employee.getQualificationlist().get(j).getCertificate_path());
							System.out.println("university "+employee.getQualificationlist().get(j).getUniversity());
							emp.setInstituteName(employee.getQualificationlist().get(j).getUniversity());
							emp.setMediumInstruction(employee.getQualificationlist().get(j).getMedium());
							emp.setYearOf_passing(employee.getQualificationlist().get(j).getYearofpassing());
							emp.setEmployee(entitymanager.find(Employee.class, edId));
							if(employee.getQualificationlist().get(j).getEmpQual_File() != null)
							{
								String ss=employee.getQualificationlist().get(j).getEmpQual_File().getContentType();
								System.out.println("empqualFile  "+ss);
								String type = ss.substring(ss.lastIndexOf("/") + 1);
								String id=employee.getEmployeeid()+" "+employee.getQualificationlist().get(j).getYearofpassing();
								System.out.println("empexpFile  "+type);
								copyFile1(employee.getDob(),id, employee.getQualificationlist().get(j).getEmpQual_File().getInputstream(), type);
								String path = ft.format(employee.getDob()) + "/" +id+ "." + type;
								emp.setCertificatePath(path);
								System.out.println("empfile --- "+employee.getQualificationlist().get(j).getEmpQual_File().getSize());
							}
							emp.setStatus("Active");
							entitymanager.persist(emp);
							entitymanager.flush();
							entitymanager.clear();
						}
						else{
							int qid=Integer.parseInt(employee.getQualificationlist().get(j).getQualID());
							System.out.println("qid"+qid);
							EmployeeQualification emp=entitymanager.find(EmployeeQualification.class, qid);
							emp.setCourseName(employee.getQualificationlist().get(j).getCourse());
							emp.setCertificatePath(employee.getQualificationlist().get(j).getCertificate_path());
							System.out.println("university "+employee.getQualificationlist().get(j).getUniversity());
							emp.setInstituteName(employee.getQualificationlist().get(j).getUniversity());
							emp.setMediumInstruction(employee.getQualificationlist().get(j).getMedium());
							emp.setYearOf_passing(employee.getQualificationlist().get(j).getYearofpassing());
							emp.setEmployee(entitymanager.find(Employee.class, edId));
							if(employee.getQualificationlist().get(j).getEmpQual_File() != null)
							{
								String ss=employee.getQualificationlist().get(j).getEmpQual_File().getContentType();
								System.out.println("empqualFile  "+ss);
								String type = ss.substring(ss.lastIndexOf("/") + 1);
								String id=employee.getEmployeeid()+" "+employee.getQualificationlist().get(j).getYearofpassing();
								System.out.println("empexpFile  "+type);
								copyFile1(employee.getDob(),id, employee.getQualificationlist().get(j).getEmpQual_File().getInputstream(), type);
								String path = ft.format(employee.getDob()) + "/" +id+ "." + type;
								emp.setCertificatePath(path);
								System.out.println("empfile --- "+employee.getQualificationlist().get(j).getEmpQual_File().getSize());
							}
							entitymanager.merge(emp);
							
						}
					}
				}
				
				
				//Experience
				if(employee.getEducationlist().size()>0){
					for (int i = 0; i < employee.getEducationlist().size(); i++) {
						System.out.println("ExpID == "+employee.getEducationlist().get(i).getExpID());
						if(employee.getEducationlist().get(i).getExpID().equals(""))
						{
							Experience experience=new Experience();
							experience.setCompanyName(employee.getEducationlist().get(i).getCompanyName());
							experience.setContactName(employee.getEducationlist().get(i).getContactName());
							experience.setContactNumber(employee.getEducationlist().get(i).getContactNumber());
							experience.setFromDate(employee.getEducationlist().get(i).getFromDate());
							experience.setToDate(employee.getEducationlist().get(i).getToDate());
							experience.setEmployee(entitymanager.find(Employee.class, edId));
							System.out.println("-----"+experience.getEmployee());
							if(employee.getEducationlist().get(i).getEmpexpFile() != null)
							{
								String ss=employee.getEducationlist().get(i).getEmpexpFile().getContentType();
								System.out.println("empexpFile  "+ss);
								String type = ss.substring(ss.lastIndexOf("/") + 1);
								String id=employee.getEmployeeid()+" "+employee.getEducationlist().get(i).getCompanyName();
								System.out.println("empexpFile  "+type);
								copyFile2(employee.getDob(),id, employee.getEducationlist().get(i).getEmpexpFile().getInputstream(), type);
								String path = ft.format(employee.getDob()) + "/" +id+ "." + type;
								experience.setFilePath(path);
								System.out.println("empfile --- "+employee.getEducationlist().get(i).getEmpexpFile().getSize());
							}
							experience.setStatus("Active");
							entitymanager.persist(experience);
							entitymanager.flush();
							entitymanager.clear();
						}
						else{
							int exid=Integer.parseInt(employee.getEducationlist().get(i).getExpID());
							System.out.println("exid =="+exid);
							Experience experience=entitymanager.find(Experience.class, exid);
							experience.setCompanyName(employee.getEducationlist().get(i).getCompanyName());
							experience.setContactName(employee.getEducationlist().get(i).getContactName());
							experience.setContactNumber(employee.getEducationlist().get(i).getContactNumber());
							experience.setFromDate(employee.getEducationlist().get(i).getFromDate());
							experience.setToDate(employee.getEducationlist().get(i).getToDate());
							experience.setEmployee(entitymanager.find(Employee.class, edId));
							System.out.println("-----"+experience.getEmployee());
							if(employee.getEducationlist().get(i).getEmpexpFile() != null)
							{
								String ss=employee.getEducationlist().get(i).getEmpexpFile().getContentType();
								System.out.println("empexpFile  "+ss);
								String type = ss.substring(ss.lastIndexOf("/") + 1);
								String id=employee.getEmployeeid()+" "+employee.getEducationlist().get(i).getCompanyName();
								System.out.println("empexpFile  "+type);
								copyFile2(employee.getDob(),id, employee.getEducationlist().get(i).getEmpexpFile().getInputstream(), type);
								String path = ft.format(employee.getDob()) + "/" +id+ "." + type;
								experience.setFilePath(path);
								System.out.println("empfile --- "+employee.getEducationlist().get(i).getEmpexpFile().getSize());
							}
							entitymanager.merge(experience);
						
						}
						
					}
				}
				
			} else if (employee.getStatus().equalsIgnoreCase("delete")) {
				Employee dele = entitymanager.find(Employee.class, edId);
				dele.setStatus("delete");
				entitymanager.merge(dele);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Inside Exception", e);
		} finally {

		}
		return viewlist;
	}

	/* udhaya 22.1.2015 */

	@Transactional(value = "transactionManager")
	public ArrayList<String> changeEvent(String s, ArrayList<String> list1)
			throws DemoException {
		System.out.println(" inside dao----->>>");
		ArrayList<String> designation = new ArrayList<String>();
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			q = entitymanager.createQuery("from Designation where type=? and client_ID=? ");
			q.setParameter(1, s);
			q.setParameter(2, clientID);
			List<Designation> result = (List<Designation>) q.getResultList();
			int dcid = 0;
			if (result.size() > 0) {
				int i = 0;
				dcid = result.get(i).getDesignationCategoryId();
				System.out.println("designation id---->>>" + dcid);
				Query q1 = null;
				q1 = entitymanager
						.createQuery("from Employee where designation_category_id=? and client_ID=? ");
				System.out.println("employee id-------->>>>");
				q1.setParameter(1, dcid);
				System.out.println("size----->>>" + dcid);
				q1.setParameter(2, clientID);
				List<Employee> result1 = (List<Employee>) q1.getResultList();
				System.out.println("list size---->>>" + result1.size());
				for (Employee re : result1) {
					System.out.println("inside for loop-------->>>");
					designation.add(re.getEmployeeId());
					System.out.println("employee id---->>>" + designation
							+ ">>>>>>>>>>" + re.getEmployeeId());
				}
				System.out.println("outside for loop------>>>" + designation
						+ ">>>>>>>>>>" + designation.size());
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return designation;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<String> changeEvent1(String s, ArrayList<String> list2)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		System.out.println(" inside dao----->>>");
		ArrayList<String> designation1 = new ArrayList<String>();
		Query q = null;
		try {
			q = entitymanager.createQuery("from Designation where type=?");
			q.setParameter(1, s);
			List<Designation> result = (List<Designation>) q.getResultList();
			int dcid = 0;
			if (result.size() > 0) {
				int i = 0;
				dcid = result.get(i).getDesignationCategoryId();
				System.out.println("designation id---->>>" + dcid);
				Query q1 = null;
				q1 = entitymanager
						.createQuery("from Employee where designation_category_id=? and client_ID=? ");
				logger.info("employee id-------->>>>");
				q1.setParameter(1, dcid);
				logger.info("size----->>>" + dcid);
				q1.setParameter(2, clientID);
				List<Employee> result1 = (List<Employee>) q1.getResultList();
				logger.info("list size---->>>" + result1.size());
				for (Employee re : result1) {
					logger.info("inside for loop-------->>>");
					designation1.add(re.getEmployeeName());
					logger.info("employee id---->>>" + designation1
							+ ">>>>>>>>>>" + re.getEmployeeName());
				}
				logger.info("outside for loop------>>>" + designation1
						+ ">>>>>>>>>>" + designation1.size());
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return designation1;
	}

	/** generate payroll **/
	@Transactional(value = "transactionManager")
	public List<Employee> payroll(List<Employee> list3, EmployeePayroll save)
			throws DemoException {
		logger.info("inside dao------->>>>");
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		List<Employee> employee1 = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			logger.info("try >>>>>>>>");
			List<Year> yearz = null;
			List<Month> monthz = null;
			List<Employee> empz = null;
			List<Payroll> pay = null;
			Query y, emp, m, n = null;
			int yid = 0;
			int mid = 0;
			int eid = 0;
			if(userType.equalsIgnoreCase("Maker")){
				emp = entitymanager.createQuery("from Employee where employeeId=? and client_ID=? and user_ID=?");
				emp.setParameter(1, save.employeeid);
				emp.setParameter(2, clientID);
				emp.setParameter(3, userID);
			}else{
				emp = entitymanager.createQuery("from Employee where employeeId=? and client_ID=?");
				emp.setParameter(1, save.employeeid);
				emp.setParameter(2, clientID);
			}
			empz = emp.getResultList();
			if (empz.size() > 0) {
				eid = empz.get(0).getEmployeeDetailsId();
				logger.info("emp id : " + eid);
			}

			m = entitymanager.createQuery("from Month where type=?");
			m.setParameter(1, save.month);
			monthz = m.getResultList();
			if (monthz.size() > 0) {
				mid = monthz.get(0).getMonthId();
				logger.info("month id : " + mid);
			}

			y = entitymanager.createQuery("from Year where type=?");
			y.setParameter(1, save.year);
			yearz = y.getResultList();
			if (yearz.size() > 0) {
				yid = yearz.get(0).getYearId();
				logger.info("year id : " + yid);
			}
			n = entitymanager
					.createQuery("from Payroll where month_Id=? and year_Id=? and employee_details_id=? and status='inserted' ");
			n.setParameter(1, mid);
			n.setParameter(2, yid);
			n.setParameter(3, eid);
			pay = n.getResultList();
			if (pay.size() > 0) {
				logger.info("inside condition");
				throw new DemoException(
						"This employee id got the payroll already");
			} else {
				logger.info("inside try in dao---------->>>");
				if(userType.equalsIgnoreCase("Maker")){
					q = entitymanager.createQuery("from Employee where employeeId=? and client_ID=? and user_ID=?");
					q.setParameter(1, save.getEmployeeid());
					System.out.println("employee id----->>>" + save.getEmployeeid());
					q.setParameter(2, clientID);
					q.setParameter(3, userID);
				}else{
					q = entitymanager.createQuery("from Employee where employeeId=? and client_ID=?");
					q.setParameter(1, save.getEmployeeid());
					System.out.println("employee id----->>>" + save.getEmployeeid());
					q.setParameter(2, clientID);
				}
				employee1 = q.getResultList();
				logger.info("size id----->>>" + employee1.size());
				if (employee1.size() > 0) {
					logger.info("inside if loop---->>>>");
					save.setList3(employee1);
				}
			}
			Query g = null;
			Query c = null;
			Query e = null;
			String commissionAmount = "0";
			int buyerId = 0;
			List<SalesRecord> salrec = null;
			List<I0021> list21 = null;
			List<I0032> list32 = null;
			String date1 = "";
			String date2 = "";
			String month = "";
			if (mid < 10) {
				month = "0" + mid;
			} else {
				month = "" + mid;
			}
			date1 = "01/" + month + "/" + save.year;
			date2 = "31/" + month + "/" + save.year;
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String datee1 = date1;
			String datee2 = date2;
			int sid = 0;
			String totCom = "";
			BigDecimal bd = BigDecimal.valueOf(0);
			int i = 0;
			e = entitymanager
					.createQuery("from I0032 where freelancerName=? and status='in' and client_ID=? and user_ID=?");
			e.setParameter(1, save.getEmployeeid());
			e.setParameter(2, clientID);
			e.setParameter(3, userID);
			list32 = e.getResultList();
			if (list32.size() > 0) {
				try {
					Date D1 = formatter.parse(datee1);
					Date D2 = formatter.parse(datee2);
					for (i = 0; i < list32.size(); i++) {
						buyerId = list32.get(i).getBuyer_ID();
						c = entitymanager
								.createQuery("from I0021 where salesOrderDate between ? and ? and buyer_ID=? and client_ID=? and user_ID=?");
						c.setParameter(1, D1);
						c.setParameter(2, D2);
						c.setParameter(3, buyerId);
						c.setParameter(4, clientID);
						c.setParameter(5, userID);
						list21 = c.getResultList();

						if (list21.size() > 0) {
							for (int j = 0; j < list21.size(); j++) {
								sid = list21.get(j).getSales_ID();
								logger.info("sales id-->> " + sid);

								g = entitymanager
										.createQuery("from SalesRecord where sales_ID=?");
								g.setParameter(1, sid);
								salrec = g.getResultList();
								if (salrec.size() > 0) {
									logger.info("salrec.size()-->> "
											+ salrec.size());
									for (int k = 0; k < salrec.size(); k++) {

										logger.info("salrec.comsn-->> "
												+ salrec.get(k).getTotcmsion());
										if (salrec.get(k).getTotcmsion() == null) {
											bd = BigDecimal.valueOf(0);
										} else {
											totCom = salrec.get(k)
													.getTotcmsion();
										}
										bd = bd.add(new BigDecimal(totCom));

									}

									logger.info("tot coms -->> " + bd);
								}

							}
						}
					}
					save.setCommission("" + bd);
				} catch (java.text.ParseException e1) {

					e1.printStackTrace();
				}
			} else {
				save.setCommission("0");
			}
		}

		finally {

		}
		return employee1;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<String> changeEvent2(String s, ArrayList<String> list1)
			throws DemoException {
		logger.info(" inside dao----->>>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<String> designation = new ArrayList<String>();
		Query q = null;
		try {
			q = entitymanager.createQuery("from Designation where type=? and client_ID=? ");
			q.setParameter(1, s);
			q.setParameter(2, clientID);
			List<Designation> result = (List<Designation>) q.getResultList();
			int dcid = 0;
			if (result.size() > 0) {
				int i = 0;
				dcid = result.get(i).getDesignationCategoryId();
				logger.info("designation id---->>>" + dcid);
				Query q1 = null;
				q1 = entitymanager
						.createQuery("from Employee where designation_category_id=? and status=? and payrollStatus=? and client_ID=?");
				logger.info("employee id-------->>>>");
				q1.setParameter(1, dcid);
				logger.info("size----->>>" + dcid);
				q1.setParameter(2, "inserted");
				q1.setParameter(3, "payroll generated");
				q1.setParameter(4, clientID);
				List<Employee> result1 = (List<Employee>) q1.getResultList();
				logger.info("list size---->>>" + result1.size());
				for (Employee re : result1) {
					logger.info("inside for loop-------->>>");
					designation.add(re.getEmployeeId());
					logger.info("employee id---->>>" + designation
							+ ">>>>>>>>>>" + re.getEmployeeId());
				}
				logger.info("outside for loop------>>>" + designation
						+ ">>>>>>>>>>" + designation.size());
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return designation;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<String> changeEvent3(String s, ArrayList<String> list2)
			throws DemoException {
		logger.info(" inside dao----->>>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<String> designation1 = new ArrayList<String>();
		Query q = null;
		try {
			q = entitymanager.createQuery("from Designation where type=? and client_ID=?");
			q.setParameter(1, s);
			q.setParameter(2, clientID);
			List<Designation> result = (List<Designation>) q.getResultList();
			int dcid = 0;
			if (result.size() > 0) {
				int i = 0;
				dcid = result.get(i).getDesignationCategoryId();
				logger.info("designation id---->>>" + dcid);
				Query q1 = null;
				q1 = entitymanager
						.createQuery("from Employee where designation_category_id=? and status=? and payrollStatus=? and client_ID=? ");
				logger.info("employee id-------->>>>");
				q1.setParameter(1, dcid);
				logger.info("size----->>>" + dcid);
				q1.setParameter(2, "inserted");
				q1.setParameter(3, "payroll generated");
				q1.setParameter(4, clientID);
				List<Employee> result1 = (List<Employee>) q1.getResultList();
				logger.info("list size---->>>" + result1.size());
				for (Employee re : result1) {
					logger.info("inside for loop-------->>>");
					designation1.add(re.getEmployeeName());
					logger.info("employee id---->>>" + designation1
							+ ">>>>>>>>>>" + re.getEmployeeName());
				}
				logger.info("outside for loop------>>>" + designation1
						+ ">>>>>>>>>>" + designation1.size());
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return designation1;
	}

	public String payroll1(EmployeePayroll pay) throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager.createQuery("from Employee where employeeId=? and client_ID=? ");
			q.setParameter(1, pay.getEmployeeid());
			q.setParameter(2, clientID);
			logger.info(pay.getEmployeeid());
			List<Employee> res = (List<Employee>) q.getResultList();
			int sdid = 0;
			if (res.size() > 0) {
				sdid = res.get(0).getEmployeeDetailsId();
				logger.info("staff id---->" + sdid);
				q = entitymanager
						.createQuery("from Payroll where payrollNumber=?");
				q.setParameter(1, pay.getPayrollno());
				List<Payroll> result = (List<Payroll>) q.getResultList();
				logger.info("paylist-------->");
				int edid = 0;
				if (result.size() > 0) {
					edid = result.get(0).getPayrollId();
					logger.info("employee detail id---?" + edid);
				}
			}
			pay.setList3(res);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public String search(EmployeePayroll pay) throws DemoException {
		List<EmployeePayroll> data = new ArrayList<EmployeePayroll>();
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			q = entitymanager
					.createQuery("from payroll where month_id=? and year_id=?");
			q.setParameter(1, pay.getMonth());
			q.setParameter(2, pay.getYear());
			logger.info(pay.getMonth());
			List<Month> res = (List<Month>) q.getResultList();
			int mdid = 0;
			int i = 0;
			if (res.size() > 0) {
				mdid = res.get(i).getMonthId();
				logger.info("month id---->" + mdid);
				q = entitymanager.createQuery("from Payroll where month_id=?");
				q.setParameter(1, mdid);
				List<Payroll> result = (List<Payroll>) q.getResultList();
				logger.info("paylist-------->" + result.size());
				int pdid = 0;
				if (result.size() > 0) {
					for (i = 0; i < result.size(); i++) {
						pdid = result.get(i).getPayrollId();
						logger.info("payroll detail id---?" + pdid);
					}
					q = entitymanager.createQuery("from Year where type=?");
					q.setParameter(1, pay.getYear());
					logger.info(pay.getYear());
					List<Year> res1 = (List<Year>) q.getResultList();
					int ydid = 0;
					int j = 0;
					if (res1.size() > 0) {
						ydid = res1.get(j).getYearId();
						logger.info("year id---->" + ydid);
						q = entitymanager
								.createQuery("from Payroll where year_id=?");
						q.setParameter(1, ydid);
						List<Payroll> result1 = (List<Payroll>) q
								.getResultList();
						logger.info("paylist-------->" + result.size());
						int pdid1 = 0;
						if (result1.size() > 0) {
							for (j = 0; j < result1.size(); j++) {
								pdid1 = result1.get(j).getPayrollId();
								logger.info("payroll detail id---?" + pdid1);
							}
						}
						for (j = 0; j < result1.size(); j++) {
							for (i = 0; i < result.size(); i++) {
								if (result.get(i).getPayrollId() == result1
										.get(j).getPayrollId()) {
									logger.info("monthid-------->>>"
											+ result.get(i).getPayrollId()
											+ "year id-------->>>"
											+ result1.get(j).getPayrollId());
									EmployeePayroll payroll = new EmployeePayroll();
									payroll.setAdvanceamount(""
											+ result1.get(j).getAdvanceAmount());
									payroll.setCommission(result1.get(j)
											.getCommission());
									payroll.setOvertime(result1.get(j)
											.getOverTime());
									payroll.setMonth(result1.get(j).getMonth()
											.getType());
									data.add(payroll);
									pay.setDatalist(data);
								}
							}
						}
					}
				}

				q = entitymanager
						.createQuery("from Employee where payroll_id=? and client_ID=?  ");
				q.setParameter(1, pdid);
				q.setParameter(2, clientID);
				List<Employee> result1 = (List<Employee>) q.getResultList();
				logger.info("paylist-------->");
				int edid = 0;
				if (result1.size() > 0) {
					edid = result1.get(0).getEmployeeDetailsId();
					logger.info("employee detail id---?" + edid);
				}
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	/** search by month & designation & emp id **/
	@Transactional(value = "transactionManager")
	public String search2(EmployeePayroll pay) throws DemoException {
		Query q = null;
		Query emp, e1 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query d = null;
		Query m = null;
		List<Employee> empz, empz1 = null;
		List<Month> monthz = null;
		List<Designation> desnz = null;
		List<Payroll> payrollz = null;
		List<EmployeePayroll> value = new ArrayList<EmployeePayroll>();
		int newid = 0;
		int mid = 0, eid = 0, did = 0;
		try {
			logger.info("inside dao >>>>> search 2");
			emp = entitymanager.createQuery("from Employee where employeeId=? and client_ID=? ");
			emp.setParameter(1, pay.employeeid1);
			emp.setParameter(2, clientID);
			empz = emp.getResultList();
			if (empz.size() > 0) {
				eid = empz.get(0).getEmployeeDetailsId();
				logger.info("emp id : " + eid);
			}
			d = entitymanager.createQuery("from Designation where type=? and client_ID=?");
			d.setParameter(1, pay.designation1);
			d.setParameter(2, clientID);
			desnz = d.getResultList();
			if (desnz.size() > 0) {
				did = desnz.get(0).getDesignationCategoryId();
				logger.info("desn id : " + did);
			}
			m = entitymanager.createQuery("from Month where type=?");
			m.setParameter(1, pay.month1);
			monthz = m.getResultList();
			if (monthz.size() > 0) {
				mid = monthz.get(0).getMonthId();
				logger.info("month id : " + mid);
			}
			e1 = entitymanager
					.createQuery("from Employee where designation_category_id=? and employeeId=?");
			e1.setParameter(1, did);
			e1.setParameter(2, pay.employeeid1);
			empz1 = e1.getResultList();
			logger.info("new list size --- " + empz1.size());
			if (empz1.size() > 0) {
				m = entitymanager
						.createQuery("from Payroll where month_Id=? and employee_details_id=? and status='inserted' ");
				m.setParameter(1, mid);
				m.setParameter(2, eid);
				payrollz = m.getResultList();
			}
			logger.info("size of payrollz" + payrollz.size());
			newid = payrollz.get(0).getPayrollId();
			logger.info("list size " + payrollz.size() + "--- " + newid);

			if (payrollz.size() > 0) {
				logger.info("inside dao >> if ");
				EmployeePayroll payz = new EmployeePayroll();
				payz.setDesignation1(payrollz.get(0).getEmployee()
						.getDesignation().getType());
				payz.setName(payrollz.get(0).getEmployee().getEmployeeName());
				payz.setTodayDate(payrollz.get(0).getPayrollDate());
				payz.setPayrollno(payrollz.get(0).getPayrollNumber());
				payz.setTotalsalary("" + payrollz.get(0).getTotalSalary());
				payz.setEmployeeid1(payrollz.get(0).getEmployee()
						.getEmployeeId());
				// payz.setPayrolldate(payrollz.get(0).getPayrollDate());
				payz.setAdvanceamount("" + payrollz.get(0).getAdvanceAmount());
				payz.setBasicsalary("" + payrollz.get(0).getTotalSalary());
				payz.setWorkingdays(payrollz.get(0).getWorkingDays());
				payz.setCommission(payrollz.get(0).getCommission());
				payz.setOvertime(payrollz.get(0).getOverTime());
				value.add(payz);
				pay.setVallist(value);
				;

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	/** search by month & year **/
	public String search1(EmployeePayroll pay) throws DemoException {
		Query q = null;
		Query y = null;
		Query m = null;
		List<Payroll> payrollz = null;
		List<Month> monthz = null;
		List<Year> yearz = null;
		List<EmployeePayroll> value = new ArrayList<EmployeePayroll>();
		int mid = 0, yid = 0;
		try {
			logger.info("inside dao++++++");
			m = entitymanager.createQuery("from Month where type=?");
			m.setParameter(1, pay.months);
			monthz = m.getResultList();
			if (monthz.size() > 0) {
				mid = monthz.get(0).getMonthId();
				logger.info("month id : " + mid);
			}
			y = entitymanager.createQuery("from Year where type=?");
			y.setParameter(1, pay.year);
			yearz = y.getResultList();
			if (yearz.size() > 0) {
				yid = yearz.get(0).getYearId();
				logger.info("year id : " + yid);
			}
			q = entitymanager
					.createQuery("from Payroll where month_id=? and year_id=? and status='inserted' ");
			q.setParameter(1, mid);
			q.setParameter(2, yid);
			payrollz = (List<Payroll>) q.getResultList();
			logger.info("list size " + payrollz.size());
			if (payrollz.size() > 0) {
				logger.info("inside dao >> if ");
				for (int i = 0; i < payrollz.size(); i++) {
					EmployeePayroll payz = new EmployeePayroll();
					payz.setDesignation(payrollz.get(i).getEmployee()
							.getDesignation().getType());
					payz.setName(payrollz.get(i).getEmployee()
							.getEmployeeName());
					payz.setTodayDate(payrollz.get(i).getPayrollDate());
					payz.setPayrollno(payrollz.get(i).getPayrollNumber());
					payz.setWork(new BigDecimal(payrollz.get(i)
							.getTotalSalary()));
					payz.setTotalsalary("" + payrollz.get(i).getTotalSalary());
					payz.setEmployeeid(payrollz.get(i).getEmployee()
							.getEmployeeId());
					// payz.setPayrolldate(payrollz.get(i).getPayrollDate());
					payz.setAdvanceamount(""
							+ payrollz.get(i).getAdvanceAmount());
					logger.info("==payy===="
							+ new BigDecimal(payrollz.get(i).getEmployee()
									.getBasicSalary()));
					payz.setBsc(new BigDecimal(payrollz.get(i).getEmployee()
							.getBasicSalary()));
					payz.setBasicsalary(payrollz.get(i).getEmployee()
							.getBasicSalary());
					// payz.setWork(new
					// BigDecimal(payrollz.get(i).getWorkingDays()));
					payz.setWorkingdays(payrollz.get(i).getWorkingDays());
					payz.setCommission(payrollz.get(0).getCommission());
					payz.setOvertime(payrollz.get(0).getOverTime());
					value.add(payz);
					pay.setVallist(value);
				}
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public List<EmployeePayroll> viewPayrollz(EmployeePayroll view)
			throws DemoException {
		logger.info("inside dao--------->>>>");
		List<EmployeePayroll> datalist = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		try {
			Query q = null;
			q = entitymanager.createQuery("from Payroll where payroll_id=?");
			logger.info("payroll number-------->>>>>" + view.getPayrollno());
			q.setParameter(1, view.getId());
			List<Payroll> result = (List<Payroll>) q.getResultList();
			logger.info("size ----->>>>" + result.size());
			int pid = 0;
			if (result.size() > 0) {
				pid = result.get(0).getPayrollId();
				logger.info("payroll id------->>>>" + pid);
				datalist = new ArrayList<EmployeePayroll>();
				EmployeePayroll viewlist = new EmployeePayroll();
				viewlist.setAdvanceamount(result.get(0).getAdvanceAmount());
				viewlist.setBasicsalary(result.get(0).getEmployee()
						.getBasicSalary());
				logger.info("basic salary----------->>>>"
						+ result.get(0).getEmployee().getBasicSalary());
				viewlist.setDesignation(result.get(0).getEmployee()
						.getDesignation().getType());
				viewlist.setEmployeeid(result.get(0).getEmployee()
						.getEmployeeId());
				viewlist.setMonth(result.get(0).getMonth().getType());
				viewlist.setName(result.get(0).getEmployee().getEmployeeName());
				viewlist.setTodayDate(result.get(0).getPayrollDate());
				viewlist.setPayrollno(result.get(0).getPayrollNumber());
				viewlist.setTotalsalary(result.get(0).getTotalSalary());
				viewlist.setWorkingdays(result.get(0).getWorkingDays());
				viewlist.setCommission(result.get(0).getCommission());
				viewlist.setOvertime(result.get(0).getOverTime());
				viewlist.setYear(result.get(0).getYear().getType());
				viewlist.setCurrency(result.get(0).getCurrency());
				viewlist.setCurrencyAmount(result.get(0).getCurrencyAmount());
				datalist.add(viewlist);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return datalist;
	}

	@Transactional(value = "transactionManager")
	public List<EmployeePayroll> editPayroll(EmployeePayroll view)
			throws DemoException {
		logger.info("inside dao--------->>>>");
		List<EmployeePayroll> datalist = null;Date date=new Date();
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
		try {
			Query q = null;
			q = entitymanager.createQuery("from Payroll where payrollNumber=? and client_ID=?");
			q.setParameter(1, view.getPayrollno());
			q.setParameter(2, clientID);
			logger.info("payroll number-------->>>>>" + view.getPayrollno());
			List<Payroll> result = (List<Payroll>) q.getResultList();
			logger.info("size ----->>>>" + result.size());
			int pid = 0;
			if (result.size() > 0) {
				pid = result.get(0).getPayrollId();
				logger.info("payroll id------->>>>" + pid);
			}
			if (view.getStatus().equalsIgnoreCase("register")) {
				logger.info("inside 1st loop--------->>>>");
				datalist = new ArrayList<EmployeePayroll>();
				EmployeePayroll viewlist = new EmployeePayroll();
				viewlist.setAdvanceamount(result.get(0).getAdvanceAmount());
				viewlist.setBasicsalary(result.get(0).getEmployee()
						.getBasicSalary());
				logger.info("basic salary----------->>>>"
						+ result.get(0).getEmployee().getBasicSalary());
				viewlist.setDesignation(result.get(0).getEmployee()
						.getDesignation().getType());
				viewlist.setEmployeeid(result.get(0).getEmployee()
						.getEmployeeId());
				viewlist.setMonth(result.get(0).getMonth().getType());
				viewlist.setName(result.get(0).getEmployee().getEmployeeName());
				viewlist.setTodayDate(result.get(0).getPayrollDate());
				viewlist.setPayrollno(result.get(0).getPayrollNumber());
				viewlist.setTotalsalary(result.get(0).getTotalSalary());
				viewlist.setWorkingdays(result.get(0).getWorkingDays());
				viewlist.setCommission(result.get(0).getCommission());
				viewlist.setOvertime(result.get(0).getOverTime());
				viewlist.setYear(result.get(0).getYear().getType());
				viewlist.setCurrency(result.get(0).getCurrency());
				viewlist.setCurrencyAmount(result.get(0).getCurrencyAmount());
				datalist.add(viewlist);
			} else if (view.getStatus().equalsIgnoreCase("update")) {
				logger.info("inside 2nd loop-------->>>>");
				Payroll edit = entitymanager.find(Payroll.class, pid);
				edit.setAdvanceAmount(view.getAdvanceamount());
				edit.setTotalSalary(view.getTotalsalary());
				if(baseCurrency.equalsIgnoreCase(view.getCurrency())){
					edit.setCurrencyAmount(view.getTotalsalary());
				}else{
					BigDecimal currAmount=CurrencyConverter.findExchangeRateAndConvert(view.getCurrency(),baseCurrency,view.getTotalsalary());
					edit.setCurrencyAmount(String.valueOf(currAmount));
				}
				System.out.println("currency"+view.getCurrency());
				edit.setPayrollDate(view.getTodayDate());
				edit.setWorkingDays(view.getWorkingdays());
				edit.setCurrency(view.getCurrency());
				edit.setCommission(view.getCommission());
				edit.setOverTime(view.getOvertime());
				edit.setUpdatedDate(date);
				entitymanager.merge(edit);
			} else if (view.getStatus().equalsIgnoreCase("delete")) {
				logger.info("inside 3rd loop-------->>>");
				Payroll dele = entitymanager.find(Payroll.class, pid);
				dele.setStatus("delete");
				entitymanager.merge(dele);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return datalist;
	}

	@Transactional(value = "transactionManager")
	public String payroll(EmployeePayroll pay) throws DemoException {
		logger.info("inside dao--------->>>>");
		List<EmployeePayroll> value1 = new ArrayList<EmployeePayroll>();
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		Query q = null;
		try {
			if(userType.equalsIgnoreCase("Maker")){
				q = entitymanager.createQuery("from Employee where employeeId=? and status='inserted' and client_ID=? and user_ID=? ORDER BY createdDate DESC");
				q.setParameter(1, pay.getEmployeeid());
				q.setParameter(2, clientID);
				q.setParameter(3, userID);
			}else{
				if (pay.getEmployeeid()=="ApprovalData") {
					q = entitymanager.createQuery("from Employee where status='inserted' and client_ID=? ORDER BY createdDate DESC");
					q.setParameter(1, clientID);
				}
				else{
				q = entitymanager.createQuery("from Employee where employeeId=? and status='inserted' and client_ID=? ORDER BY createdDate DESC");
				q.setParameter(1, pay.getEmployeeid());
				q.setParameter(2, clientID);
				}
			}
			logger.info(pay.getEmployeeid());
			List<Employee> res = (List<Employee>) q.getResultList();
			int sdid = 0;
			int i = 0;
			if (res.size() > 0) {
				sdid = res.get(0).getEmployeeDetailsId();
				logger.info("staff id---->" + sdid);
				if (pay.getEmployeeid()=="ApprovalData") {
					q = entitymanager.createQuery("from Payroll where client_ID=? and status='inserted' and approvalStatus='draft' ORDER BY createdDate DESC");
					q.setParameter(1, clientID);
				}
				else{
				q = entitymanager.createQuery("from Payroll where employee_details_id=? and status='inserted' ORDER BY createdDate DESC");
				q.setParameter(1, sdid);
				}
				logger.info("payrollid-------->>>" + sdid);
				List<Payroll> result = (List<Payroll>) q.getResultList();
				logger.info("paylist-------->" + result.size());
				int edid = 0;
				int j = 0;
				if (result.size() > 0) {
					logger.info("insude if loop----->>>>");
					for (j = 0; j < result.size(); j++) {
						logger.info("inside for loop-------->>>");
						edid = result.get(j).getPayrollId();
						logger.info("employee payroll id---?" + edid);
						logger.info("inside for loop--------->>>>");
						EmployeePayroll payz = new EmployeePayroll();
						payz.setMonth(result.get(j).getMonth().getType());
						payz.setYear(result.get(j).getYear().getType());
						logger.info("year---->>>"
								+ result.get(j).getYear().getType());
						payz.setBasicsalary(result.get(j).getEmployee()
								.getBasicSalary());
						logger.info("basic salary----->>>"
								+ result.get(j).getEmployee().getBasicSalary());
						payz.setEmployeeid(result.get(j).getEmployee()
								.getEmployeeId());
						payz.setName(result.get(j).getEmployee()
								.getEmployeeName());
						payz.setPayrollno(result.get(j).getPayrollNumber());
						payz.setTodayDate(result.get(j).getPayrollDate());
						payz.setAdvanceamount(result.get(j).getAdvanceAmount());
						payz.setTotalsalary(result.get(j).getTotalSalary());
						logger.info("total salary-------->>>"
								+ result.get(j).getTotalSalary());
						payz.setWorkingdays(result.get(j).getWorkingDays());
						payz.setCommission(result.get(j).getCommission());
						payz.setOvertime(result.get(j).getOverTime());
						logger.info("---->>> "
								+ result.get(j).getPayrollNumber());
						payz.setApprovalStatus(result.get(j).getApprovalStatus());
						payz.setId(result.get(j).getPayrollId());
						payz.setCurrency(result.get(j).getCurrency());
						payz.setCurrencyAmount(result.get(j).getCurrencyAmount());
						value1.add(payz);
						pay.setVallist(value1);
					}
				}
				logger.info("outside if loop---------->>>>");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public List<EmployeePayroll> viewPayroll(EmployeePayroll view)
			throws DemoException {
		logger.info("inside dao--------->>>>");
		List<EmployeePayroll> datalist = null;
		try {
			Query q = null;
			q = entitymanager.createQuery("from Payroll where payrollNumber=?");
			q.setParameter(1, view.getPayrollno());
			logger.info("payroll number-------->>>>>" + view.getPayrollno());
			List<Payroll> result = (List<Payroll>) q.getResultList();
			logger.info("size ----->>>>" + result.size());
			int pid = 0;
			if (result.size() > 0) {
				pid = result.get(0).getPayrollId();
				logger.info("payroll id------->>>>" + pid);
				datalist = new ArrayList<EmployeePayroll>();
				EmployeePayroll viewlist = new EmployeePayroll();
				viewlist.setAdvanceamount(result.get(0).getAdvanceAmount());
				viewlist.setCommission(result.get(0).getCommission());
				viewlist.setOvertime(result.get(0).getOverTime());
				viewlist.setBasicsalary(result.get(0).getEmployee()
						.getBasicSalary());
				logger.info("basic salary----------->>>>"
						+ result.get(0).getEmployee().getBasicSalary());
				viewlist.setDesignation(result.get(0).getEmployee()
						.getDesignation().getType());
				viewlist.setEmployeeid(result.get(0).getEmployee()
						.getEmployeeId());
				viewlist.setMonth(result.get(0).getMonth().getType());
				viewlist.setName(result.get(0).getEmployee().getEmployeeName());
				viewlist.setTodayDate(result.get(0).getPayrollDate());
				viewlist.setPayrollno(result.get(0).getPayrollNumber());
				viewlist.setTotalsalary(result.get(0).getTotalSalary());
				viewlist.setWorkingdays(result.get(0).getWorkingDays());
				viewlist.setYear(result.get(0).getYear().getType());
				datalist.add(viewlist);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return datalist;
	}

	public ArrayList<String> changezEvent(String s, ArrayList<String> list1)
			throws DemoException {
		logger.info(" inside dao----->>>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<String> designation = new ArrayList<String>();
		Query q = null;
		try {
			q = entitymanager.createQuery("from Designation where type=? and client_ID=? ");
			q.setParameter(1, s);
			q.setParameter(2, clientID);
			List<Designation> result = (List<Designation>) q.getResultList();
			int dcid = 0;
			if (result.size() > 0) {
				int i = 0;
				dcid = result.get(i).getDesignationCategoryId();
				logger.info("designation id---->>>" + dcid);
				Query q1 = null;
				q1 = entitymanager
						.createQuery("from Employee where designation_category_id=? and status=? and payrollStatus=? and client_ID=?");
				logger.info("employee id-------->>>>");
				q1.setParameter(1, dcid);
				logger.info("size----->>>" + dcid);
				q1.setParameter(2, "inserted");
				q1.setParameter(3, "payroll generated");
				q1.setParameter(4, clientID);
				List<Employee> result1 = (List<Employee>) q1.getResultList();
				logger.info("list size---->>>" + result1.size());
				for (Employee re : result1) {
					logger.info("inside for loop-------->>>");
					designation.add(re.getEmployeeId());
					logger.info("employee id---->>>" + designation
							+ ">>>>>>>>>>" + re.getEmployeeId());
				}
				logger.info("outside for loop------>>>" + designation
						+ ">>>>>>>>>>" + designation.size());
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return designation;
	}

	/* udhaya 30.1.2015 */
	@Transactional(value = "transactionManager")
	public String transactionView(ATransaction search) throws DemoException {
		logger.info("inside dao-------->>>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			logger.info("inside try in dao---->>>");

			q = entitymanager
					.createQuery("from Transaction where (status='inserted' or status='credited' or status='debited') and client_ID=? ORDER BY createdDate DESC");
			q.setParameter(1, clientID);
			List<Transaction> result = (List<Transaction>) q.getResultList();
			logger.info("table data size-------->>>" + result.size());
			search.setView(result);

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public List<ATransaction> getviewForm(ATransaction view)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String baseCurency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
		logger.info("inside dao-------->>>");
		List<ATransaction> list = null;
		Query q = null;
		try {
			logger.info("inside try in dao--------->>>");
			q = entitymanager
					.createQuery("from Transaction where transactionNo=? and client_ID=?");
			q.setParameter(1, view.getTransactionNo());
			q.setParameter(2, clientID);
			logger.info("transaction no------->>>" + view.getTransactionNo());
			List<Transaction> result = (List<Transaction>) q.getResultList();
			logger.info("data size------>>>" + result.size());
			int tid = 0;
			if (result.size() > 0) {
				tid = result.get(0).getTransactionId();
				logger.info("transaction id--------->>>>" + tid);
				list = new ArrayList<ATransaction>();
				ATransaction viewlist = new ATransaction();
				viewlist.setCurrency(result.get(0).getCurrency());
				logger.info("amount--------->>>" + result.get(0).getAmount());
				viewlist.setBankname(result.get(0).getBankName());
				viewlist.setCardno(result.get(0).getCardNo());
				viewlist.setChequedate(result.get(0).getChequeDate());
				viewlist.setChequeno(result.get(0).getChequeNo());
				viewlist.setDate(result.get(0).getPayDate());
				viewlist.setNote(result.get(0).getNote());
				viewlist.setParticular(result.get(0).getParticulars());
				viewlist.setPaymentMode(result.get(0).getPaymentMode());
				viewlist.setTransactionNo(result.get(0).getTransactionNo());
				viewlist.setPaydate(result.get(0).getPayDate());
				viewlist.setDuedate(result.get(0).getDueDate());
				viewlist.setFilepath(result.get(0).getFilePath());
				System.out.println("====="+result.get(0).getDueDate());
				viewlist.setMaddress(result.get(0).getMailingAddress());
				logger.info("transaction no----->>>"
						+ result.get(0).getTransactionNo());
				viewlist.setTransactionType(result.get(0).getTransactionType());
				viewlist.setTransStatus(result.get(0).getTransactionStatus());
				viewlist.setCurrencyType(result.get(0).getCurrencyType());
				viewlist.setAmount(result.get(0).getAmount());
				System.out.println("cureency "+result.get(0).getAmount());
				viewlist.setBaseCurrency(baseCurency);
				list.add(viewlist);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return list;
	}

	/****/
	public String employeeIdSearch(EmployeeDetail empid) throws DemoException {
		String status = "fail";
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query z = null;
			int i = 0;
			int iid = 0;
			List<Employee> empz = null;
			z = entitymanager.createQuery("from Employee where employeeId=? and client_ID=?");
			z.setParameter(1, empid.employeeid);
			z.setParameter(2, clientID);
			empz = z.getResultList();
			if (empz.size() > 0) {
				status = "exist";

			} else {
				logger.info("inside dao else");
				status="success";
			}
		} catch (Exception ie) {

			logger.error("Inside Exception", ie);
		}

		finally {

		}
		return status;

	}

	/****/
	public String chequeCredit(ATransaction save) throws DemoException {
		Query q = null;
		Query q2 = null;
		int ca = 0;
		List<ATransaction> list = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.info("inside dao-------->>>");
		try {
			int tid = 0;
			String s = null;
			int c = 0;
			Query z = null;
			Query cas = null;
			logger.info("trans no : " + save.getTransactionNo());
			cas = entitymanager
					.createQuery("from Transaction where transactionNo=? and client_ID=? ");
			cas.setParameter(1, save.getTransactionNo());
			cas.setParameter(2, clientID);
			List<Transaction> z2 = (List<Transaction>) z.getResultList();
			if (z2.size() > 0) {
				logger.info("inside income / cash ");

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public List<Month> monthInfo(List<Month> monthinfo) throws DemoException {
		logger.info(" inside dao----->>>");
		Query q = null;
		try {
			logger.info("inside try in dao---->>>");
			q = entitymanager.createQuery("select type from Month");
			monthinfo = q.getResultList();
			logger.info("month------->>>" + monthinfo);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return monthinfo;
	}

	@Transactional(value = "transactionManager")
	public List<Year> yearInfo(List<Year> yearinfo) throws DemoException {
		logger.info(" inside dao----->>>");
		Query q = null;
		try {
			logger.info("inside try in dao---->>>");
			q = entitymanager.createQuery("select type from Year");
			yearinfo = q.getResultList();
			logger.info("year------->>>" + yearinfo);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return yearinfo;
	}

	@Transactional(value = "transactionManager")
	public String onloadEmpView(EmployeeDetail employee) throws DemoException {
		logger.info(" inside dao----->>>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q = null;
			q = entitymanager.createQuery("from Employee where status=? and client_ID=? ");
			q.setParameter(1, "inserted");
			q.setParameter(2, clientID);
			List<Employee> result = (List<Employee>) q.getResultList();
			logger.info("size------>>>" + result.size());
			employee.setList(result);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public List<ATransaction> getStatusChange(ATransaction change)
			throws DemoException {
		logger.info("inside dao-------->>>>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			logger.info("inside try in dao--------->>>");
			q = entitymanager
					.createQuery("from Transaction where transactionNo=? and client_ID=? ");
			q.setParameter(1, change.getTransactionNo());
			q.setParameter(2, clientID);
			logger.info("transaction no-------->>>" + change.getTransactionNo());
			List<Transaction> result = (List<Transaction>) q.getResultList();
			logger.info("size-------->>>" + result.size());
			int tid = 0;
			if (result.size() > 0) {
				tid = result.get(0).getTransactionId();
				logger.info("inside equal case-------->>>>>>>>>>>");
				String s = result.get(0).getTransactionType();
				logger.info("-------s----------" + s);
				if (s.equalsIgnoreCase("Income")) {
					Transaction credit = entitymanager.find(Transaction.class,
							tid);
					logger.info("transaction id--------->>" + tid);
					credit.setStatus("credited");
					entitymanager.merge(credit);
					logger.info("----------inside if income-----");
					AccReceivableAsset credit1 = new AccReceivableAsset();
					credit1.setStatus("inserted");
					credit1.setTransaction(entitymanager.find(
							Transaction.class, tid));
					entitymanager.persist(credit1);
					logger.info("---------cheque inserted in acc receivable asset-------");
				} else if (s.equalsIgnoreCase("Expenses")) {
					logger.info("----------inside if expense-----");
					Transaction credit = entitymanager.find(Transaction.class,
							tid);
					logger.info("transaction id--------->>" + tid);
					credit.setStatus("debited");
					entitymanager.merge(credit);
					AccPayableLiability credit2 = new AccPayableLiability();
					credit2.setStatus("inserted");
					credit2.setTransaction(entitymanager.find(
							Transaction.class, tid));
					entitymanager.persist(credit2);
					logger.info("---------cheque inserted in acc payable liability-------");
				}
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	/** transaction insert **/
	@Transactional(value = "transactionManager")
	public String saveconfirm(ATransaction save) throws DemoException {
		Query q2 = null;int tid = 0;int s = 0;Date date=new Date();
		Query z = null;Query cas = null;BigDecimal currAmount=BigDecimal.valueOf(0);
		logger.info("inside dao-------->>>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("inside try in dao------->>>>");
			for (int i = 0; i < save.getDebitlist().size(); i++) {
				q2 = entitymanager.createQuery("from Transaction where client_ID=? ");
				q2.setParameter(1, clientID);
				List<Transaction> reult1 = (List<Transaction>) q2.getResultList();
				int count = 0;
				if (reult1.size() > 0) {
					for (Transaction re : reult1) {
						count++;
					}
				}
				if (count == 0) {
					count++;
					String a = "TN0000" + count;
					save.setTransactionNo(a);
				} else {
					count++;
					String a = "TN0000" + count;
					save.setTransactionNo(a);
				}				
				logger.info("transaction no------->>>>=" + save.getTransactionNo());
				Transaction confirm = new Transaction();
				confirm.setAmount(save.getDebitlist().get(i).getAmount());
				confirm.setNote(save.getDebitlist().get(i).getNote());
				confirm.setParticulars(save.getDebitlist().get(i).getParticular());
				confirm.setPaymentMode(save.getDebitlist().get(i).getPaymentMode());
				confirm.setStatus("inserted");
				confirm.setTransactionDate(save.getDate());
				confirm.setTransactionType(save.getDebitlist().get(i).getTransactionType());
				confirm.setBankName(save.getDebitlist().get(i).getBankname());
				confirm.setCardNo(save.getDebitlist().get(i).getCardno());
				confirm.setChequeDate(save.getDebitlist().get(i).getChequedate());
				confirm.setChequeNo(save.getDebitlist().get(i).getChequeno());
				confirm.setMailingAddress(save.getDebitlist().get(i).getMaddress());
				confirm.setPayDate(save.getDebitlist().get(i).getPaydate());
				confirm.setDueDate(save.getDebitlist().get(i).getDuedate());
				confirm.setPaymentStatus("Pending");
				confirm.setCreatedDate(date);
				confirm.setCurrencyType(save.getDebitlist().get(i).getCurrencyType());
				String baseCurrency=clientCurrency(clientID);
				if(save.getDebitlist().get(i).getCurrencyType().equalsIgnoreCase(baseCurrency)){
					currAmount=new BigDecimal(save.getDebitlist().get(i).getAmount());
				}else{
					currAmount=CurrencyConverter.findExchangeRateAndConvert(save.getDebitlist().get(i).getCurrencyType(),baseCurrency,save.getDebitlist().get(i).getAmount());
				
				}
				//System.out.println("getTransFile "+save.getDebitlist().get(i).getTransFile().getContentType());
				System.out.println("amount "+save.getDebitlist().get(i).getAmount()+" currenecy "+currAmount);
				System.out.println("file "+save.getDebitlist().get(i).getTransFile());
				confirm.setCurrency(String.valueOf(currAmount));
				if(save.getDebitlist().get(i).getTransFile()!=null){
					String ss=save.getDebitlist().get(i).getTransFile().getContentType();
					System.out.println("transFile  "+ss);
					String type = ss.substring(ss.lastIndexOf("/") + 1);
					System.out.println("transFile  "+type);
					copyFile(save.getDebitlist().get(i).getPaydate(),save.getTransactionNo(), save.getDebitlist().get(i).getTransFile().getInputstream(), type);
					System.out.println("date - "+ft.format(save.getDebitlist().get(i).getPaydate()));
					String path = ft.format(save.getDebitlist().get(i).getPaydate()) + "/" +save.getTransactionNo()+ "." + type;
					confirm.setFilePath(path);
				}
				confirm.setTransactionStatus(save.getDebitlist().get(i).getTransStatus());
				confirm.setTransactionNo(save.getTransactionNo());
				confirm.setClient_ID(clientID);
				entitymanager.persist(confirm);
				entitymanager.flush();
				entitymanager.clear();
				logger.info("----------1---------" + confirm.getPaymentMode());
				z = entitymanager.createQuery("from Transaction ");
				List<Transaction> z1 = (List<Transaction>) z.getResultList();
				logger.info(" list size " + z1.size());
				s = z1.size();
				tid = z1.get(s - 1).getTransactionId();
				logger.info("tid " + tid);
				if (save.getDebitlist().get(i).getStatus().equals("Credit")) {
					logger.info(" income");
					if (save.getDebitlist().get(i).getPaymentMode().equals("Cash")) {
						cas = entitymanager
								.createQuery("from Transaction where transactionId=? and transactionStatus='credited' and paymentMode='Cash'");
						cas.setParameter(1, tid);
						List<Transaction> z2 = (List<Transaction>) cas.getResultList();
						if (z2.size() > 0) {
							logger.info("inside income / cash ");
							CashAsset casset = new CashAsset();
							casset.setStatus("inserted");
							casset.setTransaction(entitymanager.find(
									Transaction.class, tid));
							entitymanager.persist(casset);
							entitymanager.flush();
							entitymanager.clear();

						}
					}
					if (save.getDebitlist().get(i).getPaymentMode().equals("Card")) {
						logger.info("inside income / card ");
						cas = entitymanager
								.createQuery("from Transaction where transactionId=? and transactionStatus='credited' and paymentMode='card'");
						cas.setParameter(1, tid);
						List<Transaction> z2 = (List<Transaction>) cas.getResultList();
						if (z2.size() > 0) {
							logger.info("inside income / card ");
							AccReceivableAsset cardasset = new AccReceivableAsset();
							cardasset.setStatus("inserted");
							cardasset.setTransaction(entitymanager.find(
									Transaction.class, tid));
							entitymanager.persist(cardasset);
							entitymanager.flush();
							entitymanager.clear();
						}
					}
					if (save.getDebitlist().get(i).getPaymentMode().equals("Transfer")) {
						logger.info("inside income / Transfer ");
						cas = entitymanager
								.createQuery("from Transaction where transactionId=? and transactionStatus='credited' and paymentMode='Transfer'");
						cas.setParameter(1, tid);
						List<Transaction> z2 = (List<Transaction>) cas.getResultList();
						if (z2.size() > 0) {
							logger.info("inside income / Transfer ");
							AccReceivableAsset cardasset = new AccReceivableAsset();
							cardasset.setStatus("inserted");
							cardasset.setTransaction(entitymanager.find(
									Transaction.class, tid));
							entitymanager.persist(cardasset);
							entitymanager.flush();
							entitymanager.clear();
						}
					}
					if (save.getDebitlist().get(i).getPaymentMode().equals("Cheque")) {
						logger.info("inside income / cheque ");
					}

				} else if (save.getDebitlist().get(i).getStatus().equals("Debit")) {
					if (save.getDebitlist().get(i).getPaymentMode().equals("Cash")) {
						logger.info(" expense");
						cas = entitymanager
								.createQuery("from Transaction where transactionId=? and transactionStatus='debited'");
						cas.setParameter(1, tid);
						List<Transaction> z2 = (List<Transaction>) cas.getResultList();
						if (z2.size() > 0) {
							logger.info("inside expense ");
							AccPayableLiability liab = new AccPayableLiability();
							liab.setStatus("inserted");
							liab.setTransaction(entitymanager.find(
									Transaction.class, tid));
							entitymanager.persist(liab);
							entitymanager.flush();
							entitymanager.clear();
						}
					}
					if (save.getDebitlist().get(i).getPaymentMode().equals("Card")) {
						logger.info(" expense");
						cas = entitymanager
								.createQuery("from Transaction where transactionId=? and transactionStatus='debited'");
						cas.setParameter(1, tid);
						List<Transaction> z3 = (List<Transaction>) cas.getResultList();
						if (z3.size() > 0) {
							logger.info("inside expense ");
							AccPayableLiability liab = new AccPayableLiability();
							liab.setStatus("inserted");
							liab.setTransaction(entitymanager.find(
									Transaction.class, tid));
							entitymanager.persist(liab);
							entitymanager.flush();
							entitymanager.clear();
						}
					}

					if (save.getDebitlist().get(i).getPaymentMode().equals("Transfer")) {
						logger.info(" expense");
						cas = entitymanager
								.createQuery("from Transaction where transactionId=? and transactionStatus='debited'");
						cas.setParameter(1, tid);
						List<Transaction> z3 = (List<Transaction>) cas.getResultList();
						if (z3.size() > 0) {
							logger.info("inside expense ");
							AccPayableLiability liab = new AccPayableLiability();
							liab.setStatus("inserted");
							liab.setTransaction(entitymanager.find(
									Transaction.class, tid));
							entitymanager.persist(liab);
							entitymanager.flush();
							entitymanager.clear();
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	private void copyFile(Date date, String fileName, InputStream inputstream, String n) {
	try {
		// Create Directory
		File files = new File("/home/ec2-user/File_Inacsys/Transaction/"+ ft.format(date));
		if (!files.exists()) {
			files.mkdirs();
		} else {
			logger.debug("Alreday Found");
		}
		
		// write the inputStream to a FileOutputStream
		OutputStream out = new FileOutputStream(new File(files + "/" + fileName + "." + n));
		
		int read = 0;
		byte[] bytes = new byte[1024];
		
		while ((read = inputstream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		
		inputstream.close();
		out.flush();
		out.close();
		
		logger.debug("New file created!");
		} catch (IOException e) {
		logger.debug(e.getMessage());
	}

}
	
	public String clientCurrency(String clientID){
		Query v=null;
		v=entitymanager.createQuery("from Client where clientNumber=? and status='Active'");
		v.setParameter(1, clientID);
		List<Client> client=(List<Client>)v.getResultList();
		return client.get(0).getBaseCurrency();
	}
	
	@Transactional(value = "transactionManager")
	public List<ATransaction> getEditForm(ATransaction edit)
			throws DemoException {
		logger.info("inside dao-------->>>");
		List<ATransaction> list = null;BigDecimal currAmount=BigDecimal.valueOf(0);
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String baseCurency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
		Query q = null;Date date=new Date();
		try {
			logger.info("inside try in dao--------->>>");
			q = entitymanager
					.createQuery("from Transaction where transactionNo=? and client_ID=? ");
			q.setParameter(1, edit.getTransactionNo());
			q.setParameter(2, clientID);
			logger.info("transaction no------->>>" + edit.getTransactionNo());
			List<Transaction> result = (List<Transaction>) q.getResultList();
			logger.info("data size------>>>" + result.size());
			int tid = 0;
			if (result.size() > 0) {
				tid = result.get(0).getTransactionId();
				logger.info("transaction id--------->>>>" + tid);
			}
			if (edit.getStatus().equalsIgnoreCase("register")) {
				list = new ArrayList<ATransaction>();
				ATransaction viewlist = new ATransaction();
				viewlist.setCurrency(result.get(0).getCurrency());
				logger.info("amount--------->>>" + result.get(0).getAmount());
				viewlist.setBankname(result.get(0).getBankName());
				viewlist.setCardno(result.get(0).getCardNo());
				viewlist.setChequedate(result.get(0).getChequeDate());
				viewlist.setChequeno(result.get(0).getChequeNo());
				viewlist.setDate(result.get(0).getTransactionDate());
				viewlist.setNote(result.get(0).getNote());
				viewlist.setParticular(result.get(0).getParticulars());
				viewlist.setPaymentMode(result.get(0).getPaymentMode());
				viewlist.setTransactionNo(result.get(0).getTransactionNo());
				viewlist.setTransStatus(result.get(0).getTransactionStatus());
				viewlist.setTransactionType(result.get(0).getTransactionType());
				viewlist.setMaddress(result.get(0).getMailingAddress());
				viewlist.setPaydate(result.get(0).getPayDate());
				viewlist.setFilepath(result.get(0).getFilePath());
				viewlist.setDuedate(result.get(0).getDueDate());
				viewlist.setCurrencyType(result.get(0).getCurrencyType());
				viewlist.setBaseCurrency(baseCurency);
				System.out.println("currency "+result.get(0).getAmount());
				viewlist.setAmount(result.get(0).getAmount());
				logger.info("transaction no----->>>"
						+ result.get(0).getTransactionNo());
				viewlist.setTransactionType(result.get(0).getTransactionType());
				list.add(viewlist);
			} else if (edit.getStatus().equalsIgnoreCase("update")) {
				String s, a = "";
				s = result.get(0).getPaymentMode();
				logger.info("payment-------->" + s);

				if (s.equalsIgnoreCase("cheque")) {
					logger.info("inside the if condition-->"
							+ edit.getPaymentMode());
					if (edit.getTransStatus().equals("credited")) {
						a = "credited";
						logger.info("Income---------->" + a);
					} else if (edit.getTransactionType().equals("debited")) {
						a = "debited";
						logger.info("Expence---------->" + a);
					}
				}
				Transaction conedit = entitymanager
						.find(Transaction.class, tid);
				conedit.setAmount(edit.getAmount());
				logger.info("amount---------->>>>" + edit.getAmount());
				conedit.setBankName(edit.getBankname());
				conedit.setCardNo(edit.getCardno());
				conedit.setChequeDate(edit.getChequedate());
				conedit.setChequeNo(edit.getChequeno());
				conedit.setNote(edit.getNote());
				conedit.setParticulars(edit.getParticular());
				conedit.setPaymentMode(edit.getPaymentMode());
				conedit.setTransactionDate(edit.getDate());
				conedit.setTransactionNo(edit.getTransactionNo());
				conedit.setTransactionType(edit.getTransactionType());
				conedit.setUpdatedDate(date);
				//String baseCurrency=clientCurrency(clientID);
				if(edit.getCurrencyType().equalsIgnoreCase(baseCurency)){
					currAmount=new BigDecimal(edit.getAmount());
				}else{
					currAmount=CurrencyConverter.findExchangeRateAndConvert(edit.getCurrencyType(),baseCurency,edit.getAmount());
				}
				//System.out.println("getTransFile "+save.getDebitlist().get(i).getTransFile().getContentType());
				System.out.println("amount "+edit.getAmount()+" currenecy "+currAmount);
				conedit.setCurrencyType(edit.getCurrencyType());
				conedit.setCurrency(String.valueOf(currAmount));
				conedit.setMailingAddress(edit.getMaddress());
				conedit.setPayDate(edit.getPaydate());
				conedit.setDueDate(edit.getDuedate());
				logger.info("transaction no-------->>>>"
						+ edit.getTransactionNo());
				conedit.setTransactionType(edit.getTransactionType());
				String ss=edit.getTransFile().getContentType();
				System.out.println("transFile  "+ss);
				String type = ss.substring(ss.lastIndexOf("/") + 1);
				System.out.println("transFile  "+type+"-----"+edit.getPaydate());
				copyFile(edit.getPaydate(),edit.getTransactionNo(), edit.getTransFile().getInputstream(), type);
				String path = ft.format(edit.getPaydate()) + "/" +edit.getTransactionNo()+ "." + type;
				System.out.println("path --->>>"+path);
				conedit.setFilePath(path);
				entitymanager.merge(conedit);
			} else if (edit.getStatus().equalsIgnoreCase("delete")) {
				Transaction del = entitymanager.find(Transaction.class, tid);
				del.setStatus("delete");
				entitymanager.merge(del);
				String ss = result.get(0).getTransactionStatus();
				if (ss.equalsIgnoreCase("debited")) {
					q = entitymanager
							.createQuery("from AccPayableLiability where transaction_id=?");
					q.setParameter(1, tid);
					List<AccPayableLiability> resul = (List<AccPayableLiability>) q.getResultList();
					int aplid = 0;
					if (resul.size() > 0) {
						aplid = resul.get(0).getAccPayableLiabilityId();
						logger.info("payable liability id------->>>" + aplid);
						AccPayableLiability pay = entitymanager.find(
								AccPayableLiability.class, aplid);
						pay.setStatus("delete");
						entitymanager.merge(pay);
						logger.info("cheque Income deleted---------->>");
					}
				} else {

					String s = result.get(0).getPaymentMode();// ram
					logger.info("-------s----------" + s);
					if (s.equalsIgnoreCase("cash")) {
						q = entitymanager
								.createQuery("from CashAsset where transaction_id=?");
						q.setParameter(1, tid);
						List<CashAsset> res5 = (List<CashAsset>) q
								.getResultList();
						logger.info("cash asset size------>>>" + res5.size());
						int caid = 0;
						if (res5.size() > 0) {
							caid = res5.get(0).getCashAssetId();
							logger.info("cash asset id------->>>" + caid);
						}
						logger.info("----------inside if cash delete-------");
						CashAsset cash = entitymanager.find(CashAsset.class,
								caid);
						cash.setStatus("delete");
						entitymanager.merge(cash);
						logger.info("---------cash deleted-------");
					} else if (s.equalsIgnoreCase("Card")) {
						q = entitymanager
								.createQuery("from AccReceivableAsset where transaction_id=?");
						q.setParameter(1, tid);
						List<AccReceivableAsset> res = (List<AccReceivableAsset>) q
								.getResultList();
						logger.info("receivable size------>>>" + res.size());
						int arid = 0;
						if (res.size() > 0) {
							arid = res.get(0).getAccReceivableAssetId();
							logger.info("receivable id------->>>" + arid);
						}
						logger.info("---------inside else card delete--------");
						AccReceivableAsset accr = entitymanager.find(
								AccReceivableAsset.class, arid);
						accr.setStatus("delete");
						entitymanager.merge(accr);
						logger.info("---------card deleted----------");
					} else if (s.equalsIgnoreCase("cheque")) {
						q = entitymanager
								.createQuery("from AccReceivableAsset where transaction_id=?");
						q.setParameter(1, tid);
						List<AccReceivableAsset> res = (List<AccReceivableAsset>) q
								.getResultList();
						logger.info("receivable size------>>>" + res.size());
						int arid = 0;
						if (res.size() > 0) {
							arid = res.get(0).getAccReceivableAssetId();
							logger.info("receivable id------->>>" + arid);
						}
						logger.info("transaction type------------->>>>>>>>"
								+ ss);
						logger.info("insideelse cheque delete");
						if (ss.equalsIgnoreCase("credited")) {
							logger.info("inside cheque income-------->>>>");
							AccReceivableAsset ara = entitymanager.find(
									AccReceivableAsset.class, arid);
							ara.setStatus("delete");
							entitymanager.merge(ara);
							logger.info("cheque----income deleted---------->>>");
						}

					}
				}
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return list;
	}
	
	@Override
	@Transactional(value = "transactionManager")
	public List<Employee> empInfo(List<Employee> empinfo) throws DemoException {
		logger.info("-------inside emp id dao---------");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			logger.info("---------inside try block dao--------");
			q = entitymanager
					.createQuery("select employeeId from Employee where status='inserted' and client_ID=? ");
			q.setParameter(1, clientID);
			empinfo = q.getResultList();
			logger.info("-----------emp id-------------" + empinfo);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return empinfo;
	}

	@Override
	@Transactional(value = "transactionManager")
	public List<Employee> nameInfo(List<Employee> nameinfo)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			logger.info("----------inside try emp name info--------");
			q = entitymanager
					.createQuery("select employeeName from Employee where status='inserted' and client_ID=? ");
			q.setParameter(1, clientID);
			nameinfo = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return nameinfo;
	}

	@Override
	@Transactional(value = "transactionManager")
	public String employeeDetails(EmployeeDetail employee) throws DemoException {
		logger.info(" inside dao----->>>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("inside try in dao---->>>");
			Query q = null;
			q = entitymanager
					.createQuery("from Employee where employeeName=? and status=? and client_ID=? ");
			q.setParameter(1, employee.getName());
			logger.info(" employee name-------->>>" + employee.getName());
			q.setParameter(2, "inserted");
			q.setParameter(3, clientID);
			List<Employee> result = (List<Employee>) q.getResultList();
			logger.info("size------>>>" + result.size());
			employee.setList(result);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public List<Employee> payrollemp(List<Employee> list3, EmployeePayroll save)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");

		logger.info("inside dao------->>>>");
		Query q = null;
		List<SalesRecord> salrec = null;
		String totCom = "";
		BigDecimal bd = BigDecimal.valueOf(0);
		int sid = 0;
		List<Employee> employee2 = null;
		try {
			List<Year> years = null;
			List<Month> months = null;
			List<Employee> emps = null;
			List<Payroll> payrl = null;
			Query ys, empl, ms, ns = null;
			int yrid = 0;
			int mnid = 0;
			int emid = 0;

			empl = entitymanager
					.createQuery("from Employee where registration_id=? and client_ID=? ");
			empl.setParameter(1, save.regid);
			empl.setParameter(2, clientID);
			emps = empl.getResultList();
			if (emps.size() > 0) {
				emid = emps.get(0).getEmployeeDetailsId();
				logger.info("emp id : " + emid);
			}

			ms = entitymanager.createQuery("from Month where type=?");
			logger.info("----------------month1------------");
			ms.setParameter(1, save.month);
			logger.info("----------month2------------------" + save.month);
			months = ms.getResultList();
			logger.info("--------------month3--------------");
			if (months.size() > 0) {
				mnid = months.get(0).getMonthId();
				logger.info("month id : " + mnid);
			}

			ys = entitymanager.createQuery("from Year where type=?");
			ys.setParameter(1, save.year);
			years = ys.getResultList();
			if (years.size() > 0) {
				yrid = years.get(0).getYearId();
				logger.info("year id : " + yrid);
			}
			ns = entitymanager
					.createQuery("from Payroll where month_Id=? and year_Id=? and employee_details_id=? and status='inserted' ");
			ns.setParameter(1, mnid);
			ns.setParameter(2, yrid);
			ns.setParameter(3, emid);
			payrl = ns.getResultList();
			logger.info("---------size of payroll-------" + payrl.size());
			if (payrl.size() > 0) {
				logger.info("inside condition");
				throw new DemoException(
						"This employee id got the payroll already");
			} else {
				logger.info("inside try in dao---------->>>");
				q = entitymanager
						.createQuery(" from Employee where registration_id=?");
				q.setParameter(1, save.getRegid());
				logger.info("employee id----->>>" + save.getRegid());
				employee2 = q.getResultList();
				logger.info("size id----->>>" + employee2.size());
				if (employee2.size() > 0) {
					logger.info("inside if loop---->>>>");
					save.setList3(employee2);

					Query g = null;
					Query c = null;
					Query e = null;
					String commissionAmount = "0";
					int buyerId = 0;
					List<I0021> list21 = null;
					List<I0032> list32 = null;
					String date1 = "";
					String date2 = "";
					String month = "";
					String empname = "";
					BigDecimal deci = BigDecimal.valueOf(0);
					if (mnid < 10) {
						month = "0" + mnid;
					} else {
						month = "" + mnid;
					}
					date1 = "01/" + month + "/" + save.year;
					date2 = "31/" + month + "/" + save.year;
					SimpleDateFormat formatter = new SimpleDateFormat(
							"dd/MM/yyyy");
					String datee1 = date1;
					String datee2 = date2;
					int i = 0;
					empname = emps.get(0).getEmployeeName();
					e = entitymanager
							.createQuery("from I0032 where freelancerName=? and status='in' and client_ID=? ");
					e.setParameter(1, empname);
					e.setParameter(2, clientID);
					list32 = e.getResultList();
					if (list32.size() > 0) {
						Date D1 = formatter.parse(datee1);
						Date D2 = formatter.parse(datee2);
						for (i = 0; i < list32.size(); i++) {
							buyerId = list32.get(i).getBuyer_ID();
							c = entitymanager
									.createQuery("from I0021 where salesOrderDate between ? and ? and buyer_ID=? and client_ID=? ");
							c.setParameter(1, D1);
							c.setParameter(2, D2);
							c.setParameter(3, buyerId);
							c.setParameter(4, clientID);
							list21 = c.getResultList();
							if (list21.size() > 0) {
								for (int j = 0; j < list21.size(); j++) {

									sid = list21.get(j).getSales_ID();
									logger.info("sales id-->> " + sid);

									g = entitymanager
											.createQuery("from SalesRecord where sales_ID=?");
									g.setParameter(1, sid);
									salrec = g.getResultList();
									if (salrec.size() > 0) {
										logger.info("salrec.size()-->> "
												+ salrec.size());
										for (int k = 0; k < salrec.size(); k++) {

											logger.info("salrec.comsn-->> "
													+ salrec.get(k)
															.getTotcmsion());
											if (salrec.get(k).getTotcmsion() == null) {
												bd = BigDecimal.valueOf(0);
											} else {
												totCom = salrec.get(k)
														.getTotcmsion();
											}
											bd = bd.add(new BigDecimal(totCom));

										}

										logger.info("tot coms -->> " + bd);
									}

								}
							}
						}
						save.setCommission("" + bd);
					} else {
						save.setCommission("0");
					}
				}
			}
		} catch (java.text.ParseException e1) {

			e1.printStackTrace();
		} finally {

		}
		return employee2;
	}

	@Transactional(value = "transactionManager")
	public String confirm(EmployeePayroll save) throws DemoException {
		logger.info("inside dao------->>>>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		Query q = null;Date date=new Date();
		try {
			logger.info("inside try in dao---------->>>");
			logger.info("inside try in dao-->>>");
			q = entitymanager.createQuery("from Payroll where client_ID=?");
			q.setParameter(1, clientID);
			List<Payroll> result = (List<Payroll>) q.getResultList();
			int count = 0;
			if (result.size() > 0) {
				for (Payroll re : result) {
					count++;
				}
			}
			if (count == 0) {
				count++;
				String a = "AEP000" + count;
				save.setPayrollno(a);
			} else {
				count++;
				String a = "AEP000" + count;
				save.setPayrollno(a);
			}
			Query q4 = null;
			q4 = entitymanager.createQuery("from Month where type=?");
			logger.info("P0");
			q4.setParameter(1, save.getMonth());
			logger.info("P1");
			List<Month> aa = (List<Month>) q4.getResultList();
			int mid = 0;
			if (aa.size() > 0) {
				mid = aa.get(0).getMonthId();
				logger.info("month id--->" + mid);
			}
			Query n = null;
			List<Employee> namez = null;
			int nid = 0;
			n = entitymanager.createQuery("from Employee where employeeId=? and client_ID=?");
			n.setParameter(1, save.employeeid);
			n.setParameter(2, clientID);
			namez = n.getResultList();
			if (namez.size() > 0) {
				nid = namez.get(0).getEmployeeDetailsId();
				logger.info("name id : " + nid);
			}
			q = entitymanager.createQuery("from Year where type=?");
			q.setParameter(1, save.getYear());
			List<Year> bb = (List<Year>) q.getResultList();
			logger.info("list  22-->>");
			int yid = 0;
			if (bb.size() > 0) {
				yid = bb.get(0).getYearId();
				logger.info("year id---->" + yid);
			}
			Payroll result1 = new Payroll();
			result1.setEmployee(entitymanager.find(Employee.class, nid));
			// result1.setEmpId(save.getEmployeeid());
			result1.setAdvanceAmount(save.getAdvanceamount());
			result1.setCommission(save.getCommission());
			result1.setOverTime(save.getOvertime());
			result1.setTotalSalary(save.getTotalsalary());
			result1.setAbsent(save.getAbsent());
			result1.setAttendanceHours(save.getAttendancehours());
			result1.setLateAttendance(save.getLateattendance());
			result1.setGoBackEarly(save.getGobackearly());
			result1.setWorkingDays(save.getWorkingdays());
			result1.setPayrollDate(save.getTodayDate());
			logger.info("payroll date----->>>" + save.getTodayDate());
			result1.setPayrollNumber(save.getPayrollno());
			result1.setMonth(entitymanager.find(Month.class, mid));
			result1.setYear(entitymanager.find(Year.class, yid));
			result1.setClient_ID(clientID);
			result1.setApprovalStatus("draft");
			result1.setUserID(entitymanager.find(UserCreate.class,namez.get(0).getUserID().getUserNo()));
			result1.setStatus("inserted");
			result1.setCreatedDate(date);
			result1.setCurrency(save.getCurrency());
			result1.setCurrencyAmount(save.getCurrencyAmount());
			entitymanager.persist(result1);
			logger.info("successfully inserted into payroll");

			Query q1 = null;
			q1 = entitymanager .createQuery("from Payroll where payrollNumber=? and client_ID=?");
			logger.info("P0");
			q1.setParameter(1, save.getPayrollno());
			q1.setParameter(2, clientID);
			logger.info("P1");
			
			List<Payroll> resul = (List<Payroll>) q1.getResultList();
			int epid = 0;
			if (resul.size() > 0) {
				epid = resul.get(0).getPayrollId();
				logger.info("payroll id--->" + epid);
			}
			q = entitymanager.createQuery("from Employee where employeeId=? and client_ID=?");
			q.setParameter(1, save.getEmployeeid());
			q.setParameter(2, clientID);
			List<Employee> result2 = (List<Employee>) q.getResultList();
			logger.info("list  22-->>");
			int sdid = 0;
			if (result2.size() > 0) {
				sdid = result2.get(0).getEmployeeDetailsId();
				logger.info("staff id---->" + sdid);
			}
			Employee mer = entitymanager.find(Employee.class, sdid);
			mer.setPayrollStatus("payroll generated");
			mer.setPayroll(entitymanager.find(Payroll.class, epid));
			entitymanager.merge(mer);
			logger.info("successfully merged-->>>");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	public List<EmployeePayroll> getEmployeePayrollDetails(EmployeePayroll employeePayroll){
		Query q=null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		List<EmployeePayroll> payrolls=new ArrayList<EmployeePayroll>();
		Date date=new Date();
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try{
			cal.add(Calendar.MONTH, -2);
		    Date todate1 = cal.getTime();   
		    System.out.println("month "+todate1.getMonth()+ " date "+ df.format(todate1)+" - "+ df.format(date));
		    System.out.println("user id"+userID);
		    if(userType.equals("Maker")){
		    	q = entitymanager.createQuery("from Payroll where payrollDate between ? and ? and status='inserted' and client_ID=? and user_ID=? ORDER BY createdDate DESC");
				q.setParameter(1, df.format(todate1));
				q.setParameter(2, df.format(date));
				q.setParameter(3, clientID);
				q.setParameter(4, userID);
		    }else{
		    	q = entitymanager.createQuery("from Payroll where payrollDate between ? and ? and status='inserted' and client_ID=? ORDER BY createdDate DESC");
				q.setParameter(1, df.format(todate1));
				q.setParameter(2, df.format(date));
				q.setParameter(3, clientID);
		    }
			List<Payroll> result = (List<Payroll>) q.getResultList();
			System.out.println("paylist-------->" + result.size());
			int edid = 0;
			int j = 0;
			if (result.size() > 0) {
				for (j = 0; j < result.size(); j++) {
					if(clientID.equals(result.get(j).getEmployee().getClient_ID())){
						edid = result.get(j).getPayrollId();
						EmployeePayroll payz = new EmployeePayroll();
						payz.setMonth(result.get(j).getMonth().getType());
						payz.setYear(result.get(j).getYear().getType());
						payz.setBasicsalary(result.get(j).getEmployee().getBasicSalary());
						payz.setEmployeeid(result.get(j).getEmployee().getEmployeeId());
						payz.setName(result.get(j).getEmployee().getEmployeeName());
						payz.setPayrollno(result.get(j).getPayrollNumber());
						payz.setTodayDate(result.get(j).getPayrollDate());
						payz.setAdvanceamount(result.get(j).getAdvanceAmount());
						payz.setTotalsalary(result.get(j).getTotalSalary());
						payz.setWorkingdays(result.get(j).getWorkingDays());
						payz.setCommission(result.get(j).getCommission());
						payz.setOvertime(result.get(j).getOverTime());
						payz.setCurrency(result.get(j).getCurrency());
						payz.setCurrencyAmount(result.get(j).getCurrencyAmount());
						payz.setId(result.get(j).getPayrollId());
						payrolls.add(payz);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return payrolls;
	}
	
	public List<String> getAccountDepsit(String clientID, ATransaction atrans){
		List<String> accountDeposit=null;
		Query v=null;
		try{
			v=entitymanager.createQuery("select depositTo from AccountDeposit where clientID=? and accountStatus='Trans' and status='Active'");
			v.setParameter(1, clientID);
			accountDeposit=(ArrayList<String>)v.getResultList();
			HashSet<String> accounts=new HashSet<String>(accountDeposit);
			accountDeposit.clear();
			accountDeposit.addAll(accounts);
			Collections.sort(accountDeposit);
			v=entitymanager.createQuery("from Transaction where transactionNo=?");
			v.setParameter(1, atrans.getTransactionNo());
			List<Transaction> transaction=(List<Transaction>)v.getResultList();
			if(transaction.size()>0){
				v=null;
				v=entitymanager.createQuery("from AccountPayment where client_ID=? and transaction_ID=?");
				v.setParameter(1, clientID);
				v.setParameter(2, transaction.get(0).getTransactionId());
				List<AccountPayment> acpayment=(List<AccountPayment>)v.getResultList();
				if(acpayment.size()>0){
					BigDecimal amount=BigDecimal.valueOf(0);
					for (int i = 0; i < acpayment.size(); i++) {
						amount=amount.add(new BigDecimal(acpayment.get(i).getPaidAmount()));
					}
					atrans.setBalAmount(String.valueOf(new BigDecimal(transaction.get(0).getAmount()).subtract(amount)));
				}else{
					atrans.setBalAmount(transaction.get(0).getAmount());
				}
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		return accountDeposit;
	}
	
	@Transactional(value="transactionManager")
	public String saveAcountDeposit(String clientID, ATransaction aTransaction){
		System.out.println("save account deposit");
		Query v=null;
		String status="";Date date=new Date();
		try{
			v=entitymanager.createQuery("from AccountDeposit where depositTo=? and status='Active'");
			v.setParameter(1, aTransaction.getTransactionType());
			List<AccountDeposit> accDeposit=(List<AccountDeposit>)v.getResultList();
			if(accDeposit.size()>0){
				status="Exist";
			}else{
				AccountDeposit account=new AccountDeposit();
				account.setAccountType(aTransaction.getAccountType());
				account.setDepositTo(aTransaction.getTransactionType());
				account.setBalance(aTransaction.getAmount());
				account.setStatus("Active");
				account.setClientID(clientID);
				account.setDate(date);
				account.setAccountStatus("Trans");
				entitymanager.persist(account);
				status="Success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}
	
	/*@Override
	public void getAccountTypes(String clientID, ATransaction aTransaction){
		System.out.println("get account types");
		Query v=null;
		List<String> acctypes=new ArrayList<String>();
		List<String> transTypes=new ArrayList<String>();
		try{
			v=entitymanager.createQuery("from AccountType where status='Active'");
			List<AccountType> accType=(List<AccountType>)v.getResultList();
			if(accType.size()>0){
				for (int i = 0; i < accType.size(); i++) {
					acctypes.add(accType.get(i).getAccountType());
					transTypes.add(accType.get(i).getDescription());
				}
				HashSet<String> accoType=new HashSet<String>(acctypes);
				acctypes.clear();acctypes.addAll(accoType);
				Collections.sort(acctypes);
				Collections.sort(transTypes);
				aTransaction.setAccountTypes(acctypes);
				aTransaction.setDetailTypes(transTypes);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}*/

	@Transactional(value="transactionManager")
	public String saveTransPayment(String clientID, ATransaction atransaction){
		System.out.println("save trans payment dao");
		Query v=null;
		Date date=new Date();
		try{
			v=entitymanager.createQuery("from Transaction where transactionNo=?");
			v.setParameter(1, atransaction.getTransactionNo());
			List<Transaction> transaction=(List<Transaction>)v.getResultList();
			if(transaction.size()>0){
				AccountPayment acpayment=new AccountPayment();
				acpayment.setAccountDeposit(atransaction.getAccounts());
				if(new BigDecimal(atransaction.getPayAmount()).compareTo(new BigDecimal(atransaction.getBalAmount()))==0){
					acpayment.setBalanceAmount("0");
					acpayment.setStatus("paid");
				}else{
					acpayment.setBalanceAmount(String.valueOf(new BigDecimal(atransaction.getAmount()).
						subtract(new BigDecimal(atransaction.getPayAmount()))));
					acpayment.setStatus("pending");
				}
				acpayment.setPaidAmount(atransaction.getPayAmount());
				acpayment.setClient_ID(clientID);
				acpayment.setDate(date);
				acpayment.setTransaction(entitymanager.find(Transaction.class, transaction.get(0).getTransactionId()));
				entitymanager.persist(acpayment);
				if(acpayment.getStatus().equals("paid")){
					Transaction transUpdt=entitymanager.find(Transaction.class, transaction.get(0).getTransactionId());
					transUpdt.setPaymentStatus("Paid");
					entitymanager.merge(transUpdt);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}


	@Transactional(value="transactionManager")
	public void updatequalify(String qualID) {
		EmployeeQualification qualupdt=entitymanager.find(EmployeeQualification.class,Integer.parseInt(qualID));
		qualupdt.setStatus("DeActive");
		entitymanager.merge(qualupdt);
	}

	@Transactional(value="transactionManager")
	public void updateExpernce(String expID) {
		Experience qualupdt=entitymanager.find(Experience.class,Integer.parseInt(expID));
		qualupdt.setStatus("DeActive");
		entitymanager.merge(qualupdt);
	}
	
	
	
	public List<AccountType> getAllTypes(){
		Query v=null;
		v=entitymanager.createQuery("from AccountType where status='Active'");
		List<AccountType> accountTypes=(List<AccountType>)v.getResultList();
		return accountTypes;
	}
	
	public List<AccountDeposit> getDeposit(String clientID, String accountType, AccountsDatabean accountsDatabean){
		Query v=null;
		List<AccountDeposit> accDeposit=null;
		try{			
			if(accountsDatabean.getReportPeriod().equals("All dates")){
				v=entitymanager.createQuery("from AccountDeposit where depositTo=? and clientID=? and status='Active'");
				v.setParameter(1, accountType);
				v.setParameter(2, clientID);
			}else{
				v=entitymanager.createQuery("from AccountDeposit where depositTo=? and clientID=? and date between ? and ? and status='Active'");
				v.setParameter(1, accountType);
				v.setParameter(2, clientID);
				v.setParameter(3, accountsDatabean.getFromDate());
				v.setParameter(4, accountsDatabean.getToDate());
			}	
			accDeposit=(List<AccountDeposit>)v.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return accDeposit;
	}
	
	public List<AccountDeposit> getDeposits(String clientID, AccountsDatabean accountsDatabean){
		Query v=null;
		if(accountsDatabean.getReportPeriod().equals("All dates")){
			v=entitymanager.createQuery("from AccountDeposit where clientID=? and status='Active'");
			v.setParameter(1, clientID);
		}else{
			v=entitymanager.createQuery("from AccountDeposit where clientID=? and date between ? and ? and status='Active'");
			v.setParameter(1, clientID);
			v.setParameter(2, accountsDatabean.getFromDate());
			v.setParameter(3, accountsDatabean.getToDate());
		}		
		List<AccountDeposit> accDeposit=(List<AccountDeposit>)v.getResultList();
		return accDeposit;
	}

	public List<Transaction> getTransactionDetails(String clientID){
		Query v=null;
		v=entitymanager.createQuery("from Transaction where client_ID=? and status='inserted'");
		v.setParameter(1, clientID);
		List<Transaction> trans=(List<Transaction>)v.getResultList();
		return trans;
	}
	
	public List<Transaction> getTransactionDetails(String clientID,AccountsDatabean accountsDatabean){
		Query v=null;
		List<Transaction> trans=null;
		try{
			
			if(accountsDatabean.getReportPeriod().equals("All dates")){
				v=entitymanager.createQuery("from Transaction where client_ID=? and status='inserted'");
				v.setParameter(1, clientID);
			}else{
				v=entitymanager.createQuery("from Transaction where client_ID=? and transactionDate between ? and ? and status='inserted'");
				v.setParameter(1, clientID);
				v.setParameter(2, accountsDatabean.getFromDate());
				v.setParameter(3, accountsDatabean.getToDate());
			}				
			trans=(List<Transaction>)v.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}		
		return trans;
	}
	
	public List<AccountPayment> getPaymentDetails(int transactionId){
		Query v=null;
		v=entitymanager.createQuery("from AccountPayment where transaction_ID=?");
		v.setParameter(1, transactionId);
		List<AccountPayment> accountPayment=(List<AccountPayment>)v.getResultList();
		return accountPayment;
	}
	
	public List<AccountPayment> getPaymentDetails(int transactionId,AccountsDatabean accountsDatabean){
		Query v=null;
		if(accountsDatabean.getReportPeriod().equals("All dates")){
			v=entitymanager.createQuery("from AccountPayment where transaction_ID=?");
			v.setParameter(1, transactionId);
		}else{
			v=entitymanager.createQuery("from AccountPayment where transaction_ID=? and date between ? and ?");
			v.setParameter(1, transactionId);
			v.setParameter(2, accountsDatabean.getFromDate());
			v.setParameter(3, accountsDatabean.getToDate());
		}
		List<AccountPayment> accountPayment=(List<AccountPayment>)v.getResultList();
		return accountPayment;
	}
	
	public List<I0021> getSalesDetails(String clientID){
		Query v=null;
		v=entitymanager.createQuery("from I0021 where client_ID=? and (status='insert' or status='delivered' or status='Delivered' or status='Quick sales')");
		v.setParameter(1, clientID);
		List<I0021> i0021=(List<I0021>)v.getResultList();
		return i0021;
	}
	
	public List<I0021> getSalesDetails(String clientID,AccountsDatabean accountsDatabean){
		Query v=null;
		if(accountsDatabean.getReportPeriod().equals("All dates")){
			v=entitymanager.createQuery("from I0021 where client_ID=? and (status='insert' or status='delivered' or status='Delivered' or status='Quick sales')");
			v.setParameter(1, clientID);
		}else{
			v=entitymanager.createQuery("from I0021 where client_ID=? and salesOrderDate between ? and ? and (status='insert' or status='delivered' or status='Delivered' or status='Quick sales')");
			v.setParameter(1, clientID);
			v.setParameter(2, accountsDatabean.getFromDate());
			v.setParameter(3, accountsDatabean.getToDate());
		}
		List<I0021> i0021=(List<I0021>)v.getResultList();
		return i0021;
	}
	
	public List<I0022> getSalesInvoice(int sales_ID){
		Query v=null;
		v=entitymanager.createQuery("from I0022 where sales_ID=?");
		v.setParameter(1, sales_ID);
		List<I0022> i0022=(List<I0022>)v.getResultList();
		return i0022;
	}
	
	public List<I0022> getSalesInvoice(int sales_ID,AccountsDatabean accountsDatabean){
		Query v=null;
		if(accountsDatabean.getReportPeriod().equals("All dates")){
			v=entitymanager.createQuery("from I0022 where sales_ID=?");
			v.setParameter(1, sales_ID);
		}else{
			v=entitymanager.createQuery("from I0022 where sales_ID=? and invoiceDate between ? and ?");
			v.setParameter(1, sales_ID);
			v.setParameter(2, accountsDatabean.getFromDate());
			v.setParameter(3, accountsDatabean.getToDate());
		}
		List<I0022> i0022=(List<I0022>)v.getResultList();
		return i0022;
	}
	
	public List<I0023> getSalesPayment(int invoice_ID){
		Query v=null;
		v=entitymanager.createQuery("from I0023 where invoice_ID=?");
		v.setParameter(1, invoice_ID);
		List<I0023> i0023=(List<I0023>)v.getResultList();
		return i0023;
	}
	
	public List<I0023> getSalesPayment(int invoice_ID,AccountsDatabean accountsDatabean){
		Query v=null;
		if(accountsDatabean.getReportPeriod().equals("All dates")){
			v=entitymanager.createQuery("from I0023 where invoice_ID=?");
			v.setParameter(1, invoice_ID);
		}else{ 
			v=entitymanager.createQuery("from I0023 where invoice_ID=? and startDate between ? and ?");
			v.setParameter(1, invoice_ID);
			v.setParameter(2, accountsDatabean.getFromDate());
			v.setParameter(3, accountsDatabean.getToDate());
		}
		List<I0023> i0023=(List<I0023>)v.getResultList();
		return i0023;
	}
	
	public List<I0015> getPurchaseDetails(String clientID){
		Query v=null;
		v=entitymanager.createQuery("from I0015 where client_ID=? and (status='insert' or status='delivered')");
		v.setParameter(1, clientID);
		List<I0015> i0015=(List<I0015>)v.getResultList();
		return i0015;
	}
	
	public List<I0015> getPurchaseDetails(String clientID,AccountsDatabean accountsDatabean){
		Query v=null;
		if(accountsDatabean.getReportPeriod().equals("All dates")){
			v=entitymanager.createQuery("from I0015 where client_ID=? and (status='insert' or status='delivered')");
			v.setParameter(1, clientID);
		}else{
			v=entitymanager.createQuery("from I0015 where client_ID=? and orderDate between ? and ? and (status='insert' or status='delivered')");
			v.setParameter(1, clientID);
			v.setParameter(2, accountsDatabean.getFromDate());
			v.setParameter(3, accountsDatabean.getToDate());
		}
		List<I0015> i0015=(List<I0015>)v.getResultList();
		return i0015;
	}
	
	public String getVendorName(int purchase_ID){
		Query v=null;
		v=entitymanager.createQuery("from I0016 where purchase_ID=?");
		v.setParameter(1, purchase_ID);
		List<I0016> i0016=(List<I0016>)v.getResultList();
		return i0016.get(0).getI0031().getI0025().getVendorPhoneNumber();
	}
	
	public List<I0022> getPurchaseInvoice(int purchase_ID){
		Query v=null;
		v=entitymanager.createQuery("from I0022 where purchase_ID=?");
		v.setParameter(1, purchase_ID);
		List<I0022> i0022=(List<I0022>)v.getResultList();
		return i0022;
	}
	
	public List<I0022> getPurchaseInvoice(int purchase_ID,AccountsDatabean accountsDatabean){
		Query v=null;
		if(accountsDatabean.getReportPeriod().equals("All dates")){
			v=entitymanager.createQuery("from I0022 where purchase_ID=?");
			v.setParameter(1, purchase_ID);
		}else{
			v=entitymanager.createQuery("from I0022 where purchase_ID=? and invoiceDate between ? and ?");
			v.setParameter(1, purchase_ID);
			v.setParameter(2, accountsDatabean.getFromDate());
			v.setParameter(3, accountsDatabean.getToDate());
		}
		List<I0022> i0022=(List<I0022>)v.getResultList();
		return i0022;
	}
	
	public String getType(String description){
		Query v=null;
		v=entitymanager.createQuery("from AccountType where description=? and status='Active'");
		v.setParameter(1, description);
		List<AccountType> accountTypes=(List<AccountType>)v.getResultList();
		return accountTypes.get(0).getAccountType();
	}
	
/* Prema */
	@Override
	public void getsalestransactioncustprod(String clientID,ATransaction aTransaction) {
		Query q=null;List<String> customerNameList=null;List<String> productNameList=null;
		try{
			q=entitymanager.createQuery("select customerName from I0032 where client_ID=? and status='in' and approvalStatus='Approved'");
			q.setParameter(1, clientID);
			customerNameList=q.getResultList();
			HashSet<String> customethash=new HashSet<String>(customerNameList);
			customerNameList.clear();customerNameList.addAll(customethash);
			aTransaction.setCustomerNameList(customerNameList);
			q=entitymanager.createQuery("select productName from I0001 where client_ID=? and status='i' and approvalStatus='Approved'");
			q.setParameter(1, clientID);
			productNameList=q.getResultList();
			HashSet<String> producthash=new HashSet<String>(productNameList);
			productNameList.clear();productNameList.addAll(producthash);
			aTransaction.setProductNameList(productNameList);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void getcustomerdetails(String clientID, ATransaction aTransaction) {
		Query q=null;
		try{
			q=entitymanager.createQuery("from I0032 where client_ID=? and customerName=? and status='in' and approvalStatus='Approved'");
			q.setParameter(1, clientID);
			q.setParameter(2, aTransaction.getCustomerName());
			List<I0032> customerList=(List<I0032>)q.getResultList();
			if(customerList.size()>0){
				aTransaction.setCustomerEmailId(customerList.get(0).geteMail());
				aTransaction.setCustomerBillingAddress(customerList.get(0).getAddress());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*john clinton*/
	@Override
	public void expenseResource(String clientID, ATransaction aTransaction) {
		 Query q=null;
		 List<String> vendorNameList=null;
		 List<String> customerNameList=null;
		 List<String> employeeNameList=null;
		 List<String> allNameList=null;
		 // logger to bring the client ID 
		 // logger.info("----------- Inside the expenseResource() ------------");
		 // logger.info("----------- Display the client ID -------------")
		try {
			allNameList = new ArrayList<String>();
			// we need to bring into property file or some status table 
			
			if(aTransaction.getExpenseResourcetype().equalsIgnoreCase("Vendor")){
				 q=entitymanager.createQuery("select vendorPhoneNumber from I0025 where client_ID=? and status='i' and approvalStatus='Approved'");
				 q.setParameter(1, clientID);
				 vendorNameList=q.getResultList();
				 aTransaction.setVendorlist(vendorNameList);
			}
			else if(aTransaction.getExpenseResourcetype().equalsIgnoreCase("All")){
				 q=entitymanager.createQuery("select vendorPhoneNumber from I0025 where client_ID=? and status='i' and approvalStatus='Approved'");
				 q.setParameter(1, clientID);
				 vendorNameList=q.getResultList();
				 // for each need to bring instead of for loop 
				 for (int i = 0; i < vendorNameList.size(); i++) {
					 allNameList.add(vendorNameList.get(i)+"/Vendor");
				 }
				 q=entitymanager.createQuery("select customerName from I0032 where client_ID=? and status='in' and approvalStatus='Approved'");
				 q.setParameter(1, clientID);
				 customerNameList=q.getResultList();
				 // for each need to bring instead of for loop
				 for (int i = 0; i < customerNameList.size(); i++) {
					 allNameList.add(customerNameList.get(i)+"/Customer");
				 }
				 q=entitymanager.createQuery("select employeeName from Employee where client_ID=? and status='inserted' and approvalStatus='Approved'");
				 q.setParameter(1, clientID);
				 employeeNameList=q.getResultList();
				 // for each need to bring instead of for loop 

				 for (int i = 0; i < employeeNameList.size(); i++) {
					 allNameList.add(employeeNameList.get(i)+"/Employee");
				 }
				 aTransaction.setAllList(allNameList);
			}
		} catch (Exception e) {
			//need to change as logger
			 e.printStackTrace();
		}
		//finally missing
		// need to bring null as it i will impact server JVM
	}

	
	@Override
	public void accountbalance(String clientID, ATransaction aTransaction) {
		logger.info("[accountbalance()]------------------------Inside accountbalance() in DaoImpln Calling---------------------------");
		Query q=null; List<ChartOfAccount> accountlist=null;
		try{			
			q=entitymanager.createQuery("from ChartOfAccount where client_ID=? and accountName=? and status=?");
			q.setParameter(1, clientID);	
			q.setParameter(2, aTransaction.getFromAccount());
			q.setParameter(3, "Active");
			accountlist=(List<ChartOfAccount>)q.getResultList();
			if(accountlist.size()>0){
				aTransaction.setAccountBalance("AED "+accountlist.get(0).getBalance());
			}
		}catch(Exception e){
			logger.warn("---------------------Inside Exception----------------------------"+e.getMessage());
		}finally{
			q=null;accountlist=null;
		}
	}

	
				public String expenseUpdateCoa(ATransaction aTransaction,String clientID){
					int accountID=0;
					int coaID=0;
					ChartOfAccount account;
					String status="";
					try {
						for (int i = 0; i < aTransaction.getExpenseTransactionlist().size(); i++) {
								if (aTransaction.getTransactionType().equalsIgnoreCase("Bill") || aTransaction.getTransactionType().equalsIgnoreCase("Vendor Credit")) {
									coaID=getExpenseCoaId(aTransaction.getExpenseTransactionlist().get(i).getIndex(),"Secondary Account");
									account=getAccountID(clientID, aTransaction.getExpenseTransactionlist().get(i).getToAccount(),0);
									
									System.out.println("pid"+coaID+"======chartof account id"+account.getChart_of_account_ID());
									ExpenseCoa expenseCoaUpdate=entitymanager.find(ExpenseCoa.class,coaID);
									expenseCoaUpdate.setChartOfAccount(entitymanager.find(ChartOfAccount.class,account.getChart_of_account_ID()));
									entitymanager.merge(expenseCoaUpdate);	
									
								}
								else if (aTransaction.getTransactionType().equalsIgnoreCase("Expense") || aTransaction.getTransactionType().equalsIgnoreCase("Check")) {
									coaID=getExpenseCoaId(aTransaction.getExpenseTransactionlist().get(i).getIndex(),"Secondary Account");
									account=getAccountID(clientID, aTransaction.getExpenseTransactionlist().get(i).getToAccount(),0);
									
									System.out.println("pid"+coaID+"======chartof account id"+account.getChart_of_account_ID());
									ExpenseCoa expenseCoaSUpdate=entitymanager.find(ExpenseCoa.class,coaID);
									expenseCoaSUpdate.setChartOfAccount(entitymanager.find(ChartOfAccount.class,account.getChart_of_account_ID()));
									entitymanager.merge(expenseCoaSUpdate);	
									
									coaID=getExpenseCoaId(aTransaction.getExpenseTransactionlist().get(i).getIndex(),"Main Account");
									account=getAccountID(clientID, aTransaction.getFromAccount(),0);
									
									System.out.println("pid"+coaID+"======chartof account id"+account.getChart_of_account_ID());
									ExpenseCoa expenseCoaMUpdate=entitymanager.find(ExpenseCoa.class,coaID);
									expenseCoaMUpdate.setChartOfAccount(entitymanager.find(ChartOfAccount.class,account.getChart_of_account_ID()));
									entitymanager.merge(expenseCoaMUpdate);	
								}
								
						}
						status="Success";
					} catch (Exception e) {
						e.printStackTrace();
					}
					return status;
				}
				public int getExpenseCoaId(int ETransID, String typeofaccount){
					System.out.println("------getExpenseCoaId--------->"+ETransID+"----------->"+typeofaccount);
					Query q=null;
					int ExpenseCoaId=0;
					List<ExpenseCoa> expenscoaList=null;
					try {
						q=entitymanager.createQuery("from ExpenseCoa where expense_transaction_ID=? and accountStatus=? and status='Active'");
						q.setParameter(1,ETransID );
						q.setParameter(2,typeofaccount);
						expenscoaList=(List<ExpenseCoa>)q.getResultList();
						ExpenseCoaId=expenscoaList.get(0).getExpense_coa_ID();
						System.out.println("------ExpenseCoaId--------->"+ExpenseCoaId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return ExpenseCoaId;
				}
	
	
	@Override
	public List<ExpenseCoa> getTransactionAccount(int transactionID,String accountstatustype) {
		Query v=null;
		List<ExpenseCoa> expensecoadataList=null;
		System.out.println("--------chk------->"+transactionID+"---------->"+accountstatustype);
		try{			
			v=entitymanager.createQuery("from ExpenseCoa where expense_transaction_ID=? and accountStatus=? and status='Active'");
			v.setParameter(1, transactionID);
			v.setParameter(2, accountstatustype);
			expensecoadataList=(List<ExpenseCoa>)v.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("ExpenseCoa-fsdfs-->"+expensecoadataList.size());
		
		return expensecoadataList;
	}
	
	
	@Override
	public List<ChartOfAccount> getaccountnamedetail(String clientID, String depositName){
		Query v=null;
		List<ChartOfAccount> accDeposit=null;
		try{			
			
			v=entitymanager.createQuery("from ChartOfAccount where accountName=? and client_ID=? and status='Active'");
			v.setParameter(1, depositName);
			v.setParameter(2, clientID);
		
			accDeposit=(List<ChartOfAccount>)v.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return accDeposit;
	}
	/*neela*/
							
	/*  manos vajsasn*/	

					@Override
					public List<ExpenseTransaction> getbillnumber(String clientID,String transactionType) {
						List<ExpenseTransaction>transactions=null;
						Query v=null;
						try {
							v=entitymanager.createQuery("from ExpenseTransaction where client_ID=? and transactionType=?");
							v.setParameter(1, clientID);
							v.setParameter(2,  transactionType);
							transactions=(List<ExpenseTransaction>)v.getResultList();
							System.out.println("--- dao getbillnumber method transactions list size---->"+transactions.size()); 
						} catch (Exception e) {
							e.printStackTrace();
						}
						return transactions;
					}

					@Override
					public void mailresource(String clientID,ATransaction aTransaction) {
							Query q=null;
							List<String>addresslist=null;
							try {
								
								 q=entitymanager.createQuery("select address from I0025 where vendorPhoneNumber=? and client_ID=? and status='i' and approvalStatus='Approved'");
								 q.setParameter(1, aTransaction.getVendorName());
								q.setParameter(2, clientID);
								addresslist=q.getResultList();
								aTransaction.setMailingAddress(addresslist.get(0));
								System.out.println("-------ggggg----->"+addresslist.get(0));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					
					
					/*NEELA*/

					public ChartOfAccount getAccountID(String clientID, String account, int accounttype_id) {
						logger.info("[getAccountID()]------------Inside getAccountID in DaoImpln Calling--------------------");
						 Query q=null;ChartOfAccount accountID = null;ChartOfAccount chartofaccount=null;
						 List<ChartOfAccount> accountlist=null;
						 try{   
							 accountID=new ChartOfAccount();
							 q=entitymanager.createQuery("from ChartOfAccount where client_ID=? and accountName=? and status=?");
							 q.setParameter(1, clientID);
							 q.setParameter(2, account);
							 q.setParameter(3, "Active");
							 accountlist=(List<ChartOfAccount>)q.getResultList();
							 if(accountlist.size()>0){
								accountID.setChart_of_account_ID(accountlist.get(0).getChart_of_account_ID());
							 }else{
							 	chartofaccount=new ChartOfAccount();  
								chartofaccount.setAccountNameDescription("");
								chartofaccount.setBalance("0");
								chartofaccount.setClient_ID(clientID);
								chartofaccount.setTrandate(now);
								chartofaccount.setAccountType(entitymanager.find(AccountType.class, accounttype_id));
								chartofaccount.setOpeningBalance(chartofaccount.getAccountType().getOpeningBalance());
								chartofaccount.setStatus(activeStatus);
								chartofaccount.setCreatedDate(now);
								chartofaccount.setUpdatedDate(null);
								chartofaccount.setApprovalStatus(draftStatus);
								entitymanager.persist(chartofaccount);
								q=null;
								q=entitymanager.createQuery("from ChartOfAccount where client_ID=?");
								q.setParameter(1, clientID);
								accountlist=(List<ChartOfAccount>)q.getResultList();
								if(accountlist.size()>0){
									accountID.setChart_of_account_ID(accountlist.get(accountlist.size()-1).getChart_of_account_ID());
								}
							 }
						 }catch(Exception e){
							 e.printStackTrace();
						 }
						 return accountID;
					}

	// Accounts module Code Begin by Prema 

	@Transactional(value = "transactionManager")
	public String editAccount(String clientID, AccountsDatabean accountsDatabean) {
		logger.info("[editAccount()]----------------------Inside editAccount() in DaoImpln Calling----------------------------");
		Query q=null;String status="";BankAcct bankAcct=null;BigDecimal tempamt=BigDecimal.valueOf(0);
		List<ChartOfAccount> accDeposit=null;OtherCurrentAssetsAcct otherCurrentAssetsAcct=null;FixedAssetsAcct fixedAssetsAcct=null;
		OtherAssetsAcct otherAssetsAcct=null;OpenbalEquityAcct openbalEquityAcct=null;String debitParticular="";
		String creditParticular="";CreditCardAcct creditCardAcct=null;OtherCurrentLibAcct otherCurrentLibAcct=null;
		NotesPayableAcct notesPayableAcct=null;EquityAcct equityAcct=null;List<ChartOfAccount> accDeposit1=null;
		JournalEntry journalEntry=null;
		try{
			List<String> assetList=Arrays.asList("Bank","Other Current Assets","Fixed Assets","Other Assets");
			List<String> libList=Arrays.asList("Credit Card","Other Current Liabilities","Notes Payable","Equity");
			q=entitymanager.createQuery("from ChartOfAccount where accountName=? and client_ID=? and status=?");
			q.setParameter(1, accountsDatabean.getAccount_name());
			q.setParameter(2, clientID);
			q.setParameter(3, activeStatus);
			accDeposit=(List<ChartOfAccount>)q.getResultList();
			accountsDatabean.setName("Opening Balance Equity");
			q=entitymanager.createQuery("from ChartOfAccount where accountName=? and client_ID=? and status=?");
			q.setParameter(1, accountsDatabean.getName());
			q.setParameter(2, clientID);
			q.setParameter(3, activeStatus);
			accDeposit1=(List<ChartOfAccount>)q.getResultList();
			ChartOfAccount coa1=entitymanager.find(ChartOfAccount.class, accDeposit1.get(0).getChart_of_account_ID());
			if(accDeposit.size()>0){
				int id=accDeposit.get(0).getChart_of_account_ID();
				ChartOfAccount coa=entitymanager.find(ChartOfAccount.class, id);
				coa.setStatus(accountsDatabean.getStatus());
				accountsDatabean.setAccountStatus(activeStatus);accountsDatabean.setClientID(clientID);
				if(assetList.contains(accDeposit.get(0).getAccountType().getCategoryType())){
					accountsDatabean.setBalance(tempamt.subtract(new BigDecimal(accDeposit.get(0).getBalance())).toString());
					if(accDeposit.get(0).getAccountType().getCategoryType().equals(bankCategoryType)){
						accountsDatabean.setTransactionType("Cheque Expense");
						bankAcct=new BankAcct(accountsDatabean,accDeposit);
						bankAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, accDeposit.get(0).getChart_of_account_ID()));
						entitymanager.persist(bankAcct);
					}
					if(accDeposit.get(0).getAccountType().getCategoryType().equals("Other Current Assets")){
						accountsDatabean.setTransactionType("Journal Entry");
						otherCurrentAssetsAcct=new OtherCurrentAssetsAcct(accountsDatabean,accDeposit);
						otherCurrentAssetsAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, accDeposit.get(0).getChart_of_account_ID()));
						entitymanager.persist(otherCurrentAssetsAcct);
					}
					if(accDeposit.get(0).getAccountType().getCategoryType().equals("Fixed Assets")){
						accountsDatabean.setTransactionType("Journal Entry");
						fixedAssetsAcct=new FixedAssetsAcct(accountsDatabean,accDeposit);
						fixedAssetsAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, accDeposit.get(0).getChart_of_account_ID()));
						entitymanager.persist(fixedAssetsAcct);
					}
					if(accDeposit.get(0).getAccountType().getCategoryType().equals("Other Assets")){
						accountsDatabean.setTransactionType("Journal Entry");
						otherAssetsAcct=new OtherAssetsAcct(accountsDatabean,accDeposit);
						otherAssetsAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, accDeposit.get(0).getChart_of_account_ID()));
						entitymanager.persist(otherAssetsAcct);
					}
					accountsDatabean.setDebitAmount(accDeposit.get(0).getBalance());accountsDatabean.setCreditAmount("");
					openbalEquityAcct=new OpenbalEquityAcct(accountsDatabean,accDeposit1);
					openbalEquityAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, accDeposit1.get(0).getChart_of_account_ID()));
					entitymanager.persist(openbalEquityAcct);
					coa1.setBalance(new BigDecimal(accDeposit1.get(0).getBalance()).subtract(new BigDecimal(accDeposit.get(0).getBalance())).toString());
					debitParticular=accountsDatabean.getName();
					creditParticular=accountsDatabean.getAccount_name();
				}
				if(libList.contains(accDeposit.get(0).getAccountType().getCategoryType())){
					if(accDeposit.get(0).getAccountType().getCategoryType().equals("Credit Card")){
						accountsDatabean.setTransactionType("Credit Card Credit");
						accountsDatabean.setBalance(tempamt.subtract(new BigDecimal(accDeposit.get(0).getBalance())).toString());
						creditCardAcct=new CreditCardAcct(accountsDatabean,accDeposit);
						creditCardAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, accDeposit.get(0).getChart_of_account_ID()));
						entitymanager.persist(creditCardAcct);
					}
					if(accDeposit.get(0).getAccountType().getCategoryType().equals("Other Current Liabilities")){
						accountsDatabean.setTransactionType("Journal Entry");
						accountsDatabean.setBalance(tempamt.subtract(new BigDecimal(accDeposit.get(0).getBalance())).toString());
						otherCurrentLibAcct=new OtherCurrentLibAcct(accountsDatabean,accDeposit);
						otherCurrentLibAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, accDeposit.get(0).getChart_of_account_ID()));
						entitymanager.persist(otherCurrentLibAcct);
					}
					if(accDeposit.get(0).getAccountType().getCategoryType().equals("Notes Payable")){
						accountsDatabean.setTransactionType("Journal Entry");
						accountsDatabean.setBalance(tempamt.subtract(new BigDecimal(accDeposit.get(0).getBalance())).toString());
						notesPayableAcct=new NotesPayableAcct(accountsDatabean,accDeposit);
						notesPayableAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, accDeposit.get(0).getChart_of_account_ID()));
						entitymanager.persist(notesPayableAcct);
					}
					if(accDeposit.get(0).getAccountType().getCategoryType().equals("Equity")){
						accountsDatabean.setTransactionType("Journal Entry");
						accountsDatabean.setBalance(tempamt.subtract(new BigDecimal(accDeposit.get(0).getBalance())).toString());
						equityAcct=new EquityAcct(accountsDatabean,accDeposit);
						equityAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, accDeposit.get(0).getChart_of_account_ID()));
						entitymanager.persist(equityAcct);
					}
					accountsDatabean.setDebitAmount("");
					accountsDatabean.setCreditAmount(accDeposit.get(0).getBalance());
					accountsDatabean.setBalance(accDeposit.get(0).getBalance());
					openbalEquityAcct=new OpenbalEquityAcct(accountsDatabean,accDeposit1);
					openbalEquityAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, accDeposit1.get(0).getChart_of_account_ID()));
					entitymanager.persist(openbalEquityAcct);
					coa1.setBalance(new BigDecimal(accDeposit1.get(0).getBalance()).add(new BigDecimal(accDeposit.get(0).getBalance())).toString());
					debitParticular=accountsDatabean.getAccount_name();
					creditParticular=accountsDatabean.getName();
				}
				accountsDatabean.setBalance(accDeposit.get(0).getBalance());accountsDatabean.setStatus(accountsDatabean.getAccountStatus());
				journalEntry=new JournalEntry(accountsDatabean,debitParticular,creditParticular);
				entitymanager.persist(journalEntry);
				coa.setBalance(new BigDecimal(coa.getBalance()).subtract(new BigDecimal(accDeposit.get(0).getBalance())).toString());
				entitymanager.merge(coa);
				entitymanager.merge(coa1);
				entitymanager.flush();
				entitymanager.clear();
				}
				status="Success";
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("-------------------Inside Exception----------------------------"+e.getMessage());
		}
		return status;
	}
	
	public void getExpenseGstdetails(ATransaction aTransaction, ExpenseTransaction expenseTransaction){
		logger.info("[getExpenseGstdetails()-----------------Inside getExpenseGstdetails() in DaoImpln Calling------------------]");
		GstAcct gstAcct2=null;Set<GstAcct> gstaccts=null;int accounttype_id=0;BigDecimal tempamt=BigDecimal.valueOf(0);
		ChartOfAccount coas=null;ChartOfAccount coas1=null;ChartOfAccount coas2=null;GstAcct gstAcct=null;GstAcct gstAcct1=null;
		try{
			gstaccts=new HashSet<GstAcct>();
			for (ATransaction atrans : aTransaction.getExpenseTransactionlist()) {
	        	try{
	        		if(!atrans.getToAccount().equalsIgnoreCase("select")){
		        		if(!aTransaction.getTaxType().equals("Out of scope of Tax") || !aTransaction.getTaxType().equals("")){
		        			accounttype_id=getaccounttypeid(GSTcategorytype,GSTdetailtype);
		        			aTransaction.setCategoryType(GSTcategorytype);aTransaction.setDetailName(GSTdetailtype);
		        			if(gstList.contains(atrans.getTaxes())){
		        				if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
		        					aTransaction.setTransamount(atrans.getCgstAmount());
		        				}else{
		        					BigDecimal cgstCurrency=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),atrans.getCgstAmount());
		        					aTransaction.setTransamount(String.valueOf(cgstCurrency));
		        				}
		        				if(aTransaction.getTransactionType().equalsIgnoreCase("Vendor Credit")){
        	        				aTransaction.setDebitAmount("");aTransaction.setCreditAmount(aTransaction.getTransamount());
        	        				aTransaction.setTransamount(aTransaction.getTransamount());
        	        			}else{
        	        				aTransaction.setDebitAmount(aTransaction.getTransamount());aTransaction.setCreditAmount("");
        	        				aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getTransamount())).toString());
        	        			}
		        				coas1=getSalesChartofAccountID(aTransaction.getClientID(),inputCGSTName,accounttype_id,aTransaction);
        	        			aTransaction.setAccounts(inputCGSTName);aTransaction.setBalance(aTransaction.getTransamount());
        	        			gstAcct1=new GstAcct(expenseTransaction,aTransaction,coas1);
        	        			if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
		        					aTransaction.setTransamount(atrans.getSgstAmount());
		        				}else{
		        					BigDecimal sgstCurrency=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),atrans.getSgstAmount());
		        					aTransaction.setTransamount(String.valueOf(sgstCurrency));
		        				}
        	        			if(aTransaction.getTransactionType().equalsIgnoreCase("Vendor Credit")){
        	        				aTransaction.setDebitAmount("");aTransaction.setCreditAmount(aTransaction.getTransamount());
        	        				aTransaction.setTransamount(aTransaction.getTransamount());
        	        			}else{
        	        				aTransaction.setDebitAmount(aTransaction.getTransamount());aTransaction.setCreditAmount("");
        	        				aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getTransamount())).toString());
        	        			}
        	        			coas2=getSalesChartofAccountID(aTransaction.getClientID(),inputSGSTName,accounttype_id,aTransaction);
        	        			aTransaction.setAccounts(inputSGSTName);aTransaction.setBalance(aTransaction.getTransamount());
        	        			gstAcct2=new GstAcct(expenseTransaction,aTransaction,coas2);
		        			}else if(igstList.contains(atrans.getTaxes())){
		        				if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
		        					aTransaction.setTransamount(atrans.getPercentageAmount());
		        				}else{
		        					BigDecimal igstCurrency=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),atrans.getPercentageAmount());
		        					aTransaction.setTransamount(String.valueOf(igstCurrency));
		        				}
		        				if(aTransaction.getTransactionType().equalsIgnoreCase("Vendor Credit")){
        	        				aTransaction.setDebitAmount("");aTransaction.setCreditAmount(aTransaction.getTransamount());
        	        				aTransaction.setTransamount(aTransaction.getTransamount());
        	        			}else{
        	        				aTransaction.setDebitAmount(aTransaction.getTransamount());aTransaction.setCreditAmount("");
        	        				aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getTransamount())).toString());
        	        			}
        	        			coas=getSalesChartofAccountID(aTransaction.getClientID(),inputIGSTName,accounttype_id,aTransaction);
        	        			aTransaction.setAccounts(inputIGSTName);aTransaction.setBalance(aTransaction.getTransamount());
        	        			gstAcct=new GstAcct(expenseTransaction,aTransaction,coas);
		        			}
		        			
		        		}
		        	}
	        	}catch(NullPointerException e){
	        		e.printStackTrace();
	        	}
				gstaccts.add(gstAcct1);gstaccts.add(gstAcct2);gstaccts.add(gstAcct);
	        }
			expenseTransaction.setGstAccts(gstaccts);
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("-------------------------Inside Exception------------------"+e.getMessage());
		}
	}
	
	public void getEditExpenseGstdetails(ATransaction aTransaction, ExpenseTransaction expenseTransaction){
		logger.info("[getEditExpenseGstdetails()-----------------Inside getEditExpenseGstdetails() in DaoImpln Calling------------------]");
		GstAcct gstAcct2=null;int accounttype_id=0;BigDecimal tempamt=BigDecimal.valueOf(0);
		ChartOfAccount coas=null;ChartOfAccount coas1=null;ChartOfAccount coas2=null;GstAcct gstAcct=null;GstAcct gstAcct1=null;
		try{
			for (ATransaction atrans : aTransaction.getExpenseTransactionlist()) {
	        	try{
	        		if(!atrans.getToAccount().equalsIgnoreCase("select")){
		        		if(!aTransaction.getTaxType().equals("Out of scope of Tax") || !aTransaction.getTaxType().equals("")){
		        			accounttype_id=getaccounttypeid(GSTcategorytype,GSTdetailtype);
		        			aTransaction.setCategoryType(GSTcategorytype);aTransaction.setDetailName(GSTdetailtype);
		        			if(gstList.contains(atrans.getTaxes())){
		        				if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
		        					aTransaction.setTransamount(atrans.getCgstAmount());
		        				}else{
		        					BigDecimal cgstCurrency=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),atrans.getCgstAmount());
		        					aTransaction.setTransamount(String.valueOf(cgstCurrency));
		        				}
		        				if(aTransaction.getTransactionType().equalsIgnoreCase("Vendor Credit")){
        	        				aTransaction.setDebitAmount("");aTransaction.setCreditAmount(aTransaction.getTransamount());
        	        				aTransaction.setTransamount(aTransaction.getTransamount());
        	        			}else{
        	        				aTransaction.setDebitAmount(aTransaction.getTransamount());aTransaction.setCreditAmount("");
        	        				aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getTransamount())).toString());
        	        			}
		        				coas1=getSalesChartofAccountID(aTransaction.getClientID(),inputCGSTName,accounttype_id,aTransaction);
        	        			aTransaction.setAccounts(inputCGSTName);aTransaction.setBalance(aTransaction.getTransamount());
        	        			gstAcct1=new GstAcct(expenseTransaction,aTransaction,coas1);
        	        			entitymanager.persist(gstAcct1);
        	        			entitymanager.flush();
        	        			entitymanager.clear();
        	        			if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
		        					aTransaction.setTransamount(atrans.getSgstAmount());
		        				}else{
		        					BigDecimal sgstCurrency=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),atrans.getSgstAmount());
		        					aTransaction.setTransamount(String.valueOf(sgstCurrency));
		        				}
        	        			if(aTransaction.getTransactionType().equalsIgnoreCase("Vendor Credit")){
        	        				aTransaction.setDebitAmount("");aTransaction.setCreditAmount(aTransaction.getTransamount());
        	        				aTransaction.setTransamount(aTransaction.getTransamount());
        	        			}else{
        	        				aTransaction.setDebitAmount(aTransaction.getTransamount());aTransaction.setCreditAmount("");
        	        				aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getTransamount())).toString());
        	        			}
        	        			coas2=getSalesChartofAccountID(aTransaction.getClientID(),inputSGSTName,accounttype_id,aTransaction);
        	        			aTransaction.setAccounts(inputSGSTName);aTransaction.setBalance(aTransaction.getTransamount());
        	        			gstAcct2=new GstAcct(expenseTransaction,aTransaction,coas2);
        	        			entitymanager.persist(gstAcct2);
        	        			entitymanager.flush();
        	        			entitymanager.clear();
		        			}else if(igstList.contains(atrans.getTaxes())){
		        				if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
		        					aTransaction.setTransamount(atrans.getPercentageAmount());
		        				}else{
		        					BigDecimal igstCurrency=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),atrans.getPercentageAmount());
		        					aTransaction.setTransamount(String.valueOf(igstCurrency));
		        				}
		        				if(aTransaction.getTransactionType().equalsIgnoreCase("Vendor Credit")){
        	        				aTransaction.setDebitAmount("");aTransaction.setCreditAmount(aTransaction.getTransamount());
        	        				aTransaction.setTransamount(aTransaction.getTransamount());
        	        			}else{
        	        				aTransaction.setDebitAmount(aTransaction.getTransamount());aTransaction.setCreditAmount("");
        	        				aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getTransamount())).toString());
        	        			}
        	        			coas=getSalesChartofAccountID(aTransaction.getClientID(),inputIGSTName,accounttype_id,aTransaction);
        	        			aTransaction.setAccounts(inputIGSTName);aTransaction.setBalance(aTransaction.getTransamount());
        	        			gstAcct=new GstAcct(expenseTransaction,aTransaction,coas);
        	        			entitymanager.persist(gstAcct);
        	        			entitymanager.flush();
        	        			entitymanager.clear();
		        			}
		        			
		        		}
		        	}
	        	}catch(NullPointerException e){
	        		e.printStackTrace();
	        	}
	        }
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("-------------------------Inside Exception------------------"+e.getMessage());
		}
	}
	
	@Override
	@Transactional(value="transactionManager")
	public String saveexpenseTrans(ATransaction aTransaction, String clientID) {
		logger.info("[saveexpenseTrans()]----------------------Inside saveexpenseTrans() in DaoImpln Calling----------------------------");
		String status="Fail";ExpenseTransaction expenseTransaction=null;
		try{
			aTransaction.setStatus(activeStatus);aTransaction.setClientID(clientID);
			if(aTransaction.transactionType.equalsIgnoreCase("Bill")){
				aTransaction.setPaymentStatus(unpaidStatus);
			}else{
				aTransaction.setPaymentStatus(paidStatus);
			}
			expenseTransaction=saveExpenseTransDetails(aTransaction);
			getExpenseGstdetails(aTransaction,expenseTransaction);
			if(aTransaction.transactionType.equalsIgnoreCase("Bill")){
				status=saveExpenseBillTransaction(expenseTransaction,aTransaction);
			}
			if(aTransaction.transactionType.equalsIgnoreCase("Expense") || aTransaction.transactionType.equalsIgnoreCase("Check")){
				status=saveExpenseTransaction(expenseTransaction,aTransaction);
			}
			if(aTransaction.transactionType.equalsIgnoreCase("Vendor Credit")){
				status=saveExpenseVendorCreditTransaction(expenseTransaction,aTransaction);
			}
		}catch(Exception e){
			logger.warn("-------------------Inside Exception----------------------------"+e.getMessage());
		}
		return status;
	}
	
	@Transactional(value = "transactionManager")
	public String expenseMakePayment(ATransaction aTransaction, String clientID) {
		logger.info("[expenseMakePayment()]----------------------Inside expenseMakePayment() in DaoImpln Calling----------------------------");
		Query q=null;String status="Fail";ExpenseTransaction expenseTrans=null;List<AccountPayableAcct> accountPayableAcctList=null;
		HashSet<AccountPayableAcct> accountPayableAccts =null;List<ChartOfAccount> chartofaccountList=null;BankAcct bankAcct=null;
		CreditCardAcct creditCardAcct=null;JournalEntry journalEntry=null;String debitParticular="";String creditParticular="";
		BigDecimal tempamt=BigDecimal.valueOf(0);List<I0016>childList=null;;I0016 i0016=null;
		try {
			aTransaction.setStatus(activeStatus);
			if(aTransaction.getTransactionType().equals("Bill"))aTransaction.setTransactionType("Bill Payment");
			if(aTransaction.getTransactionType().equals("Purchase Invoice"))aTransaction.setTransactionType("Purchase Payment");
			accountPayableAccts=new HashSet<AccountPayableAcct>();i0016=new I0016();
			for (int i = 0; i < aTransaction.getPaymentdataTableList().size(); i++) {
				try{
					if(!aTransaction.getPaymentdataTableList().get(i).getTotalAmount().equals("")&& 
								!aTransaction.getPaymentdataTableList().get(i).getPayAmount().equals("")){
						expenseTrans=entitymanager.find(ExpenseTransaction.class, aTransaction.getPaymentdataTableList().get(i).getIndex());
						expenseTrans.setPaidAmount((new BigDecimal(expenseTrans.getPaidAmount()).add(new BigDecimal(aTransaction.getCurrencyAmount()))).toString());
						expenseTrans.setBalanceAmount(new BigDecimal(expenseTrans.getBalanceAmount())
							.subtract(new BigDecimal(aTransaction.getCurrencyAmount())).toString());
						if(expenseTrans.getPaidAmount().equalsIgnoreCase("0")){
							expenseTrans.setPaymentStatus(unpaidStatus);
						}else if(!expenseTrans.getPaidAmount().equalsIgnoreCase("0") && !Double.valueOf(expenseTrans.getBalanceAmount()).equals(Double.valueOf(0))){
							expenseTrans.setPaymentStatus(partialStatus);
						}else if(Double.valueOf(expenseTrans.getBalanceAmount()).equals(Double.valueOf(0))){
							expenseTrans.setPaymentStatus(paidStatus);
						}
						q=entitymanager.createQuery("from I0016 where ordernumber=? and client_ID=?");
						q.setParameter(1, aTransaction.getRefNo());
						q.setParameter(2, clientID);
						childList=(List<I0016>)q.getResultList();
						if(childList.size() > 0){
							i0016=entitymanager.find(I0016.class, childList.get(0).getHas_purchase_ID());
							i0016.setStatus2(expenseTrans.getPaymentStatus()); 
							entitymanager.merge(i0016); 
						}
						q=entitymanager.createQuery("from AccountPayableAcct where accountName=? and expense_transaction_ID=? and status=?");
						q.setParameter(1, accpayName);
						q.setParameter(2, aTransaction.getPaymentdataTableList().get(i).getIndex());
						q.setParameter(3, activeStatus);
						accountPayableAcctList=(List<AccountPayableAcct>)q.getResultList();
						if(accountPayableAcctList.size()>0){
							AccountPayableAcct accountPayableAcct=entitymanager.find(AccountPayableAcct.class, accountPayableAcctList.get(0).getId());
							accountPayableAcct.setDebitAmount(new BigDecimal(accountPayableAcct.getDebitAmount()).add(new BigDecimal(aTransaction.getCurrencyAmount())).toString());
							accountPayableAcct.setBalance(new BigDecimal(accountPayableAcct.getBalance()).subtract(new BigDecimal(aTransaction.getCurrencyAmount())).toString());
							accountPayableAccts.add(accountPayableAcct);
						}
						expenseTrans.setAccountPayableAccts(accountPayableAccts);
						entitymanager.merge(expenseTrans);
						entitymanager.flush();
						entitymanager.clear();
						chartofaccountList=getchartofaccountList(clientID,aTransaction.getFromAccount());
						if(chartofaccountList.size()>0){
							ChartOfAccount coas=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
							if(chartofaccountList.get(0).getAccountType().getCategoryType().equals(bankCategoryType)){
								aTransaction.setBalAmount(tempamt.subtract(new BigDecimal(aTransaction.getCurrencyAmount())).toString());
								bankAcct=new BankAcct(clientID,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTrans);
								bankAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID()));
								entitymanager.persist(bankAcct);
								coas.setBalance(new BigDecimal(coas.getBalance()).subtract(new BigDecimal(aTransaction.getCurrencyAmount())).toString());
							}
							if(chartofaccountList.get(0).getAccountType().getCategoryType().equals(creditcardCategoryType)){
								aTransaction.setDebitAmount("");
								aTransaction.setTotalAmount(aTransaction.getCurrencyAmount());aTransaction.setBalAmount(aTransaction.getCurrencyAmount());
								creditCardAcct=new CreditCardAcct(clientID,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTrans);
								creditCardAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID()));
								entitymanager.persist(creditCardAcct);
								coas.setBalance(new BigDecimal(coas.getBalance()).add(new BigDecimal(aTransaction.getCurrencyAmount())).toString());
							}
							entitymanager.merge(coas);
						}	
					}
					}catch(Exception e){
						logger.warn("------------------Inside Exception-------------------------"+e.getMessage());
					}
				}		
				chartofaccountList=getchartofaccountList(clientID,accpayName);
				if(chartofaccountList.size()>0){
					ChartOfAccount coas=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
					coas.setBalance(new BigDecimal(coas.getBalance()).subtract(new BigDecimal(aTransaction.getCurrencyAmount())).toString());
					entitymanager.merge(coas);
				}
				debitParticular=accpayName;creditParticular=aTransaction.getFromAccount();
				journalEntry=new JournalEntry(aTransaction,clientID,debitParticular,creditParticular,expenseTrans);
				entitymanager.persist(journalEntry);
				entitymanager.flush();
				entitymanager.clear();
				status="success";
			}catch(Exception e){
				logger.warn("-------------------Inside Exception-------------------"+e.getMessage());
			}finally{
				q=null;expenseTrans=null;accountPayableAcctList=null;creditCardAcct=null;journalEntry=null;
				accountPayableAccts =null;chartofaccountList=null;bankAcct=null;debitParticular="";creditParticular="";
			}
			return status;
	}
	 
	@Transactional(value="transactionManager")
	private String saveExpenseVendorCreditTransaction(ExpenseTransaction expenseTransaction, ATransaction aTransaction) {
		logger.info("[saveExpenseVendorCreditTransaction()]----------------------Inside saveExpenseVendorCreditTransaction() in DaoImpln Calling----------------------------");
		AccountPayableAcct accountPayableAcct=null;Set<AccountPayableAcct> accountPayableAccts=null;int accounttype_id=0;String status="Fail";
		ChartOfAccount chartofaccount=null;BigDecimal tempamt=BigDecimal.valueOf(0);JournalEntry journalEntry=null;Set<JournalEntry> journalEntries=null;
		List<ChartOfAccount> chartofaccountList=null;OtherCurrentAssetsAcct otherCurrentAssetsAcct=null;BankAcct bankAcct=null;Set<BankAcct> bankAccts=null;
		OtherAssetsAcct otherAssetsAcct=null;Set<OtherAssetsAcct> otherAssetsAccts=null;FixedAssetsAcct fixedAssetsAcct=null;Set<FixedAssetsAcct> fixedAssetsAccts=null;
		CogAcct cogAcct=null;Set<CogAcct> cogAccts=null;ExpensesAcct expensesAcct=null;Set<ExpensesAcct> expensesAccts=null;Set<OtherCurrentAssetsAcct> otherCurrentAssetsAccts=null;
		OtherExpensesAcct otherExpensesAcct=null;Set<OtherExpensesAcct> otherExpensesAccts=null;CreditCardAcct creditCardAcct=null;Set<CreditCardAcct> creditCardAccts=null;
		OtherCurrentLibAcct otherCurrentLibAcct=null;Set<OtherCurrentLibAcct> otherCurrentLibAccts=null;NotesPayableAcct notesPayableAcct=null;Set<NotesPayableAcct> notesPayableAccts=null;
		EquityAcct equityAcct=null;Set<EquityAcct> equityAccts=null;IncomeAcct incomeAcct=null;Set<IncomeAcct> incomeAccts=null;
		OtherIncomeAcct otherIncomeAcct=null;Set<OtherIncomeAcct> otherIncomeAccts=null;OpenbalEquityAcct openbalEquityAcct=null;Set<OpenbalEquityAcct> openbalEquityAccts=null;
		String amount="";List<String> toAccountList=null;String creditParticular="";String creditAmount="";List<String> toAmountList=null;
		try{
			toAccountList=new ArrayList<String>();toAmountList=new ArrayList<String>();
			otherCurrentAssetsAccts=new HashSet<OtherCurrentAssetsAcct>();openbalEquityAccts=new HashSet<OpenbalEquityAcct>();bankAccts=new HashSet<BankAcct>();creditCardAccts=new HashSet<CreditCardAcct>();
			otherAssetsAccts=new HashSet<OtherAssetsAcct>();fixedAssetsAccts=new HashSet<FixedAssetsAcct>();cogAccts=new HashSet<CogAcct>();incomeAccts=new HashSet<IncomeAcct>();
			otherExpensesAccts=new HashSet<OtherExpensesAcct>();expensesAccts=new HashSet<ExpensesAcct>();otherIncomeAccts=new HashSet<OtherIncomeAcct>();journalEntries=new HashSet<JournalEntry>();
			otherCurrentLibAccts=new HashSet<OtherCurrentLibAcct>();notesPayableAccts=new HashSet<NotesPayableAcct>();equityAccts=new HashSet<EquityAcct>();
			List<String> debitList=Arrays.asList("Other Current Assets","Bank","Fixed Assets","Other Assets","Cost of Goods Sold","Expenses","Other Expenses");
			List<String> creditList=Arrays.asList("Credit Card","Other Current Liabilities","Notes Payable","Equity","Income","Other Income");
			accountPayableAccts=new HashSet<AccountPayableAcct>();
			accounttype_id=getaccounttypeid(accpayCategoryType,accpayDetailType);
			aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getCurrencyAmount())).toString());
			chartofaccount=getSalesChartofAccountID(aTransaction.getClientID(),accpayName,accounttype_id,aTransaction);
			aTransaction.setDebitAmount(aTransaction.getCurrencyAmount());aTransaction.setAccounts(accpayName);aTransaction.setBalAmount(tempamt.subtract(new BigDecimal(aTransaction.getCurrencyAmount())).toString());
			accountPayableAcct=new AccountPayableAcct(aTransaction,accpayCategoryType,expenseTransaction);
			accountPayableAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccount.getChart_of_account_ID()));
			accountPayableAccts.add(accountPayableAcct);
			expenseTransaction.setAccountPayableAccts(accountPayableAccts);
			for (ATransaction atrans : aTransaction.getExpenseTransactionlist()) {
				try{
					if (!atrans.getToAccount().equals("select")){
						toAccountList.add(atrans.getToAccount());
						amount=getexceptGSTAmount(aTransaction,atrans);
						toAmountList.add(amount);
						atrans.setAmount(amount);
						chartofaccountList=getchartofaccountList(aTransaction.getClientID(),atrans.getToAccount());
						if(chartofaccountList.size()>0){
							if(debitList.contains(chartofaccountList.get(0).getAccountType().getCategoryType())){
								ChartOfAccount coas=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
								coas.setBalance(new BigDecimal(chartofaccountList.get(0).getBalance()).subtract(new BigDecimal(atrans.getAmount())).toString());
								entitymanager.merge(coas);
								entitymanager.flush();
								entitymanager.clear();
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals(bankCategoryType)){
									bankAcct=new BankAcct(chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),aTransaction,atrans,expenseTransaction);
									bankAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									bankAccts.add(bankAcct);
									expenseTransaction.setBankAccts(bankAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Current Assets")){
									otherCurrentAssetsAcct=new OtherCurrentAssetsAcct(chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),aTransaction,atrans,expenseTransaction);
									otherCurrentAssetsAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									otherCurrentAssetsAccts.add(otherCurrentAssetsAcct);
									expenseTransaction.setOtherCurrentAssetsAccts(otherCurrentAssetsAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Fixed Assets")){
									fixedAssetsAcct=new FixedAssetsAcct(chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),aTransaction,atrans,expenseTransaction);
									fixedAssetsAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									fixedAssetsAccts.add(fixedAssetsAcct);
									expenseTransaction.setFixedAssetsAccts(fixedAssetsAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Assets")){
									otherAssetsAcct=new OtherAssetsAcct(chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),aTransaction,atrans,expenseTransaction);
									otherAssetsAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									otherAssetsAccts.add(otherAssetsAcct);
									expenseTransaction.setOtherAssetsAccts(otherAssetsAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Cost of Goods Sold")){
									aTransaction.setBalAmount(tempamt.subtract(new BigDecimal(atrans.getAmount())).toString());
									cogAcct=new CogAcct(chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),aTransaction,atrans,expenseTransaction);
									cogAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									cogAccts.add(cogAcct);
									expenseTransaction.setCogAccts(cogAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Expenses")){
									aTransaction.setBalAmount(tempamt.subtract(new BigDecimal(atrans.getAmount())).toString());
									expensesAcct=new ExpensesAcct(chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),aTransaction,atrans,expenseTransaction);
									expensesAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									expensesAccts.add(expensesAcct);
									expenseTransaction.setExpenseAccts(expensesAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Expenses")){
									aTransaction.setBalAmount(tempamt.subtract(new BigDecimal(atrans.getAmount())).toString());
									otherExpensesAcct=new OtherExpensesAcct(chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),aTransaction,atrans,expenseTransaction);
									otherExpensesAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									otherExpensesAccts.add(otherExpensesAcct);
									expenseTransaction.setOtherExpenseAccts(otherExpensesAccts);
								}
							}
							if(creditList.contains(chartofaccountList.get(0).getAccountType().getCategoryType())){
								ChartOfAccount coas=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
								coas.setBalance(new BigDecimal(chartofaccountList.get(0).getBalance()).add(new BigDecimal(atrans.getAmount())).toString());
								entitymanager.merge(coas);
								entitymanager.flush();
								entitymanager.clear();
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Credit Card")){
									creditCardAcct=new CreditCardAcct(chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),atrans,aTransaction,expenseTransaction);
									creditCardAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									creditCardAccts.add(creditCardAcct);
									expenseTransaction.setCreditCardAccts(creditCardAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Current Liabilities")){
									otherCurrentLibAcct=new OtherCurrentLibAcct(chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),atrans,aTransaction,expenseTransaction);
									otherCurrentLibAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									otherCurrentLibAccts.add(otherCurrentLibAcct);
									expenseTransaction.setOthercurrentlibAccts(otherCurrentLibAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Notes Payable")){
									notesPayableAcct=new NotesPayableAcct(chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),atrans,aTransaction,expenseTransaction);
									notesPayableAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									notesPayableAccts.add(notesPayableAcct);
									expenseTransaction.setNotespayableAccts(notesPayableAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Equity")){
									if(atrans.getToAccount().equals("Opening Balance Equity")){
										openbalEquityAcct=new OpenbalEquityAcct(chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),atrans,aTransaction,expenseTransaction);
										openbalEquityAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
										openbalEquityAccts.add(openbalEquityAcct);
										expenseTransaction.setOpeningBalEquityAccts(openbalEquityAccts);
									}else{
										equityAcct=new EquityAcct(chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),atrans,aTransaction,expenseTransaction);
										equityAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
										equityAccts.add(equityAcct);
										expenseTransaction.setEquityAccts(equityAccts);
									}
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Income")){
									incomeAcct=new IncomeAcct(chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),atrans,aTransaction,expenseTransaction);
									incomeAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									incomeAccts.add(incomeAcct);
									expenseTransaction.setIncomeAccts(incomeAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Income")){
									otherIncomeAcct=new OtherIncomeAcct(chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),atrans,aTransaction,expenseTransaction);
									otherIncomeAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									otherIncomeAccts.add(otherIncomeAcct);
									expenseTransaction.setOtherIncomeAccts(otherIncomeAccts);
								}
							}
						}
					}
				}catch(Exception e){
					logger.warn("----------------------Inside Exception--------------------------"+e.getMessage());
				}
			}
			if(!aTransaction.getGstType().equals(""))toAccountList.add(aTransaction.getGstType());
			if(!aTransaction.getCgstType().equals(""))toAccountList.add(aTransaction.getCgstType());
			if(!aTransaction.getSgstType().equals(""))toAccountList.add(aTransaction.getSgstType());
			creditParticular=String.join(",", toAccountList);
			getjournalentryDetails(aTransaction);
			if(!aTransaction.getCurrencyIGSTAmount().equals(""))toAmountList.add(aTransaction.getCurrencyIGSTAmount());
			if(!aTransaction.getCurrencyCGSTAmount().equals(""))toAmountList.add(aTransaction.getCurrencyCGSTAmount());
			if(!aTransaction.getCurrencySGSTAmount().equals(""))toAmountList.add(aTransaction.getCurrencySGSTAmount());
			creditAmount=String.join(",", toAmountList);
			journalEntry=new JournalEntry(accpayName,creditParticular,creditAmount,aTransaction,expenseTransaction);
			journalEntries.add(journalEntry);
			expenseTransaction.setJournalEntries(journalEntries);
			entitymanager.persist(expenseTransaction);
			entitymanager.flush();
			entitymanager.clear();
			status="Success";
		}catch(Exception e){
			logger.warn("-------------------Inside Exception----------------------------"+e.getMessage());
		}finally{
			chartofaccountList=null;bankAcct=null;bankAccts=null;otherCurrentAssetsAcct=null;otherCurrentAssetsAccts=null;otherAssetsAcct=null;
			otherAssetsAccts=null;fixedAssetsAcct=null;fixedAssetsAccts=null;cogAcct=null;cogAccts=null;expensesAcct=null;expensesAccts=null;
			otherExpensesAcct=null;otherExpensesAccts=null;creditCardAcct=null;creditCardAccts=null;otherCurrentLibAcct=null;otherCurrentLibAccts=null;
			notesPayableAcct=null;notesPayableAccts=null;equityAcct=null;equityAccts=null;incomeAcct=null;incomeAccts=null;
			otherIncomeAcct=null;otherIncomeAccts=null;openbalEquityAcct=null;openbalEquityAccts=null;
		}
		return status;
	}

	@Transactional(value="transactionManager")
	private String saveExpenseTransaction(ExpenseTransaction expenseTransaction, ATransaction aTransaction) {
		logger.info("[saveExpenseTransaction()]----------------------Inside saveExpenseTransaction() in DaoImpln Calling----------------------------");
		List<ChartOfAccount> chartofaccountList=null;BankAcct bankAcct=null;CreditCardAcct creditCardAcct=null;String status="Fail";
		HashSet<BankAcct> bankAccts=null;HashSet<CreditCardAcct> creditCardAccts=null;JournalEntry journalEntry=null;Set<JournalEntry> journalEntries=null;
		List<String> toAccountList=null;String debitParticular="";List<String> toAmountList=null;
		String amount="";String debitAmount="";
		try{
			toAccountList=new ArrayList<String>();toAmountList=new ArrayList<String>();
			bankAccts=new HashSet<BankAcct>();creditCardAccts=new HashSet<CreditCardAcct>();journalEntries=new HashSet<JournalEntry>();
			chartofaccountList=getchartofaccountList(aTransaction.getClientID(),aTransaction.getFromAccount());
			if(chartofaccountList.size()>0){
				aTransaction.setDebitAmount("");
				ChartOfAccount coas=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
				if(chartofaccountList.get(0).getAccountType().getCategoryType().equals(bankCategoryType)){
					bankAcct=new BankAcct(aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
					bankAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID()));
					bankAccts.add(bankAcct);
					expenseTransaction.setBankAccts(bankAccts);
					coas.setBalance(new BigDecimal(chartofaccountList.get(0).getBalance()).subtract(new BigDecimal(aTransaction.getCurrencyAmount())).toString());
				}
				if(aTransaction.transactionType.equalsIgnoreCase("Expense")){
					if(chartofaccountList.get(0).getAccountType().getCategoryType().equals(creditcardCategoryType)){
						creditCardAcct=new CreditCardAcct(aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
						creditCardAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID()));
						creditCardAccts.add(creditCardAcct);
						expenseTransaction.setCreditCardAccts(creditCardAccts);
						coas.setBalance(new BigDecimal(chartofaccountList.get(0).getBalance()).add(new BigDecimal(aTransaction.getCurrencyAmount())).toString());
					}
				}
				entitymanager.merge(coas);		
			}
			for (ATransaction atrans : aTransaction.getExpenseTransactionlist()) {
				try{
					if (!atrans.getToAccount().equals("select")){
						toAccountList.add(atrans.getToAccount());
						amount=getexceptGSTAmount(aTransaction,atrans);
						toAmountList.add(amount);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(!aTransaction.getGstType().equals(""))toAccountList.add(aTransaction.getGstType());
			if(!aTransaction.getCgstType().equals(""))toAccountList.add(aTransaction.getCgstType());
			if(!aTransaction.getSgstType().equals(""))toAccountList.add(aTransaction.getSgstType());
			debitParticular=String.join(",", toAccountList);
			getjournalentryDetails(aTransaction);
			if(!aTransaction.getCurrencyIGSTAmount().equals(""))toAmountList.add(aTransaction.getCurrencyIGSTAmount());
			if(!aTransaction.getCurrencyCGSTAmount().equals(""))toAmountList.add(aTransaction.getCurrencyCGSTAmount());
			if(!aTransaction.getCurrencySGSTAmount().equals(""))toAmountList.add(aTransaction.getCurrencySGSTAmount());
			debitAmount=String.join(",", toAmountList);
			journalEntry=new JournalEntry(aTransaction,aTransaction.getFromAccount(),debitParticular,expenseTransaction,debitAmount);
			journalEntries.add(journalEntry);
			expenseTransaction.setJournalEntries(journalEntries);
			getexpenseToaccountDetails(aTransaction,expenseTransaction,bankAcct,bankAccts,creditCardAcct,creditCardAccts);
			entitymanager.persist(expenseTransaction);
			entitymanager.flush();
			entitymanager.clear();
			status="Success";
		}catch(Exception e){
			logger.warn("-------------------Inside Exception----------------------------"+e.getMessage());
		}finally{
			chartofaccountList=null;bankAcct=null;creditCardAcct=null;bankAccts=null;creditCardAccts=null;
			journalEntry=null;journalEntries=null;expenseTransaction=null;
		}
		return status;
	}
	
	private String getexceptGSTAmount(ATransaction aTransaction,ATransaction atrans){
		logger.info("[getexceptGSTAmount()]----------------------Inside getexceptGSTAmount() in DaoImpln Calling----------------------------");
		String amount="";
		try{
			if(aTransaction.getTaxType().equals("Out of scope of Tax") || aTransaction.getTaxType().equals("")){
				if(aTransaction.getCurrencyType().equals(aTransaction.getBaseCurrency())){
					amount=atrans.getAmount();
				}else{
					BigDecimal currgstamt=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), atrans.getAmount());
					amount=String.valueOf(currgstamt);
				}
			}else{
				if(atrans.getTaxes().equals("Exempt IGST") || atrans.getTaxes().equals("Exempt GST") || atrans.getTaxes().equals("Out of Scope") || atrans.getTaxes().equals("")){
					if(aTransaction.getCurrencyType().equals(aTransaction.getBaseCurrency())){
						amount=atrans.getAmount();
					}else{
						BigDecimal currgstamt=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), atrans.getAmount());
						amount=String.valueOf(currgstamt);
					}
				}else{
					if(aTransaction.getCurrencyType().equals(aTransaction.getBaseCurrency())){
						amount=atrans.getGstAmount();
					}else{
						BigDecimal currgstamt=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), atrans.getGstAmount());
						amount=String.valueOf(currgstamt);
					}
				}
			}
		}catch(Exception e){
			logger.warn("-------------------Inside Exception----------------------------"+e.getMessage());
		}
		return amount;
	}
	
	@Transactional(value="transactionManager")
	private String saveExpenseBillTransaction(ExpenseTransaction expenseTransaction, ATransaction aTransaction) {
		logger.info("[saveExpenseBillTransaction()]----------------------Inside saveExpenseBillTransaction() in DaoImpln Calling----------------------------");
		String status="Fail";int accounttype_id=0;ChartOfAccount chartofaccount=null;JournalEntry journalEntry=null;CreditCardAcct creditCardAcct=null;Set<CreditCardAcct> creditCardAccts=null;
		AccountPayableAcct accountPayableAcct=null;Set<AccountPayableAcct> accountPayableAccts=null;Set<JournalEntry> journalEntries=null;String debitAmount="";
		BankAcct bankAcct=null;Set<BankAcct> bankAccts=null;List<String> toAccountList=null;String debitParticular="";List<String> toAmountList=null;
		String amount="";
		try{
			toAccountList=new ArrayList<String>();toAmountList=new ArrayList<String>();
			bankAccts=new HashSet<BankAcct>();creditCardAccts=new HashSet<CreditCardAcct>();
			journalEntries=new HashSet<JournalEntry>();
			accountPayableAccts=new HashSet<AccountPayableAcct>();
			accounttype_id=getaccounttypeid(accpayCategoryType,accpayDetailType);
			aTransaction.setTransamount(aTransaction.getCurrencyAmount());
			chartofaccount=getSalesChartofAccountID(aTransaction.getClientID(),accpayName,accounttype_id,aTransaction);
			aTransaction.setDebitAmount("0");
			accountPayableAcct=new AccountPayableAcct(accpayName,aTransaction,expenseTransaction);
			accountPayableAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccount.getChart_of_account_ID()));
			accountPayableAccts.add(accountPayableAcct);
			expenseTransaction.setAccountPayableAccts(accountPayableAccts);
			for (ATransaction atrans : aTransaction.getExpenseTransactionlist()) {
				try{
					if (!atrans.getToAccount().equals("select")){
						toAccountList.add(atrans.getToAccount());
						amount=getexceptGSTAmount(aTransaction,atrans);
						toAmountList.add(amount);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(!aTransaction.getGstType().equals(""))toAccountList.add(aTransaction.getGstType());
			if(!aTransaction.getCgstType().equals(""))toAccountList.add(aTransaction.getCgstType());
			if(!aTransaction.getSgstType().equals(""))toAccountList.add(aTransaction.getSgstType());
			debitParticular=String.join(",", toAccountList);
			getjournalentryDetails(aTransaction);
			if(!aTransaction.getCurrencyIGSTAmount().equals(""))toAmountList.add(aTransaction.getCurrencyIGSTAmount());
			if(!aTransaction.getCurrencyCGSTAmount().equals(""))toAmountList.add(aTransaction.getCurrencyCGSTAmount());
			if(!aTransaction.getCurrencySGSTAmount().equals(""))toAmountList.add(aTransaction.getCurrencySGSTAmount());
			debitAmount=String.join(",", toAmountList);
			journalEntry=new JournalEntry(aTransaction,accpayName,debitParticular,expenseTransaction,debitAmount);
			journalEntries.add(journalEntry);
			expenseTransaction.setJournalEntries(journalEntries);
			getexpenseToaccountDetails(aTransaction,expenseTransaction,bankAcct,bankAccts,creditCardAcct,creditCardAccts);
			entitymanager.persist(expenseTransaction);
			entitymanager.flush();
			entitymanager.clear();
			status="Success";
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			accounttype_id=0;chartofaccount=null;expenseTransaction=null;journalEntry=null;accountPayableAcct=null;
			accountPayableAccts=null;journalEntries=null;
		}
		return status;
	}
	
	private void getexpenseToaccountDetails(ATransaction aTransaction,ExpenseTransaction expenseTransaction,BankAcct bankAcct,Set<BankAcct> bankAccts,CreditCardAcct creditCardAcct,Set<CreditCardAcct> creditCardAccts){
		logger.info("[getexpenseToaccountDetails()]----------------------Inside getexpenseToaccountDetails() in DaoImpln Calling----------------------------");
		List<ChartOfAccount> chartofaccountList=null;OtherCurrentAssetsAcct otherCurrentAssetsAcct=null;
		OtherAssetsAcct otherAssetsAcct=null;Set<OtherAssetsAcct> otherAssetsAccts=null;FixedAssetsAcct fixedAssetsAcct=null;Set<FixedAssetsAcct> fixedAssetsAccts=null;
		CogAcct cogAcct=null;Set<CogAcct> cogAccts=null;ExpensesAcct expensesAcct=null;Set<ExpensesAcct> expensesAccts=null;Set<OtherCurrentAssetsAcct> otherCurrentAssetsAccts=null;
		OtherExpensesAcct otherExpensesAcct=null;Set<OtherExpensesAcct> otherExpensesAccts=null;
		OtherCurrentLibAcct otherCurrentLibAcct=null;Set<OtherCurrentLibAcct> otherCurrentLibAccts=null;NotesPayableAcct notesPayableAcct=null;Set<NotesPayableAcct> notesPayableAccts=null;
		EquityAcct equityAcct=null;Set<EquityAcct> equityAccts=null;IncomeAcct incomeAcct=null;Set<IncomeAcct> incomeAccts=null;
		OtherIncomeAcct otherIncomeAcct=null;Set<OtherIncomeAcct> otherIncomeAccts=null;OpenbalEquityAcct openbalEquityAcct=null;Set<OpenbalEquityAcct> openbalEquityAccts=null;
		BigDecimal tempamt=BigDecimal.valueOf(0);String amount="";
		try{
			otherCurrentAssetsAccts=new HashSet<OtherCurrentAssetsAcct>();openbalEquityAccts=new HashSet<OpenbalEquityAcct>();
			otherAssetsAccts=new HashSet<OtherAssetsAcct>();fixedAssetsAccts=new HashSet<FixedAssetsAcct>();cogAccts=new HashSet<CogAcct>();incomeAccts=new HashSet<IncomeAcct>();
			otherExpensesAccts=new HashSet<OtherExpensesAcct>();expensesAccts=new HashSet<ExpensesAcct>();otherIncomeAccts=new HashSet<OtherIncomeAcct>();
			otherCurrentLibAccts=new HashSet<OtherCurrentLibAcct>();notesPayableAccts=new HashSet<NotesPayableAcct>();equityAccts=new HashSet<EquityAcct>();
			List<String> debitList=Arrays.asList("Other Current Assets","Bank","Fixed Assets","Other Assets","Cost of Goods Sold","Expenses","Other Expenses");
			List<String> creditList=Arrays.asList("Credit Card","Other Current Liabilities","Notes Payable","Equity","Income","Other Income");
			for (ATransaction atrans : aTransaction.getExpenseTransactionlist()) {
				try{
					if (!atrans.getToAccount().equals("select")){
						chartofaccountList=getchartofaccountList(aTransaction.getClientID(),atrans.getToAccount());
						if(chartofaccountList.size()>0){
							amount=getexceptGSTAmount(aTransaction,atrans);
							atrans.setAmount(amount);
							if(debitList.contains(chartofaccountList.get(0).getAccountType().getCategoryType())){
								ChartOfAccount coas=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
								coas.setBalance(new BigDecimal(chartofaccountList.get(0).getBalance()).add(new BigDecimal(atrans.getAmount())).toString());
								entitymanager.merge(coas);
								entitymanager.flush();
								entitymanager.clear();
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals(bankCategoryType)){
									bankAcct=new BankAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									bankAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									bankAccts.add(bankAcct);
									expenseTransaction.setBankAccts(bankAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Current Assets")){
									otherCurrentAssetsAcct=new OtherCurrentAssetsAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									otherCurrentAssetsAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									otherCurrentAssetsAccts.add(otherCurrentAssetsAcct);
									expenseTransaction.setOtherCurrentAssetsAccts(otherCurrentAssetsAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Fixed Assets")){
									fixedAssetsAcct=new FixedAssetsAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									fixedAssetsAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									fixedAssetsAccts.add(fixedAssetsAcct);
									expenseTransaction.setFixedAssetsAccts(fixedAssetsAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Assets")){
									otherAssetsAcct=new OtherAssetsAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									otherAssetsAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									otherAssetsAccts.add(otherAssetsAcct);
									expenseTransaction.setOtherAssetsAccts(otherAssetsAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Cost of Goods Sold")){
									aTransaction.setBalAmount(tempamt.add(new BigDecimal(atrans.getAmount())).toString());
									cogAcct=new CogAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									cogAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									cogAccts.add(cogAcct);
									expenseTransaction.setCogAccts(cogAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Expenses")){
									System.out.println("inside if");
									aTransaction.setBalAmount(tempamt.add(new BigDecimal(atrans.getAmount())).toString());
									expensesAcct=new ExpensesAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									expensesAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									expensesAccts.add(expensesAcct);
									expenseTransaction.setExpenseAccts(expensesAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Expenses")){
									aTransaction.setBalAmount(tempamt.add(new BigDecimal(atrans.getAmount())).toString());
									otherExpensesAcct=new OtherExpensesAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									otherExpensesAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									otherExpensesAccts.add(otherExpensesAcct);
									expenseTransaction.setOtherExpenseAccts(otherExpensesAccts);
								}
							}
							if(creditList.contains(chartofaccountList.get(0).getAccountType().getCategoryType())){
								ChartOfAccount coas=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
								coas.setBalance(new BigDecimal(chartofaccountList.get(0).getBalance()).subtract(new BigDecimal(atrans.getAmount())).toString());
								entitymanager.merge(coas);
								entitymanager.flush();
								entitymanager.clear();
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Credit Card")){
									creditCardAcct=new CreditCardAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									creditCardAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									creditCardAccts.add(creditCardAcct);
									expenseTransaction.setCreditCardAccts(creditCardAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Current Liabilities")){
									otherCurrentLibAcct=new OtherCurrentLibAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									otherCurrentLibAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									otherCurrentLibAccts.add(otherCurrentLibAcct);
									expenseTransaction.setOthercurrentlibAccts(otherCurrentLibAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Notes Payable")){
									notesPayableAcct=new NotesPayableAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									notesPayableAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									notesPayableAccts.add(notesPayableAcct);
									expenseTransaction.setNotespayableAccts(notesPayableAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Equity")){
									if(atrans.getToAccount().equals("Opening Balance Equity")){
										openbalEquityAcct=new OpenbalEquityAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
										openbalEquityAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
										openbalEquityAccts.add(openbalEquityAcct);
										expenseTransaction.setOpeningBalEquityAccts(openbalEquityAccts);
									}else{
										equityAcct=new EquityAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
										equityAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
										equityAccts.add(equityAcct);
										expenseTransaction.setEquityAccts(equityAccts);
									}
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Income")){
									incomeAcct=new IncomeAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									incomeAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									incomeAccts.add(incomeAcct);
									expenseTransaction.setIncomeAccts(incomeAccts);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Income")){
									otherIncomeAcct=new OtherIncomeAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									otherIncomeAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									otherIncomeAccts.add(otherIncomeAcct);
									expenseTransaction.setOtherIncomeAccts(otherIncomeAccts);
								}
							}
						}
					}
				}catch(Exception e){
					logger.warn("-------------Inside Exception------------"+e.getMessage());
				}
			}
		}catch(Exception e){
			logger.warn("-------------Inside Exception------------"+e.getMessage());
		}finally{
			chartofaccountList=null;bankAcct=null;bankAccts=null;otherCurrentAssetsAcct=null;otherCurrentAssetsAccts=null;otherAssetsAcct=null;
			otherAssetsAccts=null;fixedAssetsAcct=null;fixedAssetsAccts=null;cogAcct=null;cogAccts=null;expensesAcct=null;expensesAccts=null;
			otherExpensesAcct=null;otherExpensesAccts=null;creditCardAcct=null;creditCardAccts=null;otherCurrentLibAcct=null;otherCurrentLibAccts=null;
			notesPayableAcct=null;notesPayableAccts=null;equityAcct=null;equityAccts=null;incomeAcct=null;incomeAccts=null;
			otherIncomeAcct=null;otherIncomeAccts=null;openbalEquityAcct=null;openbalEquityAccts=null;
		}
	}
	
	private void getEditexpenseToaccountDetails(ATransaction aTransaction,ATransaction atrans, ExpenseTransaction expenseTransaction){
		logger.info("[getEditexpenseToaccountDetails()]----------------------Inside getEditexpenseToaccountDetails() in DaoImpln Calling----------------------------");
		List<ChartOfAccount> chartofaccountList=null;OtherCurrentAssetsAcct otherCurrentAssetsAcct=null;
		OtherAssetsAcct otherAssetsAcct=null;FixedAssetsAcct fixedAssetsAcct=null;CogAcct cogAcct=null;ExpensesAcct expensesAcct=null;
		OtherExpensesAcct otherExpensesAcct=null;BankAcct bankAcct=null;CreditCardAcct creditCardAcct=null;
		OtherCurrentLibAcct otherCurrentLibAcct=null;NotesPayableAcct notesPayableAcct=null;EquityAcct equityAcct=null;IncomeAcct incomeAcct=null;
		OtherIncomeAcct otherIncomeAcct=null;OpenbalEquityAcct openbalEquityAcct=null;BigDecimal tempamt=BigDecimal.valueOf(0);String amount="";
		try{
			List<String> debitList=Arrays.asList("Other Current Assets","Bank","Fixed Assets","Other Assets","Cost of Goods Sold","Expenses","Other Expenses");
			List<String> creditList=Arrays.asList("Credit Card","Other Current Liabilities","Notes Payable","Equity","Income","Other Income");
				try{
					if (!atrans.getToAccount().equals("select")){
						chartofaccountList=getchartofaccountList(aTransaction.getClientID(),atrans.getToAccount());
						if(chartofaccountList.size()>0){
							amount=getexceptGSTAmount(aTransaction,atrans);
							atrans.setAmount(amount);
							if(debitList.contains(chartofaccountList.get(0).getAccountType().getCategoryType())){
								ChartOfAccount coas=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
								coas.setBalance(new BigDecimal(chartofaccountList.get(0).getBalance()).add(new BigDecimal(atrans.getAmount())).toString());
								entitymanager.merge(coas);
								entitymanager.flush();
								entitymanager.clear();
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals(bankCategoryType)){
									bankAcct=new BankAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									bankAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									entitymanager.persist(bankAcct);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Current Assets")){
									otherCurrentAssetsAcct=new OtherCurrentAssetsAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									otherCurrentAssetsAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									entitymanager.persist(otherCurrentAssetsAcct);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Fixed Assets")){
									fixedAssetsAcct=new FixedAssetsAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									fixedAssetsAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									entitymanager.persist(fixedAssetsAcct);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Assets")){
									otherAssetsAcct=new OtherAssetsAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									otherAssetsAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									entitymanager.persist(otherAssetsAcct);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Cost of Goods Sold")){
									aTransaction.setBalAmount(tempamt.add(new BigDecimal(atrans.getAmount())).toString());
									cogAcct=new CogAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									cogAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									entitymanager.persist(cogAcct);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Expenses")){
									System.out.println("inside if");
									aTransaction.setBalAmount(tempamt.add(new BigDecimal(atrans.getAmount())).toString());
									expensesAcct=new ExpensesAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									expensesAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									entitymanager.persist(expensesAcct);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Expenses")){
									aTransaction.setBalAmount(tempamt.add(new BigDecimal(atrans.getAmount())).toString());
									otherExpensesAcct=new OtherExpensesAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									otherExpensesAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									entitymanager.persist(otherExpensesAcct);
								}
							}
							if(creditList.contains(chartofaccountList.get(0).getAccountType().getCategoryType())){
								ChartOfAccount coas=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
								coas.setBalance(new BigDecimal(chartofaccountList.get(0).getBalance()).subtract(new BigDecimal(atrans.getAmount())).toString());
								entitymanager.merge(coas);
								entitymanager.flush();
								entitymanager.clear();
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Credit Card")){
									creditCardAcct=new CreditCardAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									creditCardAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									entitymanager.persist(creditCardAcct);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Current Liabilities")){
									otherCurrentLibAcct=new OtherCurrentLibAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									otherCurrentLibAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									entitymanager.persist(otherCurrentLibAcct);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Notes Payable")){
									notesPayableAcct=new NotesPayableAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									notesPayableAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									entitymanager.persist(notesPayableAcct);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Equity")){
									if(atrans.getToAccount().equals("Opening Balance Equity")){
										openbalEquityAcct=new OpenbalEquityAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
										openbalEquityAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
										entitymanager.persist(openbalEquityAcct);
									}else{
										equityAcct=new EquityAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
										equityAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
										entitymanager.persist(equityAcct);
									}
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Income")){
									incomeAcct=new IncomeAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									incomeAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									entitymanager.persist(incomeAcct);
								}
								if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Income")){
									otherIncomeAcct=new OtherIncomeAcct(atrans,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
									otherIncomeAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
									entitymanager.persist(otherIncomeAcct);
								}
							}
						}
					}
				}catch(Exception e){
					logger.warn("-------------Inside Exception------------"+e.getMessage());
				}
		}catch(Exception e){
			logger.warn("-------------Inside Exception------------"+e.getMessage());
		}finally{
			chartofaccountList=null;bankAcct=null;otherCurrentAssetsAcct=null;otherAssetsAcct=null;
			fixedAssetsAcct=null;cogAcct=null;expensesAcct=null;otherExpensesAcct=null;creditCardAcct=null;otherCurrentLibAcct=null;
			notesPayableAcct=null;equityAcct=null;incomeAcct=null;otherIncomeAcct=null;openbalEquityAcct=null;
		}
	}
	
	@Transactional(value="transactionManager")
	private ExpenseTransaction saveExpenseTransDetails(ATransaction aTransaction){
		logger.info("[saveExpenseTransDetails()]----------------------Inside saveExpenseTransDetails() in DaoImpln Calling----------------------------");
		ExpenseTransaction expenseTransaction=null;ExpenseAccountsPayment expenseAccountPayment=null;
		Set<ExpenseAccountsPayment> expenseAccountsPayments=null;
		try{
			expenseAccountsPayments=new HashSet<ExpenseAccountsPayment>();
			aTransaction.setApprovalStatus(draftStatus);
			if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
				aTransaction.setCurrencybeforeTaxAmount(aTransaction.getBeforeTaxAmount());
				aTransaction.setCurrencytaxAmount(aTransaction.getTaxAmount());
			}else{
				aTransaction.setCurrencybeforeTaxAmount(String.valueOf(CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), aTransaction.getBeforeTaxAmount())));
				aTransaction.setCurrencytaxAmount(String.valueOf(CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), aTransaction.getTaxAmount())));
			}
			if(aTransaction.getTaxType().equals("Out of scope of Tax") || aTransaction.getTaxType().equals("")){
				aTransaction.setCurrencytaxAmount("0");
			}
			expenseTransaction= new ExpenseTransaction(aTransaction);
			for (ATransaction atrans : aTransaction.getExpenseTransactionlist()) {
				try{
					if (!atrans.getToAccount().equals("select")) {
						expenseAccountPayment=new ExpenseAccountsPayment(atrans,aTransaction.getClientID(),expenseTransaction,activeStatus);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				expenseAccountsPayments.add(expenseAccountPayment);
			}				
			expenseTransaction.setExpenseAccountsPayments(expenseAccountsPayments);
		}catch(Exception e){
			logger.warn("-------------------Inside Exception----------------------------"+e.getMessage());
		}finally{
			expenseAccountsPayments=null;expenseAccountPayment=null;
		}
		return expenseTransaction;
	}
			
	@Override
	public List<ExpenseTransaction> expensesDataTable(String clientID) {
		logger.info("[expensesDataTable()]----------------------Inside expensesDataTable() in DaoImpln Calling----------------------------");
		Query q=null;List<ExpenseTransaction> expensdataList=null;
		try{   
			q=entitymanager.createQuery("from ExpenseTransaction where client_ID=? and status=? ORDER BY createdDate DESC");
			q.setParameter(1, clientID); 
			q.setParameter(2, activeStatus);
			expensdataList=(List<ExpenseTransaction>)q.getResultList();
		}catch(Exception e){
			logger.warn("-------------------Inside Exception----------------------------"+e.getMessage());
		}finally{
			q=null;
		}
		return expensdataList;
	}
	
	@Override
	public List<ExpenseTransaction> expanseDetailView(String clientID,ATransaction aTransaction) {
		logger.info("[expanseDetailView()]----------------------Inside expanseDetailView() in DaoImpln Calling----------------------------");
		Query q=null;List<ExpenseTransaction> expensdataList=null;
		try{			
			q=entitymanager.createQuery("from ExpenseTransaction where client_ID=? and billNumber=? and status=?");
			q.setParameter(1, clientID);	
			q.setParameter(2, aTransaction.getRefNo());
			q.setParameter(3, activeStatus);
			expensdataList=(List<ExpenseTransaction>)q.getResultList();
		}catch(Exception e){
			logger.warn("-------------------Inside Exception----------------------------"+e.getMessage());
		}finally{
			q=null;
		}
		return expensdataList;
	}
	
	@Transactional(value="transactionManager")
	@Override
	public String expenseUpdate(ATransaction aTransaction,String clientID) {
		logger.info("[expenseUpdate()]----------------------Inside expenseUpdate() in DaoImpln Calling----------------------------");
		String status="";Date date=new Date();Query q=null;List<AccountPayableAcct> accountPayableAcctList=null;
		List<JournalEntry> journalEntryList=null;String amount="";BigDecimal tempamt=BigDecimal.valueOf(0);String amounts="";
		HashSet<ExpenseAccountsPayment> expenseAccountsPayments=null;ExpenseAccountsPayment expenseaccountPayment=null;
		String debitAmount="";List<String> toAccountList=null;String debitParticular="";List<String> toAmountList=null;
		try {
			toAccountList=new ArrayList<String>();toAmountList=new ArrayList<String>();
			expenseAccountsPayments=new HashSet<ExpenseAccountsPayment>();
			List<String> debitList=Arrays.asList("Other Current Assets","Bank","Fixed Assets","Other Assets","Cost of Goods Sold","Expenses","Other Expenses");
			List<String> creditList=Arrays.asList("Credit Card","Other Current Liabilities","Notes Payable","Equity","Income","Other Income");
			if(aTransaction.getRemoveList().size()>0){
				for (int i = 0; i < aTransaction.getRemoveList().size(); i++) {
					ExpenseAccountsPayment payment=entitymanager.find(ExpenseAccountsPayment.class, aTransaction.getRemoveList().get(i));
					payment.setStatus("DeActive");
					entitymanager.merge(payment);
				}
			}
			if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
				aTransaction.setCurrencybeforeTaxAmount(aTransaction.getBeforeTaxAmount());
				aTransaction.setCurrencytaxAmount(aTransaction.getTaxAmount());
			}else{
				aTransaction.setCurrencybeforeTaxAmount(String.valueOf(CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), aTransaction.getBeforeTaxAmount())));
				aTransaction.setCurrencytaxAmount(String.valueOf(CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), aTransaction.getTaxAmount())));
			}
			if(aTransaction.getTaxType().equals("Out of scope of Tax") || aTransaction.getTaxType().equals("")){
				aTransaction.setCurrencytaxAmount("0");
			}
			ExpenseTransaction expenseTrans=entitymanager.find(ExpenseTransaction.class,aTransaction.getIndex());
			expenseTrans.setName(aTransaction.getVendorName());
			expenseTrans.setMailingAddress(aTransaction.getMailingAddress());
			expenseTrans.setStartDate(aTransaction.getBillDate());
			expenseTrans.setDueDate(aTransaction.getDueDate());
			expenseTrans.setPaymentMethod(aTransaction.getPaymentMode());
			expenseTrans.setUpdatedDate(date);
			expenseTrans.setTransactionAmount(aTransaction.getTotalAmount());
			expenseTrans.setBalanceAmount(aTransaction.getCurrencyAmount());
			expenseTrans.setCurrencyAmount(aTransaction.getCurrencyAmount());
			expenseTrans.setCurrencybeforetaxAmount(aTransaction.getCurrencybeforeTaxAmount());
			expenseTrans.setCurrencyTaxAmount(aTransaction.getCurrencytaxAmount());
			expenseTrans.setPlaceofLocation(aTransaction.getLocation());
			expenseTrans.setTaxType(aTransaction.getTaxType());
			expenseTrans.setBeforetaxAmount(aTransaction.getBeforeTaxAmount());
			expenseTrans.setTaxAmount(aTransaction.getTaxAmount());
			expenseTrans.setSubTotalAmount(aTransaction.getSubTotalAmount());
			expenseTrans.setPayterms(aTransaction.getTerms());
			expenseTrans.setCurrencyType(aTransaction.getCurrencyType());
			for (int i = 0; i < aTransaction.getExpenseTransactionlist().size(); i++) {
				if(aTransaction.getExpenseTransactionlist().get(i).getPaymentId() != 0){
					expenseaccountPayment=entitymanager.find(ExpenseAccountsPayment.class,aTransaction.getExpenseTransactionlist().get(i).getPaymentId());
					expenseaccountPayment.setTotalAmount(aTransaction.getExpenseTransactionlist().get(i).getAmount());
					expenseaccountPayment.setDescription(aTransaction.getExpenseTransactionlist().get(i).getDescription());
					expenseaccountPayment.setAccountType(aTransaction.getExpenseTransactionlist().get(i).getToAccount());
					expenseaccountPayment.setGstAmount(aTransaction.getExpenseTransactionlist().get(i).getGstAmount());
					expenseaccountPayment.setTaxes(aTransaction.getExpenseTransactionlist().get(i).getTaxes());
					expenseaccountPayment.setTaxPercentage(aTransaction.getExpenseTransactionlist().get(i).getPercentageValue());
					expenseaccountPayment.setTaxAmount(aTransaction.getExpenseTransactionlist().get(i).getPercentageAmount());
					expenseaccountPayment.setIgstType(aTransaction.getExpenseTransactionlist().get(i).getGstType());
					expenseaccountPayment.setCgstType(aTransaction.getExpenseTransactionlist().get(i).getCgstType());
					expenseaccountPayment.setSgstType(aTransaction.getExpenseTransactionlist().get(i).getSgstType());
					expenseaccountPayment.setCgstAmount(aTransaction.getExpenseTransactionlist().get(i).getCgstAmount());
					expenseaccountPayment.setSgstAmount(aTransaction.getExpenseTransactionlist().get(i).getSgstAmount());
				}
				expenseAccountsPayments.add(expenseaccountPayment);
				if(aTransaction.getExpenseTransactionlist().get(i).getPaymentId() == 0){
					try{
						if (!aTransaction.getExpenseTransactionlist().get(i).getToAccount().equals("select")) {
							ExpenseAccountsPayment expenseAccountPayment=new ExpenseAccountsPayment(aTransaction.getExpenseTransactionlist().get(i),aTransaction.getClientID(),expenseTrans,activeStatus);
							entitymanager.persist(expenseAccountPayment);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			expenseTrans.setExpenseAccountsPayments(expenseAccountsPayments);
			entitymanager.merge(expenseTrans);
			q=entitymanager.createQuery("delete from GstAcct where expense_transaction_ID=? and status=?");
			q.setParameter(1, aTransaction.getIndex());
			q.setParameter(2, "Active");
			q.executeUpdate();
			getEditExpenseGstdetails(aTransaction,expenseTrans);
			q=entitymanager.createQuery("from AccountPayableAcct where client_ID=? and expense_transaction_ID=? and status=?");
			q.setParameter(1, clientID);
			q.setParameter(2, aTransaction.getIndex());
			q.setParameter(3, activeStatus);
			accountPayableAcctList=(List<AccountPayableAcct>)q.getResultList();
			if(accountPayableAcctList.size()>0){
				AccountPayableAcct accountPayableAcct=entitymanager.find(AccountPayableAcct.class, accountPayableAcctList.get(0).getId());
				accountPayableAcct.setPayeeName(aTransaction.getVendorName());
				accountPayableAcct.setTranDate(new Date());
				accountPayableAcct.setDueDate(new Date());
				accountPayableAcct.setCreditAmount(aTransaction.getCurrencyAmount());
				accountPayableAcct.setBalance(aTransaction.getCurrencyAmount());
				entitymanager.merge(accountPayableAcct);
			}
			List<ChartOfAccount> chartofaccountList=getchartofaccountList(clientID,accpayName);
			if(new BigDecimal(aTransaction.getCurrencyAmount()).compareTo(new BigDecimal(aTransaction.getEditTransAmount()))==1){
				amount=new BigDecimal(aTransaction.getCurrencyAmount()).subtract(new BigDecimal(aTransaction.getEditTransAmount())).toString();
				ChartOfAccount chartAccount=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
				chartAccount.setBalance(new BigDecimal(chartAccount.getBalance()).add(new BigDecimal(amount)).toString());
				entitymanager.merge(chartAccount);
			}
			if(new BigDecimal(aTransaction.getCurrencyAmount()).compareTo(new BigDecimal(aTransaction.getEditTransAmount()))==-1){
				amount=new BigDecimal(aTransaction.getEditTransAmount()).subtract(new BigDecimal(aTransaction.getCurrencyAmount())).toString();
				ChartOfAccount chartAccount=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
				chartAccount.setBalance(new BigDecimal(chartAccount.getBalance()).subtract(new BigDecimal(amount)).toString());
				entitymanager.merge(chartAccount);
			}
			q=null;
			q=entitymanager.createQuery("from JournalEntry where client_ID=? and expense_transaction_ID=? and status=?");
			q.setParameter(1, clientID);
			q.setParameter(2, aTransaction.getIndex());
			q.setParameter(3, activeStatus);
			journalEntryList=(List<JournalEntry>)q.getResultList();
			if(journalEntryList.size()>0){
				for (int j = 0; j < aTransaction.getExpenseTransactionlist().size(); j++) {
					try{
						if (!aTransaction.getExpenseTransactionlist().get(j).getToAccount().equals("select")){
							toAccountList.add(aTransaction.getExpenseTransactionlist().get(j).getToAccount());
							amount=getexceptGSTAmount(aTransaction,aTransaction.getExpenseTransactionlist().get(j));
							toAmountList.add(amount);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				if(!aTransaction.getGstType().equals(""))toAccountList.add(aTransaction.getGstType());
				if(!aTransaction.getCgstType().equals(""))toAccountList.add(aTransaction.getCgstType());
				if(!aTransaction.getSgstType().equals(""))toAccountList.add(aTransaction.getSgstType());
				debitParticular=String.join(",", toAccountList);
				getjournalentryDetails(aTransaction);
				if(!aTransaction.getCurrencyIGSTAmount().equals(""))toAmountList.add(aTransaction.getCurrencyIGSTAmount());
				if(!aTransaction.getCurrencyCGSTAmount().equals(""))toAmountList.add(aTransaction.getCurrencyCGSTAmount());
				if(!aTransaction.getCurrencySGSTAmount().equals(""))toAmountList.add(aTransaction.getCurrencySGSTAmount());
				debitAmount=String.join(",", toAmountList);
				JournalEntry journal=entitymanager.find(JournalEntry.class, journalEntryList.get(0).getJournalEntryId());
				journal.setPayeeName(aTransaction.getVendorName());
				journal.setDebitAmount(debitAmount);
				journal.setDebitParticular(debitParticular);
				journal.setCreditAmount(aTransaction.getCurrencyAmount());
				entitymanager.merge(journal);
			}
			for (ATransaction atrans : aTransaction.getExpenseTransactionlist()) {
				try{
					if (!atrans.getToAccount().equals("select")){
						if(atrans.getPaymentId()!=0){
							chartofaccountList=getchartofaccountList(aTransaction.getClientID(),atrans.getToAccount());
							if(chartofaccountList.size()>0){
								amounts=getexceptGSTAmount(aTransaction,atrans);
								atrans.setAmount(amounts);
								if(debitList.contains(chartofaccountList.get(0).getAccountType().getCategoryType())){
									if(new BigDecimal(atrans.getAmount()).compareTo(new BigDecimal(atrans.getEditBeforeTaxAmount()))==1){
										amount=new BigDecimal(atrans.getAmount()).subtract(new BigDecimal(atrans.getEditBeforeTaxAmount())).toString();
										ChartOfAccount chartAccount=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
										chartAccount.setBalance(new BigDecimal(chartAccount.getBalance()).add(new BigDecimal(amount)).toString());
										entitymanager.merge(chartAccount);
									}
									if(new BigDecimal(atrans.getAmount()).compareTo(new BigDecimal(atrans.getEditBeforeTaxAmount()))==-1){
										amount=new BigDecimal(atrans.getEditBeforeTaxAmount()).subtract(new BigDecimal(atrans.getAmount())).toString();
										ChartOfAccount chartAccount=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
										chartAccount.setBalance(new BigDecimal(chartAccount.getBalance()).subtract(new BigDecimal(amount)).toString());
										entitymanager.merge(chartAccount);
									}
									if(chartofaccountList.get(0).getAccountType().getCategoryType().equals(bankCategoryType)){
										q=null;
										q=entitymanager.createQuery("from BankAcct where expense_transaction_ID=? and status=?");
										q.setParameter(1, aTransaction.getIndex());
										q.setParameter(2, activeStatus);
										List<BankAcct> bankacctList=(List<BankAcct>)q.getResultList();
										if(bankacctList.size()>0){
											for (int i = 0; i < bankacctList.size(); i++) {
												if(bankacctList.get(i).getAccountName().equals(atrans.getToAccount())){
													BankAcct bankAcct=entitymanager.find(BankAcct.class, bankacctList.get(i).getId());
													bankAcct.setDebitAmount(atrans.getAmount());bankAcct.setBalance(atrans.getAmount());
													entitymanager.merge(bankAcct);
												}
											}
												
										}
									}
									if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Current Assets")){
										q=null;
										q=entitymanager.createQuery("from OtherCurrentAssetsAcct where expense_transaction_ID=? and status=?");
										q.setParameter(1, aTransaction.getIndex());
										q.setParameter(2, activeStatus);
										List<OtherCurrentAssetsAcct> othercurrassetacctList=(List<OtherCurrentAssetsAcct>)q.getResultList();
										if(othercurrassetacctList.size()>0){
											for (int i = 0; i < othercurrassetacctList.size(); i++) {
												if(othercurrassetacctList.get(i).getAccountName().equals(atrans.getToAccount())){
													OtherCurrentAssetsAcct othercurrassetacct=entitymanager.find(OtherCurrentAssetsAcct.class, othercurrassetacctList.get(i).getId());
													othercurrassetacct.setDebitAmount(atrans.getAmount());othercurrassetacct.setBalance(atrans.getAmount());
													entitymanager.merge(othercurrassetacct);
												}
											}
												
										}
									}
									if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Fixed Assets")){
										q=null;
										q=entitymanager.createQuery("from FixedAssetsAcct where expense_transaction_ID=? and status=?");
										q.setParameter(1, aTransaction.getIndex());
										q.setParameter(2, activeStatus);
										List<FixedAssetsAcct> fixedassetacctList=(List<FixedAssetsAcct>)q.getResultList();
										if(fixedassetacctList.size()>0){
											for (int i = 0; i < fixedassetacctList.size(); i++) {
												if(fixedassetacctList.get(i).getAccountName().equals(atrans.getToAccount())){
													FixedAssetsAcct fixedassetacct=entitymanager.find(FixedAssetsAcct.class, fixedassetacctList.get(i).getId());
													fixedassetacct.setDebitAmount(atrans.getAmount());fixedassetacct.setBalance(atrans.getAmount());
													entitymanager.merge(fixedassetacct);
												}
											}
												
										}
									}
									if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Assets")){
										q=null;
										q=entitymanager.createQuery("from OtherAssetsAcct where expense_transaction_ID=? and status=?");
										q.setParameter(1, aTransaction.getIndex());
										q.setParameter(2, activeStatus);
										List<OtherAssetsAcct> otherassetacctList=(List<OtherAssetsAcct>)q.getResultList();
										if(otherassetacctList.size()>0){
											for (int i = 0; i < otherassetacctList.size(); i++) {
												if(otherassetacctList.get(i).getAccountName().equals(atrans.getToAccount())){
													OtherAssetsAcct otherAssetsAcct=entitymanager.find(OtherAssetsAcct.class, otherassetacctList.get(i).getId());
													otherAssetsAcct.setDebitAmount(atrans.getAmount());otherAssetsAcct.setBalance(atrans.getAmount());
													entitymanager.merge(otherAssetsAcct);
												}
											}
		
										}
									}
									if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Cost of Goods Sold")){
										q=null;
										q=entitymanager.createQuery("from CogAcct where expense_transaction_ID=? and status=?");
										q.setParameter(1, aTransaction.getIndex());
										q.setParameter(2, activeStatus);
										List<CogAcct> cogacctList=(List<CogAcct>)q.getResultList();
										if(cogacctList.size()>0){
											for (int i = 0; i < cogacctList.size(); i++) {
												if(cogacctList.get(i).getAccountName().equals(atrans.getToAccount())){
													CogAcct cogAcct=entitymanager.find(CogAcct.class, cogacctList.get(i).getId());
													cogAcct.setDebitAmount(atrans.getAmount());cogAcct.setBalance(atrans.getAmount());
													entitymanager.merge(cogAcct);
												}
											}
										}
									}
									if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Expenses")){
										q=null;
										q=entitymanager.createQuery("from ExpensesAcct where expense_transaction_ID=? and status=?");
										q.setParameter(1, aTransaction.getIndex());
										q.setParameter(2, activeStatus);
										List<ExpensesAcct> expensesacctList=(List<ExpensesAcct>)q.getResultList();
										if(expensesacctList.size()>0){
											for (int i = 0; i < expensesacctList.size(); i++) {
												if(expensesacctList.get(i).getAccountName().equals(atrans.getToAccount())){
													ExpensesAcct expensesAcct=entitymanager.find(ExpensesAcct.class, expensesacctList.get(i).getId());
													expensesAcct.setDebitAmount(atrans.getAmount());expensesAcct.setBalance(atrans.getAmount());
													entitymanager.merge(expensesAcct);
												}
											}
										}
									}
									if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Expenses")){
										q=null;
										q=entitymanager.createQuery("from OtherExpensesAcct where expense_transaction_ID=? and status=?");
										q.setParameter(1, aTransaction.getIndex());
										q.setParameter(2, activeStatus);
										List<OtherExpensesAcct> otherexpensesacctList=(List<OtherExpensesAcct>)q.getResultList();
										if(otherexpensesacctList.size()>0){
											for (int i = 0; i < otherexpensesacctList.size(); i++) {
												if(otherexpensesacctList.get(i).getAccountName().equals(atrans.getToAccount())){
													OtherExpensesAcct otherexpensesAcct=entitymanager.find(OtherExpensesAcct.class, otherexpensesacctList.get(i).getId());
													otherexpensesAcct.setDebitAmount(atrans.getAmount());otherexpensesAcct.setBalance(atrans.getAmount());
													entitymanager.merge(otherexpensesAcct);
												}
											}
										}
									}
								}
								if(creditList.contains(chartofaccountList.get(0).getAccountType().getCategoryType())){
									if(new BigDecimal(atrans.getAmount()).compareTo(new BigDecimal(atrans.getEditBeforeTaxAmount()))==1){
										amount=new BigDecimal(atrans.getAmount()).subtract(new BigDecimal(atrans.getEditBeforeTaxAmount())).toString();
										ChartOfAccount chartAccount=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
										chartAccount.setBalance(new BigDecimal(chartAccount.getBalance()).subtract(new BigDecimal(amount)).toString());
										entitymanager.merge(chartAccount);
									}
									if(new BigDecimal(atrans.getAmount()).compareTo(new BigDecimal(atrans.getEditBeforeTaxAmount()))==-1){
										amount=new BigDecimal(atrans.getEditBeforeTaxAmount()).subtract(new BigDecimal(atrans.getAmount())).toString();
										ChartOfAccount chartAccount=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
										chartAccount.setBalance(new BigDecimal(chartAccount.getBalance()).add(new BigDecimal(amount)).toString());
										entitymanager.merge(chartAccount);
									}
									if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Credit Card")){
										q=null;
										q=entitymanager.createQuery("from CreditCardAcct where expense_transaction_ID=? and status=?");
										q.setParameter(1, aTransaction.getIndex());
										q.setParameter(2, activeStatus);
										List<CreditCardAcct> creditCardAcctList=(List<CreditCardAcct>)q.getResultList();
										if(creditCardAcctList.size()>0){
											for (int i = 0; i < creditCardAcctList.size(); i++) {
												if(creditCardAcctList.get(i).getAccountName().equals(atrans.getToAccount())){
													CreditCardAcct creditCardAcct=entitymanager.find(CreditCardAcct.class, creditCardAcctList.get(i).getId());
													creditCardAcct.setDebitAmount(atrans.getAmount());creditCardAcct.setBalance(tempamt.subtract(new BigDecimal(atrans.getAmount())).toString());
													entitymanager.merge(creditCardAcct);
												}
											}
										}
									}
									if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Current Liabilities")){
										q=null;
										q=entitymanager.createQuery("from OtherCurrentLibAcct where expense_transaction_ID=? and status=?");
										q.setParameter(1, aTransaction.getIndex());
										q.setParameter(2, activeStatus);
										List<OtherCurrentLibAcct> otherCurrentLibAcctList=(List<OtherCurrentLibAcct>)q.getResultList();
										if(otherCurrentLibAcctList.size()>0){
											for (int i = 0; i < otherCurrentLibAcctList.size(); i++) {
												if(otherCurrentLibAcctList.get(i).getAccountName().equals(atrans.getToAccount())){
													OtherCurrentLibAcct otherCurrentLibAcct=entitymanager.find(OtherCurrentLibAcct.class, otherCurrentLibAcctList.get(i).getId());
													otherCurrentLibAcct.setDebitAmount(atrans.getAmount());otherCurrentLibAcct.setBalance(tempamt.subtract(new BigDecimal(atrans.getAmount())).toString());
													entitymanager.merge(otherCurrentLibAcct);
												}
											}
												
										}
									}
									if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Notes Payable")){
										q=null;
										q=entitymanager.createQuery("from NotesPayableAcct where expense_transaction_ID=? and status=?");
										q.setParameter(1, aTransaction.getIndex());
										q.setParameter(2, activeStatus);
										List<NotesPayableAcct> notesPayableAcctList=(List<NotesPayableAcct>)q.getResultList();
										if(notesPayableAcctList.size()>0){
											for (int i = 0; i < notesPayableAcctList.size(); i++) {
												if(notesPayableAcctList.get(i).getAccountName().equals(atrans.getToAccount())){
													NotesPayableAcct notesPayableAcct=entitymanager.find(NotesPayableAcct.class, notesPayableAcctList.get(i).getId());
													notesPayableAcct.setDebitAmount(atrans.getAmount());notesPayableAcct.setBalance(tempamt.subtract(new BigDecimal(atrans.getAmount())).toString());
													entitymanager.merge(notesPayableAcct);
												}
											}
												
										}
									}
									if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Equity")){
										if(atrans.getToAccount().equals("Opening Balance Equity")){
											q=null;
											q=entitymanager.createQuery("from OpenbalEquityAcct where expense_transaction_ID=? and status=?");
											q.setParameter(1, aTransaction.getIndex());
											q.setParameter(2, activeStatus);
											List<OpenbalEquityAcct> openbalEquityAcctList=(List<OpenbalEquityAcct>)q.getResultList();
											if(openbalEquityAcctList.size()>0){
												for (int i = 0; i < openbalEquityAcctList.size(); i++) {
													if(openbalEquityAcctList.get(i).getAccountName().equals(atrans.getToAccount())){
														OpenbalEquityAcct openbalEquityAcct=entitymanager.find(OpenbalEquityAcct.class, openbalEquityAcctList.get(i).getId());
														openbalEquityAcct.setDebitAmount(atrans.getAmount());openbalEquityAcct.setBalance(tempamt.subtract(new BigDecimal(atrans.getAmount())).toString());
														entitymanager.merge(openbalEquityAcct);
													}
												}
													
											}
										}else{
											q=null;
											q=entitymanager.createQuery("from EquityAcct where expense_transaction_ID=? and status=?");
											q.setParameter(1, aTransaction.getIndex());
											q.setParameter(2, activeStatus);
											List<EquityAcct> equityAcctList=(List<EquityAcct>)q.getResultList();
											if(equityAcctList.size()>0){
												for (int i = 0; i < equityAcctList.size(); i++) {
													if(equityAcctList.get(i).getAccountName().equals(atrans.getToAccount())){
														EquityAcct equityAcct=entitymanager.find(EquityAcct.class, equityAcctList.get(i).getId());
														equityAcct.setDebitAmount(atrans.getAmount());equityAcct.setBalance(tempamt.subtract(new BigDecimal(atrans.getAmount())).toString());
														entitymanager.merge(equityAcct);
													}
												}
													
											}
										}
									}
									if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Income")){
										q=null;
										q=entitymanager.createQuery("from IncomeAcct where expense_transaction_ID=? and status=?");
										q.setParameter(1, aTransaction.getIndex());
										q.setParameter(2, activeStatus);
										List<IncomeAcct> incomeAcctList=(List<IncomeAcct>)q.getResultList();
										if(incomeAcctList.size()>0){
											for (int i = 0; i < incomeAcctList.size(); i++) {
												if(incomeAcctList.get(i).getAccountName().equals(atrans.getToAccount())){
													IncomeAcct incomeAcct=entitymanager.find(IncomeAcct.class, incomeAcctList.get(i).getId());
													incomeAcct.setDebitAmount(atrans.getAmount());incomeAcct.setBalance(tempamt.subtract(new BigDecimal(atrans.getAmount())).toString());
													entitymanager.merge(incomeAcct);
												}
											}
										}
									}
									if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Other Income")){
										q=null;
										q=entitymanager.createQuery("from OtherIncomeAcct where expense_transaction_ID=? and status=?");
										q.setParameter(1, aTransaction.getIndex());
										q.setParameter(2, activeStatus);
										List<OtherIncomeAcct> otherIncomeAcctList=(List<OtherIncomeAcct>)q.getResultList();
										if(otherIncomeAcctList.size()>0){
											for (int i = 0; i < otherIncomeAcctList.size(); i++) {
												if(otherIncomeAcctList.get(i).getAccountName().equals(atrans.getToAccount())){
													OtherIncomeAcct otherIncomeAcct=entitymanager.find(OtherIncomeAcct.class, otherIncomeAcctList.get(i).getId());
													otherIncomeAcct.setDebitAmount(atrans.getAmount());otherIncomeAcct.setBalance(tempamt.subtract(new BigDecimal(atrans.getAmount())).toString());
													entitymanager.merge(otherIncomeAcct);
												}
											}
										}
									}
								}
							}
						}
						if(atrans.getPaymentId()==0){
							getEditexpenseToaccountDetails(aTransaction,atrans,expenseTrans);
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			status="Success";
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("---------------------Inside Exception------------------------------"+e.getMessage());
		}
		return status;
	}
	
	@Override
	public List<ExpenseAccountsPayment> expensepaymentDetail(int expense_transaction_ID) {
		logger.info("[expanseDetailView()]----------------------Inside expanseDetailView() in DaoImpln Calling----------------------------");
		Query q=null;List<ExpenseAccountsPayment> expenseaccountpaymentList=null;
		try{
			q=entitymanager.createQuery("from ExpenseAccountsPayment where expense_transaction_ID=? and status=?");
			q.setParameter(1, expense_transaction_ID);
			q.setParameter(2, activeStatus);
			expenseaccountpaymentList=(List<ExpenseAccountsPayment>)q.getResultList();
		}catch(Exception e){
			logger.warn("-------------------Inside Exception----------------------------"+e.getMessage());
		}finally{
			q=null;
		}
		return expenseaccountpaymentList;
	}
	
	
	@Override
	public List<ExpenseAccountsPayment> expenseaccountpaymentDetail(String clientID) {
		logger.info("[expenseaccountpaymentDetail()]----------------------Inside expenseaccountpaymentDetail() in DaoImpln Calling----------------------------");
		Query q=null;List<ExpenseAccountsPayment> expenseaccountpaymentList=null;
		try{
			q=entitymanager.createQuery("from ExpenseAccountsPayment where client_ID=? and status=?");
			q.setParameter(1, clientID);
			q.setParameter(2, activeStatus);
			expenseaccountpaymentList=(List<ExpenseAccountsPayment>)q.getResultList();
		}catch(Exception e){
			logger.warn("-------------------Inside Exception----------------------------"+e.getMessage());
		}finally{
			q=null;
		}
		return expenseaccountpaymentList;
	}

	@Override
	public List<ChartOfAccount> accountlist(String clientID) {
		logger.info("[accountlist()]---------------------Inside accountlist() in DaoImpln Calling-----------------------");
		Query q=null;List<ChartOfAccount> accDeposit=null;
		try{			
			q=entitymanager.createQuery("from ChartOfAccount where client_ID=? and status=?");
			q.setParameter(1, clientID);
			q.setParameter(2, activeStatus);
			accDeposit=(List<ChartOfAccount>)q.getResultList();
		}catch(Exception e){
			logger.warn("-------------Inside Exception-------------------"+e.getMessage());
		}finally{
			q=null;
		}
		return accDeposit;
	}
					
	@Override
	public List<SalesTransaction> getsalestransactionNumber(String clientID,String transactionType) {
		logger.info("[getsalestransactionNumber()]--------------Inside getsalestransactionNumber() in DaoImpln Calling-----------------");
		List<SalesTransaction> salesTransactionList=null;Query q=null;
		try{
			q=entitymanager.createQuery("from SalesTransaction where client_ID=? and transactionType=?");
			q.setParameter(1, clientID);
			q.setParameter(2, transactionType);
			salesTransactionList=(List<SalesTransaction>)q.getResultList();
		}catch(Exception e){
			logger.warn("------------Inside Exception-----------------"+e.getMessage());
		}finally{
			q=null;
		}
		return salesTransactionList;
	}
	
	public int getaccounttypeid(String categoryType,String detailType){
		logger.info("[getaccounttypeid()]-------------------Inside getaccounttypeid() in DaoImpln Calling-------------------------");
		int accounttype_id=0;Query q=null;
		try{
			q=entitymanager.createQuery("from AccountType where categoryType=? and detailType=? and status=?");
			q.setParameter(1, categoryType);
			q.setParameter(2, detailType);
			q.setParameter(3, activeStatus);
			List<AccountType> accounttypeList=(List<AccountType>)q.getResultList();
			if(accounttypeList.size()>0)accounttype_id=accounttypeList.get(0).getAccount_type_ID();
		}catch(Exception e){
			logger.warn("------------Inside Exception-------------"+e.getMessage());
		}finally{
			q=null;
		}
		return accounttype_id;
	}
	
	public List<ChartOfAccount> getchartofaccountList(String clientID,String account){
		logger.info("[getchartofaccountList()]----------------------Inside getchartofaccountList() in DaoImpln Calling-------------------");
		List<ChartOfAccount> chartofaccountList=null;Query q=null;
		try{
			q=entitymanager.createQuery("from ChartOfAccount where client_ID=? and accountName=? and status=?");
			q.setParameter(1, clientID);
			q.setParameter(2, account);
			q.setParameter(3, activeStatus);
			chartofaccountList=(List<ChartOfAccount>)q.getResultList();
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("-------------Inside Exception-------------------"+e.getMessage());
		}finally{
			q=null;
		}
		return chartofaccountList;
	}
	
	@Transactional(value="transactionManager")
	public ChartOfAccount getSalesChartofAccountID(String clientID, String account, int accounttype_id,ATransaction aTransaction) {
		 logger.info("[getAccountID()]------------Inside getAccountID in DaoImpln Calling--------------------");
		 Query q=null;ChartOfAccount accountID = null;ChartOfAccount chartofaccount=null;
		 List<ChartOfAccount> accountlist=null;
		 try{  
			 accountID=new ChartOfAccount();
			 accountlist=getchartofaccountList(clientID,account);
			 if(accountlist.size()>0){
				accountID.setChart_of_account_ID(accountlist.get(0).getChart_of_account_ID());
				ChartOfAccount coas=entitymanager.find(ChartOfAccount.class, accountlist.get(0).getChart_of_account_ID());
				coas.setBalance(new BigDecimal(accountlist.get(0).getBalance()).add(new BigDecimal(aTransaction.getTransamount())).toString());
				entitymanager.merge(coas);
				entitymanager.flush();
				entitymanager.clear();
			 } 
			 if(accountlist.size()==0){
			 	chartofaccount=new ChartOfAccount(); 
			 	chartofaccount.setAccountName(account);
				chartofaccount.setAccountNameDescription("");
				chartofaccount.setBalance(aTransaction.getTransamount());
				chartofaccount.setClient_ID(clientID);
				chartofaccount.setTrandate(aTransaction.getBillDate());
				chartofaccount.setAccountType(entitymanager.find(AccountType.class, accounttype_id));
				if(chartofaccount.getAccountType().getOpeningBalance().equals(noOpeningBalance)){
					chartofaccount.setOpeningBalance(nonOpeningBalance);
				}
				chartofaccount.setStatus(activeStatus);
				chartofaccount.setCreatedDate(now);
				chartofaccount.setUpdatedDate(null);
				chartofaccount.setCurrency(aTransaction.getBaseCurrency());
				chartofaccount.setApprovalStatus(draftStatus);
				entitymanager.persist(chartofaccount);
				entitymanager.flush();
				entitymanager.clear();
				accountlist=getchartofaccountList(clientID);
				if(accountlist.size()>0){
					accountID.setChart_of_account_ID(accountlist.get(accountlist.size()-1).getChart_of_account_ID());
				}
			 }
		 }catch(Exception e){
			 logger.warn("----------------Inside Exception--------------"+e.getMessage());
		 }
		 finally{
			 q=null;chartofaccount=null;accountlist=null;
		 }
		 return accountID;
	}
	
	@Override
	public List<ChartOfAccount> getchartofaccountList(String clientID){
		Query q=null;List<ChartOfAccount> accountlist=null;
		try{
			q=entitymanager.createQuery("from ChartOfAccount where client_ID=?");
			q.setParameter(1, clientID);
			accountlist=(List<ChartOfAccount>)q.getResultList();
		}catch(Exception e){
			 logger.warn("----------------Inside Exception--------------"+e.getMessage());
		}finally{
			q=null;
		}
		return accountlist;
	}
	
	public void getGstdetails(ATransaction aTransaction, List<ATransaction> productdetails,SalesTransaction salestransaction){
		logger.info("[getGstdetails()-----------------Inside getGstdetails() in DaoImpln Calling------------------]");
		GstAcct gstAcct2=null;Set<GstAcct> gstaccts=null;int accounttype_id=0;BigDecimal tempamt=BigDecimal.valueOf(0);
		ChartOfAccount coas=null;ChartOfAccount coas1=null;ChartOfAccount coas2=null;GstAcct gstAcct=null;GstAcct gstAcct1=null;
		try{
			gstaccts=new HashSet<GstAcct>();
			for (ATransaction atrans : productdetails) {
	        	try{
	        		if(!atrans.getProductName().equalsIgnoreCase("select")){
		        		if(!aTransaction.getTaxType().equals("Out of scope of Tax") || !aTransaction.getTaxType().equals("")){
		        			accounttype_id=getaccounttypeid(GSTcategorytype,GSTdetailtype);
		        			aTransaction.setCategoryType(GSTcategorytype);aTransaction.setDetailName(GSTdetailtype);
		        			if(gstList.contains(atrans.getTaxes())){
		        				if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
		        					aTransaction.setTransamount(atrans.getCgstAmount());
		        				}else{
		        					BigDecimal cgstCurrency=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),atrans.getCgstAmount());
		        					aTransaction.setTransamount(String.valueOf(cgstCurrency));
		        				}
		        				if(aTransaction.getTransactionType().equalsIgnoreCase("Credit Memo")){
        	        				aTransaction.setDebitAmount(aTransaction.getTransamount());aTransaction.setCreditAmount("");
        	        				aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getTransamount())).toString());
        	        			}else{
        	        				aTransaction.setDebitAmount("");aTransaction.setCreditAmount(aTransaction.getTransamount());
        	        				aTransaction.setTransamount(aTransaction.getTransamount());
        	        			}
		        				coas1=getSalesChartofAccountID(aTransaction.getClientID(),outputCGSTName,accounttype_id,aTransaction);
        	        			aTransaction.setAccounts(outputCGSTName);aTransaction.setBalance(aTransaction.getTransamount());
        	        			gstAcct1=new GstAcct(salestransaction,aTransaction,coas1);
        	        			if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
		        					aTransaction.setTransamount(atrans.getSgstAmount());
		        				}else{
		        					BigDecimal sgstCurrency=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),atrans.getSgstAmount());
		        					aTransaction.setTransamount(String.valueOf(sgstCurrency));
		        				}
        	        			if(aTransaction.getTransactionType().equalsIgnoreCase("Credit Memo")){
        	        				aTransaction.setDebitAmount(aTransaction.getTransamount());aTransaction.setCreditAmount("");
        	        				aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getTransamount())).toString());
        	        			}else{
        	        				aTransaction.setDebitAmount("");aTransaction.setCreditAmount(aTransaction.getTransamount());
        	        				aTransaction.setTransamount(aTransaction.getTransamount());
        	        			}
        	        			coas2=getSalesChartofAccountID(aTransaction.getClientID(),outputSGSTName,accounttype_id,aTransaction);
        	        			aTransaction.setAccounts(outputSGSTName);aTransaction.setBalance(aTransaction.getTransamount());
        	        			gstAcct2=new GstAcct(salestransaction,aTransaction,coas2);
		        			}else if(igstList.contains(atrans.getTaxes())){
		        				if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
		        					aTransaction.setTransamount(atrans.getPercentageAmount());
		        				}else{
		        					BigDecimal igstCurrency=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),atrans.getPercentageAmount());
		        					aTransaction.setTransamount(String.valueOf(igstCurrency));
		        				}
		        				if(aTransaction.getTransactionType().equalsIgnoreCase("Credit Memo")){
        	        				aTransaction.setDebitAmount(aTransaction.getTransamount());aTransaction.setCreditAmount("");
        	        				aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getTransamount())).toString());
        	        			}else{
        	        				aTransaction.setDebitAmount("");aTransaction.setCreditAmount(aTransaction.getTransamount());
        	        				aTransaction.setTransamount(aTransaction.getTransamount());
        	        			}
        	        			coas=getSalesChartofAccountID(aTransaction.getClientID(),outputIGSTName,accounttype_id,aTransaction);
        	        			aTransaction.setAccounts(outputIGSTName);aTransaction.setBalance(aTransaction.getTransamount());
        	        			gstAcct=new GstAcct(salestransaction,aTransaction,coas);
		        			}
		        			
		        		}
		        	}
	        	}catch(NullPointerException e){
	        		e.printStackTrace();
	        	}
				gstaccts.add(gstAcct1);gstaccts.add(gstAcct2);gstaccts.add(gstAcct);
	        }
			salestransaction.setGstAccts(gstaccts);
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("-------------------------Inside Exception------------------"+e.getMessage());
		}
	}
	
	@Transactional(value="transactionManager")
	public SalesTransaction getsavesalestransactiondetails(ATransaction aTransaction, List<ATransaction> productdetails){
		logger.info("[getsavesalestransactiondetails()-----------------Inside getsavesalestransactiondetails() in DaoImpln Calling------------------]");
		SalesTransaction salestransaction=null;SalesAccountsPayment salesaccountPayment=null;
		Set<SalesAccountsPayment> salespayments=null;String status="Fail";BigDecimal tempamt=BigDecimal.valueOf(0);
		try{
			if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
				aTransaction.setCurrencybeforeTaxAmount(aTransaction.getBeforeTaxAmount());
				aTransaction.setCurrencytaxAmount(aTransaction.getTaxAmount());
			}else{
				aTransaction.setCurrencybeforeTaxAmount(String.valueOf(CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), aTransaction.getBeforeTaxAmount())));
				aTransaction.setCurrencytaxAmount(String.valueOf(CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), aTransaction.getTaxAmount())));
			}
			if(aTransaction.getTaxType().equals("Out of scope of Tax") || aTransaction.getTaxType().equals("")){
				aTransaction.setCurrencytaxAmount("0");
			}
			salestransaction=new SalesTransaction(aTransaction); 
			salespayments = new HashSet<SalesAccountsPayment>();
			for (ATransaction atrans : productdetails) {
	        	try{
	        		if(!atrans.getProductName().equalsIgnoreCase("select")){
		        		salesaccountPayment=new SalesAccountsPayment(atrans,salestransaction,activeStatus);
		        		
		        	}
	        	}catch(NullPointerException e){
	        		e.printStackTrace();
	        	}
				salespayments.add(salesaccountPayment);
	        }
			salestransaction.setSalesaccountpayments(salespayments);
		}catch(Exception e){
			logger.warn("-------------------------Inside Exception------------------"+e.getMessage());
		}finally{
			salesaccountPayment=null;salespayments=null;
		}
		return salestransaction;
	}
	
	@Transactional(value="transactionManager")
	public String saveSalesInvoiceTransaction(String clientID,ATransaction aTransaction,JournalEntry journalEntry,SalesTransaction salesTransaction) {
		logger.info("[saveSalesInvoiceTransaction()]--------------------------Inside saveSalesInvoiceTransaction() in DaoImpln Calling--------------------");
		Query q=null;int accounttype_id=0;ChartOfAccount chartofaccount=new ChartOfAccount();String status="Fail";
		ChartOfAccount chartofaccount1=new ChartOfAccount();AccountReceivableAcct accRecAcct=null;IncomeAcct incomeAcct=null;
		try{
			aTransaction.setTransamount(aTransaction.getCurrencyAmount());
			accounttype_id=getaccounttypeid(accrecCategoryType,accrecDetailType);
			chartofaccount=getSalesChartofAccountID(clientID,accrecName,accounttype_id,aTransaction);
			aTransaction.setStatus(activeStatus);aTransaction.setCreditAmount("0");
			accRecAcct=new AccountReceivableAcct(aTransaction,clientID,accrecName,accrecCategoryType,accrecDetailType,salesTransaction);
			accRecAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, chartofaccount.getChart_of_account_ID()));				
			accounttype_id=0;
			aTransaction.setTransamount(aTransaction.getCurrencybeforeTaxAmount());
			accounttype_id=getaccounttypeid(serviceCategoryType,serviceDetailType);
			chartofaccount1=getSalesChartofAccountID(clientID,serviceName,accounttype_id,aTransaction);
			aTransaction.setBalAmount(aTransaction.getTransamount());
			incomeAcct=new IncomeAcct(aTransaction,clientID,serviceName,serviceCategoryType,serviceDetailType,salesTransaction);
			incomeAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, chartofaccount1.getChart_of_account_ID()));
			entitymanager.persist(accRecAcct);
			entitymanager.persist(incomeAcct);
			entitymanager.persist(journalEntry);
			entitymanager.flush();
			entitymanager.clear();
			status="Success";
		}catch(Exception e){
			logger.warn("--------------Inside Exception------------------"+e.getMessage());
		}
		finally{
			q=null;accounttype_id=0;chartofaccount=null;chartofaccount1=null;accRecAcct=null;incomeAcct=null;
		}
		return status;
	}
	
	@Transactional(value = "transactionManager")
	public String qucikSalesConform1(PurchaseOrder purchaseOrder)throws DemoException {
		logger.info("[qucikSalesConform1()]--------------------------Inside qucikSalesConform1() in DemoAccountsDao Impln Calling---------------------------------");
		Query q = null;Date date=null;BigDecimal currAmnt=BigDecimal.valueOf(0);
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		List<I0021> salesOrder = null;ATransaction aTransaction=null;SalesTransaction salestransaction=null;
		SalesAccountsPayment salesAccountsPayment=null;HashSet<SalesAccountsPayment> salespayments=null;
		try {
			aTransaction=new ATransaction();date=new Date();salespayments=new HashSet<SalesAccountsPayment>();
			q = entitymanager.createQuery("from I0021 where natureOfBusiness=? and client_ID=?");
			q.setParameter(1, "Quick sales");
			q.setParameter(2, clientID);
			salesOrder = q.getResultList();
			String salesOrderNo = "";
			if (salesOrder.size() <= 0) {
				salesOrderNo = GenerateEmployee.getEmployeeReference(1);
			} else {
				salesOrderNo = GenerateEmployee.getEmployeeReference(salesOrder.size() + 1);
			}
			purchaseOrder.setSalesIdReference(salesOrderNo);
			entitymanager.clear();
			I0021 sales1 = new I0021();
			sales1.setDiscamnt(purchaseOrder.getDiscAmnt());
			sales1.setDisctype(purchaseOrder.getDiscType());
			sales1.setShippingCharge("0");
			sales1.setTotalNumberOfCount(purchaseOrder.getTotalnumberofcount());
			sales1.setCrossTotal(purchaseOrder.getTotalPrice());
			sales1.setSalesOrderDate(Calendar.getInstance().getTime());
			sales1.setDeliveryDate(Calendar.getInstance().getTime());
			sales1.setStatus("Quick sales");
			sales1.setQuickStatus("delivered");
			sales1.setCustomerName("Quick sales");
			sales1.setStatus2("paid");
			sales1.setPaymentType(purchaseOrder.getPaymentType());
			sales1.setBankname(purchaseOrder.getBankname());
			sales1.setAccno(purchaseOrder.getAccno());
			sales1.setCardno(purchaseOrder.getCardno());
			sales1.setChequeno(purchaseOrder.getChequeno());
			sales1.setChequedate(purchaseOrder.getChequedate());
			sales1.setSalesOrderNumber(salesOrderNo);
			sales1.setNatureOfBusiness("Quick sales");
			sales1.setCurrency(purchaseOrder.getCurrency());
			sales1.setApprovalStatus("draft");
			sales1.setCreatedDate(date);
			sales1.setUserID(entitymanager.find(UserCreate.class, Integer.parseInt(userID)));
			purchaseOrder.setSalesIdReference(salesOrderNo);
			sales1.setClient_ID(clientID);
			if(purchaseOrder.getCurrency().equalsIgnoreCase(purchaseOrder.getBaseCurrency())){
				currAmnt=new BigDecimal(purchaseOrder.getTotalPrice());
			}else{
				currAmnt=CurrencyConverter.findExchangeRateAndConvert(purchaseOrder.getCurrency(), purchaseOrder.getBaseCurrency(), purchaseOrder.getTotalPrice());
			}
			sales1.setCurrencyAmount(String.valueOf(currAmnt));
			entitymanager.persist(sales1);
			q = entitymanager.createQuery("from I0021 where salesOrderNumber=? and client_ID=?");
			q.setParameter(1, sales1.getSalesOrderNumber());
			q.setParameter(2, clientID);
			List<I0021> resl = (List<I0021>) q.getResultList();
			if (resl.size() > 0) {
				purchaseOrder.setSalesId(resl.get(0).getSales_ID());
				purchaseOrder.setCurrencyAmount(resl.get(0).getCurrencyAmount());
			}
			purchaseOrder.setTransactionType("Quick Sales");
			purchaseOrder.setPaymentStatus("Paid");
			purchaseOrder.setStatus2("");purchaseOrder.setPayableAmount(purchaseOrder.getCurrencyAmount());
			purchaseOrder.setSalesIdReference(sales1.getSalesOrderNumber());
			purchaseOrder.setOrderDate(new Date());purchaseOrder.setDueDate(new Date());
			salestransaction=new SalesTransaction(clientID,purchaseOrder); 
			for(PurchaseOrder purchase : purchaseOrder.getResulfinal()){ 
				if(!purchase.getProductName().equalsIgnoreCase("")){
					salesAccountsPayment=new SalesAccountsPayment(clientID,purchase,salestransaction);
				}
				salespayments.add(salesAccountsPayment);
			}
			salestransaction.setSalesaccountpayments(salespayments); 				
			entitymanager.persist(salestransaction); 
			entitymanager.flush();
			entitymanager.clear();
			aTransaction.setCurrencybeforeTaxAmount(purchaseOrder.getCurrencyAmount());
			aTransaction.setTransactionType(purchaseOrder.getTransactionType());
			aTransaction.setTransactionNo(purchaseOrder.getSalesIdReference());
			aTransaction.setStatus(activeStatus);
			aTransaction.setAccountType(purchaseOrder.getAccounttype());
			aTransaction.setCurrencyAmount(purchaseOrder.getCurrencyAmount());
			saveSalesReceiptTransaction(aTransaction,clientID,salestransaction);
		} catch (Exception e) {
			logger.error("Inside Exception", e.getMessage());
		} finally {
			q=null;salespayments=null;salesAccountsPayment=null;
		}
		return "";
	}
	
	@Transactional(value="transactionManager")
	private String saveSalesReceiptTransaction(ATransaction aTransaction,String clientID,SalesTransaction salestransaction) {
		logger.info("[saveSalesReceiptTransaction()]---------------------Inside saveSalesReceiptTransaction() in DaoImpln Calling----------------------");
		int accounttype_id=0;ChartOfAccount chartofaccount1=null;IncomeAcct incomeAcct=null;String status="Fail";
		List<ChartOfAccount> chartofaccountList=null;BankAcct  bankAcct=null;OtherCurrentAssetsAcct othercurrentassetAcct=null;
		JournalEntry journalEntry=null;String creditParticular="";String creditAmount="";List<String> creditParticularList=null;
		List<String> creditAmountList=null;
		try{
			creditParticularList=new ArrayList<String>();creditAmountList=new ArrayList<String>();
			chartofaccount1=new ChartOfAccount();
			accounttype_id=getaccounttypeid(serviceCategoryType,serviceDetailType);
			aTransaction.setTransamount(aTransaction.getCurrencybeforeTaxAmount());
			chartofaccount1=getSalesChartofAccountID(clientID,serviceName,accounttype_id,aTransaction);
			aTransaction.setTransamount(aTransaction.getCurrencybeforeTaxAmount());
			aTransaction.setBalAmount(aTransaction.getCurrencybeforeTaxAmount());
			incomeAcct=new IncomeAcct(aTransaction,clientID,serviceName,serviceDetailType,serviceDetailType,salestransaction);
			incomeAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, chartofaccount1.getChart_of_account_ID()));
			chartofaccountList=getchartofaccountList(clientID,aTransaction.getAccountType());
			if(chartofaccountList.size()>0){
				aTransaction.setTransamount(aTransaction.getCurrencyAmount());
				if(chartofaccountList.get(0).getAccountType().getCategoryType().equals(bankCategoryType)){
					bankAcct=new BankAcct(clientID,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType());
					bankAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID()));
					entitymanager.persist(bankAcct);
				}
				if(chartofaccountList.get(0).getAccountType().getCategoryType().equals(othercurrassetsCateType)){
					othercurrentassetAcct=new OtherCurrentAssetsAcct(clientID,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType());
					othercurrentassetAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID()));
					entitymanager.persist(othercurrentassetAcct);
				}
				ChartOfAccount coas=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
				coas.setBalance(new BigDecimal(chartofaccountList.get(0).getBalance()).add(new BigDecimal(aTransaction.getTransamount())).toString());
				entitymanager.merge(coas);		
			}
			creditParticularList.add(serviceName);
			if(aTransaction.getTransactionType().equalsIgnoreCase("Sales Receipt")){
				if(!aTransaction.getGstType().equals(""))creditParticularList.add(aTransaction.getGstType());
				if(!aTransaction.getCgstType().equals(""))creditParticularList.add(aTransaction.getCgstType());
				if(!aTransaction.getSgstType().equals(""))creditParticularList.add(aTransaction.getSgstType());
			}
			creditParticular=String.join(",", creditParticularList);
			if(aTransaction.getTransactionType().equalsIgnoreCase("Sales Receipt")){
				getjournalentryDetails(aTransaction);
			}
			creditAmountList.add(aTransaction.getCurrencybeforeTaxAmount());
			if(aTransaction.getTransactionType().equalsIgnoreCase("Sales Receipt")){
				if(!aTransaction.getCurrencyIGSTAmount().equals(""))creditAmountList.add(aTransaction.getCurrencyIGSTAmount());
				if(!aTransaction.getCurrencyCGSTAmount().equals(""))creditAmountList.add(aTransaction.getCurrencyCGSTAmount());
				if(!aTransaction.getCurrencySGSTAmount().equals(""))creditAmountList.add(aTransaction.getCurrencySGSTAmount());
			}
			creditAmount=String.join(",", creditAmountList);
			journalEntry=new JournalEntry(aTransaction,clientID,aTransaction.getAccountType(),creditParticular,creditAmount,salestransaction);
			entitymanager.persist(journalEntry);
			entitymanager.persist(incomeAcct);
			entitymanager.flush();
			entitymanager.clear();
			status="Success";
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("------------------Inside Exception---------------------"+e.getMessage());
		}finally{
			othercurrentassetAcct=null;accounttype_id=0;chartofaccount1=null;incomeAcct=null;
			chartofaccountList=null;bankAcct=null;othercurrentassetAcct=null;journalEntry=null;
		}
		return status;
	}
	
	public void getjournalentryDetails(ATransaction aTransaction){
		logger.info("[getjournalentryDetails()]--------------------------Inside getjournalentryDetails() in DaoImpln Calling--------------------");
		try{
			if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
				if(!aTransaction.getCurrencyIGSTAmount().equals(""))aTransaction.setCurrencyIGSTAmount(aTransaction.getCurrencyIGSTAmount());
				if(!aTransaction.getCurrencyCGSTAmount().equals(""))aTransaction.setCurrencyCGSTAmount(aTransaction.getCurrencyCGSTAmount());
				if(!aTransaction.getCurrencySGSTAmount().equals(""))aTransaction.setCurrencySGSTAmount(aTransaction.getCurrencySGSTAmount());
			}else{
				if(!aTransaction.getCurrencyIGSTAmount().equals("")){
					BigDecimal igstcurramt=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), aTransaction.getCurrencyIGSTAmount());
					aTransaction.setCurrencyIGSTAmount(String.valueOf(igstcurramt));
				}
				if(!aTransaction.getCurrencyCGSTAmount().equals("")){
					BigDecimal cgstcurramt=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), aTransaction.getCurrencyCGSTAmount());
					aTransaction.setCurrencyCGSTAmount(String.valueOf(cgstcurramt));
				}
				if(!aTransaction.getCurrencySGSTAmount().equals("")){
					BigDecimal sgstcurramt=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), aTransaction.getCurrencySGSTAmount());
					aTransaction.setCurrencySGSTAmount(String.valueOf(sgstcurramt));
				}
			}
		}catch(Exception e){
			logger.warn("------------------Inside Exception---------------------"+e.getMessage());
		}
	}

	@Override
	@Transactional(value="transactionManager")
	public String saveSalesTransaction(String clientID,	ATransaction aTransaction, List<ATransaction> productdetails) {
		logger.info("[saveSalesTransaction()]--------------------------Inside saveSalesTransaction() in DaoImpln Calling--------------------");
		Query q=null;int accounttype_id=0;ChartOfAccount chartofaccount=null;ChartOfAccount chartofaccount1=null;
		ChartOfAccount chartofaccounts=null;String status="Fail";List<SalesTransaction> salestransactionlists=null;
		SalesTransaction salestransaction=null;String accountstatus="";List<SalesCoa> salescoaList=null;List<String> creditParticularList=null;
		JournalEntry journalEntry=null;AccountReceivableAcct accRecAcct=null;String creditParticular="";String creditAmount="";
		List<String> creditAmountList=null;
		try{
			creditParticularList=new ArrayList<String>();creditAmountList=new ArrayList<String>();
			aTransaction.setClientID(clientID);aTransaction.setStatus(activeStatus);aTransaction.setApprovalStatus(draftStatus);
			chartofaccount=new ChartOfAccount();chartofaccount1=new ChartOfAccount();
			chartofaccounts=new ChartOfAccount();
			if(aTransaction.getTransactionType().equalsIgnoreCase("Invoice")){
				aTransaction.setBalAmount(aTransaction.getCurrencyAmount());
				aTransaction.setReceiveAmount("0");aTransaction.setPaymentStatus(unpaidStatus);
				salestransaction=getsavesalestransactiondetails(aTransaction,productdetails);
				getGstdetails(aTransaction,productdetails,salestransaction);
				entitymanager.persist(salestransaction);
				entitymanager.flush();
				entitymanager.clear();
				creditParticularList.add(serviceName);
				if(!aTransaction.getGstType().equals(""))creditParticularList.add(aTransaction.getGstType());
				if(!aTransaction.getCgstType().equals(""))creditParticularList.add(aTransaction.getCgstType());
				if(!aTransaction.getSgstType().equals(""))creditParticularList.add(aTransaction.getSgstType());
				creditParticular=String.join(",", creditParticularList);
				getjournalentryDetails(aTransaction);
				creditAmountList.add(aTransaction.getCurrencybeforeTaxAmount());
				if(!aTransaction.getCurrencyIGSTAmount().equals(""))creditAmountList.add(aTransaction.getCurrencyIGSTAmount());
				if(!aTransaction.getCurrencyCGSTAmount().equals(""))creditAmountList.add(aTransaction.getCurrencyCGSTAmount());
				if(!aTransaction.getCurrencySGSTAmount().equals(""))creditAmountList.add(aTransaction.getCurrencySGSTAmount());
				creditAmount=String.join(",", creditAmountList);
				journalEntry=new JournalEntry(aTransaction,accrecName,creditParticular,salestransaction,creditAmount);
				status=saveSalesInvoiceTransaction(clientID,aTransaction,journalEntry,salestransaction);
			}
			if(aTransaction.getTransactionType().equalsIgnoreCase("Credit Memo")){
				BigDecimal tempamt=BigDecimal.valueOf(0);
				aTransaction.setBalAmount(tempamt.subtract(new BigDecimal(aTransaction.getCurrencyAmount())).toString());
				aTransaction.setReceiveAmount(aTransaction.getCurrencyAmount());
				aTransaction.setTransamount(aTransaction.getBalAmount());
				aTransaction.setDueDate(new Date());
				aTransaction.setPaymentStatus(closedStatus);
				salestransaction=getsavesalestransactiondetails(aTransaction,productdetails);
				getGstdetails(aTransaction,productdetails,salestransaction);
				entitymanager.persist(salestransaction);
				entitymanager.flush();
				entitymanager.clear();
				status=saveSalesCreditMemoTransaction(clientID,aTransaction,salestransaction);
			}
			if(aTransaction.getTransactionType().equalsIgnoreCase("Delayed Charge") || aTransaction.getTransactionType().equalsIgnoreCase("Time Activity")){
				aTransaction.setTransStatus(invoiceStatus);aTransaction.setReceiveAmount("0");aTransaction.setPaymentStatus(unpaidStatus);
				aTransaction.setDueDate(aTransaction.getBillDate());
				salestransaction=getsavesalestransactiondetails(aTransaction,productdetails);
				entitymanager.persist(salestransaction);
				entitymanager.flush();
				entitymanager.clear();
				status="Success";
			}
			if(aTransaction.getTransactionType().equalsIgnoreCase("Estimate")){
				aTransaction.setPaymentStatus(closedStatus);aTransaction.setBalAmount("0");aTransaction.setReceiveAmount("0");
				aTransaction.setDueDate(aTransaction.getBillDate());
				salestransaction=getsavesalestransactiondetails(aTransaction,productdetails);
				entitymanager.persist(salestransaction);
				entitymanager.flush();
				entitymanager.clear();
				status="Success";
			}
			if(aTransaction.getTransactionType().equalsIgnoreCase("Sales Receipt")){
				aTransaction.setBalAmount("0");aTransaction.setReceiveAmount(aTransaction.getCurrencyAmount());
				aTransaction.setPaymentStatus(paidStatus);
				salestransaction=getsavesalestransactiondetails(aTransaction,productdetails);
				getGstdetails(aTransaction,productdetails,salestransaction);
				entitymanager.persist(salestransaction);
				entitymanager.flush();
				entitymanager.clear();
				status=saveSalesReceiptTransaction(aTransaction,clientID,salestransaction);
			}
			if(aTransaction.getTransactionType().equalsIgnoreCase("Payment")){
				if(aTransaction.getPaymentdataTableList().size()==0){
				}else{
					status=saveSalesPaymentTransaction(aTransaction,clientID);
				}
			}
		}catch(Exception e){
			logger.info("--------------Inside Exception------------------"+e.getMessage());
		}
		finally {
			q=null;accounttype_id=0;chartofaccount=null;chartofaccount1=null;chartofaccounts=null;
			salestransactionlists=null;salestransaction=null;journalEntry=null;accRecAcct=null;
		}
		return status;
	}
	
	@Transactional(value="transactionManager")
	private String saveSalesCreditMemoTransaction(String clientID,ATransaction aTransaction,SalesTransaction salestransaction) {
		logger.info("[saveSalesCreditMemoTransaction()]--------------------------Inside saveSalesCreditMemoTransaction() in DaoImpln Calling-------------------------");
		List<ChartOfAccount> chartofaccountList=null;ChartOfAccount chartOfAccount=null;int accounttype_id=0;IncomeAcct incomeAcct=null;
		String debitParticular="";String creditParticular="";JournalEntry journalEntry=null;AccountReceivableAcct accountreceivableAcct=null;
		String status="Fail";BigDecimal tempamt=BigDecimal.valueOf(0);String debitAmount="";List<String> debitParticularList=null;
		List<String> debitAmountList=null;
		try{
			debitAmountList=new ArrayList<String>();debitParticularList=new ArrayList<String>();
			accounttype_id=getaccounttypeid(accrecCategoryType,accrecDetailType);
			aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getReceiveAmount())).toString());
			chartOfAccount=getSalesChartofAccountID(clientID,accrecName,accounttype_id,aTransaction);
			aTransaction.setAccounts(accrecName);aTransaction.setAccountType(accrecDetailType);aTransaction.setStatus(activeStatus);
			accountreceivableAcct=new AccountReceivableAcct(clientID,aTransaction,salestransaction,chartOfAccount,accrecCategoryType);
			entitymanager.persist(accountreceivableAcct);
			accounttype_id=0;chartOfAccount=null;
			accounttype_id=getaccounttypeid(serviceCategoryType,serviceDetailType);
			aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getCurrencybeforeTaxAmount())).toString());
			chartOfAccount=getSalesChartofAccountID(clientID,serviceName,accounttype_id,aTransaction);
			aTransaction.setAccounts(serviceName);aTransaction.setAccountType(serviceDetailType);
			incomeAcct=new IncomeAcct(serviceCategoryType,aTransaction,clientID,salestransaction,chartOfAccount);
			entitymanager.persist(incomeAcct);
			creditParticular=accrecName;
			debitParticularList.add(serviceName);
			if(!aTransaction.getGstType().equals(""))debitParticularList.add(aTransaction.getGstType());
			if(!aTransaction.getCgstType().equals(""))debitParticularList.add(aTransaction.getCgstType());
			if(!aTransaction.getSgstType().equals(""))debitParticularList.add(aTransaction.getSgstType());
			debitParticular=String.join(",", debitParticularList);
			getjournalentryDetails(aTransaction);
			debitAmountList.add(aTransaction.getCurrencybeforeTaxAmount());
			if(!aTransaction.getCurrencyIGSTAmount().equals(""))debitAmountList.add(aTransaction.getCurrencyIGSTAmount());
			if(!aTransaction.getCurrencyCGSTAmount().equals(""))debitAmountList.add(aTransaction.getCurrencyCGSTAmount());
			if(!aTransaction.getCurrencySGSTAmount().equals(""))debitAmountList.add(aTransaction.getCurrencySGSTAmount());
			debitAmount=String.join(",", debitAmountList);
			journalEntry=new JournalEntry(aTransaction,debitParticular,creditParticular,debitAmount,salestransaction);
			entitymanager.persist(journalEntry);
			entitymanager.flush();
			entitymanager.clear();
			status="Success";
		}catch(Exception e){
			logger.warn("-----------------------Inside Exception-----------------------------"+e.getMessage());
		}
		return status;
	}

	@Transactional(value="transactionManager")
	private String saveSalesPaymentTransaction(ATransaction aTransaction,String clientID) {
		logger.info("[saveSalesPaymentTransaction()]------------------------Inside saveSalesPaymentTransaction() in DaoImpln Calling--------------------------");
		Query q=null;List<SalesTransaction> salestransactionLists=null;SalesTransaction salestrans=null;String status="Fail";
		List<ChartOfAccount> chartofaccountList=null;BankAcct  bankAcct=null;OtherCurrentAssetsAcct othercurrentassetAcct=null;
		List<AccountReceivableAcct> accountReceivableAcctList=null;HashSet<AccountReceivableAcct> accountReceivableAccts=null;
		JournalEntry journalEntry=null;String debitParticular="";String creditParticular="";I0021 i0021=null;List<I0021>childList=null;
		try{
			accountReceivableAccts=new HashSet<AccountReceivableAcct>();aTransaction.setReceiveAmount(aTransaction.getCurrencyAmount());
			for (int i = 0; i < aTransaction.getPaymentdataTableList().size(); i++) {
				BigDecimal currAmount=BigDecimal.valueOf(0);
				try{
					if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
						currAmount=new BigDecimal(aTransaction.getPaymentdataTableList().get(i).getPayAmount());
					}else{
						currAmount=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),aTransaction.getPaymentdataTableList().get(i).getPayAmount());
					}
					if(!aTransaction.getPaymentdataTableList().get(i).getTransamount().equals("")&& 
							!aTransaction.getPaymentdataTableList().get(i).getPayAmount().equals("")){
						salestrans=entitymanager.find(SalesTransaction.class, aTransaction.getPaymentdataTableList().get(i).getTransactionID());
						salestrans.setPaidAmount(new BigDecimal(salestrans.getPaidAmount()).add(currAmount).toString());
						salestrans.setBalanceAmount(new BigDecimal(salestrans.getBalanceAmount())
						.subtract(currAmount).toString());
						if(salestrans.getPaidAmount().equalsIgnoreCase("0")){
							salestrans.setPaymentStatus(unpaidStatus);
						}else if(Double.valueOf(salestrans.getBalanceAmount()).equals(Double.valueOf(0))){
							salestrans.setPaymentStatus(paidStatus);
						}else if(!salestrans.getPaidAmount().equalsIgnoreCase("0")&&
								!(salestrans.getBalanceAmount().equalsIgnoreCase("0"))){
							salestrans.setPaymentStatus(partialStatus);
						}
						q=entitymanager.createQuery("from I0021 where salesOrderNumber=? and client_ID=?");
						q.setParameter(1, aTransaction.getPaymentdataTableList().get(0).getTransactionNo());
						q.setParameter(2, clientID);
						childList=(List<I0021>)q.getResultList();
						if(childList.size() > 0){
							i0021=entitymanager.find(I0021.class, childList.get(0).getSales_ID());
							i0021.setStatus2(salestrans.getPaymentStatus()); 
							entitymanager.merge(i0021);
						}else{
							logger.info("-----------------------Inside Sales Order Payment else condition---------------------------");
						}
						q=entitymanager.createQuery("from AccountReceivableAcct where accountName=? and sales_transaction_ID=? and status=?");
						q.setParameter(1, accrecName);
						q.setParameter(2, aTransaction.getPaymentdataTableList().get(i).getTransactionID());
						q.setParameter(3, activeStatus);
						accountReceivableAcctList=(List<AccountReceivableAcct>)q.getResultList();
						if(accountReceivableAcctList.size()>0){
							AccountReceivableAcct accountReceivableAcct=entitymanager.find(AccountReceivableAcct.class, accountReceivableAcctList.get(0).getId());
							accountReceivableAcct.setCreditAmount(new BigDecimal(accountReceivableAcct.getCreditAmount()).add(currAmount).toString());
							accountReceivableAcct.setBalance(new BigDecimal(accountReceivableAcct.getBalance()).subtract(currAmount).toString());
							accountReceivableAccts.add(accountReceivableAcct);
						}
						salestrans.setAccountReceivableAccts(accountReceivableAccts);
						entitymanager.merge(salestrans);
						entitymanager.flush();
						entitymanager.clear();
						chartofaccountList=getchartofaccountList(clientID,aTransaction.getAccountType());
						aTransaction.setTransamount(String.valueOf(currAmount));
						aTransaction.setTransactionNo(aTransaction.getPaymentdataTableList().get(i).getTransactionNo());
						if(chartofaccountList.size()>0){
							ChartOfAccount coas=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
							aTransaction.setStatus(activeStatus);
							if(chartofaccountList.get(0).getAccountType().getCategoryType().equals(bankCategoryType)){
								bankAcct=new BankAcct(clientID,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),salestrans);
								bankAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID()));
								entitymanager.persist(bankAcct);
							}
							if(chartofaccountList.get(0).getAccountType().getCategoryType().equals(othercurrassetsCateType)){
								othercurrentassetAcct=new OtherCurrentAssetsAcct(clientID,aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),salestrans);
								othercurrentassetAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID()));
								entitymanager.persist(othercurrentassetAcct);
							}
							coas.setBalance(new BigDecimal(coas.getBalance()).add(new BigDecimal(aTransaction.getTransamount())).toString());
							entitymanager.merge(coas);	
						}
						debitParticular=aTransaction.getAccountType();creditParticular=accrecName;aTransaction.setCurrencyAmount(String.valueOf(currAmount));
						journalEntry=new JournalEntry(aTransaction,clientID,debitParticular,creditParticular,null,activeStatus);
						entitymanager.persist(journalEntry);
						entitymanager.flush();
						entitymanager.clear();
					}
				}catch(Exception e){
					logger.warn("------------------Inside Exception-------------------------"+e.getMessage());
				}
			}		
			chartofaccountList=getchartofaccountList(clientID,accrecName);
			if(chartofaccountList.size()>0){
				ChartOfAccount coas=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
				coas.setBalance(new BigDecimal(coas.getBalance()).subtract(new BigDecimal(aTransaction.getReceiveAmount())).toString());
				entitymanager.merge(coas);
			}
			status="Success";
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("-------------------Inside Exception-------------------"+e.getMessage());
		}finally{
			q=null;salestransactionLists=null;salestrans=null;chartofaccountList=null;bankAcct=null;othercurrentassetAcct=null;
			accountReceivableAcctList=null;accountReceivableAccts=null;journalEntry=null;debitParticular="";creditParticular="";
		}
		return status;
	}

	@Override
	@Transactional(value="transactionManager")
	public String generateInvoice(String clientID, int transactionID) {
		logger.info("[generateInvoice()]---------------------Inside generateInvoice() in DaoImpln Calling----------------------");
		String status="Fail";JournalEntry journalEntry=null;ATransaction aTransaction=null;Query q=null;String creditAmount="";
		List<SalesAccountsPayment> salesaccountpaymentList=null;List<ATransaction> domainList=null;String creditParticular="";
		BigDecimal peramt;BigDecimal cgstamt;BigDecimal sgstamt;String igstAmt="";String cgstAmt="";String sgstAmt="";String gstType="";String cgstType="";String sgstType="";
		BigDecimal totalperamt=BigDecimal.valueOf(0);BigDecimal totalcgstamt=BigDecimal.valueOf(0);BigDecimal totalsgstamt=BigDecimal.valueOf(0);
		List<String> creditParticularList=null;List<String> creditAmountList=null;
		try{
			creditParticularList=new ArrayList<String>();creditAmountList=new ArrayList<String>();
			aTransaction=new ATransaction();domainList=new ArrayList<ATransaction>();
			SalesTransaction salesTrans=entitymanager.find(SalesTransaction.class, transactionID);
			salesTrans.setInvoiceStatus(notStartPayStatus);salesTrans.setBalanceAmount(salesTrans.getCurrencyAmount());
			salesTrans.setPaymentStatus(unpaidStatus);aTransaction.setClientID(clientID);aTransaction.setStatus(activeStatus);
			aTransaction.setBillDate(salesTrans.getStartDate());
			aTransaction.setCustomerName(salesTrans.getCustomerName());aTransaction.setTransamount(salesTrans.getTransactionAmount());
			aTransaction.setTransactionNo(salesTrans.getReferenceNumber());aTransaction.setTransactionType(salesTrans.getTransactionType());
			aTransaction.setCurrencyAmount(salesTrans.getCurrencyAmount());aTransaction.setTaxType(salesTrans.getTaxType());
			aTransaction.setCurrencybeforeTaxAmount(salesTrans.getCurrencybeforetaxAmount());aTransaction.setPayeeName(salesTrans.getCustomerName());
			aTransaction.setCurrencyType(salesTrans.getCurrencyType());aTransaction.setBaseCurrency(salesTrans.getBaseCurrency());
			q=entitymanager.createQuery("from SalesAccountsPayment where sales_transaction_ID=? and status=?");
			q.setParameter(1, transactionID);
			q.setParameter(2, activeStatus);
			salesaccountpaymentList=(List<SalesAccountsPayment>)q.getResultList();
			if(salesaccountpaymentList.size()>0){
				for (int i = 0; i < salesaccountpaymentList.size(); i++) {
					ATransaction domain=new ATransaction();
					domain.setTaxes(salesaccountpaymentList.get(i).getTaxes());
					domain.setProductName(salesaccountpaymentList.get(i).getProductName());
					domain.setCgstAmount(salesaccountpaymentList.get(i).getCgstAmount());
					domain.setSgstAmount(salesaccountpaymentList.get(i).getSgstAmount());
					domain.setPercentageAmount(salesaccountpaymentList.get(i).getTaxAmount());
					if(!aTransaction.getTaxType().equals("Out of scope of Tax") || !aTransaction.getTaxType().equals("")){
						peramt=BigDecimal.valueOf(0);cgstamt=BigDecimal.valueOf(0);sgstamt=BigDecimal.valueOf(0);
						try{
							if(salesaccountpaymentList.get(i).getTaxAmount().equalsIgnoreCase("") || salesaccountpaymentList.get(i).getTaxAmount().equalsIgnoreCase(null)){
								peramt=BigDecimal.valueOf(0);igstAmt="";aTransaction.setCurrencyIGSTAmount("");
							}else{
								peramt=new BigDecimal(salesaccountpaymentList.get(i).getTaxAmount());
								totalperamt=totalperamt.add(peramt);gstType="Output IGST";
								igstAmt=String.valueOf(totalperamt);
							}
						}catch(NullPointerException e){
							e.printStackTrace();
						}
						try{
							if(salesaccountpaymentList.get(i).getCgstAmount().equalsIgnoreCase("") || salesaccountpaymentList.get(i).getCgstAmount().equalsIgnoreCase(null)){
								cgstamt=BigDecimal.valueOf(0);cgstAmt="";aTransaction.setCurrencyCGSTAmount("");
								cgstType="";
							}else{
								cgstamt=new BigDecimal(salesaccountpaymentList.get(i).getCgstAmount());
								totalcgstamt=totalcgstamt.add(cgstamt);cgstType="Output CGST";
								cgstAmt=String.valueOf(totalcgstamt);
							}
						}catch(NullPointerException e){
							e.printStackTrace();
						}
						try{
							if(salesaccountpaymentList.get(i).getSgstAmount().equalsIgnoreCase("") || salesaccountpaymentList.get(i).getSgstAmount().equalsIgnoreCase(null)){
								sgstamt=BigDecimal.valueOf(0);sgstAmt="";aTransaction.setCurrencySGSTAmount("");
								sgstType="";
							}else {
								sgstamt=new BigDecimal(salesaccountpaymentList.get(i).getSgstAmount());
								totalsgstamt=totalsgstamt.add(sgstamt);sgstType="Output SGST";
								sgstAmt=String.valueOf(totalsgstamt);
							}
						}catch(NullPointerException e){
							e.printStackTrace();
						}
					}
					domainList.add(domain);
				}
				aTransaction.setGstType(gstType);aTransaction.setCgstType(cgstType);aTransaction.setSgstType(sgstType);
				aTransaction.setCurrencyIGSTAmount(igstAmt);aTransaction.setCurrencyCGSTAmount(cgstAmt);aTransaction.setCurrencySGSTAmount(sgstAmt);
			}
			getGstdetails(aTransaction,domainList,salesTrans);
			creditParticularList.add(serviceName);
			if(!aTransaction.getGstType().equals(""))creditParticularList.add(aTransaction.getGstType());
			if(!aTransaction.getCgstType().equals(""))creditParticularList.add(aTransaction.getCgstType());
			if(!aTransaction.getSgstType().equals(""))creditParticularList.add(aTransaction.getSgstType());
			creditParticular=String.join(",", creditParticularList);
			getjournalentryDetails(aTransaction);
			creditAmountList.add(aTransaction.getCurrencybeforeTaxAmount());
			if(!aTransaction.getCurrencyIGSTAmount().equals(""))creditAmountList.add(aTransaction.getCurrencyIGSTAmount());
			if(!aTransaction.getCurrencyCGSTAmount().equals(""))creditAmountList.add(aTransaction.getCurrencyCGSTAmount());
			if(!aTransaction.getCurrencySGSTAmount().equals(""))creditAmountList.add(aTransaction.getCurrencySGSTAmount());
			creditAmount=String.join(",", creditAmountList);
			journalEntry=new JournalEntry(aTransaction,accrecName,creditParticular,salesTrans,creditAmount);
			status=saveSalesInvoiceTransaction(clientID,aTransaction,journalEntry,salesTrans);
			entitymanager.merge(salesTrans);
			entitymanager.flush();
			entitymanager.clear();
		}catch(Exception e){
			logger.warn("----------------Inside Exception-------------------"+e.getMessage());
		}finally{
		}
		return status;
	}
	
	@Override
	public List<JournalEntry> getjournalEntryList(AccountsDatabean accountsDatabean) {
		logger.info("[getjournalEntryList()-----------------Inside getjournalEntryList() in DaoImpln Calling---------------------------]");
		List<JournalEntry> journalEntryList=null;Query q=null;
		SimpleDateFormat dateformat = null;
		try{
			dateformat=new SimpleDateFormat("yyyy-MM-dd");
			if(accountsDatabean.getReportPeriod().equals("All dates")){
				q=entitymanager.createQuery("from JournalEntry where client_ID=? and status=? order by tranDate desc");
				q.setParameter(1, accountsDatabean.getClientID());
				q.setParameter(2, activeStatus);
			}else{
				q=entitymanager.createQuery("from JournalEntry where client_ID=? and status=? and createdDate between ? and ? order by tranDate desc");
				q.setParameter(1, accountsDatabean.getClientID());
				q.setParameter(2, activeStatus);
				q.setParameter(3, accountsDatabean.getFromDate());
				q.setParameter(4, accountsDatabean.getToDate());
			}
			journalEntryList=(List<JournalEntry>)q.getResultList();
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("------------Inside Exception----------------"+e.getMessage());
		}finally{
			q=null;
		}
		return journalEntryList;
	}
	
	public List<AccountType> getCategoryTypes(){	
		logger.debug("[getCategoryTypes()]--------------------Inside getCategoryTypes() DaoImpln Calling--------------------"); 
		Query q=null;List<AccountType> accType=null;
		try{
			q=entitymanager.createQuery("from AccountType where status=?");
			q.setParameter(1, activeStatus);
			accType=(ArrayList<AccountType>)q.getResultList();
		}catch(Exception e){
			logger.warn("-------------------Inside Exception------------------");
		}finally{
			q=null;
		}
		return accType;		
	}
	
	public List<String> getAccountDetailsType(String account_type){
		logger.debug("[getAccountDetailsType()]--------------------Inside getAccountDetailsType DaoImpln Calling--------------------"); 
		Query q=null;List<String> accType=null;
		try{
			q=entitymanager.createQuery("select detailType from AccountType where categoryType=? and status=?"); 
			q.setParameter(1, account_type);
			q.setParameter(2,"Active");
			accType=(ArrayList<String>)q.getResultList();
		}catch(Exception e){
			logger.warn("-------------------Inside Exception------------------");
		}finally{
			q=null;
		}
		return accType;	
	}
	
	private List<AccountType> getAccountTypeList(String categoryType,String detailType){
		logger.info("[getAccountTypeList()]-------------------getAccountTypeList() in DaoImpln Calling------------------");
		Query q=null;List<AccountType> accountType=null;
		try{
			q=entitymanager.createQuery("from AccountType where categoryType=? and detailType=? and status=?");
			q.setParameter(1, categoryType);
			q.setParameter(2, detailType);
			q.setParameter(3, activeStatus);
			accountType=(List<AccountType>)q.getResultList();
		}catch(Exception e){
			logger.warn("-----------------Inside Exception------------------"+e.getMessage());
		}finally{
			q=null;
		}
		return accountType;
	}
	
	@Transactional(value="transactionManager")
	private String inserOpeningBalEquity(String clientID,AccountsDatabean accountsDatabean){
		logger.info("[inserOpeningBalEquity()]-------------------inserOpeningBalEquity() in DaoImpln Calling------------------");
		List<ChartOfAccount> accDeposit1=null;ChartOfAccount account1=null;List<AccountType> accountType1=null;
		BigDecimal tempamt=BigDecimal.valueOf(0);
		try{
			List<String> assetList=Arrays.asList("Bank","Other Current Assets","Fixed Assets","Other Assets");
			List<String> libList=Arrays.asList("Credit Card","Other Current Liabilities","Notes Payable","Equity");
			accDeposit1=getchartofaccountList(clientID,openingbalequityName);
			if(accDeposit1.size()==0){
				account1=new ChartOfAccount(); 
				accountType1=getAccountTypeList(openingbalequityCateType, openingbalequityName);
				account1.setAccountType(entitymanager.find(AccountType.class, accountType1.get(0).getAccount_type_ID()));
				account1.setAccountName(openingbalequityName);
				account1.setAccountNameDescription("");
				account1.setStatus(activeStatus);
				account1.setClient_ID(clientID);
				account1.setTrandate(accountsDatabean.getDate());
				account1.setCreatedDate(new Date());
				if(assetList.contains(accountsDatabean.getAccount_type())){
					account1.setBalance(tempamt.add(new BigDecimal(accountsDatabean.getBalance())).toString());
				}else if(libList.contains(accountsDatabean.getAccount_type())){
					account1.setBalance(tempamt.subtract(new BigDecimal(accountsDatabean.getBalance())).toString());
				}else{
					account1.setBalance("0");
				}
				entitymanager.persist(account1);
				entitymanager.flush();
				entitymanager.clear();
			}
			if(accDeposit1.size()>0){
				ChartOfAccount chartofaccount=entitymanager.find(ChartOfAccount.class, accDeposit1.get(0).getChart_of_account_ID());
				if(assetList.contains(accountsDatabean.getAccount_type())){
					chartofaccount.setBalance(new BigDecimal(accDeposit1.get(0).getBalance()).add(new BigDecimal(accountsDatabean.getBalance())).toString());
				}
				if(libList.contains(accountsDatabean.getAccount_type())){
					chartofaccount.setBalance(new BigDecimal(accDeposit1.get(0).getBalance()).subtract(new BigDecimal(accountsDatabean.getBalance())).toString());
				}
				entitymanager.merge(chartofaccount);
				entitymanager.flush();
				entitymanager.clear();
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("-----------------Inside Exception------------------"+e.getMessage());
		}finally{
			 accDeposit1=null;account1=null;accountType1=null;
		}
		return "";
	}
	
	@Override
	@Transactional(value="transactionManager")
	public String saveAcountDeposit(String clientID, AccountsDatabean accountsDatabean){
		logger.info("[saveAcountDeposit()]--------------------Inside saveAcountDeposit() Calling----------------------");
		Query q=null;String status="";Date date=null; ChartOfAccount account=null;
		List<ChartOfAccount> accDeposit=null;List<AccountType> accountType=null;
		try{
			List<String> assetList=Arrays.asList("Bank","Other Current Assets","Fixed Assets","Other Assets");
			List<String> libList=Arrays.asList("Credit Card","Other Current Liabilities","Notes Payable","Equity");
			List<String> libList1=Arrays.asList("Other Current Liabilities","Notes Payable","Equity");
			date=new Date();
			accDeposit=getchartofaccountList(clientID,accountsDatabean.getAccount_name());
			if(accDeposit.size()>0){
				status="Exist";
			}
			if(accDeposit.size()==0){
				if(accountsDatabean.getAccount_type().equals(openingbalequityCateType)&&accountsDatabean.getDetailType().equals(openingbalequityName)){
					status="OpenbalequityExist";
				}else{
					accountsDatabean.setBalance(accountsDatabean.getCurrencyAmount());
					account=new ChartOfAccount(); 
					account.setAccountName(accountsDatabean.getAccount_name());
					account.setAccountNameDescription("");
					account.setBalance(String.valueOf(accountsDatabean.getBalance()));
					account.setStatus(activeStatus);
					account.setClient_ID(clientID);
					account.setTrandate(accountsDatabean.getDate());
					account.setCreatedDate(date);
					account.setCurrency(accountsDatabean.getCurrency());
					account.setTaxes(accountsDatabean.getTaxes());
					accountType=getAccountTypeList(accountsDatabean.getAccount_type(), accountsDatabean.getDetailType());				
					if (accountType.size()>0) {
						account.setAccountType(entitymanager.find(AccountType.class, accountType.get(0).getAccount_type_ID()));
						if(accountType.get(0).getOpeningBalance().equals(openingBalance)){
							account.setOpeningBalance(String.valueOf(accountsDatabean.getBalance()));
							insertSubAccountOpeningBalDetails(clientID,accountsDatabean,account);
						}else{
							account.setOpeningBalance(nonOpeningBalance);
						}
						entitymanager.persist(account);
						entitymanager.flush();
						entitymanager.clear();
					}
					if(!accountsDatabean.getAccount_name().equals(openingbalequityName)){
						inserOpeningBalEquity(clientID,accountsDatabean);
					}
					status="Success";
				}
			}
		}catch(Exception e){
			logger.warn("-------------------Inside Exception------------------"+e.getMessage());
		}finally{
			q=null;account=null;date=null;accountType=null;accDeposit=null;
		}
		return status;
	}
	
	@Transactional(value="transactionManager")
	private void insertSubAccountOpeningBalDetails(String clientID,AccountsDatabean accountsDatabean,ChartOfAccount account) {
		logger.info("[insertSubAccountOpeningBalDetails()]--------------------Inside insertSubAccountOpeningBalDetails() Calling----------------------");
		BankAcct bankAcct=null;HashSet<BankAcct> bankAccts=null;OtherCurrentAssetsAcct otherCurrentAssetsAcct=null;HashSet<OtherCurrentAssetsAcct> otherCurrentAssetsAccts=null;
		OtherAssetsAcct otherAssetsAcct=null;HashSet<OtherAssetsAcct> otherAssetsAccts=null;FixedAssetsAcct fixedAssetsAcct=null;HashSet<FixedAssetsAcct> fixedAssetsAccts=null;
		CreditCardAcct creditCardAcct=null;HashSet<CreditCardAcct> creditCardAccts=null;EquityAcct equityAcct=null;HashSet<EquityAcct> equityAccts=null;
		OpenbalEquityAcct openbalEquityAcct=null;HashSet<OpenbalEquityAcct> openbalEquityAccts=null;
		OtherCurrentLibAcct otherCurrentLibAcct=null;HashSet<OtherCurrentLibAcct> otherCurrentLibAccts=null;
		NotesPayableAcct notesPayableAcct=null;HashSet<NotesPayableAcct> notesPayableAccts=null;
		String debitParticular="";String creditParticular="";JournalEntry journalEntry=null;BigDecimal balamt=BigDecimal.valueOf(0);
		try{
			List<String> assetList=Arrays.asList("Bank","Other Current Assets","Fixed Assets","Other Assets");
			List<String> libList=Arrays.asList("Credit Card","Other Current Liabilities","Notes Payable","Equity");
			bankAccts=new HashSet<BankAcct>();otherCurrentAssetsAccts=new HashSet<OtherCurrentAssetsAcct>();
			otherAssetsAccts=new HashSet<OtherAssetsAcct>();fixedAssetsAccts=new HashSet<FixedAssetsAcct>();
			creditCardAccts=new HashSet<CreditCardAcct>();equityAccts=new HashSet<EquityAcct>();openbalEquityAccts=new HashSet<OpenbalEquityAcct>();
			otherCurrentLibAccts=new HashSet<OtherCurrentLibAcct>();notesPayableAccts=new HashSet<NotesPayableAcct>();
			accountsDatabean.setStatus(activeStatus);
			accountsDatabean.setClientID(clientID);
			if(assetList.contains(accountsDatabean.getAccount_type())){
				accountsDatabean.setTransactionType("Deposit");
				accountsDatabean.setName("Opening Balance Equity");
				accountsDatabean.setDepositAmount(accountsDatabean.getBalance());
				accountsDatabean.setCreditAmount("");
				if(accountsDatabean.getAccount_type().equals("Bank")){
					bankAcct=new BankAcct(accountsDatabean,account);
					bankAccts.add(bankAcct);
					account.setBankAccts(bankAccts);
				}
				if(accountsDatabean.getAccount_type().equals("Other Current Assets")){
					otherCurrentAssetsAcct=new OtherCurrentAssetsAcct(accountsDatabean,account);
					otherCurrentAssetsAccts.add(otherCurrentAssetsAcct);
					account.setOtherCurrentAssetsAccts(otherCurrentAssetsAccts);
				}
				if(accountsDatabean.getAccount_type().equals("Fixed Assets")){
					fixedAssetsAcct=new FixedAssetsAcct(accountsDatabean,account);
					fixedAssetsAccts.add(fixedAssetsAcct);
					account.setFixedAssetsAccts(fixedAssetsAccts);
				}
				if(accountsDatabean.getAccount_type().equals("Other Assets")){
					otherAssetsAcct=new OtherAssetsAcct(accountsDatabean,account);
					otherAssetsAccts.add(otherAssetsAcct);
					account.setOtherAssetsAccts(otherAssetsAccts);
				}
				accountsDatabean.setDepositAmount("");
				accountsDatabean.setCreditAmount(accountsDatabean.getBalance());
				accountsDatabean.setAccount_amount(accountsDatabean.getBalance());
				openbalEquityAcct=new OpenbalEquityAcct(accountsDatabean,account,openingbalequityCateType);
				openbalEquityAccts.add(openbalEquityAcct);
				account.setOpeningbalequityAccts(openbalEquityAccts);
				debitParticular=accountsDatabean.getAccount_name();
				creditParticular=accountsDatabean.getName();
				journalEntry=new JournalEntry(accountsDatabean,debitParticular,creditParticular);
				entitymanager.persist(journalEntry);
				entitymanager.flush();
				entitymanager.clear();
			}
			if(libList.contains(accountsDatabean.getAccount_type())){
				accountsDatabean.setName("Opening Balance Equity");
				accountsDatabean.setDepositAmount("");
				accountsDatabean.setCreditAmount(accountsDatabean.getBalance());
				if(accountsDatabean.getAccount_type().equals("Credit Card")){
					accountsDatabean.setTransactionType("Credit Card Expense");
					creditCardAcct=new CreditCardAcct(accountsDatabean,account);
					creditCardAccts.add(creditCardAcct);
					account.setCreditCardAccts(creditCardAccts);
				}
				if(accountsDatabean.getAccount_type().equals("Other Current Liabilities")){
					accountsDatabean.setTransactionType("Journal");
					otherCurrentLibAcct=new OtherCurrentLibAcct(accountsDatabean,account);
					otherCurrentLibAccts.add(otherCurrentLibAcct);
					account.setOthercurrentlibAccts(otherCurrentLibAccts);
				}
				if(accountsDatabean.getAccount_type().equals("Notes Payable")){
					accountsDatabean.setTransactionType("Journal");
					notesPayableAcct=new NotesPayableAcct(accountsDatabean,account);
					notesPayableAccts.add(notesPayableAcct);
					account.setNotespayableAccts(notesPayableAccts);
				}
				if(accountsDatabean.getAccount_type().equals("Equity")){
					accountsDatabean.setTransactionType("Journal");
					equityAcct=new EquityAcct(accountsDatabean,account);
					equityAccts.add(equityAcct);
					account.setEquityAccts(equityAccts);
				}
				accountsDatabean.setDepositAmount(accountsDatabean.getBalance());
				accountsDatabean.setCreditAmount("");accountsDatabean.setAccount_amount(balamt.subtract(new BigDecimal(accountsDatabean.getBalance())).toString());
				openbalEquityAcct=new OpenbalEquityAcct(accountsDatabean,account,openingbalequityCateType);
				openbalEquityAccts.add(openbalEquityAcct);
				account.setOpeningbalequityAccts(openbalEquityAccts);
				debitParticular=accountsDatabean.getName();
				creditParticular=accountsDatabean.getAccount_name();
				journalEntry=new JournalEntry(accountsDatabean,debitParticular,creditParticular);
				entitymanager.persist(journalEntry);
				entitymanager.flush();
				entitymanager.clear();
			}
		}catch(Exception e){
			logger.warn("-------------------Inside Exception---------------------------"+e.getMessage());
		}finally{
			bankAcct=null;bankAccts=null;otherCurrentAssetsAcct=null;otherCurrentAssetsAccts=null;
			otherAssetsAcct=null;otherAssetsAccts=null;fixedAssetsAcct=null;fixedAssetsAccts=null;
			creditCardAcct=null;creditCardAccts=null;equityAcct=null;equityAccts=null;
			openbalEquityAcct=null;openbalEquityAccts=null;otherCurrentLibAcct=null;otherCurrentLibAccts=null;
			notesPayableAcct=null;notesPayableAccts=null;
		}
	}

	public List<ChartOfAccount> getDeposit(String clientID, String description){
		logger.info("[getDeposit()]---------------------Inside getDeposit() in DaoImpln Calling-----------------------");
		Query q=null;List<ChartOfAccount> accDeposit=null;
		try{			
			q=entitymanager.createQuery("from ChartOfAccount where client_ID=? and status=?");
			q.setParameter(1, clientID);
			q.setParameter(2, activeStatus);
			accDeposit=(List<ChartOfAccount>)q.getResultList();
		}catch(Exception e){
			logger.warn("-------------Inside Exception-------------------"+e.getMessage());
		}finally{
			q=null;
		}
		return accDeposit;
	}
	
	@Override
	public List<SalesTransaction> salesTransactionView(String clientID,ATransaction aTransaction) {
		logger.info("[salesTransactionView()]------------------salesTransactionView() in DaoImpln Calling-----------------------");
		Query q=null;List<SalesTransaction> transactionList=null;
		try{
			 q=entitymanager.createQuery("from SalesTransaction where client_ID=? and sales_transaction_ID=? and transactionType=? and status=?");
			 q.setParameter(1, clientID);
			 q.setParameter(2, aTransaction.getTransactionID());
			 q.setParameter(3, aTransaction.getTransactionType());
			 q.setParameter(4, activeStatus);
			 transactionList=q.getResultList();
		}catch(Exception e){
			logger.warn("---------------Inside Exception------------------"+e.getMessage());
		}finally{
			q=null;
		}
		return transactionList;
	}

	@Override
	public List<SalesAccountsPayment> salesTransactionPaymentview(int sales_transaction_ID, String clientID) {
		logger.info("[salesTransactionPaymentview()]------------------salesTransactionPaymentview() in DaoImpln Calling-----------------------");
		Query q=null;List<SalesAccountsPayment> accountsList=null;
		try{
			q=entitymanager.createQuery("from SalesAccountsPayment where sales_transaction_ID=? and status=?");
			q.setParameter(1, sales_transaction_ID);
			q.setParameter(2, activeStatus);
			accountsList=q.getResultList();
		}catch(Exception e){
			logger.warn("---------------Inside Exception------------------"+e.getMessage());
		}finally{
			q=null;
		}
		return accountsList;
	}
	
	@Transactional(value = "transactionManager")
	private String updateSalesInvoiceTransaction(String clientID,ATransaction aTransaction) {
		logger.info("[updateSalesInvoiceTransaction()]--------------------Inside updateSalesInvoiceTransaction() in DaoImpln Calling--------------------------------");
		String status="Fail";Query q=null;List<JournalEntry> journalEntryList=null;String amount="";String creditAmount="";
		List<AccountReceivableAcct> accountReceivableList=null;List<IncomeAcct> incomeAcctList=null;List<String> creditAmountList=null;
		ChartOfAccount chartofaccount=null;int accounttype_id=0;List<String> creditParticularList=null;String creditParticular="";
		List<ChartOfAccount> chartofaccountList=null;List<ChartOfAccount> chartofaccountList1=null;
		try{
			System.out.println("before tax amount"+aTransaction.getBeforeTaxAmount());
			System.out.println("curr amount"+aTransaction.getCurrencyAmount());
			System.out.println("curr before tax amount"+aTransaction.getCurrencybeforeTaxAmount());
			creditParticularList=new ArrayList<String>();creditAmountList=new ArrayList<String>();
			chartofaccountList=getchartofaccountList(clientID,accrecName);
			chartofaccountList1=getchartofaccountList(clientID,serviceName);
			if(chartofaccountList.size()>0){
				if(new BigDecimal(aTransaction.getCurrencyAmount()).compareTo(new BigDecimal(aTransaction.getEditTransAmount()))==1){
					amount=new BigDecimal(aTransaction.getCurrencyAmount()).subtract(new BigDecimal(aTransaction.getEditTransAmount())).toString();
					ChartOfAccount coaobj1=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
					coaobj1.setBalance(new BigDecimal(coaobj1.getBalance()).add(new BigDecimal(amount)).toString());
					entitymanager.merge(coaobj1);
				}
				if(new BigDecimal(aTransaction.getCurrencyAmount()).compareTo(new BigDecimal(aTransaction.getEditTransAmount()))==-1){
					amount=new BigDecimal(aTransaction.getEditTransAmount()).subtract(new BigDecimal(aTransaction.getCurrencyAmount())).toString();
					ChartOfAccount coaobj2=entitymanager.find(ChartOfAccount.class, chartofaccountList.get(0).getChart_of_account_ID());
					coaobj2.setBalance(new BigDecimal(coaobj2.getBalance()).subtract(new BigDecimal(amount)).toString());
					entitymanager.merge(coaobj2);
				}
			}
			if(chartofaccountList1.size()>0){
				if(new BigDecimal(aTransaction.getCurrencybeforeTaxAmount()).compareTo(new BigDecimal(aTransaction.getEditBeforeTaxAmount()))==1){
					amount=new BigDecimal(aTransaction.getCurrencybeforeTaxAmount()).subtract(new BigDecimal(aTransaction.getEditBeforeTaxAmount())).toString();
					ChartOfAccount coaobj3=entitymanager.find(ChartOfAccount.class, chartofaccountList1.get(0).getChart_of_account_ID());
					coaobj3.setBalance(new BigDecimal(coaobj3.getBalance()).add(new BigDecimal(amount)).toString());
					entitymanager.merge(coaobj3);
				}
				if(new BigDecimal(aTransaction.getCurrencybeforeTaxAmount()).compareTo(new BigDecimal(aTransaction.getEditBeforeTaxAmount()))==-1){
					amount=new BigDecimal(aTransaction.getEditBeforeTaxAmount()).subtract(new BigDecimal(aTransaction.getCurrencybeforeTaxAmount())).toString();
					ChartOfAccount coaobj4=entitymanager.find(ChartOfAccount.class, chartofaccountList1.get(0).getChart_of_account_ID());
					coaobj4.setBalance(new BigDecimal(coaobj4.getBalance()).subtract(new BigDecimal(amount)).toString());
					entitymanager.merge(coaobj4);
				}
			}
			creditParticularList.add(serviceName);
			if(!aTransaction.getGstType().equals(""))creditParticularList.add(aTransaction.getGstType());
			if(!aTransaction.getCgstType().equals(""))creditParticularList.add(aTransaction.getCgstType());
			if(!aTransaction.getSgstType().equals(""))creditParticularList.add(aTransaction.getSgstType());
			creditParticular=String.join(",", creditParticularList);
			getjournalentryDetails(aTransaction);
			creditAmountList.add(aTransaction.getCurrencybeforeTaxAmount());
			if(!aTransaction.getCurrencyIGSTAmount().equals(""))creditAmountList.add(aTransaction.getCurrencyIGSTAmount());
			if(!aTransaction.getCurrencyCGSTAmount().equals(""))creditAmountList.add(aTransaction.getCurrencyCGSTAmount());
			if(!aTransaction.getCurrencySGSTAmount().equals(""))creditAmountList.add(aTransaction.getCurrencySGSTAmount());
			creditAmount=String.join(",", creditAmountList);
			q=entitymanager.createQuery("from JournalEntry where client_ID=? and sales_transaction_ID=? and status=?");
			q.setParameter(1, clientID);
			q.setParameter(2, aTransaction.getTransactionID());
			q.setParameter(3, activeStatus);
			journalEntryList=(List<JournalEntry>)q.getResultList();
			if(journalEntryList.size()>0){
				JournalEntry journal=entitymanager.find(JournalEntry.class, journalEntryList.get(0).getJournalEntryId());
				journal.setPayeeName(aTransaction.getCustomerName());
				journal.setDebitParticular(accrecName);
				journal.setCreditParticular(creditParticular);
				journal.setDebitAmount(aTransaction.getCurrencyAmount());
				journal.setCreditAmount(creditAmount);
				entitymanager.merge(journal);
			}
			q=null;
			q=entitymanager.createQuery("from AccountReceivableAcct where client_ID=? and sales_transaction_ID=? and status=?");
			q.setParameter(1, clientID);
			q.setParameter(2, aTransaction.getTransactionID());
			q.setParameter(3, activeStatus);
			accountReceivableList=(List<AccountReceivableAcct>)q.getResultList();
			if(accountReceivableList.size()>0){
				AccountReceivableAcct accountReceivable=entitymanager.find(AccountReceivableAcct.class, accountReceivableList.get(0).getId());
				accountReceivable.setPayeeName(aTransaction.getCustomerName());
				accountReceivable.setDebitAmount(aTransaction.getCurrencyAmount());
				accountReceivable.setBalance(aTransaction.getCurrencyAmount());
				entitymanager.merge(accountReceivable);
			}
			q=null;
			q=entitymanager.createQuery("from IncomeAcct where client_ID=? and sales_transaction_ID=? and status=?");
			q.setParameter(1, clientID);
			q.setParameter(2, aTransaction.getTransactionID());
			q.setParameter(3, activeStatus);
			incomeAcctList=(List<IncomeAcct>)q.getResultList();
			if(incomeAcctList.size()>0){
				IncomeAcct incomeAcct=entitymanager.find(IncomeAcct.class, incomeAcctList.get(0).getId());
				incomeAcct.setPayeeName(aTransaction.getCustomerName());
				incomeAcct.setCreditAmount(aTransaction.getCurrencybeforeTaxAmount());
				incomeAcct.setBalance(aTransaction.getCurrencybeforeTaxAmount());
				entitymanager.merge(incomeAcct);
			}
			status="Success";
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("---------------Inside Exception-----------------------"+e.getMessage());
		}
		return status;
	}
	
	@Override
	@Transactional(value = "transactionManager")
	public String salesTransactionUpadte(String clientID,ATransaction aTransaction) {
		logger.info("[updateSalesTransaction()]--------------------Inside updateSalesTransaction() in DaoImpln Calling--------------------------------");
		String status="Fail";Query q=null;
		try{
			if(aTransaction.getRemoveList().size()>0){
				for (int i = 0; i < aTransaction.getRemoveList().size(); i++) {
					SalesAccountsPayment payment=entitymanager.find(SalesAccountsPayment.class, aTransaction.getRemoveList().get(i));
					payment.setStatus("DeActive");
					entitymanager.merge(payment);
				}
			}
			status=updateSalesTransaction(clientID,aTransaction);
			if(aTransaction.getTransactionType().equalsIgnoreCase("Invoice")){
				q=entitymanager.createQuery("delete from GstAcct where sales_transaction_ID=? and status=?");
				q.setParameter(1, aTransaction.getTransactionID());
				q.setParameter(2, "Active");
				q.executeUpdate();
				saveSalesEditGstDetails(aTransaction,aTransaction.getSalestransaction());
				status=updateSalesInvoiceTransaction(aTransaction.getClientID(),aTransaction);
			}
			if(aTransaction.getTransactionType().equalsIgnoreCase("Delayed Charge") || aTransaction.getTransactionType().equalsIgnoreCase("Time Activity")){
				if(aTransaction.getSalestransaction().getInvoiceStatus().equalsIgnoreCase("Payment Not Start")){
					q=entitymanager.createQuery("delete from GstAcct where sales_transaction_ID=? and status=?");
					q.setParameter(1, aTransaction.getTransactionID());
					q.setParameter(2, "Active");
					q.executeUpdate();
					saveSalesEditGstDetails(aTransaction,aTransaction.getSalestransaction());
					status=updateSalesInvoiceTransaction(aTransaction.getClientID(),aTransaction);
				}
			}
		}catch(Exception e){
			logger.warn("---------------Inside Exception-----------------------"+e.getMessage());
		}
		return status;
	}
	
	@Transactional(value = "transactionManager")
	private String updateSalesTransaction(String clientID,ATransaction aTransaction) {
		logger.info("[updateSalesTransaction()]--------------------Inside updateSalesTransaction() in DaoImpln Calling--------------------------------");
		String status="Fail";SalesAccountsPayment salesaccountPayment=null;Query q=null;
		try{
			SalesTransaction transaction=entitymanager.find(SalesTransaction.class, aTransaction.getTransactionID());
			if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
				aTransaction.setCurrencybeforeTaxAmount(aTransaction.getBeforeTaxAmount());
				aTransaction.setCurrencytaxAmount(aTransaction.getTaxAmount());
			}else{
				aTransaction.setCurrencybeforeTaxAmount(String.valueOf(CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), aTransaction.getBeforeTaxAmount())));
				aTransaction.setCurrencytaxAmount(String.valueOf(CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(), aTransaction.getBaseCurrency(), aTransaction.getTaxAmount())));
			}
			if(aTransaction.getTaxType().equals("Out of scope of Tax") || aTransaction.getTaxType().equals("")){
				aTransaction.setCurrencytaxAmount("0");
			}
			aTransaction.setBalAmount(aTransaction.getCurrencyAmount());
			aTransaction.setSalestransaction(transaction);
			transaction.setUpdatedDate(new Date());
			transaction.setCurrencyType(aTransaction.getCurrencyType());
			transaction.setCustomerName(aTransaction.getCustomerName());
			transaction.setMailId(aTransaction.getCustomerEmailId());
			transaction.setDescription(aTransaction.getDescription());
			transaction.setMailingAddress(aTransaction.getCustomerBillingAddress());
			transaction.setStartDate(aTransaction.getBillDate());
			transaction.setDueDate(aTransaction.getDueDate());
			transaction.setTransactionAmount(aTransaction.getTotalAmount());
			transaction.setBalanceAmount(aTransaction.getBalAmount());
			transaction.setCurrencyAmount(aTransaction.getCurrencyAmount());
			transaction.setCurrencybeforetaxAmount(aTransaction.getCurrencybeforeTaxAmount());
			transaction.setCurrencyTaxAmount(aTransaction.getCurrencytaxAmount());
			transaction.setPlaceofLocation(aTransaction.getLocation());
			transaction.setTaxType(aTransaction.getTaxType());
			transaction.setBeforetaxAmount(aTransaction.getBeforeTaxAmount());
			transaction.setTaxAmount(aTransaction.getTaxAmount());
			transaction.setBaseCurrency(aTransaction.getBaseCurrency());
			transaction.setSubTotalAmount(aTransaction.getSubTotalAmount());
			entitymanager.merge(transaction);
			for (int i = 0; i < aTransaction.getProductList().size(); i++) {
				if(aTransaction.getProductList().get(i).getSalesPaymentID() != 0){
					SalesAccountsPayment payment=entitymanager.find(SalesAccountsPayment.class, aTransaction.getProductList().get(i).getSalesPaymentID());
					payment.setProductName(aTransaction.getProductList().get(i).getProductName());
					payment.setQuantity(aTransaction.getProductList().get(i).getQuantity());
					payment.setRate(aTransaction.getProductList().get(i).getRate());
					payment.setTotalAmount(aTransaction.getProductList().get(i).getAmount());
					payment.setGstAmount(aTransaction.getProductList().get(i).getGstAmount());
					payment.setTaxes(aTransaction.getProductList().get(i).getTaxes());
					payment.setTaxPercentage(aTransaction.getProductList().get(i).getPercentageValue());
					payment.setTaxAmount(aTransaction.getProductList().get(i).getPercentageAmount());
					payment.setIgstType(aTransaction.getProductList().get(i).getGstType());
					payment.setCgstType(aTransaction.getProductList().get(i).getCgstType());
					payment.setSgstType(aTransaction.getProductList().get(i).getSgstType());
					payment.setCgstAmount(aTransaction.getProductList().get(i).getCgstAmount());
					payment.setSgstAmount(aTransaction.getProductList().get(i).getSgstAmount());
					entitymanager.merge(payment);
					entitymanager.flush();
					entitymanager.clear();
				}
				if(aTransaction.getProductList().get(i).getSalesPaymentID() == 0){
					try{
						if(!aTransaction.getProductList().get(i).getProductName().equalsIgnoreCase("select")){
			        		salesaccountPayment=new SalesAccountsPayment(aTransaction.getProductList().get(i),transaction,activeStatus);
			        		entitymanager.persist(salesaccountPayment);
			        	}
					}catch(NullPointerException e){
						e.printStackTrace();
					}
				}
				status="Success";
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("---------------Inside Exception-----------------------"+e.getMessage());
		}finally{
			salesaccountPayment=null;
		}
		return status;
	}
	
	public void saveSalesEditGstDetails(ATransaction aTransaction,SalesTransaction salestransaction){
		logger.info("[saveSalesEditGstDetails()-----------------Inside saveSalesEditGstDetails() in DaoImpln Calling------------------]");
		GstAcct gstAcct2=null;int accounttype_id=0;BigDecimal tempamt=BigDecimal.valueOf(0);List<ChartOfAccount> chartofaccountList=null;
		GstAcct gstAcct=null;GstAcct gstAcct1=null;List<ChartOfAccount> chartofaccountList1=null;List<ChartOfAccount> chartofaccountList2=null;
		ChartOfAccount coas1=null;ChartOfAccount coas2=null;ChartOfAccount coas3=null;
		try{
			for (ATransaction atrans : aTransaction.getProductList()) {
	        	try{
	        		if(!atrans.getProductName().equalsIgnoreCase("select")){
		        		if(!aTransaction.getTaxType().equals("Out of scope of Tax") || !aTransaction.getTaxType().equals("")){
		        			accounttype_id=getaccounttypeid(GSTcategorytype,GSTdetailtype);
		        			aTransaction.setCategoryType(GSTcategorytype);aTransaction.setDetailName(GSTdetailtype);
		        			if(gstList.contains(atrans.getTaxes())){
		        				if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
		        					aTransaction.setTransamount(atrans.getCgstAmount());
		        				}else{
		        					BigDecimal cgstCurrency=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),atrans.getCgstAmount());
		        					aTransaction.setTransamount(String.valueOf(cgstCurrency));
		        				}
		        				if(aTransaction.getTransactionType().equalsIgnoreCase("Credit Memo")){
        	        				aTransaction.setDebitAmount(aTransaction.getTransamount());aTransaction.setCreditAmount("");
        	        				aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getTransamount())).toString());
        	        			}else{
        	        				aTransaction.setDebitAmount("");aTransaction.setCreditAmount(aTransaction.getTransamount());
        	        				aTransaction.setTransamount(aTransaction.getTransamount());
        	        			}
		        				coas1=getSalesChartofAccountID(aTransaction.getClientID(), outputCGSTName, accounttype_id,aTransaction);
        	        			aTransaction.setAccounts(outputCGSTName);aTransaction.setBalance(aTransaction.getTransamount());
        	        			gstAcct1=new GstAcct(salestransaction,aTransaction);
        	        			gstAcct1.setChartOfAccount(entitymanager.find(ChartOfAccount.class, coas1.getChart_of_account_ID()));
        	        			entitymanager.persist(gstAcct1);
        	        			entitymanager.flush();
        	        			entitymanager.clear();
        	        			if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
		        					aTransaction.setTransamount(atrans.getSgstAmount());
		        				}else{
		        					BigDecimal sgstCurrency=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),atrans.getSgstAmount());
		        					aTransaction.setTransamount(String.valueOf(sgstCurrency));
		        				}
        	        			if(aTransaction.getTransactionType().equalsIgnoreCase("Credit Memo")){
        	        				aTransaction.setDebitAmount(aTransaction.getTransamount());aTransaction.setCreditAmount("");
        	        				aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getTransamount())).toString());
        	        			}else{
        	        				aTransaction.setDebitAmount("");aTransaction.setCreditAmount(aTransaction.getTransamount());
        	        				aTransaction.setTransamount(aTransaction.getTransamount());
        	        			}
        	        			coas2=getSalesChartofAccountID(aTransaction.getClientID(), outputSGSTName, accounttype_id,aTransaction);
        	        			aTransaction.setAccounts(outputSGSTName);aTransaction.setBalance(aTransaction.getTransamount());
        	        			gstAcct2=new GstAcct(salestransaction,aTransaction);
        	        			gstAcct2.setChartOfAccount(entitymanager.find(ChartOfAccount.class, coas2.getChart_of_account_ID()));
        	        			entitymanager.persist(gstAcct2);
        	        			entitymanager.flush();
        	        			entitymanager.clear();
		        			}else if(igstList.contains(atrans.getTaxes())){
		        				if(aTransaction.getCurrencyType().equalsIgnoreCase(aTransaction.getBaseCurrency())){
		        					aTransaction.setTransamount(atrans.getPercentageAmount());
		        				}else{
		        					BigDecimal igstCurrency=CurrencyConverter.findExchangeRateAndConvert(aTransaction.getCurrencyType(),aTransaction.getBaseCurrency(),atrans.getPercentageAmount());
		        					aTransaction.setTransamount(String.valueOf(igstCurrency));
		        				}
		        				if(aTransaction.getTransactionType().equalsIgnoreCase("Credit Memo")){
        	        				aTransaction.setDebitAmount(aTransaction.getTransamount());aTransaction.setCreditAmount("");
        	        				aTransaction.setTransamount(tempamt.subtract(new BigDecimal(aTransaction.getTransamount())).toString());
        	        			}else{
        	        				aTransaction.setDebitAmount("");aTransaction.setCreditAmount(aTransaction.getTransamount());
        	        				aTransaction.setTransamount(aTransaction.getTransamount());
        	        			}
		        				coas3=getSalesChartofAccountID(aTransaction.getClientID(), outputIGSTName, accounttype_id,aTransaction);
        	        			aTransaction.setAccounts(outputIGSTName);aTransaction.setBalance(aTransaction.getTransamount());
        	        			gstAcct=new GstAcct(salestransaction,aTransaction);
        	        			gstAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class, coas3.getChart_of_account_ID()));
        	        			entitymanager.persist(gstAcct);
        	        			entitymanager.flush();
        	        			entitymanager.clear();
		        			}
		        			
		        		}
		        	}
	        	}catch(NullPointerException e){
	        		e.printStackTrace();
	        	}
	        }
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("-------------------------Inside Exception------------------"+e.getMessage());
		}
	}
	
	@Override
	public List<SalesTransaction> getsalestransactiontableview(String clientID,ATransaction aTransaction) {
		logger.info("[getsalestransactiontableview()]----------------------Inside getsalestransactiontableview() in DaoImpln Calling------------------------");
		Query q=null;List<SalesTransaction> salestransactionList=null;
		try{
			q=entitymanager.createQuery("from SalesTransaction where client_ID=? and status=? ORDER BY createdDate DESC");
			q.setParameter(1, clientID);
			q.setParameter(2, activeStatus);
			salestransactionList=(List<SalesTransaction>)q.getResultList();
		}catch(Exception e){
			logger.warn("--------------------Inside Exception---------------------------"+e.getMessage());
		}finally{
			q=null;
		}
		return salestransactionList;
	}

	@Override
	public List<SalesTransaction> getcustomersalestransactionList(String clientID,String paymentStatus, ATransaction aTransaction) {
		logger.info("[getcustomersalestransactionList()]----------------------Inside getcustomersalestransactionList() in DaoImpln Calling------------------------");
		Query q=null;List<SalesTransaction> salestransactionList=null;
		try{
			if(paymentStatus.equalsIgnoreCase("payment")){
				q=entitymanager.createQuery("from SalesTransaction where customerName=? and client_ID=? and status=? and (transactionType='Invoice' or transactionType='Delayed Charge' or transactionType='Time Activity' or transactionType='Sales Invoice') and paymentStatus!='Paid'");
				q.setParameter(1, aTransaction.getCustomerName());
			}else if(paymentStatus.equalsIgnoreCase("receive payment")){
				q=entitymanager.createQuery("from SalesTransaction where sales_transaction_ID=? and client_ID=? and status=? and balanceAmount!='0'");
				q.setParameter(1, aTransaction.getTransactionID());
			}
			q.setParameter(2, clientID);
			q.setParameter(3, activeStatus);
			salestransactionList=(List<SalesTransaction>)q.getResultList();
		}catch(Exception e){
			logger.warn("----------------Inside Exception----------------------"+e.getMessage());
		}finally{
			q=null;
		}
		return salestransactionList;
	}
	
	//Code End
	
	//Inventory Sales Code in Accounts by Stanley 
	
	@Transactional(value = "transactionManager")
	public String invoiceSales1(PurchaseOrder purchaseOrder)throws DemoException {
		logger.info("[invoiceSales1()]--------------------------Inside invoiceSales1() in DaoImpln Calling--------------------");
		Query q = null;List<I0021> salesnumberList=null;Query q1 = null;List<I0022> salesIDlist=null;ATransaction aTransaction=null;
		SalesAccountsPayment salesAccountsPayment=null;	SalesTransaction salesTransaction=null;int i = 0;int id = 0;
		I0022 invoice=null;Set<SalesAccountsPayment>salespayments=null;JournalEntry journalEntry=null;
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			q = entitymanager.createQuery("from I0021 where salesOrderNumber=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getSalesIdReference());
			q.setParameter(2, clientID);
			salesnumberList = (List<I0021>) q.getResultList();
			purchaseOrder.setResult(salesnumberList);
			if (salesnumberList.size() > 0) {
				id = salesnumberList.get(i).getSales_ID();
				q1 = entitymanager.createQuery("from I0022 where sales_ID=? ");
				q1.setParameter(1, id);
				salesIDlist = (List<I0022>) q1.getResultList();
				if (salesIDlist.size() > 0) {
					RequestContext.getCurrentInstance().execute("PF('salesinv1').show();");
				}else {
					Date myDate = new Date();
					invoice = new I0022();
					invoice.setI0021(entitymanager.find(I0021.class, id));
					invoice.setApprovalStatus("not submitted");
					invoice.setStatus("Sales Invoice");
					invoice.setInvoiceDate(myDate);
					invoice.setClient_ID(clientID);
					salesTransaction=new SalesTransaction(clientID,purchaseOrder); 
					salespayments=new HashSet<SalesAccountsPayment>();
					for(PurchaseOrder purchase : purchaseOrder.getResulfinal()){ 
						salesAccountsPayment=new SalesAccountsPayment(clientID,purchase,salesTransaction);
						salespayments.add(salesAccountsPayment);
					}
					salesTransaction.setSalesaccountpayments(salespayments); 
					entitymanager.persist(salesTransaction); 
					entitymanager.merge(invoice); 
					journalEntry=new JournalEntry(purchaseOrder,clientID,accrecName,serviceName,salesTransaction,activeStatus);
					aTransaction=new ATransaction();
					aTransaction.setTransamount(purchaseOrder.getCurrencyAmount());aTransaction.setCustomerName(purchaseOrder.getCustomername()); 
					aTransaction.setTransactionType("Sales Invoice");aTransaction.setTransactionNo(purchaseOrder.getSalesIdReference());
					aTransaction.setCurrencyAmount(purchaseOrder.getCurrencyAmount()); aTransaction.setStatus(activeStatus); 
					aTransaction.setCurrencybeforeTaxAmount(purchaseOrder.getCurrencyAmount());
					aTransaction.setBillDate(purchaseOrder.getOrderDate());
					saveSalesInvoiceTransaction(clientID,aTransaction,journalEntry,salesTransaction);
					RequestContext.getCurrentInstance().execute("PF('salesinv').show();");
				}
			}
		} catch (Exception e) {
			logger.warn("-------------------------Inside Exception------------------"+e.getMessage());
		}finally{
			q = null;salesnumberList=null;q1 = null;salesIDlist=null;invoice=null;
		}
		return null;
	}
	
	@Transactional(value = "transactionManager")
	public String updateSales1(PurchaseOrder purchaseOrder)	throws DemoException {
		System.out.println("--------- inside updateSales1 adao method() ----------");String status="";
		Query q  = null;Query q1 = null;Query q5 = null;Query q2 = null;Query q3 = null;Query q4 = null;
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			q = entitymanager.createQuery("from I0021 where salesOrderNumber=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getSalesIdReference());
			q.setParameter(2, clientID);
			ArrayList<I0021> result = (ArrayList<I0021>) q.getResultList();
			Date date=new Date();
			if (result.size() > 0) {
				int salesid = 0;
				String disctype = "";
				String discamnt = "";
				String shipchar = "";
				BigDecimal bb = BigDecimal.valueOf(0);
				disctype = result.get(0).getDisctype();
				discamnt = result.get(0).getDiscamnt();
				shipchar = result.get(0).getShippingCharge();
				salesid = result.get(0).getSales_ID();
				for (int k = 0; k < purchaseOrder.getResult5().size(); k++) {
					q1 = entitymanager.createQuery("from I0018 where productName=? and client_ID=?");
					q1.setParameter(1, purchaseOrder.getResult5().get(k).getProduct_name());
					q1.setParameter(2, clientID);
					ArrayList<I0018> resultproduct = (ArrayList<I0018>) q1.getResultList();
					if (resultproduct.size() > 0) {
						int batchid = 0;
						batchid = resultproduct.get(0).getBatch_ID();
						String sellingprice = "0";
						sellingprice = "" + resultproduct.get(0).getUnitPrice();
						q5 = entitymanager.createQuery("from I0019 where batch_ID=? and status='barcode genterated' ");
						q5.setParameter(1, batchid);
						ArrayList<I0019> resultStock5 = (ArrayList<I0019>) q5.getResultList();
						String stockQuan = "0";
						String saledQuantity = "0";
						int barID = 0;
						if (resultStock5.size() > 0) {
							barID = resultStock5.get(0).getBar_code_ID();
							stockQuan = resultStock5.get(0).getQuantity();
							if (new BigDecimal(stockQuan).compareTo(new BigDecimal(purchaseOrder.getResult5().get(k).getQuantity())) == -1)
							{
								System.out.println("----- inside if updateSales1 dao------");
								status="Fail";
								purchaseOrder.setStatus2(stockQuan); 
								System.out.println("---- status----->"+status); 
							}else {
								System.out.println("----- inside else updateSales1 dao------");
								q3 = entitymanager.createQuery("from SalesRecord where sales_id=? and bar_code_id=? and sold_quantity=?");
								q3.setParameter(1, salesid);
								q3.setParameter(2, barID);
								q3.setParameter(3, purchaseOrder.getResult5().get(k).getQuantity1());
								List<SalesRecord> salerec = (List<SalesRecord>) q3.getResultList();
								if (salerec.size() > 0) {
									saledQuantity = salerec.get(0).getSoldQuantity();
									q2 = entitymanager.createQuery("from I0019 where batch_ID=?");
									q2.setParameter(1, batchid);
									ArrayList<I0019> resultStock = (ArrayList<I0019>) q2.getResultList();
									if (resultStock.size() > 0) {
										String discType = "0";
										String discAmount = "0";
										BigDecimal n1 = BigDecimal.valueOf(0);
										BigDecimal n2 = BigDecimal.valueOf(0);
										BigDecimal n3 = BigDecimal.valueOf(0);
										int batchid2 = 0;
										batchid2 = resultStock.get(0).getBar_code_ID();
										String newQuantity = "0";
										String remQuantity = "0";
										String quantitiez = "0";
										int salrecID = 0;
										salrecID = salerec.get(0).getSalesRecordId();
										discAmount = salerec.get(0).getDiscount();
										discType = salerec.get(0).getDiscountType();
										stockQuan = resultStock.get(0).getQuantity();
										System.out.println("-----amount for check up ----->"+discAmount); 
										System.out.println("-----type for check up ----->"+discType);
										System.out.println("-----quantity for check up ----->"+stockQuan);
										BigDecimal b1 = new BigDecimal(purchaseOrder.getResult5().get(k).getQuantity());
										System.out.println("-----b1 for check up ----->"+b1);
										b1 = b1.setScale(2, RoundingMode.CEILING);
										newQuantity = "" + b1;
										logger.info(" saled Quan --<< " + saledQuantity);
										logger.info("new Quan    --<< " + newQuantity);
										logger.info("stock Quan  --<< " + stockQuan);
										if (new BigDecimal(saledQuantity).compareTo(new BigDecimal(newQuantity)) == -1) {
											remQuantity = ""+ new BigDecimal(newQuantity).subtract(new BigDecimal(saledQuantity));
											quantitiez = ""+ new BigDecimal(stockQuan).subtract(new BigDecimal(remQuantity));
											System.out.println("---------------- check up for total ---------->"+remQuantity); 
											System.out.println("---------------- check up for total 1 ---------->"+quantitiez);
										}
										if (new BigDecimal(newQuantity).compareTo(new BigDecimal(saledQuantity)) == -1) {
											remQuantity = ""+ new BigDecimal(saledQuantity).subtract(new BigDecimal(newQuantity));
											quantitiez = ""+ new BigDecimal(stockQuan).add(new BigDecimal(remQuantity));
											System.out.println("---------------- another check up for total ---------->"+remQuantity); 
											System.out.println("---------------- another check up for total 1 ---------->"+quantitiez);

										}

										I0019 batch = entitymanager.find(I0019.class,batchid2);
										batch.setQuantity(quantitiez);
										entitymanager.merge(batch);
										n1 = new BigDecimal(newQuantity).multiply(new BigDecimal(purchaseOrder.getResult5().get(k).getSellingPrice()));
										System.out.println("---------------- another check up for total n1 ---------->"+n1);
										if (discType.equalsIgnoreCase("RP")) {
											n2 = new BigDecimal(discAmount);
											System.out.println("---------------- another check up for total n2 ---------->"+n2);	
										} else if (discType.equalsIgnoreCase("%")) {
											n2 = ((n1.multiply(new BigDecimal(discAmount))).divide(BigDecimal.valueOf(100)));
											System.out.println("---------------- another else check up for total n2 ---------->"+n2);
										}
										SalesRecord rec = entitymanager.find(SalesRecord.class, salrecID);
										rec.setSoldQuantity(newQuantity);
										rec.setDiscountAmount("" + n2);
										entitymanager.merge(rec);
										q4 = entitymanager.createQuery("from SalesRecord where sales_id=?");
										q4.setParameter(1, salesid);
										List<SalesRecord> salerecz = (List<SalesRecord>) q4.getResultList();
										if (salerecz.size() > 0) {
											String crossAmount = "0";
											String temp = "0";
											String quan = "0";
											String totquan = "0";
											String price = "0";
											String totprice = "0";
											String totdisc = "0";
											discAmount = "";
											n1 = BigDecimal.valueOf(0);
											n2 = BigDecimal.valueOf(0);
											n3 = BigDecimal.valueOf(0);
											for (int i = 0; i < salerecz.size(); i++) {
												quan = salerecz.get(i).getSoldQuantity();
												price = salerecz.get(i).getSell_price();
												discAmount = salerecz.get(i).getDiscountAmount();
												totquan = ""+ (new BigDecimal(totquan).add(new BigDecimal(quan)));
												totprice = ""+ (new BigDecimal(totprice)).add(new BigDecimal(price).multiply(new BigDecimal(quan)));
												totdisc = ""+ new BigDecimal(totdisc).add(new BigDecimal(discAmount));
												System.out.println("-----ano quan for check up ----->"+quan);
												System.out.println("-----ano price for check up ----->"+price);
												System.out.println("-----ano discAmount for check up ----->"+discAmount);
												System.out.println("-----ano totquan for check up ----->"+totquan);
												System.out.println("-----ano totprice for check up ----->"+totprice);
												System.out.println("-----ano totprice for check up ----->"+totdisc);
											}
											n3 = new BigDecimal(totprice).subtract(new BigDecimal(totdisc));
											System.out.println("---------------- another else check up for total n3 ---------->"+n3);
											BigDecimal currAmount=CurrencyConverter.findExchangeRateAndConvert(purchaseOrder.getCurrency(),purchaseOrder.getBaseCurrency(),String.valueOf(n3));
											System.out.println("currency amount"+currAmount);
											purchaseOrder.setCurrencyAmount(String.valueOf(currAmount));
											I0021 sal = entitymanager.find(I0021.class,salesid);
											sal.setCrossTotal(String.valueOf(n3));
											sal.setCurrencyAmount(String.valueOf(currAmount));
											sal.setTotalNumberOfCount(totquan);
											sal.setUpdatedDate(date);
											entitymanager.merge(sal);
										}
										updateSalesinvoice(purchaseOrder,n3);
									}
								}
							}
						}
				}
			}
		}
			
			
	}
		catch(Exception e){
			e.printStackTrace();
			logger.error("inside exception ",e);
		}
		finally {
			q = null;q1 = null;q5 = null;q2 = null;q3 = null;
		}

		return status;
	}
	
	@Transactional(value = "transactionManager")
	public String updateSalesinvoice(PurchaseOrder purchaseOrder, BigDecimal n3){ 
		logger.info("[updateSalesinvoice()]--------------------Inside updateSalesinvoice() in DaoImpln Calling--------------------------------");
		SalesTransaction salesTransaction=new SalesTransaction();List<SalesAccountsPayment>childList=null;String quantity="0";
		Query q=null;List<SalesTransaction>refernceList=null;ATransaction aTransaction=null;String price="0";Query q1=null;
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			q=entitymanager.createQuery("from SalesTransaction where reference_number=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getSalesIdReference()); 
			q.setParameter(2, clientID);
			refernceList=(List<SalesTransaction>)q.getResultList();
			price=purchaseOrder.getResulfinal().get(0).getSellingPrice();quantity=purchaseOrder.getResulfinal().get(0).getQuantity();
			if(refernceList.size() > 0){
				logger.info("[updateSalesinvoice()]--------------------inside if--------------------------------");
				salesTransaction=entitymanager.find(SalesTransaction.class, refernceList.get(0).getSales_transaction_ID());
				salesTransaction.setTransactionAmount(String.valueOf(n3));    
				salesTransaction.setBalanceAmount(String.valueOf(n3));  
				entitymanager.merge(salesTransaction);
				q1=entitymanager.createQuery("from SalesAccountsPayment where sales_transaction_ID=?");
				q1.setParameter(1, refernceList.get(0).getSales_transaction_ID()); 
				childList=(List<SalesAccountsPayment>)q1.getResultList();
				for(int i =0 ; childList.size() > i ; i++){
					SalesAccountsPayment pay=entitymanager.find(SalesAccountsPayment.class, childList.get(i).getSales_account_payments_ID());
					pay.setTotalAmount(String.valueOf(n3)); 
					pay.setQuantity(purchaseOrder.getResulfinal().get(0).getQuantity()); 
					System.out.println("--------- child table set amount------"+pay.getTotalAmount());
					entitymanager.merge(pay);
				}
				aTransaction=new ATransaction();
				aTransaction.setTransamount(String.valueOf(n3));
				aTransaction.setEditTransAmount(purchaseOrder.getResulfinal().get(0).getTotalPrice());
				aTransaction.setTransactionID(refernceList.get(0).getSales_transaction_ID());
				updateSalesInvoiceTransaction(clientID,aTransaction);
			}else{
				logger.info("[updateSalesinvoice()]--------------------inside else--------------------------------");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("---------------Inside Exception-----------------------"+e.getMessage());
		}
		return "";
	}
	
	@Transactional(value = "transactionManager")
	public String invoicePurhcase(PurchaseOrder purchaseOrder)throws DemoException {
		logger.info("[invoicePurhcase()]----------------------Inside invoicePurhcase() in DaoImpln Calling------------------------");
		Query q=null;ArrayList<I0016> result=null;
		try {
			ArrayList<PurchaseOrder> f = new ArrayList<PurchaseOrder>();
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			q = entitymanager.createQuery("from I0016 where ordernumber=? and client_ID=? and status=?");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, clientID);
			q.setParameter(3, "inserted");
			result = (ArrayList<I0016>) q.getResultList();
			int i = 0;
			int id = 0;
			int fId = 0;
			int serialNo = 0;
			if (result.size() > 0) {
				serialNo++;
				id = result.get(i).getHas_purchase_ID();
				fId = result.get(i).getI0015().getPurchase_ID();
				Query q1 = null;
				q1 = entitymanager.createQuery("from I0022 where purchase_ID=?");
				q1.setParameter(1, fId);
				List<I0022> result1 = (List<I0022>) q1.getResultList();
				if (result1.size() > 0) {
					RequestContext.getCurrentInstance().execute("PF('purinv1').show();");
				} else {
					purchaseOrder.setPurchaselist(result);
					for (I0016 i0016 : result) {
						PurchaseOrder purchaseOrder2 = new PurchaseOrder();
						purchaseOrder2.setTotalPrice(""+ new BigDecimal(i0016.getI0031().getI0001().getAutualPrice())
						.multiply(new BigDecimal(i0016.getQuantity())));
						purchaseOrder2.setSerialNo(serialNo);
						f.add(purchaseOrder2);
						serialNo++;
					}
					purchaseOrder.setF(f);
					purchaseOrder.setSerialNo(serialNo);
					Date date = new Date();
					I0022 invoice = new I0022();
					invoice.setI0015(entitymanager.find(I0015.class, fId));
					invoice.setApprovalStatus("not submitted");
					invoice.setStatus("purchase Invoice");
					invoice.setInvoiceDate(date);
					invoice.setClient_ID(clientID);
					entitymanager.persist(invoice);
					purcahseOrderaccounts(purchaseOrder); //stanley changes
					RequestContext.getCurrentInstance().execute("PF('purinv').show();");
				}
			}
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		}
	}
	
	@Transactional(value="transactionManager")
	public String purcahseOrderaccounts(PurchaseOrder purchaseOrder){
		System.out.println("--------------inside purcahseOrderaccounts method--------------");
		ExpenseTransaction expenseTransaction=null;ATransaction aTransaction=null;Set<ExpenseAccountsPayment>expenseAccountsPayments=null;BigDecimal tempamt=BigDecimal.valueOf(0);
		ExpenseAccountsPayment expenseAccountsPayment=null;Set<AccountPayableAcct> accountPayableAccts=null;int accounttype_id=0;JournalEntry journalEntry=null;
		List<ChartOfAccount> chartofaccountList=null;ExpensesAcct expensesAcct=null;AccountPayableAcct accountPayableAcct=null;
		ChartOfAccount chartofaccount=null;Set<ExpensesAcct> expensesAccts=null;;Set<JournalEntry>journalEntries=null;
		try{
			expensesAccts=new HashSet<ExpensesAcct>();accountPayableAccts=new HashSet<AccountPayableAcct>();expenseAccountsPayments=new HashSet<ExpenseAccountsPayment>();
			journalEntries=new HashSet<JournalEntry>();
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			expenseTransaction= new ExpenseTransaction(purchaseOrder,clientID);
			if(purchaseOrder.getResultview().size() > 0){
				for(PurchaseOrder order : purchaseOrder.getResultview()){
					expenseAccountsPayment=new ExpenseAccountsPayment(order,activeStatus,expenseTransaction,clientID);
					expenseAccountsPayments.add(expenseAccountsPayment);
				}
				expenseTransaction.setExpenseAccountsPayments(expenseAccountsPayments); 
			} 
			aTransaction=new ATransaction();
			aTransaction.setBillDate(purchaseOrder.getOrderDate());
			aTransaction.setTotalAmount(purchaseOrder.getResultview().get(0).getNetAmount());
			aTransaction.setAmount(purchaseOrder.getCurrencyAmount());
			aTransaction.setDueDate(purchaseOrder.getTargentDate());
			aTransaction.setVendorName(purchaseOrder.getVendor_phone_number());
			aTransaction.setTransactionType("Purchase Invoice");
			aTransaction.setRefNo(purchaseOrder.getOrderNumber());
			aTransaction.setStatus(activeStatus);
			aTransaction.setClientID(clientID);
			aTransaction.setCurrencyAmount(purchaseOrder.getCurrencyAmount());
			aTransaction.setToAccount(purchaseType);
			journalEntry=new JournalEntry(aTransaction,activeStatus,expenseTransaction,clientID);
			journalEntries.add(journalEntry);
			expenseTransaction.setJournalEntries(journalEntries); 
			accounttype_id=getaccounttypeid(accpayCategoryType,accpayDetailType);
			aTransaction.setTransamount(aTransaction.getCurrencyAmount());
			chartofaccount=getSalesChartofAccountID(aTransaction.getClientID(),accpayName,accounttype_id,aTransaction);
			aTransaction.setDebitAmount("0");
			aTransaction.setTransamount(aTransaction.getCurrencyAmount());
			accountPayableAcct=new AccountPayableAcct(accpayName,aTransaction,expenseTransaction);
			accountPayableAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccount.getChart_of_account_ID()));
			accountPayableAccts.add(accountPayableAcct);
			expenseTransaction.setAccountPayableAccts(accountPayableAccts);
			accounttype_id=0;
			accounttype_id=getaccounttypeid("Expenses",purchaseType);
			chartofaccount=getSalesChartofAccountID(aTransaction.getClientID(),aTransaction.getToAccount(),accounttype_id,aTransaction);
			chartofaccountList=getchartofaccountList(aTransaction.getClientID(),aTransaction.getToAccount());
			if(chartofaccountList.get(0).getAccountType().getCategoryType().equals("Expenses")){
				expensesAcct=new ExpensesAcct(aTransaction,chartofaccountList.get(0).getAccountType().getCategoryType(),chartofaccountList.get(0).getAccountType().getDetailType(),expenseTransaction);
				expensesAcct.setChartOfAccount(entitymanager.find(ChartOfAccount.class,chartofaccountList.get(0).getChart_of_account_ID()));
				expensesAccts.add(expensesAcct);
				expenseTransaction.setExpenseAccts(expensesAccts);
			}
			entitymanager.persist(expenseTransaction);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	@Transactional(value = "transactionManager")
	public String purchaseProductEdit(PurchaseOrder purchaseOrder)throws DemoException {
		Query q2 = null;
		Query q = null;
		Query q3 = null;BigDecimal currAmnt=BigDecimal.valueOf(0);
		System.out.println("'--------------inside purchaseProductEdit method()-----------");
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
			q = entitymanager.createQuery("from I0015 where temOrderNumber=? and status='insert' and client_ID=? and user_ID=?");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, clientID);
			q.setParameter(3, userID);
			ArrayList<I0015> result = (ArrayList<I0015>) q.getResultList();
			int id4 = 0;
			if (result.size() > 0) {
				id4 = result.get(0).getPurchase_ID();
				System.out.println("------id4--->"+id4); 
				q2 = entitymanager.createQuery("from I0001 where productName=? and client_ID=? and user_ID=?");
				q2.setParameter(1, purchaseOrder.getProduct_name());
				q2.setParameter(2, clientID);
				q2.setParameter(3, userID);
				ArrayList<I0001> result1 = (ArrayList<I0001>) q2.getResultList();
				int id1 = 0;
				int id2 = 0;
				int id3 = 0;
				if (result1.size() > 0) {
					id1 = result1.get(0).getProduct_ID();
					System.out.println("------id1--->"+id1);
					Query q4 = null;
					q4 = entitymanager.createQuery("from I0031 where product_ID=?");
					q4.setParameter(1, id1);
					ArrayList<I0031> result3 = (ArrayList<I0031>) q4.getResultList();
					if (result3.size() > 0) {
						id3 = result3.get(0).getHas_vendor_ID();
						System.out.println("------id3--->"+id3);
						q3 = entitymanager.createQuery("from I0016 where ordernumber=? and has_vendor_ID=?");
						q3.setParameter(1, purchaseOrder.getOrderNumber());
						q3.setParameter(2, id3);
						ArrayList<I0016> result2 = (ArrayList<I0016>) q3.getResultList();
						if (result2.size() > 0) {
							Date date=new Date();
							id2 = result2.get(0).getHas_purchase_ID();
							System.out.println("------id2--->"+id2);
							BigDecimal bg = BigDecimal.valueOf(0);
							String temptotal = "";
							String tempQuantity = null;
							String tempQuantity1 = null;
							String tempProductprice = "";
							temptotal = result.get(0).getTotalPrice();
							purchaseOrder.setTotalPrice(temptotal); 
							System.out.println("------temptotal--->"+temptotal);
							System.out.println("------temptotal after set--->"+purchaseOrder.getTotalPrice());
							tempQuantity = result.get(0).getQuantity();
							System.out.println("------tempQuantity--->"+tempQuantity);
							tempProductprice = result1.get(0).getAutualPrice();
							System.out.println("------tempProductprice--->"+tempProductprice);
							tempQuantity1 = result2.get(0).getQuantity();
							System.out.println("------tempQuantity1--->"+tempQuantity1);
							tempQuantity = (Float.parseFloat(tempQuantity) - Float.parseFloat(tempQuantity1)) + "";
							System.out.println("------tempQuantity 1--->"+tempQuantity);
							temptotal = ""+ (new BigDecimal(temptotal).subtract(new BigDecimal(tempProductprice).multiply(new BigDecimal(tempQuantity1))));
							System.out.println("------temptotal 1--->"+temptotal);
							tempQuantity = ""+ (new BigDecimal(tempQuantity).add(new BigDecimal(purchaseOrder.getPurchaseQuantity())));
							System.out.println("------tempQuantity 2--->"+tempQuantity);
							temptotal = ""+ (new BigDecimal(temptotal).add(new BigDecimal(tempProductprice).multiply(new BigDecimal(purchaseOrder.getPurchaseQuantity()))));
							System.out.println("------temptotal 2--->"+temptotal);
							bg = new BigDecimal(tempQuantity).multiply(new BigDecimal(tempProductprice));
							System.out.println("------bg--->"+bg);
							I0015 purchase = entitymanager.find(I0015.class,id4);
							purchase.setQuantity(tempQuantity);
							System.out.println("------qantity set--->"+purchase.getQuantity());
							//String baseCurrency=clientCurrency(clientID);
							if(result.get(0).getCurrencyType().equalsIgnoreCase(baseCurrency)){
								currAmnt=bg;
							}else{
								currAmnt=CurrencyConverter.findExchangeRateAndConvert(result.get(0).getCurrencyType(),baseCurrency,String.valueOf(bg));
							}
							purchase.setCurrency(String.valueOf(currAmnt));
							purchase.setTotalPrice(String.valueOf(bg));
							System.out.println("------totalprize set--->"+purchase.getTotalPrice());
							purchase.setUpdatedDate(date);
							entitymanager.merge(purchase);
							I0016 haspurchase = entitymanager.find(I0016.class,id2);
							haspurchase.setQuantity(purchaseOrder.getPurchaseQuantity());
							entitymanager.merge(haspurchase);
							purchaseEditAccounts(purchaseOrder,temptotal);
						} else {
							logger.info("<--------------inside else------------->");

						}
					}

				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Inside Exception", e);
		}
		return null;
	}
	
	@Transactional(value = "transactionManager")
	public String purchaseEditAccounts(PurchaseOrder purchaseOrder, String temptotal){ 
		logger.info("[purchaseEditAccounts()]----------------------Inside purchaseEditAccounts() in DaoImpln Calling------------------------");
		Query q=null;ExpenseTransaction expenseTransaction=null;List<ExpenseTransaction>expenseTransactions=null;Query q1=null;AccountPayableAcct acc=null;
		List<ExpenseAccountsPayment>childList=null;ExpenseAccountsPayment expenseAccountsPayment=null;ExpensesAcct expenseAcct=null;Query q3=null;
		List<JournalEntry>journalentry=null;JournalEntry journalenry=null;List<AccountPayableAcct>accList=null;String amount="";
		List<ExpensesAcct>expense=null;Query q4=null;ChartOfAccount chartacc=null;ChartOfAccount chartacc1=null;Query q2=null;
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			q=entitymanager.createQuery("from ExpenseTransaction where billNumber=? and client_ID=? and status=?");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, clientID);
			q.setParameter(3, activeStatus);
			expenseTransactions=(List<ExpenseTransaction>)q.getResultList();
			if(expenseTransactions.size() > 0){
				logger.info("[purchaseEditAccounts()]----------------------Inside expenseTransactions if------------------------");
				expenseTransaction=entitymanager.find(ExpenseTransaction.class, expenseTransactions.get(0).getExpense_transaction_ID()); 
				expenseTransaction.setTransactionAmount(temptotal); 
				expenseTransaction.setBalanceAmount(temptotal);
				entitymanager.merge(expenseTransaction); 
				q1=entitymanager.createQuery("from ExpenseAccountsPayment where expense_transaction_ID=? and status=?");
				q1.setParameter(1, expenseTransactions.get(0).getExpense_transaction_ID());
				q1.setParameter(2, activeStatus);
				childList=(List<ExpenseAccountsPayment>)q1.getResultList();
				if(childList.size() > 0){
					logger.info("[purchaseEditAccounts()]----------------------Inside expenseAccountsPayment if------------------------");
					for(int i=0 ; childList.size() > i ; i++){
						expenseAccountsPayment=entitymanager.find(ExpenseAccountsPayment.class, childList.get(i).getExpense_accounts_payments_ID());
						expenseAccountsPayment.setTotalAmount(temptotal); 
						entitymanager.merge(expenseAccountsPayment);
					}
				}else{
					logger.info("[purchaseEditAccounts()]----------------------Inside expenseAccountsPayment else------------------------");
				}
				q2=entitymanager.createQuery("from JournalEntry where expense_transaction_ID=? and status=? and client_ID=?");
				q2.setParameter(1, expenseTransactions.get(0).getExpense_transaction_ID());
				q2.setParameter(2, activeStatus); 
				q2.setParameter(3, clientID);
				journalentry=(List<JournalEntry>)q2.getResultList();
				if(journalentry.size() > 0){
					logger.info("[purchaseEditAccounts()]----------------------Inside journal entry if------------------------");
					journalenry=entitymanager.find(JournalEntry.class, journalentry.get(0).getJournalEntryId());
					journalenry.setDebitAmount(temptotal);
					journalenry.setCreditAmount(temptotal); 
					entitymanager.merge(journalenry);
				}else{
					logger.info("[purchaseEditAccounts()]----------------------Inside journal entry else------------------------");
				}
				q3=entitymanager.createQuery("from AccountPayableAcct where expense_transaction_ID=? and client_ID=? and status=?");
				q3.setParameter(1, expenseTransactions.get(0).getExpense_transaction_ID());
				q3.setParameter(2, clientID);
				q3.setParameter(3, activeStatus);
				accList=(List<AccountPayableAcct>)q3.getResultList();
				if(accList.size() > 0){
					logger.info("[purchaseEditAccounts()]----------------------Inside accountPayableAcct if------------------------");
					acc=entitymanager.find(AccountPayableAcct.class, accList.get(0).getId());
					acc.setCreditAmount(temptotal); 
					acc.setBalance(temptotal); 
					entitymanager.merge(acc);
				}else{
					logger.info("[purchaseEditAccounts()]----------------------Inside accountPayableAcct else------------------------");
				}
				q4=entitymanager.createQuery("from ExpensesAcct where expense_transaction_ID=? and client_ID=? and status=?");
				q4.setParameter(1, expenseTransactions.get(0).getExpense_transaction_ID());
				q4.setParameter(2, clientID);
				q4.setParameter(3, activeStatus);
				expense=(List<ExpensesAcct>)q4.getResultList();
				if(expense.size() > 0){
					logger.info("[purchaseEditAccounts()]----------------------Inside expensesAcct if------------------------");
					expenseAcct=entitymanager.find(ExpensesAcct.class, expense.get(0).getId());
					expenseAcct.setDebitAmount(temptotal); 
					expenseAcct.setBalance(temptotal);
					entitymanager.merge(expenseAcct);
				}else{
					logger.info("[purchaseEditAccounts()]----------------------Inside expensesAcct else------------------------");
				}
				List<ChartOfAccount>chartList=getchartofaccountList(clientID,accpayName);   
				List<ChartOfAccount>chartList1=getchartofaccountList(clientID,purchaseType); 
				System.out.println("----checkup values temp total----->"+temptotal);
				System.out.println("----checkup values before value----->"+purchaseOrder.getTotalAmount());
				if(new BigDecimal(temptotal).compareTo(new BigDecimal(purchaseOrder.getTotalAmount()))==1){
					logger.info("[purchaseEditAccounts()]----------------------Inside chartofaccountList and chartofaccountList1 = 1 if------------------------");
					amount=new BigDecimal(temptotal).subtract(new BigDecimal(purchaseOrder.getTotalAmount())).toString();	
					chartacc=entitymanager.find(ChartOfAccount.class, chartList.get(0).getChart_of_account_ID());
					chartacc.setBalance(new BigDecimal(chartacc.getBalance()).add(new BigDecimal(amount)).toString());
					entitymanager.merge(chartacc);
					chartacc1=entitymanager.find(ChartOfAccount.class, chartList1.get(0).getChart_of_account_ID());
					chartacc1.setBalance(new BigDecimal(chartacc1.getBalance()).add(new BigDecimal(amount)).toString());
					entitymanager.merge(chartacc1);
				}else{
					logger.info("[purchaseEditAccounts()]----------------------Inside chartofaccountList and chartofaccountList1 = 1 else------------------------");
				}
				if(new BigDecimal(temptotal).compareTo(new BigDecimal(purchaseOrder.getTotalAmount()))==-1){
					logger.info("[purchaseEditAccounts()]----------------------Inside chartofaccountList and chartofaccountList1 = -1 if------------------------");
					amount=new BigDecimal(purchaseOrder.getTotalAmount()).subtract(new BigDecimal(temptotal)).toString();	
					chartacc=entitymanager.find(ChartOfAccount.class, chartList.get(0).getChart_of_account_ID());
					chartacc.setBalance(new BigDecimal(chartacc.getBalance()).subtract(new BigDecimal(amount)).toString());
					entitymanager.merge(chartacc);
					chartacc1=entitymanager.find(ChartOfAccount.class, chartList1.get(0).getChart_of_account_ID());
					chartacc1.setBalance(new BigDecimal(chartacc1.getBalance()).subtract(new BigDecimal(amount)).toString());
					entitymanager.merge(chartacc1);
				}else{
					logger.info("[purchaseEditAccounts()]----------------------Inside chartofaccountList and chartofaccountList1 = -1 else------------------------");
				}
			}else{
				logger.info("[purchaseEditAccounts()]----------------------Inside expenseTransactions else------------------------");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.warn("-------------------Inside Exception----------------------------"+e.getMessage());
		}
		return "";
	}
}