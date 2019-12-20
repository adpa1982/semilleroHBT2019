package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hbt.semillero.dto.VentasDTO;
import com.hbt.semillero.ejb.IGestionarVentasLocal;
import com.hbt.semillero.entidad.Comic;

/**
 * 
 * @description Clase que determina el servicio rest que permite gestionar una
 *              Venta
 * 
 * @author Alberto Puche Algarin
 * 
 * @fecha 2019-12-19
 *
 */
@Path("/GestionarVenta")
public class GetionarVentasRest {

	// final static Logger logger = Logger.getLogger(GestionarPersonaBean.class);

	/**
	 * Atriburo que permite gestionar un Venta
	 */
	@EJB
	private IGestionarVentasLocal gestionarVentasEJB;
	
	/**
	 * @description Crea las ventas en el sistema.
	 *              http://localhost:8085/semillero-servicios/rest/GestionarVenta/crear
	 * 
	 * @author Alberto Puche Algarin
	 * 
	 * @fecha 2019-12-19
	 * 
	 * @param personaDTO
	 * 
	 * @return
	 */
	@POST
	@Path("/crear")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response crearVenta(VentasDTO ventasDTO, Comic comic) {
		try {
			gestionarVentasEJB.crearVenta(ventasDTO, comic);
			return Response.status(Response.Status.CREATED)
					       .entity(ventasDTO)
					       .build();
		} catch (Exception e) {			
			return Response.status(Response.Status.BAD_REQUEST)
					       .entity("Se presento fallo en la invocación del servicio al crear la venta " + e)
					       .build();
		}
	}
	
	
	/**
	 * 
	 * @description Metodo encargado de traer la informacion de una venta determinada
	 *              http://localhost:8085/semillero-servicios/rest/GestionarVenta/consultar?id=1
	 * 
	 * @author Alberto Puche Algarin
	 * 
	 * @fecha 2019-12-19
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/consultarId")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VentasDTO> consultarVenta(@QueryParam("id") Long id) {
		List<VentasDTO> ventasDTO = null;
		try {
			if (id != null) {
				ventasDTO = gestionarVentasEJB.consultarVenta(id);
			}
		} catch (Exception e) {
			//logger.error("Se capturo la excepcion y la informacion es:  codigo " + e.getCodigo() + " mensaje:"+ e.getMensaje());
		}
		return ventasDTO;

	}
	
	/**
	 * 
	 * @description Metodo encargado de traer la informacion de las pesonas 
	 *              http://localhost:8085/semillero-servicios/rest/GestionarVenta/consultar
	 * 
	 * @author Alberto Puche Algarin
	 * 
	 * @fecha 2019-12-19
	 * 
	 * @return
	 */
	@GET
	@Path("/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VentasDTO> consultarVenta() {
		List<VentasDTO> ventasDTO = null;
		try {
			ventasDTO = gestionarVentasEJB.consultarVenta();
		} catch (Exception e) {
			//logger.error("Se capturo la excepcion y la informacion es:  codigo " + e.getCodigo() + " mensaje:"+ e.getMensaje());
		}
		return ventasDTO;	
	}

}
