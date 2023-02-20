import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { UsersList } from 'src/app/shared/models/user-list.interface';
import { AccountService } from 'src/app/user-authentication/services/account.service';
import { BehaviorSubject, Observable } from 'rxjs';


@Component({
  selector: 'alp-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css'],
})
export class UserManagementComponent implements OnInit {
  allCount!: number;
  activateCount!: number;
  deactivateCount!: number;
  usersResponse!: BehaviorSubject<UsersList[]>;
  users!: Observable<UsersList[]>;

  constructor(
    private accountService: AccountService,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    this.getAllUser();
  }

  get countAllUsers(): number {
    return this.usersList?.length;
  }

  get countAllDeactivatedUsers(): number {
    return this.usersList?.length;
  }

  get countAllActiveUsers(): number {
    return this.getAllActiveUsers?.length;
  }


  get usersList(): UsersList[] {
    return this.usersResponse?.value;
  }


  getAllUser(): void {
    this.accountService.getAllUsers().subscribe(
      (response: any) => {
        this.usersResponse = new BehaviorSubject<UsersList[]>(
          response
        );
        this.users = this.usersResponse.asObservable();
        
      },
      (error: { message: string | undefined }) => {
        this.toastr.error(error?.message);
      }
    );
  }

  getAllInActiveUsers(): void {
    this.accountService.getAllInActiveUsers().subscribe(
      (response: any) => {
        this.usersResponse = new BehaviorSubject<UsersList[]>(
          response
        );
        this.users = this.usersResponse.asObservable();
      },
      (error: { message: string | undefined }) => {
        this.toastr.error(error?.message);
      }
    );
  }

  getAllActiveUsers(): void {
    this.accountService.getAllActiveUsers().subscribe(
      (response: any) => {
        this.usersResponse = new BehaviorSubject<UsersList[]>(
          response
        );
        this.users = this.usersResponse.asObservable();
      },
      (error: { message: string | undefined }) => {
        this.toastr.error(error?.message);
      }
    );
  }
}
