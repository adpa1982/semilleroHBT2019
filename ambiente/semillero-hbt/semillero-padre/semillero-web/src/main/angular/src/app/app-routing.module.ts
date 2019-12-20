import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GestionarComicComponent } from './semillero/componentes/gestionarComic/gestionar-comic';
import { BienvenidaComponent } from './semillero/componentes/home/bienvenida-component';
import { GestionarVerComicComponent } from './semillero/componentes/gestionarComic/gestionar-ver-comic';
import { CrearPersonaComponent } from './semillero/componentes/crearPersona/crear-persona-component';
import { VentaComponent } from './semillero/componentes/venta/venta.component';


const routes: Routes = [
  { path: 'gestionar-venta', component: VentaComponent },
  { path: 'gestionar-persona', component: CrearPersonaComponent },
  { path: 'gestionar-comic', component: GestionarComicComponent },
  { path: 'gestionar-ver-comic', component: GestionarVerComicComponent },
  { path: 'bienvenida', component: BienvenidaComponent, data : null }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
