import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../User";
import {Observable, of} from "rxjs";
import {catchError, tap} from "rxjs/operators";

@Injectable({
	providedIn: 'root'
})
export class UserService {
	httpOptions = {
		headers: new HttpHeaders({'Content-Type': 'application/json'})
	};
	private user: User = new User();
	loginURL: string = 'http://localhost:8080/user'

	constructor(private http: HttpClient) {
		this.user.username = "Anonymous";
	}

	login(attemptedUser: User): Observable<User> {
		return this.http.post<User>(this.loginURL + "/login", attemptedUser, this.httpOptions).pipe(
			tap((actualUser) => {
				console.log(`[user service] logged in with`, actualUser);
				this.user = actualUser;
			})
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

	isAdmin(): boolean {
		return this.user.username.toLowerCase().includes("admin");
	}

	isLoggedIn(): boolean {
		return this.user.username !== "Anonymous";
	}
}
