import {Routes} from "@angular/router";
import {DroneItemsComponent} from "./drone-items/drone-items.component";
import {DroneCrudComponent} from "./drone-crud/drone-crud.component";
import {ImageCrudComponent} from "./image-crud/image-crud.component";
import {ImageItemsComponent} from "./image-items/image-items.component";
import {ImageUpdateComponent} from "./image-update/image-update.component";
import {DroneUpdateComponent} from "./drone-update/drone-update.component";
import {EntityProcessComponent} from "./entity-process/entity-process.component";

export const CRUD_ROUTES: Routes = [
	{
		path: 'drones/items',
		component: DroneItemsComponent
	},
	{
		path: 'drones',
		component: DroneCrudComponent
	},
	{
		path: 'images/items',
		component: ImageItemsComponent
	},
	{
		path: 'images',
		component: ImageCrudComponent
	},
	{
		path: 'drones/update/:id',
		component: DroneUpdateComponent
	},
	{
		path: 'images/update/:id',
		component: ImageUpdateComponent
	},
	{
		path: 'process',
		component:EntityProcessComponent
	}
]