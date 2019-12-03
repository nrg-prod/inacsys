package com.inacsys.domain;

// here You need to add only Specific details on 
public class Purchase extends Product {

	// public String ProductName;

	public String productId;
	public String Price;
	public String group;

	public String getproductId() {
		return productId;
	}

	public void setproductId(String productId) {
		this.productId = productId;
	}

	public String getprice() {
		return Price;
	}

	public void setprice(String Price) {
		this.Price = Price;
	}

	public String getgroup() {
		return group;
	}

	public void setgroup(String group) {
		this.group = group;
	}

	public String temptotquan;

	public String getTemptotquan() {
		return temptotquan;
	}

	public void setTemptotquan(String temptotquan) {
		this.temptotquan = temptotquan;
	}

}
