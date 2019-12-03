package com.inacsys.managedBean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
//import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;
import com.inacsys.domain.VendorCount;

@ManagedBean(name = "chartView")
public class ChartView implements Serializable {
	private static Logger logger = Logger.getLogger(ChartView.class);
	private static final long serialVersionUID = 1L;
	private LineChartModel animatedModel1;
	private BarChartModel animatedModel2;
	DemoController controller = null;
	private BarChartModel barchart;
	private PieChartModel pieModel1;
	private LineChartModel lineModel1;

	@PostConstruct
	public void init() {
		logger.debug("Alex inside post constract :::::::::::::::");
		createAnimatedModels();
		createPieModel2();
		createLineModels_1();
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

	public BarChartModel getBarchart() {
		return barchart;
	}

	public void setBarchart(BarChartModel barchart) {
		this.barchart = barchart;
	}

	public LineChartModel getAnimatedModel1() {
		return animatedModel1;
	}

	public BarChartModel getAnimatedModel2() {
		return animatedModel2;
	}

	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	public void setLineModel1(LineChartModel lineModel1) {
		this.lineModel1 = lineModel1;
	}

	private LineChartModel initLinearModel_1() {
		LineChartModel model = new LineChartModel();
		LineChartSeries purchase = new LineChartSeries();
		LineChartSeries sales = new LineChartSeries();

		purchase.setLabel("purchase");
		purchase.set(2010, 100);
		purchase.set(2011, 200);
		purchase.set(2012, 300);
		purchase.set(2013, 400);
		purchase.set(2014, 500);

		sales.setLabel("sales");
		sales.set(1, 100);
		sales.set(2, 200);
		sales.set(3, 300);
		sales.set(4, 400);
		sales.set(5, 500);

		model.addSeries(purchase);
		model.addSeries(sales);

		return model;
	}

	private void createLineModels_1() {
		logger.info("[createLineModels_1()] --------------- Inside createLineModels_1() method() ------------------------");
		lineModel1 = initLinearModel_1();
		lineModel1.setTitle("purchase / sales  Chart");
		lineModel1.setLegendPosition("e");
		Axis yAxis = lineModel1.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(500);

	}

	private void createPieModel2() {
		logger.info("[createPieModel2()] --------------- Inside createPieModel2() method() ------------------------"); 
		pieModel1 = new PieChartModel();
		pieModel1.setSeriesColors("051C5F,FFCC33,A1B4D5,AA4644");
		pieModel1.set("sales", 100);
		pieModel1.set("purchase", 120);
		pieModel1.set("inventory out", 80);
		pieModel1.set("inventory in", 110);
		pieModel1.setTitle("over view");
		pieModel1.setLegendPosition("w");
	}

	private BarChartModel initBar() {
		logger.info("[initBar()] --------------- Inside initBar() method() ------------------------");
		BarChartModel model = new BarChartModel();
		model.setSeriesColors("051C5F,FFCC33");
		model.setAnimate(true);
		ChartSeries purchase = new ChartSeries();
		purchase.setLabel("Purchase");
		purchase.set("2011", 100);
		purchase.set("2012", 150);
		purchase.set("2013", 200);
		purchase.set("2014", 250);
		purchase.set("2016", 150);
		ChartSeries sales = new ChartSeries();
		sales.setLabel("Sales");
		sales.set("2011", 80);
		sales.set("2012", 100);
		sales.set("2013", 150);
		sales.set("2014", 100);
		sales.set("2015", 50);
		model.addSeries(purchase);
		model.addSeries(sales);
		return model;
	}

	private void createAnimatedModels() {
		logger.info("[createAnimatedModels()] --------------- Inside createAnimatedModels() method() ------------------------");
		animatedModel1 = initLinearModel();
		animatedModel1.setTitle("StockIn & StockOut");
		animatedModel1.setAnimate(true);
		animatedModel1.setLegendPosition("ne");
		animatedModel1.getAxes().put(AxisType.X, new CategoryAxis("Months"));
		Axis yAxis = animatedModel1.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(500);
		animatedModel2 = initBarModel();
		animatedModel2.setTitle("Sales & Purchase Price");
		animatedModel2.setAnimate(true);
		animatedModel2.setLegendPosition("ne");
		yAxis = animatedModel2.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(1000);
		barchart = initBar();
		barchart.setTitle("purchase / sales charts");
		barchart.setAnimate(true);
		barchart.setLegendPosition("ne");
		yAxis = barchart.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(300);

	}

	private BarChartModel initBarModel() {
		logger.info("[initBarModel()] --------------- Inside initBarModel() method() ------------------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			BarChartModel model = new BarChartModel();
			ChartSeries girls = new ChartSeries();
			girls.setLabel("Sale");
			ChartSeries boys = new ChartSeries();
			boys.setLabel("Purchase");
			Date d = Calendar.getInstance().getTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			SimpleDateFormat dfm = new SimpleDateFormat("MM");
			SimpleDateFormat dfd = new SimpleDateFormat("dd");
			SimpleDateFormat dffm = new SimpleDateFormat("MMM");
			int year = Integer.parseInt(df.format(d));
			int month = Integer.parseInt(dfm.format(d));
			SimpleDateFormat dateA = new SimpleDateFormat("dd/MM/yyyy");
			GregorianCalendar gc = new GregorianCalendar(year, month - 1,
					Calendar.DAY_OF_MONTH);
			java.util.Date monthEndDate = new java.util.Date(gc.getTime()
					.getTime());
			gc.set(Calendar.DAY_OF_MONTH,
					gc.getActualMinimum(Calendar.DAY_OF_MONTH));
			Date cdf = gc.getTime();
			gc.set(Calendar.DAY_OF_MONTH,
					gc.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date cde = gc.getTime();
			String cMonth = dffm.format(gc.getTime());
			float amount = controller.getSalesAmount(cdf, cde);
			float pAmount = controller.getpurchaseAmount(cdf, cde);
			int pm = 0;
			int pmy = 0;
			if (month == 1) {
				pm = 12 - 4 + 1;
				pmy = year - 1;
			} else {
				pm = month - 4;
				pmy = year;
			}
			List<Date> pdf = new ArrayList<Date>();
			List<Date> pde = new ArrayList<Date>();
			List<String> pMonth = new ArrayList<String>();
			for (int i = pm; i < pm + 4; i++) {
				GregorianCalendar gc1 = new GregorianCalendar(pmy, i - 1,
						Calendar.DAY_OF_MONTH);
				gc1.set(Calendar.DAY_OF_MONTH,
						gc1.getActualMinimum(Calendar.DAY_OF_MONTH));
				pdf.add(gc1.getTime());
				Date pdff = gc1.getTime();
				logger.debug("[initBarModel()] --------------- Inside initBarModel() method() date format pdf------------------------>"+dateA.format(pdff));
				Date pdee = null;
				gc1.set(Calendar.DAY_OF_MONTH,
						gc1.getActualMaximum(Calendar.DAY_OF_MONTH));
				if (i != 4 || i != 6 || i != 8 || i != 11) {
					pdee = gc1.getTime();
				} else if (i == 2) {
					int leap = pmy % 4;
					if (leap == 0) {
						pdee = dateA.parse("29/" + i + "/" + pmy);
					} else {
						pdee = dateA.parse("28/" + i + "/" + pmy);
					}
				} else {
					Calendar c1 = Calendar.getInstance();
					c1.add(Integer.parseInt(dfd.format(gc1.getTime())), -1);
					pdee = c1.getTime();
				}
				logger.debug("[initBarModel()] --------------- Inside initBarModel() method() date------------------------>"+dateA.format(pdee));
				float amount1 = controller.getSalesAmount(pdff, pdee);
				float pAmount1 = controller.getpurchaseAmount(pdff, pdee);
				String s = dffm.format(pdff);
				logger.debug("[initBarModel()] --------------- Inside initBarModel() method() pdf format------------------------>"+s);
				girls.set(s, amount1);
				boys.set(s, pAmount1);
			}
			girls.set(cMonth, amount);
			boys.set(cMonth, pAmount);
			model.addSeries(boys);
			model.addSeries(girls);
			return model;
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return null;
		} finally {
			controller =null;
		}
	}

	private LineChartModel initLinearModel() {
		logger.info("[initLinearModel()] --------------- Inside initLinearModel() method() ------------------------");
		try {
			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");
			LineChartModel model = new LineChartModel();
			LineChartSeries stockin = new LineChartSeries();
			stockin.setLabel("StockIn");
			LineChartSeries stockout = new LineChartSeries();
			stockout.setLabel("StockOut");
			Date d = Calendar.getInstance().getTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			SimpleDateFormat dfm = new SimpleDateFormat("MM");
			SimpleDateFormat dfd = new SimpleDateFormat("dd");
			SimpleDateFormat dffm = new SimpleDateFormat("MMM");
			int year = Integer.parseInt(df.format(d));
			int month = Integer.parseInt(dfm.format(d));
			SimpleDateFormat dateA = new SimpleDateFormat("dd/MM/yyyy");
			GregorianCalendar gc = new GregorianCalendar(year, month - 1,
					Calendar.DAY_OF_MONTH);
			java.util.Date monthEndDate = new java.util.Date(gc.getTime()
					.getTime());
			gc.set(Calendar.DAY_OF_MONTH,
					gc.getActualMinimum(Calendar.DAY_OF_MONTH));
			Date cdf = gc.getTime();
			gc.set(Calendar.DAY_OF_MONTH,
					gc.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date cde = gc.getTime();
			String cMonth = dffm.format(gc.getTime());
			int stockin1 = controller.getStockinQuantity(cdf, cde);
			int stockout1 = controller.getStockOutQuantity(cdf, cde);
			int pm = 0;
			int pmy = 0;
			if (month == 1) {
				pm = 12 - 4 + 1;
				pmy = year - 1;
			} else {
				pm = month - 4;
				pmy = year;
			}
			List<Date> pdf = new ArrayList<Date>();
			List<Date> pde = new ArrayList<Date>();
			List<String> pMonth = new ArrayList<String>();
			for (int i = pm; i < pm + 4; i++) {
				GregorianCalendar gc1 = new GregorianCalendar(pmy, i - 1,
						Calendar.DAY_OF_MONTH);
				gc1.set(Calendar.DAY_OF_MONTH,
						gc1.getActualMinimum(Calendar.DAY_OF_MONTH));
				pdf.add(gc1.getTime());
				Date pdff = gc1.getTime();
				Date pdee = null;
				gc1.set(Calendar.DAY_OF_MONTH,
						gc1.getActualMaximum(Calendar.DAY_OF_MONTH));
				if (i != 4 || i != 6 || i != 8 || i != 11) {
					logger.debug("1>>>>");
					pdee = gc1.getTime();
				} else if (i == 2) {
					int leap = pmy % 4;
					if (leap == 0) {
						pdee = dateA.parse("29/" + i + "/" + pmy);
					} else {
						pdee = dateA.parse("28/" + i + "/" + pmy);
					}
				} else {
					Calendar c1 = Calendar.getInstance();
					c1.add(Integer.parseInt(dfd.format(gc1.getTime())), -1);
					pdee = c1.getTime();
				}
				int stockin2 = controller.getStockinQuantity(pdff, pdee);
				int stockout2 = controller.getStockOutQuantity(pdff, pdee);
				String s = dffm.format(pdff);
				stockin.set(s, stockin2);
				stockout.set(s, stockout2);
			}
			stockin.set(cMonth, stockin1);
			stockout.set(cMonth, stockout1);
			model.addSeries(stockin);
			model.addSeries(stockout);
			return model;
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return null;
		} finally {

		}
	}

}