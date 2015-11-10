package com.sigcomt.famacia.controller;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import com.sigcomt.famacia.model.Sede;

@Controller
public class SedeController {
	String insertSql = "INSERT INTO sede " + "(descripcion) VALUES (?)";
	private static final Logger logger = LoggerFactory.getLogger(SedeController.class);
	
	@Autowired
	@Qualifier("dbDataSource")
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	//handles Sedes Getter
	
	
	@RequestMapping(value = "/rest/sedes", method = RequestMethod.GET)
	public @ResponseBody String getSedes() {
		logger.info("Start getSedes.");
		
		Gson gson = new Gson();
		
		
		List<Sede> sedList = new ArrayList<Sede>();
		//JDBC Code - Start
		String query = "select idSedes, descripcion from sede";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String,Object>> empRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> empRow : empRows){
			Sede sed = new Sede();
			sed.setIdSede(Integer.parseInt(String.valueOf(empRow.get("idSedes"))));
			sed.setDescripcion(String.valueOf(empRow.get("descripcion")));
			
			sedList.add(sed);
		}
		logger.info("JSON ==========================");
		logger.info(gson.toJson(sedList));
		String hola = gson.toJson(sedList).toString();
		return hola;
	}
	
	
	
	//handles Sedes Summit
	@RequestMapping(value = "/rest/addSede", method = RequestMethod.POST, headers = "content-type=application/json")
	public @ResponseBody Sede saveSede(@RequestBody Sede sede) {
		logger.info("Valores " + sede.getDescripcion());

		saveRecord(sede.getDescripcion());

		return sede;
	}
	
	public void saveRecord(String descripcion) {

		JdbcTemplate template = new JdbcTemplate(dataSource);

		// define query arguments

		Object[] params = new Object[] { descripcion};

		// define SQL types of the arguments

		int[] types = new int[] { Types.VARCHAR };

		// execute insert query to insert the data

		// return number of row / rows processed by the executed query

		int row = template.update(insertSql, params, types);

		System.out.println(row + " row inserted.");

	}
}
