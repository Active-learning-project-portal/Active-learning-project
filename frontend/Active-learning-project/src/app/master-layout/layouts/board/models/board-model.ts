export type CardStatus =
  | 'backlog'
  | 'started'
  | 'paused'
  | 'completed'
  | 'archived';

export interface CardTypes {
  backlog: Array<CardModel>;
  started: Array<CardModel>;
  paused: Array<CardModel>;
  completed: Array<CardModel>;
  archived: Array<CardModel>;
}

export type viewType = 'create' | 'view';

export type CardButtonAction =
  | 'start'
  | 'resume'
  | 'complete'
  | 'restore'
  | 'archive'
  | 'pause';

export type activeCardPopupWindowState = 'open' | 'close';

export interface CardTaskModel {
  id?: number;
  title: string;
  complete: boolean;
  cardId?: number;
}

export interface CardCommentModel {
  id?: number;
  comment: string;
  commentType: 'paused' | 'archived';
  cardId?: number;
}

export interface CardModel {
  id?: number;
  title: string;
  description: string;
  duration: string;
  tasks?: Array<CardTaskModel>;
  comments?: Array<CardCommentModel>;
  pausedCount: number;
  archivedCount: number;
  cardStatus: CardStatus;
  timeRemaining: string;
  userId: string;
}
