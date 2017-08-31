package org.evm.biz.func.entity;

import org.evm.core.entity.AbstractEntity;
/**
 * 服务权限对应关系
 * @author jerry.x
 *
 */
public class AppServiceVO extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 服务编号
	 */
	private String serviceId;
	/**
	 * 描述
	 */
	private String serviceDesc;
	/**
	 * 权限名称
	 */
	private String functionId;
 

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
 

}
