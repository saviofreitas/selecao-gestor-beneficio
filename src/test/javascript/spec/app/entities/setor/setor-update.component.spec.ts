import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestorBeneficioTestModule } from '../../../test.module';
import { SetorUpdateComponent } from 'app/entities/setor/setor-update.component';
import { SetorService } from 'app/entities/setor/setor.service';
import { Setor } from 'app/shared/model/setor.model';

describe('Component Tests', () => {
  describe('Setor Management Update Component', () => {
    let comp: SetorUpdateComponent;
    let fixture: ComponentFixture<SetorUpdateComponent>;
    let service: SetorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [SetorUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SetorUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SetorUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SetorService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Setor(123);
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
        const entity = new Setor();
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
