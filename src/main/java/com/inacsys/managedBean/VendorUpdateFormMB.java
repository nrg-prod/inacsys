package com.inacsys.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.Vendor;
import com.inacsys.domain.VendorUpdate;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0025;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "vendorUpdateFormMB")
public class VendorUpdateFormMB implements Serializable {

	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(VendorUpdateFormMB.class);
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
	public int vendor_Id = 0;
	public String validate;
	public String otherfirmtype;
	public String natureofbusiness;

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
	public String flag = "none";

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

	public VendorUpdate getVendorUpdate() {
		return vendorUpdate;
	}

	public void setVendorUpdate(VendorUpdate vendorUpdate) {
		this.vendorUpdate = vendorUpdate;
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

	DemoController controller = null;
	VendorUpdate vendorUpdate = new VendorUpdate();
	Vendor vendor = new Vendor();

	public String vendorUpdat() throws DemoException {
		logger.info("[vendorUpdat()]------------------- inside vendorUpdat method() ---------------");
		xx = null;
		try {
			flag = "none";
			vendor.setFirmName(firmName);
			vendor.setVendorPhoneNumber(vendorPhoneNumber);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			xx = controller.vendorUpdate(vendor, xx);
			if (xx.size() > 0) {
				flag = "1";
				int i = 0;
			}
			if (xx.size() > 0) {
				flag = "1";
				int i = 0;
				String s = xx.get(i).getI0028().getName();
			}
			if (xx.size() == 0) {
				throw new DemoException("* enter correct Phone number");
			}
			return "vendorUpdatsuccess1";
		} catch (DemoException i) {
			logger.error("Inside Exception", i);
			setValidate(i.getMessage());
			return "vendorUpdatfailure1";
		}

	}

	public String update() {
		logger.info("[update()]------------------- inside update method() ---------------");
		try {
			validate = "";
			flag = "none";
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
			otherfirmtype = xx.get(0).getOtherFirmType();
			natureofbusiness = xx.get(0).getNatureOfBusiness();
			vendor_Id = xx.get(0).getVendor_ID();
			vendorUpdate.setVendorPhoneNumber(vendorPhoneNumber);
			vendorUpdate.setFirmName(firmName);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			return "vendorUpdatsuccess2";
		} catch (Exception i) {
			logger.error("Inside Exception", i);
			setValidate(i.getMessage());
			return "vendorUpdatfailure2";
		}
	}

	public String updateModify() {
		logger.info("[updateModify()]------------------- inside updateModify method() ---------------");
		try {
			vendor.setVendor_Id(vendor_Id);
			vendor.setFirmName(firmName);
			vendor.setFirmRegistrationNumber(firmRegistrationNumber);
			vendor.setAddress(address);
			vendor.setVendorTelephoneNumber(vendorTelephoneNumber);
			vendor.setVendorPhoneNumber(vendorPhoneNumber);
			vendor.setCountry_ID(country_ID);
			vendor.setEmail_ID_vendor(email_ID_vendor);
			vendor.setFaxVendor(faxVendor);
			vendor.setPeresonIncharge(peresonIncharge);
			vendor.setFirmTypeStandard(firmTypeStandard);
			vendor.setOtherfirmtype(otherfirmtype);
			vendor.setNatureofbusiness(natureofbusiness);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.vendorModify(vendor, xx);
			return "vendorUpdatsuccess3";
		}

		catch (DemoException e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			logger.debug("log:" + e.getMessage());
			return "vendorUpdatfailure3";
		}
	}
}
