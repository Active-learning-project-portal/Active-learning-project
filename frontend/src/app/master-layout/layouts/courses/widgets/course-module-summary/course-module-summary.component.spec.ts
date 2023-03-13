import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseModuleSummaryComponent } from './course-module-summary.component';

describe('CourseModuleSummaryComponent', () => {
  let component: CourseModuleSummaryComponent;
  let fixture: ComponentFixture<CourseModuleSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseModuleSummaryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CourseModuleSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
