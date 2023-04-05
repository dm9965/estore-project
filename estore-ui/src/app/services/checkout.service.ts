import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CartService} from "./cart.service";
import {Observable, of} from "rxjs";
import {Order} from "../Order";
import {catchError, tap} from "rxjs/operators";
import {UserService} from "./user.service";
import {ToastrService} from "ngx-toastr";
import {Shoe} from "../ShoeInterface";

@Injectable({
	providedIn: 'root'
})
export class CheckoutService {

	httpOptions = {
		headers: new HttpHeaders({'Content-Type': 'application/json'})
	};
	private checkoutURL = 'http://localhost:8080/orders'

	constructor(public httpClient: HttpClient, public userService: UserService, public cartService: CartService, private toastr: ToastrService) {
	}

	getAllOrders(): Observable<Order[]> {
		const url = `${this.checkoutURL}/`;
		return this.httpClient.get<Order[]>(url).pipe(
			tap(_ => console.log('Orders successfully pulled')),
			catchError(this.handleError<Order[]>('getAllOrders', []))
		);
	}

	getOrderItems(): Observable<Shoe[]> {
		const url = `${this.checkoutURL}/${this.userService.getUser().username}`;
		return this.httpClient.get<Shoe[]>(url).pipe(
			tap(_ => console.log('Pulled items for customer order')),
			catchError(this.handleError<Shoe[]>('get order items'))
		);
	}

	checkout() {
		const url = `${this.checkoutURL}/${this.userService.getUser().username}`;
		const cartData = {
			items: this.cartService.getItems(),
			total: this.cartService.getTotalCost()
		}
		// checkout method creates new order and empties the cart
		return this.httpClient.post<Order>(url, cartData, this.httpOptions).pipe(
			catchError(this.handleError<Order>('Error in order placement'))
		).subscribe(order => {
			console.log(`Checkout successful: ${order.totalCost}`);
			this.toastr.success(`Checkout successful! Total cost: ${order.totalCost}`);
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
