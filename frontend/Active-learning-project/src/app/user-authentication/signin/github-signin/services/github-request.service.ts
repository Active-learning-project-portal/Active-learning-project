import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import queryString from 'query-string';

@Injectable({
  providedIn: 'root',
})
export class GithubRequestService {
  private gitClientId: string = 'e04b86df24f7cbf794c8';
  private gitClientSecret: string = 'b758bc4642bcb6af67c8f32ea11bcc6c00eb4585';
  private githubParams!: string;
  private githubLoginUrl!: string;

  constructor(private http: HttpClient) {
    this.githubParams = queryString.stringify({
      client_id: this.gitClientId,
      redirect_uri: `http://localhost:4200/auth/user/signin`,
      scope: ['read:user', 'user:email'].join(' '),
      allow_signup: true,
    });

    this.githubLoginUrl = `/login/oauth/authorize?${this.githubParams}`;
  }

  get gitLoginUrl() {
    return this.githubLoginUrl;
  }

  getAccessToken(code: any) {
    return this.http.get('/login/oauth/access_token', {
      params: {
        client_id: this.gitClientId,
        client_secret: this.gitClientSecret,
        redirect_uri: 'http://localhost:4200/auth/user/signin',
        code,
      },
    });
  }
}
