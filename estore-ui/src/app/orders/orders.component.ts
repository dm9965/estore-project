import {Component} from '@angular/core';
import {Order} from "../Order";
import {CheckoutService} from "../services/checkout.service";

@Component({
	selector: 'app-orders',
	templateUrl: './orders.component.html',
	styleUrls: ['./orders.component.scss']
})
export class OrdersComponent {

	orders: Order[] = [];

	constructor(private checkoutService: CheckoutService) {
	}

	ngOnInit(): void {
		this.getAllOrders();
	}

	getAllOrders(): void {
		this.checkoutService.getAllOrders().subscribe(orders => this.orders = orders);
	}
}
