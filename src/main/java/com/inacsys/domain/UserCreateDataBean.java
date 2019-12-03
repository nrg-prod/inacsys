package com.inacsys.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.inacsys.shared.UserCreate;

public class UserCreateDataBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String createUserName;
	public String createUserPwd;
	public String createUserMail;
	public String createUserPhone;
	ArrayList<UserCreateDataBean> userinfoList = new ArrayList<UserCreateDataBean>();
	public String flag = "none";
	public String flag1 = "none";
	public String flag2 = "none";
	public String flag3 = "none";
	public String flag4 = "none";
	public String tick = "false";
	public int sno;
	public List<UserCreateDataBean> userlist = null;
	public String errorMsg;
	public String message;
	private String errorusername;
	private String errorpassword;
	private String erroremail;
	private String errorphone;
	private String erroruserType;
	private String check;
	private int users;
	private String clientNumber;
	private int clientLimit;
	private int userID;
	private String userType;
	private List<String> departmentList = null;
	private List<String> menuList = null;
	private List<String> usertypeList = null;
	private List<String> usertypeList1 = null;
	private String userdepartments;
	private String usermenus;
	private String[] selectedDepartments;
	private String[] selectedMenus;
	private String hiddenselectedDepts;
	private String erroruserDepartments;
	private String erroruserMenus;
	private int userLimit;
	private String clientID;
	
	
	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public List<String> getUsertypeList1() {
		return usertypeList1;
	}

	public void setUsertypeList1(List<String> usertypeList1) {
		this.usertypeList1 = usertypeList1;
	}

	public List<String> getUsertypeList() {
		return usertypeList;
	}

	public void setUsertypeList(List<String> usertypeList) {
		this.usertypeList = usertypeList;
	}

	public int getUserLimit() {
		return userLimit;
	}

	public void setUserLimit(int userLimit) {
		this.userLimit = userLimit;
	}

	public String getErroruserDepartments() {
		return erroruserDepartments;
	}

	public void setErroruserDepartments(String erroruserDepartments) {
		this.erroruserDepartments = erroruserDepartments;
	}

	public String getErroruserMenus() {
		return erroruserMenus;
	}

	public void setErroruserMenus(String erroruserMenus) {
		this.erroruserMenus = erroruserMenus;
	}

	public String getHiddenselectedDepts() {
		return hiddenselectedDepts;
	}

	public void setHiddenselectedDepts(String hiddenselectedDepts) {
		this.hiddenselectedDepts = hiddenselectedDepts;
	}

	public String[] getSelectedDepartments() {
		return selectedDepartments;
	}

	public void setSelectedDepartments(String[] selectedDepartments) {
		this.selectedDepartments = selectedDepartments;
	}

	public String[] getSelectedMenus() {
		return selectedMenus;
	}

	public void setSelectedMenus(String[] selectedMenus) {
		this.selectedMenus = selectedMenus;
	}

	public String getUserdepartments() {
		return userdepartments;
	}

	public void setUserdepartments(String userdepartments) {
		this.userdepartments = userdepartments;
	}

	public String getUsermenus() {
		return usermenus;
	}

	public void setUsermenus(String usermenus) {
		this.usermenus = usermenus;
	}

	public List<String> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<String> departmentList) {
		this.departmentList = departmentList;
	}

	public List<String> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<String> menuList) {
		this.menuList = menuList;
	}

	public String getErroruserType() {
		return erroruserType;
	}

	public void setErroruserType(String erroruserType) {
		this.erroruserType = erroruserType;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getClientLimit() {
		return clientLimit;
	}

	public void setClientLimit(int clientLimit) {
		this.clientLimit = clientLimit;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public int getUsers() {
		return users;
	}

	public void setUsers(int users) {
		this.users = users;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg
	 *            the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public List<UserCreateDataBean> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<UserCreateDataBean> userlist) {
		this.userlist = userlist;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getTick() {
		return tick;
	}

	public void setTick(String tick) {
		this.tick = tick;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	public String getFlag3() {
		return flag3;
	}

	public void setFlag3(String flag3) {
		this.flag3 = flag3;
	}

	public String getFlag4() {
		return flag4;
	}

	public void setFlag4(String flag4) {
		this.flag4 = flag4;
	}

	/**
	 * @return the userinfoList
	 */
	public ArrayList<UserCreateDataBean> getUserinfoList() {
		return userinfoList;
	}

	/**
	 * @param userinfoList
	 *            the userinfoList to set
	 */
	public void setUserinfoList(ArrayList<UserCreateDataBean> userinfoList) {
		this.userinfoList = userinfoList;
	}

	/**
	 * @return the createUserName
	 */
	public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * @param createUserName
	 *            the createUserName to set
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * @return the createUserPwd
	 */
	public String getCreateUserPwd() {
		return createUserPwd;
	}

	/**
	 * @param createUserPwd
	 *            the createUserPwd to set
	 */
	public void setCreateUserPwd(String createUserPwd) {
		this.createUserPwd = createUserPwd;
	}

	/**
	 * @return the createUserMail
	 */
	public String getCreateUserMail() {
		return createUserMail;
	}

	/**
	 * @param createUserMail
	 *            the createUserMail to set
	 */
	public void setCreateUserMail(String createUserMail) {
		this.createUserMail = createUserMail;
	}

	/**
	 * @return the createUserPhone
	 */
	public String getCreateUserPhone() {
		return createUserPhone;
	}

	/**
	 * @param createUserPhone
	 *            the createUserPhone to set
	 */
	public void setCreateUserPhone(String createUserPhone) {
		this.createUserPhone = createUserPhone;
	}

	/**
	 * @return the errorusername
	 */
	public String getErrorusername() {
		return errorusername;
	}

	/**
	 * @param errorusername
	 *            the errorusername to set
	 */
	public void setErrorusername(String errorusername) {
		this.errorusername = errorusername;
	}

	/**
	 * @return the errorpassword
	 */
	public String getErrorpassword() {
		return errorpassword;
	}

	/**
	 * @param errorpassword
	 *            the errorpassword to set
	 */
	public void setErrorpassword(String errorpassword) {
		this.errorpassword = errorpassword;
	}

	/**
	 * @return the erroremail
	 */
	public String getErroremail() {
		return erroremail;
	}

	/**
	 * @param erroremail
	 *            the erroremail to set
	 */
	public void setErroremail(String erroremail) {
		this.erroremail = erroremail;
	}

	/**
	 * @return the errorphone
	 */
	public String getErrorphone() {
		return errorphone;
	}

	/**
	 * @param errorphone
	 *            the errorphone to set
	 */
	public void setErrorphone(String errorphone) {
		this.errorphone = errorphone;
	}

}
