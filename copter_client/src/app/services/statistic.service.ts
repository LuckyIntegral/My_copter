import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {EventDateModel} from "../models/event-date.model";
import {appSettings} from "../app.const";
import {DataContainer} from "../models/data.container";
import {DroneActivityModel} from "../models/drone-activity.model";

@Injectable({
	providedIn: 'root'
})
export class StatisticService {

	constructor(private _http: HttpClient) {
	}

	loadDroneStatistic(): Observable<DroneActivityModel> {
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

	loadEventStatistic(): Observable<EventDateModel> {
		return this._http.get(appSettings.apiManager + '/statistic')
			.pipe(
				map(
					res => {
						const data = res as DataContainer;
						return data.data
					}
				)
			);
	}
}