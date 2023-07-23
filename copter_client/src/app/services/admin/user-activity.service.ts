import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {catchError, map, Observable, of} from "rxjs";
import {DataTableResponse} from "../../models/wrapers/data-table.response";
import {UserActivityModel} from "../../models/admin/user-activity.model";
import {appSettings} from "../../app.const";
import {DataContainer} from "../../models/wrapers/data.container";

@Injectable({
	providedIn: 'root'
})
export class UserActivityService {

	constructor(private _http: HttpClient) {
	}

	disableUser(id: number): Observable<boolean> {
		return this.process('/disable', id);
	}

	enableUser(id: number): Observable<boolean> {
		return this.process('/enable', id);
	}

	process(url: string, id: number): Observable<boolean> {
		return this._http.put(appSettings.apiAdmin + '/activity' + url + '/' + id, null)
			.pipe(
				map(res => {
					const data = res as DataContainer;
					return data.data
				}),
				catchError(() => of(false))
			)
	}

	loadAllUserActivity(page: number = 0, size: number = 10): Observable<DataTableResponse<UserActivityModel>> {
		const params: HttpParams = new HttpParams()
			.set('page', page)
			.set('size', size)
		return this._http.get(appSettings.apiAdmin + '/activity', {params})
			.pipe(
				map(res => {
					const data = res as DataContainer;
					return data.data
				})
			)
	}
}