import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GestorBeneficioTestModule } from '../../../test.module';
import { AnexoDeleteDialogComponent } from 'app/entities/anexo/anexo-delete-dialog.component';
import { AnexoService } from 'app/entities/anexo/anexo.service';

describe('Component Tests', () => {
  describe('Anexo Management Delete Component', () => {
    let comp: AnexoDeleteDialogComponent;
    let fixture: ComponentFixture<AnexoDeleteDialogComponent>;
    let service: AnexoService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [AnexoDeleteDialogComponent]
      })
        .overrideTemplate(AnexoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AnexoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AnexoService);
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
