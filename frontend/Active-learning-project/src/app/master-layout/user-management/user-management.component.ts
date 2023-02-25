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
  sortBy!: string;
  pageNo!: string;
  pageSize!: string;
  sortDir!: 'ASC' | 'DESC';

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

  onChangeSortBy(colunmName: string) {
    console.log(colunmName)
    this.sortBy = colunmName;
  }

  onChangeSortDir() {
    if (this.sortDir === 'ASC') {
      this.sortDir = 'DESC';
      return;
    }
    this.sortDir = 'ASC';
  }


  onChangePageNo(newPage: string, pageSize: string) {
    let newPageNumberConvertedToInt = Number.parseInt(pageSize);
    if (newPage === 'NEXT') {
      newPageNumberConvertedToInt++;
      this.pageSize = String(newPageNumberConvertedToInt);
      return;
    }
    newPageNumberConvertedToInt--;
    this.pageSize = String(newPageNumberConvertedToInt);
  }
  onChangePageSize(colunmName: Pagination) {
    this.sortDir = colunmName.sortDir;
  }

  getAllUsers(): void {
    const pagination: Pagination = {
      pageNo: this.pageNo,
      pageSize: this.pageSize,
      sortBy: this.sortBy,
      sortDir: this.sortDir,
    };
    this.usersService.getUsers(pagination).subscribe(
      (response: any) => {
        this.usersResponse = new BehaviorSubject<UsersList[]>(response);
        this.users = this.usersResponse.asObservable();
      },
      (error: { message: string | undefined }) => {
        this.toastr.error(error?.message);
      }
    );
  }
}
