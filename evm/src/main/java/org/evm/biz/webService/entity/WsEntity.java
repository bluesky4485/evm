package org.evm.biz.webService.entity;

import org.evm.core.consts.MessageType;
import org.evm.core.entity.AbstractEntity;

public class WsEntity {
	/**
	 * 项目ID
	 */
	private String projectID;
	/**
	 * 服务ID
	 */
	private String serviceId;
	/**
	 * 
	 */
	private String seq;
	/**
	 * 系统ID100	用户APP
200	工程APP
300	维修APP

	 */
	private String systemId;
	/**
	 * 用户ID
	 */
	private String uid;
	/**
	 * 时间戳
	 */
	private String token;
	/**
	 * 输入参数
	 */
	private String para;
	/**
	 * 根据参数解析成实体VO
	 */
	private AbstractEntity paraEntity;
	/**
	 * 返回结果集
	 */
	private ResponseResult responseResult;

	/**
	 * 消息内容
	 */
	private String message = "success";
	/**
	 * sucess/error
	 */
	private String messageType = MessageType.success.toString();
	/**
	 * app版本号
	 */
	private String version;
	/**
	 * app下载版本路径
	 */
	private String appPath;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public AbstractEntity getParaEntity() {
		return paraEntity;
	}

	public void setParaEntity(AbstractEntity paraEntity) {
		this.paraEntity = paraEntity;
	}

	public ResponseResult getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(ResponseResult responseResult) {
		this.responseResult = responseResult;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getAppPath() {
		return appPath;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}

}
