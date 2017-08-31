package org.evm.core.exception;


// Referenced classes of package com.smart.platform.core.exception:
//			SmartRuntimeException

public class SmartDBAccessException extends SmartRuntimeException
{

	public SmartDBAccessException()
	{
	}

	public SmartDBAccessException(String message)
	{
		super(message);
	}

	public SmartDBAccessException(Throwable cause)
	{
		super(cause);
	}

	public SmartDBAccessException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
