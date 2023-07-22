import {Component, OnInit} from '@angular/core';
import {FormGroup, ReactiveFormsModule} from "@angular/forms";
import {map, Observable, of} from "rxjs";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {DroneCrudModel} from "../../../models/drone-crud.model";
import {DroneCrudService} from "../../../services/product/crud/drone.crud.service";
import {FormService} from "../../../services/util/form.service";

@Component({
	selector: 'app-drone-crud',
	standalone: true,
	templateUrl: './drone-crud.component.html',
	imports: [
		AsyncPipe,
		NgIf,
		ReactiveFormsModule,
		NgForOf
	],
	styleUrls: ['./drone-crud.component.scss']
})
export class DroneCrudComponent implements OnInit {

	brands = ['DJI', 'PARROT', 'AUTEL', 'WALKERA', 'OTHER'];
	categories = ['PROFESSIONAL', 'RACING', 'SELFIE'];

	form: FormGroup = this._formService.createDroneForm();

	isSubmit: Observable<boolean> = this.form.statusChanges.pipe(
		map(status => status === 'VALID')
	);

	isCreated: Observable<boolean> = of(false);

	constructor(
		private _droneCrudService: DroneCrudService,
		private _formService: FormService) {
	}

	ngOnInit(): void {
		this.form.statusChanges.pipe(
			map(value => {
				if (value === 'VALID') return 1;
				if (value === 'INVALID') return 2;
				return 0
			})
		)
			.subscribe();
	}

	createCopter(): void {
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
			this.isCreated = this._droneCrudService.createDrone(drone);
		}
	}
}
