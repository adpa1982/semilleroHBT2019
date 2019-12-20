package com.hbt.semillero.ejb;

import java.util.List;

import com.hbt.semillero.dto.VentasDTO;
import com.hbt.semillero.entidad.Comic;

/**
 * @author user
 *
 */
public interface IGestionarVentasLocal {
	/**
	 *
	 * @description Metodo encargado de crear la Personaje
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 *
	 * @param VentasDTO VentasDTO
	 */
	public void crearVenta(VentasDTO VentasDTO, Comic comic);

	/**
	 *
	 * @description Metodo encargado de actualizar la Persona
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 *
	 * @param Long id, String nombre, VentasDTO VentasDTO
	 */
    public void modificarVenta(Long id, String nombre, VentasDTO VentasDTO);

	/**
	 *
	 * @description Metodo encargado de eliminar una Persona
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 *
	 * @param Long id
	 */
    public void eliminarVenta(Long id);

	/**
	 * @return
	 *
	 * Metodo encargado de retornar la informacion de un Persona
	 *
	 *
	 * @return comic Resultado de la consulta @throws
	 */
	public List<VentasDTO> consultarVenta();

	/**
	 *
	* @description Metodo encargado de devolver la informacion de una venta
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-19
	 *
	 * @return
	 */
	public List<VentasDTO> consultarVenta(Long id);
}