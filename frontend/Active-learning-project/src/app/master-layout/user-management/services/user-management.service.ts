import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserRequest } from 'src/app/models/payloads/requests/user.auth.request.model';
import { UserResponse } from 'src/app/models/payloads/response/user.auth.response.model';
import { environment } from 'src/environments/environment';
import { Pagination } from 'src/app/shared/models/pagination.interface';

@Injectable({
  providedIn: 'root',
})
export class UserManagementService {
  [x: string]: any;
  private userSubject!: BehaviorSubject<UserResponse>;
  public users!: Observable<UserResponse>;

  constructor(private router: Router, private http: HttpClient) {
    this.userSubject = new BehaviorSubject<UserResponse>(
      JSON.parse(localStorage.getItem('user')!)
    );
    this.users = this.userSubject.asObservable();
  }

  public get userValue(): UserResponse {
    return this.userSubject.value;
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

  logout() {
    localStorage.removeItem('user');
    this.router.navigate(['/auth/user/signin']);
  }

  save(user: UserRequest) {
    return this.http.post(`${environment.apiUrl}auth/signup`, user);
  }

  getUsers(pagination: Pagination): any {
    const accessToken = this.userValue?.token;
    const tokenType = this.userValue?.tokenType;
    if (!accessToken || !tokenType) {
      this.router.navigate(['/auth/user/signup']);
    }

    const pageUrl = this.getRequestUrl(pagination);
    console.log(pageUrl);

    return this.http.get(`${environment.apiUrl}users?${pageUrl}`, {
      headers: {
        Authorization: `${tokenType} ${accessToken}`,
      },
    });
  }

  addPageNo(pagination: Pagination): String[] {
    const paramsList = [];
    if (pagination.pageNo) {
      paramsList.push(`pageNo=${pagination.pageNo}`);
    }
    return paramsList;
  }

  addPageSize(pagination: Pagination): String[] {
    const paramsList = this.addPageNo(pagination);
    if (pagination.pageSize) {
      paramsList.push(`pageSize=${pagination.pageSize}`);
    }
    return paramsList;
  }

  addSortBy(pagination: Pagination): String[] {
    const paramsList = this.addPageSize(pagination);
    if (pagination.sortBy) {
      paramsList.push(`sortBy=${pagination.sortBy}`);
    }
    return paramsList;
  }
  addSortDirection(pagination: Pagination): String[] {
    const paramsList = this.addSortBy(pagination);
    if (pagination.sortBy) {
      paramsList.push(`sortDir=${pagination.sortDir}`);
    }
    return paramsList;
  }

  getRequestUrl(pagination: Pagination): string {
    return this.addSortDirection(pagination).join('&');
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
