import { Component } from '@angular/core';
import { faBell, faUser, faMessage, faCog } from '@fortawesome/free-solid-svg-icons';
// import {  } from '@fortawesome/free-regular-svg-icons';

@Component({
  selector: 'alp-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent {
  notification = faBell
  user = faUser
  messages = faMessage
  settings = faCog
}
