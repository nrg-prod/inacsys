package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the dispatch database table.
 * 
 */
@Entity
@Table(name = "dispatch")
@NamedQuery(name = "Dispatch.findAll", query = "SELECT d FROM Dispatch d")
public class Dispatch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int dispatch_ID;

	private String dispatchno;

	private String vehicle1;

	private String vehicle2;

	@Column(name = "sales_order_number")
	private String salesOrderNumber;

	// bi-directional many-to-one association to I0021
	@ManyToOne
	@JoinColumn(name = "sales_ID")
	private I0021 i0021;

	public Dispatch() {
	}

	public String getVehicle1() {
		return vehicle1;
	}

	public void setVehicle1(String vehicle1) {
		this.vehicle1 = vehicle1;
	}

	public String getVehicle2() {
		return vehicle2;
	}

	public void setVehicle2(String vehicle2) {
		this.vehicle2 = vehicle2;
	}

	public int getDispatch_ID() {
		return this.dispatch_ID;
	}

	public void setDispatch_ID(int dispatch_ID) {
		this.dispatch_ID = dispatch_ID;
	}

	public String getDispatchno() {
		return this.dispatchno;
	}

	public void setDispatchno(String dispatchno) {
		this.dispatchno = dispatchno;
	}

	public String getSalesOrderNumber() {
		return this.salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public I0021 getI0021() {
		return this.i0021;
	}

	public void setI0021(I0021 i0021) {
		this.i0021 = i0021;
	}
	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}
}