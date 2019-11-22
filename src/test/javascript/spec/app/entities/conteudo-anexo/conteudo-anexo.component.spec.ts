import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestorBeneficioTestModule } from '../../../test.module';
import { ConteudoAnexoComponent } from 'app/entities/conteudo-anexo/conteudo-anexo.component';
import { ConteudoAnexoService } from 'app/entities/conteudo-anexo/conteudo-anexo.service';
import { ConteudoAnexo } from 'app/shared/model/conteudo-anexo.model';

describe('Component Tests', () => {
  describe('ConteudoAnexo Management Component', () => {
    let comp: ConteudoAnexoComponent;
    let fixture: ComponentFixture<ConteudoAnexoComponent>;
    let service: ConteudoAnexoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [ConteudoAnexoComponent],
        providers: []
      })
        .overrideTemplate(ConteudoAnexoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConteudoAnexoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConteudoAnexoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ConteudoAnexo(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.conteudoAnexos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
