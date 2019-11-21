import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestorBeneficioTestModule } from '../../../test.module';
import { CategoriaAnexoUpdateComponent } from 'app/entities/categoria-anexo/categoria-anexo-update.component';
import { CategoriaAnexoService } from 'app/entities/categoria-anexo/categoria-anexo.service';
import { CategoriaAnexo } from 'app/shared/model/categoria-anexo.model';

describe('Component Tests', () => {
  describe('CategoriaAnexo Management Update Component', () => {
    let comp: CategoriaAnexoUpdateComponent;
    let fixture: ComponentFixture<CategoriaAnexoUpdateComponent>;
    let service: CategoriaAnexoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [CategoriaAnexoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CategoriaAnexoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategoriaAnexoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoriaAnexoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CategoriaAnexo(123);
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
        const entity = new CategoriaAnexo();
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
