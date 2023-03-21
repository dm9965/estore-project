import {Component, OnInit} from '@angular/core';
import {CartService} from "../services/cart.service";
import {Shoe} from "../ShoeInterface";

@Component({
	selector: 'app-shopping-cart',
	templateUrl: './shopping-cart.component.html',
	styleUrls: ['./shopping-cart.component.scss']
})
export class ShoppingCartComponent implements OnInit {

	totalPrice: number = 0;
	items: Shoe[] = [];

	constructor(public cartService: CartService) {
	}

	ngOnInit() {
		this.cartService.getTotalCost().subscribe((totalPrice) => {
			this.totalPrice = totalPrice;
		});
		this.cartService.getItems().subscribe((items) => {
			this.items = items;
		});
	}

	removeItem(shoe: Shoe) {
		this.cartService.removeItem(shoe);

		// re-render the component
		this.ngOnInit();
	}

}
