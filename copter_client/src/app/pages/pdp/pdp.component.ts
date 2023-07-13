import { Component } from '@angular/core';
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {DronePdp} from "../../models/drone-pdp";
import {PdpService} from "../../services/pdp.service";
import {AppPriceUtil} from "../../app.price-util";

@Component({
  selector: 'app-pdp',
  standalone: true,
  templateUrl: './pdp.component.html',
	imports: [
		AsyncPipe,
		NgIf,
		NgForOf
	],
  styleUrls: ['./pdp.component.scss']
})
export class PdpComponent {

  pdpDrone$: Observable<DronePdp>;

  constructor(private _pdpService: PdpService, private _router: Router) {
    this.pdpDrone$ = this._pdpService
        .loadDronePdp(parseInt(this._router.routerState.snapshot.url.split('/')[2])) as Observable<DronePdp>;
  }

	protected readonly AppPriceUtil = AppPriceUtil;
}
