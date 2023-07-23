import {Injectable} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';

@Injectable({
	providedIn: 'root'
})
export class FormService {
	constructor(private _formBuilder: FormBuilder) {
	}

	purchaseForm() {
		return this._formBuilder.group({
			contact: ['', [Validators.required, Validators.minLength(8)]],
			address: ['', [Validators.required, Validators.minLength(8)]]
		});
	}

	loginForm() {
		return this._formBuilder.group({
			username: ['', [Validators.required, Validators.email]],
			password: ['', [Validators.required, Validators.minLength(8)]],
		});
	}

	registerForm() {
		return this._formBuilder.group({
			firstname: ['', Validators.required],
			lastname: ['', Validators.required],
			username: ['', [Validators.required, Validators.email]],
			password: ['', [Validators.required, Validators.minLength(8)]],
		});
	}

	createImageForm() {
		return this._formBuilder.group({
			mainImage: ['', Validators.required],
			imageUrl: ['', Validators.required],
		});
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
