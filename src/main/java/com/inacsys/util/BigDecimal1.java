package com.inacsys.util;

import java.awt.Component;

import java.math.BigDecimal;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigDecimal1 {

	static final Logger logger = LoggerFactory.getLogger(BigDecimal1.class);

	public static void main(String args[]) {

		/*
		 * Component frame = null; JOptionPane.showMessageDialog(frame,
		 * "Eggs are not supposed to be green.");
		 */

		String a = "1006767676767", b = "207676767655656";
		String temp = "";
		float i = 1, j = 20;
		try {
			temp = ""
					+ (BigDecimal.valueOf(j).add(BigDecimal.valueOf(i))
							.add(BigDecimal.valueOf(i)));
			logger.debug("value------------>" + temp);
			temp = "" + (new BigDecimal(a).add(new BigDecimal(b)));
			logger.debug("value-------------->" + temp);
			temp = "" + (BigDecimal.valueOf(j).subtract(BigDecimal.valueOf(i)));
			logger.debug("value sub------------>" + temp);
			temp = ""
					+ (BigDecimal.valueOf(j).multiply(new BigDecimal(b))
							.setScale(0, BigDecimal.ROUND_HALF_UP));
			logger.debug("value mul-------------->" + temp);
			a = "" + temp;
			temp = "" + (new BigDecimal(a).divide(new BigDecimal(b)));
			logger.debug("value------------>" + temp);

			logger.debug("reminder---------->"
					+ new BigDecimal(b).remainder(BigDecimal.valueOf(100)));
			int ii = new BigDecimal("20").compareTo(BigDecimal.valueOf(0));
			logger.debug("--------->" + ii);
			int iii = new BigDecimal("-5").compareTo(BigDecimal.valueOf(0));
			logger.debug("--------->" + iii);
			logger.debug("---------------------->"
					+ new BigDecimal("0").compareTo(BigDecimal.valueOf(0)));
			logger.debug("g------------------->"
					+ new BigDecimal("5").compareTo(new BigDecimal(4)));
			logger.debug("le------------------->"
					+ new BigDecimal("4").compareTo(new BigDecimal("5")));
			logger.debug("le------------------->"
					+ new BigDecimal("4").compareTo(new BigDecimal("4")));
		}

		catch (Exception e) {

			logger.error("Inside Exception", e);

		} finally {

		}

	}

}