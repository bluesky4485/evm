package org.evm.biz.order.entity;

/**
 * ordeStatus=4是完成的 
 * ordeStatus！=4 mStatus =1 维修中的 
 * ordeStatus！=4 mStatus =0安装中的
 * 
 * @author admin
 *
 */
public class IndexOrderStVO {
	private String ordeStatus;
	private String mStatus;
	private String orderCnt;

	public String getOrdeStatus() {
		return ordeStatus;
	}

	public void setOrdeStatus(String ordeStatus) {
		this.ordeStatus = ordeStatus;
	}

	public String getOrderCnt() {
		return orderCnt;
	}

	public void setOrderCnt(String orderCnt) {
		this.orderCnt = orderCnt;
	}

	public String getmStatus() {
		return mStatus;
	}

	public void setmStatus(String mStatus) {
		this.mStatus = mStatus;
	}

}
