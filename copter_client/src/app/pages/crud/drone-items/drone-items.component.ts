import {Component} from '@angular/core';
import {DroneCrudService} from "../../../services/product/drone.crud.service";
import {Observable, of} from "rxjs";
import {DroneFullModel} from "../../../models/drone-full.model";
import {DataTableResponse} from "../../../models/data-table.response";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {AppPriceUtil} from "../../../app.price-util";
import {Router} from "@angular/router";

@Component({
	selector: 'app-drone-items',
	standalone: true,
	templateUrl: './drone-items.component.html',
	imports: [
		NgForOf,
		AsyncPipe,
		NgIf
	],
	styleUrls: ['./drone-items.component.scss']
})
export class DroneItemsComponent {

	drones$: Observable<DataTableResponse<DroneFullModel>> = this._droneCrudService.loadAllDrones();
	isDescClicked: { [key: number]: boolean } = {};
	isDeleted: Observable<boolean> = of(false);

	constructor(private _droneCrudService: DroneCrudService,
				private _router: Router) {
	}

	toggleDescClicked(droneId: number) {
		this.isDescClicked[droneId] = !this.isDescClicked[droneId];
	}

	deleteDrone(id: number): void {
		this.isDeleted = this._droneCrudService.deleteDrone(id);
		this.showPage(0);
	}

	showPage(page: number): void {
		this.drones$ = this._droneCrudService.loadAllDrones(page) as Observable<DataTableResponse<DroneFullModel>>;
	}

	protected readonly formatNumber = AppPriceUtil.formatNumber;

	redirectToUpdate(id: number) {
		this._router.navigateByUrl('/crud/drones/update/' + id);
	}
}
