package com.inacsys.shared;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.inacsys.domain.ATransaction;
import com.inacsys.domain.AccountsDatabean;
import com.inacsys.domain.Sales;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the credit_card_acct database table.
 * 
 */
@Entity
@Table(name="revenue")
@NamedQuery(name="Revenue.findAll", query="SELECT c FROM Revenue c")
public class Revenue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String programmeName;

	private String client_ID;

	private String schedules;

	@Column(name="billed_amount")
	private String billedAmount;

	@Column(name="billed_amountpercentage")
	private String billedAmountpercentage;

	private String totalPayable;

	private String status;

	private Date date;

	public Revenue() {
	}
	
	public Revenue(Sales sales,String activeStatus, String clientID) {
		this.date=sales.getSalesorderDate();
		this.programmeName=sales.getProgrammeName();
		this.schedules=sales.getSchedules();
		this.billedAmount=sales.getBilledAmount();
		this.billedAmountpercentage=sales.getBilledAmountPercentage();
		this.totalPayable=sales.getTotalpayable();
		this.client_ID=clientID;
		this.status=activeStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProgrammeName() {
		return programmeName;
	}

	public void setProgrammeName(String programmeName) {
		this.programmeName = programmeName;
	}

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public String getSchedules() {
		return schedules;
	}

	public void setSchedules(String schedules) {
		this.schedules = schedules;
	}

	public String getBilledAmount() {
		return billedAmount;
	}

	public void setBilledAmount(String billedAmount) {
		this.billedAmount = billedAmount;
	}

	public String getBilledAmountpercentage() {
		return billedAmountpercentage;
	}

	public void setBilledAmountpercentage(String billedAmountpercentage) {
		this.billedAmountpercentage = billedAmountpercentage;
	}

	public String getTotalPayable() {
		return totalPayable;
	}

	public void setTotalPayable(String totalPayable) {
		this.totalPayable = totalPayable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}