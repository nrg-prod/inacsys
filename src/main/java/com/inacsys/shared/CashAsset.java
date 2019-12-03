package com.inacsys.shared;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the cash_asset database table.
 * 
 */
@Entity
@Table(name = "cash_asset")
@NamedQuery(name = "CashAsset.findAll", query = "SELECT c FROM CashAsset c")
public class CashAsset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cash_asset_id")
	private int cashAssetId;

	private String status;

	// bi-directional many-to-one association to Transaction
	@ManyToOne
	@JoinColumn(name = "transaction_id")
	private Transaction transaction;

	public CashAsset() {
	}

	public int getCashAssetId() {
		return this.cashAssetId;
	}

	public void setCashAssetId(int cashAssetId) {
		this.cashAssetId = cashAssetId;
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