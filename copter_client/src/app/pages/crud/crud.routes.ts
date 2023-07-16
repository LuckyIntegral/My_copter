import {Routes} from "@angular/router";
import {DroneItemsComponent} from "./drone-items/drone-items.component";
import {DroneCrudComponent} from "./drone-crud/drone-crud.component";
import {ImageCrudComponent} from "./image-crud/image-crud.component";

export const CRUD_ROUTES: Routes = [
	{
		path: 'drones',
		component: DroneItemsComponent
	},
	{
		path: 'drones/crud',
		component: DroneCrudComponent
	},
	{
		path: 'images',
		component: ImageCrudComponent
	},
	{
		path: 'images/crud',
		component: ImageCrudComponent
	}
]