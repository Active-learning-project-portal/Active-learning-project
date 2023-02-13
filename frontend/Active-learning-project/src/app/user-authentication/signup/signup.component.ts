import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SocialAuthService } from '@abacritt/angularx-social-login';

@Component({
  selector: 'alp-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  loginForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl!: string;
  anonymous = 'anonymous';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private googleAuth: SocialAuthService
  ) { }

  ngOnInit() {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    });
  }

  // convenience getter for easy access to form fields
  get controls() {
    return this.loginForm.controls;
  }

  onSubmit() {}
}