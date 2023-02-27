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
    private route:ActivatedRoute,
    private authenticateService: AuthenticateService,
    private toastr: ToastrService
  ) {
    console.log(router.url);
    
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

    this.printpath('', this.router.config);
  }

  printpath(parent: String, config: Route[]) {
    for (const element of config) {
      const route = element;
      console.log(parent + '/' + route.path);
      if (route.children) {
        const currentPath = route.path ? parent + '/' + route.path : parent;
        this.printpath(currentPath, route.children);
      }
    }

    this.router.navigate(['/auth/user/signup']);
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
    const authModel: AuthenticateRequest = {
      username: this.getFormControl('email').value,
      password: this.getFormControl('password').value,
    };

    this.authenticateService.authenticate(authModel).subscribe(
      (userAuth) => {
        this.toastr.success(`Successful login`);
        const stringifyUser = JSON.stringify(userAuth);
        localStorage.setItem('user', stringifyUser);
        // this.router.navigate(['/auth/user/signup']);
        this.router.navigate(['../../alp/users'], { relativeTo: this.route }); //absolute
        console.log(this.router);
        console.log({ relativeTo: this.route })
        // this.router.navigate(['./list'], { relativeTo: this.route }); //child
        // this.router.navigate(['../list'], { relativeTo: this.route }); //sibling
        // this.router.navigate(['../../list'], { relativeTo: this.route }); //parent
        // this.router.navigate(['tabs/list'], { relativeTo: this.route });
        // this.router.navigate(['/tabs/list'], { relativeTo: this.route });
      },
      (error) => {
        this.toastr.error(error?.message);
      }
    );
  }
}
