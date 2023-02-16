import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { UserAuthRequestModel } from 'src/app/models/payloads/requests/user.auth.request.model';
import { AccountService } from '../services/account.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'alp-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
})
export class SignInComponent implements OnInit {
  loginForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl!: string;
  isBtnLocked: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private accountService: AccountService,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
    });

    this.getFormControl('email').valueChanges.subscribe(() => {
      if (this.hasError('password')) {
        this.getFormControl('password').setErrors(null);
        this.lockButton(false);
      }
    });

    this.getFormControl('password').valueChanges.subscribe(() => {
      if (this.hasError('email')) {
        this.getFormControl('email').setErrors(null);
        this.lockButton(false);
      }
    });
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

  hasError(control: string, error: string = 'incorrectCredentials') {
    return this.getFormControl(control).hasError(error);
  }

  lockButton(state: boolean): void {
    this.isBtnLocked = state;
  }

  setFormControlErrors(): void {
    // When an error occures, use the following in response
    this.getFormControl('email').setErrors({ incorrectCredentials: true });
    this.getFormControl('password').setErrors({ incorrectCredentials: true });
    this.lockButton(true);
  }

  onSubmit() {
    this.loginForm.markAllAsTouched();

    // ???
    if (this.loginForm.invalid) return;

    // If form is valid
    // Build the auth model
    const authModel: UserAuthRequestModel = {
      authType: 'signin',
      provider: 'MANUAL',
      username: this.getFormControl('email').value,
      password: this.getFormControl('password').value,
    };

    this.accountService.authenticateUser(authModel).subscribe(
      (userAuth) => {
        console.log("I am user auth");
        console.log(userAuth)
        this.toastr.success(`Successful login`);
        this.router.navigate(["/app"])
      },
      (error) => {
        this.toastr.error(error?.message, error?.title);
      }
    );
  }
}
