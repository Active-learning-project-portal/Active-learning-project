import { Component } from '@angular/core';
import { faCalendarCheck, faCog, faCubes, faGraduationCap, faUserGraduate } from '@fortawesome/free-solid-svg-icons';

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
}
