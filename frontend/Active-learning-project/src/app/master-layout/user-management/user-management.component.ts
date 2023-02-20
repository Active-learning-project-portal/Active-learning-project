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


  constructor(
  ) {}

}
