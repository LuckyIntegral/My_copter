import {Component} from '@angular/core';
import {CartService} from "../../services/order/cart.service";
import {map, Observable} from "rxjs";
import {AsyncPipe, DatePipe, JsonPipe, NgForOf, NgIf} from "@angular/common";
import {CartModel} from "../../models/order/cart.model";
import {AppPriceUtil} from "../../app.price-util";
import {Router} from "@angular/router";

@Component({
	selector: 'app-cart',
	standalone: true,
	templateUrl: './cart.component.html',
	imports: [
		NgIf,
		AsyncPipe,
		JsonPipe,
		NgForOf,
		DatePipe
	],
	styleUrls: ['./cart.component.scss']
})
export class CartComponent {

	cart$: Observable<CartModel> = this._cartService.loadCart();

	constructor(private _cartService: CartService, private _router: Router) {
	}

	deleteProductFromCart(id: number): Observable<any> {
		return this._cartService.addToCart(id, -1)
			.pipe(
				map(() => {
					this.cart$ = this._cartService.loadCart();
				})
			);
	}

	addProductToCart(id: number): Observable<any> {
		return this._cartService.addToCart(id, 1)
			.pipe(
				map(() => {
					this.cart$ = this._cartService.loadCart();
				})
			);
	}

	redirectToPurchase(id: number) {
		this._router.navigateByUrl('/purchase/' + id)
	}

	protected readonly appPriceUtil = AppPriceUtil;
	protected readonly parseInt = parseInt;
}
