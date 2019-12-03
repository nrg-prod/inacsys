package org.richfaces.model;

import java.io.File;
import java.io.Serializable;

public class UploadItem implements Serializable {

	private static final long serialVersionUID = -111723029745124147L;
	private String fileName;

	private String contentType;
	private File file;

	private byte[] bytes;

	private int fileSize;

	public UploadItem(String fileName, int fileSize, String contentType,
			Object file) {
		this.fileName = fileName;
		this.contentType = contentType;
		this.fileSize = fileSize;
		if (null != file) {
			if (file.getClass().isAssignableFrom(File.class)) {
				this.file = (File) file;
			} else if (file.getClass().isAssignableFrom(byte[].class)) {
				this.bytes = (byte[]) file;
			}
		}
	}

	public boolean isTempFile() {
		return (null != file);
	}

	public File getFile() {
		return file;
	}

	public byte[] getData() {
		return bytes;
	}

	public String getFileName() {
		return fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public int getFileSize() {
		return fileSize;
	}

}
