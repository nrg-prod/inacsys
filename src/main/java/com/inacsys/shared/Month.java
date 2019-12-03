package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the month database table.
 * 
 */
@Entity
@Table(name = "month")
@NamedQuery(name = "Month.findAll", query = "SELECT m FROM Month m")
public class Month implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "month_id")
	private int monthId;

	private String type;

	// bi-directional many-to-one association to Payroll
	@OneToMany(mappedBy = "month")
	private List<Payroll> payrolls;

	public Month() {
	}

	public int getMonthId() {
		return this.monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
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
		payroll.setMonth(this);

		return payroll;
	}

	public Payroll removePayroll(Payroll payroll) {
		getPayrolls().remove(payroll);
		payroll.setMonth(null);

		return payroll;
	}
}