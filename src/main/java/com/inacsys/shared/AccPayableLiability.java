package com.inacsys.shared;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the acc_payable_liability database table.
 * 
 */
@Entity
@Table(name = "acc_payable_liability")
@NamedQuery(name = "AccPayableLiability.findAll", query = "SELECT a FROM AccPayableLiability a")
public class AccPayableLiability implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "acc_payable_liability_id")
	private int accPayableLiabilityId;

	private String status;

	// bi-directional many-to-one association to Transaction
	@ManyToOne
	@JoinColumn(name = "transaction_id")
	private Transaction transaction;

	public AccPayableLiability() {
	}

	public int getAccPayableLiabilityId() {
		return this.accPayableLiabilityId;
	}

	public void setAccPayableLiabilityId(int accPayableLiabilityId) {
		this.accPayableLiabilityId = accPayableLiabilityId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}