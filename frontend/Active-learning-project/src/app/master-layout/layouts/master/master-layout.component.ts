import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { faGraduationCap } from '@fortawesome/free-solid-svg-icons';
import { CourseRegistrationComponent } from '../../modals/course-registration/course-registration.component';

@Component({
  selector: 'alp-master-layout',
  templateUrl: './master-layout.component.html',
  styleUrls: ['./master-layout.component.css']
})
export class MasterLayoutComponent {
  faSolidBars = faGraduationCap
  isMenuOpen: boolean = false
  private isRegisteredForACourse: boolean = false;

  constructor(private dialog: MatDialog, private router: Router) { }

  ngOnInit(): void {
    // Check if user registered for a course?
    if (!this.isRegisteredForACourse) {
      // Request user to register for a course to use the system
      this.dialog.open(CourseRegistrationComponent, {
        width: '70%',
        height: '89%',
        backdropClass: 'courseSelectorBackgrop'
      })
    }
    else {
      this.router.navigate(['/alp/dashboard']);
    }
  }

  toggleSideNavMenu(changeState: boolean) {
    this.isMenuOpen = changeState;
  }
}
