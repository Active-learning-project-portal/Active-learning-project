import { Component, Input, OnInit } from '@angular/core';
import { CardModel, CardStatus } from '../../models/board-model';


@Component({
  selector: 'alp-card-column-list',
  templateUrl: './card-column-list.component.html',
  styleUrls: ['./card-column-list.component.css'],
})
export class CardColumnListComponent implements OnInit {
  @Input()
  cardsList!: Array<CardModel>;

  @Input()
  connectedIDList!: Array<string>;

  @Input()
  onDropRef!: any;

	@Input()
	listID!: CardStatus

  constructor() {}

  ngOnInit(): void {}
}
