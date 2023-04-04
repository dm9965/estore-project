import {Component, OnInit} from '@angular/core';
import {Shoe} from "../ShoeInterface";
import {CartService} from "../services/cart.service";
import {ProductService} from "../services/product.service";
import {ActivatedRoute} from "@angular/router";

@Component({
	selector: 'app-product-page',
	templateUrl: './product-page.component.html',
	styleUrls: ['./product-page.component.scss']
})
export class ProductPageComponent implements OnInit {

	shoe: Shoe = new Shoe();
	query: string = "";

	constructor(
		public productService: ProductService,
		public cartService: CartService,
		public route: ActivatedRoute
	) {
	}

	addToCart(item: Shoe) {
		this.cartService.addItem(item);
	}

	ngOnInit(): void {
		this.route.paramMap.subscribe(params => {
			const shoeId = Number(params.get('id'));
			this.productService.getShoeByID(shoeId).subscribe((data) => {
				this.shoe = data;
			});
		});
	}
}
