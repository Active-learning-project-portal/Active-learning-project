import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import queryString from 'query-string';
import { Observable, Observer } from 'rxjs';
import { UserRequest } from 'src/app/models/payloads/requests/user.auth.request.model';
import { AuthenticateService } from 'src/app/services/user-authentication/authenticate.service';
import { GithubRequestService } from 'src/app/services/user-authentication/github-request.service';
import { auth } from 'src/app/shared/routers/Routers';

@Component({
  selector: 'alp-github-button',
  templateUrl: './github-button.component.html',
  styleUrls: ['./github-button.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class GithubButtonComponent {
  githubLoginUrl!: string;
  gitAuthCode!: any;
  btnTitle!: string;
  gitAccessCodeObserver!: Observable<string | null>;
  gitTokenHandlerObserver!: Observable<Object>;
  accessToken$!: Observable<Object>;
  private postfixWord: string = 'with Github';
  gitUserData!: Object;
  gitUserEmails!: Array<Object>;

  @Input()
  btnType!: 'signup' | 'signin';

  constructor(
    private config: GithubRequestService,
    private router: Router,
    private toastr: ToastrService,
    private authenticateService: AuthenticateService
  ) {
    this.gitAccessCodeObserver = new Observable(
      (observer: Observer<string | null>) => {
        router.events.subscribe(() => {
          this.gitAuthCode = queryString.parse(this.router.url.split('?')[1]);
          const gitHubCode: string | undefined = this.gitAuthCode['code'];

          // If code does not exists
          if (!gitHubCode) return router.navigate(['/', auth.rootPath]);
          return observer.next(gitHubCode!);
        });
      }
    );

    this.gitTokenHandlerObserver = new Observable(
      (observer: Observer<Object>) => {
        this.gitAccessCodeObserver.subscribe((code: string | null) => {
          // Storing an observable stream
          this.accessToken$ = this.config.getAccessToken(code!);

          this.accessToken$.subscribe((tokenResponse: any) => {
            if (tokenResponse.error) {
              this.toastr.error(
                tokenResponse.error_description,
                tokenResponse.error.replace(/[_]/g, ' ')
              );
              this.router.navigate(['/', 'auth']);
            }

            // Passing the access token data for retrieving user information
            observer.next({
              token_type: tokenResponse.token_type,
              access_token: tokenResponse.access_token,
            });
          });
        });
      }
    );
  }

  ngOnInit(): void {
    this.githubLoginUrl = this.config.gitLoginUrl;
    this.btnTitle =
      this.btnType == 'signin'
        ? `Sign in ${this.postfixWord}`
        : `Sign up ${this.postfixWord}`;

    new Observable((observer: any) => {
      this.gitTokenHandlerObserver.subscribe((token: any) => {
        const userProfileInfo$ = this.config.fetchGitUserProfileFromToken(
          token.token_type,
          token.access_token
        );
        const userEmails$ = this.config.fetchGitUserEmailsFromToken(
          token.token_type,
          token.access_token
        );

        userProfileInfo$.subscribe((userInfo: any) => {
          observer.next(userInfo);
        });

        userEmails$.subscribe((mails: any) => {
          observer.next(mails);
        });
      });
    }).subscribe((userData: any) => {
      // Set each respective value
      switch (userData.constructor.name) {
        case 'Array':
          this.gitUserEmails = userData;
          break;
        case 'Object':
          this.gitUserData = userData;
          break;
      }

      // Check if both objects are set create an authModel
      // for a git signup/signin
      if (this.gitUserData! && this.gitUserEmails!) {
        const gitAuthModel: UserRequest =
          this.config.transformUserDataToModel(
            this.gitUserData,
            this.gitUserEmails,
            this.btnType
          );
        this.authenticateService.authenticate(gitAuthModel);
      }
    });
  }
}
