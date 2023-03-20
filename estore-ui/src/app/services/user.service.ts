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
	private user: User = new User;
	loginURL: string = 'http://localhost:8080/login'
  	constructor(private http: HttpClient) { }
	login(username: String, password: String): Observable<User> {
		const url = '${this.loginURL}/login';
		const body = { username, password };
		return this.http.post(url, body).pipe(map((response: any) => {
			this.user = response;
			return response;
		}));
	}
	getUser(): User {
		return this.user;
	}
}
