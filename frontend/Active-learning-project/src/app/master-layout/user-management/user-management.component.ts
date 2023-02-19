import { Component, OnInit } from '@angular/core';
import { MatDialog} from '@angular/material/dialog';
import { FakeUsers } from './models/fake-users';
import { UsersList } from './models/user-list.interface';

@Component({
  selector: 'alp-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css'],
})
export class UserManagementComponent implements OnInit {
  users!: UsersList[];
  allCount!:number;
  activeCount!:number;
  deactiveCount!:number;

  constructor() {}

  
  ngOnInit() {
    this.filterAllUsers();
    this.allCount = this.allUsers;
    this.activeCount = this.activeUsers.length;
    this.deactiveCount = this.deactiveUsers.length;
  }

  get activeUsers():UsersList[]{
    return FakeUsers.filter(user => user.action === "Activate");
  }

  get deactiveUsers():UsersList[]{
    return FakeUsers.filter(user => user.action === "Deactivate");
  }

  get allUsers():number{
    return FakeUsers.length;
  }

  filterActivateUsers():void{
    this.users = this.activeUsers;
  }

  filterDeactivateUsers():void{
    this.users = this.deactiveUsers;
  }
  filterAllUsers():void{
    this.users = FakeUsers;
  }



}
