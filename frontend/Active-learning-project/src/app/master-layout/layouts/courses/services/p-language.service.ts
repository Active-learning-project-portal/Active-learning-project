import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, BehaviorSubject } from 'rxjs';
import { UserManagementService } from 'src/app/services/user-management/user-management.service';
import { environment } from 'src/environments/environment';
import { CourseRequest } from '../models/request/course.request.model';
import { ProgrammingLanguageRequest } from '../models/request/language.request.model';

@Injectable({
  providedIn: 'root'
})
export class PLanguageService {


  public language!: Observable<ProgrammingLanguageRequest>
  private languageSubJect!:BehaviorSubject<ProgrammingLanguageRequest>

  constructor(private router: Router, private http: HttpClient,private userService:UserManagementService) {
    this.languageSubJect = new BehaviorSubject<ProgrammingLanguageRequest>(
      JSON.parse(localStorage.getItem('course')!)
    );
    this.language = this.languageSubJect.asObservable();
  }

  public get courseValue(): ProgrammingLanguageRequest {
    return this.languageSubJect.value;
  }

  getProgrammingLanguageById(id:string):any{
    return this.http.get(environment.apiUrl+"/programming-language/"+id);
  }

  saveProgrammmingLanguage(programmingLanguage:ProgrammingLanguageRequest):any{
    return this.http.post(environment.apiUrl+"/programming-language/",programmingLanguage);
  }
}
