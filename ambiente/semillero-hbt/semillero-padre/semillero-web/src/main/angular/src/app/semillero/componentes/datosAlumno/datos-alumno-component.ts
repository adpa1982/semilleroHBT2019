import { Component, OnInit } from '@angular/core';


/**
 * @description Componente datos-alumno, el cual muestra nombre completo y ciudad del alumno
 * @author Alberto David Puche Algarin
 * @fecha 2019-12-03
 */
@Component({
    // tslint:disable-next-line: component-selector
    selector: 'datos-alumno',
    templateUrl: './datos-alumno-component.html',
    styleUrls: ['./datos-alumno-component.css']
})

/**
 * @description Clase que va a manejar todo lo relacionado con los datos del alumno
 * @author Alberto David Puche Algarin
 * @fecha 2019-12-03
 */
export class DatosAlumnoComponent implements OnInit {

    /**
     * @description Variable que captura los nombres del alumno
     */
    public nombres: string;
    /**
     * @description Variable que captura los apellidos del alumno
     */
    public apellidos: string;
    /**
     * @description Variable que captura la ciudad del alumno
     */
    public ciudad: string;

    /**
     * @description Metodo constructor de la clase datos alumno
     */
    constructor() {
        this.nombres = 'Alberto David';
        this.apellidos = 'Puche Algarin';
        this.ciudad = 'Montería - Cordoba';
    }

    ngOnInit(): void {}
}
