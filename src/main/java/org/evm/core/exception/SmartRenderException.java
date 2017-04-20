 
package org.evm.core.exception;


// Referenced classes of package com.smart.platform.core.exception:
//			SmartRuntimeException

public class SmartRenderException extends SmartRuntimeException
{

	public SmartRenderException()
	{
	}

	public SmartRenderException(String messageCode)
	{
		super(messageCode);
	}

	public SmartRenderException(Throwable cause)
	{
		super(cause);
	}

	public SmartRenderException(String messageCode, Throwable cause)
	{
		super(messageCode, cause);
	}
}
