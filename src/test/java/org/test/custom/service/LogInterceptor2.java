package org.test.custom.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogInterceptor2 {

	// @Pointcut("execution(public * org.test.custom.service.*.*(..))")
	@Pointcut("@annotation(org.test.custom.service.TestAnn)")
	public void myMethod() {
	};

	/*
	 * @Before("execution(public void com.bjsxt.dao.impl.UserDAOImpl.save(com.bjsxt.model.User))"
	 * )
	 */
	@Before("myMethod()")
	public void before(JoinPoint joinPoint) {
		System.out.println("method state");
	}

	@After("myMethod()")
	public void after(JoinPoint joinPoint) {
		System.out.println("method after");
	}

}