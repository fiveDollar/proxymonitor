package com.windoor.date;

import java.util.Calendar;

public class MyDateUtil {
	public static String getData(int hour){
		int y, m, d, h, mi, s;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, hour);
		y = cal.get(Calendar.YEAR);
		m = cal.get(Calendar.MONTH)+1 ;
		d = cal.get(Calendar.DATE);
		h = cal.get(Calendar.HOUR_OF_DAY);
		mi = cal.get(Calendar.MINUTE);
		s = cal.get(Calendar.SECOND);
		String date = y + "-" + m + "-" + d + " " + h;
		return date;
	}
}
