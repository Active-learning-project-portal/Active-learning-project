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
import { ArrayToStringPipe } from '../shared/pipes/array-string.pipe';
import { CoursesModule } from './layouts/courses/courses.module';
import { DashboardComponent } from './layouts/dashboard/dashboard.component';
import { AssignmentsComponent } from './layouts/assignments/assignments.component';
import { SettingsComponent } from './layouts/settings/settings.component';

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
    ArrayToStringPipe,
    DashboardComponent,
    AssignmentsComponent,
    SettingsComponent,
  ],
  exports: [
    MasterLayoutComponent,
    UserProfileComponent,
    UserManagementComponent,
    ModalComponent,
    RemoveRoleAndUnderScorePipe,
    TopNavComponent,
    SideNavComponent,
    UserProfileComponent,
  ],
  imports: [
    CommonModule,
    MasterLayoutRoutingModule,
    CoursesModule,
    FontAwesomeModule,
  ],
})
export class MasterLayoutModule {}
