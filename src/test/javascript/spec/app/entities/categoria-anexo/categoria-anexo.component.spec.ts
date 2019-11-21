import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestorBeneficioTestModule } from '../../../test.module';
import { CategoriaAnexoComponent } from 'app/entities/categoria-anexo/categoria-anexo.component';
import { CategoriaAnexoService } from 'app/entities/categoria-anexo/categoria-anexo.service';
import { CategoriaAnexo } from 'app/shared/model/categoria-anexo.model';

describe('Component Tests', () => {
  describe('CategoriaAnexo Management Component', () => {
    let comp: CategoriaAnexoComponent;
    let fixture: ComponentFixture<CategoriaAnexoComponent>;
    let service: CategoriaAnexoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [CategoriaAnexoComponent],
        providers: []
      })
        .overrideTemplate(CategoriaAnexoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategoriaAnexoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoriaAnexoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CategoriaAnexo(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.categoriaAnexos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
