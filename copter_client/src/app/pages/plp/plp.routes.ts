import { Routes } from "@angular/router";
import { PlpComponent } from "./plp.component";

export const PLP_ROUTES: Routes = [
  {
    path: '',
    component: PlpComponent,
  },
  {
    path: 'category/:param',
    component: PlpComponent,
  },
  {
    path: 'brand/:param',
    component: PlpComponent,
  }
]
