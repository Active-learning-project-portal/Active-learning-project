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
        path: `${master.rootPath}/${master.action.users}`,
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
