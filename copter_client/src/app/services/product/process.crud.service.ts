import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {catchError, map, Observable, of} from "rxjs";
import {appSettings} from "../../app.const";
import {DataContainer} from "../../models/data.container";

@Injectable({
	providedIn: 'root'
})
export class ProcessCrudService {

	constructor(private _http: HttpClient) {
	}

	attachPictureToDrone(droneId: number, imageId: number): Observable<boolean> {
		return this.process(droneId, imageId, '/attach')
	}

	detachPictureToDrone(droneId: number, imageId: number): Observable<boolean> {
		return this.process(droneId, imageId, '/detach')
	}

	process(droneId: number, imageId: number, method: string): Observable<boolean> {
		const dto = {
			"copterId": droneId,
			"imageId": imageId
		}
		return this._http.put(appSettings.apiManager + '/process' + method, dto)
			.pipe(
				map(res => {
					const data = res as DataContainer;
					return data.data;
				}),
				catchError(() => of(false))
			);
	}
}