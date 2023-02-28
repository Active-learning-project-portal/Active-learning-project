import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MasterLayoutComponent } from './layouts/master/master-layout.component';
import { UserManagementComponent } from './layouts/user-management/user-management.component';
import { UserManagementGuard } from '../shared/guards/user-management.guard';

const routes: Routes = [
	{
		path: '',
		component: MasterLayoutComponent,
		children: [
			{
				path: '',
				component: UserManagementComponent,
				canActivate:[UserManagementGuard],
			}
		],
	}
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule],
})
export class MasterLayoutRoutingModule { }
