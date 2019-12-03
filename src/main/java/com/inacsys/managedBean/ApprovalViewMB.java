package com.inacsys.managedBean;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
//import org.primefaces.model.DefaultStreamedContent;
//import org.primefaces.model.StreamedContent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.Approval;
import com.inacsys.domain.Files;
import com.inacsys.domain.Files2;
import com.inacsys.exception.DemoException;
import com.inacsys.shared.I0033;

/**
 * This Java Class will communicate with InventoryService
 * ------------------------------------------------------------
 * 
 * @author | Created Date | Changed Date | Module |
 *         ------------------------------------------------------------ Alex
 *         02-July-2014 Login Authorization Sivaranjini
 * 
 */

@ManagedBean(name = "approvalViewMB")
public class ApprovalViewMB {
	private String head;
	public Date orderDate;
	public String firmName;
	public float totalAmount;
	public String purchaseNumber;
	public Date fromDate;
	public Date toDate;
	public String validate;
	public String orderNumber;
	private String status = "none";
	public String flag = "none";
	public String flag1 = "none";
	public ArrayList<I0033> filelist = null;
	public ArrayList<ApprovalViewMB> purchaselist;
	private List<Files> fileList = new ArrayList<Files>();
	Approval approval = new Approval();
	private List<Files2> fileList1 = null;

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public List<Files2> getFileList1() {
		return fileList1;
	}

	public void setFileList1(List<Files2> fileList1) {
		this.fileList1 = fileList1;
	}

	private String fileName;

	public List<Files> getFileList() {
		return fileList;
	}

	public void setFileList(List<Files> fileList) {
		this.fileList = fileList;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Approval getApproval() {
		return approval;
	}

	public void setApproval(Approval approval) {
		this.approval = approval;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<I0033> getFilelist() {
		return filelist;
	}

	public void setFilelist(ArrayList<I0033> filelist) {
		this.filelist = filelist;
	}

	public String getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	public ArrayList<ApprovalViewMB> getPurchaselist() {
		return purchaselist;
	}

	public void setPurchaselist(ArrayList<ApprovalViewMB> purchaselist) {
		this.purchaselist = purchaselist;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	ArrayList<String> finallist = null;

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public ArrayList<String> getFinallist() {
		return finallist;
	}

	public void setFinallist(ArrayList<String> finallist) {
		this.finallist = finallist;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	private static Logger logger = Logger.getLogger(ApprovalViewMB.class);

	public String approvalView() {
		logger.info("[approvalView()] --------------- Inside approvalView() method() ------------------------");
		DemoController controller = null;
		try {
			setValidate("");
			flag = "none";
			flag1 = "none";
			if (fromDate == null) {
				logger.info("[approvalView()] --------------- Inside approvalView() method() if condition from date------------------------");
				throw new DemoException("Enter From Date");
			} else if (toDate == null) {
				logger.info("[approvalView()] --------------- Inside approvalView() method() if condition to date------------------------");
				throw new DemoException("Enter To Date");
			}

			else {
				logger.info("[approvalView()] --------------- Inside approvalView mb------------------------");
				purchaselist = null;
				Approval approval = new Approval();
				approval.setFromDate(fromDate);
				approval.setToDate(toDate);
				ApplicationContext ctx = FacesContextUtils
						.getWebApplicationContext(FacesContext
								.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				controller.approvalView(approval);
				setPurchaselist(approval.getPurchaselist());
				logger.debug("list size" + purchaselist.size());
				if (purchaselist.size() == 0) {
					logger.info("[approvalView()] --------------- Inside if purchasde------------------------");
					flag = "none";
					flag1 = "1";
				}
				logger.info("[approvalView()] --------------- outside approvalView mb------------------------");
				flag = "1";
				return "";
			}
		} catch (DemoException ie) {
			logger.debug("" + ie.getMessage());
			setValidate(ie.getMessage());
			logger.debug(ie.getMessage());
			return "";
		} catch (Exception e) {
			logger.debug("error" + e.getMessage());
			return "";
		} finally {
			controller =null;
		}
	}

	public String approvalView2() {
		logger.info("[approvalView2()] --------------- Inside approvalView2() method() ------------------------");
		DemoController controller = null;
		try {
			flag = "none";
			purchaselist = null;
			if (purchaseNumber.equalsIgnoreCase("")) {
				logger.info("[approvalView2()] --------------- Inside approvalView2() method() if condition purchase number ------------------------");
				throw new DemoException("* Select Purchase Reference Number");
			} else {
				logger.info("[approvalView2()] --------------- Inside approvalView2() method() else condition purchase number ------------------------");
				Approval approval = new Approval();
				fromDate = null;
				toDate = null;
				approval.setPurchaseNumber(purchaseNumber);
				ApplicationContext ctx = FacesContextUtils
						.getWebApplicationContext(FacesContext
								.getCurrentInstance());
				controller = (DemoController) ctx.getBean("controller");
				controller.approvalView2(approval);
				setPurchaselist(approval.getPurchaselist());
				if (purchaselist.size() == 0) {
					flag = "none";
					flag1 = "1";
				}
				flag = "1";
			}
			return "";
		}
		catch (DemoException ie) {
			logger.error("" + ie.getMessage());
			setValidate(ie.getMessage());
			return "";
		} finally {
			controller =null;
		}
	}

	public String approvalView3() {
		logger.info("[approvalView3()] --------------- Inside approvalView3() method() ------------------------");
		DemoController controller = null;
		try {
			logger.debug("[approvalView3()] --------------- purchase order number ------------------------>"+orderNumber);
			Approval approval = new Approval();
			fromDate = null;
			toDate = null;
			approval.setPurchaseNumber(orderNumber);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.approvalView3(approval);
			if (approval.getFilelist().size() == 0) {
				throw new DemoException("*no data found.");
			}
			setFilelist(approval.getFilelist());
			logger.debug("[approvalView3()] --------------- file size ------------------------>"+approval.getFilelist().size());
			logger.debug("[approvalView3()] --------------- file path ------------------------>"+approval.getFilelist().get(0).getFilepath());
			logger.debug("[approvalView3()] --------------- file path ------------------------>"+approval.getFilelist().get(0).getFinalfilePath());
			fileList = new ArrayList<Files>();
			int count = 0, count1 = 0;
			for (I0033 i0033 : filelist) {
				logger.debug("[approvalView3()] --------------- count value ------------------------>"+count);
				count++;
				Files files = new Files();
				files.setFileName(i0033.getFinalfilePath());
				files.setFileName1(i0033.getFilepath());
				fileList.add(files);

			}
			fileList1 = new ArrayList<Files2>();
			for (I0033 i0033 : filelist) {
				count1++;
				logger.debug("[approvalView3()] --------------- count1 value ------------------------>"+count1);
				fileList1.add(new Files2(i0033.getFilepath()));
			}
			return "approvalView3success";
		} catch (Exception ie) {
			logger.error("" + ie.getMessage());
			setValidate(ie.getMessage());
			return "approvalView3failure";

		} finally {
			controller =null;
		}
	}

	public String approvalView4() {
		logger.info("[approvalView4()] --------------- Inside approvalView4() method() ------------------------");
		DemoController controller = null;
		try {
			logger.debug("[approvalView4()] --------------- purchase order number ------------------------>"+orderNumber);
			Approval approval = new Approval();
			fromDate = null;
			toDate = null;
			approval.setPurchaseNumber(orderNumber);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.approvalView4(approval);
			if (approval.getFilelist().size() == 0) {
				throw new DemoException("*no data found.");
			}
			setFilelist(approval.getFilelist());
			logger.debug("[approvalView4()] --------------- file size ------------------------>"+approval.getFilelist().size());
			logger.debug("[approvalView4()] --------------- file path ------------------------>"+approval.getFilelist().get(0).getFilepath());
			logger.debug("[approvalView3()] --------------- file path ------------------------>"+approval.getFilelist().get(0).getFinalfilePath());
			fileList = new ArrayList<Files>();
			int count = 0, count1 = 0;
			for (I0033 i0033 : filelist) {
				logger.debug("[approvalView4()] --------------- count value ------------------------>"+count);
				count++;
				Files files = new Files();
				files.setFileName(i0033.getFinalfilePath());
				files.setFileName1(i0033.getFilepath());
				fileList.add(files);
			}
			fileList1 = new ArrayList<Files2>();
			for (I0033 i0033 : filelist) {
				count1++;
				logger.debug("[approvalView4()] --------------- count1 value ------------------------>"+count1);
				fileList1.add(new Files2(i0033.getFilepath()));

			}
			flag = "none";
			return "approvalView4success";
		} catch (Exception ie) {
			flag = "none";
			logger.error("" + ie.getMessage());
			setValidate(ie.getMessage());
			return "approvalView4success";
		} finally {
			controller =null;
		}
	}

	public String fileCall() {
		logger.info("[fileCall()] --------------- Inside fileCall() method() ------------------------");
		try {
			head = "Uploaded Files";
			status = "inherit";
		} catch (Exception e) {
			logger.error("Inside Exception",e);
		} finally {
			
		}
		return "";
	}

	public StreamedContent getdFile() throws IOException {
		logger.info("[getdFile()] --------------- Inside getdFile() method() ------------------------");
		String contentType;
		try {
			DemoController controller = null;
			logger.info("dfile");
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			logger.debug("[getdFile()] --------------- file path dfile1 ------------------------>"+"---- d:\\doc\\ ---->"+fileName);
			contentType = FacesContext.getCurrentInstance()
					.getExternalContext().getMimeType("d:\\doc\\" + fileName);
			return new DefaultStreamedContent(new FileInputStream("d:\\doc\\"
					+ fileName), contentType, fileName);
		} catch (Exception e) {
			logger.error("Inside Exception",e);
			return null;
		}
	}

	public String approvalDropdown() {
		logger.info("[approvalDropdown()] --------------- Inside approvalDropdown() method() ------------------------");
		DemoController controller = null;
		try {
			Approval approval = new Approval();
			approval.setFromDate(fromDate);
			approval.setToDate(toDate);
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			controller.approvalView1(approval);
			setFinallist(approval.getFinallist());
			logger.debug("[approvalDropdown()] --------------- Inside approvalDropdown() method() final list ------------------------>"+finallist);
			if (finallist.size() == 0) {
				flag = "none";
			}
			return "success";
		} catch (DemoException ie) {
			logger.error("" + ie.getMessage());
			setValidate(ie.getMessage());
			return "failure";
		} finally {
			controller =null;
		}
	}

	public String cancel() {
		logger.info("[cancel()] --------------- Inside cancel() method() ------------------------");
		try {
			setValidate("");
			setFirmName("");
			flag = "none";
			flag1 = "none";
		} catch (Exception e) {
			logger.error("" + e.getMessage());
		}

		finally {
			setValidate("");
			setFirmName("");
			setFromDate(null);
			setToDate(null);
			flag = "none";
			flag1 = "none";

		}

		return "gotoapprovalhome";

	}

	public String approvalDirect() {
		logger.info("[approvalDirect()] --------------- Inside approvalDirect() method() ------------------------");
		try {
			setValidate("");
			setFirmName("");
			flag = "none";
			flag1 = "none";
		} catch (Exception e) {
			logger.error("" + e.getMessage());
		}

		finally {
			setValidate("");
			setFirmName("");
			setFromDate(null);
			setToDate(null);
			flag = "none";
			flag1 = "none";

		}

		return "approvalDirect";
	}
}
