import {Component, OnInit} from '@angular/core';
import {User} from "../User";
import {UserService} from "../services/user.service";
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

	errorMessage: String = 'Login Error';
	user: User = new User();
	ngOnInit() {}
	constructor(private userService: UserService, private router: Router) {
	}

	onSubmit(form: NgForm) {
		if (form.valid) {
			this.user = new User();
			this.user.username = form.value.username;
			this.user.password = form.value.password;

			this.userService.createUser(this.user).subscribe(
				(response: any) => {
					console.log(response);
					if (response === 'login successful') {
						this.router.navigate(['/home']).then(r => true);
					} else {
						console.log('Error logging in: ', response);
						this.errorMessage = response;
					}
				},
				(error) => {
					console.log('Error logging in: ', error);
					this.errorMessage = error;
				}
			);
			this.userService.login(this.user);
		}
	}

	validateUsername(): boolean {
		return this.user.getUsername() !== null;
	}

	validatePassword(): boolean {
		return this.user.getPassword() !== null;
	}
}
