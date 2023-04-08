import { TestBed } from '@angular/core/testing';

import { CardCommentService } from './card-comment.service';

describe('CardCommentService', () => {
  let service: CardCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CardCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
