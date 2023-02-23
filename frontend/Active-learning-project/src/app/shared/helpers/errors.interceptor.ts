import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { ServerErrors } from 'src/app/models/server-errors/server-errors';

@Injectable()
export class ErrorsInterceptor implements HttpInterceptor {
  exceptionsHandler!: any;

  constructor() {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    let errorObject = {};
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 0) {
          console.log(error);
          if (!error.ok) {
            errorObject = {
              message: 'Server not started, contact operational@alp.com',
            };
          }
        } else {
          switch (error.status) {
            case ServerErrors.BAD_REQUEST:
            case ServerErrors.NOT_FOUND:
              errorObject = {
                message: error.error.message,
              };
              break;
            case ServerErrors.UNAUTHORIZED:
              errorObject = {
                message: 'You are not authorized',
              };
              break;
            default:
              errorObject = {
                message: 'Something bad happen, we know about this ...',
              };
              break;
          }
        }
        return throwError(errorObject);
      })
    );
  }
}
