export class User {
	username: String = '';
	password: String = '';
	confirmPassword: String = ''
	constructor() {}
	getUsername(): String {
		return this.username
	}
	getPassword(): String {
		return this.password
	}
	isValid(): boolean {
		return this.username !== null && this.password !== null && this.confirmPassword !== null
	}
}
