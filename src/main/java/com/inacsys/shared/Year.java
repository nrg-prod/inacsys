package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import com.inacsys.shared.Payroll;

import java.util.List;

/**
 * The persistent class for the year database table.
 * 
 */
@Entity
@Table(name = "year")
@NamedQuery(name = "Year.findAll", query = "SELECT y FROM Year y")
public class Year implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "year_id")
	private int yearId;

	private String type;

	// bi-directional many-to-one association to Payroll
	@OneToMany(mappedBy = "year")
	private List<Payroll> payrolls;

	public Year() {
	}

	public int getYearId() {
		return this.yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Payroll> getPayrolls() {
		return this.payrolls;
	}

	public void setPayrolls(List<Payroll> payrolls) {
		this.payrolls = payrolls;
	}

	public Payroll addPayroll(Payroll payroll) {
		getPayrolls().add(payroll);
		payroll.setYear(this);

		return payroll;
	}

	public Payroll removePayroll(Payroll payroll) {
		getPayrolls().remove(payroll);
		payroll.setYear(null);

		return payroll;
	}

}