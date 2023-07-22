import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {BehaviorSubject, Observable, take} from "rxjs";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {ProductInfoModel} from "../../models/product/product-info.model";
import {ProductSearchService} from "../../services/product/search/product-search.service";

@Component({
	selector: 'app-search',
	standalone: true,
	templateUrl: './app-search.component.html',
	imports: [
		NgForOf,
		ReactiveFormsModule,
		AsyncPipe,
		NgIf
	],
	styleUrls: ['./app-search.component.scss']
})
export class AppSearchComponent implements OnInit {

	private productsSub$ = new BehaviorSubject<ProductInfoModel[]>([]);
	products$: Observable<ProductInfoModel[]> = this.productsSub$.asObservable();
	queryForm: FormGroup = new FormGroup({
		"query": new FormControl()
	});

	constructor(private _productSearchService: ProductSearchService, private _router: Router) {
	}

	ngOnInit(): void {
		this.queryForm.valueChanges
			.subscribe(value => {
				this._productSearchService
					.searchProduct(value.query)
					.pipe(
						take(1),
					)
					.subscribe(res => {
						let productInfoModelList: ProductInfoModel[] = res as ProductInfoModel[];
						this.productsSub$.next(productInfoModelList);
					});
			});
	}

	navigateToPdp(id: number) {
		this._router.navigateByUrl('/pdp/' + id);
	}
}
