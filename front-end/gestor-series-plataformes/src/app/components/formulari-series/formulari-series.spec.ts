import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormulariSeries } from './formulari-series';

describe('FormulariSeries', () => {
  let component: FormulariSeries;
  let fixture: ComponentFixture<FormulariSeries>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormulariSeries]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormulariSeries);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
