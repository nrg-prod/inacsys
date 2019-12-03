package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the i0027 database table.
 * 
 */
@Entity
@Table(name = "i0027")
@NamedQuery(name = "I0027.findAll", query = "SELECT i FROM I0027 i")
public class I0027 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "nature_of_business_id")
	private int natureOfBusinessId;

	@Column(name = "authorixed_agent_business")
	private String authorixedAgentBusiness;

	@Column(name = "manufacturing_business")
	private String manufacturingBusiness;

	@Column(name = "marketing_business")
	private String marketingBusiness;

	private String name;

	@Column(name = "sole_selling_business")
	private String soleSellingBusiness;

	@Column(name = "trader_business")
	private String traderBusiness;

	// bi-directional many-to-one association to I0025
	@OneToMany(mappedBy = "i0027")
	private List<I0025> i0025s;

	public I0027() {
	}

	public int getNatureOfBusinessId() {
		return this.natureOfBusinessId;
	}

	public void setNatureOfBusinessId(int natureOfBusinessId) {
		this.natureOfBusinessId = natureOfBusinessId;
	}

	public String getAuthorixedAgentBusiness() {
		return this.authorixedAgentBusiness;
	}

	public void setAuthorixedAgentBusiness(String authorixedAgentBusiness) {
		this.authorixedAgentBusiness = authorixedAgentBusiness;
	}

	public String getManufacturingBusiness() {
		return this.manufacturingBusiness;
	}

	public void setManufacturingBusiness(String manufacturingBusiness) {
		this.manufacturingBusiness = manufacturingBusiness;
	}

	public String getMarketingBusiness() {
		return this.marketingBusiness;
	}

	public void setMarketingBusiness(String marketingBusiness) {
		this.marketingBusiness = marketingBusiness;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSoleSellingBusiness() {
		return this.soleSellingBusiness;
	}

	public void setSoleSellingBusiness(String soleSellingBusiness) {
		this.soleSellingBusiness = soleSellingBusiness;
	}

	public String getTraderBusiness() {
		return this.traderBusiness;
	}

	public void setTraderBusiness(String traderBusiness) {
		this.traderBusiness = traderBusiness;
	}

	public List<I0025> getI0025s() {
		return this.i0025s;
	}

	public void setI0025s(List<I0025> i0025s) {
		this.i0025s = i0025s;
	}

	public I0025 addI0025(I0025 i0025) {
		getI0025s().add(i0025);
		i0025.setI0027(this);

		return i0025;
	}

	public I0025 removeI0025(I0025 i0025) {
		getI0025s().remove(i0025);
		i0025.setI0027(null);

		return i0025;
	}

}