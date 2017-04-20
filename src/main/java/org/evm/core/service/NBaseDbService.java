package org.evm.core.service;

import org.springframework.jdbc.core.JdbcTemplate;

public class NBaseDbService {
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
