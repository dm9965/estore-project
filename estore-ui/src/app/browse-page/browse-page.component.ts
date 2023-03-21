import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {Shoe} from "../ShoeInterface";
import {Router} from "@angular/router";

@Component({
	selector: 'app-browse-page',
	templateUrl: './browse-page.component.html',
	styleUrls: ['./browse-page.component.scss']
})
export class BrowsePageComponent implements OnInit {
	shoes: Shoe[] = [];
	query: string = "";

	constructor(private productService: ProductService, private router: Router) {
		router.events.subscribe(() => {
			this.ngOnInit();
		})
	}

	ngOnInit(): void {
		// Get query string from url called 'query'
		const urlParams = new URLSearchParams(window.location.search);
		this.query = urlParams.get('query')?.toString() || "";

		this.productService.searchShoes(this.query).subscribe((data) => {
			this.shoes = data;
		});
	}
}
