import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserAuthRequestModel } from 'src/app/models/payloads/requests/user.auth.request.model';
import { UserAuthResponseModel } from 'src/app/models/payloads/response/user.auth.response.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  [x: string]: any;
  private userSubject!: BehaviorSubject<UserAuthResponseModel>;
  public users!: Observable<UserAuthResponseModel>;

  constructor(private router: Router, private http: HttpClient) {
    this.userSubject = new BehaviorSubject<UserAuthResponseModel>(
      JSON.parse(localStorage.getItem('user')!)
    );
    this.users = this.userSubject.asObservable();
  }

  public get userValue(): UserAuthResponseModel {
    return this.userSubject.value;
  }

  authenticateUser(user: UserAuthRequestModel) {
    return this.http.post<UserAuthResponseModel>(
      `${environment.apiUrl}auth/signin`,
      user
    );
  }

  //   login(email: string, password: string) {
  //     return this.http
  //       .post<UserAuthModel>(`${this.config.apiRoute}/authenticate`, {
  //         email,
  //         password,
  //       })
  //       .pipe(
  //         map((user) => {
  //           // store user details and jwt token in local storage to keep user logged in between page refreshes
  //           localStorage.setItem('user', JSON.stringify(user));
  //           this.userSubject.next(user);
  //           return user;
  //         })
  //       );
  //   }

  // logout() {
  //   localStorage.removeItem('user');
  //   // this.userSubject.next(null);
  //   this.router.navigate(['/account/login']);
  // }

  save(user: UserAuthRequestModel) {
    return this.http.post(`${environment.apiUrl}auth/signup`, user);
  }

  getUsers(): any {
    const accessToken = this.userValue?.accessToken;
    const tokenType = this.userValue?.tokenType;
    if (!accessToken || !tokenType) {
      this.router.navigate(['/auth/user/signin']);
    }
    return this.http.get(`${environment.apiUrl}users`, {
      headers: {
        Authorization: `${tokenType} ${accessToken}`,
      },
    });
  }

  // getById(userId: string): Observable<UserAuthModel> {
  //   return this.http.get<UserAuthModel>(this.config.apiRoute + '/' + userId);
  // }

  // update(id: string, params: UserAuthModel) {
  //   // Admin should have a privilege to delete admin
  //   // Only super_admin can do this and Admin
  //   return this.http.put(`${this.config.apiRoute}/users/${id}`, params).pipe(
  //     map((x) => {
  //       // update stored user if the logged in user updated their own record
  //       if (id == this.userValue.id) {
  //         // update local storage
  //         const user = { ...this.userValue, ...params };
  //         localStorage.setItem('user', JSON.stringify(user));
  //         // publish updated user to subscribers
  //         this.userSubject.next(user);
  //       }
  //       return x;
  //     })
  //   );
  // }

  // delete(id: string) {
  //   console.log(id);
  //   return this.http.delete(`${this.config.apiRoute}/users/${id}`).pipe(
  //     map((x) => {
  //       // auto logout if the logged in user deleted their own record
  //       if (id == this.userValue.id) {
  //         this.logout();
  //       }
  //       return x;
  //     })
  //   );
  // }
}
