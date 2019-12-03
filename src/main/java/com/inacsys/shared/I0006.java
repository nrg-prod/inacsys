package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the i0006 database table.
 * 
 */
@Entity
@Table(name = "i0006")
@NamedQuery(name = "I0006.findAll", query = "SELECT i FROM I0006 i")
public class I0006 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int industry_ID;

	@Column(name = "industry_name")
	private String industryName;

	// bi-directional many-to-one association to I0001
	@OneToMany(mappedBy = "i0006")
	private List<I0001> i0001s;

	// bi-directional many-to-one association to I0007
	@OneToMany(mappedBy = "i0006")
	private List<I0007> i0007s;

	// bi-directional many-to-one association to I0008
	@OneToMany(mappedBy = "i0006")
	private List<I0008> i0008s;

	// bi-directional many-to-one association to I0009
	@OneToMany(mappedBy = "i0006")
	private List<I0009> i0009s;

	// bi-directional many-to-one association to I0010
	@OneToMany(mappedBy = "i0006")
	private List<I0010> i0010s;

	// bi-directional many-to-one association to I0011
	@OneToMany(mappedBy = "i0006")
	private List<I0011> i0011s;

	// bi-directional many-to-one association to I0012
	@OneToMany(mappedBy = "i0006")
	private List<I0012> i0012s;

	// bi-directional many-to-one association to I0013
	@OneToMany(mappedBy = "i0006")
	private List<I0013> i0013s;

	public I0006() {
	}

	public int getIndustry_ID() {
		return this.industry_ID;
	}

	public void setIndustry_ID(int industry_ID) {
		this.industry_ID = industry_ID;
	}

	public String getIndustryName() {
		return this.industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public List<I0001> getI0001s() {
		return this.i0001s;
	}

	public void setI0001s(List<I0001> i0001s) {
		this.i0001s = i0001s;
	}

	public I0001 addI0001(I0001 i0001) {
		getI0001s().add(i0001);
		i0001.setI0006(this);

		return i0001;
	}

	public I0001 removeI0001(I0001 i0001) {
		getI0001s().remove(i0001);
		i0001.setI0006(null);

		return i0001;
	}

	public List<I0007> getI0007s() {
		return this.i0007s;
	}

	public void setI0007s(List<I0007> i0007s) {
		this.i0007s = i0007s;
	}

	public I0007 addI0007(I0007 i0007) {
		getI0007s().add(i0007);
		i0007.setI0006(this);

		return i0007;
	}

	public I0007 removeI0007(I0007 i0007) {
		getI0007s().remove(i0007);
		i0007.setI0006(null);

		return i0007;
	}

	public List<I0008> getI0008s() {
		return this.i0008s;
	}

	public void setI0008s(List<I0008> i0008s) {
		this.i0008s = i0008s;
	}

	public I0008 addI0008(I0008 i0008) {
		getI0008s().add(i0008);
		i0008.setI0006(this);

		return i0008;
	}

	public I0008 removeI0008(I0008 i0008) {
		getI0008s().remove(i0008);
		i0008.setI0006(null);

		return i0008;
	}

	public List<I0009> getI0009s() {
		return this.i0009s;
	}

	public void setI0009s(List<I0009> i0009s) {
		this.i0009s = i0009s;
	}

	public I0009 addI0009(I0009 i0009) {
		getI0009s().add(i0009);
		i0009.setI0006(this);

		return i0009;
	}

	public I0009 removeI0009(I0009 i0009) {
		getI0009s().remove(i0009);
		i0009.setI0006(null);

		return i0009;
	}

	public List<I0010> getI0010s() {
		return this.i0010s;
	}

	public void setI0010s(List<I0010> i0010s) {
		this.i0010s = i0010s;
	}

	public I0010 addI0010(I0010 i0010) {
		getI0010s().add(i0010);
		i0010.setI0006(this);

		return i0010;
	}

	public I0010 removeI0010(I0010 i0010) {
		getI0010s().remove(i0010);
		i0010.setI0006(null);

		return i0010;
	}

	public List<I0011> getI0011s() {
		return this.i0011s;
	}

	public void setI0011s(List<I0011> i0011s) {
		this.i0011s = i0011s;
	}

	public I0011 addI0011(I0011 i0011) {
		getI0011s().add(i0011);
		i0011.setI0006(this);

		return i0011;
	}

	public I0011 removeI0011(I0011 i0011) {
		getI0011s().remove(i0011);
		i0011.setI0006(null);

		return i0011;
	}

	public List<I0012> getI0012s() {
		return this.i0012s;
	}

	public void setI0012s(List<I0012> i0012s) {
		this.i0012s = i0012s;
	}

	public I0012 addI0012(I0012 i0012) {
		getI0012s().add(i0012);
		i0012.setI0006(this);

		return i0012;
	}

	public I0012 removeI0012(I0012 i0012) {
		getI0012s().remove(i0012);
		i0012.setI0006(null);

		return i0012;
	}

	public List<I0013> getI0013s() {
		return this.i0013s;
	}

	public void setI0013s(List<I0013> i0013s) {
		this.i0013s = i0013s;
	}

	public I0013 addI0013(I0013 i0013) {
		getI0013s().add(i0013);
		i0013.setI0006(this);

		return i0013;
	}

	public I0013 removeI0013(I0013 i0013) {
		getI0013s().remove(i0013);
		i0013.setI0006(null);

		return i0013;
	}

}