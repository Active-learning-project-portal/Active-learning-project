import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LanguageManagementComponent } from './language-management/language-management.component';

const routes: Routes = [

  {
    path: '',
    component: LanguageManagementComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CoursesRoutingModule {}
