import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {DataTableResponse} from "../../../models/data-table.response";
import {DronePlp} from "../../../models/drone-plp";
import {PlpService} from "../../../services/plp.service";
import {Router} from "@angular/router";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {AppPriceUtil} from "../../../app.price-util";

@Component({
	selector: 'app-category',
	standalone: true,
	templateUrl: './category.component.html',
	imports: [
		AsyncPipe,
		NgForOf,
		NgIf
	],
	styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {

	plpList$: Observable<DataTableResponse<DronePlp>>;

	constructor(private _plpService: PlpService, private _router: Router) {
		this.plpList$ = this._plpService
			.loadAllByCategory(this._router.routerState.snapshot.url.split('/')[3]) as Observable<DataTableResponse<DronePlp>>;
	}

	ngOnInit(): void {
	}

	redirectToPdp(productId: number): void {
		this._router.navigateByUrl('/pdp/' + productId);
	}

	protected readonly AppPriceUtil = AppPriceUtil;
}
