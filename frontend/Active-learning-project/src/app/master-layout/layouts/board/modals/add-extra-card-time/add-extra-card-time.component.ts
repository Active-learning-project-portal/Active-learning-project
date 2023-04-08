import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';

@Component({
  selector: 'alp-add-extra-card-time',
  templateUrl: './add-extra-card-time.component.html',
  styleUrls: ['./add-extra-card-time.component.css']
})
export class AddExtraCardTimeComponent implements OnInit {
  extraTimeFormGroup: FormGroup = new FormGroup({
    Time: new FormControl(null, [Validators.required])
  })

  constructor(
    private extraTimeModalReference: MdbModalRef<AddExtraCardTimeComponent>
  ) { }

  ngOnInit(): void {}

  getFormControl(name: string): AbstractControl {
    return this.extraTimeFormGroup.controls[name];
  }

  isFormControlInvalid(name: string): boolean {
    return this.getFormControl(name).invalid;
  }

  isFormControlTouched(name: string): boolean {
    return this.getFormControl(name).touched;
  }

  addExtraTime() {
    this.extraTimeFormGroup.markAllAsTouched();

    if (this.extraTimeFormGroup.invalid) return;

    this.extraTimeModalReference
      .close(this.getFormControl("Time").value)
  }

  closeModal() {
    this.extraTimeModalReference
      .close(null)
  }
}
