import { Component } from '@angular/core';
import {AppSearchComponent} from "../../components/app-search/app-search.component";

@Component({
  selector: 'app-header',
  standalone: true,
  templateUrl: './header.component.html',
  imports: [
    AppSearchComponent
  ],
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

}
