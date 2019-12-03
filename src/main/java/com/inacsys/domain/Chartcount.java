package com.inacsys.domain;

import java.io.Serializable;

public class Chartcount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int salesCount;
	private int purchaseCount;
	private int stockInCount;
	private int stockOutCount;
	private int salesProductCount;
	private int purchaseProductCount;
	private int stockInProductCount;
	private int stockOutProductCount;

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	public int getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(int purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public int getStockInCount() {
		return stockInCount;
	}

	public void setStockInCount(int stockInCount) {
		this.stockInCount = stockInCount;
	}

	public int getStockOutCount() {
		return stockOutCount;
	}

	public void setStockOutCount(int stockOutCount) {
		this.stockOutCount = stockOutCount;
	}

	public int getSalesProductCount() {
		return salesProductCount;
	}

	public void setSalesProductCount(int salesProductCount) {
		this.salesProductCount = salesProductCount;
	}

	public int getPurchaseProductCount() {
		return purchaseProductCount;
	}

	public void setPurchaseProductCount(int purchaseProductCount) {
		this.purchaseProductCount = purchaseProductCount;
	}

	public int getStockInProductCount() {
		return stockInProductCount;
	}

	public void setStockInProductCount(int stockInProductCount) {
		this.stockInProductCount = stockInProductCount;
	}

	public int getStockOutProductCount() {
		return stockOutProductCount;
	}

	public void setStockOutProductCount(int stockOutProductCount) {
		this.stockOutProductCount = stockOutProductCount;
	}

}
