import { SocialAuthService } from '@abacritt/angularx-social-login';
import { ChangeDetectionStrategy, Component, Input } from '@angular/core';

import { ToastrService } from 'ngx-toastr';
import { Observable, Observer } from 'rxjs';
import { AuthenticateRequest } from 'src/app/models/payloads/requests/authenticate.request.model';
import { UserRequest } from 'src/app/models/payloads/requests/user.auth.request.model';
import { AuthenticateService } from 'src/app/services/user-authentication/authenticate.service';
import { UserManagementService } from '../../services/user-management/user-management.service';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'alp-google-button',
  templateUrl: './google-button.component.html',
  styleUrls: ['./google-button.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class GoogleButtonComponent {
  @Input()
  btnType!: 'signin' | 'signup';

  constructor(
    private authenticateService: AuthenticateService,
    private googleAuth: SocialAuthService,
    private toastr: ToastrService,
    private userManagementService: UserManagementService
  ) {}

  ngOnInit(): void {
    const authObserver: Observable<any> = new Observable(
      (observer: Observer<any>) => {
        this.googleAuth.authState.subscribe((user: any) => {
          if (!user) {
            return this.toastr.error('User login error', 'Google Auth');
          }
          console.log(user);
          const authModel: UserRequest = {
            firstname: user['firstName'],
            lastname: user['lastName'],
            authType: this.btnType,
            provider: user['provider'],
            username: user['email'],
            password: environment.defaultPassword,
            avatar: user['photoUrl'],
          };

          this.authenticateService.authenticate(authModel).subscribe(
            (userAuth) => {
              this.toastr.success(`Successful login`);
              const stringifyUser = JSON.stringify(userAuth);
              localStorage.setItem('user', stringifyUser);
              window.location.href = '/alp';
            },
            (error) => {
              this.toastr.error(error?.message);
            }
          );
          return;
        });
      }
    );

    // Process the request reponse here...
    authObserver.subscribe((response: any) => {
      console.log(response);
    });
  }
}
