package com.hbt.semillero.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.hbt.semillero.entidad.Persona;



public class VentasDTO implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 1L;

	private Long id;
	private Persona persona;
	private LocalDateTime fechaVenta;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public LocalDateTime getFechaVenta() {
		return fechaVenta;
	}
	
	public void setFechaVenta(LocalDateTime fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

}