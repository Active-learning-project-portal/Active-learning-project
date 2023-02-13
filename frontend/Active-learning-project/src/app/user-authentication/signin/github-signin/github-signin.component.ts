import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import queryString from 'query-string';
import { Subscription } from 'rxjs';
import { GithubRequestService } from './services/github-request.service';

@Component({
  selector: 'alp-github-signin',
  templateUrl: './github-signin.component.html',
  styleUrls: ['./github-signin.component.css'],
})
export class GithubSigninComponent implements OnInit {
  githubLoginUrl!: string;
  gitAuthCode!: any;
  $token!: Subscription;

  constructor(private config: GithubRequestService, private router: Router) {
    router.events.subscribe(() => {
      this.gitAuthCode = queryString.parse(this.router.url.split('?')[1]);
      const gitHubCode = this.gitAuthCode['code'];
      console.log(gitHubCode)
      if (gitHubCode) {
        this.config.getAccessToken(gitHubCode).subscribe({
          next(token) {
            console.log(token);
          },
          complete() {
            console.log('Done!!');
          },
          error(err) {
            console.error(err);
          },
        });
      } else {
        this.router.navigate(['/', 'auth']);
      }
    });
  }

  ngOnInit(): void {
    this.githubLoginUrl = this.config.gitLoginUrl;
  }
}
