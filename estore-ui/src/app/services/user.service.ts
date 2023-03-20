import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../User";
import {Observable, of} from "rxjs";
import {catchError, map, tap} from "rxjs/operators";
import {Shoe} from "../ShoeInterface";

@Injectable({
  providedIn: 'root'
})
export class UserService {
	httpOptions = {
		headers: new HttpHeaders({'Content-Type': 'application/json'})
	};
	private user: User = new User;
	loginURL: string = 'http://localhost:8080/user/login'
  	constructor(private http: HttpClient) { }
	login(username: String, password: String): Observable<User> {
		return this.http.post(this.loginURL, username, this.httpOptions).pipe(map((response: any) => {
			this.user = response;
			return response;
		}));
	}
	getUser(): User {
		return this.user;
	}
}
