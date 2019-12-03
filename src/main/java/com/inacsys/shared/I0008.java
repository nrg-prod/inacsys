package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the i0008 database table.
 * 
 */
@Entity
@Table(name = "i0008")
@NamedQuery(name = "I0008.findAll", query = "SELECT i FROM I0008 i")
public class I0008 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int mechanical_ID;

	@Column(name = "no_of_wheel")
	private String noOfWheel;

	@Column(name = "product_cc")
	private String productCc;

	@Column(name = "product_gear")
	private String productGear;

	@Column(name = "product_strain")
	private String productStrain;

	@Column(name = "product_stress")
	private String productStress;

	@Column(name = "product_stroke")
	private String productStroke;

	@Column(name = "top_speed")
	private String topSpeed;

	@Column(name = "type_of_iron")
	private String typeOfIron;

	// bi-directional many-to-one association to I0001
	@OneToMany(mappedBy = "i0008")
	private List<I0001> i0001s;

	// bi-directional many-to-one association to I0006
	@ManyToOne
	@JoinColumn(name = "industry_ID")
	private I0006 i0006;

	// bi-directional many-to-one association to I0014
	@OneToMany(mappedBy = "i0008")
	private List<I0014> i0014s;

	public I0008() {
	}

	public int getMechanical_ID() {
		return this.mechanical_ID;
	}

	public void setMechanical_ID(int mechanical_ID) {
		this.mechanical_ID = mechanical_ID;
	}

	public String getNoOfWheel() {
		return this.noOfWheel;
	}

	public void setNoOfWheel(String noOfWheel) {
		this.noOfWheel = noOfWheel;
	}

	public String getProductCc() {
		return this.productCc;
	}

	public void setProductCc(String productCc) {
		this.productCc = productCc;
	}

	public String getProductGear() {
		return this.productGear;
	}

	public void setProductGear(String productGear) {
		this.productGear = productGear;
	}

	public String getProductStrain() {
		return this.productStrain;
	}

	public void setProductStrain(String productStrain) {
		this.productStrain = productStrain;
	}

	public String getProductStress() {
		return this.productStress;
	}

	public void setProductStress(String productStress) {
		this.productStress = productStress;
	}

	public String getProductStroke() {
		return this.productStroke;
	}

	public void setProductStroke(String productStroke) {
		this.productStroke = productStroke;
	}

	public String getTopSpeed() {
		return this.topSpeed;
	}

	public void setTopSpeed(String topSpeed) {
		this.topSpeed = topSpeed;
	}

	public String getTypeOfIron() {
		return this.typeOfIron;
	}

	public void setTypeOfIron(String typeOfIron) {
		this.typeOfIron = typeOfIron;
	}

	public List<I0001> getI0001s() {
		return this.i0001s;
	}

	public void setI0001s(List<I0001> i0001s) {
		this.i0001s = i0001s;
	}

	public I0001 addI0001(I0001 i0001) {
		getI0001s().add(i0001);
		i0001.setI0008(this);

		return i0001;
	}

	public I0001 removeI0001(I0001 i0001) {
		getI0001s().remove(i0001);
		i0001.setI0008(null);

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
		i0014.setI0008(this);

		return i0014;
	}

	public I0014 removeI0014(I0014 i0014) {
		getI0014s().remove(i0014);
		i0014.setI0008(null);

		return i0014;
	}

}