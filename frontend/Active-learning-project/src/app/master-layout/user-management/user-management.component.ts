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
export class UserManagementComponent{


  usersResponse!: BehaviorSubject<UsersList[]>;
  users!: Observable<UsersList[]>;

  constructor(
    private accountService: AccountService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.getAllUsers();
  }

  get usersList(): UsersList[] {
    return this.usersResponse?.value;
  }


  getAllUsers(): void {
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
}
