import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {DronePdp} from "../../../models/product/drone-pdp";
import {appSettings} from "../../../app.const";
import {map, Observable} from "rxjs";
import {DataContainer} from "../../../models/wrapers/data.container";

@Injectable({
	providedIn: 'root'
})
export class PdpService {

	constructor(private _httpClient: HttpClient) {
	}

	loadDronePdp(id: number): Observable<DronePdp> {
		return this._httpClient.get(appSettings.apiOpen + '/pdp/' + id)
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