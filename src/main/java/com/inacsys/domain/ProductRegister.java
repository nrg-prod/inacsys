package com.inacsys.domain;

/*import java.sql.Date;*/
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

import org.primefaces.model.UploadedFile;

import com.inacsys.managedBean.UploadedImage;
import com.inacsys.shared.I0001;
import com.inacsys.shared.I0002;
import com.inacsys.shared.I0004;
import com.inacsys.shared.I0005;
import com.inacsys.shared.I0006;
import com.inacsys.shared.I0025;
import com.inacsys.shared.I0031;

public class ProductRegister {
	public List<I0001> auto;
	public int product_ID;
	public String productName = null;
	public String newProductName;
	public String autual_price;
	public String market_price;
	public String sellingPrice;
	public String margin_price;
	public String vendor;
	public List<I0031> join;
	public String actual;
	public String selling;
	private String rollID;
	public String productlimit;
	public int quantity;
	public String unit;
	public String approval;
	
	
	
	String stockinDate;

	
	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getNewProductName() {
		return newProductName;
	}

	public void setNewProductName(String newProductName) {
		this.newProductName = newProductName;
	}

	public String getStockinDate() {
		return stockinDate;
	}

	public void setStockinDate(String stockinDate) {
		this.stockinDate = stockinDate;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductlimit() {
		return productlimit;
	}

	public void setProductlimit(String productlimit) {
		this.productlimit = productlimit;
	}

	public String barcode;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public String getSelling() {
		return selling;
	}

	public void setSelling(String selling) {
		this.selling = selling;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public List<I0031> getJoin() {
		return join;
	}

	public void setJoin(List<I0031> join) {
		this.join = join;
	}

	public List<I0001> getAuto() {
		return auto;
	}

	public void setAuto(List<I0001> auto) {
		this.auto = auto;
	}

	public Date expired_date;
	public Date dates;

	public Date getDates() {
		return dates;
	}

	public void setDates(Date dates) {
		this.dates = dates;
	}

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

	public List<I0001> i0001s;

	public List<I0001> getI0001s() {
		return i0001s;
	}

	public void setI0001s(List<I0001> i0001s) {
		this.i0001s = i0001s;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public int getProduct_ID() {
		return product_ID;
	}

	public void setProduct_ID(int product_ID) {
		this.product_ID = product_ID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String date;

	public String getDate() {
		return date;
	}

	public void setDate(String todayDate) {
		this.date = todayDate;
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

	List<I0004> typeparent = null;

	public List<I0004> getTypeparent() {
		return typeparent;
	}

	public void setTypeparent(List<I0004> typeparent) {
		this.typeparent = typeparent;
	}

	List<I0002> productgroup = null;
	List<I0006> industryList = null;
	List<I0025> ven = null;

	public List<I0025> getVen() {
		return ven;
	}

	public void setVen(List<I0025> ven) {
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

	/* udhaya 31.12.2014 */
	public String category;
	public List<I0001> datalist = null;
	public List<I0005> join1;

	public List<I0005> getJoin1() {
		return join1;
	}

	public void setJoin1(List<I0005> join1) {
		this.join1 = join1;
	}

	public List<I0001> getDatalist() {
		return datalist;
	}

	public void setDatalist(List<I0001> datalist) {
		this.datalist = datalist;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	/* 6.1.2015 udhaya */
	public String refcode;

	public String getRefcode() {
		return refcode;
	}

	public void setRefcode(String refcode) {
		this.refcode = refcode;
	}

	/* udhaya 19.2.2015 */
	public List<I0001> name = null;
	public Date stockdate;
	public String limit;
	public int counter;

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public Date getStockdate() {
		return stockdate;
	}

	public void setStockdate(Date stockdate) {
		this.stockdate = stockdate;
	}

	public List<I0001> getName() {
		return name;
	}

	public void setName(List<I0001> name) {
		this.name = name;
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

	public String getRollID() {
		return rollID;
	}

	public void setRollID(String rollID) {
		this.rollID = rollID;
	}

	BigDecimal actual1 = BigDecimal.valueOf(0);
	BigDecimal market1 = BigDecimal.valueOf(0);

	public BigDecimal getMarket1() {
		return market1;
	}

	public void setMarket1(BigDecimal market1) {
		this.market1 = market1;
	}

	public BigDecimal getActual1() {
		return actual1;
	}

	public void setActual1(BigDecimal actual1) {
		this.actual1 = actual1;
	}

	UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	private String imagepath;

	public String getImagepath() {
		return this.imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public List<ProductRegister> domain1 = new ArrayList<ProductRegister>();

	public List<ProductRegister> getDomain1() {
		return domain1;
	}

	public void setDomain1(List<ProductRegister> domain1) {
		this.domain1 = domain1;
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

	private ArrayList<UploadedImage> files = new ArrayList<UploadedImage>();

	public ArrayList<UploadedImage> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<UploadedImage> files) {
		this.files = files;
	}

	private ArrayList<UploadedImage> ll = new ArrayList<UploadedImage>();

	public ArrayList<UploadedImage> getLl() {
		return ll;
	}

	public void setLl(ArrayList<UploadedImage> ll) {
		this.ll = ll;
	}

	public List<ProductRegister> domain2 = new ArrayList<ProductRegister>();

	public List<ProductRegister> getDomain2() {
		return domain2;
	}

	public void setDomain2(List<ProductRegister> domain2) {
		this.domain2 = domain2;
	}

	OutputStream oo;

	public OutputStream getOo() {
		return oo;
	}

	public void setOo(OutputStream oo) {
		this.oo = oo;
	}

	public String priroty = "false";

	public String getPriroty() {
		return priroty;
	}

	public void setPriroty(String priroty) {
		this.priroty = priroty;
	}

	public String desc;
	public String no;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<ProductRegister> domain3 = new ArrayList<ProductRegister>();

	public List<ProductRegister> getDomain3() {
		return domain3;
	}

	public void setDomain3(List<ProductRegister> domain3) {
		this.domain3 = domain3;
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

}
