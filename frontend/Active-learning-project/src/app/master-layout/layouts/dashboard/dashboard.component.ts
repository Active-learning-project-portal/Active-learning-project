import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'alp-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  currentLocation!: string;

  constructor(private router: Router) {
    router.events.subscribe((event: any) => {
      const currentUrl = event?.routerEvent?.url;
      this.currentLocation = currentUrl.replace(/^[\/]/, '').trim()
      this.currentLocation = this.currentLocation.replace(/[\/]/g, ' > ').trim();
    })
  }

  ngOnInit(): void {
    // throw new Error('Method not implemented.');
  }
}
