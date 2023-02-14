import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import queryString from 'query-string';
import { Observable, Observer } from 'rxjs';
import { UserAuthModel } from 'src/app/models/payloads/requests/user.auth.model';
import { auth } from 'src/app/shared/routers/Routers';
import { GithubRequestService } from '../services/github-request.service';

@Component({
  selector: 'alp-github-button',
  templateUrl: './github-button.component.html',
  styleUrls: ['./github-button.component.css']
})
export class GithubButtonComponent {
  githubLoginUrl!: string;
  gitAuthCode!: any;
  btnTitle!: string;
  postfixWord: string = "with Github";
  gitAccessToken!: string | null;
  gitTokenType!: string | null;
  gitAccessCode!: Observable<unknown>
  accessToken$!: Observable<Object>;

  @Input()
  btnType!: 'signup' | 'signin';

  // complete() {
  //   if (gitAccessToken && gitTokenType) {
  //     config.fetchUserInformationFromToken(gitTokenType, gitAccessToken)
  //       .subscribe({
  //         next(userData: any) {
  //           userName = userData.name;
  //           userAvatar = userData.avatar_url;
  //         },
  //         complete() {
  //           config.fetchUserEmailsFromToken(gitTokenType!, gitAccessToken!)
  //             .subscribe({
  //               next(userEmails: any) {
  //                 const mailList = userEmails.filter((mail: any) => !!mail.primary)

  //                 authModel = {
  //                   firstname: userName.split(" ")[0],
  //                   lastname: userName.split(" ")[1],
  //                   email: mailList[0].email,
  //                   provider: "GITHUB",
  //                   authType: null
  //                 }
  //               },
  //               complete() {
  //                 console.log(authModel)
  //               },
  //               error(err) {
  //                 console.error(err)
  //               },
  //             })
  //         },
  //         error(err) {
  //           console.error(err)
  //         }
  //       })
  //   }
  //   else router.navigate(['/', auth.rootPath, auth.person, auth.action.signin])
  // },

  constructor(private config: GithubRequestService, private router: Router) {
    this.gitAccessCode = new Observable((observer: Observer<string | null>) => {
      // observer.next(gitHubCode)
      router.events.subscribe(() => {
        this.gitAuthCode = queryString.parse(this.router.url.split('?')[1]);
        const gitHubCode: string | undefined = this.gitAuthCode['code'];

        // If code does not exists
        if (!gitHubCode) return router.navigate(['/', auth.rootPath, auth.person, auth.action.signin]);
        return observer.next(gitHubCode!);
      });
    })
  }

  ngOnInit(): void {
    this.githubLoginUrl = this.config.gitLoginUrl;
    this.btnTitle = this.btnType == 'signin' ? `Sign in ${this.postfixWord}` : `Sign up ${this.postfixWord}`

    this.gitAccessCode.subscribe((code: any) => {
      // codeObserver.unsubscribe();

      this.accessToken$ = this.config.getAccessToken(code)
      // this.config.getAccessToken(gitHubCode!).subscribe((token: any) => {
      //   this.gitAccessToken = token.access_token;
      //   this.gitTokenType = token.token_type;


      // });
    })

    this.accessToken$?.subscribe(token => {
      console.log(token)
    })
  }
}
