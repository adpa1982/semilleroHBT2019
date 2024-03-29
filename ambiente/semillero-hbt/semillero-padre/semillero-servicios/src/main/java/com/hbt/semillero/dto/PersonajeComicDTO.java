/**
 * 
 */
package com.hbt.semillero.dto;

import java.io.Serializable;

/**
 * <b>Descripción:<b> Clase que determina el dto a usar para modificar, consultar y posteriormente eliminar un comic
 * 
 * @author ccastano
 */
/**
 * 
 * @description Clase que determina el dto a usar para modificar, consultar y
 *              posteriormente eliminar un personaje de un comic
 *
 * @author Alberto Puche Algarin
 * 
 * @fecha 2019-12-07
 */
public class PersonajeComicDTO implements Serializable {

	/**
	 * Serializar es pasar un Objeto a un array de bytes y viceversa. Atributo que
	 * determina serialVersionUID es el id único que identifica una clase cuando lo
	 * serializamos. ;ediante este id podemos identificar el objeto convertido en un
	 * array de bytes.
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private String estado;
	private String superpoder;
	private Long idComic;
	private Long idRol;

	/**
	 * Metodo encargado de retornar el valor del atributo id
	 * 
	 * @return El id asociado a la clase
	 */
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
	 * Metodo encargado de retornar el valor del atributo estado
	 * 
	 * @return El estado asociado a la clase
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo estado
	 * 
	 * @param estado El nuevo estado a modificar.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo superpoder
	 * 
	 * @return El superpoder asociado a la clase
	 */
	public String getSuperpoder() {
		return superpoder;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo superpoder
	 * 
	 * @param superpoder El nuevo superpoder a modificar.
	 */
	public void setSuperpoder(String superpoder) {
		this.superpoder = superpoder;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo idComic
	 * 
	 * @return El idComic asociado a la clase
	 */
	public Long getIdComic() {
		return idComic;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo idComic
	 * 
	 * @param idComic El nuevo idComic a modificar.
	 */
	public void setIdComic(Long idComic) {
		this.idComic = idComic;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo idRol
	 * 
	 * @return El idRol asociado a la clase
	 */
	public Long getIdRol() {
		return idRol;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo idRol
	 * 
	 * @param nombre El nuevo idRol a modificar.
	 */
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	/**
	 * Método encargado de convertir los datos recibidos en JSON al tipo PersonajeComicDTO.
	 * <b>Caso de Uso:</b>
	 * 
	 * @param arg Cadena que representa el objeto complejo JSON.
	 * @return Instancia con los datos recibidos.
	 */
	public static PersonajeComicDTO valueOf(String arg) {
		return JsonUtils.valueOf(arg, PersonajeComicDTO.class);
	}

	/**
	 * Método encargado de convertir los datos recibidos en ComicDTO al JSON
	 * esperado
	 * 
	 * @param dto DTO
	 * 
	 * @return Json
	 */
	@Override
	public String toString() {
		return JsonUtils.toStringJson(this);
	}

}
