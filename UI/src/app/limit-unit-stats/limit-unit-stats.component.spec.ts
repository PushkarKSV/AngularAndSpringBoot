import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LimitUnitStatsComponent } from './limit-unit-stats.component';

describe('LimitUnitStatsComponent', () => {
  let component: LimitUnitStatsComponent;
  let fixture: ComponentFixture<LimitUnitStatsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LimitUnitStatsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LimitUnitStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
