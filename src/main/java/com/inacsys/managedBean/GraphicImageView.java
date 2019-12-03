package com.inacsys.managedBean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.inacsys.controler.DemoController;

@ManagedBean
public class GraphicImageView implements Serializable {
	private static Logger logger = Logger.getLogger(GraphicImageView.class);
	private static final long serialVersionUID = 1L;

	// sivaranjini 13_1_15
	private PieChartModel pieModel1;

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

	//

	// Sivaranjini 12/1/15
	DemoController controller = null;

	//

	@PostConstruct
	public void init() {
		logger.info("[init()] --------------- Inside init() method() ------------------------");
		try {

			pieModel1 = new PieChartModel();
			pieModel1.setTitle("Sales Ratio");
			pieModel1.setShowDataLabels(true);
			pieModel1.setLegendPosition("w");
			pieModel1.setData(createpieChart());

		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}
	}

	// sivaranjini_13_1_15
	private Map<String, Number> createpieChart() {
		  try {
		   PieChartModel pi = new PieChartModel();
		   ApplicationContext ctx = FacesContextUtils
		     .getWebApplicationContext(FacesContext.getCurrentInstance());
		   controller = (DemoController) ctx.getBean("controller");
		   Map<String, Number> agents = new LinkedHashMap<String, Number>();
		   Date d = Calendar.getInstance().getTime();
		   SimpleDateFormat df = new SimpleDateFormat("yyyy");
		   SimpleDateFormat dfm = new SimpleDateFormat("MM");
		   SimpleDateFormat dfd = new SimpleDateFormat("dd");
		   SimpleDateFormat dffm = new SimpleDateFormat("MMMM");
		   int year = Integer.parseInt(df.format(d));
		   logger.debug(">>>>>>>>>>>>>>>>>>>>>>" + year);
		   int month = Integer.parseInt(dfm.format(d));
		   logger.debug(">>>>>>>>>>>>>>>>>" + month);
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
		   int val = controller.getsalesQuantityOfMonth(cdf, cde);
		   agents.put(cMonth, val);
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
		    logger.debug("S>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
		      + dateA.format(pdff));
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
		     logger.debug("2>>>>");
		     Calendar c1 = Calendar.getInstance();
		     c1.add(Integer.parseInt(dfd.format(gc1.getTime())), -1);
		     pdee = c1.getTime();
		    }
		    logger.debug("E>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
		      + dateA.format(pdee));
		    int val1 = controller.getsalesQuantityOfMonth(pdff, pdee);
		    String s = dffm.format(pdff);
		    logger.debug("M>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + s);
		    agents.put(s, val1);
		   }
		   return agents;
		  } catch (Exception e) {
		   logger.error("Inside Exception", e);
		   return null;
		  } finally {

		  }
		 }
}