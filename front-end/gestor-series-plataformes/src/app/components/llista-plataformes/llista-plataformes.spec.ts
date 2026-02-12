import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LlistaPlataformes } from './llista-plataformes';

describe('LlistaPlataformes', () => {
  let component: LlistaPlataformes;
  let fixture: ComponentFixture<LlistaPlataformes>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LlistaPlataformes]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LlistaPlataformes);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
