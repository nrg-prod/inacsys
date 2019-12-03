package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import org.primefaces.model.UploadedFile;

import java.util.Date;


/**
 * The persistent class for the documents database table.
 * 
 */
@Entity
@Table(name="documents")
@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int documents_ID;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String description;

	private String fileName;
	
	private String FileUpload; 
 
	

	
	public String getFileUpload() {
		return FileUpload;
	}

	public void setFileUpload(String fileUpload) {
		FileUpload = fileUpload;
	}

	private String fileType;

	private String status;

	public Document() {
	}

	public int getDocuments_ID() {
		return this.documents_ID;
	}

	public void setDocuments_ID(int documents_ID) {
		this.documents_ID = documents_ID;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}