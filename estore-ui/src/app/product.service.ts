import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Shoe } from "./Shoe";
import { MessageService} from "./message.service";


@Injectable({ providedIn: 'root' })
export class ProductService {

	private shoeURL = 'http://localhost:8080/shoes';  // URL to web api

	httpOptions = {
		headers: new HttpHeaders({ 'Content-Type': 'application/json' })
	};

	constructor(private http: HttpClient, private messageService: MessageService) { }

	/** Get all shoes from the server */
	getShoes(): Observable<Shoe[]> {
		return this.http.get<Shoe[]>(this.shoeURL)
			.pipe(
				tap(_ => this.log('fetched heroes')),
				catchError(this.handleError<Shoe[]>('getShoes', []))
			);
	}

	/** Get shoe by id. Return `undefined` when id not found */
	getError<Data>(id: number): Observable<Shoe> {
		const url = `${this.shoeURL}/?id=${id}`;
		return this.http.get<Shoe[]>(url)
			.pipe(
				map(heroes => heroes[0]), // returns a {0|1} element array
				tap(h => {
					const outcome = h ? 'fetched' : 'did not find';
					this.log(`${outcome} shoe id=${id}`);
				}),
				catchError(this.handleError<Shoe>(`getShoe id=${id}`))
			);
	}

	/** GET singular shoe by id. Will 404 if id not found */
	getShoeByID(id: number): Observable<Shoe> {
		const url = `${this.shoeURL}/${id}`;
		return this.http.get<Shoe>(url).pipe(
			tap(_ => this.log(`fetched hero id=${id}`)),
			catchError(this.handleError<Shoe>(`getShoe id=${id}`))
		);
	}

	getShoeBySizing(sizing: Sizing): Observable<Shoe> {
		const url = `${this.shoeURL}/${sizing}`;
		return this.http.get<Shoe>(url).pipe(
			tap(_ => this.log(`fetched hero id=${sizing}`)),
			catchError(this.handleError<Shoe>(`getShoe sizing=${sizing}`))
		);
	}

	getShoeBySize(size: number): Observable<Shoe> {
		const url = `${this.shoeURL}/${size}`;
		return this.http.get<Shoe>(url).pipe(
			tap(_ => this.log(`fetched hero id=${size}`)),
			catchError(this.handleError<Shoe>(`getShoe size=${size}`))
		);
	}

	getShoeByColor(color: string): Observable<Shoe> {
		const url = `${this.shoeURL}/${color}`;
		return this.http.get<Shoe>(url).pipe(
			tap(_ => this.log(`fetched hero color=${color}`)),
			catchError(this.handleError<Shoe>(`getShoe color=${color}`))
		);
	}

	getShoeByBrand(brand: string): Observable<Shoe> {
		const url = `${this.shoeURL}/${brand}`;
		return this.http.get<Shoe>(url).pipe(
			tap(_ => this.log(`fetched hero id=${brand}`)),
			catchError(this.handleError<Shoe>(`getShoe brand=${brand}`))
		);
	}

	getShoeByPrice(price: number): Observable<Shoe> {
		const url = `${this.shoeURL}/${price}`;
		return this.http.get<Shoe>(url).pipe(
			tap(_ => this.log(`fetched hero price=${price}`)),
			catchError(this.handleError<Shoe>(`getShoe price=${price}`))
		);
	}

	/* GET shoes whose name contains search term */
	searchShoes(term: string): Observable<Shoe[]> {
		if (!term.trim()) {
			// if not search term, return empty hero array.
			return of([]);
		}
		return this.http.get<Shoe[]>(`${this.shoeURL}/?name=${term}`).pipe(
			tap(x => x.length ?
				this.log(`found shoes matching "${term}"`) :
				this.log(`no shoes matching "${term}"`)),
			catchError(this.handleError<Shoe[]>('searchHeroes', []))
		);
	}

	//Save methods //

	/** POST: add a new shoe to the server */
	addShoe(shoe: Shoe): Observable<Shoe> {
		return this.http.post<Shoe>(this.shoeURL, shoe, this.httpOptions).pipe(
			tap((newShoe: Shoe) => this.log(`added shoe w/ id=${newShoe.id}`)),
			catchError(this.handleError<Shoe>('addShoe'))
		);
	}

	/* DELETE: delete the shoe from the server */
	deleteShoe(id: number): Observable<Shoe> {
		const url = `${this.shoeURL}/${id}`;

		return this.http.delete<Shoe>(url, this.httpOptions).pipe(
			tap(_ => this.log(`deleted hero id=${id}`)),
			catchError(this.handleError<Shoe>('deleteShoe'))
		);
	}

	/** PUT: update the shoe on the server */
	updateShoe(shoe: Shoe): Observable<any> {
		return this.http.put(this.shoeURL, shoe, this.httpOptions).pipe(
			tap(_ => this.log(`updated hero id=${shoe.id}`)),
			catchError(this.handleError<any>('updateShoe'))
		);
	}

	/**
	 * Handle Http operation that failed.
	 * Let the app continue.
	 *
	 * @param operation - name of the operation that failed
	 * @param result - optional value to return as the observable result
	 */
	private handleError<T>(operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error); // log to console instead
			this.log(`${operation} failed: ${error.message}`);

			// Let the app keep running by returning an empty result.
			return of(result as T);
		};
	}

	/** Log a ProductService message with the MessageService */
	private log(message: string) {
		this.messageService.add('ProductService: ${message}');
	}
}

