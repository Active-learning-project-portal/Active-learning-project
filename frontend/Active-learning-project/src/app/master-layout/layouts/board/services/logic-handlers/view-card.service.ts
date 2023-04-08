import { Injectable } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { ViewSelectedCardComponent } from '../../modals/views/view-selected-card/view-selected-card.component';
import { CardModel } from '../../models/board-model';
import { CardModalHandlerService } from '../modals/card-modal-handler.service';

@Injectable({
  providedIn: 'root'
})
export class ViewCardService {
  viewSelectedCardModalRef!: MdbModalRef<ViewSelectedCardComponent>

  constructor(
    private mdbModalService: CardModalHandlerService<any>
  ) { }

  viewSelectedCard(card: CardModel, ignoreBackdropClick: boolean = false): void {
    this.viewSelectedCardModalRef = this.mdbModalService.openMdbModal<ViewSelectedCardComponent>({
      component: ViewSelectedCardComponent,
      data: {
         card: card,
        allowModalClosure: !ignoreBackdropClick
      },
      ignoreBackdropClick: ignoreBackdropClick,
      width: 50
    })
  }

  getViewedCardModalReference(): MdbModalRef<ViewSelectedCardComponent> {
    return this.viewSelectedCardModalRef;
  }

  closeViewedCard() {
    this.getViewedCardModalReference()?.close()
  }
}
