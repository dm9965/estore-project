import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Shoe} from "../ShoeInterface";
import {Observable, of} from "rxjs";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class CartService {
	httpOptions = {
		headers: new HttpHeaders({'Content-Type': 'application/json'})
	};
	private cartURL = 'http://localhost:8080/cart';  // URL to web api

	constructor(public http: HttpClient) {}
	items: Shoe[] = [];

	getItems() {
		return this.items;
	}

	addItem(item: Shoe): Observable<Shoe> {
			this.items.push(item);
			const url = `${this.cartURL}/`;
			return this.http.post<Shoe>(url, item, this.httpOptions).pipe(
				tap((shoe: Shoe) => console.log(`added cart w/ id=${shoe.id}`)),
				catchError(this.handleError<Shoe>('addShoe'))
			);
	}

	removeItem(item: Shoe) {
		const index = this.items.indexOf(item);
		if (index > -1) {
			this.items.splice(index, 1);
		}
		const url = `${this.cartURL}/${item.id}`;
		return this.http.delete<Shoe>(url, this.httpOptions).pipe(
			tap(_ => console.log(`Removed shoe id=${item.id}`)),
			catchError(this.handleError<Shoe>('Remove Shoe'))
		);
	}

	getTotalCost(): number {
		let totalCost = 0;
		for (const item of this.items) {
			totalCost += item.price;
		}
		return totalCost;
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
