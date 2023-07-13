import { Routes } from "@angular/router";
import { BrandComponent } from "./brand.component";

export const BRAND_ROUTES: Routes = [
	{
		path: ':brand',
		component: BrandComponent,
	}
]