package org.evm.biz.func.entity;

import org.evm.core.entity.AbstractEntity;

public class FuncVO extends AbstractEntity {
	/**
	 * 权限ID
	 */
	private String funcId;
	/**
	 * 权限名称
	 */
	private String funcName;
	/**
	 * 权限描述
	 */
	private String funcRemark;
	/**
	 * 用戶名
	 */
	private String uName;
	/**
	 * 用户ID
	 */
	private String uId;
	/**
	 * App调用的serviceID;
	 */
	private String serviceId;

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncRemark() {
		return funcRemark;
	}

	public void setFuncRemark(String funcRemark) {
		this.funcRemark = funcRemark;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if (this.funcId != null)
			sb.append("权限ID:" + this.funcId).append(";");
		if (this.funcName != null)
			sb.append("权限名称:" + this.funcName).append(";");
		if (this.funcRemark != null)
			sb.append("权限备注:" + this.funcRemark).append(";");
		// sb.append("updUser=" + super.updUser).append(";");
		// sb.append("updDate=" + super.updDate).append(";");
		// sb.append("insUser=" + super.insUser).append(";");
		// sb.append("insDate=" + super.updDate).append(";");
		return sb.toString();
	}
}
