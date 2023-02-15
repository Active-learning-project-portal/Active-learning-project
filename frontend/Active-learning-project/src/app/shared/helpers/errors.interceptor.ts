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
          this.exceptionsHandler = JSON.parse(error.error);
        } else {
          switch (error.status) {
            case ServerErrors.badRequest:
            case ServerErrors.ok:
            case ServerErrors.unauthorized:
              errorObject = {
                title: 'Server error',
                message: error.error.message,
              };
              break;
            default:
              errorObject = {
                title: 'Unknown error',
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
