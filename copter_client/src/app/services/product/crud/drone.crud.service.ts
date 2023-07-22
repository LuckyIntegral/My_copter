import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {DataTableResponse} from "../../../models/wrapers/data-table.response";
import {DroneCrudModel} from "../../../models/product/drone-crud.model";
import {appSettings} from "../../../app.const";
import {DataContainer} from "../../../models/wrapers/data.container";
import {DroneFullModel} from "../../../models/product/drone-full.model";

@Injectable({
	providedIn: 'root'
})
export class DroneCrudService {

	constructor(private _http: HttpClient) {
	}

	deleteDrone(id: number): Observable<boolean> {
		return this._http.delete(appSettings.apiManager + '/copter/' + id)
			.pipe(
				map(
					res => {
						const data = res as DataContainer;
						return data.data
					}
				)
			);
	}

	updateDrone(drone: DroneCrudModel, id: number): Observable<boolean> {
		return this._http.put(appSettings.apiManager + '/copter/' + id, drone)
			.pipe(
				map(
					res => {
						const data = res as DataContainer;
						return data.data
					}
				)
			);
	}

	createDrone(drone: DroneCrudModel): Observable<boolean> {
		return this._http.post(appSettings.apiManager + '/copter', drone)
			.pipe(
				map(
					res => {
						const data = res as DataContainer;
						return data.data
					}
				)
			);
	}

	loadDroneById(id: number): Observable<DroneFullModel> {
		return this._http.get(appSettings.apiManager + '/copter/' + id)
			.pipe(
				map(
					res => {
						const data = res as DataContainer;
						return data.data
					}
				)
			);
	}

	loadAllDrones(page: number = 0, size: number = 5): Observable<DataTableResponse<DroneFullModel>> {
		let params: HttpParams = new HttpParams()
			.set('page', page)
			.set('size', size)
		return this._http.get(appSettings.apiManager + '/copter', {params})
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