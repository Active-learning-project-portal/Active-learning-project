import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthenticateRequest } from 'src/app/models/payloads/requests/authenticate.request.model';
import { AuthenticateResponse } from 'src/app/models/payloads/response/authenticate.response.model';
import { environment } from 'src/environments/environment';
import { LocalStorageService } from 'src/app/shared/services/local-storage/local-storage.service';

@Injectable({
  providedIn: 'root',
})
export class AuthenticateService {
  [x: string]: any;
  private userSubject!: BehaviorSubject<AuthenticateResponse>;
  public users!: Observable<AuthenticateResponse>;

  constructor(
    private router: Router,
    private http: HttpClient,
    private localStorageService: LocalStorageService
  ) {
    this.userSubject = new BehaviorSubject<AuthenticateResponse>(
      JSON.parse(localStorageService.getItem('user')!)
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
