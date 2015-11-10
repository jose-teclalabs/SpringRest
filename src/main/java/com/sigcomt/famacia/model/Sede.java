package com.sigcomt.famacia.model;

public class Sede {
	
	private Integer idSede;
	private String descripcion;
	
	public Integer getIdSede() {
		return idSede;
	}
	public void setIdSede(Integer idSede) {
		this.idSede = idSede;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Sede [idSede=" + idSede + ", descripcion=" + descripcion + "]";
	}

}

