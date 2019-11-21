import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestorBeneficioTestModule } from '../../../test.module';
import { MovimentacaoBeneficioDetailComponent } from 'app/entities/movimentacao-beneficio/movimentacao-beneficio-detail.component';
import { MovimentacaoBeneficio } from 'app/shared/model/movimentacao-beneficio.model';

describe('Component Tests', () => {
  describe('MovimentacaoBeneficio Management Detail Component', () => {
    let comp: MovimentacaoBeneficioDetailComponent;
    let fixture: ComponentFixture<MovimentacaoBeneficioDetailComponent>;
    const route = ({ data: of({ movimentacaoBeneficio: new MovimentacaoBeneficio(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [MovimentacaoBeneficioDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MovimentacaoBeneficioDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MovimentacaoBeneficioDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.movimentacaoBeneficio).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
