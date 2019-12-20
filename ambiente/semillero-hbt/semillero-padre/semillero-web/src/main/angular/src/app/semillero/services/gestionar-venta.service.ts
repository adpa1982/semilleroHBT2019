import { Injectable } from '@angular/core';
import { Injector } from '@angular/core';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import { AbstractService } from './template.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { VentaDTO } from '../dto/venta.dto';


 /**
 * @description Servicio encargado de llamar a los servicios REST de GestionarPersona
 * @author Alberto David Puche Algarin
 * @fecha 2019-12-20
 */
@Injectable({
  providedIn: 'root'
})
export class GestionaVentaService extends AbstractService {

  /**
   * Constructor
   */
  constructor(injector: Injector, private httpClient: HttpClient) {
    super(injector);
  }

  public crear(ventaDTO: VentaDTO): Observable<any> {
    return this.httpClient.post('http://localhost:8085/semillero-servicios/rest/GestionarVenta/crear', ventaDTO);
  }

  public consultar(): Observable<any> {
    return this.httpClient.get('http://localhost:8085/semillero-servicios/rest/GestionarVenta/consultar');
  }

}
