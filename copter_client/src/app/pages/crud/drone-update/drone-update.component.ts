import {Component} from '@angular/core';
import {FormGroup, ReactiveFormsModule} from "@angular/forms";
import {map, Observable, of} from "rxjs";
import {DroneCrudService} from "../../../services/product/crud/drone.crud.service";
import {DroneCrudModel} from "../../../models/drone-crud.model";
import {FormService} from "../../../services/util/form.service";
import {Router} from "@angular/router";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";

@Component({
	selector: 'app-drone-update',
	standalone: true,
	templateUrl: './drone-update.component.html',
	imports: [
		ReactiveFormsModule,
		NgIf,
		NgForOf,
		AsyncPipe
	],
	styleUrls: ['./drone-update.component.scss']
})
export class DroneUpdateComponent {

	brands = ['DJI', 'PARROT', 'AUTEL', 'WALKERA', 'OTHER'];
	categories = ['PROFESSIONAL', 'RACING', 'SELFIE'];

	droneId: number = parseInt(this._router.routerState.snapshot.url.split('/')[4]);
	form: FormGroup = this._droneFormService.createDroneForm();
	isUpdated$: Observable<boolean> = of(false);
	droneModel$: Observable<DroneCrudModel> = this._droneCrudService.loadDroneById(this.droneId);

	isSubmit: Observable<boolean> = this.form.statusChanges.pipe(
		map(status => status === 'VALID')
	);

	constructor(
		private _droneCrudService: DroneCrudService,
		private _droneFormService: FormService,
		private _router: Router) {
		this.droneModel$.subscribe(res => this.populateFormWithCurrentValues(res))
	}

	populateFormWithCurrentValues(currentDrone: DroneCrudModel): void {
		this.form.patchValue({
			brand: currentDrone.brand,
			category: currentDrone.category,
			fpv: currentDrone.fpv,
			name: currentDrone.name,
			cameraResolution: currentDrone.cameraResolution,
			battery: currentDrone.battery,
			flyTime: currentDrone.flyTime,
			price: currentDrone.price,
			quantity: currentDrone.quantity,
			description: currentDrone.description
		});
	}

	updateCopter(): void {
		if (this.form.valid) {
			const value = this.form.value;
			let drone: DroneCrudModel = {
				brand: value.brand!,
				category: value.category!,
				fpv: value.fpv! === 'true',
				name: value.name!,
				cameraResolution: value.cameraResolution!,
				battery: value.battery!,
				flyTime: value.flyTime!,
				price: +value.price!,
				quantity: +value.quantity!,
				description: value.description!
			};
			this.isUpdated$ = this._droneCrudService.updateDrone(drone, this.droneId);
		}
	}
}
