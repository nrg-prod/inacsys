package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the user_department database table.
 * 
 */
@Entity
@Table(name="user_department")
@NamedQuery(name="UserDepartment.findAll", query="SELECT u FROM UserDepartment u")
public class UserDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int user_department_ID;

	@ManyToOne
	@JoinColumn(name = "department_ID")
	private Department department;

	@ManyToOne
	@JoinColumn(name = "user_no")
	private UserCreate userNo;
	
	private String status;

	public UserDepartment() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUser_department_ID() {
		return this.user_department_ID;
	}

	public void setUser_department_ID(int user_department_ID) {
		this.user_department_ID = user_department_ID;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public UserCreate getUserNo() {
		return userNo;
	}

	public void setUserNo(UserCreate userNo) {
		this.userNo = userNo;
	}
}