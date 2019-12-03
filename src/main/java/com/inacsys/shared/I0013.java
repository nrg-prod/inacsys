package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the i0013 database table.
 * 
 */
@Entity
@Table(name = "i0013")
@NamedQuery(name = "I0013.findAll", query = "SELECT i FROM I0013 i")
public class I0013 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int food_ID;

	@Column(name = "fat_percentage")
	private BigDecimal fatPercentage;

	@Column(name = "food_category_number")
	private String foodCategoryNumber;

	@Column(name = "nature_of_product")
	private String natureOfProduct;

	@Column(name = "orgin_place")
	private String orginPlace;

	// bi-directional many-to-one association to I0001
	@OneToMany(mappedBy = "i0013")
	private List<I0001> i0001s;

	// bi-directional many-to-one association to I0006
	@ManyToOne
	@JoinColumn(name = "industry_ID")
	private I0006 i0006;

	// bi-directional many-to-one association to I0014
	@OneToMany(mappedBy = "i0013")
	private List<I0014> i0014s;

	public I0013() {
	}

	public int getFood_ID() {
		return this.food_ID;
	}

	public void setFood_ID(int food_ID) {
		this.food_ID = food_ID;
	}

	public BigDecimal getFatPercentage() {
		return this.fatPercentage;
	}

	public void setFatPercentage(BigDecimal fatPercentage) {
		this.fatPercentage = fatPercentage;
	}

	public String getFoodCategoryNumber() {
		return this.foodCategoryNumber;
	}

	public void setFoodCategoryNumber(String foodCategoryNumber) {
		this.foodCategoryNumber = foodCategoryNumber;
	}

	public String getNatureOfProduct() {
		return this.natureOfProduct;
	}

	public void setNatureOfProduct(String natureOfProduct) {
		this.natureOfProduct = natureOfProduct;
	}

	public String getOrginPlace() {
		return this.orginPlace;
	}

	public void setOrginPlace(String orginPlace) {
		this.orginPlace = orginPlace;
	}

	public List<I0001> getI0001s() {
		return this.i0001s;
	}

	public void setI0001s(List<I0001> i0001s) {
		this.i0001s = i0001s;
	}

	public I0001 addI0001(I0001 i0001) {
		getI0001s().add(i0001);
		i0001.setI0013(this);

		return i0001;
	}

	public I0001 removeI0001(I0001 i0001) {
		getI0001s().remove(i0001);
		i0001.setI0013(null);

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
		i0014.setI0013(this);

		return i0014;
	}

	public I0014 removeI0014(I0014 i0014) {
		getI0014s().remove(i0014);
		i0014.setI0013(null);

		return i0014;
	}

}