import { Component } from '@angular/core';
import { MatStepper } from '@angular/material/stepper';

@Component({
  selector: 'alp-course-registration',
  templateUrl: './course-registration.component.html',
  styleUrls: ['./course-registration.component.css']
})
export class CourseRegistrationComponent {
  nextStep(stepper: MatStepper){
    stepper.next()
  }

  prevStep(stepper: MatStepper){
    stepper.previous()
  }
}
