import { Component, OnInit } from '@angular/core';
import queryString from 'query-string'
import axios from 'axios'

@Component({
  selector: 'alp-github-signin',
  templateUrl: './github-signin.component.html',
  styleUrls: ['./github-signin.component.css']
})
export class GithubSigninComponent implements OnInit {
  githubParams!: string;
  githubLoginUrl!: string;
  gitClientId: string = '0b21a25c5d85b0a71f3d';
  gitClientSecret: string = 'f4720f1d20d22ba145293f976b5071ee4b83dca0';

  async getGithubAccessToken(code: string | (string | null)[]) {
    // const { data } = await axios({
    //   url: 'https://github.com/login/oauth/access_token',
    //   method: 'get',
    //   withCredentials: false,
    //   params: {
    //     client_id: this.gitClientId,
    //     client_secret: this.gitClientSecret,
    //     redirect_uri: 'http://localhost:4200/auth/user/signin',
    //     code,
    //   },
    //   headers: {
    //     'Access-Control-Allow-Origin': '*'
    //   },
    // });

    const { data } = await axios.get("https://github.com/login/oauth/access_token", {
      withCredentials: false,
      params: {
        client_id: this.gitClientId,
        client_secret: this.gitClientSecret,
        redirect_uri: 'http://localhost:4200/auth/user/signin',
        code,
      },
      headers: {
        'Access-Control-Allow-Origin': '*'
      }
    })

    const parsedData = queryString.parse(data);
    console.log(parsedData)
  }

  ngOnInit(): void {
    this.githubParams = queryString.stringify({
      client_id: this.gitClientId,
      redirect_uri: `http://localhost:4200/auth/user/signin`,
      scope: ['read:user', 'user:email'].join(' '),
      allow_signup: true,
    });

    this.githubLoginUrl = `https://github.com/login/oauth/authorize?${this.githubParams}`;
    const gitCode = queryString.parse(window.location.search)['code'];

    if (gitCode) {
      this.getGithubAccessToken(gitCode)
    }
  }

}
