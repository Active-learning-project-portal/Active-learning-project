import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import queryString from 'query-string';
import { Observable, Observer } from 'rxjs';
import { UserRequest } from 'src/app/models/payloads/requests/user.auth.request.model';
import { environment } from 'src/environments/environment';

@Injectable({
	providedIn: 'root'
})
export class GithubRequestService {
	private githubParams!: string;
	private githubLoginUrl!: string;

	constructor(private http: HttpClient) {
		this.githubParams = queryString.stringify({
			client_id: environment.gitClientId,
			redirect_uri: `http://localhost:4200/auth/user/signin`,
			scope: ['read:user', 'user:email'].join(' '),
			allow_signup: true,
		});

		this.githubLoginUrl = `/login/oauth/authorize?${this.githubParams}`;
	}

	get gitLoginUrl() {
		return this.githubLoginUrl;
	}

	getAccessToken(code: string): Observable<Object> {
		return this.http.post('/login/oauth/access_token', {
			client_id: environment.gitClientId,
			client_secret: environment.gitClientSecret,
			redirect_uri: 'http://localhost:4200/auth/user/signin',
			code,
		});
	}

	fetchGitUserProfileFromToken(
		token_type: string,
		access_token: string
	): Observable<Object> {
		return new Observable((observer: Observer<Object>) => {
			this.http
				.get('/user', {
					headers: {
						Authorization: `${token_type} ${access_token}`,
					},
				})
				.subscribe((userData: Object) => {
					observer.next(userData);
				});
		});
	}

	fetchGitUserEmailsFromToken(
		token_type: string,
		access_token: string
	): Observable<Object[]> {
		return new Observable((observer: Observer<Object[]>) => {
			this.http
				.get('/user/emails', {
					headers: {
						Authorization: `${token_type} ${access_token}`,
					},
				})
				.subscribe((userMails: any) => {
					observer.next(userMails);
				});
		});
	}

	getPrimaryEmail(mails: Array<any>): string {
		return mails.filter((mail) => !!mail.primary)[0].email;
	}

	getFirstName(name: string): string {
		return name.split(' ')[0];
	}

	getLastName(name: string): string {
		return name.split(' ')[1];
	}

	transformUserDataToModel(
		userProfile: any,
		userMails: Array<Object>,
		authType: 'signin' | 'signup'
	): UserRequest {
		return {
			firstname: this.getFirstName(userProfile.name),
			lastname: this.getLastName(userProfile.name),
			username: this.getPrimaryEmail(userMails),
			avatar: userProfile.avatar_url,
			password: '',
			provider: 'GITHUB',
			authType: authType,
		};
	}
}
