import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CartService} from "./cart.service";
import {Observable, of} from "rxjs";
import {Order} from "../Order";
import {catchError} from "rxjs/operators";
import {UserService} from "./user.service";

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

	private checkoutURL = 'http://localhost:8080/orders'

	httpOptions = {
		headers: new HttpHeaders({'Content-Type': 'application/json'})
	};
  	constructor(public httpClient: HttpClient, public userService: UserService, public cartService: CartService) { }

	checkout() {
		const url = `${this.checkoutURL}/${this.userService.getUser().username}`;
		const cartData = {
			items: this.cartService.getItems(),
			total: this.cartService.getTotalCost()
		}
		// checkout method creates new order and empties the cart
		return this.httpClient.post<Order>(url, cartData, this.httpOptions).pipe(
			catchError(this.handleError<Order>('Error in order placement'))
		).subscribe( () => {
			console.log("Checkout successful");
		});
	}
	private handleError<T>(operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error); // log to console instead
			console.log(`${operation} failed: ${error.message}`);
			// Let the app keep running by returning an empty result.
			return of(result as T);
		};
	}
}
