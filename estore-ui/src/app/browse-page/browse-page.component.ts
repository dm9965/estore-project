import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {Shoe} from "../ShoeInterface";
import {Router} from "@angular/router";
import {CartService} from "../services/cart.service";
import {UserService} from "../services/user.service";

@Component({
	selector: 'app-browse-page',
	templateUrl: './browse-page.component.html',
	styleUrls: ['./browse-page.component.scss']
})
export class BrowsePageComponent implements OnInit {
	shoes: Shoe[] = [];
	shoe: Shoe = new Shoe();
	query: string = "";

	constructor(private productService: ProductService, private router: Router, private cartService: CartService, private userService: UserService) {
		router.events.subscribe(() => {
			this.ngOnInit();
		})
	}

	ngOnInit(): void {
		// Get query string from url called 'query'
		const urlParams = new URLSearchParams(window.location.search);
		this.query = urlParams.get('query')?.toString() || "";

		if (this.productService.searchShoes("All")) {
			this.productService.getAllShoes().subscribe((data) => {
				this.shoes = data;
			});
		}

		this.productService.searchShoes(this.query).subscribe((data) => {
			this.shoes = data;
		});
	}

	addToCart(shoe: Shoe) {
		console.log("ADDED TO CART")
		this.cartService.addItem(shoe);
	}

	isLoggedIn() {
		return this.userService.isLoggedIn();
	}

	goToProductPage(): void {
		this.router.navigateByUrl(`/product-page/${this.shoe.id}`);
	}
}
