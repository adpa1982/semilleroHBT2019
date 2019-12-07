/**
 * 
 */
package com.hbt.semillero.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Table(name = "ROLES")
public class RolPersonajeComic implements Serializable {

	/**
	 * Serializar es pasar un Objeto a un array de bytes y viceversa. Atributo que
	 * determina serialVersionUID es el id único que identifica una clase cuando lo
	 * serializamos. ;ediante este id podemos identificar el objeto convertido en un
	 * array de bytes.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize = 1, name = "ROLE_ID_GENERATOR", sequenceName = "SEQ_ROLES")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_ID_GENERATOR")
	@Column(name = "ROL_ID")
	private Long id;
	@Column(name = "ROL_NOMBRE")
	private String nombre;
	@Column(name = "ROL_ESTADO")
	private String estado;

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

}
