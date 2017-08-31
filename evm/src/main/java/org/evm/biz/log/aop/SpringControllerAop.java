package org.evm.biz.log.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

public class SpringControllerAop {
	public SpringControllerAop() {
		System.out.println("SpringControllerAop");
	}

	// @Around("within(org.springframework.web.bind.annotation.support.HandlerMethodInvoker..*)")
	@Around("execution( * org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.handle(..))")
	public Object aa(ProceedingJoinPoint pjp) throws Throwable {
		try {
			Object retVal = pjp.proceed();
			System.out.println(retVal);
			return retVal;
		} catch (Exception e) {
			System.out.println("异常");
			return null;
		}
	}
}
