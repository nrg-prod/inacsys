package com.inacsys.shared;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the user_product database table.
 * 
 */
@Entity
@Table(name = "user_product")
@NamedQuery(name = "UserProduct.findAll", query = "SELECT u FROM UserProduct u")
public class UserProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int has_product_ID;

	private String status;

	// bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name = "has_user_ID")
	private Login login;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "product_ID")
	private Product product;

	public UserProduct() {
	}

	public int getHas_product_ID() {
		return this.has_product_ID;
	}

	public void setHas_product_ID(int has_product_ID) {
		this.has_product_ID = has_product_ID;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}