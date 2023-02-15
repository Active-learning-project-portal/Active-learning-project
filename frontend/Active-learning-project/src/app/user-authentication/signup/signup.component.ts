import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SocialAuthService } from '@abacritt/angularx-social-login';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'alp-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  providers:[AccountService]
})
export class SignupComponent implements OnInit {
  loginForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl!: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private googleAuth: SocialAuthService,
    private accountService: AccountService,
    private toastrService: ToastrService
  ) {}

  ngOnInit() {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      provider: new FormControl('MANUAL')
    });
  }

  get controls() {
    return this.loginForm.controls;
  }

  onSignUp() {
    this.submitted = true;

    if (this.loginForm.invalid) {
      return;
    }

    // this.loading = true;
    // this.accountService.register(this.loginForm.value).subscribe(
    //   (data) => {
    //     console.log('Registration successfully' + data);
    //     this.toastrService.success('Registration successfully');
    //     console.log(this.route)
    //     this.router.navigate(['../login'], { relativeTo: this.route });
    //   },
    //   (error) => {
    //     this.toastrService.error( error.message,error.title);
    //     this.loading = false;
    //   }
    // );
  }
}
