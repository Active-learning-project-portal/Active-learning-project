import { ToastrService } from 'ngx-toastr';
import { Component } from '@angular/core';
import { UsersList } from '../../../shared/models/user-list.interface';
import { BehaviorSubject, Observable } from 'rxjs';
import { Pagination } from '../../../shared/models/pagination.interface';
import { UserManagementService } from '../../../services/user-management/user-management.service';

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
  searchValue!: string;

  constructor(
    private usersService: UserManagementService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.getAllUsers();
  }

  onEnter(value: string) {
    this.searchValue = value;
    this.getAllUsers();
  }

  get usersList(): UsersList[] {
    return this.usersResponse?.value;
  }

  onChangeSortBy(colunmName: string) {
    this.sortBy = colunmName;
    this.onChangeSortDir();
  }

  onChangeSortDir() {
    if (this.sortDir === 'ASC') {
      this.sortDir = 'DESC';
      this.getAllUsers();
      return;
    }
    this.sortDir = 'ASC';
    this.getAllUsers();
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
      pageSize: '20',
      sortBy: this.sortBy,
      sortDir: this.sortDir,
      searchValue: this.searchValue,
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
