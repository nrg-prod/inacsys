package com.inacsys.managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.LoginAccess;
import com.inacsys.exception.DemoException;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "loginMB")
public class homeMB {
	private static Logger logger = Logger.getLogger(BuyersViewMB.class);
	private String invusername;
	private String invpassword;

	public String getInvusername() {
		return invusername;
	}

	public void setInvusername(String invusername) {
		this.invusername = invusername;
	}

	public String getInvpassword() {
		return invpassword;
	}

	public void setInvpassword(String invpassword) {
		this.invpassword = invpassword;
	}

	public String userLogin() {
		logger.info("[userLogin()] --------------- Inside userLogin() method() ------------------------");
		DemoController controller = null;
		try {
			LoginAccess loginaccess = new LoginAccess();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.userLogin(loginaccess);
			return "success";
		}

		catch (DemoException ie) {
			logger.error("Inside Exception"+ie.getMessage());
			return "failure";

		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
			return "failure";
		} finally {

		}
	}

}
