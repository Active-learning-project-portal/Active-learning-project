import { TestBed } from '@angular/core/testing';

import { CardValidationHandlerService } from './card-validation-handler.service';

describe('CardValidationHandlerService', () => {
  let service: CardValidationHandlerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CardValidationHandlerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
