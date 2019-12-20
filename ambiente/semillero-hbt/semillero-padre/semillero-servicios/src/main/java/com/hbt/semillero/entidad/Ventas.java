package com.hbt.semillero.entidad;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @description Clase que determina la entidad que permite representar una Venta
 * 
 * @author Alberto Puche Algarin
 * 
 * @fecha 2019-12-19
 *
 */
@Entity
@Table(name = "VENTAS")
public class Ventas implements Serializable {

	/**
	 * Serializar es pasar un Objeto a un array de bytes y viceversa. Atributo que
	 * determina serialVersionUID es el id único que identifica una clase cuando lo
	 * serializamos. ;ediante este id podemos identificar el objeto convertido en un
	 * array de bytes.
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Persona persona;
	private LocalDateTime fechaVenta;
	
	/**
	 * 
	 * 
	 * @param 
	 */
	@JoinTable(name = "VENTAS_COMIC", joinColumns = @JoinColumn(name = "FK_VENTA", nullable = false), inverseJoinColumns = @JoinColumn(name = "FK_COMIC", nullable = false))
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Comic> comics;

	/**
	 * 
	 * 
	 * @param
	 */
	public void addComic(Comic comic) {
		if (this.comics == null) {
			this.comics = new ArrayList<>();
		}
		this.comics.add(comic);
	}

	/**
	 * Metodo encargado de retornar el valor del atributo id
	 * 
	 * @return El id asociado a la clase
	 */
	@Id
	@SequenceGenerator(allocationSize = 1, name = "VENTAS_SCID_GENERATOR", sequenceName = "SEQ_VENTAS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VENTAS_SCID_GENERATOR")
	@Column(name = "VEN_ID")
	public Long getId() {
		return id;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo id
	 * 
	 * @param id El nuevo id a modificar.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @description Metodo encargado de retornar el valor del atributo persona
	 * 
	 * @return La persona asociado a la clase
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VEN_IDPERSONA", nullable = false)
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @description Metodo encargado de retornar el valor del atributo comic
	 * 
	 * @return El comic asociado a la clase
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo fecha
	 * 
	 * @return La fecha asociado a la clase
	 */
	@Column(name = "VEN_FECVENTA")
	public LocalDateTime getFechaVenta() {
		return fechaVenta;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo fecha
	 * 
	 * @param fecha La nueva fecha a modificar.
	 */
	public void setFechaVenta(LocalDateTime fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

}
