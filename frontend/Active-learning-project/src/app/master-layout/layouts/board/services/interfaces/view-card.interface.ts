import { MdbModalRef } from "mdb-angular-ui-kit/modal";
import { ViewSelectedCardComponent } from "../../modals/views/view-selected-card/view-selected-card.component";
import { CardButtonAction, CardModel } from "../../models/board-model";

export interface ButtonActionParameters {
    actionType: CardButtonAction,
    card: CardModel,
    modalReference: MdbModalRef<ViewSelectedCardComponent>
}