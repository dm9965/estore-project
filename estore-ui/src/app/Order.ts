import {Shoe} from "./ShoeInterface";
import {CartService} from "./services/cart.service";
import {Observable} from "rxjs";

export class Order {
	username: string = '';
	items: Shoe[] = [];
	totalCost: number = 0;

	constructor(username: string, items: Shoe[], totalCost: number) {
		this.username = username;
		this.items = items;
		this.totalCost = totalCost;
	}

	getUsername(): string {
		return this.username
	}
	getItems(): Shoe[] {
		return this.items;
	}
	getTotalCost(): number {
		return this.totalCost;
	}
}
