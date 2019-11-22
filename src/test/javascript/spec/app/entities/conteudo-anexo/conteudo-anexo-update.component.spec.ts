import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestorBeneficioTestModule } from '../../../test.module';
import { ConteudoAnexoUpdateComponent } from 'app/entities/conteudo-anexo/conteudo-anexo-update.component';
import { ConteudoAnexoService } from 'app/entities/conteudo-anexo/conteudo-anexo.service';
import { ConteudoAnexo } from 'app/shared/model/conteudo-anexo.model';

describe('Component Tests', () => {
  describe('ConteudoAnexo Management Update Component', () => {
    let comp: ConteudoAnexoUpdateComponent;
    let fixture: ComponentFixture<ConteudoAnexoUpdateComponent>;
    let service: ConteudoAnexoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [ConteudoAnexoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ConteudoAnexoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConteudoAnexoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConteudoAnexoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ConteudoAnexo(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ConteudoAnexo();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
