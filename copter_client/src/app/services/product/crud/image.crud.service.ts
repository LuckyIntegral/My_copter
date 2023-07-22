import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {appSettings} from "../../../app.const";
import {DataContainer} from "../../../models/data.container";
import {DataTableResponse} from "../../../models/data-table.response";
import {ImageCrudModel} from "../../../models/image-crud.model";
import {ImageFullModel} from "../../../models/image-full.model";

@Injectable({
	providedIn: 'root'
})
export class ImageCrudService {
	constructor(private _http: HttpClient) {
	}

	deleteImage(id: number): Observable<boolean> {
		return this._http.delete(appSettings.apiManager + '/image/' + id)
			.pipe(
				map(
					res => {
						const data = res as DataContainer;
						return data.data
					}
				)
			);
	}

	updateImage(image: ImageCrudModel, id: number): Observable<boolean> {
		return this._http.put(appSettings.apiManager + '/image/' + id, image)
			.pipe(
				map(
					res => {
						const data = res as DataContainer;
						return data.data
					}
				)
			);
	}

	createImage(image: ImageCrudModel): Observable<boolean> {
		return this._http.post(appSettings.apiManager + '/image', image)
			.pipe(
				map(
					res => {
						const data = res as DataContainer;
						return data.data
					}
				)
			);
	}

	loadImageById(id: number): Observable<ImageFullModel> {
		return this._http.get(appSettings.apiManager + '/image/' + id)
			.pipe(
				map(
					res => {
						const data = res as DataContainer;
						return data.data
					}
				)
			);
	}

	loadAllImages(page: number = 0, size: number = 5): Observable<DataTableResponse<ImageFullModel>> {
		let params: HttpParams = new HttpParams()
			.set('page', page)
			.set('size', size)
		return this._http.get(appSettings.apiManager + '/image', {params})
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