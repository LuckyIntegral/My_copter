import {Component} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {map, Observable} from "rxjs";
import {ProcessCrudService} from "../../../services/process.crud.service";

@Component({
	selector: 'app-entity-process',
	standalone: true,
	templateUrl: './entity-process.component.html',
	styleUrls: ['./entity-process.component.scss']
})
export class EntityProcessComponent {

	form = this._fb.group({
		copterId: ['', Validators.required, Validators.min(0)],
		imageId: ['', Validators.required, Validators.min(0)]
	});

	isSubmit: Observable<boolean> = this.form.statusChanges.pipe(
		map(status => status === 'VALID')
	);

	constructor(private _fb: FormBuilder, private _processService: ProcessCrudService) {
	}

	attachProcess() {
		const droneId: number = +this.form.value.copterId!;
		const imageId: number = +this.form.value.imageId!;
		return this._processService.attachPictureToDrone(droneId, imageId);
	}

	detachProcess() {
		const droneId: number = +this.form.value.copterId!;
		const imageId: number = +this.form.value.imageId!;
		return this._processService.detachPictureToDrone(droneId, imageId);
	}
}
