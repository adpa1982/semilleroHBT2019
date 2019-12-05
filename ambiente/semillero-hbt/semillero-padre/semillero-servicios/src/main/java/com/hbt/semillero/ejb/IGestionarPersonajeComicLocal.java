package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.PersonajeComicDTO;

/**
 * 
 * @description Expone los métodos del EJB GestionarPersonajeComic Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 *
 * @author Alberto Puche Algarin
 *  
 * @fecha 2019-12-05
 */
@Local
public interface IGestionarPersonajeComicLocal {
	
	/**
	 *
	 * @description Metodo encargado de crear un el Personaje de un comic y persistirlo
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-05
	 *
	 * @param personajeComicNuevo informacion nueva a crear
	 */
	public void crearPersonajeComic(PersonajeComicDTO personajeComicNuevo);

	/**
	 *
	 * @description Metodo encargado de consultar un Personaje de un comic modificarlo y guardarlo
	 *
	 * @author Alberto Puche Algarin
	 * 
	 * @fecha 2019-12-05
	 *
	 * @param personajeComicModificar informacion nueva a modificar
	 */
	public void modificarPersonajeComic(Long id, String nombre, PersonajeComicDTO personajeComicNuevo);

	/**
	 *
	 * @description Metodo encargado de eliminar un Personaje de un comic para eliminarlo
	 *
	 * @author Alberto Puche Algarin
	 * 
	 * @fecha 2019-12-05
	 *
	 * @param personajeComicEliminar informacion a eliminar
	 */
	public void eliminarPersonajeComic(Long id);

	/**
	 *
	 * @description Metodo encargado de retornar la informacion de un Personaje de un comic
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-05
	 * 
	 * @param id identificador del Personaje de un comic a ser consultado
	 * @return personajeComic Resultado de la consulta
	 * @throws Exception si no se recibe id
	 */
	public PersonajeComicDTO consultarPersonajeComic(Long id);

	/**
	 *
	 * @description Metodo encargado de retornar una lista de Personajes de comics
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-05
	 *
	 * @return
	 */
	public List<PersonajeComicDTO> consultarPersonajeComics();


}
