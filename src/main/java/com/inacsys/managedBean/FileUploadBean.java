package com.inacsys.managedBean;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
/**
 * @author Ilya Shaikovsky
 *
 */
@ManagedBean(name = "fileUploadBean")
public class FileUploadBean {
	private static Logger logger = Logger.getLogger(FileUploadBean.class);
	private ArrayList<UploadedImage> files = new ArrayList<UploadedImage>();
	private int uploadsAvailable = 5;
	private boolean autoUpload = false;
	private boolean useFlash = false;

	public String clearUploadData() {
		files.clear();
		setUploadsAvailable(5);
		return null;
	}

	public long getTimeStamp() {
		return System.currentTimeMillis();
	}

	public ArrayList<UploadedImage> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<UploadedImage> files) {
		this.files = files;
	}

	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	public boolean isAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(boolean autoUpload) {
		this.autoUpload = autoUpload;
	}

	public boolean isUseFlash() {
		return useFlash;
	}

	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash;
	}

	public int getSize() {
		if (getFiles().size() > 0) {
			return getFiles().size();
		} else {
			return 0;
		}
	}

	public FileUploadBean() {

	}

	public void paint(OutputStream stream, Object object) throws IOException {
		logger.info("[paint()] --------------- Inside paint() method() ------------------------");
		stream.write(getFiles().get((Integer) object).getData());
	}

	OutputStream stream;
	Object object;

	public void listener(FileUploadEvent event) throws Exception {
		logger.info("[listener()] --------------- Inside listener() method() ------------------------");
		UploadedFile item = event.getFile();
		UploadedImage file = new UploadedImage();
		file.setName(item.getFileName());
		file.setData(item.getContents());
		files.add(file);
		uploadsAvailable--;
	}
}