import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MasterLayoutComponent } from './layouts/master/master-layout.component';
import { UserManagementComponent } from './layouts/user-management/user-management.component';
import { UserManagementGuard } from '../shared/guards/user-management.guard';
import { DashboardComponent } from './layouts/dashboard/dashboard.component';
import { AssignmentsComponent } from './layouts/assignments/assignments.component';
import { SettingsComponent } from './layouts/settings/settings.component';
import { CommunityComponent } from './layouts/community/community.component';
import { PlaygroundComponent } from './layouts/playground/playground.component';

const routes: Routes = [
  {
    path: '',
    component: MasterLayoutComponent,
    children: [
      {
        path: 'dashboard',
        component: DashboardComponent,
      },
      {
        path: 'courses',
        loadChildren: () => import("./layouts/courses/courses.module")
          .then(c => c.CoursesModule)
      },
      {
        path: 'assignments',
        component: AssignmentsComponent,
      },
      {
        path: 'users',
        component: UserManagementComponent,
        canActivate:[UserManagementGuard]
      
      },
      {
        path: 'settings',
        component: SettingsComponent,
      },
      {
        path: 'community',
        component: CommunityComponent,
      },
      {
        path: 'playground',
        component: PlaygroundComponent,
      }
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MasterLayoutRoutingModule { }
