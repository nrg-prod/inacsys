package com.inacsys.managedBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.ClientDataBean;
import com.inacsys.util.CommonValidate;
import com.inacsys.util.CurrencyConverter;
import com.inacsys.util.MailSendJDBC;

@ManagedBean(name="clientMB")
public class ClientMB {
	private static Logger logger = Logger.getLogger(ClientMB.class);
	ClientDataBean clientDataBean=new ClientDataBean();
	private List<String> departmentList=null;
	private String[] selectedDepartments;
	private String[] selectedMenus;
	private List<String> menuList=null;
	DemoController controller = null;
	private String validate;
	
	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String[] getSelectedMenus() {
		return selectedMenus;
	}

	public void setSelectedMenus(String[] selectedMenus) {
		this.selectedMenus = selectedMenus;
	}

	public String[] getSelectedDepartments() {
		return selectedDepartments;
	}

	public void setSelectedDepartments(String[] selectedDepartments) {
		this.selectedDepartments = selectedDepartments;
	}

	public List<String> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<String> menuList) {
		this.menuList = menuList;
	}

	public List<String> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<String> departmentList) {
		this.departmentList = departmentList;
	}

	public ClientDataBean getClientDataBean() {
		return clientDataBean;
	}

	public void setClientDataBean(ClientDataBean clientDataBean) {
		this.clientDataBean = clientDataBean;
	}
	
	public String superadminpage() {
		logger.info("[superadminpage()] --------------- Inside superadminpage() method() ------------------------");
		try{
			clientDataBean.setAddress("");
			clientDataBean.setClientName("");clientDataBean.setBaseCurrency("");
			clientDataBean.setDepartment("");clientDataBean.setUserLimit("");
			clientDataBean.setMailID("");clientDataBean.setPhoneNumber("");clientDataBean.setClientCountry("");
			departmentList=new ArrayList<String>();
			menuList=new ArrayList<String>();
			selectedDepartments=null;selectedMenus=null;
			ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller=(DemoController)ctx.getBean("controller");
			departmentList=controller.getdepartmentname();
			menuList=controller.getmenus();
		}catch(Exception e){
			logger.error("Inside Exception"+e.getMessage());
		}
		return "clientRegsitration";
	}
	
	public String clientSave(){
		logger.info("[clientSave()] --------------- Inside clientSave() method() ------------------------");
		ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		controller=(DemoController)ctx.getBean("controller");
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			if(validate(true)){
				if(selectedDepartments.length>0){
					if(selectedDepartments[0].equals("add")){
						String department = Arrays.toString(Arrays.toString(selectedDepartments).split(", ",2)[1].split("]")[0].split(", "));  
						department = department.substring(1, department.length()-1).replaceAll(", ", ";");
						clientDataBean.setDepartment(department);
						logger.debug("[clientSave()] --------------- department ------------------------>"+department);
					}else{
						String department = Arrays.toString(selectedDepartments);  
						department = department.substring(1, department.length()-1).replaceAll(", ", ";");
						clientDataBean.setDepartment(department);
					}
				}
				String menu = Arrays.toString(selectedMenus);
				menu = menu.substring(1, menu.length()-1).replaceAll(", ", ";");
				logger.debug("[clientSave()] --------------- menus ------------------------>"+menu);
				clientDataBean.setMenus(menu);
				String status=controller.saveClient(clientDataBean);
				logger.debug("[clientSave()] --------------- status ------------------------>"+status);
				if(status.equals("Success")){
					MailSendJDBC.clientInsert(clientDataBean);
					RequestContext.getCurrentInstance().execute("PF('clientConfirm').show();");
				}else if(status.equals("Exist")){
					fieldName = com.inacsys.util.CommonValidate.findComponentInRoot("phnoerr").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Already Exist"));
				}
			}			
		}
		catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "";
	}
	
	private boolean validate(boolean flag) {
		logger.info("[validate()] --------------- inside validate() method() ------------------------>");
		boolean valid = true;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		System.out.println("client country"+clientDataBean.getClientCountry());
		if (StringUtils.isEmpty(clientDataBean.getClientCountry())) {
			System.out.println("inbside if"+clientDataBean.getClientCountry());
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("ccoun").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Choose Client Country"));
			}
			valid = false;

		}
		if (StringUtils.isEmpty(clientDataBean.getClientName())) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("nameerr").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter Client Name"));
			}
			valid = false;

		} else if (!StringUtils.isEmpty(clientDataBean.getClientName())) {
			if (!CommonValidate.validateName(clientDataBean.getClientName())) {
				if (flag) {
					fieldName = CommonValidate.findComponentInRoot("nameerr").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter valid Client Name"));
				}
				valid = false;
			}
		}
		if (StringUtils.isEmpty(clientDataBean.getPhoneNumber())) {
			if (flag) {

				fieldName = CommonValidate.findComponentInRoot("phnoerr").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter Phone Number"));
			}
			valid = false;
		} else if (!StringUtils.isEmpty(clientDataBean.getPhoneNumber())) {
			if (!CommonValidate.validateNumberOnly(clientDataBean.getPhoneNumber())) {
				if (flag) {

					fieldName = CommonValidate.findComponentInRoot("phnoerr").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("10 Digits Eg:8769543210"));
				}
				valid = false;
			}else if(clientDataBean.getPhoneNumber().length()<10){
				if (flag) {

					fieldName = CommonValidate.findComponentInRoot("phnoerr").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("10 Digits Eg:8769543210"));
				}
				valid = false;
			}
		}
		if (StringUtils.isEmpty(clientDataBean.getMailID())) {
			{
				if (flag) {
					fieldName = CommonValidate.findComponentInRoot("mailerr").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter Email ID"));
				}
				valid = false;
			}
		}
		else if (!StringUtils.isEmpty(clientDataBean.getMailID())) {
			if (!CommonValidate.validateEmail(clientDataBean.getMailID())) {
				if (flag) {
					fieldName = CommonValidate.findComponentInRoot("mailerr").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter valid Email ID"));
				}
				valid = false;
			}
		}
		if (StringUtils.isEmpty(clientDataBean.getUserLimit())) {
			if (flag) {

				fieldName = CommonValidate.findComponentInRoot("limiterr").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter No of Users"));
			}
			valid = false;
		} else if (!StringUtils.isEmpty(clientDataBean.getUserLimit())) {
			if(clientDataBean.getUserLimit().equals("0")){
				if (flag) {

					fieldName = CommonValidate.findComponentInRoot("limiterr").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("User Limit Should not be Zero"));
				}
				valid = false;
			}
			if (!CommonValidate.validateNumberOnly(clientDataBean.getUserLimit())) {
				if (flag) {

					fieldName = CommonValidate.findComponentInRoot("limiterr").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Must be Number"));
				}
				valid = false;
			}
		}
		if(clientDataBean.getBaseCurrency().equals("select") || clientDataBean.getBaseCurrency().equals("")){
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("basecurr").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select the Currency"));
			}
			valid = false;
		}
		if(selectedDepartments.length==0 ||selectedDepartments==null || selectedDepartments.equals("")){
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("errdepartment").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select the Departments"));
			}
			valid = false;
		}else if(selectedDepartments.length==1){
			if(selectedDepartments[0].equals("add")){
				if (flag) {
					fieldName = CommonValidate.findComponentInRoot("errdepartment").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Select the Departments"));
				}
				valid = false;
			}
		}
		if(selectedMenus.length==0 || selectedMenus==null || selectedMenus.equals("")){
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("errmenu").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select the Menus"));
			}
			valid = false;
		}
		return valid;
	}
	
	public String reset(){
		logger.info("[reset()] --------------- inside reset() method() ------------------------>");
		  clientDataBean.setClientName("");
		  clientDataBean.setAddress("");
		  clientDataBean.setPhoneNumber("");clientDataBean.setUserLimit("");
		  clientDataBean.setMailID("");clientDataBean.setBaseCurrency("");
		  selectedDepartments=new String[0]; selectedMenus=new String[0];
		  return "";
	}
	
	public String clientView(){
		logger.info("[clientView()] --------------- inside clientView() method() ------------------------>");
		RequestContext.getCurrentInstance().execute("PF('clientUpdate').hide();");
		ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		controller=(DemoController)ctx.getBean("controller");
		try{
			controller.getclientDetails(clientDataBean);
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "";
	}
	
	public String clientDetails(){
		logger.info("[clientDetails()] --------------- inside clientDetails() method() ------------------------>");
		RequestContext.getCurrentInstance().execute("PF('clientUpdate').hide();");
		ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		controller=(DemoController)ctx.getBean("controller");
		try{
			controller.getclientDetailsView(clientDataBean);
			departmentList=controller.getdepartmentname();
			menuList=controller.getmenus();
			String[] departmets=clientDataBean.getDepartment().split(";");
			setSelectedDepartments(departmets);
			String[] menus=clientDataBean.getMenus().split(";");
			setSelectedMenus(menus);
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "";
	}
	
	public String clientUpdate(){
		logger.info("[clientUpdate()] --------------- inside clientUpdate() method() ------------------------>");
		ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		controller=(DemoController)ctx.getBean("controller");
		try{
			if(validate(true)){
				String department = Arrays.toString(selectedDepartments);  
				department = department.substring(1, department.length()-1).replaceAll(", ", ";");
				clientDataBean.setDepartment(department);
				String menu = Arrays.toString(selectedMenus);
				menu = menu.substring(1, menu.length()-1).replaceAll(", ", ";");
				clientDataBean.setMenus(menu);
				controller.clientUpdate(clientDataBean);
				RequestContext.getCurrentInstance().execute("PF('clientDialogModify').hide();");
				RequestContext.getCurrentInstance().execute("PF('clientUpdate').show();");
			}
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "";
	}
	
	public String clientDelete(){
		logger.info("[clientDelete()] --------------- inside clientDelete() method() ------------------------>");
		ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		controller=(DemoController)ctx.getBean("controller");
		try{			
			controller.clientDelete(clientDataBean);
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "";
	}
	
	
	public String clientNoCheck(){
		logger.info("[clientNoCheck()] --------------- inside clientNoCheck() method() ------------------------>");
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext context1 = FacesContext.getCurrentInstance();
		ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());	
		controller = (DemoController) ctx.getBean("controller");
		try {

			Map<String, String> params = context1.getExternalContext().getRequestParameterMap();
			String phno = params.get("param1");
			String status = controller.clientNoCheck(phno);
			if (status.equalsIgnoreCase("Exist")) {
				context.execute("numberFail();");
			} else {
				context.execute("numberSuccess();");
			}
		} catch (Exception e) {
			logger.error("inside exception ",e);
		}
		return "";
	}
	public void departmentvalueChange(){
		logger.info("[departmentValueChange()] --------------- inside departmentValueChange() method() ------------------------>");
		try{
			clientDataBean.setDepartment("");
			setValidate("");
			RequestContext.getCurrentInstance().execute("PF('adddepartment').hide();");
			for (int i = 0; i < selectedDepartments.length; i++) {
				RequestContext.getCurrentInstance().execute("PF('adddepartment').hide();");
				if(selectedDepartments[i].equals("add")){
					clientDataBean.setDepartment("");
					setValidate("");
					RequestContext.getCurrentInstance().execute("PF('adddepartment').show();");
				}
			}
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
	}
	public void saveDepartment(){
		logger.info("[saveDepartment()] --------------- inside saveDepartment() method() ------------------------>");
		try{
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());	
			controller = (DemoController) ctx.getBean("controller");
			if(clientDataBean.getDepartment().equalsIgnoreCase("")){
				throw new Exception("Please Enter Department");
			}else{
				String status=controller.insertdepartment(clientDataBean.getDepartment());
				if(status.equalsIgnoreCase("Exist")){
					throw new Exception("This Department Already Exist.");
				}else{
					clientDataBean.setDepartment("");
					setValidate("");
					departmentList=controller.getdepartmentname();
					RequestContext.getCurrentInstance().execute("PF('adddepartment').hide()");
				}
			}
		}catch(Exception e){
			setValidate(e.getMessage());
			logger.error("inside exception ",e);
		}
	}
	public String reset1(){
		logger.info("[reset1()] --------------- inside reset1() method() ------------------------>");
		clientDataBean.setDepartment("");
		setValidate("");
		return "";
	}
	
	public String convertMethod(){
		logger.info("[convertMethod()] --------------- inside convertMethod() method() ------------------------>");
		String output="";
		try{
			output=CurrencyConverter.currencyConvertion(clientDataBean.getBaseCurrency(),clientDataBean.getToCurrency());
			clientDataBean.setCurrencyValue(output);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
}
