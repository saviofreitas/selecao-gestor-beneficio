import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISetor } from 'app/shared/model/setor.model';
import { SetorService } from './setor.service';

@Component({
  templateUrl: './setor-delete-dialog.component.html'
})
export class SetorDeleteDialogComponent {
  setor: ISetor;

  constructor(protected setorService: SetorService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.setorService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'setorListModification',
        content: 'Deleted an setor'
      });
      this.activeModal.dismiss(true);
    });
  }
}
