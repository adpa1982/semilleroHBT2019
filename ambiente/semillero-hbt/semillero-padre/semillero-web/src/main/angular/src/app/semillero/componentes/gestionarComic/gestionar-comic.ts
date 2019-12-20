import { ComicDTO } from '../../dto/comic.dto';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GestionarComicsService } from '../../services/gestionar-comics.service';

/**
 * @description Componenete gestionar comic, el cual contiene la logica CRUD
 *
 * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
 */
@Component({
    // tslint:disable-next-line: component-selector
    selector: 'gestionar-comic',
    templateUrl: './gestionar-comic.html',
    styleUrls: ['./gestionar-comic.css']
})
export class GestionarComicComponent implements OnInit {

    /**
     * Atributo que contiene los controles del formulario
     */
    // public: FormGroup;
    public gestionarComicForm: FormGroup;

    /**
     * Atributo que contendra la informacion del comic
     */
    public comic: ComicDTO;

    /**
     * Atributo que contendra la lista de comics creados
     */
    public listaComics: Array<ComicDTO>;

    public idComic: number;

    /**
     * Atributo que indica si se envio a validar el formulario
     */
    public submitted: boolean;

    /**
     * @description Variable que va a contener el mensaje del dato eliminado
     */
    public mensaje: string;
    /**
     * @description Variable que va a verificar el estado del div a mostrar
     */
    public verMensaje: boolean;

    /**
     * @description Variable que va a verificar el estado del div a mostrar
     */
    public msjId: boolean;
    /**
     * @description Variable que va a contener el mensaje del dato actualizado
     */
    public mensajeId: string;

    /**
     * @description Este es el constructor del componente GestionarComicComponent
     * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
     */
    constructor(private fb: FormBuilder,
        private router: Router,
        private gestionarComicService: GestionarComicsService) {
        this.gestionarComicForm = this.fb.group({
            nombre : [null, Validators.required],
            editorial : [null],
            tematica : [null],
            coleccion : [null],
            numeroPaginas : [null],
            precio : [null],
            autores : [null],
            color : [null],
            cantidad : [null],
            id : [null]
        });
    }

    /**
     * @description Evento angular que se ejecuta al invocar el componente
     * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
     */
    ngOnInit(): void {
        console.log('Ingreso al al evento oninit');
        this.comic = new ComicDTO();
        this.listaComics = new Array<ComicDTO>();

        this.mensaje = '';
        this.verMensaje = false;
        this.msjId = false;
        this.mensajeId = '';

        this.consultarComics();
    }

    /**
     * @description Metodo que permite consultar los comics de la tabla por medio de ServiciosRest
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-18
     * @param posicion en la lista del comic seleccionado
     */
    public consultarComics(): void {
        this.gestionarComicService.consultarComics().subscribe(listaComics => {
            this.listaComics = listaComics;
        }, error => {
            console.log('Se ha presentado un error al consumir el servicio de consultarComics(): ' + error);
        });
    }

    /**
     * @description Metodo que permite validar el formulario y crear o actulizar un comic por medio de ServiciosRest
     *
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-14 Modificado
     */
    public crearActualizarComicRest (): void {
        this.submitted = true;
        if (this.gestionarComicForm.invalid) {
            return;
        }
        // console.log('g/a this.listaComics.length: ' + this.listaComics.length);
        // console.log('id ' + this.gestionarComicForm.controls.id.value);
        let id: string;
        let bool: boolean;
        id = this.gestionarComicForm.controls.id.value;
        // console.dir(this.listaComics);
        this.listaComics.forEach(element => {
            if (element.id === id) {
                // console.log(element.id);
                bool = true;
            }
        });

        if (bool) {
            // console.log('id ' + id );
            this.comic.nombre = this.gestionarComicForm.controls.nombre.value;
            this.comic.editorial = this.gestionarComicForm.controls.editorial.value;
            this.comic.tematicaEnum = this.gestionarComicForm.controls.tematica.value;
            this.comic.coleccion = this.gestionarComicForm.controls.coleccion.value;
            this.comic.numeroPaginas = this.gestionarComicForm.controls.numeroPaginas.value;
            this.comic.precio = this.gestionarComicForm.controls.precio.value;
            this.comic.autores = this.gestionarComicForm.controls.autores.value;
            this.comic.color = this.gestionarComicForm.controls.color.value;
            this.comic.cantidad = this.gestionarComicForm.controls.cantidad.value;
            this.msjId = true;
            this.mensajeId = 'Se ha actualizado el comic: ' + this.comic.nombre + '  con Id: ' + id;
            setTimeout (() => {
                this.msjId = false;
             }, 2000);
        } else {
            this.idComic++;
            this.comic = new ComicDTO();
            this.comic.id = this.idComic + '';
            this.comic.nombre = this.gestionarComicForm.controls.nombre.value;
            this.comic.editorial = this.gestionarComicForm.controls.editorial.value;
            this.comic.tematicaEnum = this.gestionarComicForm.controls.tematica.value;
            this.comic.coleccion = this.gestionarComicForm.controls.coleccion.value;
            this.comic.numeroPaginas = this.gestionarComicForm.controls.numeroPaginas.value;
            this.comic.precio = this.gestionarComicForm.controls.precio.value;
            this.comic.autores = this.gestionarComicForm.controls.autores.value;
            this.comic.color = this.gestionarComicForm.controls.color.value;
            this.comic.cantidad = this.gestionarComicForm.controls.cantidad.value;
            this.listaComics.push(this.comic);
        }
        this.limpiarFormulario();
    }

    /**
     * @description Metodo que permite validar el formulario y crear o actulizar un comic
     *
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-14 Modificado
     */
    public crearActualizarComic(): void {
        this.submitted = true;
        if (this.gestionarComicForm.invalid) {
            return;
        }
        // console.log('g/a this.listaComics.length: ' + this.listaComics.length);
        // console.log('id ' + this.gestionarComicForm.controls.id.value);
        let id: string;
        let bool: boolean;
        id = this.gestionarComicForm.controls.id.value;
        // console.dir(this.listaComics);
        this.listaComics.forEach(element => {
            if (element.id === id) {
                // console.log(element.id);
                bool = true;
            }
        });

        if (bool) {
            // console.log('id ' + id );
            this.comic.nombre = this.gestionarComicForm.controls.nombre.value;
            this.comic.editorial = this.gestionarComicForm.controls.editorial.value;
            this.comic.tematicaEnum = this.gestionarComicForm.controls.tematica.value;
            this.comic.coleccion = this.gestionarComicForm.controls.coleccion.value;
            this.comic.numeroPaginas = this.gestionarComicForm.controls.numeroPaginas.value;
            this.comic.precio = this.gestionarComicForm.controls.precio.value;
            this.comic.autores = this.gestionarComicForm.controls.autores.value;
            this.comic.color = this.gestionarComicForm.controls.color.value;
            this.comic.cantidad = this.gestionarComicForm.controls.cantidad.value;
            this.msjId = true;
            this.mensajeId = 'Se ha actualizado el comic: ' + this.comic.nombre + '  con Id: ' + id;
            setTimeout (() => {
                this.msjId = false;
             }, 2000);
        } else {
            this.idComic++;
            this.comic = new ComicDTO();
            this.comic.id = this.idComic + '';
            this.comic.nombre = this.gestionarComicForm.controls.nombre.value;
            this.comic.editorial = this.gestionarComicForm.controls.editorial.value;
            this.comic.tematicaEnum = this.gestionarComicForm.controls.tematica.value;
            this.comic.coleccion = this.gestionarComicForm.controls.coleccion.value;
            this.comic.numeroPaginas = this.gestionarComicForm.controls.numeroPaginas.value;
            this.comic.precio = this.gestionarComicForm.controls.precio.value;
            this.comic.autores = this.gestionarComicForm.controls.autores.value;
            this.comic.color = this.gestionarComicForm.controls.color.value;
            this.comic.cantidad = this.gestionarComicForm.controls.cantidad.value;
            this.listaComics.push(this.comic);
        }
        this.limpiarFormulario();
    }

    /**
     * @description Metodo que permite consultar un comic de la tabla y sus detalles e inhabilitar el formulario
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-14
     * @param posicion en la lista del comic seleccionado
     */
    public consultarComic(posicion: number): void {
        let comic: any;
        comic = this.listaComics[posicion];
        this.gestionarComicForm.controls.nombre.setValue(comic.nombre);
        this.gestionarComicForm.controls.editorial.setValue(comic.editorial);
        this.gestionarComicForm.controls.tematica.setValue(comic.tematica);
        this.gestionarComicForm.controls.coleccion.setValue(comic.coleccion);
        this.gestionarComicForm.controls.numeroPaginas.setValue(comic.numeroPaginas);
        this.gestionarComicForm.controls.precio.setValue(comic.precio);
        this.gestionarComicForm.controls.autores.setValue(comic.autores);
        this.gestionarComicForm.controls.color.setValue(comic.color);
        this.gestionarComicForm.controls.nombre.disable();
        this.gestionarComicForm.controls.editorial.disable();
        this.gestionarComicForm.controls.tematica.disable();
        this.gestionarComicForm.controls.coleccion.disable();
        this.gestionarComicForm.controls.numeroPaginas.disable();
        this.gestionarComicForm.controls.precio.disable();
        this.gestionarComicForm.controls.autores.disable();
        this.gestionarComicForm.controls.color.disable();
        // this.gestionarComicForm.controls.color.enable(); para habilitar el campo

    }

    /**
     * @description Metodo que permite consultar un comic de la tabla y sus detalles en el formulario para poder actualizarlos
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-14
     * @param posicion en la lista del comic seleccionado
     */
    public editarComic(posicion: number): void {
        let comic: any;
        comic = this.listaComics[posicion];
        console.log('editar this.listaComics.length: ' + this.listaComics.length);
        this.gestionarComicForm.controls.nombre.setValue(comic.nombre);
        this.gestionarComicForm.controls.editorial.setValue(comic.editorial);
        this.gestionarComicForm.controls.tematica.setValue(comic.tematica);
        this.gestionarComicForm.controls.coleccion.setValue(comic.coleccion);
        this.gestionarComicForm.controls.numeroPaginas.setValue(comic.numeroPaginas);
        this.gestionarComicForm.controls.precio.setValue(comic.precio);
        this.gestionarComicForm.controls.autores.setValue(comic.autores);
        this.gestionarComicForm.controls.color.setValue(comic.color);
        this.gestionarComicForm.controls.id.setValue(comic.id);
    }

    /**
     * @description Metodo que permite eliminar un comic de la tabla
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-14
     * @param posicion en la lista del comic seleccionado
     */
    public eliminarComic(posicion: number): void {
        let comic: any;
        comic = this.listaComics[posicion];
        this.listaComics.splice(posicion, 1);

        this.verMensaje = true;
        this.mensaje = 'Se ha eliminado el comic: ' + comic.nombre + '  con Id: ' + comic.id;
        setTimeout (() => {
            this.verMensaje = false;
         }, 2000);
    }

    private limpiarFormulario(): void {
        this.submitted = false;
        this.gestionarComicForm.controls.nombre.setValue(null);
        this.gestionarComicForm.controls.editorial.setValue(null);
        this.gestionarComicForm.controls.tematica.setValue(null);
        this.gestionarComicForm.controls.coleccion.setValue(null);
        this.gestionarComicForm.controls.numeroPaginas.setValue(null);
        this.gestionarComicForm.controls.precio.setValue(null);
        this.gestionarComicForm.controls.autores.setValue(null);
        this.gestionarComicForm.controls.color.setValue(null);
    }

    /**
     * @description Metodo que obtiene los controles y sus propiedades
     * @author Diego Fernando Alvarez Silva
     */
    get f() {
        return this.gestionarComicForm.controls;
    }
}