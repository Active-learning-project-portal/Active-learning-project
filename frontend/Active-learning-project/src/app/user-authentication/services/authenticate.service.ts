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

  save(user: UserRequest) {
    return this.http.post(`${environment.apiUrl}auth/signup`, user);
  }

  getUsers(pagination: Pagination): any {
    const accessToken = this.userValue?.token;
    const tokenType = this.userValue?.tokenType;
    if (!accessToken || !tokenType) {
      this.router.navigate(['/auth/user/signin']);
    }

    const pageUrl = this.populateRequestUrl(pagination);
    console.log(pageUrl);

    return this.http.get(`${environment.apiUrl}users?${pageUrl}`, {
      headers: {
        Authorization: `${tokenType} ${accessToken}`,
      },
    });
  }

  //Can do better, will refactore this code
  populateRequestUrl(pagination: Pagination): string {
    let requestUrl = '';
    if (pagination.pageNo) {
      requestUrl += `pageNo=${pagination.pageNo}`;
    }
    if (pagination.pageSize) {
      if (requestUrl.length > 0) {
        requestUrl += `&pageSize=${pagination.pageSize}`;
      } else {
        requestUrl += `pageSize=${pagination.pageSize}`;
      }
    }
    if (pagination['sortBy']) {
      if (requestUrl.length > 0) {
        requestUrl += `&sortBy=${pagination.sortBy}`;
      } else {
        requestUrl += `sortBy=${pagination.sortBy}`;
      }
    }
    if (pagination.sortDir) {
      if (requestUrl.length > 0) {
        requestUrl += `&sortDir=${pagination.sortDir}`;
      } else {
        requestUrl += `sortDir=${pagination.sortDir}`;
      }
    }
    return requestUrl;
  }
}
