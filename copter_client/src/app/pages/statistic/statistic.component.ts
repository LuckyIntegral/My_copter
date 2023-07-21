import {Component} from '@angular/core';
import {StatisticService} from "../../services/statistic.service";
import {Observable} from "rxjs";
import {DataTableResponse} from "../../models/data-table.response";
import {EventDateModel} from "../../models/event-date.model";
import {Router} from "@angular/router";
import {AsyncPipe, DatePipe, NgForOf, NgIf} from "@angular/common";
import {AppDiagramComponent} from "../../components/app-diagram/app-diagram.component";

@Component({
	selector: 'app-statistic',
	standalone: true,
	templateUrl: './statistic.component.html',
	imports: [
		NgForOf,
		AsyncPipe,
		NgIf,
		DatePipe,
		AppDiagramComponent
	],
	styleUrls: ['./statistic.component.scss']
})
export class StatisticComponent {

	eventStatList$: Observable<DataTableResponse<EventDateModel>>;

	constructor(private _statisticService: StatisticService, private _router: Router) {
		this.eventStatList$ = this._statisticService.loadEventStatistic();
	}

	redirectToPdp(productId: number): void {
		this._router.navigateByUrl('/pdp/' + productId);
	}

	showPage(page: number): void {
		this.eventStatList$ = this._statisticService.loadEventStatistic(page) as Observable<DataTableResponse<EventDateModel>>;
	}
}
