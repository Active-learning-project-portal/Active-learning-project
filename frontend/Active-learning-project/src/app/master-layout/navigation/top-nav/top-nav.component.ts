import { Component, EventEmitter, Input, Output } from '@angular/core';
import { faBars, faGraduationCap, faSearch } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'alp-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent {
  faSolidBars = faBars
  searchIcon = faSearch
  graduation = faGraduationCap

  @Input()
  menuOpen!: boolean

  @Output()
  toggleMenuEmitter!: EventEmitter<boolean>

  constructor(){
    this.toggleMenuEmitter = new EventEmitter();
  }

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
    this.toggleMenuEmitter.emit(this.menuOpen);
  }
}
