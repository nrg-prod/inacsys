package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the i0022 database table.
 * 
 */
@Entity
@Table(name = "i0022")
@NamedQuery(name = "I0022.findAll", query = "SELECT i FROM I0022 i")
public class I0022 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int invoice_ID;

	@Column(name = "approval_status")
	private String approvalStatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "invoice_date")
	private Date invoiceDate;

	private String status;

	// bi-directional many-to-one association to I0015
	@ManyToOne
	@JoinColumn(name = "purchase_ID")
	private I0015 i0015;

	// bi-directional many-to-one association to I0021
	@ManyToOne
	@JoinColumn(name = "sales_ID")
	private I0021 i0021;

	// bi-directional many-to-one association to I0023
	@OneToMany(mappedBy = "i0022")
	private List<I0023> i0023s;

	// bi-directional many-to-one association to I0033
	@OneToMany(mappedBy = "i0022")
	private List<I0033> i0033s;

	public I0022() {
	}

	public int getInvoice_ID() {
		return this.invoice_ID;
	}

	public void setInvoice_ID(int invoice_ID) {
		this.invoice_ID = invoice_ID;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public I0015 getI0015() {
		return this.i0015;
	}

	public void setI0015(I0015 i0015) {
		this.i0015 = i0015;
	}

	public I0021 getI0021() {
		return this.i0021;
	}

	public void setI0021(I0021 i0021) {
		this.i0021 = i0021;
	}

	public List<I0023> getI0023s() {
		return this.i0023s;
	}

	public void setI0023s(List<I0023> i0023s) {
		this.i0023s = i0023s;
	}

	public I0023 addI0023(I0023 i0023) {
		getI0023s().add(i0023);
		i0023.setI0022(this);

		return i0023;
	}

	public I0023 removeI0023(I0023 i0023) {
		getI0023s().remove(i0023);
		i0023.setI0022(null);

		return i0023;
	}

	public List<I0033> getI0033s() {
		return this.i0033s;
	}

	public void setI0033s(List<I0033> i0033s) {
		this.i0033s = i0033s;
	}

	public I0033 addI0033(I0033 i0033) {
		getI0033s().add(i0033);
		i0033.setI0022(this);

		return i0033;
	}

	public I0033 removeI0033(I0033 i0033) {
		getI0033s().remove(i0033);
		i0033.setI0022(null);

		return i0033;
	}
	
	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

}