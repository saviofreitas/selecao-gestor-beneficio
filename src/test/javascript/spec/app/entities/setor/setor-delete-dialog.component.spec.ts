import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GestorBeneficioTestModule } from '../../../test.module';
import { SetorDeleteDialogComponent } from 'app/entities/setor/setor-delete-dialog.component';
import { SetorService } from 'app/entities/setor/setor.service';

describe('Component Tests', () => {
  describe('Setor Management Delete Component', () => {
    let comp: SetorDeleteDialogComponent;
    let fixture: ComponentFixture<SetorDeleteDialogComponent>;
    let service: SetorService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [SetorDeleteDialogComponent]
      })
        .overrideTemplate(SetorDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SetorDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SetorService);
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
