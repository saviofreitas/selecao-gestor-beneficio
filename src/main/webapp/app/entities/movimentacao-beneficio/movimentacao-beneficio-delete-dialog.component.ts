import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMovimentacaoBeneficio } from 'app/shared/model/movimentacao-beneficio.model';
import { MovimentacaoBeneficioService } from './movimentacao-beneficio.service';

@Component({
  templateUrl: './movimentacao-beneficio-delete-dialog.component.html'
})
export class MovimentacaoBeneficioDeleteDialogComponent {
  movimentacaoBeneficio: IMovimentacaoBeneficio;

  constructor(
    protected movimentacaoBeneficioService: MovimentacaoBeneficioService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.movimentacaoBeneficioService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'movimentacaoBeneficioListModification',
        content: 'Deleted an movimentacaoBeneficio'
      });
      this.activeModal.dismiss(true);
    });
  }
}
