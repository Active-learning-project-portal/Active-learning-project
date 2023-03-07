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
import { CourseRegistrationComponent } from './modals/course-registration/course-registration.component';
import { MaterialModule } from '../shared/modules/material-modules';
import { ProgrammingLanguageComponent } from './modals/views/programming-language/programming-language.component';
import { LanguageCourseComponent } from './modals/views/language-course/language-course.component';
import { LanguageSubjectComponent } from './modals/views/language-subject/language-subject.component';
import { MatButtonModule } from '@angular/material/button';
import { CommunityComponent } from './layouts/community/community.component';
import { PlaygroundComponent } from './layouts/playground/playground.component';

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
    CourseRegistrationComponent,
    ProgrammingLanguageComponent,
    LanguageCourseComponent,
    LanguageSubjectComponent,
    CommunityComponent,
    PlaygroundComponent
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
    ArrayToStringPipe,
    DashboardComponent,
    AssignmentsComponent,
    SettingsComponent,
    CourseRegistrationComponent,
    ProgrammingLanguageComponent,
    LanguageCourseComponent,
    LanguageSubjectComponent
  ],
  imports: [
    CommonModule,
    MasterLayoutRoutingModule,
    CoursesModule,
    FontAwesomeModule,
    MaterialModule,
    MatButtonModule
  ],
})
export class MasterLayoutModule { }
