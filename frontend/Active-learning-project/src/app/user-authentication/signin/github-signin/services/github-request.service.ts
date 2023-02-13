import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import queryString from 'query-string';

@Injectable({
  providedIn: 'root'
})
export class GithubRequestService {
  private gitClientId: string = '0b21a25c5d85b0a71f3d';
  private gitClientSecret: string = 'f4720f1d20d22ba145293f976b5071ee4b83dca0';
  private githubParams!: string;
  private githubLoginUrl!: string;

  constructor(private http: HttpClient) {
    this.githubParams = queryString.stringify({
      client_id: this.gitClientId,
      redirect_uri: `http://localhost:4200/auth/user/signin`,
      scope: ['read:user', 'user:email'].join(' '),
      allow_signup: true,
    });

    this.githubLoginUrl = `https://github.com/login/oauth/authorize?${this.githubParams}`;
  }

  get gitLoginUrl() {
    return this.githubLoginUrl;
  }

  getAccessToken(code: any) {
    return this.http.get("https://github.com/login/oauth/access_token", {
      params: {
        client_id: this.gitClientId,
        client_secret: this.gitClientSecret,
        redirect_uri: 'http://localhost:4200/auth/user/signin',
        code
      }
    })
  }
}
