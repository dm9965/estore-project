import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Shoe} from "../ShoeInterface";
import {Observable, of} from "rxjs";
import {catchError, map, tap} from "rxjs/operators";
import {UserService} from "./user.service";

@Injectable({
	providedIn: 'root'
})
export class CartService {
	httpOptions = {
		headers: new HttpHeaders({'Content-Type': 'application/json'})
	};
	private cartURL = 'http://localhost:8080/cart';  // URL to web api

	constructor(public http: HttpClient, public userService: UserService) {
	}

	getItems(): Observable<Shoe[]> {
		let user = this.userService.getUser();
		if (user.username == "Anonymous") {
			return of([]);
		}

		const url = `${this.cartURL}/${this.userService.getUser().username}`;
		return this.http.get<Shoe[]>(url).pipe(
			tap(_ => console.log(`Fetched shoes from cart`)),
			map(obj => (obj as any).items),
			catchError(this.handleError<Shoe[]>('getShoes'))
		);
	}

	addItem(item: Shoe) {
		let user = this.userService.getUser();
		if (user.username == "Anonymous") {
			return of([]);
		}

		const url = `${this.cartURL}/${this.userService.getUser().username}`;
		return this.http.post<Shoe>(url, item, this.httpOptions).pipe(
			catchError(this.handleError<Shoe>('addShoe'))
		).subscribe(() => {
			console.log("Added shoe to cart");
		});
	}

	removeItem(item: Shoe) {
		let user = this.userService.getUser();
		if (user.username == "Anonymous") {
			return of([]);
		}

		const url = `${this.cartURL}/${this.userService.getUser().username}`;
		return this.http.delete<Shoe>(url, this.httpOptions).pipe(
			tap(_ => console.log(`Removed shoe id=${item.id}`)),
			catchError(this.handleError<Shoe>('Remove Shoe'))
		);
	}

	getTotalCost(): Observable<number> {
		let user = this.userService.getUser();
		if (user.username == "Anonymous") {
			return of(0);
		}

		return this.getItems().pipe(
			map((shoes: Shoe[]) => {
				return shoes.reduce((prev, curr) => {
					return prev + curr.price;
				}, 0);
			})
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
