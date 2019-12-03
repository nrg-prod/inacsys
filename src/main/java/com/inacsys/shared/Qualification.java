package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the qualification database table.
 * 
 */
@Entity
@Table(name = "qualification")
@NamedQuery(name = "Qualification.findAll", query = "SELECT q FROM Qualification q")
public class Qualification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "qualification_category_id")
	private int qualificationCategoryId;

	private String type;

	// bi-directional many-to-one association to Employee
	@OneToMany(mappedBy = "qualification")
	private List<Employee> employees;

	public Qualification() {
	}

	public int getQualificationCategoryId() {
		return this.qualificationCategoryId;
	}

	public void setQualificationCategoryId(int qualificationCategoryId) {
		this.qualificationCategoryId = qualificationCategoryId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setQualification(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setQualification(null);

		return employee;
	}

}