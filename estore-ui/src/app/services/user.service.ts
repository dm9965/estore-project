import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../User";
import {Observable, of} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {ToastrService} from "ngx-toastr";

@Injectable({
	providedIn: 'root'
})
export class UserService {
	httpOptions = {
		headers: new HttpHeaders({'Content-Type': 'application/json'})
	};
	loginURL: string = 'http://localhost:8080/user'
	private user: User = new User();

	constructor(private http: HttpClient, private toastr: ToastrService) {
		console.log("Started user script");
		if (this.getCookie("username")) {
			this.user.username = this.getCookie("username") || "";
		} else {
			this.user.username = "Anonymous";
		}
	}

	login(attemptedUser: User): Observable<User> {
		return this.http.post<User>(this.loginURL + "/login", attemptedUser, this.httpOptions).pipe(
			tap((actualUser) => {
				console.log(`[user service] logged in with`, actualUser);
				this.user = actualUser;
				this.setCookie("username", actualUser.username);
				this.toastr.success(`Logged in successfully as ${actualUser.username}`);
			})
		);
	}

	logout(): void {
		if (!this.isLoggedIn()) {
			this.toastr.success(`Logged out successfully`);
		}
		this.user = new User();
		this.user.username = "Anonymous";
		this.setCookie("username", "");
	}

	createUser(user: User): Observable<User> {
		return this.http.post<User>(this.loginURL, user, this.httpOptions).pipe(
			tap((user) => console.log(`created new user w/ username ${this.user.username}`)),
			catchError(this.handleError<User>('createUser'))
		);
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

	private handleError<T>(operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error); // log to console instead
			console.log(`${operation} failed: ${error.message}`);

			// Let the app keep running by returning an empty result.
			return of(result as T);
		};
	}

	private getCookie(name: string): string | undefined {
		let value = "; " + document.cookie;
		let parts = value.split("; " + name + "=") || "";
		if (parts.length == 2) return parts?.pop()?.split(";")?.shift() || undefined;
		return undefined;
	}

	private setCookie(name: string, value: string) {
		document.cookie = name + "=" + value + "; path=/";
	}
}
