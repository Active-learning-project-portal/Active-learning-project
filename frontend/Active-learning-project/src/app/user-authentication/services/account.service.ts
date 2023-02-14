import { HttpClient } from '@angular/common/http';
import { Inject, Injectable, Optional } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { IGlobalPayload } from 'src/app/models/payloads/requests/global.interface';
import { environment as config} from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class AccountService {
  [x: string]: any;
  private userSubject!: BehaviorSubject<IGlobalPayload>;
  public users!: Observable<IGlobalPayload>;

  constructor(
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
      .post<IGlobalPayload>(`${config.apiUrl}/auth/signin`, {
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
    this.router.navigate(['/account/login']);
  }

  register(user: IGlobalPayload) {
    console.log('I am user : ');
    console.log(user);
    console.log(config.apiUrl)
    return this.http.post(`${config.apiUrl}auth/signup`, user);
  }

  getAllUser(): Observable<IGlobalPayload[]> {
    return this.http.get<IGlobalPayload[]>(`${config.apiUrl}`);
  }

  getById(userId: string): Observable<IGlobalPayload> {
    return this.http.get<IGlobalPayload>(config.apiUrl + '/' + userId);
  }

  update(id: string, params: IGlobalPayload) {
    // Admin should have a privilege to delete admin
    // Only super_admin can do this and Admin
    return this.http.put(`${config.apiUrl}/users/${id}`, params).pipe(
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
    return this.http.delete(`${config.apiUrl}/users/${id}`).pipe(
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
