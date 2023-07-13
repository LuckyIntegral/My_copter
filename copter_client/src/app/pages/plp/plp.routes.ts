import { Routes } from "@angular/router";
import { PlpComponent } from "./plp.component";

export const PLP_ROUTES: Routes = [
  {
    path: '',
    component: PlpComponent,
  },
  {
    path: 'category',
    pathMatch: "prefix",
    loadChildren: () => import('./category/category.routes').then(m => m.CATEGORY_ROUTES)
  },
  {
    path: 'brand',
    pathMatch: "prefix",
    loadChildren: () => import('./brand/brand.routes').then(m => m.BRAND_ROUTES)
  }
]
