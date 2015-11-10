package com.sigcomt.famacia.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sigcomt.famacia.model.Empleados;
import com.sigcomt.famacia.model.Sede;
import com.sigcomt.famacia.model.Validacion;

@Controller
public class LoginController {

	@Autowired
	@Qualifier("dbDataSource")
	private DataSource dataSource;

	private static final Logger logger = LoggerFactory.getLogger(EmpleadosController.class);

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// handles Sedes Summit
	@RequestMapping(value = "/rest/Login", method = RequestMethod.POST, headers = "content-type=application/json")
	public @ResponseBody Validacion Login(@RequestBody Empleados empleados) {
		String prueba;

		Empleados emp = new Empleados();
		Validacion val = new Validacion();

		String query = "select idUsuario as codigo from empleados where usuario = ? and clave = ? ";

		PreparedStatement pstmt;
		try {
			pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, empleados.getUsuario());
			pstmt.setString(2, empleados.getClave());

			ResultSet resultSet = pstmt.executeQuery();
			String codigoUsuario = "";

			if (resultSet.next()) {
				codigoUsuario = resultSet.getString("codigo");
				val.setMessage("Correcto");
				val.setSucces(true);
				val.setValor(codigoUsuario);
				
				return val;

			} else {

				
				val.setMessage("Incorrecto");
				val.setSucces(false);
				
				
				return val;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return val;

	}

}