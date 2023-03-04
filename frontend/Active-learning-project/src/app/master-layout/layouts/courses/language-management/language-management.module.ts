import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LanguageManagementComponent } from './language-management.component';



@NgModule({
  declarations: [
    LanguageManagementComponent
  ],
  imports: [
    CommonModule
  ],
  exports:[
    LanguageManagementComponent
  ]
})
export class LanguageManagementModule { }
