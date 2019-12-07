/**
 * 
 */
package com.hbt.semillero.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

/**
 * 
 * @description Clase que determina la entidad que permite representar la COMIC
 * 
 * @author Alberto Puche Algarin
 * 
 * @fecha 2019-12-07
 *
 */
@Entity
@Table(name = "PERSONAJES")
public class PersonajeComic implements Serializable {

	/**
	 * Serializar es pasar un Objeto a un array de bytes y viceversa. Atributo que
	 * determina serialVersionUID es el id único que identifica una clase cuando lo
	 * serializamos. ;ediante este id podemos identificar el objeto convertido en un
	 * array de bytes.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize = 1, name = "PERSONAJE_ID_GENERATOR", sequenceName = "SEQ_PERSONAJES")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONAJE_ID_GENERATOR")
	@Column(name = "PER_ID")
	private Long id;

	@Column(name = "PER_NOMBRE")
	private String nombre;

	@Column(name = "PER_ESTADO")
	private String estado;

	@Column(name = "PER_SUPERPODER")
	private String superpoder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PER_ID_COMIC", nullable = false)
	private Comic comic;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PER_ID_ROL", nullable = false)
	private RolPersonajeComic rol;

	/**
	 * @description Metodo encargado de retornar el valor del atributo id
	 * 
	 * @return El id asociado a la clase
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @description Metodo encargado de modificar el valor del atributo id
	 * 
	 * @param string El nuevo id a modificar.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @description Metodo encargado de retornar el valor del atributo nombre
	 * 
	 * @return El nombre asociado a la clase
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @description Metodo encargado de modificar el valor del atributo nombre
	 * 
	 * @param nombre El nuevo nombre a modificar.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @description Metodo encargado de retornar el valor del atributo estado
	 * 
	 * @return El estado asociado a la clase
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @description Metodo encargado de retornar el valor del atributo estado
	 * 
	 * @return El estado asociado a la clase
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @description Metodo encargado de retornar el valor del atributo superpoder
	 * 
	 * @return El superpoder asociado a la clase
	 */
	public String getSuperpoder() {
		return superpoder;
	}

	/**
	 * @description Metodo encargado de retornar el valor del atributo superpoder
	 * 
	 * @return El superpoder asociado a la clase
	 */
	public void setSuperpoder(String superpoder) {
		this.superpoder = superpoder;
	}

	/**
	 * @description Metodo encargado de retornar el valor del atributo comic
	 * 
	 * @return El comic asociado a la clase
	 */
	public Comic getComic() {
		return comic;
	}

	/**
	 * @description Metodo encargado de retornar el valor del atributo comic
	 * 
	 * @return El comic asociado a la clase
	 */
	public void setComic(Comic comic) {
		this.comic = comic;
	}

	/**
	 * @description Metodo encargado de retornar el valor del atributo rol
	 * 
	 * @return El rol asociado a la clase
	 */
	public RolPersonajeComic getRol() {
		return rol;
	}

	/**
	 * @description Metodo encargado de retornar el valor del atributo rol
	 * 
	 * @return El rol asociado a la clase
	 */
	public void setRol(RolPersonajeComic rol) {
		this.rol = rol;
	}

}
