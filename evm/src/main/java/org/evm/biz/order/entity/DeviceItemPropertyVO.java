package org.evm.biz.order.entity;

import org.evm.core.entity.AbstractEntity;

public class DeviceItemPropertyVO  extends AbstractEntity{
	/**
	 * 设备ID
	 */
	private long deviceItemId;
	/**
	 * 设备属性ID
	 */
	private String deviceItemPropertyId;
	/**
	 * 设备属性名
	 */
	private String deviceItemPropertyName;
	/**
	 * 属性值
	 */
	private String deviceItemPropertyValue;
	/**
	 * 设备类型属性ID
	 */
	private long devcieTypePropertyId;
	/**
	 * 设备属性名
	 */
	private String deviceTypePropertyName;
	private String deviceClassTypeId;
	private String deviceClassTypeName;
	public long getDeviceItemId() {
		return deviceItemId;
	}
	public void setDeviceItemId(long deviceItemId) {
		this.deviceItemId = deviceItemId;
	}
	public String getDeviceItemPropertyId() {
		return deviceItemPropertyId;
	}
	public void setDeviceItemPropertyId(String deviceItemPropertyId) {
		this.deviceItemPropertyId = deviceItemPropertyId;
	}
	public String getDeviceItemPropertyName() {
		return deviceItemPropertyName;
	}
	public void setDeviceItemPropertyName(String deviceItemPropertyName) {
		this.deviceItemPropertyName = deviceItemPropertyName;
	}
	public String getDeviceItemPropertyValue() {
		return deviceItemPropertyValue;
	}
	public void setDeviceItemPropertyValue(String deviceItemPropertyValue) {
		this.deviceItemPropertyValue = deviceItemPropertyValue;
	}
	public long getDevcieTypePropertyId() {
		return devcieTypePropertyId;
	}
	public void setDevcieTypePropertyId(long devcieTypePropertyId) {
		this.devcieTypePropertyId = devcieTypePropertyId;
	}
	public String getDeviceTypePropertyName() {
		return deviceTypePropertyName;
	}
	public void setDeviceTypePropertyName(String deviceTypePropertyName) {
		this.deviceTypePropertyName = deviceTypePropertyName;
	}
	public String getDeviceClassTypeId() {
		return deviceClassTypeId;
	}
	public void setDeviceClassTypeId(String deviceClassTypeId) {
		this.deviceClassTypeId = deviceClassTypeId;
	}
	public String getDeviceClassTypeName() {
		return deviceClassTypeName;
	}
	public void setDeviceClassTypeName(String deviceClassTypeName) {
		this.deviceClassTypeName = deviceClassTypeName;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("deviceItemId;="+this.deviceItemId).append(";");
		sb.append("deviceItemPropertyId;="+this.deviceItemPropertyId).append(";");
		
		sb.append("deviceItemPropertyValue;="+this.deviceItemPropertyValue).append(";");
		sb.append("devcieTypePropertyId;="+this.devcieTypePropertyId).append(";");
		sb.append("updUser=" + super.updUser).append(";");
		sb.append("updDate=" + super.updDate).append(";");
		sb.append("insUser=" + super.insUser).append(";");
		sb.append("insDate=" + super.updDate).append(";");
		
		return sb.toString();
	}
	 

}
