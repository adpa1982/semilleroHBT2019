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

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeComicDTO;
import com.hbt.semillero.dto.RolPersonajeComicDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.PersonajeComic;
import com.hbt.semillero.entidad.RolPersonajeComic;

/**
 * 
 * @description Clase que determina el bean para realizar las gestion del personaje de un comics
 * 
 * @author Alberto Puche Algarin
 * 
 * @fecha 2019-12-07
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarRolPersonajeComicBean implements IGestionalRolPersonajeComicLocal {
	
	final static Logger logger = Logger.getLogger(GestionarRolPersonajeComicBean.class);

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
	 * @fecha 2019-12-07
	 * 
	 * @see com.hbt.semillero.ejb.IGestionalRolPersonajeComicLocal#crearRolPersonajeComic(com.hbt.semillero.dto.PersonajeComicDTO)
	 *
	 * @param personajeComicNuevo informacion nueva a crear
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearRolPersonajeComic(RolPersonajeComicDTO rolPersonajeComicDTO) {
		// TODO Auto-generated method stub
		logger.debug("Se Ejecuta el metodo crear");
		
		RolPersonajeComic rolPersonajeComic = convertirDTOEntidad(rolPersonajeComicDTO);
		entityManager.persist(rolPersonajeComic);

		logger.debug("Finaliza el metodo crear");
	}

	/**
	 *
	 * @description Metodo encargado de modificar el Personaje de un comic y
	 *              persistirlo
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-07
	 * 
	 * @see com.hbt.semillero.ejb.IGestionalRolPersonajeComicLocal#modificarRolPersonajeComic(com.hbt.semillero.dto.PersonajeComicDTO)
	 *
	 * @param id, nombre , personajeComicNuevo informacion para modificar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarRolPersonajeComic(Long id, String nombre, RolPersonajeComicDTO rolPersonajeComicDTO) {
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
	 * @fecha 2019-12-07
	 * 
	 * @see com.hbt.semillero.ejb.IGestionalRolPersonajeComicLocal#eliminarRolPersonajeComic(java.lang.Long)
	 *
	 * @param id informacion para eliminar un personaje de un comic
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarRolPersonajeComic(Long id) {
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
	 * @fecha 2019-12-07
	 * 
	 * @see com.hbt.semillero.ejb.IGestionalRolPersonajeComicLocal#consultarRolPersonajeComic(java.lang.Long)
	 *
	 * @param id informacion para buscar un personaje de un comic
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RolPersonajeComicDTO consultarRolPersonajeComic(Long id) {
		// TODO Auto-generated method stub
		logger.debug("Se Ejecuta el metodo consultar rol personaje comic");
		
		RolPersonajeComic rolPersonajeComic = null;
		rolPersonajeComic = new RolPersonajeComic();
		rolPersonajeComic = entityManager.find(RolPersonajeComic.class, id);
		RolPersonajeComicDTO rolPersonajeComicDTO = convertirEntidadDTO(rolPersonajeComic);
		
		logger.debug("Finaliza el metodo consultar rol personaje comic");
		return rolPersonajeComicDTO;
	}	

	/**
	 *
	 * @description Metodo encargado de mostrar todos los Personaje de un comic
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-07
	 * 
	 * @see com.hbt.semillero.ejb.IGestionalRolPersonajeComicLocal#consultarRolPersonajesComics()
	 *
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RolPersonajeComicDTO> consultarRolPersonajesComics() {
		// TODO Auto-generated method stub
		logger.debug("Se Ejecuta el metodo consultar todos los personajes de un comic");

		/*
		 * Convierte un PersonajeComic a PersonajeComicDTO
		 */
		List<RolPersonajeComicDTO> listarRolPersonajesComicsDTO = new ArrayList<RolPersonajeComicDTO>();

		String query = "SELECT rolpersonajecomic FROM RolPersonajeComic rolpersonajecomic";
		List<RolPersonajeComic> rolPersonajeComics = entityManager.createQuery(query).getResultList();

		for (RolPersonajeComic rolPersonajeComic:rolPersonajeComics) {
			listarRolPersonajesComicsDTO.add(convertirEntidadDTO(rolPersonajeComic));
		}

		logger.debug("Finaliza el metodo consultar todos los personajes de un comic");

		return listarRolPersonajesComicsDTO;
		
	}


	/**
	 * 
	 * @description Metodo encargado de transformar un personajeComic a un
	 *              personajeComicDTO
	 * 
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-07
	 * 
	 * @param PersonajeComicDTO
	 * 
	 * @return
	 */
	// @SuppressWarnings("unused")
	private RolPersonajeComic convertirDTOEntidad(RolPersonajeComicDTO rolPersonajeComicDTO) {

		RolPersonajeComic rolPersonajeComic = new RolPersonajeComic();

		if (rolPersonajeComicDTO.getId() != null) {
			rolPersonajeComic.setId(rolPersonajeComicDTO.getId());
		}

		rolPersonajeComic.setId(rolPersonajeComicDTO.getId());
		rolPersonajeComic.setNombre(rolPersonajeComicDTO.getNombre());
		rolPersonajeComic.setEstado(rolPersonajeComicDTO.getEstado());		
		

		return rolPersonajeComic;
	}

	/**
	 * 
	 * @description Metodo encargado de transformar un personajeComicDTO a un
	 *              rolpersonajeComic
	 * 
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-07
	 * 
	 * @param PersonajeComic
	 * 
	 * @return
	 */
	// @SuppressWarnings("unused")
	private RolPersonajeComicDTO convertirEntidadDTO(RolPersonajeComic rolPersonajeComic) {

		RolPersonajeComicDTO rolPersonajeComicDTO = new RolPersonajeComicDTO();

		if (rolPersonajeComic.getId() != null) {
			rolPersonajeComicDTO.setId(rolPersonajeComic.getId());
		}

		rolPersonajeComicDTO.setId(rolPersonajeComic.getId());
		rolPersonajeComicDTO.setNombre(rolPersonajeComic.getNombre());
		rolPersonajeComicDTO.setEstado(rolPersonajeComic.getEstado());

		return rolPersonajeComicDTO;
	}

}
