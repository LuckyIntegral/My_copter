import {Component} from '@angular/core';
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {Observable, switchMap} from "rxjs";
import {Router} from "@angular/router";
import {DronePdp} from "../../models/drone-pdp";
import {PdpService} from "../../services/pdp.service";
import {AppPriceUtil} from "../../app.price-util";
import {CartService} from "../../services/cart.service";
import {AuthService} from "../../services/auth.service";

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
			}, (error) => {
				this._router.navigateByUrl('/login')
			});
	}

	protected readonly AppPriceUtil = AppPriceUtil;
}
