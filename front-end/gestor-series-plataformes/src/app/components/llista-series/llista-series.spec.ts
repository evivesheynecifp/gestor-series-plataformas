import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LlistaSeries } from './llista-series';

describe('LlistaSeries', () => {
  let component: LlistaSeries;
  let fixture: ComponentFixture<LlistaSeries>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LlistaSeries]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LlistaSeries);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
