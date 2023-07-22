import {Injectable} from "@angular/core";
import {map, Observable, of} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {appSettings} from "../../app.const";
import {DataContainer} from "../../models/data.container";

@Injectable({
	providedIn: 'root'
})
export class ProductSearchService {
	constructor(private _http: HttpClient) {
	}

	searchProduct(query: string): Observable<any> {
		if (query === '') {
			return of(null);
		}
		return this._http.get(appSettings.apiOpen + '/search?query=' + query)
			.pipe(
				map(res => {
					const data: DataContainer = res as DataContainer;
					return data.data;
				})
			);
	}
}