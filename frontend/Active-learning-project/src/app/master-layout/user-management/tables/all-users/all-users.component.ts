import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject, Observable } from 'rxjs';
import { UsersList } from 'src/app/shared/models/user-list.interface';
import { AccountService } from 'src/app/user-authentication/services/account.service';

@Component({
  selector: 'alp-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit{

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
