package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the employee_qualification database table.
 * 
 */
@Entity
@Table(name="employee_qualification")
@NamedQuery(name="EmployeeQualification.findAll", query="SELECT e FROM EmployeeQualification e")
public class EmployeeQualification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int qualification_ID;

	@Column(name="certificate_path")
	private String certificatePath;

	@Column(name="course_name")
	private String courseName;

	@Column(name="institute_name")
	private String instituteName;

	@Column(name="medium_instruction")
	private String mediumInstruction;

	private String status;

	private String yearOf_passing;
	
	// bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name = "employee_ID")
	private Employee employee;

	public EmployeeQualification() {
	}

	public int getQualification_ID() {
		return this.qualification_ID;
	}

	public void setQualification_ID(int qualification_ID) {
		this.qualification_ID = qualification_ID;
	}

	public String getCertificatePath() {
		return this.certificatePath;
	}

	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getInstituteName() {
		return this.instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getMediumInstruction() {
		return this.mediumInstruction;
	}

	public void setMediumInstruction(String mediumInstruction) {
		this.mediumInstruction = mediumInstruction;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getYearOf_passing() {
		return this.yearOf_passing;
	}

	public void setYearOf_passing(String yearOf_passing) {
		this.yearOf_passing = yearOf_passing;
	}

}