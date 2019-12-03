package com.inacsys.managedBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.CategoryRegistration;
import com.inacsys.exception.DemoException;
import com.inacsys.util.CommonValidate;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "categoryRegistrationMB")
public class CategoryRegistrationMB {
	private static Logger logger = Logger
			.getLogger(CategoryRegistrationMB.class);
	public String category;
	public String industry;
	public String description;
	public String validate;

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	CategoryRegistration categoryreg = new CategoryRegistration();

	public String categoryregister() {
		logger.info("[categoryregister()] --------------- Inside categoryregister() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;String status="Fail";
		try {
			setValidate("");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if (category.equalsIgnoreCase("")) {
				logger.info("[categoryregister()] --------------- Category ------------------------");
				throw new Exception("Enter the Category Type");
			}
			else if (!CommonValidate.validateName(category)) {
				throw new DemoException(" Category Type should be in alphabets");
			} else {
				categoryreg.setCategory(category);
				categoryreg.setIndustry(industry);
				categoryreg.setDescription(description);
				status=controller.categoryType(categoryreg);
				if(status.equalsIgnoreCase("Success")){
					RequestContext.getCurrentInstance().execute("PF('categoryRegDialog').hide();");
					RequestContext.getCurrentInstance().execute("PF('categorySuccess').show();");
				}
			}
			return "";

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			logger.debug("inside categreg catch");
			setValidate(e.getMessage());
			return "";
		} finally {

		}
	}

	public String reset() {
		logger.info("[reset()] --------------- inside reset method() ------------------------");
		category = "";
		description = "";
		validate = "";
		return "";
	}

	public String categorytype() {

		return "";

	}

	public void value(ValueChangeEvent v) {
		logger.info("[value()] --------------- inside value method() ------------------------");
		try {
			ApplicationContext ctx = null;
			DemoController controller = null;
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			List<String> categorytype1 = null;
			List<String> categ = controller.categorylist1(categorytype1);
			int cnt = 0;
			for (int i = 0; i < categ.size(); i++) {
				logger.debug("[value()] --------------- inside value method() categ size ------------------------>"+categ.size());
				if (categ.get(i).equalsIgnoreCase(v.getNewValue().toString())) {
					cnt++;
				}
			}
			if (cnt <= 0) {
				throw new Exception(
						"This Category Type Available for Registration.");

			} else {
				logger.debug("inside else category");
				setCategory(v.getNewValue().toString());
				throw new Exception("This Category Type Already Registered.");
			}

		} catch (Exception e) {
			logger.debug("inside valuechange catch");
			setValidate(e.getMessage());
			e.getMessage();
		} finally {

		}

	}

	// prema begin 02/05/2016 dialog box creation for category Registration

	public void getcategoryRegistrationForm() {
		reset();
	}

	// prema end 02/05/2016
	public void categoryformclose() {
		RequestContext.getCurrentInstance().closeDialog(
				"categoryRegistrationForm");
	}
}
