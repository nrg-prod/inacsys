package com.inacsys.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.io.ObjectInputStream.GetField;
//import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.context.FacesContext;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;









//import org.omg.PortableInterceptor.DISCARDING;
//import org.primefaces.component.paginator.NextPageLinkRenderer;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inacsys.domain.ATransaction;
import com.inacsys.domain.Approval;
import com.inacsys.domain.Buyer;
import com.inacsys.domain.CategoryRegistration;
import com.inacsys.domain.ClientDataBean;
import com.inacsys.domain.Commission;
import com.inacsys.domain.EmployeeDetail;
import com.inacsys.domain.EmployeePayroll;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.ProductRegister;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.domain.Report1;
import com.inacsys.domain.Sales;
import com.inacsys.domain.StockView;
import com.inacsys.domain.UserCreateDataBean;
import com.inacsys.domain.Vendor;
import com.inacsys.domain.VendorDelete;
import com.inacsys.domain.VendorReport;
import com.inacsys.exception.DemoException;
import com.inacsys.managedBean.ApprovalViewMB;
//import com.inacsys.managedBean.BuyersViewMB;
import com.inacsys.managedBean.EmployeeDetailsViewFormMB;
import com.inacsys.managedBean.ProductViewMB;
import com.inacsys.managedBean.PurchaseOrderFromMB;
import com.inacsys.managedBean.PurchaseReturnFormMB;
import com.inacsys.managedBean.QuickSaleViewMB;
import com.inacsys.managedBean.SalesOrderFormMB;
import com.inacsys.managedBean.SalesViewMB;
//import com.inacsys.managedBean.StockOutFormMB;
//import com.inacsys.managedBean.UploadedImage;
import com.inacsys.managedBean.VendorRegisterFormMB;
//import com.inacsys.managedBean.VendorViewFormMB;
import com.inacsys.shared.AccountType;
import com.inacsys.shared.Code;
import com.inacsys.shared.Indexes;
import com.inacsys.shared.JournalEntry;
import com.inacsys.shared.MemberPayment;
import com.inacsys.shared.Revenue;
import com.inacsys.shared.SalesQuote;
import com.inacsys.shared.Client;
import com.inacsys.shared.CrmCustomerdetail;
import com.inacsys.shared.CrmIndustry;
import com.inacsys.shared.Department;
import com.inacsys.shared.DesignRegister;
import com.inacsys.shared.Designation;
import com.inacsys.shared.Dispatch;
import com.inacsys.shared.Document;
import com.inacsys.shared.Employee;
import com.inacsys.shared.ErrorCode;
import com.inacsys.shared.I0001;
import com.inacsys.shared.I0002;
import com.inacsys.shared.I0004;
import com.inacsys.shared.I0005;
import com.inacsys.shared.I0006;
import com.inacsys.shared.I0015;
import com.inacsys.shared.I0016;
import com.inacsys.shared.I0017;
import com.inacsys.shared.I0018;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0020;
import com.inacsys.shared.I0021;
import com.inacsys.shared.I0022;
import com.inacsys.shared.I0023;
import com.inacsys.shared.I0025;
import com.inacsys.shared.I0026;
import com.inacsys.shared.I0027;
import com.inacsys.shared.I0028;
import com.inacsys.shared.I0031;
import com.inacsys.shared.I0032;
import com.inacsys.shared.I0033;
import com.inacsys.shared.ImagePath;
import com.inacsys.shared.Login;
import com.inacsys.shared.Paymentcash;
import com.inacsys.shared.Payroll;
import com.inacsys.shared.Product;
import com.inacsys.shared.SalesQuoteDetails;
import com.inacsys.shared.PurchaseReturn;
import com.inacsys.shared.Quotation;
import com.inacsys.shared.QuotationDetail;
import com.inacsys.shared.SalesQuote;
import com.inacsys.shared.SalesQuoteDetails;
import com.inacsys.shared.Sequance_number;
import com.inacsys.shared.SalesRecord;
import com.inacsys.shared.SalesReturn;
import com.inacsys.shared.SubProduct;
import com.inacsys.shared.Transaction;
import com.inacsys.shared.UserCreate;
import com.inacsys.shared.UserDepartment;
import com.inacsys.shared.UserProduct;
import com.inacsys.util.AccountsJDBC;
import com.inacsys.util.CurrencyConverter;
import com.inacsys.util.GenerateEmployee;
//import com.inacsys.util.ReportJDBC;
import com.inacsys.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  
 * @author | Created Date | Changed Date | Module |
 * Stantly   20-June-2017 | 20-June-2017 | Sales quote
 * Stantly   30-June-2017 | 30-June-2017 | Approval - sales
 * Prema     30-June-2017 | 30-June-2017 | Balance sheet
 * 
 */


@Repository
@Singleton
public class DemoDaoImpl implements DemoDao {

	final Logger logger = LoggerFactory.getLogger(DemoDaoImpl.class);

	Date date=new Date();
	
	@PersistenceContext(unitName = "inacsys-pu")
	private EntityManager entitymanager;

	@PersistenceContext(unitName = "inacsys-pu")
	private EntityManager entitymanager1;
	
	@Value("${approval.draft.status}" )
	private String approvalStatus;
	
	@Value("${active.status}" )
	private String activeStatus;

	@Transactional(value = "transactionManager")
	public String loginDao(LoginAccess loginaccess) throws DemoException {

		Query q = null;
		String pwd = null;
	//	String p = null;
		q = entitymanager.createQuery("from Login where login_user=? ");
		q.setParameter(1, loginaccess.getUsername());
		if (loginaccess.getUsername().length() == 0) {
			throw new DemoException("Enter UserName And Password");
		}
		List<Login> result = (List<Login>) q.getResultList();

		if (result.size() == 0) {
			throw new DemoException("Invalid UserName");

		} else {

			int out = 0;
			pwd = result.get(out).getLoginPassword();
			if (result.get(out).getStatus().equalsIgnoreCase("active")) {
				loginaccess.setLoginStatus("active");
			} else if (!result.get(out).getStatus().equalsIgnoreCase("active")) {
				loginaccess.setLoginStatus("deActive");
			}

			if (pwd.equalsIgnoreCase(loginaccess.getUserpassword())) {
				Login l = entitymanager.find(Login.class, result.get(out)
						.getLoginId());
				l.setStatus(loginaccess.getStatus());
				entitymanager.merge(l);
			}
		}
		if (!pwd.equalsIgnoreCase(loginaccess.getUserpassword())) {
			throw new DemoException("Invalid Password");
		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public String logoutDao(LoginAccess loginaccess) throws DemoException {

		Query q = null;
		//String pwd = null;
		//String p = null;
		q = entitymanager.createQuery("from Login where login_user=? ");
		q.setParameter(1, loginaccess.getUsername());
		List<Login> result = (List<Login>) q.getResultList();
		int out = 0;
		if (result.size() > 0) {
			Login l = entitymanager.find(Login.class, result.get(out)
					.getLoginId());
			l.setStatus(loginaccess.getStatus());
			entitymanager.merge(l);
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String vendorDao(Vendor vendor) throws DemoException { 
		String status = "Fail";
		Date date=new Date();
		try {
			/*String userID=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			String clientID=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");*/
			logger.info("user id "+vendor.getUserID()+" client id- "+vendor.getClientID());
			I0028 country = new I0028();
			country.setName(vendor.getCountry_ID());
			Query q = null;
			q = entitymanager.createQuery("from I0028 where name=?");
			q.setParameter(1, vendor.getCountry_ID());
			List<I0028> result = (List<I0028>) q.getResultList();
			System.out.println("country id"+vendor.getCountry_ID()+"result size"+result.size());
			int countryID = 0;
			if (result.size() > 0) {
				int i = 0;
				countryID = result.get(i).getCountry_ID();
			}
			q=entitymanager.createQuery("from Paymentcash where paymentType=?");
			q.setParameter(1, vendor.getCash()); 
			List<Paymentcash> result1=(List<Paymentcash>) q.getResultList();
			int type=0;
			if(result1.size() > 0){
				int i = 0;
				type=result1.get(i).getPaymentCashId();
			}
			logger.info("id "+countryID);
			Query q4 = null;
			q4 = entitymanager.createQuery("from I0025 where vendor_phone_number=? and status='i' and client_ID=? and user_ID=?");
			q4.setParameter(1, vendor.getVendorPhoneNumber());
			q4.setParameter(2, vendor.getClientID());
			q4.setParameter(3, vendor.getUserID());
			List<I0025> re = (List<I0025>) q4.getResultList();
			if (re.size() <= 0) {
				I0025 ven = new I0025();
				ven.setFirmName(vendor.getFirmName());
				ven.setFirmRegistrationNumber(vendor.getFirmRegistrationNumber());
				ven.setAddress(vendor.getAddress());
				ven.setVendorTelephoneNumber(vendor.getVendorTelephoneNumber());
				ven.setVendorPhoneNumber(vendor.getVendorPhoneNumber());
				ven.setEmail_ID_vendor(vendor.getEmail_ID_vendor());
				ven.setFaxVendor(vendor.getFaxVendor());
				ven.setPeresonIncharge(vendor.getPeresonIncharge());
				ven.setFirmType(vendor.getFirmTypeStandard());
				ven.setOtherFirmType(vendor.getOtherfirmtype());
				ven.setNatureOfBusiness(vendor.getNatureofbusiness());
				ven.setCityName(vendor.getCity());
				ven.setStateName(vendor.getState());
				ven.setWebsiteAny(vendor.getWebsite());
				ven.setNotesAny(vendor.getNote());
				ven.setCompany(vendor.getVenCompany()); 
				ven.setPresentcity(vendor.getVenCity()); 
				ven.setPresentpostalcode(vendor.getVenPostalcode()); 
				ven.setPresentstate(vendor.getVenState()); 
				ven.setPermenantaddress(vendor.getVenAddress1()); 
				ven.setPermenantcity(vendor.getVenCity1()); 
				ven.setPermenantcountry(vendor.getVenCountry1()); 
				ven.setPermenantpostalcode(vendor.getVenPostalcode1()); 
				ven.setPermenantstate(vendor.getVenState1()); 
				ven.setPresentcountry(vendor.getCountry_ID()); 
				ven.setCreatedDate(date);
				ven.setApprovalStatus("draft");
				ven.setStatus("i");
				ven.setVendorLicenceNumber(vendor.getVenLicence()); 
				ven.setVendorExpireDate(vendor.getVenExdate());
				ven.setVendorType(vendor.getVenType()); 
				ven.setVendorCode(vendor.getVenCode()); 
				ven.setPaymentType(vendor.getPayment()); 
				ven.setClient_ID(vendor.getClientID());
				ven.setI0027(entitymanager.find(I0027.class, 1));
				ven.setI0028(entitymanager.find(I0028.class, countryID));
				ven.setI0026(entitymanager.find(I0026.class, 1)); 
				ven.setUserID(entitymanager.find(UserCreate.class, Integer.parseInt(vendor.getUserID())));
				ven.setPaymentCash_ID(entitymanager.find(Paymentcash.class, type)); 
				entitymanager.persist(ven);
				status = "success";
				int vendor_id=0;
				q=null;
			    q=entitymanager.createQuery("from I0025");
			    List<I0025> resultList=(List<I0025>)q.getResultList();
			    if(resultList.size()>0){
			     vendor_id=resultList.get(resultList.size()-1).getVendor_ID();
			    }
			    String module="Vendor";
			    AccountsJDBC.indexesInsertion(vendor_id,vendor.getVendorTelephoneNumber(),vendor.getVendorPhoneNumber(),vendor.getEmail_ID_vendor(),module,
			    		vendor.getClientID(),vendor.getUserID());
			    q=null;
			    q=entitymanager.createQuery("from I0001 where productName=? and client_ID=? and user_ID=? and status='i' and approvalStatus='Approved'");
			    q.setParameter(1, vendor.getProductName());
			    q.setParameter(2, vendor.getClientID());
			    q.setParameter(3, vendor.getUserID());
			    List<I0001> productList=(List<I0001>)q.getResultList();
			    if(productList.size()>0){
			     I0031 i0031=new I0031();
			     i0031.setStatus("i");
			     i0031.setI0001(entitymanager.find(I0001.class, productList.get(0).getProduct_ID()));
			     i0031.setI0025(entitymanager.find(I0025.class, vendor_id));
			     i0031.setProductPrice(vendor.getProductPrice());
			     entitymanager.persist(i0031);
			     entitymanager.flush();
			     entitymanager.clear();
			    }
			}
		} catch (Exception e) {
			logger.info("Inside Exception");
			logger.error("inside exception ",e);
		}
		return status;
	}

	@Transactional(value = "transactionManager")
	public String countryDrop(Vendor vendor) throws DemoException {
		Query q = null;
		q = entitymanager.createQuery("select name from I0028");
		logger.info("3");
		ArrayList<I0028> result = (ArrayList<I0028>) q.getResultList();
		logger.info("4");
		if (result.size() == 0) {
			throw new DemoException("no country table");
		}
		vendor.setCountrydrop1(result);
		return "success";
	}

	@Transactional(value = "transactionManager")
	public List<I0025> vendorUpdateDao(Vendor vendor, List<I0025> I0025)
			throws DemoException {
		Query q = null;
		/*String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");*/
		q = entitymanager.createQuery("from I0025 where vendor_ID=? and status=?");
		q.setParameter(1, Integer.parseInt(vendor.getVendorPhoneNumber()));
		q.setParameter(2, "i");
		I0025 = (List<I0025>) q.getResultList();
		return I0025;
	}

	@Transactional(value = "transactionManager")
	public String vendorDeleteDao(VendorDelete vendorDelete)
			throws DemoException {
		//String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		/*Query q = null;
		q = entitymanager
				.createQuery("from I0025 where vendor_ID=? and status='i' and client_ID=?");
		q.setParameter(1, Integer.parseInt(vendorDelete.getVendorPhoneNumber()));
		q.setParameter(2, vendorDelete.getClientID());
		List<I0025> result = (List<I0025>) q.getResultList();
		int vendorID = 0;
		if (result.size() > 0) {
			int i = 0;
			vendorID = result.get(i).getVendor_ID();
		}*/
		I0025 ID = entitymanager.find(I0025.class, Integer.parseInt(vendorDelete.getVendorPhoneNumber()));
		ID.setStatus("deleted");
		entitymanager.merge(ID);
		vendorDelete.setStatus("Success");
		return null;
	}

	@Transactional(value = "transactionManager")
	public String vendorModify(Vendor vendor, List<I0025> xx)
			throws DemoException {
		//String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		q = entitymanager.createQuery("from I0028 where name=? ");
		q.setParameter(1, vendor.getCountry_ID());
		List<I0028> result = (List<I0028>) q.getResultList();
		int countryID = 0;
		if (result.size() > 0) {
			int i1 = 0;
			countryID = result.get(i1).getCountry_ID();
		}
		
		q=entitymanager.createQuery("from Paymentcash where paymentType=? ");
		q.setParameter(1, vendor.getPayDays());  
		List<Paymentcash> result1=(List<Paymentcash>) q.getResultList();
		int type=0;
		if(result1.size() > 0){
			int i = 0;
			type=result1.get(i).getPaymentCashId();
		}
		
		
		
		Query q4 = null;
		q4 = entitymanager
				.createQuery("from I0025 where vendor_ID=? and status=?");
		q4.setParameter(1, vendor.getVendor_Id());
		q4.setParameter(2, "i");
		List<I0025> r = (List<I0025>) q4.getResultList();
		int id = 0;
		if (r.size() > 0) {
			int i = 0;
			id = r.get(id).getVendor_ID();
			if (r.get(i).getVendorPhoneNumber().equalsIgnoreCase(xx.get(0).getVendorPhoneNumber())) {
				if (r.get(i).getVendorPhoneNumber()
						.equalsIgnoreCase(vendor.getVendorPhoneNumber())) {
					I0025 vend = entitymanager.find(I0025.class,vendor.getVendor_Id());
					vend.setFirmName(vendor.getFirmName());
					vend.setFirmRegistrationNumber(vendor.getFirmRegistrationNumber());
					
					vend.setVendorTelephoneNumber(vendor.getVendorTelephoneNumber());
					vend.setVendorPhoneNumber(vendor.getVendorPhoneNumber());
					vend.setOtherFirmType(vendor.getOtherfirmtype());
					vend.setNatureOfBusiness(vendor.getNatureofbusiness());			
					vend.setWebsiteAny(vendor.getWebsite()); 
					vend.setNotesAny(vendor.getNote());
					vend.setI0028(entitymanager.find(I0028.class, countryID));
					vend.setPaymentCash_ID(entitymanager.find(Paymentcash.class, type));
					vend.setEmail_ID_vendor(vendor.getEmail_ID_vendor());
					vend.setFaxVendor(vendor.getFaxVendor());
					vend.setPeresonIncharge(vendor.getPeresonIncharge());
					vend.setFirmType(vendor.getFirmTypeStandard());
					vend.setPaymentType(vendor.getPayType()); 
					vend.setAddress(vendor.getAddress()); 
					vend.setPermenantaddress(vendor.getVenAddress1()); 
					vend.setPresentcity(vendor.getVenCity()); 
					vend.setPermenantcity(vendor.getVenCity1()); 
					vend.setPresentcountry(vendor.getCountry_ID()); 
					vend.setPermenantcountry(vendor.getVenCountry1()); 
					vend.setPresentpostalcode(vendor.getVenPostalcode()); 
					vend.setPermenantpostalcode(vendor.getVenPostalcode1()); 
					vend.setPresentstate(vendor.getVenState()); 
					vend.setPermenantstate(vendor.getVenState1()); 
					vend.setCompany(vendor.getVenCompany());
					vend.setVendorLicenceNumber(vendor.getVenLicence()); 
					vend.setVendorExpireDate(vendor.getVenExdate()); 
					vend.setVendorType(vendor.getVenType()); 
					vend.setVendorCode(vendor.getVenCode()); 
					vend.setUpdatedDate(date);
					logger.debug("Expire date----------->"+vend.getVendorExpireDate());  
					entitymanager.merge(vend);
					vendor.setStatus("Success");
				} else if (!r.get(i).getVendorPhoneNumber().equalsIgnoreCase(vendor.getVendorPhoneNumber())) {
					I0025 vend = entitymanager.find(I0025.class,
							vendor.getVendor_Id());
					logger.debug("Address ----------->"+vendor.getAddress());
					vend.setFirmName(vendor.getFirmName());
					vend.setFirmRegistrationNumber(vendor
							.getFirmRegistrationNumber());
					
					vend.setVendorTelephoneNumber(vendor
							.getVendorTelephoneNumber());
					vend.setVendorPhoneNumber(vendor.getVendorPhoneNumber());
					
					
					vend.setWebsiteAny(vendor.getWebsite());
					vend.setNotesAny(vendor.getNote());
					vend.setI0028(entitymanager.find(I0028.class, countryID));
					vend.setPaymentCash_ID(entitymanager.find(Paymentcash.class, type));
					vend.setOtherFirmType(vendor.getOtherfirmtype());
					vend.setNatureOfBusiness(vendor.getNatureofbusiness());
					vend.setEmail_ID_vendor(vendor.getEmail_ID_vendor());
					vend.setFaxVendor(vendor.getFaxVendor());
					vend.setPresentcountry(vendor.getCountry_ID()); 
					vend.setPermenantcountry(vendor.getVenCountry1()); 
					vend.setPeresonIncharge(vendor.getPeresonIncharge());
					vend.setFirmType(vendor.getFirmTypeStandard());
					vend.setPaymentType(vendor.getPayType()); 
					vend.setAddress(vendor.getAddress()); 
					vend.setPermenantaddress(vendor.getVenAddress1()); 
					vend.setPresentcity(vendor.getVenCity()); 
					vend.setPermenantcity(vendor.getVenCity1());  
					vend.setPresentpostalcode(vendor.getVenPostalcode()); 
					vend.setPermenantpostalcode(vendor.getVenPostalcode1()); 
					vend.setPresentstate(vendor.getVenState()); 
					vend.setPermenantstate(vendor.getVenState1()); 
					vend.setCompany(vendor.getVenCompany());
					vend.setVendorLicenceNumber(vendor.getVenLicence()); 
					vend.setVendorExpireDate(vendor.getVenExdate()); 
					vend.setVendorType(vendor.getVenType()); 
					vend.setVendorCode(vendor.getVenCode());
					vend.setUpdatedDate(date);
					entitymanager.merge(vend);
					vendor.setStatus("Success");
				} else {
					throw new DemoException("*Firm Name Already Present:::::");
				}
			}
		} else {
			I0025 vend = entitymanager.find(I0025.class, vendor.getVendor_Id());
			vend.setFirmName(vendor.getFirmName());
			vend.setFirmRegistrationNumber(vendor.getFirmRegistrationNumber());			
			vend.setVendorTelephoneNumber(vendor.getVendorTelephoneNumber());
			vend.setVendorPhoneNumber(vendor.getVendorPhoneNumber());			
			vend.setWebsiteAny(vendor.getWebsite());
			vend.setNotesAny(vendor.getNote());
			vend.setI0028(entitymanager.find(I0028.class, countryID));
			vend.setPaymentCash_ID(entitymanager.find(Paymentcash.class, type));
			vend.setOtherFirmType(vendor.getOtherfirmtype());
			vend.setNatureOfBusiness(vendor.getNatureofbusiness());
			vend.setEmail_ID_vendor(vendor.getEmail_ID_vendor());
			vend.setFaxVendor(vendor.getFaxVendor());
			vend.setPresentcountry(vendor.getCountry_ID()); 
			vend.setPermenantcountry(vendor.getVenCountry1()); 
			vend.setPeresonIncharge(vendor.getPeresonIncharge());
			vend.setFirmType(vendor.getFirmTypeStandard());
			vend.setAddress(vendor.getAddress()); 
			vend.setPermenantaddress(vendor.getVenAddress1()); 
			vend.setPresentcity(vendor.getVenCity()); 
			vend.setPermenantcity(vendor.getVenCity1());  
			vend.setPresentpostalcode(vendor.getVenPostalcode()); 
			vend.setPermenantpostalcode(vendor.getVenPostalcode1()); 
			vend.setPresentstate(vendor.getVenState()); 
			vend.setPermenantstate(vendor.getVenState1()); 
			vend.setPaymentType(vendor.getPayType()); 	
			vend.setCompany(vendor.getVenCompany());
			vend.setVendorLicenceNumber(vendor.getVenLicence()); 
			vend.setVendorExpireDate(vendor.getVenExdate()); 
			vend.setVendorType(vendor.getVenType()); 
			vend.setVendorCode(vendor.getVenCode());	
			vend.setUpdatedDate(date);
			entitymanager.merge(vend);
			vendor.setStatus("Success");
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String dropDown(ProductRegister productRegister,
			List<I0004> typeparent, List<I0002> productgroup,
			List<I0006> industryList) throws DemoException {
		Query q = null;
		q = entitymanager.createQuery("from I0004");
		typeparent = (List<I0004>) q.getResultList();
		Query q1 = null;
		q1 = entitymanager.createQuery("from I0002");
		productgroup = (List<I0002>) q1.getResultList();
		Query q2 = null;
		q2 = entitymanager.createQuery("from I0006");
		industryList = (List<I0006>) q2.getResultList();

		return "";
	}

	public List<I0004> dropDown(List<I0004> typeparent) throws DemoException {
		Query q = null;
		q = entitymanager.createQuery("select name from I0004 ");
		typeparent = q.getResultList();
		return typeparent;
	}

	public List<I0002> dropDow(List<I0002> productgroup) throws DemoException {
		Query q1 = null;
		q1 = entitymanager.createQuery("select productGroupName from I0002");
		productgroup = q1.getResultList();
		return productgroup;
	}

	public List<I0006> dropDo(List<I0006> industryList) throws DemoException {
		Query q2 = null;
		q2 = entitymanager.createQuery("select industryName from I0006");
		industryList = q2.getResultList();
		return industryList;
	}

	public List<String> dropD(List<String> ven) throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		q = entitymanager.createQuery("select vendorPhoneNumber from I0025 where status=? and client_ID=? and user_ID=? and approvalStatus='Approved'");
		q.setParameter(1, "i");
		q.setParameter(2, clientID);
		q.setParameter(3, userID);
		ven = q.getResultList();
		return ven;
	}

	@Transactional(value = "transactionManager")
	public String saveProductRegister(ProductRegister productRegister)throws DemoException, ParseException {
		String barno = null;
		String status = "Fail";
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		Query q = null;
		q = entitymanager.createQuery("from I0002 where productGroupName=?");
		q.setParameter(1, productRegister.getProduct_group());
		List<I0002> result = (List<I0002>) q.getResultList();
		int country = 0;
		if (result.size() > 0) {
			int i = 0;
			country = result.get(i).getProduct_group_ID();
		}
		Query q1 = null;
		q1 = entitymanager.createQuery("from I0004 where name=?");
		q1.setParameter(1, productRegister.getType_parent());
		List<I0004> resul = (List<I0004>) q1.getResultList();
		int typeID = 0;
		if (resul.size() > 0) {
			int i = 0;
			typeID = resul.get(i).getType_parent_ID();
		}
		Query c = null;
		c = entitymanager.createQuery("from I0005 where categoryType=? and client_ID=? and user_ID=? and status='Active'");
		c.setParameter(1, productRegister.getCategory());
		c.setParameter(2, clientID);
		c.setParameter(3, userID);
		List<I0005> category = (List<I0005>) c.getResultList();
		int catid = 0;
		if (category.size() > 0) {
			catid = category.get(0).getCategoryId();
		}
		Query qc = null;
		qc = entitymanager.createQuery("from I0001 where productWeight=? and status='i' and client_ID=? and user_ID=?");
		qc.setParameter(1, productRegister.getProduct_weight());
		qc.setParameter(2, clientID);
		qc.setParameter(3, userID);
		List<I0001> codelis = (List<I0001>) qc.getResultList();
		if(codelis.size()>0){
			status="codeExist";
		}
		Query q4 = null;
		q4 = entitymanager.createQuery("from I0001 where productName=? and status='i' and client_ID=? and user_ID=?");
		q4.setParameter(1, productRegister.getProductName());
		q4.setParameter(2, clientID);
		q4.setParameter(3, userID);
		List<I0001> lis = (List<I0001>) q4.getResultList();
		String productname = null;
		if (lis.size() > 0) {
			productname = lis.get(0).getProductName();
			if (productname.equalsIgnoreCase(productRegister.getProductName())) {
				throw new DemoException("This product is already present");
			}
		} else if (productRegister.getProductName() == "") {
			throw new DemoException("Enter the Product Name");
		} else {
			int count = 0;
			if (lis.size() > 0) {
				for (I0001 re : lis) {
					count++;
				}
			}
			if (count == 0) {
				count++;
				String a = df + "PRN0000" + count;
				productRegister.setRefcode(a);
			} else {
				count++;
				String a = df + "PRN0000" + count;
				productRegister.setRefcode(a);
			}
			Query qq = null;
			List<I0001> value = null;
			qq = entitymanager.createQuery("from I0001 where status='i' and client_ID=? and user_ID=?");
			qq.setParameter(1, clientID);
			qq.setParameter(2, userID);
			value = (List<I0001>) qq.getResultList();
			try {
				if (value.size() > 0) {
					int i = value.get(value.size() - 1).getProduct_ID();
					int ii = value.get(value.size() - 1).getProduct_ID() + 1;
					if (ii <= 9) {
						barno = productRegister.getBarcode()
								+ "0"
								+ (value.get(value.size() - 1).getProduct_ID() + 1);
					} else {
						barno = productRegister.getBarcode()
								+ (value.get(value.size() - 1).getProduct_ID() + 1);
					}
					String b = "" + value.size();
					if (b.length() < 1) {
						String a = "" + 0;
						barno = a + barno;
					} else {
						barno = barno.substring(0, 12);
					}
				} else {
					barno = productRegister.getBarcode() + "01";
				}
				I0001 productt = new I0001();
				productt.setStatus("i");
				productt.setProductName(productRegister.getProductName());
				productt.setProduct_ID(productRegister.getProduct_ID());
				productt.setAutualPrice(productRegister.getAutual_price());
				productt.setMarketPrice(productRegister.getMarket_price());
				productt.setSellingPrice(productRegister.getSellingPrice());
				productt.setMarginPrice(productRegister.getMargin_price());
				productt.setCreateDate(date);
				productt.setBarCode(barno);
				productt.setBatch(productRegister.getBatch());
				productt.setDescription(productRegister.getDescription());
				productt.setColor(productRegister.getColor());
				productt.setSizeOfProduct(productRegister.getSize_of_product());
				productt.setProductStandard(productRegister.getProduct_standard());
				productt.setClient_ID(clientID);
				productt.setBrand(productRegister.getBrand());
				productt.setIdealFor(productRegister.getIdeal_for());
				productt.setI0004(entitymanager.find(I0004.class, typeID));
				productt.setProductWeight(productRegister.getProductlimit());
				productt.setUnit(productRegister.getUnit());
				productt.setI0005(entitymanager.find(I0005.class, catid));
				productt.setUserID(entitymanager.find(UserCreate.class, Integer.parseInt(userID)));
				productt.setCreatedDate(date);
				productt.setApprovalStatus("draft");
				entitymanager.persist(productt);
				Query q5 = null;
				q5 = entitymanager
						.createQuery("from I0001 where productName=? and status='i' and client_ID=? and user_ID=?");
				q5.setParameter(1, productRegister.getProductName());
				q5.setParameter(2, clientID);
				q5.setParameter(3, userID);
				List<I0001> reu = (List<I0001>) q5.getResultList();
				int productforigen = reu.get(0).getProduct_ID();
				Query q6 = null;
				q6 = entitymanager
						.createQuery("from I0025 where vendorPhoneNumber=? and status='i' and client_ID=? and user_ID=?");
				q6.setParameter(1, productRegister.getIndustry());
				q6.setParameter(2, clientID);
				q6.setParameter(3, userID);
				List<I0025> reus = (List<I0025>) q6.getResultList();
				int vendorforigen = reus.get(0).getVendor_ID();
				I0031 i0031 = new I0031();
				i0031.setI0001(entitymanager.find(I0001.class, productforigen));
				i0031.setI0025(entitymanager.find(I0025.class, vendorforigen));
				i0031.setProductPrice(productRegister.getAutual_price());
				i0031.setStatus("i");
				entitymanager.persist(i0031);
				logger.info("5");
				logger.info("successfully executed");
				status = "success";

			} catch (Exception e) {
							logger.error("Error --------------->"+e.getMessage());
				logger.info("Inside Exception");
			}
		}
		return status;
	}

	@Transactional(value = "transactionManager")
	public String saveProductEdit(List<I0001> i0001s,
			ProductRegister productRegister) throws Exception {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		q = entitymanager.createQuery("from I0002 where productGroupName=?");
		q.setParameter(1, productRegister.getProduct_group());
		List<I0002> result = (List<I0002>) q.getResultList();
		int country = 0;
		if (result.size() > 0) {
			int i = 0;
			country = result.get(i).getProduct_group_ID();
		}
		Query q1 = null;
		q1 = entitymanager.createQuery("from I0004 where name=?");
		q1.setParameter(1, productRegister.getType_parent());
		List<I0004> resul = (List<I0004>) q1.getResultList();
		int typeID = 0;
		if (resul.size() > 0) {
			int i = 0;
			typeID = resul.get(i).getType_parent_ID();
		}
		Query q5 = null;
		q5 = entitymanager.createQuery("from I0005 where categoryType=? and client_ID=? and user_ID=?");
		q5.setParameter(1, productRegister.getCategory());
		q5.setParameter(2, clientID);
		q5.setParameter(3, userID);
		List<I0005> re = (List<I0005>) q5.getResultList();
		int cId = 0;
		if (re.size() > 0) {
			cId = re.get(0).getCategoryId();
		}
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		int count=0;
		if(productRegister.getProductName().equals(productRegister.getNewProductName())){
			count++;
		}
		else{
			Query q3 = null;
			q3=entitymanager.createQuery("from I0018 where productName=? and client_ID=?");
			q3.setParameter(1, productRegister.getNewProductName());
			q3.setParameter(2, clientID);
			List<I0018> r1=(List<I0018>)q3.getResultList();
			if(r1.size()>0){
				logger.info("---------------------- This product is stock in ------------------------");
				throw new DemoException("This product stocked in so can't edit Product Name");
			}
			else{
				count++;
			}
		}
		if(count>0){
			Query q4 = null;
			q4 = entitymanager.createQuery("from I0001 where productName=? and client_ID=? and user_ID=?");
			q4.setParameter(1, productRegister.getProductName());
			q4.setParameter(2, clientID);
			q4.setParameter(3, userID);
			List<I0001> r = (List<I0001>) q4.getResultList();
			int id = 0;
			int i = 0;
			if (r.size() > 0) {
				id = r.get(i).getProduct_ID();
				if (r.get(i).getProductName()
						.equalsIgnoreCase(i0001s.get(0).getProductName())) {
					if (r.get(i).getProductName()
							.equalsIgnoreCase(productRegister.getProductName())) {
						I0001 productt = entitymanager.find(I0001.class, i0001s
								.get(0).getProduct_ID());
						productt.setStatus("i");
						productt.setProductName(productRegister.getProductName());
						productt.setAutualPrice(productRegister.getAutual_price());
						productt.setMarketPrice(productRegister.getMarket_price());
						productt.setSellingPrice(productRegister.getSellingPrice());
						productt.setMarginPrice(productRegister.getMargin_price());
						productt.setBatch(productRegister.getBatch());
						productt.setDescription(productRegister.getDescription());
						productt.setColor(productRegister.getColor());
						productt.setSizeOfProduct(productRegister
								.getSize_of_product());
						productt.setI0005(entitymanager.find(I0005.class, cId));
						productt.setProductStandard(productRegister
								.getProduct_standard());
						productt.setBrand(productRegister.getBrand());
						productt.setIdealFor(productRegister.getIdeal_for());
						productt.setI0004(entitymanager.find(I0004.class, typeID));
						productt.setProductWeight(productRegister.getProductlimit());
						productt.setUnit(productRegister.getUnit());
						productt.setUpdatedDate(date);
						entitymanager.merge(productt);
					} else if (!r.get(i).getProductName()
							.equalsIgnoreCase(productRegister.getProductName())) {
						I0001 productt = entitymanager.find(I0001.class, i0001s
								.get(0).getProduct_ID());
						logger.info("13");
						productt.setStatus("i");
						productt.setProductName(productRegister.getProductName());
						productt.setAutualPrice(productRegister.getAutual_price());
						productt.setMarketPrice(productRegister.getMarket_price());
						productt.setBatch(productRegister.getBatch());
						productt.setDescription(productRegister.getDescription());
						productt.setColor(productRegister.getColor());
						productt.setSizeOfProduct(productRegister
								.getSize_of_product());
						productt.setI0005(entitymanager.find(I0005.class, cId));
						productt.setProductStandard(productRegister
								.getProduct_standard());
						productt.setBrand(productRegister.getBrand());
						productt.setIdealFor(productRegister.getIdeal_for());
						productt.setI0004(entitymanager.find(I0004.class, typeID));
						productt.setProductWeight(productRegister.getProductlimit());
						productt.setUnit(productRegister.getUnit());
						productt.setUpdatedDate(date);
						entitymanager.merge(productt);

					} else {
						throw new Exception("*Product Name Already Present:::::");
					}

				} else {
					throw new Exception("*Product Name Already Present");
				}

			} else {
				I0001 productt = entitymanager.find(I0001.class, i0001s.get(0)
						.getProduct_ID());
				productt.setStatus("i");
				productt.setProductName(productRegister.getProductName());
				productt.setAutualPrice(productRegister.getAutual_price());
				productt.setMarketPrice(productRegister.getMarket_price());
				productt.setBatch(productRegister.getBatch());
				productt.setDescription(productRegister.getDescription());
				productt.setColor(productRegister.getColor());
				productt.setSizeOfProduct(productRegister.getSize_of_product());
				productt.setI0005(entitymanager.find(I0005.class, cId));
				productt.setProductStandard(productRegister.getProduct_standard());
				productt.setBrand(productRegister.getBrand());
				productt.setIdealFor(productRegister.getIdeal_for());
				productt.setI0004(entitymanager.find(I0004.class, typeID));
				productt.setProductWeight(productRegister.getProductlimit());
				productt.setUnit(productRegister.getUnit());
				productt.setUpdatedDate(date);
				entitymanager.merge(productt);
			}
		}
		return "";
	}

	
	@Transactional(value = "transactionManager")
	public List<I0001> productView(List<I0001> i0001s,
			ProductRegister productRegister) throws DemoException {
		Query q = null;
		//String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		productRegister.setFileName("");
		q = entitymanager.createQuery("from I0001 where product_ID=?");
		q.setParameter(1, productRegister.getProduct_ID());
		i0001s = (List<I0001>) q.getResultList();
		if (i0001s.size() <= 0) {
			throw new DemoException("invalid product");
		} else {
			productRegister.setVendor(i0001s.get(0).getI0031s().get(0)
					.getI0025().getVendorPhoneNumber());
			Query q4 = null;
			int pid = 0;
			pid = i0001s.get(0).getProduct_ID();
			q4 = entitymanager
					.createQuery("from ImagePath where product_id=? and status2='finalized'");
			q4.setParameter(1, pid);
			List<ImagePath> list3 = (List<ImagePath>) q4.getResultList();
			if (list3.size() > 0) {
				productRegister.setFileName(list3.get(0).getStatus());
				productRegister.setDesc(list3.get(0).getDescription());
			} else {
				Query qq = null;
				qq = entitymanager
						.createQuery("from DesignRegister where product_id=? and status2='inserted' ");
				qq.setParameter(1, pid);
				List<DesignRegister> list5 = (List<DesignRegister>) qq
						.getResultList();
				if (list5.size() > 0) {
					productRegister.setFileName(list5.get(0).getFilePath());
					productRegister.setDesc(list5.get(0).getStatus());
				} else {
					productRegister.setFileName("");
				}
			}

		}
		return i0001s;
	}

	@Transactional(value = "transactionManager")
	public String productReject(ProductRegister productRegister)
			throws DemoException {
		try {
			String s = "" + productRegister.getProduct_ID();
			I0001 reject = entitymanager.find(I0001.class,
					productRegister.getProduct_ID());
			reject.setStatus("reject");
			entitymanager.merge(reject);
			entitymanager.close();
			Query query = null;
			query = entitymanager.createQuery("from I0031 where product_ID=?");
			query.setParameter(1, productRegister.getProduct_ID());
			ArrayList<I0031> result = (ArrayList<I0031>) query.getResultList();
			int pid = 0;
			if (result.size() > 0) {
				for (I0031 r : result) {
					pid = r.getHas_vendor_ID();
					I0031 many = entitymanager.find(I0031.class, pid);
					many.setStatus("reject");
					entitymanager.merge(many);
				}
			}
			return "sucess";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		}

	}

	public String reject2(String s) {
		try {

			Query q = null;
			q = entitymanager.createQuery("from I0031 where product_ID=?");
			q.setParameter(1, s);
			ArrayList<I0031> result = (ArrayList<I0031>) q.getResultList();
			int pid = 0;
			if (result.size() > 0) {
				for (I0031 r : result) {
					pid = r.getI0025().getVendor_ID();
					I0031 reject1 = entitymanager.find(I0031.class, pid);
					reject1.setStatus("");
					entitymanager.persist(reject1);
				}
			} else {
				throw new DemoException("Please check it::::::::");
			}

		} catch (Exception e) {
			logger.error("Error --------------->"+e.getMessage());
		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String productRemove(ProductRegister productRegister)
			throws DemoException {
		String s = "" + productRegister.getProduct_ID();
		I0001 reject = entitymanager.find(I0001.class,
				productRegister.getProduct_ID());
		reject.setStatus("romoved");
		entitymanager.merge(reject);
		entitymanager.close();
		Query query = null;
		query = entitymanager.createQuery("from I0031 where product_ID=?");
		query.setParameter(1, productRegister.getProduct_ID());
		ArrayList<I0031> result = (ArrayList<I0031>) query.getResultList();
		int pid = 0;
		if (result.size() > 0) {
			for (I0031 r : result) {
				pid = r.getHas_vendor_ID();
				I0031 many = entitymanager.find(I0031.class, pid);
				many.setStatus("romoved");
				entitymanager.merge(many);
			}
		}
		return "sucess";
	}

	@Transactional(value = "transactionManager")
	public List<I0001> autoComplete(List<I0001> auto,
			ProductRegister productRegister) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			String i = "1";
			Query q = null;
			q = entitymanager.createQuery("SELECT productName FROM I0001 and client_ID=? ");
			q.setParameter(1, clientID);
			auto = (List<I0001>) q.getResultList();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return auto;
	}

	@Transactional(value = "transactionManager")
	public List<I0025> purchaseDrop(List<I0025> drop,
			PurchaseOrder purchaseOrder) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		try {
			Query q = null;
			q = entitymanager
					.createQuery("select vendorPhoneNumber from I0025 where status=? and client_ID=? and user_ID=? and approvalStatus='Approved'");
			q.setParameter(1, "i");
			q.setParameter(2, clientID);
			q.setParameter(3, userID);
			drop = (List<I0025>) q.getResultList();
		} catch (Exception e) {
			logger.error("Error --------------->"+e.getMessage());
		}
		return drop;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<String> changeDrop(String s, ArrayList<String> productlist)
			throws DemoException {
		ArrayList<String> product = new ArrayList<String>();
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		try {
			Query q = null;
			q = entitymanager
					.createQuery("from I0025 where vendorPhoneNumber=? and client_ID=? and user_ID=?");
			q.setParameter(1, s);
			q.setParameter(2, clientID);
			q.setParameter(3, userID);
			List<I0025> result = (List<I0025>) q.getResultList();
			int fID = 0;
			if (result.size() > 0) {
				int j = 0;
				fID = result.get(j).getVendor_ID();
			}
			String a = "" + fID;
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0031 where vendor_ID=? and status=?");
			q1.setParameter(1, a);
			q1.setParameter(2, "i");
			List<I0031> resul = (List<I0031>) q1.getResultList();
			for (I0031 re : resul) {
				if(re.getI0001().getStatus().equals("i")){
					product.add(re.getI0001().getProductName());
				}				
			}
		} catch (Exception e) {
			logger.error("Error --------------->"+e.getMessage());
		}

		return product;
	}

	@Transactional(value = "transactionManager")
	public String changeFirmName(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q = null;
			q = entitymanager
					.createQuery("from I0025 where vendorPhoneNumber=? and client_ID=? ");
			q.setParameter(1, purchaseOrder.getVendor_phone_number());
			q.setParameter(2, clientID);
			List<I0025> result = (List<I0025>) q.getResultList();
			if (result.size() > 0) {
				purchaseOrder.setFirmName(result.get(0).getFirmName());
			}
		} catch (Exception e) {
			logger.error("Error --------------->"+e.getMessage());
		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String changeDrop1(String s) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String s1 = null;
		try {
			Query q = null;
			q = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
			q.setParameter(1, s);
			q.setParameter(2, clientID);
			List<I0001> result = (List<I0001>) q.getResultList();
			String f = "";
			if (result.size() > 0) {
				f = result.get(0).getSellingPrice();
			}
			s1 = "" + f;
		} catch (Exception e) {
			logger.error("Error --------------->"+e.getMessage());
		}
		return s1;
	}

	@Transactional(value = "transactionManager")
	public String puruchaseOrder(PurchaseOrder purchaseOrder)
			throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getProduct_name());
			q.setParameter(2, clientID);
			List<I0001> result = (List<I0001>) q.getResultList();
			String f = "";
			if (result.size() > 0) {
				f = result.get(0).getSellingPrice();
				purchaseOrder.setActualPrice(result.get(0).getAutualPrice());
				purchaseOrder.setSellingPrice(result.get(0).getSellingPrice());
			}
		} catch (Exception e) {
			logger.error("Error --------------->"+e.getMessage());
		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public String savePuruchaseOrder(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		Query q3 = null;
		Query q1 = null;
		Query q2 = null;
		try {
			Query q11 = null;
			q11 = entitymanager.createQuery("from I0015 where client_ID=?");
			q11.setParameter(1, clientID);
			List<I0015> resultt = (List<I0015>) q11.getResultList();
			int count = 0;
			if (resultt.size() > 0) {
				for (I0015 re : resultt) {
					count++;
				}
			}
			purchaseOrder.setCounter(count);
			purchaseOrder.getCounter();
			if (purchaseOrder.getCounter() == 0) {
				String s = "IPO" + 1;
				purchaseOrder.setOrderNumber(s);
			} else {
				int j = purchaseOrder.getCounter();
				j++;
				String s = "IPO" + j;
				purchaseOrder.setOrderNumber(s);

			}
			I0015 puruchase = new I0015();
			puruchase.setQuantity(purchaseOrder.getQuantity());
			puruchase.setQuantityTotal(purchaseOrder.getQuantity());
			puruchase.setOrderDate(purchaseOrder.getOrderDate());
			puruchase.setTargentDate(purchaseOrder.getTargentDate());
			puruchase.setTotalPrice(purchaseOrder.getTotalPrice());
			puruchase.setStatus("insert");
			puruchase.setTemOrderNumber(purchaseOrder.getOrderNumber());
			puruchase.setDateName(purchaseOrder.getDueDate());
			puruchase.setClient_ID(clientID);
			entitymanager.persist(puruchase);
			Query q5;
			q5 = entitymanager.createQuery("from I0015 where temOrderNumber=? and client_ID=?");
			q5.setParameter(1, purchaseOrder.getOrderNumber());
			q5.setParameter(2, clientID);
			List<I0015> res = (List<I0015>) q5.getResultList();
			int puID = 0;
			if (res.size() > 0) {
				int i = 0;
				puID = res.get(i).getPurchase_ID();
			}
			q3 = entitymanager.createQuery("from I0016 where ordernumber=? and client_ID=?");			
			q3.setParameter(1, purchaseOrder.getOrderNumber());
			q3.setParameter(2, clientID);
			List<I0016> re = (List<I0016>) q3.getResultList();
			int puI = 0;
			if (re.size() > 0) {
				int i = 0;
				puI = re.get(i).getHas_purchase_ID();
			}
			String s;
			q = entitymanager
					.createQuery("from I0025 where vendorPhoneNumber=?  and client_ID=?");
			q.setParameter(1, purchaseOrder.getVendor_phone_number());
			q.setParameter(2, clientID);
			List<I0025> result = (List<I0025>) q.getResultList();
			int fID = 0;
			if (result.size() > 0) {
				int j = 0;
				fID = result.get(j).getVendor_ID();
			}
			q1 = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
			q1.setParameter(1, purchaseOrder.getProduct_name());
			q1.setParameter(2, clientID);
			List<I0001> resul = (List<I0001>) q1.getResultList();
			int pID = 0;
			if (resul.size() > 0) {
				int j = 0;
				pID = resul.get(j).getProduct_ID();
			}
			String b = "" + pID;
			String a = "" + fID;
			q2 = entitymanager
					.createQuery("from I0031 where vendor_ID=? and product_ID=?");
			q2.setParameter(1, a);
			q2.setParameter(2, b);
			List<I0031> resu = (List<I0031>) q2.getResultList();
			int hID = 0;
			if (resu.size() > 0) {
				int i = 0;
				hID = resu.get(i).getHas_vendor_ID();
			}
			I0016 haspurchase = new I0016();
			haspurchase.setOrdernumber(purchaseOrder.getOrderNumber());
			haspurchase.setStatus("inserted");
			haspurchase.setApprovalStatus("inserted");
			haspurchase.setOrderDate(purchaseOrder.getOrderDate());
			haspurchase.setStatus2("pending");
			haspurchase.setStatus3("Waiting");
			haspurchase.setStatus4("Waiting");
			haspurchase.setQuantity(purchaseOrder.getQuantity());
			haspurchase.setQuantityTotal(purchaseOrder.getQuantity());
			haspurchase.setI0031(entitymanager.find(I0031.class, hID));
			haspurchase.setI0015(entitymanager.find(I0015.class, puID));
			haspurchase.setDueDate(purchaseOrder.getDueDate());
			haspurchase.setClient_ID(clientID);
			entitymanager.persist(haspurchase);
			return "";
		} catch (Exception e) {
			logger.error("Error --------------->"+e.getMessage());
			return null;
		} finally {
			q1 = null;
			q = null;
			q2 = null;
			q3 = null;
		}

	}

	@Transactional(value = "transactionManager")
	public String savePuruchaseOrder1(PurchaseOrder purchaseOrder)
			throws DemoException {
		Query q = null;
		Query q3 = null;
		Query q1 = null;
		Query q2 = null;
		try {
			Query q11 = null;
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			q11 = entitymanager
					.createQuery("from I0015 where temOrderNumber=? and client_ID=?");
			q11.setParameter(1, purchaseOrder.getOrderNumber());
			q11.setParameter(2, clientID);
			List<I0015> resultt = (List<I0015>) q11.getResultList();
			int id = 0;
			int k = 0;
			String temptotal = "";
			String tempquantity = null;
			if (resultt.size() > 0) {
				id = resultt.get(k).getPurchase_ID();
				temptotal = resultt.get(k).getTotalPrice();
				tempquantity = resultt.get(k).getQuantity();
			} else {
				throw new DemoException("*This  order number not present");
			}
			q = entitymanager
					.createQuery("from I0025 where vendorPhoneNumber=?  and client_ID=?");
			q.setParameter(1, purchaseOrder.getVendor_phone_number());
			q.setParameter(2, clientID);
			List<I0025> result = (List<I0025>) q.getResultList();
			int fID = 0;
			if (result.size() > 0) {
				int j = 0;
				fID = result.get(j).getVendor_ID();
			}
			q1 = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
			q1.setParameter(1, purchaseOrder.getProduct_name());
			q1.setParameter(2, clientID);
			List<I0001> resul = (List<I0001>) q1.getResultList();
			int pID = 0;
			float sellingpriceProduct = 0;
			if (resul.size() > 0) {
				int j = 0;
				pID = resul.get(j).getProduct_ID();
				sellingpriceProduct = Float.parseFloat(resul.get(j)
						.getAutualPrice());
			}
			String b = "" + pID;
			String a = "" + fID;
			logger.info("13");
			q2 = entitymanager
					.createQuery("from I0031 where vendor_ID=? and product_ID=?");
			logger.info("14");
			q2.setParameter(1, a);
			q2.setParameter(2, b);
			logger.info("15");
			List<I0031> resu = (List<I0031>) q2.getResultList();
			logger.info("16");
			int hID = 0;
			if (resu.size() > 0) {
				logger.info("----------------------Inside vendor If -----------------------");
				int i = 0;
				hID = resu.get(i).getHas_vendor_ID();
				//logger.info("vendor Id:::::" + hID);
				logger.info("Vendor ID ------------------->"+hID);
			}
			logger.info("enter into save purchase:::::");
			logger.info(purchaseOrder.getTotalPrice());
			I0016 joinpurchase = new I0016();
			joinpurchase.setOrderDate(purchaseOrder.getOrderDate());
			joinpurchase.setI0015(entitymanager.find(I0015.class, id));
			joinpurchase.setI0031(entitymanager.find(I0031.class, hID));
			joinpurchase.setQuantity(purchaseOrder.getQuantity1());
			joinpurchase.setStatus2("pending");
			joinpurchase.setStatus3("Waiting");
			joinpurchase.setStatus4("Waiting");
			joinpurchase.setStatus("inserted");
			joinpurchase.setClient_ID(clientID);
			joinpurchase.setApprovalStatus("inserted");
			joinpurchase.setOrdernumber(purchaseOrder.getOrderNumber());
			entitymanager.persist(joinpurchase);
			logger.info("purchase id---------->" + id);
			I0015 purchase = entitymanager.find(I0015.class, id);
			purchase.setQuantity((Integer.parseInt(tempquantity) + Integer
					.parseInt(purchaseOrder.getQuantity1())) + "");
			purchase.setTotalPrice(temptotal
					+ (sellingpriceProduct * Float.parseFloat(purchaseOrder
							.getQuantity1())));
			entitymanager.merge(purchase);

			logger.debug("quantit----------------->" + ""
					+ Integer.parseInt(tempquantity)
					+ Integer.parseInt(purchaseOrder.getQuantity1()));
			logger.info("total amount------------->"
					+ temptotal
					+ (sellingpriceProduct * Float.parseFloat(purchaseOrder
							.getQuantity1())));
			logger.info("amount---------------->" + sellingpriceProduct
					* Float.parseFloat(purchaseOrder.getQuantity1()));

			// haspurchase.setI0015(puruchase);

			logger.info("1 out:::::");

			logger.info("2 out:::::");

			logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$inside savePuruchaseOrder1 dao$$$$$$$$$$$$$");
			return "";

		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		} finally {

			q1 = null;
			q = null;
			q2 = null;
			q3 = null;
		}

	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0016> purchaseOrdercancel(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseOrdercancel Dao-------------$$$$$$$$$$$$$$-----------");
		logger.info(s);
		Query q = null;

		ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
		ArrayList<I0015> result = null;
		try {
			logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseView dao-------------$$$$$$$$$$$$$$-----------");
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0015 where temOrderNumber=? and (status=? or status='delivered') and client_ID=?");
			q1.setParameter(1, s);
			q1.setParameter(2, "insert");
			q1.setParameter(3, clientID);
			result = (ArrayList<I0015>) q1.getResultList();
			if (result.size() > 0) {
				logger.info("result size---------->" + result.size());
				q = entitymanager
						.createQuery("from I0016 where ordernumber=? and status=? and (status3=? or status3='delivered') and client_ID=?");
				q.setParameter(1, s);
				q.setParameter(2, "inserted");
				q.setParameter(3, "Waiting");
				q.setParameter(3, clientID);
				purchaselist = (ArrayList<I0016>) q.getResultList();
				logger.info("excuted::::::");
				if (purchaselist.size() == 0) {

				} else {

					int count = 0;
					for (I0016 i0016 : purchaselist) {
						logger.info("ccccc-----" + i0016.getQuantity()
								+ "......"
								+ i0016.getI0031().getI0001().getAutualPrice());
						PurchaseOrder purchaseOrder2 = new PurchaseOrder();
						BigDecimal valref = new BigDecimal("0");
						valref = new BigDecimal(i0016.getQuantity())
								.multiply(new BigDecimal(i0016.getI0031()
										.getI0001().getAutualPrice()));
						purchaseOrder2.setNetReference(valref.toString());
						for (I0015 i0015 : result) {

							logger.info("1");
							purchaseOrder2.setOrderDate(i0015.getOrderDate());
							logger.info("2");
							purchaseOrder2.setOrderNumber(i0015
									.getTemOrderNumber());
							logger.info("3");
							purchaseOrder2.setFirmName(i0015.getI0016s().get(0)
									.getI0031().getI0025()
									.getVendorPhoneNumber());
							logger.info("4");
							purchaseOrder2
									.setPhonenumber(i0015.getI0016s().get(0)
											.getI0031().getI0025()
											.getFirmName());
							logger.info("5");
							purchaseOrder2
									.setIndustry(i0015.getI0016s().get(0)
											.getI0031().getI0001()
											.getIndustryProduct());
							logger.info("6"
									+ GenerateEmployee.numberFormat
											.format(new BigDecimal(i0015
													.getTotalPrice())));
							purchaseOrder2.setTotalPrice(""
									+ GenerateEmployee.numberFormat
											.format(new BigDecimal(i0015
													.getTotalPrice())));
							logger.info("7");

							purchaseOrder2.setPayableAmount(""
									+ i0015.getI0022s().get(0).getI0023s()
											.get(0).getBalanceAmount());
							logger.info("8");
							purchaseOrder2.setStatus2(i0015.getI0016s().get(0)
									.getStatus2());
							purchaseOrder2.setStatus3(i0015.getI0016s().get(0)
									.getStatus3());
							result4.add(purchaseOrder2);
							count++;
							logger.info("==dao===>>" + i0015);
							logger.info("==dao===>>" + result4.size());

						}
					}
					purchaseOrder.setResult4(result4);
					logger.info("size============>" + result4.size());

				}
			}

			logger.info("--------------$$$$$$$$$$$$$$------------Outside purchaseView dao-------------$$$$$$$$$$$$$$-----------");

		} catch (Exception e) {
			logger.info("<-----------------inside exception------------->");
			int count = 0;
			PurchaseOrder purchaseOrder2 = new PurchaseOrder();
			for (I0016 i0016 : purchaselist) {
				logger.info("ccccc-----" + i0016.getQuantity() + "......"
						+ i0016.getI0031().getI0001().getAutualPrice());

				BigDecimal valref = new BigDecimal("0");
				valref = new BigDecimal(i0016.getQuantity())
						.multiply(new BigDecimal(i0016.getI0031().getI0001()
								.getAutualPrice()));
				purchaseOrder2.setNetReference(valref.toString());
			}
			if (result.size() > 0) {
				int i = 0;
				logger.info("==dao==111=");

				logger.info("1");
				purchaseOrder2.setOrderDate(result.get(i).getOrderDate());
				logger.info("2");
				purchaseOrder2
						.setOrderNumber(result.get(i).getTemOrderNumber());
				logger.info("3");
				purchaseOrder2.setFirmName(result.get(i).getI0016s().get(0)
						.getI0031().getI0025().getVendorPhoneNumber());
				logger.info("4");
				purchaseOrder2.setPhonenumber(result.get(i).getI0016s().get(0)
						.getI0031().getI0025().getFirmName());
				logger.info("5");
				purchaseOrder2.setIndustry(result.get(i).getI0016s().get(0)
						.getI0031().getI0001().getIndustryProduct());
				logger.info("6");
				purchaseOrder2
						.setTotalPrice("" + result.get(i).getTotalPrice());
				logger.info("7");

				purchaseOrder2.setPayableAmount(""
						+ result.get(i).getTotalPrice());
				logger.info("8");
				purchaseOrder2.setStatus2(result.get(i).getI0016s().get(0)
						.getStatus2());
				purchaseOrder2.setStatus3(result.get(i).getI0016s().get(0)
						.getStatus3());
				result4.add(purchaseOrder2);
				count++;
				logger.info("==dao===>>" + result4.size());
			}
			purchaseOrder.setResult4(result4);
			logger.info("size============>" + result4.size());
			logger.info("inside exeption------------>" + e.getStackTrace());
		}
		return purchaselist;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0016> purchaseOrdercancel1(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseOrdercancel1 Dao-------------$$$$$$$$$$$$$$-----------");
		logger.info(s);

		Query q = null;

		ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
		ArrayList<I0015> result = null;
		try {
			logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseView dao-------------$$$$$$$$$$$$$$-----------");

			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0015 where purchase_ID=?");
			q1.setParameter(1, purchaseOrder.getPurchaseid());
			result = (ArrayList<I0015>) q1.getResultList();
			if (result.size() > 0) {
				logger.info("result size---------->" + result.size());
				q = entitymanager
						.createQuery("from I0016 where purchase_ID=?");
				q.setParameter(1, result.get(0).getPurchase_ID());
				purchaselist = (ArrayList<I0016>) q.getResultList();
				logger.info("excuted::::::");
				if (purchaselist.size() == 0) {

				} else {

					int count = 0;
					for (I0015 i0015 : result) {
						logger.info("==dao==");
						PurchaseOrder purchaseOrder2 = new PurchaseOrder();
						logger.info("1");
						purchaseOrder2.setOrderDate(i0015.getOrderDate());
						logger.info("2");
						purchaseOrder2
								.setOrderNumber(i0015.getTemOrderNumber());
						logger.info("3");
						purchaseOrder2.setDestinationCurrency(i0015.getCurrencyType());
						purchaseOrder2.setFirmName(i0015.getI0016s().get(0)
								.getI0031().getI0025().getVendorPhoneNumber());
						logger.info("4");
						purchaseOrder2.setPhonenumber(i0015.getI0016s().get(0)
								.getI0031().getI0025().getFirmName());
						logger.info("5");
						purchaseOrder2.setIndustry(i0015.getI0016s().get(0)
								.getI0031().getI0001().getIndustryProduct());
						logger.info("6");
						purchaseOrder2
								.setTotalPrice("" + i0015.getTotalPrice());
						logger.info("7");
						purchaseOrder2.setMarginPrice(""
								+ i0015.getI0022s().get(0).getI0023s().get(0)
										.getPaidAmount());
						logger.info("margin--------------->"
								+ i0015.getI0022s().get(0).getI0023s().get(0)
										.getPaidAmount());
						purchaseOrder2.setPayableAmount(""
								+ i0015.getI0022s().get(0).getI0023s().get(0)
										.getBalanceAmount());
						logger.info("8");
						purchaseOrder2.setStatus2(i0015.getI0016s().get(0)
								.getStatus2());
						purchaseOrder2.setStatus3(i0015.getI0016s().get(0)
								.getStatus3());
						result4.add(purchaseOrder2);
						count++;
					}
					purchaseOrder.setResult4(result4);
					logger.info("size============>" + result4.size());
				}
			}

			logger.info("--------------$$$$$$$$$$$$$$------------Outside purchaseView dao-------------$$$$$$$$$$$$$$-----------");

		} catch (Exception e) {
			logger.info("<-----------------inside exception------------->");
			int count = 0;

			for (I0015 i0015 : result) {
				logger.info("==dao=-=11=");
				PurchaseOrder purchaseOrder2 = new PurchaseOrder();
				logger.info("1");
				purchaseOrder2.setOrderDate(i0015.getOrderDate());
				logger.info("2");
				purchaseOrder2.setOrderNumber(i0015.getTemOrderNumber());
				logger.info("3");
				purchaseOrder2.setFirmName(i0015.getI0016s().get(0).getI0031()
						.getI0025().getVendorPhoneNumber());
				logger.info("4");
				purchaseOrder2.setPhonenumber(i0015.getI0016s().get(0)
						.getI0031().getI0025().getFirmName());
				logger.info("5");
				purchaseOrder2.setIndustry(i0015.getI0016s().get(0).getI0031()
						.getI0001().getIndustryProduct());
				logger.info("6");
				purchaseOrder2.setTotalPrice("" + i0015.getTotalPrice());
				logger.info("7");
				purchaseOrder2.setMarginPrice("" + BigDecimal.valueOf(0));
				purchaseOrder2.setPayableAmount("" + i0015.getTotalPrice());
				logger.info("8");
				purchaseOrder2
						.setStatus2(i0015.getI0016s().get(0).getStatus2());
				purchaseOrder2
						.setStatus3(i0015.getI0016s().get(0).getStatus3());
				result4.add(purchaseOrder2);
				count++;
			}
			purchaseOrder.setResult4(result4);
			logger.info("size============>" + result4.size());
			logger.info("inside exeption------------>" + e.getStackTrace());
		}
		return purchaselist;

	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0015> invoicePurachaseDrop(ArrayList<I0015> purchaselist)
			throws DemoException {
		logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseOrdercancel Dao-------------$$$$$$$$$$$$$$-----------");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		Query q1 = null;
		q1 = entitymanager
				.createQuery("from I0015 where  (status='delivered' or status='insert') and client_ID=?");
		q1.setParameter(1, clientID);
		purchaselist = (ArrayList<I0015>) q1.getResultList();

		logger.info("excuted::::::");
		logger.info("purchase list size" + purchaselist.size());
		if (purchaselist.size() == 0) {
			// throw new InventoryException("* please check the Order Number");
		} else {
			for (int i = 0; i < purchaselist.size(); i++) {
				q = null;
				q1 = null;
				logger.info("string name------------>" + purchaselist.get(i));
				ArrayList<I0015> purchaselist2 = null;

				q = entitymanager.createQuery("from I0022 where purchase_ID=?");
				q.setParameter(1, purchaselist.get(i).getPurchase_ID());
				ArrayList<I0022> invoicelist = (ArrayList<I0022>) q
						.getResultList();
				logger.info("invoice List size" + invoicelist.size());
				if (invoicelist.size() > 0) {
					purchaselist.remove(i);
					i--;
				}
			}
		}
		logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseOrdercancel Dao-------------$$$$$$$$$$$$$$-----------");
		return purchaselist;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0015> invoicePurachaseDrop1(ArrayList<I0015> purchaselist)
			throws DemoException {
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			
			purchaselist = new ArrayList<I0015>();
			ArrayList<String> finallist = new ArrayList<String>();
			logger.info("--------------$$$$$$$$$$$$$$------------inside invoicePurachaseDrop1 Dao-------------$$$$$$$$$$$$$$-----------");
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0022 where  approvalStatus=? and status=? and client_ID=?");
			q1.setParameter(1, "not submitted");
			q1.setParameter(2, "purchase Invoice");
			q1.setParameter(3, clientID);
			ArrayList<I0022> purchaselist1 = (ArrayList<I0022>) q1
					.getResultList();
			logger.info("excuted::::::");
			if (purchaselist1.size() == 0) {
				throw new DemoException("");
			}
			logger.info("2");

			for (I0022 i0022 : purchaselist1) {
				logger.info("1");
				I0015 purchaselist2 = new I0015();
				logger.info("temp order number------>"
						+ i0022.getI0015().getTemOrderNumber());
				purchaselist2.setTemOrderNumber(i0022.getI0015()
						.getTemOrderNumber());
				// purchaselist2.setTotalPrice(GenerateEmployee.numberFormat.format(new
				// BigDecimal(""+purchaselist2.getTotalPrice())));
				purchaselist.add(purchaselist2);
			}
			logger.info("result size----------->" + purchaselist);
			logger.info("--------------$$$$$$$$$$$$$$------------outside invoicePurachaseDrop1 Dao-------------$$$$$$$$$$$$$$-----------");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return purchaselist;
	}

	@Transactional(value = "transactionManager")
	public String purchaseClose(PurchaseOrder purchaseOrder)
			throws DemoException {
		Query q = null;
		Query q1 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<I0015> result3 = new ArrayList<I0015>();
		ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
		try {
			logger.info("%%%%%%%%%%%%%%%INSIDE PURCHASE CLOSE DAO %%%%%%%%%%%%%%%%%");
			logger.info("purchase order number----------------"
					+ purchaseOrder.orderNumber);
			q = entitymanager.createQuery("from I0015 where temOrderNumber=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, clientID);
			ArrayList<I0015> result = (ArrayList<I0015>) q.getResultList();
			int id = 0;
			if (result.size() > 0) {
				id = result.get(0).getPurchase_ID();
				q1 = entitymanager
						.createQuery("from I0016 where ordernumber=? and client_ID=?");
				q1.setParameter(1, purchaseOrder.getOrderNumber());
				q1.setParameter(2, clientID);
				ArrayList<I0016> result1 = (ArrayList<I0016>) q1
						.getResultList();
				if (result1.size() > 0) {
					logger.info("1");
					int count = 0;
					for (I0016 i0016 : result1) {
						logger.info("2");
						PurchaseOrder result5 = new PurchaseOrder();
						result5.setProduct_ID(count + 1);
						result5.setProduct_name(i0016.getI0031().getI0001()
								.getProductName());
						result5.setOrderDate(i0016.getOrderDate());
						result5.setQuantity1(i0016.getQuantity());
						result5.setCrosstotal1(i0016.getI0031().getI0001()
								.getAutualPrice());
						result5.setMarginPrice(""
								+ new BigDecimal(result1.get(count)
										.getQuantity())
										.multiply(new BigDecimal(i0016
												.getI0031().getI0001()
												.getAutualPrice())));
						result4.add(result5);
						count++;
					}

					purchaseOrder.setResult4(result4);
				}
			} else {
				throw new DemoException("*this odernumber is not present");
			}
		} catch (Exception e) {

		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0015> dateSearchInvoice(Date fromDate, Date todate,
			ArrayList<I0015> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
		ArrayList<I0015> result = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("--------------$$$$$$$$$$$$$$------------inside dateSearchInvoice Dao-------------$$$$$$$$$$$$$$-----------");

			Query q = null;
			logger.info("from date::::::::::" + fromDate);
			logger.info("to date::::::::::" + todate);
			q = entitymanager
					.createQuery("from I0015 where orderDate between ? and ? and (status='insert' or status='delivered') and client_ID=?");
			q.setParameter(1, fromDate);
			q.setParameter(2, todate);
			q.setParameter(3, clientID);
			purchaselist = (ArrayList<I0015>) q.getResultList();
			logger.info("excuted::::::");
			if (purchaselist.size() == 0) {
				throw new DemoException("* please check the Date");
			}

			if (purchaselist.size() > 0) {

				logger.info("in");
				int count = 0;
				for (I0015 i0015 : purchaselist) {
					PurchaseOrder purchaseOrder2 = new PurchaseOrder();
					logger.info("1");
					purchaseOrder2.setOrderDate(i0015.getOrderDate());
					logger.info("2");
					purchaseOrder2.setOrderNumber(i0015.getTemOrderNumber());
					logger.info("3");
					purchaseOrder2.setFirmName(i0015.getI0016s().get(0)
							.getI0031().getI0025().getVendorPhoneNumber());
					logger.info("4");
					purchaseOrder2.setPhonenumber(i0015.getI0016s().get(0)
							.getI0031().getI0025().getFirmName());
					logger.info("5");
					purchaseOrder2.setIndustry(i0015.getI0016s().get(0)
							.getI0031().getI0001().getIndustryProduct());
					logger.info("6");
					purchaseOrder2.setTotalPrice("" + i0015.getTotalPrice());
					logger.info("7");

					purchaseOrder2.setPayableAmount(""
							+ i0015.getI0022s().get(0).getI0023s().get(0)
									.getBalanceAmount());
					logger.info("8");
					purchaseOrder2.setStatus2(i0015.getI0016s().get(0)
							.getStatus2());
					purchaseOrder2.setStatus3(i0015.getI0016s().get(0)
							.getStatus3());
					result4.add(purchaseOrder2);
					count++;
				}
				purchaseOrder.setResult4(result4);
				logger.info("size============>" + result4.size());

				logger.info("--------------$$$$$$$$$$$$$$-----------Outside dateSearchInvoice Dao-------------$$$$$$$$$$$$$$-----------");

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			logger.info("<-----------------inside exception------------->");
			int count = 0;
			for (I0015 i0015 : purchaselist) {

				PurchaseOrder purchaseOrder2 = new PurchaseOrder();
				logger.info("1");
				purchaseOrder2.setOrderDate(i0015.getOrderDate());
				logger.info("2");
				purchaseOrder2.setOrderNumber(i0015.getTemOrderNumber());
				logger.info("3");
				purchaseOrder2.setFirmName(i0015.getI0016s().get(0).getI0031()
						.getI0025().getVendorPhoneNumber());
				logger.info("4");
				purchaseOrder2.setPhonenumber(i0015.getI0016s().get(0)
						.getI0031().getI0025().getFirmName());
				logger.info("5");
				purchaseOrder2.setIndustry(i0015.getI0016s().get(0).getI0031()
						.getI0001().getIndustryProduct());
				logger.info("6");
				purchaseOrder2.setTotalPrice("" + i0015.getTotalPrice());
				logger.info("7");

				purchaseOrder2.setPayableAmount("" + i0015.getTotalPrice());
				logger.info("8");
				purchaseOrder2
						.setStatus2(i0015.getI0016s().get(0).getStatus2());
				purchaseOrder2
						.setStatus3(i0015.getI0016s().get(0).getStatus3());
				result4.add(purchaseOrder2);
				count++;
			}
			purchaseOrder.setResult4(result4);
			logger.info("size============>" + result4.size());
			logger.info("inside exeption------------>" + e.getStackTrace());
		}

		return purchaselist;
	}

	@Transactional(value = "transactionManager")
	public String cancelConform1(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("comes in dao:::::::" + purchaseOrder.getOrderNumber());

			Query q = null;
			q = entitymanager.createQuery("from I0016 where purchase_ID=?");
			q.setParameter(1, purchaseOrder.getPurchaseid());
			List<I0016> result = (List<I0016>) q.getResultList();
			int hID = 0;
			int id = 0;
			if (result.size() > 0) {
				int i = 0;
				hID = result.get(i).getHas_purchase_ID();
				Query q1 = null;
				q1 = entitymanager
						.createQuery("from I0015 where purchase_ID=?");
				q1.setParameter(1, purchaseOrder.getPurchaseid());
				List<I0015> resul = (List<I0015>) q1.getResultList();
				if (resul.size() > 0) {
					id = resul.get(0).getPurchase_ID();
					Query q2 = null;
					q2 = entitymanager
							.createQuery("from I0022 where purchase_ID=?");
					q2.setParameter(1, id);
					List<I0022> result1 = (List<I0022>) q2.getResultList();
					int invoiceId = 0;
					if (result1.size() > 0) {
						logger.info("-------------------This order number hase generated Invoice-----------");
						invoiceId = result1.get(0).getInvoice_ID();
						logger.info("----------invoiceId------------"
								+ invoiceId);
						Query q3 = null;
						q3 = entitymanager
								.createQuery("from I0023 where invoice_ID=?");
						q3.setParameter(1, invoiceId);
						List<I0023> result2 = (List<I0023>) q3.getResultList();
						int paymentid = 0;
						if (result2.size() > 0) {
							logger.info("-------------------This order number hase generated Invoice-----------");
							paymentid = result2.get(0).getPayment_ID();
							logger.info("----------payment id------------"
									+ paymentid);
							I0023 i0023 = entitymanager.find(I0023.class,
									paymentid);
							entitymanager.remove(i0023);
						}
						I0022 i0022 = entitymanager
								.find(I0022.class, invoiceId);
						entitymanager.remove(i0022);
					}
				}

			}
			logger.info("hid::::::::" + hID);
			int pid = 0;
			for (I0016 i0016 : result) {
				pid = i0016.getHas_purchase_ID();
				I0016 haspurchase = entitymanager.find(I0016.class, pid);
				logger.info("1");
				haspurchase.setStatus("cancelled");
				entitymanager.merge(haspurchase);

			}
			I0015 purchase = entitymanager.find(I0015.class, id);
			logger.info("1");
			purchase.setStatus("cancelled");
			entitymanager.merge(purchase);

			return "";
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0016> purchaseView(PurchaseOrder purchaseOrder,
			ArrayList<I0016> purchaselist) throws DemoException {
		Query q = null;

		ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
		ArrayList<I0015> result = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		String baseCurrency=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("baseCurrency");
		try {
			logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseView dao-------------$$$$$$$$$$$$$$-----------");
			Query q1 = null;
			if(userType.equalsIgnoreCase("Maker")){
				q1=entitymanager.createQuery("from I0015 where (status=? or status='delivered' or status='cancelled') and client_ID=? and user_ID=? ORDER BY createdDate DESC");
				q1.setParameter(1, "insert");
				q1.setParameter(2, clientID);
				q1.setParameter(3, userID);
			}else{
				if (purchaseOrder.getApproval()=="ApprovalData") {
					q1=entitymanager.createQuery("from I0015 where (status=? or status='delivered' or status='cancelled') and client_ID=? and approvalStatus='draft' ORDER BY createdDate DESC");
					q1.setParameter(1, "insert");
					q1.setParameter(2, clientID);
				}
				else{
					q1=entitymanager.createQuery("from I0015 where (status=? or status='delivered' or status='cancelled') and client_ID=? ORDER BY createdDate DESC");
					q1.setParameter(1, "insert");
					q1.setParameter(2, clientID);
				}
				
			}
			result = (ArrayList<I0015>) q1.getResultList();
			if (result.size() > 0) {
				logger.info("result size---------->" + result.size());
				q = entitymanager.createQuery("from I0016 ");
				purchaselist = (ArrayList<I0016>) q.getResultList();
				logger.info("excuted::::::");
				if (purchaselist.size() == 0) {
					throw new DemoException("No value found !");
				}
				int count = 0;

				for (I0015 i0015 : result) {
					PurchaseOrder purchaseOrder2 = new PurchaseOrder();
					logger.info("1");
					purchaseOrder2.setOrderDate(i0015.getOrderDate());
					logger.info("2");
					purchaseOrder2.setOrderNumber(i0015.getTemOrderNumber());
					logger.info("3");
					purchaseOrder2.setFirmName(i0015.getI0016s().get(0)
							.getI0031().getI0025().getVendorPhoneNumber());
					logger.info("4");
					purchaseOrder2.setNum(new BigDecimal(purchaselist.get(0)
							.getI0031().getI0025().getFirmName()));
					purchaseOrder2.setPhonenumber(purchaselist.get(0)
							.getI0031().getI0025().getFirmName());
					logger.info("5");
					purchaseOrder2.setIndustry(i0015.getI0016s().get(0)
							.getI0031().getI0001().getIndustryProduct());
					logger.info("6");
					purchaseOrder2
							.setTot(new BigDecimal(i0015.getTotalPrice()));
					purchaseOrder2.setTotalPrice("" + i0015.getTotalPrice());
					purchaseOrder2.setApproveStatus(i0015.getApprovalStatus());
					purchaseOrder2.setPid(i0015.getPurchase_ID());
					purchaseOrder2.setCurrency(i0015.getCurrency());
					purchaseOrder2.setUserID(String.valueOf(i0015.getUserID().getUserNo()));
					purchaseOrder2.setPurchaseid(i0015.getPurchase_ID());
					logger.info("7");

					try {

						logger.info("--------------  index---------------");
						purchaseOrder2.setPayableAmount(""
								+ i0015.getI0022s().get(0).getI0023s().get(0)
										.getBalanceAmount());

						logger.info("--------------else index---------------");

					} catch (Exception e) {
						logger.info("inside inner exception:::::::");
						purchaseOrder2.setPayableAmount(""
								+ i0015.getTotalPrice());
					}
					logger.info("8");
					purchaseOrder2.setStatus(i0015.getI0016s().get(0)
							.getStatus());
					purchaseOrder2.setStatus2(i0015.getI0016s().get(0)
							.getStatus2());
					purchaseOrder2.setStatus3(i0015.getI0016s().get(0)
							.getStatus3());
					purchaseOrder2.setApprovalStatus(i0015.getI0016s().get(0)
							.getApprovalStatus());
					purchaseOrder2.setBaseCurrency(baseCurrency);
					int cnt = 0;
					if (i0015.getI0016s().get(0).getStatus2()
							.equalsIgnoreCase("pending")
							&& i0015.getI0016s().get(0).getStatus3()
									.equalsIgnoreCase("Waiting")) {

						logger.info("if1");
						try {
							if (new BigDecimal(i0015.getI0022s().get(0)
									.getI0023s().get(0).getPaidAmount())
									.compareTo(BigDecimal.valueOf(0)) == 0) {
								logger.info("if2");
								cnt = 2;

								logger.info("if3");
							} else {
								cnt = 1;

							}
						} catch (Exception e) {
							if (i0015.getI0016s().get(0).getStatus2()
									.equalsIgnoreCase("pending")
									&& i0015.getI0016s().get(0).getStatus3()
											.equalsIgnoreCase("Waiting")) {
								purchaseOrder2.setStatus4("in2");
							} else {
								purchaseOrder2.setStatus4("out");
							}

						}
						if (cnt == 2) {
							purchaseOrder2.setStatus4("in2");

						} else if (cnt == 1) {
							purchaseOrder2.setStatus4("in1");
						}
						logger.info("if4");
					} else {
						logger.info("else1");
						purchaseOrder2.setStatus4("out");
					}
					logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>"
							+ purchaseOrder2.getStatus4());
					result4.add(purchaseOrder2);
					count++;
				}
				purchaseOrder.setResult4(result4);
				logger.info("size============>" + result4.size());
			}

			logger.info("--------------$$$$$$$$$$$$$$------------Outside purchaseView dao-------------$$$$$$$$$$$$$$-----------");

		} catch (Exception e) {
			logger.info("<-----------------inside exception------------->");
			logger.error("Inside Exception", e);

			logger.info("size============>" + result4.size());
			logger.info("inside exeption------------>" + e.getStackTrace());
		}
		return purchaselist;

	}

	@Override
	public ArrayList<I0016> invoicePurhcase1(PurchaseOrder purchaseOrder,
			ArrayList<I0016> purchaselist) throws DemoException {
		Query q;
		try {
			logger.info("inside Dao:::::invoice");
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			logger.info("ssss" + purchaseOrder.getOrderNumber());

			q = entitymanager.createQuery("from I0016 where ordernumber=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, clientID);
			purchaselist = (ArrayList<I0016>) q.getResultList();
			int i = 0;
			int id = 0;
			int fId = 0;

			if (purchaselist.size() > 0) {
				id = purchaselist.get(i).getHas_purchase_ID();
				fId = purchaselist.get(i).getI0015().getPurchase_ID();

			}
			logger.info("Fid::::::::" + fId);
			logger.info("id::::::::" + id);
			logger.info("excuted::::::");
			Date date = new Date();
			I0022 invoice = new I0022();
			logger.info("1");
			invoice.setI0015(entitymanager.find(I0015.class, fId));
			logger.info("2");
			invoice.setStatus("purchase Invoice");
			invoice.setInvoiceDate(date);
			logger.info("3");
			invoice.setClient_ID(clientID);
			entitymanager.persist(invoice);
			logger.info("4");

			return purchaselist;
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return null;
		}

	}

	@Transactional(value = "transactionManager")
	public ArrayList<String> dropAccount(String s, ArrayList<String> ordernumber)
			throws DemoException {
		Query q = null, q2 = null;
		;
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			ArrayList<String> result3 = new ArrayList<String>();
			if (s.endsWith("PURCHASE")) {
				q2 = entitymanager
						.createQuery("from  I0015 where (status='insert' or status='delivered') and client_ID=?");
				q2.setParameter(1, clientID);
				ArrayList<I0015> result = (ArrayList<I0015>) q2.getResultList();
				if (result.size() > 0) {
					String j = null;
					;
					for (I0015 i0015 : result) {
						I0015 sobj = new I0015();
						j = i0015.getTemOrderNumber();
						q = entitymanager
								.createQuery("from I0016 where ordernumber=? and status='inserted' and status2='pending'  and client_ID=?");
						q.setParameter(1, j);
						q.setParameter(2, clientID);
						ArrayList<I0016> result1 = (ArrayList<I0016>) q
								.getResultList();
						if (result1.size() > 0) {
							result3.add(i0015.getTemOrderNumber());
						}
					}
				}

			}
			return result3;
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return null;
		}
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0023> AccountOut(PurchaseOrder purchaseOrder,
			ArrayList<I0023> purchaselist) throws DemoException {
		logger.info("--------------$$$$$$$$$$$$$$------------Inside AccountOut Dao -------------$$$$$$$$$$$$$$-----------");
		Query q1 = null;
		Query q2 = null;
		Query q3 = null;
		Query q = null;
		int pID = 0;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.info("inside dao ##################:::::::::::");
		logger.info("order number:##################::"
				+ purchaseOrder.getOrderNumber());
		if (purchaseOrder.getOrderNumber().equals("")) {
			logger.info("111111111");
			throw new DemoException("*Please enter the order number ");
		} else {
			q = entitymanager
					.createQuery("from I0016 where ordernumber=? and status=?  and client_ID=?");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, "inserted");
			q.setParameter(3, clientID);
			ArrayList<I0016> result = (ArrayList<I0016>) q.getResultList();
			if (result.size() > 0) {
				int i = 0;
				logger.info("1");
				pID = result.get(i).getI0015().getPurchase_ID();
				Query qq = null;
				qq = entitymanager
						.createQuery("from PurchaseReturn where purchase_ID=?");
				qq.setParameter(1, pID);
				logger.info("purchase id -- >> " + pID);
				List<PurchaseReturn> prlist = (List<PurchaseReturn>) qq
						.getResultList();
				if (prlist.size() > 0) {
					logger.info("normal return :::"
							+ prlist.get(0).getNormalReturn());
					logger.info("damage return :::"
							+ prlist.get(0).getDamageReturn());

					BigDecimal rq = BigDecimal.valueOf(0);
					BigDecimal aprice = BigDecimal.valueOf(0);
					logger.info("purchase return size - " + prlist.size());
					for (int j = 0; j < prlist.size(); j++) {
						Query ap = null;
						ap = entitymanager
								.createQuery("from I0001 where productName=?");
						ap.setParameter(1, prlist.get(j).getProductName());
						List<I0001> prdlist = (List<I0001>) ap.getResultList();

						rq = new BigDecimal(prlist.get(j).getNormalReturn())
								.add(new BigDecimal(prlist.get(j)
										.getDamageReturn()));
						logger.info("quan - >" + rq + " actual price -- > "
								+ prdlist.get(0).getAutualPrice());
						logger.info("aprice" + aprice);
						aprice = aprice.add(rq.multiply(new BigDecimal(prdlist
								.get(0).getAutualPrice())));
						logger.info("amnt -- > " + aprice);
					}
					logger.info("purchase return price -2 " + aprice);
					purchaseOrder.setTotalPrice(""
							+ (new BigDecimal(result.get(0).getI0015()
									.getTotalPrice()).subtract(aprice)));
				} else {
					logger.info("purchase return else -- >> " + pID);
					purchaseOrder.setTotalPrice(""
							+ new BigDecimal(result.get(0).getI0015()
									.getTotalPrice()));
				}
				purchaseOrder.setOrderDate(result.get(i).getI0015()
						.getOrderDate());
				q1 = entitymanager
						.createQuery("from I0022 where purchase_ID=?");
				q1.setParameter(1, pID);
				logger.info("3");
				ArrayList<I0022> resul = (ArrayList<I0022>) q1.getResultList();
				int jId = 0;
				if (resul.size() > 0) {
					int j = 0;
					logger.info("1");
					jId = resul.get(j).getInvoice_ID();
					logger.info("invoice Id:::::" + jId);
					q2 = entitymanager
							.createQuery("from I0023 where invoice_ID=?");
					q2.setParameter(1, jId);
					ArrayList<I0023> resu = (ArrayList<I0023>) q2
							.getResultList();
					if (resu.size() > 0) {
						logger.info("11111111111");
						purchaselist = resu;
						logger.info("suuuuuu::::"
								+ purchaselist.get(0).getI0022().getI0015()
										.getI0016s().get(0).getI0031()
										.getI0001().getProductName());
					} else {
						logger.info("2222222222");
						I0023 payment = new I0023();
						payment.setBalanceAmount(purchaseOrder.getTotalPrice());
						payment.setPaidAmount("0");
						payment.setStartDate(purchaseOrder.orderDate);
						payment.setI0022(entitymanager.find(I0022.class, jId));
						payment.setStatus("pending");
						payment.setClient_ID(clientID);
						payment.setPayableAmount(purchaseOrder.getTotalPrice());
						logger.info("payable" + payment.getPayableAmount());
						logger.info("balance" + payment.getBalanceAmount());
						logger.info("paid" + payment.getPaidAmount());
						payment.setSales_payment_ID(1);
						entitymanager.persist(payment);
						q3 = entitymanager
								.createQuery("from I0023 where invoice_ID=?");
						q3.setParameter(1, jId);
						purchaselist = (ArrayList<I0023>) q3.getResultList();
						purchaselist.get(0).getI0022().getI0015().getI0016s()
								.get(0).getI0031().getI0001().getProductName();
						purchaselist.get(0).getI0022().getI0015().getI0016s()
								.get(0).getI0031().getI0001().getProductName();
						logger.info("success");

					}
				} else {
					RequestContext.getCurrentInstance().execute(
							"PF('purpay1').show();");
					throw new DemoException("* Should  Generate Invoice First ");
				}

			} else {
				throw new DemoException("invalid order number>>>>");
			}
		}
		logger.info("--------------$$$$$$$$$$$$$$------------Outside AccountOut Dao -------------$$$$$$$$$$$$$$-----------");
		return purchaselist;
	}

	/*@Transactional(value = "transactionManager")
	public String payNow1(PurchaseOrder purchaseOrder) throws DemoException {
		logger.debug("--------------$$$$$$$$$$$$$$------------Inside AccountOut paynow Dao -------------$$$$$$$$$$$$$$-----------");
		Query q = null;
		logger.debug("number" + purchaseOrder.getOrderNumber());
		int pID = 0;
		logger.debug("inside dao:::::::::::");
		String clientID = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("ClientID");
		logger.debug("order number:::" + purchaseOrder.getOrderNumber());
		if (purchaseOrder.getOrderNumber().equals("")) {
			logger.debug("111111111");
			throw new DemoException("* Please enter the order number ");
		} else {

			q = entitymanager
					.createQuery("from I0016 where ordernumber=? and status=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, "inserted");
			q.setParameter(3, clientID);
			ArrayList<I0016> result = (ArrayList<I0016>) q.getResultList();
			if (result.size() > 0) {
				int i = 0;
				logger.debug("1");
				int hpid = 0;

				pID = result.get(i).getI0015().getPurchase_ID();
				purchaseOrder.setOrderDate(result.get(i).getI0015()
						.getOrderDate());
				purchaseOrder.setTotalPrice(""
						+ result.get(i).getI0015().getTotalPrice());
				logger.debug("date:::" + purchaseOrder.getOrderDate());
				logger.debug("pID:::::" + pID);
				Query q1 = null;
				logger.debug("1");
				q1 = entitymanager
						.createQuery("from I0022 where purchase_ID=?");
				logger.debug("2");
				Object arg1 = pID;
				q1.setParameter(1, arg1);
				logger.debug("3");
				ArrayList<I0022> resul = (ArrayList<I0022>) q1.getResultList();
				int jId = 0;
				if (resul.size() > 0) {
					int j = 0;
					logger.debug("1");
					jId = resul.get(j).getInvoice_ID();
					logger.debug("invoice Id:::::" + jId);
					Query q2 = null;
					q2 = entitymanager
							.createQuery("from I0023 where invoice_ID=?");
					q2.setParameter(1, jId);
					ArrayList<I0023> resu = (ArrayList<I0023>) q2
							.getResultList();
					if (resu.size() > 0) {
						int l = 0;
						l = resu.get(l).getPayment_ID();
						int k = 0;
						String f = "0";
						logger.debug("11111111111");
						logger.debug("baying amount::::"
								+ purchaseOrder.remaining);
						f = purchaseOrder.getRemaining();
						logger.debug("ffffffffff" + f);
						String balance = "0";
						String paid = resu.get(k).getPaidAmount();
						balance = resu.get(k).getBalanceAmount();
						logger.debug("balance:::" + balance);
						if (new BigDecimal(f)
								.compareTo(new BigDecimal(balance)) == 1) {
							throw new DemoException(
									"* The Amount you Entered is Above than the Need ");
						} else {
							if (new BigDecimal(balance).subtract(
									new BigDecimal(f)).compareTo(
									BigDecimal.valueOf(0)) == 0) {
								for (I0016 i0016 : result) {
									hpid = i0016.getHas_purchase_ID();
									logger.debug("inside balance-f:::::");
									logger.debug("payable Amount::"+ purchaseOrder.getPayableAmount());
									I0023 payment = entitymanager.find(I0023.class, l);
									payment.setPaidAmount(purchaseOrder.getPayableAmount());
									payment.setBalanceAmount(""+ BigDecimal.valueOf(0));
									payment.setStatus("paid");
									if (new BigDecimal(paid).compareTo(BigDecimal.valueOf(0)) == 0) {
										payment.setPaymentType(purchaseOrder.getPaymentType());
										payment.setBankName(purchaseOrder.getBankname());
										payment.setCardNo(purchaseOrder.getCardno());
										payment.setChequeDate(purchaseOrder.getChequedate());
										payment.setChequeNo(purchaseOrder.getChequeno());
										payment.setAccountNo(purchaseOrder.getAccno());
										payment.setAccountType(purchaseOrder.getAccounttype());
										payment.setAccountDescription(purchaseOrder.getAccountdescription());
									}
									entitymanager.merge(payment);
									logger.debug("merrrrrrrrrrrrrrrgggggggggggeeeeeeee::::");
									logger.debug("pid:::::" + pID);
									I0016 productpurchase = entitymanager.find(
											I0016.class, hpid);
									productpurchase.setStatus2("paid");
									entitymanager.merge(productpurchase);
								}

							} else if (new BigDecimal(balance).compareTo(new BigDecimal(f)) == 1) {
								logger.debug("balance>f::::::");
								String newBalance = ""+ (new BigDecimal(balance).subtract(new BigDecimal(f)));
								balance = newBalance;
								String newPaid = ""+ (new BigDecimal(paid).add(new BigDecimal(f)));
								paid = newPaid;
								logger.debug("paid:::::::::::::::::::" + paid);
								logger.debug("balance:::::::::::::" + balance);
								I0023 payment = entitymanager.find(I0023.class,l);
								payment.setPaidAmount(paid);
								payment.setBalanceAmount(balance);
								payment.setPaymentType(purchaseOrder
										.getPaymentType());
								payment.setBankName(purchaseOrder.getBankname());
								payment.setCardNo(purchaseOrder.getCardno());
								payment.setChequeDate(purchaseOrder
										.getChequedate());
								payment.setChequeNo(purchaseOrder.getChequeno());
								payment.setAccountNo(purchaseOrder.getAccno());
								payment.setAccountType(purchaseOrder.getAccounttype());
								payment.setAccountDescription(purchaseOrder.getAccountdescription());
								entitymanager.merge(payment);
							}
						}

					} else {
						logger.debug("success");
					}
				} else {
					throw new DemoException("* Should  generate Invoice first ");
				}
			} else {
				throw new DemoException("*Invalid order number ");
			}
		}
		logger.debug("--------------$$$$$$$$$$$$$$------------Outside AccountOut paynow Dao -------------$$$$$$$$$$$$$$-----------");
		return null;

	}*/
	
	@Transactional(value = "transactionManager")
	public String payNow1(PurchaseOrder purchaseOrder) throws DemoException {
		logger.info("purchase pay dao ");
		Query v=null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		String status="";
		try{
			v=entitymanager.createQuery("from I0015 where temOrderNumber=? and user_ID=? and client_ID=? and (status='insert' or status='delivered')" );
			v.setParameter(1, purchaseOrder.getOrderNumber());
			v.setParameter(2, Integer.parseInt(userID));
			v.setParameter(3, clientID);
			List<I0015> i0015=(List<I0015>)v.getResultList();
			if(i0015.size()>0){
				v=entitymanager.createQuery("from I0016 where purchase_ID=?" );
				v.setParameter(1, i0015.get(0).getPurchase_ID());
				List<I0016> i0016=(List<I0016>)v.getResultList();
				if(i0016.size()>0){
					v=null;
					v = entitymanager.createQuery("from I0022 where purchase_ID=?");
					v.setParameter(1, i0015.get(0).getPurchase_ID());
					ArrayList<I0022> i0022 = (ArrayList<I0022>) v.getResultList();
					if (i0022.size() > 0) {
						v = entitymanager.createQuery("from I0023 where invoice_ID=?");
						v.setParameter(1, i0022.get(0).getInvoice_ID());
						ArrayList<I0023> i0023 = (ArrayList<I0023>) v.getResultList();
						if (i0023.size() > 0) {
							if(purchaseOrder.getPaymentTerms().equals("Cash")){
								I0023 payment = entitymanager.find(I0023.class,i0023.get(0).getPayment_ID());
								payment.setPaidAmount(purchaseOrder.getRemaining());
								payment.setBalanceAmount("0");
								payment.setPaymentType("Cash");
								payment.setAccountType(purchaseOrder.getAccounttype());
								payment.setAccountDescription(purchaseOrder.getAccountdescription());
								payment.setStatus("paid");
								entitymanager.merge(payment);
								entitymanager.flush();
								entitymanager.clear();
							}else{
								I0023 payment = entitymanager.find(I0023.class,i0023.get(0).getPayment_ID());
								payment.setPaidAmount(String.valueOf(new BigDecimal(i0023.get(0).getPaidAmount()).
										add(new BigDecimal(purchaseOrder.getRemaining()))));
								payment.setBalanceAmount(String.valueOf(new BigDecimal(purchaseOrder.getPayableAmount()).
										subtract(new BigDecimal(i0023.get(0).getPaidAmount()))));
								payment.setPaymentType(purchaseOrder.getPaymentType()); 
								payment.setAccountType(purchaseOrder.getAccounttype());
								payment.setBankName(purchaseOrder.getBankname());
								payment.setCardNo(purchaseOrder.getCardno());
								payment.setChequeDate(purchaseOrder.getChequedate());
								payment.setChequeNo(purchaseOrder.getChequeno());
								payment.setAccountNo(purchaseOrder.getAccno());
								payment.setAccountDescription(purchaseOrder.getAccountdescription());
								logger.info(""+payment.getBalanceAmount());
								if(payment.getBalanceAmount().equals("0")){
									payment.setStatus("paid");
									status="paid";
								}else{
									status="pending";
								}
								entitymanager.merge(payment);
								entitymanager.flush();
								entitymanager.clear();
							}
						}
						logger.info("status "+status);
						for (int i = 0; i < i0016.size(); i++) {
							if(purchaseOrder.getPaymentTerms().equals("Cash")){
								I0016 productpurchase = entitymanager.find(I0016.class, i0016.get(i).getHas_purchase_ID());
								productpurchase.setStatus2("paid");
								entitymanager.merge(productpurchase);
								entitymanager.flush();
								entitymanager.clear();
							}else{
								if(status.equals("paid")){
									I0016 productpurchase = entitymanager.find(I0016.class, i0016.get(i).getHas_purchase_ID());
									productpurchase.setStatus2("paid");
									entitymanager.merge(productpurchase);
									entitymanager.flush();
									entitymanager.clear();
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
						logger.error("Error --------------->"+e.getMessage());
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0016> purchaseOrderPayment(Date s, Date s1,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
		ArrayList<I0015> result = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {

			logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseOrderPayment dao-------------$$$$$$$$$$$$$$-----------");
			Query q = null;
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0015 where orderDate between ? and ? and (status=? or status='delivered') and client_ID=?");
			q1.setParameter(1, s);
			q1.setParameter(2, s1);
			q1.setParameter(3, "insert");
			q1.setParameter(4, clientID);
			result = (ArrayList<I0015>) q1.getResultList();
			if (result.size() > 0) {
				logger.info("result size---------->" + result.size());
				q = entitymanager
						.createQuery("from I0016 where orderDate between ? and ? and status=? and client_ID=?");
				q.setParameter(1, s);
				q.setParameter(2, s1);
				q.setParameter(3, "inserted");
				q.setParameter(4, clientID);
				purchaselist = (ArrayList<I0016>) q.getResultList();
				logger.info("excuted::::::");
				if (purchaselist.size() == 0) {
					throw new DemoException("No value found !");
				}

				else {
					int count = 0;
					for (I0015 i0015 : result) {
						PurchaseOrder purchaseOrder2 = new PurchaseOrder();
						logger.info("1");
						purchaseOrder2.setOrderDate(i0015.getOrderDate());
						logger.info("2");
						purchaseOrder2
								.setOrderNumber(i0015.getTemOrderNumber());
						logger.info("3");
						purchaseOrder2.setFirmName(i0015.getI0016s().get(0)
								.getI0031().getI0025().getVendorPhoneNumber());
						logger.info("4");
						purchaseOrder2.setPhonenumber(i0015.getI0016s().get(0)
								.getI0031().getI0025().getFirmName());
						logger.info("5");
						purchaseOrder2.setIndustry(i0015.getI0016s().get(0)
								.getI0031().getI0001().getIndustryProduct());
						logger.info("6");

						try {

							logger.info("--------------  index---------------");
							purchaseOrder2.setPayableAmount(""
									+ i0015.getI0022s().get(0).getI0023s()
											.get(0).getBalanceAmount());
							purchaseOrder2.setTotalPrice(""
									+ i0015.getI0022s().get(0).getI0023s()
											.get(0).getPayableAmount());

							logger.info("--------------else index---------------");

						} catch (Exception e) {
							logger.info("inside inner exception:::::::");
							purchaseOrder2.setPayableAmount(""
									+ i0015.getTotalPrice());
						}

						logger.info("8");
						purchaseOrder2.setStatus(i0015.getI0016s().get(0)
								.getStatus2());
						result4.add(purchaseOrder2);
						count++;
					}
				}
				logger.info("size 1===============>" + result.size());
				purchaseOrder.setResult4(result4);
				logger.info("size============>" + result4.size());
			}

			logger.info("--------------$$$$$$$$$$$$$$------------Outside purchaseOrderPayment dao-------------$$$$$$$$$$$$$$-----------");

		} catch (Exception e) {

			logger.info("<-----------------inside exception------------->");
			int count = 0;
			result4.clear();

			logger.error("Inside Exception", e);
			logger.info("size 1===============>" + result.size());
			purchaseOrder.setResult4(result4);
			logger.info("size============>" + result4.size());
			logger.info("inside exeption------------>" + e.getStackTrace());
		}
		return purchaselist;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0016> purchaseDeliveryStatus(String s,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseDeliveryStatus Dao-------------$$$$$$$$$$$$$$-----------");
		logger.info(s);
		ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
		ArrayList<I0015> result = null;
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			
			logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseOrderPayment dao-------------$$$$$$$$$$$$$$-----------");
			logger.info(s);
			Query q = null;
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0015 where temOrderNumber=? and status=? and client_ID=?");
			q1.setParameter(1, s);

			q1.setParameter(2, "insert");
			q1.setParameter(3, clientID);
			result = (ArrayList<I0015>) q1.getResultList();
			if (result.size() > 0) {
				logger.info("result size---------->" + result.size());
				q = entitymanager
						.createQuery("from I0016 where ordernumber=? and status=?  and status3=? and client_ID=?");
				q.setParameter(1, s);
				q.setParameter(2, "inserted");
				q.setParameter(3, "Waiting");
				q.setParameter(4, clientID);
				purchaselist = (ArrayList<I0016>) q.getResultList();
				logger.info("excuted::::::");
				if (purchaselist.size() == 0) {
					throw new DemoException("No value found !");
				}
				int count = 0;
				for (I0015 i0015 : result) {
					PurchaseOrder purchaseOrder2 = new PurchaseOrder();
					logger.info("1");
					purchaseOrder2.setOrderDate(i0015.getOrderDate());
					logger.info("2");
					purchaseOrder2.setOrderNumber(i0015.getTemOrderNumber());
					logger.info("3");
					purchaseOrder2.setFirmName(i0015.getI0016s().get(0)
							.getI0031().getI0025().getVendorPhoneNumber());
					logger.info("4");
					purchaseOrder2.setPhonenumber(i0015.getI0016s().get(0)
							.getI0031().getI0025().getFirmName());
					logger.info("5");
					purchaseOrder2.setIndustry(i0015.getI0016s().get(0)
							.getI0031().getI0001().getIndustryProduct());
					logger.info("6");
					purchaseOrder2.setTotalPrice("" + i0015.getTotalPrice());
					logger.info("7");

					purchaseOrder2.setPayableAmount(""
							+ i0015.getI0022s().get(0).getI0023s().get(0)
									.getBalanceAmount());
					logger.info("8");
					purchaseOrder2.setStatus(i0015.getI0016s().get(0)
							.getStatus());
					purchaseOrder2.setStatus2(i0015.getI0016s().get(0)
							.getStatus2());
					purchaseOrder2.setStatus3(i0015.getI0016s().get(0)
							.getStatus3());
					result4.add(purchaseOrder2);
					count++;
				}
				purchaseOrder.setResult4(result4);
				logger.info("size============>" + result4.size());
			}

			logger.info("--------------$$$$$$$$$$$$$$------------Outside purchaseOrderPayment dao-------------$$$$$$$$$$$$$$-----------");

		} catch (Exception e) {
			logger.info("<-----------------inside exception------------->");
			int count = 0;
			for (I0015 i0015 : result) {
				PurchaseOrder purchaseOrder2 = new PurchaseOrder();
				logger.info("1");
				purchaseOrder2.setOrderDate(i0015.getOrderDate());
				logger.info("2");
				purchaseOrder2.setOrderNumber(i0015.getTemOrderNumber());
				logger.info("3");
				purchaseOrder2.setFirmName(i0015.getI0016s().get(0).getI0031()
						.getI0025().getVendorPhoneNumber());
				logger.info("4");
				purchaseOrder2.setPhonenumber(i0015.getI0016s().get(0)
						.getI0031().getI0025().getFirmName());
				logger.info("5");
				purchaseOrder2.setIndustry(i0015.getI0016s().get(0).getI0031()
						.getI0001().getIndustryProduct());
				logger.info("6");
				purchaseOrder2.setTotalPrice("" + i0015.getTotalPrice());
				logger.info("7");

				purchaseOrder2.setPayableAmount("" + i0015.getTotalPrice());
				logger.info("8");
				purchaseOrder2.setStatus(i0015.getI0016s().get(0).getStatus());
				purchaseOrder2
						.setStatus2(i0015.getI0016s().get(0).getStatus2());
				purchaseOrder2
						.setStatus3(i0015.getI0016s().get(0).getStatus3());
				result4.add(purchaseOrder2);
				count++;
			}
			purchaseOrder.setResult4(result4);
			logger.info("size============>" + result4.size());
			logger.info("inside exeption------------>" + e.getStackTrace());
		}
		logger.info("--------------$$$$$$$$$$$$$$------------Outside purchaseDeliveryStatus Dao-------------$$$$$$$$$$$$$$-----------");
		return purchaselist;

	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0016> purchaseDeliveryStatus2(
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseDeliveryStatus2 Dao-------------$$$$$$$$$$$$$$-----------");

		Query q = null;
		q = entitymanager
				.createQuery("select temOrderNumber from I0015 where status=? ");
		q.setParameter(1, "insert");
		ArrayList<String> result1 = (ArrayList<String>) q.getResultList();
		logger.info("excuted::::::" + result1.size());
		if (result1.size() == 0) {
			throw new DemoException("no date found");
		}
		ArrayList<String> order1 = new ArrayList<String>();
		int count = 0;
		for (String i0015 : result1) {
			ArrayList<String> order = new ArrayList<String>();
			logger.info("1");

			order.add(i0015);
			logger.info("3");
			order1.add(order.get(0));

			count++;
		}

		logger.info("final count------------->" + order1);
		purchaseOrder.setOrder(order1);

		logger.info("--------------$$$$$$$$$$$$$$------------Outside purchaseDeliveryStatus2 Dao-------------$$$$$$$$$$$$$$-----------");
		return purchaselist;

	}

	@Transactional(value = "transactionManager")
	public String deliveryStatus(PurchaseOrder purchaseOrder)
			throws DemoException {
		Query q1 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q = null;
			q = entitymanager.createQuery("from I0015 where temOrderNumber=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, clientID);
			List<I0015> result = (List<I0015>) q.getResultList();
			int hID = 0;
			int i = 0;
			if (result.size() > 0) {
				if (result.get(i).getStatus().equalsIgnoreCase("delivered")) {
					RequestContext.getCurrentInstance().execute(
							"PF('delistatus').show();");
				} else {
					q1 = entitymanager
							.createQuery("from I0016 where ordernumber=?");
					q1.setParameter(1, purchaseOrder.getOrderNumber());
					ArrayList<I0016> result1 = (ArrayList<I0016>) q1
							.getResultList();
					if (result1.size() > 0) {
						int j = 0;
						for (I0016 i0016 : result1) {
							hID = result1.get(j).getHas_purchase_ID();
							I0016 haspurchase = entitymanager.find(I0016.class,
									hID);
							haspurchase.setStatus3("delivered");
							entitymanager.merge(haspurchase);
							j++;
						}
						i = result.get(i).getPurchase_ID();
						I0015 purchase = entitymanager.find(I0015.class, i);
						purchase.setStatus("delivered");
						entitymanager.merge(purchase);

					} else {
						throw new DemoException(
								"===============no value in join purachase============");

					}
					RequestContext.getCurrentInstance().execute(
							"PF('delistatus1').show();");
				}
			}

			return "";
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0015> purchaseOrderClose(ArrayList<I0015> purchaselist,
			PurchaseOrder purchaseOrder) throws DemoException {
		ArrayList<I0015> i0015 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null, q1 = null;
		;
		try {
			i0015 = new ArrayList<I0015>();
			q1 = entitymanager
					.createQuery("from I0015 where (status='insert' or status='delivered') and client_ID=?");
			q1.setParameter(1, clientID);
			ArrayList<I0015> purchase = (ArrayList<I0015>) q1.getResultList();
			if (purchase.size() > 0) {
				int purchaseId = 0;
				ArrayList<String> firmname = new ArrayList<String>();
				ArrayList<String> phoneNumber = new ArrayList<String>();
				for (I0015 i00152 : purchase) {
					purchaseId = i00152.getPurchase_ID();
					q = entitymanager
							.createQuery("from I0016 where purchase_ID=? and status=? and status2=? and status3=?");
					q.setParameter(1, purchaseId);
					q.setParameter(2, "inserted");
					q.setParameter(3, "paid");
					q.setParameter(4, "delivered");
					ArrayList<I0016> purchasejoin = (ArrayList<I0016>) q
							.getResultList();
					if (purchasejoin.size() > 0) {
						firmname.add(i00152.getI0016s().get(0).getI0031()
								.getI0025().getVendorPhoneNumber());
						phoneNumber.add(i00152.getI0016s().get(0).getI0031()
								.getI0025().getFirmName());
						i0015.add(i00152);

					}

				}
				purchaseOrder.setFirmname(firmname);
				purchaseOrder.setPhoneNumber(phoneNumber);
				firmname = null;
				phoneNumber = null;

			}

			return i0015;
		} catch (Exception e) {
			logger.info(e.getMessage());

			return null;
		} finally {
			q = null;

		}
	}

	@Transactional(value = "transactionManager")
	public String stockInForm(PurchaseOrder purchaseOrder) throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		q = entitymanager
				.createQuery("from I0016 where ordernumber=? and status3=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getOrderNumber());
		q.setParameter(2, "delivered");
		q.setParameter(3, clientID);
		List<I0016> result = (List<I0016>) q.getResultList();
		int hID = 0;
		if (result.size() > 0) {
			int i = 0;
			hID = result.get(i).getHas_purchase_ID();

			purchaseOrder.setOrderNumber(result.get(i).getOrdernumber());
			purchaseOrder.setVendor_phone_number(result.get(i).getI0031()
					.getI0025().getVendorPhoneNumber());
			purchaseOrder.setFirmName(result.get(i).getI0031().getI0025()
					.getFirmName());
			purchaseOrder.setProduct_name(result.get(i).getI0031().getI0001()
					.getProductName());
			purchaseOrder.setProduct_ID(result.get(i).getI0031().getI0001()
					.getProduct_ID());
			purchaseOrder.setQuantity(result.get(i).getI0015().getQuantity());
			purchaseOrder.setTargentDate(result.get(i).getI0015()
					.getTargentDate());
			purchaseOrder.setOrderDate(result.get(i).getI0015().getOrderDate());
			purchaseOrder.setBatchNumber(result.get(i).getI0031().getI0001()
					.getBatch());
			purchaseOrder.setSellingPrice(result.get(i).getI0031().getI0001()
					.getSellingPrice());
			Date d = result.get(i).getI0015().getTargentDate();

		} else {
			purchaseOrder.setOrderNumber("");
			purchaseOrder.setVendor_phone_number("");
			purchaseOrder.setFirmName("");
			purchaseOrder.setProduct_name("");
			purchaseOrder.setProduct_ID(0);
			purchaseOrder.setQuantity("");
			purchaseOrder.setTargentDate(null);
			purchaseOrder.setOrderDate(null);
			purchaseOrder.setSellingPrice("" + BigDecimal.valueOf(0));

			throw new DemoException("*No value Found");

		}
		return "";

	}

	@Transactional(value = "transactionManager")
	public String stockInForm1(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		q = entitymanager
				.createQuery("select ordernumber from I0016 where status3= and client_ID=?");
		q.setParameter(1, "delivered");
		q.setParameter(2, clientID);
		ArrayList<I0016> result = (ArrayList<I0016>) q.getResultList();
		int hID = 0;
		if (result.size() > 0) {
			purchaseOrder.setStockDrop(result);
		} else {
		}

		return "";
	}

	@Transactional(value = "transactionManager")
	public String addStock(PurchaseOrder purchaseOrder) throws DemoException {
		Query q = null;String status="";
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		q = entitymanager
				.createQuery("from I0016 where ordernumber=? and status3=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getOrderNumber());
		q.setParameter(2, "delivered");
		q.setParameter(3, clientID);
		List<I0016> result = (List<I0016>) q.getResultList();
		int id = 0;
		if (result.size() > 0) {
			int i = 0;
			id = result.get(i).getI0015().getPurchase_ID();
			Query q1 = null;
			q1 = entitymanager.createQuery("from I0017 where purchase_ID=?");
			q1.setParameter(1, id);
			List<I0017> resul = (List<I0017>) q1.getResultList();
			logger.info("1");
			if (resul.size() > 0) {
				logger.info("2");
				status="Exist";
				//throw new DemoException("*This Order is Already comes In");
			}
			I0017 stock = new I0017();
			stock.setStockInDate(purchaseOrder.getDeliveredDate());
			stock.setPurchaseDate(result.get(i).getOrderDate());
			stock.setI0015(entitymanager.find(I0015.class, id));
			entitymanager.persist(stock);
			entitymanager.close();
			logger.info("gooooooood");
			purchaseOrder.setStatus2("" + id);
		}

		return status;
	}

	@Transactional(value = "transactionManager")
	public String addStock3(PurchaseOrder purchaseOrder) throws DemoException {
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			List<String> result1 = new ArrayList<String>();
			Query q = null;
			q = entitymanager.createQuery("from I0015 where status=? and client_ID=?");
			q.setParameter(1, "delivered");
			q.setParameter(2, clientID);
			List<I0015> result = (List<I0015>) q.getResultList();
			int id = 0;
			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++)

				{
					int j = 0;
					id = result.get(i).getPurchase_ID();
					Query q1 = null;
					q1 = entitymanager
							.createQuery("from I0017 where purchase_ID=?");
					q1.setParameter(1, id);
					List<I0017> resul = (List<I0017>) q1.getResultList();
					if (resul.size() <= 0) {
						result1.add(result.get(i).getTemOrderNumber());
						purchaseOrder.setStockDrop1(result1);
					}
				}

			}
			return null;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "";
		}

	}

	@Transactional(value = "transactionManager")
	public List<I0016> addStock1(PurchaseOrder purchaseOrder)
			throws DemoException {
		I0018 batch = new I0018();
		List<I0016> resul = null;
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0016 where ordernumber=? and status3=? and client_ID=?");
			q1.setParameter(1, purchaseOrder.getOrderNumber());
			q1.setParameter(2, "delivered");
			q1.setParameter(3, clientID);
			resul = (List<I0016>) q1.getResultList();
			int jid = 0;
			if (resul.size() > 0) {
				int J = 0;

			} else {
				RequestContext.getCurrentInstance().execute(
						"PF('stockin1').show();");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return resul;
	}

	@Transactional(value = "transactionManager")
	public List<I0016> valueChange(List<I0016> resul,
			PurchaseOrder purchaseOrder, int J, I0018 batch) {
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			
			Query q1 = null;
			resul = null;
			q1 = entitymanager
					.createQuery("from I0016 where ordernumber=? and status3=? and client_ID=?");
			q1.setParameter(1, purchaseOrder.getOrderNumber());
			q1.setParameter(2, "delivered");
			q1.setParameter(3, clientID);
			resul = (List<I0016>) q1.getResultList();

			if (resul.size() > 0) {
				int pid = resul.get(0).getI0015().getPurchase_ID();
				purchaseOrder.setPurchaseid(pid);
				int jid = 0;
				jid = resul.get(0).getI0015().getI0017s().get(0).getStock_ID();
				Query q4 = null;
				q4 = entitymanager.createQuery("from I0018 where productName=? and client_ID=?");
				logger.info("2");
				q4.setParameter(1, resul.get(J).getI0031().getI0001()
						.getProductName());
				logger.info("3");
				q4.setParameter(2, clientID);
				List<I0018> lis = (List<I0018>) q4.getResultList();
				String batchname = null;

				if (lis.size() > 0) {
					batchname = lis.get(0).getProductName();
					if (batchname.equalsIgnoreCase(resul.get(J).getI0031()
							.getI0001().getProductName())) {

						int i = 0;
						i = lis.get(0).getBatch_ID();
						I0018 batch1 = entitymanager.find(I0018.class, i);
						batch1.setI0017(entitymanager.find(I0017.class, jid));
						batch1.setBatchName(resul.get(J).getI0031().getI0001()
								.getBatch());
						batch1.setProductName(resul.get(J).getI0031()
								.getI0001().getProductName());
						batch1.setUnitPrice(resul.get(J).getI0031().getI0001()
								.getSellingPrice());
						batch1.setStatus("insert");
						entitymanager.merge(batch1);

					}
				}

				else {

					batch.setI0017(entitymanager.find(I0017.class, jid));
					batch.setBatchName(resul.get(J).getI0031().getI0001()
							.getBatch());
					batch.setProductName(resul.get(J).getI0031().getI0001()
							.getProductName());
					batch.setUnitPrice(resul.get(J).getI0031().getI0001()
							.getSellingPrice());
					batch.setStatus("insert");
					batch.setClient_ID(clientID);
					entitymanager.persist(batch);
					entitymanager.flush();
				}
			}
		} catch (Exception e) {
						logger.error("Error --------------->"+e.getMessage());
			logger.info(e.getMessage());
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String addStock2(PurchaseOrder purchaseOrder, List<I0016> result,
			int count) throws DemoException {
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0016 where ordernumber=? and status3=? and client_ID=?");
			q1.setParameter(1, purchaseOrder.getOrderNumber());
			q1.setParameter(2, "delivered");
			q1.setParameter(3, clientID);
			List<I0016> resul = (List<I0016>) q1.getResultList();
			int jid = 0;
			if (resul.size() > 0) {
				int j = 0;

				for (I0016 i0016 : resul) {

					j++;
				}

				purchaseOrder.setResulp(resul);
				Query q2 = null;
				q2 = entitymanager.createQuery(" from I0018 where stock_ID=?");
				q2.setParameter(1, resul.get(0).getI0015().getI0017s().get(0)
						.getStock_ID());
				List<I0018> resu = (List<I0018>) q2.getResultList();
				String productName = null;
				if (resu.size() > 0) {
					purchaseOrder.setResup1(resu);

				}
				int temp = 0;
				int count2 = 0;
				count2 = count;
			}

			logger.info("result quantity---------->"
					+ result.get(count).getQuantity());
			logger.info("size result===========>" + result);
			logger.info("result===========>" + result);

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public String addStock4Normal(int batchid, Date due) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		I0019 barcode = new I0019();		
		barcode.setI0018(entitymanager.find(I0018.class, batchid));
		barcode.setStatus("barcode genterated");
		barcode.setDueDate(due);
		barcode.setClient_ID(clientID);
		entitymanager.persist(barcode);
		return null;
	}

	public List<I0018> salesDrop(List<I0018> batch) throws DemoException {
		logger.info("inside dao1");
		Query q = null;
		q = entitymanager
				.createQuery("select productName from I0018 where status=?");
		logger.info("1");
		q.setParameter(1, "insert");
		batch = q.getResultList();
		logger.info("out");
		return batch;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> stockView2(List<I0019> batch2, StockView stockView)throws DemoException {
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
			ArrayList<String> rolls = new ArrayList<String>();
			ArrayList<String> roll_quant = new ArrayList<String>();
			ArrayList<I0019> sample = new ArrayList<I0019>();
			ArrayList<StockView> stockList = new ArrayList<StockView>();
			Query q = null;
			q = entitymanager.createQuery("from I0018 where status=? and client_ID=?");
			q.setParameter(1, "insert");
			q.setParameter(2, clientID);
			List<I0018> result = (List<I0018>) q.getResultList();
			int id = 0;
			for (int i = 0; i < result.size(); i++) {
				StockView stock = new StockView();
				id = result.get(i).getBatch_ID();
				Query q1 = null;
				logger.info("1");
				q1 = entitymanager
						.createQuery("from I0019 where batch_ID=? and (status=?)");
				logger.info("2");
				q1.setParameter(1, id);
				q1.setParameter(2, "barcode genterated");
				logger.info("3");
				batch2 = (List<I0019>) q1.getResultList();

				if (batch2.size() > 0) {
					for (I0019 in : batch2) {

						String roll_quantity = in.getQuantity();
						roll_quant.add(roll_quantity);
						sample.add(in);
						stock.setS1(batch2.get(0).getI0018().getI0017().getI0015()
								.getI0016s().get(0).getI0031().getI0025()
								.getFirmName());
						stock.setS2(batch2.get(0).getI0018().getI0017().getI0015()
								.getI0016s().get(0).getI0031().getI0025()
								.getVendorPhoneNumber());
						stock.setUnitprice(batch2.get(0).getI0018().getUnitPrice());
					}
					Query q2 = null;
					q2 = entitymanager
							.createQuery("from I0001 where product_name=? and client_ID=?");
					
					q2.setParameter(1, result.get(i).getProductName());
					q2.setParameter(2, clientID);
					List<I0001> resultproduct = (List<I0001>) q2.getResultList();
					stock.setBuyingPrice(resultproduct.get(0).getAutualPrice());
					stock.setUnit(resultproduct.get(0).getUnit());
					stock.setProductname(resultproduct.get(0).getProductName());
					stockList.add(stock);
				}
				stockView.setSample(sample);
				stockView.setRoll_stock_in(roll_quant);
			}
			stockView.setDomain1(stockList);			
		
		}catch(Exception e){
						logger.error("Error --------------->"+e.getMessage());
		}
		return batch2;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> stockView(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		q = entitymanager
				.createQuery("from I0018 where batchName=? and status=? and client_ID=?");
		logger.info("1");
		q.setParameter(1, s);
		q.setParameter(2, "insert");
		q.setParameter(3, clientID);
		List<I0018> result = (List<I0018>) q.getResultList();
		int id = 0;
		if (result.size() > 0) {
			int i = 0;
			id = result.get(i).getBatch_ID();
			String product = "";
			product = result.get(i).getProductName();
			Query q2 = null;
			q2 = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
			logger.info("1");
			q2.setParameter(1, product);
			q2.setParameter(2, clientID);
			List<I0001> resultproduct = (List<I0001>) q2.getResultList();
			if (resultproduct.size() > 0) {
				stockView.setBuyingPrice(resultproduct.get(0).getAutualPrice());
			}
		}

		Query q1 = null;
		q1 = entitymanager
				.createQuery("from I0019 where batch_ID=? and status=?");
		q1.setParameter(1, id);
		q1.setParameter(2, "barcode genterated");
		batch2 = (List<I0019>) q1.getResultList();

		if (batch2.size() > 0) {
			stockView.setS1(batch2.get(0).getI0018().getI0017().getI0015()
					.getI0016s().get(0).getI0031().getI0025().getFirmName());
			stockView.setS2(batch2.get(0).getI0018().getI0017().getI0015()
					.getI0016s().get(0).getI0031().getI0025()
					.getVendorPhoneNumber());
		}

		logger.info("out");

		return batch2;
	}

	@Transactional(value = "transactionManager")
	public List<I0018> stockView1(List<I0018> batch2) throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		q = entitymanager
				.createQuery("select batchName from I0018 where status=? and client_ID=?");
		logger.info("1");
		q.setParameter(1, "insert");
		q.setParameter(2, clientID);
		batch2 = (List<I0018>) q.getResultList();
		return batch2;
	}

	@Transactional(value = "transactionManager")
	public String addDamage(PurchaseOrder purchaseOrder) throws DemoException {

		logger.info("inside dao1");
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.info("batch:::::::::" + purchaseOrder.getBatch());
		q = entitymanager
				.createQuery("from I0018 where productName=? and status=? and client_ID=?");
		logger.info("1");
		q.setParameter(1, purchaseOrder.getBatch());
		q.setParameter(2, "insert");
		q.setParameter(3, clientID);
		List<I0018> result = (List<I0018>) q.getResultList();
		int id = 0;
		if (result.size() > 0) {
			int i = 0;
			id = result.get(i).getBatch_ID();
		}

		if (result.size() == 0) {
			throw new DemoException("this batch is not present:::::");
		}

		Query q1 = null;
		logger.info("1");
		q1 = entitymanager
				.createQuery("from I0019 where batch_ID=? and status=?");
		logger.info("2");
		q1.setParameter(1, id);
		q1.setParameter(2, "barcode genterated");
		logger.info("3");
		List<I0019> batch2 = (List<I0019>) q1.getResultList();
		int bID = 0;
		int stockid = 0;
		String olddamageQuan = "0";
		String remQuan = "0";
		String stockQuan = "0";
		String damageQuan = "0";
		if (batch2.size() > 0) {

			bID = batch2.get(0).getBar_code_ID();
			stockQuan = batch2.get(0).getQuantity();
			damageQuan = purchaseOrder.getQuantity();
			olddamageQuan = batch2.get(0).getDamgeStatus();
			remQuan = ""
					+ (new BigDecimal(stockQuan).subtract(new BigDecimal(
							damageQuan)));
			I0019 bar = entitymanager.find(I0019.class, bID);
			if (olddamageQuan.equals("0")) {
				olddamageQuan = "0";
			} else {
				damageQuan = ""
						+ new BigDecimal(damageQuan).add(new BigDecimal(
								olddamageQuan));
			}
			bar.setDamgeStatus(damageQuan);
			bar.setQuantity(remQuan);
			entitymanager.merge(bar);

			I0020 damage = new I0020();
			damage.setI0017(entitymanager.find(I0017.class, stockid));
			damage.setClient_ID(clientID);
			entitymanager.persist(damage);
			logger.info("final for::::::");
		}
		if (batch2.size() == 0) {
			throw new DemoException(
					"this product is sold claim in first in first out:::::::");
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String addDamage1(PurchaseOrder purchaseOrder) throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		q = entitymanager
				.createQuery("from I0018 where (status='insert' or status='closed') and productName=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getBatch());
		q.setParameter(2, clientID);
		List<I0018> result = (List<I0018>) q.getResultList();
		int id = 0;
		if (result.size() > 0) {
			int i = 0;
			id = result.get(i).getBatch_ID();
			purchaseOrder.setProduct_name(result.get(i).getProductName());
			purchaseOrder.setBatch(result.get(i).getBatchName());
			purchaseOrder.setSellingPrice("" + result.get(i).getUnitPrice());
		}

		else if (result.size() == 0) {
			throw new DemoException("Invalid Batch name");

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> stockdamageView(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		logger.info("inside dao1");
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.info("ssssssss" + s);
		q = entitymanager
				.createQuery("from I0018 where (status='insert' or status='closed') and productName=? and client_ID=?");
		logger.info("1");
		q.setParameter(1, s);
		q.setParameter(2, clientID);
		List<I0018> result = (List<I0018>) q.getResultList();
		int id = 0;
		if (result.size() > 0) {
			int i = 0;
			id = result.get(i).getBatch_ID();
		}
		logger.info("id::::::::" + id);

		Query q1 = null;
		logger.info("1");
		q1 = entitymanager.createQuery("from I0019 where batch_ID=?");
		logger.info("2");
		q1.setParameter(1, id);
		logger.info("3");
		batch2 = q1.getResultList();

		if (batch2.size() > 0) {
			String damageQ = "0";
			damageQ = batch2.get(0).getDamgeStatus();
			stockView.setDamageQ(damageQ);
			logger.info("comes in:::");

			logger.info("firm name"
					+ batch2.get(0).getI0018().getI0017().getI0015()
							.getI0016s().get(0).getI0031().getI0025()
							.getVendorPhoneNumber());
			stockView.setS1(batch2.get(0).getI0018().getI0017().getI0015()
					.getI0016s().get(0).getI0031().getI0025().getFirmName());
			stockView.setS2(batch2.get(0).getI0018().getI0017().getI0015()
					.getI0016s().get(0).getI0031().getI0025()
					.getVendorPhoneNumber());
			logger.info("comes in:::");

		}

		return batch2;
	}

	@Transactional(value = "transactionManager")
	public String salesOrder1(PurchaseOrder purchaseOrder) throws DemoException {

		Query q11 = null;
		logger.info("customer name----------->>MMNN3>>>"
				+ purchaseOrder.getCustomername());
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		q11 = entitymanager
				.createQuery("from I0021 where natureOfBusiness='Normal sales' and client_ID=?");
		q11.setParameter(1, clientID);
		List<I0021> resultt = (List<I0021>) q11.getResultList();
		// logger.info("out1111111111"+resultt.get(0));
		int count = 0;
		int count1 = 1;

		if (resultt.size() > 0) {
			for (I0021 re : resultt) {
				count++;

			}
			count1 = resultt.get(resultt.size() - 1).getSales_ID() + 1;
		}
		logger.info("count1::::::::::" + count1);

		purchaseOrder.setCounter(count1);

		purchaseOrder.getCounter();

		String salesOrderNo = "";

		if (resultt.size() <= 0) {
			salesOrderNo = GenerateEmployee.getSalesOrderNo(1);
			logger.info("<<<<<<<salesOrderNo>>>>>>>>" + salesOrderNo);
		} else {
			salesOrderNo = GenerateEmployee.getSalesOrderNo(resultt.size() + 1);
		}
		purchaseOrder.setSalesIdReference(salesOrderNo);
		purchaseOrder.setCount(count1);
		return null;
	}

	@Transactional(value = "transactionManager")
	public String salesOrder2(PurchaseOrder purchaseOrder) throws DemoException {
		try {
			Query q11 = null;

			q11 = entitymanager.createQuery("from I0021");
			List<I0021> resultt = (List<I0021>) q11.getResultList();
			int pkid = 0;

			if (resultt.size() > 0)
				logger.info("inside if");
			{
				for (I0021 re : resultt) {
					pkid++;
				}
			}
			purchaseOrder.setSalesId(pkid);
		} finally {

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public List<I0018> salesOrder3(List<I0018> batchProductName3)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		
		Query q = null;
		q = entitymanager
				.createQuery("select productName from I0018 where status='insert' and client_ID=?");
		logger.info("1");
		q.setParameter(1, clientID);
		batchProductName3 = q.getResultList();
		HashSet<I0018> hashList=new HashSet<I0018>(batchProductName3);
		batchProductName3.clear();
		batchProductName3.addAll(hashList);
		return batchProductName3;

	}

	@Transactional(value = "transactionManager")
	public List<String> productVendor(List<String> batchProductName3)throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<String> resul = new ArrayList<String>();
		resul.add("");
		Query q = null;
		q = entitymanager
				.createQuery("select productName from I0018 where status='insert' and client_ID=?");
		q.setParameter(1, clientID);
		List<String> batchProductName = q.getResultList();
		if (batchProductName.size() > 0) {
			int k = 0;
			for (String string : batchProductName) {

			}

		}

		return batchProductName;

	}

	@Override
	@Transactional(value = "transactionManager")
	public List<String> getnames1(List<String> names1) throws DemoException {
		List<String> result = new ArrayList<String>();
		result.add("");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		q = entitymanager
				.createQuery("select city from I0032 where status='in' and client_ID=?");
		q.setParameter(1, clientID);
		List<String> batchProductName1 = q.getResultList();
		return batchProductName1;

	}

	@Override
	@Transactional(value = "transactionManager")
	public List<String> getnames(List<String> names) throws DemoException {
		List<String> result = new ArrayList<String>();
		result.add("");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		q = entitymanager
				.createQuery("select cityName from I0025 where status='i' and client_ID=?");
		q.setParameter(1, clientID);
		List<String> batchProductName1 = q.getResultList();
		return batchProductName1;

	}

	@Transactional(value = "transactionManager")
	public List<String> productVendor1(List<String> batchProductName3, String s)
			throws DemoException {
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			List<String> resul = new ArrayList<String>();

			Query q = null;
			q = entitymanager.createQuery(" from I0025 where firmName=? and client_ID=?");
			q.setParameter(1, s);
			q.setParameter(2, clientID);
			List<I0025> batchProductName = (List<I0025>) q.getResultList();
			if (batchProductName.size() > 0) {
				int q2 = 0;
				for (I0025 string : batchProductName) {
					q2 = string.getVendor_ID();
					Query q1 = null;
					q1 = entitymanager
							.createQuery(" from I0031 where vendor_ID=? ");
					logger.info("1");
					q1.setParameter(1, q2);
					// q.setParameter(2, "insert");
					List<I0031> result = (List<I0031>) q1.getResultList();
					if (result.size() > 0) {
						String j = result.get(0).getI0001().getProductName();
						resul.add(j);
					}

				}
				batchProductName3 = resul;

			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return batchProductName3;

	}

	@Transactional(value = "transactionManager")
	public String salesOrder4(PurchaseOrder purchaseOrder) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null, q2 = null;
		String stockquan = "";
		q = entitymanager
				.createQuery("from I0018 where productName=? and status=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getBatchProductName());
		q.setParameter(2, "insert");
		q.setParameter(3, clientID);
		List<I0018> batchProduct = (List<I0018>) q.getResultList();
		int id = 0;
		if (batchProduct.size() > 0) {
			int i = 0;

			q2 = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
			logger.info("1");
			q2.setParameter(1, purchaseOrder.getBatchProductName());
			q2.setParameter(2, clientID);
			List<I0001> batchProduct1 = (List<I0001>) q2.getResultList();

			logger.info("batch size====>" + batchProduct.size());
			id = batchProduct.get(i).getBatch_ID();
			logger.info("innnnnnnnnnnnn" + id);
			purchaseOrder.setProduct_ID(batchProduct.get(i).getI0017()
					.getI0015().getI0016s().get(i).getI0031().getI0001()
					.getProduct_ID());
			logger.info("1");
			purchaseOrder.setSellingPrice(batchProduct1.get(i)
					.getSellingPrice());
			purchaseOrder.setMarginPrice((batchProduct.get(i).getI0017()
					.getI0015().getI0016s().get(i).getI0031().getI0001()
					.getMarginPrice()));

		}
		if (batchProduct.size() == 0) {
			throw new DemoException("*This Product Not In Stock");
		}
		String quant = purchaseOrder.getQuantity();
		Query q1 = null;
		q1 = entitymanager.createQuery("from I0019 where batch_ID=? and status=?");
		q1.setParameter(1, id);
		q1.setParameter(2, "barcode genterated");
		List<I0019> batchProduct1 = (List<I0019>) q1.getResultList();
		int barcodeID = 0;
		int count = 0;

		if (batchProduct1.size() > 0) {
			int i = 0;
			stockquan = batchProduct1.get(0).getQuantity();
			barcodeID = batchProduct1.get(i).getBar_code_ID();
			for (I0019 b : batchProduct1) {
				count++;
			}

		} else {
			I0018 batchClose = entitymanager.find(I0018.class, id);
			batchClose.setStatus("closed");
		}
		purchaseOrder.setQuantity1(stockquan);
		purchaseOrder.setBatchID(id);
		;
		return null;
	}

	@Transactional(value = "transactionManager")
	public String salesOrder5(PurchaseOrder purchaseOrder) throws DemoException {
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			Query q = null;
			q = entitymanager
					.createQuery("from I0018 where productName=? and status=? and client_ID=?");
			logger.info("1");
			q.setParameter(1, purchaseOrder.getBatchProductName());
			q.setParameter(2, "insert");
			q.setParameter(3, clientID);
			List<I0018> batchProduct = (List<I0018>) q.getResultList();
			int id = 0;
			if (batchProduct.size() > 0) {
				int i = 0;

				id = batchProduct.get(i).getBatch_ID();

			}

			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0019 where batch_ID=? and status=?");
			q1.setParameter(1, id);
			q1.setParameter(2, "barcode genterated");
			List<I0019> batchProduct1 = (List<I0019>) q1.getResultList();
			int barcodeID = 0;
			if (batchProduct1.size() > 0) {
				int i = 0;

				barcodeID = batchProduct1.get(i).getBar_code_ID();

			}
			I0019 barcode = entitymanager.find(I0019.class, barcodeID);
			barcode.setStatus("Solded");
			barcode.setI0021(entitymanager.find(I0021.class,
					purchaseOrder.getCount()));
			entitymanager.merge(barcode);

			I0021 sale = entitymanager.find(I0021.class,
					purchaseOrder.getCount());
			sale.setCrossTotal(purchaseOrder.getCrosstotal1());
			entitymanager.merge(sale);
		} catch (Exception e) {

		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> stockoutForm(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			logger.info("inside dao1");
			Query q = null;
			Query q1 = null;
			Query q2 = null;
			String totprice = "";
			String unitprice = "";
			int id1 = 0;
			int id2 = 0;
			List<StockView> domain1 = new ArrayList<StockView>();
			List<I0019> rolling = new ArrayList<I0019>();
			logger.info("ssssssss" + s);

			Query qunit = null;
			qunit = entitymanager
					.createQuery("from I0001 where product_name=? and client_ID=?");
			qunit.setParameter(1, s);
			qunit.setParameter(2, clientID);
			List<I0001> qunitresult = (List<I0001>) qunit.getResultList();
			String unit = "";
			if (qunitresult.size() > 0) {
				int j = 0;
				unit = qunitresult.get(j).getUnit();
			}
			q = entitymanager.createQuery("from I0018 where productName=? and client_ID=?");
			logger.info("1");
			q.setParameter(1, s);
			q.setParameter(2, clientID);
			List<I0018> result = (List<I0018>) q.getResultList();
			int id = 0;

			if (result.size() > 0) {
				id1 = result.get(0).getBatch_ID();

				q1 = entitymanager
						.createQuery("from I0019 where batch_ID=? and (status='barcode genterated')");
				q1.setParameter(1, id1);
				batch2 = q1.getResultList();
				if (batch2.size() > 0) {
					id2 = batch2.get(0).getBar_code_ID();
					Query q112 = null;
					int ides = 0;
					String rollid = "";
					logger.info("Before Barcode id----" + id2);
					rolling.clear();
					stockView.setDomain2(null);
					for (I0019 ing1 : batch2) {
						logger.info("====================================================");

						ides = ing1.getBar_code_ID();
						rollid = ing1.getRoll_ID();
						logger.info("After Barcode id-----" + ides);
						logger.info("After Barcode id-----" + rollid);
						q112 = entitymanager
								.createQuery("from SalesRecord where bar_code_id=?");
						q112.setParameter(1, ides);
						List<SalesRecord> salerec1 = (List<SalesRecord>) q112
								.getResultList();
						logger.info("Barcode Size " + salerec1.size());
						Format formatter = new SimpleDateFormat("dd/MM/yyyy");

						if (salerec1.size() > 0) {

							for (int i = 0; i < salerec1.size(); i++) {
								unitprice = salerec1.get(i).getSell_price();
								logger.info("Inside Database-----");
								logger.info("getSalesOrderDate  "
										+ salerec1.get(i).getI0021()
												.getSalesOrderDate());
								String sing = formatter.format(salerec1.get(i)
										.getI0021().getSalesOrderDate());
								logger.info("getSoldQuantity  "
										+ salerec1.get(i).getSoldQuantity());
								logger.info("product_name  " + s);
								logger.info("unitprice  " + unitprice);
								logger.info("-----------------------------------------------");
								logger.info("sid-- :"
										+ salerec1.get(i).getSalesRecordId());
								BigDecimal b1 = new BigDecimal(salerec1.get(i)
										.getSoldQuantity())
										.subtract(new BigDecimal(salerec1
												.get(i).getReturnQuantity()));
								b1 = b1.setScale(2, RoundingMode.CEILING);
								logger.info("b1" + b1);
								logger.info("-----------------------------------------------");
								totprice = ""
										+ (new BigDecimal(unitprice))
												.multiply(b1);
								logger.info("totprice  " + totprice);
								StockView sOut = new StockView();
								// sOut.setStockinDate(""+salerec1.get(i).getI0021().getSalesOrderDate());
								sOut.setDate(sing);
								// sOut.setSaleQuan(salerec1.get(i).getSoldQuantity());
								sOut.setSaleQuan(b1.toString());
								sOut.setProductname(s);
								sOut.setUnitprice(unitprice);
								totprice = ""
										+ (new BigDecimal(unitprice))
												.multiply(new BigDecimal(sOut
														.getSaleQuan()));
								sOut.setTotalPrice(totprice);
								sOut.setRollID(rollid);
								sOut.setUnit(unit);
								domain1.add(sOut);
								stockView.setDomain2(domain1);

								logger.info("Outside Database-----");

							}
							logger.info("-------------------------------------");
						}

						rolling.add(ing1);
						logger.info("************************************************");
						stockView.setS1(batch2.get(0).getI0018().getI0017()
								.getI0015().getI0016s().get(0).getI0031()
								.getI0025().getFirmName());
						stockView.setS2(batch2.get(0).getI0018().getI0017()
								.getI0015().getI0016s().get(0).getI0031()
								.getI0025().getFirmRegistrationNumber());
					}
					stockView.setSample(null);
					stockView.setSample(rolling);

				}
			}

		} catch (IllegalArgumentException p) {
			logger.error("inside exception",p);
		}

		return batch2;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> stockInForm(List<I0019> batch2, String s)
			throws DemoException {
		logger.info("inside dao1");

		Query q1 = null;
		logger.info("1");
		q1 = entitymanager.createQuery("from I0019 where status=? or status=?");
		logger.info("2");
		q1.setParameter(1, "Solded");
		q1.setParameter(2, "Delivered");
		logger.info("3");
		batch2 = q1.getResultList();

		if (batch2.size() == 0) {
			throw new DemoException("");
		}

		return batch2;
	}

	@Transactional(value = "transactionManager")
	public String salesOrdercancelForm(PurchaseOrder purchaseOrder)
			throws DemoException {

		Query q = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("from I0021 where salesOrderNumber=? and (status='insert' or status='Delivered')");
		q.setParameter(1, purchaseOrder.getSalesIdReference());

		logger.info("inside sales order cancel::::");
		List<I0021> result = (List<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		if (result.size() > 0) {
			logger.info("1");
			pkid = result.get(i).getSales_ID();
			logger.info("2");
			Query q1 = null;

			q1 = entitymanager.createQuery("from SalesRecord where sales_ID=?");
			logger.info("3");
			q1.setParameter(1, pkid);
			logger.info("4");
			List<SalesRecord> resul1 = (List<SalesRecord>) q1.getResultList();
			logger.info("5");
			purchaseOrder.setResul1(resul1);
			purchaseOrder.setResult(result);
			logger.info("6");
			// resul.get(i).getI0018().getI0017().getI0015().get

		} else {
			logger.info("inside 5 else loop--------->>>>>");
			throw new DemoException("*already Cancelled Or delivered");
		}
		return null;

	}

	@Transactional(value = "transactionManager")
	public String salesOrderViewproduct(PurchaseOrder purchaseOrder)
			throws DemoException {
		List<PurchaseOrder> resulfinal1 = new ArrayList<PurchaseOrder>();
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		try{
			q = entitymanager.createQuery("from I0021 where sales_ID=?");
			q.setParameter(1, purchaseOrder.getSalesId());
			logger.info("------------------salesOrderViewproduct------------------");
			List<I0021> result = (List<I0021>) q.getResultList();
			int pkid = 0;
			int i = 0;
			String shipcmpny = "";
			String shipcost = "";
			Date due;
			if (result.size() > 0) {
				int serialNo = 0;

				logger.info("1");
				pkid = result.get(i).getSales_ID();
				try {
					shipcmpny = result.get(i).getShipping_company();
					shipcost = result.get(i).getShippingCharge();
					due = result.get(i).getDeliveryDate();
					purchaseOrder.setTargentDate(due);
					purchaseOrder.setShipping_company(shipcmpny);
					purchaseOrder.setShipping_charge(shipcost);
					purchaseOrder.setDiscType(result.get(i).getDisctype());
					purchaseOrder.setDiscAmnt(result.get(i).getDiscamnt());
					purchaseOrder.setStatus(result.get(i).getStatus());
					purchaseOrder.setCurrency(result.get(0).getCurrency());
					purchaseOrder.setCurrencyAmount(result.get(0).getCurrencyAmount());
					logger.info("status in dao" + purchaseOrder.getStatus());
					logger.info("Company" + "--" + "Cost" + "--" + shipcmpny
							+ "due date" + due);
				} catch (NullPointerException nl) {
					shipcmpny = "";
					shipcost = "";
					// due="";
				}
				logger.info("2");
				Query q1 = null;
				q1 = entitymanager
						.createQuery("from SalesRecord where sales_ID=? and (soldStatus='solded' or soldStatus='delivered') ");

				logger.info("3");
				q1.setParameter(1, pkid);
				logger.info("4");
				List<SalesRecord> resul = (List<SalesRecord>) q1.getResultList();
				logger.info("res Size checking" + resul.size());
				logger.info("5");
				if (resul.size() > 0) {
					serialNo++;
					String temp = "";
					int ii = 0;
					String temp1 = "";
					BigDecimal valref1 = BigDecimal.ZERO;
					for (SalesRecord rec : resul) {
						logger.info("-------------Entry--------------");

						PurchaseOrder Order = new PurchaseOrder();
						{

							int counter = 1;
							String s = rec.getI0019().getI0018().getProductName();
							String price = rec.getSell_price();

							logger.info("product name----!!!!!!--------->" + s);
							logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
									+ purchaseOrder.getProduct_name());
							if (s.equals(purchaseOrder.getProduct_name())) {
								logger.info("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
							} else {
								logger.info("nossssssssssss");
							}

							try {
								temp = s;
								logger.info("inside else");
								Order.setSerialNo(serialNo);
								logger.info("serial num" + Order.getSerialNo());
								Order.setProduct_name(rec.getI0019().getI0018()
										.getProductName());
								Order.setOrderDate(result.get(0)
										.getSalesOrderDate());
								Order.setBatchProductName(rec.getI0019().getI0018()
										.getBatchName());

								String unit = "";
								Query qunit = null;
								qunit = entitymanager
										.createQuery("from I0001 where batch=?  and client_ID=?");
								qunit.setParameter(1, rec.getI0019().getI0018()
										.getBatchName());
								qunit.setParameter(2, clientID);
								List<I0001> runit = (List<I0001>) qunit
										.getResultList();
								if (runit.size() > 0) {
									unit = runit.get(0).getUnit();
									logger.info("unit name " + unit);
								}

								logger.info("Roll id in dao-----"
										+ Order.getRollID());
								BigDecimal qty = (new BigDecimal(
										rec.getSoldQuantity()))
										.subtract(new BigDecimal(rec
												.getReturnQuantity()));
								qty = qty.setScale(2, RoundingMode.CEILING);
								Order.setQuantity(qty.toString());
								Order.setQuantity1(qty.toString());
								Order.setDiscountzAmount(rec.getDiscountAmount());
								Order.setUnit(unit);
								Order.setOrderDate(result.get(0)
										.getSalesOrderDate());
								Order.setCurrency(result.get(0).getCurrency());
								purchaseOrder.setCustomerName(result.get(0)
										.getCustomerName());
								purchaseOrder.setAddress(result.get(0)
										.getShipingAddress());
								purchaseOrder.setTelephonenumber(result.get(0)
										.getPhoneNumber());
								logger.info("==daaaa==="
										+ result.get(0).getCrossTotal());

								String totPriz = "";
								totPriz = ""
										+ ((qty.multiply(new BigDecimal(rec
												.getSell_price())))
												.subtract(new BigDecimal(rec
														.getDiscountAmount())));
								logger.info("totpriz -->> " + totPriz);

								purchaseOrder.setCrosstotal1(result.get(0)
										.getCrossTotal());
								purchaseOrder.setTotalPrice(totPriz);
								purchaseOrder.setSalesIdReference(result.get(0)
										.getSalesOrderNumber());
								// Order.setSellingPrice(""+rec.getI0019().getI0018().getUnitPrice());
								Order.setSellingPrice("" + rec.getSell_price());
								BigDecimal valref = BigDecimal.ZERO;
								valref = (new BigDecimal(rec.getSell_price())
										.multiply(qty)).subtract(new BigDecimal(rec
										.getDiscountAmount()));
								Order.setNetReference(valref.toString());
								valref1 = valref1.add(valref);
								purchaseOrder.setgTotal(valref1.toString());

								Order.setTotalPrice(totPriz);
								logger.info("total price--------->"
										+ Order.getTotalPrice()
										+ "~~~~purchase~~~~"
										+ purchaseOrder.getTotalPrice());
								counter = 1;
								int s3 = 0;
								int s4 = 0;
								s3 = rec.getI0019().getI0018().getBatch_ID();
								s4 = result.get(0).getSales_ID();
								logger.info("s3------------->" + s3);
								logger.info("s4------------->" + s4);
								Query q3 = null;
								q3 = entitymanager
										.createQuery("from I0019 where batch_ID=? and (roll_status='Sold' or roll_status='Insert')");
								logger.info("3");
								q3.setParameter(1, s3);
								logger.info("4");
								List<I0019> resu = (List<I0019>) q3.getResultList();
								if (resu.size() > 0) {
									Order.setQuantity(qty.toString());
								}
								resulfinal1.add(Order);
								serialNo++;
							} catch (IndexOutOfBoundsException ind) {
								logger.info("Inside Exception");
								// }
							}
						}
						logger.info("final size------------->" + resulfinal1.size());
						purchaseOrder.setResulfinal(resulfinal1);
					}
					logger.info("before If");
					try {
						logger.info("inside else");
						logger.info("Paid amount-!!!!!!!!!!!!!!!!!!!!---------->"
								+ result.get(0).getI0022s().get(0).getI0023s()
										.get(0).getPaidAmount());
						purchaseOrder.setActualPrice(""
								+ result.get(0).getI0022s().get(0).getI0023s()
										.get(0).getPaidAmount());
					} catch (Exception e) {
						purchaseOrder.setActualPrice("null");
						logger.info("exception--->Index out bound");
					}

					purchaseOrder.setResul1(resul);
					purchaseOrder.setResult(result);
					logger.info("6");
				} else {
					logger.info("pkid" + pkid);
					logger.info("INSIDE ELSE=====================");
					Query q11 = null;
					q11 = entitymanager
							.createQuery("from SalesRecord where sales_ID=? and (soldStatus='Solded' or soldStatus='delivered')  ");
					logger.info("3");
					q11.setParameter(1, pkid);
					logger.info("4");
					List<SalesRecord> res = (List<SalesRecord>) q11.getResultList();
					logger.info("5");
					if (res.size() > 0) {

						logger.info("inside if::::");
						String temp = "";
						int ii = 0;
						String temp1 = "";
						for (SalesRecord rec1 : res) {
							PurchaseOrder Order = new PurchaseOrder();
							{
								int counter = 1;

								String s = rec1.getI0019().getI0018()
										.getProductName();
								logger.info("product name---|||||!!!!!!||||||||||---------->"
										+ s + "=================" + temp);
								if (s.equals(purchaseOrder.getProduct_name())) {
									logger.info("inside daooooooooooooooooooo");
								}
								if (s == temp) {
									logger.info("inside if");
									counter++;
									serialNo++;
									Order.setQuantity("" + counter);
									Order.setSerialNo(serialNo);
									logger.info("counter--------->" + counter);
									logger.info("Serial num--------->" + serialNo);

								} else {
									temp = s;
									logger.info("inside else");

									Order.setProduct_name(rec1.getI0019()
											.getI0018().getProductName());
									Order.setOrderDate(result.get(0)
											.getSalesOrderDate());
									Order.setBatchProductName(rec1.getI0019()
											.getI0018().getBatchName());

									String unit = "";
									Query qunit1 = null;
									qunit1 = entitymanager
											.createQuery("from I0001 where batch=? and client_ID=?");
									qunit1.setParameter(1, rec1.getI0019()
											.getI0018().getBatchName());
									qunit1.setParameter(2, clientID);
										
									List<I0001> runit1 = (List<I0001>) qunit1
											.getResultList();
									if (runit1.size() > 0) {
										unit = runit1.get(0).getUnit();
										logger.info("unit name " + unit);
									}
									Order.setRollID(rec1.getI0019().getRoll_ID());
									BigDecimal qty1 = (new BigDecimal(
											rec1.getSoldQuantity()))
											.subtract(new BigDecimal(rec1
													.getReturnQuantity()));
									qty1 = qty1.setScale(2, RoundingMode.CEILING);
									Order.setTotalPrice(""
											+ qty1.multiply(
													new BigDecimal(rec1.getI0019()
															.getI0018()
															.getUnitPrice()))
													.subtract(
															new BigDecimal(
																	rec1.getDiscountAmount())));
									Order.setQuantity(qty1.toString());
									Order.setUnit(unit);
									Order.setDiscountzAmount(rec1
											.getDiscountAmount());
									purchaseOrder.setCustomerName(result.get(0)
											.getCustomerName());
									purchaseOrder.setAddress(result.get(0)
											.getShipingAddress());
									purchaseOrder.setTelephonenumber(result.get(0)
											.getPhoneNumber());
									Order.setSellingPrice("" + rec1.getSell_price());
									Order.setOrderDate(result.get(0)
											.getSalesOrderDate());
									// Order.setSellingPrice(""+rec1.getI0019().getI0018().getUnitPrice());
									logger.info("total price--------->"
											+ Order.getTotalPrice());
									counter = 1;
									int s3 = 0;
									int s4 = 0;
									s3 = rec1.getI0019().getI0018().getBatch_ID();
									s4 = result.get(0).getSales_ID();
									logger.info("s3------------->" + s3);
									logger.info("s4------------->" + s4);
									Query q3 = null;
									q3 = entitymanager
											.createQuery("from I0019 where batch_ID=? and status='barcode genterated' ");
									logger.info("3");
									q3.setParameter(1, s3);
									logger.info("4");
									List<I0019> resu = (List<I0019>) q3
											.getResultList();
									if (resu.size() > 0) {
										logger.info("resu size---------------->"
												+ resu.size());
										Order.setTotalPrice(""
												+ qty1.multiply(
														new BigDecimal(rec1
																.getI0019()
																.getI0018()
																.getUnitPrice()))
														.subtract(
																new BigDecimal(
																		rec1.getDiscountAmount())));
										Order.setQuantity(qty1.toString());
									}
									resulfinal1.add(Order);
								}
							}
							logger.info("final size------------->"
									+ resulfinal1.size());
							purchaseOrder.setResulfinal(resulfinal1);
						}
						purchaseOrder.setResul1(resul);
						purchaseOrder.setResult(result);
						logger.info("6");
					} else {
						logger.info("inside empty list------------");

						purchaseOrder.setResulfinal(null);
					}
				}
			}
		}catch(Exception e){
			logger.error("inside exception ",e);
		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public String salesOrdercancelForm4(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		List<SalesRecord> resu = new ArrayList<SalesRecord>();
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("from I0021 where salesOrderNumber=? and (status='insert' or status='delivered' or status='cancelled') and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		logger.info("status" + purchaseOrder.getStatus());
		q.setParameter(2, clientID);
		logger.info("inside sales order cancel::::");
		List<I0021> result = (List<I0021>) q.getResultList();
		logger.info("size=kujuk===" + result.size() + "id"
				+ result.get(0).getSales_ID());

		int i = 0;
		if (result.size() > 0) {
			logger.info("1");
			int pkid = result.get(i).getSales_ID();
			logger.info("2 " + result.get(i).getSales_ID());
			Query q1 = null;

			q1 = entitymanager
					.createQuery("from SalesRecord where sales_ID=? and (soldStatus='solded' or soldStatus='delivered') ");
			logger.info("3");
			q1.setParameter(1, pkid);
			logger.info("4");
			List<SalesRecord> resul = (List<SalesRecord>) q1.getResultList();
			logger.info("5" + resul.size());
			resu.addAll(0, resul);
			purchaseOrder.setResul1(resu);
			purchaseOrder.setResult(result);
			logger.info("6");

		} else {
			logger.info("inside 1 else loop--------->>>>>");
			throw new DemoException("*already Cancelled Or delivered");

		}
		return null;

	}

	@Transactional(value = "transactionManager")
	public String salesOrdercancel(PurchaseOrder purchaseOrder)
			throws DemoException {

		Query q = null;
		try {

			logger.info("inside sales order cancel::::");
			q = entitymanager
					.createQuery("select salesOrderNumber from I0021 where status='insert' ");

			logger.info("inside sales order cancel1::::");
			List<I0021> result = (List<I0021>) q.getResultList();
			int pkid = 0;
			int i = 0;

			if (result.size() > 0) {

				purchaseOrder.setResult(result);
			} else {
				logger.info("inside 2 else loop--------->>>>>");
				purchaseOrder.setResult(null);
				throw new DemoException("*already Cancelled Or delivered");
			}

		} finally {

		}

		return null;

	}

	@Transactional(value = "transactionManager")
	public String salesOrdercancelFormsub1(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("from I0021 where salesOrderNumber=? and  status='Delivered' and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, clientID);
		logger.info("inside sales order cancel::::");
		List<I0021> result = (List<I0021>) q.getResultList();

		int pkid = 0;
		int i = 0;
		if (result.size() > 0) {
			logger.info("1");
			pkid = result.get(i).getSales_ID();
			logger.info("2");
			Query q1 = null;

			q1 = entitymanager.createQuery("from SalesRecord where sales_ID=?");
			logger.info("3");
			q1.setParameter(1, pkid);
			logger.info("4");
			List<SalesRecord> resul1 = (List<SalesRecord>) q1.getResultList();
			logger.info("5");
			// logger.info("---------"+resul.get(i).getI0021().getCustomerName());
			purchaseOrder.setResul1(resul1);
			purchaseOrder.setResult(result);
			logger.info("6");
			// resul.get(i).getI0018().getI0017().getI0015().get

		} else {
			purchaseOrder.setResul1(null);
			purchaseOrder.setResult(null);
			throw new DemoException("*Not delivered or cancelled");
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String salesOrdercancelFormsub(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("from I0021 where salesOrderNumber=? and status='insert' and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, clientID);
		logger.info("inside sales order cancel::::");
		List<I0021> result = (List<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		if (result.size() > 0) {
			logger.info("1");
			pkid = result.get(i).getSales_ID();
			logger.info("2");
			Query q1 = null;
			q1 = entitymanager.createQuery("from I0019 where sales_ID=?");
			logger.info("3");
			q1.setParameter(1, pkid);
			logger.info("4");
			List<I0019> resul = (List<I0019>) q1.getResultList();
			logger.info("5");
			// logger.info("---------"+resul.get(i).getI0021().getCustomerName());
			purchaseOrder.setResul(resul);
			logger.info("6");
			// resul.get(i).getI0018().getI0017().getI0015().get

		} else {
			logger.info("inside 3 else loop--------->>>>>");
			throw new DemoException("*already Cancelled Or delivered");
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String changeDrop(PurchaseOrder purchaseOrder) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;

		q = entitymanager
				.createQuery("select salesOrderNumber from I0021 where customerName=? and (status='insert' or status='Delivered' or status='cancelled') and client_ID=?");
		q.setParameter(1, purchaseOrder.getValueChange());
		q.setParameter(2, clientID);
		logger.info("inside salesDrop::::");
		ArrayList<I0021> result = (ArrayList<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		if (result.size() > 0) {
			purchaseOrder.setResult2(result);
		} else {
			purchaseOrder.setResult2(null);

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String changeDrop1(PurchaseOrder purchaseOrder) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		q = entitymanager
				.createQuery("select salesOrderNumber from I0021 where customerName=? and  status='Delivered' and client_ID=?");
		q.setParameter(1, purchaseOrder.getValueChange());
		q.setParameter(2, clientID);
		logger.info("inside salesDrop::::");
		ArrayList<I0021> result = (ArrayList<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		if (result.size() > 0) {
			purchaseOrder.setResult2(result);
		} else {
			purchaseOrder.setResult2(null);

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String salesDrop(PurchaseOrder purchaseOrder) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;

		q = entitymanager
				.createQuery("select customerName from I0021 where (status='insert' or status='Delivered' or status='cancelled') and client_ID=?");
		q.setParameter(1, clientID);
		logger.info("inside salesDrop::::");
		ArrayList<I0021> result = (ArrayList<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		logger.info("Size sales" + result.size());
		if (result.size() > 0) {
			purchaseOrder.setResult1(result);
		} else {
			purchaseOrder.setResult1(null);
			throw new DemoException("*Please check the sales order number");
		}

		return null;

	}

	@Transactional(value = "transactionManager")
	public String salesDrop1(PurchaseOrder purchaseOrder) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		List<String> resulfinal1 = new ArrayList<String>();
		q = entitymanager
				.createQuery(" from I0021 where  (status='insert' or  status='Delivered') and client_ID=?");
		q.setParameter(1, clientID);
		logger.info("inside salesDrop::::");
		ArrayList<I0021> result = (ArrayList<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		logger.info("Sales list size" + result.size());
		if (result.size() > 0) {
			logger.info("inside if::::");

			String temp = "";
			int ii = 0;
			String temp1 = "";
			String s = null;
			int j = 0;
			for (I0021 i00191 : result) {
				String Order = new String();

				logger.info("tempppppppp" + temp);
				int counter = 1;
				s = result.get(j).getCustomerName();
				logger.info("CustomerName------------->" + s);

				if (s.equals(temp)) {
					logger.info("inside if");
					counter++;

					logger.info("counter--------->" + counter);
				} else {
					temp = s;
					logger.info("inside else");
					counter = 1;
					String s3 = null;
					int s4 = 0;
					s3 = result.get(0).getCustomerName();

					resulfinal1.add(result.get(j).getCustomerName());
				}

				j++;
			}
			logger.info("final size------------->" + resulfinal1.size());
			purchaseOrder.setResulfinal1(resulfinal1);

			logger.info("final size------------->"
					+ purchaseOrder.getResulfinal1());

			// logger.info("---------"+resul.get(i).getI0021().getCustomerName());
		} else {
			purchaseOrder.setResult1(null);
			throw new DemoException("*Please check the sales order number");
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String salesOrdercancelForm1(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("from I0021 where salesOrderNumber=? and status=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, "insert");
		q.setParameter(3, clientID);
		logger.info("inside sales order cancel::::");
		List<I0021> result = (List<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		if (result.size() > 0) {
			logger.info("1");
			pkid = result.get(i).getSales_ID();
			logger.info("2");
			Query q1 = null;
			q1 = entitymanager.createQuery("from I0019 where sales_ID=?");
			logger.info("3");
			q1.setParameter(1, pkid);
			logger.info("4");
			List<I0019> resul = (List<I0019>) q1.getResultList();
			logger.info("5");
			// logger.info("---------"+resul.get(i).getI0021().getCustomerName());
			purchaseOrder.setResul(resul);
			logger.info("6");
			// resul.get(i).getI0018().getI0017().getI0015().get

		} else {
			throw new DemoException("*please check the sales order number");
		}
		return null;

	}

	@Transactional(value = "transactionManager")
	public String salesOrdercancelForm3(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("from I0021 where salesOrderNumber=? and (status='insert' or status='Delivered') and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, clientID);
		logger.info("inside sales order cancel::::");
		List<I0021> result = (List<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		if (result.size() > 0) {
			logger.info("1");
			pkid = result.get(i).getSales_ID();
			logger.info("2");
			Query q1 = null;
			q1 = entitymanager.createQuery("from I0019 where sales_ID=?");
			logger.info("3");
			q1.setParameter(1, pkid);
			logger.info("4");
			List<I0019> resul = (List<I0019>) q1.getResultList();
			logger.info("5");
			// logger.info("---------"+resul.get(i).getI0021().getCustomerName());
			purchaseOrder.setResul(resul);
			logger.info("6");
			// resul.get(i).getI0018().getI0017().getI0015().get

		} else {
			throw new DemoException("*Please check the sales order number");
		}
		return null;

	}

	@Transactional(value = "transactionManager")
	public String salesOrdercancelForm2(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		Query q4 = null;
		Query q5 = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("from I0021 where salesOrderNumber=? and (status='insert' or status='Quick sales') and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, clientID);
		// q.setParameter(2, "insert");
		logger.info("inside sales order cancel::::");
		List<I0021> result = (List<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		if (result.size() > 0) {
			logger.info("1");
			pkid = result.get(i).getSales_ID();
			logger.info("2---------->" + pkid);
			Query q1 = null;

			I0021 sale = entitymanager.find(I0021.class, pkid);
			sale.setStatus("cancelled");
			entitymanager.merge(sale);

			String saledQuant = "0";
			int barID = 0;

			logger.info("salesid --<< " + purchaseOrder.getSalesIdReference());
			q4 = entitymanager.createQuery("from SalesRecord where sales_id=?");
			q4.setParameter(1, pkid);
			List<SalesRecord> salerecz = (List<SalesRecord>) q4.getResultList();
			if (salerecz.size() > 0) {
				for (int j = 0; j < salerecz.size(); j++) {
					saledQuant = salerecz.get(j).getSoldQuantity();
					barID = salerecz.get(j).getI0019().getBar_code_ID();

					String stockQuant = "0";
					String newQuant = "0";
					q5 = entitymanager
							.createQuery("from I0019 where bar_code_ID=?");
					q5.setParameter(1, barID);
					List<I0019> batchProduct = (List<I0019>) q5.getResultList();
					if (batchProduct.size() > 0) {

						stockQuant = batchProduct.get(0).getStock_In();
						newQuant = ""
								+ new BigDecimal(stockQuant)
										.add(new BigDecimal(saledQuant));

						I0019 barcode = entitymanager.find(I0019.class, barID);
						barcode.setQuantity(newQuant);
						entitymanager.merge(barcode);
					}

				}
			}

			Query q2 = null;
			q2 = entitymanager.createQuery("from I0022 where sales_ID=?");
			q2.setParameter(1, pkid);
			List<I0022> result1 = (List<I0022>) q2.getResultList();
			int invoiceId = 0;
			if (result1.size() > 0) {
				logger.info("-------------------This order number hase generated Invoice-----------");
				invoiceId = result1.get(0).getInvoice_ID();
				logger.info("----------invoiceId------------" + invoiceId);
				Query q3 = null;
				q3 = entitymanager.createQuery("from I0023 where invoice_ID=?");
				q3.setParameter(1, invoiceId);
				List<I0023> result2 = (List<I0023>) q3.getResultList();
				int paymentid = 0;
				if (result2.size() > 0) {
					logger.info("-------------------This order number hase generated Invoice-----------");
					paymentid = result2.get(0).getPayment_ID();
					logger.info("----------payment id------------" + paymentid);
					I0023 i0023 = entitymanager.find(I0023.class, paymentid);
					entitymanager.remove(i0023);
				}
				I0022 i0022 = entitymanager.find(I0022.class, invoiceId);
				entitymanager.remove(i0022);
			}

		} else {
		}

		return null;

	}

	@Transactional(value = "transactionManager")
	public String salesOrdercancelForm5(PurchaseOrder purchaseOrder)
			throws DemoException {

		Query q = null;
		Query q4 = null;
		Query q5 = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager.createQuery("from I0021 where sales_ID=?");
		q.setParameter(1, purchaseOrder.getSalesId());
		// q.setParameter(2, "insert");
		logger.info("inside sales order cancel::::");
		List<I0021> result = (List<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		if (result.size() > 0) {
			logger.info("1");
			pkid = result.get(i).getSales_ID();
			logger.info("2---------->" + pkid);
			Query q1 = null;

			I0021 sale = entitymanager.find(I0021.class, pkid);
			sale.setStatus2("");
			sale.setStatus("Quick sale Cancelled");
			entitymanager.merge(sale);

			String saledQuant = "0";
			int barID = 0;

			logger.info("salesid --<< " + purchaseOrder.getSalesIdReference());
			q4 = entitymanager.createQuery("from SalesRecord where sales_id=?");
			q4.setParameter(1, pkid);
			List<SalesRecord> salerecz = (List<SalesRecord>) q4.getResultList();
			if (salerecz.size() > 0) {
				for (int j = 0; j < salerecz.size(); j++) {
					saledQuant = salerecz.get(j).getSoldQuantity();
					barID = salerecz.get(j).getI0019().getBar_code_ID();

					String stockQuant = "0";
					String newQuant = "0";
					q5 = entitymanager
							.createQuery("from I0019 where bar_code_ID=?");
					q5.setParameter(1, barID);
					List<I0019> batchProduct = (List<I0019>) q5.getResultList();
					String stockinqunt = "";
					float temp = (float) 0.0;
					if (batchProduct.size() > 0) {
						int k = 0;
						stockinqunt = batchProduct.get(k).getQuantity();

						temp = (Float.parseFloat(stockinqunt) + Float
								.parseFloat(saledQuant));

						I0019 barcode = entitymanager.find(I0019.class, barID);
						barcode.setQuantity(String.valueOf(temp));

						entitymanager.merge(barcode);

					}

				}
			}
			Query q2 = null;
			q2 = entitymanager.createQuery("from I0022 where sales_ID=?");
			q2.setParameter(1, pkid);
			List<I0022> result1 = (List<I0022>) q2.getResultList();
			int invoiceId = 0;
			if (result1.size() > 0) {
				logger.info("-------------------This order number hase generated Invoice-----------");
				invoiceId = result1.get(0).getInvoice_ID();
				logger.info("----------invoiceId------------" + invoiceId);
				Query q3 = null;
				q3 = entitymanager.createQuery("from I0023 where invoice_ID=?");
				q3.setParameter(1, invoiceId);
				List<I0023> result2 = (List<I0023>) q3.getResultList();
				int paymentid = 0;
				if (result2.size() > 0) {
					logger.info("-------------------This order number hase generated Invoice-----------");
					paymentid = result2.get(0).getPayment_ID();
					logger.info("----------payment id------------" + paymentid);
					I0023 i0023 = entitymanager.find(I0023.class, paymentid);
					entitymanager.remove(i0023);
				}
				I0022 i0022 = entitymanager.find(I0022.class, invoiceId);
				entitymanager.remove(i0022);
			}

		} else {
			throw new DemoException("*Please check the sales order number");
		}

		return null;

	}

	@Transactional(value = "transactionManager")
	public String salesReturnForm4(PurchaseOrder purchaseOrder)
			throws DemoException {

		Query q = null;
		logger.info("purorder number4------------------->"
				+ purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("from I0021 where salesOrderNumber=? and status=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, "Delivered");
		logger.info("::salesReturnForm4::");
		List<I0021> result = (List<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		if (result.size() > 0) {
			logger.info("4");
			pkid = result.get(i).getSales_ID();
			logger.info("24");
			Query q1 = null;

			q1 = entitymanager
					.createQuery("from I0019 where sales_ID=? and status='Delivered'");
			logger.info("34");
			q1.setParameter(1, pkid);
			logger.info("44");
			List<I0019> resul = (List<I0019>) q1.getResultList();
			int barid = 0;
			if (resul.size() > 0) {

			} else {

				I0021 sale = entitymanager.find(I0021.class, pkid);
				sale.setStatus("Return");
				entitymanager.merge(sale);

			}

		} else {
			throw new DemoException(
					"please check the Sales order Number:::::::::::::");
		}

		return null;

	}

	@Transactional(value = "transactionManager")
	public String salesdelivery(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("from I0021 where salesOrderNumber=? and status=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, "insert");
		q.setParameter(3, clientID);
		logger.info("inside sales order cancel::::");
		List<I0021> result = (List<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		if (result.size() > 0) {

			logger.info("1");
			pkid = result.get(i).getSales_ID();
			logger.info("2");
			Query q1 = null;
			I0021 sale = entitymanager.find(I0021.class, pkid);
			sale.setStatus("Delivered");
			entitymanager.merge(sale);

			q1 = entitymanager.createQuery("from SalesRecord where sales_ID=?");
			q1.setParameter(1, pkid);
			List<SalesRecord> recordList = (List<SalesRecord>) q1
					.getResultList();
			int recid = 0;
			if (recordList.size() > 0) {
				for (int j = 0; j < recordList.size(); j++) {
					recid = recordList.get(j).getSalesRecordId();
					SalesRecord rec = entitymanager.find(SalesRecord.class,
							recid);
					rec.setSoldStatus("delivered");
					entitymanager.merge(rec);
				}
			}
			q = entitymanager.createQuery("from Dispatch where client_ID=?");
			q.setParameter(1, clientID);
			List<Dispatch> disList = q.getResultList();

			String dispathNo = "";

			if (disList.size() <= 0) {
				dispathNo = GenerateEmployee.getdispathchno(1);
			} else {
				dispathNo = GenerateEmployee.getdispathchno(disList.size() + 1);
			}

			Dispatch dis = new Dispatch();
			dis.setDispatchno(dispathNo);
			dis.setSalesOrderNumber(result.get(i).getSalesOrderNumber());
			dis.setI0021(entitymanager.find(I0021.class, pkid));
			dis.setClient_ID(clientID);
			entitymanager.persist(dis);
			RequestContext.getCurrentInstance().execute(
					"PF('sdelivery1').show();");

		} else {
			RequestContext.getCurrentInstance().execute(
					"PF('sdelivery').show();");
			throw new DemoException(
					"please check the Sales order Number:::::::::::::");

		}
		return null;

	}

	@Transactional(value = "transactionManager")
	public List<I0021> salesPayment1(List<I0021> salesreferenumber)
			throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.info("inside sales payment dao::::::::");
		q = entitymanager.createQuery("select salesOrderNumber from I0021  and client_ID=?");
		logger.info("2");

		logger.info("3");
		q.setParameter(1, clientID);
		salesreferenumber = q.getResultList();

		logger.info("inside sales payment out dao::::::::");
		return salesreferenumber;
	}

	@Transactional(value = "transactionManager")
	public String salesPayment2(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q = null;
			logger.info("purorder number" + purchaseOrder.getSalesIdReference());
			q = entitymanager
					.createQuery("from I0021 where salesOrderNumber=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getSalesIdReference());
			q.setParameter(2, clientID);
			logger.info("inside sales order cancel::::");
			List<I0021> result = (List<I0021>) q.getResultList();
			purchaseOrder.setResult(result);
			if (result.size() > 0) {
				try {
					String f = result.get(0).getI0022s().get(0).getI0023s()
							.get(0).getBalanceAmount();
					logger.info("ffff" + f);
					if (Integer.parseInt(f) > 0) {
						purchaseOrder.setTotalPrice("" + new BigDecimal(f));
					} else {
						purchaseOrder.setTotalPrice("0");
					}
				} catch (Exception e) {

					String f = result.get(0).getCrossTotal();
					logger.info("ffff" + f);
					purchaseOrder.setTotalPrice("" + f);
					return null;
				}

				try {
					String f = result.get(0).getI0022s().get(0).getI0023s()
							.get(0).getPayableAmount();
					logger.info("ffff  11  - " + f);
					purchaseOrder.setCrosstotal("" + new BigDecimal(f));
					logger.info("Inside try" + purchaseOrder.getCrosstotal());
				} catch (Exception e) {

					String f = result.get(0).getCrossTotal();
					logger.info("ffff" + f);
					purchaseOrder.setCrosstotal("" + f);
					return null;
				}
			}

		} catch (Exception e) {
		}
		return null;

	}

	@Transactional(value = "transactionManager")
	public String salesView(PurchaseOrder purchaseOrder) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		logger.info("purchase order:::::" + purchaseOrder.getOrderDate());
		Query q = null;
		if(userType.equals("Maker")){
			q = entitymanager
					.createQuery("from I0021 where (status='insert' or status='delivered' or status='cancelled') and client_ID=? and user_ID=? ORDER BY createdDate DESC");
			q.setParameter(1, clientID);
			q.setParameter(2, userID);
		}else{
			if (purchaseOrder.getApproval()=="ApprovalData") {
				q = entitymanager.createQuery("from I0021 where (status='insert' or status='delivered' or status='cancelled') and client_ID=? and approvalStatus='draft'  ORDER BY createdDate DESC");
				q.setParameter(1, clientID);
			}
			else{
			q = entitymanager.createQuery("from I0021 where (status='insert' or status='delivered' or status='cancelled') and client_ID=?  ORDER BY createdDate DESC");
			q.setParameter(1, clientID);
			}
		}
		List<I0021> result = (List<I0021>) q.getResultList();
		purchaseOrder.setResult(result);
		if (result.size() == 0) {
			logger.info("inside Null");
		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public String invoiceSales(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager.createQuery("from I0021 where salesOrderNumber=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, clientID);
		logger.info("inside sales order cancel::::");
		List<I0021> result = (List<I0021>) q.getResultList();
		purchaseOrder.setResult(result);
		return null;

	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0021> invoiceSales(PurchaseOrder purchaseOrder,
			ArrayList<I0021> sales) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("select salesOrderNumber from I0021 where (status='insert' or status='Delivered') and client_ID=?");
		q.setParameter(1, clientID);
		logger.info("inside sales order cancel::::");
		sales = (ArrayList<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		if (sales.size() > 0) {
			for (int j = 0; j < sales.size(); j++) {

				q = null;
				logger.info("purorder number"
						+ purchaseOrder.getSalesIdReference());
				q = entitymanager
						.createQuery("from I0021 where salesOrderNumber=? and (status='insert' or status='Delivered') and client_ID=?");
				q.setParameter(1, sales.get(j));
				q.setParameter(2, clientID);
				ArrayList<I0021> sales1 = (ArrayList<I0021>) q.getResultList();
				q = null;
				q = entitymanager.createQuery("from I0022 where sales_ID=?");
				q.setParameter(1, sales1.get(0).getSales_ID());
				ArrayList<I0022> invoieList = (ArrayList<I0022>) q
						.getResultList();
				if (invoieList.size() > 0) {
					sales.remove(j);
					j--;
				}
			}
		} else {
			throw new DemoException("*Please check the sales order number");
		}
		return sales;
	}

	@Transactional(value = "transactionManager")
	public String accountin(PurchaseOrder purchaseOrder) throws DemoException {
		logger.info("--------------$$$$$$$$$$$$$$------------inside account Dao-------------$$$$$$$$$$$$$$-----------");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q1 = null;
		Query q2 = null;
		Query q = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager.createQuery("from I0021 where salesOrderNumber=? and client_ID=? ");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, clientID);
		// q.setParameter(2, "insert");
		logger.info("accountin::::");
		List<I0021> result = (List<I0021>) q.getResultList();
		purchaseOrder.setResult(result);
		int id = 0;
		int i = 0;
		Date date = null;
		String payable = "";
		String balance = "";
		if (result.size() > 0) {
			id = result.get(i).getSales_ID();
			date = result.get(i).getSalesOrderDate();
			payable = result.get(i).getCrossTotal();
			Query qq = null;
			qq = entitymanager.createQuery("from SalesReturn where sales_ID=?");
			qq.setParameter(1, id);
			logger.info("purchase id -- >> " + id);
			List<SalesReturn> prlist = (List<SalesReturn>) qq.getResultList();
			if (prlist.size() > 0) {
				logger.info("normal return :::" + prlist.get(0).getNr());
				logger.info("damage return :::" + prlist.get(0).getDr());

				BigDecimal rq = BigDecimal.valueOf(0);
				BigDecimal aprice = BigDecimal.valueOf(0);
				logger.info("purchase return size - " + prlist.size());
				for (int j = 0; j < prlist.size(); j++) {
					Query ap = null;
					ap = entitymanager
							.createQuery("from I0001 where productName=?");
					ap.setParameter(1, prlist.get(j).getProductName());
					List<I0001> prdlist = (List<I0001>) ap.getResultList();

					rq = new BigDecimal(prlist.get(j).getNr())
							.add(new BigDecimal(prlist.get(j).getDr()));
					logger.info("quan - >" + rq + " actual price -- > "
							+ prdlist.get(0).getMarketPrice());
					logger.info("aprice" + aprice);
					aprice = aprice.add(rq.multiply(new BigDecimal(prdlist.get(
							0).getMarketPrice())));
					logger.info("amnt -- > " + aprice);
				}
				logger.info("purchase return price -2 " + aprice);
				purchaseOrder.setTotalPrice(""
						+ (new BigDecimal(result.get(0).getCrossTotal())
								.subtract(aprice)));
			} else {
				logger.info("purchase return else -- >> " + id);
				purchaseOrder.setTotalPrice(""
						+ new BigDecimal(result.get(0).getCrossTotal()));
			}
			q1 = entitymanager
					.createQuery("from I0022 where sales_ID=? and status=?");
			q1.setParameter(1, id);
			q1.setParameter(2, "Sales Invoice");
			List<I0022> resul = (List<I0022>) q1.getResultList();
			int invoiceid = 0;
			if (resul.size() > 0) {
				int j = 0;
				invoiceid = resul.get(j).getInvoice_ID();
				q2 = entitymanager
						.createQuery("from I0023 where invoice_ID=? ");
				q2.setParameter(1, invoiceid);
				List<I0023> resu = (List<I0023>) q2.getResultList();
				if (resu.size() > 0) {
					logger.info("already inserted.......");
				} else {
					I0023 payment = new I0023();
					payment.setPaidAmount("" + BigDecimal.valueOf(0));
					payment.setI0022(entitymanager.find(I0022.class, invoiceid));
					payment.setPayableAmount(purchaseOrder.getTotalPrice());
					payment.setBalanceAmount(purchaseOrder.getTotalPrice());
					payment.setStartDate(date);
					payment.setStatus("pending");
					payment.setClient_ID(clientID);
					payment.setSales_payment_ID(2);
					entitymanager.persist(payment);
					logger.info("sucess insert:::::::");
				}
				logger.info("sucess in if:::::::");
			} else {
				RequestContext.getCurrentInstance().execute(
						"PF('salespay1').show();");
				throw new DemoException("*Should Generate Invoice First");
			}
		} else {
			throw new DemoException("*invalid sale number");
		}
		logger.info("id::::::::" + id);
		logger.info("excuted::::::");
		logger.info("--------------$$$$$$$$$$$$$$------------outside account Dao-------------$$$$$$$$$$$$$$-----------");
		return null;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0021> accountin(ArrayList<I0021> drop)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			q = entitymanager
					.createQuery("select salesOrderNumber from I0021 where (status2='pending' or status2='unpaid')  and (status='insert' or status='Delivered') and client_ID=?");
			q.setParameter(1, clientID);
			drop = (ArrayList<I0021>) q.getResultList();

		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {

		}
		return drop;
	}

	@Transactional(value = "transactionManager")
	public String accountin1(PurchaseOrder purchaseOrder) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q1 = null;
		Query q2 = null;

		Query q = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager.createQuery("from I0021 where salesOrderNumber=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		// q.setParameter(2, "insert");
		q.setParameter(2, clientID);
		logger.info("accountin::::");
		List<I0021> result = (List<I0021>) q.getResultList();
		purchaseOrder.setResult(result);
		int id = 0;
		int i = 0;
		Date date = null;
		String payable = "0";
		String balance = "0";

		if (result.size() > 0) {
			id = result.get(i).getSales_ID();
			date = result.get(i).getSalesOrderDate();
			payable = result.get(i).getCrossTotal();
			// fId=result.get(i).getI0015().getPurchase_ID();
			q1 = entitymanager
					.createQuery("from I0022 where sales_ID=? and status=?");
			q1.setParameter(1, id);
			q1.setParameter(2, "Sales Invoice");
			List<I0022> resul = (List<I0022>) q1.getResultList();
			int invoiceid = 0;
			if (resul.size() > 0) {
				int j = 0;
				invoiceid = resul.get(j).getInvoice_ID();
				q2 = entitymanager
						.createQuery("from I0023 where invoice_ID=? ");
				q2.setParameter(1, invoiceid);
				List<I0023> resu = (List<I0023>) q2.getResultList();
				purchaseOrder.setResu(resu);

			}
		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public String payNowAccount(PurchaseOrder purchaseOrder)
			throws DemoException {
		String status="Fail";
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q1 = null;
		Query q2 = null;
		Query q = null;
		logger.debug("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager.createQuery("from I0021 where salesOrderNumber=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, clientID);
		logger.debug("accountin::::");
		List<I0021> result = (List<I0021>) q.getResultList();
		purchaseOrder.setResult(result);
		int id = 0;
		int i = 0;
		Date date = null;
		String payable = "0";
		String balance = "0";
		if (result.size() > 0) {
			id = result.get(i).getSales_ID();
			date = result.get(i).getSalesOrderDate();
			payable = result.get(i).getCrossTotal();
			// fId=result.get(i).getI0015().getPurchase_ID();
			q1 = entitymanager
					.createQuery("from I0022 where sales_ID=? and status=?");
			q1.setParameter(1, id);
			q1.setParameter(2, "Sales Invoice");
			List<I0022> resul = (List<I0022>) q1.getResultList();
			int invoiceid = 0;
			if (resul.size() > 0) {
				int j = 0;
				invoiceid = resul.get(j).getInvoice_ID();
				q2 = entitymanager
						.createQuery("from I0023 where invoice_ID=? ");
				q2.setParameter(1, invoiceid);
				List<I0023> resu = (List<I0023>) q2.getResultList();
				purchaseOrder.setResu(resu);

				if (resu.size() > 0) {
					int l = 0;
					l = resu.get(l).getPayment_ID();
					int k = 0;
					String f = "0";
					logger.debug("11111111111");
					logger.debug("baying amount::::" + purchaseOrder.remaining);
					f = purchaseOrder.getRemaining();
					logger.debug("ffffffffff" + f);
					// float balance=0;
					String paid = resu.get(k).getPaidAmount();
					balance = resu.get(k).getBalanceAmount();
					logger.debug("balance:::" + balance);
				
					if("Cash".equalsIgnoreCase(purchaseOrder.getPaymentMode())){
						I0023 payment = entitymanager.find(I0023.class, l);
						payment.setPaidAmount(purchaseOrder.getPayableAmount());
						payment.setAccountType(purchaseOrder.getAccounttype());
						payment.setAccountDescription(purchaseOrder.getAccountdescription());
						payment.setBalanceAmount("0");
						payment.setStatus("paid");
						entitymanager.merge(payment);
						I0021 sales = entitymanager.find(I0021.class, id);
						sales.setStatus2("paid");
						entitymanager.merge(sales);
						status="Success";
					}else if("Credit".equalsIgnoreCase(purchaseOrder.getPaymentMode())){
						I0023 payment = entitymanager.find(I0023.class, l);
						payment.setPaidAmount(String.valueOf(new BigDecimal(purchaseOrder.getRemaining()).add(new BigDecimal(resu.get(0).getPaidAmount()))));
						if(paid=="0"){
							payment.setBalanceAmount(new BigDecimal(purchaseOrder.getPayableAmount()).subtract(new BigDecimal(purchaseOrder.getRemaining())).toString());
						}else{
							payment.setBalanceAmount(new BigDecimal(balance).subtract(new BigDecimal(purchaseOrder.getRemaining())).toString());
						}
						if(payment.getBalanceAmount().equals("0")){
							payment.setStatus("Paid");
						}
						payment.setPaymentType(purchaseOrder.getPaymentType());
						payment.setAccountType(purchaseOrder.getAccounttype());
						payment.setAccountDescription(purchaseOrder.getAccountdescription());
						payment.setBankName(purchaseOrder.getBankname());
						payment.setCardNo(purchaseOrder.getCardno());
						payment.setChequeDate(purchaseOrder.getChequedate());
						payment.setChequeNo(purchaseOrder.getChequeno());
						payment.setAccountNo(purchaseOrder.getAccno());
						entitymanager.merge(payment);
						I0021 sales = entitymanager.find(I0021.class, id);
						if(payment.getBalanceAmount().equals("0")){
						sales.setStatus2("paid");
						}
						entitymanager.merge(sales);
						status="Success";
					}
				}
			}
		}
		return status;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0001> dropdownproduct(String productname,
			ArrayList<I0001> productDrop) throws DemoException {
		try {
			logger.info("inside dao dropdown::::::::::::");
			Query q = null;
			q = entitymanager
					.createQuery("select productName from I0001 where status=? ");
			logger.info("1");
			q.setParameter(1, "i");
			logger.info("1");
			productDrop = (ArrayList<I0001>) q.getResultList();
			logger.info("1");

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return productDrop;
	}

	@Transactional(value = "transactionManager")
	public List<I0001> dropdownproduct1() throws DemoException {
		List<I0001> productDrop = null;
		try {
			logger.info("inside dao dropdown::::::::::::");
			Query q = null;
			q = entitymanager.createQuery("from I0001 where status=? ");
			logger.info("1");
			q.setParameter(1, "i");
			logger.info("1");
			productDrop = q.getResultList();
			logger.info("1");

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return productDrop;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0022> reportInvoice(ArrayList<I0022> invoiceList,
			Report1 report1) throws DemoException {
		logger.info("--------------$$$$$$$$$$$$$$------------inside reportInvoice Dao-------------$$$$$$$$$$$$$$-----------");
		String temp = "0";
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<String> strin = new ArrayList<String>();
		Query q = null;
		try {
			if (report1.getCategory().equals("Sales Invoice")) {
				logger.info("inside sales::::::::::::");
				logger.info("to date::::::::::::::" + report1.getToDate());
				logger.info("from date::::::::::" + report1.getFromDate());
				q = entitymanager
						.createQuery("from I0022 where invoice_date between ? and ? and status=? and 	client_ID=?");
				q.setParameter(1, report1.getFromDate());
				q.setParameter(2, report1.getToDate());
				q.setParameter(3, "Sales Invoice");
				q.setParameter(4, clientID);
				invoiceList = (ArrayList<I0022>) q.getResultList();

				if (invoiceList.size() > 0) {
					for (I0022 r : invoiceList) {
						temp = ""
								+ new BigDecimal(temp).add(new BigDecimal(r
										.getI0021().getCrossTotal()));
					}
				}
				logger.info("temp:::::" + temp);
				report1.setInvoiceList(invoiceList);
			} else {
				logger.info("inside purachase::::::::::::");
				logger.info("to date::::::::::::::" + report1.getToDate());
				logger.info("from date::::::::::" + report1.getFromDate());
				q = entitymanager
						.createQuery("from I0022 where invoice_date between ? and ? and status=? and 	client_ID=?");
				q.setParameter(1, report1.getFromDate());
				q.setParameter(2, report1.getToDate());
				q.setParameter(3, "purchase Invoice");
				q.setParameter(4, clientID);
				invoiceList = (ArrayList<I0022>) q.getResultList();
				// float temp=0;
				if (invoiceList.size() > 0) {
					for (I0022 r : invoiceList) {
						temp = ""
								+ (new BigDecimal(temp).add(new BigDecimal(r
										.getI0015().getTotalPrice())));
						String s = r.getI0015().getI0016s().get(0).getI0031()
								.getI0025().getVendorPhoneNumber();
						logger.info("s--------------->firm Name-------->" + s);
						strin.add(s);
					}
					logger.info("temp:::::" + temp);
				}
				report1.setStrin(strin);
				logger.info("size========-->" + strin);
				report1.setInvoiceList(invoiceList);
			}
			report1.setTotal(temp);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		logger.info("--------------$$$$$$$$$$$$$$------------Outside reportInvoice Dao------------$$$$$$$$$$$$$$-----------");
		return invoiceList;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0015> reportPurchase(ArrayList<I0015> purchaseList,
			Report1 report1) throws DemoException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		logger.info("--------------$$$$$$$$$$$$$$------------Inside reportPurchase-------------$$$$$$$$$$$$$$-----------");
		String temp = "0";
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null, q2 = null;
		ArrayList<I0015> xx = new ArrayList<I0015>();
		ArrayList<String> xx1 = new ArrayList<String>();
		ArrayList<Report1> report = new ArrayList<Report1>();
		ArrayList<I0016> purchaseList1 = null;
		try {

			logger.info("inside purachse report::::::::::::");
			logger.info("to date::::::::::::::" + report1.getToDate());
			logger.info("from date::::::::::" + report1.getFromDate());
			q = entitymanager
					.createQuery("from I0015 where orderDate between ? and ? and (status='insert' or status='delivered') and client_ID=?");
			q.setParameter(1, report1.getFromDate());
			q.setParameter(2, report1.getToDate());
			q.setParameter(3, clientID);
			ArrayList<I0015> result = (ArrayList<I0015>) q.getResultList();
			int count = 0;
			int id = 0;
			if (result.size() > 0) {
				for (I0015 r : result) {
					purchaseList1 = null;
					id = r.getPurchase_ID();
					logger.info("id::::::::::::" + id);
					q2 = entitymanager
							.createQuery("from I0016 where purchase_ID=? and status=? and status2=?");
					q2.setParameter(1, id);
					q2.setParameter(2, "inserted");
					q2.setParameter(3, "paid");
					purchaseList1 = (ArrayList<I0016>) q2.getResultList();
					if (purchaseList1.size() > 0) {
						logger.info("1");
						temp = ""
								+ (new BigDecimal(temp).add(new BigDecimal(r
										.getTotalPrice())));
						I0015 i0015 = new I0015();
						logger.info("2");
						Report1 reobj = new Report1();
						reobj.setFirmName(result.get(count).getI0016s().get(0)
								.getI0031().getI0025().getVendorPhoneNumber());
						reobj.setDate(""
								+ df.format(result.get(count).getOrderDate()));
						logger.info("Order number-------------->"
								+ result.get(count).getTemOrderNumber());
						reobj.setOrderNumber(result.get(count)
								.getTemOrderNumber());
						reobj.setTotalAmount(result.get(count).getTotalPrice());
						report.add(reobj);
						logger.info("firm name----------->"
								+ purchaseList1.get(0).getI0031().getI0025()
										.getFirmName());
						xx.addAll(count, result);

						logger.info("3");
					}
					count++;
					logger.info("counttttttttttt" + count);
				}
			}
			logger.info("temp:::::" + temp);

			logger.info("resul$$$$$$$$$$$$$$ size" + report.size());
			report1.setReport(report);
			report1.setTotal(temp);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		logger.info("--------------$$$$$$$$$$$$$$------------Outside reportPurchase-------------$$$$$$$$$$$$$$-----------");
		return xx;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0021> reportSales(ArrayList<I0021> salesList1,
			Report1 report1) throws DemoException {
		logger.info("--------------$$$$$$$$$$$$$$------------Inside reportSales Dao-------------$$$$$$$$$$$$$$-----------");
		String temp = "0";
		Query q = null, q2 = null;
		Query c = null;
		ArrayList<I0032> customerlist = null;

		ArrayList<Report1> report2 = new ArrayList<Report1>();
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			logger.info("inside sales report::::::::::::");
			logger.info("to date::::::::::::::" + report1.getToDate());
			logger.info("from date::::::::::" + report1.getFromDate());
			logger.info("Cus Category========" + report1.getCuscategory());
			c = entitymanager.createQuery(" from I0032 where categoryName=?");
			c.setParameter(1, report1.getCuscategory());
			customerlist = (ArrayList<I0032>) c.getResultList();
			logger.info("customer list size====" + customerlist.size()
					+ "======id===" + customerlist);
			if (customerlist.size() > 0) {
				for (int i = 0; i < customerlist.size(); i++) {
					logger.info("====buyer id==="
							+ customerlist.get(i).getBuyer_ID());
					q = entitymanager
							.createQuery("from I0021 where salesOrderDate between ? and ? and status2=? and buyer_ID=? and client_ID=?");
					q.setParameter(1, report1.getFromDate());
					q.setParameter(2, report1.getToDate());
					q.setParameter(3, "paid");
					q.setParameter(4, customerlist.get(i).getBuyer_ID());
					q.setParameter(5, clientID);
					salesList1 = (ArrayList<I0021>) q.getResultList();
					if (salesList1.size() > 0) {
						int j = 0;
						logger.info("Size>>>>>>>" + salesList1.size());
						for (I0021 r : salesList1) {
							Report1 rep = new Report1();
							logger.info("inside ");
							rep.setOrderdate(salesList1.get(j)
									.getSalesOrderDate());
							rep.setOrderNumber(salesList1.get(j)
									.getSalesOrderNumber());
							rep.setCustomername(salesList1.get(j)
									.getCustomerName());
							rep.setTotalAmount(""
									+ salesList1.get(j).getCrossTotal());
							temp = ""
									+ (new BigDecimal(temp).add(new BigDecimal(
											r.getCrossTotal())));
							rep.setTotal(temp);
							report2.add(rep);
							j++;
						}
					}
				}
				logger.info("temp:::::" + temp);
				report1.setReport2(report2);
				report1.setTotal(temp);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		logger.info("--------------$$$$$$$$$$$$$$------------Outside reportSales Dao-------------$$$$$$$$$$$$$$-----------");
		return salesList1;
	}

	@Transactional(value = "transactionManager")
	public String salesDelete1(int id) throws DemoException {
		try {
			logger.info("---------id1------" + id);
			Query q = null;
			q = entitymanager.createQuery("from I0022 where sales_ID=?");
			q.setParameter(1, id);
			ArrayList<I0022> sale = (ArrayList<I0022>) q.getResultList();
			int invoiceId = 0;
			if (sale.size() > 0) {
				logger.info("inside if invoice present");
				invoiceId = sale.get(0).getInvoice_ID();
				I0022 invoobj = entitymanager.find(I0022.class, invoiceId);
				entitymanager.remove(invoobj);
			}
			I0021 sal = entitymanager.find(I0021.class, id);
			entitymanager.remove(sal);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0021> salesDelete() throws DemoException {
		Query q = null;
		try {
			logger.info("==in dao===");
			q = entitymanager.createQuery("from I0021 where cross_total='0.0'");
			ArrayList<I0021> sale1 = (ArrayList<I0021>) q.getResultList();
			logger.info("result list size--------------------->" + sale1.size());
			if (sale1.size() > 0) {
			}

			return sale1;
		} catch (Exception e) {

			return null;
		}

	}

	@Transactional(value = "transactionManager")
	public List<I0018> dropDownbatch(List<I0018> batch) throws DemoException {
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			logger.info("inside dao1");
			Query q = null;
			q = entitymanager
					.createQuery("select batchName from  I0018  where  status=? and client_ID=?");
			logger.info("1");
			q.setParameter(1, "insert");
			q.setParameter(2, clientID);
			batch = (List<I0018>) q.getResultList();
			Query q1 = null;
		} catch (Exception i) {
			i.printStackTrace();
		}

		return batch;
	}

	@Transactional(value = "transactionManager")
	public String saveBuyer(Buyer b) throws DemoException {
		Query q = null;
		/*String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");*/
		String status = "Fail";
		logger.debug("customer name" + b.getPhoneNumber());
		List<I0032> resultList = null;
		try {
			q=entitymanager.createQuery("from Paymentcash where paymentType=? ");
			q.setParameter(1, b.getCash()); 
			List<Paymentcash> result1=(List<Paymentcash>) q.getResultList();
			int type=0;
			if(result1.size() > 0){
				int i = 0;
				type=result1.get(i).getPaymentCashId();
			}
			
			q = entitymanager.createQuery("from I0032 where phoneNumber=? and client_ID=? and user_ID=? and status='in'");
			q.setParameter(1, b.getPhoneNumber());
			q.setParameter(2, b.getClientID());
			q.setParameter(3, b.getUserID());
			resultList = q.getResultList();
			logger.debug("resultList size" + resultList.size());
			if (resultList.size() > 0) {
				status = "Fail";
			} else {
				I0032 buyer = new I0032();
				buyer.setCustomerTitle(b.getCustomerTitle());
				buyer.setCustomerName(b.getCustomerName());
				buyer.setCustomerMiddleName(b.getCustomerMiddleName());
				buyer.setCustomerLastName(b.getCustomerLastName());
				buyer.setCustomerSuffix(b.getCustomerSuffix());
				buyer.setEMail(b.getMail());
				buyer.setPhoneCode(b.getCode());
				buyer.setPhoneNumber(b.getPhoneNumber());
				buyer.setCustomerMobile(b.getCustMobile());
				buyer.setCategoryName(b.getCategoryName());
				buyer.setOther(b.getOther());
				logger.debug("Date" + b.getDate());
				buyer.setSalesOrderDate(b.getDate());
				buyer.setCompany(b.getCompany());
				buyer.setWebsite(b.getWebsite());
				buyer.setTaxNumber((b.getTaxnumber()));
				buyer.setCustomerFaxNumber(b.getFaxnumber());
				buyer.setDisplayNameAs(b.getDisplayName());
				buyer.setAddress(b.getPermanentaddress());
				buyer.setShipingAddress(b.getAddress());
				buyer.setCity(b.getCity());
				buyer.setPresentCity(b.getPresentCity());
				buyer.setState(b.getState());
				buyer.setCountry(b.getCountry());
				buyer.setPresentCountry(b.getPresentcountryID());
				buyer.setPresentPostCode(b.getPrePostCode());
				buyer.setPermanentPostCode(b.getPerPostCode());
				buyer.setNote(b.getNote());
				buyer.setState(b.getState());
				buyer.setPresentState(b.getPresentstate());
				buyer.setStatus("in");
				buyer.setFreelancerName(b.getFreelancerName());
				buyer.setClient_ID(b.getClientID());
				buyer.setApprovalStatus("draft");
				buyer.setUserID(entitymanager.find(UserCreate.class, Integer.parseInt(b.getUserID())));
				
				buyer.setCustomerLicenseNumber(b.getCusLicence()); 
				buyer.setCustomerExpireDate(b.getCusExdate()); 
				buyer.setCustomerCode(b.getCusCode()); 
				buyer.setCustomerType(b.getCusType()); 
				buyer.setPaymentType(b.getType()); 
				buyer.setPaymentCash_ID(entitymanager.find(Paymentcash.class, type)); 
				buyer.setCreatedDate(date);
				buyer.setDeliveryDate(b.getDeliveryDate());
				buyer.setPhotoUpload(b.getPhotouploadfile());
				buyer.setAttachmentFile(b.getAttachFilePath());
				buyer.setFreelancerName(b.getFilePath());
				buyer.setPnName(b.getPartnerShipName());
				buyer.setPnFatherRelation(b.getPartnerFnamerelation());
				buyer.setPnDOB(b.getPnDOB());
				buyer.setPnEmailID(b.getPnEmailID());
				buyer.setPnPhoneNumber1(b.getPnPhoneNumber1());
				buyer.setPnPhoneNumber2(b.getPnPhoneNumber2());
				buyer.setPnAddress(b.getPnAddress());
				buyer.setDateOfJoin(b.getDateofjoin());
				entitymanager.persist(buyer);
				status = "success";
				logger.debug("status" + status);
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			q = null;
		}
		return status;
	}

	@Transactional(value = "transactionManager")
	public int getPurchasestatus() throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0016> pur = null;
		int count = 0;
		try {
			q = entitymanager
					.createQuery("select distinct ordernumber from I0016 where status2=? and status='inserted' and 	client_ID=?");
			q.setParameter(1, "pending");
			q.setParameter(2, clientID);
			pur = q.getResultList();
			if (pur.size() > 0) {
				logger.info("-------------------------------------------------------------><"
						+ pur.size());
			}
			for (int i = 0; i < pur.size(); i++) {
				count++;
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return count;
	}

	@Transactional(value = "transactionManager")
	public int getsalesstatus() throws DemoException {
		Query q = null;
		List<I0021> pur = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		int count = 0;
		try {
			q = entitymanager
					.createQuery("from I0021 where (status=? or status=?) and status2=? and client_ID=?");
			q.setParameter(1, "insert");
			q.setParameter(2, "Delivered");
			q.setParameter(3, "pending");
			q.setParameter(4, clientID);
			pur = q.getResultList();

			for (int i = 0; i < pur.size(); i++) {
				count++;
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {

		}
		return count;
	}

	public int getSalesOrderStatus() throws DemoException {
		Query q = null;
		List<I0021> pur = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		int count = 0;
		try {
			q = entitymanager
					.createQuery("from I0021 where status=? and status2=? and client_ID=?");
			q.setParameter(1, "insert");
			q.setParameter(2, "pending");
			q.setParameter(3, clientID);
			pur = q.getResultList();
			if (pur.size() > 0) {
				logger.info("Size -->" + pur.size());
			}
			for (int i = 0; i < pur.size(); i++) {
				count++;
			}
			logger.info("Count -->" + count);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return count;
	}

	/** remainder for puchase order delivery **/
	public int getPurchaseorderStatus() throws DemoException {
		Query q = null;
		List<I0015> pur = null;
		List<I0016> pur1 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		int count = 0;
		try {
			q = entitymanager
					.createQuery("from I0016 where status3='Waiting' and approvalStatus=? and client_ID=?");
			q.setParameter(1, "approved");
			q.setParameter(2, clientID);
			pur1 = q.getResultList();
			for (int i = 0; i < pur1.size(); i++) {
				count++;
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {

		}
		return count;
	}

	@Transactional(value = "transactionManager")
	public List<I0028> getCountry() throws DemoException {
		Query q = null;
		List<I0028> country = null;
		try {
			q = entitymanager.createQuery("select name from I0028");
			country = q.getResultList();
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {

		}
		return country;
	}

	@Transactional(value = "transactionManager")
	public List<I0032> getBuyerInfo(String phoneNumber) throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0032> buyerInfo = null;
		try {
			logger.info("%%%%%%%%%%%%% inside buyer info dao%%%%%%%%%%%");
			q = entitymanager.createQuery("select o from I0032 o where o.phoneNumber like :x and o.status='in' and o.client_ID=? ");
			q.setParameter("x", phoneNumber + "%");
			q.setParameter(1, clientID);
			buyerInfo = q.getResultList();
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			q = null;
		}
		return buyerInfo;
	}

	@Transactional(value = "transactionManager")
	public List<I0032> getBuyerInfo1(String phoneNumber,String clientID,String userID) throws DemoException {
		Query q = null;
		//String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0032> buyerInfo = null;
		try {
			logger.debug("%%%%%%%%%%%%% inside buyer info dao%%%%%%%%%%%");
			q = entitymanager
					.createQuery("from I0032  where buyer_ID=? and status=?");
			q.setParameter(1, Integer.parseInt(phoneNumber));
			q.setParameter(2, "in");
			buyerInfo = q.getResultList();
		} catch (Exception e) {
						logger.error("Error --------------->"+e.getMessage());
			logger.debug(e.getMessage());
		} finally {
			q = null;
		}
		return buyerInfo;
	}
	
	@Transactional(value = "transactionManager")
	public List<I0032> getBuyerUpdate(Buyer b) throws DemoException {
		Query q = null;
		List<I0032> buyer = null;
		List<I0021> sales = null;
		//String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.debug("inside getBuyerUpdate:");
			q = entitymanager
					.createQuery("from I0032 where buyer_ID=? and status=?");
			logger.debug("1");
			logger.info("phonenumber"+b.getPhoneNumber());
			logger.info(">>>>>>>>>>>>>>>"+b.getClientID());
			q.setParameter(1, b.getBuyerID());
			q.setParameter(2, "in");
			logger.debug("2");
			buyer = q.getResultList();
			if (buyer.size() > 0) {
				I0032 buyerUpdate = entitymanager.find(I0032.class, buyer
						.get(0).getBuyer_ID());
				logger.info("size"+buyer.size());
				logger.info("title"+b.getCustomerTitle());
				buyerUpdate.setCustomerTitle(b.getCustomerTitle());
				buyerUpdate.setPhoneNumber(b.getPhoneNumber());
				buyerUpdate.setCustomerName(b.getCustomerName());
				logger.info("customername"+b.getCustomerName());
				buyerUpdate.setCustomerMiddleName(b.getCustomerMiddleName());
				buyerUpdate.setCustomerSuffix(b.getCustomerSuffix());
				buyerUpdate.setPhoneCode(b.getCode());
				buyerUpdate.setCountry(b.getCountry());
				buyerUpdate.setEMail(b.getMail());
				buyerUpdate.setCustomerMobile(b.getCustMobile());
				logger.info("phonenumber>>>>>>>>>"+b.getCustMobile());
				logger.info("phonenumber>>>>>>>>>"+b.getPhoneNumber());
				//buyerUpdate.setPhoneNumber(b.getPhoneno());
				buyerUpdate.setCategoryName(b.getCategoryName());
				buyerUpdate.setOther(b.getOther());
				buyerUpdate.setSalesOrderDate(b.getDate());
				buyerUpdate.setCompany(b.getCompany());
				buyerUpdate.setWebsite(b.getWebsite());
				buyerUpdate.setTaxNumber(b.getTaxnumber());
				logger.info("TAxnumber??????????"+b.getTaxnumber());
				buyerUpdate.setCustomerFaxNumber(b.getFaxnumber());
				buyerUpdate.setDisplayNameAs(b.getDisplayName());
				buyerUpdate.setAddress(b.getAddress());
				buyerUpdate.setShipingAddress(b.getPermanentaddress());
				buyerUpdate.setCity(b.getCity());
				buyerUpdate.setPresentCity(b.getPresentCity());
				buyerUpdate.setPresentCountry(b.getPresentcountry());
				buyerUpdate.setPresentPostCode(b.getPerpostcode());
				buyerUpdate.setPermanentPostCode(b.getPerPostCode());
				buyerUpdate.setState(b.getState());
				buyerUpdate.setPresentState(b.getPresentstate());
				buyerUpdate.setNote(b.getNote());
				if(!b.getPhotouploadfile().equals("") || b.getPhotouploadfile()!=null){
					buyerUpdate.setPhotoUpload(b.getPhotouploadfile());
				}
				if(!b.getAttachFilePath().equals("") || b.getAttachFilePath()!=null){
					buyerUpdate.setAttachmentFile(b.getAttachFilePath());
				}
				if(!b.getFilePath().equals("") || b.getFilePath()!=null){
					buyerUpdate.setFreelancerName(b.getFilePath());
				}
				buyerUpdate.setCustomerLicenseNumber(b.getCusLicence()); 
				buyerUpdate.setCustomerExpireDate(b.getCusExdate()); 
				buyerUpdate.setCustomerCode(b.getCusCode()); 
				buyerUpdate.setCustomerType(b.getCusType()); 
				buyerUpdate.setPaymentType(b.getType());
				buyerUpdate.setUpdatedDate(date);
				
				buyerUpdate.setDateOfJoin(b.getDateofjoin());
				
				buyerUpdate.setPnName(b.getPartnerShipName());
				buyerUpdate.setPnDOB(b.getPnDOB());
				buyerUpdate.setPnFatherRelation(b.getPartnerFnamerelation());
				buyerUpdate.setPnEmailID(b.getPnEmailID());
				buyerUpdate.setPnPhoneNumber1(b.getPnPhoneNumber1());
				buyerUpdate.setPnPhoneNumber2(b.getPnPhoneNumber2());
				buyerUpdate.setPnAddress(b.getPnAddress());
				
				
				if("Credit".equalsIgnoreCase(b.getType())){
					q=entitymanager.createQuery("from Paymentcash where paymentType=? ");
					q.setParameter(1, b.getCash()); 
					List<Paymentcash> result1=(List<Paymentcash>) q.getResultList();
					int id=0;
					if(result1.size() > 0){
						id=result1.get(0).getPaymentCashId();
					}
					buyerUpdate.setPaymentCash_ID(entitymanager.find(Paymentcash.class, id));
				}else{
					buyerUpdate.setPaymentCash_ID(null);
				}
				entitymanager.merge(buyerUpdate);
				b.setStatus("Success");
				q = entitymanager.createQuery("from I0021 where phoneNumber=? and client_ID=?");
				q.setParameter(1, b.getPhoneno());
				q.setParameter(2, b.getClientID());
				sales = q.getResultList();
				if (sales.size() > 0) {
					for (int i = 0; i < sales.size(); i++) {
						I0021 sale = entitymanager.find(I0021.class,
								sales.get(i).getSales_ID());
						sale.setCustomerName(b.getCustomerName());
						sale.setSalesOrderDate(b.getDate());
						sale.setShipingAddress(b.getAddress());
						sale.setCity(b.getCity());
						sale.setState(b.getState());
						sale.setCountry(b.getCountry());
						sale.setEMail(b.getMail());
						sale.setPhoneNumber(b.getPhoneNumber());
						sale.setNote(b.getNote());
						entitymanager.merge(sale);
						b.setStatus("Success");
					}
				}
			}
		}

		finally {
			q = null;

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0032> salesCustomer(ArrayList<I0032> buyername,
			PurchaseOrder purchaseOrder) throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<String> resulfinal1 = new ArrayList<String>();
		try {
			q = entitymanager.createQuery("from I0032 where status=? and client_ID=?");
			q.setParameter(1, "in");
			q.setParameter(2, clientID);
			buyername = (ArrayList<I0032>) q.getResultList();
			if (buyername.size() < 0) {
				throw new DemoException("*no date found");
			} else {
				logger.info("inside if::::");

				String temp = "";
				int ii = 0;
				String temp1 = "";
				String s = null;
				int j = 0;
				for (I0032 i00191 : buyername) {
					try {

						String Order = new String();
						logger.info("tempppppppp" + temp);
						int counter = 1;
						s = buyername.get(j).getCustomerName();
						logger.info("CustomerName------------->" + s);
						if (s.equals(temp)) {
							logger.info("inside if");
							counter++;

							logger.info("counter--------->" + counter);
						} else {
							temp = s;
							logger.info("inside else");
							logger.info("result list size------>"
									+ resulfinal1.size());
							for (int i = 0; i < resulfinal1.size(); i++) {
								if (resulfinal1.get(i).equals(
										buyername.get(j).getCustomerName())) {
									logger.info("inside inner for loop");
									throw new Exception();
								}
							}
							counter = 1;
							String s3 = null;
							int s4 = 0;
							s3 = buyername.get(0).getCustomerName();
							resulfinal1.add(buyername.get(j).getCustomerName());
						}
						j++;
					} catch (Exception e) {
						logger.info("log--->" + e.getMessage());
					}
				}
				logger.info("final size------------->" + resulfinal1.size());
				purchaseOrder.setResulfinal1(resulfinal1);
				logger.info("final size------------->"
						+ purchaseOrder.getResulfinal1());

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			q = null;
		}
		return buyername;
	}

	@Transactional(value = "transactionManager")
	public String buyerDelete(Buyer b) throws DemoException {
		Query q = null;
		//String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0032> buyer = null;
		try {
			q = entitymanager.createQuery("from I0032 where buyer_ID=?");
			q.setParameter(1, Integer.parseInt(b.getPhoneNumber()));
			buyer = q.getResultList();
			if (buyer.size() > 0) {
				I0032 buy = entitymanager.find(I0032.class, buyer.get(0).getBuyer_ID());
				buy.setStatus("out");
				entitymanager.merge(buy);
				b.setStatus("Success");
			} else {
				logger.info("No Data Found...");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String getSalesupdation(Buyer b, int saleId) throws DemoException {
		Query q = null;
		List<I0021> sales = null;
		try {
			I0021 sale = entitymanager.find(I0021.class, sales.get(0)
					.getSales_ID());
			sale.setCustomerName(b.getCustomerName());
			sale.setSalesOrderDate(b.getDate());
			sale.setShipingAddress(b.getAddress());
			sale.setCity(b.getCity());
			sale.setState(b.getState());
			sale.setCountry(b.getCountry());
			sale.setEMail(b.getMail());
			sale.setPhoneNumber(b.getPhoneNumber());
			sale.setNote(b.getNote());
			entitymanager.merge(sale);
		} catch (Exception e) {
		} finally {

		}
		return "success";
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0032> salesOrder(ArrayList<I0032> buyername, Buyer b)
			throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<I0032> buyer = null;
		try {
			q = entitymanager
					.createQuery("from I0032 where phoneNumber=? and status='in' and client_ID=? ");
			logger.info("1");
			q.setParameter(1, b.getPhoneNumber());
			q.setParameter(2, clientID);
			logger.info("2");
			buyer = (ArrayList) q.getResultList();
			if (buyer.size() < 0) {
				throw new DemoException("*no Data found");
			} else {
				b.setSaleDetail(buyername);
			}

		} catch (Exception e) {

		}
		return buyer;

	}

	@Transactional(value = "transactionManager")
	public List<StockView> getStockInfo() throws DemoException {
		Query q = null;
		List<StockView> view = null;
		List<I0019> batch2 = null;
		List<I0001> product = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			StockView s = null;
			q = entitymanager
					.createQuery("from I0019 where status=? or status=? and client_ID=?");
			logger.info("2");
			q.setParameter(1, "Solded");
			q.setParameter(2, "Delivered");
			q.setParameter(3, clientID);
			logger.info("3");
			batch2 = q.getResultList();
			if (batch2.size() > 0) {
				view = new ArrayList<StockView>();
				logger.info("comes in:::");
				for (int k = 0; k < batch2.size(); k++) {
					s = new StockView();
					logger.info("1");
					q = entitymanager
							.createQuery("from I0001 where productName=? and client_ID=? ");
					q.setParameter(1, batch2.get(k).getI0018().getProductName());
					q.setParameter(2, clientID);
					logger.info(batch2.get(k).getI0018().getProductName());
					product = q.getResultList();
					if (product.size() > 0) {
						s.setBrand(product.get(0).getBrand());
						s.setProductname(batch2.get(k).getI0018()
								.getProductName());
						s.setBatchName(batch2.get(k).getI0018().getBatchName());
						s.setStockinDate(batch2.get(k).getI0018().getI0017()
								.getStockInDate().toString());
						s.setUnitprice(""
								+ batch2.get(k).getI0018().getUnitPrice());
					}
					view.add(s);
				}
			} else if (batch2.size() == 0) {
				throw new DemoException("");
			}
			logger.info("out");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return view;
	}

	@Transactional(value = "transactionManager")
	public String filePath(PurchaseOrder purchaseOrder) throws DemoException {
		Query q = null, q1 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("purchase order number inside dao---------->"
					+ purchaseOrder.getOrderNumber());
			q1 = entitymanager
					.createQuery("from I0015 where temOrderNumber=? and client_ID=? ");
			q1.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, clientID);
			ArrayList<I0015> result = (ArrayList<I0015>) q1.getResultList();
			if (result.size() > 0) {
				logger.info("list size---------------->" + result.size());
				int purchaseid = 0;
				purchaseid = result.get(0).getPurchase_ID();
				logger.info("purchase id-------------->" + purchaseid);
				q = entitymanager
						.createQuery("from I0033 where purchase_ID=? ");
				q.setParameter(1, purchaseid);
				ArrayList<I0033> result1 = (ArrayList<I0033>) q.getResultList();
				if (result1.size() > 0) {
					String filename = purchaseOrder.getOrderNumber();
					logger.info("order number--------->" + filename);
					String temp = (result1.size() + 1) + "";
					logger.info("file name------>" + filename + "docu" + temp);
					purchaseOrder.setFilenametemp(filename + "docu" + temp);

				} else {
					String filename = purchaseOrder.getOrderNumber();
					logger.info("order number--------->" + filename);
					logger.info("file name------>" + filename + "docu" + 1);
					purchaseOrder.setFilenametemp(filename + "docu" + 1);
				}
			}

		} catch (Exception e) {

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String fileSave(PurchaseOrder purchaseOrder) throws DemoException {
		Query q = null, q1 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("final file name----------->"
					+ purchaseOrder.getFilePathfinal());
			logger.info("final file path---------->"
					+ purchaseOrder.getFilenametemp());
			logger.info("purchase order number inside dao---------->"
					+ purchaseOrder.getOrderNumber());
			q1 = entitymanager.createQuery("from I0015 where temOrderNumber=? and client_ID=?");
			logger.info("1");
			q1.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, clientID);
			logger.info("2");
			ArrayList<I0015> result = (ArrayList<I0015>) q1.getResultList();
			if (result.size() > 0) {
				logger.info("1");
				logger.info("list size---------------->" + result.size());
				int purchaseid = 0;
				purchaseid = result.get(0).getPurchase_ID();
				int invoiceid = result.get(0).getI0022s().get(0)
						.getInvoice_ID();
				logger.info("invoice id------>" + invoiceid);
				logger.info("purchase id------>" + purchaseid);
				I0033 filepath = new I0033();
				filepath.setFilepath(purchaseOrder.getFilePathfinal());
				filepath.setI0015(entitymanager.find(I0015.class, purchaseid));
				filepath.setFinalfilePath(purchaseOrder.getFilenametemp());
				entitymanager.persist(filepath);
				logger.info("1");
				I0022 invoice = entitymanager.find(I0022.class, invoiceid);
				logger.info("2");
				invoice.setApprovalStatus("submitted");
				invoice.setClient_ID(clientID);
				entitymanager.persist(invoice);
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String approvalView(Approval approval) throws DemoException {
		Query q = null, q1 = null;
		ArrayList<I0015> result = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {

			logger.info("Inside the approvalView : ");
			logger.info("inside approval view dao");
			logger.info("from date--->" + approval.fromDate);
			logger.info("to date----->" + approval.toDate);
			q = entitymanager
					.createQuery("from I0015 where orderDate between ? and ? and client_ID=?");
			q.setParameter(1, approval.getFromDate());
			q.setParameter(2, approval.getToDate());
			q.setParameter(3, clientID);
			result = (ArrayList<I0015>) q.getResultList();
			if (result.size() > 0) {
				int purchaseid = 0;

				logger.info("inside if loop-------->" + result.size());
				for (I0015 i0015 : result) {
					logger.info("purchase----->" + purchaseid);
					q1 = entitymanager
							.createQuery("from I0033 where purchase_ID=?");
					q1.setParameter(1, i0015.getPurchase_ID());
					ArrayList<I0033> resullt1 = (ArrayList<I0033>) q1
							.getResultList();
					if (resullt1.size() > 0) {
						ApprovalViewMB approval2 = new ApprovalViewMB();
						approval2.setFirmName(resullt1.get(0).getI0015()
								.getI0016s().get(0).getI0031().getI0025()
								.getVendorPhoneNumber());
						approval2.setOrderNumber(resullt1.get(0).getI0015()
								.getTemOrderNumber());
						approval2.setOrderDate(resullt1.get(0).getI0015()
								.getOrderDate());
						approval2.setTotalAmount(Float.parseFloat(resullt1
								.get(0).getI0015().getTotalPrice()));
						approval2.setStatus("in");
						approval.purchaselist.add(approval2);

						logger.info("approval Id------------->"
								+ resullt1.get(0).getFilepath_Id());

					}

				}
				logger.info("final list11---------->"
						+ approval.purchaselist.size());
				logger.info("check--->"
						+ approval.purchaselist.get(0).orderNumber);
				logger.info("check--->"
						+ approval.purchaselist.get(1).orderNumber);
				logger.info("check--->"
						+ approval.purchaselist.get(2).orderNumber);
			} else {
				// throw new InventoryException("No data found");
			}
			logger.info("outside approval view dao");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String approvalView1(Approval approval) throws DemoException {
		Query q = null, q1 = null;
		ArrayList<I0015> result = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<String> finallist = new ArrayList<String>();
		try {
			logger.info("Inside the approvalView1 : ");
			logger.info("inside approval view1 dao");
			q = entitymanager.createQuery("from I0015 where client_ID=?");
			q.setParameter(1, clientID);
			result = (ArrayList<I0015>) q.getResultList();
			if (result.size() > 0) {
				int purchaseid = 0;

				logger.info("inside if loop-------->" + result.size());
				for (I0015 i0015 : result) {
					logger.info("purchase----->" + purchaseid);
					q1 = entitymanager
							.createQuery("from I0033 where purchase_ID=?  ");
					q1.setParameter(1, i0015.getPurchase_ID());
					ArrayList<I0033> resullt1 = (ArrayList<I0033>) q1
							.getResultList();
					if (resullt1.size() > 0) {
						logger.info("approval Id------------->"
								+ resullt1.get(0).getFilepath_Id());
						finallist.add(resullt1.get(0).getI0015()
								.getTemOrderNumber());

					}

				}
				logger.info("final size------->" + finallist);
				approval.setFinallist(finallist);

			} else {
				// throw new InventoryException("No data found");
			}
			logger.info("outside approval view1 dao");

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String approvalView2(Approval approval) throws DemoException {
		Query q = null, q1 = null;
		ArrayList<I0015> result = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {

			logger.info("Inside the approvalView : ");
			logger.info("inside approval view dao");
			logger.info("purchase order number----->"
					+ approval.getPurchaseNumber());
			q = entitymanager.createQuery("from I0015 where temOrderNumber=? and client_ID=?");
			q.setParameter(1, approval.getPurchaseNumber());
			q.setParameter(2, clientID);
			result = (ArrayList<I0015>) q.getResultList();
			if (result.size() > 0) {
				int purchaseid = 0;

				logger.info("inside if loop-------->" + result.size());
				for (I0015 i0015 : result) {
					logger.info("purchase----->" + purchaseid);
					q1 = entitymanager
							.createQuery("from I0033 where purchase_ID=? ");
					q1.setParameter(1, i0015.getPurchase_ID());
					ArrayList<I0033> resullt1 = (ArrayList<I0033>) q1
							.getResultList();
					if (resullt1.size() > 0) {
						ApprovalViewMB approval2 = new ApprovalViewMB();
						approval2.setFirmName(resullt1.get(0).getI0015()
								.getI0016s().get(0).getI0031().getI0025()
								.getVendorPhoneNumber());
						approval2.setOrderNumber(resullt1.get(0).getI0015()
								.getTemOrderNumber());
						approval2.setOrderDate(resullt1.get(0).getI0015()
								.getOrderDate());
						approval2.setTotalAmount(Float.parseFloat(resullt1
								.get(0).getI0015().getTotalPrice()));
						approval.purchaselist.add(approval2);

						logger.info("approval Id------------->"
								+ resullt1.get(0).getFilepath_Id());

					}

				}
				logger.info("final list11---------->"
						+ approval.purchaselist.size());
				logger.info("check--->"
						+ approval.purchaselist.get(0).orderNumber);
				logger.info("check--->"
						+ approval.purchaselist.get(1).orderNumber);
				logger.info("check--->"
						+ approval.purchaselist.get(2).orderNumber);
			} else {
				throw new DemoException("No data found");
			}
			logger.info("outside approval view dao");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String approvalView3(Approval approval) throws DemoException {
		Query q = null, q1 = null;
		ArrayList<I0015> result = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {

			logger.info("Inside the approvalView3 : ");
			logger.info("inside approval view3 dao");
			logger.info("purchase order number----->"
					+ approval.getPurchaseNumber());
			logger.info("purchase order number----->"
					+ approval.getPurchaseNumber());
			q = entitymanager.createQuery("from I0015 where temOrderNumber=? and client_ID=?");
			q.setParameter(1, approval.getPurchaseNumber());
			q.setParameter(2, clientID);
			result = (ArrayList<I0015>) q.getResultList();
			if (result.size() > 0) {
				int purchaseid = 0;

				logger.info("inside if loop-------->" + result.size());
				for (I0015 i0015 : result) {
					logger.info("purchase----->" + i0015.getPurchase_ID());
					q1 = entitymanager
							.createQuery("from I0033 where purchase_ID=? ");
					q1.setParameter(1, i0015.getPurchase_ID());
					ArrayList<I0033> resullt1 = (ArrayList<I0033>) q1
							.getResultList();
					if (resullt1.size() > 0) {
						approval.setFilelist(resullt1);
						logger.info("file size----->" + resullt1.size());
						logger.info("file path----->"
								+ resullt1.get(0).getFilepath());
						logger.info("file path----->"
								+ resullt1.get(0).getFinalfilePath());
					}

				}

			} else {
				throw new DemoException("No data found");
			}
			logger.info("outside approval view dao");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0033> approvalView4(Approval approval)
			throws DemoException {
		Query q = null, q1 = null;
		ArrayList<I0015> result = null;
		ArrayList<I0033> resullt1 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {

			logger.info("Inside the approvalView4 : ");
			logger.info("inside approval view4 dao");
			logger.info("purchase order number----->"
					+ approval.getPurchaseNumber());
			logger.info("purchase order number4----->"
					+ approval.getPurchaseNumber());
			q = entitymanager.createQuery("from I0015 where temOrderNumber=? and client_ID=?");
			q.setParameter(1, approval.getPurchaseNumber());
			q.setParameter(2, clientID);
			result = (ArrayList<I0015>) q.getResultList();
			if (result.size() > 0) {
				int purchaseid = 0;

				logger.info("inside if loop-------->" + result.size());
				for (I0015 i0015 : result) {
					logger.info("purchase----->" + i0015.getPurchase_ID());
					q1 = entitymanager
							.createQuery("from I0033 where purchase_ID=? ");
					q1.setParameter(1, i0015.getPurchase_ID());
					resullt1 = (ArrayList<I0033>) q1.getResultList();
					if (resullt1.size() > 0) {
						for (I0033 i0033 : resullt1) {

						}
						approval.setResullt1(resullt1.get(0).getI0015()
								.getI0022s().get(0).getInvoice_ID());
					}

				}

			} else {
				throw new DemoException("No data found");
			}
			logger.info("outside approval view dao");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return resullt1;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0033> approvalView5(ArrayList<I0033> approval, int i,
			int j) throws DemoException {
		try {
			logger.info("1");

			I0033 i0033 = entitymanager.find(I0033.class, approval.get(i)
					.getFilepath_Id());

			entitymanager.remove(i0033);

		} catch (Exception e) {
			logger.error("Inside Exception", e);

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public void approvalView6(int i) throws DemoException {
		try {

			logger.info("iiiiiiiiiiii" + i);
			I0022 i0022 = entitymanager.find(I0022.class, i);
			i0022.setApprovalStatus("not submitted");
			entitymanager.merge(i0022);

		} catch (Exception e) {
			logger.error("Inside Exception", e);

		}

	}

	@Transactional(value = "transactionManager")
	public String categoryType(CategoryRegistration categoryreg)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		String status="";
		List<I0005> i0005s = new ArrayList<I0005>();
		Query q2 = null;
		q2 = entitymanager.createQuery("from I0005 where categoryType=? and client_ID=? and user_ID=? and status='Active'");
		q2.setParameter(1, categoryreg.getCategory());
		q2.setParameter(2, clientID);
		q2.setParameter(3, userID);
		i0005s = q2.getResultList();
		logger.info("inside dao-------->>" + i0005s.size() + "!!!!!cat~~~~"
				+ categoryreg.getCategory());
		if (i0005s.size() > 0) {
			throw new DemoException("Already Registered This Category");
		}

		else {
			logger.info("in~~~else~~~");
			I0005 cat = new I0005();
			cat.setCategoryType(categoryreg.getCategory());
			cat.setClient_ID(clientID);
			cat.setDescription(categoryreg.getDescription());
			cat.setStatus("Active");
			cat.setApprovalStatus("draft");
			cat.setCreatedDate(date);
			cat.setUserID(entitymanager.find(UserCreate.class, Integer.parseInt(userID)));
			entitymanager.persist(cat);
			status="Success";
			logger.info("successfully inserted---->>");
		}

		return status;
	}

	@Transactional(value = "transactionManager")
	public List<String> categorylist(List<String> categorytype)
			throws DemoException {
		logger.info("inside dao->>>");
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		try {
			logger.info("~~Q~~~~");
			q = entitymanager.createQuery("select categoryType from I0005 where client_ID=? and user_ID=? and approvalStatus='Approved' and status='Active'");
			q.setParameter(1, clientID);
			q.setParameter(2, userID);
			categorytype = q.getResultList();
			logger.info("category type---->>" + categorytype);

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return categorytype;
	}

	@Transactional(value = "transactionManager")
	public String category(ProductRegister productRegister)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");

		logger.info("inside dao----->>");
		try {
			Query q = null;
			q = entitymanager.createQuery("from I0005 where categoryType=? and client_ID=?");
			q.setParameter(1, productRegister.getCategory());
			q.setParameter(2, clientID);
			List<I0005> result1 = (List<I0005>) q.getResultList();
			int cid = 0;
			if (result1.size() > 0) {
				cid = result1.get(0).getCategoryId();
			}
			q = entitymanager
					.createQuery("from I0001 where category_id=? and status='i'");
			q.setParameter(1, cid);
			List<I0001> result = (List<I0001>) q.getResultList();
			productRegister.setDatalist(result);
			logger.info("datalist----->>" + result);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	/* udhaya 2.1.2015 **** */
	@Transactional(value = "transactionManager")
	public int getPurchaseapprovalstatus() throws DemoException {
		Query q = null;
		List<I0015> pur = null;
		List<I0016> pur1 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");

		int count = 0;
		try {
			q = entitymanager.createQuery("from I0016 where approvalStatus=? and client_ID=?");
			q.setParameter(1, "inserted");
			q.setParameter(2, clientID);
			pur1 = q.getResultList();

			for (int i = 0; i < pur1.size(); i++) {
				count++;
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return count;
	}

	@Transactional(value = "transactionManager")
	public String approvalstatus(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.info("inside dao-->>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q = null;
			q = entitymanager
					.createQuery("from I0016 where approvalStatus='inserted' and client_ID=?");
			q.setParameter(1, clientID);
			ArrayList<I0016> result = (ArrayList<I0016>) q.getResultList();
			purchaseOrder.setDatalist(result);
			logger.info("datalist----->>" + result);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	/* udhaya 5.1.2015 */
	@Transactional(value = "transactionManager")
	public ArrayList<I0016> approvalStage(String orderNumber,
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.info("inside dao--->>in approval");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");

		logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseOrdercancel Dao-------------$$$$$$$$$$$$$$-----------");
		logger.info(orderNumber);
		Query q = null;

		ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
		ArrayList<I0015> result = null;
		try {
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0015 where temOrderNumber=? and (status=? or status='delivered')  and client_ID=?");
			q1.setParameter(1, orderNumber);
			q1.setParameter(2, "insert");
			q1.setParameter(3, clientID);
			result = (ArrayList<I0015>) q1.getResultList();
			if (result.size() > 0) {
				logger.info("result size---------->" + result.size());
				q = entitymanager
						.createQuery("from I0016 where ordernumber=? and status=? and (status3=? or status3='delivered')  and client_ID=?");
				q.setParameter(1, orderNumber);
				q.setParameter(2, "inserted");
				q.setParameter(3, "Waiting");
				q.setParameter(4, clientID);
				purchaselist = (ArrayList<I0016>) q.getResultList();
				logger.info("excuted::::::");
				if (purchaselist.size() == 0) {

				} else {

					int count = 0;
					for (I0015 i0015 : result) {
						PurchaseOrder purchaseOrder2 = new PurchaseOrder();
						logger.info("1");
						purchaseOrder2.setOrderDate(i0015.getOrderDate());
						logger.info("2");
						purchaseOrder2
								.setOrderNumber(i0015.getTemOrderNumber());
						logger.info("3");
						purchaseOrder2.setFirmName(i0015.getI0016s().get(0)
								.getI0031().getI0025().getVendorPhoneNumber());
						logger.info("4");
						purchaseOrder2.setPhonenumber(i0015.getI0016s().get(0)
								.getI0031().getI0025().getFirmName());
						logger.info("5");
						purchaseOrder2.setIndustry(i0015.getI0016s().get(0)
								.getI0031().getI0001().getIndustryProduct());
						logger.info("6");
						purchaseOrder2
								.setTotalPrice("" + i0015.getTotalPrice());
						logger.info("7");

						purchaseOrder2.setPayableAmount(""
								+ i0015.getI0022s().get(0).getI0023s().get(0)
										.getBalanceAmount());
						logger.info("8");
						purchaseOrder2.setStatus2(i0015.getI0016s().get(0)
								.getStatus2());
						purchaseOrder2.setStatus3(i0015.getI0016s().get(0)
								.getStatus3());
						result4.add(purchaseOrder2);
						count++;
					}
					purchaseOrder.setResult4(result4);
					logger.info("size============>" + result4.size());

				}
			}

			logger.info("--------------$$$$$$$$$$$$$$------------Outside purchaseView dao-------------$$$$$$$$$$$$$$-----------");

		} catch (Exception e) {
			logger.info("<-----------------inside exception------------->");
			int count = 0;
			for (I0015 i0015 : result) {
				PurchaseOrder purchaseOrder2 = new PurchaseOrder();
				logger.info("1");
				purchaseOrder2.setOrderDate(i0015.getOrderDate());
				logger.info("2");
				purchaseOrder2.setOrderNumber(i0015.getTemOrderNumber());
				logger.info("3");
				purchaseOrder2.setFirmName(i0015.getI0016s().get(0).getI0031()
						.getI0025().getVendorPhoneNumber());
				logger.info("4");
				purchaseOrder2.setPhonenumber(i0015.getI0016s().get(0)
						.getI0031().getI0025().getFirmName());
				logger.info("5");
				purchaseOrder2.setIndustry(i0015.getI0016s().get(0).getI0031()
						.getI0001().getIndustryProduct());
				logger.info("6");
				purchaseOrder2.setTotalPrice("" + i0015.getTotalPrice());
				logger.info("7");

				purchaseOrder2.setPayableAmount("" + i0015.getTotalPrice());
				logger.info("8");
				purchaseOrder2
						.setStatus2(i0015.getI0016s().get(0).getStatus2());
				purchaseOrder2
						.setStatus3(i0015.getI0016s().get(0).getStatus3());
				result4.add(purchaseOrder2);
				count++;
			}
			purchaseOrder.setResult4(result4);
			logger.info("size============>" + result4.size());
			logger.info("inside exeption------------>" + e.getStackTrace());
		}
		return purchaselist;
	}

	@Transactional(value = "transactionManager")
	public String approved(PurchaseOrder purchaseOrder) throws DemoException {
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			logger.info("inside dao------->>");
			Query q = null;
			Query q1 = null;
			q = entitymanager.createQuery("from I0015 where temOrderNumber=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, clientID);
			List<I0015> result = (List<I0015>) q.getResultList();
			int hID = 0;
			int i = 0;
			if (result.size() > 0) {
				logger.info("1");
				q1 = entitymanager
						.createQuery("from I0016 where ordernumber=?  and client_ID=?");
				logger.info("2");
				q1.setParameter(1, purchaseOrder.getOrderNumber());
				q1.setParameter(2, clientID);
				ArrayList<I0016> result1 = (ArrayList<I0016>) q1
						.getResultList();
				logger.info("3");
				logger.info("size-------??" + result1.size());
				if (result1.size() > 0) {
					int j = 0;
					for (I0016 i0016 : result1) {
						hID = result1.get(j).getHas_purchase_ID();
						I0016 haspurchase = entitymanager
								.find(I0016.class, hID);
						logger.info("1");
						haspurchase.setApprovalStatus("approved");
						entitymanager.merge(haspurchase);
						j++;
					}
				} else {
					throw new DemoException("==========");
				}
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String getPurchaseVendorView(PurchaseOrder purchaseVendor)
			throws DemoException {

		Query q = null;
		List<PurchaseOrder> vendorview = new ArrayList<PurchaseOrder>();
		List<PurchaseOrder> result5 = new ArrayList<PurchaseOrder>();
		ArrayList<I0016> vendorInf = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("%%%%%%%%%%%%% inside vendor info dao%%%%%%%%%%%");
			q = entitymanager
					.createQuery("from I0025 where vendorPhoneNumber=? and client_ID=?");
			q.setParameter(1, purchaseVendor.getVendorPhoneNumber());
			q.setParameter(2, clientID);
			List<I0025> result = (List<I0025>) q.getResultList();
			logger.info("list-------->>" + result.size());
			int vid = 0;
			int i = 0;
			logger.info("" + result.size());
			if (result.size() > 0) {

				logger.info("result size---------->" + result.size());
				vid = result.get(i).getVendor_ID();

				I0025 venid = entitymanager.find(I0025.class, vid);
				logger.info("Vendr Id" + vid);

				int count = 0;

				Query q2 = null;
				q2 = entitymanager.createQuery("from I0031 where vendor_ID=?");
				q2.setParameter(1, vid);
				List<I0031> result1 = (List<I0031>) q2.getResultList();
				int hasvendId = 0;
				if (result1.size() > 0) {
					logger.info("--------Finf hasVendor ID------");
					hasvendId = result1.get(0).getHas_vendor_ID();
					logger.info("----------invoiceId------------" + hasvendId);
					Query q3 = null;
					q3 = entitymanager
							.createQuery("from I0016 where has_vendor_ID=?");
					q3.setParameter(1, hasvendId);

					List<I0016> result3 = (List<I0016>) q3.getResultList();
					logger.info("excuted::::::");
					if (result3.size() > 0) {
						for (i = 0; i < result3.size(); i++) {
							PurchaseOrder po = new PurchaseOrder();
							po.setOrderDate(result3.get(i).getOrderDate());
							logger.info("OrderDate"
									+ result3.get(i).getOrderDate());
							po.setAddress(result3.get(i).getI0031().getI0025()
									.getAddress());
							logger.info("Address"
									+ result3.get(i).getI0031().getI0025()
											.getAddress());
							po.setOrderNumber(result3.get(i).getOrdernumber());
							logger.info("OrderNumbernumber"
									+ result3.get(i).getOrdernumber());
							po.setVendorPhoneNumber(result3.get(i).getI0031()
									.getI0025().getVendorPhoneNumber());
							po.setFirmName(result3.get(i).getI0031().getI0025()
									.getFirmName());
							logger.info("Phonenumber"
									+ result3.get(i).getI0031().getI0025()
											.getFirmName());
							po.setApprovalStatus(result3.get(i)
									.getApprovalStatus());
							logger.info("AppStatus"
									+ result3.get(i).getApprovalStatus());
							po.setStatus2(result3.get(i).getStatus2());
							logger.info("Status" + result3.get(i).getStatus2());
							po.setStatus3(result3.get(i).getStatus3());
							logger.info("Status3" + result3.get(i).getStatus3());

							vendorview.add(po);
							logger.info("1");
							purchaseVendor.setResult5(vendorview);
							logger.info("2");
							count++;

						}

					}
				}

				logger.info("--------------$$$$$$$$$$$$$$------------Outside purchaseView dao-------------$$$$$$$$$$$$$$-----------");

			}
			if (result.size() == 0) {

				logger.info("Zero-------------->");
				result = q.getResultList();
				logger.info("****cust name*********" + result);

			}

		}

		catch (Exception e) {
			logger.info("" + e.getMessage());

		} finally {

		}

		return null;

	}

	/* kasturi */

	/** remainder method 1 **/
	@Transactional(value = "transactionManager")
	public ArrayList<I0016> purchaseOrderPayment1(
			ArrayList<I0016> purchaselist, PurchaseOrder purchaseOrder)
			throws DemoException {
		ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
		ArrayList<I0015> result = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q = null;
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0015 where status=? or status='delivered' and client_ID=?");
			q1.setParameter(1, "insert");
			q1.setParameter(2, clientID);
			result = (ArrayList<I0015>) q1.getResultList();
			if (result.size() > 0) {
				logger.info("result size---------->" + result.size());
				q = entitymanager.createQuery("from I0016 where  status=? and client_ID=?");
				q.setParameter(1, "inserted");
				q.setParameter(2, clientID);
				purchaselist = (ArrayList<I0016>) q.getResultList();
				logger.info("excuted::::::");
				if (purchaselist.size() == 0) {
					throw new DemoException("No value found !");
				}

				else {
					int count = 0;
					for (I0015 i0015 : result) {
						PurchaseOrder purchaseOrder2 = new PurchaseOrder();
						logger.info("1");
						purchaseOrder2.setOrderDate(i0015.getOrderDate());
						logger.info("2");
						purchaseOrder2
								.setOrderNumber(i0015.getTemOrderNumber());
						logger.info("3");
						purchaseOrder2.setFirmName(i0015.getI0016s().get(0)
								.getI0031().getI0025().getVendorPhoneNumber());
						logger.info("4");
						purchaseOrder2.setPhonenumber(i0015.getI0016s().get(0)
								.getI0031().getI0025().getFirmName());
						logger.info("5");
						purchaseOrder2.setIndustry(i0015.getI0016s().get(0)
								.getI0031().getI0001().getIndustryProduct());
						logger.info("6");
						purchaseOrder2
								.setTotalPrice("" + i0015.getTotalPrice());
						logger.info("7");
						try {
							purchaseOrder2.setPayableAmount(""
									+ i0015.getI0022s().get(0).getI0023s()
											.get(0).getBalanceAmount());

						} catch (Exception e) {
							logger.info("inside inner exception:::::::");
							purchaseOrder2.setPayableAmount(""
									+ i0015.getTotalPrice());
						}

						logger.info("8");
						purchaseOrder2.setStatus(i0015.getI0016s().get(0)
								.getStatus2());
						result4.add(purchaseOrder2);
						count++;
					}
				}
				logger.info("size 1===============>" + result.size());
				purchaseOrder.setResult4(result4);
				logger.info("size============>" + result4.size());
			}

		} catch (Exception e) {

			logger.info("<-----------------inside exception------------->");
			int count = 0;
			result4.clear();

			logger.error("Inside Exception", e);
			logger.info("size 1===============>" + result.size());
			purchaseOrder.setResult4(result4);
			logger.info("size============>" + result4.size());
			logger.info("inside exeption------------>" + e.getStackTrace());
		}
		return purchaselist;
	}

	/** remainder method sales delivery **/
	@Transactional(value = "transactionManager")
	public String remSalesDeliver(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q = null;
			List<I0019> resu = new ArrayList<I0019>();
			logger.info("purorder number" + purchaseOrder.getSalesIdReference());
			q = entitymanager.createQuery("from I0021 where status='insert' and client_ID=?");
			q.setParameter(1, clientID);
			logger.info("inside sales order cancel::::");
			List<I0021> result = (List<I0021>) q.getResultList();
			int pkid = 0;
			int i = 0;
			if (result.size() > 0) {
				logger.info("1");
				pkid = result.get(i).getSales_ID();
				logger.info("2");
				Query q1 = null;
				q1 = entitymanager.createQuery("from I0019 where sales_ID=?");
				logger.info("3");
				q1.setParameter(1, pkid);
				logger.info("4");
				List<I0019> resul = (List<I0019>) q1.getResultList();
				logger.info("5");
				resu.addAll(0, resul);
				purchaseOrder.setResul(resu);
				logger.info("sssssssiiiiiiizeeeeee"
						+ purchaseOrder.getResul().size());
				purchaseOrder.setResult(result);
				logger.info("6");
			} else {
				logger.info("inside 4 else loop--------->>>>>");
				throw new DemoException("*already Cancelled Or delivered");
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {

		}
		return null;

	}

	/** remainder method sales delivery **/
	@Transactional(value = "transactionManager")
	public String remSalesDelivery(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q = null;
			List<I0019> resu = new ArrayList<I0019>();
			logger.info("purorder number" + purchaseOrder.getOrderNumber());

			q = entitymanager
					.createQuery("from I0021 where salesOrderNumber=? and status='insert' and client_ID=?");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, clientID);
			logger.info("inside sales order cancel::::");
			List<I0021> result = (List<I0021>) q.getResultList();
			int deliverid = 0;
			if (result.size() > 0) {
				deliverid = result.get(0).getSales_ID();
			}
			I0021 sales = entitymanager.find(I0021.class, deliverid);
			sales.setStatus("delivered");
			entitymanager.merge(sales);
		} catch (Exception e) {
			e.getMessage();
		} finally {

		}

		return "";
	}

	/** remainder method purchase delivery **/
	@Transactional(value = "transactionManager")
	public ArrayList<I0016> remPurchaseDeliver(ArrayList<I0016> purchaselist,
			PurchaseOrder purchaseOrder) throws DemoException {
		logger.info(" inside dao");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
		ArrayList<I0016> result = null;
		try {
			logger.info(" inside dao try");
			Query q = null;
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0016 where approvalStatus='approved' and status3='Waiting' and client_ID=?");
			q1.setParameter(1, clientID);
			result = (ArrayList<I0016>) q1.getResultList();
			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					PurchaseOrder order = new PurchaseOrder();
					order.setOrderDate(result.get(i).getOrderDate());
					order.setOrderNumber(result.get(i).getOrdernumber());
					order.setFirmName(result.get(i).getI0031().getI0025()
							.getFirmName());
					order.setTelephonenumber(result.get(i).getI0031()
							.getI0025().getVendorTelephoneNumber());
					order.setTotalPrice(""
							+ result.get(i).getI0015().getTotalPrice());
					order.setStatus(result.get(i).getApprovalStatus());
					result4.add(order);
					purchaseOrder.setResult5(result4);

				}
				logger.info("----------" + result.size());
			} else {
				logger.info("no records found");
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return purchaselist;
	}

	/** remainder method purchase payment **/
	@Transactional(value = "transactionManager")
	public String remPurchasePaymentStatus(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.info(" inside dao");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<PurchaseOrder> result5 = new ArrayList<PurchaseOrder>();
		ArrayList<I0015> result = null;
		ArrayList<I0016> result1 = null;
		ArrayList<I0016> result2 = null;
		try {
			logger.info(" inside dao try");
			Query q = null;
			Query q1 = null;

			q1 = entitymanager.createQuery("from I0015 where client_ID=?");
			q1.setParameter(1, clientID);
			result = (ArrayList<I0015>) q1.getResultList();
			if (result.size() > 0) {
				logger.info("------ list 1--" + result.size());
				for (int i = 0; i < result.size(); i++) {

					q = entitymanager
							.createQuery("from I0016 where  status2=? and ordernumber=? and status='inserted' and client_ID=?");
					q.setParameter(1, "pending");
					q.setParameter(2, result.get(i).getTemOrderNumber());
					q.setParameter(3, clientID);
					result1 = (ArrayList<I0016>) q.getResultList();
					logger.info("---------->>-" + result1.size());
					if (result1.size() > 0) {
						PurchaseOrder order = new PurchaseOrder();
						order.setOrderDate(result.get(i).getOrderDate());
						order.setOrderNumber(result.get(i).getTemOrderNumber());
						logger.info("==names=====>>>>"
								+ result.get(i).getI0016s().get(0).getI0031()
										.getI0025().getVendorPhoneNumber());
						order.setFirmName(result.get(i).getI0016s().get(0)
								.getI0031().getI0025().getVendorPhoneNumber());
						order.setTelephonenumber(result.get(i).getI0016s()
								.get(0).getI0031().getI0025()
								.getVendorTelephoneNumber());
						order.setQ1(new BigDecimal(result.get(i).getI0016s()
								.get(0).getI0031().getI0025()
								.getVendorTelephoneNumber()));
						order.setTotalPrice("" + result.get(i).getTotalPrice());
						order.setQ(new BigDecimal(result.get(i).getTotalPrice()));
						order.setStatus(result.get(i).getI0016s().get(0)
								.getStatus2());
						try {
							// logger.info("ram--------"+result.get(i).getI0022s());
							// logger.info("--- pid"+result.get(i).getPurchase_ID());
							order.setQ2(new BigDecimal(result.get(i)
									.getI0022s().get(0).getI0023s().get(0)
									.getBalanceAmount()));
							order.setPayableAmount(""
									+ result.get(i).getI0022s().get(0)
											.getI0023s().get(0)
											.getBalanceAmount());
						} catch (IndexOutOfBoundsException e) {
							order.setQ2(new BigDecimal(result.get(i)
									.getTotalPrice()));
							order.setPayableAmount(""
									+ result.get(i).getTotalPrice());
							// logger.info("inside nullpointer exception");
						}

						// logger.info("-------->>"+result.get(i).getI0022s().get(0).getI0023s().get(0).getBalanceAmount());
						// logger.info("---"+result.get(i).getI0016s().get(0).getI0031().getI0025().getFirmName());
						result5.add(order);
						purchaseOrder.setResult6(result5);
					}

				}

			} else {
				logger.info("no records found");
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			e.getMessage();
		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String quickSaleView(PurchaseOrder purchaseOrder)
			throws DemoException {
		List<I0001> products = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		Query q = null;
		try {
			if(userType.equals("Maker")){
				q = entitymanager.createQuery("from I0021 where  status='Quick sales' and client_ID=? and user_ID=? ORDER BY createdDate DESC");
				q.setParameter(1, clientID);
				q.setParameter(2, userID);
			}else{
				if (purchaseOrder.getApproval()=="ApprovalData") {
					q = entitymanager.createQuery("from I0021 where  status='Quick sales' and client_ID=? and approvalStatus='draft' ORDER BY createdDate DESC");
					q.setParameter(1, clientID);
				}
				else {
					q = entitymanager.createQuery("from I0021 where  status='Quick sales' and client_ID=? ORDER BY createdDate DESC");
					q.setParameter(1, clientID);
				}
				
			}
			List<I0021> result = (List<I0021>) q.getResultList();
			purchaseOrder.setResult(result);
			logger.info("datalist----->>" + result);

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public List<I0021> view(List<I0021> saleslist, PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.info("inside dao------->>");
		ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
		List<I0021> result = null;
		try {
			logger.info("inside try in dao=========>>>");
			Query q = null;
			q = entitymanager
					.createQuery("from I0021 where salesOrderNumber=?  and client_ID=?");
			q.setParameter(1, purchaseOrder.getSalesIdReference());
			q.setParameter(2, clientID);
			logger.info("sales no---->>>" + purchaseOrder.getSalesIdReference());
			saleslist = (List<I0021>) q.getResultList();
			int sid = 0;
			if (saleslist.size() > 0) {
				sid = saleslist.get(0).getSales_ID();
				logger.info("sales id----->>" + sid);
				for (I0021 i0021 : result) {
					PurchaseOrder purchaseOrder1 = new PurchaseOrder();
					purchaseOrder1.setGrosstotal(""
							+ result.get(0).getCrossTotal());
					purchaseOrder1.setCustomername(result.get(0)
							.getCustomerName());
					purchaseOrder1.setDelayreason(result.get(0).getNote());
					purchaseOrder1.setDeliverydate(result.get(0)
							.getDeliveryDate());
					purchaseOrder1.setEmail(result.get(0).getEMail());
					purchaseOrder1.setOrderDate(result.get(0)
							.getSalesOrderDate());
					purchaseOrder1.setShipingaddress(result.get(0)
							.getShipingAddress());
					purchaseOrder1.setOrderNumber(result.get(0)
							.getSalesOrderNumber());
					purchaseOrder1.setPhonenumber(result.get(0)
							.getPhoneNumber());
					// purchaseOrder1.setProduct_name(result.get(0).getI0019s().get(0).getI0001().getProductName());
					// purchaseOrder1.setSellingPrice(result.get(0).getI0019s().get(0).getI0001().getSellingPrice());
					result4.add(purchaseOrder1);
				}
			}
			purchaseOrder.setResult4(result4);
			logger.info("result------->>" + result4.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return saleslist;
	}

	/* jeni */

	@Transactional(value = "transactionManager")
	public String salesPaypend(PurchaseOrder purchaseOrder)
			throws DemoException {
		List<PurchaseOrder> purchase = new ArrayList<PurchaseOrder>();
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {

			Query q = null;
			logger.info("purorder number" + purchaseOrder.getSalesIdReference());

			q = entitymanager
					.createQuery("from I0021 where (status2='pending' or status2='unpaid') and client_ID=?");
			q.setParameter(1, clientID);
			logger.info("inside sales order cancel::::");
			List<I0021> result = (List<I0021>) q.getResultList();
			logger.info("sizze===" + result.size());
			purchaseOrder.setResult(result);
			if (result.size() > 0) {
				logger.info("-----insid if------");
				for (int i = 0; i < result.size(); i++) {
					logger.info("-----insid for----");
					try {
						logger.info("-----insid try-----"
								+ result.get(i).getI0022s().get(0).getI0023s()
										.get(0).getBalanceAmount());
						String f = result.get(i).getI0022s().get(0).getI0023s()
								.get(0).getBalanceAmount();
						logger.info("===ffff====ffff===" + f);

						PurchaseOrder po = new PurchaseOrder();
						po.setSalesorderdate(result.get(i).getSalesOrderDate());
						po.setSalesIdReference(result.get(i)
								.getSalesOrderNumber());
						po.setCustomername(result.get(i).getCustomerName());
						po.setA(new BigDecimal(result.get(i).getPhoneNumber()));
						logger.info("phn num===" + po.getA());
						logger.info("==totlll==="
								+ new BigDecimal(result.get(i).getCrossTotal()));
						po.setA1(new BigDecimal(result.get(i).getCrossTotal()));
						logger.info("=bal====tottt===" + po.getA1());
						po.setA2(new BigDecimal(f));
						po.setPhonenumber(result.get(i).getPhoneNumber());
						po.setTotalPrice("" + result.get(i).getCrossTotal());
						po.setActualPrice(f);
						po.setStatus(result.get(i).getStatus2());
						purchase.add(po);
					} catch (Exception e) {

						PurchaseOrder po = new PurchaseOrder();
						po.setSalesorderdate(result.get(i).getSalesOrderDate());
						po.setSalesIdReference(result.get(i)
								.getSalesOrderNumber());
						po.setCustomername(result.get(i).getCustomerName());
						logger.info("==phn==num=="
								+ new BigDecimal(""
										+ result.get(i).getPhoneNumber()));

						po.setA(new BigDecimal(""
								+ result.get(i).getPhoneNumber()));
						logger.info("===a====" + po.getA());
						po.setA1(new BigDecimal(""
								+ result.get(i).getCrossTotal()));
						po.setA2(new BigDecimal(""
								+ result.get(i).getCrossTotal()));
						po.setPhonenumber(result.get(i).getPhoneNumber());
						po.setTotalPrice("" + result.get(i).getCrossTotal());
						po.setActualPrice("" + result.get(i).getCrossTotal());
						po.setStatus(result.get(i).getStatus2());
						purchase.add(po);
					}

				}

			}

			purchaseOrder.setResult5(purchase);

			return null;
		} catch (Exception e) {
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public int getSalesPayStatus() throws DemoException {
		Query q = null;
		List<I0021> pur = null;
		int count = 0;
		try {

			q = entitymanager
					.createQuery("from I0021 where (status2=? or status2=?)");
			q.setParameter(1, "pending");
			q.setParameter(2, "unpaid");
			pur = q.getResultList();
			if (pur.size() > 0) {
				logger.info("Size of Pending" + pur.size());
			}
			for (int i = 0; i < pur.size(); i++) {
				count++;
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return count;
	}

	/* udhaya 7.1.2015,8.1.2015 */

	@Transactional(value = "transactionManager")
	public List<PurchaseOrder> getQuicksaleEdit(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.info("inside dao--------->>");
		List<PurchaseOrder> editlist = null;
		List<I0021> details = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			logger.info("inside try in dao--------->>>");
			q = entitymanager
					.createQuery("from I0021 where salesOrderNumber=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getSalesIdReference());
			q.setParameter(2, clientID);
			logger.info("sales id----->>" + purchaseOrder.getSalesIdReference());
			details = (List<I0021>) q.getResultList();
			int sid = 0;
			if (details.size() > 0) {
				sid = details.get(0).getSales_ID();
				logger.info("sales id----->>" + sid);
			}
			if (purchaseOrder.getStatus().equalsIgnoreCase("edit")) {
				editlist = new ArrayList<PurchaseOrder>();
				PurchaseOrder purchase = new PurchaseOrder();
				purchase.setGrosstotal("" + details.get(0).getCrossTotal());
				logger.info("cross total----->>>"
						+ details.get(0).getCrossTotal());
				purchase.setCustomername(details.get(0).getCustomerName());
				logger.info("customer name------->>>>"
						+ details.get(0).getCustomerName());
				purchaseOrder.setDelayreason(details.get(0).getNote());
				logger.info("description---->>" + details.get(0).getNote());
				purchase.setDeliverydate(details.get(0).getDeliveryDate());
				logger.info("del date----------->>"
						+ details.get(0).getDeliveryDate());
				purchase.setEmail(details.get(0).getEMail());
				purchase.setOrderDate(details.get(0).getSalesOrderDate());
				logger.info("date---->>" + details.get(0).getSalesOrderDate());
				purchase.setPhonenumber(details.get(0).getPhoneNumber());
				logger.info("ph no-------->>" + details.get(0).getPhoneNumber());
				purchase.setSalesIdReference(details.get(0)
						.getSalesOrderNumber());
				logger.info("sales no---->>"
						+ details.get(0).getSalesOrderNumber());
				purchase.setShipingaddress(details.get(0).getShipingAddress());
				logger.info("shipping address---------->>>"
						+ details.get(0).getShipingAddress());
				editlist.add(purchase);
				logger.info("edit list---->>" + editlist.size());
			} else if (purchaseOrder.getStatus().equalsIgnoreCase("update")) {
				I0021 edit = entitymanager.find(I0021.class, sid);
				edit.setCrossTotal(purchaseOrder.getGrosstotal());
				edit.setCustomerName(purchaseOrder.getCustomername());
				edit.setDeliveryDate(purchaseOrder.getDeliverydate());
				edit.setEMail(purchaseOrder.getEmail());
				edit.setPhoneNumber(purchaseOrder.getPhonenumber());
				edit.setSalesOrderDate(purchaseOrder.getOrderDate());
				edit.setSalesOrderNumber(purchaseOrder.getSalesIdReference());
				edit.setShipingAddress(purchaseOrder.getShipingaddress());
				entitymanager.merge(edit);
			} else if (purchaseOrder.getStatus().equalsIgnoreCase("delete")) {
				I0021 del = entitymanager.find(I0021.class, sid);
				del.setQuickStatus("delete");
				entitymanager.merge(del);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return editlist;
	}

	/* udhaya 8.1.2015 */
	@Transactional(value = "transactionManager")
	public List<I0021> customerNameChange(List<I0021> cusname)
			throws DemoException {
		logger.info("inside dao------->>>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		q = entitymanager
				.createQuery("select customerName from I0021 where status='insert' and client_ID=?");
		q.setParameter(1, clientID);
		cusname = q.getResultList();
		logger.info("custromer name--------->>" + cusname);
		return cusname;
	}

	/** jency *23*9 */
	@Override
	@Transactional(value = "transactionManager")
	public List<I0021> getProductInf(Date date, Date date2)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0021> product = null;
		Query q = null;
		try {
			logger.info("~~~dao ~~~~ddd~~~~" + date);
			logger.info("~~~~dao~~~~D1~~~~~~" + date2);
			q = entitymanager
					.createQuery("from I0021 where salesOrderDate between ? and ? and (status='insert' or status='Delivered' or status='Quick sales')  and client_ID=? ");
			q.setParameter(1, date);
			q.setParameter(2, date2);
			q.setParameter(3, clientID);
			product = q.getResultList();
			logger.info("size=====" + product.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
		}
		return product;
	}

	@Override
	public List<SalesRecord> getProductInfo2(int salesidd) throws DemoException {
		List<SalesRecord> salrec = null;
		try {
			List<Integer> baridList = new ArrayList<Integer>();
			Query q11 = null;
			q11 = entitymanager
					.createQuery("from SalesRecord where sales_id=?");
			q11.setParameter(1, salesidd);
			salrec = (List<SalesRecord>) q11.getResultList();
			logger.info("~~~size~~~" + salrec.size());

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return salrec;
	}

	@Override
	public List<I0019> getbatchdemo(Integer j) throws DemoException {
		List<I0019> batches = null;
		try {

			Query q7 = null;
			q7 = entitymanager.createQuery("from I0019 where bar_code_ID=?");
			q7.setParameter(1, j);
			batches = (List<I0019>) q7.getResultList();
			logger.info("~~~size of batches~~~~~~~~~" + batches.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return batches;
	}

	@Override
	public List<Integer> getproductsname() throws DemoException {
		List<Integer> batches1 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q8 = null;
			q8 = entitymanager
					.createQuery("select batch_ID from I0018 where status='insert' and client_ID=? ");
			batches1 = (List<Integer>) q8.getResultList();
			q8.setParameter(1, clientID);
			logger.info("~~~size of batches~~~~~~~~~" + batches1.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return batches1;
	}

	@Transactional(value = "transactionManager")
	public List<I0018> getBatchInfo(String pName) throws DemoException {
		List<I0018> batch = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		
		Query q = null;
		try {
			q = entitymanager
					.createQuery("from I0018 where productName=? and status=?  and client_ID=? ");
			logger.info("1");
			q.setParameter(1, pName);
			q.setParameter(2, "insert");
			q.setParameter(3, clientID);
			batch = (List<I0018>) q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return batch;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> getBarcodeInfo(int batchId) throws DemoException {
		Query q = null;
		List<I0019> bar = null;
		try {
			q = entitymanager
					.createQuery("from I0019 where batch_ID=? and status=?");
			q.setParameter(1, batchId);
			q.setParameter(2, "barcode genterated");
			bar = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return bar;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> getBarcodeInfo1(int batchId) throws DemoException {
		Query q = null;
		List<I0019> bar = null;
		try {
			q = entitymanager
					.createQuery("from I0019 where batch_ID=? and dueDate<=CURDATE()");
			q.setParameter(1, batchId);
			bar = (List<I0019>) q.getResultList();
			logger.info("Size>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<--------------------->"
					+ bar.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return bar;
	}

	@Transactional(value = "transactionManager")
	public List<Date> getBarcodeInfo11(int batchId) throws DemoException {
		Query q = null;
		List<Date> bar = null;
		try {
			q = entitymanager
					.createQuery("select distinct dueDate from I0019 where batch_ID=? and dueDate<=CURDATE()");
			q.setParameter(1, batchId);
			bar = q.getResultList();
			logger.info("Size>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<--------------------->"
					+ bar.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return bar;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> getBarcodeInfo2(int batchId, Date d)
			throws DemoException {
		Query q = null;
		List<I0019> bar = null;
		try {
			q = entitymanager
					.createQuery("from I0019 where batch_ID=? and dueDate=? and status!='barcode genterated'");
			q.setParameter(1, batchId);
			q.setParameter(2, d);
			bar = q.getResultList();
			logger.info("Size>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<__________>"
					+ bar.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return bar;
	}

	@Transactional(value = "transactionManager")
	public String outofStockz(int batchId) throws DemoException {
		Query q = null;
		Query q1 = null;
		List<I0019> bar = null;
		List<I0019> bar2 = null;
		String countzz = "";
		try {
			q = entitymanager
					.createQuery("from I0019 where batch_ID=? and status='barcode genterated'");
			q.setParameter(1, batchId);
			bar = q.getResultList();
			logger.info("Size>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<__________>"
					+ bar.size());
			int count1 = 0;
			int count2 = 0;
			int countz = 0;
			count1 = bar.size() - 1;
			q1 = entitymanager
					.createQuery("from I0019 where batch_ID=? and roll_status='Sold'");
			q1.setParameter(1, batchId);
			bar2 = q1.getResultList();
			count2 = bar2.size();
			countz = count1 - count2;
			PurchaseOrder po = new PurchaseOrder();
			po.setCounter(countz);
			logger.info("Size>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<__________>"
					+ bar2.size());
			countzz = Integer.toString(countz);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return countzz;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> outofStock(int batchId) throws DemoException {
		Query q = null;
		List<I0019> bar = null;
		try {
			q = entitymanager
					.createQuery("from I0019 where batch_ID=? and status='barcode genterated'");
			q.setParameter(1, batchId);
			bar = q.getResultList();
			logger.info("Size>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<__________>"
					+ bar.size());
			q = entitymanager
					.createQuery("from I0019 where batch_ID=? and roll_status='Sold'");
			q.setParameter(1, batchId);
			bar = q.getResultList();
			logger.info("Size>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<__________>"
					+ bar.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return bar;
	}

	@Transactional(value = "transactionManager")
	public StockView getStockData(int batchID) throws DemoException {
		Query q = null;
		List<I0019> bar = null;
		try {

			q = entitymanager
					.createQuery("from I0019 where batch_ID=? and status=?");
			q.setParameter(1, batchID);
			q.setParameter(2, "barcode genterated");
			bar = q.getResultList();
			if (bar.size() > 0) {
				StockView st = new StockView();
				st.setStockinDate(""
						+ bar.get(0).getI0018().getI0017().getStockInDate());
				st.setProductname(bar.get(0).getI0018().getProductName());
				st.setFirmName(bar.get(0).getI0018().getI0017().getI0015()
						.getI0016s().get(0).getI0031().getI0025()
						.getVendorPhoneNumber());
				st.setBatchName(bar.get(0).getI0018().getBatchName());
				st.setBuyingPrice(bar.get(0).getI0018().getI0017().getI0015()
						.getI0016s().get(0).getI0031().getI0001()
						.getAutualPrice());
				st.setUnitprice(""
						+ bar.get(0).getI0018().getI0017().getI0015()
								.getI0016s().get(0).getI0031().getI0001()
								.getSellingPrice());
				st.setQuantity(bar.size());
				st.setStocklimit(bar.get(0).getI0018().getI0017().getI0015()
						.getI0016s().get(0).getI0031().getI0001()
						.getProductLimit());
				return st;
			} else {
				return null;
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return null;
		} finally {

		}
	}

	@Transactional(value = "transactionManager")
	public ProductRegister getProductData(int batchID) throws DemoException {
		Query q = null;
		List<I0019> bar = null;
		try {
			logger.info("inside getProductData");
			q = entitymanager
					.createQuery("from I0019 where batch_ID=? and status=?");
			q.setParameter(1, batchID);
			q.setParameter(2, "barcode genterated");
			bar = q.getResultList();
			logger.info("data" + bar.size());
			if (bar.size() > 0) {
				ProductRegister prdct = new ProductRegister();
				prdct.setExpired_date(bar.get(0).getI0018().getI0017()
						.getStockInDate());
				prdct.setProductName(bar.get(0).getI0018().getProductName());
				prdct.setVendor(bar.get(0).getI0018().getI0017().getI0015()
						.getI0016s().get(0).getI0031().getI0025()
						.getVendorPhoneNumber());
				prdct.setBatch(bar.get(0).getI0018().getBatchName());
				prdct.setAutual_price(bar.get(0).getI0018().getI0017()
						.getI0015().getI0016s().get(0).getI0031().getI0001()
						.getAutualPrice());
				prdct.setMarket_price(bar.get(0).getI0018().getI0017()
						.getI0015().getI0016s().get(0).getI0031().getI0001()
						.getMarketPrice());
				prdct.setQuantity(bar.size());

				return prdct;
			} else {
				return null;
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return null;
		} finally {

		}
	}

	@Transactional(value = "transactionManager")
	public ProductRegister getProductData1(int batchID, Date d)
			throws DemoException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Query q = null;
		List<I0019> bar = null;
		try {
			logger.info("inside getProductData");
			q = entitymanager
					.createQuery("from I0019 where batch_ID=? and status=? and dueDate=?");
			q.setParameter(1, batchID);
			q.setParameter(2, "barcode genterated");
			q.setParameter(3, d);
			bar = q.getResultList();
			logger.info("data" + bar.size());
			if (bar.size() > 0) {
				ProductRegister prdct = new ProductRegister();
				prdct.setExpired_date(PurchaseOrderFromMB
						.sampleBeforeOneWeak(bar.get(0).getDueDate()));
				prdct.setStockinDate(df.format(PurchaseOrderFromMB
						.sampleBeforeOneWeak(bar.get(0).getDueDate())));
				prdct.setProductName(bar.get(0).getI0018().getProductName());
				prdct.setVendor(bar.get(0).getI0018().getI0017().getI0015()
						.getI0016s().get(0).getI0031().getI0025()
						.getVendorPhoneNumber());
				prdct.setBatch(bar.get(0).getI0018().getBatchName());
				prdct.setAutual_price(bar.get(0).getI0018().getI0017()
						.getI0015().getI0016s().get(0).getI0031().getI0001()
						.getAutualPrice());
				prdct.setMarket_price(bar.get(0).getI0018().getI0017()
						.getI0015().getI0016s().get(0).getI0031().getI0001()
						.getMarketPrice());
				prdct.setQuantity(bar.size());

				return prdct;
			} else {
				return null;
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return null;
		} finally {

		}
	}

	@Transactional(value = "transactionManager")
	public List<I0017> getStock(int id, Date startDate, Date endDate)
			throws DemoException {
		Query q = null;
		List<I0017> stock = null;
		try {
			q = entitymanager
					.createQuery("from I0017 where stockInDate between ? and ? and stock_ID=?");
			q.setParameter(1, startDate);
			q.setParameter(2, endDate);
			q.setParameter(3, id);
			stock = q.getResultList();
			logger.info("Size of Stock" + stock.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return stock;
	}

	@Transactional(value = "transactionManager")
	public List<I0017> getStock1(int id) throws DemoException {
		Query q = null;
		List<I0017> stock = null;
		try {
			q = entitymanager.createQuery("from I0017 where stock_ID=?");
			q.setParameter(1, id);
			stock = q.getResultList();
			logger.info("Size of Stock" + stock.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return stock;
	}

	@Transactional(value = "transactionManager")
	public List<String> categorylist1(List<String> categorytype)
			throws DemoException {
		logger.info("inside dao->>>");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			q = entitymanager.createQuery("select categoryType from I0005 where client_ID=?");
			q.setParameter(1, clientID);
			categorytype = q.getResultList();
			logger.info("category type---->>" + categorytype);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return categorytype;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0032> customerNameChange(String s) throws DemoException {
		ArrayList<I0032> result = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			logger.info("in dao -->>");
			q = entitymanager
					.createQuery("from I0032 where customerName=? and status='in' and client_ID=?");
			q.setParameter(1, s);
			q.setParameter(2, clientID);
			result = (ArrayList<I0032>) q.getResultList();
			if (result.size() < 0) {
				throw new DemoException("* no data found");
			} else {
				logger.info("in dao -->> else "
						+ result.get(0).getPhoneNumber());

			}
		} finally {
			q = null;
		}
		return result;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<I0032> customerNameChange1(String s) throws DemoException {
		ArrayList<I0032> result = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			q = entitymanager
					.createQuery("select customerName from I0032 where freelancerName=? and status='in' and client_ID=?");
			q.setParameter(1, s);
			q.setParameter(2, clientID);
			result = (ArrayList<I0032>) q.getResultList();
			if (result.size() < 0) {
				throw new DemoException("* no data found");
			}
		} finally {
			q = null;
		}
		return result;
	}

	@Transactional(value = "transactionManager")
	public String salesOrder3(PurchaseOrder purchaseOrder) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.info("order numbber3:::" + purchaseOrder.getOrderNumber());

		logger.info("inside dao salesOrder3::::");
		logger.info("custumer name3::::" + purchaseOrder.getCustomername());
		logger.info("sales order date::::" + purchaseOrder.getSalesorderdate());
		logger.info("ship address::::" + purchaseOrder.getShipingaddress());
		logger.info("phone number::::" + purchaseOrder.getPhonenumber());
		logger.info("email::::" + purchaseOrder.getEmail());
		logger.info("total number of count::::"
				+ purchaseOrder.getTotalnumberofcount());
		logger.info("total number of count::::"
				+ purchaseOrder.getTotalnumberofcount1());

		logger.info("delivery date::::" + purchaseOrder.getDeliverydate());
		logger.info("note::::" + purchaseOrder.getNote());
		logger.info("shipp charge::::" + purchaseOrder.getShipping_charge());
		logger.info("shipp charge::::" + purchaseOrder.getShipping_charge1());
		logger.info("cross total3::::" + purchaseOrder.getCrosstotal());

		logger.info("cross 2::::" + purchaseOrder.getCrosstotal1());
		logger.info("count1 ----------->" + purchaseOrder.getCount());

		I0021 salesOrder = new I0021();
		logger.info("1");
		salesOrder.setCustomerName(purchaseOrder.getCustomername());
		salesOrder.setSalesOrderDate(purchaseOrder.getSalesorderdate());
		salesOrder.setShipingAddress(purchaseOrder.getShipingaddress());
		salesOrder.setPhoneNumber(purchaseOrder.getPhonenumber());
		salesOrder.setEMail(purchaseOrder.getEmail());
		salesOrder.setTotalNumberOfCount("0");
		salesOrder.setCrossTotal("0");
		salesOrder.setShippingCharge("0");
		salesOrder.setSalesOrderNumber(purchaseOrder.getOrderNumber());
		salesOrder.setDeliveryDate(purchaseOrder.getDeliverydate());
		salesOrder.setNote(purchaseOrder.getNote());
		salesOrder.setStatus2("pending");
		salesOrder.setStatus("insert");
		salesOrder.setSales_ID(purchaseOrder.getCount());
		salesOrder.setI0032(entitymanager.find(I0032.class,
				purchaseOrder.getDummyId()));
		logger.info("2");
		salesOrder.setCommissionAmount("0");
		salesOrder.setCrossCommision("0");
		salesOrder.setClient_ID(clientID);
		salesOrder.setDueDate(purchaseOrder.getDueDate());
		entitymanager.persist(salesOrder);

		return null;
	}

	List<PurchaseReturn> list1 = null;

	@Transactional(value = "transactionManager")
	public String viewPurchaseReturn(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.info("-->>in dao");
		Query q = null;
		Query q1 = null;
		List<PurchaseReturn> list1 = null;
		List<PurchaseOrder> mb2 = new ArrayList<PurchaseOrder>();
		PurchaseOrder p1 = new PurchaseOrder();
		try {

			q1 = entitymanager.createQuery("from PurchaseReturn where client_ID=?");
			q1.setParameter(1, clientID);
			list1 = (ArrayList) q1.getResultList();
			logger.info("-->>list size dao " + list1.size());
			if (list1.size() > 0) {
				logger.info("Inside if");
				for (int i = 0; i < list1.size(); i++) {
					logger.info("inside for loop - "
							+ list1.get(i).getI0015().getTemOrderNumber());
					q1 = null;
					q1 = entitymanager.createQuery("from I0016 where ordernumber=? and client_ID=?");
					q1.setParameter(1, list1.get(i).getI0015().getTemOrderNumber());
					q1.setParameter(2, clientID);
					List<I0016> i0016 = (List<I0016>) q1.getResultList();
					logger.info("i0016 size - " + i0016.size());
					PurchaseOrder po = new PurchaseOrder();
					po.setVendorPhoneNumber(i0016.get(0).getI0031().getI0025().getVendorPhoneNumber());
					po.setOrderDate(list1.get(i).getI0015().getOrderDate());
					po.setOrderNumber(list1.get(i).getPurchaseOrderNumber());
					mb2.add(po);
					purchaseOrder.setDomain2(mb2);
					logger.info("Domain2 size"+ purchaseOrder.getDomain2().size());

				}
			}
			logger.info("inside dao end");
			logger.info("Domain2 size" + purchaseOrder.getDomain2().size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";

	}

	@Transactional(value = "transactionManager")
	public String viewPurchaseReturnDetail(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.info("-->>in dao");
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		Query q1 = null;
		List<PurchaseReturn> list1 = null;
		List<PurchaseOrder> mb2 = new ArrayList<PurchaseOrder>();
		PurchaseOrder p1 = new PurchaseOrder();
		try {

			q1 = entitymanager.createQuery("from PurchaseReturn where purchaseOrderNumber=? and client_ID=?");
			q1.setParameter(1, purchaseOrder.getOrderNumber());
			q1.setParameter(2, clientID);

			list1 = (ArrayList) q1.getResultList();
			logger.info("-->>list size " + list1.size());
			if (list1.size() > 0) {
				for (int i = 0; i < list1.size(); i++) {
					logger.info("-->> order no "
							+ list1.get(i).getPurchaseOrderNumber());
					logger.info("-->> order date "
							+ list1.get(i).getI0015().getOrderDate());
					logger.info("Purchase id"
							+ list1.get(i).getI0015().getPurchase_ID());
					logger.info("getBar_code_ID"
							+ list1.get(i).getI0019().getBar_code_ID());
					Query q11 = entitymanager.createQuery("from I0019 where bar_code_ID=? and client_ID=?");
					q11.setParameter(1, list1.get(i).getI0019().getBar_code_ID());
					q11.setParameter(2, clientID);
					List<I0019> li = (List<I0019>) q11.getResultList();
					logger.info("List Size" + li.size());
					if (li.size() > 0) {

						PurchaseOrder po = new PurchaseOrder();
						po.setVendorPhoneNumber(list1.get(i).getI0015()
								.getI0016s().get(0).getI0031().getI0025()
								.getVendorPhoneNumber());
						po.setOrderDate(list1.get(i).getI0015().getOrderDate());
						po.setOrderNumber(list1.get(i).getPurchaseOrderNumber());
						po.setReturnDate(list1.get(i).getReturnDate());
						po.setProductName(list1.get(i).getProductName());
						po.setRollID(li.get(0).getRoll_ID());

						po.setReturnQuan1(list1.get(i).getNormalReturn());
						po.setReturnQuan2(list1.get(i).getDamageReturn());
						po.setTotalQuan1(list1.get(i).getI0015().getI0016s()
								.get(0).getQuantity());

						mb2.add(po);
						purchaseOrder.setDomain2(mb2);

					}
				}
			}
		} catch (Exception e) {

		}
		return "";

	}

	/* Udhaya */
	@Transactional(value = "transactionManager")
	public String salesReturnForm2(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("from I0021 where salesOrderNumber=? and status=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, "Delivered");
		q.setParameter(3, clientID);
		logger.info("::salesReturnForm2::");
		List<I0021> result = (List<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		if (result.size() > 0) {
			logger.info("1");
			pkid = result.get(i).getSales_ID();
			logger.info("2");
			Query q1 = null;

			I0021 sale = entitymanager.find(I0021.class, pkid);
			sale.setStatus("Return");
			sale.setNorReturnReason(purchaseOrder.getReason());
			sale.setNorDueDate(purchaseOrder.getReturnDate());
			logger.info("return date---->>>" + sale.getNorDueDate());
			entitymanager.merge(sale);

			q1 = entitymanager.createQuery("from I0019 where sales_ID=? and client_ID=?");
			logger.info("3");
			q1.setParameter(1, pkid);
			q1.setParameter(2, clientID);
			logger.info("4");
			List<I0019> resul = (List<I0019>) q1.getResultList();
			int barid = 0;
			if (resul.size() > 0) {

				for (I0019 re : resul) {
					int id = re.getI0018().getBatch_ID();
					logger.info("int id::::::::::::::" + id);
					barid = re.getBar_code_ID();
					I0019 barcode = entitymanager.find(I0019.class, barid);
					barcode.setStatus("barcode genterated");
					entitymanager.merge(barcode);
					I0018 i0018 = entitymanager.find(I0018.class, id);
					i0018.setStatus("insert");
					entitymanager.merge(i0018);

				}

			}
			logger.info("5");
			logger.info("---------" + resul.get(i).getI0021().getCustomerName());
			purchaseOrder.setResul(resul);
			logger.info("6");
			// resul.get(i).getI0018().getI0017().getI0015().get

		} else {
			throw new DemoException(
					"please check the Sales order Number:::::::::::::");
		}
		return null;

	}

	@Transactional(value = "transactionManager")
	public String salesReturnForm3(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		logger.info("purorder number------------------->"
				+ purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("from I0021 where salesOrderNumber=? and status=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, "Delivered");
		q.setParameter(3, clientID);
		logger.info("::salesReturnForm2::");
		List<I0021> result = (List<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		String totalamount = "0";
		if (result.size() > 0) {
			totalamount = result.get(i).getCrossTotal();
			logger.info("totalamount--------------" + totalamount);
			logger.info("1");
			pkid = result.get(i).getSales_ID();
			logger.info("2");
			Query q1 = null;
			Query q3 = null;
			logger.info("--------------------product Name------------------"+ purchaseOrder.getProduct_name());
			q3 = entitymanager.createQuery("from I0018 where productName=? and client_ID=? ");
			logger.info("3");
			q3.setParameter(1, purchaseOrder.getProduct_name());
			q3.setParameter(2, clientID);
			logger.info("4");
			List<I0018> resu = (List<I0018>) q3.getResultList();
			if (resu.size() > 0) {
				logger.info("resu.size-----------------------" + resu.size());
				logger.info("productid-----------------------"
						+ resu.get(0).getBatch_ID());
			}
			q1 = entitymanager
					.createQuery("from I0019 where sales_ID=? and status='Delivered' and batch_ID=? and client_ID=?");
			logger.info("3");
			q1.setParameter(1, pkid);
			q1.setParameter(2, resu.get(0).getBatch_ID());
			q1.setParameter(3, clientID);
			logger.info("4");
			List<I0019> resul = (List<I0019>) q1.getResultList();
			int barid = 0;
			if (resul.size() > 0) {

				logger.info("resul.size-----------------------" + resul.size());
				logger.info("productid-----------------------"
						+ resul.get(0).getBar_code_ID());

				int id = resul.get(0).getI0018().getBatch_ID();
				logger.info("int id::::::::::::::" + id);
				barid = resul.get(0).getBar_code_ID();
				logger.info("BARIDDDDDDDDDD" + barid);
				I0019 barcode = entitymanager.find(I0019.class, barid);
				barcode.setStatus("barcode genterated");
				entitymanager.merge(barcode);
				I0018 i0018 = entitymanager.find(I0018.class, id);
				i0018.setStatus("insert");
				entitymanager.merge(i0018);
				I0021 sale = entitymanager.find(I0021.class, pkid);
				sale.setCrossTotal(""+ (new BigDecimal(totalamount).subtract(new BigDecimal(resul.get(0).getI0018().getUnitPrice()))));
				sale.setNorReturnReason(purchaseOrder.getReason());
				logger.info("reason------>>>>" + sale.getNorReturnReason());
				sale.setNorDueDate(purchaseOrder.getReturnDate());
				logger.info("return date---->>>" + sale.getNorDueDate());
				entitymanager.merge(sale);
				Query q2 = null;
				q2 = entitymanager
						.createQuery("from I0019 where sales_ID=? and status='Delivered' and client_ID=?");
				q1.setParameter(1, pkid);
				q1.setParameter(2, clientID);
				logger.info("44");
				List<I0019> result10 = (List<I0019>) q1.getResultList();
				if (result.size() == 0) {

				}
				logger.info("5");
				logger.info("---------"+ resul.get(i).getI0021().getCustomerName());
				purchaseOrder.setResul(resul);
			} else {
				I0021 sale = entitymanager.find(I0021.class, pkid);
				sale.setStatus("Return");
				sale.setNorReturnReason(purchaseOrder.getReason());
				sale.setNorDueDate(purchaseOrder.getReturnDate());
				logger.info("return date---->>>" + sale.getNorDueDate());

				entitymanager.merge(sale);

			}

			logger.info("6");
			// resul.get(i).getI0018().getI0017().getI0015().get
		} else {
			throw new DemoException(
					"please check the Sales order Number:::::::::::::");
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String salesReturnFor(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		logger.info("purorder number------------------->"
				+ purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("from I0021 where salesOrderNumber=? and status=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, "Delivered");
		q.setParameter(3, clientID);
		logger.info("::salesReturnForm2::");
		List<I0021> result = (List<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		String totalamount = "0";
		if (result.size() > 0) {
			totalamount = result.get(i).getCrossTotal();
			logger.info("totalamount--------------" + totalamount);
			logger.info("1");
			pkid = result.get(i).getSales_ID();
			logger.info("2");
			Query q1 = null;
			Query q3 = null;
			logger.info("--------------------product Name------------------"
					+ purchaseOrder.getProduct_name());
			q3 = entitymanager.createQuery("from I0018 where productName=? and client_ID=?");
			q3.setParameter(1, purchaseOrder.getProduct_name());
			q3.setParameter(2, clientID);
			logger.info("4");
			List<I0018> resu = (List<I0018>) q3.getResultList();
			if (resu.size() > 0) {
				logger.info("resu.size-----------------------" + resu.size());
				logger.info("productid-----------------------"
						+ resu.get(0).getBatch_ID());
			}
			q1 = entitymanager
					.createQuery("from I0019 where sales_ID=? and status='Delivered' and batch_ID=? and client_ID=?");
			q1.setParameter(1, pkid);
			q1.setParameter(2, resu.get(0).getBatch_ID());
			q1.setParameter(3, clientID);
			logger.info("4");
			List<I0019> resul = (List<I0019>) q1.getResultList();
			int barid = 0;
			if (resul.size() > 0) {

				logger.info("resul.size-----------------------" + resul.size());
				logger.info("productid-----------------------"+ resul.get(0).getBar_code_ID());

				int id = resul.get(0).getI0018().getBatch_ID();
				logger.info("int id::::::::::::::" + id);
				barid = resul.get(0).getBar_code_ID();
				logger.info("BARIDDDDDDDDDD" + barid);
				I0019 barcode = entitymanager.find(I0019.class, barid);
				barcode.setStatus("damaged");
				entitymanager.merge(barcode);
				I0018 i0018 = entitymanager.find(I0018.class, id);
				i0018.setStatus("insert");
				entitymanager.merge(i0018);
				I0021 sale = entitymanager.find(I0021.class, pkid);
				sale.setCrossTotal(""+ (new BigDecimal(totalamount).subtract(new BigDecimal(
								resul.get(0).getI0018().getUnitPrice()))));
				sale.setDamageReturnReason(purchaseOrder.getReason());
				sale.setDamDueDate(purchaseOrder.getReturnDate());
				entitymanager.merge(sale);
				Query q2 = null;
				q2 = entitymanager
						.createQuery("from I0019 where sales_ID=? and status='Delivered' and client_ID=?");
				q1.setParameter(1, pkid);
				q1.setParameter(2, clientID);
				logger.info("44");
				List<I0019> result10 = (List<I0019>) q1.getResultList();
				if (result.size() == 0) {

				}
				logger.info("5");
				logger.info("---------"
						+ resul.get(i).getI0021().getCustomerName());
				purchaseOrder.setResul(resul);
			} else {
				I0021 sale = entitymanager.find(I0021.class, pkid);
				sale.setStatus("Return");
				sale.setDamageReturnReason(purchaseOrder.getReason());
				entitymanager.merge(sale);
			}

			logger.info("6");
			// resul.get(i).getI0018().getI0017().getI0015().get
		} else {
			throw new DemoException(
					"please check the Sales order Number:::::::::::::");
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String salesReturnForm5(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("from I0021 where salesOrderNumber=? and status=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, "Delivered");
		q.setParameter(3, clientID);
		logger.info("::salesReturnForm5::");
		List<I0021> result = (List<I0021>) q.getResultList();
		int pkid = 0;
		int i = 0;
		if (result.size() > 0) {
			logger.info("1");
			pkid = result.get(i).getSales_ID();
			logger.info("2");
			Query q1 = null;

			I0021 sale = entitymanager.find(I0021.class, pkid);
			sale.setStatus("Return");
			sale.setDamageReturnReason(purchaseOrder.getReason());
			sale.setDamDueDate(purchaseOrder.getReturnDate());
			entitymanager.merge(sale);

			q1 = entitymanager.createQuery("from I0019 where sales_ID=? and client_ID=?");
			q1.setParameter(1, pkid);
			q1.setParameter(2, clientID);
			logger.info("4");
			List<I0019> resul = (List<I0019>) q1.getResultList();
			int barid = 0;
			if (resul.size() > 0) {

				for (I0019 re : resul) {
					barid = re.getBar_code_ID();
					I0019 barcode = entitymanager.find(I0019.class, barid);
					barcode.setStatus("damaged");
					entitymanager.merge(barcode);
				}

			}
			logger.info("5");
			logger.info("---------" + resul.get(i).getI0021().getCustomerName());
			purchaseOrder.setResul(resul);
			logger.info("6");
			// resul.get(i).getI0018().getI0017().getI0015().get

		} else {
			throw new DemoException(
					"please check the Sales order Number:::::::::::::");
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public List<I0032> getCategoryData(String category) throws DemoException {
		List<I0032> categoryList = null;
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");	
		try {
			q = entitymanager
					.createQuery("from I0032 where freelancerName=? and status='in' and client_ID=?");
			q.setParameter(1, category);
			q.setParameter(2, clientID);
			categoryList = q.getResultList();
			logger.info("size>>>>>>>>>>>>>" + categoryList.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return categoryList;
	}

	@Transactional(value = "transactionManager")
	public List<I0021> getSalesData(Date fDate, Date tDate, int bId)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0021> salesList = null;
		Query q = null;
		try {
			logger.info("From Date" + fDate + "Todate" + tDate + "bid" + bId);
			logger.info("1");
			q = entitymanager
					.createQuery("from I0021 where salesOrderDate between ? and ? and buyer_ID=? and client_ID=?");
			logger.info("2");
			q.setParameter(1, fDate);
			logger.info("3");
			q.setParameter(2, tDate);
			logger.info("4");
			q.setParameter(3, bId);
			q.setParameter(4, clientID);
			logger.info("5");
			salesList = q.getResultList();
			logger.info("--------------->" + salesList.size());
			logger.info("6");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return salesList;
	}

	@Transactional(value = "transactionManager")
	public List<I0021> getSalesData1(Date fDate, Date tDate)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0021> salesList = null;
		Query q = null;
		try {
			logger.info("From Date" + fDate + "Todate" + tDate);
			logger.info("1");
			q = entitymanager
					.createQuery("from I0021 where salesOrderDate between ? and ? and client_ID=?");
			q.setParameter(1, fDate);
			q.setParameter(2, tDate);
			q.setParameter(3, clientID);
			salesList = q.getResultList();
			logger.info("--------------->" + salesList.size());
			logger.info("6");
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return salesList;
	}

	@Transactional(value = "transactionManager")
	public List<SalesRecord> getSalesQuantityData(int sId) throws DemoException {
		List<SalesRecord> salesList = null;
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("---inside dao===");
			q = entitymanager
					.createQuery("from SalesRecord where sales_id=? and (soldStatus='solded' or soldStatus='delivered') and client_ID=?");
			q.setParameter(1, sId);
			q.setParameter(2, clientID);
			salesList = q.getResultList();
			logger.info("==list===" + salesList.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return salesList;
	}

	public List<I0021> getSalesDatafromNumber(String saleNo)
			throws DemoException {
		List<I0021> salesList = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			q = entitymanager
					.createQuery("from I0021 where salesOrderNumber=? and (status='insert' or status='Delivered') and client_ID=?");
			q.setParameter(1, saleNo);
			q.setParameter(2, clientID);
			salesList = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return salesList;
	}

	@Transactional(value = "transactionManager")
	public String commisionUpdate(int sid, Commission c) throws DemoException {
		List<I0018> batchids = null;
		List<I0019> baridd = null;
		List<SalesRecord> rec = null;
		Query q1 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q1 = entitymanager.createQuery("from I0018 where productName=? and client_ID=?");
			q1.setParameter(1, c.getProductname());
			q1.setParameter(2, clientID);
			batchids = q1.getResultList();
			logger.info("size===" + batchids.size());
			if (batchids.size() > 0) {
				int batidd = 0;
				batidd = batchids.get(0).getBatch_ID();
				Query q11 = null;
				q11 = entitymanager.createQuery("from I0019 where batch_ID=? and client_ID=?");
				q11.setParameter(1, batidd);
				q11.setParameter(2, clientID);
				baridd = q11.getResultList();
				logger.info("==size ---of--baridd==" + baridd.size());
				if (baridd.size() > 0) {
					int baridds = 0;
					baridds = baridd.get(0).getBar_code_ID();
					Query q20 = null;
					q20 = entitymanager
							.createQuery("from SalesRecord where soldQuantity=? and bar_code_id=? and sales_id=? and client_ID=?");
					q20.setParameter(1, "" + c.getTotlquan());
					q20.setParameter(2, baridds);
					q20.setParameter(3, sid);
					q20.setParameter(4, clientID);
					rec = q20.getResultList();
					logger.info("==result of rec===" + rec.size());
					int salesrecid = 0;
					salesrecid = rec.get(0).getSalesRecordId();
					SalesRecord sales1 = entitymanager.find(SalesRecord.class,
							salesrecid);
					logger.info("==commission amt====" + c.getCmst()
							+ "~~~~~~~" + c.getTotcms());
					sales1.setCommAmt("" + (c.getCmst()));
					sales1.setTotcmsion("" + c.getTotcms());
					entitymanager.merge(sales1);

				}

			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	public String findCashBook(PurchaseOrder purchaseOrder)
			throws DemoException {
		Query q = null, q1 = null,q2 = null;
		ArrayList<I0015> purchase = null;
		ArrayList<I0016> purchase1 = null;
		ArrayList<I0021> sales = null;
		ArrayList<Transaction> transcation = null;
		try {
			Query c = null;
			Query ij = null;
			List<I0023> cashMode = null;
			List<I0022> list22 = null;
			c = entitymanager
					.createQuery("from I0023 where paymentType='Cash' and status='paid' and client_ID=? ");
			c.setParameter(1, purchaseOrder.getClientID());
			cashMode = c.getResultList();
			logger.info("cashmode size>>>>>" + cashMode.size());
			int purId = 0;
			int invId = 0;
			if (cashMode.size() > 0) {
				logger.info("inside");
				for (int ii = 0; ii < cashMode.size(); ii++) {
					invId = cashMode.get(ii).getI0022().getInvoice_ID();
					logger.info("invoice id - " + invId);
					ij = entitymanager
							.createQuery("from I0022 where invoice_ID=? and status='purchase Invoice' and client_ID=?");
					ij.setParameter(1, invId);
					ij.setParameter(2, purchaseOrder.getClientID());
					list22 = ij.getResultList();
					logger.info("list22 size>>>>>" + list22.size());
					if (list22.size() > 0) {
						purId = list22.get(0).getI0015().getPurchase_ID();
						logger.info("-->> i0015 id " + purId);
						logger.info("-->> i0022 id " + invId);
						logger.info("-->> amount paid "
								+ cashMode.get(ii).getPaidAmount());

						q = entitymanager
								.createQuery("from I0015 where (status='insert' or status='delivered') and purchase_ID=? and client_ID=? ");
						q.setParameter(1, purId);
						q.setParameter(2, purchaseOrder.getClientID());
						purchase = (ArrayList<I0015>) q.getResultList();
						if (purchase.size() > 0) {
							logger.info("---------------if1---------------"
									+ purchase.size());

							int i = 0;
							for (I0015 pu : purchase) {

								logger.info("1");
								PurchaseOrder purchaseOrder2 = new PurchaseOrder();
								logger.info("2");
								purchaseOrder2.setsNo(""
										+ (purchaseOrder.getCounter() + 1));
								purchaseOrder.setCounter(purchaseOrder
										.getCounter() + 1);
								purchaseOrder2.setParticulars("Purchase");
								logger.info("3");
								q1 = entitymanager
										.createQuery("from I0016 where purchase_ID=?");
								logger.info("1");
								q1.setParameter(1, purchase.get(i)
										.getPurchase_ID());
								logger.info("1");
								purchase1 = (ArrayList<I0016>) q1
										.getResultList();
								logger.info("1");
								purchaseOrder2.setClientName(purchase1.get(0)
										.getI0031().getI0025()
										.getVendorPhoneNumber());
								logger.info("1");

								purchaseOrder2.setDebit(cashMode.get(ii)
										.getPayableAmount());
								purchaseOrder2.setCredit("");
								purchaseOrder2.setOrderDate(purchaseOrder
										.getTargentDate());
								purchaseOrder2.setBatch(new SimpleDateFormat(
										"dd/MM/yyyy").format(purchase1.get(0)
										.getOrderDate()));
								purchaseOrder.getCashBookList().add(
										purchaseOrder2);
							}
						}
					}
				}
			}
			logger.info("purchase size "
					+ purchaseOrder.getCashBookList().size());
			c = entitymanager
					.createQuery("from I0023 where paymentType='Cash' and status='paid' and client_ID=?");
			c.setParameter(1, purchaseOrder.getClientID());
			List<I0021> list21 = null;
			cashMode = c.getResultList();
			logger.info("23 size " + cashMode.size());
			int salId = 0;
			if (cashMode.size() > 0) {
				for (int ii = 0; ii < cashMode.size(); ii++) {
					invId = cashMode.get(ii).getI0022().getInvoice_ID();
					ij = entitymanager
							.createQuery("from I0022 where invoice_ID=? and status='Sales Invoice' and client_ID=?");
					ij.setParameter(1, invId);
					ij.setParameter(2, purchaseOrder.getClientID());
					list22 = ij.getResultList();
					if (list22.size() > 0) {
						salId = list22.get(0).getI0021().getSales_ID();
						Query qq = null;
						qq = entitymanager
								.createQuery("from I0021 where (sales_ID=?) and (status='Delivered' or status='insert') and client_ID=? ");
						qq.setParameter(1, salId);
						qq.setParameter(2, purchaseOrder.getClientID());
						sales = (ArrayList<I0021>) qq.getResultList();
						if (sales.size() > 0) {
							for (I0021 sa : sales) {
								PurchaseOrder purchaseOrder2 = new PurchaseOrder();
								purchaseOrder2.setsNo(""
										+ (new BigDecimal(purchaseOrder
												.getCounter()).add(BigDecimal
												.valueOf(1))));
								purchaseOrder.setCounter(purchaseOrder
										.getCounter() + 1);
								purchaseOrder2.setParticulars("Sales");
								purchaseOrder2.setClientName(sa
										.getCustomerName());

								purchaseOrder2.setCredit(cashMode.get(ii)
										.getPayableAmount());
								purchaseOrder2.setDebit("");
								purchaseOrder2.setOrderDate(purchaseOrder
										.getTargentDate());
								purchaseOrder2.setBatch(new SimpleDateFormat(
										"dd/MM/yyyy").format(sa
										.getSalesOrderDate()));
								purchaseOrder.getCashBookList().add(
										purchaseOrder2);
							}
						}

					}
				}
			}
			logger.info("sales size " + purchaseOrder.getCashBookList().size());
			q1 = null;
			q1 = entitymanager
					.createQuery("from I0021 where (natureOfBusiness='Quick sales' and paymentType='Cash') and status='Quick sales' and client_ID=? ");
			q1.setParameter(1, purchaseOrder.getClientID());
			sales = (ArrayList<I0021>) q1.getResultList();
			if (sales.size() > 0) {
				logger.info("list 1 size " + sales.size());
				for (I0021 sa : sales) {
					PurchaseOrder purchaseOrder2 = new PurchaseOrder();
					purchaseOrder2.setsNo(""
							+ (new BigDecimal(purchaseOrder.getCounter())
									.add(BigDecimal.valueOf(1))));
					purchaseOrder.setCounter(purchaseOrder.getCounter() + 1);
					purchaseOrder2.setParticulars("Quick Sales");
					purchaseOrder2.setClientName(sa.getCustomerName());
					purchaseOrder2.setCredit(sa.getCrossTotal());
					purchaseOrder2.setDebit("");
					purchaseOrder2.setOrderDate(purchaseOrder.getTargentDate());
					purchaseOrder2.setBatch(new SimpleDateFormat("dd/MM/yyyy")
							.format(sa.getSalesOrderDate()));
					purchaseOrder.getCashBookList().add(purchaseOrder2);
				}
			}

			logger.info("quick sale size "
					+ purchaseOrder.getCashBookList().size());
			q = null;
			q = entitymanager
					.createQuery("from Transaction where status='inserted' and paymentMode='Cash' and client_ID=?");
			q.setParameter(1, purchaseOrder.getClientID());
			transcation = (ArrayList<Transaction>) q.getResultList();
			if (transcation.size() > 0) {
				logger.info("---------------if3---------------"
						+ transcation.size());
				for (Transaction transaction : transcation) {
					PurchaseOrder purchaseOrder2 = new PurchaseOrder();
					purchaseOrder2
							.setsNo("" + (purchaseOrder.getCounter() + 1));
					purchaseOrder.setCounter(purchaseOrder.getCounter() + 1);
					purchaseOrder2.setParticulars("Transaction");
					purchaseOrder2.setClientName(transaction.getParticulars());
					if (transaction.getTransactionType().equals("Income")) {
						purchaseOrder2.setCredit(transaction.getAmount());
						purchaseOrder2.setDebit("");
					} else {
						purchaseOrder2.setDebit(transaction.getAmount());
						purchaseOrder2.setCredit("");
					}
					purchaseOrder2.setBatch(new SimpleDateFormat("dd/MM/yyyy")
							.format(transaction.getTransactionDate()));
					purchaseOrder2.setOrderDate(purchaseOrder.getTargentDate());
					purchaseOrder.getCashBookList().add(purchaseOrder2);
				}

			}
			logger.info("cash book size - "
					+ purchaseOrder.getCashBookList().size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public List<I0032> getPhone() throws DemoException {
		Query q = null;
		List<I0032> buyer = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager.createQuery("from I0032 where status='in' and client_ID=? ");
			q.setParameter(1, clientID);
			buyer = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			q = null;
		}
		return buyer;
	}

	/* siva 10/4/15 */
	@Transactional(value = "transactionManager")
	public List<String> getSONfordispatch() throws DemoException {
		List<String> sonumber = null;
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager
					.createQuery("select salesOrderNumber from I0021 where status=? and client_ID=?");
			q.setParameter(1, "Delivered");
			q.setParameter(2, clientID);
			sonumber = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return sonumber;
	}

	@Transactional(value = "transactionManager")
	public List<Dispatch> getDispathchfromSales(String soNumber)
			throws DemoException {
		List<Dispatch> dispath = null;
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager
					.createQuery("from Dispatch where salesOrderNumber=? and client_ID=? ");
			q.setParameter(1, soNumber);
			q.setParameter(2, clientID);
			dispath = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return dispath;
	}

	@Transactional(value = "transactionManager")
	public String updateDispatch(int id, String v1, String v2)
			throws DemoException {
		try {
			Dispatch dis = entitymanager.find(Dispatch.class, id);
			dis.setVehicle1(v1);
			dis.setVehicle2(v2);
			entitymanager.merge(dis);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public List<Dispatch> getDispathchfromrefference(String soNumber)
			throws DemoException {
		List<Dispatch> dispath = null;
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager.createQuery("from Dispatch where dispatchno=? and client_ID=?");
			q.setParameter(1, soNumber);
			q.setParameter(2, clientID);
			dispath = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return dispath;
	}

	public List<I0019> getDeliveredStock(int sid) throws DemoException {
		List<I0019> resul = null;
		List<SalesRecord> resul1 = null;
		Query q1 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q1 = entitymanager
					.createQuery("from I0019 where sales_ID=? and status='Delivered' and client_ID=?");
			q1.setParameter(1, sid);
			q1.setParameter(2, clientID);
			resul = (List<I0019>) q1.getResultList();
			logger.info("size of resul");

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return resul;
	}

	public List<SalesRecord> getDeliveredStockz(int sid, PurchaseOrder dom)
			throws DemoException {
		List<I0019> resul = null;
		List<SalesRecord> resul1 = null;
		List<I0001> res = null;
		Query q1 = null;
		Query q11 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q1 = entitymanager
					.createQuery("from SalesRecord where sales_ID=? and soldStatus='delivered' and client_ID=?");
			q1.setParameter(1, sid);
			q1.setParameter(2, clientID);
			// q1.setParameter(2,rollid);
			resul1 = (List<SalesRecord>) q1.getResultList();
			logger.info("==size===>>" + resul1.size());

			String idd, idd1, idd2;
			int barcode = 0;
			logger.info("batch===>>>"
					+ resul1.get(0).getI0019().getI0018().getBatch_ID());
			// int id=res.get(0).getProduct_ID();
			if (resul1.size() > 0) {
				logger.info("==in===dao====");
				logger.info("==idd11==="
						+ resul1.get(0).getI0019().getRoll_ID());
				idd = resul1.get(0).getI0019().getRoll_ID();
				logger.info("roll quntity==="
						+ resul1.get(0).getI0019().getRoll_Qunatity());
				idd1 = resul1.get(0).getI0019().getRoll_Qunatity();
				barcode = resul1.get(0).getI0019().getI0018().getBatch_ID();
				logger.info("==barcode===>>" + barcode);
				q11 = entitymanager.createQuery("from I0018 where batch_ID=? and client_ID=?");
				q11.setParameter(1, barcode);
				q11.setParameter(2, clientID);
				List<I0018> val = (List<I0018>) q11.getResultList();
				logger.info("size" + val.size());
				if (val.size() > 0) {
					String name = val.get(0).getProductName();

					Query q13 = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
					q13.setParameter(1, name);
					q13.setParameter(2, clientID);
					List<I0001> val1 = (List<I0001>) q13.getResultList();
					logger.info("size" + val.size());
					if (val1.size() > 0) {
						String unit11 = val1.get(0).getUnit();
						logger.info("==unit===" + val1.get(0).getUnit());

						dom.setUnit(unit11);

					}
				}
				logger.info("--batch id==" + barcode);

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return resul1;
	}

	@Transactional(value = "transactionManager")
	public List<I0021> getSalesDataForCommision(int bID) throws DemoException {
		List<I0021> sales = null;
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager
					.createQuery("from I0021 where buyer_ID=? and (status='insert' or status='Delivered') and client_ID=?");
			q.setParameter(1, bID);
			q.setParameter(2, clientID);
			sales = q.getResultList();
			logger.info("Size>>>>>>>>>>>>>>>>>>>>> Sales >>>>>>>"
					+ sales.size());
		} catch (Exception e) {

		} finally {

		}
		return sales;
	}

	public List<I0018> getBatchfromStock() throws DemoException {
		List<I0018> bList = null;
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager.createQuery("select distinct i0018 from I0019 where client_ID=?");
			q.setParameter(1, clientID);
			bList = q.getResultList();
			logger.info("Stock-------------------------------->Batch----------------->"
					+ bList);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return bList;
	}

	@Transactional(value = "transactionManager")
	public ProductRegister getProductData2(int batchID) throws DemoException {
		Query q = null;
		List<I0019> bar = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("inside getProductData");
			q = entitymanager.createQuery("from I0019 where batch_ID=? and client_ID=?");
			q.setParameter(1, batchID);
			q.setParameter(2, clientID);
			bar = q.getResultList();
			logger.info("data" + bar.size());
			if (bar.size() > 0) {
				ProductRegister prdct = new ProductRegister();

				prdct.setProductName(bar.get(0).getI0018().getProductName());
				prdct.setVendor(bar.get(0).getI0018().getI0017().getI0015()
						.getI0016s().get(0).getI0031().getI0025()
						.getVendorPhoneNumber());
				prdct.setBatch(bar.get(0).getI0018().getBatchName());
				prdct.setActual1(new BigDecimal(bar.get(0).getI0018()
						.getI0017().getI0015().getI0016s().get(0).getI0031()
						.getI0001().getAutualPrice()));
				prdct.setAutual_price(bar.get(0).getI0018().getI0017()
						.getI0015().getI0016s().get(0).getI0031().getI0001()
						.getAutualPrice());
				prdct.setMarket1(new BigDecimal(bar.get(0).getI0018()
						.getI0017().getI0015().getI0016s().get(0).getI0031()
						.getI0001().getMarketPrice()));
				prdct.setMarket_price(bar.get(0).getI0018().getI0017()
						.getI0015().getI0016s().get(0).getI0031().getI0001()
						.getMarketPrice());

				return prdct;
			} else {
				return null;
			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return null;
		} finally {

		}
	}

	@Transactional(value = "transactionManager")
	public List<I0018> getStockDataforbatch(int sid) throws DemoException {
		List<I0018> bList = null;
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager
					.createQuery("select distinct i0018 from I0019 where sales_ID=? and client_ID=?");
			q.setParameter(1, sid);
			q.setParameter(2, clientID);
			bList = (List<I0018>) q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return bList;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> stocks(int sid, int bid) throws DemoException {
		List<I0019> i19 = null;
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager
					.createQuery("from I0019 where sales_ID=? and batch_ID=? and status='Delivered' and client_ID=?");
			q.setParameter(1, sid);
			q.setParameter(2, bid);
			q.setParameter(3, clientID);
			i19 = (List<I0019>) q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return i19;
	}

	@Transactional(value = "transactionManager")
	public String stockupdate(int bid) throws DemoException {
		try {
			I0019 barcode = entitymanager.find(I0019.class, bid);
			barcode.setStatus("barcode genterated");
			entitymanager.merge(barcode);
		} catch (Exception e) {

		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String stockupdate1(int bid) throws DemoException {
		try {
			I0019 barcode = entitymanager.find(I0019.class, bid);
			barcode.setStatus("damaged");
			entitymanager.merge(barcode);
		} catch (Exception e) {

		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String batchupdated(int batId) throws DemoException {
		try {
			I0018 i0018 = entitymanager.find(I0018.class, batId);
			i0018.setStatus("insert");
			entitymanager.merge(i0018);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String salesreturnSave(List<I0019> i19, Date d, String s)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			SalesReturn sal = new SalesReturn();
			sal.setProductName(i19.get(0).getI0018().getProductName());
			sal.setI0021(entitymanager.find(I0021.class, i19.get(0).getI0021()
					.getSales_ID()));
			sal.setSalesOrderNumber(i19.get(0).getI0021().getSalesOrderNumber());
			sal.setQuantity("" + i19.size());
			if (s.equalsIgnoreCase("damage")) {
				sal.setDr("" + i19.size());
			} else if (s.equalsIgnoreCase("normal")) {
				sal.setNr("" + i19.size());
			}
			sal.setDueDate(d);
			sal.setClient_ID(clientID);
			entitymanager.persist(sal);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String salesupdateforreturn(int sid, PurchaseOrder purchaseOrder)
			throws DemoException {
		try {
			I0021 sale = entitymanager.find(I0021.class, sid);
			sale.setStatus("Return");
			sale.setNorReturnReason(purchaseOrder.getReason());
			sale.setNorDueDate(purchaseOrder.getReturnDate());
			logger.info("return date---->>>" + sale.getNorDueDate());
			entitymanager.merge(sale);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String salesupdateforreturn1(int sid, PurchaseOrder purchaseOrder)
			throws DemoException {
		try {
			I0021 sale = entitymanager.find(I0021.class, sid);
			sale.setStatus("Return");
			sale.setDamageReturnReason(purchaseOrder.getReason());
			sale.setDamDueDate(purchaseOrder.getReturnDate());
			logger.info("return date---->>>" + sale.getNorDueDate());
			entitymanager.merge(sale);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String partialnormalreturn(PurchaseOrder p, int qty, int sid)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			SalesReturn sal = new SalesReturn();
			sal.setProductName(p.getProduct_name());
			sal.setI0021(entitymanager.find(I0021.class, sid));
			sal.setSalesOrderNumber(p.getSalesIdReference());
			sal.setQuantity("" + qty);
			if (p.getStatus().equalsIgnoreCase("damage")) {
				sal.setDr("" + qty);
			} else if (p.getStatus().equalsIgnoreCase("normal")) {
				sal.setNr("" + qty);
			}
			sal.setDueDate(p.getReturnDate());
			sal.setClient_ID(clientID);
			entitymanager.persist(sal);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public List<String> getSalesreturnSalesorder(Date d) throws DemoException {
		logger.info("==in=dao==");
		List<String> sList = null;
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager
					.createQuery("select distinct salesOrderNumber from SalesReturn where dueDate between ? and CURDATE() and client_ID=?");
			q.setParameter(1, d);
			q.setParameter(2, clientID);
			sList = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return sList;
	}

	public String viewAccountReceivable(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		ArrayList<I0021> salesList = null;
		ArrayList<I0022> invoiceList = null;
		ArrayList<I0023> payamentList = null;
		ArrayList<PurchaseOrder> accountList = null;
		try {
			purchaseOrder.setResulfinal(null);
			accountList = new ArrayList<PurchaseOrder>();
			logger.info("---------------inside viewaccountrecivable------------");
			logger.info("from date---------------->"
					+ purchaseOrder.getFromDate());
			logger.info("to date---------------->" + purchaseOrder.getToDate());
			q = entitymanager
					.createQuery("from I0021 where salesOrderDate between ? and ? and (status='insert' or status='Quick sales' or status='Delivered') and client_ID=?");
			q.setParameter(1, purchaseOrder.getFromDate());
			q.setParameter(2, purchaseOrder.getToDate());
			q.setParameter(3, clientID);
			salesList = (ArrayList<I0021>) q.getResultList();
			if (salesList.size() > 0) {
				logger.info("------------inside sales list----------------"
						+ salesList.size());
				int i = 0;
				for (I0021 salesList1 : salesList) {
					i++;
					q = null;
					q = entitymanager.createQuery("from I0022 where sales_ID=? and client_ID=?");
					q.setParameter(1, salesList1.getSales_ID());
					q.setParameter(2, clientID);
					invoiceList = (ArrayList<I0022>) q.getResultList();
					if (invoiceList.size() > 0) {
						logger.info("-----------invoice generated-------------"
								+ salesList1.getCrossTotal());
						q = null;
						q = entitymanager
								.createQuery("from I0023 where invoice_ID=? and status='pending' and client_ID=?");
						q.setParameter(1, invoiceList.get(0).getInvoice_ID());
						q.setParameter(2, clientID);
						payamentList = (ArrayList<I0023>) q.getResultList();
						if (payamentList.size() > 0) {
							logger.info("-----------inside payment pending----------");
							PurchaseOrder purchaseOrder2 = new PurchaseOrder();
							purchaseOrder2.setSerialNo(i);
							purchaseOrder2.setReason("Sales");
							purchaseOrder.setClientName(salesList1
									.getCustomerName());
							purchaseOrder2.setFromDate(salesList1
									.getSalesOrderDate());
							purchaseOrder2.setOrderNumber(salesList1
									.getSalesOrderNumber());
							purchaseOrder2.setTotalPrice(salesList1
									.getCrossTotal());
							purchaseOrder2.setCrosstotal1(payamentList.get(0)
									.getBalanceAmount());
							accountList.add(purchaseOrder2);
						} else {
							logger.info("-----------inside payment finished----------");
						}
					} else {
						logger.info("-----------invoice not generated-------------"
								+ salesList1.getSales_ID());
						PurchaseOrder purchaseOrder2 = new PurchaseOrder();
						purchaseOrder2.setSerialNo(i);
						purchaseOrder2.setReason("Sales");
						purchaseOrder.setClientName(salesList1
								.getCustomerName());
						purchaseOrder2.setFromDate(salesList1
								.getSalesOrderDate());
						purchaseOrder2.setOrderNumber(salesList1
								.getSalesOrderNumber());
						purchaseOrder2
								.setTotalPrice(salesList1.getCrossTotal());
						purchaseOrder2.setCrosstotal1(salesList1
								.getCrossTotal());
						accountList.add(purchaseOrder2);
					}

				}

			}
			logger.info("size-------------->" + accountList.size());
			purchaseOrder.setResulfinal(accountList);

		} catch (Exception e) {
			logger.info("-----------inside exception----------");
		}
		return null;

	}

	@Override
	public String viewAccountPayable(PurchaseOrder purchaseOrder)
			throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<I0015> purchaseList = null;
		ArrayList<I0022> invoiceList = null;
		ArrayList<I0023> payamentList = null;
		ArrayList<PurchaseOrder> accountList = null;
		try {
			purchaseOrder.setResulfinal(null);
			accountList = new ArrayList<PurchaseOrder>();
			logger.info("---------------inside viewaccountpayable------------");
			logger.info("from date---------------->"
					+ purchaseOrder.getFromDate());
			logger.info("to date---------------->" + purchaseOrder.getToDate());
			q = entitymanager
					.createQuery("from I0015 where orderDate between ? and ? and (status='delivered' or status='insert') and client_ID=?");
			q.setParameter(1, purchaseOrder.getFromDate());
			q.setParameter(2, purchaseOrder.getToDate());
			q.setParameter(3, clientID);
			purchaseList = (ArrayList<I0015>) q.getResultList();
			if (purchaseList.size() > 0) {
				logger.info("------------inside purchase list----------------"
						+ purchaseList.size());
				int i = 0;
				for (I0015 purchaseList1 : purchaseList) {
					i++;
					q = null;
					q = entitymanager
							.createQuery("from I0022 where purchase_ID=? and client_ID=? ");
					q.setParameter(1, purchaseList1.getPurchase_ID());
					q.setParameter(2, clientID);
					invoiceList = (ArrayList<I0022>) q.getResultList();
					if (invoiceList.size() > 0) {
						logger.info("-----------invoice generated-------------"
								+ purchaseList1.getPurchase_ID());
						q = null;
						q = entitymanager
								.createQuery("from I0023 where invoice_ID=? and status='pending' and client_ID=? ");
						q.setParameter(1, invoiceList.get(0).getInvoice_ID());
						q.setParameter(2, clientID);
						payamentList = (ArrayList<I0023>) q.getResultList();
						if (payamentList.size() > 0) {
							logger.info("-----------inside payment pending----------");
							PurchaseOrder purchaseOrder2 = new PurchaseOrder();
							purchaseOrder2.setSerialNo(i);
							purchaseOrder2.setReason("Purchase");
							purchaseOrder2.setFromDate(purchaseList1
									.getOrderDate());
							purchaseOrder2.setOrderNumber(purchaseList1
									.getTemOrderNumber());
							purchaseOrder2.setTotalPrice(purchaseList1
									.getTotalPrice());
							purchaseOrder2.setCrosstotal1(payamentList.get(0)
									.getBalanceAmount());
							accountList.add(purchaseOrder2);
						} else {
							logger.info("-----------inside payment finished----------");
						}
					} else {
						logger.info("-----------invoice not generated-------------"
								+ purchaseList1.getPurchase_ID());
						PurchaseOrder purchaseOrder2 = new PurchaseOrder();
						purchaseOrder2.setSerialNo(i);
						purchaseOrder2.setReason("Purchase");
						purchaseOrder2
								.setFromDate(purchaseList1.getOrderDate());
						purchaseOrder2.setOrderNumber(purchaseList1
								.getTemOrderNumber());
						purchaseOrder2.setTotalPrice(purchaseList1
								.getTotalPrice());
						purchaseOrder2.setCrosstotal1(purchaseList1
								.getTotalPrice());
						accountList.add(purchaseOrder2);
					}

				}

			}
			logger.info("size-------------->" + accountList.size());
			purchaseOrder.setResulfinal(accountList);

		} catch (Exception e) {
			logger.info("-----------inside exception----------");
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public List<SalesReturn> getSaleReturn(Date d) throws DemoException {
		List<SalesReturn> srList = null;
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager
					.createQuery("from SalesReturn where dueDate between ? and CURDATE() and client_ID=? ");
			q.setParameter(1, d);
			q.setParameter(2, clientID);
			srList = q.getResultList();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return srList;
	}

	@Transactional(value = "transactionManager")
	public List<SalesReturn> getSaleReturn1(Date d, String pNmae, String oNumber)
			throws DemoException {
		List<SalesReturn> srList = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			logger.info("==inside sales return remiander=====");
			q = entitymanager
					.createQuery("from SalesReturn where productName=? and salesOrderNumber=? and dueDate between ? and CURDATE() and client_ID=?");
			q.setParameter(1, pNmae);
			q.setParameter(2, oNumber);
			q.setParameter(3, d);
			q.setParameter(4, clientID);
			srList = q.getResultList();
			logger.info("===size===return===" + srList.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
		return srList;
	}

	@Transactional(value = "transactionManager")
	public String designation(EmployeeDetail emp) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q = null;
			Query d = null;
			q = entitymanager.createQuery("from Designation where client_ID=?");
			q.setParameter(1, clientID);
			List<Designation> desn = (List<Designation>) q.getResultList();
			int desnid = 0;
			if (desn.size() == 0) {
				logger.info("in dao -->> list = 0");
				Designation D = new Designation();
				D.setType(emp.designation);
				entitymanager.persist(D);
			} else if (desn.size() > 0) {
				logger.info("in dao -->> list > 0 ");

				d = entitymanager.createQuery("from Designation where type=? and client_ID=?");
				d.setParameter(1, emp.designation);
				d.setParameter(2, clientID);
				List<Designation> type = (List<Designation>) d.getResultList();
				if (type.size() == 0) {
					logger.info("in dao -->> desg > " + emp.designation);
					Designation D = new Designation();
					D.setType(emp.designation);
					D.setClient_ID(clientID);
					entitymanager.persist(D);
				} else {
					logger.info("in dao -->> desn already exists"
							+ emp.designation);
				}
			}

		} catch (Exception e) {

		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String salesSave(PurchaseOrder purchaseOrder) throws DemoException {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("customer name----------->>MMNN 2>>>"
					+ purchaseOrder.getCustomername());

			logger.info("phone number--------------------------->"
					+ purchaseOrder.getPhonenumber());

			q = entitymanager.createQuery("from I0032 where client_ID=? ");
			q.setParameter(1, clientID);
			logger.info("1");

			logger.info("2");
			ArrayList<I0032> buyer1 = (ArrayList<I0032>) q.getResultList();
			if (buyer1.size() == 0) {
				throw new DemoException("*no Data found");
			} else {
				logger.info("---------------------inside value present---------------------------"
						+ buyer1.size());

				purchaseOrder.setEmail("");
				for (I0032 i0032 : buyer1) {
					logger.info("customer name--------------->"
							+ i0032.getStatus());

					if (i0032.getPhoneNumber().equals(
							purchaseOrder.getPhonenumber())) {
						logger.info("---------------inside equals value---------------");
						purchaseOrder.setCustomername(i0032.getCustomerName());
						purchaseOrder.setShipingaddress(i0032
								.getShipingAddress());
						purchaseOrder.setPhonenumber(i0032.getPhoneNumber());
						purchaseOrder.setEmail(i0032.getEMail());
					}
				}

				logger.info("out------------>");

			}
		} finally {

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String salesSave1(PurchaseOrder purchaseOrder) throws DemoException {
		Query q31 = null;BigDecimal currAmnt=BigDecimal.valueOf(0);
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		I0021 salesOrder = new I0021();
		logger.info("1");

		logger.info("indao-->>--");
		int buyiid = 0;
		logger.info("custname " + purchaseOrder.getCustName());
		logger.info("custname " + purchaseOrder.getCustomername());
		logger.info("~~~type~~~~" + purchaseOrder.getCategory());
		if (purchaseOrder.getCategory() == null || purchaseOrder.getCategory().equalsIgnoreCase(null)) {
			logger.info("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
		} else {
			if (purchaseOrder.category.equalsIgnoreCase("Free Lancer"))

			{

				Query q9 = null;
				logger.info("===purchase===>>>" + purchaseOrder.getFirmName());
				logger.info("==custm name===>>" + purchaseOrder.getCategory());
				logger.info("==cust1===>>>" + purchaseOrder.getCustName());
				logger.info("===cust22===>>" + purchaseOrder.getCustPhNo());

				q9 = entitymanager
						.createQuery("from I0032 where freelancerName=? and categoryName='Free Lancer' and customerName=? and client_ID=?");
				q9.setParameter(1, purchaseOrder.getFirmName());

				q9.setParameter(2, purchaseOrder.getCustName());
				q9.setParameter(3, clientID);
				logger.info("11");
				ArrayList<I0032> buyer11 = (ArrayList<I0032>) q9
						.getResultList();
				logger.info("buyer id===>>" + buyer11.size());

				if (buyer11.size() > 0) {
					int i = 0;
					logger.info("==id===>>>" + buyer11.get(i).getBuyer_ID());
					buyiid = buyer11.get(i).getBuyer_ID();

				}
				logger.info("==id===buy==" + buyiid);
				logger.info("custname <<if" + purchaseOrder.getCustName());

				salesOrder.setCustomerName(purchaseOrder.getCustName());
				salesOrder.setEMail(purchaseOrder.getCustEmailId());
				salesOrder.setPhoneNumber(purchaseOrder.getCustPhNo());
			} else {
				logger.info(" custname << else"
						+ purchaseOrder.getCustomername());
				logger.info(" custname << phno" + purchaseOrder.getCustPhNo());
				salesOrder.setCustomerName(purchaseOrder.getCustomername());
				salesOrder.setPhoneNumber(purchaseOrder.getCustPhNo());

			}
		}

		salesOrder.setSalesOrderDate(purchaseOrder.getSalesorderdate());
		salesOrder.setShipingAddress(purchaseOrder.getShipingaddress());
		salesOrder.setDiscamnt(purchaseOrder.getDiscAmnt());
		salesOrder.setDisctype(purchaseOrder.getDiscType());
		salesOrder
				.setTotalNumberOfCount((purchaseOrder.getTotalnumberofcount()));
		// salesOrder.setCrossTotal(purchaseOrder.getTotalAmount());
		salesOrder.setCrossTotal(purchaseOrder.getTotalPrice());
		// salesOrder.setShippingCharge("" + 0);
		salesOrder.setShippingCharge(purchaseOrder.getShipping_charge());
		salesOrder.setShipping_company(purchaseOrder.getShipping_company());
		// salesOrder.setShopStatus(""+FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login"));
		salesOrder.setSalesOrderNumber(purchaseOrder.getSalesIdReference());
		salesOrder.setDeliveryDate(purchaseOrder.getDeliverydate());
		salesOrder.setNote(purchaseOrder.getNote());
		salesOrder.setStatus2("unpaid");
		salesOrder.setStatus("insert");
		salesOrder.setClient_ID(clientID);
		salesOrder.setCurrency(purchaseOrder.getCurrency());
		salesOrder.setNatureOfBusiness("Normal sales");
		salesOrder.setApprovalStatus("draft");
		salesOrder.setCreatedDate(date);
		if(purchaseOrder.getCurrency().equalsIgnoreCase(purchaseOrder.getBaseCurrency())){
			currAmnt=new BigDecimal(purchaseOrder.getTotalPrice());
		}else{
			currAmnt=CurrencyConverter.findExchangeRateAndConvert(purchaseOrder.getCurrency(), purchaseOrder.getBaseCurrency(), purchaseOrder.getTotalPrice());
		}
		salesOrder.setCurrencyAmount(String.valueOf(currAmnt));
		salesOrder.setUserID(entitymanager.find(UserCreate.class, Integer.parseInt(userID)));
		logger.info("1");
		I0032 id = entitymanager.find(I0032.class, buyiid);
		logger.info("==id===ent==>->" + id);
		salesOrder.setI0032(id);
		// salesOrder.setShippingCost(purchaseOrder.getShippingcost());
		// salesOrder.setShippingCompanyName(purchaseOrder.getShippingname());
		logger.info("2");
		entitymanager.persist(salesOrder);
		purchaseOrder.setCurrencyAmount(String.valueOf(currAmnt));
		Query q11 = null;
		logger.info("----------inside dao--------->");
		q11 = entitymanager.createQuery("from I0021 where client_ID=?");
		q11.setParameter(1, clientID);
		// q11.setParameter(1,
		// ""+FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login"));
		List<I0021> resultt = (List<I0021>) q11.getResultList();
		if (resultt.size() > 0) {
			logger.info("------------inside if----------");
			purchaseOrder.setCount(resultt.get(resultt.size() - 1)
					.getSales_ID());
			purchaseOrder.setSalesId(resultt.get(resultt.size() - 1)
					.getSales_ID());

		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public String purchasePrice(PurchaseOrder p) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		try {
			List<I0001> batchProduct1 = null;
			logger.info("----------------inside  dao---------------");
			Query q2 = null;
			q2 = entitymanager
					.createQuery("from I0001 where productName=? and status='i' and client_ID=? and user_ID=?");
			logger.info("1");
			q2.setParameter(1, p.getBatchProductName());
			q2.setParameter(2, clientID);
			q2.setParameter(3, userID);
			batchProduct1 = (List<I0001>) q2.getResultList();
			int id = 0;
			if (batchProduct1.size() > 0) {
				logger.info("-----------inside if--------------");
				logger.info("batch size----------->" + batchProduct1.size());
				id = batchProduct1.get(0).getProduct_ID();
				p.setSellingPrice(batchProduct1.get(0).getAutualPrice());
				logger.info("Selling Price------------>" + p.sellingPrice);
				p.setMarginPrice((batchProduct1.get(0).getAutualPrice()));
				p.setUnit(batchProduct1.get(0).getUnit());
				logger.info("Unit" + batchProduct1.get(0).getUnit());
				logger.info("iddddddddd" + id);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return null;
	}

	public String clientCurrency(String clientID){
		System.out.println("client id "+clientID);
		Query v=null;
		v=entitymanager.createQuery("from Client where clientNumber=? and status='Active'");
		v.setParameter(1, clientID);
		List<Client> client=(List<Client>)v.getResultList();
		return client.get(0).getBaseCurrency();
	}
	
	@Transactional(value = "transactionManager")
	public String purchase(PurchaseOrder purchaseOrder) throws DemoException {
		logger.info("-------------------inside purchase dao--------------");
		Query q11 = null;BigDecimal currAmount=BigDecimal.valueOf(0);
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		q11 = entitymanager.createQuery("from I0015 where client_ID=?");
		q11.setParameter(1, clientID);
		List<I0015> resultt = (List<I0015>) q11.getResultList();

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		logger.info("Current Year is : " + year);
		String month = new SimpleDateFormat("MMM").format(now.getTime());
		
		logger.info("Month" + month.toUpperCase());
		System.out.println("Month" + month.toUpperCase());

		int count = 00000;
		if (resultt.size() > 0) {
			for (I0015 re : resultt) {
				count++;
			}
			count = count + 1;
			String s1 = String.format("%05d", count);
			String s = "PO" + s1 +  month.toUpperCase() + year;
			purchaseOrder.setOrderNumber(s);
			logger.info("order no 2---->>>  " + purchaseOrder.getOrderNumber());
		} else {
			count++;
			String s1 = String.format("%05d", count);
			String s = "PO" + s1 + month.toUpperCase() + year;
			purchaseOrder.setOrderNumber(s);
			logger.info("order no 2---->>>  " + purchaseOrder.getOrderNumber());
		}

		logger.info("reference number------------>"
				+ purchaseOrder.getOrderNumber());
		I0015 purchase = new I0015();
		logger.info("1");
		purchase.setQuantity(purchaseOrder.getQuantity1());
		purchase.setOrderDate(purchaseOrder.getOrderDate());
		purchase.setTemOrderNumber(purchaseOrder.getOrderNumber());
		purchase.setTargentDate(purchaseOrder.getTargentDate());
		purchase.setStatus("insert");
		purchase.setStatus2("notAdded");
		purchase.setClient_ID(clientID);
		purchase.setCurrencyType(purchaseOrder.getDestinationCurrency());
		purchase.setTotalPrice(String.valueOf(purchaseOrder.getTotalAmount()));
		if(purchaseOrder.getDestinationCurrency().equalsIgnoreCase(purchaseOrder.getBaseCurrency())){
			currAmount=new BigDecimal(purchaseOrder.getTotalAmount());
		}else{
			currAmount=CurrencyConverter.findExchangeRateAndConvert(purchaseOrder.getDestinationCurrency(),purchaseOrder.getBaseCurrency(),purchaseOrder.getTotalAmount());
		}
		purchase.setCurrency(String.valueOf(currAmount));
		System.out.println("amount"+String.valueOf(currAmount));
		purchase.setApprovalStatus("draft");
		purchase.setCreatedDate(date);
		purchase.setUserID(entitymanager.find(UserCreate.class, Integer.parseInt(userID)));
		entitymanager.persist(purchase);
		q11 = null;
		q11 = entitymanager.createQuery("from I0015 where client_ID=? and user_ID=?");
		q11.setParameter(1, clientID);
		q11.setParameter(2, userID);
		resultt = (List<I0015>) q11.getResultList();
		purchaseOrder.setProduct_ID((resultt.get(resultt.size() - 1)
				.getPurchase_ID()));
		logger.info("id------------------------>"
				+ purchaseOrder.getProduct_ID());
		return null;
	}

	@Transactional(value = "transactionManager")
	public String purchase1(PurchaseOrder purchaseOrder) throws DemoException {
		PurchaseOrderFromMB puridss = new PurchaseOrderFromMB();
		logger.info("~~~pur~~~ids~~~" + puridss.getActualPrice());
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		try {
			Query q = null;
			Query q1 = null;
			Query q2 = null;
			q = entitymanager
					.createQuery("from I0025 where vendorPhoneNumber=? and status='i' and client_ID=? and user_ID=?");
			q.setParameter(1, purchaseOrder.getVendorPhoneNumber());
			q.setParameter(2, clientID);
			q.setParameter(3, userID);
			List<I0025> result = (List<I0025>) q.getResultList();
			int fID = 0;
			if (result.size() > 0) {
				logger.info("--------------inside vendor IF----------------");
				int j = 0;
				fID = result.get(j).getVendor_ID();
				purchaseOrder.setFirmName(result.get(j).getFirmName());
				logger.info("Fid---------------------------->" + fID);
			}
			q1 = entitymanager
					.createQuery("from I0001 where productName=? and status='i' and client_ID=? and user_ID=?");
			q1.setParameter(1, purchaseOrder.getBatchProductName());
			q1.setParameter(2, clientID);
			q1.setParameter(3, userID);
			List<I0001> resul = (List<I0001>) q1.getResultList();
			int pID = 0;
			if (resul.size() > 0) {
				logger.info("--------------inside product IF--------------");
				int j = 0;
				pID = resul.get(j).getProduct_ID();
				logger.info("pID---------------------------->" + pID);
			}
			String b = "" + pID;
			String a = "" + fID;
			q2 = entitymanager
					.createQuery("from I0031 where vendor_ID=? and product_ID=?");
			q2.setParameter(1, a);
			q2.setParameter(2, b);
			List<I0031> resu = (List<I0031>) q2.getResultList();
			int hID = 0;
			if (resu.size() > 0) {
				logger.info("---------------inside vendor_product IF----------------");
				int i = 0;
				hID = resu.get(i).getHas_vendor_ID();
				logger.info("vendor Id---------------->" + hID);
			}
			I0016 haspurchase = new I0016();
			haspurchase.setOrdernumber(purchaseOrder.getOrderNumber());
			haspurchase.setQuantityTotal(resul.get(0).getAutualPrice());
			haspurchase.setStatus("inserted");
			haspurchase.setApprovalStatus("inserted");
			haspurchase.setOrderDate(purchaseOrder.getOrderDate());
			haspurchase.setStatus2("pending");
			haspurchase.setStatus3("Waiting");
			haspurchase.setStatus4("Waiting");
			haspurchase.setQuantity(purchaseOrder.getQuantity());
			haspurchase.setI0031(entitymanager.find(I0031.class, hID));
			haspurchase.setI0015(entitymanager.find(I0015.class,
					purchaseOrder.getProduct_ID()));
			logger.info("------------1 out------------------");
			haspurchase.setClient_ID(clientID);
			entitymanager.persist(haspurchase);
			logger.info("------------inside dao----------");
		} catch (Exception e) {
			logger.info("-------------inside exception----------------");
			logger.error("inside exception ",e);
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String purchaseorderClose(PurchaseOrder purchaseOrder)
			throws DemoException {
		Query q = null;
		Query q1 = null;
		String status="";
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		ArrayList<I0015> result3 = new ArrayList<I0015>();
		ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
		try {
			logger.info("%%%%%%%%%%%%%%%INSIDE PURCHASE CLOSE DAO %%%%%%%%%%%%%%%%%");
			logger.info("purchase order number----------------"
					+ purchaseOrder.orderNumber);
			q = entitymanager.createQuery("from I0015 where temOrderNumber=? and client_ID=? and user_ID=?");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, clientID);
			q.setParameter(3, userID);
			ArrayList<I0015> result = (ArrayList<I0015>) q.getResultList();
			int id = 0;
			if (result.size() > 0) {
				logger.info("----------------------inside result purachase close-----------------------");
				id = result.get(0).getPurchase_ID();
				logger.info("Id---------->" + id);
				q1 = entitymanager
						.createQuery("from I0016 where purchase_ID=?");
				q1.setParameter(1, id);
				ArrayList<I0016> result1 = (ArrayList<I0016>) q1
						.getResultList();
				if (result1.size() > 0) {
					logger.info("-1-");
					int count = 0;
					for (I0016 i0016 : result1) {
						logger.info("-2-");
						PurchaseOrder result5 = new PurchaseOrder();
						result5.setSerialNo((count + 1));
						result5.setProduct_name(i0016.getI0031().getI0001()
								.getProductName());
						result5.setOrderDate(i0016.getOrderDate());
						result5.setQuantity1(i0016.getQuantity());
						result5.setUnit(i0016.getI0031().getI0001().getUnit());
						result5.setCrosstotal1(""
								+ (new BigDecimal(i0016.getI0031().getI0001()
										.getAutualPrice())));
						result5.setMarginPrice(""
								+ ((new BigDecimal(result1.get(count)
										.getQuantity())))
										.multiply(new BigDecimal(i0016
												.getI0031().getI0001()
												.getAutualPrice())));
						logger.info("price----------------->"
								+ ((new BigDecimal(result1.get(count)
										.getQuantity())))
										.multiply(new BigDecimal(i0016
												.getI0031().getI0001()
												.getAutualPrice())));
						result5.setDestinationCurrency(i0016.getI0015().getCurrencyType());
						result4.add(result5);
						count++;
					}
					purchaseOrder.setResult4(result4);
					purchaseOrder.setTotalPrice(result.get(0).getTotalPrice());
					purchaseOrder.setTotalAmount(result.get(0).getCurrency());
				}
				logger.info("----------------------inside result purachase close-----------------------");
				status="success";
			} else {
				throw new DemoException("*this odernumber is not present");
			}
		} catch (Exception e) {

		}
		return status;
	}

	@Transactional(value = "transactionManager")
	public List<I0023> paymentamountsale(PurchaseOrder purchaseOrder)
			throws DemoException {
		Query q1 = null;
		Query q2 = null;
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		List<I0023> list = null;
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager.createQuery("from I0021 where salesOrderNumber=? and client_ID=? and user_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, clientID);
		q.setParameter(3, userID);
		List<I0021> result = (List<I0021>) q.getResultList();
		purchaseOrder.setResult(result);
		int id = 0;
		int i = 0;
		Date date = null;
		if (result.size() > 0) {
			id = result.get(i).getSales_ID();
			date = result.get(i).getSalesOrderDate();
			q1 = entitymanager
					.createQuery("from I0022 where sales_ID=? and status=? and client_ID=?");
			q1.setParameter(1, id);
			q1.setParameter(2, "Sales Invoice");
			q1.setParameter(3, clientID);
			List<I0022> resul = (List<I0022>) q1.getResultList();
			int invoiceid = 0;
			if (resul.size() > 0) {
				int j = 0;
				invoiceid = resul.get(j).getInvoice_ID();
				q2 = entitymanager
						.createQuery("from I0023 where invoice_ID=? and client_ID=?");
				q2.setParameter(1, invoiceid);
				q2.setParameter(2, clientID);
				list = (List<I0023>) q2.getResultList();
				logger.info("list size ---->> " + list.size());
			}
			logger.info("customer "+result.get(0).getCustomerName());
			q=entitymanager.createQuery("from I0032 where customerName=? and client_ID=? and user_ID=? and status='in'");
			q.setParameter(1, result.get(0).getCustomerName());
			q.setParameter(2, clientID);
			q.setParameter(3, Integer.parseInt(userID));
			List<I0032> i0032=(List<I0032>)q.getResultList();
			if(i0032.size()>0){
				purchaseOrder.setPaymentMode(i0032.get(0).getPaymentType());
			}
		}
		return list;

	}

	@Transactional(value = "transactionManager")
	public List<I0023> paymentamount(PurchaseOrder purchaseOrder)
			throws DemoException {
		logger.info("--------------$$$$$$$$$$$$$$------------Inside AccountOut paynow Dao -------------$$$$$$$$$$$$$$-----------");
		List<I0023> list = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		int pID = 0;
		int jId = 0;
		logger.info("order number:::" + purchaseOrder.getOrderNumber());
		q = entitymanager
				.createQuery("from I0016 where ordernumber=? and status=? and client_ID=? ");
		q.setParameter(1, purchaseOrder.getOrderNumber());
		q.setParameter(2, "inserted");
		q.setParameter(3, clientID);
		ArrayList<I0016> result = (ArrayList<I0016>) q.getResultList();
		if (result.size() > 0) {
			purchaseOrder.setPaymentTerms(result.get(0).getI0031().getI0025().getPaymentType());
			int i = 0;
			int hpid = 0;
			pID = result.get(i).getI0015().getPurchase_ID();
			Query q1 = null;
			q1 = entitymanager.createQuery("from I0022 where purchase_ID=?");
			Object arg1 = pID;
			q1.setParameter(1, arg1);
			ArrayList<I0022> resul = (ArrayList<I0022>) q1.getResultList();
			if (resul.size() > 0) {
				int j = 0;
				jId = resul.get(j).getInvoice_ID();
				Query q2 = null;
				q2 = entitymanager.createQuery("from I0023 where invoice_ID=?");
				q2.setParameter(1, jId);
				list = (ArrayList<I0023>) q2.getResultList();
			}

		}

		return list;
	}

	@Transactional(value = "transactionManager")
	public String addStock4(PurchaseOrder purchaseOrder, int batchid,
			float quantity) throws DemoException {
		Query a = null;String status="";
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		a = entitymanager.createQuery("from I0019 where batch_ID=? and client_ID=?");
		a.setParameter(1, batchid);
		a.setParameter(2, clientID);
		List<I0019> result = (List<I0019>) a.getResultList();
		if (result.size() > 0) {
			int bid = 0;
			float quan = 0;
			float addquan = 0;
			bid = result.get(0).getBar_code_ID();
			quan = Float.parseFloat(result.get(0).getQuantity());
			addquan = quan + quantity;
			I0019 barcode = entitymanager.find(I0019.class, bid);
			barcode.setStatus("barcode genterated");
			barcode.setStatus2("i");
			barcode.setQuantity(String.valueOf(addquan));
			barcode.setI0015(entitymanager.find(I0015.class,
					purchaseOrder.getPurchaseid()));
			entitymanager.merge(barcode);
		} else {
			I0019 barcode = new I0019();
			barcode.setI0018(entitymanager.find(I0018.class, batchid));
			barcode.setStatus("barcode genterated");
			barcode.setStatus2("i");
			barcode.setDamgeStatus("0");
			barcode.setI0015(entitymanager.find(I0015.class,
					purchaseOrder.getPurchaseid()));
			barcode.setQuantity(String.valueOf(quantity));
			barcode.setClient_ID(clientID);
			entitymanager.persist(barcode);
		}
		status="Success";
		return status;
	}

	@Transactional(value = "transactionManager")
	public String addedStock(PurchaseOrder purchaseOrder) throws DemoException {
		try {
			I0015 ss = entitymanager.find(I0015.class,
					Integer.parseInt(purchaseOrder.getStatus2()));
			ss.setStatus2("addedToStock");
			entitymanager.merge(ss);
		} catch (Exception e) {
			logger.info("catch -" + e.getMessage());
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String stockQuantity(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q1 = null;
			Query q5 = null;
			q1 = entitymanager.createQuery("from I0018 where productName=? and 	client_ID=?");
			q1.setParameter(1, purchaseOrder.getBatch());
			q1.setParameter(2, clientID);
			ArrayList<I0018> resultproduct = (ArrayList<I0018>) q1
					.getResultList();
			if (resultproduct.size() > 0) {
				int batchid = 0;
				batchid = resultproduct.get(0).getBatch_ID();
				String sellingprice = "0";
				sellingprice = "" + resultproduct.get(0).getUnitPrice();
				q5 = entitymanager
						.createQuery("from I0019 where batch_ID=? and status='barcode genterated' and client_ID=? ");
				q5.setParameter(1, batchid);
				q5.setParameter(2, clientID);
				ArrayList<I0019> resultStock5 = (ArrayList<I0019>) q5.getResultList();
				logger.info("overall product in barcode---->"+ resultStock5.size());
				String stockQuan = "0";
				String saledQuantity = "0";
				int barID = 0;
				if (resultStock5.size() > 0) {
					barID = resultStock5.get(0).getBar_code_ID();
					stockQuan = resultStock5.get(0).getQuantity();
					if (new BigDecimal(stockQuan).compareTo(new BigDecimal(
							purchaseOrder.getQuantity())) == -1) {
						throw new DemoException("This product has only "
								+ stockQuan + " stocks");
					}
				}
			}
			return null;
		} finally {

		}

	}

	@Transactional(value = "transactionManager")
	public String salesReturnSubmit(PurchaseOrder purchaseOrder)
			throws DemoException {
		Query q = null;
		Query s = null;
		Query s1 = null;
		Query s2 = null;
		String stockQuan = "0";
		String newQuant = "0";
		String remQuant = "0";
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		float stockin = (float) 0.0;
		float stockout = (float) 0.0;
		float tempnew = (float) 0.0;
		float tempnew1 = (float) 0.0;
		try {
			q = entitymanager
					.createQuery("from I0021 where salesOrderNumber=? and client_ID=? ");
			q.setParameter(1, purchaseOrder.getSalesIdReference());
			q.setParameter(2, clientID);
			List<I0021> sales = (List<I0021>) q.getResultList();
			int batchid = 0;
			int barcodeid = 0;
			int salesid = 0;
			int salesrecordid = 0;
			if (sales.size() > 0) {
				salesid = sales.get(0).getSales_ID();
			}
			s = entitymanager.createQuery("from I0018 where productName=? and client_ID=?");
			s.setParameter(1, purchaseOrder.getProduct_name());
			s.setParameter(2, clientID);
			List<I0018> result = (List<I0018>) s.getResultList();
			if (result.size() > 0) {
				batchid = result.get(0).getBatch_ID();
				logger.info("product id -- >>> " + batchid);
				s1 = entitymanager.createQuery("from I0019 where batch_ID=? and client_ID=?");
				s1.setParameter(1, batchid);
				s1.setParameter(2, clientID);
				List<I0019> resul = (List<I0019>) s1.getResultList();
				if (resul.size() > 0) {
					// stockQuan=resul.get(0).getQuantity();
					stockin = Float.parseFloat(resul.get(0).getQuantity());
					stockout = Float.parseFloat(resul.get(0).getStock_Out());
					barcodeid = resul.get(0).getBar_code_ID();
					s2 = entitymanager.createQuery("from SalesRecord where bar_code_ID=? and sales_ID=?  and (returnStatus='no' or returnStatus='Return') and client_ID=?");
					s2.setParameter(1, barcodeid);
					s2.setParameter(2, salesid);
					s2.setParameter(3, clientID);
					List<SalesRecord> resu = (List<SalesRecord>) s2
							.getResultList();
					String temp = "";
					if (resu.size() > 0) {
						salesrecordid = resu.get(0).getSalesRecordId();
						temp = ""
								+ (new BigDecimal(purchaseOrder.getNr())
										.add(new BigDecimal(purchaseOrder
												.getDr())));
						float returnq = 0;
						float norreturn = 0;
						float damreturn = 0;
						try {
							returnq = Float.parseFloat(resu.get(0)
									.getReturnQuantity());

						} catch (Exception e) {
							returnq = Float.parseFloat("0");
							norreturn = Float.parseFloat("0");
							damreturn = Float.parseFloat("0");
							;
						}
						returnq = returnq + Float.parseFloat(temp);
						norreturn = norreturn
								+ Float.parseFloat(purchaseOrder.getNr());
						damreturn = damreturn
								+ Float.parseFloat(purchaseOrder.getDr());
						SalesRecord retur = entitymanager.find(
								SalesRecord.class, salesrecordid);
						BigDecimal qq = new BigDecimal(returnq);
						qq = qq.setScale(2, RoundingMode.CEILING);
						retur.setReturnQuantity("" + qq);
						retur.setReturnStatus("Return");

						entitymanager.merge(retur);
					}
					SalesReturn insert = new SalesReturn();
					insert.setProductName(purchaseOrder.getProduct_name());
					insert.setQuantity(purchaseOrder.getQuantity());
					insert.setDueDate(purchaseOrder.getTargentDate());
					insert.setDr(purchaseOrder.getDr());
					insert.setNr(purchaseOrder.getNr());
					insert.setSalesRecord(entitymanager.find(SalesRecord.class,
							salesrecordid));

					insert.setSalesOrderNumber(purchaseOrder
							.getSalesIdReference());
					insert.setI0021(entitymanager.find(I0021.class, salesid));
					insert.setClient_ID(clientID);
					entitymanager.persist(insert);

					if (purchaseOrder.getNr() != null) {
						tempnew = stockin
								+ Float.parseFloat(purchaseOrder.getNr());
						tempnew1 = stockout
								- Float.parseFloat(purchaseOrder.getNr());
						I0019 stock = entitymanager
								.find(I0019.class, barcodeid);
						stock.setQuantity(String.valueOf(tempnew));
						stock.setStock_Out(String.valueOf(tempnew1));
						entitymanager.merge(stock);
						s1 = entitymanager
								.createQuery("from I0019 where batch_ID=? and client_ID=? ");
						s1.setParameter(1, batchid);
						s1.setParameter(2, clientID);
						List<I0019> resul1 = (List<I0019>) s1.getResultList();
						logger.info("list 19 size" + resul1.size());
						logger.info("list 19 stock in "
								+ resul1.get(0).getQuantity());

					}
					if (purchaseOrder.getDr() != null) {
						BigDecimal dreturn = BigDecimal.valueOf(0);
						I0019 stock1 = entitymanager.find(I0019.class,
								barcodeid);
						dreturn = (new BigDecimal(stock1.getDamgeStatus()))
								.add(new BigDecimal(purchaseOrder.getDr()));
						stock1.setDamgeStatus("" + dreturn);
						entitymanager.merge(stock1);
					}

					s2 = entitymanager
							.createQuery("from SalesRecord where bar_code_ID=? and sales_ID=? and (returnStatus='no' or returnStatus='Return') and client_ID=?");
					s2.setParameter(1, barcodeid);
					s2.setParameter(2, salesid);
					s2.setParameter(3, clientID);
					List<SalesRecord> resu1 = (List<SalesRecord>) s2
							.getResultList();
					if (resu1.size() > 0) {
						int sid = 0;
						if (resu1.get(0).getSoldQuantity()
								.equals(resu1.get(0).getReturnQuantity())) {
							sid = resu1.get(0).getSalesRecordId();
							SalesRecord retur = entitymanager.find(
									SalesRecord.class, sid);
							retur.setReturnStatus("Returned");
						}
					}

					Query s3 = null;
					s3 = entitymanager
							.createQuery("from SalesRecord where  sales_ID=? and client_ID=? ");
					s3.setParameter(1, salesid);
					s3.setParameter(2, clientID);
					List<SalesRecord> res = (List<SalesRecord>) s3
							.getResultList();
					int cc = 0;
					if (res.size() > 0) {
						for (int i = 0; i < resu1.size(); i++) {
							int sid = 0;
							if (res.get(0).getSoldQuantity()
									.equals(res.get(0).getReturnQuantity())) {
								cc++;
							}
						}
					}

					if (cc == res.size()) {
						I0021 i21 = entitymanager.find(I0021.class, salesid);
						i21.setStatus("returned");
						entitymanager.merge(i21);

					}

				}
			}
			Query qq = null;
			qq = entitymanager.createQuery("from SalesReturn where sales_ID=? and client_ID=?");
			qq.setParameter(1, salesid);
			qq.setParameter(2, clientID);
			List<SalesReturn> prlist = (List<SalesReturn>) qq.getResultList();
			BigDecimal rq = BigDecimal.valueOf(0);
			BigDecimal aprice = BigDecimal.valueOf(0);
			if (prlist.size() > 0) {
				Query ap = null;
				ap = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
				ap.setParameter(1, prlist.get(0).getProductName());
				ap.setParameter(2, clientID);
				List<I0001> prdlist = (List<I0001>) ap.getResultList();
				for (int i = 0; i < prlist.size(); i++) {
					rq = new BigDecimal(prlist.get(i).getNr())
							.add(new BigDecimal(prlist.get(i).getDr()));
					aprice = aprice.add(rq.multiply(new BigDecimal(prdlist.get(
							0).getMarketPrice())));
				}
				purchaseOrder.setA(new BigDecimal(sales.get(0).getCrossTotal())
						.subtract(aprice));
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional(value = "transactionManager")
	public String salesReturnSubmit1(PurchaseOrder purchaseOrder) {
		BigDecimal paymount = BigDecimal.valueOf(0);
		BigDecimal namount = BigDecimal.valueOf(0);
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			logger.info("Inside purchaseReturnInsert1");
			Query q = null;
			q = entitymanager
					.createQuery("from I0021 where salesOrderNumber=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getSalesIdReference());
			q.setParameter(2, clientID);
			List<I0021> list15 = (List<I0021>) q.getResultList();
			logger.info("size of list15" + list15.size());

			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0022 where sales_ID=? and status='Sales Invoice' and client_ID=? ");
			q1.setParameter(1, list15.get(0).getSales_ID());
			q1.setParameter(2, clientID);
			List<I0022> list16 = (List<I0022>) q1.getResultList();
			logger.info("size of list16" + list16.size());

			if (list16.size() > 0) {
				Query q2 = null;
				q2 = entitymanager.createQuery("from I0023 where invoice_ID=? and client_ID=?");
				q2.setParameter(1, list16.get(0).getInvoice_ID());
				q2.setParameter(2, clientID);
				List<I0023> list17 = (List<I0023>) q2.getResultList();
				logger.info("size of list17" + list17.size());

				int payid = 0;
				String ap = "" + purchaseOrder.getA();
				if (list17.size() > 0) {
					logger.info("status" + list17.get(0).getStatus());
					payid = list17.get(0).getPayment_ID();
					logger.info("Inside paid/pending"
							+ payid
							+ " -- paid -> "
							+ ((purchaseOrder.getA()).subtract(new BigDecimal(
									list17.get(0).getPaidAmount()))));

					if (list17.get(0).getStatus().equals("pending")) {
						logger.info("inside pending");
						I0023 i0023 = entitymanager.find(I0023.class, payid);
						paymount = new BigDecimal(i0023.getPayableAmount());
						namount = paymount.subtract(purchaseOrder.getA());
						i0023.setPayableAmount("" + ap);
						logger.info("a1  :" + i0023.getPayableAmount());
						i0023.setBalanceAmount(""
								+ ((purchaseOrder.getA())
										.subtract(new BigDecimal(list17.get(0)
												.getPaidAmount()))));
						logger.info("balance amount after return : "
								+ i0023.getBalanceAmount()
								+ "-------"
								+ namount.subtract(new BigDecimal(i0023
										.getPaidAmount())));
						entitymanager.merge(i0023);
						String namt = "" + purchaseOrder.getA();
						if (Integer.parseInt(list17.get(0).getPayableAmount()) <= Integer
								.parseInt(namt)) {
							logger.info("payable amount less");
							if (Integer.parseInt(list17.get(0).getPaidAmount()) > Integer
									.parseInt(list17.get(0).getPayableAmount())) {
								logger.info("payable amount less");
								I0023 i023 = entitymanager.find(I0023.class,
										payid);
								i023.setStatus("paid");
								entitymanager.merge(i023);
							}
						} else if (Integer.parseInt(list17.get(0)
								.getPayableAmount()) > Integer.parseInt(namt)) {
							if (Integer.parseInt(list17.get(0).getPaidAmount()) > Integer
									.parseInt(namt)) {
								I0023 i023 = entitymanager.find(I0023.class,
										payid);
								i023.setStatus("paid");
								entitymanager.merge(i023);
							}
						}
					} else if (list17.get(0).getStatus().equals("paid")) {
						logger.info("inside paid");
						I0023 i23 = entitymanager.find(I0023.class, payid);

						paymount = new BigDecimal(i23.getPayableAmount());
						namount = paymount.subtract(purchaseOrder.getA());
						i23.setPayableAmount("" + purchaseOrder.getA());

						entitymanager.merge(i23);
					}
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return null;

	}

	@Transactional(value = "transactionManager")
	public String returnQuantity(PurchaseOrder purchaseOrder)
			throws DemoException {
		Query q = null;
		Query s = null;
		Query s1 = null;
		Query s2 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q = entitymanager
					.createQuery("from I0021 where salesOrderNumber=? and client_ID=? ");
			q.setParameter(1, purchaseOrder.getSalesIdReference());
			q.setParameter(2, clientID);
			List<I0021> sales = (List<I0021>) q.getResultList();
			int batchid = 0;
			int barcodeid = 0;
			int salesid = 0;
			int salesrecordid = 0;
			salesid = sales.get(0).getSales_ID();
			s = entitymanager.createQuery("from I0018 where productName=? and client_ID=? ");
			s.setParameter(1, purchaseOrder.getProduct_name());
			s.setParameter(2, clientID);
			List<I0018> result = (List<I0018>) s.getResultList();
			if (result.size() > 0) {
				batchid = result.get(0).getBatch_ID();
				s1 = entitymanager.createQuery("from I0019 where batch_ID=? and client_ID=? ");
				s1.setParameter(1, batchid);
				s1.setParameter(2, clientID);
				List<I0019> resul = (List<I0019>) s1.getResultList();
				if (resul.size() > 0) {
					barcodeid = resul.get(0).getBar_code_ID();
					s2 = entitymanager
							.createQuery("from SalesRecord where bar_code_ID=? and sales_ID=? and (returnStatus='no' or returnStatus='Return') and client_ID=?");
					s2.setParameter(1, barcodeid);
					s2.setParameter(2, salesid);
					s2.setParameter(3, clientID);
					List<SalesRecord> resu = (List<SalesRecord>) s2
							.getResultList();
					String temp = "0";
					if (resu.size() > 0) {
						salesrecordid = resu.get(0).getSalesRecordId();
						String balquan = "";
						temp = ""
								+ (new BigDecimal(purchaseOrder.getNr())
										.add(new BigDecimal(purchaseOrder
												.getDr())));
						float quantity = 0;
						try {
							quantity = Float.parseFloat(resu.get(0)
									.getReturnQuantity());
						} catch (Exception e) {
							quantity = Float.parseFloat("0");
						}
						String soldquan = resu.get(0).getSoldQuantity();
						purchaseOrder.setRemaining(String.valueOf(quantity));
						balquan = ""
								+ (new BigDecimal(soldquan)
										.subtract(new BigDecimal(quantity)));
						purchaseOrder.setQuantity1(balquan);
					}

				}
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String viewSalesReturn(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			List<PurchaseOrder> mb2 = new ArrayList<PurchaseOrder>();
			purchaseOrder.setResult1(null);
			Query q = entitymanager.createQuery("From SalesReturn where client_ID=? ");
			q.setParameter(1, clientID);
			List<SalesReturn> salelist = (ArrayList<SalesReturn>) q
					.getResultList();
			int salrecid = 0;
			if (salelist.size() > 0) {
				for (int i = 0; i < salelist.size(); i++) {
					salrecid = salelist.get(i).getSalesRecord()
							.getSalesRecordId();
					Query q1 = entitymanager
							.createQuery("From SalesRecord where salesRecordId=? and  status='salesorder' and (returnStatus='Returned' or returnStatus='Return') and client_ID=?");
					q1.setParameter(1, salrecid);
					q1.setParameter(2, clientID);
					List<SalesRecord> salelist1 = (ArrayList<SalesRecord>) q1
							.getResultList();
					if (salelist1.size() > 0) {
						int ii = 0;
						ii = salelist1.get(0).getI0019().getBar_code_ID();
						PurchaseOrder po = new PurchaseOrder();
						po.setOrderDate(salelist.get(i).getDueDate());
						po.setOrderNumber(salelist.get(i).getSalesOrderNumber());
						po.setVendorPhoneNumber(salelist1.get(0).getI0021()
								.getCustomerName());

						mb2.add(po);
						purchaseOrder.setDomain2(mb2);
					}
				}

			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "";

	}

	@Transactional(value = "transactionManager")
	public String viewSalesReturnDetail(PurchaseOrder purchaseOrder)
			throws DemoException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Query q = null;
		Query q1 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<SalesReturn> list1 = null;
		List<PurchaseOrder> mb2 = new ArrayList<PurchaseOrder>();
		PurchaseOrder p1 = new PurchaseOrder();
		try {
			q1 = entitymanager.createQuery("from SalesReturn where salesOrderNumber=? and client_ID=?");
			q1.setParameter(1, purchaseOrder.getOrderNumber());
			q1.setParameter(2, clientID);
			list1 = (ArrayList) q1.getResultList();
			if (list1.size() > 0) {
				logger.info("1");
				for (int i = 0; i < list1.size(); i++) {
					logger.info("2");
					Query q2 = entitymanager
							.createQuery("from SalesRecord where sales_id=? and (returnStatus='Returned' or returnStatus='Return') and client_ID=?");
					q2.setParameter(1, list1.get(i).getI0021().getSales_ID());
					q2.setParameter(2, clientID);
					List<SalesRecord> li = (List<SalesRecord>) q2.getResultList();
					if (li.size() > 0) {
						int barid = li.get(0).getI0019().getBar_code_ID();
						logger.info("bar id" + barid);
						Query q3 = entitymanager
								.createQuery("from I0019 where bar_code_ID=? and client_ID=?");
						q3.setParameter(1, barid);
						q3.setParameter(2, clientID);
						List<I0019> lis = (List<I0019>) q3.getResultList();
						if (lis.size() > 0) {
							String roll = lis.get(0).getRoll_ID();
							PurchaseOrder po = new PurchaseOrder();
							po.setVendorname(list1.get(0).getSalesRecord()
									.getI0019().getI0015().getI0016s().get(0)
									.getI0031().getI0025()
									.getVendorPhoneNumber());
							po.setOrderDate(list1.get(i).getI0021()
									.getSalesOrderDate());
							po.setOrderNumber(list1.get(i)
									.getSalesOrderNumber());
							po.setReturnDate(list1.get(i).getDueDate());
							po.setProductName(list1.get(i).getProductName());
							po.setReturnQuan1(list1.get(i).getNr());
							po.setReturnQuan2(list1.get(i).getDr());
							po.setTotalQuan1(list1.get(i).getSalesRecord()
									.getSoldQuantity());
							po.setRollID(roll);
							mb2.add(po);
							purchaseOrder.setDomain2(mb2);
						}

					}
				}
			}
		} catch (Exception e) {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public ArrayList<String> changeList(String changeEvent,
			ArrayList<String> productList, PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<String> product = new ArrayList<String>();
		ArrayList<String> productQuantity = new ArrayList<String>();
		try {
			Query q = null;
			q = entitymanager.createQuery("from I0015 where temOrderNumber=? and client_ID=?");
			q.setParameter(1, changeEvent);
			q.setParameter(2, clientID);
			List<I0015> result = (List<I0015>) q.getResultList();
			int purchaseID = 0;
			if (result.size() > 0) {
				int j = 0;
				purchaseID = result.get(j).getPurchase_ID();
				logger.info("purchaseID" + result.get(j).getPurchase_ID());
				purchaseOrder.setStatus2("" + purchaseID);
			}
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0016 where purchase_ID=? and status=? and client_ID=?");
			q1.setParameter(1, purchaseID);
			q1.setParameter(2, "inserted");
			q1.setParameter(3, clientID);

			List<I0016> resul = (List<I0016>) q1.getResultList();

			purchaseOrder.setProductQuantity(null);
			for (I0016 re : resul) {
				product.add(re.getI0031().getI0001().getProductName());
				logger.info("product quan" + re.getQuantity());
				productQuantity.add(re.getQuantity());
				purchaseOrder.setProductQuantity(productQuantity);

			}
			purchaseOrder.setProductQuantity(productQuantity);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return product;
	}

	@Transactional(value = "transactionManager")
	public String addStockIn(PurchaseOrder purchaseOrder) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Date date = Calendar.getInstance().getTime();
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String today = formatter.format(date);
			logger.info("Today : " + today);

			Query q11 = null;
			q11 = entitymanager.createQuery("from I0019 where client_ID=? ");
			q11.setParameter(1, clientID);
			List<I0019> resultt = (List<I0019>) q11.getResultList();
			int count = 0;
			int c = 0;
			if (resultt.size() > 0) {
				logger.info("inside if");
				for (I0019 re : resultt) {
					count++;
				}
				c = count;
			}
			if (count == 0) {
				count++;
				String s = "ROLL-" + count;
				purchaseOrder.setRollID(s);
			} else {
				count++;
				String s = "ROLL" + count;
				purchaseOrder.setRollID(s);
			}

			Query q = null;
			q = entitymanager
					.createQuery("from I0016 where ordernumber=? and status3=? and client_ID=? ");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, "delivered");
			q.setParameter(3, clientID);
			List<I0016> result = (List<I0016>) q.getResultList();
			int id = 0;
			int stid = 0;
			int bid = 0;
			if (result.size() > 0) {
				int i = 0;
				id = result.get(i).getI0015().getPurchase_ID();
				Query qry = null;
				qry = entitymanager
						.createQuery("from I0018 where productName=? and status=? and client_ID=?");
				qry.setParameter(1, purchaseOrder.getProductName());
				qry.setParameter(2, "insert");
				qry.setParameter(3, clientID);
				List<I0018> resultqry = (List<I0018>) qry.getResultList();
				int batchId = 0;
				if (resultqry.size() > 0) {
					int k = 0;
					batchId = resultqry.get(k).getBatch_ID();
					I0019 roll = new I0019();
					roll.setRoll_Qunatity(purchaseOrder.getRollQuantity());
					roll.setRoll_status("Insert");
					roll.setStatus("barcode genterated");
					roll.setRollDate(formatter.parse(today));
					roll.setRoll_serialNo(purchaseOrder.getRollSerialNo());
					roll.setRoll_ID(purchaseOrder.getRollID());
					roll.setStock_In(purchaseOrder.getRollBatch());
					roll.setI0018(entitymanager.find(I0018.class, batchId));
					roll.setI0015(entitymanager.find(I0015.class, result.get(i)
							.getI0015().getPurchase_ID()));
					roll.setClient_ID(clientID);
					entitymanager.persist(roll);
					entitymanager.close();

				}
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String getRollQuantity(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q = null;
			q = entitymanager
					.createQuery("from I0019 where roll_ID=? and roll_status=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getRollID());
			q.setParameter(2, "insert");
			q.setParameter(3, clientID);
			List<I0019> result = (List<I0019>) q.getResultList();

			float stockin = (float) 0.0;
			if (result.size() > 0) {
				int k = 0;
				purchaseOrder.setRollStockIn(Float.parseFloat(result.get(k)
						.getStock_In()));
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String getProductQuntity(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<String> product = new ArrayList<String>();
		ArrayList<String> productQuantity = new ArrayList<String>();
		try {
			Query qunit = null;
			qunit = entitymanager
					.createQuery("from I0001 where product_name=? and client_ID=?");
			qunit.setParameter(1, purchaseOrder.getBatchProductName());
			qunit.setParameter(2, clientID);
			List<I0001> qunitresult = (List<I0001>) qunit.getResultList();
			String unit = "";
			if (qunitresult.size() > 0) {
				int j = 0;
				unit = qunitresult.get(j).getUnit();

				purchaseOrder.setUnit(unit);
			}

			Query q = null;
			q = entitymanager.createQuery("from I0015 where temOrderNumber=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, clientID);
			List<I0015> result = (List<I0015>) q.getResultList();
			int purchaseID = 0;
			if (result.size() > 0) {
				int j = 0;
				purchaseID = result.get(j).getPurchase_ID();
			}
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0016 where purchase_ID=? and status=? and client_ID=?");
			q1.setParameter(1, purchaseID);
			q1.setParameter(2, "inserted");
			q1.setParameter(3, clientID);
			List<I0016> resul = (List<I0016>) q1.getResultList();
			purchaseOrder.setRemaining("");
			for (I0016 re : resul)

			{
				if (purchaseOrder.getBatchProductName().equals(
						re.getI0031().getI0001().getProductName())) {
					purchaseOrder.setRemaining(re.getQuantity());
					productQuantity.add(re.getQuantity());
					purchaseOrder.setProductQuantity(productQuantity);
				}

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public List<I0032> customerNameChange11(String s1) throws DemoException {
		ArrayList<I0032> result1 = null;
		Query q40 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			q40 = entitymanager
					.createQuery("select phoneNumber from I0032 where customerName=? and status='in' and client_ID=?");
			q40.setParameter(1, s1);
			q40.setParameter(2, clientID);
			result1 = (ArrayList<I0032>) q40.getResultList();
			if (result1.size() < 0) {
				throw new DemoException("* no data found");
			}
		} finally {
			q40 = null;
		}
		return result1;
	}

	@Transactional(value = "transactionManager")
	public List<Employee> freenamelistser(List<Employee> name)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q11 = null;
		logger.info("==in==");
		q11 = entitymanager
				.createQuery("select employeeName from Employee where status='inserted' and freelancer='Yes' and client_ID=?");
		q11.setParameter(1, clientID);
		name = q11.getResultList();
		return name;
	}

	@Transactional(value = "transactionManager")
	public ArrayList<String> getAddRollList(String productName,
			ArrayList<String> rollList) throws DemoException {
		ArrayList<String> rolls = new ArrayList<String>();
		Query qry = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		qry = entitymanager.createQuery("from I0018 where productName=? and status=? and client_ID=?");
		qry.setParameter(1, productName);
		qry.setParameter(2, "insert");
		qry.setParameter(3, clientID);
		List<I0018> resultqry = (List<I0018>) qry.getResultList();
		int batchId = 0;
		if (resultqry.size() > 0) {
			int k = 0;
			batchId = resultqry.get(k).getBatch_ID();

			Query qry1 = null;
			qry1 = entitymanager
					.createQuery("from I0019 where batch_ID=? and roll_status=? and client_ID=?");
			qry1.setParameter(1, batchId);
			qry1.setParameter(2, "Insert");
			qry1.setParameter(3, clientID);
			List<I0019> resultqry1 = (List<I0019>) qry1.getResultList();
			for (I0019 re : resultqry1)

			{

				rolls.add(re.getRoll_ID());
			}

		} else {
			throw new DemoException("*This productName is not there");
		}
		return rolls;
	}

	@Transactional(value = "transactionManager")
	public String rollDamage(PurchaseOrder purchaseOrder) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q = null;
			q = entitymanager
					.createQuery("from I0019 where roll_ID=? and roll_status=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getRollID());
			q.setParameter(2, "insert");
			q.setParameter(3, clientID);
			List<I0019> result = (List<I0019>) q.getResultList();
			int barID = 0;
			int stockid = 0;
			int purchase_ID = 0;
			float temp = (float) 0.0;
			float stock = (float) 0.0;
			float damage = (float) 0.0;
			if (result.size() > 0) {
				barID = result.get(0).getBar_code_ID();
				stockid = result.get(0).getI0018().getI0017().getStock_ID();
				purchase_ID = result.get(0).getI0015().getPurchase_ID();
				stock = Float.parseFloat(result.get(0).getStock_In());
				damage = Float.parseFloat(purchaseOrder.getQuantity());
				temp = stock - damage;
				try {
					if (result.get(0).getRoll_damage().equalsIgnoreCase(null)
							|| result.get(0).getRoll_damage()
									.equalsIgnoreCase("")) {
						I0019 barcode = entitymanager.find(I0019.class, barID);
						barcode.setStock_In(String.valueOf(temp));
						if (temp == 0.0) {
							barcode.setRoll_status("Sold");
						}

						barcode.setDamgeStatus("Damaged");
						barcode.setRoll_damage(String.valueOf(damage));
						entitymanager.merge(barcode);
					} else {
						I0019 barcode = entitymanager.find(I0019.class, barID);
						barcode.setStock_In(String.valueOf(temp));
						if (temp == 0.0) {
							barcode.setRoll_status("Sold");
						}

						barcode.setDamgeStatus("Damaged");
						barcode.setRoll_damage(""
								+ (Float.parseFloat(result.get(0)
										.getRoll_damage()) + damage));
						entitymanager.merge(barcode);
					}
				} catch (NullPointerException n) {
					I0019 barcode = entitymanager.find(I0019.class, barID);
					barcode.setStock_In(String.valueOf(temp));
					if (temp == 0.0) {
						barcode.setRoll_status("Sold");
					}

					barcode.setDamgeStatus("Damaged");
					barcode.setRoll_damage(String.valueOf(damage));
					entitymanager.merge(barcode);
				}
				I0020 damagest = new I0020();
				damagest.setI0017(entitymanager.find(I0017.class, stockid));
				damagest.setI0015(entitymanager.find(I0015.class, purchase_ID));
				damagest.setClient_ID(clientID);
				entitymanager.persist(damagest);
			}

		} catch (Exception e) {
			logger.error("inside exception",e);
		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String changeDrop1(String s, PurchaseOrder dom) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String s1 = null;
		try {
			Query q = null;
			q = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
			q.setParameter(1, s);
			q.setParameter(2, clientID);
			List<I0001> result = (List<I0001>) q.getResultList();
			if (result.size() > 0) {
				dom.setProdcode(result.get(0).getProductWeight());
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return s1;
	}

	@Transactional(value = "transactionManager")
	public List<I0019> stockView3(List<I0019> batch2, String s,
			StockView stockView) throws DemoException {
		ArrayList<String> rolls = new ArrayList<String>();
		ArrayList<String> roll_quant = new ArrayList<String>();
		ArrayList<I0019> sample = new ArrayList<I0019>();
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		q = entitymanager
				.createQuery("from I0025 where vendorPhoneNumber=? and status=? and  client_ID=? ");
		logger.info("1");
		q.setParameter(1, s);
		q.setParameter(2, "i");
		q.setParameter(3, clientID);

		List<I0025> resultt = (List<I0025>) q.getResultList();
		int id = 0;
		int id1 = 0;
		if (resultt.size() > 0) {
			int i = 0;
			id = resultt.get(i).getVendor_ID();
		}

		Query q1 = null;
		logger.info("1");
		q1 = entitymanager
				.createQuery("from I0031 where vendor_ID=? and status=?");
		logger.info("2");
		q1.setParameter(1, id);
		q1.setParameter(2, "i");
		List<I0031> result11 = (List<I0031>) q1.getResultList();
		if (result11.size() > 0) {
			String id11 = "";
			for (int i = 0; i < result11.size(); i++) {
				id11 = result11.get(i).getI0001().getProductName();
				Query q21 = null;
				q21 = entitymanager
						.createQuery("from I0018 where productName=? and status=? and client_ID=? ");
				q21.setParameter(1, id11);
				q21.setParameter(2, "insert");
				q21.setParameter(3, clientID);
				List<I0018> res1 = (List<I0018>) q21.getResultList();
				int i0018Id = 0;
				if (res1.size() > 0) {
					i0018Id = res1.get(0).getBatch_ID();
					Query q2 = null;
					q2 = entitymanager
							.createQuery("from I0019 where batch_ID=? and (status=?) and client_ID=? ");
					q2.setParameter(1, i0018Id);
					q2.setParameter(2, "barcode genterated");
					q2.setParameter(3, clientID);
					batch2 = (List<I0019>) q2.getResultList();
					if (batch2.size() > 0) {
						for (I0019 in : batch2) {
							String roll_quantity = in.getQuantity();
							roll_quant.add(roll_quantity);
							sample.add(in);
							stockView.setS1(batch2.get(0).getI0018().getI0017()
									.getI0015().getI0016s().get(0).getI0031()
									.getI0025().getFirmName());
							stockView.setS2(batch2.get(0).getI0018().getI0017()
									.getI0015().getI0016s().get(0).getI0031()
									.getI0025().getVendorPhoneNumber());

						}

						Query q3 = null;
						q3 = entitymanager
								.createQuery("from I0001 where productName=? and client_ID=?");
						q3.setParameter(1, id11);
						q3.setParameter(2, clientID);
						List<I0001> resultproduct = (List<I0001>) q3
								.getResultList();
						if (resultproduct.size() > 0) {
							stockView.setBuyingPrice(resultproduct.get(0).getAutualPrice());
							stockView.setUnit(resultproduct.get(0).getUnit());
						}
					}

					stockView.setRoll_stock_in(roll_quant);
				}

			}
			stockView.setSample(sample);
		}

		return sample;

	}

	@Transactional(value = "transactionManager")
	public List<String> productVendor1(List<String> batchProductName3)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<String> resul = new ArrayList<String>();
		resul.add("");
		Query q = null;
		q = entitymanager
				.createQuery("select vendorPhoneNumber from I0025 where status='i' and client_ID=?");
		q.setParameter(1, clientID);
		List<String> batchProductName = q.getResultList();
		if (batchProductName.size() > 0) {
			int k = 0;
			for (String string : batchProductName) {

			}

		}
		logger.info("out");

		return batchProductName;

	}

	@Transactional(value = "transactionManager")
	public String changeUserName(String newuser, String invusername)
			throws DemoException {
		Query v = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			v = entitymanager.createQuery("from Login where loginUser=? and clientId=?");
			v.setParameter(1, newuser);
			v.setParameter(2, clientID);
			List<Login> login = (List<Login>) v.getResultList();
			if (login.size() > 0) {
				throw new DemoException("This UserName Already Exists");
			} else {
				v = null;
				v = entitymanager.createQuery("from Login where loginUser=? and clientId=?");
				v.setParameter(1, invusername);
				v.setParameter(2, clientID);
				List<Login> login1 = (List<Login>) v.getResultList();
				if (login1.size() > 0) {
					int loginId = 0;
					loginId = login1.get(0).getLoginId();
					Login newUser = entitymanager.find(Login.class, loginId);
					newUser.setLoginUser(newuser);
					entitymanager.merge(newUser);
				}
			}
		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String changeUserPassword(String newpasswrd, String invpassword)
			throws DemoException {
		Query v = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			v = entitymanager.createQuery("from Login where loginPassword=? and clientId=?");
			v.setParameter(1, invpassword);
			v.setParameter(2, clientID);
			List<Login> login = (List<Login>) v.getResultList();
			if (login.size() > 0) {
				int loginId = 0;
				loginId = login.get(0).getLoginId();
				Login newPassword = entitymanager.find(Login.class, loginId);
				newPassword.setLoginPassword(newpasswrd);
				entitymanager.merge(newPassword);
			}

		} finally {

		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String i0015Insert(PurchaseOrder opngstock) throws DemoException {
		Query q = null;
		List<I0018> batch = null;
		List<I0001> product = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q11 = null;
			q11 = entitymanager.createQuery("from I0015 where client_ID=?");
			q11.setParameter(1, clientID);
			List<I0015> resultt = (List<I0015>) q11.getResultList();
			int count = 0;
			int c = 0;
			if (resultt.size() > 0) {
				for (I0015 re : resultt) {
					count++;
				}
				c = count;
			}
			if (c == 0) {
				String s = "OS0" + 1;
				opngstock.setOrderNumber(s);
			} else {
				int j = c + 1;
				String s = "OS0" + j;
				opngstock.setOrderNumber(s);
			}
			I0015 purchase = new I0015();
			purchase.setQuantity(opngstock.getOpeningStock());
			purchase.setStatus("OpeningStock");
			purchase.setTemOrderNumber(opngstock.getOrderNumber());
			purchase.setTotalPrice("0");
			purchase.setOrderDate(opngstock.getDeliveredDate());
			purchase.setTargentDate(opngstock.getDeliveredDate());
			purchase.setClient_ID(clientID);
			entitymanager.persist(purchase);
		} finally {

		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String i0016Insert(PurchaseOrder opngstock) throws DemoException {
		Query q = null;
		Query q1 = null;
		Query q2 = null;
		List<I0015> li = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Date d = new Date();
		try {
			q1 = entitymanager
					.createQuery("from I0001 where productName=? and status='i' and client_ID=?");
			q1.setParameter(1, opngstock.getProduct_name());
			q1.setParameter(2, clientID);
			List<I0001> resul = (List<I0001>) q1.getResultList();
			int pID = 0;
			String batchname = "";
			if (resul.size() > 0) {
				int j = 0;
				pID = resul.get(j).getProduct_ID();
				batchname = resul.get(j).getBatch();
				opngstock.setBatchProductName(batchname);
			}
			String b = "" + pID;
			q2 = entitymanager.createQuery("from I0031 where product_ID=? ");
			q2.setParameter(1, b);
			List<I0031> resu = (List<I0031>) q2.getResultList();
			int hID = 0;
			if (resu.size() > 0) {
				int i = 0;
				hID = resu.get(i).getHas_vendor_ID();
			}
			Query a = entitymanager
					.createQuery("from I0015 where temOrderNumber=? and client_ID=?");
			a.setParameter(1, opngstock.getOrderNumber());
			a.setParameter(2, clientID);
			li = (ArrayList<I0015>) a.getResultList();
			int pid = 0;
			if (li.size() > 0) {
				pid = li.get(0).getPurchase_ID();
				opngstock.setPurchaseid(pid);
			}
			I0016 haspurchase = new I0016();
			haspurchase.setOrdernumber(opngstock.getOrderNumber());
			haspurchase.setStatus("OpeningStock");
			haspurchase.setApprovalStatus("OpeningStock");
			haspurchase.setOrderDate(opngstock.getDeliveredDate());
			haspurchase.setStatus2("OpeningStock");
			haspurchase.setStatus3("OpeningStock");
			haspurchase.setStatus4("OpeningStock");
			haspurchase.setQuantity(opngstock.getOpeningStock());
			haspurchase.setI0031(entitymanager.find(I0031.class, hID));
			haspurchase.setI0015(entitymanager.find(I0015.class, pid));
			haspurchase.setI0001(entitymanager.find(I0001.class, pID));
			haspurchase.setClient_ID(clientID);
			entitymanager.persist(haspurchase);
			logger.info("i0017 insert --- >>");
			I0017 stock = new I0017();
			logger.info("1");
			stock.setI0015(entitymanager.find(I0015.class, pid));
			stock.setPurchaseDate(d);
			stock.setStockInDate(d);
			entitymanager.persist(stock);
		} catch (Exception ie) {
			logger.error("Inside Exception", ie);
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String i0018Insert(PurchaseOrder opngstock) throws DemoException {
		Query q = null, s = null;
		List<I0018> batch = null;
		List<I0017> stock = null;
		I0018 batchdata = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		int bid = 0;
		int sid = 0;
		try {
			s = entitymanager.createQuery("from I0017 where purchase_ID=?");
			s.setParameter(1, opngstock.getPurchaseid());
			stock = (ArrayList<I0017>) s.getResultList();
			if (stock.size() > 0) {
				sid = stock.get(0).getStock_ID();
				logger.info("i0017 id -- >> " + sid);
				q = entitymanager.createQuery("from I0018 where productName=? and client_ID=?");
				q.setParameter(1, opngstock.getProduct_name());
				q.setParameter(2, clientID);
				batch = (ArrayList<I0018>) q.getResultList();
				if (batch.size() > 0) {
					bid = batch.get(0).getBatch_ID();
					opngstock.setBatchID(bid);
					batchdata = entitymanager.find(I0018.class, bid);
					batchdata.setI0017(entitymanager.find(I0017.class, sid));
					entitymanager.merge(batchdata);
				} else {
					q = null;
					batch = null;
					q = entitymanager.createQuery("from I0018 where client_ID=?");
					q.setParameter(1, clientID);
					batch = (ArrayList<I0018>) q.getResultList();
					batchdata = new I0018();
					batchdata.setProductName(opngstock.getProduct_name());
					batchdata.setBatchName(opngstock.getBatchProductName());
					batchdata.setUnitPrice("" + opngstock.getActualPrice());
					batchdata.setStatus("insert");
					batchdata.setClient_ID(clientID);
					batchdata.setI0017(entitymanager.find(I0017.class, sid));
					entitymanager.persist(batchdata);
					q = null;
					batch = null;
					q = entitymanager
							.createQuery("from I0018 where productName=? and client_ID=?");
					q.setParameter(1, opngstock.getProduct_name());
					q.setParameter(2, clientID);
					batch = (ArrayList<I0018>) q.getResultList();
					if (batch.size() > 0) {
						bid = 0;
						bid = batch.get(0).getBatch_ID();
						opngstock.setBatchID(bid);
					}
				}
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String rollInsertI0019(PurchaseOrder opngstock) throws DemoException {
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			Date date = Calendar.getInstance().getTime();
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String today = formatter.format(date);
			Query q11 = null;
			q11 = entitymanager.createQuery("from I0019 where client_ID=?");
			q11.setParameter(1, clientID);
			List<I0019> resultt = (List<I0019>) q11.getResultList();
			int count = 0;
			int c = 0;
			if (resultt.size() > 0) {
				for (I0019 re : resultt) {
					count++;
				}
				c = count;
			}
			if (count == 0) {
				count++;
				String s = "ROLL-" + count;
				opngstock.setRollID(s);
			} else {
				count++;
				String s = "ROLL" + count;
				opngstock.setRollID(s);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String openingStockInsert(int batchid, PurchaseOrder opngstock)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q5 = null;
		BigDecimal opstock = BigDecimal.valueOf(0);
		String stockq = "";
		int newid = 0;
		q5 = entitymanager
				.createQuery("from I0019 where batch_ID=? and status=? and client_ID=?");
		q5.setParameter(1, batchid);
		q5.setParameter(2, "barcode genterated");
		q5.setParameter(3, clientID);
		List<I0019> list19 = (List<I0019>) q5.getResultList();
		if (list19.size() > 0) {
			logger.info("-->> if");
			newid = list19.get(0).getBar_code_ID();
			stockq = list19.get(0).getQuantity();
			logger.info("-->> stockq " + stockq);
			opstock = new BigDecimal(opngstock.getOpeningStock())
					.add(new BigDecimal(stockq));
			I0019 barcode = entitymanager.find(I0019.class, newid);
			barcode.setI0018(entitymanager.find(I0018.class, batchid));
			barcode.setStatus("barcode genterated");
			barcode.setDamgeStatus("0");
			barcode.setStatus2("i");

			barcode.setI0015(entitymanager.find(I0015.class,
					opngstock.getPurchaseid()));
			barcode.setQuantity(""
					+ (opstock.setScale(2, BigDecimal.ROUND_HALF_UP)));
			entitymanager.merge(barcode);
		} else {
			logger.info("-->> else");
			opstock = new BigDecimal(opngstock.getOpeningStock());
			I0019 barcode = new I0019();
			barcode.setI0018(entitymanager.find(I0018.class, batchid));
			barcode.setStatus("barcode genterated");
			barcode.setStatus2("i");
			barcode.setDamgeStatus("0");

			barcode.setI0015(entitymanager.find(I0015.class,
					opngstock.getPurchaseid()));
			barcode.setQuantity(""
					+ (opstock.setScale(2, BigDecimal.ROUND_HALF_UP)));
			barcode.setClient_ID(clientID);
			entitymanager.persist(barcode);
		}
		return null;
	}

	@Override
	public String getpurchaseView(PurchaseOrder purchaseOrder)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {

			logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseOrdercancel Dao-------------$$$$$$$$$$$$$$-----------");
			List<PurchaseOrder> resulView1 = new ArrayList<PurchaseOrder>();
			Query q = null;

			ArrayList<PurchaseOrder> result4 = new ArrayList<PurchaseOrder>();
			ArrayList<I0015> result = null;
			logger.info("--------------$$$$$$$$$$$$$$------------inside purchaseView dao-------------$$$$$$$$$$$$$$-----------");
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0015 where purchase_ID=?");
			q1.setParameter(1, purchaseOrder.getPurchaseid());
			result = (ArrayList<I0015>) q1.getResultList();
			if (result.size() > 0) {
				q = entitymanager
						.createQuery("from I0016 where purchase_ID=?");
				q.setParameter(1, result.get(0).getPurchase_ID());
				List<I0016> purchaselist = (ArrayList<I0016>) q.getResultList();

				if (purchaselist.size() == 0) {

				} else {

					int count = 0;
					BigDecimal valref = new BigDecimal("0");
					for (I0016 i0016 : purchaselist) {
						try {

							PurchaseOrder Order = new PurchaseOrder();
							count = count + 1;
							Order.setSno1(count);
							Order.setProduct_name(i0016.getI0031().getI0001()
									.getProductName());
							Order.setQuantity(i0016.getQuantity());
							Order.setPrice(i0016.getQuantityTotal());
							Order.setUnit(i0016.getI0031().getI0001().getUnit());
							Order.setTotalPrice(i0016.getI0015()
									.getTotalPrice());
							Order.setFirmName(i0016.getI0031().getI0025()
									.getFirmName());
							Order.setVendorPhoneNumber(i0016.getI0031()
									.getI0025().getVendorPhoneNumber());
							Order.setOrderDate(i0016.getI0015().getOrderDate());
							Order.setTargentDate(i0016.getI0015()
									.getTargentDate());
							Order.setStatus3(i0016.getStatus3());
							Order.setStatus2(i0016.getStatus2());
							valref = new BigDecimal(i0016.getI0031().getI0001()
									.getAutualPrice()).multiply(new BigDecimal(
									i0016.getQuantity()));
							Order.setNetAmount(valref.toString());
							Order.setDestinationCurrency(i0016.getI0015().getCurrencyType());
							resulView1.add(Order);

						} catch (IndexOutOfBoundsException in) {
						}
						purchaseOrder.setResulfinal(resulView1);
					}

				}
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String purchaseReturnInsert(PurchaseOrder purchaseOrder)
			throws DemoException {
		Date d = new Date();
		Query q = null;
		Query q1 = null;
		Query q2 = null;
		Query q3 = null;
		Query q4 = null;
		Query q5 = null;
		Query q6 = null;
		List<PurchaseReturn> list1 = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<PurchaseReturnFormMB> mblist = new ArrayList<PurchaseReturnFormMB>();
		PurchaseOrder p1 = new PurchaseOrder();
		try {
			logger.info(" dao - 1");
			int prodVen = 0;
			int prodid = 0;
			int purId = 0;
			int barId = 0;
			List<I0015> list15 = null;
			String price = "";
			int newid = 0;
			int bid = 0;
			int spid = 0;
			int pqty15 = 0;
			String pamount15 = "";
			String pid = null;
			q1 = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
			q1.setParameter(1, purchaseOrder.getProduct_name());
			q1.setParameter(2, clientID);
			List<I0001> list01 = (List<I0001>) q1.getResultList();
			if (list01.size() > 0) {
				price = list01.get(0).getAutualPrice();
				prodid = list01.get(0).getProduct_ID();
				q2 = entitymanager.createQuery("from I0031 where product_ID=?");
				q2.setParameter(1, prodid);
				List<I0031> list31 = (List<I0031>) q2.getResultList();
				if (list31.size() > 0) {
					prodVen = list31.get(0).getHas_vendor_ID();
				}
				q3 = entitymanager
						.createQuery("from I0015 where temOrderNumber=? and client_ID=?");
				q3.setParameter(1, purchaseOrder.getOrderNumber());
				q3.setParameter(2, clientID);
				list15 = (List<I0015>) q3.getResultList();
				if (list15.size() > 0) {
					purId = list15.get(0).getPurchase_ID();
					pamount15 = list15.get(0).getTotalPrice();
				}
			}
			int iid = 0;
			q = entitymanager
					.createQuery("from I0016 where ordernumber=? and has_vendor_ID=? and client_ID=?");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			q.setParameter(2, prodVen);
			q.setParameter(3, clientID);
			List<I0016> list16 = (List<I0016>) q.getResultList();
			if (list16.size() > 0) {
				iid = list16.get(0).getHas_purchase_ID();
				spid = list16.get(0).getHas_purchase_ID();
				pid = list16.get(0).getQuantity();
			}
			String totprice = "";
			String totalPrice = null;
			totprice = ""
					+ (new BigDecimal(pid).multiply(new BigDecimal(price)));
			totalPrice = totprice;
			q4 = entitymanager.createQuery("from I0018 where productName=? and client_ID=?");
			q4.setParameter(1, purchaseOrder.getProduct_name());
			q4.setParameter(2, clientID);
			List<I0018> list18 = (List<I0018>) q4.getResultList();
			bid = list18.get(0).getBatch_ID();
			logger.info(" -->> bid " + bid);
			float i1 = (float) 0.0;
			float i2 = (float) 0.0;
			if (purchaseOrder.getTotalQuan1().equalsIgnoreCase("")) {
				i1 = (float) 0.0;
				purchaseOrder.setTotalQuan1("0");
			} else {
				i1 = Float.parseFloat(purchaseOrder.getTotalQuan1());
				if (i1 > 0.0) {
					q5 = entitymanager
							.createQuery("from I0019 where batch_ID=? and status=? and client_ID=?");
					q5.setParameter(1, bid);
					q5.setParameter(2, "barcode genterated");
					q5.setParameter(3, clientID);
					List<I0019> list19 = (List<I0019>) q5.getResultList();
					if (list19.size() > 0) {
						String purquant = null;
						String newquant = null;
						String rollquant = null;
						String rolldamage = null;
						purquant = list19.get(0).getQuantity();
						barId = list19.get(0).getBar_code_ID();
						newquant = ""
								+ new BigDecimal(purquant)
										.subtract(new BigDecimal(purchaseOrder
												.getTotalQuan1()));
						if ((Float.parseFloat(newquant) == 0.0)
								|| (Float.parseFloat(newquant) == 0)) {
							newid = list19.get(0).getBar_code_ID();
							I0019 ID = entitymanager.find(I0019.class, newid);
							ID.setQuantity(newquant);
							entitymanager.merge(ID);
						} else {
							newid = list19.get(0).getBar_code_ID();
							I0019 ID = entitymanager.find(I0019.class, newid);
							ID.setQuantity(newquant);
							entitymanager.merge(ID);
						}
					}
				}
			}
			if (purchaseOrder.getTotalQuan2().equalsIgnoreCase("")) {
				i2 = (float) 0.0;
				purchaseOrder.setTotalQuan2("0");
			} else {
				i2 = Float.parseFloat(purchaseOrder.getTotalQuan2());
				if (i2 > 0.0) {
					q6 = entitymanager
							.createQuery("from I0019 where batch_ID=? and status=? and client_ID=?");
					q6.setParameter(1, bid);
					q6.setParameter(2, "barcode genterated");
					q6.setParameter(3,clientID);
					List<I0019> list19 = (List<I0019>) q6.getResultList();

					if (list19.size() > 0) {
						String purquant = null;
						String newquant = null;
						purquant = list19.get(0).getQuantity();
						newquant = ""
								+ new BigDecimal(purquant)
										.subtract(new BigDecimal(purchaseOrder
												.getTotalQuan2()));
						barId = list19.get(0).getBar_code_ID();
						if ((Float.parseFloat(newquant) == 0.0)
								|| (Float.parseFloat(newquant) == 0)) {
							newid = list19.get(0).getBar_code_ID();
							I0019 ID = entitymanager.find(I0019.class, newid);
							ID.setQuantity(newquant);
							entitymanager.merge(ID);
						} else {
							newid = list19.get(0).getBar_code_ID();
							I0019 ID = entitymanager.find(I0019.class, newid);
							ID.setQuantity(newquant);
							entitymanager.merge(ID);
						}
					}
				}
			}

			PurchaseReturn ret = new PurchaseReturn();
			ret.setNormalReturn(purchaseOrder.getTotalQuan1());
			ret.setDamageReturn(purchaseOrder.getTotalQuan2());
			ret.setQuantity(pid);
			ret.setPrice(totalPrice);
			ret.setReturnDate(d);
			ret.setPurchaseOrderNumber(purchaseOrder.orderNumber);
			ret.setProductName(purchaseOrder.product_name);
			ret.setI0015(entitymanager.find(I0015.class, purId));
			ret.setI0019(entitymanager.find(I0019.class, barId));
			ret.setClient_ID(clientID);
			entitymanager.persist(ret);
			float ff = 0;
			ff = Float.parseFloat("" + purchaseOrder.getQ());

			if (new BigDecimal(pid).compareTo(new BigDecimal(ff)) == 0) {
				I0016 ID = entitymanager.find(I0016.class, iid);
				ID.setStatus4("Returned");
				entitymanager.merge(ID);

			}
			Query qq = null;
			qq = entitymanager
					.createQuery("from PurchaseReturn where purchase_id=? and client_ID=?");
			qq.setParameter(1, purId);
			qq.setParameter(2, clientID);
			List<PurchaseReturn> prlist = (List<PurchaseReturn>) qq.getResultList();
			Query ap = null;
			ap = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
			ap.setParameter(1, prlist.get(0).getProductName());
			ap.setParameter(2, clientID);
			List<I0001> prdlist = (List<I0001>) ap.getResultList();

			BigDecimal rq = BigDecimal.valueOf(0);
			BigDecimal aprice = BigDecimal.valueOf(0);
			if (prlist.size() > 0) {
				for (int i = 0; i < prlist.size(); i++) {
					rq = new BigDecimal(prlist.get(i).getNormalReturn())
							.add(new BigDecimal(prlist.get(i).getDamageReturn()));
					aprice = aprice.add(rq.multiply(new BigDecimal(prdlist.get(
							0).getAutualPrice())));
				}
				purchaseOrder
						.setA(new BigDecimal(list15.get(0).getTotalPrice())
								.subtract(aprice));
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {

		}
		return "";
	}

	@Override
	@Transactional(value = "transactionManager")
	public String purchaseReturnInsert1(PurchaseOrder purchaseOrder) {
		BigDecimal paymount = BigDecimal.valueOf(0);
		BigDecimal namount = BigDecimal.valueOf(0);
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			Query q = null;
			q = entitymanager.createQuery("from I0015 where temOrderNumber=? ");
			q.setParameter(1, purchaseOrder.getOrderNumber());
			List<I0015> list15 = (List<I0015>) q.getResultList();
			logger.info("size of list15" + list15.size());

			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0022 where purchase_ID=? and status='purchase Invoice' and client_ID=?");
			q1.setParameter(1, list15.get(0).getPurchase_ID());
			q1.setParameter(2, clientID);
			List<I0022> list16 = (List<I0022>) q1.getResultList();
			if (list16.size() > 0) {
				Query q2 = null;
				q2 = entitymanager.createQuery("from I0023 where invoice_ID=? and client_ID=?");
				q2.setParameter(1, list16.get(0).getInvoice_ID());
				q2.setParameter(2, clientID);
				List<I0023> list17 = (List<I0023>) q2.getResultList();
				logger.info("size of list17" + list17.size());

				int payid = 0;
				String ap = "" + purchaseOrder.getA();
				if (list17.size() > 0) {
					payid = list17.get(0).getPayment_ID();
					if (list17.get(0).getStatus().equals("pending")) {
						I0023 i0023 = entitymanager.find(I0023.class, payid);
						paymount = new BigDecimal(i0023.getPayableAmount());
						namount = paymount.subtract(purchaseOrder.getA());
						i0023.setPayableAmount("" + ap);
						i0023.setBalanceAmount(""
								+ ((purchaseOrder.getA())
										.subtract(new BigDecimal(list17.get(0)
												.getPaidAmount()))));
						entitymanager.merge(i0023);
						String namt = "" + purchaseOrder.getA();
						if (Integer.parseInt(list17.get(0).getPayableAmount()) <= Integer
								.parseInt(namt)) {
							if (Integer.parseInt(list17.get(0).getPaidAmount()) > Integer
									.parseInt(list17.get(0).getPayableAmount())) {
								I0023 i023 = entitymanager.find(I0023.class,
										payid);
								i023.setStatus("paid");
								entitymanager.merge(i023);
							}
						} else if (Integer.parseInt(list17.get(0)
								.getPayableAmount()) > Integer.parseInt(namt)) {
							if (Integer.parseInt(list17.get(0).getPaidAmount()) > Integer
									.parseInt(namt)) {
								I0023 i023 = entitymanager.find(I0023.class,
										payid);
								i023.setStatus("paid");
								entitymanager.merge(i023);
							}
						}
					} else if (list17.get(0).getStatus().equals("paid")) {
						I0023 i23 = entitymanager.find(I0023.class, payid);
						paymount = new BigDecimal(i23.getPayableAmount());
						namount = paymount.subtract(purchaseOrder.getA());
						i23.setPayableAmount("" + purchaseOrder.getA());
						entitymanager.merge(i23);
					}
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return null;

	}

	@Transactional(value = "transactionManager")
	public String purchaseReturnValuechangeDrop1(String s,
			PurchaseOrder purchaseOrder) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			String phNo;
			List<PurchaseOrder> domainlist = new ArrayList<PurchaseOrder>();
			LinkedList<Integer> allpurID = new LinkedList<Integer>();
			List<I0015> purchaseresul = null;
			List<String> finallist = new ArrayList<String>();
			List<String> finallist2 = new ArrayList<String>();
			logger.info("inside changedrop dao:::::::::");
			logger.info("string:::" + s);

			Query q = null;
			Query qpurchase = null;
			q = entitymanager
					.createQuery("from I0025 where vendorPhoneNumber=? and status='i' and client_ID=?");
			q.setParameter(1, s);
			q.setParameter(2, clientID);
			List<I0025> result = (List<I0025>) q.getResultList();
			int fID = 0;
			int HVID = 0;
			int PID = 0;
			if (result.size() > 0) {
				int j = 0;
				phNo = result.get(0).getVendorTelephoneNumber();
				PurchaseOrder po = new PurchaseOrder();
				po.setVendorTelephoneNumber(phNo);
				domainlist.add(po);
				purchaseOrder.setDomainlist(domainlist);
				fID = result.get(j).getVendor_ID();
			}
			String a = "" + fID;
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from I0031 where vendor_ID=? and status=?");
			q1.setParameter(1, a);
			q1.setParameter(2, "i");
			logger.info("15");
			List<I0031> resul = (List<I0031>) q1.getResultList();
			if (resul.size() > 0) {

				for (int vid = 0; vid < resul.size(); vid++) {
					HVID = resul.get(vid).getHas_vendor_ID();
					logger.info("16");
					String b = "" + HVID;
					Query qhas = null;
					qhas = entitymanager
							.createQuery("from I0016 where has_vendor_ID=? and client_ID=?");
					qhas.setParameter(1, b);
					qhas.setParameter(2, clientID);
					List<I0016> hasresul = (List<I0016>) qhas.getResultList();
					if (hasresul.size() > 0) {
						int kk = 0;
						for (kk = 0; kk < hasresul.size(); kk++) {
							PID = hasresul.get(kk).getI0015().getPurchase_ID();
							allpurID.add(PID);
						}
						logger.info("All purchase IDs are" + allpurID);
						HashSet<Integer> hashlist1 = new HashSet<Integer>(
								allpurID);
						logger.info("All purchase IDs are" + hashlist1);
					}
				}
			}

			int i;
			if (allpurID.size() > 0) {
				for (i = 0; i < allpurID.size(); i++) {

					String c = "" + allpurID.get(i);
					qpurchase = entitymanager
							.createQuery("from I0015 where purchase_ID=? and status=? and client_ID=?");
					qpurchase.setParameter(1, allpurID.get(i));
					qpurchase.setParameter(2, "delivered");
					qpurchase.setParameter(3, clientID);
					purchaseresul = (List<I0015>) qpurchase.getResultList();
					if (purchaseresul.size() > 0) {
						int k, count = 0;
						for (k = 0; k < purchaseresul.size(); k++) {
							count++;
							finallist.add(purchaseresul.get(k)
									.getTemOrderNumber());
						}
					}

				}
			}
			HashSet<String> hashlist2 = new HashSet<String>(finallist);
			List<String> templist = new ArrayList<String>();
			for (String hh : hashlist2) {
				templist.add(hh);
			}
			purchaseOrder.setFinallist2(templist);
		} catch (Exception ie) {
			logger.info(ie.getMessage());
		}

		return "";
	}

	// prema 02/05/2016 customer view
	@Transactional(value = "transactionManager")
	public List<I0032> getBuyercustInfo(Buyer b) throws DemoException {
		Query q = null;
		List<I0032> resultList = null;
		/*String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");*/
		try {
			if(b.getUserType().equalsIgnoreCase("Maker")){
				q = entitymanager.createQuery("from I0032 where status='in' and client_ID=? and user_ID=? ORDER BY createdDate DESC");
				q.setParameter(1, b.getClientID());
				q.setParameter(2, b.getUserID());
			}else{
				if (b.getApproval()=="ApprovalData") {
					q = entitymanager.createQuery("from I0032 where status='in' and client_ID=? and approvalStatus='draft' ORDER BY createdDate DESC");
					q.setParameter(1, b.getClientID());
				}
				else{
				q = entitymanager.createQuery("from I0032 where status='in' and client_ID=? ORDER BY createdDate DESC");
				q.setParameter(1, b.getClientID());
				}
			}
			resultList = q.getResultList();
			if (resultList.size() == 0) {
				resultList = q.getResultList();
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return resultList;
	}

	// prema end 02/05/2016

	@Transactional(value = "transactionManager")
	public List<I0032> getBuyercustInfo(Buyer b, String golbalnamesearch)
			throws DemoException {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0032> resultList = null;
		try {
			q = entitymanager
					.createQuery("from I0032 where customerName=? and status='in' and client_ID=?");
			q.setParameter(1, golbalnamesearch);
			q.setParameter(2, clientID);
			resultList = q.getResultList();
			if (resultList.size() == 0) {
				resultList = q.getResultList();
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return resultList;
	}

	@Transactional(value = "transactionManager")
	public List<I0032> getFreeLancerInfo(String freeLancerName)
			throws DemoException {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0032> custInf = null;
		try {
			q = entitymanager
					.createQuery("select o from I0032 o where o.freelancerName like :x and o.status='in' and o.client_ID like :y");
			q.setParameter("x", "%" + freeLancerName + "%");
			q.setParameter("y", "%" + clientID + "%");
			custInf = q.getResultList();
			if (custInf.size() == 0) {
				custInf = q.getResultList();
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
		}
		return custInf;
	}

	// prema 29/04/2016 vendor view
	@Transactional(value = "transactionManager")
	public ArrayList<I0025> vendorView(Vendor vendor) throws DemoException {
		ArrayList<I0025> res = null;
		/*String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");*/
		res = new ArrayList<I0025>();
		Query q = null;
		try {
			if(vendor.getUserType().equalsIgnoreCase("Maker")){
				q = entitymanager.createQuery("from I0025 where status='i' and client_ID=? and user_ID=? ORDER BY createdDate DESC");
				q.setParameter(1, vendor.getClientID());
				q.setParameter(2, vendor.getUserID());
			}else{
				if (vendor.getApproval()=="ApprovalData") {
					q = entitymanager.createQuery("from I0025 where status='i' and client_ID=? and approvalStatus='draft' ORDER BY createdDate DESC");
					q.setParameter(1, vendor.getClientID());
				}
				else{
					q = entitymanager.createQuery("from I0025 where status='i' and client_ID=? ORDER BY createdDate DESC");
					q.setParameter(1, vendor.getClientID());
				}
			}
			ArrayList<I0025> result = (ArrayList<I0025>) q.getResultList();
			logger.info("dao size" + result.size());
			if (result.size() == 0) {
				logger.info("size" + result.size());
				vendor.setVendorlist(result);

			} else {
				vendor.setVendorlist(result);
				res.addAll(result);
			}
		} catch (Exception e) {
						logger.error("Error --------------->"+e.getMessage());
			logger.error("Inside Exception", e);
		}
		return res;

	}

	@Override
	public ArrayList<I0025> vendorView1(Vendor vendor, String golbalnamesearch)
			throws DemoException {

		ArrayList<I0025> res = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		res = new ArrayList<I0025>();
		Query q = null;
		try {
			q = entitymanager.createQuery("from I0025 where vendorPhoneNumber=? and status='i' and client_ID=?");
			q.setParameter(1, golbalnamesearch);
			q.setParameter(2, clientID);
			ArrayList<I0025> result = (ArrayList<I0025>) q.getResultList();
			if (result.size() == 0) {
				logger.info("size" + result.size());
				vendor.setVendorlist(result);

			} else {
				vendor.setVendorlist(result);
				res.addAll(result);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return res;
	}

	@Transactional(value = "transactionManager")
	public String searchCity(Vendor vendor) throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		q = entitymanager
				.createQuery("from I0025 where cityName=? and status='i' and client_ID=?");
		q.setParameter(1, vendor.getCities());
		q.setParameter(2, clientID);
		ArrayList<I0025> result = (ArrayList<I0025>) q.getResultList();
		if (result.size() == 0) {
			vendor.setVendorlist(null);

		}
		vendor.setVendorlist(result);
		return null;
	}

	@Transactional(value = "transactionManager")
	public List<I0001> productView2(List<I0001> i0001s,
			ProductRegister productRegister, String golbalnamesearch)
			throws DemoException {
		ArrayList<I0031> temp = new ArrayList<I0031>();
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		q = entitymanager
				.createQuery("from I0001 where productName=? and status='i' and client_ID=?");
		q.setParameter(1, golbalnamesearch);
		q.setParameter(2, clientID);
		i0001s = (List<I0001>) q.getResultList();
		int i = 0;
		int count = 0;
		if (i0001s.size() > 0) {
			for (I0001 i0001 : i0001s) {
				i = i0001.getProduct_ID();
				Query q1 = null;
				q1 = entitymanager
						.createQuery("from I0031  where product_ID=? ");
				q1.setParameter(1, i);
				ArrayList<I0031> result = (ArrayList<I0031>) q1.getResultList();
				if (result.size() > 0) {
					for (I0031 i0031 : result) {
						count++;
						temp.add(i0031);
					}
				}

			}
			productRegister.setJoin(temp);

		}

		return i0001s;
	}

	@Transactional(value = "transactionManager")
	public List<I0001> productView1(List<I0001> i0001s,
			ProductRegister productRegister) throws DemoException {
		ArrayList<I0031> temp = new ArrayList<I0031>();
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		if(userType.equalsIgnoreCase("Maker")){
			q = entitymanager.createQuery("from I0001 where status='i' and client_ID=? and user_ID=? ORDER BY createdDate DESC");
			q.setParameter(1, clientID);
			q.setParameter(2, userID);
		}else{
			if (productRegister.getApproval()=="ApprovalData") {
				q = entitymanager.createQuery("from I0001 where status='i' and client_ID=? and approvalStatus='draft' ORDER BY createdDate DESC");
				q.setParameter(1, clientID);
			}
			else {
				q = entitymanager.createQuery("from I0001 where status='i' and client_ID=? ORDER BY createdDate DESC");
				q.setParameter(1, clientID);
			}
			
		}
		i0001s = (List<I0001>) q.getResultList();
		int i = 0;
		int count = 0;
		if (i0001s.size() > 0) {
			for (I0001 i0001 : i0001s) {
				i = i0001.getProduct_ID();
				Query q1 = null;
				q1 = entitymanager
						.createQuery("from I0031  where product_ID=? ");
				q1.setParameter(1, i);
				ArrayList<I0031> result = (ArrayList<I0031>) q1.getResultList();
				if (result.size() > 0) {
					for (I0031 i0031 : result) {
						count++;
						temp.add(i0031);
					}
				}

			}
			productRegister.setJoin(temp);

		}
		return i0001s;
	}

	@Override
	public String getunitprice(String productName, String unitprice)
			throws DemoException {
		try {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			Query q1 = null;
			String Untprc = "";
			q1 = entitymanager
					.createQuery("from I0001 where productName=? and status='i' and client_ID=?");
			q1.setParameter(1, productName);
			q1.setParameter(2, clientID);
			List<I0001> price1 = (List<I0001>) q1.getResultList();
			if (price1.size() > 0) {
				unitprice = price1.get(0).getAutualPrice();
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		// TODO Auto-generated method stub
		return unitprice;

	}

	@Transactional(value = "transactionManager")
	public String salesRecordView(PurchaseOrder purchaseOrder)
			throws DemoException {
		List<PurchaseOrder> resulfinal1 = new ArrayList<PurchaseOrder>();
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		logger.info("purorder number" + purchaseOrder.getSalesIdReference());
		q = entitymanager
				.createQuery("from I0021 where salesOrderNumber=? and client_ID=?");
		q.setParameter(1, purchaseOrder.getSalesIdReference());
		q.setParameter(2, clientID);
		List<I0021> results = (List<I0021>) q.getResultList();
		if (results.size() > 0) {
			purchaseOrder.setStatus(results.get(0).getStatus());
			q = entitymanager
					.createQuery("from I0021 where salesOrderNumber=? and (status='Quick sales' or status='Delivered' or status='cancelled') and client_ID=?");
			q.setParameter(1, purchaseOrder.getSalesIdReference());
			q.setParameter(2, clientID);
			List<I0021> result = (List<I0021>) q.getResultList();
			int pkid = 0;
			int i = 0;
			Date due;
			if (result.size() > 0) {
			int serialNo = 0;
			pkid = result.get(i).getSales_ID();
			purchaseOrder.setTelephonenumber(result.get(0).getPhoneNumber());
			purchaseOrder.setCustomerName(result.get(0).getCustomerName());			
			Query q1 = null;
			q1 = entitymanager
					.createQuery("from SalesRecord where sales_ID=? and (soldStatus='solded' or soldStatus='delivered') and client_ID=?");
			q1.setParameter(1, pkid);
			q1.setParameter(2, clientID);
			List<SalesRecord> resul = (List<SalesRecord>) q1.getResultList();
			if (resul.size() > 0) {
				int cc = 0;
				serialNo++;
				String temp = "";
				int ii = 0;
				String temp1 = "";
				for (SalesRecord rec : resul) {
					PurchaseOrder Order = new PurchaseOrder();
					if (rec.getSoldQuantity().equalsIgnoreCase(
							rec.getReturnQuantity())) {
						cc++;
					} else {

						int counter = 1;
						String s = rec.getI0019().getI0018().getProductName();
						String price = rec.getSell_price();

						try {
							temp = s;
							
							Order.setSerialNo(serialNo);
							Order.setProduct_name(rec.getI0019().getI0018()
									.getProductName());
							Order.setOrderDate(result.get(0)
									.getSalesOrderDate());
							Order.setBatchProductName(rec.getI0019().getI0018()
									.getBatchName());

							String unit = "";
							Query qunit = null;
							qunit = entitymanager
									.createQuery("from I0001 where batch=? and client_ID=?");
							qunit.setParameter(1, rec.getI0019().getI0018()
									.getBatchName());
							qunit.setParameter(2, clientID);
							List<I0001> runit = (List<I0001>) qunit
									.getResultList();
							if (runit.size() > 0) {
								unit = runit.get(0).getUnit();
							}
							Order.setRollID(rec.getI0019().getRoll_ID());

							Order.setQuantity(rec.getSoldQuantity());
							Order.setUnit(unit);
							purchaseOrder.setSalesIdReference(result.get(0)
									.getSalesOrderNumber());
							counter = 1;

							resulfinal1.add(Order);
							serialNo++;
						} catch (IndexOutOfBoundsException ind) {
							logger.info(ind.getMessage());
						}
					}

					purchaseOrder.setResulfinal(resulfinal1);
				}

				if (cc == resul.size()) {
					throw new DemoException(
							"*Already returned all products for this SO");
				}
				purchaseOrder.setResul1(resul);
				purchaseOrder.setResult(result);

			} else {
				Query q11 = null;
				q11 = entitymanager
						.createQuery("from SalesRecord where sales_ID=? and (soldStatus='Solded' or soldStatus='delivered') and client_ID=?");
				q11.setParameter(1, pkid);
				q11.setParameter(2, clientID);
				List<SalesRecord> res = (List<SalesRecord>) q11.getResultList();
				if (res.size() > 0) {

					String temp = "";
					int ii = 0;
					String temp1 = "";
					for (SalesRecord rec1 : res) {
						PurchaseOrder Order = new PurchaseOrder();
						{
							int counter = 1;

							String s = rec1.getI0019().getI0018()
									.getProductName();

							if (s == temp) {
								counter++;
								serialNo++;
								Order.setQuantity("" + counter);
								Order.setSerialNo(serialNo);
							} else {
								temp = s;
								Order.setProduct_name(rec1.getI0019()
										.getI0018().getProductName());
								Order.setOrderDate(result.get(0)
										.getSalesOrderDate());
								Order.setBatchProductName(rec1.getI0019()
										.getI0018().getBatchName());

								String unit = "";
								Query qunit1 = null;
								qunit1 = entitymanager
										.createQuery("from I0001 where batch=? and client_ID=?");
								qunit1.setParameter(1, rec1.getI0019()
										.getI0018().getBatchName());
								qunit1.setParameter(2, clientID);
								List<I0001> runit1 = (List<I0001>) qunit1
										.getResultList();
								if (runit1.size() > 0) {
									unit = runit1.get(0).getUnit();
								}
								Order.setRollID(rec1.getI0019().getRoll_ID());
								Order.setQuantity(rec1.getSoldQuantity());
								Order.setUnit(unit);
								Order.setOrderDate(result.get(0)
										.getSalesOrderDate());
								counter = 1;

								resulfinal1.add(Order);
							}
						}
						purchaseOrder.setResulfinal(resulfinal1);
					}
					purchaseOrder.setResul1(resul);
					purchaseOrder.setResult(result);
				} else {
					purchaseOrder.setResulfinal(null);
				}
			}
		}
		}
logger.info("ss "+purchaseOrder.getStatus());
		return null;
	}

	@Override
	public List<I0001> getProductInfo() throws DemoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int productInfo2() throws DemoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> productnonsales(PurchaseOrder po, Integer g)
			throws DemoException {
		List<String> pur = new ArrayList<String>();
		List<I0018> i0018s = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<String> i0001s = null;
		Query q21 = null;
		try {
			q21 = entitymanager
					.createQuery("from I0018 where batch_ID=? and status='insert' and client_ID=?");
			q21.setParameter(1, g);
			q21.setParameter(2, clientID);
			i0018s = q21.getResultList();
			if (i0018s.size() > 0) {
				String name = "";
				name = i0018s.get(0).getProductName();
				Query q31 = null;
				q31 = entitymanager
						.createQuery("from I0001 where productName=? and status='i' and client_ID=?");
				q31.setParameter(1, name);
				q31.setParameter(2, clientID);
				i0001s = (ArrayList<String>) q31.getResultList();
				if (i0001s.size() > 0) {
					pur.addAll(i0001s);
				}

			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return i0001s;
	}

	@Transactional(value = "transactionManager")
	public List<I0032> getcitiesinfo(String city) throws DemoException {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0032> cityinf = null;
		try {
			q = entitymanager
					.createQuery("from I0032 o where o.city like :x and o.status='in' and o.client_ID like :y");
			q.setParameter("x", "%" + city + "%");
			q.setParameter("y", "%" + clientID + "%");
			cityinf = q.getResultList();

			if (cityinf.size() == 0) {
				cityinf = q.getResultList();
			}
		} catch (Exception e) {
			logger.info(e.getMessage());

		} finally {

		}
		return cityinf;
	}

	public String filepath;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String TransferFile(String fileName,
			java.io.InputStream inputStream, String temp) throws DemoException {
		try {

			filepath = "C://product//";
			OutputStream out = new FileOutputStream(new File(filepath + temp
					+ "" + fileName));
			int reader = 0;
			byte[] bytes = new byte[(int) getFile().getSize()];
			while ((reader = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, reader);
			}
			inputStream.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		return temp + "" + fileName;
	}

	private long length;

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	@Transactional(value = "transactionManager")
	public String designRegSubmit(ProductRegister productRegister)
			throws DemoException, ParseException {
		Query q1 = null, q2 = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		q1 = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
		q1.setParameter(1, productRegister.getProductName());
		q1.setParameter(2, clientID);
		List<I0001> Product1 = (List<I0001>) q1.getResultList();
		int pid = 0;
		if (Product1.size() > 0) {
			pid = Product1.get(0).getProduct_ID();
			q2 = entitymanager
					.createQuery("from DesignRegister where product_id=? and status2='inserted'");
			q2.setParameter(1, pid);
			List<DesignRegister> list1 = (List<DesignRegister>) q2
					.getResultList();
			int id = 0;
			if (list1.size() > 0) {
				throw new DemoException(
						"*Already an Image has been Selected for this Product");
			} else {
				setFile(productRegister.getFile());
				DesignRegister d = new DesignRegister();
				d.setDate(productRegister.getExpired_date());
				d.setStatus(productRegister.getDescription());
				d.setI0001(entitymanager.find(I0001.class, pid));
				d.setArticleNo(productRegister.getArticleNo());
				d.setThemeProduct(productRegister.getThemeProd());
				d.setArticleProduct(productRegister.getArticleProd());
				d.setProductionDate(productRegister.getDate1());
				d.setEstimateDate(productRegister.getDate2());
				d.setSampleDate(productRegister.getDate3());
				d.setBidingTime(productRegister.getDate4());
				try {
					d.setFilePath(TransferFile(productRegister.getFile()
							.getFileName(), productRegister.getFile()
							.getInputstream(), productRegister.getProductName()));
				} catch (Exception e) {
					d.setFilePath("");
				}
				d.setStatus2("inserted");
				entitymanager.persist(d);
			}
		} else {
			logger.info("no prod");
		}
		return null;
	}

	@Transactional(value = "transactionManager")
	public String designView(ProductRegister productRegister)
			throws DemoException {
		try {
			List<ProductRegister> domain2 = new ArrayList<ProductRegister>();
			Query q1 = null, q2 = null, q3 = null, q4 = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			if (productRegister.getStatus().equalsIgnoreCase("byDate")) {
				q1 = entitymanager
						.createQuery("from DesignRegister where status2='inserted' and date between ? and ?");
				q1.setParameter(1, productRegister.getDates());
				q1.setParameter(2, productRegister.getExpired_date());
				List<DesignRegister> list1 = (ArrayList<DesignRegister>) q1
						.getResultList();
				if (list1.size() > 0) {
					for (int i = 0; i < list1.size(); i++) {
						q1 = entitymanager
								.createQuery("from I0001 where productName=? and client_ID=?");
						q1.setParameter(1, list1.get(i).getI0001()
								.getProductName());
						q1.setParameter(2,clientID);
						List<I0001> lis = (List<I0001>) q1.getResultList();
						int prod = 0;
						if (lis.size() > 0) {
							prod = lis.get(0).getProduct_ID();
						}
						q4 = entitymanager
								.createQuery("from ImagePath where product_id=? and (status2='inserted' or status2='selected' or status2='finalized') ");
						q4.setParameter(1, prod);
						List<ImagePath> list = (List<ImagePath>) q4
								.getResultList();
						int iid = 0;
						int count1 = 0;
						int count2 = 0;
						String s1 = "";
						String s2 = "";
						List<Integer> ll = new ArrayList<Integer>();
						int j = 0;
						if (list.size() > 0) {
							logger.info("list--" + list.size());
							for (int k = 0; k < list.size(); k++) {
								iid = list.get(k).getPathId();
								logger.info("iid--" + iid);

								if (list.get(k).getStatus2()
										.equalsIgnoreCase("finalized")) {
									j = k + 1;
									logger.info("finalized--"
											+ list.get(k).getStatus2());
									logger.info("j--" + j);
									count1 = count1 + 1;
									s1 = "" + j;
								}
								if (list.get(k).getStatus2()
										.equalsIgnoreCase("selected")) {
									j = k + 1;
									count2 = count2 + 1;
									ll.add(j);
								}
							}

						}
						ProductRegister p = new ProductRegister();
						p.setProductName(list1.get(i).getI0001()
								.getProductName());
						p.setDescription(list1.get(i).getStatus());
						p.setDates(list1.get(i).getDate());
						p.setFileName(list1.get(i).getFilePath());
						if (count1 > 0) {
							p.setBatch(s1);
						} else {
							for (int k = 0; k < ll.size(); k++) {
								s2 = "" + ll;
							}
							p.setBatch(s2);
						}
						domain2.add(p);
						productRegister.setDomain1(domain2);
					}
				} else {
					logger.info("*No Records for this Product");

				}
			}
			if (productRegister.getStatus().equalsIgnoreCase("byName")) {
				q1 = entitymanager
						.createQuery("from I0001 where productName=? and client_ID=?");
				q1.setParameter(1, productRegister.getProductName());
				q1.setParameter(2, clientID);
				List<I0001> lis = (List<I0001>) q1.getResultList();
				int prod = 0;
				if (lis.size() > 0) {
					prod = lis.get(0).getProduct_ID();
				}
				q2 = entitymanager
						.createQuery("from DesignRegister where status2='inserted' and  product_id=?  ");
				q2.setParameter(1, prod);
				List<DesignRegister> list2 = (ArrayList<DesignRegister>) q2
						.getResultList();
				if (list2.size() > 0) {
					q4 = entitymanager
							.createQuery("from ImagePath where product_id=? and (status2='inserted' or status2='selected' or status2='finalized') ");
					q4.setParameter(1, prod);
					List<ImagePath> list = (List<ImagePath>) q4.getResultList();
					int iid = 0;
					int count1 = 0;
					int count2 = 0;
					String s1 = "";
					String s2 = "";
					List<Integer> ll = new ArrayList<Integer>();
					int j = 0;
					if (list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							iid = list.get(i).getPathId();
							logger.info("iid--" + iid);

							if (list.get(i).getStatus2()
									.equalsIgnoreCase("finalized")) {
								j = i + 1;
								count1 = count1 + 1;
								s1 = "" + j;
							}
							if (list.get(i).getStatus2()
									.equalsIgnoreCase("selected")) {
								j = i + 1;
								count2 = count2 + 1;
								ll.add(j);
							}
						}
					}
					for (int i = 0; i < list2.size(); i++) {
						ProductRegister p = new ProductRegister();
						p.setProductName(list2.get(i).getI0001()
								.getProductName());
						p.setDescription(list2.get(i).getStatus());
						p.setDates(list2.get(i).getDate());
						p.setFileName(list2.get(i).getFilePath());
						if (count1 > 0) {
							p.setBatch(s1);
						} else {
							for (int k = 0; k < ll.size(); k++) {
								s2 = "" + ll;
							}
							p.setBatch(s2);
						}
						domain2.add(p);
						productRegister.setDomain1(domain2);
					}
				} else {
					logger.info("*No Records for this Product");

				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());

		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public String designValidate(ProductRegister productRegister)
			throws DemoException {
		try {
			List<ProductRegister> domain2 = new ArrayList<ProductRegister>();
			Query q1 = null, q2 = null, q3 = null, q4 = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			q1 = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
			q1.setParameter(1, productRegister.getProductName());
			q1.setParameter(2, clientID);
			List<I0001> Product1 = (List<I0001>) q1.getResultList();
			int pid = 0;
			if (Product1.size() > 0) {
				pid = Product1.get(0).getProduct_ID();
				q4 = entitymanager
						.createQuery("from ImagePath where product_id=? and status2='finalized'");
				q4.setParameter(1, pid);
				List<ImagePath> list3 = (List<ImagePath>) q4.getResultList();
				if (list3.size() > 0) {
					productRegister.setStatus("finalized");
				} else {
					q3 = entitymanager
							.createQuery("from ImagePath where product_id=? and status2='selected'");
					q3.setParameter(1, pid);
					List<ImagePath> list2 = (List<ImagePath>) q3
							.getResultList();
					if (list2.size() > 0) {
						productRegister.setStatus("finalupload");
						for (int i = 0; i < list2.size(); i++) {
							ProductRegister p = new ProductRegister();
							p.setFileName(list2.get(i).getStatus());
							p.setStatus("nill");
							p.setDesc(list2.get(i).getDescription());
							p.setNo("" + i);
							domain2.add(p);
							productRegister.setDomain2(domain2);
						}
					} else {
						q2 = entitymanager
								.createQuery("from ImagePath where product_id=? and status2='inserted'");
						q2.setParameter(1, pid);
						List<ImagePath> list1 = (List<ImagePath>) q2
								.getResultList();
						int id = 0;
						if (list1.size() > 0) {
							productRegister.setStatus("viewUpload");
							for (int i = 0; i < list1.size(); i++) {
								ProductRegister p = new ProductRegister();
								p.setFileName(list1.get(i).getStatus());
								p.setStatus("nill");
								p.setNo("" + i);
								domain2.add(p);
								productRegister.setDomain2(domain2);
							}

						} else {
							productRegister.setStatus("upload");
						}
					}
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String designViews(ProductRegister productRegister, int i)
			throws DemoException {
		try {
			Query q1 = null, q2 = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			q1 = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
			q1.setParameter(1, productRegister.getProductName());
			q1.setParameter(2, clientID);
			List<I0001> Product1 = (List<I0001>) q1.getResultList();
			int pid = 0;
			if (Product1.size() > 0) {
				pid = Product1.get(0).getProduct_ID();
				q2 = entitymanager
						.createQuery("from DesignRegister where product_id=? and status2='inserted'");
				q2.setParameter(1, pid);
				List<DesignRegister> list1 = (List<DesignRegister>) q2
						.getResultList();
				int id = 0;
				if (list1.size() > 0) {
					ImagePath ii = new ImagePath();
					ii.setDescription("nill");
					ii.setStatus(productRegister.getProductName()
							+ productRegister.getFiles().get(i).getAname());
					ii.setI0001(entitymanager.find(I0001.class, pid));
					ii.setStatus2("inserted");
					entitymanager.persist(ii);
				} else {
					throw new DemoException("*No Records for this Product");
				}
			} else {
				throw new DemoException("*No Records for this Product");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public String saveImages(ProductRegister productRegister, int i)
			throws DemoException {
		try {
			Query q1 = null, q2 = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			q1 = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
			q1.setParameter(1, productRegister.getProductName());
			q1.setParameter(2, clientID);
			List<I0001> Product1 = (List<I0001>) q1.getResultList();
			int pid = 0;
			if (Product1.size() > 0) {
				logger.info("-->> if");
				pid = Product1.get(0).getProduct_ID();
				q2 = entitymanager
						.createQuery("from ImagePath where product_id=? and status=? and (status2='inserted' or status2='selected') ");
				q2.setParameter(1, pid);
				q2.setParameter(2, productRegister.getDomain1().get(i)
						.getFileName());
				List<ImagePath> list1 = (List<ImagePath>) q2.getResultList();
				int id = 0;
				if (list1.size() > 0) {
					logger.info("-->> id " + id);
					id = list1.get(0).getPathId();

					ImagePath ID = entitymanager.find(ImagePath.class, id);
					if (productRegister.getStatus().equalsIgnoreCase(
							"viewUpload")) {
						ID.setStatus2("selected");
					}
					if (productRegister.getStatus().equalsIgnoreCase(
							"finalupload")) {
						ID.setStatus2("finalized");
					}
					ID.setDescription(productRegister.getDomain1().get(i)
							.getDesc());
					entitymanager.merge(ID);

				} else {
					throw new DemoException("*No Records for this Product");
				}
			} else {
				throw new DemoException("*No Records for this Product");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return null;
	}

	@Transactional(value = "transactionManager")
	public String designViewDelete(ProductRegister productRegister)
			throws DemoException {
		Query q1 = null, q2 = null, q3 = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		q1 = entitymanager.createQuery("from I0001 where productName=? and client_ID=?");
		q1.setParameter(1, productRegister.getProductName());
		q1.setParameter(2, clientID);
		List<I0001> Product1 = (List<I0001>) q1.getResultList();
		int pid = 0;
		if (Product1.size() > 0) {
			pid = Product1.get(0).getProduct_ID();
			q2 = entitymanager
					.createQuery("from DesignRegister where product_id=? and status2='inserted'");
			q2.setParameter(1, pid);
			List<DesignRegister> list1 = (List<DesignRegister>) q2
					.getResultList();
			int id = 0;
			if (list1.size() > 0) {
				id = list1.get(0).getRegId();
				DesignRegister ID = entitymanager
						.find(DesignRegister.class, id);
				ID.setStatus2("deleted");
				entitymanager.merge(ID);

			}
			q3 = entitymanager
					.createQuery("from ImagePath where product_id=? and (status2='inserted' or status2='selected' or status2='finalized') ");
			q3.setParameter(1, pid);
			List<ImagePath> list2 = (List<ImagePath>) q3.getResultList();
			int iid = 0;
			if (list2.size() > 0) {
				for (int i = 0; i < list2.size(); i++) {
					iid = list2.get(i).getPathId();
					logger.info("iid " + iid);
					ImagePath ID = entitymanager.find(ImagePath.class, iid);
					entitymanager.remove(ID);
				}
			}
		}
		return null;
	}

	/* Database Validation */
	@Override
	public String databaseValidate(LoginAccess loginaccess) {
		String status = "Fail";
		String srole = "";
		Query d = null;
		Query menuQuery = null;
		Query menuQuery1 = null;
		List<Login> databaselist=null;
		try {
			//d = entitymanager.createQuery("from Login where loginUser=? and loginPassword=?");
			d = entitymanager.createQuery(Util.getMessage("LOGIN_QUERY", "query"));	
			d.setParameter(1, loginaccess.getUsername());
			d.setParameter(2, loginaccess.getUserpassword());
			logger.info("Username:::::" + loginaccess.getUsername());
			logger.info("Password:::::" + loginaccess.getUserpassword());
			databaselist = (List<Login>) d.getResultList();
			logger.info("DatabaseList size" + databaselist.size());
			if (databaselist.size() > 0) {
				loginaccess.setClientID(databaselist.get(0).getClientId());
				loginaccess.setUser_ID(String.valueOf(databaselist.get(0).getUserCreate().getUserNo()));
				loginaccess.setUserType(databaselist.get(0).getUserCreate().getUserType());
				srole = databaselist.get(0).getRole();
				loginaccess.setUsername(databaselist.get(0).getLoginUser());
				loginaccess.setClientName(databaselist.get(0).getClientId());
				loginaccess.setBaseCurrency(databaselist.get(0).getUserCreate().getClient().getBaseCurrency());
				loginaccess.setCountry(databaselist.get(0).getUserCreate().getClient().getCountry());
				logger.info("srole is" + databaselist.get(0).getRole());
				//menuQuery = entitymanager.createQuery("from UserProduct where has_user_ID=? and status='Active'");
				menuQuery = entitymanager.createQuery(Util.getMessage("MENU_QUERY", "query"));	
				menuQuery.setParameter(1, databaselist.get(0).getLoginId());
				List<UserProduct> res = (List<UserProduct>) menuQuery.getResultList();
				if (res.size() > 0) {
					loginaccess.setUser_Product(res);
				}
				status = "success";
			}

			else {
				status = "failure1";
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return status;
	}

	@Override
	public String getdashboardCount(LoginAccess loginaccess) {
		Query q=null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		logger.info("inside dashboard"+clientID);
		try{
			if(userType.equals("Maker")){
				q=entitymanager.createQuery("from I0021 where client_ID=? and user_ID=? and (status='Delivered' or status='Quick sales')");
				q.setParameter(1, clientID);
				q.setParameter(2, userID);
			}else{
				q=entitymanager.createQuery("from I0021 where client_ID=? and (status='Delivered' or status='Quick sales')");
				q.setParameter(1, clientID);
			}
			List<I0021> salescount=(List<I0021>)q.getResultList();
			loginaccess.setSalesCount(salescount.size());
			q=null;
			if(userType.equals("Maker")){
				q=entitymanager.createQuery("from I0015 where client_ID=? and user_ID=? and (status='insert' or status='delivered')");
				q.setParameter(1, clientID);
				q.setParameter(2, userID);
			}else{
				q=entitymanager.createQuery("from I0015 where client_ID=? and (status='insert' or status='delivered')");
				q.setParameter(1, clientID);
			}
			List<I0015> purcount=(List<I0015>)q.getResultList();
			loginaccess.setPurchaseCount(purcount.size());
			q=null;
			q=entitymanager.createQuery("from I0019 where client_ID=? and status='barcode genterated'");
			q.setParameter(1, clientID);
			List<I0019> stockcount=(List<I0019>)q.getResultList();
			loginaccess.setStockCount(stockcount.size());
			q=null;
			if(userType.equals("Maker")){
				q=entitymanager.createQuery("from I0001 where client_ID=? and user_ID=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, userID);
				q.setParameter(3, "i");
			}else{
				q=entitymanager.createQuery("from I0001 where client_ID=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, "i");
			}
			List<I0001> prodcount=(List<I0001>)q.getResultList();
			loginaccess.setProductCount(prodcount.size());
			q=null;
			if(userType.equals("Maker")){
				q=entitymanager.createQuery("from I0032 where client_ID=? and user_ID=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, userID);
				q.setParameter(3, "in");
			}else{
				q=entitymanager.createQuery("from I0032 where client_ID=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, "in");
			}
			List<I0032> custcount=(List<I0032>)q.getResultList();
			loginaccess.setCustomerCount(custcount.size());
			q=null;
			if(userType.equals("Maker")){
				q=entitymanager.createQuery("from I0025 where client_ID=? and user_ID=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, userID);
				q.setParameter(3, "i");
			}else{
				q=entitymanager.createQuery("from I0025 where client_ID=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, "i");
			}
			List<I0025> vencount=(List<I0025>)q.getResultList();
			loginaccess.setVendorCount(vencount.size());
		}catch(Exception e){
						logger.error("Error --------------->"+e.getMessage());
		}
		return null;
	}
	/* check Error_code Table */
	@Override
	public String checkerrorcode(LoginAccess loginaccess) {
		Query e = null;
		Query ee = null;
		Query eee = null;
		String Errormsg1 = "";
		try {
			logger.info("====Inside checkErrorcode method calling====");
			e = entitymanager.createQuery("from Login where loginUser=?");
			e.setParameter(1, loginaccess.getUsername());
			List<Login> loginList = (List<Login>) e.getResultList();
			logger.info("loginList size" + loginList.size());
			if (loginList.size() == 0) {
				Query e1 = null;
				ee = entitymanager.createQuery("from Login where loginPassword=?");
			ee.setParameter(1, loginaccess.getUserpassword());
				List<Login> loginList1 = (List<Login>) ee.getResultList();
				if (loginList1.size() == 0) {
					Query ee1 = null;
					ee1 = entitymanager.createQuery("from ErrorCode where errorCode=?");
					ee1.setParameter(1, "E-003");
					List<ErrorCode> errorList1 = (List<ErrorCode>) ee1
							.getResultList();
					if (errorList1.size() > 0) {
						Errormsg1 = errorList1.get(0).getErrorMsg();
						logger.info("Password Error Msg" + Errormsg1);
					}

				} else {
					e1 = entitymanager.createQuery("from ErrorCode where errorCode=?");
					e1.setParameter(1, "E-001");
					List<ErrorCode> errorList = (List<ErrorCode>) e1
							.getResultList();
					logger.info("ErrorList size" + errorList.size());
					if (errorList.size() > 0) {
						Errormsg1 = errorList.get(0).getErrorMsg();
						logger.info("UserName Error Msg" + Errormsg1);
					}
				}

			} else {
				ee = entitymanager
						.createQuery("from Login where loginPassword=?");
				ee.setParameter(1, loginaccess.getUserpassword());
				List<Login> loginList1 = (List<Login>) ee.getResultList();
				if (loginList1.size() == 0) {
					Query ee1 = null;
					ee1 = entitymanager
							.createQuery("from ErrorCode where errorCode=?");
					ee1.setParameter(1, "E-002");
					List<ErrorCode> errorList1 = (List<ErrorCode>) ee1
							.getResultList();
					if (errorList1.size() > 0) {
						Errormsg1 = errorList1.get(0).getErrorMsg();
						logger.info("Password Error Msg" + Errormsg1);
					}

				} else {

					Errormsg1 = "";
				}
			}

		} catch (Exception e1) {
			logger.info(e1.getMessage());
		}
		return Errormsg1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demo.dao.DemoDao#insertCreateUser(com.demo.domain.UserCreateDataBean)
	 */
	@Override
	@Transactional(value = "transactionManager")
	public String insertCreateUser(UserCreateDataBean userCreateDataBean) {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		String status = "fail";
		try {
			Query q = null;
			q = entitymanager.createQuery("from UserCreate where userName=?");
			q.setParameter(1, userCreateDataBean.getCreateUserName());
			List<UserCreate> userNameList = (List<UserCreate>) q
					.getResultList();
			logger.info("userNameList Size" + userNameList.size());
			if (userNameList.size() > 0) {
				status = "fail";
			} else {
				UserCreate user1 = new UserCreate();
				user1.setUserName(userCreateDataBean.getCreateUserName());
				user1.setUserPassword(userCreateDataBean.getCreateUserPwd());
				user1.setUserMailid(userCreateDataBean.getCreateUserMail());
				user1.setUserPhone(userCreateDataBean.getCreateUserPhone());
				user1.setDate(date);
				user1.setTime(timestamp);
				user1.setStatus("Active");
				entitymanager.persist(user1);
				status = "success";
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demo.dao.DemoDao#insideUserEdit(com.demo.domain.UserCreateDataBean)
	 */
	@Override
	public List<UserCreateDataBean> insideUserEdit(
			UserCreateDataBean userCreateDataBean) {
		Query e1 = null;
		List<UserCreateDataBean> userList1 = null;
		try {
			userList1 = new ArrayList<UserCreateDataBean>();
			e1 = entitymanager.createQuery("from UserCreate where status=?");
			e1.setParameter(1, "Active");
			List<UserCreate> userEditList = (List<UserCreate>) e1
					.getResultList();
			logger.info("userEditList size is" + userEditList.size());
			if (userEditList.size() > 0) {
				for (int i = 0; i < userEditList.size(); i++) {
					UserCreateDataBean user3 = new UserCreateDataBean();
					user3.setCreateUserName(userEditList.get(i).getUserName());
					user3.setCreateUserPwd(userEditList.get(i)
							.getUserPassword());
					user3.setCreateUserMail(userEditList.get(i).getUserMailid());
					user3.setCreateUserPhone(userEditList.get(i).getUserPhone());
					userList1.add(user3);
				}
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return userList1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demo.dao.DemoDao#insideUpdate(com.demo.domain.UserCreateDataBean)
	 */
	@Override
	@Transactional(value = "transactionManager")
	public String insideUpdate(UserCreateDataBean userCreateDataBean) {
		logger.info("InsideUpdate method dao calling");
		Query q = null;
		int userno = 0;
		try {
			q = entitymanager
					.createQuery("from UserCreate where userName=? and status='Active'");
			q.setParameter(1, userCreateDataBean.getCreateUserName());
			List<UserCreate> userNameList1 = (List<UserCreate>) q
					.getResultList();
			logger.info("user Name List size" + userNameList1.size());
			if (userNameList1.size() > 0) {
				userno = userNameList1.get(0).getUserNo();
				logger.info("user no" + userno);
				UserCreate obj = entitymanager.find(UserCreate.class, userno);
				obj.setUserPassword(userCreateDataBean.getCreateUserPwd());
				obj.setUserMailid(userCreateDataBean.getCreateUserMail());
				obj.setUserPhone(userCreateDataBean.getCreateUserPhone());
				entitymanager.merge(obj);
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "";
	}

	@Override
	public String getVendorVerification(String name) {
		logger.info("Callling getVendorVerification in DAO");
		String status = "Fail";String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		try {
			if (name != null || name != "") {
				q = entitymanager.createQuery("from I0025 where vendorPhoneNumber=? and status='i' and client_ID=? and user_ID=?");
				q.setParameter(1, name);
				q.setParameter(2, clientID);
				q.setParameter(3, userID);
				List<I0025> result = (List<I0025>) q.getResultList();
				if (result.size() > 0) {
					status = "Success";
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return status;
	}

	@Override
	public String getCustomerVerification(String name) {
		logger.info("Callling getCustomerVerification:");
		String status = "Fail";
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		try {
			if (name != null || name != "") {
				q = entitymanager.createQuery("from I0032 where phoneNumber=? and status='in' and client_ID=? and user_ID=?");
				q.setParameter(1, name);
				q.setParameter(2, clientID);
				q.setParameter(3, userID);
				List<I0032> result = (List<I0032>) q.getResultList();
				logger.info("result List size" + result.size());
				if (result.size() > 0) {
					status = "Success";
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return status;
	}

	@Override
	@Transactional(value = "transactionManager")
	public String userInsert(UserCreateDataBean userCreateDataBean)
			throws DemoException {
		Date date = new Date();Query q=null;
		int mainMenuID1 = 0;
		Timestamp time = new Timestamp(date.getTime());
		Query v = null;int count = 0;
		userCreateDataBean.setErrorMsg("");
		List<Client> client=null;String status="fail";
		try {
			logger.info("no - "+userCreateDataBean.getClientNumber());
			client=getClientID(userCreateDataBean.getClientNumber());
			logger.info("client size "+client.size());
			if(client.size()>0){
				for (int i = 0; i < userCreateDataBean.getUserlist().size(); i++) {
					if (userCreateDataBean.getUserlist().get(i).getTick()
							.equalsIgnoreCase("true")) {
						if(!userCreateDataBean.getUserlist().get(i).getCreateUserName().equalsIgnoreCase("")){
							q=entitymanager.createQuery("from UserCreate where userName=? and status=? and client_ID=?");
							q.setParameter(1, userCreateDataBean.getUserlist().get(i).getCreateUserName());
							q.setParameter(2, "Active");
							q.setParameter(3, userCreateDataBean.getClientID());
							List<UserCreate> userlist=(List<UserCreate>)q.getResultList();
							if(userlist.size()>0){
								status="usernameexist";
							}else{
								UserCreate user = new UserCreate();
								user.setUserName(userCreateDataBean.getUserlist().get(i)
										.getCreateUserName());
								user.setUserPassword(userCreateDataBean.getUserlist()
										.get(i).getCreateUserPwd());
								user.setUserMailid(userCreateDataBean.getUserlist().get(i)
										.getCreateUserMail());
								user.setUserPhone(userCreateDataBean.getUserlist().get(i)
										.getCreateUserPhone());
								user.setUserType(userCreateDataBean.getUserlist().get(i).getUserType());
								user.setDate(date);
								user.setTime(time);
								user.setClient(entitymanager.find(Client.class, client.get(0).getClient_ID()));
								user.setStatus("Active");
								entitymanager.persist(user);
								entitymanager.flush();
								entitymanager.clear();
								v=null;
								v=entitymanager.createQuery("from UserCreate");
								List<UserCreate> users=(List<UserCreate>)v.getResultList();
								if(users.size()>0){
										for (int j2 = 0; j2 < userCreateDataBean.getUserlist().get(i).getSelectedDepartments().length; j2++) {
											v=null;
											v=entitymanager.createQuery("from Department where departmentName=?");
											v.setParameter(1, userCreateDataBean.getUserlist().get(i).getSelectedDepartments()[j2]);
											List<Department> departmentList=(List<Department>)v.getResultList();
											if(departmentList.size()>0){
												UserDepartment userdept=new UserDepartment();
												userdept.setDepartment(entitymanager.find(Department.class, departmentList.get(0).getDepartment_ID()));
												userdept.setUserNo(entitymanager.find(UserCreate.class, users.get(users.size()-1).getUserNo()));
												userdept.setStatus("Active");
												entitymanager.persist(userdept);
												entitymanager.flush();
												entitymanager.clear();
											}
										}
									Login login =new Login();
									login.setLoginUser(userCreateDataBean.getUserlist().get(i).getCreateUserName());
									login.setLoginPassword(userCreateDataBean.getUserlist().get(i).getCreateUserPwd());
									login.setStatus("Active");
									login.setClientId(userCreateDataBean.getClientNumber());
									login.setRole("User");
									login.setUserCreate(entitymanager.find(UserCreate.class, users.get(users.size()-1).getUserNo()));
									entitymanager.persist(login);
									entitymanager.flush();
									entitymanager.clear();
									status="Success";
									v=entitymanager.createQuery("from Login");
									List<Login> logins=(List<Login>)v.getResultList();
									if(logins.size()>0){
										for (int j = 0; j < userCreateDataBean.getUserlist().get(i).getSelectedMenus().length; j++) {
											mainMenuID1 = getMainMenu(userCreateDataBean.getUserlist().get(i).getSelectedMenus()[j]);
											if (mainMenuID1 > 0) {
												UserProduct userp = new UserProduct();
												userp.setLogin(entitymanager.find(Login.class, logins.get(logins.size()-1).getLoginId()));
												userp.setStatus("Active");
												userp.setProduct(entitymanager.find(com.inacsys.shared.Product.class, mainMenuID1));
												entitymanager.persist(userp);
												entitymanager.flush();
												entitymanager.clear();
											}
										}
									}
								}
							}
						}
					}
				}
			}			
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.error("inside exception ",e);
		}
		return status;
	}

	private int getMainMenu(String code) {
		int res = 0;
		Query q = null;
		try {
			q = entitymanager.createQuery("from com.inacsys.shared.Product where productName=? and status='Active'");
			q.setParameter(1, code);
			List<com.inacsys.shared.Product> result = (List<com.inacsys.shared.Product>) q.getResultList();
			if (result.size() > 0) {
				res = result.get(0).getProduct_ID();
			}
		} catch (Exception e) {
			logger.error("inside exception ",e);
		}
		return res;
	}
	
	@Override
	public String userCheck(UserCreateDataBean userCreateDataBean)
			throws DemoException {
		String status = "fail";
		Query v = null;
		try {
			v = entitymanager.createQuery("from UserCreate where userName=? and status='Active'");
			v.setParameter(1, userCreateDataBean.getCreateUserName());
			List<UserCreate> usercreate = (List<UserCreate>) v.getResultList();
			if (usercreate.size() > 0) {
				status = "fail";
			} else {
				status = "success";
			}
		} catch (Exception e) {
		}
		return status;
	}

	@Override
	public String retirveUser(UserCreateDataBean userCreateDataBean) {
		Query v = null;
		List<Client> client=null;List<String> typeList=new ArrayList<String>();
		List<String> typeList1=new ArrayList<String>();
		try {
			client=getClientID(userCreateDataBean.getClientNumber());
			if(client.size()>0){
				v = entitymanager
						.createQuery("from UserCreate where client_ID=? and status='Active'");
				v.setParameter(1, client.get(0).getClient_ID());
				List<UserCreate> usercreate = (List<UserCreate>) v.getResultList();
				logger.info("user size -- " + usercreate.size());
				userCreateDataBean.setUsers(usercreate.size());
				userCreateDataBean.setClientLimit(Integer.parseInt(client.get(0).getUserLimit()));
				userCreateDataBean.setUserdepartments(client.get(0).getDepartment());
				userCreateDataBean.setUsermenus(client.get(0).getMenus());
				for (int i = 0; i < usercreate.size(); i++) {
					typeList.add(usercreate.get(i).getUserType());
				}
				userCreateDataBean.setUsertypeList(typeList);
				for (int i = 0; i < usercreate.size(); i++) {
					logger.info("phone"+userCreateDataBean.getCreateUserPhone());
					if(!userCreateDataBean.getCreateUserPhone().equals(usercreate.get(i).getUserPhone())){
						typeList1.add(usercreate.get(i).getUserType());
					}
				}
				userCreateDataBean.setUsertypeList1(typeList1);
			}			
		} catch (Exception e) {
		}
		return "";
	}

	// prema begin 26/04/2016 customer drop box
	@Override
	public List<String> getCustomerInfo(Buyer b) {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<String> resultList = null;
		q = entitymanager.createQuery("select customerName from I0032 where status='in' and client_ID=?");
		q.setParameter(1, clientID);
		resultList = q.getResultList();
		logger.info("resultList size" + resultList.size());
		return resultList;
	}

	// prema begin 27/04/2016 product drop box
	@Override
	public List<String> getproductListInfo(ProductRegister productRegister) {
		Query q = null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<String> productList = null;
		q = entitymanager
				.createQuery("select productName from I0001  where status='i' and client_ID=? ");
		q.setParameter(1, clientID);
		productList = q.getResultList();
		logger.info("productList size" + productList.size());
		return productList;
	}

	// prema begin 27/04/2016 product register verification
	@Override
	public String getProductVerification(String name) {
		String status = "Fail";
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		Query q = null;
		try {
			if (name != null || name != "") {
				q = entitymanager
						.createQuery("from I0001 where productName=? and status='i' and client_ID=? and user_ID=?");
				q.setParameter(1, name);
				q.setParameter(2, clientID);
				q.setParameter(3, userID);
				List<I0001> result = (List<I0001>) q.getResultList();
				if (result.size() > 0) {
					status = "Success";
				}
			}
		} catch (Exception e) {
		}
		return status;
	}

	// prema end 27/04/2016
	
	@Override
	public String getProductcodeVerification(String name) {
		String status = "Fail";String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		Query q = null;
		try {
			if (name != null || name != "") {
				q = entitymanager
						.createQuery("from I0001 where productWeight=? and status='i' and client_ID=? and user_ID=?");
				q.setParameter(1, name);
				q.setParameter(2, clientID);
				q.setParameter(3, userID);
				List<I0001> result = (List<I0001>) q.getResultList();
				if (result.size() > 0) {
					status = "Success";
				}
			}
		} catch (Exception e) {
		}
		return status;
	}

	@Override
	public ArrayList<Report1> getVendorNameList() {
		ArrayList<Report1> vendornameList = null;
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try {
			vendornameList = new ArrayList<Report1>();
			q = entitymanager.createQuery("from I0025 where status='i' and client_ID=?");
			q.setParameter(1, clientID);
			List<I0025> vendorname = (ArrayList<I0025>) q.getResultList();
			if (vendorname.size() > 0) {
				for (I0025 i0025 : vendorname) {
					Report1 rep = new Report1();
					rep.setVendorName(i0025.getVendorPhoneNumber());
					vendornameList.add(rep);
				}
			}
		} catch (Exception e) {
		}
		return vendornameList;
	}

	@Override
	public ArrayList<VendorReport> insidevendornamesearch(
			VendorReport vendorReport) {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<Integer> vendoridList = new ArrayList<Integer>();
		ArrayList<VendorReport> vendorList = null;
		vendorList = new ArrayList<VendorReport>();
		try {
			q = entitymanager
					.createQuery("from I0025 where vendorPhoneNumber=? and status='i' and client_ID=?");
			q.setParameter(1, vendorReport.getVendorReportName());
			q.setParameter(2, clientID);
			List<I0025> resultList = (List<I0025>) q.getResultList();
			logger.info("resultList size" + resultList.size());
			if (resultList.size() > 0) {
				q = null;
				q = entitymanager.createQuery("from I0031 where vendor_ID=?");
				q.setParameter(1, resultList.get(0).getVendor_ID());
				List<I0031> res = (List<I0031>) q.getResultList();
				logger.info("res size" + res.size());
				if (res.size() > 0) {
					for (int i = 0; i < res.size(); i++) {
						vendoridList.add(res.get(i).getHas_vendor_ID());
					}
					if (vendoridList.size() > 0) {
						for (int i = 0; i < vendoridList.size(); i++) {
							q = null;
							q = entitymanager
									.createQuery("from I0016 where orderDate between ? and ? and has_vendor_ID=? and client_ID=?");
							q.setParameter(1, vendorReport.getVendorFromDate());
							q.setParameter(2, vendorReport.getVendorToDate());
							q.setParameter(3, vendoridList.get(i));
							q.setParameter(4, clientID);
							List<I0016> resList2 = (List<I0016>) q
									.getResultList();
							if (resList2.size() > 0) {
								for (int j = 0; j < resList2.size(); j++) {
									q = null;
									q = entitymanager
											.createQuery("from I0001 where productName=? and status='i' and client_ID=?");
									q.setParameter(1, resList2.get(i)
											.getI0031().getI0001()
											.getProductName());
									q.setParameter(2, clientID);
									List<I0001> prodList = (List<I0001>) q
											.getResultList();
									VendorReport venrp = new VendorReport();
									venrp.setVendorRepName(resultList.get(j)
											.getVendorPhoneNumber());
									venrp.setVendorphonenumber(resultList
											.get(j).getFirmName());
									venrp.setPurchaseOrderNO(resList2.get(j)
											.getI0015().getTemOrderNumber());
									venrp.setPrize(""
											+ new BigDecimal(resList2.get(j)
													.getQuantity())
													.multiply(new BigDecimal(
															prodList.get(j)
																	.getAutualPrice())));
									venrp.setPurchaseStatus(resList2.get(j)
											.getI0015().getStatus());
									vendorList.add(venrp);
									logger.info("vendorList size"
											+ vendorList.size());
								}
							}

						}
					}
				}
			} else {
				vendorList.add(null);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return vendorList;
	}

	@Override
	public ArrayList<VendorReport> insideallvendornamesearch(
			VendorReport vendorReport) {
		Query q = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<String> vendoridList = new ArrayList<String>();
		ArrayList<VendorReport> vendorList = null;
		BigDecimal totprice = BigDecimal.valueOf(0);
		vendorList = new ArrayList<VendorReport>();
		try {
			logger.info("Inside allvendorname search method calling in dao");
			q = entitymanager.createQuery("from I0025 where status='i' and client_ID=?");
			q.setParameter(1, clientID);
			List<I0025> resultList = (List<I0025>) q.getResultList();
			logger.info("resultList size" + resultList.size());
			if (resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					q = null;
					q = entitymanager
							.createQuery("from I0031 where vendor_ID=?");
					q.setParameter(1, resultList.get(i).getVendor_ID());
					List<I0031> res = (List<I0031>) q.getResultList();
					logger.info("res size" + res.size());
					if (res.size() > 0) {
						q = null;
						q = entitymanager
								.createQuery("from I0016 where orderDate between ? and ? and has_vendor_ID=? and client_ID=?");
						q.setParameter(1, vendorReport.getVendorFromDate());
						q.setParameter(2, vendorReport.getVendorToDate());
						q.setParameter(3, res.get(0).getHas_vendor_ID());
						q.setParameter(4, clientID);
						List<I0016> resList2 = (List<I0016>) q.getResultList();
						if (resList2.size() > 0) {
							for (int j = 0; j < resList2.size(); j++) {
								vendoridList.add(resList2.get(j)
										.getOrdernumber());

							}
							HashSet<String> orderno = new HashSet<String>(
									vendoridList);
							vendoridList.clear();
							vendoridList.addAll(orderno);
							logger.info("vendor list size"
									+ vendoridList.size());
							if (vendoridList.size() > 0) {
								totprice = BigDecimal.valueOf(0);
								for (int j = 0; j < vendoridList.size(); j++) {
									q = null;
									q = entitymanager
											.createQuery("from I0015 where temOrderNumber=? and (status='insert' or status='delivered') and client_ID=?");
									q.setParameter(1, vendoridList.get(j));
									q.setParameter(2, clientID);
									List<I0015> orderList = (List<I0015>) q
											.getResultList();
									logger.info("orderList size"
											+ orderList.size());
									if (orderList.size() > 0) {
										totprice = totprice.add(new BigDecimal(
												orderList.get(0)
														.getTotalPrice()));
									}
								}
								VendorReport venrp = new VendorReport();
								venrp.setAllrepVendorName(resultList.get(i)
										.getVendorPhoneNumber());
								venrp.setAllvendorphNo(resultList.get(i)
										.getFirmName());
								venrp.setTotalprice("" + totprice);
								vendorList.add(venrp);
								logger.info("vendorList size"
										+ vendorList.size());
							}
						}
					}
				}
			} else {
				vendorList.add(null);

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return vendorList;
	}

	@Override
	public List<I0025> getphonenumberglobalsearch(String golbalnamesearch)
			throws DemoException {
		List<I0025> result = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			q = entitymanager
					.createQuery("from I0025 where firmName=? and status='i' and client_ID=?");
			q.setParameter(1, golbalnamesearch);
			q.setParameter(2, clientID);
			result = (List<I0025>) q.getResultList();
			logger.info("result size" + result.size());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return result;
	}

	@Override
	public List<I0032> getcustphnosearch(String golbalnamesearch)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<I0032> result = null;
		Query q = null;
		try {
			q = entitymanager
					.createQuery("from I0032 where phoneNumber=? and status='in' and client_ID=?");
			q.setParameter(1, golbalnamesearch);
			q.setParameter(2, clientID);
			result = (List<I0032>) q.getResultList();
			logger.info("result size" + result.size());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return result;
	}

	@Override
	public List<Employee> getempphnosearch(String golbalnamesearch)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		List<Employee> result = null;
		Query q = null;
		try {
			logger.info("inside getempphnosearch:");
			q = entitymanager
					.createQuery("from Employee where phoneNumber=? and status='inserted' and client_ID=?");
			q.setParameter(1, golbalnamesearch);
			q.setParameter(2, clientID);
			result = (List<Employee>) q.getResultList();
			logger.info("result size" + result.size());
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public ArrayList<I0025> getgmailsearch(String golbalnamesearch)
			throws DemoException {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		ArrayList<I0025> result = null;
		Query q = null;
		try {
			logger.info("inside getgmailsearch:");
			q = entitymanager
					.createQuery("from I0025 where email_ID_vendor=? and status='i' and client_ID=?");
			q.setParameter(1, golbalnamesearch);
			q.setParameter(2, clientID);
			result = (ArrayList<I0025>) q.getResultList();
			logger.info("result size" + result.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return result;
	}

	@Override
	public List<I0032> getcustemailsearch(String golbalnamesearch)
			throws DemoException {
		List<I0032> result = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			logger.info("inside email search dao calling");
			q = entitymanager
					.createQuery("from I0032 where eMail=? and status='in' and client_ID=?");
			q.setParameter(1, golbalnamesearch);
			q.setParameter(2, clientID);
			result = (List<I0032>) q.getResultList();
			logger.info("result size" + result.size());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return result;
	}

	@Override
	public List<Employee> getempemailsearch(String golbalnamesearch)
			throws DemoException {
		List<Employee> result = null;String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		Query q = null;
		try {
			logger.info("inside email search dao calling");
			q = entitymanager
					.createQuery("from Employee where emailId=? and status='inserted' and client_ID=?");
			q.setParameter(1, golbalnamesearch);
			q.setParameter(2, clientID);
			result = (List<Employee>) q.getResultList();
			logger.info("result size" + result.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return result;
	}

	@Override
	public List<SubProduct> submenus(int product_ID, String productCode) {
		Query q=null;
		List<SubProduct> list=null;
		try{
			q=entitymanager.createQuery("from SubProduct where product_ID=? and status='ACTIVE'");
			q.setParameter(1, product_ID);
			list=(List<SubProduct>)q.getResultList();
		}catch(Exception e){
			logger.error("inside exception ",e);
			logger.error("Error Message"+e);
		}
		return list;
	}
	
	public List<Client> getAllClient(){
		Query v=null;List<Client> client=new ArrayList<Client>();
		try{
			v=entitymanager.createQuery("from Client");
			client=(List<Client>) v.getResultList();
			logger.info("client size "+client.size());
		}catch(Exception e){
						logger.error("Error --------------->"+e.getMessage());
		}
		return client;
	}
	@Transactional(value="transactionManager")
	public String saveClient(ClientDataBean clientDataBean)throws DemoException{
		logger.info("inside client save dao");
		Query v=null;Date date=new Date();
		String status="fail";
		try{
			v=entitymanager.createQuery("from Client where phoneNumber=? and status='Active'");
			v.setParameter(1, clientDataBean.getPhoneNumber());
			List<Client> clients=(List<Client>) v.getResultList();
			logger.info("client size "+clients.size());
			if(clients.size()>0){
				status="Exist";
			}else{
				Client clientinsert=new Client();
				clientinsert.setClientNumber(clientDataBean.getClientNumber());
				clientinsert.setClientName(clientDataBean.getClientName());
				clientinsert.setPhoneNumber(clientDataBean.getPhoneNumber());
				clientinsert.setMailId(clientDataBean.getMailID());
				clientinsert.setUserLimit(clientDataBean.getUserLimit());
				clientinsert.setDepartment(clientDataBean.getDepartment());
				clientinsert.setMenus(clientDataBean.getMenus());
				clientinsert.setBaseCurrency(clientDataBean.getBaseCurrency());
				clientinsert.setAddress(clientDataBean.getAddress());
				clientinsert.setCountry(clientDataBean.getClientCountry());
				clientinsert.setStatus("Active");
				clientinsert.setCreatedDate(date);
				entitymanager.persist(clientinsert);
				status="Success";
			}			
		}
		catch(Exception e){
			logger.error("inside exception ",e);
		}
		return status;
	}
	
	public String clientNoCheck(String phno)throws DemoException{
		Query v=null;
		String status="fail";
		try{
			v=entitymanager.createQuery("from Client where phoneNumber=? and status='Active'");
			v.setParameter(1, phno);
			List<Client> client=(List<Client>) v.getResultList();
			logger.info("client size "+client.size());
			if(client.size()>0){
				status="Exist";
			}else{
				status="Not";
			}
		}
		catch(Exception e){
			logger.error("inside exception ",e);
		}
		return status;
	}
	
	public String getclientDetails(ClientDataBean clientDataBean){
		Query q=null;
		List<ClientDataBean> clientList=null;
		try{
			clientList=new ArrayList<ClientDataBean>();
			q=entitymanager.createQuery("from Client where status=? order by clientNumber desc");
			q.setParameter(1, "Active");
			List<Client> clients=(List<Client>) q.getResultList();
			logger.info("client size "+clients.size());
			if(clients.size()>0){
				for(Client client:clients){
					q=null;
					q=entitymanager.createQuery("from UserCreate where client_ID=? and status=?");
					q.setParameter(1, client.getClient_ID());
					q.setParameter(2, "Active");
					List<UserCreate> userList=(List<UserCreate>)q.getResultList();
					ClientDataBean clientdetails=new ClientDataBean();
					clientdetails.setClientID(client.getClient_ID());
					clientdetails.setClientName(client.getClientName());
					clientdetails.setClientNumber(client.getClientNumber());
					clientdetails.setPhoneNumber(client.getPhoneNumber());
					clientdetails.setMailID(client.getMailId());
					clientdetails.setUserLimit(client.getUserLimit());
					clientdetails.setCreatedUser(userList.size());
					clientList.add(clientdetails);
				}
				clientDataBean.setClientLists(clientList);
			}
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "";
	}
	
	public String getclientDetailsView(ClientDataBean clientDataBean){
		Query v=null;
		try{
			v=entitymanager.createQuery("from Client where phoneNumber=? and status='Active'");
			v.setParameter(1, clientDataBean.getPhoneNumber());
			List<Client> client=(List<Client>) v.getResultList();
			if(client.size()>0){
				clientDataBean.setClientID(client.get(0).getClient_ID());
				clientDataBean.setClientName(client.get(0).getClientName());
				clientDataBean.setPhoneNumber(client.get(0).getPhoneNumber());
				clientDataBean.setMailID(client.get(0).getMailId());
				clientDataBean.setUserLimit(client.get(0).getUserLimit());
				clientDataBean.setDepartment(client.get(0).getDepartment());
				clientDataBean.setAddress(client.get(0).getAddress());
				clientDataBean.setMenus(client.get(0).getMenus());
				clientDataBean.setBaseCurrency(client.get(0).getBaseCurrency());
				clientDataBean.setClientCountry(client.get(0).getCountry());
			}
		}
		catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "";
	}
	
	@Transactional(value="transactionManager")
	public String clientUpdate(ClientDataBean clientDataBean){
		logger.info("client id "+clientDataBean.getClientID());
		Date date=new Date();
		try{
			Client clientinsert=entitymanager.find(Client.class, clientDataBean.getClientID());
			clientinsert.setClientName(clientDataBean.getClientName());
			clientinsert.setPhoneNumber(clientDataBean.getPhoneNumber());
			clientinsert.setMailId(clientDataBean.getMailID());
			clientinsert.setUserLimit(clientDataBean.getUserLimit());
			clientinsert.setDepartment(clientDataBean.getDepartment());
			clientinsert.setAddress(clientDataBean.getAddress());
			clientinsert.setMenus(clientDataBean.getMenus());
			clientinsert.setBaseCurrency(clientDataBean.getBaseCurrency());
			clientinsert.setCountry(clientDataBean.getClientCountry());
			clientinsert.setUpdatedDate(date);
			entitymanager.merge(clientinsert);
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "";
	}
	
	@Transactional(value="transactionManager")
	public String clientDelete(ClientDataBean clientDataBean){
		logger.info("client id "+clientDataBean.getClientID());
		Date date=new Date();
		try{
			Client clientinsert=entitymanager.find(Client.class, clientDataBean.getClientID());
			clientinsert.setStatus("DeActive");
			clientinsert.setUpdatedDate(date);
			entitymanager.merge(clientinsert);
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "";
	}

	public List<Client> getClientID(String clientNumber){
		Query v=null;
		v=entitymanager.createQuery("from Client where clientNumber=? and status='Active'");
		v.setParameter(1, clientNumber);
		List<Client> client=(List<Client>) v.getResultList();
		return client;
	}
	
	public String userView(UserCreateDataBean userCreateDataBean){
		Query v=null;
		List<UserCreateDataBean> userlist=null;
		try{
			userlist=new ArrayList<UserCreateDataBean>();
			v=entitymanager.createQuery("from UserCreate where status='Active'");
			List<UserCreate> users=(List<UserCreate>)v.getResultList();
			logger.info("user size "+users.size());
			if(users.size()>0){
				for(UserCreate user:users){
					UserCreateDataBean userdetails=new UserCreateDataBean();
					userdetails.setCreateUserMail(user.getUserMailid());
					userdetails.setCreateUserName(user.getUserName());
					userdetails.setCreateUserPhone(user.getUserPhone());
					userdetails.setCreateUserPwd(user.getUserPassword());
					userdetails.setClientNumber(user.getClient().getClientNumber());
					userdetails.setUserID(user.getUserNo());
					userdetails.setUserType(user.getUserType());
					userlist.add(userdetails);
				}
				userCreateDataBean.setUserlist(userlist);
			}
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "";
	}
	
	public String getUserdetails(UserCreateDataBean userCreateDataBean){
		Query v=null;
		try{
			v=entitymanager.createQuery("from UserCreate where userName=? and userPhone=? and status='Active'");
			v.setParameter(1, userCreateDataBean.getCreateUserName());
			v.setParameter(2, userCreateDataBean.getCreateUserPhone());
			List<UserCreate> users=(List<UserCreate>)v.getResultList();
			logger.info("user size "+users.size());
			if(users.size()>0){
				userCreateDataBean.setCreateUserMail(users.get(0).getUserMailid());
				userCreateDataBean.setCreateUserName(users.get(0).getUserName());
				userCreateDataBean.setCreateUserPhone(users.get(0).getUserPhone());
				userCreateDataBean.setCreateUserPwd(users.get(0).getUserPassword());
				userCreateDataBean.setUserType(users.get(0).getUserType());
				userCreateDataBean.setClientNumber(users.get(0).getClient().getClientNumber());		
				v=null;
				v=entitymanager.createQuery("from UserDepartment where user_no=? and status='Active'");
				v.setParameter(1, users.get(0).getUserNo());
				List<UserDepartment> userdepartmentList=(List<UserDepartment>)v.getResultList();
				if(userdepartmentList.size()>0){
					List<String> depts=new ArrayList<String>();
					for (int i = 0; i < userdepartmentList.size(); i++) {
						depts.add(userdepartmentList.get(i).getDepartment().getDepartmentName());
					}
					String[] deptArray = depts.toArray(new String[depts.size()]);
					for (int i = 0; i < depts.size(); i++) {
						deptArray[i]=depts.get(i);
					}
					userCreateDataBean.setSelectedDepartments(deptArray);
					logger.info("departments"+userCreateDataBean.getSelectedDepartments());
					logger.info("selected departments"+userCreateDataBean.getSelectedDepartments().length);
				}
				v=null;
				v=entitymanager.createQuery("from Login where user_ID=? and status='Active'");
				v.setParameter(1, users.get(0).getUserNo());
				List<Login> loginList=(List<Login>)v.getResultList();
				if(loginList.size()>0){
					v=null;
					v=entitymanager.createQuery("from UserProduct where has_user_ID=? and status='Active'");
					v.setParameter(1, loginList.get(0).getLoginId());
					List<UserProduct> userproductList=(List<UserProduct>)v.getResultList();
					if(userproductList.size()>0){
						List<String> menus=new ArrayList<String>();
						for (int i = 0; i < userproductList.size(); i++) {
							menus.add(userproductList.get(i).getProduct().getProductName());
						}
						logger.info("menus "+menus);
						String[] menuArray = menus.toArray(new String[menus.size()]);
						for (int i = 0; i < menus.size(); i++) {
							menuArray[i]=menus.get(i);
						}
						userCreateDataBean.setSelectedMenus(menuArray);
						logger.info("selected menus"+userCreateDataBean.getSelectedMenus().length);
					}
				}
				
			}
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "";
	}
	
	@Transactional(value="transactionManager")
	public String userUpdate(UserCreateDataBean userCreateDataBean){
		Date date = new Date();Query v=null;int mainMenuID1=0;
		Timestamp time = new Timestamp(date.getTime());
		try {		
				UserCreate user = entitymanager.find(UserCreate.class, userCreateDataBean.getUserID());
				user.setUserName(userCreateDataBean.getCreateUserName());
				user.setUserPassword(userCreateDataBean.getCreateUserPwd());
				user.setUserMailid(userCreateDataBean.getCreateUserMail());
				user.setUserPhone(userCreateDataBean.getCreateUserPhone());
				user.setUserType(userCreateDataBean.getUserType());
				user.setDate(date);
				user.setTime(time);
				entitymanager.merge(user);
				v=entitymanager.createQuery("from UserDepartment where user_no=? and status='Active'");
				v.setParameter(1, userCreateDataBean.getUserID());
				List<UserDepartment> userdepartmentList=(List<UserDepartment>)v.getResultList();
				logger.info("list size"+userdepartmentList.size());
				if(userdepartmentList.size()>0){
					for (int i = 0; i < userdepartmentList.size(); i++) {
						UserDepartment userdept=entitymanager.find(UserDepartment.class, userdepartmentList.get(i).getUser_department_ID());
						userdept.setStatus("DeActive");
						entitymanager.merge(userdept);
						entitymanager.flush();
						entitymanager.clear();
					}
					for (int j2 = 0; j2 < userCreateDataBean.getSelectedDepartments().length; j2++) {
						v=null;
						v=entitymanager.createQuery("from Department where departmentName=?");
						v.setParameter(1, userCreateDataBean.getSelectedDepartments()[j2]);
						List<Department> departmentList=(List<Department>)v.getResultList();
						if(departmentList.size()>0){
							UserDepartment userdept1=new UserDepartment();
							userdept1.setDepartment(entitymanager.find(Department.class, departmentList.get(0).getDepartment_ID()));
							userdept1.setUserNo(entitymanager.find(UserCreate.class, userCreateDataBean.getUserID()));
							userdept1.setStatus("Active");
							entitymanager.persist(userdept1);
							entitymanager.flush();
							entitymanager.clear();
						}
					}
				}
				v=null;
				v=entitymanager.createQuery("from Login where user_ID=? and status='Active'");
				v.setParameter(1, userCreateDataBean.getUserID());
				List<Login> loginList=(List<Login>)v.getResultList();
				if(loginList.size()>0){
					Login login=entitymanager.find(Login.class, loginList.get(0).getLoginId());
					login.setLoginUser(userCreateDataBean.getCreateUserName());
					login.setLoginPassword(userCreateDataBean.getCreateUserPwd());
					entitymanager.merge(login);
					v=null;
					v=entitymanager.createQuery("from UserProduct where has_user_ID=? and status='Active'");
					v.setParameter(1, loginList.get(0).getLoginId());
					List<UserProduct> userproductList=(List<UserProduct>)v.getResultList();
					logger.info("user product list size"+userproductList.size());
					if(userproductList.size()>0){
						for (int i = 0; i < userproductList.size(); i++) {
							UserProduct userprod=entitymanager.find(UserProduct.class, userproductList.get(i).getHas_product_ID());
							userprod.setStatus("DeActive");
							entitymanager.merge(userprod);
							entitymanager.flush();
							entitymanager.clear();
						}
					}
					for (int j2 = 0; j2 < userCreateDataBean.getSelectedMenus().length; j2++) {
						mainMenuID1 = getMainMenu(userCreateDataBean.getSelectedMenus()[j2]);
						UserProduct userprod1=new UserProduct();
						userprod1.setProduct(entitymanager.find(Product.class, mainMenuID1));
						userprod1.setLogin(entitymanager.find(Login.class, loginList.get(0).getLoginId()));
						userprod1.setStatus("Active");
						entitymanager.persist(userprod1);
						entitymanager.flush();
						entitymanager.clear();
					}
				}
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.error("inside exception ",e);
		}
		return "";
	}
	
	@Transactional(value="transactionManager")
	public String userDelete(UserCreateDataBean userCreateDataBean){
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		Query v=null;
		try {		
			UserCreate user = entitymanager.find(UserCreate.class, userCreateDataBean.getUserID());
			user.setStatus("DeActive");
			user.setDate(date);
			user.setTime(time);
			entitymanager.merge(user);
			entitymanager.flush();
			entitymanager.clear();
			v=entitymanager.createQuery("from Login where user_ID=?");
			v.setParameter(1, userCreateDataBean.getUserID());
			List<Login> login=(List<Login>) v.getResultList();
			if(login.size()>0){
				Login logins = entitymanager.find(Login.class, login.get(0).getLoginId());
				logins.setStatus("DeActive");
				entitymanager.merge(logins);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.error("inside exception ",e);
		}
		return "";
	}

	@Transactional(value = "transactionManager")
	public String addAccount(ATransaction payment)
	{
		logger.info("----addAccount-----");
		String status="";
		try{
			AccountType account=new AccountType();
			account.setAccountType(payment.getAccountType());
			//account.setDescription(payment.getDescription());
			account.setStatus("insert");
			entitymanager.persist(account);
			status="success";
		}catch(Exception e){
						logger.error("Error --------------->"+e.getMessage());
		}
		return status;
	}
	
	@Override
	public List<String> getAccountType(String clientID) {
		logger.info("---getAccountType---");
		Query q=null;
		List<String> account=null;
		try{
			account=new ArrayList<String>();
			q=entitymanager.createQuery("select depositTo from AccountDeposit where clientID=? and status='Active'");
			q.setParameter(1, clientID);
			account=(List<String>)q.getResultList();
			logger.info("size -- "+account.size());
		}catch(Exception e){
						logger.error("Error --------------->"+e.getMessage());
		}
		return account;
	}
	@Override
	public String getempID() {
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
		String empID="";
		try{
			Query q=null;
			q = entitymanager.createQuery("from Employee where client_ID=?");
			q.setParameter(1, clientID);
			List<Employee> reult1 = (List<Employee>) q.getResultList();
			int count = 0;
			if (reult1.size() > 0) {
				for (Employee re : reult1) {
					count++;
				}
			}
			if (count == 0) {
				count++;
				empID = "EMP000" + count;
			} else {
				count++;
				empID = "EMP000" + count;
				
			}	
		}catch(Exception e)
		{
						logger.error("Error --------------->"+e.getMessage());
		}
		return empID;
	}
	public List<String> getstatelist(String country) {
		List<String> reult1 =null;Query q=null;
		try{
		   q = entitymanager.createQuery("select stateName from State where country=?");
		   q.setParameter(1, country);
		   reult1 = (List<String>) q.getResultList();
		}
		catch(Exception e){
		   			logger.error("Error --------------->"+e.getMessage());
		}
		return reult1;
	}

	SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");

	@Transactional(value = "transactionManager")
		@Override
		public String getdocUpload(EmployeeDetail employeeDetail) {
			String status="";
			try{
				Document document=new Document();
				document.setDate(employeeDetail.getDocDate()); 
				document.setFileName(employeeDetail.getDocFilename()); 
				document.setDescription(employeeDetail.getDocDescription()); 
				document.setFileType(employeeDetail.getDocFiletype()); 
				if(employeeDetail.getEducationlist().size()>0)
				   {
						for (int i = 0; i < employeeDetail.getEducationlist().size(); i++) {
							logger.info("file "+employeeDetail.getEmpFile1());
							if(employeeDetail.getEmpFile1()!=null){
								 String ss=employeeDetail.getEmpFile1().getContentType();
								    String type = ss.substring(ss.lastIndexOf("/") + 1);
								    String s="doc";
								    logger.info("date "+employeeDetail.getDocDate()+ " file "+employeeDetail.getDocFilename()+
								    		" emp file "+employeeDetail.getEmpFile1().getInputstream()+type);	
								    copyFile12(employeeDetail.getDocDate(),employeeDetail.getDocFilename(), employeeDetail.getEmpFile1().getInputstream(), type);
								    String path = ft.format(employeeDetail.getDocDate()) + "/" +employeeDetail.getDocFilename()+ "." + type;
								    document.setFileUpload(path);
								    document.setStatus("Active"); 	
									logger.info("==========================>"+ document.getDescription());
									entitymanager.persist(document); 
									entitymanager.flush();
									entitymanager.clear();
							}
							
						}
				    			    
				   }
				
				status="success";
			}catch(Exception e){ 
							logger.error("Error --------------->"+e.getMessage());
			}
			
			return status;
		}
		private void copyFile12(Date date, String fileName, InputStream inputstream, String n) {
			logger.info("file insert");
			  try {
			   // Create Directory
			   File files = new File("/home/ec2-user/File_Inacsys/Employee/Photos/"+ ft.format(date)); 
			   if (!files.exists()) {
			    files.mkdirs();
			   } else {
			    logger.info("Alreday Found");
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
			   
			   logger.info("New file created!");
			   } catch (IOException e) {
				   			logger.error("Error --------------->"+e.getMessage());
			   logger.info(e.getMessage());
			  }

			 }

		public List<String> getdepartmentname() {
			Query q=null;List<String> departmentList=new ArrayList<String>();
			try{
				q=entitymanager.createQuery("select departmentName from Department");
				departmentList=q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return departmentList;
		}

		@Override
		public List<I0015> getI0015daily(Report1 report1, String clientID) {
			Query q=null;
			List<I0015> i15=null;
			try{
				logger.info("-----Purchase Delivery -DailyReports-----");
				q = entitymanager.createQuery("from I0015 where orderDate=? and client_ID=? and status=?");
				q.setParameter(1, report1.getReportdate());
				q.setParameter(2, clientID);
				q.setParameter(3, "delivered");
				i15=q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i15;
		}

		/**/
		@Override
		public List<I0015> getI0015weekly(Report1 report1, String clientID) {
			Query q=null;
			List<I0015> i15=null;
			try{
				logger.info("-----Purchase Delivery -WeeklyReports-----");
				q = entitymanager.createQuery("from I0015 where orderDate between ? and ? and client_ID=? and status=?");
				logger.info("report1.getWeek1().get(0)   "+report1.getWeek1().get(0));
				q.setParameter(1, report1.getWeek1().get(0));
				q.setParameter(2, report1.getWeek1().get(report1.getWeek1().size()-1));
				q.setParameter(3, clientID);
				q.setParameter(4, "delivered");
				i15=q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i15;
		}

		@Override
		public List<I0015> getI0015monthly(Report1 report1, String clientID) {
			Query q=null;
			List<I0015> i15=null;
			try{
				logger.info("-----Purchase Delivery -MonthlyReports-----"+report1.getMonth());
				q = entitymanager.createQuery("from I0015 where year(orderDate)= ? and Month(orderDate)=? and client_ID=? and status=?");
				q.setParameter(1, Integer.parseInt(report1.getYear()));
				q.setParameter(2, Integer.parseInt(report1.getMonth()));
				q.setParameter(3, clientID);
				q.setParameter(4, "delivered");
				i15=q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i15;
		}

		@Override
		public List<I0015> getI0015quarterly(Report1 report1, String clientID) {
			Query q=null;
			List<I0015> i15=null;
			try{
				logger.info("-----Purchase Delivery -QuarterlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getQuarterlyreporttype());
			     String fromMonth = st.nextToken("-");
			     String toMonth=report1.getQuarterlyreporttype().substring(report1.getQuarterlyreporttype().lastIndexOf("-")+1);
				logger.info("------split 0----->"+fromMonth);
				logger.info("-----split +1-------->"+toMonth);
				q = entitymanager.createQuery("from I0015 where year(orderDate)= ? and Month(orderDate) >= ? and Month(orderDate) <= ?  and client_ID=? and status=?");
				q.setParameter(1, Integer.parseInt(report1.getYear()));
				q.setParameter(2, Integer.parseInt(fromMonth));
				q.setParameter(3, Integer.parseInt(toMonth));
				q.setParameter(4, clientID);
				q.setParameter(5, "delivered");
				i15=q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i15;
		}

		@Override
		public List<I0015> getI0015halfyearly(Report1 report1, String clientID) {
			Query q=null;
			List<I0015> i15=null;
			try{
				logger.info("-----Purchase Delivery -HalfyearlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getHalfyearlyreporttype());
			     String fromMonth = st.nextToken("-");
			     String toMonth=report1.getHalfyearlyreporttype().substring(report1.getHalfyearlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from I0015 where year(orderDate)= ? and Month(orderDate) >= ? and Month(orderDate) <= ?  and client_ID=? and status=?");
				q.setParameter(1, Integer.parseInt(report1.getYear()));
				q.setParameter(2, Integer.parseInt(fromMonth));
				q.setParameter(3, Integer.parseInt(toMonth));
				q.setParameter(4, clientID);
				q.setParameter(5, "delivered");
				i15=q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i15;
		}

		@Override
		public List<I0015> getI0015annual(Report1 report1, String clientID) {
			Query q=null;
			List<I0015> i15=null;
			try{
				logger.info("-----Purchase Delivery -AnnualReports-----");
				q = entitymanager.createQuery("from I0015 where year(orderDate)= ? and client_ID=? and status=?");
				q.setParameter(1, Integer.parseInt(report1.getYear()));
				q.setParameter(2, clientID);
				q.setParameter(3, "delivered");
				i15=q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i15;
		}

		@Override
		public List<I0016> getvendorname(String orderNumber) {
			Query q=null;
			List<I0016> i16=null;
			try{
				q=null;
				q = entitymanager.createQuery("from I0016 where ordernumber=?");
				q.setParameter(1, orderNumber);
				i16= (List<I0016>) q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i16;
		}

		@Override
		public List<I0016> geti0016paid(String orderNumber) {
			Query q=null;
			List<I0016> i16=null;
			try{
				q = entitymanager.createQuery("from I0016 where ordernumber=? and status2=?");
				q.setParameter(1, orderNumber);
				q.setParameter(2, "paid");
				i16 = (List<I0016>) q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i16;
		}

		@Override
		public List<PurchaseReturn> getpreturndaily(Report1 report1,String clientID) {
			Query q=null;
			List<PurchaseReturn> preturn=null;
			try{
				logger.info("-----Purchase Return -DailyReports-----");
				q = entitymanager.createQuery("from PurchaseReturn where returnDate=? and client_ID=? ");
				q.setParameter(1, report1.getReportdate());
				q.setParameter(2, clientID);
				preturn = (List<PurchaseReturn>) q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return preturn;
		}

		
		@Override
		public List<PurchaseReturn> getpreturnweekly(Report1 report1,
				String clientID) {
			Query q=null;
			List<PurchaseReturn> preturn=null;
			try{
				logger.info("-----Purchase Return -WeeklyReports-----");
				q = entitymanager.createQuery("from PurchaseReturn where  returnDate between ? and ? and client_ID=? ");
				q.setParameter(1, report1.getWeek1().get(0));
				q.setParameter(2, report1.getWeek1().get(report1.getWeek1().size()-1));
				q.setParameter(3, clientID);
				preturn = (List<PurchaseReturn>) q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return preturn;
		}
		@Override
		public List<PurchaseReturn> getpreturnmonthly(Report1 report1,
				String clientID) {
			Query q=null;
			List<PurchaseReturn> preturn=null;
			try{
				logger.info("-----Purchase Return -MonthlyReports-----");
				q = entitymanager.createQuery("from PurchaseReturn where  year(returnDate)=? and  month(returnDate)=? and client_ID=? ");
				q.setParameter(1, Integer.parseInt(report1.getYear()));
				q.setParameter(2, Integer.parseInt(report1.getMonth()));
				q.setParameter(3, clientID);
				preturn = (List<PurchaseReturn>) q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return preturn;
		}

		@Override
		public List<PurchaseReturn> getpreturquarterly(Report1 report1,
				String clientID) {
			Query q=null;
			List<PurchaseReturn> preturn=null;
			try{
				logger.info("-----Purchase Return -QuarterlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getQuarterlyreporttype());
			     String fromMonth = st.nextToken("-");
			     String toMonth=report1.getQuarterlyreporttype().substring(report1.getQuarterlyreporttype().lastIndexOf("-")+1);
				logger.info("------split 0----->"+fromMonth);
				logger.info("-----split +1-------->"+toMonth);
				
				q = entitymanager.createQuery("from PurchaseReturn where  year(returnDate)=? and Month(returnDate) >= ? and Month(returnDate) <= ? and client_ID=? ");
				q.setParameter(1, Integer.parseInt(report1.getYear()));
				q.setParameter(2, Integer.parseInt(fromMonth));
				q.setParameter(3, Integer.parseInt(toMonth));
				q.setParameter(4, clientID);
				preturn = (List<PurchaseReturn>) q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return preturn;
		}

		@Override
		public List<PurchaseReturn> getpreturnhalfyearly(Report1 report1,
					String clientID) {
			Query q=null;
			List<PurchaseReturn> preturn=null;
			try{
				logger.info("-----Purchase Return -HalfyearlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getHalfyearlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getHalfyearlyreporttype().substring(report1.getHalfyearlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from PurchaseReturn where  year(returnDate)=? and Month(returnDate) >= ? and Month(returnDate) <= ? and client_ID=? ");
				q.setParameter(1, Integer.parseInt(report1.getYear()));
				q.setParameter(2, Integer.parseInt(fromMonth));
				q.setParameter(3, Integer.parseInt(toMonth));
				q.setParameter(4, clientID);
				preturn = (List<PurchaseReturn>) q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return preturn;
		}

		@Override
		public List<PurchaseReturn> getpreturnannual(Report1 report1,
				String clientID) {
			Query q=null;
			List<PurchaseReturn> preturn=null;
			try{
				logger.info("-----Purchase Return -AnnualReports-----");
				q = entitymanager.createQuery("from PurchaseReturn where year(returnDate)=? and client_ID=? ");
				q.setParameter(1, Integer.parseInt(report1.getYear()));
				q.setParameter(2, clientID);
				preturn = (List<PurchaseReturn>) q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return preturn;
		}

		@Override
		public List<I0021> getdailysalesdelivery(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i0021=null;
			try{
				logger.info("-----Sales Delivery -DailyReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and salesOrderDate=? and natureOfBusiness=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getReportdate());
				q.setParameter(3, "Normal sales");
				q.setParameter(4, "Delivered");
				i0021=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i0021;
		}

		
		@Override
		public List<I0021> getweeklysalesdelivery(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i0021=null;
			try{
				logger.info("-----Sales Delivery -DailyReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and salesOrderDate between ? and ? and natureOfBusiness=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getWeek1().get(0));
				q.setParameter(3, report1.getWeek1().get(report1.getWeek1().size()-1));
				q.setParameter(4, "Normal sales");
				q.setParameter(5, "Delivered");
				i0021=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i0021;
		}

		@Override
		public List<I0021> getmonthlysalesdelivery(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i0021=null;
			try{
				logger.info("-----Sales Delivery -MonthlyReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and year(salesOrderDate)=? and Month(salesOrderDate)=? and natureOfBusiness=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(report1.getMonth()));
				q.setParameter(4, "Normal sales");
				q.setParameter(5, "Delivered");
				i0021=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i0021;
		}

		@Override
		public List<I0021> getquarterlysalesdelivery(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i0021=null;
			try{
				logger.info("-----Sales Delivery -QuarterlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getQuarterlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getQuarterlyreporttype().substring(report1.getQuarterlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from I0021 where client_ID=? and  year(salesOrderDate)=? and  Month(salesOrderDate) >= ? and Month(salesOrderDate) <= ?  and natureOfBusiness=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));
				q.setParameter(5, "Normal sales");
				q.setParameter(6, "Delivered");
				i0021=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i0021;
		}

		@Override
		public List<I0021> gethalfyearlysalesdelivery(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i0021=null;
			try{
				logger.info("-----Sales Delivery -HalfyearlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getHalfyearlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getHalfyearlyreporttype().substring(report1.getHalfyearlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from I0021 where client_ID=? and  year(salesOrderDate)=? and  Month(salesOrderDate) >= ? and Month(salesOrderDate) <= ?  and natureOfBusiness=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));
				q.setParameter(5, "Normal sales");
				q.setParameter(6, "Delivered");
				i0021=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i0021;
		}

		@Override
		public List<I0021> getannualsalesdelivery(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i0021=null;
			try{

				logger.info("-----Sales Delivery -AnnualReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and year(salesOrderDate)=? and natureOfBusiness=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, "Normal sales");
				q.setParameter(4, "Delivered");
				i0021=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i0021;
		}

		@Override
		public List<I0021> getdailysalespayment(String clientID, Report1 report1) {
			Query q=null;
			List<I0021> i0021paid=null;
			try{
				logger.info("-----Sales Payment -DailyReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and salesOrderDate=? and natureOfBusiness=? and status2=?");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getReportdate());
				q.setParameter(3, "Normal sales");
				q.setParameter(4, "paid");
				i0021paid=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i0021paid;
		}

		
		@Override
		public List<I0021> getweeklysalespayment(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i0021paid=null;
			try{
				logger.info("-----Sales Payment -DailyReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and salesOrderDate between ? and ? and natureOfBusiness=? and status2=?");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getWeek1().get(0));
				q.setParameter(3, report1.getWeek1().get(report1.getWeek1().size()-1));
				q.setParameter(4, "Normal sales");
				q.setParameter(5, "paid");
				i0021paid=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i0021paid;
		}

		@Override
		public List<I0021> getmonthlysalespayment(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i0021paid=null;
			try{
				logger.info("-----Sales Payment -MonthlyReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and year(salesOrderDate)=? and Month(salesOrderDate)=? and natureOfBusiness=? and status2=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(report1.getMonth()));
				q.setParameter(4, "Normal sales");
				q.setParameter(5, "paid");
				i0021paid=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i0021paid;
		}

		@Override
		public List<I0021> getquaterlysalespayment(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i0021paid=null;
			try{
				logger.info("-----Sales Payment -QuarterlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getQuarterlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getQuarterlyreporttype().substring(report1.getQuarterlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from I0021 where client_ID=? and  year(salesOrderDate)=? and  Month(salesOrderDate) >= ? and Month(salesOrderDate) <= ?  and natureOfBusiness=? and status2=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));
				q.setParameter(5, "Normal sales");
				q.setParameter(6, "paid");
				i0021paid=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i0021paid;
		}

		@Override
		public List<I0021> gethalfyearlysalespayment(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i0021paid=null;
			try{
				logger.info("-----Sales Payment -HalfyearlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getHalfyearlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getHalfyearlyreporttype().substring(report1.getHalfyearlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from I0021 where client_ID=? and  year(salesOrderDate)=? and  Month(salesOrderDate) >= ? and Month(salesOrderDate) <= ?  and natureOfBusiness=? and status2=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));
				q.setParameter(5, "Normal sales");
				q.setParameter(6, "paid");
				i0021paid=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i0021paid;
		}

		@Override
		public List<I0021> getannualsalespayment(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i0021paid=null;
			try{
				logger.info("-----Sales Payment -AnnualReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and year(salesOrderDate)=? and natureOfBusiness=? and status2=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, "Normal sales");
				q.setParameter(4, "paid");
				i0021paid=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i0021paid;
		}

		@Override
		public List<SalesReturn> getdailySalesreturn(String clientID,
				Report1 report1) {
			Query q=null;
			List<SalesReturn> sreturn=null;
			try{
				logger.info("-----Sales Return -DailyReports-----");
				q = entitymanager.createQuery("from SalesReturn where client_ID=? and dueDate=? ");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getReportdate());
				sreturn=(List<SalesReturn>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return sreturn;
		}

		@Override
		public List<SalesReturn> getweeklySalesreturn(String clientID,
				Report1 report1) {
			Query q=null;
			List<SalesReturn> sreturn=null;
			try{
				logger.info("-----Sales Return -DailyReports-----");
				q = entitymanager.createQuery("from SalesReturn where client_ID=? and dueDate between ? and ? ");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getWeek1().get(0));
				q.setParameter(3, report1.getWeek1().size()-1);
				sreturn=(List<SalesReturn>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return sreturn;
		}
		@Override
		public List<SalesReturn> getmonthlySalesreturn(String clientID,
				Report1 report1) {
			Query q=null;
			List<SalesReturn> sreturn=null;
			try{
				logger.info("-----Sales Return -MonthlyReports-----");
				q = entitymanager.createQuery("from SalesReturn where client_ID=? and year(dueDate)=? and Month(dueDate)=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(report1.getMonth()));
				sreturn=(List<SalesReturn>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return sreturn;
		}

		@Override
		public List<SalesReturn> getquarterlySalesreturn(String clientID,
				Report1 report1) {
			Query q=null;
			List<SalesReturn> sreturn=null;
			try{
				logger.info("-----Sales Return -QuarterlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getQuarterlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getQuarterlyreporttype().substring(report1.getQuarterlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from SalesReturn where client_ID=? and  year(dueDate)=? and  Month(dueDate) >= ? and Month(dueDate) <= ?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));
				sreturn=(List<SalesReturn>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return sreturn;
		}

		@Override
		public List<SalesReturn> gethalfyearlySalesreturn(String clientID,
				Report1 report1) {
			Query q=null;
			List<SalesReturn> sreturn=null;
			try{
				logger.info("-----Sales Return -HalfyearlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getHalfyearlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getHalfyearlyreporttype().substring(report1.getHalfyearlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from SalesReturn where client_ID=? and  year(dueDate)=? and  Month(dueDate) >= ? and Month(dueDate) <= ?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));
				
				sreturn=(List<SalesReturn>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return sreturn;
		}

		@Override
		public List<SalesReturn> getannualSalesreturn(String clientID,
				Report1 report1) {
			Query q=null;
			List<SalesReturn> sreturn=null;
			try{
				logger.info("-----Sales Return -AnnualReports-----");
				q = entitymanager.createQuery("from SalesReturn where client_ID=? and year(dueDate)=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				sreturn=(List<SalesReturn>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return sreturn;
		}

		@Override
		public List<I0021> getdailyQsalesdelivery(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i21=null;
			try{
				logger.info("-----Quick Sales Delivery -DailyReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and salesOrderDate=? and natureOfBusiness=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getReportdate());
				q.setParameter(3, "Quick sales");
				q.setParameter(4, "Quick sales");
				i21=(List<I0021>) q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i21;
		}
		
		
		@Override
		public List<I0021> getweeklyQsalesdelivery(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i21=null;
			try{
				logger.info("-----Quick Sales Delivery -DailyReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and salesOrderDate between ?and ? and natureOfBusiness=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getWeek1().get(0));
				q.setParameter(3, report1.getWeek1().get(report1.getWeek1().size()-1));
				q.setParameter(4, "Quick sales");
				q.setParameter(5, "Quick sales");
				i21=(List<I0021>) q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i21;
		}

		@Override
		public List<I0021> getmonthlyQsalesdelivery(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i21=null;
			try{
				logger.info("-----Quick Sales Delivery -MonthlyReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and year(salesOrderDate)=? and Month(salesOrderDate)=? and natureOfBusiness=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(report1.getMonth()));
				q.setParameter(4, "Quick sales");
				q.setParameter(5, "Quick sales");
				i21=(List<I0021>) q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i21;
		}

		@Override
		public List<I0021> getquarterlyQsalesdelivery(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i21=null;
			try{
				logger.info("-----Quick Sales Delivery -QuarterlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getQuarterlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getQuarterlyreporttype().substring(report1.getQuarterlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from I0021 where client_ID=? and  year(salesOrderDate)=? and  Month(salesOrderDate) >= ? and Month(salesOrderDate) <= ?  and natureOfBusiness=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));
				q.setParameter(5, "Quick sales");
				q.setParameter(6, "Quick sales");
				i21=(List<I0021>) q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i21;
		}

		@Override
		public List<I0021> gethalfyearlyQsalesdelivery(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i21=null;
			try{
				logger.info("-----Quick Sales Delivery -HalfyearlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getHalfyearlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getHalfyearlyreporttype().substring(report1.getHalfyearlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from I0021 where client_ID=? and  year(salesOrderDate)=? and  Month(salesOrderDate) >= ? and Month(salesOrderDate) <= ? and natureOfBusiness=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));
				q.setParameter(5, "Quick sales");
				q.setParameter(6, "Quick sales");
				i21=(List<I0021>) q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i21;
		}

		@Override
		public List<I0021> getannualQsalesdelivery(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i21=null;
			try{
				logger.info("-----Quick Sales Delivery -AnnualReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and year(salesOrderDate)=? and natureOfBusiness=? and status=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, "Quick sales");
				q.setParameter(4, "Quick sales");
				i21=(List<I0021>) q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i21;
		}

		@Override
		public List<I0021> getdailyQsalespaid(String clientID, Report1 report1) {
			Query q=null;
			List<I0021> i21paid=null;
			try{
				
				logger.info("-----Quick Sales Payment -DailyReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and salesOrderDate=? and natureOfBusiness=? and status2=?");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getReportdate());
				q.setParameter(3, "Quick sales");
				q.setParameter(4, "paid");
				i21paid=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i21paid;
		}
		
		
		@Override
		public List<I0021> getweeklyQsalespaid(String clientID, Report1 report1) {
			Query q=null;
			List<I0021> i21paid=null;
			try{
				
				logger.info("-----Quick Sales Payment -DailyReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and salesOrderDate between ? and ? and natureOfBusiness=? and status2=?");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getWeek1().get(0));
				q.setParameter(3, report1.getWeek1().get(report1.getWeek1().size()-1));
				q.setParameter(4, "Quick sales");
				q.setParameter(5, "paid");
				i21paid=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i21paid;
		}

		@Override
		public List<I0021> getmonthlyQsalespaid(String clientID, Report1 report1) {
			Query q=null;
			List<I0021> i21paid=null;
			try{
				logger.info("-----Quick Sales Payment -MonthlyReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and year(salesOrderDate)=? and Month(salesOrderDate)=? and natureOfBusiness=? and status2=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(report1.getMonth()));
				q.setParameter(4, "Quick sales");
				q.setParameter(5, "paid");
				i21paid=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i21paid;
		}

		@Override
		public List<I0021> getquarterlyQsalespaid(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i21paid=null;
			try{
				logger.info("-----Quick Sales Payment -QuarterlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getQuarterlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getQuarterlyreporttype().substring(report1.getQuarterlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from I0021 where client_ID=? and  year(salesOrderDate)=? and  Month(salesOrderDate) >= ? and Month(salesOrderDate) <= ?  and natureOfBusiness=? and status2=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));
				q.setParameter(5, "Quick sales");
				q.setParameter(6, "paid");
				i21paid=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i21paid;
		}

		@Override
		public List<I0021> gethalfyearlyQsalespaid(String clientID,
				Report1 report1) {
			Query q=null;
			List<I0021> i21paid=null;
			try{
				logger.info("-----Quick Sales Payment -HalfyearlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getHalfyearlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getHalfyearlyreporttype().substring(report1.getHalfyearlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from I0021 where client_ID=? and  year(salesOrderDate)=? and  Month(salesOrderDate) >= ? and Month(salesOrderDate) <= ? and natureOfBusiness=? and status2=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));
				q.setParameter(5, "Quick sales");
				q.setParameter(6, "paid");
				i21paid=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i21paid;
		}

		@Override
		public List<I0021> getannualQsalespaid(String clientID, Report1 report1) {
			Query q=null;
			List<I0021> i21paid=null;
			try{
				logger.info("-----Quick Sales Payment -AnnualReports-----");
				q = entitymanager.createQuery("from I0021 where client_ID=? and year(salesOrderDate)=? and natureOfBusiness=? and status2=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, "Quick sales");
				q.setParameter(4, "paid");
				i21paid=(List<I0021>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return i21paid;
		}

		@Override
		public List<SalesReturn> getdailyQsalesreturn(String clientID,
				Report1 report1) {
			Query q=null;
			List<SalesReturn> qsreturn=null;
			try{
				logger.info("-----Quick Sales Return -DailyReports-----");
				q = entitymanager.createQuery("from SalesReturn where client_ID=? and dueDate=? ");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getReportdate());
				qsreturn=(List<SalesReturn>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return qsreturn;
		}

		
		@Override
		public List<SalesReturn> getweeklyQsalesreturn(String clientID,
				Report1 report1) {
			Query q=null;
			List<SalesReturn> qsreturn=null;
			try{
				logger.info("-----Quick Sales Return -DailyReports-----");
				q = entitymanager.createQuery("from SalesReturn where client_ID=? and dueDate between ? and ? ");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getWeek1().get(0));
				q.setParameter(3, report1.getWeek1().get(report1.getWeek1().size()-1));
				qsreturn=(List<SalesReturn>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return qsreturn;
		}

		@Override
		public List<SalesReturn> getmonthlyQsalesreturn(String clientID,
				Report1 report1) {
			Query q=null;
			List<SalesReturn> qsreturn=null;
			try{
				logger.info("-----Quick Sales Return -MonthlyReports-----");
				q = entitymanager.createQuery("from SalesReturn where client_ID=? and year(dueDate)=? and Month(dueDate)=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(report1.getMonth()));
				qsreturn=(List<SalesReturn>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return qsreturn;
		}

		@Override
		public List<SalesReturn> getquarterlyQsalesreturn(String clientID,
				Report1 report1) {
			Query q=null;
			List<SalesReturn> qsreturn=null;
			try{
				logger.info("-----Quick Sales Return -QuarterlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getQuarterlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getQuarterlyreporttype().substring(report1.getQuarterlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from SalesReturn where client_ID=? and  year(dueDate)=? and  Month(dueDate) >= ? and Month(dueDate) <= ?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));
				qsreturn=(List<SalesReturn>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return qsreturn;
		}

		@Override
		public List<SalesReturn> gethalfyearlyQsalesreturn(String clientID,
				Report1 report1) {
			Query q=null;
			List<SalesReturn> qsreturn=null;
			try{
				logger.info("-----Quick Sales Return -HalfyearlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getHalfyearlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getHalfyearlyreporttype().substring(report1.getHalfyearlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from SalesReturn where client_ID=? and  year(dueDate)=? and  Month(dueDate) >= ? and Month(dueDate) <= ?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));
				qsreturn=(List<SalesReturn>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return qsreturn;
		}

		@Override
		public List<SalesReturn> getannualQsalesreturn(String clientID,
				Report1 report1) {
			Query q=null;
			List<SalesReturn> qsreturn=null;
			try{
				logger.info("-----Quick Sales Return -AnnualReports-----");
				q = entitymanager.createQuery("from SalesReturn where client_ID=? and year(dueDate)=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				qsreturn=(List<SalesReturn>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return qsreturn;
		}

		@Override
		public List<Transaction> getdailyprofit(String clientID, Report1 report1) {
			Query q=null;
			List<Transaction> trans=null;
			try{
				logger.info("-----Profit -DailyReports-----");
				q = entitymanager.createQuery("from Transaction where client_ID=? and payDate=? and transactionStatus=?");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getReportdate());
				q.setParameter(3, "credited");
				trans=(List<Transaction>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return trans;
		}

		
		@Override
		public List<Transaction> getweeklyprofit(String clientID,
				Report1 report1) {
			Query q=null;
			List<Transaction> trans=null;
			try{
				logger.info("-----Profit -DailyReports-----");
				q = entitymanager.createQuery("from Transaction where client_ID=? and payDate between ? and ? and transactionStatus=?");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getWeek1().get(0));
				q.setParameter(3, report1.getWeek1().get(report1.getWeek1().size()));
				q.setParameter(4, "credited");
				trans=(List<Transaction>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return trans;
		}

		@Override
		public List<Transaction> getmonthlyprofit(String clientID,
				Report1 report1) {
			Query q=null;
			List<Transaction> trans=null;
			try{
				logger.info("-----Profit -DailyReports-----");
				logger.info("-----Profit -MonthlyReports-----");
				q = entitymanager.createQuery("from Transaction where client_ID=? and year(payDate)=? and Month(payDate)=?  and transactionStatus=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(report1.getMonth()));
				q.setParameter(4, "credited");
				trans=(List<Transaction>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return trans;
		}
		
		@Override
		public List<Transaction> getquaterlyprofit(String clientID,
				Report1 report1) {
			Query q=null;
			List<Transaction> trans=null;
			try{
				logger.info("-----Profit -QuarterlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getQuarterlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getQuarterlyreporttype().substring(report1.getQuarterlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from Transaction where client_ID=? and  year(payDate)=? and  Month(payDate) >= ? and Month(payDate) <= ? and transactionStatus=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));	
				q.setParameter(5, "credited");
				trans=(List<Transaction>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return trans;
		}

		@Override
		public List<Transaction> gethalfyearlyprofit(String clientID,
				Report1 report1) {
			Query q=null;
			List<Transaction> trans=null;
			try{
				logger.info("-----Profit -HalfyearlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getHalfyearlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getHalfyearlyreporttype().substring(report1.getHalfyearlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from Transaction where client_ID=? and  year(payDate)=? and  Month(payDate) >= ? and Month(payDate) <= ? and transactionStatus=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));
				q.setParameter(5, "credited");
				trans=(List<Transaction>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return trans;
		}

		@Override
		public List<Transaction> getannualprofit(String clientID,
				Report1 report1) {
			Query q=null;
			List<Transaction> trans=null;
			try{
				logger.info("-----Profit -AnnualReports-----");
				q = entitymanager.createQuery("from Transaction where client_ID=? and year(payDate)=? and transactionStatus=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, "credited");
				trans=(List<Transaction>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return trans;
		}

		@Override
		public List<Transaction> getdailyloss(String clientID, Report1 report1) {
			Query q=null;
			List<Transaction> loss=null;
			try{
				logger.info("-----Loss -DailyReports-----");
				q = entitymanager.createQuery("from Transaction where client_ID=? and payDate=? and transactionStatus=?");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getReportdate());
				q.setParameter(3, "debited");
				loss=(List<Transaction>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return loss;
		}

		
		@Override
		public List<Transaction> getweeklyloss(String clientID, Report1 report1) {
			Query q=null;
			List<Transaction> loss=null;
			try{
				logger.info("-----Loss -DailyReports-----");
				q = entitymanager.createQuery("from Transaction where client_ID=? and payDate between ? and ? and transactionStatus=?");
				q.setParameter(1, clientID);
				q.setParameter(2, report1.getWeek1().get(0));
				q.setParameter(3, report1.getWeek1().get(report1.getWeek1().size()-1));
				q.setParameter(4, "debited");
				loss=(List<Transaction>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return loss;
		}

		@Override
		public List<Transaction> getmonthlyloss(String clientID, Report1 report1) {
			Query q=null;
			List<Transaction> loss=null;
			try{
				logger.info("-----Loss -MonthlyReports-----");
				q = entitymanager.createQuery("from Transaction where client_ID=? and year(payDate)=? and Month(payDate)=?  and transactionStatus=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(report1.getMonth()));
				q.setParameter(4, "debited");
				loss=(List<Transaction>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return loss;
		}

		@Override
		public List<Transaction> getquarterlyloss(String clientID,
				Report1 report1) {
			Query q=null;
			List<Transaction> loss=null;
			try{
				logger.info("-----Loss -QuarterlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getQuarterlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getQuarterlyreporttype().substring(report1.getQuarterlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from Transaction where client_ID=? and  year(payDate)=? and  Month(payDate) >= ? and Month(payDate) <= ? and transactionStatus=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));	
				q.setParameter(5, "debited");
				loss=(List<Transaction>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return loss;
		}

		@Override
		public List<Transaction> gethalfyearlyloss(String clientID,
				Report1 report1) {
			Query q=null;
			List<Transaction> loss=null;
			try{
				logger.info("-----Loss -HalfyearlyReports-----");
				StringTokenizer st = new StringTokenizer(report1.getHalfyearlyreporttype());
			    String fromMonth = st.nextToken("-");
			    String toMonth=report1.getHalfyearlyreporttype().substring(report1.getHalfyearlyreporttype().lastIndexOf("-")+1);
				q = entitymanager.createQuery("from Transaction where client_ID=? and  year(payDate)=? and  Month(payDate) >= ? and Month(payDate) <= ? and transactionStatus=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, Integer.parseInt(fromMonth));
				q.setParameter(4, Integer.parseInt(toMonth));
				q.setParameter(5, "debited");
				loss=(List<Transaction>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return loss;
		}

		@Override
		public List<Transaction> getannualloss(String clientID, Report1 report1) {
			Query q=null;
			List<Transaction> loss=null;
			try{
				logger.info("-----Loss -AnnualReports-----");
				q = entitymanager.createQuery("from Transaction where client_ID=? and year(payDate)=? and transactionStatus=?");
				q.setParameter(1, clientID);
				q.setParameter(2, Integer.parseInt(report1.getYear()));
				q.setParameter(3, "debited");
				loss=(List<Transaction>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return loss;
		}
		
		@Override
		public List<String> getmenus() {
			Query q=null;List<String> menuList=new ArrayList<String>();
			try{
				q=entitymanager.createQuery("select productName from Product where status='Active' and productName != 'Settings'");
				menuList=q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return menuList;
		}

		@Override
		@Transactional(value="transactionManager")
		public String insertdepartment(String department) {
			Query q=null;String status="";
			try{
				q=entitymanager.createQuery("from Department where departmentName=?");
				q.setParameter(1, department);
				List<Department> deptList=(List<Department>)q.getResultList();
				if(deptList.size()>0){
					status="Exist";
				}else{
					Department dept=new Department();
					dept.setDepartmentName(department.toUpperCase());
					entitymanager.persist(dept);
				}
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return status;
		}
		
		@Override
		@Transactional(value="transactionManager")
		public String vendorApproval(int i, String approvalStatus) {
			logger.info("inside vendor approval dao method calling");
			Query q=null;String status="Fail";
			try{
				I0025 i0025=entitymanager.find(I0025.class, i);
				if(approvalStatus.equalsIgnoreCase("Approve")){
					i0025.setApprovalStatus("Approved");
				}else if(approvalStatus.equalsIgnoreCase("Reject")){
					i0025.setApprovalStatus("Rejected");
				}
				entitymanager.merge(i0025);
				status="Success";
			}catch(Exception e){
				logger.error("Error --------------->"+e.getMessage());
			}finally{
				q=null;
			}
			return status;
		}
		
		@Override
		@Transactional(value="transactionManager")
		public String customerApproval(int buyer_ID, String approvalStatus) {
			logger.info("inside customer approval dao method calling");
			Query q=null;String status="Fail";
			try{
				I0032 i0032=entitymanager.find(I0032.class, buyer_ID);
				if(approvalStatus.equalsIgnoreCase("Approve")){
					i0032.setApprovalStatus("Approved");
				}else if(approvalStatus.equalsIgnoreCase("Reject")){
					i0032.setApprovalStatus("Rejected");
				}
				entitymanager.merge(i0032);
				status="Success";
			}catch(Exception e){
				logger.error("Error --------------->"+e.getMessage());
			}finally{
				q=null;
			}
			return status;
		}

		public List<ProductViewMB> getcategoryList(String approvalstatus) {
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			Query q = null;List<ProductViewMB> categoryList=new ArrayList<ProductViewMB>();
			try {
				if(userType.equalsIgnoreCase("Maker")){
					q = entitymanager.createQuery("from I0005 where status='Active' and client_ID=? and user_ID=? ORDER BY createdDate DESC");
					q.setParameter(1, clientID);
					q.setParameter(2, userID);
				}else{
					if (approvalstatus=="ApprovalData") {
						q = entitymanager.createQuery("from I0005 where status='Active' and client_ID=? and approvalStatus='draft' ORDER BY createdDate DESC");
						q.setParameter(1, clientID);
					}
					else {
						q = entitymanager.createQuery("from I0005 where status='Active' and client_ID=? ORDER BY createdDate DESC");
						q.setParameter(1, clientID);
					}
				}
				ArrayList<I0005> result = (ArrayList<I0005>) q.getResultList();
				logger.info("dao size" + result.size());
				if(result.size()>0){
					for (int i = 0; i < result.size(); i++) {
						ProductViewMB cate=new ProductViewMB();
						cate.setSerialno(String.valueOf(i+1));  
						cate.setId(String.valueOf(result.get(i).getCategoryId()));
						cate.setCategoryname(result.get(i).getCategoryType());
						cate.setDescription(result.get(i).getDescription());
						cate.setApprovalStatus(result.get(i).getApprovalStatus());
						categoryList.add(cate);
						/*if(result.get(i).getUserID().getUserNo()==Integer.parseInt(userID)){
							categoryList.add(cate);
						}else{
							if(result.get(i).getApprovalStatus().equals("draft")){
								categoryList.add(cate);
							}
						}*/
					}	
				}
			} catch (Exception e) {
				logger.error("Inside Exception", e);
			}
			return categoryList;
		}
		
		@Transactional(value="transactionManager")
		public String categoryApproval(List<ProductViewMB> categoryList) {
			Query q=null;String status="Fail";
			try{
				for (int i = 0; i < categoryList.size(); i++) {
					if(categoryList.get(i).isCategoryCheck()==true){
							I0005 i0005=entitymanager.find(I0005.class, Integer.parseInt(categoryList.get(i).getId()));
							i0005.setApprovalStatus("Approved");
							entitymanager.merge(i0005);
							status="Success";
						}
					}
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return status;
		}

		public List<I0005> getcategoryview(ProductRegister productRegister) {
			Query q=null;List<I0005> categoryList=null;
			try{
				q=entitymanager.createQuery("from I0005 where category_id=? and status='Active'");
				q.setParameter(1, productRegister.getIndustry_ID());
				categoryList=(List<I0005>)q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return categoryList;
		}

		@Transactional(value="transactionManager")
		public String categoryUpdate(ProductRegister productRegister) {
			Query q=null;String status="Fail";
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			try{
				q=entitymanager.createQuery("from I0005 where categoryType=? and client_ID=? and user_ID=? and status='Active'");
				q.setParameter(1, productRegister.getCategory());
				q.setParameter(2, clientID);
				q.setParameter(3, userID);
				List<I0005> categoryList=(List<I0005>)q.getResultList();
				if(categoryList.size()>0){
					if(String.valueOf(categoryList.get(0).getCategoryId()).equals(productRegister.getIndustry_ID())){
						I0005 i0005=entitymanager.find(I0005.class, Integer.parseInt(productRegister.getIndustry_ID()));
						i0005.setCategoryType(productRegister.getCategory());
						i0005.setDescription(productRegister.getDescription());
						i0005.setUpdatedDate(date);
						entitymanager.merge(i0005);
						status="Success";
					}else{
						status="Exist";
					}
				}else{
					I0005 i0005=entitymanager.find(I0005.class, Integer.parseInt(productRegister.getIndustry_ID()));
					i0005.setCategoryType(productRegister.getCategory());
					i0005.setDescription(productRegister.getDescription());
					i0005.setUpdatedDate(date);
					entitymanager.merge(i0005);
					status="Success";
				}
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return status;
		}

		@Transactional(value="transactionManager")
		public String deleteCategory(String id) {
			Query q=null;String status="Fail";
			try{
				I0005 i0005=entitymanager.find(I0005.class, Integer.parseInt(id));
				i0005.setStatus("DeActive");
				entitymanager.merge(i0005);
				status="Success";
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return status;
		}

		@Transactional(value="transactionManager")
		public String productApproval(ArrayList<ProductViewMB> finalList) {
			Query q=null;String status="Fail";
			try{
				for (int i = 0; i < finalList.size(); i++) {
					if(finalList.get(i).isProductCheck()==true){
						I0001 i0001=entitymanager.find(I0001.class, finalList.get(i).getProduct_ID());
						i0001.setApprovalStatus("Approved");
						entitymanager.merge(i0001);
						status="Success";
					}
				}
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return status;
		}
		
		//Stanley
		
		@Transactional(value = "transactionManager")
		public String setcash(Vendor vendor) {
			Query q=null;
			String status="Fail";
			
			try{
				q=entitymanager.createQuery("from Paymentcash where paymentType=? and status='Active'");
				q.setParameter(1, vendor.getNewcash());
				List<Paymentcash> paymentcashlist=(List<Paymentcash>)q.getResultList();
				if(paymentcashlist.size()>0){
					status="Exist";
				}else{
					Paymentcash paymentcash=new Paymentcash();
					paymentcash.setPaymentType(vendor.getNewcash());  
					paymentcash.setStatus("Active");  
					entitymanager.persist(paymentcash); 					
					status="Success";
				}
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return status; 
		}
		

		@Transactional(value = "transactionManager") 
		@Override
		public String setcashupdate(Vendor vendor) {
			Query q=null;
			String status="Fail";
			try{
				q=entitymanager.createQuery("from Paymentcash where paymentType=? and status='Active'");
				q.setParameter(1, vendor.getNewcash());
				List<Paymentcash> paymentcashlist=(List<Paymentcash>)q.getResultList();
				if(paymentcashlist.size()>0){
					status="Exist";
				}else{
					Paymentcash paymentcash=new Paymentcash();
					paymentcash.setPaymentType(vendor.getNewcash()); 
					logger.info("given Dao Values-------------------->"+paymentcash.getPaymentType());   
					entitymanager.merge(paymentcash); 					
					status="Success";
				}
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return status;
		}

		public List<String> getpaytype() {
			Query q=null;
			List<String>tableList=new ArrayList<String>();
			try{
				q=entitymanager.createQuery("select paymentType from Paymentcash where status='Active'"); 
				tableList=q.getResultList();
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			
			return tableList;
		}

		@Override
		public String getvencode(String clientID,String userID) {
			String ID="";
			Query q=null;
			List<I0025>getvencodelist=null;			
			try{
				q=entitymanager.createQuery("from I0025 where client_ID=?");
				q.setParameter(1, clientID); 
				getvencodelist=(List<I0025>)q.getResultList();
				int count=0;
				if(getvencodelist.size() > 0){
					for(I0025 vencodelist:getvencodelist) {
						count ++;
					}
				}
				if(count == 0){
					count++;
					ID="VEND000"+ count;
				}else{
					count++;
					ID="VEND000"+ count;
				}
				logger.info("vendor code----------------->"+ID); 
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return ID;  
		}

		@Override
		public String getcusCode(String clientID,String userID) {
			String ID="";
			Query q=null;
			List<I0032>getvencodelist=null;	
			String value="";
			try{
				q=entitymanager.createQuery("from I0032 where client_ID=?");
				q.setParameter(1, clientID); 
				getvencodelist=(List<I0032>)q.getResultList();
				int count=0;
				if(getvencodelist.size() > 0){
					for(I0032 vencodelist:getvencodelist) {
						count ++;
					}
				}
				if(clientID.equals(Util.getMessage("SIOCOA", "messages"))){
					value="OUOA000";         // changes of stanley for new changes
				}else{
					value="CUSD000";
				}
				if(count == 0){
					count++;
					ID=value+ count;
				}else{
					count++;
					ID=value+ count;
				} 
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return ID; 
		}
		
		@Transactional(value = "transactionManager")
		public String setbuycash(Buyer buyer) {
			Query q=null;String status="Fail";
			try{
				q=entitymanager.createQuery("from Paymentcash where paymentType=? and status='Active'");
				q.setParameter(1, buyer.getNewcash());
				List<Paymentcash> paymentcashlist=(List<Paymentcash>)q.getResultList();
				if(paymentcashlist.size()>0){
					status="Exist";
				}else{
					Paymentcash paymentcash=new Paymentcash();
					paymentcash.setPaymentType(buyer.getNewcash()); 
					paymentcash.setStatus("Active");  
					entitymanager.persist(paymentcash); 
					status="Success";
				}
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return status; 
		}

		@Override
		@Transactional(value="transactionManager")
		public String employeeApproval(List<EmployeeDetailsViewFormMB> employeeDetailList) {
			Query q=null;String status="Fail";
			try{
				for (int i = 0; i < employeeDetailList.size(); i++) {
					if(employeeDetailList.get(i).isEmployeeCheck()==true){
							Employee emp=entitymanager.find(Employee.class, employeeDetailList.get(i).getEmployeeDetailsId());
							emp.setApprovalStatus("Approved");
							entitymanager.merge(emp);
							status="Success";
						}
					}
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return status;
		}
		
		@Override
		@Transactional(value="transactionManager")
		public String payrollApproval(List<EmployeePayroll> value1) {
			Query q=null;String status="Fail";
			try{
				for (int i = 0; i < value1.size(); i++) {
					if(value1.get(i).isPayrollCheck()==true){
							Payroll pay=entitymanager.find(Payroll.class, value1.get(i).getId());
							pay.setApprovalStatus("Approved");
							entitymanager.merge(pay);
							status="Success";
						}
					}
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return status;
		}
		
		@Override
		@Transactional(value="transactionManager")
		public String purchaseApproval(ArrayList<PurchaseOrder> result4) {
			Query q=null;String status="Fail";
			try{
				for (int i = 0; i < result4.size(); i++) {
					if(result4.get(i).isPurchaseCheck()==true){
							I0015 i0015=entitymanager.find(I0015.class, result4.get(i).getPid());
							i0015.setApprovalStatus("Approved");
							entitymanager.merge(i0015);
							status="Success";
						}
					}
			}catch(Exception e){
				logger.error("Error --------------->"+e.getMessage());
			}
			return status;
		}

		@Override
		@Transactional(value="transactionManager")
		public String salesApproval(ArrayList<SalesViewMB> sales) {
			Query q=null;String status="Fail";
			try{
				for (int i = 0; i < sales.size(); i++) {
					if(sales.get(i).isSalesCheck()==true){
							I0021 i0021=entitymanager.find(I0021.class, sales.get(i).getSalesId());
							i0021.setApprovalStatus("Approved");
							entitymanager.merge(i0021);
							status="Success";
						}
					}
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return status;
		}
		
		@Override
		public List<AccountType> accountdescription(String accounttype) {
			Query q=null;
			List<AccountType> List = null;
			try {
				q=entitymanager.createQuery("from AccountType where accountType=? and status='Active'");
				q.setParameter(1, accounttype);
				List=(List<AccountType>)q.getResultList();
			} catch (Exception e) {
							logger.error("Error --------------->"+e.getMessage());
			}
		
			return List;
		}
		
		@Transactional(value="transactionManager")
		public String quicksalesApproval(ArrayList<QuickSaleViewMB> sales1) {
			Query q=null;String status="Fail";
			try{
				for (int i = 0; i < sales1.size(); i++) {
					if(sales1.get(i).isQuicksalesCheck()==true){
							I0021 i0021=entitymanager.find(I0021.class, sales1.get(i).getQsalesid());
							i0021.setApprovalStatus("Approved");
							entitymanager.merge(i0021);
							status="Success";
						}
					}
			}catch(Exception e){
							logger.error("Error --------------->"+e.getMessage());
			}
			return status;
		}
		
		
		public List<String> getquotproductList(String clientID, String userID) {
			logger.info("---------Inside  getquotproductList dao-------");
			Query q=null;List<String> productList=new ArrayList<String>();
			try{
				q=entitymanager.createQuery("select productName from I0001 where client_ID=? and user_ID=? and status='i' and approvalStatus='Approved'");
				q.setParameter(1, clientID);
				q.setParameter(2, userID);
				productList=q.getResultList();
			}catch(Exception e){
				logger.error("Error --------------->"+e.getMessage());
			}finally{
				q=null;
			}
			return productList;
		}

		public List<I0001> getproductVendor(String clientID, String userID,String str) {
			logger.info("---------Inside  getproductVendor dao-------");
			Query q=null;List<I0001> resultList=null;
			try{
				q=entitymanager.createQuery("from I0001 where productName=? and client_ID=? and user_ID=? and status='i' and approvalStatus='Approved'");
				q.setParameter(1, str);
				q.setParameter(2, clientID);
				q.setParameter(3, userID);
				resultList=(List<I0001>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return resultList;
		}

		public List<I0031> getvendorList(int prodId) {
			logger.info("---------Inside  getvendorList dao-------");
			Query q=null;List<I0031> resultList1=null;
			try{
				q=entitymanager.createQuery("from I0031 where product_ID=? and status='i'");
				q.setParameter(1, prodId);
				resultList1=(List<I0031>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return resultList1;
		}
		
		public List<Quotation> getquotationnumber(String clientID, String userID) {
			logger.info("---------Inside  getquotationnumber dao-------");
			Query q=null;List<Quotation> quotationList=null;
			try{
				q=entitymanager.createQuery("from Quotation where client_ID=?");
				q.setParameter(1, clientID);
				quotationList=q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return quotationList;
		}

		@Override
		@Transactional(value="transactionManager")
		public int insertQuotation(String clientID, String userID,List<VendorRegisterFormMB> quotationList, int i,String quotationNumber) {
			logger.info("---------Inside  insertQuotation dao-------");
			Date date=new Date();Query q=null;int quot_id=0;
			try{
				Quotation quotation=new Quotation();
				quotation.setQuotationNumber(quotationNumber);
				quotation.setCreatedDate(date);
				quotation.setProductName(quotationList.get(i).getProductName());
				quotation.setProductCount(quotationList.get(i).getProductCount());
				quotation.setDeliveryDate(quotationList.get(i).getDeliveryDate());
				quotation.setStatus("Active");
				quotation.setApprovalStatus("draft");
				quotation.setClient_ID(clientID);
				quotation.setCreatedDate(date);
				quotation.setUserID(entitymanager.find(UserCreate.class,Integer.parseInt(userID)));
				entitymanager.persist(quotation);
				entitymanager.flush();
				entitymanager.clear();
				q=entitymanager.createQuery("from Quotation");
				List<Quotation> quotList=(List<Quotation>)q.getResultList();
				quot_id=quotList.get(quotList.size()-1).getQuotationId();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return quot_id;
		}

		@Override
		public int getproductid(String clientID, String userID,String productName) {
			logger.info("---------Inside  getproductid dao-------");
			Query q=null;int prod_id=0;
			try{
				q=entitymanager.createQuery("from I0001 where productName=? and client_ID=? and user_ID=? and status='i'");
				q.setParameter(1, productName);
				q.setParameter(2, clientID);
				q.setParameter(3, userID);
				List<I0001> prodList=(List<I0001>)q.getResultList();
				prod_id=prodList.get(0).getProduct_ID();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return prod_id;
		}

		public int getvendorid(String clientID, String userID, String vendorName) {
			logger.info("---------Inside  getvendorid dao-------");
			Query q=null;int vendor_id=0;
			try{
				q=entitymanager.createQuery("from I0025 where vendorPhoneNumber=? and client_ID=? and user_ID=? and status='i'");
				q.setParameter(1, vendorName);
				q.setParameter(2, clientID);
				q.setParameter(3, userID);
				List<I0025> vendorList=(List<I0025>)q.getResultList();
				vendor_id=vendorList.get(0).getVendor_ID();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return vendor_id;
		}

		public int getproductPrice(int product_id, int vendor_id) {
			logger.info("---------Inside  getproductPrice dao-------");
			Query q=null;int prod_price=0;
			try{
				q=entitymanager.createQuery("from I0031 where product_ID=? and vendor_ID=? and status='i'");
				q.setParameter(1, product_id);
				q.setParameter(2, vendor_id);
				List<I0031> prodList=(List<I0031>)q.getResultList();
				prod_price=Integer.parseInt(prodList.get(0).getProductPrice());
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return prod_price;
		}
		
		@Transactional(value="transactionManager")
		public String insertQuotationDetails(int quot_id, int vendor_id,int product_price, int product_id, 
				String productName,String vendorName, String productCount) {
			logger.info("---------Inside insert quotationdetails dao-------");
			String status="Fail";
			try{
				QuotationDetail quotdetail=new QuotationDetail();
				quotdetail.setQuotationId(entitymanager.find(Quotation.class, quot_id));
				quotdetail.setVendor_ID(entitymanager.find(I0025.class, vendor_id));
				quotdetail.setProduct_ID(entitymanager.find(I0001.class, product_id));
				quotdetail.setProductName(productName);
				quotdetail.setVendorName(vendorName);
				quotdetail.setProductPrice(product_price);
				if(productCount.equals("") || product_price == 0){
					quotdetail.setTotalPrice(null);
				}else{
					quotdetail.setTotalPrice(new BigDecimal(product_price).multiply(new BigDecimal(productCount)).toString());
				}
				quotdetail.setStatus("Active");
				entitymanager.persist(quotdetail);
				entitymanager.flush();
				entitymanager.clear();
				status="Success";
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
			}
			return status;
		}
		
		@Override
		public List<Quotation> getquotationApprovallist(String clientID,String userID, List<VendorRegisterFormMB> quotationList, int i) {
			logger.info("----------------------inside getquotationApprovallist dao------------------");
			Query q=null;List<Quotation> list=null;
			try{
				q=entitymanager.createQuery("from Quotation where quotationNumber=? and client_ID=?");
				q.setParameter(1, quotationList.get(i).getQuotationNumber());
				q.setParameter(2, clientID);
				list=(List<Quotation>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return list;
		}

		@Override
		@Transactional(value="transactionManager")
		public String quotationApproval(int quotationId) {
			logger.info("----------------------inside quotation approval dao------------------");
			Query q=null;String status="Fail";
			try{	
				Quotation quot=entitymanager.find(Quotation.class, quotationId);
				quot.setApprovalStatus("Approved");
				entitymanager.merge(quot);
				entitymanager.flush();
				entitymanager.clear();
				status="Success";
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return status;
		}
		
		@Transactional(value="transactionManager")
		public String insertQuotationDetails(int quot_id, int product_price,int product_id, String productName, String productCount){
			logger.info("--------------inside insert quotationdetails dao---------------");
			String status="Fail";
			try{
				QuotationDetail quotdetail=new QuotationDetail();
				quotdetail.setQuotationId(entitymanager.find(Quotation.class, quot_id));
				quotdetail.setProduct_ID(entitymanager.find(I0001.class, product_id));
				quotdetail.setVendor_ID(null);
				quotdetail.setProductName(productName);
				quotdetail.setVendorName(null);
				quotdetail.setProductPrice(product_price);
				if(productCount.equals("") || product_price == 0){
					quotdetail.setTotalPrice(null);
				}else{
					quotdetail.setTotalPrice(new BigDecimal(product_price).multiply(new BigDecimal(productCount)).toString());
				}
				quotdetail.setStatus("Active");
				entitymanager.persist(quotdetail);
				entitymanager.flush();
				entitymanager.clear();
				status="Success";
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
			}
			return status;
		}		
		@Override
		public List<Quotation> getquotationDetails(String clientID,String quotationNumber) {
			logger.info("----------Inside getquotationDetails method calling----------");
			Query q=null;List<Quotation> quotationList=null;
			try{
				q=entitymanager.createQuery("from Quotation where client_ID=? and quotationNumber=? and status='Active'");
				q.setParameter(1, clientID);
				q.setParameter(2, quotationNumber);
				quotationList=(List<Quotation>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return quotationList;
		}

		@Override
		public List<QuotationDetail> getquotationDetailsList(int quotationId) {
			logger.info("----------Inside getquotationDetailsList method calling----------");
			Query q=null;List<QuotationDetail> quotdetailList=null;
			try{
				q=entitymanager.createQuery("from QuotationDetail where quotation_id=? and status='Active' ORDER BY product_name,product_price ASC");
				q.setParameter(1, quotationId); 
				quotdetailList=(List<QuotationDetail>)q.getResultList();
				logger.debug("quotation detail list size--->"+quotdetailList.size());
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return quotdetailList;
		}
		
		@Override
		@Transactional(value="transactionManager")
		public String deleteQuotation(int quotationId) {
			logger.info("----------Inside deleteQuotation method calling----------");
			String status="Fail";
			try{
				Quotation quot=entitymanager.find(Quotation.class, quotationId);
				quot.setStatus("DeActive");
				entitymanager.merge(quot);
				entitymanager.flush();
				entitymanager.clear();
				status="Success";
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
			}
			return status;
		}

		@Override
		@Transactional(value="transactionManager")
		public String deleteQuotationdetail(int quotationDetailsId) {
			logger.info("----------Inside deleteQuotationdetail method calling----------");
			String status="Fail";
			try{
				QuotationDetail quotdetail=entitymanager.find(QuotationDetail.class, quotationDetailsId);
				quotdetail.setStatus("DeActive");
				entitymanager.merge(quotdetail);
				entitymanager.flush();
				entitymanager.clear();
				status="Success";
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
			}
			return status;
		}

		@Override
		@Transactional(value="transactionManager")
		public void finalQuotation(int quotationId) {
			logger.info("----------Inside finalQuotation method calling----------");
			Query q=null;
			try{
				Quotation quot=entitymanager.find(Quotation.class, quotationId);
				quot.setChoosenStatus("Choosed");
				entitymanager.merge(quot);
				entitymanager.flush();
				entitymanager.clear();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
		}

		@Override
		@Transactional(value="transactionManager")
		public String finalQuotationDetail(int quotationDetailsId,String choosenStatus) {
			logger.info("----------Inside finalQuotationDetail method calling----------");
			Query q=null;String status="Fail";
			try{
				QuotationDetail quot=entitymanager.find(QuotationDetail.class, quotationDetailsId);
				quot.setChoosenStatus(choosenStatus);
				entitymanager.merge(quot);
				entitymanager.flush();
				entitymanager.clear();
				status="Success";
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return status;
		}
		
		@Override
		public List<Quotation> getfinalquotationList(String clientID,String quotationNumber) {
			logger.info("----------Inside getfinalquotationList method calling----------");
			Query q=null;List<Quotation> quotList=null;
			try{
				q=entitymanager.createQuery("from Quotation where client_ID=? and quotationNumber=? and choosenStatus='Choosed' and status='Active'");
				q.setParameter(1, clientID);
				q.setParameter(2, quotationNumber);
				quotList=(List<Quotation>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return quotList;
		}
	
		@Override
		public List<QuotationDetail> getdinalquotationDetailList(int quotationId) {
			logger.info("----------Inside getfinalquotationDetailList method calling----------");
			Query q=null;List<QuotationDetail> quotdetailList=null;
			try{
				q=entitymanager.createQuery("from QuotationDetail where quotation_id=? and status='Active' and choosenStatus='Choosed'");
				q.setParameter(1, quotationId);
				quotdetailList=(List<QuotationDetail>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return quotdetailList;
		}
		
		@Override
		@Transactional(value="transactionManager")
		public int updateQuotation(String clientID, String userID,List<VendorRegisterFormMB> quotationList, int i,String quotationNumber) {
			logger.info("----------Inside updateQuotation method calling----------");
			Date date=new Date();Query q=null;int quot_id=0;
			try{
				Quotation quotation=new Quotation();
				quotation.setQuotationNumber(quotationNumber);
				quotation.setCreatedDate(date);
				quotation.setProductName(quotationList.get(i).getProductName());
				quotation.setProductCount(quotationList.get(i).getProductCount());
				quotation.setDeliveryDate(quotationList.get(i).getDeliveryDate());
				quotation.setStatus("Active");
				quotation.setUpdatedDate(date);
				quotation.setApprovalStatus("Approved");
				quotation.setClient_ID(clientID);
				quotation.setUserID(entitymanager.find(UserCreate.class,Integer.parseInt(userID)));
				entitymanager.persist(quotation);
				entitymanager.flush();
				entitymanager.clear();
				q=entitymanager.createQuery("from Quotation");
				List<Quotation> quotList=(List<Quotation>)q.getResultList();
				quot_id=quotList.get(quotList.size()-1).getQuotationId();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return quot_id;
		}

		@Override
		@Transactional(value="transactionManager")
		public void removeEditRow(int quotationDetailsId) {
			logger.info("----------Inside removeEditRow method calling----------");
			try{
				QuotationDetail quotdetail=entitymanager.find(QuotationDetail.class, quotationDetailsId);
				quotdetail.setStatus("DeActive");
				entitymanager.merge(quotdetail);
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
			}
		}

		@Override
		public int getvendorapprovalCount(String clientID) {
			logger.info("----------Inside getvendorapprovalCount method calling----------");
			Query q=null;List<I0025> list=null;
			try{
				q=entitymanager.createQuery("from I0025 where client_ID=? and status='i' and approvalStatus='draft'");
				q.setParameter(1, clientID);
				list=(List<I0025>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return list.size();
		}

		@Override
		public int getcustomerapprovalCount(String clientID) {
			logger.info("----------Inside getcustomerapprovalCount method calling----------");
			Query q=null;List<I0032> list=null;
			try{
				q=entitymanager.createQuery("from I0032 where client_ID=? and status='in' and approvalStatus='draft'");
				q.setParameter(1, clientID);
				list=(List<I0032>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return list.size();
		}

		@Override
		public int getpurchaseapprovalCount(String clientID) {
			logger.info("----------Inside getpurchaseapprovalCount method calling----------");
			Query q=null;List<I0015> list=null;
			try{
				q=entitymanager.createQuery("from I0015 where client_ID=? and status='insert' and approvalStatus='draft'");
				q.setParameter(1, clientID);
				list=(List<I0015>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return list.size();
		}

		@Override
		public int getsalesapprovalCount(String clientID, String str) {
			logger.info("----------Inside getsalesapprovalCount method calling----------");
			Query q=null;List<I0021> list=null;
			try{
				if(str.equals("Sales")){
					q=entitymanager.createQuery("from I0021 where client_ID=? and natureOfBusiness='Normal sales' and status='insert' and approvalStatus='draft'");
				}else if(str.equals("Quick Sales")){
					q=entitymanager.createQuery("from I0021 where client_ID=? and status='Quick sales' and natureOfBusiness='Quick sales' and approvalStatus='draft'");
				}
				q.setParameter(1, clientID);
				list=(List<I0021>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return list.size();
		}

		@Override
		public int getemployeeapprovalCount(String clientID) {
			logger.info("----------Inside getemployeeapprovalCount method calling----------");
			Query q=null;List<Employee> list=null;
			try{
				q=entitymanager.createQuery("from Employee where client_ID=? and status='inserted' and approvalStatus='draft'");
				q.setParameter(1, clientID);
				list=(List<Employee>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return list.size();
		}

		@Override
		public int getpayrollapprovalCount(String clientID) {
			logger.info("----------Inside getpayrollapprovalCount method calling----------");
			Query q=null;List<Payroll> list=null;
			try{
				q=entitymanager.createQuery("from Payroll where client_ID=? and status='inserted' and approvalStatus='draft'");
				q.setParameter(1, clientID);
				list=(List<Payroll>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return list.size();
		}

		@Override
		public int getcategoryapprovalCount(String clientID) {
			logger.info("----------Inside getcategoryapprovalCount method calling----------");
			Query q=null;List<I0005> list=null;
			try{
				q=entitymanager.createQuery("from I0005 where client_ID=? and status='Active' and approvalStatus='draft'");
				q.setParameter(1, clientID);
				list=(List<I0005>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return list.size();
		}

		@Override
		public int getproductapprovalCount(String clientID) {
			logger.info("----------Inside getproductapprovalCount method calling----------");
			Query q=null;List<I0001> list=null;
			try{
				q=entitymanager.createQuery("from I0001 where client_ID=? and status='i' and approvalStatus='draft'");
				q.setParameter(1, clientID);
				list=(List<I0001>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return list.size();
		}

		@Override
		public int getquotationapprovalCount(String clientID) {
			logger.info("----------Inside getquotationapprovalCount method calling----------");
			Query q=null;List<String> numberlist=null;
			try{
				numberlist=new ArrayList<String>();
				q=entitymanager.createQuery("from Quotation where client_ID=? and status='Active' and approvalStatus='draft'");
				q.setParameter(1, clientID);
				List<Quotation> list=(List<Quotation>)q.getResultList();
				for (int i = 0; i < list.size(); i++) {
					numberlist.add(list.get(i).getQuotationNumber());
				}
				HashSet<String> hashList=new HashSet<String>(numberlist);
				numberlist.clear();
				numberlist.addAll(hashList);
			}catch(Exception e){
				logger.warn("inside exception"+e);
			}finally{
				q=null;
			}
			return numberlist.size();
		}
		
/* stanley code for crm */

		@Override
		public List<String> getstatusList() 
		{
			logger.info("inside insert getstatusList");
			List<String>statustablist=null;
			Query q=null;
			try{
				q=entitymanager.createQuery("select status from CrmDropdown");
				statustablist=(List<String>)q.getResultList();
				System.out.println("si----->"+statustablist.size()); 
			}catch(Exception e){
				logger.debug(e.getMessage());
				//e.printStackTrace();
			}
			return statustablist;
		}
		
		@Override
		public String getcmtcode()
		{
			logger.info("inside insert getcmtcode");
			String ID="";
			Query q=null;
			List<CrmCustomerdetail>tabList=null;
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			try{
				q=entitymanager.createQuery("from CrmCustomerdetail where client_ID=? and user_ID=?");
				q.setParameter(1, clientID); 
				q.setParameter(2, userID);
				tabList=(List<CrmCustomerdetail>)q.getResultList();
				System.out.println("cmtcodelist------->"+tabList.size()); 
				int count=0;
				//If the database has already CRM Number
				if(tabList.size() > 0){
					System.out.println("tablist size in cmt---->"+tabList.size()); 
					for(CrmCustomerdetail getlist:tabList){
						count ++;
						System.out.println("count size in cmt---->"+count); 
					}
				}
				// IF the database value matches with zero 
				if(count == 0){
					System.out.println("if condition in cmtcode");
					count++;
					ID="CRM000"+ count;
					System.out.println("count size in cmt1---->"+count); 
				}if(count >0)//else
					{
					System.out.println("else condition in cmtcode");
					count++;
					ID="CRM000"+ count;
				}
				System.out.println("id---->"+ID); 
			}catch(Exception e){
				logger.debug(e.getMessage());
				//e.printStackTrace();
			}
			return ID;
		}
		
		@Override
		public List<String> getcrmtype()
		{
			Query q=null;
			List<String>tableList=new ArrayList<String>();
			try{
				q=entitymanager.createQuery("select industry from CrmIndustry"); 
				tableList=q.getResultList();
				System.out.println("tab--->"+tableList); 
			}catch(Exception e){
				logger.debug(e.getMessage());
					//e.printStackTrace();
			}
			return tableList;
		}
		
		@Override
		public List<String> getcrmproduct() 
		{
			Query q=null;
			List<String>productlist=new ArrayList<String>();
			try{
				q=entitymanager.createQuery("select productName from I0001");
				productlist=q.getResultList();
				System.out.println("productlist size---->"+productlist.size()); 
			}catch(Exception e){
				logger.debug(e.getMessage());
				//e.printStackTrace();
			}
			return productlist;
		}
		
		@Transactional(value = "transactionManager")
		@Override
		public String getCrmIndustrySave(Buyer buyer)
		{
			Query q=null;
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			String status="Fail";
			try{
				q=entitymanager.createQuery("from CrmIndustry where industry=? and client_ID=? and user_ID=?");
				q.setParameter(1, buyer.getCrmcash());
				q.setParameter(2, clientID);
				q.setParameter(3, Integer.parseInt(userID));
				List<CrmIndustry> paymentcashlist=(List<CrmIndustry>)q.getResultList();
				System.out.println("paymentcashlist-------->"+paymentcashlist.size()); 
				if(paymentcashlist.size()>0){
					status="Exist";
				}else{
					CrmIndustry crmindustry=new CrmIndustry();
					crmindustry.setIndustry(buyer.getCrmcash()); 
					crmindustry.setClient_ID(clientID); 
					crmindustry.setUserID(entitymanager.find(UserCreate.class, Integer.parseInt(userID)));
					entitymanager.persist(crmindustry); 
					System.out.println("industry----->"+crmindustry.getIndustry()); 
					status="Success";
				}
				System.out.println("setbuycash1------>"+status); 
			}catch(Exception e){
				logger.debug(e.getMessage());
				//	e.printStackTrace();
			}
			return status; 
		}	
		
		@Transactional(value="transactionManager")
		public String saveCrm(Buyer buyer) {
			logger.info("inside insert saveCrm");
			String status="fail";
			CrmCustomerdetail crm=null;
			try{
				String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
				String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
				crm=new CrmCustomerdetail();
				crm.setTitle(buyer.getCustomerTitle()); 
				crm.setPersonName(buyer.getCustomerName()); 
				crm.setMiddleName(buyer.getCustomerMiddleName()); 
				crm.setCompany(buyer.getCustomerLastName()); 
				crm.setSuffix(buyer.getCustomerSuffix()); 
				crm.setEmail1(buyer.getMail()); 
				crm.setCode(buyer.getCode()); 
				crm.setMobileNo_1(buyer.getPhoneNumber()); 
				crm.setMobileNo_2(buyer.getCustMobile()); 
				crm.setIndustry(buyer.getCategoryName());  
				crm.setOther(buyer.getOther()); 
				crm.setDate(buyer.getDate()); 
				crm.setCompanyName(buyer.getCompany()); 
				crm.setWebsite1(buyer.getWebsite()); 
				crm.setTaxNo(buyer.getTaxnumber()); 
				crm.setFaxNo_1(buyer.getFilePath()); 
				crm.setDisplayName(buyer.getDisplayName()); 
				crm.setLicenceNo(buyer.getCusLicence()); 
				crm.setCustomerType(buyer.getCusType()); 
				crm.setExpireDate(buyer.getCusExdate()); 
				crm.setEmail2(buyer.getEmail2()); 
				crm.setCustomerComments(buyer.getCrmcomments()); 
				crm.setWebsite2(buyer.getCrmwebsite2());   
				crm.setStatus(buyer.getCrmstatus()); 
				crm.setCrmCode(buyer.getCrmcode()); 
				crm.setCreatedDate(date); 
				crm.setUpdateDate(date);  
				crm.setFollowupDate(Util.getCurrentDate());
				crm.setCustomerProduct(buyer.getCrmcusproduct()); 
				crm.setModeOfCommunication(buyer.getCrmmodeofcommunications()); 
				crm.setCreatedPerson(buyer.getCrmcreatedperson()); 
				crm.setUpdatePerson(buyer.getCrmupdatedperson());
				crm.setPresentAddress(buyer.getAddress()); 
				crm.setPermenantAddress(buyer.getPermanentaddress()); 
				crm.setPresentCity(buyer.getPresentCity()); 
				crm.setPresentCountry(buyer.getPresentcountryID()); 
				crm.setPermenantCity(buyer.getCity()); 
				crm.setPermenantCountry(buyer.getCountry()); 
				crm.setPresentPostalcode(buyer.getPrePostCode()); 
				crm.setPresentState(buyer.getPresentstate()); 
				crm.setPermenantPostalcode(buyer.getPerPostCode()); 
				crm.setPermenantState(buyer.getState()); 
				crm.setNotes(buyer.getNote()); 
				System.out.println("dao fax number----->"+crm.getFaxNo_1()); 
				crm.setClient_ID(clientID); 
				crm.setUserID(entitymanager.find(UserCreate.class, Integer.parseInt(userID))); 
				entitymanager.persist(crm);
				System.out.println("after persist fax number---->"+crm.getFaxNo_1()); 
				status="success";
				logger.debug("inside insert saveCrm success"+status);
			}catch(Exception e){
				logger.debug("Exception -->"+e.getMessage());
				//e.printStackTrace();
			}
			
			return status;
		}
		
		@Transactional(value="transactionManager")
		@Override
		public List<CrmCustomerdetail> getcrmenquiry(String query) {
			Query q=null;
			ArrayList<CrmCustomerdetail>getlist=null;
			try{
				q=entitymanager.createQuery(query);						
				getlist=(ArrayList<CrmCustomerdetail>)q.getResultList();	
				
			}catch(Exception e){
				logger.debug("Exception -->"+e.getMessage());
				//e.printStackTrace(); 
			}
			return getlist;
		}
		
		/*stanley for salesQuote and quoteConsole start*/
		// Sales quote
		@Override
		public List<String> getcustomername() {
			logger.info("[getcustomername()]------------------- inside getcustomername method() ---------------");
			Query q =null;
			List<String>list=new ArrayList<String>();
			try{
				String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
				q=entitymanager.createQuery("SELECT CONCAT(customer_name , '-' ,e_mail ) FROM  I0032  where client_ID=?  order by customer_name desc");
				q.setParameter(1, clientID);
				list=q.getResultList();
			}catch(Exception e){
				logger.error("inside exception ",e);
			}
			return list;
		}

		@Override
		public List<String> getcustomerDetails(Sales sales) {
			logger.info("[getcustomerDetails()]------------------- inside getcustomerDetails method() ---------------");
			List<I0032>customerDetails=null;
			Query q=null;
			try{
				String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
				String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
				q=entitymanager.createQuery("from I0032 where customerName=? and eMail=? and client_ID=? and user_ID=?");
				System.out.println("Customer name dao -->"+sales.getCustomerName());
				q.setParameter(1, sales.getCustomerName());
				q.setParameter(2, sales.getEmail());
				q.setParameter(3, clientID);
				q.setParameter(4, userID);
				customerDetails=(List<I0032>)q.getResultList();
				System.out.println("---- dao getcustomerDetails method customerDetails size --->"+customerDetails.size());
				if(customerDetails.size() >0 ){ 
					sales.setCustomername1(customerDetails.get(0).getCustomerName());
					sales.setBusinessname(customerDetails.get(0).getCompany());
					sales.setPhoneNumber(customerDetails.get(0).getPhoneNumber());
					sales.setMobile(customerDetails.get(0).getCustomerMobile());
					sales.setPermanentaddress(customerDetails.get(0).getAddress()); 
					sales.setEmail(customerDetails.get(0).getEMail());
				}
				if(customerDetails.size()==0)
				{
					System.out.println("Database no record found !!!");
				}
			}catch(Exception e){
				logger.error("inside exception ",e);  
			}
			
			return null;
		}
		
		@Override
		public List<String> getproductlist(String clientID, String userID) {
			logger.info("[getproductlist()]------------------- inside getproductlist method() ---------------");
			Query q=null;
			List<String>list1=new ArrayList<String>();
			try{
				q=entitymanager.createQuery("select productName from I0001 where client_ID=? and user_ID=? and status='i' and approvalStatus='Approved'");
				q.setParameter(1, clientID);
				q.setParameter(2, userID);
				list1=q.getResultList();
			}catch(Exception e){
				logger.error("inside exception ",e);
			}
			return list1; 
		}

		@Override
		public String getUnitprice(String productname) throws DemoException {
			logger.info("[getUnitprice()]------------------- inside getUnitprice method() ---------------");
			Query q=null;
			List<I0001>unitprice=null;
			String unit="";
			try{
				String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
				String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
				q=entitymanager.createQuery("from I0001 where productName=? and client_ID=? and user_ID=?");
				q.setParameter(1, productname);
				q.setParameter(2, clientID); 
				q.setParameter(3, userID); 
				unitprice=(List<I0001>)q.getResultList();
				unit=unitprice.get(0).getMarginPrice();
				System.out.println("inside dao method getUnitprice() unitprice size--->"+unitprice.size());
			}catch(Exception e){
				logger.error("inside exception ",e);
			}
			
			return unit; 
		}

		@Override
		@Transactional(value="transactionManager")
		public String insertNewouote(String clientID, String userID,ArrayList<SalesOrderFormMB> mblist,Sales sales) {
			logger.info("[insertNewouote()]------------------- inside insertNewouote method() ---------------");
			Query q=null;
			String status="";
			Date date;
			try{
				System.out.println("Client ID -->"+clientID);
				date = new Date();			
		       Set<SalesQuoteDetails> salquotedetailshashtable =null;// new HashSet<SalesQuoteDetails>();
				System.out.println("Test 2");
		        SalesQuoteDetails salesquotedetails = null; 
		        SalesQuote salesquote=null;
		        	salquotedetailshashtable = new HashSet<SalesQuoteDetails>();
		        	salesquote = new SalesQuote(getquotationcode(),"PENDING FOR APPROVAL","ACTIVE",date,sales.getCustomername1(),				
							sales.getPhoneNumber(),sales.getMobile(),sales.getEmail(),sales.getBusinessname(),sales.getPermanentaddress(),clientID,userID);		
		        	for(SalesOrderFormMB s : mblist){
						System.out.println("Test 3"); 
						if(s.getProductName() != null && s.getProductName() != "") {
			        	System.out.println("Inside for loop "+s.getProductName());		        	
			        	salesquotedetails = new SalesQuoteDetails();
			        	salesquotedetails.setName(s.getProductName());
			        	salesquotedetails.setDescription(s.getQuantity());
			        	salesquotedetails.setNetprize(s.getNetAmount()); 
			        	salesquotedetails.setPrice(s.getUnit()); 		        	
			        	salesquotedetails.setSalesquote(salesquote);  		        	
			        	salquotedetailshashtable.add(salesquotedetails);
						}
						else 
						{
							logger.info("[insertNewouote()]------------------- Value is null ---------------");
						}
					}
			        salesquote.setSalesquotedetails(salquotedetailshashtable);
			        entitymanager.persist(salesquote);
					status="success";
			}catch(Exception e){
				logger.error("inside exception ",e);
			}
			
			return status;
		}

		@Override
		public Map<SalesOrderFormMB, ArrayList<SalesOrderFormMB>> getQuoteview(Map<SalesOrderFormMB, ArrayList<SalesOrderFormMB>> maps) {
			logger.info("[getQuoteview()]------------------- inside getQuoteview method() ---------------");
			Query q=null;
			ArrayList<SalesQuote>getlist=null;
			Set <SalesQuoteDetails> sets=null;
			ArrayList<SalesOrderFormMB> clist=null;
			try{
				sets = new HashSet <SalesQuoteDetails>();
				String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
				String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
				String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
				/*getlist= (ArrayList<SalesQuote>) entitymanager.createQuery("from SalesQuote where client_ID=? and status='ACTIVE' order by sales_quote_number desc").getResultList();
				q.setParameter(1, clientID);*/
				if(userType.equals("Maker")){
					q=entitymanager.createQuery("from SalesQuote where client_ID=? and user_ID=? and status='ACTIVE' order by sales_quote_number desc");
					q.setParameter(1, clientID);
					q.setParameter(2, userID);
				}else{
					q=entitymanager.createQuery("from SalesQuote where client_ID=? and status='ACTIVE' order by sales_quote_number desc");
					q.setParameter(1, clientID);
				}
				getlist=(ArrayList<SalesQuote>)q.getResultList();
				SalesOrderFormMB sales=null;
				SalesOrderFormMB innersales=null;
				for (SalesQuote s : getlist){
					sales = new SalesOrderFormMB();
					System.out.println("[getQuoteview-parent] Name "+s.getBusinessName());
					System.out.println("[getQuoteview-parent] Approval status "+s.getApprovalStatus());
					System.out.println("[getQuoteview-parent] Sales quote number "+s.getSales_quote_number());
					System.out.println("[getQuoteview-parent] Phone number "+s.getMobileNumber());
					System.out.println("[getQuoteview-parent] Business name "+s.getBusinessName());
				    sales.setProductName(s.getCustomerName());
					sales.setQuotationcode(s.getSales_quote_number());
					sales.setPhoneNumber(s.getTelephone()); 
					sales.setApprovalStatus(s.getApprovalStatus()); 
					sales.setQuoteID(s.getSales_quote_ID()); 
					sales.setBusinessname(s.getBusinessName());
					sales.setMobile(s.getMobileNumber());
					sales.setEmail(s.getEmail());
					sales.setPermanentaddress(s.getAddress());
					clist =new ArrayList<SalesOrderFormMB>();
					sets = s.getSalesquotedetails();  
					Iterator iterator = sets.iterator(); 				      
					int j=0;
					   while (iterator.hasNext()){
						innersales = new SalesOrderFormMB();
						SalesQuoteDetails sdr = (SalesQuoteDetails) iterator.next();
						System.out.println("[getQuoteview-child] Product name --> "+sdr.getName());
			            System.out.println("[getQuoteview-child] Net Prize "+sdr.getNetprize());
			            innersales.setSerialno(String.valueOf(j+1)); 
			            innersales.setProductName(sdr.getName());
			            innersales.setUnit(sdr.getPrice());
			            innersales.setQuantity(sdr.getDescription());
			            innersales.setNetAmount(sdr.getNetprize());
			            innersales.setQuoteDetailsID(sdr.getSales_quote_details_ID()); 
			            System.out.println("----- child table before set------>"+sdr.getSales_quote_details_ID());
			            System.out.println("----- child table after set------>"+innersales.getQuoteDetailsID());
			            clist.add(innersales);
					   j++;
					   }
					maps.put(sales, clist); 
				}
				
				return maps;
			}catch(Exception e){
				logger.error("inside exception ",e);
			}
			return maps;
		}
		
		@Override
		public String getquotationcode() {
			logger.info("[getquotationcode()]------------------- inside getquotationcode method() ---------------");
			Query q=null;
			List<Sequance_number>getSalesquotelist=null;
			String ID="";
			long count=0;
			try{
				String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
				q=entitymanager.createQuery("from Sequance_number order by sales_quote_count desc"); 
				getSalesquotelist=(List<Sequance_number>)q.getResultList(); 
				System.out.println("--- list for getting quote code size---->"+getSalesquotelist.size()); 
				if(getSalesquotelist.size() > 0){
					System.out.println("--- list for getting quote code size if condition---->"+getSalesquotelist.size());
					for(Sequance_number getlist : getSalesquotelist){
						count = getlist.getSales_quote_count();						
						System.out.println("--- list for getting quote code size for loop---->"+getlist);
					}
				}	
				ID="SRFQ"+ count;
			/*	entitymanager.flush();
				entitymanager.clear();*/
				q=null;
				count = count + 1;
				q=entitymanager.createQuery("update Sequance_number set sales_quote_count=?"); 
				q.setParameter(1, count);
				q.executeUpdate();
			}catch(Exception e){
				logger.error("inside exception ",e);
			}
			return ID;
		}		
		
		@Override
		public List<SalesQuoteDetails> getsalesquotationdetails(int quoteID) {
			logger.info("[getsalesquotationdetails()]------------------- inside getsalesquotationdetails method() ---------------");
			Query q=null;
			List<SalesQuoteDetails> quotdetailList=null;
			try{
				q=entitymanager.createQuery("from SalesQuoteDetails where sales_quote_id=?");
				q.setParameter(1, quoteID); 
				quotdetailList=(List<SalesQuoteDetails>)q.getResultList();
				logger.debug("quotation detail list size--->"+quotdetailList.size());
			}catch(Exception e){
				logger.error("inside exception ",e);
			}finally{
				q=null;
			}
			return quotdetailList;
		}

		@Override
		@Transactional(value="transactionManager")
		public String quoteTabledelete(int quoteID) {
			logger.info("[quoteTabledelete()]------------------- inside quoteTabledelete method() ---------------");
			String status="Fail";
			try{
				SalesQuote salesQuote=entitymanager.find(SalesQuote.class, quoteID);
				salesQuote.setStatus("DEACTIVE"); 
				entitymanager.merge(salesQuote); 
				entitymanager.flush();
				entitymanager.clear();
				status="Success";
			}catch(Exception e){
				logger.error("inside exception ",e);
			}
			return status;
		}

		// Sales quote
		@Override
		@Transactional(value="transactionManager")
		public String consoleUpdate(String clientID,ArrayList<SalesOrderFormMB> quoteListDetails,int quoteID) {
		   logger.info("[consoleUpdate()]------------------- inside consoleUpdate method() ---------------");
		   String status="fail";
		   try{
		     for(int i=0 ; i < quoteListDetails.size() ; i++){
		      SalesQuoteDetails salesQuoteDetails;
		      if(quoteListDetails.get(i).getQuoteDetailsID()!=0) 
		      {
		      logger.info("---- Edited only Existing Row in Sales quote Edit -----");
		      salesQuoteDetails=entitymanager.find(SalesQuoteDetails.class, quoteListDetails.get(i).getQuoteDetailsID());
		      salesQuoteDetails.setName(quoteListDetails.get(i).getProductName());
		      salesQuoteDetails.setDescription(quoteListDetails.get(i).getQuantity()); 
		      salesQuoteDetails.setPrice(quoteListDetails.get(i).getUnit()); 
		      salesQuoteDetails.setNetprize(quoteListDetails.get(i).getNetAmount());
		      entitymanager.merge(salesQuoteDetails);
		      }
		      if(quoteListDetails.get(i).getQuoteDetailsID() == 0){
		    	  logger.info("---- New row added in Sales quote Edit -----");
		       salesQuoteDetails = new  SalesQuoteDetails();
		       salesQuoteDetails.setName(quoteListDetails.get(i).getProductName());
		       salesQuoteDetails.setDescription(quoteListDetails.get(i).getQuantity()); 
		       salesQuoteDetails.setPrice(quoteListDetails.get(i).getUnit()); 
		       salesQuoteDetails.setNetprize(quoteListDetails.get(i).getNetAmount());
		       salesQuoteDetails.setSalesquote(entitymanager.find(SalesQuote.class, quoteID));
		       logger.info("---- name for insert new row ----->"+salesQuoteDetails.getName());
		       logger.info("---- quantity for insert new row ----->"+salesQuoteDetails.getDescription());
		       logger.info("---- unit price for insert new row ----->"+salesQuoteDetails.getPrice());
		       logger.info("---- total amount for insert new row ----->"+salesQuoteDetails.getNetprize());
		       entitymanager.persist(salesQuoteDetails);
		      }
		  //    status="Success";
		    }
		      status="success";

		   }catch(Exception e){
		    logger.error("inside exception "+e.getMessage());
		    status="fail";
		   }
		   return status;
		  }	
		
		// Sales quote
		@Override
		public int getsalesQuoteapprovalCount(String clientID) {
			logger.info("----------Inside getsalesQuoteapprovalCount method calling----------");
			Query q=null;List<String> numberlist=null;
			try{
				numberlist=new ArrayList<String>();
				System.out.println("Client ID --"+clientID);					
				q=entitymanager.createQuery("from SalesQuote where  status='ACTIVE' and approvalStatus='PENDING FOR APPROVAL' and client_ID=? ");
				//q=entitymanager.createQuery("from SalesQuote where approvalStatus='PENDING FOR APPROVAL' and status='ACTIVE' ");
				q.setParameter(1, clientID);
				List<SalesQuote> list=(List<SalesQuote>)q.getResultList();
				System.out.println("---- salesquote list ---->"+list.size());
				for (int i = 0; i < list.size(); i++) {
					numberlist.add(list.get(i).getSales_quote_number());
				}
				HashSet<String> hashList=new HashSet<String>(numberlist);
				numberlist.clear();
				numberlist.addAll(hashList);
				System.out.println("---- list for sales quote---->"+numberlist.size()); 
			}catch(Exception e){
				logger.warn("inside exception"+e.getMessage());
			}finally{
				q=null;
			}
			return numberlist.size();
		}
		
		// Sales quote
		@Override
		public List<SalesQuote> getQuoteviewdetails(String clientID,int quoteID, Sales sales)
		{
			System.out.println("----- inside getQuoteviewdetails method() dao --------");
			Query q=null;
			List<SalesQuote> quotationList=null;
			try{
				q=entitymanager.createQuery("from SalesQuote where client_ID=? and sales_quote_id=? and status='ACTIVE'");
				q.setParameter(1, clientID);
				q.setParameter(2, quoteID);
				quotationList=(List<SalesQuote>)q.getResultList();
				System.out.println("----- inside getQuoteviewdetails method() dao quotationList size --------"+quotationList.size());
			}catch(Exception e){
				logger.warn("inside exception"+e.getMessage());
			}finally{
				q=null;
			}
			return quotationList;
		}

		@Override
		public List<SalesQuote> getquoteApprovallist(String clientID, String userID,ArrayList<SalesOrderFormMB> quoteTablelist, int i) {
			logger.info("----------------------inside getquoteApprovallist dao------------------");
			Query q=null;
			List<SalesQuote> list=null;
			try{
				q=entitymanager.createQuery("from SalesQuote where sales_quote_number=? and client_ID=?");
				q.setParameter(1, quoteTablelist.get(i).getQuotationcode());
				q.setParameter(2, clientID);
				list=(List<SalesQuote>)q.getResultList();
			}catch(Exception e){
				logger.warn("inside exception"+e.getMessage());
			}finally{
				q=null;
			}
			return list;
		}
		//Sales quote
		@Override
		@Transactional(value="transactionManager")
		public String quoteApproval(int sales_quote_ID) {
			logger.info("----------------------inside quotation approval dao------------------");
			Query q=null;
			String status="Fail";
			try{	
				SalesQuote quot=entitymanager.find(SalesQuote.class, sales_quote_ID);
				quot.setApprovalStatus("Approved");
				entitymanager.merge(quot);
				entitymanager.flush();
				entitymanager.clear();
				status="Success";
			}catch(Exception e){
				logger.warn("inside exception"+e.getMessage());
			}finally{
				q=null;
			}
			return status;
		}
		
		@Transactional(value="transactionManager")
		@Override
		public String codeSave(ATransaction aTransaction) {
			List<Code> codelist=null;
			String status="";
			Query q=null;
			try{
				q=entitymanager.createQuery("from Code where code=? and client_ID=? and status=?");
				q.setParameter(1, aTransaction.getCode());
				q.setParameter(2, aTransaction.getClientID());
				q.setParameter(3, "Active");
				codelist=(ArrayList<Code>)q.getResultList();
				if(codelist.size()==0){
					Code code=new Code(aTransaction);
					entitymanager.persist(code);
					status="Success";
				}else{
					status="Exist";
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return status;
		}

		@Override
		public List<String> getCodelist(String clientID) {
			List<String> codeList=null;
			Query q=null;
			q=entitymanager.createQuery("select code from Code where client_ID=? and status=?");
			q.setParameter(1, clientID);
			q.setParameter(2, "Active");
			codeList=(ArrayList<String>)q.getResultList();
			return codeList;
		}

		@Override
		public List<Code> codeDetails(ATransaction aTransaction) {
			List<Code> codelist=null;
			Query q=null;
			try{
				q=entitymanager.createQuery("from Code where client_ID=? and status=?");
				q.setParameter(1, aTransaction.getClientID());
				q.setParameter(2, "Active");
				codelist=(ArrayList<Code>)q.getResultList();
			}catch(Exception e){
				e.printStackTrace();
			}
			return codelist;
		}

		@Override
		public void getDescription(ATransaction aTransaction) {
			Query q=null;
			try{
				q=entitymanager.createQuery("select description from Code where code=? and client_ID=? and status=?");
				q.setParameter(1, aTransaction.getCode());
				q.setParameter(2, aTransaction.getClientID());
				q.setParameter(3, "Active");
				List<String> codelist=(ArrayList<String>)q.getResultList();
				if(codelist.size()>0){
					aTransaction.setCodeDescription(codelist.get(0));
					aTransaction.setDescription(codelist.get(0));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		@Transactional(value="transactionManager")
		@Override
		public String paymentSave(Buyer buyer) {
			String status="";
			try{
				MemberPayment payment=new MemberPayment();
				payment.setBuyerId(entitymanager.find(I0032.class, buyer.getBuyerID()));
				payment.setAmount(buyer.getCash());
				payment.setClientID(buyer.getClientID());
				payment.setDescription(buyer.getNote());
				payment.setCreaateDate(date);
				payment.setStatus(activeStatus);
				payment.setMemberId(buyer.getCusCode());
				entitymanager.persist(payment);
				status="Success";
			}catch(Exception e){
				e.printStackTrace();
			}
			return status;
		}

		@Override
		public List<MemberPayment> getmemberPayment(Buyer buyer) {
			Query q=null;List<MemberPayment> paymentList=null;
			try{
				q=entitymanager.createQuery("from MemberPayment where memberId=? and status=?");
				q.setParameter(1, buyer.getCusCode());
				q.setParameter(2, activeStatus);
				paymentList=(ArrayList<MemberPayment>)q.getResultList();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				q=null;
			}
			return paymentList;
		}

		@Transactional(value="transactionManager")
		@Override
		public String mamberPaymentUpdate(Buyer buyer) {
			String status="";
			try{
				MemberPayment payment=entitymanager.find(MemberPayment.class, buyer.getPaymentID());
				payment.setAmount(buyer.getCash());
				payment.setDescription(buyer.getNote());
				payment.setCreaateDate(date);
				entitymanager.merge(payment);
				status="Success";
			}catch(Exception e){
			}
			return status;
		}

		@Transactional(value="transactionManager")
		@Override
		public String mamberPaymentDelete(Buyer buyer) {
			String status="";
			try{
				MemberPayment payment=entitymanager.find(MemberPayment.class, buyer.getPaymentID());
				payment.setStatus("Deactive");
				entitymanager.merge(payment);
				status="Success";
			}catch(Exception e){
			}
			return status;
		}
		
	//stanley code for revenue
		
		@Transactional(value="transactionManager")
		@Override
		public String revenueInsertion(Sales sales){
			System.out.println("------------inside of revenue dao-----------");
			String status="Fail";Revenue revenue=null;
			try{
				String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
				revenue=new Revenue(sales,activeStatus,clientID);
				entitymanager.persist(revenue); 
				status="success";
				System.out.println("------------inside of revenue dao status-----------"+status);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(revenue != null){
					revenue=null;
				}
			}
			return status;
		}

		@Override
		public List<SalesOrderFormMB> getValuesRevenue(Sales sales){
			Query q=null;ArrayList<Revenue>getlist=null;SalesOrderFormMB mbobject=null;List<SalesOrderFormMB>returnList=null;
			try{
				returnList=new ArrayList<SalesOrderFormMB>();
				q=entitymanager.createQuery("from Revenue where client_ID=? and status=?");
				q.setParameter(1, sales.getClientID());
				q.setParameter(2, activeStatus);
				getlist=(ArrayList<Revenue>)q.getResultList();
				if(getlist.size() > 0){
					for(Revenue s :getlist){
						mbobject=new SalesOrderFormMB();
						mbobject.setProgrammeName(s.getProgrammeName()); 
						mbobject.setSchedules(s.getSchedules()); 
						mbobject.setTotalAmount(s.getTotalPayable());
						mbobject.setId(s.getId()); 
						returnList.add(mbobject);
					}
				}else{
					System.out.println("-----------inside else in dao revenue----------");
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(q != null){
					q = null;
				}if(getlist != null){
					getlist = null;
				}if(mbobject != null){
					mbobject = null;
				}
			}
			return returnList;
		}

		@Override
		public List<Revenue> getViewRevenue(Sales sales) {
			Query q=null;ArrayList<Revenue>getlist=null;
			try{
				q=entitymanager.createQuery("from Revenue where id=? and client_ID=? and status=?");
				q.setParameter(1, sales.getId());
				q.setParameter(2, sales.getClientID());
				q.setParameter(3, activeStatus);
				getlist=(ArrayList<Revenue>)q.getResultList();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(q != null){
					q = null;
				}
			}
			return getlist;
		}

		@Transactional(value="transactionManager")
		@Override
		public String coformDelete(Sales sales) {
			String status="Fail";Revenue revenue=null;
			try{
				revenue=new Revenue();
				revenue=entitymanager.find(Revenue.class, sales.getId());
				revenue.setStatus("DeActive"); 
				entitymanager.merge(revenue); 
				status="success";
			}catch(Exception e){
				e.printStackTrace();
			}
			return status;
		}

		@Transactional(value="transactionManager")
		@Override
		public String revenueUpdate(Sales sales) {
			String status="Fail";Revenue revenue=null;
			try{
				revenue=entitymanager.find(Revenue.class, sales.getId());
				revenue.setDate(sales.getSalesorderDate()); 
				revenue.setProgrammeName(sales.getProgrammeName());
				revenue.setSchedules(sales.getSchedules()); 
				revenue.setBilledAmount(sales.getBilledAmount()); 
				revenue.setBilledAmountpercentage(sales.getBilledAmountPercentage()); 
				revenue.setTotalPayable(sales.getTotalAmount()); 
				entitymanager.merge(revenue);
				status="success";
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(revenue != null){
					revenue=null;
				}
			}
			return status;
		}
}
