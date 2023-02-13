import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'alp-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Active-learning-project';

  constructor(public toastr: ToastrService) {}

  showSuccess() {
    this.toastr.success('everything is broken', 'Major Error', {
      timeOut: 3000,
    });
  }

  showError() {
    this.toastr.error('everything is broken', 'Major Error', {
      timeOut: 3000,
    });
  }

  showInfo() {
    this.toastr.info('everything is broken', 'Major Error', {
      timeOut: 3000,
    });
  }

  showWarning() {
    this.toastr.warning('everything is broken', 'Major Error', {
      timeOut: 3000,
    });
  }
}
