package com.inacsys.managedBean;

import javax.faces.bean.ManagedBean;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "purchaseInvoiceForm")
public class PurchaseInvoiceForm {

	public String redirect() {
		return "sucess";

	}

}
