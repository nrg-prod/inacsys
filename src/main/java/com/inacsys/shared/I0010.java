package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the i0010 database table.
 * 
 */
@Entity
@Table(name = "i0010")
@NamedQuery(name = "I0010.findAll", query = "SELECT i FROM I0010 i")
public class I0010 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int electrical_ID;

	@Column(name = "cable_dimension")
	private String cableDimension;

	@Column(name = "cable_type")
	private String cableType;

	@Column(name = "product_control")
	private String productControl;

	@Column(name = "product_distribution")
	private String productDistribution;

	@Column(name = "product_domestic")
	private String productDomestic;

	@Column(name = "product_switching")
	private String productSwitching;

	@Column(name = "product_voltage")
	private String productVoltage;

	// bi-directional many-to-one association to I0001
	@OneToMany(mappedBy = "i0010")
	private List<I0001> i0001s;

	// bi-directional many-to-one association to I0006
	@ManyToOne
	@JoinColumn(name = "industry_ID")
	private I0006 i0006;

	// bi-directional many-to-one association to I0014
	@OneToMany(mappedBy = "i0010")
	private List<I0014> i0014s;

	public I0010() {
	}

	public int getElectrical_ID() {
		return this.electrical_ID;
	}

	public void setElectrical_ID(int electrical_ID) {
		this.electrical_ID = electrical_ID;
	}

	public String getCableDimension() {
		return this.cableDimension;
	}

	public void setCableDimension(String cableDimension) {
		this.cableDimension = cableDimension;
	}

	public String getCableType() {
		return this.cableType;
	}

	public void setCableType(String cableType) {
		this.cableType = cableType;
	}

	public String getProductControl() {
		return this.productControl;
	}

	public void setProductControl(String productControl) {
		this.productControl = productControl;
	}

	public String getProductDistribution() {
		return this.productDistribution;
	}

	public void setProductDistribution(String productDistribution) {
		this.productDistribution = productDistribution;
	}

	public String getProductDomestic() {
		return this.productDomestic;
	}

	public void setProductDomestic(String productDomestic) {
		this.productDomestic = productDomestic;
	}

	public String getProductSwitching() {
		return this.productSwitching;
	}

	public void setProductSwitching(String productSwitching) {
		this.productSwitching = productSwitching;
	}

	public String getProductVoltage() {
		return this.productVoltage;
	}

	public void setProductVoltage(String productVoltage) {
		this.productVoltage = productVoltage;
	}

	public List<I0001> getI0001s() {
		return this.i0001s;
	}

	public void setI0001s(List<I0001> i0001s) {
		this.i0001s = i0001s;
	}

	public I0001 addI0001(I0001 i0001) {
		getI0001s().add(i0001);
		i0001.setI0010(this);

		return i0001;
	}

	public I0001 removeI0001(I0001 i0001) {
		getI0001s().remove(i0001);
		i0001.setI0010(null);

		return i0001;
	}

	public I0006 getI0006() {
		return this.i0006;
	}

	public void setI0006(I0006 i0006) {
		this.i0006 = i0006;
	}

	public List<I0014> getI0014s() {
		return this.i0014s;
	}

	public void setI0014s(List<I0014> i0014s) {
		this.i0014s = i0014s;
	}

	public I0014 addI0014(I0014 i0014) {
		getI0014s().add(i0014);
		i0014.setI0010(this);

		return i0014;
	}

	public I0014 removeI0014(I0014 i0014) {
		getI0014s().remove(i0014);
		i0014.setI0010(null);

		return i0014;
	}

}