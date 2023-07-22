import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {DataContainer} from "../../../models/data.container";
import {map, Observable} from "rxjs";
import {appSettings} from "../../../app.const";
import {DataTableResponse} from "../../../models/data-table.response";
import {DronePlp} from "../../../models/drone-plp";

@Injectable({
	providedIn: 'root'
})
export class PlpService {
	constructor(private _http: HttpClient) {
	}

	loadAllProducts(brand: string = 'all', category: string = 'all', page: number = 0, size: number = 6)
		: Observable<DataTableResponse<DronePlp>> {
		let params: HttpParams = new HttpParams()
			.set('page', page)
			.set('size', size)
		if (brand !== 'all') {
			params = params.set('brand', brand)
		}
		if (category !== 'all') {
			params = params.set('category', category)
		}
		return this._http.get(appSettings.apiOpen + '/plp', {params})
			.pipe(
				map(res => {
					const data: DataContainer = res as DataContainer;
					return data.data;
				})
			);
	}
}