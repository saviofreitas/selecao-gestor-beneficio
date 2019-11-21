import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBeneficio } from 'app/shared/model/beneficio.model';
import { BeneficioService } from './beneficio.service';

@Component({
  templateUrl: './beneficio-delete-dialog.component.html'
})
export class BeneficioDeleteDialogComponent {
  beneficio: IBeneficio;

  constructor(protected beneficioService: BeneficioService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.beneficioService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'beneficioListModification',
        content: 'Deleted an beneficio'
      });
      this.activeModal.dismiss(true);
    });
  }
}
