import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { CourseRequest } from 'src/app/master-layout/layouts/courses/models/request/course.request.model';
import { CoursesService } from 'src/app/master-layout/layouts/courses/services/courses.service';
import { UserRequest } from 'src/app/models/payloads/requests/user.auth.request.model';
import { IProgrammingLanguage } from 'src/app/shared/models/programming-language.interface';

@Component({
  selector: 'alp-programming-language',
  templateUrl: './programming-language.component.html',
  styleUrls: ['./programming-language.component.css']
})
export class ProgrammingLanguageComponent {

  courses!: Observable<CourseRequest>
  courseSubJect!:BehaviorSubject<CourseRequest>
  courseList!:CourseRequest;
  courseRequest!:Array<CourseRequest>;

  constructor(private courseService:CoursesService,private toastr:ToastrService){
    console.log("Inside constructor");
    this.getCourses();
    console.log(this.courseValue)
    console.log(this.courseService.getCourses());
  }

  get courseValue (){
    return this.getCourses();
  }

  getCourses(){
   this.courseService.getAllCourses().subscribe(course=>{
    console.log("I am an course");
    console.log(course)
   })
  }
}
