package org.evm.biz.order.entity;

import org.evm.core.entity.AbstractEntity;

public class OrderDeviceVO extends AbstractEntity {
	/**
	 * 订单ID
	 */
	private long orderId;
	/**
	 * 设备类型ID
	 */
	private long deviceId;
	/**
	 *大分类
	 */
	private String deviceClassType;
	/**
	 * 设备类型描述
	 */
	private String deviceName;
	/**
	 * 可领（可领数量）
	 */
	private int planCnt;
	/**
	 * 已领（已领数量）总数
	 */
	private int deviceCnt;
	/**
	 * 库存（已安装数量）
	 */
	private int storeCnt;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceClassType() {
		return deviceClassType;
	}

	public void setDeviceClassType(String deviceClassType) {
		this.deviceClassType = deviceClassType;
	}

	public int getPlanCnt() {
		return planCnt;
	}

	public void setPlanCnt(int planCnt) {
		this.planCnt = planCnt;
	}

	public int getDeviceCnt() {
		return deviceCnt;
	}

	public void setDeviceCnt(int deviceCnt) {
		this.deviceCnt = deviceCnt;
	}

	public int getStoreCnt() {
		return storeCnt;
	}

	public void setStoreCnt(int storeCnt) {
		this.storeCnt = storeCnt;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		StringBuffer sb = new StringBuffer();
//		sb.append("orderId=" + this.orderId).append(";");
//		sb.append("deviceId=" + this.deviceId).append(";");
//		sb.append("deviceClassType=" + this.deviceClassType).append(";");
//		sb.append("deviceName=" + this.deviceName).append(";");
//		sb.append("planCnt=" + this.planCnt).append(";");
//		
//		sb.append("deviceCnt=" + this.deviceCnt).append(";");
//		sb.append("storeCnt=" + this.storeCnt).append(";");
//		
//		return sb.toString();
//	}
}
