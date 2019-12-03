package com.inacsys.managedBean;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.ProductRegister;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0001;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "productRejectFormMB")
public class ProductRejectFormMB {
	private static Logger logger = Logger.getLogger(ProductRejectFormMB.class);
	public String productName;
	List<I0001> i0001s;
	public String validate;
	public String productID;
	public String flag = "none";
	public String autual_price;
	public String market_price;
	public String sellingPrice;
	public String margin_price;
	public Date date;
	public Date expired_date;
	public String batch;
	public String description;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public int product_ID;

	public int getProduct_ID() {
		return product_ID;
	}

	public void setProduct_ID(int product_ID) {
		this.product_ID = product_ID;
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

	DemoController controller = null;
	ProductRegister productRegister = new ProductRegister();

	public String productReject() {
		logger.info("[productReject()] --------------- Inside productReject() method()------------------------");
		try {
			flag = "none";
			if (productName.equalsIgnoreCase("Select")) {
				i0001s = null;
				throw new Exception("please select the product");
			}
			if (productID.equalsIgnoreCase("")) {
				i0001s = null;
				throw new Exception("please enter the Id");
			}
			validate = null;
			productRegister.setProductName(productName);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			i0001s = controller.productView(i0001s, productRegister);
			if (i0001s.size() > 0) {
				flag = "1";
			}
			return "";
		} catch (Exception e) {
			setValidate(e.getMessage());
			logger.error("error----" + e.getMessage());
			return "";
		} finally {

		}
	}

	public String load() {
		logger.info("[load()] --------------- Inside load() method()------------------------");
		return "";
	}

	public String reject() {
		try {
			logger.info("[reject()] --------------- Inside reject() method()------------------------");
			productName = i0001s.get(0).getProductName();
			product_ID = i0001s.get(0).getProduct_ID();
			autual_price = i0001s.get(0).getAutualPrice();
			margin_price = i0001s.get(0).getMarginPrice();
			sellingPrice = i0001s.get(0).getSellingPrice();
			market_price = i0001s.get(0).getMarketPrice();
			date = i0001s.get(0).getCreateDate();
			expired_date = i0001s.get(0).getExpiredDate();
			batch = i0001s.get(0).getBatch();
			description = i0001s.get(0).getDescription();
			color = i0001s.get(0).getColor();
			brand = i0001s.get(0).getBrand();
			size_of_product = i0001s.get(0).getColor();
			ideal_for = i0001s.get(0).getIdealFor();
			type_parent = i0001s.get(0).getI0004().getName();
			product_group = i0001s.get(0).getI0002().getProductGroupName();
			industry_ID = i0001s.get(0).getI0006().getIndustryName();
			flag = "none";
		}
		catch (Exception e) {
			logger.error("Inside Exception"+e.getMessage());
		}
		return "success1";
	}

	public String reject1() {
		logger.info("[reject1()] --------------- Inside reject1() method()------------------------");
		try {
			productRegister.setProductName(productName);
			productRegister.setProduct_ID(product_ID);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.productReject(productRegister);
			return "success2";
		} catch (DemoException ie) {
			logger.error("erorr--" + ie.getMessage());
			return "failure2";
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
}
