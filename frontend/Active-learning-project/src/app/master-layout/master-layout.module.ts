import { UserManagementComponent } from './user-management/user-management.component';
import {NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MasterLayoutRoutingModule } from './master-layout-routing.module';
import { MasterLayoutComponent } from './master-layout.component';
import { SideNavComponent } from '../shared/components/side-nav/side-nav.component';
import { TopNavComponent } from '../shared/components/top-nav/top-nav.component';
import { ModalComponent } from './user-management/modal/modal.component';
import { RemoveRoleAndUnderScorePipe } from '../shared/pipes/remove-role-and-under-score.pipe';import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { TopNavComponent } from './navigation/top-nav/top-nav.component';
import { SideNavComponent } from './navigation/side-nav/side-nav.component';
import { UserProfileComponent } from './navigation/top-nav/user-profile/user-profile.component';


@NgModule({
  declarations: [
    MasterLayoutComponent,
    SideNavComponent,
    TopNavComponent,
    UserManagementComponent,
    ModalComponent,
    RemoveRoleAndUnderScorePipe,
    TopNavComponent,
    SideNavComponent,
    UserProfileComponent
  ],
  imports: [
    CommonModule,
    MasterLayoutRoutingModule
  ]
})
export class MasterLayoutModule {}
