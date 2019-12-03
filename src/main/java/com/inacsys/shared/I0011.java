package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the i0011 database table.
 * 
 */
@Entity
@Table(name = "i0011")
@NamedQuery(name = "I0011.findAll", query = "SELECT i FROM I0011 i")
public class I0011 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int electronics_ID;

	@Column(name = "camera_type")
	private String cameraType;

	@Column(name = "enery_rating_ac")
	private String eneryRatingAc;

	private String features;

	@Column(name = "interface_name")
	private String interfaceName;

	@Column(name = "life_style")
	private String lifeStyle;

	@Column(name = "no_of_port")
	private String noOfPort;

	@Column(name = "power_consumption")
	private String powerConsumption;

	@Column(name = "processor_name")
	private String processorName;

	@Column(name = "resolution_screen")
	private String resolutionScreen;

	@Column(name = "screen_size")
	private String screenSize;

	private String storage;

	// bi-directional many-to-one association to I0001
	@OneToMany(mappedBy = "i0011")
	private List<I0001> i0001s;

	// bi-directional many-to-one association to I0006
	@ManyToOne
	@JoinColumn(name = "industry_ID")
	private I0006 i0006;

	// bi-directional many-to-one association to I0014
	@OneToMany(mappedBy = "i0011")
	private List<I0014> i0014s;

	public I0011() {
	}

	public int getElectronics_ID() {
		return this.electronics_ID;
	}

	public void setElectronics_ID(int electronics_ID) {
		this.electronics_ID = electronics_ID;
	}

	public String getCameraType() {
		return this.cameraType;
	}

	public void setCameraType(String cameraType) {
		this.cameraType = cameraType;
	}

	public String getEneryRatingAc() {
		return this.eneryRatingAc;
	}

	public void setEneryRatingAc(String eneryRatingAc) {
		this.eneryRatingAc = eneryRatingAc;
	}

	public String getFeatures() {
		return this.features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getInterfaceName() {
		return this.interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getLifeStyle() {
		return this.lifeStyle;
	}

	public void setLifeStyle(String lifeStyle) {
		this.lifeStyle = lifeStyle;
	}

	public String getNoOfPort() {
		return this.noOfPort;
	}

	public void setNoOfPort(String noOfPort) {
		this.noOfPort = noOfPort;
	}

	public String getPowerConsumption() {
		return this.powerConsumption;
	}

	public void setPowerConsumption(String powerConsumption) {
		this.powerConsumption = powerConsumption;
	}

	public String getProcessorName() {
		return this.processorName;
	}

	public void setProcessorName(String processorName) {
		this.processorName = processorName;
	}

	public String getResolutionScreen() {
		return this.resolutionScreen;
	}

	public void setResolutionScreen(String resolutionScreen) {
		this.resolutionScreen = resolutionScreen;
	}

	public String getScreenSize() {
		return this.screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public String getStorage() {
		return this.storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public List<I0001> getI0001s() {
		return this.i0001s;
	}

	public void setI0001s(List<I0001> i0001s) {
		this.i0001s = i0001s;
	}

	public I0001 addI0001(I0001 i0001) {
		getI0001s().add(i0001);
		i0001.setI0011(this);

		return i0001;
	}

	public I0001 removeI0001(I0001 i0001) {
		getI0001s().remove(i0001);
		i0001.setI0011(null);

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
		i0014.setI0011(this);

		return i0014;
	}

	public I0014 removeI0014(I0014 i0014) {
		getI0014s().remove(i0014);
		i0014.setI0011(null);

		return i0014;
	}

}