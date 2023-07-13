import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {PlpService} from "../../services/plp.service";
import {DronePlp} from "../../models/drone-plp";
import {DataTableResponse} from "../../models/data-table.response";
import {AsyncPipe, DecimalPipe, formatNumber, JsonPipe, NgForOf, NgIf} from "@angular/common";
import {AppPriceUtil} from "../../app.price-util";

@Component({
	selector: 'app-plp',
	standalone: true,
	templateUrl: './plp.component.html',
	imports: [
		AsyncPipe,
		NgIf,
		NgForOf
	],
	styleUrls: ['./plp.component.scss']
})
export class PlpComponent implements OnInit {
	plpList$: Observable<DataTableResponse<DronePlp>> = this._plpService.loadAllProducts() as Observable<DataTableResponse<DronePlp>>;

	constructor(private _plpService: PlpService, private _router: Router) {
	}

	ngOnInit(): void {
	}

	redirectToPdp(productId: number): void {
		this._router.navigateByUrl('/pdp/' + productId);
	}

	protected readonly AppPriceUtil = AppPriceUtil;
	protected readonly formatNumber = formatNumber;
}
