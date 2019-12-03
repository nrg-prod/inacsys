package com.inacsys.managedBean;

import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.ApplicationContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.extensions.event.WaypointEvent.Direction;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.Chartcount;
import com.inacsys.domain.PurchasecountDomain;
import com.inacsys.domain.VendorCount;
import com.inacsys.util.ChartcountJDBC;
import com.inacsys.util.purchasecountJDBC;

import org.primefaces.model.chart.ChartSeries;

/* John begin 23/05/2016 chart for sales,Purchase,inventory In & Out count*/

@ManagedBean(name = "ChartView2")
public class ChartView2 implements Serializable {
	private static Logger logger = Logger.getLogger(ChartView2.class);
	private static final long serialVersionUID = 362498820763181265L;

	Chartcount chartcount = new Chartcount();
	private BarChartModel purchaseBarModel1;
	private HorizontalBarChartModel horizontalBarModel1;
	private HorizontalBarChartModel horizontalBarModel2;
	private PieChartModel pieModel1;
	private PieChartModel pieModel2;

	DemoController controller = null;

	public List<Chartcount> countList = null;
	public List<PurchasecountDomain> purchasecountList = null;
	public int salescount;
	public int purchasecount;
	public int stockincount;
	public int stockoutcount;
	public int salespcount;
	public int purchasepcount;
	public int stockinpcount;
	public int stockoutpcount;
	public int countmax;
	public int countpmax;

	// ////////////

	public int purchasemax;

	public int getPurchasemax() {
		return purchasemax;
	}

	public void setPurchasemax(int purchasemax) {
		this.purchasemax = purchasemax;
	}

	// //////////////
	public int getCountmax() {
		return countmax;
	}

	public void setCountmax(int countmax) {
		this.countmax = countmax;
	}

	public int getCountpmax() {
		return countpmax;
	}

	public void setCountpmax(int countpmax) {
		this.countpmax = countpmax;
	}

	public int getSalescount() {
		return salescount;
	}

	public void setSalescount(int salescount) {
		this.salescount = salescount;
	}

	public int getPurchasecount() {
		return purchasecount;
	}

	public void setPurchasecount(int purchasecount) {
		this.purchasecount = purchasecount;
	}

	public int getStockincount() {
		return stockincount;
	}

	public void setStockincount(int stockincount) {
		this.stockincount = stockincount;
	}

	public int getStockoutcount() {
		return stockoutcount;
	}

	public void setStockoutcount(int stockoutcount) {
		this.stockoutcount = stockoutcount;
	}

	public int getSalespcount() {
		return salespcount;
	}

	public void setSalespcount(int salespcount) {
		this.salespcount = salespcount;
	}

	public int getPurchasepcount() {
		return purchasepcount;
	}

	public void setPurchasepcount(int purchasepcount) {
		this.purchasepcount = purchasepcount;
	}

	public int getStockinpcount() {
		return stockinpcount;
	}

	public void setStockinpcount(int stockinpcount) {
		this.stockinpcount = stockinpcount;
	}

	public int getStockoutpcount() {
		return stockoutpcount;
	}

	public void setStockoutpcount(int stockoutpcount) {
		this.stockoutpcount = stockoutpcount;
	}

	public String business() {
		logger.info("[business()] --------------- Inside business() method() ------------------------");
		try {
			countList = new ArrayList<Chartcount>();
			countList = ChartcountJDBC.countSearch();
			logger.debug("[business()] --------------- country list ------------------------>"+countList.size());
			if (countList.size() != 0) {
				setSalescount(countList.get(0).getSalesCount());
				setPurchasecount(countList.get(0).getPurchaseCount());
				setStockincount(countList.get(0).getStockInCount());
				setStockoutcount(countList.get(0).getStockOutCount());
				setSalespcount(countList.get(0).getSalesProductCount());
				setPurchasepcount(countList.get(0).getPurchaseProductCount());
				setStockinpcount(countList.get(0).getStockInProductCount());
				setStockoutpcount(countList.get(0).getStockOutProductCount());
				logger.debug("[business()] --------------- sales,purchase,stockIn,stockout ------------------------>"+countList.get(0).getSalesCount()+"-->"+countList.get(0).getPurchaseCount()+"-->"+countList.get(0).getStockInCount()+"-->"+countList.get(0).getStockOutCount());
			} else {
				logger.info("[business()] --------------- out ------------------------");
			}
			setCountmax(0);
			createBarModels();

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
		return "";

	}

	@PostConstruct
	public void init() {
		business();

	}

	public BarChartModel getpurchaseBarModel1() {
		return purchaseBarModel1;
	}

	public HorizontalBarChartModel getHorizontalBarModel1() {
		return horizontalBarModel1;
	}

	public HorizontalBarChartModel getHorizontalBarModel2() {
		return horizontalBarModel2;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	private void createBarModels() {
		setmax();
		createHorizontalBarModel1();
		createPieModel1();

	}

	private void createHorizontalBarModel1() {
		logger.info("[createHorizontalBarModel1()] --------------- inside createHorizontalBarModel1() method ------------------------");
		horizontalBarModel1 = new HorizontalBarChartModel();
		ChartSeries count = new ChartSeries();
		count.set("Inventory Out", stockoutcount);
		count.set("Inventory In", stockincount);
		count.set("Purchase", purchasecount);
		count.set("Sales", salescount);
		count.setLabel("Count");
		horizontalBarModel1.addSeries(count);
		horizontalBarModel1.setTitle("");
		horizontalBarModel1.setLegendPosition("e");
		horizontalBarModel1.setStacked(false);
		horizontalBarModel1.setBarWidth(20);
		horizontalBarModel1.setMouseoverHighlight(true);
		horizontalBarModel1.setSeriesColors("EA4335");
		Axis xAxis = horizontalBarModel1.getAxis(AxisType.X);
		xAxis.setMin(0);
		xAxis.setMax(countmax);
	}

	private void createHorizontalBarModel2() {
		logger.info("[createHorizontalBarModel2()] --------------- inside createHorizontalBarModel2() method ------------------------");
		horizontalBarModel2 = new HorizontalBarChartModel();
		ChartSeries count = new ChartSeries();
		count.set("Inventory Out", stockoutpcount);
		count.set("Inventory In", stockinpcount);
		count.set("Purchase", purchasepcount);
		count.set("Sales", salespcount);
		count.setLabel("Product Count");
		horizontalBarModel2.addSeries(count);
		horizontalBarModel2.setTitle("");
		horizontalBarModel2.setLegendPosition("e");
		horizontalBarModel2.setBarWidth(20);
		horizontalBarModel2.setSeriesColors("EA4335");
		horizontalBarModel2.setResetAxesOnResize(false);
		horizontalBarModel2.setShadow(false);
		Axis xAxis = horizontalBarModel2.getAxis(AxisType.X);
		xAxis.setMin(0);
		xAxis.setMax(countpmax);
	}

	private PieChartModel createPieModel1() {
		logger.info("[createPieModel1()] --------------- inside createPieModel1() method ------------------------");
		pieModel1 = new PieChartModel();
		pieModel1.set("Sales", salescount);
		pieModel1.set("Purchase", purchasecount);
		pieModel1.set("Inventory In", stockincount);
		pieModel1.set("Inventory Out", stockoutcount);
		pieModel1.setSeriesColors("3369E8,D50F25,EEB211,009925");
		pieModel1.setLegendPosition("w");
		pieModel1.setDiameter(150);
		logger.debug("[createPieModel1()] --------------- piechart values ------------------------>"+salescount+"-->"+purchasecount+"-->"+stockincount+"-->"+stockoutcount);
		return pieModel1;
	}

	private PieChartModel createPieModel2() {
		logger.info("[createPieModel2()] --------------- inside createPieModel2() method ------------------------");
		pieModel2 = new PieChartModel();
		pieModel2.set("Sales", salespcount);
		pieModel2.set("Purchase", purchasepcount);
		pieModel2.set("Inventory In", stockinpcount);
		pieModel2.set("Inventory Out", stockoutpcount);
		pieModel2.setSeriesColors("3369E8,D50F25,EEB211,009925");
		pieModel2.setLegendPosition("w");
		pieModel2.setDiameter(150);
		return pieModel2;
	}

	public void setmax() {
		logger.info("[setmax()] --------------- inside setmax() method ------------------------");
		int numbers[] = new int[] { salescount, purchasecount, stockincount,
				stockoutcount };
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > countmax)
				countmax = numbers[i];
		}
		countmax += 25;
		setCountmax(roundToMultipleOf(countmax, 11, Direction.DOWN));
	}

	public void psetmax() {
		logger.info("[psetmax()] --------------- inside psetmax() method ------------------------");
		int numbers[] = new int[] { salespcount, purchasepcount, stockinpcount,
				stockoutpcount };
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > countpmax)
				countpmax = numbers[i];
		}
		countpmax += 25;
		setCountpmax(roundToMultipleOf(countpmax, 11, Direction.DOWN));
	}

	public void purchasemax() {
		logger.info("[purchasemax()] --------------- inside purchasemax() method ------------------------");
		int numbers[] = new int[] { purchasecountList.get(0).getJan(),
				purchasecountList.get(0).getFeb(),
				purchasecountList.get(0).getMar(),
				purchasecountList.get(0).getApril(),
				purchasecountList.get(0).getMay(),
				purchasecountList.get(0).getJune(),
				purchasecountList.get(0).getJuly(),
				purchasecountList.get(0).getAug(),
				purchasecountList.get(0).getSep(),
				purchasecountList.get(0).getOct(),
				purchasecountList.get(0).getNov(),
				purchasecountList.get(0).getDec() };
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > purchasemax)
				purchasemax = numbers[i];
		}
		purchasemax += 25;
		setPurchasemax(roundToMultipleOf(purchasemax, 100, Direction.UP));
	}

	public int roundToMultipleOf(int current, int multipleOf,
			Direction direction) {
		logger.debug("inside roundof");
		if (current % multipleOf == 0) {
			return ((current / multipleOf) + (direction == Direction.UP ? 1
					: -1)) * multipleOf;
		}
		return (direction == Direction.UP ? (int) Math.ceil((double) current
				/ multipleOf) : (direction == Direction.DOWN ? (int) Math
				.floor((double) current / multipleOf) : current)) * multipleOf;
	}

	public void tabchange(TabChangeEvent event) {
		logger.info("[tabchange()] --------------- inside tabchange() method ------------------------");
		setCountmax(0);
		setCountpmax(0);

		if (event.getTab().getTitle().equalsIgnoreCase("Dashboard")) {
			logger.debug("inside dashbord if condition");

			setmax();
			createHorizontalBarModel1();
			createPieModel1();

		} else if (event.getTab().getTitle()
				.equalsIgnoreCase("Product Dashboard")) {
			logger.debug("inside pro dashboard if condition");

			psetmax();
			createHorizontalBarModel2();
			createPieModel2();
		}
	}
}