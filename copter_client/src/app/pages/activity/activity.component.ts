import {Component} from '@angular/core';
import {Observable} from "rxjs";
import {UserActivityModel} from "../../models/admin/user-activity.model";
import {DataTableResponse} from "../../models/wrapers/data-table.response";
import {UserActivityService} from "../../services/admin/user-activity.service";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";

@Component({
	selector: 'app-activity',
	standalone: true,
	templateUrl: './activity.component.html',
	imports: [
		NgIf,
		AsyncPipe,
		NgForOf
	],
	styleUrls: ['./activity.component.scss']
})
export class ActivityComponent {

	users$: Observable<DataTableResponse<UserActivityModel>>;
	page: number = 0;

	constructor(private _userActivityService: UserActivityService) {
		this.users$ = this._userActivityService.loadAllUserActivity()
	}

	enableUser(id: number) {
		this._userActivityService.enableUser(id).subscribe(() => this.showPage(this.page));
	}

	disableUser(id: number) {
		this._userActivityService.disableUser(id).subscribe(() => this.showPage(this.page));
	}

	showPage(page: number): void {
		this.page = page;
		this.users$ = this._userActivityService.loadAllUserActivity(page);
	}
}
