/**
 * 
 */
package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.RolPersonajeComicDTO;



/**
 * 
 * @description Expone los métodos del EJB RolPersonajeComic Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 *
 * @author Alberto Puche Algarin
 *  
 * @fecha 2019-12-07
 */
@Local
public interface IGestionalRolPersonajeComicLocal {
	
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
	public abstract void crearRolPersonajeComic(RolPersonajeComicDTO rolPersonajeComicDTO);

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
	public void modificarRolPersonajeComic(Long id, String nombre, RolPersonajeComicDTO rolPersonajeComicDTO);

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
	public void eliminarRolPersonajeComic(Long id);

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
	public RolPersonajeComicDTO consultarRolPersonajeComic(Long id);

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
	public List<RolPersonajeComicDTO> consultarRolPersonajesComics();
	
	

}
