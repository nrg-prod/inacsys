package com.inacsys.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the account_deposit database table.
 * 
 */
@Entity
@Table(name="sequance_number")
@NamedQuery(name="Sequance_number.findAll", query="SELECT a FROM Sequance_number a")
public class Sequance_number implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sequance_number_ID")
	private int sequance_number_ID;

	@Column(name="sales_quote_count")
	private long sales_quote_count;//getquotationcode() ;

	public int getSequance_number_ID() {
		return sequance_number_ID;
	}

	public void setSequance_number_ID(int sequance_number_ID) {
		this.sequance_number_ID = sequance_number_ID;
	}

	public long getSales_quote_count() {
		return sales_quote_count;
	}

	public void setSales_quote_count(long sales_quote_count) {
		this.sales_quote_count = sales_quote_count;
	}

	
}