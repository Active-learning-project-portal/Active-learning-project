import { TestBed } from '@angular/core/testing';

import { PLanguageService } from './p-language.service';

describe('PLanguageService', () => {
  let service: PLanguageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PLanguageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
