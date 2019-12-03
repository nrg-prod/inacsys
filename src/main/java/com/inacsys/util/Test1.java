package com.inacsys.util;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test1 {
	static final Logger logger = LoggerFactory.getLogger(Test1.class);

	public static void main(String args[]) {

		/*
		 * Component frame = null; JOptionPane.showMessageDialog(frame,
		 * "Eggs are not supposed to be green.");
		 */

		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date d = df.parse(df.format(df.parse("17/04/2015")));
			Calendar now = new GregorianCalendar();
			now.setTime(d);
			now.add(Calendar.WEEK_OF_YEAR, -1);
			String s = (now.get(Calendar.DATE)) + "/"
					+ (now.get(Calendar.MONTH) + 1) + "/"
					+ now.get(Calendar.YEAR);
			logger.debug("Date---------------------------->" + s);

			Date dd = df.parse(df.format(df.parse("21/04/2015")));
			Calendar now1 = new GregorianCalendar();
			now1.setTime(dd);
			now1.add(Calendar.MONTH, -1);
			String ss = now1.get(Calendar.DATE) + "/"
					+ (now1.get(Calendar.MONTH) + 1) + "/"
					+ now1.get(Calendar.YEAR);
			logger.debug("Date---------------------------->" + ss);
		} catch (Exception e) {
			logger.error("Inside Exception", e);
		}

	}

}