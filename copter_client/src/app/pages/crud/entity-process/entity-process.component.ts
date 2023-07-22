import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {map, Observable, of, timer} from "rxjs";
import {ProcessCrudService} from "../../../services/product/process.crud.service";
import {AsyncPipe, NgIf} from "@angular/common";

@Component({
	selector: 'app-entity-process',
	standalone: true,
	templateUrl: './entity-process.component.html',
	imports: [
		FormsModule,
		ReactiveFormsModule,
		AsyncPipe,
		NgIf
	],
	styleUrls: ['./entity-process.component.scss']
})
export class EntityProcessComponent implements OnInit {

	form = this._fb.group({
		copterId: ['', [Validators.required, Validators.min(0)]],
		imageId: ['', [Validators.required, Validators.min(0)]]
	});

	isSuccessful$: Observable<boolean> = of(false);
	isError$: Observable<boolean> = of(false);

	isSubmit$ = this.form.statusChanges.pipe(
		map(status => status === 'VALID')
	);

	ngOnInit(): void {
		this.form.statusChanges
			.pipe(
				map(value => {
					if (value === 'VALID') return 1;
					if (value === 'INVALID') return 2;
					return 0
				})
			)
			.subscribe();
	}

	constructor(private _fb: FormBuilder, private _processService: ProcessCrudService) {
	}

	attachProcess(): void {
		const droneId: number = +this.form.value.copterId!;
		const imageId: number = +this.form.value.imageId!;
		this._processService.attachPictureToDrone(droneId, imageId).subscribe(
			result => this.showStatus(result)
		);
	}

	detachProcess(): void {
		const droneId: number = +this.form.value.copterId!;
		const imageId: number = +this.form.value.imageId!;
		this._processService.detachPictureToDrone(droneId, imageId).subscribe(
			result => this.showStatus(result)
		);
	}

	showStatus(result: boolean): void {
		if (result) {
			this.isSuccessful$ = of(true)
			timer(3000).subscribe(() => this.isSuccessful$ = of(false));
		} else {
			this.isError$ = of(true)
			timer(3000).subscribe(() => this.isError$ = of(false));
		}
	}
}
