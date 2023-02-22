import { Component } from '@angular/core';
import { faBars, faSearch } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'alp-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent {
  faSolidBars = faBars
  searchIcon = faSearch
}
