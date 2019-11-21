import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestorBeneficioTestModule } from '../../../test.module';
import { BeneficioDetailComponent } from 'app/entities/beneficio/beneficio-detail.component';
import { Beneficio } from 'app/shared/model/beneficio.model';

describe('Component Tests', () => {
  describe('Beneficio Management Detail Component', () => {
    let comp: BeneficioDetailComponent;
    let fixture: ComponentFixture<BeneficioDetailComponent>;
    const route = ({ data: of({ beneficio: new Beneficio(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [BeneficioDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BeneficioDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BeneficioDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.beneficio).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
