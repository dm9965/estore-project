import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../User";
import {Observable, of} from "rxjs";

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
	login(username: string, password: string): Observable<User> {
		console.log(username)
		return this.http.post<User>(this.loginURL, username);
	}

	getUser(): User {
		return this.user;
	}
}
