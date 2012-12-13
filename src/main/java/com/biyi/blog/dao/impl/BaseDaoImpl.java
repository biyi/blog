package com.biyi.blog.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;


@Component
class BaseDaoImpl {
	
	private JdbcTemplate template;
	private NamedParameterJdbcTemplate nameTemplate;

	protected NamedParameterJdbcTemplate getNameTemplate() {
		return nameTemplate;
	}

	protected JdbcTemplate getJdbcTemplate() {
		return template;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
		nameTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
}
