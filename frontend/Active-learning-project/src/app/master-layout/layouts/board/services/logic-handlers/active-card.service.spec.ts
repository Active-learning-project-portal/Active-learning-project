import { TestBed } from '@angular/core/testing';

import { ActiveCardService } from './active-card.service';

describe('ActiveCardService', () => {
  let service: ActiveCardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActiveCardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
