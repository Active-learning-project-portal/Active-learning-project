import { Injectable } from '@angular/core';
import { maxToastrTimeout } from '../../shared/constants/card-boundaries';
import { ToastrMessagesService } from '../../shared/utils/services/toastr-messages.service';
import { CardModel, CardTaskModel } from '../../models/board-model';


@Injectable({
  providedIn: 'root'
})
export class CardValidationHandlerService {

  constructor(private toastrMessagesService: ToastrMessagesService) { }

  public canCardBeStarted(startedList: Array<CardModel>): boolean {
    if (startedList.length === 0) return true;

    this.toastrMessagesService
      .showErrorMessage(
        "Start Goal",
        "You cannot cannot begin working on this goal while you still have another goal in-progress.",
        maxToastrTimeout);
    return false;
  }

  public canCompleteCard(completedTasks: Array<CardTaskModel>, allGoalTasks: Array<CardTaskModel>): boolean {
    if (completedTasks.length === allGoalTasks?.length) return true;

    this.toastrMessagesService
      .showErrorMessage(
        "Complete Goal",
        "This goal cannot be completed while it still has outstanding tasks.",
        maxToastrTimeout)
    return false;
  }
}
