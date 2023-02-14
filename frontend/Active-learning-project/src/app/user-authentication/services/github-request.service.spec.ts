import { TestBed } from '@angular/core/testing';

import { GithubRequestService } from './github-request.service';

describe('GithubRequestService', () => {
  let service: GithubRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GithubRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
