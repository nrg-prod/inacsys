package com.inacsys.shared;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the error_code database table.
 * 
 */
@Entity
@Table(name = "error_code")
@NamedQuery(name = "ErrorCode.findAll", query = "SELECT e FROM ErrorCode e")
public class ErrorCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "error_number")
	private int errorNumber;

	@Column(name = "error_code")
	private String errorCode;

	@Column(name = "error_msg")
	private String errorMsg;

	public ErrorCode() {
	}

	public int getErrorNumber() {
		return this.errorNumber;
	}

	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}