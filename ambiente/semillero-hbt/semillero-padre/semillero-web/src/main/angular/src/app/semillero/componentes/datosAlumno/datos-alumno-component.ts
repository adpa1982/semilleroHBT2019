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
     * @description Variable que va almacenar los datos de los comic
     */
    public arrayDatos: Array<any>;
    /**
     * @description Variable que va a listar los comics despues de eliminar un dato
     */
    public listArrayDatos: string;
    /**
     * @description Variable que va a listar los comics
     */
    public listArrayDatos1: string;
    /**
     * @description Variable que va a contener el mensaje del dato eliminado
     */
    public mensaje: string;
    /**
     * @description Variable que va a verificar el estado del div a mostrar
     */
    public verMensaje: boolean;


    /**
     * @description Metodo constructor de la clase datos alumno
     */
    constructor() {
    }

    ngOnInit(): void {
        this.nombres = 'Alberto David';
        this.apellidos = 'Puche Algarin';
        this.ciudad = 'Montería - Cordoba';

        this.mensaje = '';
        this.verMensaje = false;

        this.arrayDatos = new Array<any>();
        this.llenarObjeto();

        // Muestra los datos del array completos
        this.listArrayDatos1 = JSON.stringify(this.arrayDatos);
    }

    /**
     * @description Metodo para eliminar un dato de un objeto
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-10
     */
    public eliminar (e) {
        this.arrayDatos.splice(3, 1);
        let datoEliminado = this.arrayDatos[3];
        this.verMensaje = true;
        this.listArrayDatos = JSON.stringify(this.arrayDatos);
        this.mensaje = 'Se ha eliminado el comic ' + datoEliminado.nombre + '  Informacion completa: ' + JSON.stringify(datoEliminado);
        console.log(e);
    }

    /**
     * @description Metodo para llenar los datos de un objeto
     * @author Alberto David Puche Algarin
     * @fecha 2019-12-10
     */
    public llenarObjeto () {
        let arrayDatos1: any = {};
        arrayDatos1 = {
            'id': 11,
            'nombre': 'SUPERMAN',
            'editorial': 'DC COMIC',
            'tematica': 'SUPER HEROES',
            'numeroPaginas': '120',
            'precio': 1000,
            'autores': 'CLARK KENT',
            'aColor': 'SI',
            'fechaVenta': '2019-10-12',
            'estado': 'ACTIVO',
        };
        this.arrayDatos.push(arrayDatos1);
        arrayDatos1 = {
            'id': 22,
            'nombre': 'DARK PHOENIX',
            'editorial': 'MARVEL',
            'tematica': 'MUTANTES',
            'numeroPaginas': '100',
            'precio': 2000,
            'autores': 'CHARLES XAVIER',
            'aColor': 'SI',
            'fechaVenta': '2019-10-12',
            'estado': 'ACTIVO',
        };
        this.arrayDatos.push(arrayDatos1);
        arrayDatos1 = {
            'id': 33,
            'nombre': 'BIG HERO 6',
            'editorial': 'WALT DISNEY',
            'tematica': 'HEROES',
            'numeroPaginas': '150',
            'precio': 3000,
            'autores': 'HIRO HAMADA',
            'aColor': 'SI',
            'fechaVenta': '2019-10-12',
            'estado': 'ACTIVO',
        };
        this.arrayDatos.push(arrayDatos1);
        arrayDatos1 = {
            'id': 44,
            'nombre': 'NARUTO',
            'editorial': 'SHONEN',
            'tematica': 'AVENTURA NINJA',
            'numeroPaginas': '30',
            'precio': 4000,
            'autores': 'MASASHI KISIMOTO',
            'aColor': 'NO',
            'fechaVenta': '2019-10-12',
            'estado': 'ACTIVO',
        };
        this.arrayDatos.push(arrayDatos1);
        arrayDatos1 = {
            'id': 55,
            'nombre': 'PHINEAS Y FERBS',
            'editorial': 'INDEPENDIENTE',
            'tematica': 'AVENTURA INFANTIL',
            'numeroPaginas': '50',
            'precio': 4000,
            'autores': 'FINN FLECHER',
            'aColor': 'SI',
            'fechaVenta': '2019-10-12',
            'estado': 'ACTIVO',
        };
        this.arrayDatos.push(arrayDatos1);
    }

}
