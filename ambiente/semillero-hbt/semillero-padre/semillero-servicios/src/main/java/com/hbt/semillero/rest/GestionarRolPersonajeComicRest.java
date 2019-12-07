package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.hbt.semillero.dto.RolPersonajeComicDTO;
import com.hbt.semillero.ejb.IGestionalRolPersonajeComicLocal;

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
	/*
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 */
	public void crearPersonajeComic(RolPersonajeComicDTO rolPersonajeComicDTO) {
		gestionarRolPersonajeComicEJB.crearRolPersonajeComic(rolPersonajeComicDTO);
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
	// @Produces(MediaType.APPLICATION_JSON)
	public List<RolPersonajeComicDTO> consultarRolPersonajesComics() {
		return gestionarRolPersonajeComicEJB.consultarRolPersonajesComics();
	}


}
