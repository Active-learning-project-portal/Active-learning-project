import { ToastrService } from 'ngx-toastr';
import { Component } from '@angular/core';
import { UsersList } from 'src/app/shared/models/user-list.interface';
import { BehaviorSubject, Observable } from 'rxjs';
import { Pagination } from '../../shared/models/pagination.interface';
import { UserManagementService } from './services/user-management.service';

@Component({
  selector: 'alp-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css'],
})
export class UserManagementComponent {
  usersResponse!: BehaviorSubject<UsersList[]>;
  users!: Observable<UsersList[]>;

  constructor(
    private usersService: UserManagementService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.getAllUsers();
  }

  get usersList(): UsersList[] {
    return this.usersResponse?.value;
  }

  getAllUsers(): void {
    const pagination: Pagination = {
      pageNo: '0',
      pageSize: '3',
      sortBy: 'lastname',
      sortDir: 'DESC',
    };
    this.usersService.getUsers(pagination).subscribe(
      (response: any) => {
        this.usersResponse = new BehaviorSubject<UsersList[]>(response);
        this.users = this.usersResponse.asObservable();
        console.log(this.usersResponse )
      },
      (error: { message: string | undefined }) => {
        this.toastr.error(error?.message);
      }
    );
  }
}
