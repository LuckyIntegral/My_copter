import {Component} from '@angular/core';
import {RouterOutlet} from "@angular/router";
import {HeaderComponent} from "./layout/header/header.component";
import {FooterComponent} from "./layout/footer/footer.component";

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	standalone: true,
	imports: [
		RouterOutlet,
		HeaderComponent,
		FooterComponent
	],
	styleUrls: ['./app.component.scss']
})
export class AppComponent {
	title = 'copter_client';
}
