import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseUnitsComponent } from './course-units.component';

describe('CourseUnitsComponent', () => {
  let component: CourseUnitsComponent;
  let fixture: ComponentFixture<CourseUnitsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseUnitsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CourseUnitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
