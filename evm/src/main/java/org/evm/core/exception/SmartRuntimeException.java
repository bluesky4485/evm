package org.evm.core.exception;

public class SmartRuntimeException extends RuntimeException {

	protected String msgCode;

	public SmartRuntimeException() {
		msgCode = null;
	}

	public SmartRuntimeException(String messageCode) {
		super(messageCode);
		msgCode = null;
		msgCode = messageCode;
	}

	public SmartRuntimeException(Throwable cause) {
		super(cause);
		msgCode = null;
	}

	public SmartRuntimeException(String messageCode, Throwable cause) {
		super(messageCode, cause);
		msgCode = null;
		msgCode = messageCode;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
}
