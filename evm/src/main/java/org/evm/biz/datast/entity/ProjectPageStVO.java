package org.evm.biz.datast.entity;

import org.evm.core.entity.AbstractEntity;

public class ProjectPageStVO extends AbstractEntity {
	/**
	 * 今日工期截止的项目
	 */
	private int todayStopWorkCnt;
	/**
	 * 最近一个月签订的项目
	 */
	private int mothSingProjectCnt;
	/**
	 * 最近一周工期截止的项目
	 */
	private int weekStopWorkCnt;
	/**
	 * 所有未处理完的订单
	 */
	private int allNonDealOrderCnt;

	public int getTodayStopWorkCnt() {
		return todayStopWorkCnt;
	}

	public void setTodayStopWorkCnt(int todayStopWorkCnt) {
		this.todayStopWorkCnt = todayStopWorkCnt;
	}

	public int getMothSingProjectCnt() {
		return mothSingProjectCnt;
	}

	public void setMothSingProjectCnt(int mothSingProjectCnt) {
		this.mothSingProjectCnt = mothSingProjectCnt;
	}

	public int getWeekStopWorkCnt() {
		return weekStopWorkCnt;
	}

	public void setWeekStopWorkCnt(int weekStopWorkCnt) {
		this.weekStopWorkCnt = weekStopWorkCnt;
	}

	public int getAllNonDealOrderCnt() {
		return allNonDealOrderCnt;
	}

	public void setAllNonDealOrderCnt(int allNonDealOrderCnt) {
		this.allNonDealOrderCnt = allNonDealOrderCnt;
	}

}
