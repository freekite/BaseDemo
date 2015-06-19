package com.demo.basedemo.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	public static final String FORMAT_TIME = "HH:mm:ss";
	public static final String FORMAT_TIME_LESS_SS = "HH:mm";
	public static final String FORMAT_FULL_ZH = "yyyy年MM月dd日 HH:mm:ss";
	public static final String FORMAT_DATE_ZH = "yyyy年MM月dd日";
	
	public static final int SECOND=1000;
	public static final int MINUTE=SECOND*60;
	public static final int HOUR=MINUTE*60;
	public static final int DAY=HOUR*24;
	

	public static String format(Date date, String formatStr) {
		if (date != null) {
			DateFormat format = new SimpleDateFormat(formatStr);
			return format.format(date);
		}
		return null;
	}

	public static Date strToDate(String date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = df.parse(date);
		} catch (Exception e) {
		}
		return d;
	}

	public static int compare(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(date1);
			c2.setTime(date2);
		} catch (Exception e) {
		}
		int i = c1.compareTo(c2);
		return i;
	}

    public static Date getToday(){
        return new Date(System.currentTimeMillis());
    }
	/**
	 * 根据出生日期计算年龄 过去的日期返回0
	 * 
	 * @param birthDay
	 * @return
	 */
	public static int getAge(Date birthDay) {
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) {
			return 0;
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;
			} else {
				age--;
			}
		}
		return age;
	}
	
	/**
	 * 
	 * @param dateStr
	 * @param n
	 * @return
	 */
	public static Date addDay(Date date, int n) {
		try {
			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			cd.add(Calendar.DATE, n);// 增加一天
			// cd.add(Calendar.MONTH, n);//增加一个月
			return new Date(cd.getTimeInMillis());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String timeToString(long time){
		if(time>=DAY){
			return (time/DAY)+"天"+(time%DAY/HOUR)+"小时"+(time%DAY%HOUR/MINUTE)+"分钟";
		}else if(time>=HOUR&&time<DAY){
			return (time%DAY/HOUR)+"小时"+(time%DAY%HOUR/MINUTE)+"分钟";
		}else {
			return (time%DAY%HOUR/MINUTE)+"分钟";
		}
	}
}
