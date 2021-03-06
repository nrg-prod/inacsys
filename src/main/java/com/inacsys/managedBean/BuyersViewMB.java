package com.inacsys.managedBean;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.Buyer;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.Employee;
import com.inacsys.shared.I0028;
import com.inacsys.shared.I0032;
import com.inacsys.util.CommonValidate;
import com.inacsys.util.Util;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "buyersViewMB")
public class BuyersViewMB {
	public String phoneNumber;
	public List<I0032> phoneInfo;
	public Date salesorderdate;
	public Date getSalesorderdate() {
		return salesorderdate;
	}

	public void setSalesorderdate(Date salesorderdate) {
		this.salesorderdate = salesorderdate;
	}

	public String customerName;
	public Date date;
	public String phonenumber;
	public String address;
	public String city;
	public String state;
	public String perstate;
	public String attachmentfile1;
	public int buyer_ID;
	public String userType;
	public String approvalStatus;
	private boolean customerCheck=false;
	private String approveButtonFlag="none";
	public UploadedFile nomineePhoto;
	
	public UploadedFile getNomineePhoto() {
		return nomineePhoto;
	}

	public void setNomineePhoto(UploadedFile nomineePhoto) {
		this.nomineePhoto = nomineePhoto;
	}
	
	public int getBuyer_ID() {
		return buyer_ID;
	}

	public void setBuyer_ID(int buyer_ID) {
		this.buyer_ID = buyer_ID;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public boolean isCustomerCheck() {
		return customerCheck;
	}

	public void setCustomerCheck(boolean customerCheck) {
		this.customerCheck = customerCheck;
	}

	public String getApproveButtonFlag() {
		return approveButtonFlag;
	}

	public void setApproveButtonFlag(String approveButtonFlag) {
		this.approveButtonFlag = approveButtonFlag;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAttachmentfile1() {
		return attachmentfile1;
	}

	public void setAttachmentfile1(String attachmentfile1) {
		this.attachmentfile1 = attachmentfile1;
	}

	public void setAttachmentFile(UploadedFile attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	private UploadedFile attachmentFile;
	private StreamedContent stream;
	public StreamedContent getStream() {
		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}

	SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
	
	private UploadedFile photoUploadFile;
	public String attachmentFile1;
	private boolean sameCheckBox=false;
	private boolean hiddenFlag=false;
	private boolean showFlag=true;

	public boolean isSameCheckBox() {
		return sameCheckBox;
	}

	public void setSameCheckBox(boolean sameCheckBox) {
		this.sameCheckBox = sameCheckBox;
	}

	public boolean isHiddenFlag() {
		return hiddenFlag;
	}

	public void setHiddenFlag(boolean hiddenFlag) {
		this.hiddenFlag = hiddenFlag;
	}

	public boolean isShowFlag() {
		return showFlag;
	}

	public void setShowFlag(boolean showFlag) {
		this.showFlag = showFlag;
	}

	public String getAttachmentFile1() {
		return attachmentFile1;
	}

	public void setAttachmentFile1(String attachmentFile1) {
		this.attachmentFile1 = attachmentFile1;
	}

	public UploadedFile getPhotoUploadFile() {
		return photoUploadFile;
	}

	public void setPhotoUploadFile(UploadedFile photoUploadFile) {
		this.photoUploadFile = photoUploadFile;
	}

	
	public String getPerstate() {
		return perstate;
	}

	public void setPerstate(String perstate) {
		this.perstate = perstate;
	}

	public String country;
	public String phoneno;
	public String mail;
	public String note;
	public String flag = "none";
	public String flag2 = "none";
	public Date deliveryDate;
	public List<I0028> countrys;
	public String taxnumber;
	public String validate;
	public List<I0032> buyer;
	public List<Employee> name = null;
	public String cities;
	public String customerTitle;
	public String customerMiddleName;
	public String image;
	public String code;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String customerLastName;
	public String customerSuffix;
	public String custMobile;
	public String other;
	public String company;
	public String website;
	public String faxnumber;
	public String displayName;
	public String permanentaddress;
	public String presentCity;
	public String perPostCode;
	public String prePostCode;
	public String presentstate;
	public String presentcountry;
	private boolean newFlag=false;
	private String amount;
	private List<Buyer> memberPaymentList=null;
	private int paymentID;
	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public List<Buyer> getMemberPaymentList() {
		return memberPaymentList;
	}

	public void setMemberPaymentList(List<Buyer> memberPaymentList) {
		this.memberPaymentList = memberPaymentList;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public boolean isNewFlag() {
		return newFlag;
	}

	public void setNewFlag(boolean newFlag) {
		this.newFlag = newFlag;
	}

	public String getPresentcountry() {
		return presentcountry;
	}

	public void setPresentcountry(String presentcountry) {
		this.presentcountry = presentcountry;
	}

	public String getCustomerTitle() {
		return customerTitle;
	}

	public void setCustomerTitle(String customerTitle) {
		this.customerTitle = customerTitle;
	}

	public String getCustomerMiddleName() {
		return customerMiddleName;
	}

	public void setCustomerMiddleName(String customerMiddleName) {
		this.customerMiddleName = customerMiddleName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerSuffix() {
		return customerSuffix;
	}

	public void setCustomerSuffix(String customerSuffix) {
		this.customerSuffix = customerSuffix;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFaxnumber() {
		return faxnumber;
	}

	public void setFaxnumber(String faxnumber) {
		this.faxnumber = faxnumber;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPermanentaddress() {
		return permanentaddress;
	}

	public void setPermanentaddress(String permanentaddress) {
		this.permanentaddress = permanentaddress;
	}

	public String getPresentCity() {
		return presentCity;
	}

	public void setPresentCity(String presentCity) {
		this.presentCity = presentCity;
	}

	public String getPerPostCode() {
		return perPostCode;
	}

	public void setPerPostCode(String perPostCode) {
		this.perPostCode = perPostCode;
	}

	public String getPrePostCode() {
		return prePostCode;
	}

	public void setPrePostCode(String prePostCode) {
		this.prePostCode = prePostCode;
	}

	public String getPresentstate() {
		return presentstate;
	}

	public void setPresentstate(String presentstate) {
		this.presentstate = presentstate;
	}

	public DemoController getController() {
		return controller;
	}

	public void setController(DemoController controller) {
		this.controller = controller;
	}

	public String getCities() {
		return cities;
	}

	public void setCities(String cities) {
		this.cities = cities;
	}

	public List<Employee> getName() {
		return name;
	}

	public void setName(List<Employee> name) {
		this.name = name;
	}

	DemoController controller = null;

	public List<I0032> getBuyer() {
		return buyer;
	}

	public void setBuyer(List<I0032> buyer) {
		this.buyer = buyer;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getTaxnumber() {
		return taxnumber;
	}

	public void setTaxnumber(String taxnumber) {
		this.taxnumber = taxnumber;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<I0028> getCountrys() {
		return countrys;
	}

	public void setCountrys(List<I0028> countrys) {
		this.countrys = countrys;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<I0032> getPhoneInfo() {
		return phoneInfo;
	}

	public void setPhoneInfo(List<I0032> phoneInfo) {
		this.phoneInfo = phoneInfo;
	}

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	Buyer b = new Buyer();
	private static Logger logger = Logger.getLogger(BuyersViewMB.class);

	public String buyersMenuLoad() {
		logger.info("[buyersMenuLoad()] --------------- Inside buyersMenuLoad() method() ------------------------");
		try {
			phoneNumber = null;
			phoneInfo = null;
			return "redirectBuyer";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		} finally {
			controller =null;
		}
	}

	List<String> names1;

	public List<String> getNames1() {
		return names1;
	}

	public void setNames1(List<String> names1) {
		this.names1 = names1;
	}

	public String vendorsredir() {
		try {
			
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			
		}
		return "customerReg";
	}
	public String vendors() {
		logger.info("[vendors()] --------------- Inside vendors() method() ------------------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setNames1(controller.getnames1(names1));
			Set<String> duplicate = new HashSet<String>();
			customerNameList = controller.getCustomerInfo(b);
			logger.debug("customername List size" + customerNameList.size());
			duplicate.addAll(names1);
			names1.clear();
			names1.addAll(duplicate);
			Collections.sort(names1, String.CASE_INSENSITIVE_ORDER);
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		}finally {
			controller =null;
		}
	}

	public String redirectBuyersView() {
		logger.info("[redirectBuyersView()] --------------- Inside redirectBuyersView() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			buys.clear();
			setValidate("");
			freelancerName = "";
			customerName = "";
			cities = "";
			flag = "none";
			flag1 = "none";
			flag2 = "none";
			flag3 = "none";
			logger.debug("[redirectBuyersView()] --------------- phone number ------------------------>"+phoneNumber);
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			// prema begin 26/04/2016 customer name dropbox
			customerNameList = controller.getCustomerInfo(b);
			logger.debug("[redirectBuyersView()] --------------- customername List size ------------------------>"+customerNameList.size());
			logger.debug("customername List size" + customerNameList.size());
			// prema end 26/04/2016
			b.setPhoneNumber(phoneNumber);
			b.setPhoneInfo(phoneInfo);
			if (phoneInfo.size() > 0) {
				for (int i = 0; i < phoneInfo.size(); i++) {
					BuyersViewMB buyers = new BuyersViewMB();
					buyers.setCustomerName(phoneInfo.get(i).getCustomerName());
					buyers.setPhns(new BigDecimal(""
							+ phoneInfo.get(i).getPhoneNumber()));
					buyers.setCountry(phoneInfo.get(i).getCountry());
					buyers.setFreelancerName(phoneInfo.get(i)
							.getFreelancerName());
					buys.add(buyers);
					logger.debug("2");
				}
				flag = "none";
				flag1 = "none";
				flag2 = "none";
				flag3 = "none";
			} else if (phoneInfo.size() == 0) {
				flag = "none";
				flag1 = "none";
				flag2 = "none";
				flag3 = "none";
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			phoneNumber = "";
			customerName = "";
			freelancerName = "";
			city = "";

		}

		return "";
	}

	@ManagedProperty(value = "#{salesOrderFormMB}")
	SalesOrderFormMB salesOrderFormMB;

	public SalesOrderFormMB getSalesOrderFormMB() {
		return salesOrderFormMB;
	}

	public void setSalesOrderFormMB(SalesOrderFormMB salesOrderFormMB) {
		this.salesOrderFormMB = salesOrderFormMB;
	}
	public List<String> stateList=null;
	public List<String> stateList1=null;
	public List<String> getStateList1() {
		return stateList1;
	}

	public void setStateList1(List<String> stateList1) {
		this.stateList1 = stateList1;
	}

	public List<String> getStateList() {
		return stateList;
	}

	public void setStateList(List<String> stateList) {
		this.stateList = stateList;
	}
	public String postCode;
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String photoPath;
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	
Date now=new Date();
	public static String isEmailValid(String email) {
		logger.info("[direct1()] --------------- Inside isEmailValid() method() ------------------------");
		try {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			CharSequence inputStr = email;
			// Make the comparison case-insensitive.
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inputStr);
			if (matcher.matches()) {
				return "Match";
			} else {
				return "NotMatch";
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "NotMatch";
		} finally {
			
		}

	}

	public String attachmentfile;
	
	
	
 
  public String getPhonenumber() {
	return phonenumber;
}

public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}

public UploadedFile getAttachmentFile() {
	return attachmentFile;
}

private void copyFile1(Date date, String fileName, InputStream inputstream, String n) {
	logger.info("[copyFile1()] --------------- Inside copyFile1() method() ------------------------");
		try {
			// Create Directory
			File files = new File("/home/ec2-user/File_Inacsys/Customer/PDF/"+ ft.format(now));
			if (!files.exists()) {
				files.mkdirs();
			} else {
				logger.debug("Alreday Found");
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
			
			} catch (IOException e) {
				e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}

UploadedFile file;
private void copyFile(Date date, String fileName, InputStream inputstream, String n) {
	logger.info("[copyFile1()] --------------- Inside copyFile() method() ------------------------");
	try {
		// Create Directory
		File files = new File(Util.getMessage("PhotoPath", "messages")+ ft.format(now));
		if (!files.exists()) {
			files.mkdirs();
		} else {
			logger.debug("Alreday Found");
			System.out.println("Alreday Found");
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
		} catch (IOException e) {
			e.printStackTrace();
		logger.debug(e.getMessage());
	}

}
  public void dummyAction(FileUploadEvent event) throws IOException {
		this.photoUploadFile = event.getFile();
	}

  public void dummyAction1(FileUploadEvent event) throws IOException {
		this.attachmentFile = event.getFile();
	}
  
  public void dummyAction2(FileUploadEvent event) throws IOException {
		this.nomineePhoto = event.getFile();
	}
	ArrayList<BuyersViewMB> buys = new ArrayList<BuyersViewMB>();
	BigDecimal phns = BigDecimal.valueOf(0);

	public ArrayList<BuyersViewMB> getBuys() {
		return buys;
	}

	public void setBuys(ArrayList<BuyersViewMB> buys) {
		this.buys = buys;
	}

	public BigDecimal getPhns() {
		return phns;
	}

	public void setPhns(BigDecimal phns) {
		this.phns = phns;
	}

	public String freelancerSearch() {
		logger.info("[freelancerSearch()] --------------- Inside freelancerSearch() method() ------------------------");
		DemoController controller = null;
		ApplicationContext ctx = null;
		try {
			buys.clear();
			setValidate("");
			cities = "";
			customerName = "";
			if (freelancerName.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the  FreeLancer Name");
			}

			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setPhoneInfo(controller.getFreeLancerInfo(freelancerName));
			if (phoneInfo.size() > 0) {
				flag = "1";
				flag1 = "none";
				flag3 = "none";
				flag2 = "none";
				for (int i = 0; i < phoneInfo.size(); i++) {
					BuyersViewMB buyers = new BuyersViewMB();
					buyers.setCustomerName(phoneInfo.get(i).getCustomerName());
					buyers.setPhns(new BigDecimal(phoneInfo.get(i)
							.getPhoneNumber()));
					buyers.setCountry(phoneInfo.get(i).getCountry());
					buyers.setFreelancerName(phoneInfo.get(i)
							.getFreelancerName());
					buys.add(buyers);
				}
			}
			if (phoneInfo.size() == 0) {
				logger.debug("Back to Bean with Zero-------------->");
				logger.debug("Size" + phoneInfo.size());
				flag = "none";
				flag1 = "none";
				flag3 = "none";
				flag2 = "1";

			}
			return "redirectBuyer";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			setValidate(e.getMessage());
			return "";
		} finally {

		}

	}

	public String buyerSearch() {
		logger.info("[buyerSearch()] --------------- Inside buyerSearch() method() ------------------------");
		DemoController controller = null;
		ApplicationContext ctx = null;
		try {
			setValidate("");
			if (phoneNumber.equalsIgnoreCase("")) {
				throw new Exception("Enter the phone number");
			}
			flag = "1";
			flag1 = "none";
			flag2 = "none";
			logger.debug("phone--------->" + phoneNumber);
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setPhoneInfo(controller.getBuyerInfo(phoneNumber));
			b.setPhoneNumber(phoneNumber);
			b.setPhoneInfo(phoneInfo);
			for (int i = 0; i < phoneInfo.size(); i++) {
				logger.debug("customer name---------->"
						+ phoneInfo.get(i).getCustomerName());
			}
			if (phoneInfo.size() == 0) {
				logger.debug("Back to Bean with Zero-------------->");
				logger.debug("Size" + phoneInfo.size());
				flag2 = "1";
				flag1 = "none";
				flag = "none";

			}

			return "redirectBuyer";
		} catch (DemoException e) {
			logger.error("log:" + e.getMessage());
			return "";
		} catch (Exception e) {
			setValidate(e.getMessage());
			return "";
		} finally {

		}
	}

	
	public void checkBoxValueChange(ValueChangeEvent vc){
		logger.info("[checkBoxValueChange()] --------------- Inside checkBoxValueChange() method() ------------------------");
		try{
			String check=vc.getNewValue().toString();
			if(check.equals("true")){
				setHiddenFlag(true);
				setShowFlag(false);
				setPermanentaddress(permanentaddress);
				setCity(presentCity);
				setPerPostCode(prePostCode);
				setState(presentstate);
			}else{
				setHiddenFlag(false);
				setShowFlag(true);
				setPermanentaddress("");
				setCity("");
				setPerPostCode("");
				setState("");
			}
		}catch(Exception e){
			logger.error("log:" + e.getMessage());
		}
	}
	public String buyerDelete() {
		logger.info("[buyerDelete()] --------------- Inside buyerDelete() method() ------------------------");
		DemoController controller = null;
		ApplicationContext ctx = null;
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			Buyer b = new Buyer();
		System.out.println("--------------" + phoneno);
			b.setPhoneNumber(String.valueOf(buyer_ID));
			controller.buyerDelete(b);
			//setPhoneInfo(controller.getBuyerInfo(String.valueOf(buyer_ID)));
			RequestContext.getCurrentInstance().execute("PF('cd1su').show();");
		} catch (Exception e) {
			logger.error("log:" + e.getMessage());
		} finally {
			phoneNumber = null;
			customerName = null;
		}
		return "";
	}

	public String viewBack() {
		logger.info("[viewBack()] --------------- Inside viewBack() method() ------------------------");
		DemoController controller = null;
		ApplicationContext ctx = null;
		try {
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setPhoneInfo(controller.getBuyerInfo(""));
			return "buyersView";
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return "";
		} finally {
			customerName = "";
			phoneNumber = "";
		}
	}

	/* jeni */

	public String flag1 = "none";
	public List<I0032> customerInfo;

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public List<I0032> getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(List<I0032> customerInfo) {
		this.customerInfo = customerInfo;
	}

	ArrayList<BuyersViewMB> catbuy = new ArrayList<BuyersViewMB>();
	ArrayList<BuyersViewMB> filterValue;

	public ArrayList<BuyersViewMB> getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(ArrayList<BuyersViewMB> filterValue) {
		this.filterValue = filterValue;
	}

	BigDecimal phonenum = BigDecimal.valueOf(0);

	public ArrayList<BuyersViewMB> getCatbuy() {
		return catbuy;
	}

	public void setCatbuy(ArrayList<BuyersViewMB> catbuy) {
		this.catbuy = catbuy;
	}

	public BigDecimal getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(BigDecimal phonenum) {
		this.phonenum = phonenum;
	}

	ArrayList<BuyersViewMB> cities1 = new ArrayList<BuyersViewMB>();

	public ArrayList<BuyersViewMB> getCities1() {
		return cities1;
	}

	public void setCities1(ArrayList<BuyersViewMB> cities1) {
		this.cities1 = cities1;
	}

	public List<I0032> cityinfo;

	public List<I0032> getCityinfo() {
		return cityinfo;
	}

	public void setCityinfo(List<I0032> cityinfo) {
		this.cityinfo = cityinfo;
	}

	Buyer bc = new Buyer();

	public Buyer getBc() {
		return bc;
	}

	public void setBc(Buyer bc) {
		this.bc = bc;
	}

	public String flag3;

	public String getFlag3() {
		return flag3;
	}

	public void setFlag3(String flag3) {
		this.flag3 = flag3;
	}

	public String citysearch() {
		logger.info("[citysearch()] --------------- Inside citysearch() method() ------------------------");
		cities1.clear();
		setValidate("");
		freelancerName = "";
		customerName = "";
		DemoController controller = null;
		ApplicationContext ctx = null;
		try {
			if (cities.equalsIgnoreCase("")) {
				throw new Exception("Please Select the City");
			}
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setCityinfo(controller.getcitiesinfo(cities));
			bc.setCity(cities);
			bc.setCityinfo(cityinfo);
			if (cityinfo.size() > 0) {
				flag3 = "1";
				flag = "none";
				flag1 = "none";
				flag2 = "none";
				for (int i = 0; i < cityinfo.size(); i++) {
					BuyersViewMB citys = new BuyersViewMB();
					citys.setCustomerName(cityinfo.get(i).getCustomerName());
					citys.setPhns(new BigDecimal(""
							+ cityinfo.get(i).getPhoneNumber()));
					citys.setCity(cityinfo.get(i).getCity());
					citys.setCountry(cityinfo.get(i).getCountry());
					citys.setCategoryName(cityinfo.get(i).getCategoryName());
					citys.setFreelancerName(cityinfo.get(i).getFreelancerName());
					cities1.add(citys);
				}
			} else if (cityinfo.size() == 0) {
				flag3 = "none";
				flag = "none";
				flag1 = "none";
				flag2 = "1";
			}
			return "";
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
		}

		return "";
	}

	List<String> customerNameList = null;

	public List<String> getCustomerNameList() {
		return customerNameList;
	}

	public void setCustomerNameList(List<String> customerNameList) {
		this.customerNameList = customerNameList;
	}
	public String approval;
	
	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	ArrayList<BuyersViewMB> filterList;

	public ArrayList<BuyersViewMB> getFilterList() {
		return filterList;
	}

	public void setFilterList(ArrayList<BuyersViewMB> filterList) {
		this.filterList = filterList;
	}

	public String cancel() {
		logger.info("[cancel()] --------------- Inside cancel() method() ------------------------");
		flag = "none";
		flag1 = "none";
		flag2 = "none";
		customerName = null;
		phoneNumber = null;
		return "buyersView";
	}

	public void cancelModify() {
		logger.info("[cancelModify()] --------------- Inside cancelModify() method() ------------------------");
		flag = "none";
		flag1 = "none";
		flag2 = "none";
		customerName = null;
		phoneNumber = null;
		RequestContext.getCurrentInstance().closeDialog(
				"modifyBuyerRegistration");

	}

	public String deleteback() {
		logger.info("[deleteback()] --------------- Inside deleteback() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			phoneNumber = "";
			flag = "none";
			flag1 = "none";
			flag2 = "none";
			logger.debug("phone number-------->" + phoneNumber);
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setPhoneInfo(controller.getBuyerInfo(phoneNumber));
			logger.debug("1");
			b.setPhoneNumber(phoneNumber);
			b.setPhoneInfo(phoneInfo);
			if (phoneInfo.size() > 0) {
				for (int i = 0; i < phoneInfo.size(); i++) {
					logger.debug("2");
					logger.debug("customer name------------------>"
							+ phoneInfo.get(i).getCustomerName());
				}
				flag = "1";
				flag1 = "none";
				flag2 = "none";
			} else if (phoneInfo.size() == 0) {
				flag = "none";
				flag1 = "none";
				flag2 = "1";
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {
			customerName = null;
			phoneNumber = null;
		}
		return "customersViewpage";

	}

	public String categoryName;
	public String freelancerName;
	public String flagFree = "none";
	public String customFlag = "none";

	public String getCustomFlag() {
		return customFlag;
	}

	public void setCustomFlag(String customFlag) {
		this.customFlag = customFlag;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getFreelancerName() {
		return freelancerName;
	}

	public void setFreelancerName(String freelancerName) {
		this.freelancerName = freelancerName;
	}

	public String getFlagFree() {
		return flagFree;
	}

	public void setFlagFree(String flagFree) {
		this.flagFree = flagFree;
	}

	public void categoryChange(ValueChangeEvent v) throws DemoException {
		logger.info("[categoryChange()] --------------- Inside categoryChange() method() ------------------------");
		try {
			ApplicationContext ctx = null;
			DemoController controller = null;
			if (v.getNewValue().toString().equalsIgnoreCase("Free Lancer")) {
				flagFree = "1";
				ctx = FacesContextUtils.getWebApplicationContext(FacesContext
						.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				setName(controller.freelancerNameInfo(name));
				customFlag = "none";
			} else {
				flagFree = "none";
				freelancerName = " ";
				customFlag = "1";
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		} finally {

		}
	}

	/* jency */
	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public BuyersViewMB() {
		logger.info("[BuyersViewMB()] --------------- Inside BuyersViewMB() method() ------------------------");
		sortsOrders = new HashMap<String, SortOrder>();
		sortPriorities = new ArrayList<String>();
	}

	public void sort() {
		logger.info("[sort()] --------------- Inside sort() method() ------------------------");
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
				sortsOrders.put(property, SortOrder.ASCENDING);
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

	// prema 29/04/2015 dialog box creation for customer view

	public List<I0032> customerlist = null;

	public List<I0032> getCustomerlist() {
		return customerlist;
	}

	public void setCustomerlist(List<I0032> customerlist) {
		this.customerlist = customerlist;
	}

	// prema begin 02/05/2016 customer dialog box close
	public void customerviewclose() {
		RequestContext.getCurrentInstance().closeDialog("buyersView");
	}

	// prema end 02/05/2016

	public void buyerView() {
		logger.debug("inside buyerview");

		buyerViewLoad();

	}

	public void custview() {
		logger.info("[custview()] --------------- Inside custview() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 180);
		options.put("contentWidth", 435);
		RequestContext.getCurrentInstance().openDialog("viewBuyer", options,
				null);
		buyerViewLoad();
	}

	public void custmodify() {
		logger.info("[custmodify()] --------------- Inside custmodify() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 320);
		options.put("contentWidth", 650);
		RequestContext.getCurrentInstance().openDialog(
				"modifyBuyerRegistration", options, null);
		modifyLoad();

	}

	public void customermodifyclose() {
		RequestContext.getCurrentInstance().closeDialog(
				"modifyBuyerRegistration");
	}

	public String custrefresh() {
		logger.info("[custrefresh()] --------------- Inside custrefresh() method() ------------------------");
		buyerSearch1();
		modifyLoad();
		vendors();
		setValidate(null);
		return "";
	}
	public void onrowselect(final SelectEvent event){
		logger.info("[onrowselect()] --------------- Inside onrowselect() method() ------------------------");
		phoneno = ((BuyersViewMB) event.getObject()).getPhoneno();
		modifyLoad();
	}
	public void imageview(OutputStream out, Object data) throws IOException {
		logger.info("[imageview()] --------------- Inside imageview() method() ------------------------");
		  String s =Util.getMessage("PhotoPath", "messages");
		  try{  
		   System.out.println(s + photoPath);
		   if(photoPath=="" || photoPath.equals("")){
		   }else{
		   BufferedImage img = ImageIO
		     .read(new File(s + photoPath));
		   ImageIO.write(img, "png", out);
		   }
		  }catch(Exception e){
		   e.printStackTrace();
		  }
		  
		 }
	public void imageview1(OutputStream out, Object data) throws IOException {
		logger.info("[imageview()] --------------- Inside imageview() method() ------------------------");
		String s =Util.getMessage("PhotoPath", "messages");
		  try{  
		   System.out.println(s + attachmentfile1);
		   if(attachmentfile1=="" || attachmentfile1.equals("")){
		   }else{
		   BufferedImage img = ImageIO
		     .read(new File(s + attachmentfile1));
		   ImageIO.write(img, "png", out);
		   }
		  }catch(Exception e){
		   e.printStackTrace();
		  }
		 }
	
	public void imageview2(OutputStream out, Object data) throws IOException {
		logger.info("[imageview()] --------------- Inside imageview() method() ------------------------");
		String s =Util.getMessage("PhotoPath", "messages");
		  try{  
		   System.out.println(s + freelancerName);
		   if(freelancerName=="" || freelancerName.equals("")){
		   }else{
		   BufferedImage img = ImageIO
		     .read(new File(s + freelancerName));
		   ImageIO.write(img, "png", out);
		   }
		  }catch(Exception e){
		   e.printStackTrace();
		  }
		  
		 }
	
	public void counSelectValueChange(ValueChangeEvent vc){
		logger.info("[counSelectValueChange()] --------------- Inside counSelectValueChange() method() ------------------------");
		String country = "";
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
		   country = vc.getNewValue().toString();
		   ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		   controller = (DemoController) ctx.getBean("controller");
		   stateList=new ArrayList<String>();
		   stateList=controller.getstatelist(country);
		   System.out.println("stateist "+stateList.size());
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	public void counSelectValueChange1(ValueChangeEvent vc){
		logger.info("[counSelectValueChange1()] --------------- Inside counSelectValueChange1() method() ------------------------");
		String country = "";setCode("");
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			country = vc.getNewValue().toString();
		   ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		   controller = (DemoController) ctx.getBean("controller");
		   stateList1=new ArrayList<String>();
		   stateList1=controller.getstatelist(country);
		   System.out.println("stateist "+stateList1.size());
		   if(country.equalsIgnoreCase("India")) setCode("+91");
		   else if(country.equalsIgnoreCase("Indonesia")) setCode("+62");
		   else if(country.equalsIgnoreCase("Malesia")) setCode("+60");
		   else if(country.equalsIgnoreCase("Singapore")) setCode("+65");
		   else if(country.equalsIgnoreCase("UAE")) setCode("+971");
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	public void file() throws FileNotFoundException {
		logger.info("[file()] --------------- Inside file() method() ------------------------");
	     try{
	      System.out.println("file === "+attachmentFile1);
	      InputStream input=null;
	      File file=new File("/home/ec2-user/File_Inacsys/Customer/PDF/"+attachmentFile1);
	      System.out.println("files -- "+file);
		   input = new FileInputStream(file);
		   System.out.println("input "+input);
		   ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		   this.setStream(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
	     }
	     catch(Exception e){
	    	 e.printStackTrace();
	     }
	    }
	
	    //Customer approval
		public String customerApproval(){
			logger.info("[customerApproval()] --------------- Inside customerApproval() method() ------------------------");
			String status="";DemoController controller = null;
			int count=0;setValidate("");String approvalStatus="";
			try{
				ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				for (int i = 0; i < catbuy.size(); i++) {
					if(catbuy.get(i).isCustomerCheck()==true){
						count++;
					}
				}
				if(count==0){
					throw new Exception("Please Choose atleast one row for Approval.");
				}else{
					setValidate("");
					approvalStatus="Approve";
					status=controller.customerApproval(catbuy,approvalStatus);
					if(status.equalsIgnoreCase("Success")){
						RequestContext.getCurrentInstance().execute("PF('approvalConfirm').show()");
					}
				}
			}catch(Exception e){
				setValidate(e.getMessage());
				logger.warn("Inside Exception"+e);
			}finally{
			}
			return "";
		}
		
		//Customer Reject
		public String customerReject(){
			logger.info("[customerReject()] --------------- Inside customerReject() method() ------------------------");
			String status="";DemoController controller = null;
			int count=0;setValidate("");String approvalStatus="";
			try{
				ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				for (int i = 0; i < catbuy.size(); i++) {
					if(catbuy.get(i).isCustomerCheck()==true){
						count++;
					}
				}
				if(count==0){
					throw new Exception("Please Choose atleast one row for Reject.");
				}else{
					setValidate("");
					approvalStatus="Reject";
					status=controller.customerApproval(catbuy,approvalStatus);
					if(status.equalsIgnoreCase("Success")){
						RequestContext.getCurrentInstance().execute("PF('rejectConfirm').show()");
					}
				}
			}catch(Exception e){
				setValidate(e.getMessage());
				logger.warn("Inside Exception"+e);
			}finally{
			}
			return "";
		}
		
		public String cuslicNo;
		public String custype;
		public Date cusexDate;
		public String cusCode;
		public String paytype;
		public String paydays;
		public boolean venflag=true; 
		public String newcash;
		public List<String> daylist=null;
		
		public List<String> getDaylist() {
			return daylist;
		}

		public void setDaylist(List<String> daylist) {
			this.daylist = daylist;
		}

		public String getNewcash() {
			return newcash;
		}

		public void setNewcash(String newcash) {
			this.newcash = newcash;
		}

		public boolean isVenflag() {
			return venflag;
		}

		public void setVenflag(boolean venflag) {
			this.venflag = venflag;
		}

		public String getPaytype() {
			return paytype;
		}

		public void setPaytype(String paytype) {
			this.paytype = paytype;
		}

		public String getPaydays() {
			return paydays;
		}

		public void setPaydays(String paydays) {
			this.paydays = paydays;
		}

		public String getCuslicNo() {
			return cuslicNo;
		}

		public void setCuslicNo(String cuslicNo) {
			this.cuslicNo = cuslicNo;
		}

		public String getCustype() {
			return custype;
		}

		public void setCustype(String custype) {
			this.custype = custype;
		}

		public Date getCusexDate() {
			return cusexDate;
		}

		public void setCusexDate(Date cusexDate) {
			this.cusexDate = cusexDate;
		}

		public String getCusCode() {
			return cusCode;
		}

		public void setCusCode(String cusCode) {
			this.cusCode = cusCode;
		}
		
		public String buyerViewLoad() {
			logger.info("[buyerViewLoad()] --------------- Inside buyerViewLoad() method() ------------------------");
			DemoController controller = null;
			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
			ApplicationContext ctx = null;
			try {
				String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
				String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
				ctx = FacesContextUtils.getWebApplicationContext(FacesContext
						.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				setPhoneInfo(controller.getBuyerInfo1(String.valueOf(buyer_ID),clientID,userID));
				if (phoneInfo.size() > 0) {
					if(clientID.equals(Util.getMessage("SIOCOA", "messages"))){
						setNewFlag(true);
						setCustomerName(phoneInfo.get(0).getCustomerName());
						setCustomerMiddleName(phoneInfo.get(0).getCustomerMiddleName());
						setCustomerLastName(phoneInfo.get(0).getCustomerLastName());
						setMail(phoneInfo.get(0).getEMail());
						setCustomerTitle(phoneInfo.get(0).getCustomerTitle());
						setDate(phoneInfo.get(0).getSalesOrderDate());
						setDeliveryDate(phoneInfo.get(0).getDeliveryDate());
						setPhoneno(phoneInfo.get(0).getPhoneNumber());
						setOther(phoneInfo.get(0).getOther());
						setPaytype(phoneInfo.get(0).getPaymentType()); 
						setCusCode(phoneInfo.get(0).getCustomerCode()); 
						setAddress(phoneInfo.get(0).getAddress());
						setPermanentaddress(phoneInfo.get(0).getShipingAddress());
						setCity(phoneInfo.get(0).getCity());
						setCities(phoneInfo.get(0).getPresentCity());
						setCountry(phoneInfo.get(0).getCountry());
						setPresentcountry(phoneInfo.get(0).getPresentCountry());
						setPrePostCode(phoneInfo.get(0).getPresentPostCode());
						setPostCode(phoneInfo.get(0).getPermanentPostCode());
						setState(phoneInfo.get(0).getState());
						setPerstate(phoneInfo.get(0).getPresentState());
						setPhotoPath(phoneInfo.get(0).getPhotoUpload());
						setFreelancerName(phoneInfo.get(0).getFreelancerName());
						setAttachmentfile1(phoneInfo.get(0).getAttachmentFile());
						
						setDateofjoin(phoneInfo.get(0).getDateOfJoin());
						
						setPartnerShipName(phoneInfo.get(0).getPnName());
						setPartnerFnamerelation(phoneInfo.get(0).getPnFatherRelation());
						setPnDOB(phoneInfo.get(0).getPnDOB());
						setPnEmailID(phoneInfo.get(0).getPnEmailID());
						setPnPhoneNumber1(phoneInfo.get(0).getPnPhoneNumber1());
						setPnPhoneNumber2(phoneInfo.get(0).getPnPhoneNumber2());
						setPnAddress(phoneInfo.get(0).getPnAddress());
						
					}else{
						setNewFlag(false);
						setCode(phoneInfo.get(0).getPhoneCode());
						setCountry(phoneInfo.get(0).getCountry());
						setMail(phoneInfo.get(0).getEMail());
						setCustMobile(phoneInfo.get(0).getCustomerMobile());
						setPhoneno(phoneInfo.get(0).getPhoneNumber());
						setCategoryName(phoneInfo.get(0).getCategoryName());
						setOther(phoneInfo.get(0).getOther());
						setDate(phoneInfo.get(0).getSalesOrderDate());
						setCompany(phoneInfo.get(0).getCompany());
						setWebsite(phoneInfo.get(0).getWebsite());
						setTaxnumber(phoneInfo.get(0).getTaxNumber());
						setFaxnumber("" + phoneInfo.get(0).getCustomerFaxNumber());
						setDisplayName(phoneInfo.get(0).getDisplayNameAs());
						setAddress(phoneInfo.get(0).getAddress());
						setPermanentaddress(phoneInfo.get(0).getShipingAddress());
						setCity(phoneInfo.get(0).getCity());
						System.out.println("city"+getCity());
						setCities(phoneInfo.get(0).getPresentCity());
						System.out.println("cityww"+getPresentCity());
						setCountry(phoneInfo.get(0).getCountry());
						setPresentcountry(phoneInfo.get(0).getPresentCountry());
						setPrePostCode(phoneInfo.get(0).getPresentPostCode());
						setPostCode(phoneInfo.get(0).getPermanentPostCode());
						setState(phoneInfo.get(0).getState());
						setPerstate(phoneInfo.get(0).getPresentState());
						setNote(phoneInfo.get(0).getNote());	
						setCustomerTitle(phoneInfo.get(0).getCustomerTitle());
						setCustomerName(phoneInfo.get(0).getCustomerName());
						setCustomerMiddleName(phoneInfo.get(0).getCustomerMiddleName());
						setAttachmentFile1(phoneInfo.get(0).getAttachmentFile());
						setCustomerSuffix(phoneInfo.get(0).getCustomerSuffix());
					    setPhotoPath(phoneInfo.get(0).getPhotoUpload());
						setFreelancerName(phoneInfo.get(0).getFreelancerName());
						setCuslicNo(phoneInfo.get(0).getCustomerLicenseNumber()); 
						setCusexDate(phoneInfo.get(0).getCustomerExpireDate()); 
						setCusCode(phoneInfo.get(0).getCustomerCode()); 
						setCustype(phoneInfo.get(0).getCustomerType()); 
						setPaytype(phoneInfo.get(0).getPaymentType()); 
						if("Credit".equalsIgnoreCase(getPaytype())){
							setPaydays(phoneInfo.get(0).getPaymentCash_ID().getPaymentType());  
							daylist=controller.getpaytype();
						}
					}
					RequestContext.getCurrentInstance().update("center_content");
				}
				return "viewBuyer";
			} catch (Exception e) {
				logger.error("Inside Exception", e);
				return "";
			} finally {
			}
		}
		
		public String modifyLoad() {
			logger.info("[modifyLoad()] --------------- Inside modifyLoad() method() ------------------------");
			ApplicationContext ctx = null;
			DemoController controller = null;
			try {
				String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
				String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
				flag = "1";
				flag1 = "none";
				logger.debug("Customer phno" + phoneno);
				logger.debug("phone number------->" + phoneno);
				ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				setCountrys(controller.getCountry());
				setPhoneInfo(controller.getBuyerInfo1(String.valueOf(buyer_ID),clientID,userID));
				if(phoneInfo.size()>0){
				if(clientID.equals(Util.getMessage("SIOCOA", "messages"))){
					setNewFlag(true);
					setCustomerName(phoneInfo.get(0).getCustomerName());
					setCustomerMiddleName(phoneInfo.get(0).getCustomerMiddleName());
					setCustomerLastName(phoneInfo.get(0).getCustomerLastName());
					setMail(phoneInfo.get(0).getEMail());
					setCustomerTitle(phoneInfo.get(0).getCustomerTitle());
					setDate(phoneInfo.get(0).getSalesOrderDate());
					setDeliveryDate(phoneInfo.get(0).getDeliveryDate());
					setPhoneno(phoneInfo.get(0).getPhoneNumber());
					setOther(phoneInfo.get(0).getOther());
					setPaytype(phoneInfo.get(0).getPaymentType()); 
					setCusCode(phoneInfo.get(0).getCustomerCode()); 
					setAddress(phoneInfo.get(0).getAddress());
					setPermanentaddress(phoneInfo.get(0).getShipingAddress());
					setCity(phoneInfo.get(0).getCity());
					setCities(phoneInfo.get(0).getPresentCity());
					setCountry(phoneInfo.get(0).getCountry());
					setPresentcountry(phoneInfo.get(0).getPresentCountry());
					setPrePostCode(phoneInfo.get(0).getPresentPostCode());
					setPostCode(phoneInfo.get(0).getPermanentPostCode());
					setState(phoneInfo.get(0).getState());
					setPerstate(phoneInfo.get(0).getPresentState());
					setPhotoPath(phoneInfo.get(0).getPhotoUpload());
					setFreelancerName(phoneInfo.get(0).getFreelancerName());
					setAttachmentfile1(phoneInfo.get(0).getAttachmentFile());
					
					setDateofjoin(phoneInfo.get(0).getDateOfJoin());
					
					setPartnerShipName(phoneInfo.get(0).getPnName());
					setPartnerFnamerelation(phoneInfo.get(0).getPnFatherRelation());
					setPnDOB(phoneInfo.get(0).getPnDOB());
					setPnEmailID(phoneInfo.get(0).getPnEmailID());
					setPnPhoneNumber1(phoneInfo.get(0).getPnPhoneNumber1());
					setPnPhoneNumber2(phoneInfo.get(0).getPnPhoneNumber2());
					setPnAddress(phoneInfo.get(0).getPnAddress());
					
					stateList1=new ArrayList<String>();
			 		stateList1=controller.getstatelist(phoneInfo.get(0).getPresentCountry());
			 		stateList=new ArrayList<String>();
					stateList=controller.getstatelist(phoneInfo.get(0).getCountry());
					RequestContext.getCurrentInstance().update("center_content");
				}else{
					setNewFlag(false);
					setCustomerTitle(phoneInfo.get(0).getCustomerTitle());
					setPhonenumber(phoneInfo.get(0).getPhoneNumber());
					setCustomerName(phoneInfo.get(0).getCustomerName());
					setCustomerMiddleName(phoneInfo.get(0).getCustomerMiddleName());
					setCustomerSuffix(phoneInfo.get(0).getCustomerSuffix());
					setCode(phoneInfo.get(0).getPhoneCode());
					setMail(phoneInfo.get(0).getEMail());
					setCustMobile(phoneInfo.get(0).getCustomerMobile());
					setPhoneno(phoneInfo.get(0).getPhoneNumber());
					setCategoryName(phoneInfo.get(0).getCategoryName());
					setOther(phoneInfo.get(0).getOther());
					setDate(phoneInfo.get(0).getSalesOrderDate());
					setCompany(phoneInfo.get(0).getCompany());
					setWebsite(phoneInfo.get(0).getWebsite());
					setTaxnumber(phoneInfo.get(0).getTaxNumber());
					setFaxnumber("" + phoneInfo.get(0).getCustomerFaxNumber());
					setDisplayName(phoneInfo.get(0).getDisplayNameAs());
					setAddress(phoneInfo.get(0).getAddress());
					setPermanentaddress(phoneInfo.get(0).getShipingAddress());
					setCity(phoneInfo.get(0).getCity());
					setCities(phoneInfo.get(0).getPresentCity());
					setCountry(phoneInfo.get(0).getCountry());
					setPresentcountry(phoneInfo.get(0).getPresentCountry());
					setPrePostCode(phoneInfo.get(0).getPresentPostCode());
					setPostCode(phoneInfo.get(0).getPermanentPostCode());
					setState(phoneInfo.get(0).getState());
					setPerstate(phoneInfo.get(0).getPresentState());
					setNote(phoneInfo.get(0).getNote());
					setCuslicNo(phoneInfo.get(0).getCustomerLicenseNumber()); 
					setCusexDate(phoneInfo.get(0).getCustomerExpireDate()); 
					setCusCode(phoneInfo.get(0).getCustomerCode()); 
					setCustype(phoneInfo.get(0).getCustomerType()); 			
					logger.debug("Name" + getFreelancerName());
		            setAttachmentFile1(phoneInfo.get(0).getAttachmentFile());
		            setPhotoPath(phoneInfo.get(0).getPhotoUpload());
		           stateList1=new ArrayList<String>();
		 		   stateList1=controller.getstatelist(phoneInfo.get(0).getPresentCountry());
		 		   stateList=new ArrayList<String>();
				   stateList=controller.getstatelist(phoneInfo.get(0).getCountry());
				   setPaytype(phoneInfo.get(0).getPaymentType()); 
					if("Credit".equalsIgnoreCase(getPaytype())){
						setPaydays(phoneInfo.get(0).getPaymentCash_ID().getPaymentType());  
						daylist=controller.getpaytype();
					}
					}
					if (categoryName.equalsIgnoreCase("Free Lancer")) {
						flagFree = "1";
						customFlag = "none";
	
					} else {
						flagFree = "none";
						customFlag = "1";
					}
				}
				return "";
			} catch (Exception e) {
				logger.error("Inside Exception", e);
				return "";
			} finally {
			}

		}
		
		
		 public String buyerUpdate() throws IOException {
			 logger.info("[buyerUpdate()] --------------- Inside buyerUpdate() method() ------------------------");
			  ApplicationContext ctx = null;
			  DemoController controller = null;
			  validate = "";
			  String clientID="",userID="";
			  try {
				  clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
				  userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
				  ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				  controller = (DemoController) ctx.getBean("controller");
				  if(clientID.equals(Util.getMessage("SIOCOA", "messages"))){
					  if(memberValidation(true)){
						  b.setClientID(clientID);
						  b.setUserID(userID);
						  b.setCustomerName(customerName);
						  b.setCustomerMiddleName(customerMiddleName);
						  b.setCustomerLastName(customerLastName);
						  b.setBuyerID(buyer_ID);
						  b.setCustomerTitle(customerTitle);
						  b.setDate(date);
						  b.setPhoneNumber(phoneno);
						  b.setMail(mail);
						  b.setDeliveryDate(deliveryDate);
						  b.setType(paytype);
						  b.setOther(other);
						  b.setCusCode(cusCode);
						  b.setAddress(address);
						  b.setPermanentaddress(permanentaddress);
						  b.setCity(city);
						  b.setPresentCity(cities);
						  b.setCountry(country);
						  b.setPresentcountry(presentcountry);
						  b.setPerpostcode(prePostCode);
						  b.setPerPostCode(postCode);
						  b.setState(state);
						  b.setPresentstate(perstate);
						  
						  b.setDateofjoin(dateofjoin);
						  
						  b.setPartnerShipName(partnerShipName);
						  b.setPartnerFnamerelation(partnerFnamerelation);
						  b.setPnDOB(pnDOB);
						  b.setPnEmailID(pnEmailID);
						  b.setPnPhoneNumber1(pnPhoneNumber1);
						  b.setPnPhoneNumber2(pnPhoneNumber2);
						  b.setPnAddress(pnAddress);
						  
						  if(photoUploadFile==null || photoUploadFile.equals(null)){
								b.setPhotouploadfile("");
							}else{
								String s=getPhotoUploadFile().getContentType();
								String type = s.substring(s.lastIndexOf("/") + 1);
								copyFile(new Date(), b.getCustomerName(),getPhotoUploadFile().getInputstream(),type);
								b.setPhotouploadfile(ft.format(new Date()) + "/" +b.getCustomerName()+ "." + type);
							}
							if(attachmentFile==null || attachmentFile.equals(null)){
								b.setAttachFilePath("");
							}else{
								String s=getAttachmentFile().getContentType();
								String type = s.substring(s.lastIndexOf("/") + 1);
								copyFile(new Date(), b.getCustomerMiddleName(),getAttachmentFile().getInputstream(),type);
								b.setAttachFilePath(ft.format(new Date()) + "/" +b.getCustomerMiddleName()+ "." + type);
							}
							if(nomineePhoto==null || nomineePhoto.equals(null)){
								b.setFilePath("");
							}else{
								String s=getNomineePhoto().getContentType();
								String type = s.substring(s.lastIndexOf("/") + 1);
								copyFile(new Date(), b.getCustomerTitle(),getNomineePhoto().getInputstream(),type);
								b.setFilePath(ft.format(new Date()) + "/" +b.getCustomerTitle()+ "." + type);
							}
						  controller.getBuyerUpdate(b);
						  if("Success".equalsIgnoreCase(b.getStatus())){
							  RequestContext.getCurrentInstance().execute("PF('cuc').show();");
						  }
					  }
				  }else{
				   if (customerName.equalsIgnoreCase("")) {
					    throw new DemoException("Enter Customer Name");
				   }
				   if(mail.equalsIgnoreCase("")){
					   throw new DemoException("Enter Email ID");
				   }else if(!mail.equals("")){
					   if(!mail.matches("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$")){
						   throw new DemoException("Please Enter the Valid Email ID(Eg:aa@bb.cc)");
					   }
				   } /*if(!custMobile.equals("")){
					   if(!custMobile.matches("^\\d+(\\.\\d+)*$")){
						   throw new DemoException("Phone Number with in 13 digit Numbers");
					   }
				   } */if(categoryName.equals("select")){
					   throw new DemoException("Select the Category");
				   } /*if(date==null){
					   throw new DemoException("Select the Date");
				   } if (cities.equalsIgnoreCase("")) {
					    throw new DemoException("Please Enter the City");
				   }else if (cities != null || !cities.equalsIgnoreCase("")) {
					    if (!cities.matches("^[a-zA-Z ]{1,30}$")) {
					    	throw new DemoException("City Should be in Alphabet");
					    }
				   }*/ if (presentcountry.equalsIgnoreCase("select")) {
					    throw new DemoException("Please Select the Country");
				   } if (categoryName.equalsIgnoreCase("select")) {
					    throw new DemoException("Please Enter the Catergory Name");
				   }/*if(!taxnumber.equals("")){
				    	if(!taxnumber.matches("^\\d+(\\.\\d+)*$")){
				    		throw new DemoException("Please Enter valid Tax Number");
				    	}
				    } if(!faxnumber.equals("")){
				    	if(!faxnumber.matches("^\\d+(\\.\\d+)*$")){
				    		throw new DemoException("Please Enter valid Fax Number");
				    	}
				    } if(cuslicNo!=null){
				    	if(!cuslicNo.matches("^\\d+(\\.\\d+)*$")){
				    		throw new DemoException("Please Enter valid Licence Number");
				    	}
				    }if(paytype.equals("select")){
				    	throw new DemoException("Please Select Payment Type");
				    }else if(!paytype.equals("select")){
				    	if(paytype.equals("Credit")){
				    		System.out.println("days "+paydays);
				    		if(paydays.equals("select") || paydays.equals("Add new")){
				    			throw new DemoException("Please Select Credit Days");
				    		}			    		
				    	}			    	
				    } */
				    b.setClientID(clientID);
				    b.setUserID(userID);
				    b.setCustomerTitle(customerTitle);
					b.setCustomerName(customerName);
					b.setCustomerMiddleName(customerMiddleName);
					b.setCustomerLastName(customerLastName);
					b.setCustomerSuffix(customerSuffix);
					b.setMail(mail);
					b.setCode(code);
					b.setBuyerID(buyer_ID);
					b.setPhoneNumber(phoneno);
					b.setPhoneno(phonenumber);
					System.out.println("oooook"+b.getPhoneNumber());
					b.setCustMobile(custMobile);
					b.setCategoryName(categoryName);
					b.setOther(other);
					b.setDate(date);
					b.setCompany(company);
					b.setWebsite(website);
					b.setTaxnumber(taxnumber);
					System.out.println("taxnumber"+b.getTaxnumber());
					b.setFaxnumber(faxnumber);
					b.setDisplayName(displayName);
					b.setAddress(address);
					b.setPermanentaddress(permanentaddress);
					b.setCity(city);
					b.setPresentCity(cities);
					b.setCountry(country);
					b.setPresentcountry(presentcountry);
					b.setPerpostcode(prePostCode);
					b.setPerPostCode(postCode);
					b.setState(state);
					b.setPresentstate(perstate);
					b.setNote(note);
					b.setCusLicence(cuslicNo); 
					b.setCusType(custype);  
					b.setCusExdate(cusexDate); 
					b.setCusCode(cusCode); 
					b.setType(paytype);
					b.setCash(paydays);
					b.setFilePath("");
					if (b.getPhotoUploadFile() != null) {
						String s = b.getPhotoUploadFile().getContentType();
						logger.debug("file Type " + s);
						String type = s.substring(s.lastIndexOf("/") + 1);
						logger.debug(type);
	
						copyFile(b.getDate(), b.getPhoneNumber(),b.getPhotoUploadFile().getInputstream(),type);
	
						
						String path = ft.format(now) + "/" + b.getPhotoUploadFile() + "." + type;
						b.setPhotouploadfile(path);
						
					} else {
						b.setPhotouploadfile("");
						
					}
					String paths ="";
					if(attachmentFile==null || attachmentFile.equals("")){
						System.out.println("inside null");
					}else{
						String sss=getAttachmentFile().getContentType();
						System.out.println("transFile --- "+sss);
						String types = sss.substring(sss.lastIndexOf("/") + 1);
						System.out.println("transFile---  "+types);
						System.out.println("phone number"+b.getPhoneNumber()+"--"+phonenumber+"date"+b.getDate()+"--"+salesorderdate);
						copyFile1(b.getDate(), b.getPhoneNumber(),getAttachmentFile().getInputstream(),types);
						paths = ft.format(now) + "/" +b.getPhoneNumber()+ "." + types;
					}
					
					b.setAttachFilePath(paths);
				  System.out.println("website "+website);
				  controller.getBuyerUpdate(b);
				  if("Success".equalsIgnoreCase(b.getStatus())){
					  RequestContext.getCurrentInstance().execute("PF('cuc').show();");
				  }
				}
				return "";
			  } catch (DemoException e) {
			   logger.error("Inside Exception", e);
			   setValidate(e.getMessage());
			   return "";
			  } finally {

			  }

			 }
		 
		 private boolean memberValidation(boolean valid) {
			 valid=true;
				String name="";
				FacesContext fc=FacesContext.getCurrentInstance();
				try{
				if(StringUtils.isEmpty(customerName)){
					name=CommonValidate.findComponentInRoot("unitname").getClientId(fc);
					fc.addMessage(name, new FacesMessage(Util.getMessage("Unitname", "messages")));
					valid=false;
				}
				if(StringUtils.isEmpty(customerMiddleName)){
					name=CommonValidate.findComponentInRoot("ownername").getClientId(fc);
					fc.addMessage(name, new FacesMessage(Util.getMessage("Ownername", "messages")));
					valid=false;
				}
			/*	if(StringUtils.isEmpty(customerTitle)){
					name=CommonValidate.findComponentInRoot("nomineename").getClientId(fc);
					fc.addMessage(name, new FacesMessage(Util.getMessage("Nomineename", "messages")));
					valid=false;
				}*/
				}catch(Exception e){
				}
				return valid;
		}

		public void venTypes(ValueChangeEvent v){
			 logger.info("[venTypes()] --------------- Inside venTypes() method() ------------------------");
				String valueven="";	
				DemoController controller=null;
				try{
					 valueven=v.getNewValue().toString();
					if(valueven.equalsIgnoreCase("Cash")){
						setVenflag(false);
						
					}
					else{ 
					
						setVenflag(true);
						ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
						controller = (DemoController) ctx.getBean("controller");				
						daylist=controller.getpaytype();
						System.out.println("statelist1---->"+stateList.size()); 
					}
					setCity(valueven); 
					System.out.println("---------"+valueven);
				}catch(Exception e){
					logger.error("Inside Exception", e);
				}
				
			}
		
		 public void dialog(){
			 logger.info("[dialog()] --------------- Inside dialog() method() ------------------------");
				  try{
				    setValidate("");
				    setNewcash("");
				    RequestContext.getCurrentInstance().execute("PF('confirm1').show();"); 
				    RequestContext.getCurrentInstance().execute("PF('customerDialogModify').hide();"); 
				  }catch(Exception e){
					  logger.error("Inside Exception", e);
				  }
				 }
			public String validate1;

			public String getValidate1() {
				return validate1;
			}

			public void setValidate1(String validate1) {
				this.validate1 = validate1;
			}
			
			public String vendorRegistercash(){
				logger.info("[dialog()] --------------- Inside dialog() method() ------------------------");
				  DemoController controller=null;
				  String status="";
				  setValidate1(null);
				  try{
					  Buyer buyer=new Buyer();
				   ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				   controller = (DemoController) ctx.getBean("controller");
				   buyer.setNewcash(newcash+" days"); 
				   status=controller.setbuycash(buyer); 
				   	setPaydays(buyer.getNewcash());  
				     if("Success".equalsIgnoreCase(status)){
				     RequestContext.getCurrentInstance().execute("PF('confirm1').hide();");
				     daylist=controller.getpaytype();     
				    RequestContext.getCurrentInstance().execute("PF('customerDialogModify').show();");
				   }else if("exist".equalsIgnoreCase(status)){
					   setValidate1("Already Exist this Day");
				   }
				     RequestContext.getCurrentInstance().update("center_content:cusModify");
				  }catch(Exception e){
					  logger.error("Inside Exception", e);
				  }
				  return "";
				 }
			
			/*stanley changes*/
			public String serialno;
			
			public String getSerialno() {
				return serialno;
			}

			public void setSerialno(String serialno) {
				this.serialno = serialno;
			}
			public String partnerShipName;
			public String partnerFnamerelation;
			public Date pnDOB;
			public String pnEmailID;
			public String pnPhoneNumber1;
			public String pnPhoneNumber2;
			public String pnAddress;
			public Date dateofjoin;	
			
			
			public String getPartnerShipName() {
				return partnerShipName;
			}

			public void setPartnerShipName(String partnerShipName) {
				this.partnerShipName = partnerShipName;
			}

			public String getPartnerFnamerelation() {
				return partnerFnamerelation;
			}

			public void setPartnerFnamerelation(String partnerFnamerelation) {
				this.partnerFnamerelation = partnerFnamerelation;
			}

			public Date getPnDOB() {
				return pnDOB;
			}

			public void setPnDOB(Date pnDOB) {
				this.pnDOB = pnDOB;
			}

			public String getPnEmailID() {
				return pnEmailID;
			}

			public void setPnEmailID(String pnEmailID) {
				this.pnEmailID = pnEmailID;
			}

			public String getPnPhoneNumber1() {
				return pnPhoneNumber1;
			}

			public void setPnPhoneNumber1(String pnPhoneNumber1) {
				this.pnPhoneNumber1 = pnPhoneNumber1;
			}

			public String getPnPhoneNumber2() {
				return pnPhoneNumber2;
			}

			public void setPnPhoneNumber2(String pnPhoneNumber2) {
				this.pnPhoneNumber2 = pnPhoneNumber2;
			}

			public String getPnAddress() {
				return pnAddress;
			}

			public void setPnAddress(String pnAddress) {
				this.pnAddress = pnAddress;
			}

			public Date getDateofjoin() {
				return dateofjoin;
			}

			public void setDateofjoin(Date dateofjoin) {
				this.dateofjoin = dateofjoin;
			}

			public void partnerNomineeDetail(int rowindex) {
				System.out.println("rowindex---->"+rowindex);
				setPartnerShipName("");
				setPartnerFnamerelation("");
				setPnDOB(null);
				setPnEmailID("");
				setPnPhoneNumber1("");
				setPnPhoneNumber2("");
				setPnAddress("");
				setPaytype("");
				try {
					setPartnerShipName(customerlist.get(rowindex).getPnName());
					setPartnerFnamerelation(customerlist.get(rowindex).getPnFatherRelation());
					setPnDOB(customerlist.get(rowindex).getPnDOB());
					setPnEmailID(customerlist.get(rowindex).getPnEmailID());
					setPnAddress(customerlist.get(rowindex).getPnAddress());
					setPnPhoneNumber1(customerlist.get(rowindex).getPnPhoneNumber1());
					setPnPhoneNumber2(customerlist.get(rowindex).getPnPhoneNumber2());
					setPaytype(customerlist.get(rowindex).getPaymentType());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		
			public void memberIDLinkFormDetail(int rowindex) {
				System.out.println("memberIDLinkFormDetail-->rowindex---->"+rowindex);
				setCustomerName("");
				setCustomerMiddleName("");
				setCustomerLastName("");
				setDate(null);
				setAddress("");
				setPhoneno("");
				setDateofjoin(null);
				setPhotoPath("");
				try {
					setCustomerName(customerlist.get(rowindex).getCustomerName());
					setCustomerMiddleName(customerlist.get(rowindex).getCustomerMiddleName());
					setCustomerLastName(customerlist.get(rowindex).getCustomerLastName());
					setDate(customerlist.get(rowindex).getSalesOrderDate());
					setAddress(customerlist.get(rowindex).getShipingAddress());
					setPhoneno(customerlist.get(rowindex).getPhoneNumber());
					setDateofjoin(customerlist.get(rowindex).getDateOfJoin());
					setPhotoPath(customerlist.get(rowindex).getPhotoUpload());
					
					RequestContext.getCurrentInstance().update("center_content");
					
			} catch (Exception e) {
					e.printStackTrace();
			logger.debug(">>>>>>>>>" + e.getMessage());
				}
			}
			public String buyerSearch1() {
				logger.info("[buyerSearch1()] --------------- Inside buyerSearch1() method() ------------------------");
				DemoController controller = null;
				ApplicationContext ctx = null;
				try {
					setFilterList(null);
					catbuy.clear();
					setValidate("");
					flag = "none";
					flag1 = "none";int count=0;
					customerlist = null;
					b.setApproval(approval);
					ctx = FacesContextUtils.getWebApplicationContext(FacesContext
							.getCurrentInstance());
					controller = (DemoController) ctx.getBean("controller");
					String clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
					String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
					String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
					b.setClientID(clientID);
					b.setUserID(userID);
					b.setUserType(userType);
					if(clientID.equals(Util.getMessage("SIOCOA", "messages"))){
						setNewFlag(true);
					}else{
						setNewFlag(false);
					}
					customerlist = controller.getBuyercustInfo(b);
					if (customerlist.size() > 0) {
						for (int i = 0; i < customerlist.size(); i++) {
							BuyersViewMB cats = new BuyersViewMB();
							cats.setSerialno(String.valueOf(i+1));  
							cats.setCustomerName(customerlist.get(i).getCustomerName());
							cats.setPhoneno(customerlist.get(i).getPhoneNumber());
							cats.setCity(customerlist.get(i).getPresentCity());
							cats.setCountry(customerlist.get(i).getPresentCountry());
							if(clientID.equals(Util.getMessage("SIOCOA", "messages"))){
								cats.setCategoryName(customerlist.get(i).getCustomerMiddleName());
								cats.setCompany(customerlist.get(i).getCustomerTitle());
								cats.setCusCode(customerlist.get(i).getCustomerCode());
							}else{
								cats.setCategoryName(customerlist.get(i).getCategoryName());
								cats.setCompany(customerlist.get(i).getCompany());
							}
							cats.setFreelancerName(customerlist.get(i).getFreelancerName());
							cats.setMail(customerlist.get(i).getEMail());
							cats.setState(customerlist.get(i).getPresentState());
							cats.setPostCode(customerlist.get(i).getPresentPostCode());
							cats.setApprovalStatus(customerlist.get(i).getApprovalStatus());
							cats.setBuyer_ID(customerlist.get(i).getBuyer_ID());
							cats.setPartnerShipName(customerlist.get(i).getPnName());
							cats.setDateofjoin(customerlist.get(i).getDateOfJoin());
							catbuy.add(cats);
							/*if(customerlist.get(i).getUserID().getUserNo()==Integer.parseInt(userID)){
								catbuy.add(cats);
							}else{
								if(customerlist.get(i).getApprovalStatus().equals("draft")){
									catbuy.add(cats);
								}
							}*/
							if(customerlist.get(i).getApprovalStatus().equals("draft")){
								count++;
							}
						}
						if(count==0){
							approveButtonFlag="none";
						}else{
							approveButtonFlag="1";
						}
						flag = "1";
					} else if (customerlist.size() == 0) {
						flag1 = "1";

					}

				} catch (Exception e) {
					setValidate(e.getMessage());
					logger.debug(">>>>>>>>>" + e.getMessage());

				}
				return "";

			}
			
			public String paymentSave(){
				DemoController controller = null;
				ApplicationContext ctx = null;
				String clientID="",userID="",status="";
				boolean valid=true;
				String name="";
				FacesContext fc=FacesContext.getCurrentInstance();
				try{
					clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
 					userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
 					if(StringUtils.isEmpty(amount)){
 						name=CommonValidate.findComponentInRoot("payamount").getClientId(fc);
 						fc.addMessage(name, new FacesMessage("Please enter the amount"));
 						valid=false;
 					}else if(!StringUtils.isEmpty(amount)){
 						if(!CommonValidate.validateNumber(amount)){
 							name=CommonValidate.findComponentInRoot("payamount").getClientId(fc);
 	 						fc.addMessage(name, new FacesMessage("Please enter the valid amount"));
 	 						valid=false;
 						}
 					}
 					if(valid==true){
	 					b.setClientID(clientID);
	 					b.setUserID(userID);
	 					b.setCash(amount);
	 					b.setNote(note);
	 					b.setBuyerID(buyer_ID);
	 					b.setCusCode(cusCode);
	 					ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
						controller = (DemoController) ctx.getBean("controller");
						status=controller.paymentSave(b);
						if("Success".equalsIgnoreCase(status)){
							memberPayment();
						}
 					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					controller = null;
					ctx = null;
				}
				return"";
			}
			
		public void memberPayment(){
			DemoController controller = null;
			ApplicationContext ctx = null;
			String clientID="",userID="";
			try{
				clientID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ClientID");
				userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
				b.setClientID(clientID);
				b.setUserID(userID);
				b.setBuyerID(buyer_ID);
				b.setCusCode(cusCode);
				ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				setAmount("");
				setNote("");
				memberPaymentList=new ArrayList<Buyer>();
				memberPaymentList=controller.getmemberPayment(b);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public void mamberPaymentUpdate(){
			DemoController controller = null;
			ApplicationContext ctx = null;
			String status;
			boolean valid=true;
			String name="";
			FacesContext fc=FacesContext.getCurrentInstance();
			try{
				if(StringUtils.isEmpty(amount)){
						name=CommonValidate.findComponentInRoot("epayamount").getClientId(fc);
						fc.addMessage(name, new FacesMessage("Please enter the amount"));
						valid=false;
					}else if(!StringUtils.isEmpty(amount)){
						if(!CommonValidate.validateNumber(amount)){
							name=CommonValidate.findComponentInRoot("epayamount").getClientId(fc);
	 						fc.addMessage(name, new FacesMessage("Please enter the valid amount"));
	 						valid=false;
						}
					}
					if(valid==true){
						ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
						controller = (DemoController) ctx.getBean("controller");
						b.setCash(amount);
						b.setNote(note);
						b.setPaymentID(paymentID);
						status=controller.mamberPaymentUpdate(b);
						if("Success".equalsIgnoreCase(status)){
							RequestContext.getCurrentInstance().execute("PF('paymentEditDialog').hide()");
							memberPayment();
						}
					}
			}catch(Exception e){
				
			}
		}
		
		public void memberPaymentDelete(){
			DemoController controller = null;
			ApplicationContext ctx = null;
			String status;
			try{
				ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				b.setPaymentID(paymentID);
				status=controller.mamberPaymentDelete(b);
				if("Success".equalsIgnoreCase(status)){
					memberPayment();
				}
			}catch(Exception e){
			}
		}
}