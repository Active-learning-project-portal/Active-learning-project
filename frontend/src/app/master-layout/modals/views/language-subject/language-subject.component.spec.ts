import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LanguageSubjectComponent } from './language-subject.component';

describe('LanguageSubjectComponent', () => {
  let component: LanguageSubjectComponent;
  let fixture: ComponentFixture<LanguageSubjectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LanguageSubjectComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LanguageSubjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
