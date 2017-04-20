 
package org.evm.core.exception;


// Referenced classes of package com.smart.platform.core.exception:
//			SmartRuntimeException

public class SmartLimitException extends SmartRuntimeException
{

	public SmartLimitException()
	{
	}

	public SmartLimitException(String messageCode)
	{
		super(messageCode);
	}

	public SmartLimitException(Throwable cause)
	{
		super(cause);
	}

	public SmartLimitException(String messageCode, Throwable cause)
	{
		super(messageCode, cause);
	}
}
