import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {AuthData} from "../../models/auth.data";
import {appSettings} from "../../app.const";
import {SessionService} from "./session.service";
import {DataContainer} from "../../models/data.container";

@Injectable({
	providedIn: 'root'
})
export class AuthService {

	constructor(private _http: HttpClient, private _sessionService: SessionService) {
	}

	register(firstname: string, lastname: string, username: string, password: string, roleType: string): Observable<any> {
		const register = {
			firstName: firstname,
			lastName: lastname,
			username: username,
			password: password,
			roleType: roleType
		};
		return this._http.post(appSettings.apiOpen + '/auth/register', register)
			.pipe(
				map(res => {
					return res as AuthData;
				})
			);
	}

	authenticate(username: string, password: string): Observable<any> {
		const auth = {
			username: username,
			password: password,
		};
		return this._http.post(appSettings.apiOpen + '/auth/authenticate', auth)
			.pipe(
				map(res => {
					return res as AuthData;
				})
			);
	}

	getRole(): Observable<string> {
		return this._http.get(appSettings.apiCustomer + '/role')
			.pipe(
				map(res => {
					const data: DataContainer = res as DataContainer;
					return data.data;
				})
			)
	}

	isLoggedIn(): Observable<boolean> {
		return this._sessionService.fromStorage("token")
			.pipe(
				map(token => {
					return !!token;
				})
			);
	}
}
