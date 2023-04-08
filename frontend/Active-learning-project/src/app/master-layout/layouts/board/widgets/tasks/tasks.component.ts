import { Component, Input, OnInit } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { CreateCardTaskComponent } from '../../modals/create-card-task/create-card-task.component';
import {
  CardModel,
  CardStatus,
  CardTaskModel,
  viewType,
} from '../../models/board-model';
import { BoardService } from '../../services/api/board.service';
import { CaptureCardService } from '../../services/logic-handlers/capture-card.service';
import { CardButtonActionService } from '../../services/logic-handlers/card-button-action.service';
import { CardModalHandlerService } from '../../services/modals/card-modal-handler.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css'],
})
export class TasksComponent implements OnInit {
  taskModalReference!: MdbModalRef<CreateCardTaskComponent>;
  newCardTask!: CardTaskModel;

  @Input()
  cardObject!: CardModel;

  @Input()
  viewType!: viewType;

  @Input()
  cardStatus!: CardStatus;

  constructor(
    private boardService: BoardService,
    private captureCardService: CaptureCardService,
    private cardButtonActionService: CardButtonActionService,
    private mdbModalService: CardModalHandlerService<any>
  ) {}

  ngOnInit(): void {}

  toggleTaskForCompletion(element: any) {
    const {
      target: { id },
    } = element;

    if (this.cardObject.tasks) {
      this.cardObject.tasks[id].complete =
        !this.cardObject?.tasks[id]?.complete;

      if (this.viewType === 'view') {
        this.boardService
          .updateCard(this.cardObject)
          .subscribe((updatedCard: CardModel) => {
            console.log(updatedCard);
            this.cardButtonActionService.calculateTaskCompletion(updatedCard);
          });
      }
    }
  }

  removeTask(element: any): void {
    const {
      target: { id },
    } = element;

    if (this.cardObject.tasks) {
      this.cardObject.tasks.splice(id, 1);

      if (this.viewType === 'view') {
        this.boardService
          .updateCard(this.cardObject)
          .subscribe((updatedCard: CardModel) => {
            console.log(updatedCard);
            this.cardButtonActionService.calculateTaskCompletion(updatedCard);
          });
      }
    }
  }

  addMoreTasks() {
    this.taskModalReference =
      this.mdbModalService.openMdbModal<CreateCardTaskComponent>({
        component: CreateCardTaskComponent,
        data: null,
        ignoreBackdropClick: false,
        width: 50,
      });

    this.taskModalReference.onClose.subscribe((newTask: string | null) => {
      if (newTask) {
        // This model must be sent to the backend [add-goal-task]
        this.newCardTask = {
          title: newTask,
          complete: false,
        };
        this.cardObject['tasks']?.push(this.newCardTask);
        // This should send a request to the [add-goal-task] endpoint
        this.boardService
          .updateCard(this.cardObject)
          .subscribe((updatedCard: CardModel) => {
            // When the new task is returned
            // Update it to the goal tasks list
            // this.cardObject['tasks']?.push(this.newCardTask);
            this.cardButtonActionService.calculateTaskCompletion(
              this.cardObject
            );
            console.log(updatedCard);
          });
      }
    });
  }
}
