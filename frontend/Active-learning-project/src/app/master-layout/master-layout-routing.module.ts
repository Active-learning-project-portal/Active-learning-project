import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MasterLayoutComponent } from './master-layout.component';
import { UserManagementComponent } from './user-management/user-management.component';
import { master } from '../shared/routers/Routers';

const routes: Routes = [
  {
    path: '',
    component: MasterLayoutComponent,
    children:[
      {
        path: master.action.users,
        component: UserManagementComponent,
      }
    ],
  }
];

// path: auth.person,
//     component: AccountComponent,
//     children: [
//       {
//         path: auth.action.signin,
//         component: SignInComponent,
//       },
//       {
//         path: auth.action.signup,
//         component: SignupComponent,
//       },
//       {
//         path: '**',
//         redirectTo: auth.action.signin,
//         pathMatch: 'full'
//       }
//     ],

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MasterLayoutRoutingModule {}
