/**
 * 
 */
package com.hbt.semillero.ejb;

import java.util.ArrayList;
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
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.PersonajeComic;
import com.hbt.semillero.entidad.RolPersonajeComic;

/**
 * 
 * @description Clase que determina el bean para realizar las gestion del personaje de un comics
 * 
 * @author Alberto Puche Algarin
 * 
 * @fecha 2019-12-05
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonajeComicBean implements IGestionarPersonajeComicLocal {

	final static Logger logger = Logger.getLogger(GestionarPersonajeComicBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 *
	 * @description Metodo encargado de crear un el Personaje de un comic y
	 *              persistirlo
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
	public void crearPersonajeComic(PersonajeComicDTO personajeComicDTO) {
		// TODO Auto-generated method stub
		logger.debug("Se Ejecuta el metodo crear");

		PersonajeComic personajeComic = convertirDTOEntidad(personajeComicDTO);
		entityManager.persist(personajeComic);

		logger.debug("Finaliza el metodo crear");
	}

	/**
	 *
	 * @description Metodo encargado de modificar el Personaje de un comic y
	 *              persistirlo
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
	public void modificarPersonajeComic(Long id, String nombre, PersonajeComicDTO personajeComicDTO) {
		// TODO Auto-generated method stub
		logger.debug("Se Ejecuta el metodo modificar");
		logger.debug("Finaliza el metodo modificar");
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
		logger.debug("Finaliza el metodo eliminar");
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
		logger.debug("Finaliza el metodo consultar personaje comic");
		return null;
	}

	/**
	 *
	 * @description Metodo encargado de mostrar todos los Personaje de un comic
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-05
	 * 
	 * @see main.java.com.hbt.semillero.ejb.IGestionarPersonajeComicLocal#consultarPersonajeComics()
	 *
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeComicDTO> consultarPersonajesComics() {
		// TODO Auto-generated method stub
		logger.debug("Se Ejecuta el metodo consultar todos los personajes de un comic");

		/*
		 * Convierte un PersonajeComic a PersonajeComicDTO
		 */
		List<PersonajeComicDTO> listarPersonajesComicsDTO = new ArrayList<PersonajeComicDTO>();

		String query = "SELECT personajecomic FROM PersonajeComic personajecomic";
		List<PersonajeComic> personajesComics = entityManager.createQuery(query).getResultList();

		for (PersonajeComic personajeComic : personajesComics) {
			listarPersonajesComicsDTO.add(convertirEntidadDTO(personajeComic));
		}

		logger.debug("Finaliza el metodo consultar todos los personajes de un comic");

		return listarPersonajesComicsDTO;
	}

	/**
	 *
	 * @description Metodo encargado de mostrar todos los Personaje de un comic
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-05
	 * 
	 * @see main.java.com.hbt.semillero.ejb.IGestionarPersonajeComicLocal#consultarPersonajeComics(id)
	 *
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public List<PersonajeComicDTO> consultarPersonajesComics(Long idComic) {
		// TODO Auto-generated method stub
		logger.debug("Se Ejecuta el metodo consultar todos los personajes de un comic por idComic");

		/*
		 * Convierte un PersonajeComic a PersonajeComicDTO
		 */
		List<PersonajeComicDTO> listarPersonajesComicsDTO = new ArrayList<PersonajeComicDTO>();

		String query = "SELECT personajeComic FROM PersonajeComic personajeComic WHERE personajeComic.comic.id = :idComic";
		List<PersonajeComic> personajesComics = entityManager.createQuery(query).setParameter("idComic", idComic).getResultList();
		
		for (PersonajeComic personajeComic : personajesComics) {
			listarPersonajesComicsDTO.add(convertirEntidadDTO(personajeComic));
		}

		logger.debug("Finaliza el metodo consultar todos los personajes de un comic idComic");
		
		return listarPersonajesComicsDTO;
	}

	/**
	 * 
	 * @description Metodo encargado de transformar un personajeComic a un
	 *              personajeComicDTO
	 * 
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-05
	 * 
	 * @param PersonajeComicDTO
	 * 
	 * @return
	 */
	// @SuppressWarnings("unused")
	private PersonajeComic convertirDTOEntidad(PersonajeComicDTO personajeComicDTO) {

		PersonajeComic personajeComic = new PersonajeComic();

		if (personajeComicDTO.getId() != null) {
			personajeComic.setId(personajeComicDTO.getId());
		}

		personajeComic.setId(personajeComicDTO.getId());
		personajeComic.setNombre(personajeComicDTO.getNombre());
		personajeComic.setEstado(personajeComicDTO.getEstado());
		personajeComic.setSuperpoder(personajeComicDTO.getSuperpoder());

		personajeComic.setComic(new Comic());
		personajeComic.getComic().setId(personajeComicDTO.getIdComic());

		personajeComic.setRol(new RolPersonajeComic());
		personajeComic.getRol().setId(personajeComicDTO.getIdRol());

		return personajeComic;
	}

	/**
	 * 
	 * @description Metodo encargado de transformar un personajeComicDTO a un
	 *              personajeComic
	 * 
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-05
	 * 
	 * @param PersonajeComic
	 * 
	 * @return
	 */
	// @SuppressWarnings("unused")
	private PersonajeComicDTO convertirEntidadDTO(PersonajeComic personajeComic) {

		PersonajeComicDTO personajeComicDTO = new PersonajeComicDTO();

		if (personajeComic.getId() != null) {
			personajeComicDTO.setId(personajeComic.getId());
		}

		personajeComicDTO.setId(personajeComic.getId());
		personajeComicDTO.setNombre(personajeComic.getNombre());
		personajeComicDTO.setEstado(personajeComic.getEstado());
		personajeComicDTO.setSuperpoder(personajeComic.getSuperpoder());

		personajeComicDTO.setIdComic(personajeComic.getComic().getId());
		personajeComicDTO.setIdRol(personajeComic.getRol().getId());

		return personajeComicDTO;
	}

}
