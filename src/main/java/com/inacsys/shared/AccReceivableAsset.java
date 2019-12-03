package com.inacsys.shared;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the acc_receivable_asset database table.
 * 
 */
@Entity
@Table(name = "acc_receivable_asset")
@NamedQuery(name = "AccReceivableAsset.findAll", query = "SELECT a FROM AccReceivableAsset a")
public class AccReceivableAsset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "acc_receivable_asset_id")
	private int accReceivableAssetId;

	private String status;

	// bi-directional many-to-one association to Transaction
	@ManyToOne
	@JoinColumn(name = "transaction_id")
	private Transaction transaction;

	public AccReceivableAsset() {
	}

	public int getAccReceivableAssetId() {
		return this.accReceivableAssetId;
	}

	public void setAccReceivableAssetId(int accReceivableAssetId) {
		this.accReceivableAssetId = accReceivableAssetId;
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