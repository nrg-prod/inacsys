package com.inacsys.shared;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Value;

import com.inacsys.domain.ATransaction;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.util.Util;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the sales_transaction database table.
 * 
 */
@Entity
@Table(name="sales_transaction")
@NamedQuery(name="SalesTransaction.findAll", query="SELECT s FROM SalesTransaction s")
public class SalesTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sales_transaction_ID")
	private int sales_transaction_ID;

	@Column(name="approval_status")
	private String approvalStatus;

	private String client_ID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name="customer_name")
	private String customerName;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Column(name="mail_id")
	private String mailId;

	@Column(name="mailing_address")
	private String mailingAddress;

	@Column(name="payment_method")
	private String paymentMethod;

	@Column(name="reference_number")
	private String referenceNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	private String status;

	private String terms;
	
	@Column(name="balance_amount")
	private String balanceAmount;
	
	@Column(name="place_of_location")
	private String placeofLocation;
	
	@Column(name="tax_type")
	private String taxType;
	
	@Column(name="paid_amount")
	private String paidAmount;

	@Column(name="transaction_amount")
	private String transactionAmount;
	
	@Column(name="currency_amount")
	private String currencyAmount;

	@Column(name="tax_amount")
	private String taxAmount;
	
	@Column(name="currency_tax_amount")
	private String currencyTaxAmount;
	
	@Column(name="before_tax_amount")
	private String beforetaxAmount;
	
	@Column(name="currency_before_tax_amount")
	private String currencybeforetaxAmount;
	
	@Column(name="transaction_type")
	private String transactionType;
	
	@Column(name="currency_type")
	private String currencyType;
	
	@Column(name="invoice_status")
	private String invoiceStatus;
	
	@Column(name="payment_status")
	private String paymentStatus;
	
	@Column(name="sub_total_amount")
	private String subTotalAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="updated_date")
	private Date updatedDate;
	
	//bi-directional many-to-one association to SalesAccountsPayment
	@OneToMany(mappedBy="salestransaction", cascade = CascadeType.ALL) 
	private Set<SalesAccountsPayment> salesaccountpayments;
	
	//bi-directional many-to-one association to SalesCoa
	@OneToMany(mappedBy="salestransaction", cascade = CascadeType.ALL) 
	private Set<SalesCoa> salesCoas;
	
	//bi-directional many-to-one association to SalesCoa
	@OneToMany(mappedBy="salestransaction", cascade = CascadeType.ALL) 
	private Set<AccountReceivableAcct> accountReceivableAccts;
	
	//bi-directional many-to-one association to SalesCoa
	@OneToMany(mappedBy="salestransaction", cascade = CascadeType.ALL) 
	private Set<GstAcct> gstAccts;
	
	private String baseCurrency;
	
	private String depositAccount;
		
	public SalesTransaction() {
	}

	public SalesTransaction(ATransaction aTransaction) {
		BigDecimal tempamt=BigDecimal.valueOf(0);
		this.transactionType = aTransaction.getTransactionType();
		this.client_ID = aTransaction.getClientID();
		this.customerName = aTransaction.getCustomerName();
		this.mailingAddress = aTransaction.getCustomerBillingAddress();
		this.startDate = aTransaction.getBillDate();
		this.dueDate = aTransaction.getDueDate();
		this.description = aTransaction.getDescription();
		this.transactionAmount = aTransaction.getTotalAmount();
		this.paymentMethod = aTransaction.getPaymentMode();
		this.referenceNumber = aTransaction.getTransactionNo();
		this.status = aTransaction.getStatus();
        this.createdDate = new Date();        
        this.approvalStatus = aTransaction.getApprovalStatus();
        this.mailId = aTransaction.getCustomerEmailId();
        this.terms = aTransaction.getTerms();
        this.balanceAmount = aTransaction.getBalAmount();
        this.paidAmount = aTransaction.getReceiveAmount();
        this.currencyType=aTransaction.getCurrencyType();
        this.invoiceStatus = aTransaction.getTransStatus();
        this.paymentStatus = aTransaction.getPaymentStatus();
        if(aTransaction.getTransactionType().equalsIgnoreCase("Credit Memo")){
        	this.currencyAmount = tempamt.subtract(new BigDecimal(aTransaction.getCurrencyAmount())).toString();
        	this.currencybeforetaxAmount = tempamt.subtract(new BigDecimal(aTransaction.getCurrencybeforeTaxAmount())).toString();
			this.currencyTaxAmount = tempamt.subtract(new BigDecimal(aTransaction.getCurrencytaxAmount())).toString();
        }else{
        	this.currencyAmount = aTransaction.getCurrencyAmount();
        	this.currencybeforetaxAmount = aTransaction.getCurrencybeforeTaxAmount();
        	this.currencyTaxAmount = aTransaction.getCurrencytaxAmount();
        }
        this.depositAccount = aTransaction.getAccountType();
        this.placeofLocation = aTransaction.getLocation();
        this.taxType = aTransaction.getTaxType();
        this.beforetaxAmount = aTransaction.getBeforeTaxAmount();
        this.taxAmount = aTransaction.getTaxAmount();
        this.baseCurrency = aTransaction.getBaseCurrency();
        this.subTotalAmount = aTransaction.getSubTotalAmount();
    }

	public SalesTransaction(String clientID,PurchaseOrder purchaseOrder){
		this.transactionType = purchaseOrder.getTransactionType();
		this.customerName = purchaseOrder.getCustomername();
		this.referenceNumber = purchaseOrder.getSalesIdReference();
		this.transactionAmount = purchaseOrder.getCrosstotal1();
		this.balanceAmount = purchaseOrder.getCurrencyAmount();
		this.startDate = purchaseOrder.getOrderDate();
		this.dueDate = purchaseOrder.getDueDate();
		this.currencyType = purchaseOrder.getCurrency();
		this.createdDate = new Date();
		this.paymentStatus = purchaseOrder.getPaymentStatus();
		this.invoiceStatus = purchaseOrder.getStatus2();
		this.approvalStatus = "draft";
		this.status = "Active";
		this.client_ID = clientID;
		this.paidAmount = purchaseOrder.getPayableAmount();
        this.balanceAmount = purchaseOrder.getCurrencyAmount();
    	this.currencyAmount = purchaseOrder.getCurrencyAmount();
    	this.currencybeforetaxAmount = purchaseOrder.getCurrencyAmount();
    	this.currencyTaxAmount = "0";
        this.taxType = "Out of scope of Tax";
        this.beforetaxAmount = purchaseOrder.getCrosstotal1();
        this.taxAmount = "0";
        this.baseCurrency = purchaseOrder.getBaseCurrency();
        this.subTotalAmount = purchaseOrder.getCrosstotal1();
	}
	
	public String getDepositAccount() {
		return depositAccount;
	}

	public void setDepositAccount(String depositAccount) {
		this.depositAccount = depositAccount;
	}

	public String getSubTotalAmount() {
		return subTotalAmount;
	}

	public void setSubTotalAmount(String subTotalAmount) {
		this.subTotalAmount = subTotalAmount;
	}

	public String getCurrencybeforetaxAmount() {
		return currencybeforetaxAmount;
	}

	public void setCurrencybeforetaxAmount(String currencybeforetaxAmount) {
		this.currencybeforetaxAmount = currencybeforetaxAmount;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public Set<GstAcct> getGstAccts() {
		return gstAccts;
	}

	public void setGstAccts(Set<GstAcct> gstAccts) {
		this.gstAccts = gstAccts;
	}

	public String getCurrencyTaxAmount() {
		return currencyTaxAmount;
	}

	public void setCurrencyTaxAmount(String currencyTaxAmount) {
		this.currencyTaxAmount = currencyTaxAmount;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getBeforetaxAmount() {
		return beforetaxAmount;
	}

	public void setBeforetaxAmount(String beforetaxAmount) {
		this.beforetaxAmount = beforetaxAmount;
	}

	public String getPlaceofLocation() {
		return placeofLocation;
	}

	public void setPlaceofLocation(String placeofLocation) {
		this.placeofLocation = placeofLocation;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public String getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(String currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public Set<AccountReceivableAcct> getAccountReceivableAccts() {
		return accountReceivableAccts;
	}

	public void setAccountReceivableAccts(Set<AccountReceivableAcct> accountReceivableAccts) {
		this.accountReceivableAccts = accountReceivableAccts;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public int getSales_transaction_ID() {
		return this.sales_transaction_ID;
	}

	public void setSales_transaction_ID(int sales_transaction_ID) {
		this.sales_transaction_ID = sales_transaction_ID;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getClient_ID() {
		return this.client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getMailId() {
		return this.mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getMailingAddress() {
		return this.mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTerms() {
		return this.terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getTransactionAmount() {
		return this.transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Set<SalesAccountsPayment> getSalesaccountpayments() {
		return salesaccountpayments;
	}

	public void setSalesaccountpayments(
			Set<SalesAccountsPayment> salesaccountpayments) {
		this.salesaccountpayments = salesaccountpayments;
	}

	
	/*public SalesAccountsPayment addSalesAccountsPayment(SalesAccountsPayment salesAccountsPayment) {
		getSalesAccountsPayments().add(salesAccountsPayment); 
		salesAccountsPayment.setSalesTransaction(this);

		return salesAccountsPayment;
	}

	public SalesAccountsPayment removeSalesAccountsPayment(SalesAccountsPayment salesAccountsPayment) {
		getSalesAccountsPayments().remove(salesAccountsPayment);
		salesAccountsPayment.setSalesTransaction(null);

		return salesAccountsPayment;
	}*/
	
	
	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Set<SalesCoa> getSalesCoas() {
		return salesCoas;
	}

	public void setSalesCoas(Set<SalesCoa> salesCoas) {
		this.salesCoas = salesCoas;
	}

	/*public SalesCoa addSalesCoa(SalesCoa salesCoa) {
		getSalesCoas().add(salesCoa);
		salesCoa.setSalesTransaction(this);

		return salesCoa;
	}

	public SalesCoa removeSalesCoa(SalesCoa salesCoa) {
		getSalesCoas().remove(salesCoa);
		salesCoa.setSalesTransaction(null);

		return salesCoa;
	}
*/
}