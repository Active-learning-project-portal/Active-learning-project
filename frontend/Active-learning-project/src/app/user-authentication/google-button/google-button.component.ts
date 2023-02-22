import { SocialAuthService } from '@abacritt/angularx-social-login';
import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable, Observer } from 'rxjs';
import { UserRequest } from 'src/app/models/payloads/requests/user.auth.request.model';
import { AuthenticateService } from '../services/authenticate.service';


@Component({
	selector: 'alp-google-button',
	templateUrl: './google-button.component.html',
	styleUrls: ['./google-button.component.css'],
	changeDetection: ChangeDetectionStrategy.OnPush
})
export class GoogleButtonComponent {
	signContext!: "signin" | "signup";
	userModel!: UserRequest;

	@Input()
	btnType!: "signin" | "signup";

	constructor(
		private authenticateService: AuthenticateService,
		private googleAuth: SocialAuthService,
		private toastr: ToastrService) { }

	ngOnInit(): void {
		console.log("Hi mushe")
		const authObserver: Observable<any> = new Observable((observer: Observer<any>) => {
			this.googleAuth.authState.subscribe((user: any) => {
				if (!user) {
					return this.toastr.error("User login error", "Google Auth");
				}

				this.userModel = {
					firstname: user.firstName,
					lastname: user.lastName,
					username: user.email,
					avatar: user.photoUrl,
					provider: user.provider,
					authType: this.btnType,
					password: ""
				};

				// Making a post request
				this.authenticateService.authenticate(this.userModel)
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
