package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the i0021 database table.
 * 
 */
@Entity
@Table(name = "i0021")
@NamedQuery(name = "I0021.findAll", query = "SELECT i FROM I0021 i")
public class I0021 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int sales_ID;
	private String accno;
	private String city;

	@Column(name = "commission_amount")
	private String commissionAmount;

	private String country;
	private String bankname;
	private String cardno;

	@Column(name = "approval_status")
	private String approvalStatus;
	
	@Temporal(TemporalType.DATE)
	private Date chequedate;
	
	@Column(name = "currency_amount")
	private String currencyAmount;

	public Date getChequedate() {
		return chequedate;
	}

	public void setChequedate(Date chequedate) {
		this.chequedate = chequedate;
	}

	private String chequeno;

	private String currency;
	
	private String shipping_company;

	private int country_ID;

	@Column(name = "cross_commision")
	private String crossCommision;

	@Column(name = "cross_total")
	private String crossTotal;

	@Column(name = "customer_name")
	private String customerName;

	@Temporal(TemporalType.DATE)
	@Column(name = "dam_due_date")
	private Date damDueDate;

	@Column(name = "damage_return_reason")
	private String damageReturnReason;

	@Temporal(TemporalType.DATE)
	@Column(name = "delivery_date")
	private Date deliveryDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Date dueDate;

	@Column(name = "e_mail")
	private String eMail;

	@Column(name = "firm_type")
	private String firmType;

	@Column(name = "nature_of_business")
	private String natureOfBusiness;

	@Temporal(TemporalType.DATE)
	@Column(name = "nor_due_date")
	private Date norDueDate;

	@Column(name = "nor_return_reason")
	private String norReturnReason;

	private String note;

	private String paymentType;

	@Column(name = "phone_number")
	private String phoneNumber;

	private String quickStatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "sales_order_date")
	private Date salesOrderDate;

	@Column(name = "sales_order_number")
	private String salesOrderNumber;

	@Column(name = "shiping_address")
	private String shipingAddress;

	@Column(name = "shipping_charge")
	private String shippingCharge;

	private String state;

	private String status;

	private String status2;

	@Column(name = "total_number_of_count")
	private String totalNumberOfCount;

	// bi-directional many-to-one association to Dispatch
	@OneToMany(mappedBy = "i0021")
	private List<Dispatch> dispatches;

	// bi-directional many-to-one association to I0019
	@OneToMany(mappedBy = "i0021")
	private List<I0019> i0019s;

	// bi-directional many-to-one association to I0032
	@ManyToOne
	@JoinColumn(name = "buyer_ID")
	private I0032 i0032;

	// bi-directional many-to-one association to I0022
	@OneToMany(mappedBy = "i0021")
	private List<I0022> i0022s;

	// bi-directional many-to-one association to SalesReturn
	@OneToMany(mappedBy = "i0021")
	private List<SalesReturn> salesReturns;

	// bi-directional many-to-one association to SalesRecord
	@OneToMany(mappedBy = "i0021")
	private List<SalesRecord> salesRecords;
	
	// bi-directional many-to-one association to UserCreate
		@ManyToOne
		@JoinColumn(name = "user_ID")
		private UserCreate userID;
		
		@Temporal(TemporalType.TIMESTAMP)
		private Date createdDate;
		
		@Temporal(TemporalType.TIMESTAMP)
		private Date updatedDate;

	public I0021() {
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getSales_ID() {
		return this.sales_ID;
	}

	public void setSales_ID(int sales_ID) {
		this.sales_ID = sales_ID;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCommissionAmount() {
		return this.commissionAmount;
	}

	public void setCommissionAmount(String commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getCountry_ID() {
		return this.country_ID;
	}

	public void setCountry_ID(int country_ID) {
		this.country_ID = country_ID;
	}

	public String getCrossCommision() {
		return this.crossCommision;
	}

	public void setCrossCommision(String crossCommision) {
		this.crossCommision = crossCommision;
	}

	public String getCrossTotal() {
		return this.crossTotal;
	}

	public void setCrossTotal(String crossTotal) {
		this.crossTotal = crossTotal;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDamDueDate() {
		return this.damDueDate;
	}

	public void setDamDueDate(Date damDueDate) {
		this.damDueDate = damDueDate;
	}

	public String getDamageReturnReason() {
		return this.damageReturnReason;
	}

	public void setDamageReturnReason(String damageReturnReason) {
		this.damageReturnReason = damageReturnReason;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getEMail() {
		return this.eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public String getFirmType() {
		return this.firmType;
	}

	public void setFirmType(String firmType) {
		this.firmType = firmType;
	}

	public String getNatureOfBusiness() {
		return this.natureOfBusiness;
	}

	public void setNatureOfBusiness(String natureOfBusiness) {
		this.natureOfBusiness = natureOfBusiness;
	}

	public Date getNorDueDate() {
		return this.norDueDate;
	}

	public void setNorDueDate(Date norDueDate) {
		this.norDueDate = norDueDate;
	}

	public String getNorReturnReason() {
		return this.norReturnReason;
	}

	public void setNorReturnReason(String norReturnReason) {
		this.norReturnReason = norReturnReason;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getQuickStatus() {
		return this.quickStatus;
	}

	public void setQuickStatus(String quickStatus) {
		this.quickStatus = quickStatus;
	}

	public Date getSalesOrderDate() {
		return this.salesOrderDate;
	}

	public void setSalesOrderDate(Date salesOrderDate) {
		this.salesOrderDate = salesOrderDate;
	}

	public String getSalesOrderNumber() {
		return this.salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public String getShipingAddress() {
		return this.shipingAddress;
	}

	public void setShipingAddress(String shipingAddress) {
		this.shipingAddress = shipingAddress;
	}

	public String getShippingCharge() {
		return this.shippingCharge;
	}

	public void setShippingCharge(String shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getTotalNumberOfCount() {
		return this.totalNumberOfCount;
	}

	public void setTotalNumberOfCount(String totalNumberOfCount) {
		this.totalNumberOfCount = totalNumberOfCount;
	}

	public List<Dispatch> getDispatches() {
		return this.dispatches;
	}

	public void setDispatches(List<Dispatch> dispatches) {
		this.dispatches = dispatches;
	}

	public Dispatch addDispatch(Dispatch dispatch) {
		getDispatches().add(dispatch);
		dispatch.setI0021(this);

		return dispatch;
	}

	public Dispatch removeDispatch(Dispatch dispatch) {
		getDispatches().remove(dispatch);
		dispatch.setI0021(null);

		return dispatch;
	}

	public List<I0019> getI0019s() {
		return this.i0019s;
	}

	public void setI0019s(List<I0019> i0019s) {
		this.i0019s = i0019s;
	}

	public I0019 addI0019(I0019 i0019) {
		getI0019s().add(i0019);
		i0019.setI0021(this);

		return i0019;
	}

	public I0019 removeI0019(I0019 i0019) {
		getI0019s().remove(i0019);
		i0019.setI0021(null);

		return i0019;
	}

	public I0032 getI0032() {
		return this.i0032;
	}

	public void setI0032(I0032 i0032) {
		this.i0032 = i0032;
	}

	public List<I0022> getI0022s() {
		return this.i0022s;
	}

	public void setI0022s(List<I0022> i0022s) {
		this.i0022s = i0022s;
	}

	public I0022 addI0022(I0022 i0022) {
		getI0022s().add(i0022);
		i0022.setI0021(this);

		return i0022;
	}

	public I0022 removeI0022(I0022 i0022) {
		getI0022s().remove(i0022);
		i0022.setI0021(null);

		return i0022;
	}

	public List<SalesReturn> getSalesReturns() {
		return this.salesReturns;
	}

	public void setSalesReturns(List<SalesReturn> salesReturns) {
		this.salesReturns = salesReturns;
	}

	public SalesReturn addSalesReturn(SalesReturn salesReturn) {
		getSalesReturns().add(salesReturn);
		salesReturn.setI0021(this);

		return salesReturn;
	}

	public SalesReturn removeSalesReturn(SalesReturn salesReturn) {
		getSalesReturns().remove(salesReturn);
		salesReturn.setI0021(null);

		return salesReturn;
	}

	public List<SalesRecord> getSalesRecords() {
		return this.salesRecords;
	}

	public void setSalesRecords(List<SalesRecord> salesRecords) {
		this.salesRecords = salesRecords;
	}

	public SalesRecord addSalesRecord(SalesRecord salesRecord) {
		getSalesRecords().add(salesRecord);
		salesRecord.setI0021(this);

		return salesRecord;
	}

	public SalesRecord removeSalesRecord(SalesRecord salesRecord) {
		getSalesRecords().remove(salesRecord);
		salesRecord.setI0021(null);

		return salesRecord;
	}

	public String getShipping_company() {
		return shipping_company;
	}

	public void setShipping_company(String shipping_company) {
		this.shipping_company = shipping_company;
	}

	public String getChequeno() {
		return chequeno;
	}

	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	private String discamnt;

	private String disctype;

	public String getDiscamnt() {
		return this.discamnt;
	}

	public void setDiscamnt(String discamnt) {
		this.discamnt = discamnt;
	}

	public String getDisctype() {
		return this.disctype;
	}

	public void setDisctype(String disctype) {
		this.disctype = disctype;
	}
	
	private String client_ID;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public UserCreate getUserID() {
		return userID;
	}

	public void setUserID(UserCreate userID) {
		this.userID = userID;
	}

	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
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