import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MasterLayoutRoutingModule } from './master-layout-routing.module';
import { MasterLayoutComponent } from './master-layout.component';
import { SideNavComponent } from '../shared/components/side-nav/side-nav.component';
import { TopNavComponent } from '../shared/components/top-nav/top-nav.component';
import { UserManagementComponent } from './user-management/user-management.component';
import { ModalComponent } from './user-management/modal/modal.component';

@NgModule({
  declarations: [
    MasterLayoutComponent,
    UserManagementComponent,
    SideNavComponent,
    TopNavComponent,
    ModalComponent,
  ],
  imports: [CommonModule, MasterLayoutRoutingModule],
})
export class MasterLayoutModule {}
