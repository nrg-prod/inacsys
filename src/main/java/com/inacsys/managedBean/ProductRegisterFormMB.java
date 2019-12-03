package com.inacsys.managedBean;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.CategoryRegistration;
import com.inacsys.domain.ProductRegister;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0001;
import com.inacsys.shared.I0002;
import com.inacsys.shared.I0004;
import com.inacsys.shared.I0005;
import com.inacsys.shared.I0006;
import com.inacsys.shared.I0025;
import com.inacsys.util.CommonValidate;

@ManagedBean(name = "productRegisterFormMB")
public class ProductRegisterFormMB {
	private static Logger logger = Logger
			.getLogger(ProductRegisterFormMB.class);
	public int product_ID;
	public String product_name = null;
	public String autual_price;
	public String market_price;

	public String sellingPrice;
	public String margin_price;
	public Date date;
	public Date expired_date;
	public String batch;
	public String description;
	public String description1;
	public String desc;
	private boolean productSuccessFlag = false;
	private boolean productFlag = false;

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public boolean isProductFlag() {
		return productFlag;
	}

	public void setProductFlag(boolean productFlag) {
		this.productFlag = productFlag;
	}

	public boolean isProductSuccessFlag() {
		return productSuccessFlag;
	}

	public void setProductSuccessFlag(boolean productSuccessFlag) {
		this.productSuccessFlag = productSuccessFlag;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String color;
	public String size_of_product;
	public String product_standard;
	public String brand;
	public String product_weight;
	public String ideal_for;
	public String type_parent;
	public String product_group;
	public String industry_ID;
	public String software;
	public String electronics;
	public String mechanical;
	public String electrical;
	public String biomedical;
	public String civil;
	public String food;
	public String industry;
	public String invalidate2;
	public String getValidate2() {
		return validate2;
	}

	public void setValidate2(String validate2) {
		this.validate2 = validate2;
	}

	public String test;
	public List<I0001> auto;
	private String category1;
	private String validate2;

	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	public List<I0001> getAuto() {
		return auto;
	}

	public void setAuto(List<I0001> auto) {
		this.auto = auto;
	}

	public String getAutual_price() {
		return autual_price;
	}

	public void setAutual_price(String autual_price) {
		this.autual_price = autual_price;
	}

	public String getMarket_price() {
		return market_price;
	}

	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getMargin_price() {
		return margin_price;
	}

	public void setMargin_price(String margin_price) {
		this.margin_price = margin_price;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	// public int productlimit;
	public String productlimit;
	public String unit;

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getInvalidate2() {
		return invalidate2;
	}

	public String getProductlimit() {
		return productlimit;
	}

	public void setProductlimit(String productlimit) {
		this.productlimit = productlimit;
	}

	public void setInvalidate2(String invalidate2) {
		this.invalidate2 = invalidate2;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getIndustry_ID() {
		return industry_ID;
	}

	public void setIndustry_ID(String industry_ID) {
		this.industry_ID = industry_ID;
	}

	public int getProduct_ID() {
		return product_ID;
	}

	public void setProduct_ID(int product_ID) {
		this.product_ID = product_ID;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getExpired_date() {
		return expired_date;
	}

	public void setExpired_date(Date expired_date) {
		this.expired_date = expired_date;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize_of_product() {
		return size_of_product;
	}

	public void setSize_of_product(String size_of_product) {
		this.size_of_product = size_of_product;
	}

	public String getProduct_standard() {
		return product_standard;
	}

	public void setProduct_standard(String product_standard) {
		this.product_standard = product_standard;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProduct_weight() {
		return product_weight;
	}

	public void setProduct_weight(String product_weight) {
		this.product_weight = product_weight;
	}

	public String getIdeal_for() {
		return ideal_for;
	}

	public void setIdeal_for(String ideal_for) {
		this.ideal_for = ideal_for;
	}

	public String getType_parent() {
		return type_parent;
	}

	public void setType_parent(String type_parent) {
		this.type_parent = type_parent;
	}

	public String getProduct_group() {
		return product_group;
	}

	public void setProduct_group(String product_group) {
		this.product_group = product_group;
	}

	public String getSoftware() {
		return software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getElectronics() {
		return electronics;
	}

	public void setElectronics(String electronics) {
		this.electronics = electronics;
	}

	public String getMechanical() {
		return mechanical;
	}

	public void setMechanical(String mechanical) {
		this.mechanical = mechanical;
	}

	public String getElectrical() {
		return electrical;
	}

	public void setElectrical(String electrical) {
		this.electrical = electrical;
	}

	public String getBiomedical() {
		return biomedical;
	}

	public void setBiomedical(String biomedical) {
		this.biomedical = biomedical;
	}

	public String getCivil() {
		return civil;
	}

	public void setCivil(String civil) {
		this.civil = civil;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	List<I0004> typeparent = null;

	public List<I0004> getTypeparent() {
		return typeparent;
	}

	public void setTypeparent(List<I0004> typeparent) {
		this.typeparent = typeparent;
	}

	List<I0002> productgroup = null;
	List<I0006> industryList = null;
	List<String> ven = null;

	public List<String> getVen() {
		return ven;
	}

	public void setVen(List<String> ven) {
		this.ven = ven;
	}

	public List<I0002> getProductgroup() {
		return productgroup;
	}

	public void setProductgroup(List<I0002> productgroup) {
		this.productgroup = productgroup;
	}

	public List<I0006> getIndustryList() {
		return industryList;
	}

	public void setIndustryList(List<I0006> industryList) {
		this.industryList = industryList;
	}

	String validate;

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	DemoController controller = null;
	ProductRegister productRegister = new ProductRegister();

	/* udhaya 31.12.2014 */

	public List<String> categorytype = null;
	public String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getCategorytype() {
		return categorytype;
	}

	public void setCategorytype(List<String> categorytype) {
		this.categorytype = categorytype;
	}

	
	ArrayList<String> proMB = new ArrayList<String>();

	public ArrayList<String> getProMB() {
		return proMB;
	}

	public void setProMB(ArrayList<String> proMB) {
		this.proMB = proMB;
	}

	public List<String> productList = null;

	public List<String> getProductList() {
		return productList;
	}

	public void setProductList(List<String> productList) {
		this.productList = productList;
	}

	public String dropDown() {
		logger.info("[dropdown()] --------------- Inside dropdown() method() ------------------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setTypeparent(controller.dropDown(typeparent));
			setProductgroup(controller.dropDow(productgroup));
			setIndustryList(controller.dropDo(industryList));
			setVen(controller.dropD(ven));
			Collections.sort(ven, String.CASE_INSENSITIVE_ORDER);
			setCategorytype(controller.categorylist(categorytype));
			// prema begin 27/04/2016 product drop box
			productList = controller.getproductListInfo(productRegister);
			// prema end
			Collections.sort(categorytype, String.CASE_INSENSITIVE_ORDER);
			if (categorytype.size() > 0) {

			}
			
			return "";
		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
			return "";
		}
	}

	public void valueChange(ValueChangeEvent e) {
		logger.info("[valuechange()] --------------- Inside valuechange() method() ------------------------");
		productRegister.setSelling("" + e.getNewValue());
		logger.debug("[valuechange()] --------------- actual ------------------------>"+productRegister.getActual());
		logger.debug("[valuechange()] --------------- selling ------------------------>"+productRegister.getSelling());
		if (productRegister.getActual() != null	&& productRegister.getSelling() != null) {
			setMargin_price(""+ new BigDecimal(productRegister.getSelling()).subtract(new BigDecimal(productRegister.getActual())));
		} else {
			logger.info("[valuechange()] --------------- outside selling------------------------");
		}
	}

	public void valueChange1(ValueChangeEvent e) {
		logger.info("[valuechange1()] --------------- Inside valuechange1() method() ------------------------");
		productRegister.setActual("" + e.getNewValue());
		logger.debug("[valuechange1()] --------------- selling ------------------------>"+productRegister.getSelling());
		if (productRegister.getActual() != null && productRegister.getSelling() != null) {
			setMargin_price(""+ new BigDecimal(productRegister.getSelling()).subtract(new BigDecimal(productRegister.getActual())));
		} else {
			logger.info("[valuechange1()] --------------- outside selling ------------------------");
		}
	}

	public String calculate() {
		logger.info("[calculate()] --------------- Inside calculate() method() ------------------------");
		try {
			productRegister.setActual("" + autual_price);
			productRegister.setSelling("" + sellingPrice);
			logger.debug("[calculate()] --------------- selling ------------------------>"+productRegister.getSelling());
			logger.debug("[calculate()] --------------- actual ------------------------>"+productRegister.getActual());
			if (productRegister.getActual() != null && productRegister.getSelling() != null) {
				setMargin_price(""+ new BigDecimal(productRegister.getSelling()).subtract(new BigDecimal(productRegister.getActual())));
				logger.debug("[calculate()] --------------- margin price ------------------------>"+margin_price);
			} else {
				logger.info("[calculate()] --------------- outside selling ------------------------");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "productRegisterForm";
	}

	public String productRegisterDirect() {
		logger.info("[productRegisterDirect()] --------------- Inside productRegisterDirect() method() ------------------------");
		setInvalidate2(null);
		setValidate(null);
		setProduct_name(null);
		setProduct_ID(0);
		setAutual_price("");
		setMarket_price("");
		setSellingPrice("");
		setMargin_price("");
		setDate(null);
		setExpired_date(null);
		setDescription(null);
		setColor(null);
		setSize_of_product(null);
		setProduct_standard(null);
		setBrand(null);
		setProduct_weight(null);
		setType_parent(null);
		// setProduct_group(null);
		setIndustry_ID(null);
		setIndustry(null);
		setCategory(null);
		setCategorytype(null);
		// setProductlimit(0);
		setProductlimit(null);
		setUnit(null);
		/* return "productRegisterDirect"; */
		return "";
	}

	public String reset() {
		logger.info("[reset()] --------------- Inside reset() method() ------------------------");
		setValidate(null);
		setProduct_name(null);
		setProduct_ID(0);
		setAutual_price("");
		setMarket_price("");
		setSellingPrice("");
		setMargin_price("");
		setDate(null);
		setExpired_date(null);
		setDescription(null);
		setColor(null);
		setSize_of_product(null);
		setProduct_standard(null);
		setBrand(null);
		setProduct_weight(null);
		setType_parent(null);
		setProduct_group(null);
		setIndustry_ID(null);
		setIndustry(null);
		setCategory(null);
		// setProductlimit(0);
		setProductlimit(null);
		setUnit(null);
		return "";
	}

	public void value(ValueChangeEvent v) {
		logger.info("[value()] --------------- Inside value() method() ------------------------");
		try {
			List<I0001> producttt = controller.dropdownproduct1();
			int cnt = 0;
			for (int i = 0; i < producttt.size(); i++) {
				if (producttt.get(i).getProductName().equalsIgnoreCase(v.getNewValue().toString())) {
					cnt++;
				}
			}
			if (cnt > 0) {
				throw new Exception("This Product Name Already Registered");
			} else {
				setBatch(v.getNewValue() + "NRG01");
				throw new Exception(
						"This Product Name Available for Registration");
			}

		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
		} finally {

		}
	}

	/* udhaya 30.12.2014 */

	public String todayDate;

	public String getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	public String barcode;
	public String format;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	Date dateobj = new Date();

	public String saveProductRegister() {
		logger.info("[saveProductRegister()] --------------- Inside saveProductRegister() method() ------------------------");
		todayDate = df.format(Calendar.getInstance().getTime());
		try {
			setInvalidate2(null);
			setInvalidate2(null);
			setInvalidate2(null);
			setInvalidate2(null);
			if (category.equalsIgnoreCase("")) {
				throw new Exception("Please Choose the Category Type.");
			} else if (product_name.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Product Name.");
			} else if (productlimit.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Product Code.");
			} else if (unit.equalsIgnoreCase("")) {
				throw new Exception("Please Choose the Unit.");
			} else if (size_of_product.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Product Size.");
			} else if (!size_of_product.matches("[0-9]+")) {
				throw new Exception("Size should be in Number");
			}
		
			else if (color.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Product Color");
			} else if (industry.equalsIgnoreCase("")) {
				throw new Exception("Please Choose the Vendor Name");
			}
			else if (autual_price.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Actual Price");
			} else if (!autual_price.matches("[0-9]+")) {
				throw new Exception("Actual price should be in Number");
			} else if (market_price.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Market Price");
			} else if (!market_price.matches("[0-9]+")) {
				throw new Exception("Market price should be in Number");
			}
			setMargin_price(autual_price);
			productRegister.setIndustry(industry);
			productRegister.setProduct_ID(product_ID);
			productRegister.setProductName(product_name);
			productRegister.setAutual_price(autual_price);
			productRegister.setMargin_price(autual_price);
			productRegister.setMarket_price(market_price);
			productRegister.setCategory(category);
			logger.debug("category-->>" + category);
			productRegister.setDate(todayDate);
			Date date = df.parse(todayDate);
			logger.debug("batch-------------->" + batch);
			productRegister.setBatch(batch);
			logger.debug("size of product--->" + size_of_product);
			productRegister.setDescription(description);
			productRegister.setColor(color);
			productRegister.setSize_of_product(size_of_product);
			productRegister.setProduct_standard(product_standard);
			productRegister.setBrand(brand);
			productRegister.setProduct_weight(product_weight);
			productRegister.setIdeal_for(ideal_for);
			productRegister.setMechanical(mechanical);
			productRegister.setSoftware(software);
			productRegister.setElectrical(electrical);
			productRegister.setElectronics(electronics);
			productRegister.setBiomedical(biomedical);
			productRegister.setCivil(civil);
			productRegister.setFood(food);
			productRegister.setUnit(unit);
			productRegister.setActual("" + autual_price);
			logger.debug("[saveProductRegister()] --------------- selling ------------------------>"+productRegister.getActual());
			if (productRegister.getActual() != null) {
				setMargin_price(productRegister.getActual());
			} else {
				logger.info("[saveProductRegister()] --------------- outside selling ------------------------>"+productRegister.getActual());
			}
			conform();
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("error::" + e.getMessage());
			setProductSuccessFlag(false);
		}
		return "";
	}

	public String conform() throws ParseException {
		logger.info("[conform()] --------------- Inside conform() method() ------------------------");
		setValidate(null);
		try {
			if (!autual_price.matches("[0-9]+")) {
				throw new DemoException("Actual price should be in Number");
			} else if (!market_price.matches("[0-9]+")) {
				throw new DemoException("Market price should be in Number");
			}
			String status = "Fail";
			String k, j, l;
			/* DateFormat format = new SimpleDateFormat("MM"); */
			Calendar now = Calendar.getInstance();
			k = "" + now.get(Calendar.MONTH) + 1;
			j = "" + now.get(Calendar.YEAR);
			l = "" + j.substring(2);
			format = k + l;
			String s = productlimit.toUpperCase();
			logger.debug("[conform()] --------------- before ------------------------>"+s);
			s = s.replaceAll("\\s", "");
			logger.debug("[conform()] --------------- String after ------------------------>"+s);
			if (s.length() > 0) {
				logger.debug("[conform()] --------------- after the converting ------------------------>"+s.length());
				if (s.length() <= 1) {
					barcode = format + "00000" + s;
					logger.debug("[conform()] --------------- length <=1 ------------------------>"+barcode);
					productRegister.setBarcode(barcode);
				} else if (s.length() <= 2) {
					barcode = format + "0000" + s;
					logger.debug("[conform()] --------------- length <=2 ------------------------>"+barcode);
					productRegister.setBarcode(barcode);
				} else if (s.length() <= 3) {
					barcode = format + "000" + s;
					logger.debug("[conform()] --------------- length <=3 ------------------------>"+barcode);
					productRegister.setBarcode(barcode);
				} else if (s.length() <= 4) {
					barcode = format + "00" + s;
					logger.debug("[conform()] --------------- length <=4 ------------------------>"+barcode);
					logger.debug("4------------>" + barcode);
					productRegister.setBarcode(barcode);
				} else if (s.length() <= 5) {
					barcode = format + "0" + s;
					logger.debug("[conform()] --------------- length <=5 ------------------------>"+barcode);
					productRegister.setBarcode(barcode);
				} else if (s.length() <= 6) {
					barcode = format + s;
					logger.debug("[conform()] --------------- length <=6 ------------------------>"+barcode);
					productRegister.setBarcode(barcode);
				} else {
					barcode = format + s.substring(0, 6);
					productRegister.setBarcode(barcode);
				}
			}
			setInvalidate2(null);
			productRegister.setIndustry(industry);
			productRegister.setProduct_ID(product_ID);
			productRegister.setProductName(product_name);
			productRegister.setAutual_price(autual_price);
			productRegister.setMargin_price(autual_price);
			productRegister.setSellingPrice(market_price);
			productRegister.setMarket_price(market_price);
			productRegister.setDate(todayDate);
			productRegister.setCategory(category);
			productRegister.setRefcode(refcode);
			productRegister.setBatch(batch);
			productRegister.setDescription(description);
			productRegister.setColor(color);
			productRegister.setSize_of_product(size_of_product);
			productRegister.setProduct_standard(product_standard);
			productRegister.setBrand(brand);
			productRegister.setProduct_weight(product_weight);
			productRegister.setIdeal_for(ideal_for);
			productRegister.setMechanical(mechanical);
			productRegister.setSoftware(software);
			productRegister.setElectrical(electrical);
			productRegister.setElectronics(electronics);
			productRegister.setBiomedical(biomedical);
			productRegister.setCivil(civil);
			productRegister.setFood(food);
			productRegister.setProductlimit(productlimit);
			productRegister.setUnit(unit);
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			status = controller.saveProductRegister(productRegister);
			logger.debug("[conform()] --------------- status ------------------------>"+status);
			RequestContext context = RequestContext.getCurrentInstance();
			if (status.equalsIgnoreCase("success")) {
				context.execute("PF('confirm').show();");
			}else{
				setProductFlag(true);
				context.execute("checkFail2();");
				context.execute("checkFail3();");
				setProductSuccessFlag(false);
			}
		} catch (DemoException e) {

			setValidate(e.getMessage());
			setInvalidate2(e.getMessage());
			logger.error("error::" + e.getMessage());
			setProductFlag(false);
			setProductSuccessFlag(false);
		} finally {
		}
		return "";

	}

	public String cancel() {
		return "success";
	}

	public String redirect1() {
		return "home";
	}

	/* 6.1.2015 udhaya */

	public String refcode;

	public String getRefcode() {
		return refcode;
	}

	public void setRefcode(String refcode) {
		this.refcode = refcode;
	}

	UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	private ArrayList<UploadedImage> ll = new ArrayList<UploadedImage>();

	public ArrayList<UploadedImage> getLl() {
		return ll;
	}

	public void setLl(ArrayList<UploadedImage> ll) {
		this.ll = ll;
	}

	private ArrayList<UploadedImage> files = new ArrayList<UploadedImage>();

	public ArrayList<UploadedImage> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<UploadedImage> files) {
		this.files = files;
	}

	public void paint1(OutputStream stream, Object object) throws IOException {
		logger.info("[paint1()] --------------- Inside paint1() method() ------------------------");
		stream.write(getFiles().get((Integer) object).getData());
		stream.close();
	}

	public void paint2(OutputStream out, Object data) throws IOException {
		logger.info("[paint2()] --------------- Inside paint2() method() ------------------------");
		String s = "C://product/";
		BufferedImage img = ImageIO.read(new File(s + "" + fileName));
		ImageIO.write(img, "png", out);
	}

	public String uploadValidate;

	public String getUploadValidate() {
		return uploadValidate;
	}

	public void setUploadValidate(String uploadValidate) {
		this.uploadValidate = uploadValidate;
	}

	public void listener(FileUploadEvent event) throws Exception {
		logger.info("[listener()] --------------- Inside listener() method() ------------------------");
		try {
			setUploadValidate("");
			UploadedFile item = event.getFile();
			if (files.size() >= 1) {
				throw new Exception("Only one Image can upload");
			}
			UploadedImage file = new UploadedImage();
			file.setLength(item.getSize());
			file.setName(item.getFileName());
			file.setData(item.getContents());
			files.add(file);
			File fullFile = new File(item.getFileName());
			this.file = event.getFile();
		} catch (Exception e) {
			setUploadValidate(e.getMessage());
			logger.error("Inside Exception"+e.getMessage());
		}
	}

	public void listener2(FileUploadEvent event) throws Exception {
		logger.info("[listener2()] --------------- Inside listener2() method() ------------------------");
		try {
			setUploadValidate("");
			UploadedFile item = event.getFile();
			if (files.size() >= 10) {
				throw new Exception("Only 10 Images can be uploaded");
			}
			UploadedImage file = new UploadedImage();
			file.setFlength(item.getSize());
			file.setName(item.getFileName());
			file.setAname(item.getFileName());
			productRegister.setNo(item.getFileName());
			file.setData(item.getContents());
			file.setIo(item.getInputstream());
			files.add(file);
			File fullFile = new File(item.getFileName());
			this.file = event.getFile();
			if (files.size() == 1) {
			} else if (files.size() > 1) {
				for (int i = 0; i < files.size() - 1; i++) {
					if (files.get(i).getAname().equals(productRegister.getNo())) {
						throw new Exception("Already Select this Image");
					}
				}
			}
		} catch (Exception e) {
			setUploadValidate(e.getMessage());
			logger.error("Inside Exception"+e.getMessage());
		}
	}

	public String clearUploadData() {
		logger.info("[clearUploadData()] --------------- Inside clearUploadData() method() ------------------------");
		setUploadValidate("");
		files.clear();
		list1.clear();
		setFile(null);
		return null;
	}

	public int getSize() {
		logger.info("[getSize()] --------------- Inside getSize() method() ------------------------");
		if (getFiles().size() > 0) {
			return getFiles().size();
		} else {
			return 0;
		}
	}

	public long getTimeStamp() {
		return System.currentTimeMillis();
	}

	private boolean valid = true;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String flag = "none";
	public String flag2 = "none";
	public List<ProductRegister> domain1 = new ArrayList<ProductRegister>();

	public List<ProductRegister> getDomain1() {
		return domain1;
	}

	public void setDomain1(List<ProductRegister> domain1) {
		this.domain1 = domain1;
	}

	public List<ProductRegister> domain2 = new ArrayList<ProductRegister>();

	public List<ProductRegister> getDomain2() {
		return domain2;
	}

	public void setDomain2(List<ProductRegister> domain2) {
		this.domain2 = domain2;
	}

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String designReg() {
		logger.info("[designReg()] --------------- Inside designReg() method() ------------------------");
		try {
			valid = true;
			setValidate("");
			setDescription("");
			setProduct_name("");
			setArticleNo(null);
			setArticleProd("");
			setThemeProd("");
			setDate1(null);
			setDate2(null);
			setDate3(null);
			setDate4(null);
			setUploadValidate("");
			files.clear();
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			auto = controller.autoComplete(auto, productRegister);
		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
		}
		return "DesignRegisterPage";
	}

	public String designRegSubmit() {
		try {
			setValidate("");
			if (product_name.equalsIgnoreCase("")) {
				throw new DemoException("Please Select the Brand Product");
			} else if (files.size() == 0) {
				throw new DemoException("Please Select One Image");
			}
			Date today = Calendar.getInstance().getTime();
			date = today;
			productRegister.setExpired_date(date);
			productRegister.setDescription(description);
			productRegister.setProductName(product_name);
			productRegister.setFile(file);
			productRegister.setArticleNo(articleNo);
			productRegister.setArticleProd(articleProd);
			productRegister.setThemeProd(themeProd);
			productRegister.setDate1(date1);
			productRegister.setDate2(date2);
			productRegister.setDate3(date3);
			productRegister.setDate4(date4);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.designRegSubmit(productRegister);
		} catch (DemoException ei) {
			setValidate(ei.getMessage());
			logger.debug("----------inside io exception--------"+ ei.getMessage());
			ei.printStackTrace();
			return "";
		} catch (Exception e) {

		}
		return "DesignConfirmationPage";
	}

	public String designView() {
		logger.info("[designView()] --------------- Inside designView() method() ------------------------");
		setValidate("");
		setDate(null);
		setExpired_date(null);
		setProduct_name("");
		setFlag("none");
		return "DesignViewPage";
	}

	public String designViewOnload() {
		logger.info("[designViewOnload()] --------------- Inside designViewOnload() method() ------------------------");
		try {
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			auto = controller.autoComplete(auto, productRegister);

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";
	}

	public String designViewByDate() {
		logger.info("[designViewByDate()] --------------- Inside designViewByDate() method() ------------------------");
		setProduct_name("");
		try {
			domain2.clear();
			setValidate("");
			if (date == null) {
				throw new DemoException("Please Enter the From Date");
			} else if (expired_date == null) {
				throw new DemoException("Please Enter the To Date");
			}
			productRegister.setExpired_date(expired_date);
			productRegister.setDates(date);
			productRegister.setStatus("byDate");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.designView(productRegister);
			if (productRegister.getDomain1().size() > 0) {
				flag = "1";
				setDomain2(productRegister.getDomain1());
			} else {
				flag = "none";
				throw new DemoException("*No Records Found");
			}
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
		}
		return "DesignViewPage";
	}

	public String designViewByName() {
		logger.info("[designViewByName()] --------------- Inside designViewByName() method() ------------------------");
		setExpired_date(null);
		setDate(null);
		try {
			setValidate("");
			domain2.clear();
			if (product_name.equalsIgnoreCase("")) {
				throw new DemoException("Please Select the Product Name");
			}
			productRegister.setProductName(product_name);
			productRegister.setStatus("byName");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.designView(productRegister);
			if (productRegister.getDomain1().size() > 0) {
				flag = "1";
				setDomain2(productRegister.getDomain1());
			} else {
				flag = "none";
				throw new DemoException("*No Records for this Product");
			}
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
		}
		return "";
	}

	public String designViews() {
		logger.info("[designViews()] --------------- Inside designViews() method() ------------------------");
		try {
			setValidate("");
			files.clear();
			ll.clear();
			productRegister.setProductName(product_name);
			productRegister.setStatus("byName");
			ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.designView(productRegister);
			if (productRegister.getDomain1().size() > 0) {
				setProduct_name(productRegister.getDomain1().get(0)
						.getProductName());
				setDescription(productRegister.getDomain1().get(0)
						.getDescription());
				setFileName(productRegister.getDomain1().get(0).getFileName());

			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "DesignViewsPage";
	}

	ArrayList<String> list1 = new ArrayList<String>();

	public ArrayList<String> getList1() {
		return list1;
	}

	public void setList1(ArrayList<String> list1) {
		this.list1 = list1;
	}

	public String filepath;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Autowired
	ServletContext servletContext;

	public String TransferFile2(String fileName,
			java.io.InputStream inputStream, String temp, long ll)
			throws DemoException {
		logger.info("[TransferFile2()] --------------- Inside TransferFile2() method() ------------------------");
		try {
			filepath = "C://product//";
			logger.debug("[TransferFile2()] --------------- filepath ------------------------>"+filepath);
			OutputStream out = new FileOutputStream(new File(filepath + temp
					+ fileName));
			int reader = 0;
			byte[] bytes = new byte[(int) ll];
			while ((reader = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, reader);
			}

			inputStream.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.debug(e.getMessage());
		}
		return temp + "" + fileName;
	}

	public ArrayList<ProductRegisterFormMB> listz = new ArrayList<ProductRegisterFormMB>();

	public ArrayList<ProductRegisterFormMB> getListz() {
		return listz;
	}

	public void setListz(ArrayList<ProductRegisterFormMB> listz) {
		this.listz = listz;
	}

	public String ff1 = "none";
	public String ff2 = "none";
	public String ff3 = "none";
	public String ff4 = "none";

	public String getFf4() {
		return ff4;
	}

	public void setFf4(String ff4) {
		this.ff4 = ff4;
	}

	public String getFf3() {
		return ff3;
	}

	public void setFf3(String ff3) {
		this.ff3 = ff3;
	}

	public String getFf1() {
		return ff1;
	}

	public void setFf1(String ff1) {
		this.ff1 = ff1;
	}

	public String getFf2() {
		return ff2;
	}

	public void setFf2(String ff2) {
		this.ff2 = ff2;
	}

	public String vvalidate() {
		logger.info("[validate()] --------------- Inside validate() method() ------------------------");
		try {
			setInvalidate2("");
			setUploadValidate("");
			count = 0;
			domain1.clear();
			setValidate("");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.designValidate(productRegister);
			if (productRegister.getStatus().equalsIgnoreCase("upload")) {
				ff1 = "1";
				ff2 = "none";
				ff3 = "none";
				ff4 = "none";
			}
			if (productRegister.getStatus().equalsIgnoreCase("viewUpload")) {
				ff2 = "1";
				ff1 = "none";
				ff3 = "none";
				ff4 = "none";
				logger.debug("[validate()] --------------- domain2 size ------------------------>"+productRegister.getDomain2().size());
				setDomain1(productRegister.getDomain2());
			}
			if (productRegister.getStatus().equalsIgnoreCase("finalupload")) {
				ff1 = "none";
				ff2 = "none";
				ff3 = "1";
				ff4 = "none";
				logger.debug("[validate()] --------------- domain2 size ------------------------>"+productRegister.getDomain2().size());
				setDomain1(productRegister.getDomain2());
			}
			if (productRegister.getStatus().equalsIgnoreCase("finalized")) {
				ff1 = "none";
				ff2 = "none";
				ff3 = "none";
				ff4 = "1";
			}

		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
		}
		return "DesignViewsFilterPage";
	}

	public List<ProductRegister> domain3 = new ArrayList<ProductRegister>();

	public List<ProductRegister> getDomain3() {
		return domain3;
	}

	public void setDomain3(List<ProductRegister> domain3) {
		this.domain3 = domain3;
	}

	public String designImageSubmit() {
		logger.info("[designImageSubmit()] --------------- Inside designImageSubmit() method() ------------------------");
		try {
			setValidate("");
			if (files.size() == 0) {
				throw new Exception("Please Select Images");
			}
			if (files.size() < 10) {
				throw new Exception("Please Select 10 Images");
			}
			productRegister.setProductName(product_name);
			productRegister.setFiles(files);
			ArrayList<UploadedImage> files1 = new ArrayList<UploadedImage>();
			files1.addAll(files);
			int c = 0;
			for (int i = 0; i < files.size(); i++) {
				c = 0;
				String name = files.get(i).getAname();
				for (int j = 0; j < files1.size(); j++) {
					if (files1.get(j).getAname().equals(name)) {
						c++;
					}
				}
				if (c > 1) {
					throw new Exception("Please Select Unique Images");
				}
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.designViews(productRegister);
			for (int i = 0; i < files.size(); i++) {
				TransferFile2(files.get(i).getAname(), files.get(i).getIo(),
						product_name, files.get(i).getFlength());
			}

		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception"+e.getMessage());
			return "";
		}
		return "DesignConfirmationPage";
	}

	public String designViewDelete() {
		logger.info("[designViewDelete()] --------------- Inside designViewDelete() method() ------------------------");
		try {
			setValidate("");
			productRegister.setProductName(product_name);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.designViewDelete(productRegister);

		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
		}
		return "DesignViewDeletePage";
	}

	public int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String priroty = "false";

	public String getPriroty() {
		return priroty;
	}

	public void setPriroty(String priroty) {
		this.priroty = priroty;
	}

	public void Change(ValueChangeEvent val) {
		logger.info("[change()] --------------- Inside change() method() ------------------------");
		ProductRegister uu = new ProductRegister();
		String de = val.getNewValue().toString();
		String no = val.getComponent().getAttributes().get("no").toString();
		String priroty = val.getComponent().getAttributes().get("priroty")
				.toString();
		String fname = val.getComponent().getAttributes().get("fname")
				.toString();
		uu.setFileName(fname);
		uu.setNo(no);
		uu.setDesc(de);
		uu.setPriroty(priroty);
		uu.setStatus("selected");
		domain1.set(Integer.parseInt(no), uu);
	}

	public void checkBox(ValueChangeEvent v) throws Exception {
		logger.info("[checkBox()] --------------- Inside checkBox() method() ------------------------");
		try {
			String desc = "";
			String fname = "";
			String no = "";
			ProductRegister uu = new ProductRegister();

			try {
				fname = v.getComponent().getAttributes().get("fname")
						.toString();
				no = v.getComponent().getAttributes().get("no").toString();
				desc = v.getComponent().getAttributes().get("desc").toString();
				String valid = v.getNewValue().toString();
				if (("" + v.getNewValue()).equals("true")) {
					uu.setFileName(fname);
					uu.setDesc(desc);
					uu.setNo(no);
					uu.setStatus("selected");
					domain1.set(Integer.parseInt(no), uu);
					count = count + 1;
				} else {
					count = count - 1;
					for (int i = 0; i < domain1.size(); i++) {
						if (fname
								.equalsIgnoreCase(domain1.get(i).getFileName())
								&& desc.equalsIgnoreCase(domain1.get(i)
										.getDesc())
								&& no.equalsIgnoreCase(domain1.get(i).getNo())) {
							domain1.get(i).setDesc("");
							domain1.get(i).setStatus("");
						}

					}
				}
			} catch (NullPointerException n) {
				if (("" + v.getNewValue()).equals("true")) {
					desc = "nill";
					uu.setFileName(fname);
					uu.setDesc(desc);
					uu.setNo(no);
					uu.setStatus("selected");
					domain1.set(Integer.parseInt(no), uu);
					count = count + 1;
				} else {
					count = count - 1;
					for (int i = 0; i < domain1.size(); i++) {
						if (fname
								.equalsIgnoreCase(domain1.get(i).getFileName())
								&& desc.equalsIgnoreCase(domain1.get(i)
										.getDesc())
								&& no.equalsIgnoreCase(domain1.get(i).getNo())) {
							domain1.get(i).setStatus("");
						}

					}
				}

			}
			if (productRegister.getStatus().equalsIgnoreCase("viewUpload")) {
				if (count > 5) {
					throw new Exception("*Select any 5 Images");

				}
			}
			if (productRegister.getStatus().equalsIgnoreCase("finalupload")) {
				if (count > 1) {
					throw new Exception("*Select any 1 Image");
				}
			}
		} catch (Exception e) {
			setInvalidate2(e.getMessage());
			logger.error("Inside Exception", e);

		}
	}

	public String saveImages() {
		logger.info("[saveImages()] --------------- Inside saveImages() method() ------------------------");
		try {
			if (count == 0) {
				throw new DemoException("Please Select Images");
			}
			if (count < 5
					&& productRegister.getStatus().equalsIgnoreCase(
							"viewUpload")) {
				throw new DemoException("*Select any 5 Images");
			}
			if (count > 5
					&& productRegister.getStatus().equalsIgnoreCase(
							"viewUpload")) {
				throw new DemoException("*Select any 5 Images");
			}
			if (count > 1
					&& productRegister.getStatus().equalsIgnoreCase(
							"finalupload")) {
				throw new DemoException("*Select any 1 Images");
			}
			productRegister.setDomain1(domain1);
			for (int i = 0; i < domain1.size(); i++) {
				logger.debug("[saveImages()] --------------- status ------------------------>"+domain1.get(i).getStatus());
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.saveImages(productRegister);
		} catch (DemoException e) {
			setInvalidate2(e.getMessage());
			logger.error("Inside Exception"+e.getMessage());
			return "";
		}
		return "SaveConfirmationPage";
	}

	public String articleNo;
	public String themeProd;
	public String articleProd;
	public Date date1;
	public Date date2;
	public Date date3;
	public Date date4;

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getThemeProd() {
		return themeProd;
	}

	public void setThemeProd(String themeProd) {
		this.themeProd = themeProd;
	}

	public String getArticleProd() {
		return articleProd;
	}

	public void setArticleProd(String articleProd) {
		this.articleProd = articleProd;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Date getDate3() {
		return date3;
	}

	public void setDate3(Date date3) {
		this.date3 = date3;
	}

	public Date getDate4() {
		return date4;
	}

	public void setDate4(Date date4) {
		this.date4 = date4;
	}

	// prema begin 27/04/2016 product registration
	public String productcheck() {
		logger.info("[productCheck()] --------------- Inside productCheck() method() ------------------------");
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext context1 = FacesContext.getCurrentInstance();
		DemoController controller = null;
		try {

			Map<String, String> params = context1.getExternalContext()
					.getRequestParameterMap();
			String name = params.get("param3");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String status = controller.getProductVerification(name);
			logger.debug("[productCheck()] --------------- status ------------------------>"+status);
			if (status.equalsIgnoreCase("Success")) {
				context.execute("checkFail2();");

			} else {
				context.execute("checkSuccess2();");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";

	}

	// prema end 27/04/2016
	
	
	public String productcodecheck() {
		logger.info("[productcodecheck()] --------------- Inside productcodecheck() method() ------------------------");
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext context1 = FacesContext.getCurrentInstance();
		DemoController controller = null;
		try {

			Map<String, String> params = context1.getExternalContext()
					.getRequestParameterMap();
			String name = params.get("param4");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			String status = controller.getProductcodeVerification(name);
			logger.debug("[productcodecheck()] --------------- status ------------------------>"+status);
			if (status.equalsIgnoreCase("Success")) {
				context.execute("checkFail3();");
			} else {
				context.execute("checkSuccess3();");
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";

	}

	public String productDialog() {
		logger.info("[productDialog()] --------------- Inside productDialog() method() ------------------------");
		setProductFlag(true);
		return "";

	}

	public String hideDialogBox() {
		logger.info("[hideDialogBox()] --------------- Inside hideDialogBox() method() ------------------------");
		setProductFlag(false);
		setProductSuccessFlag(false);
		return "";

	}
	

	// prema begin 28/04/2016
	public String loadDialogBox() {
		logger.info("[loadDialogBox()] --------------- Inside loadDialogBox() method() ------------------------");
		DemoController controller = null;
		ven.clear();
		try {
			setProductFlag(true);
			setProduct_name(null);
			setCategory(null);
			setBrand(null);
			setProductlimit(null);
			setProduct_standard(null);
			setAutual_price(null);
			setSize_of_product(null);
			setMarket_price(null);
			setDescription(null);
			setUnit(null);
			setIndustry(null);
			setValidate(null);
			setInvalidate2(null);
			setProductSuccessFlag(false);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setVen(controller.dropD(ven));
			setCategorytype(controller.categorylist(categorytype));

		} catch (Exception e) {
			logger.error("Inside Exception", e);
			setProductFlag(false);
			setProductSuccessFlag(false);
		}

		return "";

	}
	// prema end 28/04/2016
	public void categoryValueChange(ValueChangeEvent vc){
		logger.info("[categoryValueChange()] --------------- Inside categoryValueChange() method() ------------------------");
		String str="";
		try{
			str=vc.getNewValue().toString();
			if(str.equals("addnew")){
				reset1();
				RequestContext.getCurrentInstance().execute("PF('categoryRegDialog').show();");
			}
		}catch(Exception e){
			logger.error("Inside Exception", e);
		}
	}
	CategoryRegistration categoryReg=new CategoryRegistration();
	
	public CategoryRegistration getCategoryReg() {
		return categoryReg;
	}

	public void setCategoryReg(CategoryRegistration categoryReg) {
		this.categoryReg = categoryReg;
	}

	public String reset1(){
		logger.info("[reset1()] --------------- Inside reset1() method() ------------------------");
		setCategory1("");
		setValidate2("");
		setDescription1("");
		return "";
	}
	public String categoryregister() {
		logger.info("[categoryregister()] --------------- Inside categoryregister() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;String status="Fail";
		try {
			setValidate2("");
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");

			if (category1.equalsIgnoreCase("")) {
				throw new Exception("Enter the Category Type");
			}

			else if (!CommonValidate.validateName(category1)) {
				throw new DemoException(" Category Type should be in alphabets");
			} else {
				categoryReg.setCategory(category1);
				categoryReg.setIndustry(industry);
				categoryReg.setDescription(description1);
				status=controller.categoryType(categoryReg);
				if(status.equalsIgnoreCase("Success")){
					setCategorytype(controller.categorylist(categorytype));
					Collections.sort(categorytype, String.CASE_INSENSITIVE_ORDER);
					setCategory(category1);
					RequestContext.getCurrentInstance().execute("PF('categoryRegDialog').hide();");
				}
			}
			return "";

		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
			setValidate2(e.getMessage());
			return "";
		} finally {

		}
	}
	public void value1(ValueChangeEvent v) {
		logger.info("[value1()] --------------- Inside value1() method() ------------------------");
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
				if (categ.get(i).equalsIgnoreCase(v.getNewValue().toString())) {
					cnt++;
				}
			}
			if (cnt <= 0) {
				throw new Exception("This Category Type Available for Registration.");

			} else {
				setCategory1(v.getNewValue().toString());
				throw new Exception("This Category Type Already Registered.");
			}

		} catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
			setValidate2(e.getMessage());
			e.getMessage();
		} finally {

		}

	}
}
