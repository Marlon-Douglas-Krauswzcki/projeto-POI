import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPoiPosicaoComponent } from './list-poi-posicao.component';

describe('ListPoiPosicaoComponent', () => {
  let component: ListPoiPosicaoComponent;
  let fixture: ComponentFixture<ListPoiPosicaoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListPoiPosicaoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListPoiPosicaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
