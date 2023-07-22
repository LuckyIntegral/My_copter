import {Component, OnInit} from '@angular/core';
import {DroneActivityModel} from "../../models/drone-activity.model";
import {StatisticService} from "../../services/product/statistic.service";
import {Router} from "@angular/router";
import {CanvasJSAngularChartsModule} from "@canvasjs/angular-charts";
import {NgIf} from "@angular/common";

export interface ChartDataPoint {
	y: number;
	name: string;
}

@Component({
	selector: 'app-diagram',
	standalone: true,
	templateUrl: './app-diagram.component.html',
	imports: [
		CanvasJSAngularChartsModule,
		NgIf
	],
	styleUrls: ['./app-diagram.component.scss']
})
export class AppDiagramComponent implements OnInit {

	chartOptions;

	constructor(private _statisticService: StatisticService, private _router: Router) {
		this.chartOptions = {
			animationEnabled: true,
			theme: "dark2",
			title: {
				text: "The popularity of drones"
			},
			data: [{
				type: "doughnut",
				indexLabel: "{name}",
				dataPoints: [] as ChartDataPoint[]
			}]
		};
	}

	async ngOnInit(): Promise<void> {
		await this.updateChart();
	}

	async updateChart(): Promise<void> {
		this.chartOptions.data[0].dataPoints = await this.fillChart();
	}

	async fillChart(): Promise<ChartDataPoint[]> {
		const models: DroneActivityModel[] | undefined = await this._statisticService.loadDroneStatistic().toPromise();
		if (models === undefined) {
			return [];
		}
		return models.map((item: DroneActivityModel) => ({
			y: item.activity,
			name: item.event
		}));
	}
}
