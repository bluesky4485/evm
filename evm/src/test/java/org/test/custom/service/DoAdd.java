package org.test.custom.service;

import org.springframework.stereotype.Component;
@Component
public class DoAdd {
	@TestAnn(description = "查询用户")
	public int add(int i, int j) {
		return i + j;
	}
}
