package com.inacsys.managedBean;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;







import javax.swing.event.ChangeEvent;





//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.context.ApplicationContext;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.dao.DemoAccountsDaoImpl;
import com.inacsys.domain.Buyer;
import com.inacsys.domain.EmployeeDetail;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.ProductRegister;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.domain.Sales;
import com.inacsys.domain.StockReport;
import com.inacsys.domain.StockView;
import com.inacsys.domain.Vendor;
import com.inacsys.domain.VendorCount;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.Employee;
import com.inacsys.shared.I0001;
import com.inacsys.shared.I0025;
import com.inacsys.shared.I0031;
import com.inacsys.shared.I0032;
import com.inacsys.shared.Indexes;
import com.inacsys.shared.SubProduct;
import com.inacsys.util.AccountsJDBC;
import com.inacsys.util.CommonValidate;
import com.inacsys.util.CountJDBC;
import com.inacsys.util.CustomJDBC;
import com.inacsys.util.StockReportJDBC;
import com.inacsys.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This Java Class will communicate with Inacsys
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 * ------------------------------------------------
 * Alex    | 26-Feb-207   | 26-Feb-207   | Login  |
 */

@ManagedBean(name = "loginMB")
public class LoginMB {
	
	final Logger logger = LoggerFactory.getLogger(LoginMB.class);

	@ManagedProperty(value = "#{buyersViewMB}")
	BuyersViewMB buyersViewMB;
	@ManagedProperty(value = "#{vendorViewFormMB}")
	VendorViewFormMB vendorViewFormMB;
	@ManagedProperty(value = "#{quickSaleViewMB}")
	QuickSaleViewMB quickSaleViewMB;
	@ManagedProperty(value = "#{salesViewMB}")
	SalesViewMB salesViewMB;
	@ManagedProperty(value = "#{chartView2}")
	ChartView2 chartView2;
	@ManagedProperty(value = "#{purchaseViewMB}")
	PurchaseViewMB purchaseViewMB;
	@ManagedProperty(value = "#{purchaseExpenseMB}")
	PurchaseExpenseMB purchaseExpenseMB;
	@ManagedProperty(value = "#{salesIncomeMB}")
	SalesIncomeMB salesIncomeMB;
	@ManagedProperty(value = "#{vendorRegisterFormMB}")
	VendorRegisterFormMB vendorRegisterFormMB;
	@ManagedProperty(value = "#{productRegisterFormMB}")
	ProductRegisterFormMB productRegisterFormMB;
	@ManagedProperty(value = "#{cashBookMB}")
	CashBookMB cashBookMB;
	@ManagedProperty(value = "#{employeePayrollFormMB}")
	EmployeePayrollFormMB employeePayrollFormMB;
	
	
	@ManagedProperty(value = "#{clientMB}")
	ClientMB clientMB;
	@ManagedProperty(value = "#{purchaseOrderFromMB}")
	PurchaseOrderFromMB purchaseOrderFromMB;
	
	@ManagedProperty(value = "#{userCreateMB}")
	UserCreateMB userCreateMB;
	@ManagedProperty(value = "#{reportMB}")
	ReportMB reportMB;
	
	
	@ManagedProperty(value = "#{crmMB}")
	CrmMB crmMB;
	
	@ManagedProperty(value = "#{salesOrderFormMB}")
	SalesOrderFormMB salesOrderFormMB;
	
	
	public SalesOrderFormMB getSalesOrderFormMB() {
		return salesOrderFormMB;
	}

	public void setSalesOrderFormMB(SalesOrderFormMB salesOrderFormMB) {
		this.salesOrderFormMB = salesOrderFormMB;
	}
	
	
	
	
	public CrmMB getCrmMB() {
		return crmMB;
	}

	public void setCrmMB(CrmMB crmMB) {
		this.crmMB = crmMB;
	}

	public ReportMB getReportMB() {
		return reportMB;
	}

	public void setReportMB(ReportMB reportMB) {
		this.reportMB = reportMB;
	}

	public UserCreateMB getUserCreateMB() {
		return userCreateMB;
	}

	public void setUserCreateMB(UserCreateMB userCreateMB) {
		this.userCreateMB = userCreateMB;
	}
	
	public PurchaseOrderFromMB getPurchaseOrderFromMB() {
		return purchaseOrderFromMB;
	}

	public void setPurchaseOrderFromMB(PurchaseOrderFromMB purchaseOrderFromMB) {
		this.purchaseOrderFromMB = purchaseOrderFromMB;
	}

	public ClientMB getClientMB() {
		return clientMB;
	}

	public void setClientMB(ClientMB clientMB) {
		this.clientMB = clientMB;
	}
	
	public EmployeePayrollFormMB getEmployeePayrollFormMB() {
		return employeePayrollFormMB;
	}

	public void setEmployeePayrollFormMB(EmployeePayrollFormMB employeePayrollFormMB) {
		this.employeePayrollFormMB = employeePayrollFormMB;
	}

	public CashBookMB getCashBookMB() {
		return cashBookMB;
	}

	public void setCashBookMB(CashBookMB cashBookMB) {
		this.cashBookMB = cashBookMB;
	}

	public ProductRegisterFormMB getProductRegisterFormMB() {
		return productRegisterFormMB;
	}

	public void setProductRegisterFormMB(ProductRegisterFormMB productRegisterFormMB) {
		this.productRegisterFormMB = productRegisterFormMB;
	}

	public VendorRegisterFormMB getVendorRegisterFormMB() {
		return vendorRegisterFormMB;
	}

	public void setVendorRegisterFormMB(
			VendorRegisterFormMB vendorRegisterFormMB) {
		this.vendorRegisterFormMB = vendorRegisterFormMB;
	}

	public SalesIncomeMB getSalesIncomeMB() {
		return salesIncomeMB;
	}

	public void setSalesIncomeMB(SalesIncomeMB salesIncomeMB) {
		this.salesIncomeMB = salesIncomeMB;
	}

	public PurchaseExpenseMB getPurchaseExpenseMB() {
		return purchaseExpenseMB;
	}

	public void setPurchaseExpenseMB(PurchaseExpenseMB purchaseExpenseMB) {
		this.purchaseExpenseMB = purchaseExpenseMB;
	}

	@ManagedProperty(value = "#{productViewMB}")
	ProductViewMB productViewMB;
	@ManagedProperty(value = "#{employeeDetailsViewFormMB}")
	EmployeeDetailsViewFormMB employeeDetailsViewFormMB;
	@ManagedProperty(value = "#{stockViewMB}")
	StockViewMB stockViewMB;
	@ManagedProperty(value = "#{profitLossMB}")
	ProfitLossMB profitLossMB;

	public ProfitLossMB getProfitLossMB() {
		return profitLossMB;
	}

	public void setProfitLossMB(ProfitLossMB profitLossMB) {
		this.profitLossMB = profitLossMB;
	}

	public EmployeeDetailsViewFormMB getEmployeeDetailsViewFormMB() {
		return employeeDetailsViewFormMB;
	}

	public void setEmployeeDetailsViewFormMB(
			EmployeeDetailsViewFormMB employeeDetailsViewFormMB) {
		this.employeeDetailsViewFormMB = employeeDetailsViewFormMB;
	}

	public StockViewMB getStockViewMB() {
		return stockViewMB;
	}

	public void setStockViewMB(StockViewMB stockViewMB) {

		this.stockViewMB = stockViewMB;
	}

	public SalesViewMB getSalesViewMB() {
		return salesViewMB;
	}

	public void setSalesViewMB(SalesViewMB salesViewMB) {
		this.salesViewMB = salesViewMB;
	}

	public PurchaseViewMB getPurchaseViewMB() {
		return purchaseViewMB;
	}

	public void setPurchaseViewMB(PurchaseViewMB purchaseViewMB) {
		this.purchaseViewMB = purchaseViewMB;
	}

	public QuickSaleViewMB getQuickSaleViewMB() {
		return quickSaleViewMB;
	}

	public void setQuickSaleViewMB(QuickSaleViewMB quickSaleViewMB) {
		this.quickSaleViewMB = quickSaleViewMB;
	}

	public ChartView2 getChartView2() {
		return chartView2;
	}

	public void setChartView2(ChartView2 chartView2) {
		this.chartView2 = chartView2;
	}

	public VendorViewFormMB getVendorViewFormMB() {
		return vendorViewFormMB;
	}

	public void setVendorViewFormMB(VendorViewFormMB vendorViewFormMB) {
		this.vendorViewFormMB = vendorViewFormMB;
	}

	public BuyersViewMB getBuyersViewMB() {
		return buyersViewMB;
	}

	public void setBuyersViewMB(BuyersViewMB buyersViewMB) {
		this.buyersViewMB = buyersViewMB;
	}

	private String invusername;
	private String invpassword;
	private String validate;
	private String day;
	private String cDate;
	private String cMonthYr;
	private String cTime;
	private int purchaseStatus;
	private int salespayStatus;
	private int purchaseorderStatus;
	private int salesorderStatus;
	private int stockStatus;
	private int nonsaleproductStatus;
	private String rollID;
	private String srFlag = "none";
	public String loginType1 = "none";
	public String loginType2 = "none";
	public String loginType3 = "none";
	public String loginType4 = "none";
	public String logo;
	private List<PurchaseOrder> filterList;
	private List<PurchaseOrder> orderList = null;
	LoginAccess loginaccess1 = new LoginAccess();
	PurchaseOrder purchaseorder = new PurchaseOrder();
	private List<StockView> stock = null;
	private List<Sales> salesReturns = null;
	private List<ProductRegister> nonsaleProduct = new ArrayList<ProductRegister>();
	private List<SubProduct> submenuList=null;
	public int salesCount;
	 public int purchaseCount;
	 public int stockCount;
	 public int productCount;
	 public int customerCount;
	 public int vendorCount;
	 private String clientName;
	 private String userName;
	 public String userType;
	 public String menuName;
	 public List<String> menuList=new ArrayList<String>();
	 
	 
	 
		
		public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

		public List<String> getMenuList() {
			return menuList;
		}

		public void setMenuList(List<String> menuList) {
			this.menuList = menuList;
		}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public List<SubProduct> getSubmenuList() {
		return submenuList;
	}

	public void setSubmenuList(List<SubProduct> submenuList) {
		this.submenuList = submenuList;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public PurchaseOrder getPurchaseorder() {
		return purchaseorder;
	}

	public void setPurchaseorder(PurchaseOrder purchaseorder) {
		this.purchaseorder = purchaseorder;
	}

	private MenuModel model;

	/**
	 * @return the loginaccess1
	 */
	public LoginAccess getLoginaccess1() {
		return loginaccess1;
	}

	/**
	 * @param loginaccess1
	 *            the loginaccess1 to set
	 */
	public void setLoginaccess1(LoginAccess loginaccess1) {
		this.loginaccess1 = loginaccess1;
	}

	public String getLoginType1() {
		return loginType1;
	}

	public void setLoginType1(String loginType1) {
		this.loginType1 = loginType1;
	}

	public String getLoginType2() {
		return loginType2;
	}

	public void setLoginType2(String loginType2) {
		this.loginType2 = loginType2;
	}

	public String getLoginType3() {
		return loginType3;
	}

	public void setLoginType3(String loginType3) {
		this.loginType3 = loginType3;
	}

	public String getLoginType4() {
		return loginType4;
	}

	public void setLoginType4(String loginType4) {
		this.loginType4 = loginType4;
	}

	public String rem1 = "none";
	public String rem2 = "none";
	public String rem3 = "none";
	public String rem4 = "none";
	public String rem5 = "none";

	public String getRem1() {
		return rem1;
	}

	public void setRem1(String rem1) {
		this.rem1 = rem1;
	}

	public String getRem2() {
		return rem2;
	}

	public void setRem2(String rem2) {
		this.rem2 = rem2;
	}

	public String getRem3() {
		return rem3;
	}

	public void setRem3(String rem3) {
		this.rem3 = rem3;
	}

	public String getRem4() {
		return rem4;
	}

	public void setRem4(String rem4) {
		this.rem4 = rem4;
	}

	public String getRem5() {
		return rem5;
	}

	public void setRem5(String rem5) {
		this.rem5 = rem5;
	}

	public String getSrFlag() {
		return srFlag;
	}

	public void setSrFlag(String srFlag) {
		this.srFlag = srFlag;
	}

	public List<Sales> getSalesReturns() {
		return salesReturns;
	}

	public void setSalesReturns(List<Sales> salesReturns) {
		this.salesReturns = salesReturns;
	}

	public int getNonsaleproductStatus() {
		return nonsaleproductStatus;
	}

	public void setNonsaleproductStatus(int nonsaleproductStatus) {
		this.nonsaleproductStatus = nonsaleproductStatus;
	}

	public List<ProductRegister> getNonsaleProduct() {
		return nonsaleProduct;
	}

	public void setNonsaleProduct(List<ProductRegister> nonsaleProduct) {
		this.nonsaleProduct = nonsaleProduct;
	}

	public List<StockView> getStock() {
		return stock;
	}

	public void setStock(List<StockView> stock) {
		this.stock = stock;
	}

	public int getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(int stockStatus) {
		this.stockStatus = stockStatus;
	}

	public int getSalespayStatus() {
		return salespayStatus;
	}

	public void setSalespayStatus(int salespayStatus) {
		this.salespayStatus = salespayStatus;
	}

	public int getPurchaseorderStatus() {
		return purchaseorderStatus;
	}

	public void setPurchaseorderStatus(int purchaseorderStatus) {
		this.purchaseorderStatus = purchaseorderStatus;
	}

	public int getSalesorderStatus() {
		return salesorderStatus;
	}

	public void setSalesorderStatus(int salesorderStatus) {
		this.salesorderStatus = salesorderStatus;
	}

	public int getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(int purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public String getcMonthYr() {
		return cMonthYr;
	}

	public void setcMonthYr(String cMonthYr) {
		this.cMonthYr = cMonthYr;
	}

	public String getcTime() {
		return cTime;
	}

	public void setcTime(String cTime) {
		this.cTime = cTime;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

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

	private String loginStatus;

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	private transient HttpSession session = null;
	DemoController controller = null;
	LoginAccess loginaccess = new LoginAccess();

	public LoginAccess getLoginaccess() {
		return loginaccess;
	}

	public void setLoginaccess(LoginAccess loginaccess) {
		this.loginaccess = loginaccess;
	}

	private int purchaseapprovalstatus;

	public int getPurchaseapprovalstatus() {
		return purchaseapprovalstatus;
	}

	public void setPurchaseapprovalstatus(int purchaseapprovalstatus) {
		this.purchaseapprovalstatus = purchaseapprovalstatus;
	}

	public ArrayList<String> productnonsale = null;

	public ArrayList<String> getProductnonsale() {
		return productnonsale;
	}

	public void setProductnonsale(ArrayList<String> productnonsale) {
		this.productnonsale = productnonsale;
	}

	public String homeLoad() {
		DemoController controller = null;
		try {
			logger.info("-------------------------------------- Inside homeLod()method ------------------------- ");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setDay(new SimpleDateFormat("EEEE").format(new Date()));
			setcMonthYr(new SimpleDateFormat("MMMM yyyy").format(new Date()));
			String d = new SimpleDateFormat("d").format(new Date());
			//int dd = 0; //Commented by Alex 13 May 2017
			if (Integer.parseInt(d) == 1 || Integer.parseInt(d) == 21
					|| Integer.parseInt(d) == 31) {
				setcDate(d + "st");
			} else if (Integer.parseInt(d) == 2 || Integer.parseInt(d) == 22) {
				setcDate(d + "nd");
			} else if (Integer.parseInt(d) == 3 || Integer.parseInt(d) == 23) {
				setcDate(d + "rd");
			} else {
				setcDate(d + "th");
			}
			setcTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
			setPurchaseStatus(controller.getPurchasestatus());
			setNonsaleproductStatus(controller.productInfo1());
			setStockStatus(controller.outofStock());
			setSalespayStatus(controller.getSalesPayStatus());
			logger.debug("----------------------------------- Sales pay status ------------------------------->" + salespayStatus);
			setSalesorderStatus(controller.salesreturncount());
		} catch (Exception e) {
			logger.error("Exception ----------------------->" +e.getMessage());

		} finally {
			controller=null;
		}
		return "";
	}

	public String logout() {
		logger.info("----------------------------- Inside logout() method ----------------------------- ");
		try {
			loginaccess.setUsername(""
					+ FacesContext.getCurrentInstance().getExternalContext()
							.getSessionMap().get("User"));
			loginaccess.setStatus("deactive");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.userLogin(loginaccess);
			HttpSession session = Util.getSession();
			session.invalidate();
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			Cookie[] cookies = request.getCookies();
			Cookie opentoken = null;
			for (Cookie c : cookies) {
				if (c.getName().equals("opentoken")) {
					logger.debug("found the cookie: " + c.getName()
							+ " domain:" + c.getDomain() + " exp:"
							+ c.getMaxAge()); // log4j debug statement
					opentoken = c;
					opentoken.setMaxAge(0);
					opentoken.setValue(""); // it is more elegant to clear the
											// value but not necessary
					response.addCookie(opentoken);
					logger.debug("redirecting to " + request.getContextPath());
					response.sendRedirect(request.getContextPath());
					break;
				}
			}
			return "logout";
		} catch (Exception e) {
			logger.error("Exception ----------------------->" +e.getMessage());
			return "failure";
		}
		finally {
			controller = null;
		}
	}

	public String redirectStockLimiView() {
		logger.info("----------------------------- Inside redirectStockLimiView() method ----------------------------- ");
		DemoController controller = null;
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setStock(controller.getStockInInfo());
			logger.debug("Stock Size -------------->" + stock.size());
		} catch (Exception e) {
			logger.error("Exception ----------------------->" +e.getMessage());
		} finally {
			controller = null;
		}
		return "stockLimitView";
	}

	public List<ProductRegister> outStock = null;

	public List<ProductRegister> getOutStock() {
		return outStock;
	}

	public void setOutStock(List<ProductRegister> outStock) {
		this.outStock = outStock;
	}

	ArrayList<LoginMB> log1 = new ArrayList<LoginMB>();

	public ArrayList<LoginMB> getLog1() {
		return log1;
	}

	public void setLog1(ArrayList<LoginMB> log1) {
		this.log1 = log1;
	}

	public String prod_name;
	public String prod_code;
	public String price;
	public String unit;

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getProd_code() {
		return prod_code;
	}

	public void setProd_code(String prod_code) {
		this.prod_code = prod_code;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String valid;

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String flag1;

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public String redirectNonSaleProductView() {
		logger.info("----------------------------- Inside redirectNonSaleProductView() method ----------------------------- ");
		DemoController controller = null;
		try {
			setValid("");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setProductnonsale(controller.productnonsales());
			logger.debug("Product non sales Size ---------------------------------------->" + productnonsale.size());
			if (productnonsale.size() <= 0) {
				flag1 = "none";
				throw new Exception("*No Records Found");
			} else {
				flag1 = "1";
			}
		} catch (Exception e) {
			setValid(e.getMessage());
			logger.error("Exception ----------------------->" +e.getMessage());
		} finally {
			controller = null;
		}
		return "NonSaleProductView";
	}

	public String flag = "none";

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String validate1 = "none";
	public String loginValidate = "none";

	public String getLoginValidate() {
		return loginValidate;
	}

	public void setLoginValidate(String loginValidate) {
		this.loginValidate = loginValidate;
	}

	public String getValidate1() {
		return validate1;
	}

	public void setValidate1(String validate1) {
		this.validate1 = validate1;
	}

	public String redirectoutofstockView() {
		logger.info("-------------------------------------- Inside redirectoutofstockView() method ------------------------------ ");
		DemoController controller = null;
		try {
			setValidate1("");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setOutStock(controller.outofStock1());
			logger.info("Stock out size ---------------------------------------->" + outStock.size());
			if (outStock.size() > 0) {
				flag = "1";
				return "outofstock";
			} else {
				flag = "none";
				throw new Exception("No Records Found");
			}
		} catch (Exception e) {
			flag = "none";
			setValidate1(e.getMessage());
			logger.error("Exception ----------------------->"+e.getMessage());
			return "";
		} finally {
			controller=null;
		}

	}

	public String redirectsalesReturnView() {
		logger.info("---------------------------------------- Inside  redirectNonSaleProductView() method ----------------------------");
		DemoController controller = null;
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setSalesReturns(controller.getSalesReturnView());
			if (salesReturns.size() > 0) {
				srFlag = "1";
				return "salesreturnviewRemainder";
			} else {
				logger.info("-------------------------- redirect sales return view() method Inside else condition ---------------------------");
				return "";
			}
		} catch (DemoException e) {
			return "";
		} catch (Exception e) {
			logger.error("Exception ----------------------->" +e.getMessage());
			return "";
		} finally {
			controller=null;
		}
	}

	//private StreamedContent chartImage; It is not used any where commanded by Alex
	private StreamedContent barcode;

	public StreamedContent getBarcode() {
		return barcode;
	}

	public void setBarcode(StreamedContent barcode) {
		this.barcode = barcode;
	}

	public void drawChart(OutputStream out, Object data) throws IOException {
		logger.info("----------------------------- Inside drawChart() method ----------------------------- ");
		String s = "c:/barimage/mouse.png";
		BufferedImage img;
		img = ImageIO.read(new File(s));
		ImageIO.write(img, "png", out);
	}

	public String read() {
		File barcodeFile = new File("c:/barimage/mouse.png");
		/*
		 * BarcodeImageHandler.saveJPEG(BarcodeFactory.createCode128("PRIMEFACES"
		 * ), barcodeFile);
		 */
		try {
			barcode = new DefaultStreamedContent(new FileInputStream(
					barcodeFile), "image/png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Inside Exception", e);
		}
		return "";
	}

	public String fontColor = "#91534d";
	public String fontSize = "16px";
	public String labelWidth = "200px";
	public String fontFamily = "Helvetica Neue LT Pro 55";
	public String fontWeight = "500";

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public String getLabelWidth() {
		return labelWidth;
	}

	public void setLabelWidth(String labelWidth) {
		this.labelWidth = labelWidth;
	}

	public String getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	public String getFontWeight() {
		return fontWeight;
	}

	public void setFontWeight(String fontWeight) {
		this.fontWeight = fontWeight;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String hfontStyle = "normal";
	public String hfontSize = "18px";
	public String hfontWeight = "bold";

	public String getHfontStyle() {
		return hfontStyle;
	}

	public void setHfontStyle(String hfontStyle) {
		this.hfontStyle = hfontStyle;
	}

	public String getHfontSize() {
		return hfontSize;
	}

	public void setHfontSize(String hfontSize) {
		this.hfontSize = hfontSize;
	}

	public String getHfontWeight() {
		return hfontWeight;
	}

	public void setHfontWeight(String hfontWeight) {
		this.hfontWeight = hfontWeight;
	}

	public String elabelColor = "#F51313";

	public String getElabelColor() {
		return elabelColor;
	}

	public void setElabelColor(String elabelColor) {
		this.elabelColor = elabelColor;
	}

	public String getRollID() {
		return rollID;
	}

	public void setRollID(String rollID) {
		this.rollID = rollID;
	}

	public String newuser;
	public String confirmuser;
	public String newpasswrd;
	public String confirmpasswrd;
	public String currentpasswrd;
	public String message;

	public String getNewuser() {
		return newuser;
	}

	public void setNewuser(String newuser) {
		this.newuser = newuser;
	}

	public String getConfirmuser() {
		return confirmuser;
	}

	public void setConfirmuser(String confirmuser) {
		this.confirmuser = confirmuser;
	}

	public String getNewpasswrd() {
		return newpasswrd;
	}

	public void setNewpasswrd(String newpasswrd) {
		this.newpasswrd = newpasswrd;
	}

	public String getConfirmpasswrd() {
		return confirmpasswrd;
	}

	public void setConfirmpasswrd(String confirmpasswrd) {
		this.confirmpasswrd = confirmpasswrd;
	}

	public String getCurrentpasswrd() {
		return currentpasswrd;
	}

	public void setCurrentpasswrd(String currentpasswrd) {
		this.currentpasswrd = currentpasswrd;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String userManagement() {
		logger.info("----------------------------- Inside userManagement() method ----------------------------- ");
		newuser = "";
		newpasswrd = "";
		currentpasswrd = "";
		confirmpasswrd = "";
		confirmuser = "";
		message = "";
		return "userManagement";
	}

	public String changeUserName() {
		logger.info("------------------------------- Inside changeUserName() method --------------------------");//Correct way 
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			if (newuser.equals("")) {
				throw new Exception("Enter New UserName");
			} else if (confirmuser.equals("")) {
				throw new Exception("Enter Confirm New UserName");
			} else if (!confirmuser.equals(newuser)) {
				throw new Exception("Enter Correct UserName");
			}
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.changeUserName(newuser, invusername);
			setInvusername(newuser);
			logger.info("User name -->" + newuser);
			return "userNameChange";
		} catch (Exception e) {
			setMessage(e.getMessage());
			return "";
		}
		finally {
			 ctx = null;
			 controller = null;
		}
	}

	public String passwordChange() {
		logger.info("------------------------------------- Inside passwordChange() method ----------------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			if (currentpasswrd.equals("")) {
				throw new Exception("Enter Current Password");
			} else if (!currentpasswrd.equals(invpassword)) {
				throw new Exception("Current password You enter is Incorrect");
			} else if (newpasswrd.equals("")) {
				throw new Exception("Enter New Password");
			} else if (confirmpasswrd.equals("")) {
				throw new Exception("Enter Confirm New Password");
			} else if (!confirmpasswrd.equals(newpasswrd)) {
				throw new Exception("Enter Correct Password");
			}
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.changeUserPassword(newpasswrd, invpassword);
			setInvpassword(newpasswrd);
			logger.info("password  -  > " + newpasswrd);
			return "userPasswordChange";
		} catch (Exception e) {
			setMessage(e.getMessage());
			return "";
		}
		finally {
			 ctx = null;
			 controller = null;
		}
	}

	private boolean disableMenu;

	public boolean isDisableMenu() {
		return disableMenu;
	}

	public void setDisableMenu(boolean disableMenu) {
		this.disableMenu = disableMenu;
	}

	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public LoginMB() {
		sortsOrders = new HashMap<String, SortOrder>();
		sortPriorities = new ArrayList<String>();
	}
//Who put this code , try catch  missing 
	public void sort() {
		logger.info("----------------------------- Inside sort() method ----------------------------- ");
		String property = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get(SORT_PROPERTY_PARAMETER);
		if (property != null) {
			SortOrder currentPropertySortOrder = sortsOrders.get(property);
			if (multipleSorting) {
				if (!sortPriorities.contains(property)) {
					sortPriorities.add(property);
				}
			} else {
				sortsOrders.clear();
			}
			if (currentPropertySortOrder == null
					|| currentPropertySortOrder.equals(SortOrder.DESCENDING)) {
				sortsOrders.put(property, SortOrder.ASCENDING);
			} else {
				sortsOrders.put(property, SortOrder.DESCENDING);
			}

		}
	}

	public Map<String, SortOrder> getSortsOrders() {
		return sortsOrders;
	}

	public void setSortsOrders(Map<String, SortOrder> sortsOrders) {
		this.sortsOrders = sortsOrders;
	}

	public List<String> getSortPriorities() {
		return sortPriorities;
	}

	public void setSortPriorities(List<String> sortPriorities) {
		this.sortPriorities = sortPriorities;
	}

	public boolean isMultipleSorting() {
		return multipleSorting;
	}

	public void setMultipleSorting(boolean multipleSorting) {
		this.multipleSorting = multipleSorting;
	}

	//Do check this code if this mehtod is using the appln or not if not remove , test in development
	private boolean validate(boolean flag) {
		logger.info("----------------------------- Inside validate() method ----------------------------- ");
		boolean valid = true;
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		if (invusername.equalsIgnoreCase("")) {
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("username")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Enter the UserName."));
			}
			valid = false;
		}
		if (invpassword.equalsIgnoreCase("")) {
			logger.debug("inside password if");
			if (flag) {
				fieldName = CommonValidate.findComponentInRoot("Password")
						.getClientId(fc);
				fc.addMessage(fieldName, new FacesMessage(
						"Please Enter the Password."));
			}
			valid = false;
		}
		return valid;
	}

	//Finally missing with all open object not yet closed , do put finally and do proper manner 13 / May / 2017
	public String userLogin() {
		logger.info("----------------------------- Inside userLogin() method ----------------------------- ");
		DemoController controller = null;
		submenuList=null;
		DefaultMenuItem item;
		String status;
		String Errstatus;
		String fieldName; // This field is not used any where do check in development environment and remove if not require
		setValidate("");
		purchaseorder.setGolbalnamesearch("");
		loginaccess.setUsername("");
		loginaccess.setGlobalValue("");
		logger.info("username" + loginaccess.getUsername());
		loginaccess.setUserpassword("");
		logger.info("password" + loginaccess.getUserpassword());
		FacesContext fc = FacesContext.getCurrentInstance(); // // This field is not used any where do check in development environment and remove if not require
		model = new DefaultMenuModel();
		try {
			 submenuList=new ArrayList<SubProduct>();
				logger.info("----------------------------- Inside userLogin() method try block called ----------------------------- "); // why this empty after the line do check properlly
			if(invusername.equalsIgnoreCase("") && invpassword.equalsIgnoreCase("")){
				throw new Exception("Enter Your User Name & Password");
			}
			try{
				System.out.println("inside user name"+invusername);
				if(invusername.equalsIgnoreCase("")){
					//THis is info we are not printing any objects why logger.debug , it need come as logger.info
					logger.debug("----------------------------- Inside userLogin() method second try If called  ----------------------------- ");
					throw new Exception("Please Enter User Name");
				}
			}catch(Exception e){
				throw new Exception("Please Enter User Name");
			}
			try{
				if(invpassword.equalsIgnoreCase("")){
					logger.debug("----------------------------- Inside userLogin() method third try If called ----------------------------- ");
					throw new Exception("Please Enter Password");
				}
			}catch(Exception e){
				throw new Exception("Please Enter Password");
			}
				ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				loginaccess.setUsername(invusername);
				loginaccess.setUserpassword(invpassword);
				logger.debug("User name ------------------------------------->" + loginaccess.getUsername());
				logger.debug("Password -------------------------------------->" + loginaccess.getUserpassword());
				status = controller.databaseValidate(loginaccess);
				System.out.println("user type"+loginaccess.getUserType());
				setClientName(loginaccess.getClientName());
				setUserName(loginaccess.getUsername().substring(0,1).toUpperCase()+loginaccess.getUsername().substring(1));
				if (status.equalsIgnoreCase("success")) {
					ExternalContext externalcontext = FacesContext.getCurrentInstance().getExternalContext();
					Map<String, Object> sessionMap = externalcontext.getSessionMap();
					sessionMap.put("ClientID", loginaccess.getClientID());
					sessionMap.put("userID", loginaccess.getUser_ID());
					sessionMap.put("userType", loginaccess.getUserType());
					sessionMap.put("baseCurrency", loginaccess.getBaseCurrency());
					sessionMap.put("clientCountry", loginaccess.getCountry());
					setUserType(loginaccess.getUserType());
					System.out.println("user id "+(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
							.get("userID"));
					System.out.println("ClientID id "+(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
							.get("ClientID"));
					RequestContext rq=RequestContext.getCurrentInstance();
					rq.execute("PF('loginblockUI').hide();");
					logger.debug("User menu size ------------------>"+ loginaccess.getUser_Product().size());
					System.out.println("User client ID ------------------>"+loginaccess.getClientID());
					if(loginaccess.getClientID().equals(Util.getMessage("SIOCOA", "messages"))){
						setLogo(Util.getMessage("SIOCOA_LOGO", "messages"));
					}else{
						setLogo(Util.getMessage("NRG"+"_LOGO", "messages"));
					}
					controller.getdashboardCount(loginaccess);
					setPurchaseCount(loginaccess.getPurchaseCount());
					setSalesCount(loginaccess.getSalesCount());
					setStockCount(loginaccess.getStockCount());
					setProductCount(loginaccess.getProductCount());
					setCustomerCount(loginaccess.getCustomerCount());
					setVendorCount(loginaccess.getVendorCount());
					if (loginaccess.getUser_Product().size() > 0) {
						item = new DefaultMenuItem("Dashboard");
						item.setCommand("#{loginMB.getdashboard}");
						item.setAjax(false);
						item.setUpdate(":center_content");
						/*
						 * item.setUrl("../../pages/xhtml/businesspartner.xhtml")
						 * ;
						 */
						model.addElement(item);
						for (int i = 0; i < loginaccess.getUser_Product().size(); i++) {
							 DefaultSubMenu submenu = new DefaultSubMenu(loginaccess.getUser_Product().get(i).getProduct().getProductName());
							 submenuList=controller.submenus(loginaccess.getUser_Product().get(i).getProduct().getProduct_ID(),loginaccess.getUser_Product().get(i).getProduct().getProductCode());
							 if(submenuList.size()>0){
								 for(int j=0;j<submenuList.size();j++){
									if(submenuList.get(j).getProductCode().equalsIgnoreCase("NV01")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{vendorRegisterFormMB.vendorDirect}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("VC01")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setCommand("#{loginMB.business}");
										menu.setIcon("fa fa-info");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("NC01")){
										DefaultMenuItem menu=null;
										if(loginaccess.getClientID().equals(Util.getMessage("SIOCOA", "messages"))){
											menu=new DefaultMenuItem(Util.getMessage("AddMember", "messages"));
										}else{
											menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										}
										menu.setAjax(false);
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{salesOrderFormMB.redirectbuyerRegister}");
										submenu.addElement(menu);
									}
									// Why else if , You can put If condition instead of else If , do change and test in development if working then make as If condition
									else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CC01")){
										DefaultMenuItem menu=null;
										if(loginaccess.getClientID().equals(Util.getMessage("SIOCOA", "messages"))){
											menu=new DefaultMenuItem(Util.getMessage("MemberConsole", "messages"));
										}else{
											menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										}
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{loginMB.businesscustomer}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("PON02")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{loginMB.purchaseorderpage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("POC02")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{loginMB.purchasepage}");
										submenu.addElement(menu);
									}
									else if(submenuList.get(j).getProductCode().equalsIgnoreCase("SON03")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{salesOrderFormMB.salesOrderpage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("SOC03")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{loginMB.salesorder}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("QSON03")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{quickSaleMB.quicksales}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("QSOC03")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{loginMB.quickksales}");
										submenu.addElement(menu);
									}
									else if(submenuList.get(j).getProductCode().equalsIgnoreCase("SALQUO")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{salesOrderFormMB.getDataLoad}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("SALOCO")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{salesOrderFormMB.getSalesQuoteView}");
										submenu.addElement(menu);
									}
									else if(submenuList.get(j).getProductCode().equalsIgnoreCase("TF04")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{aTransactionMB.accountstransactionpage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("APL04")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setCommand("#{loginMB.accountsPayableLiablity}");
										menu.setIcon("fa fa-cc-amex");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("ARA04")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setCommand("#{loginMB.accountsReceivable}");
										menu.setIcon("fa fa-credit-card");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CB04")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-money");
										menu.setCommand("#{loginMB.accountscashbook}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("Inv05")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setCommand("#{loginMB.inventorypage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("MIS06")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{loginMB.reportspage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("MIS061")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{reportMB.dailyreportspage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("MIS062")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{reportMB.weeklyreportPage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("MIS063")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{reportMB.monthlyreportspage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("MIS064")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{reportMB.quarterlyreportspage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("MIS065")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{reportMB.halfyearlyreportspage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("MIS066")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{reportMB.annualreportspage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("PF08")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{loginMB.productReg}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("PC08")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{loginMB.product}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CAT08")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{productViewMB.categoryView}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CR11")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{clientMB.superadminpage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CV11")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{loginMB.clientView}");
										menu.setUpdate(":center_content");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("UV11")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{loginMB.userView}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("EMP07")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{employeeRegistrationFormMB.employeeFormPage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("EMV07")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{loginMB.hrEmployeepage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("PR07")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{loginMB.hrPayrollpage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("PV07")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{employeePayrollViewFormMB.employeepayrollPage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("FIL001")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{employeeRegistrationFormMB.hrFileUpload}");
										submenu.addElement(menu);	
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("PL04")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-bar-chart");
										menu.setCommand("#{accountsMB.chartofAccountsPage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("JE01")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-address-book");
										menu.setCommand("#{accountsMB.journalEntryPage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("GL01")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-address-book");
										menu.setCommand("#{accountsMB.generalLedgerPage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("TB02")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-columns");
										menu.setCommand("#{accountsMB.trialBalancePage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("BS03")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-columns");
										menu.setCommand("#{accountsMB.balanceSheetPage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("PL05")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-line-chart");
										menu.setCommand("#{accountsMB.profitLossPage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("QUON01")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{vendorRegisterFormMB.quotationPage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("QUOC02")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{vendorRegisterFormMB.quotationConsolePage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("APR01")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false);
										menu.setIcon("fa fa-info");
										menu.setCommand("#{loginMB.approvalPage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CRMN00")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false); 
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{crmMB.crmNewNavigation}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CRME00")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false); 
										menu.setIcon("fa fa-exclamation");
										menu.setCommand("#{crmMB.crmEnquiryNavigation}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CRMR00")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false); 
										menu.setIcon("fa fa-angle-double-right");
										menu.setCommand("#{crmMB.crmReachesNavigation}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CRML00")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false); 
										menu.setIcon("fa fa-line-chart");
										menu.setCommand("#{crmMB.crmLeadsNavigation}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CRMI00")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false); 
										menu.setIcon("fa fa-upload");
										menu.setCommand("#{crmMB.crmImpLeadsNavigation}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CRMC00")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false); 
										menu.setIcon("fa fa-crosshairs");
										menu.setCommand("#{crmMB.crmClosureNavigation}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CRMNe00")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false); 
										menu.setIcon("fa fa-arrows");
										menu.setCommand("#{crmMB.crmNegosationNavigation}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CRMO00")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false); 
										menu.setIcon("fa fa-plus");
										menu.setCommand("#{crmMB.crmOnboardNavigation}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CRMCo00")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false); 
										menu.setIcon("fa fa-check");
										menu.setCommand("#{crmMB.crmCompletedNavigation}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("CRMRe00")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false); 
										menu.setIcon("fa fa-times");
										menu.setCommand("#{crmMB.crmRejectedNavigation}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("ACCSAL01")){
										DefaultMenuItem menu=null;
										if(loginaccess.getClientID().equals(Util.getMessage("SIOCOA", "messages"))){
											menu=new DefaultMenuItem(Util.getMessage("Income", "messages"));
										}else{
											menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										}
										menu.setAjax(false); 
										menu.setIcon("fa fa-shopping-cart");
										menu.setCommand("#{aTransactionMB.salesaccountstransactionpage}");
										submenu.addElement(menu);
									}else if(submenuList.get(j).getProductCode().equalsIgnoreCase("ACCEXP02")){
										DefaultMenuItem menu=new DefaultMenuItem(submenuList.get(j).getProductName());
										menu.setAjax(false); 
										menu.setIcon("fa fa-cart-arrow-down");
										menu.setCommand("#{aTransactionMB.accountExpensesPage}");
										submenu.addElement(menu);
									}
								 }
						     }
							 model.addElement(submenu);
						}

						item = new DefaultMenuItem("Logout");
						item.setCommand("#{loginMB.getlogout}");
						item.setAjax(false);
						item.setUpdate(":center_content");
						model.addElement(item);

						RequestContext.getCurrentInstance().execute(
								"PF('loginblocksUI').hide();");

					}
					logger.info("------------------------------ Menu loading  completed !!! ------------------------------ "); //remove space
					if(loginaccess.getUsername().equalsIgnoreCase("superadmin")){
						return "superadminsuccess";
					}
					else{
						return "success2";
					}
				} else {
					Errstatus = controller.checkErrorcode(loginaccess);
					logger.info("Error Message" + Errstatus);
					if (!Errstatus.equalsIgnoreCase("")) {
						logger.info("---------------------------------- Inside error status ----------------------------- "); //remove space
						throw new DemoException("" + Errstatus);

					}

					return "failure1";
				}
		} catch (Exception e) {
			logger.info("---------------------------------- Inside Exception ----------------------------- ");
			logger.error("Exception ----------------------------------> " +e.getMessage());
			setValidate(e.getMessage());
		}
		//fianlly missing, put the closing objects and do test in local properly 
		finally {
			
		}
		return "";
	}

	//No try catch and finally block for the below method
	public void changeEvent(ValueChangeEvent event) {
		logger.info("--------------------------------------- Inside changeEvent() method --------------------------------------- ");
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		logger.info("Input Text" + event.getNewValue());
		String aa = event.getNewValue().toString();
		loginaccess.setUsername(aa);
		if (loginaccess.getUsername().equalsIgnoreCase("")
				|| loginaccess.getUsername() == null) {
			//This need to come in top of the method start why it is coming in middle 
			//logger.info("--------------------------------------- Inside changeEvent() method --------------------------------------- ");
			fieldName = CommonValidate.findComponentInRoot("username")
					.getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage(
					"Please Enter the username"));
		}

	}
//Logger is not properlly put and try catch and finally missing
	public void changeEvent1(ValueChangeEvent event) {
		String fieldName;
		FacesContext fc = FacesContext.getCurrentInstance();
		logger.info("Input text value --------------------------------> " + event.getNewValue());
		String aa = event.getNewValue().toString();
		loginaccess.setUserpassword(aa);
		if (loginaccess.getUserpassword().equalsIgnoreCase("")
				|| loginaccess.getUserpassword() == null) {
			logger.info("Inside Method");
			fieldName = CommonValidate.findComponentInRoot("Password")
					.getClientId(fc);
			fc.addMessage(fieldName, new FacesMessage(
					"Please Enter the password"));
		}

	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	//finally missing 
	public String getdashboard() {
		logger.info("--------------------------------------- Inside getdashboard() method start ----------------------------------");
		purchaseorder.setGolbalnamesearch("");
		try {
			chartView2.business();
			logger.info("--------------------------------------- getdashboard() method completed !!! ----------------------------------");
		} catch (Exception e) {
			logger.error("Exception --------------------------->"+e.getMessage());
		}
		finally {
			
		}
		return "dashboard";
	}

	public List<VendorCount> countList = null;
	public int vendorcount;

	public int getVendorcount() {
		return vendorcount;
	}

	public void setVendorcount(int vendorcount) {
		this.vendorcount = vendorcount;
	}

	public int getCustomercount() {
		return customercount;
	}

	public void setCustomercount(int customercount) {
		this.customercount = customercount;
	}

	public int customercount;

	public List<VendorCount> getCountList() {
		return countList;
	}

	public void setCountList(List<VendorCount> countList) {
		this.countList = countList;
	}
//This method looks better way 
	public String business() {
		logger.info("--------------------------------------- Inside business() method start ----------------------------------");
		purchaseorder.setGolbalnamesearch("");
		try {
			vendorViewFormMB.setUserType(loginaccess.getUserType());
			vendorViewFormMB.success();
			logger.info("--------------------------------------- Inside business() method completed !!! ----------------------------------");
		} catch (Exception e) {
			logger.error("Exception --------------------------------> " +e.getMessage());
		}
		return "businesspartner";

	}

	/* prema begin 23/05/2016 pie chart for vendor&customer count */

	private PieChartModel pieModel2;

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
	}
// Do check if your using this method or not 
	private PieChartModel getLivePieModel() {
		logger.info("--------------------------------------- Inside getLivePieModel() method ----------------------------------");
		pieModel2 = new PieChartModel();
		pieModel2.setSeriesColors("FFCC33,051C5F");
		pieModel2.set("vendor", vendorcount);
		pieModel2.set("customer", customercount);
		pieModel2.setTitle("");
		pieModel2.setLegendPosition("w");
		pieModel2.setDiameter(150);
		return pieModel2;
	}

	public String salesorder() {
		purchaseorder.setGolbalnamesearch("");
		salesViewMB.setUserType(loginaccess.getUserType());
		salesViewMB.salesView();
		return "Salesorder";

	}
	
	public String quickksales() {
		purchaseorder.setGolbalnamesearch("");
		quickSaleViewMB.quicksaleview();
		quickSaleViewMB.setUserType(loginaccess.getUserType());
		return "Salesquick";

	}
// This method  looks better way
	public String purchasepage() {
		logger.info("--------------------------------------- Inside purchasepage() method ----------------------------------");
		purchaseorder.setGolbalnamesearch("");
		try {
			purchaseViewMB.setUserType(loginaccess.getUserType());
			purchaseViewMB.purchaseView();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "purchase";
		
	}
	public String purchaseorderpage() {
		logger.info("Inside purchasepage");
		purchaseorder.setGolbalnamesearch("");
		try {
			/* chartView2.business2(); */
			purchaseOrderFromMB.purchase();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "purchaseOrderNewForm";

	}

// Below methods look better standard
	
	public String accountsPayableLiablity() {
		logger.info("--------------------------------------- Inside accountsPayableLiablity() method ----------------------------------");
		purchaseorder.setGolbalnamesearch("");
		purchaseExpenseMB.purchaseExpense();
		
		return "accountsPayableLiablity";

	}
	public String accountscashbook() {
		logger.info("--------------------------------------- Inside accountscashbook() method ----------------------------------");
		purchaseorder.setGolbalnamesearch("");
		cashBookMB.findCashBook();
		return "accountsCashBook";

	}
	public String accountsReceivable() {
		logger.info("--------------------------------------- Inside accountsReceivable() method ----------------------------------");
		purchaseorder.setGolbalnamesearch("");
		salesIncomeMB.SalesIncomeAsset();
		return "accountReceivable";

	}
	public String accountsProfitandloss() {
		logger.info("--------------------------------------- Inside accountsProfitandloss() method ----------------------------------");
		purchaseorder.setGolbalnamesearch("");
		profitLossMB.profitLose();
		return "accountsProfitandLoss";

	}

	public String inventorypage() {
		logger.info("--------------------------------------- Inside inventorypage() method ----------------------------------");
		purchaseorder.setGolbalnamesearch("");
		stockViewMB.stockView3();
		return "inventory";

	}

	public String reportspage() {
		logger.info("--------------------------------------- Inside reportspage() method ----------------------------------");
		purchaseorder.setGolbalnamesearch("");
		return "reports";

	}
	public String hrEmployeepage() {
		logger.info("--------------------------------------- Inside hrEmployeepage() method ----------------------------------");
		purchaseorder.setGolbalnamesearch("");
		employeeDetailsViewFormMB.setStatus("empView");
		employeeDetailsViewFormMB.setUserType(loginaccess.getUserType());
		employeeDetailsViewFormMB.view();
		return "hrEmployee";

	}
	public String hrPayrollpage() {
		logger.info("--------------------------------------- Inside hrPayrollpage() method ----------------------------------");
		purchaseorder.setGolbalnamesearch("");
		employeeDetailsViewFormMB.setStatus("empPayroll");
		employeeDetailsViewFormMB.view();
	    employeePayrollFormMB.tabchange();
		return "hrpayroll";
	}
	// Top methods look better standard


	public ProductViewMB getProductViewMB() {
		return productViewMB;
	}

	public void setProductViewMB(ProductViewMB productViewMB) {
		this.productViewMB = productViewMB;
	}

	public String invoicepage() {
		logger.info("--------------------------------------- Inside invoicepage() method ----------------------------------");
		purchaseorder.setGolbalnamesearch("");
		return "invoice";

	}

	public String paymentpage() {
		logger.info("----------------------------- Inside paymentpage() method ----------------------------- ");
		purchaseorder.setGolbalnamesearch("");
		return "payment";

	}


	public String getlogout()  {
		logger.info("----------------------------- Inside getlogout() method ----------------------------- ");

		try {
			loginaccess.setUsername(""
					+ FacesContext.getCurrentInstance().getExternalContext()
							.getSessionMap().get("User"));
			loginaccess.setStatus("deactive");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.userLogin(loginaccess);
			HttpSession session = Util.getSession();
			session.invalidate();
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			Cookie[] cookies = request.getCookies();
			Cookie opentoken = null;
			for (Cookie c : cookies) {
				if (c.getName().equals("opentoken")) {
					logger.debug("found the cookie: " + c.getName()
							+ " domain:" + c.getDomain() + " exp:"
							+ c.getMaxAge()); // log4j debug statement
					opentoken = c;
					opentoken.setMaxAge(0);
					opentoken.setValue(""); // it is more elegant to clear the
											// value but not necessary
					response.addCookie(opentoken);
					logger.debug("redirecting to " + request.getContextPath());
					response.sendRedirect(request.getContextPath());
					break;
				}
			}
			return "login";
		} catch (Exception e) {
			logger.error("inside  Eception :)");
			return "failure";
		}
	}

	List<I0001> i0001s = null;
	ArrayList<ProductRegister> prodList = null;
	ArrayList<EmployeeDetail> employeeList = null;
	ArrayList<StockReport> stockReportlist = null;
	ArrayList<StockReport> stockReportlist1 = null;
	public List<Employee> emplist = null;
	ArrayList<Vendor> ven1 = null;
	ArrayList<Buyer> customerList1 = null;
	public ArrayList<I0025> vendorlist = null;
	ProductRegister productRegister = new ProductRegister();
	StockReport stock1 = new StockReport();
	EmployeeDetail employee = new EmployeeDetail();
	Vendor vendor = new Vendor();
	private boolean productFlag = false;
	private boolean purchaseFlag = false;
	private boolean vendorFlag = false;
	private boolean customerFlag = false;
	private boolean employeeFlag = false;
	private boolean salesFlag = false;
	private boolean qsFlag = false;
	private boolean norecordFlag = false;
	Buyer b = new Buyer();
	public List<I0032> customerlist = null;
	public List<I0025> vendorphoneList = null;
	public String Type;

	public boolean isNorecordFlag() {
		return norecordFlag;
	}

	public void setNorecordFlag(boolean norecordFlag) {
		this.norecordFlag = norecordFlag;
	}

	public ArrayList<StockReport> getStockReportlist1() {
		return stockReportlist1;
	}

	public void setStockReportlist1(ArrayList<StockReport> stockReportlist1) {
		this.stockReportlist1 = stockReportlist1;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public ArrayList<StockReport> getStockReportlist() {
		return stockReportlist;
	}

	public void setStockReportlist(ArrayList<StockReport> stockReportlist) {
		this.stockReportlist = stockReportlist;
	}

	public List<I0025> getVendorphoneList() {
		return vendorphoneList;
	}

	public void setVendorphoneList(List<I0025> vendorphoneList) {
		this.vendorphoneList = vendorphoneList;
	}

	public boolean isSalesFlag() {
		return salesFlag;
	}

	public void setSalesFlag(boolean salesFlag) {
		this.salesFlag = salesFlag;
	}

	public boolean isQsFlag() {
		return qsFlag;
	}

	public void setQsFlag(boolean qsFlag) {
		this.qsFlag = qsFlag;
	}

	public ArrayList<EmployeeDetail> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<EmployeeDetail> employeeList) {
		this.employeeList = employeeList;
	}

	public List<Employee> getEmplist() {
		return emplist;
	}

	public void setEmplist(List<Employee> emplist) {
		this.emplist = emplist;
	}

	public boolean isEmployeeFlag() {
		return employeeFlag;
	}

	public void setEmployeeFlag(boolean employeeFlag) {
		this.employeeFlag = employeeFlag;
	}

	public boolean isCustomerFlag() {
		return customerFlag;
	}

	public void setCustomerFlag(boolean customerFlag) {
		this.customerFlag = customerFlag;
	}

	public ArrayList<Buyer> getCustomerList1() {
		return customerList1;
	}

	public void setCustomerList1(ArrayList<Buyer> customerList1) {
		this.customerList1 = customerList1;
	}

	public List<I0032> getCustomerlist() {
		return customerlist;
	}

	public void setCustomerlist(List<I0032> customerlist) {
		this.customerlist = customerlist;
	}

	public boolean isVendorFlag() {
		return vendorFlag;
	}

	public void setVendorFlag(boolean vendorFlag) {
		this.vendorFlag = vendorFlag;
	}

	public ArrayList<Vendor> getVen1() {
		return ven1;
	}

	public void setVen1(ArrayList<Vendor> ven1) {
		this.ven1 = ven1;
	}

	public ArrayList<I0025> getVendorlist() {
		return vendorlist;
	}

	public void setVendorlist(ArrayList<I0025> vendorlist) {
		this.vendorlist = vendorlist;
	}

	public boolean isPurchaseFlag() {
		return purchaseFlag;
	}

	public void setPurchaseFlag(boolean purchaseFlag) {
		this.purchaseFlag = purchaseFlag;
	}

	public ProductRegister getProductRegister() {
		return productRegister;
	}

	public void setProductRegister(ProductRegister productRegister) {
		this.productRegister = productRegister;
	}

	public boolean isProductFlag() {
		return productFlag;
	}

	public void setProductFlag(boolean productFlag) {
		this.productFlag = productFlag;
	}

	public List<I0001> getI0001s() {
		return i0001s;
	}

	public void setI0001s(List<I0001> i0001s) {
		this.i0001s = i0001s;
	}

	public ArrayList<ProductRegister> getProdList() {
		return prodList;
	}

	public void setProdList(ArrayList<ProductRegister> prodList) {
		this.prodList = prodList;
	}

	//This method need to Simplyfiy as it seems to be  not good manner of coding 
	public void globalSearch() {
		logger.info("----------------------------- Inside globalSearch() method ----------------------------- ");
		orderList = null;
		setPurchaseFlag(false);
		setProductFlag(false);
		setSalesFlag(false);
		setEmployeeFlag(false);
		setVendorFlag(false);
		setQsFlag(false);
		setCustomerFlag(false);
		setNorecordFlag(false);
		try {
			orderList = new ArrayList<PurchaseOrder>();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			logger.info("name " + purchaseorder.getGolbalnamesearch());
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if (purchaseorder.getGolbalnamesearch() == null
					|| purchaseorder.getGolbalnamesearch().equalsIgnoreCase("")) {
				Map<String, Object> options = new HashMap<String, Object>();
				options.put("modal", true);
				options.put("draggable", false);
				options.put("resizable", false);
				options.put("contentHeight", 500);
				options.put("contentWidth", 800);
				RequestContext.getCurrentInstance().execute(
						"PF('globalblocksUI').hide();");
				RequestContext.getCurrentInstance().openDialog(
						"globalnamesearch", options, null);
				setNorecordFlag(true);
				logger.info("inside empty value else");
			} else if (purchaseorder.getGolbalnamesearch() != null
					|| !purchaseorder.getGolbalnamesearch()
							.equalsIgnoreCase("")) {
				Map<String, Object> options = new HashMap<String, Object>();
				options.put("modal", true);
				options.put("draggable", false);
				options.put("resizable", false);
				options.put("contentHeight", 500);
				options.put("contentWidth", 800);
				if (CommonValidate.isNumeric(purchaseorder
						.getGolbalnamesearch())) {
					logger.info("inside if number");
					RequestContext.getCurrentInstance().openDialog(
							"globalnamesearch", options, null);
					vendorphoneList = controller
							.getphonenumberglobalsearch(purchaseorder
									.getGolbalnamesearch());
					logger.info("vendor phone list size"
							+ vendorphoneList.size());
					ven1 = new ArrayList<Vendor>();
					for (int i = 0; i < vendorphoneList.size(); i++) {
						Vendor ven = new Vendor();
						ven.setVendorPhoneNumber(vendorphoneList.get(i)
								.getVendorPhoneNumber());
						ven.setCity(vendorphoneList.get(i).getCityName());
						ven.setCountry(vendorphoneList.get(i).getI0028()
								.getName());
						ven.setFirmName(""
								+ new BigDecimal(vendorphoneList.get(i)
										.getFirmName()));
						ven.setEmail_ID_vendor(vendorphoneList.get(i)
								.getEmail_ID_vendor());
						ven1.add(ven);
					}
					RequestContext.getCurrentInstance().execute(
							"PF('globalblocksUI').hide();");
					if (ven1.size() > 0) {
						setVendorFlag(true);
					} else {
						setVendorFlag(false);
					}

					customerlist = controller.getcustphnosearch(purchaseorder
							.getGolbalnamesearch());
					logger.info("customer phone list size"
							+ customerlist.size());
					customerList1 = new ArrayList<Buyer>();
					for (int i = 0; i < customerlist.size(); i++) {
						b = new Buyer();
						b.setCustomerName(customerlist.get(i).getCustomerName());
						b.setPhoneNumber(""
								+ new BigDecimal(customerlist.get(i)
										.getPhoneNumber()));
						b.setCity(customerlist.get(i).getCity());
						b.setCountry(customerlist.get(i).getCountry());
						b.setCategoryName(customerlist.get(i).getCategoryName());
						b.setMail(customerlist.get(i).getEMail());
						customerList1.add(b);
					}
					logger.info("customer list1 size" + customerList1.size());
					RequestContext.getCurrentInstance().execute(
							"PF('globalblocksUI').hide();");
					if (customerList1.size() > 0) {
						setCustomerFlag(true);
					} else {
						setCustomerFlag(false);
					}
					emplist = controller.getempphnosearch(purchaseorder
							.getGolbalnamesearch());
					employeeList = new ArrayList<EmployeeDetail>();

					for (int i = 0; i < emplist.size(); i++) {
						employee = new EmployeeDetail();
						employee.setName(emplist.get(i).getEmployeeName());
						employee.setPhno(emplist.get(i).getPhoneNumber());
						employee.setEmployeeid(emplist.get(i).getEmployeeId());
						employee.setRegid(emplist.get(i).getRegistrationId());
						employee.setMailid(emplist.get(i).getEmailId());
						employee.setDesignation(emplist.get(i).getDesignation()
								.getType());
						employeeList.add(employee);
					}
					RequestContext.getCurrentInstance().execute(
							"PF('globalblocksUI').hide();");
					if (employeeList.size() > 0) {
						setEmployeeFlag(true);
					} else {
						setEmployeeFlag(false);
					}
				} else if (CommonValidate.validateEmail(purchaseorder
						.getGolbalnamesearch())) {
					logger.info("inside email search");
					RequestContext.getCurrentInstance().openDialog(
							"globalnamesearch", options, null);
					vendorlist = controller.getgmailsearch(purchaseorder
							.getGolbalnamesearch());
					ven1 = new ArrayList<Vendor>();
					if (vendorlist.size() > 0) {
						for (int j = 0; j < vendorlist.size(); j++) {
							logger.info(vendorlist.get(j)
									.getVendorPhoneNumber());
							logger.info((vendorlist.get(j).getFirmName()));
							vendor = new Vendor();
							vendor.setVendorPhoneNumber(vendorlist.get(j)
									.getVendorPhoneNumber());
							vendor.setCity(vendorlist.get(j).getCityName());
							vendor.setCountry(vendorlist.get(j).getI0028()
									.getName());
							vendor.setFirmName(""
									+ new BigDecimal(vendorlist.get(j)
											.getFirmName()));
							vendor.setEmail_ID_vendor(vendorlist.get(j)
									.getEmail_ID_vendor());
							ven1.add(vendor);
						}
						RequestContext.getCurrentInstance().execute(
								"PF('globalblocksUI').hide();");
						if (ven1.size() > 0) {
							setVendorFlag(true);
						} else {
							setVendorFlag(false);
						}
					} else {
						setVendorFlag(false);
					}
					customerlist = controller.getcustemailsearch(purchaseorder
							.getGolbalnamesearch());
					customerList1 = new ArrayList<Buyer>();
					if (customerlist.size() > 0) {
						for (int i = 0; i < customerlist.size(); i++) {
							b = new Buyer();
							b.setCustomerName(customerlist.get(i)
									.getCustomerName());
							b.setPhoneNumber(""
									+ new BigDecimal(customerlist.get(i)
											.getPhoneNumber()));
							b.setCity(customerlist.get(i).getCity());
							b.setCountry(customerlist.get(i).getCountry());
							b.setCategoryName(customerlist.get(i)
									.getCategoryName());
							b.setMail(customerlist.get(i).getEMail());
							customerList1.add(b);
						}
						RequestContext.getCurrentInstance().execute(
								"PF('globalblocksUI').hide();");
						if (customerList1.size() > 0) {
							setCustomerFlag(true);
						} else {
							setCustomerFlag(false);
						}
					} else {
						setCustomerFlag(false);
					}
					emplist = controller.getempemailsearch(purchaseorder
							.getGolbalnamesearch());
					employeeList = new ArrayList<EmployeeDetail>();
					if (emplist.size() > 0) {
						for (int i = 0; i < emplist.size(); i++) {
							employee = new EmployeeDetail();
							employee.setName(emplist.get(i).getEmployeeName());
							employee.setPhno(emplist.get(i).getPhoneNumber());
							employee.setEmployeeid(emplist.get(i)
									.getEmployeeId());
							employee.setRegid(emplist.get(i)
									.getRegistrationId());
							employee.setMailid(emplist.get(i).getEmailId());
							employee.setDesignation(emplist.get(i)
									.getDesignation().getType());
							employeeList.add(employee);
						}
						RequestContext.getCurrentInstance().execute(
								"PF('globalblocksUI').hide();");
						if (employeeList.size() > 0) {
							setEmployeeFlag(true);
						} else {
							setEmployeeFlag(false);
						}
					} else {
						setEmployeeFlag(false);
					}
				} else if (purchaseorder.getGolbalnamesearch()
						.equalsIgnoreCase("gmail")) {
					logger.info("inside gmail search");
					RequestContext.getCurrentInstance().openDialog(
							"globalnamesearch", options, null);
					vendorlist = controller.vendorView(vendor);
					logger.debug("vendor list size ----------------------------->"+ vendorlist.size());
					ven1 = new ArrayList<Vendor>();
					if (vendorlist.size() > 0) {
						for (int i = 0; i < vendorlist.size(); i++) {
							Vendor vendor = new Vendor();
							try {
								if (vendorlist.get(i).getEmail_ID_vendor()
										.contains("@gmail")) {
									vendor.setVendorPhoneNumber(vendorlist.get(
											i).getVendorPhoneNumber());
									vendor.setCity(vendorlist.get(i)
											.getCityName());
									vendor.setCountry(vendorlist.get(i)
											.getI0028().getName());
									vendor.setFirmName(""
											+ new BigDecimal(vendorlist.get(i)
													.getFirmName()));
									vendor.setEmail_ID_vendor(vendorlist.get(i)
											.getEmail_ID_vendor());
									ven1.add(vendor);
								}
							} catch (Exception e) {
								logger.error("Exception ------------------------>"+e.getMessage());
							}
							logger.info("ven1 size" + ven1.size());
							RequestContext.getCurrentInstance().execute(
									"PF('globalblocksUI').hide();");
						}
						if (ven1.size() > 0) {
							setVendorFlag(true);
						} else {
							setVendorFlag(false);
						}
					} else {
						setVendorFlag(false);
					}
					customerlist = controller.getBuyercustInfo(b);
					customerList1 = new ArrayList<Buyer>();
					if (customerlist.size() > 0) {
						for (int i = 0; i < customerlist.size(); i++) {
							b = new Buyer();
							try {
								if (customerlist.get(i).getEMail()
										.contains("@gmail")) {
									logger.info("inside if");
									b.setCustomerName(customerlist.get(i)
											.getCustomerName());
									b.setPhoneNumber(""
											+ new BigDecimal(customerlist
													.get(i).getPhoneNumber()));
									b.setCity(customerlist.get(i).getCity());
									b.setCountry(customerlist.get(i)
											.getCountry());
									b.setCategoryName(customerlist.get(i)
											.getCategoryName());
									b.setMail(customerlist.get(i).getEMail());
									customerList1.add(b);
								}
							} catch (Exception e) {
								logger.error("Exception ------------------------------->"+e.getMessage());
							}
							logger.debug("Customer List size ---------------------------->"+ customerList1.size());
							RequestContext.getCurrentInstance().execute(
									"PF('globalblocksUI').hide();");
						}
						if (customerList1.size() > 0) {
							setCustomerFlag(true);
						} else {
							setCustomerFlag(false);
						}
					} else {
						setCustomerFlag(false);
					}
					controller.employeeIdInfo(employee);
					setEmplist(employee.getList());
					employeeList = new ArrayList<EmployeeDetail>();
					if (emplist.size() > 0) {
						for (int i = 0; i < emplist.size(); i++) {
							employee = new EmployeeDetail();
							try {
								if (emplist.get(i).getEmailId()
										.contains("@gmail")) {
									employee.setName(emplist.get(i)
											.getEmployeeName());
									employee.setPhno(emplist.get(i)
											.getPhoneNumber());
									employee.setEmployeeid(emplist.get(i)
											.getEmployeeId());
									employee.setRegid(emplist.get(i)
											.getRegistrationId());
									employee.setMailid(emplist.get(i)
											.getEmailId());
									employee.setDesignation(emplist.get(i)
											.getDesignation().getType());
									employeeList.add(employee);
								}
							} catch (Exception e) {
								logger.error("Exception ------------------------->"+e.getMessage());
							}
							RequestContext.getCurrentInstance().execute(
									"PF('globalblocksUI').hide();");
							if (employeeList.size() > 0) {
								setEmployeeFlag(true);
							} else {
								setEmployeeFlag(false);
							}
						}
					} else {
						setEmployeeFlag(false);
					}
				} else if (purchaseorder.getGolbalnamesearch()
						.equalsIgnoreCase("yahoo")) {
					logger.info("inside yahoo search");
					RequestContext.getCurrentInstance().openDialog(
							"globalnamesearch", options, null);
					vendorlist = controller.vendorView(vendor);
					logger.debug("Vendor Size --------------------------->"+ vendorlist.size());
					ven1 = new ArrayList<Vendor>();
					if (vendorlist.size() > 0) {
						for (int i = 0; i < vendorlist.size(); i++) {
							vendor = new Vendor();
							try {
								if (vendorlist.get(i).getEmail_ID_vendor()
										.contains("@yahoo")) {
									vendor.setVendorPhoneNumber(vendorlist.get(
											i).getVendorPhoneNumber());
									vendor.setCity(vendorlist.get(i)
											.getCityName());
									vendor.setCountry(vendorlist.get(i)
											.getI0028().getName());
									vendor.setFirmName(""
											+ new BigDecimal(vendorlist.get(i)
													.getFirmName()));
									vendor.setEmail_ID_vendor(vendorlist.get(i)
											.getEmail_ID_vendor());
									ven1.add(vendor);
								}
							} catch (Exception e) {
								logger.error("Inside Exception", e);
							}
							logger.debug("Vendor 1 Size ------------------------------>"+ven1.size());
							RequestContext.getCurrentInstance().execute(
									"PF('globalblocksUI').hide();");
							if (ven1.size() > 0) {
								setVendorFlag(true);
							} else {
								setVendorFlag(false);
							}
						}
					} else {
						setVendorFlag(false);
					}
					customerlist = controller.getBuyercustInfo(b);
					customerList1 = new ArrayList<Buyer>();
					if (customerlist.size() > 0) {
						for (int i = 0; i < customerlist.size(); i++) {
							b = new Buyer();
							try {
								if (customerlist.get(i).getEMail()
										.contains("@yahoo")) {
									logger.info("inside if");
									b.setCustomerName(customerlist.get(i)
											.getCustomerName());
									b.setPhoneNumber(""
											+ new BigDecimal(customerlist
													.get(i).getPhoneNumber()));
									b.setCity(customerlist.get(i).getCity());
									b.setCountry(customerlist.get(i)
											.getCountry());
									b.setCategoryName(customerlist.get(i)
											.getCategoryName());
									b.setMail(customerlist.get(i).getEMail());
									customerList1.add(b);
								}
							} catch (Exception e) {
								logger.error("Exception -------------------------------->"+e.getMessage());
							}
							logger.info("Customer List Size --------------------------->"+ customerList1.size());
							RequestContext.getCurrentInstance().execute(
									"PF('globalblocksUI').hide();");
							if (customerList1.size() > 0) {
								setCustomerFlag(true);
							} else {
								setCustomerFlag(false);
							}
						}

					} else {
						setCustomerFlag(false);
					}
					controller.employeeIdInfo(employee);
					setEmplist(employee.getList());
					employeeList = new ArrayList<EmployeeDetail>();
					if (emplist.size() > 0) {
						for (int i = 0; i < emplist.size(); i++) {
							employee = new EmployeeDetail();
							try {
								if (emplist.get(i).getEmailId()
										.contains("@yahoo")) {
									employee.setName(emplist.get(i)
											.getEmployeeName());
									employee.setPhno(emplist.get(i)
											.getPhoneNumber());
									employee.setEmployeeid(emplist.get(i)
											.getEmployeeId());
									employee.setRegid(emplist.get(i)
											.getRegistrationId());
									employee.setMailid(emplist.get(i)
											.getEmailId());
									employee.setDesignation(emplist.get(i)
											.getDesignation().getType());
									employeeList.add(employee);
								}
							} catch (Exception e) {
								logger.error("Inside Exception", e);
							}
							RequestContext.getCurrentInstance().execute(
									"PF('globalblocksUI').hide();");
							if (employeeList.size() > 0) {
								setEmployeeFlag(true);
							} else {
								setEmployeeFlag(false);
							}
						}
					} else {
						setEmployeeFlag(false);
					}
				} else {

					logger.info("--------------------------------------- Inside globalSearch() method else ----------------------------------");
					String status = controller.findGlobalSearch(purchaseorder
							.getGolbalnamesearch());
					Map<String, Object> options1 = new HashMap<String, Object>();
					options1.put("modal", true);
					options1.put("draggable", false);
					options1.put("resizable", false);
					options1.put("contentHeight", 500);
					options1.put("contentWidth", 800);
					if (status.equalsIgnoreCase("Purchase")) {
						orderList = CustomJDBC.findGlobalSearch(purchaseorder
								.getGolbalnamesearch());
						RequestContext.getCurrentInstance().execute(
								"PF('globalblocksUI').hide();");
						logger.info("List count -->" + orderList.size());
						RequestContext.getCurrentInstance().openDialog(
								"globalnamesearch", options1, null);
						if (orderList.size() > 0) {
							setPurchaseFlag(true);
							setSalesFlag(false);
							setQsFlag(false);
						} else {
							setPurchaseFlag(false);
							setSalesFlag(false);
							setQsFlag(false);
						}
					} else if (status.equalsIgnoreCase("Sales")) {

						// else if(status.equalsIgnoreCase("Sales")){
						logger.info("Inside Sales");
						orderList = CustomJDBC.findGlobalSearch(purchaseorder
								.getGolbalnamesearch());
						RequestContext.getCurrentInstance().execute(
								"PF('globalblocksUI').hide();");
						logger.info("orderlist size" + orderList.size());
						RequestContext.getCurrentInstance().openDialog(
								"globalnamesearch", options1, null);
						if (orderList.size() > 0) {
							setSalesFlag(true);
							setPurchaseFlag(false);
							setQsFlag(false);
						} else {
							setSalesFlag(false);
							setQsFlag(false);
							setPurchaseFlag(false);
						}
					} else if (status.equalsIgnoreCase("QuickSales")) {
						orderList = CustomJDBC.findGlobalSearch(purchaseorder
								.getGolbalnamesearch());
						RequestContext.getCurrentInstance().execute(
								"PF('globalblocksUI').hide();");
						logger.info("orderlist size" + orderList.size());
						logger.info("Inside quick sales");
						RequestContext.getCurrentInstance().openDialog(
								"globalnamesearch", options1, null);
						if (orderList.size() > 0) {
							setQsFlag(true);
							setSalesFlag(false);
							setPurchaseFlag(false);
						} else {
							setQsFlag(false);
							setPurchaseFlag(false);
							setSalesFlag(false);
						}

					} else {
						RequestContext.getCurrentInstance().execute(
								"PF('globalblocksUI').hide();");
						logger.info("Failure");
					}

					// product name global search
					stockReportlist = new ArrayList<StockReport>();
					logger.info("product name===========>"
							+ purchaseorder.getGolbalnamesearch());
					setType(purchaseorder.getGolbalnamesearch());
					stockReportlist = StockReportJDBC.stocksearch(Type);
					logger.info("product list size==================>"
							+ stockReportlist.size());
					RequestContext.getCurrentInstance().openDialog(
							"globalnamesearch", options1, null);
					stockReportlist1 = new ArrayList<StockReport>();
					if (stockReportlist.size() > 0) {
						for (int i = 0; i < stockReportlist.size(); i++) {
							stock1 = new StockReport();
							stock1.setProductName(stockReportlist.get(i)
									.getProductName());
							stock1.setUnitprice(stockReportlist.get(i)
									.getUnitprice());
							stock1.setAvlQuantity(stockReportlist.get(i)
									.getAvlQuantity());
							stock1.setStockInQuantity(stockReportlist.get(i)
									.getStockInQuantity());
							stock1.setStockOutQuantity(stockReportlist.get(i)
									.getStockOutQuantity());
							stockReportlist1.add(stock1);
							logger.info("product size++++++++++"
									+ stockReportlist1.size());
						}
						RequestContext.getCurrentInstance().execute(
								"PF('globalblocksUI').hide();");
						if (stockReportlist1.size() > 0) {
							setProductFlag(true);
						} else {
							setProductFlag(false);

						}
					} else {
						setProductFlag(false);
					}
					vendorlist = controller.vendorView1(vendor,
							purchaseorder.getGolbalnamesearch());
					ven1 = new ArrayList<Vendor>();
					if (vendorlist.size() > 0) {
						for (int j = 0; j < vendorlist.size(); j++) {
							logger.info(vendorlist.get(j)
									.getVendorPhoneNumber());
							logger.info((vendorlist.get(j).getFirmName()));
							vendor = new Vendor();
							vendor.setVendorPhoneNumber(vendorlist.get(j)
									.getVendorPhoneNumber());
							vendor.setCity(vendorlist.get(j).getCityName());
							vendor.setCountry(vendorlist.get(j).getI0028()
									.getName());
							vendor.setFirmName(""
									+ new BigDecimal(vendorlist.get(j)
											.getFirmName()));
							vendor.setEmail_ID_vendor(vendorlist.get(j)
									.getEmail_ID_vendor());
							ven1.add(vendor);
						}
						RequestContext.getCurrentInstance().execute(
								"PF('globalblocksUI').hide();");
						if (ven1.size() > 0) {
							setVendorFlag(true);
						} else {
							setVendorFlag(false);
						}
					} else {
						setVendorFlag(false);
					}

					customerlist = controller.getBuyercustInfo(b,
							purchaseorder.getGolbalnamesearch());
					RequestContext.getCurrentInstance().execute(
							"PF('globalblocksUI').hide();");
					customerList1 = new ArrayList<Buyer>();
					if (customerlist.size() > 0) {
						for (int i = 0; i < customerlist.size(); i++) {
							b = new Buyer();
							b.setCustomerName(customerlist.get(i)
									.getCustomerName());
							b.setPhoneNumber(""
									+ new BigDecimal(customerlist.get(i)
											.getPhoneNumber()));
							b.setCity(customerlist.get(i).getCity());
							b.setCountry(customerlist.get(i).getCountry());
							b.setCategoryName(customerlist.get(i)
									.getCategoryName());
							b.setMail(customerlist.get(i).getEMail());
							customerList1.add(b);
						}

						if (customerList1.size() > 0) {
							setCustomerFlag(true);
						} else {
							setCustomerFlag(false);
						}
					} else {
						setCustomerFlag(false);
					}
					emplist = controller.employeeNameInfo(employee,
							purchaseorder.getGolbalnamesearch());
					RequestContext.getCurrentInstance().execute(
							"PF('globalblocksUI').hide();");
					employeeList = new ArrayList<EmployeeDetail>();
					if (emplist.size() > 0) {
						for (int i = 0; i < emplist.size(); i++) {
							employee = new EmployeeDetail();
							employee.setName(emplist.get(i).getEmployeeName());
							employee.setPhno(emplist.get(i).getPhoneNumber());
							employee.setEmployeeid(emplist.get(i)
									.getEmployeeId());
							employee.setRegid(emplist.get(i)
									.getRegistrationId());
							employee.setMailid(emplist.get(i).getEmailId());
							employee.setDesignation(emplist.get(i)
									.getDesignation().getType());
							employeeList.add(employee);
						}
						if (employeeList.size() > 0) {
							setEmployeeFlag(true);
						} else {
							setEmployeeFlag(false);
						}
					} else {
						setEmployeeFlag(false);
					}
				}

			}

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	public List<PurchaseOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<PurchaseOrder> orderList) {
		this.orderList = orderList;
	}

	public List<PurchaseOrder> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<PurchaseOrder> filterList) {
		this.filterList = filterList;
	}

// Do check if this method is using or not 
	public void doSomething() {
		try {
			// simulate a long running request
			Thread.sleep(1200);
		} catch (Exception e) {
			// ignore
		}
	}

	public String businesscustomer() {
		logger.info("----------------------------- Inside businesscustomer() method ----------------------------- ");
		purchaseorder.setGolbalnamesearch("");
		//vendorRegisterFormMB.loadDialogBox();
		try {

			//vendorViewFormMB.success();
			buyersViewMB.setUserType(loginaccess.getUserType());
			buyersViewMB.buyerSearch1();
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "customerTables";

	}
	
	public String product() {
		logger.info("----------------------------- Inside product() method ----------------------------- ");
		productRegisterFormMB.setProductFlag(false);
		productViewMB.setUserType(loginaccess.getUserType());
		purchaseorder.setGolbalnamesearch("");
		//productViewMB.success();
		productViewMB.setMessage(null);
		productViewMB.likeProductView();
		return "product";

	}

	public String clientView() {
		logger.info("----------------------------- Inside clientView() method ----------------------------- ");
	//	RequestContext.getCurrentInstance().execute("PF('userCreateDialog').hide();");
		clientMB.clientDataBean.setClientLists(null);	
		clientMB.clientView();
		return "clientViews";
	}
	
	public String userView() {
		logger.info("----------------------------- Inside userView() method ----------------------------- ");
		RequestContext.getCurrentInstance().execute("PF('userUpdate').hide();");
		userCreateMB.userCreateDataBean.setUserlist(null);
		userCreateMB.userView();
		return "userViews";
	}

	//It is neat and clearn manner
	public String productReg() {
		logger.info("----------------------------- Inside productReg() method ----------------------------- ");
		productRegisterFormMB.dropDown();
		productRegisterFormMB.productRegisterDirect();
		return "productregistrationform4";

	}
	
	@PostConstruct
	public void Init() {
		logger.info("----------------------------- Inside Init() method ----------------------------- ");
		String userIDses = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("User");
		if (userIDses != null) {
			model = null;
			userLogin();
		} else {
		}
	}
	
	public String goHome() {
		logger.info("----------------------------- Inside goHome() method ----------------------------- ");
		try{
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.getdashboardCount(loginaccess);
			setPurchaseCount(loginaccess.getPurchaseCount());
			setSalesCount(loginaccess.getSalesCount());
			setStockCount(loginaccess.getStockCount());
			setProductCount(loginaccess.getProductCount());
			setCustomerCount(loginaccess.getCustomerCount());
			setVendorCount(loginaccess.getVendorCount());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "dashboard";
	}
	public String letmeinClick(){
		logger.info("----------------------------- Inside goHome() method ----------------------------- ");
		setValidate(null);
		setInvusername("");
		setInvpassword("");
		return "";
	}

	List<LoginAccess> approvalConsoleList=null;
	
	public List<LoginAccess> getApprovalConsoleList() {
		return approvalConsoleList;
	}

	public void setApprovalConsoleList(List<LoginAccess> approvalConsoleList) {
		this.approvalConsoleList = approvalConsoleList;
	}

	public String approvalPage(){
		DemoController controller=null;
		String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
		try{
		 	ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		 	controller = (DemoController) ctx.getBean("controller");
		 	approvalConsoleList=new ArrayList<LoginAccess>();
		 	approvalConsoleList=controller.getapprovalCountList(clientID);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "approvalConsolePage";
	}
	
	public String approvalView(){
		try{
			if (menuName.equalsIgnoreCase("Vendor")) {
				vendorViewFormMB.setApproval("ApprovalData");
				vendorViewFormMB.setUserType(loginaccess.getUserType());
				vendorViewFormMB.success();
				vendorViewFormMB.setApproval("");
			}else if (menuName.equalsIgnoreCase("Customer")) {
				buyersViewMB.setApproval("ApprovalData");
				buyersViewMB.setUserType(loginaccess.getUserType());
				buyersViewMB.buyerSearch1();
				buyersViewMB.setApproval("");
			}else if (menuName.equalsIgnoreCase("Purchase")) {
				purchaseViewMB.setApproval("ApprovalData");
				purchaseViewMB.setUserType(loginaccess.getUserType());
				purchaseViewMB.purchaseView();
				purchaseViewMB.setApproval("");
			}else if (menuName.equalsIgnoreCase("Sales")) {
				salesViewMB.setApproval("ApprovalData");
				salesViewMB.setUserType(loginaccess.getUserType());
				salesViewMB.salesView();
				salesViewMB.setApproval("");
			}else if (menuName.equalsIgnoreCase("Quick Sales")) {
				quickSaleViewMB.setApproval("ApprovalData");
				quickSaleViewMB.setUserType(loginaccess.getUserType());
				quickSaleViewMB.quicksaleview();
				quickSaleViewMB.setApproval("");
			}else if (menuName.equalsIgnoreCase("Employee")) {
				employeeDetailsViewFormMB.setApproval("ApprovalData");
				employeeDetailsViewFormMB.setStatus("empView");
				employeeDetailsViewFormMB.setUserType(loginaccess.getUserType());
				employeeDetailsViewFormMB.view();
				employeeDetailsViewFormMB.setApproval("");
			}else if (menuName.equalsIgnoreCase("Payroll")) {
				employeePayrollFormMB.employeePayrollRe();			
			}else if (menuName.equalsIgnoreCase("Category")) {
				productViewMB.setApprovalStatus("ApprovalData");
				productViewMB.categoryView();
				productViewMB.setApprovalStatus("");
			}else if (menuName.equalsIgnoreCase("Product")) {
				productViewMB.setApprovalStatus("ApprovalData");
				productRegisterFormMB.setProductFlag(false);
				productViewMB.setUserType(loginaccess.getUserType());
				productViewMB.setMessage(null);
				productViewMB.likeProductView();
				productViewMB.setApprovalStatus("");			
			}else if (menuName.equalsIgnoreCase("Quotation")) {
				System.out.println("-------inside Quotation-------");
				vendorRegisterFormMB.setApprovalStatus("ApprovalData");
				vendorRegisterFormMB.quotationConsolePage();
				vendorRegisterFormMB.setApprovalStatus("");
			}
			else if (menuName.equalsIgnoreCase("Sales Quote")) {
				System.out.println("-------inside Sales Quote-------");
				salesOrderFormMB.setApprovalStatus("ApprovalData");
				salesOrderFormMB.getSalesQuoteView();
				salesOrderFormMB.setApprovalStatus("");
			}
			
		}catch(Exception e){
			e.printStackTrace(); //wrong , it is only for develpoment if live change to logger.debug or logger.error
		}
		//fianlly missing
		return "";
	}
	

	public List<LoginAccess> globalsearchValue(String query) {
		System.out.println("-------------inside globalsearchValue calling---------------------");
		List<LoginAccess> filteredList = null;DemoController controller=null;
		loginaccess.setGlobalValue("");
		try{
			String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			filteredList=new ArrayList<LoginAccess>();loginaccess.setSearchList(new ArrayList<LoginAccess>());
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		 	controller = (DemoController) ctx.getBean("controller");
		 	loginaccess.setSearchList(controller.getglobalsearchList(clientID,userID));
			for (int i = 0; i < loginaccess.searchList.size(); i++) {
				 LoginAccess login=loginaccess.searchList.get(i);
				 if(login.getModulevalue().toLowerCase().startsWith(query)|| login.getModulevalue().startsWith(query)) {
					 filteredList.add(login);
		         }
			 }
			 if(filteredList.size()==0){
				 LoginAccess login=new LoginAccess();
				 login.setModulevalue("---No Data found---");
				 filteredList.add(login);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			controller=null;
		}
        return filteredList;
    }
	
	public void selectListener(SelectEvent event){
		System.out.println("-------------inside autocomplete SelectEvent listener calling---------------------");
		loginaccess.setGlobalValue("");
		try{
			Object value=event.getObject();
			loginaccess.setGlobalValue(value.toString());
			goGlobalSearch();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String goGlobalSearch(){
		System.out.println("----------------inside goGlobalSearch method calling-----------------");
		try{
			loginaccess.setModulevalue(loginaccess.getGlobalValue().split("/")[0]);loginaccess.setModulename(loginaccess.getGlobalValue().split("/")[1]);loginaccess.setModuleid(loginaccess.getGlobalValue().split("/")[2]);
			loginaccess.setApprovalStatus(loginaccess.getGlobalValue().split("/")[3]);
			if(!loginaccess.getModulevalue().equalsIgnoreCase("")){
				int moduleid=Integer.parseInt(loginaccess.getModuleid());
				if(loginaccess.getModulename().equalsIgnoreCase("Vendor")){
					vendorViewFormMB.setVendor_Id(moduleid);
					vendorViewFormMB.vendorUpdate();
					FacesContext.getCurrentInstance().getExternalContext().redirect("/inacsys/pages/xhtml/globalSearchVendorPage.xhtml");
				}else if(loginaccess.getModulename().equalsIgnoreCase("Customer")){
					buyersViewMB.setBuyer_ID(moduleid);
					buyersViewMB.modifyLoad();
					FacesContext.getCurrentInstance().getExternalContext().redirect("/inacsys/pages/xhtml/globalSearchCustomerPage.xhtml");
				}else if(loginaccess.getModulename().equalsIgnoreCase("Employee")){
					employeeDetailsViewFormMB.setEmployeeDetailsId(moduleid);
					employeeDetailsViewFormMB.edit();
					FacesContext.getCurrentInstance().getExternalContext().redirect("/inacsys/pages/xhtml/globalSearchEmployeePage.xhtml");
				}else if(loginaccess.getModulename().equalsIgnoreCase("Product")){
					productViewMB.setProduct_ID(moduleid);
					productViewMB.productEdit();
					FacesContext.getCurrentInstance().getExternalContext().redirect("/inacsys/pages/xhtml/globalSearchProductPage.xhtml");
				}else if(loginaccess.getModulename().equalsIgnoreCase("Purchase")){
					purchaseViewMB.setPurchaseid(moduleid);
					purchaseViewMB.editpurchase1();
					FacesContext.getCurrentInstance().getExternalContext().redirect("/inacsys/pages/xhtml/globalSearchPurchasePage.xhtml");
				}  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
}
