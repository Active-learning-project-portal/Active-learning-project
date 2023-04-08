import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CardModel, CardTypes, CardTaskModel, CardCommentModel } from '../../models/board-model';


@Injectable({
  providedIn: 'root',
})
export class BoardService {
  
  private cardSubject!: Subject<CardModel>;

  private cardTypeObjectList: CardTypes = {
    backlog: new Array<CardModel>(),
    started: new Array<CardModel>(),
    paused: new Array<CardModel>(),
    completed: new Array<CardModel>(),
    archived: new Array<CardModel>(),
  };

  constructor(
    private http: HttpClient,
  ) {
    this.cardSubject = new Subject<CardModel>();
  }

  getCardSubject(): Subject<CardModel> {
    return this.cardSubject;
  }

  getCardTypeObjectList(): CardTypes {
    return this.cardTypeObjectList;
  }

  emitCard(card: CardModel): void {
    this.getCardSubject().next(card);
  }

  insertNewCard(card: CardModel): void {
    // const user = this.tokenService.getDecodeToken();
    // card.userId = user.id;

    this.http
      .post<CardModel>(`${environment.apiUrl}/card`, card)
      .subscribe((newcard) => {
        this.emitCard(newcard);
      });
  }

  updateCard(card: CardModel): Observable<CardModel> {
    return this.http.put<CardModel>(`${environment.apiUrl}/card`, card);
  }

  onSelectUserCards(user_id: string): Subject<CardModel> {
    this.http
      .get<CardModel[]>(`${environment.apiUrl}/card/${user_id}`)
      .subscribe((cards: CardModel[]) => {
        cards.forEach((card: CardModel) => {
          this.emitCard(card);
        });
      });

    return this.getCardSubject();
  }

  getCardById(id: any): Observable<CardModel> {
    return this.http.get<CardModel>(`${environment.apiUrl}/card/${id}`);
  }

  // cards endpoints

  addCardTask(newcardTask: CardTaskModel): Observable<CardTaskModel> {
    return this.http.post<CardTaskModel>(`${environment.apiUrl}/card/task`, newcardTask);
  }

  updateCardTask(updatedcardTask: CardTaskModel): Observable<CardTaskModel> {
    return this.http.put<CardTaskModel>(`${environment.apiUrl}/card/task/${updatedcardTask.id}`, updatedcardTask);
  }

  removeCardTask(cardTask: CardTaskModel): Observable<CardTaskModel> {
    return this.http.delete<CardTaskModel>(`${environment.apiUrl}/card/task/${cardTask.id}`);
  }

  // Comments
  addCardComment(newcardComment: CardCommentModel): Observable<CardCommentModel> {
    return this.http.post<CardCommentModel>(`${environment.apiUrl}/card/comment`, newcardComment);
  }

  // The following I am not sure
  updateCardComment(updatedcardComment: CardCommentModel): Observable<CardCommentModel> {
    return this.http.put<CardCommentModel>(`${environment.apiUrl}/card/comment/${updatedcardComment.id}`, updatedcardComment);
  }

  removeCardComment(cardCommnent: CardCommentModel): Observable<CardCommentModel> {
    return this.http.delete<CardCommentModel>(`${environment.apiUrl}/card/comment/${cardCommnent.id}`);
  }
}
