package com.inacsys.managedBean;

import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;

@ManagedBean
public class Chartbubble implements Serializable {

	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(Chartbubble.class);
	private static final long serialVersionUID = 1L;
	private BubbleChartModel bubbleModel1;

	// sivaranjini 14_1_15
	public String tit;

	public String getTit() {
		return tit;
	}

	public void setTit(String tit) {
		this.tit = tit;
	}

	//
	@PostConstruct
	public void init() {
		createBubbleModels();
	}

	public BubbleChartModel getBubbleModel1() {
		return bubbleModel1;
	}

	private void createBubbleModels() {
		logger.info("[createBubbleModels()] --------------- Inside createBubbleModels() method() ------------------------");
		bubbleModel1 = initBubbleModel();
		bubbleModel1.setTitle("Sales & Purchases Invoices");
		bubbleModel1.getAxis(AxisType.X).setLabel(tit);
		Axis yAxis = bubbleModel1.getAxis(AxisType.Y);
		Axis xAxis = bubbleModel1.getAxis(AxisType.X);
		yAxis.setMin(0);
		yAxis.setMax(50);

		xAxis.setMin(0);
		xAxis.setMax(4);
	}

	private BubbleChartModel initBubbleModel() {
		logger.info("[initBubbleModel()] --------------- Inside initBubbleModel() method() ------------------------");
		DemoController controller = null;
		try {
			BubbleChartModel model = new BubbleChartModel();

			ApplicationContext ctx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			controller = (DemoController) ctx.getBean("controller");

			Date d = Calendar.getInstance().getTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			SimpleDateFormat dfm = new SimpleDateFormat("MM");
			SimpleDateFormat dfd = new SimpleDateFormat("dd");
			SimpleDateFormat dffm = new SimpleDateFormat("MMM");
			SimpleDateFormat dffmo = new SimpleDateFormat("MMMM");
			int year = Integer.parseInt(df.format(d));
			logger.debug("[initBubbleModel()] --------------- Inside initBubbleModel() method() year------------------------>"+year);
			int month = Integer.parseInt(dfm.format(d));
			logger.debug("[initBubbleModel()] --------------- Inside initBubbleModel() method() month------------------------>"+month);
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
			String cMonth = dffmo.format(gc.getTime());
			tit = cMonth;
			int salesin = controller.getsalesInvoice(cdf, cde);
			int purchasein = controller.getpurchaseInvoice(cdf, cde);
			model.add(new BubbleChartSeries("Sales", 1, salesin, 50));
			model.add(new BubbleChartSeries("Purchase", 3, purchasein, 100));

			return model;
		} catch (Exception e) {
			logger.error("Inside Exception", e);
			return null;
		} finally {

		}

	}

}
