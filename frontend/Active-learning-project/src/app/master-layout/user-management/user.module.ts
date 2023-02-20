import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserManagementComponent } from './user-management.component';
import { RemoveRoleAndUnderScorePipe } from 'src/app/shared/pipes/remove-role-and-under-score.pipe';
import { ModalComponent } from './modal/modal.component';
import { ActiveUsersComponent } from './tables/active-users/active-users.component';
import { InactiveUsersComponent } from './tables/inactive-users/inactive-users.component';
import { AllUsersComponent } from './tables/all-users/all-users.component';


@NgModule({
  declarations: [ UserManagementComponent,RemoveRoleAndUnderScorePipe,ModalComponent, ActiveUsersComponent, InactiveUsersComponent, AllUsersComponent,],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule { }
