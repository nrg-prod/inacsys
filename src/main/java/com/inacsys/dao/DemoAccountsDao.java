package com.inacsys.dao;

import java.util.ArrayList;
import java.util.List;

import com.inacsys.domain.ATransaction;
import com.inacsys.domain.AccountsDatabean;
import com.inacsys.domain.EmployeeDetail;
import com.inacsys.domain.EmployeePayroll;
import com.inacsys.domain.PurchaseOrder;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.AccountDeposit;
import com.inacsys.shared.AccountPayment;
import com.inacsys.shared.AccountType;
import com.inacsys.shared.ChartOfAccount;
import com.inacsys.shared.Designation;
import com.inacsys.shared.Employee;
import com.inacsys.shared.ExpenseAccountsPayment;
import com.inacsys.shared.ExpenseCoa;
import com.inacsys.shared.ExpenseTransaction;
import com.inacsys.shared.GstAcct;
import com.inacsys.shared.I0015;
import com.inacsys.shared.I0021;
import com.inacsys.shared.I0022;
import com.inacsys.shared.I0023;
import com.inacsys.shared.I0032;
import com.inacsys.shared.IncomeAcct;
import com.inacsys.shared.JournalEntry;
import com.inacsys.shared.Month;
import com.inacsys.shared.Qualification;
import com.inacsys.shared.SalesAccountsPayment;
import com.inacsys.shared.SalesTransaction;
import com.inacsys.shared.Transaction;
import com.inacsys.shared.Year;

public interface DemoAccountsDao {
	public List<String> designationInfo(List<String> designate)
			throws DemoException;

	public List<Qualification> qualificationInfo(List<Qualification> qualificate)
			throws DemoException;

	public String employee(EmployeeDetail employee) throws DemoException;

	public String employeeIdInfo(EmployeeDetail employee) throws DemoException;

	public String employeeNameInfo(EmployeeDetail employee)
			throws DemoException;

	public List<String> employeeId(List<String> emploeid) throws DemoException;

	public List<String> employeeName(List<String> emploename)
			throws DemoException;

	public List<EmployeeDetail> getEmployeeDetail(EmployeeDetail employee)
			throws DemoException;

	public List<EmployeeDetail> getEmployeeDetailEdit(EmployeeDetail employee)
			throws DemoException;

	public List<Month> monthInfo(List<Month> monthinfo) throws DemoException;

	public List<Year> yearInfo(List<Year> yearinfo) throws DemoException;

	public ArrayList<String> changeEvent(String s, ArrayList<String> list1)
			throws DemoException;

	public ArrayList<String> changeEvent1(String s, ArrayList<String> list2)
			throws DemoException;

	public List<Employee> payroll(List<Employee> list3, EmployeePayroll save)
			throws DemoException;

	public List<Employee> payrollemp(List<Employee> list3, EmployeePayroll save)
			throws DemoException;

	public String confirm(EmployeePayroll save) throws DemoException;

	/* udhaya 23.1.2015 */
	public String payroll(EmployeePayroll pay) throws DemoException;

	public ArrayList<String> changeEvent2(String s, ArrayList<String> list1)
			throws DemoException;

	public ArrayList<String> changeEvent3(String s, ArrayList<String> list2)
			throws DemoException;

	public String payroll1(EmployeePayroll pay) throws DemoException;

	/* UDHAYA 28.1.2015 */
	public List<EmployeePayroll> viewPayroll(EmployeePayroll view)
			throws DemoException;

	public String search(EmployeePayroll pay) throws DemoException;

	public List<EmployeePayroll> editPayroll(EmployeePayroll view)
			throws DemoException;

	/* kasthu */
	public String search1(EmployeePayroll pay) throws DemoException;

	public String search2(EmployeePayroll pay) throws DemoException;

	public List<EmployeePayroll> viewPayrollz(EmployeePayroll view)
			throws DemoException;

	public String employeeIdSearch(EmployeeDetail empid) throws DemoException;

	public String chequeCredit(ATransaction save) throws DemoException;

	/* udhaya 29.1.2015 */
	public String saveconfirm(ATransaction save) throws DemoException;

	public ArrayList<String> changezEvent(String s, ArrayList<String> list1)
			throws DemoException;

	/* udhaya 30.1.2015 */
	public String transactionView(ATransaction search) throws DemoException;

	public List<ATransaction> getviewForm(ATransaction view)
			throws DemoException;

	public List<ATransaction> getEditForm(ATransaction edit)
			throws DemoException;

	/* udhaya 09.02.2015 */
	public List<ATransaction> getStatusChange(ATransaction change)
			throws DemoException;

	public String onloadEmpView(EmployeeDetail employee) throws DemoException;

	public List<Employee> empInfo(List<Employee> empinfo) throws DemoException;

	public List<Employee> nameInfo(List<Employee> nameinfo)
			throws DemoException;

	public String employeeDetails(EmployeeDetail employee) throws DemoException;

	public List<Employee> employeeNameInfo(EmployeeDetail employee,
			String golbalnamesearch) throws DemoException;

	public List<EmployeePayroll> getEmployeePayrollDetails(EmployeePayroll employeePayroll);
	
	public void updatequalify(String qualID);

	public void updateExpernce(String expID);
	
	public String saveAcountDeposit(String clientID, ATransaction payment);

	public List<String> getAccountDepsit(String clientID, ATransaction atrans);

	public String saveTransPayment(String clientID, ATransaction atransaction);

	public List<String> getAccountDetailsType(String account_type);

	public String saveAcountDeposit(String clientID,
			AccountsDatabean accountsDatabean);

	public List<AccountType> getAllTypes();

	public List<AccountDeposit> getDeposit(String clientID, String description, AccountsDatabean accountsDatabean);

	public List<Transaction> getTransactionDetails(String clientID);

	public List<AccountPayment> getPaymentDetails(int transactionId);

	public List<I0021> getSalesDetails(String clientID);

	public List<I0022> getSalesInvoice(int sales_ID);

	public List<I0023> getSalesPayment(int invoice_ID);

	public List<I0015> getPurchaseDetails(String clientID);

	public List<I0022> getPurchaseInvoice(int purchase_ID);

	public List<AccountDeposit> getDeposits(String clientID,AccountsDatabean accountsDatabean);
	
	public String getType(String description);

	public String getVendorName(int purchase_ID);

	public List<Transaction> getTransactionDetails(String clientID,AccountsDatabean accountsDatabean);

	public List<AccountPayment> getPaymentDetails(int transactionId,AccountsDatabean accountsDatabean);

	public List<I0021> getSalesDetails(String clientID,AccountsDatabean accountsDatabean);

	public List<I0022> getSalesInvoice(int sales_ID,AccountsDatabean accountsDatabean);

	public List<I0023> getSalesPayment(int invoice_ID,AccountsDatabean accountsDatabean);

	public List<I0015> getPurchaseDetails(String clientID,AccountsDatabean accountsDatabean);

	public List<I0022> getPurchaseInvoice(int purchase_ID,AccountsDatabean accountsDatabean);
	
	public void expenseResource(String clientID, ATransaction aTransaction);

	public void getsalestransactioncustprod(String clientID,ATransaction aTransaction);

	public List<ChartOfAccount> accountlist(String clientID);

	public void accountbalance(String clientID, ATransaction aTransaction);

	public String saveexpenseTrans(ATransaction aTransaction, String clientID);

	public List<ExpenseTransaction> getbillnumber(String clientID,String transactionType);

	public void mailresource(String clientID, ATransaction aTransaction);

	public List<ExpenseTransaction> expensesDataTable(String clientID);

	public List<ExpenseTransaction> expanseDetailView(String clientID, ATransaction aTransaction);

	public List<ExpenseCoa> getTransactionAccount(int transactionID, String accountstatustype);

	public String expenseUpdate(ATransaction aTransaction, String clientID);

	public List<ExpenseAccountsPayment> expensepaymentDetail(int expense_transaction_ID);

	public String expenseMakePayment(ATransaction aTransaction, String clientID);

	public List<ChartOfAccount> getaccountnamedetail(String clientID,String account_name);

	public String editAccount(String clientID, AccountsDatabean accountsDatabean);

	public void getcustomerdetails(String clientID, ATransaction aTransaction);

	public List<AccountType> getCategoryTypes();

	public String saveSalesTransaction(String clientID,ATransaction aTransaction, List<ATransaction> productdetails);

	public List<SalesTransaction> getsalestransactionNumber(String clientID,String transactionType);

	public List<SalesTransaction> getsalestransactiontableview(String clientID,ATransaction aTransaction);

	public String generateInvoice(String clientID, int transactionID);

	public List<SalesTransaction> getcustomersalestransactionList(String clientID, String paymentStatus, ATransaction aTransaction);

	public String salesTransactionUpadte(String clientID,ATransaction aTransaction);

	public List<SalesTransaction> salesTransactionView(String clientID,ATransaction aTransaction);

	public List<SalesAccountsPayment> salesTransactionPaymentview(int sales_transaction_ID, String clientID);
		
	public String invoiceSales1(PurchaseOrder purchaseOrder)throws DemoException;
	
	public String updateSales1(PurchaseOrder purchaseOrder)throws DemoException;

	public String invoicePurhcase(PurchaseOrder purchaseOrder)throws DemoException;

	public String purchaseProductEdit(PurchaseOrder purchaseOrder)throws DemoException;

	public List<JournalEntry> getjournalEntryList(AccountsDatabean aTransaction);
	
	public List<ChartOfAccount> getchartofaccountList(String clientID);

	public List<ExpenseAccountsPayment> expenseaccountpaymentDetail(String clientID);

	public String qucikSalesConform1(PurchaseOrder purchaseOrder)throws DemoException;
}