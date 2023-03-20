import {Component} from '@angular/core';
import {UserService} from "../services/user.service";
import {User} from "../User";

@Component({
	selector: 'app-navbar',
	templateUrl: './navbar.component.html',
	styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
	user: User = new User();
	constructor(private userService: UserService) {
		this.user = userService.getUser();
	}

}
