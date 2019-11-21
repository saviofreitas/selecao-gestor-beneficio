import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GestorBeneficioTestModule } from '../../../test.module';
import { CategoriaAnexoDeleteDialogComponent } from 'app/entities/categoria-anexo/categoria-anexo-delete-dialog.component';
import { CategoriaAnexoService } from 'app/entities/categoria-anexo/categoria-anexo.service';

describe('Component Tests', () => {
  describe('CategoriaAnexo Management Delete Component', () => {
    let comp: CategoriaAnexoDeleteDialogComponent;
    let fixture: ComponentFixture<CategoriaAnexoDeleteDialogComponent>;
    let service: CategoriaAnexoService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [CategoriaAnexoDeleteDialogComponent]
      })
        .overrideTemplate(CategoriaAnexoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategoriaAnexoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoriaAnexoService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
