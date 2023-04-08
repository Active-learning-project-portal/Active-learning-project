import { Component } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { toNumber } from 'lodash';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';


import {
  CardCommentModel,
  CardModel,
  CardTaskModel,
} from '../../models/board-model';
import { BoardService } from '../../services/api/board.service';
import { CardModalHandlerService } from '../../services/modals/card-modal-handler.service';
import { ToastrMessagesService } from '../../shared/utils/services/toastr-messages.service';
import { maxToastrTimeout, minCardDuration } from '../../shared/constants/card-boundaries';
import { CreateCardTaskComponent } from '../create-card-task/create-card-task.component';


@Component({
  selector: 'alp-capture-card',
  templateUrl: './capture-card.component.html',
  styleUrls: ['./capture-card.component.css'],
})
export class CaptureCardComponent {
  attendanceId: any;
  taskModalReference!: MdbModalRef<CreateCardTaskComponent>;

  formModel: FormGroup = new FormGroup({
    title: new FormControl(null, [Validators.required]),
    duration: new FormControl(null, [Validators.required]),
    description: new FormControl(''),
  });

  cardObject: CardModel = {
    title: String(''),
    description: String(''),
    duration: String('00:00:00'),
    pausedCount: 0,
    archivedCount: 0,
    cardStatus: 'backlog',
    timeRemaining: String('00:00:00'),
    comments: new Array<CardCommentModel>(),
    tasks: new Array<CardTaskModel>(),
    userId: String(''),
  };

  constructor(
    private modalRef: MdbModalRef<CaptureCardComponent>,
    private boardService:BoardService,
    private toastrMessage: ToastrMessagesService,
    private mdbModalService: CardModalHandlerService<any>
  ) {}

  getFormControl(name: string): AbstractControl {
    return this.formModel.controls[name];
  }

  isFormControlTouched(name: string): boolean {
    return this.getFormControl(name).touched;
  }

  isFormControlInvalid(name: string): boolean {
    return this.getFormControl(name).invalid;
  }

  setCardValues(title: string, duration: string, description: string): void {
    this.cardObject.title = title;
    this.cardObject.duration = duration.concat(':00');
    this.cardObject.description = description;
    this.cardObject.timeRemaining = duration.concat(':00');
  }

  closeCaptureCardModal() {
    this.modalRef.close();
  }

  addCardTask(): void {
    this.taskModalReference =
      this.mdbModalService.openMdbModal<CreateCardTaskComponent>({
        component: CreateCardTaskComponent,
        data: null,
        ignoreBackdropClick: false,
        width: 50,
      });

    this.taskModalReference.onClose.subscribe((newTask: string | null) => {
      if (newTask) {
        this.cardObject['tasks']?.push({
          title: newTask,
          complete: false,
          cardId: this.cardObject.id,
        });
      }
    });
  }

  addNewCard() {
    this.formModel.markAllAsTouched();

    if (this.formModel.invalid) return;

    // Extracting duration timeparts (hours & minutes)
    const [hours, minutes] = this.getFormControl('duration').value.split(':');

    // Business rule [cards must have a minumum duration of 25 minutes]
    if (toNumber(hours) === 0 && toNumber(minutes) < minCardDuration) {
      this.toastrMessage.showErrorMessage(
        'Create New Card',
        `Cannot set a Card with a duration less than ${minCardDuration} minutes`,
        maxToastrTimeout
      );
      return;
    }

    this.setCardValues(
      this.getFormControl('title').value,
      this.getFormControl('duration').value,
      this.getFormControl('description').value
    );

    // console.log(this.cardObject);
    this.boardService.insertNewCard(this.cardObject);

    this.closeCaptureCardModal();
  }
}
