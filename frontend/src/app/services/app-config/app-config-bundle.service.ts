import { Inject, Injectable } from '@angular/core';
import { AppConfig } from './app-config.interface';
import { APP_CONFIG } from './app-config.service';

@Injectable({
  providedIn: "root"
})
export class AppConfigBundleService {
  constructor(@Inject(APP_CONFIG) private config: AppConfig) { }

  get apiRoute(): string {
    return this.config.apiRoute;
  }

  get googleAuthClientID(): string {
    return this.config.googleAuthClientID;
  }

  get clientUrl(): string {
    return this.config.clientURL;
  }
}
