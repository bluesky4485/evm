package org.evm.biz.datast.entity;

import org.evm.core.entity.AbstractEntity;

public class OrderPageStVO extends AbstractEntity {
	/**
	 * 今日未处理的订单
	 */
	private int todayNoDoOrderCnt;
	/**
	 * 7日未处理的订单
	 */
	private int weekNoDoOrderCnt;
	/**
	 * 本月未处理的订单
	 */
	private int monthNoDoOrderCnt;
	/**
	 * 所有订单
	 */
	private int allOrderCnt;

	public int getTodayNoDoOrderCnt() {
		return todayNoDoOrderCnt;
	}

	public void setTodayNoDoOrderCnt(int todayNoDoOrderCnt) {
		this.todayNoDoOrderCnt = todayNoDoOrderCnt;
	}

	public int getWeekNoDoOrderCnt() {
		return weekNoDoOrderCnt;
	}

	public void setWeekNoDoOrderCnt(int weekNoDoOrderCnt) {
		this.weekNoDoOrderCnt = weekNoDoOrderCnt;
	}

	public int getMonthNoDoOrderCnt() {
		return monthNoDoOrderCnt;
	}

	public void setMonthNoDoOrderCnt(int monthNoDoOrderCnt) {
		this.monthNoDoOrderCnt = monthNoDoOrderCnt;
	}

	public int getAllOrderCnt() {
		return allOrderCnt;
	}

	public void setAllOrderCnt(int allOrderCnt) {
		this.allOrderCnt = allOrderCnt;
	}

}
