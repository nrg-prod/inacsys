package com.inacsys.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.inacsys.domain.AccountsDatabean;

public class Dates {

	private static Date fdate;
	private static Date tdate;
	
	public static Date getFdate() {
		return fdate;
	}

	public static void setFdate(Date fdate) {
		Dates.fdate = fdate;
	}

	public static Date getTdate() {
		return tdate;
	}

	public static void setTdate(Date tdate) {
		Dates.tdate = tdate;
	}
	
	public static void getDates(String reportPeriod, AccountsDatabean accountsDatabean){
		Calendar cal=GregorianCalendar.getInstance();
		DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
		Date date=new Date();
		String startDate = "", endDate = "";
		try{
			if(reportPeriod.equals("This Week")){
				cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
				startDate = df.format(cal.getTime());
		        cal.add(Calendar.DATE, 6);
		        endDate = df.format(cal.getTime());		        
			}else if(reportPeriod.equals("This Week-to-date")){
				cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
				startDate = df.format(cal.getTime());
		        endDate = df.format(date);		        
			}else if(reportPeriod.equals("This month")){
				getThisMonthDate();
				startDate = df.format(fdate);
		        endDate = df.format(tdate);		        
			}else if(reportPeriod.equals("This month-to-date")){
				getThisMonthDate();
				startDate = df.format(fdate);
		        endDate = df.format(date);	        
			}else if(reportPeriod.equals("This Quarter")){
				getDayOfQuarter(date);
				startDate = df.format(fdate);
				endDate = df.format(tdate);	    
			}else if(reportPeriod.equals("This Quarter-to-date")){
				getDayOfQuarter(date);
				startDate = df.format(fdate);
				endDate = df.format(date);	    
			}else if(reportPeriod.equals("This Year")){
				getDayOfYear();
			    startDate = df.format(fdate);
			    endDate = df.format(tdate);	    
			}else if(reportPeriod.equals("This Year-to-date")){
				getDayOfYear();
			    startDate = df.format(fdate);
			    endDate = df.format(date);	    
			}else if(reportPeriod.equals("Yesterday")){
				cal.add(Calendar.DATE, -1);
			    startDate = df.format(cal.getTime());
			    endDate = df.format(cal.getTime());	    
			}else if(reportPeriod.equals("Last Week")){
				cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
				cal.add(Calendar.DATE, -7);
				startDate = df.format(cal.getTime());
		        cal.add(Calendar.DATE, 6);
		        endDate = df.format(cal.getTime());	  
			}else if(reportPeriod.equals("Last Week-to-date")){
				cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
				cal.add(Calendar.DATE, -7);
				startDate = df.format(cal.getTime());
		        endDate = df.format(date);	  
			}else if(reportPeriod.equals("Last Month")){
				getlastMonthDate();
				startDate = df.format(fdate);
		        endDate = df.format(tdate);		  
			}else if(reportPeriod.equals("Last Month-to-date")){
				getlastMonthDate();
				startDate = df.format(fdate);
		        endDate = df.format(date);		  
			}else if(reportPeriod.equals("Last Quarter")){
				getLastQuarter(date);
				startDate = df.format(fdate);
				endDate = df.format(tdate);	  
			}else if(reportPeriod.equals("Last Quarter-to-date")){
				getLastQuarter(date);
				startDate = df.format(fdate);
				endDate = df.format(date);	  
			}else if(reportPeriod.equals("Last Year")){
				int year=cal.get(Calendar.YEAR)-1;
				fdate=df.parse("01-01-"+String.valueOf(year));
				tdate=df.parse("31-12-"+String.valueOf(year));
				startDate = df.format(fdate);
				endDate = df.format(tdate);	  
			}else if(reportPeriod.equals("Last Year-to-date")){
				int year=cal.get(Calendar.YEAR)-1;
				fdate=df.parse("01-01-"+String.valueOf(year));
				startDate = df.format(fdate);
				endDate = df.format(date);	  
			}
			accountsDatabean.setFromDate(df.parse(startDate));
	        accountsDatabean.setToDate(df.parse(endDate));
			System.out.println("f date "+startDate);
	        System.out.println("t date "+endDate);	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	private static void getThisMonthDate(){
	    Calendar calendar = new GregorianCalendar();
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    fdate=calendar.getTime();
	    calendar.add(Calendar.MONTH, 1);
	    calendar.add(Calendar.DATE, -1);
	    tdate=calendar.getTime();
	}
	
	private static void getDayOfQuarter(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    fdate=cal.getTime();
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3 + 2);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	    tdate=cal.getTime();
	}
	
	private static void getDayOfYear() {
		Calendar cal = Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
	    cal.set(Calendar.DAY_OF_YEAR, 1);
	    fdate =cal.getTime();
	    if(year % 4 ==0)cal.set(Calendar.DAY_OF_YEAR, 366);
		else cal.set(Calendar.DAY_OF_YEAR, 365);
		tdate = cal.getTime();	 
	}
	
	private static void getlastMonthDate(){
	    Calendar calendar = new GregorianCalendar();
	    calendar.add(Calendar.MONTH, -1);
	    calendar.set(Calendar.DATE, 1);
	    fdate=calendar.getTime();
	    calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	    tdate=calendar.getTime();
	}
	
	private static void getLastQuarter(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3);
	    if(Calendar.MONTH>0){
	    	cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)-3);
	    }
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    fdate=cal.getTime();
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3 + 2);
	    if(Calendar.MONTH>2){
	    	cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)-3);
	    }
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	    tdate=cal.getTime();
	}
		
	public static void main(String[] args){
		System.out.println("inside");
		Date date=new Date();
		/*Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		System.out.println("day "+cal.get(Calendar.YEAR));
		DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
		String startDate = "", endDate = "";
        startDate = df.format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        endDate = df.format(cal.getTime());
        System.out.println("f date "+startDate);
        System.out.println("t date "+endDate);
        getMonth(cal);  
        int year=cal.get(Calendar.YEAR)-1;
		 System.out.println("year "+year);*/
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    System.out.println("time "+cal.getTime()+" - - "+cal.get(Calendar.MONTH)+"----->"+cal.get(Calendar.MONTH)/3 * 3);
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3);
	    if(Calendar.MONTH>0){
	    	cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)-3);
	    }
	    System.out.println("date "+" --"+ cal.get(Calendar.MONTH)/3 * 3+" -- "+Calendar.MONTH);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    fdate=cal.getTime();
	    System.out.println("date "+fdate);
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3 + 2);
	    /*if(Calendar.MONTH>2){
	    	cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)-3);
	    }*/
	    System.out.println("to  "+(cal.get(Calendar.MONTH)/3 * 3 + 2)+" -- "+Calendar.MONTH);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	    tdate=cal.getTime();
	    System.out.println("to date "+tdate);
	}
	
	public static void getMonth(Calendar cal){
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		System.out.println("month "+month+" year "+year);
		String from="";
		String to="";
		if(month==1){
			from="01-01-"+String.valueOf(year);
			to="31-01-"+String.valueOf(year);
		}
		else if(month==2){
			from="01-02-"+String.valueOf(year);
			if(year % 4 ==0) to="29-01-"+String.valueOf(year);
			else to="28-01-"+String.valueOf(year);
		}
		else if(month==3){
			from="01-03-"+String.valueOf(year);
			to="31-03-"+String.valueOf(year);
		}
		else if(month==4){
			from="01-04-"+String.valueOf(year);
			to="30-04-"+String.valueOf(year);
		}
		else if(month==5){
			from="01-05-"+String.valueOf(year);
			to="31-05-"+String.valueOf(year);
		}
		else if(month==6){
			from="01-06-"+String.valueOf(year);
			to="30-06-"+String.valueOf(year);
		}
		else if(month==7){
			from="01-07-"+String.valueOf(year);
			to="31-07-"+String.valueOf(year);
		}
		else if(month==8){
			from="01-08-"+String.valueOf(year);
			to="31-08-"+String.valueOf(year);
		}
		else if(month==9){
			from="01-09-"+String.valueOf(year);
			to="30-09-"+String.valueOf(year);
		}
		else if(month==10){
			from="01-10-"+String.valueOf(year);
			to="31-10-"+String.valueOf(year);
		}
		else if(month==11){
			from="01-11-"+String.valueOf(year);
			to="30-11-"+String.valueOf(year);
		}
		else if(month==12){
			from="01-12-"+String.valueOf(year);
			to="31-12-"+String.valueOf(year);
		}
		System.out.println("from "+from+" to "+to);
	}
	
}
