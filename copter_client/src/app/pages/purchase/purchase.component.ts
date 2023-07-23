import {Component} from '@angular/core';
import {FormService} from "../../services/util/form.service";
import {Router} from "@angular/router";
import {map, Observable, of, timer} from "rxjs";
import {OrderService} from "../../services/order/order.service";
import {ReactiveFormsModule} from "@angular/forms";
import {AsyncPipe, NgIf} from "@angular/common";

@Component({
	selector: 'app-purchase',
	standalone: true,
	templateUrl: './purchase.component.html',
	imports: [
		ReactiveFormsModule,
		AsyncPipe,
		NgIf
	]
})
export class PurchaseComponent {

	form = this._formService.purchaseForm();
	isSubmit: Observable<boolean> = this.form.statusChanges.pipe(
		map(status => status === 'VALID')
	);
	isSuccessful$: Observable<boolean> = of(false);

	constructor(private _formService: FormService, private _orderService: OrderService, private _router: Router) {
	}

	makePurchase() {
		let value = this.form.value;
		const contact: string = value.contact as string;
		const address: string = value.address as string;
		const id: number = parseInt(this._router.routerState.snapshot.url.split('/')[2]);
		this._orderService.makePurchase(contact, address, id).subscribe(() => {
			this.isSuccessful$ = of(true)
			timer(3000).subscribe(() => this._router.navigateByUrl('/plp'));
		});
	}
}
