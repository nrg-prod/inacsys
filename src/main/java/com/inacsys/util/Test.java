package com.inacsys.util;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inacsys.domain.AccountsDatabean;

public class Test {

	static final Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String args[]) throws ParseException, SQLException {
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.DATE, 0);
		logger.debug("------date-----" + sdf.format(c1.getTime()));
		int cals = c.get(Calendar.DAY_OF_WEEK);
		c.add(Calendar.DATE, -9);
		logger.debug("=yesterday==" + sdf.format(c.getTime()));
		logger.debug("dayyy~~~~" + c.get(Calendar.DAY_OF_WEEK));

		logger.debug("===" + c + "~~~~~~" + c1 + "\n~~~cals" + cals);
		if (cals == 1) {
			logger.debug("---1---");
		}
		if (cals == 2) {
			logger.debug("---2---");
		}
		if (cals == 3) {
			logger.debug("---3---");
		}
		if (cals == 4) {
			logger.debug("---4---");
		}
		if (cals == 5) {
			logger.debug("---5---");
		}*/
	/*String number="00000099";
	int number1=Integer.parseInt(number)+1;
	System.out.println("number"+number1);
	int n=3;int l=2;
	for (int i = 0; i < n; i++) {
		System.out.println("inside 1 loop---"+i);
		for (int j = 0; j < l; j++) {
			System.out.println("inside 2 loop---"+j);
		}
	}*/
	
	/*List<String> testList=new ArrayList<String>();
	testList.add("1");
	testList.add("3");
	testList.add("2");
	System.out.println("list"+testList);
	}*/
	
		/*Date date=new Date();
	     System.out.println("date"+date);
	     SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
	     String date1=sf.format(date);
	     System.out.println("date"+date1);
	     
	     Date today;
	     String strDate;
	     DateFormat dateFormat;
	     dateFormat = DateFormat
	         .getDateInstance(DateFormat.LONG);
	     today = new Date();
	     strDate = dateFormat.format(today);
	     System.out.println(strDate);
	     List<String> list3=new ArrayList<String>();
	     List<String> list2=Arrays.asList("a,b,c");
	     List<String> list1=Arrays.asList("d,e,f");
	     list3.addAll(list1);list3.addAll(list2);
	     System.out.println("list 3"+list3);
	     Map<Integer, List<String>> map=new HashMap<Integer, List<String>>();
	     map.put(1, list1);
	     map.put(2, list2);
	     for (Map.Entry m : map.entrySet()) {
			System.out.println("mmmm"+m.getValue());
		 }
		
		String a="aaaa";
		String a1=new String("aaaa");
System.out.println("1---"+a);
System.out.println("333---"+a1);*/

		Double d=20.4333;
				DecimalFormat df = new DecimalFormat(".##");
				System.out.println("value"+df.format(d));

	}	
	
}



