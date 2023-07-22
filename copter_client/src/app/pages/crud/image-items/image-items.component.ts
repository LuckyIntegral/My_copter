import {Component} from '@angular/core';
import {Observable, of} from "rxjs";
import {DataTableResponse} from "../../../models/data-table.response";
import {ImageFullModel} from "../../../models/image-full.model";
import {ImageCrudService} from "../../../services/product/crud/image.crud.service";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {Router} from "@angular/router";

@Component({
	selector: 'app-image-items',
	standalone: true,
	templateUrl: './image-items.component.html',
	imports: [
		AsyncPipe,
		NgForOf,
		NgIf
	],
	styleUrls: ['./image-items.component.scss']
})
export class ImageItemsComponent {

	images$: Observable<DataTableResponse<ImageFullModel>> = this._imageCrudService.loadAllImages(0, 10);
	isDeleted: Observable<boolean> = of(false);

	constructor(
		private _imageCrudService: ImageCrudService,
		private _router: Router) {
	}

	deleteImage(id: number): void {
		this.isDeleted = this._imageCrudService.deleteImage(id);
		this.showPage(0);
	}

	showPage(page: number, size: number = 10): void {
		this.images$ = this._imageCrudService.loadAllImages(page, size) as Observable<DataTableResponse<ImageFullModel>>;
	}

	redirectToUpdate(id: number) {
		this._router.navigateByUrl('/crud/images/update/' + id);
	}
}
