import { AuthenticateRequest } from './../../models/payloads/requests/authenticate.request.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserRequest } from 'src/app/models/payloads/requests/user.auth.request.model';
import { environment } from 'src/environments/environment';
import { Pagination } from '../../shared/models/pagination.interface';
import { AuthenticateResponse } from 'src/app/models/payloads/response/authenticate.response.model';

@Injectable({
  providedIn: 'root',
})
export class AuthenticateService {
  [x: string]: any;
  private userSubject!: BehaviorSubject<AuthenticateResponse>;
  public users!: Observable<AuthenticateResponse>;

  constructor(private router: Router, private http: HttpClient) {
    this.userSubject = new BehaviorSubject<AuthenticateResponse>(
      JSON.parse(localStorage.getItem('user')!)
    );
    this.users = this.userSubject.asObservable();
  }

  public get userValue(): AuthenticateResponse {
    return this.userSubject.value;
  }

  authenticate(user: AuthenticateRequest) {
    return this.http.post<AuthenticateRequest>(
      `${environment.apiUrl}auth/authenticate`,
      user
    );
  }
}
