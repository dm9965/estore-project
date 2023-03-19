import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from "../../User";
import { UserService } from "../../services/user.service";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent {
	user: User = new User();

	constructor(private userService: UserService) {}

	onSubmit(form: NgForm) {
		if (form.valid) {
			this.userService.signup(this.user).subscribe((response: String) => console.log(response),
				error => console.error(error));
		}
	}
}
