import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AccountModule } from './user-authentication/account.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AccountRoutingModule } from './user-authentication/account-routing.module';
import { MasterLayoutModule } from './master-layout/master-layout.module';
import {
  SocialLoginModule,
  GoogleLoginProvider,
} from '@abacritt/angularx-social-login';
import { environment } from 'src/environments/environment';
import { SigninAuthService } from './shared/helpers/signin-auth.service';
import { AuthenticateService } from './user-authentication/services/authenticate.service';
import { ErrorsInterceptor } from './shared/helpers/errors.interceptor';
import {
  APP_CONFIG,
  APP_SERVICE_CONFIG,
} from './app-config/app-config.service';
import { FullNamePipe } from './shared/pipes/full-name.pipe';


@NgModule({
  declarations: [AppComponent, FullNamePipe],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ToastrModule.forRoot({
      positionClass: 'toast-bottom-right',
      preventDuplicates: true,
      progressBar: true,
    }),
    AccountRoutingModule,
    AccountModule,
    MasterLayoutModule,
    SocialLoginModule,
    HttpClientModule,
  ],


  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: SigninAuthService,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorsInterceptor,
      multi: true,
    },
    {
      provide: APP_SERVICE_CONFIG,
      useValue: APP_CONFIG,
    },
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(environment.googleAuthClientID),
          },
        ],
        onError: (err: any) => {
          console.error(err);
        },
      },
    },
    AuthenticateService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
