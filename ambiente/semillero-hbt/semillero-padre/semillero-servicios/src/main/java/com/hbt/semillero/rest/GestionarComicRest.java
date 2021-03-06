/**
 * GestionarComicRest.java
 */
package com.hbt.semillero.rest;

import java.util.List;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.GestionarComicBean;
import com.hbt.semillero.ejb.IGestionarComicLocal;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.exceptions.ComicException;

/**
 * <b>Descripción:<b> Clase que determina el servicio rest que permite gestionar
 * un comic
 * 
 * @author ccastano
 * @version
 */
@Path("/GestionarComic")
public class GestionarComicRest {

	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	// final static Logger logger = Logger.getLogger(GestionarComicBean.class);

	/**
	 * Atriburo que permite gestionar un comic
	 */
	@EJB
	private IGestionarComicLocal gestionarComicEJB;

	/**
	 * 
	 * Metodo encargado de traer la informacion de un comic determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/saludo
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/saludo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response primerRest() {
		try {
			return Response.status(Response.Status.OK).entity("Prueba inicial servicios rest en el semillero java hbt")
					.type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Se presento fallo en la invocación del sercicio " + e).build();
		}
		// return "Prueba inicial servicios rest en el semillero java hbt";
	}

	/**
	 * 
	 * Metodo encargado de traer la informacion de un comic determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/consultarComics
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/consultarComics")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComicDTO> consultarComic() {
		return gestionarComicEJB.consultarComics();

	}

	/**
	 * 
	 * Metodo encargado de traer la informacion de un comic determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/consultarComic?idComic=1
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/consultarComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ComicDTO consultarComic(@QueryParam("id") Long id) {
		ComicDTO comicDTO = null;
		try {
			if (id != null) {
				comicDTO = gestionarComicEJB.consultarComic(id);
			}
		} catch (ComicException e) {
			logger.error("Se capturo la excepcion y la informacion es:  codigo " + e.getCodigo() + " mensaje:"
					+ e.getMensaje());
		}
		return comicDTO;

	}

	/**
	 * Crea las personas en sus diferentes roles dentro del sistema.
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/crear
	 * 
	 * @param persona
	 * @return
	 */
	@POST
	@Path("/crear")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	// public ResultadoDTO crearComic(ComicDTO comicDTO) {
	public Response crearComic(ComicDTO comicDTO) {
		try {
			gestionarComicEJB.crearComic(comicDTO);
			ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Comic creado exitosamente");
			return Response.status(Response.Status.CREATED).entity(resultadoDTO).build();
		} catch (ComicException e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Se presento fallo en la invocación del sercicio " + e).build();
		}
	}

	/**
	 * 
	 * Metodo encargado de modificar el nombre de un comic
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/modificar?id=1&nombre=nuevonombre
	 * 
	 * @param idComic identificador del comic a buscar
	 * @param nombre  nombre nuevo del comic
	 */
	@POST
	@Path("/modificar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modificarComic(@QueryParam("id") Long id, @QueryParam("nombre") String nombre, ComicDTO comicDTO) {
		// public Response modificarComic(ComicDTO comicDTO) {
		try {
			gestionarComicEJB.modificarComic(id, nombre, null);
			return Response.status(Response.Status.OK).entity(comicDTO).build();
		} catch (ComicException e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Se presento fallo en la invocación del sercicio modificar " + e).build();
		}
	}

	/**
	 * 
	 * Metodo encargado de eliminar un comic dado el id
	 * http://localhost:8085/semillero-servicios/rest/GestionarComic/eliminar?id=1
	 * 
	 * @param idComic identificador del comic
	 */
	@POST
	@Path("/eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarComic(@QueryParam("id") Long id) {
		try {
			if (id != null) {
				gestionarComicEJB.eliminarComic(id);
				return Response.status(Response.Status.OK).entity("Comic eliminado con id " + id).build();
			} else {
				return Response.status(Response.Status.OK).entity("No existe el id del comic a eliminar").build();
			}
		} catch (ComicException e) {
			logger.error("Se capturo la excepcion y la informacion es:  codigo " + e.getCodigo() + " mensaje:"
					+ e.getMensaje());
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Se presento fallo en la invocación del sercicio modificar " + e).build();
		}

	}
}
