import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { faArrowRightFromBracket, faCalendarCheck, faCode, faCog, faCubes, faGraduationCap, faUserGraduate, faUsers, faUsersViewfinder } from '@fortawesome/free-solid-svg-icons';
import { UserResponse } from 'src/app/models/payloads/response/user.auth.response.model';
import { UserManagementService } from 'src/app/services/user-management/user-management.service';
import { RoleList } from 'src/app/shared/models/roles.interface';

@Component({
  selector: 'alp-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.css']
})
export class SideNavComponent implements OnInit{
  dashboard = faCubes
  courses = faGraduationCap
  assignments = faCalendarCheck
  settings = faCog
  users = faUserGraduate
  logout = faArrowRightFromBracket
  playground = faCode
  community = faUsers
  isAdmin = false;
  isTrainee = false;
  isSuperAdmin = false;
  isTrainer = false;
  

  @Output()
  closeSideMenuEmitter!: EventEmitter<boolean>

  constructor(private router: Router,private userManagementService:UserManagementService) {
    this.closeSideMenuEmitter = new EventEmitter();
  }

  signOut() {
    localStorage.clear()
    this.router.navigate(['/signin'])
  }

  closeMenu() {
    this.closeSideMenuEmitter.emit(false)
  }

  ngOnInit(): void {
    this.assignValidRoles();
  }

  assignValidRoles(){
    const user = this.userManagementService.userValue;
    user.roles.forEach(role => {
      switch (role.name) {
        case "ROLE_ADMIN":
           this.isAdmin =!this.isAdmin;
          break;
        case "ROLE_SUPER_ADMIN":
          this.isSuperAdmin != this.isSuperAdmin;
          break;
        case "ROLE_TRAINEE":
          this.isTrainee != this.isTrainee;
          break;
        case "ROLE_TRAINER":
          this.isTrainer !=this.isTrainer;
          break;
        default:
          break;
      }
    });
  }

  hasUsersPermission():boolean{
    return this.isAdmin || this.isSuperAdmin || this.isTrainer
  }

  hasCoursePermission():boolean{
    return this.isAdmin || this.isSuperAdmin;
  }
 
}
