package com.inacsys.managedBean;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.jfree.ui.Align;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.ProductRegister;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0001;
import com.inacsys.shared.I0031;
import com.inacsys.util.CommonValidate;
import com.inacsys.util.GenerateEmployee;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/*import com.itextpdf.text.Document;
 import com.itextpdf.text.PageSize;
 import com.itextpdf.text.Paragraph;
 import com.itextpdf.text.Rectangle;
 import com.itextpdf.text.pdf.Barcode128;
 import com.itextpdf.text.pdf.PdfWriter;
 */
/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "productViewMB")
public class ProductViewMB {
	public String productcode;

	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	public String productName;
	List<I0001> i0001s;
	public int product_ID;
	public String flag = "none";
	public String flag2 = "none";
	public String id;
	public String like;
	public String price;
	public String firmName;
	public String frimPhoneNumber;
	public String autual_price;
	public String market_price;
	public String sellingPrice;
	public String margin_price;

	public Date expired_date;
	public String batch;
	public String description;
	public String color;
	public String size_of_product;
	public String product_standard;
	public String brand;
	public String product_weight;
	public String ideal_for;
	public String Standard;
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
	public String vendor;
	public String validate;
	public String barvalid;
	public String quantiy;
	public String unit;
	public String productlimit;
	public String approvalStatus;
	public String userType;
	private boolean categoryCheck=false;
	private boolean productCheck=false;
	private String approveButtonFlag="none";
	
	public boolean isProductCheck() {
		return productCheck;
	}

	public void setProductCheck(boolean productCheck) {
		this.productCheck = productCheck;
	}

	public boolean isCategoryCheck() {
		return categoryCheck;
	}

	public void setCategoryCheck(boolean categoryCheck) {
		this.categoryCheck = categoryCheck;
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

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	private List<ProductViewMB> categoryList=new ArrayList<ProductViewMB>();
	
	public List<ProductViewMB> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<ProductViewMB> categoryList) {
		this.categoryList = categoryList;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getProductlimit() {
		return productlimit;
	}

	public void setProductlimit(String productlimit) {
		this.productlimit = productlimit;
	}

	public String getQuantiy() {
		return quantiy;
	}

	public void setQuantiy(String quantiy) {
		this.quantiy = quantiy;
	}

	public String getBarvalid() {
		return barvalid;
	}

	public void setBarvalid(String barvalid) {
		this.barvalid = barvalid;
	}

	private static Logger logger = Logger.getLogger(ProductViewMB.class);

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getFrimPhoneNumber() {
		return frimPhoneNumber;
	}

	public void setFrimPhoneNumber(String frimPhoneNumber) {
		this.frimPhoneNumber = frimPhoneNumber;
	}

	public ArrayList<ProductViewMB> finalList = new ArrayList<ProductViewMB>();
	public ArrayList<ProductViewMB> finalList1 = new ArrayList<ProductViewMB>();

	public ArrayList<ProductViewMB> getFinalList() {
		return finalList;
	}

	public void setFinalList(ArrayList<ProductViewMB> finalList) {
		this.finalList = finalList;
	}

	public ArrayList<ProductViewMB> getFinalList1() {
		return finalList1;
	}

	public void setFinalList1(ArrayList<ProductViewMB> finalList1) {
		this.finalList1 = finalList1;
	}

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getProduct_ID() {
		return product_ID;
	}

	public void setProduct_ID(int product_ID) {
		this.product_ID = product_ID;
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

	public String getIndustry_ID() {
		return industry_ID;
	}

	public void setIndustry_ID(String industry_ID) {
		this.industry_ID = industry_ID;
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

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public ProductRegister getProductRegister() {
		return productRegister;
	}

	public void setProductRegister(ProductRegister productRegister) {
		this.productRegister = productRegister;
	}

	public DemoController getController() {
		return controller;
	}

	public void setController(DemoController controller) {
		this.controller = controller;
	}

	public String getStandard() {
		return Standard;
	}

	public void setStandard(String standard) {
		Standard = standard;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public List<I0001> getI0001s() {
		return i0001s;
	}

	public void setI0001s(List<I0001> i0001s) {
		this.i0001s = i0001s;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}
	
	public Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	ProductRegister productRegister = new ProductRegister();
	DemoController controller = null;

	List<String> productList = null;

	public List<String> getProductList() {
		return productList;
	}

	public void setProductList(List<String> productList) {
		this.productList = productList;
	}

	public String success() {
		logger.info("[success()] --------------- Inside success() method()------------------------");
		try {
			flag = "none";
			flag1 = "none";
			flag2 = "none";
			setValidate(null);
			setMessage(null);
			productRegister.setProductName("");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			/* i0001s=controller.productView1(i0001s,productRegister); */
			// prema begin 27/04/2016 product drop box
			productList = controller.getproductListInfo(productRegister);
			// prema end 27/04/2016
			if (i0001s.size() > 0) {
				if (productRegister.getJoin().size() > 0) {
					logger.info("[success()] --------------- Inside success() method() Join If------------------------");
					int count = 0;
					finalList.clear();
					for (I0031 result : productRegister.getJoin()) {
						logger.info("[success()] --------------- Inside success() method() if condition 1------------------------");
						ProductViewMB productViewMB = new ProductViewMB();
						productViewMB.setProductName(i0001s.get(count)
								.getProductName());
						productViewMB.setProduct_ID(i0001s.get(count)
								.getProduct_ID());
						productViewMB.setBatch(i0001s.get(count).getBatch());
						productViewMB.setPrice(i0001s.get(count)
								.getSellingPrice());
						productViewMB.setSize_of_product(i0001s.get(count)
								.getSizeOfProduct());
						productViewMB.setCategoryname(result.getI0001()
								.getI0005().getCategoryType());
						// productViewMB.setFirmName(result.getI0025().getFirmName());
						productViewMB.setBrand(i0001s.get(count).getBrand());
						productViewMB.setActual(new BigDecimal(i0001s
								.get(count).getAutualPrice()));
						productViewMB.setAutual_price(i0001s.get(count)
								.getAutualPrice());
						productViewMB
								.setDate(i0001s.get(count).getCreateDate());
						productViewMB.setUnit(i0001s.get(count).getUnit());
						productViewMB.setProductlimit(i0001s.get(count)
								.getProductWeight());
						// productViewMB.setFrimPhoneNumber(result.getI0025().getVendorPhoneNumber());
						logger.info("[success()] --------------- Inside success() method() If condition 2------------------------");
						finalList.add(productViewMB);
						count++;
					}
					if (finalList.size() > 0) {
						for (int i = 0; i < finalList.size(); i++) {
							finalList.get(i).setPrice(GenerateEmployee.numberFormat.format(new BigDecimal(finalList.get(i).getPrice())));
						}
					}
					flag = "none";
					flag1 = "none";
				}
			}
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			logger.error("error---->" + e.getMessage());
		} finally {
			like = "";
			categoryname = "";
		}
		return "";
	}

	public String successbarcode() {
		logger.info("[successbarcode()] --------------- Inside successbarcode() method() ------------------------");
		try {
			like = "";
			categoryname = "";
			barcode2 = "";
			flag = "none";
			setValidate(null);
			setCategoryname("");
			productRegister.setProductName("");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			if (i0001s.size() > 0) {
				if (productRegister.getJoin().size() > 0) {
					logger.info("[successbarcode()] --------------- Inside join if ------------------------");
					int count = 0;
					finalList.clear();
					for (I0031 result : productRegister.getJoin()) {
						logger.info("[successbarcode()] --------------- Inside join if 1------------------------");
						ProductViewMB productViewMB = new ProductViewMB();
						productViewMB.setProductName(i0001s.get(count)
								.getProductName());
						productViewMB.setProduct_ID(i0001s.get(count)
								.getProduct_ID());
						productViewMB.setBatch(i0001s.get(count).getBatch());
						productViewMB.setPrice(i0001s.get(count)
								.getSellingPrice());
						productViewMB.setSize_of_product(i0001s.get(count)
								.getSizeOfProduct());
						productViewMB.setCategoryname(result.getI0001()
								.getI0005().getCategoryType());
						productViewMB.setBrand(i0001s.get(count).getBrand());
						productViewMB.setAutual_price(i0001s.get(count)
								.getAutualPrice());
						productViewMB
								.setDate(i0001s.get(count).getCreateDate());
						productViewMB
								.setBarcode(i0001s.get(count).getBarCode());
						logger.info("[successbarcode()] --------------- Inside join if 2------------------------");
						finalList.add(productViewMB);
						count++;
					}

					flag = "none";
					flag1 = "none";
				}
			}
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
			logger.error("error---->" + e.getMessage());
		} finally {

		}
		return "productb";
	}

	public String productEdit() {
		logger.info("[productEdit()] --------------- Inside productEdit() method() ------------------------");
		try {
			flag = "none";
			validate = null;
			productRegister.setProductName(productName);
			productRegister.setProduct_ID(product_ID);
			productRegister.setNewProductName(productName);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			i0001s = controller.productView(i0001s, productRegister);
			if (i0001s.size() > 0) {
				flag = "1";
				setVendor(productRegister.getVendor());
				productName = i0001s.get(0).getProductName();
				product_ID = i0001s.get(0).getProduct_ID();
				autual_price = i0001s.get(0).getAutualPrice();
				// margin_price=i0001s.get(0).getMarginPrice();
				sellingPrice = i0001s.get(0).getSellingPrice();
				market_price = i0001s.get(0).getMarketPrice();
				date = i0001s.get(0).getCreateDate();
				// expired_date=i0001s.get(0).getExpiredDate();
				batch = i0001s.get(0).getBatch();
				description = i0001s.get(0).getDescription();
				color = i0001s.get(0).getColor();
				brand = i0001s.get(0).getBrand();
				size_of_product = i0001s.get(0).getSizeOfProduct();
				categoryname = i0001s.get(0).getI0005().getCategoryType();
				ideal_for = i0001s.get(0).getIdealFor();
				product_standard = i0001s.get(0).getProductStandard();
				productlimit = i0001s.get(0).getProductWeight();
				unit = i0001s.get(0).getUnit();
				flag = "none";
			}
			return "";
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.debug("error-------------->" + e.getMessage());
			return "";
		} finally {

		}
	}

	public String productEditSave() {
		logger.info("[productEditSave()] --------------- Inside productEditSave() method() ------------------------");
		try {
			productRegister.setIndustry(industry);
			productRegister.setProduct_ID(product_ID);
			productRegister.setProductName(productName);
			productRegister.setAutual_price(autual_price);
			productRegister.setMargin_price(autual_price);
			productRegister.setSellingPrice(market_price);
			productRegister.setMarket_price(market_price);
			// productRegister.setDate(date);
			productRegister.setExpired_date(expired_date);
			logger.debug("batch---------------->" + batch);
			productRegister.setBatch(batch);
			productRegister.setDescription(description);
			productRegister.setColor(color);
			productRegister.setSize_of_product(size_of_product);
			productRegister.setCategory(categoryname);
			productRegister.setProduct_standard(product_standard);
			productRegister.setBrand(brand);
			// productRegister.setProduct_weight(product_weight);
			productRegister.setIdeal_for(ideal_for);
			// productRegister.setType_parent(type_parent);
			// productRegister.setProduct_group(product_group);
			// productRegister.setIndustry_ID(industry_ID);
			productRegister.setMechanical(mechanical);
			productRegister.setSoftware(software);
			productRegister.setElectrical(electrical);
			productRegister.setElectronics(electronics);
			productRegister.setBiomedical(biomedical);
			productRegister.setCivil(civil);
			productRegister.setFood(food);
			productRegister.setVendor(vendor);
			productRegister.setProductlimit(productlimit);
			productRegister.setUnit(unit);
			if (categoryname.equalsIgnoreCase("select")) {
				throw new Exception("Please Choose the Category Type");
			} else if (productName.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Product Name");
			} else if (productlimit.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Product Code");
			}
			/*else if (size_of_product.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Product Size");
			}*/
			else if (autual_price.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Actual Price");
			} else if (!autual_price.matches("[0-9]+")) {
				throw new Exception("Autual Price should be in number");
			} else if (market_price.equalsIgnoreCase("")) {
				throw new Exception("Please Enter the Market Price");
			} else if (!market_price.matches("[0-9]+")) {
				throw new Exception("Market Price should ne in number");
			} else if (unit.equalsIgnoreCase("select")) {
				throw new Exception("Please Choose the Unit");
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.saveProductEdit(i0001s, productRegister);
			setValidate(null);
			RequestContext.getCurrentInstance().execute("PF('productUpdateconf').show();");
			return "";
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("log----------->" + e.getMessage());
			logger.error("Inside Exception", e);
			return "";
		}
	}

	public String productView() {
		logger.info("[productView()] --------------- Inside productView() method() ------------------------");
		try {
			flag = "none";
			if (productName.equalsIgnoreCase("Select")) {
				i0001s = null;
				throw new Exception("please select the product:::::");
			}
			productRegister.setProductName(getProductName());
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			i0001s = controller.productView(i0001s, productRegister);
			if (i0001s.size() > 0) {
				if (productRegister.getJoin().size() > 0) {
					logger.info("[productView()] --------------- Inside join if ------------------------");
					flag = "1";
				}
			}
			logger.info("--------------$$$$$$$$$$$$$$------------Outside productView mb-------------$$$$$$$$$$$$$$-----------");
			return "";
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("error---->" + e.getMessage());
			return "";
		} finally {
		}
	}

	/* udhaya 30.12.2014 */

	public String categoryname;

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String barcode;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	BigDecimal actual = BigDecimal.valueOf(0);

	/* jeni 12.1.2015 */

	public BigDecimal getActual() {
		return actual;
	}

	public void setActual(BigDecimal actual) {
		this.actual = actual;
	}
	
	public String serialno;
	

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String likeProductView() {
		logger.info("[likeProductView()] --------------- Inside likeProductView() method() ------------------------");
		try {
			setFilterList(null);
			int c=0;
			setValidate(null);
			setCategoryname(null);
			setMessage(null);
			finalList.clear();
			productRegister.setApproval(approvalStatus);
			String userID=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			i0001s = controller.productView1(i0001s, productRegister);
			flag = "none";
			flag1 = "none";
			flag2 = "none";
			if (i0001s.size() > 0) {
				if (productRegister.getJoin().size() > 0) {
					logger.info("[likeProductView()] --------------- Inside join if ------------------------");
					for (int i = 0; i < i0001s.size(); i++) {
						logger.info("[likeProductView()] --------------- Inside join if 1------------------------");
						ProductViewMB productViewMB = new ProductViewMB();
						productViewMB.setSerialno(String.valueOf(i+1));  
						productViewMB.setProductName(i0001s.get(i)
								.getProductName());
						productViewMB.setProduct_ID(i0001s.get(i)
								.getProduct_ID());
						// productViewMB.setBatch(i0001s.get(count).getBatch());
						productViewMB.setPrice(i0001s.get(i)
								.getSellingPrice());
						productViewMB.setFirmName(productRegister.getJoin().get(0).getI0025()
								.getFirmName());
						productViewMB.setSize_of_product(i0001s.get(i)
								.getSizeOfProduct());
						productViewMB.setCategoryname(i0001s.get(i)
								.getI0005().getCategoryType());
						productViewMB.setProductName(i0001s.get(i)
								.getProductName());
						productViewMB.setBrand(i0001s.get(i).getBrand());
						productViewMB.setAutual_price(i0001s.get(i)
								.getAutualPrice());
						productViewMB.setActual(new BigDecimal(i0001s
								.get(i).getAutualPrice()));
						productViewMB
								.setDate(i0001s.get(i).getCreateDate());
						productViewMB
								.setBarcode(i0001s.get(i).getBarCode());
						productViewMB.setFrimPhoneNumber(productRegister.getJoin().get(i).getI0025()
								.getVendorPhoneNumber());
						logger.info("[likeProductView()] --------------- Inside join if 2------------------------");
						productViewMB.setUnit(i0001s.get(i).getUnit());
						productViewMB.setProductlimit(i0001s.get(i)
								.getProductWeight());
						productViewMB.setApprovalStatus(i0001s.get(i).getApprovalStatus());
						productViewMB.setProduct_ID(i0001s.get(i).getProduct_ID());
						finalList.add(productViewMB);
						/*if(i0001s.get(i).getUserID().getUserNo()==Integer.parseInt(userID)){
							finalList.add(productViewMB);
						}else{
							if(i0001s.get(i).getApprovalStatus().equals("draft")){
								finalList.add(productViewMB);
							}
						}*/
						//finalList1.addAll(finalList);
						if(i0001s.get(i).getApprovalStatus().equals("draft")){
							c++;
						}
					}
					if(c==0){
						approveButtonFlag="none";
					}else{
						approveButtonFlag="1";
					}
					flag = "1";
					flag1 = "none";
					flag2 = "none";
				}

			} else {
				flag = "none";
				flag1 = "none";
				flag2 = "1";
			}
			if (finalList.size() > 0) {
				for (int i = 0; i < finalList.size(); i++) {
					finalList.get(i).setActual(new BigDecimal(GenerateEmployee.numberFormat.format(new BigDecimal(finalList.get(i).getAutual_price()))));
					finalList.get(i).setPrice(GenerateEmployee.numberFormat.format(new BigDecimal(finalList.get(i).getPrice())));
				}
			}

			return "";
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception"+e.getMessage());
			return "";
		}

		finally {

		}
	}

	public String cancel() {
		logger.info("[cancel()] --------------- Inside cancel() method() ------------------------");
		flag = "none";
		flag1 = "none";
		flag2 = "none";
		productName = null;
		like = null;
		validate = null;
		categoryname = null;
		return "productView";

	}

	public String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void paint2(OutputStream out, Object data) throws IOException {
		logger.info("[paint2()] --------------- Inside paint2() method() ------------------------");
		String s = "C://product/";
		BufferedImage img = ImageIO.read(new File(s + "" + fileName));
		ImageIO.write(img, "png", out);
	}

	public String productReject() {
		logger.info("[productReject()] --------------- Inside productReject() method() ------------------------");
		try {
			flag = "none";
			validate = null;
			productRegister.setProductName(productName);
			productRegister.setProduct_ID(product_ID);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			i0001s = controller.productView(i0001s, productRegister);
			if (i0001s.size() > 0) {
				flag = "1";
				productName = i0001s.get(0).getProductName();
				product_ID = i0001s.get(0).getProduct_ID();
				autual_price = GenerateEmployee.numberFormat
						.format(new BigDecimal(i0001s.get(0).getAutualPrice()));
				// margin_price=i0001s.get(0).getMarginPrice();
				sellingPrice = GenerateEmployee.numberFormat
						.format(new BigDecimal(i0001s.get(0).getSellingPrice()));
				market_price = GenerateEmployee.numberFormat
						.format(new BigDecimal(i0001s.get(0).getMarketPrice()));
				date = i0001s.get(0).getCreateDate();
				// expired_date=i0001s.get(0).getExpiredDate();
				description = i0001s.get(0).getDescription();
				color = i0001s.get(0).getColor();
				brand = i0001s.get(0).getBrand();
				size_of_product = i0001s.get(0).getSizeOfProduct();
				categoryname = i0001s.get(0).getI0005().getCategoryType();
				ideal_for = i0001s.get(0).getIdealFor();
				product_standard = i0001s.get(0).getProductStandard();
				vendor = productRegister.getVendor();
				productlimit = i0001s.get(0).getProductWeight();
				unit = i0001s.get(0).getUnit();
				flag = "none";
				setDesc(productRegister.getDesc());
				setFileName(productRegister.getFileName());
			}
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
			logger.error("error----------->" + e.getMessage());
			return "";
		} finally {

		}
		return "";
	}

	public String productRemove() {
		logger.info("[productRemove()] --------------- Inside productRemove() method() ------------------------");
		try {
			flag = "none";
			validate = null;
			productRegister.setProductName(productName);
			productRegister.setProduct_ID(product_ID);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			i0001s = controller.productView(i0001s, productRegister);
			if (i0001s.size() > 0) {
				flag = "1";
				productName = i0001s.get(0).getProductName();
				product_ID = i0001s.get(0).getProduct_ID();
				autual_price = GenerateEmployee.numberFormat
						.format(new BigDecimal(i0001s.get(0).getAutualPrice()));
				sellingPrice = i0001s.get(0).getSellingPrice();
				market_price = GenerateEmployee.numberFormat
						.format(new BigDecimal(i0001s.get(0).getMarketPrice()));
				date = i0001s.get(0).getCreateDate();
				batch = i0001s.get(0).getBatch();
				description = i0001s.get(0).getDescription();
				color = i0001s.get(0).getColor();
				brand = i0001s.get(0).getBrand();
				size_of_product = i0001s.get(0).getSizeOfProduct();
				categoryname = i0001s.get(0).getI0005().getCategoryType();
				ideal_for = i0001s.get(0).getIdealFor();
				product_standard = i0001s.get(0).getProductStandard();
				vendor = productRegister.getVendor();
				productlimit = i0001s.get(0).getProductWeight();
				unit = i0001s.get(0).getUnit();
				flag = "none";
			}
			return "";
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("error----" + e.getMessage());
			return "";
		} finally {

		}
	}

	public String reject1() {
		logger.info("[reject1()] --------------- Inside reject1() method() ------------------------");
		try {
			productRegister.setProductName(productName);
			productRegister.setProduct_ID(product_ID);
			if (productName.equalsIgnoreCase("")) {
				throw new DemoException("* the product name not found:::::::");
			}
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.productReject(productRegister);
			return "success1";
		} catch (DemoException ie) {
			logger.error("erorr----->" + ie.getMessage());
			return "";
		} finally {
			productName = null;
			product_ID = 0;
			autual_price = "";
			margin_price = "";
			sellingPrice = "";
			market_price = "";
			date = null;
			expired_date = null;
			batch = null;
			description = null;
			color = null;
			brand = null;
			size_of_product = null;
			ideal_for = null;
			type_parent = null;
			product_group = null;
			industry_ID = null;
			i0001s = null;
			flag = "none";
		}
	}

	public String remove1() {
		logger.info("[remove1()] --------------- Inside remove1() method() ------------------------");
		try {
			productRegister.setProductName(productName);
			productRegister.setProduct_ID(product_ID);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.productRemove(productRegister);
			RequestContext.getCurrentInstance().execute(
					"PF('prodelete').hide();");
			RequestContext.getCurrentInstance().execute(
					"PF('productDeleteconf').show();");
			return "";
		} catch (DemoException ie) {
			logger.error("erorr------->" + ie.getMessage());
			return "";
		} finally {
			product_ID = 0;
			autual_price = "";
			margin_price = "";
			market_price = "";
			date = null;
			expired_date = null;
			batch = null;
			description = null;
			color = null;
			brand = null;
			size_of_product = null;
			ideal_for = null;
			type_parent = null;
			product_group = null;
			industry_ID = null;
			i0001s = null;
		}
	}

	/* udhaya 31.12.2014 */
	public List<I0001> datalist = null;
	public String flag1 = "none";

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public List<I0001> getDatalist() {
		return datalist;
	}

	public void setDatalist(List<I0001> datalist) {
		this.datalist = datalist;
	}

	public String categorysearch() {
		logger.info("[categorysearch()] --------------- Inside categorysearch() method() ------------------------");
		setLike(null);
		ApplicationContext ctx = null;
		DemoController controller = null;
		try {
			setValidate(null);

			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			setDatalist(productRegister.getDatalist());
			productRegister.setCategory(categoryname);
			if (categoryname.equalsIgnoreCase("")) {
				throw new Exception("Please Choose the Category");
			}
			flag1 = "none";
			flag = "none";
			flag2 = "none";
			controller.category(productRegister);
			if (productRegister.getDatalist().size() > 0) {
				setDatalist(productRegister.getDatalist());
				for (int i = 0; i < datalist.size(); i++) {
					datalist.get(i).setAutualPrice(
							GenerateEmployee.numberFormat
									.format(new BigDecimal(datalist.get(i)
											.getAutualPrice())));
					datalist.get(i).setSellingPrice(
							GenerateEmployee.numberFormat
									.format(new BigDecimal(datalist.get(i)
											.getSellingPrice())));
				}
				flag1 = "1";
				flag2 = "none";
				flag = "none";
				setValidate(null);
			} else {
				logger.debug("datalist has no value");
				flag2 = "1";
				flag = "none";
				flag1 = "none";
			}
		}
		catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("Inside Exception", e);
			logger.debug("category non msg::" + e.getStackTrace());
		} finally {

		}
		return "";
	}

	ArrayList<String> barcd = new ArrayList<String>();

	public ArrayList<String> getBarcd() {
		return barcd;
	}

	public void setBarcd(ArrayList<String> barcd) {
		this.barcd = barcd;
	}

	UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void TransferFile(String fileName, InputStream in)
			throws DemoException {
		logger.info("[transferfile()] --------------- Inside transferfile() method() ------------------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			OutputStream out = new FileOutputStream(new File("c:/" + ""
					+ fileName));
			int reader = 0;
			byte[] bytes = new byte[(int) getFile().getSize()];
			while ((reader = in.read(bytes)) != -1) {
				out.write(bytes, 0, reader);
			}
			in.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public String barcode1() {
		logger.info("[barcode1()] --------------- Inside barcode1() method() ------------------------");
		setBarvalid("");
		DemoController controller = null;
		try {
			productRegister.setProductName(productName);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			i0001s = controller.productView(i0001s, productRegister);
			productcode = i0001s.get(0).getProductWeight();
			price = i0001s.get(0).getMarginPrice();
			color = i0001s.get(0).getColor();
			size_of_product = i0001s.get(0).getSizeOfProduct();
			brand = i0001s.get(0).getBrand();
			if (barcode2.equals("")) {
				throw new Exception("Please Enter the Quantity");
			} else if (!barcode2.equals("")) {
				if (!barcode2.matches("[-+]?[0-9]*\\.?[0-9]+")) {
					throw new Exception("Quantity Should be in Numeric");
				}
				if (Integer.parseInt(barcode2) != 30) {
					throw new Exception("Quantity must be 30");
				}
			}
			String prodName = productName;
			if (productName.length() > 13) {
				prodName = productName.substring(0, 13) + "..";
			}
			File dir = new File("c:\\barcode");
			dir.mkdir();
			File f = null;
			boolean bool = false;
			// create new file
			f = new File("c:\\barcode\\" + productName + ".pdf");
			// tries to create new file in the system
			bool = f.createNewFile();
			// prints
			// deletes file from the system
			f.delete();
			// delete() is invoked
			// tries to create new file in the system
			bool = f.createNewFile();
			// print
			logger.debug("File created: " + bool);
			barcd.clear();
			Document document = new Document(new Rectangle(PageSize.A4));
			document.setMargins(7f, 7f, 7f, 7f);
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("c:/barcode/" + productName + ".pdf"));
			document.open();
			Barcode128 code12 = new Barcode128();
			code12.setGenerateChecksum(true);
			code12.setCode(barcode);
			Image img = code12.createImageWithBarcode(
					writer.getDirectContent(), null, null);
			img.setWidthPercentage(60);
			PdfPTable table1 = new PdfPTable(5);
			table1.setWidthPercentage(100);
			table1.setHorizontalAlignment(Align.CENTER);
			for (int i = 0; i < Integer.parseInt(barcode2); i++) {
				PdfPCell cell11 = new PdfPCell();
				cell11.setPaddingLeft(8f);
				cell11.setPaddingBottom(6f);
				cell11.setBorder(Rectangle.NO_BORDER);
				cell11.addElement(new Phrase("  ", FontFactory.getFont(
						FontFactory.COURIER, 2f)));

				cell11.addElement(new Phrase(productcode, FontFactory.getFont(
						FontFactory.COURIER_BOLD, 7f)));
				cell11.addElement(new Phrase(prodName, FontFactory.getFont(
						FontFactory.COURIER_BOLD, 7f)));
				cell11.addElement(new Phrase("Rp " + price, FontFactory
						.getFont(FontFactory.COURIER_BOLD, 7f)));
				cell11.addElement(new Phrase("  ", FontFactory.getFont(
						FontFactory.COURIER, 3f)));
				img.setAlignment(Image.LEFT);
				cell11.addElement(img);
				cell11.setVerticalAlignment(Element.ALIGN_JUSTIFIED_ALL);
				cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.addCell(cell11);

				logger.debug("count--------->" + i);
				barcd.add(barcode);
			}
			document.add(table1);
			// second
			logger.debug("1");
			document.close();
			return "barcodegenerate";
		}
		catch (Exception e) {
			setBarvalid(e.getMessage());
			logger.debug("=====================exception================="+ e.getMessage());
			return "";
		}

	}

	public StreamedContent getdFile() throws IOException {
		logger.info("[getdFile()] --------------- Inside getdFile() method() ------------------------");
		String contentType;
		try {
			fileName = productName + ".pdf";
			logger.info("------------inside dfile2---------------->");
			DemoController controller = null;
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			contentType = FacesContext.getCurrentInstance()
					.getExternalContext()
					.getMimeType("c:\\barcode\\" + fileName);
			return new DefaultStreamedContent(new FileInputStream(
					"c:\\barcode\\" + fileName), contentType, fileName);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return null;
		}
	}

	public void barcode3(ValueChangeEvent v) {
		logger.info("[barcode3()] --------------- Inside barcode3() method() ------------------------");
		try {
			barcode2 = "" + v.getNewValue();
			if (barcode2.equals("")) {
				throw new Exception("Please Enter the Quantity");
			}
		} catch (Exception e) {
			setBarvalid(e.getMessage());
			logger.error("Inside Exception", e);
		}
	}
	public String barcode2;

	public String getBarcode2() {
		return barcode2;
	}

	public void setBarcode2(String barcode2) {
		this.barcode2 = barcode2;
	}

	public String openingStock;
	public String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOpeningStock() {
		return openingStock;
	}

	public void setOpeningStock(String openingStock) {
		this.openingStock = openingStock;
	}

	public boolean openingFlag = false;

	public boolean isOpeningFlag() {
		return openingFlag;
	}

	public void setOpeningFlag(boolean openingFlag) {
		this.openingFlag = openingFlag;
	}

	public String openingStocks() {
		logger.info("[openingStocks()] --------------- Inside openingStocks() method() ------------------------");
		ApplicationContext ctx = null;
		DemoController controller = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date d = new Date();
		try {
			openingFlag = false;
			setMessage("");
			if (openingStock.equals("")) {
				throw new DemoException("Enter the Opening Stock");
			} else if (!openingStock.matches("[0-9]*\\.?[0-9]+")) {
				throw new DemoException("Opening Stock Must be Numeric");
			}
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			PurchaseOrder opngstock = new PurchaseOrder();
			opngstock.setOpeningStock(openingStock);
			opngstock.setProduct_name(productName);
			opngstock.setActualPrice(autual_price.replace(",", ""));
			opngstock.setDeliveredDate(d);
			controller.openingStockInsert(opngstock);
			openingStock = null;
			throw new DemoException("Opening Stock Added Succesfully");
		} catch (DemoException e) {
			logger.error("inside exception ",e);
			setMessage(e.getMessage());
			return "";
		}

	}
	private Map<String, SortOrder> sortsOrders;
	private List<String> sortPriorities;
	private static final String SORT_PROPERTY_PARAMETER = "sortProperty";
	private boolean multipleSorting = false;

	public ProductViewMB() {
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

	// prema begin 02/05/2016 dialog box creation for product view

	public void getproductView() {
		logger.info("[getproductView()] --------------- Inside getproductView() method() ------------------------");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 800);
		RequestContext.getCurrentInstance().openDialog("productView", options,
				null);
		success();

	}

	// prema end 02/05/2016

	public void productviewclose() {
		logger.info("[productviewclose()] --------------- Inside productviewclose() method() ------------------------");
		RequestContext.getCurrentInstance().closeDialog("productView");
	}
	public void onrowselect(final SelectEvent event){
		logger.info("[onrowselect()] --------------- Inside onrowselect() method() ------------------------");
		logger.debug("inside row select");
		productName = ((ProductViewMB) event.getObject()).getProductName();
		logger.debug("productName==="+productName);
		productReject();
	}
	
	ArrayList<ProductViewMB> filterList;
	public ArrayList<ProductViewMB> getFilterList() {
		return filterList;
	}

	public void setFilterList(ArrayList<ProductViewMB> filterList) {
		this.filterList = filterList;
	}

	
	
	public String categoryView(){
		logger.info("[categoryView()] --------------- Inside categoryView() method() ------------------------");
		setFilterList(null);
		setValidate("");
		ApplicationContext ctx = null;
		DemoController controller = null;
		int count=0;
		String userType=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userType");
		try{
			setUserType(userType);
			ctx = FacesContextUtils.getWebApplicationContext(FacesContext
					.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			categoryList=controller.getcategoryList(approvalStatus);
			for (int j = 0; j < categoryList.size(); j++) {
				if(categoryList.get(j).getApprovalStatus().equals("draft")){
					count++;
				}
			}
			if(count==0){
				approveButtonFlag="none";
			}else{
				approveButtonFlag="1";
			}
		}catch(Exception e){
			logger.error("Inside Exception"+e.getMessage());
		}
		return "categoryview";
	}
	
	//Category approval
	
		public String categoryApproval(){
			logger.info("[categoryApproval()] --------------- Inside categoryApproval() method() ------------------------");
			String status="";
			DemoController controller = null;
			int count=0;setValidate("");
			try{
				ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				for (int i = 0; i < categoryList.size(); i++) {
					if(categoryList.get(i).isCategoryCheck()==true){
						count++;
					}
				}
				if(count==0){
					throw new Exception("Please Choose atleast one row for Approval.");
				}else{
					setValidate("");
					status=controller.categoryApproval(categoryList);
					if(status.equalsIgnoreCase("Success")){
						RequestContext.getCurrentInstance().execute("PF('approvalConfirm').show()");
					}
				}
			}catch(Exception e){
				setValidate(e.getMessage());
				logger.error("Inside Exception"+e.getMessage());
			}
			return "";
		}
		
		public String categoryDetailsView(){
			logger.info("[categoryDetailsView()] --------------- Inside categoryDetailsView() method() ------------------------");
			DemoController controller = null;
			try{
				ApplicationContext ctx = FacesContextUtils
						.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				productRegister.setIndustry_ID(id);
				controller.getcategoryview(productRegister);
			}catch(Exception e){
				logger.error("Inside Exception"+e.getMessage());
			}
			return "";
		}
		public String categoryEditSave(){
			logger.info("[categoryEditSave()] --------------- Inside categoryEditSave() method() ------------------------");
			DemoController controller = null;
			String status="Fail";String fieldName;
			FacesContext fc = FacesContext.getCurrentInstance();
			try{
				ApplicationContext ctx = FacesContextUtils
						.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				System.out.println("category--"+productRegister.getCategory());
				if(productRegister.getCategory().equals("")){
					fieldName = CommonValidate.findComponentInRoot("catename").getClientId(fc);
					fc.addMessage(fieldName, new FacesMessage("Please Enter the Category"));
				}else{
					productRegister.setIndustry_ID(id);
					status=controller.categoryUpdate(productRegister);
					if(status.equalsIgnoreCase("Success")){
						RequestContext.getCurrentInstance().execute("PF('categoryDialogEdit').hide();");
						RequestContext.getCurrentInstance().execute("PF('updateConfirm').show();");
					}else if(status.equalsIgnoreCase("Exist")){
						fieldName = CommonValidate.findComponentInRoot("catename").getClientId(fc);
						fc.addMessage(fieldName, new FacesMessage("This Category Already Exist"));
						RequestContext.getCurrentInstance().execute("PF('updateConfirm').hide();");
					}
				}
			}catch(Exception e){
				logger.error("Inside Exception"+e.getMessage());
			}
			return "";
		}
		
		public String deleteCategory(){
			logger.info("[deleteCategory()] --------------- Inside deleteCategory() method() ------------------------");
			DemoController controller = null;String status="Fail";
			try{
				ApplicationContext ctx = FacesContextUtils
						.getWebApplicationContext(FacesContext.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				status=controller.deleteCategory(id);
				if(status.equalsIgnoreCase("Success")){
					RequestContext.getCurrentInstance().execute("PF('deleteonfirm').show();");
				}
			}catch(Exception e){
				logger.error("Inside Exception"+e.getMessage());
			}
			return "";
		}
		
		//Product approval
		
			public String productApproval(){
				logger.info("[productApproval()] --------------- Inside productApproval() method() ------------------------");
				String status="";DemoController controller = null;
				int count=0;setValidate("");
				try{
					ApplicationContext ctx = FacesContextUtils
							.getWebApplicationContext(FacesContext.getCurrentInstance());
					controller = (DemoController) ctx.getBean("controller");
					for (int i = 0; i < finalList.size(); i++) {
						if(finalList.get(i).isProductCheck()==true){
							count++;
						}
					}
					if(count==0){
						throw new Exception("Please Choose atleast one row for Approval.");
					}else{
						setValidate("");
						status=controller.productApproval(finalList);
						System.out.println("status"+status);
						if(status.equalsIgnoreCase("Success")){
							RequestContext.getCurrentInstance().execute("PF('approvalConfirm').show()");
						}
					}
				}catch(Exception e){
					setValidate(e.getMessage());
					logger.error("Inside Exception"+e.getMessage());
				}
				return "";
			}
}