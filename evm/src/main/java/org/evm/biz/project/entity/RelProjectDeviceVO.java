package org.evm.biz.project.entity;

import java.util.List;

import org.evm.biz.device.entity.DeviceTypePropertyVO;
import org.evm.core.entity.AbstractEntity;

public class RelProjectDeviceVO extends AbstractEntity {
	/**
	 * 主键
	 */
	private long pdId;
	/**
	 * 项目ID
	 */
	private long projectId;
	/**
	 * 设备分类ID
	 */
	private long deviceId;
	/**
	 * 具体类型设备数量
	 */
	private String deviceCnt;
	/**
	 * 设备名称
	 */
	private String deviceName;
	/**
	 * 大分类
	 */
	private String deviceClassType;
	/**
	 * 大分类名称
	 */
	private String deviceClassTypeName;
	/**
	 * 设备属性
	 */
	private List<DeviceTypePropertyVO> properties;

	public long getPdId() {
		return pdId;
	}

	public void setPdId(long pdId) {
		this.pdId = pdId;
	}

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getDeviceCnt() {
		return deviceCnt;
	}

	public void setDeviceCnt(String deviceCnt) {
		this.deviceCnt = deviceCnt;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceClassType() {
		return deviceClassType;
	}

	public void setDeviceClassType(String deviceClassType) {
		this.deviceClassType = deviceClassType;
	}

	public String getDeviceClassTypeName() {
		return deviceClassTypeName;
	}

	public void setDeviceClassTypeName(String deviceClassTypeName) {
		this.deviceClassTypeName = deviceClassTypeName;
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
		sb.append("pdId=" + this.pdId).append(";");
		sb.append("projectId=" + this.projectId).append(";");
		sb.append("deviceId=" + this.deviceId).append(";");
		sb.append("deviceCnt=" + this.deviceCnt).append(";");
		sb.append("deviceName=" + this.deviceName).append(";");
		sb.append("deviceClassType=" + this.deviceClassType).append(";");
		return sb.toString();
	}
}
