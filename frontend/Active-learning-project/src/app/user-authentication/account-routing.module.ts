import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignInComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { AccountComponent } from './account.component';
import { auth } from '../shared/routers/Routers';

const routes: Routes = [
  {
    path: auth.person,
    component: AccountComponent,
    children: [
      {
        path: auth.action.signin,
        component: SignInComponent,
      },
      {
        path: auth.action.signup,
        component: SignupComponent,
      },
      {
        path: '**',
        redirectTo: auth.action.signin,
        pathMatch: 'full'
      }
    ],
  },
  {
    path: '**',
    redirectTo: `${auth.person}/${auth.action.signin}`,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AccountRoutingModule { }
