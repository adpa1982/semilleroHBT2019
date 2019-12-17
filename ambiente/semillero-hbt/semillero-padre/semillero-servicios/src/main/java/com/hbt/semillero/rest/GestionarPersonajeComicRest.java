/**
 * 
 */
package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.hbt.semillero.dto.PersonajeComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.IGestionarPersonajeComicLocal;

/**
 * 
 * @description Clase que determina el servicio rest que permite gestionar un
 *              PersonajeComic
 * 
 * @author Puche_Algarin
 * 
 * @fecha 2019-12-07
 *
 */
@Path("/GestionarPersonajeComic")
public class GestionarPersonajeComicRest {

	/**
	 * Atriburo que permite gestionar un PersonajeComic
	 */
	@EJB
	private IGestionarPersonajeComicLocal gestionarPersonajeComicEJB;

	/**
	 * @description Crea las personas en sus diferentes roles dentro del sistema.
	 *              http://localhost:8085/semillero-servicios/rest/GestionarPersonajeComic/crear
	 * 
	 * @author Puche_Algarin
	 * 
	 * @fecha 2019-12-07
	 * 
	 * @param personajeComic
	 * 
	 * @return
	 */
	@POST
	@Path("/crear")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	//public void crearPersonajeComic(PersonajeComicDTO personajeComicDTO) {
	public Response crearPersonajeComic(PersonajeComicDTO personajeComicDTO) {
		
		try {
			gestionarPersonajeComicEJB.crearPersonajeComic(personajeComicDTO);
			//ResultadoDTO resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Personaje del Comic creado exitosamente");
			return Response.status(Response.Status.CREATED)
					       .entity(personajeComicDTO)
					       .build();
		} catch (Exception e) {			
			return Response.status(Response.Status.BAD_REQUEST)
					       .entity("Se presento fallo en la invocación del sercicio " + e)
					       .build();
		}
		
		//gestionarPersonajeComicEJB.crearPersonajeComic(personajeComicDTO);
	}

	/**
	 * 
	 * @description Metodo encargado de consultar los personajes de comic
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonajeComic/consultar
	 * 
	 * @author Puche_Algarin
	 * 
	 * @fecha 2019-12-07
	 * 
	 * @return
	 */
	@GET
	@Path("/consultar")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public List<PersonajeComicDTO> consultarPersonajesComics() {
		return gestionarPersonajeComicEJB.consultarPersonajesComics();
	}

	/**
	 * 
	 * @description Metodo encargado de consultar los personajes de comic
	 *              http://localhost:8085/semillero-servicios/rest/GestionarPersonajeComic/consultar/comic?idComic=1
	 * 
	 * @author Puche_Algarin
	 * 
	 * @fecha 2019-12-07
	 * 
	 * @return
	 */
	@GET
	@Path("/consultar/comic")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public List<PersonajeComicDTO> consultarPersonajesComics(@QueryParam("idComic") Long idComic) {
		return gestionarPersonajeComicEJB.consultarPersonajesComics(idComic);
	}
 

}
