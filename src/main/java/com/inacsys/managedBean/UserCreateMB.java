package com.inacsys.managedBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.util.UserDataAttribute;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.UserCreateDataBean;
import com.inacsys.util.CommonValidate;
import com.inacsys.util.MailSendJDBC;

@ManagedBean(name = "userCreateMB")
public class UserCreateMB {
	private static Logger logger = Logger.getLogger(UserCreateMB.class);
	UserCreateDataBean userCreateDataBean = new UserCreateDataBean();

	List<UserCreateDataBean> userinfoList1 = null;
	List<UserCreateDataBean> filterList;
	public String username;
	public String status;
	private String testChecking;
	private String testerrChecking;
	private String[] selectedDepartments;
	private String[] selectedMenus;

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
	
	public String getTestChecking() {
		return testChecking;
	}

	public void setTestChecking(String testChecking) {
		this.testChecking = testChecking;
	}

	public String getTesterrChecking() {
		return testerrChecking;
	}

	public void setTesterrChecking(String testerrChecking) {
		this.testerrChecking = testerrChecking;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the filterList
	 */
	public List<UserCreateDataBean> getFilterList() {
		return filterList;
	}

	/**
	 * @param filterList
	 *            the filterList to set
	 */
	public void setFilterList(List<UserCreateDataBean> filterList) {
		this.filterList = filterList;
	}

	/**
	 * @return the userinfoList1
	 */
	public List<UserCreateDataBean> getUserinfoList1() {
		return userinfoList1;
	}

	/**
	 * @param userinfoList1
	 *            the userinfoList1 to set
	 */
	public void setUserinfoList1(List<UserCreateDataBean> userinfoList1) {
		this.userinfoList1 = userinfoList1;
	}

	/**
	 * @return the userCreateDataBean
	 */
	public UserCreateDataBean getUserCreateDataBean() {
		return userCreateDataBean;
	}

	/**
	 * @param userCreateDataBean
	 *            the userCreateDataBean to set
	 */
	public void setUserCreateDataBean(UserCreateDataBean userCreateDataBean) {
		this.userCreateDataBean = userCreateDataBean;
	}

	private boolean validate(boolean flag) {
		logger.info("[validate()]--------------Inside validate() method()----------------------- ");
		boolean valid = true;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		if (userCreateDataBean.getCreateUserName().equalsIgnoreCase("")) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("usercname")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Enter the UserName."));
			}
			valid = false;
		} else if (!userCreateDataBean.getCreateUserName().equalsIgnoreCase("")) {
			if (!CommonValidate.validateName(userCreateDataBean
					.getCreateUserName())) {
				if (flag) {
					fieldName = CommonValidate.findComponentInRoot("usercname")
							.getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage(
							"Please Enter the valid UserName."));
				}
				valid = false;
			}
		}
		if (userCreateDataBean.getCreateUserPwd().equalsIgnoreCase("")) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("usercpwd")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Enter the Password."));
			}
			valid = false;
		}
		if (userCreateDataBean.getCreateUserMail().equalsIgnoreCase("")) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("usercmail")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Enter the Mail-Id."));
			}
			valid = false;
		} else if (!userCreateDataBean.getCreateUserMail().equalsIgnoreCase("")) {
			if (!CommonValidate.validateEmail(userCreateDataBean
					.getCreateUserMail())) {
				if (flag) {
					fieldName = CommonValidate.findComponentInRoot("usercmail")
							.getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage(
							"Please Enter the valid Email-Id."));
				}
				valid = false;
			}
		}
		if (userCreateDataBean.getCreateUserPhone().equalsIgnoreCase("")) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("usercphone")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Enter the Phone Number."));
			}
			valid = false;
		} else if (!userCreateDataBean.getCreateUserPhone()
				.equalsIgnoreCase("")) {
			if (!CommonValidate.validatePhone(userCreateDataBean
					.getCreateUserPhone())) {
				if (flag) {
					fieldName = CommonValidate
							.findComponentInRoot("usercphone").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage(
							"Please Enter the valid Phone Number."));
				}
				valid = false;
			}
		}
		return valid;
	}

	public String userCreate() {
		logger.info("[userCreate()]--------------Inside userCreate() method()----------------------- ");
		String page = "user_create_sucess";
		DemoController controller = null;
		String status = "";
		try {
			if (validate(true)) {
				logger.info("[userCreate()]--------------Inside userCreate() if condition----------------------- ");
				ApplicationContext ctx = FacesContextUtils
						.getWebApplicationContext(FacesContext
								.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				status = controller.insertCreateUser(userCreateDataBean);
				if (status.equalsIgnoreCase("success")) {
					userCreateDataBean.setCreateUserName("");
					userCreateDataBean.setCreateUserPwd("");
					userCreateDataBean.setCreateUserMail("");
					userCreateDataBean.setCreateUserPhone("");
				}
				return page;
			}
			else {
				return "";
			}
		} catch (Exception e) {
			page = "";
			logger.info("Inside userCreate method exception calling");
			logger.error("Inside Exception", e);
			return page;

		} finally {

		}
	}

	public String userClear() {
		logger.info("[userClear()]--------------------userClear() method()---------------------------");
		userCreateDataBean.setCreateUserName("");
		userCreateDataBean.setCreateUserPwd("");
		userCreateDataBean.setCreateUserMail("");
		userCreateDataBean.setCreateUserPhone("");
		return "";
	}

	public String userEdit() {
		logger.info("[userEdit()]--------------------userEdit() method()---------------------------");
		DemoController controller = null;
		userinfoList1 = null;
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			userinfoList1 = controller.insideUserEdit(userCreateDataBean);
			logger.debug("Inside userCreateMB userInfoList size"+ userinfoList1.size());
		} catch (Exception e) {
			logger.debug("Inside userEdit method exception calling");
			logger.error("Inside Exception", e);
		}
		return "";
	}

	public void update(RowEditEvent event) {
		logger.info("[Updae()]-----------------Inside Update() method()-----------------------");
		String status = "Fail";
		String uname = "";
		String upwd = "";
		String umail = "";
		String uphone = "";
		DemoController controller = null;
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			userCreateDataBean.setCreateUserName("");
			userCreateDataBean.setCreateUserPwd("");
			userCreateDataBean.setCreateUserMail("");
			userCreateDataBean.setCreateUserPhone("");
			uname = ((UserCreateDataBean) event.getObject())
					.getCreateUserName().toString();
			upwd = ((UserCreateDataBean) event.getObject()).getCreateUserPwd()
					.toString();
			umail = ((UserCreateDataBean) event.getObject())
					.getCreateUserMail().toString();
			uphone = ((UserCreateDataBean) event.getObject())
					.getCreateUserPhone().toString();
			userCreateDataBean.setCreateUserName(uname);
			userCreateDataBean.setCreateUserPwd(upwd);
			userCreateDataBean.setCreateUserMail(umail);
			userCreateDataBean.setCreateUserPhone(uphone);
			status = controller.insideUpdate(userCreateDataBean);
		} catch (Exception e) {
			logger.info("Inside update method exception calling");
			logger.error("Inside Exception", e);
		}

	}

	public boolean userflag = false;
	List<UserCreateDataBean> lists = null;
	public String validate;

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public List<UserCreateDataBean> getLists() {
		return lists;
	}

	public void setLists(List<UserCreateDataBean> lists) {
		this.lists = lists;
	}

	public boolean isUserflag() {
		return userflag;
	}

	public void setUserflag(boolean userflag) {
		this.userflag = userflag;
	}

	public String userCreateDialog() {
		logger.info("[userCreateDialog()]-------------------Inside userCreatedialog() method()--------------");
		validate = "";
		DemoController controller = null;
		try {

			lists = new ArrayList<UserCreateDataBean>();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.retirveUser(userCreateDataBean);
			logger.debug("size "+userCreateDataBean.getUsers()+" limit "+userCreateDataBean.getClientLimit());
			if (userCreateDataBean.getUsers() == userCreateDataBean.getClientLimit()) {
				status = "exist";
				throw new Exception("Already " +userCreateDataBean.getClientLimit()+" Users are Created");
			}else {
				UserCreateDataBean list = new UserCreateDataBean();
				list.setCreateUserMail("");
				list.setCreateUserName("");
				list.setCreateUserPhone("");
				list.setCreateUserPwd("");
				list.setFlag("1");
				list.setTick("false");
				list.setErroremail("");
				list.setErrorpassword("");
				list.setErrorphone("");
				list.setErrorusername("");
				list.setCheck("");
				list.setUserType("");
				list.setErroruserType("");
				list.setHiddenselectedDepts("");
				System.out.println("hidden input-------"+list.getHiddenselectedDepts());
				list.setDepartmentList(Arrays.asList(userCreateDataBean.getUserdepartments().split(";")));
				list.setMenuList(Arrays.asList(userCreateDataBean.getUsermenus().split(";")));
				list.setSno(1);
				lists.add(list);
				status = "register";
			}
			logger.debug("lists size -- " + lists.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
		}
		return "";
	}

	public String userRow() {
		logger.info("[userRow()]------------------Inside add user row() method()------------------- ");
		validate = "";
		DemoController controller = null;
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.retirveUser(userCreateDataBean);
			logger.debug("size "+lists.size()+" -- "+userCreateDataBean.getUsers()+" limit "+userCreateDataBean.getClientLimit());
			int count = lists.size() + userCreateDataBean.getUsers();
			if (count >= userCreateDataBean.getClientLimit()) {
				throw new Exception("The User Creation Limit is "+userCreateDataBean.getClientLimit());
			}else if (lists.size()==5) {
				throw new Exception("At a time to create only 5 Users ");
			}else {
				UserCreateDataBean list = new UserCreateDataBean();
				list.setCreateUserMail("");
				list.setCreateUserName("");
				list.setCreateUserPhone("");
				list.setCreateUserPwd("");
				list.setFlag("1");
				list.setTick("false");
				list.setErroremail("");
				list.setErrorpassword("");
				list.setErrorphone("");
				list.setErrorusername("");
				list.setUserType("");
				list.setErroruserType("");
				list.setCheck("");
				list.setSelectedDepartments(null);
				list.setSelectedMenus(null);
				list.setDepartmentList(Arrays.asList(userCreateDataBean.getUserdepartments().split(";")));
				list.setMenuList(Arrays.asList(userCreateDataBean.getUsermenus().split(";")));
				list.setSno(lists.size() + 1);
				lists.add(list);
			}
			logger.debug("lists size -- " + lists.size());
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
		}
		return "";
	}

	public String removeRow() {
		logger.info("[removeRow()]------------------Inside add removeRow() method()------------------- ");
		List<Integer> row = new ArrayList<Integer>();
		try {
			List<Integer> row2 = new ArrayList<Integer>();
			for (int i = 0; i < lists.size(); i++) {
				if (lists.get(i).getTick().equals("true")) {
					row.add(lists.get(i).getSno());
				}
			}
			if (row.size() == 5) {
				row2.add(row.get(4));
				row2.add(row.get(3));
				row2.add(row.get(2));
				row2.add(row.get(1));
				row2.add(row.get(0));
			} else if (row.size() == 4) {
				row2.add(row.get(3));
				row2.add(row.get(2));
				row2.add(row.get(1));
				row2.add(row.get(0));
			} else if (row.size() == 3) {
				row2.add(row.get(2));
				row2.add(row.get(1));
				row2.add(row.get(0));
			} else if (row.size() == 2) {
				row2.add(row.get(1));
				row2.add(row.get(0));
			} else if (row.size() == 1) {
				row2.add(row.get(0));
			}
			for (int i = 0; i < row2.size(); i++) {
				int c = row2.get(i);
				lists.remove(c - 1);
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";
	}
	private boolean validate1(boolean flag) {
		logger.info("[validate1()]------------------Inside validate1() method()------------------- ");
		boolean valid = true;
		String fieldName;int count=0;
		FacesContext fc = FacesContext.getCurrentInstance();
		if(userCreateDataBean.getUsertypeList().size()==0){
			if(userCreateDataBean.getUserLimit() == 1){
				if(!lists.get(0).getUserType().equals("Maker/Authorizer")){
					if (flag) {
						fieldName = CommonValidate.findComponentInRoot("limitcheck").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("You should be a Maker/Authorizer to save your changes"));
					}
					valid = false;
				}
			}else{
				if(userCreateDataBean.getUserLimit()==lists.size()){
					for (int i = 0; i < lists.size(); i++) {
						if(lists.get(i).getUserType().equals("Maker/Authorizer")){
							count++;
						}
					}
					if(count==0){
						if (flag) {
							fieldName = CommonValidate.findComponentInRoot("limitcheck").getClientId(fc);
							fc.addMessage(fieldName, new FacesMessage("You should choose atleast one Maker/Authorizer"));
						}
						valid = false;
					}
					if(count>0){
						valid = true;
					}
				}
			}
		}else{
				for (int i = 0; i < userCreateDataBean.getUsertypeList().size(); i++) {
					if(userCreateDataBean.getUsertypeList().get(i).equals("Maker/Authorizer")){
						count++;
					}
				}
				if(count==0){
					if (flag) {
						fieldName = CommonValidate.findComponentInRoot("limitcheck").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("You should choose atleast one Maker/Authorizer"));
					}
					valid = false;
				}
				if(count>0){
					valid = true;
				}else{
					for (int i = 0; i < lists.size(); i++) {
						if(lists.get(i).getUserType().equals("Maker/Authorizer")){
							count++;
						}
					}
					if(count==0){
						if (flag) {
							fieldName = CommonValidate.findComponentInRoot("limitcheck").getClientId(fc);
							fc.addMessage(fieldName, new FacesMessage("You should choose atleast one Maker/Authorizer"));
						}
						valid = false;
					}
					if(count>0){
						valid = true;
					}
				}
		}
		return valid;
	}
	public String userCreateForm() {
		logger.info("[userCreateForm()]------------------Insert user Create Form() method()------------------- ");
		DemoController controller = null;
		validate = "";
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			System.out.println("user limit"+userCreateDataBean.getUserLimit());
			controller.retirveUser(userCreateDataBean);
			if(validate1(true)){
				userCreateDataBean.setUserlist(lists);
				String statuss=controller.userInsert(userCreateDataBean);
				if(statuss.equals("Success")){
					fieldName = CommonValidate.findComponentInRoot("limitcheck").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage(""));
					List<String> emailList=new ArrayList<String>();
					List<String> userpawd=new ArrayList<String>();
					for (int i = 0; i < lists.size(); i++) {
						if (userCreateDataBean.getUserlist().get(i).getTick().equalsIgnoreCase("true")) {
							emailList.add(lists.get(i).getCreateUserMail());
							userpawd.add(lists.get(i).getCreateUserName()+"/"+lists.get(i).getCreateUserPwd());
						}
					}
					MailSendJDBC.sendMailUsers(emailList,userpawd);
				}else if(statuss.equalsIgnoreCase("usernameexist")){
					throw new Exception("This user name is already Exist");
				}
				status = "insert";
				validate = "";
			}
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			return "";
		}
	}

	public String refreh() {
		logger.info("[refresh()]-------------------Inside refresh() method()-----------------------------");
		RequestContext.getCurrentInstance().execute("PF('userCreateDialog').hide();");
		return "";
	}

	public String usernamecheck() {
		DemoController controller = null;
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext context1 = FacesContext.getCurrentInstance();
		setTestChecking("");
		setTesterrChecking("");
		validate = "";
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			Map<String, String> params = context1.getExternalContext()
					.getRequestParameterMap();
			logger.debug("------------------->" + params.get("param1"));
			String name = params.get("param1");
			userCreateDataBean.setCreateUserName(name);
			String name1 = params.get("param2");
			logger.debug("id -- " + name1);
			String error = params.get("param3");
			setTesterrChecking(error);
			logger.debug("id -- " + error);
			setTestChecking(name1);
			logger.debug("check -- " + testChecking + " -- " + testerrChecking);
			String status = controller.userCheck(userCreateDataBean);
			if (status.equalsIgnoreCase("fail")) {
				context.execute("userfail();");
			} else {
				context.execute("usersucess();");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";
	}

	public String createduserDetails(){
		logger.info("[createduserDetails()]--------------Inside createduserDetails() method()-------------------------- ");
		DemoController controller = null;
		try{
			ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller=(DemoController)ctx.getBean("controller");
			if(userCreateDataBean.getUserLimit()==0){
				RequestContext.getCurrentInstance().execute("PF('nousercreatedetailsDlg').show();");
			}
			if(userCreateDataBean.getUserLimit()>0){
				userCreateDataBean.setUserinfoList(new ArrayList<UserCreateDataBean>());
				controller.userView(userCreateDataBean);
				if(userCreateDataBean.getUserlist().size()>0){
					for (int i = 0; i < userCreateDataBean.getUserlist().size(); i++) {
						if(userCreateDataBean.getClientNumber().equals(userCreateDataBean.getUserlist().get(i).getClientNumber())){
							UserCreateDataBean user=new UserCreateDataBean();
							user.setCreateUserName(userCreateDataBean.getUserlist().get(i).getCreateUserName());
							user.setCreateUserPwd(userCreateDataBean.getUserlist().get(i).getCreateUserPwd());
							user.setCreateUserPhone(userCreateDataBean.getUserlist().get(i).getCreateUserPhone());
							user.setCreateUserMail(userCreateDataBean.getUserlist().get(i).getCreateUserMail());
							user.setUserType(userCreateDataBean.getUserlist().get(i).getUserType());
							userCreateDataBean.getUserinfoList().add(user);
						}
					}
				}
				RequestContext.getCurrentInstance().execute("PF('usercreatedetailsDlg').show();");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	public String userView(){
		logger.info("[userView()]--------------Inside user view() method()-------------------------- ");
		RequestContext.getCurrentInstance().execute("PF('userUpdate').hide();");
		DemoController controller = null;
		ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		controller=(DemoController)ctx.getBean("controller");
		try{
			controller.userView(userCreateDataBean);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("inside exception ",e);
		}
		return "";
	}
	
	public String userDetails(){
		logger.debug("[userDetails()]------------------Inside user details() method()-----------------");
		//RequestContext.getCurrentInstance().execute("PF('clientUpdate').hide();");
		DemoController controller = null;
		ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		controller=(DemoController)ctx.getBean("controller");
		try{
			controller.getUserdetails(userCreateDataBean);
			controller.retirveUser(userCreateDataBean);
			userCreateDataBean.setDepartmentList(Arrays.asList(userCreateDataBean.getUserdepartments().split(";")));
			userCreateDataBean.setMenuList(Arrays.asList(userCreateDataBean.getUsermenus().split(";")));
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "";
	}

	public String userUpdate(){
		  logger.debug("[userUpdate()]------------------Inside userUpdate() method()-----------------");
		  DemoController controller = null;
		  ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		  controller=(DemoController)ctx.getBean("controller");
		  try{
		   controller.retirveUser(userCreateDataBean);
		   if(validates(true)){
		    controller.userUpdate(userCreateDataBean);
		    MailSendJDBC.sendMailUsersUpdate(userCreateDataBean.getCreateUserName(),userCreateDataBean.getCreateUserPwd(),
		    userCreateDataBean.getCreateUserMail());
		    RequestContext.getCurrentInstance().execute("PF('userDialogModify').hide();");
		    RequestContext.getCurrentInstance().execute("PF('userUpdate').show();");
		   }
		  }catch(Exception e){
		   logger.error("inside exception ",e);
		  }
		  return "";
		 }
	
	private boolean validates(boolean flag) {
		boolean valid = true;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();

		if (StringUtils.isEmpty(userCreateDataBean.getCreateUserName())) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("nameerr").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter User Name"));
			}
			valid = false;

		} else if (!StringUtils.isEmpty(userCreateDataBean.getCreateUserName())) {
			if (!CommonValidate.validateName(userCreateDataBean.getCreateUserName())) {
				if (flag) {
					fieldName = CommonValidate.findComponentInRoot("nameerr").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter valid User Name"));
				}
				valid = false;
			}
		}
		if (StringUtils.isEmpty(userCreateDataBean.getCreateUserPhone())) {
			if (flag) {

				fieldName = CommonValidate.findComponentInRoot("phnoerr").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter Phone Number"));
			}
			valid = false;
		} else if (!StringUtils.isEmpty(userCreateDataBean.getCreateUserPhone())) {
			if (!CommonValidate.validateNumberOnly(userCreateDataBean.getCreateUserPhone())) {
				if (flag) {

					fieldName = CommonValidate.findComponentInRoot("phnoerr").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("10 Digits Eg:8769543210"));
				}
				valid = false;
			}else if(userCreateDataBean.getCreateUserPhone().length()<10){
				if (flag) {

					fieldName = CommonValidate.findComponentInRoot("phnoer").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("10 Digits Eg:8769543210"));
				}
				valid = false;
			}
		}
		if (StringUtils.isEmpty(userCreateDataBean.getCreateUserMail())) {
			{
				if (flag) {
					fieldName = CommonValidate.findComponentInRoot("mailerr").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter Email ID"));
				}
				valid = false;
			}
		}
		else if (!StringUtils.isEmpty(userCreateDataBean.getCreateUserMail())) {
			if (!CommonValidate.validateEmail(userCreateDataBean.getCreateUserMail())) {
				if (flag) {
					fieldName = CommonValidate.findComponentInRoot("mailerr").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter valid Email ID"));
				}
				valid = false;
			}
		}
		if (StringUtils.isEmpty(userCreateDataBean.getCreateUserPwd())) {
			if (flag) {

				fieldName = CommonValidate.findComponentInRoot("pwderr").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Enter Password"));
			}
			valid = false;
		} 
		if (userCreateDataBean.getUserType().equals("select")) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("utype").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select the User Type"));
			}
			valid = false;
		}else{
			if(userCreateDataBean.getClientLimit() == 1){
				if(!userCreateDataBean.getUserType().equals("Maker/Authorizer")){
					System.out.println("inside iff========");
					if (flag) {
						fieldName = CommonValidate.findComponentInRoot("utype").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("You should be a Maker/Authorizer to save your changes"));
					}
					valid = false;
				}
			}else{
				int count=0;
				for (int i = 0; i < userCreateDataBean.getUsertypeList1().size(); i++) {
					if(userCreateDataBean.getUsertypeList1().get(i).equals("Maker/Authorizer")){
						System.out.println("inside if-----");
						count++;
					}
				}
				if(count==0){
					if(!userCreateDataBean.getUserType().equals("Maker/Authorizer")){
						if (flag) {
							fieldName = CommonValidate.findComponentInRoot("utype").getClientId(fc);
							fc.addMessage(fieldName, new FacesMessage("Please choose Maker/Authorizer"));
						}
						valid = false;
					}
				}else{
					valid = true;
				}
			}
		}
		if(userCreateDataBean.getSelectedDepartments().length==0){
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("errdepartment").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select the Departments"));
			}
			valid = false;
		}
		if(userCreateDataBean.getSelectedMenus().length==0){
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("errmenu").getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage("Please Select the Menus"));
			}
			valid = false;
		}
		return valid;
	}
	
	public String userDelete(){
		logger.info("[userDelete()]----------------- Inside user delete() method()----------------------- ");
		DemoController controller = null;
		try{	
			ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller=(DemoController)ctx.getBean("controller");
			controller.userDelete(userCreateDataBean);
		}catch(Exception e){
			logger.error("inside exception ",e);
		}
		return "";
	}
	
	public void userTypeValueChange(ValueChangeEvent vc){
		logger.info("[userTypeValueChange()]----------------- Inside userTypeValueChange() method()----------------------- ");
		String str="";String serialNo="";DemoController controller = null;
		try{
			ApplicationContext ctx=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller=(DemoController)ctx.getBean("controller");
			serialNo = vc.getComponent().getAttributes().get("sno").toString();
			str=vc.getNewValue().toString();
			/*controller.retirveUser(userCreateDataBean);
			UserCreateDataBean list = new UserCreateDataBean();
			list.setCreateUserMail(lists.get(Integer.parseInt(serialNo) -1).getCreateUserMail());
			list.setCreateUserName(lists.get(Integer.parseInt(serialNo) -1).getCreateUserName());
			list.setCreateUserPhone(lists.get(Integer.parseInt(serialNo) -1).getCreateUserPhone());
			list.setCreateUserPwd(lists.get(Integer.parseInt(serialNo) -1).getCreateUserPwd());
			list.setFlag("1");
			list.setTick("false");
			list.setErroremail(lists.get(Integer.parseInt(serialNo) -1).getErroremail());
			list.setErrorpassword(lists.get(Integer.parseInt(serialNo) -1).getErrorpassword());
			list.setErrorphone(lists.get(Integer.parseInt(serialNo) -1).getErrorphone());
			list.setErrorusername(lists.get(Integer.parseInt(serialNo) -1).getErrorusername());
			list.setCheck(lists.get(Integer.parseInt(serialNo) -1).getCheck());
			list.setUserType(lists.get(Integer.parseInt(serialNo) -1).getUserType());
			list.setErroruserType(lists.get(Integer.parseInt(serialNo) -1).getErroruserType());
			list.setHiddenselectedDepts(lists.get(Integer.parseInt(serialNo) -1).getHiddenselectedDepts());
			list.setDepartmentList(lists.get(Integer.parseInt(serialNo) -1).getDepartmentList());
			list.setSno(Integer.parseInt(serialNo));
			if(str.equals("Maker")){
				list.setMenuList(lists.get(Integer.parseInt(serialNo) -1).getMenuList());
				if(!list.getMenuList().contains("APPROVAL")){
					list.setMenuList(list.getMenuList());
				}
			}else{
				list.setMenuList(lists.get(Integer.parseInt(serialNo) -1).getMenuList());
			}
			lists.set(Integer.parseInt(serialNo) - 1, list);*/
		}catch(Exception e){
			e.printStackTrace();
			logger.error("inside exception ",e);
		}
	}
}
