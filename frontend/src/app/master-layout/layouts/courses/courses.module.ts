import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoursesRoutingModule } from './courses.module.routing';
import { CourseItemComponent } from './views/course-item/course-item.component';
import { CourseMasterComponent } from './course-layouts/course-master/course-master.component';
import { CourseModulesComponent } from './course-layouts/course-modules/course-modules.component';
import { CourseUnitsComponent } from './course-layouts/course-units/course-units.component';
import { ActiveCourseComponent } from './widgets/active-course/active-course.component';
import { CourseModuleSummaryComponent } from './widgets/course-module-summary/course-module-summary.component';

@NgModule({
  declarations: [
    CourseItemComponent,
    CourseMasterComponent,
    CourseModulesComponent,
    CourseUnitsComponent,
    ActiveCourseComponent,
    CourseModuleSummaryComponent
  ],
  imports: [
    CommonModule,
    CoursesRoutingModule
  ],
})
export class CoursesModule { }
