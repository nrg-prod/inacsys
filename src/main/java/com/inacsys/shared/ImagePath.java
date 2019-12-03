package com.inacsys.shared;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the image_path database table.
 * 
 */
@Entity
@Table(name = "image_path")
@NamedQuery(name = "ImagePath.findAll", query = "SELECT i FROM ImagePath i")
public class ImagePath implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "path_id")
	private int pathId;

	private String description;

	private String status;

	private String status2;

	// bi-directional many-to-one association to I0001
	@ManyToOne
	@JoinColumn(name = "product_id")
	private I0001 i0001;

	public ImagePath() {
	}

	public int getPathId() {
		return this.pathId;
	}

	public void setPathId(int pathId) {
		this.pathId = pathId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus2() {
		return this.status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public I0001 getI0001() {
		return this.i0001;
	}

	public void setI0001(I0001 i0001) {
		this.i0001 = i0001;
	}

}