import { Injectable } from '@angular/core';
import { Injector } from '@angular/core';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import { AbstractService } from './template.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PersonaDTO } from '../dto/persona.dto';


 /**
 * @description Servicio encargado de llamar a los servicios REST de GestionarPersona
 * @author Alberto David Puche Algarin
 * @fecha 2019-12-20
 */
@Injectable({
  providedIn: 'root'
})
export class GestionaPersonaService extends AbstractService {

  /**
   * Constructor
   */
  constructor(injector: Injector, private httpClient: HttpClient) {
    super(injector);
  }

  public crear(personaDTO: PersonaDTO): Observable<any> {
    return this.httpClient.post('http://localhost:8085/semillero-servicios/rest/GestionarPersona/crear', personaDTO);
  }

  public consultar(): Observable<any> {
    return this.httpClient.get('http://localhost:8085/semillero-servicios/rest/GestionarPersona/consultar');
  }

}
