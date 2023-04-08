import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCardTaskComponent } from './create-card-task.component';

describe('CreateGoalTaskComponent', () => {
  let component: CreateCardTaskComponent;
  let fixture: ComponentFixture<CreateCardTaskComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateCardTaskComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCardTaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
