import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MasterLayoutRoutingModule } from './master-layout-routing.module';
import { MasterLayoutComponent } from './master-layout.component';


@NgModule({
  declarations: [
    MasterLayoutComponent
  ],
  imports: [
    CommonModule,
    MasterLayoutRoutingModule
  ]
})
export class MasterLayoutModule { }
