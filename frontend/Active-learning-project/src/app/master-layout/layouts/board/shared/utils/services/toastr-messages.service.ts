import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class ToastrMessagesService {

  constructor(private toastrService: ToastrService) { }

  showErrorMessage(messageTitle: string, message: string, duration: number = 1000): void {
    this.toastrService.error(message, messageTitle, { timeOut: duration });
  }

  showWarningMessage(messageTitle: string, message: string, duration: number = 1000): void {
    this.toastrService.warning(message, messageTitle, { timeOut: duration });
  }

  showInfoMessage(messageTitle: string, message: string, duration: number = 1000): void {
    this.toastrService.info(message, messageTitle, { timeOut: duration });
  }

  showSuccessMessage(messageTitle: string, message: string, duration: number = 1000): void {
    this.toastrService.success(message, messageTitle, { timeOut: duration });
  }
}
