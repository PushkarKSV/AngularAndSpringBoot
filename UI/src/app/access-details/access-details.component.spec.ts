import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccessDetailsComponent } from './access-details.component';

describe('AccessDetailsComponent', () => {
  let component: AccessDetailsComponent;
  let fixture: ComponentFixture<AccessDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccessDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccessDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
