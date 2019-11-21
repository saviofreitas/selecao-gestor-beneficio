import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { GestorBeneficioTestModule } from '../../../test.module';
import { MovimentacaoBeneficioComponent } from 'app/entities/movimentacao-beneficio/movimentacao-beneficio.component';
import { MovimentacaoBeneficioService } from 'app/entities/movimentacao-beneficio/movimentacao-beneficio.service';
import { MovimentacaoBeneficio } from 'app/shared/model/movimentacao-beneficio.model';

describe('Component Tests', () => {
  describe('MovimentacaoBeneficio Management Component', () => {
    let comp: MovimentacaoBeneficioComponent;
    let fixture: ComponentFixture<MovimentacaoBeneficioComponent>;
    let service: MovimentacaoBeneficioService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [MovimentacaoBeneficioComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(MovimentacaoBeneficioComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MovimentacaoBeneficioComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MovimentacaoBeneficioService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MovimentacaoBeneficio(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.movimentacaoBeneficios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MovimentacaoBeneficio(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.movimentacaoBeneficios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should re-initialize the page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MovimentacaoBeneficio(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);
      comp.reset();

      // THEN
      expect(comp.page).toEqual(0);
      expect(service.query).toHaveBeenCalledTimes(2);
      expect(comp.movimentacaoBeneficios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,asc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,asc', 'id']);
    });
  });
});
