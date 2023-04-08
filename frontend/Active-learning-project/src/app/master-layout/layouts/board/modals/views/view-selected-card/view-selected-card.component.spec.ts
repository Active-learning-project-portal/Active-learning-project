import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewSelectedCardComponent } from './view-selected-card.component';

describe('ViewSelectedGoalComponent', () => {
  let component: ViewSelectedCardComponent;
  let fixture: ComponentFixture<ViewSelectedCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewSelectedCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewSelectedCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
