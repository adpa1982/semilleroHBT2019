package com.hbt.semillero.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeComicDTO;
import com.hbt.semillero.dto.RolPersonajeComicDTO;
import com.hbt.semillero.ejb.IGestionalRolPersonajeComicLocal;
import com.hbt.semillero.entidad.PersonajeComic;

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
@Path("/GestionarRolPersonajeComic")
public class GestionarRolPersonajeComicRest {
	
	
	/**
	 *  Atriburo que permite gestionar un PersonajeComic
	 */
	@EJB
	private IGestionalRolPersonajeComicLocal gestionarRolPersonajeComicEJB;

	/**
	 * @description Crea las personas en sus diferentes roles dentro del sistema.
	 *              http://localhost:8085/semillero-servicios/rest/GestionarRolPersonajeComic/crear
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
	public void crearPersonajeComic(RolPersonajeComicDTO rolPersonajeComicDTO) {
		gestionarRolPersonajeComicEJB.crearRolPersonajeComic(rolPersonajeComicDTO);
	}
	
	/**
	 * 
	 * Metodo encargado de traer la informacion de un rolpersonajecomic determiando
	 * http://localhost:8085/semillero-servicios/rest/GestionarRolPersonajeComic/consultarrol?id=1
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/consultarrol")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public RolPersonajeComicDTO consultarRolPersonajeComic(@QueryParam("id") Long id) {
		if (id != null) {
			RolPersonajeComicDTO rolPersonajeComicDTO = gestionarRolPersonajeComicEJB.consultarRolPersonajeComic(id);
			return rolPersonajeComicDTO;
		}
		return null;
	}

	/**
	 * 
	 * @description Metodo encargado de consultar los personajes de comic
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
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public List<RolPersonajeComicDTO> consultarRolPersonajesComics() {
		return gestionarRolPersonajeComicEJB.consultarRolPersonajesComics();
	}


}
