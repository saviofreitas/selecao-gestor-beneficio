import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestorBeneficioTestModule } from '../../../test.module';
import { CategoriaAnexoDetailComponent } from 'app/entities/categoria-anexo/categoria-anexo-detail.component';
import { CategoriaAnexo } from 'app/shared/model/categoria-anexo.model';

describe('Component Tests', () => {
  describe('CategoriaAnexo Management Detail Component', () => {
    let comp: CategoriaAnexoDetailComponent;
    let fixture: ComponentFixture<CategoriaAnexoDetailComponent>;
    const route = ({ data: of({ categoriaAnexo: new CategoriaAnexo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [CategoriaAnexoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CategoriaAnexoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategoriaAnexoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.categoriaAnexo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
