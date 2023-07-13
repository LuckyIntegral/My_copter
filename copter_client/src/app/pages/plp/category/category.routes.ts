import { Routes } from "@angular/router";
import { CategoryComponent } from "./category.component";

export const CATEGORY_ROUTES: Routes = [
	{
		path: ':category',
		component: CategoryComponent,
	},
]