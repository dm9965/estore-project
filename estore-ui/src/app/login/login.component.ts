import {Component, OnInit, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
	@ViewChild('emailInput') emailInput: ElementRef | undefined;
	@ViewChild('passwordInput') passwordInput: ElementRef | undefined;

	email: string = '';
	password: string = '';
	emailValid: boolean = true;
	passwordValid: boolean = true;

	constructor() { }

	ngOnInit() {
	}

	onSubmit() {
		this.emailValid = this.validateEmail();
		this.passwordValid = this.validatePassword();
		if (this.emailValid && this.passwordValid) {
			// handle successful login
		}
	}

	private validateEmail(): boolean {
		const emailRegex = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/;
		// @ts-ignore
		const email = this.emailInput.nativeElement.value.trim();
		return emailRegex.test(email);
	}

	private validatePassword(): boolean {
		// @ts-ignore
		const password = this.passwordInput.nativeElement.value.trim();
		return password.trim().length > 0;
	}
}
