 
package org.evm.core.exception;


// Referenced classes of package com.smart.platform.core.exception:
//			SmartRuntimeException

public class SmartDomainException extends SmartRuntimeException
{

	public SmartDomainException()
	{
	}

	public SmartDomainException(String message)
	{
		super(message);
	}

	public SmartDomainException(Throwable cause)
	{
		super(cause);
	}

	public SmartDomainException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
