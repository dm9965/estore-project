import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../User";
import {Observable} from "rxjs";
import {catchError, map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserService {
	signupURL: string = 'http://localhost:8080/sign-up'
	loginURL: string = 'http://localhost:8080/login'
  	constructor(private http: HttpClient) { }

	signup(user: User): Observable<any> {
		const url = '${this.signupURL}/signup';
		const body = JSON.stringify(user);
		return this.http.post(url, body);
	}

	login(username: String, password: String): Observable<User> {
		const url = '${this.loginURL}/login';
		const body = { username, password };
		return this.http.post(url, body).pipe(map((response: any) => {
			const user = new User();
			user.username = response.username;
			user.password = response.password;
			return user;
		})
		);
	}

}
