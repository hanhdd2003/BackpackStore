import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewBackpackComponent } from './view-employee.component';

describe('ViewBackpackComponent', () => {
  let component: ViewBackpackComponent;
  let fixture: ComponentFixture<ViewBackpackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewBackpackComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewBackpackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
