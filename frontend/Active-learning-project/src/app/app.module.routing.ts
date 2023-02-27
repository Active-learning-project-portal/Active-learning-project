import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { alp, auth } from './shared/routers/Routers';
import { AccountComponent } from './user-authentication/account/account.component';
import { SignInComponent } from './user-authentication/signin/signin.component';
import { SignupComponent } from './user-authentication/signup/signup.component';

const routes: Routes = [
	{
		path: 'alp',
		loadChildren: () =>
			import('./master-layout/master-layout.module').then(
				(m) => m.MasterLayoutModule
			)
	},
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
			{
				path: '**',
				redirectTo: 'signin',
				pathMatch: 'full'
			}
		],
	},
	{
		path: '**',
		redirectTo: '',
		pathMatch: 'full',
	}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
