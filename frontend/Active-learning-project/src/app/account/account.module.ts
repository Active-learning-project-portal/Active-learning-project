import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignInComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../shared/modules/material-modules';
import { AccountComponent } from './account.component';

@NgModule({
  declarations: [AccountComponent, SignInComponent, SignupComponent],
  exports: [AccountComponent, SignInComponent, SignupComponent],

  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class AccountModule {}
