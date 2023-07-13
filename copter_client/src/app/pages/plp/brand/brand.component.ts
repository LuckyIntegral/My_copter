import { Component } from '@angular/core';
import {Observable} from "rxjs";
import {DataTableResponse} from "../../../models/data-table.response";
import {DronePlp} from "../../../models/drone-plp";
import {PlpService} from "../../../services/plp.service";
import {Router} from "@angular/router";
import {AsyncPipe, formatNumber, NgForOf, NgIf} from "@angular/common";
import {AppPriceUtil} from "../../../app.price-util";

@Component({
  selector: 'app-brand',
  standalone: true,
  templateUrl: './brand.component.html',
  imports: [
    AsyncPipe,
    NgForOf,
    NgIf
  ],
  styleUrls: ['./brand.component.scss']
})
export class BrandComponent {
  plpList$: Observable<DataTableResponse<DronePlp>>;

  constructor(private _plpService: PlpService, private _router: Router) {
    this.plpList$ = this._plpService
        .loadAllByBrand(this._router.routerState.snapshot.url.split('/')[3]) as Observable<DataTableResponse<DronePlp>>;
  }

  redirectToPdp(productId: number): void {
    this._router.navigateByUrl('/pdp/' + productId);
  }

  protected readonly AppPriceUtil = AppPriceUtil;
  protected readonly formatNumber = formatNumber;
}
