import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'alp-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  loginForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl!: string;
  anonymous = 'anonymous';

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    // private accountService: AccountService,
    // private alertService: AlertService
  ) {
    // redirect to home if already logged in
    // if (this.accountService.userValue) {
    //   this.router.navigate(['/']);
    // }
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', Validators.required],
    });

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  // convenience getter for easy access to form fields
  get controls() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // reset alerts on submit
    // this.alertService.clear();

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
  //   this.accountService
  //     .login(this.controls['email'].value, this.controls['password'].value)
  //     .pipe(first())
  //     .subscribe(
  //       (data) => {
  //         //Change this
  //         this.router.navigate([this.returnUrl]);
  //       },
  //       (error) => {
  //         this.alertService.error(error);
  //         this.loading = false;
  //       }
      // );
  }
}