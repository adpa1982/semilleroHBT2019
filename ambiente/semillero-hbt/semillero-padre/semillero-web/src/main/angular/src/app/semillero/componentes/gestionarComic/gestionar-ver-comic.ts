import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';


/**
 * @description Componenete gestionar data
 * @author Alberto David Puche Algarin
 * @fecha 2019-12-13
 */
@Component({
    // tslint:disable-next-line: component-selector
    selector: 'gestionar-ver-comic',
    templateUrl: './gestionar-ver-comic.html',
    styleUrls: ['./gestionar-ver-comic.css']
})
export class GestionarVerComicComponent implements OnInit {

    /**
     * Atributo que contiene los controles del formulario
     */
    public: FormGroup;

    /**
    * Variable que contiene el id del data.
    */
    public id: string;

    /**
    * Variable que contiene el nombre del data.
    */
    public nombre: string;

    /**
    * Variable que contiene la editorial del data.
    */
    public editorial: string;

    /**
    * Variable que contiene la tematica del data.
    */
    public tematica: string;

    /**
    * Variable que contiene la coleccion del data.
    */
    public coleccion: string;

    /**
    * Variable que contiene el numero de paginas del data.
    */
    public numeroPaginas: number;

    /**
    * Variable que contiene el precio del data.
    */
    public precio: number;

    /**
    * Variable que contiene el autor(es) del data.
    */
    public autores: string;

    /**
    * Variable que contiene si el data es a color o no.
    */
    public color: string;

    constructor(private router: Router, private activatedRoute: ActivatedRoute) {
        // console.log('entro al constructor del componente bienvenida');
    }

    /**
     * @description Evento angular que se ejecuta al invocar el componente
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-13
     */
    ngOnInit(): void {
        console.log('Ingreso al al evento oninit');
        let data = this.activatedRoute.snapshot.params;

        console.log('Parametros recibidos ' +  data.autores);
        console.dir(data);
        this.mostrarComicData(data);
    }

    /**
     * @description Metodo que permite consultar un data de la tabla y muestra su resultado en una tabla
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-13
     * @param data obtiene lod datos enviados desde la tabla gestionar comic
     */
    public mostrarComicData(data): void {
        let col = '';
        this.id = data.id;
        this.nombre = data.nombre;
        this.editorial = data.editorial;
        this.tematica = data.tematica;
        this.coleccion = data.coleccion;
        this.numeroPaginas = data.numeroPaginas;
        this.precio = data.precio;
        this.autores = data.autores;
        col = data.color === 'true' ? 'SI' : 'NO';
        this.color = col;
    }

    /**
     * @description Metodo que permite consultar redirigir a la vista de gestionar comic
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-13
     */
    public gestionarComic(): void {
        this.router.navigate(['gestionar-comic']);
    }
}