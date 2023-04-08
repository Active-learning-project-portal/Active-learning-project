import { Injectable } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { Subject } from 'rxjs';
import { CommentComponent } from '../../modals/comment/comment.component';
import { CardModalHandlerService } from '../modals/card-modal-handler.service';
import { CardStatus } from '../../models/board-model';

@Injectable({
  providedIn: 'root'
})
export class CardCommentService {
  commentModalRef!: MdbModalRef<CommentComponent>
  commentSubject!: Subject<string | null>

  constructor(private mdbModalService: CardModalHandlerService<any>) {
    this.commentSubject = new Subject<string | null>();
  }

  onCapturedUserComment(commentType: CardStatus): Subject<string | null> {
    this.commentModalRef = this.mdbModalService.openMdbModal<CommentComponent>({
      component: CommentComponent,
      data: { commentType: commentType },
      ignoreBackdropClick: true,
      width: 50
    });

    // Because of the [async] nature of Subject, the return value will be executed first!
    return this.commentSubject;
  }

  emitUserCommentResponseAction(action: string | null) {
    // Emit user-action
    this.commentSubject.next(action)
  }

  closeUserCommentModal(userResponse: string | null) {
    this.emitUserCommentResponseAction(userResponse)
    this.commentModalRef?.close()
  }
}
