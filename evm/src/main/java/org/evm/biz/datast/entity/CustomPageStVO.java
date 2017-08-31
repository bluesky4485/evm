package org.evm.biz.datast.entity;

import org.evm.core.entity.AbstractEntity;

public class CustomPageStVO extends AbstractEntity {
	/**
	 * 今日停止服务的客户 数
	 */
	private int todayStopServiceCnt;
	/**
	 * 3日停止服务的客户 数
	 */
	private int threeDayStopServiceCnt;
	/**
	 * 15日停止服务的客户 数
	 */
	private int fifthDayStopServiceCnt;
	/**
	 * 本月停止服务的客户 数
	 */
	private int monthStopServiceCnt;
	/**
	 * 全部客户
	 */
	private int allCustomerCnt;
	/**
	 * 查询开始日期
	 */
	private String startDay;
	/**
	 * 查询结束时间
	 */
	private String endDay;

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public int getTodayStopServiceCnt() {
		return todayStopServiceCnt;
	}

	public void setTodayStopServiceCnt(int todayStopServiceCnt) {
		this.todayStopServiceCnt = todayStopServiceCnt;
	}

	public int getThreeDayStopServiceCnt() {
		return threeDayStopServiceCnt;
	}

	public void setThreeDayStopServiceCnt(int threeDayStopServiceCnt) {
		this.threeDayStopServiceCnt = threeDayStopServiceCnt;
	}

	public int getFifthDayStopServiceCnt() {
		return fifthDayStopServiceCnt;
	}

	public void setFifthDayStopServiceCnt(int fifthDayStopServiceCnt) {
		this.fifthDayStopServiceCnt = fifthDayStopServiceCnt;
	}

	public int getMonthStopServiceCnt() {
		return monthStopServiceCnt;
	}

	public void setMonthStopServiceCnt(int monthStopServiceCnt) {
		this.monthStopServiceCnt = monthStopServiceCnt;
	}

	public int getAllCustomerCnt() {
		return allCustomerCnt;
	}

	public void setAllCustomerCnt(int allCustomerCnt) {
		this.allCustomerCnt = allCustomerCnt;
	}

}
