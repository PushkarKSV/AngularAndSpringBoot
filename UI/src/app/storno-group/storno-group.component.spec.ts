import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StornoGroupComponent } from './storno-group.component';

describe('StornoGroupComponent', () => {
  let component: StornoGroupComponent;
  let fixture: ComponentFixture<StornoGroupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StornoGroupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StornoGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
