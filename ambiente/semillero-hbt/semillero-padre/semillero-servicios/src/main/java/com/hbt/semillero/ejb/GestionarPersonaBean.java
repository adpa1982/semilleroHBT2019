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

import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.entidad.Persona;

/**
 * 
 * @description Clase que determina el bean para realizar las gestion del
 *              personaje de un comics
 * 
 * @author Alberto Puche Algarin
 * 
 * @fecha 2019-12-19
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonaBean implements IGestionarPersonaLocal {

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager entityManger;

	final static Logger logger = Logger.getLogger(GestionarComicBean.class);

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearPersona(PersonaDTO personaDTO) {
		logger.debug("Inicia metodo crearPersonaje");
		try {
			Persona persona = convertirDTOEntidad(personaDTO);
			entityManger.persist(persona);
		} catch (Exception e) {
			logger.error("Se presento un error al guardar los personajes " + e);
		}

		logger.debug("Se ejecuta el comando");

	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonaDTO> consultarPersona() {
		logger.debug("Inicia metodo consultarPersona");

		String query = "SELECT persona " + "FROM Persona persona ";

		// Creamos el Objeto de tipo Lista para el DTO de los Personajes
		List<PersonaDTO> listaPersonaDTO = new ArrayList<PersonaDTO>();

		try {
			@SuppressWarnings("unchecked")
			// Asignamos el resultado de la consulta al Entity manager
			List<Persona> listaPersona = entityManger.createQuery(query).getResultList();

			// Recorremos la lista de personajes Retornados y creamos el la Lista DTO
			for (Persona persona : listaPersona) {
				// Convertimos los datos del tipo Entidad a DTO para luego retornarlos
				listaPersonaDTO.add(convertirEntidadDTO(persona));
			}

		} catch (Exception e) {
			logger.error("Se ha presentado un error al Consultar los persona " + e);
		}
		logger.debug("Finaliza metodo consultarPersona");

		return listaPersonaDTO;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonaDTO> consultarPersona(Long idPersona) {
		String query = "SELECT persona " + "FROM Persona persona " + "WHERE personaje.id = :idPersona";

		List<PersonaDTO> listaPersonaDTO = new ArrayList<PersonaDTO>();

		try {
			@SuppressWarnings("unchecked")
			List<Persona> listaPersona = entityManger.createQuery(query).setParameter("idPersona", idPersona)
					.getResultList();

			for (Persona persona : listaPersona) {
				listaPersonaDTO.add(convertirEntidadDTO(persona));
			}
		} catch (Exception e) {
			logger.error("Error al consultar persona por id " + e);
		}

		logger.debug("Finaliza metodo consultarPersona");

		return listaPersonaDTO;
	}

	/**
	 * @description Metodo encargado de transformar una Venta a un PersonaDTO
	 * 
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 * 
	 * @param PersonaDTO
	 * 
	 * @return
	 */
	private Persona convertirDTOEntidad(PersonaDTO personaDTO) {

		Persona persona = new Persona();

		if (personaDTO.getId() != null) {
			persona.setId(personaDTO.getId());
		}

		persona.setNombre(personaDTO.getNombre());
		persona.setNumeroDocumento(personaDTO.getNumeroDocumento());
		persona.setTipoDocumento(personaDTO.getTipoDocumento());
		persona.setFecha(personaDTO.getFecha());

		return persona;
	}

	/**
	 * 
	 * @description Metodo encargado de transformar un VentasDTO a un Persona
	 * 
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 * 
	 * @param Persona
	 * 
	 * @return
	 */
	private PersonaDTO convertirEntidadDTO(Persona persona) {

		PersonaDTO personajeDTO = new PersonaDTO();

		if (persona.getId() != null) {
			personajeDTO.setId(persona.getId());
		}

		personajeDTO.setNombre(persona.getNombre());
		personajeDTO.setNumeroDocumento(persona.getNumeroDocumento());

		personajeDTO.setTipoDocumento(persona.getTipoDocumento());
		personajeDTO.setFecha(persona.getFecha());

		return personajeDTO;
	}

	/**
	 *
	 * @description Metodo encargado de modificar el Persona y
	 *              persistirlo
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-05
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarVentasLocal#modificarVenta(com.hbt.semillero.dto.VentasDTO)
	 *
	 * @param id, nombre , personajeComicNuevo informacion para modificar
	 */
	@Override
	public void modificarPersona(Long id, String nombre, PersonaDTO personaDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPersona(Long id) {
		// TODO Auto-generated method stub
		
	}
}
