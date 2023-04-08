import { TestBed } from '@angular/core/testing';

import { CardButtonActionService } from './card-button-action.service';

describe('GoalButtonActionService', () => {
  let service: CardButtonActionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CardButtonActionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
