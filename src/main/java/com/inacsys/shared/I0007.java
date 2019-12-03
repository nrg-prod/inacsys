package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the i0007 database table.
 * 
 */
@Entity
@Table(name = "i0007")
@NamedQuery(name = "I0007.findAll", query = "SELECT i FROM I0007 i")
public class I0007 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int software_ID;

	@Column(name = "architecture_support")
	private String architectureSupport;

	private String catalog_ID;

	@Column(name = "language_supported")
	private String languageSupported;

	@Column(name = "life_cycle")
	private String lifeCycle;

	@Column(name = "os_type")
	private String osType;

	@Column(name = "product_edition")
	private String productEdition;

	private String version;

	// bi-directional many-to-one association to I0001
	@OneToMany(mappedBy = "i0007")
	private List<I0001> i0001s;

	// bi-directional many-to-one association to I0006
	@ManyToOne
	@JoinColumn(name = "industry_ID")
	private I0006 i0006;

	// bi-directional many-to-one association to I0014
	@OneToMany(mappedBy = "i0007")
	private List<I0014> i0014s;

	public I0007() {
	}

	public int getSoftware_ID() {
		return this.software_ID;
	}

	public void setSoftware_ID(int software_ID) {
		this.software_ID = software_ID;
	}

	public String getArchitectureSupport() {
		return this.architectureSupport;
	}

	public void setArchitectureSupport(String architectureSupport) {
		this.architectureSupport = architectureSupport;
	}

	public String getCatalog_ID() {
		return this.catalog_ID;
	}

	public void setCatalog_ID(String catalog_ID) {
		this.catalog_ID = catalog_ID;
	}

	public String getLanguageSupported() {
		return this.languageSupported;
	}

	public void setLanguageSupported(String languageSupported) {
		this.languageSupported = languageSupported;
	}

	public String getLifeCycle() {
		return this.lifeCycle;
	}

	public void setLifeCycle(String lifeCycle) {
		this.lifeCycle = lifeCycle;
	}

	public String getOsType() {
		return this.osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getProductEdition() {
		return this.productEdition;
	}

	public void setProductEdition(String productEdition) {
		this.productEdition = productEdition;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<I0001> getI0001s() {
		return this.i0001s;
	}

	public void setI0001s(List<I0001> i0001s) {
		this.i0001s = i0001s;
	}

	public I0001 addI0001(I0001 i0001) {
		getI0001s().add(i0001);
		i0001.setI0007(this);

		return i0001;
	}

	public I0001 removeI0001(I0001 i0001) {
		getI0001s().remove(i0001);
		i0001.setI0007(null);

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
		i0014.setI0007(this);

		return i0014;
	}

	public I0014 removeI0014(I0014 i0014) {
		getI0014s().remove(i0014);
		i0014.setI0007(null);

		return i0014;
	}

}