import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { faArrowRightFromBracket, faCalendarCheck, faCode, faCog, faCubes, faGraduationCap, faUserGraduate, faUsers, faUsersViewfinder } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'alp-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.css']
})
export class SideNavComponent {
  dashboard = faCubes
  courses = faGraduationCap
  assignments = faCalendarCheck
  settings = faCog
  users = faUserGraduate
  logout = faArrowRightFromBracket
  playground = faCode
  community = faUsers

  @Output()
  closeSideMenuEmitter!: EventEmitter<boolean>

  constructor(private router: Router) {
    this.closeSideMenuEmitter = new EventEmitter();
  }

  signOut() {
    localStorage.clear()
    this.router.navigate(['/signin'])
  }

  closeMenu() {
    this.closeSideMenuEmitter.emit(false)
  }
}
