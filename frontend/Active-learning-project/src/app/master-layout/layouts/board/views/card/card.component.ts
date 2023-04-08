import { Component, Input, OnInit } from '@angular/core';
import { ActiveCardService } from '../../services/logic-handlers/active-card.service';
import { ViewCardService } from '../../services/logic-handlers/view-card.service';
import { CardModel, activeCardPopupWindowState } from '../../models/board-model';


@Component({
  selector: 'alp-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'],
})
export class CardComponent implements OnInit {
  @Input()
  card!: CardModel;

  constructor(
    private activeCardService: ActiveCardService,
    private viewCardService: ViewCardService
  ) { }

  ngOnInit(): void { }

  grab(event: any) {
    const { target } = event;
    target.style.cursor = 'grabbing';
  }

  release(event: any) {
    const { target } = event;
    target.style.cursor = 'grab';
  }

  isCardStarted(): activeCardPopupWindowState {
    return this.activeCardService.getActiveCardPopupWindowState();
  }

  onViewCard(card?: CardModel): void {
    this.viewCardService.viewSelectedCard(card!)
  }

  onCloseCard(): void {
    this.viewCardService.closeViewedCard()
  }

  getActiveState(): activeCardPopupWindowState {
    return this.activeCardService.getActiveCardPopupWindowState()
  }
}
