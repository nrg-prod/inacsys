package com.inacsys.domain;

import java.util.List;

import com.inacsys.managedBean.LoginMB;
import com.inacsys.shared.UserProduct;

/**
 * This Java Class will communicate with Domain Object
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */
public class LoginAccess{
	private String loginStatus;
	private List<UserProduct> user_Product = null;
	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	String username;
	String userpassword;
	String role;
	String clientID;
	String user_ID;
	public int salesCount;
	public int purchaseCount;
	public int stockCount;
	public int productCount;
	public int customerCount;
	public int vendorCount;
	private String clientName;
	private String userType;
	private String baseCurrency;
	private String country;
	public String menuName;
	public int approvalCount;
	private List<String> userMenulist=null;
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getUserMenulist() {
		return userMenulist;
	}

	public void setUserMenulist(List<String> userMenulist) {
		this.userMenulist = userMenulist;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	public int getApprovalCount() {
		return approvalCount;
	}

	public void setApprovalCount(int approvalCount) {
		this.approvalCount = approvalCount;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	public int getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(int purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public int getStockCount() {
		return stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(int customerCount) {
		this.customerCount = customerCount;
	}

	public int getVendorCount() {
		return vendorCount;
	}

	public void setVendorCount(int vendorCount) {
		this.vendorCount = vendorCount;
	}

	public String getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<UserProduct> getUser_Product() {
		return user_Product;
	}

	public void setUser_Product(List<UserProduct> user_Product) {
		this.user_Product = user_Product;
	}

	public List<LoginAccess> searchList=null;
	public String modulename;
	public String modulevalue;
	public String moduleid;
	public String globalValue;
	public String approvalStatus;

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getModuleid() {
		return moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getGlobalValue() {
		return globalValue;
	}

	public void setGlobalValue(String globalValue) {
		this.globalValue = globalValue;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public String getModulevalue() {
		return modulevalue;
	}

	public void setModulevalue(String modulevalue) {
		this.modulevalue = modulevalue;
	}

	public List<LoginAccess> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<LoginAccess> searchList) {
		this.searchList = searchList;
	}
	
}
