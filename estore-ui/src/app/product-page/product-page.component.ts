import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Shoe} from "../ShoeInterface";
import {HttpClient} from "@angular/common/http";
import {CartService} from "../services/cart.service";

@Component({
	selector: 'app-product-page',
	templateUrl: './product-page.component.html',
	styleUrls: ['./product-page.component.scss']
})
export class ProductPageComponent implements OnInit {
	@ViewChild('productInfoTemplate', {static: true})
	productInfoTemplate!: TemplateRef<any>;
	availableShoes: Shoe[] = [];
	private shoeURL = 'http://localhost:8080/shoes';

	constructor(private http: HttpClient, private cartService: CartService) {
	}

	addToCart(item: Shoe) {
		this.cartService.addItem(item);
	}

	ngOnInit() {
		this.http.get<Shoe[]>(this.shoeURL).subscribe(data => {
			this.availableShoes = data;
		});
	}
}
