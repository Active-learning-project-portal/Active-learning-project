import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserManagementService } from 'src/app/services/user-management/user-management.service';
import { environment } from 'src/environments/environment';
import { CourseRequest } from '../models/request/course.request.model';

@Injectable({
  providedIn: 'root',
})
export class CoursesService {
  private course!: Observable<CourseRequest>;
  private courseSubJect!: BehaviorSubject<CourseRequest>;

  constructor(private router: Router, private http: HttpClient) {
    this.courseSubJect = new BehaviorSubject<CourseRequest>(
      JSON.parse(localStorage.getItem('course')!)
    );
    this.course = this.courseSubJect.asObservable();
  }

  public get courseValue(): CourseRequest {
    return this.courseSubJect.value;
  }

  getCourseById(id: string): any {
    return this.http.get(environment.apiUrl + '/course/' + id);
  }

  saveUserCourses(courseRequest: CourseRequest): any {
    return this.http.post(environment.apiUrl + '/course/', courseRequest);
  }

  getAllCourses() {
    return this.http.get(environment.apiUrl + 'courses/');
  }

  getCourses(): Array<CourseRequest> {
    const courses: Array<CourseRequest> = [
      {
        name: 'JAVA',
        avatar:
          'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angularjs/angularjs-original.svg',
        courses: [
          {
            name: 'SPRING BOOT',
            avatar:
              'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angularjs/angularjs-original.svg',
            units: [
              {
                name: 'Intro duction to spring boot',
                avatar:
                  'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angularjs/angularjs-original.svg',
                title: 'topic',
                unitContent: 'someWeb',
                prerequestines: [
                  {
                    name: 'introdcution to javascript',
                    prerequestines: [],
                    avatar:
                      'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angularjs/angularjs-original.svg',
                    title: 'JAva test tittle',
                    unitContent: 'some web 1 url',
                  },
                  {
                    name: 'variables naming',
                    unitContent: 'some web url',
                    prerequestines: [],
                    title: 'JS test title',
                    avatar:
                      'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angularjs/angularjs-original.svg',
                  },
                ],
              },
            ],
          },
        ],
      },
      {
        name: 'C#',
        avatar:
          'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angularjs/angularjs-original.svg',
        courses: [
          {
            name: 'ENTITY FRAMEWORK',
            avatar:
              'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angularjs/angularjs-original.svg',
            units: [
              {
                name: 'Intro duction to C#',
                avatar:
                  'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angularjs/angularjs-original.svg',
                title: 'topic',
                unitContent: 'here',
                prerequestines: [
                  {
                    name: 'introdcution jQUERY',
                    unitContent: 'herecontent',
                    prerequestines: [],
                    title: 'lols',
                    avatar:
                      'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angularjs/angularjs-original.svg',
                  },
                  {
                    name: 'variables naming',
                    unitContent: 'someC',
                    title: 'Hi',
                    prerequestines: [],
                    avatar:
                      'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angularjs/angularjs-original.svg',
                  },
                ],
              },
            ],
          },
        ],
      },
      {
        name: 'PYTHON',
        avatar:
          'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angularjs/angularjs-original.svg',
        courses: [],
      },
    ];
    return courses;
  }

  getCoursesForAUSer(userId: string) {
    return this.http.get(environment.apiUrl + '/courses/users/' + userId);
  }
}
