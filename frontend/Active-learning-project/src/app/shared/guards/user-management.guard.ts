import { UserManagementService } from 'src/app/services/user-management/user-management.service';
import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { RoleList } from '../models/roles.interface';
import { UsersList } from '../models/user-list.interface';

@Injectable({
  providedIn: 'root',
})
export class UserManagementGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
      let user = localStorage.getItem('user');
      const userInfo =this.hasValidRolesToManageUser(user)
    return userInfo ? true : this.router.navigate(['/signin']);
  }

  hasValidRolesToManageUser(user:any):boolean{
      user = JSON.parse(user);
      const userInfo:UsersList = {
        id: user?.id,
        username: user?.username,
        name: user?.name,
        lastname: user?.lastname,
        provider: user?.provider,
        avatar: user?.avatar,
        joined: user?.joined,
        lastSeen: user?.lastSeen,
        roles: user?.roles,
        isActive: true
      }

     const roleList =  userInfo.roles.filter(user=>user.name === 'ROLE_SUPER_ADMIN' || user.name === "ROLE_ADMIN" || user.name === "ROLE_TRAINER")
      return  roleList.length > 0;
  }
}
