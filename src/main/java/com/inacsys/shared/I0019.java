package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import com.inacsys.shared.I0015;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the i0019 database table.
 * 
 */
@Entity
@Table(name = "i0019")
@NamedQuery(name = "I0019.findAll", query = "SELECT i FROM I0019 i")
public class I0019 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int bar_code_ID;

	@Column(name = "damge_status")
	private String damgeStatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Date dueDate;

	private String quantity;

	private String status;

	private String status2;

	// private String quantity;
	private String stock_In;

	private String stock_Out;

	private String roll_ID;

	private String roll_Qunatity;

	private String roll_serialNo;

	private String roll_status;

	private String roll_damagestatus;

	public String getRoll_damagestatus() {
		return roll_damagestatus;
	}

	public void setRoll_damagestatus(String roll_damagestatus) {
		this.roll_damagestatus = roll_damagestatus;
	}

	private String roll_damage;

	public String getRoll_damage() {
		return roll_damage;
	}

	public void setRoll_damage(String roll_damage) {
		this.roll_damage = roll_damage;
	}

	public Date getRollDate() {
		return rollDate;
	}

	public void setRollDate(Date rollDate) {
		this.rollDate = rollDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "roll_date")
	private Date rollDate;

	// bi-directional many-to-one association to I0001
	@ManyToOne
	@JoinColumn(name = "product_ID")
	private I0001 i0001;

	// bi-directional many-to-one association to I0018
	@ManyToOne
	@JoinColumn(name = "batch_ID")
	private I0018 i0018;

	// bi-directional many-to-one association to I0021
	@ManyToOne
	@JoinColumn(name = "sales_ID")
	private I0021 i0021;

	// bi-directional many-to-one association to SalesRecord
	@OneToMany(mappedBy = "i0019")
	private List<SalesRecord> salesRecords;

	// bi-directional many-to-one association to I0015
	@ManyToOne
	@JoinColumn(name = "purchase_ID")
	private I0015 i0015;

	public I0015 getI0015() {
		return this.i0015;
	}

	public void setI0015(I0015 i0015) {
		this.i0015 = i0015;
	}

	public I0019() {
	}

	public int getBar_code_ID() {
		return this.bar_code_ID;
	}

	public void setBar_code_ID(int bar_code_ID) {
		this.bar_code_ID = bar_code_ID;
	}

	public String getDamgeStatus() {
		return this.damgeStatus;
	}

	public void setDamgeStatus(String damgeStatus) {
		this.damgeStatus = damgeStatus;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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

	public I0018 getI0018() {
		return this.i0018;
	}

	public void setI0018(I0018 i0018) {
		this.i0018 = i0018;
	}

	public I0021 getI0021() {
		return this.i0021;
	}

	public void setI0021(I0021 i0021) {
		this.i0021 = i0021;
	}

	public List<SalesRecord> getSalesRecords() {
		return this.salesRecords;
	}

	public void setSalesRecords(List<SalesRecord> salesRecords) {
		this.salesRecords = salesRecords;
	}

	public SalesRecord addSalesRecord(SalesRecord salesRecord) {
		getSalesRecords().add(salesRecord);
		salesRecord.setI0019(this);

		return salesRecord;
	}

	public String getStock_In() {
		return stock_In;
	}

	public void setStock_In(String stock_In) {
		this.stock_In = stock_In;
	}

	public String getStock_Out() {
		return stock_Out;
	}

	public void setStock_Out(String stock_Out) {
		this.stock_Out = stock_Out;
	}

	public String getRoll_ID() {
		return roll_ID;
	}

	public void setRoll_ID(String roll_ID) {
		this.roll_ID = roll_ID;
	}

	public SalesRecord removeSalesRecord(SalesRecord salesRecord) {
		getSalesRecords().remove(salesRecord);
		salesRecord.setI0019(null);

		return salesRecord;
	}

	public String getRoll_Qunatity() {
		return roll_Qunatity;
	}

	public void setRoll_Qunatity(String roll_Qunatity) {
		this.roll_Qunatity = roll_Qunatity;
	}

	public String getRoll_serialNo() {
		return roll_serialNo;
	}

	public void setRoll_serialNo(String roll_serialNo) {
		this.roll_serialNo = roll_serialNo;
	}

	public String getRoll_status() {
		return roll_status;
	}

	public void setRoll_status(String roll_status) {
		this.roll_status = roll_status;
	}
	
	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

}