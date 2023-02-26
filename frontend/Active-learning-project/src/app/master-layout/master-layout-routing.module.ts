import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MasterLayoutComponent } from './layout/master-layout.component';
import { UserManagementComponent } from './user-management/user-management.component';
import { alp } from '../shared/routers/Routers';

const routes: Routes = [
  {
    path: '',
    component: MasterLayoutComponent,
    children:[
      {
        path: alp.action.users,
        component: UserManagementComponent,
      }
    ],
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MasterLayoutRoutingModule {}
