import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CartService} from "./cart.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

	private checkoutURL = 'http://localhost:8080/checkout'

	httpOptions = {
		headers: new HttpHeaders({'Content-Type': 'application/json'})
	};
  	constructor(public httpClient: HttpClient, public cartService: CartService) { }

	checkout(): void {
		this.cartService.getItems().subscribe(items => {
			const totalPrice = this.cartService.getTotalCost();
			this.cartService.clearCart();
		});
	}
}
