import { SignInComponent } from './signin/signin.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { AccountComponent } from './account.component';
// import { AccountComponent } from './account.component';
const routes: Routes = [
  {
    path: '',
    component: AccountComponent,
    children: [
      {
        path: 'signin',
        component: SignInComponent,
      },
      {
        path: 'signup',
        component: SignupComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AccountRoutingModule {}
