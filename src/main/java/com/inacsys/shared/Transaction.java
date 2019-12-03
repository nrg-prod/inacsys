package com.inacsys.shared;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@Table(name = "transaction")
@NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "transaction_id")
	private int transactionId;

	private String amount;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "card_no")
	private String cardNo;

	@Temporal(TemporalType.DATE)
	@Column(name = "cheque_date")
	private Date chequeDate;

	@Column(name = "cheque_no")
	private String chequeNo;

	private String note;

	private String particulars;

	@Column(name = "payment_mode")
	private String paymentMode;
	
	@Column(name = "payment_status")
	private String paymentStatus;

	private String status;

	@Temporal(TemporalType.DATE)
	@Column(name = "transaction_date")
	private Date transactionDate;

	@Column(name = "transaction_no")
	private String transactionNo;

	@Column(name = "transaction_type")
	private String transactionType;

	@Temporal(TemporalType.DATE)
	@Column(name = "pay_date")
	private Date payDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Date dueDate;
	
	@Column(name = "mailing_address")
	private String mailingAddress;
	
	@Column(name = "file_path")
	private String filePath;

	@Column(name = "transaction_status")
	private String transactionStatus;
	
	@Column(name = "currency_type")
	private String currencyType;
	
	@Column(name = "currency")
	private String currency;
	
	// bi-directional many-to-one association to AccPayableLiability
	@OneToMany(mappedBy = "transaction")
	private List<AccPayableLiability> accPayableLiabilities;

	// bi-directional many-to-one association to AccReceivableAsset
	@OneToMany(mappedBy = "transaction")
	private List<AccReceivableAsset> accReceivableAssets;

	// bi-directional many-to-one association to CashAsset
	@OneToMany(mappedBy = "transaction")
	private List<CashAsset> cashAssets;

	private String client_ID;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}
	public Transaction() {
	}

	public int getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Date getChequeDate() {
		return this.chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public String getChequeNo() {
		return this.chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getParticulars() {
		return this.particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionNo() {
		return this.transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public List<AccPayableLiability> getAccPayableLiabilities() {
		return this.accPayableLiabilities;
	}

	public void setAccPayableLiabilities(
			List<AccPayableLiability> accPayableLiabilities) {
		this.accPayableLiabilities = accPayableLiabilities;
	}

	public AccPayableLiability addAccPayableLiability(
			AccPayableLiability accPayableLiability) {
		getAccPayableLiabilities().add(accPayableLiability);
		accPayableLiability.setTransaction(this);

		return accPayableLiability;
	}

	public AccPayableLiability removeAccPayableLiability(
			AccPayableLiability accPayableLiability) {
		getAccPayableLiabilities().remove(accPayableLiability);
		accPayableLiability.setTransaction(null);

		return accPayableLiability;
	}

	public List<AccReceivableAsset> getAccReceivableAssets() {
		return this.accReceivableAssets;
	}

	public void setAccReceivableAssets(
			List<AccReceivableAsset> accReceivableAssets) {
		this.accReceivableAssets = accReceivableAssets;
	}

	public AccReceivableAsset addAccReceivableAsset(
			AccReceivableAsset accReceivableAsset) {
		getAccReceivableAssets().add(accReceivableAsset);
		accReceivableAsset.setTransaction(this);

		return accReceivableAsset;
	}

	public AccReceivableAsset removeAccReceivableAsset(
			AccReceivableAsset accReceivableAsset) {
		getAccReceivableAssets().remove(accReceivableAsset);
		accReceivableAsset.setTransaction(null);

		return accReceivableAsset;
	}

	public List<CashAsset> getCashAssets() {
		return this.cashAssets;
	}

	public void setCashAssets(List<CashAsset> cashAssets) {
		this.cashAssets = cashAssets;
	}

	public CashAsset addCashAsset(CashAsset cashAsset) {
		getCashAssets().add(cashAsset);
		cashAsset.setTransaction(this);

		return cashAsset;
	}

	public CashAsset removeCashAsset(CashAsset cashAsset) {
		getCashAssets().remove(cashAsset);
		cashAsset.setTransaction(null);

		return cashAsset;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
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