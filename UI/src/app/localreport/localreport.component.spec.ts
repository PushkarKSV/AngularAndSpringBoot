import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalreportComponent } from './localreport.component';

describe('LocalreportComponent', () => {
  let component: LocalreportComponent;
  let fixture: ComponentFixture<LocalreportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LocalreportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LocalreportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
