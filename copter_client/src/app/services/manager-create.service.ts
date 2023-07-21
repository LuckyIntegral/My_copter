import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {catchError, map, Observable, of} from "rxjs";
import {appSettings} from "../app.const";
import {DataContainer} from "../models/data.container";

@Injectable({
	providedIn: 'root'
})
export class ManagerCreateService {

	constructor(private _http: HttpClient) {
	}

	createManager(firstname: string, lastname: string, username: string, password: string, roleType: string): Observable<boolean> {
		const register = {
			firstname: firstname,
			lastname: lastname,
			username: username,
			password: password,
			roleType: roleType
		};
		return this._http.post(appSettings.apiAdmin + '/personal', register)
			.pipe(
				map(res => {
					const data = res as DataContainer;
					return data.data;
				}),
				catchError(() => of(false))
			);
	}
}