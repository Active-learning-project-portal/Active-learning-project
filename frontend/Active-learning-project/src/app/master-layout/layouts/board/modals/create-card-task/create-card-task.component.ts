import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';

@Component({
  selector: 'alp-create-card-task',
  templateUrl: './create-card-task.component.html',
  styleUrls: ['./create-card-task.component.css']
})
export class CreateCardTaskComponent implements OnInit {
  taskFormGroup: FormGroup = new FormGroup({
    Task: new FormControl(null, [Validators.required])
  })

  constructor(private taskModalRef: MdbModalRef<CreateCardTaskComponent>) { }

  ngOnInit(): void {}

  getFormControl(name: string): AbstractControl {
    return this.taskFormGroup.controls[name];
  }

  isFormControlInvalid(name: string): boolean {
    return this.getFormControl(name).invalid;
  }

  isFormControlTouched(name: string): boolean {
    return this.getFormControl(name).touched;
  }

  addNewTask(): void {
    this.taskFormGroup.markAllAsTouched();
    if(this.taskFormGroup.invalid) return;

    this.closeAddTaskModal(this.getFormControl('Task').value);
  }

  closeAddTaskModal(task?: string): void {
    this.taskModalRef.close(task)
  }
}
