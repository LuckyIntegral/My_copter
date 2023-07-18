import {Injectable} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';

@Injectable({
	providedIn: 'root'
})
export class FormService {
	constructor(private _formBuilder: FormBuilder) {
	}

	createDroneForm() {
		return this._formBuilder.group({
			brand: ['', Validators.required],
			category: ['', Validators.required],
			fpv: ['', Validators.required],
			name: ['', Validators.required],
			cameraResolution: ['', Validators.required],
			battery: ['', Validators.required],
			flyTime: ['', Validators.required],
			price: ['', [Validators.required, Validators.min(0)]],
			quantity: ['', [Validators.required, Validators.min(0)]],
			description: ['', Validators.required],
		});
	}
}
