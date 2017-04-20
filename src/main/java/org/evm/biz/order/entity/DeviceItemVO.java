package org.evm.biz.order.entity;

import java.math.BigDecimal;
import java.util.List;

import org.evm.core.entity.AbstractEntity;

public class DeviceItemVO extends AbstractEntity {
	/**
	 * 设备ID
	 */
	private long deviceItemId;
	/**
	 * 设备名称
	 */
	private String deviceItemName;
	/**
	 * 订单ID
	 */
	private long orderId;
	/**
	 * 设备类型ID
	 */
	private long deviceTypeId;
	/**
	 * 设备标识（部分设备唯一标识）
	 */
	private String deviceItemUid;
	/**
	 * 设备地址
	 */
	private String deviceItemAddr;
	private BigDecimal lng;
	private BigDecimal lat;
	// --------------------------------------------------
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 设备类型名称
	 */
	private String deviceTypeName;
	/**
	 * 设备分类
	 */
	private long deviceTypeClassType;
	private String deviceTypeClassTypeDesc;
	/**
	 * 属性
	 */
	private String deviceItemPropertyStr;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 项目ID
	 */
	private String projectId;
	/**
	 * 设备属性表
	 */
	private List<DeviceItemPropertyVO> deviceItemPropertyList;
	/**
	 * 该类型设备已安装数量
	 */
	private int fixedCnt;

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public int getFixedCnt() {
		return fixedCnt;
	}

	public void setFixedCnt(int fixedCnt) {
		this.fixedCnt = fixedCnt;
	}

	public String getDeviceItemPropertyStr() {
		return deviceItemPropertyStr;
	}

	public void setDeviceItemPropertyStr(String deviceItemPropertyStr) {
		this.deviceItemPropertyStr = deviceItemPropertyStr;
	}

	public List<DeviceItemPropertyVO> getDeviceItemPropertyList() {
		return deviceItemPropertyList;
	}

	public void setDeviceItemPropertyList(List<DeviceItemPropertyVO> deviceItemPropertyList) {
		this.deviceItemPropertyList = deviceItemPropertyList;
	}

	public long getDeviceItemId() {
		return deviceItemId;
	}

	public void setDeviceItemId(long deviceItemId) {
		this.deviceItemId = deviceItemId;
	}

	public long getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceItemName() {
		return deviceItemName;
	}

	public void setDeviceItemName(String deviceItemName) {
		this.deviceItemName = deviceItemName;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getDeviceTypeClassType() {
		return deviceTypeClassType;
	}

	public void setDeviceTypeClassType(long deviceTypeClassType) {
		this.deviceTypeClassType = deviceTypeClassType;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public String getDeviceItemUid() {
		return deviceItemUid;
	}

	public void setDeviceItemUid(String deviceItemUid) {
		this.deviceItemUid = deviceItemUid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getDeviceTypeClassTypeDesc() {
		return deviceTypeClassTypeDesc;
	}

	public void setDeviceTypeClassTypeDesc(String deviceTypeClassTypeDesc) {
		this.deviceTypeClassTypeDesc = deviceTypeClassTypeDesc;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getDeviceItemAddr() {
		return deviceItemAddr;
	}

	public void setDeviceItemAddr(String deviceItemAddr) {
		this.deviceItemAddr = deviceItemAddr;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if (this.deviceItemId != 0)
			sb.append("设备ID:" + this.deviceItemId).append(";");
		// if (this.deviceItemName != null)
		// sb.append("deviceItemName=" + this.deviceItemName).append(";");
		if (this.orderId != 0)
			sb.append("订单ID:" + this.orderId).append(";");
		if (this.deviceTypeId != 0)
			sb.append("设备类型ID:" + this.deviceTypeId).append(";");
		if (this.deviceItemUid != null)
			sb.append("设备标识:" + this.deviceItemUid).append(";");
		if (this.deviceItemAddr != null)
			sb.append("设备地址:" + this.deviceItemAddr).append(";");
		// sb.append("updUser=" + super.updUser).append(";");
		// sb.append("updDate=" + super.updDate).append(";");
		// sb.append("insUser=" + super.insUser).append(";");
		// sb.append("insDate=" + super.updDate).append(";");
		return sb.toString();
	}
}
