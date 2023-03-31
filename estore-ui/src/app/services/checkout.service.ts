import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CartService} from "./cart.service";
import {Observable, of} from "rxjs";
import {Order} from "../Order";
import {Shoe} from "../ShoeInterface";
import {catchError, map, tap} from "rxjs/operators";
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
		const url = `${this.checkoutURL}`;
		const cartData = {
			username: this.userService.getUser().username,
			items: this.cartService.getItems(),
			totalCost: this.cartService.getTotalCost()
		};
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
