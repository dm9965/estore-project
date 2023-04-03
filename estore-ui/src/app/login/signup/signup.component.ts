import { Component } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {User} from "../../User";
import {catchError} from "rxjs/operators";
import {of} from "rxjs";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
	errorMessage: string | null = null;

	form = new FormGroup({
		username: new FormControl(),
		password: new FormControl()
	});

	constructor(private userService: UserService, private router: Router) {
	}

	ngOnInit() {
		this.userService.logout();
	}

	onSubmit() {
		if (this.form.valid) {
			let user = new User();
			user.username = this.form.value.username;
			user.password = this.form.value.password;

			// Login the user if valid credentials, else show error message if observable sends error
			this.userService.createUser(user).pipe(
				catchError((error): any => {
					let message = error.message.toString();
					if (message.includes("404")) {
						this.errorMessage = "Invalid username!";
					} else if (message.includes("401")) {
						this.errorMessage = "Invalid password!";
					} else {
						this.errorMessage = "An error occurred!";
					}
					return of(null);
				})
			).subscribe(
				(user) => {
					if (user) this.router.navigate(['/home']).then();
				});
		}
	}
}
