import { Injectable } from '@angular/core';
import { BehaviorSubject, interval, Observable, Subscription } from 'rxjs';
import { activeCardPopupWindowState, CardModel } from '../../models/board-model';
import { BoardService } from '../api/board.service';
import { ViewCardService } from './view-card.service';
import { getSessionStorageValue, removeSessionStoragePair, getTimeFormated, setSessionStoragePairs } from '../../shared/utils/utils';

@Injectable({
  providedIn: 'root'
})
export class ActiveCardService {
  popupCardWindowState: activeCardPopupWindowState = "close";
  currentCard!: CardModel
  timeRemaining!: string
  countDownTimerBehaviourSubject!: BehaviorSubject<string>
  $intervalStream: Subscription | null = null

  constructor(
    private viewCardService: ViewCardService,
    private boardService: BoardService
  ) {
    this.countDownTimerBehaviourSubject = new BehaviorSubject<string>("00:00:00");
  }

  setActiveCard(activatingCard: CardModel): void {
    this.popupCardWindowState = "open"
    this.currentCard = activatingCard;
  }

  deactivateCurrentActiveCard(clearSession: boolean = true): void {
    if (this.$intervalStream) this.$intervalStream.unsubscribe();
    if (this.popupCardWindowState = "open") this.popupCardWindowState = "close"

    if (clearSession) {
      if (getSessionStorageValue('activeCardSession')) removeSessionStoragePair('activeCardSession')
      if (getSessionStorageValue('warningShown')) removeSessionStoragePair('warningShown')
    }
  }

  getActiveCardPopupWindowState(): activeCardPopupWindowState {
    return this.popupCardWindowState;
  }

  getActiveCardObject() {
    return this.currentCard;
  }

  getIntegerFromString(timepart: string): number {
    return parseInt(timepart);
  }

  getCardDuration(timesplit: Array<string>): Date {
    const startDateTime = new Date();
    const [hours, minutes, seconds] = timesplit

    // Setting the endtime for the card from the current timestamp!
    const endDateTime = new Date();
    endDateTime.setHours(endDateTime.getHours() + this.getIntegerFromString(hours))
    endDateTime.setMinutes(endDateTime.getMinutes() + this.getIntegerFromString(minutes))
    endDateTime.setSeconds(endDateTime.getSeconds() + this.getIntegerFromString(seconds))

    const cardDuration = new Date(endDateTime)
    // Setting up the card duration time from the current time!
    cardDuration.setHours(cardDuration.getHours() - startDateTime.getHours())
    cardDuration.setMinutes(cardDuration.getMinutes() - startDateTime.getMinutes())
    cardDuration.setSeconds(cardDuration.getSeconds() - startDateTime.getSeconds())

    return cardDuration;
  }

  activateCardCountDown(card: CardModel): void {
    // Check if active card is running and deactivate before creating a new interval instance
    this.deactivateCurrentActiveCard(false);

    // Set the new card instance
    this.setActiveCard(card);

    // console.log(startDateTime, endDateTime)
    const cardDuration = this.getCardDuration(this.currentCard.timeRemaining.split(":"));

    this.$intervalStream = interval(1000).subscribe(() => {
      if (cardDuration.getHours() === 0
        && cardDuration.getMinutes() === 0
        && cardDuration.getSeconds() === 0) {
        this.boardService.updateCard(card).subscribe((updatedCard: CardModel) => {
          card = updatedCard;

          this.deactivateCurrentActiveCard(false);
          // Open the ADD EXTRA TIME DIALOG!!
          this.viewCardService.viewSelectedCard(this.getActiveCardObject(), true)
        })
        return;
      }

      cardDuration.setSeconds(cardDuration.getSeconds() - 1)
      card.timeRemaining = getTimeFormated(cardDuration);

      // Emit the current remaining time
      this.countDownTimerBehaviourSubject.next(card.timeRemaining)

      // Create a Memento for the current timestamp
      setSessionStoragePairs("activeCardSession", JSON.stringify({
        id: card.id,
        timeRemaining: card.timeRemaining
      }))
    })
  }

  getCountDownTimerBehaviourSubject(): Observable<string> {
    return this.countDownTimerBehaviourSubject!;
  }
}

