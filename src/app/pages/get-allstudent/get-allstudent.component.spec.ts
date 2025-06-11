import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllstudentComponent } from './get-allstudent.component';

describe('GetAllstudentComponent', () => {
  let component: GetAllstudentComponent;
  let fixture: ComponentFixture<GetAllstudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetAllstudentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetAllstudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
