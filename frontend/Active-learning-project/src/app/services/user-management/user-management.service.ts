import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { UserRequest } from 'src/app/models/payloads/requests/user.auth.request.model';
import { UserResponse } from 'src/app/models/payloads/response/user.auth.response.model';
import { Pagination } from 'src/app/shared/models/pagination.interface';
import { environment } from 'src/environments/environment';

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

  logout() {
    localStorage.removeItem('user');
    this.router.navigate(['/auth/user/signin']);
  }

  save(user: UserRequest) {
    return this.http.post(`${environment.apiUrl}users/signup`, user);
  }

  getUsers(pagination: Pagination): any {
    const accessToken = this.userValue?.token;
    const tokenType = this.userValue?.tokenType;
    if (!accessToken || !tokenType) {
      this.router.navigate(['/signin']);
    }
    const pageUrl = this.getRequestUrl(pagination);
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

  addSearchValue(pagination: Pagination): String[] {
    const paramsList = this.addSortDirection(pagination);
    if (pagination.searchValue) {
      paramsList.push(`searchValue=${pagination.searchValue}`);
    }
    return paramsList;
  }

  getRequestUrl(pagination: Pagination): string {
    return this.addSearchValue(pagination).join('&');
  }

  update(id: string, params: UserRequest) {
    return this.http.put(`${environment.apiUrl}/users/${id}`, params).pipe(
      map((x) => {
        if (id == this.userValue.id) {
          const user = { ...this.userValue, ...params };
          localStorage.setItem('user', JSON.stringify(user));
          this.userSubject.next(user);
        }
        return x;
      })
    );
  }

  delete(id: string) {
    console.log(id);
    return this.http.delete(`${environment.apiUrl}/users/${id}`).pipe(
      map((user) => {
        if (id == this.userValue.id) {
          this.logout();
        }
        return user;
      })
    );
  }
}
