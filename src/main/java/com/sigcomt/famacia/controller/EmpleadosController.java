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
import com.sigcomt.famacia.model.Empleados;



@Controller
public class EmpleadosController {

	String insertSql = "INSERT INTO Empleados " + "(usuario, clave) VALUES (?, ?)";
	private static final Logger logger = LoggerFactory.getLogger(EmpleadosController.class);

	@Autowired
	@Qualifier("dbDataSource")
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@RequestMapping(value = "/rest/empleados", method = RequestMethod.GET)
	public @ResponseBody String getEmpleados() {
		logger.info("Start getEmpleados.");
		
		Gson gson = new Gson();
		
		
		List<Empleados> empList = new ArrayList<Empleados>();
		//JDBC Code - Start
		String query = "select idUsuario, usuario, clave from Empleados";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String,Object>> empRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> empRow : empRows){
			Empleados emp = new Empleados();
			emp.setIdUsuario(Integer.parseInt(String.valueOf(empRow.get("idUsuario"))));
			emp.setUsuario(String.valueOf(empRow.get("usuario")));
			emp.setClave(String.valueOf(empRow.get("clave")));
			empList.add(emp);
		}
		logger.info("JSON ==========================");
		logger.info(gson.toJson(empList));
		String hola = gson.toJson(empList).toString();
		return hola;
	}
	
	// handles person form submit
		@RequestMapping(value = "/rest/addEmpleado", method = RequestMethod.POST, headers = "content-type=application/json")
		public @ResponseBody Empleados savePerson(@RequestBody Empleados empleado) {
			logger.info("Valores " + empleado.getUsuario());

			saveRecord(empleado.getUsuario(), empleado.getClave());

			return empleado;
		}

		public void saveRecord(String usuario, String clave) {

			JdbcTemplate template = new JdbcTemplate(dataSource);

			// define query arguments

			Object[] params = new Object[] { usuario, clave };

			// define SQL types of the arguments

			int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };

			// execute insert query to insert the data

			// return number of row / rows processed by the executed query

			int row = template.update(insertSql, params, types);

			System.out.println(row + " row inserted.");

		}
	
}
