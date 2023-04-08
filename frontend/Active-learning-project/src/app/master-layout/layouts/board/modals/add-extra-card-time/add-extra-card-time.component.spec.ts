import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddExtraCardTimeComponent } from './add-extra-card-time.component';

describe('AddExtraCardTimeComponent', () => {
  let component: AddExtraCardTimeComponent;
  let fixture: ComponentFixture<AddExtraCardTimeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddExtraCardTimeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddExtraCardTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
