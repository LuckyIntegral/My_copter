import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {appSettings} from "../../app.const";
import {DataContainer} from "../../models/wrapers/data.container";
import {DataTableResponse} from "../../models/wrapers/data-table.response";
import {PurchaseModel} from "../../models/order/purchase.model";

@Injectable({
	providedIn: 'root'
})
export class OrderService {

	constructor(private _http: HttpClient) {
	}

	processPurchase(done: boolean, id: number): Observable<boolean> {
		return this._http.put(appSettings.apiCustomer + '/order/' + id, done)
			.pipe(
				map(res => {
					const data = res as DataContainer
					return data.data
				})
			)
	}

	loadAllOrders(page: number = 0, size: number = 4): Observable<DataTableResponse<PurchaseModel>> {
		const params: HttpParams = new HttpParams()
			.set('page', page)
			.set('size', size)
		return this._http.get(appSettings.apiCustomer + '/order', {params})
			.pipe(
				map(res => {
					const data = res as DataContainer;
					return data.data;
				})
			)
	}

	makePurchase(contact: string, address: string, cartId: number): Observable<boolean> {
		const body = {
			contact: contact,
			address: address,
			cartId: cartId
		};
		return this._http.post(appSettings.apiCustomer + '/order', body)
			.pipe(
				map(res => {
					const data = res as DataContainer;
					return data.data
				})
			)
	}
}