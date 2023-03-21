import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {Observable} from "rxjs";
import {Shoe} from "../ShoeInterface";

@Component({
	selector: 'app-browse-page',
	templateUrl: './browse-page.component.html',
	styleUrls: ['./browse-page.component.scss']
})
export class BrowsePageComponent implements OnInit {
	shoes: Shoe[] = [];

	constructor(private productService: ProductService) {
	}

	ngOnInit(): void {
		// Get query string from url called 'query'
		const urlParams = new URLSearchParams(window.location.search);
		const query = urlParams.get('query')?.toString() || "";

		this.productService.searchShoes(query).subscribe((data) => {
			this.shoes = data;
		});
	}
}
