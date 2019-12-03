package com.inacsys.domain;

import java.util.ArrayList;
import java.util.List;

import com.inacsys.managedBean.StockViewMB;
import com.inacsys.shared.I0019;
import com.inacsys.shared.I0025;

/**
 * This Java Class will communicate with Domain Object
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */
public class StockView {

	public String s1;
	public String s2;
	public String productname;
	public String brand;
	public String unitprice;
	public String batchName;
	public String stockinDate;
	public String buyingPrice;
	public String firmName;
	public String stockQaun;
	public int quantity;
	public String saleQuan;
	public String totalPrice;
	public int stocklimit;
	public List<StockView> domain1 = new ArrayList<StockView>();
	public List<StockView> domain2 = new ArrayList<StockView>();
	public String damageQ;
	private String rollID;
	private String stock_in;
	private String date;
	private String stock_out;
	public List<String> rollids = null;
	private List<String> roll_stock_in = null;
	private List<I0019> sample = null;
	public String vendorname;
	List<String> batchProductName4;
	List<StockViewMB> stockList1 = new ArrayList<StockViewMB>();

	public List<StockViewMB> getStockList1() {
		return stockList1;
	}

	public void setStockList1(List<StockViewMB> stockList1) {
		this.stockList1 = stockList1;
	}

	public List<String> getBatchProductName4() {
		return batchProductName4;
	}

	public void setBatchProductName4(List<String> batchProductName4) {
		this.batchProductName4 = batchProductName4;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public List<String> getRollids() {
		return rollids;
	}

	public void setRollids(List<String> rollids) {
		this.rollids = rollids;
	}

	public String getRollID() {
		return rollID;
	}

	public void setRollID(String rollID) {
		this.rollID = rollID;
	}

	public String getStock_in() {
		return stock_in;
	}

	public void setStock_in(String stock_in) {
		this.stock_in = stock_in;
	}

	public String getStock_out() {
		return stock_out;
	}

	public void setStock_out(String stock_out) {
		this.stock_out = stock_out;
	}

	private String unit;

	public String getDamageQ() {
		return damageQ;
	}

	public void setDamageQ(String damageQ) {
		this.damageQ = damageQ;
	}

	public List<StockView> getDomain1() {
		return domain1;
	}

	public void setDomain1(List<StockView> domain1) {
		this.domain1 = domain1;
	}

	public List<StockView> getDomain2() {
		return domain2;
	}

	public void setDomain2(List<StockView> domain2) {
		this.domain2 = domain2;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSaleQuan() {
		return saleQuan;
	}

	public void setSaleQuan(String saleQuan) {
		this.saleQuan = saleQuan;
	}

	public String getStockQaun() {
		return stockQaun;
	}

	public void setStockQaun(String stockQaun) {
		this.stockQaun = stockQaun;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStocklimit() {
		return stocklimit;
	}

	public void setStocklimit(int stocklimit) {
		this.stocklimit = stocklimit;
	}

	public String getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(String buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getStockinDate() {
		return stockinDate;
	}

	public void setStockinDate(String stockinDate) {
		this.stockinDate = stockinDate;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public String getS2() {
		return s2;
	}

	public void setS2(String s2) {
		this.s2 = s2;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<String> getRoll_stock_in() {
		return roll_stock_in;
	}

	public void setRoll_stock_in(List<String> roll_stock_in) {
		this.roll_stock_in = roll_stock_in;
	}

	public List<I0019> getSample() {
		return sample;
	}

	public void setSample(List<I0019> sample) {
		this.sample = sample;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
