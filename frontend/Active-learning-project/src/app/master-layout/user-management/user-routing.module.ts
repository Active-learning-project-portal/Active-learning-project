import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActiveUsersComponent } from './tables/active-users/active-users.component';
import { AllUsersComponent } from './tables/all-users/all-users.component';
import { InactiveUsersComponent } from './tables/inactive-users/inactive-users.component';
import { UserManagementComponent } from './user-management.component';

const routes: Routes = [
  {
    path: '',
    component: UserManagementComponent,
    children: [
      {
        path: 'all',
        component: AllUsersComponent,
      },
      {
        path: 'active',
        component: ActiveUsersComponent,
      },
      {
        path: 'inactive',
        component: InactiveUsersComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule {}
