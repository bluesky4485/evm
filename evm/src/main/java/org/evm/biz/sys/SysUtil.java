package org.evm.biz.sys;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SysUtil {
	/**
	 * 取得当前月的最大天数
	 * 
	 * @return
	 */
	public static int getCurrentMonthLastDay() {
		// Calendar a = Calendar.getInstance();
		// a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		// a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		// int maxDate = a.get(Calendar.DATE);
		Date date = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int currentDate = ca.get(Calendar.DAY_OF_MONTH);
		return currentDate;
	}

	public static String getFirstDayOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = format.format(c.getTime());
		System.out.println("===============first:" + first);
		return first;
	}

	public static String getLastDayOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(ca.getTime());
		System.out.println("===============last:" + last);
		return last;
	}

	public static void main(String[] args) {
		// System.out.println(getCurrentMonthLastDay());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(ca.getTime());
		System.out.println("===============last:" + last);
	}
}
