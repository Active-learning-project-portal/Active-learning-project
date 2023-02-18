import { Component, OnInit } from '@angular/core';
import { FakeUsers } from './models/fake-users';
import { UsersList } from './models/user-list.interface';

@Component({
  selector: 'alp-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css'],
})
export class UserManagementComponent implements OnInit {
  users!: UsersList[];

  ngOnInit() {
    this.users = FakeUsers;
  }
}
