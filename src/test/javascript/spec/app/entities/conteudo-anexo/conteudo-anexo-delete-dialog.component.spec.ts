import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GestorBeneficioTestModule } from '../../../test.module';
import { ConteudoAnexoDeleteDialogComponent } from 'app/entities/conteudo-anexo/conteudo-anexo-delete-dialog.component';
import { ConteudoAnexoService } from 'app/entities/conteudo-anexo/conteudo-anexo.service';

describe('Component Tests', () => {
  describe('ConteudoAnexo Management Delete Component', () => {
    let comp: ConteudoAnexoDeleteDialogComponent;
    let fixture: ComponentFixture<ConteudoAnexoDeleteDialogComponent>;
    let service: ConteudoAnexoService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [ConteudoAnexoDeleteDialogComponent]
      })
        .overrideTemplate(ConteudoAnexoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConteudoAnexoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConteudoAnexoService);
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
