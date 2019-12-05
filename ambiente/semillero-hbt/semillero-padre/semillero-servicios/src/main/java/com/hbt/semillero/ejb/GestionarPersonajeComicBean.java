/**
 * 
 */
package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.PersonajeComicDTO;

/**
 * 
 * @description 
 * 
 * @author Alberto Puche Algarin 
 * 
 * @fecha 2019-12-05
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonajeComicBean implements IGestionarPersonajeComicLocal {

	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	
	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;
	
	/**
	 *
	 * @description Metodo encargado de crear un el Personaje de un comic y persistirlo
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-05
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeComicLocal#crearPersonajeComic(com.hbt.semillero.dto.PersonajeComicDTO)
	 *
	 * @param personajeComicNuevo informacion nueva a crear
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearPersonajeComic(PersonajeComicDTO personajeComicNuevo) {
		// TODO Auto-generated method stub
		logger.debug("Se Ejecuta el metodo crear");
	}

	/**
	 *
	 * @description Metodo encargado de modificar el Personaje de un comic y persistirlo
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-05
	 * 
     * @see com.hbt.semillero.ejb.IGestionarPersonajeComicLocal#modificarPersonajeComic(com.hbt.semillero.dto.PersonajeComicDTO)
	 *
	 * @param id, nombre , personajeComicNuevo informacion para modificar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarPersonajeComic(Long id, String nombre, PersonajeComicDTO personajeComicNuevo) {
		// TODO Auto-generated method stub
		logger.debug("Se Ejecuta el metodo modificar");
	}

	/**
	 *
	 * @description Metodo encargado de eliminar un Personaje de un comic
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-05
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeComicLocal#eliminarPersonajeComic(java.lang.Long)
	 *
	 * @param id informacion para eliminar un personaje de un comic
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPersonajeComic(Long id) {
		// TODO Auto-generated method stub
		logger.debug("Se Ejecuta el metodo eliminar");
	}

	/**
	 *
	 * @description Metodo encargado de mostrar el Personaje de un comic
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-05
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeComicLocal#consultarPersonajeComic(java.lang.Long)
	 *
	 * @param id informacion para buscar un personaje de un comic
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PersonajeComicDTO consultarPersonajeComic(Long id) {
		// TODO Auto-generated method stub
		logger.debug("Se Ejecuta el metodo consultar personaje comic");
		return null;
	}

	/**
	 *
	 * @description Metodo encargado de crear un el Personaje de un comic y persistirlo
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-05
	 * 
	 * @see main.java.com.hbt.semillero.ejb.IGestionarPersonajeComicLocal#consultarPersonajeComics()
	 *
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeComicDTO> consultarPersonajeComics() {
		// TODO Auto-generated method stub
		logger.debug("Se Ejecuta el metodo consultar todos los personajes de uncomic");
		return null;
	}
	

}
