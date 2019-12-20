import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

import { VentaDTO } from '../../dto/venta.dto';
import { GestionaVentaService } from '../../services/gestionar-venta.service';


import { PersonaDTO } from '../../dto/persona.dto';
import { ComicDTO } from '../../dto/comic.dto';
import { GestionaPersonaService } from '../../services/gestionar-persona.service';
import { GestionarComicsService } from '../../services/gestionar-comics.service';

@Component({
  // tslint:disable-next-line: component-selector
  selector: 'crear-venta',
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css']
})
export class VentaComponent implements OnInit {

  /**
     * Atributo que contiene los controles del formulario
     */
    public gestionarVentaForm: FormGroup;

    /**
     * Atributo que contendra la informacion de la persona
     */
    public venta: VentaDTO;

    /**
     * Atributo que contendra la lista de personas creadas
     */
    public listaVentas: Array<VentaDTO>;

    /**
     * @description Variable que va a contener el id de la venta
     */
    public id: number;

    /**
     * @description Variable que va a contener el id de la personsa
     */
    public personaid: number;

    /**
     * @description Variable que va a contener el id del comic
     */
    public comicid: number;

    /**
     * @description Variable que va a contener el id del comic
     */
    public cantidad: number;

    /**
     * @description Variable que va a contener la fecha de venta
     */
    public fecha: Date;

    /**
     * Atributo que indica si se envio a validar el formulario
     */
    public submitted: boolean;

    /**
     * @description Variable que va a contener el mensaje
     */
    public mensaje: string;

    /**
     * @description Variable que va a verificar el estado del div a mostrar
     */
    public verMensaje: boolean;

    public personadto: PersonaDTO;
    public listaPersonas: Array<PersonaDTO>;
    public comicdto: ComicDTO;
    public listaComics: Array<ComicDTO>;

  constructor(private fb: FormBuilder,
    private gestionarVentaService: GestionaVentaService,
    private gestionarPersonaService: GestionaPersonaService,
    private gestionarComicService: GestionarComicsService) {
      this.gestionarVentaForm = this.fb.group({
        personaid: [null, Validators.required],
        comicid: [null, Validators.required],
        fecha: [null, Validators.required],
        cantidad: [null, Validators.required],
        id : [null]
    });
    }

  ngOnInit(): void {
    console.log('Ingreso al al evento oninit');
        this.venta = new VentaDTO();
        this.listaVentas = new Array<VentaDTO>();
        this.mensaje = '';
        this.verMensaje = false;
        this.listaPersonas = new Array<PersonaDTO>();
        this.listaComics = new Array<ComicDTO>();
        this.fecha = new Date();
  }

  /**
   * @description Metodo que permite consultar las personas de la tabla por medio de ServiciosRest
   * @author Alberto David Puche Algarin
   * @fecha 2019-12-20
   */
  public consultarPersonas(): void {
    this.gestionarPersonaService.consultar().subscribe(listaPersonas => {
        console.dir(listaPersonas);
        this.listaPersonas = listaPersonas;
    }, error => {
        console.log('Se ha presentado un error al consumir el servicio de consultarComics(): ' + error);
    });
  }

  /**
   * @description Metodo que permite consultar los comics de la tabla por medio de ServiciosRest
   * @author Alberto David Puche Algarin
   * @fecha 2019-12-20
   */
  public consultarComics(): void {
    this.gestionarComicService.consultarComics().subscribe(listaComics => {
        console.log('consultarPersonas');
        this.listaComics = listaComics;
    }, error => {
        console.log('Se ha presentado un error al consumir el servicio de consultarComics(): ' + error);
    });
  }

  /**
     * @description Metodo que permite validar el formulario y crear una persona por medio de ServiciosRest
     *
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-20
     */
    public crearActualizarPersonaRest(): void {
      this.submitted = true;

      if (this.gestionarVentaForm.invalid) {
          return;
      }

      this.venta = new VentaDTO();
      this.venta.personaid = this.gestionarVentaForm.controls.personaid.value;
      this.venta.comicid = this.gestionarVentaForm.controls.comicid.value;
      this.venta.cantidad = this.gestionarVentaForm.controls.cantidad.value;
      this.venta.fechaVenta = this.gestionarVentaForm.controls.fecha.value;

      this.gestionarVentaService.crear(this.venta).subscribe(resultado => {
          this.mensaje = resultado.mensajeEjecucion;
          this.limpiarFormulario();
          this.verMensaje = true;
          this.mensaje = 'Se ha creado la venta correctamente.';
          setTimeout (() => {
              this.verMensaje = false;
          }, 2000);
      }, error => {
          console.log('Se ha presentado un error al consumir el servicio de Crear la venta (): ' + error);
          console.dir(error);
      });
  }

  /**
   * @description Metodo que permite limpiar un formulario
   *
   * @author Alberto David Puche Algarin
   * @fecha 2019-12-20
   */
  private limpiarFormulario(): void {
      this.submitted = false;
      this.gestionarVentaForm.controls.personaid.setValue(null);
      this.gestionarVentaForm.controls.comicid.setValue(null);
      this.gestionarVentaForm.controls.cantidad.setValue(null);
      this.gestionarVentaForm.controls.fecha.setValue(null);
  }


  /**
   * @description Metodo que obtiene los controles y sus propiedades
   *
   * @author Alberto David Puche Algarin
   * @fecha 2019-12-20
   */
  get f() {
      return this.gestionarVentaForm.controls;
  }



}
