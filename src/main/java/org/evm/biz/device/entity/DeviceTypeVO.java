package org.evm.biz.device.entity;

import java.util.List;

import org.evm.core.entity.AbstractEntity;

public class DeviceTypeVO extends AbstractEntity {
	/**
	 * id
	 */
	private long deviceId;
	/**
	 * 名称
	 */
	private String deviceName;
	/**
	 * 大分类
	 */
	private String classType;
	/**
	 * 大分类描述
	 */
	private String classTypeDesc;
	/**
	 * 属性
	 */
	private String allPropertys;
	/**
	 * 属性对象
	 */
	private List<DeviceTypePropertyVO> properties;
	/**
	 * 项目ID
	 */
	private String projectId;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 订单ID
	 */
	private String orderId;
	/**
	 * 订单编号
	 */
	private String orderNo;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getClassTypeDesc() {
		return classTypeDesc;
	}

	public void setClassTypeDesc(String classTypeDesc) {
		this.classTypeDesc = classTypeDesc;
	}

	public String getAllPropertys() {
		return allPropertys;
	}

	public void setAllPropertys(String allPropertys) {
		this.allPropertys = allPropertys;
	}

	public List<DeviceTypePropertyVO> getProperties() {
		return properties;
	}

	public void setProperties(List<DeviceTypePropertyVO> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("deviceId=" + this.deviceId).append(";");
		sb.append("deviceName=" + this.deviceName).append(";");
		sb.append("classType=" + this.classType).append(";");
		sb.append("updUser=" + super.updUser).append(";");
		sb.append("updDate=" + super.updDate).append(";");
		sb.append("insUser=" + super.insUser).append(";");
		sb.append("insDate=" + super.updDate).append(";");

		return sb.toString();
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

}
