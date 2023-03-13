import { Component, OnInit } from '@angular/core';
import { Router, Route, ActivatedRoute, NavigationEnd } from '@angular/router';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  Validators
} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthenticateRequest } from 'src/app/models/payloads/requests/authenticate.request.model';
import { filter, tap } from 'rxjs';
import { AuthenticateService } from 'src/app/services/user-authentication/authenticate.service';

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
  currentRoute: any;

  constructor(
    private router: Router,
    private authenticateService: AuthenticateService,
    private toastr: ToastrService
  ) {

    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd),
      tap(event => {
       console.log(event)
      }));
  }

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

    const authModel: AuthenticateRequest = {
      username: this.getFormControl('email').value,
      password: this.getFormControl('password').value,
      provider:"MANUAL"
    };

    this.authenticateService.authenticate(authModel).subscribe(
      (userAuth) => {
        console.log(userAuth);
        this.toastr.success(`Successful login`);
        const stringifyUser = JSON.stringify(userAuth);
        localStorage.setItem('user', stringifyUser);
        // this.router.navigate(['/alp']);
        window.location.href = "/alp"
      },
      (error) => {
        this.toastr.error(error?.message);
      }
    );
  }
}
