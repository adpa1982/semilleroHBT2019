package com.hbt.semillero.interfaces;

import java.math.BigDecimal;

import com.hbt.semillero.dto.RolPersonajeComicDTO;
import com.hbt.semillero.entidad.TematicaEnum;

/**
 * 
 * @description Interface que pemite implementar los metods para realizar
 *              calculos determinados
 * 
 * @author Alberto Puche Algarin
 * 
 * @fecha 2019-12-12
 *
 */
public interface Utils {

	/**
	 *
	 * @description Metodo encargado de calcular el iva de una comic dependiendo de su tematica
	 *
	 * @author Alberto Puche Algarin
	 *
	 * @fecha 2019-12-12
	 *
	 * @param tematicaEnum informacion de la tematica de un comic
	 */
	public float calcularIva(String tematicaEnum);

	/**
	 *
	 * @description Metodo encargado de calcular rl precio total de un comic por su precio e iva
	 *
	 * @author Alberto Puche Algarin
	 * 
	 * @fecha 2019-12-12
	 *
	 * @param BigDecimal precio del comic
	 * @param float iva del comic
	 */
	public BigDecimal calcularPercioTotal(BigDecimal precio, float iva);

}
