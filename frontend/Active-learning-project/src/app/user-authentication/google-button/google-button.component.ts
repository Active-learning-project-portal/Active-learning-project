import { SocialAuthService } from '@abacritt/angularx-social-login';
import { Component, Input } from '@angular/core';
import { UserAuthModel } from 'src/app/models/payloads/requests/user.auth.model';

@Component({
  selector: 'alp-google-button',
  templateUrl: './google-button.component.html',
  styleUrls: ['./google-button.component.css']
})
export class GoogleButtonComponent {
  signContext!: "signin" | "signup";
  authModel!: UserAuthModel;

  @Input()
  btnType!: "signin" | "signup";

  constructor(private googleAuth: SocialAuthService) {}

  ngOnInit(): void {
    this.googleAuth.authState.subscribe((user: any) => {
      if(!user) throw "Toast Error Message"

      this.authModel = {
        firstname: user.firstName,
        lastname: user.lastName,
        email: user.email,
        avatar: user.photoUrl,
        provider: user.provider,
        authType: this.btnType
      };

      // Awaiting a post request
      console.log(this.authModel)
    })
  }
}
