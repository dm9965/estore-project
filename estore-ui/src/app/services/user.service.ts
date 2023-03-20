import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../User";
import {Observable, of} from "rxjs";
import {Shoe} from "../ShoeInterface";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserService {
	httpOptions = {
		headers: new HttpHeaders({'Content-Type': 'application/json'})
	};
	private user: User = new User;
	loginURL: string = 'http://localhost:8080/user/login'

	constructor(private http: HttpClient) {
	}

	login(user: User): Observable<User> {
		console.log(user)
		return this.http.post<User>(this.loginURL, user, this.httpOptions).pipe(
			tap((user) => console.log(`created new user w/ username ${this.user.username}`)),
			catchError(this.handleError<User>('login'))
		);
	}

	createUser(user: User): Observable<User> {
		return this.http.post<User>(this.loginURL, user, this.httpOptions).pipe(
			tap((user) => console.log(`created new user w/ username ${this.user.username}`)),
			catchError(this.handleError<User>('createUser'))
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

	getUser(): User {
		return this.user;
	}
}
