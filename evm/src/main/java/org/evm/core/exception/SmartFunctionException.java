package org.evm.core.exception;
/**
 * 权限异常
 * @author jerry.x  update  2016年11月24日 下午3:41:45
 */
public class SmartFunctionException extends SmartRuntimeException {

	public SmartFunctionException() {
	}

	public SmartFunctionException(String message) {
		super(message);
	}

	public SmartFunctionException(Throwable cause) {
		super(cause);
	}

	public SmartFunctionException(String message, Throwable cause) {
		super(message, cause);
	}
}
