package com.hbt.semillero.ejb;

import java.util.List;

import com.hbt.semillero.dto.PersonaDTO;

/**
 * 
 * @description Expone los métodos del EJB IGestionarPersonaLocal Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 *
 * @author Alberto Puche Algarin
 *  
 * @fecha 2019-12-19
 */
public interface IGestionarPersonaLocal {
	/**
	 *
	 * @description Metodo encargado de crear la Personaje
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 *
	 * @param Persona comicNuevo
	 */
	public void crearPersona(PersonaDTO personaDTO);

	/**
	 *
	 * @description Metodo encargado de actualizar la Persona
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-05
	 *
	 * @param Long id, String nombre, PersonaDTO personajeNuevo
	 */
	public void modificarPersona(Long id, String nombre, PersonaDTO personaDTO);

	/**
	 *
	 * @description Metodo encargado de eliminar una Persona
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 *
	 * @param Long id
	 */
	public void eliminarPersona(Long id);

	/**
	 * @description Metodo encargado de devolver la informacion de una persona
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 *
	 *
	 * @return comic Resultado de la consulta @throws
	 */
	public List<PersonaDTO> consultarPersona();

	/**
	 *
	 * @description Metodo encargado de devolver la informacion de las personas
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 *
	 *
	 * @return
	 */
	public List<PersonaDTO> consultarPersona(Long id);
}