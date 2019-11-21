import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestorBeneficioTestModule } from '../../../test.module';
import { MovimentacaoBeneficioUpdateComponent } from 'app/entities/movimentacao-beneficio/movimentacao-beneficio-update.component';
import { MovimentacaoBeneficioService } from 'app/entities/movimentacao-beneficio/movimentacao-beneficio.service';
import { MovimentacaoBeneficio } from 'app/shared/model/movimentacao-beneficio.model';

describe('Component Tests', () => {
  describe('MovimentacaoBeneficio Management Update Component', () => {
    let comp: MovimentacaoBeneficioUpdateComponent;
    let fixture: ComponentFixture<MovimentacaoBeneficioUpdateComponent>;
    let service: MovimentacaoBeneficioService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [MovimentacaoBeneficioUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MovimentacaoBeneficioUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MovimentacaoBeneficioUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MovimentacaoBeneficioService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MovimentacaoBeneficio(123);
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
        const entity = new MovimentacaoBeneficio();
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
