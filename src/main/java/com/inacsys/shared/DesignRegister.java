package com.inacsys.shared;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the design_register database table.
 * 
 */
@Entity
@Table(name = "design_register")
@NamedQuery(name = "DesignRegister.findAll", query = "SELECT d FROM DesignRegister d")
public class DesignRegister implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "reg_id")
	private int regId;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "file_path")
	private String filePath;

	private String status;

	private String status2;

	// bi-directional many-to-one association to I0001
	@ManyToOne
	@JoinColumn(name = "product_id")
	private I0001 i0001;

	public DesignRegister() {
	}

	public int getRegId() {
		return this.regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus2() {
		return this.status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public I0001 getI0001() {
		return this.i0001;
	}

	public void setI0001(I0001 i0001) {
		this.i0001 = i0001;
	}

	@Column(name = "article_no")
	private String articleNo;

	@Column(name = "article_product")
	private String articleProduct;

	@Temporal(TemporalType.DATE)
	@Column(name = "biding_time")
	private Date bidingTime;

	@Temporal(TemporalType.DATE)
	@Column(name = "estimate_date")
	private Date estimateDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "production_date")
	private Date productionDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "sample_date")
	private Date sampleDate;

	@Column(name = "theme_product")
	private String themeProduct;

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getArticleProduct() {
		return articleProduct;
	}

	public void setArticleProduct(String articleProduct) {
		this.articleProduct = articleProduct;
	}

	public Date getBidingTime() {
		return bidingTime;
	}

	public void setBidingTime(Date bidingTime) {
		this.bidingTime = bidingTime;
	}

	public Date getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(Date estimateDate) {
		this.estimateDate = estimateDate;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Date getSampleDate() {
		return sampleDate;
	}

	public void setSampleDate(Date sampleDate) {
		this.sampleDate = sampleDate;
	}

	public String getThemeProduct() {
		return themeProduct;
	}

	public void setThemeProduct(String themeProduct) {
		this.themeProduct = themeProduct;
	}

}