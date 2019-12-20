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

import com.hbt.semillero.dto.VentasDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Persona;
import com.hbt.semillero.entidad.Ventas;

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
public class GestionarVentasBean implements IGestionarVentasLocal {

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager entityManger;

	final static Logger logger = Logger.getLogger(GestionarVentasBean.class);

	/**
	 *
	 * @description Metodo encargado de crear una venta y persistirlo
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarVentasLocal#crearVenta(com.hbt.semillero.dto.VentasDTO)
	 *
	 * @param ventas informacion nueva a crear
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearVenta(VentasDTO ventasDTO, Comic comic) {
		logger.debug("Inicia metodo crearVenta");
		try {
			Ventas ventas = convertirDTOEntidad(ventasDTO);
			entityManger.persist(ventas);
			ventas.addComic(comic);
		} catch (Exception e) {
			logger.error("Se presento un error al guardar la venta " + e);
		}

		logger.debug("Se ejecuta el comando");

	}

	/**
	 *
	 * @description Metodo encargado de mostrar todos los Persona
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 * 
	 * @see main.java.com.hbt.semillero.ejb.IGestionarVentasLocal#consultarVenta()
	 *
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<VentasDTO> consultarVenta() {
		logger.debug("Inicia metodo consultarPersona");

		String query = "SELECT persona " + "FROM Persona persona ";

		// Creamos el Objeto de tipo Lista para el DTO de los Personajes
		List<VentasDTO> listaVentaDTO = new ArrayList<VentasDTO>();

		try {
			@SuppressWarnings("unchecked")
			// Asignamos el resultado de la consulta al Entity manager
			List<Ventas> listaVenta = entityManger.createQuery(query).getResultList();

			// Recorremos la lista de personajes Retornados y creamos el la Lista DTO
			for (Ventas ventas : listaVenta) {
				// Convertimos los datos del tipo Entidad a DTO para luego retornarlos
				listaVentaDTO.add(convertirEntidadDTO(ventas));
			}

		} catch (Exception e) {
			logger.error("Se ha presentado un error al Consultar los persona " + e);
		}
		logger.debug("Finaliza metodo consultarPersona");

		return listaVentaDTO;
	}

	/**
	 *
	 * @description Metodo encargado de mostrar la venta por id
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 * 
	 * @see main.java.com.hbt.semillero.ejb.IGestionarVentasLocal#consultarVentas(id)
	 *
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<VentasDTO> consultarVenta(Long id) {
		String query = "SELECT venta " + "FROM Ventas ventas " + "WHERE ventas.id = :id";

		List<VentasDTO> listaVentaDTO = new ArrayList<VentasDTO>();

		try {
			@SuppressWarnings("unchecked")
			List<Ventas> listaVenta = entityManger.createQuery(query).setParameter("idVenta", id).getResultList();

			for (Ventas ventas : listaVenta) {
				listaVentaDTO.add(convertirEntidadDTO(ventas));
			}
		} catch (Exception e) {
			logger.error("Error al consultar persona por id " + e);
		}

		logger.debug("Finaliza metodo consultarPersona");

		return listaVentaDTO;
	}

	/**
	 * @description Metodo encargado de transformar una Venta a un VentasDTO
	 * 
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 * 
	 * @param VentasDTO
	 * 
	 * @return
	 */
	private Ventas convertirDTOEntidad(VentasDTO ventasDTO) {

		Ventas ventas = new Ventas();

		if (ventasDTO.getId() != null) {
			ventas.setId(ventasDTO.getId());
		}

		ventas.setPersona(new Persona());
		ventas.getPersona().setId(ventasDTO.getPersona().getId());
		// ventas.getPersona().setId(ventasDTO.getPersona());

		ventas.setFechaVenta(ventasDTO.getFechaVenta());

		return ventas;
	}

	/**
	 * 
	 * @description Metodo encargado de transformar un VentasDTO a un Ventas
	 * 
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 * 
	 * @param Ventas
	 * 
	 * @return
	 */
	private VentasDTO convertirEntidadDTO(Ventas ventas) {

		VentasDTO ventasDTO = new VentasDTO();

		if (ventas.getId() != null) {
			ventasDTO.setId(ventas.getId());
		}

		ventasDTO.setPersona(ventas.getPersona());
		ventasDTO.setFechaVenta(ventas.getFechaVenta());

		return ventasDTO;
	}

	@Override
	public void modificarVenta(Long id, String nombre, VentasDTO VentasDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarVenta(Long id) {
		// TODO Auto-generated method stub

	}

}
