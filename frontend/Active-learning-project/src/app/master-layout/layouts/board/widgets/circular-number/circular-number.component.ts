import { Component, Input, OnInit } from '@angular/core';
import { CardStatus } from '../../models/board-model';

@Component({
  selector: 'alp-circular-number',
  templateUrl: './circular-number.component.html',
  styleUrls: ['./circular-number.component.css']
})
export class CircularNumberComponent implements OnInit {
  @Input()
  cardCount!: number;

  @Input()
  activeClass!: CardStatus;

  constructor() { }

  ngOnInit(): void {
  }

}
