import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {EventStatModel} from "../../../models/statistic/event-stat.model";
import {appSettings} from "../../../app.const";
import {DataContainer} from "../../../models/wrapers/data.container";
import {DroneStatModel} from "../../../models/statistic/drone-stat.model";
import {DataTableResponse} from "../../../models/wrapers/data-table.response";

@Injectable({
	providedIn: 'root'
})
export class StatisticService {

	constructor(private _http: HttpClient) {
	}

	loadDroneStatistic(): Observable<DroneStatModel[]> {
		return this._http.get(appSettings.apiManager + '/statistic/products')
			.pipe(
				map(
					res => {
						const data = res as DataContainer;
						return data.data
					}
				)
			);
	}

	loadEventStatistic(page: number = 0, size: number = 8): Observable<DataTableResponse<EventStatModel>> {
		const params: HttpParams = new HttpParams()
			.set('page', page)
			.set('size', size)
		return this._http.get(appSettings.apiManager + '/statistic', {params})
			.pipe(
				map(res => {
						const data = res as DataContainer;
						return data.data
					}
				)
			);
	}
}