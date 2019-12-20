package com.hbt.semillero.entidad;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @description Clase que determina la entidad que permite representar una
 *              Persona
 * 
 * @author Alberto Puche Algarin
 * 
 * @fecha 2019-12-19
 *
 */
@Entity
@Table(name = "PERSONAS")
public class Persona implements Serializable {

	/**
	 * Serializar es pasar un Objeto a un array de bytes y viceversa. Atributo que
	 * determina serialVersionUID es el id único que identifica una clase cuando lo
	 * serializamos. ;ediante este id podemos identificar el objeto convertido en un
	 * array de bytes.
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private String tipoDocumento;
	private Long numeroDocumento;
	private LocalDateTime fecha;
	
	/**
	 * Metodo encargado de retornar el valor del atributo id
	 * 
	 * @return El id asociado a la clase
	 */
	@Id
	@SequenceGenerator(allocationSize = 1, name = "PERSONAS_SCID_GENERATOR", sequenceName = "SEQ_PERSONAS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONAS_SCID_GENERATOR")
	@Column(name = "PER_ID")
	public Long getId() {
		return id;
	}
	
	/**
	 * Metodo encargado de modificar el valor del atributo id
	 * 
	 * @param id El nuevo id a modificar.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Metodo encargado de retornar el valor del atributo nombre
	 * 
	 * @return El nombre asociado a la clase
	 */
	@Column(name = "PER_NOMBRES")
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Metodo encargado de modificar el valor del atributo nombre
	 * 
	 * @param nombre El nuevo nombre a modificar.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo encargado de retornar el valor del atributo tipodocumento
	 * 
	 * @return El tipodocumento asociado a la clase
	 */
	@Column(name = "PER_TIPID")
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	
	/**
	 * @description Metodo encargado de retornar el valor del atributo tipodocumento
	 * 
	 * @return El tipodocumento asociado a la clase
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	/**
	 * Metodo encargado de retornar el valor del atributo numerodocumento
	 * 
	 * @return El numerodocumento asociado a la clase
	 */
	@Column(name = "PER_NUMID", unique=true, nullable = false)
	public Long getNumeroDocumento() {
		return numeroDocumento;
	}
	
	/**
	 * @description Metodo encargado de retornar el valor del atributo numerodocumento
	 * 
	 * @return El numerodocumento asociado a la clase
	 */
	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	
	/**
	 * Metodo encargado de retornar el valor del atributo fecha
	 * 
	 * @return La fecha asociado a la clase
	 */
	@Column(name = "PER_FECNAC")
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	/**
	 * Metodo encargado de modificar el valor del atributo fecha
	 * 
	 * @param fecha La nueva fecha a modificar.
	 */
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	

}
