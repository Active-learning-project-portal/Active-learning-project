import { Injectable } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { CaptureCardComponent } from '../../modals/capture-cards/capture-card.component';
import { CreateCardTaskComponent } from '../../modals/create-card-task/create-card-task.component';
import { CardModel, viewType } from '../../models/board-model';
import { BoardService } from '../api/board.service';
import { CardButtonActionService } from './card-button-action.service';
import { CardModalHandlerService } from '../modals/card-modal-handler.service';

@Injectable({
  providedIn: 'root',
})
export class CaptureCardService {
  private currentCard!: CardModel;

  constructor(
    private boardService: BoardService,
    private mdbModalService: CardModalHandlerService<any>,
    private cardButtonActionService: CardButtonActionService
  ) { }

  public openCaptureCardDialog(attendance?: any) {
    this.mdbModalService.openMdbModal<CaptureCardComponent>({
      component: CaptureCardComponent,
      data: { attendance: attendance ? attendance : null },
      ignoreBackdropClick: false,
      width: 50
    })
  }

  public onTaskCreation(
    taskReference: MdbModalRef<CreateCardTaskComponent>,
    cardObject: CardModel,
    modalActionViewType: viewType = "create"
  ): void {
    taskReference.onClose.subscribe((newTask: string | null) => {
      // Check if user did capture a task on closed the modal
      if (newTask) {
        cardObject['tasks']?.push({
          title: newTask,
          complete: false,
          cardId: cardObject.id
        })

        // If updating a user card task, then update the card task table
        if (modalActionViewType === "view") {
          this.boardService.updateCard(this.currentCard)
            .subscribe((updatedCard: CardModel) => {
              this.cardButtonActionService.calculateTaskCompletion(this.currentCard);
              console.log(updatedCard)
            })
        }
      }
    })
  }
}
