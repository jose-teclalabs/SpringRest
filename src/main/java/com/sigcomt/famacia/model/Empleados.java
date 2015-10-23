package com.sigcomt.famacia.model;

public class Empleados {
	
	private Integer idUsuario;
	private String usuario;
	private String clave;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	@Override
	public String toString() {
		return "Empleados [idUsuario=" + idUsuario + ", usuario=" + usuario + ", clave=" + clave + "]";
	}
	
	

}
