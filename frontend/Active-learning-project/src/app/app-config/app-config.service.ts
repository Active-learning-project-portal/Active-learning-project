import { InjectionToken } from '@angular/core';
import { environment } from 'src/environments/environments';
import { AppConfig } from './app-config.interface';

export const APP_SERVICE_CONFIG = new InjectionToken<AppConfig>('app.config');

export const APP_CONFIG: AppConfig = {
  apiRoute: environment.apiUrl,
  googleAuthClientID: environment.googleAuthClientID,
  clientURL: environment.clientURL
};
