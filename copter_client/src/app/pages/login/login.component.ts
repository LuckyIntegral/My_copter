import {Component} from '@angular/core';
import {AsyncPipe, NgIf} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {map, Observable, of, timer} from "rxjs";
import {AuthService} from "../../services/account/auth.service";
import {SessionService} from "../../services/account/session.service";
import {Router} from "@angular/router";
import {FormService} from "../../services/util/form.service";

@Component({
	selector: 'app-login',
	standalone: true,
	templateUrl: './login.component.html',
	imports: [
		AsyncPipe,
		FormsModule,
		ReactiveFormsModule,
		NgIf
	],
})
export class LoginComponent {

	form = this._formService.loginForm();
	isSubmit: Observable<boolean> = this.form.statusChanges.pipe(
		map(status => status === 'VALID')
	);
	isError$: Observable<boolean> = of(false)

	constructor(
		private _formService: FormService,
		private _registerService: AuthService,
		private _sessionService: SessionService,
		private _router: Router) {
	}

	login(): void {
		let value = this.form.value;
		const username: string = value.username as string;
		const password: string = value.password as string;
		this._registerService.authenticate(username, password).subscribe(res => {
			if (typeof res === "boolean") {
				this.isError$ = of(true)
				timer(3000).subscribe(() => this.isError$ = of(false));
			} else {
				this._sessionService.addToStorage("token", JSON.stringify(res));
				this._router.navigateByUrl('/plp');
			}
		});
	}
}
