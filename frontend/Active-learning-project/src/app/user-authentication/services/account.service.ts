import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { IGlobalPayload } from '../../models/payloads/requests/global.interface';
import { AppConfigBundleService } from 'src/app/app-config/app-config-bundle.service';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  [x: string]: any;
  private userSubject!: BehaviorSubject<IGlobalPayload>;
  public users!: Observable<IGlobalPayload>;

  constructor(
    private config: AppConfigBundleService,
    private router: Router,
    private http: HttpClient
  ) {
    this.userSubject = new BehaviorSubject<IGlobalPayload>(
      JSON.parse(localStorage.getItem('user')!)
    );
    this.users = this.userSubject.asObservable();
  }

  public get userValue(): IGlobalPayload {
    return this.userSubject.value;
  }

  login(email: string, password: string) {
    return this.http
      .post<IGlobalPayload>(`${this.config.apiRoute}/authenticate`, {
        email,
        password,
      })
      .pipe(
        map((user) => {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('user', JSON.stringify(user));
          this.userSubject.next(user);
          return user;
        })
      );
  }

  logout() {
    localStorage.removeItem('user');
    // this.userSubject.next(null);
    this.router.navigate(['/account/login']);
  }

  register(user: IGlobalPayload) {
    return this.http.post(`${this.config.apiRoute}/register`, user);
  }

  getAllUser(): Observable<IGlobalPayload[]> {
    return this.http.get<IGlobalPayload[]>(`${this.config.apiRoute}`);
  }

  getById(userId: string): Observable<IGlobalPayload> {
    return this.http.get<IGlobalPayload>(this.config.apiRoute + '/' + userId);
  }

  update(id: string, params: IGlobalPayload) {
    // Admin should have a privilege to delete admin
    // Only super_admin can do this and Admin
    return this.http.put(`${this.config.apiRoute}/users/${id}`, params).pipe(
      map((x) => {
        // update stored user if the logged in user updated their own record
        if (id == this.userValue.id) {
          // update local storage
          const user = { ...this.userValue, ...params };
          localStorage.setItem('user', JSON.stringify(user));
          // publish updated user to subscribers
          this.userSubject.next(user);
        }
        return x;
      })
    );
  }

  delete(id: string) {
    console.log(id);
    return this.http.delete(`${this.config.apiRoute}/users/${id}`).pipe(
      map((x) => {
        // auto logout if the logged in user deleted their own record
        if (id == this.userValue.id) {
          this.logout();
        }
        return x;
      })
    );
  }
}