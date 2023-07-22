import {Component, OnInit} from '@angular/core';
import {ReactiveFormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {CommonModule} from "@angular/common";
import {map, Observable} from "rxjs";
import {SessionService} from "../../services/account/session.service";
import {AuthService} from "../../services/account/auth.service";
import {FormService} from "../../services/util/form.service";

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	imports: [
		ReactiveFormsModule,
		CommonModule
	],
	standalone: true
})
export class RegisterComponent implements OnInit {

	form = this._formService.registerForm();

	isSubmit: Observable<boolean> = this.form.statusChanges.pipe(
		map(status => status === 'VALID')
	);

	constructor(
		private _formService: FormService,
		private _registerService: AuthService,
		private _sessionService: SessionService, private _router: Router) {
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

	register(): void {
		let value = this.form.value;
		const firstname: string = value.firstname as string;
		const lastname: string = value.lastname as string;
		const username: string = value.username as string;
		const password: string = value.password as string;
		this._registerService.register(firstname, lastname, username, password, 'CUSTOMER').subscribe(res => {
			this._sessionService.addToStorage("token", JSON.stringify(res));
			this._router.navigateByUrl('/plp');
		});
	}
}
