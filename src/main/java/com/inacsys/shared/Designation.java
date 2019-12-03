package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the designation database table.
 * 
 */
@Entity
@Table(name = "designation")
@NamedQuery(name = "Designation.findAll", query = "SELECT d FROM Designation d")
public class Designation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "designation_category_id")
	private int designationCategoryId;

	private String type;
	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	// bi-directional many-to-one association to Employee
	@OneToMany(mappedBy = "designation")
	private List<Employee> employees;

	public Designation() {
	}

	public int getDesignationCategoryId() {
		return this.designationCategoryId;
	}

	public void setDesignationCategoryId(int designationCategoryId) {
		this.designationCategoryId = designationCategoryId;
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
		employee.setDesignation(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setDesignation(null);

		return employee;
	}

}