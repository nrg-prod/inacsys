package com.inacsys.managedBean;

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
import com.inacsys.domain.VendorDelete;
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

@ManagedBean(name = "vendorDeleteFormMB")
public class VendorDeleteFormMB {

	private static Logger logger = Logger.getLogger(VendorDeleteFormMB.class);
	public String vendorPhoneNumber;
	public String flag = "none";
	public String validate;

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getVendorPhoneNumber() {
		return vendorPhoneNumber;
	}

	public void setVendorPhoneNumber(String vendorPhoneNumber) {
		this.vendorPhoneNumber = vendorPhoneNumber;
	}

	public List<I0025> getVen() {
		return ven;
	}

	public void setVen(List<I0025> ven) {
		this.ven = ven;
	}

	List<I0025> ven = new ArrayList<I0025>();

	public String deleteVendor() {
		ven = null;
		DemoController controller = null;
		try {
			validate = "";
			flag = "none";
			logger.info("[deleteVendor()]------------Inside Delete Vendor() method()----------------------");
			Vendor vendor = new Vendor();
			vendor.setVendorPhoneNumber(vendorPhoneNumber);
			
			LoginAccess loginaccess = new LoginAccess();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			ven = controller.vendorUpdate(vendor, ven);
			if (ven.size() == 0) {
				throw new DemoException("* Enter the correct Phone number ");
			}
			if (ven.size() > 0) {
				flag = "1";
			}
			logger.info("[DeleteVendor()]--------------------Outside Delete Vendor() method()-----------------");
			return "";
		} catch (DemoException ie) {
			setValidate(ie.getMessage());
			logger.debug("log:" + ie.getMessage());
			return "";

		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
			return "";
		} finally {

		}

	}

	public String delete() {
		DemoController controller = null;

		try {
			logger.info("[Delete()]----------Inside Delete() ----------------------");
			VendorDelete vendorDelete = new VendorDelete();
			vendorDelete.setVendorPhoneNumber(vendorPhoneNumber);
			
			LoginAccess loginaccess = new LoginAccess();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.vendorDelete(vendorDelete);
			logger.debug("[Delete()]-----------Outside Delete() method() ----------------------");
			return "deleteVendorsuccess";
		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
		}
		return "";
	}

}
