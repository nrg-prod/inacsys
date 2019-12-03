package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the i0001 database table.
 * 
 */
@Entity
@Table(name = "i0001")
@NamedQuery(name = "I0001.findAll", query = "SELECT i FROM I0001 i")
public class I0001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int product_ID;

	@Column(name = "autual_price")
	private String autualPrice;

	@Column(name = "bar_code")
	private String barCode;

	private String batch;

	private String brand;

	private String color;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date createDate;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "expired_date")
	private Date expiredDate;

	@Column(name = "group_product")
	private String groupProduct;

	@Column(name = "ideal_for")
	private String idealFor;

	@Column(name = "industry_product")
	private String industryProduct;

	@Column(name = "margin_price")
	private String marginPrice;

	@Column(name = "market_price")
	private String marketPrice;

	@Column(name = "product_limit")
	private int productLimit;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_reference_number")
	private String productReferenceNumber;

	@Column(name = "product_standard")
	private String productStandard;

	@Column(name = "product_weight")
	private String productWeight;

	@Column(name = "selling_price")
	private String sellingPrice;

	@Column(name = "size_of_product")
	private String sizeOfProduct;

	private String status;

	private String unit;

	// bi-directional many-to-one association to I0006
	@ManyToOne
	@JoinColumn(name = "industry_ID")
	private I0006 i0006;

	// bi-directional many-to-one association to I0007
	@ManyToOne
	@JoinColumn(name = "software_ID")
	private I0007 i0007;

	// bi-directional many-to-one association to I0004
	@ManyToOne
	@JoinColumn(name = "type_parent_ID")
	private I0004 i0004;

	// bi-directional many-to-one association to I0005
	@ManyToOne
	@JoinColumn(name = "category_id")
	private I0005 i0005;

	// bi-directional many-to-one association to I0010
	@ManyToOne
	@JoinColumn(name = "electrical_ID")
	private I0010 i0010;

	// bi-directional many-to-one association to I0009
	@ManyToOne
	@JoinColumn(name = "biomedical_ID")
	private I0009 i0009;

	// bi-directional many-to-one association to I0011
	@ManyToOne
	@JoinColumn(name = "electronics_ID")
	private I0011 i0011;

	// bi-directional many-to-one association to I0008
	@ManyToOne
	@JoinColumn(name = "mechanical_ID")
	private I0008 i0008;

	// bi-directional many-to-one association to I0012
	@ManyToOne
	@JoinColumn(name = "civil_ID")
	private I0012 i0012;

	// bi-directional many-to-one association to I0002
	@ManyToOne
	@JoinColumn(name = "product_group_ID")
	private I0002 i0002;

	// bi-directional many-to-one association to I0013
	@ManyToOne
	@JoinColumn(name = "food_ID")
	private I0013 i0013;

	// bi-directional many-to-one association to I0019
	@OneToMany(mappedBy = "i0001")
	private List<I0019> i0019s;

	// bi-directional many-to-one association to I0031
	@OneToMany(mappedBy = "i0001")
	private List<I0031> i0031s;

	// bi-directional many-to-one association to UserCreate
	@ManyToOne
	@JoinColumn(name = "user_ID")
	private UserCreate userID;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	private String approvalStatus;
	
	public I0001() {
	}

	public int getProduct_ID() {
		return this.product_ID;
	}

	public void setProduct_ID(int product_ID) {
		this.product_ID = product_ID;
	}

	public UserCreate getUserID() {
		return userID;
	}

	public void setUserID(UserCreate userID) {
		this.userID = userID;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getAutualPrice() {
		return this.autualPrice;
	}

	public void setAutualPrice(String autualPrice) {
		this.autualPrice = autualPrice;
	}

	public String getBarCode() {
		return this.barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getBatch() {
		return this.batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpiredDate() {
		return this.expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getGroupProduct() {
		return this.groupProduct;
	}

	public void setGroupProduct(String groupProduct) {
		this.groupProduct = groupProduct;
	}

	public String getIdealFor() {
		return this.idealFor;
	}

	public void setIdealFor(String idealFor) {
		this.idealFor = idealFor;
	}

	public String getIndustryProduct() {
		return this.industryProduct;
	}

	public void setIndustryProduct(String industryProduct) {
		this.industryProduct = industryProduct;
	}

	public String getMarginPrice() {
		return this.marginPrice;
	}

	public void setMarginPrice(String marginPrice) {
		this.marginPrice = marginPrice;
	}

	public String getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public int getProductLimit() {
		return this.productLimit;
	}

	public void setProductLimit(int productLimit) {
		this.productLimit = productLimit;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductReferenceNumber() {
		return this.productReferenceNumber;
	}

	public void setProductReferenceNumber(String productReferenceNumber) {
		this.productReferenceNumber = productReferenceNumber;
	}

	public String getProductStandard() {
		return this.productStandard;
	}

	public void setProductStandard(String productStandard) {
		this.productStandard = productStandard;
	}

	public String getProductWeight() {
		return this.productWeight;
	}

	public void setProductWeight(String productWeight) {
		this.productWeight = productWeight;
	}

	public String getSellingPrice() {
		return this.sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getSizeOfProduct() {
		return this.sizeOfProduct;
	}

	public void setSizeOfProduct(String sizeOfProduct) {
		this.sizeOfProduct = sizeOfProduct;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public I0006 getI0006() {
		return this.i0006;
	}

	public void setI0006(I0006 i0006) {
		this.i0006 = i0006;
	}

	public I0007 getI0007() {
		return this.i0007;
	}

	public void setI0007(I0007 i0007) {
		this.i0007 = i0007;
	}

	public I0004 getI0004() {
		return this.i0004;
	}

	public void setI0004(I0004 i0004) {
		this.i0004 = i0004;
	}

	public I0005 getI0005() {
		return this.i0005;
	}

	public void setI0005(I0005 i0005) {
		this.i0005 = i0005;
	}

	public I0010 getI0010() {
		return this.i0010;
	}

	public void setI0010(I0010 i0010) {
		this.i0010 = i0010;
	}

	public I0009 getI0009() {
		return this.i0009;
	}

	public void setI0009(I0009 i0009) {
		this.i0009 = i0009;
	}

	public I0011 getI0011() {
		return this.i0011;
	}

	public void setI0011(I0011 i0011) {
		this.i0011 = i0011;
	}

	public I0008 getI0008() {
		return this.i0008;
	}

	public void setI0008(I0008 i0008) {
		this.i0008 = i0008;
	}

	public I0012 getI0012() {
		return this.i0012;
	}

	public void setI0012(I0012 i0012) {
		this.i0012 = i0012;
	}

	public I0002 getI0002() {
		return this.i0002;
	}

	public void setI0002(I0002 i0002) {
		this.i0002 = i0002;
	}

	public I0013 getI0013() {
		return this.i0013;
	}

	public void setI0013(I0013 i0013) {
		this.i0013 = i0013;
	}

	public List<I0019> getI0019s() {
		return this.i0019s;
	}

	public void setI0019s(List<I0019> i0019s) {
		this.i0019s = i0019s;
	}

	public I0019 addI0019(I0019 i0019) {
		getI0019s().add(i0019);
		i0019.setI0001(this);

		return i0019;
	}

	public I0019 removeI0019(I0019 i0019) {
		getI0019s().remove(i0019);
		i0019.setI0001(null);

		return i0019;
	}

	public List<I0031> getI0031s() {
		return this.i0031s;
	}

	public void setI0031s(List<I0031> i0031s) {
		this.i0031s = i0031s;
	}

	public I0031 addI0031(I0031 i0031) {
		getI0031s().add(i0031);
		i0031.setI0001(this);

		return i0031;
	}

	public I0031 removeI0031(I0031 i0031) {
		getI0031s().remove(i0031);
		i0031.setI0001(null);

		return i0031;
	}

	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	
}