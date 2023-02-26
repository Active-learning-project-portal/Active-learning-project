import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { RemoveRoleAndUnderScorePipe } from '../shared/pipes/remove-role-and-under-score.pipe';
import { MasterLayoutRoutingModule } from './master.layout.module.routing';
import { SideNavComponent } from './navigation/side-nav/side-nav.component';
import { TopNavComponent } from './navigation/top-nav/top-nav.component';
import { UserProfileComponent } from './navigation/top-nav/user-profile/user-profile.component';
import { ModalComponent } from './layouts/user-management/modal/modal.component';
import { UserManagementComponent } from './layouts/user-management/user-management.component';
import { MasterLayoutComponent } from './layouts/master/master-layout.component';

@NgModule({
	declarations: [
		MasterLayoutComponent,
		UserProfileComponent,
		UserManagementComponent,
		ModalComponent,
		RemoveRoleAndUnderScorePipe,
		TopNavComponent,
		SideNavComponent,
		UserProfileComponent
	],
	exports: [
		MasterLayoutComponent,
		UserProfileComponent,
		UserManagementComponent,
		ModalComponent,
		RemoveRoleAndUnderScorePipe,
		TopNavComponent,
		SideNavComponent,
		UserProfileComponent
	],
	imports: [
		CommonModule,
		MasterLayoutRoutingModule,
		FontAwesomeModule
	]
})
export class MasterLayoutModule { }
