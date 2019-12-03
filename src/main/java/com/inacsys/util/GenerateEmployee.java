package com.inacsys.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateEmployee {
	static final Logger logger = LoggerFactory
			.getLogger(GenerateEmployee.class);
	public static final int interval = 1000;
	public static final String suffixID = "@nrg.com";
	public static NumberFormat numberFormat = NumberFormat
			.getNumberInstance(Locale.US);

	public static String getEmployeeReference(int count) {
		int randomNo = count;
		String refId = "QSON" + randomNo;
		return refId;
	}

	public static String getSalesOrderNo(int count) {
		/*
		 * int randomNo=count; String refId="SON"+randomNo;
		 */
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		logger.debug("Current Year is : " + year);
		String month = new SimpleDateFormat("MMM").format(now.getTime());
		logger.debug("Month" + month);
		String s1 = String.format("%05d", count);

		String refId = "SO" + s1 + month + year;

		return refId;
	}

	public static String getdispathchno(int count) {
		int randomNo = count;
		String refId = null;
		if (randomNo <= 9) {
			refId = "SJ000" + randomNo;
		} else if (randomNo > 9 && randomNo <= 99) {
			refId = "SJ00" + randomNo;
		} else if (randomNo == 0) {
			refId = "SJ000" + 1;
		} else if (randomNo > 99 && randomNo <= 999) {
			refId = "SJ0" + randomNo;
		} else {
			refId = "SJ" + randomNo;
		}
		return refId;
	}

}
