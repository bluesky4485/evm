package org.test.custom.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppAoptest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-aop.xml");
		DoAdd add = (DoAdd) context.getBean("add");
		int j = add.add(1, 2);
		System.out.println(j);
	}

}
