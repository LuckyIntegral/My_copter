import { Routes } from "@angular/router";

export const APP_ROUTES: Routes = [
  {
    path: '',
    redirectTo: 'plp',
    pathMatch: 'full'
  },
  {
    path: 'plp',
    pathMatch: 'prefix',
    loadChildren:() => import('./pages/plp/plp.routes').then(m => m.PLP_ROUTES)
  },
  {
    path: 'pdp',
    pathMatch: 'prefix',
    loadChildren:() => import('./pages/pdp/pdp.routes').then(m => m.PDP_ROUTES)
  }
];
