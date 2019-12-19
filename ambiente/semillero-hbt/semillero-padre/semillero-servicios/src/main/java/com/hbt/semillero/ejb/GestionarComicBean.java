/**
 * GestionarComicBean.java
 */
package com.hbt.semillero.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.exceptions.ComicException;
import com.hbt.semillero.interfaces.Utils;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los comics
 * 
 * @author ccastano
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarComicBean implements IGestionarComicLocal, Utils {

	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * 
	 * @throws ComicException
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#crearComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ComicDTO crearComic(ComicDTO comicNuevo) throws ComicException {
		try {
			// Entidad nueva
			Comic comic = convertirComicDTOToComic(comicNuevo);
			// Se almacena la informacion y se maneja la enidad comic
			em.persist(comic);
			// em.flush();
			return convertirComicToComicDTO(comic);
		} catch (Exception e) {
			logger.error("Error al eliminar el comic " + e);
			throw new ComicException("COD-0001", "Error ejecuntando la eliminacion del comic", e);
		}
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#modificarComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarComic(Long id, String nombre, ComicDTO comicDTO) throws ComicException {
		// public ComicDTO modificarComic(ComicDTO comicDTO) throws ComicException {
		logger.info("INICIO EJECUTANDO METODO modificarComic " + id + "|" + nombre + "|" + comicDTO);
		try {
			Comic comicModificar;
			if (comicDTO == null) {
				// Entidad a modificar
				comicModificar = em.find(Comic.class, id);
			} else {
				comicModificar = convertirComicDTOToComic(comicDTO);
			}

			comicModificar.setNombre(nombre);
			em.merge(comicModificar);

		} catch (Exception e) {
			logger.error("Error al modificar el comic " + e);
			throw new ComicException("COD-0002", "Error ejecuntando la actualizacion del comic", e);
		}
		logger.info("FIN EJECUTANDO METODO modificarComic");

	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#eliminarComic(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarComic(Long id) throws ComicException {
		logger.debug("INICIO EJECUTANDO METODO eliminarComic " + id);
		try {
			Comic comicEliminar = em.find(Comic.class, id);
			if (comicEliminar != null) {
				em.remove(comicEliminar);
				em.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error al eliminar el comic " + e);
			throw new ComicException("COD-0001", "Error ejecutando la eliminacion del comic", e);
		}
		logger.debug("FIN EJECUTANDO METODO eliminarComic");
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComic(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ComicDTO consultarComic(Long idComic) throws ComicException {

		try {
			Comic comic = null;
			comic = new Comic();
			comic = em.find(Comic.class, idComic);
			ComicDTO comicDTO = convertirComicToComicDTO(comic);
			return comicDTO;
		} catch (NumberFormatException e) {
			logger.error("Error convirtiendo la cadena a numero " + idComic);
			throw new ComicException("COD-0002", "No se pudo convertir la cadena", e);
		}

	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComics()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> consultarComics() {

		logger.debug("Se Ejecuta el comando");

		List<ComicDTO> resultadosComicDTO = new ArrayList<ComicDTO>();
		List<Comic> resultados = em.createQuery("select c from Comic c order by c.id").getResultList();
		for (Comic comic : resultados) {
			resultadosComicDTO.add(convertirComicToComicDTO(comic));
		}
		return resultadosComicDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
	 */
	private ComicDTO convertirComicToComicDTO(Comic comic) {
		ComicDTO comicDTO = new ComicDTO();
		if (comic.getId() != null) {
			comicDTO.setId(comic.getId());
		}
		comicDTO.setNombre(comic.getNombre());
		comicDTO.setEditorial(comic.getEditorial());
		comicDTO.setTematicaEnum(comic.getTematicaEnum());
		comicDTO.setColeccion(comic.getColeccion());
		comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
		comicDTO.setPrecio(comic.getPrecio());
		comicDTO.setAutores(comic.getAutores());
		comicDTO.setColor(comic.getColor());
		comicDTO.setFechaVenta(comic.getFechaVenta());
		comicDTO.setEstadoEnum(comic.getEstadoEnum());
		comicDTO.setCantidad(comic.getCantidad());

		// Calculo del iva del comic dependiendo de su tematica
		comicDTO.setIva(this.calcularIva(comic.getTematicaEnum().toString()));
		// Calculo del precio total de un comic por su precio e iva
		comicDTO.setPrecioTotal(this.calcularPercioTotal(comic.getPrecio(), comicDTO.getIva()));

		return comicDTO;
	}

	/**
	 * 
	 * @Descripción Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
	 */
	private Comic convertirComicDTOToComic(ComicDTO comicDTO) {
		Comic comic = new Comic();
		if (comicDTO.getId() != null) {
			comic.setId(comicDTO.getId());
		}
		comic.setNombre(comicDTO.getNombre());
		comic.setEditorial(comicDTO.getEditorial());
		comic.setTematicaEnum(comicDTO.getTematicaEnum());
		comic.setColeccion(comicDTO.getColeccion());
		comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
		comic.setPrecio(comicDTO.getPrecio());
		comic.setAutores(comicDTO.getAutores());
		comic.setColor(comicDTO.getColor());
		comic.setFechaVenta(comicDTO.getFechaVenta());
		comic.setEstadoEnum(comicDTO.getEstadoEnum());
		comic.setCantidad(comicDTO.getCantidad());
		return comic;
	}

	/**
	 * 
	 * @Descripción Metodo calcular el iva de un comic dependiendo de su tematica
	 * 
	 * @author Alberto Puche Algarin
	 * 
	 * @fecha 2019-12-12
	 * 
	 * @param tematicaEnum Temetica del comic
	 * @return iva
	 */
	@Override
	public float calcularIva(String tematicaEnum) {
		// Variable que llevara el iva del comic y sera retornada por el metodo
		float iva = 0;

		if (tematicaEnum.equals("AVENTURAS") || tematicaEnum.equals("FANTASTICO") || tematicaEnum.equals("HISTORICO")) {
			iva = (float) 5;
		}

		if (tematicaEnum.equals("DEPORTIVO")) {
			iva = (float) 10;
		}

		if (tematicaEnum.equals("BELICO") || tematicaEnum.equals("CIENCIA_FICCION") || tematicaEnum.equals("HORROR")) {
			iva = (float) 16;
		}

		return iva;
	}

	/**
	 * 
	 * @Descripción Metodo calcular el iva de un comic dependiendo de su tematica
	 * 
	 * @author Alberto Puche Algarin
	 * 
	 * @fecha 2019-12-12
	 * 
	 * @param precio Precio del comic
	 * @param iva    Iva del comic
	 * 
	 * @return valorTotal
	 */
	@Override
	public BigDecimal calcularPercioTotal(BigDecimal precio, float iva) {
		// TODO Auto-generated method stub
		BigDecimal valorTotal;
		float ivaT;
		ivaT = (iva / 100);
		valorTotal = precio.multiply(new BigDecimal(ivaT));
		valorTotal = valorTotal.add(precio);

		return valorTotal;
	}
}
