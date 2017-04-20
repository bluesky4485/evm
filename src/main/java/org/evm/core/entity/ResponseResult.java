package org.evm.core.entity;
/**
 * 客户端返回模型
 * 
 * @author xuh 下午04:07:00 2014-6-19
 * 
 */
public class ResponseResult {
	Object bizData;
	String message;
	String messageType;

	public Object getBizData() {
		return bizData;
	}

	public void setBizData(Object bizData) {
		this.bizData = bizData;
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
}
