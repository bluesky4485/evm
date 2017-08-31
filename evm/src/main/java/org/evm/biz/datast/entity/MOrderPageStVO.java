package org.evm.biz.datast.entity;

import org.evm.core.entity.AbstractEntity;

public class MOrderPageStVO extends AbstractEntity {
	/**
	 * 未处理的运维订单      维修状态
	 */
	private int allNoDoMorderCnt;
	/**
	 * 未回访的运维订单 维修状态
	 */
	private int noCallBackMorderCnt;
	/**
	 * 运维中的运维订单  维修状态=2
	 */
	private int doingMorderCnt;
	/**
	 * 存在问题的运维订单??
	 */
	private int problemMorderCnt;

	public int getAllNoDoMorderCnt() {
		return allNoDoMorderCnt;
	}

	public void setAllNoDoMorderCnt(int allNoDoMorderCnt) {
		this.allNoDoMorderCnt = allNoDoMorderCnt;
	}

	public int getNoCallBackMorderCnt() {
		return noCallBackMorderCnt;
	}

	public void setNoCallBackMorderCnt(int noCallBackMorderCnt) {
		this.noCallBackMorderCnt = noCallBackMorderCnt;
	}

	public int getDoingMorderCnt() {
		return doingMorderCnt;
	}

	public void setDoingMorderCnt(int doingMorderCnt) {
		this.doingMorderCnt = doingMorderCnt;
	}

	public int getProblemMorderCnt() {
		return problemMorderCnt;
	}

	public void setProblemMorderCnt(int problemMorderCnt) {
		this.problemMorderCnt = problemMorderCnt;
	}

}
