import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignInComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../shared/modules/material-modules';
import { AccountComponent } from './account.component';
import { AccountRoutingModule } from './account-routing.module';
import { GoogleSignupComponent } from './signup/google-signup/google-signup.component';
import { GoogleSigninComponent } from './signin/google-signin/google-signin.component';
import { GithubSigninComponent } from './signin/github-signin/github-signin.component';
import { AccountService } from './services/account.service';
import { APP_SERVICE_CONFIG, APP_CONFIG } from '../app-config/app-config.service';


@NgModule({
  declarations: [
    AccountComponent,
    SignInComponent,
    SignupComponent,
    GoogleSignupComponent,
    GoogleSigninComponent,
    GithubSigninComponent,
  ],
  exports: [
    AccountComponent,
    SignInComponent,
    SignupComponent,
    GoogleSignupComponent,
    GoogleSigninComponent,
    GithubSigninComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
    AccountRoutingModule,
  ]
 
})
export class AccountModule {}
