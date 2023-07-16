import {Component} from '@angular/core';
import {AsyncPipe} from "@angular/common";
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {map, Observable} from "rxjs";
import {AuthService} from "../../services/auth.service";
import {SessionService} from "../../services/session.service";
import {Router} from "@angular/router";

@Component({
	selector: 'app-login',
	standalone: true,
	templateUrl: './login.component.html',
	imports: [
		AsyncPipe,
		FormsModule,
		ReactiveFormsModule
	],
})
export class LoginComponent {

	form = this._fb.group({
		username: ['', [Validators.required, Validators.email]],
		password: ['', [Validators.required, Validators.minLength(8)]],
	})

	isSubmit: Observable<boolean> = this.form.statusChanges.pipe(
		map(status => status === 'VALID')
	);

	constructor(
		private _fb: FormBuilder,
		private _registerService: AuthService,
		private _sessionService: SessionService,
		private _router: Router) {
	}

	login(): void {
		let value = this.form.value;
		const username: string = value.username as string;
		const password: string = value.password as string;
		this._registerService.authenticate(username, password).subscribe(res => {
			this._sessionService.addToStorage("token", JSON.stringify(res));
			this._router.navigateByUrl('/plp');
		});
	}
}
