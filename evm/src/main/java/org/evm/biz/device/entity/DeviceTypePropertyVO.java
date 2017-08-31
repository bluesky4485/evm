package org.evm.biz.device.entity;

import org.evm.core.entity.AbstractEntity;

public class DeviceTypePropertyVO extends AbstractEntity {
//	private long id;
	private String propertyName;
	private String propertyId;
	private long deviceTypeId;

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public long getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if(this.propertyName!=null)
		sb.append("propertyName="+this.propertyName).append(";");
		if(this.propertyId!=null)
		sb.append("propertyId="+this.propertyId).append(";");
		if(this.deviceTypeId!=0)
		sb.append("deviceTypeId="+this.deviceTypeId).append(";");
		
		return sb.toString();
	}
}
