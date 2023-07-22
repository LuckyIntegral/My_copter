import {Component} from '@angular/core';
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {DronePdp} from "../../models/product/drone-pdp";
import {PdpService} from "../../services/product/pdp/pdp.service";
import {AppPriceUtil} from "../../app.price-util";
import {CartService} from "../../services/order/cart.service";
import {AuthService} from "../../services/account/auth.service";

@Component({
	selector: 'app-pdp',
	standalone: true,
	templateUrl: './pdp.component.html',
	imports: [
		AsyncPipe,
		NgIf,
		NgForOf
	],
	styleUrls: ['./pdp.component.scss']
})
export class PdpComponent {

	pdpDrone$: Observable<DronePdp>;

	constructor(
		private _pdpService: PdpService,
		private _router: Router,
		private _cartService: CartService,
		private _authService: AuthService) {
		this.pdpDrone$ = this._pdpService
			.loadDronePdp(parseInt(this._router.routerState.snapshot.url.split('/')[2])) as Observable<DronePdp>;
	}

	addToCart(): void {
		if (!this._authService.isLoggedIn())
			this._router.navigateByUrl('/login')
		const droneId: number = parseInt(this._router.routerState.snapshot.url.split('/')[2]);
		this.pdpDrone$
			.pipe(() => this._cartService.addToCart(droneId, 1))
			.subscribe(() => {
				this._router.navigateByUrl('/cart')
			});
	}

	protected readonly AppPriceUtil = AppPriceUtil;
}
