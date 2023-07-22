import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {map, Observable, of} from "rxjs";
import {ImageCrudService} from "../../../services/product/crud/image.crud.service";
import {ImageCrudModel} from "../../../models/image-crud.model";
import {Router} from "@angular/router";
import {AsyncPipe, NgIf} from "@angular/common";

@Component({
	selector: 'app-image-update',
	standalone: true,
	templateUrl: './image-update.component.html',
	imports: [
		AsyncPipe,
		FormsModule,
		NgIf,
		ReactiveFormsModule
	],
	styleUrls: ['./image-update.component.scss']
})
export class ImageUpdateComponent implements OnInit {

	form = this._fb.group({
		mainImage: ['', Validators.required],
		imageUrl: ['', Validators.required],
	})

	isSubmit: Observable<boolean> = this.form.statusChanges.pipe(
		map(status => status === 'VALID')
	);

	isValidSrc$: Observable<boolean> = this.form.controls.imageUrl.valueChanges.pipe(
		map(() => this.form.controls.imageUrl.valid)
	);

	imageId: number = parseInt(this._router.routerState.snapshot.url.split('/')[4]);
	imageModel$: Observable<ImageCrudModel> = this._imageCrudService.loadImageById(this.imageId);
	isUpdated$: Observable<boolean> = of(false);

	constructor(
		private _fb: FormBuilder,
		private _imageCrudService: ImageCrudService,
		private _router: Router) {
		this.imageModel$.subscribe(res => this.populateFormWithCurrentValues(res))
	}

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

	populateFormWithCurrentValues(currentValues: any): void {
		this.form.patchValue({
			mainImage: currentValues.mainImage,
			imageUrl: currentValues.imageUrl
		});
	}

	updateImage(): void {
		if (this.form.valid) {
			const value = this.form.value;
			let image: ImageCrudModel = {
				imageUrl: value.imageUrl!,
				mainImage: value.mainImage! === 'true'
			};
			this.isUpdated$ = this._imageCrudService.updateImage(image, this.imageId);
		}
	}
}
