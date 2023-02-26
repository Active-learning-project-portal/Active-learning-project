import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { alp } from '../shared/routers/Routers';
import { MasterLayoutComponent } from './layouts/master/master-layout.component';
import { UserManagementComponent } from './layouts/user-management/user-management.component';

const routes: Routes = [
	{
		path: '',
		component: MasterLayoutComponent,
		children: [
			{
				path: '',
				component: UserManagementComponent,
			}
		],
	}
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule],
})
export class MasterLayoutRoutingModule { }
