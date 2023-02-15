import { SocialAuthService } from '@abacritt/angularx-social-login';
import { Component, Input } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable, Observer } from 'rxjs';
import { UserAuthRequestModel } from 'src/app/models/payloads/requests/user.auth.request.model';
import { AccountService } from '../services/account.service';


@Component({
	selector: 'alp-google-button',
	templateUrl: './google-button.component.html',
	styleUrls: ['./google-button.component.css']
})
export class GoogleButtonComponent {
	signContext!: "signin" | "signup";
	authModel!: UserAuthRequestModel;

	@Input()
	btnType!: "signin" | "signup";

	constructor(
		private accountService: AccountService,
		private googleAuth: SocialAuthService,
		private toastr: ToastrService) { }

	ngOnInit(): void {
		const authObserver: Observable<any> = new Observable((observer: Observer<any>) => {
			this.googleAuth.authState.subscribe((user: any) => {
				if (!user) {
					return this.toastr.error("User login error", "Google Auth");
				}

				this.authModel = {
					firstname: user.firstName,
					lastname: user.lastName,
					username: user.email,
					avatar: user.photoUrl,
					provider: user.provider,
					authType: this.btnType,
					password: ""
				};

				// Making a post request
				this.accountService.authenticateUser(this.authModel)
					.subscribe((response: any) => {
						observer.next(response)
					})
				return;
			})
		})

		// Process the request reponse here...
		authObserver.subscribe((response: any) => {
			console.log(response)
		})
	}
}
