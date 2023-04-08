import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardColumnHeadersComponent } from './column-headers.component';

describe('GoalColumnHeadersComponent', () => {
  let component: CardColumnHeadersComponent;
  let fixture: ComponentFixture<CardColumnHeadersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardColumnHeadersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardColumnHeadersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
