export class User {
	username: string = '';
	password: string = '';
	confirmPassword: string = ''
	constructor() {}
	getUsername(): string {
		return this.username
	}
	getPassword(): string {
		return this.password
	}
	isValid(): boolean {
		return this.username !== null && this.password !== null && this.confirmPassword !== null
	}
}
