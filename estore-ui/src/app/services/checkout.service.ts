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

	checkout(): Observable<Order> {
		const url = `${this.checkoutURL}`;
		const cartData = {
			username: this.userService.getUser().username,
			items: this.cartService.getItems(),
			totalCost: this.cartService.getTotalCost()
		};
		this.cartService.clearCart();
		console.log ("checkout service log")
		return this.httpClient.post<Order>(url, cartData, this.httpOptions).pipe(
			tap(_ => console.log(`Order placed`)),
			map(obj => (obj as any).items),
			catchError(this.handleError<Order>('Error in order placement'))

		);
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
