import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { PersonaDTO } from '../../dto/persona.dto';
import { GestionaPersonaService } from '../../services/gestionar-persona.service';

/**
 * @description Componente gestionar data persona
 * @author Alberto David Puche Algarin
 * @fecha 2019-12-20
 */
@Component({
    // tslint:disable-next-line: component-selector
    selector: 'crear-persona',
    templateUrl: './crear-persona-component.html',
    styleUrls: ['./crear-persona-component.css']
})
export class CrearPersonaComponent implements OnInit {

    /**
     * Atributo que contiene los controles del formulario
     */
    // public: FormGroup;
    public gestionarPersonaForm: FormGroup;

    /**
     * Atributo que contendra la informacion de la persona
     */
    public persona: PersonaDTO;

    /**
     * Atributo que contendra la lista de personas creadas
     */
    public listaPersonas: Array<PersonaDTO>;

    /**
     * @description Variable que va a contener el id de la persona
     */
    public id: number;

    /**
     * @description Variable que va a contener el nombre de la personsa
     */
     public nombre: string;

     /**
     * @description Variable que va a contener el tipodocumento de la personsa
     */
    public tipoDocumento: string;

    /**
     * @description Variable que va a contener el numerodocumento de la personsa
     */
    public numeroDocumento: number;

    /**
     * @description Variable que va a contener la fecha de nacimiento de la personsa
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

    /**
     * @description Constructor del componente GestionarPersona
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-20
     */
    constructor(private fb: FormBuilder,
        private gestionarPersonaService: GestionaPersonaService) {
        // console.log('entro al constructor del componente persona');
        this.gestionarPersonaForm = this.fb.group({
            nombre : [null, Validators.required],
            tipoDocumento : [null, Validators.required],
            numeroDocumento : [null, Validators.required],
            fecha : [null, Validators.required],
            id : [null]
        });
    }

    ngOnInit(): void {
        console.log('Ingreso al al evento oninit');
        this.persona = new PersonaDTO();
        this.listaPersonas = new Array<PersonaDTO>();
        this.mensaje = '';
        this.verMensaje = false;
        this.consultarPersonas();
    }

    /**
     * @description Metodo que permite consultar las personas de la tabla por medio de ServiciosRest
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-20
     */
    public consultarPersonas(): void {
        this.gestionarPersonaService.consultar().subscribe(listaPersonas => {
            console.log('consultarPersonas');
            console.dir(listaPersonas);
            this.listaPersonas = listaPersonas;
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

        if (this.gestionarPersonaForm.invalid) {
            return;
        }

        this.persona = new PersonaDTO();
        this.persona.nombre = this.gestionarPersonaForm.controls.nombre.value;
        this.persona.tipoDocumento = this.gestionarPersonaForm.controls.tipoDocumento.value;
        this.persona.numeroDocumento = this.gestionarPersonaForm.controls.numeroDocumento.value;
        this.persona.fecha = this.gestionarPersonaForm.controls.fecha.value;

        this.gestionarPersonaService.crear(this.persona).subscribe(resultado => {
            this.mensaje = resultado.mensajeEjecucion;
            this.consultarPersonas();
            this.limpiarFormulario();
            this.verMensaje = true;
            this.mensaje = 'Se ha creado la persona correctamente.';
            setTimeout (() => {
                this.verMensaje = false;
            }, 2000);
        }, error => {
            console.log('Se ha presentado un error al consumir el servicio de Crear persona(): ' + error);
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
        this.gestionarPersonaForm.controls.nombre.setValue(null);
        this.gestionarPersonaForm.controls.tipoDocumento.setValue(null);
        this.gestionarPersonaForm.controls.numeroDocumento.setValue(null);
        this.gestionarPersonaForm.controls.fecha.setValue(null);
    }


    /**
     * @description Metodo que obtiene los controles y sus propiedades
     *
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-20
     */
    get f() {
        return this.gestionarPersonaForm.controls;
    }

}