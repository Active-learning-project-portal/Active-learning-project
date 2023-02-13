import { GoogleLoginProvider, SocialAuthService } from '@abacritt/angularx-social-login';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'alp-google-signin',
  templateUrl: './google-signin.component.html',
  styleUrls: ['./google-signin.component.css']
})
export class GoogleSigninComponent implements OnInit {
  constructor(private googleAuth: SocialAuthService) { }

  ngOnInit(): void {
    this.googleAuth.authState.subscribe(user => {
      console.log(user)
    })

    // this.googleAuth.refreshAccessToken(GoogleLoginProvider.PROVIDER_ID);
  }
}
