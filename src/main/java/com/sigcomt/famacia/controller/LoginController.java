package com.sigcomt.famacia.controller;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
	
	@Autowired
	@Qualifier("dbDataSource")
	private DataSource dataSource;
	
	private static final Logger logger = LoggerFactory.getLogger(EmpleadosController.class);
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	

	static final String loginSql = "select usuario from empleados where usuario = ? and clave = ? ";
	
	
}