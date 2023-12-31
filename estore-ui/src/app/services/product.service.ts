import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {Shoe} from "../ShoeInterface";
import {MessageService} from "./message.service";
import {ToastrService} from "ngx-toastr";


@Injectable({providedIn: 'root'})
export class ProductService {

	httpOptions = {
		headers: new HttpHeaders({'Content-Type': 'application/json'})
	};
	private shoeURL = 'http://localhost:8080/shoe';  // URL to web api

	constructor(public http: HttpClient, private messageService: MessageService, private toastr: ToastrService) {
	}

	/** Get all shoes from the server */
	getAllShoes(): Observable<Shoe[]> {
		const url = `${this.shoeURL}/all`;
		return this.http.get<Shoe[]>(url)
			.pipe(
				tap(_ => this.log('fetched shoes')),
				catchError(this.handleError<Shoe[]>('getShoes', []))
			);
	}

	/** GET singular shoe by id. Will 404 if id not found */
	getShoeByID(id: number): Observable<Shoe> {
		const url = `${this.shoeURL}/id/${id}`;
		return this.http.get<Shoe>(url).pipe(
			tap(_ => this.log(`fetched shoe id=${id}`)),
			catchError(this.handleError<Shoe>(`getShoe id=${id}`))
		);
	}

	/* GET shoes whose name contains search term */
	searchShoes(term: string): Observable<Shoe[]> {
		if (!term.trim()) {
			return of([]);
		}
		const url = `${this.shoeURL}/search/?query=${encodeURIComponent(term)}`;
		return this.http.get<Shoe[]>(url).pipe(
			tap(x => x.length ?
				this.log(`found shoes matching "${term}"`) :
				this.log(`no shoes matching "${term}"`)),
			catchError(this.handleError<Shoe[]>('search shoes', []))
		);
	}

	//Save methods //

	/** POST: add a new shoe to the server */
	addShoe(shoe: Shoe): Observable<Shoe> {
		const url = `${this.shoeURL}/`;
		return this.http.post<Shoe>(url, shoe, this.httpOptions).pipe(
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

	/** PATCH: update the shoe on the server */
	updateShoe(shoe: Shoe): Observable<any> {
		const url = `${this.shoeURL}/`;
		return this.http.patch(url, shoe, this.httpOptions).pipe(
			tap(_ => this.log(`updated hero id=${shoe.id}`)),
			catchError(this.handleError<any>('updateShoe'))
		);
	}

	public save(shoes: Shoe[]): Observable<boolean> {
		const url = `${this.shoeURL}/`;
		return this.getAllShoes()
			.pipe(
				tap((shoeArray: Shoe[]) => {
					// Serializes the Java Objects to JSON objects into the file
					// writeValue will thrown an IOException if there is an issue
					// with the file or reading from the file
					return this.http.patch(url, shoeArray, this.httpOptions)
						.pipe(
							tap(_ => console.log(`saved shoes`)),
							catchError(this.handleError<any>('save'))
						)
						.subscribe();
				}),
				catchError(this.handleError<any>('save'))
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
			this.toastr.error(`${operation} failed: ${error.message}, consult developers`);

			// Let the app keep running by returning an empty result.
			return of(result as T);
		};
	}

	/** Log a ProductService message with the MessageService */
	private log(message: string) {
		this.messageService.add('ProductService: ${message}');
	}
}

