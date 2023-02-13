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

  exceptionsHandler !: any;

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const cors = request.clone({
      setHeaders: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': "PUT, POST, GET, DELETE, PATCH, OPTIONS",
        'Access-Control-Allow-Headers': 'X-PINGOTHER, Content-Type',
        "Access-Control-Allow-Credentials": "true",
        "Access-Control-Max-Age": "1800",
        'Content-Type': 'application/json',
        'Accept': 'application/json'

      },
      withCredentials: false
    })
    
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 0) { // probably not
          //client  side issues,
          //Can we render alert from here?? probably warning etc
          this.exceptionsHandler = JSON.parse(error.error);
        } else {
          //Something on backend happened,
          //Can we render alert from here?? probably error etc
          // console.log("I am error : "+error)
          console.log(error.error)
          // this.exceptionsHandler = JSON.parse(error.error);
          // console.log("I am error 2 : "+this.exceptionsHandler)
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
