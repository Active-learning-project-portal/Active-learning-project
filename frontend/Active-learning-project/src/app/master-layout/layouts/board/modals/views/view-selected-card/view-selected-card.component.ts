import { Component, OnInit } from '@angular/core';
import { toNumber } from 'lodash';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { CardButtonAction, CardModel, CardStatus, activeCardPopupWindowState } from '../../../models/board-model';
import { ActiveCardService } from '../../../services/logic-handlers/active-card.service';
import { CardButtonActionService } from '../../../services/logic-handlers/card-button-action.service';
import { CardModalHandlerService } from '../../../services/modals/card-modal-handler.service';
import { getTimeFormattedString } from '../../../shared/utils/utils';
import { ViewCardService } from '../../../services/logic-handlers/view-card.service';
import { BoardService } from '../../../services/api/board.service';
import { AddExtraCardTimeComponent } from '../../add-extra-card-time/add-extra-card-time.component';

@Component({
  selector: 'alp-view-selected-card',
  templateUrl: './view-selected-card.component.html',
  styleUrls: ['./view-selected-card.component.css'],
})
export class ViewSelectedCardComponent implements OnInit {
  card!: CardModel;
  allowModalClosure!: boolean;
  cardStatus!: CardStatus;
  progressState: "danger" | "warning" | "success" | "primary" = 'danger'
  addTimeModalReference!: MdbModalRef<AddExtraCardTimeComponent>

  constructor(
    private viewCardService: ViewCardService,
    private activeCardService: ActiveCardService,
    private cardButtonActonService: CardButtonActionService,
    private mdbModalService: CardModalHandlerService<any>,
    private boardService: BoardService
  ) { }

  ngOnInit(): void {
    this.getCardColor();
    this.cardButtonActonService.calculateTaskCompletion(this.card);
  }

  getProgress(): number {
    return this.cardButtonActonService.getCardProgress();
  }

  getProgressValue(): number {
    const percentage = this.getProgress() * 100;

    if (percentage === 100) this.progressState = "success"
    else if (percentage >= 75) this.progressState = "primary"
    else if (percentage >= 50) this.progressState = "warning"
    else this.progressState = "danger"

    return percentage;
  }

  getCardColor() {
    if (this.card?.cardStatus) this.cardStatus = this.card?.cardStatus;
  }

  closeViewCardModal(): void {
    this.viewCardService.closeViewedCard();
  }

  isCardStarted(): activeCardPopupWindowState {
    return this.activeCardService.getActiveCardPopupWindowState();
  }

  onCardAction(actionType: CardButtonAction, card: CardModel) {
    this.cardButtonActonService.performButtonAction({
      actionType: actionType,
      card: card,
      modalReference: this.viewCardService.getViewedCardModalReference()
    });
  }

  addMoreTime() {
    this.mdbModalService.openMdbModal<AddExtraCardTimeComponent>({
      component: AddExtraCardTimeComponent,
      data: null,
      ignoreBackdropClick: false,
      width: 50
    })
      .onClose.subscribe((userExtraTime: string | null) => {
        if (userExtraTime) {
          const [newHours, newMinutes] = userExtraTime.split(':')
          const [durHours, durMinutes, durSeconds] = this.card.duration.split(":");
          const [tmHours, tmMinutes, tmSeconds] = this.card.timeRemaining.split(":");;

          this.card.duration = getTimeFormattedString(toNumber(durHours) + toNumber(newHours),
            toNumber(durMinutes) + toNumber(newMinutes), toNumber(durSeconds));

          this.card.timeRemaining = getTimeFormattedString(toNumber(tmHours) + toNumber(newHours),
            toNumber(tmMinutes) + toNumber(newMinutes), toNumber(tmSeconds));

          // Updatecardin the database
          this.boardService.updateCard(this.card)
            .subscribe((response: CardModel) => {
              console.log(response)

              this.activeCardService.activateCardCountDown(this.card);
              this.viewCardService.closeViewedCard();
            })
        }
      })
  }
}
