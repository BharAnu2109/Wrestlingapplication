import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WrestlerFormComponent } from './wrestler-form.component';

describe('WrestlerFormComponent', () => {
  let component: WrestlerFormComponent;
  let fixture: ComponentFixture<WrestlerFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WrestlerFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WrestlerFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
