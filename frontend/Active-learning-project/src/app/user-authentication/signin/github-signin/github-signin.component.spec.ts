import { ComponentFixture, TestBed } from '@angular/core/testing';

import GithubSigninComponent from './github-signin.component';

describe('GithubSigninComponent', () => {
  let component: GithubSigninComponent;
  let fixture: ComponentFixture<GithubSigninComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GithubSigninComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(GithubSigninComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
