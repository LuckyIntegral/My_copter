import {Component} from '@angular/core';
import {Observable} from "rxjs";
import {DataTableResponse} from "../../models/wrapers/data-table.response";
import {PurchaseModel} from "../../models/order/purchase.model";
import {OrderService} from "../../services/order/order.service";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {AppPriceUtil} from "../../app.price-util";

@Component({
	selector: 'app-orders',
	standalone: true,
	templateUrl: './orders.component.html',
	imports: [
		AsyncPipe,
		NgForOf,
		NgIf
	]
})
export class OrdersComponent {

	orders$: Observable<DataTableResponse<PurchaseModel>>;
	page: number = 0;

	constructor(private _orderService: OrderService) {
		this.orders$ = this._orderService.loadAllOrders();
	}

	processPurchase(done: boolean, id: number) {
		this._orderService.processPurchase(done, id).subscribe(() => this.showPage(this.page));
	}

	showPage(page: number): void {
		this.page = page;
		this.orders$ = this._orderService.loadAllOrders(page) as Observable<DataTableResponse<PurchaseModel>>;
	}

	protected readonly AppPriceUtil = AppPriceUtil;
}
