import { Component } from '@angular/core';
import {Shoe} from "../ShoeInterface";
import {CartService} from "../services/cart.service";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.scss']
})
export class ShoppingCartComponent {
	itemsInCart: Shoe[] = [];
	totalCost: number = 0;

	constructor(private cartService: CartService) {
		this.itemsInCart = cartService.getItems();
		this.totalCost = cartService.getTotalCost(this.itemsInCart);
	}

	removeItem(item: Shoe) {
		this.cartService.removeItem(item);
	}

	checkout(){

	}

}
