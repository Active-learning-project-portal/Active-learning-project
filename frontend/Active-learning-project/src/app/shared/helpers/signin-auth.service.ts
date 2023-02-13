import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SigninAuthService implements HttpInterceptor {

  constructor() { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler) {
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
    return next.handle(cors)
  }
}
