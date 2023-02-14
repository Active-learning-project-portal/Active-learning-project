import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'alp-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SignInComponent implements OnInit {
  loginForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl!: string;
  anonymous = 'anonymous';

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
    });
  }

  // convenience getter for easy access to form fields
  get controls() {
    return this.loginForm.controls;
  }

  getFormControl(name: string): AbstractControl {
    return this.loginForm.controls[name];
  }

  isFormControlTouched(name: string): boolean {
    return this.getFormControl(name).touched;
  }

  isFormControlValid(name: string): boolean {
    return this.getFormControl(name).valid;
  }

  onSubmit() {
    this.loginForm.markAllAsTouched();


  }
}
