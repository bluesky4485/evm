package org.evm.biz.faulttype.entity;

import org.evm.core.entity.AbstractEntity;

public class FaultTypeVO extends AbstractEntity {
	/**
	 * 故障类型名称
	 */
	private String faultTypeName;
	/**
	 * 故障类型ID
	 */
	private long faultTypeId;
	/**
	 * 故障类型编号
	 */
	private String faultTypeNo;

	public String getFaultTypeNo() {
		return faultTypeNo;
	}

	public void setFaultTypeNo(String faultTypeNo) {
		this.faultTypeNo = faultTypeNo;
	}

	public String getFaultTypeName() {
		return faultTypeName;
	}

	public void setFaultTypeName(String faultTypeName) {
		this.faultTypeName = faultTypeName;
	}

	public long getFaultTypeId() {
		return faultTypeId;
	}

	public void setFaultTypeId(long faultTypeId) {
		this.faultTypeId = faultTypeId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if (this.faultTypeName != null)
			sb.append("故障类型名称:" + this.faultTypeName).append(";");
		if (this.faultTypeId != 0)
			sb.append("故障类型ID:" + this.faultTypeId).append(";");
		if (this.faultTypeNo != null)
			sb.append("故障类型编号:" + this.faultTypeNo).append(";");
		return sb.toString();
	}
}
