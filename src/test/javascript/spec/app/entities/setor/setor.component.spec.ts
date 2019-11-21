import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestorBeneficioTestModule } from '../../../test.module';
import { SetorComponent } from 'app/entities/setor/setor.component';
import { SetorService } from 'app/entities/setor/setor.service';
import { Setor } from 'app/shared/model/setor.model';

describe('Component Tests', () => {
  describe('Setor Management Component', () => {
    let comp: SetorComponent;
    let fixture: ComponentFixture<SetorComponent>;
    let service: SetorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [SetorComponent],
        providers: []
      })
        .overrideTemplate(SetorComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SetorComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SetorService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Setor(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.setors[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
