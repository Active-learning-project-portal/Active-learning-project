import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CourseMasterComponent } from './course-layouts/course-master/course-master.component';

const routes: Routes = [
  {
    path: '',
    component: CourseMasterComponent,
    children: [
      
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CoursesRoutingModule {}
