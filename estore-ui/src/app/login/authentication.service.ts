import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
	login(email: string, password: string): boolean {
		return email === 'youremail@email.com' && password === 'password';
	}
  constructor() { }
}
