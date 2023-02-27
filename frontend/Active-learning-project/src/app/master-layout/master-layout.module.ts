import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MasterLayoutRoutingModule } from './master-layout-routing.module';
import { MasterLayoutComponent } from './master-layout.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { TopNavComponent } from './navigation/top-nav/top-nav.component';
import { SideNavComponent } from './navigation/side-nav/side-nav.component';
import { UserProfileComponent } from './navigation/top-nav/user-profile/user-profile.component';
import { RemoveRoleAndUnderScorePipe } from '../shared/pipes/remove-role-and-under-score.pipe';
import { ModalComponent } from './user-management/modal/modal.component';
import { UserManagementComponent } from './user-management/user-management.component';
import { ArrayToStringPipe } from '../shared/pipes/array-string.pipe';


@NgModule({
  declarations: [
    MasterLayoutComponent,
    UserProfileComponent,
    UserManagementComponent,
    ModalComponent,
    RemoveRoleAndUnderScorePipe,
    TopNavComponent,
    SideNavComponent,
    UserProfileComponent,
    ArrayToStringPipe
  ],
  imports: [
    CommonModule,
    MasterLayoutRoutingModule,
    FontAwesomeModule
  ]
})
export class MasterLayoutModule {}
