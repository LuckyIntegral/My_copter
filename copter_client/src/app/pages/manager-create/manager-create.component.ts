import {Component, OnInit} from '@angular/core';
import {map, Observable, of, timer} from "rxjs";
import {ManagerCreateService} from "../../services/account/manager-create.service";
import {FormGroup, ReactiveFormsModule} from "@angular/forms";
import {FormService} from "../../services/util/form.service";
import {AsyncPipe, NgIf} from "@angular/common";

@Component({
	selector: 'app-manager-create',
	standalone: true,
	templateUrl: './manager-create.component.html',
	imports: [
		ReactiveFormsModule,
		AsyncPipe,
		NgIf
	],
	styleUrls: ['./manager-create.component.scss']
})
export class ManagerCreateComponent implements OnInit {
	form: FormGroup = this._formService.registerForm();

	isCreated$: Observable<boolean> = of(false);
	isError$: Observable<boolean> = of(false);
	isValid$: Observable<boolean> = this.form.statusChanges.pipe(map(status => status === 'VALID'));

	constructor(private _formService: FormService, private _managerService: ManagerCreateService) {
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

	createManager() {
		let value = this.form.value;
		const firstname: string = value.firstname as string;
		const lastname: string = value.lastname as string;
		const username: string = value.username as string;
		const password: string = value.password as string;
		this._managerService.createManager(firstname, lastname, username, password, 'MANAGER').subscribe(res => {
			if (res) {
				this.isCreated$ = of(true)
				timer(3000).subscribe(() => this.isCreated$ = of(false));
			} else {
				this.isError$ = of(true)
				timer(3000).subscribe(() => this.isError$ = of(false));
			}
		});
	}
}
