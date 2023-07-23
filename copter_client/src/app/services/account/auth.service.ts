import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {catchError, map, Observable, of} from "rxjs";
import {AuthData} from "../../models/account/auth.data";
import {appSettings} from "../../app.const";
import {SessionService} from "./session.service";
import {DataContainer} from "../../models/wrapers/data.container";

@Injectable({
	providedIn: 'root'
})
export class AuthService {

	constructor(private _http: HttpClient, private _sessionService: SessionService) {
	}

	register(firstname: string, lastname: string, username: string, password: string, roleType: string): Observable<AuthData | Boolean> {
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
				}),
				catchError(() => of(false))
			);
	}

	authenticate(username: string, password: string): Observable<AuthData | Boolean> {
		const auth = {
			username: username,
			password: password,
		};
		return this._http.post(appSettings.apiOpen + '/auth/authenticate', auth)
			.pipe(
				map(res => {
					return res as AuthData;
				}),
				catchError(() => of(false))
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
