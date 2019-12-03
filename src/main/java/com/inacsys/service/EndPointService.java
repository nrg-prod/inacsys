package com.inacsys.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.inacsys.domain.Buyer;
import com.inacsys.domain.LoginAccess;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.domain.Vendor;
import com.inacsys.domain.VendorDelete;
import com.inacsys.managedBean.CashBookMB;
import com.inacsys.shared.I0025;
import com.inacsys.shared.I0032;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@XmlRootElement
@Path("/endPoint/service")
public class EndPointService {
	
	@Autowired
	DemoService service=(DemoService)SpringApplicationContext.getBean("service");
	LoginAccess loginAccess=new LoginAccess();
	Vendor vendor=new Vendor();
	Buyer buyer=new Buyer();
	PurchaseOrder purchaseOrder=new PurchaseOrder();
	SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");	
	private static Logger logger = Logger.getLogger(EndPointService.class);
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(String jsonValue)throws JSONException
	{
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonValue);
			
		String userName=element.getAsJsonObject().get("user").getAsString();
		String password=element.getAsJsonObject().get("pasword").getAsString();
		List<String> menuList=new ArrayList<String>();
		try{
			loginAccess.setUsername(userName);
			loginAccess.setUserpassword(password);
			String status=service.databaseValidate(loginAccess);
			loginAccess.setLoginStatus(status);
			if(loginAccess.getUser_Product().size()>0){
				for(int i=0;i<loginAccess.getUser_Product().size();i++){
					String productName=loginAccess.getUser_Product().get(i).getProduct().getProductName();
					menuList.add(productName);
				}
			}
			menuList.add("SIGN OUT");
			loginAccess.setUserMenulist(menuList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(loginAccess);	
	}
	
	@GET
	@Path("/vendorCode/{clientID}/{userID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String vendorCode(@PathParam ("clientID") String clientID,@PathParam ("userID") String userID){
		List<String> dayList=new ArrayList<String>();
		Gson gson = new Gson();
		String venCode="";
		if(clientID!=null)
		try{
			venCode=service.getvencode(clientID,userID);
			dayList=service.getpaytype();
			vendor.setVenCode(venCode);
			vendor.setStringList(dayList);
		}catch(Exception e){
			
		}
		return gson.toJson(vendor);
	}
	
	@GET
	@Path("/countryChange/{countryName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String countryChange(@PathParam("countryName") String countryName){
		List<String> stateList=null;
		Gson gson = new Gson();
		try{
			stateList=new ArrayList<String>();
			stateList=service.getstatelist(countryName);
		}catch(Exception e){
			
		}
		return gson.toJson(stateList);
	}
	
	@POST
	@Path("/newdaySave/{newDay}")
	@Produces(MediaType.APPLICATION_JSON)
	public String newdaySave(@PathParam("newDay") String newDay){
		String status="";
		Gson gson = new Gson();
		List<String> dayList=new ArrayList<String>();
		try{
			vendor.setNewcash(newDay); 
			status=service.setcash(vendor);
			dayList=service.getpaytype();
			vendor.setStatus(status);
			vendor.setStringList(dayList);
		}catch(Exception e){
			
		}
		return gson.toJson(vendor);
	}
	
	@POST
	@Path("/vendorRegister")
	@Produces(MediaType.APPLICATION_JSON)
	public String vendorRegister(String jsonValue){
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonValue);
		String clientID=element.getAsJsonObject().get("clientID").getAsString();
		String userID=element.getAsJsonObject().get("userID").getAsString();
		String firmName=element.getAsJsonObject().get("firmName").getAsString();
		String faxNumber=element.getAsJsonObject().get("faxNumber").getAsString();
		String expiryDate=element.getAsJsonObject().get("expiryDate").getAsString();
		String webSite=element.getAsJsonObject().get("website").getAsString();
		String licenceNumber=element.getAsJsonObject().get("licenceNumber").getAsString();
		String business=element.getAsJsonObject().get("business").getAsString();
		String personIncharge=element.getAsJsonObject().get("personIncharge").getAsString();
		String emailId=element.getAsJsonObject().get("emailId").getAsString();
		String phoneNumber=element.getAsJsonObject().get("phoneNumber").getAsString();
		String company=element.getAsJsonObject().get("company").getAsString();
		String vendorType=element.getAsJsonObject().get("vendorType").getAsString();
		String vendorCode=element.getAsJsonObject().get("vendorCode").getAsString();
		String paymetType=element.getAsJsonObject().get("paymentType").getAsString();
		String paymentDays=element.getAsJsonObject().get("paymentDays").getAsString();
		String street=element.getAsJsonObject().get("street").getAsString();
		String city=element.getAsJsonObject().get("city").getAsString();
		String country=element.getAsJsonObject().get("country").getAsString();
		String postcode=element.getAsJsonObject().get("postCode").getAsString();
		String state=element.getAsJsonObject().get("state").getAsString();
		String street1=element.getAsJsonObject().get("street1").getAsString();
		String city1=element.getAsJsonObject().get("city1").getAsString();
		String country1=element.getAsJsonObject().get("country1").getAsString();
		String postcode1=element.getAsJsonObject().get("postCode1").getAsString();
		String state1=element.getAsJsonObject().get("state1").getAsString();
		String note=element.getAsJsonObject().get("note").getAsString();
		String status="";
		try{
			if(clientID!=null || clientID!=""){
				vendor.setFirmName(phoneNumber);
				vendor.setFaxVendor(faxNumber);
				if(expiryDate==null || expiryDate.equals("")){
					vendor.setVenExdate(null);
				}else{
					vendor.setVenExdate(format.parse(expiryDate));
				}
				vendor.setWebsite(webSite);
				vendor.setVenLicence(licenceNumber);
				vendor.setNatureofbusiness(business);
				vendor.setPeresonIncharge(personIncharge);
				vendor.setEmail_ID_vendor(emailId);
				vendor.setVendorPhoneNumber(firmName);
				vendor.setVenCompany(company);
				vendor.setVenType(vendorType);
				vendor.setVenCode(vendorCode);
				vendor.setPayment(paymetType);
				vendor.setCash(paymentDays);
				vendor.setNote(note);
				vendor.setAddress(street);
				vendor.setVenCity(city);
				vendor.setVenCountry(country);
				vendor.setVenPostalcode(postcode);
				vendor.setVenState(state);
				vendor.setVenAddress1(street1);
				vendor.setVenCity1(city1);
				vendor.setVenCountry1(country1);
				vendor.setVenPostalcode1(postcode1);
				vendor.setVenState1(state1);
				vendor.setClientID(clientID);
				vendor.setUserID(userID);
				status = service.vendorService(vendor);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(status);
	}
	
	@POST
	@Path("/vendorConsole")
	@Produces(MediaType.APPLICATION_JSON)
	public String vendorConsole(String jsonValue){
		List<I0025> vendorList=new ArrayList<I0025>();
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonValue);
		String clientID=element.getAsJsonObject().get("clientID").getAsString();
		String userID=element.getAsJsonObject().get("userID").getAsString();
		String userType=element.getAsJsonObject().get("userType").getAsString();
		try{
			if(clientID!=null || clientID!=""){
				vendor.setClientID(clientID);
				vendor.setUserID(userID);
				vendor.setUserType(userType);
				vendorList=service.vendorView(vendor);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(vendorList);
	}
	
	@POST
	@Path("/vendorView")
	@Produces(MediaType.APPLICATION_JSON)
	public String vendorView( String jsonValue){
		List<I0025> vendorList=new ArrayList<I0025>();
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonValue);
		String clientID=element.getAsJsonObject().get("clientID").getAsString();
		String userID=element.getAsJsonObject().get("userID").getAsString();
		String vendorID=element.getAsJsonObject().get("vendorID").getAsString();
		try{
			vendor.setClientID(clientID);
			vendor.setUserID(userID);
			vendor.setVendorPhoneNumber(vendorID);
			vendorList = service.vendorUpdateService(vendor, vendorList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(vendorList);
	}
		
	@POST
	@Path("/vendorUpdate")
	@Produces(MediaType.APPLICATION_JSON)
	public String vendorUpdate(String jsonValue){
		List<I0025> list=new ArrayList<I0025>();
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonValue);
		String clientID=element.getAsJsonObject().get("clientID").getAsString();
		String userID=element.getAsJsonObject().get("userID").getAsString();
		String firmName=element.getAsJsonObject().get("firmName").getAsString();
		String faxNumber=element.getAsJsonObject().get("faxNumber").getAsString();
		String expiryDate=element.getAsJsonObject().get("expiryDate").getAsString();
		String webSite=element.getAsJsonObject().get("website").getAsString();
		String licenceNumber=element.getAsJsonObject().get("licenceNumber").getAsString();
		String business=element.getAsJsonObject().get("business").getAsString();
		String personIncharge=element.getAsJsonObject().get("personIncharge").getAsString();
		String emailId=element.getAsJsonObject().get("emailId").getAsString();
		String phoneNumber=element.getAsJsonObject().get("phoneNumber").getAsString();
		String company=element.getAsJsonObject().get("company").getAsString();
		String vendorType=element.getAsJsonObject().get("vendorType").getAsString();
		String vendorCode=element.getAsJsonObject().get("vendorCode").getAsString();
		String paymetType=element.getAsJsonObject().get("paymentType").getAsString();
		String paymentDays=element.getAsJsonObject().get("paymentDays").getAsString();
		String street=element.getAsJsonObject().get("street").getAsString();
		String city=element.getAsJsonObject().get("city").getAsString();
		String country=element.getAsJsonObject().get("country").getAsString();
		String postcode=element.getAsJsonObject().get("postCode").getAsString();
		String state=element.getAsJsonObject().get("state").getAsString();
		String street1=element.getAsJsonObject().get("street1").getAsString();
		String city1=element.getAsJsonObject().get("city1").getAsString();
		String country1=element.getAsJsonObject().get("country1").getAsString();
		String postcode1=element.getAsJsonObject().get("postCode1").getAsString();
		String state1=element.getAsJsonObject().get("state1").getAsString();
		String note=element.getAsJsonObject().get("note").getAsString();
		String id=element.getAsJsonObject().get("id").getAsString();
		try{
			if(clientID!=null || clientID!=""){
				vendor.setFirmName(phoneNumber);
				vendor.setFaxVendor(faxNumber);
				if(expiryDate==null || expiryDate.equals("")){
					vendor.setVenExdate(null);
				}else{
					vendor.setVenExdate(format.parse(expiryDate));
				}
				vendor.setWebsite(webSite);
				vendor.setVenLicence(licenceNumber);
				vendor.setNatureofbusiness(business);
				vendor.setPeresonIncharge(personIncharge);
				vendor.setEmail_ID_vendor(emailId);
				vendor.setVendorPhoneNumber(firmName);
				vendor.setVenCompany(company);
				vendor.setVenType(vendorType);
				vendor.setVenCode(vendorCode);
				vendor.setPayType(paymetType);
				vendor.setPayDays(paymentDays);
				vendor.setNote(note);
				vendor.setAddress(street);
				vendor.setVenCity(city);
				vendor.setCountry_ID(country);
				vendor.setVenPostalcode(postcode);
				vendor.setVenState(state);
				vendor.setVenAddress1(street1);
				vendor.setVenCity1(city1);
				vendor.setVenCountry1(country1);
				vendor.setVenPostalcode1(postcode1);
				vendor.setVenState1(state1);
				vendor.setClientID(clientID);
				vendor.setUserID(userID);
				vendor.setVendor_Id(Integer.parseInt(id));
				service.vendorModify(vendor,list);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(vendor.getStatus());
	}
	
	@POST
	@Path("/vendorDelete")
	@Produces(MediaType.APPLICATION_JSON)
	public String vendorDelete(String jsonValue){
		VendorDelete vendorDelete = new VendorDelete();
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonValue);
		String clientID=element.getAsJsonObject().get("clientID").getAsString();
		String userID=element.getAsJsonObject().get("userID").getAsString();
		String vendorID=element.getAsJsonObject().get("vendorID").getAsString();
		try{
			if(clientID!="" && vendorID!=""){
				vendorDelete.setClientID(clientID);
				vendorDelete.setVendorPhoneNumber(vendorID);
				service.vendorDeleteService(vendorDelete);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(vendorDelete.getStatus());
	}
	
	@GET
	@Path("/customerCode/{clientID}/{userID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String customerCode(@PathParam ("clientID") String clientID,@PathParam ("userID") String userID){
		List<String> dayList=new ArrayList<String>();
		Gson gson = new Gson();
		String customerCode="";
		if(clientID!=null)
		try{
			customerCode=service.getcusCode(clientID,userID);
			dayList=service.getpaytype();
			vendor.setVenCode(customerCode);
			vendor.setStringList(dayList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(vendor);
	}
	
	@POST
	@Path("/customerRegister")
	@Produces(MediaType.APPLICATION_JSON)
	public String customerRegister(String jsonValue){
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonValue);
		String clientID=element.getAsJsonObject().get("clientID").getAsString();
		String userID=element.getAsJsonObject().get("userID").getAsString();
		String customerTitle=element.getAsJsonObject().get("title").getAsString();
		String customername=element.getAsJsonObject().get("firstname").getAsString();
		String customerMiddleName=element.getAsJsonObject().get("middlename").getAsString();
		String customerLastName=element.getAsJsonObject().get("lastname").getAsString();
		String email=element.getAsJsonObject().get("email").getAsString();
		String code=element.getAsJsonObject().get("code").getAsString();
		String date=element.getAsJsonObject().get("date").getAsString();
		String custMobile=element.getAsJsonObject().get("mobile").getAsString();
		String phonenumber=element.getAsJsonObject().get("phonenumber").getAsString();
		String categoryName=element.getAsJsonObject().get("category").getAsString();
		String other=element.getAsJsonObject().get("other").getAsString();
		String company=element.getAsJsonObject().get("company").getAsString();
		String website=element.getAsJsonObject().get("website").getAsString();
		String faxnumber=element.getAsJsonObject().get("faxnumber").getAsString();
		String tradelicencenumber=element.getAsJsonObject().get("licencenum").getAsString();
		String permanentaddress=element.getAsJsonObject().get("street1").getAsString();
		String presentAddress=element.getAsJsonObject().get("street").getAsString();
		String presentCity=element.getAsJsonObject().get("city").getAsString();
		String permanentCity=element.getAsJsonObject().get("city1").getAsString();
		String presentCountry=element.getAsJsonObject().get("country").getAsString();
		String permanentCountry=element.getAsJsonObject().get("country1").getAsString();
		String presentState=element.getAsJsonObject().get("state").getAsString();
		String permanentState=element.getAsJsonObject().get("state1").getAsString();
		String presentPostCode=element.getAsJsonObject().get("postcode").getAsString();
		String permanentPostCode=element.getAsJsonObject().get("postcode1").getAsString();
		String note=element.getAsJsonObject().get("note").getAsString();
		String cusExdate=element.getAsJsonObject().get("exdate").getAsString();
		String cusCode=element.getAsJsonObject().get("cuscode").getAsString();
		String cusType=element.getAsJsonObject().get("custype").getAsString();
		String paymentType=element.getAsJsonObject().get("paymenttype").getAsString();
		String paymentDays=element.getAsJsonObject().get("paymentdays").getAsString();
		String status="";
		try{
			if(clientID!=null || clientID!=""){
				buyer.setFaxnumber(faxnumber);
				if(cusExdate==null || cusExdate.equals("")){
					buyer.setCusExdate(null);
				}else{
					buyer.setCusExdate(format.parse(cusExdate));
				}
				buyer.setDate(format.parse(date));
				buyer.setCusLicence(tradelicencenumber);
				buyer.setCustomerTitle(customerTitle);
				buyer.setCustomerName(customername);
				buyer.setCustomerMiddleName(customerMiddleName);
				buyer.setCustomerLastName(customerLastName);
				buyer.setMail(email);
				buyer.setCode(code);
				buyer.setPhoneNumber(custMobile);
				buyer.setCustMobile(phonenumber);
				buyer.setCategoryName(categoryName);
				buyer.setOther(other);
				buyer.setCompany(company);
				buyer.setWebsite(website);
				buyer.setFaxnumber(faxnumber);
				buyer.setAddress(presentAddress);
				buyer.setPermanentaddress(permanentaddress);
				buyer.setCity(permanentCity);
				buyer.setPresentCity(presentCity);
				buyer.setCountry(permanentCountry);
				buyer.setPresentcountryID(presentCountry);
				buyer.setPerPostCode(permanentPostCode);
				buyer.setPrePostCode(presentPostCode);
				buyer.setState(permanentState);
				buyer.setPresentstate(presentState);
				buyer.setNote(note);
				buyer.setCusCode(cusCode); 
				buyer.setCusType(cusType); 
				buyer.setType(paymentType); 
				buyer.setCash(paymentDays); 
				buyer.setClientID(clientID);
				buyer.setUserID(userID);
				status = service.saveBuyer(buyer);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(status);
	}
	
	@POST
	@Path("/customerConsole")
	@Produces(MediaType.APPLICATION_JSON)
	public String customerConsole(String jsonValue){
		List<I0032> customerList=new ArrayList<I0032>();
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonValue);
		String clientID=element.getAsJsonObject().get("clientID").getAsString();
		String userID=element.getAsJsonObject().get("userID").getAsString();
		String userType=element.getAsJsonObject().get("userType").getAsString();
		try{
			if(clientID!=null || clientID!=""){
				buyer.setClientID(clientID);
				buyer.setUserID(userID);
				buyer.setUserType(userType);
				customerList=service.getBuyercustInfo(buyer);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(customerList);
	}
	
	@POST
	@Path("/customerView")
	@Produces(MediaType.APPLICATION_JSON)
	public String customerView( String jsonValue){
		List<I0032> customerList=new ArrayList<I0032>();
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonValue);
		String clientID=element.getAsJsonObject().get("clientID").getAsString();
		String customerID=element.getAsJsonObject().get("customerID").getAsString();
		try{
			buyer.setClientID(clientID);
			//customerList = service.getBuyerInfo1(customerID,clientID);
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(customerList);
	}

	@POST
	@Path("/customerUpdate")
	@Produces(MediaType.APPLICATION_JSON)
	public String customerUpdate(String jsonValue){
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonValue);
		String clientID=element.getAsJsonObject().get("clientID").getAsString();
		String userID=element.getAsJsonObject().get("userID").getAsString();
		String customerTitle=element.getAsJsonObject().get("title").getAsString();
		String customername=element.getAsJsonObject().get("firstname").getAsString();
		String customerMiddleName=element.getAsJsonObject().get("middlename").getAsString();
		String customerLastName=element.getAsJsonObject().get("lastname").getAsString();
		String email=element.getAsJsonObject().get("email").getAsString();
		String code=element.getAsJsonObject().get("code").getAsString();
		String date=element.getAsJsonObject().get("date").getAsString();
		String custMobile=element.getAsJsonObject().get("mobile").getAsString();
		String phonenumber=element.getAsJsonObject().get("phonenumber").getAsString();
		String categoryName=element.getAsJsonObject().get("category").getAsString();
		String other=element.getAsJsonObject().get("other").getAsString();
		String company=element.getAsJsonObject().get("company").getAsString();
		String website=element.getAsJsonObject().get("website").getAsString();
		String faxnumber=element.getAsJsonObject().get("faxnumber").getAsString();
		String tradelicencenumber=element.getAsJsonObject().get("licencenum").getAsString();
		String permanentaddress=element.getAsJsonObject().get("street1").getAsString();
		String presentAddress=element.getAsJsonObject().get("street").getAsString();
		String presentCity=element.getAsJsonObject().get("city").getAsString();
		String permanentCity=element.getAsJsonObject().get("city1").getAsString();
		String presentCountry=element.getAsJsonObject().get("country").getAsString();
		String permanentCountry=element.getAsJsonObject().get("country1").getAsString();
		String presentState=element.getAsJsonObject().get("state").getAsString();
		String permanentState=element.getAsJsonObject().get("state1").getAsString();
		String presentPostCode=element.getAsJsonObject().get("postcode").getAsString();
		String permanentPostCode=element.getAsJsonObject().get("postcode1").getAsString();
		String note=element.getAsJsonObject().get("note").getAsString();
		String cusExdate=element.getAsJsonObject().get("exdate").getAsString();
		String cusCode=element.getAsJsonObject().get("cuscode").getAsString();
		String cusType=element.getAsJsonObject().get("custype").getAsString();
		String paymentType=element.getAsJsonObject().get("paymenttype").getAsString();
		String paymentDays=element.getAsJsonObject().get("paymentdays").getAsString();
		try{
			if(clientID!=null || clientID!=""){
				buyer.setFaxnumber(faxnumber);
				if(cusExdate==null || cusExdate.equals("")){
					buyer.setCusExdate(null);
				}else{
					buyer.setCusExdate(format.parse(cusExdate));
				}
				buyer.setDate(format.parse(date));
				buyer.setCusLicence(tradelicencenumber);
				buyer.setCustomerTitle(customerTitle);
				buyer.setCustomerName(customername);
				buyer.setCustomerMiddleName(customerMiddleName);
				buyer.setCustomerLastName(customerLastName);
				buyer.setMail(email);
				buyer.setCode(code);
				buyer.setPhoneNumber(custMobile);
				buyer.setCustMobile(phonenumber);
				buyer.setCategoryName(categoryName);
				buyer.setOther(other);
				buyer.setCompany(company);
				buyer.setWebsite(website);
				buyer.setFaxnumber(faxnumber);
				buyer.setAddress(presentAddress);
				buyer.setPermanentaddress(permanentaddress);
				buyer.setCity(permanentCity);
				buyer.setPresentCity(presentCity);
				buyer.setCountry(permanentCountry);
				buyer.setPresentcountry(presentCountry);
				buyer.setPerPostCode(permanentPostCode);
				buyer.setPrePostCode(presentPostCode);
				buyer.setState(permanentState);
				buyer.setPerstate(presentState);
				buyer.setNote(note);
				buyer.setCusCode(cusCode); 
				buyer.setCusType(cusType); 
				buyer.setType(paymentType); 
				buyer.setCash(paymentDays); 
				buyer.setClientID(clientID);
				buyer.setUserID(userID);
				service.getBuyerUpdate(buyer);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(buyer.getStatus());
	}
	
	@POST
	@Path("/customerDelete")
	@Produces(MediaType.APPLICATION_JSON)
	public String customerDelete(String jsonValue){
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonValue);
		String clientID=element.getAsJsonObject().get("clientID").getAsString();
		String userID=element.getAsJsonObject().get("userID").getAsString();
		String customerID=element.getAsJsonObject().get("customerID").getAsString();
		try{
			if(clientID!="" && customerID!=""){
				buyer.setPhoneNumber(customerID);
				service.buyerDelete(buyer);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(buyer.getStatus());
	}
	
	/* PREMA BEGIN 11-09-2017*/
	
	/*Cash Book Console Code*/
	@POST
	@Path("/cashbookConsole")
	@Produces(MediaType.APPLICATION_JSON)
	public String cashbookConsole(String jsonValue){
		logger.info("[cashbookConsole()]---------------------------Inside cashbookConsole() in EndpointService Calling-------------------------------");
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonValue);
		String clientID=element.getAsJsonObject().get("clientID").getAsString();
		BigDecimal debit=BigDecimal.valueOf(0);BigDecimal credit=BigDecimal.valueOf(0);BigDecimal balance=BigDecimal.valueOf(0);
		try{
			if(clientID!=null || clientID!=""){
				purchaseOrder.setClientID(clientID);
				service.findCashBook(purchaseOrder);
				if (purchaseOrder.getCashBookList().size() > 0) {
					for (PurchaseOrder domain : purchaseOrder.getCashBookList()) {
						try {
							debit = debit.add(new BigDecimal(domain.getDebit()));
						} catch (Exception e) {
							credit = credit.add(new BigDecimal(domain.getCredit()));
						}
					}
					balance = (credit.subtract(debit));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(purchaseOrder);
	}
	
}

