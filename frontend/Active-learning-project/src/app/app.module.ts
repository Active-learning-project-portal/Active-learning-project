import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ToastrModule} from 'ngx-toastr'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { AccountModule } from './account/account.module';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { ErrorsInterceptor } from './shared/helpers/errors.interceptor';
import { AccountRoutingModule } from './account/account-routing.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ToastrModule.forRoot({
      positionClass: 'toast-bottom-right',
      preventDuplicates: true,
      progressBar: true
    }),
    AccountModule,
    AccountRoutingModule
   
  ],
  providers: [ {
    provide: HTTP_INTERCEPTORS,
    useClass: ErrorsInterceptor,
    multi: true,
  },],
  bootstrap: [AppComponent]
})
export class AppModule { }
