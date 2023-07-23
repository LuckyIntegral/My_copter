import {Routes} from "@angular/router";
import {PurchaseComponent} from "./purchase.component";

export const PURCHASE_ROUTES: Routes = [
	{
		path: ':id',
		component: PurchaseComponent
	}
]