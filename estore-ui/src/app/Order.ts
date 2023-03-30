import {Shoe} from "./ShoeInterface";
import {CartService} from "./services/cart.service";
import {Observable} from "rxjs";
import {User} from "./User";

export class Order {
	user: User = new User();
	items: Shoe[] = [];
	totalCost: number = 0;

	constructor(user: User, items: Shoe[], totalCost: number) {
		this.user = user;
		this.items = items;
		this.totalCost = totalCost;
	}

	getUsername(): string {
		return this.user.getUsername()
	}
	getItems(): Shoe[] {
		return this.items;
	}
	getTotalCost(): number {
		return this.totalCost;
	}
}
