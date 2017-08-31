 
package org.evm.core.exception;


// Referenced classes of package com.smart.platform.core.exception:
//			SmartRuntimeException

public class SmartSystemException extends SmartRuntimeException
{

	public SmartSystemException()
	{
	}

	public SmartSystemException(String message)
	{
		super(message);
	}

	public SmartSystemException(Throwable cause)
	{
		super(cause);
	}

	public SmartSystemException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
