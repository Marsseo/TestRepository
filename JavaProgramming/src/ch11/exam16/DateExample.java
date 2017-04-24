package ch11.exam16;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateExample {

	public static void main(String[] args) {
		Date now = new Date();
		
	

		System.out.println(now);
		TimeZone timezone = TimeZone.getTimeZone("Europe/Beriln");
		Calendar cal = Calendar.getInstance(timezone);
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		
		
		System.out.println(year+"-"+month+"-"+date+"   "+hour+":"+minute+":"+second);
		
	}

}
