import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { Subscription } from 'rxjs';

import {
  archivedState,
  backlogState,
  completedState,
  pausedState,
  startedState,
} from './shared/constants/card-states';
import { ToastrMessagesService } from './shared/utils/services/toastr-messages.service';
import { ViewSelectedCardComponent } from './modals/views/view-selected-card/view-selected-card.component';
import { BoardService } from './services/api/board.service';
import {
  getCompletedTasks,
  getPastTenseFromCardState,
  getPresentTenseFromCardState,
  getRandomCustomPauseMessage,
  getRandomCustomSuccessMessage,
} from './services/helpers/service.helpers';
import { ActiveCardService } from './services/logic-handlers/active-card.service';
import { CaptureCardService } from './services/logic-handlers/capture-card.service';
import { CardCommentService } from './services/logic-handlers/card-comment.service';
import { CardValidationHandlerService } from './services/validations/card-validation-handler.service';
import { CardTaskModel, CardTypes } from './models/board-model';
import { maxCustomToastrTimeout, maxPauseCount, maxToastrTimeout } from './shared/constants/card-boundaries';

@Component({
  selector: 'alp-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent implements OnInit, OnDestroy {
  modalRef: MdbModalRef<ViewSelectedCardComponent> | null = null;
  cardStates = {
    backlog: backlogState,
    started: startedState,
    paused: pausedState,
    completed: completedState,
    archived: archivedState,
  };

  constructor(
    private boardService: BoardService,
    private activeCardPopupService: ActiveCardService,
    private captureCardService: CaptureCardService,
    private cardCommentService: CardCommentService,
    private toastrMessageService: ToastrMessagesService,
    private cardValidationsService: CardValidationHandlerService
  ) {}

  ngOnInit(): void {}

  onDropCard = (event: CdkDragDrop<Array<any>>): void => {
    if (event.previousContainer === event.container) {
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      const previousContainerLinks: Array<string> =
        event.previousContainer.connectedTo.toString().split(',');

      // Checking if the current card is movable to the target container
      if (previousContainerLinks.includes(event.container.id)) {
        // Handling Card Activity Logic
        switch (event.container.id) {
          case archivedState:
            const userCommentSubscription: Subscription =
              this.cardCommentService
                .onCapturedUserComment(archivedState)
                .subscribe((userComment: string | null) => {
                  // Unsubscribing from the observer listener to avoid mulitple emits
                  userCommentSubscription.unsubscribe();

                  if (userComment) {
                    if (
                      event.previousContainer.data[event.previousIndex]
                        .cardStatus === startedState
                    ) {
                      // If a user decides to archive a Card directly from [in-progress] state, stop the countdown window
                      this.activeCardPopupService.deactivateCurrentActiveCard();
                    }

                    // Set the user comment for archiving a card!
                    event.previousContainer.data[
                      event.previousIndex
                    ]?.comments?.push({
                      comment: userComment,
                      commentType: archivedState,
                      cardId:
                        event.previousContainer.data[event.previousIndex].id,
                    });

                    // Changing the Card metadata
                    event.previousContainer.data[
                      event.previousIndex
                    ].cardStatus = archivedState;
                    event.previousContainer.data[
                      event.previousIndex
                    ].pausedCount = 0;
                    event.previousContainer.data[
                      event.previousIndex
                    ].archiveCount += 1;
                    event.previousContainer.data[
                      event.previousIndex
                    ].timeRemaining =
                      event.previousContainer.data[
                        event.previousIndex
                      ].duration;
                    event.previousContainer.data[
                      event.previousIndex
                    ]?.tasks?.forEach((element: CardTaskModel) => {
                      element.complete = false;
                    });

                    // Updating Card changes in the database
                    this.updateCardChanges(event);
                  }
                });
            break;
          case pausedState:
            // If the [pauseLimit] has been reached!
            if (
              parseInt(
                event.previousContainer.data[event.previousIndex].pausedCount
              ) > 0 &&
              parseInt(
                event.previousContainer.data[event.previousIndex].pausedCount
              ) %
                maxPauseCount ===
                0
            ) {
              const userCommentSubscription: Subscription =
                this.cardCommentService
                  .onCapturedUserComment(pausedState)
                  .subscribe((userComment: string | null) => {
                    // Unsubscribing from the observer listener to avoid mulitple emits
                    userCommentSubscription.unsubscribe();

                    if (userComment) {
                      // If a user decides to archive a Card directly from [in-progress] state, stop the countdown window
                      this.activeCardPopupService.deactivateCurrentActiveCard();

                      event.previousContainer.data[
                        event.previousIndex
                      ]?.comments?.push({
                        comment: userComment,
                        commentType: pausedState,
                        cardId:
                          event.previousContainer.data[event.previousIndex].id,
                      });

                      // Changing the Card state
                      event.previousContainer.data[
                        event.previousIndex
                      ].cardStatus = pausedState;

                      // Incrementing the [pauseCount]
                      event.previousContainer.data[
                        event.previousIndex
                      ].pausedCount += 1;

                      // Updating Card changes in the database
                      this.updateCardChanges(event);

                      this.toastrMessageService.showInfoMessage(
                        'Encouragement',
                        getRandomCustomPauseMessage(),
                        maxCustomToastrTimeout
                      );
                    }
                  });
            } else {
              // Deactivate the active session of the currently running card!!
              this.activeCardPopupService.deactivateCurrentActiveCard();

              // Changing the Card state
              event.previousContainer.data[event.previousIndex].cardStatus =
                pausedState;

              // Incrementing the [pauseCount]
              event.previousContainer.data[
                event.previousIndex
              ].pausedCount += 1;

              // Updating Card changes in the database
              this.updateCardChanges(event);

              this.toastrMessageService.showInfoMessage(
                'Encouragement',
                getRandomCustomPauseMessage(),
                maxCustomToastrTimeout
              );
            }
            break;
          case startedState:
            // Guard condition
            if (
              !this.cardValidationsService.canCardBeStarted(
                this.getCardTypeObjectList().started
              )
            )
              return;

            // Changing the Card state
            event.previousContainer.data[event.previousIndex].cardStatus =
              startedState;

            // Starting the [Active Card Popup] window
            this.activeCardPopupService.activateCardCountDown(
              event.previousContainer.data[event.previousIndex]
            );

            // Updating Card changes in the database
            this.updateCardChanges(event);
            break;
          case completedState:
            if (
              event.previousContainer.data[event.previousIndex]?.tasks &&
              event.previousContainer.data[event.previousIndex]?.tasks?.length >
                0
            ) {
              if (
                !this.cardValidationsService.canCompleteCard(
                  getCompletedTasks(
                    event.previousContainer.data[event.previousIndex]?.tasks
                  ),
                  event.previousContainer.data[event.previousIndex]?.tasks
                )
              )
                return;
            }

            // If a user decides to archive a Card directly, [Stop the countdown window]
            this.activeCardPopupService.deactivateCurrentActiveCard();

            // Change Card State
            event.previousContainer.data[event.previousIndex].cardStatus =
              completedState;

            // Updating Card changes in the database
            this.updateCardChanges(event);

            // Show an encouraging message
            this.toastrMessageService.showSuccessMessage(
              'Congradulations',
              getRandomCustomSuccessMessage(),
              maxCustomToastrTimeout
            );
            break;
          case backlogState:
            // Change Card State
            event.previousContainer.data[event.previousIndex].cardStatus =
              backlogState;

            // Updating Card changes in the database
            this.updateCardChanges(event);
            break;
        }
      } else {
        this.toastrMessageService.showErrorMessage(
          'Change Card State',
          `Invalid Card operation! You cannot ${getPresentTenseFromCardState(
            event.container.id
          )} a Card that is ${getPastTenseFromCardState(
            event.previousContainer.id
          )}`,
          maxToastrTimeout
        );
      }
    }
  };

  updateCardChanges(event: CdkDragDrop<any[], any[], any>) {
    console.log('I started here');
    console.log(event.previousContainer);
    this.boardService
      .updateCard(event.previousContainer.data[event.previousIndex])
      .subscribe((response: any) => {
        console.log(response);
        transferArrayItem(
          event.previousContainer.data,
          event.container.data,
          event.previousIndex,
          event.currentIndex
        );
      });
  }

  isPopupOpen(containerId: string): boolean {
    return containerId === startedState;
  }

  addNewCard() {
    this.captureCardService.openCaptureCardDialog();
  }

  ngOnDestroy(): void {
    if (this.activeCardPopupService.getActiveCardObject())
      this.boardService.updateCard(
        this.activeCardPopupService.getActiveCardObject()
      );
  }

  getCardTypeObjectList(): CardTypes {
    return this.boardService.getCardTypeObjectList();
  }
}
