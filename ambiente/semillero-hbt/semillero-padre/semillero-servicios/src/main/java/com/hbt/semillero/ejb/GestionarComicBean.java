/**
 * GestionarComicBean.java
 */
package com.hbt.semillero.ejb;

import java.math.BigDecimal;
import java.math.BigInteger;
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
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.TematicaEnum;
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
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#crearComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearComic(ComicDTO comicNuevo) {
		// Entidad nueva
		Comic comic = convertirComicDTOToComic(comicNuevo);
		// Se almacena la informacion y se maneja la enidad comic
		em.persist(comic);
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#modificarComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarComic(Long id, String nombre, ComicDTO comicNuevo) {
		Comic comicModificar ;
		if(comicNuevo==null) {
			// Entidad a modificar
			comicModificar = em.find(Comic.class, id);
		}else {
			comicModificar = convertirComicDTOToComic(comicNuevo);
		}
		comicModificar.setNombre(nombre);
		em.merge(comicModificar);
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#eliminarComic(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarComic(Long idComic) {
		Comic comicEliminar = em.find(Comic.class, idComic);
		if (comicEliminar != null) {
			em.remove(comicEliminar);
		}
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComic(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ComicDTO consultarComic(String idComic) {
		Comic comic = null;
		comic = new Comic();
		comic = em.find(Comic.class, Long.parseLong(idComic));
		ComicDTO comicDTO = convertirComicToComicDTO(comic);
		return comicDTO;
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComics()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> consultarComics() {
		
		logger.debug("Se Ejecuta el comando");
		
		List<ComicDTO> resultadosComicDTO = new ArrayList<ComicDTO>();
		List<Comic> resultados = em.createQuery("select c from Comic c").getResultList();
		for (Comic comic:resultados) {
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
		if(comic.getId()!=null) {
		 comicDTO.setId(comic.getId().toString());
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
		comicDTO.setIva(this.calcularIva(comic.getTematicaEnum().toString() ));
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
		if(comicDTO.getId()!=null) {
			comic.setId(Long.parseLong(comicDTO.getId()));
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

		if ( tematicaEnum.equals("AVENTURAS") || tematicaEnum.equals("FANTASTICO") || tematicaEnum.equals("HISTORICO")  ) {
			iva = (float) 5;
		}
		
		if (tematicaEnum.equals("DEPORTIVO")) {
			iva = (float) 10;
		}
		
		if (tematicaEnum.equals("BELICO") || tematicaEnum.equals("CIENCIA_FICCION") || tematicaEnum.equals("HORROR") ) {
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
	 * @param iva Iva del comic
	 * 
	 * @return valorTotal
	 */
	@Override
	public BigDecimal calcularPercioTotal(BigDecimal precio, float iva) {
		// TODO Auto-generated method stub
		BigDecimal valorTotal; 
		float ivaT; 
		ivaT = (iva/100);
		valorTotal = precio.multiply(new BigDecimal(ivaT));
		valorTotal = valorTotal.add(precio);

		return valorTotal;
	}
}
