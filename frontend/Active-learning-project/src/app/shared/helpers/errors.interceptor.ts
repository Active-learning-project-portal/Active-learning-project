import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable()
export class ErrorsInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 0) { // probably not
          //client  side issues,
          //Can we render alert from here?? probably warning etc
        } else {
          //Something on backend happened,
          //Can we render alert from here?? probably error etc
        }
        return throwError(
          () =>
            new Error(
              'message to return to the subscription'
            )
        );
      })
    );
  }
}
