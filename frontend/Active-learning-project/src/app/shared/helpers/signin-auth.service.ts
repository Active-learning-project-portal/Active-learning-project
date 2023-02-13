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
        'Access-Control-Allow-Methods': 'GET, PUT, POST',
        'Access-Control-Allow-Headers': 'X-PINGOTHER, Content-Type',
        'Content-Type': 'aplication/json'
      },
      withCredentials: false
    })
    return next.handle(cors)
  }
}
