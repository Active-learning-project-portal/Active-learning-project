import { Injectable } from '@angular/core';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { Subscription } from 'rxjs';
import {
  getCompletedTasks,
  getRandomCustomPauseMessage,
  getRandomCustomSuccessMessage,
  transferCard,
} from '../helpers/service.helpers';

import { ActiveCardService } from './active-card.service';
import { CardCommentService } from './card-comment.service';
import { pausedState, archivedState, backlogState, startedState, completedState } from '../../shared/constants/card-states';
import { BoardService } from '../api/board.service';
import { ToastrMessagesService } from '../../shared/utils/services/toastr-messages.service';
import { CardModel, CardStatus, CardTaskModel, CardTypes } from '../../models/board-model';
import { maxCustomToastrTimeout, maxPauseCount, maxToastrTimeout } from '../../shared/constants/card-boundaries';
import { ViewSelectedCardComponent } from '../../modals/views/view-selected-card/view-selected-card.component';
import { CardValidationHandlerService } from '../validations/card-validation-handler.service';
import { ButtonActionParameters } from '../interfaces/view-card.interface';
import { AddExtraCardTimeComponent } from '../../modals/add-extra-card-time/add-extra-card-time.component';

@Injectable({
  providedIn: 'root',
})
export class CardButtonActionService {
  private viewCardModalRef!: MdbModalRef<ViewSelectedCardComponent>;
  private extraTimeModalRef!: MdbModalRef<AddExtraCardTimeComponent>;
  private cardProgress: number = 0;

  constructor(
    private boardService: BoardService,
    private toastrMessages: ToastrMessagesService,
    private activeCardService: ActiveCardService,
    private cardCommentService: CardCommentService,
    private modalService: MdbModalService,
    private cardValidationsService: CardValidationHandlerService
  ) {}

  public performButtonAction({
    actionType,
    card,
    modalReference,
  }: ButtonActionParameters) {
    this.setviewCardModalReference(modalReference);

    switch (actionType) {
      case 'start':
        this.startCard(card);
        break;
      case 'pause':
        this.pauseCard(card);
        break;
      case 'complete':
        this.completeCard(card);
        break;
      case 'archive':
        this.archiveCard(card);
        break;
      case 'restore':
        this.restoreCard(card);
        break;
      case 'resume':
        this.resumeCard(card);
        break;
    }
  }

  private setviewCardModalReference(
    modalRef: MdbModalRef<ViewSelectedCardComponent>
  ): void {
    this.viewCardModalRef = modalRef;
  }

  private getviewCardModalReference(): MdbModalRef<ViewSelectedCardComponent> {
    return this.viewCardModalRef;
  }

  private closeViewCardModal(): void {
    this.getviewCardModalReference().close();
  }

  private getCardTypeObjectList(): CardTypes {
    return this.boardService.getCardTypeObjectList();
  }

  private pauseCardDBUpdate(card: CardModel) {
    // Changing the card state
    card.cardStatus = pausedState;

    // Incrementing the [pauseCount]
    card.pausedCount += 1;

    // Updating card changes in the database
    this.boardService
      .updateCard(card)
      .subscribe((response: CardModel) => {
        console.log(response);

        transferCard(
          this.getCardTypeObjectList().started,
          this.getCardTypeObjectList().paused,
          card
        );

        this.activeCardService.deactivateCurrentActiveCard();

        this.toastrMessages.showInfoMessage(
          'Encouragement',
          getRandomCustomPauseMessage(),
          maxCustomToastrTimeout
        );
      });
  }

  private archiveCardDBUpdate(card: CardModel) {
    // Store the previous status for use later
    const previousCardContainer: CardStatus = card.cardStatus;

    // Change the card status
    card.cardStatus = archivedState;

    // Updating card changes in the database
    this.boardService
      .updateCard(card)
      .subscribe((response: CardModel) => {
        console.log(response);

        switch (previousCardContainer) {
          case backlogState:
            transferCard(
              this.getCardTypeObjectList().backlog,
              this.getCardTypeObjectList().archived,
              card
            );
            break;
          case startedState:
            transferCard(
              this.getCardTypeObjectList().started,
              this.getCardTypeObjectList().archived,
              card
            );
            this.activeCardService.deactivateCurrentActiveCard();
            break;
          case pausedState:
            transferCard(
              this.getCardTypeObjectList().paused,
              this.getCardTypeObjectList().archived,
              card
            );
            break;
        }

        this.closeViewCardModal();

        if (card.archivedCount % 3 === 0)
          this.toastrMessages.showInfoMessage(
            'Archive card',
            'Please note that frequent archiving of your cards may affect your progress tracking and hinder your ability to achieve your desired outcomes.',
            maxToastrTimeout
          );
      });
  }

  private startCard(card: CardModel): void {
    // Guard condition
    if (
      !this.cardValidationsService.canCardBeStarted(
        this.getCardTypeObjectList().started
      )
    )
      return;

    // Proceed starting the card
    if (card.cardStatus === backlogState) {
      card.cardStatus = startedState;

      this.boardService
        .updateCard(card)
        .subscribe((response: CardModel) => {
          console.log(response);

          transferCard(
            this.getCardTypeObjectList().backlog,
            this.getCardTypeObjectList().started,
            card
          );

          // Start the newly created card in the started list
          this.activeCardService.activateCardCountDown(
            this.getCardTypeObjectList().started[
              this.getCardTypeObjectList().started.length - 1
            ]
          );

          // Close the modal
          this.closeViewCardModal();
        });
    } else if (card.cardStatus === archivedState) {
      card.cardStatus = startedState;

      this.boardService
        .updateCard(card)
        .subscribe((response: CardModel) => {
          console.log(response);

          transferCard(
            this.getCardTypeObjectList().archived,
            this.getCardTypeObjectList().started,
            card
          );

          // Start the newly created card in the started list
          this.activeCardService.activateCardCountDown(
            this.getCardTypeObjectList().started[
              this.getCardTypeObjectList().started.length - 1
            ]
          );

          // Close the modal
          this.closeViewCardModal();
        });
    }
  }

  private pauseCard(card: CardModel) {
    if (card.pausedCount > 0 && card.pausedCount % maxPauseCount === 0) {
      // Open the comment dialog
      const onCommentResponse: Subscription = this.cardCommentService
        .onCapturedUserComment(pausedState)
        .subscribe((userComment: string | null) => {
          // Unsubscribing from the observer listener to avoid mulitple emits
          onCommentResponse.unsubscribe();

          if (userComment) {
            // If a user decides to archive a card directly from [in-progress] state, stop the countdown window
            this.activeCardService.deactivateCurrentActiveCard();

            card?.comments?.push({
              comment: userComment,
              commentType: 'paused',
              cardId: card.id,
            });

            this.pauseCardDBUpdate(card);
          }
          // Close the modal
          this.closeViewCardModal();
        });
    } else {
      this.pauseCardDBUpdate(card);
      // Close the modal
      this.closeViewCardModal();
    }
  }

  private archiveCard(card: CardModel) {
    const userCommentSubscription: Subscription = this.cardCommentService
      .onCapturedUserComment(archivedState)
      .subscribe((userComment: string | null) => {
        // Unsubscribing from the observer listener to avoid mulitple emits
        userCommentSubscription.unsubscribe();

        if (userComment) {
          // If a user decides to archive a card directly from [in-progress] state, stop the countdown window
          // this.activeCardService.deactivateCurrentActiveCard();

          // Set the user comment for archiving a card!
          card?.comments?.push({
            comment: userComment,
            commentType: 'archived',
            cardId: card.id,
          });

          // Changing the card metadata
          card.pausedCount = 0;
          card.archivedCount += 1;
          card.timeRemaining = card.duration;
          card?.tasks?.forEach((element: CardTaskModel) => {
            element.complete = false;
          });

          // Updating card changes in the database
          this.archiveCardDBUpdate(card);
        }
      });
  }

  private resumeCard(card: CardModel) {
    // Guard condition
    if (
      !this.cardValidationsService.canCardBeStarted(
        this.getCardTypeObjectList().started
      )
    )
      return;

    // Change card status
    card.cardStatus = startedState;

    // Updating card changes in the database
    this.boardService
      .updateCard(card)
      .subscribe((response: CardModel) => {
        console.log(response);

        transferCard(
          this.getCardTypeObjectList().paused,
          this.getCardTypeObjectList().started,
          card
        );

        // Start the newly created card in the started list
        this.activeCardService.activateCardCountDown(
          this.getCardTypeObjectList().started[
            this.getCardTypeObjectList().started.length - 1
          ]
        );

        this.closeViewCardModal();
      });
  }

  private completeCard(card: CardModel) {
    if (card?.tasks && card?.tasks?.length > 0) {
      if (
        !this.cardValidationsService.canCompleteCard(
          getCompletedTasks(card?.tasks),
          card?.tasks
        )
      )
        return;
    }

    // Change card status
    card.cardStatus = completedState;

    // Updating card changes in the database
    this.boardService
      .updateCard(card)
      .subscribe((response: CardModel) => {
        console.log(response);

        transferCard(
          this.getCardTypeObjectList().started,
          this.getCardTypeObjectList().completed,
          card
        );

        // Stop the countdown timer
        this.activeCardService.deactivateCurrentActiveCard();

        // Close the modal!
        this.closeViewCardModal();

        // Show an encouraging message
        this.toastrMessages.showSuccessMessage(
          'Congradulations',
          getRandomCustomSuccessMessage(),
          20000
        );
      });
  }

  private restoreCard(card: CardModel) {
    // Change card status
    card.cardStatus = backlogState;

    // Updating card changes in the database
    this.boardService
      .updateCard(card)
      .subscribe((response: CardModel) => {
        console.log(response);

        transferCard(
          this.getCardTypeObjectList().archived,
          this.getCardTypeObjectList().backlog,
          card
        );

        // Close the modal!
        this.closeViewCardModal();
      });
  }

  public calculateTaskCompletion(card: CardModel): void {
    if (card?.tasks && card?.tasks?.length > 0) {
      const completedTasks = card?.tasks?.filter(
        (task) => task.complete === true
      );
      this.cardProgress = completedTasks.length / card?.tasks?.length;
    } else this.cardProgress = 0;
  }

  public getCardProgress(): number {
    return this.cardProgress;
  }
}
