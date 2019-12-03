package com.inacsys.managedBean;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

public class UploadedImage implements Serializable {
	private static final long serialVersionUID = -8192553629588066292L;
	@Id
	@GeneratedValue
	@Column(name = "pid")
	private Integer pid;
	@Column(name = "name")
	private String name;
	@Column(name = "aid")
	private String aname;
	@Lob
	@Column(name = "data")
	@Basic(fetch = FetchType.LAZY)
	public InputStream io;

	public InputStream getIo() {
		return io;
	}

	public void setIo(InputStream io) {
		this.io = io;
	}

	private byte[] data;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	/*
	 * public void setName(String name) { this.name = name; }
	 */
	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setLength(int length) {
		// TODO Auto-generated method stub

	}

	// new

	private String Name;
	private String mime;
	private long length;

	public void setName(String name) {
		Name = name;
		int extDot = name.lastIndexOf('.');
		if (extDot > 0) {
			String extension = name.substring(extDot + 1);
			if ("bmp".equals(extension)) {
				mime = "image/bmp";
			} else if ("jpg".equals(extension)) {
				mime = "image/jpeg";
			} else if ("gif".equals(extension)) {
				mime = "image/gif";
			} else if ("png".equals(extension)) {
				mime = "image/png";
			} else {
				mime = "image/unknown";
			}
		}
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public String getMime() {
		return mime;
	}

	private ArrayList<UploadedImage> files = new ArrayList<UploadedImage>();

	public ArrayList<UploadedImage> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<UploadedImage> files) {
		this.files = files;
	}

	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private String no;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	private long flength;

	public long getFlength() {
		return flength;
	}

	public void setFlength(long flength) {
		this.flength = flength;
	}

}
