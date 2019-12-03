package com.inacsys.shared;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name = "sales_quote_details")
@NamedQuery(name="SalesQuoteDetails.findAll", query="SELECT p FROM SalesQuoteDetails p")
public class SalesQuoteDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sales_quote_details_ID")
	private int sales_quote_details_ID;
	private String description;
	private String name;
	private String price;
	private String netprize;


	//bi-directional many-to-one association to Category
	@ManyToOne(optional=false)
	@JoinColumn(name="sales_quote_id")
	private SalesQuote salesquote;

	public SalesQuoteDetails() {
	}
	
	
	public int getSales_quote_details_ID() {
		return sales_quote_details_ID;
	}

	public void setSales_quote_details_ID(int sales_quote_details_ID) {
		this.sales_quote_details_ID = sales_quote_details_ID;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getNetprize() {
		return netprize;
	}


	public void setNetprize(String netprize) {
		this.netprize = netprize;
	}


	public SalesQuote getSalesquote() {
		return salesquote;
	}

	public void setSalesquote(SalesQuote salesquote) {
		this.salesquote = salesquote;
	}
	

}