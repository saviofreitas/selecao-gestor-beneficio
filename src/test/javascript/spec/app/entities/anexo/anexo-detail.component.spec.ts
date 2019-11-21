import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestorBeneficioTestModule } from '../../../test.module';
import { AnexoDetailComponent } from 'app/entities/anexo/anexo-detail.component';
import { Anexo } from 'app/shared/model/anexo.model';

describe('Component Tests', () => {
  describe('Anexo Management Detail Component', () => {
    let comp: AnexoDetailComponent;
    let fixture: ComponentFixture<AnexoDetailComponent>;
    const route = ({ data: of({ anexo: new Anexo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [AnexoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AnexoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AnexoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.anexo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
