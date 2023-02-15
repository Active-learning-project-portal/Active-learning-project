import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignInComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../shared/modules/material-modules';
import { AccountComponent } from './account.component';
import { AccountRoutingModule } from './account-routing.module';
import { GoogleButtonComponent } from './google-button/google-button.component';
import { GithubButtonComponent } from './github-button/github-button.component';

@NgModule({
  declarations: [
    AccountComponent,
    SignInComponent,
    SignupComponent,
    GoogleButtonComponent,
    GithubButtonComponent
  ],
  exports: [
    AccountComponent,
    SignInComponent,
    SignupComponent,
    GoogleButtonComponent
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
