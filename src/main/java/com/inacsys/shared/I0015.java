package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import com.inacsys.shared.I0019;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the i0015 database table.
 * 
 */
@Entity
@Table(name = "i0015")
@NamedQuery(name = "I0015.findAll", query = "SELECT i FROM I0015 i")
public class I0015 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int purchase_ID;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_name")
	private Date dateName;

	@Temporal(TemporalType.DATE)
	@Column(name = "order_date")
	private Date orderDate;

	private String quantity;

	private String quantityTotal;

	public String getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(String quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	private String status;

	private String status2;
	
	@Column(name = "currency_type")
	private String currencyType;
	
	@Column(name = "currency")
	private String currency;

	@Temporal(TemporalType.DATE)
	@Column(name = "targent_date")
	private Date targentDate;

	@Column(name = "tem_order_number")
	private String temOrderNumber;

	@Column(name = "total_price")
	private String totalPrice;

	// bi-directional many-to-one association to I0016
	@OneToMany(mappedBy = "i0015")
	private List<I0016> i0016s;

	// bi-directional many-to-one association to I0017
	@OneToMany(mappedBy = "i0015")
	private List<I0017> i0017s;

	// bi-directional many-to-one association to I0020
	@OneToMany(mappedBy = "i0015")
	private List<I0020> i0020s;

	// bi-directional many-to-one association to I0022
	@OneToMany(mappedBy = "i0015")
	private List<I0022> i0022s;

	// bi-directional many-to-one association to I0024
	@OneToMany(mappedBy = "i0015")
	private List<I0024> i0024s;

	// bi-directional many-to-one association to I0033
	@OneToMany(mappedBy = "i0015")
	private List<I0033> i0033s;

	// bi-directional many-to-one association to I0019
	@OneToMany(mappedBy = "i0015")
	private List<I0019> i0019s;

	// bi-directional many-to-one association to UserCreate
	@ManyToOne
	@JoinColumn(name = "user_ID")
	private UserCreate userID;
	
	private String approvalStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
		
	public I0015() {
	}

	public UserCreate getUserID() {
		return userID;
	}

	public void setUserID(UserCreate userID) {
		this.userID = userID;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public int getPurchase_ID() {
		return this.purchase_ID;
	}

	public void setPurchase_ID(int purchase_ID) {
		this.purchase_ID = purchase_ID;
	}

	public Date getDateName() {
		return this.dateName;
	}

	public void setDateName(Date dateName) {
		this.dateName = dateName;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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

	public Date getTargentDate() {
		return this.targentDate;
	}

	public void setTargentDate(Date targentDate) {
		this.targentDate = targentDate;
	}

	public String getTemOrderNumber() {
		return this.temOrderNumber;
	}

	public void setTemOrderNumber(String temOrderNumber) {
		this.temOrderNumber = temOrderNumber;
	}

	public String getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<I0016> getI0016s() {
		return this.i0016s;
	}

	public void setI0016s(List<I0016> i0016s) {
		this.i0016s = i0016s;
	}

	public I0016 addI0016(I0016 i0016) {
		getI0016s().add(i0016);
		i0016.setI0015(this);

		return i0016;
	}

	public I0016 removeI0016(I0016 i0016) {
		getI0016s().remove(i0016);
		i0016.setI0015(null);

		return i0016;
	}

	public List<I0017> getI0017s() {
		return this.i0017s;
	}

	public void setI0017s(List<I0017> i0017s) {
		this.i0017s = i0017s;
	}

	public I0017 addI0017(I0017 i0017) {
		getI0017s().add(i0017);
		i0017.setI0015(this);

		return i0017;
	}

	public I0017 removeI0017(I0017 i0017) {
		getI0017s().remove(i0017);
		i0017.setI0015(null);

		return i0017;
	}

	public List<I0020> getI0020s() {
		return this.i0020s;
	}

	public void setI0020s(List<I0020> i0020s) {
		this.i0020s = i0020s;
	}

	public I0020 addI0020(I0020 i0020) {
		getI0020s().add(i0020);
		i0020.setI0015(this);

		return i0020;
	}

	public I0020 removeI0020(I0020 i0020) {
		getI0020s().remove(i0020);
		i0020.setI0015(null);

		return i0020;
	}

	public List<I0022> getI0022s() {
		return this.i0022s;
	}

	public void setI0022s(List<I0022> i0022s) {
		this.i0022s = i0022s;
	}

	public I0022 addI0022(I0022 i0022) {
		getI0022s().add(i0022);
		i0022.setI0015(this);

		return i0022;
	}

	public I0022 removeI0022(I0022 i0022) {
		getI0022s().remove(i0022);
		i0022.setI0015(null);

		return i0022;
	}

	public List<I0024> getI0024s() {
		return this.i0024s;
	}

	public void setI0024s(List<I0024> i0024s) {
		this.i0024s = i0024s;
	}

	public I0024 addI0024(I0024 i0024) {
		getI0024s().add(i0024);
		i0024.setI0015(this);

		return i0024;
	}

	public I0024 removeI0024(I0024 i0024) {
		getI0024s().remove(i0024);
		i0024.setI0015(null);

		return i0024;
	}

	public List<I0033> getI0033s() {
		return this.i0033s;
	}

	public void setI0033s(List<I0033> i0033s) {
		this.i0033s = i0033s;
	}

	public I0033 addI0033(I0033 i0033) {
		getI0033s().add(i0033);
		i0033.setI0015(this);

		return i0033;
	}

	public I0033 removeI0033(I0033 i0033) {
		getI0033s().remove(i0033);
		i0033.setI0015(null);

		return i0033;
	}

	public List<I0019> getI0019s() {
		return this.i0019s;
	}

	public void setI0019s(List<I0019> i0019s) {
		this.i0019s = i0019s;
	}

	public I0019 addI0019(I0019 i0019) {
		getI0019s().add(i0019);
		i0019.setI0015(this);

		return i0019;
	}

	public I0019 removeI0019(I0019 i0019) {
		getI0019s().remove(i0019);
		i0019.setI0015(null);

		return i0019;
	}

	
	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}