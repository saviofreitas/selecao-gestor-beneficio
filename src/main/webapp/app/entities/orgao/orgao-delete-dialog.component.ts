import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrgao } from 'app/shared/model/orgao.model';
import { OrgaoService } from './orgao.service';

@Component({
  templateUrl: './orgao-delete-dialog.component.html'
})
export class OrgaoDeleteDialogComponent {
  orgao: IOrgao;

  constructor(protected orgaoService: OrgaoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.orgaoService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'orgaoListModification',
        content: 'Deleted an orgao'
      });
      this.activeModal.dismiss(true);
    });
  }
}
