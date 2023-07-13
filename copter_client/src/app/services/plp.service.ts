import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { DataContainer } from "../models/data.container";
import { map, Observable } from "rxjs";
import { appSettings } from "../app.const";

@Injectable({
	providedIn: 'root'
})
export class PlpService {
	constructor(private _http: HttpClient) {}

	loadAllProducts(): Observable<any> {
		return this._http.get(appSettings.apiOpen + '/plp')
			.pipe(
				map(res => {
					const data: DataContainer = res as DataContainer;
					return data.data;
				})
			);
	}

	loadAllByBrand(brand: string): Observable<any>{
		return this._http.get(appSettings.apiOpen + '/plp?brand=' + brand)
			.pipe(
				map(res => {
					const data: DataContainer = res as DataContainer;
					return data.data;
				})
			);
	}

	loadAllByCategory(category: string): Observable<any>{
		return this._http.get(appSettings.apiOpen + '/plp?category=' + category)
			.pipe(
				map(res => {
					const data: DataContainer = res as DataContainer;
					return data.data;
				})
			);
	}
}